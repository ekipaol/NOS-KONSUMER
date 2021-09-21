package com.application.bris.ikurma_nos_konsumer.page_data_nasabah;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityDsrDbrNasabahBinding ;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;

public class Activity_DSR_DBR_Nasabah extends AppCompatActivity {
    private ActivityDsrDbrNasabahBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDsrDbrNasabahBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.etSisapenghasilan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etSisapenghasilan));
        binding.etAngsuranPinjamanEksisting.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etAngsuranPinjamanEksisting));
        binding.etMaxAngsuran.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etMaxAngsuran));
        binding.etDsrDbr.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etDsrDbr));
        binding.etSisaDsrDbr.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etSisaDsrDbr));
        binding.etSisapenghasilan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
