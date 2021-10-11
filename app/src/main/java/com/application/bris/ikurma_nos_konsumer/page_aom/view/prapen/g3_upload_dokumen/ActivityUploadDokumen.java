package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g3_upload_dokumen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

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
        setContentView(view);
        binding.tfNamaDokumen.setVisibility(View.GONE);
        binding.tfKeteranganDokumen.setVisibility(View.GONE);
        binding.btnFotoUploadDokumen.setVisibility(View.GONE);
        binding.ivFotoUploadDokumen.setVisibility(View.GONE);
        binding.rlFotoUploadDokumen.setVisibility(View.GONE);
        binding.btnHapusDokumen.setVisibility(View.GONE);
        onclickSelectDialog();
        onClicks();
        OnBack();
        AppUtil.toolbarRegular(this, "Upload Dokumen");
    }

    private void OnBack() {
                binding.tfNamaDokumen.setVisibility(View.GONE);
                binding.tfKeteranganDokumen.setVisibility(View.GONE);
                binding.btnFotoUploadDokumen.setVisibility(View.GONE);
                binding.ivFotoUploadDokumen.setVisibility(View.GONE);
                binding.rlFotoUploadDokumen.setVisibility(View.GONE);
                binding.btnHapusDokumen.setVisibility(View.GONE);
    }



    private void onClicks() {
        binding.btnTambahDokumen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                binding.tfNamaDokumen.setVisibility(View.VISIBLE);
                binding.tfKeteranganDokumen.setVisibility(View.VISIBLE);
                binding.btnTambahDokumen.setVisibility(View.VISIBLE);
                binding.ivFotoUploadDokumen.setVisibility(View.VISIBLE);
                binding.rlFotoUploadDokumen.setVisibility(View.VISIBLE);
                binding.btnHapusDokumen.setVisibility(View.VISIBLE);
            }
        });
    }
    private void onclickSelectDialog() {
        binding.ivFotoSkPengangkatan.setOnClickListener(this);
        binding.ivFotoSkTerakhir.setOnClickListener(this);
        binding.ivFotoSkPensiun.setOnClickListener(this);
        binding.ivFotoCovernoteSuransi.setOnClickListener(this);
        binding.ivTandaTerimaDokumen.setOnClickListener(this);
        binding.ivFormMutasiKantorBayar.setOnClickListener(this);
        binding.ivSuratKuasaDanPernyataanFlagging.setOnClickListener(this);
        binding.ivSuratPernyataanDanKuasaPembiayaan.setOnClickListener(this);
        binding.ivSupPembiayaanRetailPensiunPrapen.setOnClickListener(this);
        binding.ivPurchaseOrder.setOnClickListener(this);
        binding.ivAkadWakalah.setOnClickListener(this);
        binding.ivAkadMuharabahah.setOnClickListener(this);
        binding.ivJadwalAngsuranMurabahah.setOnClickListener(this);
        binding.ivSuratTandaTerimaBarang.setOnClickListener(this);
        binding.ivPurchaseOrder2.setOnClickListener(this);
        binding.ivAkadWakalah2.setOnClickListener(this);
        binding.ivAkadIjarah.setOnClickListener(this);
        binding.ivJadwalAngsuranUjrah.setOnClickListener(this);
        binding.ivLaporanPenilaianAsetMmq.setOnClickListener(this);
        binding.ivAkadMmq.setOnClickListener(this);
        binding.ivLampiranJadwalPengambilalihan.setOnClickListener(this);
        binding.ivSuratPernyataanDanKuasaAsetMmqSendiri.setOnClickListener(this);
        binding.ivSuratPernyataanDanKuasaAsetMmqPihakKetiga.setOnClickListener(this);
        binding.ivAkadMmq.setOnClickListener(this);
        binding.ivJadwalAngsuranRahn.setOnClickListener(this);
        binding.ivFormMutasiKantorBayar2.setOnClickListener(this);
        binding.ivFotoProsesPenandatanganAkad.setOnClickListener(this);
        binding.ivFotoScreenCaptureBuktiOtentikasiNasabah.setOnClickListener(this);

        binding.btnFotoSkPengangkatan.setOnClickListener(this);
        binding.btnFotoSkTerakhir.setOnClickListener(this);
        binding.btnFotoSkPensiun.setOnClickListener(this);
        binding.btnFotoCovernoteSuransi.setOnClickListener(this);
        binding.btnTandaTerimaDokumen.setOnClickListener(this);
        binding.btnFormMutasiKantorBayar.setOnClickListener(this);
        binding.btnSuratKuasaDanPernyataanFlagging.setOnClickListener(this);
        binding.btnSuratPernyataanDanKuasaPembiayaan.setOnClickListener(this);
        binding.btnSupPembiayaanRetailPensiunPrapen.setOnClickListener(this);
        binding.btnPurchaseOrder.setOnClickListener(this);
        binding.btnAkadWakalah.setOnClickListener(this);
        binding.btnAkadMuharabahah.setOnClickListener(this);
        binding.btnJadwalAngsuranMurabahah.setOnClickListener(this);
        binding.btnSuratTandaTerimaBarang.setOnClickListener(this);
        binding.btnPurchaseOrder2.setOnClickListener(this);
        binding.btnAkadWakalah2.setOnClickListener(this);
        binding.btnAkadIjarah.setOnClickListener(this);
        binding.btnJadwalAngsuranUjrah.setOnClickListener(this);
        binding.btnLaporanPenilaianAsetMmq.setOnClickListener(this);
        binding.btnAkadMmq.setOnClickListener(this);
        binding.btnLampiranJadwalPengambilalihan.setOnClickListener(this);
        binding.btnSuratPernyataanDanKuasaAsetMmqSendiri.setOnClickListener(this);
        binding.btnSuratPernyataanDanKuasaAsetMmqPihakKetiga.setOnClickListener(this);
        binding.btnAkadMmq.setOnClickListener(this);
        binding.btnJadwalAngsuranRahn.setOnClickListener(this);
        binding.btnFormMutasiKantorBayar2.setOnClickListener(this);
        binding.btnFotoProsesPenandatanganAkad.setOnClickListener(this);
        binding.btnFotoScreenCaptureBuktiOtentikasiNasabah.setOnClickListener(this);

        binding.tfNamaDokumen.setOnClickListener(this);
        binding.tfKeteranganDokumen.setOnClickListener(this);
        binding.ivFotoUploadDokumen.setOnClickListener(this);
        binding.btnTambahDokumen.setOnClickListener(this);
        binding.btnHapusDokumen.setOnClickListener(this);

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
            case R.id.btn_tambah_dokumen:
                binding.tfNamaDokumen.setVisibility(View.VISIBLE);
                binding.tfKeteranganDokumen.setVisibility(View.VISIBLE);
                binding.btnTambahDokumen.setVisibility(View.VISIBLE);
                binding.ivFotoUploadDokumen.setVisibility(View.VISIBLE);
                binding.rlFotoUploadDokumen.setVisibility(View.VISIBLE);
                binding.btnHapusDokumen.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_foto_sk_pensiun:
            case R.id.iv_foto_sk_pengangkatan:
            case R.id.iv_foto_sk_terakhir:
            case R.id.iv_foto_covernote_suransi:
            case R.id.iv_tanda_terima_dokumen:
            case R.id.iv_form_mutasi_kantor_bayar:
            case R.id.iv_surat_kuasa_dan_pernyataan_flagging:
            case R.id.iv_surat_pernyataan_dan_kuasa_pembiayaan:
            case R.id.iv_sup_pembiayaan_retail_pensiun_prapen:
            case R.id.iv_purchase_order:
            case R.id.iv_akad_wakalah:
            case R.id.iv_akad_muharabahah:
            case R.id.iv_jadwal_angsuran_murabahah:
            case R.id.iv_surat_tanda_terima_barang:
            case R.id.iv_purchase_order2:
            case R.id.iv_akad_wakalah2:
            case R.id.iv_akad_ijarah:
            case R.id.iv_jadwal_angsuran_ujrah:
            case R.id.iv_laporan_penilaian_aset_mmq:
            case R.id.iv_akad_mmq:
            case R.id.iv_lampiran_jadwal_pengambilalihan:
            case R.id.iv_surat_pernyataan_dan_kuasa_aset_mmq_sendiri:
            case R.id.iv_surat_pernyataan_dan_kuasa_aset_mmq_pihak_ketiga:
            case R.id.iv_akad_rahn:
            case R.id.iv_jadwal_angsuran_rahn:
            case R.id.iv_form_mutasi_kantor_bayar2:
            case R.id.iv_foto_proses_penandatangan_akad:
            case R.id.iv_foto_screen_capture_bukti_otentikasi_nasabah:

            case R.id.btn_foto_sk_pensiun:
            case R.id.btn_foto_sk_pengangkatan:
            case R.id.btn_foto_sk_terakhir:
            case R.id.btn_foto_covernote_suransi:
            case R.id.btn_tanda_terima_dokumen:
            case R.id.btn_form_mutasi_kantor_bayar:
            case R.id.btn_surat_kuasa_dan_pernyataan_flagging:
            case R.id.btn_surat_pernyataan_dan_kuasa_pembiayaan:
            case R.id.btn_sup_pembiayaan_retail_pensiun_prapen:
            case R.id.btn_purchase_order:
            case R.id.btn_akad_wakalah:
            case R.id.btn_akad_muharabahah:
            case R.id.btn_jadwal_angsuran_murabahah:
            case R.id.btn_surat_tanda_terima_barang:
            case R.id.btn_purchase_order2:
            case R.id.btn_akad_wakalah2:
            case R.id.btn_akad_ijarah:
            case R.id.btn_jadwal_angsuran_ujrah:
            case R.id.btn_laporan_penilaian_aset_mmq:
            case R.id.btn_akad_mmq:
            case R.id.btn_lampiran_jadwal_pengambilalihan:
            case R.id.btn_surat_pernyataan_dan_kuasa_aset_mmq_sendiri:
            case R.id.btn_surat_pernyataan_dan_kuasa_aset_mmq_pihak_ketiga:
            case R.id.btn_akad_rahn:
            case R.id.btn_jadwal_angsuran_rahn:
            case R.id.btn_form_mutasi_kantor_bayar2:
            case R.id.btn_foto_proses_penandatangan_akad:
            case R.id.btn_foto_screen_capture_bukti_otentikasi_nasabah:
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
        }



    }
}