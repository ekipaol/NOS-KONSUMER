package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_ideb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseFile;
import com.application.bris.ikurma_nos_konsumer.api.model.request.foto.ReqUploadFile;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateIdebOjk;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityEditIdebBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataMarketing;
import com.application.bris.ikurma_nos_konsumer.model.prapen.ReqUpdateDataMarketing;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataIdeb;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_marketing.DataMarketingActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_nasabah.DataNasabahPrapenActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_nasabah.FragmentDataPribadiPrapen;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditIdebActivity extends AppCompatActivity implements View.OnClickListener, GenericListenerOnSelect, CameraListener, ConfirmListener {

    private PrapenAoActivityEditIdebBinding binding;
    private List<MGenericModel> dataDropdownTreatmentPembiayaan =new ArrayList<>();
    private DataIdeb dataIdeb;
    private Long idAplikasi;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private  boolean uploadFoto=false;
    private String idFile,fileName;
    private String namaFileIntent;


    //dokumen variabel
    private String valBase64Pdf ="";
    private String namaDokumen="dokumenIdeb";
    private String tipeFile="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PrapenAoActivityEditIdebBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        appPreferences=new AppPreferences(this);
        apiClientAdapter=new ApiClientAdapter(this);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Perlakuan IDEB");
        namaFileIntent=getIntent().getStringExtra("namaDokumen");

        allOnClicks();
        disableEditTexts();
        isiDropdown();
        allOnChange();
        setData();

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.DialogBackpress(EditIdebActivity.this);
            }
        });
    }


    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public void onBackPressed() {
        CustomDialog.DialogBackpress(EditIdebActivity.this);
    }

    private void allOnClicks(){
        endIconOnClick();
        binding.etTreatmentPembiayaan.setOnClickListener(this);
        binding.tfTreatmentPembiayaan.setOnClickListener(this);
        binding.btnSimpanEditIdeb.setOnClickListener(this);
        binding.btnFotoDokumen.setOnClickListener(this);
        binding.ivFotoDokumen.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            // Memiliki aset
            case R.id.et_treatment_pembiayaan:
            case R.id.tf_treatment_pembiayaan:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTreatmentPembiayaan.getLabelText(), dataDropdownTreatmentPembiayaan, EditIdebActivity.this);
                break;
            case R.id.btn_simpan_edit_ideb:
                if(binding.etTreatmentPembiayaan.getText().toString().isEmpty()){
                    AppUtil.notiferror(EditIdebActivity.this, findViewById(android.R.id.content), "Harap Pilih Treatment Terlebih Dahulu");
                    binding.tfTreatmentPembiayaan.setError("Harap Pilih",true);
                }
                else{
                    updateIdeb();
                }
                break;
            case R.id.btn_foto_dokumen:
                BSUploadFile.displayWithTitle(getSupportFragmentManager(), EditIdebActivity.this,"Upload Dokumen");
                break;
            case R.id.iv_foto_dokumen:
                DialogPreviewPhoto.display(((AppCompatActivity) binding.ivFotoDokumen.getContext()).getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)binding.ivFotoDokumen.getDrawable()).getSourceBitmap());
            default:break;
        }
    }
    private void endIconOnClick(){
        binding.tfTreatmentPembiayaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTreatmentPembiayaan.getLabelText(), dataDropdownTreatmentPembiayaan, EditIdebActivity.this);
            }
        });

    }

    private void disableEditTexts(){
        binding.etNamaLembagaKeuangan.setFocusable(false);
        binding.etBakiDebet.setFocusable(false);
        binding.etKualitasPembiayaan.setFocusable(false);
        binding.etPerkiraanAngsuranBulanan.setFocusable(false);
        binding.etTreatmentPembiayaan.setFocusable(false);

    }

    @Override
    public void onSelect(String title, MGenericModel data) {
        if(title.equalsIgnoreCase(binding.tfTreatmentPembiayaan.getLabelText())){
            binding.etTreatmentPembiayaan.setText(data.getDESC());
        }

    }

    private void isiDropdown(){
        dataDropdownTreatmentPembiayaan.add(new MGenericModel("Pembiayaan tetap dilanjutkan","Pembiayaan tetap dilanjutkan"));
        dataDropdownTreatmentPembiayaan.add(new MGenericModel("Pembiayaan akan dilunasi melalui Pencairan","Pembiayaan akan dilunasi melalui Pencairan"));
        dataDropdownTreatmentPembiayaan.add(new MGenericModel("Pembiayaan dilakukan Take Over","Pembiayaan dilakukan Take Over"));
        dataDropdownTreatmentPembiayaan.add(new MGenericModel("Nasabah Menginfokan pembiayaan sudah Lunas/Selesai","Nasabah Menginfokan pembiayaan sudah Lunas/Selesai"));
        dataDropdownTreatmentPembiayaan.add(new MGenericModel("Nasabah Menginfokan tidak memiliki pembiayaan tersebut","Nasabah Menginfokan tidak memiliki pembiayaan tersebut"));
        dataDropdownTreatmentPembiayaan.add(new MGenericModel("Nasabah Menginfokan Membayar Tepat Waktu","Nasabah Menginfokan Membayar Tepat Waktu"));


    }


    private void allOnChange(){
//        binding.etPerkiraanAngsuranBulanan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etPerkiraanAngsuranBulanan));
//        binding.etBakiDebet.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etBakiDebet));
    }

    private void setData(){
        dataIdeb=new DataIdeb();
        dataIdeb.setPerkiraanAngsuranBulanan(getIntent().getStringExtra("perkiraanAngsuran"));
        dataIdeb.setNama(getIntent().getStringExtra("nama"));
        dataIdeb.setNamaLembagaKeuangan(getIntent().getStringExtra("lembagaKeuangan"));
        dataIdeb.setKualitasPembiayaan(getIntent().getStringExtra("kualitasPembiayaan"));
        dataIdeb.setTreatmentPembiayaan(getIntent().getStringExtra("treatmentPembiayaan"));
        dataIdeb.setPerkiraanAngsuranBulanan(getIntent().getStringExtra("perkiraanAngsuran"));
        dataIdeb.setBakiDebet(getIntent().getStringExtra("bakiDebet"));
        dataIdeb.setIdDokumen(getIntent().getLongExtra("fasilitasId",0));
        idAplikasi=getIntent().getLongExtra("idAplikasi",0);

        binding.etNamaLembagaKeuangan.setText(dataIdeb.getNamaLembagaKeuangan());
        binding.etBakiDebet.setText(dataIdeb.getBakiDebet());
        binding.etKualitasPembiayaan.setText(dataIdeb.getKualitasPembiayaan());
        binding.etPerkiraanAngsuranBulanan.setText(dataIdeb.getPerkiraanAngsuranBulanan());
        binding.etTreatmentPembiayaan.setText(dataIdeb.getTreatmentPembiayaan());

        if(!namaFileIntent.isEmpty()){
            if(namaFileIntent.substring(namaFileIntent.length()-3,namaFileIntent.length()).equalsIgnoreCase("pdf")){
                AppUtil.setLoadPdf(EditIdebActivity.this,Integer.parseInt(getIntent().getStringExtra("idDokumen")),binding.ivFotoDokumen);
            }
            else if(namaFileIntent.substring(namaFileIntent.length()-3,namaFileIntent.length()).equalsIgnoreCase("png")){
                AppUtil.setImageGlide(EditIdebActivity.this,Integer.parseInt(getIntent().getStringExtra("idDokumen")),binding.ivFotoDokumen);
            }
        }



    }

    //UPLOAD FILE METHODS


    private void openCamera(int cameraCode,String namaFoto) {
        if (ContextCompat.checkSelfPermission(EditIdebActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(EditIdebActivity.this, new String[]{Manifest.permission.CAMERA},
                    100);
        } else {
            Uri outputFileUri = getCaptureImageOutputUri(namaFoto);
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(captureIntent, cameraCode);
        }
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

    private Uri getCaptureImageOutputUri(String namaFoto) {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                outputFileUri = FileProvider.getUriForFile(EditIdebActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), namaFoto + ".png"));
            } else {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), namaFoto + ".png"));
            }
        }
        return outputFileUri;
    }

    public Uri getPickImageResultUri(Intent data, String namaFoto) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri(namaFoto) : data.getData();
    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        switch(idMenu){
            case "Take Photo":
                tipeFile="png";
                openCamera(1,namaDokumen);
                break;
            case "Pick Photo":
                tipeFile="png";
                openGalery(1);
                break;
            case "Pick File":
                openFile(1);
                tipeFile="pdf";
                break;
        }

    }

    //legacy upload
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        setDataImage(binding.ivFotoDokumen, data, namaDokumen);
//        uploadFoto=true;
//
//    }

    //logical doc
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setDataImage(binding.ivFotoDokumen, data, namaDokumen);
        if(tipeFile.equalsIgnoreCase("pdf")){
            fileName=idAplikasi+"_ideb_"+dataIdeb.getIdDokumen() +".pdf";
            uploadFile(valBase64Pdf,fileName);
        }
        else{
            binding.ivFotoDokumen.invalidate();
            RoundedDrawable drawableIdeb = (RoundedDrawable) binding.ivFotoDokumen.getDrawable();
            Bitmap bitmapIdeb = drawableIdeb.getSourceBitmap();
            fileName=idAplikasi+"_ideb_"+dataIdeb.getIdDokumen() +".png";
            uploadFile(AppUtil.encodeImageTobase64(bitmapIdeb),fileName);
        }
//        uploadFoto=true;

    }

    private void setDataImage(ImageView iv, Intent data, String namaFoto) {
        Bitmap bitmap = null;
        Uri uri;
        if (getPickImageResultUri(data, namaFoto) != null) {
            uri = getPickImageResultUri(data, namaFoto);
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                bitmap = AppUtil.rotateImageIfRequired(EditIdebActivity.this, bitmap, uri);
                iv.setImageBitmap(bitmap);


            } catch (Exception e) {
                e.printStackTrace();
                try{
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));
                    Uri uriPdf = data.getData();
                    valBase64Pdf= AppUtil.encodeFileToBase64(EditIdebActivity.this,uriPdf);
                }
                catch (Exception e2){
                    e2.printStackTrace();
                }
            }
        }
    }

    public void updateIdeb(){
        UpdateIdebOjk req=new UpdateIdebOjk();
        req.setApplicationId(idAplikasi);
        req.setFasilitasAktifId(dataIdeb.getIdDokumen());
        req.setTreatmentPembiayaan(binding.etTreatmentPembiayaan.getText().toString());
        req.setUid(String.valueOf(appPreferences.getUid()));

        if(uploadFoto){

            //legacy upload
//            if(tipeFile.equalsIgnoreCase("pdf")){
//                req.setImg(valBase64Pdf);
//                req.setFileName("_ideb_"+dataIdeb.getIdDokumen() +".pdf");
//            }
//            else{
//                binding.ivFotoDokumen.invalidate();
//                RoundedDrawable drawableIdeb = (RoundedDrawable) binding.ivFotoDokumen.getDrawable();
//                Bitmap bitmapIdeb = drawableIdeb.getSourceBitmap();
//                req.setImg(AppUtil.encodeImageTobase64(bitmapIdeb));
//                req.setFileName("_ideb_"+dataIdeb.getIdDokumen() +".png");
//            }


            //logical doc
            req.setImg(idFile);
            req.setFileName(fileName);
        }
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updateIdebOjk(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){

                            CustomDialog.DialogSuccess(EditIdebActivity.this, "Success!", "Update Data Marketing sukses", EditIdebActivity.this);
                        }
                        else{
                            AppUtil.notiferror(EditIdebActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(EditIdebActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(EditIdebActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void uploadFile(String base64, String fileName) {
        ApiClientAdapter apiClientAdapter=new ApiClientAdapter(this);
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUploadFile req=new ReqUploadFile();
        //pantekan uid
        req.setFolderId(AppUtil.getIdFolderLogicalDoc());
        req.setLanguage("en");
        req.setFileB64(base64);
        req.setFileName(fileName);
        Call<ParseResponseFile> call = apiClientAdapter.getApiInterface().uploadFileLogicalDoc(req);
        call.enqueue(new Callback<ParseResponseFile>() {
            @Override
            public void onResponse(Call<ParseResponseFile> call, Response<ParseResponseFile> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {

                        idFile=response.body().getId();

                    AppUtil.notifsuccess(EditIdebActivity.this, findViewById(android.R.id.content), "Upload Berhasil");
                    uploadFoto=true;
                }
                else{
                    AppUtil.notiferror(EditIdebActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseFile> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(EditIdebActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });

    }

    @Override
    public void success(boolean val) {
        finish();
    }

    @Override
    public void confirm(boolean val) {

    }
}