package com.application.bris.ikurma_nos_konsumer.page_aom.view.pipeline.kpr;


import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseSaldo;
import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.model.request.flpp.ReqDataSikasep;
import com.application.bris.ikurma_nos_konsumer.api.model.request.flpp.ReqPihakKetiga;
import com.application.bris.ikurma_nos_konsumer.api.model.request.monitoring.ReqSaldoNasabah;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.KonsumerKPRInputPipeline;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.inquiryInstansi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.inquiryProject;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.model.ProjectModel;
import com.application.bris.ikurma_nos_konsumer.model.monitoring.SaldoNasabah;
import com.application.bris.ikurma_nos_konsumer.model.pipeline.KonsumerKPRDetailPipeline;
import com.application.bris.ikurma_nos_konsumer.page_aom.adapter.tindaklanjut.TindaklanjutAdapater;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogAddress;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogInputDataPipelineKPR;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogProject;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.AddressListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.LoadDataInputPipelineKPRListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ProjectListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataSikasep;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListBidangPekerjaan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListJenisKPR;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListPihakKetiga;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListProject;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MTujuanPenggunaan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.PihakKetiga;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.RekeningAmanah;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.address;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.tindaklanjut;
import com.application.bris.ikurma_nos_konsumer.util.AppBarStateChangedListener;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.ImageHandler;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherForThousand;
import com.application.bris.ikurma_nos_konsumer.util.Validator;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import static android.view.View.GONE;

public class KonsumerKPRPipelineEditActivity extends AppCompatActivity implements
        View.OnClickListener, View.OnFocusChangeListener, LoadDataInputPipelineKPRListener, ProjectListener, KeyValueListener, AddressListener, CameraListener, ConfirmListener, TextWatcher {

    @BindView(R.id.tb_custom)
    Toolbar toolbar;
    @BindView(R.id.tv_page_title)
    TextView tv_page_title;
    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    @BindView(R.id.btn_takepicture)
    ImageView btn_takepicture;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.btn_pickaddress)
    Button btn_pickaddress;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    //Data Pembiayaan
    @BindView(R.id.tf_segmen)
    TextFieldBoxes tf_segmen;
    @BindView(R.id.et_segmen)
    EditText et_segmen;

    @BindView(R.id.tf_produk)
    TextFieldBoxes tf_produk;
    @BindView(R.id.et_produk)
    EditText et_produk;

    @BindView(R.id.tf_paket)
    TextFieldBoxes tf_paket;
    @BindView(R.id.et_paket)
    EditText et_paket;

    @BindView(R.id.tf_program)
    TextFieldBoxes tf_program;
    @BindView(R.id.et_program)
    EditText et_program;

    @BindView(R.id.tf_no_rekening_developer)
    TextFieldBoxes tf_no_rekening_developer;
    @BindView(R.id.et_no_rekening_developer)
    EditText et_no_rekening_developer;

    @BindView(R.id.tf_pihak_ketiga)
    TextFieldBoxes tf_pihak_ketiga;
    @BindView(R.id.et_pihak_ketiga)
    EditText et_pihak_ketiga;

    @BindView(R.id.tf_proyek_perumahan)
    TextFieldBoxes tf_proyek_perumahan;
    @BindView(R.id.et_proyek_perumahan)
    EditText et_proyek_perumahan;

    @BindView(R.id.tf_tujuanpenggunaan)
    TextFieldBoxes tf_tujuanpenggunaan;
    @BindView(R.id.et_tujuanpenggunaan)
    EditText et_tujuanpenggunaan;

    @BindView(R.id.tf_plafond)
    TextFieldBoxes tf_plafond;
    @BindView(R.id.et_plafond)
    EditText et_plafond;

    @BindView(R.id.tf_tenor)
    TextFieldBoxes tf_tenor;
    @BindView(R.id.et_tenor)
    EditText et_tenor;

    //Data Pribadi
    @BindView(R.id.tf_nik)
    TextFieldBoxes tf_nik;
    @BindView(R.id.et_nik)
    EditText et_nik;

    @BindView(R.id.tf_nama)
    TextFieldBoxes tf_nama;
    @BindView(R.id.et_nama)
    EditText et_nama;

    @BindView(R.id.tf_tempatlahir)
    TextFieldBoxes tf_tempatlahir;
    @BindView(R.id.et_tempatlahir)
    EditText et_tempatlahir;

    @BindView(R.id.tf_tanggallahir)
    TextFieldBoxes tf_tanggallahir;
    @BindView(R.id.et_tanggallahir)
    EditText et_tanggallahir;

    @BindView(R.id.tf_nomorhp)
    TextFieldBoxes tf_nomorhp;
    @BindView(R.id.et_nomorhp)
    EditText et_nomorhp;

//    @BindView(R.id.tf_instansi)
//    TextFieldBoxes tf_instansi;
//    @BindView(R.id.et_instansi)
//    EditText et_instansi;

    @BindView(R.id.tf_bidang_pekerjaan)
    TextFieldBoxes tf_bidang_pekerjaan;
    @BindView(R.id.et_bidang_pekerjaan)
    EditText et_bidang_pekerjaan;

    @BindView(R.id.tf_jenis_pekerjaan)
    TextFieldBoxes tf_jenis_pekerjaan;
    @BindView(R.id.et_jenis_pekerjaan)
    EditText et_jenis_pekerjaan;

    @BindView(R.id.tf_pendapatan)
    TextFieldBoxes tf_pendapatan;
    @BindView(R.id.et_pendapatan)
    EditText et_pendapatan;

    //Data Alamat
    @BindView(R.id.tf_alamat)
    TextFieldBoxes tf_alamat;
    @BindView(R.id.et_alamat)
    EditText et_alamat;

    @BindView(R.id.tf_rt)
    TextFieldBoxes tf_rt;
    @BindView(R.id.et_rt)
    EditText et_rt;

    @BindView(R.id.tf_rw)
    TextFieldBoxes tf_rw;
    @BindView(R.id.et_rw)
    EditText et_rw;

    @BindView(R.id.tf_provinsi)
    TextFieldBoxes tf_provinsi;
    @BindView(R.id.et_provinsi)
    EditText et_provinsi;

    @BindView(R.id.tf_kota)
    TextFieldBoxes tf_kota;
    @BindView(R.id.et_kota)
    EditText et_kota;

    @BindView(R.id.tf_kecamatan)
    TextFieldBoxes tf_kecamatan;
    @BindView(R.id.et_kecamatan)
    EditText et_kecamatan;

    @BindView(R.id.tf_kelurahan)
    TextFieldBoxes tf_kelurahan;
    @BindView(R.id.et_kelurahan)
    EditText et_kelurahan;

    @BindView(R.id.tf_kodepos)
    TextFieldBoxes tf_kodepos;
    @BindView(R.id.et_kodepos)
    EditText et_kodepos;

    @BindView(R.id.tf_pihak_ketiga_sikasep)
    TextFieldBoxes tf_pihak_ketiga_sikasep;
    @BindView(R.id.et_pihak_ketiga_sikasep)
    EditText et_pihak_ketiga_sikasep;

    @BindView(R.id.tf_proyek_perumahan_sikasep)
    TextFieldBoxes tf_proyek_perumahan_sikasep;
    @BindView(R.id.et_proyek_perumahan_sikasep)
    EditText et_proyek_perumahan_sikasep;

    @BindView(R.id.tf_npwp_developer)
    TextFieldBoxes tf_npwp_developer;
    @BindView(R.id.et_npwp_developer)
    EditText et_npwp_developer;

    //Tindak lanjut
    @BindView(R.id.rg_typetindaklanjut)
    RadioGroup rg_typetindaklanjut;

    @BindView(R.id.rb_byvisit)
    RadioButton rb_byvisit;

    @BindView(R.id.rb_bycall)
    RadioButton rb_bycall;

    @BindView(R.id.et_tindaklanjut)
    EditText et_tindaklanjut;
    @BindView(R.id.rv_history_tindaklanjut)
    RecyclerView rv_history_tindaklanjut;
    @BindView(R.id.ll_emptydata)
    LinearLayout ll_emptydata;

    @BindView(R.id.ll_rekening_developer)
    LinearLayout ll_rekening_developer;
    @BindView(R.id.tv_status_rekening_developer)
    TextView tv_status_rekening_developer;
    @BindView(R.id.bt_cek_rekening_developer)
    Button bt_cek_rekening_developer;

    @BindView(R.id.ll_ishotprospek)
    LinearLayout ll_ishotprospek;
    @BindView(R.id.sw_ishotprospek)
    Switch sw_ishotprospek;


    @BindView(R.id.btnfab_takepicture)
    FloatingActionButton btnfab_takepicture;

    private DatePickerDialog dp_tanggallahir;
    private Calendar calendar;
    private Calendar calendarMinDate;

    private final int PICK_IMAGE = 1;
    private final int TAKE_PICTURE = 0;
    private int CHANGE_PICTURE = 0;
    private Uri uriPhoto;
    private Bitmap bitmapPhoto;
    private int hasFace = 0;
    private boolean rekeningDeveloperBerubah=true;
    private String cleanNamaPihakKetigaSikasep;
    private String cleanNamaPihakKetiga;

    private int isHotprospek=0;

    //for npwp formatting
    int dot1=0;
    int dot2=0;
    int dot3=0;
    int dot4=0;
    int dot5=0;

    //VALUE
    private static String val_segmen = "";
    private static String val_produk = "";
    private static int val_program = 0;
    private static int val_pihak_ketiga = 0;
    private static int val_project = 0;
    private static int val_tujuanpenggunaan = 0;
    private static String val_desc_tujuan = "";
    private static String val_plafond = "";
    private static String val_tenor = "";
    private static String val_nik = "";
    private static String val_nama = "";
    private static String val_tempatlahir = "";
    private static String val_tanggallahir = "";
    private static String val_nomorhp = "";
    private static String val_alamat = "";
    private static String val_rt = "";
    private static String val_rw = "";
    private static String val_provinsi = "";
    private static String val_kota = "";
    private static String val_kecamatan = "";
    private static String val_kelurahan = "";
    private static String val_kodepos = "";
    private static String val_bidang_pekerjaan = "";
    private static String val_jenis_pekerjaan = "";
    private static String val_pendapatan = "";
    private static String val_jenistindaklanjut = "";
    private static String val_texttindaklanjut = "";
    private static String val_gps = "";
    private static String val_urlphoto;

    private List<tindaklanjut> tindaklanjuts;
    private String dataPipeline, dataTindakLanjut;
    private KonsumerKPRDetailPipeline dataPL;
    private List<tindaklanjut> dataTL;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    private List<MListPihakKetiga> listPihakKetiga;
    private ProjectModel projectModel;
    private PihakKetiga dataPihakKetiga;
    private DataSikasep dataSikasep;
    private List<MTujuanPenggunaan> listTujuanPenggunaan;
    private List<MListBidangPekerjaan> listBidangPekerjaan;
    private List<MListJenisKPR> listJenisKPR;
    private SaldoNasabah dataSaldo;
    private RekeningAmanah dataRekeningAmanah;

    private String nama, fid_pihak_ketiga, tipe;
    private int id_project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konsumer_kpr_pipeline_edit);
        ButterKnife.bind(this);
        collapsingToolbar.setStatusBarScrimColor(getResources().getColor(R.color.colorBackgroundTransparent));
        backgroundStatusBar();
        checkCollapse();

        //push up when keyboard shown
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        projectModel = new ProjectModel(this);

        dataPipeline = getIntent().getStringExtra("dataPipeline");
        dataTindakLanjut = getIntent().getStringExtra("dataTindakLanjut");
        iv_photo.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_takepicture.setOnClickListener(this);
        btnfab_takepicture.setOnClickListener(this);
        btn_pickaddress.setOnClickListener(this);
        btn_send.setOnClickListener(this);

        et_segmen.setFocusable(false);
        et_produk.setFocusable(false);
        et_paket.setFocusable(false);
        et_bidang_pekerjaan.setFocusable(false);
        et_jenis_pekerjaan.setFocusable(false);

        tf_rt.setVisibility(View.GONE);
        et_rt.setVisibility(View.GONE);
        tf_rw.setVisibility(View.GONE);
        et_rw.setVisibility(View.GONE);

        rg_typetindaklanjut.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_byvisit:
                        val_jenistindaklanjut = "VISIT";
                        break;
                    case R.id.rb_bycall:
                        val_jenistindaklanjut = "CALL";
                        break;
                }
            }
        });

        loadPihakKetiga();
        loadTujuanPenggunaan();
        loadBidangPekerjaan();
        loadJenisKPR();


        onclickSelectDialog();
        onChangeText();
        setDynamicIcon();

        npwpFormattingTextChange(et_npwp_developer);
        setData();

        if(et_program.getText().toString().toUpperCase().contains("FLPP")){
            if(dataPL.getKode_pihak_ketiga()!=0){
                getPihakKetiga(dataPL.getKode_pihak_ketiga());
            }
            getDataSikasep();
        }

        setDynamicIconDropDown();

        otherViewChanges();



        calendarMinDate = Calendar.getInstance();
        calendarMinDate.add(Calendar.YEAR, -75);
    }

    private void setDynamicIcon() {
        AppUtil.dynamicIconLogoChange(KonsumerKPRPipelineEditActivity.this, et_plafond, tf_plafond);
        AppUtil.dynamicIconLogoChange(KonsumerKPRPipelineEditActivity.this, et_tenor, tf_tenor);
        AppUtil.dynamicIconLogoChange(KonsumerKPRPipelineEditActivity.this, et_nik, tf_nik);
        AppUtil.dynamicIconLogoChange(KonsumerKPRPipelineEditActivity.this, et_nama, tf_nama);
        AppUtil.dynamicIconLogoChange(KonsumerKPRPipelineEditActivity.this, et_tempatlahir, tf_tempatlahir);
        AppUtil.dynamicIconLogoChange(KonsumerKPRPipelineEditActivity.this, et_nomorhp, tf_nomorhp);
        AppUtil.dynamicIconLogoChange(KonsumerKPRPipelineEditActivity.this, et_pendapatan, tf_pendapatan);
    }

    private void setDynamicIconDropDown() {
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPipelineEditActivity.this, tf_segmen, et_segmen);
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPipelineEditActivity.this, tf_produk, et_produk);
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPipelineEditActivity.this, tf_paket, et_paket);
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPipelineEditActivity.this, tf_program, et_program);
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPipelineEditActivity.this, tf_tanggallahir, et_tanggallahir);
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPipelineEditActivity.this, tf_bidang_pekerjaan, et_bidang_pekerjaan);
    }

    private void setData() {
        Gson gson = new Gson();
        dataPL = gson.fromJson(dataPipeline, KonsumerKPRDetailPipeline.class);

        getBidangPekerjaan(dataPL.getBidang_usaha());

        val_urlphoto = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + dataPL.getFid_photo();
        Glide
                .with(KonsumerKPRPipelineEditActivity.this)
                .asBitmap()
                .load(val_urlphoto)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv_photo.setImageBitmap(resource);
                        bitmapPhoto = resource;
                        hasFace = 1;
                    }
                });

        et_segmen.setText(dataPL.getNama_segmen());
        val_segmen = dataPL.getKode_segmen();
        et_produk.setText(dataPL.getNama_produk());
        val_produk = dataPL.getKode_produk();
        et_paket.setText("NON PAKET");
        et_program.setText(dataPL.getNama_gimmick());
        val_program = dataPL.getKode_gimmick();
        et_pihak_ketiga.setText(dataPL.getNama_pihak_ketiga());
        val_pihak_ketiga = dataPL.getKode_pihak_ketiga();
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPipelineEditActivity.this, tf_pihak_ketiga, et_pihak_ketiga);
        et_proyek_perumahan.setText(dataPL.getNama_project());
        val_project = dataPL.getKode_project();
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPipelineEditActivity.this, tf_proyek_perumahan, et_proyek_perumahan);
        et_tujuanpenggunaan.setText(dataPL.getNama_tujuan_penggunaan());
        val_tujuanpenggunaan = dataPL.getKode_tujuan_penggunaan();
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPipelineEditActivity.this, tf_tujuanpenggunaan, et_tujuanpenggunaan);

        et_plafond.setText(Long.toString(Long.valueOf(dataPL.getPlafond())));
        et_tenor.setText(Integer.toString(dataPL.getJw()));
        et_nik.setText(dataPL.getNo_ktp());
        et_nama.setText(dataPL.getNama());
        et_tempatlahir.setText(dataPL.getTempat_lahir());
        et_tanggallahir.setText(AppUtil.parseTanggalLahir(dataPL.getTanggal_lahir()));
        val_tanggallahir = dataPL.getTanggal_lahir();
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPipelineEditActivity.this, tf_tanggallahir, et_tanggallahir);

        et_nomorhp.setText(dataPL.getNo_hp());

//        et_bidang_pekerjaan.setText(dataPL.getBidang_usaha());
        val_bidang_pekerjaan = dataPL.getBidang_usaha();
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPipelineEditActivity.this, tf_bidang_pekerjaan, et_bidang_pekerjaan);
        et_jenis_pekerjaan.setText(dataPL.getJenis_kpr());
        val_jenis_pekerjaan = dataPL.getJenis_kpr();
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPipelineEditActivity.this, tf_jenis_pekerjaan, et_jenis_pekerjaan);
        et_pendapatan.setText(Integer.toString(dataPL.getOmzet_per_hari()));
        et_alamat.setText(dataPL.getAlamat());
        et_rt.setText(dataPL.getRt());
        et_rw.setText(dataPL.getRw());
        et_provinsi.setText(dataPL.getProvinsi());
        et_kota.setText(dataPL.getKota());
        et_kecamatan.setText(dataPL.getKecamatan());
        et_kelurahan.setText(dataPL.getKelurahan());
        et_kodepos.setText(dataPL.getKode_pos());

        Type type = new TypeToken<List<tindaklanjut>>() {}.getType();
        dataTL = gson.fromJson(dataTindakLanjut, type);
        if(dataTL.size() == 0){
            ll_emptydata.setVisibility(View.VISIBLE);
        }
        else{
            TindaklanjutAdapater adp = new TindaklanjutAdapater(KonsumerKPRPipelineEditActivity.this, dataTL);
            rv_history_tindaklanjut.setLayoutManager(new LinearLayoutManager(KonsumerKPRPipelineEditActivity.this));
            rv_history_tindaklanjut.setItemAnimator(new DefaultItemAnimator());
            rv_history_tindaklanjut.setAdapter(adp);
        }

        val_gps = dataPL.getLokasi_gps();
    }

    private void getBidangPekerjaan(String bidang_usaha) {
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listBidangPekerjaan(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<MListBidangPekerjaan>>() {
                            }.getType();

                            listBidangPekerjaan = gson.fromJson(listDataString, type);

                            for (int i = 0; i < response.body().getData().size(); i++){
                                if (listBidangPekerjaan.get(i).getDESC1().equalsIgnoreCase(bidang_usaha)) {
                                    et_bidang_pekerjaan.setText(listBidangPekerjaan.get(i).getDESC2());
                                }
                            }

                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKPRPipelineEditActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void loadPihakKetiga() {
        inquiryProject req = new inquiryProject(appPreferences.getKodeKanwil());
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listPihakKetiga(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<MListPihakKetiga>>() {
                            }.getType();

                            listPihakKetiga = gson.fromJson(listDataString, type);

                            setDynamicIcon();
                            onclickSelectDialogPihakKetiga();
                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKPRPipelineEditActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void onclickSelectDialogPihakKetiga() {

        et_pihak_ketiga.setFocusable(false);
        et_pihak_ketiga.setInputType(InputType.TYPE_NULL);
        et_pihak_ketiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_pihak_ketiga.getLabelText().toString().trim(), listPihakKetiga, KonsumerKPRPipelineEditActivity.this, "pihak ketiga");
            }
        });
        et_pihak_ketiga.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_pihak_ketiga.getLabelText().toString().trim(), listPihakKetiga, KonsumerKPRPipelineEditActivity.this, "pihak ketiga");
            }
        });
        tf_pihak_ketiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_pihak_ketiga.getLabelText().toString().trim(), listPihakKetiga, KonsumerKPRPipelineEditActivity.this, "pihak ketiga");
            }
        });
        tf_pihak_ketiga.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_pihak_ketiga.getLabelText().toString().trim(), listPihakKetiga, KonsumerKPRPipelineEditActivity.this, "pihak ketiga");
            }
        });
    }

    @Override
    public void onSelectPihakKetiga(String title, MListPihakKetiga data) {
        if (title.equalsIgnoreCase(tf_pihak_ketiga.getLabelText().toString().trim())) {
            et_pihak_ketiga.setText(data.getNAMA());
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPipelineEditActivity.this, tf_pihak_ketiga, et_pihak_ketiga);
            val_pihak_ketiga = data.getID_PIHAK_KETIGA();
            getPihakKetiga(val_pihak_ketiga);



            if(val_pihak_ketiga==1){
                et_proyek_perumahan.setText("Tidak Mengikuti Project");
                val_project=1;
                et_proyek_perumahan.setFocusable(false);
                et_proyek_perumahan.setOnClickListener(null);
                tf_proyek_perumahan.setOnClickListener(null);
                tf_proyek_perumahan.getEndIconImageButton().setVisibility(View.GONE);
            }
            else{
                et_proyek_perumahan.setText("Pilih");
                loadProject(String.valueOf(data.getID_PIHAK_KETIGA()));
                et_proyek_perumahan.setFocusable(false);
                et_proyek_perumahan.setOnClickListener(this);
                tf_proyek_perumahan.setOnClickListener(this);
                tf_proyek_perumahan.getEndIconImageButton().setVisibility(View.VISIBLE);
            }
        }
    }

    private void loadProject(String idPihakketiga) {
        inquiryProject req = new inquiryProject(appPreferences.getKodeKanwil());
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listProject(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            JsonArray arrSegmen = response.body().getData().getAsJsonArray();
                            JsonObject objSegmen;

                            projectModel.clear();
                            for (int i = 0; i < response.body().getData().size(); i++){
                                objSegmen = arrSegmen.get(i).getAsJsonObject();
                                if (String.valueOf(objSegmen.get("FID_PIHAK_KETIGA")).equalsIgnoreCase(idPihakketiga)) {
                                    nama = objSegmen.get("NAMA").getAsString();
                                    fid_pihak_ketiga = String.valueOf(objSegmen.get("FID_PIHAK_KETIGA").getAsString());
                                    tipe = objSegmen.get("tipe").getAsString();
                                    id_project = objSegmen.get("ID_PROJECT").getAsInt();

                                    Log.d("Project", objSegmen.get("NAMA").getAsString());

                                    projectModel.add(nama, fid_pihak_ketiga, tipe, id_project);
                                }
                            }
                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKPRPipelineEditActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void getPihakKetiga(int idPihakKetiga) {
        loading.setVisibility(View.VISIBLE);
        ReqPihakKetiga req = new ReqPihakKetiga();
        req.setIdPihakKetiga(idPihakKetiga);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().getPihakketiga(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        loading.setVisibility(GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<PihakKetiga>() {}.getType();

                            dataPihakKetiga = gson.fromJson(listDataString, type);


                            et_npwp_developer.setText(dataPihakKetiga.getNPWP());

                            if(appPreferences.getCbAmanah().equalsIgnoreCase("true")){
                                if(dataPihakKetiga.getAMANAH_NO_REK_DEVELOPER()==null||dataPihakKetiga.getAMANAH_NO_REK_DEVELOPER().isEmpty()){
                                    tf_no_rekening_developer.setError("Harap isi Rekening Developer",false);
                                    AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), "Nomor rekening developer tidak ditemukan, silahkan input secara manual");
                                }
                                else{
                                    et_no_rekening_developer.setText(dataPihakKetiga.getAMANAH_NO_REK_DEVELOPER());
                                }
                            }
                            else{
                                if(dataPihakKetiga.getNOREKDEVELOPER()==null||dataPihakKetiga.getNOREKDEVELOPER().isEmpty()){
                                    tf_no_rekening_developer.setError("Harap isi Rekening Developer",false);
                                    AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), "Nomor rekening developer tidak ditemukan, silahkan input secara manual");
                                }
                                else{
                                    et_no_rekening_developer.setText(dataPihakKetiga.getNOREKDEVELOPER());
                                }
                            }




                            if(dataPihakKetiga.getNPWP()==null||dataPihakKetiga.getNPWP().isEmpty()){
                            tf_npwp_developer.setError("Harap isi NPWP Developer",false);
                            }


                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        loading.setVisibility(GONE);
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), error.getMessage());
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
                Toast.makeText(KonsumerKPRPipelineEditActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void getDataSikasep() {
        loading.setVisibility(View.VISIBLE);
        ReqDataSikasep req = new ReqDataSikasep();
        req.setKtpDebitur(et_nik.getText().toString());
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

                            et_pihak_ketiga_sikasep.setText(dataSikasep.getNamaPengembang());
                            et_proyek_perumahan_sikasep.setText(dataSikasep.getNamaPerumahan());

                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        loading.setVisibility(GONE);
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), error.getMessage());
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
                Toast.makeText(KonsumerKPRPipelineEditActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void openProjectDialog(String fid_pihak_ketiga, String pihak_ketiga){
        DialogProject.display(getSupportFragmentManager(), fid_pihak_ketiga, pihak_ketiga, this);
    }

    @Override
    public void onProjectSelect(String title, MListProject data) {
        if (title.equalsIgnoreCase(et_pihak_ketiga.getText().toString().trim())) {
            et_proyek_perumahan.setText(data.getNAMA());
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPipelineEditActivity.this, tf_proyek_perumahan, et_proyek_perumahan);
            val_project = data.getID_PROJECT();
        }
    }

    private void loadTujuanPenggunaan() {
        inquiryInstansi req = new inquiryInstansi(appPreferences.getKodeKantor());
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listTujuanPenggunaanKpr(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<MTujuanPenggunaan>>() {
                            }.getType();

                            listTujuanPenggunaan = gson.fromJson(listDataString, type);
                            onclickSelectDialogTujuanPenggunaan();
                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKPRPipelineEditActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void onclickSelectDialogTujuanPenggunaan(){
        //INSTANSI
        et_tujuanpenggunaan.setFocusable(false);
        et_tujuanpenggunaan.setInputType(InputType.TYPE_NULL);
        et_tujuanpenggunaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_tujuanpenggunaan.getLabelText().toString().trim(), listTujuanPenggunaan, KonsumerKPRPipelineEditActivity.this, "tujuan");
            }
        });
        et_tujuanpenggunaan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_tujuanpenggunaan.getLabelText().toString().trim(), listTujuanPenggunaan, KonsumerKPRPipelineEditActivity.this, "tujuan");
            }
        });
        tf_tujuanpenggunaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_tujuanpenggunaan.getLabelText().toString().trim(), listTujuanPenggunaan, KonsumerKPRPipelineEditActivity.this, "tujuan");
            }
        });
        tf_tujuanpenggunaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_tujuanpenggunaan.getLabelText().toString().trim(), listTujuanPenggunaan, KonsumerKPRPipelineEditActivity.this, "tujuan");
            }
        });
    }

    @Override
    public void onSelectTujuan(String title, MTujuanPenggunaan data) {
        if (title.equalsIgnoreCase(tf_tujuanpenggunaan.getLabelText().toString().trim())) {
            et_tujuanpenggunaan.setText(data.getDESC1());
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPipelineEditActivity.this, tf_tujuanpenggunaan, et_tujuanpenggunaan);
            val_tujuanpenggunaan = data.getID_TUJUAN();
        }
    }

    private void loadBidangPekerjaan() {
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listBidangPekerjaan(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<MListBidangPekerjaan>>() {
                            }.getType();

                            listBidangPekerjaan = gson.fromJson(listDataString, type);
                            onclickSelectDialogBidangPekerjaan();
                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKPRPipelineEditActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void onclickSelectDialogBidangPekerjaan() {

        et_bidang_pekerjaan.setFocusable(false);
        et_bidang_pekerjaan.setInputType(InputType.TYPE_NULL);
        et_bidang_pekerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_bidang_pekerjaan.getLabelText().toString().trim(), listBidangPekerjaan, KonsumerKPRPipelineEditActivity.this, "bidang pekerjaan");
            }
        });
        et_bidang_pekerjaan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_bidang_pekerjaan.getLabelText().toString().trim(), listBidangPekerjaan, KonsumerKPRPipelineEditActivity.this, "bidang pekerjaan");
            }
        });
        tf_bidang_pekerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_bidang_pekerjaan.getLabelText().toString().trim(), listBidangPekerjaan, KonsumerKPRPipelineEditActivity.this, "bidang pekerjaan");
            }
        });
        tf_bidang_pekerjaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_bidang_pekerjaan.getLabelText().toString().trim(), listBidangPekerjaan, KonsumerKPRPipelineEditActivity.this, "bidang pekerjaan");
            }
        });
    }

    @Override
    public void onSelectBidangPekerjaan(String title, MListBidangPekerjaan data) {
        if (title.equalsIgnoreCase(tf_bidang_pekerjaan.getLabelText().toString().trim())) {
            et_bidang_pekerjaan.setText(data.getDESC2());
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPipelineEditActivity.this, tf_bidang_pekerjaan, et_bidang_pekerjaan);
            val_bidang_pekerjaan = data.getDESC1();
        }
    }

    private void loadJenisKPR() {
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listJenisKPR(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<MListJenisKPR>>() {
                            }.getType();

                            listJenisKPR = gson.fromJson(listDataString, type);

                            if(!et_program.getText().toString().toLowerCase().contains("flpp")){
                                onclickSelectDialogJenisKPR();
                            }
                            else{
                                et_jenis_pekerjaan.setFocusable(false);
                                tf_jenis_pekerjaan.setPanelBackgroundColor(getResources().getColor(R.color.colorDisableEdit));
                                tf_jenis_pekerjaan.setEndIcon(0);

                            }

                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKPRPipelineEditActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void onclickSelectDialogJenisKPR() {

        et_jenis_pekerjaan.setFocusable(false);
        et_jenis_pekerjaan.setInputType(InputType.TYPE_NULL);
        et_jenis_pekerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_jenis_pekerjaan.getLabelText().toString().trim(), listJenisKPR, KonsumerKPRPipelineEditActivity.this, "jenis kpr");
            }
        });
        et_jenis_pekerjaan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_jenis_pekerjaan.getLabelText().toString().trim(), listJenisKPR, KonsumerKPRPipelineEditActivity.this, "jenis kpr");
            }
        });
        tf_jenis_pekerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_jenis_pekerjaan.getLabelText().toString().trim(), listJenisKPR, KonsumerKPRPipelineEditActivity.this, "jenis kpr");
            }
        });
        tf_jenis_pekerjaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_jenis_pekerjaan.getLabelText().toString().trim(), listJenisKPR, KonsumerKPRPipelineEditActivity.this, "jenis kpr");
            }
        });
    }

    @Override
    public void onSelectJenisKPR(String title, MListJenisKPR data) {
        if (title.equalsIgnoreCase(tf_jenis_pekerjaan.getLabelText().toString().trim())) {
            et_jenis_pekerjaan.setText(data.getValue());
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPipelineEditActivity.this, tf_jenis_pekerjaan, et_jenis_pekerjaan);
            val_jenis_pekerjaan = data.getKey();
        }
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorBackgroundTransparent));
        }
    }

    private void checkCollapse() {
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangedListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state.name().equalsIgnoreCase("COLLAPSED")) {
                    tv_page_title.setVisibility(View.VISIBLE);
                    btn_takepicture.setVisibility(View.VISIBLE);
                    tv_page_title.setText("Edit Pipeline");
                } else {
                    tv_page_title.setVisibility(View.GONE);
                    btn_takepicture.setVisibility(View.GONE);
                    tv_page_title.setText("");
                }
            }
        });
    }

    private void openAddressDialog() {
        DialogAddress.display(getSupportFragmentManager(), this);
    }

    private void openCameraMenu() {
        BSBottomCamera.displayWithTitle(getSupportFragmentManager(), this, "Foto Nasabah");
    }

    private void onclickSelectDialog() {
        //PROGRAM
        et_program.setFocusable(false);
        et_program.setInputType(InputType.TYPE_NULL);
        et_program.setOnClickListener(this);
        et_program.setOnFocusChangeListener(this);
        tf_program.setOnClickListener(this);
        tf_program.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_segmen.getText().toString().trim().isEmpty() && !et_produk.getText().toString().trim().isEmpty()){
                    Toast.makeText(KonsumerKPRPipelineEditActivity.this, "Tidak dapat mengubah program", Toast.LENGTH_LONG).show();
//                    openProdukDialog(tf_program.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), et_produk.getText().toString().trim());
                }
//                else{
//                    AppUtil.notifwarning(KonsumerKPRPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect)+" "+tf_segmen.getLabelText()+" atau "+tf_produk.getLabelText()+" terlebih dahulu");
//                }
            }
        });

        //PROJECT
        et_proyek_perumahan.setFocusable(false);
        et_proyek_perumahan.setInputType(InputType.TYPE_NULL);
        et_proyek_perumahan.setOnClickListener(this);
        et_proyek_perumahan.setOnFocusChangeListener(this);
        tf_proyek_perumahan.setOnClickListener(this);
        tf_proyek_perumahan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_pihak_ketiga.getText().toString().trim().equalsIgnoreCase("Pilih")) {
                    openProjectDialog(String.valueOf(val_pihak_ketiga), et_pihak_ketiga.getText().toString().trim());
                } else {
                    AppUtil.notifwarning(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect) + " " + tf_pihak_ketiga.getLabelText() + " terlebih dahulu");
                }
            }
        });

        //TANGGAL LAHIR
        et_tanggallahir.setFocusable(false);
        et_tanggallahir.setInputType(InputType.TYPE_NULL);
        et_tanggallahir.setOnFocusChangeListener(this);
        tf_tanggallahir.setOnClickListener(this);
        tf_tanggallahir.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerTanggalLahir();
            }
        });

        //ALAMAT
        et_provinsi.setInputType(InputType.TYPE_NULL);
        et_provinsi.setFocusable(false);
        et_kota.setInputType(InputType.TYPE_NULL);
        et_kota.setFocusable(false);
        et_kecamatan.setInputType(InputType.TYPE_NULL);
        et_kecamatan.setFocusable(false);
        et_kelurahan.setInputType(InputType.TYPE_NULL);
        et_kelurahan.setFocusable(false);
        et_kodepos.setInputType(InputType.TYPE_NULL);
        et_kodepos.setFocusable(false);

        //no rekening developer
        bt_cek_rekening_developer.setOnClickListener(this);

        et_pihak_ketiga_sikasep.setFocusable(false);
        et_proyek_perumahan_sikasep.setFocusable(false);
    }

//    private void openProdukDialog(String title, String segmen, String product){
//        DialogSelect.display(getSupportFragmentManager(), title, segmen, product, this);
//    }

//    @Override
//    public void onProductSelect(String title, ProductPojo data) {
//        et_program.setText(data.getNama_gimmick());
//        val_program = data.getKode_gimmick(); //set value program
//
//    }

    private void onChangeText() {
        et_plafond.addTextChangedListener(new NumberTextWatcherForThousand(et_plafond));
        et_pendapatan.addTextChangedListener(new NumberTextWatcherForThousand(et_pendapatan));
        et_tindaklanjut.addTextChangedListener(this);
    }

    private void datePickerTanggalLahir() {
        calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -18);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        final SimpleDateFormat formatServer = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        DatePickerDialog.OnDateSetListener ls_tanggallahir = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String tanggallahir = sdf.format(calendar.getTime());
                et_tanggallahir.setText(tanggallahir);
                AppUtil.dynamicIconLogoChangeDropdown(KonsumerKPRPipelineEditActivity.this, tf_tanggallahir, et_tanggallahir);
                if (!et_tanggallahir.getText().toString().trim().isEmpty()) {
                    val_tanggallahir = formatServer.format(calendar.getTime()); //set value tanggal lahir
                }
            }
        };

        dp_tanggallahir = new DatePickerDialog(KonsumerKPRPipelineEditActivity.this, R.style.AppTheme_TimePickerTheme, ls_tanggallahir, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dp_tanggallahir.getDatePicker().setMinDate(calendarMinDate.getTimeInMillis());
        dp_tanggallahir.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dp_tanggallahir.show();
    }

    //TAKE PICTURE
    public void openGalery() {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(Intent.createChooser(it, "Select File"), PICK_IMAGE);
    }

    private void openCamera() {
        checkCameraPermission();
    }

    private void openPreviewPhoto() {
        DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", bitmapPhoto);
    }

    private static final int MY_CAMERA_REQUEST_CODE = 100;

    public void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);
        } else {
            Uri outputFileUri = getCaptureImageOutputUri();
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(captureIntent, TAKE_PICTURE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                AppUtil.showToastLong(this, "Camera Permission Granted");
                Uri outputFileUri = getCaptureImageOutputUri();
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                startActivityForResult(captureIntent, TAKE_PICTURE);
            } else {
                AppUtil.showToastLong(this, "Camera Permission Denied");
            }
        }
    }

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                outputFileUri = FileProvider.getUriForFile(KonsumerKPRPipelineEditActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "fotonasabah.png"));
            } else {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "fotonasabah.png"));
            }
        }
        return outputFileUri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            initImageFileName();
            if (getPickImageResultUri(data) != null) {
                uriPhoto = getPickImageResultUri(data);
                try {
                    bitmapPhoto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriPhoto);
                    bitmapPhoto = AppUtil.getResizedBitmap(bitmapPhoto, 1024);
                    bitmapPhoto = AppUtil.rotateImageIfRequired(this, bitmapPhoto, uriPhoto);
                    iv_photo.setImageBitmap(bitmapPhoto);
                    CHANGE_PICTURE = 1; //FLAG CHANGE PICTURE
                    hasFace = AppUtil.hasFaceImage(bitmapPhoto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }

    private void initImageFileName() {
        String fileName = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()) + "_" + System.currentTimeMillis() + ".jpg";
        appPreferences.setFotoNasabahName(fileName);
    }

    public void processInputPipeline() {
        loading.setVisibility(View.VISIBLE);
        String filename = appPreferences.getFotoNasabahName();
        if (CHANGE_PICTURE == 1){
            ImageHandler.saveImageToCache(this, bitmapPhoto, filename);
            File imageFile = new File(getApplicationContext().getCacheDir(), filename);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
            MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);
            Call<ParseResponse> call = apiClientAdapter.getApiInterface().uploadFoto(fileBody);
            call.enqueue(new Callback<ParseResponse>() {
                @Override
                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                    try {
                        if (response.isSuccessful()){
                            if (response.body().getStatus().equalsIgnoreCase("00")){
                                if(et_program.getText().toString().toUpperCase().contains("FLPP")){
                                    sendDataFlpp(response.body().getData().get("id").getAsInt());
                                }
                                else{
                                    sendData(response.body().getData().get("id").getAsInt());
                                }

                            }
                            else{
                                loading.setVisibility(View.GONE);
                                AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            }
                        }
                        else {
                            loading.setVisibility(View.GONE);
                            Error error = ParseResponseError.confirmEror(response.errorBody());
                            AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), error.getMessage());
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ParseResponse> call, Throwable t) {
                    loading.setVisibility(View.GONE);
                    AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                }
            });
        }
        else {
            if(et_program.getText().toString().toUpperCase().contains("FLPP")){
                sendDataFlpp(dataPL.getFid_photo());
            }
            else{
                sendData(dataPL.getFid_photo());
            }

        }
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {

    }

    private void sendData(int idPhoto) {
        val_tenor = et_tenor.getText().toString().trim();
        val_plafond = NumberTextWatcherForThousand.trimCommaOfString(et_plafond.getText().toString().trim());
        val_nik = et_nik.getText().toString().trim();
        val_nama = et_nama.getText().toString().trim();
        val_tempatlahir = et_tempatlahir.getText().toString().trim();
        val_nomorhp = et_nomorhp.getText().toString().trim();
        val_pendapatan = NumberTextWatcherForThousand.trimCommaOfString(et_pendapatan.getText().toString().trim());
        val_alamat = et_alamat.getText().toString().trim();
        val_provinsi = et_provinsi.getText().toString().trim();
        val_kota = et_kota.getText().toString().trim();
        val_kecamatan = et_kecamatan.getText().toString().trim();
        val_kelurahan = et_kelurahan.getText().toString().trim();
        val_kodepos = et_kodepos.getText().toString().trim();
        val_texttindaklanjut = et_tindaklanjut.getText().toString().trim();


        KonsumerKPRInputPipeline req = new KonsumerKPRInputPipeline(String.valueOf(appPreferences.getUid()), dataPL.getId(), idPhoto, val_segmen, val_produk, val_program, val_pihak_ketiga, val_project, val_tujuanpenggunaan, val_desc_tujuan, AppUtil.parseLongWithDefault(val_plafond, 0), AppUtil.parseIntWithDefault(val_tenor, 0),
                val_nik, val_nama, val_tempatlahir, val_tanggallahir, val_nomorhp, val_gps, val_alamat, val_provinsi, val_kota, val_kecamatan, val_kelurahan, val_kodepos, val_rt, val_rw, val_bidang_pekerjaan, val_jenis_pekerjaan, AppUtil.parseIntWithDefault(val_pendapatan, 0), val_jenistindaklanjut, val_texttindaklanjut);


        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendDataPipelineKpr(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            CustomDialog.DialogSuccess(KonsumerKPRPipelineEditActivity.this, "Success!", "Input data Pipeline sukses", KonsumerKPRPipelineEditActivity.this);
                        } else {
                            AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void sendDataFlpp(int idPhoto) {
        val_tenor = et_tenor.getText().toString().trim();
        val_plafond = NumberTextWatcherForThousand.trimCommaOfString(et_plafond.getText().toString().trim());
        val_nik = et_nik.getText().toString().trim();
        val_nama = et_nama.getText().toString().trim();
        val_tempatlahir = et_tempatlahir.getText().toString().trim();
        val_nomorhp = et_nomorhp.getText().toString().trim();
        val_pendapatan = NumberTextWatcherForThousand.trimCommaOfString(et_pendapatan.getText().toString().trim());
        val_alamat = et_alamat.getText().toString().trim();
        val_provinsi = et_provinsi.getText().toString().trim();
        val_kota = et_kota.getText().toString().trim();
        val_kecamatan = et_kecamatan.getText().toString().trim();
        val_kelurahan = et_kelurahan.getText().toString().trim();
        val_kodepos = et_kodepos.getText().toString().trim();
        val_texttindaklanjut = et_tindaklanjut.getText().toString().trim();


        KonsumerKPRInputPipeline req = new KonsumerKPRInputPipeline(String.valueOf(appPreferences.getUid()), dataPL.getId(), idPhoto, val_segmen, val_produk, val_program, val_pihak_ketiga, val_project, val_tujuanpenggunaan, val_desc_tujuan, AppUtil.parseLongWithDefault(val_plafond, 0), AppUtil.parseIntWithDefault(val_tenor, 0),
                val_nik, val_nama, val_tempatlahir, val_tanggallahir, val_nomorhp, val_gps, val_alamat, val_provinsi, val_kota, val_kecamatan, val_kelurahan, val_kodepos, val_rt, val_rw, val_bidang_pekerjaan, val_jenis_pekerjaan, AppUtil.parseIntWithDefault(val_pendapatan, 0), val_jenistindaklanjut, val_texttindaklanjut);

        req.setNoRekDeveloper(et_no_rekening_developer.getText().toString());
        req.setNpwpDeveloper(et_npwp_developer.getText().toString());

        Call<ParseResponse> call ;
        if (isHotprospek == 1) {
            call = apiClientAdapter.getApiInterface().savePipelineHotprospekFlpp(req);
        } else {
            call = apiClientAdapter.getApiInterface().sendDataPipelineKprFlpp(req);
        }
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            if(isHotprospek==1){
                                CustomDialog.DialogSuccess(KonsumerKPRPipelineEditActivity.this, "Success!", "Input data Pipeline sukses, data sudah masuk ke halaman Hotprospek", KonsumerKPRPipelineEditActivity.this);
                            }
                            else{
                                CustomDialog.DialogSuccess(KonsumerKPRPipelineEditActivity.this, "Success!", "Input data Pipeline sukses", KonsumerKPRPipelineEditActivity.this);
                            }

                        } else {
                            AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private boolean validateForm() {
        Log.d("here", "validate");
        if (et_segmen.getText().toString().trim().isEmpty() || et_segmen.getText().toString().trim().equalsIgnoreCase("")) {
            tf_segmen.setError(tf_segmen.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "segmen");

        if (et_produk.getText().toString().trim().isEmpty() || et_produk.getText().toString().trim().equalsIgnoreCase("")) {
            tf_produk.setError(tf_produk.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "produk");

        if (et_program.getText().toString().trim().isEmpty() || et_program.getText().toString().trim().equalsIgnoreCase("")) {
            tf_program.setError(tf_program.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "program");

        if (et_pihak_ketiga.getText().toString().trim().equalsIgnoreCase("Pilih")) {
            tf_pihak_ketiga.setError(tf_pihak_ketiga.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }
        Log.d("lolos", "pihak ketiga");

        if (et_proyek_perumahan.getText().toString().trim().equalsIgnoreCase("Pilih")) {
            tf_proyek_perumahan.setError(tf_proyek_perumahan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }
        Log.d("lolos", "proyek perumahan");

        if (et_tujuanpenggunaan.getText().toString().trim().equalsIgnoreCase("Pilih")) {
            tf_tujuanpenggunaan.setError(tf_tujuanpenggunaan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }
        Log.d("lolos", "tujuan");

        if (!et_plafond.getText().toString().trim().isEmpty() || !et_plafond.getText().toString().trim().equalsIgnoreCase("")) {
            long plafond = Long.valueOf(NumberTextWatcherForThousand.trimCommaOfString(et_plafond.getText().toString().trim()));
            long plafond_minimal = 25000000;
            String plafond_min = Long.toString(plafond_minimal);
            if (plafond < 25000000) {
                tf_plafond.setError("Plafond minimal Rp. " + AppUtil.parseRupiah(plafond_min), true);
                return false;
            }
        } else if (et_plafond.getText().toString().trim().isEmpty() || et_plafond.getText().toString().trim().equalsIgnoreCase("")) {
            tf_plafond.setError(tf_plafond.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "plafond");

        if (!et_tenor.getText().toString().trim().isEmpty() || !et_tenor.getText().toString().trim().equalsIgnoreCase("")) {
            if (Integer.valueOf(et_tenor.getText().toString().trim()) < 12) {
                tf_tenor.setError("Tenor minimal 12 bulan", true);
                return false;
            }
        } else if (et_tenor.getText().toString().trim().isEmpty() || et_tenor.getText().toString().trim().equalsIgnoreCase("")) {
            tf_tenor.setError(tf_tenor.getLabelText() + " " + getString(R.string.title_validate_field), true);
            et_tenor.setSelection(et_tenor.getText().length());
            return false;
        }

        Log.d("lolos", "tenor");

        if (Integer.parseInt(et_tenor.getText().toString()) > 180) {
            tf_tenor.setError("Tenor tidak boleh melebihi 180 Bulan", true);
            return false;
        }

        Log.d("lolos", "tenor 2");

        if (!et_nik.getText().toString().trim().isEmpty() || !et_nik.getText().toString().trim().equalsIgnoreCase("")) {
            if (Validator.validateKtp(et_nik.getText().toString().trim()) == false) {
                tf_nik.setError("Format " + tf_nik.getLabelText() + " " + getString(R.string.title_invalid_field), true);
                return false;
            }
        } else if (et_nik.getText().toString().trim().isEmpty() || et_nik.getText().toString().trim().equalsIgnoreCase("")) {
            tf_nik.setError(tf_nik.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "nik");

        if (et_nama.getText().toString().trim().isEmpty() || et_nama.getText().toString().trim().equalsIgnoreCase("")) {
            tf_nama.setError(tf_nama.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "nama");

        if (et_tempatlahir.getText().toString().trim().isEmpty() || et_tempatlahir.getText().toString().trim().equalsIgnoreCase("")) {
            tf_tempatlahir.setError(tf_tempatlahir.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "tempat");

        if (et_tanggallahir.getText().toString().trim().equalsIgnoreCase("Pilih")) {
            tf_tanggallahir.setError(tf_tanggallahir.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "tanggal");

        if (!et_nomorhp.getText().toString().trim().isEmpty() || !et_nomorhp.getText().toString().trim().equalsIgnoreCase("")) {
            if (Validator.validateNomorHp(et_nomorhp.getText().toString().trim()) == false) {
                tf_nomorhp.setError("Format " + tf_nomorhp.getLabelText() + " " + getString(R.string.title_invalid_field), true);
                return false;
            }
        } else if (et_nomorhp.getText().toString().trim().isEmpty() || et_nomorhp.getText().toString().trim().equalsIgnoreCase("")) {
            tf_nomorhp.setError(tf_nomorhp.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "hp");

//        if (et_instansi.getText().toString().trim().equalsIgnoreCase("Pilih")) {
//            tf_instansi.setError(tf_instansi.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }

//        Log.d("lolos", "instansi");

        if (et_bidang_pekerjaan.getText().toString().trim().equalsIgnoreCase("Pilih")) {
            tf_bidang_pekerjaan.setError(tf_bidang_pekerjaan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "bidang");

        if (et_jenis_pekerjaan.getText().toString().trim().equalsIgnoreCase("Pilih")) {
            tf_jenis_pekerjaan.setError(tf_jenis_pekerjaan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "jenis");

        if (et_pendapatan.getText().toString().trim().isEmpty() || et_pendapatan.getText().toString().trim().equalsIgnoreCase("")) {
            tf_pendapatan.setError(tf_pendapatan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "pendapatan");

        if (et_alamat.getText().toString().trim().isEmpty() || et_alamat.getText().toString().trim().equalsIgnoreCase("")) {
            tf_alamat.setError(tf_alamat.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "alamat");

        if (et_provinsi.getText().toString().trim().isEmpty() || et_provinsi.getText().toString().trim().equalsIgnoreCase("")) {
            tf_provinsi.setError(tf_provinsi.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "prov");

        if (et_tindaklanjut.getText().toString().trim().isEmpty() && val_jenistindaklanjut.isEmpty()) {
            AppUtil.notifwarning(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), "Pilih jenis tindak lanjut");
            return false;
        }

        Log.d("lolos", "tindak");

        if (!et_tindaklanjut.getText().toString().trim().isEmpty() && val_jenistindaklanjut.isEmpty()) {
            AppUtil.notifwarning(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), "Pilih jenis tindak lanjut");
            return false;
        }

        Log.d("lolos", "tindak 2");

        if (!val_jenistindaklanjut.isEmpty() && et_tindaklanjut.getText().toString().trim().isEmpty()) {
            AppUtil.notifwarning(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), "Isi deskripsi tindak lanjut");
            return false;
        }

        Log.d("lolos", "jenis");

//        if (idFoto == 0) {
//            AppUtil.notifwarning(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), "Silahkan pilih / ambil Foto");
//            return false;
//        }

        if (bitmapPhoto == null) {
            AppUtil.notifwarning(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), "Silahkan pilih / ambil Foto");
            return false;
        }

        Log.d("lolos", "foto");

        if (hasFace == 0) {
            AppUtil.notifwarning(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.title_nohasface));
            return false;
        }

        Log.d("lolos", "wajah");

        if(et_program.getText().toString().toUpperCase().contains("FLPP")){
            if(et_no_rekening_developer.getText().toString().isEmpty()){
                AppUtil.notifwarning(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), "Harap isi no rekening developer");
                tf_no_rekening_developer.setError("Harap isi no rekening",true);
                return false;
            }
            else   if(et_npwp_developer.getText().toString().isEmpty()){
                AppUtil.notifwarning(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), "Harap isi no rekening developer");
                tf_npwp_developer.setError("Harap isi npwp developer",true);
                return false;
            }
//            else if(!cleanNamaPihakKetiga.equalsIgnoreCase(cleanNamaPihakKetigaSikasep)){
//                AppUtil.notifwarning(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), "Nama pihak ketiga harus sama dengan nama di SIKASEP, harap hubungi FLPP Center");
//                Log.d("checkingpihakketiga",cleanNamaPihakKetiga);
//                Log.d("checkingpihakketigasi",cleanNamaPihakKetigaSikasep);
//                return false;
//            }
//            else if(!et_proyek_perumahan.getText().toString().trim().equalsIgnoreCase(et_proyek_perumahan_sikasep.getText().toString().trim())){
//                AppUtil.notifwarning(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), "Nama perumahan  harus sama dengan nama di SIKASEP, harap hubungi FLPP Center");
//                return false;
//            }
           else if (rekeningDeveloperBerubah) {
                AppUtil.notifwarning(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), "Ada perubahan di nomor rekening developer, atau nomor rekening belum valid, klik tombol cek rekening");
                bt_cek_rekening_developer.requestFocus();
                return false;
            }


        }



        return true;
    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        switch (idMenu) {
            case "Take Photo":
                openCamera();
                break;
            case "Pick Photo":
                openGalery();
                break;
        }
    }

    @Override
    public void success(boolean val) {
        if (val) {
            finish();
//            startActivity(new Intent(this, PipelineActivity.class));
            startActivity(new Intent(this, KonsumerKprPipelineActivity.class));
        }
    }

    @Override
    public void confirm(boolean val) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                CustomDialog.DialogBackpress(this);
                break;
            case R.id.btn_pickaddress:
                openAddressDialog();
                break;
            case R.id.btn_send:
                cleansePihakKetiga();
                if (validateForm())
                    processInputPipeline();
                break;
            //PROGRAM
            case R.id.et_program:
            case R.id.tf_program:
                if (!et_segmen.getText().toString().trim().isEmpty() && !et_produk.getText().toString().trim().isEmpty()) {
                    Toast.makeText(KonsumerKPRPipelineEditActivity.this, "Tidak dapat mengubah program", Toast.LENGTH_LONG).show();
//                    openProdukDialog(tf_program.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), et_produk.getText().toString().trim());
                }
                break;
            case R.id.bt_cek_rekening_developer:

                if(appPreferences.getCbAmanah().equalsIgnoreCase("true")){
                    cekValiditasRekeningAmanah();
                }
                else{
                    cekValiditasRekening();
                }


                break;
            //PROJECT
            case R.id.et_proyek_perumahan:
            case R.id.tf_proyek_perumahan:
                if (!et_pihak_ketiga.getText().toString().trim().equalsIgnoreCase("Pilih")) {
                    openProjectDialog(String.valueOf(val_pihak_ketiga), et_pihak_ketiga.getText().toString().trim());
                } else {
                    AppUtil.notifwarning(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect) + " " + tf_pihak_ketiga.getLabelText() + " terlebih dahulu");
                }
                break;
            //TANGGAL LAHIR
            case R.id.et_tanggallahir:
            case R.id.tf_tanggallahir:
                datePickerTanggalLahir();
                break;
            //TAKE PICTURE
            case R.id.btn_takepicture:
            case R.id.btnfab_takepicture:
                openCameraMenu();
                break;
            case R.id.iv_photo:
                openPreviewPhoto();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            switch (v.getId()) {
                //PROGRAM
                case R.id.et_program:
                case R.id.tf_program:
                    if (!et_segmen.getText().toString().trim().isEmpty() && !et_produk.getText().toString().trim().isEmpty()) {
                        Toast.makeText(KonsumerKPRPipelineEditActivity.this, "Tidak dapat mengubah program", Toast.LENGTH_LONG).show();
//                    openProdukDialog(tf_program.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), et_produk.getText().toString().trim());
                    }
                    break;
                //PROJECT
                case R.id.et_proyek_perumahan:
                case R.id.tf_proyek_perumahan:
                    if (!et_pihak_ketiga.getText().toString().trim().equalsIgnoreCase("Pilih")) {
                        openProjectDialog(String.valueOf(val_pihak_ketiga), et_pihak_ketiga.getText().toString().trim());
                    } else {
                        AppUtil.notifwarning(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect) + " " + tf_pihak_ketiga.getLabelText() + " terlebih dahulu");
                    }
                    break;
                //TANGGAL LAHIR
                case R.id.et_tanggallahir:
                case R.id.tf_tanggallahir:
                    datePickerTanggalLahir();
                    break;
            }
        }
    }

    public void cekValiditasRekening(){

        loading.setVisibility(View.VISIBLE);
        ReqSaldoNasabah req = new ReqSaldoNasabah();

        req.setNoRekening(et_no_rekening_developer.getText().toString());


        //conditioning list yang ditampilkan
        Call<ParseResponseSaldo> call;
        call = apiClientAdapter.getApiInterface().getSaldoNasabah(req);

        call.enqueue(new Callback<ParseResponseSaldo>() {
            @Override
            public void onResponse(Call<ParseResponseSaldo> call, Response<ParseResponseSaldo> response) {
                // progressbar_loading.setVisibility(View.GONE);
                loading.setVisibility(GONE);
                if (response.isSuccessful()) {
                    loading.setVisibility(GONE);
                    //tutorial overlay fitur sumary kalau data sukses
//                    AppUtil.tutorialOverlay(PerformanceAoActivity.this,bt_tampil_summary,"Sekarang anda dapat melihat summary performance pembiayaan","tutorial_summary_performance");

                    if (response.body().getStatus().equalsIgnoreCase("200")) {

                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<SaldoNasabah>() {
                        }.getType();
                        dataSaldo = gson.fromJson(listDataString, type);

                        tv_status_rekening_developer.setVisibility(View.VISIBLE);
                        if(dataSaldo.getHeader22()!=null&&!dataSaldo.getHeader22().isEmpty()){
                            tv_status_rekening_developer.setText("Rekening Ditemukan : " + dataSaldo.getHeader22());
                        }
                        else{
                            tv_status_rekening_developer.setText("Rekening Ditemukan : " + dataSaldo.getNama());
                        }

                        tv_status_rekening_developer.setTextColor(getResources().getColor(R.color.main_green_stroke_color));

                        //flag berubah karena rekening sudah valid
                        rekeningDeveloperBerubah = false;

                    } else if (response.body().getStatus().equalsIgnoreCase("404")) {

                        tv_status_rekening_developer.setVisibility(View.VISIBLE);
                        tv_status_rekening_developer.setText("Rekening Tidak Ditemukan");
                        tv_status_rekening_developer.setTextColor(getResources().getColor(R.color.red_btn_bg_color));

                        //karna rekening belum valid, maka flag tetap true
                        rekeningDeveloperBerubah = true;


                    }
                    else{
                        AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseSaldo> call, Throwable t) {
                loading.setVisibility(GONE);
                Log.d("LOG D", t.getMessage());
                AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan pada server");
            }
        });
    }

    public void cekValiditasRekeningAmanah(){

        loading.setVisibility(View.VISIBLE);
        ReqSaldoNasabah req = new ReqSaldoNasabah();

        req.setNoRekening(et_no_rekening_developer.getText().toString());


        //conditioning list yang ditampilkan
        Call<ParseResponse> call;
        call = apiClientAdapter.getApiInterface().inquiryRekeningAmanah(req);

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                // progressbar_loading.setVisibility(View.GONE);
                loading.setVisibility(GONE);
                if (response.isSuccessful()) {
                    loading.setVisibility(GONE);


                    if (response.body().getStatus().equalsIgnoreCase("00")) {



                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<RekeningAmanah>() {
                        }.getType();
                        dataRekeningAmanah = gson.fromJson(listDataString, type);

                        if(dataRekeningAmanah.getStatusDormant().equalsIgnoreCase("Y")){
                            tv_status_rekening_developer.setVisibility(View.VISIBLE);
                            tv_status_rekening_developer.setText("Rekening Dormant : " + dataRekeningAmanah.getNamaNasabah());
                            tv_status_rekening_developer.setTextColor(getResources().getColor(R.color.red_btn_bg_color));
                        }
                        else{
                            tv_status_rekening_developer.setVisibility(View.VISIBLE);
                            tv_status_rekening_developer.setText("Rekening Ditemukan : " + dataRekeningAmanah.getNamaNasabah());


                            tv_status_rekening_developer.setTextColor(getResources().getColor(R.color.main_green_stroke_color));

                            //flag berubah karena rekening sudah valid
                            rekeningDeveloperBerubah = false;
                        }



                    }
                    else{
                        AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content),response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(GONE);
                Log.d("LOG D", t.getMessage());
                AppUtil.notiferror(KonsumerKPRPipelineEditActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan pada server");
            }
        });
    }



    private void otherViewChanges(){
        if(et_program.getText().toString().toUpperCase().contains("FLPP")){
            tf_pihak_ketiga_sikasep.setVisibility(View.VISIBLE);
            tf_proyek_perumahan_sikasep.setVisibility(View.VISIBLE);
            tf_npwp_developer.setVisibility(View.VISIBLE);
            et_npwp_developer.setVisibility(View.VISIBLE);


            ll_rekening_developer.setVisibility(View.VISIBLE);
            tv_status_rekening_developer.setVisibility(View.GONE);
            tf_no_rekening_developer.setVisibility(View.VISIBLE);




            et_no_rekening_developer.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    //kalo mengubah no rekening, maka counter di reset, jadi user harus klik tombol cek rekening lagi
                    rekeningDeveloperBerubah=true;
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


        }
        else{
            ll_rekening_developer.setVisibility(View.GONE);
            tf_no_rekening_developer.setVisibility(View.GONE);
            tf_pihak_ketiga_sikasep.setVisibility(GONE);
            tf_proyek_perumahan_sikasep.setVisibility(GONE);
            tf_npwp_developer.setVisibility(GONE);
            et_npwp_developer.setVisibility(GONE);
        }

        //checked kirim ke hotprospek
        sw_ishotprospek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isHotprospek = 1;
                    ll_ishotprospek.setBackgroundColor(getResources().getColor(R.color.colorGreenSoft));
                    btn_send.setText(getString(R.string.title_savepipelinehotprospek));
                } else {
                    isHotprospek = 0;
                    ll_ishotprospek.setBackgroundColor(getResources().getColor(R.color.colorBackgroundTransparent));
                    btn_send.setText(getString(R.string.title_savepipeline));
                }
            }
        });

    }

    //hilangkan kata kata PT dari nama pihak ketiga , supaya bisa dibandingkan, dan divalidasi jika tidak sama
    private void cleansePihakKetiga(){
        if(et_pihak_ketiga_sikasep.getText().toString().toUpperCase().contains("PT .")){
            cleanNamaPihakKetigaSikasep=et_pihak_ketiga_sikasep.getText().toString().toUpperCase().replace("PT .","").trim();
            Log.d("namapihakketigasikasep",cleanNamaPihakKetigaSikasep);
        }
        else{
            cleanNamaPihakKetigaSikasep=et_pihak_ketiga_sikasep.getText().toString().toUpperCase();
        }

        if(et_pihak_ketiga_sikasep.getText().toString().toUpperCase().contains("PT ")){
            cleanNamaPihakKetigaSikasep=et_pihak_ketiga_sikasep.getText().toString().toUpperCase().replace("PT ","").trim();
            Log.d("namapihakketigasikasep",cleanNamaPihakKetigaSikasep);
        }
        else{
            cleanNamaPihakKetigaSikasep=et_pihak_ketiga_sikasep.getText().toString().toUpperCase();
        }

        if(et_pihak_ketiga_sikasep.getText().toString().toUpperCase().contains("PT.")){
            cleanNamaPihakKetigaSikasep=et_pihak_ketiga_sikasep.getText().toString().toUpperCase().replace("PT.","").trim();
            Log.d("namapihakketigasikasep",cleanNamaPihakKetigaSikasep);
        }
        else{
            cleanNamaPihakKetigaSikasep=et_pihak_ketiga_sikasep.getText().toString().toUpperCase();
        }

        if(et_pihak_ketiga_sikasep.getText().toString().toUpperCase().contains("CV.")){
            cleanNamaPihakKetigaSikasep=et_pihak_ketiga_sikasep.getText().toString().toUpperCase().replace("CV.","").trim();
            Log.d("namapihakketigasikasep",cleanNamaPihakKetigaSikasep);
        }
        else{
            cleanNamaPihakKetigaSikasep=et_pihak_ketiga_sikasep.getText().toString().toUpperCase();
        }



        if(et_pihak_ketiga.getText().toString().toUpperCase().contains(", PT")){
            cleanNamaPihakKetiga=et_pihak_ketiga.getText().toString().replace(", PT","").trim();
            Log.d("namapihakketigadoang",cleanNamaPihakKetiga);
            Log.d("namapihakketigalengkap",et_pihak_ketiga.getText().toString());
        }
        else{
            cleanNamaPihakKetiga=et_pihak_ketiga.getText().toString().toUpperCase();
        }

        if(et_pihak_ketiga.getText().toString().toUpperCase().contains(", CV")){
            cleanNamaPihakKetiga=et_pihak_ketiga.getText().toString().replace(", CV","").trim();
            Log.d("namapihakketigadoang",cleanNamaPihakKetiga);
            Log.d("namapihakketigalengkap",et_pihak_ketiga.getText().toString());
        }
        else{
            cleanNamaPihakKetiga=et_pihak_ketiga.getText().toString().toUpperCase();
        }
    }

    private void npwpFormattingTextChange(final EditText npwp){
        npwp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()==2){
                    dot1=1;
                    npwp.setSelection(npwp.getText().length());

                }
                else if(s.length()==3&&dot1==1&&s.toString().substring(2,3)!="."){
                    dot1=0;
                    npwp.setText(npwp.getText().toString().substring(0,2)+"."+npwp.getText().toString().substring(2,3));
                    npwp.setSelection(npwp.getText().length());
                }

                if(s.length()==6){
                    dot2=1;
//                    npwp.setText(npwp.getText()+".");
                    npwp.setSelection(npwp.getText().length());
                }
                else if(s.length()==7&&dot2==1&&s.toString().substring(6,7)!="."){
                    dot2=0;
                    npwp.setText(npwp.getText().toString().substring(0,6)+"."+npwp.getText().toString().substring(6,7));
                    npwp.setSelection(npwp.getText().length());
                }

                if(s.length()==10){
                    dot3=1;
//                    npwp.setText(npwp.getText()+".");
                    npwp.setSelection(npwp.getText().length());
                }
                else if(s.length()==11&&dot3==1&&s.toString().substring(10,11)!="."){
                    dot3=0;
                    npwp.setText(npwp.getText().toString().substring(0,10)+"."+npwp.getText().toString().substring(10,11));
                    npwp.setSelection(npwp.getText().length());
                }

                if(s.length()==12){
                    dot4=1;
//                    npwp.setText(npwp.getText()+".");
                    npwp.setSelection(npwp.getText().length());
                }
                else if(s.length()==13&&dot4==1&&s.toString().substring(12,13)!="-"){
                    dot4=0;
                    npwp.setText(npwp.getText().toString().substring(0,12)+"-"+npwp.getText().toString().substring(12,13));
                    npwp.setSelection(npwp.getText().length());
                }

                if(s.length()==16){
                    dot5=1;
//                    npwp.setText(npwp.getText()+".");
                    npwp.setSelection(npwp.getText().length());
                }
                else if(s.length()==17&&dot5==1&&s.toString().substring(16,17)!="."){
                    dot5=0;
                    npwp.setText(npwp.getText().toString().substring(0,16)+"."+npwp.getText().toString().substring(16,17));
                    npwp.setSelection(npwp.getText().length());
                }

//                if(s.length()==10){
//                    npwp.setText(npwp.getText()+".");
//                    npwp.setSelection(npwp.getText().length());
//                }
//                if(s.length()==12){
//                    npwp.setText(npwp.getText()+"-");
//                    npwp.setSelection(npwp.getText().length());
//                }
//                if(s.length()==16){
//                    npwp.setText(npwp.getText()+".");
//                    npwp.setSelection(npwp.getText().length());
//                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (et_tindaklanjut.getText().toString().trim().isEmpty() || et_tindaklanjut.getText().toString().trim().equalsIgnoreCase("")) {
            rg_typetindaklanjut.clearCheck();
            val_jenistindaklanjut = "";
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onAddressSelect(address data) {
        et_provinsi.setText(data.getProvinsi());
        et_kota.setText(data.getKota());
        et_kecamatan.setText(data.getKecamatan());
        et_kelurahan.setText(data.getKelurahan());
        et_kodepos.setText(data.getKodepos());
    }

    @Override
    public void onBackPressed() {
        CustomDialog.DialogBackpress(this);
    }
}

