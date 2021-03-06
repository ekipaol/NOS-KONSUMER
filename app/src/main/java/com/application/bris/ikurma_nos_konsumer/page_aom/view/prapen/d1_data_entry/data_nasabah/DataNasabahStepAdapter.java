package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_nasabah;

import android.content.Context;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.application.bris.ikurma_nos_konsumer.model.prapen.DataInstansiDapen;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataNasabahPrapen;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataLengkap;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class DataNasabahStepAdapter extends AbstractFragmentStepAdapter {
    private String title;
    private DataNasabahPrapen dataNasabah;
    private DataInstansiDapen dataInstansi;


    public DataNasabahStepAdapter(@NonNull FragmentManager fm, @NonNull Context context, DataNasabahPrapen mdataLengkap, DataInstansiDapen dataInstansiDapen) {
        super(fm, context);
        dataNasabah = mdataLengkap;
        dataInstansi=dataInstansiDapen;
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
                title = "Data Pensiun";
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
                FragmentDataPribadiPrapen fragmentDataPribadiPrapen = new FragmentDataPribadiPrapen(dataNasabah,"no");
                return fragmentDataPribadiPrapen;
            case 1:
                FragmentDataAlamatPrapen fragmentDataAlamatPrapen = new FragmentDataAlamatPrapen(dataNasabah,"no");
                return fragmentDataAlamatPrapen;
            case 2:
                FragmentDataPensiunPrapen fragmentDataPensiunPrapen = new FragmentDataPensiunPrapen(dataInstansi,"no");
                return fragmentDataPensiunPrapen;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
