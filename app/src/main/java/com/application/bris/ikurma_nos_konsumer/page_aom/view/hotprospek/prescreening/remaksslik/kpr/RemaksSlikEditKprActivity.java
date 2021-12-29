package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.prescreening.remaksslik.kpr;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inputRemaksSlik;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.baseapp.RouteApp;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.prescreening.model.ModelRemaksSlik;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.ImageHandler;
import com.application.bris.ikurma_nos_konsumer.util.KeyValue;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by idong on 17/09/2019.
 */

public class RemaksSlikEditKprActivity extends AppCompatActivity implements View.OnClickListener, CameraListener, ConfirmListener{

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.tv_bank)
    TextView tv_bank;
    @BindView(R.id.tv_plafond)
    TextView tv_plafond;
    @BindView(R.id.tv_outstanding)
    TextView tv_outstanding;
    @BindView(R.id.tv_angsuran)
    TextView tv_angsuran;
    @BindView(R.id.tv_tanggalmulai)
    TextView tv_tanggalmulai;
    @BindView(R.id.tv_tanggaljatuhtempo)
    TextView tv_tanggaljatuhtempo;
    @BindView(R.id.tv_kolektabilitas)
    TextView tv_kolektabilitas;
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.sp_remaks)
    Spinner sp_remaks;
    @BindView(R.id.btn_foto)
    ImageView btn_foto;
    @BindView(R.id.iv_foto)
    ImageView iv_foto;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.ll_bukti_lunas)
    LinearLayout ll_bukti_lunas;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String dataString = "";
    private String idType = "";
    private int position;
    private ModelRemaksSlik data;
    private ArrayAdapter remaksArr;
    private List<String> remaksData;
    private String[] remaks;
    private String isSelectPhoto = "";
    private final int TAKE_PICTURE= 1;
    private final int PICT_PICTURE = 0;
    private Uri uri_foto;
    private Bitmap bitmap_foto;
    private int val_idPhoto = 0;
    private int dataRemaksPosition = 0;
    private RouteApp routeApp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_remaksslikedit);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        routeApp = new RouteApp(this);
        Bundle extras = getIntent().getExtras();
        dataString = extras.getString("dataString");
        idType = extras.getString("idType");
        position = extras.getInt("position", 0);
        backgroundStatusBar();
        loadData();
        AppUtil.toolbarRegular(this, "Remarks Hasil SLIK");
        iv_foto.setOnClickListener(this);
        btn_foto.setOnClickListener(this);
        btn_send.setOnClickListener(this);

        sp_remaks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dataRemaksPosition = position;
                if (position == 1){
                    ll_bukti_lunas.setVisibility(View.VISIBLE);
                }
                else {
                    ll_bukti_lunas.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void backgroundStatusBar(){
        try {
            Window window = getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadData() {

        Gson gson = new Gson();
        data = gson.fromJson(dataString, ModelRemaksSlik.class);

        tv_bank.setText(data.getkODEBANK()+" - "+data.getnAMABANK());
        tv_plafond.setText(AppUtil.parseRupiah(String.valueOf(data.getpLAFOND())));
        tv_outstanding.setText(AppUtil.parseRupiah(String.valueOf(data.getoS())));
        tv_angsuran.setText(AppUtil.parseRupiah(String.valueOf(data.getaNGSURAN())));
        tv_tanggalmulai.setText(AppUtil.parseTanggalGeneral(data.gettANGGALMULAI(), "yyyyMMdd", "dd-MM-yyyy"));
        tv_tanggaljatuhtempo.setText(AppUtil.parseTanggalGeneral(data.gettANGGALJATUHTEMPO(), "yyyyMMdd", "dd-MM-yyyy"));
        tv_kolektabilitas.setText(String.valueOf(data.getkOL()));
        tv_status.setText(data.getkONDISIKET());

        if(idType.equalsIgnoreCase("pasangan")){
            remaks = getResources().getStringArray(R.array.remaks_pasangan);
        }
        else{
            remaks = getResources().getStringArray(R.array.remaks);
        }

        remaksData = new ArrayList<>(Arrays.asList(remaks));
        remaksArr = new ArrayAdapter<String>(this, R.layout.spinner_style_lefttext, remaksData);
        remaksArr.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_remaks.setAdapter(remaksArr);

        dataRemaksPosition = data.getrEMARK();
        sp_remaks.setSelection(data.getrEMARK());

        Glide
                .with(this)
                .load(UriApi.Baseurl.URL+UriApi.foto.urlFile +data.getfIDPHOTO())
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .into(iv_foto);

        val_idPhoto = data.getfIDPHOTO();

    }


    public void sendData(){
        loading.setVisibility(View.VISIBLE);

        List<ModelRemaksSlik> slikNasabah = RemaksSlikKprActivity.slikNasabah;
        List<ModelRemaksSlik> slikPasangan = RemaksSlikKprActivity.slikPasangan;
        if (idType.equalsIgnoreCase("nasabah")){
            slikNasabah.set(position, updateDate());
        }
        else{
            slikPasangan.set(position, updateDate());
        }

        inputRemaksSlik dataReq = new inputRemaksSlik();
        dataReq.setIdAplikasi(RemaksSlikKprActivity.idAplikasi);
        dataReq.setDtMemoSales(slikNasabah);
        dataReq.setDtMemoSalesPasangan(slikPasangan);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendRemaksSlikKpr(dataReq);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            CustomDialog.DialogSuccess(RemaksSlikEditKprActivity.this, "Success!", "Remarks Slik Sukses", RemaksSlikEditKprActivity.this);
                        }
                        else{
                            AppUtil.notiferror(RemaksSlikEditKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(RemaksSlikEditKprActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(RemaksSlikEditKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private boolean validateForm() {
        if (data.getkODEBANK().equalsIgnoreCase("422") && dataRemaksPosition == 3){
            AppUtil.notiferror(RemaksSlikEditKprActivity.this, findViewById(android.R.id.content), "Kode Bank 422 - BRI Syariah tidak bisa Remarks Takeover");
            return false;
        }
        else if (!data.getkODEBANK().equalsIgnoreCase("422") && dataRemaksPosition == 2){
            AppUtil.notiferror(RemaksSlikEditKprActivity.this, findViewById(android.R.id.content), "Remarks Top Up hanya untuk Kode Bank 422 - BRI Syariah");
            return false;
        }
        else if (dataRemaksPosition == 1 && val_idPhoto == 0) {
            AppUtil.notiferror(RemaksSlikEditKprActivity.this, findViewById(android.R.id.content), "Foto bukti lunas " + getString(R.string.title_validate_field));
            return false;
        }
        return  true;
    }

    public ModelRemaksSlik updateDate(){
        ModelRemaksSlik dataUpdate = new ModelRemaksSlik();
        dataUpdate.setoS(data.getoS());
        dataUpdate.setfLAGTAKEOVER(data.getfLAGTAKEOVER());
        dataUpdate.settANGGALJATUHTEMPO(data.gettANGGALJATUHTEMPO());
        dataUpdate.setkONDISI(data.getkONDISI());
        dataUpdate.setkOL(data.getkOL());
        dataUpdate.setfIDPHOTO(val_idPhoto);
        dataUpdate.setkONDISIKET(data.getkONDISIKET());
        dataUpdate.setpLAFOND(data.getpLAFOND());
        dataUpdate.settANGGALKONDISI(data.gettANGGALKONDISI());
        dataUpdate.setaNGSURAN(data.getaNGSURAN());
        dataUpdate.setrEMARKKET(KeyValue.getKeyRemaksSlik(String.valueOf(dataRemaksPosition)));
        dataUpdate.setiD(data.getiD());
        dataUpdate.setrEMARK(dataRemaksPosition);
        dataUpdate.setnAMABANK(data.getnAMABANK());
        dataUpdate.settANGGALMULAI(data.gettANGGALMULAI());
        dataUpdate.setkODEBANK(data.getkODEBANK());

        return dataUpdate;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_foto:
                isSelectPhoto = "foto";
                BSBottomCamera.displayWithTitle(getSupportFragmentManager(), this, "Foto Bukti Lunas");
                break;
            case R.id.btn_send:
                if(validateForm())
                    sendData();
                break;
            case R.id.iv_foto:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Bukti Lunas", ((RoundedDrawable)iv_foto.getDrawable()).getSourceBitmap());
                break;
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

    private void openCamera(int cameraCode) {
        checkCameraPermission(cameraCode);
    }
    //TAKE PICTURE
    public void openGalery(int requestCode){
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(Intent.createChooser(it, "Select File"), requestCode);
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
                outputFileUri = FileProvider.getUriForFile(RemaksSlikEditKprActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "fotoagunandeposito.png"));
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

    private void setDataImage(Uri uri, Bitmap bitmap, ImageView iv, Intent data){
        initImageFileName();
        if (getPickImageResultUri(data) != null) {
            uri = getPickImageResultUri(data);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                bitmap = AppUtil.rotateImageIfRequired(this, bitmap, uri);
                iv_foto.setImageBitmap(bitmap);
                bitmap_foto = bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            String filename = appPreferences.getFotoAgunan();
            ImageHandler.saveImageToCache(this, bitmap, filename);
            uploadFoto(filename);
        }
    }

    private void initImageFileName() {
        String fileName = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date())+"_"+System.currentTimeMillis()+ ".jpg";
        appPreferences.setFotoAgunan(fileName);
    }

    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();
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
                            iv_foto.setImageBitmap(bitmap_foto);
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
                            AppUtil.notiferror(RemaksSlikEditKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(RemaksSlikEditKprActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(RemaksSlikEditKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    @Override
    public void success(boolean val) {
        if (val){
            finish();
        }
    }

    @Override
    public void confirm(boolean val) {

    }
}

