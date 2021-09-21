package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_marketing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoMarketingActivityBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

public class DataMarketingActivity extends AppCompatActivity implements KeyValueListener, View.OnClickListener {

    private PrapenAoMarketingActivityBinding binding;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PrapenAoMarketingActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Marketing");

        allOnClicks();
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

    }


    private void openDialog(String title,int viewId){
        DialogKeyValue.displayByViewId(getSupportFragmentManager(),title,viewId, this);
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {

        if(title.equalsIgnoreCase(binding.tfSumberAplikasi.getLabelText())){
            binding.etSumberAplikasi.setText(data.getName());

            if(data.getValue().equalsIgnoreCase("mitra fronting")){
                binding.tfMitraFronting.setVisibility(View.VISIBLE);
                binding.tfKodeAoMitraFronting.setVisibility(View.VISIBLE);
            }
            else{
                binding.tfMitraFronting.setVisibility(View.GONE);
                binding.tfKodeAoMitraFronting.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //SUMBER APLIKASI
            case R.id.et_sumber_aplikasi:
            case R.id.tf_sumber_aplikasi:
                openDialog(binding.tfSumberAplikasi.getLabelText(),binding.tfSumberAplikasi.getId());
                break;

            default:break;
        }
    }
    private void endIconOnClick(){
        binding.tfSumberAplikasi.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(binding.tfSumberAplikasi.getLabelText(),binding.tfSumberAplikasi.getId());
            }
        });

    }

    private void disableEditTexts(){
        binding.etSumberAplikasi.setFocusable(false);

    }

}