package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.canvasing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.application.bris.ikurma_nos_konsumer.databinding.FragmentResumeTaspenBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

public class FragmentResumeCekTaspen extends Fragment implements Step, KeyValueListener, View.OnClickListener{

    FragmentResumeTaspenBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentResumeTaspenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        disableText();
        numberText();
        return view;
    }

    private void disableText(){
        binding.etFlagging.setFocusable(false);
        binding.etHariTua.setFocusable(false);
        binding.etKantorBayar.setFocusable(false);
//        binding.etResumeTaspen.setFocusable(false);
        binding.etKpe.setFocusable(false);
        binding.etManfaatPensiun.setFocusable(false);
        binding.etNama.setFocusable(false);
        binding.etNip.setFocusable(false);
        binding.etNotas.setFocusable(false);
        binding.etPangkat.setFocusable(false);
        binding.etTglLahir.setFocusable(false);
        binding.etTglMulaiTaspen.setFocusable(false);
        binding.etTglPensiun.setFocusable(false);
    }

    private void numberText(){
        binding.etHariTua.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etHariTua));
        binding.etManfaatPensiun.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etHariTua));
    }

    @Override
    public void onClick(View v) {

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
