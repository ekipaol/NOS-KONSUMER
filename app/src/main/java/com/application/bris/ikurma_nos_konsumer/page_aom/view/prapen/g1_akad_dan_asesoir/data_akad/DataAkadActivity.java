package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g1_akad_dan_asesoir.data_akad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityDataAkadBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoAsesoirActivityBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;

import java.util.ArrayList;
import java.util.List;

public class DataAkadActivity extends AppCompatActivity implements GenericListenerOnSelect, View.OnClickListener {

    private PrapenAoActivityDataAkadBinding binding;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private List<MGenericModel> dataDropdownAset=new ArrayList<>();
    private List<MGenericModel> dataDropdownDokumenTanah=new ArrayList<>();
    private List<MGenericModel> dataDropdownDokumenTempatTinggal=new ArrayList<>();
    private List<MGenericModel> dataDropdownJenisKendaraan=new ArrayList<>();
    private List<MGenericModel> dataDropdownDokumenKepemilikanKendaraan=new ArrayList<>();
    private String namaAkad="MMQ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PrapenAoActivityDataAkadBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Akad");

        allOnTextChanged();
        setData();
        allOnClicks();
        disableEditTexts();
        isiDropdown();
        defaultViewCondition();

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.DialogBackpress(DataAkadActivity.this);
            }
        });
    }

    private void setData(){
        binding.etAkadPembiayaan.setText(namaAkad);
    }

    private void isiDropdown(){

        //dropdown aset
        dataDropdownAset.add(new MGenericModel("Tanah","Tanah"));
        dataDropdownAset.add(new MGenericModel("Tempat Tinggal","Tempat Tinggal"));
        dataDropdownAset.add(new MGenericModel("Kendaraan","Kendaraan"));
        dataDropdownAset.add(new MGenericModel("Lainnya","Lainnya"));

        //dropdown dokumen tanah
        dataDropdownDokumenTanah.add(new MGenericModel("SHM","SHM"));
        dataDropdownDokumenTanah.add(new MGenericModel("SHMSRS","SHMSRS"));
        dataDropdownDokumenTanah.add(new MGenericModel("Non Sertifikat(Girik)","Non Sertifikat(Girik)"));
        dataDropdownDokumenTanah.add(new MGenericModel("HPL","HPL"));

        //dropdown dokumen tempat tinggal
        dataDropdownDokumenTempatTinggal.add(new MGenericModel("SHM","SHM"));
        dataDropdownDokumenTempatTinggal.add(new MGenericModel("SHMSRS","SHMSRS"));
        dataDropdownDokumenTempatTinggal.add(new MGenericModel("Non Sertifikat(Girik)","Non Sertifikat(Girik)"));
        dataDropdownDokumenTempatTinggal.add(new MGenericModel("HPL","HPL"));

        //dropdown jenis kendaraan
        dataDropdownJenisKendaraan.add(new MGenericModel("Mobil","Mobil"));
        dataDropdownJenisKendaraan.add(new MGenericModel("Motor","Motor"));
        dataDropdownJenisKendaraan.add(new MGenericModel("Kendaraan Usaha","Kendaraan Usaha"));

        //dropdown dokumen kendaraan
        dataDropdownDokumenKepemilikanKendaraan.add(new MGenericModel("BPKB","BPKB"));
        dataDropdownDokumenKepemilikanKendaraan.add(new MGenericModel("STNK","STNK"));

    }


    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public void onBackPressed() {
        CustomDialog.DialogBackpress(DataAkadActivity.this);
    }

    private void allOnClicks(){
        endIconOnClick();
        binding.tfRencanaPenandatangananAkad.setOnClickListener(this);
        binding.etRencanaPenandatangananAkad.setOnClickListener(this);
        binding.tfBulanAngsuranPertama.setOnClickListener(this);
        binding.etBulanAngsuranPertama.setOnClickListener(this);
        binding.tfTanggalAktaNikah.setOnClickListener(this);
        binding.etTanggalAktaNikah.setOnClickListener(this);
        binding.tfTanggalPenilaian.setOnClickListener(this);
        binding.etTanggalPenilaian.setOnClickListener(this);
        binding.tfKepemilikanAset.setOnClickListener(this);
        binding.etKepemilikanAset.setOnClickListener(this);


        binding.tfJenisDokumenTanah.setOnClickListener(this);
        binding.etJenisDokumenTanah.setOnClickListener(this);
        binding.tfJenisDokumenTempatTinggal.setOnClickListener(this);
        binding.etJenisDokumenTempatTinggal.setOnClickListener(this);
        binding.tfJenisKendaraan.setOnClickListener(this);
        binding.etJenisKendaraan.setOnClickListener(this);
        binding.tfDokumenKepemilikanKendaraan.setOnClickListener(this);
        binding.etDokumenKepemilikanKendaraan.setOnClickListener(this);

        binding.btnSimpanDataAkad.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //SUMBER APLIKASI
            case R.id.tf_rencana_penandatanganan_akad:
            case R.id.et_rencana_penandatanganan_akad:
                AppUtil.genericCalendarDialog(DataAkadActivity.this,binding.etRencanaPenandatangananAkad);
                break;
            case R.id.et_bulan_angsuran_pertama:
            case R.id.tf_bulan_angsuran_pertama:
                AppUtil.customCalendarDialog(DataAkadActivity.this,binding.etBulanAngsuranPertama,"mm-yyyy");
                break;
            case R.id.tf_tanggal_akta_nikah:
            case R.id.et_tanggal_akta_nikah:
                AppUtil.genericCalendarDialog(DataAkadActivity.this,binding.etTanggalAktaNikah);
                break;
            case R.id.tf_tanggal_penilaian:
            case R.id.et_tanggal_penilaian:
                AppUtil.genericCalendarDialog(DataAkadActivity.this,binding.etTanggalPenilaian);
                break;
            case R.id.tf_kepemilikan_aset:
            case R.id.et_kepemilikan_aset:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfKepemilikanAset.getLabelText(),dataDropdownAset,DataAkadActivity.this);
                break;
            case R.id.tf_jenis_dokumen_tanah:
            case R.id.et_jenis_dokumen_tanah:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJenisDokumenTanah.getLabelText(),dataDropdownDokumenTanah,DataAkadActivity.this);
                break;
            case R.id.tf_jenis_dokumen_tempat_tinggal:
            case R.id.et_jenis_dokumen_tempat_tinggal:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJenisDokumenTempatTinggal.getLabelText(),dataDropdownDokumenTempatTinggal,DataAkadActivity.this);
                break;
            case R.id.tf_jenis_kendaraan:
            case R.id.et_jenis_kendaraan:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJenisKendaraan.getLabelText(),dataDropdownJenisKendaraan,DataAkadActivity.this);
                break;
            case R.id.tf_dokumen_kepemilikan_kendaraan:
            case R.id.et_dokumen_kepemilikan_kendaraan:
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfDokumenKepemilikanKendaraan.getLabelText(),dataDropdownDokumenKepemilikanKendaraan,DataAkadActivity.this);
                break;
            case R.id.btn_simpan_data_asesoir:
                Toast.makeText(this, "clicking simpan", Toast.LENGTH_SHORT).show();

                break;

            default:break;
        }
    }
    private void endIconOnClick(){
        binding.tfRencanaPenandatangananAkad.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.genericCalendarDialog(DataAkadActivity.this,binding.etRencanaPenandatangananAkad);
            }
        });

        binding.tfBulanAngsuranPertama.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.customCalendarDialog(DataAkadActivity.this,binding.etBulanAngsuranPertama,"mm-yyyy");
            }
        });

        binding.tfTanggalAktaNikah.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.genericCalendarDialog(DataAkadActivity.this,binding.etTanggalAktaNikah);
            }
        });

        binding.tfTanggalPenilaian.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.genericCalendarDialog(DataAkadActivity.this,binding.etTanggalPenilaian);
            }
        });

        binding.tfKepemilikanAset.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfKepemilikanAset.getLabelText(),dataDropdownAset,DataAkadActivity.this);
            }
        });

        binding.tfJenisDokumenTanah.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJenisDokumenTanah.getLabelText(),dataDropdownDokumenTanah,DataAkadActivity.this);
            }
        });

        binding.tfJenisDokumenTempatTinggal.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJenisDokumenTempatTinggal.getLabelText(),dataDropdownDokumenTempatTinggal,DataAkadActivity.this);
            }
        });

        binding.tfKepemilikanAset.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfKepemilikanAset.getLabelText(),dataDropdownAset,DataAkadActivity.this);
            }
        });

        binding.tfJenisKendaraan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfJenisKendaraan.getLabelText(),dataDropdownJenisKendaraan,DataAkadActivity.this);
            }
        });

        binding.tfDokumenKepemilikanKendaraan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),binding.tfDokumenKepemilikanKendaraan.getLabelText(),dataDropdownDokumenKepemilikanKendaraan,DataAkadActivity.this);
            }
        });

    }

    private void disableEditTexts(){
        binding.etRencanaPenandatangananAkad.setFocusable(false);
        binding.etTanggalPenilaian.setFocusable(false);
        binding.etTanggalAktaNikah.setFocusable(false);
        binding.etKepemilikanAset.setFocusable(false);
        binding.etJenisDokumenTanah.setFocusable(false);
        binding.etJenisDokumenTempatTinggal.setFocusable(false);
        binding.etJenisKendaraan.setFocusable(false);
        binding.etDokumenKepemilikanKendaraan.setFocusable(false);

    }

    private void defaultViewCondition(){
        if(namaAkad.equalsIgnoreCase("murabahah")||namaAkad.equalsIgnoreCase("ijarah")){
            binding.llAkadMmq.setVisibility(View.GONE);
            binding.llAkadWakalah.setVisibility(View.VISIBLE);
        }
        else if(namaAkad.equalsIgnoreCase("MMQ")){
            binding.llAkadMmq.setVisibility(View.VISIBLE);
            binding.llAsetLainnya.setVisibility(View.GONE);
            binding.llAsetTanah.setVisibility(View.GONE);
            binding.llAsetTempatTinggal.setVisibility(View.GONE);
            binding.llAsetKendaraan.setVisibility(View.GONE);
            binding.llAkadWakalah.setVisibility(View.GONE);
        }
    }




    @Override
    public void onSelect(String title, MGenericModel data) {
        if(title.equalsIgnoreCase(binding.tfKepemilikanAset.getLabelText())){
            binding.etKepemilikanAset.setText(data.getDESC());
            if(data.getDESC().equalsIgnoreCase("tanah")){
                binding.llAsetTanah.setVisibility(View.VISIBLE);
                binding.llAsetKendaraan.setVisibility(View.GONE);
                binding.llAsetTempatTinggal.setVisibility(View.GONE);
                binding.llAsetLainnya.setVisibility(View.GONE);
            }
           else if(data.getDESC().equalsIgnoreCase("tempat tinggal")){
                binding.llAsetTanah.setVisibility(View.GONE);
                binding.llAsetKendaraan.setVisibility(View.GONE);
                binding.llAsetTempatTinggal.setVisibility(View.VISIBLE);
                binding.llAsetLainnya.setVisibility(View.GONE);
            }
            else if(data.getDESC().equalsIgnoreCase("kendaraan")){
                binding.llAsetTanah.setVisibility(View.GONE);
                binding.llAsetKendaraan.setVisibility(View.VISIBLE);
                binding.llAsetTempatTinggal.setVisibility(View.GONE);
                binding.llAsetLainnya.setVisibility(View.GONE);
            }
            else if(data.getDESC().equalsIgnoreCase("lainnya")){
                binding.llAsetTanah.setVisibility(View.GONE);
                binding.llAsetKendaraan.setVisibility(View.GONE);
                binding.llAsetTempatTinggal.setVisibility(View.GONE);
                binding.llAsetLainnya.setVisibility(View.VISIBLE);
            }
        }
        else if(title.equalsIgnoreCase(binding.tfJenisDokumenTanah.getLabelText())){
            binding.etJenisDokumenTanah.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfJenisDokumenTempatTinggal.getLabelText())){
            binding.etJenisDokumenTempatTinggal.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfJenisKendaraan.getLabelText())){
            binding.etJenisKendaraan.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfDokumenKepemilikanKendaraan.getLabelText())){
            binding.etDokumenKepemilikanKendaraan.setText(data.getDESC());
        }
    }

    private void allOnTextChanged(){
        binding.etHargaTanah.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etHargaTanah));
        binding.etHargaBangunan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etHargaBangunan));
        binding.etHargaKendaraan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etHargaKendaraan));
        binding.etNilaiAsetLainnya.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etNilaiAsetLainnya));
        binding.etHargaTanahTempatTinggal.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etHargaTanahTempatTinggal));

        binding.etTotalNilaiBangunan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalNilaiBangunan));
        binding.etTotalNilaiTanahTempatTinggal.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalNilaiTanahTempatTinggal));
        binding.etTotalNilaiAsetTanah.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalNilaiAsetTanah));
        binding.etTotalNilaiAsetTempatTinggal.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalNilaiAsetTempatTinggal));
        binding.etTotalNilaiTanah.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalNilaiTanah));

        binding.etHargaTanah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!binding.etLuasTanah.getText().toString().isEmpty()&&!binding.etHargaTanah.getText().toString().isEmpty()){
                    //total nilai = luas dikali harga
                    Long totalNilai=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etHargaTanah.getText().toString())))*Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etLuasTanah.getText().toString())));
                    binding.etTotalNilaiTanah.setText(String.valueOf(totalNilai));
                    binding.etTotalNilaiAsetTanah.setText(String.valueOf(totalNilai));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etLuasTanah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!binding.etLuasTanah.getText().toString().isEmpty()&&!binding.etHargaTanah.getText().toString().isEmpty()){
                    //total nilai = luas dikali harga
                    Long totalNilai=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etHargaTanah.getText().toString())))*Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etLuasTanah.getText().toString())));
                    binding.etTotalNilaiTanah.setText(String.valueOf(totalNilai));
                    binding.etTotalNilaiAsetTanah.setText(String.valueOf(totalNilai));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etHargaBangunan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!binding.etLuasBangunan.getText().toString().isEmpty()&&!binding.etHargaBangunan.getText().toString().isEmpty()){
                    //total nilai = luas dikali harga
                    Long totalNilai=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etHargaBangunan.getText().toString())))*Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etLuasBangunan.getText().toString())));
                    binding.etTotalNilaiBangunan.setText(String.valueOf(totalNilai));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etLuasBangunan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!binding.etLuasBangunan.getText().toString().isEmpty()&&!binding.etHargaBangunan.getText().toString().isEmpty()){
                    //total nilai = luas dikali harga
                    Long totalNilai=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etHargaBangunan.getText().toString())))*Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etLuasBangunan.getText().toString())));
                    binding.etTotalNilaiBangunan.setText(String.valueOf(totalNilai));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etHargaTanahTempatTinggal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!binding.etLuasTanahTempatTinggal.getText().toString().isEmpty()&&!binding.etHargaTanahTempatTinggal.getText().toString().isEmpty()){
                    //total nilai = luas dikali harga
                    Long totalNilai=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etHargaTanahTempatTinggal.getText().toString())))*Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etLuasTanahTempatTinggal.getText().toString())));
                    binding.etTotalNilaiTanahTempatTinggal.setText(String.valueOf(totalNilai));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etLuasTanahTempatTinggal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!binding.etLuasTanahTempatTinggal.getText().toString().isEmpty()&&!binding.etHargaTanahTempatTinggal.getText().toString().isEmpty()){
                    //total nilai = luas dikali harga
                    Long totalNilai=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etHargaTanahTempatTinggal.getText().toString())))*Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etLuasTanahTempatTinggal.getText().toString())));
                    binding.etTotalNilaiTanahTempatTinggal.setText(String.valueOf(totalNilai));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etTotalNilaiTanahTempatTinggal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!binding.etTotalNilaiBangunan.getText().toString().isEmpty()&&!binding.etTotalNilaiTanahTempatTinggal.getText().toString().isEmpty()){
                    //total nilai = luas dikali harga
                    Long totalNilai=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etTotalNilaiBangunan.getText().toString())))+Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etTotalNilaiTanahTempatTinggal.getText().toString())));
                    binding.etTotalNilaiAsetTempatTinggal.setText(String.valueOf(totalNilai));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etTotalNilaiBangunan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!binding.etTotalNilaiTanahTempatTinggal.getText().toString().isEmpty()&&!binding.etTotalNilaiBangunan.getText().toString().isEmpty()){
                    //total nilai = luas dikali harga
                    Long totalNilai=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etTotalNilaiBangunan.getText().toString())))+Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString((binding.etTotalNilaiTanahTempatTinggal.getText().toString())));
                    binding.etTotalNilaiAsetTempatTinggal.setText(String.valueOf(totalNilai));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}