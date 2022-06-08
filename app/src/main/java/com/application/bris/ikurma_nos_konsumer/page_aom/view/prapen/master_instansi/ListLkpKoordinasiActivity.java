package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.master_instansi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDetilInstansi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityListLkpKoordinasiPrapenBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataListAplikasi;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataLkpKoordinasi;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.general.AdapterListAplikasi;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.CoreLayoutActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListLkpKoordinasiActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SearchView searchView;
    List<DataLkpKoordinasi> dataListLkp=new ArrayList<>();
    AdapterListLkpKoordinasi AdapterAplikasi;
    ApiClientAdapter apiClientAdapter;
    ActivityListLkpKoordinasiPrapenBinding binding;
    private Long idInstansi=0l;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityListLkpKoordinasiPrapenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(getIntent().hasExtra("idInstansi")){
            idInstansi=getIntent().getLongExtra("idInstansi",0);
        }
        main();
        backgroundStatusBar();
        allOnClick();

        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListLkpKoordinasiActivity.super.onBackPressed();
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Nama Nasabah, Produk, dll ..");

        searchPipeline();

        return true;

    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        ListLkpKoordinasiActivity.this.recreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        main();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void allOnClick(){
        binding.btnTambahLkp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListLkpKoordinasiActivity.this,InputLkpKoordinasiActivity.class);
                intent.putExtra("idInstansi",idInstansi);
                startActivity(intent);
            }
        });
    }

    public void main(){
        setSupportActionBar(binding.toolbar.tbRegular);
        apiClientAdapter=new ApiClientAdapter(ListLkpKoordinasiActivity.this);

        //jangan panggil method ini kalau pake viewbinding
//        AppUtil.toolbarRegular(this, "Putusan Gadai");
        binding.toolbar.tvPageTitle.setText("List LKP");
        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);

        loadData();
//        loadDataDummy();
    }


    public void loadData() {
        //  dataUser = getListUser();
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqDetilInstansi req=new ReqDetilInstansi();

        //pantekan uid
//        req.setUID("");
        req.setIdInstansi(idInstansi);

        Call<ParseResponseArr> call;

        call = apiClientAdapter.getApiInterface().inquirylkp(req);

        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                binding.rvListLkp.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {


                        try{
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<DataLkpKoordinasi>>() {
                            }.getType();
                            dataListLkp = gson.fromJson(listDataString, type);
                        }
                        catch (NullPointerException e){
                            e.printStackTrace();
                            dataListLkp=new ArrayList<>();
                        }

                        AdapterAplikasi = new AdapterListLkpKoordinasi(ListLkpKoordinasiActivity.this, dataListLkp);
                        binding.rvListLkp.setLayoutManager(new LinearLayoutManager(ListLkpKoordinasiActivity.this));
                        binding.rvListLkp.setItemAnimator(new DefaultItemAnimator());
                        binding.rvListLkp.setAdapter(AdapterAplikasi);


                        if (dataListLkp.size() == 0) {
                            binding.ivEmptyData.setVisibility(View.VISIBLE);
                            binding.tvWhale.setVisibility(View.VISIBLE);
                        } else {
                            binding.ivEmptyData.setVisibility(View.GONE);
                            binding.tvWhale.setVisibility(View.INVISIBLE);
                        }
                    }
                    else{
                        AppUtil.notiferror(ListLkpKoordinasiActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ListLkpKoordinasiActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
            }
        });
    }

    private void loadDataDummy(){
        Toast.makeText(this, "Masih menggunakan data dummy", Toast.LENGTH_SHORT).show();
        DataLkpKoordinasi dataDummy1=new DataLkpKoordinasi();
        DataLkpKoordinasi dataDummy2=new DataLkpKoordinasi();
        DataLkpKoordinasi dataDummy3=new DataLkpKoordinasi();

        dataDummy1.setNamaCabang("KC Makassar");
        dataDummy1.setKodeCabang("ID08811");
        dataDummy1.setTanggalLkp("29-Mei-2022");
        dataDummy1.setTanggalKadaluarsa("31-Juni-2022");
        dataDummy1.setFileName("lkp.pdf");
        dataDummy1.setIdDokumen("4389");

        dataDummy2.setNamaCabang("KC Medan");
        dataDummy2.setKodeCabang("ID08811");
        dataDummy2.setTanggalLkp("29-Mei-2022");
        dataDummy2.setTanggalKadaluarsa("31-Juni-2022");
        dataDummy2.setFileName("lkp1.png");
        dataDummy2.setIdDokumen("4388");

        dataDummy3.setNamaCabang("KC Serang");
        dataDummy3.setKodeCabang("ID08111");
        dataDummy3.setTanggalLkp("29-Mei-2022");
        dataDummy3.setTanggalKadaluarsa("31-Juni-2022");
        dataDummy3.setFileName("lkp2.png");
        dataDummy3.setIdDokumen("4388");

        dataListLkp.clear();

        dataListLkp.add(dataDummy1);
        dataListLkp.add(dataDummy2);
        dataListLkp.add(dataDummy3);

        AdapterAplikasi = new AdapterListLkpKoordinasi(ListLkpKoordinasiActivity.this, dataListLkp);
        binding.rvListLkp.setLayoutManager(new LinearLayoutManager(ListLkpKoordinasiActivity.this));
        binding.rvListLkp.setItemAnimator(new DefaultItemAnimator());
        binding.rvListLkp.setAdapter(AdapterAplikasi);


        if (dataListLkp.size() == 0) {
            binding.ivEmptyData.setVisibility(View.VISIBLE);
            binding.tvWhale.setVisibility(View.VISIBLE);
        } else {
            binding.ivEmptyData.setVisibility(View.GONE);
            binding.tvWhale.setVisibility(View.INVISIBLE);
        }
    }


    private void searchPipeline(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                AdapterAplikasi.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    AdapterAplikasi.getFilter().filter(query);
                    return false;
                }
                catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        binding.refresh.setRefreshing(false);
//        progressbar_loading.setVisibility(View.VISIBLE);
        binding.rvListLkp.setVisibility(View.GONE);

//        loadData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent=new Intent(ListLkpKoordinasiActivity.this, CoreLayoutActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
//        startActivity(intent);
//        finish();
    }




}