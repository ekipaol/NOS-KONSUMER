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

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.adapter.menu.SubmenuHotprospekKprAdapter;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryHotprospek;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.config.menu.Menu;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.AoActivityHotprospekDetailKprBinding;
import com.application.bris.ikurma_nos_konsumer.listener.menu.MenuClickListener;
import com.application.bris.ikurma_nos_konsumer.model.menu.ListViewSubmenuHotprospek;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSKirimPutusanKpr;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUpdateIdLokasi;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.HotprospekKpr;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan.AgunanTerikatActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.data_finansial.DataFinansialKprActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap.kpr.DataLengkapKprActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.history.HistoryActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.kelengkapandokumen.kpr.KonsumerKprFlppKelengkapanDokumenActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.kelengkapandokumen.kpr.KonsumerKprKaryawanPnsKelengkapanDokumenActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.kelengkapandokumen.kpr.KonsumerKprWiraswastaKelengkapanDokumenActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.prescreening.PrescreeningKprActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.scoring_konsumer.ScoringKprActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.sektorekonomi.kpr.SektorEkonomiKprActivity;
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

public class HotprospekDetailKprActivity extends AppCompatActivity implements MenuClickListener, ConfirmListener{

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
    @BindView(R.id.btn_maintain_id_lokasi)
    Button btn_maintain_id_lokasi;

    private List<ListViewSubmenuHotprospek> dataMenu;
    private SubmenuHotprospekKprAdapter submenuHotprospekAdapter;
    private GridLayoutManager layoutMenu;
    private int coloumMenu = 3;
    public static int idAplikasi;
    private HotprospekKpr data;
    private HotprospekKpr dataHp;
    private String dataString;
    private Bitmap bitmapPhoto;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private AoActivityHotprospekDetailKprBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_hotprospek_detail_kpr);
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
                BSKirimPutusanKpr.display(getSupportFragmentManager(), HotprospekDetailKprActivity.this, data);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_send.setEnabled(true);
                    }
                }, 5000);
            }
        });

        btn_maintain_id_lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BSUpdateIdLokasi.display(getSupportFragmentManager(), HotprospekDetailKprActivity.this, data);
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
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryHotprospekKpr(req);
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
                            data = gson.fromJson(dataString, HotprospekKpr.class);
                            String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + data.getFid_photo();
                            Glide
                                    .with(HotprospekDetailKprActivity.this)
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
                            if(data.getPROGRAM_NAME()!=null){ tv_produk.setText(data.getPROGRAM_NAME());
                                tv_produk.setText(data.getPROGRAM_NAME());
                            }
                            else{
                                tv_produk.setText(data.getNama_produk());
                            }

                            tv_plafond.setText(AppUtil.parseRupiah(Long.toString(data.getPlafond_induk())));
                            tv_status.setText(data.getStatus_aplikasi());
                            tv_idaplikasi.setText(Integer.toString(data.getId_aplikasi()));
                            tv_cifsyiar.setText(Integer.toString(data.getFid_cif_las()));
                            tv_tujuanpenggunaan.setText(data.getNama_tujuan());
                            initializeMenu();
                            enableButtonSend();
                            enableMaintainIdLokasiCheck();

                        }
                        else {
                            AppUtil.notiferror(HotprospekDetailKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
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
                        AppUtil.notiferror(HotprospekDetailKprActivity.this, findViewById(android.R.id.content), error.getMessage());
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
                AppUtil.notiferror(HotprospekDetailKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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
        submenuHotprospekAdapter = new SubmenuHotprospekKprAdapter(this, dataMenu, dataHp,this);
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
            if (flagPrescreening == 1 && flagDataLengkap == 1 && flagSektorEkonomi == 1  && flagAgunan == 1 && flagFinansial == 1  &&     flagKelengkapanData == 1 && flagScoring == 1){
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

    private void enableMaintainIdLokasiCheck(){
        if(data.getID_PROGRAM()!=null&&data.getID_PROGRAM().equalsIgnoreCase("222")&&data.getId_st_aplikasi() == -165){
            btn_maintain_id_lokasi.setVisibility(View.VISIBLE);
            btn_maintain_id_lokasi.getBackground().setColorFilter(getResources().getColor(R.color.blue_btn_bg_color), PorterDuff.Mode.SRC_ATOP);
            btn_maintain_id_lokasi.setEnabled(true);
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
                Intent it = new Intent(HotprospekDetailKprActivity.this, HotprospekEditKprActivity.class);
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
        Menu.SubmenuHotprospekKpr(this, menu);
        return menu;
    }

    @Override
    public void onMenuClick(String menu) {
        if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_prescreening))){
            Intent it = new Intent(this, PrescreeningKprActivity.class);
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("kodeProduct", Integer.toString(data.getId_tujuan()));
            it.putExtra("approved", "no");
            it.putExtra("flagMemoSales",data.getFlag_memo_sales());
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_datalengkap))){
            Intent it=new Intent(HotprospekDetailKprActivity.this,DataLengkapKprActivity.class) ;

            // kirim id gimmick ke halaman dataLengkap
            if(data.getID_PROGRAM()!=null){
                it.putExtra("idProgram", data.getID_PROGRAM());
            }

            it.putExtra("cif", String.valueOf(data.getFid_cif_las()));
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("plafond", data.getPlafond_induk());
            it.putExtra("approved", "no");
            it.putExtra("gimmick", String.valueOf(data.getGIMMICK_ID()));
            it.putExtra("dataHotprospek",data);
            startActivity(it);
        }

        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_history))){
            Intent it = new Intent(this, HistoryActivity.class);
            it.putExtra("cif", String.valueOf(data.getFid_cif_las()));
            it.putExtra("idAplikasi", data.getId_aplikasi());
            startActivity(it);
        }

        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_sektorekonomi))){
            Intent it = new Intent(this, SektorEkonomiKprActivity.class);

            // kirim id gimmick
            if(data.getID_PROGRAM()!=null){
                it.putExtra("idProgram", data.getID_PROGRAM());
            }

            it.putExtra("idTujuan", data.getId_tujuan());
            it.putExtra("namaTujuan", data.getNama_tujuan());
            it.putExtra("cifLas", data.getFid_cif_las());
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("approved", "no");
            it.putExtra("loanType", data.getID_PROGRAM());
            it.putExtra("gimmick", String.valueOf(data.getGIMMICK_ID()));
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase("data finansial")){
            Intent it = new Intent(this, DataFinansialKprActivity.class);

            // kirim id gimmick ke halaman data finansial
            if(data.getID_PROGRAM()!=null){
                it.putExtra("idProgram", data.getID_PROGRAM());
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

            if(data.getKlasifikasi_kredit().equalsIgnoreCase("wiraswasta")){
                it = new Intent(this, KonsumerKprWiraswastaKelengkapanDokumenActivity.class);
            }
            else {
                it = new Intent(this, KonsumerKprKaryawanPnsKelengkapanDokumenActivity.class);
            }

            if(data.getID_PROGRAM()!=null){


                if(data.getID_PROGRAM().equalsIgnoreCase("222")){
                    it = new Intent(this, KonsumerKprFlppKelengkapanDokumenActivity.class);
                }

                it.putExtra("idProgram", data.getID_PROGRAM());
            }


            //replace intent dengan activity ke kelas FLPP jika FLPP

            it.putExtra("gimmick", String.valueOf(data.getGIMMICK_ID()));
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("kodeProduct", data.getKode_produk());
            it.putExtra("idTujuan", data.getId_tujuan());
            it.putExtra("plafond", data.getPlafond_induk());
            it.putExtra("klasifikasiKredit", data.getKlasifikasi_kredit());
            it.putExtra("approved", "no");





            startActivity(it);
        }

        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_scoring))){
            Intent it = new Intent(this, ScoringKprActivity.class);

            // kirim id gimmick ke halaman scoring
            if(data.getID_PROGRAM()!=null){
                it.putExtra("idProgram", data.getID_PROGRAM());
            }
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("cif", data.getFid_cif_las());
            it.putExtra("kodeProduct", data.getKode_produk());
            it.putExtra("approved", "no");
            startActivity(it);
        }

        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_agunan))){
            Intent it = new Intent(this, AgunanTerikatActivity.class);
            it.putExtra("cif", String.valueOf(data.getFid_cif_las()));
            it.putExtra("idAplikasi", String.valueOf(data.getId_aplikasi()));
            it.putExtra("jenisAo", "pemrakarsa");
            it.putExtra("flagAgunan", data.getFlag_agunan());


            // kirim id gimmick ke halaman agunan
            if(data.getID_PROGRAM()!=null){
                it.putExtra("idProgram", data.getID_PROGRAM());
            }

            appPreferences.setStatusAplikasiAgunan(Integer.toString(data.getId_st_aplikasi()));
            startActivity(it);

//            Toast.makeText(this, "Tidak Ada Data Agunan, Silahkan Lanjutkan Ke Scoring", Toast.LENGTH_LONG).show();
//            AppUtil.notifinfo(this,findViewById(android.R.id.content),"Pembiayaan Beragun SK, Silahkan Lanjutkan Ke Scoring");
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

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent it = new Intent(HotprospekDetailKprActivity.this, HotprospekKprActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(it);
    }
}
