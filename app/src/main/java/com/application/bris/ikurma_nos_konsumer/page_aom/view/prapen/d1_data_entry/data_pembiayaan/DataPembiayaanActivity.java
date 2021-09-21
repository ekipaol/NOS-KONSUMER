package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_pembiayaan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityDataPembiayaanBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;

import java.util.ArrayList;
import java.util.List;

public class DataPembiayaanActivity extends AppCompatActivity implements  View.OnClickListener, GenericListenerOnSelect {

    private PrapenAoActivityDataPembiayaanBinding binding;
    private List<MGenericModel> dataDropdownMemilikiAset =new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PrapenAoActivityDataPembiayaanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Pembiayaan");

        allOnClicks();
        disableEditTexts();
        isiDropdown();
        allOnChange();

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.DialogBackpress(DataPembiayaanActivity.this);
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
        CustomDialog.DialogBackpress(DataPembiayaanActivity.this);
    }

    private void allOnClicks(){
        endIconOnClick();
        binding.etMemilikiAset.setOnClickListener(this);
        binding.tfMemilikiAset.setOnClickListener(this);
        binding.btnSimpanDataPembiayaan.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            // Memiliki aset
            case R.id.et_memiliki_aset:
            case R.id.tf_memiliki_aset:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfMemilikiAset.getLabelText(), dataDropdownMemilikiAset, DataPembiayaanActivity.this);
                break;
            case R.id.btn_simpan_data_pembiayaan:
                Toast.makeText(this, "Nit Not, menyimpan data pembiayaan", Toast.LENGTH_SHORT).show();

            default:break;
        }
    }
    private void endIconOnClick(){
        binding.tfMemilikiAset.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfMemilikiAset.getLabelText(), dataDropdownMemilikiAset, DataPembiayaanActivity.this);
            }
        });

    }

    private void disableEditTexts(){
        binding.etMemilikiAset.setFocusable(false);
        binding.etJenisPembiayaan.setFocusable(false);
        binding.etSegmen.setFocusable(false);
        binding.etJenisTipeProduk.setFocusable(false);
        binding.etProgram.setFocusable(false);
        binding.etTujuanPembiayaan.setFocusable(false);
        binding.etAkadPembiayaan.setFocusable(false);


    }

    @Override
    public void onSelect(String title, MGenericModel data) {
        if(title.equalsIgnoreCase(binding.tfMemilikiAset.getLabelText())){
            binding.etMemilikiAset.setText(data.getDESC());
        }

    }

    private void isiDropdown(){
        dataDropdownMemilikiAset.add(new MGenericModel("Ya","Ya"));
        dataDropdownMemilikiAset.add(new MGenericModel("Tidak","Tidak"));


    }

    private void allOnChange(){
//        binding.etPriceDitawarkan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etPriceDitawarkan));
    }
}