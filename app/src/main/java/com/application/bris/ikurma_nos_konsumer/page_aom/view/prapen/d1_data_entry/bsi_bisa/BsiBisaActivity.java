package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.bsi_bisa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityPrapenBsiBisaBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.BsiBisa;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BsiBisaActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityPrapenBsiBisaBinding binding;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("MM-yyyy", Locale.US);
    private AppPreferences appPreferences;
    private ApiClientAdapter apiClientAdapter;
    private long idAplikasi;
    private BsiBisa dataBsiBisa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrapenBsiBisaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        appPreferences=new AppPreferences(this);
        apiClientAdapter =new ApiClientAdapter(this);
        idAplikasi=Long.parseLong(getIntent().getStringExtra("idAplikasi"));
        setContentView(view);

        disableEditText();
        backgroundStatusBar();
        allOnClicks();
        AppUtil.toolbarRegular(this, "Pengecekan BSI Bisa");

        loadData();

    }

    private void allOnClicks(){
        binding.btnClose.setOnClickListener(this);
        binding.ivDhn.setOnClickListener(this);
        binding.ivPpatk.setOnClickListener(this);
        binding.ivDttot.setOnClickListener(this);
        binding.ivOpac.setOnClickListener(this);
        binding.ivUnlist.setOnClickListener(this);
        binding.ivPep.setOnClickListener(this);
        binding.ivInternal.setOnClickListener(this);
    }

    private void easyShowSnackbarInformation(String pesan){
        Snackbar snackbar=AppUtil.notifWithButtonCustomLengthAndImage(BsiBisaActivity.this, findViewById(android.R.id.content), pesan,Snackbar.LENGTH_INDEFINITE,R.drawable.ic_info_outline_blue);
        snackbar.setDuration(20000);
        snackbar.setAction("Tutup", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               snackbar.dismiss();
            }
        });
    }


    private void disableEditText() {
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


    private void setData(){
        binding.etTanggalPengecekan.setText(AppUtil.parseTanggalGeneral(dataBsiBisa.getCheckDate(),"yyyy-MM-dd","dd-MM-yyyy"));

        if(dataBsiBisa.getDHN()){
            binding.etHasilDhn.setText("Ditemukan");
            binding.tfHasilDhn.setBackgroundColor(getResources().getColor(R.color.red_btn_bg_color));
        }
        else{
            binding.etHasilDhn.setText("Tidak Ditemukan");
        }

        if(dataBsiBisa.getDTTOT()){
            binding.etHasilDttot.setText("Ditemukan");
            binding.tfHasilDttot.setBackgroundColor(getResources().getColor(R.color.red_btn_bg_color));
        }
        else{
            binding.etHasilDttot.setText("Tidak Ditemukan");
        }

        if(dataBsiBisa.getPPATK()){
            binding.etHasilPpatk.setText("Ditemukan");
            binding.tfHasilPpatk.setBackgroundColor(getResources().getColor(R.color.red_btn_bg_color));
        }
        else{
            binding.etHasilPpatk.setText("Tidak Ditemukan");
        }

        if(dataBsiBisa.getOFAC()){
            binding.etHasilOfac.setText("Ditemukan");
            binding.tfHasilOfac.setBackgroundColor(getResources().getColor(R.color.red_btn_bg_color));
        }
        else{
            binding.etHasilOfac.setText("Tidak Ditemukan");
        }
        if(dataBsiBisa.getUNLIST()){
            binding.etHasilUnlist.setText("Ditemukan");
            binding.tfHasilUnlist.setBackgroundColor(getResources().getColor(R.color.red_btn_bg_color));
        }
        else{
            binding.etHasilUnlist.setText("Tidak Ditemukan");
        }

        if(dataBsiBisa.getPEP()){
            binding.etHasilPep.setText("Ditemukan");
            binding.tfHasilPep.setBackgroundColor(getResources().getColor(R.color.red_btn_bg_color));
        }
        else{
            binding.etHasilPep.setText("Tidak Ditemukan");
        }

        if(dataBsiBisa.getINTERNAL()){
            binding.etHasilInternal.setText("Ditemukan");
            binding.tfHasilInternal.setBackgroundColor(getResources().getColor(R.color.red_btn_bg_color));
        }
        else{
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


    private void loadData(){
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        req.setApplicationId(idAplikasi);
        req.setUID(Integer.toString(appPreferences.getUid()));

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().getBsiBisa(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<BsiBisa>() {
                        }.getType();
                        dataBsiBisa =  gson.fromJson(listDataString, type);

                        if(dataBsiBisa !=null){
                            setData();
                        }
                    }
                    else{
                        AppUtil.notiferror(BsiBisaActivity.this, findViewById(android.R.id.content), response.body().getMessage());

                    }
                }
                else{
                    AppUtil.notiferror(BsiBisaActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(BsiBisaActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");

            }
        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                finish();
                break;
            case R.id.iv_dhn:
                easyShowSnackbarInformation("Daftar Hitam Nasional");
                break;
            case R.id.iv_ppatk:
                easyShowSnackbarInformation("Pusat Pelaporan dan Analisis Transaksi Keuangan");
                break;
            case R.id.iv_dttot:
                easyShowSnackbarInformation("Daftar Terduga Teroris dan Organisasi Teroris");
                break;
            case R.id.iv_opac:
//                easyShowSnackbarInformation("Merupakan Kerupuk Gurih Yang Terbuat dari Udang");
                easyShowSnackbarInformation("The Office of Foreign Assets Control : Pihak yang dilarang transaksi karena ada keterkaitan dengan kriminal");
                break;
            case R.id.iv_pep:
//                easyShowSnackbarInformation("Merupakan 3 Huruf Pertama Produk Pasta Gigi Ternama");
                easyShowSnackbarInformation("Political Exposed Person : Orang yang terekspos secara politik.");
                break;
            case R.id.iv_unlist:
//                easyShowSnackbarInformation("Adalah Daftar Orang yang Masuk UN (Urang Ngawur)");
                easyShowSnackbarInformation("United Nations List (Negative List) : Daftar pihak yang dilarang karena keterkaitan dengan kriminal yang diacu oleh UN (PBB)");
                break;
            case R.id.iv_internal:
//                easyShowSnackbarInformation("Yaitu Sesuatu yang Berada di Dalam");
                easyShowSnackbarInformation("Daftar internal Bank (BSI) terhadap terduga pencucian uang, terorisme, penipuan dan aktifitas mencurigakan");
                break;
        }

    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }
}