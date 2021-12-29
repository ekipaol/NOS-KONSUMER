package com.application.bris.ikurma_nos_konsumer.page_aom.view.pipeline.kpr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.KonsumerKMGInquiryPipeline;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.processRejectPipeline;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.model.pipeline.KonsumerKPRDetailPipeline;
import com.application.bris.ikurma_nos_konsumer.page_aom.adapter.tindaklanjut.TindaklanjutAdapater;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListBidangPekerjaan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.tindaklanjut;
import com.application.bris.ikurma_nos_konsumer.util.AppBarStateChangedListener;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonsumerKPRPipelineDetailActivity extends AppCompatActivity implements View.OnClickListener, ConfirmListener {

    @BindView(R.id.tb_custom)
    Toolbar toolbar;
    @BindView(R.id.tv_page_title)
    TextView tv_page_title;
    @BindView(R.id.btn_edit)
    ImageView btn_edit;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.header)
    ImageView header;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.btnfab_edit)
    FloatingActionButton btnfab_edit;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.btn_reject)
    Button btn_reject;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.ll_info)
    LinearLayout ll_info;

    //Data Pembiayaan
    @BindView(R.id.tv_segmen)
    TextView tv_segmen;
    @BindView(R.id.tv_produk)
    TextView tv_produk;
    @BindView(R.id.tv_paket)
    TextView tv_paket;
    @BindView(R.id.tv_program)
    TextView tv_program;
    @BindView(R.id.tv_pihak_ketiga)
    TextView tv_pihak_ketiga;
    @BindView(R.id.tv_proyek_perumahan)
    TextView tv_proyek_perumahan;
    @BindView(R.id.tv_tujuanpenggunaan)
    TextView tv_tujuanpenggunaan;
    @BindView(R.id.tv_plafond)
    TextView tv_plafond;
    @BindView(R.id.tv_tenor)
    TextView tv_tenor;

    //Data Nasabah
    @BindView(R.id.tv_nik)
    TextView tv_nik;
    @BindView(R.id.tv_nama)
    TextView tv_nama;
    @BindView(R.id.tv_tempatlahir)
    TextView tv_tempatlahir;
    @BindView(R.id.tv_tanggallahir)
    TextView tv_tanggallahir;
    @BindView(R.id.tv_nomorhp)
    TextView tv_nomorhp;
    @BindView(R.id.ll_bidang_pekerjaan)
    LinearLayout ll_bidang_pekerjaan;
    @BindView(R.id.label_bidang_pekerjaan)
    TextView label_bidang_pekerjaan;
    @BindView(R.id.tv_bidang_pekerjaan)
    TextView tv_bidang_pekerjaan;
    @BindView(R.id.ll_jenis_pekerjaan)
    LinearLayout ll_jenis_pekerjaan;
    @BindView(R.id.label_jenis_pekerjaan)
    TextView label_jenis_pekerjaan;
    @BindView(R.id.tv_jenis_pekerjaan)
    TextView tv_jenis_pekerjaan;
    @BindView(R.id.label_pendapatan)
    TextView label_pendapatan;
    @BindView(R.id.tv_pendapatan)
    TextView tv_pendapatan;

    //Data Alamat Kantor
    @BindView(R.id.tv_alamat)
    TextView tv_alamat;
    @BindView(R.id.tv_kecamatan)
    TextView tv_kecamatan;
    @BindView(R.id.tv_kota)
    TextView tv_kota;
    @BindView(R.id.tv_provinsi)
    TextView tv_provinsi;

    @BindView(R.id.rv_history_tindaklanjut)
    RecyclerView rv_history_tindaklanjut;
    @BindView(R.id.ll_emptydata)
    LinearLayout ll_emptydata;

    private List<tindaklanjut> tindaklanjuts;
    public static int idPipeline;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private static String val_title;
    private static String val_urlphoto;
    private static Bitmap bitmapPhoto;
    private static String dataPipeline = "";
    private static String dataTindakLanjut = "";
    private KonsumerKPRDetailPipeline data;
    private List<MListBidangPekerjaan> listBidangPekerjaan;
    private MListBidangPekerjaan bidangPekerjaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konsumer_kpr_pipeline_detail);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idPipeline = getIntent().getIntExtra("idPipeline", 0);
        collapsingToolbar.setStatusBarScrimColor(getResources().getColor(R.color.colorBackgroundTransparent));
        backgroundStatusBar();
        checkCollapse();
        btn_back.setOnClickListener(this);
        btn_edit.setOnClickListener(this);
        btnfab_edit.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        btn_reject.setOnClickListener(this);
        header.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ll_emptydata.setVisibility(View.GONE);
        loadDataPipeline();
    }

    private void loadDataPipeline() {
        ll_info.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        KonsumerKMGInquiryPipeline req = new KonsumerKMGInquiryPipeline(idPipeline);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryPipelineKpr(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            if (response.body().getData().get("pipeline").isJsonNull()){
                                ll_info.setVisibility(View.GONE);
                            }
                            else {
                                if (response.body().getData().get("listCatatan").getAsJsonArray().size() == 0){
                                    ll_emptydata.setVisibility(View.VISIBLE);
                                }
                                dataPipeline = response.body().getData().get("pipeline").toString();
                                dataTindakLanjut = response.body().getData().get("listCatatan").toString();
                                ll_info.setVisibility(View.VISIBLE);
                                //DATA PIPELINE
                                Gson gson = new Gson();
                                data = gson.fromJson(dataPipeline, KonsumerKPRDetailPipeline.class);
                                val_title = data.getNama();
                                val_urlphoto = UriApi.Baseurl.URL + UriApi.foto.urlFile + data.getFid_photo();
                                Glide
                                        .with(KonsumerKPRPipelineDetailActivity.this)
                                        .asBitmap()
                                        .load(val_urlphoto)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                header.setImageBitmap(resource);
                                                bitmapPhoto = resource;
                                            }
                                        });
                                tv_segmen.setText(data.getNama_segmen());
                                tv_produk.setText(data.getNama_produk());
                                tv_paket.setText("NON PAKET");
                                tv_program.setText(data.getNama_gimmick());
                                tv_pihak_ketiga.setText(data.getNama_pihak_ketiga());
                                tv_proyek_perumahan.setText(data.getNama_project());
                                tv_tujuanpenggunaan.setText(data.getNama_tujuan_penggunaan());
                                tv_plafond.setText(AppUtil.parseRupiah(Long.toString(Long.valueOf(data.getPlafond()))));
                                tv_tenor.setText(Integer.toString(data.getJw())+ " Bulan");

                                tv_nik.setText(data.getNo_ktp());
                                tv_nama.setText(data.getNama());
                                tv_tempatlahir.setText(data.getTempat_lahir());
                                tv_tanggallahir.setText(AppUtil.parseTanggalLahir(data.getTanggal_lahir()));
                                tv_nomorhp.setText(data.getNo_hp());

                                tv_jenis_pekerjaan.setText(data.getJenis_kpr());
                                loadBidangPekerjaan(data.getBidang_usaha());
                                tv_pendapatan.setText(AppUtil.parseRupiah(Integer.toString(data.getOmzet_per_hari())));
                                tv_alamat.setText(data.getAlamat() + ", " + data.getKelurahan() + ", " + data.getKode_pos());
                                tv_kecamatan.setText(data.getKecamatan());
                                tv_kota.setText(data.getKota());
                                tv_provinsi.setText(data.getProvinsi());

                                //DATA TINDAK LANJUT
                                Type type = new TypeToken<List<tindaklanjut>>() {}.getType();
                                tindaklanjuts = gson.fromJson(dataTindakLanjut, type);
                                TindaklanjutAdapater adp = new TindaklanjutAdapater(KonsumerKPRPipelineDetailActivity.this, tindaklanjuts);
                                rv_history_tindaklanjut.setLayoutManager(new LinearLayoutManager(KonsumerKPRPipelineDetailActivity.this));
                                rv_history_tindaklanjut.setItemAnimator(new DefaultItemAnimator());
                                rv_history_tindaklanjut.setAdapter(adp);
                            }
                        }
                        else {
                            AppUtil.notiferror(KonsumerKPRPipelineDetailActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKPRPipelineDetailActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KonsumerKPRPipelineDetailActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void loadBidangPekerjaan(String bidang_usaha) {
        Log.d("Masuk", "loadBidangPekerjaan");
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listBidangPekerjaan(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<MListBidangPekerjaan>>() {
                            }.getType();

                            listBidangPekerjaan = gson.fromJson(listDataString, type);

                            for (int i = 0; i < response.body().getData().size(); i++){
                                if (listBidangPekerjaan.get(i).getDESC1().equalsIgnoreCase(bidang_usaha)) {
                                    tv_bidang_pekerjaan.setText(listBidangPekerjaan.get(i).getDESC2());
                                }
                            }

                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKPRPipelineDetailActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKPRPipelineDetailActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKPRPipelineDetailActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKPRPipelineDetailActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void sendData(final String type){
        loading.setVisibility(View.VISIBLE);
        processRejectPipeline req = new processRejectPipeline(data.getId(),appPreferences.getUid());
        Call<ParseResponse> call = null;
        if (type.equalsIgnoreCase(getString(R.string.title_gotohotprospek))){
            call = apiClientAdapter.getApiInterface().pipelineToHotprospekKpr(req);
        }
        else if(type.equalsIgnoreCase("Hapus Pipeline")){
            call = apiClientAdapter.getApiInterface().rejectPipelineKpr(req);
        }
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String msg = "";
                            if(type.equalsIgnoreCase(getString(R.string.title_gotohotprospek))){
                                msg = getString(R.string.title_successtohotprospek);
                            }
                            else if(type.equalsIgnoreCase(getString(R.string.title_rejectpipeline))){
                                msg = getString(R.string.title_successtorejectpipeline);
                            }
                            CustomDialog.DialogSuccess(KonsumerKPRPipelineDetailActivity.this, "Success!", msg, KonsumerKPRPipelineDetailActivity.this);
                        }
                        else {
                            AppUtil.notiferror(KonsumerKPRPipelineDetailActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKPRPipelineDetailActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KonsumerKPRPipelineDetailActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorBackgroundTransparent));
        }
    }

    private void checkCollapse(){
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangedListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state.name().equalsIgnoreCase("COLLAPSED")){
                    tv_page_title.setVisibility(View.VISIBLE);
                    btn_edit.setVisibility(View.VISIBLE);
                    if(val_title.length() > 16){
                        val_title = val_title.substring(0, 16)+"..";
                    }
                    tv_page_title.setText(val_title);
                }
                else {
                    tv_page_title.setVisibility(View.GONE);
                    btn_edit.setVisibility(View.GONE);
                    tv_page_title.setText("");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_edit:
                btn_edit.setEnabled(false);
            case R.id.btnfab_edit:
                btnfab_edit.setEnabled(false);
                Intent it = new Intent(this, KonsumerKPRPipelineEditActivity.class);
                it.putExtra("dataPipeline", dataPipeline);
                it.putExtra("dataTindakLanjut", dataTindakLanjut);

                startActivity(it);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_edit.setEnabled(true);
                        btnfab_edit.setEnabled(true);
                    }
                }, 5000);
                break;
            case R.id.header:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", bitmapPhoto);
                break;
            case R.id.btn_send:
                sendData(btn_send.getText().toString().trim());
                break;
            case R.id.btn_reject:
                CustomDialog.DialogConfirmation(this, "Konfirmasi", getString(R.string.title_confirm_rejectpipeline), this);
                break;
        }
    }

    @Override
    public void success(boolean val) {
        if(val)
            finish();
            startActivity(new Intent(this, KonsumerKprPipelineActivity.class));
    }

    @Override
    public void confirm(boolean val) {
        if(val)
            sendData(btn_reject.getText().toString().trim());
    }
}

