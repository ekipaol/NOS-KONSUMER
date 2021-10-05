package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_pendapatan;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.application.bris.ikurma_nos_konsumer.databinding.FragmentDokumenPendapatanBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class FragmentDokumenPendapatan extends Fragment implements Step, KeyValueListener, View.OnClickListener {

    private FragmentDokumenPendapatanBinding binding;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDokumenPendapatanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        hideItem();
        return view;
    }
    @Override
    public void onClick(View v) {

    }

    private void hideItem(){
        binding.btnRekeningKoran1.setVisibility(View.GONE);
        binding.btnRekeningKoran2.setVisibility(View.GONE);
        binding.btnSlipgajiP1.setVisibility(View.GONE);
        binding.btnSlipgajiP2.setVisibility(View.GONE);
        binding.btnSlipgajiP3.setVisibility(View.GONE);
        binding.btnSliptunjanganP1.setVisibility(View.GONE);
        binding.btnSliptunjanganP2.setVisibility(View.GONE);
        binding.btnSliptunjanganP3.setVisibility(View.GONE);
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {

    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {

    }
}
