package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g3_upload_dokumen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityUploadDokumenBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.io.File;

import cn.pedant.SweetAlert.BuildConfig;

public class ActivityUploadDokumen extends AppCompatActivity implements CameraListener, View.OnClickListener {
    ActivityUploadDokumenBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadDokumenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        binding.cvUploadDokumen2.setVisibility(View.GONE);
        binding.cvUploadDokumen3.setVisibility(View.GONE);
        binding.cvUploadDokumen4.setVisibility(View.GONE);
        binding.cvUploadDokumen5.setVisibility(View.GONE);
        binding.tvUploadDokumen2.setVisibility(View.GONE);
        binding.tvUploadDokumen3.setVisibility(View.GONE);
        binding.tvUploadDokumen4.setVisibility(View.GONE);
        binding.tvUploadDokumen5.setVisibility(View.GONE);
        binding.tfNamaDokumen2.setVisibility(View.GONE);
        binding.tfNamaDokumen3.setVisibility(View.GONE);
        binding.tfNamaDokumen4.setVisibility(View.GONE);
        binding.tfNamaDokumen5.setVisibility(View.GONE);
        binding.tfKeteranganDokumen2.setVisibility(View.GONE);
        binding.tfKeteranganDokumen3.setVisibility(View.GONE);
        binding.tfKeteranganDokumen4.setVisibility(View.GONE);
        binding.tfKeteranganDokumen5.setVisibility(View.GONE);
        binding.tfUploadDokumen2.setVisibility(View.GONE);
        binding.tfUploadDokumen3.setVisibility(View.GONE);
        binding.tfUploadDokumen4.setVisibility(View.GONE);
        binding.tfUploadDokumen5.setVisibility(View.GONE);
        binding.etNamaDokumen2.setVisibility(View.GONE);
        binding.etNamaDokumen3.setVisibility(View.GONE);
        binding.etNamaDokumen4.setVisibility(View.GONE);
        binding.etNamaDokumen5.setVisibility(View.GONE);
        binding.etKeteranganDokumen2.setVisibility(View.GONE);
        binding.etKeteranganDokumen3.setVisibility(View.GONE);
        binding.etKeteranganDokumen4.setVisibility(View.GONE);
        binding.etKeteranganDokumen5.setVisibility(View.GONE);
        binding.ivUploadDokumen2.setVisibility(View.GONE);
        binding.ivUploadDokumen3.setVisibility(View.GONE);
        binding.ivUploadDokumen4.setVisibility(View.GONE);
        binding.ivUploadDokumen5.setVisibility(View.GONE);
        binding.btnUploadDokumen2.setVisibility(View.GONE);
        binding.btnUploadDokumen3.setVisibility(View.GONE);
        binding.btnUploadDokumen4.setVisibility(View.GONE);
        binding.btnUploadDokumen5.setVisibility(View.GONE);
        setContentView(view);
        onclickSelectDialog();
        AppUtil.toolbarRegular(this, "Upload Dokumen");
    }

    private void onclickSelectDialog() {
        binding.dFotoSkPengangkatan.setOnClickListener(this);
        binding.dFotoSkTerakhir.setOnClickListener(this);
        binding.dFotoSkPensiun.setOnClickListener(this);
        binding.dFotoCovernoteAsuransi.setOnClickListener(this);
        binding.dSk.setOnClickListener(this);
        binding.dMutasi.setOnClickListener(this);
        binding.dKuasa.setOnClickListener(this);
        binding.dPernyataan.setOnClickListener(this);
        binding.dSup.setOnClickListener(this);
        binding.dPo.setOnClickListener(this);
        binding.dAkadWakalah.setOnClickListener(this);
        binding.dAkadMurabahah.setOnClickListener(this);
        binding.dJadwalAngsuran.setOnClickListener(this);
        binding.dTerimaBarang.setOnClickListener(this);
        binding.dPoIjarah.setOnClickListener(this);
        binding.dAkadWakalahIjarah.setOnClickListener(this);
        binding.dAkadIjarah.setOnClickListener(this);
        binding.dAngsuranUjrah.setOnClickListener(this);
        binding.dAssetMmq.setOnClickListener(this);
        binding.dAkadMmq.setOnClickListener(this);
        binding.dJadwalPengambilalihan.setOnClickListener(this);
        binding.dSuratPernyataanDanKuasaAsetMmqSendiri.setOnClickListener(this);
        binding.dSuratPernyataanDanKuasaAsetMmqPihakKetiga.setOnClickListener(this);
        binding.dAkadMmq.setOnClickListener(this);
        binding.dAkadRahn.setOnClickListener(this);
        binding.dJadwalAngsuranRahn.setOnClickListener(this);
        binding.dFormMutasiKantorBayar.setOnClickListener(this);
        binding.dFotoProsesPenandatangananAkad.setOnClickListener(this);
        binding.dFotoScreenBuktiOtentikasiNasabahSaatAkad.setOnClickListener(this);
        binding.ivUploadDokumen1.setOnClickListener(this);
        binding.ivUploadDokumen2.setOnClickListener(this);

        binding.btnFotoSkPengangkatan.setOnClickListener(this);
        binding.btnFotoSkTerakhir.setOnClickListener(this);
        binding.btnFotoSkPensiun.setOnClickListener(this);
        binding.btnFotoCovernoteAsuransi.setOnClickListener(this);
        binding.btnSk.setOnClickListener(this);
        binding.btnMutasi.setOnClickListener(this);
        binding.btnFormMutasiKantorBayar.setOnClickListener(this);
        binding.btnKuasa.setOnClickListener(this);
        binding.btnPernyataan.setOnClickListener(this);
        binding.btnSup.setOnClickListener(this);
        binding.btnPo.setOnClickListener(this);
        binding.btnAkadWakalah.setOnClickListener(this);
        binding.btnAkadMurabahah.setOnClickListener(this);
        binding.btnJadwalAngsuran.setOnClickListener(this);
        binding.btnTerimaBarang.setOnClickListener(this);
        binding.btnPoIjarah.setOnClickListener(this);
        binding.btnAkadWakalahIjarah.setOnClickListener(this);
        binding.btnAkadWakalah.setOnClickListener(this);
        binding.btnAkadIjarah.setOnClickListener(this);
        binding.btnAngsuranUjrah.setOnClickListener(this);
        binding.btnAkadRahn.setOnClickListener(this);
        binding.btnAssetMmq.setOnClickListener(this);
        binding.btnAkadMmq.setOnClickListener(this);
        binding.btnJadwalPengambilalihan.setOnClickListener(this);
        binding.btnSuratPernyataanDanKuasaAsetMmqSendiri.setOnClickListener(this);
        binding.btnSuratPernyataanDanKuasaAsetMmqPihakKetiga.setOnClickListener(this);
        binding.btnAkadMmq.setOnClickListener(this);
        binding.btnJadwalAngsuranRahn.setOnClickListener(this);
        binding.btnFormMutasiKantorBayar.setOnClickListener(this);
        binding.btnFotoProsesPenandatangananAkad.setOnClickListener(this);
        binding.btnFotoScreenCaptureBuktiOtentikasiNasabahSaatAkad.setOnClickListener(this);
        binding.etNamaDokumen1.setOnClickListener(this);
        binding.etNamaDokumen2.setOnClickListener(this);
        binding.etKeteranganDokumen1.setOnClickListener(this);
        binding.etKeteranganDokumen2.setOnClickListener(this);
        binding.btnUploadDokumen1.setOnClickListener(this);
        binding.btnUploadDokumen2.setOnClickListener(this);
        binding.btnUploadDokumen3.setOnClickListener(this);
        binding.btnUploadDokumen4.setOnClickListener(this);
        binding.btnUploadDokumen5.setOnClickListener(this);

        binding.etNamaDokumen1.setOnClickListener(this);
        binding.etNamaDokumen2.setOnClickListener(this);
        binding.etNamaDokumen3.setOnClickListener(this);
        binding.etNamaDokumen4.setOnClickListener(this);
        binding.etNamaDokumen5.setOnClickListener(this);
        binding.etKeteranganDokumen1.setOnClickListener(this);
        binding.etKeteranganDokumen2.setOnClickListener(this);
        binding.etKeteranganDokumen3.setOnClickListener(this);
        binding.etKeteranganDokumen4.setOnClickListener(this);
        binding.etKeteranganDokumen5.setOnClickListener(this);

        binding.llTambahanDokumen.setOnClickListener(this);
        binding.btnTambahanDokumen.setOnClickListener(this);
        binding.btnSend.setOnClickListener(this);
        binding.llBtnSend.setOnClickListener(this);
    }

    public void onSelectMenuCamera(String idMenu) {
        switch (idMenu) {
            case "Take Photo":
                openCamera(TAKE_PICTURE_KANTOR1);
                break;
            case "Pick Photo":
                openGalery(PICK_PICTURE_KANTOR1);
                break;
            case "Pick File":
                openFile(PICK_PICTURE_KANTOR1);
                break;
        }

    }


    private final int TAKE_PICTURE_KANTOR1 = 11;
    private final int PICK_PICTURE_KANTOR1 = 22;

    private void openCamera(int cameraCode) {
        checkCameraPermission(cameraCode);
    }

    public void openGalery(int cameraCode) {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(Intent.createChooser(it, "Select File"), cameraCode);
    }

    public void openFile(int cameraCode) {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("application/pdf");
        startActivityForResult(Intent.createChooser(it, "Select File"), cameraCode);
    }

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static int CAMERA_CODE_FORE_PERMISSION = 0;

    public void checkCameraPermission(int cameraCode) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);
        } else {
            Uri outputFileUri = getCaptureImageOutputUri();
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(captureIntent, cameraCode);
        }
    }

    public void directOpenCamera(int cameraCode) {
        Uri outputFileUri = getCaptureImageOutputUri();
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(captureIntent, cameraCode);
    }

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = this.getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                BuildConfig BuildConfig = null;
                outputFileUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "Upload Dokumen.png"));
            } else {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "Upload Dokumen.png"));
            }
        }
        return outputFileUri;
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.d_foto_sk_pensiun:
            case R.id.d_foto_sk_pengangkatan:
            case R.id.d_foto_sk_terakhir:
            case R.id.d_foto_covernote_asuransi:
            case R.id.d_sk:
            case R.id.d_form_mutasi_kantor_bayar:
            case R.id.d_kuasa:
            case R.id.d_pernyataan:
            case R.id.d_sup:
            case R.id.d_po:
            case R.id.d_akad_wakalah:
            case R.id.d_akad_murabahah:
            case R.id.d_jadwal_angsuran:
            case R.id.d_terima_barang:
            case R.id.d_po_Ijarah:
            case R.id.d_akad_wakalah_Ijarah:
            case R.id.d_akad_ijarah:
            case R.id.d_angsuran_ujrah:
            case R.id.d_asset_mmq:
            case R.id.d_akad_mmq:
            case R.id.d_jadwal_pengambilalihan:
            case R.id.d_surat_pernyataan_dan_kuasa_aset_mmq_sendiri:
            case R.id.d_surat_pernyataan_dan_kuasa_aset_mmq_pihak_ketiga:
            case R.id.d_akad_rahn:
            case R.id.d_jadwal_angsuran_rahn:
            case R.id.d_mutasi:
            case R.id.d_foto_proses_penandatanganan_akad:
            case R.id.d_foto_screen_bukti_otentikasi_nasabah_saat_akad:
            case R.id.iv_upload_dokumen1:
            case R.id.iv_upload_dokumen2:
            case R.id.iv_upload_dokumen3:
            case R.id.iv_upload_dokumen4:
            case R.id.iv_upload_dokumen5:

            case R.id.btn_foto_sk_pensiun:
            case R.id.btn_foto_sk_pengangkatan:
            case R.id.btn_foto_sk_terakhir:
            case R.id.btn_foto_covernote_asuransi:
            case R.id.btn_sk:
            case R.id.btn_form_mutasi_kantor_bayar:
            case R.id.btn_kuasa:
            case R.id.btn_pernyataan:
            case R.id.btn_sup:
            case R.id.btn_po:
            case R.id.btn_akad_wakalah:
            case R.id.btn_akad_murabahah:
            case R.id.btn_jadwal_angsuran:
            case R.id.btn_terima_barang:
            case R.id.btn_po_Ijarah:
            case R.id.btn_akad_wakalah_Ijarah:
            case R.id.btn_akad_ijarah:
            case R.id.btn_angsuran_ujrah:
            case R.id.btn_asset_mmq:
            case R.id.btn_akad_mmq:
            case R.id.btn_jadwal_pengambilalihan:
            case R.id.btn_surat_pernyataan_dan_kuasa_aset_mmq_sendiri:
            case R.id.btn_surat_pernyataan_dan_kuasa_aset_mmq_pihak_ketiga:
            case R.id.btn_akad_rahn:
            case R.id.btn_jadwal_angsuran_rahn:
            case R.id.btn_mutasi:
            case R.id.btn_foto_proses_penandatanganan_akad:
            case R.id.btn_foto_screen_capture_bukti_otentikasi_nasabah_saat_akad:
            case R.id.btn_upload_dokumen1:
            case R.id.btn_upload_dokumen2:
            case R.id.btn_upload_dokumen3:
            case R.id.btn_upload_dokumen4:
            case R.id.btn_upload_dokumen5:
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.ll_tambahan_dokumen:
            case R.id.btn_tambahan_dokumen:
                Toast.makeText(getApplicationContext(), "Tambahan Dokumen Lainya",Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_cek_lngp:
            case R.id.ll_btn_send:
            case R.id.btn_send:
                break;
        }

        binding.btnTambahanDokumen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                binding.cvUploadDokumen2.setVisibility(View.VISIBLE);
                binding.cvUploadDokumen3.setVisibility(View.VISIBLE);
                binding.cvUploadDokumen4.setVisibility(View.VISIBLE);
                binding.cvUploadDokumen5.setVisibility(View.VISIBLE);
                binding.tvUploadDokumen2.setVisibility(View.VISIBLE);
                binding.tvUploadDokumen3.setVisibility(View.VISIBLE);
                binding.tvUploadDokumen4.setVisibility(View.VISIBLE);
                binding.tvUploadDokumen5.setVisibility(View.VISIBLE);
                binding.tfNamaDokumen2.setVisibility(View.VISIBLE);
                binding.tfNamaDokumen3.setVisibility(View.VISIBLE);
                binding.tfNamaDokumen4.setVisibility(View.VISIBLE);
                binding.tfNamaDokumen5.setVisibility(View.VISIBLE);
                binding.tfKeteranganDokumen2.setVisibility(View.VISIBLE);
                binding.tfKeteranganDokumen3.setVisibility(View.VISIBLE);
                binding.tfKeteranganDokumen4.setVisibility(View.VISIBLE);
                binding.tfKeteranganDokumen5.setVisibility(View.VISIBLE);
                binding.tfUploadDokumen2.setVisibility(View.VISIBLE);
                binding.tfUploadDokumen3.setVisibility(View.VISIBLE);
                binding.tfUploadDokumen4.setVisibility(View.VISIBLE);
                binding.tfUploadDokumen5.setVisibility(View.VISIBLE);
                binding.etNamaDokumen2.setVisibility(View.VISIBLE);
                binding.etNamaDokumen3.setVisibility(View.VISIBLE);
                binding.etNamaDokumen4.setVisibility(View.VISIBLE);
                binding.etNamaDokumen5.setVisibility(View.VISIBLE);
                binding.etKeteranganDokumen2.setVisibility(View.VISIBLE);
                binding.etKeteranganDokumen3.setVisibility(View.VISIBLE);
                binding.etKeteranganDokumen4.setVisibility(View.VISIBLE);
                binding.etKeteranganDokumen5.setVisibility(View.VISIBLE);
                binding.ivUploadDokumen2.setVisibility(View.VISIBLE);
                binding.ivUploadDokumen3.setVisibility(View.VISIBLE);
                binding.ivUploadDokumen4.setVisibility(View.VISIBLE);
                binding.ivUploadDokumen5.setVisibility(View.VISIBLE);
                binding.btnUploadDokumen2.setVisibility(View.VISIBLE);
                binding.btnUploadDokumen3.setVisibility(View.VISIBLE);
                binding.btnUploadDokumen4.setVisibility(View.VISIBLE);
                binding.btnUploadDokumen5.setVisibility(View.VISIBLE);
            }
        });

    }
}