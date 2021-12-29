package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqAgunanData;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inputAgunanKios;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanKios;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.ImageHandler;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.application.bris.ikurma_nos_konsumer.util.Permission;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.io.File;
import java.lang.reflect.Type;
import java.math.BigDecimal;
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

public class AgunanKiosInputActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener, KeyValueListener, CameraListener, TextWatcher, ConfirmListener{

    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.tf_tanggalpemeriksaan)
    TextFieldBoxes tf_tanggalpemeriksaan;
    @BindView(R.id.et_tanggalpemeriksaan)
    EditText et_tanggalpemeriksaan;
    @BindView(R.id.tf_jenisagunan)
    TextFieldBoxes tf_jenisagunan;
    @BindView(R.id.et_jenisagunan)
    EditText et_jenisagunan;
    @BindView(R.id.tf_dokumenbuktihakkios)
    TextFieldBoxes tf_dokumenbuktihakkios;
    @BindView(R.id.et_dokumenbuktihakkios)
    EditText et_dokumenbuktihakkios;
    @BindView(R.id.tf_nomordokumen)
    TextFieldBoxes tf_nomordokumen;
    @BindView(R.id.et_nomordokumen)
    EditText et_nomordokumen;
    @BindView(R.id.tf_namapemeganghak)
    TextFieldBoxes tf_namapemeganghak;
    @BindView(R.id.et_namapemeganghak)
    EditText et_namapemeganghak;
    @BindView(R.id.tf_hubunganpemeganghakdengannasabah)
    TextFieldBoxes tf_hubunganpemeganghakdengannasabah;
    @BindView(R.id.et_hubunganpemeganghakdengannasabah)
    EditText et_hubunganpemeganghakdengannasabah;
    @BindView(R.id.tf_masaberlakuijinkios)
    TextFieldBoxes tf_masaberlakuijinkios;
    @BindView(R.id.et_masaberlakuijinkios)
    EditText et_masaberlakuijinkios;
    @BindView(R.id.tf_luaskios)
    TextFieldBoxes tf_luaskios;
    @BindView(R.id.et_luaskios)
    EditText et_luaskios;
    @BindView(R.id.tf_namapasar)
    TextFieldBoxes tf_namapasar;
    @BindView(R.id.et_namapasar)
    EditText et_namapasar;
    @BindView(R.id.tf_blok)
    TextFieldBoxes tf_blok;
    @BindView(R.id.et_blok)
    EditText et_blok;
    @BindView(R.id.tf_nomorkios)
    TextFieldBoxes tf_nomorkios;
    @BindView(R.id.et_nomorkios)
    EditText et_nomorkios;
    @BindView(R.id.btn_set_loc)
    Button btn_set_loc;
    @BindView(R.id.tv_set_loc)
    TextView tv_set_loc;
    @BindView(R.id.tf_wilayahkota)
    TextFieldBoxes tf_wilayahkota;
    @BindView(R.id.et_wilayahkota)
    EditText et_wilayahkota;
    @BindView(R.id.tf_lokasijaminan)
    TextFieldBoxes tf_lokasijaminan;
    @BindView(R.id.et_lokasijaminan)
    EditText et_lokasijaminan;
    @BindView(R.id.tf_tahundibangunrenovasi)
    TextFieldBoxes tf_tahundibangunrenovasi;
    @BindView(R.id.et_tahundibangunrenovasi)
    EditText et_tahundibangunrenovasi;
    @BindView(R.id.tf_retribusi)
    TextFieldBoxes tf_retribusi;
    @BindView(R.id.et_retribusi)
    EditText et_retribusi;
    @BindView(R.id.tf_listrik)
    TextFieldBoxes tf_listrik;
    @BindView(R.id.et_listrik)
    EditText et_listrik;
    @BindView(R.id.tf_dayalistrik)
    TextFieldBoxes tf_dayalistrik;
    @BindView(R.id.et_dayalistrik)
    EditText et_dayalistrik;
    @BindView(R.id.tf_telepon)
    TextFieldBoxes tf_telepon;
    @BindView(R.id.et_telepon)
    EditText et_telepon;
    @BindView(R.id.tf_nomortelepon)
    TextFieldBoxes tf_nomortelepon;
    @BindView(R.id.et_nomortelepon)
    EditText et_nomortelepon;
    @BindView(R.id.tf_namapemberiinformasi)
    TextFieldBoxes tf_namapemberiinformasi;
    @BindView(R.id.et_namapemberiinformasi)
    EditText et_namapemberiinformasi;
    @BindView(R.id.tf_alamatpemberiinformasi)
    TextFieldBoxes tf_alamatpemberiinformasi;
    @BindView(R.id.et_alamatpemberiinformasi)
    EditText et_alamatpemberiinformasi;
    @BindView(R.id.tf_nohppemberiinformasi)
    TextFieldBoxes tf_nohppemberiinformasi;
    @BindView(R.id.et_nohppemberiinformasi)
    EditText et_nohppemberiinformasi;
    @BindView(R.id.tf_nilaimarket)
    TextFieldBoxes tf_nilaimarket;
    @BindView(R.id.et_nilaimarket)
    EditText et_nilaimarket;
    @BindView(R.id.tf_nilailikuidasi)
    TextFieldBoxes tf_nilailikuidasi;
    @BindView(R.id.et_nilailikuidasi)
    EditText et_nilailikuidasi;
    @BindView(R.id.iv_fotobpn)
    ImageView iv_fotobpn;
    @BindView(R.id.btn_fotobpn)
    ImageView btn_fotobpn;
    @BindView(R.id.iv_fotokios)
    ImageView iv_fotokios;
    @BindView(R.id.btn_fotokios)
    ImageView btn_fotokios;
    @BindView(R.id.iv_batasutara)
    ImageView iv_batasutara;
    @BindView(R.id.btn_batasutara)
    ImageView btn_batasutara;
    @BindView(R.id.iv_batasselatan)
    ImageView iv_batasselatan;
    @BindView(R.id.btn_batasselatan)
    ImageView btn_batasselatan;
    @BindView(R.id.iv_batastimur)
    ImageView iv_batastimur;
    @BindView(R.id.btn_batastimur)
    ImageView btn_batastimur;
    @BindView(R.id.iv_batasbarat)
    ImageView iv_batasbarat;
    @BindView(R.id.btn_batasbarat)
    ImageView btn_batasbarat;
    @BindView(R.id.et_pendapatkondisijaminan)
    EditText et_pendapatkondisijaminan;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;


    private Calendar calTanggalPemeriksaan;
    private Calendar calTanggalMasaBerlaku;
    private DatePickerDialog dpTanggalPemeriksaan;
    private DatePickerDialog dpTanggalMasaBerlaku;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    private String idAgunan;
    private String idApl;
    private String idCif;
    private String typeProduk;
    private String loanType;
    private AgunanKios dataAgunan;
    private List<AgunanKios> listDataAgunan;
    private String idAgunanResponse;
    private String val_tanggalPemeriksaan;
    private String val_jenisAgunan;
    private String val_dokumenBukti;
    private String val_noDokumen;
    private String val_namaPemegangHak;
    private String val_hubunganPemegangHak;
    private String val_masaBerlakuIjin;
    private int val_luasKios;
    private String val_namaPasar;
    private String val_losKios;
    private String val_noKios;
    private String val_wilayahKota;
    private String val_lokasiJaminan;
    private String val_tahunRenovasi;
    private String val_retribusi;
    private String val_listrik;
    private String val_besaranDaya;
    private String val_telepon;
    private String val_noTelp;
    private String val_nilaiMarket;
    private String val_nilaiTaksasi;
    private String val_ftv = "";
    private String val_namaPemberiInfo1;
    private String val_alamatPemberiInfo1;
    private String val_noTelpPemberiInfo1;
    private String val_pendapatPemeriksa;
    private String val_klasifikasiAgunan = "";
    private String val_jenisPengikatan = "";
    private String val_descPengikatan = "";
    private String val_fotoAgunanKios = "";
    private String val_nilaiPengikatan = "";
    private String val_collIdSyiar = "";
    private String val_Koordinat = "";
    private int val_idPhotoKbpn = 0;
    private int val_idPhotoKutama = 0;
    private int val_idPhotoKbarat = 0;
    private int val_idPhotoKutara = 0;
    private int val_idPhotoKselatan = 0;
    private int val_idPhotoKtimur = 0;
    private String isSelectPhoto = "";
    private final int TAKE_PICTURE_FOTOKIOS = 1;
    private final int TAKE_PICTURE_BATASUTARA = 2;
    private final int TAKE_PICTURE_BATASSELATAN = 3;
    private final int TAKE_PICTURE_BATASTIMUR = 4;
    private final int TAKE_PICTURE_BATASBARAT = 5;
    private final int TAKE_PICTURE_FOTOBPN = 6;
    private final int PICK_PICTURE_FOTOKIOS = 11;
    private final int PICK_PICTURE_BATASUTARA = 22;
    private final int PICK_PICTURE_BATASSELATAN = 33;
    private final int PICK_PICTURE_BATASTIMUR = 44;
    private final int PICK_PICTURE_BATASBARAT = 55;
    private final int PICK_PICTURE_FOTOBPN = 66;


    private Uri uri_fotobpn, uri_fotokios, uri_batasutara, uri_batasselatan, uri_batastimur,
            uri_batasbarat;

    private Bitmap bitmap_fotobpn, bitmap_fotokios, bitmap_batasutara, bitmap_batasselatan, bitmap_batastimur, bitmap_batasbarat, loadedPicture;

    private String latitude="";
    private String longitude="";
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private Permission permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_agunankios_input);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        permission = new Permission(this);
        AppUtil.toolbarRegular(this, "Agunan Kios");
        idAgunan = getIntent().getStringExtra("idAgunan");
        idApl = getIntent().getStringExtra("idAplikasi");
        idCif = getIntent().getStringExtra("cif");
        typeProduk = getIntent().getStringExtra("tp_produk");
        loanType = getIntent().getStringExtra("loan_type");
        backgroundStatusBar();
        if (!idAgunan.equalsIgnoreCase("0")){
            loadData();
        }
        else {
            sm_placeholder.setVisibility(View.GONE);
            ll_content.setVisibility(View.VISIBLE);
        }
        onclickSelectDialog();

    }

    private void loadData() {
        sm_placeholder.startShimmer();
        ReqAgunanData req = new ReqAgunanData(AppUtil.parseIntWithDefault(idApl, 0), AppUtil.parseIntWithDefault(idAgunan, 0), AppUtil.parseIntWithDefault(idCif, 0));
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().inquiryAgunanKios(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<AgunanKios>>() {}.getType();

                            String listDataString = response.body().getData().toString();
                            listDataAgunan = gson.fromJson(listDataString, type);
                            dataAgunan=listDataAgunan.get(0);
                            et_tanggalpemeriksaan.setText(AppUtil.parseTanggalGeneral(dataAgunan.gettANGGALPEMERIKSAAN(), "ddMMyyyy", "dd-MM-yyyy"));
                            et_jenisagunan.setText(dataAgunan.getjENISJAMINAN());
                            et_dokumenbuktihakkios.setText(dataAgunan.getdOKUMENBUKTI());
                            et_nomordokumen.setText(dataAgunan.getnODOKUMEN());
                            et_namapemeganghak.setText(dataAgunan.getnAMAPEMEGANGHAK());
                            et_hubunganpemeganghakdengannasabah.setText(dataAgunan.gethUBUNGANPEMEGANGHAK());
                            et_masaberlakuijinkios.setText(AppUtil.parseTanggalGeneral(dataAgunan.getmASABERLAKUIJIN(), "ddMMyyyy", "dd-MM-yyyy"));
                            et_luaskios.setText(String.valueOf(dataAgunan.getlUASKIOS()));
                            et_namapasar.setText(dataAgunan.getnAMAPASAR());
                            et_blok.setText(dataAgunan.getlOSKIOS());
                            et_nomorkios.setText(dataAgunan.getnOKIOS());
                            tv_set_loc.setText(dataAgunan.getKoordinat());
                            et_wilayahkota.setText(dataAgunan.getwILAYAHKOTA());
                            et_lokasijaminan.setText(dataAgunan.getlOKASIJAMINAN());
                            et_tahundibangunrenovasi.setText(dataAgunan.gettAHUNRENOVASI());
                            et_retribusi.setText(dataAgunan.getrETRIBUSI());
                            et_listrik.setText(dataAgunan.getlISTRIK());
                            et_dayalistrik.setText(String.valueOf(dataAgunan.getbESARANDAYA()));
                            et_telepon.setText(dataAgunan.gettELEPON());
                            et_nomortelepon.setText(dataAgunan.getnOTLP());
                            et_namapemberiinformasi.setText(dataAgunan.getnAMAPEMBERIINFO1());
                            et_alamatpemberiinformasi.setText(dataAgunan.getaLAMATPEMBERIINFO1());
                            et_nohppemberiinformasi.setText(dataAgunan.getnOTELPEMBERIINFO1());
                            et_nilaimarket.setText(dataAgunan.getnILAIMARKET());
                            et_nilailikuidasi.setText(dataAgunan.getnILAITAKSASI());
                            et_pendapatkondisijaminan.setText(dataAgunan.getpENDAPATPEMERIKSA());

                            bitmap_fotobpn = setLoadImage(iv_fotobpn, dataAgunan.getIdPhotoKbpn());
                            bitmap_fotokios = setLoadImage(iv_fotokios, dataAgunan.getIdPhotoKutama());
                            bitmap_batasutara = setLoadImage(iv_batasutara, dataAgunan.getIdPhotoKutara());
                            bitmap_batasselatan = setLoadImage(iv_batasselatan, dataAgunan.getIdPhotoKselatan());
                            bitmap_batastimur = setLoadImage(iv_batastimur, dataAgunan.idPhotoKtimur);
                            bitmap_batasbarat = setLoadImage(iv_batasbarat, dataAgunan.getIdPhotoKbarat());

                            val_idPhotoKbpn = dataAgunan.getIdPhotoKbpn();
                            val_idPhotoKutama = dataAgunan.getIdPhotoKutama();
                            val_idPhotoKutara = dataAgunan.getIdPhotoKutara();
                            val_idPhotoKselatan = dataAgunan.getIdPhotoKselatan();
                            val_idPhotoKtimur = dataAgunan.getIdPhotoKtimur();
                            val_idPhotoKbarat = dataAgunan.getIdPhotoKbarat();
                        }
                        else{
                            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
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
                        AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), error.getMessage());
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
                AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }

    private void onclickSelectDialog(){
        iv_fotobpn.setOnClickListener(this);
        iv_fotokios.setOnClickListener(this);
        iv_batasutara.setOnClickListener(this);
        iv_batasselatan.setOnClickListener(this);
        iv_batastimur.setOnClickListener(this);
        iv_batasbarat.setOnClickListener(this);
        btn_fotobpn.setOnClickListener(this);
        btn_fotokios.setOnClickListener(this);
        btn_batasutara.setOnClickListener(this);
        btn_batasselatan.setOnClickListener(this);
        btn_batastimur.setOnClickListener(this);
        btn_batasbarat.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        btn_set_loc.setOnClickListener(this);

        et_nilaimarket.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilaimarket));
        et_nilailikuidasi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilailikuidasi));
        et_nilaimarket.addTextChangedListener(this);
        et_retribusi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_retribusi));

        // TANGGAL PEMERIKSAAN
        et_tanggalpemeriksaan.setFocusable(false);
        et_tanggalpemeriksaan.setInputType(InputType.TYPE_NULL);
        et_tanggalpemeriksaan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                dpTanggalPemeriksaan();
            }
        });
        tf_tanggalpemeriksaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggalPemeriksaan();
            }
        });
        tf_tanggalpemeriksaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggalPemeriksaan();
            }
        });

        //JENIS AGUNAN
        et_jenisagunan.setFocusable(false);
        et_jenisagunan.setInputType(InputType.TYPE_NULL);
        et_jenisagunan.setOnFocusChangeListener(this);
        tf_jenisagunan.setOnClickListener(this);
        tf_jenisagunan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_jenisagunan.getLabelText().toString().trim());
            }
        });

        //DOKUMEN BUKTI HAK KIOS
        et_dokumenbuktihakkios.setFocusable(false);
        et_dokumenbuktihakkios.setInputType(InputType.TYPE_NULL);
        et_dokumenbuktihakkios.setOnFocusChangeListener(this);
        tf_dokumenbuktihakkios.setOnClickListener(this);
        tf_dokumenbuktihakkios.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_dokumenbuktihakkios.getLabelText().toString().trim());
            }
        });


        //HUBUNGAN PEMEGANG HAK
        et_hubunganpemeganghakdengannasabah.setFocusable(false);
        et_hubunganpemeganghakdengannasabah.setInputType(InputType.TYPE_NULL);
        et_hubunganpemeganghakdengannasabah.setOnFocusChangeListener(this);
        tf_hubunganpemeganghakdengannasabah.setOnClickListener(this);
        tf_hubunganpemeganghakdengannasabah.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_hubunganpemeganghakdengannasabah.getLabelText().toString().trim());
            }
        });

        // TANGGAL MASA BERLAKU
        et_masaberlakuijinkios.setFocusable(false);
        et_masaberlakuijinkios.setInputType(InputType.TYPE_NULL);
        et_masaberlakuijinkios.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                dpTanggalMasaBerlaku();
            }
        });
        tf_masaberlakuijinkios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggalMasaBerlaku();
            }
        });
        tf_masaberlakuijinkios.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggalMasaBerlaku();
            }
        });

        //LOKASI AGUNAN
        et_lokasijaminan.setFocusable(false);
        et_lokasijaminan.setInputType(InputType.TYPE_NULL);
        et_lokasijaminan.setOnFocusChangeListener(this);
        tf_lokasijaminan.setOnClickListener(this);
        tf_lokasijaminan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_lokasijaminan.getLabelText().toString().trim());
            }
        });


        //LISTRIK
        et_listrik.setFocusable(false);
        et_listrik.setInputType(InputType.TYPE_NULL);
        et_listrik.setOnFocusChangeListener(this);
        tf_listrik.setOnClickListener(this);
        tf_listrik.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_listrik.getLabelText().toString().trim());
            }
        });

        //TELPON
        et_telepon.setFocusable(false);
        et_telepon.setInputType(InputType.TYPE_NULL);
        et_telepon.setOnFocusChangeListener(this);
        tf_telepon.setOnClickListener(this);
        tf_telepon.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_telepon.getLabelText().toString().trim());
            }
        });
    }


    private void dpTanggalPemeriksaan(){
        calTanggalPemeriksaan = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ls_tanggalLahir = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calTanggalPemeriksaan.set(Calendar.YEAR, year);
                calTanggalPemeriksaan.set(Calendar.MONTH, month);
                calTanggalPemeriksaan.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calTanggalKunjunganString = dateClient.format(calTanggalPemeriksaan.getTime());
                et_tanggalpemeriksaan.setText(calTanggalKunjunganString);
            }
        };

        dpTanggalPemeriksaan = new DatePickerDialog(this, R.style.AppTheme_TimePickerTheme, ls_tanggalLahir, calTanggalPemeriksaan.get(Calendar.YEAR),
                calTanggalPemeriksaan.get(Calendar.MONTH), calTanggalPemeriksaan.get(Calendar.DAY_OF_MONTH));
        dpTanggalPemeriksaan.getDatePicker().setMaxDate(calTanggalPemeriksaan.getTimeInMillis());
        dpTanggalPemeriksaan.show();
    }


    private void dpTanggalMasaBerlaku(){
        calTanggalMasaBerlaku = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ls_tanggalLahir = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calTanggalMasaBerlaku.set(Calendar.YEAR, year);
                calTanggalMasaBerlaku.set(Calendar.MONTH, month);
                calTanggalMasaBerlaku.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calTanggalMasaBerlakuString = dateClient.format(calTanggalMasaBerlaku.getTime());
                et_masaberlakuijinkios.setText(calTanggalMasaBerlakuString);
            }
        };

        dpTanggalMasaBerlaku = new DatePickerDialog(this, R.style.AppTheme_TimePickerTheme, ls_tanggalLahir, calTanggalMasaBerlaku.get(Calendar.YEAR),
                calTanggalMasaBerlaku.get(Calendar.MONTH), calTanggalMasaBerlaku.get(Calendar.DAY_OF_MONTH));
        dpTanggalMasaBerlaku.getDatePicker().setMinDate(calTanggalMasaBerlaku.getTimeInMillis());
        dpTanggalMasaBerlaku.show();
    }


    private void openKeyValueDialog(String title){
        DialogKeyValue.display(getSupportFragmentManager(), title, this);
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }



    //TAKE PICTURE
    public void openGalery(int requestCode){
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(Intent.createChooser(it, "Select File"), requestCode);
    }

    private void openCamera(int cameraCode) {
        checkCameraPermission(cameraCode);
    }



    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static int CAMERA_CODE_FORE_PERMISSION = 0;
    public void checkCameraPermission(int cameraCode)
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            CAMERA_CODE_FORE_PERMISSION = cameraCode;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);
        } else {
            directOpenCamera(cameraCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_CAMERA_REQUEST_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                AppUtil.showToastLong(this, "Camera Permission Granted");
                directOpenCamera(CAMERA_CODE_FORE_PERMISSION);
            }
            else {
                AppUtil.showToastLong(this, "Camera Permission Denied");
            }
        }
    }

    private void directOpenCamera(int cameraCode){
        Uri outputFileUri = getCaptureImageOutputUri();
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(captureIntent, cameraCode);
    }

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                outputFileUri = FileProvider.getUriForFile(AgunanKiosInputActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "fotoagunankios.png"));
            }
            else{
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "fotoagunankios.png"));
            }
        }
        return outputFileUri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode){
                case TAKE_PICTURE_FOTOBPN:
                case PICK_PICTURE_FOTOBPN:
                    setDataImage(uri_fotobpn, bitmap_fotobpn, iv_fotobpn, data);
                    break;

                case TAKE_PICTURE_FOTOKIOS:
                case PICK_PICTURE_FOTOKIOS:
                    setDataImage(uri_fotokios, bitmap_fotokios, iv_fotokios, data);
                    break;

                case TAKE_PICTURE_BATASUTARA:
                case PICK_PICTURE_BATASUTARA:
                    setDataImage(uri_batasutara, bitmap_batasutara, iv_batasutara, data);
                    break;

                case TAKE_PICTURE_BATASSELATAN:
                case PICK_PICTURE_BATASSELATAN:
                    setDataImage(uri_batasselatan, bitmap_batasselatan, iv_batasselatan, data);
                    break;

                case TAKE_PICTURE_BATASTIMUR:
                case PICK_PICTURE_BATASTIMUR:
                    setDataImage(uri_batastimur, bitmap_batastimur, iv_batastimur, data);
                    break;

                case TAKE_PICTURE_BATASBARAT:
                case PICK_PICTURE_BATASBARAT:
                    setDataImage(uri_batasbarat, bitmap_batasbarat, iv_batasbarat, data);
                    break;

                case 69:
                    if (data != null) {
                        if (data.hasExtra("latitude")) {
                            latitude = data.getStringExtra("latitude");
                        }
                        if (data.hasExtra("longitude")) {
                            longitude = data.getStringExtra("longitude");
                        }

                        AppUtil.showToastShort(this, "latitude : "+latitude+"\nlongitude : "+longitude );

                        if (!(latitude.isEmpty() || latitude.equalsIgnoreCase("") || latitude.equalsIgnoreCase("0"))) {
                            tv_set_loc.setText(latitude + "," + longitude);
                        }
                    }
                    break;
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
        String fileName = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date())+"_"+System.currentTimeMillis()+ ".jpg";
        appPreferences.setFotoKelengkapanDokumen(fileName);
    }

    private void setDataImage(Uri uri, Bitmap bitmap, ImageView iv, Intent data){
        initImageFileName();
        if (getPickImageResultUri(data) != null) {
            uri = getPickImageResultUri(data);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                bitmap = AppUtil.rotateImageIfRequired(this, bitmap, uri);
                iv.setImageBitmap(bitmap);
                bitmap_fotobpn = bitmap;
                bitmap_fotokios = bitmap;
                bitmap_batasutara = bitmap;
                bitmap_batasselatan = bitmap;
                bitmap_batastimur = bitmap;
                bitmap_batasbarat = bitmap;

            } catch (Exception e) {
                e.printStackTrace();
            }
            String filename = appPreferences.getFotoKelengkapanDokumen();
            ImageHandler.saveImageToCache(this, bitmap, filename);
            uploadFoto(filename);
        }
    }

    public Bitmap setLoadImage(final ImageView iv, int idFoto){
        String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFile + idFoto;
        Glide
                .with(AgunanKiosInputActivity.this)
                .asBitmap()
                .load(url_photo)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv.setImageBitmap(resource);
                        loadedPicture = resource;
                    }
                });
        return loadedPicture;
    }

    public void uploadFoto(String filename){
        loading.setVisibility(View.VISIBLE);
        File imageFile = new File(getApplicationContext().getCacheDir(), filename);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().uploadFileOld(fileBody);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    loading.setVisibility(View.GONE);
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            int idFoto = response.body().getData().get("id").getAsInt();
                            switch (isSelectPhoto){
                                case "fotobpn":
                                    val_idPhotoKbpn = idFoto;
                                    break;
                                case "fotokios":
                                    val_idPhotoKutama = idFoto;
                                    break;
                                case "batasutara":
                                    val_idPhotoKutara = idFoto;
                                    break;
                                case "batasselatan":
                                    val_idPhotoKselatan = idFoto;
                                    break;
                                case "batastimur":
                                    val_idPhotoKtimur = idFoto;
                                    break;
                                case "batasbarat":
                                    val_idPhotoKbarat = idFoto;
                                    break;
                                default:
                                    break;
                            }
                        }
                        else{
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }


    public void sendData(){
        loading.setVisibility(View.VISIBLE);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendAgunanKios(getDataRequestValid());
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            CustomDialog.DialogSuccess(AgunanKiosInputActivity.this, "Success!", response.body().getMessage(), AgunanKiosInputActivity.this);
                            idAgunanResponse = response.body().getData().get("idAgunan").getAsString();
                        }
                        else{
                            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private boolean validateForm(){
        if(et_tanggalpemeriksaan.getText().toString().trim().isEmpty() || et_tanggalpemeriksaan.getText().toString().trim().equalsIgnoreCase("")){
            tf_tanggalpemeriksaan.setError(tf_tanggalpemeriksaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_tanggalpemeriksaan.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_jenisagunan.getText().toString().trim().isEmpty() || et_jenisagunan.getText().toString().trim().equalsIgnoreCase("")){
            tf_jenisagunan.setError(tf_jenisagunan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_jenisagunan.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_dokumenbuktihakkios.getText().toString().trim().isEmpty() || et_dokumenbuktihakkios.getText().toString().trim().equalsIgnoreCase("")){
            tf_dokumenbuktihakkios.setError(tf_dokumenbuktihakkios.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_dokumenbuktihakkios.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_nomordokumen.getText().toString().trim().isEmpty() || et_nomordokumen.getText().toString().trim().equalsIgnoreCase("")){
            tf_nomordokumen.setError(tf_nomordokumen.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_nomordokumen.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_namapemeganghak.getText().toString().trim().isEmpty() || et_namapemeganghak.getText().toString().trim().equalsIgnoreCase("")){
            tf_namapemeganghak.setError(tf_namapemeganghak.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_namapemeganghak.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_hubunganpemeganghakdengannasabah.getText().toString().trim().isEmpty() || et_hubunganpemeganghakdengannasabah.getText().toString().trim().equalsIgnoreCase("")){
            tf_hubunganpemeganghakdengannasabah.setError(tf_hubunganpemeganghakdengannasabah.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_hubunganpemeganghakdengannasabah.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_masaberlakuijinkios.getText().toString().trim().isEmpty() || et_masaberlakuijinkios.getText().toString().trim().equalsIgnoreCase("")){
            tf_masaberlakuijinkios.setError(tf_masaberlakuijinkios.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_masaberlakuijinkios.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_luaskios.getText().toString().trim().isEmpty() || et_luaskios.getText().toString().trim().equalsIgnoreCase("")){
            tf_luaskios.setError(tf_luaskios.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_luaskios.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_namapasar.getText().toString().trim().isEmpty() || et_namapasar.getText().toString().trim().equalsIgnoreCase("")){
            tf_namapasar.setError(tf_namapasar.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_namapasar.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_blok.getText().toString().trim().isEmpty() || et_blok.getText().toString().trim().equalsIgnoreCase("")){
            tf_blok.setError(tf_blok.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_blok.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_nomorkios.getText().toString().trim().isEmpty() || et_nomorkios.getText().toString().trim().equalsIgnoreCase("")){
            tf_nomorkios.setError(tf_nomorkios.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_nomorkios.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if (tv_set_loc.getText().toString().trim().equalsIgnoreCase("Lokasi Belum Diset") || tv_set_loc.getText().toString().trim().equalsIgnoreCase("")) {
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), "Lokasi koordinat harus diset");
            return false;
        }

        else if(et_wilayahkota.getText().toString().trim().isEmpty() || et_wilayahkota.getText().toString().trim().equalsIgnoreCase("")){
            tf_wilayahkota.setError(tf_wilayahkota.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_wilayahkota.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_lokasijaminan.getText().toString().trim().isEmpty() || et_lokasijaminan.getText().toString().trim().equalsIgnoreCase("")){
            tf_lokasijaminan.setError(tf_lokasijaminan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_lokasijaminan.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }
        else if(et_tahundibangunrenovasi.getText().toString().trim().isEmpty() || et_tahundibangunrenovasi.getText().toString().trim().equalsIgnoreCase("")){
            tf_tahundibangunrenovasi.setError(tf_tahundibangunrenovasi.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_tahundibangunrenovasi.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }
        else if(et_retribusi.getText().toString().trim().isEmpty() || et_retribusi.getText().toString().trim().equalsIgnoreCase("")){
            tf_retribusi.setError(tf_retribusi.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_retribusi.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }
        else if(et_listrik.getText().toString().trim().isEmpty() || et_listrik.getText().toString().trim().equalsIgnoreCase("")){
            tf_listrik.setError(tf_listrik.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_listrik.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }
        else if(et_telepon.getText().toString().trim().isEmpty() || et_telepon.getText().toString().trim().equalsIgnoreCase("")){
            tf_telepon.setError(tf_telepon.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_telepon.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_namapemberiinformasi.getText().toString().trim().isEmpty() || et_namapemberiinformasi.getText().toString().trim().equalsIgnoreCase("")){
            tf_namapemberiinformasi.setError(tf_namapemberiinformasi.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_namapemberiinformasi.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }
        else if(et_alamatpemberiinformasi.getText().toString().trim().isEmpty() || et_alamatpemberiinformasi.getText().toString().trim().equalsIgnoreCase("")){
            tf_alamatpemberiinformasi.setError(tf_alamatpemberiinformasi.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_alamatpemberiinformasi.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }
        else if(et_nohppemberiinformasi.getText().toString().trim().isEmpty() || et_nohppemberiinformasi.getText().toString().trim().equalsIgnoreCase("")){
            tf_nohppemberiinformasi.setError(tf_nohppemberiinformasi.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_nohppemberiinformasi.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }


        else if(et_nilaimarket.getText().toString().trim().isEmpty() || et_nilaimarket.getText().toString().trim().equalsIgnoreCase("")){
            tf_nilaimarket.setError(tf_nilaimarket.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_nilaimarket.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }
        else if(et_nilailikuidasi.getText().toString().trim().isEmpty() || et_nilailikuidasi.getText().toString().trim().equalsIgnoreCase("")){
            tf_nilailikuidasi.setError(tf_nilailikuidasi.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), tf_nilailikuidasi.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(val_idPhotoKbpn == 0){
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), "Silahkan tambah foto BPN");
            return false;
        }

        else if(val_idPhotoKutama == 0){
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), "Silahkan tambah foto kios");
            return false;
        }

        else if(val_idPhotoKutara == 0){
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), "Silahkan tambah foto batas utara");
            return false;
        }

        else if(val_idPhotoKselatan == 0){
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), "Silahkan tambah foto batas selatan");
            return false;
        }

        else if(val_idPhotoKtimur == 0){
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), "Silahkan tambah foto batas timur");
            return false;
        }

        else if(val_idPhotoKbarat == 0){
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), "Silahkan tambah foto batas barat");
            return false;
        }

        else if(et_pendapatkondisijaminan.getText().toString().trim().isEmpty() || et_pendapatkondisijaminan.getText().toString().trim().equalsIgnoreCase("")){
            AppUtil.notiferror(AgunanKiosInputActivity.this, findViewById(android.R.id.content), "Silahkan isi pendapat pemeriksa");
            return false;
        }
        return  true;
    }


    public inputAgunanKios getDataRequestValid(){

        val_tanggalPemeriksaan = (AppUtil.parseTanggalGeneral(et_tanggalpemeriksaan.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
        val_jenisAgunan = et_jenisagunan.getText().toString().trim();
        val_dokumenBukti = et_dokumenbuktihakkios.getText().toString().trim();
        val_noDokumen = et_nomordokumen.getText().toString().trim();
        val_namaPemegangHak = et_namapemeganghak.getText().toString().trim();
        val_hubunganPemegangHak = et_hubunganpemeganghakdengannasabah.getText().toString().trim();
        val_masaBerlakuIjin = (AppUtil.parseTanggalGeneral(et_masaberlakuijinkios.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
        val_luasKios = AppUtil.parseIntWithDefault(et_luaskios.getText().toString().trim(), 0);
        val_namaPasar = et_namapasar.getText().toString().trim();
        val_losKios = et_blok.getText().toString().trim();
        val_dokumenBukti = et_dokumenbuktihakkios.getText().toString().trim();
        val_noKios = et_nomorkios.getText().toString().trim();
        val_Koordinat = tv_set_loc.getText().toString().trim();
        val_wilayahKota = et_wilayahkota.getText().toString().trim();
        val_lokasiJaminan = et_lokasijaminan.getText().toString().trim();
        val_tahunRenovasi = et_tahundibangunrenovasi.getText().toString().trim();
        val_retribusi = NumberTextWatcherCanNolForThousand.trimCommaOfString(et_retribusi.getText().toString().trim());
        val_listrik = et_listrik.getText().toString().trim();
        val_besaranDaya = et_dayalistrik.getText().toString().trim();
        val_telepon = et_telepon.getText().toString().trim();
        val_noTelp = et_nomortelepon.getText().toString().trim();
        val_nilaiMarket = NumberTextWatcherCanNolForThousand.trimCommaOfString(et_nilaimarket.getText().toString().trim());
        val_nilaiTaksasi = NumberTextWatcherCanNolForThousand.trimCommaOfString(et_nilailikuidasi.getText().toString().trim());
        val_namaPemberiInfo1 = et_namapemberiinformasi.getText().toString().trim();
        val_alamatPemberiInfo1 = et_alamatpemberiinformasi.getText().toString().trim();
        val_noTelpPemberiInfo1 = et_nohppemberiinformasi.getText().toString().trim();
        val_pendapatPemeriksa = et_pendapatkondisijaminan.getText().toString().trim();

        inputAgunanKios req = new inputAgunanKios(AppUtil.parseIntWithDefault(idAgunan, 0), AppUtil.parseIntWithDefault(idApl, 0), AppUtil.parseIntWithDefault(idCif, 0), AppUtil.parseIntWithDefault(typeProduk, 0), val_tanggalPemeriksaan, val_jenisAgunan, val_dokumenBukti, val_noDokumen,
                val_namaPemegangHak, val_hubunganPemegangHak, val_masaBerlakuIjin, val_luasKios, val_namaPasar, val_losKios, val_noKios, val_Koordinat, val_wilayahKota, val_lokasiJaminan, val_tahunRenovasi, val_retribusi, val_listrik, val_besaranDaya, val_telepon, val_noTelp, val_nilaiMarket, val_nilaiTaksasi, val_ftv, val_namaPemberiInfo1,
                val_alamatPemberiInfo1, val_noTelpPemberiInfo1, val_pendapatPemeriksa, val_klasifikasiAgunan, val_jenisPengikatan, val_descPengikatan, val_fotoAgunanKios, val_nilaiPengikatan, val_collIdSyiar, val_idPhotoKutama, val_idPhotoKbarat, val_idPhotoKutara, val_idPhotoKselatan, val_idPhotoKtimur, val_idPhotoKbpn);

        return req;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //TANGGAL PEMERIKSAAN
            case R.id.tf_tanggalpemeriksaan:
            case R.id.et_tanggalpemeriksaan:
                dpTanggalPemeriksaan();
                break;

            //JENIS AGUNAN
            case R.id.tf_jenisagunan:
            case R.id.et_jenisagunan:
                openKeyValueDialog(tf_jenisagunan.getLabelText().toString().trim());
                break;

            //DOKUMEN BUKTI HAK KIOS
            case R.id.tf_dokumenbuktihakkios:
            case R.id.et_dokumenbuktihakkios:
                openKeyValueDialog(tf_dokumenbuktihakkios.getLabelText().toString().trim());
                break;

            //HUBUNGAN PEMEGANG HAK
            case R.id.tf_hubunganpemeganghakdengannasabah:
            case R.id.et_hubunganpemeganghakdengannasabah:
                openKeyValueDialog(tf_hubunganpemeganghakdengannasabah.getLabelText().toString().trim());
                break;

            //TANGGAL MASA BERLAKU
            case R.id.tf_masaberlakuijinkios:
            case R.id.et_masaberlakuijinkios:
                dpTanggalMasaBerlaku();
                break;

            //LOKASI AGUNAN
            case R.id.tf_lokasijaminan:
            case R.id.et_lokasijaminan:
                openKeyValueDialog(tf_lokasijaminan.getLabelText().toString().trim());
                break;

            //LISTRIK
            case R.id.tf_listrik:
            case R.id.et_listrik:
                openKeyValueDialog(tf_listrik.getLabelText().toString().trim());
                break;

            //TELPON
            case R.id.tf_telepon:
            case R.id.et_telepon:
                openKeyValueDialog(tf_telepon.getLabelText().toString().trim());
                break;

            //TAKE PICTURE
            case R.id.btn_fotobpn:
                isSelectPhoto = "fotobpn";
                BSBottomCamera.displayWithTitle(getSupportFragmentManager(), this, "Foto BPN");
                break;

            //TAKE PICTURE
            case R.id.btn_fotokios:
                isSelectPhoto = "fotokios";
                BSBottomCamera.displayWithTitle(getSupportFragmentManager(), this, "Foto Kios");
                break;

            //TAKE PICTURE
            case R.id.btn_batasutara:
                isSelectPhoto = "batasutara";
                BSBottomCamera.displayWithTitle(getSupportFragmentManager(), this, "Batas Utara");
                break;

            //TAKE PICTURE
            case R.id.btn_batasselatan:
                isSelectPhoto = "batasselatan";
                BSBottomCamera.displayWithTitle(getSupportFragmentManager(), this, "Batas Selatan");
                break;

            //TAKE PICTURE
            case R.id.btn_batastimur:
                isSelectPhoto = "batastimur";
                BSBottomCamera.displayWithTitle(getSupportFragmentManager(), this, "Batas Timur");
                break;

            //TAKE PICTURE
            case R.id.btn_batasbarat:
                isSelectPhoto = "batasbarat";
                BSBottomCamera.displayWithTitle(getSupportFragmentManager(), this, "Batas Barat");
                break;

            case R.id.iv_fotobpn:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_fotobpn.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_fotokios:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_fotokios.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_batasutara:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_batasutara.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_batasselatan:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_batasselatan.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_batastimur:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_batastimur.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_batasbarat :
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_batasbarat.getDrawable()).getSourceBitmap());
                break;

            case R.id.btn_set_loc:
                if(permission.Location())
                {
                    Intent intent = new Intent(this, MapsActivity.class);
                    startActivityForResult(intent, 69);
                }
                break;

            //SEND
            case R.id.btn_send:
                if (validateForm())
                    sendData();
                break;
        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            //TANGGAL PEMERIKSAAN
            case R.id.et_tanggalpemeriksaan:
                dpTanggalPemeriksaan();
                break;

            //JENIS AGUNAN
            case R.id.et_jenisagunan:
                openKeyValueDialog(tf_jenisagunan.getLabelText().toString().trim());
                break;

            //DOKUMEN BUKTI HAK KIOS
            case R.id.et_dokumenbuktihakkios:
                openKeyValueDialog(tf_dokumenbuktihakkios.getLabelText().toString().trim());
                break;

            //HUBUNGAN PEMEGANG HAK
            case R.id.et_hubunganpemeganghakdengannasabah:
                openKeyValueDialog(tf_hubunganpemeganghakdengannasabah.getLabelText().toString().trim());
                break;

            //TANGGAL MASA BERLAKU
            case R.id.et_masaberlakuijinkios:
                dpTanggalMasaBerlaku();
                break;

            //LOKASI AGUNAN
            case R.id.et_lokasijaminan:
                openKeyValueDialog(tf_lokasijaminan.getLabelText().toString().trim());
                break;
        }
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase(tf_jenisagunan.getLabelText().toString().trim())){
            et_jenisagunan.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(tf_dokumenbuktihakkios.getLabelText().toString().trim())){
            et_dokumenbuktihakkios.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(tf_hubunganpemeganghakdengannasabah.getLabelText().toString().trim())){
            et_hubunganpemeganghakdengannasabah.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(tf_lokasijaminan.getLabelText().toString().trim())){
            et_lokasijaminan.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(tf_listrik.getLabelText().toString().trim())){
            et_listrik.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(tf_telepon.getLabelText().toString().trim())){
            et_telepon.setText(data.getName());
        }
    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        if (isSelectPhoto.equalsIgnoreCase("fotobpn")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_FOTOBPN);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_FOTOBPN);
                    break;
            }
        }
        else if (isSelectPhoto.equalsIgnoreCase("fotokios")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_FOTOKIOS);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_FOTOKIOS);
                    break;
            }
        }
        else if (isSelectPhoto.equalsIgnoreCase("batasutara")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_BATASUTARA);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_BATASUTARA);
                    break;
            }
        }
        else if (isSelectPhoto.equalsIgnoreCase("batasselatan")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_BATASSELATAN);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_BATASSELATAN);
                    break;
            }
        }
        else if (isSelectPhoto.equalsIgnoreCase("batastimur")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_BATASTIMUR);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_BATASTIMUR);
                    break;
            }
        }
        else if (isSelectPhoto.equalsIgnoreCase("batasbarat")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_BATASBARAT);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_BATASBARAT);
                    break;
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        try {
            BigDecimal bd_nilailikuidasi = new BigDecimal(0);
            if (et_nilaimarket.getText().toString().trim().length() > 0 || !et_nilaimarket.getText().toString().isEmpty()){
                bd_nilailikuidasi = new BigDecimal(70).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_nilaimarket.getText().toString().trim()))).setScale(0, BigDecimal.ROUND_HALF_UP);
            }
            et_nilailikuidasi.setText(bd_nilailikuidasi.toString());
        }
        catch (Exception e)
        {
            AppUtil.showToastShort(AgunanKiosInputActivity.this, e.getMessage());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void success(boolean val) {
        if (val){
            if (idAgunan.equalsIgnoreCase("0")){
                finish();
                Intent intent = new Intent(AgunanKiosInputActivity.this, InfoAgunanActivity.class);
                intent.putExtra("idAplikasi", idApl);
                intent.putExtra("idCif", idCif);
                intent.putExtra("idAgunan", idAgunanResponse);
                intent.putExtra("loan_type", loanType);
                intent.putExtra("tp_produk", typeProduk);
                intent.putExtra("jenisAgunan", "kios");
                startActivity(intent);
            }
            else {
                finish();
            }
        }
    }

    @Override
    public void confirm(boolean val) {

    }
}
