package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g1.persiapan_akad;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma_nos_konsumer.databinding.ActivityPersiapanAkadBinding ;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

public class PersiapanAkadActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityPersiapanAkadBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersiapanAkadBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        onclickSelectDialog();
        setContentView(view);
        AppUtil.toolbarRegular(this, "Dokumen Persiapan Akad");
    }

    private  void onclickSelectDialog(){

    }

    @Override
    public void onClick(View v) {

    }
}
