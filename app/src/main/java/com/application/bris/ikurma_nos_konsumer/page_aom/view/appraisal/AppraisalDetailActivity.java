package com.application.bris.ikurma_nos_konsumer.page_aom.view.appraisal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.adapter.menu.SubmenuAppraisalAdapter;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.kpr.SelesaiAppraisal;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.rejectAppraisal;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryHotprospek;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.config.menu.Menu;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.listener.menu.MenuClickListener;
import com.application.bris.ikurma_nos_konsumer.model.menu.ListViewSubmenuHotprospek;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.HotprospekKpr;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan.AgunanTerikatActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.data_finansial.DataFinansialKprActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.data_finansial.DataFinansialPurnaActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap.kpr.DataLengkapKprActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap.purna.DataLengkapActivityPurna;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.history.HistoryActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.kelengkapandokumen.kpr.KonsumerKprKaryawanPnsKelengkapanDokumenActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.prescreening.PrescreeningKprActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.scoring_konsumer.ScoringKprActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.sektorekonomi.kpr.SektorEkonomiKprActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.Stringinfo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppraisalDetailActivity extends AppCompatActivity implements MenuClickListener, View.OnClickListener, ConfirmListener {

    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    @BindView(R.id.tv_nama)
    TextView tv_nama;
    @BindView(R.id.tv_tenor)
    TextView tv_tenor;
    @BindView(R.id.tv_produk)
    TextView tv_produk;
    @BindView(R.id.tv_plafond)
    TextView tv_plafond;
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.tv_idaplikasi)
    TextView tv_idaplikasi;
    @BindView(R.id.tv_cifsyiar)
    TextView tv_cifsyiar;
    @BindView(R.id.tv_tujuanpenggunaan)
    TextView tv_tujuanpenggunaan;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.rv_submenu_approved)
    RecyclerView rv_submenu_approved;

    @BindView(R.id.ll_info)
    LinearLayout ll_info;
    @BindView(R.id.tv_info)
    TextView tv_info;

    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_page_title)
    TextView tv_page_title;


    @BindView(R.id.btn_reject)
    Button btn_reject;

    @BindView(R.id.btn_selesai_appraisal)
    Button btn_selesai_appraisal;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    public int idAplikasi, cif;
    private HotprospekKpr data;
    private HotprospekKpr dataHp;
    private String dataString;
    private Bitmap bitmapPhoto;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private List<ListViewSubmenuHotprospek> dataMenu;
    private SubmenuAppraisalAdapter submenuHotprospekAdapter;
    private GridLayoutManager layoutMenu;
    private int coloumMenu = 3;
    private int confirmId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_appraisal_detail);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        cif = getIntent().getIntExtra("cif", 0);



        backgroundStatusBar();


        AppUtil.toolbarRegular(this, "Detail Appraisal");
        tv_info.setText(Html.fromHtml(Stringinfo.info_appraisal));
        btn_reject.setOnClickListener(this);
        btn_selesai_appraisal.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDataHotprospek();
        sm_placeholder.startShimmer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm_placeholder.stopShimmer();
    }

    private void loadDataHotprospek() {
        inquiryHotprospek req = new inquiryHotprospek(idAplikasi);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryHotprospek(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            Gson gson = new Gson();
                            dataString = response.body().getData().get("aplikasi").toString();
                            data = gson.fromJson(dataString, HotprospekKpr.class);
                            String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFile + data.getFid_photo();
                            Glide
                                    .with(AppraisalDetailActivity.this)
                                    .asBitmap()
                                    .load(url_photo)
                                    .into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                            iv_photo.setImageBitmap(resource);
                                            bitmapPhoto = resource;
                                        }
                                    });

                            iv_photo.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", bitmapPhoto);
                                }
                            });

                            tv_nama.setText(data.getNama_debitur_1());
                            tv_tenor.setText(Integer.toString(data.getJw()) + " Bulan");
                            if (data.getPROGRAM_NAME() != null && data.getPROGRAM_NAME().toLowerCase().contains("purna")) {
                                tv_produk.setText(data.getPROGRAM_NAME());
                                tv_produk.setText(data.getPROGRAM_NAME());
                            } else {
                                tv_produk.setText(data.getNama_produk());
                            }

                            tv_plafond.setText(AppUtil.parseRupiah(Long.toString(data.getPlafond_induk())));
                            tv_idaplikasi.setText(Integer.toString(data.getId_aplikasi()));
                            tv_cifsyiar.setText(Integer.toString(data.getFid_cif_las()));
                            tv_tujuanpenggunaan.setText(data.getNama_tujuan());
                            initializeMenu();
                            otherViewChanges();

                        } else {
                            AppUtil.notiferror(AppraisalDetailActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 2000);
                        }
                    } else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AppraisalDetailActivity.this, findViewById(android.R.id.content), error.getMessage());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }
                } catch (Exception e) {
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
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(AppraisalDetailActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }

    private void rejectAppraisal() {
        loading.setVisibility(View.VISIBLE);
        rejectAppraisal req = new rejectAppraisal(String.valueOf(appPreferences.getUid()), String.valueOf(idAplikasi), "OK");
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().rejectAppraisal(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            String msg = "";
                            msg = getString(R.string.title_successtorejectappraisal);
                            CustomDialog.DialogSuccess(AppraisalDetailActivity.this, "Success!", msg, AppraisalDetailActivity.this);

                        } else {
                            AppUtil.notiferror(AppraisalDetailActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 2000);
                        }
                    } else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AppraisalDetailActivity.this, findViewById(android.R.id.content), error.getMessage());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }
                } catch (Exception e) {
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
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(AppraisalDetailActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }

    private void selesaiAppraisal() {
        loading.setVisibility(View.VISIBLE);
        SelesaiAppraisal req = new SelesaiAppraisal();
        req.setIdApl(String.valueOf(idAplikasi));
        req.setUid(String.valueOf(appPreferences.getUid()));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().selesaiAppraisal(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            String msg = "";
                            msg = getString(R.string.title_successselesaiappraisal);
                            CustomDialog.DialogSuccess(AppraisalDetailActivity.this, "Success!", msg, AppraisalDetailActivity.this);

                        } else {
                            AppUtil.notiferror(AppraisalDetailActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 2000);
                        }
                    } else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AppraisalDetailActivity.this, findViewById(android.R.id.content), error.getMessage());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }
                } catch (Exception e) {
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
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(AppraisalDetailActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void success(boolean val) {
        if (val)
            finish();
        startActivity(new Intent(this, AppraisalActivity.class));
    }

    @Override
    public void confirm(boolean val) {
        if (val){
            if(confirmId==1){
                rejectAppraisal();
            }
            else if(confirmId==2){
                selesaiAppraisal();
            }

        }

    }

    public void initializeMenu() {
        rv_submenu_approved.setHasFixedSize(true);
        dataHp = data;
        dataMenu = getListMenu();
        submenuHotprospekAdapter = new SubmenuAppraisalAdapter(this, dataMenu, dataHp, this);
        layoutMenu = new GridLayoutManager(this, coloumMenu);
        rv_submenu_approved.setLayoutManager(layoutMenu);
        rv_submenu_approved.setAdapter(submenuHotprospekAdapter);
    }

    private List<ListViewSubmenuHotprospek> getListMenu() {
        List<ListViewSubmenuHotprospek> menu = new ArrayList<>();
        Menu.SubmenuHotprospekAppraisal(this, menu);
        return menu;
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public void onMenuClick(String menu) {
        if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_prescreening))) {
            Intent it = new Intent(this, PrescreeningKprActivity.class);
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("kodeProduct", Integer.toString(data.getId_tujuan()));
            it.putExtra("approved", "yes");
            it.putExtra("flagMemoSales", data.getFlag_memo_sales());
            startActivity(it);
        } else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_datalengkap))) {
            Intent it;

            if (data.getPROGRAM_NAME() != null && data.getPROGRAM_NAME().toLowerCase().contains("purna")) {
                it = new Intent(this, DataLengkapActivityPurna.class);
                Log.d("purnafaedah", "masuk ke purna");
            } else {
                it = new Intent(this, DataLengkapKprActivity.class);
//                it = new Intent(this, DataLengkapActivity.class);
                Log.d("purnafaedah", "tidak masuk ke purna");
            }
            it.putExtra("cif", String.valueOf(data.getFid_cif_las()));
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("plafond", data.getPlafond_induk());
            it.putExtra("approved", "yes");
            it.putExtra("gimmick", String.valueOf(data.getGIMMICK_ID()));
            startActivity(it);
        } else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_history))) {
            Intent it = new Intent(this, HistoryActivity.class);
            it.putExtra("cif", String.valueOf(data.getFid_cif_las()));
            it.putExtra("idAplikasi", data.getId_aplikasi());
            startActivity(it);
        } else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_sektorekonomi))) {
            Intent it = new Intent(this, SektorEkonomiKprActivity.class);
            it.putExtra("idTujuan", data.getId_tujuan());
            it.putExtra("namaTujuan", data.getNama_tujuan());
            it.putExtra("cifLas", data.getFid_cif_las());
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("approved", "yes");
            it.putExtra("loanType", data.getID_PROGRAM());
            it.putExtra("gimmick", String.valueOf(data.getGIMMICK_ID()));
            startActivity(it);
        } else if (menu.equalsIgnoreCase("data finansial")) {
            Intent it;

            //seleksi dia purna atau bukan (dari kata kata purna di program name)
            if (data.getPROGRAM_NAME() != null && data.getPROGRAM_NAME().toLowerCase().contains("purna")) {
                it = new Intent(this, DataFinansialPurnaActivity.class);
            } else {
                it = new Intent(this, DataFinansialKprActivity.class);
            }
            it.putExtra("cif", String.valueOf(data.getFid_cif_las()));
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("kodeProduct", data.getKode_produk());
            it.putExtra("namaProduct", data.getNama_produk());
            it.putExtra("idTujuanPembiayaan", data.getId_tujuan());
            it.putExtra("tujuanPembiayaan", data.getNama_tujuan());
            it.putExtra("plafond", data.getPlafond_induk());
            it.putExtra("jw", data.getJw());
            it.putExtra("approved", "yes");

            startActivity(it);
        } else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_kelengkapandokumen))) {
            Intent it;

//            if(data.getLOAN_TYPE().equalsIgnoreCase("125")){
//                it = new Intent(this, KonsumerPurnaKelengkapanDokumenActivity.class);
//            }
//            else if (data.getLOAN_TYPE().equalsIgnoreCase("129")){
//                it = new Intent(this, KonsumerPraPurnaKelengkapanDokumenActivity.class);
//            }
//            else{
//                it = new Intent(this, KonsumerKmgKelengkapanDokumenActivity.class);
//            }

            it = new Intent(this, KonsumerKprKaryawanPnsKelengkapanDokumenActivity.class);

            it.putExtra("gimmick", String.valueOf(data.getGIMMICK_ID()));
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("kodeProduct", data.getKode_produk());
            it.putExtra("idTujuan", data.getId_tujuan());
            it.putExtra("plafond", data.getPlafond_induk());
            it.putExtra("klasifikasiKredit", data.getKlasifikasi_kredit());
            it.putExtra("approved", "yes");
            startActivity(it);
        } else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_scoring))) {
            Intent it = new Intent(this, ScoringKprActivity.class);
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("cif", data.getFid_cif_las());
            it.putExtra("kodeProduct", data.getKode_produk());
            it.putExtra("approved", "yes");
            startActivity(it);
        } else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_agunan))) {
            Intent it = new Intent(this, AgunanTerikatActivity.class);
            it.putExtra("cif", String.valueOf(data.getFid_cif_las()));
            it.putExtra("idAplikasi", String.valueOf(data.getId_aplikasi()));
            it.putExtra("jenisAo", "ao silang");
            it.putExtra("flagAgunan", data.getFlag_agunan());
            appPreferences.setStatusAplikasiAgunan(Integer.toString(data.getId_st_aplikasi()));
            startActivity(it);

//            Toast.makeText(this, "Tidak Ada Data Agunan, Silahkan Lanjutkan Ke Scoring", Toast.LENGTH_LONG).show();
//            AppUtil.notifinfo(this,findViewById(android.R.id.content),"Pembiayaan Beragun SK, Silahkan Lanjutkan Ke Scoring");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_reject:
                if(data.getId_st_aplikasi()==1||data.getId_st_aplikasi()==-34){
                    finish();
                }
                else{
                    CustomDialog.DialogConfirmation(this, "Konfirmasi", getString(R.string.title_confirm_rejectappraisal), this);
                    confirmId=1;
                }

                break;
            case R.id.btn_selesai_appraisal:
                if(data.getId_st_aplikasi()==1||data.getId_st_aplikasi()==-34){
                    finish();
                }
                else if(appPreferences.getStatusAppraise().isEmpty()){
                    CustomDialog.DialogConfirmation(this, "Konfirmasi", getString(R.string.title_confirm_validasi_appraisal), this);
                    confirmId=2;
                }
                else{
                    CustomDialog.DialogConfirmation(this, "Konfirmasi", getString(R.string.title_confirm_selesaiappraisal), this);
                    confirmId=2;
                }
                break;
        }
    }

    private void otherViewChanges(){
        if(data.getId_st_aplikasi()==1||data.getId_st_aplikasi()==-34){
            btn_reject.setText("Selesai");
            btn_reject.setBackgroundColor(getResources().getColor(R.color.colorGreenSuccess));
        }
    }
}
