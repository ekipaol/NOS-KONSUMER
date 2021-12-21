package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_dedupe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDedupe;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.FlagAplikasiPojo;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityDedupeBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataDedupe;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
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

public class DataDedupeActivity extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener, ConfirmListener {

    private DataDedupeAdapter dataDedupeAdapter;

    private List<DataDedupe> data=new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private PrapenAoActivityDedupeBinding binding;
    private List<DataDedupe> dataDedupe;
    private String idAplikasi="";
    private boolean nikChange=false;
    List<MGenericModel> dataDropdownFlow=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=PrapenAoActivityDedupeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        if(getIntent().hasExtra("idAplikasi")){
            idAplikasi=getIntent().getStringExtra("idAplikasi");
        }

        //pantekan status untuk testing
        customToolbar();
        backgroundStatusBar();

        //initialize status
//        setData();
        initialize();
        onClicks();
        defaultViewCondition();


    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void initialize(){
        binding.rvListDedupe.setVisibility(View.VISIBLE);
        binding.rvListDedupe.setHasFixedSize(true);
        dataDedupeAdapter = new DataDedupeAdapter(DataDedupeActivity.this, data);
        binding.rvListDedupe.setLayoutManager(new LinearLayoutManager(DataDedupeActivity.this));
        binding.rvListDedupe.setItemAnimator(new DefaultItemAnimator());
        binding.rvListDedupe.setAdapter(dataDedupeAdapter);

        binding.refresh.setEnabled(false);
    }


    public void customToolbar(){
        binding.toolbarNosearch.tvPageTitle.setText("Data DEDUPE");

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setData(){
        //initialize status pertama
//        DataDedupe data1=new DataDedupe();
//        data1.setNamaNasabah("Alexis Sanchez");
//        data1.setProduk("NOS Prapen");
//        data1.setStatusDalamAplikasi("Nasabah");
//        data1.setStatusTerakhir("Cair");
//
//
//        DataDedupe data2=new DataDedupe();
//        data2.setNamaNasabah("Alexis Sanchez");
//        data2.setProduk("NOS Prapen");
//        data2.setStatusDalamAplikasi("Nasabah");
//        data2.setStatusTerakhir("Confirm Validasi Engine");
//
//        DataDedupe data3=new DataDedupe();
//        data3.setNamaNasabah("Winona Sanchez");
//        data3.setProduk("NOS Prapen");
//        data3.setStatusDalamAplikasi("Pasangan");
//        data3.setStatusTerakhir("Ditolak");
//
//        DataDedupe data4=new DataDedupe();
//        data4.setNamaNasabah("Alexis Sanchez");
//        data4.setProduk("NOS Mitraguna");
//        data4.setStatusDalamAplikasi("Nasabah");
//        data4.setStatusTerakhir("Cair");
//
//
//        DataDedupe data5=new DataDedupe();
//        data5.setNamaNasabah("Alexis Sanchez");
//        data5.setProduk("NOS Prapen");
//        data5.setStatusDalamAplikasi("Nasabah");
//        data5.setStatusTerakhir("Ditolak");
//
//        DataDedupe data6=new DataDedupe();
//        data6.setNamaNasabah("Winona Sanchez");
//        data6.setProduk("NOS Mikro");
//        data6.setStatusDalamAplikasi("Pasangan");
//        data6.setStatusTerakhir("Cair");
//
//        data.add(data1);
//        data.add(data2);
//        data.add(data3);
//        data.add(data4);
//        data.add(data5);
//        data.add(data6);

        if(data.size()==0){
            binding.llEmptydata.setVisibility(View.VISIBLE);
        }
    }

    public void cekDedupe() {
        //  dataUser = getListUser();
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqDedupe req=new ReqDedupe();
        AppPreferences appPreferences=new AppPreferences(DataDedupeActivity.this);

        //pantekan uid
        req.setNIK(binding.etNik.getText().toString());
        req.setApplicationId(idAplikasi);
//        req.setUID(String.valueOf(appPreferences.getUid()));

        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().inquiryDedupe(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                binding.rvListDedupe.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DataDedupe>>() {
                        }.getType();

                        dataDedupe = gson.fromJson(listDataString, type);
                        dataDedupeAdapter = new DataDedupeAdapter(DataDedupeActivity.this, dataDedupe);
                        binding.rvListDedupe.setLayoutManager(new LinearLayoutManager(DataDedupeActivity.this));
                        binding.rvListDedupe.setItemAnimator(new DefaultItemAnimator());
                        binding.rvListDedupe.setAdapter(dataDedupeAdapter);


                        if (dataDedupe.size() == 0) {
                            binding.llEmptydata.setVisibility(View.VISIBLE);
                        } else {
                            binding.llEmptydata.setVisibility(View.GONE);
                        }
                    }
                    else{
                        AppUtil.notiferror(DataDedupeActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataDedupeActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    public void updateDedupe() {
        //  dataUser = getListUser();
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);

        ReqDedupe req=new ReqDedupe();
        req.setNIK(binding.etNik.getText().toString()); //prapen dipantek pp
        req.setApplicationId(idAplikasi);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updateDedupe(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        CustomDialog.DialogSuccess(DataDedupeActivity.this, "Success!", "Data Berhasil Disimpan", DataDedupeActivity.this);

                        //update Flagging
                        Realm realm=Realm.getDefaultInstance();
                        FlagAplikasiPojo dataFlag= realm.where(FlagAplikasiPojo.class).equalTo("idAplikasi", Long.parseLong(idAplikasi)).findFirst();
                        if(!realm.isInTransaction()){
                            realm.beginTransaction();
                        }
                        dataFlag.setFlagD1DataDedupe(true);
                        realm.insertOrUpdate(dataFlag);
                        realm.close();

                    }
                    else{
                        AppUtil.notiferror(DataDedupeActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataDedupeActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }



    @Override
    public void onSelect(String title, MGenericModel data) {

    }

    @Override
    public void onRefresh() {
        binding.refresh.setRefreshing(false);
        binding.rvListDedupe.setVisibility(View.GONE);
        setData();
        initialize();
    }

    private void onClicks(){
        binding.btnLanjutDedupe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(binding.etNik.getText().toString().isEmpty()){
                  binding.tfNik.setError("Harap Isi NIK Terlebih Dahulu",true);
              }
              else if(nikChange){
                  AppUtil.notiferror(DataDedupeActivity.this, findViewById(android.R.id.content), "Harap Klik Cek DEDUPE Dahulu");
                }
              else{
                  updateDedupe();
              }

            }
        });

        binding.btCekNikDedupe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.etNik.getText().toString().isEmpty()){
                    binding.tfNik.setError("Harap Isi NIK Terlebih Dahulu",true);
                }
                else{
                    nikChange=false;
                    cekDedupe();
                }
            }
        });

        binding.etNik.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nikChange=true;

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void defaultViewCondition(){
        binding.rvListDedupe.setVisibility(View.GONE);
    }

    @Override
    public void success(boolean val) {
        finish();
    }

    @Override
    public void confirm(boolean val) {

    }
}