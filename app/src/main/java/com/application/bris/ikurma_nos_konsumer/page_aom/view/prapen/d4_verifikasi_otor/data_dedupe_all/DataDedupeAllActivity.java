package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.data_dedupe_all;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDedupe;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityDedupeAllBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataDedupe;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.BsiBisa;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataDedupeAllActivity extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener, ConfirmListener {

    private com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.data_dedupe_all.MISAdapter MISAdapter;
    private com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.data_dedupe_all.PembiayaanAdapter PembiayaanAdapter;
    private com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.data_dedupe_all.PendanaanAdapter PendanaanAdapter;
    private com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.data_dedupe_all.SIFOAdapter SIFOAdapter;

    private List<DataDedupe> data = new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private ActivityDedupeAllBinding binding;
    private String idAplikasi = "0";
    private boolean nikChange = false;
    private String statusId = "";
    JsonArray data1, data2, data3, data4;
    private BsiBisa dataBsiBisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDedupeAllBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        if (getIntent().hasExtra("idAplikasi")) {
            idAplikasi = getIntent().getStringExtra("idAplikasi");
        }

        if (getIntent().hasExtra("statusId")) {
            statusId = getIntent().getStringExtra("statusId");
        }

        //pantekan status untuk testing
        customToolbar();
        backgroundStatusBar();
        binding.listItem.setVisibility(View.GONE);
        //initialize status
        cekDataDedupe();
        defaultViewCondition();
        disabletext();
        binding.btnLanjutDedupe.setOnClickListener(v -> finish());
    }

    private void disabletext() {
        binding.etKetDhn.setFocusable(false);
        binding.etHasilDhn.setFocusable(false);
        binding.etKetDttot.setFocusable(false);
        binding.etHasilDttot.setFocusable(false);
        binding.etKetPpatk.setFocusable(false);
        binding.etHasilPpatk.setFocusable(false);
        binding.etKetOfac.setFocusable(false);
        binding.etHasilOfac.setFocusable(false);
        binding.etKetUnlist.setFocusable(false);
        binding.etHasilUnlist.setFocusable(false);
        binding.etKetPep.setFocusable(false);
        binding.etHasilPep.setFocusable(false);
        binding.etKetInternal.setFocusable(false);
        binding.etHasilInternal.setFocusable(false);
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void cekDataDedupe() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqDedupe req = new ReqDedupe();
        AppPreferences appPreferences = new AppPreferences(DataDedupeAllActivity.this);
        req.setApplicationId(Long.parseLong(idAplikasi));
        req.setUID(String.valueOf(appPreferences.getUid()));
        //pantekan uid
//        req.setApplicationId(Long.parseLong("131"));
//        req.setUID("4976");
        Call<ParseResponseAgunan> call = apiClientAdapter.getApiInterface().inquiryDedupeD3(req);
        call.enqueue(new Callback<ParseResponseAgunan>() {
            @Override
            public void onResponse(Call<ParseResponseAgunan> call, Response<ParseResponseAgunan> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        binding.listItem.setVisibility(View.VISIBLE);

                        //        Load Data
                        data1 = response.body().getData().get("OriginatingSistem").getAsJsonArray();
                        data2 = response.body().getData().get("InternalPembiayaan").getAsJsonArray();
                        data3 = response.body().getData().get("InternalPendanaan").getAsJsonArray();
                        data4 = response.body().getData().get("SIFO").getAsJsonArray();

                        //        Set Data
                        binding.tvDatecheck.setText(response.body().getData().get("DateCheckDedupe").getAsString());
                        binding.tvWriteOff.setText(response.body().getData().get("IsWO").getAsBoolean() ? "Iya" : "Tidak");

                        //        Height Seting
                        LinearLayout layout = binding.refreshPengecekanWise;
                        LinearLayout layout2 = binding.refreshPengecekanPembiayaan;
                        LinearLayout layout3 = binding.refreshPengecekanPendanaan;
                        LinearLayout layout4 = binding.refreshPengecekanSifo;

                        ViewGroup.LayoutParams params = layout.getLayoutParams(), params2 = layout2.getLayoutParams(), params3 = layout3.getLayoutParams(), params4 = layout4.getLayoutParams();

                        params.height = data1.size() * 400;
                        layout.setLayoutParams(params);
                        params2.height = data2.size() * 625;
                        layout2.setLayoutParams(params2);
                        params3.height = data3.size() * 200;
                        layout3.setLayoutParams(params3);
                        params4.height = data4.size() * 240;
                        layout4.setLayoutParams(params4);

                        if (data1.size() == 0) {
                            binding.refreshPengecekanWise.setVisibility(View.GONE);
                            binding.tvPengecekanWise.setVisibility(View.VISIBLE);
                        } else {
                            //        List Data MIS
                            binding.rvListPengecekanWise.setVisibility(View.VISIBLE);
                            binding.rvListPengecekanWise.setHasFixedSize(true);
                            MISAdapter = new MISAdapter(DataDedupeAllActivity.this, data1);
                            binding.rvListPengecekanWise.setLayoutManager(new LinearLayoutManager(DataDedupeAllActivity.this));
                            binding.rvListPengecekanWise.setItemAnimator(new DefaultItemAnimator());
                            binding.rvListPengecekanWise.setAdapter(MISAdapter);
                        }
                        if (data2.size() == 0) {
                            binding.refreshPengecekanPembiayaan.setVisibility(View.GONE);
                            binding.tvPengecekanPembiayaan.setVisibility(View.VISIBLE);
                        } else {
                            //      List Pembiayaan
                            binding.rvListPengecekanPembiayaan.setVisibility(View.VISIBLE);
                            binding.rvListPengecekanPembiayaan.setHasFixedSize(true);
                            PembiayaanAdapter = new PembiayaanAdapter(DataDedupeAllActivity.this, data2);
                            binding.rvListPengecekanPembiayaan.setLayoutManager(new LinearLayoutManager(DataDedupeAllActivity.this));
                            binding.rvListPengecekanPembiayaan.setItemAnimator(new DefaultItemAnimator());
                            binding.rvListPengecekanPembiayaan.setAdapter(PembiayaanAdapter);
                        }
                        if (data3.size() == 0) {
                            binding.refreshPengecekanPendanaan.setVisibility(View.GONE);
                            binding.tvPengecekanPendanaan.setVisibility(View.VISIBLE);
                        } else {
                            //        List Pendanaan
                            binding.rvListPengecekanPendanaan.setVisibility(View.VISIBLE);
                            binding.rvListPengecekanPendanaan.setHasFixedSize(true);
                            PendanaanAdapter = new PendanaanAdapter(DataDedupeAllActivity.this, data3);
                            binding.rvListPengecekanPendanaan.setLayoutManager(new LinearLayoutManager(DataDedupeAllActivity.this));
                            binding.rvListPengecekanPendanaan.setItemAnimator(new DefaultItemAnimator());
                            binding.rvListPengecekanPendanaan.setAdapter(PendanaanAdapter);
                        }
                        if (data4.size() == 0) {
                            binding.refreshPengecekanSifo.setVisibility(View.GONE);
                            binding.tvPengecekanSifo.setVisibility(View.VISIBLE);
                        } else {
                            //        List SIFO
                            binding.rvListPengecekanSifo.setVisibility(View.VISIBLE);
                            binding.rvListPengecekanSifo.setHasFixedSize(true);
                            SIFOAdapter = new SIFOAdapter(DataDedupeAllActivity.this, data4);
                            binding.rvListPengecekanSifo.setLayoutManager(new LinearLayoutManager(DataDedupeAllActivity.this));
                            binding.rvListPengecekanSifo.setItemAnimator(new DefaultItemAnimator());
                            binding.rvListPengecekanSifo.setAdapter(SIFOAdapter);
                        }

                        String listDataString = response.body().getData().get("BSIBISA").getAsJsonObject().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<BsiBisa>() {
                        }.getType();
                        dataBsiBisa = gson.fromJson(listDataString, type);

                        if (dataBsiBisa != null) {
                            setData();
                        }

                    } else {
                        AppUtil.notiferror(DataDedupeAllActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseAgunan> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataDedupeAllActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");

            }
        });

    }


    public void customToolbar() {
        binding.toolbarNosearch.tvPageTitle.setText("Data DEDUPE");

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setData() {

        binding.etTanggalPengecekan.setText(AppUtil.parseTanggalGeneral(dataBsiBisa.getCheckDate(), "yyyy-MM-dd", "dd-MM-yyyy"));

        if (dataBsiBisa.getDHN()) {
            binding.etHasilDhn.setText("Ditemukan");
            binding.tfHasilDhn.setBackgroundColor(getResources().getColor(R.color.red_btn_bg_color));
        } else {
            binding.etHasilDhn.setText("Tidak Ditemukan");
        }

        if (dataBsiBisa.getDTTOT()) {
            binding.etHasilDttot.setText("Ditemukan");
            binding.tfHasilDttot.setBackgroundColor(getResources().getColor(R.color.red_btn_bg_color));
        } else {
            binding.etHasilDttot.setText("Tidak Ditemukan");
        }

        if (dataBsiBisa.getPPATK()) {
            binding.etHasilPpatk.setText("Ditemukan");
            binding.tfHasilPpatk.setBackgroundColor(getResources().getColor(R.color.red_btn_bg_color));
        } else {
            binding.etHasilPpatk.setText("Tidak Ditemukan");
        }

        if (dataBsiBisa.getOFAC()) {
            binding.etHasilOfac.setText("Ditemukan");
            binding.tfHasilOfac.setBackgroundColor(getResources().getColor(R.color.red_btn_bg_color));
        } else {
            binding.etHasilOfac.setText("Tidak Ditemukan");
        }
        if (dataBsiBisa.getUNLIST()) {
            binding.etHasilUnlist.setText("Ditemukan");
            binding.tfHasilUnlist.setBackgroundColor(getResources().getColor(R.color.red_btn_bg_color));
        } else {
            binding.etHasilUnlist.setText("Tidak Ditemukan");
        }

        if (dataBsiBisa.getPEP()) {
            binding.etHasilPep.setText("Ditemukan");
            binding.tfHasilPep.setBackgroundColor(getResources().getColor(R.color.red_btn_bg_color));
        } else {
            binding.etHasilPep.setText("Tidak Ditemukan");
        }

        if (dataBsiBisa.getINTERNAL()) {
            binding.etHasilInternal.setText("Ditemukan");
            binding.tfHasilInternal.setBackgroundColor(getResources().getColor(R.color.red_btn_bg_color));
        } else {
            binding.etHasilInternal.setText("Tidak Ditemukan");
        }

        binding.etKetDhn.setText(dataBsiBisa.getHasilDHN());
        binding.etKetDttot.setText(dataBsiBisa.getHasilDTTOT());
        binding.etKetPpatk.setText(dataBsiBisa.getHasilPPATK());
        binding.etKetOfac.setText(dataBsiBisa.getHasilOFAC());
        binding.etKetUnlist.setText(dataBsiBisa.getHasilUNLIST());
        binding.etKetPep.setText(dataBsiBisa.getHasilPEP());
        binding.etKetInternal.setText(dataBsiBisa.getHasilINTERNAL());
    }

    @Override
    public void onSelect(String title, MGenericModel data) {

    }

    @Override
    public void onRefresh() {
//        binding.refresh.setRefreshing(false);
//        binding.rvListDedupe.setVisibility(View.GONE);
        setData();
    }

    private void defaultViewCondition() {
    }

    private void noInputMode() {
        binding.btnLanjutDedupe.setVisibility(View.GONE);
    }

    @Override
    public void success(boolean val) {
        finish();
    }

    @Override
    public void confirm(boolean val) {

    }
}