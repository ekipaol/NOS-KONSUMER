package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.general;

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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityListAplikasiPrapenBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataListAplikasi;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_dedupe.DataDedupeActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.CoreLayoutActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAplikasiActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    private SearchView searchView;
    List<DataListAplikasi> dataListAplikasi;
    AdapterListAplikasi AdapterAplikasi;
    ApiClientAdapter apiClientAdapter;
    ActivityListAplikasiPrapenBinding binding;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityListAplikasiPrapenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        main();
        backgroundStatusBar();

        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListAplikasiActivity.this, CoreLayoutActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                startActivity(intent);
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
//        ListAplikasiActivity.this.recreate();
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

    public void main(){
        setSupportActionBar(binding.toolbar.tbRegular);
        apiClientAdapter=new ApiClientAdapter(ListAplikasiActivity.this);

        //jangan panggil method ini kalau pake viewbinding
//        AppUtil.toolbarRegular(this, "Putusan Gadai");
        binding.toolbar.tvPageTitle.setText("List Aplikasi");
        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);
        loadData();
    }


    public void loadData() {
        //  dataUser = getListUser();
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        AppPreferences appPreferences=new AppPreferences(ListAplikasiActivity.this);

        //pantekan uid
//        req.setUID("");
        req.setUID(String.valueOf(appPreferences.getUid()));

        Call<ParseResponseArr> call;

        if(AppUtil.checkIsPemutus(appPreferences.getFidRole())){
            call = apiClientAdapter.getApiInterface().listAplikasiPemutus(req);
        }
        else{
            call = apiClientAdapter.getApiInterface().listAplikasiMarketing(req);
        }

        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                binding.rvListAplikasi.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DataListAplikasi>>() {
                        }.getType();

                        dataListAplikasi = gson.fromJson(listDataString, type);
                        AdapterAplikasi = new AdapterListAplikasi(ListAplikasiActivity.this, dataListAplikasi);
                        binding.rvListAplikasi.setLayoutManager(new LinearLayoutManager(ListAplikasiActivity.this));
                        binding.rvListAplikasi.setItemAnimator(new DefaultItemAnimator());
                        binding.rvListAplikasi.setAdapter(AdapterAplikasi);


                        if (dataListAplikasi.size() == 0) {
                            binding.ivEmptyData.setVisibility(View.VISIBLE);
                            binding.tvWhale.setVisibility(View.VISIBLE);
                        } else {
                            binding.ivEmptyData.setVisibility(View.GONE);
                            binding.tvWhale.setVisibility(View.INVISIBLE);
                        }
                    }
                    else{
                        AppUtil.notiferror(ListAplikasiActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ListAplikasiActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
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
        binding.rvListAplikasi.setVisibility(View.GONE);

        loadData();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent=new Intent(ListAplikasiActivity.this, CoreLayoutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
        startActivity(intent);
//        finish();
    }


}