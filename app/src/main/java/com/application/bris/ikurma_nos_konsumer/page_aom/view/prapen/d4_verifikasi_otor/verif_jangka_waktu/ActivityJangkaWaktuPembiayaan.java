package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_jangka_waktu;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityJangkaWaktuBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataJangkaWaktu;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiIdeb;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_ideb.VerifikasiIdebActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityJangkaWaktuPembiayaan extends AppCompatActivity implements View.OnClickListener {

    private @NonNull
    BigDecimal umur = new BigDecimal(125) ;
    ActivityJangkaWaktuBinding binding;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("MM-yyyy", Locale.US);
    private AppPreferences appPreferences;
    private ApiClientAdapter apiClientAdapter;
    private long idAplikasi;
    private DataJangkaWaktu dataJangkaWaktu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJangkaWaktuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        appPreferences=new AppPreferences(this);
        apiClientAdapter =new ApiClientAdapter(this);
        idAplikasi=Long.parseLong(getIntent().getStringExtra("idAplikasi"));
        setContentView(view);
        BigDecimal();
        disableEditText();
        backgroundStatusBar();
        allOnClicks();
        AppUtil.toolbarRegular(this, "Jangka Waktu Pembiayaan");

        loadData();

    }

    private void allOnClicks(){
        binding.btnClose.setOnClickListener(this);
    }


    private void disableEditText() {
        binding.etUmurNasabahTaspen.setFocusable(false);
        binding.tfUmurNasabahTaspen.setFocusable(false);
        binding.etUmurNasabahDukcapil.setFocusable(false);
        binding.tfUmurNasabahDukcapil.setFocusable(false);
        binding.etMaksimalJangkaWaktuPembiayaanNasabah.setFocusable(false);
        binding.tfMaksimalJangkaWaktuPembiayaanNasabah.setFocusable(false);
        binding.etUmurMaksimalNasabahRac.setFocusable(false);
        binding.tfUmurMaksimalNasabahRac.setFocusable(false);
        binding.etJangkaWaktuPembiayaanMaksimalDifiturProduk.setFocusable(false);
        binding.tfJangkaWaktuPembiayaanMaksimalDifiturProduk.setFocusable(false);
    }


   private void BigDecimal(){
       BigDecimal sisabulan = umur.remainder(new BigDecimal(12));
       BigDecimal totumur = umur.divide(new BigDecimal(12),0, RoundingMode.HALF_EVEN);
       final String text = totumur.toString() + " Tahun " + sisabulan.toString() + " Bulan";
       binding.etUmurNasabahTaspen.setText(text);
       binding.etUmurNasabahDukcapil.setText(text);
       binding.etJangkaWaktuPembiayaanMaksimalDifiturProduk.setText(text);
       binding.etUmurMaksimalNasabahRac.setText(text);
       binding.etMaksimalJangkaWaktuPembiayaanNasabah.setText(text);
    }

    private void setData(){
        binding.etUmurNasabahTaspen.setText(dataJangkaWaktu.getUmurNasabahTASPEN());
        binding.etJangkaWaktuPembiayaanMaksimalDifiturProduk.setText(dataJangkaWaktu.getJangkaWaktuProduk());
        binding.etMaksimalJangkaWaktuPembiayaanNasabah.setText(dataJangkaWaktu.getMaksimalJangkaWaktu());
        binding.etUmurMaksimalNasabahRac.setText(getUmurTahunBulan(dataJangkaWaktu.getUmurMaksimalNasabahRAC()));
        binding.etUmurNasabahDukcapil.setText(getUmurTahunBulan(dataJangkaWaktu.getUmurNasabahDukCapil()));
        binding.etUmurNasabahTaspen.setText(getUmurTahunBulan(dataJangkaWaktu.getUmurNasabahTASPEN()));

    }

    private String getUmurTahunBulan(String usia){
        BigDecimal umur=new BigDecimal(usia);
        BigDecimal sisabulan = umur.remainder(new BigDecimal(12));
        BigDecimal totumur = umur.divide(new BigDecimal(12), 0, RoundingMode.HALF_EVEN);
        String text = totumur.toString() + " Tahun " + sisabulan.toString() + " Bulan";
        return text;
    }

     private void loadData(){
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        req.setApplicationId(idAplikasi);
        //pantekan no aplikasi
//        Toast.makeText(this, "ada pantekan id aplikasi", Toast.LENGTH_SHORT).show();
//        req.setApplicationId(4);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryJangkaWaktu(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<DataJangkaWaktu>() {
                        }.getType();
                        dataJangkaWaktu =  gson.fromJson(listDataString, type);

                        if(dataJangkaWaktu!=null){
                            setData();
                        }


                    }
                    else if (response.body().getStatus().equalsIgnoreCase("01")) {
                        AppUtil.notiferror(ActivityJangkaWaktuPembiayaan.this, findViewById(android.R.id.content), "Data Belum Pernah Disimpan Sebellumnya, Silahkan Diisi");
                    }
                    else{
                        AppUtil.notiferror(ActivityJangkaWaktuPembiayaan.this, findViewById(android.R.id.content), response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityJangkaWaktuPembiayaan.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                finish();
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