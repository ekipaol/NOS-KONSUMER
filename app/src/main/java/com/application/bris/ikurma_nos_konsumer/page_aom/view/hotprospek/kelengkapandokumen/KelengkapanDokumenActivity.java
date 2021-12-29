package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.kelengkapandokumen;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import com.application.bris.ikurma_nos_konsumer.page_aom.adapter.hotprospek.AgunanKelengkapanDokumenAdapter;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.ListAgunanKelengkapanDokumen;
import com.application.bris.ikurma_nos_konsumer.util.Constants;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inputKelengkapanDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryKelengkapanDokumen;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.KelengkapanDokumen;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.ImageHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;

import java.io.File;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KelengkapanDokumenActivity extends AppCompatActivity implements ConfirmListener, View.OnClickListener{

    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.cb_ktpnasabahpasangan)
    CheckBox cb_ktpnasabahpasangan;
    @BindView(R.id.cb_kartukeluarga)
    CheckBox cb_kartukeluarga;
    @BindView(R.id.cb_suratnikah)
    CheckBox cb_suratnikah;
    @BindView(R.id.cb_pasphoto)
    CheckBox cb_pasphoto;
    @BindView(R.id.cb_npwp)
    CheckBox cb_npwp;
    @BindView(R.id.cb_formuliraplikasi)
    CheckBox cb_formuliraplikasi;
    @BindView(R.id.cb_suratijinusaha)
    CheckBox cb_suratijinusaha;
    @BindView(R.id.cb_catatankeuangan)
    CheckBox cb_catatankeuangan;
    @BindView(R.id.cb_daftarrencanapembiayaan)
    CheckBox cb_daftarrencanapembiayaan;
    @BindView(R.id.ll_iskur)
    LinearLayout ll_iskur;
    @BindView(R.id.cb_suratpernyataankur)
    CheckBox cb_suratpernyataankur;
    @BindView(R.id.cb_suratketeranganlunas)
    CheckBox cb_suratketeranganlunas;


    @BindView(R.id.iv_ktpnasabahpasangan)
    ImageView iv_ktpnasabahpasangan;
    @BindView(R.id.iv_kartukeluarga)
    ImageView iv_kartukeluarga;
    @BindView(R.id.iv_suratnikah)
    ImageView iv_suratnikah;
    @BindView(R.id.iv_pasphoto)
    ImageView iv_pasphoto;
    @BindView(R.id.iv_npwp)
    ImageView iv_npwp;
    @BindView(R.id.iv_formuliraplikasi)
    ImageView iv_formuliraplikasi;
    @BindView(R.id.iv_suratijinusaha)
    ImageView iv_suratijinusaha;
    @BindView(R.id.iv_catatankeuangan)
    ImageView iv_catatankeuangan;
    @BindView(R.id.iv_daftarrencanapembiayaan)
    ImageView iv_daftarrencanapembiayaan;
    @BindView(R.id.iv_suratpernyataankur)
    ImageView iv_suratpernyataankur;
    @BindView(R.id.iv_suratketeranganlunas)
    ImageView iv_suratketeranganlunas;
    @BindView(R.id.btn_ktpnasabahpasangan)
    ImageView btn_ktpnasabahpasangan;
    @BindView(R.id.btn_kartukeluarga)
    ImageView btn_kartukeluarga;
    @BindView(R.id.btn_suratnikah)
    ImageView btn_suratnikah;
    @BindView(R.id.btn_pasphoto)
    ImageView btn_pasphoto;
    @BindView(R.id.btn_npwp)
    ImageView btn_npwp;
    @BindView(R.id.btn_formuliraplikasi)
    ImageView btn_formuliraplikasi;
    @BindView(R.id.btn_suratijinusaha)
    ImageView btn_suratijinusaha;
    @BindView(R.id.btn_catatankeuangan)
    ImageView btn_catatankeuangan;
    @BindView(R.id.btn_daftarrencanapembiayaan)
    ImageView btn_daftarrencanapembiayaan;
    @BindView(R.id.btn_suratpernyataankur)
    ImageView btn_suratpernyataankur;
    @BindView(R.id.btn_suratketeranganlunas)
    ImageView btn_suratketeranganlunas;
    @BindView(R.id.et_nomorsiup)
    EditText et_nomorsiup;

    @BindView(R.id.rv_listagunan)
    RecyclerView rv_listagunan;
    @BindView(R.id.ll_emptydata)
    LinearLayout ll_emptydata;

    private int idAplikasi;
    private String kodeProduct;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String dataString;
    private KelengkapanDokumen data;

    private boolean val_cb_ktpnasabahpasangan = false;
    private boolean val_cb_kartukeluarga = false;
    private boolean val_cb_suratnikah = false;
    private boolean val_cb_pasphoto = false;
    private boolean val_cb_npwp = false;
    private boolean val_cb_formuliraplikasi = false;
    private boolean val_cb_suratijinusaha = false;
    private boolean val_cb_catatankeuangan = false;
    private boolean val_cb_daftarrencanapembiayaan = false;
    private boolean val_cb_suratpernyataankur = false;
    private boolean val_cb_suratketeranganlunas = false;
    private int val_ktpnasabahpasangan = 0;
    private int val_kartukeluarga = 0;
    private int val_suratnikah = 0;
    private int val_pasphoto = 0;
    private int val_npwp = 0;
    private int val_formuliraplikasi = 0;
    private int val_suratijinusaha = 0;
    private int val_catatankeuangan = 0;
    private int val_daftarrencanapembiayaan = 0;
    private int val_suratpernyataankur = 0;
    private int val_suratketeranganlunas = 0;
    private String val_nosiup;

    private final int TAKE_PICTURE_KTPNASABAHPASANGAN = 1;
    private final int TAKE_PICTURE_KARTUKELUARGA = 2;
    private final int TAKE_PICTURE_SURATNIKAH = 3;
    private final int TAKE_PICTURE_PASPHOTO = 4;
    private final int TAKE_PICTURE_NPWP = 5;
    private final int TAKE_PICTURE_FORMULIRAPLIKASI = 6;
    private final int TAKE_PICTURE_SURATIJINUSAHA = 7;
    private final int TAKE_PICTURE_CATATANKEUANGAN = 8;
    private final int TAKE_PICTURE_DAFTARRENCANAPEMBIAYAAN = 9;
    private final int TAKE_PICTURE_SURATPERNYATAANKUR = 10;
    private final int TAKE_PICTURE_SURATKETERANGANLUNAS = 11;

    private String isSelectPhoto = "";

    private Bitmap loadedPicture;

    private Uri uri_ktpnasabahpasangan, uri_kartukeluarga, uri_suratnikah, uri_pasphoto,
            uri_npwp, uri_formuliraplikasi, uri_suratijinusaha, uri_catatankeuangan,
            uri_daftarrencanapembiayaan, uri_suratpernyataankur, uri_suratketeranganlunas;

    private Bitmap bitmap_ktpnasabahpasangan, bitmap_kartukeluarga, bitmap_suratnikah, bitmap_pasphoto,
            bitmap_npwp, bitmap_formuliraplikasi, bitmap_suratijinusaha, bitmap_catatankeuangan,
            bitmap_daftarrencanapembiayaan, bitmap_suratpernyataankur, bitmap_suratketeranganlunas;

    private int plafond;

    private List<ListAgunanKelengkapanDokumen> dataAgunan;
    private AgunanKelengkapanDokumenAdapter adapterAgunan;
    private LinearLayoutManager layoutAgunan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_hotprospek_kelengkapandokumen);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        kodeProduct = getIntent().getStringExtra("kodeProduct");
        plafond = getIntent().getIntExtra("plafond", 0);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, getString(R.string.submenu_hotprospek_kelengkapandokumen));
        loadKelengkapanDokumen();
        setUncheckboxDefault();
        onClickCheckbox();
        if (kodeProduct.equalsIgnoreCase("127") || kodeProduct.equalsIgnoreCase("128")){
            ll_iskur.setVisibility(View.VISIBLE);
        }
        setOnClick();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm_placeholder.startShimmer();
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

    private void setOnClick(){
        iv_ktpnasabahpasangan.setOnClickListener(this);
        iv_kartukeluarga.setOnClickListener(this);
        iv_suratnikah.setOnClickListener(this);
        iv_pasphoto.setOnClickListener(this);
        iv_npwp.setOnClickListener(this);
        iv_formuliraplikasi.setOnClickListener(this);
        iv_suratijinusaha.setOnClickListener(this);
        iv_catatankeuangan.setOnClickListener(this);
        iv_daftarrencanapembiayaan.setOnClickListener(this);
        iv_suratpernyataankur.setOnClickListener(this);
        iv_suratketeranganlunas.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        btn_ktpnasabahpasangan.setOnClickListener(this);
        btn_kartukeluarga.setOnClickListener(this);
        btn_suratnikah.setOnClickListener(this);
        btn_pasphoto.setOnClickListener(this);
        btn_npwp.setOnClickListener(this);
        btn_formuliraplikasi.setOnClickListener(this);
        btn_suratijinusaha.setOnClickListener(this);
        btn_catatankeuangan.setOnClickListener(this);
        btn_daftarrencanapembiayaan.setOnClickListener(this);
        btn_suratpernyataankur.setOnClickListener(this);
        btn_suratketeranganlunas.setOnClickListener(this);
    }

    private void onClickCheckbox(){

        cb_ktpnasabahpasangan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_ktpnasabahpasangan = true;
                }
                else {
                    val_cb_ktpnasabahpasangan = false;
                }
            }
        });

        cb_kartukeluarga.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_kartukeluarga = true;
                }
                else {
                    val_cb_kartukeluarga = false;
                }
            }
        });

        cb_suratnikah.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_suratnikah = true;
                }
                else {
                    val_cb_suratnikah = false;
                }
            }
        });

        cb_pasphoto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_pasphoto = true;
                }
                else {
                    val_cb_pasphoto = false;
                }
            }
        });

        cb_npwp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_npwp = true;
                }
                else {
                    val_cb_npwp = false;
                }
            }
        });

        cb_formuliraplikasi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_formuliraplikasi = true;
                }
                else {
                    val_cb_formuliraplikasi = false;
                }
            }
        });

        cb_suratijinusaha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_suratijinusaha = true;
                }
                else {
                    val_cb_suratijinusaha = false;
                }
            }
        });

        cb_catatankeuangan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_catatankeuangan = true;
                }
                else {
                    val_cb_catatankeuangan = false;
                }
            }
        });
        cb_daftarrencanapembiayaan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_daftarrencanapembiayaan = true;
                }
                else {
                    val_cb_daftarrencanapembiayaan = false;
                }
            }
        });
        cb_suratpernyataankur.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_suratpernyataankur = true;
                }
                else {
                    val_cb_suratpernyataankur = false;
                }
            }
        });
        cb_suratketeranganlunas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_suratketeranganlunas = true;
                }
                else {
                    val_cb_suratketeranganlunas = false;
                }
            }
        });
    }

    private void setUncheckboxDefault(){
        cb_ktpnasabahpasangan.setChecked(false);
        cb_kartukeluarga.setChecked(false);
        cb_suratnikah.setChecked(false);
        cb_pasphoto.setChecked(false);
        cb_npwp.setChecked(false);
        cb_formuliraplikasi.setChecked(false);
        cb_suratijinusaha.setChecked(false);
        cb_catatankeuangan.setChecked(false);
        cb_daftarrencanapembiayaan.setChecked(false);
        cb_suratpernyataankur.setChecked(false);
        cb_suratketeranganlunas.setChecked(false);
    }


    private void loadKelengkapanDokumen() {
        inquiryKelengkapanDokumen req = new inquiryKelengkapanDokumen(idAplikasi);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryKelengkapanDokumen(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            Type typeAgunan = new TypeToken<List<ListAgunanKelengkapanDokumen>>() {
                            }.getType();

                            dataString = response.body().getData().get("kelDokumen").toString();

                            if (!dataString.equalsIgnoreCase("{}")){
                                data = gson.fromJson(dataString, KelengkapanDokumen.class);
                                dataAgunan = gson.fromJson(response.body().getData().get("kelDokumen").getAsJsonObject().get("ID_DOKUMEN_AGUNAN").toString(), typeAgunan);

                                setCheckbox();
                                setValData();

                                if (dataAgunan.size() > 0) {
                                    ll_emptydata.setVisibility(View.GONE);
                                    initializeAgunan();
                                } else {
                                    ll_emptydata.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }


    private void setCheckbox(){

        if(data != null){
            if (data.getfCKTP())
                cb_ktpnasabahpasangan.setChecked(true);

            if(data.getfCKK())
                cb_kartukeluarga.setChecked(true);

            if (data.getfCSURATNIKAH())
                cb_suratnikah.setChecked(true);

            if (data.getpASPHOTO())
                cb_pasphoto.setChecked(true);

            if (data.getfCNPWPPRIBADI())
                cb_npwp.setChecked(true);

            if (data.getaPLIKASIPERMOHONAN())
                cb_formuliraplikasi.setChecked(true);

            if (data.getfCSIUP())
                cb_suratijinusaha.setChecked(true);

            if (data.getlAPORANKEUANGAN())
                cb_catatankeuangan.setChecked(true);

            if (data.getsURATPERNYATAANNASABAH())
                cb_daftarrencanapembiayaan.setChecked(true);

            if (data.getsURATPERNYATAANKUR())
                cb_suratpernyataankur.setChecked(true);

            if (data.getsURATKETERANGANLUNASKUR())
                cb_suratketeranganlunas.setChecked(true);

            et_nomorsiup.setText(data.getnOSKU());

            bitmap_ktpnasabahpasangan = setLoadImage(iv_ktpnasabahpasangan, data.getiDDOKUMENKTP());
            bitmap_kartukeluarga = setLoadImage(iv_kartukeluarga, data.getiDDOKUMENKK());
            bitmap_suratnikah = setLoadImage(iv_suratnikah, data.getiDDOKUMENSURATNIKAH());
            bitmap_pasphoto = setLoadImage(iv_pasphoto, data.getiDDOKUMENPASPHOTO());
            bitmap_npwp = setLoadImage(iv_npwp, data.getiDDOKUMENNPWPPRIBADI());
            bitmap_formuliraplikasi = setLoadImage(iv_formuliraplikasi, data.getiDDOKUMENAPLIKASI());
            bitmap_suratijinusaha = setLoadImage(iv_suratijinusaha, data.getiDDOKUMENSIUP());
            bitmap_catatankeuangan = setLoadImage(iv_catatankeuangan, data.getiDDOKUMENLAPORANKEUANGAN());
            bitmap_daftarrencanapembiayaan = setLoadImage(iv_daftarrencanapembiayaan, data.getiDDOKUMENSURATPERNYATAANNASABAH());
            bitmap_suratpernyataankur = setLoadImage(iv_suratpernyataankur, data.getiDDOKUMENSURATPERNYATAANKUR());
            bitmap_suratketeranganlunas = setLoadImage(iv_suratketeranganlunas, data.getiDDOKUMENSURATKETERANGANLUNASKUR());
        }
    }

    private void setValData(){
        if (data != null){
            val_cb_ktpnasabahpasangan = data.getfCKTP();
            val_cb_kartukeluarga = data.getfCKK();
            val_cb_suratnikah = data.getfCSURATNIKAH();
            val_cb_pasphoto = data.getpASPHOTO();
            val_cb_npwp = data.getfCNPWPPRIBADI();
            val_cb_formuliraplikasi = data.getaPLIKASIPERMOHONAN();
            val_cb_suratijinusaha = data.getfCSIUP();
            val_cb_catatankeuangan = data.getlAPORANKEUANGAN();
            val_cb_daftarrencanapembiayaan = data.getsURATPERNYATAANNASABAH();
            val_cb_suratpernyataankur = data.getsURATPERNYATAANKUR();
            val_cb_suratketeranganlunas = data.getsURATKETERANGANLUNASKUR();

            val_ktpnasabahpasangan = data.getiDDOKUMENKTP();
            val_kartukeluarga = data.getiDDOKUMENKK();
            val_suratnikah = data.getiDDOKUMENSURATNIKAH();
            val_pasphoto = data.getiDDOKUMENPASPHOTO();
            val_npwp = data.getiDDOKUMENNPWPPRIBADI();
            val_formuliraplikasi = data.getiDDOKUMENAPLIKASI();
            val_suratijinusaha = data.getiDDOKUMENSIUP();
            val_catatankeuangan = data.getiDDOKUMENLAPORANKEUANGAN();
            val_daftarrencanapembiayaan = data.getiDDOKUMENSURATPERNYATAANNASABAH();
            val_suratpernyataankur = data.getiDDOKUMENSURATPERNYATAANKUR();
            val_suratketeranganlunas = data.getiDDOKUMENSURATKETERANGANLUNASKUR();

            val_nosiup = data.getnOSKU();
        }
    }


    public void initializeAgunan(){
        adapterAgunan = new AgunanKelengkapanDokumenAdapter(KelengkapanDokumenActivity.this, dataAgunan);
        layoutAgunan = new LinearLayoutManager(KelengkapanDokumenActivity.this);
        rv_listagunan.setLayoutManager(layoutAgunan);
        rv_listagunan.setAdapter(adapterAgunan);

        if (dataAgunan == null){
            ll_emptydata.setVisibility(View.VISIBLE);
        }
    }


    private boolean validatePhoto(){
        if ((val_cb_ktpnasabahpasangan && val_ktpnasabahpasangan == 0) || (!val_cb_ktpnasabahpasangan && val_ktpnasabahpasangan != 0)  ){
            AppUtil.notifwarning(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Checklist / ambil Foto KTP");
            return false;
        }
        if ((val_cb_kartukeluarga && val_kartukeluarga == 0) || (!val_cb_kartukeluarga && val_kartukeluarga != 0)){
            AppUtil.notifwarning(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Checklist / ambil Foto KK");
            return false;
        }
        if ((val_cb_suratnikah && val_suratnikah == 0) || (!val_cb_suratnikah && val_suratnikah != 0)){
            AppUtil.notifwarning(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Checklist / ambil Foto Surat Nikah");
            return false;
        }
        if ((val_cb_pasphoto && val_pasphoto == 0) || (!val_cb_pasphoto && val_pasphoto != 0)){
            AppUtil.notifwarning(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Checklist / ambil Foto Pas Foto");
            return false;
        }
        if ((val_cb_npwp && val_npwp == 0) || (!val_cb_npwp && val_npwp != 0) || (!val_cb_npwp && val_npwp == 0)){
            if (plafond >= Constants.VALIDATE_PLAFOND_NPWP){
                AppUtil.notifwarning(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Checklist / ambil Foto NPWP");
                return false;
            }
            else{
                return true;
            }
        }
        if ((val_cb_formuliraplikasi && val_formuliraplikasi == 0) || (!val_cb_formuliraplikasi && val_formuliraplikasi != 0)){
            AppUtil.notifwarning(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Checklist / ambil Foto Formulir Aplikasi");
            return false;
        }
        if ((val_cb_suratijinusaha && val_suratijinusaha == 0) || (!val_cb_suratijinusaha && val_suratijinusaha != 0)){
            AppUtil.notifwarning(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Checklist / ambil Foto SIUP");
            return false;
        }
        if (val_cb_suratijinusaha && et_nomorsiup.getText().toString().isEmpty()){
            AppUtil.notifwarning(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Nomor Siup tidak boleh kosong");
            return false;
        }
        if ((val_cb_catatankeuangan && val_catatankeuangan == 0) || (!val_cb_catatankeuangan && val_catatankeuangan != 0)){
            AppUtil.notifwarning(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Checklist / ambil Foto Laporan Keuangan");
            return false;
        }
        if ((val_cb_daftarrencanapembiayaan && val_daftarrencanapembiayaan == 0) || (!val_cb_daftarrencanapembiayaan && val_daftarrencanapembiayaan != 0)){
            AppUtil.notifwarning(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Checklist / ambil Foto Surat Pernyataan Nasabah");
            return false;
        }

        if ((val_cb_suratpernyataankur && val_suratpernyataankur == 0) || (!val_cb_suratpernyataankur && val_suratpernyataankur != 0)){
            AppUtil.notifwarning(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Checklist / ambil Foto Surat Pernyataan tidak sedang menerima KUR");
            return false;
        }
//        if ((val_cb_suratketeranganlunas && val_suratketeranganlunas == 0) || (!val_cb_suratketeranganlunas && val_suratketeranganlunas != 0)){
//            AppUtil.notifwarning(KelengkapanDokumenActivity_Apr.this, findViewById(android.R.id.content), "Silahkan Checklist / ambil Foto Surat Pernyataan lunas KUR");
//            return false;
//        }
        return true;
    }


    private void sendData() {
        loading.setVisibility(View.VISIBLE);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendKelengkapanDokumen(getDataRequestValid());
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            CustomDialog.DialogSuccess(KelengkapanDokumenActivity.this, "Success!", "Input Data Kelengkapan Dokumen Sukses", KelengkapanDokumenActivity.this);
                        }
                        else{
                            AppUtil.notiferror(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public inputKelengkapanDokumen getDataRequestValid(){
        val_nosiup = et_nomorsiup.getText().toString().trim();

        inputKelengkapanDokumen req = new inputKelengkapanDokumen(idAplikasi, val_cb_formuliraplikasi, val_cb_kartukeluarga, val_cb_ktpnasabahpasangan, val_cb_npwp, val_cb_pasphoto, val_cb_suratijinusaha, val_cb_suratnikah, val_cb_catatankeuangan, val_cb_suratketeranganlunas, val_cb_suratpernyataankur, val_cb_daftarrencanapembiayaan,
                val_formuliraplikasi, val_kartukeluarga, val_ktpnasabahpasangan, val_npwp, val_pasphoto, val_suratijinusaha, val_suratnikah, val_catatankeuangan, val_suratketeranganlunas, val_suratpernyataankur, val_daftarrencanapembiayaan, val_nosiup);

        return req;
    }


    private void openCamera(int cameraCode) {
        checkCameraPermission(cameraCode);
    }

    private void directOpenCamera(int cameraCode){
        Uri outputFileUri = getCaptureImageOutputUri();
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(captureIntent, cameraCode);
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

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                outputFileUri = FileProvider.getUriForFile(KelengkapanDokumenActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "fotodokumen.png"));
            }
            else{
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "fotodokumen.png"));
            }
        }
        return outputFileUri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode){
                case TAKE_PICTURE_KTPNASABAHPASANGAN:
                    setDataImage(uri_ktpnasabahpasangan, bitmap_ktpnasabahpasangan, iv_ktpnasabahpasangan, data);
                    break;
                case TAKE_PICTURE_KARTUKELUARGA:
                    setDataImage(uri_kartukeluarga, bitmap_kartukeluarga, iv_kartukeluarga, data);
                    break;
                case TAKE_PICTURE_SURATNIKAH:
                    setDataImage(uri_suratnikah, bitmap_suratnikah, iv_suratnikah, data);
                    break;
                case TAKE_PICTURE_PASPHOTO:
                    setDataImage(uri_pasphoto, bitmap_pasphoto, iv_pasphoto, data);
                    break;
                case TAKE_PICTURE_NPWP:
                    setDataImage(uri_npwp, bitmap_npwp, iv_npwp, data);
                    break;
                case TAKE_PICTURE_FORMULIRAPLIKASI:
                    setDataImage(uri_formuliraplikasi, bitmap_formuliraplikasi, iv_formuliraplikasi, data);
                    break;
                case TAKE_PICTURE_SURATIJINUSAHA:
                    setDataImage(uri_suratijinusaha, bitmap_suratijinusaha, iv_suratijinusaha, data);
                    break;
                case TAKE_PICTURE_CATATANKEUANGAN:
                    setDataImage(uri_catatankeuangan, bitmap_catatankeuangan, iv_catatankeuangan, data);
                    break;
                case TAKE_PICTURE_DAFTARRENCANAPEMBIAYAAN:
                    setDataImage(uri_daftarrencanapembiayaan, bitmap_daftarrencanapembiayaan, iv_daftarrencanapembiayaan, data);
                    break;
                case TAKE_PICTURE_SURATPERNYATAANKUR:
                    setDataImage(uri_suratpernyataankur, bitmap_suratpernyataankur, iv_suratpernyataankur, data);
                    break;
                case TAKE_PICTURE_SURATKETERANGANLUNAS:
                    setDataImage(uri_suratketeranganlunas, bitmap_suratketeranganlunas, iv_suratketeranganlunas, data);
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

                bitmap_ktpnasabahpasangan = bitmap;
                bitmap_kartukeluarga = bitmap;
                bitmap_suratnikah = bitmap;
                bitmap_pasphoto = bitmap;
                bitmap_npwp = bitmap;
                bitmap_formuliraplikasi = bitmap;
                bitmap_suratijinusaha = bitmap;
                bitmap_catatankeuangan = bitmap;
                bitmap_daftarrencanapembiayaan = bitmap;
                bitmap_suratpernyataankur = bitmap;
                bitmap_suratketeranganlunas = bitmap;

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
                .with(KelengkapanDokumenActivity.this)
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
                                case "ktpnasabahpasangan":
                                    val_ktpnasabahpasangan = idFoto;
                                    break;
                                case "kartukeluarga":
                                    val_kartukeluarga = idFoto;
                                    break;
                                case "suratnikah":
                                    val_suratnikah = idFoto;
                                    break;
                                case "pasphoto":
                                    val_pasphoto = idFoto;
                                    break;
                                case "npwp":
                                    val_npwp = idFoto;
                                    break;
                                case "formuliraplikasi":
                                    val_formuliraplikasi = idFoto;
                                    break;
                                case "suratijinusaha":
                                    val_suratijinusaha = idFoto;
                                    break;
                                case "catatankeuangan":
                                    val_catatankeuangan = idFoto;
                                    break;
                                case "daftarrencanapembiayaan":
                                    val_daftarrencanapembiayaan = idFoto;
                                    break;
                                case "suratpernyataankur":
                                    val_suratpernyataankur = idFoto;
                                    break;
                                case "suratketeranganlunas":
                                    val_suratketeranganlunas = idFoto;
                                    break;
                                default:
                                    break;
                            }
                        }
                        else{
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KelengkapanDokumenActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }


    @Override
    public void success(boolean val) {
        if(val)
            finish();
    }

    @Override
    public void confirm(boolean val) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send:
                if (validatePhoto())
                    sendData();
                    break;
            case R.id.btn_ktpnasabahpasangan:
                isSelectPhoto = "ktpnasabahpasangan";
                openCamera(TAKE_PICTURE_KTPNASABAHPASANGAN);
                break;
            case R.id.btn_kartukeluarga:
                isSelectPhoto = "kartukeluarga";
                openCamera(TAKE_PICTURE_KARTUKELUARGA);
                break;
            case R.id.btn_suratnikah:
                isSelectPhoto = "suratnikah";
                openCamera(TAKE_PICTURE_SURATNIKAH);
                break;
            case R.id.btn_pasphoto:
                isSelectPhoto = "pasphoto";
                openCamera(TAKE_PICTURE_PASPHOTO);
                break;
            case R.id.btn_npwp:
                isSelectPhoto = "npwp";
                openCamera(TAKE_PICTURE_NPWP);
                break;
            case R.id.btn_formuliraplikasi:
                isSelectPhoto = "formuliraplikasi";
                openCamera(TAKE_PICTURE_FORMULIRAPLIKASI);
                break;
            case R.id.btn_suratijinusaha:
                isSelectPhoto = "suratijinusaha";
                openCamera(TAKE_PICTURE_SURATIJINUSAHA);
                break;
            case R.id.btn_catatankeuangan:
                isSelectPhoto = "catatankeuangan";
                openCamera(TAKE_PICTURE_CATATANKEUANGAN);
                break;
            case R.id.btn_daftarrencanapembiayaan:
                isSelectPhoto = "daftarrencanapembiayaan";
                openCamera(TAKE_PICTURE_DAFTARRENCANAPEMBIAYAAN);
                break;
            case R.id.btn_suratpernyataankur:
                isSelectPhoto = "suratpernyataankur";
                openCamera(TAKE_PICTURE_SURATPERNYATAANKUR);
                break;
            case R.id.btn_suratketeranganlunas:
                isSelectPhoto = "suratketeranganlunas";
                openCamera(TAKE_PICTURE_SURATKETERANGANLUNAS);
                break;
            case R.id.iv_ktpnasabahpasangan:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_ktpnasabahpasangan.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_kartukeluarga:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_kartukeluarga.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_suratnikah:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_suratnikah.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_pasphoto:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_pasphoto.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_npwp:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_npwp.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_formuliraplikasi:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_formuliraplikasi.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_suratijinusaha:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_suratijinusaha.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_catatankeuangan:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_catatankeuangan.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_daftarrencanapembiayaan:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_daftarrencanapembiayaan.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_suratpernyataankur:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_suratpernyataankur.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_suratketeranganlunas:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_suratketeranganlunas.getDrawable()).getSourceBitmap());
                break;
        }
    }
}
