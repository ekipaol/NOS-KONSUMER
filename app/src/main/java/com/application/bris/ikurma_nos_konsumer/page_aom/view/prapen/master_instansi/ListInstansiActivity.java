package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.master_instansi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqCariInstansi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityListInstansiPrapenBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataListInstansi;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListInstansiActivity extends AppCompatActivity {

    private SearchView searchView;
    List<DataListInstansi> dataListInstansi =new ArrayList<>();
    AdapterListInstansi AdapterAplikasi;
    ApiClientAdapter apiClientAdapter;
    ActivityListInstansiPrapenBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityListInstansiPrapenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        main();
        backgroundStatusBar();
        allOnClick();

        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListInstansiActivity.super.onBackPressed();
            }
        });
    }


    @Override
    protected void onRestart() {
        super.onRestart();
//        ListInstansiActivity.this.recreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        main();
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

    private void allOnClick(){
        binding.btCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(binding.etKeyword.getText().toString().isEmpty()){
//                    AppUtil.notiferror(ListInstansiActivity.this, findViewById(android.R.id.content), "Harap isi keyword pencairan");
//                }
//                else{
                    loadData();
//                }

            }
        });

        binding.btnTambahInstansi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ListInstansiActivity.this, "Input Instansi Baru", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ListInstansiActivity.this,InputMasterInstansiActivity.class);
                startActivity(intent);

            }
        });
    }

    public void main(){
        setSupportActionBar(binding.toolbar.tbRegular);
        apiClientAdapter=new ApiClientAdapter(ListInstansiActivity.this);

        //jangan panggil method ini kalau pake viewbinding
//        AppUtil.toolbarRegular(this, "Putusan Gadai");
        binding.toolbar.tvPageTitle.setText("List Instansi");

//        loadData();
//        loadDataDummy();
    }


    public void loadData() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqCariInstansi req=new ReqCariInstansi();
        AppPreferences appPreferences=new AppPreferences(ListInstansiActivity.this);

        //pantekan uid
//        req.setUID("");
        req.setKeyword(binding.etKeyword.getText().toString());

        Call<ParseResponseArr> call;

        call = apiClientAdapter.getApiInterface().cariInstansi(req);

        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                binding.rvListInstansi.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString;
                        try{
                            listDataString = response.body().getData().toString();
                        }
                        catch (NullPointerException e){
                            listDataString = "[]";
                        }

                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DataListInstansi>>() {
                        }.getType();

                        dataListInstansi = gson.fromJson(listDataString, type);
                        AdapterAplikasi = new AdapterListInstansi(ListInstansiActivity.this, dataListInstansi);
                        binding.rvListInstansi.setLayoutManager(new LinearLayoutManager(ListInstansiActivity.this));
                        binding.rvListInstansi.setItemAnimator(new DefaultItemAnimator());
                        binding.rvListInstansi.setAdapter(AdapterAplikasi);


                        if (dataListInstansi.size() == 0) {
                            binding.llEmptydata.setVisibility(View.VISIBLE);
                        } else {
                            binding.llEmptydata.setVisibility(View.GONE);
                        }
                    }
                    else{
                        AppUtil.notiferror(ListInstansiActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ListInstansiActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
            }
        });
    }

    private void loadDataDummy(){
        Toast.makeText(this, "Masih menggunakan data dummy", Toast.LENGTH_SHORT).show();
        DataListInstansi dataDummy1=new DataListInstansi();
        DataListInstansi dataDummy2=new DataListInstansi();
        DataListInstansi dataDummy3=new DataListInstansi();

        dataDummy1.setNamaInstansi("PT Mundur Maju");
        dataDummy1.setKodeInstansiInduk("XPP100");
        dataDummy1.setJenisInstansi("ASN SKPP");
        dataDummy1.setStatusInstansi("Aktif");

        dataDummy2.setNamaInstansi("PT Tak Mau Maju");
        dataDummy2.setKodeInstansiInduk("LPP91");
        dataDummy2.setJenisInstansi("ASN SKPD");
        dataDummy2.setStatusInstansi("Aktif");

        dataDummy3.setNamaInstansi("PT Selalu Mundur");
        dataDummy3.setKodeInstansiInduk("HOPP01");
        dataDummy3.setJenisInstansi("ASN SKPP");
        dataDummy3.setStatusInstansi("Tidak Aktif");

        dataListInstansi.clear();

        dataListInstansi.add(dataDummy1);
        dataListInstansi.add(dataDummy2);
        dataListInstansi.add(dataDummy3);

        AdapterAplikasi = new AdapterListInstansi(ListInstansiActivity.this, dataListInstansi);
        binding.rvListInstansi.setLayoutManager(new LinearLayoutManager(ListInstansiActivity.this));
        binding.rvListInstansi.setItemAnimator(new DefaultItemAnimator());
        binding.rvListInstansi.setAdapter(AdapterAplikasi);


        if (dataListInstansi.size() == 0) {
            binding.llEmptydata.setVisibility(View.VISIBLE);
        } else {
            binding.llEmptydata.setVisibility(View.GONE);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent=new Intent(ListInstansiActivity.this, CoreLayoutActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
//        startActivity(intent);
//        finish();
    }




}