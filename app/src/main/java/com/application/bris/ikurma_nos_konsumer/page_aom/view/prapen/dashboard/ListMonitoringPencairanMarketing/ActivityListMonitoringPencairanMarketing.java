package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.dashboard.ListMonitoringPencairanMarketing;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

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

public class ActivityListMonitoringPencairanMarketing extends AppCompatActivity implements View.OnClickListener {

    ActivityListMonitoringPencairanMarketingBinding binding;
    List<DataListMonitoringMarketing> dataListAplikasi = new ArrayList<DataListMonitoringMarketing>();
    ListMonitoringPencairanMarketingAdapter AdapterAplikasi;
    private ApiClientAdapter apiClientAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListMonitoringPencairanMarketingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "List Monitoring Pencairan Marketing");
        apiClientAdapter = new ApiClientAdapter(this);
        binding.toolbar.btnBack.setOnClickListener(v -> finish());

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

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public void onClick(View v) {

    }
}
