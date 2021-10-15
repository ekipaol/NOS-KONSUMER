package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_tempat_kerja;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityVerifikasiTempatKerjaBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.simulasi_angsuran.kalkulatorsimulasiangsuran;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.BuildConfig;

public class VerifikasiTempatKerjaActivity extends AppCompatActivity implements View.OnClickListener, CameraListener, GenericListenerOnSelect, KeyValueListener {
    private ActivityVerifikasiTempatKerjaBinding binding;
    List<MGenericModel> dataDropdownTempatKerja = new ArrayList<>(), dataDropdownLGNP = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifikasiTempatKerjaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        onclickSelectDialog();
        setParameterDropdown();
        allOnclick();
        onClickEndIcon();
        onclickSelectDialog();
        setContentView(view);
        backgroundStatusBar();
        disableEditText();
        AppUtil.toolbarRegular(this, "Verifikasi Tempat Kerja");
    }

//    private void chageListener() {
//        binding.etPerkiraanTunjangan.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                BigDecimal vergaji = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString( binding.etPerkiraanGaji.getText().toString()));
//                BigDecimal vertunjangan= new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etPerkiraanTunjangan.getText().toString()));
//                BigDecimal totalRpc=(vergaji.add(vertunjangan));
//                binding.etTotalPendapatan.setText(String.valueOf(totalRpc));
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//
//
//        });
//
//    }


    private void onclickSelectDialog() {
        binding.tfNamaInstansi.setOnClickListener(this);
        binding.etNamaInstansi.setOnClickListener(this);

        binding.tfMenggunakanLngp.setOnClickListener(this);
        binding.etMenggunakanLngp.setOnClickListener(this);

        binding.tfInputLngp.setOnClickListener(this);
        binding.etInputLngp.setOnClickListener(this);

        binding.tfNamaInstansiLngp.setOnClickListener(this);
        binding.etNamaInstansiLngp.setOnClickListener(this);

        binding.tfRateLngp.setOnClickListener(this);
        binding.etRateLngp.setOnClickListener(this);

        binding.tfKotaTempatBekerja.setOnClickListener(this);
        binding.etKotaTempatBekerja.setOnClickListener(this);

        binding.tfNamaInstansi2.setOnClickListener(this);
        binding.etNamaInstansi2.setOnClickListener(this);

        binding.tfNamaInstansi3.setOnClickListener(this);
        binding.etNamaInstansi3.setOnClickListener(this);

        binding.etCatatanhasilverifikasi.setOnClickListener(this);

        binding.btnSuratRekomendasiInstansi.setOnClickListener(this);
        binding.ivSuratRekomendasiInstansi.setOnClickListener(this);
        binding.rlSuratRekomendasiInstansi.setOnClickListener(this);

        binding.btnResumeDokumenPembiayaan.setOnClickListener(this);
        binding.ivResumeDokumenPembiayaan.setOnClickListener(this);
        binding.rlSuratRekomendasiInstansi.setOnClickListener(this);

        binding.rlUploadDokumen.setOnClickListener(this);
        binding.btnUploadDokumen.setOnClickListener(this);
        binding.ivUploadDokumen.setOnClickListener(this);

        binding.btnCekLngp.setOnClickListener(this);
        binding.btnSend.setOnClickListener(this);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cek_lngp:
            case R.id.btn_send:
                break;

            case R.id.et_menggunakan_lngp:
            case R.id.tf_menggunakan_lngp:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfMenggunakanLngp.getLabelText(), dataDropdownLGNP, VerifikasiTempatKerjaActivity.this);
                break;

            case R.id.et_kesimpulan_pensesuaian_verifikasi:
            case R.id.tf_kesimpulan_pensesuaian_verifikasi:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfKesimpulanPensesuaianVerifikasi.getLabelText(), dataDropdownTempatKerja, VerifikasiTempatKerjaActivity.this);
                break;

            case R.id.rl_upload_dokumen:
            case R.id.rl_surat_rekomendasi_instansi:
            case R.id.iv_upload_dokumen:
            case R.id.iv_surat_rekomendasi_instansi:
            case R.id.btn_upload_dokumen:
            case R.id.btn_surat_rekomendasi_instansi:
                BSUploadFile.displayWithTitle(VerifikasiTempatKerjaActivity.this.getSupportFragmentManager(), this, "");
                break;

            case R.id.rl_resume_dokumen_pembiayaan:
            case R.id.iv_resume_dokumen_pembiayaan:
            case R.id.btn_resume_dokumen_pembiayaan:
                Toast.makeText(getApplicationContext(), "Menampilkan Dokumen Pribadi dan Pasangan",Toast.LENGTH_LONG).show();
                break;
        }
    }


    private void setParameterDropdown() {
        //dropdown Kesimpulan
        dataDropdownTempatKerja.add(new MGenericModel("Telah Sesuai", "Telah Sesuai"));
        dataDropdownTempatKerja.add(new MGenericModel("Tidak Sesuai Kebutuhan", "Tidak Sesuai Kebutuhan"));

        dataDropdownLGNP.add(new MGenericModel("Ya", "Ya"));
        dataDropdownLGNP.add(new MGenericModel("Tidak", "Tidak"));
    }

    private void onClickEndIcon() {
        binding.tfKesimpulanPensesuaianVerifikasi.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfKesimpulanPensesuaianVerifikasi.getLabelText(), dataDropdownTempatKerja, VerifikasiTempatKerjaActivity.this);
            }
        });
        binding.tfMenggunakanLngp.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfMenggunakanLngp.getLabelText(), dataDropdownLGNP, VerifikasiTempatKerjaActivity.this);
            }
        });

    }

    private void disableEditText() {
        binding.etMenggunakanLngp.setFocusable(false);
        binding.etKesimpulanPensesuaianVerifikasi.setFocusable(false);
        binding.etNamaInstansiLngp.setFocusable(false);
        binding.etRateLngp.setFocusable(false);
    }

    private void allOnclick() {
        binding.tfMenggunakanLngp.setOnClickListener(this);
        binding.etMenggunakanLngp.setOnClickListener(this);
        binding.tfKesimpulanPensesuaianVerifikasi.setOnClickListener(this);
        binding.etKesimpulanPensesuaianVerifikasi.setOnClickListener(this);
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

    public void directOpenCamera(int cameraCode) {
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
                BuildConfig BuildConfig = null;
                outputFileUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "fotoagunan.png"));
            } else {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "fotoagunan.png"));
            }
        }
        return outputFileUri;
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }

    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {

    }

    @Override
    public void onSelect(String title, MGenericModel data) {
        if (title.equalsIgnoreCase(binding.tfMenggunakanLngp.getLabelText())) {
            binding.etMenggunakanLngp.setText(data.getDESC());
        } else if(title.equalsIgnoreCase(binding.tfKesimpulanPensesuaianVerifikasi.getLabelText())) {
            binding.etKesimpulanPensesuaianVerifikasi.setText(data.getDESC());
        }
        if (data.getDESC().equalsIgnoreCase("Ya")) {
            binding.tfNamaInstansiLngp.setVisibility(View.VISIBLE);
            binding.tfRateLngp.setVisibility(View.VISIBLE);
            binding.tfInputLngp.setVisibility(View.VISIBLE);
            binding.btnCekLngp.setVisibility(View.VISIBLE);

        } else if (data.getDESC().equalsIgnoreCase("Tidak")) {
            {
                binding.tfNamaInstansiLngp.setVisibility(View.GONE);
                binding.tfRateLngp.setVisibility(View.GONE);
                binding.tfInputLngp.setVisibility(View.GONE);
                binding.btnCekLngp.setVisibility(View.GONE);
            }
//        }else if (title.equalsIgnoreCase(binding.btnCekLngp.getLabelText())) {
//            binding.btnCekLngp.setText(data.getDESC());
//       }else if (title.equalsIgnoreCase(binding.btnSend.getLabelText())) {
//          binding.btnSend.setText(data.getDESC());
        }
    }
}