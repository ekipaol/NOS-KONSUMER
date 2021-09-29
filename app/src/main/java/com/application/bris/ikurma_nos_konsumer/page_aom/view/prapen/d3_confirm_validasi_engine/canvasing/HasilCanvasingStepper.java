package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.canvasing;

import android.content.Context;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;


import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class HasilCanvasingStepper extends AbstractFragmentStepAdapter {

    private String title;

    public HasilCanvasingStepper(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        switch (position){
            case 0:
                title = "Resume RAC";
                break;
            case 1:
                title = "Resume Fitur";
                break;
            case 2:
                title = "Resume Cek Taspen";
                break;
            default:
                title = "Default Tab";
        }
        return new StepViewModel.Builder(context)
                .setTitle(title)
                .create();
    }

    @Override
    public Step createStep(int position) {
        switch (position) {
            case 0:
                FragmentResumeRAC FragmentResumeRAC = new FragmentResumeRAC();
                return FragmentResumeRAC;
            case 1:
                FragmentResumeFitur FragmentResumeFitur = new FragmentResumeFitur();
                return FragmentResumeFitur;
            case 2:
                FragmentResumeCekTaspen FragmentResumeCekTaspen = new FragmentResumeCekTaspen();
                return FragmentResumeCekTaspen;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
