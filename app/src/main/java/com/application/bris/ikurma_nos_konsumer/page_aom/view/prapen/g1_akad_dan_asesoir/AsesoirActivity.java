package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g1_akad_dan_asesoir;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoAsesoirActivityBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoMarketingActivityBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AsesoirActivity extends AppCompatActivity implements GenericListenerOnSelect, View.OnClickListener {

    private PrapenAoAsesoirActivityBinding binding;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private List<MGenericModel> dataDropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PrapenAoAsesoirActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Asesoir");

        allOnClicks();
        disableEditTexts();

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.DialogBackpress(AsesoirActivity.this);
            }
        });
    }

    private void isiDropdown(){

        //belum dipakai, karena dropdown dari service, bukan hardcode
        dataDropdown.add(new MGenericModel("Pembiayaan tetap dilanjutkan","Pembiayaan tetap dilanjutkan"));

    }


    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public void onBackPressed() {
        CustomDialog.DialogBackpress(AsesoirActivity.this);
    }

    private void allOnClicks(){
        endIconOnClick();
        binding.etTanggalSuratKeputusan.setOnClickListener(this);
        binding.tfTanggalSuratKeputusan.setOnClickListener(this);
        binding.etTanggalSuratKuasa.setOnClickListener(this);
        binding.tfTanggalSuratKuasa.setOnClickListener(this);
        binding.btnSimpanDataAsesoir.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //SUMBER APLIKASI
            case R.id.et_tanggal_surat_kuasa:
            case R.id.tf_tanggal_surat_kuasa:
               AppUtil.genericCalendarDialog(AsesoirActivity.this,binding.etTanggalSuratKuasa);
                break;
            case R.id.et_tanggal_surat_keputusan:
            case R.id.tf_tanggal_surat_keputusan:
                AppUtil.genericCalendarDialog(AsesoirActivity.this,binding.etTanggalSuratKeputusan);
                break;
            case R.id.et_pejabat_penandatangan:
            case R.id.tf_pejabat_penandatangan:

                break;
            case R.id.btn_simpan_data_asesoir:
                Toast.makeText(this, "clicking simpan", Toast.LENGTH_SHORT).show();

                break;

            default:break;
        }
    }
    private void endIconOnClick(){
        binding.tfTanggalSuratKuasa.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.genericCalendarDialog(AsesoirActivity.this,binding.etTanggalSuratKuasa);
            }
        });

        binding.tfTanggalSuratKeputusan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.genericCalendarDialog(AsesoirActivity.this,binding.etTanggalSuratKeputusan);
            }
        });

        binding.tfPejabatPenandatangan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void disableEditTexts(){
        binding.etTanggalSuratKuasa.setFocusable(false);
        binding.etTanggalSuratKeputusan.setFocusable(false);
        binding.etAtasNamaRekening.setFocusable(false);
        binding.etNomorCif.setFocusable(false);
        binding.etPejabatPenandatangan.setFocusable(false);

    }


    @Override
    public void onSelect(String title, MGenericModel data) {
        //masi belom dipake karena dropdownnya dari serpis
    }
}