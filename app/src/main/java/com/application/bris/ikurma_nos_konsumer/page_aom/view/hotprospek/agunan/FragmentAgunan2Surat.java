package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.InputType;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.AgunanTanahBangunanPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanTanahBangunan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.ImageHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.makeramen.roundedimageview.RoundedDrawable;
import com.makeramen.roundedimageview.RoundedImageView;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import static com.application.bris.ikurma_nos_konsumer.util.magiccrypt.lib.Crypt.BUFFER_SIZE;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunan2Surat extends Fragment implements Step, KeyValueListener, View.OnClickListener, View.OnFocusChangeListener, CameraListener {

    @BindView(R.id.tf_jenis_surat)
    TextFieldBoxes tf_jenis_surat;
    @BindView(R.id.et_jenis_surat)
    ExtendedEditText et_jenis_surat;

    @BindView(R.id.tf_no_sertifikat)
    TextFieldBoxes tf_no_sertifikat;
    @BindView(R.id.et_no_sertifikat)
    ExtendedEditText et_no_sertifikat;

    @BindView(R.id.tf_atas_nama_sertifikat)
    TextFieldBoxes tf_atas_nama_sertifikat;
    @BindView(R.id.et_atas_nama_sertifikat)
    ExtendedEditText et_atas_nama_sertifikat;

    @BindView(R.id.tf_tanggal_terbit_sertifikat)
    TextFieldBoxes tf_tanggal_terbit_sertifikat;
    @BindView(R.id.et_tanggal_terbit_sertifikat)
    ExtendedEditText et_tanggal_terbit_sertifikat;

    @BindView(R.id.tf_luas_tanah_sertifikat)
    TextFieldBoxes tf_luas_tanah_sertifikat;
    @BindView(R.id.et_luas_tanah_sertifikat)
    ExtendedEditText et_luas_tanah_sertifikat;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    @BindView(R.id.img_bpn)
    RoundedImageView img_bpn;
    @BindView(R.id.btn_upload_bpn)
    ImageView btn_upload_bpn;

    @BindView(R.id.img_pbb)
    RoundedImageView img_pbb;
    @BindView(R.id.btn_upload_pbb)
    ImageView btn_upload_pbb;

    @BindView(R.id.img_imb)
    RoundedImageView img_imb;
    @BindView(R.id.btn_upload_imb)
    ImageView btn_upload_imb;

    @BindView(R.id.cl_content)
    ScrollView cl_content;

    private Calendar calTerbit;
    private DatePickerDialog dpTanggalTerbit;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    private AgunanTanahBangunan dataAgunan;
    private String idAgunan="";

    public static String val_JenisSurat ="";
    public static String val_NoSertifikat ="";
    public static String val_AtasNamaSertifikat ="";
    public static String val_TanggalTerbitSertifikat ="";
    public static String val_LuasTanahSertifikat ="";
    public static String val_ImgBpn="";
    public static String val_ImgPbb="";
    public static String val_ImgImb="";

    //START EMPTY STRING
    public static String val_NoGbr ="";
    public static String val_HubNasabahDenganPemegangHak ="";
    public static String val_MasaHakAtasTanah ="";

    private final int PICK_IMAGE_BPN = 1;
    private final int TAKE_PICTURE_BPN = 11;

    private final int PICK_IMAGE_PBB = 2;
    private final int TAKE_PICTURE_PBB = 12;

    private final int PICK_IMAGE_IMB = 3;
    private final int TAKE_PICTURE_IMB = 13;

    private static final String IMAGE_DIRECTORY = "/file_kelengkapan_dokumen";

    private Uri uriPhotoBpn;
    private Bitmap bitmapPhotoBpn;

    private Uri uriPhotoPbb;
    private Bitmap bitmapPhotoPbb;

    private Uri uriPhotoImb;
    private Bitmap bitmapPhotoImb;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;



    private String idGlobal = "";

    private String idBtnBpn = "";
    private String sudahUploadBpn = "belum";
    private int idFotoBpn = 0;

    private String idBtnPbb = "";
    private String sudahUploadPbb = "belum";
    private int idFotoPbb = 0;

    private String idBtnImb = "";
    private String sudahUploadImb = "belum";
    private int idFotoImb = 0;

    private Realm realm;
    private boolean enableEditText;

    public FragmentAgunan2Surat() {
    }

    public FragmentAgunan2Surat(String midAgunan, AgunanTanahBangunan magunanTanahBangunan) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
    }

    public FragmentAgunan2Surat(String midAgunan, AgunanTanahBangunan magunanTanahBangunan,boolean enableEditText) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
        this.enableEditText=enableEditText;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.agunan_2_identifikasi_surat, container, false);
        ButterKnife.bind(this,view);
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());
        onSelectDialog();
//        if(!idAgunan.equalsIgnoreCase("0")) {
//            setData();
//        }

        setData();

        otherViewChanges();

        img_bpn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_bpn.getDrawable()).getSourceBitmap());
//                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_bpn.getDrawable()).getSourceBitmap());
                if(val_ImgBpn.isEmpty()){
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("application/pdf");

                    idBtnBpn = "sertifikat";
                    idGlobal = idBtnBpn;
                    startActivityForResult(intent,TAKE_PICTURE_BPN);
                }
                else{
                    setLoadPdf(Integer.parseInt(val_ImgBpn));
                }

            }
        });
        btn_upload_bpn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                idBtnBpn = "sertifikat";
//                idGlobal = idBtnBpn;
//                openCameraMenu();

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");

                idBtnBpn = "sertifikat";
                idGlobal = idBtnBpn;
                startActivityForResult(intent,TAKE_PICTURE_BPN);
            }
        });

        img_pbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_pbb.getDrawable()).getSourceBitmap());
            }
        });
        btn_upload_pbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idBtnPbb = "pbb";
                idGlobal = idBtnPbb;
                openCameraMenu();
            }
        });

        img_imb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_imb.getDrawable()).getSourceBitmap());
            }
        });
        btn_upload_imb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idBtnImb = "imb";
                idGlobal = idBtnImb;
                openCameraMenu();
            }
        });


        return view;
    }

    private void onSelectDialog(){

        et_jenis_surat.setFocusable(false);
        et_jenis_surat.setInputType(InputType.TYPE_NULL);
        et_jenis_surat.setOnClickListener(this);
        et_jenis_surat.setOnFocusChangeListener(this);
        tf_jenis_surat.setOnClickListener(this);
        tf_jenis_surat.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_jenis_surat.getLabelText().toString().trim());
            }
        });

        et_tanggal_terbit_sertifikat.setFocusable(false);
        et_tanggal_terbit_sertifikat.setInputType(InputType.TYPE_NULL);
        et_tanggal_terbit_sertifikat.setOnFocusChangeListener(this);
        tf_tanggal_terbit_sertifikat.setOnClickListener(this);
        tf_tanggal_terbit_sertifikat.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTglTerbit();
            }
        });
    }

    private void openKeyValueDialog(String title){
        DialogKeyValue.display(getFragmentManager(), title, this);
    }

    private void setData(){
        try {
            et_jenis_surat.setText(dataAgunan.getJenisSuratTanah());
            et_no_sertifikat.setText(dataAgunan.getNoSertifikat());
            et_atas_nama_sertifikat.setText(dataAgunan.getAtasNamaSertifikat());
            et_tanggal_terbit_sertifikat.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTanggalSertifikat(), "ddMMyyyy", "dd-MM-yyyy"));
            et_luas_tanah_sertifikat.setText(dataAgunan.getLuasTanahSertifikat());


            //PDF
            if (dataAgunan.getIdPhotoTBbpn() != 0) {
                img_bpn.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_pdf_hd));
                final int id_bpn = dataAgunan.getIdPhotoTBbpn();
                val_ImgBpn = String.valueOf(id_bpn);
                sudahUploadBpn = "sudah";
            }

            //FOTO DOKUMEN
//            final int id_bpn = dataAgunan.getIdPhotoTBbpn();
            final int id_pbb = dataAgunan.getIdPhotoTBPbb();
            final int id_imb = dataAgunan.getIdPhotoTBImb();

//            String ImgBpn = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + id_bpn;
//            val_ImgBpn = String.valueOf(id_bpn);
//            Glide
//                    .with(getContext())
//                    .asBitmap()
//                    .load(ImgBpn)
//                    .into(new SimpleTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                            img_bpn.setImageBitmap(resource);
//                            bitmapPhotoBpn = resource;
//                            sudahUploadBpn = "sudah";
//                        }
//                    });


            String ImgPbb = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + id_pbb;
            val_ImgPbb = String.valueOf(id_pbb);
            Glide
                    .with(getContext())
                    .asBitmap()
                    .load(ImgPbb)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            img_pbb.setImageBitmap(resource);
                            bitmapPhotoPbb = resource;
                            sudahUploadPbb = "sudah";
                        }
                    });


            String ImgImb = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + id_imb;
            val_ImgImb = String.valueOf(id_imb);
            Glide
                    .with(getContext())
                    .asBitmap()
                    .load(ImgImb)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            img_imb.setImageBitmap(resource);
                            bitmapPhotoImb = resource;
                            sudahUploadImb = "sudah";
                        }
                    });

            if(enableEditText==false){
                AppUtil.disableEditTexts(cl_content);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_tanggal_terbit_sertifikat:
            case R.id.tf_tanggal_terbit_sertifikat:
                dpTglTerbit();
                break;

            case R.id.et_jenis_surat:
            case R.id.tf_jenis_surat:
                openKeyValueDialog(tf_jenis_surat.getLabelText().toString().trim());
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            switch (v.getId()){
                case R.id.et_tanggal_terbit_sertifikat:
                case R.id.tf_tanggal_terbit_sertifikat:
                    dpTglTerbit();
                    break;

                case R.id.et_jenis_surat:
                case R.id.tf_jenis_surat:
                    openKeyValueDialog(tf_jenis_surat.getLabelText().toString().trim());
                    break;
            }
        }
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return  validateForm();
    }

    @Override
    public void onSelected() {
        if (bitmapPhotoBpn != null){
            img_bpn.setImageBitmap(bitmapPhotoBpn);
        }
    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notifwarning(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase("jenis surat tanah")){
            et_jenis_surat.setText(data.getName());
        }
    }

    private void dpTglTerbit(){
        calTerbit = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ls_expiredDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calTerbit.set(Calendar.YEAR, year);
                calTerbit.set(Calendar.MONTH, month);
                calTerbit.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calExpiredString = dateClient.format(calTerbit.getTime());
                et_tanggal_terbit_sertifikat.setText(calExpiredString);
            }
        };

        dpTanggalTerbit = new DatePickerDialog(getContext(), R.style.AppTheme_TimePickerTheme, ls_expiredDate, calTerbit.get(Calendar.YEAR),
                calTerbit.get(Calendar.MONTH), calTerbit.get(Calendar.DAY_OF_MONTH));
        dpTanggalTerbit.getDatePicker().setMaxDate(calTerbit.getTimeInMillis());
        dpTanggalTerbit.show();
    }

    private VerificationError validateForm(){
        if (et_jenis_surat.getText().toString().isEmpty() || et_jenis_surat.getText().toString().equalsIgnoreCase("Pilih")){
            tf_jenis_surat.setError("Format "+ tf_jenis_surat.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_jenis_surat.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_no_sertifikat.getText().toString().isEmpty() || et_no_sertifikat.getText().toString().equalsIgnoreCase("")){
            tf_no_sertifikat.setError("Format "+ tf_no_sertifikat.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_no_sertifikat.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_jenis_surat.getText().toString().equalsIgnoreCase("SHGB") && (et_atas_nama_sertifikat.getText().toString().isEmpty() || et_atas_nama_sertifikat.getText().toString().equalsIgnoreCase(""))){
            tf_atas_nama_sertifikat.setError("Format "+ tf_atas_nama_sertifikat.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_atas_nama_sertifikat.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_tanggal_terbit_sertifikat.getText().toString().isEmpty() || et_tanggal_terbit_sertifikat.getText().toString().equalsIgnoreCase("")){
            tf_tanggal_terbit_sertifikat.setError("Format "+ tf_tanggal_terbit_sertifikat.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_tanggal_terbit_sertifikat.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_luas_tanah_sertifikat.getText().toString().isEmpty() || et_luas_tanah_sertifikat.getText().toString().equalsIgnoreCase("")){
            tf_luas_tanah_sertifikat.setError("Format "+ tf_luas_tanah_sertifikat.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_luas_tanah_sertifikat.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if(val_ImgBpn.isEmpty()){
            return new VerificationError("Silahkan upload Dokumen Nomor Sertifikat");
        }

        //validasi dicopot dlu karena di servicenya masih belom ngesave id bpn dan imb
        else if(val_ImgPbb.isEmpty()){
            return new VerificationError("Silahkan pilih / ambil Foto PBB");
        }
        else if(val_ImgImb.isEmpty()){
            return new VerificationError("Silahkan pilih / ambil Foto IMB");
        }

        else {
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner(){
        try {
            val_JenisSurat = (et_jenis_surat.getText().toString().trim());
            val_NoSertifikat = (et_no_sertifikat.getText().toString().trim());
            if (et_jenis_surat.getText().toString().equalsIgnoreCase("SHGB")){
                val_AtasNamaSertifikat = (et_atas_nama_sertifikat.getText().toString().trim());
            } else {
                if (et_atas_nama_sertifikat.getText().toString().equalsIgnoreCase("") || et_atas_nama_sertifikat.getText().toString().isEmpty()) {
                    val_AtasNamaSertifikat = "";
                } else {
                    val_AtasNamaSertifikat = (et_atas_nama_sertifikat.getText().toString().trim());
                }
            }
            val_TanggalTerbitSertifikat = (AppUtil.parseTanggalGeneral(et_tanggal_terbit_sertifikat.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
            val_LuasTanahSertifikat = (et_luas_tanah_sertifikat.getText().toString().trim());

            final AgunanTanahBangunanPojo d = new AgunanTanahBangunanPojo();
            d.setJenisSuratTanah(val_JenisSurat);
            d.setNoSertifikat(val_NoSertifikat);
            d.setAtasNamaSertifikat(val_AtasNamaSertifikat);
            d.setTanggalSertifikat(val_TanggalTerbitSertifikat);
            d.setLuasTanahSertifikat(val_LuasTanahSertifikat);
            d.setIdDok_BPN(AppUtil.parseIntWithDefault(val_ImgBpn, 0));
            d.setNoGS(val_NoGbr);
            d.setHubNasabahDgnPemegangHak(val_HubNasabahDenganPemegangHak);
            d.setMasaHakAtasTanah(val_MasaHakAtasTanah);
            d.setIdDokPBB(AppUtil.parseIntWithDefault(val_ImgPbb, 0));
            d.setIdDokIMB(AppUtil.parseIntWithDefault(val_ImgImb, 0));

            AgunanTanahBangunanPojo dataR = realm.where(AgunanTanahBangunanPojo.class).equalTo("uuid", AgunanTanahBangunanInputActivity.uuid).findFirst();

            d.setUuid(dataR.getUuid());
            d.setIdAgunan(dataR.getIdAgunan());
            d.setIdApl(dataR.getIdApl());
            d.setCif(dataR.getCif());
            d.setLokasiTanah(dataR.getLokasiTanah());
            d.setKodePos(dataR.getKodePos());
            d.setKelurahan(dataR.getKelurahan());
            d.setKecamatan(dataR.getKecamatan());
            d.setKota(dataR.getKota());
            d.setPropinsi(dataR.getPropinsi());
            d.setBatasUtaraTanah(dataR.getBatasUtaraTanah());
            d.setBatasSelatanTanah(dataR.getBatasSelatanTanah());
            d.setBatasBaratTanah(dataR.getBatasBaratTanah());
            d.setBatasTimurTanah(dataR.getBatasTimurTanah());
            d.setKavBlok(dataR.getKavBlok());
            d.setRT(dataR.getRT());
            d.setRW(dataR.getRW());
            d.setKoordinat(dataR.getKoordinat());
            d.setBentukTanah(dataR.getBentukTanah());
            d.setPermukaanTanah(dataR.getPermukaanTanah());

            //flpp
            d.setKodeWilayah(dataR.getKodeWilayah());
            d.setNoAlamat(dataR.getNoAlamat());

            realm.executeTransaction (new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(d);
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void openCameraMenu() {
        BSBottomCamera.displayWithTitle(getActivity().getSupportFragmentManager(), this, "Foto Dokumen");
    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        if (idGlobal.equalsIgnoreCase("sertifikat")) {
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_BPN);
                    break;
                case "Pick Photo":
                    openGalery(PICK_IMAGE_BPN);
                    break;
            }
        }

        if (idGlobal.equalsIgnoreCase("pbb")) {
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_PBB);
                    break;
                case "Pick Photo":
                    openGalery(PICK_IMAGE_PBB);
                    break;
            }
        }

        if (idGlobal.equalsIgnoreCase("imb")) {
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_IMB);
                    break;
                case "Pick Photo":
                    openGalery(PICK_IMAGE_IMB);
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

                case TAKE_PICTURE_BPN:
                case PICK_IMAGE_BPN:
//                    setDataImage(uriPhotoBpn, bitmapPhotoBpn, img_bpn, data);
                    setDataPdf(img_bpn, data);
                    break;
                case TAKE_PICTURE_PBB:
                case PICK_IMAGE_PBB:
                    setDataImage(uriPhotoPbb, bitmapPhotoPbb, img_pbb, data);
                    break;
                case TAKE_PICTURE_IMB:
                case PICK_IMAGE_IMB:
                    setDataImage(uriPhotoImb, bitmapPhotoImb, img_imb, data);
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
                bitmap = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), uri);
                bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                bitmap = AppUtil.rotateImageIfRequired(getContext(), bitmap, uri);
                iv.setImageBitmap(bitmap);
                bitmapPhotoBpn = bitmap;
                bitmapPhotoImb=bitmap;
                bitmapPhotoPbb=bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }

            String filename = appPreferences.getFotoAgunan();
            ImageHandler.saveImageToCache(getContext(), bitmap, filename);
            uploadFoto(filename);
        }
    }

    private void initImageFileName() {
        String fileName = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()) + "_" + System.currentTimeMillis() + ".jpg";
        appPreferences.setFotoAgunan(fileName);
    }

    public void uploadFoto(String filename) {
        loading.setVisibility(View.VISIBLE);
        File imageFile = new File(getActivity().getApplicationContext().getCacheDir(), filename);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().uploadFoto(fileBody);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            if (idGlobal.equalsIgnoreCase("sertifikat")) {
                                idFotoBpn = response.body().getData().get("id").getAsInt();
                                val_ImgBpn = String.valueOf(idFotoBpn);
                                sudahUploadBpn = "sudah";
                            }
                            else  if (idGlobal.equalsIgnoreCase("pbb")) {
                                idFotoPbb = response.body().getData().get("id").getAsInt();
                                val_ImgPbb = String.valueOf(idFotoPbb);
                                sudahUploadPbb = "sudah";
                            }
                            else  if (idGlobal.equalsIgnoreCase("imb")) {
                                idFotoImb = response.body().getData().get("id").getAsInt();
                                val_ImgImb = String.valueOf(idFotoImb);
                                sudahUploadImb = "sudah";
                            }
                        } else {
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    //UPLOAD PDF METHODS
    private void initImageFileNameDokumen() {
        String fileName = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date())+"_"+System.currentTimeMillis()+ ".jpg";
//        appPreferences.setDokumenSuratNikah(fileName);
    }

    private void setDataPdf(ImageView iv, Intent data){
        iv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_pdf_hd));
        initImageFileNameDokumen();
        Uri uri = data.getData();
        String filename = appPreferences.getFotoKelengkapanDokumen();
        String path = getFilePathFromURI(getContext(), uri, filename);
        uploadFile(path, iv);
    }

    public static String getFilePathFromURI(Context context, Uri contentUri, String filename) {
        //copy file and send new file path
        String name = getFileName(contentUri);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }
        if (!TextUtils.isEmpty(name)) {
            File copyFile = new File(wallpaperDirectory + File.separator + name);
            // create folder if not exists

            copy(context, contentUri, copyFile);
            return copyFile.getAbsolutePath();
        }
        return null;
    }

    public static void copy(Context context, Uri srcUri, File dstFile) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(srcUri);
            if (inputStream == null) return;
            OutputStream outputStream = new FileOutputStream(dstFile);
            copystream(inputStream, outputStream);
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int copystream(InputStream input, OutputStream output) throws Exception, IOException {
        byte[] buffer = new byte[BUFFER_SIZE];

        BufferedInputStream in = new BufferedInputStream(input, BUFFER_SIZE);
        BufferedOutputStream out = new BufferedOutputStream(output, BUFFER_SIZE);
        int count = 0, n = 0;
        try {
            while ((n = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
                out.write(buffer, 0, n);
                count += n;
            }
            out.flush();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
            try {
                in.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
        }
        return count;
    }

    public static String getFileName(Uri uri) {
        if (uri == null) return null;
        String fileName = null;
        String path = uri.getPath();
        int cut = path.lastIndexOf('/');
        if (cut != -1) {
            fileName = path.substring(cut + 1);
        }
        return fileName;
    }

    public void uploadFile(String filename, final ImageView iv){
//        Log.d("Test Masuk", "uploadFile");
        loading.setVisibility(View.VISIBLE);
        File file = new File(filename);
//        File file = new File(getApplicationContext().getCacheDir(), filename);
//        File file = new File(KonsumerKmgKelengkapanDokumenActivity.class, filename);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        String fileNamePdf = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date())+"_"+System.currentTimeMillis()+ ".pdf";
        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", fileNamePdf, requestBody);
//        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), pdfname);

//        PDFInterface getResponse = retrofit.create(PDFInterface.class);
//        Call<ParseResponse> call = getResponse.uploadImage(fileToUpload);

//        File imageFile = new File(getApplicationContext().getCacheDir(), filename);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), imageFile);
//        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().uploadFile(fileBody);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    loading.setVisibility(View.GONE);
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            int idPdF = response.body().getData().get("id").getAsInt();
//                            Log.d("idPdF", String.valueOf(idPdF));

                            switch (idGlobal){
                                case "sertifikat":
                                    val_ImgBpn = String.valueOf(idPdF);
                                    break;
                                default:
                                    break;
                            }
                        }
                        else{
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        loading.setVisibility(View.GONE);
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
                Log.d("onFailure", t.toString());
                AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void setLoadPdf(int idFoto) {
        String url_pdf = UriApi.Baseurl.URL + UriApi.hotprospek.urlPdf + idFoto;
        Uri external = Uri.parse(url_pdf);
        Intent intentPdf;
        intentPdf = new Intent(Intent.ACTION_VIEW);
        intentPdf.setDataAndType(external, "application/pdf");
        try {
            startActivity(intentPdf);
        } catch (ActivityNotFoundException e) {
            // No application to view, ask to download one
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("No Application Found");
            builder.setMessage("Download one from Android Market?");
            builder.setPositiveButton("Yes, Please",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent marketIntent = new Intent(Intent.ACTION_VIEW);
                            marketIntent
                                    .setData(Uri
                                            .parse("market://details?id=com.adobe.reader"));
                            startActivity(marketIntent);
                        }
                    });
            builder.setNegativeButton("No, Thanks", null);
            builder.create().show();
        }
    }


    //end OF PDF METHODS



    private void otherViewChanges(){
        if(enableEditText==false){
            btn_upload_bpn.setVisibility(View.GONE);
            btn_upload_imb.setVisibility(View.GONE);
            btn_upload_pbb.setVisibility(View.GONE);
        }
    }
}

