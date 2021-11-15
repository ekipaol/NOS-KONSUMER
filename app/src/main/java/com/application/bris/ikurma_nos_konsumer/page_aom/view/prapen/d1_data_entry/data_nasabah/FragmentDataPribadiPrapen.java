package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_nasabah;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqAcctNumber;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqValidasiDukcapil;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoFragmentDataPribadiBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataCIfRekening;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataNasabahPrapen;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataDukcapilPasangan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedDrawable;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDataPribadiPrapen extends Fragment implements Step, KeyValueListener, View.OnClickListener, CameraListener {

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
    private DataNasabahPrapen dataNasabah;

    //for npwp formatting
    int dot1=0;
    int dot2=0;
    int dot3=0;
    int dot4=0;
    int dot5=0;

    private String valBase64PdfKtp ="";
    private String valBase64PdfIdeb ="";
    private final int UPLOAD_KTP=1;
    private final int UPLOAD_IDEB=2;
    private String namaFotoKtp="fotoKtp";
    private String namaFotoIdeb="fotoIdeb";
    private String tipeFileKtp="";
    private String tipeFileIdeb="";
    private int idUpload;

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



    public FragmentDataPribadiPrapen(DataNasabahPrapen mdataLengkap, String maprroved) {
        dataNasabah = mdataLengkap;
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

        if(dataNasabah!=null){
            setData();
        }
        npwpFormattingTextChange(binding.etNpwp);


        return view;
    }

    private void setData(){

        binding.etNomorKtp.setText(dataNasabah.getNoKTP());
        binding.etNpwp.setText(dataNasabah.getNoNPWP());
        binding.etNamaLengkap.setText(dataNasabah.getNamaLengkap());
        binding.etNamaKtp.setText(dataNasabah.getNamaLengkapSesuaiKTP());
        binding.etJenisKelamin.setText(dataNasabah.getJenisKelamin());
        binding.etTempatLahir.setText(dataNasabah.getTempatLahir());
        binding.etTanggallahir.setText(AppUtil.parseTanggalGeneral(dataNasabah.getTanggalLahir(), "yyyy-mm-dd", "dd-MM-yyyy"));
        binding.etNamaIbuKandung.setText(dataNasabah.getNamaIbuKandung());
        binding.etNomorHp.setText(dataNasabah.getNomorHandphone());
        binding.etTelponRumah.setText(dataNasabah.getNoTelpRumah());
        binding.etEmail.setText(dataNasabah.getEmail());
        binding.etAgama.setText(dataNasabah.getAgama());
        binding.etTanggalTerbitKtp.setText(AppUtil.parseTanggalGeneral(dataNasabah.getTanggalPenerbitanKTP(), "yyyy-mm-dd", "dd-MM-yyyy"));
        binding.etStatusNikahKtp.setText(dataNasabah.getStatusPernikahanSesuaiKTP());
        binding.etStatusNikahSaatIni.setText(dataNasabah.getStatusPernikahan());
        binding.etNomorKtpPasangan.setText(dataNasabah.getNoKTPPasangan());
        binding.etTanggalLahirPasangan.setText(AppUtil.parseTanggalGeneral(dataNasabah.getTanggalLahirPasangan(), "yyyy-mm-dd", "dd-MM-yyyy"));
        binding.etNamaLengkapPasangan.setText(dataNasabah.getNamaLengkapPasangan());


        if (dataNasabah.getStatusPernikahan().equalsIgnoreCase("Kawin")){
            binding.llPasangan.setVisibility(View.VISIBLE);

        }


        try{
            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik

            if(dataNasabah.getFile_Name_Ktp().substring(dataNasabah.getFile_Name_Ktp().length()-3,dataNasabah.getFile_Name_Ktp().length()).equalsIgnoreCase("pdf")){
                AppUtil.logSecure("ekstensi cek",dataNasabah.getFile_Name_Ktp().substring(dataNasabah.getFile_Name_Ktp().length()-3,dataNasabah.getFile_Name_Ktp().length()));

                AppUtil.convertBase64ToFileWithOnClick(getContext(),dataNasabah.getImgKtp(),binding.imgFotoKtp,dataNasabah.getFile_Name_Ktp());
            }
            else{
                AppUtil.convertBase64ToImage(dataNasabah.getImgKtp(),binding.imgFotoKtp);
            }

            if(dataNasabah.getFile_Name_Ideb().substring(dataNasabah.getFile_Name_Ideb().length()-3,dataNasabah.getFile_Name_Ideb().length()).equalsIgnoreCase("pdf")){
                AppUtil.logSecure("ekstensi cek",dataNasabah.getFile_Name_Ideb().substring(dataNasabah.getFile_Name_Ideb().length()-3,dataNasabah.getFile_Name_Ideb().length()));

                AppUtil.convertBase64ToFileWithOnClick(getContext(),dataNasabah.getImgIdeb(),binding.imgFotoIdeb,dataNasabah.getFile_Name_Ideb());
            }
            else{
                AppUtil.convertBase64ToImage(dataNasabah.getImgIdeb(),binding.imgFotoIdeb);
            }
        }
        catch (Exception e){
            AppUtil.logSecure("error setdata",e.getMessage());
        }





    }



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
            setPojoData();
            return null;
//        }
    }

    public void setPojoData(){

//        val_Referensi = (KeyValue.getTypeReferensi(et_referensi.getText().toString().trim()));

        DataNasabahPrapen d = realm.where(DataNasabahPrapen.class).equalTo("applicationId", DataNasabahPrapenActivity.idAplikasi).findFirst();
        DataNasabahPrapen copyRealm=new DataNasabahPrapen();

        if(!realm.isInTransaction()){
            realm.beginTransaction();
        }

        if(d!=null){
            copyRealm=realm.copyFromRealm(d);
        }
        else{
            //karena id aplikasi primary key, jadi hanya ditambahkan jika sudah dipastikan data dari realm itu null
            copyRealm.setApplicationId(DataNasabahPrapenActivity.idAplikasi);
        }
        copyRealm.setUid(String.valueOf(appPreferences.getUid()));
        copyRealm.setNoKTP(binding.etNomorKtp.getText().toString());
        copyRealm.setNoNPWP(binding.etNpwp.getText().toString());
        copyRealm.setNamaLengkap(binding.etNamaLengkap.getText().toString());
        copyRealm.setNamaLengkapSesuaiKTP(binding.etNamaKtp.getText().toString());
        copyRealm.setJenisKelamin(binding.etJenisKelamin.getText().toString());
        copyRealm.setTempatLahir(binding.etTempatLahir.getText().toString());
        copyRealm.setTanggalLahir(AppUtil.parseTanggalGeneral(binding.etTanggallahir.getText().toString(),"dd-mm-yyyy","yyyy-mm-dd"));
        copyRealm.setNamaIbuKandung(binding.etNamaIbuKandung.getText().toString());
        copyRealm.setNomorHandphone(binding.etNomorHp.getText().toString());
        copyRealm.setNoTelpRumah(binding.etTelponRumah.getText().toString());
        copyRealm.setEmail(binding.etEmail.getText().toString());
        copyRealm.setAgama(binding.etAgama.getText().toString());
        copyRealm.setTanggalPenerbitanKTP(AppUtil.parseTanggalGeneral(binding.etTanggalTerbitKtp.getText().toString(),"dd-mm-yyyy","yyyy-mm-dd"));
        copyRealm.setStatusPernikahan(binding.etStatusNikahSaatIni.getText().toString());
        copyRealm.setStatusPernikahanSesuaiKTP(binding.etStatusNikahKtp.getText().toString());
        copyRealm.setNamaLengkapPasangan(binding.etNamaLengkapPasangan.getText().toString());
        copyRealm.setNoKTPPasangan(binding.etNomorKtpPasangan.getText().toString());
        copyRealm.setTanggalLahirPasangan(AppUtil.parseTanggalGeneral(binding.etTanggalLahirPasangan.getText().toString(),"dd-mm-yyyy","yyyy-mm-dd"));

//        ambil gambar
        binding.imgFotoKtp.invalidate();
        binding.imgFotoIdeb.invalidate();
        RoundedDrawable drawableKtp = (RoundedDrawable) binding.imgFotoKtp.getDrawable();
        RoundedDrawable drawableIdeb = (RoundedDrawable) binding.imgFotoIdeb.getDrawable();
        Bitmap bitmapKtp = drawableKtp.getSourceBitmap();
        Bitmap bitmapIdeb = drawableIdeb.getSourceBitmap();

        if(tipeFileKtp.equalsIgnoreCase("pdf")){
            copyRealm.setImgKtp(valBase64PdfKtp);
            copyRealm.setFile_Name_Ktp(String.valueOf(DataNasabahPrapenActivity.idAplikasi)+"_ktp.pdf");
        }
        else{
            copyRealm.setImgKtp(AppUtil.encodeImageTobase64(bitmapKtp));
            copyRealm.setFile_Name_Ktp(String.valueOf(DataNasabahPrapenActivity.idAplikasi)+"_ktp.png");
        }

        if(tipeFileIdeb.equalsIgnoreCase("pdf")){
            copyRealm.setImgIdeb(valBase64PdfIdeb);
            copyRealm.setFile_Name_Ideb(String.valueOf(DataNasabahPrapenActivity.idAplikasi)+"_ideb.pdf");
        }
        else{
            copyRealm.setImgIdeb(AppUtil.encodeImageTobase64(bitmapIdeb));
            copyRealm.setFile_Name_Ideb(String.valueOf(DataNasabahPrapenActivity.idAplikasi)+"_ideb.png");
        }
        realm.insertOrUpdate(copyRealm);
        realm.close();

    }

    public void validasiDukcapil() {
        //  dataUser = getListUser();
        binding.loadingDukcapil.setVisibility(View.VISIBLE);
        //pantekan no aplikasi dan aktifitas
        ReqValidasiDukcapil req=new ReqValidasiDukcapil();
        req.setApplicationId(dataNasabah.getApplicationId());
        req.setUid(Integer.toString(appPreferences.getUid()));
        req.setNamaLgkp(binding.etNamaKtp.getText().toString());
        req.setNik(binding.etNomorKtp.getText().toString());
        req.setJenisKlmin(binding.etJenisKelamin.getText().toString());
        req.setTmptLhr(binding.etTempatLahir.getText().toString());
        req.setTglLhr(binding.etTanggallahir.getText().toString());
        req.setNamaLgkpIbu(binding.etNamaIbuKandung.getText().toString());
        req.setStatusKawin(binding.etStatusNikahKtp.getText().toString());

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().validasiDukcapil(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingDukcapil.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        AppUtil.notifsuccess(getContext(), getActivity().findViewById(android.R.id.content),"Ddata DUKCAPIL Sudah Sesuai");
                    }
                    else{
                        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingDukcapil.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
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
            case R.id.btn_upload_foto_ktp:
                idUpload=UPLOAD_KTP;
                BSUploadFile.displayWithTitle(getFragmentManager(),FragmentDataPribadiPrapen.this,"Upload Foto KTP");
                Toast.makeText(getContext(), "ambil ktp", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_upload_persetujuan_ideb:
                idUpload=UPLOAD_IDEB;
                BSUploadFile.displayWithTitle(getFragmentManager(),FragmentDataPribadiPrapen.this,"Upload Foto IDEB");
                Toast.makeText(getContext(), "ambil ideb", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cek_nik_dukcapil:
                validasiDukcapil();
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

        binding.btnUploadFotoKtp.setOnClickListener(this);
        binding.btnUploadPersetujuanIdeb.setOnClickListener(this);

        binding.btnCekNikDukcapil.setOnClickListener(this);


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




    //UPLOAD FILE METHODS


    private void openCamera(int cameraCode,String namaFoto) {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                    100);
        } else {
            Uri outputFileUri = getCaptureImageOutputUri(namaFoto);
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(captureIntent, cameraCode);
        }
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

    private Uri getCaptureImageOutputUri(String namaFoto) {
        Uri outputFileUri = null;
        File getImage = getActivity().getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                outputFileUri = FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), namaFoto + ".png"));
            } else {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), namaFoto + ".png"));
            }
        }
        return outputFileUri;
    }

    public Uri getPickImageResultUri(Intent data, String namaFoto) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri(namaFoto) : data.getData();
    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        switch(idMenu){
            case "Take Photo":
                if (idUpload==UPLOAD_IDEB) {
                    tipeFileIdeb="png";
                    openCamera(UPLOAD_IDEB, namaFotoIdeb);
                } else if (idUpload==UPLOAD_KTP) {
                    openCamera(UPLOAD_KTP, namaFotoKtp);
                    tipeFileKtp="png";
                }
                break;
            case "Pick Photo":
                if (idUpload==UPLOAD_IDEB) {
                    openGalery(UPLOAD_IDEB);
                    tipeFileIdeb="png";
                } else if (idUpload==UPLOAD_KTP) {
                    openGalery(UPLOAD_KTP);
                    tipeFileKtp="png";
                }
                break;
            case "Pick File":
                if (idUpload==UPLOAD_IDEB) {
                    openFile(UPLOAD_IDEB);
                    tipeFileIdeb="pdf";
                } else if (idUpload==UPLOAD_KTP) {
                    openFile(UPLOAD_KTP);
                    tipeFileKtp="pdf";
                }
                break;
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case UPLOAD_KTP:
                setDataImage(binding.imgFotoKtp, data, namaFotoKtp,UPLOAD_KTP);
                break;
            case UPLOAD_IDEB:
                setDataImage(binding.imgFotoIdeb, data, namaFotoIdeb,UPLOAD_IDEB);
                break;

        }
    }

    private void setDataImage(ImageView iv, Intent data, String namaFoto,int KODE_UPLOAD) {
        Bitmap bitmap = null;
        Uri uri;
        if (getPickImageResultUri(data, namaFoto) != null) {
            uri = getPickImageResultUri(data, namaFoto);
            try {

                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                    bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                    bitmap = AppUtil.rotateImageIfRequired(getContext(), bitmap, uri);
                    iv.setImageBitmap(bitmap);


            } catch (Exception e) {
                e.printStackTrace();
                iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pdf_hd));

                if(KODE_UPLOAD==UPLOAD_IDEB){
                    Uri uriPdf = data.getData();
                    valBase64PdfIdeb= AppUtil.encodeFileToBase64(getContext(),uriPdf);

                }
                else if(KODE_UPLOAD==UPLOAD_KTP){
                    Uri uriPdf = data.getData();
                    valBase64PdfKtp= AppUtil.encodeFileToBase64(getContext(),uriPdf);
                }
            }
        }
    }

}