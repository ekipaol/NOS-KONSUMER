package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g1_akad_dan_asesoir.data_akad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqAkadAsesoir;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDataAkadAsesoir;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UploadImage;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityDataAkadBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoAsesoirActivityBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataMmqKendaraan;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataMmqLainnya;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataMmqTanah;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataMmqTempatTinggal;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataMurabahah;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataVerifikasiTempatKerja;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_marketing.DataMarketingActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_nasabah.DataNasabahPrapenActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_nasabah.FragmentDataPribadiPrapen;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_tempat_kerja.VerifikasiTempatKerjaActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class DataAkadActivity extends AppCompatActivity implements GenericListenerOnSelect, View.OnClickListener, ConfirmListener, CameraListener {

    private PrapenAoActivityDataAkadBinding binding;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private DataMmqKendaraan dataMmqKendaraan;
    private DataMmqTanah dataMmqTanah;
    private DataMmqTempatTinggal dataMmqTempatTinggal;
    private DataMmqLainnya dataMmqLainnya;
    private DataMurabahah dataMurabahah;
    private List<MGenericModel> dataDropdownAset=new ArrayList<>();
    private List<MGenericModel> dataDropdownDokumenTanah=new ArrayList<>();
    private List<MGenericModel> dataDropdownDokumenTempatTinggal=new ArrayList<>();
    private List<MGenericModel> dataDropdownJenisKendaraan=new ArrayList<>();
    private List<MGenericModel> dataDropdownDokumenKepemilikanKendaraan=new ArrayList<>();
    private long idAplikasi;
    private String akad;
    private boolean adaFieldBelumDiisi=false;
    private String tipeFileAset="";
    private String valBase64PdfAset="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PrapenAoActivityDataAkadBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        apiClientAdapter=new ApiClientAdapter(this);
        appPreferences=new AppPreferences(this);

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Akad");
        idAplikasi=Long.parseLong(getIntent().getStringExtra("idAplikasi"));

        //pantekan akad
        Toast.makeText(this, "Ada pantekan akad", Toast.LENGTH_SHORT).show();
        akad="mmq";
//        akad=getIntent().getStringExtra("akad");

        allOnTextChanged();
        setData();
        allOnClicks();
        disableEditTexts();
        isiDropdown();
        defaultViewCondition();

        if(akad.equalsIgnoreCase("mmq")){
            loadDataMmq();
        }
        else if(akad.equalsIgnoreCase("murabahah")){
            loadDataMurabahah();
        }
        else{
            Toast.makeText(this, "Akad tidak dikenali", Toast.LENGTH_SHORT).show();
        }

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.DialogBackpress(DataAkadActivity.this);
            }
        });
    }

    private void setData(){
        binding.etAkadPembiayaan.setText(akad);
    }

    private void isiDropdown(){

        //dropdown aset
        dataDropdownAset.add(new MGenericModel("Tanah","Tanah"));
        dataDropdownAset.add(new MGenericModel("Tempat Tinggal","Tempat Tinggal"));
        dataDropdownAset.add(new MGenericModel("Kendaraan","Kendaraan"));
        dataDropdownAset.add(new MGenericModel("Lainnya","Lainnya"));

        //dropdown dokumen tanah
        dataDropdownDokumenTanah.add(new MGenericModel("SHM","SHM"));
        dataDropdownDokumenTanah.add(new MGenericModel("SHMSRS","SHMSRS"));
        dataDropdownDokumenTanah.add(new MGenericModel("Non Sertifikat (Girik)","Non Sertifikat(Girik)"));
        dataDropdownDokumenTanah.add(new MGenericModel("HPL","HPL"));

        //dropdown dokumen tempat tinggal
        dataDropdownDokumenTempatTinggal.add(new MGenericModel("SHM","SHM"));
        dataDropdownDokumenTempatTinggal.add(new MGenericModel("SHMSRS","SHMSRS"));
        dataDropdownDokumenTempatTinggal.add(new MGenericModel("Non Sertifikat (Girik)","Non Sertifikat(Girik)"));
        dataDropdownDokumenTempatTinggal.add(new MGenericModel("HPL","HPL"));

        //dropdown jenis kendaraan
        dataDropdownJenisKendaraan.add(new MGenericModel("Mobil","Mobil"));
        dataDropdownJenisKendaraan.add(new MGenericModel("Motor","Motor"));
        dataDropdownJenisKendaraan.add(new MGenericModel("Kendaraan Usaha","Kendaraan Usaha"));

        //dropdown dokumen kendaraan
        dataDropdownDokumenKepemilikanKendaraan.add(new MGenericModel("BPKB","BPKB"));
        dataDropdownDokumenKepemilikanKendaraan.add(new MGenericModel("STNK","STNK"));

    }


    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public void onBackPressed() {
        CustomDialog.DialogBackpress(DataAkadActivity.this);
    }

    private void allOnClicks(){
        endIconOnClick();
        binding.tfRencanaPenandatangananAkad.setOnClickListener(this);
        binding.etRencanaPenandatangananAkad.setOnClickListener(this);
        binding.tfBulanAngsuranPertama.setOnClickListener(this);
        binding.etBulanAngsuranPertama.setOnClickListener(this);
        binding.tfTanggalAktaNikah.setOnClickListener(this);
        binding.etTanggalAktaNikah.setOnClickListener(this);
        binding.tfTanggalPenilaian.setOnClickListener(this);
        binding.etTanggalPenilaian.setOnClickListener(this);
        binding.tfKepemilikanAset.setOnClickListener(this);
        binding.etKepemilikanAset.setOnClickListener(this);


        binding.tfJenisDokumenTanah.setOnClickListener(this);
        binding.etJenisDokumenTanah.setOnClickListener(this);
        binding.tfJenisDokumenTempatTinggal.setOnClickListener(this);
        binding.etJenisDokumenTempatTinggal.setOnClickListener(this);
        binding.tfJenisKendaraan.setOnClickListener(this);
        binding.etJenisKendaraan.setOnClickListener(this);
        binding.tfDokumenKepemilikanKendaraan.setOnClickListener(this);
        binding.etDokumenKepemilikanKendaraan.setOnClickListener(this);

        binding.btnFotoAset.setOnClickListener(this);

        binding.btnSimpanDataAkad.setOnClickListener(this);

    }

    private void loadDataMmq(){
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        req.setApplicationId(idAplikasi);
        req.setUID(Integer.toString(appPreferences.getUid()));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryAkadMmq(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {

                        Gson gson = new Gson();
                        Type typeKendaraan = new TypeToken<DataMmqKendaraan>() {}.getType();
                        Type typeTanah = new TypeToken<DataMmqTanah>() {}.getType();
                        Type typeTempatTinggal = new TypeToken<DataMmqTempatTinggal>() {}.getType();
                        Type typeLainnya = new TypeToken<DataMmqLainnya>() {}.getType();

                        try{
                            String listDataStringKendaraan = response.body().getData().get("MMQAsetKendaraan").toString();
                            String listDataStringTanah = response.body().getData().get("MMQAsetTanah").toString();
                            String listDataStringTempatTinggal= response.body().getData().get("MMQAsetTempatTinggal").toString();
                            String listDataStringLainnya = response.body().getData().get("MMQAsetLainnya").toString();
                            dataMmqKendaraan =  gson.fromJson(listDataStringKendaraan, typeKendaraan);
                            dataMmqTanah =  gson.fromJson(listDataStringTanah, typeTanah);
                            dataMmqTempatTinggal =  gson.fromJson(listDataStringTempatTinggal, typeTempatTinggal);
                            dataMmqLainnya =  gson.fromJson(listDataStringLainnya, typeLainnya);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }


                        if(dataMmqKendaraan!=null){
                            setDataMmqKendaraan();
                        }
                        if(dataMmqTanah!=null){
                            setDataMmqTanah();
                        }
                        if(dataMmqTempatTinggal!=null){
                            setDataMmqTempatTinggal();
                        }
                        if(dataMmqLainnya!=null){
                            setDataMmqLainnya();
                        }

                    }
//                    else if (response.body().getStatus().equalsIgnoreCase("01")) {
//                        AppUtil.notiferror(DataAkadActivity.this, findViewById(android.R.id.content), "Data Belum Pernah Disimpan Sebellumnya, Silahkan Diisi");
//                    }
                    else{
                        AppUtil.notiferror(DataAkadActivity.this, findViewById(android.R.id.content), response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataAkadActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    private void loadDataMurabahah(){
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        req.setApplicationId(idAplikasi);
        req.setUID(Integer.toString(appPreferences.getUid()));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryAkadMurabahahIjarah(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {

                        Gson gson = new Gson();
                        Type type = new TypeToken<DataMurabahah>() {}.getType();
                        try{
                            String listDataString = response.body().getData().get("MRBHIJRHAset").toString();
                            dataMurabahah =  gson.fromJson(listDataString, type);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }


                        if(dataMurabahah!=null){
                            setDataMurabahah();
                        }

                    }
//                    else if (response.body().getStatus().equalsIgnoreCase("01")) {
//                        AppUtil.notiferror(DataAkadActivity.this, findViewById(android.R.id.content), "Data Belum Pernah Disimpan Sebellumnya, Silahkan Diisi");
//                    }
                    else{
                        AppUtil.notiferror(DataAkadActivity.this, findViewById(android.R.id.content), response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataAkadActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    private void sendDataMurabahah(){
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqAkadAsesoir req=new ReqAkadAsesoir();
        ReqDataAkadAsesoir reqData=new ReqDataAkadAsesoir();
        DataMurabahah data=new DataMurabahah();


        req.setApplicationId(idAplikasi);
        req.setUID(Integer.toString(appPreferences.getUid()));

        //data
        data.setRencanaTandaTanganAkad(AppUtil.parseTanggalGeneral(binding.etRencanaPenandatangananAkad.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
        data.setBulanAngsuranPertama(AppUtil.parseTanggalGeneral("01-"+binding.etBulanAngsuranPertama.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
        data.setTanggalAktaNikah(AppUtil.parseTanggalGeneral(binding.etTanggalAktaNikah.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
        data.setNoAktaNikah(binding.etNomorAktaNikah.getText().toString());
        data.setNamaBarang(binding.etNamaDanJenisBarangAkad.getText().toString());
        data.setJumlahSatuan(Integer.parseInt(binding.etJumlahSatuan.getText().toString()));
        data.setLokasi(binding.etLokasi.getText().toString());
        data.setPemasok(binding.etPemasok.getText().toString());
        data.setHargaBarang(Double.parseDouble(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etHargaPerolehanBarang.getText().toString())));

        //setdata
        reqData.setMurabahah(data);
        req.setData(reqData);


        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updateAkadMurabahahIjarah(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        CustomDialog.DialogSuccess(DataAkadActivity.this, "Success!", "Update Data Akad sukses", DataAkadActivity.this);
                    }
                    else{
                        AppUtil.notiferror(DataAkadActivity.this, findViewById(android.R.id.content), response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataAkadActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    private void sendDataMmq(){
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqAkadAsesoir req=new ReqAkadAsesoir();
        ReqDataAkadAsesoir reqData=new ReqDataAkadAsesoir();
        UploadImage reqAset=new UploadImage();
        DataMmqTempatTinggal dataMmqTempatTinggal=new DataMmqTempatTinggal();
        DataMmqTanah dataMmqTanah=new DataMmqTanah();
        DataMmqKendaraan dataMmqKendaraan=new DataMmqKendaraan();
        DataMmqLainnya dataMmqLainnya=new DataMmqLainnya();


        req.setApplicationId(idAplikasi);
        req.setUID(Integer.toString(appPreferences.getUid()));


        //setdata
        if(binding.etKepemilikanAset.getText().toString().equalsIgnoreCase("kendaraan")){
            dataMmqKendaraan.setRencanaTandaTanganAkad(AppUtil.parseTanggalGeneral(binding.etRencanaPenandatangananAkad.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
            dataMmqKendaraan.setBulanAngsuranPertama(AppUtil.parseTanggalGeneral("01-"+binding.etBulanAngsuranPertama.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
            dataMmqKendaraan.setTanggalAktaNikah(AppUtil.parseTanggalGeneral(binding.etTanggalAktaNikah.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
            dataMmqKendaraan.setNoAktaNikah(binding.etNomorAktaNikah.getText().toString());
            dataMmqKendaraan.setTanggalPenilaian(AppUtil.parseTanggalGeneral(binding.etTanggalPenilaian.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
            dataMmqKendaraan.setNamaPenilai(binding.etNamaPenilai.getText().toString());
            dataMmqKendaraan.setKotaPenandatangananLaporan(binding.etKotaPenandatangananLaporan.getText().toString());
            dataMmqKendaraan.setKepemilikanAset(binding.etKepemilikanAset.getText().toString());

            dataMmqKendaraan.setJenisKendaraan(binding.etJenisKendaraan.getText().toString());
            dataMmqKendaraan.setDokumenKepemilikan(binding.etDokumenKepemilikanKendaraan.getText().toString());
            dataMmqKendaraan.setNomorDokumen(binding.etNomorDokumenAsetKendaraan.getText().toString());
            dataMmqKendaraan.setNomorPlat(binding.etNomorPlat.getText().toString());
            dataMmqKendaraan.setAlamat(binding.etAlamatKendaraan.getText().toString());
            dataMmqKendaraan.setMerek(binding.etMerek.getText().toString());
            dataMmqKendaraan.setTipe(binding.etTipe.getText().toString());
            dataMmqKendaraan.setTahunPembuatan(Integer.parseInt(binding.etTahunPembuatan.getText().toString()));
            dataMmqKendaraan.setKondisiAset(binding.etKondisiKendaraan.getText().toString());
            dataMmqKendaraan.setSumberData(binding.etSumberDataKendaraan.getText().toString());
            dataMmqKendaraan.setHargaKendaraan(Double.parseDouble(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etHargaKendaraan.getText().toString())));

            reqData.setMmqKendaraan(dataMmqKendaraan);
        }
        else if(binding.etKepemilikanAset.getText().toString().equalsIgnoreCase("tanah")){
            dataMmqTanah.setRencanaTandaTanganAkad(AppUtil.parseTanggalGeneral(binding.etRencanaPenandatangananAkad.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
            dataMmqTanah.setBulanAngsuranPertama(AppUtil.parseTanggalGeneral("01-"+binding.etBulanAngsuranPertama.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
            dataMmqTanah.setTanggalAktaNikah(AppUtil.parseTanggalGeneral(binding.etTanggalAktaNikah.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
            dataMmqTanah.setNoAktaNikah(binding.etNomorAktaNikah.getText().toString());
            dataMmqTanah.setTanggalPenilaian(AppUtil.parseTanggalGeneral(binding.etTanggalPenilaian.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
            dataMmqTanah.setNamaPenilai(binding.etNamaPenilai.getText().toString());
            dataMmqTanah.setKotaPenandatangananLaporan(binding.etKotaPenandatangananLaporan.getText().toString());
            dataMmqTanah.setKepemilikanAset(binding.etKepemilikanAset.getText().toString());
            dataMmqTanah.setJenisDokumen(binding.etJenisDokumenTanah.getText().toString());
            dataMmqTanah.setNomorDokumen(binding.etNomorDokumenAsetTanah.getText().toString());
            dataMmqTanah.setAlamat(binding.etAlamatTanah.getText().toString());
            dataMmqTanah.setLuasAset(Double.parseDouble(binding.etLuasTanah.getText().toString()));
            dataMmqTanah.setHargaAset(Double.parseDouble(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etHargaTanah.getText().toString())));
            dataMmqTanah.setTotalNilaiTanah(Double.parseDouble(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTotalNilaiTanah.getText().toString())));
            dataMmqTanah.setKondisiAset(binding.etKondisiTanah.getText().toString());
            dataMmqTanah.setSumberData(binding.etSumberDataTanah.getText().toString());
            dataMmqTanah.setTotalNilaiAset(Double.parseDouble(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etHargaTanah.getText().toString())));

            reqData.setMmqTanah(dataMmqTanah);
        }
        else if(binding.etKepemilikanAset.getText().toString().equalsIgnoreCase("tempat tinggal")){
            dataMmqTempatTinggal.setRencanaTandaTanganAkad(AppUtil.parseTanggalGeneral(binding.etRencanaPenandatangananAkad.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
            dataMmqTempatTinggal.setBulanAngsuranPertama(AppUtil.parseTanggalGeneral("01-"+binding.etBulanAngsuranPertama.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
            dataMmqTempatTinggal.setTanggalAktaNikah(AppUtil.parseTanggalGeneral(binding.etTanggalAktaNikah.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
            dataMmqTempatTinggal.setNoAktaNikah(binding.etNomorAktaNikah.getText().toString());
            dataMmqTempatTinggal.setTanggalPenilaian(AppUtil.parseTanggalGeneral(binding.etTanggalPenilaian.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
            dataMmqTempatTinggal.setNamaPenilai(binding.etNamaPenilai.getText().toString());
            dataMmqTempatTinggal.setKotaPenandatangananLaporan(binding.etKotaPenandatangananLaporan.getText().toString());
            dataMmqTempatTinggal.setKepemilikanAset(binding.etKepemilikanAset.getText().toString());

            dataMmqTempatTinggal.setJenisDokumen(binding.etJenisDokumenTempatTinggal.getText().toString());
            dataMmqTempatTinggal.setNomorDokumen(binding.etNomorDokumenAsetTempatTinggal.getText().toString());
            dataMmqTempatTinggal.setNomorIMB(binding.etNomorImb.getText().toString());
            dataMmqTempatTinggal.setAlamat(binding.etAlamatTempatTinggal.getText().toString());
            dataMmqTempatTinggal.setLuasAsetTanah(Double.parseDouble(binding.etLuasTanahTempatTinggal.getText().toString()));
            dataMmqTempatTinggal.setHargaAsetTanah(Double.parseDouble(binding.etHargaTanahTempatTinggal.getText().toString()));
            dataMmqTempatTinggal.setTotalNilaiTanah(Double.parseDouble(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etLuasTanahTempatTinggal.getText().toString())));
            dataMmqTempatTinggal.setLuasAsetBangunan(Double.parseDouble(binding.etLuasBangunan.getText().toString()));
            dataMmqTempatTinggal.setHargaAsetBangunan(Double.parseDouble(binding.etHargaBangunan.getText().toString()));
            dataMmqTempatTinggal.setTotalNilaiBangunan(Double.parseDouble(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTotalNilaiBangunan.getText().toString())));
            dataMmqTempatTinggal.setKondisiAset(binding.etKondisiTempatTinggal.getText().toString());
            dataMmqTempatTinggal.setSumberData(binding.etSumberDataTempatTinggal.getText().toString());
            dataMmqTempatTinggal.setTotalNilaiAset(Double.parseDouble(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTotalNilaiAsetTempatTinggal.getText().toString())));

            reqData.setMmqTempatTinggal(dataMmqTempatTinggal);

        }
        else if(binding.etKepemilikanAset.getText().toString().equalsIgnoreCase("lainnya")){
            dataMmqLainnya.setRencanaTandaTanganAkad(AppUtil.parseTanggalGeneral(binding.etRencanaPenandatangananAkad.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
            dataMmqLainnya.setBulanAngsuranPertama(AppUtil.parseTanggalGeneral("01-"+binding.etBulanAngsuranPertama.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
            dataMmqLainnya.setTanggalAktaNikah(AppUtil.parseTanggalGeneral(binding.etTanggalAktaNikah.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
            dataMmqLainnya.setNoAktaNikah(binding.etNomorAktaNikah.getText().toString());
            dataMmqLainnya.setTanggalPenilaian(AppUtil.parseTanggalGeneral(binding.etTanggalPenilaian.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
            dataMmqLainnya.setNamaPenilai(binding.etNamaPenilai.getText().toString());
            dataMmqLainnya.setKotaPenandatangananLaporan(binding.etKotaPenandatangananLaporan.getText().toString());
            dataMmqLainnya.setKepemilikanAset(binding.etKepemilikanAset.getText().toString());

            dataMmqLainnya.setJenisAset(binding.etJenisAset.getText().toString());
            dataMmqLainnya.setDokumenKepemilikan(binding.etDokumenKepemilikanLainnya.getText().toString());
            dataMmqLainnya.setAlamat(binding.etAlamatLainnya.getText().toString());
            dataMmqLainnya.setKondisi(binding.etKondisiLainnya.getText().toString());
            dataMmqLainnya.setSumberData(binding.etSumberDataLainnya.getText().toString());
            dataMmqLainnya.setNilaiAset(Double.parseDouble(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etNilaiAsetLainnya.getText().toString())));

            reqData.setMmqLainnya(dataMmqLainnya);
        }

        //data foto

        if(tipeFileAset.equalsIgnoreCase("pdf")){
            reqAset.setImg(valBase64PdfAset);
            reqAset.setFile_Name(String.valueOf(idAplikasi)+"_aset.pdf");
        }
        else{
            RoundedDrawable drawableAset = (RoundedDrawable) binding.ivFotoAset.getDrawable();
            Bitmap bitmapAset = drawableAset.getSourceBitmap();
            reqAset.setImg(AppUtil.encodeImageTobase64(bitmapAset));
            reqAset.setFile_Name(String.valueOf(idAplikasi)+"_aset.png");
        }



        reqData.setDokumenAset(reqAset);
        req.setData(reqData);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updateAkadMmq(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        CustomDialog.DialogSuccess(DataAkadActivity.this, "Success!", "Update Data Akad sukses", DataAkadActivity.this);
                    }
                    else{
                        AppUtil.notiferror(DataAkadActivity.this, findViewById(android.R.id.content), response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataAkadActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }





    private void setDataMurabahah(){
        binding.etRencanaPenandatangananAkad.setText(AppUtil.parseTanggalGeneral(dataMurabahah.getRencanaTandaTanganAkad(),"yyyy-MM-dd","dd-MM-yyyy"));
        binding.etBulanAngsuranPertama.setText(AppUtil.parseTanggalGeneral(dataMurabahah.getBulanAngsuranPertama(),"yyyy-MM-dd","MM-yyyy"));
        binding.etNomorAktaNikah.setText(dataMurabahah.getNoAktaNikah());
        binding.etTanggalAktaNikah.setText(AppUtil.parseTanggalGeneral(dataMurabahah.getTanggalAktaNikah(),"yyyy-MM-dd","dd-MM-yyyy"));

        binding.etNamaDanJenisBarangAkad.setText(dataMurabahah.getNamaBarang());
        binding.etJumlahSatuan.setText(Integer.toString(dataMurabahah.getJumlahSatuan()));
        binding.etNamaDanJenisBarangAkad.setText(dataMurabahah.getNamaBarang());
        binding.etLokasi.setText(dataMurabahah.getLokasi());
        binding.etPemasok.setText(dataMurabahah.getPemasok());
        binding.etHargaPerolehanBarang.setText(Double.toString(dataMurabahah.getHargaBarang()));
    }

    private void setDataMmqKendaraan(){
        binding.etRencanaPenandatangananAkad.setText(AppUtil.parseTanggalGeneral(dataMmqKendaraan.getRencanaTandaTanganAkad(),"yyyy-MM-dd","dd-MM-yyyy"));
        binding.etBulanAngsuranPertama.setText(AppUtil.parseTanggalGeneral(dataMmqKendaraan.getBulanAngsuranPertama(),"yyyy-MM-dd","MM-yyyy"));
        binding.etNomorAktaNikah.setText(dataMmqKendaraan.getNoAktaNikah());
        binding.etTanggalAktaNikah.setText(AppUtil.parseTanggalGeneral(dataMmqKendaraan.getTanggalAktaNikah(),"yyyy-MM-dd","dd-MM-yyyy"));
        binding.etTanggalPenilaian.setText(AppUtil.parseTanggalGeneral(dataMmqKendaraan.getTanggalPenilaian(),"yyyy-MM-dd","dd-MM-yyyy"));
        binding.etNamaPenilai.setText(dataMmqKendaraan.getNamaPenilai());
        binding.etKotaPenandatangananLaporan.setText(dataMmqKendaraan.getKotaPenandatangananLaporan());
        binding.etKepemilikanAset.setText(dataMmqKendaraan.getKepemilikanAset());
        checkKepemilikanAset();

        binding.etJenisKendaraan.setText(dataMmqKendaraan.getJenisKendaraan());
        binding.etDokumenKepemilikanKendaraan.setText(dataMmqKendaraan.getDokumenKepemilikan());
        binding.etNomorDokumenAsetKendaraan.setText(dataMmqKendaraan.getNomorDokumen());
        binding.etNomorPlat.setText(dataMmqKendaraan.getNomorPlat());
        binding.etAlamatKendaraan.setText(dataMmqKendaraan.getAlamat());
        binding.etMerek.setText(dataMmqKendaraan.getMerek());
        binding.etTipe.setText(dataMmqKendaraan.getTipe());
        binding.etTahunPembuatan.setText(dataMmqKendaraan.getTahunPembuatan());
        binding.etKondisiKendaraan.setText(dataMmqKendaraan.getKondisiAset());
        binding.etSumberDataKendaraan.setText(dataMmqKendaraan.getSumberData());
        binding.etHargaKendaraan.setText(Double.toString(dataMmqKendaraan.getHargaKendaraan()));
    }

    private void setDataMmqTempatTinggal(){
        binding.etRencanaPenandatangananAkad.setText(AppUtil.parseTanggalGeneral(dataMmqTempatTinggal.getRencanaTandaTanganAkad(),"yyyy-MM-dd","dd-MM-yyyy"));
        binding.etBulanAngsuranPertama.setText(AppUtil.parseTanggalGeneral(dataMmqTempatTinggal.getBulanAngsuranPertama(),"yyyy-MM-dd","MM-yyyy"));
        binding.etNomorAktaNikah.setText(dataMmqTempatTinggal.getNoAktaNikah());
        binding.etTanggalAktaNikah.setText(AppUtil.parseTanggalGeneral(dataMmqTempatTinggal.getTanggalAktaNikah(),"yyyy-MM-dd","dd-MM-yyyy"));
        binding.etTanggalPenilaian.setText(AppUtil.parseTanggalGeneral(dataMmqTempatTinggal.getTanggalPenilaian(),"yyyy-MM-dd","dd-MM-yyyy"));
        binding.etNamaPenilai.setText(dataMmqTempatTinggal.getNamaPenilai());
        binding.etKotaPenandatangananLaporan.setText(dataMmqTempatTinggal.getKotaPenandatangananLaporan());
        binding.etKepemilikanAset.setText(dataMmqTempatTinggal.getKepemilikanAset());
        checkKepemilikanAset();

        binding.etJenisDokumenTempatTinggal.setText(dataMmqTempatTinggal.getJenisDokumen());
        binding.etNomorDokumenAsetTempatTinggal.setText(dataMmqTempatTinggal.getNomorDokumen());
        binding.etNomorImb.setText(dataMmqTempatTinggal.getNomorIMB());
        binding.etAlamatTempatTinggal.setText(dataMmqTempatTinggal.getAlamat());
        binding.etLuasTanahTempatTinggal.setText(Double.toString(dataMmqTempatTinggal.getLuasAsetTanah()));
        binding.etHargaTanahTempatTinggal.setText(Double.toString(dataMmqTempatTinggal.getHargaAsetTanah()));
        binding.etTotalNilaiTanahTempatTinggal.setText(Double.toString(dataMmqTempatTinggal.getTotalNilaiTanah()));
        binding.etLuasBangunan.setText(Double.toString(dataMmqTempatTinggal.getLuasAsetBangunan()));
        binding.etHargaBangunan.setText(Double.toString(dataMmqTempatTinggal.getHargaAsetBangunan()));
        binding.etTotalNilaiBangunan.setText(Double.toString(dataMmqTempatTinggal.getTotalNilaiBangunan()));
        binding.etKondisiTempatTinggal.setText(dataMmqTempatTinggal.getKondisiAset());
        binding.etSumberDataTempatTinggal.setText(dataMmqTempatTinggal.getSumberData());
        binding.etTotalNilaiAsetTempatTinggal.setText(Double.toString(dataMmqTempatTinggal.getTotalNilaiAset()));
    }

    private void setDataMmqTanah(){
        binding.etRencanaPenandatangananAkad.setText(AppUtil.parseTanggalGeneral(dataMmqTanah.getRencanaTandaTanganAkad(),"yyyy-MM-dd","dd-MM-yyyy"));
        binding.etBulanAngsuranPertama.setText(AppUtil.parseTanggalGeneral(dataMmqTanah.getBulanAngsuranPertama(),"yyyy-MM-dd","MM-yyyy"));
        binding.etNomorAktaNikah.setText(dataMmqTanah.getNoAktaNikah());
        binding.etTanggalAktaNikah.setText(AppUtil.parseTanggalGeneral(dataMmqTanah.getTanggalAktaNikah(),"yyyy-MM-dd","dd-MM-yyyy"));
        binding.etTanggalPenilaian.setText(AppUtil.parseTanggalGeneral(dataMmqTanah.getTanggalPenilaian(),"yyyy-MM-dd","dd-MM-yyyy"));
        binding.etNamaPenilai.setText(dataMmqTanah.getNamaPenilai());
        binding.etKotaPenandatangananLaporan.setText(dataMmqTanah.getKotaPenandatangananLaporan());
        binding.etKepemilikanAset.setText(dataMmqTanah.getKepemilikanAset());
        checkKepemilikanAset();

        binding.etJenisDokumenTanah.setText(dataMmqTanah.getJenisDokumen());
        binding.etNomorDokumenAsetTanah.setText(dataMmqTanah.getNomorDokumen());
        binding.etAlamatTanah.setText(dataMmqTanah.getAlamat());
        binding.etLuasTanah.setText(Double.toString(dataMmqTanah.getLuasAset()));
        binding.etHargaTanah.setText(Double.toString(dataMmqTanah.getHargaAset()));
        binding.etTotalNilaiTanah.setText(Double.toString(dataMmqTanah.getTotalNilaiTanah()));
        binding.etKondisiTanah.setText(dataMmqTanah.getKondisiAset());
        binding.etSumberDataTanah.setText(dataMmqTanah.getSumberData());
        binding.etTotalNilaiAsetTanah.setText(Double.toString(dataMmqTanah.getTotalNilaiAset()));
    }

    private void setDataMmqLainnya(){
        binding.etRencanaPenandatangananAkad.setText(AppUtil.parseTanggalGeneral(dataMmqLainnya.getRencanaTandaTanganAkad(),"yyyy-MM-dd","dd-MM-yyyy"));
        binding.etBulanAngsuranPertama.setText(AppUtil.parseTanggalGeneral(dataMmqLainnya.getBulanAngsuranPertama(),"yyyy-MM-dd","MM-yyyy"));
        binding.etNomorAktaNikah.setText(dataMmqLainnya.getNoAktaNikah());
        binding.etTanggalAktaNikah.setText(AppUtil.parseTanggalGeneral(dataMmqLainnya.getTanggalAktaNikah(),"yyyy-MM-dd","dd-MM-yyyy"));
        binding.etTanggalPenilaian.setText(AppUtil.parseTanggalGeneral(dataMmqLainnya.getTanggalPenilaian(),"yyyy-MM-dd","dd-MM-yyyy"));
        binding.etNamaPenilai.setText(dataMmqLainnya.getNamaPenilai());
        binding.etKotaPenandatangananLaporan.setText(dataMmqLainnya.getKotaPenandatangananLaporan());
        binding.etKepemilikanAset.setText(dataMmqLainnya.getKepemilikanAset());
        checkKepemilikanAset();

        binding.etJenisAset.setText(dataMmqLainnya.getJenisAset());
        binding.etDokumenKepemilikanLainnya.setText(dataMmqLainnya.getDokumenKepemilikan());
        binding.etAlamatLainnya.setText(dataMmqLainnya.getAlamat());
        binding.etKondisiLainnya.setText(dataMmqLainnya.getKondisi());
        binding.etSumberDataLainnya.setText(dataMmqTanah.getSumberData());
        binding.etNilaiAsetLainnya.setText(Double.toString(dataMmqLainnya.getNilaiAset()));
    }

    private void checkKepemilikanAset(){
        if(binding.etKepemilikanAset.getText().toString().equalsIgnoreCase("tanah")){
            binding.llAsetTanah.setVisibility(View.VISIBLE);
            binding.llAsetKendaraan.setVisibility(View.GONE);
            binding.llAsetTempatTinggal.setVisibility(View.GONE);
            binding.llAsetLainnya.setVisibility(View.GONE);
        }
       else if(binding.etKepemilikanAset.getText().toString().equalsIgnoreCase("tempat tinggal")){
            binding.llAsetTanah.setVisibility(View.GONE);
            binding.llAsetKendaraan.setVisibility(View.GONE);
            binding.llAsetTempatTinggal.setVisibility(View.VISIBLE);
            binding.llAsetLainnya.setVisibility(View.GONE);
        }
        else if(binding.etKepemilikanAset.getText().toString().equalsIgnoreCase("kendaraan")){
            binding.llAsetTanah.setVisibility(View.GONE);
            binding.llAsetKendaraan.setVisibility(View.VISIBLE);
            binding.llAsetTempatTinggal.setVisibility(View.GONE);
            binding.llAsetLainnya.setVisibility(View.GONE);
        }
        else if(binding.etKepemilikanAset.getText().toString().equalsIgnoreCase("lainnya")){
            binding.llAsetTanah.setVisibility(View.GONE);
            binding.llAsetKendaraan.setVisibility(View.GONE);
            binding.llAsetTempatTinggal.setVisibility(View.GONE);
            binding.llAsetLainnya.setVisibility(View.VISIBLE);
        }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //SUMBER APLIKASI
            case R.id.tf_rencana_penandatanganan_akad:
            case R.id.et_rencana_penandatanganan_akad:
                AppUtil.genericCalendarDialog(DataAkadActivity.this,binding.etRencanaPenandatangananAkad);
                break;
            case R.id.et_bulan_angsuran_pertama:
            case R.id.tf_bulan_angsuran_pertama:
                AppUtil.customCalendarDialog(DataAkadActivity.this,binding.etBulanAngsuranPertama,"MM-yyyy");
                break;
            case R.id.tf_tanggal_akta_nikah:
            case R.id.et_tanggal_akta_nikah:
                AppUtil.genericCalendarDialog(DataAkadActivity.this,binding.etTanggalAktaNikah);
                break;
            case R.id.tf_tanggal_penilaian:
            case R.id.et_tanggal_penilaian:
                AppUtil.genericCalendarDialog(DataAkadActivity.this,binding.etTanggalPenilaian);
                break;
            case R.id.tf_kepemilikan_aset:
            case R.id.et_kepemilikan_aset:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfKepemilikanAset.getLabelText(),dataDropdownAset,DataAkadActivity.this);
                break;
            case R.id.tf_jenis_dokumen_tanah:
            case R.id.et_jenis_dokumen_tanah:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJenisDokumenTanah.getLabelText(),dataDropdownDokumenTanah,DataAkadActivity.this);
                break;
            case R.id.tf_jenis_dokumen_tempat_tinggal:
            case R.id.et_jenis_dokumen_tempat_tinggal:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJenisDokumenTempatTinggal.getLabelText(),dataDropdownDokumenTempatTinggal,DataAkadActivity.this);
                break;
            case R.id.tf_jenis_kendaraan:
            case R.id.et_jenis_kendaraan:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJenisKendaraan.getLabelText(),dataDropdownJenisKendaraan,DataAkadActivity.this);
                break;
            case R.id.tf_dokumen_kepemilikan_kendaraan:
            case R.id.et_dokumen_kepemilikan_kendaraan:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfDokumenKepemilikanKendaraan.getLabelText(),dataDropdownDokumenKepemilikanKendaraan,DataAkadActivity.this);
                break;
                case R.id.btn_foto_aset:
                    BSUploadFile.displayWithTitle(getSupportFragmentManager(), DataAkadActivity.this,"Upload Aset");
                    break;
            case R.id.btn_simpan_data_akad:
                validateField(binding.etRencanaPenandatangananAkad,binding.tfRencanaPenandatangananAkad);
                validateField(binding.etBulanAngsuranPertama,binding.tfBulanAngsuranPertama);
                validateField(binding.etNomorAktaNikah,binding.tfNomorAktaNikah);
                validateField(binding.etTanggalAktaNikah,binding.tfTanggalAktaNikah);

                //validasi murabahah
                if(akad.equalsIgnoreCase("murabahah")){
                    validateField(binding.etNamaDanJenisBarangAkad,binding.tfNamaDanJenisBarangAkad);
                    validateField(binding.etJumlahSatuan,binding.tfJumlahSatuan);
                    validateField(binding.etPemasok,binding.tfPemasok);
                    validateField(binding.etLokasi,binding.tfLokasi);
                    validateField(binding.etHargaPerolehanBarang,binding.tfHargaPerolehanBarang);

                    if(!adaFieldBelumDiisi){
                        sendDataMurabahah();
                    }
                }
                else{
                    //validasi mmq
                    validateField(binding.etTanggalPenilaian,binding.tfTanggalPenilaian);
                    validateField(binding.etNamaPenilai,binding.tfNamaPenilai);
                    validateField(binding.etKotaPenandatangananLaporan,binding.tfKotaPenandatangananLaporan);

                    if(binding.etKepemilikanAset.getText().toString().isEmpty()){
                        validateField(binding.etKepemilikanAset,binding.tfKepemilikanAset);
                    }
                    else{
                        if(binding.etKepemilikanAset.getText().toString().equalsIgnoreCase("kendaraan")){
                            validateField(binding.etJenisKendaraan,binding.tfJenisKendaraan);
                            validateField(binding.etDokumenKepemilikanKendaraan,binding.tfDokumenKepemilikanKendaraan);
                            validateField(binding.etNomorDokumenAsetKendaraan,binding.tfNomorDokumenAsetKendaraan);
                            validateField(binding.etAlamatKendaraan,binding.tfAlamatKendaraan);
                            validateField(binding.etMerek,binding.tfMerek);
                            validateField(binding.etTipe,binding.tfTipe);
                            validateField(binding.etTahunPembuatan,binding.tfTahunPembuatan);
                            validateField(binding.etKondisiKendaraan,binding.tfKondisiKendaraan);
                            validateField(binding.etSumberDataKendaraan,binding.tfSumberDataKendaraan);
                            validateField(binding.etHargaKendaraan,binding.tfHargaKendaraan);

                        }
                        else if(binding.etKepemilikanAset.getText().toString().equalsIgnoreCase("tanah")){
                            validateField(binding.etJenisDokumenTanah,binding.tfJenisDokumenTanah);
                            validateField(binding.etNomorDokumenAsetTanah,binding.tfNomorDokumenAsetTanah);
                            validateField(binding.etAlamatTanah,binding.tfAlamatTanah);
                            validateField(binding.etLuasTanah,binding.tfLuasTanah);
                            validateField(binding.etHargaTanah,binding.tfHargaTanah);
                            validateField(binding.etKondisiTanah,binding.tfKondisiTanah);
                            validateField(binding.etSumberDataTanah,binding.tfSumberDataTanah);
                        }
                        else if(binding.etKepemilikanAset.getText().toString().equalsIgnoreCase("tempat tinggal")){
                            validateField(binding.etJenisDokumenTempatTinggal,binding.tfJenisDokumenTempatTinggal);
                            validateField(binding.etNomorDokumenAsetTempatTinggal,binding.tfNomorDokumenAsetTempatTinggal);
                            validateField(binding.etAlamatTempatTinggal,binding.tfAlamatTempatTinggal);
                            validateField(binding.etLuasTanahTempatTinggal,binding.tfLuasTanahTempatTinggal);
                            validateField(binding.etHargaTanahTempatTinggal,binding.tfHargaTanahTempatTinggal);
                            validateField(binding.etLuasBangunan,binding.tfLuasBangunan);
                            validateField(binding.etHargaBangunan,binding.tfHargaBangunan);
                            validateField(binding.etKondisiTempatTinggal,binding.tfKondisiTempatTinggal);
                            validateField(binding.etSumberDataTempatTinggal,binding.tfSumberDataTempatTinggal);
                        }
                        else if(binding.etKepemilikanAset.getText().toString().equalsIgnoreCase("lainnya")){
                            validateField(binding.etJenisAset,binding.tfJenisAset);
                            validateField(binding.etAlamatLainnya,binding.tfAlamatLainnya);
                            validateField(binding.etKondisiLainnya,binding.tfKondisiLainnya);
                            validateField(binding.etSumberDataLainnya,binding.tfSumberDataLainnya);
                            validateField(binding.etNilaiAsetLainnya,binding.tfNilaiAsetLainnya);
                        }
                    }
                }
                if(!adaFieldBelumDiisi){
                    sendDataMmq();

                }
                break;

            default:break;
        }
    }
    private void endIconOnClick(){
        binding.tfRencanaPenandatangananAkad.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.genericCalendarDialog(DataAkadActivity.this,binding.etRencanaPenandatangananAkad);
            }
        });

        binding.tfBulanAngsuranPertama.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.customCalendarDialog(DataAkadActivity.this,binding.etBulanAngsuranPertama,"MM-yyyy");
            }
        });

        binding.tfTanggalAktaNikah.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.genericCalendarDialog(DataAkadActivity.this,binding.etTanggalAktaNikah);
            }
        });

        binding.tfTanggalPenilaian.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.genericCalendarDialog(DataAkadActivity.this,binding.etTanggalPenilaian);
            }
        });

        binding.tfKepemilikanAset.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfKepemilikanAset.getLabelText(),dataDropdownAset,DataAkadActivity.this);
            }
        });

        binding.tfJenisDokumenTanah.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJenisDokumenTanah.getLabelText(),dataDropdownDokumenTanah,DataAkadActivity.this);
            }
        });

        binding.tfJenisDokumenTempatTinggal.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJenisDokumenTempatTinggal.getLabelText(),dataDropdownDokumenTempatTinggal,DataAkadActivity.this);
            }
        });

        binding.tfKepemilikanAset.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfKepemilikanAset.getLabelText(),dataDropdownAset,DataAkadActivity.this);
            }
        });

        binding.tfJenisKendaraan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJenisKendaraan.getLabelText(),dataDropdownJenisKendaraan,DataAkadActivity.this);
            }
        });

        binding.tfDokumenKepemilikanKendaraan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfDokumenKepemilikanKendaraan.getLabelText(),dataDropdownDokumenKepemilikanKendaraan,DataAkadActivity.this);
            }
        });

    }

    private void disableEditTexts(){
        binding.etAkadPembiayaan.setFocusable(false);
        binding.etCatatanPemutus1.setFocusable(false);
        binding.etCatatanPemutus2.setFocusable(false);
        binding.etRencanaPenandatangananAkad.setFocusable(false);
        binding.etBulanAngsuranPertama.setFocusable(false);
        binding.etTanggalPenilaian.setFocusable(false);
        binding.etTanggalAktaNikah.setFocusable(false);
        binding.etKepemilikanAset.setFocusable(false);
        binding.etJenisDokumenTanah.setFocusable(false);
        binding.etJenisDokumenTempatTinggal.setFocusable(false);
        binding.etJenisKendaraan.setFocusable(false);
        binding.etDokumenKepemilikanKendaraan.setFocusable(false);

    }

    private void defaultViewCondition(){
        if(akad.equalsIgnoreCase("murabahah")||akad.equalsIgnoreCase("ijarah")){
            binding.llAkadMmq.setVisibility(View.GONE);
            binding.llAkadWakalah.setVisibility(View.VISIBLE);
            binding.llFotoAset.setVisibility(View.GONE);
            binding.tvFotoAset.setVisibility(View.GONE);
        }
        else if(akad.equalsIgnoreCase("MMQ")){
            binding.llAkadMmq.setVisibility(View.VISIBLE);
            binding.llAsetLainnya.setVisibility(View.GONE);
            binding.llAsetTanah.setVisibility(View.GONE);
            binding.llAsetTempatTinggal.setVisibility(View.GONE);
            binding.llAsetKendaraan.setVisibility(View.GONE);
            binding.llAkadWakalah.setVisibility(View.GONE);
            binding.tvFotoAset.setVisibility(View.VISIBLE);
            binding.llFotoAset.setVisibility(View.VISIBLE);
        }
    }




    @Override
    public void onSelect(String title, MGenericModel data) {
        if(title.equalsIgnoreCase(binding.tfKepemilikanAset.getLabelText())){
            binding.etKepemilikanAset.setText(data.getDESC());
            if(data.getDESC().equalsIgnoreCase("tanah")){
                binding.llAsetTanah.setVisibility(View.VISIBLE);
                binding.llAsetKendaraan.setVisibility(View.GONE);
                binding.llAsetTempatTinggal.setVisibility(View.GONE);
                binding.llAsetLainnya.setVisibility(View.GONE);
            }
           else if(data.getDESC().equalsIgnoreCase("tempat tinggal")){
                binding.llAsetTanah.setVisibility(View.GONE);
                binding.llAsetKendaraan.setVisibility(View.GONE);
                binding.llAsetTempatTinggal.setVisibility(View.VISIBLE);
                binding.llAsetLainnya.setVisibility(View.GONE);
            }
            else if(data.getDESC().equalsIgnoreCase("kendaraan")){
                binding.llAsetTanah.setVisibility(View.GONE);
                binding.llAsetKendaraan.setVisibility(View.VISIBLE);
                binding.llAsetTempatTinggal.setVisibility(View.GONE);
                binding.llAsetLainnya.setVisibility(View.GONE);
            }
            else if(data.getDESC().equalsIgnoreCase("lainnya")){
                binding.llAsetTanah.setVisibility(View.GONE);
                binding.llAsetKendaraan.setVisibility(View.GONE);
                binding.llAsetTempatTinggal.setVisibility(View.GONE);
                binding.llAsetLainnya.setVisibility(View.VISIBLE);
            }
        }
        else if(title.equalsIgnoreCase(binding.tfJenisDokumenTanah.getLabelText())){
            binding.etJenisDokumenTanah.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfJenisDokumenTempatTinggal.getLabelText())){
            binding.etJenisDokumenTempatTinggal.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfJenisKendaraan.getLabelText())){
            binding.etJenisKendaraan.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfDokumenKepemilikanKendaraan.getLabelText())){
            binding.etDokumenKepemilikanKendaraan.setText(data.getDESC());
        }
    }

    private void validateField(EditText editText, TextFieldBoxes textFieldBoxes){
        if(editText.getText().toString().isEmpty()){
            adaFieldBelumDiisi=true;
            AppUtil.notiferror(DataAkadActivity.this, findViewById(android.R.id.content), "Harap Isi "+textFieldBoxes.getLabelText());
        }
    }

    private void allOnTextChanged(){
        binding.etHargaTanah.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etHargaTanah));
        binding.etHargaBangunan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etHargaBangunan));
        binding.etHargaKendaraan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etHargaKendaraan));
        binding.etNilaiAsetLainnya.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etNilaiAsetLainnya));
        binding.etHargaTanahTempatTinggal.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etHargaTanahTempatTinggal));

        binding.etTotalNilaiBangunan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalNilaiBangunan));
        binding.etTotalNilaiTanahTempatTinggal.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalNilaiTanahTempatTinggal));
        binding.etTotalNilaiAsetTanah.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalNilaiAsetTanah));
        binding.etTotalNilaiAsetTempatTinggal.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalNilaiAsetTempatTinggal));
        binding.etTotalNilaiTanah.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalNilaiTanah));
        binding.etHargaPerolehanBarang.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etHargaPerolehanBarang));

        binding.etHargaTanah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!binding.etLuasTanah.getText().toString().isEmpty()&&!binding.etHargaTanah.getText().toString().isEmpty()){
                    //total nilai = luas dikali harga
                    Long totalNilai=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etHargaTanah.getText().toString())))*Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etLuasTanah.getText().toString())));
                    binding.etTotalNilaiTanah.setText(String.valueOf(totalNilai));
                    binding.etTotalNilaiAsetTanah.setText(String.valueOf(totalNilai));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etLuasTanah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!binding.etLuasTanah.getText().toString().isEmpty()&&!binding.etHargaTanah.getText().toString().isEmpty()){
                    //total nilai = luas dikali harga
                    Long totalNilai=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etHargaTanah.getText().toString())))*Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etLuasTanah.getText().toString())));
                    binding.etTotalNilaiTanah.setText(String.valueOf(totalNilai));
                    binding.etTotalNilaiAsetTanah.setText(String.valueOf(totalNilai));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etHargaBangunan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!binding.etLuasBangunan.getText().toString().isEmpty()&&!binding.etHargaBangunan.getText().toString().isEmpty()){
                    //total nilai = luas dikali harga
                    Long totalNilai=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etHargaBangunan.getText().toString())))*Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etLuasBangunan.getText().toString())));
                    binding.etTotalNilaiBangunan.setText(String.valueOf(totalNilai));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etLuasBangunan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!binding.etLuasBangunan.getText().toString().isEmpty()&&!binding.etHargaBangunan.getText().toString().isEmpty()){
                    //total nilai = luas dikali harga
                    Long totalNilai=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etHargaBangunan.getText().toString())))*Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etLuasBangunan.getText().toString())));
                    binding.etTotalNilaiBangunan.setText(String.valueOf(totalNilai));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etHargaTanahTempatTinggal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!binding.etLuasTanahTempatTinggal.getText().toString().isEmpty()&&!binding.etHargaTanahTempatTinggal.getText().toString().isEmpty()){
                    //total nilai = luas dikali harga
                    Long totalNilai=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etHargaTanahTempatTinggal.getText().toString())))*Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etLuasTanahTempatTinggal.getText().toString())));
                    binding.etTotalNilaiTanahTempatTinggal.setText(String.valueOf(totalNilai));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etLuasTanahTempatTinggal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!binding.etLuasTanahTempatTinggal.getText().toString().isEmpty()&&!binding.etHargaTanahTempatTinggal.getText().toString().isEmpty()){
                    //total nilai = luas dikali harga
                    Long totalNilai=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etHargaTanahTempatTinggal.getText().toString())))*Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etLuasTanahTempatTinggal.getText().toString())));
                    binding.etTotalNilaiTanahTempatTinggal.setText(String.valueOf(totalNilai));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etTotalNilaiTanahTempatTinggal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!binding.etTotalNilaiBangunan.getText().toString().isEmpty()&&!binding.etTotalNilaiTanahTempatTinggal.getText().toString().isEmpty()){
                    //total nilai = luas dikali harga
                    Long totalNilai=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etTotalNilaiBangunan.getText().toString())))+Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etTotalNilaiTanahTempatTinggal.getText().toString())));
                    binding.etTotalNilaiAsetTempatTinggal.setText(String.valueOf(totalNilai));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etTotalNilaiBangunan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!binding.etTotalNilaiTanahTempatTinggal.getText().toString().isEmpty()&&!binding.etTotalNilaiBangunan.getText().toString().isEmpty()){
                    //total nilai = luas dikali harga
                    Long totalNilai=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etTotalNilaiBangunan.getText().toString())))+Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etTotalNilaiTanahTempatTinggal.getText().toString())));
                    binding.etTotalNilaiAsetTempatTinggal.setText(String.valueOf(totalNilai));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    //UPLOAD FILE METHODS


    private void openCamera(String namaFoto) {
        if (ContextCompat.checkSelfPermission(DataAkadActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(DataAkadActivity.this, new String[]{Manifest.permission.CAMERA},
                    100);
        } else {
            Uri outputFileUri = getCaptureImageOutputUri(namaFoto);
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(captureIntent, 1);
        }
    }
    public void openGalery() {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(Intent.createChooser(it, "Select File"), 2);
    }

    public void openFile() {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("application/pdf");
        startActivityForResult(Intent.createChooser(it, "Select File"), 3);
    }

    private Uri getCaptureImageOutputUri(String namaFoto) {
        Uri outputFileUri = null;
        File getImage = DataAkadActivity.this.getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                outputFileUri = FileProvider.getUriForFile(DataAkadActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), namaFoto + ".png"));
            } else {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), namaFoto + ".png"));
            }
        }
        return outputFileUri;
    }

    public Uri getPickImageResultUri(Intent data, String namaFoto) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri(namaFoto) : data.getData();
    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        switch(idMenu){
            case "Take Photo":
                    tipeFileAset="png";
                    openCamera("fotoAset");
                break;
            case "Pick Photo":
                    openGalery();
                tipeFileAset="png";
                break;
            case "Pick File":
                    openFile();
                break;
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

                setDataImage(binding.ivFotoAset, data, "dokumenaset");


    }

    private void setDataImage(ImageView iv, Intent data, String namaFoto) {
        Bitmap bitmap = null;
        Uri uri;
        if (getPickImageResultUri(data, namaFoto) != null) {
            uri = getPickImageResultUri(data, namaFoto);
            try {

                bitmap = MediaStore.Images.Media.getBitmap(DataAkadActivity.this.getContentResolver(), uri);
                bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                bitmap = AppUtil.rotateImageIfRequired(DataAkadActivity.this, bitmap, uri);
                iv.setImageBitmap(bitmap);


            } catch (Exception e) {
                e.printStackTrace();

                try{
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));
                        Uri uriPdf = data.getData();
                        valBase64PdfAset= AppUtil.encodeFileToBase64(DataAkadActivity.this,uriPdf);
                }
                catch (Exception e2){
                    e2.printStackTrace();
                }

            }
        }
    }

    //END OF UPLOAD METHODS

    @Override
    public void success(boolean val) {
        finish();
    }

    @Override
    public void confirm(boolean val) {

    }
}