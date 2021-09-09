package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.history;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryHistory;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by idong on 20/06/2019.
 */

public class HistoryActivity extends AppCompatActivity{

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.vp_history)
    ViewPager vp_history;
    @BindView(R.id.tab_history)
    TabLayout tab_history;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

    private String cif;
    private int idAplikasi;
    private ApiClientAdapter apiClientAdapter;
    public String dataStatus, dataFasilitas, dataCovenance;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_history);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);

        cif = getIntent().getStringExtra("cif");
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "History");

        loadData();


    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tb_regular.setElevation(0);
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void loadData() {
        loading.setVisibility(View.VISIBLE);
        inquiryHistory req = new inquiryHistory(AppUtil.parseIntWithDefault(cif, 0), idAplikasi);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryHistory(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            dataStatus = response.body().getData().get("historyStatus").toString();
                            dataFasilitas = response.body().getData().get("historyFasilitas").toString();
                            dataCovenance = response.body().getData().get("historyCatatanPemutus").toString();

                            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
                            vp_history.setAdapter(adapter);
                            tab_history.setupWithViewPager(vp_history);
                        }
                        else{
                            AppUtil.notiferror(HistoryActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            finish();
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(HistoryActivity.this, findViewById(android.R.id.content), error.getMessage());
                        finish();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(HistoryActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                finish();
            }
        });

    }

    // Adapter for the viewpager using FragmentPagerAdapter
    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fr = null;
            Bundle bdl = new Bundle();
            switch (position){
                case 0:
                    fr = new FragmentHistoryStatus();
                    bdl.putString("dataStatus", dataStatus);
                    fr.setArguments(bdl);
                    return fr;

                case 1:
                    fr = new FragmentHistoryFasilitas();
                    bdl.putString("dataFasilitas", dataFasilitas);
                    fr.setArguments(bdl);
                    return fr;

                case 2:
                    fr = new FragmentHistoryCovenance();
                    bdl.putString("dataCovenance", dataCovenance);
                    fr.setArguments(bdl);
                    return fr;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "Status";
                case 1: return "Fasilitas";
                case 2: return "Catatan";
                default: return null;
            }
        }
    }

}
