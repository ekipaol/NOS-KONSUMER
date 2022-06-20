package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.master_instansi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqListLngp;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityListLngpPrapenBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataListLngp;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataListLngpByEscrow;
import com.application.bris.ikurma_nos_konsumer.model.prapen.ReqUpdateLngp;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListLngpActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SearchView searchView;
    List<DataListLngp> dataListLngp =new ArrayList<>();
    List<DataListLngpByEscrow> dataListLngpByEscrow =new ArrayList<>();
    AdapterListLngp adapterLngp;
    ApiClientAdapter apiClientAdapter;
    ActivityListLngpPrapenBinding binding;
    Long idInstansi=0l;
    String escrow="";
    private int counterRefreshLngpBerhasil=0;
    private int counterRefreshLngpGagal=0;
    AppPreferences appPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityListLngpPrapenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        appPreferences=new AppPreferences(this);

        if(getIntent().hasExtra("idInstansi")){
            idInstansi=getIntent().getLongExtra("idInstansi",0);
        }

        if(getIntent().hasExtra("escrow")){
            escrow=getIntent().getStringExtra("escrow");
        }

        main();
        backgroundStatusBar();
        allOnClick();

        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListLngpActivity.super.onBackPressed();
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
        searchView.setQueryHint("Nama Instansi / LNGP");

        searchPipeline();

        return true;

    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        ListLngpActivity.this.recreate();
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
        binding.btnTambahLngp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLngpByEscrow();
//                Toast.makeText(ListLngpActivity.this, "Pura Pura mengklik", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void main(){
        setSupportActionBar(binding.toolbar.tbRegular);
        apiClientAdapter=new ApiClientAdapter(ListLngpActivity.this);

        //jangan panggil method ini kalau pake viewbinding
        binding.toolbar.tvPageTitle.setText("List LNGP");
        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);

        loadData();
//        loadDataDummy();
    }


    public void loadData() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqListLngp req=new ReqListLngp();
        req.setIdInstansi(idInstansi);
        req.setEscrow(escrow);

        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().inquiryListLngp(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                binding.rvListLngp.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DataListLngp>>() {
                        }.getType();

                        dataListLngp = gson.fromJson(listDataString, type);
                        adapterLngp = new AdapterListLngp(ListLngpActivity.this, dataListLngp);
                        binding.rvListLngp.setLayoutManager(new LinearLayoutManager(ListLngpActivity.this));
                        binding.rvListLngp.setItemAnimator(new DefaultItemAnimator());
                        binding.rvListLngp.setAdapter(adapterLngp);


                        if (dataListLngp.size() == 0) {
                            binding.ivEmptyData.setVisibility(View.VISIBLE);
                            binding.tvWhale.setVisibility(View.VISIBLE);
                        } else {
                            binding.ivEmptyData.setVisibility(View.GONE);
                            binding.tvWhale.setVisibility(View.INVISIBLE);
                        }
                    }
                    else{
                        AppUtil.notiferror(ListLngpActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ListLngpActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
            }
        });
    }
    public void getLngpByEscrow() {

        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqListLngp req=new ReqListLngp();
        req.setIdInstansi(idInstansi);
        req.setEscrow(escrow);

        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().inquiryListLngpByEscrow(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString="";
                        try{
                             listDataString = response.body().getData().toString();
                        }
                        catch (NullPointerException e){
                            e.printStackTrace();
                            listDataString="[]";
                        }

                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DataListLngpByEscrow>>() {
                        }.getType();
                        dataListLngpByEscrow = gson.fromJson(listDataString, type);

                        if(dataListLngpByEscrow.size()>0){
                            counterRefreshLngpBerhasil=0;
                            counterRefreshLngpGagal=0;
                            refreshLngp(dataListLngpByEscrow.get(0).getDescription(),dataListLngpByEscrow.get(0).getId());

                        }
                    }
                    else{
                        AppUtil.notiferror(ListLngpActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ListLngpActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
            }
        });
    }

    public void refreshLngp(String nama,String lngp) {
        AppUtil.logSecure("baksoberhasil",Integer.toString(counterRefreshLngpBerhasil));
        AppUtil.logSecure("baksogagal",Integer.toString(counterRefreshLngpGagal));
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        binding.loading.tvTextLoading.setVisibility(View.VISIBLE);
        binding.loading.tvTextLoading.setText("Menambah LNGP : "+nama+" data :"+Integer.toString(counterRefreshLngpBerhasil+counterRefreshLngpGagal+1)+" dari "+dataListLngpByEscrow.size());

        AppUtil.logSecure("baksoloading","Menambah LNGP : "+nama+" data :"+Integer.toString(counterRefreshLngpBerhasil+counterRefreshLngpGagal+1)+" dari "+dataListLngpByEscrow.size());
        ReqUpdateLngp req=new ReqUpdateLngp();
        DataListLngp dataLngp=new DataListLngp();
        dataLngp.setNoLngp(lngp);
        dataLngp.setNamaInstansi(nama);

        req.setIdInstansi(idInstansi);
        req.setUid(Integer.toString(appPreferences.getUid()));
        req.setData(dataLngp);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updateLngpInstansi(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        counterRefreshLngpBerhasil++;
                    }
                    else{
                        AppUtil.notiferror(ListLngpActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        counterRefreshLngpGagal++;
                    }
                }
                else{
                    counterRefreshLngpGagal++;
                }

                if(counterRefreshLngpGagal+counterRefreshLngpBerhasil==dataListLngpByEscrow.size()){
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    binding.loading.tvTextLoading.setVisibility(View.GONE);
                    binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
                    binding.rvListLngp.setVisibility(View.GONE);

                    loadData();
                }


                if(counterRefreshLngpBerhasil+counterRefreshLngpGagal<dataListLngpByEscrow.size()){
                    refreshLngp(dataListLngpByEscrow.get(counterRefreshLngpBerhasil+counterRefreshLngpGagal).getDescription(),dataListLngpByEscrow.get(counterRefreshLngpBerhasil+counterRefreshLngpGagal).getId());
                }

            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ListLngpActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan ketika update LNGP "+nama);
                counterRefreshLngpGagal++;
                if(counterRefreshLngpGagal+counterRefreshLngpBerhasil==dataListLngpByEscrow.size()){
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    binding.loading.tvTextLoading.setVisibility(View.GONE);
                    binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
                    binding.rvListLngp.setVisibility(View.GONE);
                    loadData();
                }
                if(counterRefreshLngpBerhasil+counterRefreshLngpGagal<dataListLngpByEscrow.size()){
                    refreshLngp(dataListLngpByEscrow.get(counterRefreshLngpBerhasil+counterRefreshLngpGagal).getDescription(),dataListLngpByEscrow.get(counterRefreshLngpBerhasil+counterRefreshLngpGagal).getId());
                }
            }
        });


    }

    private void loadDataDummy(){
        Toast.makeText(this, "Masih menggunakan data dummy", Toast.LENGTH_SHORT).show();
        DataListLngp dataDummy1=new DataListLngp();
        DataListLngp dataDummy2=new DataListLngp();
        DataListLngp dataDummy3=new DataListLngp();

        dataDummy1.setNamaInstansi("Kementrian Agama Serang");
        dataDummy1.setNoLngp("LNGP1099001212");

        dataDummy2.setNamaInstansi("Pemerintah Daerah Medan");
        dataDummy2.setNoLngp("LNGP00991121212");

        dataDummy3.setNamaInstansi("Polrestabes Malang");
        dataDummy3.setNoLngp("LNGP009991111188");
       
        dataListLngp.clear();

        dataListLngp.add(dataDummy1);
        dataListLngp.add(dataDummy2);
        dataListLngp.add(dataDummy3);

        adapterLngp = new AdapterListLngp(ListLngpActivity.this, dataListLngp);
        binding.rvListLngp.setLayoutManager(new LinearLayoutManager(ListLngpActivity.this));
        binding.rvListLngp.setItemAnimator(new DefaultItemAnimator());
        binding.rvListLngp.setAdapter(adapterLngp);


        if (dataListLngp.size() == 0) {
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
                adapterLngp.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapterLngp.getFilter().filter(query);
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
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        binding.rvListLngp.setVisibility(View.GONE);

        loadData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent=new Intent(ListLngpActivity.this, CoreLayoutActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
//        startActivity(intent);
//        finish();
    }




}