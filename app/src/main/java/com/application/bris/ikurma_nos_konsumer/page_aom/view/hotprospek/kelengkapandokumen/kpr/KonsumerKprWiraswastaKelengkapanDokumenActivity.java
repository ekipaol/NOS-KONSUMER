package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.kelengkapandokumen.kpr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.KonsumerKPRWiraswastaInputKelengkapanDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryKelengkapanDokumen;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.model.hotprospek.KonsumerKprWiraswastaKelengkapanDokumen;
import com.application.bris.ikurma_nos_konsumer.page_aom.adapter.hotprospek.AgunanKelengkapanDokumenAdapter;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.ListAgunanKelengkapanDokumen;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.Constants;
import com.application.bris.ikurma_nos_konsumer.util.ImageHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KonsumerKprWiraswastaKelengkapanDokumenActivity extends AppCompatActivity implements ConfirmListener, View.OnClickListener{

    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.sv_parent_kelengkapan_dokumen_konsumer)
    ViewGroup sv_parent_kelengkapan_dokumen_konsumer;
    @BindView(R.id.rl_parent_kelengkapan_dokumen_konsumer)
    ViewGroup rl_parent_kelengkapan_dokumen_konsumer;
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
    @BindView(R.id.cb_keterangan_usaha)
    CheckBox cb_keterangan_usaha;
    @BindView(R.id.cb_spp_rab)
    CheckBox cb_spp_rab;
    @BindView(R.id.cb_siup)
    CheckBox cb_siup;
    @BindView(R.id.cb_laporan_keuangan)
    CheckBox cb_laporan_keuangan;
    @BindView(R.id.cb_rekap_bon)
    CheckBox cb_rekap_bon;
    @BindView(R.id.cb_tdp)
    CheckBox cb_tdp;
    @BindView(R.id.cb_npwp_badan)
    CheckBox cb_npwp_badan;
    @BindView(R.id.cb_rekening_koran)
    CheckBox cb_rekening_koran;


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
    @BindView(R.id.iv_keterangan_usaha)
    ImageView iv_keterangan_usaha;
    @BindView(R.id.iv_spp_rab)
    ImageView iv_spp_rab;
    @BindView(R.id.iv_siup)
    ImageView iv_siup;
    @BindView(R.id.iv_laporan_keuangan)
    ImageView iv_laporan_keuangan;
    @BindView(R.id.iv_rekap_bon)
    ImageView iv_rekap_bon;
    @BindView(R.id.iv_tdp)
    ImageView iv_tdp;
    @BindView(R.id.iv_npwp_badan)
    ImageView iv_npwp_badan;
    @BindView(R.id.iv_rekening_koran)
    ImageView iv_rekening_koran;

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
    @BindView(R.id.btn_keterangan_usaha)
    ImageView btn_keterangan_usaha;
    @BindView(R.id.btn_spp_rab)
    ImageView btn_spp_rab;
    @BindView(R.id.btn_siup)
    ImageView btn_siup;
    @BindView(R.id.btn_laporan_keuangan)
    ImageView btn_laporan_keuangan;
    @BindView(R.id.btn_rekap_bon)
    ImageView btn_rekap_bon;
    @BindView(R.id.btn_tdp)
    ImageView btn_tdp;
    @BindView(R.id.btn_npwp_badan)
    ImageView btn_npwp_badan;
    @BindView(R.id.btn_rekening_koran)
    ImageView btn_rekening_koran;

    @BindView(R.id.ll_skpg)
    LinearLayout ll_skpg;
    @BindView(R.id.v_skpg)
    View v_skpg;

    @BindView(R.id.ll_surat_rekomendasi)
    LinearLayout ll_surat_rekomendasi;
    @BindView(R.id.v_surat_rekomendasi)
    View v_surat_rekomendasi;


    @BindView(R.id.rv_listagunan)
    RecyclerView rv_listagunan;
    @BindView(R.id.ll_emptydata)
    LinearLayout ll_emptydata;

    private int idAplikasi;
    private String kodeProduct;
    private int idTujuan;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String dataString;
    private KonsumerKprWiraswastaKelengkapanDokumen data;
    private String klasifikasiKredit;

    private boolean val_cb_ktpnasabahpasangan = false;
    private boolean val_cb_kartukeluarga = false;
    private boolean val_cb_suratnikah = false;
    private boolean val_cb_pasphoto = false;
    private boolean val_cb_npwp = false;
    private boolean val_cb_formuliraplikasi = false;
    private boolean val_cb_keterangan_usaha = false;
    private boolean val_cb_spp_rab = false;
    private boolean val_cb_siup = false;
    private boolean val_cb_laporan_keuangan = false;
    private boolean val_cb_rekap_bon = false;
    private boolean val_cb_tdp = false;
    private boolean val_cb_npwp_badan = false;
    private boolean val_cb_rekening_koran = false;


    private int val_ktpnasabahpasangan = 0;
    private int val_kartukeluarga = 0;
    private int val_suratnikah = 0;
    private int val_pasphoto = 0;
    private int val_npwp = 0;
    private int val_formuliraplikasi = 0;
    private int val_keterangan_usaha = 0;
    private int val_spp_rab = 0;
    private int val_siup = 0;
    private int val_laporan_keuangan = 0;
    private int val_npwp_badan = 0;
    private int val_rekening_koran = 0;
    private int val_rekap_bon = 0;
    private int val_tdp = 0;


    private String val_nosiup;

    private final int TAKE_PICTURE_KTPNASABAHPASANGAN = 1;
    private final int TAKE_PICTURE_KARTUKELUARGA = 2;
    private final int TAKE_PDF_SURATNIKAH = 3;
    private final int TAKE_PICTURE_PASPHOTO = 4;
    private final int TAKE_PICTURE_NPWP = 5;
    private final int TAKE_PICTURE_FORMULIRAPLIKASI = 6;
    private final int TAKE_PDF_KETERANGAN_USAHA = 7;
    private final int TAKE_PDF_SKK_RAB = 8;
    private final int TAKE_PICTURE_SIUP = 9;
    private final int TAKE_PICTURE_LAPORAN_KEUANGAN = 10;
    private final int TAKE_PICTURE_NPWP_BADAN = 11;
    private final int TAKE_PDF_REKAP_BON = 12;
    private final int TAKE_PDF_TDP = 13;
    private final int TAKE_PICTURE_REKENING_KORAN = 14;


    private String isSelectPhoto = "";

    private Bitmap loadedPicture;

    private Uri uri_ktpnasabahpasangan, uri_kartukeluarga, uri_suratnikah, uri_pasphoto,
            uri_npwp, uri_formuliraplikasi, uri_skpegawaitetap, uri_skjabatanterakhir,
            uri_suratrekomendasiperusahaan, uri_skpg, uri_suratkuasadebetrekening, uri_slipgaji,
            uri_rekkoran, uri_kwitansi;

    private Bitmap bitmap_ktpnasabahpasangan, bitmap_kartukeluarga, bitmap_pasphoto,
            bitmap_npwp, bitmap_formuliraplikasi, bitmap_siup,
            bitmap_laporan_keuangan,bitmap_npwp_badan,bitmap_rekening_koran;

    private int plafond;

    private String approved;

    private List<ListAgunanKelengkapanDokumen> dataAgunan;
    private AgunanKelengkapanDokumenAdapter adapterAgunan;
    private LinearLayoutManager layoutAgunan;
    private static final int BUFFER_SIZE = 1024 * 2;
    private static final String IMAGE_DIRECTORY = "/file_kelengkapan_dokumen";
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konsumer_kpr_wiraswasta_hotprospek_kelengkapan_dokumen);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        idTujuan = getIntent().getIntExtra("idTujuan", 0);
        kodeProduct = getIntent().getStringExtra("kodeProduct");
        approved = getIntent().getStringExtra("approved");
        klasifikasiKredit = getIntent().getStringExtra("klasifikasiKredit");
        plafond = getIntent().getIntExtra("plafond", 0);
        realm=Realm.getDefaultInstance();

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, getString(R.string.submenu_hotprospek_kelengkapandokumen));
        requestMultiplePermissions();
        loadKelengkapanDokumen();
        //membuat semua checkbox mengeluarkan toast ketika di pencet, dan disable click
        autoCheckboxOnclick(sv_parent_kelengkapan_dokumen_konsumer);
        onClickCheckbox();
        otherViewModification();

        //disable uploadbutton if approved
        if (approved.equalsIgnoreCase("yes")){
            btn_formuliraplikasi.setVisibility(View.GONE);
            btn_ktpnasabahpasangan.setVisibility(View.GONE);
            btn_kartukeluarga.setVisibility(View.GONE);
            btn_suratnikah.setVisibility(View.GONE);
            btn_pasphoto.setVisibility(View.GONE);
            btn_keterangan_usaha.setVisibility(View.GONE);
            btn_spp_rab.setVisibility(View.GONE);
            btn_siup.setVisibility(View.GONE);
            btn_laporan_keuangan.setVisibility(View.GONE);
            btn_rekap_bon.setVisibility(View.GONE);
            btn_npwp_badan.setVisibility(View.GONE);
            btn_tdp.setVisibility(View.GONE);
            btn_rekening_koran.setVisibility(View.GONE);
            btn_npwp.setVisibility(View.GONE);
            btn_send.setVisibility(View.GONE);
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
        iv_keterangan_usaha.setOnClickListener(this);
        iv_spp_rab.setOnClickListener(this);
        iv_siup.setOnClickListener(this);
        iv_laporan_keuangan.setOnClickListener(this);
        iv_rekap_bon.setOnClickListener(this);
        iv_tdp.setOnClickListener(this);
        iv_npwp_badan.setOnClickListener(this);
        iv_rekening_koran.setOnClickListener(this);

        btn_send.setOnClickListener(this);
        btn_ktpnasabahpasangan.setOnClickListener(this);
        btn_kartukeluarga.setOnClickListener(this);
        btn_suratnikah.setOnClickListener(this);
        btn_pasphoto.setOnClickListener(this);
        btn_npwp.setOnClickListener(this);
        btn_formuliraplikasi.setOnClickListener(this);
        btn_keterangan_usaha.setOnClickListener(this);
        btn_spp_rab.setOnClickListener(this);
        btn_siup.setOnClickListener(this);
        btn_laporan_keuangan.setOnClickListener(this);
        btn_rekap_bon.setOnClickListener(this);
        btn_tdp.setOnClickListener(this);
        btn_npwp_badan.setOnClickListener(this);
        btn_rekening_koran.setOnClickListener(this);
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

        cb_keterangan_usaha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_keterangan_usaha = true;
                }
                else {
                    val_cb_keterangan_usaha = false;
                }
            }
        });

        cb_spp_rab.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_spp_rab = true;
                }
                else {
                    val_cb_spp_rab = false;
                }
            }
        });

        cb_siup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_siup = true;
                }
                else {
                    val_cb_siup = false;
                }
            }
        });

        cb_laporan_keuangan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_laporan_keuangan = true;
                }
                else {
                    val_cb_laporan_keuangan = false;
                }
            }
        });


        cb_rekap_bon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_rekap_bon = true;
                }
                else {
                    val_cb_rekap_bon = false;
                }
            }
        });

        cb_tdp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_tdp = true;
                }
                else {
                    val_cb_tdp = false;
                }
            }
        });

        cb_npwp_badan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_npwp_badan = true;
                }
                else {
                    val_cb_npwp_badan = false;
                }
            }
        });

        cb_rekening_koran.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    val_cb_rekening_koran = true;
                }
                else {
                    val_cb_rekening_koran = false;
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
        cb_keterangan_usaha.setChecked(false);
        cb_spp_rab.setChecked(false);
        cb_siup.setChecked(false);
        cb_laporan_keuangan.setChecked(false);
        cb_rekap_bon.setChecked(false);
        cb_tdp.setChecked(false);
        cb_npwp_badan.setChecked(false);
        cb_rekening_koran.setChecked(false);


    }

    private void loadKelengkapanDokumen() {
//        inquiryKelengkapanDokumen req = new inquiryKelengkapanDokumen(idAplikasi);

        //pantekan kelengkapan
//        Toast.makeText(this, "ada pantekan id aplikasi load kelengkapan", Toast.LENGTH_SHORT).show();
        inquiryKelengkapanDokumen req = new inquiryKelengkapanDokumen(idAplikasi);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryKelengkapanDokumenKpr(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    setUncheckboxDefault();
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            Type typeAgunan = new TypeToken<List<ListAgunanKelengkapanDokumen>>() {
                            }.getType();

                            dataString = response.body().getData().get("kelDokumen").toString();

                            if (!dataString.equalsIgnoreCase("{}")){
                                data = gson.fromJson(dataString, KonsumerKprWiraswastaKelengkapanDokumen.class);
//                                dataAgunan = gson.fromJson(response.body().getData().get("kelDokumen").getAsJsonObject().get("ID_DOKUMEN_AGUNAN").toString(), typeAgunan);




                                setCheckbox();
                                setValData();

                                if (dataAgunan!=null&&dataAgunan.size() > 0) {
                                    ll_emptydata.setVisibility(View.GONE);
                                    initializeAgunan();
                                } else {
                                    ll_emptydata.setVisibility(View.VISIBLE);
                                }
                            }
                            else{
                                setCheckBoxRealm();
                            }
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void setCheckbox(){

        if(data != null){
            if (data.getFC_KTP()!=null&&data.getFC_KTP())
                cb_ktpnasabahpasangan.setChecked(true);

            if(data.getFC_KK()!=null&&data.getFC_KK())
                cb_kartukeluarga.setChecked(true);

            if (data.getFC_SURAT_NIKAH()!=null&&data.getFC_SURAT_NIKAH())
                cb_suratnikah.setChecked(true);

            if (data.getPAS_PHOTO()!=null&&data.getPAS_PHOTO())
                cb_pasphoto.setChecked(true);

            if (data.getFC_NPWP_PRIBADI()!=null&&data.getFC_NPWP_PRIBADI())
                cb_npwp.setChecked(true);

            if (data.getAPLIKASI_PERMOHONAN()!=null&&data.getAPLIKASI_PERMOHONAN())
                cb_formuliraplikasi.setChecked(true);

            if (data.getSURAT_KETERANGAN_USAHA()!=null&&data.getSURAT_KETERANGAN_USAHA())
                cb_keterangan_usaha.setChecked(true);

            if (data.getSPR_SPP_RAB()!=null&&data.getSPR_SPP_RAB())
                cb_spp_rab.setChecked(true);

            if (data.getFC_SIUP()!=null&&data.getFC_SIUP())
                cb_siup.setChecked(true);

            if (data.getLAPORAN_KEUANGAN()!=null&&data.getLAPORAN_KEUANGAN())
                cb_laporan_keuangan.setChecked(true);


            if (data.getREKAP_BON_BUKU_BESAR()!=null&&data.getREKAP_BON_BUKU_BESAR())
                cb_rekap_bon.setChecked(true);

            if (data.getFC_TDP()!=null&&data.getFC_TDP())
                cb_tdp.setChecked(true);

            if (data.getFC_NPWP_BADAN_USAHA()!=null&&data.getFC_NPWP_BADAN_USAHA())
                cb_tdp.setChecked(true);

            if (data.getREKENING_KORAN()!=null&&data.getREKENING_KORAN())
                cb_rekening_koran.setChecked(true);



            //bitmaps

            bitmap_ktpnasabahpasangan = setLoadImage(iv_ktpnasabahpasangan, data.getID_DOKUMEN_KTP());
            bitmap_kartukeluarga = setLoadImage(iv_kartukeluarga, data.getID_DOKUMEN_KK());
            bitmap_pasphoto = setLoadImage(iv_pasphoto, data.getID_DOKUMEN_PAS_PHOTO());
            bitmap_npwp = setLoadImage(iv_npwp, data.getID_FC_NPWP_PRIBADI());
            bitmap_formuliraplikasi = setLoadImage(iv_formuliraplikasi, data.getID_DOKUMEN_APLIKASI());
            bitmap_siup = setLoadImage(iv_siup, data.getID_DOKUMEN_FC_SIUP());
            bitmap_laporan_keuangan = setLoadImage(iv_laporan_keuangan, data.getID_DOKUMEN_LAPORAN_KEUANGAN());
            bitmap_npwp_badan = setLoadImage(iv_npwp_badan, data.getID_DOKUMEN_FC_NPWP_BADAN_USAHA());
            bitmap_rekening_koran = setLoadImage(iv_rekening_koran, data.getID_DOKUMEN_REKENING_KORAN());


            //pdfs
            if (data.getID_DOKUMEN_SURAT_NIKAH() != 0) {
                iv_suratnikah.setImageDrawable(ContextCompat.getDrawable(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
            }

            if (data.getID_DOKUMEN_SURAT_KETERANGAN_USAHA() != 0) {
                iv_keterangan_usaha.setImageDrawable(ContextCompat.getDrawable(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
            }
            if (data.getID_DOKUMEN_SPR_SPP_RAB() != 0) {
                iv_spp_rab.setImageDrawable(ContextCompat.getDrawable(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
            }

            if (data.getID_DOKUMEN_REKAP_BON_BUKU_BESAR() != 0) {
                iv_rekap_bon.setImageDrawable(ContextCompat.getDrawable(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
            }
            if (data.getID_DOKUMEN_FC_TDP() != 0) {
                iv_tdp.setImageDrawable(ContextCompat.getDrawable(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
            }


        }
    }

    //method ini sudah include set val datanya juga, jadi cukup panggil ini, tidak usah buat valdata khusus realm

    //seluruh blok kode harus didalam trycatch, karena kalau ada yang null ,kode bawahnya gak akan jalan kalau gak trycatch
    //dan trycatch-nya jangan digabung jadi satu, harus dipisah pisah tiap if
    private void setCheckBoxRealm(){

        KonsumerKprWiraswastaKelengkapanDokumen realmKelengkapan=realm.where(KonsumerKprWiraswastaKelengkapanDokumen.class).equalTo("ID_APLIKASI",idAplikasi).findFirst();


        if(realmKelengkapan != null){
            try {
                if (realmKelengkapan.getID_DOKUMEN_KTP()!=0) {
                    cb_ktpnasabahpasangan.setChecked(true);
                    bitmap_ktpnasabahpasangan = setLoadImage(iv_ktpnasabahpasangan, realmKelengkapan.getID_DOKUMEN_KTP());
                    val_ktpnasabahpasangan = realmKelengkapan.getID_DOKUMEN_KTP();
                }
            }
            catch (NullPointerException e){
                Log.d("ada error di ","dokumen ktp");
            }

            try{
                if (realmKelengkapan.getID_DOKUMEN_KK()!=0) {
                    cb_kartukeluarga.setChecked(true);
                    bitmap_kartukeluarga = setLoadImage(iv_kartukeluarga, realmKelengkapan.getID_DOKUMEN_KK());
                    val_kartukeluarga = realmKelengkapan.getID_DOKUMEN_KK();
                }
            }
            catch (NullPointerException e){
                Log.d("ada error di ","dokumen kartu keluarga");
            }

            try{
                if (realmKelengkapan.getID_DOKUMEN_SURAT_NIKAH()!=0) {
                    cb_suratnikah.setChecked(true);
                    iv_suratnikah.setImageDrawable(ContextCompat.getDrawable(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
                    val_suratnikah = realmKelengkapan.getID_DOKUMEN_SURAT_NIKAH();
                }

            }
            catch (NullPointerException e){
                Log.d("ada error di ","dokumen surat nikah");
            }

            try{
                if (realmKelengkapan.getID_DOKUMEN_PAS_PHOTO()!=0) {
                    cb_pasphoto.setChecked(true);
                    bitmap_pasphoto = setLoadImage(iv_pasphoto, realmKelengkapan.getID_DOKUMEN_PAS_PHOTO());
                    val_pasphoto = realmKelengkapan.getID_DOKUMEN_PAS_PHOTO();
                }
            }
            catch (NullPointerException e){
                Log.d("ada error di ","dokumen pas foto");
            }


            try{
                if (realmKelengkapan.getID_DOKUMEN_NPWP_PRIBADI()!=0) {
                    cb_npwp.setChecked(true);
                    bitmap_npwp = setLoadImage(iv_npwp, realmKelengkapan.getID_DOKUMEN_NPWP_PRIBADI());
                    val_npwp = realmKelengkapan.getID_DOKUMEN_NPWP_PRIBADI();
                }
            }
            catch (NullPointerException e){
                Log.d("ada error di ","dokumen npwp");
            }

            try{
                if (realmKelengkapan.getID_DOKUMEN_APLIKASI()!=0) {
                    cb_formuliraplikasi.setChecked(true);
                    bitmap_formuliraplikasi = setLoadImage(iv_formuliraplikasi, realmKelengkapan.getID_DOKUMEN_APLIKASI());
                    val_formuliraplikasi = realmKelengkapan.getID_DOKUMEN_APLIKASI();
                }
            }
            catch (NullPointerException e){
                Log.d("ada error di ","dokumen formulir aplikasi");
            }

            try{
                if (realmKelengkapan.getID_DOKUMEN_SURAT_KETERANGAN_USAHA()!=0) {
                    cb_keterangan_usaha.setChecked(true);
                    iv_keterangan_usaha.setImageDrawable(ContextCompat.getDrawable(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
                    val_keterangan_usaha = realmKelengkapan.getID_DOKUMEN_SURAT_KETERANGAN_USAHA();
                }
            }
            catch (NullPointerException e){
                Log.d("ada error di ","dokumen keterangan usaha");
            }

            try{
                if (realmKelengkapan.getID_DOKUMEN_SPR_SPP_RAB()!=0) {
                    cb_spp_rab.setChecked(true);
                    iv_spp_rab.setImageDrawable(ContextCompat.getDrawable(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
                    val_spp_rab = realmKelengkapan.getID_DOKUMEN_SPR_SPP_RAB();
                }
            }
            catch (NullPointerException e){
                Log.d("ada error di ","dokumen spp rab");
            }

            try{
                if (realmKelengkapan.getID_DOKUMEN_FC_SIUP()!=0) {
                    cb_siup.setChecked(true);
                    bitmap_siup = setLoadImage(iv_siup, realmKelengkapan.getID_DOKUMEN_FC_SIUP());
                    val_siup = realmKelengkapan.getID_DOKUMEN_FC_SIUP();
                }
            }
            catch (NullPointerException e){
                Log.d("ada error di ","dokumen siup");
            }

            try{
                if (realmKelengkapan.getID_DOKUMEN_LAPORAN_KEUANGAN()!=0) {
                    cb_laporan_keuangan.setChecked(true);
                    bitmap_laporan_keuangan = setLoadImage(iv_laporan_keuangan, realmKelengkapan.getID_DOKUMEN_LAPORAN_KEUANGAN());
                    val_laporan_keuangan = realmKelengkapan.getID_DOKUMEN_LAPORAN_KEUANGAN();
                }
            }
            catch (NullPointerException e){
                Log.d("ada error di ","dokumen laporan keuangan");
            }



            try{
                if (realmKelengkapan.getID_DOKUMEN_REKAP_BON_BUKU_BESAR()!=0) {
                    cb_rekap_bon.setChecked(true);
                    iv_rekap_bon.setImageDrawable(ContextCompat.getDrawable(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
                    val_rekap_bon = realmKelengkapan.getID_DOKUMEN_REKAP_BON_BUKU_BESAR();
                }
            }
            catch (NullPointerException e){
                Log.d("ada error di ","dokumen rekap bon");
            }


            try{
                if (realmKelengkapan.getID_DOKUMEN_FC_TDP()!=0) {
                    cb_tdp.setChecked(true);
                    iv_tdp.setImageDrawable(ContextCompat.getDrawable(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
                    val_tdp = realmKelengkapan.getID_DOKUMEN_FC_TDP();
                }
            }
            catch (NullPointerException e){
                Log.d("ada error di ","dokumen tdp");
            }

            try{
                if (realmKelengkapan.getID_DOKUMEN_FC_NPWP_BADAN_USAHA()!=0) {
                    cb_npwp_badan.setChecked(true);
                    bitmap_npwp_badan = setLoadImage(iv_npwp_badan, realmKelengkapan.getID_DOKUMEN_FC_NPWP_BADAN_USAHA());
                    val_npwp_badan = realmKelengkapan.getID_DOKUMEN_FC_NPWP_BADAN_USAHA();
                }
            }
            catch (NullPointerException e){
                Log.d("ada error di ","dokumen npwp badan usaha");
            }

            try{
                if (realmKelengkapan.getID_DOKUMEN_REKENING_KORAN()!=0) {
                    cb_rekening_koran.setChecked(true);
                    bitmap_rekening_koran = setLoadImage(iv_rekening_koran, realmKelengkapan.getID_DOKUMEN_REKENING_KORAN());
                    val_rekening_koran = realmKelengkapan.getID_DOKUMEN_REKENING_KORAN();
                }
            }
            catch (NullPointerException e){
                Log.d("ada error di ","dokumen rekening koran");
            }




        }
    }

    private void setValData(){


        if (data != null){
            val_cb_ktpnasabahpasangan = data.getFC_KTP();
            val_cb_kartukeluarga = data.getFC_KK();
            val_cb_suratnikah = data.getFC_SURAT_NIKAH();
            val_cb_pasphoto = data.getPAS_PHOTO();
            val_cb_npwp = data.getFC_NPWP_PRIBADI();
            val_cb_formuliraplikasi = data.getAPLIKASI_PERMOHONAN();
            val_cb_keterangan_usaha = data.getSURAT_KETERANGAN_USAHA();
            val_cb_spp_rab = data.getSPR_SPP_RAB();
            val_cb_siup = data.getFC_SIUP();
            val_cb_laporan_keuangan = data.getLAPORAN_KEUANGAN();
            val_cb_rekap_bon = data.getREKAP_BON_BUKU_BESAR();
            val_cb_tdp = data.getFC_TDP();
            val_cb_npwp_badan = data.getFC_NPWP_BADAN_USAHA();
            val_cb_rekening_koran = data.getREKENING_KORAN();



            val_ktpnasabahpasangan = data.getID_DOKUMEN_KTP();
            val_kartukeluarga = data.getID_DOKUMEN_KK();
            val_suratnikah = data.getID_DOKUMEN_SURAT_NIKAH();
            val_pasphoto = data.getID_DOKUMEN_PAS_PHOTO();
            val_npwp = data.getID_FC_NPWP_PRIBADI();
            val_formuliraplikasi = data.getID_DOKUMEN_APLIKASI();
            val_keterangan_usaha = data.getID_DOKUMEN_SURAT_KETERANGAN_USAHA();
            val_spp_rab = data.getID_DOKUMEN_SPR_SPP_RAB();
            val_siup = data.getID_DOKUMEN_FC_SIUP();
            val_laporan_keuangan = data.getID_DOKUMEN_LAPORAN_KEUANGAN();
            val_rekap_bon = data.getID_DOKUMEN_REKAP_BON_BUKU_BESAR();
            val_tdp = data.getID_DOKUMEN_FC_TDP();
            val_npwp_badan = data.getID_DOKUMEN_FC_NPWP_BADAN_USAHA();
            val_rekening_koran = data.getID_DOKUMEN_REKENING_KORAN();

        }
    }




    public void initializeAgunan(){
        adapterAgunan = new AgunanKelengkapanDokumenAdapter(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, dataAgunan);
        layoutAgunan = new LinearLayoutManager(KonsumerKprWiraswastaKelengkapanDokumenActivity.this);
        rv_listagunan.setLayoutManager(layoutAgunan);
        rv_listagunan.setAdapter(adapterAgunan);

        if (dataAgunan == null){
            ll_emptydata.setVisibility(View.VISIBLE);
        }
    }

    private boolean validateForm(){
        if ((val_cb_ktpnasabahpasangan && val_ktpnasabahpasangan == 0) || (!val_cb_ktpnasabahpasangan && val_ktpnasabahpasangan != 0) || (!val_cb_ktpnasabahpasangan && val_ktpnasabahpasangan == 0)  ){
            AppUtil.notifwarning(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Foto KTP Nasabah dan/atau Pasangan");
            return false;
        }
        if ((val_cb_kartukeluarga && val_kartukeluarga == 0) || (!val_cb_kartukeluarga && val_kartukeluarga != 0) || (!val_cb_kartukeluarga && val_kartukeluarga == 0)){
            AppUtil.notifwarning(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Foto KK");
            return false;
        }
        if ((val_cb_suratnikah && val_suratnikah == 0) || (!val_cb_suratnikah && val_suratnikah != 0) || (!val_cb_suratnikah && val_suratnikah == 0)){
            AppUtil.notifwarning(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload File Surat Nikah/Akta Cerai/Surat Ket. Status Perkawinan Lainnya");
            return false;
        }
        if ((val_cb_pasphoto && val_pasphoto == 0) || (!val_cb_pasphoto && val_pasphoto != 0) || (!val_cb_pasphoto && val_pasphoto == 0)){
            AppUtil.notifwarning(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Foto Pas Photo");
            return false;
        }
        if ((val_cb_npwp && val_npwp == 0) || (!val_cb_npwp && val_npwp != 0) || (!val_cb_npwp && val_npwp == 0)){
            if (plafond >= Constants.VALIDATE_PLAFOND_NPWP){
                AppUtil.notifwarning(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Foto NPWP");
                return false;
            }
            else{
                return true;
            }
        }
        if ((val_cb_formuliraplikasi && val_formuliraplikasi == 0) || (!val_cb_formuliraplikasi && val_formuliraplikasi != 0) || (!val_cb_formuliraplikasi && val_formuliraplikasi == 0)){
            AppUtil.notifwarning(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Foto Formulir Aplikasi Pembiayaan");
            return false;
        }
        if ((val_cb_keterangan_usaha && val_keterangan_usaha == 0) || (!val_cb_keterangan_usaha && val_keterangan_usaha != 0) || (!val_cb_keterangan_usaha && val_keterangan_usaha == 0)){
            AppUtil.notifwarning(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload File Surat Keterangan Usaha");
            return false;
        }
        if ((val_cb_spp_rab && val_spp_rab == 0) || (!val_cb_spp_rab && val_spp_rab != 0) || (!val_cb_spp_rab && val_spp_rab == 0)){
            AppUtil.notifwarning(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload File SPP/RAB");
            return false;
        }


            if ((val_cb_siup && val_siup == 0) || (!val_cb_siup && val_siup != 0) || (!val_cb_siup && val_siup == 0)){
                AppUtil.notifwarning(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Foto SIUP");
                return false;
            }
            if ((val_cb_laporan_keuangan && val_laporan_keuangan == 0) || (!val_cb_laporan_keuangan && val_laporan_keuangan != 0) || (!val_cb_laporan_keuangan && val_laporan_keuangan == 0)){
                AppUtil.notifwarning(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Foto Laporan Keuangan");
                return false;
            }




        if ((val_cb_rekap_bon && val_rekap_bon == 0) || (!val_cb_rekap_bon && val_rekap_bon != 0) || (!val_cb_rekap_bon && val_rekap_bon == 0)){
            AppUtil.notifwarning(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload File Rekap Bon");
            return false;
        }
        if ((val_cb_tdp && val_tdp == 0) || (!val_cb_tdp && val_tdp != 0) || (!val_cb_tdp && val_tdp == 0)){
            AppUtil.notifwarning(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload File TDP");
            return false;
        }

        if ((val_cb_npwp_badan && val_npwp_badan == 0) || (!val_cb_npwp_badan && val_npwp_badan != 0) || (!val_cb_npwp_badan && val_npwp_badan == 0)){
            AppUtil.notifwarning(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Foto NPWP Badan");
            return false;
        }

        if ((val_cb_rekening_koran && val_rekening_koran == 0) || (!val_cb_rekening_koran && val_rekening_koran != 0) || (!val_cb_rekening_koran && val_rekening_koran == 0)){
            AppUtil.notifwarning(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Foto Rekening Koran");
            return false;
        }




        return true;
    }

    private void sendData() {
        loading.setVisibility(View.VISIBLE);
        KonsumerKPRWiraswastaInputKelengkapanDokumen req = new KonsumerKPRWiraswastaInputKelengkapanDokumen();
        //true false masing masing field
//        req.setIdAplikasi(idAplikasi);
//        Toast.makeText(this, "ada pantekan id aplikasi simpan kelengkapan", Toast.LENGTH_SHORT).show();
        req.setIdAplikasi(idAplikasi);
        req.setAplikasiPermohonan(val_cb_formuliraplikasi);
        req.setFcKtp(val_cb_ktpnasabahpasangan);
        req.setFcKK(val_cb_kartukeluarga);
        req.setFcSuratNikah(val_cb_suratnikah);
        req.setFcPasPhoto(val_cb_pasphoto);
        req.setSprSppRAB(val_cb_spp_rab);

        req.setFcNpwpPribadi(val_cb_npwp);
        req.setRekeningKoran(val_cb_tdp);
        req.setRekapBonBukuBesar(val_cb_rekap_bon);
        req.setLaporanKeuangan(val_cb_laporan_keuangan);
        req.setFcSiup(val_cb_siup);
        req.setSuratKeteranganUsaha(val_cb_keterangan_usaha);
        req.setRekeningKoran(val_cb_rekening_koran);




        //id dokumen/foto
        req.setIdPhotoaplikasiPermohonan(val_formuliraplikasi);
        req.setIdPhotofcKtp(val_ktpnasabahpasangan);
        req.setIdPhotofcKK(val_kartukeluarga);
        req.setIdPhotofcSuratNikah(val_suratnikah);
        req.setIdPhotofcPasPhoto(val_pasphoto);

        req.setIdPhotosprSppRAB(val_spp_rab);
        req.setIdPhotofcNpwpPribadi(val_npwp);
        req.setIdPhotofcTdp(val_tdp);
        req.setIdPhotorekapBonBukuBesar(val_rekap_bon);
        req.setIdPhotorekapBonBukuBesar(val_laporan_keuangan);
        req.setIdPhotofcSiup(val_siup);
        req.setIdPhotosuratKeteranganUsaha(val_keterangan_usaha);
        req.setIdPhotorekeningKoran(val_rekening_koran);



        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendKelengkapanDokumenKprWiraswasta(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            CustomDialog.DialogSuccess(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, "Success!", "Input Data Kelengkapan Dokumen Sukses", KonsumerKprWiraswastaKelengkapanDokumenActivity.this);
                        }
                        else{
                            AppUtil.notiferror(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }



    private void openCamera(int cameraCode) {
        checkCameraPermission(cameraCode);
    }

    private void directOpenCamera(int cameraCode){
        Uri outputFileUri = getCaptureImageOutputUri();
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
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

    private void  requestMultiplePermissions(){
        Dexter.withActivity(this)
                .withPermissions(

                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
//                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
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
                outputFileUri = FileProvider.getUriForFile(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "fotodokumen.png"));
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
                case TAKE_PDF_SURATNIKAH:
                    setDataPdf(iv_suratnikah, data);
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
                case TAKE_PDF_KETERANGAN_USAHA:
                    setDataPdf(iv_keterangan_usaha, data);
                    break;
                case TAKE_PDF_SKK_RAB:
                    setDataPdf(iv_spp_rab, data);
                    break;
                case TAKE_PICTURE_SIUP:
                    setDataImage(uri_suratrekomendasiperusahaan, bitmap_siup, iv_siup, data);
                    break;
                case TAKE_PICTURE_LAPORAN_KEUANGAN:
                    setDataImage(uri_skpg, bitmap_laporan_keuangan, iv_laporan_keuangan, data);
                    break;
                case TAKE_PDF_REKAP_BON:
                    setDataPdf(iv_rekap_bon, data);
                    break;
                case TAKE_PDF_TDP:
                    setDataPdf(iv_tdp, data);
                    break;
                case TAKE_PICTURE_NPWP_BADAN:
                    setDataImage(uri_skpg, bitmap_npwp_badan, iv_npwp_badan, data);
                    break;
                case TAKE_PICTURE_REKENING_KORAN:
                    setDataImage(uri_skpg, bitmap_rekening_koran, iv_rekening_koran, data);
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

    private void initImageFileNameDokumen() {
        String fileName = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date())+"_"+System.currentTimeMillis()+ ".jpg";
//        appPreferences.setDokumenSuratNikah(fileName);
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
                bitmap_pasphoto = bitmap;
                bitmap_npwp = bitmap;
                bitmap_formuliraplikasi = bitmap;
                bitmap_siup = bitmap;
                bitmap_laporan_keuangan = bitmap;
                bitmap_npwp_badan = bitmap;
                bitmap_rekening_koran = bitmap;

            } catch (Exception e) {
                e.printStackTrace();
            }
            String filename = appPreferences.getFotoKelengkapanDokumen();
            ImageHandler.saveImageToCache(this, bitmap, filename);
            uploadFoto(filename);
        }
    }

    private void setDataPdf(ImageView iv, Intent data){
        iv.setImageDrawable(ContextCompat.getDrawable(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
        initImageFileNameDokumen();
        Uri uri = data.getData();
        String filename = appPreferences.getFotoKelengkapanDokumen();
        String path = getFilePathFromURI(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, uri, filename);
        uploadFile(path, iv);
    }

    public Bitmap setLoadImage(final ImageView iv, int idFoto){
        String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + idFoto;
        Glide
                .with(KonsumerKprWiraswastaKelengkapanDokumenActivity.this)
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
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().uploadFoto(fileBody);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    loading.setVisibility(View.GONE);
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            int idFoto = response.body().getData().get("id").getAsInt();

                            KonsumerKprWiraswastaKelengkapanDokumen realmKelengkapan=realm.where(KonsumerKprWiraswastaKelengkapanDokumen.class).equalTo("ID_APLIKASI",idAplikasi).findFirst();

                            KonsumerKprWiraswastaKelengkapanDokumen realmCopyKelengkapan=new KonsumerKprWiraswastaKelengkapanDokumen();

                            if(!realm.isInTransaction()){
                                realm.beginTransaction();
                            }

                            if(realmKelengkapan==null){
                                realmCopyKelengkapan.setID_APLIKASI(idAplikasi);
                                realm.insertOrUpdate(realmCopyKelengkapan);
                                realm.commitTransaction();
                                //realm.close();
                            }
                            else{
                                realmCopyKelengkapan=realm.copyFromRealm(realmKelengkapan);
                            }




                            if(!realm.isInTransaction()){
                                realm.beginTransaction();
                            }

                            switch (isSelectPhoto){
                                case "ktpnasabahpasangan":
                                    val_ktpnasabahpasangan = idFoto;
                                    cb_ktpnasabahpasangan.setChecked(true);
                                    val_cb_ktpnasabahpasangan = true;
                                    Log.d("masukpakeki","it is set");
                                    realmCopyKelengkapan.setID_DOKUMEN_KTP(idFoto);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                case "kartukeluarga":
                                    val_kartukeluarga = idFoto;
                                    cb_kartukeluarga.setChecked(true);
                                    val_cb_kartukeluarga = true;
                                    realmCopyKelengkapan.setID_DOKUMEN_KK(idFoto);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                case "pasphoto":
                                    val_pasphoto = idFoto;
                                    cb_pasphoto.setChecked(true);
                                    val_cb_pasphoto = true;
                                    realmCopyKelengkapan.setID_DOKUMEN_PAS_PHOTO(idFoto);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                case "npwp":
                                    val_npwp = idFoto;
                                    cb_npwp.setChecked(true);
                                    val_cb_npwp = true;
                                    realmCopyKelengkapan.setID_DOKUMEN_NPWP_PRIBADI(idFoto);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                case "formuliraplikasi":
                                    val_formuliraplikasi = idFoto;
                                    cb_formuliraplikasi.setChecked(true);
                                    val_cb_formuliraplikasi = true;
                                    realmCopyKelengkapan.setID_DOKUMEN_APLIKASI(idFoto);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                case "siup":
                                    val_siup = idFoto;
                                    cb_siup.setChecked(true);
                                    val_cb_siup = true;
                                    realmCopyKelengkapan.setID_DOKUMEN_FC_SIUP(idFoto);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                case "laporankeuangan":
                                    val_laporan_keuangan = idFoto;
                                    cb_laporan_keuangan.setChecked(true);
                                    val_cb_laporan_keuangan = true;
                                    realmCopyKelengkapan.setID_DOKUMEN_LAPORAN_KEUANGAN(idFoto);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                case "npwpbadan":
                                    val_npwp_badan = idFoto;
                                    cb_npwp_badan.setChecked(true);
                                    val_cb_npwp_badan = true;
                                    realmCopyKelengkapan.setID_DOKUMEN_FC_NPWP_BADAN_USAHA(idFoto);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                case "rekeningkoran":
                                    val_rekening_koran = idFoto;
                                    cb_rekening_koran.setChecked(true);
                                    val_cb_rekening_koran = true;
                                    realmCopyKelengkapan.setID_DOKUMEN_REKENING_KORAN(idFoto);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                default:
                                    break;
                            }
                        }
                        else{
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void uploadFile(String filename, final ImageView iv){
//        Log.d("Test Masuk", "uploadFile");
        loading.setVisibility(View.VISIBLE);
        File file = new File(filename);
//        File file = new File(getApplicationContext().getCacheDir(), filename);
//        File file = new File(KonsumerKprWiraswastaKelengkapanDokumenActivity.class, filename);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        String fileNamePdf = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date())+"_"+System.currentTimeMillis()+ ".pdf";
        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", fileNamePdf, requestBody);
//        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), pdfname);

//        PDFInterface getResponse = retrofit.create(PDFInterface.class);
//        Call<ParseResponse> call = getResponse.uploadImage(fileToUpload);

//        File imageFile = new File(getApplicationContext().getCacheDir(), filename);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), imageFile);
//        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().uploadFile(fileBody);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    loading.setVisibility(View.GONE);
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            int idPdF = response.body().getData().get("id").getAsInt();
                            Log.d("idPdF", String.valueOf(idPdF));

                            KonsumerKprWiraswastaKelengkapanDokumen realmKelengkapan=realm.where(KonsumerKprWiraswastaKelengkapanDokumen.class).equalTo("ID_APLIKASI",idAplikasi).findFirst();

                            KonsumerKprWiraswastaKelengkapanDokumen realmCopyKelengkapan=new KonsumerKprWiraswastaKelengkapanDokumen();

                            if(!realm.isInTransaction()){
                                realm.beginTransaction();
                            }

                            if(realmKelengkapan==null){
                                realmCopyKelengkapan.setID_APLIKASI(idAplikasi);
                                realm.insertOrUpdate(realmCopyKelengkapan);
                                realm.commitTransaction();
//                                //realm.close();
                            }
                            else{
                                realmCopyKelengkapan=realm.copyFromRealm(realmKelengkapan);
                            }




                            if(!realm.isInTransaction()){
                                realm.beginTransaction();
                            }

                            switch (isSelectPhoto){
                                case "suratnikah":
                                    val_suratnikah = idPdF;
                                    cb_suratnikah.setChecked(true);
                                    val_cb_suratnikah = true;
                                    realmCopyKelengkapan.setID_DOKUMEN_SURAT_NIKAH(idPdF);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                case "keteranganusaha":
                                    val_keterangan_usaha = idPdF;
                                    cb_keterangan_usaha.setChecked(true);
                                    val_cb_keterangan_usaha = true;
                                    realmCopyKelengkapan.setID_DOKUMEN_SURAT_KETERANGAN_USAHA(idPdF);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                case "spprab":
                                    val_spp_rab = idPdF;
                                    cb_spp_rab.setChecked(true);
                                    val_cb_spp_rab = true;
                                    realmCopyKelengkapan.setID_DOKUMEN_SPR_SPP_RAB(idPdF);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                case "rekapbon":
                                    val_rekap_bon = idPdF;
                                    cb_rekap_bon.setChecked(true);
                                    val_cb_rekap_bon = true;
                                    realmCopyKelengkapan.setID_DOKUMEN_REKAP_BON_BUKU_BESAR(idPdF);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                case "tdp":
                                    val_tdp = idPdF;
                                    cb_tdp.setChecked(true);
                                    val_cb_tdp = true;
                                    realmCopyKelengkapan.setID_DOKUMEN_FC_TDP(idPdF);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                default:
                                    break;
                            }
                        }
                        else{
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                Log.d("onFailure", t.toString());
                AppUtil.notiferror(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");

        switch (v.getId()){
            case R.id.btn_send:
                if (validateForm())
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
                startActivityForResult(intent,TAKE_PDF_SURATNIKAH);
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
            case R.id.btn_keterangan_usaha:
                isSelectPhoto = "keteranganusaha";
                startActivityForResult(intent, TAKE_PDF_KETERANGAN_USAHA);
                break;
            case R.id.btn_spp_rab:
                isSelectPhoto = "spprab";
                startActivityForResult(intent, TAKE_PDF_SKK_RAB);
                break;
            case R.id.btn_siup:
                isSelectPhoto = "siup";
                openCamera(TAKE_PICTURE_SIUP);
                break;
            case R.id.btn_laporan_keuangan:
                isSelectPhoto = "laporankeuangan";
                openCamera(TAKE_PICTURE_LAPORAN_KEUANGAN);
                break;
            case R.id.btn_rekap_bon:
                isSelectPhoto = "rekapbon";
                startActivityForResult(intent, TAKE_PDF_REKAP_BON);
                break;
            case R.id.btn_tdp:
                isSelectPhoto = "tdp";
                startActivityForResult(intent, TAKE_PDF_TDP);
                break;
            case R.id.btn_npwp_badan:
                isSelectPhoto = "npwpbadan";
                openCamera(TAKE_PICTURE_NPWP_BADAN);
                break;
            case R.id.btn_rekening_koran:
                isSelectPhoto = "rekeningkoran";
                openCamera(TAKE_PICTURE_REKENING_KORAN);
                break;


            case R.id.iv_ktpnasabahpasangan:
                if(val_ktpnasabahpasangan==0){
                    isSelectPhoto = "ktpnasabahpasangan";
                    openCamera(TAKE_PICTURE_KTPNASABAHPASANGAN);
                }
                else{
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_ktpnasabahpasangan.getDrawable()).getSourceBitmap());
                }

                break;
            case R.id.iv_kartukeluarga:
                if(val_kartukeluarga==0){
                    isSelectPhoto = "kartukeluarga";
                    openCamera(TAKE_PICTURE_KARTUKELUARGA);
                }
                else{
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_kartukeluarga.getDrawable()).getSourceBitmap());
                }

                break;
            case R.id.iv_suratnikah:
                if (val_suratnikah == 0) {
                    isSelectPhoto = "suratnikah";
                    startActivityForResult(intent,TAKE_PDF_SURATNIKAH);
                } else {
                    setLoadPdf(val_suratnikah);
                }
                break;
            case R.id.iv_pasphoto:
                if(val_pasphoto==0){
                    isSelectPhoto = "pasphoto";
                    openCamera(TAKE_PICTURE_PASPHOTO);
                }
                else{
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_pasphoto.getDrawable()).getSourceBitmap());
                }

                break;
            case R.id.iv_npwp:
                if(val_npwp==0){
                    isSelectPhoto = "npwp";
                    openCamera(TAKE_PICTURE_NPWP);
                }
                else{
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_npwp.getDrawable()).getSourceBitmap());
                }

                break;
            case R.id.iv_formuliraplikasi:
                if(val_formuliraplikasi==0){
                    isSelectPhoto = "formuliraplikasi";
                    openCamera(TAKE_PICTURE_FORMULIRAPLIKASI);
                }
                else{
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_formuliraplikasi.getDrawable()).getSourceBitmap());
                }

                break;
            case R.id.iv_keterangan_usaha:
                if (val_keterangan_usaha == 0) {
                    isSelectPhoto = "keteranganusaha";
                    startActivityForResult(intent, TAKE_PDF_KETERANGAN_USAHA);
                } else {
                    setLoadPdf(val_keterangan_usaha);
                }
                break;
            case R.id.iv_spp_rab:
                if (val_spp_rab == 0) {
                    isSelectPhoto = "spprab";
                    startActivityForResult(intent, TAKE_PDF_SKK_RAB);
                } else {
                    setLoadPdf(val_spp_rab);
                }
                break;
            case R.id.iv_siup:
                if(val_siup ==0){
                    isSelectPhoto = "siup";
                    openCamera(TAKE_PICTURE_SIUP);
                }
                else{
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_siup.getDrawable()).getSourceBitmap());
                }

                break;
            case R.id.iv_laporan_keuangan:
                if(val_laporan_keuangan ==0){
                    isSelectPhoto = "laporankeuangan";
                    openCamera(TAKE_PICTURE_LAPORAN_KEUANGAN);
                }
                else{
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_laporan_keuangan.getDrawable()).getSourceBitmap());
                }

                break;
            case R.id.iv_rekap_bon:
                if (val_rekap_bon == 0) {
                    isSelectPhoto = "rekapbon";
                    startActivityForResult(intent, TAKE_PDF_REKAP_BON);
                } else {
                    setLoadPdf(val_rekap_bon);
                }
                break;
            case R.id.iv_tdp:
                if (val_tdp == 0) {
                    isSelectPhoto = "tdp";
                    startActivityForResult(intent, TAKE_PDF_TDP);
                } else {
                    setLoadPdf(val_tdp);
                }
                break;
            case R.id.iv_npwp_badan:
                if(val_npwp_badan ==0){
                    isSelectPhoto = "npwpbadan";
                    openCamera(TAKE_PICTURE_NPWP_BADAN);
                }
                else{
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_npwp_badan.getDrawable()).getSourceBitmap());
                }
            case R.id.iv_rekening_koran:
                if(val_rekening_koran ==0){
                    isSelectPhoto = "rekeningkoran";
                    openCamera(TAKE_PICTURE_REKENING_KORAN);
                }
                else{
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_rekening_koran.getDrawable()).getSourceBitmap());
                }
        }
    }

    public void setLoadPdf(int idFoto) {
        String url_pdf = UriApi.Baseurl.URL + UriApi.hotprospek.urlPdf + idFoto;
        Uri external = Uri.parse(url_pdf);
        Intent intentPdf;
        intentPdf = new Intent(Intent.ACTION_VIEW);
        intentPdf.setDataAndType(external, "application/pdf");
        try {
            startActivity(intentPdf);
        } catch (ActivityNotFoundException e) {
            // No application to view, ask to download one
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("No Application Found");
            builder.setMessage("Download one from Android Market?");
            builder.setPositiveButton("Yes, Please",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent marketIntent = new Intent(Intent.ACTION_VIEW);
                            marketIntent
                                    .setData(Uri
                                            .parse("market://details?id=com.adobe.reader"));
                            startActivity(marketIntent);
                        }
                    });
            builder.setNegativeButton("No, Thanks", null);
            builder.create().show();
        }
    }

    public static String getFilePathFromURI(Context context, Uri contentUri, String filename) {
        //copy file and send new file path
        String name = getFileName(contentUri);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }
        if (!TextUtils.isEmpty(name)) {
            File copyFile = new File(wallpaperDirectory + File.separator + name);
            // create folder if not exists

            copy(context, contentUri, copyFile);
            return copyFile.getAbsolutePath();
        }
        return null;
    }

    public static String getFileName(Uri uri) {
        if (uri == null) return null;
        String fileName = null;
        String path = uri.getPath();
        int cut = path.lastIndexOf('/');
        if (cut != -1) {
            fileName = path.substring(cut + 1);
        }
        return fileName;
    }

    public static void copy(Context context, Uri srcUri, File dstFile) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(srcUri);
            if (inputStream == null) return;
            OutputStream outputStream = new FileOutputStream(dstFile);
            copystream(inputStream, outputStream);
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int copystream(InputStream input, OutputStream output) throws Exception, IOException {
        byte[] buffer = new byte[BUFFER_SIZE];

        BufferedInputStream in = new BufferedInputStream(input, BUFFER_SIZE);
        BufferedOutputStream out = new BufferedOutputStream(output, BUFFER_SIZE);
        int count = 0, n = 0;
        try {
            while ((n = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
                out.write(buffer, 0, n);
                count += n;
            }
            out.flush();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
            try {
                in.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
        }
        return count;
    }

    private void autoCheckboxOnclick(View viewInduk){

        if(viewInduk instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) viewInduk;
            for (int i = 0, count = vg.getChildCount(); i < count; ++i) {
                View view = vg.getChildAt(i);
                autoCheckboxOnclick(view);
                if (view instanceof LinearLayout) {
                    ((LinearLayout) view).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(KonsumerKprWiraswastaKelengkapanDokumenActivity.this, "Tombol akan berubah hijau setelah upload dokumen", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                if (view instanceof CheckBox) {
                    ((CheckBox) view).setClickable(false);
                }
            }
        }

    }

    private void otherViewModification(){



    }

    @Override
    public void onBackPressed() {

        if(approved.equalsIgnoreCase("no")){
            CustomDialog.DialogBackpressSaved(KonsumerKprWiraswastaKelengkapanDokumenActivity.this);
        }
        else{
            super.onBackPressed();
        }
    }
}