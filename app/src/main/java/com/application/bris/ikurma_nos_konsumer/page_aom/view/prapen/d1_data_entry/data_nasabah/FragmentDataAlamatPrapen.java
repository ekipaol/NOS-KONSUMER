package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_nasabah;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqValidasiDukcapil;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoFragmentDataAlamatBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataNasabahPrapen;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogAddress;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.AddressListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.address;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Locale;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDataAlamatPrapen extends Fragment implements Step, KeyValueListener, View.OnClickListener, AddressListener {
    AppPreferences appPreferences;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    private DataNasabahPrapen dataNasabah;
    private Realm realm;
    private String approved;
    private ApiClientAdapter apiClientAdapter;
    private PrapenAoFragmentDataAlamatBinding binding;

    public FragmentDataAlamatPrapen() {
    }
    
    public FragmentDataAlamatPrapen(DataNasabahPrapen mdataLengkap, String maprroved) {
        dataNasabah = mdataLengkap;
        approved = maprroved;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= PrapenAoFragmentDataAlamatBinding.inflate(inflater,container,false);
        View view =binding.getRoot();
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences=new AppPreferences(getContext());

        onSelectDialog();
        allOncClick();
        setPojoData();
        if(dataNasabah!=null){
            setData();
        }
//        if (approved.equalsIgnoreCase("no")) {
//        }


//        setData();

        return view;
    }

    private void setData(){

        binding.etAlamat.setText(dataNasabah.getAlamat());
        binding.etRw.setText(dataNasabah.getRw());
        binding.etRt.setText(dataNasabah.getRt());
        binding.etKodeposktp.setText(dataNasabah.getKodePos());
        binding.etProvinsi.setText(dataNasabah.getProvinsi());
        binding.etKota.setText(dataNasabah.getKabupaten());
        binding.etKecamatan.setText(dataNasabah.getKecamatan());
        binding.etKelurahanktp.setText(dataNasabah.getKelurahan());

    }

    private void allOncClick(){
        binding.btnCariAlamat.setOnClickListener(this);
        binding.btnCekAlamatDukcapil.setOnClickListener(this);
    }
    private void onSelectDialog(){



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

        //new data
        copyRealm.setAlamat(binding.etAlamat.getText().toString());
        copyRealm.setRt(binding.etRt.getText().toString());
        copyRealm.setRw(binding.etRw.getText().toString());
        copyRealm.setKodePos(binding.etKodeposktp.getText().toString());
        copyRealm.setProvinsi(binding.etProvinsi.getText().toString());
        copyRealm.setKabupaten(binding.etKota.getText().toString());
        copyRealm.setKecamatan(binding.etKecamatan.getText().toString());
        copyRealm.setKelurahan(binding.etKelurahanktp.getText().toString());


        realm.insertOrUpdate(copyRealm);

    }

    public void validasiDukcapil() {

        //real untuk ngambil NIk aja
        DataNasabahPrapen d = realm.where(DataNasabahPrapen.class).equalTo("applicationId", DataNasabahPrapenActivity.idAplikasi).findFirst();

        //  dataUser = getListUser();
        binding.loadingDukcapil.setVisibility(View.VISIBLE);
        //pantekan no aplikasi dan aktifitas
        ReqValidasiDukcapil req=new ReqValidasiDukcapil();
        req.setApplicationId(dataNasabah.getApplicationId());
        req.setUid(Integer.toString(appPreferences.getUid()));
        req.setNik(d.getNoKTP());
        req.setNamaLgkp(d.getNamaLengkapSesuaiKTP());
        req.setJenisKlmin(d.getJenisKelamin());
        req.setTmptLhr(d.getTempatLahir());
        req.setTglLhr(d.getTanggalLahir());
        req.setNamaLgkpIbu(d.getNamaIbuKandung());
        req.setStatusKawin(d.getStatusPernikahanSesuaiKTP());
        req.setAlamat(binding.etAlamat.getText().toString());
        req.setPropName(binding.etProvinsi.getText().toString());
        req.setNoRt(binding.etRt.getText().toString());
        req.setNoRw(binding.etRw.getText().toString());
        req.setKelName(binding.etKelurahanktp.getText().toString());
        req.setKabName(binding.etKota.getText().toString());
        req.setKecName(binding.etKecamatan.getText().toString());

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().validasiDukcapil(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingDukcapil.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        AppUtil.notifsuccess(getContext(), getActivity().findViewById(android.R.id.content),"Data DUKCAPIL Sudah Sesuai");
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

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
//        if (title.equalsIgnoreCase("jenis kelamin")){
//            et_jeniskelamin.setText(data.getName());
//
//            val_Jenkel=KeyValue.getKeyJenisKelamin(et_jeniskelamin.getText().toString());
//        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_cari_alamat:
                DialogAddress.display(getFragmentManager(), this);
                break;
            case R.id.btn_cek_alamat_dukcapil:
                validasiDukcapil();
                break;


            //TANGGAL LAHIR PASANGAN
//            case R.id.et_tanggallahirpasangan:
//            case R.id.tf_tanggallahirpasangan:
//                dpCalLahirPasangan();
//                break;
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


    @Override
    public void onAddressSelect(address data) {
        binding.etKodeposktp.setText(data.getKodepos());
        binding.etProvinsi.setText(data.getProvinsi());
        binding.etKota.setText(data.getKota());
        binding.etKecamatan.setText(data.getKecamatan());
        binding.etKelurahanktp.setText(data.getKelurahan());
    }
}