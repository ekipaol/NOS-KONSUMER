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
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqInquery;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityDsrDbrNasabahBinding ;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        binding = ActivityDsrDbrNasabahBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        numberText();
        onchangeText();
        disableText();
        customToolbar();
        allOnClicks();
        initialize();
    }

    private void initialize(){
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

    private void sendData(){
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

    private void allOnClicks(){
        binding.btnSend.setOnClickListener(this);
    }

    private void disableText(){
        binding.etAngsuranPinjamanEksisting.setFocusable(false);
        binding.etSisapenghasilan.setFocusable(false);
        binding.etSisaDsrDbr.setFocusable(false);
        binding.etMaxAngsuran.setFocusable(false);
        binding.etDsrDbr.setFocusable(false);
        binding.etKetentuan.setFocusable(false);
    }

    private void onchangeText(){
        binding.etAngsuranPinjamanEksisting.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                BigDecimal verangsuran = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString( binding.etAngsuranPinjamanEksisting.getText().toString()));
                BigDecimal vertotverpendapatan = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString("10000"));
                BigDecimal verparamProduk= new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString("10000"));
                BigDecimal sisaPendapatan  = (vertotverpendapatan.subtract(verangsuran));
                BigDecimal dsrNasabah = (verangsuran.divide(vertotverpendapatan, 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal(1)));
                BigDecimal sisaDsr = (verparamProduk.subtract(sisaPendapatan));
                BigDecimal max_angsuran = (vertotverpendapatan.multiply(sisaDsr));
                binding.etSisapenghasilan.setText(String.valueOf(sisaPendapatan));
                binding.etDsrDbr.setText(String.valueOf(dsrNasabah));
                binding.etSisaDsrDbr.setText(String.valueOf(sisaDsr));
                binding.etMaxAngsuran.setText(String.valueOf(max_angsuran));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void numberText(){
        binding.etSisapenghasilan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etSisapenghasilan));
        binding.etAngsuranPinjamanEksisting.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etAngsuranPinjamanEksisting));
        binding.etMaxAngsuran.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etMaxAngsuran));
        binding.etDsrDbr.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etDsrDbr));
        binding.etSisaDsrDbr.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etSisaDsrDbr));
    }

    public void customToolbar(){
        binding.toolbarNosearch.tvPageTitle.setText("Perhitungan DBR DSR");

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog.DialogBackpress(Activity_DSR_DBR_Nasabah.this);
            }
        });
    }

    @Override
    public void onBackPressed() {
        CustomDialog.DialogBackpress(Activity_DSR_DBR_Nasabah.this);
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
