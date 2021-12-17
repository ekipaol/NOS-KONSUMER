package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.ReqUid;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqBatalAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateIdebOjk;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateMemo;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityMemoBinding;
import com.application.bris.ikurma_nos_konsumer.model.menu.ListViewSubmenuHotprospek;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataMemo;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_ideb.EditIdebActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_rac.VerifikasiRacActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.general.ListAplikasiActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.CoreLayoutActivity;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.menu.MenuFlppActivity;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemoActivity extends AppCompatActivity implements GenericListenerOnSelect {

    private List<ListViewSubmenuHotprospek> dataMenu;
    private MemoAdapter memoAdapter;

    public long idAplikasi=0;
    private String statusId;
    private List<DataMemo> dataMemo =new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private PrapenAoActivityMemoBinding binding;
    private BottomSheetBehavior bottomSheetBehavior;
    List<MGenericModel> dataDropdownFlow=new ArrayList<>();

    int hideButtonClickIndicator=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=PrapenAoActivityMemoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idAplikasi=Long.parseLong(getIntent().getStringExtra("idAplikasi"));
        statusId=getIntent().getStringExtra("statusId");
        bottomSheetBehavior=BottomSheetBehavior.from(binding.bottomSheet.bottomSheet);

        //pantekan default toolbar
        customToolbar();
        backgroundStatusBar();
        otherViewChanges();
        onClicks();
        loadData();

        //initialize status
//        setData();
//        initialize();

    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void initialize(){
        binding.rvMemo.setVisibility(View.VISIBLE);
        binding.rvMemo.setHasFixedSize(true);
        memoAdapter = new MemoAdapter(MemoActivity.this, dataMemo);
        binding.rvMemo.setLayoutManager(new LinearLayoutManager(MemoActivity.this));
        binding.rvMemo.setItemAnimator(new DefaultItemAnimator());
        binding.rvMemo.setAdapter(memoAdapter);

    }


    public void customToolbar(){
        binding.toolbarNosearch.tvPageTitle.setText("Memo");

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog.DialogBackpress(MemoActivity.this);
            }
        });
    }

    private void otherViewChanges(){
        //default bottom shit position
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        //biar keyboard gak nongol di awal activity kalau ada edittext
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void setData(){
//        DataMemo data1=new DataMemo();
//        data1.setNama("Aleksander Dashkevitct");
//        data1.setJabatan("Marketing Mikro");
//        data1.setJenisPutusan("Dilanjutkan");
//        data1.setTahapAplikasi("Confirm Validasi Engine");
//        data1.setTanggal(AppUtil.parseTanggalGeneral("18032021","ddmmyyyy","dd-mm-yyyy"));
//        data1.setMemo("Menurut saya ini sangatlah bagus sekali, pembiayaan ini ingin saya lanjutkan dan langsung saya sikat aja rasanya pak");
//
//        DataMemo data2=new DataMemo();
//        data2.setNama("Pelawak Uzbek");
//        data2.setJabatan("FF");
//        data2.setJenisPutusan("Dilanjutkan");
//        data2.setTahapAplikasi("Verifikasi");
//        data2.setTanggal(AppUtil.parseTanggalGeneral("19032021","ddmmyyyy","dd-mm-yyyy"));
//        data2.setMemo("Dilanjutkan aja pak biar cepat selesai, saya mau pulang soalnya");
//
//        DataMemo data3=new DataMemo();
//        data3.setNama("Hasan Kahraman");
//        data3.setJabatan("Supervisor FF");
//        data3.setJenisPutusan("Dilanjutkan");
//        data3.setTahapAplikasi("Otor Verifikasi");
//        data3.setTanggal(AppUtil.parseTanggalGeneral("19032021","ddmmyyyy","dd-mm-yyyy"));
//        data3.setMemo("Ya boleh silahkan aja kamu pulang");
//
//
//        dataMemo.add(data1);
//        dataMemo.add(data2);
//        dataMemo.add(data3);
//

    }

    private void loadData(){
        binding.rvMemo.setVisibility(View.GONE);
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        req.setApplicationId(idAplikasi);


        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().inquiryMemo(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DataMemo>>() {
                        }.getType();
                        dataMemo =  gson.fromJson(listDataString, type);

                        if(dataMemo.size()==0){
                            binding.llEmptydata.setVisibility(View.VISIBLE);
                        }

                        initialize();
                    }
                    else if(response.body().getStatus().equalsIgnoreCase("01")){
                        binding.llEmptydata.setVisibility(View.VISIBLE);
                    }
                    else{
                        AppUtil.notiferror(MemoActivity.this, findViewById(android.R.id.content), response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(MemoActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    public void updateMemo(SweetAlertDialog dialog,String namaAktifitas){
        dialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        dialog.setTitleText("Memproses Memo");
        dialog.setContentText("Harap Tunggu");
        UpdateMemo req=new UpdateMemo();
        req.setApplicationId(idAplikasi);
        req.setMemo(binding.bottomSheet.extendedCatatan.getText().toString());
        req.setUID(String.valueOf(appPreferences.getUid()));
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        Call<ParseResponse> call;
        call = apiClientAdapter.getApiInterface().updateMemo(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            if(namaAktifitas.equalsIgnoreCase("setuju")){
                                lanjutPembiayaan(dialog);
                            }
                            else if(namaAktifitas.equalsIgnoreCase("kembalikan")){
                                kembalikanPembiayaan(dialog);
                            }
                            else if(namaAktifitas.equalsIgnoreCase("tolak")){
                                batalPembiayaan(dialog);
                            }

                        }
                        else{
                            dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            dialog.setTitleText("Gagal");
                            dialog.setConfirmText("Coba Lagi");
                            dialog.setContentText(response.body().getMessage()+"\n\n");
//                            AppUtil.notiferror(MemoActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        dialog.dismissWithAnimation();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(MemoActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                dialog.dismissWithAnimation();
                AppUtil.notiferror(MemoActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void lanjutPembiayaan(SweetAlertDialog dialog){
        dialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        dialog.setTitleText("Memproses Aplikasi");
        dialog.setContentText("Harap Tunggu");

        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        req.setApplicationId(idAplikasi);
        req.setUID(String.valueOf(appPreferences.getUid()));

        Call<ParseResponse> call=null;
        if(statusId.equalsIgnoreCase("d.3")){
            call = apiClientAdapter.getApiInterface().lanjutPembiayaanKeVerifikator(req);
        }
        else if(statusId.equalsIgnoreCase("d.4")){
            call = apiClientAdapter.getApiInterface().lanjutPembiayaanKeOtorVerifikator(req);
        }
        else if(statusId.equalsIgnoreCase("d.4 otor")){
            call = apiClientAdapter.getApiInterface().lanjutPembiayaanKeD5(req);
        }
        else if(statusId.equalsIgnoreCase("d.5")){
            call = apiClientAdapter.getApiInterface().lanjutPembiayaanKePemutus(req);
        }
        else if(statusId.equalsIgnoreCase("d.6")){
            call = apiClientAdapter.getApiInterface().lanjutPembiayaanKeAkad(req);
        }
        else if(statusId.equalsIgnoreCase("g.1")){
            call = apiClientAdapter.getApiInterface().lanjutG1(req);
        }

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){

                            dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            dialog.setTitleText("Berhasil");
                            dialog.setContentText("Aplikasi Berhasil Dilanjutkan\n\n");
                            dialog.setConfirmText("OK");
                            dialog.showCancelButton(false);
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    dialog.dismissWithAnimation();
                                    Intent intent=new Intent(MemoActivity.this, ListAplikasiActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                                    startActivity(intent);
                                }
                            });
                        }
                        else{
                            dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            dialog.setTitleText("Gagal");
                            dialog.setConfirmText("Coba Lagi");
                            dialog.setContentText(response.body().getMessage()+"\n\n");
//                            AppUtil.notiferror(MemoActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        dialog.dismissWithAnimation();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(MemoActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                dialog.dismissWithAnimation();
                AppUtil.notiferror(MemoActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void batalPembiayaan(SweetAlertDialog dialog){
        dialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        dialog.setTitleText("Memproses Aplikasi");
        dialog.setContentText("Harap Tunggu");
        ReqBatalAplikasi req=new ReqBatalAplikasi();
        req.setApplicationId(idAplikasi);
        req.setUID(String.valueOf(appPreferences.getUid()));
        req.setReasonDescription(binding.bottomSheet.extendedCatatan.getText().toString());
        Call<ParseResponse> call=null;
        if(statusId.equalsIgnoreCase("d.3")){
            call = apiClientAdapter.getApiInterface().batalPembiayaan(req);
        }
        else if(statusId.equalsIgnoreCase("d.4")){
            call = apiClientAdapter.getApiInterface().batalPembiayaanVerifikator(req);
        }
        else if(statusId.equalsIgnoreCase("d.4 otor")){
            call = apiClientAdapter.getApiInterface().batalPembiayaanOtorVerifikator(req);
        }
        else if(statusId.equalsIgnoreCase("d.5")){
            call = apiClientAdapter.getApiInterface().batalPembiayaanD5(req);
        }
        else if(statusId.equalsIgnoreCase("d.6")){
            call = apiClientAdapter.getApiInterface().batalPembiayaanD6(req);
        }
        else if(statusId.equalsIgnoreCase("g.1")){
            call = apiClientAdapter.getApiInterface().batalG1(req);
        }
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){

                            dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            dialog.setTitleText("Berhasil");
                            dialog.setContentText("Aplikasi Berhasil Dibatalkan\n\n");
                            dialog.setConfirmText("OK");
                            dialog.showCancelButton(false);
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    dialog.dismissWithAnimation();
                                    Intent intent=new Intent(MemoActivity.this, ListAplikasiActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                                    startActivity(intent);
                                }
                            });
                        }
                        else{
                            dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            dialog.setTitleText("Gagal");
                            dialog.setConfirmText("Coba Lagi");
                            dialog.setContentText(response.body().getMessage()+"\n\n");
//                            AppUtil.notiferror(MemoActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        dialog.dismissWithAnimation();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(MemoActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                dialog.dismissWithAnimation();
                AppUtil.notiferror(MemoActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void kembalikanPembiayaan(SweetAlertDialog dialog){
        dialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        dialog.setTitleText("Memproses Aplikasi");
        dialog.setContentText("Harap Tunggu");
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        req.setApplicationId(idAplikasi);
        req.setUID(String.valueOf(appPreferences.getUid()));

        Call<ParseResponse> call=null;
        if(statusId.equalsIgnoreCase("d.3")){
            call = apiClientAdapter.getApiInterface().kembalikanPembiayaan(req);
        }
        else if(statusId.equalsIgnoreCase("d.4")){
            call = apiClientAdapter.getApiInterface().kembalikanPembiayaanVerifikator(req);
        }
        else if(statusId.equalsIgnoreCase("d.4 otor")){
            call = apiClientAdapter.getApiInterface().kembalikanPembiayaanOtorVerifikator(req);
        }
        else if(statusId.equalsIgnoreCase("d.5")){
            call = apiClientAdapter.getApiInterface().kembalikanPembiayaanOtorMarketing(req);
        }
        else if(statusId.equalsIgnoreCase("d.6")){
            call = apiClientAdapter.getApiInterface().kembalikanD6(req);
        }
        else if(statusId.equalsIgnoreCase("g.1")){
            call = apiClientAdapter.getApiInterface().kembalikanG1(req);
        }

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){

                            dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            dialog.setTitleText("Berhasil");
                            dialog.setContentText("Aplikasi Berhasil Dikembalikan ke Tahap Sebelumnya?\n\n");
                            dialog.setConfirmText("OK");
                            dialog.showCancelButton(false);
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    dialog.dismissWithAnimation();
                                    Intent intent=new Intent(MemoActivity.this, ListAplikasiActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                                    startActivity(intent);
                                }
                            });
                        }
                        else{
                            dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            dialog.setTitleText("Gagal");
                            dialog.setConfirmText("Coba Lagi");
                            dialog.setContentText(response.body().getMessage()+"\n\n");
//                            AppUtil.notiferror(MemoActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        dialog.dismissWithAnimation();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(MemoActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                dialog.dismissWithAnimation();
                AppUtil.notiferror(MemoActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }



    @Override
    public void onSelect(String title, MGenericModel data) {

    }


    private void onClicks(){
        binding.btSembunyiInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("buttonindicator",Integer.toString(hideButtonClickIndicator));
                if(hideButtonClickIndicator==0){

                    binding.llContentInfo.animate().translationY(0).setDuration(500);
                    binding.llContentInfo.setVisibility(View.GONE);

                    binding.btSembunyiInfo.setVisibility(View.GONE);
                    binding.btTampilInfo.setVisibility(View.VISIBLE);
                    hideButtonClickIndicator++;

                }

            }
        });

        binding.btTampilInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(hideButtonClickIndicator==1){
                    binding.cvDataPembiayaanCatatan.setVisibility(View.VISIBLE);
                    binding.llContentInfo.setVisibility(View.VISIBLE);
                    binding.btTampilInfo.setVisibility(View.GONE);
                    binding.btSembunyiInfo.setVisibility(View.VISIBLE);
                    hideButtonClickIndicator--;
                }
            }
        });

        binding.buttonLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.bottomSheet.extendedCatatan.setEnabled(true);
                binding.bottomSheet.extendedCatatan.setFocusable(true);

                binding.bottomSheet.extendedCatatan.requestFocus();
//                binding.bottomSheet.extendedCatatan.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        binding.bottomSheet.ivCapsuleClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        binding.bottomSheet.btSetuju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.bottomSheet.extendedCatatan.getText().toString().isEmpty()){
                    AppUtil.notiferror(MemoActivity.this, findViewById(android.R.id.content), "Harap isi catatan terlebih dahulu");
                }
                else{
                    SweetAlertDialog dialog=new SweetAlertDialog(MemoActivity.this,SweetAlertDialog.WARNING_TYPE);
                    dialog.setTitleText("Konfirmasi");
                    dialog.setContentText("Apakah anda yakin ingin melanjutkan aplikasi?\n\n");
                    dialog.setConfirmText("Ya");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setCancelText("Batal");
                    dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            updateMemo(dialog,"setuju");
                        }
                    });
                    dialog.show();
                }

            }
        });

        binding.bottomSheet.btTolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.bottomSheet.extendedCatatan.getText().toString().isEmpty()){
                    AppUtil.notiferror(MemoActivity.this, findViewById(android.R.id.content), "Harap isi catatan terlebih dahulu");
                }
                else{
                    SweetAlertDialog dialog=new SweetAlertDialog(MemoActivity.this,SweetAlertDialog.WARNING_TYPE);
                    dialog.setTitleText("Konfirmasi");
                    dialog.setContentText("Apakah anda yakin ingin membatalkan aplikasi?\n\n");
                    dialog.setConfirmText("Ya");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setCancelText("Batal");
                    dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            batalPembiayaan(dialog);
                        }
                    });
                    dialog.show();
                }

            }
        });

        binding.bottomSheet.btKembalikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.bottomSheet.extendedCatatan.getText().toString().isEmpty()){
                    AppUtil.notiferror(MemoActivity.this, findViewById(android.R.id.content), "Harap isi catatan terlebih dahulu");
                }
                else{
                    SweetAlertDialog dialog=new SweetAlertDialog(MemoActivity.this,SweetAlertDialog.WARNING_TYPE);
                    dialog.setTitleText("Konfirmasi");
                    dialog.setContentText("Apakah anda yakin ingin mengembalikan aplikasi ke tahap sebelumnya?\n\n");
                    dialog.setConfirmText("Ya");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setCancelText("Batal");
                    dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
//                            kembalikanPembiayaan(dialog);
                            updateMemo(dialog,"kembalikan");
                        }
                    });
                    dialog.show();
                }

                }


        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            //close bottom sheet on click outside
            if (bottomSheetBehavior.getState()==BottomSheetBehavior.STATE_EXPANDED) {

                Rect outRect = new Rect();
                binding.bottomSheet.bottomSheet.getGlobalVisibleRect(outRect);

                if(!outRect.contains((int)ev.getRawX(), (int)ev.getRawY()))
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        CustomDialog.DialogBackpress(MemoActivity.this);
    }
}