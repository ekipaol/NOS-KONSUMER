package com.application.bris.ikurma_nos_konsumer.page_isian_cif_all;



import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.ImageHandler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

public class IsianCifKmgActivity extends AppCompatActivity implements
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

    //Data Finansial
    @BindView(R.id.tf_produk)
    TextFieldBoxes tf_produk;
    @BindView(R.id.et_produk)
    EditText et_produk;

    @BindView(R.id.tf_institusi_pembayar_pensiun)
    TextFieldBoxes tf_institusi_pembayar_pensiun;
    @BindView(R.id.et_institusi_pembayar_pensiun)
    EditText et_institusi_pembayar_pensiun;

    @BindView(R.id.tf_pihak_ketiga)
    TextFieldBoxes tf_pihak_ketiga;
    @BindView(R.id.et_pihak_ketiga)
    EditText et_pihak_ketiga;

    @BindView(R.id.tf_instansi_koperasi)
    TextFieldBoxes tf_instansi_koperasi;
    @BindView(R.id.et_instansi_koperasi)
    EditText et_instansi_koperasi;

    @BindView(R.id.tf_kerjasama_instansi_koperasi)
    TextFieldBoxes tf_kerjasama_instansi_koperasi;
    @BindView(R.id.et_kerjasama_instansi_koperasi)
    EditText et_kerjasama_instansi_koperasi;

    @BindView(R.id.tf_deskripsi_tujuan_penggunaan)
    TextFieldBoxes tf_deskripsi_tujuan_penggunaan;
    @BindView(R.id.et_deskripsi_tujuan_penggunaan)
    EditText et_deskripsi_tujuan_penggunaan;

    @BindView(R.id.tf_cif)
    TextFieldBoxes tf_cif;
    @BindView(R.id.et_cif)
    EditText et_cif;

    @BindView(R.id.tf_no_id_ktp)
    TextFieldBoxes tf_no_id_ktp;
    @BindView(R.id.et_no_id_ktp)
    EditText et_no_id_ktp;

    @BindView(R.id.tf_nama_nasabah)
    TextFieldBoxes tf_nama_nasabah;
    @BindView(R.id.et_nama_nasabah)
    EditText et_nama_nasabah;

    @BindView(R.id.tf_jenis_kelamin)
    TextFieldBoxes tf_jenis_kelamin;
    @BindView(R.id.et_jenis_kelamin)
    EditText et_jenis_kelamin;

    @BindView(R.id.tf_nama_sesuai_id)
    TextFieldBoxes tf_nama_sesuai_id;
    @BindView(R.id.et_nama_sesuai_id)
    EditText et_nama_sesuai_id;

    @BindView(R.id.tf_expired_id)
    TextFieldBoxes tf_expired_id;
    @BindView(R.id.et_expired_id)
    EditText et_expired_id;

    @BindView(R.id.tf_tanggal_lahir)
    TextFieldBoxes tf_tanggal_lahir;
    @BindView(R.id.et_tanggal_lahir)
    EditText et_tanggal_lahir;

    @BindView(R.id.tf_tempat_lahir)
    TextFieldBoxes tf_tempat_lahir;
    @BindView(R.id.et_tempat_lahir)
    EditText et_tempat_lahir;

    @BindView(R.id.tf_nama_ibu_kandung)
    TextFieldBoxes tf_nama_ibu_kandung;
    @BindView(R.id.et_nama_ibu_kandung)
    EditText et_nama_ibu_kandung;

    @BindView(R.id.tf_jenis_pekerjaan)
    TextFieldBoxes tf_jenis_pekerjaan;
    @BindView(R.id.et_jenis_pekerjaan)
    EditText et_jenis_pekerjaan;

    @BindView(R.id.tf_kategori_calon_nasabah_pensiunan)
    TextFieldBoxes tf_kategori_calon_nasabah_pensiunan;
    @BindView(R.id.et_kategori_calon_nasabah_pensiunan)
    EditText et_kategori_calon_nasabah_pensiunan;

    @BindView(R.id.tf_embp)
    TextFieldBoxes tf_embp;
    @BindView(R.id.et_embp)
    EditText et_embp;

    @BindView(R.id.tf_rekanan_direct_marketing)
    TextFieldBoxes tf_rekanan_direct_marketing;
    @BindView(R.id.et_rekanan_direct_marketing)
    EditText et_rekanan_direct_marketing;

    @BindView(R.id.tf_referal_dari)
    TextFieldBoxes tf_referal_dari;
    @BindView(R.id.et_referal_dari)
    EditText et_referal_dari;

    @BindView(R.id.tf_pendidikan_terakhir)
    TextFieldBoxes tf_pendidikan_terakhir;
    @BindView(R.id.et_pendidikan_terakhir)
    EditText et_pendidikan_terakhir;

    @BindView(R.id.tf_keterangan_status)
    TextFieldBoxes tf_keterangan_status;
    @BindView(R.id.et_keterangan_status_gelar)
    EditText et_keterangan_status_gelar;

    @BindView(R.id.tf_status_perkawinan)
    TextFieldBoxes tf_status_perkawinan;
    @BindView(R.id.et_status_perkawinan)
    EditText et_status_perkawinan;

    @BindView(R.id.tf_jumlah_tanggungan)
    TextFieldBoxes tf_jumlah_tanggungan;
    @BindView(R.id.et_jumlah_tanggungan)
    EditText et_jumlah_tanggungan;

    @BindView(R.id.tf_no_ktp_pasangan)
    TextFieldBoxes tf_no_ktp_pasangan;
    @BindView(R.id.et_no_ktp_pasangan)
    EditText et_no_ktp_pasangan;

    @BindView(R.id.tf_tujuan_penggunaan)
    TextFieldBoxes tf_tujuan_penggunaan;
    @BindView(R.id.et_tujuan_penggunaan)
    EditText et_tujuan_penggunaan;

    @BindView(R.id.tf_nama_pasangan)
    TextFieldBoxes tf_nama_pasangan;
    @BindView(R.id.et_nama_pasangan)
    EditText et_nama_pasangan;

    @BindView(R.id.tf_tanggal_lahir_pasangan)
    TextFieldBoxes tf_tanggal_lahir_pasangan;
    @BindView(R.id.et_tanggal_lahir_pasangan)
    EditText et_tanggal_lahir_pasangan;




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
    private int hasFace = 0;
    //variabel buat menghitung berapa field yang udah lolos validasi
    private int jumlahValidasi = 0;


    //VALUE
    private static String val_institusi_pembayar_gaji_pensiunan = "";
    private static String val_tujuan_penggunaan = "";
    private static String val_jenis_pekerjaan = "";
    private static String val_jenis_kelamin = "";
    private static String val_pendidikan_terakhir = "";
    private static String val_referal_dari = "";
    private static String val_status_perkawinan = "";
    private static String val_kategori_jenis_pekerjaan = "";
    private static String val_embp = "";
    private static String val_tanggal_lahir = "";
    private static String val_expired_id = "";
    private static String val_tanggal_lahir_pasangan = "";




    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konsumer_kmg_isian_data_cif);

//        //push up when keyboard shown
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ButterKnife.bind(this);

        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
//        setGps();
        backgroundStatusBar();
//        checkCollapse();
        tv_page_title.setText("Form Isian CIF KMG");
        btn_back.setOnClickListener(this);
        btn_send.setOnClickListener(this);
//        et_expired_id.setOnClickListener(this);
//        et_tanggal_lahir.setOnClickListener(this);
//        et_tanggal_lahir_pasangan.setOnClickListener(this);
        onclickSelectDialog();


        //for dynamic icon in textfieldboxes
        AppUtil.dynamicIconLogoChange(IsianCifKmgActivity.this,et_deskripsi_tujuan_penggunaan,tf_deskripsi_tujuan_penggunaan);
        AppUtil.dynamicIconLogoChange(IsianCifKmgActivity.this,et_cif,tf_cif);
        AppUtil.dynamicIconLogoChange(IsianCifKmgActivity.this,et_no_id_ktp,tf_no_id_ktp);
        AppUtil.dynamicIconLogoChange(IsianCifKmgActivity.this,et_nama_nasabah,tf_nama_nasabah);
        AppUtil.dynamicIconLogoChange(IsianCifKmgActivity.this,et_deskripsi_tujuan_penggunaan,tf_deskripsi_tujuan_penggunaan);
        AppUtil.dynamicIconLogoChange(IsianCifKmgActivity.this,et_nama_sesuai_id,tf_nama_sesuai_id);
        AppUtil.dynamicIconLogoChange(IsianCifKmgActivity.this,et_tempat_lahir,tf_tempat_lahir);
        AppUtil.dynamicIconLogoChange(IsianCifKmgActivity.this,et_nama_ibu_kandung,tf_nama_ibu_kandung);
        AppUtil.dynamicIconLogoChange(IsianCifKmgActivity.this,et_keterangan_status_gelar,tf_keterangan_status);
        AppUtil.dynamicIconLogoChange(IsianCifKmgActivity.this,et_jumlah_tanggungan,tf_jumlah_tanggungan);
        AppUtil.dynamicIconLogoChange(IsianCifKmgActivity.this,et_no_ktp_pasangan,tf_no_ktp_pasangan);
        AppUtil.dynamicIconLogoChange(IsianCifKmgActivity.this,et_nama_pasangan,tf_nama_nasabah);




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


    //method ketika klik panah dropdown
    private void onclickSelectDialog() {

        subSelectDialog(et_produk, tf_produk);
        subSelectDialog(et_institusi_pembayar_pensiun, tf_institusi_pembayar_pensiun);
        subSelectDialog(et_pihak_ketiga, tf_pihak_ketiga);
        subSelectDialog(et_instansi_koperasi, tf_instansi_koperasi);
        subSelectDialog(et_kerjasama_instansi_koperasi, tf_kerjasama_instansi_koperasi);
        subSelectDialog(et_deskripsi_tujuan_penggunaan, tf_deskripsi_tujuan_penggunaan);
        subSelectDialog(et_jenis_kelamin, tf_jenis_kelamin);
        subSelectDialog(et_jenis_pekerjaan, tf_jenis_pekerjaan);
        subSelectDialog(et_embp, tf_embp);
        subSelectDialog(et_rekanan_direct_marketing, tf_rekanan_direct_marketing);
        subSelectDialog(et_referal_dari, tf_referal_dari);
        subSelectDialog(et_pendidikan_terakhir, tf_pendidikan_terakhir);
        subSelectDialog(et_status_perkawinan, tf_status_perkawinan);
        subSelectDialog(et_tujuan_penggunaan, tf_tujuan_penggunaan);

        et_tanggal_lahir.setFocusable(false);
        et_tanggal_lahir.setOnFocusChangeListener(this);
        tf_tanggal_lahir.setOnClickListener(this);
        tf_tanggal_lahir.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerTanggalLahir(et_tanggal_lahir,tf_tanggal_lahir);
            }
        });

        et_tanggal_lahir_pasangan.setFocusable(false);
        et_tanggal_lahir_pasangan.setOnFocusChangeListener(this);
        tf_tanggal_lahir_pasangan.setOnClickListener(this);
        tf_tanggal_lahir_pasangan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerTanggalLahir(et_tanggal_lahir_pasangan,tf_tanggal_lahir_pasangan);
            }
        });

        et_expired_id.setFocusable(false);
        et_expired_id.setOnFocusChangeListener(this);
        tf_expired_id.setOnClickListener(this);
        tf_expired_id.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerTanggalLahir(et_expired_id,tf_expired_id);
            }
        });

    }

    //method untuk mempersingkat method onclickselectdialog^ jadi semua semua onclick ditaruh disini, gak menggunakan implement onclick
    private void subSelectDialog(EditText editText, final TextFieldBoxes textFieldBoxes) {
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
                if (hasFocus) {
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

    //   TIDAK DIGUNAKAN----method ketika on click di edittext yang berupa pilihan/dropdown
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
            case R.id.tf_tanggal_lahir:
                datePickerTanggalLahir(et_tanggal_lahir,tf_tanggal_lahir);
                break;
            case R.id.tf_tanggal_lahir_pasangan:
                datePickerTanggalLahir(et_tanggal_lahir_pasangan,tf_tanggal_lahir_pasangan);
                break;
            case R.id.tf_expired_id:
                datePickerTanggalLahir(et_expired_id,tf_expired_id);
                break;

            case R.id.btn_send:

                if (validateForm()) {
//                        processInputPipeline();
                    Toast.makeText(IsianCifKmgActivity.this, "Menyimpan data", Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }

    //method ketika memilih salah satu opsi di dialog fragment, tidak bisa di simplifikasi , eh belum bisa, belum cukup ilmunya :')
    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase("institusi pembayar gaji pensiun")) {
            et_institusi_pembayar_pensiun.setText(data.getName());
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this, tf_institusi_pembayar_pensiun);
            val_institusi_pembayar_gaji_pensiunan = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this,tf_institusi_pembayar_pensiun);
        }
        else if (title.equalsIgnoreCase("tujuan penggunaan kmg")) {
            et_tujuan_penggunaan.setText(data.getName());
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this, tf_tujuan_penggunaan);
            val_tujuan_penggunaan = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this,tf_tujuan_penggunaan);
        }
        else if (title.equalsIgnoreCase("jenis kelamin")) {
            et_jenis_kelamin.setText(data.getName());
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this, tf_jenis_kelamin);
            val_jenis_kelamin = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this,tf_jenis_kelamin);
        }
        else if (title.equalsIgnoreCase("jenis pekerjaan")) {
            et_jenis_pekerjaan.setText(data.getName());
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this, tf_jenis_pekerjaan);
            val_jenis_pekerjaan = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this,tf_jenis_pekerjaan);
        }

        else if (title.equalsIgnoreCase("kategori calon nasabah pensiunan")) {
            et_kategori_calon_nasabah_pensiunan.setText(data.getName());
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this, tf_kategori_calon_nasabah_pensiunan);
            val_kategori_jenis_pekerjaan = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this,tf_kategori_calon_nasabah_pensiunan);
        }

        else if (title.equalsIgnoreCase("embp")) {
            et_embp.setText(data.getName());
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this, tf_embp);
            val_embp = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this,tf_kategori_calon_nasabah_pensiunan);
        }

        else if (title.equalsIgnoreCase("referal dari")) {
            et_referal_dari.setText(data.getName());
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this, tf_referal_dari);
            val_referal_dari = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this,tf_referal_dari);
        }

        else if (title.equalsIgnoreCase("referal dari")) {
            et_referal_dari.setText(data.getName());
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this, tf_referal_dari);
            val_referal_dari = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this,tf_referal_dari);
        }

        else if (title.equalsIgnoreCase("pendidikan terakhir")) {
            et_pendidikan_terakhir.setText(data.getName());
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this, tf_pendidikan_terakhir);
            val_pendidikan_terakhir = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this,tf_pendidikan_terakhir);
        }

        else if (title.equalsIgnoreCase("tujuan penggunaan kmg")) {
            et_tujuan_penggunaan.setText(data.getName());
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this, tf_tujuan_penggunaan);
            val_tujuan_penggunaan = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this,tf_tujuan_penggunaan);
        }


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
                                AppUtil.notiferror(IsianCifKmgActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            }
                        } else {
                            loading.setVisibility(View.GONE);
                            Error error = ParseResponseError.confirmEror(response.errorBody());
                            AppUtil.notiferror(IsianCifKmgActivity.this, findViewById(android.R.id.content), error.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ParseResponse> call, Throwable t) {
                    loading.setVisibility(View.GONE);
                    AppUtil.notiferror(IsianCifKmgActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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
                AppUtil.dynamicIconLogoChangeDropdown(IsianCifKmgActivity.this,textFieldBoxes);
                if (!tanggal_lahir.getText().toString().trim().isEmpty()) {
                    val_tanggal_lahir = formatServer.format(calendar.getTime()); //set value tanggal lahir
                }
            }
        };

        dp_tanggallahir = new DatePickerDialog(IsianCifKmgActivity.this, R.style.AppTheme_TimePickerTheme, ls_tanggallahir, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        //tambah kondisi jika yang menggunakan method adalah objek untuk editetxt tanggal lahir, maka di set ada tahun minimumnya
        if(textFieldBoxes.getLabelText().substring(0,textFieldBoxes.getLabelText().length()-2).equalsIgnoreCase("tanggal lahir")||textFieldBoxes.getLabelText().substring(0,textFieldBoxes.getLabelText().length()-2).equalsIgnoreCase("tanggal lahir pasangan")){
            dp_tanggallahir.getDatePicker().setMinDate(calendarMinDate.getTimeInMillis());
            dp_tanggallahir.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        }

        dp_tanggallahir.show();
    }

    private boolean validateForm() {

        //BELOM DIVALIDASI YAAA, BELOM JELAS NICH APA AAJA YANG DI VALIDASI

        //reset nilai jumlah validasi
        jumlahValidasi = 0;

        //else dan if buat validasi seluruh field dengan menggunakan 1 method
//        subValidate(et_penghasilan_rerata_perbulan,tf_penghasilan_rerata_perbulan);




        if(jumlahValidasi==0){
            return true;
        }
        else{
            return false;
        }

    }

    private void subValidate(EditText editext, TextFieldBoxes textFieldBoxes) {
        if (editext.getText().toString().trim().equalsIgnoreCase("pilih") ||editext.getText().toString().trim().equalsIgnoreCase("pilih jenis tiering") || editext.getText().toString().trim().isEmpty()) {
            //agar user tau field yang error dari textfield
            textFieldBoxes.setError("Harap pilih " + textFieldBoxes.getLabelText().substring(0, textFieldBoxes.getLabelText().length() - 2), true);

            //agar user tau nama field yang error dari snackbar
            AppUtil.notiferror(IsianCifKmgActivity.this, findViewById(R.id.nested_scv), "Harap pilih " + textFieldBoxes.getLabelText().substring(0, textFieldBoxes.getLabelText().length() - 2));


        } else {
            jumlahValidasi = jumlahValidasi + 1;
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