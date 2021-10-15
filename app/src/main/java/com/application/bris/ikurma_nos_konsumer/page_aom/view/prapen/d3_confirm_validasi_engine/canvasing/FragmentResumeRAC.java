package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.canvasing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.application.bris.ikurma_nos_konsumer.databinding.FragmentResumeRacBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

public class FragmentResumeRAC extends Fragment implements Step, KeyValueListener, View.OnClickListener{
    FragmentResumeRacBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentResumeRacBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        nominalText();
        disabledText();
        return view;
    }
    private void nominalText(){
        binding.etNominalDsr.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etNominalDsr));
        binding.etNominalDbr.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etNominalDbr));
    }

    private void disabledText(){
        binding.etResumeRac.setFocusable(false);
//        binding.etHasilDbr.setFocusable(false);
        binding.etHistoryIdeb.setFocusable(false);
        binding.etRacStatus.setFocusable(false);
        binding.etMasaPeserta.setFocusable(false);
        binding.etNasabahFlaging.setFocusable(false);
        binding.etNominalDbr.setFocusable(false);
        binding.etNominalDsr.setFocusable(false);
        binding.etNomorTaspen.setFocusable(false);
        binding.etPayrollBsi.setFocusable(false);
        binding.etPersentaseDbr.setFocusable(false);
        binding.etPersentaseDsr.setFocusable(false);
        binding.etRacMasaPeserta.setFocusable(false);
        binding.etRacNomorTaspen.setFocusable(false);
        binding.etRacTmtPensiun.setFocusable(false);
        binding.etRacUmurMin.setFocusable(false);
        binding.etRacUmurNasabah.setFocusable(false);
        binding.etTmtPensiun.setFocusable(false);
        binding.etUmurMin.setFocusable(false);
        binding.etUmurNasabah.setFocusable(false);
        binding.etUmurPembiayaEksisting.setFocusable(false);
        binding.etSuratRekomendasi.setFocusable(false);
        binding.etDhn.setFocusable(false);
        binding.etWo.setFocusable(false);
        binding.etPpatk.setFocusable(false);
        binding.etDttot.setFocusable(false);
        binding.etTanggalPengecekan.setFocusable(false);

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
