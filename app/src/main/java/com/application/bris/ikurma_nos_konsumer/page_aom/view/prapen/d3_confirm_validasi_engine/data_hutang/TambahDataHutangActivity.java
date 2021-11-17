package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_hutang;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateIdebOjk;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoTambahDataHutangActivityBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.UpdateDataHutang;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_pembiayaan.DataPembiayaanActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_ideb.EditIdebActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class TambahDataHutangActivity extends AppCompatActivity implements View.OnClickListener, GenericListenerOnSelect, ConfirmListener {

    private PrapenAoTambahDataHutangActivityBinding binding;
    private List<MGenericModel> dataDropdownTreatment=new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private long idAplikasi;
    private boolean adaFieldBelumDiisi=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PrapenAoTambahDataHutangActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        apiClientAdapter=new ApiClientAdapter(this);
        appPreferences=new AppPreferences(this);
        idAplikasi=getIntent().getLongExtra("idAplikasi",0);

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Tambah Kewajibah Lainnya");

        allOnClicks();
        disableEditTexts();
        isiDropdown();
        allOnChange();

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.DialogBackpress(TambahDataHutangActivity.this);
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
        CustomDialog.DialogBackpress(TambahDataHutangActivity.this);
    }

    private void allOnClicks(){
        endIconOnClick();
        binding.etTreatmentPembiayaan.setOnClickListener(this);
        binding.tfTreatmentPembiayaan.setOnClickListener(this);
        binding.btnTambahDataHutang.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //MENGGUNAKAN LNGP
            case R.id.et_sumber_aplikasi:
            case R.id.tf_sumber_aplikasi:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTreatmentPembiayaan.getLabelText(),dataDropdownTreatment,TambahDataHutangActivity.this);
                break;
            case R.id.btn_tambah_data_hutang:

                adaFieldBelumDiisi=false;
                validateField(binding.etAngsuranBulanan,binding.tfAngsuranBulanan);
                validateField(binding.etNominalPinjaman,binding.tfNominalPinjaman);
                validateField(binding.etNamaPemberiHutang,binding.tfNamaPemberiHutang);
                validateField(binding.etSisaJangkaWaktu,binding.tfSisaJangkaWaktu);
                validateField(binding.etTreatmentPembiayaan,binding.tfTreatmentPembiayaan);
                if(!adaFieldBelumDiisi){
                    updateDataHutang();
                }

            default:break;
        }
    }
    private void endIconOnClick(){
        binding.tfTreatmentPembiayaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTreatmentPembiayaan.getLabelText(),dataDropdownTreatment,TambahDataHutangActivity.this);
            }
        });

    }


    private void validateField(EditText editText, TextFieldBoxes textFieldBoxes){
        if(editText.getText().toString().isEmpty()){
            adaFieldBelumDiisi=true;
            AppUtil.notiferror(TambahDataHutangActivity.this, findViewById(android.R.id.content), "Harap Isi "+textFieldBoxes.getLabelText());
        }
    }

    private void disableEditTexts(){
        binding.etTreatmentPembiayaan.setFocusable(false);

    }

    @Override
    public void onSelect(String title, MGenericModel data) {
        if(title.equalsIgnoreCase(binding.tfTreatmentPembiayaan.getLabelText())){
            binding.etTreatmentPembiayaan.setText(data.getDESC());
        }

    }

    private void isiDropdown(){
        dataDropdownTreatment.add(new MGenericModel("1","Pembiayaan tetap dilanjutkan"));
        dataDropdownTreatment.add(new MGenericModel("2","Pembiayaan akan dilunasi melalui pencairan"));
        dataDropdownTreatment.add(new MGenericModel("3","Pembiayaan dilakukan takeover"));

    }

    private void allOnChange(){
        binding.etNominalPinjaman.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etNominalPinjaman));
        binding.etAngsuranBulanan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etAngsuranBulanan));
    }

    public void updateDataHutang(){
        UpdateDataHutang req=new UpdateDataHutang();
        req.setApplicationId(idAplikasi);
        req.setAnguranBulanan(Double.parseDouble(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etAngsuranBulanan.getText().toString())));
        req.setNominalPinjaman(Double.parseDouble(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etNominalPinjaman.getText().toString())));
        req.setNamaPemberiUtang(binding.etNamaPemberiHutang.getText().toString());
        req.setSisaWaktuPinjaman(Long.parseLong(binding.etSisaJangkaWaktu.getText().toString()));
        req.setTreatmentPembiayaan(binding.etTreatmentPembiayaan.getText().toString());
        req.setUid(String.valueOf(appPreferences.getUid()));

        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updateDataHutang(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){

                            CustomDialog.DialogSuccess(TambahDataHutangActivity.this, "Success!", "Update Data Kewajiban sukses", TambahDataHutangActivity.this);
                        }
                        else{
                            AppUtil.notiferror(TambahDataHutangActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(TambahDataHutangActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(TambahDataHutangActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    @Override
    public void success(boolean val) {
        finish();
    }

    @Override
    public void confirm(boolean val) {

    }
}