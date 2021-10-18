package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_ideb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityEditIdebBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataIdeb;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;

import java.util.ArrayList;
import java.util.List;

public class EditIdebActivity extends AppCompatActivity implements  View.OnClickListener, GenericListenerOnSelect {

    private PrapenAoActivityEditIdebBinding binding;
    private List<MGenericModel> dataDropdownTreatmentPembiayaan =new ArrayList<>();
    private DataIdeb dataIdeb;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PrapenAoActivityEditIdebBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Perlakuan IDEB");

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
                Toast.makeText(this, "Nit Not, menyimpan data ideb", Toast.LENGTH_SHORT).show();

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
        dataDropdownTreatmentPembiayaan.add(new MGenericModel("Nasabah Menginfokan pembiayaan sudah Lunas/Selesai","Nasabah merasa pembiayaan sudah Lunas/Selesai"));
        dataDropdownTreatmentPembiayaan.add(new MGenericModel("Nasabah Menginfokan tidak memiliki pembiayaan tersebut","Nasabah merasa tidak memiliki pembiayaan tersebut"));
        dataDropdownTreatmentPembiayaan.add(new MGenericModel("Nasabah Menginfokan Membayar Tepat Waktu","Nasabah Merasa Membayar Tepat Waktu"));


    }


    private void allOnChange(){
        binding.etPerkiraanAngsuranBulanan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etPerkiraanAngsuranBulanan));
        binding.etBakiDebet.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etBakiDebet));
    }

    private void setData(){
        dataIdeb=(DataIdeb)getIntent().getSerializableExtra("dataIdeb");
        binding.etNamaLembagaKeuangan.setText(dataIdeb.getNamaLembagaKeuangan());
        binding.etBakiDebet.setText(dataIdeb.getBakiDebet());
        binding.etKualitasPembiayaan.setText(dataIdeb.getKualitasPembiayaan());
        binding.etPerkiraanAngsuranBulanan.setText(dataIdeb.getPerkiraanAngsuranBulanan());
        binding.etTreatmentPembiayaan.setText(dataIdeb.getTreatmentPembiayaan());
    }
}