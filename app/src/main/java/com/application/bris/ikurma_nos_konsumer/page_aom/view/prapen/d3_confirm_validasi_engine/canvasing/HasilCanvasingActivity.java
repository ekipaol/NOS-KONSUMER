package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.canvasing;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqInquery;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseFitur;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseHasilRAC;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseRAC;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseTaspen;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityHasilCanvasingBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap.OnNavigationBarListener;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HasilCanvasingActivity extends AppCompatActivity implements StepperLayout.StepperListener, OnNavigationBarListener, View.OnClickListener {
    ActivityHasilCanvasingBinding binding;
    private int startingStepPosition;
    private static final String CURRENT_STEP_POSITION_KEY = "position";
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private List<MparseResponseRAC> dataRAC = new ArrayList<MparseResponseRAC>();
    private List<MparseResponseFitur> dataFitur = new ArrayList<MparseResponseFitur>();
    private MparseResponseTaspen dataTaspen = new MparseResponseTaspen();
    private MparseResponseHasilRAC datahasilRAC = new MparseResponseHasilRAC();
    private String rRAC, rTaspen, rFitur, rhasilRAC;
    String statusId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHasilCanvasingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Hasil Canvasing");
        startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        statusId=getIntent().getStringExtra("statusId");

        initData();

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initData() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqInquery req = new ReqInquery();
        req.setUID(String.valueOf(appPreferences.getUid()));
        req.setApplicationId(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
        Call<ParseResponseAgunan> call;
         call = apiClientAdapter.getApiInterface().inquiryCanvassingD3(req);

        if(statusId.equalsIgnoreCase("d.3")){
            call = apiClientAdapter.getApiInterface().inquiryCanvassingD3(req);
        }
        else   if(statusId.equalsIgnoreCase("d.5")){
            call = apiClientAdapter.getApiInterface().InquiryHasilCanvasingD5(req);
        }
        else   if(statusId.equalsIgnoreCase("d.6")){
            call = apiClientAdapter.getApiInterface().InquiryHasilCanvasingD6(req);
        }
        else{
            call = apiClientAdapter.getApiInterface().inquiryCanvassingD3(req);
        }
        call.enqueue(new Callback<ParseResponseAgunan>() {
            @Override
            public void onResponse(Call<ParseResponseAgunan> call, Response<ParseResponseAgunan> response) {
                try {
                    if (response.isSuccessful()) {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            Gson gson = new Gson();
                            String aRac = "", aFitur = "";
                            if (response.body().getData().get("ResponseTaspenPrapen") != null) {



                                rTaspen = response.body().getData().get("ResponseTaspenPrapen").getAsJsonObject().toString();
                                Type typeTaspen = new TypeToken<MparseResponseTaspen>() {
                                }.getType();
                                dataTaspen = gson.fromJson(rTaspen, typeTaspen);

                                if (response.body().getData().get("NoTaspen") != null) {
                                    dataTaspen.setNomorTaspen(response.body().getData().get("NoTaspen").getAsString());
                                }
                            }
                            if (response.body().getData().get("ResponseRAC") != null) {
                                rRAC = response.body().getData().get("ResponseRAC").toString();
                                Type type = new TypeToken<List<MparseResponseRAC>>() {
                                }.getType();
                                dataRAC = gson.fromJson(rRAC, type);
                            }
                            if (response.body().getData().get("HasilChecking") != null) {
                                rhasilRAC = response.body().getData().get("HasilChecking").getAsJsonObject().toString();
                                datahasilRAC = gson.fromJson(rhasilRAC, MparseResponseHasilRAC.class);
                            }
                            if (response.body().getData().get("StatusRAC") != null) {
                                aRac = response.body().getData().get("StatusRAC").getAsString();
                            }
                            if (response.body().getData().get("ResponseFitur") != null) {
                                rFitur = response.body().getData().get("ResponseFitur").getAsJsonArray().toString();
                                Type typeFitur = new TypeToken<List<MparseResponseFitur>>() {
                                }.getType();
                                dataFitur = gson.fromJson(rFitur, typeFitur);
                            }
                            if (response.body().getData().get("StatusFitur") != null) {
                                aFitur = response.body().getData().get("StatusFitur").getAsString();
                            }
                            binding.stepperlayout.setAdapter(new HasilCanvasingStepper(getSupportFragmentManager(), HasilCanvasingActivity.this, dataRAC, aRac, datahasilRAC, dataFitur, aFitur, dataTaspen));
                            binding.stepperlayout.setListener(HasilCanvasingActivity.this);

                        } else {
                            AppUtil.notiferror(HasilCanvasingActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        binding.loading.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(HasilCanvasingActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseAgunan> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(HasilCanvasingActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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
     finish();

    }

    @Override
    public void onCompleted(View completeButton) {
//        Intent intent=new Intent(this, DataMarketingActivity.class);
//        startActivity(intent);
        finish();
    }

    @Override
    public void onError(VerificationError verificationError) {
    }

    @Override
    public void onStepSelected(int newStepPosition) {
    }

    @Override
    public void onReturn() {
        finish();
    }

    @Override
    public void onChangeEndButtonsEnabled(boolean enabled) {
        binding.stepperlayout.setNextButtonVerificationFailed(!enabled);
        binding.stepperlayout.setCompleteButtonVerificationFailed(!enabled);
    }


    public void success(boolean val) {
        if (val) {
            finish();
        }
    }


    public void confirm(boolean val) {

    }

    @Override
    public void onClick(View v) {

    }
}
