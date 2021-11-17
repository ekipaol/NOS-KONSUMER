package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_rac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityVerifRacBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelectRecycler;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiRac;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_ideb.DataIdebActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifikasiRacActivity extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, DropdownRecyclerListener, GenericListenerOnSelectRecycler {

    private VerifikasiRacAdapter dataIdebAdapter;

    private long  idAplikasi=0;
    private List<DataVerifikasiRac> dataVerifikasiRac =new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private PrapenAoActivityVerifRacBinding binding;
    List<MGenericModel> dataDropdownVerif=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=PrapenAoActivityVerifRacBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idAplikasi=Long.parseLong(getIntent().getStringExtra("idAplikasi"));

        //pantekan status untuk testing
        customToolbar();
        backgroundStatusBar();

        //initialize status
//        setData();
        setDropdownData();
        loadData();
//        initialize();
        onClicks();


    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void initialize(){
        binding.rvListVerifRac.setVisibility(View.VISIBLE);
        binding.rvListVerifRac.setHasFixedSize(true);
        dataIdebAdapter = new VerifikasiRacAdapter(VerifikasiRacActivity.this, dataVerifikasiRac,this);
        binding.rvListVerifRac.setLayoutManager(new LinearLayoutManager(VerifikasiRacActivity.this));
        binding.rvListVerifRac.setItemAnimator(new DefaultItemAnimator());
        binding.rvListVerifRac.setAdapter(dataIdebAdapter);

        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);

        //disable dlu smenetara
        binding.refresh.setEnabled(false);
    }


    public void customToolbar(){
        binding.toolbarNosearch.tvPageTitle.setText("Verifikasi RAC");

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setData(){
        //initialize status pertama
//        DataVerifikasiRac data1=new DataVerifikasiRac();
//        data1.setNamaRac("Nasabah sering menabung");
//        data1.setKetentuan("Minimal menabung 5 kali");
//        data1.setHasil("Nasabah menabung 25 kali");
//        data1.setHasilVerifikasiEngine("Sesuai");
//        data1.setHasilVerifikasiVerif("");
//        data1.setCatatan("");
//
//        DataVerifikasiRac data2=new DataVerifikasiRac();
//        data2.setNamaRac("Nasabah rajin beribadah");
//        data2.setKetentuan("Minimal salat 5 waktu");
//        data2.setHasil("Nasabah salat 5 waktu");
//        data2.setHasilVerifikasiEngine("Sesuai");
//        data2.setHasilVerifikasiVerif("");
//        data2.setCatatan("");
//
//        DataVerifikasiRac data3=new DataVerifikasiRac();
//        data3.setNamaRac("Nasabah mencuri ayam kampung di dekat pos ronda disebelah rumahnya bu RT");
//        data3.setKetentuan("Maksimal mencuri 0 kali");
//        data3.setHasil("Nasabah mencuri 1 kali");
//        data3.setHasilVerifikasiEngine("Tidak Sesuai");
//        data3.setHasilVerifikasiVerif("");
//        data3.setCatatan("");
//
//        dataVerifikasiRac.add(data1);
//        dataVerifikasiRac.add(data2);
//        dataVerifikasiRac.add(data3);

        if(dataVerifikasiRac.size()==0){
            binding.llEmptydata.setVisibility(View.VISIBLE);
        }
    }

    private void setDropdownData(){
        dataDropdownVerif.add(new MGenericModel("Sesuai","Sesuai"));
        dataDropdownVerif.add(new MGenericModel("Tidak Sesuai","Tidak Sesuai"));
    }



    @Override
    public void onSelect(String title, MGenericModel data) {

    }

    @Override
    public void onRefresh() {
        binding.refresh.setRefreshing(false);
//        binding.rvListVerifRac.setVisibility(View.GONE);
//        setData();
        loadData();
//        initialize();
    }

    private void onClicks(){
        binding.btnSimpanVerifRac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(VerifikasiRacActivity.this, TambahVerifikasiRacActivity.class);
//                startActivity(intent);
                Toast.makeText(VerifikasiRacActivity.this, "Clicking Simpan", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }

    private void loadData(){
        binding.rvListVerifRac.setVisibility(View.GONE);
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        req.setApplicationId(idAplikasi);
        //pantekan no aplikasi
//        Toast.makeText(this, "ada pantekan id aplikasi", Toast.LENGTH_SHORT).show();
//        req.setApplicationId(4);


        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryRac(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().get("ParamRACDetail").toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DataVerifikasiRac>>() {
                        }.getType();
                        dataVerifikasiRac =  gson.fromJson(listDataString, type);

                        if(dataVerifikasiRac.size()==0){
                            binding.llEmptydata.setVisibility(View.VISIBLE);
                        }

                        initialize();
                    }
                    else if (response.body().getStatus().equalsIgnoreCase("01")) {
                        binding.llEmptydata.setVisibility(View.VISIBLE);
                    }
                    else{
                        AppUtil.notiferror(VerifikasiRacActivity.this, findViewById(android.R.id.content), response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(VerifikasiRacActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
//            case R.id.tf_hasil_cek_verifikator:
//            case R.id.et_hasil_cek_verifikator:
//                DialogGenericDataFromService.display(getSupportFragmentManager(),"Hasil Cek Verifikator",dataDropdownVerif,VerifikasiRacActivity.this);
        }
    }

    @Override
    public void onDropdownRecyclerClick(int position,String title) {
        DialogGenericDataFromService.displayByPosition((getSupportFragmentManager()),"Hasil Cek Verifikator",dataDropdownVerif, VerifikasiRacActivity.this,position);

//        this.data.get(position).setHasilVerifikasiVerif("sesuai");
//        dataIdebAdapter.notifyItemChanged(position);

    }

    @Override
    public void onSelect(String title, MGenericModel dataModel, int position) {
        if(title.equalsIgnoreCase("Hasil Cek Verifikator")){
            dataVerifikasiRac.get(position).setParameterDescVerifikator(dataModel.getDESC());
            AppUtil.logSecure("setsuperdata","set posisi : "+String.valueOf(position)+" dengan nilai : "+dataModel.getDESC());
//            dataIdebAdapter.notifyItemChanged(position);
            dataIdebAdapter.notifyDataSetChanged();

        }
    }
}