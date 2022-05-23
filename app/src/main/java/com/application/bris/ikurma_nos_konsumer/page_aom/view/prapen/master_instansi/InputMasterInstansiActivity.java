package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.master_instansi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseFile;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseLogicalDoc;
import com.application.bris.ikurma_nos_konsumer.api.model.request.foto.ReqUploadFile;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.JaminandanDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDocument;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqInquery;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityInputInstansiBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class InputMasterInstansiActivity extends AppCompatActivity implements View.OnClickListener, CameraListener, ConfirmListener, GenericListenerOnSelect {
    private PrapenAoActivityInputInstansiBinding binding;
    List<JaminandanDokumen> jd;

    private String fileNamePks = "",fileNameLain1 = "",fileNameLain2 = "",fileNameLkp = "", tipeFile;
    private String idFilePks = "",idFileLain1 = "",idFileLain2 = "",idFileLkp= "";
    private boolean rekeningBerubah=false;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    ReqDocument JDJaminanKTP, JDJaminanKTPPasangan, JDJaminanNPWP, JDJaminanFormAplikasi, JDJaminanAsetAKAD, JDJaminanSKPensiun, JDJaminanSKPengangkatan, JDJaminanSKTerakhir, JDJaminanSuratRekomendasiInstansi, JDJaminanIDCard,JDFormAplikasi2;
    ReqDocument DataJaminanKTP = new ReqDocument(), DataJaminanKTPPasangan = new ReqDocument(),
            DataJaminanNPWP = new ReqDocument(), DataJaminanFormAplikasi = new ReqDocument(),
            DataJaminanAsetAkad = new ReqDocument(), DataJaminanSKPensiun = new ReqDocument(),
            DataJaminanSKPengangkatan = new ReqDocument(), DataJaminanSKTerakhir = new ReqDocument(),
            DataJaminanSuratRekomendasiInstansi = new ReqDocument(), DataJaminanIDCard = new ReqDocument(),
            DataJaminanFormAplikasi2 = new ReqDocument();

    private List<MGenericModel> dropdownInstansiInduk=new ArrayList<>(),dropdownTipePembayaran=new ArrayList<>(),dropdownJenisInstansi=new ArrayList<>(),dropdownPks=new ArrayList<>(),dropdownRuangLingkup=new ArrayList<>(),dropdownUnitBisnis=new ArrayList<>(),dropdownPerpanjangOtomatis=new ArrayList<>(),dropdownStatusPks=new ArrayList<>(),dropdownJasaPengelolaan=new ArrayList<>();

    private String val_pks = "", val_lain_1 = "", val_lain_2 = "", val_lkp = "";
    private final int UPLOAD_PKS = 1, UPLOAD_LAIN_1 = 2, UPLOAD_LAIN_2 = 3, UPLOAD_LKP = 4;
    int idUpload=0;
    boolean dialogOpened=false;
    boolean errorUpload=false;
    private  boolean lolosValidasi =true;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PrapenAoActivityInputInstansiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);

        onclickSelectDialog();
        setContentView(view);
        disableText();
        backgroundStatusBar();
        isiDropdown();
        otherViewChanges();
        //        initdata();
        AppUtil.toolbarRegular(this, "Input Instansi");

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
                        String SSJaminanFormAplikasi2 = response.body().getData().get("DataJaminanFormAplikasi2").getAsJsonArray().get(0).toString();

                        String SSJaminanKtpPasangan;
                        try{
                            SSJaminanKtpPasangan = response.body().getData().get("DataJaminanKTPPasangan").getAsJsonArray().get(0).toString();
                        }
                        catch (IndexOutOfBoundsException e){
                            e.printStackTrace();
                            SSJaminanKtpPasangan = "";
                        }

                        String SSJaminanSkPensiun;
                        try{
                            SSJaminanSkPensiun = response.body().getData().get("DataJaminanSKPensiun").getAsJsonArray().get(0).toString();
                        }
                        catch (IndexOutOfBoundsException e){
                            e.printStackTrace();
                            SSJaminanSkPensiun = "";
                        }


                        Type type = new TypeToken<List<JaminandanDokumen>>() {
                        }.getType();
                        jd = gson.fromJson(listDataString, type);
                        JDJaminanKTP = gson.fromJson(SSJaminanKtp, ReqDocument.class);
                        JDJaminanNPWP = gson.fromJson(SSJaminanNPWP, ReqDocument.class);
                        JDJaminanAsetAKAD = gson.fromJson(SSjaminanAsetAkad, ReqDocument.class);
                        JDJaminanFormAplikasi = gson.fromJson(SSJaminanFormApp, ReqDocument.class);
                        JDJaminanKTPPasangan = gson.fromJson(SSJaminanKtpPasangan, ReqDocument.class);
                        JDJaminanSKPengangkatan = gson.fromJson(SSJaminanSkPengangkatan, ReqDocument.class);
                        JDJaminanSKTerakhir = gson.fromJson(SSJaminanSkTerakhir, ReqDocument.class);
                        JDJaminanSuratRekomendasiInstansi = gson.fromJson(SSJaminanSuratInstansi, ReqDocument.class);
                        JDJaminanIDCard = gson.fromJson(SSJaminanIdCard, ReqDocument.class);
                        JDJaminanSKPensiun = gson.fromJson(SSJaminanSkPensiun, ReqDocument.class);
                        JDFormAplikasi2 = gson.fromJson(SSJaminanFormAplikasi2, ReqDocument.class);


                        try {


                        } catch (Exception e) {
                            e.printStackTrace();
                            AppUtil.logSecure("error setdata", e.getMessage());
                        }

                        //LOGICAL DOC

                        setDataDokumen(response);


                        //legacy UPLOAD

                        //Set Image KTP Pasangan
//                        if (response.body().getData().get("IsMarried") != null) {
//                            if (response.body().getData().get("IsMarried").getAsString().equalsIgnoreCase("False")) {
//                                binding.rlKtpPasangan.setVisibility(View.GONE);
//                                binding.tvKtpPasangan.setVisibility(View.GONE);
//                            } else {
//                                try {
//                                    DataJaminanKTPPasangan.setImg(JDJaminanKTPPasangan.getImg());
//                                    //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
//                                    if (JDJaminanKTPPasangan.getFileName().substring(JDJaminanKTPPasangan.getFileName().length() - 3, JDJaminanKTPPasangan.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                        DataJaminanKTPPasangan.setFileName("ktppasangan.pdf");
//                                        AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanKTPPasangan.getImg(), binding.ivKtpPasangan, JDJaminanKTPPasangan.getFileName());
//                                    } else {
//                                        DataJaminanKTPPasangan.setFileName("ktppasangan.png");
//                                        AppUtil.convertBase64ToImage(JDJaminanKTPPasangan.getImg(), binding.ivKtpPasangan);
//                                    }
//
//                                } catch (Exception e) {
//                                    AppUtil.logSecure("error setdata", e.getMessage());
//                                }
//                            }
//                        }

                        //Set Image NPWP
//                        try {
//
//                            DataJaminanNPWP.setImg(JDJaminanNPWP.getImg());
//
//                            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
//                            if (JDJaminanNPWP.getFileName().substring(JDJaminanNPWP.getFileName().length() - 3, JDJaminanNPWP.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                DataJaminanNPWP.setFileName("npwp.pdf");
//                                AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanNPWP.getImg(), binding.ivNpwp, JDJaminanNPWP.getFileName());
//                            } else {
//                                DataJaminanNPWP.setFileName("npwp.png");
//                                AppUtil.convertBase64ToImage(JDJaminanNPWP.getImg(), binding.ivNpwp);
//                            }
//
//                        } catch (Exception e) {
//                            AppUtil.logSecure("error setdata", e.getMessage());
//                        }
//                        //Set Image Asset Akad
//                        try {
//
//                            DataJaminanAsetAkad.setImg(JDJaminanAsetAKAD.getImg());
//
//                            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
//                            if (JDJaminanAsetAKAD.getFileName().substring(JDJaminanAsetAKAD.getFileName().length() - 3, JDJaminanAsetAKAD.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                DataJaminanAsetAkad.setFileName("assetakad.pdf");
//                                AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanAsetAKAD.getImg(), binding.ivAssetAkad, JDJaminanAsetAKAD.getFileName());
//                            } else {
//                                DataJaminanAsetAkad.setFileName("assetakad.png");
//                                AppUtil.convertBase64ToImage(JDJaminanAsetAKAD.getImg(), binding.ivAssetAkad);
//                            }
//
//                        } catch (Exception e) {
//                            AppUtil.logSecure("error setdata", e.getMessage());
//                        }
//                        //Set Image Form Aplikasi
//                        try {
//
//                            DataJaminanFormAplikasi.setImg(JDJaminanFormAplikasi.getImg());
//
//                            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
//                            if (JDJaminanFormAplikasi.getFileName().substring(JDJaminanFormAplikasi.getFileName().length() - 3, JDJaminanFormAplikasi.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                DataJaminanFormAplikasi.setFileName("formaplikasi.pdf");
//                                AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanFormAplikasi.getImg(), binding.ivFormApplikasi, JDJaminanFormAplikasi.getFileName());
//                            } else {
//                                DataJaminanFormAplikasi.setFileName("formaplikasi.png");
//                                AppUtil.convertBase64ToImage(JDJaminanFormAplikasi.getImg(), binding.ivFormApplikasi);
//                            }
//
//                        } catch (Exception e) {
//                            AppUtil.logSecure("error setdata", e.getMessage());
//                        }
//
//                        //Set Image Sk Pensiun
//                        if (response.body().getData().get("IsPensiun") != null) {
//                            if (response.body().getData().get("IsPensiun").getAsString().equalsIgnoreCase("False")) {
//                                binding.rlSkPensiun.setVisibility(View.GONE);
//                                binding.tvSkPensiun.setVisibility(View.GONE);
//                            }else{
//                                String SSJaminanSkPensiun = response.body().getData().get("DataJaminanSKPensiun").getAsJsonArray().get(0).toString();
//                                JDJaminanSKPensiun = gson.fromJson(SSJaminanSkPensiun, ReqDocument.class);
//                                try {
//                                    DataJaminanSKPensiun.setImg(JDJaminanSKPensiun.getImg());
//
//                                    //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
//                                    if (JDJaminanSKPensiun.getFileName().substring(JDJaminanSKPensiun.getFileName().length() - 3, JDJaminanSKPensiun.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                        DataJaminanSKPensiun.setFileName("skpensiun.pdf");
//                                        AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanSKPensiun.getImg(), binding.ivSkPensiun, JDJaminanSKPensiun.getFileName());
//                                    } else {
//                                        DataJaminanSKPensiun.setFileName("skpensiun.png");
//                                        AppUtil.convertBase64ToImage(JDJaminanSKPensiun.getImg(), binding.ivSkPensiun);
//                                    }
//
//                                } catch (Exception e) {
//                                    AppUtil.logSecure("error setdata", e.getMessage());
//                                }
//                            }
//                        }
//
//                        //Set Image Sk Pengangkatan
//                        try {
//
//                            DataJaminanSKPengangkatan.setImg(JDJaminanSKPengangkatan.getImg());
//
//                            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
//                            if (JDJaminanSKPengangkatan.getFileName().substring(JDJaminanSKPengangkatan.getFileName().length() - 3, JDJaminanSKPengangkatan.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                DataJaminanSKPengangkatan.setFileName("skpengangkatan.pdf");
//                                AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanSKPengangkatan.getImg(), binding.ivSkPengangkatan, JDJaminanSKPengangkatan.getFileName());
//                            } else {
//                                DataJaminanSKPengangkatan.setFileName("skpengangkatan.png");
//                                AppUtil.convertBase64ToImage(JDJaminanSKPengangkatan.getImg(), binding.ivSkPengangkatan);
//                            }
//
//                        } catch (Exception e) {
//                            AppUtil.logSecure("error setdata", e.getMessage());
//                        }
//
//                        //Set Image Sk Terakhir
//                        try {
//
//                            DataJaminanSKTerakhir.setImg(JDJaminanSKTerakhir.getImg());
//
//                            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
//                            if (JDJaminanSKTerakhir.getFileName().substring(JDJaminanSKTerakhir.getFileName().length() - 3, JDJaminanSKTerakhir.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                DataJaminanSKTerakhir.setFileName("skterakhir.pdf");
//                                AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanSKTerakhir.getImg(), binding.ivSkTerakhir, JDJaminanSKTerakhir.getFileName());
//                            } else {
//                                DataJaminanSKTerakhir.setFileName("skterakhir.png");
//                                AppUtil.convertBase64ToImage(JDJaminanSKTerakhir.getImg(), binding.ivSkTerakhir);
//                            }
//
//                        } catch (Exception e) {
//                            AppUtil.logSecure("error setdata", e.getMessage());
//                        }
//
//                        //Set Image Surat Instansi
//                        try {
//
//                            DataJaminanSuratRekomendasiInstansi.setImg(JDJaminanSuratRekomendasiInstansi.getImg());
//
//                            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
//                            if (JDJaminanSuratRekomendasiInstansi.getFileName().substring(JDJaminanSuratRekomendasiInstansi.getFileName().length() - 3, JDJaminanSuratRekomendasiInstansi.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                DataJaminanSuratRekomendasiInstansi.setFileName("suratinstansi.pdf");
//                                AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanSuratRekomendasiInstansi.getImg(), binding.ivSuratInstansi, JDJaminanSuratRekomendasiInstansi.getFileName());
//                            } else {
//                                DataJaminanSuratRekomendasiInstansi.setFileName("suratinstansi.png");
//                                AppUtil.convertBase64ToImage(JDJaminanSuratRekomendasiInstansi.getImg(), binding.ivSuratInstansi);
//                            }
//
//                        } catch (Exception e) {
//                            AppUtil.logSecure("error setdata", e.getMessage());
//                        }
//                        //Set Image Id Card
//                        try {
//
//                            DataJaminanIDCard.setImg(JDJaminanIDCard.getImg());
//
//                            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
//                            if (JDJaminanIDCard.getFileName().substring(JDJaminanIDCard.getFileName().length() - 3, JDJaminanIDCard.getFileName().length()).equalsIgnoreCase("pdf")) {
//                                DataJaminanIDCard.setFileName("idcard.pdf");
//                                AppUtil.convertBase64ToFileWithOnClick(DataJaminanActivity.this, JDJaminanIDCard.getImg(), binding.ivIdcard, JDJaminanIDCard.getFileName());
//                            } else {
//                                DataJaminanIDCard.setFileName("idcard.png");
//                                AppUtil.convertBase64ToImage(JDJaminanIDCard.getImg(), binding.ivIdcard);
//                            }
//
//                        } catch (Exception e) {
//                            AppUtil.logSecure("error setdata", e.getMessage());
//                        }

                    } else {
                        AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                } else {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    Error error = ParseResponseError.confirmEror(response.errorBody());
                    AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), error.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ParseResponseAgunan> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void setDataDokumen(Response<ParseResponseAgunan> response){

        //BELUM ADA APInya buat dapet ID
        //Set Image PKS
        try {
//            checkFileTypeThenSet(InputMasterInstansiActivity.this,JDJaminanKTP.getImg(),binding.ivFotoPks,JDJaminanKTP.getFileName());
//            DataJaminanKTP.setImg(JDJaminanKTP.getImg());
//            DataJaminanKTP.setFileName(JDJaminanKTP.getFileName());

        } catch (Exception e) {
            e.printStackTrace();
            AppUtil.logSecure("error setdata", e.getMessage());
        }

        //Set Image dokumen lain 1
        try {
//            checkFileTypeThenSet(InputMasterInstansiActivity.this,JDJaminanKTP.getImg(),binding.ivDokumenTambahan1,JDJaminanKTP.getFileName());
//            DataJaminanKTP.setImg(JDJaminanKTP.getImg());
//            DataJaminanKTP.setFileName(JDJaminanKTP.getFileName());

        }
        catch (Exception e) {
            e.printStackTrace();
            AppUtil.logSecure("error setdata", e.getMessage());
        }

        //Set Image dokumen lain 2
        try {
//            checkFileTypeThenSet(InputMasterInstansiActivity.this,JDJaminanKTP.getImg(),binding.ivDokumenTambahan2,JDJaminanKTP.getFileName());
//            DataJaminanKTP.setImg(JDJaminanKTP.getImg());
//            DataJaminanKTP.setFileName(JDJaminanKTP.getFileName());

        } catch (Exception e) {
            e.printStackTrace();
            AppUtil.logSecure("error setdata", e.getMessage());
        }

        //Set Image LKP
        try {
//            checkFileTypeThenSet(InputMasterInstansiActivity.this,JDJaminanKTP.getImg(),binding.ivFotoLkp,JDJaminanKTP.getFileName());
//            DataJaminanKTP.setImg(JDJaminanKTP.getImg());
//            DataJaminanKTP.setFileName(JDJaminanKTP.getFileName());

        } catch (Exception e) {
            e.printStackTrace();
            AppUtil.logSecure("error setdata", e.getMessage());
        }

    }


    private void disableText() {
        binding.etKantorCabang.setFocusable(false);
        binding.etAreaCabang.setFocusable(false);
        binding.etRegional.setFocusable(false);
        binding.etCifCabang.setFocusable(false);
        binding.etIdMasterInstansi.setFocusable(false);
        binding.etInstansiInduk.setFocusable(false);
        binding.etTipePembayaran.setFocusable(false);
        binding.etJenisInstansi.setFocusable(false);
        binding.etMemilikiPks.setFocusable(false);
        binding.etRuangLingkupPks.setFocusable(false);
        binding.etUnitBisnisPengusul.setFocusable(false);
        binding.etTanggalMulaiPks.setFocusable(false);
        binding.etTanggalAkhirPks.setFocusable(false);
        binding.etJangkaWaktuPks.setFocusable(false);
        binding.etPerpanjangPksOtomatis.setFocusable(false);
        binding.etStatusPks.setFocusable(false);
        binding.etJasaPengelolaan.setFocusable(false);
        binding.etTanggalLkpUtama.setFocusable(false);
        binding.etTanggalLkpUtamaKadaluarsa .setFocusable(false);

    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void onclickSelectDialog() {
        binding.tfInstansiInduk.setOnClickListener(this);
        binding.etInstansiInduk.setOnClickListener(this);
        binding.tfTipePembayaran.setOnClickListener(this);
        binding.etTipePembayaran.setOnClickListener(this);
        binding.tfJenisInstansi.setOnClickListener(this);
        binding.etJenisInstansi.setOnClickListener(this);
        binding.tfMemilikiPks.setOnClickListener(this);
        binding.etMemilikiPks.setOnClickListener(this);
        binding.tfRuangLingkupPks.setOnClickListener(this);
        binding.etRuangLingkupPks.setOnClickListener(this);
        binding.tfUnitBisnisPengusul.setOnClickListener(this);
        binding.etUnitBisnisPengusul.setOnClickListener(this);
        binding.tfInstansiInduk.setOnClickListener(this);
        binding.etInstansiInduk.setOnClickListener(this);
        binding.tfPerpanjangPks.setOnClickListener(this);
        binding.etPerpanjangPksOtomatis.setOnClickListener(this);
        binding.tfStatusPks.setOnClickListener(this);
        binding.etStatusPks.setOnClickListener(this);
        binding.tfJasaPengelolaan.setOnClickListener(this);
        binding.etJasaPengelolaan.setOnClickListener(this);
        binding.btnCekEscrow.setOnClickListener(this);
        binding.btnSimpanDataInstansi.setOnClickListener(this);

        binding.tfTanggalMulaiPks.getEndIconImageButton().setOnClickListener(v -> AppUtil.genericCalendarDialog(InputMasterInstansiActivity.this,binding.etTanggalMulaiPks));
        binding.etTanggalMulaiPks.setOnClickListener(v -> AppUtil.genericCalendarDialog(InputMasterInstansiActivity.this,binding.etTanggalMulaiPks));
        binding.tfTanggalAkhirPks.getEndIconImageButton().setOnClickListener(v -> AppUtil.genericCalendarDialog(InputMasterInstansiActivity.this,binding.etTanggalAkhirPks));
        binding.etTanggalAkhirPks.setOnClickListener(v -> AppUtil.genericCalendarDialog(InputMasterInstansiActivity.this,binding.etTanggalAkhirPks));
        binding.tfTanggalLkpUtama.getEndIconImageButton().setOnClickListener(v -> AppUtil.genericCalendarDialog(InputMasterInstansiActivity.this,binding.etTanggalLkpUtama));
        binding.etTanggalLkpUtama.setOnClickListener(v -> AppUtil.genericCalendarDialog(InputMasterInstansiActivity.this,binding.etTanggalLkpUtama));
        binding.tfTanggalLkpUtamaKadaluarsa.getEndIconImageButton().setOnClickListener(v -> AppUtil.genericCalendarDialog(InputMasterInstansiActivity.this,binding.etTanggalLkpUtamaKadaluarsa));
        binding.etTanggalLkpUtamaKadaluarsa.setOnClickListener(v -> AppUtil.genericCalendarDialog(InputMasterInstansiActivity.this,binding.etTanggalLkpUtamaKadaluarsa));

        binding.ivFotoPks.setOnClickListener(this);
        binding.ivDokumenTambahan1.setOnClickListener(this);
        binding.ivDokumenTambahan2.setOnClickListener(this);
        binding.ivFotoLkp.setOnClickListener(this);
        binding.btnFotoPks.setOnClickListener(this);
        binding.btnDokumenTambahan1.setOnClickListener(this);
        binding.btnDokumenTambahan2.setOnClickListener(this);
        binding.btnFotoLkp.setOnClickListener(this);

        easyEndIconDropdownClick(binding.tfInstansiInduk,dropdownInstansiInduk);
        easyEndIconDropdownClick(binding.tfTipePembayaran,dropdownTipePembayaran);
        easyEndIconDropdownClick(binding.tfJenisInstansi,dropdownJenisInstansi);
        easyEndIconDropdownClick(binding.tfMemilikiPks,dropdownPks);
        easyEndIconDropdownClick(binding.tfRuangLingkupPks,dropdownRuangLingkup);
        easyEndIconDropdownClick(binding.tfUnitBisnisPengusul,dropdownUnitBisnis);
        easyEndIconDropdownClick(binding.tfPerpanjangPks,dropdownPerpanjangOtomatis);
        easyEndIconDropdownClick(binding.tfStatusPks,dropdownStatusPks);
    }

    private void easyEndIconDropdownClick(TextFieldBoxes textFieldBoxes,List<MGenericModel> dropdown){
        textFieldBoxes.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),textFieldBoxes.getLabelText(),dropdown,InputMasterInstansiActivity.this);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_foto_pks:
            case R.id.btn_foto_pks:
                BSUploadFile.displayWithTitle(InputMasterInstansiActivity.this.getSupportFragmentManager(), this, "");
                idUpload = UPLOAD_PKS;
                break;
            case R.id.iv_dokumen_tambahan_1:
            case R.id.btn_dokumen_tambahan_1:
                BSUploadFile.displayWithTitle(InputMasterInstansiActivity.this.getSupportFragmentManager(), this, "");
                idUpload = UPLOAD_LAIN_1;
                break;
            case R.id.iv_dokumen_tambahan_2:
            case R.id.btn_dokumen_tambahan_2:
                BSBottomCamera.displayWithTitle(InputMasterInstansiActivity.this.getSupportFragmentManager(), this, "");
                idUpload = UPLOAD_LAIN_2;
                break;
            case R.id.iv_foto_lkp:
            case R.id.btn_foto_lkp:
                BSBottomCamera.displayWithTitle(InputMasterInstansiActivity.this.getSupportFragmentManager(), this, "");
                idUpload = UPLOAD_LKP;
                break;
            case R.id.tf_instansi_induk:
            case R.id.et_instansi_induk:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfInstansiInduk.getLabelText(),dropdownInstansiInduk,this);
                break;
            case R.id.tf_tipe_pembayaran:
            case R.id.et_tipe_pembayaran:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTipePembayaran.getLabelText(),dropdownTipePembayaran,this);
                break;
            case R.id.tf_jenis_instansi:
            case R.id.et_jenis_instansi:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJenisInstansi.getLabelText(),dropdownJenisInstansi,this);
                break;
            case R.id.tf_memiliki_pks:
            case R.id.et_memiliki_pks:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfMemilikiPks.getLabelText(),dropdownPks,this);
                break;
            case R.id.tf_ruang_lingkup_pks:
            case R.id.et_ruang_lingkup_pks:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfRuangLingkupPks.getLabelText(),dropdownRuangLingkup,this);
                break;
            case R.id.tf_unit_bisnis_pengusul:
            case R.id.et_unit_bisnis_pengusul:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfUnitBisnisPengusul.getLabelText(),dropdownUnitBisnis,this);
                break;
            case R.id.tf_perpanjang_pks:
            case R.id.et_perpanjang_pks_otomatis:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfPerpanjangPks.getLabelText(),dropdownPerpanjangOtomatis,this);
                break;
            case R.id.tf_status_pks:
            case R.id.et_status_pks:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfStatusPks.getLabelText(),dropdownStatusPks,this);
                break;
            case R.id.tf_jasa_pengelolaan:
            case R.id.et_jasa_pengelolaan:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJasaPengelolaan.getLabelText(),dropdownJasaPengelolaan,this);
                break;
            case R.id.btn_simpan_data_instansi:
                if(rekeningBerubah){
                    binding.tfNomorEscrow.setError("Harap Cek Rekening",true);
                    AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), "Harap cek nomor rekening dahulu");
                }
                else{
                    validasi();
                }

                break;
            case R.id.btn_cek_escrow:
                if(binding.etNomorEscrow.getText().toString().isEmpty()){
                    binding.tfNomorEscrow.setError("Harap isi rekening",true);
                }
                else{
                    Toast.makeText(this, "Pura pura konek API", Toast.LENGTH_SHORT).show();
                    binding.etKantorCabang.setText("Kantor Cabang 1");
                    binding.etAreaCabang.setText("Area 1");
                    binding.etRegional.setText("Regional 1");
                    binding.etCifCabang.setText("0000000000");
                    binding.etIdMasterInstansi.setText("67888099000");
                    rekeningBerubah=false;
                    break;
                }

        }
    }

    private void validasi() {
        lolosValidasi =true;
        easyValidateField(binding.etNamaInstansi,binding.tfNamaInstansi);
        easyValidateField(binding.etNomorEscrow,binding.tfNomorEscrow);
        easyValidateField(binding.etNamaInstansi,binding.tfNamaInstansi);
        easyValidateField(binding.etKantorCabang,binding.tfKantorCabang);
        easyValidateField(binding.etAreaCabang,binding.tfAreaCabang);
        easyValidateField(binding.etRegional,binding.tfRegional);
        easyValidateField(binding.etCifCabang,binding.tfCifCabang);
        easyValidateField(binding.etIdMasterInstansi,binding.tfIdMasterInstansi);
        easyValidateField(binding.etInstansiInduk,binding.tfInstansiInduk);
        easyValidateField(binding.etTipePembayaran,binding.tfTipePembayaran);
        easyValidateField(binding.etJenisInstansi,binding.tfJenisInstansi);
        easyValidateField(binding.etTahunBerdiri,binding.tfTahunBerdiri);
        easyValidateField(binding.etMemilikiPks,binding.tfMemilikiPks);

        if(binding.etMemilikiPks.getText().toString().equalsIgnoreCase("ya")){
            easyValidateField(binding.etRuangLingkupPks,binding.tfRuangLingkupPks);
            easyValidateField(binding.etUnitBisnisPengusul,binding.tfUnitBisnisPengusul);
            easyValidateField(binding.etNomorPks,binding.tfNomorPks);
            easyValidateField(binding.etTanggalMulaiPks,binding.tfTanggalAkhirPks);
            easyValidateField(binding.etJangkaWaktuPks,binding.tfJangkaWaktuPks);
            easyValidateField(binding.etPerpanjangPksOtomatis,binding.tfPerpanjangPks);
            easyValidateField(binding.etStatusPks,binding.tfStatusPks);
        }

        easyValidateField(binding.etAlamatKorespondensi,binding.tfAlamatKorespondensi);
        easyValidateField(binding.etKeyPerson,binding.tfKeyPerson);
        easyValidateField(binding.etTeleponKeyPerson,binding.tfTeleponKeyPerson);
        easyValidateField(binding.etTeleponInstansi,binding.tfTeleponInstansi);
        easyValidateField(binding.etJasaPengelolaan,binding.tfJasaPengelolaan);
        easyValidateField(binding.etTanggalLkpUtama,binding.tfTanggalLkpUtama);
        easyValidateField(binding.etTanggalLkpUtamaKadaluarsa,binding.tfTanggalLkpUtamaKadaluarsa);
        if(lolosValidasi){
            //do send data
            Toast.makeText(this, "Nit not lolos validasi dan pura pura nyimpen", Toast.LENGTH_SHORT).show();
        }
    }

    private void easyValidateField(EditText editText, TextFieldBoxes textFieldBoxes){
        if(editText.getText().toString().trim().isEmpty() || editText.getText().toString().trim().equalsIgnoreCase(" ")){
            textFieldBoxes.setError(textFieldBoxes.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), textFieldBoxes.getLabelText() + " " + getString(R.string.title_validate_field));
        }
        lolosValidasi =false;
    }

    private void isiDropdown(){
        dropdownInstansiInduk.add(new MGenericModel("1","Instansi 1"));
        dropdownInstansiInduk.add(new MGenericModel("2","Instansi 2"));

        dropdownTipePembayaran.add(new MGenericModel("1","Payroll"));
        dropdownTipePembayaran.add(new MGenericModel("2","Non Payroll"));

        dropdownJenisInstansi.add(new MGenericModel("1","SKPP"));
        dropdownJenisInstansi.add(new MGenericModel("2","SKPD"));
        dropdownJenisInstansi.add(new MGenericModel("3","TNI/POLRI"));
        dropdownJenisInstansi.add(new MGenericModel("4","Lembaga Negara"));
        dropdownJenisInstansi.add(new MGenericModel("5","BUMN Group"));
        dropdownJenisInstansi.add(new MGenericModel("6","Wholesale & SME Group"));
        dropdownJenisInstansi.add(new MGenericModel("7","BUMD"));
        dropdownJenisInstansi.add(new MGenericModel("8","Perusahaan Swasta"));
        dropdownJenisInstansi.add(new MGenericModel("9","Yayasan"));
        dropdownJenisInstansi.add(new MGenericModel("10","Rumah Sakit"));

        dropdownPks.add(new MGenericModel("1","Ya"));
        dropdownPks.add(new MGenericModel("2","Tidak"));

        dropdownUnitBisnis.add(new MGenericModel("1","Cabang 1"));
        dropdownUnitBisnis.add(new MGenericModel("2","Cabang 2"));

        dropdownRuangLingkup.add(new MGenericModel("1","PKS Pusat"));
        dropdownRuangLingkup.add(new MGenericModel("2","PKS Lokal Area"));
        dropdownRuangLingkup.add(new MGenericModel("3","PKS Lokal Branch"));

        dropdownPerpanjangOtomatis.add(new MGenericModel("1","Otomatis"));
        dropdownPerpanjangOtomatis.add(new MGenericModel("2","Perpanjangan Manual"));

        dropdownStatusPks.add(new MGenericModel("1","Baru"));
        dropdownStatusPks.add(new MGenericModel("2","Perpanjangan"));

        dropdownJasaPengelolaan.add(new MGenericModel("1","Ya"));
        dropdownJasaPengelolaan.add(new MGenericModel("2","Tidak"));

    }

    private void otherViewChanges(){
        //di hide dlu soalnya agak repot buat nyari time differencenya
        binding.tfJangkaWaktuPks.setVisibility(View.GONE);

        binding.etNomorEscrow.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                rekeningBerubah=true;

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etTanggalMulaiPks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SimpleDateFormat tanggalMulai=new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat tanggalAkhir=new SimpleDateFormat("dd-MM-yyyy");
                try{
                    Date tanggalAwalDate=tanggalMulai.parse(binding.etTanggalMulaiPks.getText().toString());
                    Date tanggalAkhirDate=tanggalAkhir.parse(binding.etTanggalMulaiPks.getText().toString());

                    Calendar startCalendar = new GregorianCalendar();
                    startCalendar.setTime(tanggalAwalDate);
                    Calendar endCalendar = new GregorianCalendar();
                    endCalendar.setTime(tanggalAkhirDate);

                    int diffMonth = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

                    binding.etJangkaWaktuPks.setText(Integer.toString(diffMonth)+ " Bulan");

                }
                catch (ParseException e){
                    e.printStackTrace();
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etTanggalAkhirPks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SimpleDateFormat tanggalMulai=new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat tanggalAkhir=new SimpleDateFormat("dd-MM-yyyy");
                try{
                    Date tanggalAwalDate=tanggalMulai.parse(binding.etTanggalMulaiPks.getText().toString());
                    Date tanggalAkhirDate=tanggalAkhir.parse(binding.etTanggalMulaiPks.getText().toString());

                    Calendar startCalendar = new GregorianCalendar();
                    startCalendar.setTime(tanggalAwalDate);
                    Calendar endCalendar = new GregorianCalendar();
                    endCalendar.setTime(tanggalAkhirDate);

                    int diffMonth = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
                    String bulan = String.valueOf(diffMonth).concat(" Bulan");

                    binding.etJangkaWaktuPks.setText(bulan);
                    AppUtil.logSecure("jangkrik",bulan);

                }
                catch (ParseException e){
                    e.printStackTrace();
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    //UPLOAD FILE METHODS

    private void easySelectCameraMenu(String idMenu, int KODE_UPLOAD, String namaDokumen){
        if(dialogOpened){
            if(idMenu.equalsIgnoreCase("Take Photo")){
                tipeFile = "png";
                openCamera(KODE_UPLOAD, namaDokumen);
            }
            else if(idMenu.equalsIgnoreCase("Pick Photo")){
                tipeFile="png";
                openGalery(KODE_UPLOAD);
            }
            else if(idMenu.equalsIgnoreCase("Pick File")){
                tipeFile="pdf";
                openFile(KODE_UPLOAD);
            }
            dialogOpened=false;
        }


    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        dialogOpened=true;
        easySelectCameraMenu(idMenu,UPLOAD_PKS,"dokPks");
        easySelectCameraMenu(idMenu,UPLOAD_LAIN_1,"dokLain1");
        easySelectCameraMenu(idMenu,UPLOAD_LAIN_2,"dokLain2");
        easySelectCameraMenu(idMenu,UPLOAD_LKP,"dokLkp");
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

    public void checkCameraPermission(int cameraCode, String namaFoto) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    100);
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
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                outputFileUri = FileProvider.getUriForFile(InputMasterInstansiActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), namaFoto + ".png"));
            } else {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), namaFoto + ".png"));
            }
        }
        return outputFileUri;
    }


    //legacy upload
//    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
//        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
//        switch (requestCode) {
//            case UPLOAD_DATAKTP:
//                //legacy
////                setDataImage(uri_fotoktp, bitmap_fotoktp, binding.ivKtpNasabah, imageReturnedIntent, "fotoktp");
//
//                //logicaldoc
//                setDataImage(uri_fotoktp, bitmap_fotoktp, binding.ivKtpNasabah, imageReturnedIntent, "fotoktp");
//                if (tipeFile.equalsIgnoreCase("pdf")) {
//                    fileNameKtp = idAplikasi + "_ktpD3.pdf";
//                    uploadFile(val_ktp, fileNameKtp, UPLOAD_DATAKTP);
//                } else {
//                    binding.ivKtpNasabah.invalidate();
//                    RoundedDrawable drawableIdeb = (RoundedDrawable) binding.ivKtpNasabah.getDrawable();
//                    Bitmap bitmapIdeb = drawableIdeb.getSourceBitmap();
//                    fileNameKtp = idAplikasi + "_ktpD3.png";
//                    uploadFile(AppUtil.encodeImageTobase64(bitmapIdeb), fileNameKtp, UPLOAD_DATAKTP);
//                }
//                break;
//            case UPLOAD_IDCARD:
//                setDataImage(uri_idcard, bitmap_idcard, binding.ivIdcard, imageReturnedIntent, "idcard");
//                break;
//            case UPLOAD_KTPPASANGAN:
//                setDataImage(uri_ktppasangan, bitmap_ktppasangan, binding.ivKtpPasangan, imageReturnedIntent, "ktppasangan");
//                break;
//            case UPLOAD_ASSETAKAD:
//                setDataImage(uri_assetakad, bitmap_assetakad, binding.ivAssetAkad, imageReturnedIntent, "assetakad");
//                break;
//            case UPLOAD_DATAINSTASI:
//                setDataImage(uri_datainstansi, bitmap_datainstansi, binding.ivSuratInstansi, imageReturnedIntent, "datainstansi");
//                break;
//            case UPLOAD_FORMAPLIKASI:
//                setDataImage(uri_formaplikasi, bitmap_formaplikasi, binding.ivFormApplikasi, imageReturnedIntent, "formaplikasi");
//                break;
//            case UPLOAD_NPWP:
//                setDataImage(uri_npwp, bitmap_npwp, binding.ivNpwp, imageReturnedIntent, "npwp");
//                break;
//            case UPLOAD_SKPENSIUN:
//                setDataImage(uri_skpensiun, bitmap_skpensiun, binding.ivSkPensiun, imageReturnedIntent, "skpensiun");
//                break;
//            case UPLOAD_SKPENGANGKATAN:
//                setDataImage(uri_skpengangkatan, bitmap_skpengangkatan, binding.ivSkPengangkatan, imageReturnedIntent, "skpengangkatan");
//                break;
//            case UPLOAD_SKTERAKHIR:
//                setDataImage(uri_skterakhir, bitmap_skterakhir, binding.ivSkTerakhir, imageReturnedIntent, "skterakhir");
//                break;
//
//        }
//    }

    //logical doc
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        errorUpload=false;
        switch (requestCode) {
            case UPLOAD_PKS:
                setDataImage(binding.ivFotoPks,data,"dokPks",UPLOAD_PKS);
                checkFileTypeThenUpload(fileNamePks,"_pks",binding.ivFotoPks,val_pks,UPLOAD_PKS);
                break;
            case UPLOAD_LAIN_1:
                setDataImage(binding.ivDokumenTambahan1,data,"dokLain1",UPLOAD_LAIN_1);
                checkFileTypeThenUpload(fileNameLain1,"_dokLain1",binding.ivDokumenTambahan1,val_lain_1,UPLOAD_LAIN_1);
                break;
            case UPLOAD_LAIN_2:
                setDataImage(binding.ivDokumenTambahan2,data,"dokLain2",UPLOAD_LAIN_2);
                checkFileTypeThenUpload(fileNameLain2,"_dokLain2",binding.ivDokumenTambahan2,val_lain_2,UPLOAD_LAIN_2);
                break;
            case UPLOAD_LKP:
                setDataImage(binding.ivFotoLkp,data,"dokLkp",UPLOAD_LKP);
                checkFileTypeThenUpload(fileNameLkp,"_dokLkp",binding.ivFotoLkp,val_lkp,UPLOAD_LKP);
                break;
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
                iv.setImageBitmap(bitmap);

                //BELUM LENGKAP DI BAGIAN OBJEK DATA, KARENA API BELOM ADA GAIS
                if (idUpload==UPLOAD_PKS) {
//                    DataJaminanKTP.setImg(idFileKtp);
//                    DataJaminanKTP.setFileName("dokpks.png");
                } else if (idUpload==UPLOAD_LAIN_1) {
//                    DataJaminanKTPPasangan.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    DataJaminanKTPPasangan.setFileName("doklain1.png");
                } else if (idUpload==UPLOAD_LAIN_2) {
//                    DataJaminanNPWP.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    DataJaminanNPWP.setFileName("doklain2.png");
                } else if (idUpload==UPLOAD_LKP) {
//                    DataJaminanAsetAkad.setImg(AppUtil.encodeImageTobase64(bitmap));
//                    DataJaminanAsetAkad.setFileName("lkp.png");
                }

            } catch (Exception e) {
                e.printStackTrace();
                try {
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));
                    if (idUpload==UPLOAD_PKS) {
                        Uri uriPdf = data.getData();
                        val_pks = AppUtil.encodeFileToBase64(this, uriPdf);
//                        DataJaminanKTP.setImg(idFilePks);
//                        DataJaminanKTP.setFileName("pks.pdf");
                    } else if (idUpload==UPLOAD_LAIN_1) {
                        Uri uriPdf = data.getData();
                        val_lain_1 = AppUtil.encodeFileToBase64(this, uriPdf);
                        DataJaminanKTPPasangan.setImg(val_lain_1);
                        DataJaminanKTPPasangan.setFileName("doklain1.pdf");
                    } else if (idUpload==UPLOAD_LAIN_2) {
                        Uri uriPdf = data.getData();
                        val_lain_2 = AppUtil.encodeFileToBase64(this, uriPdf);
//                        DataJaminanNPWP.setImg(val_lain_2);
//                        DataJaminanNPWP.setFileName("doklain2.pdf");
                    } else if (idUpload==UPLOAD_LKP) {
                        Uri uriPdf = data.getData();
                        val_lkp = AppUtil.encodeFileToBase64(this, uriPdf);
//                        DataJaminanAsetAkad.setImg(val_lkp);
//                        DataJaminanAsetAkad.setFileName("doklkp.pdf");
                    }
                } catch (NullPointerException e2) {
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.banner_placeholder));
                    e2.printStackTrace();
                }


            }
        }

    }

    private void setDataImage(ImageView iv, Intent data, String namaFoto,int KODE_UPLOAD) {
        Bitmap bitmap = null;
        Uri uri;
        if (getPickImageResultUri(data, namaFoto) != null) {
            uri = getPickImageResultUri(data, namaFoto);
            try {

                bitmap = MediaStore.Images.Media.getBitmap(InputMasterInstansiActivity.this.getContentResolver(), uri);
                bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                bitmap = AppUtil.rotateImageIfRequired(InputMasterInstansiActivity.this, bitmap, uri);
                iv.setImageBitmap(bitmap);


            } catch (NullPointerException e) {
                e.printStackTrace();

                try{
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));

                    if(KODE_UPLOAD==UPLOAD_PKS){
                        Uri uriPdf = data.getData();
                        val_pks= AppUtil.encodeFileToBase64(InputMasterInstansiActivity.this,uriPdf);

                    }
                    else if(KODE_UPLOAD==UPLOAD_LAIN_1){
                        Uri uriPdf = data.getData();
                        val_lain_1= AppUtil.encodeFileToBase64(InputMasterInstansiActivity.this,uriPdf);
                    }
                    else if(KODE_UPLOAD==UPLOAD_LAIN_2){
                        Uri uriPdf = data.getData();
                        val_lain_2= AppUtil.encodeFileToBase64(InputMasterInstansiActivity.this,uriPdf);
                    }
                    else if(KODE_UPLOAD==UPLOAD_LKP){
                        Uri uriPdf = data.getData();
                        val_lkp= AppUtil.encodeFileToBase64(InputMasterInstansiActivity.this,uriPdf);
                    }
                }
                catch (Exception e2){
                    e2.printStackTrace();
                }
            }
            catch (FileNotFoundException e2){
                e2.printStackTrace();
                errorUpload=true;
            }
            catch (Exception e3){
                e3.printStackTrace();
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

                    if (uploadCode == UPLOAD_PKS) {
                        idFilePks = response.body().getId();
//                        DataJaminanKTP.setImg(idFilePks);
//                        DataJaminanKTP.setFileName(fileName);
                    }
                    else if (uploadCode == UPLOAD_LAIN_1) {
                        idFileLain1 = response.body().getId();
//                        DataJaminanKTPPasangan.setImg(idFileLain1);
//                        DataJaminanKTPPasangan.setFileName(fileName);
                    }
                    else if (uploadCode == UPLOAD_LAIN_2) {
                        idFileLain2 = response.body().getId();
//                        DataJaminanNPWP.setImg(idFileLain2);
//                        DataJaminanNPWP.setFileName(fileName);
                    }
                    else if (uploadCode == UPLOAD_LKP) {
                        idFileLkp = response.body().getId();
//                        DataJaminanFormAplikasi.setImg(idFileLkp);
//                        DataJaminanFormAplikasi.setFileName(fileName);
                    }

                    AppUtil.notifsuccess(InputMasterInstansiActivity.this, findViewById(android.R.id.content), "Upload Berhasil");
                } else {
                    AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseFile> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
            }
        });

    }

    private void checkFileTypeThenUpload(String filename, String fileNameDoc, ImageView imageView,String val_base64, int uploadCode){

        //jadi ada variabel error upload, untuk ngecek kalau dia error karena ketika sbelum milih gambar tiba tiba di back, maka gambarnya kaga usah diupload
        if(!errorUpload){
            if (tipeFile.equalsIgnoreCase("pdf")) {
                filename =fileNameDoc+".pdf";
                uploadFile(val_base64, filename, uploadCode);
            } else {
                imageView.invalidate();
                RoundedDrawable drawableDoc = (RoundedDrawable) imageView.getDrawable();
                Bitmap bitmapDoc = drawableDoc.getSourceBitmap();
                filename =fileNameDoc+".png";
                uploadFile(AppUtil.encodeImageTobase64(bitmapDoc), filename, uploadCode);
            }
        }

    }

    private void checkFileTypeThenSet(Context context, String idDok, ImageView imageView, String fileName){

        if(fileName.substring(fileName.length()-3,fileName.length()).equalsIgnoreCase("pdf")){

            if(idDok.length()<10){
                loadFileJson(idDok,imageView);
            }
            else{
                AppUtil.convertBase64ToFileWithOnClick(context,idDok,imageView,fileName);
            }

        }
        else{

            if(idDok.length()<10){
                AppUtil.setImageGlide(context,idDok,imageView);
            }
            else{
                AppUtil.convertBase64ToImage(idDok,imageView);
            }

        }
    }

    public void loadFileJson(String idFoto,ImageView imageView) {
        ApiClientAdapter apiClientAdapter=new ApiClientAdapter(InputMasterInstansiActivity.this);
        Call<ParseResponseLogicalDoc> call = apiClientAdapter.getApiInterface().getFileJson(idFoto);
        call.enqueue(new Callback<ParseResponseLogicalDoc>() {
            @Override
            public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
//                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getBinaryData()!=null){
                        AppUtil.convertBase64ToFileWithOnClick(InputMasterInstansiActivity.this,response.body().getBinaryData(),imageView,response.body().getFileName());
                    }
                    else{
                        AppUtil.notiferror(InputMasterInstansiActivity.this,findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                    }
                }
                else{
                    AppUtil.notiferror(InputMasterInstansiActivity.this,findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(InputMasterInstansiActivity.this,findViewById(android.R.id.content), "Terjadi kesalahan");
                
                t.printStackTrace();
            }
        });

    }

    @Override
    public void onSelect(String title, MGenericModel data) {
        if(title.equalsIgnoreCase(binding.tfInstansiInduk.getLabelText())){
            binding.etInstansiInduk.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfTipePembayaran.getLabelText())){
            binding.etTipePembayaran.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfJenisInstansi.getLabelText())){
            binding.etJenisInstansi.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfMemilikiPks.getLabelText())){
            binding.etMemilikiPks.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfRuangLingkupPks.getLabelText())){
            binding.etRuangLingkupPks.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfUnitBisnisPengusul.getLabelText())){
            binding.etUnitBisnisPengusul.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfPerpanjangPks.getLabelText())){
            binding.etPerpanjangPksOtomatis.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfStatusPks.getLabelText())){
            binding.etStatusPks.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfJasaPengelolaan.getLabelText())){
            binding.etJasaPengelolaan.setText(data.getDESC());
        }
    }
}