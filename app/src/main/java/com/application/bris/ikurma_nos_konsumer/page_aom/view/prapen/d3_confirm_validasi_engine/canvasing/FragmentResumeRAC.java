package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.canvasing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqInquery;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseHasilRAC;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseRAC;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.ParseResponseInquiryHasilCanvasing;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.FragmentResumeRacBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataNasabahPrapen;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentResumeRAC extends Fragment implements Step, KeyValueListener, View.OnClickListener {
    FragmentResumeRacBinding binding;
    private List<MparseResponseRAC> lrac;
    private String approved;
    MparseResponseHasilRAC objRAC;

    public FragmentResumeRAC(List<MparseResponseRAC> lRAC, String maprroved, MparseResponseHasilRAC objHasilRAC) {
        lrac = lRAC;
        approved = maprroved;
        objRAC = objHasilRAC;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentResumeRacBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        nominalText();
        disabledText();
        initialize();
        return view;
    }

    private void initialize() {
        binding.etResumeRac.setText(approved);
        for (int i = 0; i < lrac.size(); i++) {
            if (lrac.get(i).getParameter().equalsIgnoreCase("Umur Nasabaha Jatuh Tempo")) {
                Double kk = Double.parseDouble(lrac.get(i).getValue()) / 12;
                BigDecimal dsrNasabah = new BigDecimal(kk).setScale(0, RoundingMode.HALF_DOWN);
                binding.etUmurNasabah.setText(String.valueOf(dsrNasabah.intValue()));
                binding.etRacUmurNasabah.setText(lrac.get(i).getParameterDesc());
            } else if (lrac.get(i).getParameter().equalsIgnoreCase("Umur minimal saat pengajuan")) {
                Double kk = Double.parseDouble(lrac.get(i).getValue()) / 12;
                BigDecimal dsrNasabah = new BigDecimal(kk).setScale(0, RoundingMode.HALF_DOWN);
                binding.etUmurMin.setText(String.valueOf(dsrNasabah.intValue()));
                binding.etRacUmurMin.setText(lrac.get(i).getParameterDesc());
            } else if (lrac.get(i).getParameter().equalsIgnoreCase("Nomor Taspen/Dapen Lainnya Valid")) {
                binding.etNomorTaspen.setText(lrac.get(i).getValue());
                binding.etRacNomorTaspen.setText(lrac.get(i).getParameterDesc());
            } else if (lrac.get(i).getParameter().equalsIgnoreCase("DSR")) {
                binding.etNominalDsr.setText(lrac.get(i).getValue());
            } else if (lrac.get(i).getParameter().equalsIgnoreCase("DBR")) {
                binding.etPersentaseDsr.setText(lrac.get(i).getValue());
            } else if (lrac.get(i).getParameter().equalsIgnoreCase("Histori Kolektiblitas IDEB")) {
                binding.etHistoryIdeb.setText(lrac.get(i).getValue());
            }else if (lrac.get(i).getParameter().equalsIgnoreCase("Nasabah telah terflagging")) {
                binding.etNasabahFlaging.setText(lrac.get(i).getValue());
            } else if (lrac.get(i).getParameter().equalsIgnoreCase("Nasabah Sudah Payroll di BSI")) {
                binding.etPayrollBsi.setText(lrac.get(i).getValue());
            } else if (lrac.get(i).getParameter().equalsIgnoreCase("TMT ke pensiun aktif maksimum xx bln")) {
                binding.etTmtPensiun.setText(lrac.get(i).getValue());
                binding.etRacTmtPensiun.setText(lrac.get(i).getParameterDesc());
            }else if (lrac.get(i).getParameter().equalsIgnoreCase("Masa Peserta Taspen/ Dapen Lainnya")) {
                binding.etMasaPeserta.setText(lrac.get(i).getValue());
                binding.etRacMasaPeserta.setText(lrac.get(i).getParameterDesc());
            }else if (lrac.get(i).getParameter().equalsIgnoreCase("Surat Rekomendasi dari Instansi")) {
                binding.etSuratRekomendasi.setText(lrac.get(i).getValue());
            } else if (lrac.get(i).getParameter().equalsIgnoreCase("Umur Pembiayaan Eksisting")) {
                binding.etUmurPembiayaEksisting.setText(lrac.get(i).getValue());
            }

            binding.etDhn.setText(objRAC.getHasilDHN());
            binding.etPpatk.setText(objRAC.getHasilPPATK());
            binding.etDttot.setText(objRAC.getHasilDTTOT());
            binding.etRestruk.setText(objRAC.getHasilRestruk());
            binding.etWo.setText(objRAC.getHasilWO());
            binding.etTanggalPengecekan.setText(objRAC.getTanggalCheck());
        }
    }

    private void nominalText() {
        binding.etNominalDsr.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etNominalDsr));
        binding.etNominalDbr.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etNominalDbr));
    }

    private void disabledText() {
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
