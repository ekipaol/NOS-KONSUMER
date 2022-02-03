package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.listHotprospek;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.page_aom.adapter.hotprospek.HotprospekAdapater;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.HotprospekListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.hotprospek;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotprospekActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, HotprospekListener {

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.rv_listhotprospek)
    RecyclerView rv_listhotprospek;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.ll_emptydata)
    LinearLayout ll_emptydata;

    @BindView(R.id.tv_total_hasanah)
    TextView tv_total_hasanah;

    private SearchView searchView;
    List<hotprospek> dataHotprospek;
    HotprospekAdapater adapaterHotprospek;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_hotprospek);
        main();

        if(checkPermission()){
            initializeHotprospek();
        }

//        sm_placeholder.startShimmer();
        backgroundStatusBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        initializeHotprospek();
//        sm_placeholder.startShimmer();
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

        searchHotprospek();

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
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Hasanah Card");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);
    }

    public void initializeHotprospek(){

        dataHotprospek = new ArrayList<>();
        hotprospek datahp1=new hotprospek();

        Uri uriSms = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"},null,null,null);

        cursor.moveToFirst();
        Long nilaiHasanah=0l;
        while  (cursor.moveToNext())
        {
            String address = cursor.getString(1);
            String date = cursor.getString(2);
            String body = cursor.getString(3);
            String hasanahTemp="";

            String[] spliitedBody=body.split(" ");

            for (int i = 0; i <spliitedBody.length ; i++) {
                if(spliitedBody[i].equalsIgnoreCase("IDR")){
                    nilaiHasanah=nilaiHasanah+Long.parseLong(spliitedBody[i+1].replace(".",""));
                    hasanahTemp=spliitedBody[i+1].replace(".","");
                    break;
                }
            }

            if(address.equalsIgnoreCase("BNI")){
                dataHotprospek.add(new hotprospek(address,body,date));
            }

        }

        tv_total_hasanah.setText(AppUtil.parseRupiah(Long.toString(nilaiHasanah)));





                            if (dataHotprospek.size() > 0){
                                ll_emptydata.setVisibility(View.GONE);
                                adapaterHotprospek = new HotprospekAdapater(HotprospekActivity.this, dataHotprospek, HotprospekActivity.this);
                                rv_listhotprospek.setLayoutManager(new LinearLayoutManager(HotprospekActivity.this));
                                rv_listhotprospek.setItemAnimator(new DefaultItemAnimator());
                                rv_listhotprospek.setAdapter(adapaterHotprospek);
                            }
                            else {
                                ll_emptydata.setVisibility(View.VISIBLE);
                            }

    }

    private void searchHotprospek(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    adapaterHotprospek.getFilter().filter(query);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapaterHotprospek.getFilter().filter(query);
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
        rv_listhotprospek.setVisibility(View.GONE);
//        initializeHotprospek();
        sm_placeholder.setVisibility(View.VISIBLE);
//        sm_placeholder.startShimmer();
//        HotprospekActivity.this.recreate();
    }

    @Override
    public void onHotprospekSelect(int id) {
        Intent it = new Intent(this, HotprospekDetailActivity.class);
        it.putExtra("idAplikasi", id);
        startActivity(it);
    }

    private boolean checkPermission() {
        int readSms = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        List<String> listPermissionNeeded = new ArrayList<>();


        if(readSms != PackageManager.PERMISSION_GRANTED){
            listPermissionNeeded.add(Manifest.permission.READ_SMS);
        }
        if (!listPermissionNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionNeeded.toArray(new String[listPermissionNeeded.size()]), 1);
            return false;
        }
        return true;
    }
}
