package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_hutang;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityDataHutangBinding;
import com.application.bris.ikurma_nos_konsumer.model.menu.ListViewSubmenuHotprospek;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataHutang;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_ideb.DataIdebActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataHutangActivity extends AppCompatActivity implements  GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener {

    private List<ListViewSubmenuHotprospek> dataMenu;
    private DataHutangAdapter dataHutangAdapter;
    private GridLayoutManager layoutMenu;

    public static Long idAplikasi;
    private List<DataHutang> dataHutang =new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private PrapenAoActivityDataHutangBinding binding;
    List<MGenericModel> dataDropdownFlow=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=PrapenAoActivityDataHutangBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idAplikasi=Long.parseLong(getIntent().getStringExtra("idAplikasi"));

        //pantekan status untuk testing
        customToolbar();
        backgroundStatusBar();

        //initialize status
        setData();
        initialize();
        onClicks();

        //dicomment dlu karna belum clear
        loadData();


    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void initialize(){
        binding.rvListDataHutang.setVisibility(View.VISIBLE);
        binding.rvListDataHutang.setHasFixedSize(true);
        dataHutangAdapter = new DataHutangAdapter(DataHutangActivity.this, dataHutang);
        binding.rvListDataHutang.setLayoutManager(new LinearLayoutManager(DataHutangActivity.this));
        binding.rvListDataHutang.setItemAnimator(new DefaultItemAnimator());
        binding.rvListDataHutang.setAdapter(dataHutangAdapter);

        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);
    }


    public void customToolbar(){
        binding.toolbarNosearch.tvPageTitle.setText("Kewajiban Lainnya");

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setData(){
        //initialize status pertama
        DataHutang data1=new DataHutang();
        data1.setNamaPemberiHutang("Koperasi jariyah");
        data1.setNominalPinjaman("20000000");
        data1.setSisaJangkaWaktu("180");
        data1.setAngsuranBulanan("300000");
        data1.setTreatmentPembiayaan("Dilanjutkan");

        DataHutang data2=new DataHutang();
        data2.setNamaPemberiHutang("Bank Onlen");
        data2.setNominalPinjaman("10000000");
        data2.setSisaJangkaWaktu("120");
        data2.setAngsuranBulanan("700000");
        data2.setTreatmentPembiayaan("Akan Dilunasi");

        DataHutang data3=new DataHutang();
        data3.setNamaPemberiHutang("Warung Steak and Shake");
        data3.setNominalPinjaman("12000000");
        data3.setSisaJangkaWaktu("24");
        data3.setAngsuranBulanan("120000");
        data3.setTreatmentPembiayaan("Lunas");

        dataHutang.add(data1);
        dataHutang.add(data2);
        dataHutang.add(data3);

        if(dataHutang.size()==0){
            binding.llEmptydata.setVisibility(View.VISIBLE);
        }
    }

    private void loadData(){
        binding.rvListDataHutang.setVisibility(View.GONE);
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        req.setApplicationId(idAplikasi);

        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().inquiryDataHutang(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DataHutang>>() {
                        }.getType();
                        dataHutang =  gson.fromJson(listDataString, type);

                        if(dataHutang.size()==0){
                            binding.llEmptydata.setVisibility(View.VISIBLE);
                        }

                        initialize();
                    }
                    else{
                        AppUtil.notiferror(DataHutangActivity.this, findViewById(android.R.id.content), response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataHutangActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
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
        binding.rvListDataHutang.setVisibility(View.GONE);
//        setData();
//        initialize();
                loadData();
    }

    public void refreshData(){
        loadData();
    }

    private void onClicks(){
        binding.btnTambahDataHutang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DataHutangActivity.this,TambahDataHutangActivity.class);
                intent.putExtra("idAplikasi",idAplikasi);
                startActivity(intent);

            }
        });
    }
}