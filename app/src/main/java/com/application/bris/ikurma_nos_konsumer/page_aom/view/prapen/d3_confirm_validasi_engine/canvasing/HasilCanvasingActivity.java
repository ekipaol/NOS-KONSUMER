package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.canvasing;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityHasilCanvasingBinding;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

public class HasilCanvasingActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityHasilCanvasingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHasilCanvasingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        disableEditText();
        hidden();
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Hasil Canvasing");
    }

    private void disableEditText() {
        binding.etDataDukcapil.setFocusable(false);
        binding.etAsuransiPenjaminan.setFocusable(false);
        binding.etCekTaspen.setFocusable(false);
        binding.etDetailFlagging.setFocusable(false);
        binding.etDukcapil.setFocusable(false);
        binding.etDsrDbrMax.setFocusable(false);
        binding.etFitur.setFocusable(false);
        binding.etFiturStatus.setFocusable(false);
        binding.etIdeb.setFocusable(false);
        binding.etJangkaWaktuMax.setFocusable(false);
        binding.etManfaatPensiun.setFocusable(false);
        binding.etParamIdeb.setFocusable(false);
        binding.etPlafondMax.setFocusable(false);
        binding.etRac.setFocusable(false);
        binding.etRacStatus.setFocusable(false);
        binding.etScoring.setFocusable(false);
        binding.etStatusPengajuan.setFocusable(false);
        binding.etTotalKewajiban.setFocusable(false);

    }
    private void hidden(){
        binding.btnIDeb.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {

    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }

    }
}
