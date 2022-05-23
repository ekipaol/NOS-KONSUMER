package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_pendapatan;

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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseLogicalDoc;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.DokumenPendapatan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDocument;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MParseDataUpdateVerifikasi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.databinding.FragmentVerifikasiPendapatanBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.dokumen_pendapatan.ActivityDokumenPendapatan;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentVerifikasiPendapatan extends Fragment implements Step, GenericListenerOnSelect, View.OnClickListener, CameraListener {
    private FragmentVerifikasiPendapatanBinding binding;
    private DatePickerDialog dpSK;
    private MParseDataUpdateVerifikasi dp;
    private Calendar calLahir;
    private JsonObject data;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    List<MGenericModel> dataDropdownPendapatan = new ArrayList<>();
    ReqDocument FileHasilVerifPensiun = new ReqDocument(), FileHasilVerifTotal = new ReqDocument();

    public FragmentVerifikasiPendapatan(JsonObject object) {
        data = object;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentVerifikasiPendapatanBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();
        setParameterDropdown();
//        onclickSelectDialog();
        numberTextEditor();
        chageListener();
        disabledText();
        initialize();
        return view;
    }

    private void initialize() {
        Gson gson = new Gson();
        if (data.get("DataUpdateVerifikasi") != null) {
            String listDataString = data.get("DataUpdateVerifikasi").getAsJsonObject().toString();
            dp = gson.fromJson(listDataString, MParseDataUpdateVerifikasi.class);
            if (dp.getCttnHItPendapatanSaatPensD4() != null)
                binding.etCatatanVerifikasi2.setText(dp.getCttnHItPendapatanSaatPensD4());
            if (dp.getCttnTotalendTerverifikasiD4() != null)
                binding.etCatatanVerifikasi1.setText(dp.getCttnTotalendTerverifikasiD4());
            if (dp.getTotalPendTerverifikasiD4() != null)
                binding.etTotalPendapatan.setText(String.valueOf(dp.getTotalPendTerverifikasiD4()));
            if (dp.getNomorRekeningTercerminD4() != null)
                binding.etNorekTercermin.setText(dp.getNomorRekeningTercerminD4());
            if (dp.getHasilVerifikasiGajiD4() != null)
                binding.etVerifikasiGaji.setText(String.valueOf(new BigDecimal(dp.getHasilVerifikasiGajiD4()).setScale(2,BigDecimal.ROUND_CEILING)));
            if (dp.getHasilVerifikasiTunjanganD4() != null)
                binding.etVerifikasiTunjangan.setText(String.valueOf(new BigDecimal(dp.getHasilVerifikasiTunjanganD4()).setScale(2,BigDecimal.ROUND_CEILING)));
            if (dp.getPerhitPendapatanSaatPensD4() != null)
                binding.etTotalPendapatanPensiun.setText(dp.getPerhitPendapatanSaatPensD4());
            if (dp.getCerminanGajiDanTunjD4() != null)
                binding.etVerifikasiGajiTercermin.setText(dp.getCerminanGajiDanTunjD4());
            if (dp.getNominalGajiSPAN() != null)
                binding.etNominalGaji.setText(dp.getNominalGajiSPAN());
            if (dp.getTanggalPembayaranPayrollSPAN() != null)
                binding.etTanggalGaji.setText(dp.getTanggalPembayaranPayrollSPAN());
            if (dp.getManfaatPensiunNOS() != null)
                binding.etManfaatPensiun.setText(dp.getManfaatPensiunNOS());
        }
        if (data.get("FileHasilVerifPensiun") != null) {
            String listDataString = data.get("FileHasilVerifPensiun").getAsJsonObject().toString();
            FileHasilVerifPensiun = gson.fromJson(listDataString, ReqDocument.class);
            //Set Image Slip Gaji P2
            try {
                FileHasilVerifPensiun.setImg(FileHasilVerifPensiun.getImg());
                if (FileHasilVerifPensiun.getFileName().substring(FileHasilVerifPensiun.getFileName().length() - 3, FileHasilVerifPensiun.getFileName().length()).equalsIgnoreCase("pdf")) {
                    FileHasilVerifPensiun.setFileName("FileHasilVerifPensiun.png");

                    if(FileHasilVerifPensiun.getImg().length()<10){
                        loadFileJson(FileHasilVerifPensiun.getImg(),binding.ivDokumen2);
                    }
                    else{
                        AppUtil.convertBase64ToFileWithOnClick(getContext(), FileHasilVerifPensiun.getImg(), binding.ivDokumen2, FileHasilVerifPensiun.getFileName());
                    }



                } else {
                    FileHasilVerifPensiun.setFileName("FileHasilVerifTotal.pdf");


                    if(FileHasilVerifPensiun.getImg().length()<10){
                        AppUtil.setImageGlide(getContext(),FileHasilVerifPensiun.getImg(),binding.ivDokumen2);
                    }
                    else{
                        AppUtil.convertBase64ToImage(FileHasilVerifPensiun.getImg(), binding.ivDokumen2);
                    }
                }
            } catch (Exception e) {
                AppUtil.logSecure("error setdata", e.getMessage());
            }

        }
        if (data.get("FileHasilVerifTotal") != null) {
            String listDataString = data.get("FileHasilVerifTotal").getAsJsonObject().toString();
            FileHasilVerifTotal = gson.fromJson(listDataString, ReqDocument.class);

            //Set Image Slip Gaji P2
            try {
                FileHasilVerifTotal.setImg(FileHasilVerifTotal.getImg());
                if (FileHasilVerifTotal.getFileName().substring(FileHasilVerifTotal.getFileName().length() - 3, FileHasilVerifTotal.getFileName().length()).equalsIgnoreCase("pdf")) {
                    FileHasilVerifTotal.setFileName("FileHasilVerifTotal.png");

                    if(FileHasilVerifTotal.getImg().length()<10){
                        loadFileJson(FileHasilVerifTotal.getImg(),binding.ivDokumen1);
                    }
                    else{
                        AppUtil.convertBase64ToFileWithOnClick(getContext(), FileHasilVerifTotal.getImg(), binding.ivDokumen1, FileHasilVerifTotal.getFileName());
                    }

                } else {
                    FileHasilVerifTotal.setFileName("FileHasilVerifTotal.pdf");

                    if(FileHasilVerifTotal.getImg().length()<10){
                        AppUtil.setImageGlide(getContext(),FileHasilVerifTotal.getImg(),binding.ivDokumen1);
                    }
                    else{
                        AppUtil.convertBase64ToImage(FileHasilVerifTotal.getImg(), binding.ivDokumen1);
                    }


                }
            } catch (Exception e) {
                AppUtil.logSecure("error setdata", e.getMessage());
            }
        }
    }

    private void disabledText() {
        binding.etTanggalGaji.setFocusable(false);
        binding.etManfaatPensiun.setFocusable(false);
        binding.etNominalGaji.setFocusable(false);
        binding.etVerifikasiGajiTercermin.setFocusable(false);
        binding.etCatatanVerifikasi2.setFocusable(false);
        binding.etCatatanVerifikasi1.setFocusable(false);
        binding.etTotalPendapatan.setFocusable(false);
        binding.etNorekTercermin.setFocusable(false);
        binding.etVerifikasiGaji.setFocusable(false);
        binding.etVerifikasiTunjangan.setFocusable(false);
        binding.etTotalPendapatanPensiun.setFocusable(false);
        binding.etVerifikasiGajiTercermin.setFocusable(false);
        binding.btnDokumen1.setVisibility(View.GONE);
        binding.btnDokumen2.setVisibility(View.GONE);
    }

    private void chageListener() {
        binding.etVerifikasiGaji.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                BigDecimal vergaji = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etVerifikasiGaji.getText().toString()));
//                BigDecimal vertunjangan = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etVerifikasiTunjangan.getText().toString()));
//                BigDecimal totalRpc = (vergaji.add(vertunjangan));
//                binding.etTotalPendapatan.setText(String.valueOf(totalRpc));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });

        binding.etVerifikasiTunjangan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                BigDecimal vergaji = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etVerifikasiGaji.getText().toString()));
//                BigDecimal vertunjangan = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etVerifikasiTunjangan.getText().toString()));
//                BigDecimal totalRpc = (vergaji.add(vertunjangan));
//                binding.etTotalPendapatan.setText(String.valueOf(totalRpc));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
    }

    private void numberTextEditor() {
        binding.etNominalGaji.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etNominalGaji));
        binding.etTotalPendapatan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalPendapatan));
        binding.etTotalPendapatanPensiun.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalPendapatanPensiun));
        binding.etVerifikasiGaji.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etVerifikasiGaji));
        binding.etVerifikasiTunjangan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etVerifikasiTunjangan));
        binding.etManfaatPensiun.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etManfaatPensiun));
    }


    private void onclickSelectDialog() {
        binding.btnDokumen1.setOnClickListener(this);
        binding.btnDokumen2.setOnClickListener(this);
        binding.ivDokumen1.setOnClickListener(this);
        binding.ivDokumen2.setOnClickListener(this);
        binding.rlDokumen1.setOnClickListener(this);
        binding.rlDokumen2.setOnClickListener(this);
        binding.etTanggalGaji.setOnClickListener(this);
        binding.tfTanggalGaji.setOnClickListener(this);
        binding.tfTanggalGaji.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpSKCalendar(v);
            }
        });
        binding.tfVerifikasiGajiTercermin.setOnClickListener(this);
        binding.etVerifikasiGajiTercermin.setOnClickListener(this);
        binding.tfVerifikasiGajiTercermin.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogGenericDataFromService.display(getFragmentManager(), binding.tfVerifikasiGajiTercermin.getLabelText(), dataDropdownPendapatan, FragmentVerifikasiPendapatan.this);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dokumen1:
            case R.id.btn_dokumen2:
            case R.id.rl_dokumen1:
            case R.id.rl_dokumen2:
            case R.id.iv_dokumen1:
            case R.id.iv_dokumen2:
                BSUploadFile.displayWithTitle(getActivity().getSupportFragmentManager(), this, "");
                break;
            case R.id.et_tanggal_gaji:
            case R.id.tf_tanggal_gaji:
                dpSKCalendar(v);
                break;
            case R.id.tf_verifikasi_gaji_tercermin:
            case R.id.et_verifikasi_gaji_tercermin:
                DialogGenericDataFromService.display(getFragmentManager(), binding.tfVerifikasiGajiTercermin.getLabelText(), dataDropdownPendapatan, FragmentVerifikasiPendapatan.this);
                break;
        }
    }

    private void setParameterDropdown() {
        //dropdown kalkulator
        dataDropdownPendapatan.add(new MGenericModel("1", "Tercermin"));
        dataDropdownPendapatan.add(new MGenericModel("2", "Tercermin tapi nominal lebih rendah"));
        dataDropdownPendapatan.add(new MGenericModel("3", "Tercermin tapi Nominal lebih Tinggi"));
        dataDropdownPendapatan.add(new MGenericModel("4", "Tidak tercermin direkening"));

    }


    public void onSelect(String title, MGenericModel data) {
        if (title.equalsIgnoreCase(binding.tfVerifikasiGajiTercermin.getLabelText())) {
            binding.etVerifikasiGajiTercermin.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfVerifikasiGajiTercermin.getLabelText())) {
            binding.etVerifikasiGajiTercermin.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfVerifikasiGajiTercermin.getLabelText())) {
            binding.etVerifikasiGajiTercermin.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfVerifikasiGajiTercermin.getLabelText())) {
            binding.etVerifikasiGajiTercermin.setText(data.getDESC());
        }
    }

    private void dpSKCalendar(View v) {
        calLahir = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ls_tanggalLahirPasangan = new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calLahir.set(Calendar.YEAR, year);
                calLahir.set(Calendar.MONTH, month);
                calLahir.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calLahirString = dateClient.format(calLahir.getTime());
                binding.etTanggalGaji.setText(calLahirString);
            }
        };

        dpSK = new DatePickerDialog(getContext(), R.style.AppTheme_TimePickerTheme, ls_tanggalLahirPasangan, calLahir.get(Calendar.YEAR),
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

    public void loadFileJson(String idFoto, ImageView imageView) {
        ApiClientAdapter apiClientAdapter=new ApiClientAdapter(getContext());
        Call<ParseResponseLogicalDoc> call = apiClientAdapter.getApiInterface().getFileJson(idFoto);
        call.enqueue(new Callback<ParseResponseLogicalDoc>() {
            @Override
            public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
                if (response.isSuccessful()) {
                    if (response.body().getBinaryData()!=null){
                        AppUtil.convertBase64ToFileWithOnClick(getContext(),response.body().getBinaryData(),imageView,response.body().getFileName());
                    }
                    else{
                        AppUtil.notiferror(getContext(),getActivity().findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                    }


                }
                else{
                    AppUtil.notiferror(getContext(),getActivity().findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                AppUtil.notiferror(getContext(),getActivity().findViewById(android.R.id.content), "Terjadi kesalahan");
                
                t.printStackTrace();
            }
        });

    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {

    }
    
    
}
