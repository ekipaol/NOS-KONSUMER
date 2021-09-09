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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanKendaraan;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.ImageHandler;
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
 * A simple {@link Fragment} subclass.
 */

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunanKendaraan4 extends Fragment implements Step, CameraListener, View.OnClickListener {

    @BindView(R.id.tf_nama_informan)
    TextFieldBoxes tf_nama_informan;
    @BindView(R.id.et_nama_informan)
    EditText et_nama_informan;

    @BindView(R.id.tf_alamat_informan)
    TextFieldBoxes tf_alamat_informan;
    @BindView(R.id.et_alamat_informan)
    EditText et_alamat_informan;

    @BindView(R.id.tf_telp_informan)
    TextFieldBoxes tf_telp_informan;
    @BindView(R.id.et_telp_informan)
    EditText et_telp_informan;

    @BindView(R.id.tf_keterangan)
    TextFieldBoxes tf_keterangan;
    @BindView(R.id.et_keterangan)
    EditText et_keterangan;

    @BindView(R.id.img_agunan_1)
    RoundedImageView img_agunan_1;
    @BindView(R.id.btn_upload_agunan_1)
    ImageView btn_upload_agunan_1;

    @BindView(R.id.img_agunan_2)
    RoundedImageView img_agunan_2;
    @BindView(R.id.btn_upload_agunan_2)
    ImageView btn_upload_agunan_2;

    @BindView(R.id.img_agunan_3)
    RoundedImageView img_agunan_3;
    @BindView(R.id.btn_upload_agunan_3)
    ImageView btn_upload_agunan_3;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    private AgunanKendaraan dataAgunan;
    private String idAgunan;

    public static String val_NamaInforman = "";
    public static String val_AlamatInforman = "";
    public static String val_TelpInforman = "";
    public static String val_Keterangan = "";

    private int val_ImgAgunan1 = 0;
    private int val_ImgAgunan2 = 0;
    private int val_ImgAgunan3 = 0;

    private Uri uriPhotoAgunan1, uriPhotoAgunan2, uriPhotoAgunan3;
    private Bitmap bitmapPhotoAgunan1, bitmapPhotoAgunan2, bitmapPhotoAgunan3, loadedPicture;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    private String isSelectPhoto = "";
    private final int TAKE_PICTURE_AGUNAN1 = 1;
    private final int TAKE_PICTURE_AGUNAN2 = 2;
    private final int TAKE_PICTURE_AGUNAN3 = 3;

    private final int PICK_PICTURE_AGUNAN1 = 11;
    private final int PICK_PICTURE_AGUNAN2 = 22;
    private final int PICK_PICTURE_AGUNAN3 = 33;

    private Realm realm;

    public FragmentAgunanKendaraan4() {
    }

    public FragmentAgunanKendaraan4(String midAgunan, AgunanKendaraan magunanData) {
        dataAgunan = magunanData;
        idAgunan = midAgunan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_kendaraan_4, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());
        if (!idAgunan.equalsIgnoreCase("0")) {
            setData();
        }
        img_agunan_1.setOnClickListener(this);
        img_agunan_2.setOnClickListener(this);
        img_agunan_3.setOnClickListener(this);
        btn_upload_agunan_1.setOnClickListener(this);
        btn_upload_agunan_2.setOnClickListener(this);
        btn_upload_agunan_3.setOnClickListener(this);
        return view;
    }

    private void setData() {
        try {
            et_nama_informan.setText(dataAgunan.getNamaPemberiInfo1());
            et_alamat_informan.setText(dataAgunan.getAlamatPemberiInfo1());
            et_telp_informan.setText(dataAgunan.getNoTelpPemberiInfo1());
            et_keterangan.setText(dataAgunan.getKeterangan());

            bitmapPhotoAgunan1 = setLoadImage(img_agunan_1, dataAgunan.getIdPhotoKDUtama());
            bitmapPhotoAgunan2 = setLoadImage(img_agunan_2, dataAgunan.getIdPhotoKDInterior());
            bitmapPhotoAgunan3 = setLoadImage(img_agunan_3, dataAgunan.getIdPhotoKDMesin());

            val_ImgAgunan1 = dataAgunan.getIdPhotoKDUtama();
            val_ImgAgunan2 = dataAgunan.getIdPhotoKDInterior();
            val_ImgAgunan3 = dataAgunan.getIdPhotoKDMesin();
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
                .diskCacheStrategy(DiskCacheStrategy.ALL)
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
        if (bitmapPhotoAgunan1 != null){
            img_agunan_1.setImageBitmap(bitmapPhotoAgunan1);
        }
        if (bitmapPhotoAgunan2 != null){
            img_agunan_2.setImageBitmap(bitmapPhotoAgunan2);
        }
        if (bitmapPhotoAgunan3 != null){
            img_agunan_3.setImageBitmap(bitmapPhotoAgunan3);
        }
    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notifwarning(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }

    private VerificationError validateForm() {
        if (et_nama_informan.getText().toString().isEmpty() || et_nama_informan.getText().toString().equalsIgnoreCase("")) {
            tf_nama_informan.setError("Format " + tf_nama_informan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_nama_informan.getLabelText() + " " + getString(R.string.title_validate_field));
        }
        else if (et_alamat_informan.getText().toString().isEmpty() || et_alamat_informan.getText().toString().equalsIgnoreCase("")) {
            tf_alamat_informan.setError("Format " + tf_alamat_informan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_alamat_informan.getLabelText() + " " + getString(R.string.title_validate_field));
        }
        else if (et_telp_informan.getText().toString().isEmpty() || et_telp_informan.getText().toString().equalsIgnoreCase("")) {
            tf_telp_informan.setError("Format " + tf_telp_informan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_telp_informan.getLabelText() + " " + getString(R.string.title_validate_field));
        }
        else if (et_keterangan.getText().toString().isEmpty() || et_keterangan.getText().toString().equalsIgnoreCase("")) {
            tf_keterangan.setError("Format " + tf_keterangan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_keterangan.getLabelText() + " " + getString(R.string.title_validate_field));
        }
        else if(val_ImgAgunan1 == 0){
            return new VerificationError("Silahkan pilih / ambil Foto Agunan 1");
        }
        else if(val_ImgAgunan2 == 0){
            return new VerificationError("Silahkan pilih / ambil Foto Agunan 2");
        }
        else if(val_ImgAgunan3 == 0){
            return new VerificationError("Silahkan pilih / ambil Foto Agunan 3");
        }
        else {
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner() {
        try {
            val_NamaInforman = (et_nama_informan.getText().toString().trim());
            val_AlamatInforman = (et_alamat_informan.getText().toString().trim());
            val_TelpInforman = (et_telp_informan.getText().toString().trim());
            val_Keterangan = (et_keterangan.getText().toString().trim());

            final AgunanKendaraanPojo d = new AgunanKendaraanPojo();
            d.setNamaPemberiInfo1(val_NamaInforman);
            d.setAlamatPemberiInfo1(val_AlamatInforman);
            d.setNoTelpPemberiInfo1(val_TelpInforman);
            d.setKeterangan(val_Keterangan);
            d.setIdKendaraan(val_ImgAgunan1);
            d.setIdKendaraan_Interior(val_ImgAgunan2);
            d.setIdKendaraan_Mesin(val_ImgAgunan3);

            AgunanKendaraanPojo dataR = realm.where(AgunanKendaraanPojo.class).equalTo("uuid", AgunanKendaraanInputActivity.UUIDR).findFirst();

            d.setTipeKendaraan(dataR.getTipeKendaraan());
            d.setMerkKendaraan(dataR.getMerkKendaraan());
            d.setMobiljepang(dataR.getMobiljepang());
            d.setDayaAngkut(dataR.getDayaAngkut());
            d.setKapasitas(dataR.getKapasitas());
            d.setCheckSamsat(dataR.getCheckSamsat());
            d.setDenganSiapa(dataR.getDenganSiapa());
            d.setNoTelpon(dataR.getNoTelpon());
            d.setHasil(dataR.getHasil());
            d.setNilaiMarket(dataR.getNilaiMarket());
            d.setNilaiTaksasi(dataR.getNilaiTaksasi());
            d.setHubungan(dataR.getHubungan());
            d.setNoFaktur(dataR.getNoFaktur());
            d.setNoMesin(dataR.getNoMesin());
            d.setBuktiGesekMesin(dataR.getBuktiGesekMesin());
            d.setNoRangka(dataR.getNoRangka());
            d.setBuktiGesekRangka(dataR.getBuktiGesekRangka());
            d.setNoPolisi(dataR.getNoPolisi());
            d.setJenisPlat(dataR.getJenisPlat());
            d.setNoBKPB(dataR.getNoBKPB());
            d.setNoSTNK(dataR.getNoSTNK());
            d.setWarna(dataR.getWarna());
            d.setTahunPembuatan(dataR.getTahunPembuatan());
            d.setIdKendaraan_Dok(dataR.getIdKendaraan_Dok());
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

    @Override
    public void onSelectMenuCamera(String idMenu) {
        if (isSelectPhoto.equalsIgnoreCase("agunan1")) {
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_AGUNAN1);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_AGUNAN1);
                    break;
            }
        }

        else if (isSelectPhoto.equalsIgnoreCase("agunan2")) {
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_AGUNAN2);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_AGUNAN2);
                    break;
            }
        }

        else if (isSelectPhoto.equalsIgnoreCase("agunan3")) {
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_AGUNAN3);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_AGUNAN3);
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

                case TAKE_PICTURE_AGUNAN1:
                case PICK_PICTURE_AGUNAN1:
                    setDataImage(uriPhotoAgunan1, bitmapPhotoAgunan1, img_agunan_1, data);
                    break;
                case TAKE_PICTURE_AGUNAN2:
                case PICK_PICTURE_AGUNAN2:
                    setDataImage(uriPhotoAgunan2, bitmapPhotoAgunan2, img_agunan_2, data);
                    break;
                case TAKE_PICTURE_AGUNAN3:
                case PICK_PICTURE_AGUNAN3:
                    setDataImage(uriPhotoAgunan3, bitmapPhotoAgunan3, img_agunan_3, data);
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

                if(iv == img_agunan_1){
                    bitmapPhotoAgunan1 = bitmap;
                }
                if(iv == img_agunan_2){
                    bitmapPhotoAgunan2 = bitmap;
                }
                if(iv == img_agunan_3){
                    bitmapPhotoAgunan3 = bitmap;
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
                                case "agunan1":
                                    val_ImgAgunan1 = idFoto;
                                    break;
                                case "agunan2":
                                    val_ImgAgunan2 = idFoto;
                                    break;
                                case "agunan3":
                                    val_ImgAgunan3 = idFoto;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_upload_agunan_1:
                isSelectPhoto = "agunan1";
                BSBottomCamera.displayWithTitle(getFragmentManager(), this, "Foto Agunan");
                break;

            case R.id.btn_upload_agunan_2:
                isSelectPhoto = "agunan2";
                BSBottomCamera.displayWithTitle(getFragmentManager(), this, "Foto Agunan");
                break;

            case R.id.btn_upload_agunan_3:
                isSelectPhoto = "agunan3";
                BSBottomCamera.displayWithTitle(getFragmentManager(), this, "Foto Agunan");
                break;

            case R.id.img_agunan_1:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_agunan_1.getDrawable()).getSourceBitmap());
                break;
            case R.id.img_agunan_2:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_agunan_2.getDrawable()).getSourceBitmap());
                break;
            case R.id.img_agunan_3:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_agunan_3.getDrawable()).getSourceBitmap());
                break;
        }
    }
}




