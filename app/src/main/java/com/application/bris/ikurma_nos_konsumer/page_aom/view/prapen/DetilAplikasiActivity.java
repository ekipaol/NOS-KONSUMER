package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.config.menu.Menu;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityDetilAplikasiBinding;
import com.application.bris.ikurma_nos_konsumer.listener.menu.MenuClickListener;
import com.application.bris.ikurma_nos_konsumer.model.menu.ListViewSubmenuHotprospek;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.hotprospek;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_pembiayaan.DataPembiayaanActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_hutang.DataHutangActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_marketing.DataMarketingActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_nasabah.DataNasabahPrapenActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_ideb.DataIdebActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_fitur.VerifikasiFiturActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_hutang.VerifikasiHutangActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_ideb.VerifikasiIdebActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_rac.VerifikasiRacActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.memo.MemoActivity;
import com.application.bris.ikurma_nos_konsumer.page_data_nasabah.ActivityDokumenPendapatan;
import com.application.bris.ikurma_nos_konsumer.page_data_nasabah.ActivityVerifikasiPendapatan;
import com.application.bris.ikurma_nos_konsumer.page_data_nasabah.dataJaminanActivity;
import com.application.bris.ikurma_nos_konsumer.page_simulasi_angsuran.kalkulatorsimulasiangsuran;

import java.util.ArrayList;
import java.util.List;

public class DetilAplikasiActivity extends AppCompatActivity implements MenuClickListener, ConfirmListener, GenericListenerOnSelect {

    private List<ListViewSubmenuHotprospek> dataMenu;
    private SubmenuDetilAplikasiAdapter submenuDetilAplikasiAdapter;
    private GridLayoutManager layoutMenu;
    private int coloumMenu = 3;
    public static int idAplikasi=0;
    private String status;
    private hotprospek data;
    private hotprospek dataHp;
    private String dataString;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private PrapenAoActivityDetilAplikasiBinding binding;
    List<MGenericModel> dataDropdownFlow=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=PrapenAoActivityDetilAplikasiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);

        //pantekan status untuk testing
        status="Menunggu Putusan Pemutus";
        customToolbar();
        backgroundStatusBar();
        isiDataDropdown();

        //initialize status
        initializeMenu(status);
        setData();

        binding.btnUbahFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),"Lihat Flow",dataDropdownFlow,DetilAplikasiActivity.this);
            }
        });
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void initializeMenu(String namaMenu){
        binding.rvSubmenuHotprospek.setHasFixedSize(true);
        dataHp = data;
        dataMenu = getListMenu(namaMenu);
        submenuDetilAplikasiAdapter = new SubmenuDetilAplikasiAdapter(this, dataMenu, dataHp,this);
        layoutMenu = new GridLayoutManager(this, coloumMenu);
        binding.rvSubmenuHotprospek.setLayoutManager(layoutMenu);
        binding.rvSubmenuHotprospek.setAdapter(submenuDetilAplikasiAdapter);
        submenuDetilAplikasiAdapter.notifyDataSetChanged();
    }


    public void customToolbar(){
        binding.toolbarNosearch.tvPageTitle.setText("Detail Aplikasi");

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setData(){
        //initialize status pertama
        binding.btnUbahFlow.setText("Flow : "+status);

        binding.tvStatus.setText(status);
        //bla bla bla isi dari field lain
    }


    private List<ListViewSubmenuHotprospek> getListMenu(String namaMenu) {
        List<ListViewSubmenuHotprospek> menu = new ArrayList<>();
        
        if(namaMenu.equalsIgnoreCase("data entry")){
            Menu.SubmenuD1(this, menu);
        }
        else if(namaMenu.equalsIgnoreCase("confirm validasi engine")){
            Menu.SubmenuD3(this, menu);
        }
        else if(namaMenu.equalsIgnoreCase("verifikasi + otor")){
            Menu.SubmenuD4(this, menu);
        }
        else if(namaMenu.equalsIgnoreCase("confirm verifikasi")){
            Menu.SubmenuD5(this, menu);
        }
        else if(namaMenu.equalsIgnoreCase("menunggu putusan pemutus")){
            Menu.SubmenuD6(this, menu);
        }
        // TODO: 14/09/21 harus ada if status d1, maka tampil menu d1, dst
     
        return menu;
    }

    @Override
    public void onMenuClick(String menu) {

        //FLOW D1
        if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d1_data_nasabah))){
            Intent it = new Intent(this, DataNasabahPrapenActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d1_data_marketing))){
            Intent it = new Intent(this, DataMarketingActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d1_data_pembiayaan))){
            Intent it = new Intent(this, DataPembiayaanActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }

        //FLOW D3
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d3_data_hutang))){
            Intent it = new Intent(this, DataHutangActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d3_ideb))){
            Intent it = new Intent(this, DataIdebActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d3_jaminan))){
            Intent it = new Intent(this, ActivityDokumenPendapatan.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d3_data_pendapatan))){
            Intent it = new Intent(this, dataJaminanActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d3_kalkulator))){
            Intent it = new Intent(this, kalkulatorsimulasiangsuran.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }

        //D4
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d4_pendapatan_verin))){
            Intent it = new Intent(this, ActivityVerifikasiPendapatan.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d4_hasil_rac))){
            Intent it = new Intent(this, VerifikasiRacActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d4_hasil_fitur))){
            Intent it = new Intent(this, VerifikasiFiturActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d4_hutang_verin))){
            Intent it = new Intent(this, VerifikasiHutangActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d4_ideb_verin))){
            Intent it = new Intent(this, VerifikasiIdebActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }

        //MEMO
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d4_memo))){
            Intent it = new Intent(this, MemoActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
    }

    private void isiDataDropdown(){

        //status data entry pasti masuk list karena dia paling awal
        dataDropdownFlow.add(new MGenericModel("0","Data Entry"));
        // TODO: 14/09/21 tambah if status d1 maka hanya muncul data entry, if d3, maka muncul data entry dan confirm validasi engine, dst

        //d3
        if (status.equalsIgnoreCase("confirm validasi engine")){
            dataDropdownFlow.add(new MGenericModel("1","Confirm Validasi Engine"));
        }

        //d4
        else if(status.equalsIgnoreCase("Verifikasi + Otor")){
            dataDropdownFlow.add(new MGenericModel("1","Confirm Validasi Engine"));
            dataDropdownFlow.add(new MGenericModel("2","Verifikasi + Otor"));
        }

        //d5
        else if(status.equalsIgnoreCase("Confirm Verifikasi")){
            dataDropdownFlow.add(new MGenericModel("1","Confirm Validasi Engine"));
            dataDropdownFlow.add(new MGenericModel("2","Verifikasi + Otor"));
            dataDropdownFlow.add(new MGenericModel("3","Confirm Verifikasi"));
        }

        //d6
        else if(status.equalsIgnoreCase("Menunggu Putusan Pemutus")){
            dataDropdownFlow.add(new MGenericModel("1","Confirm Validasi Engine"));
            dataDropdownFlow.add(new MGenericModel("2","Verifikasi + Otor"));
            dataDropdownFlow.add(new MGenericModel("3","Confirm Verifikasi"));
            dataDropdownFlow.add(new MGenericModel("4","Menunggu Putusan Pemutus"));
        }


    }

    @Override
    public void success(boolean val) {
        if(val)
            finish();
    }

    @Override
    public void confirm(boolean val) {
    }

    @Override
    public void onSelect(String title, MGenericModel data) {
        if(title.equalsIgnoreCase("lihat flow")){
            binding.btnUbahFlow.setText("Flow : "+data.getDESC());
            initializeMenu(data.getDESC());
        }
    }
}