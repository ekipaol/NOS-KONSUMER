package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_dedupe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityDataHutangBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityDedupeBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataDedupe;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataHutang;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_hutang.DataHutangAdapter;

import java.util.ArrayList;
import java.util.List;

public class DataDedupeActivity extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener {

    private DataDedupeAdapter dataDedupeAdapter;

    public static int idAplikasi=0;
    private List<DataDedupe> data=new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private PrapenAoActivityDedupeBinding binding;
    List<MGenericModel> dataDropdownFlow=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=PrapenAoActivityDedupeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);

        //pantekan status untuk testing
        customToolbar();
        backgroundStatusBar();

        //initialize status
        setData();
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
        DataDedupe data1=new DataDedupe();
        data1.setNamaNasabah("Alexis Sanchez");
        data1.setProduk("NOS Prapen");
        data1.setStatusDalamAplikasi("Nasabah");
        data1.setStatusTerakhir("Cair");


        DataDedupe data2=new DataDedupe();
        data2.setNamaNasabah("Alexis Sanchez");
        data2.setProduk("NOS Prapen");
        data2.setStatusDalamAplikasi("Nasabah");
        data2.setStatusTerakhir("Confirm Validasi Engine");

        DataDedupe data3=new DataDedupe();
        data3.setNamaNasabah("Winona Sanchez");
        data3.setProduk("NOS Prapen");
        data3.setStatusDalamAplikasi("Pasangan");
        data3.setStatusTerakhir("Ditolak");

        DataDedupe data4=new DataDedupe();
        data4.setNamaNasabah("Alexis Sanchez");
        data4.setProduk("NOS Mitraguna");
        data4.setStatusDalamAplikasi("Nasabah");
        data4.setStatusTerakhir("Cair");


        DataDedupe data5=new DataDedupe();
        data5.setNamaNasabah("Alexis Sanchez");
        data5.setProduk("NOS Prapen");
        data5.setStatusDalamAplikasi("Nasabah");
        data5.setStatusTerakhir("Ditolak");

        DataDedupe data6=new DataDedupe();
        data6.setNamaNasabah("Winona Sanchez");
        data6.setProduk("NOS Mikro");
        data6.setStatusDalamAplikasi("Pasangan");
        data6.setStatusTerakhir("Cair");

        data.add(data1);
        data.add(data2);
        data.add(data3);
        data.add(data4);
        data.add(data5);
        data.add(data6);

        if(data.size()==0){
            binding.llEmptydata.setVisibility(View.VISIBLE);
        }
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
                finish();
                Toast.makeText(DataDedupeActivity.this, "Silahkan Lanjut", Toast.LENGTH_SHORT).show();

            }
        });
        binding.btCekNikDedupe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DataDedupeActivity.this, "Checking DEDUPE", Toast.LENGTH_SHORT).show();
                binding.rvListDedupe.setVisibility(View.VISIBLE);
            }
        });
    }

    private void defaultViewCondition(){
        binding.rvListDedupe.setVisibility(View.GONE);
    }
}