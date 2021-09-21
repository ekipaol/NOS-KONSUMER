package com.application.bris.ikurma_nos_konsumer.page_data_nasabah;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
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
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityPendapatanBinding ;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ActivityDokumenPendapatan extends AppCompatActivity implements View.OnClickListener, CameraListener {

    private ActivityPendapatanBinding binding;
    private DatePickerDialog dpSK;
    private Calendar calLahir;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("MM-yyyy", Locale.US);
    public static SimpleDateFormat dateClient2 = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPendapatanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        binding.etGajiBersihP1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etGajiBersihP1));
        binding.etGajiBersihP2.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etGajiBersihP2));
        binding.etGajiBersihP3.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etGajiBersihP3));
        binding.etTunjanganP1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTunjanganP1));
        binding.etTunjanganP2.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTunjanganP2));
        binding.etTunjanganP3.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTunjanganP3));
        binding.etTotalDebit1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalDebit1));
        binding.etTotalDebit2.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalDebit2));
        binding.etTotalKredit1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalKredit1));
        binding.etTotalKredit2.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalKredit2));
        onclickSelectDialog();
        setContentView(view);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Dokumen Pendapatan");
    }
    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void onclickSelectDialog(){
        binding.btnSend.setOnClickListener(this);
        binding.etPeriodeAkhirWaktu1.setOnClickListener(this);
        binding.etPeriodeAkhirWaktu2.setOnClickListener(this);
        binding.etPeriodeAwalWaktu1.setOnClickListener(this);
        binding.etPeriodeAwalWaktu2.setOnClickListener(this);
        binding.etPeriodeGajiP1.setOnClickListener(this);
        binding.etPeriodeGajiP2.setOnClickListener(this);
        binding.etPeriodeGajiP3.setOnClickListener(this);
        binding.etTglTunjanganP1.setOnClickListener(this);
        binding.etTglTunjanganP2.setOnClickListener(this);
        binding.etTglTunjanganP3.setOnClickListener(this);
        binding.tfPeriodeAkhirWaktu1.setOnClickListener(this);
        binding.tfPeriodeAkhirWaktu2.setOnClickListener(this);
        binding.tfPeriodeAwalWaktu1.setOnClickListener(this);
        binding.tfPeriodeAwalWaktu2.setOnClickListener(this);
        binding.tfPeriodeGajiP1.setOnClickListener(this);
        binding.tfPeriodeGajiP2.setOnClickListener(this);
        binding.tfPeriodeGajiP3.setOnClickListener(this);
        binding.tfTglTunjanganP1.setOnClickListener(this);
        binding.tfTglTunjanganP2.setOnClickListener(this);
        binding.tfTglTunjanganP3.setOnClickListener(this);
        binding.btnRekeningKoran1.setOnClickListener(this);
        binding.btnRekeningKoran2.setOnClickListener(this);
        binding.btnSlipgajiP1.setOnClickListener(this);
        binding.btnSlipgajiP2.setOnClickListener(this);
        binding.btnSlipgajiP3.setOnClickListener(this);
        binding.btnSliptunjanganP1.setOnClickListener(this);
        binding.btnSliptunjanganP2.setOnClickListener(this);
        binding.btnSliptunjanganP3.setOnClickListener(this);
        binding.ivRekeningKoran1.setOnClickListener(this);
        binding.ivRekeningKoran2.setOnClickListener(this);
        binding.ivSlipgajiP1.setOnClickListener(this);
        binding.ivSlipgajiP2.setOnClickListener(this);
        binding.ivSlipgajiP3.setOnClickListener(this);
        binding.ivSliptunjanganP1.setOnClickListener(this);
        binding.ivSliptunjanganP2.setOnClickListener(this);
        binding.ivSliptunjanganP3.setOnClickListener(this);
        binding.rlRekeningKoran1.setOnClickListener(this);
        binding.rlRekeningKoran2.setOnClickListener(this);
        binding.rlSlipgajiP1.setOnClickListener(this);
        binding.rlSlipgajiP2.setOnClickListener(this);
        binding.rlSlipgajiP3.setOnClickListener(this);
        binding.rlSlipgajiP1.setOnClickListener(this);
        binding.rlSliptunjanganP1.setOnClickListener(this);
        binding.rlSliptunjanganP2.setOnClickListener(this);
        binding.rlSliptunjanganP3.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send:
                break;
            case R.id.btn_rekening_koran1 :
            case R.id.btn_rekening_koran2 :
            case R.id.btn_slipgaji_p1 :
            case R.id.btn_slipgaji_p2 :
            case R.id.btn_slipgaji_p3 :
            case R.id.btn_sliptunjangan_p1 :
            case R.id.btn_sliptunjangan_p2 :
            case R.id.btn_sliptunjangan_p3 :
            case R.id.rl_rekening_koran1 :
            case R.id.rl_rekening_koran2 :
            case R.id.rl_slipgaji_p1 :
            case R.id.rl_slipgaji_p2 :
            case R.id.rl_slipgaji_p3 :
            case R.id.rl_sliptunjangan_p1 :
            case R.id.rl_sliptunjangan_p2 :
            case R.id.rl_sliptunjangan_p3 :
            case R.id.iv_rekening_koran1 :
            case R.id.iv_rekening_koran2 :
            case R.id.iv_slipgaji_p1 :
            case R.id.iv_slipgaji_p2 :
            case R.id.iv_slipgaji_p3 :
            case R.id.iv_sliptunjangan_p1 :
            case R.id.iv_sliptunjangan_p2 :
            case R.id.iv_sliptunjangan_p3 :
                BSUploadFile.displayWithTitle(ActivityDokumenPendapatan.this.getSupportFragmentManager(),this,"");
                break;
            case R.id.tf_periode_akhir_waktu1 :
            case R.id.tf_periode_akhir_waktu2 :
            case R.id.tf_periode_awal_waktu1 :
            case R.id.tf_periode_awal_waktu2 :
            case R.id.tf_periode_gaji_p1 :
            case R.id.tf_periode_gaji_p2 :
            case R.id.tf_periode_gaji_p3 :
            case R.id.tf_tgl_tunjangan_p1 :
            case R.id.tf_tgl_tunjangan_p2 :
            case R.id.tf_tgl_tunjangan_p3 :
            case R.id.et_periode_akhir_waktu1 :
            case R.id.et_periode_akhir_waktu2 :
            case R.id.et_periode_awal_waktu1 :
            case R.id.et_periode_awal_waktu2 :
            case R.id.et_periode_gaji_p1 :
            case R.id.et_periode_gaji_p2 :
            case R.id.et_periode_gaji_p3 :
            case R.id.et_tgl_tunjangan_p1 :
            case R.id.et_tgl_tunjangan_p2 :
            case R.id.et_tgl_tunjangan_p3 :
                dpSKCalendar(v);
        }
    }

    private void dpSKCalendar(View v){
        calLahir = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ls_tanggalLahirPasangan = new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calLahir.set(Calendar.YEAR, year);
                calLahir.set(Calendar.MONTH, month);
                calLahir.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calLahirString = dateClient.format(calLahir.getTime());
                String calLahirString2 = dateClient2.format(calLahir.getTime());
                switch (v.getId()){
                    case R.id.et_periode_akhir_waktu1 :
                    case R.id.tf_periode_akhir_waktu1 :
                        binding.etPeriodeAkhirWaktu1.setText(calLahirString2);
                        break;
                    case R.id.et_periode_akhir_waktu2 :
                    case R.id.tf_periode_akhir_waktu2 :
                        binding.etPeriodeAkhirWaktu2.setText(calLahirString2);
                        break;
                    case R.id.et_periode_awal_waktu1 :
                    case R.id.tf_periode_awal_waktu1 :
                        binding.etPeriodeAwalWaktu1.setText(calLahirString2);
                        break;
                    case R.id.et_periode_awal_waktu2 :
                    case R.id.tf_periode_awal_waktu2 :
                        binding.etPeriodeAwalWaktu2.setText(calLahirString2);
                        break;
                    case R.id.et_periode_gaji_p1 :
                    case R.id.tf_periode_gaji_p1 :
                        binding.etPeriodeGajiP1.setText(calLahirString);
                        break;
                    case R.id.et_periode_gaji_p2 :
                    case R.id.tf_periode_gaji_p2 :
                        binding.etPeriodeGajiP2.setText(calLahirString);
                        break;
                    case R.id.et_periode_gaji_p3 :
                    case R.id.tf_periode_gaji_p3 :
                        binding.etPeriodeGajiP3.setText(calLahirString);
                        break;
                    case R.id.et_tgl_tunjangan_p1 :
                    case R.id.tf_tgl_tunjangan_p1 :
                        binding.etTglTunjanganP1.setText(calLahirString);
                        break;
                    case R.id.et_tgl_tunjangan_p2 :
                    case R.id.tf_tgl_tunjangan_p2 :
                        binding.etTglTunjanganP2.setText(calLahirString);
                        break;
                    case R.id.et_tgl_tunjangan_p3 :
                    case R.id.tf_tgl_tunjangan_p3 :
                        binding.etTglTunjanganP3.setText(calLahirString);
                        break;
                }
            }
        };

        dpSK = new DatePickerDialog(ActivityDokumenPendapatan.this, R.style.AppTheme_TimePickerTheme, ls_tanggalLahirPasangan, calLahir.get(Calendar.YEAR),
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
