package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.master_instansi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseFile;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseLogicalDoc;
import com.application.bris.ikurma_nos_konsumer.api.model.request.foto.ReqUploadFile;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.JaminandanDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDocument;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqInquery;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityInputInstansiBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityInputLkpKoordinasiBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataLkpKoordinasi;
import com.application.bris.ikurma_nos_konsumer.model.prapen.InputLkpKoordinasi;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_marketing.DataMarketingActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class InputLkpKoordinasiActivity extends AppCompatActivity implements View.OnClickListener, CameraListener, ConfirmListener {
    private PrapenAoActivityInputLkpKoordinasiBinding binding;
    List<JaminandanDokumen> jd;

    private String fileNameLkp = "", tipeFile;
    private String idFileLkp= "";
    private long idInstansi=0l;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    private String val_lkp = "";
    private final int  UPLOAD_LKP = 4;
    int idUpload=0;
    boolean dialogOpened=false;
    boolean errorUpload=false;
    private  boolean lolosValidasi =true;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PrapenAoActivityInputLkpKoordinasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();


        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        if(getIntent().hasExtra("idInstansi")){
            idInstansi=getIntent().getLongExtra("idInstansi",0);
        }

        onclickSelectDialog();
        setContentView(view);
        disableText();
        backgroundStatusBar();
        isiDropdown();
        otherViewChanges();
        //initdata();
        AppUtil.toolbarRegular(this, "Input LKP Koordinasi");

    }

    private void sendData() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        InputLkpKoordinasi req = new InputLkpKoordinasi();
        DataLkpKoordinasi dataLkp=new DataLkpKoordinasi();

        dataLkp.setIdDokumen(idFileLkp);
        dataLkp.setFileName(fileNameLkp);
        dataLkp.setTanggalLkp(AppUtil.parseTanggalGeneral(binding.etTanggalLkp.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
        dataLkp.setTanggalKadaluarsa(AppUtil.parseTanggalGeneral(binding.etTanggalLkpKadaluarsa.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
        dataLkp.setKodeCabang(binding.etKodeCabang.getText().toString());
        dataLkp.setNamaCabang(binding.etNamaCabang.getText().toString());

        req.setUid(Integer.toString(appPreferences.getUid()));
        req.setInstansiId(idInstansi);
        req.setDataLkp(dataLkp);


        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updateLkpKoordinasi(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                if (response.isSuccessful()) {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    Gson gson = new Gson();

                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        CustomDialog.DialogSuccess(InputLkpKoordinasiActivity.this, "Success!", "Update Data LKP Sukses", InputLkpKoordinasiActivity.this);


                    } else {
                        AppUtil.notiferror(InputLkpKoordinasiActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                } else {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    Error error = ParseResponseError.confirmEror(response.errorBody());
                    AppUtil.notiferror(InputLkpKoordinasiActivity.this, findViewById(android.R.id.content), error.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(InputLkpKoordinasiActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }



    private void disableText() {
        binding.etTanggalLkp.setFocusable(false);
        binding.etTanggalLkpKadaluarsa.setFocusable(false);
        binding.etNamaCabang.setFocusable(false);
        binding.etKodeCabang.setFocusable(false);

    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void onclickSelectDialog() {
        binding.btnFotoLkp.setOnClickListener(this);
        binding.btnSimpanDataLkp.setOnClickListener(this);

        binding.tfTanggalLkp.getEndIconImageButton().setOnClickListener(v -> AppUtil.genericCalendarDialog(InputLkpKoordinasiActivity.this,binding.etTanggalLkp));
        binding.tfTanggalLkp.setOnClickListener(v -> AppUtil.genericCalendarDialog(InputLkpKoordinasiActivity.this,binding.etTanggalLkp));


        binding.tfTanggalLkpKadaluarsa.getEndIconImageButton().setOnClickListener(v -> AppUtil.genericCalendarDialog(InputLkpKoordinasiActivity.this,binding.etTanggalLkpKadaluarsa));
        binding.tfTanggalLkpKadaluarsa.setOnClickListener(v -> AppUtil.genericCalendarDialog(InputLkpKoordinasiActivity.this,binding.etTanggalLkpKadaluarsa));

    }


    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_foto_lkp:
                BSUploadFile.displayWithTitle(InputLkpKoordinasiActivity.this.getSupportFragmentManager(), this, "");
                idUpload = UPLOAD_LKP;
                break;
            case R.id.btn_simpan_data_lkp:
                    validasi();
                break;

        }
    }

    private void validasi() {
        lolosValidasi =true;
        easyValidateField(binding.etTanggalLkpKadaluarsa,binding.tfTanggalLkpKadaluarsa);
        easyValidateField(binding.etTanggalLkp,binding.tfTanggalLkp);
        if(lolosValidasi){
            //do send data
            sendData();
//            Toast.makeText(this, "Nit not lolos validasi dan pura pura nyimpen", Toast.LENGTH_SHORT).show();
        }
    }

    private void easyValidateField(EditText editText, TextFieldBoxes textFieldBoxes){
        if(editText.getText().toString().trim().isEmpty() || editText.getText().toString().trim().equalsIgnoreCase(" ")){
            textFieldBoxes.setError(textFieldBoxes.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(InputLkpKoordinasiActivity.this, findViewById(android.R.id.content), textFieldBoxes.getLabelText() + " " + getString(R.string.title_validate_field));
            lolosValidasi =false;
        }

    }

    private void isiDropdown(){
     //takde dropdown gess
    }

    private void otherViewChanges(){
        try{
            binding.etNamaCabang.setText(appPreferences.getNamaKantor());
            binding.etKodeCabang.setText(appPreferences.getKodeCabang());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    //UPLOAD FILE METHODS

    private void easySelectCameraMenu(String idMenu, int KODE_UPLOAD, String namaDokumen){
        if(dialogOpened){
            if(idMenu.equalsIgnoreCase("Take Photo")){
                tipeFile = "png";
                openCamera(KODE_UPLOAD, namaDokumen);
            }
            else if(idMenu.equalsIgnoreCase("Pick Photo")){
                tipeFile="png";
                openGalery(KODE_UPLOAD);
            }
            else if(idMenu.equalsIgnoreCase("Pick File")){
                tipeFile="pdf";
                openFile(KODE_UPLOAD);
            }
            dialogOpened=false;
        }


    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        dialogOpened=true;
        easySelectCameraMenu(idMenu,UPLOAD_LKP,"dokLkpKoordinasi");
    }

    private void openCamera(int cameraCode, String namaFoto) {
        checkCameraPermission(cameraCode, namaFoto);
    }

    public void openGalery(int cameraCode) {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(Intent.createChooser(it, "Select File"), cameraCode);
    }

    public void openFile(int cameraCode) {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("application/pdf");
        startActivityForResult(Intent.createChooser(it, "Select File"), cameraCode);
    }

    public void checkCameraPermission(int cameraCode, String namaFoto) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    100);
        } else {
            Uri outputFileUri = getCaptureImageOutputUri(namaFoto);
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(captureIntent, cameraCode);
        }
    }

    private void directOpenCamera(int cameraCode, String namaFoto) {
        Uri outputFileUri = getCaptureImageOutputUri(namaFoto);
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(captureIntent, cameraCode);
    }

    private Uri getCaptureImageOutputUri(String namaFoto) {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                outputFileUri = FileProvider.getUriForFile(InputLkpKoordinasiActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), namaFoto + ".png"));
            } else {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), namaFoto + ".png"));
            }
        }
        return outputFileUri;
    }


    //legacy upload
//    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
//        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
//        switch (requestCode) {
//            case UPLOAD_DATAKTP:
//                //legacy
////                setDataImage(uri_fotoktp, bitmap_fotoktp, binding.ivKtpNasabah, imageReturnedIntent, "fotoktp");
//
//                //logicaldoc
//                setDataImage(uri_fotoktp, bitmap_fotoktp, binding.ivKtpNasabah, imageReturnedIntent, "fotoktp");
//                if (tipeFile.equalsIgnoreCase("pdf")) {
//                    fileNameKtp = idAplikasi + "_ktpD3.pdf";
//                    uploadFile(val_ktp, fileNameKtp, UPLOAD_DATAKTP);
//                } else {
//                    binding.ivKtpNasabah.invalidate();
//                    RoundedDrawable drawableIdeb = (RoundedDrawable) binding.ivKtpNasabah.getDrawable();
//                    Bitmap bitmapIdeb = drawableIdeb.getSourceBitmap();
//                    fileNameKtp = idAplikasi + "_ktpD3.png";
//                    uploadFile(AppUtil.encodeImageTobase64(bitmapIdeb), fileNameKtp, UPLOAD_DATAKTP);
//                }
//                break;
//            case UPLOAD_IDCARD:
//                setDataImage(uri_idcard, bitmap_idcard, binding.ivIdcard, imageReturnedIntent, "idcard");
//                break;
//            case UPLOAD_KTPPASANGAN:
//                setDataImage(uri_ktppasangan, bitmap_ktppasangan, binding.ivKtpPasangan, imageReturnedIntent, "ktppasangan");
//                break;
//            case UPLOAD_ASSETAKAD:
//                setDataImage(uri_assetakad, bitmap_assetakad, binding.ivAssetAkad, imageReturnedIntent, "assetakad");
//                break;
//            case UPLOAD_DATAINSTASI:
//                setDataImage(uri_datainstansi, bitmap_datainstansi, binding.ivSuratInstansi, imageReturnedIntent, "datainstansi");
//                break;
//            case UPLOAD_FORMAPLIKASI:
//                setDataImage(uri_formaplikasi, bitmap_formaplikasi, binding.ivFormApplikasi, imageReturnedIntent, "formaplikasi");
//                break;
//            case UPLOAD_NPWP:
//                setDataImage(uri_npwp, bitmap_npwp, binding.ivNpwp, imageReturnedIntent, "npwp");
//                break;
//            case UPLOAD_SKPENSIUN:
//                setDataImage(uri_skpensiun, bitmap_skpensiun, binding.ivSkPensiun, imageReturnedIntent, "skpensiun");
//                break;
//            case UPLOAD_SKPENGANGKATAN:
//                setDataImage(uri_skpengangkatan, bitmap_skpengangkatan, binding.ivSkPengangkatan, imageReturnedIntent, "skpengangkatan");
//                break;
//            case UPLOAD_SKTERAKHIR:
//                setDataImage(uri_skterakhir, bitmap_skterakhir, binding.ivSkTerakhir, imageReturnedIntent, "skterakhir");
//                break;
//
//        }
//    }

    //logical doc
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        errorUpload=false;
        switch (requestCode) {
            case UPLOAD_LKP:
                setDataImage(binding.ivFotoLkp,data,"dokLkpKoordinasi",UPLOAD_LKP);
                checkFileTypeThenUpload(fileNameLkp,"_dokLkpKoordinasi",binding.ivFotoLkp,val_lkp,UPLOAD_LKP);
                break;
        }
    }

    public Uri getPickImageResultUri(Intent data, String namaFoto) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri(namaFoto) : data.getData();
    }

    private void setDataImage(Uri uri, Bitmap bitmap, ImageView iv, Intent data, String namaFoto) {
        if (getPickImageResultUri(data, namaFoto) != null) {
            uri = getPickImageResultUri(data, namaFoto);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                bitmap = AppUtil.rotateImageIfRequired(this, bitmap, uri);
                iv.setImageBitmap(bitmap);

                //BELUM LENGKAP DI BAGIAN OBJEK DATA, KARENA API BELOM ADA GAIS
                if (idUpload==UPLOAD_LKP) {
//                    DataJaminanKTP.setImg(idFileLkp);
//                    DataJaminanKTP.setFileName("dokLkpKoordinasi.png");
                }

            } catch (Exception e) {
                e.printStackTrace();
                try {
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));
                    if (idUpload==UPLOAD_LKP) {
                        Uri uriPdf = data.getData();
                        val_lkp = AppUtil.encodeFileToBase64(this, uriPdf);
//                        DataJaminanKTP.setImg(idFilePks);
//                        DataJaminanKTP.setFileName("pks.pdf");
                    }
                } catch (NullPointerException e2) {
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.banner_placeholder));
                    e2.printStackTrace();
                }


            }
        }

    }

    private void setDataImage(ImageView iv, Intent data, String namaFoto,int KODE_UPLOAD) {
        Bitmap bitmap = null;
        Uri uri;
        if (getPickImageResultUri(data, namaFoto) != null) {
            uri = getPickImageResultUri(data, namaFoto);
            try {

                bitmap = MediaStore.Images.Media.getBitmap(InputLkpKoordinasiActivity.this.getContentResolver(), uri);
                bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                bitmap = AppUtil.rotateImageIfRequired(InputLkpKoordinasiActivity.this, bitmap, uri);
                iv.setImageBitmap(bitmap);


            } catch (NullPointerException e) {
                e.printStackTrace();

                try{
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));

                    if(KODE_UPLOAD==UPLOAD_LKP){
                        Uri uriPdf = data.getData();
                        val_lkp= AppUtil.encodeFileToBase64(InputLkpKoordinasiActivity.this,uriPdf);
                    }
                }
                catch (Exception e2){
                    e2.printStackTrace();
                }
            }
            catch (FileNotFoundException e2){
                e2.printStackTrace();
                errorUpload=true;
            }
            catch (Exception e3){
                e3.printStackTrace();
            }
        }
    }

    @Override
    public void success(boolean val) {
        finish();
    }

    @Override
    public void confirm(boolean val) {

    }

    public void uploadFile(String base64, String fileName, int uploadCode) {
        ApiClientAdapter apiClientAdapter = new ApiClientAdapter(this);
        //  dataUser = getListUser();
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUploadFile req = new ReqUploadFile();
        //pantekan uid
        req.setFolderId(AppUtil.getIdFolderLogicalDoc());
        req.setLanguage("en");
        req.setFileB64(base64);
        req.setFileName(fileName);
        Call<ParseResponseFile> call = apiClientAdapter.getApiInterface().uploadFileLogicalDoc(req);
        call.enqueue(new Callback<ParseResponseFile>() {
            @Override
            public void onResponse(Call<ParseResponseFile> call, Response<ParseResponseFile> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {

                  if (uploadCode == UPLOAD_LKP) {
                        idFileLkp = response.body().getId();
                        fileNameLkp=fileName;
                    }

                    AppUtil.notifsuccess(InputLkpKoordinasiActivity.this, findViewById(android.R.id.content), "Upload Berhasil");
                } else {
                    AppUtil.notiferror(InputLkpKoordinasiActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseFile> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(InputLkpKoordinasiActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
            }
        });

    }

    private void checkFileTypeThenUpload(String filename, String fileNameDoc, ImageView imageView,String val_base64, int uploadCode){

        //jadi ada variabel error upload, untuk ngecek kalau dia error karena ketika sbelum milih gambar tiba tiba di back, maka gambarnya kaga usah diupload


        if(!errorUpload){
            if (tipeFile.equalsIgnoreCase("pdf")) {
                filename =fileNameDoc+".pdf";
                uploadFile(val_base64, filename, uploadCode);
            } else {
                imageView.invalidate();
                RoundedDrawable drawableDoc = (RoundedDrawable) imageView.getDrawable();
                Bitmap bitmapDoc = drawableDoc.getSourceBitmap();
                filename =fileNameDoc+".png";
                uploadFile(AppUtil.encodeImageTobase64(bitmapDoc), filename, uploadCode);
            }

        }

    }

    private void checkFileTypeThenSet(Context context, String idDok, ImageView imageView, String fileName){

        if(fileName.substring(fileName.length()-3,fileName.length()).equalsIgnoreCase("pdf")){

            if(idDok.length()<10){
                loadFileJson(idDok,imageView);
            }
            else{
                AppUtil.convertBase64ToFileWithOnClick(context,idDok,imageView,fileName);
            }

        }
        else{

            if(idDok.length()<10){
                AppUtil.setImageGlide(context,idDok,imageView);
            }
            else{
                AppUtil.convertBase64ToImage(idDok,imageView);
            }

        }
    }

    public void loadFileJson(String idFoto,ImageView imageView) {
        ApiClientAdapter apiClientAdapter=new ApiClientAdapter(InputLkpKoordinasiActivity.this);
        Call<ParseResponseLogicalDoc> call = apiClientAdapter.getApiInterface().getFileJson(idFoto);
        call.enqueue(new Callback<ParseResponseLogicalDoc>() {
            @Override
            public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
//                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getBinaryData()!=null){
                        AppUtil.convertBase64ToFileWithOnClick(InputLkpKoordinasiActivity.this,response.body().getBinaryData(),imageView,response.body().getFileName());
                    }
                    else{
                        AppUtil.notiferror(InputLkpKoordinasiActivity.this,findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                    }
                }
                else{
                    AppUtil.notiferror(InputLkpKoordinasiActivity.this,findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(InputLkpKoordinasiActivity.this,findViewById(android.R.id.content), "Terjadi kesalahan");

                t.printStackTrace();
            }
        });

    }


}