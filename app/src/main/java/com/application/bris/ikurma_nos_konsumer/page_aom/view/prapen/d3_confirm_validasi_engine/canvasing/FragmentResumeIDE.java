package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.canvasing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.application.bris.ikurma_nos_konsumer.databinding.FragmentResumeIdeBinding;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.JsonObject;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.List;

public class FragmentResumeIDE extends Fragment implements Step, SwipeRefreshLayout.OnRefreshListener {
    FragmentResumeIdeBinding binding;
    private JsonObject lrac;

    public FragmentResumeIDE(JsonObject lRAC) {
        lrac = lRAC;

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentResumeIdeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initialize();
        return view;
    }

    private void initialize() {
        AppUtil.disableEditTexts(binding.getRoot());
        JsonObject dataPembiayaanCanvasing = lrac.get("DataPembiayaanCanvasing").getAsJsonObject();
        JsonObject dataNasabahCanvasing = lrac.get("DataNasabahCanvasing").getAsJsonObject();
        JsonObject dataAlamatCanvasing = lrac.get("DataAlamatCanvasing").getAsJsonObject();
        JsonObject dataMarketingCanvasing = lrac.get("DataMarketingCanvasing").getAsJsonObject();
        if (dataPembiayaanCanvasing != null) {
            binding.etJenisProduk.setText(dataPembiayaanCanvasing.get("JenisProduk").getAsString());
            binding.etSegmen.setText(dataPembiayaanCanvasing.get("Segmen").getAsString());
            binding.etJenisPembiayaan.setText(dataPembiayaanCanvasing.get("JenisPembiayaan").getAsString());
            binding.etProgram.setText(dataPembiayaanCanvasing.get("Program").getAsString());
            binding.etAkad.setText(dataPembiayaanCanvasing.get("Akad").getAsString());
        }

        if (dataNasabahCanvasing != null) {
            binding.etNoKtp.setText(dataNasabahCanvasing.get("NoKTP").getAsString());
            binding.etNpwp.setText(dataNasabahCanvasing.get("NPWP").getAsString());
            binding.etNamaLengkap.setText(dataNasabahCanvasing.get("NamaLengkap").getAsString());
            binding.etNamaLengkapSesuaiKtp.setText(dataNasabahCanvasing.get("NamaLengkapSesuaiKTP").getAsString());
            binding.etJenisKelamin.setText(dataNasabahCanvasing.get("JenisKelamin").getAsString());
            binding.etTempatLahir.setText(dataNasabahCanvasing.get("TempatLahir").getAsString());
            binding.etTanggalLahir.setText(AppUtil.parseTanggalGeneral(dataNasabahCanvasing.get("TanggalLahir").getAsString(), "yyyy-MM-dd", "dd-MM-yyyy"));
            binding.etNamaIbuKandung.setText(dataNasabahCanvasing.get("NamaIbuKandung").getAsString());
            binding.etNomorHp.setText(dataNasabahCanvasing.get("NomorHP").getAsString());
            binding.etNomorTelpRumah.setText(dataNasabahCanvasing.get("NomorTelpRumah").getAsString());
            binding.etEmail.setText(dataNasabahCanvasing.get("Email").getAsString());
            binding.etAgama.setText(dataNasabahCanvasing.get("Agama").getAsString());
            binding.etTanggalTerbitKtp.setText(AppUtil.parseTanggalGeneral(dataNasabahCanvasing.get("TanggalTerbitKTP").getAsString(), "yyyy-MM-dd", "dd-MM-yyyy"));
            binding.etStatusPernikahanTerbaru.setText(dataNasabahCanvasing.get("StatusPernikahanTerbaru").getAsString());
            binding.etStatusPernikahanKtp.setText(dataNasabahCanvasing.get("StatusPernikahanKTP").getAsString());
            binding.etNoKtpPasangan.setText(dataNasabahCanvasing.get("NoKTPPasangan").getAsString());
            binding.etNamaLengkapPasangan.setText(dataNasabahCanvasing.get("NamaLengkapPasangan").getAsString());
            binding.etTanggalLahirPasangan.setText(AppUtil.parseTanggalGeneral(dataNasabahCanvasing.get("TanggalLahirPasangan").getAsString(), "yyyy-MM-dd", "dd-MM-yyyy"));
        }
        if (dataAlamatCanvasing != null) {
            binding.etAlamat.setText(dataAlamatCanvasing.get("Alamat").getAsString());
            binding.etRt.setText(dataAlamatCanvasing.get("RT").getAsString());
            binding.etRw.setText(dataAlamatCanvasing.get("RW").getAsString());
            binding.etKodePos.setText(dataAlamatCanvasing.get("KodePos").getAsString());
            binding.etProvinsi.setText(dataAlamatCanvasing.get("Provinsi").getAsString());
            binding.etKabupaten.setText(dataAlamatCanvasing.get("Kabupaten").getAsString());
            binding.etKecamatan.setText(dataAlamatCanvasing.get("Kecamatan").getAsString());
            binding.etDesaKelurahan.setText(dataAlamatCanvasing.get("DesaorKelurahan").getAsString());
        }
        if (dataMarketingCanvasing != null) {
            binding.etSumberAplikasi.setText(dataMarketingCanvasing.get("SumberAplikasi").getAsString());
            binding.etNamaAo.setText(dataMarketingCanvasing.get("NamaAO").getAsString());
            binding.etKodeCabangPembukuan.setText(dataMarketingCanvasing.get("KodeCabangPembukuan").getAsString());
            binding.etNamaCabangPembukuan.setText(dataMarketingCanvasing.get("NamaCabangPembukuan").getAsString());
        }
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

    @Override
    public void onRefresh() {
    }
}
