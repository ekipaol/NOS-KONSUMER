package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_pendapatan;

import android.content.Context;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;


import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class VerifikasiPendapatanStepper extends AbstractFragmentStepAdapter {
    private String title;

    public VerifikasiPendapatanStepper(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        switch (position){
            case 0:
                title = "Resume Pendapatan";
                break;
            case 1:
                title = "Verifikasi Pendapatan";
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
                FragmentDokumenPendapatan FragmentDokumenPendapatan = new FragmentDokumenPendapatan();
                return FragmentDokumenPendapatan;
            case 1:
                FragmentVerifikasiPendapatan FragmentVerifikasiPendapatan = new FragmentVerifikasiPendapatan();
                return FragmentVerifikasiPendapatan;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
