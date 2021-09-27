package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_ideb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
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
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class VerifikasiIdebActivity extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, DropdownRecyclerListener, GenericListenerOnSelectRecycler {

    private VerifikasiIdebAdapter dataIdebAdapter;

    public static int idAplikasi=0;
    private List<DataVerifikasiIdeb> data=new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private PrapenAoActivityVerifIdebBinding binding;
    PrapenItemVerifikasiIdebBinding bindingNamaField;

    List<MGenericModel> dataDropdownVerif=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= PrapenAoActivityVerifIdebBinding.inflate(getLayoutInflater());

        //ini binding buat ngambil nama fieldnya aja
        bindingNamaField=PrapenItemVerifikasiIdebBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);

        //pantekan status untuk testing
        customToolbar();
        backgroundStatusBar();

        //initialize status
        setData();
        setDropdownData();
        initialize();
        onClicks();


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
        dataIdebAdapter = new VerifikasiIdebAdapter(VerifikasiIdebActivity.this, data,this);
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
        DataVerifikasiIdeb data1=new DataVerifikasiIdeb();
        data1.setNamaLembagaKeuangan("Bank Mandiri");
        data1.setBakiDebet("20000000");
        data1.setKualitasPembiayaan("Lancar");
        data1.setPerkiraanAngsuranBulanan("300000");
        data1.setTreatmentPembiayaan("Dilanjutkan");
        data1.setHasilVerifikasiIdeb("");

        DataVerifikasiIdeb data2=new DataVerifikasiIdeb();
        data2.setNamaLembagaKeuangan("Bank Rakyat Indonesia");
        data2.setBakiDebet("3000000");
        data2.setKualitasPembiayaan("Lancar");
        data2.setPerkiraanAngsuranBulanan("200000");
        data2.setTreatmentPembiayaan("Lunas");
        data1.setHasilVerifikasiIdeb("");

        DataVerifikasiIdeb data3=new DataVerifikasiIdeb();
        data3.setNamaLembagaKeuangan("Bank Negara Indonesia");
        data3.setBakiDebet("20000000");
        data3.setKualitasPembiayaan("Lancar");
        data3.setPerkiraanAngsuranBulanan("300000");
        data3.setTreatmentPembiayaan("Dilanjutkan");
        data1.setHasilVerifikasiIdeb("");

        data.add(data1);
        data.add(data2);
        data.add(data3);

        if(data.size()==0){
            binding.llEmptydata.setVisibility(View.VISIBLE);
        }
    }

    private void setDropdownData(){
        dataDropdownVerif.add(new MGenericModel("Pembiayaan tetap dilanjutkan","Pembiayaan tetap dilanjutkan"));
        dataDropdownVerif.add(new MGenericModel("Pembiayaan akan dilunasi melalui Pencairan","Pembiayaan akan dilunasi melalui Pencairan"));
        dataDropdownVerif.add(new MGenericModel("Pembiayaan dilakukan Take Over","Pembiayaan dilakukan Take Over"));
        dataDropdownVerif.add(new MGenericModel("Nasabah merasa pembiayaan sudah Lunas/Selesai","Nasabah merasa pembiayaan sudah Lunas/Selesai"));
        dataDropdownVerif.add(new MGenericModel("Nasabah merasa tidak memiliki pembiayaan tersebut","Nasabah merasa tidak memiliki pembiayaan tersebut"));
        dataDropdownVerif.add(new MGenericModel("Nasabah Merasa Membayar Tepat Waktu","Nasabah Merasa Membayar Tepat Waktu"));
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
        binding.btnSimpanVerifIdeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(VerifikasiIdebActivity.this, TambahVerifikasiIdebActivity.class);
//                startActivity(intent);
                Toast.makeText(VerifikasiIdebActivity.this, "Clicking Simpan", Toast.LENGTH_SHORT).show();

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
    public void onDropdownRecyclerClick(int position) {
        DialogGenericDataFromService.displayByPosition((getSupportFragmentManager()),bindingNamaField.tfVerifikasiFasilitas.getLabelText(),dataDropdownVerif, VerifikasiIdebActivity.this,position);

//        this.data.get(position).setHasilVerifikasiVerif("sesuai");
//        dataIdebAdapter.notifyItemChanged(position);

    }

    @Override
    public void onSelect(String title, MGenericModel dataModel, int position) {

        if(title.equalsIgnoreCase(bindingNamaField.tfVerifikasiFasilitas.getLabelText())){
            data.get(position).setHasilVerifikasiIdeb(dataModel.getDESC());
            AppUtil.logSecure("setsuperdata","set posisi : "+String.valueOf(position)+" dengan nilai : "+dataModel.getDESC());
//            dataIdebAdapter.notifyItemChanged(position);
            dataIdebAdapter.notifyDataSetChanged();

        }
    }
}