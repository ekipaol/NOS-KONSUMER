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
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityVerifikasiTempatKerjaBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataJangkaWaktu;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataVerifikasiTempatKerja;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.simulasi_angsuran.kalkulatorsimulasiangsuran;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_jangka_waktu.ActivityJangkaWaktuPembiayaan;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.BuildConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifikasiTempatKerjaActivity extends AppCompatActivity implements View.OnClickListener, CameraListener, GenericListenerOnSelect, KeyValueListener {
    private ActivityVerifikasiTempatKerjaBinding binding;
    List<MGenericModel> dataDropdownTempatKerja = new ArrayList<>(), dataDropdownLGNP = new ArrayList<>();

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private DataVerifikasiTempatKerja dataVerifikasiTempatKerja;
    private long idAplikasi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifikasiTempatKerjaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        apiClientAdapter=new ApiClientAdapter(this);
        appPreferences=new AppPreferences(this);
        idAplikasi=Long.parseLong(getIntent().getStringExtra("idAplikasi"));
        setContentView(view);
        onclickSelectDialog();
        setParameterDropdown();
        allOnclick();
        onClickEndIcon();
        onclickSelectDialog();
        setContentView(view);
        backgroundStatusBar();
        disableEditText();
        loadData();
        AppUtil.toolbarRegular(this, "Verifikasi Tempat Kerja");
    }


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

        binding.rlUploadDokumen.setOnClickListener(this);
        binding.btnUploadDokumen.setOnClickListener(this);
        binding.ivUploadDokumen.setOnClickListener(this);

        binding.btnCekLngp.setOnClickListener(this);
        binding.btnSend.setOnClickListener(this);

    }

    private void setDataFirstTime(){
        binding.etNamaInstansi.setText(dataVerifikasiTempatKerja.getInstansiDapen().getNamaInstansi());
        binding.etMenggunakanLngp.setText(dataVerifikasiTempatKerja.getInstansiDapen().getIsLNGP());
        binding.etInputLngp.setText(dataVerifikasiTempatKerja.getInstansiDapen().getNoLNGP());
        binding.etNamaInstansiLngp.setText(dataVerifikasiTempatKerja.getInstansiDapen().getNamaInstansiLNGP());
        binding.etRateLngp.setText(Double.toString(dataVerifikasiTempatKerja.getInstansiDapen().getRateLNGP()));
        binding.etKotaTempatBekerja.setText(dataVerifikasiTempatKerja.getInstansiDapen().getKotaTempatBekerja());
        binding.etPerkiraanGaji.setText(dataVerifikasiTempatKerja.getInstansiDapen().getPerkiraanGaji());
        binding.etPerkiraanTunjangan.setText(dataVerifikasiTempatKerja.getInstansiDapen().getPerkiraanTunjangan());
        binding.etTotalPendapatan.setText(dataVerifikasiTempatKerja.getInstansiDapen().getTotalPendapatan());

        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getKTPNasabah().getFile_Name(),binding.ivKtpNasabah,dataVerifikasiTempatKerja.getKTPNasabah().getImg());
        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getKTPPasangan().getFile_Name(),binding.ivKtpPasangan,dataVerifikasiTempatKerja.getKTPPasangan().getImg());
        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getSKPensiun().getFile_Name(),binding.ivSkPensiun,dataVerifikasiTempatKerja.getSKPensiun().getImg());
        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getSKPengangkatan().getFile_Name(),binding.ivSkPengangkatan,dataVerifikasiTempatKerja.getSKPengangkatan().getImg());
        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getSKTerakhir().getFile_Name(),binding.ivSkTerakhir,dataVerifikasiTempatKerja.getSKTerakhir().getImg());
        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getIDCard().getFile_Name(),binding.ivIdCard,dataVerifikasiTempatKerja.getIDCard().getImg());
        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getTempatKerjaFotoSuratRekomendasiInstansi().getFile_Name(),binding.ivSuratRekomendasiInstansi,dataVerifikasiTempatKerja.getTempatKerjaFotoSuratRekomendasiInstansi().getImg());
        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getFormAplikasi().getFile_Name(),binding.ivFormApplikasi,dataVerifikasiTempatKerja.getFormAplikasi().getImg());
        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getAset().getFile_Name(),binding.ivAset,dataVerifikasiTempatKerja.getAset().getImg());

    }
    private void setDataAfterUpdate(){

        binding.etNamaInstansi2.setText(dataVerifikasiTempatKerja.getTempatKerja().getNamaInstansiTaspen());
        binding.etNamaInstansi3.setText(dataVerifikasiTempatKerja.getTempatKerja().getNamaInstansiSPAN());
        binding.etKesimpulanPensesuaianVerifikasi.setText(dataVerifikasiTempatKerja.getTempatKerja().getKesimpulan());
        binding.etCatatanhasilverifikasi.setText(dataVerifikasiTempatKerja.getTempatKerja().getHasilVerifikasi());

        binding.etNamaInstansi.setText(dataVerifikasiTempatKerja.getTempatKerja().getNamaInstansi());
        binding.etNamaInstansi.setText(dataVerifikasiTempatKerja.getTempatKerja().getNamaInstansi());
        binding.etMenggunakanLngp.setText(dataVerifikasiTempatKerja.getTempatKerja().getIsLNGP());
        binding.etInputLngp.setText(dataVerifikasiTempatKerja.getTempatKerja().getNoLNGP());
        binding.etNamaInstansiLngp.setText(dataVerifikasiTempatKerja.getTempatKerja().getNamaInstansiLNGP());
        binding.etRateLngp.setText(Double.toString(dataVerifikasiTempatKerja.getTempatKerja().getRateLNGP()));
        binding.etKotaTempatBekerja.setText(dataVerifikasiTempatKerja.getTempatKerja().getKotaTempatBekerja());
        binding.etPerkiraanGaji.setText(dataVerifikasiTempatKerja.getTempatKerja().getPerkiraanGaji());
        binding.etPerkiraanTunjangan.setText(dataVerifikasiTempatKerja.getTempatKerja().getPerkiraanTunjangan());
        binding.etTotalPendapatan.setText(dataVerifikasiTempatKerja.getTempatKerja().getTotalPendapatan());

        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getKTPNasabah().getFile_Name(),binding.ivKtpNasabah,dataVerifikasiTempatKerja.getKTPNasabah().getImg());
        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getKTPPasangan().getFile_Name(),binding.ivKtpPasangan,dataVerifikasiTempatKerja.getKTPPasangan().getImg());
        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getSKPensiun().getFile_Name(),binding.ivSkPensiun,dataVerifikasiTempatKerja.getSKPensiun().getImg());
        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getSKPengangkatan().getFile_Name(),binding.ivSkPengangkatan,dataVerifikasiTempatKerja.getSKPengangkatan().getImg());
        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getSKTerakhir().getFile_Name(),binding.ivSkTerakhir,dataVerifikasiTempatKerja.getSKTerakhir().getImg());
        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getIDCard().getFile_Name(),binding.ivIdCard,dataVerifikasiTempatKerja.getIDCard().getImg());
        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getTempatKerjaFotoSuratRekomendasiInstansi().getFile_Name(),binding.ivSuratRekomendasiInstansi,dataVerifikasiTempatKerja.getTempatKerjaFotoSuratRekomendasiInstansi().getImg());
        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getFormAplikasi().getFile_Name(),binding.ivFormApplikasi,dataVerifikasiTempatKerja.getFormAplikasi().getImg());
        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getAset().getFile_Name(),binding.ivAset,dataVerifikasiTempatKerja.getAset().getImg());
        checkImgOrPdfThenSetData(dataVerifikasiTempatKerja.getTempatKerjaDokumen().getFile_Name(),binding.ivUploadDokumen,dataVerifikasiTempatKerja.getTempatKerjaDokumen().getImg());
    }

    private void checkImgOrPdfThenSetData(String fileName, ImageView imageView,String base64String){
        try{
            if(fileName.substring(fileName.length()-3,fileName.length()).equalsIgnoreCase("pdf")){

                AppUtil.convertBase64ToFileWithOnClick(VerifikasiTempatKerjaActivity.this,base64String,imageView,fileName);
            }
            else{
                AppUtil.convertBase64ToImage(base64String,imageView);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadData(){
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        req.setApplicationId(idAplikasi);
        req.setUID(Integer.toString(appPreferences.getUid()));
        //pantekan no aplikasi
//        Toast.makeText(this, "ada pantekan id aplikasi", Toast.LENGTH_SHORT).show();
//        req.setApplicationId(4);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryTempatKerja(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<DataVerifikasiTempatKerja>() {
                        }.getType();
                        dataVerifikasiTempatKerja =  gson.fromJson(listDataString, type);
                        if(dataVerifikasiTempatKerja!=null&&dataVerifikasiTempatKerja.getTempatKerja()==null){
                            setDataFirstTime();
                        }
                        else{
                            setDataAfterUpdate();
                        }
                    }
                    else if (response.body().getStatus().equalsIgnoreCase("01")) {
                        AppUtil.notiferror(VerifikasiTempatKerjaActivity.this, findViewById(android.R.id.content), "Data Belum Pernah Disimpan Sebellumnya, Silahkan Diisi");
                    }
                    else{
                        AppUtil.notiferror(VerifikasiTempatKerjaActivity.this, findViewById(android.R.id.content), response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(VerifikasiTempatKerjaActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
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