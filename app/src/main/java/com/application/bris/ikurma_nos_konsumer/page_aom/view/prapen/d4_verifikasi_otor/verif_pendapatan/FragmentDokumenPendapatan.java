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

import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.DokumenPendapatan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDocument;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.FragmentDokumenPendapatanBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.dokumen_pendapatan.ActivityDokumenPendapatan;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class FragmentDokumenPendapatan extends Fragment implements Step, KeyValueListener, View.OnClickListener {

    private FragmentDokumenPendapatanBinding binding;
    JsonObject dataPendapatan;
    DokumenPendapatan dp;
    ReqDocument DPSlipGajiP1, DPSlipGajiP2, DPSlipGajiP3, DPSlipTunjanganP1, DPSlipTunjanganP2, DPSlipTunjanganP3, DPKoran;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    DokumenPendapatan doc = new DokumenPendapatan();
    String clicker,
            pDatep1, pDatep2, pDatep3;
    ReqDocument DokumenPendapatanKoranBank = new ReqDocument(), DokumenPendapatanKoranBSI = new ReqDocument(),
            DokumenPendapatanSlipGajiP1 = new ReqDocument(), DokumenPendapatanSlipTunjanganP1 = new ReqDocument(),
            DataJaminanIDCard = new ReqDocument(), DokumenPendapatanSlipGajiP2 = new ReqDocument(),
            DokumenPendapatanSlipTunjanganP2 = new ReqDocument(), DokumenPendapatanSlipGajiP3 = new ReqDocument(),
            DokumenPendapatanSlipTunjanganP3 = new ReqDocument();

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    public FragmentDokumenPendapatan (JsonObject dataO){
        dataPendapatan = dataO;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDokumenPendapatanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        hideItem();
        disabledText();
        numberText();
        initialize();
        return view;
    }

    private void initialize(){
        String SSlipGajiP2 = "", SSlipGajiP3 = "", SSlipTunjanganP2 = "", SSlipTunjanganP3 = "", SSKoran = "", SSlipGajiP1 = "", SSlipTunjanganP1 = "";
        String listDataString = dataPendapatan.get("DokumenPendapatan").getAsJsonObject().toString();
        if (dataPendapatan.get("DokumenPendapatanKoranBank") != null) {
            SSKoran = dataPendapatan.get("DokumenPendapatanKoranBank").toString();
        }
        if (dataPendapatan.get("DokumenPendapatanSlipGajiP1") != null) {
            SSlipGajiP1 = dataPendapatan.get("DokumenPendapatanSlipGajiP1").toString();
            SSlipTunjanganP1 = dataPendapatan.get("DokumenPendapatanSlipTunjanganP1").toString();
        }

        if (dataPendapatan.get("DokumenPendapatanSlipGajiP2") != null) {
            SSlipGajiP2 = dataPendapatan.get("DokumenPendapatanSlipGajiP2").toString();
            SSlipGajiP3 = dataPendapatan.get("DokumenPendapatanSlipGajiP3").toString();
            SSlipTunjanganP2 = dataPendapatan.get("DokumenPendapatanSlipTunjanganP2").toString();
            SSlipTunjanganP3 = dataPendapatan.get("DokumenPendapatanSlipTunjanganP3").toString();
        }

        Gson gson = new Gson();
        dp = gson.fromJson(listDataString, DokumenPendapatan.class);
        if (dataPendapatan.get("DokumenPendapatanKoranBank") != null) {
            DPKoran = gson.fromJson(SSKoran, ReqDocument.class);
        }
        if (dataPendapatan.get("DokumenPendapatanSlipGajiP1") != null) {
            DPSlipGajiP1 = gson.fromJson(SSlipGajiP1, ReqDocument.class);
            DPSlipTunjanganP1 = gson.fromJson(SSlipTunjanganP1, ReqDocument.class);
        }
        if (dataPendapatan.get("DokumenPendapatanSlipGajiP2") != null) {
            DPSlipGajiP2 = gson.fromJson(SSlipGajiP2, ReqDocument.class);
            DPSlipTunjanganP2 = gson.fromJson(SSlipTunjanganP2, ReqDocument.class);
            DPSlipGajiP3 = gson.fromJson(SSlipGajiP3, ReqDocument.class);
            DPSlipTunjanganP3 = gson.fromJson(SSlipTunjanganP3, ReqDocument.class);
        }

        // Set Data
        try {
            binding.etAkseptasiPendaptan.setText(dp.getAkseptasiPendapatan());
            binding.etPendapatanPensiun.setText(String.valueOf(dp.getSimulasiPendapatanSaatPen()));
            binding.etPeriodeGajiP1.setText(AppUtil.parseTanggalGeneral(dp.getPeriodeGajiP1(), "yyyy-MM-dd", "MM-yyyy"));
            binding.etTglTunjanganP1.setText(AppUtil.parseTanggalGeneral(dp.getPeriodeTunjanganP1(), "yyyy-MM-dd", "MM-yyyy"));
            pDatep1 = AppUtil.parseTanggalGeneral(dp.getPeriodeGajiP1(), "yyyy-MM-dd", "dd-MM-yyyy");
            binding.etGajiBersihP1.setText(String.valueOf(dp.getTotalGajiBersihP1()));
            binding.etTunjanganP1.setText(String.valueOf(dp.getTotalTunjanganBersihP1()));

            if (dataPendapatan.get("DokumenPendapatanSlipGajiP2") != null) {
                binding.etPeriodeGajiP2.setText(AppUtil.parseTanggalGeneral(dp.getPeriodeGajiP2(), "yyyy-MM-dd", "MM-yyyy"));
                binding.etPeriodeGajiP3.setText(AppUtil.parseTanggalGeneral(dp.getPeriodeGajiP3(), "yyyy-MM-dd", "MM-yyyy"));
                binding.etTglTunjanganP2.setText(AppUtil.parseTanggalGeneral(dp.getPeriodeTunjanganP2(), "yyyy-MM-dd", "MM-yyyy"));
                binding.etTglTunjanganP3.setText(AppUtil.parseTanggalGeneral(dp.getPeriodeTunjanganP3(), "yyyy-MM-dd", "MM-yyyy"));
                pDatep2 = AppUtil.parseTanggalGeneral(dp.getPeriodeGajiP2(), "yyyy-MM-dd", "dd-MM-yyyy");
                pDatep3 = AppUtil.parseTanggalGeneral(dp.getPeriodeGajiP3(), "yyyy-MM-dd", "dd-MM-yyyy");
                binding.etGajiBersihP2.setText(String.valueOf(dp.getTotalGajiBersihP2()));
                binding.etGajiBersihP3.setText(String.valueOf(dp.getTotalGajiBersihP3()));
                binding.etTunjanganP2.setText(String.valueOf(dp.getTotalTunjanganBersihP2()));
                binding.etTunjanganP3.setText(String.valueOf(dp.getTotalTunjanganBersihP3()));
            }

            binding.etVerfikasiGajiTunjangan.setText(dp.getCerminanGajiDanTunjD3());
            if (dataPendapatan.get("CheckNorek").getAsString().equalsIgnoreCase("true")) {
                binding.etVerfikasiRekening.setText("Ya");
            } else {
                binding.etVerfikasiRekening.setText("Tidak");
            }
            if (dataPendapatan.get("CheckNorek").getAsString().equalsIgnoreCase("true")) {
                binding.tfNorekTunjangan.setVisibility(View.GONE);
                binding.tfNamaBankTunjangan.setVisibility(View.GONE);
                binding.tfTotalKredit2.setVisibility(View.GONE);
                binding.tfTotalDebit2.setVisibility(View.GONE);
                binding.tfPeriodeAwalWaktu2.setVisibility(View.GONE);
                binding.tfPeriodeAkhirWaktu2.setVisibility(View.GONE);
                binding.norekTunjangan.setVisibility(View.GONE);
            } else if (dataPendapatan.get("CheckNorek").getAsString().equalsIgnoreCase("false")) {
                binding.tfNorekTunjangan.setVisibility(View.VISIBLE);
                binding.tfNamaBankTunjangan.setVisibility(View.VISIBLE);
                binding.tfTotalKredit2.setVisibility(View.VISIBLE);
                binding.tfTotalDebit2.setVisibility(View.VISIBLE);
                binding.tfPeriodeAwalWaktu2.setVisibility(View.VISIBLE);
                binding.tfPeriodeAkhirWaktu2.setVisibility(View.VISIBLE);
                binding.norekTunjangan.setVisibility(View.VISIBLE);
            }
            if (dp.getAkseptasiPendapatan().equalsIgnoreCase("Pendapatan Saat Aktif dan Manfaat Pensiun")) {
                binding.tfGajiBersihP2.setVisibility(View.VISIBLE);
                binding.rlSlipgajiP2.setVisibility(View.VISIBLE);
                binding.tfPeriodeGajiP2.setVisibility(View.VISIBLE);
                binding.tfTunjanganP2.setVisibility(View.VISIBLE);
                binding.tfTglTunjanganP2.setVisibility(View.VISIBLE);
                binding.rlSliptunjanganP2.setVisibility(View.VISIBLE);
                binding.tp2.setVisibility(View.VISIBLE);
                binding.tpg2.setVisibility(View.VISIBLE);
                binding.tpt2.setVisibility(View.VISIBLE);
                binding.tfGajiBersihP3.setVisibility(View.VISIBLE);
                binding.rlSlipgajiP3.setVisibility(View.VISIBLE);
                binding.tfPeriodeGajiP3.setVisibility(View.VISIBLE);
                binding.tfTunjanganP3.setVisibility(View.VISIBLE);
                binding.tfTglTunjanganP3.setVisibility(View.VISIBLE);
                binding.rlSliptunjanganP3.setVisibility(View.VISIBLE);
                binding.tp3.setVisibility(View.VISIBLE);
                binding.tpg3.setVisibility(View.VISIBLE);
                binding.tpt3.setVisibility(View.VISIBLE);
            } else if (dp.getAkseptasiPendapatan().equalsIgnoreCase("Hanya Manfaat Pensiun")) {
                binding.tfGajiBersihP2.setVisibility(View.GONE);
                binding.rlSlipgajiP2.setVisibility(View.GONE);
                binding.tfPeriodeGajiP2.setVisibility(View.GONE);
                binding.tfTunjanganP2.setVisibility(View.GONE);
                binding.tfTglTunjanganP2.setVisibility(View.GONE);
                binding.rlSliptunjanganP2.setVisibility(View.GONE);
                binding.tp2.setVisibility(View.GONE);
                binding.tpg2.setVisibility(View.GONE);
                binding.tpt2.setVisibility(View.GONE);
                binding.tfGajiBersihP3.setVisibility(View.GONE);
                binding.rlSlipgajiP3.setVisibility(View.GONE);
                binding.tfPeriodeGajiP3.setVisibility(View.GONE);
                binding.tfTunjanganP3.setVisibility(View.GONE);
                binding.tfTglTunjanganP3.setVisibility(View.GONE);
                binding.rlSliptunjanganP3.setVisibility(View.GONE);
                binding.tp3.setVisibility(View.GONE);
                binding.tpg3.setVisibility(View.GONE);
                binding.tpt3.setVisibility(View.GONE);
            }

            binding.etNorekGaji.setText(dp.getNomorRekBankGaji());
            binding.etNamaBankGaji.setText(dp.getNamaBankGaji());
            binding.etPeriodeAwalWaktu1.setText(AppUtil.parseTanggalGeneral(dp.getPeriodeDateFromGaji(), "yyyy-MM-dd", "dd-MM-yyyy"));
            binding.etPeriodeAkhirWaktu1.setText(AppUtil.parseTanggalGeneral(dp.getPeriodeDateToGaji(), "yyyy-MM-dd", "dd-MM-yyyy"));
            binding.etTotalKredit1.setText(String.valueOf(dp.getTotalKreditGaji()));
            binding.etTotalDebit1.setText(String.valueOf(dp.getTotalDebitGaji()));

            binding.etNorekTunjangan.setText(dp.getNoRekeningTunjangan());
            binding.etNamaBankTunjangan.setText(dp.getNamaBankTunjangan());
            binding.etPeriodeAwalWaktu2.setText(AppUtil.parseTanggalGeneral(dp.getPeriodeDateFromTunjangan(), "yyyy-MM-dd", "dd-MM-yyyy"));
            binding.etPeriodeAkhirWaktu2.setText(AppUtil.parseTanggalGeneral(dp.getPeriodeDateToTunjangan(), "yyyy-MM-dd", "dd-MM-yyyy"));
            binding.etTotalDebit2.setText(String.valueOf(dp.getTotalDebitTunjangan()));
            binding.etTotalKredit2.setText(String.valueOf(dp.getTotalKreditTunjangan()));

        } catch (Exception e) {
            AppUtil.logSecure("error setdata", e.getMessage());
        }
        //Set Image Rekening Koran
        try {

            DokumenPendapatanKoranBank.setImg(DPKoran.getImg());

            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
            if (DPKoran.getFileName().substring(DPKoran.getFileName().length() - 3, DPKoran.getFileName().length()).equalsIgnoreCase("pdf")) {
                DokumenPendapatanKoranBank.setFileName("koran.pdf");
                AppUtil.convertBase64ToFileWithOnClick(getContext(), DPKoran.getImg(), binding.ivRekeningKoran1, DPKoran.getFileName());
            } else {
                DokumenPendapatanKoranBank.setFileName("koran.png");
                AppUtil.convertBase64ToImage(DPKoran.getImg(), binding.ivRekeningKoran1);
            }

        } catch (Exception e) {
            AppUtil.logSecure("error setdata", e.getMessage());
        }
        //Set Image Slip Gaji P1
        try {
            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
            DokumenPendapatanSlipGajiP1.setImg(DPSlipGajiP1.getImg());
            DokumenPendapatanSlipTunjanganP1.setImg(DPSlipTunjanganP1.getImg());

            if (DPSlipGajiP1.getFileName().substring(DPSlipGajiP1.getFileName().length() - 3, DPSlipGajiP1.getFileName().length()).equalsIgnoreCase("pdf")) {
                DokumenPendapatanSlipGajiP1.setFileName("slipgaji1.pdf");
                AppUtil.convertBase64ToFileWithOnClick(getContext(), DPSlipGajiP1.getImg(), binding.ivSlipgajiP1, DPSlipGajiP1.getFileName());
            } else {
                DokumenPendapatanSlipGajiP1.setFileName("slipgaji1.png");
                AppUtil.convertBase64ToImage(DPSlipGajiP1.getImg(), binding.ivSlipgajiP1);
            }
            if (DPSlipTunjanganP1.getFileName().substring(DPSlipTunjanganP1.getFileName().length() - 3, DPSlipTunjanganP1.getFileName().length()).equalsIgnoreCase("pdf")) {
                DokumenPendapatanSlipTunjanganP1.setFileName("tunjangan1.pdf");
                AppUtil.convertBase64ToFileWithOnClick(getContext(), DPSlipTunjanganP1.getImg(), binding.ivSliptunjanganP1, DPSlipTunjanganP1.getFileName());
            } else {
                DokumenPendapatanSlipTunjanganP1.setFileName("tunjangan1.png");
                AppUtil.convertBase64ToImage(DPSlipTunjanganP1.getImg(), binding.ivSliptunjanganP1);
            }
        } catch (Exception e) {
            AppUtil.logSecure("error setdata", e.getMessage());
        }
        if (dataPendapatan.get("DokumenPendapatanSlipGajiP2") != null) {
            //Set Image Slip Gaji P2
            try {
                DokumenPendapatanSlipGajiP2.setImg(DPSlipGajiP2.getImg());
                DokumenPendapatanSlipTunjanganP2.setImg(DPSlipTunjanganP2.getImg());
                if (DPSlipGajiP2.getFileName().substring(DPSlipGajiP2.getFileName().length() - 3, DPSlipGajiP2.getFileName().length()).equalsIgnoreCase("pdf")) {
                    DokumenPendapatanSlipGajiP2.setFileName("slipgaji2.png");
                    AppUtil.convertBase64ToFileWithOnClick(getContext(), DPSlipGajiP2.getImg(), binding.ivSlipgajiP2, DPSlipGajiP2.getFileName());
                } else {
                    DokumenPendapatanSlipGajiP2.setFileName("slipgaji2.pdf");
                    AppUtil.convertBase64ToImage(DPSlipGajiP2.getImg(), binding.ivSlipgajiP2);
                }
                if (DPSlipTunjanganP2.getFileName().substring(DPSlipTunjanganP2.getFileName().length() - 3, DPSlipTunjanganP2.getFileName().length()).equalsIgnoreCase("pdf")) {
                    DokumenPendapatanSlipTunjanganP2.setFileName("tunjangan2.pdf");
                    AppUtil.convertBase64ToFileWithOnClick(getContext(), DPSlipTunjanganP2.getImg(), binding.ivSliptunjanganP2, DPSlipTunjanganP2.getFileName());
                } else {
                    DokumenPendapatanSlipTunjanganP2.setFileName("tunjangan2.png");
                    AppUtil.convertBase64ToImage(DPSlipTunjanganP2.getImg(), binding.ivSliptunjanganP2);
                }
            } catch (Exception e) {
                AppUtil.logSecure("error setdata", e.getMessage());
            }
            //Set Image Slip Gaji P3
            try {
                DokumenPendapatanSlipGajiP3.setImg(DPSlipGajiP3.getImg());
                DokumenPendapatanSlipTunjanganP3.setImg(DPSlipGajiP3.getImg());
                if (DPSlipGajiP3.getFileName().substring(DPSlipGajiP3.getFileName().length() - 3, DPSlipGajiP3.getFileName().length()).equalsIgnoreCase("pdf")) {
                    DokumenPendapatanSlipGajiP3.setFileName("tunjangan3.pdf");
                    AppUtil.convertBase64ToFileWithOnClick(getContext(), DPSlipGajiP3.getImg(), binding.ivSlipgajiP3, DPSlipGajiP3.getFileName());
                } else {
                    DokumenPendapatanSlipGajiP3.setFileName("tunjangan3.png");
                    AppUtil.convertBase64ToImage(DPSlipGajiP3.getImg(), binding.ivSlipgajiP3);
                }
                if (DPSlipTunjanganP3.getFileName().substring(DPSlipTunjanganP3.getFileName().length() - 3, DPSlipTunjanganP3.getFileName().length()).equalsIgnoreCase("pdf")) {
                    DokumenPendapatanSlipTunjanganP3.setFileName("tunjangan3.pdf");
                    AppUtil.convertBase64ToFileWithOnClick(getContext(), DPSlipTunjanganP3.getImg(), binding.ivSliptunjanganP3, DPSlipTunjanganP3.getFileName());
                } else {
                    DokumenPendapatanSlipTunjanganP3.setFileName("tunjangan3.png");
                    AppUtil.convertBase64ToImage(DPSlipTunjanganP3.getImg(), binding.ivSliptunjanganP3);
                }
            } catch (Exception e) {
                AppUtil.logSecure("error setdata", e.getMessage());
            }
        }
    }

    private void disabledText(){
        binding.etPendapatanPensiun.setFocusable(false);
        binding.etAkseptasiPendaptan.setFocusable(false);
        binding.etGajiBersihP1.setFocusable(false);
        binding.etTunjanganP1.setFocusable(false);
        binding.etTglTunjanganP1.setFocusable(false);
        binding.etPeriodeGajiP1.setFocusable(false);
        binding.etGajiBersihP2.setFocusable(false);
        binding.etTunjanganP2.setFocusable(false);
        binding.etTglTunjanganP2.setFocusable(false);
        binding.etPeriodeGajiP2.setFocusable(false);
        binding.etGajiBersihP3.setFocusable(false);
        binding.etTunjanganP3.setFocusable(false);
        binding.etTglTunjanganP3.setFocusable(false);
        binding.etPeriodeGajiP3.setFocusable(false);
        binding.etPeriodeAkhirWaktu1.setFocusable(false);
        binding.etPeriodeAwalWaktu1.setFocusable(false);
        binding.etTotalDebit1.setFocusable(false);
        binding.etTotalKredit1.setFocusable(false);
        binding.etPeriodeAkhirWaktu2.setFocusable(false);
        binding.etPeriodeAwalWaktu2.setFocusable(false);
        binding.etTotalDebit2.setFocusable(false);
        binding.etTotalKredit2.setFocusable(false);
        binding.etNorekGaji.setFocusable(false);
        binding.etNorekTunjangan.setFocusable(false);
        binding.etNamaBankGaji.setFocusable(false);
        binding.etNamaBankTunjangan.setFocusable(false);
        binding.etVerfikasiGajiTunjangan.setFocusable(false);
        binding.etVerfikasiRekening.setFocusable(false);
    }
    @Override
    public void onClick(View v) {

    }

    private void numberText() {
        binding.etGajiBersihP1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etGajiBersihP1));
        binding.etGajiBersihP2.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etGajiBersihP2));
        binding.etGajiBersihP3.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etGajiBersihP3));
        binding.etTunjanganP1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTunjanganP1));
        binding.etTunjanganP2.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTunjanganP2));
        binding.etTunjanganP3.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTunjanganP3));
        binding.etTotalDebit1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalDebit1));
        binding.etTotalDebit2.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalDebit2));
        binding.etTotalKredit1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalKredit1));
        binding.etTotalKredit2.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalKredit2));
    }

    private void hideItem(){
        binding.btnRekeningKoran1.setVisibility(View.GONE);
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
