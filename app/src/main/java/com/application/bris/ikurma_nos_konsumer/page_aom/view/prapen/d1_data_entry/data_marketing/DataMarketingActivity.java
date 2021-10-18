package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_marketing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoMarketingActivityBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class DataMarketingActivity extends AppCompatActivity implements  View.OnClickListener, GenericListenerOnSelect {

    private PrapenAoMarketingActivityBinding binding;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private List<MGenericModel> dropdownSumberAplikasi=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PrapenAoMarketingActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Marketing");

        allOnClicks();
        isiDropdown();
        disableEditTexts();

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.DialogBackpress(DataMarketingActivity.this);
            }
        });
    }


    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public void onBackPressed() {
        CustomDialog.DialogBackpress(DataMarketingActivity.this);
    }

    private void allOnClicks(){
        endIconOnClick();
        binding.etSumberAplikasi.setOnClickListener(this);
        binding.tfSumberAplikasi.setOnClickListener(this);
        binding.btnCekDataAo.setOnClickListener(this);
        binding.btnCekDataCabang.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //SUMBER APLIKASI
            case R.id.et_sumber_aplikasi:
            case R.id.tf_sumber_aplikasi:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfSumberAplikasi.getLabelText(),dropdownSumberAplikasi,DataMarketingActivity.this);
                break;
            case R.id.btn_simpan_data_marketing:
                Toast.makeText(this, "Menyimpan Data Marketing", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.btn_cek_data_ao:
               binding.etNamaAo.setText("Nama Dummy");
                binding.etNamaAoReferal.setText("Nama AO Referal");
                break;
            case R.id.btn_cek_data_cabang:
                binding.etNamaCabangReferal.setText("Nama Cabang");
                binding.etNamaCabangPembukuan.setText("Nama Cabang Pembukuan");
                break;

            default:break;
        }
    }
    private void endIconOnClick(){
        binding.tfSumberAplikasi.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfSumberAplikasi.getLabelText(),dropdownSumberAplikasi,DataMarketingActivity.this);
//                openDialog(binding.tfSumberAplikasi.getLabelText(),binding.tfSumberAplikasi.getId());
            }
        });

    }

    private void disableEditTexts(){
        binding.etSumberAplikasi.setFocusable(false);
        binding.etMitraFronting.setFocusable(false);
        binding.etNamaAo.setFocusable(false);
        binding.etNamaAoReferal.setFocusable(false);
        binding.etNamaCabangPembukuan.setFocusable(false);
        binding.etNamaCabangReferal.setFocusable(false);

    }

    private void isiDropdown(){
        dropdownSumberAplikasi.add(new MGenericModel("Pegawai Bank/ Reguler","Pegawai Bank/ Reguler"));
        dropdownSumberAplikasi.add(new MGenericModel("Mitra Fronting","Mitra Fronting"));
        dropdownSumberAplikasi.add(new MGenericModel("Eksternal","Eksternal"));
    }

    @Override
    public void onSelect(String title, MGenericModel data) {
        if(title.equalsIgnoreCase(binding.tfSumberAplikasi.getLabelText())){
            binding.etSumberAplikasi.setText(data.getDESC());
            if(data.getDESC().equalsIgnoreCase("mitra fronting")){
                binding.tfMitraFronting.setVisibility(View.VISIBLE);
                binding.tfKodeAoMitraFronting.setVisibility(View.VISIBLE);
            }
            else{
                binding.tfMitraFronting.setVisibility(View.GONE);
                binding.tfKodeAoMitraFronting.setVisibility(View.GONE);
            }
        }
    }
}