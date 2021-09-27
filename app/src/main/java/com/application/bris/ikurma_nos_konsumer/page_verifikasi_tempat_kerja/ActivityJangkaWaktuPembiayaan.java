package com.application.bris.ikurma_nos_konsumer.page_verifikasi_tempat_kerja;

import android.annotation.SuppressLint;
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


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jangka_waktu);
        binding = ActivityJangkaWaktuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        BigDecimal sisabulan = umur.remainder(new BigDecimal(12));
        BigDecimal totumur = umur.divide(new BigDecimal(12),0, RoundingMode.HALF_EVEN);
        binding.etUmurNasabahTaspen.setText(totumur.toString()+" Tahun "+sisabulan.toString() + " Bulan");

        disableEditText();
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Jangka Waktu Pembiayaan");

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


    @Override
    public void onClick(View view) {

    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }
}