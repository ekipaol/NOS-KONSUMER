package com.application.bris.ikurma_nos_konsumer.page_prescoring;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.AddressListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ProductListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.address;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.datapribadi;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.page_data_finansial.DataFinansialKprActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.ImageHandler;

import java.io.File;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class PreScoringKonsumerActivity extends AppCompatActivity implements
        View.OnClickListener, View.OnFocusChangeListener, ProductListener, AddressListener, KeyValueListener, CameraListener, ConfirmListener, TextWatcher {

    @BindView(R.id.tb_regular)
    Toolbar toolbar;
    @BindView(R.id.tv_page_title)
    TextView tv_page_title;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    @BindView(R.id.ll_penilaian_yayasan)
    LinearLayout ll_penilaian_yayasan;

    @BindView(R.id.ll_penilaian_nasabah)
    LinearLayout ll_penilaian_nasabah;

    @BindView(R.id.ll_penilaian_tempat_bekerja)
    LinearLayout ll_penilaian_tempat_bekerja;

    //Data Penilaian Calon nasabah
    @BindView(R.id.tf_jenis_prescoring)
    TextFieldBoxes tf_jenis_prescoring;

    @BindView(R.id.et_jenis_prescoring)
    EditText et_jenis_prescoring;

    @BindView(R.id.tf_status_pegawai)
    TextFieldBoxes tf_status_pegawai;

    @BindView(R.id.et_status_pegawai)
    EditText et_status_pegawai;

    @BindView(R.id.tf_kepemilikan_kartu_kredit)
    TextFieldBoxes tf_kepemilikan_kartu_kredit;

    @BindView(R.id.et_kepemilikan_kartu_kredit)
    EditText et_kepemilikan_kartu_kredit;

    @BindView(R.id.tf_id_pegawai)
    TextFieldBoxes tf_id_pegawai;

    @BindView(R.id.et_id_pegawai)
    EditText et_id_pegawai;

    @BindView(R.id.tf_slip_gaji)
    TextFieldBoxes tf_slip_gaji;

    @BindView(R.id.et_slip_gaji)
    EditText et_slip_gaji;

    @BindView(R.id.tf_rekening_gaji)
    TextFieldBoxes tf_rekening_gaji;

    @BindView(R.id.et_rekening_gaji)
    EditText et_rekening_gaji;

    @BindView(R.id.tf_kebenaran_informasi)
    TextFieldBoxes tf_kebenaran_informasi;

    @BindView(R.id.et_kebenaran_informasi)
    EditText et_kebenaran_informasi;


    //Penilaian Tempat Bekerja
    @BindView(R.id.tf_operasional_kantor)
    TextFieldBoxes tf_operasional_kantor;

    @BindView(R.id.et_operasional_kantor)
    EditText et_operasional_kantor;

    @BindView(R.id.tf_prospek_usaha)
    TextFieldBoxes tf_prospek_usaha;

    @BindView(R.id.et_prospek_usaha)
    EditText et_prospek_usaha;

    @BindView(R.id.tf_lama_beroperasi_kantor)
    TextFieldBoxes tf_lama_beroperasi_kantor;

    @BindView(R.id.et_lama_beroperasi_kantor)
    EditText et_lama_beroperasi_kantor;

    @BindView(R.id.tf_akses_lokasi_kantor)
    TextFieldBoxes tf_akses_lokasi_kantor;

    @BindView(R.id.et_akses_lokasi_kantor)
    EditText et_akses_lokasi_kantor;

    @BindView(R.id.tf_jenis_kantor)
    TextFieldBoxes tf_jenis_kantor;

    @BindView(R.id.et_jenis_kantor)
    EditText et_jenis_kantor;

    @BindView(R.id.tf_papan_nama_kantor)
    TextFieldBoxes tf_papan_nama_kantor;

    @BindView(R.id.et_papan_nama_kantor)
    EditText et_papan_nama_kantor;

    @BindView(R.id.tf_jumlah_karyawan)
    TextFieldBoxes tf_jumlah_karyawan;

    @BindView(R.id.et_jumlah_karyawan)
    EditText et_jumlah_karyawan;

    @BindView(R.id.tf_keputusan_penilaian_calon_nasabah)
    TextFieldBoxes tf_keputusan_penilaian_calon_nasabah;

    @BindView(R.id.et_keputusan_penilaian_calon_nasabah)
    EditText et_keputusan_penilaian_calon_nasabah;

    @BindView(R.id.tf_keputusan_penilaian_tempat_bekerja)
    TextFieldBoxes tf_keputusan_penilaian_tempat_bekerja;

    @BindView(R.id.et_keputusan_penilaian_tempat_bekerja)
    EditText et_keputusan_penilaian_tempat_bekerja;



    //PENILAIAN YAYASAN

    @BindView(R.id.tf_kepemilikan_badan_usaha)
    TextFieldBoxes tf_kepemilikan_badan_usaha;

    @BindView(R.id.et_kepemilikan_badan_usaha)
    EditText et_kepemilikan_badan_usaha;

    @BindView(R.id.tf_keterangan_domisili_yayasan)
    TextFieldBoxes tf_keterangan_domisili_yayasan;

    @BindView(R.id.et_keterangan_domisili_yayasan)
    EditText et_keterangan_domisili_yayasan;

    @BindView(R.id.tf_total_penyertaan_yayasan)
    TextFieldBoxes tf_total_penyertaan_yayasan;

    @BindView(R.id.et_total_penyertaan_yayasan)
    EditText et_total_penyertaan_yayasan;

    @BindView(R.id.tf_jumlah_penyertaan_yayasan)
    TextFieldBoxes tf_jumlah_penyertaan_yayasan;

    @BindView(R.id.et_jumlah_penyertaan_yayasan)
    EditText et_jumlah_penyertaan_yayasan;

    @BindView(R.id.tf_kesesuaian_pendirian)
    TextFieldBoxes tf_kesesuaian_pendirian;

    @BindView(R.id.et_kesesuaian_pendirian)
    EditText et_kesesuaian_pendirian;

    @BindView(R.id.tf_kesesuaian_dengan_bris)
    TextFieldBoxes tf_kesesuaian_dengan_bris;

    @BindView(R.id.et_kesesuaian_dengan_bris)
    EditText et_kesesuaian_dengan_bris;

    @BindView(R.id.tf_struktur_manajemen)
    TextFieldBoxes tf_struktur_manajemen;

    @BindView(R.id.et_struktur_manajemen)
    EditText et_struktur_manajemen;

    @BindView(R.id.tf_lama_berdiri_yayasan)
    TextFieldBoxes tf_lama_berdiri_yayasan;

    @BindView(R.id.et_lama_berdiri_yayasan)
    EditText et_lama_berdiri_yayasan;

    @BindView(R.id.tf_lama_berdiri_badan_usaha)
    TextFieldBoxes tf_lama_berdiri_badan_usaha;

    @BindView(R.id.et_lama_berdiri_badan_usaha)
    EditText et_lama_berdiri_badan_usaha;






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
    private static String val_jenis_prescoring = "";
    private static String val_status_pegawai = "";
    private static String val_kepemilikan_kartu_kredit = "";
    private static String val_id_name_pegawai = "";
    private static String val_slip_gaji = "";
    private static String val_rekening_gaji = "";
    private static String val_kebenaran_informasi = "";

    private static String val_operasional_kantor = "";
    private static String val_prospek_usaha = "";
    private static String val_lama_beroperasi_kantor = "";
    private static String val_akses_lokasi = "";
    private static String val_jenis_kantor = "";
    private static String val_papan_nama_kantor= "";
    private static String val_jumlah_karyawan = "";
    private static String val_kepemilikan_badan_usaha = "";
    private static String val_keterangan_domisili_yayasan = "";
    private static String val_lama_berdiri_yayasan = "";
    private static String val_total_penyertaan_yayasan = "";
    private static String val_jumlah_penyertaan_yayasan = "";
    private static String val_kesesuaian_pendirian_badan_usaha = "";
    private static String val_kesesuaian_dengan_bris = "";
    private static String val_struktur_manajemen = "";
    private static String val_lama_berdiri_badan_usaha = "";


    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konsumer_form_prescoring);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
//        setGps();
        backgroundStatusBar();
//        checkCollapse();
        tv_page_title.setText("Form Pre Scoring");
        btn_back.setOnClickListener(this);
        btn_send.setOnClickListener(this);

        //onclick listener untuk field keputusan
        et_keputusan_penilaian_calon_nasabah.setFocusable(false);
        tf_keputusan_penilaian_calon_nasabah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PreScoringKonsumerActivity.this, "Kolom ini akan terisi otomatis ketika anda klik tombol lihat hasil keputusan di bagian bawah halaman", Toast.LENGTH_LONG).show();
            }
        });
        et_keputusan_penilaian_tempat_bekerja.setFocusable(false);
        tf_keputusan_penilaian_tempat_bekerja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PreScoringKonsumerActivity.this, "Kolom ini akan terisi otomatis ketika anda klik tombol lihat hasil keputusan di bagian bawah halaman", Toast.LENGTH_LONG).show();
            }
        });
        onclickSelectDialog();

        //get default textfield
        setTextFieldVisibility(et_jenis_prescoring.getText().toString());


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
//        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangedListener() {
//            @Override
//            public void onStateChanged(AppBarLayout appBarLayout, State state) {
//                if (state.name().equalsIgnoreCase("COLLAPSED")) {
//                    tv_page_title.setVisibility(View.VISIBLE);
//                    btn_takepicture.setVisibility(View.VISIBLE);
//                    tv_page_title.setText("Input Data Nasabah");
//                } else {
//                    tv_page_title.setVisibility(View.GONE);
//                    btn_takepicture.setVisibility(View.GONE);
//                    tv_page_title.setText("");
//                }
//            }
//        });
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

    //method untuk mengganti visibility textfield berdasarkan jenis prescoring yang dipilih
    private void setTextFieldVisibility(String jenis_prescoring){


        //RESET TEXTFIELD, BIAR KALO USER GONTA GANTI JENIS PRESCORING, FIELD YANG DI HIDDEN MUNCUL LAGI
        ll_penilaian_yayasan.setVisibility(View.VISIBLE);
        tf_prospek_usaha.setVisibility(View.VISIBLE);
        tf_lama_beroperasi_kantor.setVisibility(View.VISIBLE);




        if(jenis_prescoring.equalsIgnoreCase("prescoring pt")){
            ll_penilaian_yayasan.setVisibility(View.GONE);
            tf_prospek_usaha.setVisibility(View.GONE);
        }
        else  if(jenis_prescoring.equalsIgnoreCase("prescoring cv")){
            ll_penilaian_yayasan.setVisibility(View.GONE);
        }
        else  if(jenis_prescoring.equalsIgnoreCase("prescoring yayasan")){
            tf_lama_beroperasi_kantor.setVisibility(View.GONE);
            tf_prospek_usaha.setVisibility(View.GONE);
        }
        else{
            Toast.makeText(this, "Terjadi kesalahan, harap pilih jenis prescoringyang sesuai", Toast.LENGTH_SHORT).show();
        }
    }


    //method ketika klik panah dropdown
    private void onclickSelectDialog() {

        //KONSUMER
        subSelectDialog(et_jenis_prescoring,tf_jenis_prescoring);
        subSelectDialog(et_status_pegawai,tf_status_pegawai);
        subSelectDialog(et_kepemilikan_kartu_kredit,tf_kepemilikan_kartu_kredit);
        subSelectDialog(et_id_pegawai,tf_id_pegawai);
        subSelectDialog(et_status_pegawai,tf_status_pegawai);
        subSelectDialog(et_slip_gaji,tf_slip_gaji);
        subSelectDialog(et_rekening_gaji,tf_rekening_gaji);
        subSelectDialog(et_kebenaran_informasi,tf_kebenaran_informasi);


        subSelectDialog(et_operasional_kantor,tf_operasional_kantor);
        subSelectDialog(et_prospek_usaha,tf_prospek_usaha);
        subSelectDialog(et_lama_beroperasi_kantor,tf_lama_beroperasi_kantor);
        subSelectDialog(et_akses_lokasi_kantor,tf_akses_lokasi_kantor);
        subSelectDialog(et_jenis_kantor,tf_jenis_kantor);
        subSelectDialog(et_papan_nama_kantor,tf_papan_nama_kantor);
        subSelectDialog(et_jumlah_karyawan,tf_jumlah_karyawan);


        subSelectDialog(et_kepemilikan_badan_usaha,tf_kepemilikan_badan_usaha);
        subSelectDialog(et_keterangan_domisili_yayasan,tf_keterangan_domisili_yayasan);
        subSelectDialog(et_lama_berdiri_yayasan,tf_lama_berdiri_yayasan);
        subSelectDialog(et_total_penyertaan_yayasan,tf_total_penyertaan_yayasan);
        subSelectDialog(et_jumlah_penyertaan_yayasan,tf_jumlah_penyertaan_yayasan);
        subSelectDialog(et_kesesuaian_pendirian,tf_kesesuaian_pendirian);
        subSelectDialog(et_kesesuaian_dengan_bris,tf_kesesuaian_dengan_bris);
        subSelectDialog(et_struktur_manajemen,tf_struktur_manajemen);
        subSelectDialog(et_lama_berdiri_badan_usaha,tf_lama_berdiri_badan_usaha);




    }

    //method untuk mempersingkat method onclickselectdialog^ jadi semua semua onclick ditaruh disini, gak menggunakan implement onclick
    private void subSelectDialog(EditText editText, final TextFieldBoxes textFieldBoxes){
        editText.setFocusable(false);
        editText.setOnFocusChangeListener(this);
        textFieldBoxes.setOnClickListener(this);
        textFieldBoxes.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(textFieldBoxes.getLabelText().toString().trim());
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    openKeyValueDialog(textFieldBoxes.getLabelText().toString().trim());
                }

            }
        });

        textFieldBoxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(textFieldBoxes.getLabelText().toString().trim());
            }
        });
    }

    //method ketika on click di edittext yang berupa pilihan/dropdown
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
//            switch (v.getId()) {
//
//                //KONSUMER
//                case R.id.et_tingkat_pendidikan:
//                    openKeyValueDialog(tf_tingkat_pendidikan.getLabelText().toString().trim());
//                    break;
//
//                case R.id.et_jenis_kelamin:
//                    openKeyValueDialog(tf_jenis_kelamin.getLabelText().toString().trim());
//                    break;
//
//                case R.id.et_tanggal_mulai_bekerja:
//                    datePickerTanggalLahir(et_tanggal_mulai_bekerja,tf_tanggal_mulai_bekerja);
//                    break;
//
//                case R.id.et_expired_ktp:
//                    datePickerTanggalLahir(et_expired_ktp,tf_expired_ktp);
//                    break;
//
//                case R.id.et_tanggal_lahir:
//                    datePickerTanggalLahir(et_tanggal_lahir,tf_tanggal_lahir);
//                    break;
//
//                case R.id.et_no_telp:
//                    openKeyValueDialog(tf_no_telp.getLabelText().toString().trim());
//                    break;
//
//
//
//
//                case R.id.et_kepemilikan_tempat_tinggal:
//                    openKeyValueDialog(tf_kepemilikian_tempat_tinggal.getLabelText().toString().trim());
//                    break;
//


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
                Intent intent=new Intent(PreScoringKonsumerActivity.this, DataFinansialKprActivity.class);
                startActivity(intent);
//
//                if (validateForm()) {
////                        processInputPipeline();
//                    Toast.makeText(PreScoringKonsumerActivity.this, "Menyimpan data", Toast.LENGTH_SHORT).show();
//                }

                break;

            //KONSUMER

            //ambil textfield saja, karena edittextnya di set focusable false, jadi tidak akan bisa di klik juga
//            case R.id.tf_tingkat_pendidikan:
//                openKeyValueDialog(tf_tingkat_pendidikan.getLabelText().toString().trim());
//                break;
//
//            case R.id.tf_jenis_kelamin:
//                openKeyValueDialog(tf_jenis_kelamin.getLabelText().toString().trim());
//                break;
//
//
//            case R.id.tf_no_telp:
//                openKeyValueDialog(tf_no_telp.getLabelText().toString().trim());
//                break;
//
//            case R.id.tf_expired_ktp:
//                datePickerTanggalLahir(et_expired_ktp,tf_expired_ktp);
//                break;
//
//            case R.id.tf_tanggal_lahir:
//                datePickerTanggalLahir(et_tanggal_lahir,tf_tanggal_lahir);
//                break;
//
//            case R.id.tf_tanggal_mulai_bekerja:
//                datePickerTanggalLahir(et_tanggal_mulai_bekerja,tf_tanggal_mulai_bekerja);
//                break;
//
//
//            case R.id.tf_kepemilikian_tempat_tinggal:
//                openKeyValueDialog(tf_kepemilikian_tempat_tinggal.getLabelText().toString().trim());
//                break;
//

            //TAKE PICTURE



        }
    }

    //method ketika memilih salah satu opsi di dialog fragment, tidak bisa di simplifikasi , eh belum bisa, belum cukup ilmunya :')
    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase("status pegawai")) {
            et_status_pegawai.setText(data.getName());
            AppUtil.dynamicIconLogoChangeDropdown(PreScoringKonsumerActivity.this,tf_status_pegawai);
            val_status_pegawai = data.getValue(); //set value berdasarkan pilihan di dialog fragment
        }
        else if (title.equalsIgnoreCase(tf_kepemilikan_kartu_kredit.getLabelText().substring(0,tf_kepemilikan_kartu_kredit.getLabelText().toString().length()-2))) {
            et_kepemilikan_kartu_kredit.setText(data.getName());
            val_kepemilikan_kartu_kredit = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_kepemilikan_kartu_kredit);
        }
        else if (title.equalsIgnoreCase(tf_id_pegawai.getLabelText().substring(0,tf_id_pegawai.getLabelText().toString().length()-2))) {
            et_id_pegawai.setText(data.getName());
            val_id_name_pegawai = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_id_pegawai);
        }
        else if (title.equalsIgnoreCase(tf_slip_gaji.getLabelText().substring(0,tf_slip_gaji.getLabelText().toString().length()-2))) {
            et_slip_gaji.setText(data.getName());
            val_slip_gaji = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_slip_gaji);
        }
        else if (title.equalsIgnoreCase(tf_rekening_gaji.getLabelText().substring(0,tf_rekening_gaji.getLabelText().toString().length()-2))) {
            et_rekening_gaji.setText(data.getName());
            val_rekening_gaji = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_rekening_gaji);
        }
        else if (title.equalsIgnoreCase(tf_kebenaran_informasi.getLabelText().substring(0,tf_kebenaran_informasi.getLabelText().toString().length()-2))) {
            et_kebenaran_informasi.setText(data.getName());
            val_kebenaran_informasi = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_kebenaran_informasi);
        }
        else if (title.equalsIgnoreCase(tf_operasional_kantor.getLabelText().substring(0,tf_operasional_kantor.getLabelText().toString().length()-2))) {
            et_operasional_kantor.setText(data.getName());
            val_operasional_kantor = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_operasional_kantor);
        }
        else if (title.equalsIgnoreCase(tf_prospek_usaha.getLabelText().substring(0,tf_prospek_usaha.getLabelText().toString().length()-2))) {
            et_prospek_usaha.setText(data.getName());
            val_prospek_usaha = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_prospek_usaha);
        }
        else if (title.equalsIgnoreCase(tf_lama_beroperasi_kantor.getLabelText().substring(0,tf_lama_beroperasi_kantor.getLabelText().toString().length()-2))) {
            et_lama_beroperasi_kantor.setText(data.getName());
            val_lama_beroperasi_kantor = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_lama_beroperasi_kantor);
        }
        else if (title.equalsIgnoreCase(tf_akses_lokasi_kantor.getLabelText().substring(0,tf_akses_lokasi_kantor.getLabelText().toString().length()-2))) {
            et_akses_lokasi_kantor.setText(data.getName());
            val_akses_lokasi = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_akses_lokasi_kantor);
        }
        else if (title.equalsIgnoreCase(tf_jenis_kantor.getLabelText().substring(0,tf_jenis_kantor.getLabelText().toString().length()-2))) {
            et_jenis_kantor.setText(data.getName());
            val_jenis_kantor = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_jenis_kantor);
        }
        else if (title.equalsIgnoreCase(tf_papan_nama_kantor.getLabelText().substring(0,tf_papan_nama_kantor.getLabelText().toString().length()-2))) {
            et_papan_nama_kantor.setText(data.getName());
            val_papan_nama_kantor = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_papan_nama_kantor);
        }
        else if (title.equalsIgnoreCase(tf_jumlah_karyawan.getLabelText().substring(0,tf_jumlah_karyawan.getLabelText().toString().length()-2))) {
            et_jumlah_karyawan.setText(data.getName());
            val_jumlah_karyawan = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_jumlah_karyawan);
        }


        else if (title.equalsIgnoreCase(tf_kepemilikan_badan_usaha.getLabelText().substring(0,tf_kepemilikan_badan_usaha.getLabelText().toString().length()-2))) {
            et_kepemilikan_badan_usaha.setText(data.getName());
            val_kepemilikan_badan_usaha = data.getValue(); //set value berdasarkan pilihan di dialog fragment


            Log.d("et_kepemilikan",et_kepemilikan_badan_usaha.getText().toString());

            if(et_kepemilikan_badan_usaha.getText().toString().trim().equalsIgnoreCase("tidak memiliki badan usaha")){
                Toast.makeText(this, "Field otomatis diisi nihil karena tidak memiliki badan usaha", Toast.LENGTH_LONG).show();

//                et_keterangan_domisili_yayasan.setText("Nihil");
//                tf_keterangan_domisili_yayasan.setEnabled(false);
//                et_lama_berdiri_yayasan.setText("Nihil");
//                et_total_penyertaan_yayasan.setText("Nihil");
//                et_jumlah_penyertaan_yayasan.setText("Nihil");
//                et_kesesuaian_pendirian.setText("Nihil");
//                et_kesesuaian_dengan_bris.setText("Nihil");
//                et_struktur_manajemen.setText("Nihil");
//                et_lama_berdiri_badan_usaha.setText("Nihil");

                yayasanAutoAdjuster(et_keterangan_domisili_yayasan,tf_keterangan_domisili_yayasan,true);
                yayasanAutoAdjuster(et_lama_berdiri_yayasan,tf_lama_berdiri_yayasan,true);
                yayasanAutoAdjuster(et_lama_berdiri_badan_usaha,tf_lama_berdiri_badan_usaha,true);
                yayasanAutoAdjuster(et_kesesuaian_dengan_bris,tf_kesesuaian_dengan_bris,true);
                yayasanAutoAdjuster(et_kesesuaian_pendirian,tf_kesesuaian_pendirian,true);
                yayasanAutoAdjuster(et_jumlah_penyertaan_yayasan,tf_jumlah_penyertaan_yayasan,true);
                yayasanAutoAdjuster(et_total_penyertaan_yayasan,tf_total_penyertaan_yayasan,true);
                yayasanAutoAdjuster(et_struktur_manajemen,tf_struktur_manajemen,true);

                dynamicIconLogoChangeDropdown(tf_kepemilikan_badan_usaha);

            }
            else{
//                et_keterangan_domisili_yayasan.setText("Pilih");
//                et_lama_berdiri_yayasan.setText("Pilih");
//                et_total_penyertaan_yayasan.setText("Pilih");
//                et_jumlah_penyertaan_yayasan.setText("Pilih");
//                et_kesesuaian_pendirian.setText("Pilih");
//                et_kesesuaian_dengan_bris.setText("Pilih");
//                et_struktur_manajemen.setText("Pilih");
//                et_lama_berdiri_badan_usaha.setText("Pilih");

                yayasanAutoAdjuster(et_keterangan_domisili_yayasan,tf_keterangan_domisili_yayasan,false);
                yayasanAutoAdjuster(et_lama_berdiri_yayasan,tf_lama_berdiri_yayasan,false);
                yayasanAutoAdjuster(et_lama_berdiri_badan_usaha,tf_lama_berdiri_badan_usaha,false);
                yayasanAutoAdjuster(et_kesesuaian_dengan_bris,tf_kesesuaian_dengan_bris,false);
                yayasanAutoAdjuster(et_kesesuaian_pendirian,tf_kesesuaian_pendirian,false);
                yayasanAutoAdjuster(et_jumlah_penyertaan_yayasan,tf_jumlah_penyertaan_yayasan,false);
                yayasanAutoAdjuster(et_total_penyertaan_yayasan,tf_total_penyertaan_yayasan,false);
                yayasanAutoAdjuster(et_struktur_manajemen,tf_struktur_manajemen,false);
                dynamicIconLogoChangeDropdown(tf_kepemilikan_badan_usaha);
            }
        }
        else if (title.equalsIgnoreCase(tf_keterangan_domisili_yayasan.getLabelText().substring(0,tf_keterangan_domisili_yayasan.getLabelText().toString().length()-2))) {
            et_keterangan_domisili_yayasan.setText(data.getName());
            val_keterangan_domisili_yayasan = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_keterangan_domisili_yayasan);
        }
        else if (title.equalsIgnoreCase(tf_lama_berdiri_yayasan.getLabelText().substring(0,tf_lama_berdiri_yayasan.getLabelText().toString().length()-2))) {
            et_lama_berdiri_yayasan.setText(data.getName());
            val_lama_berdiri_yayasan = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_lama_berdiri_yayasan);
        }
        else if (title.equalsIgnoreCase(tf_total_penyertaan_yayasan.getLabelText().substring(0,tf_total_penyertaan_yayasan.getLabelText().toString().length()-2))) {
            et_total_penyertaan_yayasan.setText(data.getName());
            val_total_penyertaan_yayasan = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_total_penyertaan_yayasan);
        }
        else if (title.equalsIgnoreCase(tf_jumlah_penyertaan_yayasan.getLabelText().substring(0,tf_jumlah_penyertaan_yayasan.getLabelText().toString().length()-2))) {
            et_jumlah_penyertaan_yayasan.setText(data.getName());
            val_jumlah_penyertaan_yayasan = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_jumlah_penyertaan_yayasan);
        }
        else if (title.equalsIgnoreCase(tf_kesesuaian_pendirian.getLabelText().substring(0,tf_kesesuaian_pendirian.getLabelText().toString().length()-2))) {
            et_kesesuaian_pendirian.setText(data.getName());
            val_kesesuaian_pendirian_badan_usaha = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_kesesuaian_pendirian);
        }
        else if (title.equalsIgnoreCase(tf_kesesuaian_dengan_bris.getLabelText().substring(0,tf_kesesuaian_dengan_bris.getLabelText().toString().length()-2))) {
            et_kesesuaian_dengan_bris.setText(data.getName());
            val_kesesuaian_dengan_bris = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_kesesuaian_dengan_bris);
        }
        else if (title.equalsIgnoreCase(tf_struktur_manajemen.getLabelText().substring(0,tf_struktur_manajemen.getLabelText().toString().length()-2))) {
            et_struktur_manajemen.setText(data.getName());
            val_struktur_manajemen = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_struktur_manajemen);
        }
        else if (title.equalsIgnoreCase(tf_lama_berdiri_badan_usaha.getLabelText().substring(0,tf_lama_berdiri_badan_usaha.getLabelText().toString().length()-2))) {
            et_lama_berdiri_badan_usaha.setText(data.getName());
            val_lama_berdiri_badan_usaha = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            dynamicIconLogoChangeDropdown(tf_lama_berdiri_badan_usaha);
        }
        else if (title.equalsIgnoreCase(tf_jenis_prescoring.getLabelText().substring(0,tf_jenis_prescoring.getLabelText().toString().length()-2))) {
            et_jenis_prescoring.setText(data.getName());
            val_jenis_prescoring = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            setTextFieldVisibility(et_jenis_prescoring.getText().toString());
            dynamicIconLogoChangeDropdown(tf_jenis_prescoring);
        }





    }

    private void yayasanAutoAdjuster(EditText editText,TextFieldBoxes textFieldBoxes,boolean apakah_nihil){
        if(apakah_nihil){
            editText.setText("Nihil");
            textFieldBoxes.setEnabled(false);
            textFieldBoxes.setOnClickListener(null);
            textFieldBoxes.getEndIconImageButton().setOnClickListener(null);
        }
        else{
            editText.setText("Pilih");
            textFieldBoxes.setEnabled(true);
            textFieldBoxes.setOnClickListener(this);
            subSelectDialog(editText,textFieldBoxes);
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
                    textFieldBoxes.getIconImageButton().setColorFilter(ContextCompat.getColor(PreScoringKonsumerActivity.this, R.color.colorPrimaryRed));

                    textFieldBoxes.getIconImageButton().setImageResource(R.drawable.ic_error_outline_secondary_24dp);

                }
                else{
                    textFieldBoxes.setIsResponsiveIconColor(false);
                    textFieldBoxes.getIconImageButton().setColorFilter(ContextCompat.getColor(PreScoringKonsumerActivity.this, R.color.colorGreenSuccess));
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
        textFieldBoxes.getIconImageButton().setColorFilter(ContextCompat.getColor(PreScoringKonsumerActivity.this, R.color.colorGreenSuccess));
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





    public void processInputPipeline() {
        loading.setVisibility(View.VISIBLE);
        String filename = appPreferences.getFotoNasabahName();
        if (CHANGE_PICTURE == 1) {
            ImageHandler.saveImageToCache(this, bitmapPhoto, filename);
            File imageFile = new File(getApplicationContext().getCacheDir(), filename);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
            MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);
            Call<ParseResponse> call = apiClientAdapter.getApiInterface().uploadFileOld(fileBody);
            call.enqueue(new Callback<ParseResponse>() {
                @Override
                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                    try {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase("00")) {
                                sendData(response.body().getData().get("id").getAsInt());
                            } else {
                                loading.setVisibility(View.GONE);
                                AppUtil.notiferror(PreScoringKonsumerActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            }
                        } else {
                            loading.setVisibility(View.GONE);
                            Error error = ParseResponseError.confirmEror(response.errorBody());
                            AppUtil.notiferror(PreScoringKonsumerActivity.this, findViewById(android.R.id.content), error.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ParseResponse> call, Throwable t) {
                    loading.setVisibility(View.GONE);
                    AppUtil.notiferror(PreScoringKonsumerActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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

         boolean statusValidasi=false;
        //reset nilai jumlah validasi
        jumlahValidasi=0;

        //else dan if buat validasi seluruh field dengan menggunakan 1 method

        if(et_jenis_prescoring.getText().toString().equalsIgnoreCase("prescoring pt")){
            subValidate(et_status_pegawai,tf_status_pegawai);
            subValidate(et_kepemilikan_kartu_kredit,tf_kepemilikan_kartu_kredit);
            subValidate(et_id_pegawai,tf_id_pegawai);
            subValidate(et_slip_gaji,tf_slip_gaji);
            subValidate(et_rekening_gaji,tf_rekening_gaji);
            subValidate(et_kebenaran_informasi,tf_kebenaran_informasi);


            subValidate(et_operasional_kantor,tf_operasional_kantor);
            subValidate(et_lama_beroperasi_kantor,tf_lama_beroperasi_kantor);
            subValidate(et_akses_lokasi_kantor,tf_akses_lokasi_kantor);
            subValidate(et_jenis_kantor,tf_jenis_kantor);
            subValidate(et_jumlah_karyawan,tf_jumlah_karyawan);

            if(jumlahValidasi==11){
                statusValidasi= true;
            }

            else{
                statusValidasi= false;
            }

        }
        else if(et_jenis_prescoring.getText().toString().equalsIgnoreCase("prescoring cv")||et_jenis_prescoring.getText().toString().equalsIgnoreCase("prescoring lainnya")){
            subValidate(et_status_pegawai,tf_status_pegawai);
            subValidate(et_kepemilikan_kartu_kredit,tf_kepemilikan_kartu_kredit);
            subValidate(et_id_pegawai,tf_id_pegawai);
            subValidate(et_slip_gaji,tf_slip_gaji);
            subValidate(et_rekening_gaji,tf_rekening_gaji);
            subValidate(et_kebenaran_informasi,tf_kebenaran_informasi);


            subValidate(et_operasional_kantor,tf_operasional_kantor);
            subValidate(et_prospek_usaha,tf_prospek_usaha);
            subValidate(et_lama_beroperasi_kantor,tf_lama_beroperasi_kantor);
            subValidate(et_akses_lokasi_kantor,tf_akses_lokasi_kantor);
            subValidate(et_jenis_kantor,tf_jenis_kantor);
            subValidate(et_papan_nama_kantor,tf_papan_nama_kantor);
            subValidate(et_jumlah_karyawan,tf_jumlah_karyawan);

            if(jumlahValidasi==12){
                statusValidasi= true;
            }

            else{
                statusValidasi= false;
            }
        }

        else if(et_jenis_prescoring.getText().toString().equalsIgnoreCase("prescoring yayasan")){
            subValidate(et_status_pegawai,tf_status_pegawai);
            subValidate(et_kepemilikan_kartu_kredit,tf_kepemilikan_kartu_kredit);
            subValidate(et_id_pegawai,tf_id_pegawai);
            subValidate(et_slip_gaji,tf_slip_gaji);
            subValidate(et_rekening_gaji,tf_rekening_gaji);
            subValidate(et_kebenaran_informasi,tf_kebenaran_informasi);


            subValidate(et_kepemilikan_badan_usaha,tf_kepemilikan_badan_usaha);
            subValidate(et_lama_berdiri_yayasan,tf_lama_berdiri_yayasan);
            subValidate(et_total_penyertaan_yayasan,tf_total_penyertaan_yayasan);
            subValidate(et_jumlah_penyertaan_yayasan,tf_jumlah_penyertaan_yayasan);
            subValidate(et_kesesuaian_pendirian,tf_kesesuaian_pendirian);
            subValidate(et_kesesuaian_dengan_bris,tf_kesesuaian_dengan_bris);
            subValidate(et_struktur_manajemen,tf_struktur_manajemen);
            subValidate(et_lama_berdiri_badan_usaha,tf_lama_berdiri_badan_usaha);


            subValidate(et_operasional_kantor,tf_operasional_kantor);
            subValidate(et_akses_lokasi_kantor,tf_akses_lokasi_kantor);
            subValidate(et_jenis_kantor,tf_jenis_kantor);
            subValidate(et_papan_nama_kantor,tf_papan_nama_kantor);
            subValidate(et_jumlah_karyawan,tf_jumlah_karyawan);

            if(jumlahValidasi==19){
                statusValidasi= true;
            }
            else{
                statusValidasi= false;
            }


        }

        return statusValidasi;

    }

    private void subValidate(EditText editext,TextFieldBoxes textFieldBoxes){
        if (editext.getText().toString().trim().equalsIgnoreCase("pilih") || editext.getText().toString().trim().isEmpty()) {
            //agar user tau field yang error dari textfield
            textFieldBoxes.setError("Harap pilih " + textFieldBoxes.getLabelText().substring(0, textFieldBoxes.getLabelText().length() - 2), true);

            //agar user tau nama field yang error dari snackbar
            AppUtil.notiferror(PreScoringKonsumerActivity.this, findViewById(R.id.nested_scv), "Harap pilih " + textFieldBoxes.getLabelText().substring(0, textFieldBoxes.getLabelText().length() - 2));


        }
        else{
            jumlahValidasi=jumlahValidasi+1;
        }
    }



    @Override
    public void onSelectMenuCamera(String idMenu) {
        switch (idMenu) {
            case "Take Photo":
//                openCamera();
                break;
            case "Pick Photo":
//                openGalery();
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