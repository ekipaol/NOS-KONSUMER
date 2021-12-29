package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqAgunanData;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inputAgunanDeposito;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanDeposito;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.hotprospek;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.ImageHandler;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

public class AgunanDepositoInputActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener, KeyValueListener, CameraListener, TextWatcher, ConfirmListener{

    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.tf_tanggalpemeriksaan)
    TextFieldBoxes tf_tanggalpemeriksaan;
    @BindView(R.id.et_tanggalpemeriksaan)
    EditText et_tanggalpemeriksaan;
    @BindView(R.id.tf_jenisdeposito)
    TextFieldBoxes tf_jenisdeposito;
    @BindView(R.id.et_jenisdeposito)
    EditText et_jenisdeposito;
    @BindView(R.id.tf_namapemilik)
    TextFieldBoxes tf_namapemilik;
    @BindView(R.id.et_namapemilik)
    EditText et_namapemilik;
    @BindView(R.id.tf_alamatpemilik)
    TextFieldBoxes tf_alamatpemilik;
    @BindView(R.id.et_alamatpemilik)
    EditText et_alamatpemilik;

    @BindView(R.id.tf_hubungandengannasabah)
    TextFieldBoxes tf_hubungandengannasabah;
    @BindView(R.id.et_hubungandengannasabah)
    EditText et_hubungandengannasabah;
    @BindView(R.id.tf_nomorbilyet)
    TextFieldBoxes tf_nomorbilyet;
    @BindView(R.id.et_nomorbilyet)
    EditText et_nomorbilyet;
    @BindView(R.id.tf_bankpenerbit)
    TextFieldBoxes tf_bankpenerbit;
    @BindView(R.id.et_bankpenerbit)
    EditText et_bankpenerbit;
    @BindView(R.id.tf_tanggalpenerbitan)
    TextFieldBoxes tf_tanggalpenerbitan;
    @BindView(R.id.et_tanggalpenerbitan)
    EditText et_tanggalpenerbitan;
    @BindView(R.id.tf_tanggaljatuhtempo)
    TextFieldBoxes tf_tanggaljatuhtempo;
    @BindView(R.id.et_tanggaljatuhtempo)
    EditText et_tanggaljatuhtempo;
    @BindView(R.id.tf_aro)
    TextFieldBoxes tf_aro;
    @BindView(R.id.et_aro)
    EditText et_aro;
    @BindView(R.id.tf_nilainominal)
    TextFieldBoxes tf_nilainominal;
    @BindView(R.id.et_nilainominal)
    EditText et_nilainominal;
    @BindView(R.id.tf_nilailikuidasi)
    TextFieldBoxes tf_nilailikuidasi;
    @BindView(R.id.et_nilailikuidasi)
    EditText et_nilailikuidasi;
    @BindView(R.id.et_keterangan)
    EditText et_keterangan;
    @BindView(R.id.iv_foto)
    ImageView iv_foto;
    @BindView(R.id.btn_foto)
    ImageView btn_foto;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    private Calendar calTanggalPemeriksaan;
    private Calendar calTanggalPenerbitan;
    private Calendar calTanggalJatuhTempo;
    private DatePickerDialog dpTanggalPemeriksaan;
    private DatePickerDialog dpTanggalPenerbitan;
    private DatePickerDialog dpTanggalJatuhTempo;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);


    private String idAgunan;
    private String idApl;
    private String idCif;
    private String typeProduk;
    private String loanType;

    private AgunanDeposito dataAgunan;
    private List<AgunanDeposito> listDataAgunan;

    private String idAgunanResponse;

    private String val_tanggalPemeriksaan;
    private String val_jenisDeposito;
    private String val_namaPemilik;
    private String val_alamatPemilik;
    private String val_hubunganDenganNasabah;
    private String val_nomorBilyet;
    private String val_bankPenerbit;
    private String val_tanggalPenerbitan;
    private String val_tanggalJatuhTempo;
    private String val_aro;
    private String val_nilaiNominal;
    private String val_nilaiTaksasi;
    private String val_keterangan;
    private int val_idPhoto = 0;

    private final int TAKE_PICTURE= 1;
    private final int PICT_PICTURE = 0;


    private String isSelectPhoto = "";
    private Uri uri_foto;

    private Bitmap bitmap_foto;
    private Bitmap loadedPicture;


    private String dataString;
    private hotprospek data;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_agunandeposito_input);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        AppUtil.toolbarRegular(this, "Agunan Deposito");
        idAgunan = getIntent().getStringExtra("idAgunan");
        idApl = getIntent().getStringExtra("idAplikasi");
        idCif = getIntent().getStringExtra("cif");
        typeProduk = getIntent().getStringExtra("tp_produk");
        loanType = getIntent().getStringExtra("loan_type");
        backgroundStatusBar();
        if (!idAgunan.equalsIgnoreCase("0")){
            loadData();
        }
        else {
            sm_placeholder.setVisibility(View.GONE);
            ll_content.setVisibility(View.VISIBLE);
        }
        onclickSelectDialog();

    }

    private void loadData() {
        sm_placeholder.startShimmer();
        ReqAgunanData req = new ReqAgunanData(AppUtil.parseIntWithDefault(idApl, 0), AppUtil.parseIntWithDefault(idAgunan, 0), AppUtil.parseIntWithDefault(idCif, 0));

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryAgunanDeposito(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            String listDataString = response.body().getData().toString();
                            dataAgunan= gson.fromJson(listDataString, AgunanDeposito.class);

                            et_tanggalpemeriksaan.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTglPemeriksaan(), "ddMMyyyy", "dd-MM-yyyy"));
                            et_jenisdeposito.setText(dataAgunan.getJenisDeposito());
                            et_namapemilik.setText(dataAgunan.getNamaPemilik());
                            et_alamatpemilik.setText(dataAgunan.getAlamatPemilik());
                            et_hubungandengannasabah.setText(dataAgunan.getHubungan());
                            et_nomorbilyet.setText(dataAgunan.getNoBilyet());
                            et_bankpenerbit.setText(dataAgunan.getBankPenerbit());
                            et_tanggalpenerbitan.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTanggalPenerbitan(), "ddMMyyyy", "dd-MM-yyyy"));
                            et_tanggaljatuhtempo.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTanggalJatuhTempo(), "ddMMyyyy", "dd-MM-yyyy"));
                            et_aro.setText(dataAgunan.getIsAro());
                            et_nilainominal.setText(dataAgunan.getNilaiNominal());
                            et_nilailikuidasi.setText(dataAgunan.getNilaiTaksasi());
                            et_keterangan.setText(dataAgunan.getKeterangan());
                            bitmap_foto = setLoadImage(iv_foto, dataAgunan.getIdPhoto());
                            val_idPhoto = dataAgunan.getIdPhoto();
                            val_aro = dataAgunan.getIsAro();
                        }
                        else{
                            AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 2000);
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 2000);
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }

    private void onclickSelectDialog(){
        iv_foto.setOnClickListener(this);
        btn_foto.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        et_nilainominal.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilainominal));
        et_nilailikuidasi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilailikuidasi));
        et_nilainominal.addTextChangedListener(this);

        // TANGGAL PEMERIKSAAN
        et_tanggalpemeriksaan.setFocusable(false);
        et_tanggalpemeriksaan.setInputType(InputType.TYPE_NULL);
        et_tanggalpemeriksaan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                dpTanggalPemeriksaan();
            }
        });
        tf_tanggalpemeriksaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggalPemeriksaan();
            }
        });
        tf_tanggalpemeriksaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggalPemeriksaan();
            }
        });

        //JENIS AGUNAN
        et_jenisdeposito.setFocusable(false);
        et_jenisdeposito.setInputType(InputType.TYPE_NULL);
        et_jenisdeposito.setOnFocusChangeListener(this);
        tf_jenisdeposito.setOnClickListener(this);
        tf_jenisdeposito.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_jenisdeposito.getLabelText().toString().trim());
            }
        });


        //HUBUNGAN PEMEGANG HAK
        et_hubungandengannasabah.setFocusable(false);
        et_hubungandengannasabah.setInputType(InputType.TYPE_NULL);
        et_hubungandengannasabah.setOnFocusChangeListener(this);
        tf_hubungandengannasabah.setOnClickListener(this);
        tf_hubungandengannasabah.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_hubungandengannasabah.getLabelText().toString().trim());
            }
        });

        // TANGGAL PENERBITAN
        et_tanggalpenerbitan.setFocusable(false);
        et_tanggalpenerbitan.setInputType(InputType.TYPE_NULL);
        et_tanggalpenerbitan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                dpTanggalPenerbitan();
            }
        });
        tf_tanggalpenerbitan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggalPenerbitan();
            }
        });
        tf_tanggalpenerbitan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggalPenerbitan();
            }
        });

        // TANGGAL JATUH TEMPO
        et_tanggaljatuhtempo.setFocusable(false);
        et_tanggaljatuhtempo.setInputType(InputType.TYPE_NULL);
        et_tanggaljatuhtempo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                dpTanggalJatuhTempo();
            }
        });
        tf_tanggaljatuhtempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggalJatuhTempo();
            }
        });
        tf_tanggaljatuhtempo.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggalJatuhTempo();
            }
        });

        //ARO
        et_aro.setFocusable(false);
        et_aro.setInputType(InputType.TYPE_NULL);
        et_aro.setOnFocusChangeListener(this);
        tf_aro.setOnClickListener(this);
        tf_aro.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_aro.getLabelText().toString().trim());
            }
        });
    }


    private void dpTanggalPemeriksaan(){
        calTanggalPemeriksaan = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ls_tanggalLahir = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calTanggalPemeriksaan.set(Calendar.YEAR, year);
                calTanggalPemeriksaan.set(Calendar.MONTH, month);
                calTanggalPemeriksaan.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calTanggalKunjunganString = dateClient.format(calTanggalPemeriksaan.getTime());
                et_tanggalpemeriksaan.setText(calTanggalKunjunganString);
            }
        };

        dpTanggalPemeriksaan = new DatePickerDialog(this, R.style.AppTheme_TimePickerTheme, ls_tanggalLahir, calTanggalPemeriksaan.get(Calendar.YEAR),
                calTanggalPemeriksaan.get(Calendar.MONTH), calTanggalPemeriksaan.get(Calendar.DAY_OF_MONTH));
        dpTanggalPemeriksaan.getDatePicker().setMaxDate(calTanggalPemeriksaan.getTimeInMillis());
        dpTanggalPemeriksaan.show();
    }


    private void dpTanggalPenerbitan(){
        calTanggalPenerbitan = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ls_tanggalLahir = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calTanggalPenerbitan.set(Calendar.YEAR, year);
                calTanggalPenerbitan.set(Calendar.MONTH, month);
                calTanggalPenerbitan.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calTanggalPenerbitanString = dateClient.format(calTanggalPenerbitan.getTime());
                et_tanggalpenerbitan.setText(calTanggalPenerbitanString);
            }
        };

        dpTanggalPenerbitan = new DatePickerDialog(this, R.style.AppTheme_TimePickerTheme, ls_tanggalLahir, calTanggalPenerbitan.get(Calendar.YEAR),
                calTanggalPenerbitan.get(Calendar.MONTH), calTanggalPenerbitan.get(Calendar.DAY_OF_MONTH));
        dpTanggalPenerbitan.getDatePicker().setMaxDate(calTanggalPenerbitan.getTimeInMillis());
        dpTanggalPenerbitan.show();
    }

    private void dpTanggalJatuhTempo(){
        calTanggalJatuhTempo = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ls_tanggalLahir = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calTanggalJatuhTempo.set(Calendar.YEAR, year);
                calTanggalJatuhTempo.set(Calendar.MONTH, month);
                calTanggalJatuhTempo.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calTanggalJatuhTempoString = dateClient.format(calTanggalJatuhTempo.getTime());
                et_tanggaljatuhtempo.setText(calTanggalJatuhTempoString);
            }
        };

        dpTanggalJatuhTempo = new DatePickerDialog(this, R.style.AppTheme_TimePickerTheme, ls_tanggalLahir, calTanggalJatuhTempo.get(Calendar.YEAR),
                calTanggalJatuhTempo.get(Calendar.MONTH), calTanggalJatuhTempo.get(Calendar.DAY_OF_MONTH));
        dpTanggalJatuhTempo.getDatePicker().setMinDate(calTanggalJatuhTempo.getTimeInMillis());
        dpTanggalJatuhTempo.show();
    }


    private void openKeyValueDialog(String title){
        DialogKeyValue.display(getSupportFragmentManager(), title, this);
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }



    //TAKE PICTURE
    public void openGalery(int requestCode){
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(Intent.createChooser(it, "Select File"), requestCode);
    }

    private void openCamera(int cameraCode) {
        checkCameraPermission(cameraCode);
    }



    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static int CAMERA_CODE_FORE_PERMISSION = 0;
    public void checkCameraPermission(int cameraCode)
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            CAMERA_CODE_FORE_PERMISSION = cameraCode;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);
        } else {
            directOpenCamera(cameraCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_CAMERA_REQUEST_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                AppUtil.showToastLong(this, "Camera Permission Granted");
                directOpenCamera(CAMERA_CODE_FORE_PERMISSION);
            }
            else {
                AppUtil.showToastLong(this, "Camera Permission Denied");
            }
        }
    }

    private void directOpenCamera(int cameraCode){
        Uri outputFileUri = getCaptureImageOutputUri();
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(captureIntent, cameraCode);
    }

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                outputFileUri = FileProvider.getUriForFile(AgunanDepositoInputActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "fotoagunandeposito.png"));
            }
            else{
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "fotoagunandeposito.png"));
            }
        }
        return outputFileUri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode){
                case TAKE_PICTURE:
                case PICT_PICTURE:
                    setDataImage(uri_foto, bitmap_foto, iv_foto, data);
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

    private void initImageFileName() {
        String fileName = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date())+"_"+System.currentTimeMillis()+ ".jpg";
        appPreferences.setFotoAgunan(fileName);
    }

    private void setDataImage(Uri uri, Bitmap bitmap, ImageView iv, Intent data){
        initImageFileName();
        if (getPickImageResultUri(data) != null) {
            uri = getPickImageResultUri(data);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                bitmap = AppUtil.rotateImageIfRequired(this, bitmap, uri);
                iv.setImageBitmap(bitmap);
                bitmap_foto = bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            String filename = appPreferences.getFotoAgunan();
            ImageHandler.saveImageToCache(this, bitmap, filename);
            uploadFoto(filename);
        }
    }

    public Bitmap setLoadImage(final ImageView iv, int idFoto){
        String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFile + idFoto;
        Glide
                .with(AgunanDepositoInputActivity.this)
                .asBitmap()
                .load(url_photo)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv.setImageBitmap(resource);
                        loadedPicture = resource;
                    }
                });
        return loadedPicture;
    }

    public void uploadFoto(String filename){
        loading.setVisibility(View.VISIBLE);
        File imageFile = new File(getApplicationContext().getCacheDir(), filename);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().uploadFileOld(fileBody);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    loading.setVisibility(View.GONE);
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            int idFoto = response.body().getData().get("id").getAsInt();
                            switch (isSelectPhoto){
                                case "foto":
                                    val_idPhoto = idFoto;
                                    break;
                                default:
                                    break;
                            }
                        }
                        else{
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }


    public void sendData(){
        loading.setVisibility(View.VISIBLE);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendAgunanDeposito(getDataRequestValid());
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            CustomDialog.DialogSuccess(AgunanDepositoInputActivity.this, "Success!", response.body().getMessage(), AgunanDepositoInputActivity.this);
                            idAgunanResponse = response.body().getData().get("idAgunan").getAsString();
                        }
                        else{
                            AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private boolean validateForm(){
        if(et_tanggalpemeriksaan.getText().toString().trim().isEmpty() || et_tanggalpemeriksaan.getText().toString().trim().equalsIgnoreCase("")){
            tf_tanggalpemeriksaan.setError(tf_tanggalpemeriksaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), tf_tanggalpemeriksaan.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_jenisdeposito.getText().toString().trim().isEmpty() || et_jenisdeposito.getText().toString().trim().equalsIgnoreCase("")){
            tf_jenisdeposito.setError(tf_jenisdeposito.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), tf_jenisdeposito.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_namapemilik.getText().toString().trim().isEmpty() || et_namapemilik.getText().toString().trim().equalsIgnoreCase("")){
            tf_namapemilik.setError(tf_namapemilik.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), tf_namapemilik.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_alamatpemilik.getText().toString().trim().isEmpty() || et_alamatpemilik.getText().toString().trim().equalsIgnoreCase("")){
            tf_alamatpemilik.setError(tf_alamatpemilik.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), tf_alamatpemilik.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_hubungandengannasabah.getText().toString().trim().isEmpty() || et_hubungandengannasabah.getText().toString().trim().equalsIgnoreCase("")){
            tf_hubungandengannasabah.setError(tf_hubungandengannasabah.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), tf_hubungandengannasabah.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_nomorbilyet.getText().toString().trim().isEmpty() || et_nomorbilyet.getText().toString().trim().equalsIgnoreCase("")){
            tf_nomorbilyet.setError(tf_nomorbilyet.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), tf_nomorbilyet.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_bankpenerbit.getText().toString().trim().isEmpty() || et_bankpenerbit.getText().toString().trim().equalsIgnoreCase("")){
            tf_bankpenerbit.setError(tf_bankpenerbit.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), tf_bankpenerbit.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_tanggalpenerbitan.getText().toString().trim().isEmpty() || et_tanggalpenerbitan.getText().toString().trim().equalsIgnoreCase("")){
            tf_tanggalpenerbitan.setError(tf_tanggalpenerbitan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), tf_tanggalpenerbitan.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_tanggaljatuhtempo.getText().toString().trim().isEmpty() || et_tanggaljatuhtempo.getText().toString().trim().equalsIgnoreCase("")){
            tf_tanggaljatuhtempo.setError(tf_tanggaljatuhtempo.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), tf_tanggaljatuhtempo.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_aro.getText().toString().trim().isEmpty() || et_aro.getText().toString().trim().equalsIgnoreCase("")){
            tf_aro.setError(tf_aro.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), tf_aro.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }

        else if(et_nilainominal.getText().toString().trim().isEmpty() || et_nilainominal.getText().toString().trim().equalsIgnoreCase("")){
            tf_nilainominal.setError(tf_nilainominal.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), tf_nilainominal.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }
        else if(et_nilailikuidasi.getText().toString().trim().isEmpty() || et_nilailikuidasi.getText().toString().trim().equalsIgnoreCase("")){
            tf_nilailikuidasi.setError(tf_nilailikuidasi.getLabelText()+" "+getString(R.string.title_validate_field), true);
            AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), tf_nilailikuidasi.getLabelText()+" "+getString(R.string.title_validate_field));
            return false;
        }
        else if(et_keterangan.getText().toString().trim().isEmpty() || et_keterangan.getText().toString().trim().equalsIgnoreCase("")){
            AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), "Keterangan "+getString(R.string.title_validate_field));
            return false;
        }
        else if(val_idPhoto == 0){
            AppUtil.notiferror(AgunanDepositoInputActivity.this, findViewById(android.R.id.content), "Silahkan tambah foto Bilyet");
            return false;
        }
        return  true;
    }


    public inputAgunanDeposito getDataRequestValid(){

        val_tanggalPemeriksaan = (AppUtil.parseTanggalGeneral(et_tanggalpemeriksaan.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
        val_jenisDeposito = et_jenisdeposito.getText().toString().trim();
        val_namaPemilik = et_namapemilik.getText().toString().trim();
        val_alamatPemilik = et_alamatpemilik.getText().toString().trim();
        val_hubunganDenganNasabah = et_hubungandengannasabah.getText().toString().trim();
        val_nomorBilyet = et_nomorbilyet.getText().toString().trim();
        val_bankPenerbit = et_bankpenerbit.getText().toString().trim();
        val_tanggalPenerbitan = (AppUtil.parseTanggalGeneral(et_tanggalpenerbitan.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
        val_tanggalJatuhTempo = (AppUtil.parseTanggalGeneral(et_tanggaljatuhtempo.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
        val_aro = et_aro.getText().toString().trim();
        val_nilaiNominal = NumberTextWatcherCanNolForThousand.trimCommaOfString(et_nilainominal.getText().toString().trim());
        val_nilaiTaksasi = NumberTextWatcherCanNolForThousand.trimCommaOfString(et_nilailikuidasi.getText().toString().trim());
        val_keterangan = et_keterangan.getText().toString().trim();

        inputAgunanDeposito req = new inputAgunanDeposito(AppUtil.parseIntWithDefault(idApl, 0), AppUtil.parseIntWithDefault(idCif, 0), AppUtil.parseIntWithDefault(idAgunan, 0), AppUtil.parseIntWithDefault(typeProduk, 0),
                                    val_tanggalPemeriksaan, val_jenisDeposito, val_namaPemilik, val_alamatPemilik, val_hubunganDenganNasabah, val_nomorBilyet, val_bankPenerbit, val_tanggalPenerbitan,val_tanggalJatuhTempo, val_aro, val_nilaiNominal, val_nilaiTaksasi, val_keterangan, val_idPhoto);

        return req;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //TANGGAL PEMERIKSAAN
            case R.id.tf_tanggalpemeriksaan:
            case R.id.et_tanggalpemeriksaan:
                dpTanggalPemeriksaan();
                break;

            //JENIS DEPOSITO
            case R.id.tf_jenisdeposito:
            case R.id.et_jenisdeposito:
                openKeyValueDialog(tf_jenisdeposito.getLabelText().toString().trim());
                break;

            //HUBUNGAN PEMEGANG HAK
            case R.id.tf_hubungandengannasabah:
            case R.id.et_hubungandengannasabah:
                openKeyValueDialog(tf_hubungandengannasabah.getLabelText().toString().trim());
                break;

            //TANGGAL PENERBITAN
            case R.id.tf_tanggalpenerbitan:
            case R.id.et_tanggalpenerbitan:
                dpTanggalPenerbitan();
                break;

            //TANGGAL JATUH TEMPO
            case R.id.tf_tanggaljatuhtempo:
            case R.id.et_tanggaljatuhtempo:
                dpTanggalJatuhTempo();
                break;

            //ARO
            case R.id.tf_aro:
            case R.id.et_aro:
                openKeyValueDialog(tf_aro.getLabelText().toString().trim());
                break;

            //TAKE PICTURE
            case R.id.btn_foto:
                isSelectPhoto = "foto";
                BSBottomCamera.displayWithTitle(getSupportFragmentManager(), this, "Foto Bilyet Deposito");
                break;

            case R.id.iv_foto:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_foto.getDrawable()).getSourceBitmap());
                break;

            //SEND
            case R.id.btn_send:
                if (validateForm())
                    sendData();
                break;
        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            //TANGGAL PEMERIKSAAN
            case R.id.et_tanggalpemeriksaan:
                dpTanggalPemeriksaan();
                break;

            //JENIS AGUNAN
            case R.id.et_jenisdeposito:
                openKeyValueDialog(tf_jenisdeposito.getLabelText().toString().trim());
                break;

            //HUBUNGAN PEMEGANG HAK
            case R.id.et_hubungandengannasabah:
                openKeyValueDialog(tf_hubungandengannasabah.getLabelText().toString().trim());
                break;

            //TANGGAL PENERBITAN
            case R.id.et_tanggalpenerbitan:
                dpTanggalPenerbitan();
                break;

            //TANGGAL JATUH TEMPO
            case R.id.et_tanggaljatuhtempo:
                dpTanggalJatuhTempo();
                break;

            //ARO
            case R.id.et_aro:
                openKeyValueDialog(tf_aro.getLabelText().toString().trim());
                break;
        }
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase(tf_jenisdeposito.getLabelText().toString().trim())){
            et_jenisdeposito.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(tf_hubungandengannasabah.getLabelText().toString().trim())){
            et_hubungandengannasabah.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(tf_aro.getLabelText().toString().trim())){
            et_aro.setText(data.getName());
        }
    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        if (isSelectPhoto.equalsIgnoreCase("foto")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE);
                    break;
                case "Pick Photo":
                    openGalery(PICT_PICTURE);
                    break;
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        try{
            if (et_nilainominal.getText().toString().trim().length() > 0 || !et_nilainominal.getText().toString().isEmpty()){
                et_nilailikuidasi.setText(et_nilainominal.getText().toString().trim());
            }
        }
        catch (Exception e)
        {
            AppUtil.showToastShort(AgunanDepositoInputActivity.this, e.getMessage());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void success(boolean val) {
        if (val){
            if (idAgunan.equalsIgnoreCase("0")){
                finish();
                Intent intent = new Intent(AgunanDepositoInputActivity.this, InfoAgunanActivity.class);
                intent.putExtra("idAplikasi", idApl);
                intent.putExtra("idCif", idCif);
                intent.putExtra("idAgunan", idAgunanResponse);
                intent.putExtra("loan_type", loanType);
                intent.putExtra("tp_produk", typeProduk);
                intent.putExtra("jenisAgunan", "deposito");
                startActivity(intent);
            }
            else {
                finish();
            }
        }
    }

    @Override
    public void confirm(boolean val) {

    }
}
