package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_ideb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.SimpanIdebOjk;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UploadImage;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoDataIdebActivityBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataIdeb;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.DetilAplikasiActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataIdebActivity extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener, ConfirmListener {

    private DataIdebAdapter dataIdebAdapter;

    public static long idAplikasi=0;
    private List<DataIdeb> dataIdeb =new ArrayList<>();
    private UploadImage dataPdf;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private PrapenAoDataIdebActivityBinding binding;
    List<MGenericModel> dataDropdownFlow=new ArrayList<>();
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=PrapenAoDataIdebActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        bottomSheetBehavior=BottomSheetBehavior.from(binding.bottomSheet.bottomSheet);
        idAplikasi=Long.parseLong(getIntent().getStringExtra("idAplikasi"));

        //pantekan status untuk testing
        customToolbar();
        backgroundStatusBar();

        //initialize status
//        setData();
//        initialize();
        loadData();
        onClicks();


    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void initialize(){
        binding.rvListDataIdeb.setVisibility(View.VISIBLE);
        binding.rvListDataIdeb.setHasFixedSize(true);
        dataIdebAdapter = new DataIdebAdapter(DataIdebActivity.this, dataIdeb);
        binding.rvListDataIdeb.setLayoutManager(new LinearLayoutManager(DataIdebActivity.this));
        binding.rvListDataIdeb.setItemAnimator(new DefaultItemAnimator());
        binding.rvListDataIdeb.setAdapter(dataIdebAdapter);

        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);
    }


    public void customToolbar(){
        binding.toolbarNosearch.tvPageTitle.setText("Data IDEB");

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog.DialogBackpress(DataIdebActivity.this);
            }
        });
    }

    private void setData(){
        //initialize status pertama
        DataIdeb data1=new DataIdeb();
        data1.setNamaLembagaKeuangan("Bank Mandiri");
        data1.setBakiDebet("20000000");
        data1.setKualitasPembiayaan("Lancar");
        data1.setPerkiraanAngsuranBulanan("300000");
        data1.setTreatmentPembiayaan("Dilanjutkan");

        DataIdeb data2=new DataIdeb();
        data2.setNamaLembagaKeuangan("Bank Rakyat Indonesia");
        data2.setBakiDebet("3000000");
        data2.setKualitasPembiayaan("Lancar");
        data2.setPerkiraanAngsuranBulanan("200000");
        data2.setTreatmentPembiayaan("Lunas");

        DataIdeb data3=new DataIdeb();
        data3.setNamaLembagaKeuangan("Bank Negara Indonesia");
        data3.setBakiDebet("20000000");
        data3.setKualitasPembiayaan("Lancar");
        data3.setPerkiraanAngsuranBulanan("300000");
        data3.setTreatmentPembiayaan("Dilanjutkan");

        dataIdeb.add(data1);
        dataIdeb.add(data2);
        dataIdeb.add(data3);

        if(dataIdeb.size()==0){
            binding.llEmptydata.setVisibility(View.VISIBLE);
        }
    }

    private void loadData(){
        binding.rvListDataIdeb.setVisibility(View.GONE);
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        //pantekan no aplikasi
//        Toast.makeText(this, "ada pantekan id aplikasi", Toast.LENGTH_SHORT).show();
//        req.setApplicationId(4);
        req.setApplicationId(idAplikasi);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryIdebOjk(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().get("DataInquiryIDEB").toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DataIdeb>>() {
                        }.getType();
                        dataIdeb =  gson.fromJson(listDataString, type);

                        if(dataIdeb.size()==0){
                            binding.llEmptydata.setVisibility(View.VISIBLE);
                        }

                        initialize();
                    }
                    else{
                        AppUtil.notiferror(DataIdebActivity.this, findViewById(android.R.id.content), response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataIdebActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    private void downloadIdeb(){
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        //pantekan no aplikasi
//        Toast.makeText(this, "ada pantekan id aplikasi", Toast.LENGTH_SHORT).show();
        req.setApplicationId(idAplikasi);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().downloadIdeb(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<UploadImage>() {}.getType();
                        dataPdf=gson.fromJson(listDataString, type);

                        if(dataPdf!=null){
                            AppUtil.convertBase64ToFileAutoOpen(DataIdebActivity.this,dataPdf.getImg(),"idebPdf");
                        }
                    }
                    else{
                        AppUtil.notiferror(DataIdebActivity.this, findViewById(android.R.id.content), response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataIdebActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    private void simpanIdeb(){
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        SimpanIdebOjk req=new SimpanIdebOjk();
        //pantekan no aplikasi
//        Toast.makeText(this, "ada pantekan id aplikasi", Toast.LENGTH_SHORT).show();
        req.setApplicationId(idAplikasi);
        req.setCatatan(binding.bottomSheet.extendedCatatan.getText().toString());
        req.setNama(appPreferences.getNama());
        req.setUID(Integer.toString(appPreferences.getUid()));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().simpanIdebOjk(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        CustomDialog.DialogSuccess(DataIdebActivity.this, "Success!", "Simpan Data IDEB sukses", DataIdebActivity.this);

                    }
                    else{
                        AppUtil.notiferror(DataIdebActivity.this, findViewById(android.R.id.content), response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataIdebActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }



    @Override
    public void onSelect(String title, MGenericModel data) {

    }

    @Override
    public void onRefresh() {
        binding.refresh.setRefreshing(false);
        binding.rvListDataIdeb.setVisibility(View.GONE);
        loadData();
//        setData();
//        initialize();
    }

    private void onClicks(){
        binding.btnDownloadIdeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              downloadIdeb();
            }
        });


        binding.btnSimpanDataPembiayaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.bottomSheet.extendedCatatan.setEnabled(true);
                binding.bottomSheet.extendedCatatan.setFocusable(true);

                binding.bottomSheet.extendedCatatan.requestFocus();
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        binding.bottomSheet.ivCapsuleClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        binding.bottomSheet.btnSimpanDataIdeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpanIdeb();
//                Toast.makeText(DataIdebActivity.this, "Data IDEB Sudah Disimpan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            //close bottom sheet on click outside
            if (bottomSheetBehavior.getState()==BottomSheetBehavior.STATE_EXPANDED) {

                Rect outRect = new Rect();
                binding.bottomSheet.bottomSheet.getGlobalVisibleRect(outRect);

                if(!outRect.contains((int)ev.getRawX(), (int)ev.getRawY()))
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        CustomDialog.DialogBackpress(DataIdebActivity.this);
    }

    @Override
    public void success(boolean val) {
        finish();
    }

    @Override
    public void confirm(boolean val) {

    }
}