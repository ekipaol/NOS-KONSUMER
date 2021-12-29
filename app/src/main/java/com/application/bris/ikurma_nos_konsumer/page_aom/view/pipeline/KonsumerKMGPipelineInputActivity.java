package com.application.bris.ikurma_nos_konsumer.page_aom.view.pipeline;


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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.inquiryListKateg;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.inquiryTujuan;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogInputDataPipeline;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.LoadDataInputPipelineListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MKategoriNasabahPensiun;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MProgramKmg;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MRekananDM;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MTujuanPenggunaan;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.KonsumerKMGInputPipeline;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.inquiryInstansi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogAddress;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.AddressListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MlistInstansi;
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
import com.google.gson.Gson;
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

public class KonsumerKMGPipelineInputActivity extends AppCompatActivity implements
        View.OnClickListener, View.OnFocusChangeListener, LoadDataInputPipelineListener, KeyValueListener, AddressListener, CameraListener, ConfirmListener, TextWatcher,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

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
    @BindView(R.id.tf_program)
    TextFieldBoxes tf_program;
    @BindView(R.id.et_program)
    EditText et_program;
    @BindView(R.id.tf_institusi_pembayaran_gaji_pensiun)
    TextFieldBoxes tf_institusi_pembayaran_gaji_pensiun;
    @BindView(R.id.et_institusi_pembayaran_gaji_pensiun)
    EditText et_institusi_pembayaran_gaji_pensiun;
    @BindView(R.id.tf_direct_marketing)
    TextFieldBoxes tf_direct_marketing;
    @BindView(R.id.et_direct_marketing)
    EditText et_direct_marketing;
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
    @BindView(R.id.tf_instansi)
    TextFieldBoxes tf_instansi;
    @BindView(R.id.et_instansi)
    EditText et_instansi;
    @BindView(R.id.tf_bidang_pekerjaan)
    TextFieldBoxes tf_bidang_pekerjaan;
    @BindView(R.id.et_bidang_pekerjaan)
    EditText et_bidang_pekerjaan;
    @BindView(R.id.tf_jenis_pekerjaan)
    TextFieldBoxes tf_jenis_pekerjaan;
    @BindView(R.id.et_jenis_pekerjaan)
    EditText et_jenis_pekerjaan;
    @BindView(R.id.tf_kategori_calon_nasabah_pensiunan)
    TextFieldBoxes tf_kategori_calon_nasabah_pensiunan;
    @BindView(R.id.et_kategori_calon_nasabah_pensiunan)
    EditText et_kategori_calon_nasabah_pensiunan;
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

    @BindView(R.id.ll_data_kantor)
    LinearLayout ll_data_kantor;

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
    private String dataPribadi;
    private datapribadi dataPr;
    private String nik;
    private int hasFace = 0;

    //VALUE
    private int idFoto = 0;
    private String val_segmen = "";
    private String val_produk = "";
    private int val_program = 0;
    private String val_loan_type = "";
    private String val_institusi_pembayaran_gaji_pensiun = "";
    private String val_rekanan_direct_marketing = "";
    private int val_tujuanpenggunaan = 0;
    private String val_desc_tujuan = "";
    private String val_plafond = "";
    private String val_tenor = "";
    private String val_nik = "";
    private String val_nama = "";
    private String val_tempatlahir = "";
    private String val_tanggallahir = "";
    private String val_nomorhp = "";
    private int val_instansi = 0;
    private String val_bidang_pekerjaan = "";
    private String val_jenis_pekerjaan = "";
    private String val_kategori_pensiunan = "";
    private String val_pendapatan = "";
    private String val_alamat = "";
    private String val_rt = "";
    private String val_rw = "";
    private String val_provinsi = "";
    private String val_kota = "";
    private String val_kecamatan = "";
    private String val_kelurahan = "";
    private String val_kodepos = "";
    private String val_jenistindaklanjut = "";
    private String val_texttindaklanjut = "";
    private int val_embp = 0;
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

    private List<MProgramKmg> listProgram;
    private List<MKategoriNasabahPensiun> listInstitusi;
    private List<MRekananDM> listRekananDM;
    private List<MlistInstansi> listInstansi;
    private List<MTujuanPenggunaan> listTujuanPenggunaan;
    private List<MKategoriNasabahPensiun> listKategNasabah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konsumer_kmg_pipeline_input);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
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

        loadProgram();

        //2 variabel ini dipantek, jadi dynamicnya di set di awal
        AppUtil.dynamicIconLogoChange(KonsumerKMGPipelineInputActivity.this, et_segmen, tf_segmen);
        AppUtil.dynamicIconLogoChange(KonsumerKMGPipelineInputActivity.this, et_produk, tf_produk);

        //pantekan kata mbak winda
        et_segmen.setText("KONSUMER");
        et_produk.setText("KMG");

        et_segmen.setFocusable(false);
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
        AppUtil.dynamicIconLogoChange(KonsumerKMGPipelineInputActivity.this, et_plafond, tf_plafond);
        AppUtil.dynamicIconLogoChange(KonsumerKMGPipelineInputActivity.this, et_tenor, tf_tenor);
        AppUtil.dynamicIconLogoChange(KonsumerKMGPipelineInputActivity.this, et_nik, tf_nik);
        AppUtil.dynamicIconLogoChange(KonsumerKMGPipelineInputActivity.this, et_nama, tf_nama);
        AppUtil.dynamicIconLogoChange(KonsumerKMGPipelineInputActivity.this, et_tempatlahir, tf_tempatlahir);
        AppUtil.dynamicIconLogoChange(KonsumerKMGPipelineInputActivity.this, et_nomorhp, tf_nomorhp);
        AppUtil.dynamicIconLogoChange(KonsumerKMGPipelineInputActivity.this, et_pendapatan, tf_pendapatan);

    }

    private void setDynamicIconDropDown() {
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_segmen, et_segmen);
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_produk, et_produk);
//        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_program, et_program);
//        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_institusi_pembayaran_gaji_pensiun, et_institusi_pembayaran_gaji_pensiun);
//        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_direct_marketing, et_direct_marketing);
//        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_tujuanpenggunaan, et_tujuanpenggunaan);
        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_tanggallahir, et_tanggallahir);
//        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_instansi, et_instansi);
//        AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_kategori_calon_nasabah_pensiunan, et_kategori_calon_nasabah_pensiunan);
    }

    private void setData() {
        Gson gson = new Gson();
        dataPr = gson.fromJson(dataPribadi, datapribadi.class);

        val_urlphoto = UriApi.Baseurl.URL + UriApi.foto.urlFile + dataPr.getFid_photo();
        Glide
                .with(KonsumerKMGPipelineInputActivity.this)
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

    private void loadProgram() {
        loading.setVisibility(View.VISIBLE);
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().getListProgram(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                try {
                    if (response.isSuccessful()) {
                        loading.setVisibility(View.GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<MProgramKmg>>() {
                            }.getType();

                            listProgram = gson.fromJson(listDataString, type);

                            setDynamicIcon();
                            onclickSelectDialogProgram();

                            nik = getIntent().getStringExtra("nik");
                            if (getIntent().hasExtra("dataPribadi")) {
                                dataPribadi = getIntent().getStringExtra("dataPribadi");
                                setData();
                            }
                            et_nik.setText(nik);

                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        loading.setVisibility(View.GONE);
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                loading.setVisibility(View.GONE);
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKMGPipelineInputActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void onclickSelectDialogProgram() {

        et_program.setFocusable(false);
        et_program.setInputType(InputType.TYPE_NULL);
        et_program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_program.getLabelText().toString().trim(), listProgram, KonsumerKMGPipelineInputActivity.this, "program");
            }
        });
        et_program.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_program.getLabelText().toString().trim(), listProgram, KonsumerKMGPipelineInputActivity.this, "program");
            }
        });
        tf_program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_program.getLabelText().toString().trim(), listProgram, KonsumerKMGPipelineInputActivity.this, "program");
            }
        });
        tf_program.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_program.getLabelText().toString().trim(), listProgram, KonsumerKMGPipelineInputActivity.this, "program");
            }
        });
    }

    @Override
    public void onSelectProgram(String title, MProgramKmg data) {
        //kosongin lagi field kateg nasabah kalau reset program
        et_kategori_calon_nasabah_pensiunan.setText("");
        val_kategori_pensiunan="";
        if (title.equalsIgnoreCase(tf_program.getLabelText().toString().trim())) {
            et_program.setText(data.getPROGRAM_NAME());
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_program, et_program);
            val_produk = data.getLOAN_TYPE();
            val_program = data.getGIMMICK_ID();
            val_loan_type = data.getLOAN_TYPE();

            if (data.getGIMMICK_ID() == 1 || data.getGIMMICK_ID() == 26 || data.getGIMMICK_ID() == 27 || data.getGIMMICK_ID() == 28) {
                val_embp = 1;
                et_instansi.setInputType(InputType.TYPE_NULL);
                et_instansi.setText("Pilih");
                tf_bidang_pekerjaan.setVisibility(View.VISIBLE);
                tf_jenis_pekerjaan.setVisibility(View.VISIBLE);

                if (data.getGIMMICK_ID() == 1) {
                    tf_institusi_pembayaran_gaji_pensiun.setVisibility(View.GONE);
                    tf_direct_marketing.setVisibility(View.GONE);
                    tf_kategori_calon_nasabah_pensiunan.setVisibility(View.GONE);
                } else {
                    tf_institusi_pembayaran_gaji_pensiun.setVisibility(View.VISIBLE);
                    loadInstitusi(data.getLOAN_TYPE());
                    tf_direct_marketing.setVisibility(View.VISIBLE);
                    loadRekananDM();
                    tf_kategori_calon_nasabah_pensiunan.setVisibility(View.VISIBLE);
                    loadKategoriNasabah(data.getLOAN_TYPE());
                }

                loadDataInstansi("aktif");
            } else {
                val_embp = 2;
                et_instansi.setInputType(InputType.TYPE_NULL);
                et_instansi.setText("Pilih");
                tf_bidang_pekerjaan.setVisibility(View.GONE);
                tf_jenis_pekerjaan.setVisibility(View.GONE);
                ll_data_kantor.setVisibility(View.GONE);
                tf_institusi_pembayaran_gaji_pensiun.setVisibility(View.VISIBLE);
                loadInstitusi(data.getLOAN_TYPE());
                tf_direct_marketing.setVisibility(View.VISIBLE);
                loadRekananDM();
                tf_kategori_calon_nasabah_pensiunan.setVisibility(View.VISIBLE);
                loadKategoriNasabah(data.getLOAN_TYPE());

                loadDataInstansi("tidak aktif");
            }
            loadTujuanPenggunaan(data.getLOAN_TYPE());
        }
    }

    private void loadInstitusi(String loanType) {
        loading.setVisibility(View.VISIBLE);
        inquiryTujuan req = new inquiryTujuan();
        req.setLoan_type(loanType);
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listInstitusi(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<MKategoriNasabahPensiun>>() {
                            }.getType();

                            listInstitusi = gson.fromJson(listDataString, type);
                            onclickSelectDialogInstitusi();
                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                loading.setVisibility(View.GONE);
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKMGPipelineInputActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void onclickSelectDialogInstitusi() {

        et_institusi_pembayaran_gaji_pensiun.setFocusable(false);
        et_institusi_pembayaran_gaji_pensiun.setInputType(InputType.TYPE_NULL);
        et_institusi_pembayaran_gaji_pensiun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_institusi_pembayaran_gaji_pensiun.getLabelText().toString().trim(), listInstitusi, KonsumerKMGPipelineInputActivity.this, "institusi");
            }
        });
        et_institusi_pembayaran_gaji_pensiun.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_institusi_pembayaran_gaji_pensiun.getLabelText().toString().trim(), listInstitusi, KonsumerKMGPipelineInputActivity.this, "institusi");
            }
        });
        tf_institusi_pembayaran_gaji_pensiun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_institusi_pembayaran_gaji_pensiun.getLabelText().toString().trim(), listInstitusi, KonsumerKMGPipelineInputActivity.this, "institusi");
            }
        });
        tf_institusi_pembayaran_gaji_pensiun.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_institusi_pembayaran_gaji_pensiun.getLabelText().toString().trim(), listInstitusi, KonsumerKMGPipelineInputActivity.this, "institusi");
            }
        });
    }

    @Override
    public void onSelectInstitusi(String title, MKategoriNasabahPensiun data) {
        if (title.equalsIgnoreCase(tf_institusi_pembayaran_gaji_pensiun.getLabelText().toString().trim())) {
            et_institusi_pembayaran_gaji_pensiun.setText(data.getNama());
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_institusi_pembayaran_gaji_pensiun, et_institusi_pembayaran_gaji_pensiun);
            val_institusi_pembayaran_gaji_pensiun = data.getID();
        }
    }

    private void loadRekananDM() {
        loading.setVisibility(View.VISIBLE);
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().getListRekananDM(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<MRekananDM>>() {
                            }.getType();

                            listRekananDM = gson.fromJson(listDataString, type);
                            onclickSelectDialogRekananDM();
                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                loading.setVisibility(View.GONE);
                t.printStackTrace();
                Toast.makeText(KonsumerKMGPipelineInputActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void onclickSelectDialogRekananDM() {

        et_direct_marketing.setFocusable(false);
        et_direct_marketing.setInputType(InputType.TYPE_NULL);
        et_direct_marketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_direct_marketing.getLabelText().toString().trim(), listRekananDM, KonsumerKMGPipelineInputActivity.this, "rekanan dm");
            }
        });
        et_direct_marketing.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_direct_marketing.getLabelText().toString().trim(), listRekananDM, KonsumerKMGPipelineInputActivity.this, "rekanan dm");
            }
        });
        tf_direct_marketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_direct_marketing.getLabelText().toString().trim(), listRekananDM, KonsumerKMGPipelineInputActivity.this, "rekanan dm");
            }
        });
        tf_direct_marketing.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_direct_marketing.getLabelText().toString().trim(), listRekananDM, KonsumerKMGPipelineInputActivity.this, "rekanan dm");
            }
        });
    }

    @Override
    public void onSelectRekananDM(String title, MRekananDM data) {
        if (title.equalsIgnoreCase(tf_direct_marketing.getLabelText().toString().trim())) {
            et_direct_marketing.setText(data.getREKANAN_DIRECT_MARKETING());
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_direct_marketing, et_direct_marketing);
            val_rekanan_direct_marketing = data.getID_REKANAN();
        }
    }

    private void loadKategoriNasabah(String loanType) {
        loading.setVisibility(View.VISIBLE);
        inquiryListKateg req=new inquiryListKateg();
        req.setLoan_type(loanType);
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().getListKategNasabah(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<MKategoriNasabahPensiun>>() {
                            }.getType();

                            listKategNasabah = gson.fromJson(listDataString, type);
                            onclickSelectDialogKategoriNasabah();
                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                loading.setVisibility(View.GONE);
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKMGPipelineInputActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void onclickSelectDialogKategoriNasabah() {

        et_kategori_calon_nasabah_pensiunan.setFocusable(false);
        et_kategori_calon_nasabah_pensiunan.setInputType(InputType.TYPE_NULL);
        et_kategori_calon_nasabah_pensiunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_kategori_calon_nasabah_pensiunan.getLabelText().toString().trim(), listKategNasabah, KonsumerKMGPipelineInputActivity.this, "kategori nasabah");
            }
        });
        et_kategori_calon_nasabah_pensiunan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_kategori_calon_nasabah_pensiunan.getLabelText().toString().trim(), listKategNasabah, KonsumerKMGPipelineInputActivity.this, "kategori nasabah");
            }
        });
        tf_kategori_calon_nasabah_pensiunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_kategori_calon_nasabah_pensiunan.getLabelText().toString().trim(), listKategNasabah, KonsumerKMGPipelineInputActivity.this, "kategori nasabah");
            }
        });
        tf_kategori_calon_nasabah_pensiunan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_kategori_calon_nasabah_pensiunan.getLabelText().toString().trim(), listKategNasabah, KonsumerKMGPipelineInputActivity.this, "kategori nasabah");
            }
        });
    }

    @Override
    public void onSelectKategNasabah(String title, MKategoriNasabahPensiun data) {
        if (title.equalsIgnoreCase(tf_kategori_calon_nasabah_pensiunan.getLabelText().toString().trim())) {
            et_kategori_calon_nasabah_pensiunan.setText(data.getNama());
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_kategori_calon_nasabah_pensiunan, et_kategori_calon_nasabah_pensiunan);
            val_kategori_pensiunan = data.getNama();
        }
    }

    private void loadDataInstansi(final String aktif) {
        loading.setVisibility(View.VISIBLE);
        inquiryInstansi req = new inquiryInstansi(appPreferences.getKodeKantor());
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().getListInstansi(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                loading.setVisibility(View.GONE);

                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<MlistInstansi>>() {
                            }.getType();

                            listInstansi = gson.fromJson(listDataString, type);
                            onclickSelectDialogInstansi(aktif);

                        } else {
//                            finish();
                            AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                loading.setVisibility(View.GONE);
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKMGPipelineInputActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void onclickSelectDialogInstansi(final String aktif) {
        //INSTANSI
        if (aktif.equalsIgnoreCase("aktif")) {
            et_instansi.setFocusable(false);
            et_instansi.setInputType(InputType.TYPE_NULL);
            et_instansi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInputDataPipeline.display(getSupportFragmentManager(), tf_instansi.getLabelText().toString().trim(), listInstansi, KonsumerKMGPipelineInputActivity.this, "instansi");
                }
            });
            et_instansi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    DialogInputDataPipeline.display(getSupportFragmentManager(), tf_instansi.getLabelText().toString().trim(), listInstansi, KonsumerKMGPipelineInputActivity.this, "instansi");
                }
            });
            tf_instansi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInputDataPipeline.display(getSupportFragmentManager(), tf_instansi.getLabelText().toString().trim(), listInstansi, KonsumerKMGPipelineInputActivity.this, "instansi");
                }
            });
            tf_instansi.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInputDataPipeline.display(getSupportFragmentManager(), tf_instansi.getLabelText().toString().trim(), listInstansi, KonsumerKMGPipelineInputActivity.this, "instansi");
                }
            });
        } else {
            et_instansi.setFocusable(false);
            et_instansi.setInputType(InputType.TYPE_NULL);
            et_instansi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openKeyValueDialog(tf_instansi.getLabelText().toString().trim());
                }
            });
            et_instansi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    openKeyValueDialog(tf_instansi.getLabelText().toString().trim());
                }
            });
            tf_instansi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openKeyValueDialog(tf_instansi.getLabelText().toString().trim());
                }
            });
            tf_instansi.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openKeyValueDialog(tf_instansi.getLabelText().toString().trim());
                }
            });
        }
    }

    @Override
    public void onSelectInstansi(String title, MlistInstansi data) {
        if (title.equalsIgnoreCase(tf_instansi.getLabelText().toString().trim())) {
            et_instansi.setText(data.getNAMA());
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_instansi, et_instansi);
            val_instansi = data.getID_INSTANSI();
            et_bidang_pekerjaan.setText(data.getDESC_BIDANG_PEKERJAAN());
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_bidang_pekerjaan, et_bidang_pekerjaan);
            val_bidang_pekerjaan = data.getFID_BIDANG_PEKERJAAN();
            et_jenis_pekerjaan.setText("Karyawan/PNS Program EmBP");
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_jenis_pekerjaan, et_jenis_pekerjaan);
            val_jenis_pekerjaan = "Karyawan/PNS Program EmBP";
        }
    }

    private void loadTujuanPenggunaan(String loanType) {
        loading.setVisibility(View.VISIBLE);
        inquiryTujuan req = new inquiryTujuan();
        req.setLoan_type(loanType);
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().getTujuanPenggunaan(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                loading.setVisibility(View.GONE);
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
                            AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                loading.setVisibility(View.GONE);
//                finish();
                t.printStackTrace();
                Toast.makeText(KonsumerKMGPipelineInputActivity.this, "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_tujuanpenggunaan.getLabelText().toString().trim(), listTujuanPenggunaan, KonsumerKMGPipelineInputActivity.this, "tujuan");
            }
        });
        et_tujuanpenggunaan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_tujuanpenggunaan.getLabelText().toString().trim(), listTujuanPenggunaan, KonsumerKMGPipelineInputActivity.this, "tujuan");
            }
        });
        tf_tujuanpenggunaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_tujuanpenggunaan.getLabelText().toString().trim(), listTujuanPenggunaan, KonsumerKMGPipelineInputActivity.this, "tujuan");
            }
        });
        tf_tujuanpenggunaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInputDataPipeline.display(getSupportFragmentManager(), tf_tujuanpenggunaan.getLabelText().toString().trim(), listTujuanPenggunaan, KonsumerKMGPipelineInputActivity.this, "tujuan");
            }
        });
    }

    @Override
    public void onSelectTujuan(String title, MTujuanPenggunaan data) {
        if (title.equalsIgnoreCase(tf_tujuanpenggunaan.getLabelText().toString().trim())) {
            et_tujuanpenggunaan.setText(data.getDESC1());
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_tujuanpenggunaan, et_tujuanpenggunaan);
            val_tujuanpenggunaan = data.getID_TUJUAN();
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

    private void openKeyValueDialog(String title) {
        DialogKeyValue.display(getSupportFragmentManager(), title, this);
    }

    private void openAddressDialog() {
        DialogAddress.display(getSupportFragmentManager(), this);
    }

    private void openCameraMenu() {
        BSBottomCamera.displayWithTitle(getSupportFragmentManager(), this, "Foto Nasabah");
    }

    private void onclickSelectDialog() {
        //PRODUCT
        et_produk.setFocusable(false);
        et_produk.setInputType(InputType.TYPE_NULL);
        et_produk.setOnFocusChangeListener(this);
        tf_produk.setOnClickListener(this);
        tf_produk.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_segmen.getText().toString().trim().isEmpty()) {
                    Toast.makeText(KonsumerKMGPipelineInputActivity.this, "Hanya produk KMG yang dapat ditambahkan saat ini", Toast.LENGTH_LONG).show();

//                    openProdukDialog(tf_produk.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), "");
                } else {
                    AppUtil.notifwarning(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect) + " " + tf_segmen.getLabelText() + " terlebih dahulu");
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
    }

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
                AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_tanggallahir, et_tanggallahir);
                if (!et_tanggallahir.getText().toString().trim().isEmpty()) {
                    val_tanggallahir = formatServer.format(calendar.getTime()); //set value tanggal lahir
                }
            }
        };

        dp_tanggallahir = new DatePickerDialog(KonsumerKMGPipelineInputActivity.this, R.style.AppTheme_TimePickerTheme, ls_tanggallahir, calendar.get(Calendar.YEAR),
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
                outputFileUri = FileProvider.getUriForFile(KonsumerKMGPipelineInputActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "fotonasabah.png"));
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
                                AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            }
                        } else {
                            loading.setVisibility(View.GONE);
                            Error error = ParseResponseError.confirmEror(response.errorBody());
                            AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ParseResponse> call, Throwable t) {
                    loading.setVisibility(View.GONE);
                    AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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

        if (title.equalsIgnoreCase("Instansi/Koperasi")) {
            et_instansi.setText(data.getName());
            val_instansi = Integer.valueOf(data.getValue());
            AppUtil.dynamicIconLogoChangeDropdown(KonsumerKMGPipelineInputActivity.this, tf_instansi, et_instansi);
        }
    }

    private void sendData(int idPhoto) {
        loading.setVisibility(View.VISIBLE);

        val_segmen = et_segmen.getText().toString().trim();
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


        KonsumerKMGInputPipeline req = new KonsumerKMGInputPipeline(String.valueOf(appPreferences.getUid()), 0, idPhoto, val_segmen, val_produk, AppUtil.parseIntWithDefault(String.valueOf(val_program), 0), val_loan_type, val_institusi_pembayaran_gaji_pensiun, val_rekanan_direct_marketing, val_tujuanpenggunaan, val_desc_tujuan, AppUtil.parseLongWithDefault(val_plafond, 0), AppUtil.parseIntWithDefault(val_tenor, 0),
                val_nik, val_nama, val_tempatlahir, val_tanggallahir, val_nomorhp, val_instansi, val_bidang_pekerjaan, val_jenis_pekerjaan, val_kategori_pensiunan, AppUtil.parseIntWithDefault(val_pendapatan, 0), val_alamat, val_provinsi, val_kota, val_kecamatan, val_kelurahan, val_kodepos, val_rt, val_rw,
                String.valueOf(lat) + " " + String.valueOf(lng), val_jenistindaklanjut, val_texttindaklanjut, 1, val_embp);

//        req.setUid(String.valueOf(appPreferences.getUid());
//        req.setId(0);
//        req.setFidPhoto(idPhoto);
//        req.setSegmen(val_segmen);
//        req.setJenisProduk(val_produk);
//        req.setGimmick(AppUtil.parseIntWithDefault(String.valueOf(val_program),0);
//        req.setFidTujuanPenggunaan(val_loan_type);
//        req.setDescTujuanPenggunaan(val_desc_tujuan);
//        req.setRencanaPlafond(AppUtil.parseIntWithDefault(val_plafond, 0));
//        req.setTenor( AppUtil.parseIntWithDefault(val_tenor, 0));
//        req.setNoKtp(val_nik);
//        req.setNamaNasabah(val_nama);
//        req.setTptLahir(val_tempatlahir);
//        req.setTglLahir(val_tanggallahir);
//        req.setNoHp(val_nomorhp);
//        req.setFidInstansi(val_instansi);
//        req.setJenisUsaha(val_bidang_pekerjaan);
//        req.setJenisKMG(val_jenis_pekerjaan);
//        req.setOmzetPerHari();
//        req.setAlamat();
//        req.setProv();
//        req.setKota();
//        req.setKec();
//        req.setKel();
//        req.setKodePos();
//        req.setRt();
//        req.setRw();
//        req.setLokasiGps();
//        req.setJenisTindak();
//        req.setTindakLanjut();
//        req.setFidPihakKetiga();
//        req.setFlagEMBP();


        Call<ParseResponse> call = null;
        if (isHotprospek == 1) {
            call = apiClientAdapter.getApiInterface().savePipelineHotprospekKonsumer(req);
        } else {
            call = apiClientAdapter.getApiInterface().sendDataPipelineKonsumer(req);
        }

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            CustomDialog.DialogSuccess(KonsumerKMGPipelineInputActivity.this, "Success!", "Input data Pipeline sukses", KonsumerKMGPipelineInputActivity.this);
                        } else {
                            AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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

        if (et_program.getText().toString().trim().equalsIgnoreCase("Pilih")) {
            tf_program.setError(tf_program.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "program");

        if (tf_institusi_pembayaran_gaji_pensiun.getVisibility() == View.VISIBLE && et_institusi_pembayaran_gaji_pensiun.getText().toString().trim().equalsIgnoreCase("Pilih")) {
            tf_institusi_pembayaran_gaji_pensiun.setError(tf_institusi_pembayaran_gaji_pensiun.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }
        Log.d("lolos", "institusi");

        if (tf_direct_marketing.getVisibility() == View.VISIBLE && et_direct_marketing.getText().toString().trim().equalsIgnoreCase("Pilih")) {
            tf_direct_marketing.setError(tf_direct_marketing.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }
        Log.d("lolos", "dm");

        if (et_tujuanpenggunaan.getText().toString().trim().equalsIgnoreCase("Pilih")) {
            tf_tujuanpenggunaan.setError(tf_tujuanpenggunaan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }
        Log.d("lolos", "tujuan");

        if (!et_plafond.getText().toString().trim().isEmpty() || !et_plafond.getText().toString().trim().equalsIgnoreCase("")) {
            long plafond = Long.valueOf(NumberTextWatcherForThousand.trimCommaOfString(et_plafond.getText().toString().trim()));
            long pendapatan = Long.valueOf(NumberTextWatcherForThousand.trimCommaOfString(et_pendapatan.getText().toString().trim()));
            long plafond_minimal = 2 * pendapatan;
            String plafond_min = Long.toString(plafond_minimal);
            if (plafond < (2 * pendapatan)) {
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

        if (et_tanggallahir.getText().toString().trim().isEmpty() || et_tanggallahir.getText().toString().trim().equalsIgnoreCase("")) {
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

        if (et_instansi.getText().toString().trim().equalsIgnoreCase("Pilih")) {
            tf_instansi.setError(tf_instansi.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "instansi");

        if (tf_bidang_pekerjaan.getVisibility() == View.VISIBLE && et_bidang_pekerjaan.getText().toString().trim().equalsIgnoreCase("")) {
            tf_bidang_pekerjaan.setError(tf_bidang_pekerjaan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "bidang");

        if (tf_jenis_pekerjaan.getVisibility() == View.VISIBLE && et_jenis_pekerjaan.getText().toString().trim().equalsIgnoreCase("")) {
            tf_jenis_pekerjaan.setError(tf_jenis_pekerjaan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "jenis");

        if (tf_kategori_calon_nasabah_pensiunan.getVisibility() == View.VISIBLE && et_kategori_calon_nasabah_pensiunan.getText().toString().trim().equalsIgnoreCase("Pilih")) {
            tf_kategori_calon_nasabah_pensiunan.setError(tf_kategori_calon_nasabah_pensiunan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "kateg");

        if (et_pendapatan.getText().toString().trim().isEmpty() || et_pendapatan.getText().toString().trim().equalsIgnoreCase("")) {
            tf_pendapatan.setError(tf_pendapatan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "pendapatan");

        if (ll_data_kantor.getVisibility() == View.VISIBLE &&(et_alamat.getText().toString().trim().isEmpty() || et_alamat.getText().toString().trim().equalsIgnoreCase(""))) {
            tf_alamat.setError(tf_alamat.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "alamat");

        if (ll_data_kantor.getVisibility() == View.VISIBLE &&(et_provinsi.getText().toString().trim().isEmpty() || et_provinsi.getText().toString().trim().equalsIgnoreCase(""))) {
            tf_provinsi.setError(tf_provinsi.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return false;
        }

        Log.d("lolos", "prov");

        if (et_tindaklanjut.getText().toString().trim().isEmpty() && val_jenistindaklanjut.isEmpty()) {
            AppUtil.notifwarning(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), "Pilih jenis tindak lanjut");
            return false;
        }

        Log.d("lolos", "tindak");

        if (!et_tindaklanjut.getText().toString().trim().isEmpty() && val_jenistindaklanjut.isEmpty()) {
            AppUtil.notifwarning(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), "Pilih jenis tindak lanjut");
            return false;
        }

        Log.d("lolos", "tindak 2");

        if (!val_jenistindaklanjut.isEmpty() && et_tindaklanjut.getText().toString().trim().isEmpty()) {
            AppUtil.notifwarning(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), "Isi deskripsi tindak lanjut");
            return false;
        }

        Log.d("lolos", "jenis");

        if (idFoto == 0) {
            AppUtil.notifWarningButton(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), "Silahkan pilih / ambil Foto").setAction("Upload", new View.OnClickListener() {
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

        if (hasFace == 0) {
            AppUtil.notifwarning(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.title_nohasface));
            return false;
        }

        Log.d("lolos", "wajah");

        return true;
    }

//    private boolean validateFormIsHotprospek() {
//        if (et_segmen.getText().toString().trim().isEmpty() || et_segmen.getText().toString().trim().equalsIgnoreCase("")) {
//            tf_segmen.setError(tf_segmen.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }
//
//        if (et_produk.getText().toString().trim().isEmpty() || et_produk.getText().toString().trim().equalsIgnoreCase("")) {
//            tf_produk.setError(tf_produk.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }
//
//        if (et_program.getVisibility() == View.VISIBLE && et_program.getText().toString().trim().isEmpty()) {
//            tf_program.setError(tf_program.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }
//
//        if (et_tujuanpenggunaan.getVisibility() == View.VISIBLE && et_tujuanpenggunaan.getText().toString().trim().isEmpty()) {
//            tf_tujuanpenggunaan.setError(tf_tujuanpenggunaan.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }
//
//        if (!et_plafond.getText().toString().trim().isEmpty() || !et_plafond.getText().toString().trim().equalsIgnoreCase("")) {
//            long plafond = Long.valueOf(NumberTextWatcherForThousand.trimCommaOfString(et_plafond.getText().toString().trim()));
//            long pendapatan = Long.valueOf(NumberTextWatcherForThousand.trimCommaOfString(et_pendapatan.getText().toString().trim()));
//            long plafond_minimal = 2 * pendapatan;
//            String plafond_min = Long.toString(plafond_minimal);
//            if (plafond < (2 * pendapatan)) {
//                tf_plafond.setError("Plafond minimal Rp. " + AppUtil.parseRupiah(plafond_min), true);
//                return false;
//            }
//        } else if (et_plafond.getText().toString().trim().isEmpty() || et_plafond.getText().toString().trim().equalsIgnoreCase("")) {
//            tf_plafond.setError(tf_plafond.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }
//
//        if (!et_tenor.getText().toString().trim().isEmpty() || !et_tenor.getText().toString().trim().equalsIgnoreCase("")) {
//            if (Integer.valueOf(et_tenor.getText().toString().trim()) < 12) {
//                tf_tenor.setError("Tenor minimal 12 bulan", true);
//                return false;
//            }
//        } else if (et_tenor.getText().toString().trim().isEmpty() || et_tenor.getText().toString().trim().equalsIgnoreCase("")) {
//            tf_tenor.setError(tf_tenor.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }
//
//        if (!et_nik.getText().toString().trim().isEmpty() || !et_nik.getText().toString().trim().equalsIgnoreCase("")) {
//            if (Validator.validateKtp(et_nik.getText().toString().trim()) == false) {
//                tf_nik.setError("Format " + tf_nik.getLabelText() + " " + getString(R.string.title_invalid_field), true);
//                return false;
//            }
//        } else if (et_nik.getText().toString().trim().isEmpty() || et_nik.getText().toString().trim().equalsIgnoreCase("")) {
//            tf_nik.setError(tf_nik.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }
//
//        if (et_nama.getText().toString().trim().isEmpty() || et_nama.getText().toString().trim().equalsIgnoreCase("")) {
//            tf_nama.setError(tf_nama.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }
//
//        if (et_tempatlahir.getText().toString().trim().isEmpty() || et_tempatlahir.getText().toString().trim().equalsIgnoreCase("")) {
//            tf_tempatlahir.setError(tf_tempatlahir.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }
//
//        if (et_tanggallahir.getText().toString().trim().isEmpty() || et_tanggallahir.getText().toString().trim().equalsIgnoreCase("")) {
//            tf_tanggallahir.setError(tf_tanggallahir.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }
//
//        if (!et_nomorhp.getText().toString().trim().isEmpty() || !et_nomorhp.getText().toString().trim().equalsIgnoreCase("")) {
//            if (Validator.validateNomorHp(et_nomorhp.getText().toString().trim()) == false) {
//                tf_nomorhp.setError("Format " + tf_nomorhp.getLabelText() + " " + getString(R.string.title_invalid_field), true);
//                return false;
//            }
//        } else if (et_nomorhp.getText().toString().trim().isEmpty() || et_nomorhp.getText().toString().trim().equalsIgnoreCase("")) {
//            tf_nomorhp.setError(tf_nomorhp.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }
//
//        if (et_instansi.getText().toString().trim().isEmpty() || et_instansi.getText().toString().trim().equalsIgnoreCase("")) {
//            tf_instansi.setError(tf_instansi.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }
//
//        if (et_bidang_pekerjaan.getText().toString().trim().isEmpty() || et_bidang_pekerjaan.getText().toString().trim().equalsIgnoreCase("")) {
//            tf_bidang_pekerjaan.setError(tf_bidang_pekerjaan.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }
//
//        if (et_jenis_pekerjaan.getText().toString().trim().isEmpty() || et_jenis_pekerjaan.getText().toString().trim().equalsIgnoreCase("")) {
//            tf_jenis_pekerjaan.setError(tf_jenis_pekerjaan.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }
//
//        if (et_pendapatan.getText().toString().trim().isEmpty() || et_pendapatan.getText().toString().trim().equalsIgnoreCase("")) {
//            tf_pendapatan.setError(tf_pendapatan.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }
//
//        if (et_alamat.getText().toString().trim().isEmpty() || et_alamat.getText().toString().trim().equalsIgnoreCase("")) {
//            tf_alamat.setError(tf_alamat.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }
//
//        if (et_provinsi.getText().toString().trim().isEmpty() || et_provinsi.getText().toString().trim().equalsIgnoreCase("")) {
//            tf_provinsi.setError(tf_provinsi.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return false;
//        }
//
//        if (et_tindaklanjut.getText().toString().trim().isEmpty() && val_jenistindaklanjut.isEmpty()) {
//            AppUtil.notifwarning(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), "Pilih jenis tindak lanjut");
//            return false;
//        }
//
//        if (!et_tindaklanjut.getText().toString().trim().isEmpty() && val_jenistindaklanjut.isEmpty()) {
//            AppUtil.notifwarning(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), "Pilih jenis tindak lanjut");
//            return false;
//        }
//
//        if (!val_jenistindaklanjut.isEmpty() && et_tindaklanjut.getText().toString().trim().isEmpty()) {
//            AppUtil.notifwarning(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), "Isi deskripsi tindak lanjut");
//            return false;
//        }
//
//        if (bitmapPhoto == null) {
//            AppUtil.notifwarning(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), "Silahkan pilih / ambil Foto");
//            return false;
//        }
//
//        if (hasFace == 0) {
//            AppUtil.notifwarning(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.title_nohasface));
//            return false;
//        }
//        return true;
//    }

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
            startActivity(new Intent(this, KonsumerKMGPipelineActivity.class));
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
//                if (isHotprospek == 1) {
//                    if (validateFormIsHotprospek())
//                        processInputPipeline();
//                } else {
//                    if (validateForm())
//                        processInputPipeline();
//                }
                break;

//            //TUJUAN PENGGUNAAN
//            case R.id.et_tujuanpenggunaan:
//                openKeyValueDialog(tf_tujuanpenggunaan.getLabelText().toString().trim());
//                break;
//            case R.id.tf_tujuanpenggunaan:
//                openKeyValueDialog(tf_tujuanpenggunaan.getLabelText().toString().trim());
//                break;

//            //SEGMEN
//            case R.id.et_segmen:
//                openProdukDialog(tf_segmen.getLabelText().toString().trim(), "", "");
//                break;
//            case R.id.tf_segmen:
//                openProdukDialog(tf_segmen.getLabelText().toString().trim(), "", "");
//                break;

            //PRODUK
            case R.id.et_produk:
                if (!et_segmen.getText().toString().trim().isEmpty()) {
                    Toast.makeText(KonsumerKMGPipelineInputActivity.this, "Hanya produk KMG yang dapat ditambahkan saat ini", Toast.LENGTH_LONG).show();
//                    openProdukDialog(tf_produk.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), "");
                } else {
                    AppUtil.notifwarning(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect) + " " + tf_segmen.getLabelText() + " terlebih dahulu");
                }
                break;
            case R.id.tf_produk:
                if (!et_segmen.getText().toString().trim().isEmpty()) {
                    Toast.makeText(KonsumerKMGPipelineInputActivity.this, "Hanya produk KMG yang dapat ditambahkan saat ini", Toast.LENGTH_LONG).show();
//                    openProdukDialog(tf_produk.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), "");
                } else {
                    AppUtil.notifwarning(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect) + " " + tf_segmen.getLabelText() + " terlebih dahulu");
                }
                break;

//            //PROGRAM
//            case R.id.et_program:
//                if (!et_segmen.getText().toString().trim().isEmpty() && !et_produk.getText().toString().trim().isEmpty()) {
//                    Toast.makeText(KonsumerKMGPipelineInputActivity.this, "Hanya program KMG EmBP yang dapat ditambahkan saat ini", Toast.LENGTH_LONG).show();
////                    openProdukDialog(tf_program.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), et_produk.getText().toString().trim());
//                } else {
//                    AppUtil.notifwarning(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect) + " " + tf_segmen.getLabelText() + " atau " + tf_produk.getLabelText() + " terlebih dahulu");
//                }
//                break;
//            case R.id.tf_program:
//                if (!et_segmen.getText().toString().trim().isEmpty() && !et_produk.getText().toString().trim().isEmpty()) {
//                    Toast.makeText(KonsumerKMGPipelineInputActivity.this, "Hanya program KMG EmBP yang dapat ditambahkan saat ini", Toast.LENGTH_LONG).show();
////                    openProdukDialog(tf_program.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), et_produk.getText().toString().trim());
//                } else {
//                    AppUtil.notifwarning(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect) + " " + tf_segmen.getLabelText() + " atau " + tf_produk.getLabelText() + " terlebih dahulu");
//                }
//                break;

            //TANGGAL LAHIR
            case R.id.et_tanggallahir:
                datePickerTanggalLahir();
                break;
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

//                //SEGMEN
//                case R.id.et_segmen:
//                    openProdukDialog(tf_segmen.getLabelText().toString().trim(), "", "");
//                    break;
//
//                //PRODUK
//                case R.id.et_produk:
//                    if (!et_segmen.getText().toString().trim().isEmpty()) {
//                        openProdukDialog(tf_produk.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), "");
//                    } else {
//                        AppUtil.notifwarning(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect) + " " + tf_segmen.getLabelText() + " terlebih dahulu");
//                    }
//                    break;

//                //PROGRAM
//                case R.id.et_program:
//                    if (!et_segmen.getText().toString().trim().isEmpty() && !et_produk.getText().toString().trim().isEmpty()) {
//                        openProdukDialog(tf_program.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), et_produk.getText().toString().trim());
//                    } else {
//                        AppUtil.notifwarning(KonsumerKMGPipelineInputActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect) + " " + tf_segmen.getLabelText() + " atau " + tf_produk.getLabelText() + " terlebih dahulu");
//                    }
//                    break;

                //TANGGAL LAHIR
                case R.id.et_tanggallahir:
                    datePickerTanggalLahir();
                    break;
            }
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
}

