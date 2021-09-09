package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap.purna;


import android.content.Context;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataLengkap;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class DataLengkapStepAdapterPurna extends AbstractFragmentStepAdapter{
    private String title,approved, gimmick;
    private DataLengkap dataLengkap;

    //    public DataLengkapStepAdapterPurna(@NonNull FragmentManager fm, @NonNull Context context, DataLengkap mdataLengkap) {
//        super(fm, context);
//        dataLengkap = mdataLengkap;
//    }
    public DataLengkapStepAdapterPurna(@NonNull FragmentManager fm, @NonNull Context context, DataLengkap mdataLengkap,String mApproved, String mgimmick) {
        super(fm, context);
        dataLengkap = mdataLengkap;
        approved=mApproved;
        gimmick = mgimmick;
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
                FragmentDataPribadiPurna fragmentDataPribadi = new FragmentDataPribadiPurna(dataLengkap,approved);
                return fragmentDataPribadi;
            case 1:
                FragmentDataAlamatPurna fragmentDataAlamat = new FragmentDataAlamatPurna(dataLengkap,approved);
                return fragmentDataAlamat;
            case 2:
                FragmentDataPekerjaanPurna fragmentDataUsaha = new FragmentDataPekerjaanPurna(dataLengkap,approved, gimmick);
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