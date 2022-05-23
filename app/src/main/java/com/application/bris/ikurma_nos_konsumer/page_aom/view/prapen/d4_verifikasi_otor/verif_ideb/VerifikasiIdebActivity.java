package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_ideb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UploadImage;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityVerifIdebBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenItemVerifikasiIdebBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelectRecycler;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiIdeb;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_ideb.DataIdebActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_rac.VerifikasiRacActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifikasiIdebActivity extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, DropdownRecyclerListener, GenericListenerOnSelectRecycler {

    private VerifikasiIdebAdapter dataIdebAdapter;

    public long idAplikasi=0;
    private List<DataVerifikasiIdeb> dataVerifikasiIdeb =new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private PrapenAoActivityVerifIdebBinding binding;
    PrapenItemVerifikasiIdebBinding bindingNamaField;
    private int hideButtonClickIndicator=1;

    List<MGenericModel> dataDropdownTreatmentFasilitas =new ArrayList<>();
    List<MGenericModel> dataDropdownHasilVerifikasi=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= PrapenAoActivityVerifIdebBinding.inflate(getLayoutInflater());

        //ini binding buat ngambil nama fieldnya aja
        bindingNamaField=PrapenItemVerifikasiIdebBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idAplikasi=Long.parseLong(getIntent().getStringExtra("idAplikasi"));

        //pantekan status untuk testing
        customToolbar();
        backgroundStatusBar();
        loadData();

        //initialize status
//        setData();
        setDropdownData();
//        initialize();
        onClicks();
        otherViewChanges();


    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void initialize(){
        binding.rvListVerifIdeb.setVisibility(View.VISIBLE);
        binding.rvListVerifIdeb.setHasFixedSize(true);
        dataIdebAdapter = new VerifikasiIdebAdapter(VerifikasiIdebActivity.this, dataVerifikasiIdeb,this);
        binding.rvListVerifIdeb.setLayoutManager(new LinearLayoutManager(VerifikasiIdebActivity.this));
        binding.rvListVerifIdeb.setItemAnimator(new DefaultItemAnimator());
        binding.rvListVerifIdeb.setAdapter(dataIdebAdapter);

        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);

        //disable dlu smenetara
        binding.refresh.setEnabled(false);
    }


    public void customToolbar(){
        binding.toolbarNosearch.tvPageTitle.setText("Verifikasi Ideb");

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setData(){
        //initialize status pertama
//        DataVerifikasiIdeb data1=new DataVerifikasiIdeb();
//        data1.setNamaLembagaKeuangan("Bank Mandiri");
//        data1.setBakiDebet("20000000");
//        data1.setKualitasPembiayaan("Lancar");
//        data1.setPerkiraanAngsuranBulanan("300000");
//        data1.setTreatmentPembiayaan("Dilanjutkan");
//        data1.setHasilVerifikasiIdeb("");
//        data1.setAngsuranVerifikasi("300000");
//        data1.setHasilVerifikasi("");
//
//        DataVerifikasiIdeb data2=new DataVerifikasiIdeb();
//        data2.setNamaLembagaKeuangan("Bank Rakyat Indonesia");
//        data2.setBakiDebet("3000000");
//        data2.setKualitasPembiayaan("Lancar");
//        data2.setPerkiraanAngsuranBulanan("200000");
//        data2.setTreatmentPembiayaan("Lunas");
//        data2.setHasilVerifikasiIdeb("");
//        data2.setAngsuranVerifikasi("200000");
//        data2.setHasilVerifikasi("");
//
//        DataVerifikasiIdeb data3=new DataVerifikasiIdeb();
//        data3.setNamaLembagaKeuangan("Bank Negara Indonesia");
//        data3.setBakiDebet("20000000");
//        data3.setKualitasPembiayaan("Lancar");
//        data3.setPerkiraanAngsuranBulanan("300000");
//        data3.setTreatmentPembiayaan("Dilanjutkan");
//        data3.setHasilVerifikasiIdeb("");
//        data3.setAngsuranVerifikasi("300000");
//        data3.setHasilVerifikasi("");
//
//        dataVerifikasiIdeb.add(data1);
//        dataVerifikasiIdeb.add(data2);
//        dataVerifikasiIdeb.add(data3);
//
//        binding.tvNamaAo.setText("Dummy Name");
//        binding.tvCatatanAo.setText("Ini adalah catatan saya sebagai AO yang mengaku bahwa sebenarnya isi catatan ini hanya sebagai contoh saja untuk dijadikan patokan dalam membuat desain front end");
//
//        if(dataVerifikasiIdeb.size()==0){
//            binding.llEmptydata.setVisibility(View.VISIBLE);
//        }
    }

    private void loadData(){
        binding.rvListVerifIdeb.setVisibility(View.GONE);
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        req.setApplicationId(idAplikasi);
        //pantekan no aplikasi
//        Toast.makeText(this, "ada pantekan id aplikasi", Toast.LENGTH_SHORT).show();
//        req.setApplicationId(4);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryVerifikasiIdeb(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().get("DataInquiryIDEB").toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DataVerifikasiIdeb>>() {
                        }.getType();
                        dataVerifikasiIdeb =  gson.fromJson(listDataString, type);

                        if(dataVerifikasiIdeb.size()==0){
                            binding.llEmptydata.setVisibility(View.VISIBLE);
                        }

                        initialize();
                    }
                    else if (response.body().getStatus().equalsIgnoreCase("01")) {
                        binding.llEmptydata.setVisibility(View.VISIBLE);
                    }
                    else{
                        AppUtil.notiferror(VerifikasiIdebActivity.this, findViewById(android.R.id.content), response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(VerifikasiIdebActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                
            }
        });
    }

    private void setDropdownData(){
        dataDropdownTreatmentFasilitas.add(new MGenericModel("Pembiayaan tetap dilanjutkan","Pembiayaan tetap dilanjutkan"));
        dataDropdownTreatmentFasilitas.add(new MGenericModel("Pembiayaan akan dilunasi melalui Pencairan","Pembiayaan akan dilunasi melalui Pencairan"));
        dataDropdownTreatmentFasilitas.add(new MGenericModel("Pembiayaan dilakukan Take Over","Pembiayaan dilakukan Take Over"));
        dataDropdownTreatmentFasilitas.add(new MGenericModel("Nasabah menginfokan pembiayaan sudah Lunas/Selesai","Nasabah merasa pembiayaan sudah Lunas/Selesai"));
        dataDropdownTreatmentFasilitas.add(new MGenericModel("Nasabah menginfokan tidak memiliki pembiayaan tersebut","Nasabah merasa tidak memiliki pembiayaan tersebut"));
        dataDropdownTreatmentFasilitas.add(new MGenericModel("Nasabah menginfokan membayar tepat waktu","Nasabah Merasa Membayar Tepat Waktu"));

        dataDropdownHasilVerifikasi.add(new MGenericModel("Sesuai","Sesuai"));
        dataDropdownHasilVerifikasi.add(new MGenericModel("Tidak Sesuai","Tidak Sesuai"));
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
        binding.btSembunyiCatatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hideButtonClickIndicator==0){

                    binding.llCatatanAo.animate().translationY(0).setDuration(500);
                    binding.llCatatanAo.setVisibility(View.GONE);

                    binding.btSembunyiCatatan.setVisibility(View.GONE);
                    binding.btTampilCatatan.setVisibility(View.VISIBLE);
                    hideButtonClickIndicator++;

                }

            }
        });

        binding.btTampilCatatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(hideButtonClickIndicator==1){
                    binding.cvCatatanAo.setVisibility(View.VISIBLE);
                    binding.llCatatanAo.setVisibility(View.VISIBLE);
                    binding.btTampilCatatan.setVisibility(View.GONE);
                    binding.btSembunyiCatatan.setVisibility(View.VISIBLE);
                    hideButtonClickIndicator--;
                }
            }
        });

        binding.btnSimpanVerifIdeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(VerifikasiIdebActivity.this, TambahVerifikasiIdebActivity.class);
//                startActivity(intent);
//                Toast.makeText(VerifikasiIdebActivity.this, "Clicking Simpan", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        binding.btnDownloadIdeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadIdeb();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
//            case R.id.tf_hasil_cek_verifikator:
//            case R.id.et_hasil_cek_verifikator:
//                DialogGenericDataFromService.display(getSupportFragmentManager(),"Hasil Cek Verifikator",dataDropdownVerif,VerifikasiIdebActivity.this);
        }
    }

    @Override
    public void onDropdownRecyclerClick(int position,String title) {
        if(title.equalsIgnoreCase(bindingNamaField.tfHasilVerifikasi.getLabelText())){
            DialogGenericDataFromService.displayByPosition((getSupportFragmentManager()),bindingNamaField.tfHasilVerifikasi.getLabelText(), dataDropdownHasilVerifikasi, VerifikasiIdebActivity.this,position);
        }
        else if((title.equalsIgnoreCase(bindingNamaField.tfVerifikasiFasilitas.getLabelText()))){
            DialogGenericDataFromService.displayByPosition((getSupportFragmentManager()),bindingNamaField.tfVerifikasiFasilitas.getLabelText(), dataDropdownTreatmentFasilitas, VerifikasiIdebActivity.this,position);
        }
//        this.data.get(position).setHasilVerifikasiVerif("sesuai");
//        dataIdebAdapter.notifyItemChanged(position);
    }

    @Override
    public void onSelect(String title, MGenericModel dataModel, int position) {

        if(title.equalsIgnoreCase(bindingNamaField.tfVerifikasiFasilitas.getLabelText())){
            dataVerifikasiIdeb.get(position).setTreatmentFasilitas(dataModel.getDESC());
            AppUtil.logSecure("setsuperdata","set posisi : "+String.valueOf(position)+" dengan nilai : "+dataModel.getDESC());
//            dataIdebAdapter.notifyItemChanged(position);
            dataIdebAdapter.notifyDataSetChanged();

        }
        else   if(title.equalsIgnoreCase(bindingNamaField.tfHasilVerifikasi.getLabelText())){
            dataVerifikasiIdeb.get(position).setTreatmentFasilitas(dataModel.getDESC());
            dataIdebAdapter.notifyDataSetChanged();

        }
    }

    private void otherViewChanges(){
        //biar keyboard gak nongol di awal activity kalau ada edittext
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        binding.btTampilCatatan.setVisibility(View.GONE);
    }

    private void downloadIdeb(){
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        //pantekan no aplikasi
//        Toast.makeText(this, "ada pantekan id aplikasi", Toast.LENGTH_SHORT).show();
        req.setApplicationId(idAplikasi);
        req.setUID(String.valueOf(appPreferences.getUid()));
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
                        UploadImage dataPdf=gson.fromJson(listDataString, type);

                        if(dataPdf!=null){
                            AppUtil.convertBase64ToFileAutoOpen(VerifikasiIdebActivity.this,dataPdf.getImg(),"verinIdebPdf");
                        }
                    }
                    else{
                        AppUtil.notiferror(VerifikasiIdebActivity.this, findViewById(android.R.id.content), response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(VerifikasiIdebActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                
            }
        });
    }
}