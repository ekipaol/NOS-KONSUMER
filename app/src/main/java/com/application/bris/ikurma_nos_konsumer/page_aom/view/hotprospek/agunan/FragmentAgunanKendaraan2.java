package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.AgunanKendaraanPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanKendaraan;
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
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * A simple {@link Fragment} subclass.
 */

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunanKendaraan2 extends Fragment implements Step, KeyValueListener, View.OnClickListener, View.OnFocusChangeListener, CameraListener {
    //VIEW BINDING
    @BindView(R.id.tf_hub_pemilik_dengan_nasabah)
    TextFieldBoxes tf_hub_pemilik_dengan_nasabah;
    @BindView(R.id.et_hub_pemilik_dengan_nasabah)
    ExtendedEditText et_hub_pemilik_dengan_nasabah;
    @BindView(R.id.tf_no_faktur)
    TextFieldBoxes tf_no_faktur;
    @BindView(R.id.et_no_faktur)
    ExtendedEditText et_no_faktur;
    @BindView(R.id.tf_no_mesin)
    TextFieldBoxes tf_no_mesin;
    @BindView(R.id.et_no_mesin)
    ExtendedEditText et_no_mesin;
    @BindView(R.id.tf_bukti_gesek_mesin)
    TextFieldBoxes tf_bukti_gesek_mesin;
    @BindView(R.id.et_bukti_gesek_mesin)
    ExtendedEditText et_bukti_gesek_mesin;
    @BindView(R.id.tf_no_rangka)
    TextFieldBoxes tf_no_rangka;
    @BindView(R.id.et_no_rangka)
    ExtendedEditText et_no_rangka;
    @BindView(R.id.tf_bukti_gesek_rangka)
    TextFieldBoxes tf_bukti_gesek_rangka;
    @BindView(R.id.et_bukti_gesek_rangka)
    ExtendedEditText et_bukti_gesek_rangka;
    @BindView(R.id.tf_no_polisi)
    TextFieldBoxes tf_no_polisi;
    @BindView(R.id.et_no_polisi)
    ExtendedEditText et_no_polisi;
    @BindView(R.id.tf_plat_kuning)
    TextFieldBoxes tf_plat_kuning;
    @BindView(R.id.et_plat_kuning)
    ExtendedEditText et_plat_kuning;
    @BindView(R.id.tf_no_bpkb)
    TextFieldBoxes tf_no_bpkb;
    @BindView(R.id.et_no_bpkb)
    ExtendedEditText et_no_bpkb;
    @BindView(R.id.tf_no_stnk)
    TextFieldBoxes tf_no_stnk;
    @BindView(R.id.et_no_stnk)
    ExtendedEditText et_no_stnk;
    @BindView(R.id.tf_warna)
    TextFieldBoxes tf_warna;
    @BindView(R.id.et_warna)
    ExtendedEditText et_warna;
    @BindView(R.id.tf_thn_pembuatan)
    TextFieldBoxes tf_thn_pembuatan;
    @BindView(R.id.et_thn_pembuatan)
    ExtendedEditText et_thn_pembuatan;
    @BindView(R.id.img_bpkb)
    RoundedImageView img_bpkb;
    @BindView(R.id.btn_upload_bpkb)
    ImageView btn_upload_bpkb;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    //END OF VIEW BINDING
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String idAgunan;
    private AgunanKendaraan dataAgunan;
    public static String val_HubPemilikDenganNasabah ="";
    public static String val_NoFaktur ="";
    public static String val_NoMesin ="";
    public static String val_BuktiGesekMesin ="";
    public static String val_NoRangka ="";
    public static String val_BuktiGesekRangka ="";
    public static String val_NoPolisi ="";
    public static String val_PlatKuning ="";
    public static String val_NoBpkb ="";
    public static String val_NoStnk ="";
    public static String val_Warna ="";
    public static String val_ThnPembuatan ="";
    public static String val_ImgBpkb="";
    private final int PICK_IMAGE_BPKB = 1;
    private final int TAKE_PICTURE_BPKB = 11;
    private Uri uriPhotoBpkb;
    private Bitmap bitmapPhotoBpkb;
    private String idBtnBpkb = "";
    private String idGlobal = "";
    private String sudahUploadBpkb = "belum";
    private int idFotoBpkb = 0;

    private Realm realm;

    public FragmentAgunanKendaraan2() {
    }
    public FragmentAgunanKendaraan2(String midAgunan, AgunanKendaraan magunanData) {
        dataAgunan = magunanData;
        idAgunan = midAgunan;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_kendaraan_2, container, false);
        ButterKnife.bind(this,view);
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());
        onSelectDialog();
        if(!String.valueOf(idAgunan).equalsIgnoreCase("0")) {
            setData();
        }
        btn_upload_bpkb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idGlobal = "bpkb";
                openCameraMenu();
            }
        });
        img_bpkb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_bpkb.getDrawable()).getSourceBitmap());
            }
        });
        return view;
    }
    private void onSelectDialog(){
        et_hub_pemilik_dengan_nasabah.setFocusable(false);
        et_hub_pemilik_dengan_nasabah.setInputType(InputType.TYPE_NULL);
        et_hub_pemilik_dengan_nasabah.setOnClickListener(this);
        et_hub_pemilik_dengan_nasabah.setOnFocusChangeListener(this);
        tf_hub_pemilik_dengan_nasabah.setOnClickListener(this);
        tf_hub_pemilik_dengan_nasabah.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_hub_pemilik_dengan_nasabah.getLabelText().toString().trim());
            }
        });
        et_bukti_gesek_mesin.setFocusable(false);
        et_bukti_gesek_mesin.setInputType(InputType.TYPE_NULL);
        et_bukti_gesek_mesin.setOnClickListener(this);
        et_bukti_gesek_mesin.setOnFocusChangeListener(this);
        tf_bukti_gesek_mesin.setOnClickListener(this);
        tf_bukti_gesek_mesin.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_bukti_gesek_mesin.getLabelText().toString().trim());
            }
        });
        et_bukti_gesek_rangka.setFocusable(false);
        et_bukti_gesek_rangka.setInputType(InputType.TYPE_NULL);
        et_bukti_gesek_rangka.setOnClickListener(this);
        et_bukti_gesek_rangka.setOnFocusChangeListener(this);
        tf_bukti_gesek_rangka.setOnClickListener(this);
        tf_bukti_gesek_rangka.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_bukti_gesek_rangka.getLabelText().toString().trim());
            }
        });
        et_plat_kuning.setFocusable(false);
        et_plat_kuning.setInputType(InputType.TYPE_NULL);
        et_plat_kuning.setOnClickListener(this);
        et_plat_kuning.setOnFocusChangeListener(this);
        tf_plat_kuning.setOnClickListener(this);
        tf_plat_kuning.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_plat_kuning.getLabelText().toString().trim());
            }
        });
    }
    private void openKeyValueDialog(String title){
        DialogKeyValue.display(getFragmentManager(), title, this);
    }
    private void setData(){
        try {
            et_hub_pemilik_dengan_nasabah.setText(dataAgunan.getHubungan());
            et_no_faktur.setText(dataAgunan.getNoFaktur());
            et_no_mesin.setText(dataAgunan.getNoMesin());
            et_bukti_gesek_mesin.setText(dataAgunan.getBuktiGesekMesin());
            et_no_rangka.setText(dataAgunan.getNoRangka());
            et_bukti_gesek_rangka.setText(dataAgunan.getBuktiGesekRangka());
            et_no_polisi.setText(dataAgunan.getNoPolisi());
            et_plat_kuning.setText(dataAgunan.getJenisPlat());
            et_no_bpkb.setText(dataAgunan.getNoBKPB());
            et_no_stnk.setText(dataAgunan.getNoSTNK());
            et_warna.setText(dataAgunan.getWarna());
            et_thn_pembuatan.setText(dataAgunan.getTahunPembuatan());
            final int id_bpn = dataAgunan.getIdPhotoKDBPKB();

            String ImgBpn = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + id_bpn;
            val_ImgBpkb = String.valueOf(id_bpn);
            Glide
                    .with(getContext())
                    .asBitmap()
                    .load(ImgBpn)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            img_bpkb.setImageBitmap(resource);
                            bitmapPhotoBpkb = resource;
                            sudahUploadBpkb = "sudah";
                        }
                    });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_hub_pemilik_dengan_nasabah:
            case R.id.tf_hub_pemilik_dengan_nasabah:
                openKeyValueDialog(tf_hub_pemilik_dengan_nasabah.getLabelText().toString().trim());
                break;
            case R.id.et_bukti_gesek_mesin:
            case R.id.tf_bukti_gesek_mesin:
                openKeyValueDialog(tf_bukti_gesek_mesin.getLabelText().toString().trim());
                break;
            case R.id.et_bukti_gesek_rangka:
            case R.id.tf_bukti_gesek_rangka:
                openKeyValueDialog(tf_bukti_gesek_rangka.getLabelText().toString().trim());
                break;
            case R.id.et_plat_kuning:
            case R.id.tf_plat_kuning:
                openKeyValueDialog(tf_plat_kuning.getLabelText().toString().trim());
                break;
        }
    }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            switch (v.getId()){
                case R.id.et_hub_pemilik_dengan_nasabah:
                case R.id.tf_hub_pemilik_dengan_nasabah:
                    openKeyValueDialog(tf_hub_pemilik_dengan_nasabah.getLabelText().toString().trim());
                    break;
                case R.id.et_bukti_gesek_mesin:
                case R.id.tf_bukti_gesek_mesin:
                    openKeyValueDialog(tf_bukti_gesek_mesin.getLabelText().toString().trim());
                    break;
                case R.id.et_bukti_gesek_rangka:
                case R.id.tf_bukti_gesek_rangka:
                    openKeyValueDialog(tf_bukti_gesek_rangka.getLabelText().toString().trim());
                    break;
                case R.id.et_plat_kuning:
                case R.id.tf_plat_kuning:
                    openKeyValueDialog(tf_plat_kuning.getLabelText().toString().trim());
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
        if (bitmapPhotoBpkb != null){
            img_bpkb.setImageBitmap(bitmapPhotoBpkb);
        }
    }
    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notifwarning(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }
    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase("Hubungan Pemilik Dengan Nasabah")){
            et_hub_pemilik_dengan_nasabah.setText(data.getName());
        }
        else if (title.equalsIgnoreCase("Bukti Gesek Mesin")){
            et_bukti_gesek_mesin.setText(data.getName());
        }
        else if (title.equalsIgnoreCase("Bukti Gesek Rangka")){
            et_bukti_gesek_rangka.setText(data.getName());
        }
        else if (title.equalsIgnoreCase("Plat Kuning")){
            et_plat_kuning.setText(data.getName());
        }
    }
    private VerificationError validateForm(){
        if (et_hub_pemilik_dengan_nasabah.getText().toString().isEmpty() || et_hub_pemilik_dengan_nasabah.getText().toString().equalsIgnoreCase("Pilih")){
            tf_hub_pemilik_dengan_nasabah.setError("Format "+ tf_hub_pemilik_dengan_nasabah.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_hub_pemilik_dengan_nasabah.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_no_faktur.getText().toString().isEmpty() || et_no_faktur.getText().toString().equalsIgnoreCase("")){
            tf_no_faktur.setError("Format "+ tf_no_faktur.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_no_faktur.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_no_mesin.getText().toString().isEmpty() || et_no_mesin.getText().toString().equalsIgnoreCase("")){
            tf_no_mesin.setError("Format "+ tf_no_mesin.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_no_mesin.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_bukti_gesek_mesin.getText().toString().isEmpty() || et_bukti_gesek_mesin.getText().toString().equalsIgnoreCase("Pilih")){
            tf_bukti_gesek_mesin.setError("Format "+ tf_bukti_gesek_mesin.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_bukti_gesek_mesin.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_no_rangka.getText().toString().isEmpty() || et_no_rangka.getText().toString().equalsIgnoreCase("")){
            tf_no_rangka.setError("Format "+ tf_no_rangka.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_no_rangka.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_bukti_gesek_rangka.getText().toString().isEmpty() || et_bukti_gesek_rangka.getText().toString().equalsIgnoreCase("Pilih")){
            tf_bukti_gesek_rangka.setError("Format "+ tf_bukti_gesek_rangka.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_bukti_gesek_rangka.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_no_polisi.getText().toString().isEmpty() || et_no_polisi.getText().toString().equalsIgnoreCase("Pilih")){
            tf_no_polisi.setError("Format "+ tf_no_polisi.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_no_polisi.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_plat_kuning.getText().toString().isEmpty() || et_plat_kuning.getText().toString().equalsIgnoreCase("Pilih")){
            tf_plat_kuning.setError("Format "+ tf_plat_kuning.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_plat_kuning.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_no_bpkb.getText().toString().isEmpty() || et_no_bpkb.getText().toString().equalsIgnoreCase("")){
            tf_no_bpkb.setError("Format "+ tf_no_bpkb.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_no_bpkb.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_no_stnk.getText().toString().isEmpty() || et_no_stnk.getText().toString().equalsIgnoreCase("")){
            tf_no_stnk.setError("Format "+ tf_no_stnk.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_no_stnk.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_warna.getText().toString().isEmpty() || et_warna.getText().toString().equalsIgnoreCase("")){
            tf_warna.setError("Format "+ tf_warna.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_warna.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_thn_pembuatan.getText().toString().isEmpty() || et_thn_pembuatan.getText().toString().equalsIgnoreCase("")){
            tf_thn_pembuatan.setError("Format "+ tf_thn_pembuatan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_thn_pembuatan.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if(sudahUploadBpkb.equalsIgnoreCase("belum")){
            return new VerificationError("Silahkan pilih / ambil Foto Nomor BPKB");
        }
        else {
            setDataOnListerner();
            return null;
        }
    }
    public void setDataOnListerner(){
        try {
            val_HubPemilikDenganNasabah = (et_hub_pemilik_dengan_nasabah.getText().toString().trim());
            val_NoFaktur = (et_no_faktur.getText().toString().trim());
            val_NoMesin = (et_no_mesin.getText().toString().trim());
            val_BuktiGesekMesin = (et_bukti_gesek_mesin.getText().toString().trim());
            val_NoRangka = (et_no_rangka.getText().toString().trim());
            val_BuktiGesekRangka = (et_bukti_gesek_rangka.getText().toString().trim());
            val_NoPolisi = (et_no_polisi.getText().toString().trim());
            val_PlatKuning = (et_plat_kuning.getText().toString().trim());
            val_NoBpkb = (et_no_bpkb.getText().toString().trim());
            val_NoStnk = (et_no_stnk.getText().toString().trim());
            val_Warna = (et_warna.getText().toString().trim());
            val_ThnPembuatan = (et_thn_pembuatan.getText().toString().trim());

            final AgunanKendaraanPojo d = new AgunanKendaraanPojo();
            d.setHubungan(val_HubPemilikDenganNasabah);
            d.setNoFaktur(val_NoFaktur);
            d.setNoMesin(val_NoMesin);
            d.setBuktiGesekMesin(val_BuktiGesekMesin);
            d.setNoRangka(val_NoRangka);
            d.setBuktiGesekRangka(val_BuktiGesekRangka);
            d.setNoPolisi(val_NoPolisi);
            d.setJenisPlat(val_PlatKuning);
            d.setNoBKPB(val_NoBpkb);
            d.setNoSTNK(val_NoStnk);
            d.setWarna(val_Warna);
            d.setTahunPembuatan(val_ThnPembuatan);
            d.setIdKendaraan_Dok(AppUtil.parseIntWithDefault(val_ImgBpkb, 0));

            AgunanKendaraanPojo dataR = realm.where(AgunanKendaraanPojo.class).equalTo("uuid", AgunanKendaraanInputActivity.UUIDR).findFirst();

            d.setUuid(dataR.getUuid());
            d.setIdApl(dataR.getIdApl());
            d.setIdCif(dataR.getIdCif());
            d.setIdAgunan(dataR.getIdAgunan());
            d.setTglPemeriksaan(dataR.getTglPemeriksaan());
            d.setJenisKendaraan(dataR.getJenisKendaraan());
            d.setKategKendaraan(dataR.getKategKendaraan());
            d.setPenggunaanJaminan(dataR.getPenggunaanJaminan());
            d.setDaerahOperasional(dataR.getDaerahOperasional());
            d.setKondisi(dataR.getKondisi());
            d.setNamaPemilikBPKB(dataR.getNamaPemilikBPKB());
            d.setNamaPemilikSaatIni(dataR.getNamaPemilikSaatIni());
            d.setAlamatPemilik(dataR.getAlamatPemilik());

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
        BSBottomCamera.displayWithTitle(getActivity().getSupportFragmentManager(), this, "Foto Bukti Nomor BPKB");
    }
    @Override
    public void onSelectMenuCamera(String idMenu) {
        if (idGlobal.equalsIgnoreCase("bpkb")) {
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_BPKB);
                    break;
                case "Pick Photo":
                    openGalery(PICK_IMAGE_BPKB);
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
                case TAKE_PICTURE_BPKB:
                case PICK_IMAGE_BPKB:
                    setDataImage(uriPhotoBpkb, bitmapPhotoBpkb, img_bpkb, data);
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
                iv.setImageBitmap(bitmap);
                bitmapPhotoBpkb = bitmap;
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
                            if (idGlobal.equalsIgnoreCase("bpkb")) {
                                idFotoBpkb = response.body().getData().get("id").getAsInt();
                                val_ImgBpkb = String.valueOf(idFotoBpkb);
                                sudahUploadBpkb = "sudah";
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
}

