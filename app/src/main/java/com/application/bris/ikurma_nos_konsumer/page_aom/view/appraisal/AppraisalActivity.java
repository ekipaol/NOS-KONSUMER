package com.application.bris.ikurma_nos_konsumer.page_aom.view.appraisal;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.listAppraisal;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.page_aom.adapter.appraisal.AppraisalAdapater;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ApproveRejectListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.Appraisal;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.HotprospekEditActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.CoreLayoutActivity;
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

public class AppraisalActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, ApproveRejectListener {

//    @BindView(R.id.tb_regular)
//    Toolbar tb_regular;
    @BindView(R.id.tb_custom)
    Toolbar tb_custom;
    @BindView(R.id.rv_listapproved)
    RecyclerView rv_listapproved;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.ll_emptydata)
    LinearLayout ll_emptydata;
    @BindView(R.id.tv_empty)
    TextView tv_empty;

    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_page_title)
    TextView tv_page_title;
    @BindView(R.id.btn_right)
    ImageView btn_right;

    private SearchView searchView;
    List<Appraisal> dataAppraisal;
    AppraisalAdapater adapaterAppraisal;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_appraisal);
        main();
        backgroundStatusBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        rv_listapproved.setVisibility(View.GONE);
        initializeAppraisal();
        sm_placeholder.startShimmer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm_placeholder.stopShimmer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Nama Nasabah, Produk, dll ..");

        searchAppraisal();

        return true;

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
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        ButterKnife.bind(this);
        setSupportActionBar(tb_custom);
//        AppUtil.toolbarRegular(this, "Daftar Appraisal");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);

        customToolbar();
    }

    public void customToolbar(){
        btn_right.setVisibility(View.VISIBLE);
        tv_page_title.setText("Daftar Appraisal");
        btn_right.setImageResource(R.drawable.clock);

        //kalok dia mencet back, di pojok kiri atas, halaman home gak loading lagi, jadi gak berat broooo
        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AppraisalActivity.this, CoreLayoutActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                startActivity(intent);
            }
        });

//        btn_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_right.setEnabled(false);
                Intent it = new Intent(AppraisalActivity.this, HotprospekEditActivity.class);
                startActivity(it);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_right.setEnabled(true);
                    }
                }, 5000);
            }
        });
    }

    public void initializeAppraisal(){
        listAppraisal req = new listAppraisal();
        req.setUid(String.valueOf(appPreferences.getUid()));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().listPenilaianAgunan(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                rv_listapproved.setVisibility(View.VISIBLE);
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                try {
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().get("listQueue").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<Appraisal>>() {}.getType();
                            dataAppraisal = gson.fromJson(listDataString, type);
                            if (dataAppraisal.size() > 0){
                                ll_emptydata.setVisibility(View.GONE);
                                adapaterAppraisal = new AppraisalAdapater(AppraisalActivity.this, dataAppraisal, AppraisalActivity.this);
//                                adapaterAppraisal.notifyDataSetChanged();
                                rv_listapproved.setLayoutManager(new LinearLayoutManager(AppraisalActivity.this));
                                rv_listapproved.setItemAnimator(new DefaultItemAnimator());
                                rv_listapproved.setAdapter(adapaterAppraisal);

                                //reset status appraisal di halaman list, karena setiap ingin appraisal harus lewat list ini dulu
                                //gak bisa di reset di halmaan detailHotprospekAppraisal, karena dia akan nilainya kosong terus. udah intinya gitu
                                //kamu jangan banyak cakap, percaya aja sama saya

                                appPreferences.setStatusAppraise("");


                            }
                            else {
                                ll_emptydata.setVisibility(View.VISIBLE);
                                tv_empty.setText(getString(R.string.title_nodataappraisal));
                            }
                        }
                        else{
                            AppUtil.notiferror(AppraisalActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AppraisalActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(AppraisalActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void searchAppraisal(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    adapaterAppraisal.getFilter().filter(query);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapaterAppraisal.getFilter().filter(query);
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
        swipeRefreshLayout.setRefreshing(false);
        rv_listapproved.setVisibility(View.GONE);
        initializeAppraisal();
        sm_placeholder.setVisibility(View.VISIBLE);
        sm_placeholder.startShimmer();
//        AppraisalActivity.this.recreate();
    }

    @Override
    public void onApproveRejectSelect(int cif, int idAplikasi) {
        Intent it = new Intent(this, AppraisalDetailActivity.class);
        it.putExtra("cif", cif);
        it.putExtra("idAplikasi", idAplikasi);
        startActivity(it);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent=new Intent(AppraisalActivity.this, CoreLayoutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
        startActivity(intent);
    }
}
