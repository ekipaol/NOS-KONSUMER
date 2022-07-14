package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_akseptasi_pendapatan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseLogicalDoc;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDocument;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqInquery;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.SubAkseptasiPendapatan;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseDataGajiTunjangan;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparsePendapatanTunjangan;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparsePicklist;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityAkseptasiPendapatanBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

public class ActivityVerifAkseptasiPendapatan extends AppCompatActivity {

    VerifAkseptasiPendapatanAdapter verifAkseptasiPendapatanAdapter;
    ActivityAkseptasiPendapatanBinding binding;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private Calendar calLahir;
    private String idAplikasi;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("MM-yyyy", Locale.US);
    public static SimpleDateFormat dateClient2 = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    public static SimpleDateFormat dateClient3 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    Integer maxlist = 8, list = 0, photodokumen = 1, hitung = 0;
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
        hideall();
        backgroundStatusBar();
        initdata();
        defaultViewCondition();
        AppUtil.toolbarRegular(this, "Akseptasi Pendapatan");
    }

    private void hideall() {
        AppUtil.disableEditTexts(binding.getRoot());
        binding.btnList.setVisibility(View.GONE);
        binding.llBtnSend.setVisibility(View.GONE);
        binding.tfTercermin.setVisibility(View.VISIBLE);
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void defaultViewCondition(){
        binding.tfPeriodeAkhirWaktu1.setVisibility(View.GONE);
        binding.tfPeriodeAkhirWaktu2.setVisibility(View.GONE);
        binding.tfPeriodeAwalWaktu1.setVisibility(View.GONE);
        binding.tfPeriodeAwalWaktu2.setVisibility(View.GONE);
        binding.tfTotalKredit1.setVisibility(View.GONE);
        binding.tfTotalKredit2.setVisibility(View.GONE);
        binding.tfTotalDebit1.setVisibility(View.GONE);
        binding.tfTotalDebit2.setVisibility(View.GONE);
    }

    private void initdata() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqInquery req = new ReqInquery();
        req.setApplicationId(Integer.parseInt(idAplikasi));
//        req.setUID("4976");
        req.setUID(String.valueOf(appPreferences.getUid()));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().InquiryAkseptasiPendapatanMarketingD4(req);
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
                        if(response.body().getStatus().equalsIgnoreCase("00")){
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
                                binding.etTercermin.setText(mparsePendapatanTunjangan.getTercermin());
                                binding.etPendapatanSaatPensiun.setText(String.valueOf(mparsePendapatanTunjangan.getManfaatPensiun().setScale(2, RoundingMode.HALF_EVEN)));
                                binding.etTotalPendapatanUntukAngsuran.setText(String.valueOf(mparsePendapatanTunjangan.getTotal_Maksimal_Angsuran().setScale(2, RoundingMode.HALF_EVEN)));
                                binding.etTotalPendapatanSetelahAkseptasi.setText(String.valueOf(mparsePendapatanTunjangan.getTotal_Pendapatan_Akseptasi().setScale(2, RoundingMode.HALF_EVEN)));
                                if (!mparsePendapatanTunjangan.getTotal_Pendapatan_Akseptasi().equals(new BigDecimal("0"))) {
                                    hitung = 1;
                                }

                            }

                            if (response.body().getData().get("PendapatanTunjanganDetailD4List").getAsJsonArray().size() != 0) {
                                rDetail = response.body().getData().get("PendapatanTunjanganDetailD4List").toString();
                                Type typeDetail = new TypeToken<List<SubAkseptasiPendapatan>>() {
                                }.getType();
                                data1 = gson.fromJson(rDetail, typeDetail);

                                // Init List
                                list = data1.size();

                                //List Data Akseptasi Pendapatan
                                binding.rvListPendapatan.setVisibility(View.VISIBLE);
                                binding.rvListPendapatan.setHasFixedSize(true);
                                verifAkseptasiPendapatanAdapter = new VerifAkseptasiPendapatanAdapter(ActivityVerifAkseptasiPendapatan.this, data1, komponen, treatment);
                                binding.rvListPendapatan.setLayoutManager(new LinearLayoutManager(ActivityVerifAkseptasiPendapatan.this));
                                binding.rvListPendapatan.setItemAnimator(new DefaultItemAnimator());
                                binding.rvListPendapatan.setAdapter(verifAkseptasiPendapatanAdapter);
                                binding.refresh.setRefreshing(false);
                                binding.refresh.setEnabled(false);
                                resizeList();
                            }

                            if (response.body().getData().get("PendapatanTunjangan_Img") != null) {
                                rimg = response.body().getData().get("PendapatanTunjangan_Img").getAsJsonObject().toString();
                                Type typeimg = new TypeToken<ReqDocument>() {
                                }.getType();
                                reqDocument = gson.fromJson(rimg, typeimg);
                                try {
                                    binding.etTanggalDokumenPendapatan.setText(AppUtil.parseTanggalGeneral(reqDocument.getTanggal_Dokumen(), "yyyy-MM-dd", "MM-yyyy"));
                                    checkFileTypeThenSet(ActivityVerifAkseptasiPendapatan.this, reqDocument.getImg(), binding.ivDokumenPendapatan, reqDocument.getFileName());
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
                        }
                        else{
                            AppUtil.notiferror(ActivityVerifAkseptasiPendapatan.this, findViewById(android.R.id.content), response.body().getMessage());
                        }


                    } else {
                        AppUtil.notiferror(ActivityVerifAkseptasiPendapatan.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
                    }
                } else {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    assert response.errorBody() != null;
                    Error error = ParseResponseError.confirmEror(response.errorBody());
                    AppUtil.notiferror(ActivityVerifAkseptasiPendapatan.this, findViewById(android.R.id.content), error.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ParseResponse> call, @NonNull Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityVerifAkseptasiPendapatan.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }

    private void resizeList() {
        //Resize List
        SwipeRefreshLayout layout = binding.refresh;
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        if (data1.size() == 1) {
            params.height = 1020;
            AppUtil.logSecure("ukuranlist", String.valueOf(data1.size()));
        } else if (data1.size() >= 3) {
            params.height = 2040;
        } else {
            params.height = (data1.size() - 1) * 1020;
            AppUtil.logSecure("ukuranlist", String.valueOf(data1.size()));
        }
        layout.setLayoutParams(params);
        binding.rvListPendapatan.smoothScrollToPosition(binding.rvListPendapatan.getAdapter().getItemCount() - 1);
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
        ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityVerifAkseptasiPendapatan.this);
        Call<ParseResponseLogicalDoc> call = apiClientAdapter.getApiInterface().getFileJson(idFoto);
        call.enqueue(new Callback<ParseResponseLogicalDoc>() {
            @Override
            public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
//                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getBinaryData() != null) {
                        AppUtil.convertBase64ToFileWithOnClick(ActivityVerifAkseptasiPendapatan.this, response.body().getBinaryData(), imageView, response.body().getFileName());
                    } else {
                        AppUtil.notiferror(ActivityVerifAkseptasiPendapatan.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                    }


                } else {
                    AppUtil.notiferror(ActivityVerifAkseptasiPendapatan.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityVerifAkseptasiPendapatan.this, findViewById(android.R.id.content), "Terjadi kesalahan");

                t.printStackTrace();
            }
        });

    }
}
