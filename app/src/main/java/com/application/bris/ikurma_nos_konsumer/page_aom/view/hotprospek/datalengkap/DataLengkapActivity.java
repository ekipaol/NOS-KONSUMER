package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryDataLengkap;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.DataLengkapPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataLengkap;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataLengkapActivity extends AppCompatActivity implements StepperLayout.StepperListener, OnNavigationBarListener, ConfirmListener{
    @BindView(R.id.stepperlayout)
    StepperLayout stepperlayout;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_back)
    ImageView btn_back;

    private Realm realm;
    private static final String CURRENT_STEP_POSITION_KEY = "position";
    public static String cif,approved;
    public static int uid;
    public static int idAplikasi;
    public static int plafond;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String dataPribadiString;
    private DataLengkap dataLengkap;
    private int startingStepPosition;
    DataLengkapPojo dataRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_datalengkap);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        cif = getIntent().getStringExtra("cif");
        approved = getIntent().getStringExtra("approved");
        uid = appPreferences.getUid();
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        plafond = getIntent().getIntExtra("plafond", 0);



        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Lengkap");
        startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
        loadDataLengkap();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(approved.equalsIgnoreCase("no")){
                    CustomDialog.DialogBackpressSaved(DataLengkapActivity.this);
                }
                else{
                    finish();
                }

            }
        });
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void loadDataLengkap() {
        loading.setVisibility(View.VISIBLE);
        inquiryDataLengkap req = new inquiryDataLengkap(cif, String.valueOf(idAplikasi));


        //pantekan
//        req.setCif("81272");
//        req.setIdAplikasi("100997");

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryDataLengkap(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            dataPribadiString = response.body().getData().get("nasabah").toString();
                            dataLengkap = gson.fromJson(dataPribadiString, DataLengkap.class);


                            //pengecekan kalo di db masih kosong data lengkapnya, maka ambil dari realm
                            //di cek dari agama aja, soalnya mandatori kan ya, jadi kalo agama kosong, berarti dari DB belom pernah save data
                            dataRealm = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", idAplikasi).findFirst();

                            try {
                                if((dataLengkap.getAgama()==null ||dataLengkap.getAgama().isEmpty())&&dataRealm!=null){
                                    Log.d("masukpakeko",dataLengkap.getAgama());
                                    setDataFromRealm();
                                    stepperlayout.setAdapter(new DatalengkapStepAdapter(getSupportFragmentManager(), DataLengkapActivity.this, dataLengkap,approved), startingStepPosition );
                                    stepperlayout.setListener(DataLengkapActivity.this);
                                }
                                else{
                                    Log.d("masukpakeki",dataLengkap.getAgama());
                                    stepperlayout.setAdapter(new DatalengkapStepAdapter(getSupportFragmentManager(), DataLengkapActivity.this, dataLengkap,approved), startingStepPosition );
                                    stepperlayout.setListener(DataLengkapActivity.this);

                                }
                            }
                            catch (NullPointerException e){
                                Log.d("ada error di ","realm data lengkap activity load data");
                            }

//                            else if((dataLengkap.getAgama()==null ||dataLengkap.getAgama().isEmpty())&&dataRealm==null){
//                                stepperlayout.setAdapter(new DatalengkapStepAdapter(getSupportFragmentManager(), DataLengkapActivity.this, dataLengkap,approved), startingStepPosition );
//                                stepperlayout.setListener(DataLengkapActivity.this);
//
//                                //create new realm instance for the application id
//                                DataLengkapPojo newRealm=new DataLengkapPojo();
//
//                                realm.beginTransaction();
//                                    newRealm.setIdAplikasi(idAplikasi);
//                                    realm.insertOrUpdate(newRealm);
//
//
//
//                            }



                        }
                        else{
                            AppUtil.notiferror(DataLengkapActivity.this, findViewById(android.R.id.content), response.body().getMessage());
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
                        AppUtil.notiferror(DataLengkapActivity.this, findViewById(android.R.id.content), error.getMessage());
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
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(DataLengkapActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }

    public void sendData(){
        final DataLengkapPojo data = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", idAplikasi).findFirst();
        DataLengkapPojo datafix = realm.copyFromRealm(data);

        loading.setVisibility(View.VISIBLE);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendDataLengkap(datafix);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){

                            //kalau realm di delete bisa menyebabkan error di fragment pekerjaan, di comment dulu
//                            realm.beginTransaction();
//                            data.deleteFromRealm();
//                            realm.commitTransaction();
                            CustomDialog.DialogSuccess(DataLengkapActivity.this, "Success!", "Update Data Lengkap sukses", DataLengkapActivity.this);
                        }
                        else{
                            AppUtil.notiferror(DataLengkapActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataLengkapActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(DataLengkapActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void setDataFromRealm(){
         dataRealm = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", idAplikasi).findFirst();

        //ini ada yang ttp diset dari DB, karena walaupun yang belum pernah isi data lengkap, field dibawah masih ada kembaliannya dari DB
        dataLengkap.setIdInstansi(dataLengkap.getIdInstansi());
        dataLengkap.setKecPerusahaan(dataLengkap.getKecPerusahaan());
        dataLengkap.setNamaPejabat(dataLengkap.getNamaPejabat());
        dataLengkap.setKodePosPerusahaan(dataLengkap.getKodePosPerusahaan());
        dataLengkap.setKelPerusahaan(dataLengkap.getKelPerusahaan());
        dataLengkap.setSisaPlafondPerusahaan(dataLengkap.getSisaPlafondPerusahaan());
        dataLengkap.setNoHp(dataLengkap.getNoHp());
        dataLengkap.setKotaPerusahaan(dataLengkap.getKotaPerusahaan());
        dataLengkap.setBidangPekerjaan(dataLengkap.getBidangPekerjaan());
        dataLengkap.setDeskripsiPekerjaan(dataLengkap.getDeskripsiPekerjaan());
        dataLengkap.setProvPerusahaan(dataLengkap.getProvPerusahaan());
        dataLengkap.setNamaPerusahaan(dataLengkap.getNamaPerusahaan());
        dataLengkap.setAlamatPerusahaan(dataLengkap.getAlamatPerusahaan());
        dataLengkap.setAlamatId(dataLengkap.getAlamatId());

        dataLengkap.setAlamatDom(dataRealm.getAlamatDom());
        dataLengkap.setStatusKepegawaian(dataRealm.getStatusKepegawaian());
        dataLengkap.setPendidikanTerakhir(dataRealm.getPendidikanTerakhir());
        dataLengkap.setNamaAlias(dataRealm.getNamaAlias());
        dataLengkap.setKotaDom(dataRealm.getKotaDom());
        dataLengkap.setNoKtpPasangan(dataRealm.getNoKtpPasangan());
        dataLengkap.setJenisPekerjaan(dataLengkap.getJenisPekerjaan());
        dataLengkap.setStatusNikah(dataRealm.getStatusNikah());
        dataLengkap.setKodePosId(dataRealm.getKodePosId());
        dataLengkap.setProvDom(dataRealm.getProvDom());
        dataLengkap.setNamaNasabah(dataRealm.getNamaNasabah());
        dataLengkap.setNoTelpPerusahaan(dataRealm.getNoTelpPerusahaan());
        dataLengkap.setKelId(dataRealm.getKelId());
        dataLengkap.setKeteranganGelar(dataRealm.getKeteranganGelar());
        dataLengkap.setKotaId(dataRealm.getKotaId());
        dataLengkap.setNpwp(dataRealm.getNpwp());
        dataLengkap.setNoTlpn(dataRealm.getNoTlpn());
        dataLengkap.setNoSKterakhir(dataRealm.getNoSKterakhir());
        dataLengkap.setKetAgama(dataRealm.getKetAgama());
        dataLengkap.setRwDom(dataRealm.getRwDom());
        dataLengkap.setJabatan(dataRealm.getJabatan());
        dataLengkap.setKelDom(dataRealm.getKelDom());
        dataLengkap.setAgama(dataRealm.getAgama());
        dataLengkap.setRtId(dataRealm.getRtId());
        dataLengkap.setNoSKPertama(dataRealm.getNoSKPertama());
        dataLengkap.setStatusTptTinggal(dataRealm.getStatusTptTinggal());
        dataLengkap.setValiditasTempatTinggal(dataRealm.getValiditasTempatTinggal());
        dataLengkap.setNamaIbu(dataRealm.getNamaIbu());
        dataLengkap.setKecId(dataRealm.getKecId());
        dataLengkap.setNoRekomendasi(dataRealm.getNoRekomendasi());
        dataLengkap.setNamaID(dataRealm.getNamaID());
        dataLengkap.setRtDom(dataRealm.getRtDom());
        dataLengkap.setNamaPasangan(dataRealm.getNamaPasangan());
        dataLengkap.setEmail(dataRealm.getEmail());
        dataLengkap.setTelpKeluarga(dataRealm.getTelpKeluarga());
        dataLengkap.setExpId(dataRealm.getExpId());
        dataLengkap.setTgllahirPasangan(dataRealm.getTgllahirPasangan());
        dataLengkap.setNoKtp(dataRealm.getNoKtp());
        dataLengkap.setTglMulaiBekerja(dataRealm.getTglMulaiBekerja());
        dataLengkap.setKecDom(dataRealm.getKecDom());
        dataLengkap.setJlhTanggungan(dataRealm.getJlhTanggungan());
        dataLengkap.setKodePosDom(dataRealm.getKodePosDom());
        dataLengkap.setTglLahir(dataRealm.getTglLahir());
        dataLengkap.setTglVerifikasi(dataRealm.getTglVerifikasi());
        dataLengkap.setKeluarga(dataRealm.getKeluarga());
        dataLengkap.setNIP(dataRealm.getNIP());
        dataLengkap.setTptLahir(dataRealm.getTptLahir());
        dataLengkap.setRwId(dataRealm.getRwId());
        dataLengkap.setTipePendapatan(dataRealm.getTipePendapatan());
        dataLengkap.setJenkel(dataRealm.getJenkel());
        dataLengkap.setUsiaMpp(dataRealm.getUsiaMpp());
        dataLengkap.setProvId(dataRealm.getProvId());
        dataLengkap.setPembayaranGaji(dataRealm.getPembayaranGaji());

        if(dataRealm.getReferensi() !=null){
            dataLengkap.setReferensi(Integer.toString(dataRealm.getReferensi()));
        }
        else{
            dataLengkap.setReferensi(dataLengkap.getReferensi());
        }
        dataLengkap.setFID_PHOTO_RUMAH1(dataRealm.getFID_PHOTO_RUMAH1());
        dataLengkap.setFID_PHOTO_RUMAH2(dataRealm.getFID_PHOTO_RUMAH2());
        dataLengkap.setFID_PHOTO_KANTOR1(dataRealm.getFID_PHOTO_KANTOR1());
        dataLengkap.setFID_PHOTO_KANTOR2(dataRealm.getFID_PHOTO_KANTOR2());






        //biar isi lama menetap bukan tulisa "null" tapi angka 0 kalau belum ada realm yang tersimpan
        if(dataRealm.getLamaMenetap()==null){
            dataLengkap.setLamaMenetap("0");
        }
        else{
            dataLengkap.setLamaMenetap(dataRealm.getLamaMenetap());
        }


    }

    @Override
    public void onBackPressed() {
        if(approved.equalsIgnoreCase("no")){
            CustomDialog.DialogBackpressSaved(DataLengkapActivity.this);
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    public void onCompleted(View completeButton) {

        if(approved.equalsIgnoreCase("no")){
            sendData();
        }
        else{
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
        stepperlayout.setNextButtonVerificationFailed(!enabled);
        stepperlayout.setCompleteButtonVerificationFailed(!enabled);
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
