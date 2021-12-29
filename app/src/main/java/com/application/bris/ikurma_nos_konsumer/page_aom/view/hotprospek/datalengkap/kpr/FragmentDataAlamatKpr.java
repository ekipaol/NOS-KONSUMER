package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap.kpr;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.DataLengkapPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogAddress;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.AddressListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataLengkap;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.address;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.ImageHandler;
import com.application.bris.ikurma_nos_konsumer.util.KeyValue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.snackbar.Snackbar;
import com.makeramen.roundedimageview.RoundedDrawable;
import com.makeramen.roundedimageview.RoundedImageView;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentDataAlamatKpr extends Fragment implements Step, KeyValueListener, AddressListener, View.OnClickListener, View.OnFocusChangeListener, CameraListener {

    //KTP
    @BindView(R.id.ll_dataalamat)
    LinearLayout ll_dataalamat;
    @BindView(R.id.tf_alamatktp)
    TextFieldBoxes tf_alamatktp;
    @BindView(R.id.et_alamatktp)
    EditText et_alamatktp;
    @BindView(R.id.tf_rtktp)
    TextFieldBoxes tf_rtktp;
    @BindView(R.id.et_rtktp)
    EditText et_rtktp;
    @BindView(R.id.tf_rwktp)
    TextFieldBoxes tf_rwktp;
    @BindView(R.id.et_rwktp)
    EditText et_rwktp;
    @BindView(R.id.tf_provinsiktp)
    TextFieldBoxes tf_provinsiktp;
    @BindView(R.id.et_provinsiktp)
    EditText et_provinsiktp;
    @BindView(R.id.tf_kotaktp)
    TextFieldBoxes tf_kotaktp;
    @BindView(R.id.et_kotaktp)
    EditText et_kotaktp;
    @BindView(R.id.tf_kecamatanktp)
    TextFieldBoxes tf_kecamatanktp;
    @BindView(R.id.et_kecamatanktp)
    EditText et_kecamatanktp;
    @BindView(R.id.tf_kelurahanktp)
    TextFieldBoxes tf_kelurahanktp;
    @BindView(R.id.et_kelurahanktp)
    EditText et_kelurahanktp;
    @BindView(R.id.tf_kodeposktp)
    TextFieldBoxes tf_kodeposktp;
    @BindView(R.id.et_kodeposktp)
    EditText et_kodeposktp;
    @BindView(R.id.btn_ktp)
    Button btn_ktp;

    //DOMISILI
    @BindView(R.id.sw_domisiliktpsama)
    Switch sw_domisiliktpsama;
    @BindView(R.id.tf_statusdom)
    TextFieldBoxes tf_statusdom;
    @BindView(R.id.et_statusdom)
    EditText et_statusdom;
    @BindView(R.id.tf_lamadom)
    TextFieldBoxes tf_lamadom;
    @BindView(R.id.et_lamadom)
    EditText et_lamadom;
    @BindView(R.id.ll_alamatdom)
    LinearLayout ll_alamatdom;
    @BindView(R.id.tf_alamatdom)
    TextFieldBoxes tf_alamatdom;
    @BindView(R.id.et_alamatdom)
    EditText et_alamatdom;
    @BindView(R.id.tf_rtdom)
    TextFieldBoxes tf_rtdom;
    @BindView(R.id.et_rtdom)
    EditText et_rtdom;
    @BindView(R.id.tf_rwdom)
    TextFieldBoxes tf_rwdom;
    @BindView(R.id.et_rwdom)
    EditText et_rwdom;
    @BindView(R.id.tf_provinsidom)
    TextFieldBoxes tf_provinsidom;
    @BindView(R.id.et_provinsidom)
    EditText et_provinsidom;
    @BindView(R.id.tf_kotadom)
    TextFieldBoxes tf_kotadom;
    @BindView(R.id.et_kotadom)
    EditText et_kotadom;
    @BindView(R.id.tf_kecamatandom)
    TextFieldBoxes tf_kecamatandom;
    @BindView(R.id.et_kecamatandom)
    EditText et_kecamatandom;
    @BindView(R.id.tf_kelurahandom)
    TextFieldBoxes tf_kelurahandom;
    @BindView(R.id.et_kelurahandom)
    EditText et_kelurahandom;
    @BindView(R.id.tf_kodeposdom)
    TextFieldBoxes tf_kodeposdom;
    @BindView(R.id.et_kodeposdom)
    EditText et_kodeposdom;
    @BindView(R.id.btn_dom)
    Button btn_dom;

    @BindView(R.id.tf_validitas_tempat_tinggal)
    TextFieldBoxes tf_validitas_tempat_tinggal;
    @BindView(R.id.et_validitas_tempat_tinggal)
    EditText et_validitas_tempat_tinggal;

    @BindView(R.id.img_fotorumah1)
    RoundedImageView img_fotorumah1;
    @BindView(R.id.btn_upload_fotorumah1)
    ImageView btn_upload_fotorumah1;
    @BindView(R.id.img_fotorumah2)
    RoundedImageView img_fotorumah2;
    @BindView(R.id.btn_upload_fotorumah2)
    ImageView btn_upload_fotorumah2;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.progress_rumah1)
    ProgressBar progress_rumah1;
    @BindView(R.id.progress_rumah2)
    ProgressBar progress_rumah2;

    private Realm realm;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private DataLengkap dataLengkap;
    private String approved;

    private String val_AlamatId ="";
    private String val_RtId ="";
    private String val_RwId ="";
    private String val_ProvId ="";
    private String val_KotaId ="";
    private String val_KecId ="";
    private String val_KelId ="";
    private String val_KodePosId ="";
    private String val_StatusTptTinggal ="";
    private String val_ValiditasTptTinggal ="";
    private String val_LamaMenetap ;
    private String val_AlamatDom ="";
    private String val_RtDom ="";
    private String val_RwDom ="";
    private String val_ProvDom ="";
    private String val_KotaDom ="";
    private String val_KecDom ="";
    private String val_KelDom ="";
    private String val_KodePosDom ="";
    private int val_ImgRumah1 = 0;
    private int val_ImgRumah2 = 0;
    private String isSelectPhoto = "";

    private final int PICK_PICTURE_RUMAH1 = 1;
    private final int PICK_PICTURE_RUMAH2 = 2;
    private final int TAKE_PICTURE_RUMAH1 = 11;
    private final int TAKE_PICTURE_RUMAH2 = 22;

    private Uri uriPhotoRumah1, uriPhotoRumah2;
    private Bitmap bitmapPhotoRumah1, bitmapPhotoRumah2, loadedPicture;

//    private String sudahUploadRumah1 = "belum";
//    private String sudahUploadRumah2 = "belum";

//    private int idFotoRumah1 = 0;
//    private int idFotoRumah2 = 0;

    private int val_domAsId = 0;
    private String btnAddressString = "";

//    public FragmentDataAlamat(DataLengkap mdataLengkap) {
//        dataLengkap = mdataLengkap;
//    }

    public FragmentDataAlamatKpr(DataLengkap mdataLengkap, String maprroved) {
        dataLengkap = mdataLengkap;
        approved = maprroved;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_dataalamat_kpr, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());
        sw_domisiliktpsama.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ll_alamatdom.setVisibility(View.GONE);
                    val_domAsId = 1;
                }
                else {
                    ll_alamatdom.setVisibility(View.VISIBLE);
                    val_domAsId = 0;
                }
            }
        });
        onclickSelectDialog();
        if (approved.equalsIgnoreCase("no")) {
            setDynamicIcon();
        }

        setData();

        if (approved.equalsIgnoreCase("no")) {
            setDynamicIconDropDown();
        }
        onSelectDialog();


        return view;
    }

    private void onclickSelectDialog(){
        btn_ktp.setOnClickListener(this);
        btn_dom.setOnClickListener(this);
        img_fotorumah1.setOnClickListener(this);
        btn_upload_fotorumah1.setOnClickListener(this);
        img_fotorumah2.setOnClickListener(this);
        btn_upload_fotorumah2.setOnClickListener(this);
    }

    public void setData(){
        et_alamatktp.setText(dataLengkap.getAlamatId());
        et_rtktp.setText(dataLengkap.getRtId());
        et_rwktp.setText(dataLengkap.getRwId());
        et_provinsiktp.setText(dataLengkap.getProvId());
        et_kotaktp.setText(dataLengkap.getKotaId());
        et_kecamatanktp.setText(dataLengkap.getKecId());
        et_kelurahanktp.setText(dataLengkap.getKelId());
        et_kodeposktp.setText(dataLengkap.getKodePosId());

        et_statusdom.setText(KeyValue.getKeyStatusTempatDomisili(dataLengkap.getStatusTptTinggal()));

        et_validitas_tempat_tinggal.setText(KeyValue.getKeyValiditasTempatTinggal(dataLengkap.getValiditasTempatTinggal()));
        val_ValiditasTptTinggal=dataLengkap.getValiditasTempatTinggal();

        et_lamadom.setText(String.valueOf(dataLengkap.getLamaMenetap()));
        et_alamatdom.setText(dataLengkap.getAlamatDom());
        et_rtdom.setText(dataLengkap.getRtDom());
        et_rwdom.setText(dataLengkap.getRwDom());
        et_provinsidom.setText(dataLengkap.getProvDom());
        et_kotadom.setText(dataLengkap.getKotaDom());
        et_kecamatandom.setText(dataLengkap.getKecDom());
        et_kelurahandom.setText(dataLengkap.getKelDom());
        et_kodeposdom.setText(dataLengkap.getKodePosDom());

        bitmapPhotoRumah1 = setLoadImage(img_fotorumah1, dataLengkap.getFID_PHOTO_RUMAH1());
        bitmapPhotoRumah2 = setLoadImage(img_fotorumah2, dataLengkap.getFID_PHOTO_RUMAH2());
        val_ImgRumah1 = dataLengkap.getFID_PHOTO_RUMAH1();
        val_ImgRumah2 = dataLengkap.getFID_PHOTO_RUMAH2();

        if (approved.equalsIgnoreCase("yes")){
            AppUtil.disableEditTexts(ll_dataalamat);
            btn_ktp.setVisibility(View.GONE);
            btn_dom.setVisibility(View.GONE);
            sw_domisiliktpsama.setVisibility(View.GONE);
            btn_upload_fotorumah1.setVisibility(View.GONE);
            btn_upload_fotorumah2.setVisibility(View.GONE);
        }
    }

    public Bitmap setLoadImage(final ImageView iv, int idFoto){
        String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFile + idFoto;
        Glide
                .with(getContext())
                .asBitmap()
                .load(url_photo)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv.setImageBitmap(resource);
                        loadedPicture = resource;
                    }
                });
        return loadedPicture;
    }

    private void onSelectDialog(){

        //STATUS DOMISILI
        et_statusdom.setFocusable(false);
        et_statusdom.setInputType(InputType.TYPE_NULL);
        et_statusdom.setOnFocusChangeListener(this);

        et_validitas_tempat_tinggal.setFocusable(false);
        et_validitas_tempat_tinggal.setInputType(InputType.TYPE_NULL);
        et_validitas_tempat_tinggal.setOnFocusChangeListener(this);

        if (approved.equalsIgnoreCase("no")){
            et_statusdom.setOnClickListener(this);
            tf_statusdom.setOnClickListener(this);
            tf_statusdom.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openKeyValueDialog(tf_statusdom.getLabelText().toString().trim());
                }
            });

            et_validitas_tempat_tinggal.setOnClickListener(this);
            et_validitas_tempat_tinggal.setOnClickListener(this);
            tf_validitas_tempat_tinggal.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openKeyValueDialog(tf_validitas_tempat_tinggal.getLabelText().toString().trim());
                }
            });

        }

        et_provinsiktp.setInputType(InputType.TYPE_NULL);
        et_provinsiktp.setFocusable(false);
        et_kotaktp.setInputType(InputType.TYPE_NULL);
        et_kotaktp.setFocusable(false);
        et_kecamatanktp.setInputType(InputType.TYPE_NULL);
        et_kecamatanktp.setFocusable(false);
        et_kelurahanktp.setInputType(InputType.TYPE_NULL);
        et_kelurahanktp.setFocusable(false);
        et_kodeposktp.setInputType(InputType.TYPE_NULL);
        et_kodeposktp.setFocusable(false);

        et_provinsidom.setInputType(InputType.TYPE_NULL);
        et_provinsidom.setFocusable(false);
        et_kotadom.setInputType(InputType.TYPE_NULL);
        et_kotadom.setFocusable(false);
        et_kecamatandom.setInputType(InputType.TYPE_NULL);
        et_kecamatandom.setFocusable(false);
        et_kelurahandom.setInputType(InputType.TYPE_NULL);
        et_kelurahandom.setFocusable(false);
        et_kodeposdom.setInputType(InputType.TYPE_NULL);
        et_kodeposdom.setFocusable(false);
    }

    private void openKeyValueDialog(String title){
        DialogKeyValue.display(getFragmentManager(), title, this);
    }

    private void openAddressDialog(){
        DialogAddress.display(getFragmentManager(), this);
    }

    private VerificationError validateForm(){
        //KTP
        if (et_alamatktp.getText().toString().isEmpty() || et_alamatktp.getText().toString().equalsIgnoreCase("")){
            tf_alamatktp.setError("Format "+ tf_alamatktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_alamatktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_rtktp.getText().toString().isEmpty() || et_rtktp.getText().toString().equalsIgnoreCase("")){
            tf_rtktp.setError("Format "+ tf_rtktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_rtktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_rwktp.getText().toString().isEmpty() || et_rwktp.getText().toString().equalsIgnoreCase("")){
            tf_rwktp.setError("Format "+ tf_rwktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_rwktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_provinsiktp.getText().toString().isEmpty() || et_provinsiktp.getText().toString().equalsIgnoreCase("")){
            tf_provinsiktp.setError("Format "+ tf_provinsiktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_provinsiktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kotaktp.getText().toString().isEmpty() || et_kotaktp.getText().toString().equalsIgnoreCase("")){
            tf_kotaktp.setError("Format "+ tf_kotaktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kotaktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kecamatanktp.getText().toString().isEmpty() || et_kecamatanktp.getText().toString().equalsIgnoreCase("")){
            tf_kecamatanktp.setError("Format "+ tf_kecamatanktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kecamatanktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kelurahanktp.getText().toString().isEmpty() || et_kelurahanktp.getText().toString().equalsIgnoreCase("")){
            tf_kelurahanktp.setError("Format "+ tf_kelurahanktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kelurahanktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kodeposktp.getText().toString().isEmpty() || et_kodeposktp.getText().toString().equalsIgnoreCase("")){
            tf_kodeposktp.setError("Format "+ tf_kodeposktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kodeposktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }



        //DOMISILI
        else if (et_statusdom.getText().toString().isEmpty() || et_statusdom.getText().toString().equalsIgnoreCase("")){
            tf_statusdom.setError("Format "+ tf_statusdom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_statusdom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_validitas_tempat_tinggal.getText().toString().isEmpty() || et_validitas_tempat_tinggal.getText().toString().equalsIgnoreCase("")){
            tf_validitas_tempat_tinggal.setError("Format "+ tf_validitas_tempat_tinggal.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_validitas_tempat_tinggal.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_lamadom.getText().toString().isEmpty() || et_lamadom.getText().toString().equalsIgnoreCase("")){
            tf_lamadom.setError("Format "+ tf_lamadom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_lamadom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_alamatdom.getText().toString().isEmpty() || et_alamatdom.getText().toString().equalsIgnoreCase("")){
            tf_alamatdom.setError("Format "+ tf_alamatdom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_alamatdom.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_rtdom.getText().toString().isEmpty() || et_rtdom.getText().toString().equalsIgnoreCase("")){
            tf_rtdom.setError("Format "+ tf_rtdom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_rtdom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_rwdom.getText().toString().isEmpty() || et_rwdom.getText().toString().equalsIgnoreCase("")){
            tf_rwdom.setError("Format "+ tf_rwdom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_rwdom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_provinsidom.getText().toString().isEmpty() || et_provinsidom.getText().toString().equalsIgnoreCase("")){
            tf_provinsidom.setError("Format "+ tf_provinsidom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_provinsidom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kotadom.getText().toString().isEmpty() || et_kotadom.getText().toString().equalsIgnoreCase("")){
            tf_kotadom.setError("Format "+ tf_kotadom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kotadom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kecamatandom.getText().toString().isEmpty() || et_kecamatandom.getText().toString().equalsIgnoreCase("")){
            tf_kecamatandom.setError("Format "+ tf_kecamatandom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kecamatandom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kelurahandom.getText().toString().isEmpty() || et_kelurahandom.getText().toString().equalsIgnoreCase("")){
            tf_kelurahandom.setError("Format "+ tf_kelurahandom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kelurahandom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kodeposdom.getText().toString().isEmpty() || et_kodeposdom.getText().toString().equalsIgnoreCase("")){
            tf_kodeposdom.setError("Format "+ tf_kodeposdom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kodeposdom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

//        else if(val_ImgRumah1 == 0){
//            return new VerificationError("Silahkan pilih / ambil Foto Rumah");
//        }
//
//        else if(val_ImgRumah2 == 0){
//            return new VerificationError("Silahkan pilih / ambil Foto Rumah");
//        }

        else {
            setDataOnListerner();
            return null;
        }
    }

    private VerificationError validateFormDomasId(){
        //KTP
        if (et_alamatktp.getText().toString().isEmpty() || et_alamatktp.getText().toString().equalsIgnoreCase("")){
            tf_alamatktp.setError("Format "+ tf_alamatktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_alamatktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_rtktp.getText().toString().isEmpty() || et_rtktp.getText().toString().equalsIgnoreCase("")){
            tf_rtktp.setError("Format "+ tf_rtktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_rtktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_rwktp.getText().toString().isEmpty() || et_rwktp.getText().toString().equalsIgnoreCase("")){
            tf_rwktp.setError("Format "+ tf_rwktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_rwktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_provinsiktp.getText().toString().isEmpty() || et_provinsiktp.getText().toString().equalsIgnoreCase("")){
            tf_provinsiktp.setError("Format "+ tf_provinsiktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_provinsiktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kotaktp.getText().toString().isEmpty() || et_kotaktp.getText().toString().equalsIgnoreCase("")){
            tf_kotaktp.setError("Format "+ tf_kotaktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kotaktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kecamatanktp.getText().toString().isEmpty() || et_kecamatanktp.getText().toString().equalsIgnoreCase("")){
            tf_kecamatanktp.setError("Format "+ tf_kecamatanktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kecamatanktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kelurahanktp.getText().toString().isEmpty() || et_kelurahanktp.getText().toString().equalsIgnoreCase("")){
            tf_kelurahanktp.setError("Format "+ tf_kelurahanktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kelurahanktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kodeposktp.getText().toString().isEmpty() || et_kodeposktp.getText().toString().equalsIgnoreCase("")){
            tf_kodeposktp.setError("Format "+ tf_kodeposktp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kodeposktp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        //DOMISILI
        else if (et_statusdom.getText().toString().isEmpty() || et_statusdom.getText().toString().equalsIgnoreCase("")){
            tf_statusdom.setError("Format "+ tf_statusdom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_statusdom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_lamadom.getText().toString().isEmpty() || et_lamadom.getText().toString().equalsIgnoreCase("")){
            tf_lamadom.setError("Format "+ tf_lamadom.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_lamadom.getLabelText()+" "+getString(R.string.title_validate_field));
        }

//        else if(sudahUploadRumah1.equalsIgnoreCase("belum")){
//            return new VerificationError("Silahkan pilih / ambil Foto Rumah");
//        }
//
//        else if(sudahUploadRumah2.equalsIgnoreCase("belum")){
//            return new VerificationError("Silahkan pilih / ambil Foto Rumah");
//        }

        else {
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner(){

        val_AlamatId = (et_alamatktp.getText().toString().trim());
        val_RtId = (et_rtktp.getText().toString().trim());
        val_RwId = (et_rwktp.getText().toString().trim());
        val_ProvId = (et_provinsiktp.getText().toString().trim());
        val_KotaId = (et_kotaktp.getText().toString().trim());
        val_KecId = (et_kecamatanktp.getText().toString().trim());
        val_KelId = (et_kelurahanktp.getText().toString().trim());
        val_KodePosId = (et_kodeposktp.getText().toString().trim());
        val_StatusTptTinggal = (KeyValue.getTypeStatusTempatDomisili(et_statusdom.getText().toString().trim()));
        val_ValiditasTptTinggal= (KeyValue.getTypeValiditasTempatTinggal(et_validitas_tempat_tinggal.getText().toString().trim()));
        val_LamaMenetap =et_lamadom.getText().toString().trim();

        if (val_domAsId == 1){
            val_AlamatDom = (et_alamatktp.getText().toString().trim());
            val_RtDom = (et_rtktp.getText().toString().trim());
            val_RwDom = (et_rwktp.getText().toString().trim());
            val_ProvDom = (et_provinsiktp.getText().toString().trim());
            val_KotaDom = (et_kotaktp.getText().toString().trim());
            val_KecDom = (et_kecamatanktp.getText().toString().trim());
            val_KelDom = (et_kelurahanktp.getText().toString().trim());
            val_KodePosDom = (et_kodeposktp.getText().toString().trim());
        }
        else {
            val_AlamatDom = (et_alamatdom.getText().toString().trim());
            val_RtDom = (et_rtdom.getText().toString().trim());
            val_RwDom = (et_rwdom.getText().toString().trim());
            val_ProvDom = (et_provinsidom.getText().toString().trim());
            val_KotaDom = (et_kotadom.getText().toString().trim());
            val_KecDom = (et_kecamatandom.getText().toString().trim());
            val_KelDom = (et_kelurahandom.getText().toString().trim());
            val_KodePosDom = (et_kodeposdom.getText().toString().trim());
        }

//        final DataLengkapPojo d = new DataLengkapPojo();

        DataLengkapPojo d = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", DataLengkapKprActivity.idAplikasi).findFirst();

        if(!realm.isInTransaction()){
            realm.beginTransaction();
        }


        d.setAlamatId(val_AlamatId);
        d.setRtId(val_RtId);
        d.setRwId(val_RwId);
        d.setProvId(val_ProvId);
        d.setKotaId(val_KotaId);
        d.setKecId(val_KecId);
        d.setKelId(val_KelId);
        d.setKodePosId(val_KodePosId);
        d.setStatusTptTinggal(val_StatusTptTinggal);
        d.setLamaMenetap(val_LamaMenetap);
        d.setAlamatDom(val_AlamatDom);
        d.setRtDom(val_RtDom);
        d.setRwDom(val_RwDom);
        d.setProvDom(val_ProvDom);
        d.setKotaDom(val_KotaDom);
        d.setKecDom(val_KecDom);
        d.setKelDom(val_KelDom);
        d.setKodePosDom(val_KodePosDom);
        d.setFID_PHOTO_RUMAH1(val_ImgRumah1);
        d.setFID_PHOTO_RUMAH2(val_ImgRumah2);
        d.setValiditasTempatTinggal(val_ValiditasTptTinggal);

        realm.commitTransaction();

//        DataLengkapPojo data = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", DataLengkapActivity.idAplikasi).findFirst();
//
//        d.setCif(data.getCif());
//        d.setUid(data.getUid());
//        d.setIdAplikasi(data.getIdAplikasi());
//        d.setNamaAlias(data.getNamaAlias());
//        d.setNoKtpPasangan(data.getNoKtpPasangan());
//        d.setStatusNikah(data.getStatusNikah());
//        d.setNoHp(data.getNoHp());
//        d.setNamaNasabah(data.getNamaNasabah());
//        d.setNpwp(data.getNpwp());
//        d.setPendidikanTerakhir(data.getPendidikanTerakhir());
//        d.setKetAgama(data.getKetAgama());
//        d.setAgama(data.getAgama());
//        d.setNamaIbu(data.getNamaIbu());
//        d.setNamaPasangan(data.getNamaPasangan());
//        d.setEmail(data.getEmail());
//        d.setTelpKeluarga(data.getTelpKeluarga());
//        d.setExpId(data.getExpId());
//        d.setTgllahirPasangan(data.getTgllahirPasangan());
//        d.setNoKtp(data.getNoKtp());
//        d.setJlhTanggungan(data.getJlhTanggungan());
//        d.setTglLahir(data.getTglLahir());
//        d.setKeluarga(data.getKeluarga());
//        d.setTptLahir(data.getTptLahir());
//        d.setTipePendapatan(data.getTipePendapatan());
//        d.setJenkel(data.getJenkel());
//        d.setReferensi(data.getReferensi());





//        realm.executeTransaction (new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.insertOrUpdate(d);
//            }
//        });
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        if (val_domAsId == 1){
            return validateFormDomasId();
        }
        else {
            return validateForm();
        }
    }

    @Override
    public void onSelected() {
        if (bitmapPhotoRumah1 != null){
            img_fotorumah1.setImageBitmap(bitmapPhotoRumah1);
        }
        if (bitmapPhotoRumah2 != null){
            img_fotorumah2.setImageBitmap(bitmapPhotoRumah2);
        }
    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            //STATUS DOMISILI
            case R.id.et_statusdom:
            case R.id.tf_statusdom:
                openKeyValueDialog(tf_statusdom.getLabelText().toString().trim());
                break;

            case R.id.btn_ktp:
                btnAddressString = "ktp";
                openAddressDialog();
                break;

            case R.id.btn_dom:
                btnAddressString = "dom";
                openAddressDialog();
                break;

            case R.id.btn_upload_fotorumah1:
                isSelectPhoto = "fotorumah1";
                BSBottomCamera.displayWithTitle(getActivity().getSupportFragmentManager(), this, "Foto Rumah 1");
                break;

            case R.id.btn_upload_fotorumah2:
                isSelectPhoto = "fotorumah2";
                BSBottomCamera.displayWithTitle(getActivity().getSupportFragmentManager(), this, "Foto Rumah 2");
                break;

            case R.id.img_fotorumah1:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_fotorumah1.getDrawable()).getSourceBitmap());
                break;

            case R.id.img_fotorumah2:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_fotorumah2.getDrawable()).getSourceBitmap());
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            switch (v.getId()){
                case R.id.et_statusdom:
                    openKeyValueDialog(tf_statusdom.getLabelText().toString().trim());
                    break;
                case R.id.et_validitas_tempat_tinggal:
                    openKeyValueDialog(tf_validitas_tempat_tinggal.getLabelText().toString().trim());
                    break;
            }
        }
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase("status tempat domisili")){
            et_statusdom.setText(data.getName());
            AppUtil.dynamicIconLogoChangeDropdown(getContext(),tf_statusdom,et_statusdom);
        }
        else if (title.equalsIgnoreCase("validitas tempat tinggal")){
            et_validitas_tempat_tinggal.setText(data.getName());
            val_ValiditasTptTinggal=data.getValue();
            AppUtil.dynamicIconLogoChangeDropdown(getContext(),tf_validitas_tempat_tinggal,et_validitas_tempat_tinggal);
        }
    }

    @Override
    public void onAddressSelect(address data) {
        if (btnAddressString.equalsIgnoreCase("ktp")){
            et_provinsiktp.setText(data.getProvinsi());
            et_kotaktp.setText(data.getKota());
            et_kecamatanktp.setText(data.getKecamatan());
            et_kelurahanktp.setText(data.getKelurahan());
            et_kodeposktp.setText(data.getKodepos());
        }
        else{
            et_provinsidom.setText(data.getProvinsi());
            et_kotadom.setText(data.getKota());
            et_kecamatandom.setText(data.getKecamatan());
            et_kelurahandom.setText(data.getKelurahan());
            et_kodeposdom.setText(data.getKodepos());
        }
    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        if (isSelectPhoto.equalsIgnoreCase("fotorumah1")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_RUMAH1);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_RUMAH1);
                    break;
            }
        }
        else if (isSelectPhoto.equalsIgnoreCase("fotorumah2")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_RUMAH2);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_RUMAH2);
                    break;
            }
        }
    }

    private void openCamera(int cameraCode) {
        checkCameraPermission(cameraCode);
    }
    public void openGalery(int cameraCode) {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(Intent.createChooser(it, "Select File"), cameraCode);
    }

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static int CAMERA_CODE_FORE_PERMISSION = 0;
    public void checkCameraPermission(int cameraCode) {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);
        } else {
            Uri outputFileUri = getCaptureImageOutputUri();
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(captureIntent, cameraCode);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                AppUtil.showToastLong(getContext(), "Camera Permission Granted");
                directOpenCamera(CAMERA_CODE_FORE_PERMISSION);
            } else {
                AppUtil.showToastLong(getContext(), "Camera Permission Denied");
            }
        }
    }
    private void directOpenCamera(int cameraCode) {
        Uri outputFileUri = getCaptureImageOutputUri();
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(captureIntent, cameraCode);
    }
    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getActivity().getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                outputFileUri = FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "fotoagunan.png"));
            } else {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "fotoagunan.png"));
            }
        }
        return outputFileUri;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE_RUMAH1:
                case PICK_PICTURE_RUMAH1:
                    setDataImage(uriPhotoRumah1, bitmapPhotoRumah1, img_fotorumah1, data);
                    break;
                case TAKE_PICTURE_RUMAH2:
                case PICK_PICTURE_RUMAH2:
                    setDataImage(uriPhotoRumah2, bitmapPhotoRumah2, img_fotorumah2, data);
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
    private void setDataImage(Uri uri, Bitmap bitmap, ImageView iv, Intent data) {
        initImageFileName();
        if (getPickImageResultUri(data) != null) {
            uri = getPickImageResultUri(data);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), getPickImageResultUri(data));
                bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                bitmap = AppUtil.rotateImageIfRequired(getContext(), bitmap, uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String filename = appPreferences.getFotoRumah();
            ImageHandler.saveImageToCache(getContext(), bitmap, filename);
            uploadFoto(filename, iv, bitmap);
        }
    }

    private void initImageFileName() {
        String fileName = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()) + "_" + System.currentTimeMillis() + ".jpg";
        appPreferences.setFotoRumah(fileName);
    }

    public void uploadFoto(String filename, final ImageView iv, final Bitmap bitmap) {
        if(iv == img_fotorumah1){
            progress_rumah1.setVisibility(View.VISIBLE);
        }
        if(iv == img_fotorumah2){
            progress_rumah2.setVisibility(View.VISIBLE);
        }
//        loading.setVisibility(View.VISIBLE);
        File imageFile = new File(getActivity().getApplicationContext().getCacheDir(), filename);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().uploadFileOld(fileBody);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
//                            loading.setVisibility(View.GONE);
                            Snackbar snackbar;
                            snackbar=AppUtil.notifsuccessWithButton(getContext(),getActivity().findViewById(android.R.id.content),"Foto berhasil diupload");
                            snackbar.setAction("Tutup", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    snackbar.dismiss();
                                }
                            });
                            iv.setImageBitmap(bitmap);
                            if(iv == img_fotorumah1){
                                progress_rumah1.setVisibility(View.GONE);
                                bitmapPhotoRumah1 = bitmap;
                            }
                            if(iv == img_fotorumah2){
                                progress_rumah2.setVisibility(View.GONE);
                                bitmapPhotoRumah2 = bitmap;
                            }

                            int idFoto = response.body().getData().get("id").getAsInt();
                            switch (isSelectPhoto){
                                case "fotorumah1":
                                    val_ImgRumah1 = idFoto;
                                    break;
                                case "fotorumah2":
                                    val_ImgRumah2 = idFoto;
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            if(iv == img_fotorumah1){
                                progress_rumah1.setVisibility(View.GONE);
                            }
                            if(iv == img_fotorumah2){
                                progress_rumah2.setVisibility(View.GONE);
                            }
//                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        if(iv == img_fotorumah1){
                            progress_rumah1.setVisibility(View.GONE);
                        }
                        if(iv == img_fotorumah2){
                            progress_rumah2.setVisibility(View.GONE);
                        }
//                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                if(iv == img_fotorumah1){
                    progress_rumah1.setVisibility(View.GONE);
                }
                if(iv == img_fotorumah2){
                    progress_rumah2.setVisibility(View.GONE);
                }
//                loading.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void setDynamicIcon(){
        AppUtil.dynamicIconLogoChange(getContext(),et_alamatktp,tf_alamatktp);
        AppUtil.dynamicIconLogoChange(getContext(),et_rtktp,tf_rtktp);
        AppUtil.dynamicIconLogoChange(getContext(),et_rwktp,tf_rwktp);
        AppUtil.dynamicIconLogoChange(getContext(),et_provinsiktp,tf_provinsiktp);
        AppUtil.dynamicIconLogoChange(getContext(),et_kotaktp,tf_kotaktp);
        AppUtil.dynamicIconLogoChange(getContext(),et_kecamatanktp,tf_kecamatanktp);
        AppUtil.dynamicIconLogoChange(getContext(),et_kelurahanktp,tf_kelurahanktp);
        AppUtil.dynamicIconLogoChange(getContext(),et_kodeposktp,tf_kodeposktp);
        AppUtil.dynamicIconLogoChange(getContext(),et_lamadom,tf_lamadom);
        AppUtil.dynamicIconLogoChange(getContext(),et_alamatdom,tf_alamatdom);
        AppUtil.dynamicIconLogoChange(getContext(),et_rtdom,tf_rtdom);
        AppUtil.dynamicIconLogoChange(getContext(),et_rwdom,tf_rwdom);
        AppUtil.dynamicIconLogoChange(getContext(),et_provinsidom,tf_provinsidom);
        AppUtil.dynamicIconLogoChange(getContext(),et_kotadom,tf_kotadom);
        AppUtil.dynamicIconLogoChange(getContext(),et_kecamatandom,tf_kecamatandom);
        AppUtil.dynamicIconLogoChange(getContext(),et_kelurahandom,tf_kelurahandom);
        AppUtil.dynamicIconLogoChange(getContext(),et_kodeposdom,tf_kodeposdom);
    }

    private void setDynamicIconDropDown(){
        AppUtil.dynamicIconLogoChangeDropdown(getContext(),tf_statusdom,et_statusdom);
        AppUtil.dynamicIconLogoChangeDropdown(getContext(),tf_validitas_tempat_tinggal,et_validitas_tempat_tinggal);
    }
}