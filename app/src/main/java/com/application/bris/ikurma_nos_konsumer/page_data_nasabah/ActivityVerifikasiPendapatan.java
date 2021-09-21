package com.application.bris.ikurma_nos_konsumer.page_data_nasabah;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryDataLengkap;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.DataLengkapPojo;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityDataNasabahPrapenBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityVerifikasiPendapatanBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataLengkap;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap.OnNavigationBarListener;

import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_marketing.DataMarketingActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityVerifikasiPendapatan extends AppCompatActivity implements StepperLayout.StepperListener, OnNavigationBarListener, ConfirmListener, View.OnClickListener, CameraListener {

    private static final String CURRENT_STEP_POSITION_KEY = "position";
    private ActivityVerifikasiPendapatanBinding binding;
    private int startingStepPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifikasiPendapatanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Entry");
        startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
//        loadDataLengkap();


        //testing code, comment when done
        binding.stepperlayout.setAdapter(new VerifikasiPendapatanStepper(getSupportFragmentManager(), ActivityVerifikasiPendapatan.this), startingStepPosition );
        binding.stepperlayout.setListener(ActivityVerifikasiPendapatan.this);
        //end of testing

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.DialogBackpressSaved(ActivityVerifikasiPendapatan.this);
            }
        });
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }


    @Override
    public void onBackPressed() {
        CustomDialog.DialogBackpressSaved(ActivityVerifikasiPendapatan.this);

    }

    @Override
    public void onCompleted(View completeButton) {
        Intent intent=new Intent(ActivityVerifikasiPendapatan.this, DataMarketingActivity.class);
        startActivity(intent);
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

    @Override
    public void success(boolean val) {
        if(val){
            finish();
        }
    }

    @Override
    public void confirm(boolean val) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSelectMenuCamera(String idMenu) {

    }
}
