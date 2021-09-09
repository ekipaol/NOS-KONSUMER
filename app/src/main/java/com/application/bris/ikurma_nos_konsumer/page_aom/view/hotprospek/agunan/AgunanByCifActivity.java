package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.kpr.ReqAppraisal;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.kpr.ReqKirimAppraisal;
import com.application.bris.ikurma_nos_konsumer.database.pojo.AgunanTanahBangunanPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.HotprospekDetailKprActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan.kpr_only.InfoAgunanAoSilangActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.ViewCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArrAgunanByCif;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqAgunanByCif;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.page_aom.adapter.agunan.FrontAgunanByCifAdapater;
import com.application.bris.ikurma_nos_konsumer.page_aom.adapter.agunan.FrontAgunanTanahKosongByCifAdapater;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogBottomAgunan;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.AgunanByCifListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.AgunanListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanKendaraanFront;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanDepositoFront;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanKiosFront;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanTanahBangunanFront;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanTanahKosongFront;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.ListInfo;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgunanByCifActivity extends AppCompatActivity implements AgunanListener, AgunanByCifListener, ConfirmListener {

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;

    @BindView(R.id.sm_placeholder_tanah_bangunan)
    ShimmerFrameLayout sm_placeholder_tanah_bangunan;
    @BindView(R.id.sm_placeholder_tanah_kosong)
    ShimmerFrameLayout sm_placeholder_tanah_kosong;

    @BindView(R.id.ll_emptydata_tanah_bangunan)
    LinearLayout ll_emptydata_tanah_bangunan;
    @BindView(R.id.ll_emptydata_tanah_kosong)
    LinearLayout ll_emptydata_tanah_kosong;

    @BindView(R.id.rv_tanah_bangunan)
    RecyclerView rv_tanah_bangunan;
    @BindView(R.id.rv_tanah_kosong)
    RecyclerView rv_tanah_kosong;

    @BindView(R.id.btn_req_appraisal)
    Button btn_req_appraisal;
    @BindView(R.id.btn_kembalikan_appraisal)
    Button btn_kembalikan_appraisal;



    @BindView(R.id.floatAdd)
    FloatingActionButton btFloat;

    private ListInfo dataInfo;
    private List<AgunanTanahBangunanFront> dataTanahBangunan;
    private AgunanTanahBangunanFront dataTanahBangunanTemp;
    private List<AgunanTanahKosongFront> dataTanahKosong;
    private FrontAgunanByCifAdapater adapterListTanahBangunan;
    private FrontAgunanTanahKosongByCifAdapater adapterListTanahKosong;
    private LinearLayoutManager layoutTanahBangunan;
    private LinearLayoutManager layoutTanahKosong;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String idAplikasi, idCif,jenisAo, idProgram;
    private int flagAgunan;
    Realm realm;
    List<AgunanTanahBangunanPojo> dataRealmTanahBangunan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agunan_bycif);

        idAplikasi = getIntent().getStringExtra("idAplikasi");
        idCif = getIntent().getStringExtra("cif");
        jenisAo = getIntent().getStringExtra("jenisAo");
        flagAgunan=getIntent().getIntExtra("flagAgunan",0);

        if(getIntent().hasExtra("idProgram")){
            idProgram =getIntent().getStringExtra("idProgram");
        }
        else{
            idProgram ="";
        }



        main();
        backgroundStatusBar();
        realm=Realm.getDefaultInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm_placeholder_tanah_bangunan.startShimmer();
        sm_placeholder_tanah_bangunan.setVisibility(View.VISIBLE);
        sm_placeholder_tanah_kosong.startShimmer();
        sm_placeholder_tanah_kosong.setVisibility(View.VISIBLE);
        loadData();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void main(){
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Daftar Agunan Nasabah");

        otherViewChanges();

        btFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAgunanMenu();
            }
        });

        btn_req_appraisal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataTanahKosong.size()==0&&dataTanahBangunan.size()==0){
                    AppUtil.notiferror(AgunanByCifActivity.this, findViewById(android.R.id.content), "Harap tambah agunan sebelum melakukan request appraisal");
                }
                else{
                    requestAppraisal();
                }

            }
        });

        btn_kembalikan_appraisal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    kembalikanAppraisal();


            }
        });
    }

    private void openAgunanMenu(){
        DialogBottomAgunan.display(getSupportFragmentManager(), AgunanByCifActivity.this);
    }

    public void loadData(){
        sm_placeholder_tanah_bangunan.startShimmer();
        sm_placeholder_tanah_kosong.startShimmer();

        ReqAgunanByCif req = new ReqAgunanByCif();
        req.setIdCif(getIntent().getStringExtra("cif"));
        req.setIdApl(getIntent().getStringExtra("idAplikasi"));

        Call<ParseResponseArrAgunanByCif> call = apiClientAdapter.getApiInterface().listAgunanAll(req);
        call.enqueue(new Callback<ParseResponseArrAgunanByCif>() {
            @Override
            public void onResponse(Call<ParseResponseArrAgunanByCif> call, Response<ParseResponseArrAgunanByCif> response) {
                sm_placeholder_tanah_bangunan.stopShimmer();
                sm_placeholder_tanah_bangunan.setVisibility(View.GONE);
                sm_placeholder_tanah_kosong.stopShimmer();
                sm_placeholder_tanah_kosong.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            Gson gson = new Gson();
                            Type typeInfo = new TypeToken<List<ListInfo>>() {
                            }.getType();
                            Type typeTanahBangunan = new TypeToken<List<AgunanTanahBangunanFront>>() {
                            }.getType();
                            Type typeKendaraan = new TypeToken<List<AgunanKendaraanFront>>() {
                            }.getType();
                            Type typeTanahKosong = new TypeToken<List<AgunanTanahKosongFront>>() {
                            }.getType();
                            Type typeKios = new TypeToken<List<AgunanKiosFront>>() {
                            }.getType();
                            Type typeDeposito = new TypeToken<List<AgunanDepositoFront>>() {
                            }.getType();

                            List<ListInfo> listInfo = gson.fromJson(response.body().getInfo().toString(), typeInfo);
                            dataInfo = listInfo.get(0);

                            dataTanahBangunan = gson.fromJson(response.body().getData().get("List_Agunan_TanahBangunan").toString(), typeTanahBangunan);

                            dataTanahKosong = gson.fromJson(response.body().getData().get("List_Agunan_TanahKosong").toString(), typeTanahKosong);

//                            if (dataTanahBangunan.size() > 0) {
//                                ll_emptydata_tanah_bangunan.setVisibility(View.GONE);
//                                initializeTanahBangunan();
//                            } else {
//                                ll_emptydata_tanah_bangunan.setVisibility(View.VISIBLE);
//                            }

                            initializeTanahBangunan();


                            if (dataTanahKosong.size() > 0) {
                                ll_emptydata_tanah_kosong.setVisibility(View.GONE);
                                initializeTanahKosong();
                            } else {
                                ll_emptydata_tanah_kosong.setVisibility(View.VISIBLE);
                            }


                        } else {
                            AppUtil.notiferror(AgunanByCifActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AgunanByCifActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArrAgunanByCif> call, Throwable t) {
                AppUtil.notiferror(AgunanByCifActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void initializeTanahBangunan(){

        //mencari data agunan yang sudah tersimpan di realm, dan menambahkannya di list
        dataRealmTanahBangunan = realm.where(AgunanTanahBangunanPojo.class).equalTo("idApl", Integer.parseInt(idAplikasi)).findAll();

//        realm.beginTransaction();
//        for (int i = 0; i <dataRealmTanahBangunan.size() ; i++) {
//            dataRealmTanahBangunan.get(i).deleteFromRealm();
//
//        }
//        realm.commitTransaction();
//        realm.close();

        if(dataRealmTanahBangunan!=null){
            for (int i = 0; i <dataRealmTanahBangunan.size() ; i++) {
//            dataTanahBangunanTemp.setFID_AGUNAN("Data Lokal");
//            dataTanahBangunanTemp.setNO_SERTIFIKAT_TANAH("-");
//            dataTanahBangunanTemp.setJENIS_SURAT_TANAH("-");
//            dataTanahBangunanTemp.setLOKASI(dataRealmTanahBangunan.get(i).getLokasiTanah());
//            dataTanahBangunanTemp.setNILAI_PASAR("-");

                if(dataRealmTanahBangunan.get(i).getIdAgunan()==0){
                    dataTanahBangunan.add(new AgunanTanahBangunanFront(0,"-",dataRealmTanahBangunan.get(i).getLokasiTanah(),"-","0","0","-",dataRealmTanahBangunan.get(i).getUuid()));
                }


            }
        }

        if(dataTanahBangunan.size()==0){
            ll_emptydata_tanah_bangunan.setVisibility(View.VISIBLE);
        }
        else{
            ll_emptydata_tanah_bangunan.setVisibility(View.GONE);
        }


        adapterListTanahBangunan = new FrontAgunanByCifAdapater(AgunanByCifActivity.this, dataTanahBangunan, AgunanByCifActivity.this, idAplikasi, idCif);
        layoutTanahBangunan = new LinearLayoutManager(AgunanByCifActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rv_tanah_bangunan.setLayoutManager(layoutTanahBangunan);
        rv_tanah_bangunan.setAdapter(adapterListTanahBangunan);
        ViewCompat.setNestedScrollingEnabled(rv_tanah_bangunan, false);

        if (dataTanahBangunan == null){
            ll_emptydata_tanah_bangunan.setVisibility(View.VISIBLE);
        }
        else{
            ll_emptydata_tanah_bangunan.setVisibility(View.GONE);
        }
    }


    public void initializeTanahKosong(){
        adapterListTanahKosong = new FrontAgunanTanahKosongByCifAdapater(AgunanByCifActivity.this, dataTanahKosong, AgunanByCifActivity.this, idAplikasi, idCif);
        layoutTanahKosong = new LinearLayoutManager(AgunanByCifActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rv_tanah_kosong.setLayoutManager(layoutTanahKosong);
        rv_tanah_kosong.setAdapter(adapterListTanahKosong);
        ViewCompat.setNestedScrollingEnabled(rv_tanah_kosong, false);

        if (dataTanahKosong == null){
            ll_emptydata_tanah_kosong.setVisibility(View.VISIBLE);
        }
        else{
            ll_emptydata_tanah_kosong.setVisibility(View.GONE);
        }

    }





    @Override
    public void onSelectMenuAgunan(String idMenu) {
        switch (idMenu) {
            case "AgunanTanahBangunan Tanah dan Bangunan":
                Intent intent=new Intent(AgunanByCifActivity.this, AgunanTanahBangunanInputActivity.class);
                intent.putExtra("cif", getIntent().getStringExtra("cif"));
                intent.putExtra("idAplikasi", getIntent().getStringExtra("idAplikasi"));
                intent.putExtra("loan_type", dataInfo.getLOAN_TYPE());
                intent.putExtra("tp_produk", String.valueOf(dataInfo.getFID_TP_PRODUK()));
                intent.putExtra("idAgunan", "0");
                intent.putExtra("jenisAgunan", "tanah dan bangunan");
                intent.putExtra("newInput", "true");
                intent.putExtra("flagAgunan",flagAgunan);
                intent.putExtra("idProgram", idProgram);
                startActivity(intent);
                break;

            case "AgunanTanahBangunan Tanah Kosong":
                Intent itTanahKosong=new Intent(AgunanByCifActivity.this, ActivityAgunanTanahKosong.class);
                itTanahKosong.putExtra("cif", getIntent().getStringExtra("cif"));
                itTanahKosong.putExtra("idAplikasi", getIntent().getStringExtra("idAplikasi"));
                itTanahKosong.putExtra("loan_type", dataInfo.getLOAN_TYPE());
                itTanahKosong.putExtra("tp_produk", String.valueOf(dataInfo.getFID_TP_PRODUK()));
                itTanahKosong.putExtra("idAgunan", "0");
                itTanahKosong.putExtra("jenisAgunan", "tanah kosong");
                itTanahKosong.putExtra("newInput", "true");
                itTanahKosong.putExtra("flagAgunan",flagAgunan);
                itTanahKosong.putExtra("idProgram", idProgram);
                startActivity(itTanahKosong);
                break;

        }
    }


    //dipakai untuk agunan yang sudah memiliki id agunan
    @Override
    public void onAgunanByCifSelect(String idAplikasi, String idCif, String idAgunan, String jenisAgunan) {
        if(jenisAo.equalsIgnoreCase("pemrakarsa")){
            Intent intent = new Intent(this, InfoAgunanActivity.class);
            intent.putExtra("idAplikasi", idAplikasi);
            intent.putExtra("idCif", idCif);
            intent.putExtra("idAgunan", idAgunan);
            intent.putExtra("loan_type", dataInfo.getLOAN_TYPE());
            intent.putExtra("tp_produk", String.valueOf(dataInfo.getFID_TP_PRODUK()));
            intent.putExtra("jenisAgunan", jenisAgunan);
            intent.putExtra("prev", "cif");
            intent.putExtra("flagAgunan",flagAgunan);
            intent.putExtra("idProgram", idProgram);
            startActivity(intent);
        }
        else if(jenisAo.equalsIgnoreCase("ao silang")){
            Intent intent = new Intent(this, InfoAgunanAoSilangActivity.class);
            intent.putExtra("idAplikasi", idAplikasi);
            intent.putExtra("idCif", idCif);
            intent.putExtra("idAgunan", idAgunan);
            intent.putExtra("loan_type", dataInfo.getLOAN_TYPE());
            intent.putExtra("tp_produk", String.valueOf(dataInfo.getFID_TP_PRODUK()));
            intent.putExtra("jenisAgunan", jenisAgunan);
            intent.putExtra("prev", "cif");
            intent.putExtra("flagAgunan",flagAgunan);
            intent.putExtra("idProgram", idProgram);
            startActivity(intent);
        }
    }



    //dipakai untuk agunan yang belum memiliki id agunan alias data lokal
    @Override
    public void onAgunanByCifSelect(String idAplikasi, String idCif, String idAgunan, String jenisAgunan,String uuid) {
        if(jenisAo.equalsIgnoreCase("pemrakarsa")){
            Intent intent = new Intent(this, InfoAgunanActivity.class);
            intent.putExtra("idAplikasi", idAplikasi);
            intent.putExtra("idCif", idCif);
            intent.putExtra("idAgunan", idAgunan);
            intent.putExtra("loan_type", dataInfo.getLOAN_TYPE());
            intent.putExtra("tp_produk", String.valueOf(dataInfo.getFID_TP_PRODUK()));
            intent.putExtra("jenisAgunan", jenisAgunan);
            intent.putExtra("prev", "cif");
            intent.putExtra("uuid", uuid);
            intent.putExtra("flagAgunan",flagAgunan);
            intent.putExtra("idProgram", idProgram);
            startActivity(intent);
        }
        else if(jenisAo.equalsIgnoreCase("ao silang")){
            Intent intent = new Intent(this, InfoAgunanAoSilangActivity.class);
            intent.putExtra("idAplikasi", idAplikasi);
            intent.putExtra("idCif", idCif);
            intent.putExtra("idAgunan", idAgunan);
            intent.putExtra("loan_type", dataInfo.getLOAN_TYPE());
            intent.putExtra("tp_produk", String.valueOf(dataInfo.getFID_TP_PRODUK()));
            intent.putExtra("jenisAgunan", jenisAgunan);
            intent.putExtra("prev", "cif");
            intent.putExtra("uuid", uuid);
            intent.putExtra("flagAgunan",flagAgunan);
            intent.putExtra("idProgram", idProgram);
            startActivity(intent);
        }

    }

    private void requestAppraisal() {
        new AlertDialog.Builder(AgunanByCifActivity.this)
                .setTitle("Request Appraisal ke Pemutus?")
                .setMessage("Aplikasi akan dikirim ke pemutus untuk mendapatkan AO Silang baru, lanjutkan?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog loadingDialog = ProgressDialog.show(AgunanByCifActivity.this, "",
                                "Memproses", true);

                        ReqAppraisal req = new ReqAppraisal();
                        req.setIdApl(Integer.parseInt(idAplikasi));
                        req.setUid(appPreferences.getUid());
                        Call<ParseResponse> call = apiClientAdapter.getApiInterface().requestAppraisal(req);
                        call.enqueue(new Callback<ParseResponse>() {
                            @Override
                            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                                try {
                                    if (response.isSuccessful()) {
                                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                                            loadingDialog.dismiss();
                                            CustomDialog.DialogSuccess(AgunanByCifActivity.this, "Sukses!", "Berhasil request appraisal ke pemutus, silahkan monitor secara berkala status aplikasi hingga agunan selesai diproses oleh AO Silang", AgunanByCifActivity.this);
//                                            Toast.makeText(InfoAgunanAoSilangActivity.this, "Berhasil Menghapus Agunan", Toast.LENGTH_SHORT).show();
//                                            finish();
                                        } else {
                                            AppUtil.notiferror(AgunanByCifActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                                        }
                                    } else {
                                        Error error = ParseResponseError.confirmEror(response.errorBody());
                                        AppUtil.notiferror(AgunanByCifActivity.this, findViewById(android.R.id.content), error.getMessage());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ParseResponse> call, Throwable t) {
                                AppUtil.notiferror(AgunanByCifActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
                            }
                        });

                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("Batal", null)
                .show();
    }

    private void kembalikanAppraisal() {
        new AlertDialog.Builder(AgunanByCifActivity.this)
                .setTitle("Kembalikan ke Ao Silang?")
                .setMessage("Aplikasi akan dikembalikan ke AO Silang : "+appPreferences.getNamaAppraisal()+", lanjutkan?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog loadingDialog = ProgressDialog.show(AgunanByCifActivity.this, "",
                                "Memproses", true);

                        ReqKirimAppraisal req = new ReqKirimAppraisal();
                        req.setIdApl(Integer.parseInt(idAplikasi));
                        req.setUidApraisal(Integer.parseInt(appPreferences.getUidAppraisal()));
                        Call<ParseResponse> call = apiClientAdapter.getApiInterface().kirimApraisal(req);
                        call.enqueue(new Callback<ParseResponse>() {
                            @Override
                            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                                try {
                                    if (response.isSuccessful()) {
                                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                                            loadingDialog.dismiss();
                                            CustomDialog.DialogSuccess(AgunanByCifActivity.this, "Sukses!", "Berhasil mengembalikan agunan ke AO silang", AgunanByCifActivity.this);
//                                            Toast.makeText(InfoAgunanAoSilangActivity.this, "Berhasil Menghapus Agunan", Toast.LENGTH_SHORT).show();
//                                            finish();
                                        } else {
                                            AppUtil.notiferror(AgunanByCifActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                                        }
                                    } else {
                                        Error error = ParseResponseError.confirmEror(response.errorBody());
                                        AppUtil.notiferror(AgunanByCifActivity.this, findViewById(android.R.id.content), error.getMessage());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ParseResponse> call, Throwable t) {
                                AppUtil.notiferror(AgunanByCifActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
                            }
                        });

                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("Batal", null)
                .show();
    }

    @Override
    public void success(boolean val) {

        Intent intent=new Intent(AgunanByCifActivity.this, HotprospekDetailKprActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }

    @Override
    public void confirm(boolean val) {

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(AgunanByCifActivity.this, AgunanTerikatActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    private void otherViewChanges(){
        if(jenisAo.equalsIgnoreCase("pemrakarsa")){
            btn_req_appraisal.setVisibility(View.VISIBLE);
            btFloat.show();

            //kalau sudah ada ao silang baru nongol tombol kembalikan ke ao silang
            if(!appPreferences.getUidAppraisal().isEmpty()){
                btn_kembalikan_appraisal.setVisibility(View.VISIBLE);
            }
            else{
                btn_kembalikan_appraisal.setVisibility(View.GONE);
            }
        }
        else if(jenisAo.equalsIgnoreCase("ao silang")){
            btn_req_appraisal.setVisibility(View.GONE);
            btFloat.hide();
        }

        //khusus flpp
        if(idProgram.equalsIgnoreCase("222")){
            btn_req_appraisal.setVisibility(View.GONE);
            btFloat.show();
        }
    }
}
