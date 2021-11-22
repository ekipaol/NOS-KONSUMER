package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.canvasing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseFitur;
import com.application.bris.ikurma_nos_konsumer.databinding.FragmentResumeFiturBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.List;

public class FragmentResumeFitur extends Fragment implements Step, KeyValueListener, View.OnClickListener {
    FragmentResumeFiturBinding binding;
    private List<MparseResponseFitur> lFitur;
    private String approved;

    public FragmentResumeFitur(List<MparseResponseFitur> ListFitur, String maprroved) {
        lFitur = ListFitur;
        approved = maprroved;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentResumeFiturBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initFitur();

        enabledText();
        numberFormat();
        return view;
    }

    private void initFitur() {
        binding.etResumeFitur.setText(approved);
        for (int j = 0; j < lFitur.size(); j++) {
            if (lFitur.get(j).getParameter().equalsIgnoreCase("Limit Pembiayaan")) {
                binding.etLimitPembiayaan.setText(lFitur.get(j).getValue());
            }
            else if (lFitur.get(j).getParameter().equalsIgnoreCase("Loan type")) {
                binding.etLoanType.setText(lFitur.get(j).getValue());
            }
            else if (lFitur.get(j).getParameter().equalsIgnoreCase("Jangka Waktu Pembiayaan")) {
                binding.etJangkaWaktu.setText(lFitur.get(j).getValue());
            }
            else if (lFitur.get(j).getParameter().equalsIgnoreCase("Asuransi Pembiayaan")) {
                binding.etAsuransi.setText(lFitur.get(j).getValue());
            }
        }
    }

    private void enabledText() {
        binding.etAsuransi.setFocusable(false);
        binding.etResumeFitur.setFocusable(false);
        binding.etJangkaWaktu.setFocusable(false);
        binding.etLimitPembiayaan.setFocusable(false);
        binding.etLoanType.setFocusable(false);
    }

    private void numberFormat() {
        binding.etLimitPembiayaan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etLimitPembiayaan));
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
