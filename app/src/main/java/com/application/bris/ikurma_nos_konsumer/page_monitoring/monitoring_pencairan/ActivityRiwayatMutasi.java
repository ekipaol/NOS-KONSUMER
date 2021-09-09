package com.application.bris.ikurma_nos_konsumer.page_monitoring.monitoring_pencairan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.request.monitoring.ReqRiwayatMutasi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.model.monitoring.RiwayatMutasi;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class ActivityRiwayatMutasi extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.rv_list_riwayat_mutasi)
    RecyclerView rv_list_riwayat_mutasi;
    @BindView(R.id.progressbar_loading)
    RelativeLayout progressbar_loading;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.animWhale)
    ImageView whale;
    @BindView(R.id.tvWhale)
    TextView tvWhale;

    @BindView(R.id.tv_info)
    TextView tv_info;



    @BindView(R.id.ll_shimmer)
    ShimmerFrameLayout shimmer;
    ApiClientAdapter apiClientAdapter;
    String noRekening="";


    Call<ParseResponse> call;
    private SearchView searchView;
    List<RiwayatMutasi> dataMutasi;
    AdapterRiwayatMutasi adapterRiwayatMutasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_mutasi);
        main();
        loadData();
    }

    public void main(){
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Riwayat Mutasi");
        swipeRefreshLayout.setEnabled(false);

        //disable dulu refreshnya
//        swipeRefreshLayout.setOnRefreshListener(this);
//        swipeRefreshLayout.setDistanceToTriggerSync(220);

        apiClientAdapter = new ApiClientAdapter(this);
        noRekening=getIntent().getStringExtra("noRekening");




    }

    public void loadData(){
        //  dataMutasi = getListUser();
        final AppPreferences apppref=new AppPreferences(ActivityRiwayatMutasi.this);
        // progressbar_loading.setVisibility(View.VISIBLE);
        shimmer.setVisibility(View.VISIBLE);
        ReqRiwayatMutasi req = new ReqRiwayatMutasi();

        req.setNoRekening(noRekening);

        //pantekan rekening
//        req.setNoRekening("1033851495");
//        Toast.makeText(this, "ada harcode nomor rekening", Toast.LENGTH_LONG).show();


        //conditioning list yang ditampilkan
        call = apiClientAdapter.getApiInterface().getRiwayatMutasi(req);

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                // progressbar_loading.setVisibility(View.GONE);
                shimmer.setVisibility(GONE);
                if(response.isSuccessful()){

                    if(response.body().getStatus().equalsIgnoreCase("200")){

                        String listDataString = response.body().getData().get("mutationlist").toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<RiwayatMutasi>>() {}.getType();
                        dataMutasi = gson.fromJson(listDataString, type);
                        if(dataMutasi.get(0).getTgl_posting().substring(0,1).equalsIgnoreCase("*")){
                            dataMutasi.clear();
                        }
                        else{
                            adapterRiwayatMutasi = new AdapterRiwayatMutasi(ActivityRiwayatMutasi.this, dataMutasi);
                            rv_list_riwayat_mutasi.setLayoutManager(new LinearLayoutManager(ActivityRiwayatMutasi.this));
                            rv_list_riwayat_mutasi.setItemAnimator(new DefaultItemAnimator());
                            rv_list_riwayat_mutasi.setAdapter(adapterRiwayatMutasi);
                        }
//                        adapterRiwayatMutasi = new AdapterRiwayatMutasi(ActivityRiwayatMutasi.this, dataMutasi);
//                        rv_list_riwayat_mutasi.setLayoutManager(new LinearLayoutManager(ActivityRiwayatMutasi.this));
//                        rv_list_riwayat_mutasi.setItemAnimator(new DefaultItemAnimator());
//                        rv_list_riwayat_mutasi.setAdapter(adapterRiwayatMutasi);


                        if(dataMutasi.size()==0){
                            whale.setVisibility(View.VISIBLE);
                            tvWhale.setVisibility(View.VISIBLE);
                        }
                        else{
                            whale.setVisibility(GONE);
                            tvWhale.setVisibility(View.INVISIBLE);
                        }
                    }
                    else{
                        AppUtil.notiferror(ActivityRiwayatMutasi.this, findViewById(android.R.id.content), response.body().getMessage());
                        whale.setVisibility(View.VISIBLE);
                        tvWhale.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
                AppUtil.notiferror(ActivityRiwayatMutasi.this, findViewById(android.R.id.content), "Terjadi kesalahan pada server");
            }
        });
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
        ActivityRiwayatMutasi.this.recreate();
    }



}