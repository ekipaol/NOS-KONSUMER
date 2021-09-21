package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_ideb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoDataIdebActivityBinding;
import com.application.bris.ikurma_nos_konsumer.model.menu.ListViewSubmenuHotprospek;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataHutang;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataIdeb;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_hutang.DataHutangAdapter;

import java.util.ArrayList;
import java.util.List;

public class DataIdebActivity extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener {

    private DataIdebAdapter dataIdebAdapter;

    public static int idAplikasi=0;
    private List<DataIdeb> data=new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private PrapenAoDataIdebActivityBinding binding;
    List<MGenericModel> dataDropdownFlow=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=PrapenAoDataIdebActivityBinding.inflate(getLayoutInflater());
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


    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void initialize(){
        binding.rvListDataIdeb.setVisibility(View.VISIBLE);
        binding.rvListDataIdeb.setHasFixedSize(true);
        dataIdebAdapter = new DataIdebAdapter(DataIdebActivity.this, data);
        binding.rvListDataIdeb.setLayoutManager(new LinearLayoutManager(DataIdebActivity.this));
        binding.rvListDataIdeb.setItemAnimator(new DefaultItemAnimator());
        binding.rvListDataIdeb.setAdapter(dataIdebAdapter);

        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);
    }


    public void customToolbar(){
        binding.toolbarNosearch.tvPageTitle.setText("Data Hutang");

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setData(){
        //initialize status pertama
        DataIdeb data1=new DataIdeb();
        data1.setNamaLembagaKeuangan("Bank Mandiri");
        data1.setBakiDebet("20000000");
        data1.setKualitasPembiayaan("Lancar");
        data1.setPerkiraanAngsuranBulanan("300000");
        data1.setTreatmentPembiayaan("Dilanjutkan");

        DataIdeb data2=new DataIdeb();
        data2.setNamaLembagaKeuangan("Bank Rakyat Indonesia");
        data2.setBakiDebet("3000000");
        data2.setKualitasPembiayaan("Lancar");
        data2.setPerkiraanAngsuranBulanan("200000");
        data2.setTreatmentPembiayaan("Lunas");

        DataIdeb data3=new DataIdeb();
        data3.setNamaLembagaKeuangan("Bank Negara Indonesia");
        data3.setBakiDebet("20000000");
        data3.setKualitasPembiayaan("Lancar");
        data3.setPerkiraanAngsuranBulanan("300000");
        data3.setTreatmentPembiayaan("Dilanjutkan");

        data.add(data1);
        data.add(data2);
        data.add(data3);

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
        binding.rvListDataIdeb.setVisibility(View.GONE);
        setData();
        initialize();
    }

    private void onClicks(){
        binding.btnDownloadIdeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(DataIdebActivity.this, TambahDataIdebActivity.class);
//                startActivity(intent);
                Toast.makeText(DataIdebActivity.this, "Clicking download ideb", Toast.LENGTH_SHORT).show();

            }
        });

        binding.btnSimpanDataPembiayaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(DataIdebActivity.this, TambahDataIdebActivity.class);
//                startActivity(intent);
                Toast.makeText(DataIdebActivity.this, "Clicking simpan", Toast.LENGTH_SHORT).show();

            }
        });
    }
}