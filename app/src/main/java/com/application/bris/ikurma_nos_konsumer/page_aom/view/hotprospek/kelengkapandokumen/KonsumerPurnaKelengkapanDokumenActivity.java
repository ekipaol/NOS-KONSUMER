package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.kelengkapandokumen;


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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.KonsumerKMGInputKelengkapanDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryKelengkapanDokumen;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.DataGlobalHotprospekPojo;
import com.application.bris.ikurma_nos_konsumer.model.hotprospek.KonsumerKmgKelengkapanDokumen;
import com.application.bris.ikurma_nos_konsumer.page_aom.adapter.hotprospek.AgunanKelengkapanDokumenAdapter;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.ListAgunanKelengkapanDokumen;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
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
import android.util.Log;

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

public class KonsumerPurnaKelengkapanDokumenActivity extends AppCompatActivity implements ConfirmListener, View.OnClickListener {

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
    @BindView(R.id.cb_sk_pensiun)
    CheckBox cb_sk_pensiun;
    @BindView(R.id.cb_karip)
    CheckBox cb_karip;
    @BindView(R.id.cb_bukti_pensiun)
    CheckBox cb_bukti_pensiun;
    @BindView(R.id.cb_sk_janda_duda)
    CheckBox cb_sk_janda_duda;
    @BindView(R.id.cb_spk)
    CheckBox cb_spk;
    @BindView(R.id.cb_surat_pernyataan_rpc)
    CheckBox cb_surat_pernyataan_rpc;

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
    @BindView(R.id.iv_skpensiun)
    ImageView iv_skpensiun;
    @BindView(R.id.iv_karip)
    ImageView iv_karip;
    @BindView(R.id.iv_bukti_pensiun)
    ImageView iv_bukti_pensiun;
    @BindView(R.id.iv_janda_duda)
    ImageView iv_janda_duda;
    @BindView(R.id.iv_spk)
    ImageView iv_spk;
    @BindView(R.id.iv_surat_pernyataan_rpc)
    ImageView iv_surat_pernyataan_rpc;


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
    @BindView(R.id.btn_skpensiun)
    ImageView btn_skpensiun;
    @BindView(R.id.btn_karip)
    ImageView btn_karip;
    @BindView(R.id.btn_bukti_pensiun)
    ImageView btn_bukti_pensiun;
    @BindView(R.id.btn_janda_duda)
    ImageView btn_janda_duda;
    @BindView(R.id.btn_spk)
    ImageView btn_spk;
    @BindView(R.id.btn_surat_pernyataan_rpc)
    ImageView btn_surat_pernyataan_rpc;

    @BindView(R.id.ll_pernyataan_nasabah)
    LinearLayout ll_pernyataan_nasabah;
    @BindView(R.id.ll_janda_duda)
    LinearLayout ll_janda_duda;

    @BindView(R.id.v_janda_duda)
    View v_janda_duda;
    @BindView(R.id.v_pernyataan_nasabah)
    View v_pernyataan_nasabah;


    @BindView(R.id.tv_spk)
    TextView tv_spk;


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
    private KonsumerKmgKelengkapanDokumen data;

    private boolean val_cb_ktpnasabahpasangan = false;
    private boolean val_cb_kartukeluarga = false;
    private boolean val_cb_suratnikah = false;
    private boolean val_cb_pasphoto = false;
    private boolean val_cb_npwp = false;
    private boolean val_cb_formuliraplikasi = false;
    private boolean val_cb_sk_pensiun = false;
    private boolean val_cb_karip = false;
    private boolean val_cb_bukti_penerimaan_pensiun = false;
    private boolean val_cb_sk_janda_duda = false;
    private boolean val_cb_spk = false;
    private boolean val_cb_surat_pernyataan_nasabah_rpc = false;

    private int val_ktpnasabahpasangan = 0;
    private int val_kartukeluarga = 0;
    private int val_suratnikah = 0;
    private int val_pasphoto = 0;
    private int val_npwp = 0;
    private int val_formuliraplikasi = 0;
    private int val_sk_pensiun = 0;
    private int val_karip = 0;
    private int val_bukti_penerimaan_pensiun = 0;
    private int val_sk_janda_duda = 0;
    private int val_spk = 0;
    private int val_surat_pernyataan_nasabah_rpc = 0;

    private final int TAKE_PICTURE_KTPNASABAHPASANGAN = 1;
    private final int TAKE_PICTURE_KARTUKELUARGA = 2;
    private final int TAKE_PDF_SURATNIKAH = 3;
    private final int TAKE_PICTURE_PASPHOTO = 4;
    private final int TAKE_PICTURE_NPWP = 5;
    private final int TAKE_PICTURE_FORMULIRAPLIKASI = 6;
    private final int TAKE_PICTURE_KARIP = 7;
    private final int TAKE_PICTURE_BUKTI_PENERIMAAN_PENSIUN = 8;
    private final int TAKE_PICTURE_SK_JANDA_DUDA = 9;
    private final int TAKE_PICTURE_SPK = 10;
    private final int TAKE_PICTURE_SURAT_PERNYATAAN_NASABAH_RPC = 11;
    private final int TAKE_PICTURE_SK_PENSIUN = 12;

    boolean wajibSpk=false;
    boolean wajibJandaDuda=false;
    boolean wajibSuratRpc=false;

    private String isSelectPhoto = "";

    Double rpcGlobal;

    private Bitmap loadedPicture;

    private Bitmap bitmap_ktpnasabahpasangan, bitmap_kartukeluarga, bitmap_pasphoto,
            bitmap_npwp, bitmap_sk_pensiun, bitmap_formuliraplikasi, bitmap_kartu_identitas_pensiun,
            bitmap_bukti_penerimaan_pensiun, bitmap_sk_janda_duda, bitmap_spk, bitmap_surat_pernyataan_nasabah_rpc;

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
        setContentView(R.layout.konsumer_purna_hotprospek_kelengkapandokumen);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        idTujuan = getIntent().getIntExtra("idTujuan", 0);
        kodeProduct = getIntent().getStringExtra("kodeProduct");
        approved = getIntent().getStringExtra("approved");
        plafond = getIntent().getIntExtra("plafond", 0);
        realm = Realm.getDefaultInstance();

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, getString(R.string.submenu_hotprospek_kelengkapandokumen));
        requestMultiplePermissions();
        loadKelengkapanDokumen();
        //membuat semua checkbox mengeluarkan toast ketika di pencet, dan disable click
        autoCheckboxOnclick(sv_parent_kelengkapan_dokumen_konsumer);
        onClickCheckbox();
        otherViewModification();

        //disable uploadbutton if approved
        if (approved.equalsIgnoreCase("yes")) {
            btn_formuliraplikasi.setVisibility(View.GONE);
            btn_ktpnasabahpasangan.setVisibility(View.GONE);
            btn_kartukeluarga.setVisibility(View.GONE);
            btn_suratnikah.setVisibility(View.GONE);
            btn_pasphoto.setVisibility(View.GONE);
            btn_npwp.setVisibility(View.GONE);
            btn_skpensiun.setVisibility(View.GONE);
            btn_karip.setVisibility(View.GONE);
            btn_bukti_pensiun.setVisibility(View.GONE);
            btn_janda_duda.setVisibility(View.GONE);
            btn_spk.setVisibility(View.GONE);
            btn_surat_pernyataan_rpc.setVisibility(View.GONE);
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

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void setOnClick() {
        iv_ktpnasabahpasangan.setOnClickListener(this);
        iv_kartukeluarga.setOnClickListener(this);
        iv_suratnikah.setOnClickListener(this);
        iv_pasphoto.setOnClickListener(this);
        iv_npwp.setOnClickListener(this);
        iv_formuliraplikasi.setOnClickListener(this);
        iv_skpensiun.setOnClickListener(this);
        iv_karip.setOnClickListener(this);
        iv_bukti_pensiun.setOnClickListener(this);
        iv_janda_duda.setOnClickListener(this);
        iv_spk.setOnClickListener(this);
        iv_surat_pernyataan_rpc.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        btn_ktpnasabahpasangan.setOnClickListener(this);
        btn_kartukeluarga.setOnClickListener(this);
        btn_suratnikah.setOnClickListener(this);
        btn_pasphoto.setOnClickListener(this);
        btn_npwp.setOnClickListener(this);
        btn_formuliraplikasi.setOnClickListener(this);
        btn_skpensiun.setOnClickListener(this);
        btn_karip.setOnClickListener(this);
        btn_bukti_pensiun.setOnClickListener(this);
        btn_janda_duda.setOnClickListener(this);
        btn_spk.setOnClickListener(this);
        btn_surat_pernyataan_rpc.setOnClickListener(this);

    }

    private void onClickCheckbox() {

        cb_ktpnasabahpasangan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    val_cb_ktpnasabahpasangan = true;
                } else {
                    val_cb_ktpnasabahpasangan = false;
                }
            }
        });

        cb_kartukeluarga.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    val_cb_kartukeluarga = true;
                } else {
                    val_cb_kartukeluarga = false;
                }
            }
        });

        cb_suratnikah.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    val_cb_suratnikah = true;
                } else {
                    val_cb_suratnikah = false;
                }
            }
        });

        cb_pasphoto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    val_cb_pasphoto = true;
                } else {
                    val_cb_pasphoto = false;
                }
            }
        });

        cb_npwp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    val_cb_npwp = true;
                } else {
                    val_cb_npwp = false;
                }
            }
        });

        cb_formuliraplikasi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    val_cb_formuliraplikasi = true;
                } else {
                    val_cb_formuliraplikasi = false;
                }
            }
        });


        cb_sk_pensiun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    val_cb_sk_pensiun = true;
                } else {
                    val_cb_sk_pensiun = false;
                }
            }
        });


        cb_karip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    val_cb_karip = true;
                } else {
                    val_cb_karip = false;
                }
            }
        });

        cb_bukti_pensiun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    val_cb_bukti_penerimaan_pensiun = true;
                } else {
                    val_cb_bukti_penerimaan_pensiun = false;
                }
            }
        });

        cb_sk_janda_duda.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    val_cb_sk_janda_duda = true;
                } else {
                    val_cb_sk_janda_duda = false;
                }
            }
        });

        cb_spk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    val_cb_spk = true;
                } else {
                    val_cb_spk = false;
                }
            }
        });


        cb_surat_pernyataan_rpc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    val_cb_surat_pernyataan_nasabah_rpc = true;
                } else {
                    val_cb_surat_pernyataan_nasabah_rpc = false;
                }
            }
        });


    }

    private void setUncheckboxDefault() {
        cb_ktpnasabahpasangan.setChecked(false);
        cb_kartukeluarga.setChecked(false);
        cb_suratnikah.setChecked(false);
        cb_pasphoto.setChecked(false);
        cb_npwp.setChecked(false);
        cb_formuliraplikasi.setChecked(false);
        cb_sk_pensiun.setChecked(false);
        cb_karip.setChecked(false);
        cb_bukti_pensiun.setChecked(false);
        cb_sk_janda_duda.setChecked(false);
        cb_spk.setChecked(false);
        cb_surat_pernyataan_rpc.setChecked(false);


    }

    private void loadKelengkapanDokumen() {
        inquiryKelengkapanDokumen req = new inquiryKelengkapanDokumen(idAplikasi);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryKelengkapanDokumenKonsumer(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    setUncheckboxDefault();
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            Gson gson = new Gson();
                            Type typeAgunan = new TypeToken<List<ListAgunanKelengkapanDokumen>>() {
                            }.getType();

                            dataString = response.body().getData().get("kelDokumen").toString();

                            if (!dataString.equalsIgnoreCase("{}")) {
                                data = gson.fromJson(dataString, KonsumerKmgKelengkapanDokumen.class);
//                                dataAgunan = gson.fromJson(response.body().getData().get("kelDokumen").getAsJsonObject().get("ID_DOKUMEN_AGUNAN").toString(), typeAgunan);


                                setCheckbox();
                                setValData();

                                if (dataAgunan!=null&&dataAgunan.size() > 0) {
                                    ll_emptydata.setVisibility(View.GONE);
                                    initializeAgunan();
                                } else {
//                                    ll_emptydata.setVisibility(View.VISIBLE);
                                }
                            } else {
                                setCheckBoxRealm();
                            }
                        }
                    } else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void setCheckbox() {

        if (data != null) {
            if (data.getFC_KTP())
                cb_ktpnasabahpasangan.setChecked(true);

            if (data.getFC_KK())
                cb_kartukeluarga.setChecked(true);

            if (data.getFC_SURAT_NIKAH())
                cb_suratnikah.setChecked(true);

            if (data.getPAS_PHOTO())
                cb_pasphoto.setChecked(true);

//            if (data.getFC_NPWP_PRIBADI())
//                cb_npwp.setChecked(true);

            if (data.getAPLIKASI_PERMOHONAN())
                cb_formuliraplikasi.setChecked(true);

            if (data.getSuratPensiun())
                cb_sk_pensiun.setChecked(true);

            if (data.getIdentitasPensiun())
                cb_karip.setChecked(true);

            if (data. getSLIP_GAJI())
                cb_bukti_pensiun.setChecked(true);

            if (data.getSuratJandaDuda())
                cb_sk_janda_duda.setChecked(true);

//            if (data.getSuratKesehatan())
//                cb_spk.setChecked(true);

            if (data.getSuratNasabahRpc())
                cb_surat_pernyataan_rpc.setChecked(true);

        }


//            et_nomorsk.setText(data.getnOSKU());

        //photo
        bitmap_ktpnasabahpasangan = setLoadImage(iv_ktpnasabahpasangan, data.getID_DOKUMEN_KTP());
        bitmap_kartukeluarga = setLoadImage(iv_kartukeluarga, data.getID_DOKUMEN_KK());
        bitmap_pasphoto = setLoadImage(iv_pasphoto, data.getID_DOKUMEN_PAS_PHOTO());
//        bitmap_npwp = setLoadImage(iv_npwp, data.getID_DOKUMEN_NPWP_PRIBADI());
        bitmap_formuliraplikasi = setLoadImage(iv_formuliraplikasi, data.getID_DOKUMEN_APLIKASI());



        //pdf
        if (data.getID_DOKUMEN_SURAT_NIKAH() != 0) {
            iv_suratnikah.setImageDrawable(ContextCompat.getDrawable(KonsumerPurnaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
        }

        if (data.getIdPhotosuratPensiun() != 0) {
            iv_skpensiun.setImageDrawable(ContextCompat.getDrawable(KonsumerPurnaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
        }
        if (data.getIdPhotoidentitasPensiun() != 0) {
            iv_karip.setImageDrawable(ContextCompat.getDrawable(KonsumerPurnaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
        }
        if (data.getID_SLIP_GAJI() != 0) {
            iv_bukti_pensiun.setImageDrawable(ContextCompat.getDrawable(KonsumerPurnaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
        }
        if (data.getIdPhotosuratJandaDuda() != 0) {
            iv_janda_duda.setImageDrawable(ContextCompat.getDrawable(KonsumerPurnaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
        }
//        if (data.getIdPhotosuratKesehatan() != 0) {
//            iv_spk.setImageDrawable(ContextCompat.getDrawable(KonsumerPurnaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
//        }
        if (data.getIdPhotosuratNasabahRpc() != 0) {
            iv_surat_pernyataan_rpc.setImageDrawable(ContextCompat.getDrawable(KonsumerPurnaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
        }


    }
    

    //method ini sudah include set val datanya juga, jadi cukup panggil ini, tidak usah buat valdata khusus realm

    //seluruh blok kode harus didalam trycatch, karena kalau ada yang null ,kode bawahnya gak akan jalan kalau gak trycatch
    //dan trycatch-nya jangan digabung jadi satu, harus dipisah pisah tiap if
    //DICOMMENT DLU YAK
    
    private void setCheckBoxRealm() {

        KonsumerKmgKelengkapanDokumen realmKelengkapan = realm.where(KonsumerKmgKelengkapanDokumen.class).equalTo("ID_APLIKASI", idAplikasi).findFirst();


        if (realmKelengkapan != null) {
            try {
                if (realmKelengkapan.getID_DOKUMEN_KTP() != null && realmKelengkapan.getID_DOKUMEN_KTP() != 0) {
                    cb_ktpnasabahpasangan.setChecked(true);
                    bitmap_ktpnasabahpasangan = setLoadImage(iv_ktpnasabahpasangan, realmKelengkapan.getID_DOKUMEN_KTP());
                    val_ktpnasabahpasangan = realmKelengkapan.getID_DOKUMEN_KTP();
                }
            } catch (NullPointerException e) {
                Log.d("ada error di ", "dokumen ktp");
            }

            try {
                if (realmKelengkapan.getID_DOKUMEN_KK() != null && realmKelengkapan.getID_DOKUMEN_KK() != 0) {
                    cb_kartukeluarga.setChecked(true);
                    bitmap_kartukeluarga = setLoadImage(iv_kartukeluarga, realmKelengkapan.getID_DOKUMEN_KK());
                    val_kartukeluarga = realmKelengkapan.getID_DOKUMEN_KK();
                }
            } catch (NullPointerException e) {
                Log.d("ada error di ", "dokumen kartu keluarga");
            }

            try {
                if (realmKelengkapan.getID_DOKUMEN_SURAT_NIKAH() != null && realmKelengkapan.getID_DOKUMEN_SURAT_NIKAH() != 0) {
                    cb_suratnikah.setChecked(true);
                    iv_suratnikah.setImageDrawable(ContextCompat.getDrawable(KonsumerPurnaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
                    val_suratnikah = realmKelengkapan.getID_DOKUMEN_SURAT_NIKAH();
                }

            } catch (NullPointerException e) {
                Log.d("ada error di ", "dokumen surat nikah");
            }

            try {
                if (realmKelengkapan.getID_DOKUMEN_PAS_PHOTO() != null && realmKelengkapan.getID_DOKUMEN_PAS_PHOTO() != 0) {
                    cb_pasphoto.setChecked(true);
                    bitmap_pasphoto = setLoadImage(iv_pasphoto, realmKelengkapan.getID_DOKUMEN_PAS_PHOTO());
                    val_pasphoto = realmKelengkapan.getID_DOKUMEN_PAS_PHOTO();
                }
            } catch (NullPointerException e) {
                Log.d("ada error di ", "dokumen pas foto");
            }


//            try {
//                if (realmKelengkapan.getID_DOKUMEN_NPWP_PRIBADI() != null && realmKelengkapan.getID_DOKUMEN_NPWP_PRIBADI() != 0) {
//                    cb_npwp.setChecked(true);
//                    bitmap_npwp = setLoadImage(iv_npwp, realmKelengkapan.getID_DOKUMEN_NPWP_PRIBADI());
//                    val_npwp = realmKelengkapan.getID_DOKUMEN_NPWP_PRIBADI();
//                }
//            } catch (NullPointerException e) {
//                Log.d("ada error di ", "dokumen npwp");
//            }

            try {
                if (realmKelengkapan.getID_DOKUMEN_APLIKASI() != null && realmKelengkapan.getID_DOKUMEN_APLIKASI() != 0) {
                    cb_formuliraplikasi.setChecked(true);
                    bitmap_formuliraplikasi = setLoadImage(iv_formuliraplikasi, realmKelengkapan.getID_DOKUMEN_APLIKASI());
                    val_formuliraplikasi = realmKelengkapan.getID_DOKUMEN_APLIKASI();
                }
            } catch (NullPointerException e) {
                Log.d("ada error di ", "dokumen formulir aplikasi");
            }

            try {
                if (realmKelengkapan.getIdPhotosuratPensiun() != null && realmKelengkapan.getIdPhotosuratPensiun() != 0) {
                     cb_sk_pensiun.setChecked(true);
                    iv_skpensiun.setImageDrawable(ContextCompat.getDrawable(KonsumerPurnaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
                     val_sk_pensiun = realmKelengkapan.getIdPhotosuratPensiun();
                }
            } catch (NullPointerException e) {
                Log.d("ada error di ", "dokumen sk pensiun");
            }

            try {
                if (realmKelengkapan.getIdPhotoidentitasPensiun() != null && realmKelengkapan.getIdPhotoidentitasPensiun() != 0) {
                     cb_karip.setChecked(true);
                    iv_karip.setImageDrawable(ContextCompat.getDrawable(KonsumerPurnaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
                     val_karip = realmKelengkapan.getIdPhotoidentitasPensiun();
                }
            } catch (NullPointerException e) {
                Log.d("ada error di ", "dokumen karip");
            }

            try {
                if (realmKelengkapan. getID_SLIP_GAJI() != null && realmKelengkapan.getID_SLIP_GAJI() != 0) {
                     cb_bukti_pensiun.setChecked(true);
                    iv_bukti_pensiun.setImageDrawable(ContextCompat.getDrawable(KonsumerPurnaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
                     val_bukti_penerimaan_pensiun = realmKelengkapan.getID_SLIP_GAJI();
                }
            } catch (NullPointerException e) {
                Log.d("ada error di ", "dokumen bukti pensiun");
            }

            try {
                if (realmKelengkapan.getIdPhotosuratJandaDuda() != null && realmKelengkapan.getIdPhotosuratJandaDuda() != 0) {
                     cb_sk_janda_duda.setChecked(true);
                    iv_janda_duda.setImageDrawable(ContextCompat.getDrawable(KonsumerPurnaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
                     val_sk_janda_duda = realmKelengkapan.getIdPhotosuratJandaDuda();
                }
            } catch (NullPointerException e) {
                Log.d("ada error di ", "dokumen sk janda duda");
            }

//            try {
//                if (realmKelengkapan.getIdPhotosuratKesehatan() != null && realmKelengkapan.getIdPhotosuratKesehatan() != 0) {
//                     cb_spk.setChecked(true);
//                    iv_spk.setImageDrawable(ContextCompat.getDrawable(KonsumerPurnaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
//                     val_spk = realmKelengkapan.getIdPhotosuratKesehatan();
//                }
//            } catch (NullPointerException e) {
//                Log.d("ada error di ", "dokumen spk");
//            }

            try {
                if (realmKelengkapan.getIdPhotosuratNasabahRpc() != null && realmKelengkapan.getIdPhotosuratNasabahRpc() != 0) {
                     cb_surat_pernyataan_rpc.setChecked(true);
                    iv_surat_pernyataan_rpc.setImageDrawable(ContextCompat.getDrawable(KonsumerPurnaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
                     val_surat_pernyataan_nasabah_rpc = realmKelengkapan.getIdPhotosuratNasabahRpc();
                }
            } catch (NullPointerException e) {
                Log.d("ada error di ", "dokumen surat pernyataan nasabah");
            }

        }
    }

    private void setValData() {


        if (data != null) {
            val_cb_ktpnasabahpasangan = data.getFC_KTP();
            val_cb_kartukeluarga = data.getFC_KK();
            val_cb_suratnikah = data.getFC_SURAT_NIKAH();
            val_cb_pasphoto = data.getPAS_PHOTO();
//            val_cb_npwp = data.getFC_NPWP_PRIBADI();
            val_cb_formuliraplikasi = data.getAPLIKASI_PERMOHONAN();
             val_cb_sk_pensiun = data.getSuratPensiun();
             val_cb_karip = data.getIdentitasPensiun();
             val_cb_bukti_penerimaan_pensiun = data.getSLIP_GAJI();
             val_cb_sk_janda_duda = data.getSuratJandaDuda();
//             val_cb_spk = data.getSuratKesehatan();
             val_cb_surat_pernyataan_nasabah_rpc = data.getSuratNasabahRpc();
             

            val_ktpnasabahpasangan = data.getID_DOKUMEN_KTP();
            val_kartukeluarga = data.getID_DOKUMEN_KK();
            val_suratnikah = data.getID_DOKUMEN_SURAT_NIKAH();
            val_pasphoto = data.getID_DOKUMEN_PAS_PHOTO();
//            val_npwp = data.getID_DOKUMEN_NPWP_PRIBADI();
            val_formuliraplikasi = data.getID_DOKUMEN_APLIKASI();
             val_sk_pensiun = data.getIdPhotosuratPensiun();
             val_karip = data.getIdPhotoidentitasPensiun();
             val_bukti_penerimaan_pensiun = data. getID_SLIP_GAJI();
             val_sk_janda_duda = data.getIdPhotosuratJandaDuda();
//             val_spk = data.getIdPhotosuratKesehatan();
             
             val_surat_pernyataan_nasabah_rpc = data.getIdPhotosuratNasabahRpc();
        }
    }


    public void initializeAgunan() {
        adapterAgunan = new AgunanKelengkapanDokumenAdapter(KonsumerPurnaKelengkapanDokumenActivity.this, dataAgunan);
        layoutAgunan = new LinearLayoutManager(KonsumerPurnaKelengkapanDokumenActivity.this);
        rv_listagunan.setLayoutManager(layoutAgunan);
        rv_listagunan.setAdapter(adapterAgunan);

        if (dataAgunan == null) {
            ll_emptydata.setVisibility(View.VISIBLE);
        }
    }

    private boolean validateForm() {
        if ((val_cb_ktpnasabahpasangan && val_ktpnasabahpasangan == 0) || (!val_cb_ktpnasabahpasangan && val_ktpnasabahpasangan != 0) || (!val_cb_ktpnasabahpasangan && val_ktpnasabahpasangan == 0)) {
            AppUtil.notifwarning(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Foto KTP Nasabah dan/atau Pasangan");
            return false;
        }
        if ((val_cb_kartukeluarga && val_kartukeluarga == 0) || (!val_cb_kartukeluarga && val_kartukeluarga != 0) || (!val_cb_kartukeluarga && val_kartukeluarga == 0)) {
            AppUtil.notifwarning(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Foto KK");
            return false;
        }
        if ((val_cb_suratnikah && val_suratnikah == 0) || (!val_cb_suratnikah && val_suratnikah != 0) || (!val_cb_suratnikah && val_suratnikah == 0)) {
            AppUtil.notifwarning(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload File Surat Nikah/Akta Cerai/Surat Ket. Status Perkawinan Lainnya");
            return false;
        }
        if ((val_cb_pasphoto && val_pasphoto == 0) || (!val_cb_pasphoto && val_pasphoto != 0) || (!val_cb_pasphoto && val_pasphoto == 0)) {
            AppUtil.notifwarning(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Foto Pas Photo");
            return false;
        }
//

        if ((val_cb_formuliraplikasi && val_formuliraplikasi == 0) || (!val_cb_formuliraplikasi && val_formuliraplikasi != 0) || (!val_cb_formuliraplikasi && val_formuliraplikasi == 0)) {
            AppUtil.notifwarning(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Foto Formulir Aplikasi Pembiayaan");
            return false;
        }
        if (( val_cb_sk_pensiun &&  val_sk_pensiun == 0) || (! val_cb_sk_pensiun &&  val_sk_pensiun != 0) || (! val_cb_sk_pensiun &&  val_sk_pensiun == 0)) {
            AppUtil.notifwarning(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Dokumen SK Pensiun");
            return false;
        }
//        if (( val_cb_karip &&  val_karip == 0) || (! val_cb_karip &&  val_karip != 0) || (! val_cb_karip &&  val_karip == 0)) {
//            AppUtil.notifwarning(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Foto Kartu Identitas Pensiun");
//            return false;
//        }
        if (( val_cb_bukti_penerimaan_pensiun &&  val_bukti_penerimaan_pensiun == 0) || (! val_cb_bukti_penerimaan_pensiun &&  val_bukti_penerimaan_pensiun != 0) || (! val_cb_bukti_penerimaan_pensiun &&  val_bukti_penerimaan_pensiun == 0)) {
            AppUtil.notifwarning(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Dokumen Bukti Penerimaan Pensiun");
            return false;
        }

        //validasi jika janda duda
        if(wajibJandaDuda){
            if (( val_cb_sk_janda_duda &&  val_sk_janda_duda == 0) || (! val_cb_sk_janda_duda &&  val_sk_janda_duda != 0) || (! val_cb_sk_janda_duda &&  val_sk_janda_duda == 0)) {
                AppUtil.notifwarning(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Dokumen SK Janda/Duda");
                return false;
            }
        }



        //validasi dokumen keterangan rpc
        if(wajibSuratRpc){
            if (( val_cb_surat_pernyataan_nasabah_rpc &&  val_surat_pernyataan_nasabah_rpc == 0) || (! val_cb_surat_pernyataan_nasabah_rpc &&  val_surat_pernyataan_nasabah_rpc != 0) || (! val_cb_surat_pernyataan_nasabah_rpc &&  val_surat_pernyataan_nasabah_rpc == 0)) {
                AppUtil.notifwarning(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Dokumen Surat Pernyataan RPC (RPC diatas 70%");
                return false;
            }
        }
        //validasi spk
//        if(wajibSpk){
//            if (( val_cb_spk &&  val_spk == 0) || (! val_cb_spk &&  val_spk != 0) || (! val_cb_spk &&  val_spk == 0)) {
//                AppUtil.notifwarning(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), "Silahkan Upload Dokumen SPK");
//                return false;
//            }
//        }







        return true;
    }

    private void sendData() {
        loading.setVisibility(View.VISIBLE);
        KonsumerKMGInputKelengkapanDokumen req =new KonsumerKMGInputKelengkapanDokumen();

        //true false masing masing field
        req.setIdAplikasi(idAplikasi);
        req.setAplikasiPermohonan(val_cb_formuliraplikasi);
        req.setFcKtp(val_cb_ktpnasabahpasangan);
        req.setFcKK(val_cb_kartukeluarga);
        req.setFcSuratNikah(val_cb_suratnikah);
        req.setFcPasPhoto(val_cb_pasphoto);
        req.setSuratPensiun(val_cb_sk_pensiun);
        req.setIdentitasPensiun(val_cb_karip);
        req.setSlipGaji(val_cb_bukti_penerimaan_pensiun);
        req.setSuratJandaDuda(val_cb_sk_janda_duda);
        req.setSuratKesehatan(val_cb_sk_janda_duda);
        req.setSuratNasabahRpc(val_cb_surat_pernyataan_nasabah_rpc);
        req.setFcNpwpPribadi(val_cb_npwp);

        //ini dipantek 0, dunno why
        req.setIdPhotolainnya(0);


        //id dokumen/foto
        req.setIdPhotoaplikasiPermohonan(val_formuliraplikasi);
        req.setIdPhotofcKtp(val_ktpnasabahpasangan);
        req.setIdPhotofcKK(val_kartukeluarga);
        req.setIdPhotofcSuratNikah(val_suratnikah);
        req.setIdPhotofcPasPhoto(val_pasphoto);
        req.setIdPhotosuratPensiun(val_sk_pensiun);
        req.setIdPhotoslipGaji(val_bukti_penerimaan_pensiun);
        req.setIdPhotofcNpwpPribadi(val_npwp);
      
        req.setIdPhotosuratJandaDuda(val_sk_janda_duda);
        req.setIdPhotosuratKesehatan(val_spk);
        req.setIdPhotosuratNasabahRpc(val_surat_pernyataan_nasabah_rpc);

        if(val_spk==0){
            req.setSpkMedical(0l);
        }
        else{
            req.setSpkMedical(1l);
        }

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendKelengkapanDokumenKonsumer(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            CustomDialog.DialogSuccess(KonsumerPurnaKelengkapanDokumenActivity.this, "Success!", "Input Data Kelengkapan Dokumen Sukses", KonsumerPurnaKelengkapanDokumenActivity.this);
                        } else {
                            AppUtil.notiferror(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }


    private void openCamera(int cameraCode) {

        checkCameraPermission(cameraCode);

        //pantekan buka gallery, bukan kamera, karena emulatpr gabisa pake kamera
//        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
//        it.setType("image/*");
//        startActivityForResult(Intent.createChooser(it, "Select File"), cameraCode);
//        Toast.makeText(this, "ambil foto dari galeri untuk testing", Toast.LENGTH_SHORT).show();


    }

    private void directOpenCamera(int cameraCode) {
        Uri outputFileUri = getCaptureImageOutputUri();
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(captureIntent, cameraCode);
    }

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static int CAMERA_CODE_FORE_PERMISSION = 0;

    public void checkCameraPermission(int cameraCode) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            CAMERA_CODE_FORE_PERMISSION = cameraCode;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);
        } else {
            directOpenCamera(cameraCode);
        }
    }

    private void requestMultiplePermissions() {
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

        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                AppUtil.showToastLong(this, "Camera Permission Granted");
                directOpenCamera(CAMERA_CODE_FORE_PERMISSION);
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
                outputFileUri = FileProvider.getUriForFile(KonsumerPurnaKelengkapanDokumenActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "fotodokumen.png"));
            } else {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "fotodokumen.png"));
            }
        }
        return outputFileUri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode) {
                case TAKE_PICTURE_KTPNASABAHPASANGAN:
                    setDataImage( bitmap_ktpnasabahpasangan, iv_ktpnasabahpasangan, data);
                    break;
                case TAKE_PICTURE_KARTUKELUARGA:
                    setDataImage(bitmap_kartukeluarga, iv_kartukeluarga, data);
                    break;
                case TAKE_PDF_SURATNIKAH:
                    setDataPdf(iv_suratnikah, data);
                    break;
                case TAKE_PICTURE_PASPHOTO:
                    setDataImage( bitmap_pasphoto, iv_pasphoto, data);
                    break;
                case TAKE_PICTURE_NPWP:
                    setDataImage(bitmap_npwp, iv_npwp, data);
                    break;
                case TAKE_PICTURE_FORMULIRAPLIKASI:
                    setDataImage( bitmap_formuliraplikasi, iv_formuliraplikasi, data);
                    break;
                case TAKE_PICTURE_SK_PENSIUN:
                    setDataPdf(iv_skpensiun, data);
                    break;
                case TAKE_PICTURE_KARIP:
                    setDataPdf(iv_karip, data);
                    break;
                case TAKE_PICTURE_BUKTI_PENERIMAAN_PENSIUN:
                    setDataPdf(iv_bukti_pensiun, data);
                    break;
                case TAKE_PICTURE_SK_JANDA_DUDA:
                    setDataPdf(iv_janda_duda, data);
                    break;
                case TAKE_PICTURE_SPK:
                    setDataPdf(iv_spk, data);
                    break;
                case TAKE_PICTURE_SURAT_PERNYATAAN_NASABAH_RPC:
                    setDataPdf(iv_surat_pernyataan_rpc, data);
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
        String fileName = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()) + "_" + System.currentTimeMillis() + ".jpg";
        appPreferences.setFotoKelengkapanDokumen(fileName);
    }

    private void initImageFileNameDokumen() {
        String fileName = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()) + "_" + System.currentTimeMillis() + ".jpg";
//        appPreferences.setDokumenSuratNikah(fileName);
    }


    private void setDataImage( Bitmap bitmap, ImageView iv, Intent data) {
        Uri uri;
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

            } catch (Exception e) {
                e.printStackTrace();
            }
            String filename = appPreferences.getFotoKelengkapanDokumen();
            ImageHandler.saveImageToCache(this, bitmap, filename);
            uploadFoto(filename);
        }
    }

    private void setDataPdf(ImageView iv, Intent data) {
        iv.setImageDrawable(ContextCompat.getDrawable(KonsumerPurnaKelengkapanDokumenActivity.this, R.drawable.ic_pdf));
        initImageFileNameDokumen();
        Uri uri = data.getData();
        String filename = appPreferences.getFotoKelengkapanDokumen();
        String path = getFilePathFromURI(KonsumerPurnaKelengkapanDokumenActivity.this, uri, filename);
        uploadFile(path, iv);
    }

    public Bitmap setLoadImage(final ImageView iv, int idFoto) {
        String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFile + idFoto;
        Glide
                .with(KonsumerPurnaKelengkapanDokumenActivity.this)
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

    public void uploadFoto(String filename) {
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
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            int idFoto = response.body().getData().get("id").getAsInt();

                            KonsumerKmgKelengkapanDokumen realmKelengkapan = realm.where(KonsumerKmgKelengkapanDokumen.class).equalTo("ID_APLIKASI", idAplikasi).findFirst();

                            KonsumerKmgKelengkapanDokumen realmCopyKelengkapan = new KonsumerKmgKelengkapanDokumen();

                            if (!realm.isInTransaction()) {
                                realm.beginTransaction();
                            }

                            if (realmKelengkapan == null) {
                                realmCopyKelengkapan.setID_APLIKASI(idAplikasi);
                                realm.insertOrUpdate(realmCopyKelengkapan);
                                realm.commitTransaction();
                                //realm.close();
                            } else {
                                realmCopyKelengkapan = realm.copyFromRealm(realmKelengkapan);
                            }


                            if (!realm.isInTransaction()) {
                                realm.beginTransaction();
                            }

                            switch (isSelectPhoto) {
                                case "ktpnasabahpasangan":
                                    val_ktpnasabahpasangan = idFoto;
                                    cb_ktpnasabahpasangan.setChecked(true);
                                    val_cb_ktpnasabahpasangan = true;
                                    Log.d("masukpakeki", "it is set");
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

                                default:
                                    break;
                            }
                        } else {
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void uploadFile(String filename, final ImageView iv) {
//        Log.d("Test Masuk", "uploadFile");
        loading.setVisibility(View.VISIBLE);
        File file = new File(filename);
//        File file = new File(getApplicationContext().getCacheDir(), filename);
//        File file = new File(KonsumerPurnaKelengkapanDokumenActivity.class, filename);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        String fileNamePdf = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date())+"_"+System.currentTimeMillis()+ ".pdf";
        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", fileNamePdf, requestBody);
//        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), pdfname);

//        PDFInterface getResponse = retrofit.create(PDFInterface.class);
//        Call<ParseResponse> call = getResponse.uploadImage(fileToUpload);

//        File imageFile = new File(getApplicationContext().getCacheDir(), filename);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), imageFile);
//        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().uploadFileOld(fileBody);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    loading.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            int idPdF = response.body().getData().get("id").getAsInt();
                            Log.d("idPdF", String.valueOf(idPdF));

                            KonsumerKmgKelengkapanDokumen realmKelengkapan = realm.where(KonsumerKmgKelengkapanDokumen.class).equalTo("ID_APLIKASI", idAplikasi).findFirst();

                            KonsumerKmgKelengkapanDokumen realmCopyKelengkapan = new KonsumerKmgKelengkapanDokumen();

                            if (!realm.isInTransaction()) {
                                realm.beginTransaction();
                            }

                            if (realmKelengkapan == null) {
                                realmCopyKelengkapan.setID_APLIKASI(idAplikasi);
                                realm.insertOrUpdate(realmCopyKelengkapan);
                                realm.commitTransaction();
//                                //realm.close();
                            } else {
                                realmCopyKelengkapan = realm.copyFromRealm(realmKelengkapan);
                            }


                            if (!realm.isInTransaction()) {
                                realm.beginTransaction();
                            }

                            switch (isSelectPhoto) {
                                case "suratnikah":
                                    val_suratnikah = idPdF;
                                    cb_suratnikah.setChecked(true);
                                    val_cb_suratnikah = true;
                                    realmCopyKelengkapan.setID_DOKUMEN_SURAT_NIKAH(idPdF);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                case "buktipensiun":
                                     val_bukti_penerimaan_pensiun = idPdF;
                                     cb_bukti_pensiun.setChecked(true);
                                     val_cb_bukti_penerimaan_pensiun = true;
                                    realmCopyKelengkapan.setID_DOKUMEN_SLIP_GAJI(idPdF);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                case "skpensiun":
                                    val_sk_pensiun = idPdF;
                                    cb_sk_pensiun.setChecked(true);
                                    val_cb_sk_pensiun = true;
                                    realmCopyKelengkapan.setIdPhotosuratPensiun(idPdF);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                case "kartuidentitaspensiun":
                                    val_karip = idPdF;
                                    cb_karip.setChecked(true);
                                    val_cb_karip = true;
                                    realmCopyKelengkapan.setIdPhotoidentitasPensiun(idPdF);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                case "skjandaduda":
                                    val_sk_janda_duda = idPdF;
                                    cb_sk_janda_duda.setChecked(true);
                                    val_cb_sk_janda_duda = true;
                                    realmCopyKelengkapan.setIdPhotosuratJandaDuda(idPdF);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                case "spk":
                                    val_spk = idPdF;
                                    cb_spk.setChecked(true);
                                    val_cb_spk = true;
                                    realmCopyKelengkapan.setIdPhotosuratKesehatan(idPdF);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                case "suratpernyataannasabahrpc":
                                    val_surat_pernyataan_nasabah_rpc = idPdF;
                                    cb_surat_pernyataan_rpc.setChecked(true);
                                    val_cb_surat_pernyataan_nasabah_rpc = true;
                                    realmCopyKelengkapan.setIdPhotosuratNasabahRpc(idPdF);
                                    realm.insertOrUpdate(realmCopyKelengkapan);
                                    realm.commitTransaction();
                                    //realm.close();
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                Log.d("onFailure", t.toString());
                AppUtil.notiferror(KonsumerPurnaKelengkapanDokumenActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }


    @Override
    public void success(boolean val) {
        if (val)
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

        switch (v.getId()) {
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
                startActivityForResult(intent, TAKE_PDF_SURATNIKAH);
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
            case R.id.btn_skpensiun:
                isSelectPhoto = "skpensiun";
                startActivityForResult(intent, TAKE_PICTURE_SK_PENSIUN);
                break;
            case R.id.btn_karip:
                isSelectPhoto = "kartuidentitaspensiun";
                startActivityForResult(intent, TAKE_PICTURE_KARIP);
                break;
            case R.id.btn_bukti_pensiun:
                isSelectPhoto = "buktipensiun";
                startActivityForResult(intent, TAKE_PICTURE_BUKTI_PENERIMAAN_PENSIUN);
                break;
            case R.id.btn_janda_duda:
                isSelectPhoto = "skjandaduda";
                startActivityForResult(intent, TAKE_PICTURE_SK_JANDA_DUDA);
                break;
            case R.id.btn_spk:
                isSelectPhoto = "spk";
                startActivityForResult(intent, TAKE_PICTURE_SPK);
                break;
            case R.id.btn_surat_pernyataan_rpc:
                isSelectPhoto = "suratpernyataannasabahrpc";
                startActivityForResult(intent, TAKE_PICTURE_SURAT_PERNYATAAN_NASABAH_RPC);
                break;


            case R.id.iv_ktpnasabahpasangan:
                if (val_ktpnasabahpasangan == 0) {
                    isSelectPhoto = "ktpnasabahpasangan";
                    openCamera(TAKE_PICTURE_KTPNASABAHPASANGAN);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable) iv_ktpnasabahpasangan.getDrawable()).getSourceBitmap());
                }

                break;
            case R.id.iv_kartukeluarga:
                if (val_kartukeluarga == 0) {
                    isSelectPhoto = "kartukeluarga";
                    openCamera(TAKE_PICTURE_KARTUKELUARGA);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable) iv_kartukeluarga.getDrawable()).getSourceBitmap());
                }

                break;
            case R.id.iv_suratnikah:
                if (val_suratnikah == 0) {
                    isSelectPhoto = "suratnikah";
                    startActivityForResult(intent, TAKE_PDF_SURATNIKAH);
                } else {
                    setLoadPdf(val_suratnikah);
                }
                break;
            case R.id.iv_pasphoto:
                if (val_pasphoto == 0) {
                    isSelectPhoto = "pasphoto";
                    openCamera(TAKE_PICTURE_PASPHOTO);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable) iv_pasphoto.getDrawable()).getSourceBitmap());
                }

                break;
            case R.id.iv_npwp:
                if (val_npwp == 0) {
                    isSelectPhoto = "npwp";
                    openCamera(TAKE_PICTURE_NPWP);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable) iv_npwp.getDrawable()).getSourceBitmap());
                }

                break;
            case R.id.iv_formuliraplikasi:
                if (val_formuliraplikasi == 0) {
                    isSelectPhoto = "formuliraplikasi";
                    openCamera(TAKE_PICTURE_FORMULIRAPLIKASI);
                } else {
                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable) iv_formuliraplikasi.getDrawable()).getSourceBitmap());
                }

                break;
            case R.id.iv_skpensiun:
                if ( val_sk_pensiun == 0) {
                    isSelectPhoto = "skpensiun";
                    startActivityForResult(intent, TAKE_PICTURE_SK_PENSIUN);
                } else {
                   setLoadPdf(val_sk_pensiun);
                }
                break;
            case R.id.iv_karip:
                if ( val_karip == 0) {
                    isSelectPhoto = "kartuidentitaspensiun";
                    startActivityForResult(intent, TAKE_PICTURE_KARIP);
                } else {
                   setLoadPdf(val_karip);
                }
                break;
            case R.id. iv_bukti_pensiun:
                if ( val_bukti_penerimaan_pensiun == 0) {
                    isSelectPhoto = "buktipensiun";
                    startActivityForResult(intent, TAKE_PICTURE_BUKTI_PENERIMAAN_PENSIUN);
                } else {
                  setLoadPdf(val_bukti_penerimaan_pensiun);
                }

                break;
            case R.id. iv_janda_duda:
                if ( val_sk_janda_duda == 0) {
                    isSelectPhoto = "skjandaduda";
                    startActivityForResult(intent, TAKE_PICTURE_SK_JANDA_DUDA);
                } else {
                   setLoadPdf(val_sk_janda_duda);
                }

                break;
            case R.id.  iv_spk:
                if ( val_spk == 0) {
                    isSelectPhoto = "spk";
                    startActivityForResult(intent, TAKE_PICTURE_SPK);
                } else {
                   setLoadPdf(val_spk);
                }

                break;
            case R.id.  iv_surat_pernyataan_rpc:
                if ( val_surat_pernyataan_nasabah_rpc == 0) {
                    isSelectPhoto = "suratpernyataannasabahrpc";
                    startActivityForResult(intent, TAKE_PICTURE_SURAT_PERNYATAAN_NASABAH_RPC);
                } else {
                   setLoadPdf(val_surat_pernyataan_nasabah_rpc);
                }
                break;
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

    private void autoCheckboxOnclick(View viewInduk) {

        if (viewInduk instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) viewInduk;
            for (int i = 0, count = vg.getChildCount(); i < count; ++i) {
                View view = vg.getChildAt(i);
                autoCheckboxOnclick(view);
                if (view instanceof LinearLayout) {
                    ((LinearLayout) view).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(KonsumerPurnaKelengkapanDokumenActivity.this, "Tombol akan berubah hijau setelah upload dokumen", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                if (view instanceof CheckBox) {
                    ((CheckBox) view).setClickable(false);
                }
            }
        }

    }

    private void otherViewModification() {

        //kalau dia takeover apapun, tambah keterangan opsional di textviewnya

//        if (idTujuan != 91 && idTujuan != 110 && idTujuan != 111) {
//            tv_kwitansi.setText(tv_kwitansi.getText().toString() + " (Opsional)");
//        }



        //ambil data rpc dari data finansial yang disimpan di realm
        try {
            DataGlobalHotprospekPojo dataGlobalHotprospekPojo=realm.where(DataGlobalHotprospekPojo.class).equalTo("idAplikasi", idAplikasi).findFirst();

            rpcGlobal=Double.parseDouble(dataGlobalHotprospekPojo.getRpc());

            Log.d("nilairpcglobal",dataGlobalHotprospekPojo.getRpc());

            //pantekan rpc
//            rpcGlobal=70d;

            if(rpcGlobal>=70){
                ll_pernyataan_nasabah.setVisibility(View.VISIBLE);

                wajibSuratRpc=true;
            }
            else {
                ll_pernyataan_nasabah.setVisibility(View.GONE);
                v_pernyataan_nasabah.setVisibility(View.GONE);
                wajibSuratRpc=false;
            }


            //ambil data usia dan plafon untuk validasi wajib spk atau tidak


            //jika usia >45 dan plafon diatas 200juta
            if(dataGlobalHotprospekPojo.getUsia()>=45&&plafond>=200000000){
                wajibSpk=true;
            }
            //jika usia >65 dan plafon diatas 100juta
            else if(dataGlobalHotprospekPojo.getUsia()>=65&&plafond>=100000000){
                wajibSpk=true;
            }
            else{
                wajibSpk=false;
                tv_spk.setText("Opsional - Surat Pernyataan Kesehatan");

            }

            //ambil data apakah dia janda atau bukan, berdasarkan kategori jenis nasabah di data lengkao
            if(dataGlobalHotprospekPojo.isAdalahJandaDuda()){
                wajibJandaDuda=true;
            }
            else{
                wajibJandaDuda=false;
                ll_janda_duda.setVisibility(View.GONE);
                v_janda_duda.setVisibility(View.GONE);
            }

            Log.d("statuswajibspkusia",Integer.toString(dataGlobalHotprospekPojo.getUsia()));
            Log.d("statuswajibspkplafon",Integer.toString(plafond));
            Log.d("statuswajibspk",Boolean.toString(wajibSpk));
        }
        catch (NullPointerException e){
            Log.d("ada error di ","realm global");
            Log.d("pesanerror",e.getMessage());
        }



    }

    @Override
    public void onBackPressed() {

        if (approved.equalsIgnoreCase("no")) {
            CustomDialog.DialogBackpressSaved(KonsumerPurnaKelengkapanDokumenActivity.this);
        } else {
            super.onBackPressed();
        }
    }
    
}
