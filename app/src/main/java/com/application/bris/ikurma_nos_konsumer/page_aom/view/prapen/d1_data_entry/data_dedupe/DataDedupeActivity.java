package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_dedupe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDedupe;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.FlagAplikasiPojo;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityDedupeBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataDedupe;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataDedupeActivity extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener, ConfirmListener {

    private MISAdapter MISAdapter;
    private PembiayaanAdapter PembiayaanAdapter;
    private PendanaanAdapter PendanaanAdapter;
    private SIFOAdapter SIFOAdapter;

    private List<DataDedupe> data = new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private PrapenAoActivityDedupeBinding binding;
    private List<DataDedupe> dataDedupe;
    private String idAplikasi = "0";
    private boolean nikChange = false;
    List<MGenericModel> dataDropdownFlow = new ArrayList<>();
    private String statusId = "";
    ParseResponseAgunan responsedata;
    JsonArray data1, data2, data3, data4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PrapenAoActivityDedupeBinding.inflate(getLayoutInflater());
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
//        setData();
        onClicks();
        defaultViewCondition();

        if (!statusId.equalsIgnoreCase("d.1")) {
//            noInputMode();
        }


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
        AppPreferences appPreferences = new AppPreferences(DataDedupeActivity.this);
        req.setNIK(binding.etNik.getText().toString());
        req.setApplicationId(Long.parseLong(idAplikasi));
        req.setUID(String.valueOf(appPreferences.getUid()));
        //pantekan uid
//        req.setApplicationId(Long.parseLong("114"));
//        req.setUID("4976");
        Call<ParseResponseAgunan> call = apiClientAdapter.getApiInterface().inquiryDedupe(req);
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
                        binding.tvDatecheck.setText(response.body().getData().get("DateCheck").getAsString());
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
                        params3.height = data3.size() * 180;
                        layout3.setLayoutParams(params3);
                        params4.height = data4.size() * 240;
                        layout4.setLayoutParams(params4);

                        //        List Data MIS
                        binding.rvListPengecekanWise.setVisibility(View.VISIBLE);
                        binding.rvListPengecekanWise.setHasFixedSize(true);
                        MISAdapter = new MISAdapter(DataDedupeActivity.this, data1);
                        binding.rvListPengecekanWise.setLayoutManager(new LinearLayoutManager(DataDedupeActivity.this));
                        binding.rvListPengecekanWise.setItemAnimator(new DefaultItemAnimator());
                        binding.rvListPengecekanWise.setAdapter(MISAdapter);
                        //      List Pembiayaan
                        binding.rvListPengecekanPembiayaan.setVisibility(View.VISIBLE);
                        binding.rvListPengecekanPembiayaan.setHasFixedSize(true);
                        PembiayaanAdapter = new PembiayaanAdapter(DataDedupeActivity.this, data2);
                        binding.rvListPengecekanPembiayaan.setLayoutManager(new LinearLayoutManager(DataDedupeActivity.this));
                        binding.rvListPengecekanPembiayaan.setItemAnimator(new DefaultItemAnimator());
                        binding.rvListPengecekanPembiayaan.setAdapter(PembiayaanAdapter);
                        //        List Pendanaan
                        binding.rvListPengecekanPendanaan.setVisibility(View.VISIBLE);
                        binding.rvListPengecekanPendanaan.setHasFixedSize(true);
                        PendanaanAdapter = new PendanaanAdapter(DataDedupeActivity.this, data3);
                        binding.rvListPengecekanPendanaan.setLayoutManager(new LinearLayoutManager(DataDedupeActivity.this));
                        binding.rvListPengecekanPendanaan.setItemAnimator(new DefaultItemAnimator());
                        binding.rvListPengecekanPendanaan.setAdapter(PendanaanAdapter);
                        //        List SIFO
                        binding.rvListPengecekanSifo.setVisibility(View.VISIBLE);
                        binding.rvListPengecekanSifo.setHasFixedSize(true);
                        SIFOAdapter = new SIFOAdapter(DataDedupeActivity.this, data4);
                        binding.rvListPengecekanSifo.setLayoutManager(new LinearLayoutManager(DataDedupeActivity.this));
                        binding.rvListPengecekanSifo.setItemAnimator(new DefaultItemAnimator());
                        binding.rvListPengecekanSifo.setAdapter(SIFOAdapter);

                    } else {
                        AppUtil.notiferror(DataDedupeActivity.this, findViewById(android.R.id.content), response.body().getMessage());
//                        dataDedupe.clear();
                        MISAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseAgunan> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataDedupeActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");

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


        if (data.size() == 0) {
//            binding.llEmptydata.setVisibility(View.VISIBLE);
        }
    }

//    public void cekDedupe() {
//        //  dataUser = getListUser();
//        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
//        ReqDedupe req = new ReqDedupe();
//        AppPreferences appPreferences = new AppPreferences(DataDedupeActivity.this);
//
//        //pantekan uid
//        req.setNIK(binding.etNik.getText().toString());
//        req.setApplicationId(idAplikasi);
////        req.setUID(String.valueOf(appPreferences.getUid()));
//
//
//        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().inquiryDedupe(req);
//        call.enqueue(new Callback<ParseResponseArr>() {
//            @Override
//            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
//                binding.loading.progressbarLoading.setVisibility(View.GONE);
//                if (response.isSuccessful()) {
//                    if (response.body().getStatus().equalsIgnoreCase("00")) {
//                        String listDataString = response.body().getData().toString();
//                        Gson gson = new Gson();
//                        Type type = new TypeToken<List<DataDedupe>>() {
//                        }.getType();
//
//                        dataDedupe = gson.fromJson(listDataString, type);
////                        dataDedupeAdapter = new DataDedupeAdapter(DataDedupeActivity.this, dataDedupe);
//
//                        MISAdapter.notifyDataSetChanged();
//
//
////                        if (dataDedupe.size() == 0) {
////                            binding.llEmptydata.setVisibility(View.VISIBLE);
////                        } else {
////                            binding.llEmptydata.setVisibility(View.GONE);
////                        }
//                    } else {
//                        AppUtil.notiferror(DataDedupeActivity.this, findViewById(android.R.id.content), response.body().getMessage());
////                        dataDedupe.clear();
//                        MISAdapter.notifyDataSetChanged();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                binding.loading.progressbarLoading.setVisibility(View.GONE);
//                AppUtil.notiferror(DataDedupeActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
//
//            }
//        });
//    }

    public void updateDedupe() {
        //  dataUser = getListUser();
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);

        ReqDedupe req = new ReqDedupe();
        req.setNIK(binding.etNik.getText().toString()); //prapen dipantek pp
        req.setApplicationId(Long.parseLong(idAplikasi));
        req.setUID(String.valueOf(appPreferences.getUid()));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updateDedupe(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        CustomDialog.DialogSuccess(DataDedupeActivity.this, "Success!", "Data Berhasil Disimpan", DataDedupeActivity.this);

                        //update Flagging
                        Realm realm = Realm.getDefaultInstance();
                        FlagAplikasiPojo dataFlag = realm.where(FlagAplikasiPojo.class).equalTo("idAplikasi", Long.parseLong(idAplikasi)).findFirst();
                        if (!realm.isInTransaction()) {
                            realm.beginTransaction();
                        }

                        if (dataFlag != null) {
                            dataFlag.setFlagD1DataDedupe(true);
                        } else {
                            dataFlag = new FlagAplikasiPojo();
                            dataFlag.setIdAplikasi(Long.parseLong(idAplikasi));
                            dataFlag.setFlagD1DataDedupe(true);
                        }

                        realm.insertOrUpdate(dataFlag);
                        realm.close();

                    } else {
                        AppUtil.notiferror(DataDedupeActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataDedupeActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");

            }
        });
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

    private void onClicks() {
        binding.btnLanjutDedupe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.etNik.getText().toString().isEmpty()) {
                    binding.tfNik.setError("Harap Isi NIK Terlebih Dahulu", true);
                } else if (nikChange) {
                    AppUtil.notiferror(DataDedupeActivity.this, findViewById(android.R.id.content), "Harap Klik Cek DEDUPE Dahulu");
                } else {
                    updateDedupe();
                }

            }
        });

        binding.btCekNikDedupe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.etNik.getText().toString().isEmpty()) {
                    binding.tfNik.setError("Harap Isi NIK Terlebih Dahulu", true);
                } else {
                    nikChange = false;
                    cekDataDedupe();
//                    cekDedupe();
                }
            }
        });

        binding.etNik.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nikChange = true;

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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