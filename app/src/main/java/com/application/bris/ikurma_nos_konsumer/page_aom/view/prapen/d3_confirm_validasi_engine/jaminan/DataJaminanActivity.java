package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.jaminan;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseFile;
import com.application.bris.ikurma_nos_konsumer.api.model.request.foto.ReqUploadFile;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.JaminandanDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDocument;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqInquery;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateJaminandanDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.ParseResponseReturn;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.FlagAplikasiPojo;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityDataJaminanBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_ideb.EditIdebActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.simulasi_angsuran.KalkulatorActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.io.File;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataJaminanActivity extends AppCompatActivity implements View.OnClickListener, CameraListener, ConfirmListener {
    private ActivityDataJaminanBinding binding;
    List<JaminandanDokumen> jd;
    JaminandanDokumen doc = new JaminandanDokumen();
    private DatePickerDialog dpSK;
    private Calendar calLahir;
    public static Long idAplikasi;
    private String fileNameKtp = "", tipeFile;
    private String idFileKtp = "";
    private boolean sudahUpload = false;

    private String valDokKtp = "0";
    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    ReqDocument JDJaminanKTP, JDJaminanKTPPasangan, JDJaminanNPWP, JDJaminanFormAplikasi, JDJaminanAsetAKAD, JDJaminanSKPensiun, JDJaminanSKPengangkatan, JDJaminanSKTerakhir, JDJaminanSuratRekomendasiInstansi, JDJaminanIDCard;
    ReqDocument DataJaminanKTP = new ReqDocument(), DataJaminanKTPPasangan = new ReqDocument(),
            DataJaminanNPWP = new ReqDocument(), DataJaminanFormAplikasi = new ReqDocument(),
            DataJaminanAsetAkad = new ReqDocument(), DataJaminanSKPensiun = new ReqDocument(),
            DataJaminanSKPengangkatan = new ReqDocument(), DataJaminanSKTerakhir = new ReqDocument(),
            DataJaminanSuratRekomendasiInstansi = new ReqDocument(), DataJaminanIDCard = new ReqDocument();

    private String val_ktp = "", val_ktppasangan = "", val_npwp = "", val_assetakad = "", val_formaplikasi = "", val_skpensiun = "", val_skpengangkatan = "", val_skterakhir = "", val_datainstansi = "", val_idCard = "";
    private Uri uri_fotoktp, uri_ktppasangan, uri_npwp, uri_assetakad, uri_formaplikasi, uri_skpensiun, uri_skpengangkatan, uri_skterakhir, uri_datainstansi, uri_idcard;
    private Bitmap bitmap_fotoktp, bitmap_ktppasangan, bitmap_npwp, bitmap_assetakad, bitmap_formaplikasi, bitmap_skpensiun, bitmap_skpengangkatan, bitmap_skterakhir, bitmap_datainstansi, bitmap_idcard;
    private final int UPLOAD_DATAKTP = 1, UPLOAD_KTPPASANGAN = 2, UPLOAD_NPWP = 3, UPLOAD_ASSETAKAD = 4, UPLOAD_FORMAPLIKASI = 5, UPLOAD_SKPENSIUN = 6, UPLOAD_SKPENGANGKATAN = 7, UPLOAD_SKTERAKHIR = 8, UPLOAD_DATAINSTASI = 9, UPLOAD_IDCARD = 10;
    String clicker;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataJaminanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);

        if (getIntent().hasExtra("idAplikasi")) {
            idAplikasi = Long.parseLong(getIntent().getStringExtra("idAplikasi"));
        }

        onclickSelectDialog();
        setContentView(view);
        disableText();
        backgroundStatusBar();
        initdata();
        AppUtil.toolbarRegular(this, "Data Jaminan");

    }

    private void initdata() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqInquery req = new ReqInquery();
        req.setApplicationId(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
        Call<ParseResponseAgunan> call = apiClientAdapter.getApiInterface().InqueryJaminandanDokumen(req);
        call.enqueue(new Callback<ParseResponseAgunan>() {
            @Override
            public void onResponse(Call<ParseResponseAgunan> call, Response<ParseResponseAgunan> response) {
                if (response.isSuccessful()) {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    Gson gson = new Gson();

                    if (response.body().getStatus().equalsIgnoreCase("00")) {


                        String listDataString = response.body().getData().get("DataJaminan").toString();
                        String SSJaminanKtp = response.body().getData().get("DataJaminanKTP").getAsJsonArray().get(0).toString();
                        String SSJaminanNPWP = response.body().getData().get("DataJaminanNPWP").getAsJsonArray().get(0).toString();
                        String SSJaminanFormApp = response.body().getData().get("DataJaminanFormAplikasi").getAsJsonArray().get(0).toString();
                        String SSjaminanAsetAkad = response.body().getData().get("DataJaminanAsetAkad").getAsJsonArray().get(0).toString();
                        String SSJaminanSkPengangkatan = response.body().getData().get("DataJaminanSKPengangkatan").getAsJsonArray().get(0).toString();
                        String SSJaminanSkTerakhir = response.body().getData().get("DataJaminanSKTerakhir").getAsJsonArray().get(0).toString();
                        String SSJaminanSuratInstansi = response.body().getData().get("DataJaminanSuratRekomendasiInstansi").getAsJsonArray().get(0).toString();
                        String SSJaminanIdCard = response.body().getData().get("DataJaminanIDcard").getAsJsonArray().get(0).toString();

                        Type type = new TypeToken<List<JaminandanDokumen>>() {
                        }.getType();
                        jd = gson.fromJson(listDataString, type);
                        JDJaminanKTP = gson.fromJson(SSJaminanKtp, ReqDocument.class);
                        JDJaminanNPWP = gson.fromJson(SSJaminanNPWP, ReqDocument.class);
                        JDJaminanAsetAKAD = gson.fromJson(SSjaminanAsetAkad, ReqDocument.class);
                        JDJaminanFormAplikasi = gson.fromJson(SSJaminanFormApp, ReqDocument.class);
                        JDJaminanSKPengangkatan = gson.fromJson(SSJaminanSkPengangkatan, ReqDocument.class);
                        JDJaminanSKTerakhir = gson.fromJson(SSJaminanSkTerakhir, ReqDocument.class);
                        JDJaminanSuratRekomendasiInstansi = gson.fromJson(SSJaminanSuratInstansi, ReqDocument.class);
                        JDJaminanIDCard = gson.fromJson(SSJaminanIdCard, ReqDocument.class);

                        try {
//                            binding.etNomorSkPensiun.setText(jaminandanDokumen.getNoSKPensiun());
//                            binding.etLembagaTerbitSk.setText(jaminandanDokumen.getLembagaPenerbitSKPensiun());
//                            binding.etNomorSkPengangkatan.setText(jaminandanDokumen.getNoSKPengangkatan());
//                            binding.etNomorSkPangkatTerakhir.setText(jaminandanDokumen.getNoSKTerakhir());
//                            binding.etLembagaTerbitSk2.setText(jaminandanDokumen.getLembagaPenerbitSKPengangk());
//                            binding.etLembagaTerbitSk3.setText(jaminandanDokumen.getLembagaPenerbitSKTerakhir());
                            binding.etNomorSkPensiun.setText(jd.get(0).getNoSKPensiun());
                            binding.etLembagaTerbitSk.setText(jd.get(0).getLembagaPenerbitSKPensiun());
                            binding.etNomorSkPengangkatan.setText(jd.get(0).getNoSKPengangkatan());
                            binding.etNomorSkPangkatTerakhir.setText(jd.get(0).getNoSKTerakhir());
                            binding.etLembagaTerbitSk2.setText(jd.get(0).getLembagaPenerbitSKPengangk());
                            binding.etLembagaTerbitSk3.setText(jd.get(0).getLembagaPenerbitSKTerakhir());
                            binding.etTanggalTerbitSk1.setText(AppUtil.parseTanggalGeneral(jd.get(0).getTanggalTerbitSKPensiun(), "yyyy-MM-dd", "dd-MM-yyyy"));
                            binding.etTanggalTerbitSk2.setText(AppUtil.parseTanggalGeneral(jd.get(0).getTanggalTerbitSKPengangkat(), "yyyy-MM-dd", "dd-MM-yyyy"));
                            binding.etTanggalTerbitSk3.setText(AppUtil.parseTanggalGeneral(jd.get(0).getTanggalTerbitSKTerakhir(), "yyyy-MM-dd", "dd-MM-yyyy"));

                        } catch (Exception e) {
                            AppUtil.logSecure("error setdata", e.getMessage());
                        }

                        //Set Image KTP
                        try {

                            DataJaminanKTP.setImg(JDJaminanKTP.getImg());

                            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
                            if (JDJaminanKTP.getFileName().substring(JDJaminanKTP.getFileName().length() - 3, JDJaminanKTP.getFileName().length()).equalsIgnoreCase("pdf")) {
                                DataJaminanKTP.setFileName("fotoktp.pdf");

                                //legacy
//                                AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanKTP.getImg(), binding.ivKtpNasabah, JDJaminanKTP.getFileName());

                                //logical doc
                                AppUtil.setLoadPdf(DataJaminanActivity.this, JDJaminanKTP.getImg(), binding.ivKtpNasabah);
                            } else {
                                DataJaminanKTP.setFileName("fotoktp.png");
                                //legacy
//                                AppUtil.convertBase64ToImage(JDJaminanKTP.getImg(), binding.ivKtpNasabah);

                                //logical doc
                                AppUtil.setImageGlide(DataJaminanActivity.this, JDJaminanKTP.getImg(), binding.ivKtpNasabah);

                            }

                        } catch (Exception e) {
                            AppUtil.logSecure("error setdata", e.getMessage());
                        }

                        //Set Image KTP Pasangan
                        if (response.body().getData().get("IsMarried") != null) {
                            if (response.body().getData().get("IsMarried").getAsString().equalsIgnoreCase("False")) {
                                binding.rlKtpPasangan.setVisibility(View.GONE);
                                binding.tvKtpPasangan.setVisibility(View.GONE);
                            } else {
                                String SSJaminanKtpPasangan = response.body().getData().get("DataJaminanKTPPasangan").getAsJsonArray().get(0).toString();
                                JDJaminanKTPPasangan = gson.fromJson(SSJaminanKtpPasangan, ReqDocument.class);

                                try {
                                    DataJaminanKTPPasangan.setImg(JDJaminanKTPPasangan.getImg());
                                    //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
                                    if (JDJaminanKTPPasangan.getFileName().substring(JDJaminanKTPPasangan.getFileName().length() - 3, JDJaminanKTPPasangan.getFileName().length()).equalsIgnoreCase("pdf")) {
                                        DataJaminanKTPPasangan.setFileName("ktppasangan.pdf");
                                        AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanKTPPasangan.getImg(), binding.ivKtpPasangan, JDJaminanKTPPasangan.getFileName());
                                    } else {
                                        DataJaminanKTPPasangan.setFileName("ktppasangan.png");
                                        AppUtil.convertBase64ToImage(JDJaminanKTPPasangan.getImg(), binding.ivKtpPasangan);
                                    }

                                } catch (Exception e) {
                                    AppUtil.logSecure("error setdata", e.getMessage());
                                }
                            }
                        }

                        //Set Image NPWP
                        try {

                            DataJaminanNPWP.setImg(JDJaminanNPWP.getImg());

                            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
                            if (JDJaminanNPWP.getFileName().substring(JDJaminanNPWP.getFileName().length() - 3, JDJaminanNPWP.getFileName().length()).equalsIgnoreCase("pdf")) {
                                DataJaminanNPWP.setFileName("npwp.pdf");
                                AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanNPWP.getImg(), binding.ivNpwp, JDJaminanNPWP.getFileName());
                            } else {
                                DataJaminanNPWP.setFileName("npwp.png");
                                AppUtil.convertBase64ToImage(JDJaminanNPWP.getImg(), binding.ivNpwp);
                            }

                        } catch (Exception e) {
                            AppUtil.logSecure("error setdata", e.getMessage());
                        }
                        //Set Image Asset Akad
                        try {

                            DataJaminanAsetAkad.setImg(JDJaminanAsetAKAD.getImg());

                            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
                            if (JDJaminanAsetAKAD.getFileName().substring(JDJaminanAsetAKAD.getFileName().length() - 3, JDJaminanAsetAKAD.getFileName().length()).equalsIgnoreCase("pdf")) {
                                DataJaminanAsetAkad.setFileName("assetakad.pdf");
                                AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanAsetAKAD.getImg(), binding.ivAssetAkad, JDJaminanAsetAKAD.getFileName());
                            } else {
                                DataJaminanAsetAkad.setFileName("assetakad.png");
                                AppUtil.convertBase64ToImage(JDJaminanAsetAKAD.getImg(), binding.ivAssetAkad);
                            }

                        } catch (Exception e) {
                            AppUtil.logSecure("error setdata", e.getMessage());
                        }
                        //Set Image Form Aplikasi
                        try {

                            DataJaminanFormAplikasi.setImg(JDJaminanFormAplikasi.getImg());

                            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
                            if (JDJaminanFormAplikasi.getFileName().substring(JDJaminanFormAplikasi.getFileName().length() - 3, JDJaminanFormAplikasi.getFileName().length()).equalsIgnoreCase("pdf")) {
                                DataJaminanFormAplikasi.setFileName("formaplikasi.pdf");
                                AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanFormAplikasi.getImg(), binding.ivFormApplikasi, JDJaminanFormAplikasi.getFileName());
                            } else {
                                DataJaminanFormAplikasi.setFileName("formaplikasi.png");
                                AppUtil.convertBase64ToImage(JDJaminanFormAplikasi.getImg(), binding.ivFormApplikasi);
                            }

                        } catch (Exception e) {
                            AppUtil.logSecure("error setdata", e.getMessage());
                        }

                        //Set Image Sk Pensiun
                        if (response.body().getData().get("IsPensiun") != null) {
                            if (response.body().getData().get("IsPensiun").getAsString().equalsIgnoreCase("False")) {
                                binding.rlSkPensiun.setVisibility(View.GONE);
                                binding.tvSkPensiun.setVisibility(View.GONE);
                            }else{
                                String SSJaminanSkPensiun = response.body().getData().get("DataJaminanSKPensiun").getAsJsonArray().get(0).toString();
                                JDJaminanSKPensiun = gson.fromJson(SSJaminanSkPensiun, ReqDocument.class);
                                try {
                                    DataJaminanSKPensiun.setImg(JDJaminanSKPensiun.getImg());

                                    //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
                                    if (JDJaminanSKPensiun.getFileName().substring(JDJaminanSKPensiun.getFileName().length() - 3, JDJaminanSKPensiun.getFileName().length()).equalsIgnoreCase("pdf")) {
                                        DataJaminanSKPensiun.setFileName("skpensiun.pdf");
                                        AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanSKPensiun.getImg(), binding.ivSkPensiun, JDJaminanSKPensiun.getFileName());
                                    } else {
                                        DataJaminanSKPensiun.setFileName("skpensiun.png");
                                        AppUtil.convertBase64ToImage(JDJaminanSKPensiun.getImg(), binding.ivSkPensiun);
                                    }

                                } catch (Exception e) {
                                    AppUtil.logSecure("error setdata", e.getMessage());
                                }
                            }
                        }

                        //Set Image Sk Pengangkatan
                        try {

                            DataJaminanSKPengangkatan.setImg(JDJaminanSKPengangkatan.getImg());

                            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
                            if (JDJaminanSKPengangkatan.getFileName().substring(JDJaminanSKPengangkatan.getFileName().length() - 3, JDJaminanSKPengangkatan.getFileName().length()).equalsIgnoreCase("pdf")) {
                                DataJaminanSKPengangkatan.setFileName("skpengangkatan.pdf");
                                AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanSKPengangkatan.getImg(), binding.ivSkPengangkatan, JDJaminanSKPengangkatan.getFileName());
                            } else {
                                DataJaminanSKPengangkatan.setFileName("skpengangkatan.png");
                                AppUtil.convertBase64ToImage(JDJaminanSKPengangkatan.getImg(), binding.ivSkPengangkatan);
                            }

                        } catch (Exception e) {
                            AppUtil.logSecure("error setdata", e.getMessage());
                        }

                        //Set Image Sk Terakhir
                        try {

                            DataJaminanSKTerakhir.setImg(JDJaminanSKTerakhir.getImg());

                            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
                            if (JDJaminanSKTerakhir.getFileName().substring(JDJaminanSKTerakhir.getFileName().length() - 3, JDJaminanSKTerakhir.getFileName().length()).equalsIgnoreCase("pdf")) {
                                DataJaminanSKTerakhir.setFileName("skterakhir.pdf");
                                AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanSKTerakhir.getImg(), binding.ivSkTerakhir, JDJaminanSKTerakhir.getFileName());
                            } else {
                                DataJaminanSKTerakhir.setFileName("skterakhir.png");
                                AppUtil.convertBase64ToImage(JDJaminanSKTerakhir.getImg(), binding.ivSkTerakhir);
                            }

                        } catch (Exception e) {
                            AppUtil.logSecure("error setdata", e.getMessage());
                        }

                        //Set Image Surat Instansi
                        try {

                            DataJaminanSuratRekomendasiInstansi.setImg(JDJaminanSuratRekomendasiInstansi.getImg());

                            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
                            if (JDJaminanSuratRekomendasiInstansi.getFileName().substring(JDJaminanSuratRekomendasiInstansi.getFileName().length() - 3, JDJaminanSuratRekomendasiInstansi.getFileName().length()).equalsIgnoreCase("pdf")) {
                                DataJaminanSuratRekomendasiInstansi.setFileName("suratinstansi.pdf");
                                AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanSuratRekomendasiInstansi.getImg(), binding.ivSuratInstansi, JDJaminanSuratRekomendasiInstansi.getFileName());
                            } else {
                                DataJaminanSuratRekomendasiInstansi.setFileName("suratinstansi.png");
                                AppUtil.convertBase64ToImage(JDJaminanSuratRekomendasiInstansi.getImg(), binding.ivSuratInstansi);
                            }

                        } catch (Exception e) {
                            AppUtil.logSecure("error setdata", e.getMessage());
                        }
                        //Set Image Id Card
                        try {

                            DataJaminanIDCard.setImg(JDJaminanIDCard.getImg());

                            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
                            if (JDJaminanIDCard.getFileName().substring(JDJaminanIDCard.getFileName().length() - 3, JDJaminanIDCard.getFileName().length()).equalsIgnoreCase("pdf")) {
                                DataJaminanIDCard.setFileName("idcard.pdf");
                                AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanIDCard.getImg(), binding.ivIdcard, JDJaminanIDCard.getFileName());
                            } else {
                                DataJaminanIDCard.setFileName("idcard.png");
                                AppUtil.convertBase64ToImage(JDJaminanIDCard.getImg(), binding.ivIdcard);
                            }

                        } catch (Exception e) {
                            AppUtil.logSecure("error setdata", e.getMessage());
                        }

                    } else {
                        AppUtil.notiferror(DataJaminanActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                } else {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    Error error = ParseResponseError.confirmEror(response.errorBody());
                    AppUtil.notiferror(DataJaminanActivity.this, findViewById(android.R.id.content), error.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ParseResponseAgunan> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataJaminanActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void sendDoc() {
        doc.setNoSKPensiun("asd");
        doc.setLembagaPenerbitSKPensiun("asd");
        doc.setTanggalTerbitSKPensiun("asd");
        doc.setNoSKPengangkatan("asd");
        doc.setLembagaPenerbitSKPengangk("asd");
        doc.setTanggalTerbitSKPengangkat("asd");
        doc.setNoSKTerakhir("asd");
        doc.setLembagaPenerbitSKTerakhir("asd");
        doc.setTanggalTerbitSKTerakhir("asd");
    }

    private void sendDataJaminan() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        UpdateJaminandanDokumen req = new UpdateJaminandanDokumen();
        AppPreferences appPreferences = new AppPreferences(this);
        doc.setNoSKPensiun(binding.etNomorSkPensiun.getText().toString());
        doc.setLembagaPenerbitSKPensiun(binding.etLembagaTerbitSk.getText().toString());
        doc.setTanggalTerbitSKPensiun(AppUtil.parseTanggalGeneral(binding.etTanggalTerbitSk1.getText().toString(), "dd-MM-yyyy", "yyyy-MM-dd"));
        doc.setNoSKPengangkatan(binding.etNomorSkPengangkatan.getText().toString());
        doc.setLembagaPenerbitSKPengangk(binding.etLembagaTerbitSk2.getText().toString());
        doc.setTanggalTerbitSKPengangkat(AppUtil.parseTanggalGeneral(binding.etTanggalTerbitSk2.getText().toString(), "dd-MM-yyyy", "yyyy-MM-dd"));
        doc.setNoSKTerakhir(binding.etNomorSkPangkatTerakhir.getText().toString());
        doc.setLembagaPenerbitSKTerakhir(binding.etNomorSkPangkatTerakhir.getText().toString());
        doc.setTanggalTerbitSKTerakhir(AppUtil.parseTanggalGeneral(binding.etTanggalTerbitSk2.getText().toString(), "dd-MM-yyyy", "yyyy-MM-dd"));

        req.setApplicationId(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
        req.setUid(String.valueOf(appPreferences.getUid()));
        req.setJaminandanDokumen(doc);
        req.setLembagaPenerbitSk(binding.etLembagaTerbitSk.getText().toString());
        req.setDataJaminanKTP(DataJaminanKTP);
        req.setDataJaminanKTPPasangan(DataJaminanKTPPasangan);
        req.setDataJaminanNPWP(DataJaminanNPWP);
        req.setDataJaminanAsetAKAD(DataJaminanAsetAkad);
        req.setDataJaminanFormAplikasi(DataJaminanFormAplikasi);
        req.setDataJaminanSKPensiun(DataJaminanSKPensiun);
        req.setDataJaminanSKPengangkatan(DataJaminanSKPengangkatan);
        req.setDataJaminanSKTerakhir(DataJaminanSKTerakhir);
        req.setDataJaminanSuratRekomendasiInstansi(DataJaminanSuratRekomendasiInstansi);
        req.setDataJaminanIDCard(DataJaminanIDCard);

        Call<ParseResponseReturn> call = apiClientAdapter.getApiInterface().UpdateJaminandanDokumen(req);
        call.enqueue(new Callback<ParseResponseReturn>() {
            @Override
            public void onResponse(Call<ParseResponseReturn> call, Response<ParseResponseReturn> response) {
                try {
                    if (response.isSuccessful()) {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            CustomDialog.DialogSuccess(DataJaminanActivity.this, "Success!", "Update Data Jaminan sukses", DataJaminanActivity.this);

                            //update Flagging
                            Realm realm = Realm.getDefaultInstance();
                            FlagAplikasiPojo dataFlag = realm.where(FlagAplikasiPojo.class).equalTo("idAplikasi", idAplikasi).findFirst();
                            if (!realm.isInTransaction()) {
                                realm.beginTransaction();
                            }

                            if (dataFlag != null) {
                                dataFlag.setFlagD3Jaminan(true);
                                realm.insertOrUpdate(dataFlag);
                            } else {
                                dataFlag = new FlagAplikasiPojo();
                                dataFlag.setIdAplikasi(idAplikasi);
                                dataFlag.setFlagD3Jaminan(true);
                                realm.insertOrUpdate(dataFlag);
                            }
                            realm.close();

                        } else {
                            AppUtil.notiferror(DataJaminanActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataJaminanActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseReturn> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataJaminanActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }


    private void disableText() {
        binding.etLembagaTerbitSk.setFocusable(false);
        binding.etTanggalTerbitSk1.setFocusable(false);
        binding.etTanggalTerbitSk2.setFocusable(false);
        binding.etTanggalTerbitSk3.setFocusable(false);
        binding.tfLembagaTerbitSk1.setFocusable(false);
        binding.tfTanggalTerbitSk1.setFocusable(false);
        binding.tfTanggalTerbitSk2.setFocusable(false);
        binding.tfTanggalTerbitSk3.setFocusable(false);

    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void onclickSelectDialog() {
        binding.etTanggalTerbitSk1.setOnClickListener(this);
        binding.tfTanggalTerbitSk1.setOnClickListener(this);
        binding.tfTanggalTerbitSk1.getEndIconImageButton().setOnClickListener(v -> dpSKCalendar(binding.etTanggalTerbitSk1));
        binding.etTanggalTerbitSk2.setOnClickListener(this);
        binding.tfTanggalTerbitSk2.setOnClickListener(this);
        binding.tfTanggalTerbitSk2.getEndIconImageButton().setOnClickListener(v -> dpSKCalendar(binding.etTanggalTerbitSk2));
        binding.etTanggalTerbitSk3.setOnClickListener(this);
        binding.tfTanggalTerbitSk3.setOnClickListener(this);
        binding.tfTanggalTerbitSk3.getEndIconImageButton().setOnClickListener(v -> dpSKCalendar(binding.etTanggalTerbitSk3));
        binding.ivKtpNasabah.setOnClickListener(this);
        binding.ivAssetAkad.setOnClickListener(this);
        binding.ivFormApplikasi.setOnClickListener(this);
        binding.ivIdcard.setOnClickListener(this);
        binding.ivNpwp.setOnClickListener(this);
        binding.ivKtpPasangan.setOnClickListener(this);
        binding.ivSkPengangkatan.setOnClickListener(this);
        binding.ivSkPensiun.setOnClickListener(this);
        binding.ivSkTerakhir.setOnClickListener(this);
        binding.ivSuratInstansi.setOnClickListener(this);
        binding.btnKtpNasabah.setOnClickListener(this);
        binding.btnAssetAkad.setOnClickListener(this);
        binding.btnFormApplikasi.setOnClickListener(this);
        binding.btnIdcard.setOnClickListener(this);
        binding.btnNpwp.setOnClickListener(this);
        binding.btnKtpPasangan.setOnClickListener(this);
        binding.btnSkPengangkatan.setOnClickListener(this);
        binding.btnSkPensiun.setOnClickListener(this);
        binding.btnSkTerakhir.setOnClickListener(this);
        binding.btnSuratInstansi.setOnClickListener(this);
        binding.btnSend.setOnClickListener(this);
        binding.llBtnSend.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_tanggal_terbit_sk1:
            case R.id.tf_tanggal_terbit_sk1:
                dpSKCalendar(binding.etTanggalTerbitSk1);
                break;
            case R.id.et_tanggal_terbit_sk2:
            case R.id.tf_tanggal_terbit_sk2:
                dpSKCalendar(binding.etTanggalTerbitSk2);
                break;
            case R.id.et_tanggal_terbit_sk3:
            case R.id.tf_tanggal_terbit_sk3:
                dpSKCalendar(binding.etTanggalTerbitSk3);
                break;
            case R.id.iv_ktp_nasabah:
            case R.id.btn_ktp_nasabah:
                BSUploadFile.displayWithTitle(DataJaminanActivity.this.getSupportFragmentManager(), this, "");
                clicker = "fotoktp";
                break;
            case R.id.iv_asset_akad:
            case R.id.btn_asset_akad:
                BSUploadFile.displayWithTitle(DataJaminanActivity.this.getSupportFragmentManager(), this, "");
                clicker = "assetakad";
                break;
            case R.id.iv_form_applikasi:
            case R.id.btn_form_applikasi:
                BSUploadFile.displayWithTitle(DataJaminanActivity.this.getSupportFragmentManager(), this, "");
                clicker = "formaplikasi";
                break;
            case R.id.iv_idcard:
            case R.id.btn_idcard:
                BSUploadFile.displayWithTitle(DataJaminanActivity.this.getSupportFragmentManager(), this, "");
                clicker = "idcard";
                break;
            case R.id.iv_npwp:
            case R.id.btn_npwp:
                BSUploadFile.displayWithTitle(DataJaminanActivity.this.getSupportFragmentManager(), this, "");
                clicker = "npwp";
                break;
            case R.id.iv_ktp_pasangan:
            case R.id.btn_ktp_pasangan:
                BSUploadFile.displayWithTitle(DataJaminanActivity.this.getSupportFragmentManager(), this, "");
                clicker = "ktppasangan";
                break;
            case R.id.iv_sk_pengangkatan:
            case R.id.btn_sk_pengangkatan:
                BSUploadFile.displayWithTitle(DataJaminanActivity.this.getSupportFragmentManager(), this, "");
                clicker = "skpengangkatan";
                break;
            case R.id.iv_sk_pensiun:
            case R.id.btn_sk_pensiun:
                BSUploadFile.displayWithTitle(DataJaminanActivity.this.getSupportFragmentManager(), this, "");
                clicker = "skpensiun";
                break;
            case R.id.iv_sk_terakhir:
            case R.id.btn_sk_terakhir:
                BSUploadFile.displayWithTitle(DataJaminanActivity.this.getSupportFragmentManager(), this, "");
                clicker = "skterakhir";
                break;
            case R.id.iv_surat_instansi:
            case R.id.btn_surat_instansi:
                BSUploadFile.displayWithTitle(DataJaminanActivity.this.getSupportFragmentManager(), this, "");
                clicker = "datainstansi";
                break;
            case R.id.ll_btn_send:
            case R.id.btn_send:
                validasi();
                break;
        }
    }

    private boolean validasi() {
        if (binding.etNomorSkPengangkatan.getText().toString().trim().isEmpty() || binding.etNomorSkPengangkatan.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfNomorSkPengangkatan.setError(binding.tfNomorSkPengangkatan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(DataJaminanActivity.this, findViewById(android.R.id.content), binding.tfNomorSkPengangkatan.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etNomorSkPangkatTerakhir.getText().toString().trim().isEmpty() || binding.etNomorSkPangkatTerakhir.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfNomorSkPangkatTerakhir.setError(binding.tfNomorSkPangkatTerakhir.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(DataJaminanActivity.this, findViewById(android.R.id.content), binding.tfNomorSkPangkatTerakhir.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etTanggalTerbitSk2.getText().toString().trim().isEmpty() || binding.etTanggalTerbitSk2.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfTanggalTerbitSk2.setError(binding.tfTanggalTerbitSk2.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(DataJaminanActivity.this, findViewById(android.R.id.content), binding.tfTanggalTerbitSk2.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etTanggalTerbitSk3.getText().toString().trim().isEmpty() || binding.etTanggalTerbitSk3.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfTanggalTerbitSk3.setError(binding.tfTanggalTerbitSk3.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(DataJaminanActivity.this, findViewById(android.R.id.content), binding.tfTanggalTerbitSk3.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;
        } else {
//            AppUtil.disableEditTexts(binding.getRoot());
//            binding.ivKtpNasabah.setOnClickListener(null);
//            binding.ivAssetAkad.setOnClickListener(null);
//            binding.ivFormApplikasi.setOnClickListener(null);
//            binding.ivIdcard.setOnClickListener(null);
//            binding.ivNpwp.setOnClickListener(null);
//            binding.ivKtpPasangan.setOnClickListener(null);
//            binding.ivSkPengangkatan.setOnClickListener(null);
//            binding.ivSkPensiun.setOnClickListener(null);
//            binding.ivSkTerakhir.setOnClickListener(null);
//            binding.ivSuratInstansi.setOnClickListener(null);
//            binding.btnKtpNasabah.setOnClickListener(null);
//            binding.btnAssetAkad.setOnClickListener(null);
//            binding.btnFormApplikasi.setOnClickListener(null);
//            binding.btnIdcard.setOnClickListener(null);
//            binding.btnNpwp.setOnClickListener(null);
//            binding.btnKtpPasangan.setOnClickListener(null);
//            binding.btnSkPengangkatan.setOnClickListener(null);
//            binding.btnSkPensiun.setOnClickListener(null);
//            binding.btnSkTerakhir.setOnClickListener(null);
//            binding.btnSuratInstansi.setOnClickListener(null);
//            binding.rlKtpNasabah.setOnClickListener(null);
//            binding.rlAssetAkad.setOnClickListener(null);
//            binding.rlFormApplikasi.setOnClickListener(null);
//            binding.rlIdcard.setOnClickListener(null);
//            binding.rlNpwp.setOnClickListener(null);
//            binding.rlKtpPasangan.setOnClickListener(null);
//            binding.rlSkPengangkatan.setOnClickListener(null);
//            binding.rlSkPensiun.setOnClickListener(null);
//            binding.rlSkTerakhir.setOnClickListener(null);
//            binding.rlSuratInstansi.setOnClickListener(null);
//            binding.btnKtpNasabah.setVisibility(View.GONE);
//            binding.btnAssetAkad.setVisibility(View.GONE);
//            binding.btnFormApplikasi.setVisibility(View.GONE);
//            binding.btnIdcard.setVisibility(View.GONE);
//            binding.btnNpwp.setVisibility(View.GONE);
//            binding.btnKtpPasangan.setVisibility(View.GONE);
//            binding.btnSkPengangkatan.setVisibility(View.GONE);
//            binding.btnSkPensiun.setVisibility(View.GONE);
//            binding.btnSkTerakhir.setVisibility(View.GONE);
//            binding.btnSuratInstansi.setVisibility(View.GONE);
//            AppUtil.notiferror(DataJaminanActivity.this, findViewById(android.R.id.content), "Field Telah Terisi Penuh");
            sendDataJaminan();
            return false;
        }
    }


    private void dpSKCalendar(EditText edit) {
        calLahir = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ls_tanggalLahirPasangan = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calLahir.set(Calendar.YEAR, year);
                calLahir.set(Calendar.MONTH, month);
                calLahir.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calLahirString = dateClient.format(calLahir.getTime());
                edit.setText(calLahirString);
            }
        };

        dpSK = new DatePickerDialog(DataJaminanActivity.this, R.style.AppTheme_TimePickerTheme, ls_tanggalLahirPasangan, calLahir.get(Calendar.YEAR),
                calLahir.get(Calendar.MONTH), calLahir.get(Calendar.DAY_OF_MONTH));
        dpSK.getDatePicker().setMaxDate(calLahir.getTimeInMillis());
        dpSK.show();
    }


    //UPLOAD FILE METHODS

    @Override
    public void onSelectMenuCamera(String idMenu) {
        switch (idMenu) {
            case "Take Photo":
                tipeFile = "png";
                if (clicker.equalsIgnoreCase("fotoktp")) {
                    openCamera(UPLOAD_DATAKTP, "fotoktp");
                } else if (clicker.equalsIgnoreCase("ktppasangan")) {
                    openCamera(UPLOAD_KTPPASANGAN, "ktppasangan");
                } else if (clicker.equalsIgnoreCase("npwp")) {
                    openCamera(UPLOAD_NPWP, "npwp");
                } else if (clicker.equalsIgnoreCase("assetakad")) {
                    openCamera(UPLOAD_ASSETAKAD, "assetakad");
                } else if (clicker.equalsIgnoreCase("formaplikasi")) {
                    openCamera(UPLOAD_FORMAPLIKASI, "formaplikasi");
                } else if (clicker.equalsIgnoreCase("skpensiun")) {
                    openCamera(UPLOAD_SKPENSIUN, "skpensiun");
                } else if (clicker.equalsIgnoreCase("skpengangkatan")) {
                    openCamera(UPLOAD_SKPENGANGKATAN, "skpengangkatan");
                } else if (clicker.equalsIgnoreCase("skterakhir")) {
                    openCamera(UPLOAD_SKTERAKHIR, "skterakhir");
                } else if (clicker.equalsIgnoreCase("datainstansi")) {
                    openCamera(UPLOAD_DATAINSTASI, "datainstansi");
                } else if (clicker.equalsIgnoreCase("idcard")) {
                    openCamera(UPLOAD_IDCARD, "idcard");
                }
                break;

            case "Pick Photo":
                tipeFile = "png";
                if (clicker.equalsIgnoreCase("fotoktp")) {
                    openGalery(UPLOAD_DATAKTP);
                } else if (clicker.equalsIgnoreCase("ktppasangan")) {
                    openGalery(UPLOAD_KTPPASANGAN);
                } else if (clicker.equalsIgnoreCase("npwp")) {
                    openGalery(UPLOAD_NPWP);
                } else if (clicker.equalsIgnoreCase("assetakad")) {
                    openGalery(UPLOAD_ASSETAKAD);
                } else if (clicker.equalsIgnoreCase("formaplikasi")) {
                    openGalery(UPLOAD_FORMAPLIKASI);
                } else if (clicker.equalsIgnoreCase("skpensiun")) {
                    openGalery(UPLOAD_SKPENSIUN);
                } else if (clicker.equalsIgnoreCase("skpengangkatan")) {
                    openGalery(UPLOAD_SKPENGANGKATAN);
                } else if (clicker.equalsIgnoreCase("skterakhir")) {
                    openGalery(UPLOAD_SKTERAKHIR);
                } else if (clicker.equalsIgnoreCase("datainstansi")) {
                    openGalery(UPLOAD_DATAINSTASI);
                } else if (clicker.equalsIgnoreCase("idcard")) {
                    openGalery(UPLOAD_IDCARD);
                }
                break;
            case "Pick File":
                tipeFile = "pdf";
                if (clicker.equalsIgnoreCase("fotoktp")) {
                    openFile(UPLOAD_DATAKTP);
                } else if (clicker.equalsIgnoreCase("ktppasangan")) {
                    openFile(UPLOAD_KTPPASANGAN);
                } else if (clicker.equalsIgnoreCase("npwp")) {
                    openFile(UPLOAD_NPWP);
                } else if (clicker.equalsIgnoreCase("assetakad")) {
                    openFile(UPLOAD_ASSETAKAD);
                } else if (clicker.equalsIgnoreCase("formaplikasi")) {
                    openFile(UPLOAD_FORMAPLIKASI);
                } else if (clicker.equalsIgnoreCase("skpensiun")) {
                    openFile(UPLOAD_SKPENSIUN);
                } else if (clicker.equalsIgnoreCase("skpengangkatan")) {
                    openFile(UPLOAD_SKPENGANGKATAN);
                } else if (clicker.equalsIgnoreCase("skterakhir")) {
                    openFile(UPLOAD_SKTERAKHIR);
                } else if (clicker.equalsIgnoreCase("datainstansi")) {
                    openFile(UPLOAD_DATAINSTASI);
                } else if (clicker.equalsIgnoreCase("idcard")) {
                    openFile(UPLOAD_IDCARD);
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


    //legacy upload
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case UPLOAD_DATAKTP:
                //legacy
//                setDataImage(uri_fotoktp, bitmap_fotoktp, binding.ivKtpNasabah, imageReturnedIntent, "fotoktp");

                //logicaldoc
                setDataImage(uri_fotoktp, bitmap_fotoktp, binding.ivKtpNasabah, imageReturnedIntent, "fotoktp");
                if (tipeFile.equalsIgnoreCase("pdf")) {
                    fileNameKtp = idAplikasi + "_ktpD3.pdf";
                    uploadFile(val_ktp, fileNameKtp, UPLOAD_DATAKTP);
                } else {
                    binding.ivKtpNasabah.invalidate();
                    RoundedDrawable drawableIdeb = (RoundedDrawable) binding.ivKtpNasabah.getDrawable();
                    Bitmap bitmapIdeb = drawableIdeb.getSourceBitmap();
                    fileNameKtp = idAplikasi + "_ktpD3.png";
                    uploadFile(AppUtil.encodeImageTobase64(bitmapIdeb), fileNameKtp, UPLOAD_DATAKTP);
                }
                break;
            case UPLOAD_IDCARD:
                setDataImage(uri_idcard, bitmap_idcard, binding.ivIdcard, imageReturnedIntent, "idcard");
                break;
            case UPLOAD_KTPPASANGAN:
                setDataImage(uri_ktppasangan, bitmap_ktppasangan, binding.ivKtpPasangan, imageReturnedIntent, "ktppasangan");
                break;
            case UPLOAD_ASSETAKAD:
                setDataImage(uri_assetakad, bitmap_assetakad, binding.ivAssetAkad, imageReturnedIntent, "assetakad");
                break;
            case UPLOAD_DATAINSTASI:
                setDataImage(uri_datainstansi, bitmap_datainstansi, binding.ivSuratInstansi, imageReturnedIntent, "datainstansi");
                break;
            case UPLOAD_FORMAPLIKASI:
                setDataImage(uri_formaplikasi, bitmap_formaplikasi, binding.ivFormApplikasi, imageReturnedIntent, "formaplikasi");
                break;
            case UPLOAD_NPWP:
                setDataImage(uri_npwp, bitmap_npwp, binding.ivNpwp, imageReturnedIntent, "npwp");
                break;
            case UPLOAD_SKPENSIUN:
                setDataImage(uri_skpensiun, bitmap_skpensiun, binding.ivSkPensiun, imageReturnedIntent, "skpensiun");
                break;
            case UPLOAD_SKPENGANGKATAN:
                setDataImage(uri_skpengangkatan, bitmap_skpengangkatan, binding.ivSkPengangkatan, imageReturnedIntent, "skpengangkatan");
                break;
            case UPLOAD_SKTERAKHIR:
                setDataImage(uri_skterakhir, bitmap_skterakhir, binding.ivSkTerakhir, imageReturnedIntent, "skterakhir");
                break;

        }
    }

    //logical doc masih setengah
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        setDataImage(uri_fotoktp,bitmap_fotoktp,binding.ivKtpNasabah, data, "fotoktp");
//        if(tipeFile.equalsIgnoreCase("pdf")){
//            fileName=idAplikasi+"_ktpD3.pdf";
//            uploadFile(val_ktp,fileName);
//        }
//        else{
//            binding.ivKtpNasabah.invalidate();
//            RoundedDrawable drawableIdeb = (RoundedDrawable) binding.ivKtpNasabah.getDrawable();
//            Bitmap bitmapIdeb = drawableIdeb.getSourceBitmap();
//            fileName=idAplikasi+"_ktpD3.png";
//            uploadFile(AppUtil.encodeImageTobase64(bitmapIdeb),fileName);
//        }
////        uploadFoto=true;
//
//    }

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
                iv.setImageBitmap(bitmap);
                if (clicker.equalsIgnoreCase("fotoktp")) {
                    bitmap_fotoktp = bitmap;
                    DataJaminanKTP.setImg(idFileKtp);
                    DataJaminanKTP.setFileName("ktp.png");
                } else if (clicker.equalsIgnoreCase("ktppasangan")) {
                    bitmap_ktppasangan = bitmap;
                    DataJaminanKTPPasangan.setImg(AppUtil.encodeImageTobase64(bitmap));
                    DataJaminanKTPPasangan.setFileName("ktppasangan.png");
                } else if (clicker.equalsIgnoreCase("npwp")) {
                    bitmap_npwp = bitmap;
                    DataJaminanNPWP.setImg(AppUtil.encodeImageTobase64(bitmap));
                    DataJaminanNPWP.setFileName("npwp.png");
                } else if (clicker.equalsIgnoreCase("assetakad")) {
                    bitmap_assetakad = bitmap;
                    DataJaminanAsetAkad.setImg(AppUtil.encodeImageTobase64(bitmap));
                    DataJaminanAsetAkad.setFileName("assetakad.png");
                } else if (clicker.equalsIgnoreCase("formaplikasi")) {
                    bitmap_formaplikasi = bitmap;
                    DataJaminanFormAplikasi.setImg(AppUtil.encodeImageTobase64(bitmap));
                    DataJaminanFormAplikasi.setFileName("formaplikasi.png");
                } else if (clicker.equalsIgnoreCase("skpensiun")) {
                    bitmap_skpensiun = bitmap;
                    DataJaminanSKPensiun.setImg(AppUtil.encodeImageTobase64(bitmap));
                    DataJaminanSKPensiun.setFileName("skpensiun.png");
                } else if (clicker.equalsIgnoreCase("skpengangkatan")) {
                    bitmap_skpengangkatan = bitmap;
                    DataJaminanSKPengangkatan.setImg(AppUtil.encodeImageTobase64(bitmap));
                    DataJaminanSKPengangkatan.setFileName("skpengangkatan.png");
                } else if (clicker.equalsIgnoreCase("skterakhir")) {
                    bitmap_skterakhir = bitmap;
                    DataJaminanSKTerakhir.setImg(AppUtil.encodeImageTobase64(bitmap));
                    DataJaminanSKTerakhir.setFileName("skterakhir.png");
                } else if (clicker.equalsIgnoreCase("datainstansi")) {
                    bitmap_datainstansi = bitmap;
                    DataJaminanSuratRekomendasiInstansi.setImg(AppUtil.encodeImageTobase64(bitmap));
                    DataJaminanSuratRekomendasiInstansi.setFileName("suratinstansi.png");
                } else if (clicker.equalsIgnoreCase("idcard")) {
                    bitmap_idcard = bitmap;
                    DataJaminanIDCard.setImg(AppUtil.encodeImageTobase64(bitmap));
                    DataJaminanIDCard.setFileName("idcard.png");
                }

            } catch (Exception e) {
                e.printStackTrace();
                try {
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));
                    if (clicker.equalsIgnoreCase("fotoktp")) {
                        Uri uriPdf = data.getData();
                        val_ktp = AppUtil.encodeFileToBase64(this, uriPdf);
                        DataJaminanKTP.setImg(idFileKtp);
                        DataJaminanKTP.setFileName("ktp.pdf");
                    } else if (clicker.equalsIgnoreCase("ktppasangan")) {
                        Uri uriPdf = data.getData();
                        val_ktppasangan = AppUtil.encodeFileToBase64(this, uriPdf);
                        DataJaminanKTPPasangan.setImg(val_ktppasangan);
                        DataJaminanKTPPasangan.setFileName("ktppasangan.pdf");
                    } else if (clicker.equalsIgnoreCase("npwp")) {
                        Uri uriPdf = data.getData();
                        val_npwp = AppUtil.encodeFileToBase64(this, uriPdf);
                        DataJaminanNPWP.setImg(val_npwp);
                        DataJaminanNPWP.setFileName("npwp.pdf");
                    } else if (clicker.equalsIgnoreCase("assetakad")) {
                        Uri uriPdf = data.getData();
                        val_assetakad = AppUtil.encodeFileToBase64(this, uriPdf);
                        DataJaminanAsetAkad.setImg(val_assetakad);
                        DataJaminanAsetAkad.setFileName("assetakad.pdf");
                    } else if (clicker.equalsIgnoreCase("formaplikasi")) {
                        Uri uriPdf = data.getData();
                        val_formaplikasi = AppUtil.encodeFileToBase64(this, uriPdf);
                        DataJaminanFormAplikasi.setImg(val_formaplikasi);
                        DataJaminanFormAplikasi.setFileName("formaplikasi.pdf");
                    } else if (clicker.equalsIgnoreCase("skpensiun")) {
                        Uri uriPdf = data.getData();
                        val_skpensiun = AppUtil.encodeFileToBase64(this, uriPdf);
                        DataJaminanSKPensiun.setImg(val_skpensiun);
                        DataJaminanSKPensiun.setFileName("skpensiun.pdf");
                    } else if (clicker.equalsIgnoreCase("skpengangkatan")) {
                        Uri uriPdf = data.getData();
                        val_skpengangkatan = AppUtil.encodeFileToBase64(this, uriPdf);
                        DataJaminanSKPengangkatan.setImg(val_skpengangkatan);
                        DataJaminanSKPengangkatan.setFileName("skpengangkatan.pdf");
                    } else if (clicker.equalsIgnoreCase("skterakhir")) {
                        Uri uriPdf = data.getData();
                        val_skterakhir = AppUtil.encodeFileToBase64(this, uriPdf);
                        DataJaminanSKTerakhir.setImg(val_skterakhir);
                        DataJaminanSKTerakhir.setFileName("skterakhir.pdf");
                    } else if (clicker.equalsIgnoreCase("datainstansi")) {
                        Uri uriPdf = data.getData();
                        val_datainstansi = AppUtil.encodeFileToBase64(this, uriPdf);
                        DataJaminanSuratRekomendasiInstansi.setImg(val_datainstansi);
                        DataJaminanSuratRekomendasiInstansi.setFileName("datainstansi.pdf");
                    } else if (clicker.equalsIgnoreCase("idcard")) {
                        Uri uriPdf = data.getData();
                        val_datainstansi = AppUtil.encodeFileToBase64(this, uriPdf);
                        DataJaminanIDCard.setImg(val_datainstansi);
                        DataJaminanIDCard.setFileName("idcard.pdf");
                    }
                } catch (NullPointerException e2) {
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.banner_placeholder));
                    e2.printStackTrace();
                }


            }
        }

    }

    @Override
    public void success(boolean val) {
        finish();
    }

    @Override
    public void confirm(boolean val) {

    }

    public void uploadFile(String base64, String fileName, int uploadCode) {
        ApiClientAdapter apiClientAdapter = new ApiClientAdapter(this);
        //  dataUser = getListUser();
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
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
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {

                    if (uploadCode == UPLOAD_DATAKTP) {
                        idFileKtp = response.body().getId();
                        DataJaminanKTP.setImg(idFileKtp);
                    }


                    AppUtil.notifsuccess(DataJaminanActivity.this, findViewById(android.R.id.content), "Upload Berhasil");
//                    sudahUpload=true;
                } else {
                    AppUtil.notiferror(DataJaminanActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseFile> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataJaminanActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });

    }
}
