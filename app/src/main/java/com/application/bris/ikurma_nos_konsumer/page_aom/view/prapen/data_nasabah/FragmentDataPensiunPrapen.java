package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.data_nasabah;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoFragmentDataPensiunanBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataLengkap;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Locale;

import io.realm.Realm;

public class FragmentDataPensiunPrapen extends Fragment implements Step, KeyValueListener, View.OnClickListener {
    AppPreferences appPreferences;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    private DataLengkap dataLengkap;
    private Realm realm;
    private String approved;
    private ApiClientAdapter apiClientAdapter;
    private PrapenAoFragmentDataPensiunanBinding binding;

    public FragmentDataPensiunPrapen() {
    }

    public FragmentDataPensiunPrapen(DataLengkap mdataLengkap, String maprroved) {
        dataLengkap = mdataLengkap;
        approved = maprroved;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= PrapenAoFragmentDataPensiunanBinding.inflate(inflater,container,false);
        View view =binding.getRoot();
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences=new AppPreferences(getContext());

        //view settings
        allOnClicks();
        disableEditTexts();
        defaulViewSettings();


//        setData();

        return view;
    }

    //    private void setData(){
//
//        et_nik.setText(dataLengkap.getNoKtp());
//        et_expirednik.setText(AppUtil.parseTanggalGeneral(dataLengkap.getExpId(), "ddMMyyyy", "dd-MM-yyyy"));
////            et_npwp.setText(dataLengkap.getNpwp().replaceAll("[-.]", ""));
//        et_npwp.setText(AppUtil.parseNpwp(dataLengkap.getNpwp()));
//        et_nama.setText(dataLengkap.getNamaNasabah());
//        et_namaalias.setText(dataLengkap.getNamaAlias());
//        et_tempatlahir.setText(dataLengkap.getTptLahir());
//
//        //parameter untuk testing
//        tglLahirOri=dataLengkap.getTglLahir();
//
//        et_tanggallahir.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglLahir(), "ddMMyyyy", "dd-MM-yyyy"));
//        et_statusnikah.setText(KeyValue.getKeyStatusNikah(dataLengkap.getStatusNikah()));
//
//        et_jeniskelamin.setText(KeyValue.getKeyJenisKelamin(dataLengkap.getJenkel()));
//        et_nomorhp.setText(dataLengkap.getNoHp());
//        et_email.setText(dataLengkap.getEmail());
//        et_agama.setText(KeyValue.getKeyAgama(dataLengkap.getAgama()));
//        et_ketagama.setText(dataLengkap.getKetAgama());
//        et_namaibukandung.setText(dataLengkap.getNamaIbu());
//        et_nikpasangan.setText(dataLengkap.getNoKtpPasangan());
//        et_namapasangan.setText(dataLengkap.getNamaPasangan());
//        et_tanggallahirpasangan.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTgllahirPasangan(), "ddMMyyyy", "dd-MM-yyyy"));
//        et_namakeluarga.setText(dataLengkap.getKeluarga());
//        et_nomorhpkeluarga.setText(dataLengkap.getTelpKeluarga());
//        et_jumlahtanggungan.setText(String.valueOf(dataLengkap.getJlhTanggungan()));
////            et_tipependapatan.setText(KeyValue.getKeyTipePendapatan(dataLengkap.getTipePendapatan()));
//        et_pendidikanterakhir.setText(KeyValue.getKeyPendidikanTerakhir(dataLengkap.getPendidikanTerakhir()));
//        et_referensi.setText(KeyValue.getKeyReferensi(dataLengkap.getReferensi()));
//
//
//
//        if (dataLengkap.getStatusNikah().equalsIgnoreCase("2")){
//            ll_pasangan.setVisibility(View.VISIBLE);
////            et_namapasangan.setFocusable(false);
//            et_tanggallahirpasangan.setFocusable(false);
//            btn_cek_nik_pasangan.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    //kalo nik masi kosong udah main pencat pencet bae, validasi
//                    if(et_nikpasangan.getText().toString().isEmpty()){
//                        tf_nikpasangan.setError("Isi NIK pasangan",true);
//                    }
//
//                    //kalau format nik gak bener, validasi
//                    else if(!Validator.validateKtpRequired(et_nikpasangan.getText().toString().trim())){
//                        tf_nikpasangan.setError("Format NIK belum benar",true);
//                    }
//
//                    else{
//                     
//                        cekDukcapilPasangan();
//
////                        }
//                    }
//
//
//                }
//            });
//        }
//        if (dataLengkap.getAgama().equalsIgnoreCase("ZZZ")){
//            tf_ketagama.setVisibility(View.VISIBLE);
//        }
//
//        if (approved.equalsIgnoreCase("yes")){
//            AppUtil.disableEditTexts(ll_datapribadi);
//        }
//        
//
//        et_nikpasangan.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (dataLengkap.getStatusNikah().equalsIgnoreCase("2")){
//                    nikPasanganBerubah=true;
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//    }
    private void allOnClicks(){
        endIconOnClick();
        binding.etMenggunakanLngp.setOnClickListener(this);
        binding.tfMenggunakanLngp.setOnClickListener(this);

        binding.etNasabahBsi.setOnClickListener(this);
        binding.tfNasabahBsi.setOnClickListener(this);

        binding.etDapatBergerak.setOnClickListener(this);
        binding.tfDapatBergerak.setOnClickListener(this);

        binding.etDalamPengawasan.setOnClickListener(this);
        binding.tfDalamPengawasan.setOnClickListener(this);

        binding.etMemilikiRiwayat.setOnClickListener(this);
        binding.tfMemilikiRiwayat.setOnClickListener(this);

        binding.etMenggunakanLngp.setOnClickListener(this);
        binding.tfMenggunakanLngp.setOnClickListener(this);

        binding.etTinggalDenganKeluargaLain.setOnClickListener(this);
        binding.tfTinggalDenganKeluargaLain.setOnClickListener(this);

        binding.etUsahaSampingan.setOnClickListener(this);
        binding.tfUsahaSampingan.setOnClickListener(this);

        binding.etKirimanRutin.setOnClickListener(this);
        binding.tfKirimanRutin.setOnClickListener(this);




    }

    private void openDialogYaTidak(String title){
        DialogKeyValue.displayYaTidak(getFragmentManager(),title, this);
    }

    private VerificationError validateForm(){
//        if (Validator.validateKtpRequired(et_nik.getText().toString().trim()) == false){
//            tf_nik.setError("Format "+ tf_nik.getLabelText()+" "+getString(R.string.title_invalid_field), true);
//            return new VerificationError("Format "+ tf_nik.getLabelText()+" "+getString(R.string.title_invalid_field));
//        }

//        else {
//            setDataOnListerner();
        return null;
//        }
    }

    public void setDataOnListerner(){

//        val_Referensi = (KeyValue.getTypeReferensi(et_referensi.getText().toString().trim()));

//        DataLengkapPojo d = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", DataLengkapActivity.idAplikasi).findFirst();
//        DataLengkapPojo copyRealm=new DataLengkapPojo();
//
//        if(!realm.isInTransaction()){
//            realm.beginTransaction();
//        }
//
//        if(d!=null){
//            copyRealm=realm.copyFromRealm(d);
//        }
//        else{
//            //karena id aplikasi primary key, jadi hanya ditambahkan jika sudah dipastikan data dari realm itu null
//            copyRealm.setIdAplikasi(DataLengkapActivity.idAplikasi);
//        }
//        copyRealm.setCif(DataLengkapActivity.cif);
//        copyRealm.setUid(DataLengkapActivity.uid);
//        realm.insertOrUpdate(copyRealm);
//        realm.close();

    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return  validateForm();
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase(binding.tfMenggunakanLngp.getLabelText())){
            binding.etMenggunakanLngp.setText(data.getName());

//            val_Jenkel=KeyValue.getKeyJenisKelamin(et_jeniskelamin.getText().toString());
        }
        else if (title.equalsIgnoreCase(binding.tfNasabahBsi.getLabelText())){
            binding.etNasabahBsi.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(binding.tfNasabahBsi.getLabelText())){
            binding.etNasabahBsi.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(binding.tfDapatBergerak.getLabelText())){
            binding.etDapatBergerak.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(binding.tfDalamPengawasan.getLabelText())){
            binding.etDalamPengawasan.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(binding.tfMemilikiRiwayat.getLabelText())){
            binding.etMemilikiRiwayat.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(binding.tfTinggalDenganKeluargaLain.getLabelText())){
            binding.etTinggalDenganKeluargaLain.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(binding.tfUsahaSampingan.getLabelText())){
            binding.etUsahaSampingan.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(binding.tfKirimanRutin.getLabelText())){
            binding.etKirimanRutin.setText(data.getName());
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){


            //MENGGUNAKAN LNGP
            case R.id.et_menggunakan_lngp:
            case R.id.tf_menggunakan_lngp:
                openDialogYaTidak(binding.tfMenggunakanLngp.getLabelText());
                break;

            //MENGGUNAKAN LNGP
            case R.id.et_nasabah_bsi:
            case R.id.tf_nasabah_bsi:
                openDialogYaTidak(binding.tfNasabahBsi.getLabelText());
                break;

            //DAPAT BERGERAK
            case R.id.et_dapat_bergerak:
            case R.id.tf_dapat_bergerak:
                openDialogYaTidak(binding.tfDapatBergerak.getLabelText());
                break;

            //MENGGUNAKAN LNGP
            case R.id.et_dalam_pengawasan:
            case R.id.tf_dalam_pengawasan:
                openDialogYaTidak(binding.tfDalamPengawasan.getLabelText());
                break;

            //MENGGUNAKAN LNGP
            case R.id.et_memiliki_riwayat:
            case R.id.tf_memiliki_riwayat:
                openDialogYaTidak(binding.tfMemilikiRiwayat.getLabelText());
                break;

            //MENGGUNAKAN LNGP
            case R.id.et_tinggal_dengan_keluarga_lain:
            case R.id.tf_tinggal_dengan_keluarga_lain:
                openDialogYaTidak(binding.tfTinggalDenganKeluargaLain.getLabelText());
                break;

            //MENGGUNAKAN LNGP
            case R.id.et_usaha_sampingan:
            case R.id.tf_usaha_sampingan:
                openDialogYaTidak(binding.tfUsahaSampingan.getLabelText());
                break;

            //MENGGUNAKAN LNGP
            case R.id.et_kiriman_rutin:
            case R.id.tf_kiriman_rutin:
                openDialogYaTidak(binding.tfKirimanRutin.getLabelText());
                break;
//
//            //TANGGAL LAHIR
//            case R.id.et_tanggallahir:
//            case R.id.tf_tanggallahir:
//                dpCalLahir();
//                break;
//
//            //STATUS NIKAH
//            case R.id.et_statusnikah:
//            case R.id.tf_statusnikah:
//                openKeyValueDialog(tf_statusnikah.getLabelText().toString().trim());
//                break;
//
//            //JENIS KELAMIN
//            case R.id.et_jeniskelamin:
//            case R.id.tf_jeniskelamin:
//                openKeyValueDialog(tf_jeniskelamin.getLabelText().toString().trim());
//                break;


        }
    }

    private void disableEditTexts(){
        binding.etMenggunakanLngp.setFocusable(false);
        binding.etLembagaPengelolaPensiun.setFocusable(false);
        binding.etNasabahBsi.setFocusable(false);
        binding.etTreatmentRekeningPendapatan.setFocusable(false);
        binding.etDapatBergerak.setFocusable(false);
        binding.etDalamPengawasan.setFocusable(false);
        binding.etMemilikiRiwayat.setFocusable(false);
        binding.etUsahaSampingan.setFocusable(false);
        binding.etTinggalDenganKeluargaLain.setFocusable(false);
        binding.etKirimanRutin.setFocusable(false);
    }

    private void endIconOnClick(){
        binding.tfMenggunakanLngp.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogYaTidak(binding.tfMenggunakanLngp.getLabelText());
            }
        });

        binding.tfNasabahBsi.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogYaTidak(binding.tfNasabahBsi.getLabelText());
            }
        });

        binding.tfDapatBergerak.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogYaTidak(binding.tfDapatBergerak.getLabelText());
            }
        });

        binding.tfDalamPengawasan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogYaTidak(binding.tfDalamPengawasan.getLabelText());
            }
        });

        binding.tfMemilikiRiwayat.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogYaTidak(binding.tfMemilikiRiwayat.getLabelText());
            }
        });

        binding.tfTinggalDenganKeluargaLain.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogYaTidak(binding.tfTinggalDenganKeluargaLain.getLabelText());
            }
        });

        binding.tfUsahaSampingan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogYaTidak(binding.tfUsahaSampingan.getLabelText());
            }
        });

        binding.tfKirimanRutin.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogYaTidak(binding.tfKirimanRutin.getLabelText());
            }
        });
    }

    private void defaulViewSettings(){
        binding.tvHasilCekPayroll.setVisibility(View.GONE);
    }




}
