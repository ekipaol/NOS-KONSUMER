package com.application.bris.ikurma_nos_konsumer.page_simulasi_angsuran;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityKalkulatorSimulasiAngsuranBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;

import java.util.ArrayList;
import java.util.List;

public class kalkulatorsimulasiangsuran extends AppCompatActivity implements GenericListenerOnSelect,View.OnClickListener {
    private ActivityKalkulatorSimulasiAngsuranBinding binding;
    List<MGenericModel> dataDropdownKalkulator = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKalkulatorSimulasiAngsuranBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Kalkulator");

        setParameterDropdown();
        allOnClick();
        onClickEndIcon();
        disableEditText();

        binding.etPlafondYangDibutuhkan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etPlafondYangDibutuhkan));
        binding.etBiayaAsuransi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etBiayaAsuransi));
        binding.etBiayaasuransikhusus.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etBiayaasuransikhusus));
        binding.etBiayaAdministrasi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etBiayaAdministrasi));
        binding.etBiayapenaltiKhususTakeover.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etBiayapenaltiKhususTakeover));
        binding.etBiayamaterai.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etBiayamaterai));
    }
    private void onClickEndIcon() {
        binding.tfTreatmentBiayaAsuransi.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTreatmentBiayaAsuransi.getLabelText(),dataDropdownKalkulator,kalkulatorsimulasiangsuran.this);
            }
        });
        binding.tfTreatmentBiayaAsuransiKhusus.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTreatmentBiayaAsuransiKhusus.getLabelText(),dataDropdownKalkulator,kalkulatorsimulasiangsuran.this);
            }
        });
        binding.tfTreatmentBiayaAdministrasi.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTreatmentBiayaAdministrasi.getLabelText(),dataDropdownKalkulator,kalkulatorsimulasiangsuran.this);
            }
        });
        binding.tfTreatmentBiayaPenalti.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTreatmentBiayaPenalti.getLabelText(),dataDropdownKalkulator,kalkulatorsimulasiangsuran.this);
            }
        });
        binding.tfTreatmentBiayaMaterai.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTreatmentBiayaMaterai.getLabelText(),dataDropdownKalkulator,kalkulatorsimulasiangsuran.this);
            }
        });
    }


    private void setParameterDropdown(){
        //dropdown kalkulator
        dataDropdownKalkulator.add(new MGenericModel("Bayar Nasabah","Bayar Nasabah"));
        dataDropdownKalkulator.add(new MGenericModel("Atribusi","Atribusi"));
        dataDropdownKalkulator.add(new MGenericModel("Potong Pencairan","Potong Pencairan"));


    }

    @Override
    public void onSelect(String title, MGenericModel data) {
        if(title.equalsIgnoreCase(binding.tfTreatmentBiayaAsuransi.getLabelText())) {
            binding.etTreatmentBiayaAsuransi.setText(data.getDESC());
        }else if (title.equalsIgnoreCase(binding.tfTreatmentBiayaAsuransiKhusus.getLabelText())) {
            binding.etTreatmentBiayaAsuransiKhusus.setText(data.getDESC());
        }else if (title.equalsIgnoreCase(binding.tfTreatmentBiayaAdministrasi.getLabelText())) {
            binding.etTreatmentBiayaAdministrasi.setText(data.getDESC());
        }else if (title.equalsIgnoreCase(binding.tfTreatmentBiayaPenalti.getLabelText())) {
            binding.etTreatmentBiayaPenalti.setText(data.getDESC());
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            //treatment_biaya_asuransi
            case R.id.et_treatment_biaya_asuransi:
            case R.id.tf_treatment_biaya_asuransi:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTreatmentBiayaAsuransi.getLabelText(),dataDropdownKalkulator,kalkulatorsimulasiangsuran.this);
                break;

            //treatment_biaya_asuransi_khusus
            case R.id.et_treatment_biaya_asuransi_khusus:
            case R.id.tf_treatment_biaya_asuransi_khusus:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTreatmentBiayaAsuransiKhusus.getLabelText(),dataDropdownKalkulator,kalkulatorsimulasiangsuran.this);
                break;

            //treatment_biaya_administrasi
            case R.id.et_treatment_biaya_administrasi:
            case R.id.tf_treatment_biaya_administrasi:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTreatmentBiayaAdministrasi.getLabelText(),dataDropdownKalkulator,kalkulatorsimulasiangsuran.this);
                break;

            //treatment_biaya_penalti
            case R.id.et_treatment_biaya_penalti:
            case R.id.tf_treatment_biaya_penalti:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTreatmentBiayaPenalti.getLabelText(),dataDropdownKalkulator,kalkulatorsimulasiangsuran.this);
                break;

            //treatment_biaya_materai
            case R.id.et_treatment_biaya_materai:
            case R.id.tf_treatment_biaya_materai:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfTreatmentBiayaMaterai.getLabelText(),dataDropdownKalkulator,kalkulatorsimulasiangsuran.this);
                break;
        }



    }

    private void disableEditText(){
        binding.etTreatmentBiayaAsuransi.setFocusable(false);
        binding.etTreatmentBiayaAsuransiKhusus.setFocusable(false);
        binding.etTreatmentBiayaAdministrasi.setFocusable(false);
        binding.etTreatmentBiayaPenalti.setFocusable(false);
        binding.etTreatmentBiayaMaterai.setFocusable(false);
    }

    private void allOnClick(){
        binding.tfTreatmentBiayaAsuransi.setOnClickListener(this);
        binding.etTreatmentBiayaAsuransi.setOnClickListener(this);
        binding.tfTreatmentBiayaAsuransiKhusus.setOnClickListener(this);
        binding.etTreatmentBiayaAsuransiKhusus.setOnClickListener(this);
        binding.tfTreatmentBiayaAdministrasi.setOnClickListener(this);
        binding.etTreatmentBiayaAdministrasi.setOnClickListener(this);
        binding.tfTreatmentBiayaPenalti.setOnClickListener(this);
        binding.etTreatmentBiayaPenalti.setOnClickListener(this);
        binding.tfTreatmentBiayaMaterai.setOnClickListener(this);
        binding.etTreatmentBiayaMaterai.setOnClickListener(this);
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }
}

