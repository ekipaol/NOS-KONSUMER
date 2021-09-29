package com.application.bris.ikurma_nos_konsumer.page_data_nasabah;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityVerifikasiPendapatanBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.FragmentVerifikasiPendapatanBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap.OnNavigationBarListener;

import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_marketing.DataMarketingActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class ActivityVerifikasiPendapatan extends AppCompatActivity implements StepperLayout.StepperListener, OnNavigationBarListener, ConfirmListener, View.OnClickListener, CameraListener {

    private static final String CURRENT_STEP_POSITION_KEY = "position";
    private ActivityVerifikasiPendapatanBinding binding;
    private FragmentVerifikasiPendapatanBinding binding2;
    private int startingStepPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifikasiPendapatanBinding.inflate(getLayoutInflater());
        binding2 = FragmentVerifikasiPendapatanBinding.inflate(getLayoutInflater());
        binding2.getRoot();
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

        validasi();
//        Intent intent=new Intent(ActivityVerifikasiPendapatan.this, DataMarketingActivity.class);
//        startActivity(intent);
    }
    private boolean validasi() {
        if (binding2.etManfaatPensiun.getText().toString().trim().isEmpty() || binding2.etManfaatPensiun.getText().toString().trim().equalsIgnoreCase("0")) {
            binding2.tfManfaatPensiun.setError(binding2.tfManfaatPensiun.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityVerifikasiPendapatan.this, findViewById(android.R.id.content), binding2.tfManfaatPensiun.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding2.etNominalGaji.getText().toString().trim().isEmpty() || binding2.etNominalGaji.getText().toString().trim().equalsIgnoreCase("0")){
            binding2.tfManfaatPensiun.setError(binding2.tfManfaatPensiun.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityVerifikasiPendapatan.this, findViewById(android.R.id.content), binding2.tfManfaatPensiun.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding2.etTanggalGaji.getText().toString().trim().isEmpty() || binding2.etTanggalGaji.getText().toString().trim().equalsIgnoreCase(" ")){
            binding2.tfTanggalGaji.setError(binding2.tfTanggalGaji.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityVerifikasiPendapatan.this, findViewById(android.R.id.content), binding2.tfTanggalGaji.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        }else if (binding2.etVerifikasiGajiTercermin.getText().toString().trim().isEmpty() || binding2.etVerifikasiGajiTercermin.getText().toString().trim().equalsIgnoreCase(" ")){
            binding2.tfVerifikasiGajiTercermin.setError(binding2.tfVerifikasiGajiTercermin.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityVerifikasiPendapatan.this, findViewById(android.R.id.content), binding2.tfVerifikasiGajiTercermin.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        }else if (binding2.etNorekTercermin.getText().toString().trim().isEmpty() || binding2.etNorekTercermin.getText().toString().trim().equalsIgnoreCase(" ")){
            binding2.tfNorekTercermin.setError(binding2.tfNorekTercermin.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityVerifikasiPendapatan.this, findViewById(android.R.id.content), binding2.tfNorekTercermin.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding2.etVerifikasiGaji.getText().toString().trim().isEmpty() || binding2.etVerifikasiGaji.getText().toString().trim().equalsIgnoreCase(" ")){
            binding2.tfVerifikasiGaji.setError(binding2.tfVerifikasiGaji.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityVerifikasiPendapatan.this, findViewById(android.R.id.content), binding2.tfVerifikasiGaji.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        }else if (binding2.etVerifikasiGaji.getText().toString().trim().isEmpty() || binding2.etVerifikasiGaji.getText().toString().trim().equalsIgnoreCase("0")){
            binding2.tfVerifikasiGaji.setError(binding2.tfVerifikasiGaji.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityVerifikasiPendapatan.this, findViewById(android.R.id.content), binding2.tfVerifikasiGaji.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        }else if (binding2.etVerifikasiTunjangan.getText().toString().trim().isEmpty() || binding2.etVerifikasiTunjangan.getText().toString().trim().equalsIgnoreCase(" ")){
            binding2.tfVerifikasiTunjangan.setError(binding2.tfVerifikasiTunjangan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityVerifikasiPendapatan.this, findViewById(android.R.id.content), binding2.tfVerifikasiTunjangan.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        }else if (binding2.etCatatanVerifikasi1.getText().toString().trim().isEmpty() || binding2.etCatatanVerifikasi1.getText().toString().trim().equalsIgnoreCase(" ")){
            binding2.tfCatatanVerifikasi1.setError(binding2.tfCatatanVerifikasi1.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityVerifikasiPendapatan.this, findViewById(android.R.id.content), binding2.tfCatatanVerifikasi1.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else{
            AppUtil.notiferror(ActivityVerifikasiPendapatan.this, findViewById(android.R.id.content), "Field Telah Terisi Penuh");
            return false;
        }
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
