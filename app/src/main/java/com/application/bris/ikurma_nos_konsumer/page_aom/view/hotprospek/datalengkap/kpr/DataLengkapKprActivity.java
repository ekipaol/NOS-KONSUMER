package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap.kpr;


import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.model.request.flpp.ReqDataSikasep;
import com.application.bris.ikurma_nos_konsumer.api.model.request.flpp.ReqRumahFlpp;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryDataLengkap;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.DataLengkapPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.BlokRumahFlpp;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataLengkap;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataRumahFlpp;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataSikasep;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DeskripsiPekerjaan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.JenisPekerjaan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListBidangPekerjaan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListJenisKPR;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap.OnNavigationBarListener;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class DataLengkapKprActivity extends AppCompatActivity implements StepperLayout.StepperListener, OnNavigationBarListener, ConfirmListener{
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
    private List<MListBidangPekerjaan> listBidangPekerjaan;
    private List<MListJenisKPR> listJenisKPR;
    private List<JenisPekerjaan> listJenisPekerjaan;
    private List<DeskripsiPekerjaan> listDeskripsiPekerjaan;
    private DataSikasep dataSikasep;
    private List<DataRumahFlpp> listDataRumahFlpp;
    private List<BlokRumahFlpp> listBlokRumah;
    String idProgram="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_datalengkap);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        approved = getIntent().getStringExtra("approved");
        uid = appPreferences.getUid();


        //real data
        cif = getIntent().getStringExtra("cif");
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);

        if(getIntent().hasExtra("idProgram")){
            idProgram=getIntent().getStringExtra("idProgram");
        }

        //pantekan

//        cif = "82118";
//        idAplikasi = 102264;

//        Toast.makeText(this, "pangtekan id aplikasi", Toast.LENGTH_SHORT).show();


        plafond = getIntent().getIntExtra("plafond", 0);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Lengkap");
        startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;

        loadBidangPekerjaan();


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(approved.equalsIgnoreCase("no")){
                    CustomDialog.DialogBackpressSaved(DataLengkapKprActivity.this);
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



        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryDataLengkapKpr(req);
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
//                                    Log.d("masukpakeko",dataLengkap.getAgama());
                                    setDataFromRealm();

                                    if(idProgram.equalsIgnoreCase("222")){
                                        //lanjut ke sikasep untuk dapetin ktp debitur, agar bisa dapet list rumah flpp
                                        getDataSikasep();
                                    }
                                    else{
                                        //langsung sikat
                                        stepperlayout.setAdapter(new DataLengkapKprStepAdapter(getSupportFragmentManager(), DataLengkapKprActivity.this, dataLengkap,approved,listBidangPekerjaan,listJenisKPR, listJenisPekerjaan, listDataRumahFlpp), startingStepPosition );
                                        stepperlayout.setListener(DataLengkapKprActivity.this);
                                    }


                                }
                                else{

                                    if(idProgram.equalsIgnoreCase("222")){
                                        //lanjut ke sikasep untuk dapetin ktp debitur, agar bisa dapet list rumah flpp
                                        getDataSikasep();
                                    }
                                    else{

                                        //langsung sikat
                                        stepperlayout.setAdapter(new DataLengkapKprStepAdapter(getSupportFragmentManager(), DataLengkapKprActivity.this, dataLengkap,approved,listBidangPekerjaan,listJenisKPR, listJenisPekerjaan, listDataRumahFlpp), startingStepPosition );
                                        stepperlayout.setListener(DataLengkapKprActivity.this);
                                    }

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
                            AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
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
                        AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), error.getMessage());
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
                AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }

    private void loadBidangPekerjaan() {
        loading.setVisibility(View.VISIBLE);
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listBidangPekerjaan(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                try {
                    if (response.isSuccessful()) {
                        loading.setVisibility(View.GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            //setelah berhasil maka load jenis kpr
                            loadJenisKPR();




                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<MListBidangPekerjaan>>() {
                            }.getType();

                            listBidangPekerjaan = gson.fromJson(listDataString, type);
                        } else {
//                            finish();
                            AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(DataLengkapKprActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void loadJenisKPR() {
        loading.setVisibility(View.VISIBLE);
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listJenisKPR(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                try {
                    if (response.isSuccessful()) {
                        loading.setVisibility(View.GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            //setelah berhasil maka load list pekerjaan

                            //seleksi apakah flpp apa bukan, kalau FLPP, list jenis pekerjaan flpp
                            if(idProgram.equalsIgnoreCase("222")){
                                loadListPekerjaanFlpp();
                            }
                            else{
                                loadListPekerjaan();
                            }


                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<MListJenisKPR>>() {
                            }.getType();

                            listJenisKPR = gson.fromJson(listDataString, type);

                        } else {
//                            finish();
                            AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(DataLengkapKprActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void getDataSikasep() {
        loading.setVisibility(View.VISIBLE);
        ReqDataSikasep req = new ReqDataSikasep();
        req.setKtpDebitur(dataLengkap.getNoKtp());
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().getDataSikasep(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        loading.setVisibility(GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<DataSikasep>() {}.getType();

                            dataSikasep = gson.fromJson(listDataString, type);

                            //set npwp biar tinggal crot aja di halaman data pribadi
                            dataLengkap.setNpwpSikasep(dataSikasep.getNpwpDebitur());

                            //setelah dapaet kode perumahan, baru load rumah
                            loadRumahFlpp();



                        } else {
//                            finish();
                            AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        loading.setVisibility(GONE);
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(GONE);
//                finish();
                t.printStackTrace();
                Toast.makeText(DataLengkapKprActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void loadRumahFlpp() {
        loading.setVisibility(View.VISIBLE);
        ReqRumahFlpp req=new ReqRumahFlpp();
        req.setId_lokasi(dataSikasep.getPerumahanId());
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listRumahFlpp(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                try {
                    if (response.isSuccessful()) {
                        loading.setVisibility(View.GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {


                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<DataRumahFlpp>>() {
                            }.getType();

                            listDataRumahFlpp = gson.fromJson(listDataString, type);

                            //setelah dapet list rumahnya baru panggil step adapter untuk mulai oper semua datanya

//                            stepperlayout.setAdapter(new DataLengkapKprStepAdapter(getSupportFragmentManager(), DataLengkapKprActivity.this, dataLengkap,approved,listBidangPekerjaan,listJenisKPR, listJenisPekerjaan, listDataRumahFlpp), startingStepPosition );
//                            stepperlayout.setListener(DataLengkapKprActivity.this);

                            loadNamaBlokFlpp();

                        } else {
//                            finish();
                            AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(DataLengkapKprActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void loadNamaBlokFlpp() {
        loading.setVisibility(View.VISIBLE);
        ReqRumahFlpp req=new ReqRumahFlpp();
        req.setId_lokasi(dataSikasep.getPerumahanId());
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().getBlokByIdLokasi(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                try {
                    if (response.isSuccessful()) {
                        loading.setVisibility(View.GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {


                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<BlokRumahFlpp>>() {
                            }.getType();

                            listBlokRumah = gson.fromJson(listDataString, type);

                            for (int i = 0; i <listBlokRumah.size() ; i++) {
                                for (int j = 0; j <listDataRumahFlpp.size() ; j++) {
                                    if(listBlokRumah.get(i).getId().equalsIgnoreCase(listDataRumahFlpp.get(j).getBlok())){
                                        listDataRumahFlpp.get(j).setNamaBlok(listBlokRumah.get(i).getBlok());
                                    }
                                }
                            }

                            //setelah dapet list rumah dan sudah diinjeck dengan nama blok baru panggil step adapter untuk mulai oper semua datanya

                            stepperlayout.setAdapter(new DataLengkapKprStepAdapter(getSupportFragmentManager(), DataLengkapKprActivity.this, dataLengkap,approved,listBidangPekerjaan,listJenisKPR, listJenisPekerjaan, listDataRumahFlpp), startingStepPosition );
                            stepperlayout.setListener(DataLengkapKprActivity.this);

                        } else {
//                            finish();
                            AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(DataLengkapKprActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void loadListPekerjaan() {
        loading.setVisibility(View.VISIBLE);



        Call<ParseResponse> call = apiClientAdapter.getApiInterface().listJenisPekerjaan(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){


                      //lanjut ke data lengkap

                                loadDataLengkap();



                            //WAH WAH PEMIRSA, PARAH INI PEMIRSA, PARAH!
                            //RIBET PEMIRSA CARA PARSING JSON BUAT JENIS PEKERJAAN INI PEMIRSA
                            //KARENA OBJEK JSONNYA BERCABANG BAGAIKAN KANTOR BRI SYARIAH DENGAN 200 CABANG DI SELURUH INDONESIA

                            //JADI DIA BENTUKNYA JSON OBJEK, KEPALANYA NAMANYA dtJenisPekerjaan
                            //di dalam dtJenisPekerjaan ini ada beberapa JSON Array yang masing masing array memiliki 3 kepala yaitu :
                            //jenisPekerjaan , jenisPekerjaanDesc, dan detilPekerjaan

                            //NAH TERNYATA PEMIRSA, si detilPekerjaan INI JUGA BENTUKNYA JSON ARRAY PEMIRSA, DAN MASING MASING ARRAYNYA
                            //MEMILIKI 2 KEPALA, YAITU detilPekerjaan dan detilPekerjaanDesc

                            //GIMANA CARA PARSINGNYA PEMIRSA? HMM??
                            //SUSAH PEMIRSA!

                            //TAPI TENANG SAJA PEMIRSA, KALAU ANDA MEMBACA PESAN INI ARTINYA PARSINGAN YANG DIBAWAH INI SUDAH BERHASIL
                            //BERIKUT PENJELASANNYA SECARA DETIL, SUPAYA KALAU KETEMU KASUS SEPERTI INI LAGI, KITA UDAH CAPABLE

                            //TAHAP PERTAMA, SEPERTI BIASA, AMBIL DULU KEPALA YANG PALING PERTAMA YAITU dtJenisPekerjaan
                            //nah karena isi dari dtJenisPekerjaan ini ada yang beranak lagi, yaitu detilPekerjaan
                            //maka didalam model penampung (disini namanya JenisPekerjaan) dia memiliki atribut dalam bentuk list objek
                            //list objek disini namanya DeskripsiPekerjaan

                            Gson gson = new Gson();
                            String jenisPekerjaanString = response.body().getData().get("dtJenisPekerjaan").toString(); //masukkan dalam string
                            String   deskripsiPekerjaanString;
                            Type type = new TypeToken<List<JenisPekerjaan>>() {}.getType();//kalau bentuknya list, harus dibuat typenya
                            Type type2 = new TypeToken<List<DeskripsiPekerjaan>>() {}.getType();
                            listJenisPekerjaan = gson.fromJson(jenisPekerjaanString, type);//tampung jenisPekerjaan dalam objek

                            //nah posisi saat ini objek jenisPekerjaans sudah secara otomatis convert data string untuk atribut :
                            //jenis pekerjaan dan jenis pekerjaan desc
                            //lalu bagaimana dengan atribut detilPekerjaannya pak? kan ini bentuknya objek? apakah bisa otomatis diconvert?

                            //OH TIDAK SECEPAT ITU PEMIRSA!!
                            //kondisi saat ini saya tidak tahu caranya konversi otomatis GSON untuk json yang bercabang
                            //tapi kita tidak menyerah, ada cara untuk mapping detilPekerjaan ini kedalam objek jenisPekerjaans kita

                            //Metodenya, kita menggunakan perulangan sejumlah size objek jenisPekerjaans
                            //lalu kita masukkan satu persatu dari setiap indeks, data detil pekerjaannya kedalam masing2 objek jenisPekerjaan
                            for (int i = 0; i < listJenisPekerjaan.size() ; i++) {

                                //lalu ambil data detilPekerjaan untuk indeks "i"
                                deskripsiPekerjaanString=response.body().getData().get("dtJenisPekerjaan").getAsJsonArray().get(i).getAsJsonObject().get("detilPekerjaan").toString();

                                //data detilPekerjaan untuk indeks i, kita masukkan dalam objek
                                //INGAT!! DATA detilPekerjaan ini bentuknya array juga, jadi ditampung dalam objek list
                                //objek listnya kita beri nama deskripsiPekerjaans
                                listDeskripsiPekerjaan =gson.fromJson(deskripsiPekerjaanString,type2);

                                //setelah ditampung, tinggal di set saja listnya kedalam objek jenisPekerjaan[i]
                                //jangan lupa buat setter dan getter untuk listnya juga
                                listJenisPekerjaan.get(i).setDeskripsiPekerjaans(listDeskripsiPekerjaan);

                                //DAN SELESAI PEMIRSA, SEKIAN CARA PARSINg JSON BERCABANG, DAN MENJADIKANNYA OBJEK
                                //sekian dan terima kasih


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
                            AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
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
                        AppUtil.notiferror(DataLengkapKprActivity.this,findViewById(android.R.id.content), error.getMessage());
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
                AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      finish();
                    }
                }, 2000);
            }
        });
    }

    private void loadListPekerjaanFlpp() {
        loading.setVisibility(View.VISIBLE);



        Call<ParseResponse> call = apiClientAdapter.getApiInterface().listJenisPekerjaanFlpp(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){


                            //lanjut ke data lengkap

                            loadDataLengkap();


                            Gson gson = new Gson();
                            String jenisPekerjaanString = response.body().getData().get("dtJenisPekerjaan").toString();
                            Type type = new TypeToken<List<JenisPekerjaan>>() {}.getType();

                            listJenisPekerjaan = gson.fromJson(jenisPekerjaanString, type);


                        }
                        else{
                            AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
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
                        AppUtil.notiferror(DataLengkapKprActivity.this,findViewById(android.R.id.content), error.getMessage());
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
                AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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
        Call<ParseResponse> call;

        //seleksi apakah kpr biasa atau FLPP
        if(idProgram.equalsIgnoreCase("222")){
            call = apiClientAdapter.getApiInterface().sendDataLengkapFlpp(datafix);
        }
        else{
            call = apiClientAdapter.getApiInterface().sendDataLengkapKpr(datafix);
        }

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
                            CustomDialog.DialogSuccess(DataLengkapKprActivity.this, "Success!", "Update Data Lengkap sukses", DataLengkapKprActivity.this);
                        }
                        else{
                            AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(DataLengkapKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void setDataFromRealm(){
        dataRealm = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", idAplikasi).findFirst();

        //ini ada yang ttp diset dari DB, karena walaupun yang belum pernah isi data lengkap, field dibawah masih ada kembaliannya dari DB
        dataLengkap.setIdInstansi(dataLengkap.getIdInstansi());
        dataLengkap.setNoHp(dataLengkap.getNoHp());

        dataLengkap.setAlamatId(dataLengkap.getAlamatId());
        dataLengkap.setNamaPihakKetiga(dataLengkap.getNamaPihakKetiga());
        dataLengkap.setNamaProject(dataLengkap.getNamaProject());
        dataLengkap.setJenisKPR(dataLengkap.getJenisKPR());

        dataLengkap.setBidangPekerjaan(dataRealm.getBidangPekerjaan());
        dataLengkap.setKecPerusahaan(dataRealm.getKecPerusahaan());
        dataLengkap.setKodePosPerusahaan(dataRealm.getKodePosPerusahaan());
        dataLengkap.setKelPerusahaan(dataRealm.getKelPerusahaan());
        dataLengkap.setKotaPerusahaan(dataRealm.getKotaPerusahaan());
        dataLengkap.setDeskripsiPekerjaan(dataRealm.getDeskripsiPekerjaan());
        dataLengkap.setProvPerusahaan(dataRealm.getProvPerusahaan());
        dataLengkap.setNamaPerusahaan(dataRealm.getNamaPerusahaan());
        dataLengkap.setAlamatPerusahaan(dataRealm.getAlamatPerusahaan());
        dataLengkap.setAlamatDom(dataRealm.getAlamatDom());
        dataLengkap.setStatusKepegawaian(dataRealm.getStatusKepegawaian());
        dataLengkap.setPendidikanTerakhir(dataRealm.getPendidikanTerakhir());
        dataLengkap.setNamaAlias(dataRealm.getNamaAlias());
        dataLengkap.setKotaDom(dataRealm.getKotaDom());
        dataLengkap.setNoKtpPasangan(dataRealm.getNoKtpPasangan());
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

        dataLengkap.setDetilJenisPekerjaan(dataRealm.getDetilJenisPekerjaan());
        dataLengkap.setJenisPekerjaan(dataRealm.getJenisPekerjaan());


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
            CustomDialog.DialogBackpressSaved(DataLengkapKprActivity.this);
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
