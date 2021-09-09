package com.application.bris.ikurma_nos_konsumer.view.corelayout.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.application.bris.ikurma_nos_konsumer.database.pojo.PesanDashboardPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.HotprospekKpr;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.PipelineKpr;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.appraisal.AppraisalActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.feedback.FeedbackActivity;
import com.application.bris.ikurma_nos_konsumer.page_monitoring.monitoring_pencairan.MonitoringPencairanActivity;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.login.LoginActivity;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.login.LoginActivity2;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.menu.MenuFlppActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.fragment.app.Fragment;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.adapter.hotprospek.HotprospekHomeAdapater;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.general.home;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.config.menu.Menu;
import com.application.bris.ikurma_nos_konsumer.adapter.menu.MenuAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.listener.menu.MenuClickListener;
import com.application.bris.ikurma_nos_konsumer.model.menu.ListViewMenu;
import com.application.bris.ikurma_nos_konsumer.adapter.pipeline.PipelineHomeAdapater;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.HotprospekListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.PipelineListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.approved.ApprovedActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.HotprospekDetailActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.pipeline.KonsumerKMGPipelineDetailActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.rejected.RejectedActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppBarStateChangedListener;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.menu.MenuPutusanKonsumerActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PID on 3/29/2019.
 */

public class FragmentHome extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, MenuClickListener, PipelineListener, HotprospekListener {
    private View view;

    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbartitle)
    TextView tv_toolbartitle;
    @BindView(R.id.rv_menu)
    RecyclerView rv_menu;
    @BindView(R.id.rv_pipeline)
    RecyclerView rv_pipeline;
    @BindView(R.id.rv_hotprospek)
    RecyclerView rv_hotprospek;
    @BindView(R.id.progress_menu)
    RelativeLayout progress_menu;
    @BindView(R.id.sm_placeholder_pipeline)
    ShimmerFrameLayout sm_placeholder_pipeline;
    @BindView(R.id.sm_placeholder_hotprospek)
    ShimmerFrameLayout sm_placeholder_hotprospek;
    @BindView(R.id.ll_emptydata_pipeline)
    LinearLayout ll_emptydata_pipeline;
    @BindView(R.id.ll_pipeline)
    LinearLayout ll_pipeline;
    @BindView(R.id.ll_hotprospek)
    LinearLayout ll_hotprospek;
    @BindView(R.id.ll_pesan_dashboard)
    LinearLayout ll_pesan_dashboard;
    @BindView(R.id.ll_emptydata_hotprospek)
    LinearLayout ll_emptydata_hotprospek;
    @BindView(R.id.iv_morepipeline)
    ImageView iv_morepipeline;
    @BindView(R.id.iv_morehotprospek)
    ImageView iv_morehotprospek;
    @BindView(R.id.iv_profilpicture)
    ImageView iv_profilpicture;
    @BindView(R.id.tv_nama)
    TextView tv_nama;
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.tv_pesan_dashboard)
    TextView tv_pesan_dashboard;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<ListViewMenu> dataMenu;
    private List<PipelineKpr> dataPipeline;
    private int jumlahPipeline = 0;
    private List<HotprospekKpr> dataHotprospek;
    private int jumlahHotprospek;
    private int jumlahApproved;
    private int jumlahRejected;
    private MenuAdapter adapterMenu;
    private PipelineHomeAdapater adapaterPipeline;
    private HotprospekHomeAdapater adapterHotprospek;
    private GridLayoutManager layoutMenu;
    private int coloumMenu = 4;
    private LinearLayoutManager layoutPipelineHome;
    private LinearLayoutManager layoutHotprospekHome;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        try {
            view = inflater.inflate(R.layout.fragment_home, container, false);
        }
        catch (Exception e){
            e.printStackTrace();
//            Log.e("Error yaw", e);
        }



        ButterKnife.bind(this, view);
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());
        collapsing_toolbar.setStatusBarScrimColor(getResources().getColor(R.color.colorBackgroundTransparent));
//        backgroundStatusBar();
        checkCollapse();

        //disable onlick for AO NPF
        if(appPreferences.getFidRole()!=123){
            iv_morepipeline.setOnClickListener(this);
            iv_morehotprospek.setOnClickListener(this);
        }

//        swipeRefreshLayout.setOnRefreshListener(this);


//        appPreferences.setUpdateNotification("true");


        //ubah nilai versi notifikasi disini dengan nilai baru, jika ada notifikasi baru/update baru
        //update 1.3.0 versi notifikasi 1 ( ubah jadi 2 atau nilai lain di versi selanjutnya
        String versiNotifikasi="6";
        if(!appPreferences.getNotificationVersion().equalsIgnoreCase(versiNotifikasi)){
            appPreferences.setNotificationVersion(versiNotifikasi);
            appPreferences.setUpdateNotification("true");
        }


        if(appPreferences.isUpdateNotificationOn().equalsIgnoreCase("true")){

            //MENAMPILKAN CHANGELOG UNTUK UPDATE TERBARU KORMA
            CustomDialog.DialogUpdateInformation(getContext(), "Fitur Terbaru i-Kurma Konsumer","- Penyesuaian di halaman pipeline, data lengkap, dan prescreening untuk pengecekan dukcapil");
        }



        loadProfil();
        loadData();
        initializeMenu();
        initializePipelineHome();
        initializeHotprospekHome();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        swipeRefreshLayout.setOnRefreshListener(this);

        //di hide dulu di on resume karena berat bro

//        loadProfil();
//        loadDataTop();
//        initializeMenu();
//        initializePipelineHome();
//        initializeHotprospekHome();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void loadProfil() {

        if (appPreferences.getImageProfilBase64().equalsIgnoreCase("")){
            String urlPhoto = UriApi.Baseurl.URL+UriApi.foto.urlPhotoProfil+appPreferences.getNik();
            Glide
                    .with(getContext())
                    .asBitmap()
                    .load(urlPhoto)
                    .centerCrop()
                    .placeholder(R.drawable.banner_placeholder)
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            iv_profilpicture.setImageBitmap(resource);
                            appPreferences.setImageProfilBase64(AppUtil.encodeImageTobase64(resource));
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
        }
        else{
            Glide
                    .with(getContext())
                    .asBitmap()
                    .load(AppUtil.decodeImageTobase64(appPreferences.getImageProfilBase64()))
                    .centerCrop()
                    .placeholder(R.drawable.banner_placeholder)
                    .into(iv_profilpicture);
        }

        tv_nama.setText(appPreferences.getNama());
        tv_username.setText(appPreferences.getJabatan()+", "+appPreferences.getNamaKantor());
    }

    public void loadData(){
        progress_menu.setVisibility(View.VISIBLE);
        sm_placeholder_pipeline.startShimmer();
        sm_placeholder_hotprospek.startShimmer();

        //pantekan
//        home req = new home(941231, appPreferences.getKodeKantor());

        //real data
        home req = new home(appPreferences.getUid(), appPreferences.getKodeKantor());
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().home(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                rv_hotprospek.setVisibility(View.VISIBLE);
                rv_pipeline.setVisibility(View.VISIBLE);
                progress_menu.setVisibility(View.GONE);
                sm_placeholder_pipeline.stopShimmer();
                sm_placeholder_pipeline.setVisibility(View.GONE);
                sm_placeholder_hotprospek.stopShimmer();
                sm_placeholder_hotprospek.setVisibility(View.GONE);

                try {
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){

                            //BAIKLAH BAPAK DAN IBU SEKALIAN, berikut saya jelaskan maksud kode dibawah ya

                            //jika dari DB dapet lemparan pesan dashboard dan id pesan, maka jalankan
                            if(response.body().getData().get("pesanDashboard")!=null&&response.body().getData().get("idPesan")!=null&&!response.body().getData().get("pesanDashboard").getAsString().isEmpty()){

                                //ambil id pesan disini biar kodingan gak memanjang ke samping
                                int idPesan=response.body().getData().get("idPesan").getAsInt();

                                //pantekan testing
//                                int idPesan=21;

                                //mulai realm karena ini pesan bakal kite simpen
                                Realm realm=Realm.getDefaultInstance();

                                //query apakah id pesan ini sudah ada di realm apa belum
                                PesanDashboardPojo dataRealm=realm.where(PesanDashboardPojo.class).equalTo("idPesan",idPesan).findFirst();

                                //kalo belum ada, maka kita insert kedalam realm, lalu tampilkan pesannya
                                if(dataRealm==null){
                                    PesanDashboardPojo newDataRealm=new PesanDashboardPojo();
                                    realm.beginTransaction();
                                    newDataRealm.setIsiPesan(response.body().getData().get("pesanDashboard").getAsString());
                                    newDataRealm.setIdPesan(idPesan);
                                    newDataRealm.setPesanAktif(true);
                                    realm.insertOrUpdate(newDataRealm);
                                    realm.commitTransaction();
                                    realm.close();
                                    AppUtil.notifInfoDashboard(getContext(),getActivity().findViewById(android.R.id.content),response.body().getData().get("pesanDashboard").getAsString(),idPesan);
                                }

                                //kalo udah ada datanya, maka cek apakah statusnya aktif apa enggak nih
                                //status aktif enggaknya pesan tergantung si user ngeklik tombol "hilangkan" gak di snackbarnya
                                //kalau dia udah pernah klik "hilangkan, maka pesan dengan id ini ga akan muncul lagi
                                //jadi untuk memunculkan pesan baru, id pesannya juga harus baru
                                else{
                                    if (dataRealm.isPesanAktif()){
                                        AppUtil.notifInfoDashboard(getContext(),getActivity().findViewById(android.R.id.content),response.body().getData().get("pesanDashboard").getAsString(),idPesan);
                                    }

                                }

//                                AppUtil.notifinfoLong(getContext(),getActivity().findViewById(android.R.id.content),response.body().getData().get("pesanDashboard").getAsString());
                            }

                            if (response.body().getData().get("jlhPipeline").getAsInt() > 0){
                                Gson gson = new Gson();
                                Type typePipeline = new TypeToken<List<PipelineKpr>>() {}.getType();
                                dataPipeline = gson.fromJson(response.body().getData().get("listPipeline").toString(), typePipeline);
                                if (dataPipeline.size() > 0){
                                    ll_emptydata_pipeline.setVisibility(View.GONE);
                                    initializePipelineHome();
                                }
                                else  {
                                    ll_emptydata_pipeline.setVisibility(View.VISIBLE);
                                }

                                jumlahPipeline = response.body().getData().get("jlhPipeline").getAsInt();
                                dataMenu.get(0).setJmlPipeline(jumlahPipeline);
                                adapterMenu.notifyItemChanged(0);
                            }
                            if (response.body().getData().get("jlhHotProspek").getAsInt() > 0){
                                Gson gson = new Gson();
                                Type typeHotprospek = new TypeToken<List<HotprospekKpr>>() {}.getType();
                                dataHotprospek = gson.fromJson(response.body().getData().get("listHotProspek").toString(), typeHotprospek);
                                if (dataHotprospek.size() > 0){
                                    ll_emptydata_hotprospek.setVisibility(View.GONE);
                                    initializeHotprospekHome();
                                }
                                else  {
                                    ll_emptydata_hotprospek.setVisibility(View.VISIBLE);
                                }

                                jumlahHotprospek = response.body().getData().get("jlhHotProspek").getAsInt();
                                dataMenu.get(1).setJmlHotprospek(jumlahHotprospek);
                                adapterMenu.notifyItemChanged(1);
                            }
                            if (response.body().getData().get("jlhApproved").getAsInt() > 0){
                                jumlahApproved = response.body().getData().get("jlhApproved").getAsInt();
                                dataMenu.get(2).setJmlApproved(jumlahApproved);
                                adapterMenu.notifyItemChanged(2);
                            }
                            if (response.body().getData().get("jlhRejected").getAsInt() > 0){
                                jumlahRejected = response.body().getData().get("jlhRejected").getAsInt();
                                dataMenu.get(3).setJmlRejected(jumlahRejected);
                                adapterMenu.notifyItemChanged(3);
                            }

                        }
                        else if(response.body().getStatus().equalsIgnoreCase("01")){
                            ll_pipeline.setVisibility(View.GONE);
                            ll_hotprospek.setVisibility(View.GONE);
                            ll_pesan_dashboard.setVisibility(View.VISIBLE);
                            tv_pesan_dashboard.setText(response.body().getMessage());

                        }
                        else {
                            AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                progress_menu.setVisibility(View.GONE);
                sm_placeholder_pipeline.stopShimmer();
                sm_placeholder_pipeline.setVisibility(View.GONE);
                sm_placeholder_hotprospek.stopShimmer();
                sm_placeholder_hotprospek.setVisibility(View.GONE);
                AppUtil.showToastShort(getContext(), getString(R.string.txt_connection_failure));
            }
        });

    }

    public void initializeMenu(){
        rv_menu.setHasFixedSize(true);
        dataMenu = getListMenu();
        adapterMenu = new MenuAdapter(getContext(), dataMenu, this);
        layoutMenu = new GridLayoutManager(getContext(), coloumMenu);
        rv_menu.setLayoutManager(layoutMenu);
        rv_menu.setAdapter(adapterMenu);
        ViewCompat.setNestedScrollingEnabled(rv_menu, false);
    }

    private List<ListViewMenu> getListMenu() {
        List<ListViewMenu> menu = new ArrayList<>();

        //kalau ao NPF, hanya bisa akses menu untuk monitoring saja
//        Log.d("logfidrole",String.valueOf(appPreferences.getFidRole()));
        if(appPreferences.getFidRole()==123){
            Menu.mainMenuAONpf(getContext(), menu);
        }
        else{
            Menu.mainMenuAO(getContext(), menu);
        }
        return menu;
    }

    public void initializePipelineHome(){
        adapaterPipeline = new PipelineHomeAdapater(getContext(), dataPipeline, this);
        layoutPipelineHome = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_pipeline.setLayoutManager(layoutPipelineHome);
        rv_pipeline.setAdapter(adapaterPipeline);
        ViewCompat.setNestedScrollingEnabled(rv_pipeline, false);

        if (dataPipeline == null){
            ll_emptydata_pipeline.setVisibility(View.VISIBLE);
        }
    }

    public void initializeHotprospekHome(){
        adapterHotprospek = new HotprospekHomeAdapater(getContext(), dataHotprospek, this);
        layoutHotprospekHome = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_hotprospek.setLayoutManager(layoutHotprospekHome);
        rv_hotprospek.setAdapter(adapterHotprospek);
        ViewCompat.setNestedScrollingEnabled(rv_hotprospek, false);
        if (dataHotprospek == null){
            ll_emptydata_hotprospek.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onMenuClick(String menu) {

        if (menu.equalsIgnoreCase(getString(R.string.menu_pipeline))){
//            Intent it = new Intent(getContext(), KonsumerKMGPipelineActivity.class);
//            startActivity(it);
            Intent intent=new Intent(getContext(),MenuPutusanKonsumerActivity.class);
            intent.putExtra("jenisMenu","pipeline");
            startActivity(intent);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.menu_hotprospek))){
//            Intent it = new Intent(getContext(), HotprospekActivity.class);
//            startActivity(it);

            Intent intent=new Intent(getContext(),MenuPutusanKonsumerActivity.class);
            intent.putExtra("jenisMenu","hotprospek");
            startActivity(intent);


        }
        else if (menu.equalsIgnoreCase(getString(R.string.menu_approved))){
            Intent it = new Intent(getContext(), ApprovedActivity.class);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.menu_rejected))){
            Intent it = new Intent(getContext(), RejectedActivity.class);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.menu_appraisal))){
            Intent it = new Intent(getContext(), AppraisalActivity.class);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase("monitoring")){
            Intent it = new Intent(getContext(), MonitoringPencairanActivity.class);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase("flpp")){
            Intent it = new Intent(getContext(), MenuFlppActivity.class);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase("feedback")){
            Intent it = new Intent(getContext(), FeedbackActivity.class);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase("logout")){
            final SweetAlertDialog logout=new SweetAlertDialog(getContext(),SweetAlertDialog.WARNING_TYPE);
            logout.setCanceledOnTouchOutside(false);
            logout.setTitleText("Keluar");
            logout.setContentText("Apakah anda yakin ingin log out dari aplikasi?");
            logout.setConfirmText("Ya");
            logout.setCancelText("Batal");
            logout.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    logout.dismissWithAnimation();
//                    getActivity().finish();
                    if(appPreferences.getNama().equalsIgnoreCase("developer")){
                        Intent intent=new Intent(getContext(), LoginActivity2.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(intent);
                    }
                    else{
                        Intent intent=new Intent(getContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(intent);
                    }

                }
            });
            logout.show();
        }
        else{
            AppUtil.showToastShort(getContext(), menu);
        }
    }

    private void checkCollapse(){
        appbar.addOnOffsetChangedListener(new AppBarStateChangedListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state.name().equalsIgnoreCase("COLLAPSED") || state.name().equalsIgnoreCase("IDLE")){
                    tv_toolbartitle.setText("i-Kurma Konsumer");
                    backgroundStatusBar(state.name());
                    swipeRefreshLayout.setEnabled(false);
                }
                else {
                    backgroundStatusBar(state.name());
                    tv_toolbartitle.setText("");
                }
            }
        });
    }

    private void backgroundStatusBar(){
        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorBackgroundTransparent));
        }
    }

    private void backgroundStatusBar(String state){
        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                if (state.equalsIgnoreCase("COLLAPSED") || state.equalsIgnoreCase("IDLE")){
                    window.setStatusBarColor(getActivity().getResources().getColor(R.color.colorWhite));
                }
                else {
                    window.setStatusBarColor(getActivity().getResources().getColor(R.color.colorBackgroundTransparent));
                    swipeRefreshLayout.setEnabled(true);
                    swipeRefreshLayout.setDistanceToTriggerSync(220);
                }
            }

        }


    @Override
    public void onPipelineSelect(int id) {
        Intent it = new Intent(getContext(), KonsumerKMGPipelineDetailActivity.class);
        it.putExtra("idPipeline", id);
        startActivity(it);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getContext(),MenuPutusanKonsumerActivity.class);
        switch (v.getId()){

            case R.id.iv_morepipeline:
//                moreActivity(PipelineActivity.class);
                intent.putExtra("jenisMenu","pipeline");
                startActivity(intent);
//                moreActivity(MenuPutusanKonsumerActivity.class);
                break;
            case R.id.iv_morehotprospek:
                intent.putExtra("jenisMenu","hotprospek");
                startActivity(intent);
//                moreActivity(MenuPutusanKonsumerActivity.class);
                break;
        }
    }

    @Override
    public void onHotprospekSelect(int id) {

        //ini gak dipake ya sebenernya, karena onclicknya udah dipasang di adapter
        Intent it = new Intent(getContext(), HotprospekDetailActivity.class);
        it.putExtra("idAplikasi", id);
        startActivity(it);
    }

    public void moreActivity(Class<?> className){
        Intent it = new Intent(getContext(), className);
        startActivity(it);
    }

    @Override
    public void onRefresh() {
        initializeMenu();
        swipeRefreshLayout.setRefreshing(false);
        sm_placeholder_hotprospek.setVisibility(View.VISIBLE);
        sm_placeholder_pipeline.setVisibility(View.VISIBLE);
        rv_hotprospek.setVisibility(View.GONE);
        rv_pipeline.setVisibility(View.GONE);
        checkCollapse();
        loadProfil();
        loadData();
//        initializeMenu();
        initializePipelineHome();
        initializeHotprospekHome();

//        swipeRefreshLayout.setOnRefreshListener(this);

//        getActivity().recreate();
    }
}
