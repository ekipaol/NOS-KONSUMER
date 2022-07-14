package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.canvasing;

import android.content.Context;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;


import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseFitur;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseHasilRAC;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseRAC;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseTaspen;
import com.google.gson.JsonObject;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import java.util.ArrayList;
import java.util.List;

public class HasilCanvasingStepper extends AbstractFragmentStepAdapter {

    private String title, aRAC,aFitur;
    private JsonObject JsonData;
    private List<MparseResponseRAC> lRAC = new ArrayList<MparseResponseRAC>();
    private List<MparseResponseFitur> LFitur = new ArrayList<MparseResponseFitur>();
    private MparseResponseTaspen LTaspen = new MparseResponseTaspen();
    private MparseResponseHasilRAC objRAC;

    public HasilCanvasingStepper(@NonNull FragmentManager fm, @NonNull Context context, List<MparseResponseRAC> listRac, String aprroveRAC, MparseResponseHasilRAC objHasilRAc, List<MparseResponseFitur> dataFitur,String hasilFitur ,MparseResponseTaspen dataTaspen,JsonObject jsonData) {
        super(fm, context);
        aRAC = aprroveRAC;
        lRAC = listRac;
        objRAC = objHasilRAc;
        LFitur = dataFitur;
        aFitur = hasilFitur;
        LTaspen = dataTaspen;
        JsonData = jsonData;
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
            case 3:
                title = "Data IDE";
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
                return new FragmentResumeRAC(lRAC, aRAC, objRAC);
            case 1:
                return new FragmentResumeFitur(LFitur,aFitur);
            case 2:
                return new FragmentResumeCekTaspen(LTaspen);
            case 3:
                return new FragmentResumeIDE(JsonData);
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
