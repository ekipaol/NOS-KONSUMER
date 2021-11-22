package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.canvasing;

import android.content.Context;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;


import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseFitur;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseHasilRAC;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseRAC;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseTaspen;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import java.util.ArrayList;
import java.util.List;

public class HasilCanvasingStepper extends AbstractFragmentStepAdapter {

    private String title, aRAC,aFitur;
    private List<MparseResponseRAC> lRAC = new ArrayList<MparseResponseRAC>();
    private List<MparseResponseFitur> LFitur = new ArrayList<MparseResponseFitur>();
    private List<MparseResponseTaspen> LTaspen = new ArrayList<MparseResponseTaspen>();
    private MparseResponseHasilRAC objRAC;

    public HasilCanvasingStepper(@NonNull FragmentManager fm, @NonNull Context context, List<MparseResponseRAC> listRac, String aprroveRAC, MparseResponseHasilRAC objHasilRAc, List<MparseResponseFitur> dataFitur,String hasilFitur ,List<MparseResponseTaspen> dataTaspen) {
        super(fm, context);
        aRAC = aprroveRAC;
        lRAC = listRac;
        objRAC = objHasilRAc;
        LFitur = dataFitur;
        aFitur = hasilFitur;
        LTaspen = dataTaspen;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        switch (position) {
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
                FragmentResumeRAC FragmentResumeRAC = new FragmentResumeRAC(lRAC, aRAC, objRAC);
                return FragmentResumeRAC;
            case 1:
                FragmentResumeFitur FragmentResumeFitur = new FragmentResumeFitur(LFitur,aFitur);
                return FragmentResumeFitur;
            case 2:
                FragmentResumeCekTaspen FragmentResumeCekTaspen = new FragmentResumeCekTaspen(LTaspen);
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
