package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;


import android.content.Context;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanKendaraan;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class StepAdapterAgunanKendaraan extends AbstractFragmentStepAdapter{
    private String title;
    private AgunanKendaraan agunanData;
    private String idAgunan;
    private String loan_type, tp_produk;

    public StepAdapterAgunanKendaraan(@NonNull FragmentManager fm, @NonNull Context context, AgunanKendaraan magunanData, String midAgunan, String mloan_type, String mtp_produk) {
        super(fm, context);
        idAgunan = midAgunan;
        agunanData = magunanData;
        loan_type = mloan_type;
        tp_produk = mtp_produk;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        switch (position){
            case 0:
                title = "Identifikasi Kendaraan";
                break;
            case 1:
                title = "Spesifikasi Jaminan";
                break;
            case 2:
                title = "Detail Kendaraan";
                break;
            case 3:
                title = "Informasi Harga";
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
                FragmentAgunanKendaraan1 fragmentAgunanKendaraan1 = new FragmentAgunanKendaraan1(idAgunan, agunanData);
                return fragmentAgunanKendaraan1;
            case 1:
                FragmentAgunanKendaraan2 fragmentAgunanKendaraan2 = new FragmentAgunanKendaraan2(idAgunan, agunanData);
                return fragmentAgunanKendaraan2;
            case 2:
                FragmentAgunanKendaraan3 fragmentAgunanKendaraan3 = new FragmentAgunanKendaraan3(idAgunan, agunanData);
                return fragmentAgunanKendaraan3;
            case 3:
                FragmentAgunanKendaraan4 fragmentAgunanKendaraan4 = new FragmentAgunanKendaraan4(idAgunan, agunanData);
                return fragmentAgunanKendaraan4;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
