package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_nasabah;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseLogicalDoc;
import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqAcctNumber;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqListLngp;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqLkpD1;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqValidasiLngp;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UploadImage;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoFragmentDataPensiunanBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataCIfRekening;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataInstansiDapen;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataListLngp;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DropdownGlobalPrapen;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhotoFromIdLogicalDoc;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class FragmentDataPensiunPrapen extends Fragment implements Step, KeyValueListener, View.OnClickListener, GenericListenerOnSelect {
    AppPreferences appPreferences;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    private DataInstansiDapen dataInstansi;
    private Realm realm;
    private String approved;
    private ApiClientAdapter apiClientAdapter;
    private PrapenAoFragmentDataPensiunanBinding binding;
    private boolean valDapatBergerak=false,valDalamPengawasan=false,valMemilikiRiwayat=false,valSerumahDenganKeluarga=false,valMemilikiUsahaSampingan=false,valMemperolehKiriman =false,valMenggunakanLngp =false,valNasabahBsi =false;
    private String val_cif="";
    private boolean adaFieldBelumDiisi=false;
    public boolean rekeningBerubah=false;
    private boolean escrowBerubah=false;
    private boolean valVvip=false;
    private UploadImage dokumenLkp=new UploadImage();
    private ImageView containerImageview;


    private List<MGenericModel> dropdownLembagaPengelolaPensiun=new ArrayList<>();
    private List<MGenericModel> dropdownTreatmentRekening=new ArrayList<>();
    private List<MGenericModel> dropdownLngp=new ArrayList<>();
    private List<MGenericModel> dropdownJenisLkp=new ArrayList<>();

    public FragmentDataPensiunPrapen() {
    }

    public FragmentDataPensiunPrapen(DataInstansiDapen mdataLengkap, String maprroved) {
        dataInstansi = mdataLengkap;
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

        containerImageview=new ImageView(getContext());

        //view settings
        allOnClicks();
        disableEditTexts();
        defaultViewSettings();
        isiDropdown();
        loadDropdownLembagaPengelolaPensiun();

        if(dataInstansi!=null){
            setData();
        }


        if(!dataInstansi.getStatusId().equalsIgnoreCase("d.1")){
            noInputMode();
        }

//        setData();

        return view;
    }

    private void setData(){
        binding.etTotalPendapatan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalPendapatan));
        binding.etPerkiraanGaji.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etPerkiraanGaji));
        binding.etPerkiraanTunjangan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etPerkiraanTunjangan));
        try{
            binding.etLembagaPengelolaPensiun.setText(dataInstansi.getLembagaPengelolaPensiun());
            binding.etNomorPensiunan.setText(dataInstansi.getNomorPensiunan());
            binding.etNomorKepegawaian.setText(dataInstansi.getNomorKepegawaian());

            binding.etMenggunakanLngp.setText(dataInstansi.getIsLNGP());
            if(dataInstansi.getIsLNGP().equalsIgnoreCase("ya")){
                binding.tfInputLngp.setVisibility(View.VISIBLE);
                binding.btnCekEscrow.setVisibility(View.VISIBLE);
                binding.tfNamaInstansiLngp.setVisibility(View.VISIBLE);
                binding.tfRateLngp.setVisibility(View.VISIBLE);

                binding.etInputLngp.setText(dataInstansi.getNoLNGP());
                binding.etRateLngp.setText(Double.toString(dataInstansi.getRateLNGP()));
                binding.etEscrow.setText(dataInstansi.getEscrow());
                binding.etLkpDigunakan.setText(dataInstansi.getJenisLkp());
                dokumenLkp.setImg(dataInstansi.getLkpImg());
                dokumenLkp.setFile_Name(dataInstansi.getLkpFileName());

                try{
                    binding.tfTanggalExpired.setVisibility(View.VISIBLE);
                    binding.etTanggalExpired.setText(AppUtil.parseTanggalGeneral(dataInstansi.getTanggalExpiredLngp(),"yyyy-MM-dd","dd-MM-yyyy"));
                    binding.btnLihatLkp.setVisibility(View.VISIBLE);
                    binding.btnLihatLkp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            checkFileTypeThenSet(getContext(),dokumenLkp.getImg(),containerImageview,dokumenLkp.getFile_Name());
                        }
                    });
                }
                catch (NullPointerException e){
                    e.printStackTrace();
                }
                if(binding.etRateLngp.getText().toString().equalsIgnoreCase("null")){
                    binding.etRateLngp.setText("0");
                }
            }
            else{
                binding.tfInputLngp.setVisibility(View.GONE);
                binding.btnCekEscrow.setVisibility(View.GONE);
                binding.tfNamaInstansiLngp.setVisibility(View.GONE);
                binding.tfRateLngp.setVisibility(View.GONE);
            }



            binding.etNamaInstansiLngp.setText(dataInstansi.getNamaInstansiLNGP());
            binding.etNamaInstansi.setText(dataInstansi.getNamaInstansi());
            binding.etNomorTeleponInstansi.setText(dataInstansi.getNoTelpInstansi());
            binding.etKotaTempatBekerja.setText(dataInstansi.getKotaTempatBekerja());
            binding.etPerkiraanGaji.setText(dataInstansi.getPerkiraanGaji());
            binding.etPerkiraanTunjangan.setText(dataInstansi.getPerkiraanTunjangan());
            binding.etNasabahBsi.setText(dataInstansi.getIsNasabahBSI());
            binding.etTreatmentRekeningPendapatan.setText(dataInstansi.getTreatmentRekeningPendapata());
            binding.etNomorRekening.setText(dataInstansi.getNomorRekening());
            binding.etNominalKiriman.setText(dataInstansi.getNominalPerBulan());
            binding.etCatatanMemo.setText(dataInstansi.getCatatan());

            binding.etKeteranganSpan.setText(dataInstansi.getKeteranganSpan());
            if(dataInstansi.getKeteranganSpan().equalsIgnoreCase("Ditemukan")){
                binding.tfNamaSpan.setVisibility(View.VISIBLE);
                binding.tfRekeningSpan.setVisibility(View.VISIBLE);
                binding.tfGajiSpan.setVisibility(View.VISIBLE);
                binding.tfTunjanganSpan.setVisibility(View.VISIBLE);

                binding.etNamaSpan.setText(dataInstansi.getNamaSpan());
                binding.etGajiSpan.setText(dataInstansi.getGajiSpan());
                binding.etRekeningSpan.setText(dataInstansi.getRekeningSpan());
                binding.etTunjanganSpan.setText(dataInstansi.getTunjanganSpan());
            }

            if(dataInstansi.isVvip()){
                valVvip=true;
                binding.etNasabahVvip.setText("Ya");
            }
            else{
                valVvip=false;
                binding.etNasabahVvip.setText("Tidak");
            }



            val_cif=dataInstansi.getNoCIF();

            if(dataInstansi.getDapatBergerakAktifitas()){
                binding.etDapatBergerak.setText("Ya");
                valDapatBergerak=true;
            }
            else{
                binding.etDapatBergerak.setText("Tidak");
                valDapatBergerak=false;
            }

            if(dataInstansi.getDalamPengawasanDokter()){
                binding.etDalamPengawasan.setText("Ya");
                valDalamPengawasan=true;
            }
            else{
                binding.etDalamPengawasan.setText("Tidak");
                valDalamPengawasan=false;
            }

            if(dataInstansi.getRiwayatPenyakit()){
                binding.etMemilikiRiwayat.setText("Ya");
                valMemilikiRiwayat=true;
            }
            else{
                binding.etMemilikiRiwayat.setText("Tidak");
                valMemilikiRiwayat=false;
            }

            if(dataInstansi.getSatuRumah()){
                binding.etTinggalDenganKeluargaLain.setText("Ya");
                valSerumahDenganKeluarga=true;
            }
            else{
                binding.etTinggalDenganKeluargaLain.setText("Tidak");
                valSerumahDenganKeluarga=false;
            }

            if(dataInstansi.getMemilikiUsaha()){
                binding.etUsahaSampingan.setText("Ya");
                valMemilikiUsahaSampingan=true;
            }
            else{
                binding.etUsahaSampingan.setText("Tidak");
                valMemilikiUsahaSampingan=false;
            }

            if(dataInstansi.getKirimanRutin()){
                binding.etKirimanRutin.setText("Ya");
                valMemperolehKiriman=true;
            }
            else{
                binding.etKirimanRutin.setText("Tidak");
                valMemperolehKiriman=false;
            }

//
//            setValueYesNo(binding.etDapatBergerak,valDapatBergerak,dataInstansi.getDapatBergerakAktifitas());
//            setValueYesNo(binding.etDalamPengawasan,valDalamPengawasan,dataInstansi.getDalamPengawasanDokter());
//            setValueYesNo(binding.etMemilikiRiwayat,valMemilikiRiwayat,dataInstansi.getRiwayatPenyakit());
//            setValueYesNo(binding.etTinggalDenganKeluargaLain,valSerumahDenganKeluarga,dataInstansi.getSatuRumah());
//            setValueYesNo(binding.etKirimanRutin,valMemperolehKiriman,dataInstansi.getKirimanRutin());
//            setValueYesNo(binding.etUsahaSampingan,valMemilikiUsahaSampingan,dataInstansi.getMemilikiUsaha());
        }
        catch (Exception e){
            AppUtil.logSecure("error setdata",e.getMessage());
            e.printStackTrace();
        }

    }
    private void allOnClicks(){
        endIconOnClick();
        binding.etMenggunakanLngp.setOnClickListener(this);
        binding.tfMenggunakanLngp.setOnClickListener(this);

        binding.etNasabahBsi.setOnClickListener(this);
        binding.tfNasabahBsi.setOnClickListener(this);

        binding.etLkpDigunakan.setOnClickListener(this);
        binding.tfLkpDigunakan.setOnClickListener(this);

        binding.etInputLngp.setOnClickListener(this);
        binding.tfInputLngp.setOnClickListener(this);

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

        binding.etNasabahVvip.setOnClickListener(this);
        binding.tfNasabahVvip.setOnClickListener(this);

        binding.btnCekEscrow.setOnClickListener(this);
        binding.btnCekPayroll.setOnClickListener(this);
        binding.btnLihatLkp.setOnClickListener(this);

        binding.etTreatmentRekeningPendapatan.setOnClickListener(this);
        binding.tfTreatmentRekeningPendapatan.setOnClickListener(this);

        binding.etLembagaPengelolaPensiun.setOnClickListener(this);
        binding.tfLembagaPengelolaPensiun.setOnClickListener(this);

    }

    private void openDialogYaTidak(String title){
        DialogKeyValue.displayYaTidak(getFragmentManager(),title, this);
    }

    private VerificationError validateForm(){
//        adaFieldBelumDiisi=false;
//        validateField(binding.etLembagaPengelolaPensiun,binding.tfLembagaPengelolaPensiun);
//        validateField(binding.etNomorPensiunan,binding.tfNomorPensiunan);
//        validateField(binding.etNamaInstansi,binding.tfNamaInstansi);
//        validateField(binding.etMenggunakanLngp,binding.tfMenggunakanLngp);
//        if(!binding.etMenggunakanLngp.getText().toString().isEmpty()&&binding.etMenggunakanLngp.getText().toString().equalsIgnoreCase("ya")){
//            validateField(binding.etInputLngp,binding.tfInputLngp);
//            validateField(binding.etNamaInstansiLngp,binding.tfNamaInstansiLngp);
//            validateField(binding.etRateLngp,binding.tfRateLngp);
//        }
//        validateField(binding.etKotaTempatBekerja,binding.tfKotaTempatBekerja);
//        validateField(binding.etPerkiraanGaji,binding.tfPerkiraanGaji);
//        validateField(binding.etPerkiraanTunjangan,binding.tfPerkiraanTunjangan);
//        validateField(binding.etNasabahBsi,binding.tfNasabahBsi);
//        validateField(binding.etTreatmentRekeningPendapatan,binding.tfTreatmentRekeningPendapatan);



//        validateField(binding.etDapatBergerak,binding.tfDapatBergerak);
//        validateField(binding.etDalamPengawasan,binding.tfDalamPengawasan);
//        validateField(binding.etMemilikiRiwayat,binding.tfMemilikiRiwayat);
//        validateField(binding.etTinggalDenganKeluargaLain,binding.tfTinggalDenganKeluargaLain);
//        validateField(binding.etUsahaSampingan,binding.tfUsahaSampingan);
//        validateField(binding.etKirimanRutin,binding.tfKirimanRutin);
//        validateField(binding.etCatatanMemo,binding.tfCatatanMemo);

    if(!rekeningBerubah){
            setPojoData();
        return null;
        }
    else{
//        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), "Harap Cek Rekening Terlebih Dahulu");
        return new VerificationError("Cek rekening terlebih dahulu");
    }


//        }
    }

    public void setPojoData(){


        DataInstansiDapen d = realm.where(DataInstansiDapen.class).equalTo("applicationId", DataNasabahPrapenActivity.idAplikasi).findFirst();
        DataInstansiDapen copyRealm=new DataInstansiDapen();

        if(!realm.isInTransaction()){
            realm.beginTransaction();
        }

        if(d!=null){
            copyRealm=realm.copyFromRealm(d);
        }
        else{
            //karena id aplikasi primary key, jadi hanya ditambahkan jika sudah dipastikan data dari realm itu null
            copyRealm.setApplicationId((DataNasabahPrapenActivity.idAplikasi));
        }
        copyRealm.setLembagaPengelolaPensiun(binding.etLembagaPengelolaPensiun.getText().toString());
        copyRealm.setNomorPensiunan(binding.etNomorPensiunan.getText().toString());
        copyRealm.setNomorKepegawaian(binding.etNomorKepegawaian.getText().toString());
        copyRealm.setNamaInstansi(binding.etNamaInstansi.getText().toString());
        copyRealm.setNoTelpInstansi(binding.etNomorTeleponInstansi.getText().toString());
        copyRealm.setIsLNGP(binding.etMenggunakanLngp.getText().toString());
        if(binding.etMenggunakanLngp.getText().toString().equalsIgnoreCase("ya")){
            copyRealm.setRateLNGP(Double.parseDouble(binding.etRateLngp.getText().toString()));
            copyRealm.setNoLNGP(binding.etInputLngp.getText().toString());
            copyRealm.setNamaInstansiLNGP(binding.etNamaInstansiLngp.getText().toString());
            copyRealm.setEscrow(binding.etEscrow.getText().toString());
            copyRealm.setJenisLkp(binding.etLkpDigunakan.getText().toString());
            copyRealm.setLkpImg(dokumenLkp.getImg());
            copyRealm.setLkpFileName(dokumenLkp.getFile_Name());
            copyRealm.setTanggalExpiredLngp(AppUtil.parseTanggalGeneral(binding.etTanggalExpired.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"));
        }
        copyRealm.setKotaTempatBekerja(binding.etKotaTempatBekerja.getText().toString());
        copyRealm.setPerkiraanGaji(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etPerkiraanGaji.getText().toString()));
        copyRealm.setPerkiraanTunjangan(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etPerkiraanTunjangan.getText().toString()));
        copyRealm.setTotalPendapatan(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTotalPendapatan.getText().toString())));
        copyRealm.setIsNasabahBSI(binding.etNasabahBsi.getText().toString());
        copyRealm.setTreatmentRekeningPendapata(binding.etTreatmentRekeningPendapatan.getText().toString());
        copyRealm.setNomorRekening(binding.etNomorRekening.getText().toString());
        copyRealm.setNominalPerBulan(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etNominalKiriman.getText().toString()));
        copyRealm.setCatatan(binding.etCatatanMemo.getText().toString());

        copyRealm.setDapatBergerakAktifitas(valDapatBergerak);
        copyRealm.setDalamPengawasanDokter(valDalamPengawasan);
        copyRealm.setRiwayatPenyakit(valMemilikiRiwayat);
        copyRealm.setSatuRumah(valSerumahDenganKeluarga);
        copyRealm.setMemilikiUsaha(valMemilikiUsahaSampingan);
        copyRealm.setKirimanRutin(valMemperolehKiriman);
        copyRealm.setNoCIF(val_cif);
        copyRealm.setVvip(valVvip);
        copyRealm.setKeteranganSpan(binding.etKeteranganSpan.getText().toString());

        if(binding.etKeteranganSpan.getText().toString().equalsIgnoreCase("Ditemukan")){
            copyRealm.setNamaSpan(binding.etNamaSpan.getText().toString());
            copyRealm.setRekeningSpan(binding.etRekeningSpan.getText().toString());
            copyRealm.setGajiSpan(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etGajiSpan.getText().toString()));
            copyRealm.setTunjanganSpan(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etTunjanganSpan.getText().toString()));
        }



        realm.insertOrUpdate(copyRealm);

    }

    private void validateField(EditText editText, TextFieldBoxes textFieldBoxes){
        if(editText.getText().toString().isEmpty()){
            adaFieldBelumDiisi=true;
            AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), "Harap Isi "+textFieldBoxes.getLabelText());
        }
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
            if(data.getName().equalsIgnoreCase("ya")){
                binding.llLngp.setVisibility(View.VISIBLE);
                binding.tfInputLngp.setVisibility(View.VISIBLE);
                binding.btnCekEscrow.setVisibility(View.VISIBLE);
                binding.tfNamaInstansiLngp.setVisibility(View.VISIBLE);
                binding.tfRateLngp.setVisibility(View.VISIBLE);
            }
            else{
                binding.llLngp.setVisibility(View.GONE);
                binding.tfInputLngp.setVisibility(View.GONE);
                binding.btnCekEscrow.setVisibility(View.GONE);
                binding.tfNamaInstansiLngp.setVisibility(View.GONE);
                binding.tfRateLngp.setVisibility(View.GONE);
            }

//            val_Jenkel=KeyValue.getKeyJenisKelamin(et_jeniskelamin.getText().toString());
        }
        else if (title.equalsIgnoreCase(binding.tfNasabahBsi.getLabelText())){
            binding.etNasabahBsi.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(binding.tfNasabahVvip.getLabelText())){
            binding.etNasabahVvip.setText(data.getName());

            if(data.getName().equalsIgnoreCase("ya")){
                valVvip=true;
            }
            else{
                valVvip=false;
            }
        }
        else if (title.equalsIgnoreCase(binding.tfDapatBergerak.getLabelText())){
            binding.etDapatBergerak.setText(data.getName());

            if(data.getName().equalsIgnoreCase("ya")){
                valDapatBergerak=true;
            }
            else{
                valDapatBergerak=false;
            }

        }
        else if (title.equalsIgnoreCase(binding.tfDalamPengawasan.getLabelText())){
            binding.etDalamPengawasan.setText(data.getName());
            if(data.getName().equalsIgnoreCase("ya")){
                valDalamPengawasan=true;
            }
            else{
                valDalamPengawasan=false;
            }
        }
        else if (title.equalsIgnoreCase(binding.tfMemilikiRiwayat.getLabelText())){
            binding.etMemilikiRiwayat.setText(data.getName());
            if(data.getName().equalsIgnoreCase("ya")){
                valMemilikiRiwayat=true;
            }
            else{
                valMemilikiRiwayat=false;
            }
        }
        else if (title.equalsIgnoreCase(binding.tfTinggalDenganKeluargaLain.getLabelText())){
            binding.etTinggalDenganKeluargaLain.setText(data.getName());
            if(data.getName().equalsIgnoreCase("ya")){
                valSerumahDenganKeluarga=true;
            }
            else{
                valSerumahDenganKeluarga=false;
            }
        }
        else if (title.equalsIgnoreCase(binding.tfUsahaSampingan.getLabelText())){
            binding.etUsahaSampingan.setText(data.getName());
            if(data.getName().equalsIgnoreCase("ya")){
                valMemilikiUsahaSampingan=true;
            }
            else{
                valMemilikiUsahaSampingan=false;
            }
        }
        else if (title.equalsIgnoreCase(binding.tfKirimanRutin.getLabelText())){
            binding.etKirimanRutin.setText(data.getName());
            if(data.getName().equalsIgnoreCase("ya")){
                valMemperolehKiriman=true;
            }
            else{
                valMemperolehKiriman=false;
            }
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

            //NASABAH VVIP
            case R.id.et_nasabah_vvip:
            case R.id.tf_nasabah_vvip:
                openDialogYaTidak(binding.tfNasabahVvip.getLabelText());
                break;

            //NASABAH BSI
            case R.id.et_nasabah_bsi:
            case R.id.tf_nasabah_bsi:
                openDialogYaTidak(binding.tfNasabahBsi.getLabelText());
                break;

            //DAPAT BERGERAK
            case R.id.et_dapat_bergerak:
            case R.id.tf_dapat_bergerak:
                openDialogYaTidak(binding.tfDapatBergerak.getLabelText());
                break;

            //DALAM PENGAWASAN
            case R.id.et_dalam_pengawasan:
            case R.id.tf_dalam_pengawasan:
                openDialogYaTidak(binding.tfDalamPengawasan.getLabelText());
                break;

            //RIWAYAT
            case R.id.et_memiliki_riwayat:
            case R.id.tf_memiliki_riwayat:
                openDialogYaTidak(binding.tfMemilikiRiwayat.getLabelText());
                break;

            //KELUARGA LAIN
            case R.id.et_tinggal_dengan_keluarga_lain:
            case R.id.tf_tinggal_dengan_keluarga_lain:
                openDialogYaTidak(binding.tfTinggalDenganKeluargaLain.getLabelText());
                break;

            //USAHA SAMPINGAN
            case R.id.et_usaha_sampingan:
            case R.id.tf_usaha_sampingan:
                openDialogYaTidak(binding.tfUsahaSampingan.getLabelText());
                break;

            //KIRIMAN RUTIN
            case R.id.et_kiriman_rutin:
            case R.id.tf_kiriman_rutin:
                openDialogYaTidak(binding.tfKirimanRutin.getLabelText());
                break;
            //LMEBAGA PENGELOLA
            case R.id.et_lembaga_pengelola_pensiun:
            case R.id.tf_lembaga_pengelola_pensiun:
                DialogGenericDataFromService.display(getFragmentManager(),binding.tfLembagaPengelolaPensiun.getLabelText(),dropdownLembagaPengelolaPensiun,FragmentDataPensiunPrapen.this);
                break;
            //lngp
            case R.id.tf_input_lngp:
            case R.id.et_input_lngp:
                DialogGenericDataFromService.display(getFragmentManager(),binding.tfInputLngp.getLabelText(),dropdownLngp,FragmentDataPensiunPrapen.this);
                break;
            //LKP
            case R.id.tf_lkp_digunakan:
            case R.id.et_lkp_digunakan:
                DialogGenericDataFromService.display(getFragmentManager(),binding.tfLkpDigunakan.getLabelText(),dropdownJenisLkp,FragmentDataPensiunPrapen.this);
                break;
            //TREATMENT REKENING
            case R.id.et_treatment_rekening_pendapatan:
            case R.id.tf_treatment_rekening_pendapatan:
                DialogGenericDataFromService.display(getFragmentManager(),binding.tfTreatmentRekeningPendapatan.getLabelText(),dropdownTreatmentRekening,FragmentDataPensiunPrapen.this);
                break;
            case R.id.btn_cek_escrow:
                escrowBerubah=false;
                if(binding.etEscrow.getText().toString().isEmpty()){
                    AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content),"Harap isi rekening escrow terlebih dahulu");
                }
                else{
                    getListLngpByEscrow();
                }
                break;
            case R.id.btn_cek_payroll:
                rekeningBerubah=false;
                if(binding.etNomorRekening.getText().toString().isEmpty()){
                    AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content),"Harap isi no rekening");
                }
                else{
                    validasiPayroll();
                }
                break;
        }
    }

    private void disableEditTexts(){
        binding.etMenggunakanLngp.setFocusable(false);
        binding.etLembagaPengelolaPensiun.setFocusable(false);
        binding.etInputLngp.setFocusable(false);
        binding.etTanggalExpired.setFocusable(false);
        binding.etLkpDigunakan.setFocusable(false);
        binding.etNasabahBsi.setFocusable(false);
        binding.etTreatmentRekeningPendapatan.setFocusable(false);
        binding.etDapatBergerak.setFocusable(false);
        binding.etDalamPengawasan.setFocusable(false);
        binding.etMemilikiRiwayat.setFocusable(false);
        binding.etUsahaSampingan.setFocusable(false);
        binding.etTinggalDenganKeluargaLain.setFocusable(false);
        binding.etKirimanRutin.setFocusable(false);
        binding.etTotalPendapatan.setFocusable(false);
        binding.etRateLngp.setFocusable(false);
        binding.etNamaInstansiLngp.setFocusable(false);
        binding.etNamaRekening.setFocusable(false);
        binding.etNamaSpan.setFocusable(false);
        binding.etKeteranganSpan.setFocusable(false);
        binding.etRekeningSpan.setFocusable(false);
        binding.etGajiSpan.setFocusable(false);
        binding.etTunjanganSpan.setFocusable(false);
        binding.etNasabahVvip.setFocusable(false);
    }

    private void endIconOnClick(){
        binding.tfMenggunakanLngp.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogYaTidak(binding.tfMenggunakanLngp.getLabelText());
            }
        });

        binding.tfNasabahVvip.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogYaTidak(binding.tfNasabahVvip.getLabelText());
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

        binding.tfLkpDigunakan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getFragmentManager(),binding.tfLkpDigunakan.getLabelText(),dropdownJenisLkp,FragmentDataPensiunPrapen.this);
            }
        });

        binding.tfTreatmentRekeningPendapatan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getFragmentManager(),binding.tfTreatmentRekeningPendapatan.getLabelText(),dropdownTreatmentRekening,FragmentDataPensiunPrapen.this);
            }
        });

        binding.tfLembagaPengelolaPensiun.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getFragmentManager(),binding.tfLembagaPengelolaPensiun.getLabelText(),dropdownLembagaPengelolaPensiun,FragmentDataPensiunPrapen.this);
            }
        });

        binding.tfInputLngp.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getFragmentManager(),binding.tfInputLngp.getLabelText(),dropdownLngp,FragmentDataPensiunPrapen.this);
            }
        });
    }

    private void defaultViewSettings(){
        binding.tvHasilCekPayroll.setVisibility(View.GONE);
        binding.etNominalKiriman.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etNominalKiriman));
        binding.etPerkiraanGaji.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etPerkiraanGaji));
        binding.etPerkiraanTunjangan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etPerkiraanTunjangan));
        binding.etTotalPendapatan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTotalPendapatan));
        binding.etGajiSpan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etGajiSpan));
        binding.etTunjanganSpan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etTunjanganSpan));
        binding.tfInputLngp.setVisibility(View.GONE);
        binding.btnCekEscrow.setVisibility(View.GONE);
        binding.tfNamaInstansiLngp.setVisibility(View.GONE);
        binding.tfRateLngp.setVisibility(View.GONE);

        binding.tfTanggalExpired.setVisibility(View.GONE);
        binding.tfRateLngp.setVisibility(View.GONE);
        binding.btnLihatLkp.setVisibility(View.GONE);

        binding.tfNamaSpan.setVisibility(View.GONE);
        binding.tfRekeningSpan.setVisibility(View.GONE);
        binding.tfGajiSpan.setVisibility(View.GONE);
        binding.tfTunjanganSpan.setVisibility(View.GONE);

        binding.etNomorRekening.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                rekeningBerubah=true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etEscrow.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                escrowBerubah=true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        binding.etPerkiraanTunjangan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(!binding.etPerkiraanGaji.getText().toString().isEmpty()&&!binding.etPerkiraanTunjangan.getText().toString().isEmpty()){
                Long nilaiTotal=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etPerkiraanGaji.getText().toString()))+Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etPerkiraanTunjangan.getText().toString()));

                binding.etTotalPendapatan.setText(String.valueOf(nilaiTotal));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.etPerkiraanGaji.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!binding.etPerkiraanGaji.getText().toString().isEmpty()&&!binding.etPerkiraanTunjangan.getText().toString().isEmpty()){
                    Long nilaiTotal=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etPerkiraanGaji.getText().toString()))+Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etPerkiraanTunjangan.getText().toString()));

                    binding.etTotalPendapatan.setText(String.valueOf(nilaiTotal));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setValueYesNo(EditText editText,boolean valueString,Boolean kondisi){
        if(kondisi!=null&&kondisi){
            editText.setText("Ya");
            valueString=kondisi;
        }
        else{
            editText.setText("Tidak");
            valueString=kondisi;
        }
    }

    public void loadDropdownLembagaPengelolaPensiun() {
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        //pantekan no aplikasi dan aktifitas

        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().dropdownLembagaPengelolaPensiun(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DropdownGlobalPrapen>>() {
                        }.getType();
                        List<DropdownGlobalPrapen> dropdownTemp= gson.fromJson(listDataString, type);
                        dropdownLembagaPengelolaPensiun.clear();
                        for (int i = 0; i <dropdownTemp.size(); i++) {
                            dropdownLembagaPengelolaPensiun.add(new MGenericModel(dropdownTemp.get(i).getName(),dropdownTemp.get(i).getName()));
                        }
                        loadDropdownTreatmentRekening();
                    }
                    else{
                        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), "Terjadi kesalahan");
                
            }
        });
    }

    public void loadDropdownTreatmentRekening() {
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        //pantekan no aplikasi dan aktifitas

        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().dropdownTreatmentRekening(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DropdownGlobalPrapen>>() {
                        }.getType();
                        List<DropdownGlobalPrapen> dropdownTemp= gson.fromJson(listDataString, type);
                        dropdownTreatmentRekening.clear();
                        for (int i = 0; i <dropdownTemp.size(); i++) {
                            dropdownTreatmentRekening.add(new MGenericModel(dropdownTemp.get(i).getName(),dropdownTemp.get(i).getName()));
                        }

                    }
                    else{
                        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), "Terjadi kesalahan");
                
            }
        });
    }

    public void validasiPayroll() {
        //  dataUser = getListUser();
        binding.loadingPayroll.setVisibility(View.VISIBLE);
        //pantekan no aplikasi dan aktifitas
        ReqAcctNumber req=new ReqAcctNumber();
        req.setAccountNo(binding.etNomorRekening.getText().toString());

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().validasiPayroll(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingPayroll.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<DataCIfRekening>() {
                        }.getType();
                        DataCIfRekening dataCIfRekening =  gson.fromJson(listDataString, type);

                        if(dataCIfRekening.getCIF()!=null&&!dataCIfRekening.getCIF().isEmpty()){
                            binding.tvHasilCekPayroll.setVisibility(View.VISIBLE);
                            binding.tvHasilCekPayroll.setText("Rekening Ditemukan");
                            binding.tvHasilCekPayroll.setTextColor(getResources().getColor(R.color.main_green_color));
                            val_cif=dataCIfRekening.getCIF();

                            binding.etNamaRekening.setText(dataCIfRekening.getNama());
                            binding.etKeteranganSpan.setText(dataCIfRekening.getKeteranganSpan());

                            if(dataCIfRekening.getKeteranganSpan().equalsIgnoreCase("Ditemukan")){
                                binding.tfNamaSpan.setVisibility(View.VISIBLE);
                                binding.tfRekeningSpan.setVisibility(View.VISIBLE);
                                binding.tfGajiSpan.setVisibility(View.VISIBLE);
                                binding.tfTunjanganSpan.setVisibility(View.VISIBLE);

                                binding.etNamaSpan.setText(dataCIfRekening.getNamaSpan());
                                binding.etRekeningSpan.setText(dataCIfRekening.getRekeningSpan());
                                binding.etGajiSpan.setText(dataCIfRekening.getGajiSpan());
                                binding.etTunjanganSpan.setText(dataCIfRekening.getTunjanganSpan());
                            }

                            else{
                                binding.tfNamaSpan.setVisibility(View.GONE);
                                binding.tfRekeningSpan.setVisibility(View.GONE);
                                binding.tfGajiSpan.setVisibility(View.GONE);
                                binding.tfTunjanganSpan.setVisibility(View.GONE);
                            }


                        }
                        else{
                            binding.tvHasilCekPayroll.setVisibility(View.VISIBLE);
                            binding.tfNamaRekening.setVisibility(View.GONE);
                            binding.tvHasilCekPayroll.setText("Rekening Tidak Ditemukan");
                            binding.tvHasilCekPayroll.setTextColor(getResources().getColor(R.color.red_btn_bg_color));
                        }
                    }
                    else{
                        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                        binding.tvHasilCekPayroll.setVisibility(View.VISIBLE);
                        binding.tvHasilCekPayroll.setText("Rekening Tidak Ditemukan");
                        binding.tvHasilCekPayroll.setTextColor(getResources().getColor(R.color.red_btn_bg_color));
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingPayroll.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), "Terjadi kesalahan");
                
            }
        });
    }

    public void getListLngpByEscrow() {
        binding.loadingLngp.setVisibility(View.VISIBLE);
        ReqListLngp req=new ReqListLngp();
        req.setEscrow(binding.etEscrow.getText().toString());

        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().inquiryListLngp(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loadingLngp.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DataListLngp>>() {
                        }.getType();

                        List<DataListLngp> dataListLngp = gson.fromJson(listDataString, type);

                        dropdownLngp.clear();

                        if(dataListLngp.size()>0){
                            for (int i = 0; i <dataListLngp.size() ; i++) {
                                dropdownLngp.add(new MGenericModel(dataListLngp.get(i).getNoLngp(),dataListLngp.get(i).getNamaInstansi()));
                            }
                            binding.etInputLngp.setText("");
                            binding.etNamaInstansiLngp.setText("");
                            binding.etRateLngp.setText("");
                            binding.etTanggalExpired.setText("");
                            AppUtil.notifsuccess(getContext(), getActivity().findViewById(android.R.id.content), "Silahkan pilih LNGP");
                        }
                        else{
                            binding.etInputLngp.setText("");
                            binding.etNamaInstansiLngp.setText("");
                            binding.etRateLngp.setText("");
                            binding.etTanggalExpired.setText("");
                            AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), "Data LNGP tidak ditemukan");

                        }



                    }
                    else{
                        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loadingLngp.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), "Terjadi kesalahan");
            }
        });
    }

    public void validasiLngp() {
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        ReqValidasiLngp req=new ReqValidasiLngp();
        req.setApplicationid(DataNasabahPrapenActivity.idAplikasi);
        req.setUid(Integer.toString(appPreferences.getUid()));
        req.setNoLngp(binding.etInputLngp.getText().toString());

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().cekLngp(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {

                        try{
                            String instansiLngpString = response.body().getData().get("Nama_Instansi").toString().replace("\"","");
                            binding.etNamaInstansiLngp.setText(instansiLngpString);
                        }

                        catch (Exception e){
                            e.printStackTrace();
                        }

                        String rateLngpString = response.body().getData().get("Rate").toString().replace("\"","");
                        binding.tfRateLngp.setVisibility(View.VISIBLE);
                        binding.tfTanggalExpired.setVisibility(View.VISIBLE);
                        binding.etRateLngp.setText(rateLngpString);

                        String expiredLngp = response.body().getData().get("Tanggal_Expired").toString().replace("\"","");
                        binding.etTanggalExpired.setText(AppUtil.parseTanggalGeneral(expiredLngp,"ddMMyyyy","dd-MM-yyyy"));


                    }
                    else if(response.body().getStatus().equalsIgnoreCase("01")){
                        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), "Data LNGP tidak ditemukan");
                    }
                    else{
                        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                        binding.tvHasilCekPayroll.setVisibility(View.VISIBLE);
                        binding.tvHasilCekPayroll.setText("Rekening Tidak Ditemukan");
                        binding.tvHasilCekPayroll.setTextColor(getResources().getColor(R.color.red_btn_bg_color));
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), "Terjadi kesalahan");
                
            }
        });
    }

    private void noInputMode(){
        AppUtil.disableEditTexts(binding.getRoot());
        AppUtil.disableButtons(binding.getRoot());
    }

    private void isiDropdown(){
        dropdownJenisLkp.add(new MGenericModel("LKP Induk","LKP Induk"));
        dropdownJenisLkp.add(new MGenericModel("LKP Koordinasi","LKP Koordinasi"));
    }

    private void checkFileTypeThenSet(Context context, String idDok, ImageView imageView, String fileName){
        if(fileName.substring(fileName.length()-3,fileName.length()).equalsIgnoreCase("pdf")){

            if(idDok.length()<10){
                loadFileJson(idDok,imageView);
            }
            else{
                AppUtil.convertBase64ToFileDirect(context,idDok,imageView,fileName);
            }

        }
        else{

            if(idDok.length()<10){
                try{
                    DialogPreviewPhotoFromIdLogicalDoc.display(((AppCompatActivity) imageView.getContext()).getSupportFragmentManager(), "Preview Foto", idDok);
                }
                catch (ClassCastException e){
                    DialogPreviewPhotoFromIdLogicalDoc.display(((AppCompatActivity) imageView.getContext()).getSupportFragmentManager(), "Preview Foto", idDok);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            else{
                // TODO: 24/05/22
                //belum bisa handle kalau base 64 gaisss
//                AppUtil.convertBase64ToImage(idDok,imageView);
            }

        }
    }

    public void getLkp() {
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);

        ReqLkpD1 req=new ReqLkpD1();
        req.setEscrow(binding.etEscrow.getText().toString());
        req.setJenisLkp(binding.etLkpDigunakan.getText().toString());
        req.setKodeCabang(appPreferences.getKodeCabang());
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().getLkp(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = "{}";
                        try{
                           listDataString = response.body().getData().toString();
                        }
                        catch (NullPointerException e){
                            e.printStackTrace();
                        }
                        Gson gson = new Gson();
                        Type type = new TypeToken<UploadImage>() {}.getType();
                       dokumenLkp= gson.fromJson(listDataString, type);

                       if(dokumenLkp.getImg()!=null){
                           binding.btnLihatLkp.setVisibility(View.VISIBLE);

                           binding.btnLihatLkp.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {
                                   checkFileTypeThenSet(getContext(),dokumenLkp.getImg(),containerImageview,dokumenLkp.getFile_Name());
                               }
                           });
                       }
                       else{
                           AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), binding.etLkpDigunakan.getText().toString()+" Tidak Ditemukan,");
                           binding.btnLihatLkp.setVisibility(View.GONE);
                       }



                    }
                    else{
                        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), "Terjadi kesalahan");

            }
        });
    }

    public void loadFileJson(String idFoto,ImageView imageView) {
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        ApiClientAdapter apiClientAdapter=new ApiClientAdapter(getContext());
        Call<ParseResponseLogicalDoc> call = apiClientAdapter.getApiInterface().getFileJson(idFoto);
        call.enqueue(new Callback<ParseResponseLogicalDoc>() {
            @Override
            public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
//                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                    if (response.body().getBinaryData()!=null){
                        AppUtil.convertBase64ToFileDirect(getContext(),response.body().getBinaryData(),imageView,response.body().getFileName());
                    }
                    else{
                        Toast.makeText(getContext(), "Data PDF Tidak Didapatkan", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();

                t.printStackTrace();
            }
        });

    }

    @Override
    public void onSelect(String title, MGenericModel data) {
        if(title.equalsIgnoreCase(binding.tfLembagaPengelolaPensiun.getLabelText())){
            binding.etLembagaPengelolaPensiun.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfTreatmentRekeningPendapatan.getLabelText())){
            binding.etTreatmentRekeningPendapatan.setText(data.getDESC());
        }
        else if(title.equalsIgnoreCase(binding.tfInputLngp.getLabelText())){
            binding.etInputLngp.setText(data.getID());
            binding.etNamaInstansiLngp.setText(data.getDESC());
            validasiLngp();
        }
        else if(title.equalsIgnoreCase(binding.tfLkpDigunakan.getLabelText())){
            binding.etLkpDigunakan.setText(data.getDESC());
            getLkp();
        }
    }
}
