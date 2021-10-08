package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g1_akad_dan_asesoir.field_ojk_bi;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityFieldOjkBiBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogListSektorEkonomi;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.SektorEkonomiListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MsektorEkonomi;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class ActivityFieldOjkBI extends AppCompatActivity implements View.OnClickListener, GenericListenerOnSelect, SektorEkonomiListener {
    private ActivityFieldOjkBiBinding binding;
    List<MGenericModel> dataDropdownGraduate = new ArrayList<>(), dataDropdownKodePekerjaan = new ArrayList<>(), dataDropdownKodeBUTK = new ArrayList<>(), dataDropdownJepen = new ArrayList<>();
    String dataListSektorEkonomi = String.valueOf(new ArrayList<>());
    private String title;
    private int idAplikasi;

    @SuppressWarnings("unused")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFieldOjkBiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        disableEditText();
        endIconClick();
        setParameterDropdown();
        onclickSelectDialog();
        allOnclick();
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "OJK / BI");
    }

    private void onclickSelectDialog() {
        binding.etPendidikanTerakhir.setOnClickListener(this);
        binding.etKodePekerjaan.setOnClickListener(this);
        binding.etKodeBidangUsahaTempatKerja.setOnClickListener(this);
        binding.etAlamatTempatKerja.setOnClickListener(this);
        binding.etJumlahTanggunganNasabah.setOnClickListener(this);
        binding.etKodeJenisPenggunaan.setOnClickListener(this);
        binding.etKodeSektorEkonomi.setOnClickListener(this);

        binding.tfPendidikanTerakhir.setOnClickListener(this);
        binding.tfKodePekerjaan.setOnClickListener(this);
        binding.tfPendidikanTerakhir.setOnClickListener(this);
        binding.tfKodeBidangUsahaTempatKerja.setOnClickListener(this);
        binding.tfAlamatTempatKerja.setOnClickListener(this);
        binding.tfJumlahTanggunganNasabah.setOnClickListener(this);
        binding.tfKodeJenisPenggunaan.setOnClickListener(this);
        binding.tfKodeSektorEkonomi.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_pendidikan_terakhir:
            case R.id.tf_pendidikan_terakhir:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfPendidikanTerakhir.getLabelText(), dataDropdownGraduate, ActivityFieldOjkBI.this);
                break;
            case R.id.et_kode_pekerjaan:
            case R.id.tf_kode_pekerjaan:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfKodePekerjaan.getLabelText(), dataDropdownKodePekerjaan, ActivityFieldOjkBI.this);
                break;
            case R.id.et_kode_bidang_usaha_tempat_kerja:
            case R.id.tf_kode_bidang_usaha_tempat_kerja:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfKodeBidangUsahaTempatKerja.getLabelText(), dataDropdownKodeBUTK, ActivityFieldOjkBI.this);
                break;
            case R.id.et_kode_jenis_penggunaan:
            case R.id.tf_kode_jenis_penggunaan:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfKodeJenisPenggunaan.getLabelText(), dataDropdownJepen, ActivityFieldOjkBI.this);
                break;
            case R.id.et_kode_sektor_ekonomi:
            case R.id.tf_kode_sektor_ekonomi:
                DialogListSektorEkonomi.display(getSupportFragmentManager(), idAplikasi, binding.tfKodeSektorEkonomi.getLabelText(), dataListSektorEkonomi, ActivityFieldOjkBI.this);
                break;

        }
    }

    public void onSelect(String title, MGenericModel data) {
        if (title.equalsIgnoreCase(binding.tfPendidikanTerakhir.getLabelText())) {
            binding.etPendidikanTerakhir.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfKodePekerjaan.getLabelText())) {
            binding.etKodePekerjaan.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfKodeBidangUsahaTempatKerja.getLabelText())) {
            binding.etKodeBidangUsahaTempatKerja.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfKodeJenisPenggunaan.getLabelText())) {
            binding.etKodeJenisPenggunaan.setText(data.getDESC());

        }
    }

    private void allOnclick() {
        binding.etPendidikanTerakhir.setOnClickListener(this);
        binding.etKodePekerjaan.setOnClickListener(this);
        binding.etKodeBidangUsahaTempatKerja.setOnClickListener(this);
        binding.etKodeJenisPenggunaan.setOnClickListener(this);
        binding.etKodeSektorEkonomi.setOnClickListener(this);

        binding.tfPendidikanTerakhir.setOnClickListener(this);
        binding.tfKodePekerjaan.setOnClickListener(this);
        binding.tfKodeBidangUsahaTempatKerja.setOnClickListener(this);
        binding.tfKodeJenisPenggunaan.setOnClickListener(this);
        binding.tfKodeSektorEkonomi.setOnClickListener(this);

    }


    private void endIconClick() {
        binding.tfPendidikanTerakhir.getEndIconImageButton().setOnClickListener(v -> DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfPendidikanTerakhir.getLabelText(), dataDropdownGraduate, ActivityFieldOjkBI.this));
        binding.tfKodePekerjaan.getEndIconImageButton().setOnClickListener(v -> DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfKodePekerjaan.getLabelText(), dataDropdownKodePekerjaan, ActivityFieldOjkBI.this));
        binding.tfKodeBidangUsahaTempatKerja.getEndIconImageButton().setOnClickListener(v -> DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfKodeBidangUsahaTempatKerja.getLabelText(), dataDropdownKodeBUTK, ActivityFieldOjkBI.this));
        binding.tfKodeJenisPenggunaan.getEndIconImageButton().setOnClickListener(v -> DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfKodeJenisPenggunaan.getLabelText(), dataDropdownJepen, ActivityFieldOjkBI.this));
        binding.tfKodeSektorEkonomi.getEndIconImageButton().setOnClickListener(v -> DialogListSektorEkonomi.display(getSupportFragmentManager(), idAplikasi,binding.tfKodeSektorEkonomi .getLabelText(), dataListSektorEkonomi, ActivityFieldOjkBI.this));


    }


    private void setParameterDropdown() {
        //dropdown Pendidikan Terakhir
        dataDropdownGraduate.add(new MGenericModel("1", "SD/sederajat"));
        dataDropdownGraduate.add(new MGenericModel("2", "SMP"));
        dataDropdownGraduate.add(new MGenericModel("3", "SMA"));
        dataDropdownGraduate.add(new MGenericModel("4", "D1"));
        dataDropdownGraduate.add(new MGenericModel("5", "D2"));
        dataDropdownGraduate.add(new MGenericModel("6", "D3"));
        dataDropdownGraduate.add(new MGenericModel("7", "S1"));
        dataDropdownGraduate.add(new MGenericModel("8", "S2"));
        dataDropdownGraduate.add(new MGenericModel("9", "S3"));
        //dropdown Kode Pekerjaan
        dataDropdownKodePekerjaan.add(new MGenericModel("Kode Pekerjaan", ""));
        //dropdown Kode Bidang Usaha Tempat Kerja
        dataDropdownKodeBUTK.add(new MGenericModel("Kode BUTK", ""));
        //dropdown Jenis Penggunaan
        dataDropdownJepen.add(new MGenericModel("3", "Konsumtif"));
        dataDropdownJepen.add(new MGenericModel("1", "Produktif ( modal kerja )"));
        dataDropdownJepen.add(new MGenericModel("2", "Produktif ( investasi )"));

    }

    private void disableEditText() {
        binding.etPendidikanTerakhir.setFocusable(false);
        binding.etKodePekerjaan.setFocusable(false);
        binding.etKodeBidangUsahaTempatKerja.setFocusable(false);
        binding.etKodeJenisPenggunaan.setFocusable(false);
        binding.etKodeSektorEkonomi.setFocusable(false);

        binding.tfPendidikanTerakhir.setFocusable(false);
        binding.tfKodePekerjaan.setFocusable(false);
        binding.tfKodeBidangUsahaTempatKerja.setFocusable(false);
        binding.tfKodeJenisPenggunaan.setFocusable(false);
        binding.tfKodeSektorEkonomi.setFocusable(false);
    }


    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public void onSectorSelect(MsektorEkonomi data) {
        title.equalsIgnoreCase(binding.tfKodeSektorEkonomi.getLabelText()); {
            binding.etKodeSektorEkonomi.setText(data.getSektorEkonomiText());

        }
    }
}