package com.application.bris.ikurma_nos_konsumer.page_aom.dialog;


import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.flpp.ReqSimpanUpdateIdLokasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.ReqIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.BottomsheetMaintainIdLokasiBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataUpdateIdLokasi;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.HotprospekKpr;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.HotprospekDetailActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.HotprospekDetailKprActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BSUpdateIdLokasi extends BottomSheetDialogFragment implements View.OnClickListener, ConfirmListener {


    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private static Context context;
    private static HotprospekKpr data;
    private DataUpdateIdLokasi dataUpdateIdLokasi;
    static SweetAlertDialog dialog;
    private BottomsheetMaintainIdLokasiBinding binding;

    public static BSUpdateIdLokasi display(FragmentManager fragmentManager, Context mcontex, HotprospekKpr mdata){
        context = mcontex;
        data = mdata;
        BSUpdateIdLokasi BSCekNasabah = new BSUpdateIdLokasi();
        BSCekNasabah.show(fragmentManager, "Open Update ID Lokasi");
        return BSCekNasabah;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
//        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = BottomsheetMaintainIdLokasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        try {
            apiClientAdapter = new ApiClientAdapter(getContext());
            appPreferences = new AppPreferences(getContext());
            loadData();
            otherViewChanges();

        }
        catch (Exception e){
            e.printStackTrace();
            AppUtil.showToastShort(context, e.getMessage());
        }
        return view;
    }


    private void loadData() {
        binding.progressbarLoading.setVisibility(View.VISIBLE);
        ReqIdAplikasi req = new ReqIdAplikasi();
        req.setIdAplikasi(data.getId_aplikasi());
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().getDataUpdateIdLokasi(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.progressbarLoading.setVisibility(View.GONE);
                try {
                    if(response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00"))
                        {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<DataUpdateIdLokasi>() {}.getType();

                            dataUpdateIdLokasi = gson.fromJson(listDataString, type);

                            if(dataUpdateIdLokasi!=null){
                                binding.etNamaFlpp.setText(dataUpdateIdLokasi.getNamaDebitur());
                                binding. etKtpFlpp.setText(dataUpdateIdLokasi.getNoKtp());
                                binding.etNamaPengembangFlpp.setText(dataUpdateIdLokasi.getNamaPengembang());
                                binding.etNamaPerumahanFlpp.setText(dataUpdateIdLokasi.getNamaPerumahan());
                                binding.etNpwpPengembang.setText(dataUpdateIdLokasi.getNpwpPengembang());
                                binding.etIdLokasiBaru.setText(dataUpdateIdLokasi.getIdRumah());
                            }

                        }
                        else {
                            AppUtil.notiferror(getContext(), getDialog().findViewById(android.R.id.content), response.body().getMessage());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    dismiss();
                                }
                            }, 2000);
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(getContext(), getDialog().findViewById(android.R.id.content), error.getMessage());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dismiss();
                            }
                        }, 2000);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dismiss();
                        }
                    }, 2000);
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getDialog().findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                }, 2000);
            }
        });
    }
    

    private void otherViewChanges(){
        binding.etKtpFlpp.setInputType(InputType.TYPE_NULL);
        binding.etKtpFlpp.setFocusable(false);


        binding.etNamaFlpp.setInputType(InputType.TYPE_NULL);
        binding.etNamaFlpp.setFocusable(false);

        binding.btnUpdateIdLokasi.setOnClickListener(this);

    }


    public void sendUpdateIdLokasi(){
        binding.progressbarLoading.setVisibility(View.VISIBLE);
        Call<ParseResponse> call = null;
        ReqSimpanUpdateIdLokasi req =new ReqSimpanUpdateIdLokasi();

        req.setIdRumahBaru(binding.etIdLokasiBaru.getText().toString().trim());
        req.setKtp(binding.etKtpFlpp.getText().toString().trim());
        req.setNamaPengembang(binding.etNamaPengembangFlpp.getText().toString().trim());
        req.setNamaPerumahan(binding.etNamaPerumahanFlpp.getText().toString().trim());
        req.setNpwpPengembang(binding.etNpwpPengembang.getText().toString().trim());
        req.setIdAplikasi(Integer.toString(data.getId_aplikasi()));
        req.setUid(Integer.toString(appPreferences.getUid()));

        //pantekan parameter testing
        if(appPreferences.getNama().equalsIgnoreCase("developer")){
            Toast.makeText(context, "Parameter testing true", Toast.LENGTH_SHORT).show();
            req.setParameterTesting("1");
        }
        else{
            req.setParameterTesting("0");
        }



        //send putusan service
        call = apiClientAdapter.getApiInterface().updateIdLokasiFlpp(req);


        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.progressbarLoading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            CustomDialog.DialogSuccess(getContext(), "Success!", "ID Lokasi berhasil di-update, aplikasi sudah dikembalikan ke FLPP Center", BSUpdateIdLokasi.this);
                        }
                        else{
                            AppUtil.notiferror(getContext(), getDialog().findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(getContext(), getDialog().findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getDialog().findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_update_id_lokasi :

                    if (validateForm()){
                        dialog=new SweetAlertDialog(context,SweetAlertDialog.NORMAL_TYPE);
                        dialog.setTitle("Konfirmasi");
                        dialog.setContentText("Apakah anda yakin ingin melakukan update ID lokasi menjadi : "+binding.etIdLokasiBaru.getText()+"\n");
                        dialog.setCancelText("Batal");
                        dialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                dialog.dismissWithAnimation();
                            }
                        });
                        dialog.setConfirmText("Setuju");
                        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sendUpdateIdLokasi();
                                dialog.dismissWithAnimation();
                            }
                        });
                        dialog.show();
                    }

                break;

        }
    }

    public boolean validateForm(){
        if(binding.etNamaPengembangFlpp.getText().toString().trim().isEmpty() ){
            AppUtil.notifwarning(getContext(), getDialog().findViewById(android.R.id.content), "Nama pengembang tidak boleh kosong");
            return false;
        }
        else  if(binding.etNamaPerumahanFlpp.getText().toString().trim().isEmpty() ){
            AppUtil.notifwarning(getContext(), getDialog().findViewById(android.R.id.content), "Nama perumahan tidak boleh kosong");
            return false;
        }
        else  if(binding.etNpwpPengembang.getText().toString().trim().isEmpty() ){
            AppUtil.notifwarning(getContext(), getDialog().findViewById(android.R.id.content), "NPWP pengembang tidak boleh kosong");
            return false;
        }
        else  if(binding.etIdLokasiBaru.getText().toString().trim().isEmpty() ){
            AppUtil.notifwarning(getContext(), getDialog().findViewById(android.R.id.content), "ID lokasi tidak boleh kosong");
            return false;
        }
        return true;
    }

    private static ValueAnimator slideAnimator(final LinearLayout mLinearLayout, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = mLinearLayout.getLayoutParams();
                layoutParams.height = value;
                mLinearLayout.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    @Override
    public void success(boolean val) {
        if (val){
            dismiss();
            if(data.getNama_produk().equalsIgnoreCase("kpr")){
                ((HotprospekDetailKprActivity) context).recreate();
            }
            else{
                ((HotprospekDetailActivity) context).recreate();
            }

        }

    }

    @Override
    public void confirm(boolean val) {

    }
}
