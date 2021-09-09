package com.application.bris.ikurma_nos_konsumer.view.corelayout.user;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.application.bris.ikurma_nos_konsumer.page_aom.view.pipeline.KonsumerKMGPipelineActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.util.AppBarStateChangedListener;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by idong on 05/28/2019.
 */

public class FragmentUser extends Fragment implements View.OnClickListener{
    private View view;

    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbartitle)
    TextView tv_toolbartitle;
    @BindView(R.id.iv_profile)
    ImageView iv_profile;
    @BindView(R.id.tv_nama)
    TextView tv_nama;
    @BindView(R.id.tv_nik_jabatankantor)
    TextView tv_nik_kodeao;
    @BindView(R.id.tv_nik)
    TextView tv_jabatan;
    @BindView(R.id.tv_kodeao)
    TextView tv_kantor;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);

        ButterKnife.bind(this, view);
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());
        AppUtil.customMainFonts(getContext(), view, R.font.muli_b);
        collapsing_toolbar.setStatusBarScrimColor(getResources().getColor(R.color.colorBackgroundTransparent));
        backgroundStatusBar();
        checkCollapse();
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
        appbar.addOnOffsetChangedListener(new AppBarStateChangedListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state.name().equalsIgnoreCase("COLLAPSED") || state.name().equalsIgnoreCase("IDLE")){
                    tv_toolbartitle.setText("PROFIL");
                }
                else {
                    tv_toolbartitle.setText("");
                }
            }
        });
    }


    private void setData() {
        String urlPhoto = UriApi.Baseurl.URL+UriApi.foto.urlPhotoProfil+appPreferences.getNik();
        Glide
                .with(getContext())
                .load(urlPhoto)
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .into(iv_profile);

        tv_nama.setText(appPreferences.getNama());
        tv_nik_kodeao.setText(appPreferences.getJabatan()+", "+appPreferences.getNamaKantor());
        tv_jabatan.setText(appPreferences.getNik());
        tv_kantor.setText(appPreferences.getKodeAo());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_morepipeline:
                Intent it = new Intent(getContext(), KonsumerKMGPipelineActivity.class);
                startActivity(it);
                break;
        }
    }
}
