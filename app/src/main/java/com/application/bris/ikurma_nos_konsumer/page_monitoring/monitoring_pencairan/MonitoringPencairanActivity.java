package com.application.bris.ikurma_nos_konsumer.page_monitoring.monitoring_pencairan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.request.monitoring.ReqMonitoringNasabah;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.model.monitoring.NasabahMonitoring;
import com.application.bris.ikurma_nos_konsumer.model.monitoring.TotalMonitoring;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;


public class MonitoringPencairanActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.rv_list_monitoring_pencairan)
    RecyclerView rv_list_monitoring_pencairan;
    @BindView(R.id.progressbar_loading)
    RelativeLayout progressbar_loading;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.animWhale)
    ImageView whale;
    @BindView(R.id.tvWhale)
    TextView tvWhale;
    @BindView(R.id.tv_total_data)
    TextView tv_total_data;



    @BindView(R.id.tv_pencapaian_pencairan)
    TextView tv_pencapaian_pencairan;
    @BindView(R.id.tv_pencapaian_os)
    TextView tv_pencapaian_os;
    @BindView(R.id.tv_pencapaian_dpk)
    TextView tv_pencapaian_dpk;
    @BindView(R.id.tv_total_kol_2)
    TextView tv_total_kol_2;
    @BindView(R.id.tv_total_npf)
    TextView tv_total_npf;

//    @BindView(R.id.fb_advanced_search)
//    FloatingActionButton fb_advanced_search;

    //sumary view components
    @BindView(R.id.bt_tampil_summary)
    Button bt_tampil_summary;
    @BindView(R.id.bt_sembunyi_summary)
    Button bt_sembunyi_summary;
    @BindView(R.id.ll_summary_monitoring)
    LinearLayout ll_summary_monitoring;


    @BindView(R.id.tv_keterangan_summary)
    TextView tv_keterangan_summary;

    //bottom sheet
    @BindView(R.id.bottom_sheet)
    LinearLayout bottom_sheet;
    @BindView(R.id.iv_capsule_close)
    ImageView iv_capsule_close;
    @BindView(R.id.sp_filter_1)
    MaterialSpinner sp_filter_1;
    @BindView(R.id.sp_filter_2)
    MaterialSpinner sp_filter_2;
    @BindView(R.id.bt_cari_filter)
    Button bt_cari_filter;



    @BindView(R.id.ll_shimmer)
    ShimmerFrameLayout shimmer;
    ApiClientAdapter apiClientAdapter;


    Call<ParseResponse> call;
    private SearchView searchView;
    List<NasabahMonitoring> dataNasabah;
    TotalMonitoring dataTotalMonitoring;
    AdapterMonitoringPencairan adapterMonitoringPencairan;

    AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring_pencairan);
        appPreferences=new AppPreferences(this);
        main();

        if(appPreferences.getFidRole()==123){

            loadDataNpf();
        }
        else{
            loadData();
        }


    }

    public void main(){
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Monitoring Nasabah");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);
        apiClientAdapter = new ApiClientAdapter(this);



        bt_tampil_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_summary_monitoring.setVisibility(View.VISIBLE);
                bt_tampil_summary.setVisibility(GONE);
                setMargins(swipeRefreshLayout,0,0,0,0);

            }
        });
        bt_sembunyi_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_summary_monitoring.setVisibility(GONE);
                bt_tampil_summary.setVisibility(View.VISIBLE);


                //margin adjustmen, because relative layout, so complicated matters to programmatically set margin
                setMargins(swipeRefreshLayout,0,20,0,0);

            }
        });


    }

    public void mainNpf(){
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Monitoring Nasabah");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);
        apiClientAdapter = new ApiClientAdapter(this);



    }

    public void loadData(){
        //  dataMutasi = getListUser();
        final AppPreferences apppref=new AppPreferences(MonitoringPencairanActivity.this);
        // progressbar_loading.setVisibility(View.VISIBLE);
        shimmer.setVisibility(View.VISIBLE);
        ReqMonitoringNasabah req = new ReqMonitoringNasabah();
        req.setUid(apppref.getUid());
        req.setNoPegawai(Long.parseLong(apppref.getNik()));

        //conditioning list yang ditampilkan
        call = apiClientAdapter.getApiInterface().listMonitoringNasabah(req);

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                // progressbar_loading.setVisibility(View.GONE);
                shimmer.setVisibility(GONE);
                if(response.isSuccessful()){

                    //tutorial overlay fitur sumary kalau data sukses
//                    AppUtil.tutorialOverlay(PerformanceAoActivity.this,bt_tampil_summary,"Sekarang anda dapat melihat summary performance pembiayaan","tutorial_summary_performance");

                    if(response.body().getStatus().equalsIgnoreCase("00")){

                        String listDataString = response.body().getData().get("listNasabah").toString();
                        String listTotalString = response.body().getData().get("summary").toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<NasabahMonitoring>>() {}.getType();
                        Type type2 = new TypeToken<TotalMonitoring>() {}.getType();
                        dataNasabah = gson.fromJson(listDataString, type);
                        dataTotalMonitoring = gson.fromJson(listTotalString, type2);


                        adapterMonitoringPencairan = new AdapterMonitoringPencairan(MonitoringPencairanActivity.this, dataNasabah);
                        rv_list_monitoring_pencairan.setLayoutManager(new LinearLayoutManager(MonitoringPencairanActivity.this));
                        rv_list_monitoring_pencairan.setItemAnimator(new DefaultItemAnimator());
                        rv_list_monitoring_pencairan.setAdapter(adapterMonitoringPencairan);

                        if(dataTotalMonitoring!=null){
                            setData();
                        }
                        else{
                            AppUtil.notiferror(MonitoringPencairanActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan pada data Summary");
                        }


                        if(dataNasabah.size()==0){
                            whale.setVisibility(View.VISIBLE);
                            tvWhale.setVisibility(View.VISIBLE);
                        }
                        else{
                            whale.setVisibility(GONE);
                            tvWhale.setVisibility(View.INVISIBLE);
                        }
                    }
                    else{
                        AppUtil.notiferror(MonitoringPencairanActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        whale.setVisibility(View.VISIBLE);
                        tvWhale.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
                AppUtil.notiferror(MonitoringPencairanActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan pada server");
            }
        });
    }

    public void loadDataNpf(){
        //  dataMutasi = getListUser();

        // progressbar_loading.setVisibility(View.VISIBLE);
        shimmer.setVisibility(View.VISIBLE);
        ReqMonitoringNasabah req = new ReqMonitoringNasabah();
        req.setUid(appPreferences.getUid());
        req.setNoPegawai(Long.parseLong(appPreferences.getNik()));

        //conditioning list yang ditampilkan
        call = apiClientAdapter.getApiInterface().listMonitoringNasabahNpf(req);

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                // progressbar_loading.setVisibility(View.GONE);
                shimmer.setVisibility(GONE);
                if(response.isSuccessful()){

                    //tutorial overlay fitur sumary kalau data sukses
//                    AppUtil.tutorialOverlay(PerformanceAoActivity.this,bt_tampil_summary,"Sekarang anda dapat melihat summary performance pembiayaan","tutorial_summary_performance");

                    if(response.body().getStatus().equalsIgnoreCase("00")){

                        String listDataString = response.body().getData().get("listNasabah").toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<NasabahMonitoring>>() {}.getType();
                        dataNasabah = gson.fromJson(listDataString, type);



                        adapterMonitoringPencairan = new AdapterMonitoringPencairan(MonitoringPencairanActivity.this, dataNasabah);
                        rv_list_monitoring_pencairan.setLayoutManager(new LinearLayoutManager(MonitoringPencairanActivity.this));
                        rv_list_monitoring_pencairan.setItemAnimator(new DefaultItemAnimator());
                        rv_list_monitoring_pencairan.setAdapter(adapterMonitoringPencairan);


                            setData();



                        if(dataNasabah.size()==0){
                            whale.setVisibility(View.VISIBLE);
                            tvWhale.setVisibility(View.VISIBLE);
                        }
                        else{
                            whale.setVisibility(GONE);
                            tvWhale.setVisibility(View.INVISIBLE);
                        }
                    }
                    else{
                        AppUtil.notiferror(MonitoringPencairanActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        whale.setVisibility(View.VISIBLE);
                        tvWhale.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
                AppUtil.notiferror(MonitoringPencairanActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan pada server");
            }
        });
    }

    private void setData(){

        if(appPreferences.getFidRole()==123){
            ll_summary_monitoring.setVisibility(GONE);
            bt_tampil_summary.setVisibility(View.INVISIBLE);
            bt_sembunyi_summary.setVisibility(GONE);
        }
        else{
            tv_pencapaian_os.setText(dataTotalMonitoring.getTotalOs()+"%");
            tv_pencapaian_dpk.setText(dataTotalMonitoring.getTotalDpk()+"%");
            tv_pencapaian_pencairan.setText(dataTotalMonitoring.getTotalPencairan()+"%");
            tv_total_kol_2.setText(dataTotalMonitoring.getTotalKol2()+"%");
            tv_total_npf.setText(dataTotalMonitoring.getTotalNpf()+"%");
        }



    }




    public static void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        MonitoringPencairanActivity.this.recreate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Nama Nasabah, Kol, Tanggal Jatuh Tempo, dll ..");

        searchPipeline();

        return true;

    }

    private void searchPipeline(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterMonitoringPencairan.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapterMonitoringPencairan.getFilter().filter(query);
                    return false;
                }
                catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public void updateTotalData(String jumlahData){
        tv_total_data.setText("Total Data : "+jumlahData);
        tv_total_data.setVisibility(View.VISIBLE);
    }

}
