package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_nasabah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateDataInstansiDapen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateDataNasabah;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UploadImage;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.FlagAplikasiPojo;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityDataNasabahPrapenBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataInstansiDapen;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataNasabahPrapen;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap.OnNavigationBarListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.DetilAplikasiActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.general.ListAplikasiActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataNasabahPrapenActivity extends AppCompatActivity implements StepperLayout.StepperListener, OnNavigationBarListener, ConfirmListener {

    private Realm realm;
    private static final String CURRENT_STEP_POSITION_KEY = "position";
    private ActivityDataNasabahPrapenBinding binding;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String dataPribadiString;
    private DataNasabahPrapen dataNasabah=new DataNasabahPrapen();
    private UploadImage dataFotoKtp=new UploadImage();
    private UploadImage dataFotoIdeb=new UploadImage();
    private DataInstansiDapen dataInstansi;
    private int startingStepPosition;
    public static Long idAplikasi;
    DataNasabahPrapen dataRealm;
    private String statusId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataNasabahPrapenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);

        if(getIntent().hasExtra("idAplikasi")){
            idAplikasi=Long.parseLong(getIntent().getStringExtra("idAplikasi"));
        }

        if(getIntent().hasExtra("statusId")){
            statusId=getIntent().getStringExtra("statusId");
        }

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Nasabah");
        startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
//        loadDataLengkap();

            loadDataInstansiDapen();



        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    CustomDialog.DialogBackpressSaved(DataNasabahPrapenActivity.this);
            }
        });
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void loadDataNasabah() {
        binding.loadingCircle.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req = new ReqUidIdAplikasi();
        req.setApplicationId(idAplikasi);
        req.setUID(String.valueOf(appPreferences.getUid()));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryDataNasabah(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingCircle.progressbarLoading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            dataPribadiString = response.body().getData().get("Dukcapil").getAsJsonArray().get(0).getAsJsonObject().toString();
                            String dataFotoKtpString = response.body().getData().get("DataDukcapilIMG2").getAsJsonArray().get(0).getAsJsonObject().toString();
                            String dataFotoIdebString;
                            try{
                                 dataFotoIdebString = response.body().getData().get("DataDukcapilSign").getAsJsonObject().toString();
                            }
                            catch (Exception e){
                                 dataFotoIdebString="{\"Img\": \"\",\n" +
                                         "      \"File_Name\": \"\"}";
                            }

                            dataNasabah = gson.fromJson(dataPribadiString, DataNasabahPrapen.class);
                            dataFotoKtp= gson.fromJson(dataFotoKtpString, UploadImage.class);
                            dataFotoIdeb= gson.fromJson(dataFotoIdebString, UploadImage.class);

                            dataNasabah.setImgKtp(dataFotoKtp.getImg());
                            dataNasabah.setFile_Name_Ktp(dataFotoKtp.getFile_Name());


                            dataNasabah.setImgIdeb(dataFotoIdeb.getImg());
                            dataNasabah.setFile_Name_Ideb(dataFotoIdeb.getFile_Name());

                            dataNasabah.setStatusId(statusId);


                            //pengecekan kalo di db masih kosong data lengkapnya, maka ambil dari realm
                            //di cek dari agama aja, soalnya mandatori kan ya, jadi kalo agama kosong, berarti dari DB belom pernah save data
                            dataRealm = realm.where(DataNasabahPrapen.class).equalTo("applicationId", idAplikasi).findFirst();


                            try {

                                if((dataNasabah.getAlamat()==null ||dataNasabah.getAlamat().isEmpty())&&dataRealm!=null){

                                    dataRealm.setStatusId(statusId);
//                                    setDataFromRealm();
                                    binding.stepperlayout.setAdapter(new DataNasabahStepAdapter(getSupportFragmentManager(), DataNasabahPrapenActivity.this, dataRealm,dataInstansi), startingStepPosition );
                                    binding.stepperlayout.setListener(DataNasabahPrapenActivity.this);
                                }
                                else{
//                                    Log.d("masukpakeki",dataLengkap.getAgama());
                                    binding.stepperlayout.setAdapter(new DataNasabahStepAdapter(getSupportFragmentManager(), DataNasabahPrapenActivity.this, dataNasabah,dataInstansi), startingStepPosition );
                                    binding.stepperlayout.setListener(DataNasabahPrapenActivity.this);

                                }

                            }
                            catch (NullPointerException e){
                                e.printStackTrace();
                                Log.d("ada error di ","realm data lengkap activity load data"+e.getMessage());
                            }

                        }
                        else{
                            AppUtil.notiferror(DataNasabahPrapenActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 2000);
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataNasabahPrapenActivity.this, findViewById(android.R.id.content), error.getMessage());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 2000);
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingCircle.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataNasabahPrapenActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                t.printStackTrace();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }

    private void loadDataInstansiDapen() {
        binding.loadingCircle.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req = new ReqUidIdAplikasi();
        req.setApplicationId(idAplikasi);
        req.setUID(String.valueOf(appPreferences.getUid()));
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().inquiryDataInstansiDapen(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loadingCircle.progressbarLoading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            try{
                                String dataInstansiString = response.body().getData().get(0).toString();
                                dataInstansi = gson.fromJson(dataInstansiString, DataInstansiDapen.class);
                                dataInstansi.setStatusId(statusId);
                            }
                            catch (Exception e){
                            AppUtil.logSecure("error load instansi",e.getMessage());
                            }

                            loadDataNasabah();
                        }
                        else{
                            AppUtil.notiferror(DataNasabahPrapenActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 2000);
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataNasabahPrapenActivity.this, findViewById(android.R.id.content), error.getMessage());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 2000);
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loadingCircle.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataNasabahPrapenActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }

    public void sendDataNasabah(){
        final DataNasabahPrapen data = realm.where(DataNasabahPrapen.class).equalTo("applicationId", idAplikasi).findFirst();
        DataNasabahPrapen datafix = realm.copyFromRealm(data);
        UploadImage dataFotoKtp = new UploadImage();
        UploadImage dataFotoIdeb = new UploadImage();


        dataFotoKtp.setImg(datafix.getImgKtp());
        dataFotoKtp.setFile_Name(datafix.getFile_Name_Ktp());
        dataFotoIdeb.setImg(datafix.getImgIdeb());
        dataFotoIdeb.setFile_Name(datafix.getFile_Name_Ideb());

        UpdateDataNasabah dataNasabah=new UpdateDataNasabah();
        dataNasabah.setDataNasabah(datafix);
        // TODO: 12/11/21 data foto jangan lupa di set di fragment data pribadi
        dataNasabah.setDataFoto(dataFotoKtp);
        dataNasabah.setDataDukcapilSign(dataFotoIdeb);

        binding.loadingCircle.progressbarLoading.setVisibility(View.VISIBLE);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updateDataNasabah(dataNasabah);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingCircle.progressbarLoading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){

                            sendDataInstansiDapen();
                        }
                        else{
                            AppUtil.notiferror(DataNasabahPrapenActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataNasabahPrapenActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingCircle.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataNasabahPrapenActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                t.printStackTrace();
            }
        });
    }

    public void sendDataInstansiDapen(){
        final DataInstansiDapen data = realm.where(DataInstansiDapen.class).equalTo("applicationId", idAplikasi).findFirst();
        DataInstansiDapen datafix = realm.copyFromRealm(data);

        UpdateDataInstansiDapen updateDataInstansiDapen = new UpdateDataInstansiDapen();
        updateDataInstansiDapen.setApplicationId(idAplikasi);
        updateDataInstansiDapen.setUID(String.valueOf(appPreferences.getUid()));
        updateDataInstansiDapen.setDataInstansi(datafix);


        binding.loadingCircle.progressbarLoading.setVisibility(View.VISIBLE);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updateDataInstansiDapen(updateDataInstansiDapen);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingCircle.progressbarLoading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){

                            //kalau realm di delete bisa menyebabkan error di fragment pekerjaan, di comment dulu
//                            realm.beginTransaction();
//                            data.deleteFromRealm();
//                            realm.commitTransaction();
                            CustomDialog.DialogSuccess(DataNasabahPrapenActivity.this, "Success!", "Update Data Nasabah sukses", DataNasabahPrapenActivity.this);

                            //update Flagging
                            FlagAplikasiPojo dataFlag= realm.where(FlagAplikasiPojo.class).equalTo("idAplikasi", idAplikasi).findFirst();
                            if(!realm.isInTransaction()){
                                realm.beginTransaction();
                            }
                            dataFlag.setFlagD1DataNasabah(true);
                            realm.insertOrUpdate(dataFlag);
                            realm.close();

                        }
                        else{
                            AppUtil.notiferror(DataNasabahPrapenActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataNasabahPrapenActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingCircle.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(DataNasabahPrapenActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void setDataFromRealm(){
//        dataRealm = realm.where(DataNasabahPrapen.class).equalTo("applicationId", idAplikasi).findFirst();
//
//        //ini ada yang ttp diset dari DB, karena walaupun yang belum pernah isi data lengkap, field dibawah masih ada kembaliannya dari DB
//        dataNasabah.setIdInstansi(dataNasabah.getIdInstansi());
//        dataNasabah.setKecPerusahaan(dataNasabah.getKecPerusahaan());
//        dataNasabah.setNamaPejabat(dataNasabah.getNamaPejabat());
//        dataNasabah.setKodePosPerusahaan(dataNasabah.getKodePosPerusahaan());
//        dataNasabah.setKelPerusahaan(dataNasabah.getKelPerusahaan());
//        dataNasabah.setSisaPlafondPerusahaan(dataNasabah.getSisaPlafondPerusahaan());
//        dataNasabah.setNoHp(dataNasabah.getNoHp());
//        dataNasabah.setKotaPerusahaan(dataNasabah.getKotaPerusahaan());
//        dataNasabah.setBidangPekerjaan(dataNasabah.getBidangPekerjaan());
//        dataNasabah.setDeskripsiPekerjaan(dataNasabah.getDeskripsiPekerjaan());
//        dataNasabah.setProvPerusahaan(dataNasabah.getProvPerusahaan());
//        dataNasabah.setNamaPerusahaan(dataNasabah.getNamaPerusahaan());
//        dataNasabah.setAlamatPerusahaan(dataNasabah.getAlamatPerusahaan());
//        dataNasabah.setAlamatId(dataNasabah.getAlamatId());
//
//        dataNasabah.setAlamatDom(dataRealm.getAlamatDom());
//        dataNasabah.setStatusKepegawaian(dataRealm.getStatusKepegawaian());
//        dataNasabah.setPendidikanTerakhir(dataRealm.getPendidikanTerakhir());
//        dataNasabah.setNamaAlias(dataRealm.getNamaAlias());
//        dataNasabah.setKotaDom(dataRealm.getKotaDom());
//        dataNasabah.setNoKtpPasangan(dataRealm.getNoKtpPasangan());
//        dataNasabah.setJenisPekerjaan(dataNasabah.getJenisPekerjaan());
//        dataNasabah.setStatusNikah(dataRealm.getStatusNikah());
//        dataNasabah.setKodePosId(dataRealm.getKodePosId());
//        dataNasabah.setProvDom(dataRealm.getProvDom());
//        dataNasabah.setNamaNasabah(dataRealm.getNamaNasabah());
//        dataNasabah.setNoTelpPerusahaan(dataRealm.getNoTelpPerusahaan());
//        dataNasabah.setKelId(dataRealm.getKelId());
//        dataNasabah.setKeteranganGelar(dataRealm.getKeteranganGelar());
//        dataNasabah.setKotaId(dataRealm.getKotaId());
//        dataNasabah.setNpwp(dataRealm.getNpwp());
//        dataNasabah.setNoTlpn(dataRealm.getNoTlpn());
//        dataNasabah.setNoSKterakhir(dataRealm.getNoSKterakhir());
//        dataNasabah.setKetAgama(dataRealm.getKetAgama());
//        dataNasabah.setRwDom(dataRealm.getRwDom());
//        dataNasabah.setJabatan(dataRealm.getJabatan());
//        dataNasabah.setKelDom(dataRealm.getKelDom());
//        dataNasabah.setAgama(dataRealm.getAgama());
//        dataNasabah.setRtId(dataRealm.getRtId());
//        dataNasabah.setNoSKPertama(dataRealm.getNoSKPertama());
//        dataNasabah.setStatusTptTinggal(dataRealm.getStatusTptTinggal());
//        dataNasabah.setValiditasTempatTinggal(dataRealm.getValiditasTempatTinggal());
//        dataNasabah.setNamaIbu(dataRealm.getNamaIbu());
//        dataNasabah.setKecId(dataRealm.getKecId());
//        dataNasabah.setNoRekomendasi(dataRealm.getNoRekomendasi());
//        dataNasabah.setNamaID(dataRealm.getNamaID());
//        dataNasabah.setRtDom(dataRealm.getRtDom());
//        dataNasabah.setNamaPasangan(dataRealm.getNamaPasangan());
//        dataNasabah.setEmail(dataRealm.getEmail());
//        dataNasabah.setTelpKeluarga(dataRealm.getTelpKeluarga());
//        dataNasabah.setExpId(dataRealm.getExpId());
//        dataNasabah.setTgllahirPasangan(dataRealm.getTgllahirPasangan());
//        dataNasabah.setNoKtp(dataRealm.getNoKtp());
//        dataNasabah.setTglMulaiBekerja(dataRealm.getTglMulaiBekerja());
//        dataNasabah.setKecDom(dataRealm.getKecDom());
//        dataNasabah.setJlhTanggungan(dataRealm.getJlhTanggungan());
//        dataNasabah.setKodePosDom(dataRealm.getKodePosDom());
//        dataNasabah.setTglLahir(dataRealm.getTglLahir());
//        dataNasabah.setTglVerifikasi(dataRealm.getTglVerifikasi());
//        dataNasabah.setKeluarga(dataRealm.getKeluarga());
//        dataNasabah.setNIP(dataRealm.getNIP());
//        dataNasabah.setTptLahir(dataRealm.getTptLahir());
//        dataNasabah.setRwId(dataRealm.getRwId());
//        dataNasabah.setTipePendapatan(dataRealm.getTipePendapatan());
//        dataNasabah.setJenkel(dataRealm.getJenkel());
//        dataNasabah.setUsiaMpp(dataRealm.getUsiaMpp());
//        dataNasabah.setProvId(dataRealm.getProvId());
//        dataNasabah.setPembayaranGaji(dataRealm.getPembayaranGaji());
//
//        if(dataRealm.getReferensi() !=null){
//            dataNasabah.setReferensi(Integer.toString(dataRealm.getReferensi()));
//        }
//        else{
//            dataNasabah.setReferensi(dataNasabah.getReferensi());
//        }
//        dataNasabah.setFID_PHOTO_RUMAH1(dataRealm.getFID_PHOTO_RUMAH1());
//        dataNasabah.setFID_PHOTO_RUMAH2(dataRealm.getFID_PHOTO_RUMAH2());
//        dataNasabah.setFID_PHOTO_KANTOR1(dataRealm.getFID_PHOTO_KANTOR1());
//        dataNasabah.setFID_PHOTO_KANTOR2(dataRealm.getFID_PHOTO_KANTOR2());
//
//
//
//
//
//
//        //biar isi lama menetap bukan tulisa "null" tapi angka 0 kalau belum ada realm yang tersimpan
//        if(dataRealm.getLamaMenetap()==null){
//            dataNasabah.setLamaMenetap("0");
//        }
//        else{
//            dataNasabah.setLamaMenetap(dataRealm.getLamaMenetap());
//        }


    }

    @Override
    public void onBackPressed() {
            CustomDialog.DialogBackpressSaved(DataNasabahPrapenActivity.this);

    }

    @Override
    public void onCompleted(View completeButton) {
//        Intent intent=new Intent(DataNasabahPrapenActivity.this,DataMarketingActivity.class);
//        startActivity(intent);
//        finish();


        if(statusId.equalsIgnoreCase("d.1")){
            sendDataNasabah();
        }
        else{
//            Intent intent = new Intent(DataNasabahPrapenActivity.this, DetilAplikasiActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);

            finish();
        }
    }

    @Override
    public void onError(VerificationError verificationError) {
    }

    @Override
    public void onStepSelected(int newStepPosition) {
    }

    @Override
    public void onReturn() {
        finish();
    }

    @Override
    public void onChangeEndButtonsEnabled(boolean enabled) {
        binding.stepperlayout.setNextButtonVerificationFailed(!enabled);
        binding.stepperlayout.setCompleteButtonVerificationFailed(!enabled);
    }

    @Override
    public void success(boolean val) {
        if(val){
            finish();
        }
    }

    @Override
    public void confirm(boolean val) {

    }

}