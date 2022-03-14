package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g3_upload_dokumen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseFile;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseLogicalDoc;
import com.application.bris.ikurma_nos_konsumer.api.model.request.foto.ReqUploadFile;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDocument;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDocumentUmum;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqInquery;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateUploadDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UploadDokumen;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.UploadDokumenActivityBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.jaminan.DataJaminanActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.service_encrypt.MagicCryptHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityUploadDokumen extends AppCompatActivity implements CameraListener, View.OnClickListener, ConfirmListener {
    UploadDokumenActivityBinding binding;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private long idAplikasi;
    private String produk, tipeFile, fileload;

    Integer addImg = 0;
    Bitmap dokumenA, dokumenB, dokumenC, dokumenD, dokumenE;
    Uri UridokumenA, UridokumenB, UridokumenC, UridokumenD, UridokumenE;
    final int IntDokumenA = 1, IntDokumenB = 2, IntDokumenC = 3, IntDokumenD = 4, IntDokumenE = 5;
    String clicker, valDokumenA, valDokumenB, valDokumenC, valDokumenD, valDokumenE;
    String idDokumenA = "", idDokumenB = "", idDokumenC = "", idDokumenD = "", idDokumenE = "";
    String nameDokumenA = "", nameDokumenB = "", nameDokumenC = "", nameDokumenD = "", nameDokumenE = "";

    Bitmap skpensiun, skpengangkatan, skterakhir, covernoteasuransi,
            sk, mutasi, kuasa, pernyataan, sup,
            ttdakad, po, wakalah, murabahah, jadwalangsuran, terimabarang,
            poijarah, wakalahijarah, ijarah, angsuranujrah,
            assetmmq, akadmmq, jadwalpengambilan, pihak1, pihak3,
            akadrahn, angsuranrahn,
            kantorbayar, fotoakad;

    Uri Uriskpensiun, Uriskpengangkatan, Uriskterakhir, Uricovernoteasuransi,
            Urisk, Urimutasi, Urikuasa, Uripernyataan, Urisup,
            Urittdakad, Uripo, Uriwakalah, Urimurabahah, Urijadwalangsuran, Uriterimabarang,
            Uripoijarah, Uriwakalahijarah, Uriijarah, Uriangsuranujrah,
            Uriassetmmq, Uriakadmmq, Urijadwalpengambilan, Uripihak1, Uripihak3,
            Uriakadrahn, Uriangsuranrahn,
            Urikantorbayar, Urifotoakad;

    final int Intskpensiun = 6, Intskpengangkatan = 7, Intskterakhir = 8, Intcovernoteasuransi = 9,
            Intsk = 10, Intmutasi = 11, Intkuasa = 12, Intpernyataan = 13, Intsup = 14,
            Intttdakad = 15, Intpo = 16, Intwakalah = 17, Intmurabahah = 18, Intjadwalangsuran = 19, Intterimabarang = 20,
            Intpoijarah = 21, Intwakalahijarah = 22, Intijarah = 23, Intangsuranujrah = 24,
            Intassetmmq = 25, Intakadmmq = 26, Intjadwalpengambilan = 27, Intpihak1 = 28, Intpihak3 = 29,
            Intakadrahn = 30, Intangsuranrahn = 31,
            Intkantorbayar = 32, Intfotoakad = 33;

    String valskpensiun, valskpengangkatan, valskterakhir, valcovernoteasuransi,
            valsk, valmutasi, valkuasa, valpernyataan, valsup,
            valttdakad, valpo, valwakalah, valmurabahah, valjadwalangsuran, valterimabarang,
            valpoijarah, valwakalahijarah, valijarah, valangsuranujrah,
            valassetmmq, valakadmmq, valjadwalpengambilan, valpihak1, valpihak3,
            valakadrahn, valangsuranrahn,
            valkantorbayar, valfotoakad;
    String idskpensiun = "", idskpengangkatan = "", idskterakhir = "", idcovernoteasuransi = "",
            idsk = "", idmutasi = "", idkuasa = "", idpernyataan = "", idsup = "",
            idttdakad = "", idpo = "", idwakalah = "", idmurabahah, idjadwalangsuran, idterimabarang,
            idpoijarah = "", idwakalahijarah = "", idijarah = "", idangsuranujrah = "",
            idassetmmq = "", idakadmmq = "", idjadwalpengambilan = "", idpihak1 = "", idpihak3 = "",
            idakadrahn = "", idangsuranrahn = "",
            idkantorbayar = "", idfotoakad = "";

    String FNskpensiun, FNskpengangkatan, FNskterakhir, FNcovernoteasuransi,
            FNsk, FNmutasi, FNkuasa, FNpernyataan, FNsup,
            FNttdakad, FNpo, FNwakalah, FNmurabahah, FNjadwalangsuran, FNterimabarang,
            FNpoijarah, FNwakalahijarah, FNijarah, FNangsuranujrah,
            FNassetmmq, FNakadmmq, FNjadwalpengambilan, FNpihak1, FNpihak3,
            FNakadrahn, FNangsuranrahn,
            FNkantorbayar, FNfotoakad;

    ReqDocument Form_Mutasi_Kantor_Bayar = new ReqDocument(), Form_Mutasi_Kantor_Bayar_Taspen = new ReqDocument(), Foto_Bukti_Otentikasi_Nasabah = new ReqDocument(),
            Foto_Covernote_Asuransi = new ReqDocument(), Foto_Proses_Penandatanganan_Akad = new ReqDocument(), Foto_SK_Pengangkatan = new ReqDocument(),
            Foto_SK_Pensiun = new ReqDocument(), Foto_SK_Terakhir = new ReqDocument(), Ijarah_Akad_Ijarah = new ReqDocument(),
            Ijarah_Akad_Wakalah = new ReqDocument(), Ijarah_Lampiran_Jadwal_Angsuran = new ReqDocument(), Ijarah_Purchase_Order = new ReqDocument(),
            MMQ_Akad_MMQ = new ReqDocument(), MMQ_Lampiran_Jadwal_Pengambil_Alihan = new ReqDocument(), MMQ_Laporan_Penilaian_Aset = new ReqDocument(),
            MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga = new ReqDocument(), MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri = new ReqDocument(), Murabahah_Akad_Murabahah = new ReqDocument(),
            Murabahah_Akad_Wakalah = new ReqDocument(), Murabahah_Lampiran_Jadwal_Angsuran = new ReqDocument(), Murabahah_Purchase_Order = new ReqDocument(),
            Murabahah_Surat_Tanda_Terima_Barang = new ReqDocument(), Rahn_Akad_Rahn = new ReqDocument(), Rahn_Lampiran_Jadwal_Angsuran = new ReqDocument(),
            SUP = new ReqDocument(), Surat_Kuasa_Pernyataan_Flagging = new ReqDocument(), Surat_Pernyataan_Kuasa_Pembiayaan = new ReqDocument(),
            Tanda_Terima_Dokumen_SK = new ReqDocument();

    List<ReqDocumentUmum> DokumenUmum = new ArrayList<ReqDocumentUmum>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UploadDokumenActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idAplikasi = Long.parseLong(getIntent().getStringExtra("idAplikasi"));
        if (getIntent().hasExtra("produk")) {
            produk = getIntent().getStringExtra("produk");
        } else {
            produk = "";
        }
        hideUploadDokumen();
        hidePreviewButton();
        setContentView(view);
        onclickSelectDialog();
        showUploaded();
        initdata();
        AppUtil.toolbarRegular(this, "Upload Dokumen");

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.DialogBackpress(ActivityUploadDokumen.this);
            }
        });
    }

    private void showUploaded() {
        if (Objects.requireNonNull(getIntent().getStringExtra("akad")).equalsIgnoreCase("mmq")) {
            binding.akadmmq.setVisibility(View.VISIBLE);
            binding.akadijarah.setVisibility(View.GONE);
            binding.akadmurabahah.setVisibility(View.GONE);
            binding.akadrahn.setVisibility(View.GONE);
        } else if (Objects.requireNonNull(getIntent().getStringExtra("akad")).equalsIgnoreCase("ijarah")) {
            binding.akadmmq.setVisibility(View.GONE);
            binding.akadijarah.setVisibility(View.VISIBLE);
            binding.akadmurabahah.setVisibility(View.GONE);
            binding.akadrahn.setVisibility(View.GONE);
        } else if (Objects.requireNonNull(getIntent().getStringExtra("akad")).equalsIgnoreCase("murabahah")) {
            binding.akadmmq.setVisibility(View.GONE);
            binding.akadijarah.setVisibility(View.GONE);
            binding.akadmurabahah.setVisibility(View.VISIBLE);
            binding.akadrahn.setVisibility(View.GONE);
        } else if (Objects.requireNonNull(getIntent().getStringExtra("akad")).equalsIgnoreCase("rahn")) {
            binding.akadmmq.setVisibility(View.GONE);
            binding.akadijarah.setVisibility(View.GONE);
            binding.akadmurabahah.setVisibility(View.GONE);
            binding.akadrahn.setVisibility(View.VISIBLE);
        }
    }

    // Legacy
//    private void initdata() {
//        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
//        ReqInquery req = new ReqInquery();
//        req.setUID(String.valueOf(appPreferences.getUid()));
//        req.setApplicationId(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
//        Call<ParseResponse> call = apiClientAdapter.getApiInterface().InqDokumenUpload(req);
//        call.enqueue(new Callback<ParseResponse>() {
//            @Override
//            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
//                if (response.isSuccessful()) {
//                    binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
//
//                    if (response.body().getStatus().equalsIgnoreCase("00") && response.body().getData() != null) {
//                        Gson gson = new Gson();
//
//                        if (response.body().getData().get("AsuransiKhusus") != null) {
//                            if (response.body().getData().get("AsuransiKhusus").getAsString().equalsIgnoreCase("FALSE")) {
//                                binding.llcovernote.setVisibility(View.GONE);
//                            } else {
//                                binding.llcovernote.setVisibility(View.VISIBLE);
//                            }
//                        }
//
//                        if (response.body().getData().get("TipeProduk") != null) {
//                            if (response.body().getData().get("TipeProduk").getAsString().equalsIgnoreCase("Pra Pensiun 2 Tahun")) {
//                                binding.llSkPensiun.setVisibility(View.VISIBLE);
//                            } else {
//                                binding.llSkPensiun.setVisibility(View.GONE);
//                            }
//                        }
//
//                        if (response.body().getData().get("Form_Mutas_Kantor_Bayar") != null) {
//                            String SSFormMutasiKantorBayar = response.body().getData().get("Form_Mutas_Kantor_Bayar").getAsJsonObject().toString();
//                            Form_Mutasi_Kantor_Bayar = gson.fromJson(SSFormMutasiKantorBayar, ReqDocument.class);
//                            valkantorbayar = Form_Mutasi_Kantor_Bayar.getImg();
//                            try {
//                                if (Form_Mutasi_Kantor_Bayar.getFileName().substring(Form_Mutasi_Kantor_Bayar.getFileName().length() - 3, Form_Mutasi_Kantor_Bayar.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNkantorbayar = "kantorbayar.pdf";
//                                } else {
//                                    kantorbayar = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valkantorbayar), 0, AppUtil.decodeImageTobase64(valkantorbayar).length);
//                                    FNkantorbayar = "kantorbayar.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvFormMutasiKantorBayar.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Form_Mutasi_Kantor_Bayar_Taspen") != null) {
//                            String SSFormBayarTaspen = response.body().getData().get("Form_Mutasi_Kantor_Bayar_Taspen").getAsJsonObject().toString();
//                            Form_Mutasi_Kantor_Bayar_Taspen = gson.fromJson(SSFormBayarTaspen, ReqDocument.class);
//                            valmutasi = Form_Mutasi_Kantor_Bayar_Taspen.getImg();
//                            try {
//                                if (Form_Mutasi_Kantor_Bayar_Taspen.getFileName().substring(Form_Mutasi_Kantor_Bayar_Taspen.getFileName().length() - 3, Form_Mutasi_Kantor_Bayar_Taspen.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNmutasi = "mutasi.pdf";
//                                } else {
//                                    mutasi = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valmutasi), 0, AppUtil.decodeImageTobase64(valmutasi).length);
//                                    FNmutasi = "mutasi.png";
//                                }
//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvMutasi.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Foto_Bukti_Otentikasi_Nasabah") != null) {
//                            String SSOtentikasiNasabah = response.body().getData().get("Foto_Bukti_Otentikasi_Nasabah").getAsJsonObject().toString();
//                            Foto_Bukti_Otentikasi_Nasabah = gson.fromJson(SSOtentikasiNasabah, ReqDocument.class);
//                            valfotoakad = Foto_Bukti_Otentikasi_Nasabah.getImg();
//                            try {
//                                if (Foto_Bukti_Otentikasi_Nasabah.getFileName().substring(Foto_Bukti_Otentikasi_Nasabah.getFileName().length() - 3, Foto_Bukti_Otentikasi_Nasabah.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNfotoakad = "fotoakad.pdf";
//                                } else {
//                                    fotoakad = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valfotoakad), 0, AppUtil.decodeImageTobase64(valfotoakad).length);
//                                    FNfotoakad = "fotoakad.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvFotoScreenCaptureBuktiOtentikasiNasabahSaatAkad.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Foto_Covernote_Asuransi") != null) {
//                            String SSCovernoteAsuransi = response.body().getData().get("Foto_Covernote_Asuransi").getAsJsonObject().toString();
//                            Foto_Covernote_Asuransi = gson.fromJson(SSCovernoteAsuransi, ReqDocument.class);
//                            valcovernoteasuransi = Foto_Covernote_Asuransi.getImg();
//                            try {
//                                if (Foto_Covernote_Asuransi.getFileName().substring(Foto_Covernote_Asuransi.getFileName().length() - 3, Foto_Covernote_Asuransi.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNcovernoteasuransi = "covernoteasuransi.pdf";
//                                } else {
//                                    covernoteasuransi = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valcovernoteasuransi), 0, AppUtil.decodeImageTobase64(valcovernoteasuransi).length);
//                                    FNcovernoteasuransi = "covernoteasuransi.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvFotoCovernoteAsuransi.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Foto_Proses_Penandatanganan_Akad") != null) {
//                            String SSProsesAkad = response.body().getData().get("Foto_Proses_Penandatanganan_Akad").getAsJsonObject().toString();
//                            Foto_Proses_Penandatanganan_Akad = gson.fromJson(SSProsesAkad, ReqDocument.class);
//                            valttdakad = Foto_Proses_Penandatanganan_Akad.getImg();
//                            try {
//                                if (Foto_Proses_Penandatanganan_Akad.getFileName().substring(Foto_Proses_Penandatanganan_Akad.getFileName().length() - 3, Foto_Proses_Penandatanganan_Akad.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNttdakad = "ttdakad.pdf";
//                                } else {
//                                    ttdakad = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valttdakad), 0, AppUtil.decodeImageTobase64(valttdakad).length);
//                                    FNttdakad = "ttdakad.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvFotoProsesPenandatangananAkad.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Foto_SK_Pengangkatan") != null) {
//                            String SSSkPengangkatan = response.body().getData().get("Foto_SK_Pengangkatan").getAsJsonObject().toString();
//                            Foto_SK_Pengangkatan = gson.fromJson(SSSkPengangkatan, ReqDocument.class);
//                            valskpengangkatan = Foto_SK_Pengangkatan.getImg();
//                            try {
//                                if (Foto_SK_Pengangkatan.getFileName().substring(Foto_SK_Pengangkatan.getFileName().length() - 3, Foto_SK_Pengangkatan.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNskpengangkatan = "skpenangkatan.pdf";
//                                } else {
//                                    skpengangkatan = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valskpengangkatan), 0, AppUtil.decodeImageTobase64(valskpengangkatan).length);
//                                    FNskpengangkatan = "skpenangkatan.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvFotoSkPengangkatan.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Foto_SK_Pensiun") != null) {
//                            String SSSkPensiun = response.body().getData().get("Foto_SK_Pensiun").getAsJsonObject().toString();
//                            Foto_SK_Pensiun = gson.fromJson(SSSkPensiun, ReqDocument.class);
//                            valskpensiun = Foto_SK_Pensiun.getImg();
//                            try {
//                                valskpensiun = Foto_SK_Pensiun.getImg();
//                                if (Foto_SK_Pensiun.getFileName().substring(Foto_SK_Pensiun.getFileName().length() - 3, Foto_SK_Pensiun.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNskpensiun = "skpensiun.pdf";
//                                } else {
//                                    skpensiun = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valskpensiun), 0, AppUtil.decodeImageTobase64(valskpensiun).length);
//                                    FNskpensiun = "skpensiun.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvFotoSkPensiun.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Foto_SK_Terakhir") != null) {
//                            String SSSTerakhir = response.body().getData().get("Foto_SK_Terakhir").getAsJsonObject().toString();
//                            Foto_SK_Terakhir = gson.fromJson(SSSTerakhir, ReqDocument.class);
//                            valskterakhir = Foto_SK_Terakhir.getImg();
//                            try {
//                                if (Foto_SK_Terakhir.getFileName().substring(Foto_SK_Terakhir.getFileName().length() - 3, Foto_SK_Terakhir.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNskterakhir = "skterakhir.pdf";
//                                } else {
//                                    skterakhir = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valskterakhir), 0, AppUtil.decodeImageTobase64(valskterakhir).length);
//                                    FNskterakhir = "skterakhir.png";
//                                }
//                                binding.pvFotoSkTerakhir.setVisibility(View.VISIBLE);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//
//                        if (response.body().getData().get("Ijarah_Akad_Ijarah") != null) {
//                            String SSIjarahAkad = response.body().getData().get("Ijarah_Akad_Ijarah").getAsJsonObject().toString();
//                            Ijarah_Akad_Ijarah = gson.fromJson(SSIjarahAkad, ReqDocument.class);
//                            valijarah = Ijarah_Akad_Ijarah.getImg();
//                            try {
//                                if (Ijarah_Akad_Ijarah.getFileName().substring(Ijarah_Akad_Ijarah.getFileName().length() - 3, Ijarah_Akad_Ijarah.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNijarah = "ijarah.pdf";
//                                } else {
//                                    ijarah = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valijarah), 0, AppUtil.decodeImageTobase64(valijarah).length);
//                                    FNijarah = "ijarah.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvAkadIjarah.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Ijarah_Akad_Wakalah") != null) {
//                            String SSIjarahAkadWakalah = response.body().getData().get("Ijarah_Akad_Wakalah").getAsJsonObject().toString();
//                            Ijarah_Akad_Wakalah = gson.fromJson(SSIjarahAkadWakalah, ReqDocument.class);
//                            valwakalahijarah = Ijarah_Akad_Wakalah.getImg();
//                            try {
//                                if (Ijarah_Akad_Wakalah.getFileName().substring(Ijarah_Akad_Wakalah.getFileName().length() - 3, Ijarah_Akad_Wakalah.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNwakalahijarah = "wakalahijarah.pdf";
//                                } else {
//                                    wakalahijarah = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valwakalahijarah), 0, AppUtil.decodeImageTobase64(valwakalahijarah).length);
//                                    FNwakalahijarah = "wakalahijarah.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvAkadWakalahIjarah.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Ijarah_Lampiran_Jadwal_Angsuran") != null) {
//                            String SSIjarahLampiranJadwal = response.body().getData().get("Ijarah_Lampiran_Jadwal_Angsuran").getAsJsonObject().toString();
//                            Ijarah_Lampiran_Jadwal_Angsuran = gson.fromJson(SSIjarahLampiranJadwal, ReqDocument.class);
//                            valangsuranujrah = Ijarah_Lampiran_Jadwal_Angsuran.getImg();
//                            try {
//                                if (Ijarah_Lampiran_Jadwal_Angsuran.getFileName().substring(Ijarah_Lampiran_Jadwal_Angsuran.getFileName().length() - 3, Ijarah_Lampiran_Jadwal_Angsuran.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNangsuranujrah = "angsuranujrah.pdf";
//                                } else {
//                                    angsuranujrah = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valangsuranujrah), 0, AppUtil.decodeImageTobase64(valangsuranujrah).length);
//                                    FNangsuranujrah = "angsuranujrah.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvAngsuranUjrah.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Ijarah_Purchase_Order") != null) {
//                            String SSIjarahPurchaseOrder = response.body().getData().get("Ijarah_Purchase_Order").getAsJsonObject().toString();
//                            Ijarah_Purchase_Order = gson.fromJson(SSIjarahPurchaseOrder, ReqDocument.class);
//                            valpoijarah = Ijarah_Purchase_Order.getImg();
//                            try {
//                                if (Ijarah_Purchase_Order.getFileName().substring(Ijarah_Purchase_Order.getFileName().length() - 3, Ijarah_Purchase_Order.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNpoijarah = "poijarah.pdf";
//                                } else {
//                                    poijarah = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valpoijarah), 0, AppUtil.decodeImageTobase64(valpoijarah).length);
//                                    FNpoijarah = "poijarah.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvPoIjarah.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("MMQ_Akad_MMQ") != null) {
//                            String SSAkadMMQ = response.body().getData().get("MMQ_Akad_MMQ").getAsJsonObject().toString();
//                            MMQ_Akad_MMQ = gson.fromJson(SSAkadMMQ, ReqDocument.class);
//                            valakadmmq = MMQ_Akad_MMQ.getImg();
//                            try {
//                                if (MMQ_Akad_MMQ.getFileName().substring(MMQ_Akad_MMQ.getFileName().length() - 3, MMQ_Akad_MMQ.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNakadmmq = "akadmmq.pdf";
//                                } else {
//                                    akadmmq = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valakadmmq), 0, AppUtil.decodeImageTobase64(valakadmmq).length);
//                                    FNakadmmq = "akadmmq.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvAkadMmq.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("MMQ_Lampiran_Jadwal_Pengambil_Alihan") != null) {
//                            String SSMMQJadwalPengambilAlihan = response.body().getData().get("MMQ_Lampiran_Jadwal_Pengambil_Alihan").getAsJsonObject().toString();
//                            MMQ_Lampiran_Jadwal_Pengambil_Alihan = gson.fromJson(SSMMQJadwalPengambilAlihan, ReqDocument.class);
//                            valjadwalpengambilan = MMQ_Lampiran_Jadwal_Pengambil_Alihan.getImg();
//                            try {
//                                if (MMQ_Lampiran_Jadwal_Pengambil_Alihan.getFileName().substring(MMQ_Lampiran_Jadwal_Pengambil_Alihan.getFileName().length() - 3, MMQ_Lampiran_Jadwal_Pengambil_Alihan.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNjadwalpengambilan = "jadwalpengambilan.pdf";
//                                } else {
//                                    jadwalpengambilan = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valjadwalpengambilan), 0, AppUtil.decodeImageTobase64(valjadwalpengambilan).length);
//                                    FNjadwalpengambilan = "jadwalpengambilan.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvJadwalPengambilalihan.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("MMQ_Laporan_Penilaian_Aset") != null) {
//                            String SSMMQPenilaianAset = response.body().getData().get("MMQ_Laporan_Penilaian_Aset").getAsJsonObject().toString();
//                            MMQ_Laporan_Penilaian_Aset = gson.fromJson(SSMMQPenilaianAset, ReqDocument.class);
//                            valassetmmq = MMQ_Laporan_Penilaian_Aset.getImg();
//                            try {
//                                if (MMQ_Laporan_Penilaian_Aset.getFileName().substring(MMQ_Laporan_Penilaian_Aset.getFileName().length() - 3, MMQ_Laporan_Penilaian_Aset.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNassetmmq = "assetmmq.pdf";
//                                } else {
//                                    assetmmq = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valassetmmq), 0, AppUtil.decodeImageTobase64(valassetmmq).length);
//                                    FNassetmmq = "assetmmq.png";
//
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvAssetMmq.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga") != null) {
//                            String SSMMQSuratPeryataanAsetKetiga = response.body().getData().get("MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga").getAsJsonObject().toString();
//                            MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga = gson.fromJson(SSMMQSuratPeryataanAsetKetiga, ReqDocument.class);
//                            valpihak3 = MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.getImg();
//                            try {
//                                if (MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.getFileName().substring(MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.getFileName().length() - 3, MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNpihak3 = "pihak3.pdf";
//                                } else {
//                                    pihak3 = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valpihak3), 0, AppUtil.decodeImageTobase64(valpihak3).length);
//                                    FNpihak3 = "pihak3.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvSuratPernyataanDanKuasaAsetMmqPihakKetiga.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri") != null) {
//                            String SSMMQSuratPeryataanAsetSendiri = response.body().getData().get("MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri").getAsJsonObject().toString();
//                            MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri = gson.fromJson(SSMMQSuratPeryataanAsetSendiri, ReqDocument.class);
//                            valpihak1 = MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.getImg();
//                            try {
//                                if (MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.getFileName().substring(MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.getFileName().length() - 3, MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNpihak1 = "pihak1.pdf";
//                                } else {
//                                    pihak1 = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valpihak1), 0, AppUtil.decodeImageTobase64(valpihak1).length);
//                                    FNpihak1 = "pihak1.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvSuratPernyataanDanKuasaAsetMmqSendiri.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Murabahah_Akad_Murabahah") != null) {
//                            String SSMurabahahAkad = response.body().getData().get("Murabahah_Akad_Murabahah").getAsJsonObject().toString();
//                            Murabahah_Akad_Murabahah = gson.fromJson(SSMurabahahAkad, ReqDocument.class);
//                            valmurabahah = Murabahah_Akad_Murabahah.getImg();
//                            try {
//                                if (Murabahah_Akad_Murabahah.getFileName().substring(Murabahah_Akad_Murabahah.getFileName().length() - 3, Murabahah_Akad_Murabahah.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNmurabahah = "murabahah.pdf";
//                                } else {
//                                    murabahah = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valmurabahah), 0, AppUtil.decodeImageTobase64(valmurabahah).length);
//                                    FNmurabahah = "murabahah.pdf";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvAkadMurabahah.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Murabahah_Surat_Tanda_Terima_Barang") != null) {
//                            String SSMurabahahSuratTandaTerima = response.body().getData().get("Murabahah_Surat_Tanda_Terima_Barang").getAsJsonObject().toString();
//                            Murabahah_Surat_Tanda_Terima_Barang = gson.fromJson(SSMurabahahSuratTandaTerima, ReqDocument.class);
//                            valterimabarang = Murabahah_Surat_Tanda_Terima_Barang.getImg();
//                            try {
//                                if (Murabahah_Surat_Tanda_Terima_Barang.getFileName().substring(Murabahah_Surat_Tanda_Terima_Barang.getFileName().length() - 3, Murabahah_Surat_Tanda_Terima_Barang.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNterimabarang = "terimabarang.pdf";
//                                } else {
//                                    terimabarang = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valterimabarang), 0, AppUtil.decodeImageTobase64(valterimabarang).length);
//                                    FNterimabarang = "terimabarang.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvTerimaBarang.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Murabahah_Lampiran_Jadwal_Angsuran") != null) {
//                            String SSMurabahahLampiranJadwalAngsuran = response.body().getData().get("Murabahah_Lampiran_Jadwal_Angsuran").getAsJsonObject().toString();
//                            Murabahah_Lampiran_Jadwal_Angsuran = gson.fromJson(SSMurabahahLampiranJadwalAngsuran, ReqDocument.class);
//                            valjadwalangsuran = Murabahah_Lampiran_Jadwal_Angsuran.getImg();
//                            try {
//                                if (Murabahah_Lampiran_Jadwal_Angsuran.getFileName().substring(Murabahah_Lampiran_Jadwal_Angsuran.getFileName().length() - 3, Murabahah_Lampiran_Jadwal_Angsuran.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNjadwalangsuran = "jadwalangsuran.pdf";
//                                } else {
//                                    jadwalangsuran = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valjadwalangsuran), 0, AppUtil.decodeImageTobase64(valjadwalangsuran).length);
//                                    FNjadwalangsuran = "jadwalangsuran.png";
//                                }
//                            } catch (JsonSyntaxException e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvJadwalAngsuran.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Murabahah_Akad_Wakalah") != null) {
//                            String SSMurabahahAkadWakalah = response.body().getData().get("Murabahah_Akad_Wakalah").getAsJsonObject().toString();
//                            Murabahah_Akad_Wakalah = gson.fromJson(SSMurabahahAkadWakalah, ReqDocument.class);
//                            try {
//                                valwakalah = Murabahah_Akad_Wakalah.getImg();
//
//                                if (Murabahah_Akad_Wakalah.getFileName().substring(Murabahah_Akad_Wakalah.getFileName().length() - 3, Murabahah_Akad_Wakalah.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNwakalah = "akadwakalah.pdf";
//                                } else {
//                                    wakalah = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valwakalah), 0, AppUtil.decodeImageTobase64(valwakalah).length);
//                                    FNwakalah = "akadwakalah.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvAkadWakalah.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Murabahah_Purchase_Order") != null) {
//                            String SSMurabahahPurchaseOrder = response.body().getData().get("Murabahah_Purchase_Order").getAsJsonObject().toString();
//                            Murabahah_Purchase_Order = gson.fromJson(SSMurabahahPurchaseOrder, ReqDocument.class);
//                            valpo = Murabahah_Purchase_Order.getImg();
//                            try {
//                                if (Murabahah_Purchase_Order.getFileName().substring(Murabahah_Purchase_Order.getFileName().length() - 3, Murabahah_Purchase_Order.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNpo = "po.pdf";
//                                } else {
//                                    po = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valpo), 0, AppUtil.decodeImageTobase64(valpo).length);
//                                    FNpo = "po.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvPo.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Rahn_Lampiran_Jadwal_Angsuran") != null) {
//                            String SSRahnLampiranJadwalAngsuran = response.body().getData().get("Rahn_Lampiran_Jadwal_Angsuran").getAsJsonObject().toString();
//                            Rahn_Lampiran_Jadwal_Angsuran = gson.fromJson(SSRahnLampiranJadwalAngsuran, ReqDocument.class);
//                            valjadwalangsuran = Rahn_Lampiran_Jadwal_Angsuran.getImg();
//                            try {
//                                if (Rahn_Lampiran_Jadwal_Angsuran.getFileName().substring(Rahn_Lampiran_Jadwal_Angsuran.getFileName().length() - 3, Rahn_Lampiran_Jadwal_Angsuran.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNjadwalangsuran = "angsuranrahn.pdf";
//                                } else {
//                                    angsuranrahn = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valangsuranrahn), 0, AppUtil.decodeImageTobase64(valangsuranrahn).length);
//                                    FNjadwalangsuran = "angsuranrahn.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvJadwalAngsuran.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Rahn_Akad_Rahn") != null) {
//                            String SSAkadRahn = response.body().getData().get("Rahn_Akad_Rahn").getAsJsonObject().toString();
//                            Rahn_Akad_Rahn = gson.fromJson(SSAkadRahn, ReqDocument.class);
//                            valakadrahn = Rahn_Akad_Rahn.getImg();
//                            try {
//                                if (Rahn_Akad_Rahn.getFileName().substring(Rahn_Akad_Rahn.getFileName().length() - 3, Rahn_Akad_Rahn.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNakadrahn = "akadrahn.pdf";
//                                } else {
//                                    akadrahn = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valakadrahn), 0, AppUtil.decodeImageTobase64(valakadrahn).length);
//                                    FNakadrahn = "akadrahn.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvAkadRahn.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("SUP") != null) {
//                            String SSSup = response.body().getData().get("SUP").getAsJsonObject().toString();
//                            SUP = gson.fromJson(SSSup, ReqDocument.class);
//                            valsup = SUP.getImg();
//                            try {
//                                if (SUP.getFileName().substring(SUP.getFileName().length() - 3, SUP.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNsup = "sup.pdf";
//                                } else {
//                                    sup = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valsup), 0, AppUtil.decodeImageTobase64(valsup).length);
//                                    FNsup = "sup.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvSup.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Surat_Kuasa_Pernyataan_Flagging") != null) {
//
//                            String SSSuratKuasaPernyataanFlagging = response.body().getData().get("Surat_Kuasa_Pernyataan_Flagging").getAsJsonObject().toString();
//                            Surat_Kuasa_Pernyataan_Flagging = gson.fromJson(SSSuratKuasaPernyataanFlagging, ReqDocument.class);
//                            valkuasa = Surat_Kuasa_Pernyataan_Flagging.getImg();
//                            try {
//                                if (Surat_Kuasa_Pernyataan_Flagging.getFileName().substring(Surat_Kuasa_Pernyataan_Flagging.getFileName().length() - 3, Surat_Kuasa_Pernyataan_Flagging.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNkuasa = "kuasa.pdf";
//                                } else {
//                                    pernyataan = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valkuasa), 0, AppUtil.decodeImageTobase64(valkuasa).length);
//                                    FNkuasa = "kuasa.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvKuasa.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Surat_Pernyataan_Kuasa_Pembiayaan") != null) {
//
//                            String SSSuratKuasaPembiayaan = response.body().getData().get("Surat_Pernyataan_Kuasa_Pembiayaan").getAsJsonObject().toString();
//                            Surat_Pernyataan_Kuasa_Pembiayaan = gson.fromJson(SSSuratKuasaPembiayaan, ReqDocument.class);
//                            valpernyataan = Surat_Pernyataan_Kuasa_Pembiayaan.getImg();
//                            try {
//                                if (Surat_Pernyataan_Kuasa_Pembiayaan.getFileName().substring(Surat_Pernyataan_Kuasa_Pembiayaan.getFileName().length() - 3, Surat_Pernyataan_Kuasa_Pembiayaan.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNpernyataan = "pernyataan.pdf";
//                                } else {
//                                    kuasa = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valpernyataan), 0, AppUtil.decodeImageTobase64(valpernyataan).length);
//                                    FNpernyataan = "pernyataan.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvPernyataan.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Tanda_Terima_Dokumen_SK") != null) {
//                            String SSTandaTerimaDokumenSK = response.body().getData().get("Tanda_Terima_Dokumen_SK").getAsJsonObject().toString();
//                            Tanda_Terima_Dokumen_SK = gson.fromJson(SSTandaTerimaDokumenSK, ReqDocument.class);
//                            valsk = Tanda_Terima_Dokumen_SK.getImg();
//                            try {
//                                if (Tanda_Terima_Dokumen_SK.getFileName().substring(Tanda_Terima_Dokumen_SK.getFileName().length() - 3, Tanda_Terima_Dokumen_SK.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                    FNsk = "sk.pdf";
//                                } else {
//                                    sk = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valsk), 0, AppUtil.decodeImageTobase64(valsk).length);
//                                    FNsk = "sk.png";
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            binding.pvSk.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get("Dokumen_Umum") != null) {
//                            String SSDokumen_Umum = response.body().getData().get("Dokumen_Umum").toString();
//                            Type type = new TypeToken<List<ReqDocumentUmum>>() {
//                            }.getType();
//                            DokumenUmum = gson.fromJson(SSDokumen_Umum, type);
//                            addImg = DokumenUmum.size() - 1;
//                            if (DokumenUmum.size() == 1) {
//                                binding.llDeleteDokumen.setVisibility(View.GONE);
//                                binding.btnDeleteDokumen.setVisibility(View.GONE);
//                            } else if (DokumenUmum.size() == 5) {
//                                binding.btnTambahanDokumen.setVisibility(View.GONE);
//                                binding.llTambahanDokumen.setVisibility(View.GONE);
//                            }
//                            for (int i = 0; i < DokumenUmum.size(); i++) {
//                                if (i == 0) {
//                                    valDokumenA = DokumenUmum.get(i).getImg();
//                                    binding.etKeteranganDokumen1.setText(DokumenUmum.get(i).getKeteranganDokumen());
//                                    binding.etNamaDokumen1.setText(DokumenUmum.get(i).getNamaDokumen());
//                                    binding.cvUploadDokumen1.setVisibility(View.VISIBLE);
//                                    binding.tvUploadDokumen.setVisibility(View.VISIBLE);
//                                    binding.tfNamaDokumen1.setVisibility(View.VISIBLE);
//                                    binding.tfKeteranganDokumen1.setVisibility(View.VISIBLE);
//                                    binding.tfUploadDokumen1.setVisibility(View.VISIBLE);
//                                    binding.etNamaDokumen1.setVisibility(View.VISIBLE);
//                                    binding.etKeteranganDokumen1.setVisibility(View.VISIBLE);
//                                    binding.ivUploadDokumen1.setVisibility(View.VISIBLE);
//                                    binding.btnUploadDokumen1.setVisibility(View.VISIBLE);
//                                    try {
//                                        valDokumenA = DokumenUmum.get(i).getImg();
//                                        if (DokumenUmum.get(i).getFilename().substring(DokumenUmum.get(i).getFilename().length() - 3, DokumenUmum.get(i).getFilename().length()).equalsIgnoreCase("pdf")) {
//                                            AppUtil.convertBase64ToFileWithOnClick(ActivityUploadDokumen.this, valDokumenA, binding.ivUploadDokumen1, DokumenUmum.get(i).getNamaDokumen() + ".pdf");
//                                        } else {
//                                            AppUtil.convertBase64ToImage(valDokumenA, binding.ivUploadDokumen1);
//                                            dokumenA = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valDokumenA), 0, AppUtil.decodeImageTobase64(valDokumenA).length);
//                                        }
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                } else if (i == 1) {
//                                    try {
//                                        valDokumenB = DokumenUmum.get(i).getImg();
//                                        binding.etKeteranganDokumen2.setText(DokumenUmum.get(i).getKeteranganDokumen());
//                                        binding.etNamaDokumen2.setText(DokumenUmum.get(i).getNamaDokumen());
//                                        binding.cvUploadDokumen2.setVisibility(View.VISIBLE);
//                                        binding.tvUploadDokumen2.setVisibility(View.VISIBLE);
//                                        binding.tfNamaDokumen2.setVisibility(View.VISIBLE);
//                                        binding.tfKeteranganDokumen2.setVisibility(View.VISIBLE);
//                                        binding.tfUploadDokumen2.setVisibility(View.VISIBLE);
//                                        binding.etNamaDokumen2.setVisibility(View.VISIBLE);
//                                        binding.etKeteranganDokumen2.setVisibility(View.VISIBLE);
//                                        binding.ivUploadDokumen2.setVisibility(View.VISIBLE);
//                                        binding.btnUploadDokumen2.setVisibility(View.VISIBLE);
//                                        if (DokumenUmum.get(i).getFilename().substring(DokumenUmum.get(i).getFilename().length() - 3, DokumenUmum.get(i).getFilename().length()).equalsIgnoreCase("pdf")) {
//                                            AppUtil.convertBase64ToFileWithOnClick(ActivityUploadDokumen.this, valDokumenB, binding.ivUploadDokumen2, DokumenUmum.get(i).getNamaDokumen() + ".pdf");
//                                        } else {
//                                            AppUtil.convertBase64ToImage(valDokumenB, binding.ivUploadDokumen2);
//                                            dokumenB = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valDokumenB), 0, AppUtil.decodeImageTobase64(valDokumenB).length);
//                                        }
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                } else if (i == 2) {
//                                    try {
//                                        valDokumenC = DokumenUmum.get(i).getImg();
//                                        binding.etKeteranganDokumen3.setText(DokumenUmum.get(i).getKeteranganDokumen());
//                                        binding.etNamaDokumen3.setText(DokumenUmum.get(i).getNamaDokumen());
//                                        binding.cvUploadDokumen3.setVisibility(View.VISIBLE);
//                                        binding.tvUploadDokumen3.setVisibility(View.VISIBLE);
//                                        binding.tfNamaDokumen3.setVisibility(View.VISIBLE);
//                                        binding.tfKeteranganDokumen3.setVisibility(View.VISIBLE);
//                                        binding.tfUploadDokumen3.setVisibility(View.VISIBLE);
//                                        binding.etNamaDokumen3.setVisibility(View.VISIBLE);
//                                        binding.etKeteranganDokumen3.setVisibility(View.VISIBLE);
//                                        binding.ivUploadDokumen3.setVisibility(View.VISIBLE);
//                                        binding.btnUploadDokumen3.setVisibility(View.VISIBLE);
//                                        if (DokumenUmum.get(i).getFilename().substring(DokumenUmum.get(i).getFilename().length() - 3, DokumenUmum.get(i).getFilename().length()).equalsIgnoreCase("pdf")) {
//                                            AppUtil.convertBase64ToFileWithOnClick(ActivityUploadDokumen.this, valDokumenC, binding.ivUploadDokumen3, DokumenUmum.get(i).getNamaDokumen() + ".pdf");
//                                        } else {
//                                            AppUtil.convertBase64ToImage(valDokumenC, binding.ivUploadDokumen3);
//                                            dokumenC = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valDokumenC), 0, AppUtil.decodeImageTobase64(valDokumenC).length);
//                                        }
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                } else if (i == 3) {
//                                    try {
//                                        valDokumenD = DokumenUmum.get(i).getImg();
//                                        binding.etKeteranganDokumen4.setText(DokumenUmum.get(i).getKeteranganDokumen());
//                                        binding.etNamaDokumen4.setText(DokumenUmum.get(i).getNamaDokumen());
//                                        binding.cvUploadDokumen4.setVisibility(View.VISIBLE);
//                                        binding.tvUploadDokumen4.setVisibility(View.VISIBLE);
//                                        binding.tfNamaDokumen4.setVisibility(View.VISIBLE);
//                                        binding.tfKeteranganDokumen4.setVisibility(View.VISIBLE);
//                                        binding.tfUploadDokumen4.setVisibility(View.VISIBLE);
//                                        binding.etNamaDokumen4.setVisibility(View.VISIBLE);
//                                        binding.etKeteranganDokumen4.setVisibility(View.VISIBLE);
//                                        binding.ivUploadDokumen4.setVisibility(View.VISIBLE);
//                                        binding.btnUploadDokumen4.setVisibility(View.VISIBLE);
//                                        if (DokumenUmum.get(i).getFilename().substring(DokumenUmum.get(i).getFilename().length() - 3, DokumenUmum.get(i).getFilename().length()).equalsIgnoreCase("pdf")) {
//                                            AppUtil.convertBase64ToFileWithOnClick(ActivityUploadDokumen.this, valDokumenD, binding.ivUploadDokumen4, DokumenUmum.get(i).getNamaDokumen() + ".pdf");
//                                        } else {
//                                            AppUtil.convertBase64ToImage(valDokumenD, binding.ivUploadDokumen4);
//                                            dokumenD = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valDokumenD), 0, AppUtil.decodeImageTobase64(valDokumenD).length);
//                                        }
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                } else if (i == 4) {
//                                    try {
//                                        valDokumenE = DokumenUmum.get(i).getImg();
//                                        binding.etKeteranganDokumen5.setText(DokumenUmum.get(i).getKeteranganDokumen());
//                                        binding.etNamaDokumen5.setText(DokumenUmum.get(i).getNamaDokumen());
//                                        binding.cvUploadDokumen5.setVisibility(View.VISIBLE);
//                                        binding.tvUploadDokumen5.setVisibility(View.VISIBLE);
//                                        binding.tfNamaDokumen5.setVisibility(View.VISIBLE);
//                                        binding.tfKeteranganDokumen5.setVisibility(View.VISIBLE);
//                                        binding.tfUploadDokumen5.setVisibility(View.VISIBLE);
//                                        binding.etNamaDokumen5.setVisibility(View.VISIBLE);
//                                        binding.etKeteranganDokumen5.setVisibility(View.VISIBLE);
//                                        binding.ivUploadDokumen5.setVisibility(View.VISIBLE);
//                                        binding.btnUploadDokumen5.setVisibility(View.VISIBLE);
//                                        if (DokumenUmum.get(i).getFilename().substring(DokumenUmum.get(i).getFilename().length() - 3, DokumenUmum.get(i).getFilename().length()).equalsIgnoreCase("pdf")) {
//                                            AppUtil.convertBase64ToFileWithOnClick(ActivityUploadDokumen.this, valDokumenE, binding.ivUploadDokumen5, DokumenUmum.get(i).getNamaDokumen() + ".pdf");
//                                        } else {
//                                            AppUtil.convertBase64ToImage(valDokumenE, binding.ivUploadDokumen5);
//                                            dokumenE = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valDokumenE), 0, AppUtil.decodeImageTobase64(valDokumenE).length);
//                                        }
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//                        }
//
//                    }
//                } else {
//                    binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
//                    Error error = ParseResponseError.confirmEror(response.errorBody());
//                    AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), error.getMessage());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ParseResponse> call, Throwable t) {
//                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
//                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
//            }
//        });
//    }


    //logicalDOC

    private void initdata() {
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        ReqInquery req = new ReqInquery();
        req.setUID(String.valueOf(appPreferences.getUid()));
        req.setApplicationId(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().InqDokumenUpload(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                if (response.isSuccessful()) {
                    binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);

                    if (response.body().getStatus().equalsIgnoreCase("00") && response.body().getData() != null) {
                        Gson gson = new Gson();

                        // Umum
                        if (response.body().getData().get("TipeProduk") != null) {
                            if (response.body().getData().get("TipeProduk").getAsString().equalsIgnoreCase("Pra Pensiun 2 Tahun")) {
                                binding.llSkPensiun.setVisibility(View.VISIBLE);
                            } else {
                                binding.llSkPensiun.setVisibility(View.GONE);
                            }
                        }

                        if (response.body().getData().get("AsuransiKhusus") != null) {
                            if (response.body().getData().get("AsuransiKhusus").getAsString().equalsIgnoreCase("FALSE")) {
                                binding.llcovernote.setVisibility(View.GONE);
                            } else {
                                binding.llcovernote.setVisibility(View.VISIBLE);
                            }
                        }

                        // Dokumen Jaminan
                        if (response.body().getData().get("Form_Mutasi_Kantor_Bayar_Taspen") != null) {
                            String SSFormBayarTaspen = response.body().getData().get("Form_Mutasi_Kantor_Bayar_Taspen").getAsJsonObject().toString();
                            Form_Mutasi_Kantor_Bayar_Taspen = gson.fromJson(SSFormBayarTaspen, ReqDocument.class);
                            if (Form_Mutasi_Kantor_Bayar_Taspen.getFileName().substring(Form_Mutasi_Kantor_Bayar_Taspen.getFileName().length() - 3, Form_Mutasi_Kantor_Bayar_Taspen.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Form_Mutasi_Kantor_Bayar_Taspen.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valmutasi = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Form_Mutasi_Kantor_Bayar_Taspen.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                mutasi = resource;
                                            }
                                        });
                            }
                            binding.pvMutasi.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Foto_SK_Pengangkatan") != null) {
                            String SSSkPengangkatan = response.body().getData().get("Foto_SK_Pengangkatan").getAsJsonObject().toString();
                            Foto_SK_Pengangkatan = gson.fromJson(SSSkPengangkatan, ReqDocument.class);
                            if (Foto_SK_Pengangkatan.getFileName().substring(Foto_SK_Pengangkatan.getFileName().length() - 3, Foto_SK_Pengangkatan.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Foto_SK_Pengangkatan.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valskpengangkatan = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Foto_SK_Pengangkatan.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                skpengangkatan = resource;
                                            }
                                        });
                            }
                            binding.pvFotoSkPengangkatan.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Foto_SK_Pensiun") != null) {
                            String SSSkPensiun = response.body().getData().get("Foto_SK_Pensiun").getAsJsonObject().toString();
                            Foto_SK_Pensiun = gson.fromJson(SSSkPensiun, ReqDocument.class);
                            if (Foto_SK_Pensiun.getFileName().substring(Foto_SK_Pensiun.getFileName().length() - 3, Foto_SK_Pensiun.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Foto_SK_Pensiun.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valskpensiun = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Foto_SK_Pensiun.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                skpensiun = resource;
                                            }
                                        });
                            }
                            binding.pvFotoSkPensiun.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Foto_SK_Terakhir") != null) {
                            String SSSTerakhir = response.body().getData().get("Foto_SK_Terakhir").getAsJsonObject().toString();
                            Foto_SK_Terakhir = gson.fromJson(SSSTerakhir, ReqDocument.class);
                            if (Foto_SK_Terakhir.getFileName().substring(Foto_SK_Terakhir.getFileName().length() - 3, Foto_SK_Terakhir.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Foto_SK_Terakhir.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valskterakhir = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Foto_SK_Terakhir.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                skterakhir = resource;
                                            }
                                        });
                            }
                            binding.pvFotoSkTerakhir.setVisibility(View.VISIBLE);
                        }

                        // Asuransi Khusus
                        if (response.body().getData().get("Foto_Covernote_Asuransi") != null) {
                            String SSCovernoteAsuransi = response.body().getData().get("Foto_Covernote_Asuransi").getAsJsonObject().toString();
                            Foto_Covernote_Asuransi = gson.fromJson(SSCovernoteAsuransi, ReqDocument.class);
                            if (Foto_Covernote_Asuransi.getFileName().substring(Foto_Covernote_Asuransi.getFileName().length() - 3, Foto_Covernote_Asuransi.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Foto_Covernote_Asuransi.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valcovernoteasuransi = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Foto_Covernote_Asuransi.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                covernoteasuransi = resource;
                                            }
                                        });
                            }
                            binding.pvFotoCovernoteAsuransi.setVisibility(View.VISIBLE);
                        }

                        // Dokumen Produk
                        if (response.body().getData().get("SUP") != null) {
                            String SSSup = response.body().getData().get("SUP").getAsJsonObject().toString();
                            SUP = gson.fromJson(SSSup, ReqDocument.class);
                            if (SUP.getFileName().substring(SUP.getFileName().length() - 3, SUP.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(SUP.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valsup = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + SUP.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                sup = resource;
                                            }
                                        });
                            }
                            binding.pvSup.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Surat_Kuasa_Pernyataan_Flagging") != null) {
                            String SSSuratKuasaPernyataanFlagging = response.body().getData().get("Surat_Kuasa_Pernyataan_Flagging").getAsJsonObject().toString();
                            Surat_Kuasa_Pernyataan_Flagging = gson.fromJson(SSSuratKuasaPernyataanFlagging, ReqDocument.class);
                            if (Surat_Kuasa_Pernyataan_Flagging.getFileName().substring(Surat_Kuasa_Pernyataan_Flagging.getFileName().length() - 3, Surat_Kuasa_Pernyataan_Flagging.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Surat_Kuasa_Pernyataan_Flagging.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valkuasa = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Surat_Kuasa_Pernyataan_Flagging.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                kuasa = resource;
                                            }
                                        });
                            }
                            binding.pvKuasa.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Surat_Pernyataan_Kuasa_Pembiayaan") != null) {
                            String SSSuratKuasaPembiayaan = response.body().getData().get("Surat_Pernyataan_Kuasa_Pembiayaan").getAsJsonObject().toString();
                            Surat_Pernyataan_Kuasa_Pembiayaan = gson.fromJson(SSSuratKuasaPembiayaan, ReqDocument.class);
                            if (Surat_Pernyataan_Kuasa_Pembiayaan.getFileName().substring(Surat_Pernyataan_Kuasa_Pembiayaan.getFileName().length() - 3, Surat_Pernyataan_Kuasa_Pembiayaan.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Surat_Pernyataan_Kuasa_Pembiayaan.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valpernyataan = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Surat_Pernyataan_Kuasa_Pembiayaan.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                pernyataan = resource;
                                            }
                                        });
                            }
                            binding.pvPernyataan.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Tanda_Terima_Dokumen_SK") != null) {
                            String SSTandaTerimaDokumenSK = response.body().getData().get("Tanda_Terima_Dokumen_SK").getAsJsonObject().toString();
                            Tanda_Terima_Dokumen_SK = gson.fromJson(SSTandaTerimaDokumenSK, ReqDocument.class);
                            if (Tanda_Terima_Dokumen_SK.getFileName().substring(Tanda_Terima_Dokumen_SK.getFileName().length() - 3, Tanda_Terima_Dokumen_SK.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Tanda_Terima_Dokumen_SK.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valsk = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Tanda_Terima_Dokumen_SK.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                sk = resource;
                                            }
                                        });
                            }
                            binding.pvSk.setVisibility(View.VISIBLE);
                        }

                        // Tanda Tangan Akad
                        if (response.body().getData().get("Foto_Proses_Penandatanganan_Akad") != null) {
                            String SSProsesAkad = response.body().getData().get("Foto_Proses_Penandatanganan_Akad").getAsJsonObject().toString();
                            Foto_Proses_Penandatanganan_Akad = gson.fromJson(SSProsesAkad, ReqDocument.class);
                            if (Foto_Proses_Penandatanganan_Akad.getFileName().substring(Foto_Proses_Penandatanganan_Akad.getFileName().length() - 3, Foto_Proses_Penandatanganan_Akad.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Foto_Proses_Penandatanganan_Akad.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valttdakad = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Foto_Proses_Penandatanganan_Akad.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                ttdakad = resource;
                                            }
                                        });
                            }
                            binding.pvFotoProsesPenandatangananAkad.setVisibility(View.VISIBLE);
                        }

                        // Dokumen Murabahah
                        if (response.body().getData().get("Murabahah_Akad_Murabahah") != null) {
                            String SSMurabahahAkad = response.body().getData().get("Murabahah_Akad_Murabahah").getAsJsonObject().toString();
                            Murabahah_Akad_Murabahah = gson.fromJson(SSMurabahahAkad, ReqDocument.class);
                            if (Murabahah_Akad_Murabahah.getFileName().substring(Murabahah_Akad_Murabahah.getFileName().length() - 3, Murabahah_Akad_Murabahah.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Murabahah_Akad_Murabahah.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valmurabahah = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Murabahah_Akad_Murabahah.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                murabahah = resource;
                                            }
                                        });
                            }
                            binding.pvAkadMurabahah.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Murabahah_Surat_Tanda_Terima_Barang") != null) {
                            String SSMurabahahSuratTandaTerima = response.body().getData().get("Murabahah_Surat_Tanda_Terima_Barang").getAsJsonObject().toString();
                            Murabahah_Surat_Tanda_Terima_Barang = gson.fromJson(SSMurabahahSuratTandaTerima, ReqDocument.class);
                            if (Murabahah_Surat_Tanda_Terima_Barang.getFileName().substring(Murabahah_Surat_Tanda_Terima_Barang.getFileName().length() - 3, Murabahah_Surat_Tanda_Terima_Barang.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Murabahah_Surat_Tanda_Terima_Barang.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valterimabarang = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Murabahah_Surat_Tanda_Terima_Barang.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                terimabarang = resource;
                                            }
                                        });
                            }
                            binding.pvTerimaBarang.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Murabahah_Lampiran_Jadwal_Angsuran") != null) {
                            String SSMurabahahLampiranJadwalAngsuran = response.body().getData().get("Murabahah_Lampiran_Jadwal_Angsuran").getAsJsonObject().toString();
                            Murabahah_Lampiran_Jadwal_Angsuran = gson.fromJson(SSMurabahahLampiranJadwalAngsuran, ReqDocument.class);
                            if (Murabahah_Lampiran_Jadwal_Angsuran.getFileName().substring(Murabahah_Lampiran_Jadwal_Angsuran.getFileName().length() - 3, Murabahah_Lampiran_Jadwal_Angsuran.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Murabahah_Lampiran_Jadwal_Angsuran.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valjadwalangsuran = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Murabahah_Lampiran_Jadwal_Angsuran.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                jadwalangsuran = resource;
                                            }
                                        });
                            }
                            binding.pvJadwalAngsuran.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Murabahah_Akad_Wakalah") != null) {
                            String SSMurabahahAkadWakalah = response.body().getData().get("Murabahah_Akad_Wakalah").getAsJsonObject().toString();
                            Murabahah_Akad_Wakalah = gson.fromJson(SSMurabahahAkadWakalah, ReqDocument.class);
                            if (Murabahah_Akad_Wakalah.getFileName().substring(Murabahah_Akad_Wakalah.getFileName().length() - 3, Murabahah_Akad_Wakalah.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Murabahah_Akad_Wakalah.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valwakalah = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Murabahah_Akad_Wakalah.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                wakalah = resource;
                                            }
                                        });
                            }
                            binding.pvAkadWakalah.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Murabahah_Purchase_Order") != null) {
                            String SSMurabahahPurchaseOrder = response.body().getData().get("Murabahah_Purchase_Order").getAsJsonObject().toString();
                            Murabahah_Purchase_Order = gson.fromJson(SSMurabahahPurchaseOrder, ReqDocument.class);
                            if (Murabahah_Purchase_Order.getFileName().substring(Murabahah_Purchase_Order.getFileName().length() - 3, Murabahah_Purchase_Order.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Murabahah_Purchase_Order.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valpo = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Murabahah_Purchase_Order.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                po = resource;
                                            }
                                        });
                            }
                            binding.pvPo.setVisibility(View.VISIBLE);
                        }

                        // Dokumen Ijarah
                        if (response.body().getData().get("Ijarah_Akad_Ijarah") != null) {
                            String SSIjarahAkad = response.body().getData().get("Ijarah_Akad_Ijarah").getAsJsonObject().toString();
                            Ijarah_Akad_Ijarah = gson.fromJson(SSIjarahAkad, ReqDocument.class);
                            if (SUP.getFileName().substring(Ijarah_Akad_Ijarah.getFileName().length() - 3, Ijarah_Akad_Ijarah.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Ijarah_Akad_Ijarah.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valijarah = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Ijarah_Akad_Ijarah.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                ijarah = resource;
                                            }
                                        });
                            }
                            binding.pvAkadIjarah.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Ijarah_Akad_Wakalah") != null) {
                            String SSIjarahAkadWakalah = response.body().getData().get("Ijarah_Akad_Wakalah").getAsJsonObject().toString();
                            Ijarah_Akad_Wakalah = gson.fromJson(SSIjarahAkadWakalah, ReqDocument.class);
                            if (Ijarah_Akad_Wakalah.getFileName().substring(Ijarah_Akad_Wakalah.getFileName().length() - 3, Ijarah_Akad_Wakalah.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Ijarah_Akad_Wakalah.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valwakalahijarah = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Ijarah_Akad_Wakalah.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                wakalahijarah = resource;
                                            }
                                        });
                            }
                            binding.pvAkadWakalahIjarah.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Ijarah_Lampiran_Jadwal_Angsuran") != null) {
                            String SSIjarahLampiranJadwal = response.body().getData().get("Ijarah_Lampiran_Jadwal_Angsuran").getAsJsonObject().toString();
                            Ijarah_Lampiran_Jadwal_Angsuran = gson.fromJson(SSIjarahLampiranJadwal, ReqDocument.class);
                            if (Ijarah_Lampiran_Jadwal_Angsuran.getFileName().substring(Ijarah_Lampiran_Jadwal_Angsuran.getFileName().length() - 3, Ijarah_Lampiran_Jadwal_Angsuran.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Ijarah_Lampiran_Jadwal_Angsuran.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valangsuranujrah = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Ijarah_Lampiran_Jadwal_Angsuran.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                angsuranujrah = resource;
                                            }
                                        });
                            }
                            binding.pvAngsuranUjrah.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Ijarah_Purchase_Order") != null) {
                            String SSIjarahPurchaseOrder = response.body().getData().get("Ijarah_Purchase_Order").getAsJsonObject().toString();
                            Ijarah_Purchase_Order = gson.fromJson(SSIjarahPurchaseOrder, ReqDocument.class);
                            if (Ijarah_Purchase_Order.getFileName().substring(Ijarah_Purchase_Order.getFileName().length() - 3, Ijarah_Purchase_Order.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Ijarah_Purchase_Order.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valpoijarah = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Ijarah_Purchase_Order.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                poijarah = resource;
                                            }
                                        });
                            }
                            binding.pvPoIjarah.setVisibility(View.VISIBLE);
                        }

                        // Dokumen MMQ
                        if (response.body().getData().get("MMQ_Akad_MMQ") != null) {
                            String SSAkadMMQ = response.body().getData().get("MMQ_Akad_MMQ").getAsJsonObject().toString();
                            MMQ_Akad_MMQ = gson.fromJson(SSAkadMMQ, ReqDocument.class);
                            if (MMQ_Akad_MMQ.getFileName().substring(MMQ_Akad_MMQ.getFileName().length() - 3, MMQ_Akad_MMQ.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(MMQ_Akad_MMQ.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valakadmmq = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + MMQ_Akad_MMQ.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                akadmmq = resource;
                                            }
                                        });
                            }
                            binding.pvAkadMmq.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("MMQ_Lampiran_Jadwal_Pengambil_Alihan") != null) {
                            String SSMMQJadwalPengambilAlihan = response.body().getData().get("MMQ_Lampiran_Jadwal_Pengambil_Alihan").getAsJsonObject().toString();
                            MMQ_Lampiran_Jadwal_Pengambil_Alihan = gson.fromJson(SSMMQJadwalPengambilAlihan, ReqDocument.class);
                            if (MMQ_Lampiran_Jadwal_Pengambil_Alihan.getFileName().substring(MMQ_Lampiran_Jadwal_Pengambil_Alihan.getFileName().length() - 3, MMQ_Lampiran_Jadwal_Pengambil_Alihan.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(MMQ_Lampiran_Jadwal_Pengambil_Alihan.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valjadwalpengambilan = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + MMQ_Lampiran_Jadwal_Pengambil_Alihan.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                jadwalpengambilan = resource;
                                            }
                                        });
                            }
                            binding.pvJadwalPengambilalihan.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("MMQ_Laporan_Penilaian_Aset") != null) {
                            String SSMMQPenilaianAset = response.body().getData().get("MMQ_Laporan_Penilaian_Aset").getAsJsonObject().toString();
                            MMQ_Laporan_Penilaian_Aset = gson.fromJson(SSMMQPenilaianAset, ReqDocument.class);
                            if (MMQ_Laporan_Penilaian_Aset.getFileName().substring(MMQ_Laporan_Penilaian_Aset.getFileName().length() - 3, MMQ_Laporan_Penilaian_Aset.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(MMQ_Laporan_Penilaian_Aset.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valassetmmq = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + MMQ_Laporan_Penilaian_Aset.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                assetmmq = resource;
                                            }
                                        });
                            }
                            binding.pvAssetMmq.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga") != null) {
                            String SSMMQSuratPeryataanAsetKetiga = response.body().getData().get("MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga").getAsJsonObject().toString();
                            MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga = gson.fromJson(SSMMQSuratPeryataanAsetKetiga, ReqDocument.class);
                            if (MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.getFileName().substring(MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.getFileName().length() - 3, MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valpihak3 = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                pihak3 = resource;
                                            }
                                        });
                            }
                            binding.pvSuratPernyataanDanKuasaAsetMmqPihakKetiga.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri") != null) {
                            String SSMMQSuratPeryataanAsetSendiri = response.body().getData().get("MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri").getAsJsonObject().toString();
                            MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri = gson.fromJson(SSMMQSuratPeryataanAsetSendiri, ReqDocument.class);
                            if (MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.getFileName().substring(MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.getFileName().length() - 3, MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valpihak1 = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                pihak1 = resource;
                                            }
                                        });
                            }
                            binding.pvSuratPernyataanDanKuasaAsetMmqSendiri.setVisibility(View.VISIBLE);
                        }

                        // Dokumen Rahn
                        if (response.body().getData().get("Rahn_Lampiran_Jadwal_Angsuran") != null) {
                            String SSRahnLampiranJadwalAngsuran = response.body().getData().get("Rahn_Lampiran_Jadwal_Angsuran").getAsJsonObject().toString();
                            Rahn_Lampiran_Jadwal_Angsuran = gson.fromJson(SSRahnLampiranJadwalAngsuran, ReqDocument.class);
                            if (Rahn_Lampiran_Jadwal_Angsuran.getFileName().substring(Rahn_Lampiran_Jadwal_Angsuran.getFileName().length() - 3, Rahn_Lampiran_Jadwal_Angsuran.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Rahn_Lampiran_Jadwal_Angsuran.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valjadwalangsuran = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Rahn_Lampiran_Jadwal_Angsuran.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                jadwalangsuran = resource;
                                            }
                                        });
                            }
                            binding.pvJadwalAngsuran.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Rahn_Akad_Rahn") != null) {
                            String SSAkadRahn = response.body().getData().get("Rahn_Akad_Rahn").getAsJsonObject().toString();
                            Rahn_Akad_Rahn = gson.fromJson(SSAkadRahn, ReqDocument.class);
                            if (Rahn_Akad_Rahn.getFileName().substring(Rahn_Akad_Rahn.getFileName().length() - 3, SUP.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Rahn_Akad_Rahn.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valakadrahn = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Rahn_Akad_Rahn.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                akadrahn = resource;
                                            }
                                        });
                            }
                            binding.pvAkadRahn.setVisibility(View.VISIBLE);
                        }

                        // Dokumen Pembiayaan
                        if (response.body().getData().get("Form_Mutas_Kantor_Bayar") != null) {
                            String SSFormMutasiKantorBayar = response.body().getData().get("Form_Mutas_Kantor_Bayar").getAsJsonObject().toString();
                            Form_Mutasi_Kantor_Bayar = gson.fromJson(SSFormMutasiKantorBayar, ReqDocument.class);
                            if (Form_Mutasi_Kantor_Bayar.getFileName().substring(Form_Mutasi_Kantor_Bayar.getFileName().length() - 3, Form_Mutasi_Kantor_Bayar.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Form_Mutasi_Kantor_Bayar.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valkantorbayar = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Form_Mutasi_Kantor_Bayar.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                kantorbayar = resource;
                                            }
                                        });
                            }
                            binding.pvFormMutasiKantorBayar.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Foto_Bukti_Otentikasi_Nasabah") != null) {
                            String SSOtentikasiNasabah = response.body().getData().get("Foto_Bukti_Otentikasi_Nasabah").getAsJsonObject().toString();
                            Foto_Bukti_Otentikasi_Nasabah = gson.fromJson(SSOtentikasiNasabah, ReqDocument.class);
                            if (Foto_Bukti_Otentikasi_Nasabah.getFileName().substring(Foto_Bukti_Otentikasi_Nasabah.getFileName().length() - 3, Foto_Bukti_Otentikasi_Nasabah.getFileName().length()).equalsIgnoreCase("pdf")) {
                                ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(Foto_Bukti_Otentikasi_Nasabah.getImg());
                                call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                    @Override
                                    public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getBinaryData() != null) {
                                                valfotoakad = response.body().getBinaryData();
                                            } else {
                                                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                            }
                                        } else {
                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                        Log.d("LOG D", t.getMessage());
                                        t.printStackTrace();
                                    }
                                });
                            } else {
                                String url_photo = null;
                                try {
                                    url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + Foto_Bukti_Otentikasi_Nasabah.getImg();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Glide
                                        .with(ActivityUploadDokumen.this)
                                        .asBitmap()
                                        .load(url_photo)
                                        .placeholder(R.drawable.banner_placeholder)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                fotoakad = resource;
                                            }
                                        });
                            }
                            binding.pvFotoScreenCaptureBuktiOtentikasiNasabahSaatAkad.setVisibility(View.VISIBLE);
                        }

                        // Dokumen Tambahan
                        if (response.body().getData().get("Dokumen_Umum") != null) {
                            String SSDokumen_Umum = response.body().getData().get("Dokumen_Umum").toString();
                            Type type = new TypeToken<List<ReqDocumentUmum>>() {
                            }.getType();
                            DokumenUmum = gson.fromJson(SSDokumen_Umum, type);
                            addImg = DokumenUmum.size() - 1;
                            if (DokumenUmum.size() == 1) {
                                binding.llDeleteDokumen.setVisibility(View.GONE);
                                binding.btnDeleteDokumen.setVisibility(View.GONE);
                            } else if (DokumenUmum.size() == 5) {
                                binding.btnTambahanDokumen.setVisibility(View.GONE);
                                binding.llTambahanDokumen.setVisibility(View.GONE);
                            }
                            for (int i = 0; i < DokumenUmum.size(); i++) {
                                if (i == 0) {
                                    binding.etKeteranganDokumen1.setText(DokumenUmum.get(i).getKeteranganDokumen());
                                    binding.etNamaDokumen1.setText(DokumenUmum.get(i).getNamaDokumen());
                                    binding.cvUploadDokumen1.setVisibility(View.VISIBLE);
                                    binding.tvUploadDokumen.setVisibility(View.VISIBLE);
                                    binding.tfNamaDokumen1.setVisibility(View.VISIBLE);
                                    binding.tfKeteranganDokumen1.setVisibility(View.VISIBLE);
                                    binding.tfUploadDokumen1.setVisibility(View.VISIBLE);
                                    binding.etNamaDokumen1.setVisibility(View.VISIBLE);
                                    binding.etKeteranganDokumen1.setVisibility(View.VISIBLE);
                                    binding.ivUploadDokumen1.setVisibility(View.VISIBLE);
                                    binding.btnUploadDokumen1.setVisibility(View.VISIBLE);
                                    try {
                                        if (DokumenUmum.get(i).getFilename().substring(DokumenUmum.get(i).getFilename().length() - 3, DokumenUmum.get(i).getFilename().length()).equalsIgnoreCase("pdf")) {
                                            ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                            Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(DokumenUmum.get(i).getImg());
                                            call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                                @Override
                                                public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                                    if (response.isSuccessful()) {
                                                        if (response.body().getBinaryData() != null) {
                                                            valDokumenA = response.body().getBinaryData();
                                                            AppUtil.convertBase64ToFileWithOnClick(ActivityUploadDokumen.this, valDokumenA, binding.ivUploadDokumen1, DokumenUmum.get(0).getNamaDokumen() + ".pdf");
                                                        } else {
                                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                                        }
                                                    } else {
                                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                                    binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                                    AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                                    Log.d("LOG D", t.getMessage());
                                                    t.printStackTrace();
                                                }
                                            });
                                        } else {
                                            String url_photo = null;
                                            try {
                                                url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + DokumenUmum.get(i).getImg();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            Glide
                                                    .with(ActivityUploadDokumen.this)
                                                    .asBitmap()
                                                    .load(url_photo)
                                                    .placeholder(R.drawable.banner_placeholder)
                                                    .into(new SimpleTarget<Bitmap>() {
                                                        @Override
                                                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                            AppUtil.convertBase64ToImage(AppUtil.encodeImageTobase64(resource), binding.ivUploadDokumen1);
                                                            dokumenA = resource;
                                                        }
                                                    });
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if (i == 1) {
                                    try {
                                        binding.etKeteranganDokumen2.setText(DokumenUmum.get(i).getKeteranganDokumen());
                                        binding.etNamaDokumen2.setText(DokumenUmum.get(i).getNamaDokumen());
                                        binding.cvUploadDokumen2.setVisibility(View.VISIBLE);
                                        binding.tvUploadDokumen2.setVisibility(View.VISIBLE);
                                        binding.tfNamaDokumen2.setVisibility(View.VISIBLE);
                                        binding.tfKeteranganDokumen2.setVisibility(View.VISIBLE);
                                        binding.tfUploadDokumen2.setVisibility(View.VISIBLE);
                                        binding.etNamaDokumen2.setVisibility(View.VISIBLE);
                                        binding.etKeteranganDokumen2.setVisibility(View.VISIBLE);
                                        binding.ivUploadDokumen2.setVisibility(View.VISIBLE);
                                        binding.btnUploadDokumen2.setVisibility(View.VISIBLE);
                                        if (DokumenUmum.get(i).getFilename().substring(DokumenUmum.get(i).getFilename().length() - 3, DokumenUmum.get(i).getFilename().length()).equalsIgnoreCase("pdf")) {
                                            ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                            Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(DokumenUmum.get(i).getImg());
                                            call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                                @Override
                                                public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                                    if (response.isSuccessful()) {
                                                        if (response.body().getBinaryData() != null) {
                                                            valDokumenB = response.body().getBinaryData();
                                                            AppUtil.convertBase64ToFileWithOnClick(ActivityUploadDokumen.this, valDokumenB, binding.ivUploadDokumen2, DokumenUmum.get(1).getNamaDokumen() + ".pdf");
                                                        } else {
                                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                                        }
                                                    } else {
                                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                                    binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                                    AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                                    Log.d("LOG D", t.getMessage());
                                                    t.printStackTrace();
                                                }
                                            });
                                        } else {
                                            String url_photo = null;
                                            try {
                                                url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + DokumenUmum.get(i).getImg();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            Glide
                                                    .with(ActivityUploadDokumen.this)
                                                    .asBitmap()
                                                    .load(url_photo)
                                                    .placeholder(R.drawable.banner_placeholder)
                                                    .into(new SimpleTarget<Bitmap>() {
                                                        @Override
                                                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                            AppUtil.convertBase64ToImage(AppUtil.encodeImageTobase64(resource), binding.ivUploadDokumen2);
                                                            dokumenB = resource;
                                                        }
                                                    });
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if (i == 2) {
                                    try {
                                        binding.etKeteranganDokumen3.setText(DokumenUmum.get(i).getKeteranganDokumen());
                                        binding.etNamaDokumen3.setText(DokumenUmum.get(i).getNamaDokumen());
                                        binding.cvUploadDokumen3.setVisibility(View.VISIBLE);
                                        binding.tvUploadDokumen3.setVisibility(View.VISIBLE);
                                        binding.tfNamaDokumen3.setVisibility(View.VISIBLE);
                                        binding.tfKeteranganDokumen3.setVisibility(View.VISIBLE);
                                        binding.tfUploadDokumen3.setVisibility(View.VISIBLE);
                                        binding.etNamaDokumen3.setVisibility(View.VISIBLE);
                                        binding.etKeteranganDokumen3.setVisibility(View.VISIBLE);
                                        binding.ivUploadDokumen3.setVisibility(View.VISIBLE);
                                        binding.btnUploadDokumen3.setVisibility(View.VISIBLE);
                                        if (DokumenUmum.get(i).getFilename().substring(DokumenUmum.get(i).getFilename().length() - 3, DokumenUmum.get(i).getFilename().length()).equalsIgnoreCase("pdf")) {
                                            ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                            Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(DokumenUmum.get(i).getImg());
                                            call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                                @Override
                                                public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                                    if (response.isSuccessful()) {
                                                        if (response.body().getBinaryData() != null) {
                                                            valDokumenC = response.body().getBinaryData();
                                                            AppUtil.convertBase64ToFileWithOnClick(ActivityUploadDokumen.this, valDokumenC, binding.ivUploadDokumen3, DokumenUmum.get(2).getNamaDokumen() + ".pdf");
                                                        } else {
                                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                                        }
                                                    } else {
                                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                                    binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                                    AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                                    Log.d("LOG D", t.getMessage());
                                                    t.printStackTrace();
                                                }
                                            });
                                        } else {
                                            String url_photo = null;
                                            try {
                                                url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + DokumenUmum.get(i).getImg();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            Glide
                                                    .with(ActivityUploadDokumen.this)
                                                    .asBitmap()
                                                    .load(url_photo)
                                                    .placeholder(R.drawable.banner_placeholder)
                                                    .into(new SimpleTarget<Bitmap>() {
                                                        @Override
                                                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                            AppUtil.convertBase64ToImage(AppUtil.encodeImageTobase64(resource), binding.ivUploadDokumen3);
                                                            dokumenC = resource;
                                                        }
                                                    });
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if (i == 3) {
                                    try {
                                        binding.etKeteranganDokumen4.setText(DokumenUmum.get(i).getKeteranganDokumen());
                                        binding.etNamaDokumen4.setText(DokumenUmum.get(i).getNamaDokumen());
                                        binding.cvUploadDokumen4.setVisibility(View.VISIBLE);
                                        binding.tvUploadDokumen4.setVisibility(View.VISIBLE);
                                        binding.tfNamaDokumen4.setVisibility(View.VISIBLE);
                                        binding.tfKeteranganDokumen4.setVisibility(View.VISIBLE);
                                        binding.tfUploadDokumen4.setVisibility(View.VISIBLE);
                                        binding.etNamaDokumen4.setVisibility(View.VISIBLE);
                                        binding.etKeteranganDokumen4.setVisibility(View.VISIBLE);
                                        binding.ivUploadDokumen4.setVisibility(View.VISIBLE);
                                        binding.btnUploadDokumen4.setVisibility(View.VISIBLE);
                                        if (DokumenUmum.get(i).getFilename().substring(DokumenUmum.get(i).getFilename().length() - 3, DokumenUmum.get(i).getFilename().length()).equalsIgnoreCase("pdf")) {
                                            ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                            Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(DokumenUmum.get(i).getImg());
                                            call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                                @Override
                                                public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                                    if (response.isSuccessful()) {
                                                        if (response.body().getBinaryData() != null) {
                                                            valDokumenD = response.body().getBinaryData();
                                                            AppUtil.convertBase64ToFileWithOnClick(ActivityUploadDokumen.this, valDokumenD, binding.ivUploadDokumen4, DokumenUmum.get(3).getNamaDokumen() + ".pdf");
                                                        } else {
                                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                                        }
                                                    } else {
                                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                                    binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                                    AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                                    Log.d("LOG D", t.getMessage());
                                                    t.printStackTrace();
                                                }
                                            });
                                        } else {
                                            String url_photo = null;
                                            try {
                                                url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + DokumenUmum.get(i).getImg();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            Glide
                                                    .with(ActivityUploadDokumen.this)
                                                    .asBitmap()
                                                    .load(url_photo)
                                                    .placeholder(R.drawable.banner_placeholder)
                                                    .into(new SimpleTarget<Bitmap>() {
                                                        @Override
                                                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                            AppUtil.convertBase64ToImage(AppUtil.encodeImageTobase64(resource), binding.ivUploadDokumen4);
                                                            dokumenD = resource;
                                                        }
                                                    });
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if (i == 4) {
                                    try {
                                        binding.etKeteranganDokumen5.setText(DokumenUmum.get(i).getKeteranganDokumen());
                                        binding.etNamaDokumen5.setText(DokumenUmum.get(i).getNamaDokumen());
                                        binding.cvUploadDokumen5.setVisibility(View.VISIBLE);
                                        binding.tvUploadDokumen5.setVisibility(View.VISIBLE);
                                        binding.tfNamaDokumen5.setVisibility(View.VISIBLE);
                                        binding.tfKeteranganDokumen5.setVisibility(View.VISIBLE);
                                        binding.tfUploadDokumen5.setVisibility(View.VISIBLE);
                                        binding.etNamaDokumen5.setVisibility(View.VISIBLE);
                                        binding.etKeteranganDokumen5.setVisibility(View.VISIBLE);
                                        binding.ivUploadDokumen5.setVisibility(View.VISIBLE);
                                        binding.btnUploadDokumen5.setVisibility(View.VISIBLE);
                                        if (DokumenUmum.get(i).getFilename().substring(DokumenUmum.get(i).getFilename().length() - 3, DokumenUmum.get(i).getFilename().length()).equalsIgnoreCase("pdf")) {
                                            ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityUploadDokumen.this);
                                            Call<ParseResponseLogicalDoc> call2 = apiClientAdapter.getApiInterface().getFileJson(DokumenUmum.get(i).getImg());
                                            call2.enqueue(new Callback<ParseResponseLogicalDoc>() {
                                                @Override
                                                public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                                                    if (response.isSuccessful()) {
                                                        if (response.body().getBinaryData() != null) {
                                                            valDokumenE = response.body().getBinaryData();
                                                            AppUtil.convertBase64ToFileWithOnClick(ActivityUploadDokumen.this, valDokumenE, binding.ivUploadDokumen5, DokumenUmum.get(4).getNamaDokumen() + ".pdf");
                                                        } else {
                                                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                                                        }
                                                    } else {
                                                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                                                    binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                                                    AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                                                    Log.d("LOG D", t.getMessage());
                                                    t.printStackTrace();
                                                }
                                            });
                                        } else {
                                            String url_photo = null;
                                            try {
                                                url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + DokumenUmum.get(i).getImg();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            Glide
                                                    .with(ActivityUploadDokumen.this)
                                                    .asBitmap()
                                                    .load(url_photo)
                                                    .placeholder(R.drawable.banner_placeholder)
                                                    .into(new SimpleTarget<Bitmap>() {
                                                        @Override
                                                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                            AppUtil.convertBase64ToImage(AppUtil.encodeImageTobase64(resource), binding.ivUploadDokumen5);
                                                            dokumenE = resource;
                                                        }
                                                    });
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                    }
                } else {
                    binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                    Error error = ParseResponseError.confirmEror(response.errorBody());
                    AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), error.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void hidePreviewButton() {
        binding.pvFotoSkPengangkatan.setVisibility(View.GONE);
        binding.pvFotoSkPensiun.setVisibility(View.GONE);
        binding.pvFotoSkTerakhir.setVisibility(View.GONE);
        binding.pvFotoCovernoteAsuransi.setVisibility(View.GONE);
        binding.pvSk.setVisibility(View.GONE);
        binding.pvKuasa.setVisibility(View.GONE);
        binding.pvMutasi.setVisibility(View.GONE);
        binding.pvPernyataan.setVisibility(View.GONE);
        binding.pvSup.setVisibility(View.GONE);
        binding.pvFotoProsesPenandatangananAkad.setVisibility(View.GONE);
        binding.pvPo.setVisibility(View.GONE);
        binding.pvAkadWakalah.setVisibility(View.GONE);
        binding.pvAkadWakalahIjarah.setVisibility(View.GONE);
        binding.pvAkadMurabahah.setVisibility(View.GONE);
        binding.pvJadwalAngsuran.setVisibility(View.GONE);
        binding.pvTerimaBarang.setVisibility(View.GONE);
        binding.pvPoIjarah.setVisibility(View.GONE);
        binding.pvAkadIjarah.setVisibility(View.GONE);
        binding.pvAngsuranUjrah.setVisibility(View.GONE);
        binding.pvAssetMmq.setVisibility(View.GONE);
        binding.pvAkadMmq.setVisibility(View.GONE);
        binding.pvJadwalPengambilalihan.setVisibility(View.GONE);
        binding.pvSuratPernyataanDanKuasaAsetMmqSendiri.setVisibility(View.GONE);
        binding.pvSuratPernyataanDanKuasaAsetMmqPihakKetiga.setVisibility(View.GONE);
        binding.pvAkadRahn.setVisibility(View.GONE);
        binding.pvJadwalAngsuranRahn.setVisibility(View.GONE);
        binding.pvFormMutasiKantorBayar.setVisibility(View.GONE);
        binding.pvFotoScreenCaptureBuktiOtentikasiNasabahSaatAkad.setVisibility(View.GONE);
    }

    private void hideUploadDokumen() {
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


        //di hide dlu katanya
        binding.llLampiranMurabahah.setVisibility(View.GONE);

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
        binding.btnFotoScreenBuktiOtentikasiNasabahSaatAkad.setOnClickListener(this);
        binding.etNamaDokumen1.setOnClickListener(this);
        binding.etNamaDokumen2.setOnClickListener(this);
        binding.etKeteranganDokumen1.setOnClickListener(this);
        binding.etKeteranganDokumen2.setOnClickListener(this);
        binding.btnUploadDokumen1.setOnClickListener(this);
        binding.btnUploadDokumen2.setOnClickListener(this);
        binding.btnUploadDokumen3.setOnClickListener(this);
        binding.btnUploadDokumen4.setOnClickListener(this);
        binding.btnUploadDokumen5.setOnClickListener(this);

        binding.ivUploadDokumen1.setOnClickListener(this);
        binding.ivUploadDokumen2.setOnClickListener(this);
        binding.ivUploadDokumen3.setOnClickListener(this);
        binding.ivUploadDokumen4.setOnClickListener(this);
        binding.ivUploadDokumen5.setOnClickListener(this);

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
        binding.llDeleteDokumen.setOnClickListener(this);
        binding.btnDeleteDokumen.setOnClickListener(this);
        binding.btnSend.setOnClickListener(this);
        binding.llBtnSend.setOnClickListener(this);

        binding.pvFotoSkPengangkatan.setOnClickListener(this);
        binding.pvFotoSkPensiun.setOnClickListener(this);
        binding.pvFotoSkTerakhir.setOnClickListener(this);
        binding.pvFotoCovernoteAsuransi.setOnClickListener(this);
        binding.pvSk.setOnClickListener(this);
        binding.pvKuasa.setOnClickListener(this);
        binding.pvMutasi.setOnClickListener(this);
        binding.pvPernyataan.setOnClickListener(this);
        binding.pvSup.setOnClickListener(this);
        binding.pvFotoProsesPenandatangananAkad.setOnClickListener(this);
        binding.pvPo.setOnClickListener(this);
        binding.pvAkadWakalah.setOnClickListener(this);
        binding.pvAkadWakalahIjarah.setOnClickListener(this);
        binding.pvAkadMurabahah.setOnClickListener(this);
        binding.pvJadwalAngsuran.setOnClickListener(this);
        binding.pvTerimaBarang.setOnClickListener(this);
        binding.pvPoIjarah.setOnClickListener(this);
        binding.pvAkadIjarah.setOnClickListener(this);
        binding.pvAngsuranUjrah.setOnClickListener(this);
        binding.pvAssetMmq.setOnClickListener(this);
        binding.pvAkadMmq.setOnClickListener(this);
        binding.pvJadwalPengambilalihan.setOnClickListener(this);
        binding.pvSuratPernyataanDanKuasaAsetMmqSendiri.setOnClickListener(this);
        binding.pvSuratPernyataanDanKuasaAsetMmqPihakKetiga.setOnClickListener(this);
        binding.pvAkadRahn.setOnClickListener(this);
        binding.pvJadwalAngsuranRahn.setOnClickListener(this);
        binding.pvFormMutasiKantorBayar.setOnClickListener(this);
        binding.pvFotoScreenCaptureBuktiOtentikasiNasabahSaatAkad.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_foto_sk_pensiun:
                clicker = "skpensiun";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_foto_sk_pengangkatan:
                clicker = "skpengangkatan";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_foto_sk_terakhir:
                clicker = "skterakhir";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_foto_covernote_asuransi:
                clicker = "covernoteasuransi";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_sk:
                clicker = "sk";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_form_mutasi_kantor_bayar:
                clicker = "kantorbayar";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_kuasa:
                clicker = "kuasa";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_pernyataan:
                clicker = "pernyataan";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_sup:
                clicker = "sup";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_po:
                clicker = "po";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_akad_wakalah:
                clicker = "akadwakalah";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_akad_murabahah:
                clicker = "murabahah";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_jadwal_angsuran:
                clicker = "jadwalangsuran";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_terima_barang:
                clicker = "terimabarang";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_po_Ijarah:
                clicker = "poijarah";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_akad_wakalah_Ijarah:
                clicker = "wakalahijarah";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_akad_ijarah:
                clicker = "ijarah";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_angsuran_ujrah:
                clicker = "angsuranujrah";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_asset_mmq:
                clicker = "assetmmq";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_akad_mmq:
                clicker = "akadmmq";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_jadwal_pengambilalihan:
                clicker = "jadwalpengambilan";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_surat_pernyataan_dan_kuasa_aset_mmq_sendiri:
                clicker = "pihak1";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_surat_pernyataan_dan_kuasa_aset_mmq_pihak_ketiga:
                clicker = "pihak3";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_akad_rahn:
                clicker = "akadrahn";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_jadwal_angsuran_rahn:
                clicker = "angsuranrahn";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_mutasi:
                clicker = "mutasi";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_foto_proses_penandatanganan_akad:
                clicker = "ttdakad";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_foto_screen_bukti_otentikasi_nasabah_saat_akad:
                clicker = "fotoakad";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;



            // Dokumen Jaminan
            case R.id.pv_foto_sk_pensiun:
                if (Foto_SK_Pensiun.getFileName().substring(Foto_SK_Pensiun.getFileName().length() - 3, Foto_SK_Pensiun.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valskpensiun, Foto_SK_Pensiun.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", skpensiun);
                }
                break;
            case R.id.pv_foto_sk_pengangkatan:
                if (Foto_SK_Pengangkatan.getFileName().substring(Foto_SK_Pengangkatan.getFileName().length() - 3, Foto_SK_Pengangkatan.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valskpengangkatan, Foto_SK_Pengangkatan.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", skpengangkatan);
                }
                break;
            case R.id.pv_foto_sk_terakhir:
                if (Foto_SK_Terakhir.getFileName().substring(Foto_SK_Terakhir.getFileName().length() - 3, Foto_SK_Terakhir.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valskterakhir, Foto_SK_Terakhir.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", skterakhir);
                }
                break;
            // Asuransi Khusus
            case R.id.pv_foto_covernote_asuransi:
                if (Foto_Covernote_Asuransi.getFileName().substring(Foto_Covernote_Asuransi.getFileName().length() - 3, Foto_Covernote_Asuransi.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valcovernoteasuransi, Foto_Covernote_Asuransi.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", covernoteasuransi);
                }
                break;
            //Dokumen Produk

            case R.id.pv_sk:
                if (Tanda_Terima_Dokumen_SK.getFileName().substring(Tanda_Terima_Dokumen_SK.getFileName().length() - 3, Tanda_Terima_Dokumen_SK.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valsk, Tanda_Terima_Dokumen_SK.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", sk);
                }
                break;
            case R.id.pv_mutasi:
                if (Form_Mutasi_Kantor_Bayar_Taspen.getFileName().substring(Form_Mutasi_Kantor_Bayar_Taspen.getFileName().length() - 3, Form_Mutasi_Kantor_Bayar_Taspen.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valmutasi, Form_Mutasi_Kantor_Bayar_Taspen.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", mutasi);
                }
                break;
            case R.id.pv_kuasa:
                if (Surat_Kuasa_Pernyataan_Flagging.getFileName().substring(Surat_Kuasa_Pernyataan_Flagging.getFileName().length() - 3, Surat_Kuasa_Pernyataan_Flagging.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valkuasa, Surat_Kuasa_Pernyataan_Flagging.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", kuasa);
                }
                break;
            case R.id.pv_pernyataan:
                if (Surat_Pernyataan_Kuasa_Pembiayaan.getFileName().substring(Surat_Pernyataan_Kuasa_Pembiayaan.getFileName().length() - 3, Surat_Pernyataan_Kuasa_Pembiayaan.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valpernyataan, Surat_Pernyataan_Kuasa_Pembiayaan.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", pernyataan);
                }
                break;
            case R.id.pv_sup:
                if (SUP.getFileName().substring(SUP.getFileName().length() - 3, SUP.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valsup, SUP.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", sup);
                }
                break;

            // Tanda Tangan Akad
            case R.id.pv_foto_proses_penandatanganan_akad:
                if (Foto_Proses_Penandatanganan_Akad.getFileName().substring(Foto_Proses_Penandatanganan_Akad.getFileName().length() - 3, Foto_Proses_Penandatanganan_Akad.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valttdakad, Foto_Proses_Penandatanganan_Akad.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", ttdakad);
                }
                break;
            // Dokumen Murabahah
            case R.id.pv_po:
                if (Murabahah_Purchase_Order.getFileName().substring(Murabahah_Purchase_Order.getFileName().length() - 3, Murabahah_Purchase_Order.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valpo, Murabahah_Purchase_Order.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", po);
                }
                break;
            case R.id.pv_akad_wakalah:
                if (Murabahah_Akad_Wakalah.getFileName().substring(Murabahah_Akad_Wakalah.getFileName().length() - 3, Murabahah_Akad_Wakalah.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valwakalah, Murabahah_Akad_Wakalah.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", wakalah);
                }
                break;
            case R.id.pv_akad_murabahah:
                if (Murabahah_Akad_Murabahah.getFileName().substring(Murabahah_Akad_Murabahah.getFileName().length() - 3, Murabahah_Akad_Murabahah.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valmurabahah, Murabahah_Akad_Murabahah.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", murabahah);
                }
                break;
            case R.id.pv_jadwal_angsuran:
                if (Murabahah_Lampiran_Jadwal_Angsuran.getFileName().substring(Murabahah_Lampiran_Jadwal_Angsuran.getFileName().length() - 3, Murabahah_Lampiran_Jadwal_Angsuran.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valjadwalangsuran, Murabahah_Lampiran_Jadwal_Angsuran.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", jadwalangsuran);
                }
                break;
            case R.id.pv_terima_barang:
                if (Murabahah_Surat_Tanda_Terima_Barang.getFileName().substring(Murabahah_Surat_Tanda_Terima_Barang.getFileName().length() - 3, Murabahah_Surat_Tanda_Terima_Barang.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valterimabarang, Murabahah_Surat_Tanda_Terima_Barang.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", terimabarang);
                }
                break;
            // Dokumen Ijarah

            case R.id.pv_po_Ijarah:
                if (Ijarah_Purchase_Order.getFileName().substring(Ijarah_Purchase_Order.getFileName().length() - 3, Ijarah_Purchase_Order.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valpoijarah, Ijarah_Purchase_Order.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", poijarah);
                }
                break;
            case R.id.pv_akad_wakalah_Ijarah:
                if (Ijarah_Akad_Wakalah.getFileName().substring(Ijarah_Akad_Wakalah.getFileName().length() - 3, Ijarah_Akad_Wakalah.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valwakalahijarah, Ijarah_Akad_Wakalah.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", wakalahijarah);
                }
                break;
            case R.id.pv_akad_ijarah:
                if (Ijarah_Akad_Ijarah.getFileName().substring(Ijarah_Akad_Ijarah.getFileName().length() - 3, Ijarah_Akad_Ijarah.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valijarah, Ijarah_Akad_Ijarah.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", ijarah);
                }
                break;
            case R.id.pv_angsuran_ujrah:
                if (Ijarah_Lampiran_Jadwal_Angsuran.getFileName().substring(Ijarah_Lampiran_Jadwal_Angsuran.getFileName().length() - 3, Ijarah_Lampiran_Jadwal_Angsuran.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valangsuranujrah, Ijarah_Lampiran_Jadwal_Angsuran.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", angsuranujrah);
                }
                break;
            // Dokumen MMQ
            case R.id.pv_asset_mmq:
                if (MMQ_Laporan_Penilaian_Aset.getFileName().substring(MMQ_Laporan_Penilaian_Aset.getFileName().length() - 3, MMQ_Laporan_Penilaian_Aset.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valassetmmq, MMQ_Laporan_Penilaian_Aset.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", assetmmq);
                }
                break;
            case R.id.pv_akad_mmq:
                if (MMQ_Akad_MMQ.getFileName().substring(MMQ_Akad_MMQ.getFileName().length() - 3, MMQ_Akad_MMQ.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valakadmmq, MMQ_Akad_MMQ.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", akadmmq);
                }
                break;
            case R.id.pv_jadwal_pengambilalihan:
                if (MMQ_Lampiran_Jadwal_Pengambil_Alihan.getFileName().substring(MMQ_Lampiran_Jadwal_Pengambil_Alihan.getFileName().length() - 3, MMQ_Lampiran_Jadwal_Pengambil_Alihan.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valjadwalpengambilan, MMQ_Lampiran_Jadwal_Pengambil_Alihan.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", jadwalpengambilan);
                }
                break;
            case R.id.pv_surat_pernyataan_dan_kuasa_aset_mmq_sendiri:
                if (MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.getFileName().substring(MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.getFileName().length() - 3, MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valpihak1, MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", pihak1);
                }
                break;
            case R.id.pv_surat_pernyataan_dan_kuasa_aset_mmq_pihak_ketiga:
                if (MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.getFileName().substring(MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.getFileName().length() - 3, MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valpihak3, MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", pihak3);
                }
                break;
            // Dokumen Rahn

            case R.id.pv_akad_rahn:
                if (Rahn_Akad_Rahn.getFileName().substring(Rahn_Akad_Rahn.getFileName().length() - 3, Rahn_Akad_Rahn.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valakadrahn, Rahn_Akad_Rahn.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", akadrahn);
                }
                break;
            case R.id.pv_jadwal_angsuran_rahn:
                if (Rahn_Lampiran_Jadwal_Angsuran.getFileName().substring(Rahn_Lampiran_Jadwal_Angsuran.getFileName().length() - 3, Rahn_Lampiran_Jadwal_Angsuran.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valangsuranrahn, Rahn_Lampiran_Jadwal_Angsuran.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", angsuranrahn);
                }
                break;

            // FORM PEMBIAYAAN
            case R.id.pv_form_mutasi_kantor_bayar:
                if (Form_Mutasi_Kantor_Bayar.getFileName().substring(Form_Mutasi_Kantor_Bayar.getFileName().length() - 3, Form_Mutasi_Kantor_Bayar.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valkantorbayar, Form_Mutasi_Kantor_Bayar.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", kantorbayar);
                }
                break;

            case R.id.pv_foto_screen_capture_bukti_otentikasi_nasabah_saat_akad:
                if (Foto_Bukti_Otentikasi_Nasabah.getFileName().substring(Foto_Bukti_Otentikasi_Nasabah.getFileName().length() - 3, Foto_Bukti_Otentikasi_Nasabah.getFileName().length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valfotoakad, Foto_Bukti_Otentikasi_Nasabah.getFileName());
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", fotoakad);
                }
                break;


            case R.id.btn_upload_dokumen1:
            case R.id.iv_upload_dokumen1:
                clicker = "dokumenA";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_upload_dokumen2:
            case R.id.iv_upload_dokumen2:
                clicker = "dokumenB";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_upload_dokumen3:
            case R.id.iv_upload_dokumen3:
                clicker = "dokumenC";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_upload_dokumen4:
            case R.id.iv_upload_dokumen4:
                clicker = "dokumenD";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.btn_upload_dokumen5:
            case R.id.iv_upload_dokumen5:
                clicker = "dokumenE";
                BSUploadFile.displayWithTitle(ActivityUploadDokumen.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.ll_tambahan_dokumen:
            case R.id.btn_tambahan_dokumen:
                addImg += 1;
                AppUtil.logSecure("LogInt", addImg.toString());
                if (addImg == 1) {
                    binding.cvUploadDokumen2.setVisibility(View.VISIBLE);
                    binding.tvUploadDokumen2.setVisibility(View.VISIBLE);
                    binding.tfNamaDokumen2.setVisibility(View.VISIBLE);
                    binding.tfKeteranganDokumen2.setVisibility(View.VISIBLE);
                    binding.tfUploadDokumen2.setVisibility(View.VISIBLE);
                    binding.etNamaDokumen2.setVisibility(View.VISIBLE);
                    binding.etKeteranganDokumen2.setVisibility(View.VISIBLE);
                    binding.ivUploadDokumen2.setVisibility(View.VISIBLE);
                    binding.btnUploadDokumen2.setVisibility(View.VISIBLE);
                    binding.btnDeleteDokumen.setVisibility(View.VISIBLE);
                    binding.llDeleteDokumen.setVisibility(View.VISIBLE);
                } else if (addImg == 2) {
                    binding.cvUploadDokumen3.setVisibility(View.VISIBLE);
                    binding.tvUploadDokumen3.setVisibility(View.VISIBLE);
                    binding.tfNamaDokumen3.setVisibility(View.VISIBLE);
                    binding.tfKeteranganDokumen3.setVisibility(View.VISIBLE);
                    binding.tfUploadDokumen3.setVisibility(View.VISIBLE);
                    binding.etNamaDokumen3.setVisibility(View.VISIBLE);
                    binding.etKeteranganDokumen3.setVisibility(View.VISIBLE);
                    binding.ivUploadDokumen3.setVisibility(View.VISIBLE);
                    binding.btnUploadDokumen3.setVisibility(View.VISIBLE);
                } else if (addImg == 3) {
                    binding.cvUploadDokumen4.setVisibility(View.VISIBLE);
                    binding.tvUploadDokumen4.setVisibility(View.VISIBLE);
                    binding.tfNamaDokumen4.setVisibility(View.VISIBLE);
                    binding.tfKeteranganDokumen4.setVisibility(View.VISIBLE);
                    binding.tfUploadDokumen4.setVisibility(View.VISIBLE);
                    binding.etNamaDokumen4.setVisibility(View.VISIBLE);
                    binding.etKeteranganDokumen4.setVisibility(View.VISIBLE);
                    binding.ivUploadDokumen4.setVisibility(View.VISIBLE);
                    binding.btnUploadDokumen4.setVisibility(View.VISIBLE);
                } else {
                    binding.cvUploadDokumen5.setVisibility(View.VISIBLE);
                    binding.tvUploadDokumen5.setVisibility(View.VISIBLE);
                    binding.tfNamaDokumen5.setVisibility(View.VISIBLE);
                    binding.tfKeteranganDokumen5.setVisibility(View.VISIBLE);
                    binding.tfUploadDokumen5.setVisibility(View.VISIBLE);
                    binding.etNamaDokumen5.setVisibility(View.VISIBLE);
                    binding.etKeteranganDokumen5.setVisibility(View.VISIBLE);
                    binding.ivUploadDokumen5.setVisibility(View.VISIBLE);
                    binding.btnUploadDokumen5.setVisibility(View.VISIBLE);
                    binding.btnTambahanDokumen.setVisibility(View.GONE);
                    binding.llTambahanDokumen.setVisibility(View.GONE);
                }
                break;
            case R.id.ll_delete_dokumen:
            case R.id.btn_delete_dokumen:
                addImg -= 1;
                AppUtil.logSecure("LogInt", addImg.toString());
                Drawable res = getDrawable(R.mipmap.ico_img_for_upload);
                if (addImg == 3) {
                    if (DokumenUmum.size() == 5) {
                        DokumenUmum.remove(4);
                        binding.etNamaDokumen5.setText("");
                        binding.etKeteranganDokumen5.setText("");
                        binding.ivUploadDokumen5.setImageDrawable(res);
                    } else {
                        binding.etNamaDokumen5.setText("");
                        binding.etKeteranganDokumen5.setText("");
                        binding.ivUploadDokumen5.setImageDrawable(res);
                    }
                    binding.cvUploadDokumen5.setVisibility(View.GONE);
                    binding.tvUploadDokumen5.setVisibility(View.GONE);
                    binding.tfNamaDokumen5.setVisibility(View.GONE);
                    binding.tfKeteranganDokumen5.setVisibility(View.GONE);
                    binding.tfUploadDokumen5.setVisibility(View.GONE);
                    binding.etNamaDokumen5.setVisibility(View.GONE);
                    binding.etKeteranganDokumen5.setVisibility(View.GONE);
                    binding.ivUploadDokumen5.setVisibility(View.GONE);
                    binding.btnUploadDokumen5.setVisibility(View.GONE);
                    binding.ivUploadDokumen5.setOnClickListener(this);
                    binding.btnTambahanDokumen.setVisibility(View.GONE);
                    binding.llTambahanDokumen.setVisibility(View.GONE);
                    binding.btnTambahanDokumen.setVisibility(View.VISIBLE);
                    binding.llTambahanDokumen.setVisibility(View.VISIBLE);
                } else if (addImg == 2) {
                    if (DokumenUmum.size() == 4) {
                        DokumenUmum.remove(3);
                        binding.etNamaDokumen4.setText("");
                        binding.etKeteranganDokumen4.setText("");
                        binding.ivUploadDokumen4.setImageDrawable(res);
                    } else {
                        binding.etNamaDokumen4.setText("");
                        binding.etKeteranganDokumen4.setText("");
                        binding.ivUploadDokumen4.setImageDrawable(res);
                    }
                    binding.cvUploadDokumen4.setVisibility(View.GONE);
                    binding.tvUploadDokumen4.setVisibility(View.GONE);
                    binding.tfNamaDokumen4.setVisibility(View.GONE);
                    binding.tfKeteranganDokumen4.setVisibility(View.GONE);
                    binding.tfUploadDokumen4.setVisibility(View.GONE);
                    binding.etNamaDokumen4.setVisibility(View.GONE);
                    binding.etKeteranganDokumen4.setVisibility(View.GONE);
                    binding.ivUploadDokumen4.setVisibility(View.GONE);
                    binding.btnUploadDokumen4.setVisibility(View.GONE);
                    binding.ivUploadDokumen4.setOnClickListener(this);
                } else if (addImg == 1) {
                    if (DokumenUmum.size() == 3) {
                        DokumenUmum.remove(2);
                        binding.etNamaDokumen3.setText("");
                        binding.etKeteranganDokumen3.setText("");
                        binding.ivUploadDokumen3.setImageDrawable(res);
                    } else {
                        binding.etNamaDokumen3.setText("");
                        binding.etKeteranganDokumen3.setText("");
                        binding.ivUploadDokumen3.setImageDrawable(res);
                    }
                    binding.cvUploadDokumen3.setVisibility(View.GONE);
                    binding.tvUploadDokumen3.setVisibility(View.GONE);
                    binding.tfNamaDokumen3.setVisibility(View.GONE);
                    binding.tfKeteranganDokumen3.setVisibility(View.GONE);
                    binding.tfUploadDokumen3.setVisibility(View.GONE);
                    binding.etNamaDokumen3.setVisibility(View.GONE);
                    binding.etKeteranganDokumen3.setVisibility(View.GONE);
                    binding.ivUploadDokumen3.setVisibility(View.GONE);
                    binding.btnUploadDokumen3.setVisibility(View.GONE);
                    binding.ivUploadDokumen3.setOnClickListener(this);
                } else {
                    if (DokumenUmum.size() == 2) {
                        DokumenUmum.remove(1);
                        binding.etNamaDokumen2.setText("");
                        binding.etKeteranganDokumen2.setText("");
                        binding.ivUploadDokumen2.setImageDrawable(res);
                    } else {
                        binding.etNamaDokumen2.setText("");
                        binding.etKeteranganDokumen2.setText("");
                        binding.ivUploadDokumen2.setImageDrawable(res);
                    }
                    binding.cvUploadDokumen2.setVisibility(View.GONE);
                    binding.tvUploadDokumen2.setVisibility(View.GONE);
                    binding.tfNamaDokumen2.setVisibility(View.GONE);
                    binding.tfKeteranganDokumen2.setVisibility(View.GONE);
                    binding.tfUploadDokumen2.setVisibility(View.GONE);
                    binding.etNamaDokumen2.setVisibility(View.GONE);
                    binding.etKeteranganDokumen2.setVisibility(View.GONE);
                    binding.ivUploadDokumen2.setVisibility(View.GONE);
                    binding.btnUploadDokumen2.setVisibility(View.GONE);
                    binding.ivUploadDokumen2.setOnClickListener(this);
                    binding.btnDeleteDokumen.setVisibility(View.GONE);
                    binding.llDeleteDokumen.setVisibility(View.GONE);
                }
                break;
            case R.id.btn_cek_lngp:
            case R.id.ll_btn_send:
            case R.id.btn_send:
                Validate();
                sendData();
            default:
                break;
        }
    }

    private void Validate() {

    }

    private void sendData() {
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        UploadDokumen upD = new UploadDokumen();
        UpdateUploadDokumen req = new UpdateUploadDokumen();
        req.setApplicationId(idAplikasi);
        req.setUid(String.valueOf(appPreferences.getUid()));
        upD.setFormMutasKantorBayar(Form_Mutasi_Kantor_Bayar);
        upD.setFormMutasiKantorBayarTaspen(Form_Mutasi_Kantor_Bayar_Taspen);
        upD.setFotoBuktiOtentikasiNasabah(Foto_Bukti_Otentikasi_Nasabah);
        upD.setFotoCovernoteAsuransi(Foto_Covernote_Asuransi);
        upD.setFotoProsesPenandatangananAkad(Foto_Proses_Penandatanganan_Akad);
        upD.setFotoSKPengangkatan(Foto_SK_Pengangkatan);
        upD.setFotoSKPensiun(Foto_SK_Pensiun);
        upD.setFotoSKTerakhir(Foto_SK_Terakhir);
        upD.setIjarahAkadIjarah(Ijarah_Akad_Ijarah);
        upD.setIjarahAkadWakalah(Ijarah_Akad_Wakalah);
        upD.setIjarahLampiranJadwalAngsuran(Ijarah_Lampiran_Jadwal_Angsuran);
        upD.setIjarahPurchaseOrder(Ijarah_Purchase_Order);
        upD.setmMQAkadMMQ(MMQ_Akad_MMQ);
        upD.setmMQLampiranJadwalPengambilAlihan(MMQ_Lampiran_Jadwal_Pengambil_Alihan);
        upD.setmMQLaporanPenilaianAset(MMQ_Laporan_Penilaian_Aset);
        upD.setmMQSuratPernyataanKuasaAsetKetiga(MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga);
        upD.setmMQSuratPernyataanKuasaAsetSendiri(MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri);
        upD.setMurabahahAkadMurabahah(Murabahah_Akad_Murabahah);
        upD.setMurabahahAkadWakalah(Murabahah_Akad_Wakalah);
        upD.setMurabahahLampiranJadwalAngsuran(Murabahah_Lampiran_Jadwal_Angsuran);
        upD.setMurabahahPurchaseOrder(Murabahah_Purchase_Order);
        upD.setMurabahahSuratTandaTerimaBarang(Murabahah_Surat_Tanda_Terima_Barang);
        upD.setRahnAkadRahn(Rahn_Akad_Rahn);
        upD.setRahnLampiranJadwalAngsuran(Rahn_Lampiran_Jadwal_Angsuran);
        upD.setSuratKuasaPernyataanFlagging(Surat_Kuasa_Pernyataan_Flagging);
        upD.setSuratPernyataanKuasaPembiayaan(Surat_Pernyataan_Kuasa_Pembiayaan);
        upD.setTandaTerimaDokumenSK(Tanda_Terima_Dokumen_SK);
        upD.setDokumenUmum(DokumenUmum);
        upD.setSup(SUP);
        req.setUploadDokumen(upD);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().UploadDokumenUmum(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {

                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            CustomDialog.DialogSuccess(ActivityUploadDokumen.this, "Success!", "Update Data Sukses", ActivityUploadDokumen.this);

                        } else {
                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public void onBackPressed() {
        CustomDialog.DialogBackpress(ActivityUploadDokumen.this);
    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        switch (idMenu) {
            case "Take Photo":
                tipeFile = "png";
                if (clicker.equalsIgnoreCase("dokumenA")) {
                    openCamera(IntDokumenA, "dokumenA");
                } else if (clicker.equalsIgnoreCase("dokumenB")) {
                    openCamera(IntDokumenB, "dokumenB");
                } else if (clicker.equalsIgnoreCase("dokumenC")) {
                    openCamera(IntDokumenC, "dokumenC");
                } else if (clicker.equalsIgnoreCase("dokumenD")) {
                    openCamera(IntDokumenD, "dokumenD");
                } else if (clicker.equalsIgnoreCase("dokumenE")) {
                    openCamera(IntDokumenE, "dokumenE");
                }
                // Dokumen jaminan
                if (clicker.equalsIgnoreCase("skpensiun")) {
                    openCamera(Intskpensiun, "skpensiun");
                } else if (clicker.equalsIgnoreCase("skpengangkatan")) {
                    openCamera(Intskpengangkatan, "skpengangkatan");
                } else if (clicker.equalsIgnoreCase("skterakhir")) {
                    openCamera(Intskterakhir, "skterakhir");
                }
                // Asuransi Khusus
                if (clicker.equalsIgnoreCase("covernoteasuransi")) {
                    openCamera(Intcovernoteasuransi, "covernoteasuransi");
                }
                // Dokumen Produk

                if (clicker.equalsIgnoreCase("sk")) {
                    openCamera(Intsk, "sk");
                } else if (clicker.equalsIgnoreCase("mutasi")) {
                    openCamera(Intmutasi, "mutasi");
                } else if (clicker.equalsIgnoreCase("kuasa")) {
                    openCamera(Intkuasa, "kuasa");
                } else if (clicker.equalsIgnoreCase("pernyataan")) {
                    openCamera(Intpernyataan, "pernyataan");
                } else if (clicker.equalsIgnoreCase("sup")) {
                    openCamera(Intsup, "sup");
                }
                // Tanda Tangan Akad
                if (clicker.equalsIgnoreCase("ttdakad")) {
                    openCamera(Intttdakad, "ttdakad");
                }
                // Dokumen Murabahah

                if (clicker.equalsIgnoreCase("po")) {
                    openCamera(Intpo, "po");
                } else if (clicker.equalsIgnoreCase("akadwakalah")) {
                    openCamera(Intwakalah, "wakalah");
                } else if (clicker.equalsIgnoreCase("murabahah")) {
                    openCamera(Intmurabahah, "murabahah");
                } else if (clicker.equalsIgnoreCase("jadwalangsuran")) {
                    openCamera(Intjadwalangsuran, "jadwalangsuran");
                } else if (clicker.equalsIgnoreCase("terimabarang")) {
                    openCamera(Intterimabarang, "terimabarang");
                }
                // Dokumen Ijarah

                if (clicker.equalsIgnoreCase("poijarah")) {
                    openCamera(Intpoijarah, "poijarah");
                } else if (clicker.equalsIgnoreCase("wakalahijarah")) {
                    openCamera(Intwakalahijarah, "wakalahijarah");
                } else if (clicker.equalsIgnoreCase("ijarah")) {
                    openCamera(Intijarah, "ijarah");
                } else if (clicker.equalsIgnoreCase("angsuranujrah")) {
                    openCamera(Intangsuranujrah, "angsuranujrah");
                }
                // Dokumen MMQ

                if (clicker.equalsIgnoreCase("assetmmq")) {
                    openCamera(Intassetmmq, "assetmmq");
                } else if (clicker.equalsIgnoreCase("akadmmq")) {
                    openCamera(Intakadmmq, "akadmmq");
                } else if (clicker.equalsIgnoreCase("jadwalpengambilan")) {
                    openCamera(Intjadwalpengambilan, "jadwalpengambilan");
                } else if (clicker.equalsIgnoreCase("pihak1")) {
                    openCamera(Intpihak1, "pihak1");
                } else if (clicker.equalsIgnoreCase("pihak3")) {
                    openCamera(Intpihak3, "pihak3");
                }
                // Dokumen Rahn

                if (clicker.equalsIgnoreCase("akadrahn")) {
                    openCamera(Intakadrahn, "akadrahn");
                } else if (clicker.equalsIgnoreCase("angsuranrahn")) {
                    openCamera(Intangsuranrahn, "angsuranrahn");
                }
                // Dokumen Pembiayaan

                if (clicker.equalsIgnoreCase("kantorbayar")) {
                    openCamera(Intkantorbayar, "kantorbayar");
                } else if (clicker.equalsIgnoreCase("fotoakad")) {
                    openCamera(Intfotoakad, "fotoakad");
                }
                break;
            case "Pick Photo":
                tipeFile = "png";
                if (clicker.equalsIgnoreCase("dokumenA")) {
                    openGalery(IntDokumenA);
                } else if (clicker.equalsIgnoreCase("dokumenB")) {
                    openGalery(IntDokumenB);
                } else if (clicker.equalsIgnoreCase("dokumenC")) {
                    openGalery(IntDokumenC);
                } else if (clicker.equalsIgnoreCase("dokumenD")) {
                    openGalery(IntDokumenD);
                } else if (clicker.equalsIgnoreCase("dokumenE")) {
                    openGalery(IntDokumenE);
                }
                // Dokumen Jaminan
                if (clicker.equalsIgnoreCase("skpensiun")) {
                    openGalery(Intskpensiun);
                } else if (clicker.equalsIgnoreCase("skpengangkatan")) {
                    openGalery(Intskpengangkatan);
                } else if (clicker.equalsIgnoreCase("skterakhir")) {
                    openGalery(Intskterakhir);
                }
                // Asuransi Khusus
                if (clicker.equalsIgnoreCase("covernoteasuransi")) {
                    openGalery(Intcovernoteasuransi);
                }
                // Dokumen Produk

                if (clicker.equalsIgnoreCase("sk")) {
                    openGalery(Intsk);
                } else if (clicker.equalsIgnoreCase("mutasi")) {
                    openGalery(Intmutasi);
                } else if (clicker.equalsIgnoreCase("kuasa")) {
                    openGalery(Intkuasa);
                } else if (clicker.equalsIgnoreCase("pernyataan")) {
                    openGalery(Intpernyataan);
                } else if (clicker.equalsIgnoreCase("sup")) {
                    openGalery(Intsup);
                }
                // Tanda Tangan Akad
                if (clicker.equalsIgnoreCase("ttdakad")) {
                    openGalery(Intttdakad);
                }
                // Dokumen Murabahah

                if (clicker.equalsIgnoreCase("po")) {
                    openGalery(Intpo);
                } else if (clicker.equalsIgnoreCase("akadwakalah")) {
                    openGalery(Intwakalah);
                } else if (clicker.equalsIgnoreCase("murabahah")) {
                    openGalery(Intmurabahah);
                } else if (clicker.equalsIgnoreCase("jadwalangsuran")) {
                    openGalery(Intjadwalangsuran);
                } else if (clicker.equalsIgnoreCase("terimabarang")) {
                    openGalery(Intterimabarang);
                }
                // Dokumen Ijarah

                if (clicker.equalsIgnoreCase("poijarah")) {
                    openGalery(Intpoijarah);
                } else if (clicker.equalsIgnoreCase("wakalahijarah")) {
                    openGalery(Intwakalahijarah);
                } else if (clicker.equalsIgnoreCase("ijarah")) {
                    openGalery(Intijarah);
                } else if (clicker.equalsIgnoreCase("angsuranujrah")) {
                    openGalery(Intangsuranujrah);
                }
                // Dokumen MMQ

                if (clicker.equalsIgnoreCase("assetmmq")) {
                    openGalery(Intassetmmq);
                } else if (clicker.equalsIgnoreCase("akadmmq")) {
                    openGalery(Intakadmmq);
                } else if (clicker.equalsIgnoreCase("jadwalpengambilan")) {
                    openGalery(Intjadwalpengambilan);
                } else if (clicker.equalsIgnoreCase("pihak1")) {
                    openGalery(Intpihak1);
                } else if (clicker.equalsIgnoreCase("pihak3")) {
                    openGalery(Intpihak3);
                }
                //Dokumen Rahn

                if (clicker.equalsIgnoreCase("akadrahn")) {
                    openGalery(Intakadrahn);
                } else if (clicker.equalsIgnoreCase("angsuranrahn")) {
                    openGalery(Intangsuranrahn);
                }
                //Dokumen Rahn

                if (clicker.equalsIgnoreCase("kantorbayar")) {
                    openGalery(Intkantorbayar);
                } else if (clicker.equalsIgnoreCase("fotoakad")) {
                    openGalery(Intfotoakad);
                }
                break;
            case "Pick File":
                tipeFile = "pdf";
                if (clicker.equalsIgnoreCase("dokumenA")) {
                    openFile(IntDokumenA);
                } else if (clicker.equalsIgnoreCase("dokumenB")) {
                    openFile(IntDokumenB);
                } else if (clicker.equalsIgnoreCase("dokumenC")) {
                    openFile(IntDokumenC);
                } else if (clicker.equalsIgnoreCase("dokumenD")) {
                    openFile(IntDokumenD);
                } else if (clicker.equalsIgnoreCase("dokumenE")) {
                    openFile(IntDokumenE);
                }
                // Dokumen Jaminan
                if (clicker.equalsIgnoreCase("skpensiun")) {
                    openFile(Intskpensiun);
                } else if (clicker.equalsIgnoreCase("skpengangkatan")) {
                    openFile(Intskpengangkatan);
                } else if (clicker.equalsIgnoreCase("skterakhir")) {
                    openFile(Intskterakhir);
                }
                // Asuransi Khusus
                if (clicker.equalsIgnoreCase("covernoteasuransi")) {
                    openFile(Intcovernoteasuransi);
                }
                // Asuransi Khusus

                if (clicker.equalsIgnoreCase("sk")) {
                    openFile(Intsk);
                } else if (clicker.equalsIgnoreCase("mutasi")) {
                    openFile(Intmutasi);
                } else if (clicker.equalsIgnoreCase("kuasa")) {
                    openFile(Intkuasa);
                } else if (clicker.equalsIgnoreCase("pernyataan")) {
                    openFile(Intpernyataan);
                } else if (clicker.equalsIgnoreCase("sup")) {
                    openFile(Intsup);
                }
                // Tanda Tangan Akad

                if (clicker.equalsIgnoreCase("ttdakad")) {
                    openFile(Intttdakad);
                }
                // Dokumen Murabahah

                if (clicker.equalsIgnoreCase("po")) {
                    openFile(Intpo);
                } else if (clicker.equalsIgnoreCase("akadwakalah")) {
                    openFile(Intwakalah);
                } else if (clicker.equalsIgnoreCase("murabahah")) {
                    openFile(Intmurabahah);
                } else if (clicker.equalsIgnoreCase("jadwalangsuran")) {
                    openFile(Intjadwalangsuran);
                } else if (clicker.equalsIgnoreCase("terimabarang")) {
                    openFile(Intterimabarang);
                }
                // Dokumen Ijarah

                if (clicker.equalsIgnoreCase("poijarah")) {
                    openFile(Intpoijarah);
                } else if (clicker.equalsIgnoreCase("wakalahijarah")) {
                    openFile(Intwakalahijarah);
                } else if (clicker.equalsIgnoreCase("ijarah")) {
                    openFile(Intijarah);
                } else if (clicker.equalsIgnoreCase("angsuranujrah")) {
                    openFile(Intangsuranujrah);
                }
                // Dokumen MMQ

                if (clicker.equalsIgnoreCase("assetmmq")) {
                    openFile(Intassetmmq);
                } else if (clicker.equalsIgnoreCase("akadmmq")) {
                    openFile(Intakadmmq);
                } else if (clicker.equalsIgnoreCase("jadwalpengambilan")) {
                    openFile(Intjadwalpengambilan);
                } else if (clicker.equalsIgnoreCase("pihak1")) {
                    openFile(Intpihak1);
                } else if (clicker.equalsIgnoreCase("pihak3")) {
                    openFile(Intpihak3);
                }
                // Dokumen Rahn

                if (clicker.equalsIgnoreCase("akadrahn")) {
                    openFile(Intakadrahn);
                } else if (clicker.equalsIgnoreCase("angsuranrahn")) {
                    openFile(Intangsuranrahn);
                }
                // Dokumen Rahn

                if (clicker.equalsIgnoreCase("kantorbayar")) {
                    openFile(Intkantorbayar);
                } else if (clicker.equalsIgnoreCase("fotoakad")) {
                    openFile(Intfotoakad);
                }
                break;
        }

    }

    private void openCamera(int cameraCode, String namaFoto) {
        checkCameraPermission(cameraCode, namaFoto);
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

    public void checkCameraPermission(int cameraCode, String namaFoto) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);
        } else {
            Uri outputFileUri = getCaptureImageOutputUri(namaFoto);
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(captureIntent, cameraCode);
        }
    }

    private Uri getCaptureImageOutputUri(String namaFoto) {
        Uri outputFileUri = null;
        File getImage = this.getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                outputFileUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), namaFoto + ".png"));
            } else {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), namaFoto + ".png"));
            }
        }
        return outputFileUri;
    }


    // Legacy
//    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
//        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
//        if (resultCode != 0) {
//            switch (requestCode) {
//                case IntDokumenA:
//                    setDataImage(UridokumenA, dokumenA, binding.ivUploadDokumen1, imageReturnedIntent, "dokumenA");
//                    break;
//                case IntDokumenB:
//                    setDataImage(UridokumenB, dokumenB, binding.ivUploadDokumen2, imageReturnedIntent, "dokumenB");
//                    break;
//                case IntDokumenC:
//                    setDataImage(UridokumenC, dokumenC, binding.ivUploadDokumen3, imageReturnedIntent, "dokumenC");
//                    break;
//                case IntDokumenD:
//                    setDataImage(UridokumenD, dokumenD, binding.ivUploadDokumen4, imageReturnedIntent, "dokumenD");
//                    break;
//                case IntDokumenE:
//                    setDataImage(UridokumenE, dokumenE, binding.ivUploadDokumen5, imageReturnedIntent, "dokumenE");
//                    break;
//                // Dokumen Jaminan
//                case Intskpensiun:
//                    setDataImage(Uriskpensiun, skpensiun, null, imageReturnedIntent, "skpensiun");
//                    break;
//                case Intskpengangkatan:
//                    setDataImage(Uriskpengangkatan, skpengangkatan, null, imageReturnedIntent, "skpengangkatan");
//                    break;
//                case Intskterakhir:
//                    setDataImage(Uriskterakhir, skterakhir, null, imageReturnedIntent, "skterakhir");
//                    break;
//                // Asuransi Khusus
//                case Intcovernoteasuransi:
//                    setDataImage(Uricovernoteasuransi, covernoteasuransi, null, imageReturnedIntent, "covernoteasuransi");
//                    break;
//                // Dokumen Produk
//                case Intsk:
//                    setDataImage(Urisk, sk, null, imageReturnedIntent, "sk");
//                    break;
//                case Intmutasi:
//                    setDataImage(Urimutasi, mutasi, null, imageReturnedIntent, "mutasi");
//                    break;
//                case Intkuasa:
//                    setDataImage(Urikuasa, kuasa, null, imageReturnedIntent, "kuasa");
//                    break;
//                case Intpernyataan:
//                    setDataImage(Uripernyataan, pernyataan, null, imageReturnedIntent, "pernyataan");
//                    break;
//                case Intsup:
//                    setDataImage(Urisup, sup, null, imageReturnedIntent, "sup");
//                    break;
//                // Tanda Tangan Akad
//                case Intttdakad:
//                    setDataImage(Urittdakad, ttdakad, null, imageReturnedIntent, "ttdakad");
//                    break;
//                // Dokumen Murabahah
//                case Intpo:
//                    setDataImage(Uripo, po, null, imageReturnedIntent, "po");
//                    break;
//                case Intwakalah:
//                    setDataImage(Uriwakalah, wakalah, null, imageReturnedIntent, "wakalah");
//                    break;
//                case Intmurabahah:
//                    setDataImage(Urimurabahah, murabahah, null, imageReturnedIntent, "murabahah");
//                    break;
//                case Intjadwalangsuran:
//                    setDataImage(Urijadwalangsuran, jadwalangsuran, null, imageReturnedIntent, "jadwalangsuran");
//                    break;
//                case Intterimabarang:
//                    setDataImage(Uriterimabarang, terimabarang, null, imageReturnedIntent, "terimabarang");
//                    break;
//                // Dokumen Ijarah
//                case Intpoijarah:
//                    setDataImage(Uripoijarah, poijarah, null, imageReturnedIntent, "poijarah");
//                    break;
//                case Intwakalahijarah:
//                    setDataImage(Uriwakalahijarah, wakalahijarah, null, imageReturnedIntent, "wakalahijarah");
//                    break;
//                case Intijarah:
//                    setDataImage(Uriijarah, ijarah, null, imageReturnedIntent, "ijarah");
//                    break;
//                case Intangsuranujrah:
//                    setDataImage(Uriangsuranujrah, angsuranujrah, null, imageReturnedIntent, "angsuranujrah");
//                    break;
//                // Dokumen MMQ
//
//                case Intassetmmq:
//                    setDataImage(Uriassetmmq, assetmmq, null, imageReturnedIntent, "assetmmq");
//                    break;
//                case Intakadmmq:
//                    setDataImage(Uriakadmmq, akadmmq, null, imageReturnedIntent, "akadmmq");
//                    break;
//                case Intjadwalpengambilan:
//                    setDataImage(Urijadwalpengambilan, jadwalpengambilan, null, imageReturnedIntent, "jadwalpengambilan");
//                    break;
//                case Intpihak1:
//                    setDataImage(Uripihak1, pihak1, null, imageReturnedIntent, "pihak1");
//                    break;
//                case Intpihak3:
//                    setDataImage(Uripihak3, pihak3, null, imageReturnedIntent, "pihak3");
//                    break;
//                // Dokumen MMQ
//
//                case Intakadrahn:
//                    setDataImage(Uriakadrahn, akadrahn, null, imageReturnedIntent, "akadrahn");
//                    break;
//                case Intangsuranrahn:
//                    setDataImage(Uriangsuranrahn, angsuranrahn, null, imageReturnedIntent, "angsuranrahn");
//                    break;
//                // Dokumen Pembiayaan
//
//                case Intkantorbayar:
//                    setDataImage(Urikantorbayar, kantorbayar, null, imageReturnedIntent, "kantorbayar");
//                    break;
//                case Intfotoakad:
//                    setDataImage(Urifotoakad, fotoakad, null, imageReturnedIntent, "fotoakad");
//                    break;
//            }
//        }
//    }

    public Uri getPickImageResultUri(Intent data, String namaFoto) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri(namaFoto) : data.getData();
    }

//    private void setDataImage(Uri uri, Bitmap bitmap, ImageView iv, Intent data, String namaFoto) {
//        if (getPickImageResultUri(data, namaFoto) != null) {
//            uri = getPickImageResultUri(data, namaFoto);
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//                bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
//                bitmap = AppUtil.rotateImageIfRequired(this, bitmap, uri);
//
//                if (clicker.equalsIgnoreCase("dokumenA")) {
//                    dokumenA = bitmap;
//                    ReqDocumentUmum doc = new ReqDocumentUmum();
//                    doc.setFilename("dokumenA.png");
//                    doc.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    doc.setKeteranganDokumen(binding.etKeteranganDokumen1.getText().toString());
//                    doc.setNamaDokumen(binding.etNamaDokumen1.getText().toString());
//                    if (DokumenUmum.size() == 0) {
//                        DokumenUmum.add(0, doc);
//                    } else {
//                        DokumenUmum.get(0).setFilename("dokumenA.png");
//                        DokumenUmum.get(0).setImg(valDokumenA);
//                        DokumenUmum.get(0).setKeteranganDokumen(binding.etKeteranganDokumen1.getText().toString());
//                        DokumenUmum.get(0).setNamaDokumen(binding.etNamaDokumen1.getText().toString());
//                    }
//                    valDokumenA = AppUtil.encodeImageTobase64(bitmap);
//                    iv.setImageBitmap(bitmap);
//                } else if (clicker.equalsIgnoreCase("dokumenB")) {
//                    dokumenB = bitmap;
//                    ReqDocumentUmum doc = new ReqDocumentUmum();
//                    doc.setFilename("dokumenB.png");
//                    doc.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    doc.setKeteranganDokumen(binding.etKeteranganDokumen2.getText().toString());
//                    doc.setNamaDokumen(binding.etNamaDokumen2.getText().toString());
//                    if (DokumenUmum.size() < 1) {
//                        AppUtil.notiferror(this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
//                    } else {
//                        if (DokumenUmum.size() <= 1) {
//                            DokumenUmum.add(1, doc);
//                        } else {
//                            DokumenUmum.get(1).setFilename("dokumenB.png");
//                            DokumenUmum.get(1).setImg(valDokumenB);
//                            DokumenUmum.get(1).setKeteranganDokumen(binding.etKeteranganDokumen2.getText().toString());
//                            DokumenUmum.get(1).setNamaDokumen(binding.etNamaDokumen2.getText().toString());
//                        }
//                        iv.setImageBitmap(bitmap);
//                        valDokumenB = AppUtil.encodeImageTobase64(bitmap);
//                    }
//                } else if (clicker.equalsIgnoreCase("dokumenC")) {
//                    dokumenC = bitmap;
//                    ReqDocumentUmum doc = new ReqDocumentUmum();
//                    doc.setFilename("dokumenC.png");
//                    doc.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    doc.setKeteranganDokumen(binding.etKeteranganDokumen3.getText().toString());
//                    doc.setNamaDokumen(binding.etNamaDokumen3.getText().toString());
//                    if (DokumenUmum.size() < 2) {
//                        AppUtil.notiferror(this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
//                    } else {
//                        if (DokumenUmum.size() <= 2) {
//                            DokumenUmum.add(2, doc);
//                        } else {
//                            DokumenUmum.get(2).setFilename("dokumenC.png");
//                            DokumenUmum.get(2).setImg(valDokumenC);
//                            DokumenUmum.get(2).setKeteranganDokumen(binding.etKeteranganDokumen3.getText().toString());
//                            DokumenUmum.get(2).setNamaDokumen(binding.etNamaDokumen3.getText().toString());
//                        }
//                        iv.setImageBitmap(bitmap);
//                        valDokumenC = AppUtil.encodeImageTobase64(bitmap);
//                    }
//                } else if (clicker.equalsIgnoreCase("dokumenD")) {
//                    dokumenD = bitmap;
//                    ReqDocumentUmum doc = new ReqDocumentUmum();
//                    doc.setFilename("dokumenD.png");
//                    doc.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    doc.setKeteranganDokumen(binding.etKeteranganDokumen4.getText().toString());
//                    doc.setNamaDokumen(binding.etNamaDokumen4.getText().toString());
//                    if (DokumenUmum.size() < 3) {
//                        AppUtil.notiferror(this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
//                    } else {
//                        if (DokumenUmum.size() <= 3) {
//                            DokumenUmum.add(3, doc);
//                        } else {
//                            DokumenUmum.get(3).setFilename("dokumenD.png");
//                            DokumenUmum.get(3).setImg(valDokumenD);
//                            DokumenUmum.get(3).setKeteranganDokumen(binding.etKeteranganDokumen4.getText().toString());
//                            DokumenUmum.get(3).setNamaDokumen(binding.etNamaDokumen4.getText().toString());
//                        }
//                        iv.setImageBitmap(bitmap);
//                        valDokumenD = AppUtil.encodeImageTobase64(bitmap);
//                    }
//                } else if (clicker.equalsIgnoreCase("dokumenE")) {
//                    dokumenE = bitmap;
//                    ReqDocumentUmum doc = new ReqDocumentUmum();
//                    doc.setFilename("dokumenE.png");
//                    doc.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    doc.setKeteranganDokumen(binding.etKeteranganDokumen5.getText().toString());
//                    doc.setNamaDokumen(binding.etNamaDokumen5.getText().toString());
//                    if (DokumenUmum.size() < 4) {
//                        AppUtil.notiferror(this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
//                    } else {
//                        if (DokumenUmum.size() <= 4) {
//                            DokumenUmum.add(4, doc);
//                        } else {
//                            DokumenUmum.get(4).setFilename("dokumenE.png");
//                            DokumenUmum.get(4).setImg(valDokumenE);
//                            DokumenUmum.get(4).setKeteranganDokumen(binding.etKeteranganDokumen5.getText().toString());
//                            DokumenUmum.get(4).setNamaDokumen(binding.etNamaDokumen5.getText().toString());
//                        }
//                        iv.setImageBitmap(bitmap);
//                        valDokumenE = AppUtil.encodeImageTobase64(bitmap);
//                    }
//                }
//                // Jaminan Dokumen
//
//                if (clicker.equalsIgnoreCase("skpensiun")) {
//                    FNskpensiun = "skpensiun.png";
//                    Foto_SK_Pensiun.setFileName(FNskpensiun);
//                    Foto_SK_Pensiun.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvFotoSkPensiun.setVisibility(View.VISIBLE);
//                    valskpensiun = AppUtil.encodeImageTobase64(bitmap);
//                    skpensiun = bitmap;
//                    AppUtil.logSecure("deltaforce", Foto_SK_Pensiun.getFileName());
//                } else if (clicker.equalsIgnoreCase("skpengangkatan")) {
//                    FNskpengangkatan = "skpengangkatan.png";
//                    Foto_SK_Pengangkatan.setFileName(FNskpengangkatan);
//                    Foto_SK_Pengangkatan.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvFotoSkPengangkatan.setVisibility(View.VISIBLE);
//                    valskpengangkatan = AppUtil.encodeImageTobase64(bitmap);
//                    skpengangkatan = bitmap;
//                    AppUtil.logSecure("deltaforce", Foto_SK_Pengangkatan.getFileName());
//                } else if (clicker.equalsIgnoreCase("skterakhir")) {
//                    FNskterakhir = "skterakhir.png";
//                    Foto_SK_Terakhir.setFileName(FNskterakhir);
//                    Foto_SK_Terakhir.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvFotoSkTerakhir.setVisibility(View.VISIBLE);
//                    valskterakhir = AppUtil.encodeImageTobase64(bitmap);
//                    skterakhir = bitmap;
//                    AppUtil.logSecure("deltaforce", Foto_SK_Terakhir.getFileName());
//                }
//                // AsuransiKhusus
//
//                if (clicker.equalsIgnoreCase("covernoteasuransi")) {
//                    FNcovernoteasuransi = "covernoteasuransi.png";
//                    Foto_Covernote_Asuransi.setFileName(FNcovernoteasuransi);
//                    Foto_Covernote_Asuransi.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvFotoCovernoteAsuransi.setVisibility(View.VISIBLE);
//                    valcovernoteasuransi = AppUtil.encodeImageTobase64(bitmap);
//                    covernoteasuransi = bitmap;
//                }
//                // Dokumen Produk
//
//                if (clicker.equalsIgnoreCase("sk")) {
//                    FNsk = "sk.png";
//                    Tanda_Terima_Dokumen_SK.setFileName(FNsk);
//                    Tanda_Terima_Dokumen_SK.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvSk.setVisibility(View.VISIBLE);
//                    valsk = AppUtil.encodeImageTobase64(bitmap);
//                    sk = bitmap;
//                } else if (clicker.equalsIgnoreCase("mutasi")) {
//                    FNmutasi = "mutasi.png";
//                    Form_Mutasi_Kantor_Bayar_Taspen.setFileName(FNmutasi);
//                    Form_Mutasi_Kantor_Bayar_Taspen.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvMutasi.setVisibility(View.VISIBLE);
//                    valmutasi = AppUtil.encodeImageTobase64(bitmap);
//                    mutasi = bitmap;
//                } else if (clicker.equalsIgnoreCase("kuasa")) {
//                    FNkuasa = "kuasa.png";
//                    Surat_Kuasa_Pernyataan_Flagging.setFileName(FNkuasa);
//                    Surat_Kuasa_Pernyataan_Flagging.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvKuasa.setVisibility(View.VISIBLE);
//                    valkuasa = AppUtil.encodeImageTobase64(bitmap);
//                    kuasa = bitmap;
//                } else if (clicker.equalsIgnoreCase("pernyataan")) {
//                    FNpernyataan = "pernyataan.png";
//                    Surat_Pernyataan_Kuasa_Pembiayaan.setFileName(FNpernyataan);
//                    Surat_Pernyataan_Kuasa_Pembiayaan.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvPernyataan.setVisibility(View.VISIBLE);
//                    valpernyataan = AppUtil.encodeImageTobase64(bitmap);
//                    pernyataan = bitmap;
//                } else if (clicker.equalsIgnoreCase("sup")) {
//                    FNsup = "sup.png";
//                    SUP.setFileName(FNsup);
//                    SUP.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvSup.setVisibility(View.VISIBLE);
//                    valsup = AppUtil.encodeImageTobase64(bitmap);
//                    sup = bitmap;
//                }
//                // Tanda Tangan Akad
//
//                if (clicker.equalsIgnoreCase("ttdakad")) {
//                    FNttdakad = "ttdakad.png";
//                    Foto_Proses_Penandatanganan_Akad.setFileName(FNttdakad);
//                    Foto_Proses_Penandatanganan_Akad.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvFotoProsesPenandatangananAkad.setVisibility(View.VISIBLE);
//                    valttdakad = AppUtil.encodeImageTobase64(bitmap);
//                    ttdakad = bitmap;
//                }
//                // Dokumen Murabahah
//
//                if (clicker.equalsIgnoreCase("po")) {
//                    FNpo = "po.png";
//                    Murabahah_Purchase_Order.setFileName(FNpo);
//                    Murabahah_Purchase_Order.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvPo.setVisibility(View.VISIBLE);
//                    valpo = AppUtil.encodeImageTobase64(bitmap);
//                    po = bitmap;
//                } else if (clicker.equalsIgnoreCase("akadwakalah")) {
//                    FNwakalah = "akadwakalah.png";
//                    Murabahah_Akad_Wakalah.setFileName(FNwakalah);
//                    Murabahah_Akad_Wakalah.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvAkadWakalah.setVisibility(View.VISIBLE);
//                    valwakalah = AppUtil.encodeImageTobase64(bitmap);
//                    wakalah = bitmap;
//                } else if (clicker.equalsIgnoreCase("murabahah")) {
//                    FNmurabahah = "murabahah.png";
//                    Murabahah_Akad_Murabahah.setFileName(FNmurabahah);
//                    Murabahah_Akad_Murabahah.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvAkadMurabahah.setVisibility(View.VISIBLE);
//                    valmurabahah = AppUtil.encodeImageTobase64(bitmap);
//                    murabahah = bitmap;
//                } else if (clicker.equalsIgnoreCase("jadwalangsuran")) {
//                    FNjadwalangsuran = "jadwalangsuran.png";
//                    Murabahah_Lampiran_Jadwal_Angsuran.setFileName(FNjadwalangsuran);
//                    Murabahah_Lampiran_Jadwal_Angsuran.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvJadwalAngsuran.setVisibility(View.VISIBLE);
//                    valjadwalangsuran = AppUtil.encodeImageTobase64(bitmap);
//                    jadwalangsuran = bitmap;
//                } else if (clicker.equalsIgnoreCase("terimabarang")) {
//                    FNterimabarang = "terimabarang.png";
//                    Murabahah_Surat_Tanda_Terima_Barang.setFileName(FNterimabarang);
//                    Murabahah_Surat_Tanda_Terima_Barang.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvTerimaBarang.setVisibility(View.VISIBLE);
//                    valterimabarang = AppUtil.encodeImageTobase64(bitmap);
//                    terimabarang = bitmap;
//                }
//
//                // Dokumen Ijarah
//
//                if (clicker.equalsIgnoreCase("poijarah")) {
//                    FNpoijarah = "poijarah.png";
//                    Ijarah_Purchase_Order.setFileName(FNpoijarah);
//                    Ijarah_Purchase_Order.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvPoIjarah.setVisibility(View.VISIBLE);
//                    valpoijarah = AppUtil.encodeImageTobase64(bitmap);
//                    poijarah = bitmap;
//                } else if (clicker.equalsIgnoreCase("wakalahijarah")) {
//                    FNwakalahijarah = "wakalahijarah.png";
//                    Ijarah_Akad_Wakalah.setFileName(FNwakalahijarah);
//                    Ijarah_Akad_Wakalah.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvAkadWakalahIjarah.setVisibility(View.VISIBLE);
//                    valwakalahijarah = AppUtil.encodeImageTobase64(bitmap);
//                    wakalahijarah = bitmap;
//                } else if (clicker.equalsIgnoreCase("ijarah")) {
//                    FNijarah = "ijarah.png";
//                    Ijarah_Akad_Ijarah.setFileName(FNijarah);
//                    Ijarah_Akad_Ijarah.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvAkadIjarah.setVisibility(View.VISIBLE);
//                    valijarah = AppUtil.encodeImageTobase64(bitmap);
//                    ijarah = bitmap;
//                } else if (clicker.equalsIgnoreCase("angsuranujrah")) {
//                    FNangsuranujrah = "angsuranujrah.png";
//                    Ijarah_Lampiran_Jadwal_Angsuran.setFileName(FNangsuranujrah);
//                    Ijarah_Lampiran_Jadwal_Angsuran.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvAngsuranUjrah.setVisibility(View.VISIBLE);
//                    valangsuranujrah = AppUtil.encodeImageTobase64(bitmap);
//                    angsuranujrah = bitmap;
//                }
//                // Dokumen MMQ
//
//                if (clicker.equalsIgnoreCase("assetmmq")) {
//                    FNassetmmq = "assetmmq.png";
//                    MMQ_Laporan_Penilaian_Aset.setFileName(FNassetmmq);
//                    MMQ_Laporan_Penilaian_Aset.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvAssetMmq.setVisibility(View.VISIBLE);
//                    valassetmmq = AppUtil.encodeImageTobase64(bitmap);
//                    assetmmq = bitmap;
//                } else if (clicker.equalsIgnoreCase("akadmmq")) {
//                    FNakadmmq = "akadmmq.png";
//                    MMQ_Akad_MMQ.setFileName(FNakadmmq);
//                    MMQ_Akad_MMQ.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvAkadMmq.setVisibility(View.VISIBLE);
//                    valakadmmq = AppUtil.encodeImageTobase64(bitmap);
//                    akadmmq = bitmap;
//                } else if (clicker.equalsIgnoreCase("jadwalpengambilan")) {
//                    FNjadwalpengambilan = "jadwalpengambilan.png";
//                    MMQ_Lampiran_Jadwal_Pengambil_Alihan.setFileName(FNjadwalpengambilan);
//                    MMQ_Lampiran_Jadwal_Pengambil_Alihan.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvJadwalPengambilalihan.setVisibility(View.VISIBLE);
//                    valjadwalpengambilan = AppUtil.encodeImageTobase64(bitmap);
//                    jadwalpengambilan = bitmap;
//                } else if (clicker.equalsIgnoreCase("pihak1")) {
//                    FNpihak1 = "pihak1.png";
//                    MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.setFileName(FNpihak1);
//                    MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvSuratPernyataanDanKuasaAsetMmqSendiri.setVisibility(View.VISIBLE);
//                    valpihak1 = AppUtil.encodeImageTobase64(bitmap);
//                    pihak1 = bitmap;
//                } else if (clicker.equalsIgnoreCase("pihak3")) {
//                    FNpihak3 = "pihak3.png";
//                    MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.setFileName(FNpihak3);
//                    MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvSuratPernyataanDanKuasaAsetMmqPihakKetiga.setVisibility(View.VISIBLE);
//                    valpihak3 = AppUtil.encodeImageTobase64(bitmap);
//                    pihak3 = bitmap;
//                }
//                // Dokumen Rahn
//
//                if (clicker.equalsIgnoreCase("akadrahn")) {
//                    FNakadrahn = "akadrahn.png";
//                    Rahn_Akad_Rahn.setFileName(FNakadrahn);
//                    Rahn_Akad_Rahn.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvAkadRahn.setVisibility(View.VISIBLE);
//                    valakadrahn = AppUtil.encodeImageTobase64(bitmap);
//                    akadrahn = bitmap;
//                } else if (clicker.equalsIgnoreCase("angsuranrahn")) {
//                    FNangsuranrahn = "angsuranrahn.png";
//                    Rahn_Lampiran_Jadwal_Angsuran.setFileName(FNangsuranrahn);
//                    Rahn_Lampiran_Jadwal_Angsuran.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvJadwalAngsuranRahn.setVisibility(View.VISIBLE);
//                    valangsuranrahn = AppUtil.encodeImageTobase64(bitmap);
//                    angsuranrahn = bitmap;
//                }
//                // Dokumen Pembiayaan
//
//                if (clicker.equalsIgnoreCase("kantorbayar")) {
//                    FNkantorbayar = "kantorbayar.png";
//                    Form_Mutasi_Kantor_Bayar.setFileName(FNkantorbayar);
//                    Form_Mutasi_Kantor_Bayar.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvFormMutasiKantorBayar.setVisibility(View.VISIBLE);
//                    valkantorbayar = AppUtil.encodeImageTobase64(bitmap);
//                    kantorbayar = bitmap;
//                } else if (clicker.equalsIgnoreCase("fotoakad")) {
//                    FNfotoakad = "fotoakad.png";
//                    Foto_Bukti_Otentikasi_Nasabah.setFileName(FNfotoakad);
//                    Foto_Bukti_Otentikasi_Nasabah.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    binding.pvFotoScreenCaptureBuktiOtentikasiNasabahSaatAkad.setVisibility(View.VISIBLE);
//                    valfotoakad = AppUtil.encodeImageTobase64(bitmap);
//                    fotoakad = bitmap;
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                if (data.getData() != null) {
//
//                    if (clicker.equalsIgnoreCase("dokumenA")) {
//                        Uri uriPdf = data.getData();
//
//                        valDokumenA = AppUtil.encodeFileToBase64(this, uriPdf);
//                        ReqDocumentUmum doc = new ReqDocumentUmum();
//                        doc.setFilename("dokumenA.pdf");
//                        doc.setImg(valDokumenA);
//                        doc.setKeteranganDokumen(binding.etKeteranganDokumen1.getText().toString());
//                        doc.setNamaDokumen(binding.etNamaDokumen1.getText().toString());
//                        if (DokumenUmum.size() == 0) {
//                            DokumenUmum.add(0, doc);
//                        } else {
//                            DokumenUmum.get(0).setFilename("dokumenA.pdf");
//                            DokumenUmum.get(0).setImg(valDokumenA);
//                            DokumenUmum.get(0).setKeteranganDokumen(binding.etKeteranganDokumen1.getText().toString());
//                            DokumenUmum.get(0).setNamaDokumen(binding.etNamaDokumen1.getText().toString());
//                        }
//                        AppUtil.logSecure("dataD", String.valueOf(DokumenUmum.size()));
//                        iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));
//                    } else if (clicker.equalsIgnoreCase("dokumenB")) {
//                        Uri uriPdf = data.getData();
//                        valDokumenB = AppUtil.encodeFileToBase64(this, uriPdf);
//                        ReqDocumentUmum doc = new ReqDocumentUmum();
//                        doc.setFilename("dokumenB.pdf");
//                        doc.setImg(valDokumenB);
//                        doc.setKeteranganDokumen(binding.etKeteranganDokumen2.getText().toString());
//                        doc.setNamaDokumen(binding.etNamaDokumen2.getText().toString());
//                        if (DokumenUmum.size() < 1) {
//                            AppUtil.notiferror(this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
//                        } else {
//                            if (DokumenUmum.size() <= 1) {
//                                DokumenUmum.add(1, doc);
//                            } else {
//                                DokumenUmum.get(1).setFilename("dokumenB.pdf");
//                                DokumenUmum.get(1).setImg(valDokumenB);
//                                DokumenUmum.get(1).setKeteranganDokumen(binding.etKeteranganDokumen2.getText().toString());
//                                DokumenUmum.get(1).setNamaDokumen(binding.etNamaDokumen2.getText().toString());
//                            }
//                            iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));
//                        }
//                    } else if (clicker.equalsIgnoreCase("dokumenC")) {
//                        Uri uriPdf = data.getData();
//                        valDokumenC = AppUtil.encodeFileToBase64(this, uriPdf);
//                        ReqDocumentUmum doc = new ReqDocumentUmum();
//                        doc.setFilename("dokumenC.pdf");
//                        doc.setImg(valDokumenC);
//                        doc.setKeteranganDokumen(binding.etKeteranganDokumen3.getText().toString());
//                        doc.setNamaDokumen(binding.etNamaDokumen3.getText().toString());
//                        if (DokumenUmum.size() < 2) {
//                            AppUtil.notiferror(this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
//                        } else {
//                            if (DokumenUmum.size() <= 2) {
//                                DokumenUmum.add(2, doc);
//                            } else {
//                                DokumenUmum.get(2).setFilename("dokumenC.pdf");
//                                DokumenUmum.get(2).setImg(valDokumenC);
//                                DokumenUmum.get(2).setKeteranganDokumen(binding.etKeteranganDokumen3.getText().toString());
//                                DokumenUmum.get(2).setNamaDokumen(binding.etNamaDokumen3.getText().toString());
//                            }
//                            iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));
//                        }
//                    } else if (clicker.equalsIgnoreCase("dokumenD")) {
//                        Uri uriPdf = data.getData();
//                        valDokumenD = AppUtil.encodeFileToBase64(this, uriPdf);
//                        ReqDocumentUmum doc = new ReqDocumentUmum();
//                        doc.setFilename("dokumenD.pdf");
//                        doc.setImg(valDokumenD);
//                        doc.setKeteranganDokumen(binding.etKeteranganDokumen4.getText().toString());
//                        doc.setNamaDokumen(binding.etNamaDokumen4.getText().toString());
//                        if (DokumenUmum.size() < 3) {
//                            AppUtil.notiferror(this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
//                        } else {
//                            if (DokumenUmum.size() <= 3) {
//                                DokumenUmum.add(3, doc);
//                            } else {
//                                DokumenUmum.get(3).setFilename("dokumenD.pdf");
//                                DokumenUmum.get(3).setImg(valDokumenD);
//                                DokumenUmum.get(3).setKeteranganDokumen(binding.etKeteranganDokumen4.getText().toString());
//                                DokumenUmum.get(3).setNamaDokumen(binding.etNamaDokumen4.getText().toString());
//                            }
//                            iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));
//                        }
//                    } else if (clicker.equalsIgnoreCase("dokumenE")) {
//                        Uri uriPdf = data.getData();
//                        valDokumenE = AppUtil.encodeFileToBase64(this, uriPdf);
//                        ReqDocumentUmum doc = new ReqDocumentUmum();
//                        doc.setFilename("dokumenE.pdf");
//                        doc.setImg(valDokumenE);
//                        doc.setKeteranganDokumen(binding.etKeteranganDokumen5.getText().toString());
//                        doc.setNamaDokumen(binding.etNamaDokumen5.getText().toString());
//                        if (DokumenUmum.size() < 4) {
//                            AppUtil.notiferror(this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
//                        } else {
//                            if (DokumenUmum.size() <= 4) {
//                                DokumenUmum.add(4, doc);
//                            } else {
//                                DokumenUmum.get(4).setFilename("dokumenE.pdf");
//                                DokumenUmum.get(4).setImg(valDokumenE);
//                                DokumenUmum.get(4).setKeteranganDokumen(binding.etKeteranganDokumen5.getText().toString());
//                                DokumenUmum.get(4).setNamaDokumen(binding.etNamaDokumen5.getText().toString());
//                            }
//                            iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));
//                        }
//                    }
//                    // Dokumen Jaminan
//
//                    if (clicker.equalsIgnoreCase("skpensiun")) {
//                        Uri uriPdf = data.getData();
//                        valskpensiun = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNskpensiun = "skpensiun.pdf";
//                        Foto_SK_Pensiun.setFileName(FNskpensiun);
//                        Foto_SK_Pensiun.setImg(valskpensiun);
//                        binding.pvFotoSkPensiun.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("skpengangkatan")) {
//                        Uri uriPdf = data.getData();
//                        valskpengangkatan = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNskpengangkatan = "skpengangkatan.pdf";
//                        Foto_SK_Pengangkatan.setFileName(FNskpengangkatan);
//                        Foto_SK_Pengangkatan.setImg(valskpengangkatan);
//                        binding.pvFotoSkPengangkatan.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("skterakhir")) {
//                        Uri uriPdf = data.getData();
//                        valskterakhir = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNskterakhir = "skterakhir.pdf";
//                        Foto_SK_Terakhir.setFileName(FNskterakhir);
//                        Foto_SK_Terakhir.setImg(valskterakhir);
//                        binding.pvFotoSkTerakhir.setVisibility(View.VISIBLE);
//                    }
//                    // Asuransi Khusus
//
//                    if (clicker.equalsIgnoreCase("covernoteasuransi")) {
//                        Uri uriPdf = data.getData();
//                        valcovernoteasuransi = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNcovernoteasuransi = "covernoteasuransi.pdf";
//                        Foto_Covernote_Asuransi.setFileName(FNcovernoteasuransi);
//                        Foto_Covernote_Asuransi.setImg(valcovernoteasuransi);
//                        binding.pvFotoCovernoteAsuransi.setVisibility(View.VISIBLE);
//                    }
//                    // Dokumen Produk
//
//                    if (clicker.equalsIgnoreCase("sk")) {
//                        Uri uriPdf = data.getData();
//                        valsk = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNsk = "sk.pdf";
//                        Tanda_Terima_Dokumen_SK.setFileName(FNsk);
//                        Tanda_Terima_Dokumen_SK.setImg(valsk);
//                        binding.pvSk.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("mutasi")) {
//                        Uri uriPdf = data.getData();
//                        valmutasi = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNmutasi = "mutasi.pdf";
//                        Form_Mutasi_Kantor_Bayar_Taspen.setFileName(FNmutasi);
//                        Form_Mutasi_Kantor_Bayar_Taspen.setImg(valmutasi);
//                        binding.pvMutasi.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("kuasa")) {
//                        Uri uriPdf = data.getData();
//                        valkuasa = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNkuasa = "kuasa.pdf";
//                        Surat_Kuasa_Pernyataan_Flagging.setFileName(FNkuasa);
//                        Surat_Kuasa_Pernyataan_Flagging.setImg(valkuasa);
//                        binding.pvKuasa.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("pernyataan")) {
//                        Uri uriPdf = data.getData();
//                        valpernyataan = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNpernyataan = "pernyataan.pdf";
//                        Surat_Pernyataan_Kuasa_Pembiayaan.setFileName(FNpernyataan);
//                        Surat_Pernyataan_Kuasa_Pembiayaan.setImg(valpernyataan);
//                        binding.pvPernyataan.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("sup")) {
//                        Uri uriPdf = data.getData();
//                        valsup = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNsup = "sup.pdf";
//                        SUP.setFileName(FNsup);
//                        SUP.setImg(valsup);
//                        binding.pvSup.setVisibility(View.VISIBLE);
//                    }
//                    // Tanda Tangan Akad
//
//                    if (clicker.equalsIgnoreCase("ttdakad")) {
//                        Uri uriPdf = data.getData();
//                        valttdakad = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNttdakad = "ttdakad.pdf";
//                        Foto_Proses_Penandatanganan_Akad.setFileName(FNttdakad);
//                        Foto_Proses_Penandatanganan_Akad.setImg(valttdakad);
//                        binding.pvFotoProsesPenandatangananAkad.setVisibility(View.VISIBLE);
//                    }
//                    // Dokumen Murabahah
//
//                    if (clicker.equalsIgnoreCase("po")) {
//                        Uri uriPdf = data.getData();
//                        valpo = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNpo = "po.pdf";
//                        Murabahah_Purchase_Order.setFileName(FNpo);
//                        Murabahah_Purchase_Order.setImg(valpo);
//                        binding.pvPo.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("akadwakalah")) {
//                        Uri uriPdf = data.getData();
//                        valwakalah = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNwakalah = "akadwakalah.pdf";
//                        Murabahah_Akad_Wakalah.setFileName(FNwakalah);
//                        Murabahah_Akad_Wakalah.setImg(valwakalah);
//                        binding.pvAkadWakalah.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("murabahah")) {
//                        Uri uriPdf = data.getData();
//                        valmurabahah = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNmurabahah = "murabahah.pdf";
//                        Murabahah_Akad_Murabahah.setFileName(FNmurabahah);
//                        Murabahah_Akad_Murabahah.setImg(valmurabahah);
//                        binding.pvAkadMurabahah.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("jadwalangsuran")) {
//                        Uri uriPdf = data.getData();
//                        valjadwalangsuran = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNjadwalangsuran = "jadwalangsuran.pdf";
//                        Murabahah_Lampiran_Jadwal_Angsuran.setFileName(FNjadwalangsuran);
//                        Murabahah_Lampiran_Jadwal_Angsuran.setImg(valjadwalangsuran);
//                        binding.pvJadwalAngsuran.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("terimabarang")) {
//                        Uri uriPdf = data.getData();
//                        valterimabarang = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNterimabarang = "terimabarang.pdf";
//                        Murabahah_Surat_Tanda_Terima_Barang.setFileName(FNterimabarang);
//                        Murabahah_Surat_Tanda_Terima_Barang.setImg(valterimabarang);
//                        binding.pvTerimaBarang.setVisibility(View.VISIBLE);
//                    }
//                    // Dokumen Ijarah
//
//                    if (clicker.equalsIgnoreCase("poijarah")) {
//                        Uri uriPdf = data.getData();
//                        valpoijarah = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNpoijarah = "poijarah.pdf";
//                        Ijarah_Purchase_Order.setFileName(FNpoijarah);
//                        Ijarah_Purchase_Order.setImg(valpoijarah);
//                        binding.pvPoIjarah.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("wakalahijarah")) {
//                        Uri uriPdf = data.getData();
//                        valwakalahijarah = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNwakalahijarah = "wakalahijarah.pdf";
//                        Ijarah_Akad_Wakalah.setFileName(FNwakalahijarah);
//                        Ijarah_Akad_Wakalah.setImg(valwakalahijarah);
//                        binding.pvAkadWakalahIjarah.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("ijarah")) {
//                        Uri uriPdf = data.getData();
//                        valijarah = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNijarah = "ijarah.pdf";
//                        Ijarah_Akad_Ijarah.setFileName(FNijarah);
//                        Ijarah_Akad_Ijarah.setImg(valijarah);
//                        binding.pvAkadIjarah.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("angsuranujrah")) {
//                        Uri uriPdf = data.getData();
//                        valangsuranujrah = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNangsuranujrah = "angsuranujrah.pdf";
//                        Ijarah_Lampiran_Jadwal_Angsuran.setFileName(FNangsuranujrah);
//                        Ijarah_Lampiran_Jadwal_Angsuran.setImg(valangsuranujrah);
//                        binding.pvAngsuranUjrah.setVisibility(View.VISIBLE);
//                    }
//                    // Dokumen MMQ
//
//                    if (clicker.equalsIgnoreCase("assetmmq")) {
//                        Uri uriPdf = data.getData();
//                        valassetmmq = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNassetmmq = "assetmmq.pdf";
//                        MMQ_Laporan_Penilaian_Aset.setFileName(FNassetmmq);
//                        MMQ_Laporan_Penilaian_Aset.setImg(valassetmmq);
//                        binding.pvAssetMmq.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("akadmmq")) {
//                        Uri uriPdf = data.getData();
//                        valakadmmq = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNakadmmq = "akadmmq.pdf";
//                        MMQ_Akad_MMQ.setFileName(FNakadmmq);
//                        MMQ_Akad_MMQ.setImg(valakadmmq);
//                        binding.pvAkadMmq.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("jadwalpengambilan")) {
//                        Uri uriPdf = data.getData();
//                        valjadwalpengambilan = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNjadwalpengambilan = "jadwalpengambilan.pdf";
//                        MMQ_Lampiran_Jadwal_Pengambil_Alihan.setFileName(FNjadwalpengambilan);
//                        MMQ_Lampiran_Jadwal_Pengambil_Alihan.setImg(valjadwalpengambilan);
//                        binding.pvJadwalPengambilalihan.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("pihak1")) {
//                        Uri uriPdf = data.getData();
//                        valpihak1 = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNpihak1 = "pihak1.pdf";
//                        MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.setFileName(FNpihak1);
//                        MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.setImg(valpihak1);
//                        binding.pvSuratPernyataanDanKuasaAsetMmqSendiri.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("pihak3")) {
//                        Uri uriPdf = data.getData();
//                        valpihak3 = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNpihak3 = "pihak3.pdf";
//                        MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.setFileName(FNpihak3);
//                        MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.setImg(valpihak3);
//                        binding.pvSuratPernyataanDanKuasaAsetMmqPihakKetiga.setVisibility(View.VISIBLE);
//                    }
//                    // Dokumen Rahn
//
//                    if (clicker.equalsIgnoreCase("akadrahn")) {
//                        Uri uriPdf = data.getData();
//                        valakadrahn = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNakadrahn = "akadrahn.pdf";
//                        Rahn_Akad_Rahn.setFileName(FNakadrahn);
//                        Rahn_Akad_Rahn.setImg(valakadrahn);
//                        binding.pvAkadRahn.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("angsuranrahn")) {
//                        Uri uriPdf = data.getData();
//                        valangsuranrahn = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNangsuranrahn = "angsuranrahn.pdf";
//                        Rahn_Lampiran_Jadwal_Angsuran.setFileName(FNangsuranrahn);
//                        Rahn_Lampiran_Jadwal_Angsuran.setImg(valangsuranrahn);
//                        binding.pvJadwalAngsuranRahn.setVisibility(View.VISIBLE);
//                    }
//                    // Dokumen Pembiayaan
//
//                    if (clicker.equalsIgnoreCase("kantorbayar")) {
//                        Uri uriPdf = data.getData();
//                        valkantorbayar = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNkantorbayar = "kantorbayar.pdf";
//                        Form_Mutasi_Kantor_Bayar.setFileName(FNkantorbayar);
//                        Form_Mutasi_Kantor_Bayar.setImg(valkantorbayar);
//                        binding.pvFormMutasiKantorBayar.setVisibility(View.VISIBLE);
//                    } else if (clicker.equalsIgnoreCase("fotoakad")) {
//                        Uri uriPdf = data.getData();
//                        valfotoakad = AppUtil.encodeFileToBase64(this, uriPdf);
//                        FNfotoakad = "fotoakad.pdf";
//                        Foto_Bukti_Otentikasi_Nasabah.setFileName(FNfotoakad);
//                        Foto_Bukti_Otentikasi_Nasabah.setImg(valfotoakad);
//                        binding.pvFotoScreenCaptureBuktiOtentikasiNasabahSaatAkad.setVisibility(View.VISIBLE);
//                    }
//
//
//                }
//            }
//
//        }
//    }

    @Override
    public void success(boolean val) {
        finish();
    }

    @Override
    public void confirm(boolean val) {

    }

    //Logical Doc

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (resultCode != 0) {
            switch (requestCode) {
                // Dokumen Tambahan
                case IntDokumenA:
                    SetDataImage(binding.ivUploadDokumen1, imageReturnedIntent, "dokumenA", IntDokumenA);
                    checkFileTypeThenUpload(nameDokumenA, "_" + binding.etNamaDokumen1.getText().toString(), binding.ivUploadDokumen1, valDokumenA, IntDokumenA);
                    break;
                case IntDokumenB:
                    SetDataImage(binding.ivUploadDokumen2, imageReturnedIntent, "dokumenB", IntDokumenB);
                    checkFileTypeThenUpload(nameDokumenB, "_" + binding.etNamaDokumen2.getText().toString(), binding.ivUploadDokumen2, valDokumenB, IntDokumenB);
                    break;
                case IntDokumenC:
                    SetDataImage(binding.ivUploadDokumen3, imageReturnedIntent, "dokumenC", IntDokumenC);
                    checkFileTypeThenUpload(nameDokumenC, "_" + binding.etNamaDokumen3.getText().toString(), binding.ivUploadDokumen3, valDokumenC, IntDokumenC);
                    break;
                case IntDokumenD:
                    SetDataImage(binding.ivUploadDokumen4, imageReturnedIntent, "dokumenD", IntDokumenD);
                    checkFileTypeThenUpload(nameDokumenD, "_" + binding.etNamaDokumen4.getText().toString(), binding.ivUploadDokumen4, valDokumenD, IntDokumenD);
                    break;
                case IntDokumenE:
                    SetDataImage(binding.ivUploadDokumen5, imageReturnedIntent, "dokumenE", IntDokumenE);
                    checkFileTypeThenUpload(nameDokumenE, "_" + binding.etNamaDokumen5.getText().toString(), binding.ivUploadDokumen5, valDokumenE, IntDokumenE);
                    break;

                // Dokumen Jaminan
                case Intskpensiun:
                    SetDataImage(null, imageReturnedIntent, "skpensiun", Intskpensiun);
                    checkFileTypeThenUpload(FNskpensiun, "_skpensiun", null, valskpensiun, Intskpensiun);
                    break;
                case Intskpengangkatan:
                    SetDataImage(null, imageReturnedIntent, "skpengangkatan", Intskpengangkatan);
                    checkFileTypeThenUpload(FNskpengangkatan, "_skpengangkatan", null, valskpengangkatan, Intskpengangkatan);
                    break;
                case Intskterakhir:
                    SetDataImage(null, imageReturnedIntent, "skterakhir", Intskterakhir);
                    checkFileTypeThenUpload(FNskterakhir, "_skterakhir", null, valskterakhir, Intskterakhir);
                    break;

                // Asuransi Khusus
                case Intcovernoteasuransi:
                    SetDataImage(null, imageReturnedIntent, "covernoteasuransi", Intcovernoteasuransi);
                    checkFileTypeThenUpload(FNcovernoteasuransi, "_covernoteasuransi", null, valcovernoteasuransi, Intcovernoteasuransi);
                    break;

                // Dokumen Produk
                case Intsk:
                    SetDataImage(null, imageReturnedIntent, "sk", Intsk);
                    checkFileTypeThenUpload(FNsk, "_sk", null, valsk, Intsk);
                    break;
                case Intmutasi:
                    SetDataImage(null, imageReturnedIntent, "mutasi", Intmutasi);
                    checkFileTypeThenUpload(FNmutasi, "_mutasi", null, valmutasi, Intmutasi);
                    break;
                case Intkuasa:
                    SetDataImage(null, imageReturnedIntent, "kuasa", Intkuasa);
                    checkFileTypeThenUpload(FNkuasa, "_kuasa", null, valkuasa, Intkuasa);
                    break;
                case Intpernyataan:
                    SetDataImage(null, imageReturnedIntent, "pernyataan", Intpernyataan);
                    checkFileTypeThenUpload(FNpernyataan, "_pernyataan", null, valpernyataan, Intpernyataan);
                    break;
                case Intsup:
                    SetDataImage(null, imageReturnedIntent, "sup", Intsup);
                    checkFileTypeThenUpload(FNsup, "_sup", null, valsup, Intsup);
                    break;

                // Tanda Tangan Akad
                case Intttdakad:
                    SetDataImage(null, imageReturnedIntent, "ttdakad", Intttdakad);
                    checkFileTypeThenUpload(FNttdakad, "_ttdakad", null, valttdakad, Intttdakad);
                    break;

                // Dokumen Murabahah
                case Intpo:
                    SetDataImage(null, imageReturnedIntent, "po", Intpo);
                    checkFileTypeThenUpload(FNpo, "_po", null, valpo, Intpo);
                    break;
                case Intwakalah:
                    SetDataImage(null, imageReturnedIntent, "wakalah", Intwakalah);
                    checkFileTypeThenUpload(FNwakalah, "_wakalah", null, valwakalah, Intwakalah);
                    break;
                case Intmurabahah:
                    SetDataImage(null, imageReturnedIntent, "murabahah", Intmurabahah);
                    checkFileTypeThenUpload(FNmurabahah, "_murabahah", null, valmurabahah, Intmurabahah);
                    break;
                case Intjadwalangsuran:
                    SetDataImage(null, imageReturnedIntent, "jadwalangsuran", Intjadwalangsuran);
                    checkFileTypeThenUpload(FNjadwalangsuran, "_jadwalangsuran", null, valjadwalangsuran, Intjadwalangsuran);
                    break;
                case Intterimabarang:
                    SetDataImage(null, imageReturnedIntent, "terimabarang", Intterimabarang);
                    checkFileTypeThenUpload(FNterimabarang, "_terimabarang", null, valterimabarang, Intterimabarang);
                    break;

                // Dokumen Ijarah
                case Intpoijarah:
                    SetDataImage(null, imageReturnedIntent, "poijarah", Intpoijarah);
                    checkFileTypeThenUpload(FNpoijarah, "_poijarah", null, valpoijarah, Intpoijarah);
                    break;
                case Intwakalahijarah:
                    SetDataImage(null, imageReturnedIntent, "wakalahijarah", Intwakalahijarah);
                    checkFileTypeThenUpload(FNwakalahijarah, "_wakalahijarah", null, valwakalahijarah, Intwakalahijarah);
                    break;
                case Intijarah:
                    SetDataImage(null, imageReturnedIntent, "ijarah", Intijarah);
                    checkFileTypeThenUpload(FNijarah, "_ijarah", null, valijarah, Intijarah);
                    break;
                case Intangsuranujrah:
                    SetDataImage(null, imageReturnedIntent, "angsuranujrah", Intangsuranujrah);
                    checkFileTypeThenUpload(FNangsuranujrah, "_angsuranujrah", null, valangsuranujrah, Intangsuranujrah);
                    break;

                // Dokumen MMQ
                case Intassetmmq:
                    SetDataImage(null, imageReturnedIntent, "assetmmq", Intassetmmq);
                    checkFileTypeThenUpload(FNassetmmq, "_assetmmq", null, valassetmmq, Intassetmmq);
                    break;
                case Intakadmmq:
                    SetDataImage(null, imageReturnedIntent, "akadmmq", Intakadmmq);
                    checkFileTypeThenUpload(FNakadmmq, "_akadmmq", null, valakadmmq, Intakadmmq);
                    break;
                case Intjadwalpengambilan:
                    SetDataImage(null, imageReturnedIntent, "jadwalpengambilan", Intjadwalpengambilan);
                    checkFileTypeThenUpload(FNjadwalpengambilan, "_jadwalpengambilan", null, valjadwalpengambilan, Intjadwalpengambilan);
                    break;
                case Intpihak1:
                    SetDataImage(null, imageReturnedIntent, "pihak1", Intpihak1);
                    checkFileTypeThenUpload(FNpihak1, "_pihak1", null, valpihak1, Intpihak1);
                    break;
                case Intpihak3:
                    SetDataImage(null, imageReturnedIntent, "pihak3", Intpihak3);
                    checkFileTypeThenUpload(FNpihak3, "_pihak3", null, valpihak3, Intpihak3);
                    break;

                // Dokumen MMQ
                case Intakadrahn:
                    SetDataImage(null, imageReturnedIntent, "akadrahn", Intakadrahn);
                    checkFileTypeThenUpload(FNakadrahn, "_akadrahn", null, valakadrahn, Intakadrahn);
                    break;
                case Intangsuranrahn:
                    SetDataImage(null, imageReturnedIntent, "angsuranrahn", Intangsuranrahn);
                    checkFileTypeThenUpload(FNangsuranrahn, "_angsuranrahn", null, valangsuranrahn, Intangsuranrahn);
                    break;

                // Dokumen Pembiayaan
                case Intkantorbayar:
                    SetDataImage(null, imageReturnedIntent, "kantorbayar", Intkantorbayar);
                    checkFileTypeThenUpload(FNkantorbayar, "_kantorbayar", null, valkantorbayar, Intkantorbayar);
                    break;
                case Intfotoakad:
                    SetDataImage(null, imageReturnedIntent, "fotoakad", Intfotoakad);
                    checkFileTypeThenUpload(FNfotoakad, "_fotoakad", null, valfotoakad, Intfotoakad);
                    break;
            }
        }
    }

    private void SetDataImage(ImageView iv, Intent data, String namaFoto, int kode_upload) {
        if (getPickImageResultUri(data, namaFoto) != null) {
            Bitmap bitmap = null;
            Uri uri;
            if (getPickImageResultUri(data, namaFoto) != null) {
                uri = getPickImageResultUri(data, namaFoto);
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                    bitmap = AppUtil.rotateImageIfRequired(this, bitmap, uri);

                    if (iv != null) {
                        iv.setImageBitmap(bitmap);
                    }

                    // Jaminan Dokumen
                    if (clicker.equalsIgnoreCase("skpensiun")) {
                        skpensiun = bitmap;
                        valskpensiun = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("skpengangkatan")) {
                        skpengangkatan = bitmap;
                        valskpengangkatan = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("skterakhir")) {
                        skterakhir = bitmap;
                        valskterakhir = AppUtil.encodeImageTobase64(bitmap);
                    }

                    // Asuransi Khusus
                    if (clicker.equalsIgnoreCase("covernoteasuransi")) {
                        covernoteasuransi = bitmap;
                        valcovernoteasuransi = AppUtil.encodeImageTobase64(bitmap);
                    }

                    // Dokumen Produk
                    if (clicker.equalsIgnoreCase("sk")) {
                        valsk = AppUtil.encodeImageTobase64(bitmap);
                        binding.pvSk.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("mutasi")) {
                        mutasi = bitmap;
                        valmutasi = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("kuasa")) {
                        kuasa = bitmap;
                        valkuasa = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("pernyataan")) {
                        pernyataan= bitmap;
                        valpernyataan = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("sup")) {
                        sup= bitmap;
                        valsup = AppUtil.encodeImageTobase64(bitmap);
                    }

                    // Tanda Tangan Akad
                    if (clicker.equalsIgnoreCase("ttdakad")) {
                        ttdakad= bitmap;
                        valttdakad = AppUtil.encodeImageTobase64(bitmap);
                    }

                    // Dokumen Murabahah
                    if (clicker.equalsIgnoreCase("po")) {
                        po= bitmap;
                        valpo = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("akadwakalah")) {
                        wakalah= bitmap;
                        valwakalah = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("murabahah")) {
                        murabahah= bitmap;
                        valmurabahah = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("jadwalangsuran")) {
                        jadwalangsuran= bitmap;
                        valjadwalangsuran = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("terimabarang")) {
                        terimabarang= bitmap;
                        valterimabarang = AppUtil.encodeImageTobase64(bitmap);
                    }

                    // Dokumen Ijarah
                    if (clicker.equalsIgnoreCase("poijarah")) {
                        ijarah= bitmap;
                        valpoijarah = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("wakalahijarah")) {
                        wakalahijarah= bitmap;
                        valwakalahijarah = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("ijarah")) {
                        ijarah= bitmap;
                        valijarah = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("angsuranujrah")) {
                        angsuranujrah= bitmap;
                        valangsuranujrah = AppUtil.encodeImageTobase64(bitmap);
                    }

                    // Dokumen MMQ
                    if (clicker.equalsIgnoreCase("assetmmq")) {
                        assetmmq= bitmap;
                        valassetmmq = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("akadmmq")) {
                        akadmmq= bitmap;
                        valakadmmq = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("jadwalpengambilan")) {
                        jadwalpengambilan= bitmap;
                        valjadwalpengambilan = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("pihak1")) {
                        pihak1= bitmap;
                        valpihak1 = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("pihak3")) {
                        pihak3= bitmap;
                        valpihak3 = AppUtil.encodeImageTobase64(bitmap);
                    }

                    // Dokumen Rahn
                    if (clicker.equalsIgnoreCase("akadrahn")) {
                        akadrahn= bitmap;
                        valakadrahn = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("angsuranrahn")) {
                        angsuranrahn= bitmap;
                        valangsuranrahn = AppUtil.encodeImageTobase64(bitmap);
                    }

                    // Dokumen Pembiayaan
                    if (clicker.equalsIgnoreCase("kantorbayar")) {
                        kantorbayar = bitmap;
                        valkantorbayar = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("fotoakad")) {
                        fotoakad = bitmap;
                        valfotoakad = AppUtil.encodeImageTobase64(bitmap);
                    }

                    // Dokumen Tambahan
                    if (clicker.equalsIgnoreCase("dokumenA")) {
                        dokumenA= bitmap;
                        valDokumenA = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("dokumenB")) {
                        dokumenB= bitmap;
                        valDokumenB = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("dokumenC")) {
                        dokumenC= bitmap;
                        valDokumenC = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("dokumenD")) {
                        dokumenD= bitmap;
                        valDokumenD = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("dokumenE")) {
                        dokumenE= bitmap;
                        valDokumenE = AppUtil.encodeImageTobase64(bitmap);
                    }

                } catch (Exception e) {
                    if (data.getData() != null) {
                        if (iv != null) {
                            iv.setImageDrawable(getDrawable(R.drawable.ic_pdf_hd));
                        }
                        Uri uriPdf = data.getData();

                        // Dokumen Jaminan
                        if (clicker.equalsIgnoreCase("skpensiun")) {
                            valskpensiun = AppUtil.encodeFileToBase64(this, uriPdf);
                            binding.pvFotoSkPensiun.setVisibility(View.VISIBLE);
                        } else if (clicker.equalsIgnoreCase("skpengangkatan")) {
                            valskpengangkatan = AppUtil.encodeFileToBase64(this, uriPdf);
                            binding.pvFotoSkPengangkatan.setVisibility(View.VISIBLE);
                        } else if (clicker.equalsIgnoreCase("skterakhir")) {
                            valskterakhir = AppUtil.encodeFileToBase64(this, uriPdf);
                            binding.pvFotoSkTerakhir.setVisibility(View.VISIBLE);
                        }

                        // Asuransi Khusus
                        if (clicker.equalsIgnoreCase("covernoteasuransi")) {
                            valcovernoteasuransi = AppUtil.encodeFileToBase64(this, uriPdf);
                            binding.pvFotoCovernoteAsuransi.setVisibility(View.VISIBLE);
                        }

                        // Dokumen Produk
                        if (clicker.equalsIgnoreCase("sk")) {
                            valsk = AppUtil.encodeFileToBase64(this, uriPdf);
                            binding.pvSk.setVisibility(View.VISIBLE);
                        } else if (clicker.equalsIgnoreCase("mutasi")) {
                            valmutasi = AppUtil.encodeFileToBase64(this, uriPdf);
                            binding.pvMutasi.setVisibility(View.VISIBLE);
                        } else if (clicker.equalsIgnoreCase("kuasa")) {
                            valkuasa = AppUtil.encodeFileToBase64(this, uriPdf);
                            binding.pvKuasa.setVisibility(View.VISIBLE);
                        } else if (clicker.equalsIgnoreCase("pernyataan")) {
                            valpernyataan = AppUtil.encodeFileToBase64(this, uriPdf);
                            binding.pvPernyataan.setVisibility(View.VISIBLE);
                        } else if (clicker.equalsIgnoreCase("sup")) {
                            valsup = AppUtil.encodeFileToBase64(this, uriPdf);
                            binding.pvSup.setVisibility(View.VISIBLE);
                        }

                        // Tanda Tangan Akad
                        if (clicker.equalsIgnoreCase("ttdakad")) {
                            valttdakad = AppUtil.encodeFileToBase64(this, uriPdf);
                            binding.pvFotoProsesPenandatangananAkad.setVisibility(View.VISIBLE);
                        }

                        // Dokumen Murabahah
                        if (clicker.equalsIgnoreCase("po")) {
                            valpo = AppUtil.encodeFileToBase64(this, uriPdf);
                            binding.pvPo.setVisibility(View.VISIBLE);
                        } else if (clicker.equalsIgnoreCase("akadwakalah")) {
                            valwakalah = AppUtil.encodeFileToBase64(this, uriPdf);
                            binding.pvAkadWakalah.setVisibility(View.VISIBLE);
                        } else if (clicker.equalsIgnoreCase("murabahah")) {
                            valmurabahah = AppUtil.encodeFileToBase64(this, uriPdf);
                            binding.pvAkadMurabahah.setVisibility(View.VISIBLE);
                        } else if (clicker.equalsIgnoreCase("jadwalangsuran")) {
                            valjadwalangsuran = AppUtil.encodeFileToBase64(this, uriPdf);
                            binding.pvJadwalAngsuran.setVisibility(View.VISIBLE);
                        } else if (clicker.equalsIgnoreCase("terimabarang")) {
                            valterimabarang = AppUtil.encodeFileToBase64(this, uriPdf);
                            binding.pvTerimaBarang.setVisibility(View.VISIBLE);
                        }

                        // Dokumen Ijarah
                        if (clicker.equalsIgnoreCase("poijarah")) {
                            valpoijarah = AppUtil.encodeFileToBase64(this, uriPdf);
                        } else if (clicker.equalsIgnoreCase("wakalahijarah")) {
                            valwakalahijarah = AppUtil.encodeFileToBase64(this, uriPdf);
                        } else if (clicker.equalsIgnoreCase("ijarah")) {
                            valijarah = AppUtil.encodeFileToBase64(this, uriPdf);
                        } else if (clicker.equalsIgnoreCase("angsuranujrah")) {
                            valangsuranujrah = AppUtil.encodeFileToBase64(this, uriPdf);
                        }

                        // Dokumen MMQ
                        if (clicker.equalsIgnoreCase("assetmmq")) {
                            valassetmmq = AppUtil.encodeFileToBase64(this, uriPdf);
                        } else if (clicker.equalsIgnoreCase("akadmmq")) {
                            valakadmmq = AppUtil.encodeFileToBase64(this, uriPdf);
                        } else if (clicker.equalsIgnoreCase("jadwalpengambilan")) {
                            valjadwalpengambilan = AppUtil.encodeFileToBase64(this, uriPdf);
                        } else if (clicker.equalsIgnoreCase("pihak1")) {
                            valpihak1 = AppUtil.encodeFileToBase64(this, uriPdf);
                        } else if (clicker.equalsIgnoreCase("pihak3")) {
                            valpihak3 = AppUtil.encodeFileToBase64(this, uriPdf);
                        }

                        // Dokumen Rahn
                        if (clicker.equalsIgnoreCase("akadrahn")) {
                            valakadrahn = AppUtil.encodeFileToBase64(this, uriPdf);
                        } else if (clicker.equalsIgnoreCase("angsuranrahn")) {
                            valangsuranrahn = AppUtil.encodeFileToBase64(this, uriPdf);
                        }

                        // Dokumen Pembiayaan
                        if (clicker.equalsIgnoreCase("kantorbayar")) {
                            valkantorbayar = AppUtil.encodeFileToBase64(this, uriPdf);

                        } else if (clicker.equalsIgnoreCase("fotoakad")) {
                            valfotoakad = AppUtil.encodeFileToBase64(this, uriPdf);
                        }

                        // Dokumen Tambahan
                        if (clicker.equalsIgnoreCase("dokumenA")) {
                            valDokumenA = AppUtil.encodeFileToBase64(ActivityUploadDokumen.this, uriPdf);
                        } else if (clicker.equalsIgnoreCase("dokumenB")) {
                            valDokumenB = AppUtil.encodeFileToBase64(ActivityUploadDokumen.this, uriPdf);
                        } else if (clicker.equalsIgnoreCase("dokumenC")) {
                            valDokumenC = AppUtil.encodeFileToBase64(ActivityUploadDokumen.this, uriPdf);
                        } else if (clicker.equalsIgnoreCase("dokumenD")) {
                            valDokumenD = AppUtil.encodeFileToBase64(this, uriPdf);
                        } else if (clicker.equalsIgnoreCase("dokumenE")) {
                            valDokumenE = AppUtil.encodeFileToBase64(this, uriPdf);
                        }

                    }
                }
            }
        }
    }

    private void checkFileTypeThenUpload(String filename, String fileNameDoc, ImageView
            imageView, String val_base64, int uploadCode) {
        if (tipeFile.equalsIgnoreCase("pdf")) {
            filename = idAplikasi + fileNameDoc + ".pdf";
            uploadFile(val_base64, filename, uploadCode);
        } else {
            filename = idAplikasi + fileNameDoc + ".png";
            uploadFile(val_base64, filename, uploadCode);
        }
    }

    public void uploadFile(String base64, String fileName, int uploadCode) {
        ApiClientAdapter apiClientAdapter = new ApiClientAdapter(this);
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUploadFile req = new ReqUploadFile();
        //pantekan uid
        req.setFolderId(AppUtil.getIdFolderLogicalDoc());
        req.setLanguage("en");
        req.setFileB64(base64);
        req.setFileName(fileName);
        Call<ParseResponseFile> call = apiClientAdapter.getApiInterface().uploadFileLogicalDoc(req);
        call.enqueue(new Callback<ParseResponseFile>() {
            @Override
            public void onResponse(Call<ParseResponseFile> call, Response<ParseResponseFile> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {

                    // Jaminan Dokumen
                    if (uploadCode == Intskpensiun) {
                        idskpensiun = response.body().getId();
                        Foto_SK_Pensiun.setFileName(fileName);
                        Foto_SK_Pensiun.setImg(idskpensiun);
                        binding.pvFotoSkPensiun.setVisibility(View.VISIBLE);
                    } else if (uploadCode == Intskpengangkatan) {
                        idskpengangkatan = response.body().getId();
                        Foto_SK_Pengangkatan.setFileName(fileName);
                        Foto_SK_Pengangkatan.setImg(idskpengangkatan);
                        binding.pvFotoSkPengangkatan.setVisibility(View.VISIBLE);
                    } else if (uploadCode == Intskterakhir) {
                        idskterakhir = response.body().getId();
                        Foto_SK_Terakhir.setFileName(fileName);
                        Foto_SK_Terakhir.setImg(idskterakhir);
                        binding.pvFotoSkTerakhir.setVisibility(View.VISIBLE);
                    }

                    // AsuransiKhusus
                    if (uploadCode == Intcovernoteasuransi) {
                    idcovernoteasuransi = response.body().getId();
                    Foto_Covernote_Asuransi.setFileName(fileName);
                    Foto_Covernote_Asuransi.setImg(idcovernoteasuransi);
                    binding.pvFotoCovernoteAsuransi.setVisibility(View.VISIBLE);
                }

                    // Dokumen Produk
                    if (uploadCode == Intsk) {
                        idsk = response.body().getId();
                        Tanda_Terima_Dokumen_SK.setFileName(fileName);
                        Tanda_Terima_Dokumen_SK.setImg(idsk);
                        binding.pvSk.setVisibility(View.VISIBLE);
                    } else if (uploadCode ==Intmutasi) {
                        idmutasi= response.body().getId();
                        Form_Mutasi_Kantor_Bayar_Taspen.setFileName(fileName);
                        Form_Mutasi_Kantor_Bayar_Taspen.setImg(idmutasi);
                        binding.pvMutasi.setVisibility(View.VISIBLE);
                    } else if (uploadCode == Intkuasa) {
                        idkuasa= response.body().getId();
                        Surat_Kuasa_Pernyataan_Flagging.setFileName(fileName);
                        Surat_Kuasa_Pernyataan_Flagging.setImg(idkuasa);
                        binding.pvKuasa.setVisibility(View.VISIBLE);
                    } else if (uploadCode == Intpernyataan) {
                        idpernyataan= response.body().getId();
                        Surat_Pernyataan_Kuasa_Pembiayaan.setFileName(fileName);
                        Surat_Pernyataan_Kuasa_Pembiayaan.setImg(idpernyataan);
                        binding.pvPernyataan.setVisibility(View.VISIBLE);
                    } else if (uploadCode ==Intsup) {
                        idsup= response.body().getId();
                        SUP.setFileName(fileName);
                        SUP.setImg(idsup);
                        binding.pvSup.setVisibility(View.VISIBLE);
                    }

                    // Tanda Tangan Akad
                    if (uploadCode ==Intttdakad) {
                        idttdakad = response.body().getId();
                        Foto_Proses_Penandatanganan_Akad.setFileName(fileName);
                        Foto_Proses_Penandatanganan_Akad.setImg(idttdakad);
                        binding.pvFotoProsesPenandatangananAkad.setVisibility(View.VISIBLE);
                    }

                    // Dokumen Murabahah
                    if (uploadCode ==Intpo) {
                        idpo= response.body().getId();
                        Murabahah_Purchase_Order.setFileName(fileName);
                        Murabahah_Purchase_Order.setImg(idpo);
                        binding.pvPo.setVisibility(View.VISIBLE);
                    } else if (uploadCode ==Intwakalah) {
                        idwakalah= response.body().getId();
                        Murabahah_Akad_Wakalah.setFileName(fileName);
                        Murabahah_Akad_Wakalah.setImg(idwakalah);
                        binding.pvAkadWakalah.setVisibility(View.VISIBLE);
                    } else if (uploadCode ==Intmurabahah) {
                        idmurabahah= response.body().getId();
                        Murabahah_Akad_Murabahah.setFileName(fileName);
                        Murabahah_Akad_Murabahah.setImg(idmurabahah);
                        binding.pvAkadMurabahah.setVisibility(View.VISIBLE);
                    } else if (uploadCode ==Intjadwalangsuran) {
                        idjadwalangsuran= response.body().getId();
                        Murabahah_Lampiran_Jadwal_Angsuran.setFileName(fileName);
                        Murabahah_Lampiran_Jadwal_Angsuran.setImg(idjadwalangsuran);
                        binding.pvJadwalAngsuran.setVisibility(View.VISIBLE);
                    } else if (uploadCode ==Intterimabarang) {
                        idterimabarang= response.body().getId();
                        Murabahah_Surat_Tanda_Terima_Barang.setFileName(fileName);
                        Murabahah_Surat_Tanda_Terima_Barang.setImg(idterimabarang);
                        binding.pvTerimaBarang.setVisibility(View.VISIBLE);
                    }

                    // Dokumen Ijarah
                    if (uploadCode ==Intpoijarah) {
                        idpoijarah= response.body().getId();
                        Ijarah_Purchase_Order.setFileName(fileName);
                        Ijarah_Purchase_Order.setImg(idpoijarah);
                        binding.pvPoIjarah.setVisibility(View.VISIBLE);
                    } else if (uploadCode ==Intwakalahijarah) {
                        idwakalahijarah= response.body().getId();
                        Ijarah_Akad_Wakalah.setFileName(fileName);
                        Ijarah_Akad_Wakalah.setImg(idwakalahijarah);
                        binding.pvAkadWakalahIjarah.setVisibility(View.VISIBLE);
                    } else if (uploadCode ==Intijarah) {
                        idijarah= response.body().getId();
                        Ijarah_Akad_Ijarah.setFileName(fileName);
                        Ijarah_Akad_Ijarah.setImg(idijarah);
                        binding.pvAkadIjarah.setVisibility(View.VISIBLE);
                    } else if (uploadCode ==Intangsuranujrah) {
                        idangsuranujrah= response.body().getId();
                        Ijarah_Lampiran_Jadwal_Angsuran.setFileName(fileName);
                        Ijarah_Lampiran_Jadwal_Angsuran.setImg(idangsuranujrah);
                        binding.pvAngsuranUjrah.setVisibility(View.VISIBLE);
                    }

                    // Dokumen MMQ
                    if (uploadCode ==Intassetmmq) {
                        idassetmmq= response.body().getId();
                        MMQ_Laporan_Penilaian_Aset.setFileName(fileName);
                        MMQ_Laporan_Penilaian_Aset.setImg(idassetmmq);
                        binding.pvAssetMmq.setVisibility(View.VISIBLE);
                    } else if (uploadCode ==Intakadmmq) {
                        idakadmmq= response.body().getId();
                        MMQ_Akad_MMQ.setFileName(fileName);
                        MMQ_Akad_MMQ.setImg(idakadmmq);
                        binding.pvAkadMmq.setVisibility(View.VISIBLE);
                    } else if (uploadCode ==Intjadwalpengambilan) {
                        idjadwalpengambilan= response.body().getId();
                        MMQ_Lampiran_Jadwal_Pengambil_Alihan.setFileName(fileName);
                        MMQ_Lampiran_Jadwal_Pengambil_Alihan.setImg(idjadwalpengambilan);
                        binding.pvJadwalPengambilalihan.setVisibility(View.VISIBLE);
                    } else if (uploadCode ==Intpihak1) {
                        idpihak1= response.body().getId();
                        MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.setFileName(fileName);
                        MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.setImg(idpihak1);
                        binding.pvSuratPernyataanDanKuasaAsetMmqSendiri.setVisibility(View.VISIBLE);
                    } else if (uploadCode ==Intpihak3) {
                        idpihak3= response.body().getId();
                        MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.setFileName(fileName);
                        MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.setImg(idpihak3);
                        binding.pvSuratPernyataanDanKuasaAsetMmqPihakKetiga.setVisibility(View.VISIBLE);
                    }

                    // Dokumen Rahn
                    if (uploadCode ==Intakadrahn) {
                        idakadrahn= response.body().getId();
                        Rahn_Akad_Rahn.setFileName(fileName);
                        Rahn_Akad_Rahn.setImg(idakadrahn);
                        binding.pvAkadRahn.setVisibility(View.VISIBLE);
                    } else if (uploadCode ==Intangsuranrahn) {
                        idangsuranrahn= response.body().getId();
                        Rahn_Lampiran_Jadwal_Angsuran.setFileName(fileName);
                        Rahn_Lampiran_Jadwal_Angsuran.setImg(idangsuranrahn);
                        binding.pvJadwalAngsuranRahn.setVisibility(View.VISIBLE);
                    }

                    // Dokumen Pembiayaan
                    if (uploadCode == Intkantorbayar) {
                        idkantorbayar = response.body().getId();
                        Form_Mutasi_Kantor_Bayar.setFileName(fileName);
                        Form_Mutasi_Kantor_Bayar.setImg(idkantorbayar);
                        binding.pvFormMutasiKantorBayar.setVisibility(View.VISIBLE);
                    } else if (uploadCode == Intfotoakad) {
                        idfotoakad = response.body().getId();
                        Foto_Bukti_Otentikasi_Nasabah.setFileName(fileName);
                        Foto_Bukti_Otentikasi_Nasabah.setImg(idfotoakad);
                        binding.pvFotoScreenCaptureBuktiOtentikasiNasabahSaatAkad.setVisibility(View.VISIBLE);
                    }

                    // Dokumen Tambahan
                    if (uploadCode == IntDokumenA) {
                        idDokumenA = response.body().getId();
                        ReqDocumentUmum doc = new ReqDocumentUmum();
                        doc.setFilename(fileName);
                        doc.setImg(idDokumenA);
                        doc.setKeteranganDokumen(binding.etKeteranganDokumen1.getText().toString());
                        doc.setNamaDokumen(binding.etNamaDokumen1.getText().toString());
                        if (DokumenUmum.size() == 0) {
                            DokumenUmum.add(0, doc);
                        } else {
                            DokumenUmum.get(0).setFilename(fileName);
                            DokumenUmum.get(0).setImg(idDokumenA);
                            DokumenUmum.get(0).setKeteranganDokumen(binding.etKeteranganDokumen1.getText().toString());
                            DokumenUmum.get(0).setNamaDokumen(binding.etNamaDokumen1.getText().toString());
                        }
                    } else if (uploadCode == IntDokumenB) {
                        idDokumenB = response.body().getId();
                        ReqDocumentUmum doc = new ReqDocumentUmum();
                        doc.setFilename(fileName);
                        doc.setImg(idDokumenB);
                        doc.setKeteranganDokumen(binding.etKeteranganDokumen2.getText().toString());
                        doc.setNamaDokumen(binding.etNamaDokumen2.getText().toString());
                        if (DokumenUmum.size() < 1) {
                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
                        } else {
                            if (DokumenUmum.size() <= 1) {
                                DokumenUmum.add(1, doc);
                            } else {
                                DokumenUmum.get(1).setFilename("dokumenB.png");
                                DokumenUmum.get(1).setImg(idDokumenB);
                                DokumenUmum.get(1).setKeteranganDokumen(binding.etKeteranganDokumen2.getText().toString());
                                DokumenUmum.get(1).setNamaDokumen(binding.etNamaDokumen2.getText().toString());
                            }
                        }
                    } else if (uploadCode == IntDokumenC) {
                        idDokumenC = response.body().getId();
                        ReqDocumentUmum doc = new ReqDocumentUmum();
                        doc.setFilename(fileName);
                        doc.setImg(idDokumenC);
                        doc.setKeteranganDokumen(binding.etKeteranganDokumen3.getText().toString());
                        doc.setNamaDokumen(binding.etNamaDokumen3.getText().toString());
                        if (DokumenUmum.size() < 2) {
                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
                        } else {
                            if (DokumenUmum.size() <= 2) {
                                DokumenUmum.add(2, doc);
                            } else {
                                DokumenUmum.get(2).setFilename("dokumenC.png");
                                DokumenUmum.get(2).setImg(idDokumenC);
                                DokumenUmum.get(2).setKeteranganDokumen(binding.etKeteranganDokumen3.getText().toString());
                                DokumenUmum.get(2).setNamaDokumen(binding.etNamaDokumen3.getText().toString());
                            }
                        }
                    } else if (uploadCode == IntDokumenD) {
                        idDokumenD = response.body().getId();
                        ReqDocumentUmum doc = new ReqDocumentUmum();
                        doc.setFilename(fileName);
                        doc.setImg(idDokumenD);
                        doc.setKeteranganDokumen(binding.etKeteranganDokumen4.getText().toString());
                        doc.setNamaDokumen(binding.etNamaDokumen4.getText().toString());
                        if (DokumenUmum.size() < 3) {
                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
                        } else {
                            if (DokumenUmum.size() <= 3) {
                                DokumenUmum.add(3, doc);
                            } else {
                                DokumenUmum.get(3).setFilename("dokumenD.png");
                                DokumenUmum.get(3).setImg(idDokumenD);
                                DokumenUmum.get(3).setKeteranganDokumen(binding.etKeteranganDokumen4.getText().toString());
                                DokumenUmum.get(3).setNamaDokumen(binding.etNamaDokumen4.getText().toString());
                            }
                        }
                    } else if (uploadCode == IntDokumenE) {
                        idDokumenE = response.body().getId();
                        ReqDocumentUmum doc = new ReqDocumentUmum();
                        doc.setFilename(fileName);
                        doc.setImg(idDokumenE);
                        doc.setKeteranganDokumen(binding.etKeteranganDokumen5.getText().toString());
                        doc.setNamaDokumen(binding.etNamaDokumen5.getText().toString());
                        if (DokumenUmum.size() < 4) {
                            AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
                        } else {
                            if (DokumenUmum.size() <= 4) {
                                DokumenUmum.add(4, doc);
                            } else {
                                DokumenUmum.get(4).setFilename("dokumenE.png");
                                DokumenUmum.get(4).setImg(idDokumenE);
                                DokumenUmum.get(4).setKeteranganDokumen(binding.etKeteranganDokumen5.getText().toString());
                                DokumenUmum.get(4).setNamaDokumen(binding.etNamaDokumen5.getText().toString());
                            }
                        }
                        AppUtil.notifsuccess(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Upload Berhasil");
                    }

                } else {
                    AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseFile> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityUploadDokumen.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });

    }

}