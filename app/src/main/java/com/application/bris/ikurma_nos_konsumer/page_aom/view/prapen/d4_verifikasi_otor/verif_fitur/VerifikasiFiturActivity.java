package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_fitur;

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
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityVerifFiturBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityVerifRacBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelectRecycler;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiFitur;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiRac;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_rac.VerifikasiRacAdapter;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class VerifikasiFiturActivity extends AppCompatActivity implements GenericListenerOnSelect, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, DropdownRecyclerListener, GenericListenerOnSelectRecycler {

    private VerifikasiFiturAdapter dataIdebAdapter;

    public static int idAplikasi=0;
    private List<DataVerifikasiFitur> data=new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private PrapenAoActivityVerifFiturBinding binding;
    List<MGenericModel> dataDropdownVerif=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=PrapenAoActivityVerifFiturBinding.inflate(getLayoutInflater());
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
        binding.rvListVerifFitur.setVisibility(View.VISIBLE);
        binding.rvListVerifFitur.setHasFixedSize(true);
        dataIdebAdapter = new VerifikasiFiturAdapter(VerifikasiFiturActivity.this, data,this);
        binding.rvListVerifFitur.setLayoutManager(new LinearLayoutManager(VerifikasiFiturActivity.this));
        binding.rvListVerifFitur.setItemAnimator(new DefaultItemAnimator());
        binding.rvListVerifFitur.setAdapter(dataIdebAdapter);

        binding.refresh.setOnRefreshListener(this);
        binding.refresh.setDistanceToTriggerSync(220);

        //disable dlu smenetara
        binding.refresh.setEnabled(false);
    }


    public void customToolbar(){
        binding.toolbarNosearch.tvPageTitle.setText("Verifikasi Fitur");

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setData(){
        //initialize status pertama
        DataVerifikasiFitur data1=new DataVerifikasiFitur();
        data1.setNamaFitur("Fitur Menggandakan Laba");
        data1.setKetentuan("Minimal menabung 5 kali");
        data1.setHasil("Nasabah menabung 25 kali");
        data1.setHasilVerifikasiEngine("Sesuai");
        data1.setHasilVerifikasiVerif("");
        data1.setCatatan("");

        DataVerifikasiFitur data2=new DataVerifikasiFitur();
        data2.setNamaFitur("Fitur Menerawang Jodoh");
        data2.setKetentuan("Minimal salat 5 waktu");
        data2.setHasil("Nasabah salat 5 waktu");
        data2.setHasilVerifikasiEngine("Tidak Sesuai");
        data2.setHasilVerifikasiVerif("");
        data2.setCatatan("");

        DataVerifikasiFitur data3=new DataVerifikasiFitur();
        data3.setNamaFitur("Fitur Merendam Amarah");
        data3.setKetentuan("Maksimal mencuri 0 kali");
        data3.setHasil("Nasabah mencuri 1 kali");
        data3.setHasilVerifikasiEngine("Sesuai");
        data3.setHasilVerifikasiVerif("");
        data3.setCatatan("");

        data.add(data1);
        data.add(data2);
        data.add(data3);

        if(data.size()==0){
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
//        binding.rvListVerifFitur.setVisibility(View.GONE);
//        setData();
//        initialize();
    }

    private void onClicks(){
        binding.btnSimpanVerifFitur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(VerifikasiFiturActivity.this, TambahVerifikasiFiturActivity.class);
//                startActivity(intent);
                Toast.makeText(VerifikasiFiturActivity.this, "Clicking Simpan", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
//            case R.id.tf_hasil_cek_verifikator:
//            case R.id.et_hasil_cek_verifikator:
//                DialogGenericDataFromService.display(getSupportFragmentManager(),"Hasil Cek Verifikator",dataDropdownVerif,VerifikasiFiturActivity.this);
        }
    }

    @Override
    public void onDropdownRecyclerClick(int position,String title) {
        DialogGenericDataFromService.displayByPosition((getSupportFragmentManager()),"Hasil Cek Verifikator",dataDropdownVerif, VerifikasiFiturActivity.this,position);

//        this.data.get(position).setHasilVerifikasiVerif("sesuai");
//        dataIdebAdapter.notifyItemChanged(position);

    }

    @Override
    public void onSelect(String title, MGenericModel dataModel, int position) {
        if(title.equalsIgnoreCase("Hasil Cek Verifikator")){
            data.get(position).setHasilVerifikasiVerif(dataModel.getDESC());
            AppUtil.logSecure("setsuperdata","set posisi : "+String.valueOf(position)+" dengan nilai : "+dataModel.getDESC());
//            dataIdebAdapter.notifyItemChanged(position);
            dataIdebAdapter.notifyDataSetChanged();

        }
    }
}