package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_jangka_waktu;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityJangkaWaktuBinding;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ActivityJangkaWaktuPembiayaan extends AppCompatActivity implements View.OnClickListener {

    private @NonNull
    BigDecimal umur = new BigDecimal(125) ;
    ActivityJangkaWaktuBinding binding;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("MM-yyyy", Locale.US);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJangkaWaktuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        BigDecimal();
        disableEditText();
        backgroundStatusBar();
        allOnClicks();
        AppUtil.toolbarRegular(this, "Jangka Waktu Pembiayaan");

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