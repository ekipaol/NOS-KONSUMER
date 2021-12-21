package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_marketing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.DataPembiayaan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqKodeAo;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateDataInstansiDapen;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.FlagAplikasiPojo;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoMarketingActivityBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataInstansiDapen;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataMarketing;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DropdownGlobalPrapen;
import com.application.bris.ikurma_nos_konsumer.model.prapen.ReqUpdateDataMarketing;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_nasabah.DataNasabahPrapenActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_pembiayaan.DataPembiayaanActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class DataMarketingActivity extends AppCompatActivity implements View.OnClickListener, GenericListenerOnSelect, ConfirmListener {

    private PrapenAoMarketingActivityBinding binding;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private List<MGenericModel> dropdownSumberAplikasi=new ArrayList<>();
    private List<MGenericModel> dropdownMitraFronting=new ArrayList<>();
    private Long idAplikasi;
    private DataMarketing dataMarketing;
    private boolean adaFieldBelumDiisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PrapenAoMarketingActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        apiClientAdapter=new ApiClientAdapter(this);
        appPreferences=new AppPreferences(this);
        idAplikasi=Long.parseLong(getIntent().getStringExtra("idAplikasi"));
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Marketing");

        allOnClicks();
        isiDropdown();
        disableEditTexts();
        defaultViewCondition();
        loadData();

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
        binding.etMitraFronting.setOnClickListener(this);
        binding.tfMitraFronting.setOnClickListener(this);
        binding.btnCekDataAo.setOnClickListener(this);
        binding.btnCekDataCabang.setOnClickListener(this);
        binding.btnSimpanDataMarketing.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //SUMBER APLIKASI
            case R.id.et_sumber_aplikasi:
            case R.id.tf_sumber_aplikasi:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfSumberAplikasi.getLabelText(),dropdownSumberAplikasi,DataMarketingActivity.this);
                break;
            case R.id.et_mitra_fronting:
            case R.id.tf_mitra_fronting:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfMitraFronting.getLabelText(),dropdownMitraFronting,DataMarketingActivity.this);
                break;
            case R.id.btn_simpan_data_marketing:
                adaFieldBelumDiisi=false;
                validateField(binding.etSumberAplikasi,binding.tfSumberAplikasi);
                validateField(binding.etKodeAo,binding.tfKodeAo);
                validateField(binding.etKodeCabangPembukuan,binding.tfKodeCabangPembukuan);
                if(binding.etSumberAplikasi.toString().equalsIgnoreCase("Mitra fronting")){
                    validateField(binding.etMitraFronting,binding.tfMitraFronting);
                    validateField(binding.etKodeAoMitraFronting,binding.tfKodeAoMitraFronting);
                }
                if(!adaFieldBelumDiisi){
                    sendDataMarketing();
                }
                break;
            case R.id.btn_cek_data_ao:
               if(binding.etKodeAo.getText().toString().isEmpty()){
                   AppUtil.notiferror(DataMarketingActivity.this, findViewById(android.R.id.content),"Harap Isi Kode AO");
               }
               else {
                   inquiryNamaKodeAo();
               }
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
        binding.tfMitraFronting.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfMitraFronting.getLabelText(),dropdownMitraFronting,DataMarketingActivity.this);
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

    private void defaultViewCondition(){
        binding.etKodeCabangPembukuan.setText(appPreferences.getKodeKantor());
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
        else if(title.equalsIgnoreCase(binding.tfMitraFronting.getLabelText())){
            binding.etMitraFronting.setText(data.getDESC());
        }
    }


    private void validateField(EditText editText, TextFieldBoxes textFieldBoxes){
        if(editText.getText().toString().isEmpty()){
            adaFieldBelumDiisi=true;
            AppUtil.notiferror(DataMarketingActivity.this, findViewById(android.R.id.content), "Harap Isi "+textFieldBoxes.getLabelText());
        }
    }

    private void setData(){
        binding.etSumberAplikasi.setText(dataMarketing.getSumberAplikasi());
        binding.etKodeAo.setText(dataMarketing.getKodeAO());
        binding.etKodeAoReferal.setText(dataMarketing.getKodeAO2());
        binding.etNamaAo.setText(dataMarketing.getNamaAO());
        binding.etNamaAoReferal.setText(dataMarketing.getNamaAOReferal());
        binding.etKodeCabangPembukuan.setText(dataMarketing.getKodeCabangPembukuan());
        binding.etKodeCabangReferal.setText(dataMarketing.getKodeCabangReferral());

        if(dataMarketing.getSumberAplikasi().equalsIgnoreCase("mitra fronting")){
            binding.tfMitraFronting.setVisibility(View.VISIBLE);
            binding.tfKodeAoMitraFronting.setVisibility(View.VISIBLE);
            binding.etMitraFronting.setText(dataMarketing.getMitraFronting());
            binding.etKodeAoMitraFronting.setText(dataMarketing.getKodeAOMitraFronting());
        }
        else{
            binding.tfMitraFronting.setVisibility(View.GONE);
            binding.tfKodeAoMitraFronting.setVisibility(View.GONE);
        }

    }

    public void loadData() {
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        AppPreferences appPreferences=new AppPreferences(DataMarketingActivity.this);

        //pantekan uid
        req.setApplicationId(idAplikasi);
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().inquiryDataMarketing(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().get(0).getAsJsonObject().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<DataMarketing>() {
                        }.getType();

                        dataMarketing = gson.fromJson(listDataString, type);
                        setData();
                    }
                    else{
                        AppUtil.notiferror(DataMarketingActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataMarketingActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });

        loadDropdownMitraFronting();
    }

    public void loadDropdownMitraFronting() {
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        //pantekan no aplikasi dan aktifitas

        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().dropdownMitraFronting(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DropdownGlobalPrapen>>() {
                        }.getType();
                        List<DropdownGlobalPrapen> dropdownTemp= gson.fromJson(listDataString, type);
                        dropdownMitraFronting.clear();
                        for (int i = 0; i <dropdownTemp.size(); i++) {
                            dropdownMitraFronting.add(new MGenericModel(dropdownTemp.get(i).getName(),dropdownTemp.get(i).getName()));
                        }

                    }
                    else{
                        AppUtil.notiferror(DataMarketingActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataMarketingActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    public void inquiryNamaKodeAo() {
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        //pantekan no aplikasi dan aktifitas
        ReqKodeAo req=new ReqKodeAo();
        req.setKodeAO(binding.etKodeAo.getText().toString());

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryNamaKodeAo(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String namaAo = response.body().getData().get("nama").toString();
                     if(namaAo!=null&&!namaAo.isEmpty()){
                         binding.etNamaAo.setText(namaAo);
                     }
                    }
                    else{
                        AppUtil.notiferror(DataMarketingActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataMarketingActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });

        if(!binding.etKodeAoReferal.getText().toString().isEmpty()){
            inquiryNamaKodeAoReferal();
        }

    }

    public void inquiryNamaKodeAoReferal() {
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        //pantekan no aplikasi dan aktifitas
        ReqKodeAo req=new ReqKodeAo();
        req.setKodeAO(binding.etKodeAoReferal.getText().toString());

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryNamaKodeAo(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String namaAo = response.body().getData().get("nama").toString();
                        if(namaAo!=null&&!namaAo.isEmpty()){
                            binding.etNamaAoReferal.setText(namaAo);
                        }
                    }
                    else{
                        AppUtil.notiferror(DataMarketingActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataMarketingActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    public void sendDataMarketing(){
        ReqUpdateDataMarketing reqDataMarketing=new ReqUpdateDataMarketing();
        DataMarketing req=new DataMarketing();
        req.setApplicationId(idAplikasi);
        req.setSumberAplikasi(binding.etSumberAplikasi.getText().toString());
        req.setKodeAO(binding.etKodeAo.getText().toString());
        req.setKodeAO2(binding.etKodeAoReferal.getText().toString());
        req.setNamaAO(binding.etNamaAo.getText().toString());
        req.setNamaAOReferal(binding.etNamaAoReferal.getText().toString());
        req.setKodeCabangPembukuan(binding.etKodeCabangPembukuan.getText().toString());
        req.setKodeCabangReferral(binding.etKodeCabangReferal.getText().toString());
        req.setMitraFronting(binding.etMitraFronting.getText().toString());
        req.setKodeAOMitraFronting(binding.etKodeAoMitraFronting.getText().toString());
        req.setUid(String.valueOf(appPreferences.getUid()));

        reqDataMarketing.setDataMitraFronting(req);

        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updateDataMarketing(reqDataMarketing);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){

                            CustomDialog.DialogSuccess(DataMarketingActivity.this, "Success!", "Update Data Marketing sukses", DataMarketingActivity.this);

                            //update Flagging
                            Realm realm=Realm.getDefaultInstance();
                            FlagAplikasiPojo dataFlag= realm.where(FlagAplikasiPojo.class).equalTo("idAplikasi", idAplikasi).findFirst();
                            if(!realm.isInTransaction()){
                                realm.beginTransaction();
                                dataFlag.setFlagD1DataDataMarketing(true);
                                realm.insertOrUpdate(dataFlag);
                                realm.close();
                            }
                        }
                        else{
                            AppUtil.notiferror(DataMarketingActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataMarketingActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataMarketingActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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