package com.application.bris.ikurma_nos_konsumer.page_data_nasabah;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityDataJaminanBinding ;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.io.File;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import android.icu.util.Calendar;
import java.util.Locale;

public class dataJaminanActivity extends AppCompatActivity implements View.OnClickListener, CameraListener {
    private ActivityDataJaminanBinding binding;
    private DatePickerDialog dpSK;
    private Calendar calLahir;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataJaminanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        onclickSelectDialog();
        setContentView(view);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Jaminan");
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }
        private void onclickSelectDialog(){
        binding.etTanggalTerbitSk.setOnClickListener(this);
        binding.tfTanggalTerbitSk.setOnClickListener(this);
        binding.ivKtpNasabah.setOnClickListener(this);
         binding.ivAssetAkad.setOnClickListener(this);
         binding.ivFormApplikasi.setOnClickListener(this);
         binding.ivIdcard.setOnClickListener(this);
         binding.ivNpwp.setOnClickListener(this);
        binding.ivKtpPasangan.setOnClickListener(this);
        binding.ivSkPengangkatan.setOnClickListener(this);
        binding.ivSkPensiun.setOnClickListener(this);
        binding.ivSkTerakhir.setOnClickListener(this);
        binding.ivSuratInstansi.setOnClickListener(this);
        binding.btnKtpNasabah.setOnClickListener(this);
        binding.btnAssetAkad.setOnClickListener(this);
        binding.btnFormApplikasi.setOnClickListener(this);
        binding.btnIdcard.setOnClickListener(this);
        binding.btnNpwp.setOnClickListener(this);
        binding.btnKtpPasangan.setOnClickListener(this);
        binding.btnSkPengangkatan.setOnClickListener(this);
        binding.btnSkPensiun.setOnClickListener(this);
        binding.btnSkTerakhir.setOnClickListener(this);
        binding.btnSuratInstansi.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_tanggal_terbit_sk:
            case R.id.tf_tanggal_terbit_sk:
                dpSKCalendar();
                break;
            case R.id.iv_ktp_nasabah:
            case  R.id.iv_asset_akad:
            case  R.id.iv_form_applikasi:
            case  R.id.iv_idcard:
            case R.id.iv_npwp:
            case R.id.iv_ktp_pasangan:
            case R.id.iv_sk_pengangkatan:
            case R.id.iv_sk_pensiun:
            case R.id.iv_sk_terakhir:
            case R.id.iv_surat_instansi:
            case R.id.btn_ktp_nasabah:
            case  R.id.btn_asset_akad:
            case  R.id.btn_form_applikasi:
            case  R.id.btn_idcard:
            case R.id.btn_npwp:
            case R.id.btn_ktp_pasangan:
            case R.id.btn_sk_pengangkatan:
            case R.id.btn_sk_pensiun:
            case R.id.btn_sk_terakhir:
            case R.id.btn_surat_instansi:
                BSUploadFile.displayWithTitle(dataJaminanActivity.this.getSupportFragmentManager(),this,"");
                break;
        }
    }



    private void dpSKCalendar(){
        calLahir = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ls_tanggalLahirPasangan = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calLahir.set(Calendar.YEAR, year);
                calLahir.set(Calendar.MONTH, month);
                calLahir.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calLahirString = dateClient.format(calLahir.getTime());
                binding.etTanggalTerbitSk.setText(calLahirString);
            }
        };

        dpSK = new DatePickerDialog(dataJaminanActivity.this, R.style.AppTheme_TimePickerTheme, ls_tanggalLahirPasangan, calLahir.get(Calendar.YEAR),
                calLahir.get(Calendar.MONTH), calLahir.get(Calendar.DAY_OF_MONTH));
        dpSK.getDatePicker().setMaxDate(calLahir.getTimeInMillis());
        dpSK.show();
    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_KANTOR1);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_KANTOR1);
                    break;
                case "Pick File":
                    openFile(PICK_PICTURE_KANTOR1);
                    break;
            }

    }
    private final int TAKE_PICTURE_KANTOR1 = 11;
    private final int PICK_PICTURE_KANTOR1 = 22;

    private void openCamera(int cameraCode) {
        checkCameraPermission(cameraCode);
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

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static int CAMERA_CODE_FORE_PERMISSION = 0;
    public void checkCameraPermission(int cameraCode) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);
        } else {
            Uri outputFileUri = getCaptureImageOutputUri();
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(captureIntent, cameraCode);
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
        File getImage = this.getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                outputFileUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "fotoagunan.png"));
            } else {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "fotoagunan.png"));
            }
        }
        return outputFileUri;
    }
}
