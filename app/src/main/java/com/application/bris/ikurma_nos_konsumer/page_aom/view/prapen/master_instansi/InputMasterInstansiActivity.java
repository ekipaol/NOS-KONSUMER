package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.master_instansi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseFile;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseLogicalDoc;
import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.model.request.foto.ReqUploadFile;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.DataDetilInstansi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.DataLkpUtama;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.JaminandanDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqAcctNumber;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDetilInstansi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UploadImage;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityInputInstansiBinding;
import com.application.bris.ikurma_nos_konsumer.model.ikurma_branch.DataBranchIkurma;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataCIfRekening;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataInquiryRekening;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DropdownGlobalPrapen;
import com.application.bris.ikurma_nos_konsumer.model.prapen.InputInstansi;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogListBranch;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogListSektorEkonomi;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ListBranchListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_marketing.DataMarketingActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_pembiayaan.DataPembiayaanActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g1_akad_dan_asesoir.field_ojk_bi.ActivityFieldOjkBI;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class InputMasterInstansiActivity extends AppCompatActivity implements View.OnClickListener, CameraListener, ConfirmListener, GenericListenerOnSelect, ListBranchListener {
    private PrapenAoActivityInputInstansiBinding binding;
    List<JaminandanDokumen> jd;

    private String fileNamePks = "",fileNameLain1 = "",fileNameLain2 = "",fileNameLkp = "", tipeFile;
    private String idFilePks = "",idFileLain1 = "",idFileLain2 = "",idFileLkp= "";
    private boolean rekeningBerubah=false;
    private Long idInstansi=0l;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    private List<MGenericModel> dropdownInstansiInduk=new ArrayList<>(),dropdownTipePembayaran=new ArrayList<>(),dropdownJenisInstansi=new ArrayList<>(),dropdownPks=new ArrayList<>(),dropdownRuangLingkup=new ArrayList<>(),dropdownUnitBisnis=new ArrayList<>(),dropdownPerpanjangOtomatis=new ArrayList<>(),dropdownStatusPks=new ArrayList<>(),dropdownJasaPengelolaan=new ArrayList<>();

    private String val_pks = "", val_lain_1 = "", val_lain_2 = "", val_lkp = "",val_branch_office_code="";
    private long val_instansi_induk=0l;
    private final int UPLOAD_PKS = 1, UPLOAD_LAIN_1 = 2, UPLOAD_LAIN_2 = 3, UPLOAD_LKP = 4;
    int idUpload=0;
    boolean dialogOpened=false;
    boolean errorUpload=false;
    private  boolean lolosValidasi =true;
    private DataDetilInstansi dataInstansi;
    private DataLkpUtama dataLkpUtama;
    private List<UploadImage> dataDokumenLainnya;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PrapenAoActivityInputInstansiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);

        if(getIntent().hasExtra("idInstansi")){
            idInstansi=Long.parseLong(getIntent().getStringExtra("idInstansi"));
        }


        onclickSelectDialog();
        setContentView(view);
        disableText();
        backgroundStatusBar();
        isiDropdown();
        otherViewChanges();
//        loadData();
        loadDropdownInstansiInduk();
        AppUtil.toolbarRegular(this, "Detil Instansi");

    }

    private void loadData() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqDetilInstansi req = new ReqDetilInstansi();
        req.setIdInstansi(idInstansi);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryInstansi(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                if (response.isSuccessful()) {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);

                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        Gson gson = new Gson();
                        String listDataString = response.body().getData().get("Data_Instansi").toString();
                        String listDataLkpString = response.body().getData().get("Data_LKP_Utama").toString();
                        String listDataDokumenString = response.body().getData().get("Data_Instansi_Dokumen_Lainnya").toString();
                        Type type = new TypeToken<DataDetilInstansi>() {}.getType();
                        Type typeLkp = new TypeToken<DataLkpUtama>() {}.getType();
                        Type typeDokumen = new TypeToken<List<UploadImage>>() {}.getType();
                        dataInstansi=gson.fromJson(listDataString,type);
                        dataLkpUtama=gson.fromJson(listDataLkpString,typeLkp);
                        dataDokumenLainnya=gson.fromJson(listDataDokumenString,typeDokumen);
                        setData();


                    } else {
                        AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                } else {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    Error error = ParseResponseError.confirmEror(response.errorBody());
                    AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), error.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });


    }

    private void setData(){
        if(!dataInstansi.getIdCabangInputter().equalsIgnoreCase("0")&&!dataInstansi.getIdCabangInputter().equalsIgnoreCase(appPreferences.getKodeCabang())){
            noInputMode();
        }

        binding.etNamaInstansi.setText(dataInstansi.getNamaInstansi());
        binding.etNomorEscrow.setText(dataInstansi.getEscrow());

        if(dataInstansi.getBranchOffice()!=null&&!dataInstansi.getBranchOffice().isEmpty()){
            binding.tfKantorCabang.setVisibility(View.VISIBLE);
            binding.etKantorCabang.setText(dataInstansi.getBranchOffice());
        }
         if(dataInstansi.getAreaOffice()!=null&&!dataInstansi.getAreaOffice().isEmpty()){
           binding.tfAreaCabang.setVisibility(View.VISIBLE);
            binding.etAreaCabang.setText(dataInstansi.getAreaOffice());

        }
         if(dataInstansi.getRegional()!=null&&!dataInstansi.getRegional().isEmpty()){
          binding.tfRegional.setVisibility(View.VISIBLE);
            binding.etRegional.setText(dataInstansi.getRegional());
        }

        if(dataInstansi.getiDMasterInstansi()!=null&&!dataInstansi.getiDMasterInstansi().isEmpty()){
            binding.tfIdMasterInstansi.setVisibility(View.VISIBLE);
            binding.etIdMasterInstansi.setText(dataInstansi.getiDMasterInstansi());
        }

        binding.etCifCabang.setText(dataInstansi.getCif());

        for (int i = 0; i <dropdownInstansiInduk.size() ; i++) {
            if(dropdownInstansiInduk.get(i).getID().equalsIgnoreCase(Long.toString(dataInstansi.getInstansiInduk()))){
                binding.etInstansiInduk.setText(dropdownInstansiInduk.get(i).getDESC());
                val_instansi_induk=dataInstansi.getInstansiInduk();
            }
        }
        binding.etTipePembayaran.setText(dataInstansi.getTipePembayaran());
        binding.etJenisInstansi.setText(dataInstansi.getTipeProduk());
        binding.etTahunBerdiri.setText(dataInstansi.getTahunBerdiri());

        binding.etMemilikiPks.setText(dataInstansi.getPks());
        if(dataInstansi.getPks().equalsIgnoreCase("tidak")){
            binding.tfRuangLingkupPks.setVisibility(View.GONE);
            binding.tfUnitBisnisPengusul.setVisibility(View.GONE);
            binding.tfNomorPks.setVisibility(View.GONE);
            binding.tfTanggalAkhirPks.setVisibility(View.GONE);
            binding.tfTanggalMulaiPks.setVisibility(View.GONE);
            binding.tfPerpanjangPks.setVisibility(View.GONE);
            binding.rlDokumenPks.setVisibility(View.GONE);
            binding.tfStatusPks.setVisibility(View.GONE);
        }
        else{
            binding.tfRuangLingkupPks.setVisibility(View.VISIBLE);
            binding.tfUnitBisnisPengusul.setVisibility(View.VISIBLE);
            binding.tfNomorPks.setVisibility(View.VISIBLE);
            binding.tfTanggalAkhirPks.setVisibility(View.VISIBLE);
            binding.tfTanggalMulaiPks.setVisibility(View.VISIBLE);
            binding.tfPerpanjangPks.setVisibility(View.VISIBLE);
            binding.rlDokumenPks.setVisibility(View.VISIBLE);
            binding.tfStatusPks.setVisibility(View.VISIBLE);

            binding.etRuangLingkupPks.setText(dataInstansi.getRuangLingkupPKS());
            binding.etUnitBisnisPengusul.setText(dataInstansi.getUnitBisnisPengusulPKS());
            binding.etNomorPks.setText(dataInstansi.getNoPKS());
            binding.etStatusPks.setText(dataInstansi.getStatusPKS());
            try{
                if(dataInstansi.getPerpanjanganOtomatisPKS()){
                    binding.etPerpanjangPksOtomatis.setText("Otomatis");
                }
                else{
                    binding.etPerpanjangPksOtomatis.setText("Perpanjangan Manual");
                }
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }

            try{
                checkFileTypeThenSet(InputMasterInstansiActivity.this,dataInstansi.getImgPKSInduk(),binding.ivFotoPks,dataInstansi.getFilenamePksInduk());
                idFilePks=dataInstansi.getImgPKSInduk();
                fileNamePks=dataInstansi.getFilenamePksInduk();
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }

        }
        try{
            if(dataInstansi.isStatusAktifInstansi()){
                binding.swStatusAktif.setChecked(true);
            }
            else{
                binding.swStatusAktif.setChecked(false);
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }



        binding.etAlamatKorespondensi.setText(dataInstansi.getAlamatKorespondensi());
        binding.etKeyPerson.setText(dataInstansi.getKeyPerson());
        binding.etTeleponKeyPerson.setText(dataInstansi.getTeleponKeyPerson());
        binding.etTeleponInstansi.setText(dataInstansi.getNoTelpInstansi());
        binding.etJasaPengelolaan.setText(dataInstansi.getJasaPengolahan());

        try{
            binding.etTanggalLkpUtama.setText(AppUtil.parseTanggalGeneral(dataLkpUtama.getTanggalLKP(),"ddMMyyyy","dd-MM-yyyy"));
            binding.etTanggalLkpUtamaKadaluarsa.setText(AppUtil.parseTanggalGeneral(dataLkpUtama.getTanggalLKPKadaluarsa(),"ddMMyyyy","dd-MM-yyyy"));
            binding.etTanggalMulaiPks.setText(AppUtil.parseTanggalGeneral(dataInstansi.getMulaiPKS(),"ddMMyyyy","dd-MM-yyyy"));
            binding.etTanggalAkhirPks.setText(AppUtil.parseTanggalGeneral(dataInstansi.getAkhirPKS(),"ddMMyyyy","dd-MM-yyyy"));
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }

        try{

            checkFileTypeThenSet(InputMasterInstansiActivity.this,dataLkpUtama.getImg(),binding.ivFotoLkp,dataLkpUtama.getFilename());

            idFileLkp=dataLkpUtama.getImg();
            fileNameLkp=dataLkpUtama.getFilename();

            try{
                checkFileTypeThenSet(InputMasterInstansiActivity.this,dataDokumenLainnya.get(0).getImg(),binding.ivDokumenTambahan1,dataDokumenLainnya.get(0).getFile_Name());
                idFileLain1=dataDokumenLainnya.get(0).getImg();
                fileNameLain1=dataDokumenLainnya.get(0).getFile_Name();
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }

            try{
                checkFileTypeThenSet(InputMasterInstansiActivity.this,dataDokumenLainnya.get(1).getImg(),binding.ivDokumenTambahan2,dataDokumenLainnya.get(1).getFile_Name());
                idFileLain2=dataDokumenLainnya.get(1).getImg();
                fileNameLain2=dataDokumenLainnya.get(1).getFile_Name();
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void loadDropdownInstansiInduk() {
        //  dataUser = getListUser();
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        //pantekan no aplikasi dan aktifitas

        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().dropdownInstansiInduk(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DropdownGlobalPrapen>>() {
                        }.getType();
                        List<DropdownGlobalPrapen> dropdownTemp= gson.fromJson(listDataString, type);

                        dropdownInstansiInduk.clear();
                        for (int i = 0; i <dropdownTemp.size(); i++) {
                            dropdownInstansiInduk.add(new MGenericModel(dropdownTemp.get(i).getKode(),dropdownTemp.get(i).getName()));
                        }

                        loadData();

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");

            }
        });
    }

    private void sendData() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        InputInstansi req = new InputInstansi();
        DataLkpUtama dataLkpUtama=new DataLkpUtama();
        List<UploadImage> dataDokumenLain=new ArrayList<>();
        DataDetilInstansi dataDetilInstansi=new DataDetilInstansi();

        //DATA INSTANSI
        dataDetilInstansi.setIdInstansi(idInstansi);
        dataDetilInstansi.setNamaInstansi(binding.etNamaInstansi.getText().toString());
        dataDetilInstansi.setEscrow(binding.etNomorEscrow.getText().toString());
        dataDetilInstansi.setBranchOfficeCode(val_branch_office_code);
//        dataDetilInstansi.setAreaOffice(binding.etAreaCabang.getText().toString());
//        dataDetilInstansi.setRegional(binding.etRegional.getText().toString());
        dataDetilInstansi.setCif(binding.etCifCabang.getText().toString());
//        dataDetilInstansi.setiDMasterInstansi(binding.etIdMasterInstansi.getText().toString());
        //notes instansi induk ini ngirim ID apa nama nih
        dataDetilInstansi.setInstansiInduk(val_instansi_induk);
        dataDetilInstansi.setTipePembayaran(binding.etTipePembayaran.getText().toString());
        dataDetilInstansi.setTipeProduk(binding.etJenisInstansi.getText().toString());
        dataDetilInstansi.setTahunBerdiri(binding.etTahunBerdiri.getText().toString());
        dataDetilInstansi.setPks(binding.etMemilikiPks.getText().toString());
        dataDetilInstansi.setRuangLingkupPKS(binding.etRuangLingkupPks.getText().toString());
        dataDetilInstansi.setUnitBisnisPengusulPKS(binding.etUnitBisnisPengusul.getText().toString());
        dataDetilInstansi.setNoPKS(binding.etNomorPks.getText().toString());
        dataDetilInstansi.setMulaiPKS(AppUtil.parseTanggalGeneral(binding.etTanggalMulaiPks.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
        dataDetilInstansi.setAkhirPKS(AppUtil.parseTanggalGeneral(binding.etTanggalAkhirPks.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));

        if(binding.etPerpanjangPksOtomatis.getText().toString().equalsIgnoreCase("otomatis")){
            dataDetilInstansi.setPerpanjanganOtomatisPKS(true);
        }
        else{
            dataDetilInstansi.setPerpanjanganOtomatisPKS(false);
        }

        dataDetilInstansi.setImgPKSInduk(idFilePks);
        dataDetilInstansi.setFilenamePksInduk(fileNamePks);
        dataDetilInstansi.setStatusPKS(binding.etStatusPks.getText().toString());
        dataDetilInstansi.setAlamatKorespondensi(binding.etAlamatKorespondensi.getText().toString());
        dataDetilInstansi.setKeyPerson(binding.etKeyPerson.getText().toString());
        dataDetilInstansi.setTeleponKeyPerson(binding.etTeleponKeyPerson.getText().toString());
        dataDetilInstansi.setNoTelpInstansi(binding.etTeleponInstansi.getText().toString());
        dataDetilInstansi.setJasaPengolahan(binding.etJasaPengelolaan.getText().toString());

        if(binding.swStatusAktif.isChecked()){
            dataDetilInstansi.setStatusAktifInstansi(true);
        }
        else{
            dataDetilInstansi.setStatusAktifInstansi(false);
        }

        //LKP utama
        dataLkpUtama.setTanggalLKP(AppUtil.parseTanggalGeneral(binding.etTanggalLkpUtama.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
        dataLkpUtama.setTanggalLKPKadaluarsa(AppUtil.parseTanggalGeneral(binding.etTanggalLkpUtamaKadaluarsa.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
        dataLkpUtama.setImg(idFileLkp);
        dataLkpUtama.setFilename(fileNameLkp);

        //DOKUMEN LAINNYA
        UploadImage dokumen1=new UploadImage();
        UploadImage dokumen2=new UploadImage();

        dokumen1.setImg(idFileLain1);
        dokumen1.setFile_Name(fileNameLain1);
        dokumen2.setImg(idFileLain2);
        dokumen2.setFile_Name(fileNameLain2);

        dataDokumenLain.add(dokumen1);
        dataDokumenLain.add(dokumen2);

        req.setUid(Integer.toString(appPreferences.getUid()));
        req.setDataLkpUtama(dataLkpUtama);
        req.setDataInstansi(dataDetilInstansi);
        req.setDokumenLain(dataDokumenLain);


        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updateInstansi(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                if (response.isSuccessful()) {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        CustomDialog.DialogSuccess(InputMasterInstansiActivity.this, "Success!", "Update Data Instansi Sukses", InputMasterInstansiActivity.this);


                    } else {
                        AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                } else {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    Error error = ParseResponseError.confirmEror(response.errorBody());
                    AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), error.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }



    private void disableText() {
        binding.etKantorCabang.setFocusable(false);
        binding.etAreaCabang.setFocusable(false);
        binding.etRegional.setFocusable(false);
        binding.etCifCabang.setFocusable(false);
        binding.etIdMasterInstansi.setFocusable(false);
        binding.etInstansiInduk.setFocusable(false);
        binding.etTipePembayaran.setFocusable(false);
        binding.etJenisInstansi.setFocusable(false);
        binding.etMemilikiPks.setFocusable(false);
        binding.etRuangLingkupPks.setFocusable(false);
        binding.etUnitBisnisPengusul.setFocusable(false);
        binding.etTanggalMulaiPks.setFocusable(false);
        binding.etTanggalAkhirPks.setFocusable(false);
        binding.etJangkaWaktuPks.setFocusable(false);
        binding.etPerpanjangPksOtomatis.setFocusable(false);
        binding.etStatusPks.setFocusable(false);
        binding.etJasaPengelolaan.setFocusable(false);
        binding.etTanggalLkpUtama.setFocusable(false);
        binding.etTanggalLkpUtamaKadaluarsa .setFocusable(false);

    }

    private void noInputMode() {
        AppUtil.disableEditTexts(binding.getRoot());
        AppUtil.disableButtons(binding.getRoot());
        binding.btnSimpanDataInstansi.setVisibility(View.GONE);
        binding.btnCekEscrow.setVisibility(View.GONE);
        binding.btnFotoLkp.setVisibility(View.GONE);
        binding.btnDokumenTambahan2.setVisibility(View.GONE);
        binding.btnDokumenTambahan1.setVisibility(View.GONE);
        binding.btnFotoPks.setVisibility(View.GONE);
        //khusus 2 button dibawah harus di enable lagi click listenernya, karna bisa di klik

        binding.btnLihatLngp.setVisibility(View.VISIBLE);
        binding.btnLihatLkp.setVisibility(View.VISIBLE);
        binding.btnLihatLkp.setOnClickListener(this);
        binding.btnLihatLngp.setOnClickListener(this);


    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void onclickSelectDialog() {
        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.DialogBackpress(InputMasterInstansiActivity.this);
            }
        });

        binding.tfInstansiInduk.setOnClickListener(this);
        binding.etInstansiInduk.setOnClickListener(this);
        binding.tfTipePembayaran.setOnClickListener(this);
        binding.etTipePembayaran.setOnClickListener(this);
        binding.tfJenisInstansi.setOnClickListener(this);
        binding.etJenisInstansi.setOnClickListener(this);
        binding.tfMemilikiPks.setOnClickListener(this);
        binding.etMemilikiPks.setOnClickListener(this);
        binding.tfRuangLingkupPks.setOnClickListener(this);
        binding.etRuangLingkupPks.setOnClickListener(this);
        binding.tfUnitBisnisPengusul.setOnClickListener(this);
        binding.etUnitBisnisPengusul.setOnClickListener(this);
        binding.tfInstansiInduk.setOnClickListener(this);
        binding.etInstansiInduk.setOnClickListener(this);
        binding.tfPerpanjangPks.setOnClickListener(this);
        binding.etPerpanjangPksOtomatis.setOnClickListener(this);
        binding.tfStatusPks.setOnClickListener(this);
        binding.etStatusPks.setOnClickListener(this);
        binding.tfJasaPengelolaan.setOnClickListener(this);
        binding.etJasaPengelolaan.setOnClickListener(this);
        binding.btnCekEscrow.setOnClickListener(this);
        binding.btnSimpanDataInstansi.setOnClickListener(this);

        binding.tfTanggalMulaiPks.getEndIconImageButton().setOnClickListener(v -> AppUtil.genericCalendarDialog(InputMasterInstansiActivity.this,binding.etTanggalMulaiPks));
        binding.etTanggalMulaiPks.setOnClickListener(v -> AppUtil.genericCalendarDialog(InputMasterInstansiActivity.this,binding.etTanggalMulaiPks));
        binding.tfTanggalAkhirPks.getEndIconImageButton().setOnClickListener(v -> AppUtil.genericCalendarDialog(InputMasterInstansiActivity.this,binding.etTanggalAkhirPks));
        binding.etTanggalAkhirPks.setOnClickListener(v -> AppUtil.genericCalendarDialog(InputMasterInstansiActivity.this,binding.etTanggalAkhirPks));
        binding.tfTanggalLkpUtama.getEndIconImageButton().setOnClickListener(v -> AppUtil.genericCalendarDialog(InputMasterInstansiActivity.this,binding.etTanggalLkpUtama));
        binding.etTanggalLkpUtama.setOnClickListener(v -> AppUtil.genericCalendarDialog(InputMasterInstansiActivity.this,binding.etTanggalLkpUtama));
        binding.tfTanggalLkpUtamaKadaluarsa.getEndIconImageButton().setOnClickListener(v -> AppUtil.genericCalendarDialog(InputMasterInstansiActivity.this,binding.etTanggalLkpUtamaKadaluarsa));
        binding.etTanggalLkpUtamaKadaluarsa.setOnClickListener(v -> AppUtil.genericCalendarDialog(InputMasterInstansiActivity.this,binding.etTanggalLkpUtamaKadaluarsa));

        binding.ivFotoPks.setOnClickListener(this);
        binding.ivDokumenTambahan1.setOnClickListener(this);
        binding.ivDokumenTambahan2.setOnClickListener(this);
        binding.ivFotoLkp.setOnClickListener(this);
        binding.btnFotoPks.setOnClickListener(this);
        binding.btnDokumenTambahan1.setOnClickListener(this);
        binding.btnDokumenTambahan2.setOnClickListener(this);
        binding.btnFotoLkp.setOnClickListener(this);
        binding.btnLihatLkp.setOnClickListener(this);
        binding.btnLihatLngp.setOnClickListener(this);

        easyEndIconDropdownClick(binding.tfInstansiInduk,dropdownInstansiInduk);
        easyEndIconDropdownClick(binding.tfTipePembayaran,dropdownTipePembayaran);
        easyEndIconDropdownClick(binding.tfJenisInstansi,dropdownJenisInstansi);
        easyEndIconDropdownClick(binding.tfMemilikiPks,dropdownPks);
        easyEndIconDropdownClick(binding.tfRuangLingkupPks,dropdownRuangLingkup);
        easyEndIconDropdownClick(binding.tfPerpanjangPks,dropdownPerpanjangOtomatis);
        easyEndIconDropdownClick(binding.tfStatusPks,dropdownStatusPks);

        binding.tfUnitBisnisPengusul.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogListBranch.display(getSupportFragmentManager(), InputMasterInstansiActivity.this);
            }
        });


    }

    private void easyEndIconDropdownClick(TextFieldBoxes textFieldBoxes,List<MGenericModel> dropdown){
        textFieldBoxes.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),textFieldBoxes.getLabelText(),dropdown,InputMasterInstansiActivity.this);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_foto_pks:
                BSUploadFile.displayWithTitle(InputMasterInstansiActivity.this.getSupportFragmentManager(), this, "");
                idUpload = UPLOAD_PKS;
                break;
            case R.id.btn_dokumen_tambahan_1:
                BSUploadFile.displayWithTitle(InputMasterInstansiActivity.this.getSupportFragmentManager(), this, "");
                idUpload = UPLOAD_LAIN_1;
                break;
            case R.id.btn_dokumen_tambahan_2:
                BSUploadFile.displayWithTitle(InputMasterInstansiActivity.this.getSupportFragmentManager(), this, "");
                idUpload = UPLOAD_LAIN_2;
                break;
            case R.id.btn_foto_lkp:
                BSUploadFile.displayWithTitle(InputMasterInstansiActivity.this.getSupportFragmentManager(), this, "");
                idUpload = UPLOAD_LKP;
                break;
            case R.id.tf_instansi_induk:
            case R.id.et_instansi_induk:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfInstansiInduk.getLabelText(),dropdownInstansiInduk,this);
                break;
            case R.id.tf_tipe_pembayaran:
            case R.id.et_tipe_pembayaran:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTipePembayaran.getLabelText(),dropdownTipePembayaran,this);
                break;
            case R.id.tf_jenis_instansi:
            case R.id.et_jenis_instansi:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJenisInstansi.getLabelText(),dropdownJenisInstansi,this);
                break;
            case R.id.tf_memiliki_pks:
            case R.id.et_memiliki_pks:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfMemilikiPks.getLabelText(),dropdownPks,this);
                break;
            case R.id.tf_ruang_lingkup_pks:
            case R.id.et_ruang_lingkup_pks:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfRuangLingkupPks.getLabelText(),dropdownRuangLingkup,this);
                break;
            case R.id.tf_unit_bisnis_pengusul:
            case R.id.et_unit_bisnis_pengusul:
                DialogListBranch.display(getSupportFragmentManager(), InputMasterInstansiActivity.this);
                break;
            case R.id.tf_perpanjang_pks:
            case R.id.et_perpanjang_pks_otomatis:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfPerpanjangPks.getLabelText(),dropdownPerpanjangOtomatis,this);
                break;
            case R.id.tf_status_pks:
            case R.id.et_status_pks:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfStatusPks.getLabelText(),dropdownStatusPks,this);
                break;
            case R.id.tf_jasa_pengelolaan:
            case R.id.et_jasa_pengelolaan:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJasaPengelolaan.getLabelText(),dropdownJasaPengelolaan,this);
                break;
            case R.id.btn_lihat_lkp:
                if(idInstansi>0){
                    Intent intent=new Intent(InputMasterInstansiActivity.this,ListLkpKoordinasiActivity.class);
                    intent.putExtra("idInstansi",idInstansi);
                    startActivity(intent);
                }
                else{
                    AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), "Harap Simpan Dahulu Data Instansi");
                }

                break;
            case R.id.btn_lihat_lngp:
                if(rekeningBerubah){
                    binding.tfNomorEscrow.setError("Harap Cek Rekening",true);
                    AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), "Harap cek nomor rekening dahulu sebelum mengakses halaman LNGP");
                }
                else{
                    if(idInstansi>0){
                        Intent intent2=new Intent(InputMasterInstansiActivity.this,ListLngpActivity.class);
                        intent2.putExtra("idInstansi",idInstansi);
                        intent2.putExtra("escrow",binding.etNomorEscrow.getText().toString());
                        startActivity(intent2);
                    }
                    else{
                        AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), "Harap Simpan Dahulu Data Instansi");
                         }

                }
                break;

            case R.id.btn_simpan_data_instansi:
                if(rekeningBerubah){
                    binding.tfNomorEscrow.setError("Harap Cek Rekening",true);
                    AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), "Harap cek nomor rekening dahulu");
                }
                else{
                    validasi();
                }

                break;
            case R.id.btn_cek_escrow:
                if(binding.etNomorEscrow.getText().toString().isEmpty()){
                    binding.tfNomorEscrow.setError("Harap isi rekening",true);
                }
                else{
//                    Toast.makeText(this, "Pura pura konek API", Toast.LENGTH_SHORT).show();
//                    binding.etKantorCabang.setText("Kantor Cabang 1");
//                    binding.etAreaCabang.setText("Area 1");
//                    binding.etRegional.setText("Regional 1");
//                    binding.etCifCabang.setText("0000000000");
//                    binding.etIdMasterInstansi.setText("67888099000");
//                    rekeningBerubah=false;
                    cekRekening();

                    break;
                }

        }
    }

    private void validasi() {
        lolosValidasi =true;
        easyValidateField(binding.etNamaInstansi,binding.tfNamaInstansi);
        easyValidateField(binding.etNomorEscrow,binding.tfNomorEscrow);
        easyValidateField(binding.etNamaInstansi,binding.tfNamaInstansi);
//        easyValidateField(binding.etKantorCabang,binding.tfKantorCabang);
//        easyValidateField(binding.etAreaCabang,binding.tfAreaCabang);
//        easyValidateField(binding.etRegional,binding.tfRegional);
//        easyValidateField(binding.etCifCabang,binding.tfCifCabang);
//        easyValidateField(binding.etIdMasterInstansi,binding.tfIdMasterInstansi);
        easyValidateField(binding.etInstansiInduk,binding.tfInstansiInduk);
        easyValidateField(binding.etTipePembayaran,binding.tfTipePembayaran);
        easyValidateField(binding.etJenisInstansi,binding.tfJenisInstansi);
        easyValidateField(binding.etTahunBerdiri,binding.tfTahunBerdiri);
        easyValidateField(binding.etMemilikiPks,binding.tfMemilikiPks);

        if(binding.etMemilikiPks.getText().toString().equalsIgnoreCase("ya")){
            easyValidateField(binding.etRuangLingkupPks,binding.tfRuangLingkupPks);
            easyValidateField(binding.etUnitBisnisPengusul,binding.tfUnitBisnisPengusul);
            easyValidateField(binding.etNomorPks,binding.tfNomorPks);
            easyValidateField(binding.etTanggalMulaiPks,binding.tfTanggalAkhirPks);
            easyValidateField(binding.etJangkaWaktuPks,binding.tfJangkaWaktuPks);
            easyValidateField(binding.etPerpanjangPksOtomatis,binding.tfPerpanjangPks);
            easyValidateField(binding.etStatusPks,binding.tfStatusPks);
        }

        easyValidateField(binding.etAlamatKorespondensi,binding.tfAlamatKorespondensi);
        easyValidateField(binding.etKeyPerson,binding.tfKeyPerson);
        easyValidateField(binding.etTeleponKeyPerson,binding.tfTeleponKeyPerson);
        easyValidateField(binding.etTeleponInstansi,binding.tfTeleponInstansi);
        easyValidateField(binding.etJasaPengelolaan,binding.tfJasaPengelolaan);
        easyValidateField(binding.etTanggalLkpUtama,binding.tfTanggalLkpUtama);
        easyValidateField(binding.etTanggalLkpUtamaKadaluarsa,binding.tfTanggalLkpUtamaKadaluarsa);
        if(lolosValidasi){
            //do send data
            sendData();
//            Toast.makeText(this, "Nit not lolos validasi dan pura pura nyimpen", Toast.LENGTH_SHORT).show();
        }
    }

    private void easyValidateField(EditText editText, TextFieldBoxes textFieldBoxes){
        if(editText.getText().toString().trim().isEmpty() || editText.getText().toString().trim().equalsIgnoreCase(" ")){
            textFieldBoxes.setError(textFieldBoxes.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), textFieldBoxes.getLabelText() + " " + getString(R.string.title_validate_field));
            lolosValidasi =false;
        }

    }

    private void isiDropdown(){
//        dropdownInstansiInduk.add(new MGenericModel("1","Instansi 1"));
//        dropdownInstansiInduk.add(new MGenericModel("2","Instansi 2"));

        dropdownTipePembayaran.add(new MGenericModel("1","Payroll"));
        dropdownTipePembayaran.add(new MGenericModel("2","Non Payroll"));

        dropdownJenisInstansi.add(new MGenericModel("1","SKPP"));
        dropdownJenisInstansi.add(new MGenericModel("2","SKPD"));
        dropdownJenisInstansi.add(new MGenericModel("3","TNI/POLRI"));
        dropdownJenisInstansi.add(new MGenericModel("4","Lembaga Negara"));
        dropdownJenisInstansi.add(new MGenericModel("5","BUMN Group"));
        dropdownJenisInstansi.add(new MGenericModel("6","Wholesale & SME Group"));
        dropdownJenisInstansi.add(new MGenericModel("7","BUMD"));
        dropdownJenisInstansi.add(new MGenericModel("8","Perusahaan Swasta"));
        dropdownJenisInstansi.add(new MGenericModel("9","Yayasan"));
        dropdownJenisInstansi.add(new MGenericModel("10","Rumah Sakit"));

        dropdownPks.add(new MGenericModel("1","Ya"));
        dropdownPks.add(new MGenericModel("2","Tidak"));

        dropdownUnitBisnis.add(new MGenericModel("1","Cabang 1"));
        dropdownUnitBisnis.add(new MGenericModel("2","Cabang 2"));

        dropdownRuangLingkup.add(new MGenericModel("1","PKS Pusat"));
        dropdownRuangLingkup.add(new MGenericModel("2","PKS Lokal Area"));
        dropdownRuangLingkup.add(new MGenericModel("3","PKS Lokal Branch"));

        dropdownPerpanjangOtomatis.add(new MGenericModel("1","Otomatis"));
        dropdownPerpanjangOtomatis.add(new MGenericModel("2","Perpanjangan Manual"));

        dropdownStatusPks.add(new MGenericModel("1","Baru"));
        dropdownStatusPks.add(new MGenericModel("2","Perpanjangan"));

        dropdownJasaPengelolaan.add(new MGenericModel("1","Ya"));
        dropdownJasaPengelolaan.add(new MGenericModel("2","Tidak"));

    }

    private void otherViewChanges(){
        binding.tvHasilCekRekening.setVisibility(View.GONE);
        binding.tfKantorCabang.setVisibility(View.GONE);
        binding.tfAreaCabang.setVisibility(View.GONE);
        binding.tfRegional.setVisibility(View.GONE);
        binding.tfIdMasterInstansi.setVisibility(View.GONE);

        //di hide dlu soalnya agak repot buat nyari time differencenya
        binding.tfJangkaWaktuPks.setVisibility(View.GONE);

        binding.etNomorEscrow.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                rekeningBerubah=true;

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etTanggalMulaiPks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SimpleDateFormat tanggalMulai=new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat tanggalAkhir=new SimpleDateFormat("dd-MM-yyyy");
                try{
                    Date tanggalAwalDate=tanggalMulai.parse(binding.etTanggalMulaiPks.getText().toString());
                    Date tanggalAkhirDate=tanggalAkhir.parse(binding.etTanggalMulaiPks.getText().toString());

                    Calendar startCalendar = new GregorianCalendar();
                    startCalendar.setTime(tanggalAwalDate);
                    Calendar endCalendar = new GregorianCalendar();
                    endCalendar.setTime(tanggalAkhirDate);

                    int diffMonth = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

                    binding.etJangkaWaktuPks.setText(Integer.toString(diffMonth)+ " Bulan");

                }
                catch (ParseException e){
                    e.printStackTrace();
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etTanggalAkhirPks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SimpleDateFormat tanggalMulai=new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat tanggalAkhir=new SimpleDateFormat("dd-MM-yyyy");
                try{
                    Date tanggalAwalDate=tanggalMulai.parse(binding.etTanggalMulaiPks.getText().toString());
                    Date tanggalAkhirDate=tanggalAkhir.parse(binding.etTanggalMulaiPks.getText().toString());

                    Calendar startCalendar = new GregorianCalendar();
                    startCalendar.setTime(tanggalAwalDate);
                    Calendar endCalendar = new GregorianCalendar();
                    endCalendar.setTime(tanggalAkhirDate);

                    int diffMonth = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
                    String bulan = String.valueOf(diffMonth).concat(" Bulan");

                    binding.etJangkaWaktuPks.setText(bulan);
                    AppUtil.logSecure("jangkrik",bulan);

                }
                catch (ParseException e){
                    e.printStackTrace();
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    //UPLOAD FILE METHODS

    private void easySelectCameraMenu(String idMenu, int KODE_UPLOAD, String namaDokumen){
        if(dialogOpened){
            if(idMenu.equalsIgnoreCase("Take Photo")){
                tipeFile = "png";
                openCamera(KODE_UPLOAD, namaDokumen);
            }
            else if(idMenu.equalsIgnoreCase("Pick Photo")){
                tipeFile="png";
                openGalery(KODE_UPLOAD);
            }
            else if(idMenu.equalsIgnoreCase("Pick File")){
                tipeFile="pdf";
                openFile(KODE_UPLOAD);
            }
            dialogOpened=false;
        }


    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        dialogOpened=true;
        if(idUpload==UPLOAD_PKS){
            easySelectCameraMenu(idMenu,UPLOAD_PKS,"dokPks");
        }
        else if(idUpload==UPLOAD_LAIN_1){
            easySelectCameraMenu(idMenu,UPLOAD_LAIN_1,"dokLain1");
        }
        else if(idUpload==UPLOAD_LAIN_2){
            easySelectCameraMenu(idMenu,UPLOAD_LAIN_2,"dokLain2");
        }
        else if(idUpload==UPLOAD_LKP){
            easySelectCameraMenu(idMenu,UPLOAD_LKP,"dokLkp");
        }




    }

    private void openCamera(int cameraCode, String namaFoto) {
        checkCameraPermission(cameraCode, namaFoto);
    }

    public void openGalery(int cameraCode) {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(Intent.createChooser(it, "Select File"), cameraCode);
    }

    public void openFile(int cameraCode) {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("application/pdf");
        startActivityForResult(Intent.createChooser(it, "Select File"), cameraCode);
    }

    public void checkCameraPermission(int cameraCode, String namaFoto) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    100);
        } else {
            Uri outputFileUri = getCaptureImageOutputUri(namaFoto);
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(captureIntent, cameraCode);
        }
    }

    private void directOpenCamera(int cameraCode, String namaFoto) {
        Uri outputFileUri = getCaptureImageOutputUri(namaFoto);
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(captureIntent, cameraCode);
    }

    private Uri getCaptureImageOutputUri(String namaFoto) {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                outputFileUri = FileProvider.getUriForFile(InputMasterInstansiActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), namaFoto + ".png"));
            } else {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), namaFoto + ".png"));
            }
        }
        return outputFileUri;
    }
    //logical doc
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        errorUpload=false;
        switch (requestCode) {
            case UPLOAD_PKS:
                setDataImage(binding.ivFotoPks,data,"dokPks",UPLOAD_PKS);
                checkFileTypeThenUpload(fileNamePks,binding.etNomorEscrow.getText().toString()+"_"+appPreferences.getKodeCabang()+"_pks",binding.ivFotoPks,val_pks,UPLOAD_PKS);
                break;
            case UPLOAD_LAIN_1:
                setDataImage(binding.ivDokumenTambahan1,data,"dokLain1",UPLOAD_LAIN_1);
                checkFileTypeThenUpload(fileNameLain1,binding.etNomorEscrow.getText().toString()+"_"+appPreferences.getKodeCabang()+"_dokLain1",binding.ivDokumenTambahan1,val_lain_1,UPLOAD_LAIN_1);
                break;
            case UPLOAD_LAIN_2:
                setDataImage(binding.ivDokumenTambahan2,data,"dokLain2",UPLOAD_LAIN_2);
                checkFileTypeThenUpload(fileNameLain2,binding.etNomorEscrow.getText().toString()+"_"+appPreferences.getKodeCabang()+"_dokLain2",binding.ivDokumenTambahan2,val_lain_2,UPLOAD_LAIN_2);
                break;
            case UPLOAD_LKP:
                setDataImage(binding.ivFotoLkp,data,"dokLkp",UPLOAD_LKP);
                checkFileTypeThenUpload(fileNameLkp,binding.etNomorEscrow.getText().toString()+"_"+appPreferences.getKodeCabang()+"_dokLkpUtama",binding.ivFotoLkp,val_lkp,UPLOAD_LKP);
                break;
        }
    }

    public Uri getPickImageResultUri(Intent data, String namaFoto) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri(namaFoto) : data.getData();
    }

    private void setDataImage(ImageView iv, Intent data, String namaFoto,int KODE_UPLOAD) {
        Bitmap bitmap = null;
        Uri uri;
        if (getPickImageResultUri(data, namaFoto) != null) {
            uri = getPickImageResultUri(data, namaFoto);
            try {

                bitmap = MediaStore.Images.Media.getBitmap(InputMasterInstansiActivity.this.getContentResolver(), uri);
                bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                bitmap = AppUtil.rotateImageIfRequired(InputMasterInstansiActivity.this, bitmap, uri);
                iv.setImageBitmap(bitmap);


            } catch (NullPointerException e) {
                e.printStackTrace();

                try{
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));
                    iv.setScaleType(ImageView.ScaleType.FIT_CENTER);

                    if(KODE_UPLOAD==UPLOAD_PKS){
                        Uri uriPdf = data.getData();
                        val_pks= AppUtil.encodeFileToBase64(InputMasterInstansiActivity.this,uriPdf);

                    }
                    else if(KODE_UPLOAD==UPLOAD_LAIN_1){
                        Uri uriPdf = data.getData();
                        val_lain_1= AppUtil.encodeFileToBase64(InputMasterInstansiActivity.this,uriPdf);
                    }
                    else if(KODE_UPLOAD==UPLOAD_LAIN_2){
                        Uri uriPdf = data.getData();
                        val_lain_2= AppUtil.encodeFileToBase64(InputMasterInstansiActivity.this,uriPdf);
                    }
                    else if(KODE_UPLOAD==UPLOAD_LKP){
                        Uri uriPdf = data.getData();
                        val_lkp= AppUtil.encodeFileToBase64(InputMasterInstansiActivity.this,uriPdf);
                    }
                }
                catch (Exception e2){
                    e2.printStackTrace();
                }
            }
            catch (FileNotFoundException e2){
                e2.printStackTrace();
                errorUpload=true;
            }
            catch (Exception e3){
                e3.printStackTrace();
            }
        }
    }

    @Override
    public void success(boolean val) {
        finish();
    }

    @Override
    public void confirm(boolean val) {

    }

    public void cekRekening() {
        //  dataUser = getListUser();
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        //pantekan no aplikasi dan aktifitas
        ReqAcctNumber req=new ReqAcctNumber();
        req.setAccountNo(binding.etNomorEscrow.getText().toString());

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryDataRekening(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString="{}";
                        try{
                            listDataString = response.body().getData().toString();
                        }
                        catch (NullPointerException e){
                            e.printStackTrace();
                        }

                        Gson gson = new Gson();
                        Type type = new TypeToken<DataInquiryRekening>() {
                        }.getType();
                        DataInquiryRekening dataCIfRekening =  gson.fromJson(listDataString, type);

                        if(dataCIfRekening.getCompdisp()!=null&&!dataCIfRekening.getCompdisp().isEmpty()){

                          val_branch_office_code=dataCIfRekening.getCocode();

                            binding.tvHasilCekRekening.setVisibility(View.VISIBLE);
                            binding.tvHasilCekRekening.setText("Rekening Ditemukan : "+dataCIfRekening.getAccounttitle1());
                            binding.tvHasilCekRekening.setTextColor(getResources().getColor(R.color.main_green_color));
                            binding.etCifCabang.setText(dataCIfRekening.getCustomer());


                            //posisi rekening yang bener disini
                            rekeningBerubah=false;
                        }
                        else{
                            binding.tvHasilCekRekening.setVisibility(View.VISIBLE);
                            binding.tvHasilCekRekening.setText("Rekening Tidak Ditemukan");
                            binding.tvHasilCekRekening.setTextColor(getResources().getColor(R.color.red_btn_bg_color));
                        }
                    }
                    else{
                        AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        binding.tvHasilCekRekening.setVisibility(View.VISIBLE);
                        binding.tvHasilCekRekening.setText("Rekening Tidak Ditemukan");
                        binding.tvHasilCekRekening.setTextColor(getResources().getColor(R.color.red_btn_bg_color));
                    }
                    //posisi variabel ini diatas ya, kalo disini buat dev aja
                    rekeningBerubah=false;
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");

            }
        });
    }

    public void uploadFile(String base64, String fileName, int uploadCode) {
        ApiClientAdapter apiClientAdapter = new ApiClientAdapter(this);
        //  dataUser = getListUser();
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUploadFile req = new ReqUploadFile();
        //pantekan uid
        req.setFolderId(AppUtil.getIdFolderLogicalDoc());
        req.setLanguage("en");
        req.setFileB64(base64);
        req.setFileName(fileName);
        Call<ParseResponseFile> call = apiClientAdapter.getApiInterface().uploadFileLogicalDoc(req);
        call.enqueue(new Callback<ParseResponseFile>() {
            @Override
            public void onResponse(Call<ParseResponseFile> call, Response<ParseResponseFile> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {

                    if (uploadCode == UPLOAD_PKS) {
                        idFilePks = response.body().getId();
                        fileNamePks=fileName;
                        checkFileTypeThenSet(InputMasterInstansiActivity.this,idFilePks,binding.ivFotoPks,tipeFile);
                    }
                    else if (uploadCode == UPLOAD_LAIN_1) {
                        idFileLain1 = response.body().getId();
                        fileNameLain1=fileName;
                        checkFileTypeThenSet(InputMasterInstansiActivity.this,idFileLain1,binding.ivDokumenTambahan1,tipeFile);
                    }
                    else if (uploadCode == UPLOAD_LAIN_2) {
                        idFileLain2 = response.body().getId();
                        fileNameLain2=fileName;
                        checkFileTypeThenSet(InputMasterInstansiActivity.this,idFileLain2,binding.ivDokumenTambahan2,tipeFile);
                    }
                    else if (uploadCode == UPLOAD_LKP) {
                        idFileLkp = response.body().getId();
                        fileNameLkp=fileName;
                        checkFileTypeThenSet(InputMasterInstansiActivity.this,idFileLkp,binding.ivFotoLkp,tipeFile);
                    }

                    AppUtil.notifsuccess(InputMasterInstansiActivity.this, findViewById(android.R.id.content), "Upload Berhasil");
                } else {
                    AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseFile> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(InputMasterInstansiActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
            }
        });

    }

    private void checkFileTypeThenUpload(String filename, String fileNameDoc, ImageView imageView,String val_base64, int uploadCode){

        //jadi ada variabel error upload, untuk ngecek kalau dia error karena ketika sbelum milih gambar tiba tiba di back, maka gambarnya kaga usah diupload
        if(!errorUpload){
            if (tipeFile.equalsIgnoreCase("pdf")) {
                filename =fileNameDoc+".pdf";
                uploadFile(val_base64, filename, uploadCode);
            } else {
                imageView.invalidate();
                RoundedDrawable drawableDoc = (RoundedDrawable) imageView.getDrawable();
                Bitmap bitmapDoc = drawableDoc.getSourceBitmap();
                filename =fileNameDoc+".png";
                uploadFile(AppUtil.encodeImageTobase64(bitmapDoc), filename, uploadCode);
            }
        }

    }

    private void checkFileTypeThenSet(Context context, String idDok, ImageView imageView, String fileName){

        if(fileName.substring(fileName.length()-3,fileName.length()).equalsIgnoreCase("pdf")){

            if(idDok.length()<10){
                loadFileJson(idDok,imageView);
            }
            else{
                AppUtil.convertBase64ToFileWithOnClick(context,idDok,imageView,fileName);
            }

        }
        else{

            if(idDok.length()<10){
                AppUtil.setImageGlide(context,idDok,imageView);
            }
            else{
                AppUtil.convertBase64ToImage(idDok,imageView);
            }

        }
    }

    public void loadFileJson(String idFoto,ImageView imageView) {
        ApiClientAdapter apiClientAdapter=new ApiClientAdapter(InputMasterInstansiActivity.this);
        Call<ParseResponseLogicalDoc> call = apiClientAdapter.getApiInterface().getFileJson(idFoto);
        call.enqueue(new Callback<ParseResponseLogicalDoc>() {
            @Override
            public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
//                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getBinaryData()!=null){
                        AppUtil.convertBase64ToFileWithOnClick(InputMasterInstansiActivity.this,response.body().getBinaryData(),imageView,response.body().getFileName());
                    }
                    else{
                        AppUtil.notiferror(InputMasterInstansiActivity.this,findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                    }
                }
                else{
                    AppUtil.notiferror(InputMasterInstansiActivity.this,findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(InputMasterInstansiActivity.this,findViewById(android.R.id.content), "Terjadi kesalahan");
                
                t.printStackTrace();
            }
        });

    }

    @Override
    public void onSelect(String title, MGenericModel data) {
       if(title.equalsIgnoreCase(binding.tfTipePembayaran.getLabelText())){
            binding.etTipePembayaran.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfJenisInstansi.getLabelText())){
            binding.etJenisInstansi.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfMemilikiPks.getLabelText())){
            binding.etMemilikiPks.setText(data.getDESC());
            if(data.getDESC().equalsIgnoreCase("tidak")){
                binding.tfRuangLingkupPks.setVisibility(View.GONE);
                binding.tfUnitBisnisPengusul.setVisibility(View.GONE);
                binding.tfNomorPks.setVisibility(View.GONE);
                binding.tfTanggalAkhirPks.setVisibility(View.GONE);
                binding.tfTanggalMulaiPks.setVisibility(View.GONE);
                binding.tfPerpanjangPks.setVisibility(View.GONE);
                binding.rlDokumenPks.setVisibility(View.GONE);
                binding.tfStatusPks.setVisibility(View.GONE);
                binding.tvTitleUploadPks.setVisibility(View.GONE);
            }
            else{
                binding.tfRuangLingkupPks.setVisibility(View.VISIBLE);
                binding.tfUnitBisnisPengusul.setVisibility(View.VISIBLE);
                binding.tfNomorPks.setVisibility(View.VISIBLE);
                binding.tfTanggalAkhirPks.setVisibility(View.VISIBLE);
                binding.tfTanggalMulaiPks.setVisibility(View.VISIBLE);
                binding.tfPerpanjangPks.setVisibility(View.VISIBLE);
                binding.rlDokumenPks.setVisibility(View.VISIBLE);
                binding.tfStatusPks.setVisibility(View.VISIBLE);
                binding.tvTitleUploadPks.setVisibility(View.VISIBLE);
            }
        }
        else if(title.equalsIgnoreCase(binding.tfRuangLingkupPks.getLabelText())){
            binding.etRuangLingkupPks.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfUnitBisnisPengusul.getLabelText())){
            binding.etUnitBisnisPengusul.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfPerpanjangPks.getLabelText())){
            binding.etPerpanjangPksOtomatis.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfStatusPks.getLabelText())){
            binding.etStatusPks.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfJasaPengelolaan.getLabelText())){
            binding.etJasaPengelolaan.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfInstansiInduk.getLabelText())){
            binding.etInstansiInduk.setText(data.getDESC());
            val_instansi_induk=Long.parseLong(data.getID());
        }
    }

    @Override
    public void onBranchSelect(DataBranchIkurma data) {
        binding.etUnitBisnisPengusul.setText(data.getBranch_name());
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        CustomDialog.DialogBackpress(InputMasterInstansiActivity.this);
    }
}