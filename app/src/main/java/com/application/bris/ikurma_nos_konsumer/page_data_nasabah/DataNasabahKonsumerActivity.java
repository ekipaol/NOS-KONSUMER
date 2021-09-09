package com.application.bris.ikurma_nos_konsumer.page_data_nasabah;


import android.Manifest;
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
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.ProductPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogAddress;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.AddressListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ProductListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.address;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.datapribadi;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.page_prescoring.PreScoringKonsumerActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppBarStateChangedListener;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.ImageHandler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class DataNasabahKonsumerActivity extends AppCompatActivity implements
        View.OnClickListener, View.OnFocusChangeListener, ProductListener, AddressListener, KeyValueListener, CameraListener, ConfirmListener, TextWatcher {

    @BindView(R.id.tb_custom)
    Toolbar toolbar;
    @BindView(R.id.tv_page_title)
    TextView tv_page_title;
    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    @BindView(R.id.btn_takepicture)
    ImageView btn_takepicture;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    //Data Nasabah
    @BindView(R.id.tf_tingkat_pendidikan)
    TextFieldBoxes tf_tingkat_pendidikan;
    @BindView(R.id.et_tingkat_pendidikan)
    EditText et_tingkat_pendidikan;
    @BindView(R.id.tf_jenis_kelamin)
    TextFieldBoxes tf_jenis_kelamin;
    @BindView(R.id.et_jenis_kelamin)
    EditText et_jenis_kelamin;
    @BindView(R.id.tf_no_ktp)
    TextFieldBoxes tf_no_ktp;
    @BindView(R.id.et_no_ktp)
    EditText et_no_ktp;
    @BindView(R.id.tf_tempat_lahir)
    TextFieldBoxes tf_tempat_lahir;
    @BindView(R.id.et_tempat_lahir)
    EditText et_tempat_lahir;
    @BindView(R.id.tf_npwp)
    TextFieldBoxes tf_npwp;
    @BindView(R.id.et_npwp)
    EditText et_npwp;
    @BindView(R.id.tf_kewarganegaraan)
    TextFieldBoxes tf_kewarganegaraan;
    @BindView(R.id.et_kewarganegaraan)
    EditText et_kewarganegaraan;
    @BindView(R.id.tf_negara_domisili)
    TextFieldBoxes tf_negara_domisili;
    @BindView(R.id.et_negara_domisili)
    EditText et_negara_domisili;
    @BindView(R.id.tf_ket_status)
    TextFieldBoxes tf_ket_status;
    @BindView(R.id.et_ket_status)
    EditText et_ket_status;
    @BindView(R.id.tf_nama_gadis_ibu)
    TextFieldBoxes tf_nama_gadis_ibu;
    @BindView(R.id.et_nama_gadis_ibu)
    EditText et_nama_gadis_ibu;
    @BindView(R.id.tf_expired_ktp)
    TextFieldBoxes tf_expired_ktp;
    @BindView(R.id.et_expired_ktp)
    EditText et_expired_ktp;
    @BindView(R.id.tf_tanggal_lahir)
    TextFieldBoxes tf_tanggal_lahir;
    @BindView(R.id.et_tanggal_lahir)
    EditText et_tanggal_lahir;
    @BindView(R.id.tf_tanggal_mulai_bekerja)
    TextFieldBoxes tf_tanggal_mulai_bekerja;
    @BindView(R.id.et_tanggal_mulai_bekerja)
    EditText et_tanggal_mulai_bekerja;
    @BindView(R.id.tf_no_telp)
    TextFieldBoxes tf_no_telp;
    @BindView(R.id.et_no_telp)
    EditText et_no_telp;
    @BindView(R.id.tf_no_handphone)
    TextFieldBoxes tf_no_handphone;
    @BindView(R.id.et_no_handphone)
    EditText et_no_handphone;
    @BindView(R.id.tf_email)
    TextFieldBoxes tf_email;
    @BindView(R.id.et_email)
    EditText et_email;

    //data alamat
    @BindView(R.id.tf_alamat)
    TextFieldBoxes tf_alamat;
    @BindView(R.id.et_alamat)
    EditText et_alamat;
    @BindView(R.id.tf_lokasi_dati)
    TextFieldBoxes tf_lokasi_dati;
    @BindView(R.id.et_lokasi_dati)
    EditText et_lokasi_dati;
    @BindView(R.id.tf_lama_domisili)
    TextFieldBoxes tf_lama_domisili;
    @BindView(R.id.et_lama_domisili)
    EditText et_lama_domisili;
    @BindView(R.id.tf_kelurahan)
    TextFieldBoxes tf_kelurahan;
    @BindView(R.id.et_kelurahan)
    EditText et_kelurahan;
    @BindView(R.id.tf_kecamatan)
    TextFieldBoxes tf_kecamatan;
    @BindView(R.id.et_kecamatan)
    EditText et_kecamatan;
    @BindView(R.id.tf_kode_pos)
    TextFieldBoxes tf_kode_pos;
    @BindView(R.id.et_kode_pos)
    EditText et_kode_pos;
    @BindView(R.id.tf_kepemilikian_tempat_tinggal)
    TextFieldBoxes tf_kepemilikian_tempat_tinggal;
    @BindView(R.id.et_kepemilikan_tempat_tinggal)
    EditText et_kepemilikan_tempat_tinggal;




    @BindView(R.id.btnfab_takepicture)
    FloatingActionButton btnfab_takepicture;

    private DatePickerDialog dp_tanggallahir;
    private Calendar calendar;
    private Calendar calendarMinDate;

    private final int PICK_IMAGE = 1;
    private final int TAKE_PICTURE = 0;
    private int CHANGE_PICTURE = 0;
    private Uri uriPhoto;
    private Bitmap bitmapPhoto;
    private String dataPribadi;
    private datapribadi dataPr;
    private String nik;
    private int hasFace=0;
    //variabel buat menghitung berapa field yang udah lolos validasi
    private int jumlahValidasi = 0;


    //VALUE
    private static String val_tingkat_pendidikan = "";
    private static String val_jenis_kelamin = "";
    private static String val_no_telp = "";
    private static String val_kepemilikan_tempat_tinggal = "";
    private static String val_tanggal_lahir = "";

    //for npwp formatting
    int dot1=0;
    int dot2=0;
    int dot3=0;
    int dot4=0;
    int dot5=0;






    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konsumer_data_nasabah);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        collapsingToolbar.setStatusBarScrimColor(getResources().getColor(R.color.colorBackgroundTransparent));
//        setGps();
        backgroundStatusBar();
        checkCollapse();
        btn_back.setOnClickListener(this);
        btn_takepicture.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        btnfab_takepicture.setOnClickListener(this);
        iv_photo.setOnClickListener(this);
        onclickSelectDialog();
        onChangeText();

        dynamicIconLogoChange(et_ket_status,tf_ket_status);
        dynamicIconLogoChange(et_nama_gadis_ibu,tf_nama_gadis_ibu);
        dynamicIconLogoChange(et_no_ktp,tf_no_ktp);
        dynamicIconLogoChange(et_tempat_lahir,tf_tempat_lahir);
        dynamicIconLogoChange(et_no_handphone,tf_no_handphone);
        dynamicIconLogoChange(et_nama_gadis_ibu,tf_nama_gadis_ibu);
        dynamicIconLogoChange(et_alamat,tf_alamat);
        dynamicIconLogoChange(et_kelurahan,tf_kelurahan);
        dynamicIconLogoChange(et_kecamatan,tf_kecamatan);
        dynamicIconLogoChange(et_kode_pos,tf_kode_pos);
        dynamicIconLogoChange(et_lokasi_dati,tf_lokasi_dati);
        dynamicIconLogoChange(et_lama_domisili,tf_lama_domisili);
        dynamicIconLogoChange(et_nama_gadis_ibu,tf_nama_gadis_ibu);

        npwpFormattingTextChange(et_npwp);



//metode bang idong dia ngirim intent dalam bentuk string yang isinya adalah objek data, lalu dconvert dalam bentuk GSON untuk dimasukkan di objek databaru, ada di method setData

        //metode eki mengirim langsung objeknya dari intent lalu langsung diterima didalam objek baru di kelas ini, tidak perlu convert gson gson

//        nik = getIntent().getStringExtra("nik");
//        if(getIntent().hasExtra("dataPribadi")){
//            dataPribadi = getIntent().getStringExtra("dataPribadi");
//            setData();
//        }
//        et_nik.setText(nik);

    }

    private void setData() {
//        Gson gson = new Gson();
//        dataPr = gson.fromJson(dataPribadi, datapribadi.class);
//
//        val_urlphoto = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + dataPr.getFid_photo();
//        Glide
//                .with(IsianCifKprActivity.this)
//                .asBitmap()
//                .load(val_urlphoto)
//                .fitCenter()
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        iv_photo.setImageBitmap(resource);
//                        bitmapPhoto = resource;
//                        hasFace = AppUtil.hasFaceImage(bitmapPhoto);
//                    }
//                });
//
//        et_nama.setText(dataPr.getNama_nasabah());
//        et_tempatlahir.setText(dataPr.getTempat_lahir());
//        et_tanggallahir.setText(AppUtil.parseTanggalGeneral(dataPr.getTanggal_lahir(), "ddMMyyyy", "dd-MM-yyyy"));
//        et_alamat.setText(dataPr.getAlamat_domisili());
//        et_rt.setText(dataPr.getRt_domisili());
//        et_rw.setText(dataPr.getRw_domisili());
//        et_provinsi.setText(dataPr.getPropinsi_domisili());
//        et_kota.setText(dataPr.getKota_domisili());
//        et_kecamatan.setText(dataPr.getKecamatan_domisili());
//        et_kelurahan.setText(dataPr.getKelurahan_domisili());
//        et_kodepos.setText(dataPr.getKode_pos_domisili());
//        val_tanggallahir = AppUtil.parseTanggalGeneral(dataPr.getTanggal_lahir(), "ddMMyyyy", "yyyy-MM-dd");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }




    private void backgroundStatusBar() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorBackgroundTransparent));
        }
    }

    private void checkCollapse() {
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangedListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state.name().equalsIgnoreCase("COLLAPSED")) {
                    tv_page_title.setVisibility(View.VISIBLE);
                    btn_takepicture.setVisibility(View.VISIBLE);
                    tv_page_title.setText("Input Data Nasabah");
                } else {
                    tv_page_title.setVisibility(View.GONE);
                    btn_takepicture.setVisibility(View.GONE);
                    tv_page_title.setText("");
                }
            }
        });
    }

    private void openProdukDialog(String title, String segmen, String product) {
        DialogSelect.display(getSupportFragmentManager(), title, segmen, product, this);
    }

    private void openKeyValueDialog(String title) {
        title = title.substring(0, title.length() - 2);
        DialogKeyValue.display(getSupportFragmentManager(), title, this);
    }

    private void openAddressDialog() {
        DialogAddress.display(getSupportFragmentManager(), this);
    }

    private void openCameraMenu() {
        BSBottomCamera.display(getSupportFragmentManager(), this);
    }


    //method ketika klik panah dropdown
    private void onclickSelectDialog() {

        //KONSUMER

        //TINGKAT PENDIDIKAN
        et_tingkat_pendidikan.setFocusable(false);
//        et_referal_dari.setInputType(InputType.TYPE_NULL);
        et_tingkat_pendidikan.setOnFocusChangeListener(this);
        tf_tingkat_pendidikan.setOnClickListener(this);
        tf_tingkat_pendidikan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_tingkat_pendidikan.getLabelText().toString().trim());
            }
        });

        //JENIS KELAMIN
        et_jenis_kelamin.setFocusable(false);
//        et_paket.setInputType(InputType.TYPE_NULL);
        et_jenis_kelamin.setOnFocusChangeListener(this);
        tf_jenis_kelamin.setOnClickListener(this);
        tf_jenis_kelamin.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_jenis_kelamin.getLabelText().toString().trim());
            }
        });

        //NO TELP
        et_no_telp.setFocusable(false);
//        et_program.setInputType(InputType.TYPE_NULL);
        et_no_telp.setOnFocusChangeListener(this);
        tf_no_telp.setOnClickListener(this);
        tf_no_telp.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_no_telp.getLabelText().toString().trim());
            }
        });

        //KEPEMILIKAN TEMPAT TINGGAL
        et_kepemilikan_tempat_tinggal.setFocusable(false);
//        et_pihak_ketiga.setInputType(InputType.TYPE_NULL);
        et_kepemilikan_tempat_tinggal.setOnFocusChangeListener(this);
        tf_kepemilikian_tempat_tinggal.setOnClickListener(this);
        tf_kepemilikian_tempat_tinggal.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_kepemilikian_tempat_tinggal.getLabelText().toString().trim());
            }
        });

        //TANGGAL MULAI BEKERJA
        et_tanggal_mulai_bekerja.setFocusable(false);
//        et_tanggal_lahir.setInputType(InputType.TYPE_NULL);
        et_tanggal_mulai_bekerja.setOnFocusChangeListener(this);
        tf_tanggal_mulai_bekerja.setOnClickListener(this);
        tf_tanggal_mulai_bekerja.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerTanggalLahir(et_tanggal_mulai_bekerja,tf_tanggal_mulai_bekerja);
            }
        });

        //EXPIRED ID KTP
        et_expired_ktp.setFocusable(false);
        et_expired_ktp.setOnFocusChangeListener(this);
        tf_expired_ktp.setOnClickListener(this);
        tf_expired_ktp.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerTanggalLahir(et_expired_ktp,tf_expired_ktp);
            }
        });

        //TANGGAL LAHIR
        et_tanggal_lahir.setFocusable(false);
        et_tanggal_lahir.setOnFocusChangeListener(this);
        tf_tanggal_lahir.setOnClickListener(this);
        tf_tanggal_lahir.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerTanggalLahir(et_tanggal_lahir,tf_tanggal_lahir);
            }
        });



//        //PROGRAM
//        et_program.setFocusable(false);
//        et_program.setInputType(InputType.TYPE_NULL);
//        et_program.setOnFocusChangeListener(this);
//        tf_program.setOnClickListener(this);
//        tf_program.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!et_segmen.getText().toString().trim().isEmpty() && !et_produk.getText().toString().trim().isEmpty()){
//                    openProdukDialog(tf_program.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), et_produk.getText().toString().trim());
//                }
//                else{
//                    AppUtil.notifwarning(IsianCifKprActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect)+" "+tf_segmen.getLabelText()+" atau "+tf_produk.getLabelText()+" terlebih dahulu");
//                }
//            }
//        });


    }

    //method ketika on click di edittext yang berupa pilihan/dropdown
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            switch (v.getId()) {

                //KONSUMER
                case R.id.et_tingkat_pendidikan:
                    openKeyValueDialog(tf_tingkat_pendidikan.getLabelText().toString().trim());
                    break;

                case R.id.et_jenis_kelamin:
                    openKeyValueDialog(tf_jenis_kelamin.getLabelText().toString().trim());
                    break;

                case R.id.et_tanggal_mulai_bekerja:
                    datePickerTanggalLahir(et_tanggal_mulai_bekerja,tf_tanggal_mulai_bekerja);
                    break;

                case R.id.et_expired_ktp:
                    datePickerTanggalLahir(et_expired_ktp,tf_expired_ktp);
                    break;

                case R.id.et_tanggal_lahir:
                    datePickerTanggalLahir(et_tanggal_lahir,tf_tanggal_lahir);
                    break;

                case R.id.et_no_telp:
                    openKeyValueDialog(tf_no_telp.getLabelText().toString().trim());
                    break;




                case R.id.et_kepemilikan_tempat_tinggal:
                    openKeyValueDialog(tf_kepemilikian_tempat_tinggal.getLabelText().toString().trim());
                    break;



                //PROGRAM
//                case R.id.et_program:
//                    if (!et_segmen.getText().toString().trim().isEmpty() && !et_produk.getText().toString().trim().isEmpty()){
//                        openProdukDialog(tf_program.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), et_produk.getText().toString().trim());
//                    }
//                    else{
//                        AppUtil.notifwarning(IsianCifKprActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect)+" "+tf_segmen.getLabelText()+" atau "+tf_produk.getLabelText()+" terlebih dahulu");
//                    }
//                    break;


            }
        }
    }

    //method ketika onclick pada gambar atau textview
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                CustomDialog.DialogBackpress(this);
                break;
            case R.id.btn_pickaddress:
                openAddressDialog();
                break;

            case R.id.btn_send:


                Intent intent=new Intent(DataNasabahKonsumerActivity.this, PreScoringKonsumerActivity.class);
                startActivity(intent);

//                if (validateForm()) {
////                        processInputPipeline();
////                    Toast.makeText(DataNasabahKonsumerActivity.this, "Menyimpan data", Toast.LENGTH_SHORT).show();
//
//                    Intent intent=new Intent(DataNasabahKonsumerActivity.this, PreScoringKonsumerActivity.class);
//                    startActivity(intent);
//                }

                break;

            //KONSUMER

            //ambil textfield saja, karena edittextnya di set focusable false, jadi tidak akan bisa di klik juga
            case R.id.tf_tingkat_pendidikan:
                openKeyValueDialog(tf_tingkat_pendidikan.getLabelText().toString().trim());
                break;

            case R.id.tf_jenis_kelamin:
                openKeyValueDialog(tf_jenis_kelamin.getLabelText().toString().trim());
                break;


            case R.id.tf_no_telp:
                openKeyValueDialog(tf_no_telp.getLabelText().toString().trim());
                break;

            case R.id.tf_expired_ktp:
                datePickerTanggalLahir(et_expired_ktp,tf_expired_ktp);
                break;

            case R.id.tf_tanggal_lahir:
                datePickerTanggalLahir(et_tanggal_lahir,tf_tanggal_lahir);
                break;

            case R.id.tf_tanggal_mulai_bekerja:
                datePickerTanggalLahir(et_tanggal_mulai_bekerja,tf_tanggal_mulai_bekerja);
                break;


            case R.id.tf_kepemilikian_tempat_tinggal:
                openKeyValueDialog(tf_kepemilikian_tempat_tinggal.getLabelText().toString().trim());
                break;


            //TAKE PICTURE
            case R.id.btn_takepicture:
            case R.id.btnfab_takepicture:
                openCameraMenu();
                break;
            case R.id.iv_photo:
                openPreviewPhoto();
                break;


            //MIKRO


            //PROGRAM
//            case R.id.et_program:
//                if (!et_segmen.getText().toString().trim().isEmpty() && !et_produk.getText().toString().trim().isEmpty()){
//                    openProdukDialog(tf_program.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), et_produk.getText().toString().trim());
//                }
//                else{
//                    AppUtil.notifwarning(IsianCifKprActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect)+" "+tf_segmen.getLabelText()+" atau "+tf_produk.getLabelText()+" terlebih dahulu");
//                }
//                break;
//            case R.id.tf_program:
//                if (!et_segmen.getText().toString().trim().isEmpty() && !et_produk.getText().toString().trim().isEmpty()){
//                    openProdukDialog(tf_program.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), et_produk.getText().toString().trim());
//                }
//                else{
//                    AppUtil.notifwarning(IsianCifKprActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect)+" "+tf_segmen.getLabelText()+" atau "+tf_produk.getLabelText()+" terlebih dahulu");
//                }
//                break;


        }
    }

    //method ketika memilih salah satu opsi di dialog fragment
    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase("tingkat pendidikan")) {
            et_tingkat_pendidikan.setText(data.getName());
           AppUtil.dynamicIconLogoChangeDropdown(DataNasabahKonsumerActivity.this,tf_tingkat_pendidikan);
            val_tingkat_pendidikan = data.getValue(); //set value berdasarkan pilihan di dialog fragment
        } else if (title.equalsIgnoreCase("jenis kelamin")) {
            et_jenis_kelamin.setText(data.getName());
            val_jenis_kelamin = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_jenis_kelamin);
        } else if (title.equalsIgnoreCase("No Telp/Fixed Line")) {
            et_no_telp.setText(data.getName());
            val_no_telp = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_no_telp);
        } else if (title.equalsIgnoreCase("kepemilikan tempat tinggal")) {
            et_kepemilikan_tempat_tinggal.setText(data.getName());
            val_kepemilikan_tempat_tinggal = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_kepemilikian_tempat_tinggal);
        }
    }


    //METHOD UNTUK MENGUBAH ICON DI TEXTFIELD SECARA DINAMIS, KLAU SUDAH DIISI DIA CEKLIS, KALO ISINYA DIHAPUS DIA JADI WARNING
    public void dynamicIconLogoChange(EditText editText, final TextFieldBoxes textFieldBoxes){

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()==0){
                    textFieldBoxes.setIsResponsiveIconColor(false);
                    textFieldBoxes.getIconImageButton().setColorFilter(ContextCompat.getColor(DataNasabahKonsumerActivity.this, R.color.colorPrimaryRed));

                    textFieldBoxes.getIconImageButton().setImageResource(R.drawable.ic_error_outline_secondary_24dp);

                }
                else{
                    textFieldBoxes.setIsResponsiveIconColor(false);
                    textFieldBoxes.getIconImageButton().setColorFilter(ContextCompat.getColor(DataNasabahKonsumerActivity.this, R.color.colorGreenSuccess));
                    textFieldBoxes.getIconImageButton().setImageResource(R.drawable.ic_checked);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    //METHOD MENGUBAH ICON TEXTFIELDBOXES KHUSUS UNTUK DROPDOWN/FRAGMENT DIALOG
    public void dynamicIconLogoChangeDropdown( final TextFieldBoxes textFieldBoxes){


        textFieldBoxes.setIsResponsiveIconColor(false);
        textFieldBoxes.getIconImageButton().setColorFilter(ContextCompat.getColor(DataNasabahKonsumerActivity.this, R.color.colorGreenSuccess));
        textFieldBoxes.getIconImageButton().setImageResource(R.drawable.ic_checked);


    }


    @Override
    public void onProductSelect(String title, ProductPojo data) {
//        if (title.equalsIgnoreCase("segmen")){
//            et_segmen.setText(data.getNama_segmen());
//            val_segmen = data.getKode_segmen(); //set value segmen
//            et_produk.getText().clear();
//            et_program.getText().clear();
//            et_jenisusaha.getText().clear();
//            if(data.getNama_segmen().equalsIgnoreCase("konsumer")){
//                tf_program.setVisibility(View.VISIBLE);
//                tf_jenisusaha.setLabelText("Pekerjaan");
//                tf_pendapatan.setLabelText("Pendapatan");
//            }
//            else if (data.getNama_segmen().equalsIgnoreCase("mikro")){
//                tf_program.setVisibility(View.GONE);
//                tf_jenisusaha.setLabelText("Bidang Usaha");
//                tf_pendapatan.setLabelText("Omset Per Bulan");
//            }
//        }
//        else if(title.equalsIgnoreCase("produk")){
//            et_produk.setText(data.getNama_produk());
//            val_produk = data.getKode_produk(); //set value produk
//            et_program.getText().clear();
//        }
//
//        else if(title.equalsIgnoreCase("program")){
//            et_program.setText(data.getNama_gimmick());
//            val_program = String.valueOf(data.getKode_gimmick()); //set value program
//        }
    }


    private void onChangeText() {
//        et_plafond.addTextChangedListener(new NumberTextWatcherForThousand(et_plafond));
//        et_pendapatan.addTextChangedListener(new NumberTextWatcherForThousand(et_pendapatan));
//        et_tindaklanjut.addTextChangedListener(this);
    }

    private void datePickerTanggalLahir(final EditText tanggal_lahir, final TextFieldBoxes textFieldBoxes) {
        calendar = Calendar.getInstance();

        //tambah kondisi jika yang menggunakan method adalah objek untuk editetxt tanggal lahir, maka di set ada tahun minimumnya
        if(textFieldBoxes.getLabelText().substring(0,textFieldBoxes.getLabelText().length()-2).equalsIgnoreCase("tanggal lahir")){
                    calendar.add(Calendar.YEAR, -18);
        calendarMinDate = Calendar.getInstance();
        calendarMinDate.add(Calendar.YEAR, -75);
        }

        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        final SimpleDateFormat formatServer = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        DatePickerDialog.OnDateSetListener ls_tanggallahir = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String tanggallahir = sdf.format(calendar.getTime());
                tanggal_lahir.setText(tanggallahir);
                dynamicIconLogoChangeDropdown(textFieldBoxes);
                if (!tanggal_lahir.getText().toString().trim().isEmpty()) {
                    val_tanggal_lahir = formatServer.format(calendar.getTime()); //set value tanggal lahir
                }
            }
        };

        dp_tanggallahir = new DatePickerDialog(DataNasabahKonsumerActivity.this, R.style.AppTheme_TimePickerTheme, ls_tanggallahir, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        //tambah kondisi jika yang menggunakan method adalah objek untuk editetxt tanggal lahir, maka di set ada tahun minimumnya
        if(textFieldBoxes.getLabelText().substring(0,textFieldBoxes.getLabelText().length()-2).equalsIgnoreCase("tanggal lahir")){
            dp_tanggallahir.getDatePicker().setMinDate(calendarMinDate.getTimeInMillis());
            dp_tanggallahir.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        }

        dp_tanggallahir.show();
    }

    //TAKE PICTURE
    public void openGalery() {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(Intent.createChooser(it, "Select File"), PICK_IMAGE);
    }

    private void openCamera() {
        checkCameraPermission();
    }

    private void openPreviewPhoto() {
        DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", bitmapPhoto);
    }

    private static final int MY_CAMERA_REQUEST_CODE = 100;

    public void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);
        } else {
            Uri outputFileUri = getCaptureImageOutputUri();
            Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(captureIntent, TAKE_PICTURE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                AppUtil.showToastLong(this, "Camera Permission Granted");
                Uri outputFileUri = getCaptureImageOutputUri();
                Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                startActivityForResult(captureIntent, TAKE_PICTURE);
            } else {
                AppUtil.showToastLong(this, "Camera Permission Denied");
            }
        }
    }

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                outputFileUri = FileProvider.getUriForFile(DataNasabahKonsumerActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "fotonasabah.png"));
            } else {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "fotonasabah.png"));
            }
        }
        return outputFileUri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            initImageFileName();
            if (getPickImageResultUri(data) != null) {
                uriPhoto = getPickImageResultUri(data);
                try {
                    bitmapPhoto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriPhoto);
                    bitmapPhoto = AppUtil.getResizedBitmap(bitmapPhoto, 1024);
                    bitmapPhoto = AppUtil.rotateImageIfRequired(this, bitmapPhoto, uriPhoto);
                    iv_photo.setImageBitmap(bitmapPhoto);
                    CHANGE_PICTURE = 1; //FLAG CHANGE PICTURE
                    hasFace = AppUtil.hasFaceImage(bitmapPhoto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    private void initImageFileName() {
        String fileName = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()) + "_" + System.currentTimeMillis() + ".jpg";
        appPreferences.setFotoNasabahName(fileName);
    }

    public void processInputPipeline() {
        loading.setVisibility(View.VISIBLE);
        String filename = appPreferences.getFotoNasabahName();
        if (CHANGE_PICTURE == 1) {
            ImageHandler.saveImageToCache(this, bitmapPhoto, filename);
            File imageFile = new File(getApplicationContext().getCacheDir(), filename);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
            MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);
            Call<ParseResponse> call = apiClientAdapter.getApiInterface().uploadFoto(fileBody);
            call.enqueue(new Callback<ParseResponse>() {
                @Override
                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                    try {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase("00")) {
                                sendData(response.body().getData().get("id").getAsInt());
                            } else {
                                loading.setVisibility(View.GONE);
                                AppUtil.notiferror(DataNasabahKonsumerActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            }
                        } else {
                            loading.setVisibility(View.GONE);
                            Error error = ParseResponseError.confirmEror(response.errorBody());
                            AppUtil.notiferror(DataNasabahKonsumerActivity.this, findViewById(android.R.id.content), error.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ParseResponse> call, Throwable t) {
                    loading.setVisibility(View.GONE);
                    AppUtil.notiferror(DataNasabahKonsumerActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                }
            });
        } else {
            sendData(dataPr.getFid_photo());
        }
    }

    private void sendData(int idPhoto) {
//        val_nama = et_nama.getText().toString().trim();
//        val_nik = et_nik.getText().toString().trim();
//        val_nomorhp = et_nomorhp.getText().toString().trim();
//        val_tempatlahir = et_tempatlahir.getText().toString().trim();
//        val_pendapatan = NumberTextWatcherForThousand.trimCommaOfString(et_pendapatan.getText().toString().trim());
//        val_alamat = et_alamat.getText().toString().trim();
//        val_provinsi = et_provinsi.getText().toString().trim();
//        val_kota = et_kota.getText().toString().trim();
//        val_kecamatan = et_kecamatan.getText().toString().trim();
//        val_kelurahan = et_kelurahan.getText().toString().trim();
//        val_kodepos = et_kodepos.getText().toString().trim();
//        val_rt = et_rt.getText().toString().trim();
//        val_rw = et_rw.getText().toString().trim();
//        val_tenor = et_tenor.getText().toString().trim();
//        val_plafond = NumberTextWatcherForThousand.trimCommaOfString(et_plafond.getText().toString().trim());
//        val_texttindaklanjut = et_tindaklanjut.getText().toString().trim();
//        inputPipeline req = new inputPipeline(String.valueOf(appPreferences.getUid()), 0, val_nama, val_nik, val_nomorhp, val_tempatlahir, val_tanggallahir,
//                idPhoto, String.valueOf(lat)+" "+String.valueOf(lng), val_jenisusaha, AppUtil.parseIntWithDefault(val_pendapatan, 0),
//                val_alamat, val_provinsi, val_kota, val_kelurahan, val_kecamatan, val_kodepos, val_rt, val_rw,
//                AppUtil.parseIntWithDefault(val_plafond, 0), AppUtil.parseIntWithDefault(val_tenor, 0), val_segmen, val_produk, AppUtil.parseIntWithDefault(val_program, 0), val_texttindaklanjut, val_jenistindaklanjut);
//
//        Call<ParseResponse> call = null;
//        if (isHotprospek == 1){
//            call = apiClientAdapter.getApiInterface().savePipelineHotprospek(req);
//        }
//        else {
//            call = apiClientAdapter.getApiInterface().sendDataPipeline(req);
//        }
//
//        call.enqueue(new Callback<ParseResponse>() {
//            @Override
//            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
//                loading.setVisibility(View.GONE);
//                try {
//                    if(response.isSuccessful()){
//                        if(response.body().getStatus().equalsIgnoreCase("00")){
//                            CustomDialog.DialogSuccess(IsianCifKprActivity.this, "Success!", "Input data Pipeline sukses", IsianCifKprActivity.this);
//                        }
//                        else{
//                            AppUtil.notiferror(IsianCifKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
//                        }
//                    }
//                    else {
//                        Error error = ParseResponseError.confirmEror(response.errorBody());
//                        AppUtil.notiferror(IsianCifKprActivity.this, findViewById(android.R.id.content), error.getMessage());
//                    }
//                }
//                catch (Exception e){
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ParseResponse> call, Throwable t) {
//                loading.setVisibility(View.GONE);
//                AppUtil.notiferror(IsianCifKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
//            }
//        });
    }

    private boolean validateForm() {

        //reset nilai jumlah validasi
        jumlahValidasi=0;


        //else dan if buat validasi seluruh field dengan menggunakan 1 method

        subValidate(et_tingkat_pendidikan,tf_tingkat_pendidikan);
        subValidate(et_jenis_kelamin,tf_jenis_kelamin);
        subValidate(et_no_ktp,tf_no_ktp);
        subValidate(et_tempat_lahir,tf_tempat_lahir);
        subValidate(et_alamat,tf_alamat);
        subValidate(et_lokasi_dati,tf_lokasi_dati);
        subValidate(et_lama_domisili,tf_lama_domisili);
        subValidate(et_tanggal_mulai_bekerja,tf_tanggal_mulai_bekerja);
        subValidate(et_nama_gadis_ibu,tf_nama_gadis_ibu);
        subValidate(et_expired_ktp,tf_expired_ktp);
        subValidate(et_tanggal_lahir,tf_tanggal_lahir);
        subValidate(et_kelurahan,tf_kelurahan);
        subValidate(et_kecamatan,tf_kecamatan);
        subValidate(et_kode_pos,tf_kode_pos);
        subValidate(et_no_telp,tf_no_telp);
        subValidate(et_no_handphone,tf_no_handphone);
        subValidate(et_no_telp,tf_no_telp);
        subValidate(et_kepemilikan_tempat_tinggal,tf_kepemilikian_tempat_tinggal);


        //jika sudah seluruh field mandatory tervalidasi, maka true
        if(jumlahValidasi==18){
            return true;
        }
        else{
            return false;
        }




//
//        else if(Validator.validateKtp(et_nik.getText().toString().trim()) == false){
//            tf_nik.setError("Format "+ tf_nik.getLabelText()+" "+getString(R.string.title_invalid_field), true);
//            return false;
//        }
//
//        return true;
    }

    private void subValidate(EditText editext,TextFieldBoxes textFieldBoxes){
          if (editext.getText().toString().trim().equalsIgnoreCase("pilih") || editext.getText().toString().trim().isEmpty()) {
            //agar user tau field yang error dari textfield
              textFieldBoxes.setError("Harap pilih " + textFieldBoxes.getLabelText().substring(0, textFieldBoxes.getLabelText().length() - 2), true);

            //agar user tau nama field yang error dari snackbar
            AppUtil.notiferror(DataNasabahKonsumerActivity.this, findViewById(R.id.nested_scv), "Harap pilih " + textFieldBoxes.getLabelText().substring(0, textFieldBoxes.getLabelText().length() - 2));


        }
          else{
              jumlahValidasi=jumlahValidasi+1;
          }
    }

    private void npwpFormattingTextChange(final EditText npwp){
        npwp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()==2){
                    dot1=1;
                    npwp.setSelection(npwp.getText().length());

                }
                else if(s.length()==3&&dot1==1&&s.toString().substring(2,3)!="."){
                    dot1=0;
                    npwp.setText(npwp.getText().toString().substring(0,2)+"."+npwp.getText().toString().substring(2,3));
                    npwp.setSelection(npwp.getText().length());
                }

                if(s.length()==6){
                    dot2=1;
//                    npwp.setText(npwp.getText()+".");
                    npwp.setSelection(npwp.getText().length());
                }
                else if(s.length()==7&&dot2==1&&s.toString().substring(6,7)!="."){
                    dot2=0;
                    npwp.setText(npwp.getText().toString().substring(0,6)+"."+npwp.getText().toString().substring(6,7));
                    npwp.setSelection(npwp.getText().length());
                }

                if(s.length()==10){
                    dot3=1;
//                    npwp.setText(npwp.getText()+".");
                    npwp.setSelection(npwp.getText().length());
                }
                else if(s.length()==11&&dot3==1&&s.toString().substring(10,11)!="."){
                    dot3=0;
                    npwp.setText(npwp.getText().toString().substring(0,10)+"."+npwp.getText().toString().substring(10,11));
                    npwp.setSelection(npwp.getText().length());
                }

                if(s.length()==12){
                    dot4=1;
//                    npwp.setText(npwp.getText()+".");
                    npwp.setSelection(npwp.getText().length());
                }
                else if(s.length()==13&&dot4==1&&s.toString().substring(12,13)!="-"){
                    dot4=0;
                    npwp.setText(npwp.getText().toString().substring(0,12)+"-"+npwp.getText().toString().substring(12,13));
                    npwp.setSelection(npwp.getText().length());
                }

                if(s.length()==16){
                    dot5=1;
//                    npwp.setText(npwp.getText()+".");
                    npwp.setSelection(npwp.getText().length());
                }
                else if(s.length()==17&&dot5==1&&s.toString().substring(16,17)!="."){
                    dot5=0;
                    npwp.setText(npwp.getText().toString().substring(0,16)+"."+npwp.getText().toString().substring(16,17));
                    npwp.setSelection(npwp.getText().length());
                }

//                if(s.length()==10){
//                    npwp.setText(npwp.getText()+".");
//                    npwp.setSelection(npwp.getText().length());
//                }
//                if(s.length()==12){
//                    npwp.setText(npwp.getText()+"-");
//                    npwp.setSelection(npwp.getText().length());
//                }
//                if(s.length()==16){
//                    npwp.setText(npwp.getText()+".");
//                    npwp.setSelection(npwp.getText().length());
//                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



    @Override
    public void onSelectMenuCamera(String idMenu) {
        switch (idMenu) {
            case "Take Photo":
                openCamera();
                break;
            case "Pick Photo":
                openGalery();
                break;
        }
    }

    @Override
    public void success(boolean val) {
//        if(val){
//            finish();
//            startActivity(new Intent(this, PipelineActivity.class));
//        }

    }

    @Override
    public void confirm(boolean val) {

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        if (et_tindaklanjut.getText().toString().trim().isEmpty() || et_tindaklanjut.getText().toString().trim().equalsIgnoreCase("")){
//            rg_typetindaklanjut.clearCheck();
//            val_jenistindaklanjut = "";
//        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onAddressSelect(address data) {
//        et_provinsi.setText(data.getProvinsi());
//        et_kota.setText(data.getKota());
//        et_kecamatan.setText(data.getKecamatan());
//        et_kelurahan.setText(data.getKelurahan());
//        et_kodepos.setText(data.getKodepos());
    }


    @Override
    public void onBackPressed() {
        CustomDialog.DialogBackpress(this);
    }
}