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
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

public class FragmentResumeCekTaspen extends Fragment implements Step, KeyValueListener, View.OnClickListener {

    FragmentResumeTaspenBinding binding;
    List<MparseResponseTaspen> lTaspen = new ArrayList<MparseResponseTaspen>();

    public FragmentResumeCekTaspen(List<MparseResponseTaspen> ldataTaspen) {
        lTaspen = ldataTaspen;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentResumeTaspenBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        disableText();
        numberText();
        initialize();
        return view;
    }
    
    private void initialize(){
        for (int i = 0; i < lTaspen.size(); i++) {
            if (lTaspen.get(i).getParameterName().equalsIgnoreCase("Nama Penerima Pensiun")) {
                binding.etNama.setText(lTaspen.get(i).getParameterValue());
            } else if (lTaspen.get(i).getParameterName().equalsIgnoreCase("Tanggal Lahir Penerima Taspen")) {
                binding.etTglLahir.setText(lTaspen.get(i).getParameterValue());
            }  else if (lTaspen.get(i).getParameterName().equalsIgnoreCase("Gaji/Manfaat Pensiun Terakhir")) {
                binding.etManfaatPensiun.setText(lTaspen.get(i).getParameterValue());
            } else if (lTaspen.get(i).getParameterName().equalsIgnoreCase("Kantor Bayar Sebelumnya")) {
                binding.etKantorBayar.setText(lTaspen.get(i).getParameterValue());
            } else if (lTaspen.get(i).getParameterName().equalsIgnoreCase("Data Flagging")) {
                binding.etFlagging.setText(lTaspen.get(i).getParameterValue());
            } else if (lTaspen.get(i).getParameterName().equalsIgnoreCase("Tanggal BUP")) {
                binding.etTglPensiun.setText(lTaspen.get(i).getParameterValue());
            } else if (lTaspen.get(i).getParameterName().equalsIgnoreCase("Pangkat")) {
                binding.etPangkat.setText(lTaspen.get(i).getParameterValue());
            } else if (lTaspen.get(i).getParameterName().equalsIgnoreCase("No Taspen")) {
                binding.etNotas.setText(lTaspen.get(i).getParameterValue());
            }else if (lTaspen.get(i).getParameterName().equalsIgnoreCase("No KPE")) {
                binding.etKpe.setText(lTaspen.get(i).getParameterValue());
            }else if (lTaspen.get(i).getParameterName().equalsIgnoreCase("Hak Tabungan Hari Tua")) {
                binding.etNotas.setText(lTaspen.get(i).getParameterValue());
            }else if (lTaspen.get(i).getParameterName().equalsIgnoreCase("Tanggal Mulai Taspen")) {
                binding.etTglMulaiTaspen.setText(lTaspen.get(i).getParameterValue());
            }else if (lTaspen.get(i).getParameterName().equalsIgnoreCase("Nomor Induk Dummy")) {
                binding.etNip.setText(lTaspen.get(i).getParameterValue());
            }
        }
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
