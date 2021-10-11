package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.canvasing;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityHasilCanvasingBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap.OnNavigationBarListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_marketing.DataMarketingActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class HasilCanvasingActivity extends AppCompatActivity implements StepperLayout.StepperListener, OnNavigationBarListener,  View.OnClickListener {
    ActivityHasilCanvasingBinding binding;
    private int startingStepPosition;
    private static final String CURRENT_STEP_POSITION_KEY = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHasilCanvasingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Hasil Canvasing");
        startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
//        loadDataLengkap();


        //testing code, comment when done
        binding.stepperlayout.setAdapter(new HasilCanvasingStepper(getSupportFragmentManager(), HasilCanvasingActivity.this), startingStepPosition );
        binding.stepperlayout.setListener(HasilCanvasingActivity.this);
        //end of testing

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.DialogBackpressSaved(HasilCanvasingActivity.this);
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
        CustomDialog.DialogBackpressSaved(this);

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
        if(val){
            finish();
        }
    }


    public void confirm(boolean val) {

    }

    @Override
    public void onClick(View v) {

    }
}
