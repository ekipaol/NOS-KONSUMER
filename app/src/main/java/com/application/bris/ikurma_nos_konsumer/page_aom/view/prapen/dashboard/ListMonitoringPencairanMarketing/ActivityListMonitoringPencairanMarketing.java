package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.dashboard.ListMonitoringPencairanMarketing;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.DataListMonitoringMarketing;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityListMonitoringPencairanMarketingBinding;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityListMonitoringPencairanMarketing extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private SearchView searchView;
    ActivityListMonitoringPencairanMarketingBinding binding;
    List<DataListMonitoringMarketing> dataListAplikasi = new ArrayList<DataListMonitoringMarketing>();
    ListMonitoringPencairanMarketingAdapter AdapterAplikasi;
    private ApiClientAdapter apiClientAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListMonitoringPencairanMarketingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setSupportActionBar(binding.toolbar.tbRegular);
        binding.toolbar.tvPageTitle.setText("Monitoring Pencairan");
        setContentView(view);
        backgroundStatusBar();
//        AppUtil.toolbarRegular(this, "List Monitoring Pencairan Marketing");

        apiClientAdapter = new ApiClientAdapter(this);
        binding.toolbar.btnBack.setOnClickListener(v -> finish());

        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);

        initdata();
    }

    private void initdata() {

        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req = new ReqUidIdAplikasi();
//        AppPreferences appPreferences = new AppPreferences(ActivityListMonitoringPencairanMarketing.this);
        //pantekan uid
//        req.setUID(String.valueOf(appPreferences.getUid()));
        req.setUID("4943");
//        req.setRole((appPreferences.getFidRole()));
        Call<ParseResponseArr> call;
        call = apiClientAdapter.getApiInterface().ListMonitoringPencairanMarketing(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                binding.rvListAplikasi.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {

                    try {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DataListMonitoringMarketing>>() {
                        }.getType();

                        dataListAplikasi = gson.fromJson(listDataString, type);

                        AdapterAplikasi = new ListMonitoringPencairanMarketingAdapter(ActivityListMonitoringPencairanMarketing.this, dataListAplikasi);
                        binding.rvListAplikasi.setLayoutManager(new LinearLayoutManager(ActivityListMonitoringPencairanMarketing.this));
                        binding.rvListAplikasi.setItemAnimator(new DefaultItemAnimator());
                        binding.rvListAplikasi.setAdapter(AdapterAplikasi);


                        if (dataListAplikasi.size() == 0) {
                            binding.ivEmptyData.setVisibility(View.VISIBLE);
                            binding.tvWhale.setVisibility(View.VISIBLE);
                        } else {
                            binding.ivEmptyData.setVisibility(View.GONE);
                            binding.tvWhale.setVisibility(View.INVISIBLE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        binding.ivEmptyData.setVisibility(View.GONE);
                        binding.tvWhale.setVisibility(View.INVISIBLE);
                    }

                } else {
                    AppUtil.notiferror(ActivityListMonitoringPencairanMarketing.this, findViewById(android.R.id.content), response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityListMonitoringPencairanMarketing.this, findViewById(android.R.id.content), "Terjadi kesalahan");
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Nama Nasabah, nomor aplikasi, nomor LD dll ..");

        searchPipeline();

        return true;

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

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRefresh() {
        binding.refresh.setRefreshing(false);
//        progressbar_loading.setVisibility(View.VISIBLE);
        binding.rvListAplikasi.setVisibility(View.GONE);

        initdata();

    }
}
