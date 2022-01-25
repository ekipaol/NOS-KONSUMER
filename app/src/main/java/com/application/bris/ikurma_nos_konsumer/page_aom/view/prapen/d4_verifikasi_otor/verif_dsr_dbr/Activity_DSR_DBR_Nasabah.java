package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_dsr_dbr;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDocument;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqInquery;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseTotalKualitasPembiayaan;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityDsrDbrNasabahBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.dokumen_pendapatan.ActivityDokumenPendapatan;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.RoundingMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_DSR_DBR_Nasabah extends AppCompatActivity implements View.OnClickListener {
    private ActivityDsrDbrNasabahBinding binding;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private MparseTotalKualitasPembiayaan DPDSR;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        binding = ActivityDsrDbrNasabahBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        numberText();
        disableText();
        customToolbar();
        allOnClicks();
        initialize();
    }

    private void initialize() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqInquery req = new ReqInquery();
        req.setUID(String.valueOf(appPreferences.getUid()));
        req.setApplicationId(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
        Call<ParseResponseAgunan> call = apiClientAdapter.getApiInterface().sendInquiryTotalKualitasPemb(req);
        call.enqueue(new Callback<ParseResponseAgunan>() {
            @Override
            public void onResponse(Call<ParseResponseAgunan> call, Response<ParseResponseAgunan> response) {
                try {
                    if (response.isSuccessful()) {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            Gson gson = new Gson();
                            String listDataString = "";
                            if (response.body().getData() != null) {
                                listDataString = response.body().getData().toString();
                                DPDSR = gson.fromJson(listDataString, MparseTotalKualitasPembiayaan.class);
                                try {
                                    binding.etSisaPendapatanGajiAktif.setText(String.valueOf(DPDSR.getSisaPendapatanGajiAktif().setScale(2)));
                                    binding.etSisaPendapatanManfaatPensiun.setText(String.valueOf(DPDSR.getSisaPendapatanManfaatPensiun().setScale(2)));

                                    if(DPDSR.getTotalAngsPembEksNotLunasDBR()!=null){
                                        binding.etTotalAngsPembEksNotlunasDbr.setText(String.valueOf(DPDSR.getTotalAngsPembEksNotLunasDBR().setScale(2)));
                                    }
                                    if(DPDSR.getTotalAngsPembEksNotLunasDSR()!=null){
                                        binding.etTotalAngsPembEksNotlunasDsr.setText(String.valueOf(DPDSR.getTotalAngsPembEksNotLunasDSR().setScale(2)));
                                    }
                                    binding.etGajiAktifDigunakan.setText(DPDSR.getGajiAktifDigunakan());
                                    binding.etManfaatPensiunDigunakan.setText(DPDSR.getManfaatPensiunDigunakan());
                                    binding.etSisaGajiAktif.setText(DPDSR.getSisaGajiAktif());
                                    binding.etSisaManfaatPensiun.setText(DPDSR.getSisaManfaatPensiun());
                                    binding.etMaksimalAngsuranBulananGajiAktif.setText(DPDSR.getMaksimalAngsuranBulananGajiAktif());
                                    binding.etMaksimalAngsuranManfaatPensiun.setText(DPDSR.getMaksimalAngsuranManfaatPensiun());
                                    binding.etKetentuanGajiAktif.setText(DPDSR.getKetentuanGajiAktif());
                                    binding.etKetentuanManfaatPensiun.setText(DPDSR.getKetentuanManfaatPensiun());
                                    binding.etCatatanVerifikasi.setText(DPDSR.getCatatanVerifikasi());
                                    binding.etStatusPayroll.setText(DPDSR.getTreatmentPembayaran());
                                    try {
                                        //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik
                                        if (DPDSR.getFileName().substring(DPDSR.getFileName().length() - 3, DPDSR.getFileName().length()).equalsIgnoreCase("pdf")) {
                                            DPDSR.setFileName(DPDSR.getFileName());
                                            AppUtil.convertBase64ToFileWithOnClick(Activity_DSR_DBR_Nasabah.this, DPDSR.getImg(), binding.ivDokumen1, DPDSR.getFileName());
                                        } else {
                                            DPDSR.setFileName(DPDSR.getFileName());
                                            AppUtil.convertBase64ToImage(DPDSR.getImg(), binding.ivDokumen1);
                                        }

                                    } catch (Exception e) {
                                        AppUtil.logSecure("error setdata", e.getMessage());
                                        e.printStackTrace();
                                    }
                                } catch (Exception e) {
                                    AppUtil.logSecure("error setdata", e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            AppUtil.notiferror(Activity_DSR_DBR_Nasabah.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(Activity_DSR_DBR_Nasabah.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseAgunan> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(Activity_DSR_DBR_Nasabah.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void sendData() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqInquery req = new ReqInquery();
        req.setUID(String.valueOf(String.valueOf(appPreferences.getUid())));
        req.setApplicationId(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
        Call<ParseResponseAgunan> call = apiClientAdapter.getApiInterface().sendUpdateTotalKualitasPemb(req);
        call.enqueue(new Callback<ParseResponseAgunan>() {
            @Override
            public void onResponse(Call<ParseResponseAgunan> call, Response<ParseResponseAgunan> response) {
                try {
                    if (response.isSuccessful()) {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            Gson gson = new Gson();

                        } else {
                            AppUtil.notiferror(Activity_DSR_DBR_Nasabah.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(Activity_DSR_DBR_Nasabah.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseAgunan> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(Activity_DSR_DBR_Nasabah.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void allOnClicks() {
//        binding.btnSend.setOnClickListener(this);
    }

    private void disableText() {
        binding.etStatusPayroll.setFocusable(false);
        binding.etSisaPendapatanGajiAktif.setFocusable(false);
        binding.etCatatanVerifikasi.setFocusable(false);
        binding.etSisaPendapatanManfaatPensiun.setFocusable(false);
        binding.etTotalAngsPembEksNotlunasDbr.setFocusable(false);
        binding.etTotalAngsPembEksNotlunasDsr.setFocusable(false);
        binding.etGajiAktifDigunakan.setFocusable(false);
        binding.etManfaatPensiunDigunakan.setFocusable(false);
        binding.etSisaGajiAktif.setFocusable(false);
        binding.etSisaManfaatPensiun.setFocusable(false);
        binding.etMaksimalAngsuranBulananGajiAktif.setFocusable(false);
        binding.etMaksimalAngsuranManfaatPensiun.setFocusable(false);
        binding.etKetentuanGajiAktif.setFocusable(false);
        binding.etKetentuanManfaatPensiun.setFocusable(false);
        binding.btnDokumen1.setVisibility(View.GONE);
    }

    private void numberText() {
        binding.etSisaPendapatanGajiAktif.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etSisaPendapatanGajiAktif));
        binding.etSisaPendapatanManfaatPensiun.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etSisaPendapatanManfaatPensiun));
        binding.etTotalAngsPembEksNotlunasDbr.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalAngsPembEksNotlunasDbr));
        binding.etTotalAngsPembEksNotlunasDsr.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalAngsPembEksNotlunasDsr));
        binding.etMaksimalAngsuranManfaatPensiun.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etMaksimalAngsuranManfaatPensiun));
        binding.etMaksimalAngsuranBulananGajiAktif.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etMaksimalAngsuranBulananGajiAktif));
    }

    public void customToolbar() {
        binding.toolbarNosearch.tvPageTitle.setText("Perhitungan DBR DSR");

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
            case R.id.ll_btn_send:
                AppUtil.disableEditTexts(binding.getRoot());
                finish();
                break;
        }
    }
}
