package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryHotprospek;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.config.menu.Menu;
import com.application.bris.ikurma_nos_konsumer.adapter.menu.SubmenuHotprospekAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.listener.menu.MenuClickListener;
import com.application.bris.ikurma_nos_konsumer.model.menu.ListViewSubmenuHotprospek;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSKirimPutusan;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.hotprospek;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan.AgunanTerikatActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.data_finansial.DataFinansialPurnaActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap.DataLengkapActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap.purna.DataLengkapActivityPurna;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.history.HistoryActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.kelengkapandokumen.KonsumerKmgKelengkapanDokumenActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.kelengkapandokumen.KonsumerPraPurnaKelengkapanDokumenActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.kelengkapandokumen.KonsumerPurnaKelengkapanDokumenActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.prescreening.PrescreeningActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.scoring_konsumer.ScoringKonsumerActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.sektorekonomi.SektorEkonomiActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.data_finansial.DataFinansialKmgActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
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

public class HotprospekDetailActivity extends AppCompatActivity implements MenuClickListener, ConfirmListener{

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
    @BindView(R.id.rv_submenu_hotprospek)
    RecyclerView rv_submenu_hotprospek;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.ll_content)
    LinearLayout ll_content;

    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_page_title)
    TextView tv_page_title;
    @BindView(R.id.btn_right)
    ImageView btn_right;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_send)
    Button btn_send;

    private List<ListViewSubmenuHotprospek> dataMenu;
    private SubmenuHotprospekAdapter submenuHotprospekAdapter;
    private GridLayoutManager layoutMenu;
    private int coloumMenu = 3;
    public static int idAplikasi;
    private hotprospek data;
    private hotprospek dataHp;
    private String dataString;
    private Bitmap bitmapPhoto;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_hotprospek_detail);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        customToolbar();
        backgroundStatusBar();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_send.setEnabled(false);
                BSKirimPutusan.display(getSupportFragmentManager(), HotprospekDetailActivity.this, data);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_send.setEnabled(true);
                    }
                }, 5000);
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        btn_right.setEnabled(false);
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
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00"))
                        {
                            Gson gson = new Gson();
                            dataString = response.body().getData().get("aplikasi").toString();
                            data = gson.fromJson(dataString, hotprospek.class);
                            String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + data.getFid_photo();
                            Glide
                                    .with(HotprospekDetailActivity.this)
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
                            tv_tenor.setText(Integer.toString(data.getJw())+" Bulan");
                            if(data.getPROGRAM_NAME()!=null&&data.getPROGRAM_NAME().toLowerCase().contains("purna")){ tv_produk.setText(data.getPROGRAM_NAME());
                          tv_produk.setText(data.getPROGRAM_NAME());
                            }
                            else{
                                tv_produk.setText(data.getNama_produk());
                            }

                            tv_plafond.setText(AppUtil.parseRupiah(Integer.toString(data.getPlafond_induk())));
                            tv_status.setText(data.getStatus_aplikasi());
                            tv_idaplikasi.setText(Integer.toString(data.getId_aplikasi()));
                            tv_cifsyiar.setText(Integer.toString(data.getFid_cif_las()));
                            tv_tujuanpenggunaan.setText(data.getNama_tujuan());
                            initializeMenu();
                            enableButtonSend();

                        }
                        else {
                            AppUtil.notiferror(HotprospekDetailActivity.this, findViewById(android.R.id.content), response.body().getMessage());
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
                        AppUtil.notiferror(HotprospekDetailActivity.this, findViewById(android.R.id.content), error.getMessage());
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
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(HotprospekDetailActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }


    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void initializeMenu(){
        rv_submenu_hotprospek.setHasFixedSize(true);
        dataHp = data;
        dataMenu = getListMenu();
        submenuHotprospekAdapter = new SubmenuHotprospekAdapter(this, dataMenu, dataHp,this);
        layoutMenu = new GridLayoutManager(this, coloumMenu);
        rv_submenu_hotprospek.setLayoutManager(layoutMenu);
        rv_submenu_hotprospek.setAdapter(submenuHotprospekAdapter);
    }

    public void enableButtonSend(){
        if (data.getId_st_aplikasi() == 1 || data.getId_st_aplikasi() == -14){
            btn_right.setEnabled(true);
        }
        else{
            btn_right.setEnabled(false);
        }
        int flagPrescreening = data.getFlag_prescreening();
        int flagDataLengkap = data.getFlag_data_lengkap();
        int flagSektorEkonomi = data.getFlag_data_pembiayaan();
        int flagFinansial = data.getFlag_finansial();
        int flagAgunan = data.getFlag_agunan();
        int flagKelengkapanData = data.getFlag_dokumen();
        int flagScoring = data.getFlag_scoring();

        if (data.getId_st_aplikasi() == 1 || data.getId_st_aplikasi() == -14){
            if (flagPrescreening == 1 && flagDataLengkap == 1 && flagSektorEkonomi == 1 && flagFinansial == 1  &&     flagKelengkapanData == 1 && flagScoring == 1){
                btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                btn_send.setEnabled(true);
            }
            else{

                //pantekan button selalu enabled selama testing
//                btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
//                btn_send.setEnabled(true);
                btn_send.setEnabled(false);
            }
        }
        else{
            //pantekan button selalu enabled selama testing
//            btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
//                btn_send.setEnabled(true);
            btn_send.setEnabled(false);
        }
    }

    public void customToolbar(){
        btn_right.setVisibility(View.VISIBLE);
        tv_page_title.setText("Detail Hotprospek");
        btn_right.setImageResource(R.drawable.ic_edit_24dp);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_right.setEnabled(false);
                Intent it = new Intent(HotprospekDetailActivity.this, HotprospekEditActivity.class);
                it.putExtra("dataHotprospek", dataString);
                startActivity(it);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_right.setEnabled(true);
                    }
                }, 5000);
            }
        });
    }


    private List<ListViewSubmenuHotprospek> getListMenu() {
        List<ListViewSubmenuHotprospek> menu = new ArrayList<>();
        Menu.SubmenuD1(this, menu);
        return menu;
    }

    @Override
    public void onMenuClick(String menu) {
        if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_prescreening))){
            Intent it = new Intent(this, PrescreeningActivity.class);
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("kodeProduct", Integer.toString(data.getId_tujuan()));
            it.putExtra("approved", "no");
            it.putExtra("flagMemoSales",data.getFlag_memo_sales());
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_datalengkap))){
            Intent it ;

            if(data.getPROGRAM_NAME()!=null&&data.getPROGRAM_NAME().toLowerCase().contains("purna")){
                it=new Intent(this, DataLengkapActivityPurna.class);
                Log.d("purnafaedah","masuk ke purna");
            }
            else{
                it = new Intent(this, DataLengkapActivity.class);
                Log.d("purnafaedah","tidak masuk ke purna");
            }
            it.putExtra("cif", String.valueOf(data.getFid_cif_las()));
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("plafond", data.getPlafond_induk());
            it.putExtra("approved", "no");
            it.putExtra("gimmick", String.valueOf(data.getGIMMICK_ID()));
            startActivity(it);
        }

        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_history))){
            Intent it = new Intent(this, HistoryActivity.class);
            it.putExtra("cif", String.valueOf(data.getFid_cif_las()));
            it.putExtra("idAplikasi", data.getId_aplikasi());
            startActivity(it);
        }

        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_sektorekonomi))){
            Intent it = new Intent(this, SektorEkonomiActivity.class);
            it.putExtra("idTujuan", data.getId_tujuan());
            it.putExtra("namaTujuan", data.getNama_tujuan());
            it.putExtra("cifLas", data.getFid_cif_las());
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("approved", "no");
            it.putExtra("loanType", data.getLOAN_TYPE());
            it.putExtra("gimmick", String.valueOf(data.getGIMMICK_ID()));
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase("data finansial")){
            Intent it ;

            //seleksi dia purna atau bukan (dari kata kata purna di program name)
            if(data.getPROGRAM_NAME()!=null&&data.getPROGRAM_NAME().toLowerCase().contains("purna")){
                it=new Intent(this, DataFinansialPurnaActivity.class);
            }
            else{
                it = new Intent(this, DataFinansialKmgActivity.class);
            }
            it.putExtra("cif", String.valueOf(data.getFid_cif_las()));
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("kodeProduct", data.getKode_produk());
            it.putExtra("namaProduct", data.getNama_produk());
            it.putExtra("idTujuanPembiayaan", data.getId_tujuan());
            it.putExtra("tujuanPembiayaan", data.getNama_tujuan());
            it.putExtra("plafond",  data.getPlafond_induk());
            it.putExtra("jw", data.getJw());
            it.putExtra("approved", "no");

            startActivity(it);
        }


        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_kelengkapandokumen))){
            Intent it ;

          if(data.getLOAN_TYPE().equalsIgnoreCase("125")){
              it = new Intent(this, KonsumerPurnaKelengkapanDokumenActivity.class);
          }
          else if (data.getLOAN_TYPE().equalsIgnoreCase("129")){
              it = new Intent(this, KonsumerPraPurnaKelengkapanDokumenActivity.class);
          }
          else{
              it = new Intent(this, KonsumerKmgKelengkapanDokumenActivity.class);
          }

            it.putExtra("gimmick", String.valueOf(data.getGIMMICK_ID()));
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("kodeProduct", data.getKode_produk());
            it.putExtra("idTujuan", data.getId_tujuan());
            it.putExtra("plafond", data.getPlafond_induk());
            it.putExtra("approved", "no");
            startActivity(it);
        }

        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_scoring))){
            Intent it = new Intent(this, ScoringKonsumerActivity.class);
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("cif", data.getFid_cif_las());
            it.putExtra("kodeProduct", data.getKode_produk());
            it.putExtra("approved", "no");
            startActivity(it);
        }

        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_agunan))){
            Intent it = new Intent(this, AgunanTerikatActivity.class);
//            it.putExtra("cif", String.valueOf(data.getFid_cif_las()));
//            it.putExtra("idAplikasi", String.valueOf(data.getId_aplikasi()));
//
//            Toast.makeText(this, "Tidak Ada Data Agunan, Silahkan Lanjutkan Ke Scoring", Toast.LENGTH_LONG).show();
            AppUtil.notifinfo(this,findViewById(android.R.id.content),"Pembiayaan Beragun SK, Silahkan Lanjutkan Ke Scoring");
        }
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
