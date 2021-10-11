package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_nasabah;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoFragmentDataPribadiBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataDukcapilPasangan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataLengkap;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import io.realm.Realm;

public class FragmentDataPribadiPrapen extends Fragment implements Step, KeyValueListener, View.OnClickListener {

    private Calendar calLahirPasangan;
    private Calendar calLahir;
    private Calendar calTerbitKtp;
    private Calendar calExpiredNik;

    boolean nikPasanganBerubah=false;

    private DatePickerDialog dpTanggalLahirPasangan;
    private DatePickerDialog dpTanggalLahir;
    private DatePickerDialog dpTerbitKtp;
    private DatePickerDialog dpExpiredNik;
    AppPreferences appPreferences;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    private DataLengkap dataLengkap;

    //for npwp formatting
    int dot1=0;
    int dot2=0;
    int dot3=0;
    int dot4=0;
    int dot5=0;

    private String val_NamaAlias ="";
    private String val_NoKtpPasangan ="";
    private String val_StatusNikah ="";
    private String val_NoHp ="";
    private String val_NamaNasabah ="";
    private String val_Npwp ="";
    private String val_PendTerakhir ="";
    private String val_KetGelar ="";
    private String val_KetAgama ="";
    private String val_Agama ="";
    private String val_NamaIbu ="";
    private String val_NamaPasangan ="";
    private String val_Email ="";
    private String val_TelpKeluarga ="";
    private String val_ExpId ="";
    private String val_TglLahirPasangan ="";
    private String val_NoKtp ="";
    private String val_JlhTanggungan ;
    private String val_TglLahir ="";
    private String val_Keluarga ="";
    private String val_TptLahir ="";
    private String val_TipePendapatan ="";
    private String val_Jenkel ="";
    private String val_Referensi ="";

    private Realm realm;

    int umur=0;
    String tglLahirOri="";

    private String approved;
    private ApiClientAdapter apiClientAdapter;
    private String dataDukcapilString;
    private DataDukcapilPasangan dataDukcapilPasangan;

    private PrapenAoFragmentDataPribadiBinding binding;



    public FragmentDataPribadiPrapen() {
    }



    public FragmentDataPribadiPrapen(DataLengkap mdataLengkap, String maprroved) {
        dataLengkap = mdataLengkap;
        approved = maprroved;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=PrapenAoFragmentDataPribadiBinding.inflate(inflater,container,false);
        View view =binding.getRoot();
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences=new AppPreferences(getContext());

        allOnClicks();
        disableEditTexts();
        defaulViewSettings();

        npwpFormattingTextChange(binding.etNpwp);
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



    private void dpCalLahirPasangan(){
        calLahirPasangan = Calendar.getInstance();

        //minimal umur pasangan 17 tahun
        calLahirPasangan.add(Calendar.YEAR, -17);
        DatePickerDialog.OnDateSetListener ls_tanggalLahirPasangan = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calLahirPasangan.set(Calendar.YEAR, year);
                calLahirPasangan.set(Calendar.MONTH, month);
                calLahirPasangan.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calLahirPasanganString = dateClient.format(calLahirPasangan.getTime());
                binding.etTanggalLahirPasangan.setText(calLahirPasanganString);

            }
        };

        dpTanggalLahirPasangan = new DatePickerDialog(getContext(), R.style.AppTheme_TimePickerTheme, ls_tanggalLahirPasangan, calLahirPasangan.get(Calendar.YEAR),
                calLahirPasangan.get(Calendar.MONTH), calLahirPasangan.get(Calendar.DAY_OF_MONTH));
        dpTanggalLahirPasangan.getDatePicker().setMaxDate(calLahirPasangan.getTimeInMillis());
        dpTanggalLahirPasangan.show();
    }

    private void dpCalLahir(){
        calLahir = Calendar.getInstance();
        calLahir.add(Calendar.YEAR, -10);
        DatePickerDialog.OnDateSetListener ls_tanggalLahirPasangan = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calLahir.set(Calendar.YEAR, year);
                calLahir.set(Calendar.MONTH, month);
                calLahir.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calLahirString = dateClient.format(calLahir.getTime());
                binding.etTanggallahir.setText(calLahirString);
            }
        };

        dpTanggalLahir = new DatePickerDialog(getContext(), R.style.AppTheme_TimePickerTheme, ls_tanggalLahirPasangan, calLahir.get(Calendar.YEAR),
                calLahir.get(Calendar.MONTH), calLahir.get(Calendar.DAY_OF_MONTH));
        dpTanggalLahir.getDatePicker().setMaxDate(calLahir.getTimeInMillis());
        dpTanggalLahir.show();
    }

    private void dpTerbitKtp(){
        calTerbitKtp = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ls_terbit_ktp = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calTerbitKtp.set(Calendar.YEAR, year);
                calTerbitKtp.set(Calendar.MONTH, month);
                calTerbitKtp.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calLahirString = dateClient.format(calTerbitKtp.getTime());
                binding.etTanggalTerbitKtp.setText(calLahirString);
            }
        };

        dpTerbitKtp = new DatePickerDialog(getContext(), R.style.AppTheme_TimePickerTheme, ls_terbit_ktp, calTerbitKtp.get(Calendar.YEAR),
                calTerbitKtp.get(Calendar.MONTH), calTerbitKtp.get(Calendar.DAY_OF_MONTH));
        dpTerbitKtp.getDatePicker().setMaxDate(calTerbitKtp.getTimeInMillis());
        dpTerbitKtp.show();
    }


    private void openKeyValueDialog(String title){
        DialogKeyValue.display(getFragmentManager(), title, this);
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






    private void npwpFormattingTextChange(final EditText npwp){
        npwp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()==2){
                    dot1=1;
                    npwp.setSelection(npwp.getText().length());

                }
                else if(s.length()==3&&dot1==1&&s.toString().substring(2,3)!="."){
                    dot1=0;
                    npwp.setText(npwp.getText().toString().substring(0,2)+"."+npwp.getText().toString().substring(2,3));
                    npwp.setSelection(npwp.getText().length());
                }

                if(s.length()==6){
                    dot2=1;
//                    npwp.setText(npwp.getText()+".");
                    npwp.setSelection(npwp.getText().length());
                }
                else if(s.length()==7&&dot2==1&&s.toString().substring(6,7)!="."){
                    dot2=0;
                    npwp.setText(npwp.getText().toString().substring(0,6)+"."+npwp.getText().toString().substring(6,7));
                    npwp.setSelection(npwp.getText().length());
                }

                if(s.length()==10){
                    dot3=1;
//                    npwp.setText(npwp.getText()+".");
                    npwp.setSelection(npwp.getText().length());
                }
                else if(s.length()==11&&dot3==1&&s.toString().substring(10,11)!="."){
                    dot3=0;
                    npwp.setText(npwp.getText().toString().substring(0,10)+"."+npwp.getText().toString().substring(10,11));
                    npwp.setSelection(npwp.getText().length());
                }

                if(s.length()==12){
                    dot4=1;
//                    npwp.setText(npwp.getText()+".");
                    npwp.setSelection(npwp.getText().length());
                }
                else if(s.length()==13&&dot4==1&&s.toString().substring(12,13)!="-"){
                    dot4=0;
                    npwp.setText(npwp.getText().toString().substring(0,12)+"-"+npwp.getText().toString().substring(12,13));
                    npwp.setSelection(npwp.getText().length());
                }

                if(s.length()==16){
                    dot5=1;
//                    npwp.setText(npwp.getText()+".");
                    npwp.setSelection(npwp.getText().length());
                }
                else if(s.length()==17&&dot5==1&&s.toString().substring(16,17)!="."){
                    dot5=0;
                    npwp.setText(npwp.getText().toString().substring(0,16)+"."+npwp.getText().toString().substring(16,17));
                    npwp.setSelection(npwp.getText().length());
                }

//                if(s.length()==10){
//                    npwp.setText(npwp.getText()+".");
//                    npwp.setSelection(npwp.getText().length());
//                }
//                if(s.length()==12){
//                    npwp.setText(npwp.getText()+"-");
//                    npwp.setSelection(npwp.getText().length());
//                }
//                if(s.length()==16){
//                    npwp.setText(npwp.getText()+".");
//                    npwp.setSelection(npwp.getText().length());
//                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){


//            TANGGAL LAHIR PASANGAN
            case R.id.et_tanggal_lahir_pasangan:
            case R.id.tf_tanggal_lahir_pasangan:
                dpCalLahirPasangan();
                break;
//
//            //TANGGAL LAHIR
            case R.id.et_tanggal_lahir:
            case R.id.tf_tanggal_lahir:
                dpCalLahir();
                break;
//
//            //STATUS NIKAH SAAT INI
            case R.id.et_status_nikah_saat_ini:
            case R.id.tf_status_nikah_saat_ini:
                openKeyValueDialog(binding.tfStatusNikahSaatIni.getLabelText().toString().trim());
                break;

            //STATUS NIKAH KT[
            case R.id.et_status_nikah_ktp:
            case R.id.tf_status_nikah_ktp:
                openKeyValueDialog(binding.tfStatusNikahKtp.getLabelText().toString().trim());
                break;
//
            //JENIS KELAMIN
            case R.id.et_jenis_kelamin:
            case R.id.tf_jenis_kelamin:
                openKeyValueDialog(binding.tfJenisKelamin.getLabelText().toString().trim());
                break;

            //AGAMA
            case R.id.et_agama:
            case R.id.tf_agama:
                openKeyValueDialog(binding.tfAgama.getLabelText().toString().trim());
                break;

            //TERBIT KTP
            case R.id.et_tanggal_terbit_ktp:
            case R.id.tf_tanggal_terbit_ktp:
                dpTerbitKtp();
                break;


        }
    }

    private void allOnClicks(){
        endIconOnClick();
        binding.etJenisKelamin.setOnClickListener(this);
        binding.tfJenisKelamin.setOnClickListener(this);

        binding.etTanggallahir.setOnClickListener(this);
        binding.tfTanggalLahir.setOnClickListener(this);

        binding.etAgama.setOnClickListener(this);
        binding.tfAgama.setOnClickListener(this);

        binding.etTanggalTerbitKtp.setOnClickListener(this);
        binding.tfTanggalTerbitKtp.setOnClickListener(this);

        binding.etStatusNikahSaatIni.setOnClickListener(this);
        binding.tfStatusNikahSaatIni.setOnClickListener(this);

        binding.etStatusNikahKtp.setOnClickListener(this);
        binding.etStatusNikahKtp.setOnClickListener(this);

        binding.etTanggalLahirPasangan.setOnClickListener(this);
        binding.tfTanggalLahirPasangan.setOnClickListener(this);


    }

    private void endIconOnClick(){
        binding.tfJenisKelamin.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openKeyValueDialog(binding.tfJenisKelamin.getLabelText());
            }
        });

        binding.tfTanggalLahir.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpCalLahir();
            }
        });

        binding.tfAgama.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openKeyValueDialog(binding.tfAgama.getLabelText());
            }
        });

        binding.tfTanggalTerbitKtp.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpTerbitKtp();
            }
        });

        binding.tfStatusNikahKtp.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openKeyValueDialog(binding.tfStatusNikahKtp.getLabelText());
            }
        });

        binding.tfStatusNikahSaatIni.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openKeyValueDialog(binding.tfStatusNikahSaatIni.getLabelText());
            }
        });

        binding.tfTanggalLahirPasangan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpCalLahirPasangan();
            }
        });

    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase(binding.tfJenisKelamin.getLabelText())){
            binding.etJenisKelamin.setText(data.getName());

//            val_Jenkel=KeyValue.getKeyJenisKelamin(et_jeniskelamin.getText().toString());
        }
        else if (title.equalsIgnoreCase(binding.tfTanggalLahir.getLabelText())){
            binding.etTanggallahir.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(binding.tfAgama.getLabelText())){
            binding.etAgama.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(binding.tfTanggalTerbitKtp.getLabelText())){
            binding.etTanggalTerbitKtp.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(binding.tfStatusNikahSaatIni.getLabelText())){
            binding.etStatusNikahSaatIni.setText(data.getName());
            if(data.getName().equalsIgnoreCase("kawin")){
                binding.llPasangan.setVisibility(View.VISIBLE);
            }
            else{
                binding.llPasangan.setVisibility(View.GONE);
            }
        }
        else if (title.equalsIgnoreCase(binding.tfStatusNikahKtp.getLabelText())){
            binding.etStatusNikahKtp.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(binding.tfAgama.getLabelText())){
            binding.etAgama.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(binding.tfTanggalLahirPasangan.getLabelText())){
            binding.etTanggalLahirPasangan.setText(data.getName());
        }

    }

    private void disableEditTexts(){
        binding.etJenisKelamin.setFocusable(false);
        binding.etTanggallahir.setFocusable(false);
        binding.etAgama.setFocusable(false);
        binding.etTanggalTerbitKtp.setFocusable(false);
        binding.etStatusNikahKtp.setFocusable(false);
        binding.etStatusNikahSaatIni.setFocusable(false);
        binding.etTanggalLahirPasangan.setFocusable(false);

    }

    private void defaulViewSettings(){
        binding.llPasangan.setVisibility(View.GONE);
    }


}