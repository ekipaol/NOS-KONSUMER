package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;

import android.content.Intent;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.InsertAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqAgunanData;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.AgunanTanahBangunanPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanTanahBangunan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.ListJenisBangunan;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap.OnNavigationBarListener;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgunanTanahBangunanInputActivity extends AppCompatActivity implements StepperLayout.StepperListener, OnNavigationBarListener, ConfirmListener {

    @BindView(R.id.stepperlayout)
    StepperLayout stepperlayout;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_back)
    ImageView btn_back;

    private static final String CURRENT_STEP_POSITION_KEY = "position";
    public static String cif, idAplikasi, loan_type, tp_produk, idAgunan,uuid,sumberKlik, jenisTombol,jenisAo,idProgram;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private InsertAgunan agunan;
    private int startingStepPosition;
    private AgunanTanahBangunan dataAgunan;
    private String id_agunan_response;
    AgunanTanahBangunanPojo dataRealm;

    private int flagAgunan;

    public static String UUIDR;
    private Realm realm;
    private boolean enableEditText=true;
    private List<ListJenisBangunan> listJenisBangunan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_datalengkap);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(AgunanTanahBangunanInputActivity.this);
        appPreferences = new AppPreferences(this);
        backgroundStatusBar();
        AppUtil.toolbarRegular(AgunanTanahBangunanInputActivity.this, "Agunan Tanah Bangunan");




        agunan = new InsertAgunan();
        dataAgunan = new AgunanTanahBangunan();

        if (getIntent().hasExtra("cif")) {
            cif = getIntent().getStringExtra("cif");
        }

        if (getIntent().hasExtra("idProgram")) {
            idProgram = getIntent().getStringExtra("idProgram");
        }
        else{
            idProgram="";
        }

        UUIDR = UUID.randomUUID().toString();
        uuid=getIntent().getStringExtra("uuid");
        flagAgunan=getIntent().getIntExtra("flagAgunan",0);

        if(uuid==null||uuid.isEmpty()){
            uuid=UUIDR;
        }

        idAplikasi = getIntent().getStringExtra("idAplikasi");
        loan_type = getIntent().getStringExtra("loan_type");
        tp_produk = getIntent().getStringExtra("tp_produk");
        idAgunan = getIntent().getStringExtra("idAgunan");
        sumberKlik=getIntent().getStringExtra("newInput");
        jenisTombol =getIntent().getStringExtra("jenisTombol");
        jenisAo =getIntent().getStringExtra("jenisAo");

//        Log.d("jenisAO",jenisAo);


        if(jenisTombol!=null&&jenisTombol.equalsIgnoreCase("lihat")){
            enableEditText=false;
        }
        else{
            enableEditText=true;
        }
//
//        if(jenisTombol!=null&&jenisTombol.equalsIgnoreCase("lihat")){
//            AppUtil.disableEditTexts();
//        }


        //ini kalau dari service sudah dapat data agunan
        if (idAgunan!=null&&!idAgunan.equalsIgnoreCase("0")) {
          loadJenisBangunan();
        }

        //ini kalau input agunan tanh bangunan baru (pertama kali)
        else if(idAgunan!=null&&idAgunan.equalsIgnoreCase("0")&&sumberKlik!=null&&sumberKlik.equalsIgnoreCase("true")){

                        startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
//            stepperlayout.setAdapter(new StepAdapterAgunan(getSupportFragmentManager(), AgunanTanahBangunanInputActivity.this, dataAgunan, idAgunan, loan_type, tp_produk,enableEditText,idProgram), startingStepPosition );
//            stepperlayout.setListener(AgunanTanahBangunanInputActivity.this);

            //di load jenis bangunan udah ada stepadapter.setadapter juga
            loadJenisBangunan();

        }
        //ini kalau input agunan baru (sudah pernah disimpan di realm)
         else{

             if(idProgram.equalsIgnoreCase("222")){
                 setDataFromRealmFlpp();
             }
             else{
                 setDataFromRealm();
             }

            startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
             loadJenisBangunan();

//            startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
//            stepperlayout.setAdapter(new StepAdapterAgunan(getSupportFragmentManager(), AgunanTanahBangunanInputActivity.this, dataAgunan, idAgunan, loan_type, tp_produk), startingStepPosition );
//            stepperlayout.setListener(AgunanTanahBangunanInputActivity.this);
        }
    }

    private void getData() {
        ReqAgunanData req = new ReqAgunanData(AppUtil.parseIntWithDefault(idAplikasi, 0), AppUtil.parseIntWithDefault(idAgunan, 0), AppUtil.parseIntWithDefault(cif, 0));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryAgunanTanahBangunan(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataAgunan = response.body().getData().get("data_detail").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<AgunanTanahBangunan>() {}.getType();

                            dataAgunan = gson.fromJson(listDataAgunan, type);
                            Log.d("superduperid",uuid);

//                            if(dataAgunan.getKoordinat()==null||dataAgunan.getKoordinat().isEmpty()){
//                                setDataFromRealm();
//                            }

                            //jika data id rekomendasi kosong, berarti datanya belum disimpan AO silang, dan ambil data dari realm
                            if (dataAgunan.getDescRekomendasiPenilai()==null&&jenisAo.equalsIgnoreCase("ao silang")){

                                stepperlayout.setAdapter(new StepAdapterAgunan(getSupportFragmentManager(), AgunanTanahBangunanInputActivity.this, setDataFromRealmAoSilang(dataAgunan), idAgunan, loan_type, tp_produk,enableEditText,idProgram,listJenisBangunan), startingStepPosition );
                                stepperlayout.setListener(AgunanTanahBangunanInputActivity.this);
                            }
                            else{
                                stepperlayout.setAdapter(new StepAdapterAgunan(getSupportFragmentManager(), AgunanTanahBangunanInputActivity.this, dataAgunan, idAgunan, loan_type, tp_produk,enableEditText,idProgram,listJenisBangunan), startingStepPosition );
                                stepperlayout.setListener(AgunanTanahBangunanInputActivity.this);
                            }




                        } else{
                            AppUtil.notiferror(AgunanTanahBangunanInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AgunanTanahBangunanInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(AgunanTanahBangunanInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public void onBackPressed() {
        CustomDialog.DialogBackpressSaved(this);
    }

    @Override
    public void onCompleted(View view) {
        if(enableEditText==false){
            finish();
        }
        else{
            if(idProgram.equalsIgnoreCase("222")){
                sendDataFlpp();
            }
            else{
                sendData();
            }

        }


    }

    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int i) {

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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void sendData(){
        loading.setVisibility(View.VISIBLE);
        final AgunanTanahBangunanPojo dataR = realm.where(AgunanTanahBangunanPojo.class).equalTo("uuid", uuid).findFirst();
//        Log.d("lokasipasar2","uuid :"+uuid);
//        Log.d("lokasipasar2","namapasar :"+dataR.getNamaPasar());
//        Log.d("lokasipasar2","nkr pasar : "+dataR.getNkrBrins());

        AgunanTanahBangunanPojo datafix = realm.copyFromRealm(dataR);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendAgunanTanahBangunan(datafix);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            realm.beginTransaction();
                            dataR.deleteFromRealm();
                            realm.commitTransaction();
                            CustomDialog.DialogSuccess(AgunanTanahBangunanInputActivity.this, "Success!", response.body().getMessage(), AgunanTanahBangunanInputActivity.this);
                            id_agunan_response = response.body().getData().get("idAgunan").getAsString();


                            //kalau ao silang yang ngesave, maka set preference ini, untuk acuan validasi di halaman AppraisalDetailActivity
                            if(jenisAo!=null&&jenisAo.equalsIgnoreCase("ao silang")){
                                appPreferences.setStatusAppraise("true");
                            }
                        }
                        else{
                            AppUtil.notiferror(AgunanTanahBangunanInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AgunanTanahBangunanInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(AgunanTanahBangunanInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void sendDataFlpp(){
        loading.setVisibility(View.VISIBLE);
        final AgunanTanahBangunanPojo dataR = realm.where(AgunanTanahBangunanPojo.class).equalTo("uuid", uuid).findFirst();
//        Log.d("lokasipasar2","uuid :"+uuid);
//        Log.d("lokasipasar2","namapasar :"+dataR.getNamaPasar());
//        Log.d("lokasipasar2","nkr pasar : "+dataR.getNkrBrins());

        AgunanTanahBangunanPojo datafix = realm.copyFromRealm(dataR);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendAgunanTanahBangunanFlpp(datafix);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            realm.beginTransaction();
                            dataR.deleteFromRealm();
                            realm.commitTransaction();
                            CustomDialog.DialogSuccess(AgunanTanahBangunanInputActivity.this, "Success!", response.body().getMessage(), AgunanTanahBangunanInputActivity.this);
                            id_agunan_response = response.body().getData().get("idAgunan").getAsString();

                                appPreferences.setStatusAppraise("true");

                        }
                        else{
                            AppUtil.notiferror(AgunanTanahBangunanInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AgunanTanahBangunanInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(AgunanTanahBangunanInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    @Override
    public void success(boolean val) {
        if(val){
            if (idAgunan.equalsIgnoreCase("0")) {
                finish();
                Intent intent = new Intent(AgunanTanahBangunanInputActivity.this, InfoAgunanActivity.class);
                intent.putExtra("idAplikasi", getIntent().getStringExtra("idAplikasi"));
                intent.putExtra("idCif", cif);
                intent.putExtra("idAgunan", id_agunan_response);
                intent.putExtra("loan_type", loan_type);
                intent.putExtra("tp_produk", tp_produk);
                intent.putExtra("jenisAgunan", "tanah dan bangunan");
                intent.putExtra("flagAgunan", flagAgunan);
                intent.putExtra("idProgram", idProgram);
                startActivity(intent);
            }
            else{
                finish();
            }
        }
    }

    @Override
    public void confirm(boolean val) {

    }

    public void setDataFromRealm(){
//        Log.d("masukrealm"," yak benar idapl : "+idAplikasi);
        dataRealm = realm.where(AgunanTanahBangunanPojo.class).equalTo("uuid", uuid).findFirst();

        dataAgunan=new AgunanTanahBangunan();
        dataAgunan.setLokasiTanah(dataRealm.getLokasiTanah());
        dataAgunan.setKavBlok(dataRealm.getKavBlok());
        dataAgunan.setBatasUtaraTanah(dataRealm.getBatasUtaraTanah());
        dataAgunan.setBatasSelatanTanah(dataRealm.getBatasSelatanTanah());
        dataAgunan.setBatasBaratTanah(dataRealm.getBatasBaratTanah());
        dataAgunan.setBatasTimurTanah(dataRealm.getBatasTimurTanah());
        dataAgunan.setKoordinat(dataRealm.getKoordinat());
        dataAgunan.setrT(dataRealm.getRT());
        dataAgunan.setrW(dataRealm.getRW());
        dataAgunan.setKodePos(dataRealm.getKodePos());
        dataAgunan.setKelurahan(dataRealm.getKelurahan());
        dataAgunan.setKecamatan(dataRealm.getKecamatan());
        dataAgunan.setKota(dataRealm.getKota());
        dataAgunan.setPropinsi(dataRealm.getPropinsi());

        dataAgunan.setJenisSuratTanah(dataRealm.getJenisSuratTanah());
        dataAgunan.setNoSertifikat(dataRealm.getNoSertifikat());
        dataAgunan.setAtasNamaSertifikat(dataRealm.getAtasNamaSertifikat());
        dataAgunan.setTanggalSertifikat(dataRealm.getTanggalSertifikat());
        dataAgunan.setLuasTanahSertifikat(dataRealm.getLuasTanahSertifikat());
        dataAgunan.setIdPhotoTBbpn(dataRealm.getIdDok_BPN());
        dataAgunan.setIdPhotoTBPbb(dataRealm.getIdDokPBB());
        dataAgunan.setIdPhotoTBImb(dataRealm.getIdDokIMB());

        dataAgunan.setJenisBangunan(dataRealm.getJenisBangunan());
        dataAgunan.setLokasiBangunanBrins(dataRealm.getLokasiBangunanBrins());
        dataAgunan.setNoIMB(dataRealm.getNoIMB());
        dataAgunan.setJenisAgunanXBRL(dataRealm.getJenisAgunanXBRL());
        dataAgunan.setHubPenghuniDgPemilik(dataRealm.getHubPenghuniDgPemilik());
//        dataAgunan.setNamaPasarBRINS(dataRealm.getNamaPasar());
//        dataAgunan.setNkrPasarBRINS(dataRealm.getNkrBrins());
        dataAgunan.setTingkat(dataRealm.getTingkat());
        dataAgunan.setAlamatIMB(dataRealm.getAlamatIMB());
        dataAgunan.setNamaPenghuni(dataRealm.getNamaPenghuni());
        dataAgunan.setStatusPenghuni(dataRealm.getStatusPenghuni());
        dataAgunan.setAtasNamaIMB(dataRealm.getAtasNamaIMB());
        dataAgunan.setTahunBangunan(dataRealm.getTahunBangunan());


        dataAgunan.setTeganganListrik(dataRealm.getTeganganListrik());
        dataAgunan.setJenisAir(dataRealm.getJenisAir());
        dataAgunan.setNoTelepon(dataRealm.getNoTelepon());
        dataAgunan.setPerawatan(dataRealm.getPerawatan());
        dataAgunan.setJenisBangunanBRINS(dataRealm.getJenisBangunanBRINS());
        dataAgunan.setKetBangunan(dataRealm.getKetBangunan());
        dataAgunan.setPondasi(dataRealm.getPondasi());
        dataAgunan.setDinding(dataRealm.getDinding());
        dataAgunan.setAtap(dataRealm.getAtap());

        dataAgunan.setLebarJalanDepan(String.valueOf(dataRealm.getLebarJalanDepan()));
        dataAgunan.setPeruntukanLokasi(dataRealm.getPeruntukanLokasi());
        dataAgunan.setAksesJalanObjek(dataRealm.getAksesJalanObjek());
        dataAgunan.setFasilitasSosial(dataRealm.getFasilitasSosial());

        dataAgunan.setTanggalPemeriksaan(dataRealm.getTanggalPemeriksaan());
        dataAgunan.setDataPembanding1(dataAgunan.getDataPembanding1());
        dataAgunan.setDataPembanding2(dataAgunan.getDataPembanding2());
        dataAgunan.setHargaMeterTanah(dataRealm.getHargaMeterTanah());

        //ini memang dipantek string kosong, karena di fragmen agunan 6, textchanged-nya ga jalan kalau diambil value
        //jadi biarin aja string kosong biar diisi manual oleh AOnya
        dataAgunan.setLuasBangunan1("");
        dataAgunan.setLuasBangunan2("");
        dataAgunan.setHargaBangunan1(String.valueOf(dataRealm.getHargaBangunan1()));
        dataAgunan.setnLTanahBangunan(String.valueOf(dataRealm.getNLTanahBangunan()));
        dataAgunan.setHargaBangunan1(String.valueOf( Double.valueOf(dataRealm.getHargaBangunan1()).intValue()));
        dataAgunan.setnLTanahBangunan(String.valueOf( Double.valueOf(dataRealm.getNLTanahBangunan()).intValue()));

//        dataAgunan.setLuasBangunan1(String.valueOf( Double.valueOf(dataRealm.getLuasBangunan1()).intValue()));
//        dataAgunan.setLuasBangunan2(String.valueOf( Double.valueOf(dataRealm.getLuasBangunan2()).intValue()));


        dataAgunan.setLokasiTanah(dataRealm.getLokasiTanah());
        dataAgunan.setIdRekomendasiPenilai(dataRealm.getIdRekomendasiPenilai());
        dataAgunan.setDescRekomendasiPenilai(dataRealm.getDescRekomendasiPenilai());
        dataAgunan.setPendapatKondisiJaminan(dataRealm.getPendapatKondisiJaminan());
        dataAgunan.setIdPhotoTBbangunan(dataRealm.getIdBangunan());
        dataAgunan.setIdPhotoTButara(dataRealm.getIdBatas_Utara());
        dataAgunan.setIdPhotoTBselatan(dataRealm.getIdBatas_Selatan());
        dataAgunan.setIdPhotoTBtimur(dataRealm.getIdBatas_Timur());
        dataAgunan.setIdPhotoTBbarat(dataRealm.getIdBatas_Barat());
    }

    public void setDataFromRealmFlpp(){
//        Log.d("masukrealm"," yak benar idapl : "+idAplikasi);
        dataRealm = realm.where(AgunanTanahBangunanPojo.class).equalTo("uuid", uuid).findFirst();

        dataAgunan=new AgunanTanahBangunan();
        dataAgunan.setLokasiTanah(dataRealm.getLokasiTanah());
        dataAgunan.setKavBlok(dataRealm.getKavBlok());
        dataAgunan.setBatasUtaraTanah(dataRealm.getBatasUtaraTanah());
        dataAgunan.setBatasSelatanTanah(dataRealm.getBatasSelatanTanah());
        dataAgunan.setBatasBaratTanah(dataRealm.getBatasBaratTanah());
        dataAgunan.setBatasTimurTanah(dataRealm.getBatasTimurTanah());
        dataAgunan.setKoordinat(dataRealm.getKoordinat());
        dataAgunan.setrT(dataRealm.getRT());
        dataAgunan.setrW(dataRealm.getRW());
        dataAgunan.setKodePos(dataRealm.getKodePos());
        dataAgunan.setKelurahan(dataRealm.getKelurahan());
        dataAgunan.setKecamatan(dataRealm.getKecamatan());
        dataAgunan.setKota(dataRealm.getKota());
        dataAgunan.setPropinsi(dataRealm.getPropinsi());
        dataAgunan.setNoAlamat(dataRealm.getNoAlamat());
        dataAgunan.setKodeWilayah(dataRealm.getKodeWilayah());

        dataAgunan.setJenisSuratTanah(dataRealm.getJenisSuratTanah());
        dataAgunan.setNoSertifikat(dataRealm.getNoSertifikat());
        dataAgunan.setAtasNamaSertifikat(dataRealm.getAtasNamaSertifikat());
        dataAgunan.setTanggalSertifikat(dataRealm.getTanggalSertifikat());
        dataAgunan.setLuasTanahSertifikat(dataRealm.getLuasTanahSertifikat());
        dataAgunan.setIdPhotoTBbpn(dataRealm.getIdDok_BPN());
        dataAgunan.setIdPhotoTBPbb(dataRealm.getIdDokPBB());
        dataAgunan.setIdPhotoTBImb(dataRealm.getIdDokIMB());

        dataAgunan.setJenisBangunan(dataRealm.getJenisBangunan());
        dataAgunan.setLokasiBangunanBrins(dataRealm.getLokasiBangunanBrins());
        dataAgunan.setNoIMB(dataRealm.getNoIMB());
        dataAgunan.setJenisAgunanXBRL(dataRealm.getJenisAgunanXBRL());
        dataAgunan.setHubPenghuniDgPemilik(dataRealm.getHubPenghuniDgPemilik());
//        dataAgunan.setNamaPasarBRINS(dataRealm.getNamaPasar());
//        dataAgunan.setNkrPasarBRINS(dataRealm.getNkrBrins());
        dataAgunan.setTingkat(dataRealm.getTingkat());
        dataAgunan.setAlamatIMB(dataRealm.getAlamatIMB());
        dataAgunan.setNamaPenghuni(dataRealm.getNamaPenghuni());
        dataAgunan.setStatusPenghuni(dataRealm.getStatusPenghuni());
        dataAgunan.setAtasNamaIMB(dataRealm.getAtasNamaIMB());
        dataAgunan.setTahunBangunan(dataRealm.getTahunBangunan());
        dataAgunan.setNoBast(dataRealm.getNoBast());
        dataAgunan.setNoSlf(dataRealm.getNoSlf());
        dataAgunan.setTglBast(dataRealm.getTglBast());
        dataAgunan.setTglSlf(dataRealm.getTglSlf());


        dataAgunan.setTeganganListrik(dataRealm.getTeganganListrik());
        dataAgunan.setJenisAir(dataRealm.getJenisAir());
        dataAgunan.setNoTelepon(dataRealm.getNoTelepon());
        dataAgunan.setPerawatan(dataRealm.getPerawatan());
        dataAgunan.setJenisBangunanBRINS(dataRealm.getJenisBangunanBRINS());
        dataAgunan.setKetBangunan(dataRealm.getKetBangunan());
        dataAgunan.setPondasi(dataRealm.getPondasi());
        dataAgunan.setDinding(dataRealm.getDinding());
        dataAgunan.setAtap(dataRealm.getAtap());

        dataAgunan.setLebarJalanDepan(Double.toString(dataRealm.getLebarJalanDepan()));
        dataAgunan.setPeruntukanLokasi(dataRealm.getPeruntukanLokasi());
        dataAgunan.setAksesJalanObjek(dataRealm.getAksesJalanObjek());
        dataAgunan.setFasilitasSosial(dataRealm.getFasilitasSosial());

        dataAgunan.setTanggalPemeriksaan(dataRealm.getTanggalPemeriksaan());
        dataAgunan.setDataPembanding1(dataAgunan.getDataPembanding1());
        dataAgunan.setDataPembanding2(dataAgunan.getDataPembanding2());
        dataAgunan.setHargaMeterTanah(dataRealm.getHargaMeterTanah());

        //ini memang dipantek string kosong, karena di fragmen agunan 6, textchanged-nya ga jalan kalau diambil value
        //jadi biarin aja string kosong biar diisi manual oleh AOnya
        dataAgunan.setLuasBangunan1(Double.toString(dataRealm.getLuasBangunan1()));
        dataAgunan.setLuasBangunan2(Double.toString(dataRealm.getLuasBangunan2()));
        dataAgunan.setHargaBangunan1("0");
        dataAgunan.setnLTanahBangunan("0");
        dataAgunan.setHargaMeterTanah(0);
//        dataAgunan.setHargaBangunan1(Long.toString( Double.valueOf(dataRealm.getHargaBangunan1()).intValue()));
//        dataAgunan.setnLTanahBangunan(Long.toString( Double.valueOf(dataRealm.getNLTanahBangunan()).intValue()));

//        dataAgunan.setLuasBangunan1(String.valueOf( Double.valueOf(dataRealm.getLuasBangunan1()).intValue()));
//        dataAgunan.setLuasBangunan2(String.valueOf( Double.valueOf(dataRealm.getLuasBangunan2()).intValue()));


        dataAgunan.setLokasiTanah(dataRealm.getLokasiTanah());
        dataAgunan.setIdRekomendasiPenilai(dataRealm.getIdRekomendasiPenilai());
        dataAgunan.setDescRekomendasiPenilai(dataRealm.getDescRekomendasiPenilai());
        dataAgunan.setPendapatKondisiJaminan(dataRealm.getPendapatKondisiJaminan());
        dataAgunan.setIdPhotoTBbangunan(dataRealm.getIdBangunan());
        dataAgunan.setIdPhotoTButara(dataRealm.getIdBatas_Utara());
        dataAgunan.setIdPhotoTBselatan(dataRealm.getIdBatas_Selatan());
        dataAgunan.setIdPhotoTBtimur(dataRealm.getIdBatas_Timur());
        dataAgunan.setIdPhotoTBbarat(dataRealm.getIdBatas_Barat());
        dataAgunan.setIdDok41(dataRealm.getIdDok41());
    }

    public AgunanTanahBangunan setDataFromRealmAoSilang(AgunanTanahBangunan dataAgunanService){
        //JIKA AO SILANG, MAKA 3 HALAMAN PERTAMA TETAP AMBIL DATA DARI SERVICE, SISANYA DARI REALM

//        Log.d("masukrealm"," yak benar idapl : "+idAplikasi);
        dataRealm = realm.where(AgunanTanahBangunanPojo.class).equalTo("idApl", Integer.parseInt(idAplikasi)).findFirst();

        dataAgunan=new AgunanTanahBangunan();
        dataAgunan.setLokasiTanah(dataAgunanService.getLokasiTanah());
        dataAgunan.setKavBlok(dataAgunanService.getKavBlok());
        dataAgunan.setBatasUtaraTanah(dataAgunanService.getBatasUtaraTanah());
        dataAgunan.setBatasSelatanTanah(dataAgunanService.getBatasSelatanTanah());
        dataAgunan.setBatasBaratTanah(dataAgunanService.getBatasBaratTanah());
        dataAgunan.setBatasTimurTanah(dataAgunanService.getBatasTimurTanah());
        dataAgunan.setKoordinat(dataAgunanService.getKoordinat());
        dataAgunan.setrT(dataAgunanService.getrT());
        dataAgunan.setrW(dataAgunanService.getrW());
        dataAgunan.setKodePos(dataAgunanService.getKodePos());
        dataAgunan.setKelurahan(dataAgunanService.getKelurahan());
        dataAgunan.setKecamatan(dataAgunanService.getKecamatan());
        dataAgunan.setKota(dataAgunanService.getKota());
        dataAgunan.setPropinsi(dataAgunanService.getPropinsi());

        dataAgunan.setJenisSuratTanah(dataAgunanService.getJenisSuratTanah());
        dataAgunan.setNoSertifikat(dataAgunanService.getNoSertifikat());
        dataAgunan.setAtasNamaSertifikat(dataAgunanService.getAtasNamaSertifikat());
        dataAgunan.setTanggalSertifikat(dataAgunanService.getTanggalSertifikat());
        dataAgunan.setLuasTanahSertifikat(dataAgunanService.getLuasTanahSertifikat());
        dataAgunan.setIdPhotoTBbpn(dataAgunanService.getIdPhotoTBbpn());
        dataAgunan.setIdPhotoTBPbb(dataAgunanService.getIdPhotoTBPbb());
        dataAgunan.setIdPhotoTBImb(dataAgunanService.getIdPhotoTBImb());

        dataAgunan.setJenisBangunan(dataAgunanService.getJenisBangunan());
        dataAgunan.setLokasiBangunanBrins(dataAgunanService.getLokasiBangunanBrins());
        dataAgunan.setNoIMB(dataAgunanService.getNoIMB());
        dataAgunan.setJenisAgunanXBRL(dataAgunanService.getJenisAgunanXBRL());
        dataAgunan.setHubPenghuniDgPemilik(dataAgunanService.getHubPenghuniDgPemilik());
//        dataAgunan.setNamaPasarBRINS(dataRealm.getNamaPasar());
//        dataAgunan.setNkrPasarBRINS(dataRealm.getNkrBrins());
        dataAgunan.setTingkat(dataAgunanService.getTingkat());
        dataAgunan.setAlamatIMB(dataAgunanService.getAlamatIMB());
        dataAgunan.setNamaPenghuni(dataAgunanService.getNamaPenghuni());
        dataAgunan.setStatusPenghuni(dataAgunanService.getStatusPenghuni());
        dataAgunan.setAtasNamaIMB(dataAgunanService.getAtasNamaIMB());
        dataAgunan.setTahunBangunan(dataAgunanService.getTahunBangunan());


        dataAgunan.setTeganganListrik(dataRealm.getTeganganListrik());
        dataAgunan.setJenisAir(dataRealm.getJenisAir());
        dataAgunan.setNoTelepon(dataRealm.getNoTelepon());
        dataAgunan.setPerawatan(dataRealm.getPerawatan());
        dataAgunan.setJenisBangunanBRINS(dataRealm.getJenisBangunanBRINS());
        dataAgunan.setKetBangunan(dataRealm.getKetBangunan());
        dataAgunan.setPondasi(dataRealm.getPondasi());
        dataAgunan.setDinding(dataRealm.getDinding());
        dataAgunan.setAtap(dataRealm.getAtap());

        dataAgunan.setLebarJalanDepan(Double.toString(dataRealm.getLebarJalanDepan()));
        dataAgunan.setPeruntukanLokasi(dataRealm.getPeruntukanLokasi());
        dataAgunan.setAksesJalanObjek(dataRealm.getAksesJalanObjek());
        dataAgunan.setFasilitasSosial(dataRealm.getFasilitasSosial());

        dataAgunan.setTanggalPemeriksaan(dataRealm.getTanggalPemeriksaan());
        dataAgunan.setDataPembanding1(dataAgunan.getDataPembanding1());
        dataAgunan.setDataPembanding2(dataAgunan.getDataPembanding2());
        dataAgunan.setHargaMeterTanah(dataRealm.getHargaMeterTanah());

        //ini memang dipantek string kosong, karena di fragmen agunan 6, textchanged-nya ga jalan kalau diambil value
        //jadi biarin aja string kosong biar diisi manual oleh AOnya
        dataAgunan.setLuasBangunan1("");
        dataAgunan.setLuasBangunan2("");
        dataAgunan.setHargaBangunan1(Long.toString(dataRealm.getHargaBangunan1()));
        dataAgunan.setnLTanahBangunan(Long.toString(dataRealm.getNLTanahBangunan()));
        dataAgunan.setHargaBangunan1(Long.toString( Double.valueOf(dataRealm.getHargaBangunan1()).intValue()));
        dataAgunan.setnLTanahBangunan(Long.toString( Double.valueOf(dataRealm.getNLTanahBangunan()).intValue()));

//        dataAgunan.setLuasBangunan1(String.valueOf( Double.valueOf(dataRealm.getLuasBangunan1()).intValue()));
//        dataAgunan.setLuasBangunan2(String.valueOf( Double.valueOf(dataRealm.getLuasBangunan2()).intValue()));


        dataAgunan.setLokasiTanah(dataRealm.getLokasiTanah());
        dataAgunan.setIdRekomendasiPenilai(dataRealm.getIdRekomendasiPenilai());
        dataAgunan.setDescRekomendasiPenilai(dataRealm.getDescRekomendasiPenilai());
        dataAgunan.setPendapatKondisiJaminan(dataRealm.getPendapatKondisiJaminan());
        dataAgunan.setIdPhotoTBbangunan(dataRealm.getIdBangunan());
        dataAgunan.setIdPhotoTButara(dataRealm.getIdBatas_Utara());
        dataAgunan.setIdPhotoTBselatan(dataRealm.getIdBatas_Selatan());
        dataAgunan.setIdPhotoTBtimur(dataRealm.getIdBatas_Timur());
        dataAgunan.setIdPhotoTBbarat(dataRealm.getIdBatas_Barat());

        return  dataAgunan;
    }

    private void loadJenisBangunan() {
        loading.setVisibility(View.VISIBLE);
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listJenisBangunan(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                try {
                    if (response.isSuccessful()) {
                        loading.setVisibility(View.GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<ListJenisBangunan>>() {}.getType();

                            listJenisBangunan = gson.fromJson(listDataString, type);


                            //kalau udah dapat list jenis bangunan, baru load data agunan
                            if (idAgunan!=null&&!idAgunan.equalsIgnoreCase("0")) {
                                getData();
                            }
                            else{
                                stepperlayout.setAdapter(new StepAdapterAgunan(getSupportFragmentManager(), AgunanTanahBangunanInputActivity.this, dataAgunan, idAgunan, loan_type, tp_produk,enableEditText,idProgram,listJenisBangunan), startingStepPosition );
                                stepperlayout.setListener(AgunanTanahBangunanInputActivity.this);
                            }





                        } else {
//                            finish();
                            AppUtil.notiferror(AgunanTanahBangunanInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AgunanTanahBangunanInputActivity.this,findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    loading.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                loading.setVisibility(View.GONE);
                t.printStackTrace();
                Toast.makeText(AgunanTanahBangunanInputActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(AgunanTanahBangunanInputActivity.this,findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }
}

