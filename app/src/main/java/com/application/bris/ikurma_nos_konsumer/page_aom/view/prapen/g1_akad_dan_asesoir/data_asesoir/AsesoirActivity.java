package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g1_akad_dan_asesoir.data_asesoir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqAcctNumber;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDataPejabat;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoAsesoirActivityBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataCIfRekening;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataPejabat;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DropdownGlobalPrapen;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DropdownPejabat;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class AsesoirActivity extends AppCompatActivity implements GenericListenerOnSelect, View.OnClickListener, ConfirmListener {

    private PrapenAoAsesoirActivityBinding binding;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private List<MGenericModel> dataDropdown;
    private List<MGenericModel> dataDropdownPejabat=new ArrayList<>();
    private DataPejabat dataPejabat;
    private long idAplikasi;
    private boolean adaFieldBelumDiisi=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PrapenAoAsesoirActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        apiClientAdapter=new ApiClientAdapter(this);
        appPreferences=new AppPreferences(this);

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Asesoir");
        idAplikasi=Long.parseLong(getIntent().getStringExtra("idAplikasi"));

        allOnClicks();
        disableEditTexts();

//        loadDropdownPejabat();
        loadDataPejabat();

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
        binding.etPejabatPenandatangan.setOnClickListener(this);
        binding.tfPejabatPenandatangan.setOnClickListener(this);
        binding.btnSimpanDataAsesoir.setOnClickListener(this);
        binding.btnCekNomorRekening.setOnClickListener(this);

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
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfPejabatPenandatangan.getLabelText(),dataDropdownPejabat,this::onSelect);
                break;
            case R.id.btn_simpan_data_asesoir:
                adaFieldBelumDiisi=false;
                validateField(binding.etPejabatPenandatangan,binding.tfPejabatPenandatangan);
                validateField(binding.etNamaPejabat,binding.tfNamaPejabat);
                validateField(binding.etNomorSkpp,binding.tfNomorSkppPejabat);
                validateField(binding.etTanggalSuratKeputusan,binding.tfTanggalSuratKeputusan);
                validateField(binding.etNomorKua,binding.tfNomorKua);
                validateField(binding.etTanggalSuratKuasa,binding.tfTanggalSuratKuasa);
                validateField(binding.etKotaPenandatangananDokumen,binding.tfKotaPenandatangananDokumen);
                validateField(binding.etNomorRekeningBsi,binding.tfNomorRekeningBsi);
                validateField(binding.etAtasNamaRekening,binding.tfAtasNamaRekening);
                validateField(binding.etNomorCif,binding.tfNomorCif);

                if(!adaFieldBelumDiisi){
                    sendDataPejabat();
                }
                break;
                case R.id.btn_cek_nomor_rekening:
                    cekRekening();
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
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfPejabatPenandatangan.getLabelText(),dataDropdownPejabat,AsesoirActivity.this::onSelect);
            }
        });

    }

    private void validateField(EditText editText, TextFieldBoxes textFieldBoxes){
        if(editText.getText().toString().isEmpty()){
            adaFieldBelumDiisi=true;
            AppUtil.notiferror(AsesoirActivity.this, findViewById(android.R.id.content), "Harap Isi "+textFieldBoxes.getLabelText());
        }
    }

    private void disableEditTexts(){
        binding.etTanggalSuratKuasa.setFocusable(false);
        binding.etTanggalSuratKeputusan.setFocusable(false);
        binding.etAtasNamaRekening.setFocusable(false);
        binding.etNomorCif.setFocusable(false);
        binding.etPejabatPenandatangan.setFocusable(false);

    }

    public void loadDataPejabat() {
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();

        //pantekan uid
        req.setApplicationId(idAplikasi);
        req.setUID(Integer.toString(appPreferences.getUid()));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryDataPejabat(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {

                        try{
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<DataPejabat>() {
                            }.getType();

                            dataPejabat = gson.fromJson(listDataString, type);
                            setData();
                        }

                        catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                    else{
                        AppUtil.notiferror(AsesoirActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(AsesoirActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });

        loadDropdownPejabat();
    }

    public void loadDropdownPejabat() {
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        //pantekan no aplikasi dan aktifitas

        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().dropdownPejabat(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DropdownPejabat>>() {
                        }.getType();
                        List<DropdownPejabat> dropdownTemp= gson.fromJson(listDataString, type);
                        dataDropdownPejabat.clear();
                        for (int i = 0; i <dropdownTemp.size(); i++) {
                            dataDropdownPejabat.add(new MGenericModel(dropdownTemp.get(i).getUid(),dropdownTemp.get(i).getUid()));
                        }

                    }
                    else{
                        AppUtil.notiferror(AsesoirActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(AsesoirActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    public void sendDataPejabat(){
        ReqDataPejabat reqDataPejabat=new ReqDataPejabat();
        DataPejabat dataPejabatSend=new DataPejabat();

        dataPejabatSend.setuIDPejabatPenandatangan(binding.etPejabatPenandatangan.getText().toString());
        dataPejabatSend.setNamaPejabatPenandatangan(binding.etNamaPejabat.getText().toString());
        dataPejabatSend.setNomorSKPPPejabat(binding.etNomorSkpp.getText().toString());
        dataPejabatSend.setTanggalSuratKeputusan(AppUtil.parseTanggalGeneral(binding.etTanggalSuratKeputusan.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
        dataPejabatSend.setTanggalSuratKuasa(AppUtil.parseTanggalGeneral(binding.etTanggalSuratKuasa.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
        dataPejabatSend.setNomorKUA(binding.etNomorKua.getText().toString());
        dataPejabatSend.setKotaPenandatangananDokumen(binding.etKotaPenandatangananDokumen.getText().toString());
        dataPejabatSend.setNoRekeningBSI(binding.etNomorRekeningBsi.getText().toString());
        dataPejabatSend.setAtasNamaRekening(binding.etAtasNamaRekening.getText().toString());
        dataPejabatSend.setNomorCIF(binding.etNomorCif.getText().toString());

        reqDataPejabat.setApplicationId(idAplikasi);
        reqDataPejabat.setUID(Integer.toString(appPreferences.getUid()));
        reqDataPejabat.setData(dataPejabatSend);


        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updateDataPejabat(reqDataPejabat);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){

                            CustomDialog.DialogSuccess(AsesoirActivity.this, "Success!", "Update Data Sukses", AsesoirActivity.this);
                        }
                        else{
                            AppUtil.notiferror(AsesoirActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AsesoirActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(AsesoirActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void cekRekening() {
        //  dataUser = getListUser();
        binding.loadingRekening.setVisibility(View.VISIBLE);
        //pantekan no aplikasi dan aktifitas
        ReqAcctNumber req=new ReqAcctNumber();
        req.setAccountNo(binding.etNomorRekeningBsi.getText().toString());

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().validasiPayroll(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingRekening.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<DataCIfRekening>() {
                        }.getType();
                        DataCIfRekening dataCIfRekening =  gson.fromJson(listDataString, type);

                        if(dataCIfRekening.getCIF()!=null&&!dataCIfRekening.getCIF().isEmpty()){
                            binding.etNomorCif.setText(dataCIfRekening.getCIF());
                            binding.etAtasNamaRekening.setText(dataCIfRekening.getNama());
                            AppUtil.notifsuccess(AsesoirActivity.this, findViewById(android.R.id.content), "Rekening Ditemukan");
                        }
                    }
                    else{
                        AppUtil.notiferror(AsesoirActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingRekening.setVisibility(View.GONE);
                AppUtil.notiferror(AsesoirActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    private void setData(){
        try{
            //3 data ini ada kemungkin
            binding.etPejabatPenandatangan.setText(dataPejabat.getuIDPejabatPenandatangan());
            binding.etNamaPejabat.setText(dataPejabat.getNamaPejabatPenandatangan());
            binding.etNomorSkpp.setText(dataPejabat.getNomorSKPPPejabat());
            binding.etTanggalSuratKeputusan.setText(AppUtil.parseTanggalGeneral(dataPejabat.getTanggalSuratKeputusan(),"yyyy-MM-dd","dd-MM-yyyy"));
            binding.etTanggalSuratKuasa.setText(AppUtil.parseTanggalGeneral(dataPejabat.getTanggalSuratKuasa(),"yyyy-MM-dd","dd-MM-yyyy"));
            binding.etNomorKua.setText(dataPejabat.getNomorKUA());
            binding.etKotaPenandatangananDokumen.setText(dataPejabat.getKotaPenandatangananDokumen());
            binding.etNomorRekeningBsi.setText(dataPejabat.getNoRekeningBSI());
            binding.etAtasNamaRekening.setText(dataPejabat.getAtasNamaRekening());
            binding.etNomorCif.setText(dataPejabat.getNomorCIF());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onSelect(String title, MGenericModel data) {
       if(title.equalsIgnoreCase(binding.tfPejabatPenandatangan.getLabelText())){
           binding.etPejabatPenandatangan.setText(data.getDESC());
       }
    }

    @Override
    public void success(boolean val) {
        finish();
    }

    @Override
    public void confirm(boolean val) {

    }
}