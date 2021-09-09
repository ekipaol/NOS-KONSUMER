package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;


import android.content.Context;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanTanahBangunan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.ListJenisBangunan;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import java.util.List;

public class StepAdapterAgunan extends AbstractFragmentStepAdapter{
    private String title;
    private AgunanTanahBangunan agunanTanahBangunan;
    private String idAgunan;
    private String loan_type, tp_produk,idProgram;
    private boolean enableEditText;
    AppPreferences appPreferences;
    List<ListJenisBangunan> listJenisBangunan;


//    public StepAdapterAgunan(@NonNull FragmentManager fm, @NonNull Context context, AgunanTanahBangunan magunanTanahBangunan, String midAgunan, String mloan_type, String mtp_produk) {
//        super(fm, context);
//        idAgunan = midAgunan;
//        agunanTanahBangunan = magunanTanahBangunan;
//        loan_type = mloan_type;
//        tp_produk = mtp_produk;
//    }

    public StepAdapterAgunan(@NonNull FragmentManager fm, @NonNull Context context, AgunanTanahBangunan magunanTanahBangunan, String midAgunan, String mloan_type, String mtp_produk,boolean enableEditText,String idProgram) {
        super(fm, context);
        idAgunan = midAgunan;
        agunanTanahBangunan = magunanTanahBangunan;
        loan_type = mloan_type;
        tp_produk = mtp_produk;
        this.idProgram=idProgram;
        this.enableEditText=enableEditText;
    }

    public StepAdapterAgunan(@NonNull FragmentManager fm, @NonNull Context context, AgunanTanahBangunan magunanTanahBangunan, String midAgunan, String mloan_type, String mtp_produk, boolean enableEditText, String idProgram, List<ListJenisBangunan> listJenisBangunans) {
        super(fm, context);
        idAgunan = midAgunan;
        agunanTanahBangunan = magunanTanahBangunan;
        loan_type = mloan_type;
        tp_produk = mtp_produk;
        this.idProgram=idProgram;
        this.enableEditText=enableEditText;
        this.listJenisBangunan=listJenisBangunans;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        switch (position){
            case 0:
                title = "Identifikasi Tanah";
                break;
            case 1:
                title = "Identifikasi Surat Tanah";
                break;
            case 2:
                title = "Uraian Bangunan";
                break;
            case 3:
                title = "Spesifikasi Bangunan";
                break;
            case 4:
                title = "Data Lingkungan";
                break;
            case 5:
                title = "Hasil Penilaian";
                break;
            case 6:
                title = "Lain-lain";
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
                FragmentAgunan1Identifikasi fragmentAgunan1IdentifikasiDefa = new FragmentAgunan1Identifikasi(idAgunan, agunanTanahBangunan,enableEditText,idProgram);
                return fragmentAgunan1IdentifikasiDefa;
            case 1:
                FragmentAgunan2Surat fragmentAgunan2Surat = new FragmentAgunan2Surat(idAgunan, agunanTanahBangunan,enableEditText);
                return fragmentAgunan2Surat;
            case 2:
                FragmentAgunan3Uraian fragmentAgunan3Uraian = new FragmentAgunan3Uraian(idAgunan, agunanTanahBangunan,enableEditText,idProgram);
                return fragmentAgunan3Uraian;
            case 3:
                FragmentAgunan4Spek fragmentAgunan4Spek = new FragmentAgunan4Spek(idAgunan, agunanTanahBangunan,enableEditText,listJenisBangunan);
                return fragmentAgunan4Spek;
            case 4:
                FragmentAgunan5Lingkungan fragmentAgunan5Lingkungan = new FragmentAgunan5Lingkungan(idAgunan, agunanTanahBangunan,enableEditText);
                return fragmentAgunan5Lingkungan;
            case 5:
                FragmentAgunan6Hasil fragmentAgunan6Hasil = new FragmentAgunan6Hasil(idAgunan, agunanTanahBangunan, loan_type, tp_produk,enableEditText);
                return fragmentAgunan6Hasil;
            case 6:
                FragmentAgunan7Lain fragmentAgunan7Lain = new FragmentAgunan7Lain(idAgunan, agunanTanahBangunan,enableEditText,idProgram);
                return fragmentAgunan7Lain;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
       appPreferences=new AppPreferences(context);

       //kalau status aplikasi dalam pengisian (belum dikirim ke ao silang, maka tab hanya 3)  kecuali flpp
        if(appPreferences.getStatusAplikasiAgunan().equalsIgnoreCase("1")&&enableEditText){
            if(!idProgram.isEmpty()&&idProgram.equalsIgnoreCase("222")){
                return 7;
            }
            else{
                return 3;
            }

        }

        //kalau status aplikasi dikembalikan dari ao appraisal, maka tab hanya 3 kecuali flpp
        else  if(appPreferences.getStatusAplikasiAgunan().equalsIgnoreCase("-34")&&enableEditText){
            return 3;
        }
        //kalau sttaus aplikasi dikembalikan dari pemutus, maka tab hanya 3
        else  if(appPreferences.getStatusAplikasiAgunan().equalsIgnoreCase("-14")&&enableEditText){
            if(!idProgram.isEmpty()&&idProgram.equalsIgnoreCase("222")){
                return 7;
            }
            else{
                return 3;
            }
        }
        //selain itu, tampilkan semua tab, untuk aproved, rejected, dan ao silang
        else{
            return 7;
        }

    }
}
