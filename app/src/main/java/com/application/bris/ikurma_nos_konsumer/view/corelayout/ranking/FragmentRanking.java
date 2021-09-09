package com.application.bris.ikurma_nos_konsumer.view.corelayout.ranking;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.model.request.monitoring.ReqRankingAo;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.model.monitoring.AoRanking;
import com.application.bris.ikurma_nos_konsumer.model.monitoring.RataRataMonitoring;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.pipeline.KonsumerKMGPipelineActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentRanking extends Fragment implements View.OnClickListener{
    private View view;


    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;

    @BindView(R.id.container)
    RelativeLayout rootView;

    @BindView(R.id.iv_1st_place_pic)
    ImageView iv_1st_place_pic;
    @BindView(R.id.iv_2nd_place_pic)
    ImageView iv_2nd_place_pic;
    @BindView(R.id.iv_3rd_place_pic)
    ImageView iv_3rd_place_pic;


    @BindView(R.id.iv_1st_place_pic_aom)
    ImageView iv_1st_place_pic_aom;
    @BindView(R.id.iv_2nd_place_pic_aom)
    ImageView iv_2nd_place_pic_aom;
    @BindView(R.id.iv_3rd_place_pic_aom)
    ImageView iv_3rd_place_pic_aom;

    @BindView(R.id.tv_firstplace_amount)
    TextView tv_firstplace_amount;
    @BindView(R.id.tv_secondplace_amount)
    TextView tv_secondplace_amount;
    @BindView(R.id.tv_thirdplace_amount)
    TextView tv_thirdplace_amount;

    @BindView(R.id.tv_firstplace_amount_aom)
    TextView tv_firstplace_amount_ao_worst;
    @BindView(R.id.tv_secondplace_amount_aom)
    TextView tv_secondplace_amount_ao_worst;
    @BindView(R.id.tv_thirdplace_amount_aom)
    TextView tv_thirdplace_amount_ao_worst;

    @BindView(R.id.et_nama_1st_place)
    TextView et_nama_1st_place;
    @BindView(R.id.et_nama_2nd_place)
    TextView et_nama_2nd_place;
    @BindView(R.id.et_nama_3rd_place)
    TextView et_nama_3rd_place;

    @BindView(R.id.et_nama_1st_place_aom)
    TextView et_nama_1st_place_ao_worst;
    @BindView(R.id.et_nama_2nd_place_aom)
    TextView et_nama_2nd_place_ao_worst;
    @BindView(R.id.et_nama_3rd_place_aom)
    TextView et_nama_3rd_place_ao_worst;

    @BindView(R.id.et_nama_kc_1st_place)
    TextView et_nama_kc_1st_place;
    @BindView(R.id.et_nama_kc_2nd_place)
    TextView et_nama_kc_2nd_place;
    @BindView(R.id.et_nama_kc_3rd_place)
    TextView et_nama_kc_3rd_place;

    @BindView(R.id.et_nama_kc_1st_place_aom)
    TextView et_nama_kc_1st_place_ao_worst;
    @BindView(R.id.et_nama_kc_2nd_place_aom)
    TextView et_nama_kc_2nd_place_ao_worst;
    @BindView(R.id.et_nama_kc_3rd_place_aom)
    TextView et_nama_kc_3rd_place_ao_worst;

    @BindView(R.id.rv_top10_ao)
    RecyclerView rv_top10_ao;

    @BindView(R.id.rv_top10_aom)
    RecyclerView rv_top10_aom;

    @BindView(R.id.rv_bottom10_ao)
    RecyclerView rv_bottom10_ao;

    @BindView(R.id.rv_bottom10_aom)
    RecyclerView rv_bottom10_aom;


    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    @BindView(R.id.cv_banner_top10_ao)
    CardView cv_banner_top10_ao;

    @BindView(R.id.cv_banner_top10_aom)
    CardView cv_banner_top10_aom;

    @BindView(R.id.cv_banner_bottom10_ao)
    CardView cv_banner_bottom10_ao;

    @BindView(R.id.cv_banner_bottom10_aom)
    CardView cv_banner_bottom10_aom;

    @BindView(R.id.ll_content_top10_ao)
    LinearLayout ll_content_top10_ao;

    @BindView(R.id.ll_content_top10_aom)
    LinearLayout ll_content_top10_aom;

    @BindView(R.id.ll_content_bottom10_ao)
    LinearLayout ll_content_bottom10_ao;

    @BindView(R.id.ll_content_bottom10_aom)
    LinearLayout ll_content_bottom10_aom;

    @BindView(R.id.id_show_more_top10_ranking_ao)
    ImageView id_show_more_top10_ranking_ao;
    @BindView(R.id.iv_show_less_top10_ranking_ao)
    ImageView iv_show_less_top10_ranking_ao;

    @BindView(R.id.id_show_more_top10_ranking_aom)
    ImageView id_show_more_top10_ranking_aom;
    @BindView(R.id.iv_show_less_top10_ranking_aom)
    ImageView iv_show_less_top10_ranking_aom;

    @BindView(R.id.id_show_more_bottom10_ranking_ao)
    ImageView id_show_more_bottom10_ranking_ao;
    @BindView(R.id.iv_show_less_bottom10_ranking_ao)
    ImageView iv_show_less_bottom10_ranking_ao;

    @BindView(R.id.id_show_more_bottom10_ranking_aom)
    ImageView id_show_more_bottom10_ranking_aom;
    @BindView(R.id.iv_show_less_bottom10_ranking_aom)
    ImageView iv_show_less_bottom10_ranking_aom;

    @BindView(R.id.sv_scroll)
    ScrollView sv_scroll;

    //tab and linear layouts
    @BindView(R.id.ll_data_ranking)
    LinearLayout ll_data_ranking;
    @BindView(R.id.ll_data_total)
    LinearLayout ll_data_total;
    @BindView(R.id.ll_tab_ranking)
    LinearLayout ll_tab_ranking;
    @BindView(R.id.ll_tab_total)
    LinearLayout ll_tab_total;
    @BindView(R.id.tv_tab_ranking)
    TextView tv_tab_ranking;
    @BindView(R.id.tv_tab_total)
    TextView tv_tab_total;

    //FLIPVIEW
    @BindView(R.id.v_easyflip_pencairan)
    EasyFlipView v_easyflip_pencairan;
    @BindView(R.id.v_easyflip_os)
    EasyFlipView v_easyflip_os;
    @BindView(R.id.v_easyflip_npf)
    EasyFlipView v_easyflip_npf;
    @BindView(R.id.v_easyflip_kol2)
    EasyFlipView v_easyflip_kol2;

    //DATA TOTAL
    @BindView(R.id.tv_total_pencairan)
    TextView tv_total_pencairan;
    @BindView(R.id.tv_total_kol2)
    TextView tv_total_kol2;
    @BindView(R.id.tv_total_npf)
    TextView tv_total_npf;
    @BindView(R.id.tv_total_os)
    TextView tv_total_os;

    @BindView(R.id.tv_rata_pencairan)
    TextView tv_rata_pencairan;
    @BindView(R.id.tv_rata_kol2)
    TextView tv_rata_kol2;
    @BindView(R.id.tv_rata_npf)
    TextView tv_rata_npf;
    @BindView(R.id.tv_rata_os)
    TextView tv_rata_os;
    //END OF DATA TOTAL

    @BindView(R.id.tv_info)
    TextView tv_info;




    //ini nanti ganti jadi model data AO ya
    List<AoRanking> dataAo;
    AdapterRanking adapterRankingAo;
    AdapterRanking adapterRankingAom;
    RataRataMonitoring dataRataRata;


    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private int counterLoading=0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ranking_ao, container, false);

        ButterKnife.bind(this, view);
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());
        AppUtil.customMainFonts(getContext(), view, R.font.muli_b);
        collapsing_toolbar.setStatusBarScrimColor(getResources().getColor(R.color.colorBackgroundTransparent));
        backgroundStatusBar();
        checkCollapse();

        cv_banner_top10_ao.setOnClickListener(this);
        cv_banner_top10_aom.setOnClickListener(this);
        cv_banner_bottom10_ao.setOnClickListener(this);
        cv_banner_bottom10_aom.setOnClickListener(this);

        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

        tv_info.setText("Data yang Ditampilkan Merupakan Data Total dan Rata-Rata Per : "+dateFormat.format(cal.getTime()));

        ll_tab_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ll_data_ranking.setVisibility(View.VISIBLE);
                tv_tab_ranking.setTextColor(getResources().getColor(R.color.colorPrimary));

                ll_data_total.setVisibility(View.GONE);
                tv_tab_total.setTextColor(getResources().getColor(R.color.text_color));
            }
        });


        ll_tab_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ll_data_ranking.setVisibility(View.GONE);
                tv_tab_ranking.setTextColor(getResources().getColor(R.color.text_color));

                ll_data_total.setVisibility(View.VISIBLE);
                tv_tab_total.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });

        //load data to recycler views
        loadDataTop("100");
//        loadDataTop("8");
        loadDataBottom("100");
//        loadDataBottom("8");
        loadRataRata();


        onClickFlip(v_easyflip_pencairan);
        onClickFlip(v_easyflip_os);
        onClickFlip(v_easyflip_npf);
        onClickFlip(v_easyflip_kol2);

        setData();
        return view;
    }

    private void backgroundStatusBar(){
        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorBackgroundTransparent));
        }
    }

    private void checkCollapse(){
//        appbar.addOnOffsetChangedListener(new AppBarStateChangedListener() {
//            @Override
//            public void onStateChanged(AppBarLayout appBarLayout, State state) {
//                if (state.name().equalsIgnoreCase("COLLAPSED") || state.name().equalsIgnoreCase("IDLE")){
//                    tv_toolbartitle.setText("PROFIL");
//                }
//                else {
//                    tv_toolbartitle.setText("");
//                }
//            }
//        });
    }


    private void setData() {

        //get screen height
//        Display display = getActivity().getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        int height = Resources.getSystem().getDisplayMetrics().heightPixels-100;


        rootView.post(new Runnable() {
            public void run(){
                Rect rect = new Rect();
                Window win = getActivity().getWindow();
                win.getDecorView().getWindowVisibleDisplayFrame(rect);
                int statusHeight = rect.top;
                int contentViewTop = win.findViewById(Window.ID_ANDROID_CONTENT).getTop();
                int  titleHeight = contentViewTop - statusHeight;
//                Log.w("dimens_tag", "title = " + titleHeight + " status bar = " + statusHeight);

                ViewGroup.LayoutParams params=rv_top10_ao.getLayoutParams();
                params.height=titleHeight;
                rv_top10_ao.setLayoutParams(params);
                rv_top10_aom.setLayoutParams(params);
                rv_bottom10_ao.setLayoutParams(params);
                rv_bottom10_aom.setLayoutParams(params);
            }
        });




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_morepipeline:
                Intent it = new Intent(getContext(), KonsumerKMGPipelineActivity.class);
                startActivity(it);
                break;
            case R.id.cv_banner_top10_ao:
                if(ll_content_top10_ao.getVisibility()==View.GONE){
                    ll_content_top10_ao.setVisibility(View.VISIBLE);
                    id_show_more_top10_ranking_ao.setVisibility(View.GONE);
                    iv_show_less_top10_ranking_ao.setVisibility(View.VISIBLE);
                }
                else{
                    ll_content_top10_ao.setVisibility(View.GONE);
                    id_show_more_top10_ranking_ao.setVisibility(View.VISIBLE);
                    iv_show_less_top10_ranking_ao.setVisibility(View.GONE);
                }
                break;

            case R.id.cv_banner_top10_aom:
                if(ll_content_top10_aom.getVisibility()==View.GONE){
                    ll_content_top10_aom.setVisibility(View.VISIBLE);
                    id_show_more_top10_ranking_aom.setVisibility(View.GONE);
                    iv_show_less_top10_ranking_aom.setVisibility(View.VISIBLE);
                }
                else{
                    ll_content_top10_aom.setVisibility(View.GONE);
                    id_show_more_top10_ranking_aom.setVisibility(View.VISIBLE);
                    iv_show_less_top10_ranking_aom.setVisibility(View.GONE);
                }
                break;

            case R.id.cv_banner_bottom10_ao:
                if(ll_content_bottom10_ao.getVisibility()==View.GONE){
                    ll_content_bottom10_ao.setVisibility(View.VISIBLE);
                    id_show_more_bottom10_ranking_ao.setVisibility(View.GONE);
                    iv_show_less_bottom10_ranking_ao.setVisibility(View.VISIBLE);
                }
                else{
                    ll_content_bottom10_ao.setVisibility(View.GONE);
                    id_show_more_bottom10_ranking_ao.setVisibility(View.VISIBLE);
                    iv_show_less_bottom10_ranking_ao.setVisibility(View.GONE);
                }
                break;

            case R.id.cv_banner_bottom10_aom:
                if(ll_content_bottom10_aom.getVisibility()==View.GONE){
                    ll_content_bottom10_aom.setVisibility(View.VISIBLE);
                    id_show_more_bottom10_ranking_aom.setVisibility(View.GONE);
                    iv_show_less_bottom10_ranking_aom.setVisibility(View.VISIBLE);
                }
                else{
                    ll_content_bottom10_aom.setVisibility(View.GONE);
                    id_show_more_bottom10_ranking_aom.setVisibility(View.VISIBLE);
                    iv_show_less_bottom10_ranking_aom.setVisibility(View.GONE);
                }
                break;



        }
    }

    public void loadDataTop(String fidRole){
        loading.setVisibility(View.VISIBLE);

        //pantekan
//        home req = new home(941231, appPreferences.getKodeKantor());

        //real data
        ReqRankingAo req = new ReqRankingAo();
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().getRankingAoTop(req);
        req.setFidRole(fidRole);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {

                //indikator loading hilang kalau semua data ranking sudah di load/sudah error

                counterLoading++;
                if(counterLoading>=3){
                    loading.setVisibility(View.GONE);
                }

                try {
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){



                                Gson gson = new Gson();
                                Type typePipeline = new TypeToken<List<AoRanking>>() {}.getType();
                                dataAo = gson.fromJson(response.body().getData().toString(), typePipeline);


                                adapterRankingAo = new AdapterRanking(getContext(), dataAo,false,sv_scroll);
                                adapterRankingAom = new AdapterRanking(getContext(), dataAo,false,sv_scroll);

                                if(fidRole.equalsIgnoreCase("100")){
                                    rv_top10_ao.setLayoutManager(new LinearLayoutManager(getContext()));
                                    rv_top10_ao.setAdapter(adapterRankingAo);

                                    //set data top 3 AO
                                    //1st place
                                    et_nama_1st_place.setText(dataAo.get(0).getNAMA_PEGAWAI());
                                    et_nama_kc_1st_place.setText(dataAo.get(0).getNAMA_CABANG());
                                    BigDecimal nilaiPencairan=new BigDecimal(dataAo.get(0).getTOTAL_PENCAIRAN()).divide(new BigDecimal(100)).multiply(new BigDecimal(dataAo.get(0).getTARGET_PENCAIRAN())).setScale(1, RoundingMode.UP);
                                    tv_firstplace_amount.setText(AppUtil.parseRupiah(String.valueOf(nilaiPencairan)));
                                    AppUtil.loadPhotoUserWithCache(getContext(),iv_1st_place_pic,dataAo.get(0).getNO_PEGAWAI());

                                    //2nd place
                                    et_nama_2nd_place.setText(dataAo.get(1).getNAMA_PEGAWAI());
                                    et_nama_kc_2nd_place.setText(dataAo.get(1).getNAMA_CABANG());

                                    BigDecimal nilaiPencairan2=new BigDecimal(dataAo.get(1).getTOTAL_PENCAIRAN()).divide(new BigDecimal(100)).multiply(new BigDecimal(dataAo.get(1).getTARGET_PENCAIRAN())).setScale(1, RoundingMode.UP);
                                    tv_secondplace_amount.setText(AppUtil.parseRupiah(String.valueOf(nilaiPencairan2)));
                                    AppUtil.loadPhotoUserWithCache(getContext(),iv_2nd_place_pic,dataAo.get(1).getNO_PEGAWAI());

                                    //3rd place
                                    et_nama_3rd_place.setText(dataAo.get(2).getNAMA_PEGAWAI());
                                    et_nama_kc_3rd_place.setText(dataAo.get(2).getNAMA_CABANG());

                                    BigDecimal nilaiPencairan3=new BigDecimal(dataAo.get(2).getTOTAL_PENCAIRAN()).divide(new BigDecimal(100)).multiply(new BigDecimal(dataAo.get(2).getTARGET_PENCAIRAN())).setScale(1, RoundingMode.UP);
                                    tv_thirdplace_amount.setText(AppUtil.parseRupiah(String.valueOf(nilaiPencairan3)));
                                    AppUtil.loadPhotoUserWithCache(getContext(),iv_3rd_place_pic,dataAo.get(2).getNO_PEGAWAI());


                                }
                                else if (fidRole.equalsIgnoreCase("8")){
                                    rv_top10_aom.setLayoutManager(new LinearLayoutManager(getContext()));
                                    rv_top10_aom.setAdapter(adapterRankingAom);

                                    //set data top 3 AOM
                                    //1st place
                                    et_nama_1st_place_ao_worst.setText(dataAo.get(0).getNAMA_PEGAWAI());
                                    et_nama_kc_1st_place_ao_worst.setText(dataAo.get(0).getNAMA_CABANG());
                                    tv_firstplace_amount_ao_worst.setText(   AppUtil.formatStringDoubleBelakangKoma(dataAo.get(0).getTOTAL_PENCAIRAN(),1)+"%");
                                    AppUtil.loadPhotoUserWithCache(getContext(),iv_1st_place_pic_aom,dataAo.get(0).getNO_PEGAWAI());

                                    //2nd place
                                    et_nama_2nd_place_ao_worst.setText(dataAo.get(1).getNAMA_PEGAWAI());
                                    et_nama_kc_2nd_place_ao_worst.setText(dataAo.get(1).getNAMA_CABANG());
                                    tv_secondplace_amount_ao_worst.setText(   AppUtil.formatStringDoubleBelakangKoma(dataAo.get(1).getTOTAL_PENCAIRAN(),1)+"%");
                                    AppUtil.loadPhotoUserWithCache(getContext(),iv_2nd_place_pic_aom,dataAo.get(1).getNO_PEGAWAI());

                                    //3rd place
                                    et_nama_3rd_place_ao_worst.setText(dataAo.get(2).getNAMA_PEGAWAI());
                                    et_nama_kc_3rd_place_ao_worst.setText(dataAo.get(2).getNAMA_CABANG());
                                    tv_thirdplace_amount_ao_worst.setText( AppUtil.formatStringDoubleBelakangKoma(dataAo.get(2).getTOTAL_PENCAIRAN(),1)+"%");
                                    AppUtil.loadPhotoUserWithCache(getContext(),iv_3rd_place_pic_aom,dataAo.get(2).getNO_PEGAWAI());

                            }


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
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.showToastShort(getContext(), getString(R.string.txt_connection_failure));
            }
        });

    }

    public void loadDataBottom(String fidRole){
        loading.setVisibility(View.VISIBLE);

        //pantekan
//        home req = new home(941231, appPreferences.getKodeKantor());

        //real data
        ReqRankingAo req = new ReqRankingAo();
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().getRankingAoBottom(req);
        req.setFidRole(fidRole);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {

                //indikator loading hilang kalau semua data ranking sudah di load/sudah error
                counterLoading++;
                if(counterLoading>=3){
                    loading.setVisibility(View.GONE);
                }


                try {
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){



                            Gson gson = new Gson();
                            Type typePipeline = new TypeToken<List<AoRanking>>() {}.getType();
                            dataAo = gson.fromJson(response.body().getData().toString(), typePipeline);


                            adapterRankingAo = new AdapterRanking(getContext(), dataAo,true,sv_scroll);
                            adapterRankingAom = new AdapterRanking(getContext(), dataAo,true,sv_scroll);

                            if(fidRole.equalsIgnoreCase("100")){
                                rv_bottom10_ao.setLayoutManager(new LinearLayoutManager(getContext()));
                                rv_bottom10_ao.setAdapter(adapterRankingAo);

                                rv_top10_aom.setLayoutManager(new LinearLayoutManager(getContext()));
                                rv_top10_aom.setAdapter(adapterRankingAom);

                                //set data bottom 3 AO
                                //1st place
                                et_nama_1st_place_ao_worst.setText(dataAo.get(0).getNAMA_PEGAWAI());
                                et_nama_kc_1st_place_ao_worst.setText(dataAo.get(0).getNAMA_CABANG());
                                BigDecimal nilaiPencairan=new BigDecimal(AppUtil.formatStringDoubleBelakangKoma(dataAo.get(0).getTOTAL_PENCAIRAN(),1)).divide(new BigDecimal(100)).multiply(new BigDecimal(dataAo.get(0).getTARGET_PENCAIRAN())).setScale(1, RoundingMode.UP);
                                tv_firstplace_amount_ao_worst.setText(AppUtil.parseRupiah(String.valueOf(nilaiPencairan)));
                                AppUtil.loadPhotoUserWithCache(getContext(),iv_1st_place_pic_aom,dataAo.get(0).getNO_PEGAWAI());

                                //2nd place
                                et_nama_2nd_place_ao_worst.setText(dataAo.get(1).getNAMA_PEGAWAI());
                                et_nama_kc_2nd_place_ao_worst.setText(dataAo.get(1).getNAMA_CABANG());
                                BigDecimal nilaiPencairan2=new BigDecimal(AppUtil.formatStringDoubleBelakangKoma(dataAo.get(1).getTOTAL_PENCAIRAN(),1)).divide(new BigDecimal(100)).multiply(new BigDecimal(dataAo.get(1).getTARGET_PENCAIRAN())).setScale(1, RoundingMode.UP);
                                tv_secondplace_amount_ao_worst.setText(AppUtil.parseRupiah(String.valueOf(nilaiPencairan2)));

                                AppUtil.loadPhotoUserWithCache(getContext(),iv_2nd_place_pic_aom,dataAo.get(1).getNO_PEGAWAI());

                                //3rd place
                                et_nama_3rd_place_ao_worst.setText(dataAo.get(2).getNAMA_PEGAWAI());
                                et_nama_kc_3rd_place_ao_worst.setText(dataAo.get(2).getNAMA_CABANG());
                                BigDecimal nilaiPencairan3=new BigDecimal(AppUtil.formatStringDoubleBelakangKoma(dataAo.get(2).getTOTAL_PENCAIRAN(),1)).divide(new BigDecimal(100)).multiply(new BigDecimal(dataAo.get(2).getTARGET_PENCAIRAN())).setScale(1, RoundingMode.UP);
                                tv_thirdplace_amount_ao_worst.setText(AppUtil.parseRupiah(String.valueOf(nilaiPencairan3)));

                                AppUtil.loadPhotoUserWithCache(getContext(),iv_3rd_place_pic_aom,dataAo.get(2).getNO_PEGAWAI());


                            }
                            else if (fidRole.equalsIgnoreCase("8")){
                                rv_bottom10_aom.setLayoutManager(new LinearLayoutManager(getContext()));
                                rv_bottom10_aom.setAdapter(adapterRankingAom);


                            }

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
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.showToastShort(getContext(), getString(R.string.txt_connection_failure));
            }
        });

    }

    public void loadRataRata(){
        loading.setVisibility(View.VISIBLE);

        //pantekan
//        home req = new home(941231, appPreferences.getKodeKantor());

        //real data

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().getRataRata(new EmptyRequest());
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                //indikator loading hilang kalau semua data ranking sudah di load/sudah error
                counterLoading++;
                if(counterLoading==3){
                    loading.setVisibility(View.GONE);
                }


                try {
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){



                            Gson gson = new Gson();
                            Type type = new TypeToken<RataRataMonitoring>() {}.getType();
                            dataRataRata = gson.fromJson(response.body().getData().toString(), type);
                            setDataTotal();


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
                loading.setVisibility(View.GONE);
                AppUtil.showToastShort(getContext(), getString(R.string.txt_connection_failure));
            }
        });

    }

    private void setDataTotal(){
        tv_total_pencairan.setText(dataTotalProcesesor(dataRataRata.getTotalPencairan()));
        tv_total_os.setText(dataTotalProcesesor(dataRataRata.getTotalOs()));
        tv_total_kol2.setText(dataTotalProcesesor(dataRataRata.getTotalKol2()));
        tv_total_npf.setText(dataTotalProcesesor(dataRataRata.getTotalNpf()));

        tv_rata_pencairan.setText(dataTotalProcesesor(dataRataRata.getAvrPencairan()));
        tv_rata_os.setText(dataTotalProcesesor(dataRataRata.getAvrOs()));
        tv_rata_kol2.setText(dataTotalProcesesor(dataRataRata.getAvrKol2()));
        tv_rata_npf.setText(dataTotalProcesesor(dataRataRata.getAvrNpf()));
    }

    private String dataTotalProcesesor(String nominal){
        //BAIKLAH REKAN REKAN YANG BERBAHAGIA DAN SAYA CINTAI
        //METHOD INI DIGUNAKAN UNTUK FORMAT NOMINAL DARI SERVIS AGAR MEMILIKI SUFFIX "T(triliun), M(miliard), atau JT(juta)
        //dari servis akan dapat kembalian data nominal, dengan 2 angka dibelakang koma
        //contoh : 460969263.85

        //nilai diatas akan di proses, dengan menghilangkan 2 angka dibelakang komanya
        //menjadi 460969263

        //lalu nilai diatas akan diconvert ke dalam formatter currency, menjadi
        //460,969,263

        //lalu nilai diatas akan di-split dengan melihat numerator koma (","), dan menjadi 3 set data dalam 1 array (460, 969, dan 263)
        //lalu hanya diambil array indeks 0 (460) dan indeks 1 yang diperpendek menjadi 2 digit (96)

        //lalu ditambahkan suffix berdasarkan jumlah digit angka (triliun, miliard, atau juta)

        String formattedString="";
        String removeComma=nominal.substring(0,nominal.length()-3);
        String[] stringCutter;

        Log.d("nominalString",removeComma);

        if(removeComma.length()<=9){
            formattedString=AppUtil.parseRupiahNoSymbol(removeComma);
//            formattedString=formattedString.substring(0,formattedString.length()-3);

            if(formattedString.substring(0,4).contains(",")){
                stringCutter=formattedString.split(",");
            }
            else{
                stringCutter=formattedString.split("\\.");
            }

            Log.d("formattedstring",formattedString);
            return stringCutter[0]+","+stringCutter[1].substring(0,2)+" JT";
        }
        else if(removeComma.length()<=12){
            formattedString=AppUtil.parseRupiahNoSymbol(removeComma);
//            formattedString=formattedString.substring(0,formattedString.length()-3);

            if(formattedString.substring(0,4).contains(",")){
                stringCutter=formattedString.split(",");
            }
            else{
                stringCutter=formattedString.split("\\.");
            }
            Log.d("formattedstring",formattedString);
            return stringCutter[0]+","+stringCutter[1].substring(0,2)+" M";
        }
        else if(removeComma.length()<=15){
            formattedString=AppUtil.parseRupiahNoSymbol(removeComma);
//            formattedString=formattedString.substring(0,formattedString.length()-3);

            if(formattedString.substring(0,4).contains(",")){
                stringCutter=formattedString.split(",");
            }
            else{
                stringCutter=formattedString.split("\\.");
            }
            Log.d("formattedstring",formattedString);
            return stringCutter[0]+","+stringCutter[1].substring(0,2)+" T";
        }
        else{
            return AppUtil.parseRupiahNoSymbol(removeComma);
        }
    }

    private void onClickFlip(EasyFlipView easyFlipView1){
        easyFlipView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyFlipView1.flipTheView();
            }
        });
    }



}
