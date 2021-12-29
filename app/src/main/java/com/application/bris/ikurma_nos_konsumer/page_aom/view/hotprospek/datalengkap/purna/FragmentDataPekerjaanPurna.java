package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap.purna;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
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
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.makeramen.roundedimageview.RoundedDrawable;
import com.makeramen.roundedimageview.RoundedImageView;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class FragmentDataPekerjaanPurna extends Fragment implements Step, KeyValueListener, AddressListener, View.OnClickListener, View.OnFocusChangeListener, CameraListener {

    @BindView(R.id.ll_datapekerjaan)
    LinearLayout ll_datapekerjaan;

    @BindView(R.id.tf_bidang_pekerjaan)
    TextFieldBoxes tf_bidang_pekerjaan;
    @BindView(R.id.et_bidang_pekerjaan)
    EditText et_bidang_pekerjaan;

    @BindView(R.id.tf_jenis_pekerjaan)
    TextFieldBoxes tf_jenis_pekerjaan;
    @BindView(R.id.et_jenis_pekerjaan)
    EditText et_jenis_pekerjaan;

    @BindView(R.id.tf_namaperusahaan)
    TextFieldBoxes tf_namaperusahaan;
    @BindView(R.id.et_namaperusahaan)
    EditText et_namaperusahaan;

    @BindView(R.id.tf_sisa_plafond_perusahaan)
    TextFieldBoxes tf_sisa_plafond_perusahaan;
    @BindView(R.id.et_sisa_plafond_perusahaan)
    EditText et_sisa_plafond_perusahaan;

    @BindView(R.id.tf_institusi_pembayaran_gaji_pensiun)
    TextFieldBoxes tf_institusi_pembayaran_gaji_pensiun;
    @BindView(R.id.et_institusi_pembayaran_gaji_pensiun)
    EditText et_institusi_pembayaran_gaji_pensiun;

    @BindView(R.id.tf_direct_marketing)
    TextFieldBoxes tf_direct_marketing;
    @BindView(R.id.et_direct_marketing)
    EditText et_direct_marketing;

    @BindView(R.id.tf_kategori_calon_nasabah_pensiunan)
    TextFieldBoxes tf_kategori_calon_nasabah_pensiunan;
    @BindView(R.id.et_kategori_calon_nasabah_pensiunan)
    EditText et_kategori_calon_nasabah_pensiunan;

    @BindView(R.id.tf_tanggalmulaiperusahaan)
    TextFieldBoxes tf_tanggalmulaiperusahaan;
    @BindView(R.id.et_tanggalmulaiperusahaan)
    EditText et_tanggalmulaiperusahaan;

    @BindView(R.id.tf_nomortelponperusahaan)
    TextFieldBoxes tf_nomortelponperusahaan;
    @BindView(R.id.et_nomortelponperusahaan)
    EditText et_nomortelponperusahaan;

    //USAHA
    @BindView(R.id.sw_usahaktpsama)
    Switch sw_usahaktpsama;
    @BindView(R.id.ll_alamatperusahaan)
    LinearLayout ll_alamatperusahaan;
    @BindView(R.id.tf_alamatperusahaan)
    TextFieldBoxes tf_alamatperusahaan;
    @BindView(R.id.et_alamatperusahaan)
    EditText et_alamatperusahaan;
    @BindView(R.id.tf_rtperusahaan)
    TextFieldBoxes tf_rtperusahaan;
    @BindView(R.id.et_rtperusahaan)
    EditText et_rtperusahaan;
    @BindView(R.id.tf_rwperusahaan)
    TextFieldBoxes tf_rwperusahaan;
    @BindView(R.id.et_rwperusahaan)
    EditText et_rwperusahaan;
    @BindView(R.id.tf_provinsiperusahaan)
    TextFieldBoxes tf_provinsiperusahaan;
    @BindView(R.id.et_provinsiperusahaan)
    EditText et_provinsiperusahaan;
    @BindView(R.id.tf_kotaperusahaan)
    TextFieldBoxes tf_kotaperusahaan;
    @BindView(R.id.et_kotaperusahaan)
    EditText et_kotaperusahaan;
    @BindView(R.id.tf_kecamatanperusahaan)
    TextFieldBoxes tf_kecamatanperusahaan;
    @BindView(R.id.et_kecamatanperusahaan)
    EditText et_kecamatanperusahaan;
    @BindView(R.id.tf_kelurahanperusahaan)
    TextFieldBoxes tf_kelurahanperusahaan;
    @BindView(R.id.et_kelurahanperusahaan)
    EditText et_kelurahanperusahaan;
    @BindView(R.id.tf_kodeposperusahaan)
    TextFieldBoxes tf_kodeposperusahaan;
    @BindView(R.id.et_kodeposperusahaan)
    EditText et_kodeposperusahaan;

    @BindView(R.id.tf_nomor_sk_pegawai_tetap)
    TextFieldBoxes tf_nomor_sk_pegawai_tetap;
    @BindView(R.id.et_nomor_sk_pegawai_tetap)
    EditText et_nomor_sk_pegawai_tetap;

    @BindView(R.id.tf_nomor_sk_pangkat_terakhir)
    TextFieldBoxes tf_nomor_sk_pangkat_terakhir;
    @BindView(R.id.et_nomor_sk_pangkat_terakhir)
    EditText et_nomor_sk_pangkat_terakhir;

    @BindView(R.id.tf_nomor_sk_pensiun)
    TextFieldBoxes tf_nomor_sk_pensiun;
    @BindView(R.id.et_nomor_sk_pensiun)
    EditText et_nomor_sk_pensiun;

    @BindView(R.id.tf_nomor_taspen)
    TextFieldBoxes tf_nomor_taspen;
    @BindView(R.id.et_nomor_taspen)
    EditText et_nomor_taspen;

    @BindView(R.id.tf_nomor_induk_pegawai)
    TextFieldBoxes tf_nomor_induk_pegawai;
    @BindView(R.id.et_nomor_induk_pegawai)
    EditText et_nomor_induk_pegawai;

    @BindView(R.id.tf_status_kepegawaian)
    TextFieldBoxes tf_status_kepegawaian;
    @BindView(R.id.et_status_kepegawaian)
    EditText et_status_kepegawaian;

    @BindView(R.id.tf_deskripsi_pekerjaan)
    TextFieldBoxes tf_deskripsi_pekerjaan;
    @BindView(R.id.et_deskripsi_pekerjaan)
    EditText et_deskripsi_pekerjaan;

    @BindView(R.id.tf_usia_mpp)
    TextFieldBoxes tf_usia_mpp;
    @BindView(R.id.et_usia_mpp)
    EditText et_usia_mpp;

    @BindView(R.id.tf_posisi_jabatan)
    TextFieldBoxes tf_posisi_jabatan;
    @BindView(R.id.et_posisi_jabatan)
    EditText et_posisi_jabatan;

    @BindView(R.id.tf_pembayaran_gaji_melalui)
    TextFieldBoxes tf_pembayaran_gaji_melalui;
    @BindView(R.id.et_pembayaran_gaji_melalui)
    EditText et_pembayaran_gaji_melalui;

    @BindView(R.id.tf_tanggal_verifikasi_pejabat)
    TextFieldBoxes tf_tanggal_verifikasi_pejabat;
    @BindView(R.id.et_tanggal_verifikasi_pejabat_berwenang)
    EditText et_tanggal_verifikasi_pejabat_berwenang;

    @BindView(R.id.tf_nama_pejabat_berwenang)
    TextFieldBoxes tf_nama_pejabat_berwenang;
    @BindView(R.id.et_nama_pejabat_berwenang)
    EditText et_nama_pejabat_berwenang;

    @BindView(R.id.tf_no_surat_rekomendasi)
    TextFieldBoxes tf_no_surat_rekomendasi;
    @BindView(R.id.et_no_surat_rekomendasi)
    EditText et_no_surat_rekomendasi;

    @BindView(R.id.tf_tanggalmulaipensiun)
    TextFieldBoxes tf_tanggalmulaipensiun;
    @BindView(R.id.et_tanggalmulaipensiun)
    EditText et_tanggalmulaipensiun;

    @BindView(R.id.btn_perusahaan)
    Button btn_perusahaan;

    @BindView(R.id.rl_fotokantor1)
    RelativeLayout rl_fotokantor1;
    @BindView(R.id.rl_fotokantor2)
    RelativeLayout rl_fotokantor2;



    @BindView(R.id.img_fotokantor1)
    RoundedImageView img_fotokantor1;
    @BindView(R.id.btn_upload_fotokantor1)
    ImageView btn_upload_fotokantor1;
    @BindView(R.id.img_fotokantor2)
    RoundedImageView img_fotokantor2;
    @BindView(R.id.btn_upload_fotokantor2)
    ImageView btn_upload_fotokantor2;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.progress_kantor1)
    ProgressBar progress_kantor1;
    @BindView(R.id.progress_kantor2)
    ProgressBar progress_kantor2;

    private Realm realm;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private DataLengkap dataLengkap;
    private Calendar calTanggalMulaiUsaha;
    private Calendar calTanggalverifikasi;
    private DatePickerDialog dpTanggalMulaiUsaha;
    private DatePickerDialog dpTanggalVerifikasi;
    private String approved, gimmick;

    private String val_BidangUsaha ="";
    private String val_NamaUsaha ="";
    private String val_TglMulaiUsaha ="";
    private String val_TglPensiunan ="";
    private String val_NoTelpUsaha ="";
    private String val_AlamatUsaha ="";
    private String val_RtUsaha ="";
    private String val_RwUsaha ="";
    private String val_ProvUsaha ="";
    private String val_KotaUsaha ="";
    private String val_KecUsaha ="";
    private String val_KelUsaha ="";
    private String val_KodePosUsaha ="";
    private String val_status_kepegawaian ="";
    private String val_bidang_pekerjaan ="";
    private String val_sisa_plafond ="";
    private String val_no_sk_pegawai_tetap ="";
    private String val_no_sk_pangkat_terakhir ="";
    private String val_no_induk_pegawai ="";
    private String val_usia_mpp ="";
    private String val_posisi_jabatan ="";
    private String val_pembayaran_gaji_melalui ="";
    private String val_tanggal_verifikasi ="";
    private String val_nama_pejabat_berwenang ="";
    private String val_no_surat_rekomendasi ="";
    private String val_kategori_nasabah ="";
    private String val_id_instansi ="";
    private String val_nomor_sk_pensiun ="";
    private String val_nomor_taspen ="";
    private String val_nama_pegawai ="";
    private String val_nama_kantor ="";
    private int val_ImgKantor1 = 0;
    private int val_ImgKantor2 = 0;
    private String isSelectPhoto = "";

    private final int PICK_PICTURE_KANTOR1 = 1;
    private final int PICK_PICTURE_KANTOR2 = 2;
    private final int TAKE_PICTURE_KANTOR1 = 11;
    private final int TAKE_PICTURE_KANTOR2 = 22;

    private Uri uriPhotoKantor1, uriPhotoKantor2;
    private Bitmap bitmapPhotoKantor1, bitmapPhotoKantor2, loadedPicture;

//    private String sudahUploadKantor1 = "belum";
//    private String sudahUploadKantor2 = "belum";
//
//    private int idFotoKantor1 = 0;
//    private int idFotoKantor2 = 0;

    private int val_usahaAsId = 0;

    @SuppressLint("ValidFragment")
//    public FragmentDataPekerjaanPurna(DataLengkap mdataLengkap) {
//        dataLengkap = mdataLengkap;
//    }

    public FragmentDataPekerjaanPurna(DataLengkap mdataLengkap, String maprroved, String mgimmick) {
        dataLengkap = mdataLengkap;
        approved = maprroved;
        gimmick = mgimmick;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_data_perusahaan_purna, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());
        sw_usahaktpsama.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ll_alamatperusahaan.setVisibility(View.GONE);
                    val_usahaAsId = 1;
                }
                else {
                    ll_alamatperusahaan.setVisibility(View.VISIBLE);
                    val_usahaAsId = 0;
                }
            }
        });
        Log.wtf("masuk ke pekerjaan purna","woyyy pekerjaan ourna");

        //PENGHILANGAN FIELD TERGANTUNG GIMMICK

        //kmg embp dan pra purna embp

        if (gimmick.equalsIgnoreCase("1")||gimmick.equalsIgnoreCase("26")||gimmick.equalsIgnoreCase("27")||gimmick.equalsIgnoreCase("28")||gimmick.equalsIgnoreCase("29")||gimmick.equalsIgnoreCase("30")||gimmick.equalsIgnoreCase("31")) {
            tf_institusi_pembayaran_gaji_pensiun.setVisibility(View.GONE);
            tf_direct_marketing.setVisibility(View.GONE);
            tf_kategori_calon_nasabah_pensiunan.setVisibility(View.GONE);

        }

        else if(gimmick.equalsIgnoreCase("14")||gimmick.equalsIgnoreCase("15")||gimmick.equalsIgnoreCase("16")){
            ubahFieldKhususPurna();

        }

        //pra purna non embp
        else{
            tf_bidang_pekerjaan.setVisibility(View.GONE);
            tf_namaperusahaan.setVisibility(View.GONE);
            tf_jenis_pekerjaan.setVisibility(View.GONE);
            tf_deskripsi_pekerjaan.setVisibility(View.GONE);
            tf_sisa_plafond_perusahaan.setVisibility(View.GONE);
            tf_no_surat_rekomendasi.setVisibility(View.GONE);
            et_no_surat_rekomendasi.setVisibility(View.GONE);
        }

        //REALM LISTENERS
        saveToRealm();


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
        btn_perusahaan.setOnClickListener(this);
        img_fotokantor1.setOnClickListener(this);
        btn_upload_fotokantor1.setOnClickListener(this);
        img_fotokantor2.setOnClickListener(this);
        btn_upload_fotokantor2.setOnClickListener(this);
    }

    private void setData() {
        DataLengkapPojo existingRealm = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", DataLengkapActivityPurna.idAplikasi).findFirst();
        if(dataLengkap.getNIP()==null){
            bitmapPhotoKantor1 = setLoadImage(img_fotokantor1, existingRealm.getFID_PHOTO_KANTOR1());
            bitmapPhotoKantor2 = setLoadImage(img_fotokantor2, existingRealm.getFID_PHOTO_KANTOR2());
            val_ImgKantor1 = existingRealm.getFID_PHOTO_KANTOR1();
            val_ImgKantor2 = existingRealm.getFID_PHOTO_KANTOR2();

        }

        et_bidang_pekerjaan.setText(KeyValue.getKeyUsahaorJob(dataLengkap.getBidangPekerjaan()));
        et_namaperusahaan.setText(dataLengkap.getNamaPerusahaan());
        et_sisa_plafond_perusahaan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_sisa_plafond_perusahaan));
        et_sisa_plafond_perusahaan.setText(dataLengkap.getSisaPlafondPerusahaan());
        et_institusi_pembayaran_gaji_pensiun.setText(dataLengkap.getNamaInstitusi());
        et_direct_marketing.setText(dataLengkap.getRekDm());
        et_kategori_calon_nasabah_pensiunan.setText(dataLengkap.getJenisKMG());
//        et_tanggalmulaiperusahaan.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglMulaiBekerja(), "ddMMyyyy", "dd-MM-yyyy"));
        if(dataLengkap.getTglPensiunan()!=null&&!dataLengkap.getTglPensiunan().equalsIgnoreCase("")){
            et_tanggalmulaipensiun.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglPensiunan(), "ddMMyyyy", "dd-MM-yyyy"));
        }

        et_nomortelponperusahaan.setText(dataLengkap.getNoTelpPerusahaan());
        et_alamatperusahaan.setText(dataLengkap.getAlamatPerusahaan());
//        et_rtperusahaan.setText(dataLengkap.getRtUsaha());
//        et_rwperusahaan.setText(dataLengkap.getRwUsaha());
        et_provinsiperusahaan.setText(dataLengkap.getProvPerusahaan());
        et_kotaperusahaan.setText(dataLengkap.getKotaPerusahaan());
        et_kecamatanperusahaan.setText(dataLengkap.getKecPerusahaan());
        et_kelurahanperusahaan.setText(dataLengkap.getKelPerusahaan());
        et_kodeposperusahaan.setText(dataLengkap.getKodePosPerusahaan());
        et_nomor_sk_pegawai_tetap.setText(dataLengkap.getNoSKPertama());
        et_nomor_sk_pangkat_terakhir.setText(dataLengkap.getNoSKterakhir());

        //trim the SK, karena dari service dapet spasi banyak banget
        et_nomor_sk_pegawai_tetap.setText(et_nomor_sk_pegawai_tetap.getText().toString().trim());
        et_nomor_sk_pangkat_terakhir.setText(et_nomor_sk_pangkat_terakhir.getText().toString().trim());


        et_nomor_induk_pegawai.setText(dataLengkap.getNIP());
        et_deskripsi_pekerjaan.setText(dataLengkap.getDeskripsiPekerjaan());
        et_status_kepegawaian.setText(KeyValue.getKeyStatusKepegawaian(dataLengkap.getStatusKepegawaian()));
        et_jenis_pekerjaan.setText(dataLengkap.getJenisPekerjaan());
        et_usia_mpp.setText(dataLengkap.getUsiaMpp());
        et_posisi_jabatan.setText(KeyValue.getKeyPosisiJabatan(dataLengkap.getJabatan()));
        et_pembayaran_gaji_melalui.setText(KeyValue.getKeyPembayaranGaji(dataLengkap.getPembayaranGaji()));
        if(dataLengkap.getTglVerifikasi()!=null&&!dataLengkap.getTglVerifikasi().equalsIgnoreCase("")){
            et_tanggal_verifikasi_pejabat_berwenang.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglVerifikasi(), "ddMMyyyy", "dd-MM-yyyy"));
        }
        et_nama_pejabat_berwenang.setText(dataLengkap.getNamaPejabat());
        et_no_surat_rekomendasi.setText(dataLengkap.getNoRekomendasi());
        et_nomor_taspen.setText(dataLengkap.getNomorTaspen());
        et_nomor_sk_pensiun.setText(dataLengkap.getSkPensiun());

        bitmapPhotoKantor1 = setLoadImage(img_fotokantor1, dataLengkap.getFID_PHOTO_KANTOR1());
        bitmapPhotoKantor2 = setLoadImage(img_fotokantor2, dataLengkap.getFID_PHOTO_KANTOR2());
        val_ImgKantor1 = dataLengkap.getFID_PHOTO_KANTOR1();
        val_ImgKantor2 = dataLengkap.getFID_PHOTO_KANTOR2();

        if (approved.equalsIgnoreCase("yes")){
            AppUtil.disableEditTexts(ll_datapekerjaan);
            btn_perusahaan.setVisibility(View.GONE);
            btn_upload_fotokantor1.setVisibility(View.GONE);
            btn_upload_fotokantor2.setVisibility(View.GONE);
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

    private void onSelectDialog() {
        //BIDANG USAHA
        et_bidang_pekerjaan.setFocusable(false);
        et_bidang_pekerjaan.setInputType(InputType.TYPE_NULL);
//        et_bidang_pekerjaan.setOnClickListener(this);
//        et_bidang_pekerjaan.setOnFocusChangeListener(this);
//        tf_bidang_pekerjaan.setOnClickListener(this);
//        tf_bidang_pekerjaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openKeyValueDialog(tf_bidang_pekerjaan.getLabelText().toString().trim());
//            }
//        });

        et_institusi_pembayaran_gaji_pensiun.setInputType(InputType.TYPE_NULL);
        et_institusi_pembayaran_gaji_pensiun.setFocusable(false);

        et_direct_marketing.setInputType(InputType.TYPE_NULL);
        et_direct_marketing.setFocusable(false);

        et_direct_marketing.setInputType(InputType.TYPE_NULL);
        et_direct_marketing.setFocusable(false);

        et_kategori_calon_nasabah_pensiunan.setInputType(InputType.TYPE_NULL);
        et_kategori_calon_nasabah_pensiunan.setFocusable(false);

        et_sisa_plafond_perusahaan.setInputType(InputType.TYPE_NULL);
        et_sisa_plafond_perusahaan.setFocusable(false);

        et_namaperusahaan.setInputType(InputType.TYPE_NULL);
        et_namaperusahaan.setFocusable(false);

        et_sisa_plafond_perusahaan.setInputType(InputType.TYPE_NULL);
        et_sisa_plafond_perusahaan.setFocusable(false);

        et_tanggalmulaiperusahaan.setFocusable(false);
        et_tanggalmulaiperusahaan.setInputType(InputType.TYPE_NULL);
        et_tanggalmulaiperusahaan.setOnFocusChangeListener(this);

        et_tanggalmulaipensiun.setFocusable(false);
        et_tanggalmulaipensiun.setInputType(InputType.TYPE_NULL);
        et_tanggalmulaipensiun.setOnFocusChangeListener(this);

        et_status_kepegawaian.setFocusable(false);
        et_status_kepegawaian.setInputType(InputType.TYPE_NULL);
        et_status_kepegawaian.setOnFocusChangeListener(this);

        et_jenis_pekerjaan.setFocusable(false);
        et_jenis_pekerjaan.setInputType(InputType.TYPE_NULL);

        et_deskripsi_pekerjaan.setInputType(InputType.TYPE_NULL);
        et_deskripsi_pekerjaan.setFocusable(false);

        et_posisi_jabatan.setFocusable(false);
        et_posisi_jabatan.setInputType(InputType.TYPE_NULL);
        et_posisi_jabatan.setOnFocusChangeListener(this);

        et_pembayaran_gaji_melalui.setFocusable(false);
        et_pembayaran_gaji_melalui.setInputType(InputType.TYPE_NULL);
        et_pembayaran_gaji_melalui.setOnFocusChangeListener(this);

        et_tanggal_verifikasi_pejabat_berwenang.setFocusable(false);
        et_tanggal_verifikasi_pejabat_berwenang.setInputType(InputType.TYPE_NULL);
        et_tanggal_verifikasi_pejabat_berwenang.setOnFocusChangeListener(this);

        et_provinsiperusahaan.setInputType(InputType.TYPE_NULL);
        et_provinsiperusahaan.setFocusable(false);

        et_kotaperusahaan.setInputType(InputType.TYPE_NULL);
        et_kotaperusahaan.setFocusable(false);

        et_kecamatanperusahaan.setInputType(InputType.TYPE_NULL);
        et_kecamatanperusahaan.setFocusable(false);

        et_kelurahanperusahaan.setInputType(InputType.TYPE_NULL);
        et_kelurahanperusahaan.setFocusable(false);

        et_kodeposperusahaan.setInputType(InputType.TYPE_NULL);
        et_kodeposperusahaan.setFocusable(false);

        if (approved.equalsIgnoreCase("no")){
            et_tanggalmulaipensiun.setOnClickListener(this);
            tf_tanggalmulaipensiun.setOnClickListener(this);
            tf_tanggalmulaipensiun.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dpTanggalMulaiUsaha();
                }
            });

            et_status_kepegawaian.setOnClickListener(this);
            tf_status_kepegawaian.setOnClickListener(this);
            tf_status_kepegawaian.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openKeyValueDialog(tf_status_kepegawaian.getLabelText());
                }
            });

            et_posisi_jabatan.setOnClickListener(this);
            tf_posisi_jabatan.setOnClickListener(this);
            tf_posisi_jabatan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openKeyValueDialog(tf_posisi_jabatan.getLabelText());
                }
            });

            et_pembayaran_gaji_melalui.setOnClickListener(this);
            tf_pembayaran_gaji_melalui.setOnClickListener(this);
            tf_pembayaran_gaji_melalui.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openKeyValueDialog(tf_pembayaran_gaji_melalui.getLabelText());
                }
            });

            et_tanggal_verifikasi_pejabat_berwenang.setOnClickListener(this);
            tf_tanggal_verifikasi_pejabat.setOnClickListener(this);
            tf_tanggal_verifikasi_pejabat.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dpTanggalVerifikasiPejabat();
                }
            });
        }
    }

    private void openKeyValueDialog(String title){
        DialogKeyValue.display(getFragmentManager(), title, this);
    }

    private void openAddressDialog(){
        DialogAddress.display(getFragmentManager(), this);
    }

    private void dpTanggalMulaiUsaha(){
        calTanggalMulaiUsaha = Calendar.getInstance();
        Calendar calMin = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ls_tanggalLahir = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calTanggalMulaiUsaha.set(Calendar.YEAR, year);
                calTanggalMulaiUsaha.set(Calendar.MONTH, month);
                calTanggalMulaiUsaha.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calMulaiUsahaString = FragmentDataPribadiPurna.dateClient.format(calTanggalMulaiUsaha.getTime());
                et_tanggalmulaipensiun.setText(calMulaiUsahaString);
                AppUtil.dynamicIconLogoChangeDropdown(getContext(),tf_tanggalmulaipensiun,et_tanggalmulaipensiun);

                //add to realm when got the date
                DataLengkapPojo existingRealm = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", DataLengkapActivityPurna.idAplikasi).findFirst();
                if(!realm.isInTransaction()){
                    realm.beginTransaction();
                }
                existingRealm.setTglMulaiBekerja(AppUtil.parseTanggalGeneral(et_tanggalmulaiperusahaan.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
                realm.commitTransaction(); //realm.close();
            }
        };

        dpTanggalMulaiUsaha = new DatePickerDialog(getContext(), R.style.AppTheme_TimePickerTheme, ls_tanggalLahir, calTanggalMulaiUsaha.get(Calendar.YEAR),
                calTanggalMulaiUsaha.get(Calendar.MONTH), calTanggalMulaiUsaha.get(Calendar.DAY_OF_MONTH));
//        dpTanggalMulaiUsaha.getDatePicker().setMaxDate(calMin.getTimeInMillis());
        dpTanggalMulaiUsaha.show();
    }


    private void dpTanggalVerifikasiPejabat(){
        calTanggalverifikasi = Calendar.getInstance();
        Calendar calMin = Calendar.getInstance();
//        calMin.add(Calendar.MONTH, -1);
        DatePickerDialog.OnDateSetListener ls_tanggalLahir = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calTanggalverifikasi.set(Calendar.YEAR, year);
                calTanggalverifikasi.set(Calendar.MONTH, month);
                calTanggalverifikasi.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calMulaiVerifikasi = FragmentDataPribadiPurna.dateClient.format(calTanggalverifikasi.getTime());
                et_tanggal_verifikasi_pejabat_berwenang.setText(calMulaiVerifikasi);
                AppUtil.dynamicIconLogoChangeDropdown(getContext(),tf_tanggal_verifikasi_pejabat,et_tanggal_verifikasi_pejabat_berwenang);

                //add to realm when got the date
                DataLengkapPojo existingRealm = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", DataLengkapActivityPurna.idAplikasi).findFirst();
                if(!realm.isInTransaction()){
                    realm.beginTransaction();
                }
                existingRealm.setTglVerifikasi(AppUtil.parseTanggalGeneral(et_tanggal_verifikasi_pejabat_berwenang.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
                realm.commitTransaction(); //realm.close();
            }
        };

        dpTanggalVerifikasi = new DatePickerDialog(getContext(), R.style.AppTheme_TimePickerTheme, ls_tanggalLahir, calTanggalverifikasi.get(Calendar.YEAR),
                calTanggalverifikasi.get(Calendar.MONTH), calTanggalverifikasi.get(Calendar.DAY_OF_MONTH));
        dpTanggalVerifikasi.getDatePicker().setMaxDate(calMin.getTimeInMillis());
        dpTanggalVerifikasi.show();
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {

        if(gimmick.equalsIgnoreCase("14")||gimmick.equalsIgnoreCase("15")||gimmick.equalsIgnoreCase("16")){
            return validateFormPurna();

        }
        else{
            return validateForm();
        }



    }

    @Override
    public void onSelected() {
        if (bitmapPhotoKantor1 != null){
            img_fotokantor1.setImageBitmap(bitmapPhotoKantor1);
        }
        if (bitmapPhotoKantor2 != null){
            img_fotokantor2.setImageBitmap(bitmapPhotoKantor2);
        }
    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_tanggalmulaiperusahaan:
            case R.id.tf_tanggalmulaiperusahaan:
                dpTanggalMulaiUsaha();
                break;

            case R.id.et_tanggalmulaipensiun:
            case R.id.tf_tanggalmulaipensiun:
                dpTanggalMulaiUsaha();
                break;

            case R.id.et_bidangusaha:
            case R.id.tf_bidangusaha:
                openKeyValueDialog(tf_bidang_pekerjaan.getLabelText().toString().trim());
                break;

            case R.id.btn_perusahaan:
                openAddressDialog();
                break;

            case R.id.et_status_kepegawaian:
            case R.id.tf_status_kepegawaian:
                openKeyValueDialog(tf_status_kepegawaian.getLabelText().toString().trim());
                break;

            case R.id.et_posisi_jabatan:
            case R.id.tf_posisi_jabatan:
                openKeyValueDialog(tf_posisi_jabatan.getLabelText().toString().trim());
                break;

            case R.id.et_pembayaran_gaji_melalui:
            case R.id.tf_pembayaran_gaji_melalui:
                openKeyValueDialog(tf_pembayaran_gaji_melalui.getLabelText().toString().trim());
                break;

            case R.id.et_tanggal_verifikasi_pejabat_berwenang:
            case R.id.tf_tanggal_verifikasi_pejabat:
                dpTanggalVerifikasiPejabat();
                break;

            case R.id.btn_upload_fotokantor1:
                isSelectPhoto = "fotokantor1";
                BSBottomCamera.displayWithTitle(getActivity().getSupportFragmentManager(), this, "Foto Kantor 1");
                break;

            case R.id.btn_upload_fotokantor2:
                isSelectPhoto = "fotokantor2";
                BSBottomCamera.displayWithTitle(getActivity().getSupportFragmentManager(), this, "Foto Kantor 2");
                break;

            case R.id.img_fotokantor1:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_fotokantor1.getDrawable()).getSourceBitmap());
                break;

            case R.id.img_fotokantor2:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_fotokantor2.getDrawable()).getSourceBitmap());
                break;
        }
    }

    private VerificationError validateForm(){

        if ((et_bidang_pekerjaan.getText().toString().isEmpty() || et_bidang_pekerjaan.getText().toString().equalsIgnoreCase(""))&&tf_bidang_pekerjaan.getVisibility()==View.VISIBLE){
            tf_bidang_pekerjaan.setError("Format "+ tf_bidang_pekerjaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_bidang_pekerjaan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if ((et_namaperusahaan.getText().toString().isEmpty() || et_namaperusahaan.getText().toString().equalsIgnoreCase(""))&&tf_namaperusahaan.getVisibility()==View.VISIBLE){
            tf_namaperusahaan.setError("Format "+ tf_namaperusahaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_namaperusahaan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_tanggalmulaipensiun.getText().toString().isEmpty() || et_tanggalmulaipensiun.getText().toString().equalsIgnoreCase("")){
            tf_tanggalmulaipensiun.setError("Format "+ tf_tanggalmulaipensiun.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_tanggalmulaipensiun.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_nomortelponperusahaan.getText().toString().isEmpty() || et_nomortelponperusahaan.getText().toString().equalsIgnoreCase("")){
            tf_nomortelponperusahaan.setError("Format "+ tf_nomortelponperusahaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nomortelponperusahaan.getLabelText()+" "+getString(R.string.title_validate_field));
        }


        //usaha
        else if (et_alamatperusahaan.getText().toString().isEmpty() || et_alamatperusahaan.getText().toString().equalsIgnoreCase("")){
            tf_alamatperusahaan.setError("Format "+ tf_alamatperusahaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_alamatperusahaan.getLabelText()+" "+getString(R.string.title_validate_field));
        }
//        else if (et_rtperusahaan.getText().toString().isEmpty() || et_rtperusahaan.getText().toString().equalsIgnoreCase("")){
//            tf_rtperusahaan.setError("Format "+ tf_rtperusahaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
//            return new VerificationError("Format "+ tf_rtperusahaan.getLabelText()+" "+getString(R.string.title_validate_field));
//        }
//
//        else if (et_rwperusahaan.getText().toString().isEmpty() || et_rwperusahaan.getText().toString().equalsIgnoreCase("")){
//            tf_rwperusahaan.setError("Format "+ tf_rwperusahaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
//            return new VerificationError("Format "+ tf_rwperusahaan.getLabelText()+" "+getString(R.string.title_validate_field));
//        }

        else if (et_provinsiperusahaan.getText().toString().isEmpty() || et_provinsiperusahaan.getText().toString().equalsIgnoreCase("")){
            tf_provinsiperusahaan.setError("Format "+ tf_provinsiperusahaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_provinsiperusahaan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kotaperusahaan.getText().toString().isEmpty() || et_kotaperusahaan.getText().toString().equalsIgnoreCase("")){
            tf_kotaperusahaan.setError("Format "+ tf_kotaperusahaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kotaperusahaan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kecamatanperusahaan.getText().toString().isEmpty() || et_kecamatanperusahaan.getText().toString().equalsIgnoreCase("")){
            tf_kecamatanperusahaan.setError("Format "+ tf_kecamatanperusahaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kecamatanperusahaan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kelurahanperusahaan.getText().toString().isEmpty() || et_kelurahanperusahaan.getText().toString().equalsIgnoreCase("")){
            tf_kelurahanperusahaan.setError("Format "+ tf_kelurahanperusahaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kelurahanperusahaan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kodeposperusahaan.getText().toString().isEmpty() || et_kodeposperusahaan.getText().toString().equalsIgnoreCase("")){
            tf_kodeposperusahaan.setError("Format "+ tf_kodeposperusahaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kodeposperusahaan.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_sisa_plafond_perusahaan.getText().toString().isEmpty() || et_sisa_plafond_perusahaan.getText().toString().equalsIgnoreCase("")){
            tf_sisa_plafond_perusahaan.setError("Format "+ tf_sisa_plafond_perusahaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_sisa_plafond_perusahaan.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_nomor_sk_pegawai_tetap.getText().toString().trim().isEmpty() || et_nomor_sk_pegawai_tetap.getText().toString().equalsIgnoreCase("")){
            tf_nomor_sk_pegawai_tetap.setError("Format "+ tf_nomor_sk_pegawai_tetap.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nomor_sk_pegawai_tetap.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_nomor_sk_pangkat_terakhir.getText().toString().trim().isEmpty() || et_nomor_sk_pangkat_terakhir.getText().toString().equalsIgnoreCase("")){
            tf_nomor_sk_pangkat_terakhir.setError("Format "+ tf_nomor_sk_pangkat_terakhir.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nomor_sk_pangkat_terakhir.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_nomor_sk_pangkat_terakhir.getText().toString().contains("@")){
            tf_nomor_sk_pangkat_terakhir.setError("Tidak boleh ada karakter @ dalan nomor SK", true);
            return new VerificationError("Format "+ tf_nomor_sk_pangkat_terakhir.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_nomor_sk_pegawai_tetap.getText().toString().contains("@")){
            tf_nomor_sk_pegawai_tetap.setError("Tidak boleh ada karakter @ dalan nomor SK", true);
            return new VerificationError("Format "+ tf_nomor_sk_pegawai_tetap.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_nomor_induk_pegawai.getText().toString().isEmpty() || et_nomor_induk_pegawai.getText().toString().equalsIgnoreCase("")){
            tf_nomor_induk_pegawai.setError("Format "+ tf_nomor_induk_pegawai.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nomor_induk_pegawai.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_nomor_induk_pegawai.getText().toString().isEmpty() || et_nomor_induk_pegawai.getText().toString().equalsIgnoreCase("")){
            tf_nomor_induk_pegawai.setError("Format "+ tf_nomor_induk_pegawai.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nomor_induk_pegawai.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_status_kepegawaian.getText().toString().isEmpty() || et_status_kepegawaian.getText().toString().equalsIgnoreCase("pilih")){
            tf_status_kepegawaian.setError("Format "+ tf_status_kepegawaian.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_status_kepegawaian.getLabelText()+" "+getString(R.string.title_validate_field));
        }
//        else if (et_usia_mpp.getText().toString().isEmpty() || et_usia_mpp.getText().toString().equalsIgnoreCase("")){
//            tf_usia_mpp.setError("Format "+ tf_usia_mpp.getLabelText()+" "+getString(R.string.title_validate_field), true);
//            return new VerificationError("Format "+ tf_usia_mpp.getLabelText()+" "+getString(R.string.title_validate_field));
//        }
        else if (et_posisi_jabatan.getText().toString().isEmpty() || et_posisi_jabatan.getText().toString().equalsIgnoreCase("pilih")){
            tf_posisi_jabatan.setError("Format "+ tf_posisi_jabatan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_posisi_jabatan.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_pembayaran_gaji_melalui.getText().toString().isEmpty() || et_pembayaran_gaji_melalui.getText().toString().equalsIgnoreCase("pilih")){
            tf_pembayaran_gaji_melalui.setError("Format "+ tf_pembayaran_gaji_melalui.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_pembayaran_gaji_melalui.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_tanggal_verifikasi_pejabat_berwenang.getText().toString().isEmpty() || et_tanggal_verifikasi_pejabat_berwenang.getText().toString().equalsIgnoreCase("pilih")){
            tf_tanggal_verifikasi_pejabat.setError("Format "+ tf_tanggal_verifikasi_pejabat.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_tanggal_verifikasi_pejabat.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_nama_pejabat_berwenang.getText().toString().isEmpty() || et_nama_pejabat_berwenang.getText().toString().equalsIgnoreCase("pilih")){
            tf_nama_pejabat_berwenang.setError("Format "+ tf_nama_pejabat_berwenang.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nama_pejabat_berwenang.getLabelText()+" "+getString(R.string.title_validate_field));
        }


        else if(val_ImgKantor1 == 0){
            return new VerificationError("Silahkan pilih / ambil Foto Kantor");
        }

        else if(val_ImgKantor2 == 0){
            return new VerificationError("Silahkan pilih / ambil Foto Kantor");
        }


        else if (et_no_surat_rekomendasi.getText().toString().isEmpty() || et_no_surat_rekomendasi.getText().toString().equalsIgnoreCase("pilih")){

            //hanya validasi jika embp saja
            if(gimmick.equalsIgnoreCase("1")||gimmick.equalsIgnoreCase("26")||gimmick.equalsIgnoreCase("27")||gimmick.equalsIgnoreCase("28")){
                tf_no_surat_rekomendasi.setError("Format "+ tf_no_surat_rekomendasi.getLabelText()+" "+getString(R.string.title_validate_field), true);
                return new VerificationError("Format "+ tf_no_surat_rekomendasi.getLabelText()+" "+getString(R.string.title_validate_field));
            }
            else{
                Log.wtf("contoh data perusahaan 902","902");
                setDataOnListerner();
                return null;
            }


        }

        //ini berdasarkan catatan mbak nutri, dia tidak mandatory
//        else if (et_tanggal_verifikasi_pejabat_berwenang.getText().toString().isEmpty() || et_tanggal_verifikasi_pejabat_berwenang.getText().toString().equalsIgnoreCase("pilih")){
//            tf_tanggal_verifikasi_pejabat.setError("Format "+ tf_tanggal_verifikasi_pejabat.getLabelText()+" "+getString(R.string.title_validate_field), true);
//            return new VerificationError("Format "+ tf_tanggal_verifikasi_pejabat.getLabelText()+" "+getString(R.string.title_validate_field));
//        }

        else{
            Log.wtf("contoh data perusahaan 915","915");
            setDataOnListerner();
            return null;
        }
    }


    private VerificationError validateFormPurna(){

        if (et_nomor_sk_pensiun.getText().toString().trim().isEmpty() || et_nomor_sk_pensiun.getText().toString().trim().equalsIgnoreCase("")){
            tf_nomor_sk_pensiun.setError("Format "+ tf_nomor_sk_pensiun.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nomor_sk_pensiun.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_nomor_taspen.getText().toString().isEmpty() || et_nomor_taspen.getText().toString().equalsIgnoreCase("")){
            tf_nomor_taspen.setError("Format "+ tf_nomor_taspen.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nomor_taspen.getLabelText()+" "+getString(R.string.title_validate_field));
        }


        else if (et_nomor_induk_pegawai.getText().toString().isEmpty() || et_nomor_induk_pegawai.getText().toString().equalsIgnoreCase("")){
            tf_nomor_induk_pegawai.setError("Format "+ tf_nomor_induk_pegawai.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nomor_induk_pegawai.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_nomor_induk_pegawai.getText().toString().isEmpty() || et_nomor_induk_pegawai.getText().toString().equalsIgnoreCase("")){
            tf_nomor_induk_pegawai.setError("Format "+ tf_nomor_induk_pegawai.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nomor_induk_pegawai.getLabelText()+" "+getString(R.string.title_validate_field));
        }
//        else if (et_usia_mpp.getText().toString().isEmpty() || et_usia_mpp.getText().toString().equalsIgnoreCase("")){
//            tf_usia_mpp.setError("Format "+ tf_usia_mpp.getLabelText()+" "+getString(R.string.title_validate_field), true);
//            return new VerificationError("Format "+ tf_usia_mpp.getLabelText()+" "+getString(R.string.title_validate_field));
//        }
        else if (et_pembayaran_gaji_melalui.getText().toString().isEmpty() || et_pembayaran_gaji_melalui.getText().toString().equalsIgnoreCase("pilih")){
            tf_pembayaran_gaji_melalui.setError("Format "+ tf_pembayaran_gaji_melalui.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_pembayaran_gaji_melalui.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_tanggal_verifikasi_pejabat_berwenang.getText().toString().isEmpty() || et_tanggal_verifikasi_pejabat_berwenang.getText().toString().equalsIgnoreCase("pilih")){
            tf_tanggal_verifikasi_pejabat.setError("Format "+ tf_tanggal_verifikasi_pejabat.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_tanggal_verifikasi_pejabat.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_nama_pejabat_berwenang.getText().toString().isEmpty() || et_nama_pejabat_berwenang.getText().toString().equalsIgnoreCase("pilih")){
            tf_nama_pejabat_berwenang.setError("Format "+ tf_nama_pejabat_berwenang.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nama_pejabat_berwenang.getLabelText()+" "+getString(R.string.title_validate_field));
        }
//        else if (et_no_surat_rekomendasi.getText().toString().isEmpty() || et_no_surat_rekomendasi.getText().toString().equalsIgnoreCase("pilih")){
//            tf_no_surat_rekomendasi.setError("Format "+ tf_no_surat_rekomendasi.getLabelText()+" "+getString(R.string.title_validate_field), true);
//            return new VerificationError("Format "+ tf_no_surat_rekomendasi.getLabelText()+" "+getString(R.string.title_validate_field));
//        }

//        else if(val_ImgKantor1 == 0){
//            return new VerificationError("Silahkan pilih / ambil Foto Kantor");
//        }
//
//        else if(val_ImgKantor2 == 0){
//            return new VerificationError("Silahkan pilih / ambil Foto Kantor");
//        }

        //ini berdasarkan catatan mbak nutri, dia tidak mandatory
//        else if (et_tanggal_verifikasi_pejabat_berwenang.getText().toString().isEmpty() || et_tanggal_verifikasi_pejabat_berwenang.getText().toString().equalsIgnoreCase("pilih")){
//            tf_tanggal_verifikasi_pejabat.setError("Format "+ tf_tanggal_verifikasi_pejabat.getLabelText()+" "+getString(R.string.title_validate_field), true);
//            return new VerificationError("Format "+ tf_tanggal_verifikasi_pejabat.getLabelText()+" "+getString(R.string.title_validate_field));
//        }

        else{
             Log.wtf("contoh data perusahaan atas","980");
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner(){
        Log.wtf("contoh data perusahaan atas","987");

        DataLengkapPojo data = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", DataLengkapActivityPurna.idAplikasi).findFirst();
        if(KeyValue.getTypeUsahaorJob(et_bidang_pekerjaan.getText().toString().trim())!=null&&!KeyValue.getTypeUsahaorJob(et_bidang_pekerjaan.getText().toString().trim()).isEmpty()){
            val_bidang_pekerjaan = (KeyValue.getTypeUsahaorJob(et_bidang_pekerjaan.getText().toString().trim()));
        }
        else{
            val_bidang_pekerjaan ="";
        }

        val_NamaUsaha = (et_namaperusahaan.getText().toString().trim());
//        val_TglMulaiUsaha = (AppUtil.parseTanggalGeneral(et_tanggalmulaiperusahaan.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
        val_TglPensiunan = (AppUtil.parseTanggalGeneral(et_tanggalmulaipensiun.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
        val_NoTelpUsaha = (et_nomortelponperusahaan.getText().toString().trim());

        val_AlamatUsaha = (et_alamatperusahaan.getText().toString().trim());
        val_RtUsaha = (et_rtperusahaan.getText().toString().trim());
        val_RwUsaha = (et_rwperusahaan.getText().toString().trim());
        val_ProvUsaha = (et_provinsiperusahaan.getText().toString().trim());
        val_KotaUsaha = (et_kotaperusahaan.getText().toString().trim());
        val_KecUsaha = (et_kecamatanperusahaan.getText().toString().trim());
        val_KelUsaha = (et_kelurahanperusahaan.getText().toString().trim());
        val_KodePosUsaha = (et_kodeposperusahaan.getText().toString().trim());

        val_no_sk_pegawai_tetap=(et_nomor_sk_pegawai_tetap.getText().toString().trim());
        val_no_sk_pangkat_terakhir=(et_nomor_sk_pangkat_terakhir.getText().toString().trim());
        val_no_induk_pegawai=(et_nomor_induk_pegawai.getText().toString().trim());
        val_no_sk_pegawai_tetap=(et_nomor_sk_pegawai_tetap.getText().toString().trim());
        val_usia_mpp=(et_usia_mpp.getText().toString().trim());


        val_status_kepegawaian=(KeyValue.getTypeStatusKepegawaian(et_status_kepegawaian.getText().toString().trim()));
        val_posisi_jabatan=(KeyValue.getTypePosisiJabatan(et_posisi_jabatan.getText().toString().trim()));
        val_pembayaran_gaji_melalui=(KeyValue.getTypePembayaranGaji(et_pembayaran_gaji_melalui.getText().toString().trim()));
        val_tanggal_verifikasi = (AppUtil.parseTanggalGeneral(et_tanggal_verifikasi_pejabat_berwenang.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));

        val_nama_pejabat_berwenang=(et_nama_pejabat_berwenang.getText().toString().trim());
        val_no_surat_rekomendasi=(et_no_surat_rekomendasi.getText().toString().trim());
        val_kategori_nasabah=(et_kategori_calon_nasabah_pensiunan.getText().toString().trim());

        val_nomor_sk_pensiun=et_nomor_sk_pensiun.getText().toString().trim();
        val_nomor_taspen=et_nomor_taspen.getText().toString().trim();


        //panntekan khusus purna
        if(gimmick.equalsIgnoreCase("14")||gimmick.equalsIgnoreCase("15")||gimmick.equalsIgnoreCase("16")){
           val_status_kepegawaian="4";

           //ini masi kontroversial, dipantek ini, apa dikosongin aja (as per 23 april, still error kalo kosongin, harus pantek)
           val_posisi_jabatan="01";

        }

        val_nama_pegawai=appPreferences.getNama();
        val_nama_kantor=appPreferences.getKodeCabang();



        final DataLengkapPojo d = new DataLengkapPojo();
        d.setBidangPekerjaan(val_bidang_pekerjaan);
        d.setNamaPerusahaan(val_NamaUsaha);
//        d.setTglMulaiBekerja(val_TglMulaiUsaha);
        d.setTglPensiunan(val_TglPensiunan);
        d.setNoTelpPerusahaan(val_NoTelpUsaha);
        d.setAlamatPerusahaan(val_AlamatUsaha);
        d.setProvPerusahaan(val_ProvUsaha);
        d.setKotaPerusahaan(val_KotaUsaha);
        d.setKecPerusahaan(val_KecUsaha);
        d.setKelPerusahaan(val_KelUsaha);
        d.setKodePosPerusahaan(val_KodePosUsaha);
        d.setNoSKPertama(val_no_sk_pegawai_tetap);
        d.setNoSKterakhir(val_no_sk_pangkat_terakhir);
        d.setPendidikanTerakhir(val_no_sk_pangkat_terakhir);
        d.setNIP(val_no_induk_pegawai);
        d.setUsiaMpp(val_usia_mpp);
        d.setStatusKepegawaian(val_status_kepegawaian);
        d.setJabatan(val_posisi_jabatan);
        d.setPembayaranGaji(val_pembayaran_gaji_melalui);
        d.setTglVerifikasi(val_tanggal_verifikasi);
        d.setNamaPejabat(val_nama_pejabat_berwenang);
        d.setNoRekomendasi(val_no_surat_rekomendasi);
        d.setIdInstansi(dataLengkap.getIdInstansi());

        // pantekan
        d.setKodeCabang(val_nama_kantor);
        d.setNamaPegawai(val_nama_pegawai);

        d.setKodeCabang("");
        d.setNamaPegawai("");
        d.setNamaID(data.getNamaNasabah());
        d.setFID_PHOTO_KANTOR1(val_ImgKantor1);
        d.setFID_PHOTO_KANTOR2(val_ImgKantor2);
        d.setSkPensiun(val_nomor_sk_pensiun);
        d.setNomorTaspen(val_nomor_taspen);

        if(gimmick.equalsIgnoreCase("1")){
            d.setFlagEMBP(1);
            d.setJenisKMG("Karyawan/PNS Program EmBP");

        }
        else {
            d.setFlagEMBP(0);
            d.setJenisKMG(val_kategori_nasabah);
        }



        d.setAlamatId(data.getAlamatId());
        d.setRtId(data.getRtId());
        d.setRwId(data.getRwId());
        d.setProvId(data.getProvId());
        d.setKotaId(data.getKotaId());
        d.setKecId(data.getKecId());
        d.setKelId(data.getKelId());
        d.setKodePosId(data.getKodePosId());
        d.setStatusTptTinggal(data.getStatusTptTinggal());
        d.setLamaMenetap(data.getLamaMenetap());
        d.setAlamatDom(data.getAlamatDom());
        d.setRtDom(data.getRtDom());
        d.setRwDom(data.getRwDom());
        d.setProvDom(data.getProvDom());
        d.setKotaDom(data.getKotaDom());
        d.setKecDom(data.getKecDom());
        d.setKelDom(data.getKelDom());
        d.setKodePosDom(data.getKodePosDom());

        d.setCif(data.getCif());
        d.setUid(data.getUid());
        d.setIdAplikasi(data.getIdAplikasi());
        d.setNamaAlias(data.getNamaAlias());
        d.setNoKtpPasangan(data.getNoKtpPasangan());
        d.setStatusNikah(data.getStatusNikah());
        d.setNoHp(data.getNoHp());
        d.setNamaNasabah(data.getNamaNasabah());
        d.setNpwp(data.getNpwp());
        d.setPendidikanTerakhir(data.getPendidikanTerakhir());
        d.setKetAgama(data.getKetAgama());
        d.setAgama(data.getAgama());
        d.setNamaIbu(data.getNamaIbu());
        d.setNamaPasangan(data.getNamaPasangan());
        d.setEmail(data.getEmail());
        d.setTelpKeluarga(data.getTelpKeluarga());
        d.setExpId(data.getExpId());
        d.setTgllahirPasangan(data.getTgllahirPasangan());
        d.setNoKtp(data.getNoKtp());
        d.setJlhTanggungan(data.getJlhTanggungan());
        d.setTglLahir(data.getTglLahir());
        d.setKeluarga(data.getKeluarga());
        d.setTptLahir(data.getTptLahir());
        d.setTipePendapatan(data.getTipePendapatan());
        d.setJenkel(data.getJenkel());
        d.setReferensi(data.getReferensi());
        d.setFID_PHOTO_RUMAH1(data.getFID_PHOTO_RUMAH1());
        d.setFID_PHOTO_RUMAH2(data.getFID_PHOTO_RUMAH2());

        //
//        d.setTglLahir("10041960");
//        d.setFID_PHOTO_KANTOR1(0);
//        d.setFID_PHOTO_KANTOR2(0);
//
//        Toast.makeText(getContext(), "ada pantekan tgl lahir dan foto", Toast.LENGTH_SHORT).show();

        Log.wtf("contoh data perusahaan",val_TglPensiunan);

        //pantekan usia
//        d.setTglLahir("01011973");
//        Toast.makeText(getContext(), "ada pantekan usia", Toast.LENGTH_SHORT).show();

        //pantekan nama pasangan
//        d.setNamaPasangan("Rima sayur");

        realm.executeTransaction (new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(d);
            }
        });
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            switch (v.getId()){
                case R.id.et_bidangusaha:
                    openKeyValueDialog(tf_bidang_pekerjaan.getLabelText().toString().trim());
                    break;
            }
        }
    }

    @Override
    public void onAddressSelect(address data) {
        et_provinsiperusahaan.setText(data.getProvinsi());
        et_kotaperusahaan.setText(data.getKota());
        et_kecamatanperusahaan.setText(data.getKecamatan());
        et_kelurahanperusahaan.setText(data.getKelurahan());
        et_kodeposperusahaan.setText(data.getKodepos());
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
//        if (title.equalsIgnoreCase("bidang usaha")){
//            et_bidang_pekerjaan.setText(data.getName());
//            AppUtil.dynamicIconLogoChangeDropdown(getContext(), tf_bidang_pekerjaan, et_bidang_pekerjaan);
//        }

        if (title.equalsIgnoreCase("posisi jabatan")){
            et_posisi_jabatan.setText(data.getName());
            val_posisi_jabatan=data.getValue();

            //add to realm when got the data
            DataLengkapPojo existingRealm = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", DataLengkapActivityPurna.idAplikasi).findFirst();
            if(!realm.isInTransaction()){
                realm.beginTransaction();
            }
            existingRealm.setJabatan(data.getValue());
            realm.commitTransaction(); //realm.close();

            AppUtil.dynamicIconLogoChangeDropdown(getContext(), tf_posisi_jabatan, et_status_kepegawaian);
        }

        else if (title.equalsIgnoreCase("pembayaran gaji melalui")){
            et_pembayaran_gaji_melalui.setText(data.getName());
            val_pembayaran_gaji_melalui=data.getValue();


            //add to realm when got the data
            DataLengkapPojo existingRealm = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", DataLengkapActivityPurna.idAplikasi).findFirst();
            if(!realm.isInTransaction()){
                realm.beginTransaction();
            }
            existingRealm.setPembayaranGaji(data.getValue());
            realm.commitTransaction(); //realm.close();

            AppUtil.dynamicIconLogoChangeDropdown(getContext(), tf_pembayaran_gaji_melalui, et_pembayaran_gaji_melalui);
        }

        else if (title.equalsIgnoreCase("status kepegawaian")){
            et_status_kepegawaian.setText(data.getName());
            val_status_kepegawaian=data.getValue();

            //add to realm when got the data
            DataLengkapPojo existingRealm = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", DataLengkapActivityPurna.idAplikasi).findFirst();
            if(!realm.isInTransaction()){
                realm.beginTransaction();
            }
            existingRealm.setStatusKepegawaian(data.getValue());
            realm.commitTransaction(); //realm.close();
            AppUtil.dynamicIconLogoChangeDropdown(getContext(), tf_status_kepegawaian, et_status_kepegawaian);
        }
    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        if (isSelectPhoto.equalsIgnoreCase("fotokantor1")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_KANTOR1);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_KANTOR1);
                    break;
            }
        }
        else if (isSelectPhoto.equalsIgnoreCase("fotokantor2")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_KANTOR2);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_KANTOR2);
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
                case TAKE_PICTURE_KANTOR1:
                case PICK_PICTURE_KANTOR1:
                    setDataImage(uriPhotoKantor1, bitmapPhotoKantor1, img_fotokantor1, data);
                    break;
                case TAKE_PICTURE_KANTOR2:
                case PICK_PICTURE_KANTOR2:
                    setDataImage(uriPhotoKantor2, bitmapPhotoKantor2, img_fotokantor2, data);
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
            String filename = appPreferences.getFotoKantor();
            ImageHandler.saveImageToCache(getContext(), bitmap, filename);
            uploadFoto(filename, iv, bitmap);
        }
    }
    private void initImageFileName() {
        String fileName = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()) + "_" + System.currentTimeMillis() + ".jpg";
        appPreferences.setFotoKantor(fileName);
    }

    public void uploadFoto(String filename, final ImageView iv, final Bitmap bitmap) {
        if(iv == img_fotokantor1){
            progress_kantor1.setVisibility(View.VISIBLE);
        }
        if(iv == img_fotokantor2){
            progress_kantor2.setVisibility(View.VISIBLE);
        }
//        loading.setVisibility(View.VISIBLE);
        File imageFile = new File(getActivity().getApplicationContext().getCacheDir(), filename);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().uploadFileOld(fileBody);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {


                DataLengkapPojo existingRealm = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", DataLengkapActivityPurna.idAplikasi).findFirst();
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
//                            loading.setVisibility(View.GONE);
                            iv.setImageBitmap(bitmap);
                            if(iv == img_fotokantor1){
                                progress_kantor1.setVisibility(View.GONE);
                                bitmapPhotoKantor1 = bitmap;
                            }
                            if(iv == img_fotokantor2){
                                progress_kantor2.setVisibility(View.GONE);
                                bitmapPhotoKantor2 = bitmap;
                            }

                            int idFoto = response.body().getData().get("id").getAsInt();
                            switch (isSelectPhoto){
                                case "fotokantor1":
                                    val_ImgKantor1 = idFoto;
                                    //add to realm when got the id
                                    if(!realm.isInTransaction()){
                                        realm.beginTransaction();
                                    }
                                    Log.d("masukrealm dengan id : ",Integer.toString(idFoto));
                                    existingRealm.setFID_PHOTO_KANTOR1(idFoto);
                                    realm.commitTransaction(); //realm.close();
                                    break;
                                case "fotokantor2":
                                    val_ImgKantor2 = idFoto;
                                    //add to realm when got the id
                                    if(!realm.isInTransaction()){
                                        realm.beginTransaction();
                                    }
                                    Log.d("masukrealm dengan id : ",Integer.toString(idFoto));
                                    existingRealm.setFID_PHOTO_KANTOR2(idFoto);
                                    realm.commitTransaction(); //realm.close();
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            if(iv == img_fotokantor1){
                                progress_kantor1.setVisibility(View.GONE);
                            }
                            if(iv == img_fotokantor2){
                                progress_kantor2.setVisibility(View.GONE);
                            }
//                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        if(iv == img_fotokantor1){
                            progress_kantor1.setVisibility(View.GONE);
                        }
                        if(iv == img_fotokantor2){
                            progress_kantor2.setVisibility(View.GONE);
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
                if(iv == img_fotokantor1){
                    progress_kantor1.setVisibility(View.GONE);
                }
                if(iv == img_fotokantor2){
                    progress_kantor2.setVisibility(View.GONE);
                }
//                loading.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void setDynamicIcon(){
        AppUtil.dynamicIconLogoChange(getContext(),et_namaperusahaan,tf_namaperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_sisa_plafond_perusahaan,tf_sisa_plafond_perusahaan);
        AppUtil.dynamicIconLogoChange(getContext(), et_institusi_pembayaran_gaji_pensiun, tf_institusi_pembayaran_gaji_pensiun);
        AppUtil.dynamicIconLogoChange(getContext(), et_direct_marketing, tf_direct_marketing);
        AppUtil.dynamicIconLogoChange(getContext(), et_kategori_calon_nasabah_pensiunan, tf_kategori_calon_nasabah_pensiunan);
        AppUtil.dynamicIconLogoChange(getContext(),et_nomortelponperusahaan,tf_nomortelponperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_nomor_sk_pegawai_tetap,tf_nomor_sk_pegawai_tetap);
        AppUtil.dynamicIconLogoChange(getContext(),et_nomor_sk_pangkat_terakhir,tf_nomor_sk_pangkat_terakhir);
        AppUtil.dynamicIconLogoChange(getContext(),et_nomor_induk_pegawai,tf_nomor_induk_pegawai);
        AppUtil.dynamicIconLogoChange(getContext(),et_jenis_pekerjaan,tf_jenis_pekerjaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_deskripsi_pekerjaan,tf_deskripsi_pekerjaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_usia_mpp,tf_usia_mpp);
        AppUtil.dynamicIconLogoChange(getContext(),et_nama_pejabat_berwenang,tf_nama_pejabat_berwenang);
        AppUtil.dynamicIconLogoChange(getContext(),et_no_surat_rekomendasi,tf_no_surat_rekomendasi);
        AppUtil.dynamicIconLogoChange(getContext(),et_alamatperusahaan,tf_alamatperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_rtperusahaan,tf_rtperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_rwperusahaan,tf_rwperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_provinsiperusahaan,tf_provinsiperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_kotaperusahaan,tf_kotaperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_kecamatanperusahaan,tf_kecamatanperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_kelurahanperusahaan,tf_kelurahanperusahaan);
        AppUtil.dynamicIconLogoChange(getContext(),et_kodeposperusahaan,tf_kodeposperusahaan);

        AppUtil.dynamicIconLogoChange(getContext(),et_nomor_sk_pensiun,tf_nomor_sk_pensiun);
        AppUtil.dynamicIconLogoChange(getContext(),et_nomor_taspen,tf_nomor_taspen);
        AppUtil.dynamicIconLogoChange(getContext(),et_kodeposperusahaan,tf_kodeposperusahaan);

    }

    private void setDynamicIconDropDown(){
        AppUtil.dynamicIconLogoChangeDropdown(getContext(), tf_bidang_pekerjaan, et_bidang_pekerjaan);
        AppUtil.dynamicIconLogoChangeDropdown(getContext(),tf_tanggalmulaiperusahaan,et_tanggalmulaiperusahaan);
        AppUtil.dynamicIconLogoChangeDropdown(getContext(),tf_tanggalmulaipensiun,et_tanggalmulaipensiun);
        AppUtil.dynamicIconLogoChangeDropdown(getContext(), tf_status_kepegawaian ,et_status_kepegawaian);
        AppUtil.dynamicIconLogoChangeDropdown(getContext(), tf_posisi_jabatan ,et_posisi_jabatan);
        AppUtil.dynamicIconLogoChangeDropdown(getContext(), tf_pembayaran_gaji_melalui ,et_pembayaran_gaji_melalui);
        AppUtil.dynamicIconLogoChangeDropdown(getContext(), tf_tanggal_verifikasi_pejabat ,et_tanggal_verifikasi_pejabat_berwenang);
    }

    private void ubahFieldKhususPurna(){

        tf_nomor_sk_pensiun.setVisibility(View.VISIBLE);
        tf_nomor_taspen.setVisibility(View.VISIBLE);
        tf_nomortelponperusahaan.setLabelText("Nomor Telpon Perusahaan - Opsional");
        tf_no_surat_rekomendasi.setVisibility(View.GONE);
        et_no_surat_rekomendasi.setVisibility(View.GONE);
        tf_tanggalmulaipensiun.setVisibility(View.GONE);
        et_tanggalmulaipensiun.setVisibility(View.GONE);
        tf_nomor_sk_pangkat_terakhir.setVisibility(View.GONE);
        tf_nomor_sk_pegawai_tetap.setVisibility(View.GONE);
        tf_status_kepegawaian.setVisibility(View.GONE);
        tf_posisi_jabatan.setVisibility(View.GONE);
        tf_bidang_pekerjaan.setVisibility(View.GONE);
        tf_namaperusahaan.setVisibility(View.GONE);
        tf_jenis_pekerjaan.setVisibility(View.GONE);
        tf_deskripsi_pekerjaan.setVisibility(View.GONE);
        tf_sisa_plafond_perusahaan.setVisibility(View.GONE);
        tf_alamatperusahaan.setVisibility(View.GONE);
        tf_provinsiperusahaan.setVisibility(View.GONE);
        tf_kecamatanperusahaan.setVisibility(View.GONE);
        tf_kelurahanperusahaan.setVisibility(View.GONE);
        tf_rtperusahaan.setVisibility(View.GONE);
        tf_rwperusahaan.setVisibility(View.GONE);
        tf_kotaperusahaan.setVisibility(View.GONE);
        tf_kodeposperusahaan.setVisibility(View.GONE);
        btn_perusahaan.setVisibility(View.GONE);
        ll_alamatperusahaan.setVisibility(View.GONE);
        rl_fotokantor1.setVisibility(View.GONE);
        rl_fotokantor2.setVisibility(View.GONE);

    }

    private void saveToRealm(){
        DataLengkapPojo existingRealm = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", DataLengkapActivityPurna.idAplikasi).findFirst();


        //save nomor telpon to realm
        et_nomortelponperusahaan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!realm.isInTransaction()){
                        realm.beginTransaction();
                    }
                    existingRealm.setNoTelpPerusahaan(et_nomortelponperusahaan.getText().toString());
                    realm.commitTransaction(); //realm.close();

                }
            }
        });

        //save nomor sk pegawai tetap
        et_nomor_sk_pegawai_tetap.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!realm.isInTransaction()){
                        realm.beginTransaction();
                    }
                    existingRealm.setNoSKPertama(et_nomor_sk_pegawai_tetap.getText().toString());
                    realm.commitTransaction(); //realm.close();

                }
            }
        });

        //save nomor sk pangkat terakhir
        et_nomor_sk_pangkat_terakhir.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!realm.isInTransaction()){
                        realm.beginTransaction();
                    }
                    existingRealm.setNoSKterakhir(et_nomor_sk_pangkat_terakhir.getText().toString());
                    realm.commitTransaction(); //realm.close();

                }
            }
        });

        //save nomor induk pegawai
        et_nomor_induk_pegawai.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!realm.isInTransaction()){
                        realm.beginTransaction();
                    }
                    existingRealm.setNIP(et_nomor_induk_pegawai.getText().toString());
                    realm.commitTransaction(); //realm.close();

                }
            }
        });

        //save usia mpp
        et_usia_mpp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!realm.isInTransaction()){
                        realm.beginTransaction();
                    }
                    existingRealm.setUsiaMpp(et_usia_mpp.getText().toString());
                    realm.commitTransaction(); //realm.close();

                }
            }
        });


        //save no surat rekomendasi
        et_no_surat_rekomendasi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!realm.isInTransaction()){
                        realm.beginTransaction();
                    }
                    existingRealm.setNoRekomendasi(et_no_surat_rekomendasi.getText().toString());
                    realm.commitTransaction(); //realm.close();

                }
            }
        });

        //purna
        //save nomor sk pensiun
        et_nomor_sk_pensiun.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!realm.isInTransaction()){
                        realm.beginTransaction();
                    }
                    existingRealm.setSkPensiun(et_nomor_sk_pensiun.getText().toString());
                    realm.commitTransaction(); //realm.close();

                }
            }
        });

        //save nomor sk pensiun
        et_nomor_taspen.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!realm.isInTransaction()){
                        realm.beginTransaction();
                    }
                    existingRealm.setNomorTaspen(et_nomor_taspen.getText().toString());
                    realm.commitTransaction(); //realm.close();

                }
            }
        });

        //save nomor sk pensiun
        et_nama_pejabat_berwenang.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!realm.isInTransaction()){
                        realm.beginTransaction();
                    }
                    existingRealm.setNamaPejabat(et_nama_pejabat_berwenang.getText().toString());
                    realm.commitTransaction(); //realm.close();

                }
            }
        });





    }
}
