package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g3_upload_dokumen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDocument;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDocumentUmum;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqInquery;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateUploadDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UploadDokumen;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityUploadDokumenBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityUploadDokumen extends AppCompatActivity implements CameraListener, View.OnClickListener, ConfirmListener {
    ActivityUploadDokumenBinding binding;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private long idAplikasi;

    Integer addImg = 0;
    Bitmap dokumenA, dokumenB, dokumenC, dokumenD, dokumenE;
    Uri UridokumenA, UridokumenB, UridokumenC, UridokumenD, UridokumenE;
    final int IntDokumenA = 1, IntDokumenB = 2, IntDokumenC = 3, IntDokumenD = 4, IntDokumenE = 5;
    String clicker, valDokumenA, valDokumenB, valDokumenC, valDokumenD, valDokumenE;


    Bitmap skpensiun, skpenangakatan, skterakhir, covernoteasuransi,
            sk, mutasi, kuasa, pernyataan, sup,
            ttdakad, po, wakalah, murabahah, jadwalangsuran, terimabarang,
            poijarah, wakalahijarah, ijarah, angsuranujrah,
            assetmmq, akadmmq, jadwalpengambilan, pihak1, pihak3,
            akadrahn, angsuranrahn,
            kantorbayar, fotoakad;

    Uri Uriskpensiun, Uriskpenangakatan, Uriskterakhir, Uricovernoteasuransi,
            Urisk, Urimutasi, Urikuasa, Uripernyataan, Urisup,
            Urittdakad, Uripo, Uriwakalah, Urimurabahah, Urijadwalangsuran, Uriterimabarang,
            Uripoijarah, Uriwakalahijarah, Uriijarah, Uriangsuranujrah,
            Uriassetmmq, Uriakadmmq, Urijadwalpengambilan, Uripihak1, Uripihak3,
            Uriakadrahn, Uriangsuranrahn,
            Urikantorbayar, Urifotoakad;

    final int Intskpensiun = 6, Intskpenangakatan = 7, Intskterakhir = 8, Intcovernoteasuransi = 9,
            Intsk = 10, Intmutasi = 11, Intkuasa = 12, Intpernyataan = 13, Intsup = 14,
            Intttdakad = 15, Intpo = 16, Intwakalah = 17, Intmurabahah = 18, Intjadwalangsuran = 19, Intterimabarang = 20,
            Intpoijarah = 21, Intwakalahijarah = 22, Intijarah = 23, Intangsuranujrah = 24,
            Intassetmmq = 25, Intakadmmq = 26, Intjadwalpengambilan = 27, Intpihak1 = 28, Intpihak3 = 29,
            Intakadrahn = 30, Intangsuranrahn = 31,
            Intkantorbayar = 32, Intfotoakad = 33;

    String valskpensiun, valskpenangakatan, valskterakhir, valcovernoteasuransi,
            valsk, valmutasi, valkuasa, valpernyataan, valsup,
            valttdakad, valpo, valwakalah, valmurabahah, valjadwalangsuran, valterimabarang,
            valpoijarah, valwakalahijarah, valijarah, valangsuranujrah,
            valassetmmq, valakadmmq, valjadwalpengambilan, valpihak1, valpihak3,
            valakadrahn, valangsuranrahn,
            valkantorbayar, valfotoakad;

    String FNskpensiun, FNskpenangakatan, FNskterakhir, FNcovernoteasuransi,
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
        binding = ActivityUploadDokumenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idAplikasi = Long.parseLong(getIntent().getStringExtra("idAplikasi"));
        hideUploadDokumen();
        hidePreviewButton();
        setContentView(view);
        onclickSelectDialog();
        initdata();
        AppUtil.toolbarRegular(this, "Upload Dokumen");
    }

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
                        if (response.body().getData().get("Form_Mutas_Kantor_Bayar") != null) {
                            String SSFormMutasiKantorBayar = response.body().getData().get("Form_Mutas_Kantor_Bayar").getAsJsonObject().toString();
                            Form_Mutasi_Kantor_Bayar = gson.fromJson(SSFormMutasiKantorBayar, ReqDocument.class);
                            valkantorbayar = Form_Mutasi_Kantor_Bayar.getImg();
                            try {
                                if (Form_Mutasi_Kantor_Bayar.getFileName().substring(Form_Mutasi_Kantor_Bayar.getFileName().length() - 3, Form_Mutasi_Kantor_Bayar.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNkantorbayar = "kantorbayar.pdf";
                                } else {
                                    kantorbayar = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valkantorbayar), 0, AppUtil.decodeImageTobase64(valkantorbayar).length);
                                    FNkantorbayar = "kantorbayar.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvFormMutasiKantorBayar.setVisibility(View.VISIBLE);
                        }
                        if (response.body().getData().get("Form_Mutasi_Kantor_Bayar_Taspen") != null) {
                            String SSFormBayarTaspen = response.body().getData().get("Form_Mutasi_Kantor_Bayar_Taspen").getAsJsonObject().toString();
                            Form_Mutasi_Kantor_Bayar_Taspen = gson.fromJson(SSFormBayarTaspen, ReqDocument.class);
                            valmutasi = Form_Mutasi_Kantor_Bayar_Taspen.getImg();
                            try {
                                if (Form_Mutasi_Kantor_Bayar_Taspen.getFileName().substring(Form_Mutasi_Kantor_Bayar_Taspen.getFileName().length() - 3, Form_Mutasi_Kantor_Bayar_Taspen.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNmutasi = "mutasi.pdf";
                                } else {
                                    mutasi = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valmutasi),0, AppUtil.decodeImageTobase64(valmutasi).length);
                                    FNmutasi = "mutasi.png";
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvMutasi.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Foto_Bukti_Otentikasi_Nasabah") != null) {
                            String SSOtentikasiNasabah = response.body().getData().get("Foto_Bukti_Otentikasi_Nasabah").getAsJsonObject().toString();
                            Foto_Bukti_Otentikasi_Nasabah = gson.fromJson(SSOtentikasiNasabah, ReqDocument.class);
                            valfotoakad = Foto_Bukti_Otentikasi_Nasabah.getImg();
                            try {
                                if (Foto_Bukti_Otentikasi_Nasabah.getFileName().substring(Foto_Bukti_Otentikasi_Nasabah.getFileName().length() - 3, Foto_Bukti_Otentikasi_Nasabah.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNfotoakad = "fotoakad.pdf";
                                } else {
                                    fotoakad = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valfotoakad),0, AppUtil.decodeImageTobase64(valfotoakad).length) ;
                                    FNfotoakad = "fotoakad.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvFotoScreenCaptureBuktiOtentikasiNasabahSaatAkad.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Foto_Covernote_Asuransi") != null) {
                            String SSCovernoteAsuransi = response.body().getData().get("Foto_Covernote_Asuransi").getAsJsonObject().toString();
                            Foto_Covernote_Asuransi = gson.fromJson(SSCovernoteAsuransi, ReqDocument.class);
                            valcovernoteasuransi = Foto_Covernote_Asuransi.getImg();
                            try {
                                if (Foto_Covernote_Asuransi.getFileName().substring(Foto_Covernote_Asuransi.getFileName().length() - 3, Foto_Covernote_Asuransi.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNcovernoteasuransi = "covernoteasuransi.pdf";
                                } else {
                                    covernoteasuransi = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valcovernoteasuransi),0, AppUtil.decodeImageTobase64(valcovernoteasuransi).length);
                                    FNcovernoteasuransi = "covernoteasuransi.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvFotoCovernoteAsuransi.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Foto_Proses_Penandatanganan_Akad") != null) {
                            String SSProsesAkad = response.body().getData().get("Foto_Proses_Penandatanganan_Akad").getAsJsonObject().toString();
                            Foto_Proses_Penandatanganan_Akad = gson.fromJson(SSProsesAkad, ReqDocument.class);
                            valttdakad = Foto_Proses_Penandatanganan_Akad.getImg();
                            try {
                                if (Foto_Proses_Penandatanganan_Akad.getFileName().substring(Foto_Proses_Penandatanganan_Akad.getFileName().length() - 3, Foto_Proses_Penandatanganan_Akad.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNttdakad = "ttdakad.pdf";
                                } else {
                                    ttdakad = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valttdakad),0, AppUtil.decodeImageTobase64(valttdakad).length);
                                    FNttdakad = "ttdakad.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvFotoProsesPenandatangananAkad.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Foto_SK_Pengangkatan") != null) {
                            String SSSkPengangkatan = response.body().getData().get("Foto_SK_Pengangkatan").getAsJsonObject().toString();
                            Foto_SK_Pengangkatan = gson.fromJson(SSSkPengangkatan, ReqDocument.class);
                            valskpenangakatan = Foto_SK_Pengangkatan.getImg();
                            try {
                                if (Foto_SK_Pengangkatan.getFileName().substring(Foto_SK_Pengangkatan.getFileName().length() - 3, Foto_SK_Pengangkatan.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNskpenangakatan = "skpenangkatan.pdf";
                                } else {
                                    skpenangakatan = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valskpenangakatan),0, AppUtil.decodeImageTobase64(valskpenangakatan).length);
                                    FNskpenangakatan = "skpenangkatan.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvFotoSkPengangkatan.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Foto_SK_Pensiun") != null) {
                            String SSSkPensiun = response.body().getData().get("Foto_SK_Pensiun").getAsJsonObject().toString();
                            Foto_SK_Pensiun = gson.fromJson(SSSkPensiun, ReqDocument.class);
                            valskpensiun = Foto_SK_Pensiun.getImg();
                            try {
                                valskpensiun = Foto_SK_Pensiun.getImg();
                                if (Foto_SK_Pensiun.getFileName().substring(Foto_SK_Pensiun.getFileName().length() - 3, Foto_SK_Pensiun.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNskpensiun = "skpensiun.pdf";
                                } else {
                                    skpensiun = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valskpensiun),0, AppUtil.decodeImageTobase64(valskpensiun).length);
                                    FNskpensiun ="skpensiun.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvFotoSkPensiun.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Foto_SK_Terakhir") != null) {
                            String SSSTerakhir = response.body().getData().get("Foto_SK_Terakhir").getAsJsonObject().toString();
                            Foto_SK_Terakhir = gson.fromJson(SSSTerakhir, ReqDocument.class);
                            valskterakhir = Foto_SK_Terakhir.getImg();
                            try {
                                if (FNskterakhir.substring(FNskterakhir.length() - 3, FNskterakhir.length()).equalsIgnoreCase("pdf")) {
                                    FNskterakhir = "skterakhir.pdf";
                                } else {
                                    skterakhir = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valskterakhir),0, AppUtil.decodeImageTobase64(valskterakhir).length);
                                    FNskterakhir = "skterakhir.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvFotoSkTerakhir.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Ijarah_Akad_Ijarah") != null) {
                            String SSIjarahAkad = response.body().getData().get("Ijarah_Akad_Ijarah").getAsJsonObject().toString();
                            Ijarah_Akad_Ijarah = gson.fromJson(SSIjarahAkad, ReqDocument.class);
                            valijarah = Ijarah_Akad_Ijarah.getImg();
                            try {
                                if (Ijarah_Akad_Ijarah.getFileName().substring(Ijarah_Akad_Ijarah.getFileName().length() - 3, Ijarah_Akad_Ijarah.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNijarah = "ijarah.pdf";
                                } else {
                                    ijarah = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valijarah),0, AppUtil.decodeImageTobase64(valijarah).length);
                                    FNijarah = "ijarah.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvAkadIjarah.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Ijarah_Akad_Wakalah") != null) {
                            String SSIjarahAkadWakalah = response.body().getData().get("Ijarah_Akad_Wakalah").getAsJsonObject().toString();
                            Ijarah_Akad_Wakalah = gson.fromJson(SSIjarahAkadWakalah, ReqDocument.class);
                            valwakalahijarah = Ijarah_Akad_Wakalah.getImg();
                            try {
                                if (Ijarah_Akad_Wakalah.getFileName().substring(Ijarah_Akad_Wakalah.getFileName().length() - 3, Ijarah_Akad_Wakalah.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNwakalahijarah = "wakalahijarah.pdf";
                                } else {
                                    wakalahijarah = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valwakalahijarah),0, AppUtil.decodeImageTobase64(valwakalahijarah).length);
                                    FNwakalahijarah = "wakalahijarah.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvAkadWakalahIjarah.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Ijarah_Lampiran_Jadwal_Angsuran") != null) {
                            String SSIjarahLampiranJadwal = response.body().getData().get("Ijarah_Lampiran_Jadwal_Angsuran").getAsJsonObject().toString();
                            Ijarah_Lampiran_Jadwal_Angsuran = gson.fromJson(SSIjarahLampiranJadwal, ReqDocument.class);
                            valangsuranujrah = Ijarah_Lampiran_Jadwal_Angsuran.getImg();
                            try {
                                if (Ijarah_Lampiran_Jadwal_Angsuran.getFileName().substring(Ijarah_Lampiran_Jadwal_Angsuran.getFileName().length() - 3, Ijarah_Lampiran_Jadwal_Angsuran.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNangsuranujrah = "angsuranujrah.pdf";
                                } else {
                                    angsuranujrah = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valangsuranujrah),0, AppUtil.decodeImageTobase64(valangsuranujrah).length);
                                    FNangsuranujrah = "angsuranujrah.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvAngsuranUjrah.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Ijarah_Purchase_Order") != null) {
                            String SSIjarahPurchaseOrder = response.body().getData().get("Ijarah_Purchase_Order").getAsJsonObject().toString();
                            Ijarah_Purchase_Order = gson.fromJson(SSIjarahPurchaseOrder, ReqDocument.class);
                            valpoijarah = Ijarah_Purchase_Order.getImg();
                            try {
                                if (Ijarah_Purchase_Order.getFileName().substring(Ijarah_Purchase_Order.getFileName().length() - 3, Ijarah_Purchase_Order.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNpoijarah = "poijarah.pdf";
                                } else {
                                    poijarah = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valpoijarah),0, AppUtil.decodeImageTobase64(valpoijarah).length);
                                    FNpoijarah = "poijarah.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvPoIjarah.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("MMQ_Akad_MMQ") != null) {
                            String SSAkadMMQ = response.body().getData().get("MMQ_Akad_MMQ").getAsJsonObject().toString();
                            MMQ_Akad_MMQ = gson.fromJson(SSAkadMMQ, ReqDocument.class);
                            valakadmmq = MMQ_Akad_MMQ.getImg();
                            try {
                                if (MMQ_Akad_MMQ.getFileName().substring(MMQ_Akad_MMQ.getFileName().length() - 3, MMQ_Akad_MMQ.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNakadmmq = "akadmmq.pdf";
                                } else {
                                    akadmmq = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valakadmmq),0, AppUtil.decodeImageTobase64(valakadmmq).length);
                                    FNakadmmq = "akadmmq.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvAkadMmq.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("MMQ_Lampiran_Jadwal_Pengambil_Alihan") != null) {
                            String SSMMQJadwalPengambilAlihan = response.body().getData().get("MMQ_Lampiran_Jadwal_Pengambil_Alihan").getAsJsonObject().toString();
                            MMQ_Lampiran_Jadwal_Pengambil_Alihan = gson.fromJson(SSMMQJadwalPengambilAlihan, ReqDocument.class);
                            valjadwalpengambilan = MMQ_Lampiran_Jadwal_Pengambil_Alihan.getImg();
                            try {
                                if (MMQ_Lampiran_Jadwal_Pengambil_Alihan.getFileName().substring(MMQ_Lampiran_Jadwal_Pengambil_Alihan.getFileName().length() - 3, MMQ_Lampiran_Jadwal_Pengambil_Alihan.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNjadwalpengambilan = "jadwalpengambilan.pdf";
                                } else {
                                    jadwalpengambilan = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valjadwalpengambilan),0, AppUtil.decodeImageTobase64(valjadwalpengambilan).length);
                                    FNjadwalpengambilan = "jadwalpengambilan.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvJadwalPengambilalihan.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("MMQ_Laporan_Penilaian_Aset") != null) {
                            String SSMMQPenilaianAset = response.body().getData().get("MMQ_Laporan_Penilaian_Aset").getAsJsonObject().toString();
                            MMQ_Laporan_Penilaian_Aset = gson.fromJson(SSMMQPenilaianAset, ReqDocument.class);
                            valassetmmq = MMQ_Laporan_Penilaian_Aset.getImg();
                            try {
                                if (MMQ_Laporan_Penilaian_Aset.getFileName().substring(MMQ_Laporan_Penilaian_Aset.getFileName().length() - 3, MMQ_Laporan_Penilaian_Aset.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNassetmmq = "assetmmq.pdf";
                                } else {
                                    assetmmq= BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valassetmmq),0, AppUtil.decodeImageTobase64(valassetmmq).length);
                                    FNassetmmq = "assetmmq.png";

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvAssetMmq.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga") != null) {
                            String SSMMQSuratPeryataanAsetKetiga = response.body().getData().get("MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga").getAsJsonObject().toString();
                            MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga = gson.fromJson(SSMMQSuratPeryataanAsetKetiga, ReqDocument.class);
                            valpihak3 = MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.getImg();
                            try {
                                if (MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.getFileName().substring(MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.getFileName().length() - 3, MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNpihak3 = "pihak3.pdf";
                                } else {
                                    pihak3= BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valpihak3),0, AppUtil.decodeImageTobase64(valpihak3).length);
                                    FNpihak3 = "pihak3.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvSuratPernyataanDanKuasaAsetMmqPihakKetiga.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri") != null) {
                            String SSMMQSuratPeryataanAsetSendiri = response.body().getData().get("MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri").getAsJsonObject().toString();
                            MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri = gson.fromJson(SSMMQSuratPeryataanAsetSendiri, ReqDocument.class);
                            valpihak1 = MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.getImg();
                            try {
                                if (MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.getFileName().substring(MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.getFileName().length() - 3, MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNpihak1 = "pihak1.pdf";
                                } else {
                                    pihak1= BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valpihak1),0, AppUtil.decodeImageTobase64(valpihak1).length);
                                    FNpihak1 = "pihak1.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvSuratPernyataanDanKuasaAsetMmqSendiri.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Murabahah_Akad_Murabahah") != null) {
                            String SSMurabahahAkad = response.body().getData().get("Murabahah_Akad_Murabahah").getAsJsonObject().toString();
                            Murabahah_Akad_Murabahah = gson.fromJson(SSMurabahahAkad, ReqDocument.class);
                            valmurabahah = Murabahah_Akad_Murabahah.getImg();
                            try {
                                if (Murabahah_Akad_Murabahah.getFileName().substring(Murabahah_Akad_Murabahah.getFileName().length() - 3, Murabahah_Akad_Murabahah.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNmurabahah = "murabahah.pdf";
                                } else {
                                    murabahah= BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valmurabahah),0, AppUtil.decodeImageTobase64(valmurabahah).length);
                                    FNmurabahah = "murabahah.pdf";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvAkadMurabahah.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Murabahah_Surat_Tanda_Terima_Barang") != null) {
                            String SSMurabahahSuratTandaTerima = response.body().getData().get("Murabahah_Surat_Tanda_Terima_Barang").getAsJsonObject().toString();
                            Murabahah_Surat_Tanda_Terima_Barang = gson.fromJson(SSMurabahahSuratTandaTerima, ReqDocument.class);
                            valterimabarang = Murabahah_Surat_Tanda_Terima_Barang.getImg();
                            try {
                                if (Murabahah_Surat_Tanda_Terima_Barang.getFileName().substring(Murabahah_Surat_Tanda_Terima_Barang.getFileName().length() - 3, Murabahah_Surat_Tanda_Terima_Barang.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNterimabarang = "terimabarang.pdf";
                                } else {
                                    terimabarang= BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valterimabarang),0, AppUtil.decodeImageTobase64(valterimabarang).length);
                                    FNterimabarang = "terimabarang.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvTerimaBarang.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Murabahah_Lampiran_Jadwal_Angsuran") != null) {
                            String SSMurabahahLampiranJadwalAngsuran = response.body().getData().get("Murabahah_Lampiran_Jadwal_Angsuran").getAsJsonObject().toString();
                            Murabahah_Lampiran_Jadwal_Angsuran = gson.fromJson(SSMurabahahLampiranJadwalAngsuran, ReqDocument.class);
                            valjadwalangsuran = Murabahah_Lampiran_Jadwal_Angsuran.getImg();
                            try {
                                if (Murabahah_Lampiran_Jadwal_Angsuran.getFileName().substring(Murabahah_Lampiran_Jadwal_Angsuran.getFileName().length() - 3, Murabahah_Lampiran_Jadwal_Angsuran.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNjadwalangsuran = "jadwalangsuran.pdf";
                                } else {
                                    jadwalangsuran= BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valjadwalangsuran),0, AppUtil.decodeImageTobase64(valjadwalangsuran).length);
                                    FNjadwalangsuran = "jadwalangsuran.png";
                                }
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                            binding.pvJadwalAngsuran.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Murabahah_Akad_Wakalah") != null) {
                            String SSMurabahahAkadWakalah = response.body().getData().get("Murabahah_Akad_Wakalah").getAsJsonObject().toString();
                            Murabahah_Akad_Wakalah = gson.fromJson(SSMurabahahAkadWakalah, ReqDocument.class);
                            try {
                                valwakalah = Murabahah_Akad_Wakalah.getImg();

                                if (Murabahah_Akad_Wakalah.getFileName().substring(Murabahah_Akad_Wakalah.getFileName().length() - 3, Murabahah_Akad_Wakalah.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNwakalah = "akadwakalah.pdf";
                                } else {
                                    wakalah= BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valwakalah),0, AppUtil.decodeImageTobase64(valwakalah).length);
                                    FNwakalah = "akadwakalah.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvAkadWakalah.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Murabahah_Purchase_Order") != null) {
                            String SSMurabahahPurchaseOrder = response.body().getData().get("Murabahah_Purchase_Order").getAsJsonObject().toString();
                            Murabahah_Purchase_Order = gson.fromJson(SSMurabahahPurchaseOrder, ReqDocument.class);
                            valpo = Murabahah_Purchase_Order.getImg();
                            try {
                                if (Murabahah_Purchase_Order.getFileName().substring(Murabahah_Purchase_Order.getFileName().length() - 3, Murabahah_Purchase_Order.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNpo = "po.pdf";
                                } else {
                                    po = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valpo),0, AppUtil.decodeImageTobase64(valpo).length);
                                    FNpo = "po.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvPo.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Rahn_Lampiran_Jadwal_Angsuran") != null) {
                            String SSRahnLampiranJadwalAngsuran = response.body().getData().get("Rahn_Lampiran_Jadwal_Angsuran").getAsJsonObject().toString();
                            Rahn_Lampiran_Jadwal_Angsuran = gson.fromJson(SSRahnLampiranJadwalAngsuran, ReqDocument.class);
                            valjadwalangsuran = Rahn_Lampiran_Jadwal_Angsuran.getImg();
                            try {
                                if (Rahn_Lampiran_Jadwal_Angsuran.getFileName().substring(Rahn_Lampiran_Jadwal_Angsuran.getFileName().length() - 3, Rahn_Lampiran_Jadwal_Angsuran.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNjadwalangsuran = "angsuranrahn.pdf";
                                } else {
                                    angsuranrahn = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valangsuranrahn),0, AppUtil.decodeImageTobase64(valangsuranrahn).length);
                                    FNjadwalangsuran = "angsuranrahn.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvJadwalAngsuran.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Rahn_Akad_Rahn") != null) {
                            String SSAkadRahn = response.body().getData().get("Rahn_Akad_Rahn").getAsJsonObject().toString();
                            Rahn_Akad_Rahn = gson.fromJson(SSAkadRahn, ReqDocument.class);
                            valakadrahn = Rahn_Akad_Rahn.getImg();
                            try {
                                if (Rahn_Akad_Rahn.getFileName().substring(Rahn_Akad_Rahn.getFileName().length() - 3, Rahn_Akad_Rahn.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNakadrahn = "akadrahn.pdf";
                                } else {
                                    akadrahn= BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valakadrahn),0, AppUtil.decodeImageTobase64(valakadrahn).length);
                                    FNakadrahn = "akadrahn.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvAkadRahn.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("SUP") != null) {
                            String SSSup = response.body().getData().get("SUP").getAsJsonObject().toString();
                            SUP = gson.fromJson(SSSup, ReqDocument.class);
                            valsup = SUP.getImg();
                            try {
                                if (SUP.getFileName().substring(SUP.getFileName().length() - 3, SUP.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNsup = "sup.pdf";
                                } else {
                                    sup= BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valsup),0, AppUtil.decodeImageTobase64(valsup).length);
                                    FNsup = "sup.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvSup.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Surat_Kuasa_Pernyataan_Flagging") != null) {

                            String SSSuratKuasaPernyataanFlagging = response.body().getData().get("Surat_Kuasa_Pernyataan_Flagging").getAsJsonObject().toString();
                            Surat_Kuasa_Pernyataan_Flagging = gson.fromJson(SSSuratKuasaPernyataanFlagging, ReqDocument.class);
                            valkuasa = Surat_Kuasa_Pernyataan_Flagging.getImg();
                            try {
                                if (Surat_Kuasa_Pernyataan_Flagging.getFileName().substring(Surat_Kuasa_Pernyataan_Flagging.getFileName().length() - 3, Surat_Kuasa_Pernyataan_Flagging.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNkuasa = "kuasa.pdf";
                                } else {
                                    pernyataan= BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valkuasa),0, AppUtil.decodeImageTobase64(valkuasa).length);
                                    FNkuasa = "kuasa.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvKuasa.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Surat_Pernyataan_Kuasa_Pembiayaan") != null) {

                            String SSSuratKuasaPembiayaan = response.body().getData().get("Surat_Pernyataan_Kuasa_Pembiayaan").getAsJsonObject().toString();
                            Surat_Pernyataan_Kuasa_Pembiayaan = gson.fromJson(SSSuratKuasaPembiayaan, ReqDocument.class);
                            valpernyataan = Surat_Pernyataan_Kuasa_Pembiayaan.getImg();
                            try {
                                if (Surat_Pernyataan_Kuasa_Pembiayaan.getFileName().substring(Surat_Pernyataan_Kuasa_Pembiayaan.getFileName().length() - 3, Surat_Pernyataan_Kuasa_Pembiayaan.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNpernyataan = "pernyataan.pdf";
                                } else {
                                    kuasa= BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valpernyataan),0, AppUtil.decodeImageTobase64(valpernyataan).length);
                                    FNpernyataan = "pernyataan.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvPernyataan.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Tanda_Terima_Dokumen_SK") != null) {
                            String SSTandaTerimaDokumenSK = response.body().getData().get("Tanda_Terima_Dokumen_SK").getAsJsonObject().toString();
                            Tanda_Terima_Dokumen_SK = gson.fromJson(SSTandaTerimaDokumenSK, ReqDocument.class);
                            valsk = Tanda_Terima_Dokumen_SK.getImg();
                            try {
                                if (Tanda_Terima_Dokumen_SK.getFileName().substring(Tanda_Terima_Dokumen_SK.getFileName().length() - 3, Tanda_Terima_Dokumen_SK.getFileName().length()).equalsIgnoreCase("pdf")) {
                                    FNsk = "sk.pdf";
                                } else {
                                    sk = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valsk),0, AppUtil.decodeImageTobase64(valsk).length);
                                    FNsk = "sk.png";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            binding.pvSk.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get("Dokumen_Umum") != null) {
                            String SSDokumen_Umum = response.body().getData().get("Dokumen_Umum").toString();
                            Type type = new TypeToken<List<ReqDocumentUmum>>() {
                            }.getType();
                            DokumenUmum = gson.fromJson(SSDokumen_Umum, type);
                            for (int i = 0; i < DokumenUmum.size(); i++) {
                                if (i == 0) {
                                    valDokumenA = DokumenUmum.get(i).getImg();
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
                                        valDokumenA = DokumenUmum.get(i).getImg();
                                        if (DokumenUmum.get(i).getFilename().substring(DokumenUmum.get(i).getFilename().length() - 3, DokumenUmum.get(i).getFilename().length()).equalsIgnoreCase("pdf")) {
                                            AppUtil.convertBase64ToFileWithOnClick(ActivityUploadDokumen.this, valDokumenA, binding.ivUploadDokumen1, "dokumenA.pdf");
                                        } else {
                                            AppUtil.convertBase64ToImage(valDokumenA, binding.ivUploadDokumen1);
                                            dokumenA = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valDokumenA), 0, AppUtil.decodeImageTobase64(valDokumenA).length);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if (i == 1) {
                                    try {
                                        valDokumenB = DokumenUmum.get(i).getImg();
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
                                        if (DokumenUmum.get(i).getNamaDokumen().substring(DokumenUmum.get(i).getFilename().length() - 3, DokumenUmum.get(i).getFilename().length()).equalsIgnoreCase("pdf")) {
                                            AppUtil.convertBase64ToFileWithOnClick(ActivityUploadDokumen.this, valDokumenB, binding.ivUploadDokumen2, "dokumenB.pdf");
                                        } else {
                                            AppUtil.convertBase64ToImage(valDokumenB, binding.ivUploadDokumen2);
                                            dokumenB = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valDokumenB), 0, AppUtil.decodeImageTobase64(valDokumenB).length);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if (i == 2) {
                                    try {
                                        valDokumenC = DokumenUmum.get(i).getImg();
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
                                        if (DokumenUmum.get(i).getNamaDokumen().substring(DokumenUmum.get(i).getFilename().length() - 3, DokumenUmum.get(i).getFilename().length()).equalsIgnoreCase("pdf")) {
                                            AppUtil.convertBase64ToFileWithOnClick(ActivityUploadDokumen.this, valDokumenC, binding.ivUploadDokumen3, "dokumenC.pdf");
                                        } else {
                                            AppUtil.convertBase64ToImage(valDokumenC, binding.ivUploadDokumen3);
                                            dokumenC = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valDokumenC), 0, AppUtil.decodeImageTobase64(valDokumenC).length);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if (i == 3) {
                                    try {
                                        valDokumenD = DokumenUmum.get(i).getImg();
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
                                        if (DokumenUmum.get(i).getNamaDokumen().substring(DokumenUmum.get(i).getFilename().length() - 3, DokumenUmum.get(i).getFilename().length()).equalsIgnoreCase("pdf")) {
                                            AppUtil.convertBase64ToFileWithOnClick(ActivityUploadDokumen.this, valDokumenD, binding.ivUploadDokumen4, "dokumenD.pdf");
                                        } else {
                                            AppUtil.convertBase64ToImage(valDokumenD, binding.ivUploadDokumen4);
                                            dokumenD = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valDokumenD), 0, AppUtil.decodeImageTobase64(valDokumenD).length);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if (i == 4) {
                                    try {
                                        valDokumenE = DokumenUmum.get(i).getImg();
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
                                        if (DokumenUmum.get(i).getNamaDokumen().substring(DokumenUmum.get(i).getFilename().length() - 3, DokumenUmum.get(i).getFilename().length()).equalsIgnoreCase("pdf")) {
                                            AppUtil.convertBase64ToFileWithOnClick(ActivityUploadDokumen.this, valDokumenE, binding.ivUploadDokumen5, "dokumenE.pdf");
                                        } else {
                                            AppUtil.convertBase64ToImage(valDokumenE, binding.ivUploadDokumen5);
                                            dokumenE = BitmapFactory.decodeByteArray(AppUtil.decodeImageTobase64(valDokumenE), 0, AppUtil.decodeImageTobase64(valDokumenE).length);
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
                clicker = "skpenangakatan";
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
                clicker = "akadijarah";
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
                if (FNskpensiun.substring(FNskpensiun.length() - 3, FNskpensiun.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valskpensiun, FNskpensiun);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", skpensiun);
                }
                break;
            case R.id.pv_foto_sk_pengangkatan:
                if (FNskpenangakatan.substring(FNskpenangakatan.length() - 3, FNskpenangakatan.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valskpenangakatan, FNskpenangakatan);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", skpenangakatan);
                }
                break;
            case R.id.pv_foto_sk_terakhir:
                if (FNskterakhir.substring(FNskterakhir.length() - 3, FNskterakhir.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valskterakhir, FNskterakhir);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", skterakhir);
                }
                break;
            // Asuransi Khusus
            case R.id.pv_foto_covernote_asuransi:
                if (FNcovernoteasuransi.substring(FNcovernoteasuransi.length() - 3, FNcovernoteasuransi.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valcovernoteasuransi, FNcovernoteasuransi);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", covernoteasuransi);
                }
                break;
            //Dokumen Produk

            case R.id.pv_sk:
                if (FNsk.substring(FNsk.length() - 3, FNsk.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valsk, FNsk);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", sk);
                }
                break;
            case R.id.pv_mutasi:
                if (FNmutasi.substring(FNmutasi.length() - 3, FNmutasi.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valmutasi, FNmutasi);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", mutasi);
                }
                break;
            case R.id.pv_kuasa:
                if (FNkuasa.substring(FNkuasa.length() - 3, FNkuasa.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valkuasa, FNkuasa);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", kuasa);
                }
                break;
            case R.id.pv_pernyataan:
                if (FNpernyataan.substring(FNpernyataan.length() - 3, FNpernyataan.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valpernyataan, FNpernyataan);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", pernyataan);
                }
                break;
            case R.id.pv_sup:
                if (FNsup.substring(FNsup.length() - 3, FNsup.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valsup, FNsup);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", sup);
                }
                break;

            // Tanda Tangan Akad
            case R.id.pv_foto_proses_penandatanganan_akad:
                if (FNttdakad.substring(FNttdakad.length() - 3, FNttdakad.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valttdakad, FNttdakad);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", ttdakad);
                }
                break;
            // Dokumen Murabahah
            case R.id.pv_po:
                if (FNpo.substring(FNpo.length() - 3, FNpo.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valpo, FNpo);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", po);
                }
                break;
            case R.id.pv_akad_wakalah:
                if (FNwakalah.substring(FNwakalah.length() - 3, FNwakalah.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valwakalah, FNwakalah);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", wakalah);
                }
                break;
            case R.id.pv_akad_murabahah:
                if (FNmurabahah.substring(FNmurabahah.length() - 3, FNmurabahah.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valmurabahah, FNmurabahah);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", murabahah);
                }
                break;
            case R.id.pv_jadwal_angsuran:
                if (FNjadwalangsuran.substring(FNjadwalangsuran.length() - 3, FNjadwalangsuran.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valjadwalangsuran, FNjadwalangsuran);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", jadwalangsuran);
                }
                break;
            case R.id.pv_terima_barang:
                if (FNterimabarang.substring(FNterimabarang.length() - 3, FNterimabarang.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valterimabarang, FNterimabarang);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", terimabarang);
                }
                break;
            // Dokumen Ijarah

            case R.id.pv_po_Ijarah:
                if (FNpoijarah.substring(FNpoijarah.length() - 3, FNpoijarah.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valpoijarah, FNpoijarah);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", poijarah);
                }
                break;
            case R.id.pv_akad_wakalah_Ijarah:
                if (FNwakalahijarah.substring(FNwakalahijarah.length() - 3, FNwakalahijarah.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valwakalahijarah, FNwakalahijarah);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", wakalahijarah);
                }
                break;
            case R.id.pv_akad_ijarah:
                if (FNijarah.substring(FNijarah.length() - 3, FNijarah.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valijarah, FNijarah);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", ijarah);
                }
                break;
            case R.id.pv_angsuran_ujrah:
                if (FNangsuranujrah.substring(FNangsuranujrah.length() - 3, FNangsuranujrah.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valangsuranujrah, FNangsuranujrah);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", angsuranujrah);
                }
                break;
            // Dokumen MMQ
            case R.id.pv_asset_mmq:
                if (FNassetmmq.substring(FNassetmmq.length() - 3, FNassetmmq.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valassetmmq, FNassetmmq);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", assetmmq);
                }
                break;
            case R.id.pv_akad_mmq:
                if (FNakadmmq.substring(FNakadmmq.length() - 3, FNakadmmq.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valakadmmq, FNakadmmq);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", akadmmq);
                }
                break;
            case R.id.pv_jadwal_pengambilalihan:
                if (FNjadwalpengambilan.substring(FNjadwalpengambilan.length() - 3, FNjadwalpengambilan.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valjadwalpengambilan, FNjadwalpengambilan);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", jadwalpengambilan);
                }
                break;
            case R.id.pv_surat_pernyataan_dan_kuasa_aset_mmq_sendiri:
                if (FNpihak1.substring(FNpihak1.length() - 3, FNpihak1.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valpihak1, FNpihak1);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", pihak1);
                }
                break;
            case R.id.pv_surat_pernyataan_dan_kuasa_aset_mmq_pihak_ketiga:
                if (FNpihak3.substring(FNpihak3.length() - 3, FNpihak3.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valpihak3, FNpihak3);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", pihak3);
                }
                break;
            // Dokumen Rahn

            case R.id.pv_akad_rahn:
                if (FNakadrahn.substring(FNakadrahn.length() - 3, FNakadrahn.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valakadrahn, FNakadrahn);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", akadrahn);
                }
                break;
            case R.id.pv_jadwal_angsuran_rahn:
                if (FNangsuranrahn.substring(FNangsuranrahn.length() - 3, FNangsuranrahn.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valangsuranrahn, FNangsuranrahn);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", angsuranrahn);
                }
                break;

            // FORM PEMBIAYAAN
            case R.id.pv_form_mutasi_kantor_bayar:
                if (FNkantorbayar.substring(FNkantorbayar.length() - 3, FNkantorbayar.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valkantorbayar, FNkantorbayar);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Image", kantorbayar);
                }
                break;

            case R.id.pv_foto_screen_capture_bukti_otentikasi_nasabah_saat_akad:
                if (FNfotoakad.substring(FNfotoakad.length() - 3, FNfotoakad.length()).equalsIgnoreCase("pdf")) {
                    AppUtil.convertBase64ToFileAutoOpen(ActivityUploadDokumen.this, valfotoakad, FNfotoakad);
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
            case R.id.btn_cek_lngp:
            case R.id.ll_btn_send:
            case R.id.btn_send:
                Validate();
                sendData();
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
        upD.setForm_Mutas_Kantor_Bayar(Form_Mutasi_Kantor_Bayar);
        upD.setForm_Mutasi_Kantor_Bayar_Taspen(Form_Mutasi_Kantor_Bayar_Taspen);
        upD.setFoto_Bukti_Otentikasi_Nasabah(Foto_Bukti_Otentikasi_Nasabah);
        upD.setFoto_Covernote_Asuransi(Foto_Covernote_Asuransi);
        upD.setFoto_Proses_Penandatanganan_Akad(Foto_Proses_Penandatanganan_Akad);
        upD.setFoto_SK_Pengangkatan(Foto_SK_Pengangkatan);
        upD.setFoto_SK_Pensiun(Foto_SK_Pensiun);
        upD.setFoto_SK_Terakhir(Foto_SK_Terakhir);
        upD.setIjarah_Akad_Ijarah(Ijarah_Akad_Ijarah);
        upD.setIjarah_Akad_Wakalah(Ijarah_Akad_Wakalah);
        upD.setIjarah_Lampiran_Jadwal_Angsuran(Ijarah_Lampiran_Jadwal_Angsuran);
        upD.setIjarah_Purchase_Order(Ijarah_Purchase_Order);
        upD.setMMQ_Akad_MMQ(MMQ_Akad_MMQ);
        upD.setMMQ_Lampiran_Jadwal_Pengambil_Alihan(MMQ_Lampiran_Jadwal_Pengambil_Alihan);
        upD.setMMQ_Laporan_Penilaian_Aset(MMQ_Laporan_Penilaian_Aset);
        upD.setMMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga(MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga);
        upD.setMMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri(MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri);
        upD.setMurabahah_Akad_Murabahah(Murabahah_Akad_Murabahah);
        upD.setMurabahah_Akad_Wakalah(Murabahah_Akad_Wakalah);
        upD.setMurabahah_Lampiran_Jadwal_Angsuran(Murabahah_Lampiran_Jadwal_Angsuran);
        upD.setMurabahah_Purchase_Order(Murabahah_Purchase_Order);
        upD.setDokumenUmum(DokumenUmum);
        req.setUploadDokumen(upD);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().UploadDokumenUmum(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
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

    @Override
    public void onSelectMenuCamera(String idMenu) {
        switch (idMenu) {
            case "Take Photo":

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
                } else if (clicker.equalsIgnoreCase("skpenangakatan")) {
                    openCamera(Intskpenangakatan, "skpenangakatan");
                } else if (clicker.equalsIgnoreCase("skterakhir")) {
                    openCamera(Intskpenangakatan, "skterakhir");
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
                } else if (clicker.equalsIgnoreCase("skpenangakatan")) {
                    openGalery(Intskpenangakatan);
                } else if (clicker.equalsIgnoreCase("skterakhir")) {
                    openGalery(Intskpenangakatan);
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
                } else if (clicker.equalsIgnoreCase("skpenangakatan")) {
                    openFile(Intskpenangakatan);
                } else if (clicker.equalsIgnoreCase("skterakhir")) {
                    openFile(Intskpenangakatan);
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

    private void directOpenCamera(int cameraCode, String namaFoto) {
        Uri outputFileUri = getCaptureImageOutputUri(namaFoto);
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(captureIntent, cameraCode);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (resultCode != 0) {
            switch (requestCode) {
                case IntDokumenA:
                    setDataImage(UridokumenA, dokumenA, binding.ivUploadDokumen1, imageReturnedIntent, "dokumenA");
                    break;
                case IntDokumenB:
                    setDataImage(UridokumenB, dokumenB, binding.ivUploadDokumen2, imageReturnedIntent, "dokumenB");
                    break;
                case IntDokumenC:
                    setDataImage(UridokumenC, dokumenC, binding.ivUploadDokumen3, imageReturnedIntent, "dokumenC");
                    break;
                case IntDokumenD:
                    setDataImage(UridokumenD, dokumenD, binding.ivUploadDokumen4, imageReturnedIntent, "dokumenD");
                    break;
                case IntDokumenE:
                    setDataImage(UridokumenE, dokumenE, binding.ivUploadDokumen5, imageReturnedIntent, "dokumenE");
                    break;
                // Dokumen Jaminan
                case Intskpensiun:
                    setDataImage(Uriskpensiun, skpensiun, null, imageReturnedIntent, "skpensiun");
                    break;
                case Intskpenangakatan:
                    setDataImage(Uriskpenangakatan, skpenangakatan, null, imageReturnedIntent, "skpenangakatan");
                    break;
                case Intskterakhir:
                    setDataImage(Uriskterakhir, skterakhir, null, imageReturnedIntent, "skterakhir");
                    break;
                // Asuransi Khusus
                case Intcovernoteasuransi:
                    setDataImage(Uricovernoteasuransi, covernoteasuransi, null, imageReturnedIntent, "covernoteasuransi");
                    break;
                // Dokumen Produk
                case Intsk:
                    setDataImage(Urisk, sk, null, imageReturnedIntent, "sk");
                    break;
                case Intmutasi:
                    setDataImage(Urimutasi, mutasi, null, imageReturnedIntent, "mutasi");
                    break;
                case Intkuasa:
                    setDataImage(Urikuasa, kuasa, null, imageReturnedIntent, "kuasa");
                    break;
                case Intpernyataan:
                    setDataImage(Uripernyataan, pernyataan, null, imageReturnedIntent, "pernyataan");
                    break;
                case Intsup:
                    setDataImage(Urisup, sup, null, imageReturnedIntent, "sup");
                    break;
                // Tanda Tangan Akad
                case Intttdakad:
                    setDataImage(Urittdakad, ttdakad, null, imageReturnedIntent, "ttdakad");
                    break;
                // Dokumen Murabahah
                case Intpo:
                    setDataImage(Uripo, po, null, imageReturnedIntent, "po");
                    break;
                case Intwakalah:
                    setDataImage(Uriwakalah, wakalah, null, imageReturnedIntent, "wakalah");
                    break;
                case Intmurabahah:
                    setDataImage(Urimurabahah, murabahah, null, imageReturnedIntent, "murabahah");
                    break;
                case Intjadwalangsuran:
                    setDataImage(Urijadwalangsuran, jadwalangsuran, null, imageReturnedIntent, "jadwalangsuran");
                    break;
                case Intterimabarang:
                    setDataImage(Uriterimabarang, terimabarang, null, imageReturnedIntent, "terimabarang");
                    break;
                // Dokumen Ijarah
                case Intpoijarah:
                    setDataImage(Uripoijarah, poijarah, null, imageReturnedIntent, "poijarah");
                    break;
                case Intwakalahijarah:
                    setDataImage(Uriwakalahijarah, wakalahijarah, null, imageReturnedIntent, "wakalahijarah");
                    break;
                case Intijarah:
                    setDataImage(Uriijarah, ijarah, null, imageReturnedIntent, "ijarah");
                    break;
                case Intangsuranujrah:
                    setDataImage(Uriangsuranujrah, angsuranujrah, null, imageReturnedIntent, "angsuranujrah");
                    break;
                // Dokumen MMQ

                case Intassetmmq:
                    setDataImage(Uriassetmmq, assetmmq, null, imageReturnedIntent, "assetmmq");
                    break;
                case Intakadmmq:
                    setDataImage(Uriakadmmq, akadmmq, null, imageReturnedIntent, "akadmmq");
                    break;
                case Intjadwalpengambilan:
                    setDataImage(Urijadwalpengambilan, jadwalpengambilan, null, imageReturnedIntent, "jadwalpengambilan");
                    break;
                case Intpihak1:
                    setDataImage(Uripihak1, pihak1, null, imageReturnedIntent, "pihak1");
                    break;
                case Intpihak3:
                    setDataImage(Uripihak3, pihak3, null, imageReturnedIntent, "pihak3");
                    break;
                // Dokumen MMQ

                case Intakadrahn:
                    setDataImage(Uriakadrahn, akadrahn, null, imageReturnedIntent, "akadrahn");
                    break;
                case Intangsuranrahn:
                    setDataImage(Uriangsuranrahn, angsuranrahn, null, imageReturnedIntent, "angsuranrahn");
                    break;
                // Dokumen Pembiayaan

                case Intkantorbayar:
                    setDataImage(Urikantorbayar, kantorbayar, null, imageReturnedIntent, "kantorbayar");
                    break;
                case Intfotoakad:
                    setDataImage(Urifotoakad, fotoakad, null, imageReturnedIntent, "fotoakad");
                    break;
            }
        }
    }

    public Uri getPickImageResultUri(Intent data, String namaFoto) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri(namaFoto) : data.getData();
    }

    private void setDataImage(Uri uri, Bitmap bitmap, ImageView iv, Intent data, String namaFoto) {
        if (getPickImageResultUri(data, namaFoto) != null) {
            uri = getPickImageResultUri(data, namaFoto);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                bitmap = AppUtil.rotateImageIfRequired(this, bitmap, uri);

                if (clicker.equalsIgnoreCase("dokumenA")) {
                    dokumenA = bitmap;
                    ReqDocumentUmum doc = new ReqDocumentUmum();
                    doc.setFilename("dokumenA.png");
                    doc.setImg(AppUtil.encodeImageTobase64(bitmap));
                    doc.setKeteranganDokumen(binding.etKeteranganDokumen1.getText().toString());
                    doc.setNamaDokumen(binding.etNamaDokumen1.getText().toString());
                    if (DokumenUmum.get(0) == null) {
                        DokumenUmum.add(0, doc);
                    }else {
                        DokumenUmum.get(0).setFilename("dokumenA.png");
                        DokumenUmum.get(0).setImg(valDokumenA);
                        DokumenUmum.get(0).setKeteranganDokumen(binding.etKeteranganDokumen1.getText().toString());
                        DokumenUmum.get(0).setNamaDokumen(binding.etNamaDokumen1.getText().toString());
                    }
                    valDokumenA = AppUtil.encodeImageTobase64(bitmap);
                    iv.setImageBitmap(bitmap);
                } else if (clicker.equalsIgnoreCase("dokumenB")) {
                    dokumenB = bitmap;
                    ReqDocumentUmum doc = new ReqDocumentUmum();
                    doc.setFilename("dokumenB.png");
                    doc.setImg(AppUtil.encodeImageTobase64(bitmap));
                    doc.setKeteranganDokumen(binding.etKeteranganDokumen2.getText().toString());
                    doc.setNamaDokumen(binding.etNamaDokumen2.getText().toString());
                    if (DokumenUmum.size() < 1) {
                        AppUtil.notiferror(this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
                    } else {
                        if (DokumenUmum.get(1) == null) {
                            DokumenUmum.add(1, doc);
                        }else {
                            DokumenUmum.get(1).setFilename("dokumenB.png");
                            DokumenUmum.get(1).setImg(valDokumenB);
                            DokumenUmum.get(1).setKeteranganDokumen(binding.etKeteranganDokumen2.getText().toString());
                            DokumenUmum.get(1).setNamaDokumen(binding.etNamaDokumen2.getText().toString());
                        }
                        iv.setImageBitmap(bitmap);
                        valDokumenB = AppUtil.encodeImageTobase64(bitmap);
                    }
                } else if (clicker.equalsIgnoreCase("dokumenC")) {
                    dokumenC = bitmap;
                    ReqDocumentUmum doc = new ReqDocumentUmum();
                    doc.setFilename("dokumenC.png");
                    doc.setImg(AppUtil.encodeImageTobase64(bitmap));
                    doc.setKeteranganDokumen(binding.etKeteranganDokumen3.getText().toString());
                    doc.setNamaDokumen(binding.etNamaDokumen3.getText().toString());
                    if (DokumenUmum.size() < 2) {
                        AppUtil.notiferror(this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
                    } else {
                        if (DokumenUmum.get(2) == null) {
                            DokumenUmum.add(2, doc);
                        }else {
                            DokumenUmum.get(2).setFilename("dokumenC.png");
                            DokumenUmum.get(2).setImg(valDokumenC);
                            DokumenUmum.get(2).setKeteranganDokumen(binding.etKeteranganDokumen3.getText().toString());
                            DokumenUmum.get(2).setNamaDokumen(binding.etNamaDokumen3.getText().toString());
                        }
                        iv.setImageBitmap(bitmap);
                        valDokumenC = AppUtil.encodeImageTobase64(bitmap);
                    }
                } else if (clicker.equalsIgnoreCase("dokumenD")) {
                    dokumenD = bitmap;
                    ReqDocumentUmum doc = new ReqDocumentUmum();
                    doc.setFilename("dokumenD.png");
                    doc.setImg(AppUtil.encodeImageTobase64(bitmap));
                    doc.setKeteranganDokumen(binding.etKeteranganDokumen4.getText().toString());
                    doc.setNamaDokumen(binding.etNamaDokumen4.getText().toString());
                    if (DokumenUmum.size() < 3) {
                        AppUtil.notiferror(this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
                    } else {
                        if (DokumenUmum.get(3) == null) {
                            DokumenUmum.add(3, doc);
                        }else {
                            DokumenUmum.get(3).setFilename("dokumenD.png");
                            DokumenUmum.get(3).setImg(valDokumenD);
                            DokumenUmum.get(3).setKeteranganDokumen(binding.etKeteranganDokumen4.getText().toString());
                            DokumenUmum.get(3).setNamaDokumen(binding.etNamaDokumen4.getText().toString());
                        }
                        iv.setImageBitmap(bitmap);
                        valDokumenD = AppUtil.encodeImageTobase64(bitmap);
                    }
                } else if (clicker.equalsIgnoreCase("dokumenE")) {
                    dokumenE = bitmap;
                    ReqDocumentUmum doc = new ReqDocumentUmum();
                    doc.setFilename("dokumenE.png");
                    doc.setImg(AppUtil.encodeImageTobase64(bitmap));
                    doc.setKeteranganDokumen(binding.etKeteranganDokumen5.getText().toString());
                    doc.setNamaDokumen(binding.etNamaDokumen5.getText().toString());
                    if (DokumenUmum.size() < 4) {
                        AppUtil.notiferror(this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
                    } else {
                        if (DokumenUmum.get(4) == null) {
                            DokumenUmum.add(4, doc);
                        }else {
                            DokumenUmum.get(4).setFilename("dokumenE.png");
                            DokumenUmum.get(4).setImg(valDokumenE);
                            DokumenUmum.get(4).setKeteranganDokumen(binding.etKeteranganDokumen5.getText().toString());
                            DokumenUmum.get(4).setNamaDokumen(binding.etNamaDokumen5.getText().toString());
                        }
                        iv.setImageBitmap(bitmap);
                        valDokumenE = AppUtil.encodeImageTobase64(bitmap);
                    }
                }
                // Jaminan Dokumen

                if (clicker.equalsIgnoreCase("skpensiun")) {
                    FNskpensiun = "skpensiun.png";
                    Foto_SK_Pensiun.setFileName(FNskpensiun);
                    Foto_SK_Pensiun.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvFotoSkPensiun.setVisibility(View.VISIBLE);
                    valskpensiun = AppUtil.encodeImageTobase64(bitmap);
                    skpensiun = bitmap;
                } else if (clicker.equalsIgnoreCase("skpenangakatan")) {
                    FNskpenangakatan = "skpenangakatan.png";
                    Foto_SK_Pengangkatan.setFileName(FNskpenangakatan);
                    Foto_SK_Pengangkatan.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvFotoSkPengangkatan.setVisibility(View.VISIBLE);
                    valskpenangakatan = AppUtil.encodeImageTobase64(bitmap);
                    skpenangakatan = bitmap;
                } else if (clicker.equalsIgnoreCase("skterakhir")) {
                    FNskterakhir = "skterakhir.png";
                    Foto_SK_Terakhir.setFileName(FNskterakhir);
                    Foto_SK_Terakhir.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvFotoSkTerakhir.setVisibility(View.VISIBLE);
                    valskterakhir = AppUtil.encodeImageTobase64(bitmap);
                    skterakhir = bitmap;
                }
                // AsuransiKhusus

                if (clicker.equalsIgnoreCase("covernoteasuransi")) {
                    FNcovernoteasuransi = "covernoteasuransi.png";
                    Foto_Covernote_Asuransi.setFileName(FNcovernoteasuransi);
                    Foto_Covernote_Asuransi.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvFotoCovernoteAsuransi.setVisibility(View.VISIBLE);
                    valcovernoteasuransi = AppUtil.encodeImageTobase64(bitmap);
                    covernoteasuransi = bitmap;
                }
                // Dokumen Produk

                if (clicker.equalsIgnoreCase("sk")) {
                    FNsk = "sk.png";
                    Tanda_Terima_Dokumen_SK.setFileName(FNsk);
                    Tanda_Terima_Dokumen_SK.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvSk.setVisibility(View.VISIBLE);
                    valsk = AppUtil.encodeImageTobase64(bitmap);
                    sk = bitmap;
                } else if (clicker.equalsIgnoreCase("mutasi")) {
                    FNmutasi = "mutasi.png";
                    Form_Mutasi_Kantor_Bayar_Taspen.setFileName(FNmutasi);
                    Form_Mutasi_Kantor_Bayar_Taspen.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvMutasi.setVisibility(View.VISIBLE);
                    valmutasi = AppUtil.encodeImageTobase64(bitmap);
                    mutasi = bitmap;
                } else if (clicker.equalsIgnoreCase("kuasa")) {
                    FNkuasa = "kuasa.png";
                    Surat_Kuasa_Pernyataan_Flagging.setFileName(FNkuasa);
                    Surat_Kuasa_Pernyataan_Flagging.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvKuasa.setVisibility(View.VISIBLE);
                    valkuasa = AppUtil.encodeImageTobase64(bitmap);
                    kuasa = bitmap;
                } else if (clicker.equalsIgnoreCase("pernyataan")) {
                    FNpernyataan = "pernyataan.png";
                    Surat_Pernyataan_Kuasa_Pembiayaan.setFileName(FNpernyataan);
                    Surat_Pernyataan_Kuasa_Pembiayaan.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvPernyataan.setVisibility(View.VISIBLE);
                    valpernyataan = AppUtil.encodeImageTobase64(bitmap);
                    pernyataan = bitmap;
                } else if (clicker.equalsIgnoreCase("sup")) {
                    FNsup = "sup.png";
                    SUP.setFileName(FNsup);
                    SUP.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvSup.setVisibility(View.VISIBLE);
                    valsup = AppUtil.encodeImageTobase64(bitmap);
                    sup = bitmap;
                }
                // Tanda Tangan Akad

                if (clicker.equalsIgnoreCase("ttdakad")) {
                    FNttdakad = "ttdakad.png";
                    Foto_Proses_Penandatanganan_Akad.setFileName(FNttdakad);
                    Foto_Proses_Penandatanganan_Akad.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvFotoProsesPenandatangananAkad.setVisibility(View.VISIBLE);
                    valttdakad = AppUtil.encodeImageTobase64(bitmap);
                    ttdakad = bitmap;
                }
                // Dokumen Murabahah

                if (clicker.equalsIgnoreCase("po")) {
                    FNpo = "po.png";
                    Murabahah_Purchase_Order.setFileName(FNpo);
                    Murabahah_Purchase_Order.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvPo.setVisibility(View.VISIBLE);
                    valpo = AppUtil.encodeImageTobase64(bitmap);
                    po = bitmap;
                } else if (clicker.equalsIgnoreCase("akadwakalah")) {
                    FNwakalah = "akadwakalah.png";
                    Murabahah_Akad_Wakalah.setFileName(FNwakalah);
                    Murabahah_Akad_Wakalah.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvAkadWakalah.setVisibility(View.VISIBLE);
                    valwakalah = AppUtil.encodeImageTobase64(bitmap);
                    wakalah = bitmap;
                } else if (clicker.equalsIgnoreCase("murabahah")) {
                    FNmurabahah = "murabahah.png";
                    Murabahah_Akad_Murabahah.setFileName(FNmurabahah);
                    Murabahah_Akad_Murabahah.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvAkadMurabahah.setVisibility(View.VISIBLE);
                    valmurabahah = AppUtil.encodeImageTobase64(bitmap);
                    murabahah = bitmap;
                } else if (clicker.equalsIgnoreCase("jadwalangsuran")) {
                    FNjadwalangsuran = "jadwalangsuran.png";
                    Murabahah_Lampiran_Jadwal_Angsuran.setFileName(FNjadwalangsuran);
                    Murabahah_Lampiran_Jadwal_Angsuran.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvJadwalAngsuran.setVisibility(View.VISIBLE);
                    valjadwalangsuran = AppUtil.encodeImageTobase64(bitmap);
                    jadwalangsuran = bitmap;
                } else if (clicker.equalsIgnoreCase("terimabarang")) {
                    FNterimabarang = "terimabarang.png";
                    Murabahah_Surat_Tanda_Terima_Barang.setFileName(FNterimabarang);
                    Murabahah_Surat_Tanda_Terima_Barang.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvTerimaBarang.setVisibility(View.VISIBLE);
                    valterimabarang = AppUtil.encodeImageTobase64(bitmap);
                    terimabarang = bitmap;
                }

                // Dokumen Ijarah

                if (clicker.equalsIgnoreCase("poijarah")) {
                    FNpoijarah = "poijarah.png";
                    Ijarah_Purchase_Order.setFileName(FNpoijarah);
                    Ijarah_Purchase_Order.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvPoIjarah.setVisibility(View.VISIBLE);
                    valpoijarah = AppUtil.encodeImageTobase64(bitmap);
                    poijarah = bitmap;
                } else if (clicker.equalsIgnoreCase("wakalahijarah")) {
                    FNwakalahijarah = "wakalahijarah.png";
                    Ijarah_Akad_Wakalah.setFileName(FNwakalahijarah);
                    Ijarah_Akad_Wakalah.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvAkadWakalahIjarah.setVisibility(View.VISIBLE);
                    valwakalahijarah = AppUtil.encodeImageTobase64(bitmap);
                    wakalahijarah = bitmap;
                } else if (clicker.equalsIgnoreCase("ijarah")) {
                    FNijarah = "ijarah.png";
                    Ijarah_Akad_Ijarah.setFileName(FNijarah);
                    Ijarah_Akad_Ijarah.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvAkadIjarah.setVisibility(View.VISIBLE);
                    valijarah = AppUtil.encodeImageTobase64(bitmap);
                    ijarah = bitmap;
                } else if (clicker.equalsIgnoreCase("angsuranujrah")) {
                    FNangsuranujrah = "angsuranujrah.png";
                    Ijarah_Lampiran_Jadwal_Angsuran.setFileName(FNangsuranujrah);
                    Ijarah_Lampiran_Jadwal_Angsuran.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvAngsuranUjrah.setVisibility(View.VISIBLE);
                    valangsuranujrah = AppUtil.encodeImageTobase64(bitmap);
                    angsuranujrah = bitmap;
                }
                // Dokumen MMQ

                if (clicker.equalsIgnoreCase("assetmmq")) {
                    FNassetmmq = "assetmmq.png";
                    MMQ_Laporan_Penilaian_Aset.setFileName(FNassetmmq);
                    MMQ_Laporan_Penilaian_Aset.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvAssetMmq.setVisibility(View.VISIBLE);
                    valassetmmq = AppUtil.encodeImageTobase64(bitmap);
                    assetmmq = bitmap;
                } else if (clicker.equalsIgnoreCase("akadmmq")) {
                    FNakadmmq = "akadmmq.png";
                    MMQ_Akad_MMQ.setFileName(FNakadmmq);
                    MMQ_Akad_MMQ.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvAkadMmq.setVisibility(View.VISIBLE);
                    valakadmmq = AppUtil.encodeImageTobase64(bitmap);
                    akadmmq = bitmap;
                } else if (clicker.equalsIgnoreCase("jadwalpengambilan")) {
                    FNjadwalpengambilan = "jadwalpengambilan.png";
                    MMQ_Lampiran_Jadwal_Pengambil_Alihan.setFileName(FNjadwalpengambilan);
                    MMQ_Lampiran_Jadwal_Pengambil_Alihan.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvJadwalPengambilalihan.setVisibility(View.VISIBLE);
                    valjadwalpengambilan = AppUtil.encodeImageTobase64(bitmap);
                    jadwalpengambilan = bitmap;
                } else if (clicker.equalsIgnoreCase("pihak1")) {
                    FNpihak1 = "pihak1.png";
                    MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.setFileName(FNpihak1);
                    MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvSuratPernyataanDanKuasaAsetMmqSendiri.setVisibility(View.VISIBLE);
                    valpihak1 = AppUtil.encodeImageTobase64(bitmap);
                    pihak1 = bitmap;
                } else if (clicker.equalsIgnoreCase("pihak3")) {
                    FNpihak3 = "pihak3.png";
                    MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.setFileName(FNpihak3);
                    MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvSuratPernyataanDanKuasaAsetMmqPihakKetiga.setVisibility(View.VISIBLE);
                    valpihak3 = AppUtil.encodeImageTobase64(bitmap);
                    pihak3 = bitmap;
                }
                // Dokumen Rahn

                if (clicker.equalsIgnoreCase("akadrahn")) {
                    FNakadrahn = "akadrahn.png";
                    Rahn_Akad_Rahn.setFileName(FNakadrahn);
                    Rahn_Akad_Rahn.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvAkadRahn.setVisibility(View.VISIBLE);
                    valakadrahn = AppUtil.encodeImageTobase64(bitmap);
                    akadrahn = bitmap;
                } else if (clicker.equalsIgnoreCase("angsuranrahn")) {
                    FNangsuranrahn = "angsuranrahn.png";
                    Rahn_Lampiran_Jadwal_Angsuran.setFileName(FNangsuranrahn);
                    Rahn_Lampiran_Jadwal_Angsuran.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvJadwalAngsuranRahn.setVisibility(View.VISIBLE);
                    valangsuranrahn = AppUtil.encodeImageTobase64(bitmap);
                    angsuranrahn = bitmap;
                }
                // Dokumen Pembiayaan

                if (clicker.equalsIgnoreCase("kantorbayar")) {
                    FNkantorbayar = "kantorbayar.png";
                    Form_Mutasi_Kantor_Bayar.setFileName(FNkantorbayar);
                    Form_Mutasi_Kantor_Bayar.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvFormMutasiKantorBayar.setVisibility(View.VISIBLE);
                    valkantorbayar = AppUtil.encodeImageTobase64(bitmap);
                    kantorbayar = bitmap;
                } else if (clicker.equalsIgnoreCase("fotoakad")) {
                    FNfotoakad = "fotoakad.png";
                    Foto_Bukti_Otentikasi_Nasabah.setFileName(FNfotoakad);
                    Foto_Bukti_Otentikasi_Nasabah.setImg(AppUtil.encodeImageTobase64(bitmap));
                    binding.pvFotoScreenCaptureBuktiOtentikasiNasabahSaatAkad.setVisibility(View.VISIBLE);
                    valfotoakad = AppUtil.encodeImageTobase64(bitmap);
                    fotoakad = bitmap;
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (data.getData() != null) {

                    if (clicker.equalsIgnoreCase("dokumenA")) {
                        Uri uriPdf = data.getData();

                        valDokumenA = AppUtil.encodeFileToBase64(this, uriPdf);
                        ReqDocumentUmum doc = new ReqDocumentUmum();
                        doc.setFilename("dokumenA.pdf");
                        doc.setImg(valDokumenA);
                        doc.setKeteranganDokumen(binding.etKeteranganDokumen1.getText().toString());
                        doc.setNamaDokumen(binding.etNamaDokumen1.getText().toString());
                        if (DokumenUmum.get(0) == null) {
                            DokumenUmum.add(0, doc);
                        }else {
                            DokumenUmum.get(0).setFilename("dokumenA.pdf");
                            DokumenUmum.get(0).setImg(valDokumenA);
                            DokumenUmum.get(0).setKeteranganDokumen(binding.etKeteranganDokumen1.getText().toString());
                            DokumenUmum.get(0).setNamaDokumen(binding.etNamaDokumen1.getText().toString());
                        }
                        AppUtil.logSecure("dataD",String.valueOf(DokumenUmum.size()));
                        iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));
                    } else if (clicker.equalsIgnoreCase("dokumenB")) {
                        Uri uriPdf = data.getData();
                        valDokumenB = AppUtil.encodeFileToBase64(this, uriPdf);
                        ReqDocumentUmum doc = new ReqDocumentUmum();
                        doc.setFilename("dokumenB.pdf");
                        doc.setImg(valDokumenB);
                        doc.setKeteranganDokumen(binding.etKeteranganDokumen2.getText().toString());
                        doc.setNamaDokumen(binding.etNamaDokumen2.getText().toString());
                        if (DokumenUmum.size() < 1) {
                            AppUtil.notiferror(this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
                        } else {
                            if (DokumenUmum.get(1) == null) {
                                DokumenUmum.add(1, doc);
                            }else {
                                DokumenUmum.get(1).setFilename("dokumenB.pdf");
                                DokumenUmum.get(1).setImg(valDokumenB);
                                DokumenUmum.get(1).setKeteranganDokumen(binding.etKeteranganDokumen2.getText().toString());
                                DokumenUmum.get(1).setNamaDokumen(binding.etNamaDokumen2.getText().toString());
                            }
                            iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));
                        }
                    } else if (clicker.equalsIgnoreCase("dokumenC")) {
                        Uri uriPdf = data.getData();
                        valDokumenC = AppUtil.encodeFileToBase64(this, uriPdf);
                        ReqDocumentUmum doc = new ReqDocumentUmum();
                        doc.setFilename("dokumenC.pdf");
                        doc.setImg(valDokumenC);
                        doc.setKeteranganDokumen(binding.etKeteranganDokumen3.getText().toString());
                        doc.setNamaDokumen(binding.etNamaDokumen3.getText().toString());
                        if (DokumenUmum.size() < 2) {
                            AppUtil.notiferror(this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
                        } else {
                            if (DokumenUmum.get(2) == null) {
                                DokumenUmum.add(2, doc);
                            }else {
                                DokumenUmum.get(2).setFilename("dokumenC.pdf");
                                DokumenUmum.get(2).setImg(valDokumenC);
                                DokumenUmum.get(2).setKeteranganDokumen(binding.etKeteranganDokumen3.getText().toString());
                                DokumenUmum.get(2).setNamaDokumen(binding.etNamaDokumen3.getText().toString());
                            }
                            iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));
                        }
                    } else if (clicker.equalsIgnoreCase("dokumenD")) {
                        Uri uriPdf = data.getData();
                        valDokumenD = AppUtil.encodeFileToBase64(this, uriPdf);
                        ReqDocumentUmum doc = new ReqDocumentUmum();
                        doc.setFilename("dokumenD.pdf");
                        doc.setImg(valDokumenD);
                        doc.setKeteranganDokumen(binding.etKeteranganDokumen4.getText().toString());
                        doc.setNamaDokumen(binding.etNamaDokumen4.getText().toString());
                        if (DokumenUmum.size() < 3) {
                            AppUtil.notiferror(this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
                        } else {
                            if (DokumenUmum.get(3) == null) {
                                DokumenUmum.add(3, doc);
                            }else {
                                DokumenUmum.get(3).setFilename("dokumenD.pdf");
                                DokumenUmum.get(3).setImg(valDokumenD);
                                DokumenUmum.get(3).setKeteranganDokumen(binding.etKeteranganDokumen4.getText().toString());
                                DokumenUmum.get(3).setNamaDokumen(binding.etNamaDokumen4.getText().toString());
                            }
                            iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));
                        }
                    } else if (clicker.equalsIgnoreCase("dokumenE")) {
                        Uri uriPdf = data.getData();
                        valDokumenE = AppUtil.encodeFileToBase64(this, uriPdf);
                        ReqDocumentUmum doc = new ReqDocumentUmum();
                        doc.setFilename("dokumenE.pdf");
                        doc.setImg(valDokumenE);
                        doc.setKeteranganDokumen(binding.etKeteranganDokumen5.getText().toString());
                        doc.setNamaDokumen(binding.etNamaDokumen5.getText().toString());
                        if (DokumenUmum.size() < 4) {
                            AppUtil.notiferror(this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
                        } else {
                            if (DokumenUmum.get(4) == null) {
                                DokumenUmum.add(4, doc);
                            }else {
                                DokumenUmum.get(4).setFilename("dokumenE.pdf");
                                DokumenUmum.get(4).setImg(valDokumenE);
                                DokumenUmum.get(4).setKeteranganDokumen(binding.etKeteranganDokumen5.getText().toString());
                                DokumenUmum.get(4).setNamaDokumen(binding.etNamaDokumen5.getText().toString());
                            }
                            iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));
                        }
                    }
                    // Dokumen Jaminan

                    if (clicker.equalsIgnoreCase("skpensiun")) {
                        Uri uriPdf = data.getData();
                        valskpensiun = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNskpensiun = "skpensiun.pdf";
                        Foto_SK_Pensiun.setFileName(FNskpensiun);
                        Foto_SK_Pensiun.setImg(valskpensiun);
                        binding.pvFotoSkPensiun.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("skpenangakatan")) {
                        Uri uriPdf = data.getData();
                        valskpenangakatan = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNskpenangakatan = "skpenangakatan.pdf";
                        Foto_SK_Pengangkatan.setFileName(FNskpenangakatan);
                        Foto_SK_Pengangkatan.setImg(valskpenangakatan);
                        binding.pvFotoSkPengangkatan.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("skterakhir")) {
                        Uri uriPdf = data.getData();
                        valskterakhir = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNskterakhir = "skterakhir.pdf";
                        Foto_SK_Terakhir.setFileName(FNskterakhir);
                        Foto_SK_Terakhir.setImg(valskterakhir);
                        binding.pvFotoSkTerakhir.setVisibility(View.VISIBLE);
                    }
                    // Asuransi Khusus

                    if (clicker.equalsIgnoreCase("covernoteasuransi")) {
                        Uri uriPdf = data.getData();
                        valcovernoteasuransi = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNcovernoteasuransi = "covernoteasuransi.pdf";
                        Foto_Covernote_Asuransi.setFileName(FNcovernoteasuransi);
                        Foto_Covernote_Asuransi.setImg(valcovernoteasuransi);
                        binding.pvFotoCovernoteAsuransi.setVisibility(View.VISIBLE);
                    }
                    // Dokumen Produk

                    if (clicker.equalsIgnoreCase("sk")) {
                        Uri uriPdf = data.getData();
                        valsk = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNsk = "sk.pdf";
                        Tanda_Terima_Dokumen_SK.setFileName(FNsk);
                        Tanda_Terima_Dokumen_SK.setImg(valsk);
                        binding.pvTerimaBarang.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("mutasi")) {
                        Uri uriPdf = data.getData();
                        valmutasi = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNmutasi = "mutasi.pdf";
                        Form_Mutasi_Kantor_Bayar_Taspen.setFileName(FNmutasi);
                        Form_Mutasi_Kantor_Bayar_Taspen.setImg(valmutasi);
                        binding.pvMutasi.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("kuasa")) {
                        Uri uriPdf = data.getData();
                        valkuasa = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNkuasa = "kuasa.pdf";
                        Surat_Kuasa_Pernyataan_Flagging.setFileName(FNkuasa);
                        Surat_Kuasa_Pernyataan_Flagging.setImg(valkuasa);
                        binding.pvKuasa.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("pernyataan")) {
                        Uri uriPdf = data.getData();
                        valpernyataan = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNpernyataan = "pernyataan.pdf";
                        Surat_Pernyataan_Kuasa_Pembiayaan.setFileName(FNpernyataan);
                        Surat_Pernyataan_Kuasa_Pembiayaan.setImg(valpernyataan);
                        binding.pvPernyataan.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("sup")) {
                        Uri uriPdf = data.getData();
                        valsup = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNsup = "sup.pdf";
                        SUP.setFileName(FNsup);
                        SUP.setImg(valsup);
                        binding.pvSup.setVisibility(View.VISIBLE);
                    }
                    // Tanda Tangan Akad

                    if (clicker.equalsIgnoreCase("ttdakad")) {
                        Uri uriPdf = data.getData();
                        valttdakad = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNttdakad = "ttdakad.pdf";
                        Foto_Proses_Penandatanganan_Akad.setFileName(FNttdakad);
                        Foto_Proses_Penandatanganan_Akad.setImg(valttdakad);
                        binding.pvFotoProsesPenandatangananAkad.setVisibility(View.VISIBLE);
                    }
                    // Dokumen Murabahah

                    if (clicker.equalsIgnoreCase("po")) {
                        Uri uriPdf = data.getData();
                        valpo = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNpo = "po.pdf";
                        Murabahah_Purchase_Order.setFileName(FNpo);
                        Murabahah_Purchase_Order.setImg(valpo);
                        binding.pvPo.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("akadwakalah")) {
                        Uri uriPdf = data.getData();
                        valwakalah = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNwakalah = "akadwakalah.pdf";
                        Murabahah_Akad_Wakalah.setFileName(FNwakalah);
                        Murabahah_Akad_Wakalah.setImg(valwakalah);
                        binding.pvAkadWakalah.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("murabahah")) {
                        Uri uriPdf = data.getData();
                        valmurabahah = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNmurabahah = "murabahah.pdf";
                        Murabahah_Akad_Murabahah.setFileName(FNmurabahah);
                        Murabahah_Akad_Murabahah.setImg(valmurabahah);
                        binding.pvAkadMurabahah.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("jadwalangsuran")) {
                        Uri uriPdf = data.getData();
                        valjadwalangsuran = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNjadwalangsuran = "jadwalangsuran.pdf";
                        Murabahah_Lampiran_Jadwal_Angsuran.setFileName(FNjadwalangsuran);
                        Murabahah_Lampiran_Jadwal_Angsuran.setImg(valjadwalangsuran);
                        binding.pvJadwalAngsuran.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("terimabarang")) {
                        Uri uriPdf = data.getData();
                        valterimabarang = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNterimabarang = "terimabarang.pdf";
                        Murabahah_Surat_Tanda_Terima_Barang.setFileName(FNterimabarang);
                        Murabahah_Surat_Tanda_Terima_Barang.setImg(valterimabarang);
                        binding.pvTerimaBarang.setVisibility(View.VISIBLE);
                    }
                    // Dokumen Ijarah

                    if (clicker.equalsIgnoreCase("poijarah")) {
                        Uri uriPdf = data.getData();
                        valpoijarah = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNpoijarah = "poijarah.pdf";
                        Ijarah_Purchase_Order.setFileName(FNpoijarah);
                        Ijarah_Purchase_Order.setImg(valpoijarah);
                        binding.pvPoIjarah.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("wakalahijarah")) {
                        Uri uriPdf = data.getData();
                        valwakalahijarah = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNwakalahijarah = "wakalahijarah.pdf";
                        Ijarah_Akad_Wakalah.setFileName(FNwakalahijarah);
                        Ijarah_Akad_Wakalah.setImg(valwakalahijarah);
                        binding.pvAkadWakalahIjarah.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("ijarah")) {
                        Uri uriPdf = data.getData();
                        valijarah = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNijarah = "ijarah.pdf";
                        Ijarah_Akad_Ijarah.setFileName(FNijarah);
                        Ijarah_Akad_Ijarah.setImg(valijarah);
                        binding.pvAkadIjarah.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("angsuranujrah")) {
                        Uri uriPdf = data.getData();
                        valangsuranujrah = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNangsuranujrah = "angsuranujrah.pdf";
                        Ijarah_Lampiran_Jadwal_Angsuran.setFileName(FNangsuranujrah);
                        Ijarah_Lampiran_Jadwal_Angsuran.setImg(valangsuranujrah);
                        binding.pvAngsuranUjrah.setVisibility(View.VISIBLE);
                    }
                    // Dokumen MMQ

                    if (clicker.equalsIgnoreCase("assetmmq")) {
                        Uri uriPdf = data.getData();
                        valassetmmq = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNassetmmq = "assetmmq.pdf";
                        MMQ_Laporan_Penilaian_Aset.setFileName(FNassetmmq);
                        MMQ_Laporan_Penilaian_Aset.setImg(valassetmmq);
                        binding.pvAssetMmq.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("akadmmq")) {
                        Uri uriPdf = data.getData();
                        valakadmmq = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNakadmmq = "akadmmq.pdf";
                        MMQ_Akad_MMQ.setFileName(FNakadmmq);
                        MMQ_Akad_MMQ.setImg(valakadmmq);
                        binding.pvAkadMmq.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("jadwalpengambilan")) {
                        Uri uriPdf = data.getData();
                        valjadwalpengambilan = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNjadwalpengambilan = "jadwalpengambilan.pdf";
                        MMQ_Lampiran_Jadwal_Pengambil_Alihan.setFileName(FNjadwalpengambilan);
                        MMQ_Lampiran_Jadwal_Pengambil_Alihan.setImg(valjadwalpengambilan);
                        binding.pvJadwalPengambilalihan.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("pihak1")) {
                        Uri uriPdf = data.getData();
                        valpihak1 = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNpihak1 = "pihak1.pdf";
                        MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.setFileName(FNpihak1);
                        MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri.setImg(valpihak1);
                        binding.pvSuratPernyataanDanKuasaAsetMmqSendiri.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("pihak3")) {
                        Uri uriPdf = data.getData();
                        valpihak3 = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNpihak3 = "pihak3.pdf";
                        MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.setFileName(FNpihak3);
                        MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga.setImg(valpihak3);
                        binding.pvSuratPernyataanDanKuasaAsetMmqPihakKetiga.setVisibility(View.VISIBLE);
                    }
                    // Dokumen Rahn

                    if (clicker.equalsIgnoreCase("akadrahn")) {
                        Uri uriPdf = data.getData();
                        valakadrahn = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNakadrahn = "akadrahn.pdf";
                        Rahn_Akad_Rahn.setFileName(FNakadrahn);
                        Rahn_Akad_Rahn.setImg(valakadrahn);
                        binding.pvAkadRahn.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("angsuranrahn")) {
                        Uri uriPdf = data.getData();
                        valangsuranrahn = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNangsuranrahn = "angsuranrahn.pdf";
                        Rahn_Lampiran_Jadwal_Angsuran.setFileName(FNangsuranrahn);
                        Rahn_Lampiran_Jadwal_Angsuran.setImg(valangsuranrahn);
                        binding.pvJadwalAngsuranRahn.setVisibility(View.VISIBLE);
                    }
                    // Dokumen Pembiayaan

                    if (clicker.equalsIgnoreCase("kantorbayar")) {
                        Uri uriPdf = data.getData();
                        valkantorbayar = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNkantorbayar = "kantorbayar.pdf";
                        Form_Mutasi_Kantor_Bayar.setFileName(FNkantorbayar);
                        Form_Mutasi_Kantor_Bayar.setImg(valkantorbayar);
                        binding.pvFormMutasiKantorBayar.setVisibility(View.VISIBLE);
                    } else if (clicker.equalsIgnoreCase("fotoakad")) {
                        Uri uriPdf = data.getData();
                        valfotoakad = AppUtil.encodeFileToBase64(this, uriPdf);
                        FNfotoakad = "fotoakad.pdf";
                        Foto_Bukti_Otentikasi_Nasabah.setFileName(FNfotoakad);
                        Foto_Bukti_Otentikasi_Nasabah.setImg(valfotoakad);
                        binding.pvFotoScreenCaptureBuktiOtentikasiNasabahSaatAkad.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }


    @Override
    public void success(boolean val) {

    }

    @Override
    public void confirm(boolean val) {

    }
}