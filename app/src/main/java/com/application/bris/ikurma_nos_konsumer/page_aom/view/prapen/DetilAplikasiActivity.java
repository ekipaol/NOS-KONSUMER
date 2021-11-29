package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.ReqUid;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqAcctNumber;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqLanjutHotprospek;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.config.menu.Menu;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityDetilAplikasiBinding;
import com.application.bris.ikurma_nos_konsumer.listener.menu.MenuClickListener;
import com.application.bris.ikurma_nos_konsumer.model.menu.ListViewSubmenuHotprospek;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataCIfRekening;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataDetailAplikasi;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataMarketing;
import com.application.bris.ikurma_nos_konsumer.model.prapen.ReqUpdateDataMarketing;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.hotprospek;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_dedupe.DataDedupeActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_pembiayaan.DataPembiayaanActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.canvasing.HasilCanvasingActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_hutang.DataHutangActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_marketing.DataMarketingActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_nasabah.DataNasabahPrapenActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_ideb.DataIdebActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.dokumen_pendapatan.ActivityDokumenPendapatan;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.jaminan.dataJaminanActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.simulasi_angsuran.kalkulatorsimulasiangsuran;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_dsr_dbr.Activity_DSR_DBR_Nasabah;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_fitur.VerifikasiFiturActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_hutang.VerifikasiHutangActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_ideb.VerifikasiIdebActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_jangka_waktu.ActivityJangkaWaktuPembiayaan;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_kesimpulan.KesimpulanVerifikasi;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_pendapatan.ActivityVerifikasiPendapatan;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_rac.VerifikasiRacActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_tempat_kerja.VerifikasiTempatKerjaActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g1_akad_dan_asesoir.data_akad.DataAkadActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g1_akad_dan_asesoir.data_asesoir.AsesoirActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g1_akad_dan_asesoir.field_ojk_bi.ActivityFieldOjkBI;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g1_akad_dan_asesoir.persiapan_akad.PersiapanAkadActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g3_upload_dokumen.ActivityUploadDokumen;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.memo.MemoActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetilAplikasiActivity extends AppCompatActivity implements MenuClickListener, ConfirmListener, GenericListenerOnSelect {

    private List<ListViewSubmenuHotprospek> dataMenu;
    private SubmenuDetilAplikasiAdapter submenuDetilAplikasiAdapter;
    private GridLayoutManager layoutMenu;
    private int coloumMenu = 3;
    public  String idAplikasi="";
    private String status="";
    private String statusId="";
    private String nama="";
    private String plafond="";
    private String jangkaWaktu="";
    private hotprospek data;
    private hotprospek dataHp;
    private DataDetailAplikasi dataDetailAplikasi;
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
        status=getIntent().getStringExtra("status");
        statusId=getIntent().getStringExtra("statusId");

        if(getIntent().hasExtra("idAplikasi")){
            idAplikasi=getIntent().getStringExtra("idAplikasi");
        }
        else{
            idAplikasi="0";
        }

        customToolbar();
        backgroundStatusBar();
        isiDataDropdown();
        defaultView();

        //initialize status
        initializeMenu(status);
        loadDetailAplikasi();
//        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);

        binding.btnUbahFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),"Flow",dataDropdownFlow,DetilAplikasiActivity.this);
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

        binding.tvStatus.setText(dataDetailAplikasi.getStatusAplikasi());
        binding.tvNama.setText(dataDetailAplikasi.getNama());
        binding.tvProduk.setText(dataDetailAplikasi.getTypeProduk());
        binding.tvTenor.setText(dataDetailAplikasi.getJangkaWaktu());
        binding.tvPlafond.setText(AppUtil.parseRupiah(dataDetailAplikasi.getPlafond()));
        binding.tvIdaplikasi.setText(dataDetailAplikasi.getApplicationNo());
        binding.tvTujuanpenggunaan.setText(dataDetailAplikasi.getTujuanPembiayaan());
        binding.tvAkad.setText(dataDetailAplikasi.getAkad());

        AppUtil.convertBase64ToImage(dataDetailAplikasi.getImg(),binding.ivPhoto);
    }

    private void setDataEmpty(){
        //initialize status pertama
        binding.btnUbahFlow.setText("Flow : "+status);

        binding.tvIdaplikasi.setText("-");
        binding.tvTujuanpenggunaan.setText("-");
        binding.tvAkad.setText("-");

    }


    private List<ListViewSubmenuHotprospek> getListMenu(String namaMenu) {
        List<ListViewSubmenuHotprospek> menu = new ArrayList<>();

        if(namaMenu.equalsIgnoreCase(getString(R.string.d05_inisialisasi))){
            Menu.SubmenuD05(this, menu);
            demoVisibility(false);
        }
        else if(namaMenu.equalsIgnoreCase(getString(R.string.d1_data_entry))){
            Menu.SubmenuD1(this, menu);
            demoVisibility(true);
        }
        else if(namaMenu.equalsIgnoreCase(getString(R.string.d3_confirm_validasi_engine))){
            Menu.SubmenuD3(this, menu);
            demoVisibility(true);
        }
        else if(namaMenu.equalsIgnoreCase(getString(R.string.d4_verifikasi_otor))){
            Menu.SubmenuD4(this, menu);
            demoVisibility(true);
        }
        else if(namaMenu.equalsIgnoreCase(getString(R.string.d5_confirm_verifikasi))){
            Menu.SubmenuD5(this, menu);
            demoVisibility(true);
        }
        else if(namaMenu.equalsIgnoreCase(getString(R.string.d6_menunggu_putusan))){
            Menu.SubmenuD6(this, menu);
            demoVisibility(true);
        }
        else if(namaMenu.equalsIgnoreCase(getString(R.string.g1_asesoir_dan_akad))){
            Menu.SubmenuG1(this, menu);
            demoVisibility(true);
        }
        else if(namaMenu.equalsIgnoreCase(getString(R.string.g3_upload_dokumen))){
            Menu.SubmenuG3(this, menu);
            demoVisibility(true);
        }
     
        return menu;
    }

    private void demoVisibility(boolean cardviewVisibility){
        if(cardviewVisibility){
            binding.cvDataDetilAplikasi.setVisibility(View.VISIBLE);
            binding.llInfo.setVisibility(View.GONE);
            loadDetailAplikasi();
        }
        else{
            binding.cvDataDetilAplikasi.setVisibility(View.GONE);
            binding.llInfo.setVisibility(View.VISIBLE);
            setDataEmpty();
        }

    }

    @Override
    public void onMenuClick(String menu) {

        binding.loading.setVisibility(View.VISIBLE);

        //FLOW D1
        if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d1_data_nasabah))){
            Intent it = new Intent(this, DataNasabahPrapenActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d1_data_dedupe))){
            Intent it = new Intent(this, DataDedupeActivity.class);
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
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d1_lanjut))){
            final SweetAlertDialog dialog=new SweetAlertDialog(DetilAplikasiActivity.this,SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("Konfirmasi?");
            dialog.setContentText("Anda Akan Melanjutkan Aplikasi Ke Tahap Selanjutnya?\n");
            dialog.setConfirmText("Ya");
            dialog.setCancelText("Tidak");
            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {

                  sendDataLanjutHotprospek(dialog);


                }
            });
            dialog.show();
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
            Intent it = new Intent(this, dataJaminanActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d3_data_pendapatan))){
            Intent it = new Intent(this, ActivityDokumenPendapatan.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d3_kalkulator))){
            Intent it = new Intent(this, kalkulatorsimulasiangsuran.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d3_canvassing))){
            Intent it = new Intent(this, HasilCanvasingActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }

        //D4
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d4_pendapatan_verin))){
            Intent it = new Intent(this, ActivityVerifikasiPendapatan.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d4_kalkulator_verin))){
            Intent it = new Intent(this, kalkulatorsimulasiangsuran.class);
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
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d4_data_tempat_kerja))){
            Intent it = new Intent(this, VerifikasiTempatKerjaActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d4_jangka_waktu))){
            Intent it = new Intent(this, ActivityJangkaWaktuPembiayaan.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d4_verifikasi))){
            Intent it = new Intent(this, KesimpulanVerifikasi.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d4_kualitas_pembiayaan))){
            Intent it = new Intent(this, Activity_DSR_DBR_Nasabah.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }

        //D6
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d6_kalkulator_pemutus))){
            Intent it = new Intent(this, kalkulatorsimulasiangsuran.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }

        //G1
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_g1_OJK_BI))){
            Intent it = new Intent(this, ActivityFieldOjkBI.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_g1_asesoir))){
            Intent it = new Intent(this, AsesoirActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_g1_akad))){
            Intent it = new Intent(this, DataAkadActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_g1_dokumen_persiapan_akad))){
            Intent it = new Intent(this, PersiapanAkadActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }

        //G3
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_g3_upload_dokumen))){
            Intent it = new Intent(this, ActivityUploadDokumen.class);
            it.putExtra("idAplikasi",idAplikasi);
            startActivity(it);
        }
        //MEMO
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_detil_aplikasi_d4_memo))){
            Intent it = new Intent(this, MemoActivity.class);
            it.putExtra("idAplikasi",idAplikasi);
            it.putExtra("statusId",statusId);
            startActivity(it);
        }
//        binding.loading.progressbarLoading.setVisibility(View.GONE);
    }

    private void isiDataDropdown(){

        //status inisialisasi pasti masuk list karena dia paling awal
//        dataDropdownFlow.add(new MGenericModel("0",getString(R.string.d05_inisialisasi)));
//        dataDropdownFlow.add(new MGenericModel("1",getString(R.string.d1_data_entry)));
//        dataDropdownFlow.add(new MGenericModel("1",getString(R.string.d3_confirm_validasi_engine)));
//        dataDropdownFlow.add(new MGenericModel("2",getString(R.string.d4_verifikasi_otor)));


//        //d3
         if (status.equalsIgnoreCase(getString(R.string.d3_confirm_validasi_engine))){
            dataDropdownFlow.add(new MGenericModel("1",getString(R.string.d1_data_entry)));
        }

        //d4
         if(statusId.equalsIgnoreCase(getString(R.string.id_d4_verifikasi_otor))){
            dataDropdownFlow.add(new MGenericModel("1",getString(R.string.d3_confirm_validasi_engine)));
            dataDropdownFlow.add(new MGenericModel("2",getString(R.string.d4_verifikasi_otor)));
        }

         else if(statusId.equalsIgnoreCase(getString(R.string.id_d4_otor))){
             dataDropdownFlow.add(new MGenericModel("1",getString(R.string.d3_confirm_validasi_engine)));
             dataDropdownFlow.add(new MGenericModel("2",getString(R.string.d4_verifikasi_otor)));
         }

        //d5
        else if(statusId.equalsIgnoreCase(getString(R.string.id_d5_confirm_verifikasi))){
            dataDropdownFlow.add(new MGenericModel("1",getString(R.string.d3_confirm_validasi_engine)));
            dataDropdownFlow.add(new MGenericModel("2",getString(R.string.d4_verifikasi_otor)));
            dataDropdownFlow.add(new MGenericModel("3",getString(R.string.d5_confirm_verifikasi)));
        }

        //d6
        else if(statusId.equalsIgnoreCase(getString(R.string.id_d6_menunggu_putusan))){
            dataDropdownFlow.add(new MGenericModel("1",getString(R.string.d3_confirm_validasi_engine)));
            dataDropdownFlow.add(new MGenericModel("2",getString(R.string.d4_verifikasi_otor)));
            dataDropdownFlow.add(new MGenericModel("3",getString(R.string.d5_confirm_verifikasi)));
            dataDropdownFlow.add(new MGenericModel("4",getString(R.string.d6_menunggu_putusan)));
        }
        //g1
        else if(statusId.equalsIgnoreCase(getString(R.string.id_g1_asesoir_dan_akad))){
            dataDropdownFlow.add(new MGenericModel("1",getString(R.string.d3_confirm_validasi_engine)));
            dataDropdownFlow.add(new MGenericModel("2",getString(R.string.d4_verifikasi_otor)));
            dataDropdownFlow.add(new MGenericModel("3",getString(R.string.d5_confirm_verifikasi)));
            dataDropdownFlow.add(new MGenericModel("4",getString(R.string.d6_menunggu_putusan)));
            dataDropdownFlow.add(new MGenericModel("4",getString(R.string.g1_asesoir_dan_akad)));

            //delete below when prod
             dataDropdownFlow.add(new MGenericModel("4",getString(R.string.g3_upload_dokumen)));
        }
         //g3
         else if(statusId.equalsIgnoreCase(getString(R.string.id_g3_upload_dokumen))){
             dataDropdownFlow.add(new MGenericModel("1",getString(R.string.d3_confirm_validasi_engine)));
             dataDropdownFlow.add(new MGenericModel("2",getString(R.string.d4_verifikasi_otor)));
             dataDropdownFlow.add(new MGenericModel("3",getString(R.string.d5_confirm_verifikasi)));
             dataDropdownFlow.add(new MGenericModel("4",getString(R.string.d6_menunggu_putusan)));
             dataDropdownFlow.add(new MGenericModel("4",getString(R.string.g1_asesoir_dan_akad)));
             dataDropdownFlow.add(new MGenericModel("4",getString(R.string.g3_upload_dokumen)));
         }


    }


    private void loadDetailAplikasi(){
            //  dataUser = getListUser();
            binding.loadingAll.progressbarLoading.setVisibility(View.VISIBLE);
            //pantekan no aplikasi dan aktifitas
            ReqUidIdAplikasi req=new ReqUidIdAplikasi();
            req.setApplicationId(Long.parseLong(idAplikasi));

            Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryDetailAplikasi(req);
            call.enqueue(new Callback<ParseResponse>() {
                @Override
                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                    binding.loadingAll.progressbarLoading.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<DataDetailAplikasi>() {
                            }.getType();
                             dataDetailAplikasi =  gson.fromJson(listDataString, type);
                             setData();
                        }
                        else{
                            AppUtil.notiferror(DetilAplikasiActivity.this, findViewById(android.R.id.content), response.body().getMessage());

                        }
                    }
                }

                @Override
                public void onFailure(Call<ParseResponse> call, Throwable t) {
                    binding.loadingAll.progressbarLoading.setVisibility(View.GONE);
                    AppUtil.notiferror(DetilAplikasiActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                    Log.d("LOG D", t.getMessage());
                }
            });
        }


    public void sendDataLanjutHotprospek(SweetAlertDialog dialog){
        ReqLanjutHotprospek req=new ReqLanjutHotprospek();
        req.setApplicationId(Long.valueOf(idAplikasi));
        req.setUID(String.valueOf(appPreferences.getUid()));

        if(appPreferences.getFidRole()==100){
            req.setMitraFronting(false);
        }
        else{
            req.setMitraFronting(true);
        }

        dialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        dialog.setTitleText("Loading");
        dialog.setContentText("Harap Tunggu");
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().lanjutHotprospek(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingAll.progressbarLoading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){


                            dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            dialog.setTitleText("Berhasil");
                            dialog.setContentText("Aplikasi Berhasil Dilanjutkan");
                            dialog.showCancelButton(false);
                            dialog.setConfirmText("OK");
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    dialog.dismissWithAnimation();
                                    finish();
                                }
                            });
                        }
                        else{
                            AppUtil.notiferror(DetilAplikasiActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            dialog.dismissWithAnimation();
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DetilAplikasiActivity.this, findViewById(android.R.id.content), error.getMessage());
                        dialog.dismissWithAnimation();
                    }
                }
                catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                dialog.dismissWithAnimation();
                AppUtil.notiferror(DetilAplikasiActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }


    private void defaultView(){
        binding.cvDataDetilAplikasi.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDetailAplikasi();
        binding.loading.setVisibility(View.GONE);
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
        if(title.equalsIgnoreCase("flow")){
            binding.btnUbahFlow.setText("Flow : "+data.getDESC());
            status=data.getDESC();
            initializeMenu(data.getDESC());
        }
    }
}