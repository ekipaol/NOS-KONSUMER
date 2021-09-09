package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap.kpr;


import android.content.Context;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataLengkap;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataRumahFlpp;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.JenisPekerjaan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListBidangPekerjaan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListJenisKPR;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import java.util.List;

public class DataLengkapKprStepAdapter extends AbstractFragmentStepAdapter{
    private String title,approved;
    private DataLengkap dataLengkap;
    private List<MListBidangPekerjaan> listBidangPekerjaan;
    private List<MListJenisKPR> listJenisKpr;
    private List<JenisPekerjaan> listJenisPekerjaan;
    private List<DataRumahFlpp> listRumahFlpp;

    //    public DatalengkapStepAdapter(@NonNull FragmentManager fm, @NonNull Context context, DataLengkap mdataLengkap) {
//        super(fm, context);
//        dataLengkap = mdataLengkap;
//    }
    public DataLengkapKprStepAdapter(@NonNull FragmentManager fm, @NonNull Context context, DataLengkap mdataLengkap,String mApproved) {
        super(fm, context);
        dataLengkap = mdataLengkap;
        approved=mApproved;
    }


    public DataLengkapKprStepAdapter(@NonNull FragmentManager fm, @NonNull Context context, DataLengkap mdataLengkap, String mApproved, List<MListBidangPekerjaan> bidangPekerjaans,List<MListJenisKPR> jenisKpr,List<JenisPekerjaan> jenisPekerjaan) {
        super(fm, context);
        dataLengkap = mdataLengkap;
        approved=mApproved;
        this.listBidangPekerjaan=bidangPekerjaans;
        this.listJenisKpr=jenisKpr;
        this.listJenisPekerjaan=jenisPekerjaan;
    }

    public DataLengkapKprStepAdapter(@NonNull FragmentManager fm, @NonNull Context context, DataLengkap mdataLengkap, String mApproved, List<MListBidangPekerjaan> bidangPekerjaans,List<MListJenisKPR> jenisKpr,List<JenisPekerjaan> jenisPekerjaan,List<DataRumahFlpp> listRumahFlpp) {
        super(fm, context);
        dataLengkap = mdataLengkap;
        approved=mApproved;
        this.listBidangPekerjaan=bidangPekerjaans;
        this.listJenisKpr=jenisKpr;
        this.listJenisPekerjaan=jenisPekerjaan;
        this.listRumahFlpp=listRumahFlpp;
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
                //list rumahflpp ga dipake di halaman ini, tapi digunakan sebagai indikator aja apakah pembiayaannya flpp atau bukan
                FragmentDataPribadiKpr fragmentDataPribadi = new FragmentDataPribadiKpr(dataLengkap,approved,listRumahFlpp);
                return fragmentDataPribadi;
            case 1:
                FragmentDataAlamatKpr fragmentDataAlamat = new FragmentDataAlamatKpr(dataLengkap,approved);
                return fragmentDataAlamat;
            case 2:
                FragmentDataPekerjaanKpr fragmentDataUsaha = new FragmentDataPekerjaanKpr(dataLengkap,approved,listBidangPekerjaan,listJenisKpr,listJenisPekerjaan,listRumahFlpp);
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
