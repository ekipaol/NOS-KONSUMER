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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.databinding.FragmentVerifikasiPendapatanBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FragmentVerifikasiPendapatan extends Fragment  implements Step, GenericListenerOnSelect, View.OnClickListener, CameraListener {
    private FragmentVerifikasiPendapatanBinding binding;
    private DatePickerDialog dpSK;
    private Calendar calLahir;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    List<MGenericModel> dataDropdownPendapatan = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentVerifikasiPendapatanBinding.inflate(getLayoutInflater(),container,false);
        View view = binding.getRoot();
        setParameterDropdown();
        onclickSelectDialog();
        numberTextEditor();
        chageListener();
        disabledText();
        return view;
    }
    
    private void disabledText(){
        binding.etTanggalGaji.setFocusable(false);
        binding.etVerifikasiGajiTercermin.setFocusable(false);
    }

    private void chageListener(){
        binding.etVerifikasiGaji.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                BigDecimal vergaji = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString( binding.etVerifikasiGaji.getText().toString()));
                BigDecimal vertunjangan= new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etVerifikasiTunjangan.getText().toString()));
                BigDecimal totalRpc=(vergaji.add(vertunjangan));
                binding.etTotalPendapatan.setText(String.valueOf(totalRpc));
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
                BigDecimal vergaji = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString( binding.etVerifikasiGaji.getText().toString()));
                BigDecimal vertunjangan= new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etVerifikasiTunjangan.getText().toString()));
                BigDecimal totalRpc=(vergaji.add(vertunjangan));
                binding.etTotalPendapatan.setText(String.valueOf(totalRpc));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
    }

    private void numberTextEditor(){
        binding.etNominalGaji.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etNominalGaji));
        binding.etTotalPendapatan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalPendapatan));
        binding.etTotalPendapatanPensiun.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalPendapatanPensiun));
        binding.etVerifikasiGaji.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etVerifikasiGaji));
        binding.etVerifikasiTunjangan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etVerifikasiTunjangan));
    }


    private void onclickSelectDialog(){
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
                DialogGenericDataFromService.display(getFragmentManager(),binding.tfVerifikasiGajiTercermin.getLabelText(),dataDropdownPendapatan, FragmentVerifikasiPendapatan.this);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dokumen1 :
            case R.id.btn_dokumen2 :
            case R.id.rl_dokumen1 :
            case R.id.rl_dokumen2 :
            case R.id.iv_dokumen1 :
            case R.id.iv_dokumen2 :
                BSUploadFile.displayWithTitle( getActivity().getSupportFragmentManager(),this,"");
                break;
            case R.id.et_tanggal_gaji:
            case R.id.tf_tanggal_gaji:
                dpSKCalendar(v);
                break;
            case R.id.tf_verifikasi_gaji_tercermin:
            case R.id.et_verifikasi_gaji_tercermin:
                DialogGenericDataFromService.display(getFragmentManager(),binding.tfVerifikasiGajiTercermin.getLabelText(),dataDropdownPendapatan, FragmentVerifikasiPendapatan.this);
                break;
        }
    }

    private void setParameterDropdown(){
        //dropdown kalkulator
        dataDropdownPendapatan.add(new MGenericModel("1","Tercermin"));
        dataDropdownPendapatan.add(new MGenericModel("2","Tercermin tapi nominal lebih rendah"));
        dataDropdownPendapatan.add(new MGenericModel("3","Tercermin tapi Nominal lebih Tinggi"));
        dataDropdownPendapatan.add(new MGenericModel("4","Tidak tercermin direkening"));

    }


    public void onSelect(String title, MGenericModel data) {
        if(title.equalsIgnoreCase(binding.tfVerifikasiGajiTercermin.getLabelText())) {
            binding.etVerifikasiGajiTercermin.setText(data.getDESC());
        }else if (title.equalsIgnoreCase(binding.tfVerifikasiGajiTercermin.getLabelText())) {
            binding.etVerifikasiGajiTercermin.setText(data.getDESC());
        }else if (title.equalsIgnoreCase(binding.tfVerifikasiGajiTercermin.getLabelText())) {
            binding.etVerifikasiGajiTercermin.setText(data.getDESC());
        }else if (title.equalsIgnoreCase(binding.tfVerifikasiGajiTercermin.getLabelText())) {
            binding.etVerifikasiGajiTercermin.setText(data.getDESC());
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
