package com.application.bris.ikurma_nos_konsumer.page_aom.view.pipeline;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
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
import com.application.bris.ikurma_nos_konsumer.api.model.request.ceknasabah.cekNasabah;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.page_aom.adapter.datapembiayaan.DatapembiayaanAdapater;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.datapembiayaan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.datapribadi;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.pipeline.kpr.KonsumerKprPipelineInputActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CeknasabahActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.progressbar_loading)
    RelativeLayout progressbar_loading;

    @BindView(R.id.rv_listpembiayaan)
    RecyclerView rv_listpembiayaan;

    @BindView(R.id.btn_inputpipeline)
    Button btn_inputpipeline;

    @BindView(R.id.ll_info)
    LinearLayout ll_info;
    @BindView(R.id.ll_emptydata)
    LinearLayout ll_emptydata;

    @BindView(R.id.iv_profilpicture)
    ImageView iv_profilpicture;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_cifappel)
    TextView tv_cifappel;
    @BindView(R.id.tv_nomorktp)
    TextView tv_nomorktp;

    List<datapembiayaan> dataPembiayaan;
    DatapembiayaanAdapater datapembiayaanAdapater;
    private ApiClientAdapter apiClientAdapter;
    private String nasabahId;
    private String produk;
    private String dataPribadi = "";
    private String val_urlphoto;
    private Bitmap bitmapPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_ceknasabah);
        main();
        backgroundStatusBar();
        initializeData();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void main(){
        apiClientAdapter = new ApiClientAdapter(this);
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Cek Nasabah");
        btn_inputpipeline.setOnClickListener(this);
        Intent it = getIntent();
        nasabahId = it.getStringExtra("nasabahId");
        produk= it.getStringExtra("produk");
    }

    public void initializeData(){
        progressbar_loading.setVisibility(View.VISIBLE);
        cekNasabah req = new cekNasabah();
        req.setKey(nasabahId);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().cekNasabah(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                progressbar_loading.setVisibility(View.GONE);
                try {
                    if(response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            if(response.body().getData().get("dataPribadi").isJsonNull()){
                                ll_emptydata.setVisibility(View.VISIBLE);
                                ll_info.setVisibility(View.GONE);
                                btn_inputpipeline.setText("Pembiayaan Baru");
                            }
                            else {
                                ll_info.setVisibility(View.VISIBLE);
                                ll_emptydata.setVisibility(View.GONE);
                                btn_inputpipeline.setText("Tambah Pembiayaan Baru");
                                dataPribadi = response.body().getData().get("dataPribadi").toString();
                                Gson gson = new Gson();
                                datapribadi datapribadi = gson.fromJson(dataPribadi, datapribadi.class);
                                val_urlphoto = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + datapribadi.getFid_photo();

                                Glide
                                        .with(CeknasabahActivity.this)
                                        .asBitmap()
                                        .load(val_urlphoto)
                                        .fitCenter()
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                iv_profilpicture.setImageBitmap(resource);
                                                bitmapPhoto = resource;
                                            }
                                        });

                                iv_profilpicture.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", bitmapPhoto);
                                    }
                                });

                                tv_name.setText(datapribadi.getNama_nasabah());
                                tv_cifappel.setText(datapribadi.getCif_clas());
                                tv_nomorktp.setText(datapribadi.getNo_ktp().toString().trim());

                                Type type = new TypeToken<List<datapembiayaan>>() {}.getType();
                                dataPembiayaan = gson.fromJson(response.body().getData().get("dataPby").toString(), type);
                                datapembiayaanAdapater = new DatapembiayaanAdapater(CeknasabahActivity.this, dataPembiayaan);
                                rv_listpembiayaan.setLayoutManager(new LinearLayoutManager(CeknasabahActivity.this));
                                rv_listpembiayaan.setItemAnimator(new DefaultItemAnimator());
                                rv_listpembiayaan.setAdapter(datapembiayaanAdapater);
                            }
                        }
                        else {
                            AppUtil.notiferror(CeknasabahActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(CeknasabahActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                progressbar_loading.setVisibility(View.GONE);
                AppUtil.notiferror(CeknasabahActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_inputpipeline:
                if(produk!=null&&produk.equalsIgnoreCase("kpr")){
                    finish();
                    btn_inputpipeline.setEnabled(false);
                    Intent it = new Intent(this, KonsumerKprPipelineInputActivity.class);
                    it.putExtra("nik", nasabahId);

                    if(!dataPribadi.equalsIgnoreCase(""))
                    {
                        it.putExtra("dataPribadi", dataPribadi);
                    }
                    startActivity(it);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btn_inputpipeline.setEnabled(true);
                        }
                    }, 5000);
                }
                else{
                    finish();
                    btn_inputpipeline.setEnabled(false);
                    Intent it = new Intent(this, KonsumerKMGPipelineInputActivity.class);
                    it.putExtra("nik", nasabahId);
                    if(!dataPribadi.equalsIgnoreCase(""))
                    {
                        it.putExtra("dataPribadi", dataPribadi);
                    }
                    startActivity(it);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btn_inputpipeline.setEnabled(true);
                        }
                    }, 5000);
                }

                break;

        }
    }
}
