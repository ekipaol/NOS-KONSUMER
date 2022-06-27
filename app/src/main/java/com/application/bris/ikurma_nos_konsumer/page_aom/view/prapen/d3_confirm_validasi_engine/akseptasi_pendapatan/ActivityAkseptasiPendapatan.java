package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.akseptasi_pendapatan;

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
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseFile;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseLogicalDoc;
import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.model.request.foto.ReqUploadFile;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDocument;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqHitungAkseptasiPendapatan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqInquery;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUpdateAkseptasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.SubAkseptasiPendapatan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateAkseptasiPendapatan;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseDataGajiTunjangan;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparsePendapatanTunjangan;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparsePicklist;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityAkseptasiPendapatanBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAkseptasiPendapatan extends AppCompatActivity implements View.OnClickListener, AkseptasiPendapatanAdapter.CallbackInterface, GenericListenerOnSelect, CameraListener {

    AkseptasiPendapatanAdapter akseptasiPendapatanAdapter;
    ActivityAkseptasiPendapatanBinding binding;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private Calendar calLahir;
    private String idAplikasi;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("MM-yyyy", Locale.US);
    public static SimpleDateFormat dateClient2 = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    public static SimpleDateFormat dateClient3 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    Integer maxlist = 8, list = 0, photodokumen = 1, hitung = 0;
    MparsePicklist picklistModel;
    String tipeFile, Valdokumen;
    List<SubAkseptasiPendapatan> data1 = new ArrayList<>();
    List<MGenericModel> komponen = new ArrayList<>(), treatment = new ArrayList<>(), akseptasi = new ArrayList<>(), tercermin = new ArrayList<>(), gajitunjangan = new ArrayList<>();
    //Model
    MparsePendapatanTunjangan mparsePendapatanTunjangan;
    MparseDataGajiTunjangan mparseDataGajiTunjangan;
    ReqDocument reqDocument;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClientAdapter = new ApiClientAdapter(this);
        binding = ActivityAkseptasiPendapatanBinding.inflate(getLayoutInflater());
        appPreferences = new AppPreferences(this);
        View view = binding.getRoot();
        setContentView(view);
        if (getIntent().hasExtra("idAplikasi")) {
            idAplikasi = getIntent().getStringExtra("idAplikasi");
        }
//        idAplikasi = "131";
        backgroundStatusBar();
        onclickSelectDialog();
        isidropdown();
        initdata();
        disabledEdit();
        endIconClick();
        onchangeListener();
        AppUtil.toolbarRegular(this, "Akseptasi Pendapatan");
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void onchangeListener() {
        binding.etTotalPendapatanSetelahAkseptasi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalPendapatanSetelahAkseptasi));
        binding.etPendapatanSaatPensiun.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etPendapatanSaatPensiun));
        binding.etTotalPendapatanUntukAngsuran.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalPendapatanUntukAngsuran));
        binding.etTotalDebit1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalDebit1));
        binding.etTotalDebit2.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalDebit2));
        binding.etTotalKredit1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalKredit1));
        binding.etTotalKredit2.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalKredit2));
    }

    private void onclickSelectDialog() {
        binding.tfPilihanAkseptasiPendapatan.setOnClickListener(this);
        binding.etPilihanAkseptasiPendapatan.setOnClickListener(this);
        binding.etTanggalDokumenPendapatan.setOnClickListener(this);
        binding.tfTanggalDokumenPendapatan.setOnClickListener(this);
        binding.llBtnSend.setOnClickListener(this);
        binding.btnSend.setOnClickListener(this);
        binding.llDeleteDokumen.setOnClickListener(this);
        binding.btnDeleteDokumen.setOnClickListener(this);
        binding.llTambahanDokumen.setOnClickListener(this);
        binding.btnTambahanDokumen.setOnClickListener(this);
        binding.rlDokumenPendapatan.setOnClickListener(this);
        binding.btnDokumenPendapatan.setOnClickListener(this);
        binding.ivDokumenPendapatan.setOnClickListener(this);
        binding.llHitung.setOnClickListener(this);
        binding.btnHitung.setOnClickListener(this);
        binding.etVerfikasiRekening.setOnClickListener(this);
        binding.tfVerfikasiRekening.setOnClickListener(this);

        binding.etPeriodeAkhirWaktu1.setOnClickListener(this);
        binding.etPeriodeAkhirWaktu2.setOnClickListener(this);
        binding.etPeriodeAwalWaktu1.setOnClickListener(this);
        binding.etPeriodeAwalWaktu2.setOnClickListener(this);
        binding.tfPeriodeAkhirWaktu1.setOnClickListener(this);
        binding.tfPeriodeAkhirWaktu2.setOnClickListener(this);
        binding.tfPeriodeAwalWaktu1.setOnClickListener(this);
        binding.tfPeriodeAwalWaktu2.setOnClickListener(this);
    }

    private void initdata() {

        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqInquery req = new ReqInquery();
        req.setApplicationId(Integer.parseInt(idAplikasi));
//        req.setUID("4976");
        req.setUID(String.valueOf(appPreferences.getUid()));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().InquiryAkseptasiPendapatanMarketing(req);
        call.enqueue(new Callback<ParseResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<ParseResponse> call, @NonNull Response<ParseResponse> response) {
                if (response.isSuccessful()) {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    if (response.body() != null) {
                        Gson gson = new Gson();
                        String rpendapatan, rDetail, rimg, rgaji;
                        assert response.body().getData() != null;
                        if (response.body().getData().get("PendapatanTunjangan") != null) {
                            rpendapatan = response.body().getData().get("PendapatanTunjangan").getAsJsonObject().toString();
                            Type typependapatan = new TypeToken<MparsePendapatanTunjangan>() {
                            }.getType();
                            mparsePendapatanTunjangan = gson.fromJson(rpendapatan, typependapatan);
                            if (mparsePendapatanTunjangan.getAkseptasiPendapatan() == 1) {
                                binding.tambahanDokumen.setVisibility(View.VISIBLE);
                                binding.etPilihanAkseptasiPendapatan.setText("Pendapatan Aktif dan Pensiun");
                            } else {
                                binding.tambahanDokumen.setVisibility(View.GONE);
                                binding.etPilihanAkseptasiPendapatan.setText("Hanya Pendapatan Pensiun");
                            }
                            binding.etPendapatanSaatPensiun.setText(String.valueOf(mparsePendapatanTunjangan.getManfaatPensiun().setScale(2, RoundingMode.HALF_EVEN)));
                            binding.etTotalPendapatanUntukAngsuran.setText(String.valueOf(mparsePendapatanTunjangan.getTotal_Maksimal_Angsuran().setScale(2, RoundingMode.HALF_EVEN)));
                            binding.etTotalPendapatanSetelahAkseptasi.setText(String.valueOf(mparsePendapatanTunjangan.getTotal_Pendapatan_Akseptasi().setScale(2, RoundingMode.HALF_EVEN)));
                            if (!mparsePendapatanTunjangan.getTotal_Pendapatan_Akseptasi().equals(new BigDecimal("0"))) {
                                hitung = 1;
                            }
                        }
                        if (response.body().getData().get("PendapatanTunjanganDetailD3List").getAsJsonArray().size() != 0) {
                            rDetail = response.body().getData().get("PendapatanTunjanganDetailD3List").toString();
                            Type typeDetail = new TypeToken<List<SubAkseptasiPendapatan>>() {
                            }.getType();
                            data1 = gson.fromJson(rDetail, typeDetail);

                            //Resize List
                            SwipeRefreshLayout layout = binding.refresh;
                            ViewGroup.LayoutParams params = layout.getLayoutParams();
                            params.height = data1.size() * 1020;
                            layout.setLayoutParams(params);

                            //List Data Akseptasi Pendapatan
                            binding.rvListPendapatan.setVisibility(View.VISIBLE);
                            binding.rvListPendapatan.setHasFixedSize(true);
                            akseptasiPendapatanAdapter = new AkseptasiPendapatanAdapter(ActivityAkseptasiPendapatan.this, data1, komponen, treatment);
                            binding.rvListPendapatan.setLayoutManager(new LinearLayoutManager(ActivityAkseptasiPendapatan.this));
                            binding.rvListPendapatan.setItemAnimator(new DefaultItemAnimator());
                            binding.rvListPendapatan.setAdapter(akseptasiPendapatanAdapter);
                            binding.refresh.setRefreshing(false);
                            binding.refresh.setEnabled(false);
                        } else {
                            datakosong();
                        }
                        if (response.body().getData().get("PendapatanTunjangan_Img") != null) {
                            rimg = response.body().getData().get("PendapatanTunjangan_Img").getAsJsonObject().toString();
                            Type typeimg = new TypeToken<ReqDocument>() {
                            }.getType();
                            reqDocument = gson.fromJson(rimg, typeimg);
                            try {
                                binding.etTanggalDokumenPendapatan.setText(AppUtil.parseTanggalGeneral(reqDocument.getTanggal_Dokumen(), "yyyy-MM-dd", "MM-yyyy"));
                                checkFileTypeThenSet(ActivityAkseptasiPendapatan.this, reqDocument.getImg(), binding.ivDokumenPendapatan, reqDocument.getFileName());
                                reqDocument.setImg(reqDocument.getImg());
                                reqDocument.setFileName(reqDocument.getFileName());
                            } catch (Exception e) {
                                AppUtil.logSecure("error setdata", e.getMessage());
                            }
                        }
                        if (response.body().getData().get("DataGajiTunjangan") != null) {
                            rgaji = response.body().getData().get("DataGajiTunjangan").getAsJsonObject().toString();
                            Type typegaji = new TypeToken<MparseDataGajiTunjangan>() {
                            }.getType();
                            mparseDataGajiTunjangan = gson.fromJson(rgaji, typegaji);

                            if (mparseDataGajiTunjangan.getNomor_Rek_Bank_Gaji().equals(mparseDataGajiTunjangan.getNo_Rekening_Tunjangan())) {
                                binding.etVerfikasiRekening.setText("Ya");
                                binding.tfNorekTunjangan.setVisibility(View.GONE);
                                binding.tfNamaBankTunjangan.setVisibility(View.GONE);
                                binding.tfTotalKredit2.setVisibility(View.GONE);
                                binding.tfTotalDebit2.setVisibility(View.GONE);
                                binding.tfPeriodeAwalWaktu2.setVisibility(View.GONE);
                                binding.tfPeriodeAkhirWaktu2.setVisibility(View.GONE);
                                binding.norekTunjangan.setVisibility(View.GONE);
                            } else {
                                binding.etVerfikasiRekening.setText("Tidak");
                                binding.tfNorekTunjangan.setVisibility(View.VISIBLE);
                                binding.tfNamaBankTunjangan.setVisibility(View.VISIBLE);
                                binding.tfTotalKredit2.setVisibility(View.VISIBLE);
                                binding.tfTotalDebit2.setVisibility(View.VISIBLE);
                                binding.tfPeriodeAwalWaktu2.setVisibility(View.VISIBLE);
                                binding.tfPeriodeAkhirWaktu2.setVisibility(View.VISIBLE);
                                binding.norekTunjangan.setVisibility(View.VISIBLE);
                            }

                            binding.etTanggalDokumenPendapatan.setText(AppUtil.parseTanggalGeneral(mparseDataGajiTunjangan.getPeriode_Date_ToGaji(), "yyyy-MM-dd", "MM-yyyy"));
                            binding.etNorekGaji.setText(mparseDataGajiTunjangan.getNomor_Rek_Bank_Gaji());
                            binding.etNamaBankGaji.setText(mparseDataGajiTunjangan.getNama_Bank_Gaji());
                            binding.etPeriodeAwalWaktu1.setText(AppUtil.parseTanggalGeneral(mparseDataGajiTunjangan.getPeriode_Date_FromGaji(), "yyyy-MM-dd", "dd-MM-yyyy"));
                            binding.etPeriodeAkhirWaktu1.setText(AppUtil.parseTanggalGeneral(mparseDataGajiTunjangan.getPeriode_Date_ToGaji(), "yyyy-MM-dd", "dd-MM-yyyy"));
                            binding.etTotalKredit1.setText(String.valueOf(mparseDataGajiTunjangan.getTotal_KreditGaji()));
                            binding.etTotalDebit1.setText(String.valueOf(mparseDataGajiTunjangan.getTotal_DebitGaji()));

                            binding.etNorekTunjangan.setText(mparseDataGajiTunjangan.getNo_Rekening_Tunjangan());
                            binding.etNamaBankTunjangan.setText(mparseDataGajiTunjangan.getNama_Bank_Tunjangan());
                            binding.etPeriodeAwalWaktu2.setText(AppUtil.parseTanggalGeneral(mparseDataGajiTunjangan.getPeriode_Date_FromTunjangan(), "yyyy-MM-dd", "dd-MM-yyyy"));
                            binding.etPeriodeAkhirWaktu2.setText(AppUtil.parseTanggalGeneral(mparseDataGajiTunjangan.getPeriode_Date_ToTunjangan(), "yyyy-MM-dd", "dd-MM-yyyy"));
                            binding.etTotalDebit2.setText(String.valueOf(mparseDataGajiTunjangan.getTotal_DebitTunjangan()));
                            binding.etTotalKredit2.setText(String.valueOf(mparseDataGajiTunjangan.getTotal_KreditTunjangan()));
                        }

                    } else {
                        AppUtil.notiferror(ActivityAkseptasiPendapatan.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                } else {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    assert response.errorBody() != null;
                    Error error = ParseResponseError.confirmEror(response.errorBody());
                    AppUtil.notiferror(ActivityAkseptasiPendapatan.this, findViewById(android.R.id.content), error.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ParseResponse> call, @NonNull Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityAkseptasiPendapatan.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }

    private void datakosong() {
        //Initial Data
        list += 1;
        SubAkseptasiPendapatan new1 = new SubAkseptasiPendapatan();
        new1.setPendapatan_Tercermin(new BigDecimal("0"));
        new1.setNilaiSetelahAkseptasi(new BigDecimal("0"));
        new1.setKeterangan("Pilih Treatment Pendapatan");
        new1.setStatus_Payroll("Pilih Nama Komponen Pendapatan");
        data1.add(new1);

        //Resize List
        SwipeRefreshLayout layout = binding.refresh;
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        params.height = data1.size() * 1020;
        layout.setLayoutParams(params);

        //List Data Akseptasi Pendapatan
        binding.rvListPendapatan.setVisibility(View.VISIBLE);
        binding.rvListPendapatan.setHasFixedSize(true);
        akseptasiPendapatanAdapter = new AkseptasiPendapatanAdapter(this, data1, komponen, treatment);
        binding.rvListPendapatan.setLayoutManager(new LinearLayoutManager(this));
        binding.rvListPendapatan.setItemAnimator(new DefaultItemAnimator());
        binding.rvListPendapatan.setAdapter(akseptasiPendapatanAdapter);
        binding.refresh.setRefreshing(false);
        binding.refresh.setEnabled(false);

        binding.tambahanDokumen.setVisibility(View.GONE);
    }

    private void isidropdown() {

        treatment.add(new MGenericModel("1", "Payroll"));
        treatment.add(new MGenericModel("2", "Commited to Payroll"));
        treatment.add(new MGenericModel("3", "Non Payroll"));

        akseptasi.add(new MGenericModel("1", "Pendapatan Aktif dan Pensiun"));
        akseptasi.add(new MGenericModel("2", "Hanya Pendapatan Pensiun"));

        tercermin.add(new MGenericModel("1", "Tercermin"));
        tercermin.add(new MGenericModel("2", "Tercermin tapi nominal lebih rendah"));
        tercermin.add(new MGenericModel("3", "Tercermin tapi Nominal lebih Tinggi"));
        tercermin.add(new MGenericModel("4", "Tidak tercermin direkening"));

        gajitunjangan.add(new MGenericModel("1", "Ya"));
        gajitunjangan.add(new MGenericModel("2", "Tidak"));

        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        EmptyRequest emptyRequest = new EmptyRequest();
        //Inq PickList
        Call<MparsePicklist> calli = apiClientAdapter.getApiInterface().dropdownKomponenPendapatan(emptyRequest);
        calli.enqueue(new Callback<MparsePicklist>() {
            @Override
            public void onResponse(@NonNull Call<MparsePicklist> call, @NonNull Response<MparsePicklist> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        picklistModel = response.body();
                        if (response.body().getPicklists() != null) {
                            maxlist = response.body().getPicklists().size();
                            for (int i = 0; i < response.body().getPicklists().size(); i++) {
                                komponen.add(new MGenericModel(response.body().getPicklists().get(i).getId().toString(), response.body().getPicklists().get(i).getName(), response.body().getPicklists().get(i).getNilai(), String.valueOf(response.body().getPicklists().get(i).getPendapatanTunjanganId())));
                            }
                        }
                    }
                } else {
                    AppUtil.notiferror(ActivityAkseptasiPendapatan.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                }
            }

            @Override
            public void onFailure(@NonNull Call<MparsePicklist> call, @NonNull Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityAkseptasiPendapatan.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }

    private void disabledEdit() {
        binding.tfPilihanAkseptasiPendapatan.setFocusable(false);
        binding.etPilihanAkseptasiPendapatan.setFocusable(false);
        binding.tfPendapatanSaatPensiun.setFocusable(false);
        binding.etPendapatanSaatPensiun.setFocusable(false);
        binding.tfTotalPendapatanSetelahAkseptasi.setFocusable(false);
        binding.etTotalPendapatanSetelahAkseptasi.setFocusable(false);
        binding.tfTotalPendapatanUntukAngsuran.setFocusable(false);
        binding.etTotalPendapatanUntukAngsuran.setFocusable(false);
        binding.etPeriodeAkhirWaktu1.setFocusable(false);
        binding.etPeriodeAkhirWaktu2.setFocusable(false);
        binding.etPeriodeAwalWaktu1.setFocusable(false);
        binding.etPeriodeAwalWaktu2.setFocusable(false);
        binding.tfPeriodeAkhirWaktu1.setFocusable(false);
        binding.tfPeriodeAkhirWaktu2.setFocusable(false);
        binding.tfPeriodeAwalWaktu1.setFocusable(false);
        binding.tfPeriodeAwalWaktu2.setFocusable(false);
        binding.etVerfikasiRekening.setFocusable(false);
        binding.tfVerfikasiRekening.setFocusable(false);
    }

    private void endIconClick() {
        binding.tfPilihanAkseptasiPendapatan.getEndIconImageButton().setOnClickListener(
                v -> DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfPilihanAkseptasiPendapatan.getLabelText(), akseptasi, ActivityAkseptasiPendapatan.this)
        );
        binding.tfTanggalDokumenPendapatan.getEndIconImageButton().setOnClickListener(
                v -> dpSKCalendar(v, binding.etTanggalDokumenPendapatan, binding.tfTanggalDokumenPendapatan.getLabelText(), dateClient2)
        );
        binding.tfVerfikasiRekening.getEndIconImageButton().setOnClickListener(
                v -> DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfVerfikasiRekening.getLabelText(), gajitunjangan, ActivityAkseptasiPendapatan.this)
        );
        binding.tfPeriodeAkhirWaktu1.getEndIconImageButton().setOnClickListener(
                v -> dpSKCalendar(v, binding.etPeriodeAkhirWaktu1, binding.tfPeriodeAkhirWaktu1.getLabelText(), dateClient2)
        );
        binding.tfPeriodeAkhirWaktu2.getEndIconImageButton().setOnClickListener(
                v -> dpSKCalendar(v, binding.etPeriodeAkhirWaktu2, binding.tfPeriodeAkhirWaktu2.getLabelText(), dateClient2)
        );
        binding.tfPeriodeAwalWaktu1.getEndIconImageButton().setOnClickListener(
                v -> dpSKCalendar(v, binding.etPeriodeAwalWaktu1, binding.tfPeriodeAwalWaktu1.getLabelText(), dateClient2)
        );
        binding.tfPeriodeAwalWaktu2.getEndIconImageButton().setOnClickListener(
                v -> dpSKCalendar(v, binding.etPeriodeAwalWaktu2, binding.tfPeriodeAwalWaktu2.getLabelText(), dateClient2)
        );
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
            case R.id.et_verfikasi_rekening:
            case R.id.tf_verfikasi_rekening:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfVerfikasiRekening.getLabelText(), gajitunjangan, ActivityAkseptasiPendapatan.this);
                break;
            case R.id.ll_hitung:
            case R.id.btn_hitung:
                hitung += 1;
                hitungAkseptasi();
                break;
            case R.id.tf_tanggal_dokumen_pendapatan:
            case R.id.et_tanggal_dokumen_pendapatan:
                dpSKCalendar(v, binding.etTanggalDokumenPendapatan, binding.tfTanggalDokumenPendapatan.getLabelText(), dateClient2);
                break;
            case R.id.rl_dokumen_pendapatan:
            case R.id.iv_dokumen_pendapatan:
            case R.id.btn_dokumen_pendapatan:
                BSUploadFile.displayWithTitle(this.getSupportFragmentManager(), this, "Pilih File");
                break;
            case R.id.et_pilihan_akseptasi_pendapatan:
            case R.id.tf_pilihan_akseptasi_pendapatan:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfPilihanAkseptasiPendapatan.getLabelText(), akseptasi, ActivityAkseptasiPendapatan.this);
                break;
            case R.id.ll_btn_send:
            case R.id.btn_send:
                sendData();
                break;
            case R.id.btn_tambahan_dokumen:
            case R.id.ll_tambahan_dokumen:

                if (!maxlist.equals(list)) {
                    list += 1;
                    if (maxlist.equals(list)) {
                        binding.llTambahanDokumen.setVisibility(View.GONE);
                    }
                    SubAkseptasiPendapatan new1 = new SubAkseptasiPendapatan();
                    new1.setPendapatan_Tercermin(new BigDecimal("0"));
                    new1.setNilaiSetelahAkseptasi(new BigDecimal("0"));
                    new1.setKeterangan("Pilih Treatment Pendapatan");
                    new1.setStatus_Payroll("Pilih Nama Komponen Pendapatan");
                    data1.add(new1);

                    //Resize List
                    SwipeRefreshLayout layout = binding.refresh;
                    ViewGroup.LayoutParams params = layout.getLayoutParams();
                    params.height = data1.size() * 1010;
                    layout.setLayoutParams(params);

                    //List Data Akseptasi Pendapatan
                    binding.rvListPendapatan.setVisibility(View.VISIBLE);
                    binding.rvListPendapatan.setHasFixedSize(true);
                    akseptasiPendapatanAdapter = new AkseptasiPendapatanAdapter(this, data1, komponen, treatment);
                    binding.rvListPendapatan.setLayoutManager(new LinearLayoutManager(this));
                    binding.rvListPendapatan.setItemAnimator(new DefaultItemAnimator());
                    binding.rvListPendapatan.setAdapter(akseptasiPendapatanAdapter);
                    binding.refresh.setRefreshing(false);

                    binding.llDeleteDokumen.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.btn_delete_dokumen:
            case R.id.ll_delete_dokumen:
                if (!list.equals(1)) {
                    data1.remove(list - 1);
                    list -= 1;
                    if (list.equals(1)) {
                        binding.llDeleteDokumen.setVisibility(View.GONE);
                    }
                    //Resize List
                    SwipeRefreshLayout layout = binding.refresh;
                    ViewGroup.LayoutParams params = layout.getLayoutParams();
                    params.height = data1.size() * 1010;
                    layout.setLayoutParams(params);

                    //List Data Akseptasi Pendapatan
                    binding.rvListPendapatan.setVisibility(View.VISIBLE);
                    binding.rvListPendapatan.setHasFixedSize(true);
                    akseptasiPendapatanAdapter = new AkseptasiPendapatanAdapter(this, data1, komponen, treatment);
                    binding.rvListPendapatan.setLayoutManager(new LinearLayoutManager(this));
                    binding.rvListPendapatan.setItemAnimator(new DefaultItemAnimator());
                    binding.rvListPendapatan.setAdapter(akseptasiPendapatanAdapter);
                    binding.refresh.setRefreshing(false);

                    binding.llTambahanDokumen.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private void hitungAkseptasi() {
//        idAplikasi = "131";
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqHitungAkseptasiPendapatan req = new ReqHitungAkseptasiPendapatan();
        req.setApplicationId(Integer.parseInt(idAplikasi));
//        req.setUID("4976");
        req.setUID(String.valueOf(appPreferences.getUid()));
        req.setData(data1);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().HitungAkseptasiPendapatan(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(@NonNull Call<ParseResponse> call, @NonNull Response<ParseResponse> response) {
                if (response.isSuccessful()) {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    if (response.body() != null) {
                        assert response.body().getData() != null;
                        if (response.body().getData().get("MaksimalAngsuran") != null) {
                            binding.etTotalPendapatanUntukAngsuran.setText(String.valueOf(response.body().getData().get("MaksimalAngsuran").getAsBigDecimal().setScale(2,RoundingMode.HALF_EVEN)));

                        }
                        if (response.body().getData().get("Totalakseptasi") != null) {
                            binding.etTotalPendapatanSetelahAkseptasi.setText(String.valueOf(response.body().getData().get("Totalakseptasi").getAsBigDecimal().setScale(2,RoundingMode.HALF_EVEN)));

                        }
                    } else {
                        AppUtil.notiferror(ActivityAkseptasiPendapatan.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                } else {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    assert response.errorBody() != null;
                    Error error = ParseResponseError.confirmEror(response.errorBody());
                    AppUtil.notiferror(ActivityAkseptasiPendapatan.this, findViewById(android.R.id.content), error.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ParseResponse> call, @NonNull Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityAkseptasiPendapatan.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void sendData() {
        if (hitung != 0) {
            binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
            MparseDataGajiTunjangan doc = new MparseDataGajiTunjangan();
            doc.setNama_Bank_Gaji(binding.etNamaBankGaji.getText().toString());
            doc.setNomor_Rek_Bank_Gaji(binding.etNorekGaji.getText().toString());
            doc.setTotal_DebitGaji(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTotalDebit1.getText().toString())));
            doc.setTotal_KreditGaji(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTotalKredit1.getText().toString())));
            doc.setPeriode_Date_FromGaji(AppUtil.parseTanggalGeneral(binding.etPeriodeAwalWaktu1.getText().toString(), "dd-MM-yyyy", "yyyy-MM-dd"));
            doc.setPeriode_Date_ToGaji(AppUtil.parseTanggalGeneral(binding.etPeriodeAkhirWaktu1.getText().toString(), "dd-MM-yyyy", "yyyy-MM-dd"));
            if (binding.etVerfikasiRekening.getText().toString().equalsIgnoreCase("Tidak")) {
                doc.setNo_Rekening_Tunjangan(binding.etNorekTunjangan.getText().toString());
                doc.setNama_Bank_Tunjangan(binding.etNamaBankTunjangan.getText().toString());
                doc.setPeriode_Date_FromTunjangan(AppUtil.parseTanggalGeneral(binding.etPeriodeAwalWaktu2.getText().toString(), "dd-MM-yyyy", "yyyy-MM-dd"));
                doc.setPeriode_Date_ToTunjangan(AppUtil.parseTanggalGeneral(binding.etPeriodeAkhirWaktu2.getText().toString(), "dd-MM-yyyy", "yyyy-MM-dd"));
                doc.setTotal_DebitTunjangan(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTotalDebit2.getText().toString())));
                doc.setTotal_KreditTunjangan(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTotalKredit2.getText().toString())));
            } else {
                doc.setNo_Rekening_Tunjangan(binding.etNorekGaji.getText().toString());
                doc.setNama_Bank_Tunjangan(binding.etNamaBankGaji.getText().toString());
                doc.setPeriode_Date_FromTunjangan(AppUtil.parseTanggalGeneral(binding.etPeriodeAwalWaktu1.getText().toString(), "dd-MM-yyyy", "yyyy-MM-dd"));
                doc.setPeriode_Date_ToTunjangan(AppUtil.parseTanggalGeneral(binding.etPeriodeAkhirWaktu1.getText().toString(), "dd-MM-yyyy", "yyyy-MM-dd"));
                doc.setTotal_DebitTunjangan(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTotalDebit1.getText().toString())));
                doc.setTotal_KreditTunjangan(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTotalKredit1.getText().toString())));
            }

            mparseDataGajiTunjangan = doc;
            UpdateAkseptasiPendapatan data = new UpdateAkseptasiPendapatan();
            data.setAkseptasiPendapatan(String.valueOf(mparsePendapatanTunjangan.getAkseptasiPendapatan()));
            data.setDataGajiTunjangan(mparseDataGajiTunjangan);
            data.setDokumenPendapatan(reqDocument);
            data.setPendapatanTunjangan(data1);
            ReqUpdateAkseptasi req = new ReqUpdateAkseptasi();
            req.setApplicationId(Long.parseLong(String.valueOf(idAplikasi)));
//            req.setUid("4976");
            req.setUid(String.valueOf(appPreferences.getUid()));
            req.setUpdateAkseptasiPendapatan(data);
            Call<ParseResponse> call = apiClientAdapter.getApiInterface().UpdateAkseptasiPendapatanMarketing(req);
            call.enqueue(new Callback<ParseResponse>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(@NonNull Call<ParseResponse> call, @NonNull Response<ParseResponse> response) {
                    if (response.isSuccessful()) {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        assert response.body() != null;
                        if (response.body().getMessage().equals("Success")) {
                            finish();
                        } else {
                            AppUtil.notiferror(ActivityAkseptasiPendapatan.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        assert response.errorBody() != null;
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ActivityAkseptasiPendapatan.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ParseResponse> call, @NonNull Throwable t) {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    AppUtil.notiferror(ActivityAkseptasiPendapatan.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                }
            });
        } else {
            AppUtil.notiferror(ActivityAkseptasiPendapatan.this, findViewById(android.R.id.content), "Klik Button Hitung Terlebih Dahulu");
        }
    }

    @Override
    public void onHandleSelection(int position, SubAkseptasiPendapatan data) {
        data1.set(position, data);
    }

    @Override
    public void onSelect(String title, MGenericModel data) {
        if (title.equalsIgnoreCase(binding.tfPilihanAkseptasiPendapatan.getLabelText())) {
            binding.etPilihanAkseptasiPendapatan.setText(data.getDESC());
            if (data.getID().equals("1")) {
                binding.tambahanDokumen.setVisibility(View.VISIBLE);
            } else {
                binding.tambahanDokumen.setVisibility(View.GONE);
            }
            if (mparsePendapatanTunjangan == null) {
                mparsePendapatanTunjangan = new MparsePendapatanTunjangan();
            }
            mparsePendapatanTunjangan.setAkseptasiPendapatan(Long.parseLong(data.getID()));
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
    }

    private void dpSKCalendar(View v, EditText et, String title, SimpleDateFormat dateT) {
        calLahir = Calendar.getInstance();
        @SuppressLint("NonConstantResourceId") DatePickerDialog.OnDateSetListener ls_tanggalLahirPasangan = (view, year, month, dayOfMonth) -> {
            calLahir.set(Calendar.YEAR, year);
            calLahir.set(Calendar.MONTH, month);
            calLahir.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String calLahirString = dateClient.format(calLahir.getTime());
            String calLahirString2 = dateClient2.format(calLahir.getTime());
            String calLahirString3 = dateClient3.format(calLahir.getTime());
            if (title.equalsIgnoreCase(binding.tfTanggalDokumenPendapatan.getLabelText())) {
                binding.etTanggalDokumenPendapatan.setText(calLahirString);
                reqDocument.setTanggal_Dokumen(calLahirString3);
            } else {
                et.setText(calLahirString2);
            }

        };

        DatePickerDialog dpSK = new DatePickerDialog(ActivityAkseptasiPendapatan.this, R.style.AppTheme_TimePickerTheme, ls_tanggalLahirPasangan, calLahir.get(Calendar.YEAR),
                calLahir.get(Calendar.MONTH), calLahir.get(Calendar.DAY_OF_MONTH));
        dpSK.getDatePicker().setMaxDate(calLahir.getTimeInMillis());
        dpSK.show();
    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        switch (idMenu) {
            case "Take Photo":
                tipeFile = "png";
                openCamera(photodokumen, "dokumenA");
                break;
            case "Pick Photo":
                tipeFile = "png";
                openGalery(photodokumen);
                break;
            case "Pick File":
                tipeFile = "pdf";
                openFile(photodokumen);
                break;
        }
    }

    private void openCamera(int cameraCode, String namaFoto) {
        checkCameraPermission(cameraCode, namaFoto);
    }

    public void openGalery(int cameraCode) {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        this.startActivityForResult(Intent.createChooser(it, "Select File"), cameraCode);
    }

    public void openFile(int cameraCode) {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("application/pdf");
        this.startActivityForResult(Intent.createChooser(it, "Select File"), cameraCode);
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
            this.startActivityForResult(captureIntent, cameraCode);
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

    public Uri getPickImageResultUri(Intent data, String namaFoto) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri(namaFoto) : data.getData();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setDataImage(data, "DokumenPendapatan");
        checkFileTypeThenUpload("DokumenPendapatan", "_DokumenPendapatan", Valdokumen, photodokumen);
    }

    private void checkFileTypeThenUpload(String filename, String fileNameDoc, String val_base64, int uploadCode) {
        if (tipeFile.equalsIgnoreCase("pdf")) {
            filename = idAplikasi + fileNameDoc + ".pdf";
        } else {
            filename = idAplikasi + fileNameDoc + ".png";
        }
        uploadFile(val_base64, filename, uploadCode);
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
                    reqDocument.setFileName(fileName);
                    reqDocument.setImg(response.body().getId());
                    AppUtil.notifsuccess(ActivityAkseptasiPendapatan.this, findViewById(android.R.id.content), "Upload Berhasil");
//                    sudahUpload=true;
                } else {
                    AppUtil.notiferror(ActivityAkseptasiPendapatan.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseFile> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityAkseptasiPendapatan.this, findViewById(android.R.id.content), "Terjadi kesalahan");

            }
        });

    }

    private void setDataImage(Intent data, String namaFoto) {
        Bitmap bitmap = null;
        Uri uri;
        if (getPickImageResultUri(data, namaFoto) != null) {
            uri = getPickImageResultUri(data, namaFoto);
            try {

                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                bitmap = AppUtil.rotateImageIfRequired(this, bitmap, uri);
                binding.ivDokumenPendapatan.setImageBitmap(bitmap);
                Valdokumen = AppUtil.encodeImageTobase64(bitmap);
            } catch (NullPointerException e) {
                e.printStackTrace();
                Uri uriPdf = data.getData();
                binding.ivDokumenPendapatan.setImageDrawable(getDrawable(R.drawable.ic_pdf_hd));
                Valdokumen = AppUtil.encodeFileToBase64(this, uriPdf);
                AppUtil.convertBase64ToFileWithOnClick(this, Valdokumen, binding.ivDokumenPendapatan, namaFoto);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void checkFileTypeThenSet(Context context, String idDok, ImageView imageView, String fileName) {

        if (fileName.substring(fileName.length() - 3, fileName.length()).equalsIgnoreCase("pdf")) {

            if (idDok.length() < 10) {
                loadFileJson(idDok, imageView);
            } else {
                AppUtil.convertBase64ToFileWithOnClick(context, idDok, imageView, fileName);
            }

        } else {

            if (idDok.length() < 10) {
                AppUtil.setImageGlide(context, idDok, imageView);
            } else {
                AppUtil.convertBase64ToImage(idDok, imageView);
            }

        }
    }

    public void loadFileJson(String idFoto, ImageView imageView) {
        ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityAkseptasiPendapatan.this);
        Call<ParseResponseLogicalDoc> call = apiClientAdapter.getApiInterface().getFileJson(idFoto);
        call.enqueue(new Callback<ParseResponseLogicalDoc>() {
            @Override
            public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
//                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getBinaryData() != null) {
                        AppUtil.convertBase64ToFileWithOnClick(ActivityAkseptasiPendapatan.this, response.body().getBinaryData(), imageView, response.body().getFileName());
                    } else {
                        AppUtil.notiferror(ActivityAkseptasiPendapatan.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                    }


                } else {
                    AppUtil.notiferror(ActivityAkseptasiPendapatan.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityAkseptasiPendapatan.this, findViewById(android.R.id.content), "Terjadi kesalahan");

                t.printStackTrace();
            }
        });

    }
}
