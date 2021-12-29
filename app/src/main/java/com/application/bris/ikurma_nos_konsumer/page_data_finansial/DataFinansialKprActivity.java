package com.application.bris.ikurma_nos_konsumer.page_data_finansial;


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
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;

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

public class DataFinansialKprActivity extends AppCompatActivity implements
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
    @BindView(R.id.tf_penghasilan_rerata_perbulan)
    TextFieldBoxes tf_penghasilan_rerata_perbulan;
    @BindView(R.id.et_penghasilan_rerata_perbulan)
    EditText et_penghasilan_rerata_perbulan;

    @BindView(R.id.tf_penghasilan_lainnya)
    TextFieldBoxes tf_penghasilan_lainnya;
    @BindView(R.id.et_penghasilan_lainnya)
    EditText et_penghasilan_lainnya;

    @BindView(R.id.tf_penghasilan_suami_istri)
    TextFieldBoxes tf_penghasilan_suami_istri;
    @BindView(R.id.et_penghasilan_suami_istri)
    EditText et_penghasilan_suami_istri;

    @BindView(R.id.tf_total_penghasilan)
    TextFieldBoxes tf_total_penghasilan;
    @BindView(R.id.et_total_penghasilan)
    EditText et_total_penghasilan;

    @BindView(R.id.tf_penghasilan_bersih_sebelum_angsuran)
    TextFieldBoxes tf_penghasilan_bersih_sebelum_angsuran;
    @BindView(R.id.et_penghasilan_bersih_sebelum_angsuran)
    EditText et_penghasilan_bersih_sebelum_angsuran;

    @BindView(R.id.tf_angsuran_pinjaman_eksisting_1)
    TextFieldBoxes tf_angsuran_pinjaman_eksisting_1;
    @BindView(R.id.et_angsuran_pinjaman_eksisting_1)
    EditText et_angsuran_pinjaman_eksisting_1;

    @BindView(R.id.tf_penghasilan_bersih_setelah_angsuran)
    TextFieldBoxes tf_penghasilan_bersih_setelah_angsuran;
    @BindView(R.id.et_penghasilan_bersih_setelah_angsuran)
    EditText et_penghasilan_bersih_setelah_angsuran;

    @BindView(R.id.tf_maksimal_angsuran)
    TextFieldBoxes tf_maksimal_angsuran;
    @BindView(R.id.et_maksimal_angsuran)
    EditText et_maksimal_angsuran;

    @BindView(R.id.tf_angsuran_pinjaman_eksisting_2)
    TextFieldBoxes tf_angsuran_pinjaman_eksisting_2;
    @BindView(R.id.et_angsuran_pinjaman_eksisting_2)
    EditText et_angsuran_pinjaman_eksisting_2;

    @BindView(R.id.tf_kelonggaran_angsuran_pembiayaan)
    TextFieldBoxes tf_kelonggaran_angsuran_pembiayaan;
    @BindView(R.id.et_kelonggaran_angsuran_pembiayaan)
    EditText et_kelonggaran_angsuran_pembiayaan;

    @BindView(R.id.tf_margin_pertahun)
    TextFieldBoxes tf_margin_pertahun;
    @BindView(R.id.et_margin_pertahun)
    EditText et_margin_pertahun;

    @BindView(R.id.tf_margin_perbulan)
    TextFieldBoxes tf_margin_perbulan;
    @BindView(R.id.et_margin_perbulan)
    EditText et_margin_perbulan;

    @BindView(R.id.tf_jangka_waktu)
    TextFieldBoxes tf_jangka_waktu;
    @BindView(R.id.et_jangka_waktu)
    EditText et_jangka_waktu;

    @BindView(R.id.tf_rpc_1)
    TextFieldBoxes tf_rpc_1;
    @BindView(R.id.et_rpc_1)
    EditText et_rpc_1;

    @BindView(R.id.tf_rpc_2)
    TextFieldBoxes tf_rpc_2;
    @BindView(R.id.et_rpc_2)
    EditText et_rpc_2;

    @BindView(R.id.tf_maksimum_plafon)
    TextFieldBoxes tf_maksimum_plafon;
    @BindView(R.id.et_maksimum_plafon)
    EditText et_maksimum_plafon;

    @BindView(R.id.tf_harga_perolehan_rumah)
    TextFieldBoxes tf_harga_perolehan_rumah;
    @BindView(R.id.et_harga_perolehan_rumah)
    EditText et_harga_perolehan_rumah;

    @BindView(R.id.tf_jumlah_permohonan_pembiayaan)
    TextFieldBoxes tf_jumlah_permohonan_pembiayaan;
    @BindView(R.id.et_jumlah_permohonan_pembiayaan)
    EditText et_jumlah_permohonan_pembiayaan;

    @BindView(R.id.tf_jumlah_plafon_pembiayaan_diusulkan)
    TextFieldBoxes tf_jumlah_plafon_pembiayaan_diusulkan;
    @BindView(R.id.et_jumlah_plafon_pembiayaan_diusulkan)
    EditText et_jumlah_plafon_pembiayaan_diusulkan;

    @BindView(R.id.tf_jenis_tiering)
    TextFieldBoxes tf_jenis_tiering;
    @BindView(R.id.et_jenis_tiering)
    EditText et_jenis_tiering;

    //Data Remarks Ao
    @BindView(R.id.tf_remark_ao)
    TextFieldBoxes tf_remark_ao;
    @BindView(R.id.et_remark_ao)
    EditText et_remark_ao;

    @BindView(R.id.tf_fasilitas_pembiayaan_beragun_properti)
    TextFieldBoxes tf_fasilitas_pembiayaan_beragun_properti;
    @BindView(R.id.et_fasilitas_pembiayaan_beragun_properti)
    EditText et_fasilitas_pembiayaan_beragun_properti;

    @BindView(R.id.tf_luas_bangunan)
    TextFieldBoxes tf_luas_bangunan;
    @BindView(R.id.et_luas_bangunan)
    EditText et_luas_bangunan;

    @BindView(R.id.tf_maksimum_ltv)
    TextFieldBoxes tf_maksimum_ltv;
    @BindView(R.id.et_maksimum_ltv)
    EditText et_maksimum_ltv;

    @BindView(R.id.tf_npw_appraisal)
    TextFieldBoxes tf_npw_appraisal;
    @BindView(R.id.et_npw_appraisal)
    EditText et_npw_appraisal;

    @BindView(R.id.tf_maksimum_plafon_dari_ltv)
    TextFieldBoxes tf_maksimum_plafon_dari_ltv;
    @BindView(R.id.et_maksimum_plafon_dari_ltv)
    EditText et_maksimum_plafon_dari_ltv;


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
    private static String val_jenis_tiering = "";


    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konsumer_data_finansial_kpr);

//        //push up when keyboard shown
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ButterKnife.bind(this);

        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
//        setGps();
        backgroundStatusBar();
//        checkCollapse();
        tv_page_title.setText("Form Data Finansial");
        btn_back.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        onclickSelectDialog();


        //disabling total penghasilan field
        et_total_penghasilan.setFocusable(false);

        et_penghasilan_rerata_perbulan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_penghasilan_rerata_perbulan));
        et_penghasilan_lainnya.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_penghasilan_lainnya));
        et_penghasilan_suami_istri.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_penghasilan_suami_istri));
        et_total_penghasilan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_total_penghasilan));

        et_penghasilan_bersih_sebelum_angsuran.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_penghasilan_bersih_sebelum_angsuran));
        et_penghasilan_bersih_setelah_angsuran.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_penghasilan_bersih_setelah_angsuran));
        et_angsuran_pinjaman_eksisting_1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_angsuran_pinjaman_eksisting_1));
        et_angsuran_pinjaman_eksisting_2.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_angsuran_pinjaman_eksisting_2));
        et_maksimal_angsuran.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_maksimal_angsuran));
        et_kelonggaran_angsuran_pembiayaan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_kelonggaran_angsuran_pembiayaan));
        et_npw_appraisal.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_npw_appraisal));
        et_maksimum_plafon_dari_ltv.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_maksimum_plafon_dari_ltv));

        et_maksimum_plafon.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_maksimum_plafon));
        et_jumlah_permohonan_pembiayaan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_jumlah_permohonan_pembiayaan));
        et_jumlah_plafon_pembiayaan_diusulkan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_jumlah_plafon_pembiayaan_diusulkan));
        et_harga_perolehan_rumah.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_harga_perolehan_rumah));



        //for dynamic icon in textfieldboxes
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_penghasilan_rerata_perbulan,tf_penghasilan_rerata_perbulan);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_penghasilan_lainnya,tf_penghasilan_lainnya);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_penghasilan_suami_istri,tf_penghasilan_suami_istri);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_total_penghasilan,tf_total_penghasilan);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_penghasilan_bersih_sebelum_angsuran,tf_penghasilan_bersih_sebelum_angsuran);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_angsuran_pinjaman_eksisting_1,tf_angsuran_pinjaman_eksisting_1);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_penghasilan_bersih_setelah_angsuran,tf_penghasilan_bersih_setelah_angsuran);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_maksimal_angsuran,tf_maksimal_angsuran);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_angsuran_pinjaman_eksisting_2,tf_angsuran_pinjaman_eksisting_2);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_kelonggaran_angsuran_pembiayaan,tf_kelonggaran_angsuran_pembiayaan);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_margin_pertahun,tf_margin_pertahun);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_margin_perbulan,tf_margin_perbulan);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_jangka_waktu,tf_jangka_waktu);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_rpc_1,tf_rpc_1);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_rpc_1,tf_rpc_2);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_maksimum_plafon,tf_maksimum_plafon);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_harga_perolehan_rumah,tf_harga_perolehan_rumah);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_jumlah_permohonan_pembiayaan,tf_jumlah_permohonan_pembiayaan);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_remark_ao,tf_remark_ao);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_fasilitas_pembiayaan_beragun_properti,tf_fasilitas_pembiayaan_beragun_properti);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_luas_bangunan,tf_luas_bangunan);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_maksimum_ltv,tf_maksimum_ltv);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_npw_appraisal,tf_npw_appraisal);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this,et_maksimum_plafon_dari_ltv,tf_maksimum_plafon_dari_ltv);



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
       //hanya ada satu field yang jenis dropown
        subSelectDialog(et_jenis_tiering, tf_jenis_tiering);

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

            case R.id.btn_send:

                if (validateForm()) {
//                        processInputPipeline();
                    Toast.makeText(DataFinansialKprActivity.this, "Menyimpan data", Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }

    //method ketika memilih salah satu opsi di dialog fragment, tidak bisa di simplifikasi , eh belum bisa, belum cukup ilmunya :')
    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase("jenis tiering")) {
            et_jenis_tiering.setText(data.getName());
            AppUtil.dynamicIconLogoChangeDropdown(DataFinansialKprActivity.this, tf_jenis_tiering);
            val_jenis_tiering = data.getValue(); //set value berdasarkan pilihan di dialog fragment
            AppUtil.dynamicIconLogoChangeDropdown(DataFinansialKprActivity.this,tf_jenis_tiering);
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
                                AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            }
                        } else {
                            loading.setVisibility(View.GONE);
                            Error error = ParseResponseError.confirmEror(response.errorBody());
                            AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), error.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ParseResponse> call, Throwable t) {
                    loading.setVisibility(View.GONE);
                    AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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
        jumlahValidasi = 0;

        //else dan if buat validasi seluruh field dengan menggunakan 1 method
        subValidate(et_penghasilan_rerata_perbulan,tf_penghasilan_rerata_perbulan);
        subValidate(et_penghasilan_lainnya,tf_penghasilan_lainnya);
        subValidate(et_penghasilan_suami_istri,tf_penghasilan_suami_istri);

        subValidate(et_penghasilan_bersih_sebelum_angsuran,tf_penghasilan_bersih_sebelum_angsuran);
        subValidate(et_penghasilan_bersih_setelah_angsuran,tf_penghasilan_bersih_setelah_angsuran);
        subValidate(et_angsuran_pinjaman_eksisting_1,tf_angsuran_pinjaman_eksisting_1);
        subValidate(et_angsuran_pinjaman_eksisting_2,tf_angsuran_pinjaman_eksisting_2);
        subValidate(et_maksimal_angsuran,tf_maksimal_angsuran);
        subValidate(et_kelonggaran_angsuran_pembiayaan,tf_kelonggaran_angsuran_pembiayaan);
        subValidate(et_margin_pertahun,tf_margin_pertahun);
        subValidate(et_margin_perbulan,tf_margin_perbulan);
        subValidate(et_jangka_waktu,tf_jangka_waktu);
        subValidate(et_rpc_1,tf_rpc_1);
        subValidate(et_rpc_2,tf_rpc_2);
        subValidate(et_maksimum_plafon,tf_maksimum_plafon);
        subValidate(et_harga_perolehan_rumah,tf_harga_perolehan_rumah);
        subValidate(et_jumlah_permohonan_pembiayaan,tf_jumlah_permohonan_pembiayaan);
        subValidate(et_jumlah_plafon_pembiayaan_diusulkan,tf_jumlah_plafon_pembiayaan_diusulkan);
        subValidate(et_jenis_tiering,tf_jenis_tiering);
        subValidate(et_remark_ao,tf_remark_ao);
        subValidate(et_fasilitas_pembiayaan_beragun_properti,tf_fasilitas_pembiayaan_beragun_properti);
        subValidate(et_luas_bangunan,tf_luas_bangunan);
        subValidate(et_maksimum_ltv,tf_maksimum_ltv);
        subValidate(et_npw_appraisal,tf_npw_appraisal);
        subValidate(et_maksimum_plafon_dari_ltv,tf_maksimum_plafon_dari_ltv);



        if(jumlahValidasi==25){
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
            AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(R.id.nested_scv), "Harap pilih " + textFieldBoxes.getLabelText().substring(0, textFieldBoxes.getLabelText().length() - 2));


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