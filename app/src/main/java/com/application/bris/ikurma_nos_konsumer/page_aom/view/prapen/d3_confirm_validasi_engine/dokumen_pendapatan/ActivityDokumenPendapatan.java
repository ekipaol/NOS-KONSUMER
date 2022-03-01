package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.dokumen_pendapatan;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseLogicalDoc;
import com.application.bris.ikurma_nos_konsumer.api.model.request.foto.ReqUploadFile;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.DokumenPendapatan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDocument;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqInquery;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateDataPendapatan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.isiReqDokumenPendapatan;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.ParseResponseReturn;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.FlagAplikasiPojo;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityPendapatanBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDokumenPendapatan extends AppCompatActivity implements GenericListenerOnSelect, View.OnClickListener, CameraListener, ConfirmListener {

    private ActivityPendapatanBinding binding;
    private DatePickerDialog dpSK;
    private Calendar calLahir;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("MM-yyyy", Locale.US);
    public static SimpleDateFormat dateClient2 = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    SimpleDateFormat formatOutgoing = new SimpleDateFormat("MMMM");
    TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
    List<MGenericModel> dataDropdownPendapatan = new ArrayList<>(),
            dataDropdownPendapatan2 = new ArrayList<>(),
            dataDropdownPendapatan3 = new ArrayList<>();
    String clicker,
            pDatep1, pDatep2, pDatep3;
    DokumenPendapatan dp;

    private String fileNameSlipGajiP1="", fileNameSlipGajiP2="", fileNameSlipGajiP3="", fileNameSlipTunjanganP1="", fileNameSlipTunjanganP2="", fileNameSlipTunjanganP3="",fileNameKoran="";
    private String idFileSlipGajiP1="", idFileSlipGajiP2="",idFileSlipGajiP3="",idFileSlipTunjanganP1="",idFileSlipTunjanganP2="",idFileSlipTunjanganP3="",idFileKoran="";

    private String valGajiP1="",valGajiP2="",valGajiP3="",valTunjanganP1="",valTunjanganP2="",valTunjanganP3="",valKoran="";
    private String tipeFile;
    ReqDocument DPSlipGajiP1, DPSlipGajiP2, DPSlipGajiP3, DPSlipTunjanganP1, DPSlipTunjanganP2, DPSlipTunjanganP3, DPKoran;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    DokumenPendapatan doc = new DokumenPendapatan();
    ReqDocument DokumenPendapatanKoranBank = new ReqDocument(),
            DokumenPendapatanSlipGajiP1 = new ReqDocument(), DokumenPendapatanSlipTunjanganP1 = new ReqDocument(),
            DataJaminanIDCard = new ReqDocument(), DokumenPendapatanSlipGajiP2 = new ReqDocument(),
            DokumenPendapatanSlipTunjanganP2 = new ReqDocument(), DokumenPendapatanSlipGajiP3 = new ReqDocument(),
            DokumenPendapatanSlipTunjanganP3 = new ReqDocument();
    private Uri uri_koran, uri_slipgaji1, uri_slipgaji2, uri_slipgaji3, uri_sliptunjangan1, uri_sliptunjangan2, uri_sliptunjangan3;
    private Bitmap bitmap_koran, bitmap_slipgaji1, bitmap_slipgaji2, bitmap_slipgaji3, bitmap_sliptunjangan1, bitmap_sliptunjangan2, bitmap_sliptunjangan3;
    private final int UPLOAD_KORAN = 1, UPLOAD_SLIPGAJI1 = 2, UPLOAD_SLIPGAJI2 = 3, UPLOAD_SLIPGAJI3 = 4, UPLOAD_SLIPTUNJANGAN1 = 5, UPLOAD_SLIPTUNJANGAN2 = 6, UPLOAD_SLIPTUNJANGAN3 = 7;
//    private String val_koran, val_slipgaji1, val_slipgaji2, val_slipgaji3, val_sliptunjangan1, val_sliptunjangan2, val_sliptunjangan3;


    private Long idAplikasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPendapatanBinding.inflate(getLayoutInflater());
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        View view = binding.getRoot();

        if(getIntent().hasExtra("idAplikasi")){
            idAplikasi=Long.parseLong(getIntent().getStringExtra("idAplikasi"));
        }
        numberText();
        endIconClick();
        onclickSelectDialog();
        setContentView(view);
        backgroundStatusBar();
        setParameterDropdown();
        disableEditText();
        initdata();
        AppUtil.toolbarRegular(this, "Data Dokumen Pendapatan");
    }

    private void initdata() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqInquery req = new ReqInquery();
        req.setApplicationId(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
        Call<ParseResponseAgunan> call = apiClientAdapter.getApiInterface().inqueryDataPendapatan(req);
        call.enqueue(new Callback<ParseResponseAgunan>() {
            @Override
            public void onResponse(Call<ParseResponseAgunan> call, Response<ParseResponseAgunan> response) {
                if (response.isSuccessful()) {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String SSlipGajiP2 = "", SSlipGajiP3 = "", SSlipTunjanganP2 = "", SSlipTunjanganP3 = "", SSKoran = "", SSlipGajiP1 = "", SSlipTunjanganP1 = "";
                        String listDataString = response.body().getData().get("DokumenPendapatan").getAsJsonObject().toString();
                        if (response.body().getData().get("DokumenPendapatanKoranBank") != null) {
                            SSKoran = response.body().getData().get("DokumenPendapatanKoranBank").toString();
                        }
                        if (response.body().getData().get("DokumenPendapatanSlipGajiP1") != null) {
                            SSlipGajiP1 = response.body().getData().get("DokumenPendapatanSlipGajiP1").toString();
                            SSlipTunjanganP1 = response.body().getData().get("DokumenPendapatanSlipTunjanganP1").toString();
                        }

                        if (response.body().getData().get("DokumenPendapatanSlipGajiP2") != null) {
                            SSlipGajiP2 = response.body().getData().get("DokumenPendapatanSlipGajiP2").toString();
                            SSlipGajiP3 = response.body().getData().get("DokumenPendapatanSlipGajiP3").toString();
                            SSlipTunjanganP2 = response.body().getData().get("DokumenPendapatanSlipTunjanganP2").toString();
                            SSlipTunjanganP3 = response.body().getData().get("DokumenPendapatanSlipTunjanganP3").toString();
                        }

                        Gson gson = new Gson();
                        dp = gson.fromJson(listDataString, DokumenPendapatan.class);
                        if (response.body().getData().get("DokumenPendapatanKoranBank") != null) {
                            DPKoran = gson.fromJson(SSKoran, ReqDocument.class);
                        }
                        if (response.body().getData().get("DokumenPendapatanSlipGajiP1") != null) {
                            DPSlipGajiP1 = gson.fromJson(SSlipGajiP1, ReqDocument.class);
                            DPSlipTunjanganP1 = gson.fromJson(SSlipTunjanganP1, ReqDocument.class);
                        }
                        if (response.body().getData().get("DokumenPendapatanSlipGajiP2") != null) {
                            DPSlipGajiP2 = gson.fromJson(SSlipGajiP2, ReqDocument.class);
                            DPSlipTunjanganP2 = gson.fromJson(SSlipTunjanganP2, ReqDocument.class);
                            DPSlipGajiP3 = gson.fromJson(SSlipGajiP3, ReqDocument.class);
                            DPSlipTunjanganP3 = gson.fromJson(SSlipTunjanganP3, ReqDocument.class);
                        }

                        if(dp.getAkseptasiPendapatan()!=null){

                            //legacy setdata
//                            setData(response);

                            //logical doc
                            setDataLogicalDoc(response);
                        }
                        else{
                            binding.etPendapatanPensiun.setText(String.valueOf(dp.getSimulasiPendapatanSaatPen()));
                        }

                    } else {
                        AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                } else {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    Error error = ParseResponseError.confirmEror(response.errorBody());
                    AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), error.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ParseResponseAgunan> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void setData(Response<ParseResponseAgunan> response){
        // Set Data
        try {
            binding.etAkseptasiPendaptan.setText(dp.getAkseptasiPendapatan());
            binding.etPendapatanPensiun.setText(String.valueOf(dp.getSimulasiPendapatanSaatPen()));
            binding.etPeriodeGajiP1.setText(AppUtil.parseTanggalGeneral(dp.getPeriodeGajiP1(), "yyyy-MM-dd", "MM-yyyy"));
            binding.etTglTunjanganP1.setText(AppUtil.parseTanggalGeneral(dp.getPeriodeTunjanganP1(), "yyyy-MM-dd", "MM-yyyy"));
            pDatep1 = AppUtil.parseTanggalGeneral(dp.getPeriodeGajiP1(), "yyyy-MM-dd", "dd-MM-yyyy");
            binding.etGajiBersihP1.setText(String.valueOf(dp.getTotalGajiBersihP1()));
            binding.etTunjanganP1.setText(String.valueOf(dp.getTotalTunjanganBersihP1()));

            if (response.body().getData().get("DokumenPendapatanSlipGajiP2") != null) {
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
            if (response.body().getData().get("CheckNorek").getAsString().equalsIgnoreCase("true")) {
                binding.etVerfikasiRekening.setText("Ya");
            } else {
                binding.etVerfikasiRekening.setText("Tidak");
            }
            if (response.body().getData().get("CheckNorek").getAsString().equalsIgnoreCase("true")) {
                binding.tfNorekTunjangan.setVisibility(View.GONE);
                binding.tfNamaBankTunjangan.setVisibility(View.GONE);
                binding.tfTotalKredit2.setVisibility(View.GONE);
                binding.tfTotalDebit2.setVisibility(View.GONE);
                binding.tfPeriodeAwalWaktu2.setVisibility(View.GONE);
                binding.tfPeriodeAkhirWaktu2.setVisibility(View.GONE);
                binding.norekTunjangan.setVisibility(View.GONE);
            } else if (response.body().getData().get("CheckNorek").getAsString().equalsIgnoreCase("false")) {
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
                AppUtil.convertBase64ToFileWithOnClick(ActivityDokumenPendapatan.this, DPKoran.getImg(), binding.ivRekeningKoran1, DPKoran.getFileName());
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
                AppUtil.convertBase64ToFileWithOnClick(ActivityDokumenPendapatan.this, DPSlipGajiP1.getImg(), binding.ivSlipgajiP1, DPSlipGajiP1.getFileName());
            } else {
                DokumenPendapatanSlipGajiP1.setFileName("slipgaji1.png");
                AppUtil.convertBase64ToImage(DPSlipGajiP1.getImg(), binding.ivSlipgajiP1);
            }
            if (DPSlipTunjanganP1.getFileName().substring(DPSlipTunjanganP1.getFileName().length() - 3, DPSlipTunjanganP1.getFileName().length()).equalsIgnoreCase("pdf")) {
                DokumenPendapatanSlipTunjanganP1.setFileName("tunjangan1.pdf");
                AppUtil.convertBase64ToFileWithOnClick(ActivityDokumenPendapatan.this, DPSlipTunjanganP1.getImg(), binding.ivSliptunjanganP1, DPSlipTunjanganP1.getFileName());
            } else {
                DokumenPendapatanSlipTunjanganP1.setFileName("tunjangan1.png");
                AppUtil.convertBase64ToImage(DPSlipTunjanganP1.getImg(), binding.ivSliptunjanganP1);
            }
        } catch (Exception e) {
            AppUtil.logSecure("error setdata", e.getMessage());
        }
        if (response.body().getData().get("DokumenPendapatanSlipGajiP2") != null) {
            //Set Image Slip Gaji P2
            try {
                DokumenPendapatanSlipGajiP2.setImg(DPSlipGajiP2.getImg());
                DokumenPendapatanSlipTunjanganP2.setImg(DPSlipTunjanganP2.getImg());
                if (DPSlipGajiP2.getFileName().substring(DPSlipGajiP2.getFileName().length() - 3, DPSlipGajiP2.getFileName().length()).equalsIgnoreCase("pdf")) {
                    DokumenPendapatanSlipGajiP2.setFileName("slipgaji2.png");
                    AppUtil.convertBase64ToFileWithOnClick(ActivityDokumenPendapatan.this, DPSlipGajiP2.getImg(), binding.ivSlipgajiP2, DPSlipGajiP2.getFileName());
                } else {
                    DokumenPendapatanSlipGajiP2.setFileName("slipgaji2.pdf");
                    AppUtil.convertBase64ToImage(DPSlipGajiP2.getImg(), binding.ivSlipgajiP2);
                }
                if (DPSlipTunjanganP2.getFileName().substring(DPSlipTunjanganP2.getFileName().length() - 3, DPSlipTunjanganP2.getFileName().length()).equalsIgnoreCase("pdf")) {
                    DokumenPendapatanSlipTunjanganP2.setFileName("tunjangan2.pdf");
                    AppUtil.convertBase64ToFileWithOnClick(ActivityDokumenPendapatan.this, DPSlipTunjanganP2.getImg(), binding.ivSliptunjanganP2, DPSlipTunjanganP2.getFileName());
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
                    AppUtil.convertBase64ToFileWithOnClick(ActivityDokumenPendapatan.this, DPSlipGajiP3.getImg(), binding.ivSlipgajiP3, DPSlipGajiP3.getFileName());
                } else {
                    DokumenPendapatanSlipGajiP3.setFileName("tunjangan3.png");
                    AppUtil.convertBase64ToImage(DPSlipGajiP3.getImg(), binding.ivSlipgajiP3);
                }
                if (DPSlipTunjanganP3.getFileName().substring(DPSlipTunjanganP3.getFileName().length() - 3, DPSlipTunjanganP3.getFileName().length()).equalsIgnoreCase("pdf")) {
                    DokumenPendapatanSlipTunjanganP3.setFileName("tunjangan3.pdf");
                    AppUtil.convertBase64ToFileWithOnClick(ActivityDokumenPendapatan.this, DPSlipTunjanganP3.getImg(), binding.ivSliptunjanganP3, DPSlipTunjanganP3.getFileName());
                } else {
                    DokumenPendapatanSlipTunjanganP3.setFileName("tunjangan3.png");
                    AppUtil.convertBase64ToImage(DPSlipTunjanganP3.getImg(), binding.ivSliptunjanganP3);
                }
            } catch (Exception e) {
                AppUtil.logSecure("error setdata", e.getMessage());
            }
        }
    }

    
    //setDataLogicalDoc
    private void setDataLogicalDoc(Response<ParseResponseAgunan> response){

        try {
            binding.etAkseptasiPendaptan.setText(dp.getAkseptasiPendapatan());
            binding.etPendapatanPensiun.setText(String.valueOf(dp.getSimulasiPendapatanSaatPen()));
            binding.etPeriodeGajiP1.setText(AppUtil.parseTanggalGeneral(dp.getPeriodeGajiP1(), "yyyy-MM-dd", "MM-yyyy"));
            binding.etTglTunjanganP1.setText(AppUtil.parseTanggalGeneral(dp.getPeriodeTunjanganP1(), "yyyy-MM-dd", "MM-yyyy"));
            pDatep1 = AppUtil.parseTanggalGeneral(dp.getPeriodeGajiP1(), "yyyy-MM-dd", "dd-MM-yyyy");
            binding.etGajiBersihP1.setText(String.valueOf(dp.getTotalGajiBersihP1()));
            binding.etTunjanganP1.setText(String.valueOf(dp.getTotalTunjanganBersihP1()));

            if (response.body().getData().get("DokumenPendapatanSlipGajiP2") != null) {
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
            if (response.body().getData().get("CheckNorek").getAsString().equalsIgnoreCase("true")) {
                binding.etVerfikasiRekening.setText("Ya");
            } else {
                binding.etVerfikasiRekening.setText("Tidak");
            }
            if (response.body().getData().get("CheckNorek").getAsString().equalsIgnoreCase("true")) {
                binding.tfNorekTunjangan.setVisibility(View.GONE);
                binding.tfNamaBankTunjangan.setVisibility(View.GONE);
                binding.tfTotalKredit2.setVisibility(View.GONE);
                binding.tfTotalDebit2.setVisibility(View.GONE);
                binding.tfPeriodeAwalWaktu2.setVisibility(View.GONE);
                binding.tfPeriodeAkhirWaktu2.setVisibility(View.GONE);
                binding.norekTunjangan.setVisibility(View.GONE);
            } else if (response.body().getData().get("CheckNorek").getAsString().equalsIgnoreCase("false")) {
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
            checkFileTypeThenSet(ActivityDokumenPendapatan.this,DPKoran.getImg(),binding.ivRekeningKoran1,DPKoran.getFileName());
            DokumenPendapatanKoranBank.setImg(DPKoran.getImg());
            DokumenPendapatanKoranBank.setFileName(DPKoran.getFileName());

        } catch (Exception e) {
            e.printStackTrace();
            AppUtil.logSecure("error setdata", e.getMessage());
        }

        //Set Image Slip Gaji P1 dan Tunjangan P1
        try {

            checkFileTypeThenSet(ActivityDokumenPendapatan.this,DPSlipGajiP1.getImg(),binding.ivSlipgajiP1,DPSlipGajiP1.getFileName());
            DokumenPendapatanSlipGajiP1.setImg(DPSlipGajiP1.getImg());
            DokumenPendapatanSlipGajiP1.setFileName(DPSlipGajiP1.getFileName());

            checkFileTypeThenSet(ActivityDokumenPendapatan.this,DPSlipTunjanganP1.getImg(),binding.ivSliptunjanganP1,DPSlipTunjanganP1.getFileName());
            DokumenPendapatanSlipTunjanganP1.setImg(DPSlipTunjanganP1.getImg());
            DokumenPendapatanSlipTunjanganP1.setFileName(DPSlipTunjanganP1.getFileName());

        } catch (Exception e) {
            AppUtil.logSecure("error setdata", e.getMessage());
        }


        if (response.body().getData().get("DokumenPendapatanSlipGajiP2") != null) {
            //Set Image Slip Gaji P2 dan Tunjangan P2
            try {
                checkFileTypeThenSet(ActivityDokumenPendapatan.this,DPSlipGajiP2.getImg(),binding.ivSlipgajiP2,DPSlipGajiP2.getFileName());
                DokumenPendapatanSlipGajiP2.setImg(DPSlipGajiP2.getImg());
                DokumenPendapatanSlipGajiP2.setFileName(DPSlipGajiP2.getFileName());

                checkFileTypeThenSet(ActivityDokumenPendapatan.this,DPSlipTunjanganP2.getImg(),binding.ivSliptunjanganP2,DPSlipTunjanganP2.getFileName());
                DokumenPendapatanSlipTunjanganP2.setImg(DPSlipTunjanganP2.getImg());
                DokumenPendapatanSlipTunjanganP2.setFileName(DPSlipTunjanganP2.getFileName());
            } catch (Exception e) {
                AppUtil.logSecure("error setdata", e.getMessage());
            }

            //Set Image Slip Gaji P3 dan Tunjangan P3
            try {
                checkFileTypeThenSet(ActivityDokumenPendapatan.this,DPSlipGajiP3.getImg(),binding.ivSlipgajiP3,DPSlipGajiP3.getFileName());
                DokumenPendapatanSlipGajiP3.setImg(DPSlipGajiP3.getImg());
                DokumenPendapatanSlipGajiP3.setFileName(DPSlipGajiP3.getFileName());

                checkFileTypeThenSet(ActivityDokumenPendapatan.this,DPSlipTunjanganP3.getImg(),binding.ivSliptunjanganP3,DPSlipTunjanganP3.getFileName());
                DokumenPendapatanSlipTunjanganP3.setImg(DPSlipTunjanganP3.getImg());
                DokumenPendapatanSlipTunjanganP3.setFileName(DPSlipTunjanganP3.getFileName());
            } catch (Exception e) {
                AppUtil.logSecure("error setdata", e.getMessage());
            }
        }
    }

    private boolean validateData() {
        if (binding.etGajiBersihP1.getText().toString().trim().isEmpty() || binding.etGajiBersihP1.getText().toString().trim().equalsIgnoreCase("0")) {
            binding.tfGajiBersihP1.setError(binding.tfGajiBersihP1.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfGajiBersihP1.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etPeriodeGajiP1.getText().toString().trim().isEmpty() || binding.etPeriodeGajiP1.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfPeriodeGajiP1.setError(binding.tfPeriodeGajiP1.getLabelText() + " " + getString(R.string.title_validate_field), true);
            binding.etGajiBersihP1.requestFocus();
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfPeriodeGajiP1.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etTunjanganP1.getText().toString().trim().isEmpty() || binding.etTunjanganP1.getText().toString().trim().equalsIgnoreCase("")) {
            binding.tfTunjanganP1.setError(binding.tfTunjanganP1.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfTunjanganP1.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etAkseptasiPendaptan.getText().toString().trim().equalsIgnoreCase("Pendapatan Saat Aktif dan Manfaat Pensiun") && binding.etGajiBersihP2.getText().toString().trim().isEmpty()
                || binding.etAkseptasiPendaptan.getText().toString().trim().equalsIgnoreCase("Pendapatan Saat Aktif dan Manfaat Pensiun") && binding.etGajiBersihP2.getText().toString().trim().equalsIgnoreCase("0")) {
            binding.tfGajiBersihP2.setError(binding.tfGajiBersihP2.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfGajiBersihP2.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etAkseptasiPendaptan.getText().toString().trim().equalsIgnoreCase("Pendapatan Saat Aktif dan Manfaat Pensiun") && binding.etPeriodeGajiP2.getText().toString().trim().isEmpty()
                || binding.etAkseptasiPendaptan.getText().toString().trim().equalsIgnoreCase("Pendapatan Saat Aktif dan Manfaat Pensiun") && binding.etPeriodeGajiP2.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfPeriodeGajiP2.setError(binding.tfPeriodeGajiP2.getLabelText() + " " + getString(R.string.title_validate_field), true);
            binding.etGajiBersihP2.requestFocus();
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfPeriodeGajiP2.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etAkseptasiPendaptan.getText().toString().trim().equalsIgnoreCase("Pendapatan Saat Aktif dan Manfaat Pensiun") && binding.etTunjanganP2.getText().toString().trim().isEmpty()
                || binding.etAkseptasiPendaptan.getText().toString().trim().equalsIgnoreCase("Pendapatan Saat Aktif dan Manfaat Pensiun") && binding.etTunjanganP2.getText().toString().trim().equalsIgnoreCase("")) {
            binding.tfTunjanganP2.setError(binding.tfTunjanganP2.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfTunjanganP2.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etAkseptasiPendaptan.getText().toString().trim().equalsIgnoreCase("Pendapatan Saat Aktif dan Manfaat Pensiun") && binding.etGajiBersihP3.getText().toString().trim().isEmpty()
                || binding.etAkseptasiPendaptan.getText().toString().trim().equalsIgnoreCase("Pendapatan Saat Aktif dan Manfaat Pensiun") && binding.etGajiBersihP3.getText().toString().trim().equalsIgnoreCase("0")) {
            binding.tfGajiBersihP3.setError(binding.tfGajiBersihP3.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfGajiBersihP3.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etAkseptasiPendaptan.getText().toString().trim().equalsIgnoreCase("Pendapatan Saat Aktif dan Manfaat Pensiun") && binding.etPeriodeGajiP3.getText().toString().trim().isEmpty()
                || binding.etAkseptasiPendaptan.getText().toString().trim().equalsIgnoreCase("Pendapatan Saat Aktif dan Manfaat Pensiun") && binding.etPeriodeGajiP3.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfPeriodeGajiP3.setError(binding.tfPeriodeGajiP3.getLabelText() + " " + getString(R.string.title_validate_field), true);
            binding.etGajiBersihP3.requestFocus();
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfPeriodeGajiP3.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etAkseptasiPendaptan.getText().toString().trim().equalsIgnoreCase("Pendapatan Saat Aktif dan Manfaat Pensiun") && binding.etTunjanganP3.getText().toString().trim().isEmpty()
                || binding.etAkseptasiPendaptan.getText().toString().trim().equalsIgnoreCase("Pendapatan Saat Aktif dan Manfaat Pensiun") && binding.etTunjanganP3.getText().toString().trim().equalsIgnoreCase("")) {
            binding.tfTunjanganP3.setError(binding.tfTunjanganP3.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfTunjanganP3.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etVerfikasiGajiTunjangan.getText().toString().trim().isEmpty() || binding.etVerfikasiGajiTunjangan.getText().toString().trim().equalsIgnoreCase("Pilih")) {
            binding.tfVerfikasiGajiTunjangan.setError(binding.tfVerfikasiGajiTunjangan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfVerfikasiGajiTunjangan.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etVerfikasiRekening.getText().toString().trim().isEmpty() || binding.etVerfikasiRekening.getText().toString().trim().equalsIgnoreCase("Pilih")) {
            binding.tfVerfikasiRekening.setError(binding.tfVerfikasiRekening.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfVerfikasiRekening.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etNorekGaji.getText().toString().trim().isEmpty() || binding.etNorekGaji.getText().toString().trim().equalsIgnoreCase("")) {
            binding.tfNorekGaji.setError(binding.tfNorekGaji.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfNorekGaji.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etNamaBankGaji.getText().toString().trim().isEmpty() || binding.etNamaBankGaji.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfNamaBankGaji.setError(binding.tfNamaBankGaji.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfNamaBankGaji.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etPeriodeAwalWaktu1.getText().toString().trim().isEmpty() || binding.etPeriodeAwalWaktu1.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfPeriodeAwalWaktu1.setError(binding.tfPeriodeAwalWaktu1.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfPeriodeAwalWaktu1.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etPeriodeAkhirWaktu1.getText().toString().trim().isEmpty() || binding.etPeriodeAkhirWaktu1.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfPeriodeAkhirWaktu1.setError(binding.tfPeriodeAkhirWaktu1.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfPeriodeAkhirWaktu1.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etTotalDebit1.getText().toString().trim().isEmpty() || binding.etTotalDebit1.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfTotalDebit1.setError(binding.tfTotalDebit1.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfTotalDebit1.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etTotalKredit1.getText().toString().trim().isEmpty() || binding.etTotalKredit1.getText().toString().trim().equalsIgnoreCase("0")) {
            binding.tfTotalKredit1.setError(binding.tfTotalKredit1.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfTotalKredit1.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etVerfikasiRekening.getText().toString().trim().equalsIgnoreCase("Tidak") && binding.etNorekTunjangan.getText().toString().trim().isEmpty() || binding.etVerfikasiRekening.getText().toString().trim().equalsIgnoreCase("Tidak") && binding.etNorekTunjangan.getText().toString().trim().equalsIgnoreCase("")) {
            binding.tfNorekTunjangan.setError(binding.tfNorekTunjangan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfNorekTunjangan.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etVerfikasiRekening.getText().toString().trim().equalsIgnoreCase("Tidak") && binding.etNamaBankTunjangan.getText().toString().trim().isEmpty() || binding.etVerfikasiRekening.getText().toString().trim().equalsIgnoreCase("Tidak") && binding.etNamaBankTunjangan.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfNamaBankTunjangan.setError(binding.tfNamaBankTunjangan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfNamaBankTunjangan.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etVerfikasiRekening.getText().toString().trim().equalsIgnoreCase("Tidak") && binding.etPeriodeAwalWaktu2.getText().toString().trim().isEmpty() || binding.etVerfikasiRekening.getText().toString().trim().equalsIgnoreCase("Tidak") && binding.etPeriodeAwalWaktu2.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfPeriodeAwalWaktu2.setError(binding.tfPeriodeAwalWaktu2.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfPeriodeAwalWaktu2.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etVerfikasiRekening.getText().toString().trim().equalsIgnoreCase("Tidak") && binding.etPeriodeAkhirWaktu2.getText().toString().trim().isEmpty() || binding.etVerfikasiRekening.getText().toString().trim().equalsIgnoreCase("Tidak") && binding.etPeriodeAkhirWaktu2.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfPeriodeAkhirWaktu2.setError(binding.tfPeriodeAkhirWaktu2.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfPeriodeAkhirWaktu2.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etVerfikasiRekening.getText().toString().trim().equalsIgnoreCase("Tidak") && binding.etTotalDebit2.getText().toString().trim().isEmpty() || binding.etVerfikasiRekening.getText().toString().trim().equalsIgnoreCase("Tidak") && binding.etTotalDebit2.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfTotalDebit2.setError(binding.tfTotalDebit2.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfTotalDebit2.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etVerfikasiRekening.getText().toString().trim().equalsIgnoreCase("Tidak") && binding.etTotalKredit2.getText().toString().trim().isEmpty() || binding.etVerfikasiRekening.getText().toString().trim().equalsIgnoreCase("Tidak") && binding.etTotalKredit2.getText().toString().trim().equalsIgnoreCase("0")) {
            binding.tfTotalKredit2.setError(binding.tfTotalKredit2.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfTotalKredit2.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else {
            AppUtil.disableEditTexts(binding.getRoot());
            binding.btnRekeningKoran1.setOnClickListener(null);
            binding.btnSlipgajiP1.setOnClickListener(null);
            binding.btnSlipgajiP2.setOnClickListener(null);
            binding.btnSlipgajiP3.setOnClickListener(null);
            binding.btnSliptunjanganP1.setOnClickListener(null);
            binding.btnSliptunjanganP2.setOnClickListener(null);
            binding.btnSliptunjanganP3.setOnClickListener(null);
            binding.ivRekeningKoran1.setOnClickListener(null);
            binding.ivSlipgajiP1.setOnClickListener(null);
            binding.ivSlipgajiP2.setOnClickListener(null);
            binding.ivSlipgajiP3.setOnClickListener(null);
            binding.ivSliptunjanganP1.setOnClickListener(null);
            binding.ivSliptunjanganP2.setOnClickListener(null);
            binding.ivSliptunjanganP3.setOnClickListener(null);
            binding.rlRekeningKoran1.setOnClickListener(null);
            binding.rlSlipgajiP1.setOnClickListener(null);
            binding.rlSlipgajiP2.setOnClickListener(null);
            binding.rlSlipgajiP3.setOnClickListener(null);
            binding.rlSlipgajiP1.setOnClickListener(null);
            binding.rlSliptunjanganP1.setOnClickListener(null);
            binding.rlSliptunjanganP2.setOnClickListener(null);
            binding.rlSliptunjanganP3.setOnClickListener(null);
            binding.btnRekeningKoran1.setVisibility(View.GONE);
            binding.btnSlipgajiP1.setVisibility(View.GONE);
            binding.btnSlipgajiP2.setVisibility(View.GONE);
            binding.btnSlipgajiP3.setVisibility(View.GONE);
            binding.btnSliptunjanganP1.setVisibility(View.GONE);
            binding.btnSliptunjanganP2.setVisibility(View.GONE);
            binding.btnSliptunjanganP3.setVisibility(View.GONE);
//            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), "Field Telah Terisi Penuh");
            sendData();
            return false;
        }
    }

    private void docdata() {
        doc.setAkseptasiPendapatan("asd");
        doc.setCerminanGajiDanTunjD3("asd");
        doc.setNamaBankGaji("Asd");
    }

    private void sendData() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        AppPreferences appPreferences = new AppPreferences(this);
        isiReqDokumenPendapatan docP = new isiReqDokumenPendapatan();
        UpdateDataPendapatan req = new UpdateDataPendapatan();
        doc.setAkseptasiPendapatan(binding.etAkseptasiPendaptan.getText().toString());
        doc.setNamaBankGaji(binding.etNamaBankGaji.getText().toString());
        doc.setNomorRekBankGaji(binding.etNorekGaji.getText().toString());
        doc.setTotalDebitGaji(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTotalDebit1.getText().toString())));
        doc.setTotalKreditGaji(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTotalKredit1.getText().toString())));
        doc.setCerminanGajiDanTunjD3(binding.etVerfikasiGajiTunjangan.getText().toString());
        doc.setPeriodeDateFromGaji(AppUtil.parseTanggalGeneral(binding.etPeriodeAwalWaktu1.getText().toString(), "dd-MM-yyyy", "yyyy-MM-dd"));
        doc.setPeriodeDateToGaji(AppUtil.parseTanggalGeneral(binding.etPeriodeAkhirWaktu1.getText().toString(), "dd-MM-yyyy", "yyyy-MM-dd"));
        if (binding.etVerfikasiRekening.getText().toString().equalsIgnoreCase("Tidak")) {
            doc.setNoRekeningTunjangan(binding.etNorekTunjangan.getText().toString());
            doc.setNamaBankTunjangan(binding.etNamaBankTunjangan.getText().toString());
            doc.setPeriodeDateFromTunjangan(AppUtil.parseTanggalGeneral(binding.etPeriodeAwalWaktu2.getText().toString(), "dd-MM-yyyy", "yyyy-MM-dd"));
            doc.setPeriodeDateToTunjangan(AppUtil.parseTanggalGeneral(binding.etPeriodeAkhirWaktu2.getText().toString(), "dd-MM-yyyy", "yyyy-MM-dd"));
            doc.setTotalDebitTunjangan(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTotalDebit2.getText().toString())));
            doc.setTotalKreditTunjangan(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTotalKredit2.getText().toString())));
        } else {
            doc.setNoRekeningTunjangan(binding.etNorekGaji.getText().toString());
            doc.setNamaBankTunjangan(binding.etNamaBankGaji.getText().toString());
            doc.setPeriodeDateFromTunjangan(AppUtil.parseTanggalGeneral(binding.etPeriodeAwalWaktu1.getText().toString(), "dd-MM-yyyy", "yyyy-MM-dd"));
            doc.setPeriodeDateToTunjangan(AppUtil.parseTanggalGeneral(binding.etPeriodeAkhirWaktu1.getText().toString(), "dd-MM-yyyy", "yyyy-MM-dd"));
            doc.setTotalDebitTunjangan(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTotalDebit1.getText().toString())));
            doc.setTotalKreditTunjangan(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTotalKredit1.getText().toString())));
        }
        doc.setPeriodeGajiP1(AppUtil.parseTanggalGeneral(pDatep1, "dd-MM-yyyy", "yyyy-MM-dd"));
        doc.setPeriodeTunjanganP1(AppUtil.parseTanggalGeneral(pDatep1, "dd-MM-yyyy", "yyyy-MM-dd"));
        if (binding.etAkseptasiPendaptan.getText().toString().trim().equalsIgnoreCase("Pendapatan Saat Aktif dan Manfaat Pensiun")) {
            doc.setPeriodeGajiP2(AppUtil.parseTanggalGeneral(pDatep2, "dd-MM-yyyy", "yyyy-MM-dd"));
            doc.setPeriodeTunjanganP2(AppUtil.parseTanggalGeneral(pDatep2, "dd-MM-yyyy", "yyyy-MM-dd"));
            doc.setPeriodeGajiP3(AppUtil.parseTanggalGeneral(pDatep3, "dd-MM-yyyy", "yyyy-MM-dd"));
            doc.setPeriodeTunjanganP3(AppUtil.parseTanggalGeneral(pDatep3, "dd-MM-yyyy", "yyyy-MM-dd"));
        }

        try{
            doc.setSimulasiPendapatanSaatPen(Double.parseDouble(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etPendapatanPensiun.getText().toString())));
        }
        catch (Exception e){
            doc.setSimulasiPendapatanSaatPen(0d);
            Toast.makeText(this, "Simulasi pendapatan kosong - bypass", Toast.LENGTH_SHORT).show();
        }

        doc.setTotalGajiBersihP1(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etGajiBersihP1.getText().toString())));
        doc.setTotalTunjanganBersihP1(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTunjanganP1.getText().toString())));
        if (binding.etAkseptasiPendaptan.getText().toString().trim().equalsIgnoreCase("Pendapatan Saat Aktif dan Manfaat Pensiun")) {
            doc.setTotalGajiBersihP2(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etGajiBersihP2.getText().toString())));
            doc.setTotalTunjanganBersihP2(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTunjanganP2.getText().toString())));
            doc.setTotalGajiBersihP3(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etGajiBersihP3.getText().toString())));
            doc.setTotalTunjanganBersihP3(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTunjanganP3.getText().toString())));
        }
        req.setApplicationId(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
        req.setUID(String.valueOf(appPreferences.getUid()));
        docP.setDokumenPendapatan(doc);
        docP.setDokumenPendapatanKoranBank(DokumenPendapatanKoranBank);
        docP.setDataJaminanIDCard(DataJaminanIDCard);
        docP.setDokumenPendapatanSlipGajiP1(DokumenPendapatanSlipGajiP1);
        docP.setDokumenPendapatanSlipTunjanganP1(DokumenPendapatanSlipTunjanganP1);
        if (binding.etAkseptasiPendaptan.getText().toString().trim().equalsIgnoreCase("Pendapatan Saat Aktif dan Manfaat Pensiun")) {
            docP.setDokumenPendapatanSlipGajiP2(DokumenPendapatanSlipGajiP2);
            docP.setDokumenPendapatanSlipTunjanganP2(DokumenPendapatanSlipTunjanganP2);
            docP.setDokumenPendapatanSlipGajiP3(DokumenPendapatanSlipGajiP3);
            docP.setDokumenPendapatanSlipTunjanganP3(DokumenPendapatanSlipTunjanganP3);
        }
        req.setData(docP);
        Call<ParseResponseReturn> call = apiClientAdapter.getApiInterface().UpdateDataPendapatan(req);
        call.enqueue(new Callback<ParseResponseReturn>() {
            @Override
            public void onResponse(Call<ParseResponseReturn> call, Response<ParseResponseReturn> response) {
                try {
                    if (response.isSuccessful()) {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            CustomDialog.DialogSuccess(ActivityDokumenPendapatan.this, "Success!", "Update Data Pendapatan sukses", ActivityDokumenPendapatan.this);

                            //update Flagging
                            Realm realm=Realm.getDefaultInstance();
                            FlagAplikasiPojo dataFlag= realm.where(FlagAplikasiPojo.class).equalTo("idAplikasi", idAplikasi).findFirst();
                            if(!realm.isInTransaction()){
                                realm.beginTransaction();
                            }

                            if(dataFlag!=null){
                                dataFlag.setFlagD3Pendapatan(true);
                                realm.insertOrUpdate(dataFlag);
                            }
                            else{
                                dataFlag=new FlagAplikasiPojo();
                                dataFlag.setIdAplikasi(idAplikasi);
                                dataFlag.setFlagD3Pendapatan(true);
                                realm.insertOrUpdate(dataFlag);
                            }
                            realm.close();
                        } else {
                            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseReturn> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

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
        binding.etPendapatanPensiun.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etPendapatanPensiun));
    }

    private void disableEditText() {
        binding.etVerfikasiGajiTunjangan.setFocusable(false);
        binding.tfVerfikasiGajiTunjangan.setFocusable(false);
        binding.etPeriodeAkhirWaktu1.setFocusable(false);
        binding.etPeriodeAkhirWaktu2.setFocusable(false);
        binding.etPeriodeAwalWaktu1.setFocusable(false);
        binding.etPeriodeAwalWaktu2.setFocusable(false);
        binding.etPeriodeGajiP1.setFocusable(false);
        binding.etPeriodeGajiP2.setFocusable(false);
        binding.etPeriodeGajiP3.setFocusable(false);
        binding.etTglTunjanganP1.setFocusable(false);
        binding.etTglTunjanganP2.setFocusable(false);
        binding.etTglTunjanganP3.setFocusable(false);
        binding.tfPeriodeAkhirWaktu1.setFocusable(false);
        binding.tfPeriodeAkhirWaktu2.setFocusable(false);
        binding.tfPeriodeAwalWaktu1.setFocusable(false);
        binding.tfPeriodeAwalWaktu2.setFocusable(false);
        binding.tfPeriodeGajiP1.setFocusable(false);
        binding.tfPeriodeGajiP2.setFocusable(false);
        binding.tfPeriodeGajiP3.setFocusable(false);
        binding.tfTglTunjanganP1.setFocusable(false);
        binding.tfTglTunjanganP2.setFocusable(false);
        binding.tfTglTunjanganP3.setFocusable(false);
        binding.etAkseptasiPendaptan.setFocusable(false);
        binding.etVerfikasiRekening.setFocusable(false);
        binding.tfAkseptasiPendaptan.setFocusable(false);
        binding.tfVerfikasiRekening.setFocusable(false);
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void setParameterDropdown() {
        dataDropdownPendapatan.add(new MGenericModel("1", "Tercermin"));
        dataDropdownPendapatan.add(new MGenericModel("2", "Tercermin tapi nominal lebih rendah"));
        dataDropdownPendapatan.add(new MGenericModel("3", "Tercermin tapi Nominal lebih Tinggi"));
        dataDropdownPendapatan.add(new MGenericModel("4", "Tidak tercermin direkening"));
        dataDropdownPendapatan2.add(new MGenericModel("1", "Pendapatan Saat Aktif dan Manfaat Pensiun"));
        dataDropdownPendapatan2.add(new MGenericModel("2", "Hanya Manfaat Pensiun"));
        dataDropdownPendapatan3.add(new MGenericModel("1", "Ya"));
        dataDropdownPendapatan3.add(new MGenericModel("2", "Tidak"));
    }

    public void onSelect(String title, MGenericModel data) {
        if (title.equalsIgnoreCase(binding.tfVerfikasiGajiTunjangan.getLabelText())) {
            binding.etVerfikasiGajiTunjangan.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfVerfikasiGajiTunjangan.getLabelText())) {
            binding.etVerfikasiGajiTunjangan.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfVerfikasiGajiTunjangan.getLabelText())) {
            binding.etVerfikasiGajiTunjangan.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfVerfikasiGajiTunjangan.getLabelText())) {
            binding.etVerfikasiGajiTunjangan.setText(data.getDESC());
        }
        if (data.getDESC().equalsIgnoreCase("Ya")) {
            binding.tfNorekTunjangan.setVisibility(View.GONE);
            binding.tfNamaBankTunjangan.setVisibility(View.GONE);
            binding.tfTotalKredit2.setVisibility(View.GONE);
            binding.tfTotalDebit2.setVisibility(View.GONE);
            binding.tfPeriodeAwalWaktu2.setVisibility(View.GONE);
            binding.tfPeriodeAkhirWaktu2.setVisibility(View.GONE);
            binding.norekTunjangan.setVisibility(View.GONE);
            binding.etVerfikasiRekening.setText(data.getDESC());
        } else if (data.getDESC().equalsIgnoreCase("Tidak")) {
            binding.tfNorekTunjangan.setVisibility(View.VISIBLE);
            binding.tfNamaBankTunjangan.setVisibility(View.VISIBLE);
            binding.tfTotalKredit2.setVisibility(View.VISIBLE);
            binding.tfTotalDebit2.setVisibility(View.VISIBLE);
            binding.tfPeriodeAwalWaktu2.setVisibility(View.VISIBLE);
            binding.tfPeriodeAkhirWaktu2.setVisibility(View.VISIBLE);
            binding.norekTunjangan.setVisibility(View.VISIBLE);
            binding.etVerfikasiRekening.setText(data.getDESC());
        }
        if (data.getDESC().equalsIgnoreCase("Pendapatan Saat Aktif dan Manfaat Pensiun")) {
            binding.etPeriodeGajiP1.setText("");
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
            binding.etAkseptasiPendaptan.setText(data.getDESC());
        } else if (data.getDESC().equalsIgnoreCase("Hanya Manfaat Pensiun")) {
            binding.etPeriodeGajiP1.setText("");
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
            binding.etAkseptasiPendaptan.setText(data.getDESC());
        }
    }

    private void endIconClick() {
        binding.tfVerfikasiGajiTunjangan.getEndIconImageButton().setOnClickListener(v -> DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfVerfikasiGajiTunjangan.getLabelText(), dataDropdownPendapatan, ActivityDokumenPendapatan.this));
        binding.tfAkseptasiPendaptan.getEndIconImageButton().setOnClickListener(v -> DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfAkseptasiPendaptan.getLabelText(), dataDropdownPendapatan2, ActivityDokumenPendapatan.this));
        binding.tfVerfikasiRekening.getEndIconImageButton().setOnClickListener(v -> DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfVerfikasiRekening.getLabelText(), dataDropdownPendapatan3, ActivityDokumenPendapatan.this));
        binding.tfPeriodeAkhirWaktu1.getEndIconImageButton().setOnClickListener(v -> dpSKCalendar(v, binding.etPeriodeAkhirWaktu1, binding.tfPeriodeAkhirWaktu1.getLabelText(), dateClient2));
        binding.tfPeriodeAkhirWaktu2.getEndIconImageButton().setOnClickListener(v -> dpSKCalendar(v, binding.etPeriodeAkhirWaktu2, binding.tfPeriodeAkhirWaktu2.getLabelText(), dateClient2));
        binding.tfPeriodeAwalWaktu1.getEndIconImageButton().setOnClickListener(v -> dpSKCalendar(v, binding.etPeriodeAwalWaktu1, binding.tfPeriodeAwalWaktu1.getLabelText(), dateClient2));
        binding.tfPeriodeAwalWaktu2.getEndIconImageButton().setOnClickListener(v -> dpSKCalendar(v, binding.etPeriodeAwalWaktu2, binding.tfPeriodeAwalWaktu2.getLabelText(), dateClient2));
        binding.tfPeriodeGajiP1.getEndIconImageButton().setOnClickListener(v -> dpSKCalendar(v, binding.etPeriodeGajiP1, binding.tfPeriodeGajiP1.getLabelText(), dateClient));
    }

    private void onclickSelectDialog() {
        binding.etAkseptasiPendaptan.setOnClickListener(this);
        binding.tfAkseptasiPendaptan.setOnClickListener(this);
        binding.tfVerfikasiGajiTunjangan.setOnClickListener(this);
        binding.etVerfikasiGajiTunjangan.setOnClickListener(this);
        binding.btnSend.setOnClickListener(this);
        binding.etPeriodeAkhirWaktu1.setOnClickListener(this);
        binding.etPeriodeAkhirWaktu2.setOnClickListener(this);
        binding.etPeriodeAwalWaktu1.setOnClickListener(this);
        binding.etPeriodeAwalWaktu2.setOnClickListener(this);
        binding.etPeriodeGajiP1.setOnClickListener(this);
        binding.tfPeriodeAkhirWaktu1.setOnClickListener(this);
        binding.tfPeriodeAkhirWaktu2.setOnClickListener(this);
        binding.tfPeriodeAwalWaktu1.setOnClickListener(this);
        binding.tfPeriodeAwalWaktu2.setOnClickListener(this);
        binding.tfPeriodeGajiP1.setOnClickListener(this);
        binding.btnRekeningKoran1.setOnClickListener(this);
        binding.btnSlipgajiP1.setOnClickListener(this);
        binding.btnSlipgajiP2.setOnClickListener(this);
        binding.btnSlipgajiP3.setOnClickListener(this);
        binding.btnSliptunjanganP1.setOnClickListener(this);
        binding.btnSliptunjanganP2.setOnClickListener(this);
        binding.btnSliptunjanganP3.setOnClickListener(this);
        binding.ivRekeningKoran1.setOnClickListener(this);
        binding.ivSlipgajiP1.setOnClickListener(this);
        binding.ivSlipgajiP2.setOnClickListener(this);
        binding.ivSlipgajiP3.setOnClickListener(this);
        binding.ivSliptunjanganP1.setOnClickListener(this);
        binding.ivSliptunjanganP2.setOnClickListener(this);
        binding.ivSliptunjanganP3.setOnClickListener(this);
        binding.rlRekeningKoran1.setOnClickListener(this);
        binding.rlSlipgajiP1.setOnClickListener(this);
        binding.rlSlipgajiP2.setOnClickListener(this);
        binding.rlSlipgajiP3.setOnClickListener(this);
        binding.rlSlipgajiP1.setOnClickListener(this);
        binding.rlSliptunjanganP1.setOnClickListener(this);
        binding.rlSliptunjanganP2.setOnClickListener(this);
        binding.rlSliptunjanganP3.setOnClickListener(this);
        binding.etVerfikasiRekening.setOnClickListener(this);
        binding.tfVerfikasiRekening.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                validateData();
                break;
            case R.id.btn_rekening_koran1:
            case R.id.rl_rekening_koran1:
            case R.id.iv_rekening_koran1:
                BSUploadFile.displayWithTitle(ActivityDokumenPendapatan.this.getSupportFragmentManager(), this, "");
                clicker = "koran";
                break;
            case R.id.btn_slipgaji_p1:
            case R.id.rl_slipgaji_p1:
            case R.id.iv_slipgaji_p1:
                BSUploadFile.displayWithTitle(ActivityDokumenPendapatan.this.getSupportFragmentManager(), this, "");
                clicker = "slipgaji1";
                break;
            case R.id.btn_slipgaji_p2:
            case R.id.rl_slipgaji_p2:
            case R.id.iv_slipgaji_p2:
                BSUploadFile.displayWithTitle(ActivityDokumenPendapatan.this.getSupportFragmentManager(), this, "");
                clicker = "slipgaji2";
                break;
            case R.id.btn_slipgaji_p3:
            case R.id.rl_slipgaji_p3:
            case R.id.iv_slipgaji_p3:
                BSUploadFile.displayWithTitle(ActivityDokumenPendapatan.this.getSupportFragmentManager(), this, "");
                clicker = "slipgaji3";
                break;
            case R.id.btn_sliptunjangan_p1:
            case R.id.rl_sliptunjangan_p1:
            case R.id.iv_sliptunjangan_p1:
                BSUploadFile.displayWithTitle(ActivityDokumenPendapatan.this.getSupportFragmentManager(), this, "");
                clicker = "tunjangan1";
                break;
            case R.id.rl_sliptunjangan_p2:
            case R.id.btn_sliptunjangan_p2:
            case R.id.iv_sliptunjangan_p2:
                BSUploadFile.displayWithTitle(ActivityDokumenPendapatan.this.getSupportFragmentManager(), this, "");
                clicker = "tunjangan2";
                break;
            case R.id.rl_sliptunjangan_p3:
            case R.id.iv_sliptunjangan_p3:
            case R.id.btn_sliptunjangan_p3:
                BSUploadFile.displayWithTitle(ActivityDokumenPendapatan.this.getSupportFragmentManager(), this, "");
                clicker = "tunjangan3";
                break;
            case R.id.tf_periode_akhir_waktu1:
            case R.id.et_periode_akhir_waktu1:
                dpSKCalendar(v, binding.etPeriodeAkhirWaktu1, binding.tfPeriodeAkhirWaktu1.getLabelText(), dateClient2);
                break;
            case R.id.tf_periode_akhir_waktu2:
            case R.id.et_periode_akhir_waktu2:
                dpSKCalendar(v, binding.etPeriodeAkhirWaktu2, binding.tfPeriodeAkhirWaktu2.getLabelText(), dateClient2);
                break;
            case R.id.tf_periode_awal_waktu1:
            case R.id.et_periode_awal_waktu1:
                dpSKCalendar(v, binding.etPeriodeAwalWaktu1, binding.tfPeriodeAwalWaktu1.getLabelText(), dateClient2);
                break;
            case R.id.tf_periode_awal_waktu2:
            case R.id.et_periode_awal_waktu2:
                dpSKCalendar(v, binding.etPeriodeAwalWaktu2, binding.tfPeriodeAwalWaktu2.getLabelText(), dateClient2);
                break;
            case R.id.tf_periode_gaji_p1:
            case R.id.et_periode_gaji_p1:
                dpSKCalendar(v, binding.etPeriodeGajiP1, binding.tfPeriodeGajiP1.getLabelText(), dateClient);
                break;
            case R.id.et_verfikasi_gaji_tunjangan:
            case R.id.tf_verfikasi_gaji_tunjangan:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfVerfikasiGajiTunjangan.getLabelText(), dataDropdownPendapatan, ActivityDokumenPendapatan.this);
                break;
            case R.id.et_akseptasi_pendaptan:
            case R.id.tf_akseptasi_pendaptan:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfAkseptasiPendaptan.getLabelText(), dataDropdownPendapatan2, ActivityDokumenPendapatan.this);
                break;
            case R.id.et_verfikasi_rekening:
            case R.id.tf_verfikasi_rekening:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfVerfikasiRekening.getLabelText(), dataDropdownPendapatan3, ActivityDokumenPendapatan.this);
                break;
        }
    }

    public String s;

    private void dpSKCalendar(View v, EditText et, String title, SimpleDateFormat dateT) {
        calLahir = Calendar.getInstance();
        @SuppressLint("NonConstantResourceId") DatePickerDialog.OnDateSetListener ls_tanggalLahirPasangan = (view, year, month, dayOfMonth) -> {
            calLahir.set(Calendar.YEAR, year);
            calLahir.set(Calendar.MONTH, month);
            calLahir.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String calLahirString = dateT.format(calLahir.getTime());
            if (title.equalsIgnoreCase(binding.tfPeriodeGajiP1.getLabelText())) {

                binding.etPeriodeGajiP1.setText(calLahirString);
                binding.etTglTunjanganP1.setText(calLahirString);
                pDatep1 = dateClient2.format(calLahir.getTime());
                formatOutgoing.setTimeZone(tz);
                s = formatOutgoing.format(calLahir.getTime());
                binding.tp1.setText("Slip Gaji " + s);

                calLahir.add(Calendar.MONTH, -1);
                String p2 = dateT.format(calLahir.getTime());
                binding.etPeriodeGajiP2.setText(p2);
                binding.etTglTunjanganP2.setText(p2);
                pDatep2 = dateClient2.format(calLahir.getTime());
                s = formatOutgoing.format(calLahir.getTime());
                binding.tp2.setText("Slip Gaji " + s);

                calLahir.add(Calendar.MONTH, -1);
                String p3 = dateT.format(calLahir.getTime());
                binding.etPeriodeGajiP3.setText(p3);
                binding.etTglTunjanganP3.setText(p3);
                pDatep3 = dateClient2.format(calLahir.getTime());
                s = formatOutgoing.format(calLahir.getTime());
                binding.tp3.setText("Slip Gaji " + s);
            } else {
                et.setText(calLahirString);
            }

        };

        dpSK = new DatePickerDialog(ActivityDokumenPendapatan.this, R.style.AppTheme_TimePickerTheme, ls_tanggalLahirPasangan, calLahir.get(Calendar.YEAR),
                calLahir.get(Calendar.MONTH), calLahir.get(Calendar.DAY_OF_MONTH));
        dpSK.getDatePicker().setMaxDate(calLahir.getTimeInMillis());
        dpSK.show();
    }


    @Override
    public void onSelectMenuCamera(String idMenu) {
        switch (idMenu) {
            case "Take Photo":
                tipeFile = "png";
                if (clicker.equalsIgnoreCase("koran")) {
                    openCamera(UPLOAD_KORAN, "koran");
                } else if (clicker.equalsIgnoreCase("slipgaji1")) {
                    openCamera(UPLOAD_SLIPGAJI1, "slipgaji1");
                } else if (clicker.equalsIgnoreCase("slipgaji2")) {
                    openCamera(UPLOAD_SLIPGAJI2, "slipgaji2");
                } else if (clicker.equalsIgnoreCase("slipgaji3")) {
                    openCamera(UPLOAD_SLIPGAJI3, "slipgaji3");
                } else if (clicker.equalsIgnoreCase("tunjangan1")) {
                    openCamera(UPLOAD_SLIPTUNJANGAN1, "tunjangan1");
                } else if (clicker.equalsIgnoreCase("tunjangan2")) {
                    openCamera(UPLOAD_SLIPTUNJANGAN2, "tunjangan2");
                } else if (clicker.equalsIgnoreCase("tunjangan3")) {
                    openCamera(UPLOAD_SLIPTUNJANGAN3, "tunjangan3");
                }
                break;
            case "Pick Photo":
                tipeFile = "png";
                if (clicker.equalsIgnoreCase("koran")) {
                    openGalery(UPLOAD_KORAN);
                } else if (clicker.equalsIgnoreCase("slipgaji1")) {
                    openGalery(UPLOAD_SLIPGAJI1);
                } else if (clicker.equalsIgnoreCase("slipgaji2")) {
                    openGalery(UPLOAD_SLIPGAJI2);
                } else if (clicker.equalsIgnoreCase("slipgaji3")) {
                    openGalery(UPLOAD_SLIPGAJI3);
                } else if (clicker.equalsIgnoreCase("tunjangan1")) {
                    openGalery(UPLOAD_SLIPTUNJANGAN1);
                } else if (clicker.equalsIgnoreCase("tunjangan2")) {
                    openGalery(UPLOAD_SLIPTUNJANGAN2);
                } else if (clicker.equalsIgnoreCase("tunjangan3")) {
                    openGalery(UPLOAD_SLIPTUNJANGAN3);
                }

                break;
            case "Pick File":
                tipeFile = "pdf";
                if (clicker.equalsIgnoreCase("koran")) {
                    openFile(UPLOAD_KORAN);
                } else if (clicker.equalsIgnoreCase("slipgaji1")) {
                    openFile(UPLOAD_SLIPGAJI1);
                } else if (clicker.equalsIgnoreCase("slipgaji2")) {
                    openFile(UPLOAD_SLIPGAJI2);
                } else if (clicker.equalsIgnoreCase("slipgaji3")) {
                    openFile(UPLOAD_SLIPGAJI3);
                } else if (clicker.equalsIgnoreCase("tunjangan1")) {
                    openFile(UPLOAD_SLIPTUNJANGAN1);
                } else if (clicker.equalsIgnoreCase("tunjangan2")) {
                    openFile(UPLOAD_SLIPTUNJANGAN2);
                } else if (clicker.equalsIgnoreCase("tunjangan3")) {
                    openFile(UPLOAD_SLIPTUNJANGAN3);
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

    
    //legacy activity result
    
//    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
//        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
//        switch (requestCode) {
//            case UPLOAD_KORAN:
//                setDataImage(uri_koran, bitmap_koran, binding.ivRekeningKoran1, imageReturnedIntent, "koran");
//                break;
//            case UPLOAD_SLIPGAJI1:
//                setDataImage(uri_slipgaji1, bitmap_slipgaji1, binding.ivSlipgajiP1, imageReturnedIntent, "slipgaji1");
//                break;
//            case UPLOAD_SLIPGAJI2:
//                setDataImage(uri_slipgaji2, bitmap_slipgaji2, binding.ivSlipgajiP2, imageReturnedIntent, "slipgaji2");
//                break;
//            case UPLOAD_SLIPGAJI3:
//                setDataImage(uri_slipgaji3, bitmap_slipgaji3, binding.ivSlipgajiP3, imageReturnedIntent, "slipgaji3");
//                break;
//            case UPLOAD_SLIPTUNJANGAN1:
//                setDataImage(uri_sliptunjangan1, bitmap_sliptunjangan1, binding.ivSliptunjanganP1, imageReturnedIntent, "tunjangan1");
//                break;
//            case UPLOAD_SLIPTUNJANGAN2:
//                setDataImage(uri_sliptunjangan2, bitmap_sliptunjangan2, binding.ivSliptunjanganP2, imageReturnedIntent, "tunjangan2");
//                break;
//            case UPLOAD_SLIPTUNJANGAN3:
//                setDataImage(uri_sliptunjangan3, bitmap_sliptunjangan3, binding.ivSliptunjanganP3, imageReturnedIntent, "tunjangan3");
//                break;
//        }
//    }
    
    
    //logical doc

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case UPLOAD_SLIPGAJI1:
                setDataImage(binding.ivSlipgajiP1,data,"dokSlipGajiP1D3",UPLOAD_SLIPGAJI1);
                checkFileTypeThenUpload(fileNameSlipGajiP1,"_SlipGajiP1D3",binding.ivSlipgajiP1,valGajiP1,UPLOAD_SLIPGAJI1);
                break;
            case UPLOAD_SLIPGAJI2:
                setDataImage(binding.ivSlipgajiP2,data,"dokGajiP2D3",UPLOAD_SLIPGAJI2);
                checkFileTypeThenUpload(fileNameSlipGajiP2,"_gajiP2D3",binding.ivSlipgajiP2,valGajiP2,UPLOAD_SLIPGAJI2);
                break;
            case UPLOAD_SLIPGAJI3:
                setDataImage(binding.ivSlipgajiP3,data,"dokGajiP3D3",UPLOAD_SLIPGAJI3);
                checkFileTypeThenUpload(fileNameSlipGajiP3,"_gajiP3D3",binding.ivSlipgajiP3,valGajiP3,UPLOAD_SLIPGAJI3);
                break;
            case UPLOAD_SLIPTUNJANGAN1:
                setDataImage(binding.ivSliptunjanganP1,data,"dokTunjanganP1D3",UPLOAD_SLIPTUNJANGAN1);
                checkFileTypeThenUpload(fileNameSlipTunjanganP1,"_tunjanganP1D3",binding.ivSliptunjanganP1,valTunjanganP1,UPLOAD_SLIPTUNJANGAN1);
                break;
            case UPLOAD_SLIPTUNJANGAN2:
                setDataImage(binding.ivSliptunjanganP2,data,"dokTunjanganP2D3",UPLOAD_SLIPTUNJANGAN2);
                checkFileTypeThenUpload(fileNameSlipTunjanganP2,"_tunjanganP2D3",binding.ivSliptunjanganP2,valTunjanganP2,UPLOAD_SLIPTUNJANGAN2);
                break;
            case UPLOAD_SLIPTUNJANGAN3:
                setDataImage(binding.ivSliptunjanganP3,data,"dokTunjanganP3D3",UPLOAD_SLIPTUNJANGAN3);
                checkFileTypeThenUpload(fileNameSlipTunjanganP3,"_tunjanganP3D3",binding.ivSliptunjanganP3,valTunjanganP3,UPLOAD_SLIPTUNJANGAN3);
                break;
            case UPLOAD_KORAN:
                setDataImage(binding.ivRekeningKoran1,data,"dokKoranD3",UPLOAD_KORAN);
                checkFileTypeThenUpload(fileNameKoran,"_KoranD3",binding.ivRekeningKoran1,valKoran,UPLOAD_KORAN);
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
                if (clicker.equalsIgnoreCase("koran")) {
                    bitmap_koran = bitmap;
                    DokumenPendapatanKoranBank.setImg(AppUtil.encodeImageTobase64(bitmap));
                    DokumenPendapatanKoranBank.setFileName("koran.png");
                } else if (clicker.equalsIgnoreCase("slipgaji1")) {
                    bitmap_slipgaji1 = bitmap;
                    DokumenPendapatanSlipGajiP1.setImg(AppUtil.encodeImageTobase64(bitmap));
                    DokumenPendapatanSlipGajiP1.setFileName("slipgaji1.png");
                } else if (clicker.equalsIgnoreCase("slipgaji2")) {
                    bitmap_slipgaji2 = bitmap;
                    DokumenPendapatanSlipGajiP2.setImg(AppUtil.encodeImageTobase64(bitmap));
                    DokumenPendapatanSlipGajiP2.setFileName("slipgaji2.png");
                } else if (clicker.equalsIgnoreCase("slipgaji3")) {
                    bitmap_slipgaji3 = bitmap;
                    DokumenPendapatanSlipGajiP3.setImg(AppUtil.encodeImageTobase64(bitmap));
                    DokumenPendapatanSlipGajiP3.setFileName("slipgaji3.png");
                } else if (clicker.equalsIgnoreCase("tunjangan1")) {
                    bitmap_sliptunjangan1 = bitmap;
                    DokumenPendapatanSlipTunjanganP1.setImg(AppUtil.encodeImageTobase64(bitmap));
                    DokumenPendapatanSlipTunjanganP1.setFileName("tunjangan1.png");
                } else if (clicker.equalsIgnoreCase("tunjangan2")) {
                    bitmap_sliptunjangan2 = bitmap;
                    DokumenPendapatanSlipTunjanganP2.setImg(AppUtil.encodeImageTobase64(bitmap));
                    DokumenPendapatanSlipTunjanganP2.setFileName("tunjangan2.png");
                } else if (clicker.equalsIgnoreCase("tunjangan3")) {
                    bitmap_sliptunjangan3 = bitmap;
                    DokumenPendapatanSlipTunjanganP3.setImg(AppUtil.encodeImageTobase64(bitmap));
                    DokumenPendapatanSlipTunjanganP3.setFileName("tunjangan3.png");
                }
            } catch (Exception e) {
                e.printStackTrace();
                iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));
                if (clicker.equalsIgnoreCase("koran")) {
                    Uri uriPdf = data.getData();
                    valKoran = AppUtil.encodeFileToBase64(this, uriPdf);
                    DokumenPendapatanKoranBank.setImg(valKoran);
                    DokumenPendapatanKoranBank.setFileName("koran.pdf");
                } else if (clicker.equalsIgnoreCase("slipgaji1")) {
                    Uri uriPdf = data.getData();
                    valGajiP1 = AppUtil.encodeFileToBase64(this, uriPdf);
                    DokumenPendapatanSlipGajiP1.setImg(valGajiP1);
                    DokumenPendapatanSlipGajiP1.setFileName("slipgaji1.pdf");
                } else if (clicker.equalsIgnoreCase("slipgaji2")) {
                    Uri uriPdf = data.getData();
                    valGajiP2 = AppUtil.encodeFileToBase64(this, uriPdf);
                    DokumenPendapatanSlipGajiP2.setImg(valGajiP2);
                    DokumenPendapatanSlipGajiP2.setFileName("slipgaji2.pdf");
                } else if (clicker.equalsIgnoreCase("slipgaji3")) {
                    Uri uriPdf = data.getData();
                    valGajiP3 = AppUtil.encodeFileToBase64(this, uriPdf);
                    DokumenPendapatanSlipGajiP3.setImg(valGajiP3);
                    DokumenPendapatanSlipGajiP3.setFileName("slipgaji3.pdf");
                } else if (clicker.equalsIgnoreCase("tunjangan1")) {
                    Uri uriPdf = data.getData();
                    valTunjanganP1 = AppUtil.encodeFileToBase64(this, uriPdf);
                    DokumenPendapatanSlipTunjanganP1.setImg(valTunjanganP1);
                    DokumenPendapatanSlipTunjanganP1.setFileName("tunjangan1.pdf");
                } else if (clicker.equalsIgnoreCase("tunjangan2")) {
                    Uri uriPdf = data.getData();
                    valTunjanganP2 = AppUtil.encodeFileToBase64(this, uriPdf);
                    DokumenPendapatanSlipTunjanganP2.setImg(valTunjanganP2);
                    DokumenPendapatanSlipTunjanganP2.setFileName("tunjangan2.pdf");
                } else if (clicker.equalsIgnoreCase("tunjangan3")) {
                    Uri uriPdf = data.getData();
                    valTunjanganP3 = AppUtil.encodeFileToBase64(this, uriPdf);
                    DokumenPendapatanSlipTunjanganP3.setImg(valTunjanganP3);
                    DokumenPendapatanSlipTunjanganP3.setFileName("tunjangan3.pdf");
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

                bitmap = MediaStore.Images.Media.getBitmap(ActivityDokumenPendapatan.this.getContentResolver(), uri);
                bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                bitmap = AppUtil.rotateImageIfRequired(ActivityDokumenPendapatan.this, bitmap, uri);
                iv.setImageBitmap(bitmap);


            } catch (NullPointerException e) {
                e.printStackTrace();

                try{
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));

                    if(KODE_UPLOAD==UPLOAD_SLIPGAJI1){
                        Uri uriPdf = data.getData();
                        valGajiP1= AppUtil.encodeFileToBase64(ActivityDokumenPendapatan.this,uriPdf);

                    }
                    else if(KODE_UPLOAD==UPLOAD_SLIPGAJI2){
                        Uri uriPdf = data.getData();
                        valGajiP2= AppUtil.encodeFileToBase64(ActivityDokumenPendapatan.this,uriPdf);
                    }
                    else if(KODE_UPLOAD==UPLOAD_SLIPGAJI3){
                        Uri uriPdf = data.getData();
                        valGajiP3= AppUtil.encodeFileToBase64(ActivityDokumenPendapatan.this,uriPdf);
                    }
                    else if(KODE_UPLOAD==UPLOAD_SLIPTUNJANGAN1){
                        Uri uriPdf = data.getData();
                        valTunjanganP1= AppUtil.encodeFileToBase64(ActivityDokumenPendapatan.this,uriPdf);
                    }
                    else if(KODE_UPLOAD==UPLOAD_SLIPTUNJANGAN2){
                        Uri uriPdf = data.getData();
                        valTunjanganP2= AppUtil.encodeFileToBase64(ActivityDokumenPendapatan.this,uriPdf);
                    }
                    else if(KODE_UPLOAD==UPLOAD_SLIPTUNJANGAN3){
                        Uri uriPdf = data.getData();
                        valTunjanganP3= AppUtil.encodeFileToBase64(ActivityDokumenPendapatan.this,uriPdf);
                    }
                    else if(KODE_UPLOAD==UPLOAD_KORAN){
                        Uri uriPdf = data.getData();
                        valKoran= AppUtil.encodeFileToBase64(ActivityDokumenPendapatan.this,uriPdf);
                    }
                }
                catch (Exception e2){
                    e2.printStackTrace();
                }
            }
            catch (FileNotFoundException e2){
                e2.printStackTrace();
            }
            catch (Exception e3){
                e3.printStackTrace();
            }
        }
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

                    if (uploadCode == UPLOAD_SLIPGAJI1) {
                        idFileSlipGajiP1 = response.body().getId();
                        DokumenPendapatanSlipGajiP1.setImg(idFileSlipGajiP1);
                        DokumenPendapatanSlipGajiP1.setFileName(fileName);
                    }
                    else if (uploadCode == UPLOAD_SLIPGAJI2) {
                        idFileSlipGajiP2 = response.body().getId();
                        DokumenPendapatanSlipGajiP2.setImg(idFileSlipGajiP2);
                        DokumenPendapatanSlipGajiP2.setFileName(fileName);
                    }
                    else if (uploadCode == UPLOAD_SLIPGAJI3) {
                        idFileSlipGajiP3 = response.body().getId();
                        DokumenPendapatanSlipGajiP3.setImg(idFileSlipGajiP3);
                        DokumenPendapatanSlipGajiP3.setFileName(fileName);
                    }
                    else if (uploadCode == UPLOAD_SLIPTUNJANGAN1) {
                        idFileSlipTunjanganP1 = response.body().getId();
                        DokumenPendapatanSlipTunjanganP1.setImg(idFileSlipTunjanganP1);
                        DokumenPendapatanSlipTunjanganP1.setFileName(fileName);
                    }
                    else if (uploadCode == UPLOAD_SLIPTUNJANGAN2) {
                        idFileSlipTunjanganP2 = response.body().getId();
                        DokumenPendapatanSlipTunjanganP2.setImg(idFileSlipTunjanganP2);
                        DokumenPendapatanSlipTunjanganP2.setFileName(fileName);
                    }
                    else if (uploadCode == UPLOAD_SLIPTUNJANGAN3) {
                        idFileSlipTunjanganP3 = response.body().getId();
                        DokumenPendapatanSlipTunjanganP3.setImg(idFileSlipTunjanganP3);
                        DokumenPendapatanSlipTunjanganP3.setFileName(fileName);
                    }
                    else if (uploadCode == UPLOAD_KORAN) {
                        idFileKoran = response.body().getId();
                        DokumenPendapatanKoranBank.setImg(idFileKoran);
                        DokumenPendapatanKoranBank.setFileName(fileName);
                    }
                    AppUtil.notifsuccess(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), "Upload Berhasil");
//                    sudahUpload=true;
                } else {
                    AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseFile> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });

    }

    private void checkFileTypeThenUpload(String filename, String fileNameDoc, ImageView imageView,String val_base64, int uploadCode){
        if (tipeFile.equalsIgnoreCase("pdf")) {
            filename = idAplikasi + fileNameDoc+".pdf";
            uploadFile(val_base64, filename, uploadCode);
        } else {
            imageView.invalidate();
            RoundedDrawable drawableDoc = (RoundedDrawable) imageView.getDrawable();
            Bitmap bitmapDoc = drawableDoc.getSourceBitmap();
            filename = idAplikasi + fileNameDoc+".png";
            uploadFile(AppUtil.encodeImageTobase64(bitmapDoc), filename, uploadCode);
        }
    }

    private void checkFileTypeThenSet(Context context, String idDok, ImageView imageView, String fileName){

        if(fileName.substring(fileName.length()-3,fileName.length()).equalsIgnoreCase("pdf")){
            loadFileJson(idDok,imageView);
        }
        else{
            AppUtil.setImageGlide(context,idDok,imageView);
        }
    }

    public void loadFileJson(String idFoto,ImageView imageView) {
        ApiClientAdapter apiClientAdapter=new ApiClientAdapter(ActivityDokumenPendapatan.this);
        Call<ParseResponseLogicalDoc> call = apiClientAdapter.getApiInterface().getFileJson(idFoto);
        call.enqueue(new Callback<ParseResponseLogicalDoc>() {
            @Override
            public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
//                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getBinaryData()!=null){
                        AppUtil.convertBase64ToFileWithOnClick(ActivityDokumenPendapatan.this,response.body().getBinaryData(),imageView,response.body().getFileName());
                    }
                    else{
                        AppUtil.notiferror(ActivityDokumenPendapatan.this,findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                    }


                }
                else{
                    AppUtil.notiferror(ActivityDokumenPendapatan.this,findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityDokumenPendapatan.this,findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
                t.printStackTrace();
            }
        });

    }

    @Override
    public void success(boolean val) {
        finish();
    }

    @Override
    public void confirm(boolean val) {

    }
}
