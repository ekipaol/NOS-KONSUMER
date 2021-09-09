package com.application.bris.ikurma_nos_konsumer.view.corelayout.activation;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.general.Activation;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.baseapp.RouteApp;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.Constants;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.login.LoginActivity;
import com.google.gson.JsonObject;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivity extends AppCompatActivity implements StepperLayout.StepperListener {

    @BindView(R.id.stepperlayout)
    StepperLayout stepperlayout;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    private static final String CURRENT_STEP_POSITION_KEY = "position";
    private int startingStepPosition;
    private IntentResult intentResult;
    private ApiClientAdapter apiClientAdapter;
    private RouteApp routeApp;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this, 0);
        routeApp = new RouteApp(this);
        appPreferences = new AppPreferences(this);
        startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
        stepperlayout.setAdapter(new WelcomeStepAdapter(getSupportFragmentManager(), WelcomeActivity.this), startingStepPosition );
        stepperlayout.setListener(WelcomeActivity.this);
        backgroundStatusBar();
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void openQRScanner(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setCaptureActivity(ScanActivity.class).initiateScan();
    }

    @Override
    public void onCompleted(View view) {
        openQRScanner();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String qrcontent = "";
        if (intentResult != null) {
            if (intentResult.getContents() != null){
                qrcontent = intentResult.getContents();
                processScan(qrcontent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void processScan(String code){
        loading.setVisibility(View.VISIBLE);
        Activation activationRequest = new Activation(code, getDeviceId(), getDeviceName(), "BRISI_KONSUMER");
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().activation(activationRequest);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        setPrefActivation(response.body().getData());
                        AppUtil.notifsuccess(WelcomeActivity.this, findViewById(android.R.id.content), "Aktivasi Sukses, Enjoy I-KURMA");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                routeApp.openActivity(LoginActivity.class);
                            }
                        }, 1000);
                    } else {
                        AppUtil.notiferror(WelcomeActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                } else {
                    Error error = ParseResponseError.confirmEror(response.errorBody());
                    AppUtil.notiferror(WelcomeActivity.this, findViewById(android.R.id.content), error.getMessage());
                }
            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(WelcomeActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void setPrefActivation(JsonObject data){
        appPreferences.setIsActivated("1");
        appPreferences.setUsername(AppUtil.encrypt(data.get("username").getAsString()));
        appPreferences.setNama(AppUtil.encrypt(data.get("namaLengkap").getAsString()));
    }

    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int i) {

    }

    @Override
    public void onReturn() {

    }

    private String getDeviceId() {
        HashMap<String, String> deviceInfo = AppUtil.getDeviceInfo(this);
        String deviceId = deviceInfo.get(Constants.DEVICE_ID);
        return deviceId;
    }

    private String getDeviceName() {
        HashMap<String, String> deviceInfo = AppUtil.getDeviceInfo(this);
        String deviceName = deviceInfo.get(Constants.DEVICE_NAME);
        return deviceName;
    }
}
