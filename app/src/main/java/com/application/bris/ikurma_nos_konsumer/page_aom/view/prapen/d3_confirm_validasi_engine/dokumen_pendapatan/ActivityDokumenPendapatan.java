package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.dokumen_pendapatan;

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
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;


import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityPendapatanBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;

import java.io.File;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class ActivityDokumenPendapatan extends AppCompatActivity implements GenericListenerOnSelect, View.OnClickListener, CameraListener {

    private ActivityPendapatanBinding binding;
    private DatePickerDialog dpSK;
    private Calendar calLahir;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("MM-yyyy", Locale.US);
    public static SimpleDateFormat dateClient2 = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    SimpleDateFormat formatOutgoing = new SimpleDateFormat("MMMM");
    TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
    List<MGenericModel> dataDropdownPendapatan = new ArrayList<>(),
            dataDropdownPendapatan2 = new ArrayList<>(),
            dataDropdownPendapatan3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPendapatanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        numberText();
        endIconClick();
        onclickSelectDialog();
        setContentView(view);
        backgroundStatusBar();
        setParameterDropdown();
        disableEditText();

        AppUtil.toolbarRegular(this, "Data Dokumen Pendapatan");
    }

    private boolean validateData() {
        if (binding.etGajiBersihP1.getText().toString().trim().isEmpty() || binding.etGajiBersihP1.getText().toString().trim().equalsIgnoreCase("0")) {
            binding.tfGajiBersihP1.setError(binding.tfGajiBersihP1.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfGajiBersihP1.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etPeriodeGajiP1.getText().toString().trim().isEmpty() || binding.etPeriodeGajiP1.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfPeriodeGajiP1.setError(binding.tfPeriodeGajiP1.getLabelText() + " " + getString(R.string.title_validate_field), true);
            binding.etGajiBersihP1.requestFocus();
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfPeriodeGajiP1.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etTunjanganP1.getText().toString().trim().isEmpty() || binding.etTunjanganP1.getText().toString().trim().equalsIgnoreCase("")) {
            binding.tfTunjanganP1.setError(binding.tfTunjanganP1.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfTunjanganP1.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etAkseptasiPendaptan.getText().equals("Hanya Manfaat Pensiun") && binding.etGajiBersihP2.getText().toString().trim().isEmpty() || binding.etAkseptasiPendaptan.getText().equals("Hanya Manfaat Pensiun") && binding.etGajiBersihP2.getText().toString().trim().equalsIgnoreCase("0")) {
            binding.tfGajiBersihP2.setError(binding.tfGajiBersihP2.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfGajiBersihP2.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etAkseptasiPendaptan.getText().equals("Hanya Manfaat Pensiun") && binding.etPeriodeGajiP2.getText().toString().trim().isEmpty() || binding.etAkseptasiPendaptan.getText().equals("Hanya Manfaat Pensiun") && binding.etPeriodeGajiP2.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfPeriodeGajiP2.setError(binding.tfPeriodeGajiP2.getLabelText() + " " + getString(R.string.title_validate_field), true);
            binding.etGajiBersihP2.requestFocus();
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfPeriodeGajiP2.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etAkseptasiPendaptan.getText().equals("Hanya Manfaat Pensiun") && binding.etTunjanganP2.getText().toString().trim().isEmpty() || binding.etAkseptasiPendaptan.getText().equals("Hanya Manfaat Pensiun") && binding.etTunjanganP2.getText().toString().trim().equalsIgnoreCase("")) {
            binding.tfTunjanganP2.setError(binding.tfTunjanganP2.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfTunjanganP2.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etAkseptasiPendaptan.getText().equals("Hanya Manfaat Pensiun") && binding.etGajiBersihP3.getText().toString().trim().isEmpty() || binding.etAkseptasiPendaptan.getText().equals("Hanya Manfaat Pensiun") && binding.etGajiBersihP3.getText().toString().trim().equalsIgnoreCase("0")) {
            binding.tfGajiBersihP3.setError(binding.tfGajiBersihP3.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfGajiBersihP3.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etAkseptasiPendaptan.getText().equals("Hanya Manfaat Pensiun") && binding.etPeriodeGajiP3.getText().toString().trim().isEmpty() || binding.etAkseptasiPendaptan.getText().equals("Hanya Manfaat Pensiun") && binding.etPeriodeGajiP3.getText().toString().trim().equalsIgnoreCase(" ")) {
            binding.tfPeriodeGajiP3.setError(binding.tfPeriodeGajiP3.getLabelText() + " " + getString(R.string.title_validate_field), true);
            binding.etGajiBersihP3.requestFocus();
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfPeriodeGajiP3.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etAkseptasiPendaptan.getText().equals("Hanya Manfaat Pensiun") && binding.etTunjanganP3.getText().toString().trim().isEmpty() || binding.etAkseptasiPendaptan.getText().equals("Hanya Manfaat Pensiun") && binding.etTunjanganP3.getText().toString().trim().equalsIgnoreCase("")) {
            binding.tfTunjanganP3.setError(binding.tfTunjanganP3.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfTunjanganP3.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etVerfikasiGajiTunjangan.getText().toString().trim().isEmpty() || binding.etVerfikasiGajiTunjangan.getText().toString().trim().equalsIgnoreCase("Pilih")) {
            binding.tfVerfikasiGajiTunjangan.setError(binding.tfVerfikasiGajiTunjangan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfVerfikasiGajiTunjangan.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etNorekGaji.getText().toString().trim().isEmpty() || binding.etNorekGaji.getText().toString().trim().equalsIgnoreCase("")) {
            binding.tfNorekGaji.setError(binding.tfNorekGaji.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfNorekGaji.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etVerfikasiRekening.getText().equals("Tidak") && binding.etNorekTunjangan.getText().toString().trim().isEmpty() || binding.etVerfikasiRekening.getText().equals("Tidak") && binding.etNorekTunjangan.getText().toString().trim().equalsIgnoreCase("")) {
            binding.tfNorekTunjangan.setError(binding.tfNorekTunjangan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfNorekTunjangan.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etTotalDebit1.getText().toString().trim().isEmpty() || binding.etTotalDebit1.getText().toString().trim().equalsIgnoreCase("0")) {
            binding.tfTotalDebit1.setError(binding.tfTotalDebit1.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfTotalDebit1.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;

        } else if (binding.etTotalKredit1.getText().toString().trim().isEmpty() || binding.etTotalKredit1.getText().toString().trim().equalsIgnoreCase("0")) {
            binding.tfTotalKredit1.setError(binding.tfTotalKredit1.getLabelText() + " " + getString(R.string.title_validate_field), true);
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), binding.tfTotalKredit1.getLabelText() + " " + getString(R.string.title_validate_field));
            return false;
        } else {
            AppUtil.disableEditTexts(binding.getRoot());
            AppUtil.notiferror(ActivityDokumenPendapatan.this, findViewById(android.R.id.content), "Field Telah Terisi Penuh");
            return false;
        }
    }

    private void numberText() {
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
    }

    private void disableEditText() {
        binding.etVerfikasiGajiTunjangan.setFocusable(false);
        binding.tfVerfikasiGajiTunjangan.setFocusable(false);
        binding.etPeriodeAkhirWaktu1.setFocusable(false);
        binding.etPeriodeAkhirWaktu2.setFocusable(false);
        binding.etPeriodeAwalWaktu1.setFocusable(false);
        binding.etPeriodeAwalWaktu2.setFocusable(false);
        binding.etPeriodeGajiP1.setFocusable(false);
        binding.etPeriodeGajiP2.setFocusable(false);
        binding.etPeriodeGajiP3.setFocusable(false);
        binding.etTglTunjanganP1.setFocusable(false);
        binding.etTglTunjanganP2.setFocusable(false);
        binding.etTglTunjanganP3.setFocusable(false);
        binding.tfPeriodeAkhirWaktu1.setFocusable(false);
        binding.tfPeriodeAkhirWaktu2.setFocusable(false);
        binding.tfPeriodeAwalWaktu1.setFocusable(false);
        binding.tfPeriodeAwalWaktu2.setFocusable(false);
        binding.tfPeriodeGajiP1.setFocusable(false);
        binding.tfPeriodeGajiP2.setFocusable(false);
        binding.tfPeriodeGajiP3.setFocusable(false);
        binding.tfTglTunjanganP1.setFocusable(false);
        binding.tfTglTunjanganP2.setFocusable(false);
        binding.tfTglTunjanganP3.setFocusable(false);
        binding.etAkseptasiPendaptan.setFocusable(false);
        binding.etVerfikasiRekening.setFocusable(false);
        binding.tfAkseptasiPendaptan.setFocusable(false);
        binding.tfVerfikasiRekening.setFocusable(false);
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void setParameterDropdown() {
        dataDropdownPendapatan.add(new MGenericModel("1", "Tercermin"));
        dataDropdownPendapatan.add(new MGenericModel("2", "Tercermin tapi nominal lebih rendah"));
        dataDropdownPendapatan.add(new MGenericModel("3", "Tercermin tapi Nominal lebih Tinggi"));
        dataDropdownPendapatan.add(new MGenericModel("4", "Tidak tercermin direkening"));
        dataDropdownPendapatan2.add(new MGenericModel("1", "Pendapatan Saat Aktif dan Manfaat Pensiun"));
        dataDropdownPendapatan2.add(new MGenericModel("2", "Hanya Manfaat Pensiun"));
        dataDropdownPendapatan3.add(new MGenericModel("1", "Ya"));
        dataDropdownPendapatan3.add(new MGenericModel("2", "Tidak"));
    }

    public void onSelect(String title, MGenericModel data) {
        if (title.equalsIgnoreCase(binding.tfVerfikasiGajiTunjangan.getLabelText())) {
            binding.etVerfikasiGajiTunjangan.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfVerfikasiGajiTunjangan.getLabelText())) {
            binding.etVerfikasiGajiTunjangan.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfVerfikasiGajiTunjangan.getLabelText())) {
            binding.etVerfikasiGajiTunjangan.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfVerfikasiGajiTunjangan.getLabelText())) {
            binding.etVerfikasiGajiTunjangan.setText(data.getDESC());
        }
        if (data.getDESC().equalsIgnoreCase("Ya")) {
            binding.tfNorekTunjangan.setVisibility(View.GONE);
            binding.tfNamaBankTunjangan.setVisibility(View.GONE);
            binding.tfTotalKredit2.setVisibility(View.GONE);
            binding.tfTotalDebit2.setVisibility(View.GONE);
            binding.tfPeriodeAwalWaktu2.setVisibility(View.GONE);
            binding.tfPeriodeAkhirWaktu2.setVisibility(View.GONE);
            binding.norekTunjangan.setVisibility(View.GONE);
            binding.etVerfikasiRekening.setText(data.getDESC());
        } else if (data.getDESC().equalsIgnoreCase("Tidak")) {
            binding.tfNorekTunjangan.setVisibility(View.VISIBLE);
            binding.tfNamaBankTunjangan.setVisibility(View.VISIBLE);
            binding.tfTotalKredit2.setVisibility(View.VISIBLE);
            binding.tfTotalDebit2.setVisibility(View.VISIBLE);
            binding.tfPeriodeAwalWaktu2.setVisibility(View.VISIBLE);
            binding.tfPeriodeAkhirWaktu2.setVisibility(View.VISIBLE);
            binding.norekTunjangan.setVisibility(View.VISIBLE);
            binding.etVerfikasiRekening.setText(data.getDESC());
        }
        if (data.getDESC().equalsIgnoreCase("Pendapatan Saat Aktif dan Manfaat Pensiun")) {
            binding.tfGajiBersihP2.setVisibility(View.VISIBLE);
            binding.rlSlipgajiP2.setVisibility(View.VISIBLE);
            binding.tfPeriodeGajiP2.setVisibility(View.VISIBLE);
            binding.tfTunjanganP2.setVisibility(View.VISIBLE);
            binding.tfTglTunjanganP2.setVisibility(View.VISIBLE);
            binding.rlSliptunjanganP2.setVisibility(View.VISIBLE);
            binding.tp2.setVisibility(View.VISIBLE);
            binding.tpg2.setVisibility(View.VISIBLE);
            binding.tpt2.setVisibility(View.VISIBLE);
            binding.tfGajiBersihP3.setVisibility(View.VISIBLE);
            binding.rlSlipgajiP3.setVisibility(View.VISIBLE);
            binding.tfPeriodeGajiP3.setVisibility(View.VISIBLE);
            binding.tfTunjanganP3.setVisibility(View.VISIBLE);
            binding.tfTglTunjanganP3.setVisibility(View.VISIBLE);
            binding.rlSliptunjanganP3.setVisibility(View.VISIBLE);
            binding.tp3.setVisibility(View.VISIBLE);
            binding.tpg3.setVisibility(View.VISIBLE);
            binding.tpt3.setVisibility(View.VISIBLE);
            binding.etAkseptasiPendaptan.setText(data.getDESC());
        } else if (data.getDESC().equalsIgnoreCase("Hanya Manfaat Pensiun")) {
            binding.tfGajiBersihP2.setVisibility(View.GONE);
            binding.rlSlipgajiP2.setVisibility(View.GONE);
            binding.tfPeriodeGajiP2.setVisibility(View.GONE);
            binding.tfTunjanganP2.setVisibility(View.GONE);
            binding.tfTglTunjanganP2.setVisibility(View.GONE);
            binding.rlSliptunjanganP2.setVisibility(View.GONE);
            binding.tp2.setVisibility(View.GONE);
            binding.tpg2.setVisibility(View.GONE);
            binding.tpt2.setVisibility(View.GONE);
            binding.tfGajiBersihP3.setVisibility(View.GONE);
            binding.rlSlipgajiP3.setVisibility(View.GONE);
            binding.tfPeriodeGajiP3.setVisibility(View.GONE);
            binding.tfTunjanganP3.setVisibility(View.GONE);
            binding.tfTglTunjanganP3.setVisibility(View.GONE);
            binding.rlSliptunjanganP3.setVisibility(View.GONE);
            binding.tp3.setVisibility(View.GONE);
            binding.tpg3.setVisibility(View.GONE);
            binding.tpt3.setVisibility(View.GONE);
            binding.etAkseptasiPendaptan.setText(data.getDESC());
        }
    }

    private void endIconClick() {
        binding.tfVerfikasiGajiTunjangan.getEndIconImageButton().setOnClickListener(v -> DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfVerfikasiGajiTunjangan.getLabelText(), dataDropdownPendapatan, ActivityDokumenPendapatan.this));
        binding.tfAkseptasiPendaptan.getEndIconImageButton().setOnClickListener(v -> DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfAkseptasiPendaptan.getLabelText(), dataDropdownPendapatan2, ActivityDokumenPendapatan.this));
        binding.tfVerfikasiRekening.getEndIconImageButton().setOnClickListener(v -> DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfVerfikasiRekening.getLabelText(), dataDropdownPendapatan3, ActivityDokumenPendapatan.this));
        binding.tfPeriodeAkhirWaktu1.getEndIconImageButton().setOnClickListener(v -> dpSKCalendar(v, binding.etPeriodeAkhirWaktu1, binding.tfPeriodeAkhirWaktu1.getLabelText(), dateClient2));
        binding.tfPeriodeAkhirWaktu2.getEndIconImageButton().setOnClickListener(v -> dpSKCalendar(v, binding.etPeriodeAkhirWaktu2, binding.tfPeriodeAkhirWaktu2.getLabelText(), dateClient2));
        binding.tfPeriodeAwalWaktu1.getEndIconImageButton().setOnClickListener(v -> dpSKCalendar(v, binding.etPeriodeAwalWaktu1, binding.tfPeriodeAwalWaktu1.getLabelText(), dateClient2));
        binding.tfPeriodeAwalWaktu2.getEndIconImageButton().setOnClickListener(v -> dpSKCalendar(v, binding.etPeriodeAwalWaktu2, binding.tfPeriodeAwalWaktu2.getLabelText(), dateClient2));
        binding.tfPeriodeGajiP1.getEndIconImageButton().setOnClickListener(v -> dpSKCalendar(v, binding.etPeriodeGajiP1, binding.tfPeriodeGajiP1.getLabelText(), dateClient));
    }

    private void onclickSelectDialog() {
        binding.etAkseptasiPendaptan.setOnClickListener(this);
        binding.tfAkseptasiPendaptan.setOnClickListener(this);
        binding.tfVerfikasiGajiTunjangan.setOnClickListener(this);
        binding.etVerfikasiGajiTunjangan.setOnClickListener(this);
        binding.btnSend.setOnClickListener(this);
        binding.etPeriodeAkhirWaktu1.setOnClickListener(this);
        binding.etPeriodeAkhirWaktu2.setOnClickListener(this);
        binding.etPeriodeAwalWaktu1.setOnClickListener(this);
        binding.etPeriodeAwalWaktu2.setOnClickListener(this);
        binding.etPeriodeGajiP1.setOnClickListener(this);
        binding.tfPeriodeAkhirWaktu1.setOnClickListener(this);
        binding.tfPeriodeAkhirWaktu2.setOnClickListener(this);
        binding.tfPeriodeAwalWaktu1.setOnClickListener(this);
        binding.tfPeriodeAwalWaktu2.setOnClickListener(this);
        binding.tfPeriodeGajiP1.setOnClickListener(this);
        binding.btnRekeningKoran1.setOnClickListener(this);
        binding.btnSlipgajiP1.setOnClickListener(this);
        binding.btnSlipgajiP2.setOnClickListener(this);
        binding.btnSlipgajiP3.setOnClickListener(this);
        binding.btnSliptunjanganP1.setOnClickListener(this);
        binding.btnSliptunjanganP2.setOnClickListener(this);
        binding.btnSliptunjanganP3.setOnClickListener(this);
        binding.ivRekeningKoran1.setOnClickListener(this);
        binding.ivSlipgajiP1.setOnClickListener(this);
        binding.ivSlipgajiP2.setOnClickListener(this);
        binding.ivSlipgajiP3.setOnClickListener(this);
        binding.ivSliptunjanganP1.setOnClickListener(this);
        binding.ivSliptunjanganP2.setOnClickListener(this);
        binding.ivSliptunjanganP3.setOnClickListener(this);
        binding.rlRekeningKoran1.setOnClickListener(this);
        binding.rlSlipgajiP1.setOnClickListener(this);
        binding.rlSlipgajiP2.setOnClickListener(this);
        binding.rlSlipgajiP3.setOnClickListener(this);
        binding.rlSlipgajiP1.setOnClickListener(this);
        binding.rlSliptunjanganP1.setOnClickListener(this);
        binding.rlSliptunjanganP2.setOnClickListener(this);
        binding.rlSliptunjanganP3.setOnClickListener(this);
        binding.etVerfikasiRekening.setOnClickListener(this);
        binding.tfVerfikasiRekening.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                validateData();
                break;
            case R.id.btn_rekening_koran1:
            case R.id.btn_rekening_koran2:
            case R.id.btn_slipgaji_p1:
            case R.id.btn_slipgaji_p2:
            case R.id.btn_slipgaji_p3:
            case R.id.btn_sliptunjangan_p1:
            case R.id.btn_sliptunjangan_p2:
            case R.id.btn_sliptunjangan_p3:
            case R.id.rl_rekening_koran1:
            case R.id.rl_rekening_koran2:
            case R.id.rl_slipgaji_p1:
            case R.id.rl_slipgaji_p2:
            case R.id.rl_slipgaji_p3:
            case R.id.rl_sliptunjangan_p1:
            case R.id.rl_sliptunjangan_p2:
            case R.id.rl_sliptunjangan_p3:
            case R.id.iv_rekening_koran1:
            case R.id.iv_rekening_koran2:
            case R.id.iv_slipgaji_p1:
            case R.id.iv_slipgaji_p2:
            case R.id.iv_slipgaji_p3:
            case R.id.iv_sliptunjangan_p1:
            case R.id.iv_sliptunjangan_p2:
            case R.id.iv_sliptunjangan_p3:
                BSUploadFile.displayWithTitle(ActivityDokumenPendapatan.this.getSupportFragmentManager(), this, "");
                break;
            case R.id.tf_periode_akhir_waktu1:
            case R.id.et_periode_akhir_waktu1:
                dpSKCalendar(v, binding.etPeriodeAkhirWaktu1, binding.tfPeriodeAkhirWaktu1.getLabelText(), dateClient2);
                break;
            case R.id.tf_periode_akhir_waktu2:
            case R.id.et_periode_akhir_waktu2:
                dpSKCalendar(v, binding.etPeriodeAkhirWaktu2, binding.tfPeriodeAkhirWaktu2.getLabelText(), dateClient2);
                break;
            case R.id.tf_periode_awal_waktu1:
            case R.id.et_periode_awal_waktu1:
                dpSKCalendar(v, binding.etPeriodeAwalWaktu1, binding.tfPeriodeAwalWaktu1.getLabelText(), dateClient2);
                break;
            case R.id.tf_periode_awal_waktu2:
            case R.id.et_periode_awal_waktu2:
                dpSKCalendar(v, binding.etPeriodeAwalWaktu2, binding.tfPeriodeAwalWaktu2.getLabelText(), dateClient2);
                break;
            case R.id.tf_periode_gaji_p1:
            case R.id.et_periode_gaji_p1:
                dpSKCalendar(v, binding.etPeriodeGajiP1, binding.tfPeriodeGajiP1.getLabelText(), dateClient);
                break;
            case R.id.et_verfikasi_gaji_tunjangan:
            case R.id.tf_verfikasi_gaji_tunjangan:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfVerfikasiGajiTunjangan.getLabelText(), dataDropdownPendapatan, ActivityDokumenPendapatan.this);
                break;
            case R.id.et_akseptasi_pendaptan:
            case R.id.tf_akseptasi_pendaptan:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfAkseptasiPendaptan.getLabelText(), dataDropdownPendapatan2, ActivityDokumenPendapatan.this);
                break;
            case R.id.et_verfikasi_rekening:
            case R.id.tf_verfikasi_rekening:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfVerfikasiRekening.getLabelText(), dataDropdownPendapatan3, ActivityDokumenPendapatan.this);
                break;
        }
    }

    public String s;

    private void dpSKCalendar(View v, EditText et, String title, SimpleDateFormat dateT) {
        calLahir = Calendar.getInstance();
        @SuppressLint("NonConstantResourceId") DatePickerDialog.OnDateSetListener ls_tanggalLahirPasangan = (view, year, month, dayOfMonth) -> {
            calLahir.set(Calendar.YEAR, year);
            calLahir.set(Calendar.MONTH, month);
            calLahir.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String calLahirString = dateT.format(calLahir.getTime());
            if (title.equalsIgnoreCase(binding.tfPeriodeGajiP1.getLabelText())) {

                binding.etPeriodeGajiP1.setText(calLahirString);
                binding.etTglTunjanganP1.setText(calLahirString);
                formatOutgoing.setTimeZone(tz);
                s = formatOutgoing.format(calLahir.getTime());
                binding.tp1.setText("Slip gaji " + s);

                calLahir.add(Calendar.MONTH, -1);
                String p2 = dateT.format(calLahir.getTime());
                binding.etPeriodeGajiP2.setText(p2);
                binding.etTglTunjanganP2.setText(p2);
                s = formatOutgoing.format(calLahir.getTime());
                binding.tp2.setText("Slip gaji " + s);

                calLahir.add(Calendar.MONTH, -1);
                String p3 = dateT.format(calLahir.getTime());
                binding.etPeriodeGajiP3.setText(p3);
                binding.etTglTunjanganP3.setText(p3);
                s = formatOutgoing.format(calLahir.getTime());
                binding.tp3.setText("Slip gaji " + s);
            } else {
                et.setText(calLahirString);
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
