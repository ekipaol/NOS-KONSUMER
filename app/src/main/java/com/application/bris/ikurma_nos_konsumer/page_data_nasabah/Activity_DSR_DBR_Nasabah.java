package com.application.bris.ikurma_nos_konsumer.page_data_nasabah;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityDsrDbrNasabahBinding ;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Activity_DSR_DBR_Nasabah extends AppCompatActivity {
    private ActivityDsrDbrNasabahBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDsrDbrNasabahBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        numberText();
        onchangeText();
        disableText();


    }

    private void disableText(){
        binding.etSisapenghasilan.setFocusable(false);
        binding.etSisaDsrDbr.setFocusable(false);
        binding.etMaxAngsuran.setFocusable(false);
        binding.etDsrDbr.setFocusable(false);
        binding.etKetentuan.setFocusable(false);
    }

    private void onchangeText(){
        binding.etAngsuranPinjamanEksisting.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                BigDecimal verangsuran = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString( binding.etAngsuranPinjamanEksisting.getText().toString()));
                BigDecimal vertotverpendapatan = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString("10000"));
                BigDecimal verparamProduk= new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString("10000"));
                BigDecimal sisaPendapatan  = (vertotverpendapatan.subtract(verangsuran));
                BigDecimal dsrNasabah = (verangsuran.divide(vertotverpendapatan, 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal(1)));
                BigDecimal sisaDsr = (verparamProduk.subtract(sisaPendapatan));
                BigDecimal max_angsuran = (vertotverpendapatan.multiply(sisaDsr));
                binding.etSisapenghasilan.setText(String.valueOf(sisaPendapatan));
                binding.etDsrDbr.setText(String.valueOf(dsrNasabah));
                binding.etSisaDsrDbr.setText(String.valueOf(sisaDsr));
                binding.etMaxAngsuran.setText(String.valueOf(max_angsuran));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void numberText(){
        binding.etSisapenghasilan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etSisapenghasilan));
        binding.etAngsuranPinjamanEksisting.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etAngsuranPinjamanEksisting));
        binding.etMaxAngsuran.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etMaxAngsuran));
        binding.etDsrDbr.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etDsrDbr));
        binding.etSisaDsrDbr.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etSisaDsrDbr));
    }
}
