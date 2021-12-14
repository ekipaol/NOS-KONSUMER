package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_kesimpulan;


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
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityKesimpulanVerifikasiBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataVerifikasiKesimpulan;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KesimpulanVerifikasi extends AppCompatActivity implements View.OnClickListener {

    private
    BigDecimal umur = new BigDecimal(125);
    @NonNull
    ActivityKesimpulanVerifikasiBinding binding;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private Long idAplikasi;
    private DataVerifikasiKesimpulan dataVerifikasiKesimpulan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKesimpulanVerifikasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        appPreferences=new AppPreferences(this);
        apiClientAdapter=new ApiClientAdapter(this);
        idAplikasi=Long.parseLong(getIntent().getStringExtra("idAplikasi"));
        setContentView(view);
        disableEditText();
        NumberText();
        backgroundStatusBar();
        allOnClicks();
        AppUtil.toolbarRegular(this, "Kesimpulan Verifikasi");
        loadData();

    }


    private void disableEditText() {
        binding.etPensesuaianRac.setFocusable(false);
        binding.tfPensesuaianRac.setFocusable(false);
        binding.etPensesuaianFiturPembiayaan.setFocusable(false);
        binding.tfPensesuaianFiturPembiayaan.setFocusable(false);
        binding.etMaksimalJangkaWaktuPembiayaanNasabah.setFocusable(false);
        binding.etMaksimalJangkaWaktuPembiayaanNasabah.setFocusable(false);
        binding.etMaksimalAngsuranBulanan.setFocusable(false);
        binding.tfMaksimalAngsuranBulanan.setFocusable(false);
    }

    private void setData(){
//        binding.etHasilRekomendasiScoring.setText(dataVerifikasiKesimpulan.get);
        binding.etMaksimalAngsuranBulanan.setText(dataVerifikasiKesimpulan.getMaksimalAngsuranBulanan());
        binding.etMaksimalJangkaWaktuPembiayaanNasabah.setText(dataVerifikasiKesimpulan.getMaksimalJangkaWaktu());
        binding.etPensesuaianFiturPembiayaan.setText(dataVerifikasiKesimpulan.getSesuaiFiturPembiayaan());
        binding.etPensesuaianRac.setText(dataVerifikasiKesimpulan.getSesuaiRAC());
        binding.etHasilRekomendasiScoring.setText(dataVerifikasiKesimpulan.getRekomendasiScoring());


    }

    private void loadData(){
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        req.setApplicationId(idAplikasi);
        //pantekan no aplikasi
//        Toast.makeText(this, "ada pantekan id aplikasi", Toast.LENGTH_SHORT).show();
//        req.setApplicationId(4);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryKesimpulanVerifikasi(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<DataVerifikasiKesimpulan>() {
                        }.getType();
                        dataVerifikasiKesimpulan =  gson.fromJson(listDataString, type);

                        if(dataVerifikasiKesimpulan!=null){
                            setData();
                        }


                    }
                    else if (response.body().getStatus().equalsIgnoreCase("01")) {
                        AppUtil.notiferror(KesimpulanVerifikasi.this, findViewById(android.R.id.content), "Data Belum Pernah Disimpan Sebellumnya, Silahkan Diisi");
                    }
                    else{
                        AppUtil.notiferror(KesimpulanVerifikasi.this, findViewById(android.R.id.content), response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(KesimpulanVerifikasi.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    private void NumberText(){
        binding.etMaksimalAngsuranBulanan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etMaksimalAngsuranBulanan));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                finish();
                break;
        }
    }

    private void allOnClicks(){
        binding.btnClose.setOnClickListener(this);
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

//        public void sendMessage(View view) {
        // Do something in response to button
//        Intent intent = new Intent(this, KesimpulanVerifikasi.class);
//        EditText editText = (EditText) findViewById(R.id.et_pensesuaian_rac);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        String message2 = CheckAnswer(message);
//          intent.putExtra(CORRECT_MESSAGE, message2);
//        startActivity(intent);
//    }

//    public static String CheckAnswer(String string) {
//        String sesuai[] = {"Sesuai","Tidak Sesuai"};
//          String answer = null;
//        for (int i = 0; i <= sesuai.length - 1; i++) {
//            if (string == sesuai[i]) {
//                answer = "Sesuai";
//                break;
//            } else answer = "Tidak .";
//       }
//        return answer;
//    }
}