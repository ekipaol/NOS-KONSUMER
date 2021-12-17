package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.canvasing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseFitur;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseTaspen;
import com.application.bris.ikurma_nos_konsumer.databinding.FragmentResumeTaspenBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

public class FragmentResumeCekTaspen extends Fragment implements Step, KeyValueListener, View.OnClickListener {

    FragmentResumeTaspenBinding binding;
    MparseResponseTaspen lTaspen = new MparseResponseTaspen();

    public FragmentResumeCekTaspen(MparseResponseTaspen ldataTaspen) {
        lTaspen = ldataTaspen;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentResumeTaspenBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        disableText();
        numberText();

        if(lTaspen.getHakPensiun()!=null){
            initialize();
        }

        return view;
    }
    
    private void initialize(){

            binding.etNama.setText(lTaspen.getNamaLengkapPeserta());
            binding.etTglLahir.setText(AppUtil.parseTanggalGeneral(lTaspen.getTanggalLahirPeserta(),"yyyy-MM-dd","dd-MM-yyyy"));
            binding.etManfaatPensiun.setText((lTaspen.getHakPensiun()));
            binding.etKantorBayar.setText(lTaspen.getInstansiKantorBayar());
            binding.etFlagging.setText(lTaspen.getKeteranganFlagging());
            binding.etTglPensiun.setText(AppUtil.parseTanggalGeneral(lTaspen.getTanggalPensiun(),"yyyy-MM-dd","dd-MM-yyyy"));
            binding.etPangkat.setText(lTaspen.getPangkat());
            binding.etKpe.setText(lTaspen.getNoKPE());
            binding.etNotas.setText(lTaspen.getNomorTaspen());
            binding.etHariTua.setText((lTaspen.getHakTabunganHariTua()));
            binding.etTglMulaiTaspen.setText(AppUtil.parseTanggalGeneral(lTaspen.getTanggalMulaiTaspen(),"yyyy-MM-dd","dd-MM-yyyy"));
            binding.etNip.setText(lTaspen.getNip());

    }

    private void disableText() {
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

    private void numberText() {
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
