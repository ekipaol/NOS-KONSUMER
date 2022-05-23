package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_pembiayaan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqHasilRekomendasiAkad;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.DataPembiayaan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqProgram;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.FlagAplikasiPojo;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityDataPembiayaanBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataListAplikasi;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DropdownGlobalPrapen;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.HotprospekActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.DetilAplikasiActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.general.AdapterListAplikasi;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.general.ListAplikasiActivity;
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

public class DataPembiayaanActivity extends AppCompatActivity implements View.OnClickListener, GenericListenerOnSelect, ConfirmListener {

    private PrapenAoActivityDataPembiayaanBinding binding;
    private List<MGenericModel> dataDropdownMemilikiAset =new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private  String idAplikasi="0";
    private DataPembiayaan dataPembiayaan;
    private AppPreferences appPreferences;

    private List<MGenericModel> dropdownTipeProduk=new ArrayList<>();
    private List<MGenericModel> dropdownSegmen=new ArrayList<>();
    private List<MGenericModel> dropdownJenisPembiayaan=new ArrayList<>();
    private List<MGenericModel> dropdownTujuanPembiayaan=new ArrayList<>();
    private List<MGenericModel> dropdownProgram=new ArrayList<>();
    private List<MGenericModel> dropdownAkad=new ArrayList<>();
    private boolean adaFieldBelumDiisi =false;
    private String statusId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PrapenAoActivityDataPembiayaanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        appPreferences=new AppPreferences(this);
        apiClientAdapter=new ApiClientAdapter(this);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Pembiayaan");
        if(getIntent().hasExtra("idAplikasi")){
            idAplikasi=getIntent().getStringExtra("idAplikasi");
            loadData();
        }

        if(getIntent().hasExtra("statusId")){
            statusId=getIntent().getStringExtra("statusId");
        }





        //biar keyboard gak nongol di awal activity kalau ada edittext
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        allOnClicks();
        disableEditTexts();
        isiDropdown();
        allOnChange();

        if(!statusId.equalsIgnoreCase("d.1")){
            noInputMode();
        }

        //urutan eksekusi API dropdown, tipe produk -> segmen -> jenis pembiayaan -> tujuan pembiayaan -> program, dimulai dari tipe produk dulu, isi API urutan seterusnya ada di masing masing method loadDropdown
        loadDropdownTipeProduk();

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
        binding.etJenisTipeProduk.setOnClickListener(this);
        binding.tfJenisTipeProduk.setOnClickListener(this);
        binding.etSegmen.setOnClickListener(this);
        binding.tfSegmen.setOnClickListener(this);
        binding.etJenisPembiayaan.setOnClickListener(this);
        binding.tfJenisPembiayaan.setOnClickListener(this);
        binding.etTujuanPembiayaan.setOnClickListener(this);
        binding.tfTujuanPembiayaan.setOnClickListener(this);
        binding.etProgram.setOnClickListener(this);
        binding.tfProgram.setOnClickListener(this);
        binding.etAkadPembiayaan.setOnClickListener(this);
        binding.tfAkadPembiayaan.setOnClickListener(this);
        binding.btnCekHasilAkad.setOnClickListener(this);
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
            case R.id.et_jenis_tipe_produk:
            case R.id.tf_jenis_tipe_produk:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJenisTipeProduk.getLabelText(), dropdownTipeProduk, DataPembiayaanActivity.this);
                break;
            case R.id.et_segmen:
            case R.id.tf_segmen:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfSegmen.getLabelText(), dropdownSegmen, DataPembiayaanActivity.this);
                break;
            case R.id.et_jenis_pembiayaan:
            case R.id.tf_jenis_pembiayaan:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJenisPembiayaan.getLabelText(), dropdownJenisPembiayaan, DataPembiayaanActivity.this);
                break;
            case R.id.et_tujuan_pembiayaan:
            case R.id.tf_tujuan_pembiayaan:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTujuanPembiayaan.getLabelText(), dropdownTujuanPembiayaan, DataPembiayaanActivity.this);
                break;
            case R.id.et_program:
            case R.id.tf_program:
                if(binding.etJenisTipeProduk.getText().toString().isEmpty()){
                    AppUtil.notiferror(DataPembiayaanActivity.this, findViewById(android.R.id.content), "Harap Pilih Produk Terlebih Dahulu");
                }else{
                    DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfProgram.getLabelText(), dropdownProgram, DataPembiayaanActivity.this);
                }
                break;
            case R.id.et_akad_pembiayaan:
            case R.id.tf_akad_pembiayaan:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfAkadPembiayaan.getLabelText(), dropdownAkad, DataPembiayaanActivity.this);
                break;
            case R.id.btn_cek_hasil_akad:
               if(binding.etJenisTipeProduk.getText().toString().isEmpty()||binding.etJenisPembiayaan.getText().toString().isEmpty()||binding.etTujuanPembiayaan.getText().toString().isEmpty()||binding.etMemilikiAset.getText().toString().isEmpty()){
                   AppUtil.notiferror(DataPembiayaanActivity.this, findViewById(android.R.id.content), "Harap Lengkapi Data Sebelum Cek Akad");
                   binding.tfJenisTipeProduk.requestFocus();
               }
               else{
                   loadCekHasilRekomendasiAkad();
               }
               break;

            case R.id.btn_simpan_data_pembiayaan:
                adaFieldBelumDiisi=false;
                validateField(binding.etJenisTipeProduk,binding.tfJenisTipeProduk);
                validateField(binding.etSegmen,binding.tfSegmen);
                validateField(binding.etJenisPembiayaan,binding.tfJenisPembiayaan);
                validateField(binding.etProgram,binding.tfProgram);
                validateField(binding.etTujuanPembiayaan,binding.tfTujuanPembiayaan);
                validateField(binding.etMemilikiAset,binding.tfMemilikiAset);
                validateField(binding.etPriceDitawarkan,binding.tfPriceDitawarkan);
                validateField(binding.etAkadPembiayaan,binding.tfAkadPembiayaan);

                if(!adaFieldBelumDiisi){
                    updateData();
                }


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
        binding.tfJenisTipeProduk.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJenisTipeProduk.getLabelText(), dropdownTipeProduk, DataPembiayaanActivity.this);
            }
        });
        binding.tfSegmen.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfSegmen.getLabelText(), dropdownSegmen, DataPembiayaanActivity.this);
            }
        });
        binding.tfJenisPembiayaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJenisPembiayaan.getLabelText(), dropdownJenisPembiayaan, DataPembiayaanActivity.this);
            }
        });
        binding.tfTujuanPembiayaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTujuanPembiayaan.getLabelText(), dropdownTujuanPembiayaan, DataPembiayaanActivity.this);
            }

        });
        binding.tfProgram.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.etJenisTipeProduk.getText().toString().isEmpty()){
                    AppUtil.notiferror(DataPembiayaanActivity.this, findViewById(android.R.id.content), "Harap Pilih Produk Terlebih Dahulu");
                }else{
                    DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfProgram.getLabelText(), dropdownProgram, DataPembiayaanActivity.this);
                }
            }

        });
        binding.tfAkadPembiayaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfAkadPembiayaan.getLabelText(), dropdownAkad, DataPembiayaanActivity.this);
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
        if(title.equalsIgnoreCase(binding.tfJenisTipeProduk.getLabelText())){
            binding.etJenisTipeProduk.setText(data.getDESC());
            binding.etProgram.setText("");
            loadDropdownProgram();
        }
        if(title.equalsIgnoreCase(binding.tfSegmen.getLabelText())){
            binding.etSegmen.setText(data.getDESC());
        }
        if(title.equalsIgnoreCase(binding.tfJenisPembiayaan.getLabelText())){
            binding.etJenisPembiayaan.setText(data.getDESC());
        }
        if(title.equalsIgnoreCase(binding.tfTujuanPembiayaan.getLabelText())){
            binding.etTujuanPembiayaan.setText(data.getDESC());
        }
        if(title.equalsIgnoreCase(binding.tfProgram.getLabelText())){
            binding.etProgram.setText(data.getDESC());
        }
        if(title.equalsIgnoreCase(binding.tfAkadPembiayaan.getLabelText())){
            binding.etAkadPembiayaan.setText(data.getDESC());
            binding.tvInfo.setText("Syarat Akad : "+data.getID());
        }

    }

    private void isiDropdown(){
        dataDropdownMemilikiAset.add(new MGenericModel("Iya","Iya"));
        dataDropdownMemilikiAset.add(new MGenericModel("Tidak","Tidak"));


    }

    private void setData(){
        binding.etJenisTipeProduk.setText(dataPembiayaan.getTipeProduk());
        binding.etSegmen.setText(dataPembiayaan.getSegmen());
        binding.etJenisPembiayaan.setText(dataPembiayaan.getJenisPembiayaan());
        binding.etProgram.setText(dataPembiayaan.getProgram());
        binding.etTujuanPembiayaan.setText(dataPembiayaan.getTujuanPembiayaan());
        binding.etMemilikiAset.setText(dataPembiayaan.getMemilikiAset());
        binding.etPriceDitawarkan.setText(String.valueOf(dataPembiayaan.getOfferingPrice()));
        binding.etAkadPembiayaan.setText(dataPembiayaan.getPilihanAkad());
        binding.tvInfo.setText(dataPembiayaan.getSyaratUmumAkad());
        binding.etPricingPensiun.setText(String.valueOf(dataPembiayaan.getPensiunPrice()));
    }

    public void loadDropdownTipeProduk() {
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        //pantekan no aplikasi dan aktifitas

        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().dropdownTipeProduk(EmptyRequest.INSTANCE);
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

                        dropdownTipeProduk.clear();
                        for (int i = 0; i <dropdownTemp.size(); i++) {
                            dropdownTipeProduk.add(new MGenericModel(dropdownTemp.get(i).getName(),dropdownTemp.get(i).getName()));
                        }
                        loadDropdownSegmen();

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataPembiayaanActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                
            }
        });
    }

    public void loadDropdownSegmen() {
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        //pantekan no aplikasi dan aktifitas

        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().dropdownSegmen(EmptyRequest.INSTANCE);
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
                        dropdownSegmen.clear();
                        for (int i = 0; i <dropdownTemp.size(); i++) {
                            dropdownSegmen.add(new MGenericModel(dropdownTemp.get(i).getName(),dropdownTemp.get(i).getName()));
                        }
                        loadDropdownJenisPembiayaan();
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataPembiayaanActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                
            }
        });
    }

    public void loadDropdownJenisPembiayaan() {
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        //pantekan no aplikasi dan aktifitas

        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().dropdownJenisPembiayaan(EmptyRequest.INSTANCE);
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
                        dropdownJenisPembiayaan.clear();
                        for (int i = 0; i <dropdownTemp.size(); i++) {
                            dropdownJenisPembiayaan.add(new MGenericModel(dropdownTemp.get(i).getName(),dropdownTemp.get(i).getName()));
                        }
                        loadDropdownTujuanPembiayaan();
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataPembiayaanActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                
            }
        });
    }

    public void loadDropdownTujuanPembiayaan() {
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        //pantekan no aplikasi dan aktifitas

        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().dropdownTujuanPembiayaan(EmptyRequest.INSTANCE);
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
                        dropdownTujuanPembiayaan.clear();
                        for (int i = 0; i <dropdownTemp.size(); i++) {
                            dropdownTujuanPembiayaan.add(new MGenericModel(dropdownTemp.get(i).getName(),dropdownTemp.get(i).getName()));
                        }
//                        loadDropdownProgram();

                    }
                }
            }
            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataPembiayaanActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                
            }
        });
    }

    public void loadDropdownProgram() {
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        //pantekan no aplikasi dan aktifitas

        ReqProgram req=new ReqProgram();
        req.setNamaProduk(binding.etJenisTipeProduk.getText().toString());
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().dropdownProgramNew(req);
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
                        dropdownProgram.clear();
                        for (int i = 0; i <dropdownTemp.size(); i++) {
                            dropdownProgram.add(new MGenericModel(dropdownTemp.get(i).getName(),dropdownTemp.get(i).getName()));
                        }


                    }
                }
            }
            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataPembiayaanActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                
            }
        });
    }

    public void loadCekHasilRekomendasiAkad() {
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);

        ReqHasilRekomendasiAkad req=new ReqHasilRekomendasiAkad();
        req.setTipe_Produk(binding.etJenisTipeProduk.getText().toString());
        req.setJenis_Produk(binding.etJenisPembiayaan.getText().toString());
        req.setTujuan_Pembiayaan(binding.etTujuanPembiayaan.getText().toString());
        req.setHave_Asset(binding.etMemilikiAset.getText().toString());

        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().cekHasilRekomendasiAkad(req);
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
                        AppUtil.notifsuccess(DataPembiayaanActivity.this, findViewById(android.R.id.content), "Berhasil Mendapatkan List Akad");
                        binding.etAkadPembiayaan.setHint("Pilih");
                        dropdownAkad.clear();
                        for (int i = 0; i <dropdownTemp.size(); i++) {
                            dropdownAkad.add(new MGenericModel(dropdownTemp.get(i).getDescription(),dropdownTemp.get(i).getName()));
                        }

                    }
                    else{
                        AppUtil.notiferror(DataPembiayaanActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataPembiayaanActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                
            }
        });
    }

    public void loadData() {
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        AppPreferences appPreferences=new AppPreferences(DataPembiayaanActivity.this);

        //pantekan uid
        req.setApplicationId(Long.parseLong(idAplikasi));


        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().inquiryDataPembiayaan(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().get(0).getAsJsonObject().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<DataPembiayaan>() {
                        }.getType();

                        dataPembiayaan = gson.fromJson(listDataString, type);
                        setData();
                    }
                    else{
                        AppUtil.notiferror(DataPembiayaanActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataPembiayaanActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                
            }
        });
    }

    public void updateData() {
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);

        DataPembiayaan req=new DataPembiayaan();
        req.setGroupProduk("PP"); //prapen dipantek pp
        req.setTipeProduk(binding.etJenisTipeProduk.getText().toString());
        req.setSegmen(binding.etSegmen.getText().toString());
        req.setJenisPembiayaan(binding.etJenisPembiayaan.getText().toString());
        req.setProgram(binding.etProgram.getText().toString());
        req.setTujuanPembiayaan(binding.etTujuanPembiayaan.getText().toString());
        req.setMemilikiAset(binding.etMemilikiAset.getText().toString());
        req.setTipeProduk(binding.etJenisTipeProduk.getText().toString());
        req.setOfferingPrice(Double.parseDouble(binding.etPriceDitawarkan.getText().toString()));
        req.setPilihanAkad(binding.etAkadPembiayaan.getText().toString());
        req.setPensiunPrice(Double.parseDouble(binding.etPricingPensiun.getText().toString()));


        //ambil dari data login nanti
        req.setUid(Integer.toString(appPreferences.getUid()));
        req.setKodeCabang(appPreferences.getKodeCabang());

//        req.setUid("123");
//        req.setKodeCabang("123");

        if(!idAplikasi.isEmpty()){
            req.setApplicationId(Long.parseLong(idAplikasi));
        }
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updateDataPembiayaan(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        CustomDialog.DialogSuccess(DataPembiayaanActivity.this, "Success!", "Data Berhasil Disimpan", DataPembiayaanActivity.this);

                        idAplikasi=response.body().getData().get("ApplicationId").toString();

                        //update Flagging
                        Realm realm=Realm.getDefaultInstance();
//                        FlagAplikasiPojo dataFlagOld= realm.where(FlagAplikasiPojo.class).equalTo("idAplikasi", Long.parseLong(idAplikasi)).findFirst();

                        FlagAplikasiPojo dataFlagNew=new FlagAplikasiPojo();

                        if(!realm.isInTransaction()){
                            realm.beginTransaction();
                        }
                        dataFlagNew.setIdAplikasi(Long.parseLong(idAplikasi));
                        dataFlagNew.setFlagD1DataPembiayaan(true);
                        realm.insertOrUpdate(dataFlagNew);
                        realm.close();
                    }
                    else{
                        AppUtil.notiferror(DataPembiayaanActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataPembiayaanActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                
            }
        });
    }

    private void validateField(EditText editText, TextFieldBoxes textFieldBoxes){
        if(editText.getText().toString().isEmpty()){
            adaFieldBelumDiisi=true;
            AppUtil.notiferror(DataPembiayaanActivity.this, findViewById(android.R.id.content), "Harap Isi "+textFieldBoxes.getLabelText());
        }
    }


    private void allOnChange(){
//        binding.etPriceDitawarkan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etPriceDitawarkan));
    }

    private void noInputMode(){
        AppUtil.disableButtons(binding.getRoot());
        AppUtil.disableEditTexts(binding.getRoot());
    }

    @Override
    public void success(boolean val) {
        if (val){
            Intent intent = new Intent(DataPembiayaanActivity.this, DetilAplikasiActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("status","Data Entry");
            intent.putExtra("statusId","D.1");
            intent.putExtra("idAplikasi",idAplikasi);
            startActivity(intent);
        }
    }

    @Override
    public void confirm(boolean val) {

    }
}