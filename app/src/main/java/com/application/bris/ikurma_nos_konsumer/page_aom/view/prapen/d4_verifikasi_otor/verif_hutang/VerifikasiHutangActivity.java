package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_hutang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityVerifHutangBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenItemVerifikasiHutangBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelectRecycler;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiHutang;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiIdeb;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_hutang.DataHutangActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_hutang.TambahDataHutangActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_ideb.VerifikasiIdebActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifikasiHutangActivity extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, DropdownRecyclerListener, GenericListenerOnSelectRecycler {

    private VerifikasiHutangAdapter dataHutangAdapter;

    public long idAplikasi=0;
    private List<DataVerifikasiHutang> data=new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private PrapenAoActivityVerifHutangBinding binding;
    private PrapenItemVerifikasiHutangBinding bindingNamaField;

    List<MGenericModel> dataDropdownVerif=new ArrayList<>();
    List<MGenericModel> dataDropdownHasil=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=PrapenAoActivityVerifHutangBinding.inflate(getLayoutInflater());

        //ini binding buat ngambil nama fieldnya aja
        bindingNamaField=PrapenItemVerifikasiHutangBinding.inflate(getLayoutInflater());

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

//        initialize();
        onClicks();
        otherViewChanges();
        loadData();
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void initialize(){
        binding.rvListVerifHutang.setVisibility(View.VISIBLE);
        binding.rvListVerifHutang.setHasFixedSize(true);
        dataHutangAdapter = new VerifikasiHutangAdapter(VerifikasiHutangActivity.this, data,this);
        binding.rvListVerifHutang.setLayoutManager(new LinearLayoutManager(VerifikasiHutangActivity.this));
        binding.rvListVerifHutang.setItemAnimator(new DefaultItemAnimator());
        binding.rvListVerifHutang.setAdapter(dataHutangAdapter);

        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);

        //disable dlu smenetara
        binding.refresh.setEnabled(false);
    }


    public void customToolbar(){
        binding.toolbarNosearch.tvPageTitle.setText("Verifikasi Hutang");

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setData(){
        //initialize status pertama
//        DataVerifikasiHutang data1=new DataVerifikasiHutang();
//        data1.setNamaPemberiHutang("Koperasi Maju Jaya");
//        data1.setAngsuranBulanan("3000000");
//        data1.setNominalPinjaman("30000000");
//        data1.setSisaJangkaWaktu("180");
//        data1.setTreatmentPembiayaan("Dilanjutkan");
//        data1.setVerifikasiFasilitas("");
//        data1.setHasilVerifikasi("");
//
//        DataVerifikasiHutang data2=new DataVerifikasiHutang();
//        data2.setNamaPemberiHutang("Koperasi Mundur Roya");
//        data2.setAngsuranBulanan("2000000");
//        data2.setNominalPinjaman("20000000");
//        data2.setSisaJangkaWaktu("120");
//        data2.setTreatmentPembiayaan("Dilanjutkan");
//        data2.setVerifikasiFasilitas("");
//        data2.setHasilVerifikasi("");
//
//        DataVerifikasiHutang data3=new DataVerifikasiHutang();
//        data3.setNamaPemberiHutang("Koperasi Diam Saja");
//        data3.setAngsuranBulanan("1000000");
//        data3.setNominalPinjaman("10000000");
//        data3.setSisaJangkaWaktu("60");
//        data3.setTreatmentPembiayaan("Dilanjutkan");
//        data3.setVerifikasiFasilitas("");
//        data2.setHasilVerifikasi("");
//
//        data.add(data1);
//        data.add(data2);
//        data.add(data3);
//
//        if(data.size()==0){
//            binding.llEmptydata.setVisibility(View.VISIBLE);
//        }
    }

    private void setDropdownData(){
        dataDropdownVerif.add(new MGenericModel("Pembiayaan tetap dilanjutkan","Pembiayaan tetap dilanjutkan"));
        dataDropdownVerif.add(new MGenericModel("Pembiayaan akan dilunasi melalui Pencairan","Pembiayaan akan dilunasi melalui Pencairan"));
        dataDropdownVerif.add(new MGenericModel("Pembiayaan dilakukan Take Over","Pembiayaan dilakukan Take Over"));

        dataDropdownHasil.add(new MGenericModel("Sesuai","Sesuai"));
        dataDropdownHasil.add(new MGenericModel("Tidak Sesuai","Tidak Sesuai"));
    }



    @Override
    public void onSelect(String title, MGenericModel data) {

    }

    @Override
    public void onRefresh() {
        binding.refresh.setRefreshing(false);
//        binding.rvListVerifFitur.setVisibility(View.GONE);
//        setData();
//        initialize();
    }


    private void onClicks(){
        binding.btnSimpanVerifHutang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(VerifikasiHutangActivity.this, TambahVerifikasiHutangActivity.class);
//                startActivity(intent);
                Toast.makeText(VerifikasiHutangActivity.this, "Clicking Simpan", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        binding.btnTambahDataHutangVerif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(VerifikasiHutangActivity.this, TambahHutangVerifikatorActivity.class);
                startActivity(intent);

            }
        });

    }

    private void loadData(){
        binding.rvListVerifHutang.setVisibility(View.GONE);
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        req.setApplicationId(idAplikasi);
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().inquiryVerifikasiHutang(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DataVerifikasiHutang>>() {
                        }.getType();
                        data =  gson.fromJson(listDataString, type);

                        if(data.size()==0){
                            binding.llEmptydata.setVisibility(View.VISIBLE);
                        }

                        initialize();
                    }
                    else if (response.body().getStatus().equalsIgnoreCase("01")) {
                        binding.llEmptydata.setVisibility(View.VISIBLE);
                    }
                    else{
                        AppUtil.notiferror(VerifikasiHutangActivity.this, findViewById(android.R.id.content), response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(VerifikasiHutangActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
//            case R.id.tf_hasil_cek_verifikator:
//            case R.id.et_hasil_cek_verifikator:
//                DialogGenericDataFromService.display(getSupportFragmentManager(),"Hasil Cek Verifikator",dataDropdownVerif,VerifikasiHutangActivity.this);
        }
    }

    @Override
    public void onDropdownRecyclerClick(int position,String title) {
        if(title.equalsIgnoreCase(bindingNamaField.tfVerifikasiFasilitas.getLabelText())){
            DialogGenericDataFromService.displayByPosition((getSupportFragmentManager()),bindingNamaField.tfVerifikasiFasilitas.getLabelText(),dataDropdownVerif, VerifikasiHutangActivity.this,position);
        }
        else if(title.equalsIgnoreCase(bindingNamaField.tfHasilVerifikasi.getLabelText())){
            DialogGenericDataFromService.displayByPosition((getSupportFragmentManager()),bindingNamaField.tfHasilVerifikasi.getLabelText(),dataDropdownHasil, VerifikasiHutangActivity.this,position);
        }

//        this.data.get(position).setHasilVerifikasiVerif("sesuai");
//        dataIdebAdapter.notifyItemChanged(position);

    }

    @Override
    public void onSelect(String title, MGenericModel dataModel, int position) {

        if(title.equalsIgnoreCase(bindingNamaField.tfVerifikasiFasilitas.getLabelText())){
            data.get(position).setHasilVerifikasi(dataModel.getDESC());
//            AppUtil.logSecure("setsuperdata","set posisi : "+String.valueOf(position)+" dengan nilai : "+dataModel.getDESC());

            dataHutangAdapter.notifyDataSetChanged();

        }
       else if(title.equalsIgnoreCase(bindingNamaField.tfHasilVerifikasi.getLabelText())){
            data.get(position).setHasilVerifikasi(dataModel.getDESC());

            dataHutangAdapter.notifyDataSetChanged();

        }
    }

    private void otherViewChanges(){
        //biar keyboard gak nongol di awal activity kalau ada edittext
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}