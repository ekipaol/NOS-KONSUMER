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

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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
import com.application.bris.ikurma_nos_konsumer.util.KeyValue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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

import static com.application.bris.ikurma_nos_konsumer.util.magiccrypt.lib.Crypt.BUFFER_SIZE;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunan7Lain extends Fragment implements Step, KeyValueListener, CameraListener, View.OnClickListener {

    @BindView(R.id.tf_pendapat_kondisi_jaminan)
    TextFieldBoxes tf_pendapat_kondisi_jaminan;
    @BindView(R.id.et_pendapat_kondisi_jaminan)
    EditText et_pendapat_kondisi_jaminan;

    @BindView(R.id.tf_rekomendasi_penilai)
    TextFieldBoxes tf_rekomendasi_penilai;
    @BindView(R.id.et_rekomendasi_penilai)
    EditText et_rekomendasi_penilai;

    @BindView(R.id.img_agunan)
    RoundedImageView img_agunan;
    @BindView(R.id.btn_upload_agunan)
    ImageView btn_upload_agunan;

    @BindView(R.id.img_utara)
    RoundedImageView img_utara;
    @BindView(R.id.btn_upload_utara)
    ImageView btn_upload_utara;

    @BindView(R.id.img_selatan)
    RoundedImageView img_selatan;
    @BindView(R.id.btn_upload_selatan)
    ImageView btn_upload_selatan;

    @BindView(R.id.img_barat)
    RoundedImageView img_barat;
    @BindView(R.id.btn_upload_barat)
    ImageView btn_upload_barat;

    @BindView(R.id.img_timur)
    RoundedImageView img_timur;
    @BindView(R.id.btn_upload_timur)
    ImageView btn_upload_timur;

    @BindView(R.id.cl_lampiran_dokumen_41)
    ConstraintLayout cl_lampiran_dokumen_41;

    @BindView(R.id.img_lampiran_dokumen_41)
    RoundedImageView img_lampiran_dokumen_41;
    @BindView(R.id.btn_upload_lampiran_dokumen_41)
    ImageView btn_upload_lampiran_dokumen_41;
    @BindView(R.id.tv_lampiran_dokumen41)
    TextView tv_lampiran_dokumen41;


    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    @BindView(R.id.cl_content)
    ScrollView cl_content;

    private AgunanTanahBangunan dataAgunan;
    private String idAgunan="";

    public static String val_PendapatKondisiJaminan = "";
    private int val_ImgTanah = 0;
    private int val_ImgBatasUtara = 0;
    private int val_ImgBatasSelatan = 0;
    private int val_ImgBatasBarat = 0;
    private int val_ImgBatasTimur = 0;
    private int val_Dok41=0;




    //BEGIN EMPTY STRING
    public static String val_PendapatHarga = "";
    public static String val_rekomendasi_penilai = "";
    public static String val_rekomendasi_penilai_desc = "";
    public static String val_KondisiLingkungan = "";
    //END EMPTY STRING


    private String isSelectPhoto = "";

    private final int TAKE_PICTURE_FOTOAGUNAN = 1;
    private final int TAKE_PICTURE_BATASUTARA = 2;
    private final int TAKE_PICTURE_BATASSELATAN = 3;
    private final int TAKE_PICTURE_BATASTIMUR = 4;
    private final int TAKE_PICTURE_BATASBARAT = 5;

    private final int PICK_PICTURE_FOTOAGUNAN = 11;
    private final int PICK_PICTURE_BATASUTARA = 22;
    private final int PICK_PICTURE_BATASSELATAN = 33;
    private final int PICK_PICTURE_BATASTIMUR = 44;
    private final int PICK_PICTURE_BATASBARAT = 55;

    private final int PICK_DOC_41 = 6;

    private Uri uriPhotoAgunan, uriPhotoUtara, uriPhotoSelatan, uriPhotoBarat, uriPhotoTimur;
    private Bitmap bitmapPhotoAgunan, bitmapPhotoUtara, bitmapPhotoSelatan, bitmapPhotoBarat, bitmapPhotoTimur, loadedPicture;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private Realm realm;
    private boolean enableEditText;
    private String idProgram="";

    public FragmentAgunan7Lain() {
    }

    public FragmentAgunan7Lain(String midAgunan, AgunanTanahBangunan magunanTanahBangunan) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
    }

    public FragmentAgunan7Lain(String midAgunan, AgunanTanahBangunan magunanTanahBangunan,boolean enableEditText) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
        this.enableEditText=enableEditText;
    }


    public FragmentAgunan7Lain(String midAgunan, AgunanTanahBangunan magunanTanahBangunan,boolean enableEditText,String idProgram) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
        this.idProgram=idProgram;
        this.enableEditText=enableEditText;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_7_lain, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());
//        if (!idAgunan.equalsIgnoreCase("0")) {
//            setData();
//        }
        setData();

        otherViewChanges();

        img_agunan.setOnClickListener(this);
        img_utara.setOnClickListener(this);
        img_selatan.setOnClickListener(this);
        img_timur.setOnClickListener(this);
        img_barat.setOnClickListener(this);
        img_lampiran_dokumen_41.setOnClickListener(this);
        btn_upload_agunan.setOnClickListener(this);
        btn_upload_utara.setOnClickListener(this);
        btn_upload_selatan.setOnClickListener(this);
        btn_upload_barat.setOnClickListener(this);
        btn_upload_timur.setOnClickListener(this);
        btn_upload_lampiran_dokumen_41.setOnClickListener(this);

        et_rekomendasi_penilai.setFocusable(false);
        et_rekomendasi_penilai.setInputType(InputType.TYPE_NULL);
        et_rekomendasi_penilai.setOnClickListener(this);
        tf_rekomendasi_penilai.setOnClickListener(this);
        tf_rekomendasi_penilai.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_rekomendasi_penilai.getLabelText().toString().trim());
            }
        });



        return view;
    }

    private void setData() {
        try {
            et_pendapat_kondisi_jaminan.setText(dataAgunan.getPendapatKondisiJaminan());
            et_rekomendasi_penilai.setText(dataAgunan.getDescRekomendasiPenilai());
            bitmapPhotoAgunan = setLoadImage(img_agunan, dataAgunan.getIdPhotoTBbangunan());
            bitmapPhotoUtara = setLoadImage(img_utara, dataAgunan.getIdPhotoTButara());
            bitmapPhotoSelatan = setLoadImage(img_selatan, dataAgunan.getIdPhotoTBselatan());
            bitmapPhotoBarat = setLoadImage(img_barat, dataAgunan.getIdPhotoTBbarat());
            bitmapPhotoTimur = setLoadImage(img_timur, dataAgunan.getIdPhotoTBtimur());

            val_ImgTanah = dataAgunan.getIdPhotoTBbangunan();
            val_ImgBatasUtara = dataAgunan.getIdPhotoTButara();
            val_ImgBatasSelatan = dataAgunan.getIdPhotoTBselatan();
            val_ImgBatasBarat = dataAgunan.getIdPhotoTBbarat();
            val_ImgBatasTimur = dataAgunan.getIdPhotoTBtimur();
            val_rekomendasi_penilai = dataAgunan.getIdRekomendasiPenilai();
            val_rekomendasi_penilai_desc = dataAgunan.getDescRekomendasiPenilai();

            if (dataAgunan.getIdDok41()!=null&&!dataAgunan.getIdDok41().isEmpty()&&!dataAgunan.getIdDok41().equalsIgnoreCase("0")) {
                img_lampiran_dokumen_41.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_pdf_hd));
                final int id_dok_41 = Integer.parseInt(dataAgunan.getIdDok41());
                val_Dok41 = id_dok_41;
            }

            if(enableEditText==false){
                AppUtil.disableEditTexts(cl_content);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Bitmap setLoadImage(final ImageView iv, int idFoto){
        String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + idFoto;
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

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return validateForm();
    }

    @Override
    public void onSelected() {
        if (bitmapPhotoAgunan != null){
            img_agunan.setImageBitmap(bitmapPhotoAgunan);
        }
        if (bitmapPhotoUtara != null){
            img_utara.setImageBitmap(bitmapPhotoUtara);
        }
        if (bitmapPhotoSelatan != null){
            img_selatan.setImageBitmap(bitmapPhotoSelatan);
        }
        if (bitmapPhotoBarat != null){
            img_barat.setImageBitmap(bitmapPhotoBarat);
        }
        if (bitmapPhotoTimur != null){
            img_timur.setImageBitmap(bitmapPhotoTimur);
        }
    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notifwarning(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }

    private VerificationError validateForm() {
        if (et_pendapat_kondisi_jaminan.getText().toString().isEmpty() || et_pendapat_kondisi_jaminan.getText().toString().equalsIgnoreCase("")) {
            tf_pendapat_kondisi_jaminan.setError("Format " + tf_pendapat_kondisi_jaminan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_pendapat_kondisi_jaminan.getLabelText() + " " + getString(R.string.title_validate_field));
        }
        else if(val_ImgTanah == 0){
            return new VerificationError("Silahkan pilih / ambil Foto Agunan");
        }
        else if(val_ImgBatasUtara == 0){
            return new VerificationError("Silahkan pilih / ambil Foto Batas Utara");
        }
        else if(val_ImgBatasSelatan == 0){
            return new VerificationError("Silahkan pilih / ambil Foto Batas Selatan");
        }
        else if(val_ImgBatasBarat == 0){
            return new VerificationError("Silahkan pilih / ambil Foto Batas Barat");
        }
        else if(val_ImgBatasTimur == 0){
            return new VerificationError("Silahkan pilih / ambil Foto Batas Timur");
        }
        else if(idProgram.equalsIgnoreCase("222")&&val_Dok41 == 0){
            return new VerificationError("Silahkan pilih dokumen lampiran");
        }
        else if(val_rekomendasi_penilai_desc==null||val_rekomendasi_penilai_desc.isEmpty()){
            tf_rekomendasi_penilai.setError("Format " + tf_rekomendasi_penilai.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Silahkan pilih rekomendasi penilai");
        }
        else {
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner() {
        try {
            val_PendapatKondisiJaminan = (et_pendapat_kondisi_jaminan.getText().toString().trim());
            val_rekomendasi_penilai = (KeyValue.getTypeRekomendasiPenilai(et_rekomendasi_penilai.getText().toString().trim()));
            val_rekomendasi_penilai_desc = et_rekomendasi_penilai.getText().toString().trim();

            final AgunanTanahBangunanPojo d = new AgunanTanahBangunanPojo();
            d.setPendapatKondisiJaminan(val_PendapatKondisiJaminan);
            d.setIdBangunan(val_ImgTanah);
            d.setIdBatas_Utara(val_ImgBatasUtara);
            d.setIdBatas_Selatan(val_ImgBatasSelatan);
            d.setIdBatas_Timur(val_ImgBatasTimur);
            d.setIdBatas_Barat(val_ImgBatasBarat);
            d.setPendapatHarga(val_PendapatHarga);
            d.setKondisiSekitar(val_KondisiLingkungan);
            d.setIdRekomendasiPenilai(val_rekomendasi_penilai);
            d.setDescRekomendasiPenilai(val_rekomendasi_penilai_desc);
            d.setKodeDati("");
            d.setCollID("");
            d.setRate("");
//            d.setNamaPasar("");
//            d.setNkrBrins("");
//            d.setProvinsi("");
//            d.setKota_kab("");
//            d.setNama_pasar("");
//            d.setAlamat_pasar("");
            d.setIdTanah(0);
            d.setTipeProduk(AppUtil.parseIntWithDefault(AgunanTanahBangunanInputActivity.tp_produk, 0));
            d.setIdDok41(Integer.toString(val_Dok41));


            AgunanTanahBangunanPojo dataR = realm.where(AgunanTanahBangunanPojo.class).equalTo("uuid", AgunanTanahBangunanInputActivity.uuid).findFirst();
            d.setTanggalPemeriksaan(dataR.getTanggalPemeriksaan());
            d.setDataPembanding1(dataR.getDataPembanding1());
            d.setDataPembanding2(dataR.getDataPembanding2());
            d.setLuasTanahFisik(dataR.getLuasTanahFisik());
            d.setHargaMeterTanah(dataR.getHargaMeterTanah());
            d.setLuasBangunan1(dataR.getLuasBangunan1());
            d.setLuasBangunan2(dataR.getLuasBangunan2());
            d.setHargaBangunan1(dataR.getHargaBangunan1());
            d.setHargaBangunan2(dataR.getHargaBangunan2());
            d.setNLTanahBangunan(dataR.getNLTanahBangunan());
            d.setNPWTanahBangunan(dataR.getNPWTanahBangunan());
            d.setNPWBangunan(dataR.getNPWBangunan());
            d.setNPWBangunanBrins(dataR.getNPWBangunanBrins());
            d.setDataPembanding3(dataR.getDataPembanding3());
            d.setPeruntukanLokasi(dataR.getPeruntukanLokasi());
            d.setAksesJalanObjek(dataR.getAksesJalanObjek());
            d.setFasilitasSosial(dataR.getFasilitasSosial());
            d.setLebarJalanDepan(dataR.getLebarJalanDepan());
            d.setPerkembanganLingkungan(dataR.getPerkembanganLingkungan());
            d.setKepadatan(dataR.getKepadatan());
            d.setAksesPencapaian(dataR.getAksesPencapaian());
            d.setPerawatan(dataR.getPerawatan());
            d.setJenisBangunanBRINS(dataR.getJenisBangunanBRINS());
            d.setPondasi(dataR.getPondasi());
            d.setDinding(dataR.getDinding());
            d.setAtap(dataR.getAtap());
            d.setFasilitasListrik(dataR.isFasilitasListrik());
            d.setFasilitasPAM(dataR.isFasilitasPAM());
            d.setTelepon(dataR.isTelepon());
            d.setTeganganListrik(dataR.getTeganganListrik());
            d.setJenisAir(dataR.getJenisAir());
            d.setNoTelepon(dataR.getNoTelepon());
            d.setPlafond(dataR.getPlafond());
            d.setLantai(dataR.getLantai());
            d.setTeras(dataR.getTeras());
            d.setPagar(dataR.getPagar());
            d.setGarasiCarport(dataR.getGarasiCarport());
            d.setJenisBangunan(dataR.getJenisBangunan());
            d.setLokasiBangunanBrins(dataR.getLokasiBangunanBrins());
            d.setNoIMB(dataR.getNoIMB());
            d.setJenisAgunanXBRL(dataR.getJenisAgunanXBRL());
            d.setHubPenghuniDgPemilik(dataR.getHubPenghuniDgPemilik());
            d.setTingkat(dataR.getTingkat());
            d.setAlamatIMB(dataR.getAlamatIMB());
            d.setNamaPenghuni(dataR.getNamaPenghuni());
            d.setStatusPenghuni(dataR.getStatusPenghuni());
            d.setAtasNamaIMB(dataR.getAtasNamaIMB());
            d.setTahunBangunan(dataR.getTahunBangunan());
            d.setJenisSuratTanah(dataR.getJenisSuratTanah());
            d.setNoSertifikat(dataR.getNoSertifikat());
            d.setAtasNamaSertifikat(dataR.getAtasNamaSertifikat());
            d.setTanggalSertifikat(dataR.getTanggalSertifikat());
            d.setLuasTanahSertifikat(dataR.getLuasTanahSertifikat());
            d.setIdDok_BPN(dataR.getIdDok_BPN());
            d.setNoGS(dataR.getNoGS());
            d.setHubNasabahDgnPemegangHak(dataR.getHubNasabahDgnPemegangHak());
            d.setMasaHakAtasTanah(dataR.getMasaHakAtasTanah());
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
            d.setNamaPasar(dataR.getNamaPasar());
            d.setNkrBrins(dataR.getNkrBrins());
            d.setProvinsi(dataR.getProvinsi());
            d.setKota_kab(dataR.getKota_kab());
            d.setNama_pasar(dataR.getNama_pasar());
            d.setAlamat_pasar(dataR.getAlamat_pasar());


//            Toast.makeText(getContext(), "ada pantekan ID dok imb dan id dok pbb di fragment 7", Toast.LENGTH_SHORT).show();
            d.setIdDokPBB(dataR.getIdDokPBB());
            d.setIdDokIMB(dataR.getIdDokIMB());

            //flpp
            d.setKodeWilayah(dataR.getKodeWilayah());
            d.setNoAlamat(dataR.getNoAlamat());
            d.setNoBast(dataR.getNoBast());
            d.setTglBast(dataR.getTglBast());
            d.setNoSlf(dataR.getNoSlf());
            d.setTglSlf(dataR.getTglSlf());

            realm.executeTransaction (new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(d);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        if (isSelectPhoto.equalsIgnoreCase("fotoagunan")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_FOTOAGUNAN);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_FOTOAGUNAN);
                    break;
            }
        }
        else if (isSelectPhoto.equalsIgnoreCase("batasutara")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_BATASUTARA);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_BATASUTARA);
                    break;
            }
        }
        else if (isSelectPhoto.equalsIgnoreCase("batasselatan")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_BATASSELATAN);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_BATASSELATAN);
                    break;
            }
        }
        else if (isSelectPhoto.equalsIgnoreCase("batasbarat")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_BATASBARAT);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_BATASBARAT);
                    break;
            }
        }
        else if (isSelectPhoto.equalsIgnoreCase("batastimur")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_BATASTIMUR);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_BATASTIMUR);
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

                case TAKE_PICTURE_FOTOAGUNAN:
                case PICK_PICTURE_FOTOAGUNAN:
                    setDataImage(uriPhotoAgunan, bitmapPhotoAgunan, img_agunan, data);
                    break;

                case TAKE_PICTURE_BATASUTARA:
                case PICK_PICTURE_BATASUTARA:
                    setDataImage(uriPhotoUtara, bitmapPhotoUtara, img_utara, data);
                    break;

                case TAKE_PICTURE_BATASSELATAN:
                case PICK_PICTURE_BATASSELATAN:
                    setDataImage(uriPhotoSelatan, bitmapPhotoSelatan, img_selatan, data);
                    break;

                case TAKE_PICTURE_BATASBARAT:
                case PICK_PICTURE_BATASBARAT:
                    setDataImage(uriPhotoBarat, bitmapPhotoBarat, img_barat, data);
                    break;

                case TAKE_PICTURE_BATASTIMUR:
                case PICK_PICTURE_BATASTIMUR:
                    setDataImage(uriPhotoTimur, bitmapPhotoTimur, img_timur, data);
                    break;

                case PICK_DOC_41:
                    setDataPdf(img_lampiran_dokumen_41, data);
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

                if(iv == img_agunan){
                    bitmapPhotoAgunan = bitmap;
                }
                if(iv == img_utara){
                    bitmapPhotoUtara = bitmap;
                }
                if(iv == img_selatan){
                    bitmapPhotoSelatan = bitmap;
                }
                if(iv == img_barat){
                    bitmapPhotoBarat = bitmap;
                }
                if(iv == img_timur){
                    bitmapPhotoTimur = bitmap;
                }

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
                            int idFoto = response.body().getData().get("id").getAsInt();
                            switch (isSelectPhoto){
                                case "fotoagunan":
                                    val_ImgTanah = idFoto;
                                    break;
                                case "batasutara":
                                    val_ImgBatasUtara = idFoto;
                                    break;
                                case "batasselatan":
                                    val_ImgBatasSelatan = idFoto;
                                    break;
                                case "batasbarat":
                                    val_ImgBatasBarat = idFoto;
                                    break;
                                case "batastimur":
                                    val_ImgBatasTimur = idFoto;
                                    break;
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
                Environment.getExternalStorageDirectory() + "/file_kelengkapan_dokumen");
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


                                    val_Dok41 = idPdF;


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

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            //FOTO AGUNAN
            case R.id.btn_upload_agunan:
                isSelectPhoto = "fotoagunan";
                BSBottomCamera.displayWithTitle(getFragmentManager(), this, "Foto Agunan");
                break;

            //FOTO BATAS UTARA
            case R.id.btn_upload_utara:
                isSelectPhoto = "batasutara";
                BSBottomCamera.displayWithTitle(getFragmentManager(), this, "Foto Batas Utara");
                break;

            //FOTO BATAS UTARA
            case R.id.btn_upload_selatan:
                isSelectPhoto = "batasselatan";
                BSBottomCamera.displayWithTitle(getFragmentManager(), this, "Foto Batas Selatan");
                break;

            //FOTO BATAS UTARA
            case R.id.btn_upload_barat:
                isSelectPhoto = "batasbarat";
                BSBottomCamera.displayWithTitle(getFragmentManager(), this, "Foto Batas Barat");
                break;

            //FOTO BATAS UTARA
            case R.id.btn_upload_timur:
                isSelectPhoto = "batastimur";
                BSBottomCamera.displayWithTitle(getFragmentManager(), this, "Foto Batas Timur");
                break;


                //DOKUMEN LAMPIRAN 41
            case R.id.btn_upload_lampiran_dokumen_41:

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("application/pdf");
                    startActivityForResult(intent,PICK_DOC_41);
                    break;

            case R.id.img_lampiran_dokumen_41:
                setLoadPdf(val_Dok41);
                break;
            case R.id.img_agunan:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_agunan.getDrawable()).getSourceBitmap());
                break;
            case R.id.img_utara:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_utara.getDrawable()).getSourceBitmap());
                break;
            case R.id.img_selatan:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_selatan.getDrawable()).getSourceBitmap());
                break;
            case R.id.img_timur:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_timur.getDrawable()).getSourceBitmap());
                break;
            case R.id.img_barat:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_barat.getDrawable()).getSourceBitmap());
                break;
            case R.id.tf_rekomendasi_penilai:
              openKeyValueDialog(tf_rekomendasi_penilai.getLabelText());
                break;
            case R.id.et_rekomendasi_penilai:
                openKeyValueDialog(tf_rekomendasi_penilai.getLabelText());
                break;

        }
    }

    private void otherViewChanges(){
        if(enableEditText==false){
            btn_upload_agunan.setVisibility(View.GONE);
            btn_upload_barat.setVisibility(View.GONE);
            btn_upload_timur.setVisibility(View.GONE);
            btn_upload_selatan.setVisibility(View.GONE);
            btn_upload_utara.setVisibility(View.GONE);

        }

        if(idProgram.equalsIgnoreCase("222")){
            cl_lampiran_dokumen_41.setVisibility(View.VISIBLE);
            tv_lampiran_dokumen41.setVisibility(View.VISIBLE);
        }
        else{
            cl_lampiran_dokumen_41.setVisibility(View.GONE);
            tv_lampiran_dokumen41.setVisibility(View.GONE);
        }
    }

    private void openKeyValueDialog(String title){
        DialogKeyValue.display(getFragmentManager(), title, this);
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase("rekomendasi penilai")){
            et_rekomendasi_penilai.setText(data.getName());
            val_rekomendasi_penilai = (KeyValue.getTypeRekomendasiPenilai(et_rekomendasi_penilai.getText().toString().trim()));
            val_rekomendasi_penilai_desc = et_rekomendasi_penilai.getText().toString().trim();
        }
    }
}




