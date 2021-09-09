package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.sektorekonomi.kpr;


import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.InputSektorEkonomiKpr;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquirySektorEkonomi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogDescCode;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogSektorEkonomi;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.DescCodeListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.SektorEkonomiListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.CodeDesc;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MDataPembiayaanKpr;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MsektorEkonomi;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;
public class SektorEkonomiKprActivity extends AppCompatActivity implements DescCodeListener, SektorEkonomiListener, ConfirmListener{

    @BindView(R.id.ll_content)
    LinearLayout ll_content;

    @BindView(R.id.tf_tujuanpenggunaan)
    TextFieldBoxes tf_tujuanpenggunaan;
    @BindView(R.id.et_tujuanpenggunaan)
    EditText et_tujuanpenggunaan;

    @BindView(R.id.tf_bidangusaha)
    TextFieldBoxes tf_bidangusaha;
    @BindView(R.id.et_bidangusaha)
    EditText et_bidangusaha;

    @BindView(R.id.tf_sifatpembiayaan)
    TextFieldBoxes tf_sifatpembiayaan;
    @BindView(R.id.et_sifatpembiayaan)
    EditText et_sifatpembiayaan;

    @BindView(R.id.tf_jenispenggunaan)
    TextFieldBoxes tf_jenispenggunaan;
    @BindView(R.id.et_jenispenggunaan)
    EditText et_jenispenggunaan;

    @BindView(R.id.tf_jenispenggunaanlbu)
    TextFieldBoxes tf_jenispenggunaanlbu;
    @BindView(R.id.et_jenispenggunaanlbu)
    EditText et_jenispenggunaanlbu;

    @BindView(R.id.tf_jenispembiayaanlbu)
    TextFieldBoxes tf_jenispembiayaanlbu;
    @BindView(R.id.et_jenispembiayaanlbu)
    EditText et_jenispembiayaanlbu;

    @BindView(R.id.tf_sifatpembiayaanlbu)
    TextFieldBoxes tf_sifatpembiayaanlbu;
    @BindView(R.id.et_sifatpembiayaanlbu)
    EditText et_sifatpembiayaanlbu;

    @BindView(R.id.tf_kategoripembiayaanlbu)
    TextFieldBoxes tf_kategoripembiayaanlbu;
    @BindView(R.id.et_kategoripembiayaanlbu)
    EditText et_kategoripembiayaanlbu;

    @BindView(R.id.tf_sektorekonomi)
    TextFieldBoxes tf_sektorekonomi;
    @BindView(R.id.et_sektorekonomi)
    EditText et_sektorekonomi;

    @BindView(R.id.tf_klasifikasi_kpr)
    TextFieldBoxes tf_klasifikasi_kpr;
    @BindView(R.id.et_klasifikasi_kpr)
    EditText et_klasifikasi_kpr;

    @BindView(R.id.tf_tujuan_buka_rekening)
    TextFieldBoxes tf_tujuan_buka_rekening;
    @BindView(R.id.et_tujuan_membuka_rekening)
    EditText et_tujuan_membuka_rekening;

    @BindView(R.id.tf_sumber_aplikasi)
    TextFieldBoxes tf_sumber_aplikasi;
    @BindView(R.id.et_sumber_aplikasi)
    EditText et_sumber_aplikasi;

    @BindView(R.id.tf_referensi)
    TextFieldBoxes tf_referensi;
    @BindView(R.id.et_referensi)
    EditText et_referensi;

    @BindView(R.id.tf_hubungannasabahdgnbank)
    TextFieldBoxes tf_hubungannasabahdgnbank;
    @BindView(R.id.et_hubungannasabahdgnbank)
    EditText et_hubungannasabahdgnbank;

    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_send)
    Button btn_send;

    private int idAplikasi;
    private int cifLas;
    private int idTujuan;
    private String namaTujuan, approved;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    String gimmick;
    String loanType;

    private List<CodeDesc> dtKatLBU;
    private List<CodeDesc> dtJenisPenggunaanLbu;
    private List<CodeDesc> dtSifatPbyLBU;
    private List<CodeDesc> dtSifatPby;
    private List<CodeDesc> dtBidangUsaha;
    private List<CodeDesc> dtJenisPenggunaan;
    private List<CodeDesc> dtJenisKreditLbu;
//    private List<CodeDesc> dtHubDebitur;
    private List<CodeDesc> dtKlasifikasiKpr;
    private List<CodeDesc> dtReferensi;
    private List<CodeDesc> dtSumberAplikasi;
    private List<CodeDesc> dtTujuanMembukaRekening;
    private MDataPembiayaanKpr dataPbySebelumPutusan;

    private String dtKatLBUString, dtJenisPenggunaanLbuString, dtSifatPbyLBUString, dtSifatPbyString,dtBidangUsahaString,
            dtJenisPenggunaanString, dtJenisKreditLbuString, dtHubDebiturString, dataPbySebelumPutusanString,dtKlasifikasiKprString,dtReferensiString,dtSumberAplikasiString,dtTujuanMembukaRekeningString;

    private String val_hubunganNasabahdgnBank = "";
    private String val_bidangUsaha = "";
    private String val_sifatKredit = "";
    private String val_jenisPenggunaan = "";
    private String val_jenisPenggunaanLBU = "";
    private int val_jenisKreditLBU = 0;
    private int val_sifatKreditLBU = 0;
    private String val_kategoriKreditLBU = "";
    private String val_sektorEkonomiSID = "";
    private String val_sektorEkonomiLBU = "";

    private int valTujuanMembukaRekening = 0;
    private int val_referensi = 0;
    private String val_klasifikasi_kpr = "";
    private String val_sumber_aplikasi = "";
    private String idProgram="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_hotprospek_kpr_sektorekonomi);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        cifLas = getIntent().getIntExtra("cifLas", 0);
        idTujuan = getIntent().getIntExtra("idTujuan", 0);
        namaTujuan = getIntent().getStringExtra("namaTujuan");
        approved = getIntent().getStringExtra("approved");
        gimmick=getIntent().getStringExtra("gimmick");
        loanType=getIntent().getStringExtra("loanType");

        if(getIntent().hasExtra("idProgram")){
            idProgram=getIntent().getStringExtra("idProgram");
        }

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Sektor Ekonomi");

        if (approved.equalsIgnoreCase("no")) {
            AppUtil.dynamicIconLogoChange(SektorEkonomiKprActivity.this, et_tujuanpenggunaan, tf_tujuanpenggunaan);
//            setDynamicIconDropDown();
        }
        et_tujuanpenggunaan.setText(namaTujuan);

        //TUJUAN PENGGUNAAN

        if (approved.equalsIgnoreCase("yes")){
            btn_send.setVisibility(View.GONE);
            AppUtil.disableEditTexts(findViewById(android.R.id.content));
        }

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm())
                    sendData();
            }
        });
    }

    private void setDynamicIconDropDown(){
        AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this, tf_bidangusaha, et_bidangusaha);
        AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this,tf_sifatpembiayaan,et_sifatpembiayaan);
        AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this, tf_jenispenggunaan ,et_jenispenggunaan);
        AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this, tf_jenispenggunaanlbu ,et_jenispenggunaanlbu);
        AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this, tf_jenispembiayaanlbu ,et_jenispembiayaanlbu);
        AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this, tf_sifatpembiayaanlbu ,et_sifatpembiayaanlbu);
        AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this, tf_sektorekonomi ,et_sektorekonomi);
        AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this, tf_hubungannasabahdgnbank ,et_hubungannasabahdgnbank);
        AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this, tf_kategoripembiayaanlbu ,et_kategoripembiayaanlbu);

        AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this, tf_klasifikasi_kpr ,et_klasifikasi_kpr);
        AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this, tf_sumber_aplikasi ,et_sumber_aplikasi);
        AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this, tf_tujuan_buka_rekening ,et_tujuan_membuka_rekening);
        AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this, tf_referensi ,et_referensi);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sm_placeholder.startShimmer();
        loadData();
        onSelectDialog();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm_placeholder.stopShimmer();
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void loadData() {
        inquirySektorEkonomi req = new inquirySektorEkonomi(idAplikasi, appPreferences.getFidRole());
        Call<ParseResponse> call;

        //pantek flag amanah, karena servisnya masih pisah dari yang udah dapet flag
//        Toast.makeText(this, "Ada pantekan flag amanah jadi true", Toast.LENGTH_SHORT).show();
//        appPreferences.setCbAmanah(AppUtil.encrypt("true"));

        if(idProgram.equalsIgnoreCase("222")&&appPreferences.getCbAmanah().equalsIgnoreCase("true")){
            call = apiClientAdapter.getApiInterface().inquirySektorEkonomiFlpp(req);
            //ganti warna field
            tf_jenispenggunaan.setPanelBackgroundColor(getResources().getColor(R.color.colorBgEdittext));
            tf_jenispenggunaanlbu.setPanelBackgroundColor(getResources().getColor(R.color.colorBgEdittext));
        }
        else{
            call = apiClientAdapter.getApiInterface().inquirySektorEkonomiKpr(req);
            //ganti warna field
            tf_jenispenggunaan.setPanelBackgroundColor(getResources().getColor(R.color.colorDisableEdit));
            tf_jenispenggunaanlbu.setPanelBackgroundColor(getResources().getColor(R.color.colorDisableEdit));
        }

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){

                            Gson gson = new Gson();
                            Type typeCodeDesc = new TypeToken<List<CodeDesc>>() {}.getType();

                            dtKatLBUString = response.body().getData().get("dtKatLBU").toString();
                            dtKatLBU = gson.fromJson(dtKatLBUString, typeCodeDesc);

                            dtJenisPenggunaanLbuString = response.body().getData().get("dtJenisPenggunaanLbu").toString();
                            dtJenisPenggunaanLbu = gson.fromJson(dtJenisPenggunaanLbuString, typeCodeDesc);

                            dtSifatPbyLBUString = response.body().getData().get("dtSifatPbyLBU").toString();
                            dtSifatPbyLBU = gson.fromJson(dtSifatPbyLBUString, typeCodeDesc);

                            dtSifatPbyString = response.body().getData().get("dtSifatPby").toString();
                            dtSifatPby = gson.fromJson(dtSifatPbyString, typeCodeDesc);

                            try{
                                dtBidangUsahaString = response.body().getData().get("dtBidangUsaha").toString();
                                dtBidangUsaha = gson.fromJson(dtBidangUsahaString, typeCodeDesc);
                            }
                            catch (Exception e){
                                Log.d("ada error di :","dtbidangusaha");
                            }


                            dtJenisPenggunaanString = response.body().getData().get("dtJenisPenggunaan").toString();
                            dtJenisPenggunaan = gson.fromJson(dtJenisPenggunaanString, typeCodeDesc);

                            dtJenisKreditLbuString = response.body().getData().get("dtJenisKreditLbu").toString();
                            dtJenisKreditLbu = gson.fromJson(dtJenisKreditLbuString, typeCodeDesc);

//                            dtKatLBUString = response.body().getData().get("dtKatLBU").toString();
//                            dtKatLBU = gson.fromJson(dtKatLBUString, typeCodeDesc);

//
//                            try{
//                                dtHubDebiturString = response.body().getData().get("dtHubDebitur").toString();
//                                dtHubDebitur = gson.fromJson(dtHubDebiturString, typeCodeDesc);
//                            }
//                            catch (Exception e){
//                                Log.d("ada error di :","dthubdebitur"+e);
//                            }

                            try{
                                dtKlasifikasiKprString = response.body().getData().get("dtKlasifikasiKPR").toString();
                                dtKlasifikasiKpr = gson.fromJson(dtKlasifikasiKprString, typeCodeDesc);
                            }
                            catch (Exception e){
                                Log.d("ada error di :","dtklasifikasikpr");
                            }

                            try{
                                dtSumberAplikasiString = response.body().getData().get("dtSumberAplikasi").toString();
                                dtSumberAplikasi = gson.fromJson(dtSumberAplikasiString, typeCodeDesc);
                            }
                            catch (Exception e){
                                Log.d("ada error di :","dtsumberaplikasi");
                            }

                            try{
                                dtReferensiString = response.body().getData().get("dtReferensi").toString();
                                dtReferensi = gson.fromJson(dtReferensiString, typeCodeDesc);
                            }
                            catch (Exception e){
                                Log.d("ada error di :","dtreferensi");
                            }

                            try{
                                dtTujuanMembukaRekeningString = response.body().getData().get("dtTujuanBukaRekening").toString();
                                dtTujuanMembukaRekening = gson.fromJson(dtTujuanMembukaRekeningString, typeCodeDesc);
                            }
                            catch (Exception e){
                                Log.d("ada error di :","dttujuanbukarekening");
                            }


                            dataPbySebelumPutusanString = response.body().getData().get("dataPbySebelumPutusan").toString();
                            dataPbySebelumPutusan = gson.fromJson(dataPbySebelumPutusanString, MDataPembiayaanKpr.class);
                            setData();
                            onSelectDialog();

                            if (approved.equalsIgnoreCase("no")) {
                                setDynamicIconDropDown();
                            }

                        }
                        else{
                            finish();
                            AppUtil.notiferror(SektorEkonomiKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(SektorEkonomiKprActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                finish();
                AppUtil.notiferror(SektorEkonomiKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void setData(){

        if(dataPbySebelumPutusan.getBidangUsahaText()!=null){
            et_bidangusaha.setText(dataPbySebelumPutusan.getBidangUsahaText());
            val_bidangUsaha = dataPbySebelumPutusan.getBidangUsaha();
        }

        if(dataPbySebelumPutusan.getSifatKreditText()!=null){
            et_sifatpembiayaan.setText(dataPbySebelumPutusan.getSifatKreditText());
            val_sifatKredit = dataPbySebelumPutusan.getSifatKredit();
        }


        if(dataPbySebelumPutusan.getJenisPenggunaanText()!=null){
            et_jenispenggunaan.setText(dataPbySebelumPutusan.getJenisPenggunaanText());
            val_jenisPenggunaan = dataPbySebelumPutusan.getJenisPenggunaan();
        }

        if(dataPbySebelumPutusan.getJenisPenggunaanLBUText()!=null){
            et_jenispenggunaanlbu.setText(dataPbySebelumPutusan.getJenisPenggunaanLBUText());
            val_jenisPenggunaanLBU = dataPbySebelumPutusan.getJenisPenggunaanLBU();
        }

        if(dataPbySebelumPutusan.getHubDebiturDgnBankText()!=null){
            et_hubungannasabahdgnbank.setText(dataPbySebelumPutusan.getHubDebiturDgnBankText());

            val_hubunganNasabahdgnBank = dataPbySebelumPutusan.getHubDebiturDgnBank();
        }

        if(dataPbySebelumPutusan.getKlasifikasiKPR()!=null){
            et_klasifikasi_kpr.setText(dataPbySebelumPutusan.getKlasifikasiKPR());
            val_klasifikasi_kpr = dataPbySebelumPutusan.getKlasifikasiKPR();
        }

        if(dataPbySebelumPutusan.getSumberAplikasi()!=null){
            et_sumber_aplikasi.setText(dataPbySebelumPutusan.getSumberAplikasi());
            val_sumber_aplikasi = dataPbySebelumPutusan.getSumberAplikasi();
        }

        //dapet kembalian tujuan membuka rekening tanpa bentuk teksnya, jadi harus dicari pasangan keynya dari objek dtReferensi
        if(dataPbySebelumPutusan.getTujuanMembukaRekening()!=null){
//            et_tujuan_membuka_rekening.setText(dataPbySebelumPutusan.getTujuanMembukaRekening());
            for (int i = 0; i <dtTujuanMembukaRekening.size() ; i++) {
                Log.d("clockworkmachine",dataPbySebelumPutusan.getTujuanMembukaRekening()+" "+dtTujuanMembukaRekening.get(i).getDesc1());
                if(dataPbySebelumPutusan.getTujuanMembukaRekening().equalsIgnoreCase(dtTujuanMembukaRekening.get(i).getDesc1())){
                    Log.d("clockworkmachineHIT!",dataPbySebelumPutusan.getTujuanMembukaRekening()+" "+dtTujuanMembukaRekening.get(i).getDesc2());
                    et_tujuan_membuka_rekening.setText(dtTujuanMembukaRekening.get(i).getDesc2());
                    break;

                }

            }
            valTujuanMembukaRekening = Integer.parseInt(dataPbySebelumPutusan.getTujuanMembukaRekening());
        }

        //dapet kembalian referensi tanpa bentuk teksnya, jadi harus dicari pasangan keynya dari objek dtReferensi
        if(dataPbySebelumPutusan.getReferensi()!=null){
//            et_tujuan_membuka_rekening.setText(dataPbySebelumPutusan.getTujuanMembukaRekening());
            for (int i = 0; i <dtReferensi.size() ; i++) {
                if(dataPbySebelumPutusan.getReferensi().equalsIgnoreCase(dtReferensi.get(i).getDesc1())){
                    et_referensi.setText(dtReferensi.get(i).getDesc2());
                    break;

                }

            }
            val_referensi = Integer.parseInt(dataPbySebelumPutusan.getReferensi());
        }


        if(dataPbySebelumPutusan.getKategoriKreditLBUText()!=null){
            et_kategoripembiayaanlbu.setText(dataPbySebelumPutusan.getKategoriKreditLBUText());
            val_kategoriKreditLBU=dataPbySebelumPutusan.getKategoriKreditLBU();
        }



        if(dataPbySebelumPutusan.getSektorEkonomiText()!=null){
            et_sektorekonomi.setText(dataPbySebelumPutusan.getSektorEkonomiText());
        }

        if(dataPbySebelumPutusan.getJenisKreditLBUText()!=null){
            et_jenispembiayaanlbu.setText(dataPbySebelumPutusan.getJenisKreditLBUText());
        }

        if(dataPbySebelumPutusan.getSifatKreditLBUText()!=null){
            et_sifatpembiayaanlbu.setText(dataPbySebelumPutusan.getSifatKreditLBUText());
        }







//        et_kategoripembiayaanlbu.setText(dataPbySebelumPutusan.getKategoriKreditLBUText());








        //pantekan khusus purna dan prapurna
        //di set default valuenya jika belum pernah milih bidang usaha
//        if(val_bidangUsaha==null||val_bidangUsaha.equalsIgnoreCase("")){
//            if(loanType.equalsIgnoreCase("125")){
//                et_bidangusaha.setText("9990 - Lainnya");
//                val_bidangUsaha="9990";
//            }
//        }

        if(dataPbySebelumPutusan.getJenisKreditLBU()!=null){
            val_jenisKreditLBU = Integer.parseInt(dataPbySebelumPutusan.getJenisKreditLBU());
        }
        if(dataPbySebelumPutusan.getSifatKreditLBU()!=null){
            val_sifatKreditLBU = Integer.parseInt(dataPbySebelumPutusan.getSifatKreditLBU());
        }

//        val_kategoriKreditLBU = dataPbySebelumPutusan.getKategoriKreditLBU();
        val_sektorEkonomiSID = dataPbySebelumPutusan.getSektorEkonomiSID();
        val_sektorEkonomiLBU = dataPbySebelumPutusan.getSektorEkonomiLBU();


    }

    public void onSelectDialog(){

//        et_tujuanpenggunaan.setInputType(InputType.TYPE_NULL);
        et_tujuanpenggunaan.setFocusable(false);

        //BIDANG USAHA

        if (approved.equalsIgnoreCase("no")){

//            //BIDANG USAHA
            autoOnClickForDropdown(tf_bidangusaha,et_bidangusaha,dtBidangUsaha);


            //SIFAT PEMBIAYAAN
            autoOnClickForDropdown(tf_sifatpembiayaan,et_sifatpembiayaan,dtSifatPby);

            //SIFAT PEMBIAYAAN LBU
            autoOnClickForDropdown(tf_sifatpembiayaanlbu,et_sifatpembiayaanlbu,dtSifatPbyLBU);

            if(idProgram.equalsIgnoreCase("222")&&appPreferences.getCbAmanah().equalsIgnoreCase("true")){
                //JENIS PENGGUNAAN
                autoOnClickForDropdown(tf_jenispenggunaan,et_jenispenggunaan,dtJenisPenggunaan);

                //JENIS PENGGUNAAN LBU
                autoOnClickForDropdown(tf_jenispenggunaanlbu,et_jenispenggunaanlbu,dtJenisPenggunaanLbu);
            }


//            //HUB. NASABAH DGN BANK
//            autoOnClickForDropdown(tf_hubungannasabahdgnbank,et_hubungannasabahdgnbank,dtHubDebitur);

            //KLASIFIKASI KPR
            autoOnClickForDropdown(tf_klasifikasi_kpr,et_klasifikasi_kpr,dtKlasifikasiKpr);

            //SUMBER APLIKASI
            autoOnClickForDropdown(tf_sumber_aplikasi,et_sumber_aplikasi,dtSumberAplikasi);

            //TUJUAN MEMBUKA REKENING
            autoOnClickForDropdown(tf_tujuan_buka_rekening,et_tujuan_membuka_rekening,dtTujuanMembukaRekening);

            //REFERENSI
            autoOnClickForDropdown(tf_referensi,et_referensi,dtReferensi);

            //KATEGORI PEMBIAYAAN LBU
//            autoOnClickForDropdown(tf_kategoripembiayaanlbu,et_kategoripembiayaanlbu,dtKatLBU);


            //SEKTOR EKONOMI

            et_sektorekonomi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (et_jenispenggunaanlbu.getText().toString().isEmpty() || et_jenispenggunaanlbu.getText().toString().equalsIgnoreCase(""))
                    {
                        AppUtil.showToastShort(SektorEkonomiKprActivity.this, "Pilih Jenis Penggunaan LBU terlebih dahulu");
                    }
                    else{
                        DialogSektorEkonomi.display(getSupportFragmentManager(), idAplikasi, et_jenispenggunaanlbu.getText().toString().trim(),  SektorEkonomiKprActivity.this);
                    }
                }
            });
            et_sektorekonomi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (et_jenispenggunaanlbu.getText().toString().isEmpty() || et_jenispenggunaanlbu.getText().toString().equalsIgnoreCase(""))
                    {
                        AppUtil.showToastShort(SektorEkonomiKprActivity.this, "Pilih Jenis Penggunaan LBU terlebih dahulu");
                    }
                    else{
                        DialogSektorEkonomi.display(getSupportFragmentManager(), idAplikasi, et_jenispenggunaanlbu.getText().toString().trim(),  SektorEkonomiKprActivity.this);
                    }
                }
            });
            tf_sektorekonomi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (et_jenispenggunaanlbu.getText().toString().isEmpty() || et_jenispenggunaanlbu.getText().toString().equalsIgnoreCase(""))
                    {
                        AppUtil.showToastShort(SektorEkonomiKprActivity.this, "Pilih Jenis Penggunaan LBU terlebih dahulu");
                    }
                    else{
                        DialogSektorEkonomi.display(getSupportFragmentManager(), idAplikasi, et_jenispenggunaanlbu.getText().toString().trim(),  SektorEkonomiKprActivity.this);
                    }
                }
            });


            tf_sektorekonomi.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (et_jenispenggunaanlbu.getText().toString().isEmpty() || et_jenispenggunaanlbu.getText().toString().equalsIgnoreCase(""))
                    {
                        AppUtil.showToastShort(SektorEkonomiKprActivity.this, "Pilih Jenis Penggunaan LBU terlebih dahulu");
                    }
                    else{
                        DialogSektorEkonomi.display(getSupportFragmentManager(), idAplikasi, et_jenispenggunaanlbu.getText().toString().trim(),  SektorEkonomiKprActivity.this);
                    }
                }
            });

            //KATEGORI PEMBIAYAAN LBU
            et_kategoripembiayaanlbu.setFocusable(false);
//        et_kategoripembiayaanlbu.setInputType(InputType.TYPE_NULL);
            et_kategoripembiayaanlbu.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    DialogDescCode.display(getSupportFragmentManager(), tf_kategoripembiayaanlbu.getLabelText().toString().trim(), dtKatLBU, SektorEkonomiKprActivity.this);
                }
            });
            tf_kategoripembiayaanlbu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogDescCode.display(getSupportFragmentManager(), tf_kategoripembiayaanlbu.getLabelText().toString().trim(), dtKatLBU, SektorEkonomiKprActivity.this);
                }
            });
            tf_kategoripembiayaanlbu.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogDescCode.display(getSupportFragmentManager(), tf_kategoripembiayaanlbu.getLabelText().toString().trim(), dtKatLBU, SektorEkonomiKprActivity.this);
                }
            });

            //BODANG USAHA
            et_bidangusaha.setFocusable(false);
//        et_kategoripembiayaanlbu.setInputType(InputType.TYPE_NULL);
            et_bidangusaha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    DialogDescCode.display(getSupportFragmentManager(), tf_bidangusaha.getLabelText().toString().trim(), dtBidangUsaha, SektorEkonomiKprActivity.this);
                }
            });
            tf_bidangusaha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogDescCode.display(getSupportFragmentManager(), tf_bidangusaha.getLabelText().toString().trim(), dtBidangUsaha, SektorEkonomiKprActivity.this);
                }
            });
            tf_bidangusaha.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogDescCode.display(getSupportFragmentManager(), tf_bidangusaha.getLabelText().toString().trim(), dtBidangUsaha, SektorEkonomiKprActivity.this);
                }
            });



        }





    }

    public void autoOnClickForDropdown(TextFieldBoxes textFieldBoxes, EditText editText,List<CodeDesc> codeDescs){

        editText.setFocusable(false);
//        et_kategoripembiayaanlbu.setInputType(InputType.TYPE_NULL);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogDescCode.display(getSupportFragmentManager(), textFieldBoxes.getLabelText().toString().trim(), codeDescs, SektorEkonomiKprActivity.this);
            }
        });
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDescCode.display(getSupportFragmentManager(), textFieldBoxes.getLabelText().toString().trim(), codeDescs, SektorEkonomiKprActivity.this);
            }
        });
        textFieldBoxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDescCode.display(getSupportFragmentManager(), textFieldBoxes.getLabelText().toString().trim(), codeDescs, SektorEkonomiKprActivity.this);
            }
        });
        textFieldBoxes.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDescCode.display(getSupportFragmentManager(), textFieldBoxes.getLabelText().toString().trim(), codeDescs, SektorEkonomiKprActivity.this);
            }
        });

    }


    public void sendData(){
        loading.setVisibility(View.VISIBLE);
        InputSektorEkonomiKpr req = new InputSektorEkonomiKpr(idAplikasi, cifLas, val_hubunganNasabahdgnBank, val_bidangUsaha, val_sifatKredit, val_jenisPenggunaan, val_jenisPenggunaanLBU, val_jenisKreditLBU, val_sifatKreditLBU, val_kategoriKreditLBU, val_sektorEkonomiSID, val_sektorEkonomiLBU);

        req.setKlasifikasiKPR(val_klasifikasi_kpr);
        req.setSumberAplikasi(val_sumber_aplikasi);
        req.setReferensi(val_referensi);
        req.setTujuanMembukaRekening(valTujuanMembukaRekening);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendDataSektorEkonomiKpr(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            CustomDialog.DialogSuccess(SektorEkonomiKprActivity.this, "Success!", "Input Data Sektor Ekonomi Sukses", SektorEkonomiKprActivity.this);
                        }
                        else{
                            AppUtil.notiferror(SektorEkonomiKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(SektorEkonomiKprActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(SektorEkonomiKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });


    }

    public boolean validateForm(){
        if (et_tujuanpenggunaan.getText().toString().isEmpty() || et_tujuanpenggunaan.getText().toString().equalsIgnoreCase("")){
            tf_tujuanpenggunaan.setError(tf_tujuanpenggunaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_bidangusaha.getText().toString().isEmpty() || et_bidangusaha.getText().toString().equalsIgnoreCase("")){
            tf_bidangusaha.setError(tf_bidangusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_sifatpembiayaan.getText().toString().isEmpty() || et_sifatpembiayaan.getText().toString().equalsIgnoreCase("")){
            tf_sifatpembiayaan.setError(tf_sifatpembiayaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_jenispenggunaan.getText().toString().isEmpty() || et_jenispenggunaan.getText().toString().equalsIgnoreCase("")){
            tf_jenispenggunaan.setError(tf_jenispenggunaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_jenispenggunaanlbu.getText().toString().isEmpty() || et_jenispenggunaanlbu.getText().toString().equalsIgnoreCase("")){
            tf_jenispenggunaanlbu.setError(tf_jenispenggunaanlbu.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_jenispembiayaanlbu.getText().toString().isEmpty() || et_jenispembiayaanlbu.getText().toString().equalsIgnoreCase("")){
            tf_jenispembiayaanlbu.setError(tf_jenispembiayaanlbu.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_sifatpembiayaanlbu.getText().toString().isEmpty() || et_sifatpembiayaanlbu.getText().toString().equalsIgnoreCase("")){
            tf_sifatpembiayaanlbu.setError(tf_sifatpembiayaanlbu.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_kategoripembiayaanlbu.getText().toString().isEmpty() || et_kategoripembiayaanlbu.getText().toString().equalsIgnoreCase("")){
            tf_kategoripembiayaanlbu.setError(tf_kategoripembiayaanlbu.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_sektorekonomi.getText().toString().isEmpty() || et_sektorekonomi.getText().toString().equalsIgnoreCase("")){
            tf_sektorekonomi.setError(tf_sektorekonomi.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
//        else if (et_hubungannasabahdgnbank.getText().toString().isEmpty() || et_hubungannasabahdgnbank.getText().toString().equalsIgnoreCase("")){
//            tf_hubungannasabahdgnbank.setError(tf_hubungannasabahdgnbank.getLabelText()+" "+getString(R.string.title_validate_field), true);
//            return false;
//        }


        else if (et_klasifikasi_kpr.getText().toString().isEmpty() || et_klasifikasi_kpr.getText().toString().equalsIgnoreCase("")){
            tf_klasifikasi_kpr.setError(tf_klasifikasi_kpr.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }

        else if (et_sumber_aplikasi.getText().toString().isEmpty() || et_sumber_aplikasi.getText().toString().equalsIgnoreCase("")){
            tf_sumber_aplikasi.setError(tf_sumber_aplikasi.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }

        else if (et_tujuan_membuka_rekening.getText().toString().isEmpty() || et_tujuan_membuka_rekening.getText().toString().equalsIgnoreCase("")){
            tf_tujuan_buka_rekening.setError(tf_tujuan_buka_rekening.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }

        else if (et_referensi.getText().toString().isEmpty() || et_referensi.getText().toString().equalsIgnoreCase("")){
            tf_referensi.setError(tf_referensi.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        return true;
    }


    @Override
    public void onSelect(String title, CodeDesc data) {
        if (title.equalsIgnoreCase(tf_bidangusaha.getLabelText().toString().trim())){
            et_bidangusaha.setText(data.getDesc2());
            val_bidangUsaha = data.getDesc1();
            AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this,tf_bidangusaha);
        }
        else if (title.equalsIgnoreCase(tf_sifatpembiayaan.getLabelText().toString().trim())){
            et_sifatpembiayaan.setText(data.getDesc2());
            val_sifatKredit = data.getDesc1();
            AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this,tf_sifatpembiayaan);
        }
        else if (title.equalsIgnoreCase(tf_jenispenggunaan.getLabelText().toString().trim())){
            et_jenispenggunaan.setText(data.getDesc2());
            val_jenisPenggunaan = data.getDesc1();
            AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this,tf_jenispenggunaan);
        }
        else if (title.equalsIgnoreCase(tf_jenispenggunaanlbu.getLabelText().toString().trim())){
            et_jenispenggunaanlbu.setText(data.getDesc2());
            val_jenisPenggunaanLBU = data.getDesc1();
            AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this,tf_jenispenggunaanlbu);
        }
        else if (title.equalsIgnoreCase(tf_jenispembiayaanlbu.getLabelText().toString().trim())){
            et_jenispembiayaanlbu.setText(data.getDesc2());
            val_jenisKreditLBU = AppUtil.parseIntWithDefault(data.getDesc1(), 0);
            AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this,tf_jenispembiayaanlbu);
        }
        else if (title.equalsIgnoreCase(tf_sifatpembiayaanlbu.getLabelText().toString().trim())){
            et_sifatpembiayaanlbu.setText(data.getDesc2());
            val_sifatKreditLBU = AppUtil.parseIntWithDefault(data.getDesc1(), 0);
            AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this,tf_sifatpembiayaanlbu);
        }
        else if (title.equalsIgnoreCase(tf_kategoripembiayaanlbu.getLabelText().toString().trim())){
            et_kategoripembiayaanlbu.setText(data.getDesc2());
            val_kategoriKreditLBU =data.getDesc1();
            AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this,tf_kategoripembiayaanlbu);
        }
        else if (title.equalsIgnoreCase(tf_hubungannasabahdgnbank.getLabelText().toString().trim())){
            et_hubungannasabahdgnbank.setText(data.getDesc2());
            val_hubunganNasabahdgnBank = data.getDesc1();
            AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this,tf_hubungannasabahdgnbank);
        }

        else if (title.equalsIgnoreCase(tf_klasifikasi_kpr.getLabelText().toString().trim())){
            et_klasifikasi_kpr.setText(data.getDesc2());
            val_klasifikasi_kpr = data.getDesc1();
            AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this,tf_klasifikasi_kpr);
        }
        else if (title.equalsIgnoreCase(tf_sumber_aplikasi.getLabelText().toString().trim())){
            et_sumber_aplikasi.setText(data.getDesc2());
            val_sumber_aplikasi = data.getDesc1();
            AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this,tf_sumber_aplikasi);
        }
        else if (title.equalsIgnoreCase(tf_tujuan_buka_rekening.getLabelText().toString().trim())){
            et_tujuan_membuka_rekening.setText(data.getDesc2());
            valTujuanMembukaRekening = Integer.parseInt(data.getDesc1());
            AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this,tf_tujuan_buka_rekening);
        }
        else if (title.equalsIgnoreCase(tf_referensi.getLabelText().toString().trim())){
            et_referensi.setText(data.getDesc2());
            val_referensi = Integer.parseInt(data.getDesc1());
            AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this,tf_referensi);
        }

    }

    @Override
    public void onSectorSelect(MsektorEkonomi data) {
        et_sektorekonomi.setText(data.getSektorEkonomiText());
        AppUtil.dynamicIconLogoChangeDropdown(SektorEkonomiKprActivity.this,tf_sektorekonomi);
        val_sektorEkonomiSID = data.getSektorEkonomiSID();
        val_sektorEkonomiLBU = data.getSektorEkonomiLBU();
    }

    @Override
    public void success(boolean val) {
        if(val)
            finish();
    }

    @Override
    public void confirm(boolean val) {

    }
}
