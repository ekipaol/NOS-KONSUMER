package com.application.bris.ikurma_nos_konsumer.page_aom.view.pipeline.kpr;



import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.KonsumerKPRInputPipeline;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.inquiryInstansi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.inquiryProject;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.model.ProjectModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogAddress;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogInputDataPipelineKPR;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogProject;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.AddressListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.LoadDataInputPipelineKPRListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ProjectListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListBidangPekerjaan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListJenisKPR;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListPihakKetiga;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListProject;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MProgramKmg;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MTujuanPenggunaan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.address;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.datapribadi;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppBarStateChangedListener;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.GPSTracker;
import com.application.bris.ikurma_nos_konsumer.util.ImageHandler;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherForThousand;
import com.application.bris.ikurma_nos_konsumer.util.Validator;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
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
import java.util.ArrayList;
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

public class KonsumerKprPipelineInputActivity extends AppCompatActivity implements
        View.OnClickListener, View.OnFocusChangeListener, LoadDataInputPipelineKPRListener, ProjectListener, KeyValueListener, AddressListener, CameraListener, ConfirmListener, TextWatcher,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener , GenericListenerOnSelect {

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

    //Tindak lanjut
    @BindView(R.id.rg_typetindaklanjut)
    RadioGroup rg_typetindaklanjut;

    @BindView(R.id.rb_byvisit)
    RadioButton rb_byvisit;

    @BindView(R.id.rb_bycall)
    RadioButton rb_bycall;

    @BindView(R.id.et_tindaklanjut)
    EditText et_tindaklanjut;

    @BindView(R.id.ll_ishotprospek)
    LinearLayout ll_ishotprospek;
    @BindView(R.id.sw_ishotprospek)
    Switch sw_ishotprospek;

    @BindView(R.id.btnfab_takepicture)
    FloatingActionButton btnfab_takepicture;

    @BindView(R.id.ll_rekening_developer)
    LinearLayout ll_rekening_developer;
    @BindView(R.id.tv_status_rekening_developer)
    TextView tv_status_rekening_developer;
    @BindView(R.id.bt_cek_rekening_developer)
    Button bt_cek_rekening_developer;

    private boolean rekeningDeveloperBerubah=false;


    private DatePickerDialog dp_tanggallahir;
    private Calendar calendar;
    private Calendar calendarMinDate;

    private List<MProgramKmg> listProgram=new ArrayList<>();
    private List<MGenericModel> dropdownProgram=new ArrayList<>();

    private final int PICK_IMAGE = 1;
    private final int TAKE_PICTURE = 0;
    private int CHANGE_PICTURE = 0;
    private Uri uriPhoto;
    private Bitmap bitmapPhoto;
    private String dataPribadi;
    private datapribadi dataPr;
    private String nik;
    private int hasFace = 0;

    //VALUE
    private int idFoto = 0;
    private String val_segmen = "";
    private String val_produk = "";
    private int val_program = 0;
    private int val_pihak_ketiga = 0;
    private int val_project = 0;
    private int val_tujuanpenggunaan = 0;
    private String val_desc_tujuan = "";
    private String val_plafond = "";
    private String val_tenor = "";
    private String val_nik = "";
    private String val_nama = "";
    private String val_tempatlahir = "";
    private String val_tanggallahir = "";
    private String val_nomorhp = "";
    private String val_alamat = "";
    private String val_rt = "";
    private String val_rw = "";
    private String val_provinsi = "";
    private String val_kota = "";
    private String val_kecamatan = "";
    private String val_kelurahan = "";
    private String val_kodepos = "";
    private String val_bidang_pekerjaan = "";
    private String val_jenis_pekerjaan = "";
    private String val_pendapatan = "";
    private String val_jenistindaklanjut = "";
    private String val_texttindaklanjut = "";
    private String val_urlphoto;

    //GPS
    private GPSTracker gpsTracker;
    private LocationManager locateManager;
    private Location mLastLocation;
    private LocationCallback mLocationCallback;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double lat, lng;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private int isHotprospek = 0;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    private List<MListPihakKetiga> listPihakKetiga;
    private ProjectModel projectModel;
    private List<MTujuanPenggunaan> listTujuanPenggunaan;
    private List<MListBidangPekerjaan> listBidangPekerjaan;
    private List<MListJenisKPR> listJenisKPR;

    private String nama, fid_pihak_ketiga, tipe;
    private int id_project;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konsumer_kpr_pipeline_input);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        projectModel = new ProjectModel(this);
        gpsTracker = new GPSTracker(this);
        collapsingToolbar.setStatusBarScrimColor(getResources().getColor(R.color.colorBackgroundTransparent));
//        setGps();
        backgroundStatusBar();
        checkCollapse();
        btn_back.setOnClickListener(this);
        btn_takepicture.setOnClickListener(this);
        btn_pickaddress.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        btnfab_takepicture.setOnClickListener(this);
        iv_photo.setOnClickListener(this);
        onChangeText();

        loadPihakKetiga();
        loadTujuanPenggunaan();
        loadBidangPekerjaan();
        loadJenisKPR();
        loadProgramKpr();

        //2 variabel ini dipantek, jadi dynamicnya di set di awal
        AppUtil.dynamicIconLogoChange(KonsumerKprPipelineInputActivity.this, et_segmen, tf_segmen);
        AppUtil.dynamicIconLogoChange(KonsumerKprPipelineInputActivity.this, et_produk, tf_produk);
        AppUtil.dynamicIconLogoChange(KonsumerKprPipelineInputActivity.this, et_paket, tf_paket);
        AppUtil.dynamicIconLogoChange(KonsumerKprPipelineInputActivity.this, et_program, tf_program);

        //pantekan kata mbak winda
        et_segmen.setText("KONSUMER");
        et_produk.setText("KPR");
        et_paket.setText("NON PAKET");
        et_program.setText("KPR Murabahah Reguler");

        et_segmen.setFocusable(false);

//        et_produk.setFocusable(false);
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
        calendarMinDate = Calendar.getInstance();
        calendarMinDate.add(Calendar.YEAR, -75);
    }

    private void setDynamicIcon() {
        AppUtil.dynamicIconLogoChange(KonsumerKprPipelineInputActivity.this, et_plafond, tf_plafond);
        AppUtil.dynamicIconLogoChange(KonsumerKprPipelineInputActivity.this, et_tenor, tf_tenor);
        AppUtil.dynamicIconLogoChange(KonsumerKprPipelineInputActivity.this, et_nik, tf_nik);
        AppUtil.dynamicIconLogoChange(KonsumerKprPipelineInputActivity.this, et_nama, tf_nama);
        AppUtil.dynamicIconLogoChange(KonsumerKprPipelineInputActivity.this, et_tempatlahir, tf_tempatlahir);
        AppUtil.dynamicIconLogoChange(KonsumerKprPipelineInputActivity.this, et_nomorhp, tf_nomorhp);
        AppUtil.dynamicIconLogoChange(KonsumerKprPipelineInputActivity.this, et_pendapatan, tf_pendapatan);
    }

    private void setDynamicIconDropDown() {
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKprPipelineInputActivity.this, tf_segmen, et_segmen);
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKprPipelineInputActivity.this, tf_produk, et_produk);
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKprPipelineInputActivity.this, tf_paket, et_paket);
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKprPipelineInputActivity.this, tf_program, et_program);
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKprPipelineInputActivity.this, tf_tanggallahir, et_tanggallahir);
    }

    private void setData() {
        Gson gson = new Gson();
        dataPr = gson.fromJson(dataPribadi, datapribadi.class);

        val_urlphoto = UriApi.Baseurl.URL + UriApi.foto.urlFile + dataPr.getFid_photo();
        Glide
                .with(KonsumerKprPipelineInputActivity.this)
                .asBitmap()
                .load(val_urlphoto)
                .fitCenter()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv_photo.setImageBitmap(resource);
                        bitmapPhoto = resource;
                        hasFace = AppUtil.hasFaceImage(bitmapPhoto);
                    }
                });
        idFoto = dataPr.getFid_photo();

        et_nama.setText(dataPr.getNama_nasabah());
        et_tempatlahir.setText(dataPr.getTempat_lahir());
        et_tanggallahir.setText(AppUtil.parseTanggalGeneral(dataPr.getTanggal_lahir(), "ddMMyyyy", "dd-MM-yyyy"));
        et_alamat.setText(dataPr.getAlamat_domisili());
        et_rt.setText(dataPr.getRt_domisili());
        et_rw.setText(dataPr.getRw_domisili());
        et_provinsi.setText(dataPr.getPropinsi_domisili());
        et_kota.setText(dataPr.getKota_domisili());
        et_kecamatan.setText(dataPr.getKecamatan_domisili());
        et_kelurahan.setText(dataPr.getKelurahan_domisili());
        et_kodepos.setText(dataPr.getKode_pos_domisili());
        val_tanggallahir = AppUtil.parseTanggalGeneral(dataPr.getTanggal_lahir(), "ddMMyyyy", "yyyy-MM-dd");

        setDynamicIconDropDown();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onclickSelectDialog();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorBackgroundTransparent));
        }
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

                            nik = getIntent().getStringExtra("nik");
                            if (getIntent().hasExtra("dataPribadi")) {
                                dataPribadi = getIntent().getStringExtra("dataPribadi");
                                setData();
                            }
                            et_nik.setText(nik);
                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKprPipelineInputActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void onclickSelectDialogPihakKetiga() {

        et_pihak_ketiga.setFocusable(false);
        et_pihak_ketiga.setInputType(InputType.TYPE_NULL);
        et_pihak_ketiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_pihak_ketiga.getLabelText().toString().trim(), listPihakKetiga, KonsumerKprPipelineInputActivity.this, "pihak ketiga");
            }
        });
        et_pihak_ketiga.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_pihak_ketiga.getLabelText().toString().trim(), listPihakKetiga, KonsumerKprPipelineInputActivity.this, "pihak ketiga");
            }
        });
        tf_pihak_ketiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_pihak_ketiga.getLabelText().toString().trim(), listPihakKetiga, KonsumerKprPipelineInputActivity.this, "pihak ketiga");
            }
        });
        tf_pihak_ketiga.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_pihak_ketiga.getLabelText().toString().trim(), listPihakKetiga, KonsumerKprPipelineInputActivity.this, "pihak ketiga");
            }
        });
    }

    @Override
    public void onSelectPihakKetiga(String title, MListPihakKetiga data) {
        if (title.equalsIgnoreCase(tf_pihak_ketiga.getLabelText().toString().trim())) {
            et_pihak_ketiga.setText(data.getNAMA());
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKprPipelineInputActivity.this, tf_pihak_ketiga, et_pihak_ketiga);
            val_pihak_ketiga = data.getID_PIHAK_KETIGA();

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
                                    fid_pihak_ketiga = objSegmen.get("FID_PIHAK_KETIGA").getAsString();
                                    tipe = objSegmen.get("tipe").getAsString();
                                    id_project = objSegmen.get("ID_PROJECT").getAsInt();

                                    projectModel.add(nama, fid_pihak_ketiga, tipe, id_project);
                                }
                            }
                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKprPipelineInputActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKprPipelineInputActivity.this, tf_proyek_perumahan, et_proyek_perumahan);
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
                            AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKprPipelineInputActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_tujuanpenggunaan.getLabelText().toString().trim(), listTujuanPenggunaan, KonsumerKprPipelineInputActivity.this, "tujuan");
            }
        });
        et_tujuanpenggunaan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_tujuanpenggunaan.getLabelText().toString().trim(), listTujuanPenggunaan, KonsumerKprPipelineInputActivity.this, "tujuan");
            }
        });
        tf_tujuanpenggunaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_tujuanpenggunaan.getLabelText().toString().trim(), listTujuanPenggunaan, KonsumerKprPipelineInputActivity.this, "tujuan");
            }
        });
        tf_tujuanpenggunaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_tujuanpenggunaan.getLabelText().toString().trim(), listTujuanPenggunaan, KonsumerKprPipelineInputActivity.this, "tujuan");
            }
        });
    }

    @Override
    public void onSelectTujuan(String title, MTujuanPenggunaan data) {
        if (title.equalsIgnoreCase(tf_tujuanpenggunaan.getLabelText().toString().trim())) {
            et_tujuanpenggunaan.setText(data.getDESC1());
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKprPipelineInputActivity.this, tf_tujuanpenggunaan, et_tujuanpenggunaan);
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
                            AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKprPipelineInputActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void onclickSelectDialogBidangPekerjaan() {

        et_bidang_pekerjaan.setFocusable(false);
        et_bidang_pekerjaan.setInputType(InputType.TYPE_NULL);
        et_bidang_pekerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_bidang_pekerjaan.getLabelText().toString().trim(), listBidangPekerjaan, KonsumerKprPipelineInputActivity.this, "bidang pekerjaan");
            }
        });
        et_bidang_pekerjaan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_bidang_pekerjaan.getLabelText().toString().trim(), listBidangPekerjaan, KonsumerKprPipelineInputActivity.this, "bidang pekerjaan");
            }
        });
        tf_bidang_pekerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_bidang_pekerjaan.getLabelText().toString().trim(), listBidangPekerjaan, KonsumerKprPipelineInputActivity.this, "bidang pekerjaan");
            }
        });
        tf_bidang_pekerjaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_bidang_pekerjaan.getLabelText().toString().trim(), listBidangPekerjaan, KonsumerKprPipelineInputActivity.this, "bidang pekerjaan");
            }
        });
    }

    @Override
    public void onSelectBidangPekerjaan(String title, MListBidangPekerjaan data) {
        if (title.equalsIgnoreCase(tf_bidang_pekerjaan.getLabelText().toString().trim())) {
            et_bidang_pekerjaan.setText(data.getDESC2());
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKprPipelineInputActivity.this, tf_bidang_pekerjaan, et_bidang_pekerjaan);
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
                            onclickSelectDialogJenisKPR();
                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKprPipelineInputActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void onclickSelectDialogJenisKPR() {

        et_jenis_pekerjaan.setFocusable(false);
        et_jenis_pekerjaan.setInputType(InputType.TYPE_NULL);
        et_jenis_pekerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_jenis_pekerjaan.getLabelText().toString().trim(), listJenisKPR, KonsumerKprPipelineInputActivity.this, "jenis kpr");
            }
        });
        et_jenis_pekerjaan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_jenis_pekerjaan.getLabelText().toString().trim(), listJenisKPR, KonsumerKprPipelineInputActivity.this, "jenis kpr");
            }
        });
        tf_jenis_pekerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_jenis_pekerjaan.getLabelText().toString().trim(), listJenisKPR, KonsumerKprPipelineInputActivity.this, "jenis kpr");
            }
        });
        tf_jenis_pekerjaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipelineKPR.display(getSupportFragmentManager(), tf_jenis_pekerjaan.getLabelText().toString().trim(), listJenisKPR, KonsumerKprPipelineInputActivity.this, "jenis kpr");
            }
        });
    }

    @Override
    public void onSelectJenisKPR(String title, MListJenisKPR data) {
        if (title.equalsIgnoreCase(tf_jenis_pekerjaan.getLabelText().toString().trim())) {
            et_jenis_pekerjaan.setText(data.getValue());
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKprPipelineInputActivity.this, tf_jenis_pekerjaan, et_jenis_pekerjaan);
            val_jenis_pekerjaan = data.getKey();
        }
    }

    private void checkCollapse() {
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangedListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state.name().equalsIgnoreCase("COLLAPSED")) {
                    tv_page_title.setVisibility(View.VISIBLE);
                    btn_takepicture.setVisibility(View.VISIBLE);
                    tv_page_title.setText("Input Pipeline");
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

//                    Toast.makeText(KonsumerKprPipelineInputActivity.this, "Tidak dapat mengubah program", Toast.LENGTH_LONG).show();



                DialogGenericDataFromService.display(getSupportFragmentManager(),tf_program.getLabelText(),dropdownProgram,KonsumerKprPipelineInputActivity.this);
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
                    AppUtil.notifwarning(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect) + " " + tf_pihak_ketiga.getLabelText() + " terlebih dahulu");
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
                AppUtil.dynamicIconLogoChangeDropdown(KonsumerKprPipelineInputActivity.this, tf_tanggallahir, et_tanggallahir);
                if (!et_tanggallahir.getText().toString().trim().isEmpty()) {
                    val_tanggallahir = formatServer.format(calendar.getTime()); //set value tanggal lahir
                }
            }
        };

        dp_tanggallahir = new DatePickerDialog(KonsumerKprPipelineInputActivity.this, R.style.AppTheme_TimePickerTheme, ls_tanggallahir, calendar.get(Calendar.YEAR),
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
                outputFileUri = FileProvider.getUriForFile(KonsumerKprPipelineInputActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "fotonasabah.png"));
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
                uploadFoto();
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

    public void uploadFoto() {
        loading.setVisibility(View.VISIBLE);
        String filename = appPreferences.getFotoNasabahName();
        if (CHANGE_PICTURE == 1) {
            ImageHandler.saveImageToCache(this, bitmapPhoto, filename);
            File imageFile = new File(getApplicationContext().getCacheDir(), filename);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
            MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);
            Call<ParseResponse> call = apiClientAdapter.getApiInterface().uploadFileOld(fileBody);
            call.enqueue(new Callback<ParseResponse>() {
                @Override
                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                    try {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase("00")) {
                                loading.setVisibility(View.GONE);
                                idFoto = response.body().getData().get("id").getAsInt();
//                                sendData(response.body().getData().get("id").getAsInt());
                            } else {
                                loading.setVisibility(View.GONE);
                                AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            }
                        } else {
                            loading.setVisibility(View.GONE);
                            Error error = ParseResponseError.confirmEror(response.errorBody());
                            AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ParseResponse> call, Throwable t) {
                    loading.setVisibility(View.GONE);
                    AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                }
            });
        } else {
            loading.setVisibility(View.GONE);
            idFoto = dataPr.getFid_photo();
//            sendData(dataPr.getFid_photo());
        }
    }

    public void processInputPipeline() {
        Log.d("here", "input pipeline");
        sendData(idFoto);
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {

    }

    private void sendData(int idPhoto) {
        loading.setVisibility(View.VISIBLE);

        val_segmen = et_segmen.getText().toString().trim();
        val_produk = et_produk.getText().toString().trim();
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


        KonsumerKPRInputPipeline req = new KonsumerKPRInputPipeline(String.valueOf(appPreferences.getUid()), 0, idPhoto, val_segmen, val_produk, val_program, val_pihak_ketiga, val_project, val_tujuanpenggunaan, val_desc_tujuan, AppUtil.parseLongWithDefault(val_plafond, 0), AppUtil.parseIntWithDefault(val_tenor, 0),
                val_nik, val_nama, val_tempatlahir, val_tanggallahir, val_nomorhp, String.valueOf(lat) + " " + String.valueOf(lng), val_alamat, val_provinsi, val_kota, val_kecamatan, val_kelurahan, val_kodepos, val_rt, val_rw, val_bidang_pekerjaan, val_jenis_pekerjaan, AppUtil.parseIntWithDefault(val_pendapatan, 0), val_jenistindaklanjut, val_texttindaklanjut);

        Call<ParseResponse> call = null;
        if (isHotprospek == 1) {
            call = apiClientAdapter.getApiInterface().savePipelineHotprospekKpr(req);
        } else {
            call = apiClientAdapter.getApiInterface().sendDataPipelineKpr(req);
        }

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            CustomDialog.DialogSuccess(KonsumerKprPipelineInputActivity.this, "Success!", "Input data Pipeline sukses", KonsumerKprPipelineInputActivity.this);
                        } else {
                            AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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
            AppUtil.notifwarning(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), "Pilih jenis tindak lanjut");
            return false;
        }

        Log.d("lolos", "tindak");

        if (!et_tindaklanjut.getText().toString().trim().isEmpty() && val_jenistindaklanjut.isEmpty()) {
            AppUtil.notifwarning(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), "Pilih jenis tindak lanjut");
            return false;
        }

        Log.d("lolos", "tindak 2");

        if (!val_jenistindaklanjut.isEmpty() && et_tindaklanjut.getText().toString().trim().isEmpty()) {
            AppUtil.notifwarning(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), "Isi deskripsi tindak lanjut");
            return false;
        }

        Log.d("lolos", "jenis");

        if (idFoto == 0) {
            AppUtil.notifWarningButton(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), "Silahkan pilih / ambil Foto").setAction("Upload", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCameraMenu();
                }
            });
            return false;
        }

        Log.d("lolos", "foto");
//
//        if (bitmapPhoto == null) {
//            AppUtil.notifwarning(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), "Silahkan pilih / ambil Foto");
//            return false;
//        }

        if(et_program.getText().toString().toUpperCase().contains("FLPP")){
            if(et_no_rekening_developer.getText().toString().isEmpty()){
                AppUtil.notifwarning(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), "Harap isi no rekening developer");
                tf_no_rekening_developer.setError("harap isi no rekening",true);
                return false;
            }
            else if (rekeningDeveloperBerubah) {
                AppUtil.notifwarning(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), "Ada perubahan di nomor rekening developer, silahkan klik cek no rekening terlebih dahulu");
                bt_cek_rekening_developer.requestFocus();
                return false;
            }

        }

        if (hasFace == 0) {
            AppUtil.notifwarning(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.title_nohasface));
            return false;
        }

        Log.d("lolos", "wajah");

        return true;
    }

    private void setGps() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
        locateManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!locateManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            dialogEnabledLocationService();
        }

        if (!isGooglePlayServicesAvailable()) {
            finish();
            AppUtil.showToastShort(this, "Google Play Services not available. Ending Test case.");
        }
        lat = gpsTracker.getLatitude();
        lng = gpsTracker.getLongitude();
        buildGoogleApiClient();
        mLocationRequest = LocationRequest.create();

        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setMaxWaitTime(3000);
        mLocationRequest.setInterval(5000);
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        if (mLastLocation != null) {
            lat = mLastLocation.getLatitude();
            lng = mLastLocation.getLongitude();
        } else {
            lat = gpsTracker.getLatitude();
            lng = gpsTracker.getLongitude();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        buildGoogleApiClient();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
        } else {
            lat = gpsTracker.getLatitude();
            lng = gpsTracker.getLongitude();
        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    public void dialogEnabledLocationService() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);
        dialog.setMessage("Silahkan izinkan aplikasi aktifkan GPS");
        dialog.setPositiveButton("Izinkan",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                });
        dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//                finish();
            }
        });
        dialog.show();
    }

    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS) {
            if (googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
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
                if (validateForm())
                    processInputPipeline();
                break;
            //PROGRAM
            case R.id.et_program:
            case R.id.tf_program:

                    Toast.makeText(KonsumerKprPipelineInputActivity.this, "Tidak dapat mengubah program", Toast.LENGTH_LONG).show();

//                DialogGenericDataFromService.display(getSupportFragmentManager(),tf_program.getLabelText(),dropdownProgram,this);
                break;
            //PROJECT
            case R.id.et_proyek_perumahan:
            case R.id.tf_proyek_perumahan:
                if (!et_pihak_ketiga.getText().toString().trim().equalsIgnoreCase("Pilih")) {
                    openProjectDialog(String.valueOf(val_pihak_ketiga), et_pihak_ketiga.getText().toString().trim());
                } else {
                    AppUtil.notifwarning(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect) + " " + tf_pihak_ketiga.getLabelText() + " terlebih dahulu");
                }
                break;

            case R.id.bt_cek_rekening_developer:
                rekeningDeveloperBerubah=false;
                Toast.makeText(this, "beep boop mengecek rekening nomor : "+et_no_rekening_developer.getText().toString(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(KonsumerKprPipelineInputActivity.this, "Tidak dapat mengubah program", Toast.LENGTH_LONG).show();
//                    openProdukDialog(tf_program.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), et_produk.getText().toString().trim());
                    }
                    break;
                //PROJECT
                case R.id.et_proyek_perumahan:
                case R.id.tf_proyek_perumahan:
                    if (!et_pihak_ketiga.getText().toString().trim().equalsIgnoreCase("Pilih")) {
                        openProjectDialog(String.valueOf(val_pihak_ketiga), et_pihak_ketiga.getText().toString().trim());
                    } else {
                        AppUtil.notifwarning(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect) + " " + tf_pihak_ketiga.getLabelText() + " terlebih dahulu");
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

    private void loadProgramKpr() {
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listProgramKpr(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<MProgramKmg>>() {
                            }.getType();

                            listProgram = gson.fromJson(listDataString, type);
                            for (int i = 0; i <listProgram.size() ; i++) {
                                dropdownProgram.add(new MGenericModel(Integer.toString(listProgram.get(i).getGIMMICK_ID()),listProgram.get(i).getPROGRAM_NAME()));
                            }

                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKprPipelineInputActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKprPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void otherViewChanges(){
        if(et_program.getText().toString().toUpperCase().contains("FLPP")){
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
        }
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

    @Override
    public void onSelect(String title, MGenericModel data) {
        if(title.equalsIgnoreCase(tf_program.getLabelText())){
            et_program.setText(data.getDESC());
            val_program=Integer.parseInt(data.getID());
            otherViewChanges();
        }
    }
}
