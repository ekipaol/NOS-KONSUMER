package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap;

import android.content.Context;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataLengkap;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class DatalengkapStepAdapter extends AbstractFragmentStepAdapter{
    private String title,approved;
    private DataLengkap dataLengkap;

//    public DatalengkapStepAdapter(@NonNull FragmentManager fm, @NonNull Context context, DataLengkap mdataLengkap) {
//        super(fm, context);
//        dataLengkap = mdataLengkap;
//    }
    public DatalengkapStepAdapter(@NonNull FragmentManager fm, @NonNull Context context, DataLengkap mdataLengkap,String mApproved) {
        super(fm, context);
        dataLengkap = mdataLengkap;
        approved=mApproved;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        switch (position){
            case 0:
                title = "Data Pribadi";
                break;
            case 1:
                title = "Data Alamat";
                break;
            case 2:
                title = "Data Pekerjaan";
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
                FragmentDataPribadi fragmentDataPribadi = new FragmentDataPribadi(dataLengkap,approved);
                return fragmentDataPribadi;
            case 1:
                FragmentDataAlamat fragmentDataAlamat = new FragmentDataAlamat(dataLengkap,approved);
                return fragmentDataAlamat;
            case 2:
                FragmentDataPekerjaan fragmentDataUsaha = new FragmentDataPekerjaan(dataLengkap,approved);
                return fragmentDataUsaha;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}