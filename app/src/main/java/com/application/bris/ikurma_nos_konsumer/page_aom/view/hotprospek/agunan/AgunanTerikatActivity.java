package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogSaveAgunanFlpp;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataRekomAppraisal;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.UserAppraisalAgunanTerikat;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.appraisal.AppraisalDetailActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.HotprospekDetailKprActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArrAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqSaveAgunan;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogSaveAgunan;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.SaveAgunanListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.ListAgunan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.ListInfo;
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

public class AgunanTerikatActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, SaveAgunanListener, ConfirmListener {

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.rv_listpipeline)
    RecyclerView rv_listpipeline;
    @BindView(R.id.progressbar_loading)
    RelativeLayout progressbar_loading;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ll_emptydata)
    LinearLayout ll_emptydata;
    @BindView(R.id.ll_shimmer)
    ShimmerFrameLayout shimmer;
    @BindView(R.id.floatSearch)
    FloatingActionButton btFloat;
    @BindView(R.id.btn_save)
    Button btn_save;
    @BindView(R.id.btn_lihat_daftar_agunan)
    Button btn_lihat_daftar_agunan;

    private SearchView searchView;
    List<ListAgunan> dataAgunan;
    List<ListInfo> dataInfo;
    UserAppraisalAgunanTerikat dataUserAppraisal;
    DataRekomAppraisal dataRekomAppraisal;
    AgunanTerikatAdapater adapaterAgunan;
    ApiClientAdapter apiClientAdapter;
    private long total_plafon = 0;
    private int flagAgunan;
    private String idProgram ="";
    String idAplikasi,jenisAo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agunan);
        idAplikasi = getIntent().getStringExtra("idAplikasi");
        jenisAo=getIntent().getStringExtra("jenisAo");
        flagAgunan=getIntent().getIntExtra("flagAgunan",0);

        if(getIntent().hasExtra("idProgram")){
            idProgram =getIntent().getStringExtra("idProgram");
        }

        backgroundStatusBar();

    }

    @Override
    public void onBackPressed() {
        if(jenisAo.equalsIgnoreCase("pemrakarsa")){
            Intent it = new Intent(AgunanTerikatActivity.this, HotprospekDetailKprActivity.class);
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(it);
        }
        else{
            Intent it = new Intent(AgunanTerikatActivity.this, AppraisalDetailActivity.class);
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(it);
        }

        finish();
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
    protected void onResume() {
        super.onResume();
        loadData();
        main();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        AgunanTerikatActivity.this.recreate();
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
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSaveAgunan();
            }
        });

        btFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AgunanTerikatActivity.this, AgunanByCifActivity.class);
                intent.putExtra("cif", getIntent().getStringExtra("cif"));
                intent.putExtra("idAplikasi", getIntent().getStringExtra("idAplikasi"));
                intent.putExtra("jenisAo", jenisAo);
                intent.putExtra("flagAgunan", flagAgunan);
                intent.putExtra("idProgram", idProgram);
                startActivity(intent);
            }
        });

        btn_lihat_daftar_agunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AgunanTerikatActivity.this, AgunanByCifActivity.class);
                intent.putExtra("cif", getIntent().getStringExtra("cif"));
                intent.putExtra("idAplikasi", getIntent().getStringExtra("idAplikasi"));
                intent.putExtra("jenisAo", jenisAo);
                intent.putExtra("flagAgunan", flagAgunan);
                startActivity(intent);
            }
        });


    }

    private void openSaveAgunan(){

        //khusus flpp
        if(idProgram.equalsIgnoreCase("222")){
            if(dataAgunan==null||dataAgunan.size()<=0){
                AppUtil.notiferror(AgunanTerikatActivity.this, findViewById(android.R.id.content), "Belum Dapat Menyimpan Agunan, Harap Isi Data Agunan Terlebih Dahulu");
            }
            else{
                DialogSaveAgunanFlpp.display(getSupportFragmentManager(), AgunanTerikatActivity.this);
            }

        }

        //kpr normal
        else{
            if (dataRekomAppraisal==null){
//            Log.d("datarekomappraisal","datanya null");
                AppUtil.notiferror(AgunanTerikatActivity.this, findViewById(android.R.id.content), "Belum Dapat Menyimpan Agunan, Harap Isi Data Agunan Terlebih Dahulu");
            }
            else{
//            Log.d("datarekomappraisal",dataRekomAppraisal.getDESC1());
                DialogSaveAgunan.display(getSupportFragmentManager(), AgunanTerikatActivity.this,Integer.parseInt(dataRekomAppraisal.getID_REKOMENDASI()));
            }
        }


    }

    public void loadData() {
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Daftar Agunan Terikat");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);
        apiClientAdapter = new ApiClientAdapter(AgunanTerikatActivity.this);
        shimmer.setVisibility(View.VISIBLE);
        AppPreferences appPreferences = new AppPreferences(AgunanTerikatActivity.this);

        //reset preference untuk uid dan nama Ao setiap kali nembak kesini, jadi uidd dan nama akan berubah tergantung aplikasi
        appPreferences.setUidAppraisal("");
        appPreferences.setNamaAppraisal("");

        otherViewChanges();

        ReqAgunan req = new ReqAgunan();

        req.setIdCif(getIntent().getStringExtra("cif"));
        req.setIdApl(getIntent().getStringExtra("idAplikasi"));
        Call<ParseResponseArrAgunan> call = apiClientAdapter.getApiInterface().listAgunan(req);
        call.enqueue(new Callback<ParseResponseArrAgunan>() {
            @Override
            public void onResponse(Call<ParseResponseArrAgunan> call, Response<ParseResponseArrAgunan> response) {
                shimmer.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    String listDataInfo = response.body().getInfo().toString();

                    //masih diambil indeks ke 0 dulu untuk sementara, jadi belom mengakomodasi riwayat AO silang, hanya diambil AO pertama

                    String dataUserAppraisalString;

                    String dataRekomAppraisalString;




                    Gson gson = new Gson();
                    Type type2 = new TypeToken<List<ListInfo>>() {
                    }.getType();
                    dataInfo = gson.fromJson(listDataInfo, type2);
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Type type = new TypeToken<List<ListAgunan>>() {
                        }.getType();

                        //ambil data user appraisal
                        if(response.body().getDtUserApraisal()!=null&&response.body().getDtUserApraisal().size()!=0){
                            dataUserAppraisalString = response.body().getDtUserApraisal().get(0).toString();
                        }
                        else{
                            dataUserAppraisalString = "";
                        }


                        //ambil data rekom appraisal
                        if(response.body().getDtUserApraisal()!=null&&response.body().getDtUserApraisal().size()!=0){
                            dataRekomAppraisalString = response.body().getDtRekomApraisal().get(0).toString();
                        }
                        else{
                            dataRekomAppraisalString = "";
                        }

                        dataAgunan = gson.fromJson(listDataString, type);
                        dataUserAppraisal=gson.fromJson(dataUserAppraisalString,UserAppraisalAgunanTerikat.class);
                        dataRekomAppraisal=gson.fromJson(dataRekomAppraisalString,DataRekomAppraisal.class);
                        adapaterAgunan = new AgunanTerikatAdapater(AgunanTerikatActivity.this, dataAgunan, dataInfo,jenisAo,flagAgunan,idProgram);
                        rv_listpipeline.setLayoutManager(new LinearLayoutManager(AgunanTerikatActivity.this));
                        rv_listpipeline.setItemAnimator(new DefaultItemAnimator());
                        rv_listpipeline.setAdapter(adapaterAgunan);

                        ll_emptydata.setVisibility(View.GONE);

                        if (dataAgunan.size() == 0) {
                            ll_emptydata.setVisibility(View.VISIBLE);
                        } else {
                            ll_emptydata.setVisibility(View.GONE);
                        }

                        //session preference untuk uid dan nama appraisal, digunakan untuk dijadikan indikator apakah agunan sudah diappraisal apa belum
                        //jadi di halaman info agunan dapat disesuaikan apakah ao pemrakarsa bisa mengedit data agunan lagi apa tidak
                        if(dataUserAppraisal!=null){
                            appPreferences.setUidAppraisal(dataUserAppraisal.getFID_UID());
                            appPreferences.setNamaAppraisal(dataUserAppraisal.getNAMA_PEGAWAI());
                        }
                        else{
                            appPreferences.setUidAppraisal("");
                        }
                    } else {
//                        btn_save.setEnabled(false);
                        ll_emptydata.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    Error error = ParseResponseError.confirmEror(response.errorBody());
                    AppUtil.notiferror(AgunanTerikatActivity.this, findViewById(android.R.id.content), error.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArrAgunan> call, Throwable t) {
                shimmer.setVisibility(View.GONE);
                AppUtil.notiferror(AgunanTerikatActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void searchPipeline(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    adapaterAgunan.getFilter().filter(query);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapaterAgunan.getFilter().filter(query);
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
        swipeRefreshLayout.setRefreshing(true);
        AgunanTerikatActivity.this.recreate();
    }

    @Override
    public void onSelectMenuAgunan(int rekomendasi, String desc_rekomendasi) {
        for (int i = 0; i < dataAgunan.size(); i++) {
            total_plafon = total_plafon + Long.valueOf(dataAgunan.get(i).getNILAI_COVER_PLAFOND());
        }
        ReqSaveAgunan req = new ReqSaveAgunan();
        req.setIdApl(Integer.valueOf(getIntent().getStringExtra("idAplikasi")));
        req.setPlafond(dataAgunan.get(0).getPLAFOND_INDUK());
        req.setTotalPlafondCover(total_plafon);
        req.setRekomendasiAgunan(String.valueOf(rekomendasi));
        req.setDesc_rekomendasi(desc_rekomendasi);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().saveAgunanTerikat(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                shimmer.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {

                        CustomDialog.DialogSuccess(AgunanTerikatActivity.this, "Success!", "Berhasil Menyimpan Agunan", AgunanTerikatActivity.this);
                        Toast.makeText(AgunanTerikatActivity.this, "Berhasil Menyimpan Agunan", Toast.LENGTH_SHORT).show();
//                        finish();

                    } else if (response.body().getStatus().equalsIgnoreCase("01"))  {
                        AppUtil.notiferror(AgunanTerikatActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                } else {
                    Error error = ParseResponseError.confirmEror(response.errorBody());
                    AppUtil.notiferror(AgunanTerikatActivity.this, findViewById(android.R.id.content), error.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(AgunanTerikatActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
            }
        });
    }

    @Override
    public void success(boolean val) {
        Intent intent=new Intent(AgunanTerikatActivity.this, HotprospekDetailKprActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    @Override
    public void confirm(boolean val) {

    }

    private void otherViewChanges(){
        if(jenisAo.equalsIgnoreCase("ao silang")){
            btn_lihat_daftar_agunan.setVisibility(View.VISIBLE);
            btn_save.setVisibility(View.GONE);
            btFloat.hide();
        }
        else if(jenisAo.equalsIgnoreCase("pemrakarsa")){
            btn_lihat_daftar_agunan.setVisibility(View.GONE);
            btn_save.setVisibility(View.VISIBLE);
            btFloat.show();
        }
        else{
            btn_lihat_daftar_agunan.setVisibility(View.GONE);
            btn_save.setVisibility(View.VISIBLE);
            btFloat.show();

            jenisAo="pemrakarsa";
        }

    }
}
