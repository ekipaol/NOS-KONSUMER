package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.datalengkap;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseDataDukcapil;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryNikPasangan;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.DataLengkapPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataDukcapilPasangan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataLengkap;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.Constants;
import com.application.bris.ikurma_nos_konsumer.util.KeyValue;
import com.application.bris.ikurma_nos_konsumer.util.Validator;
import com.google.gson.Gson;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")public class FragmentDataPribadi extends Fragment implements Step, KeyValueListener, View.OnClickListener, View.OnFocusChangeListener {

    @BindView(R.id.tf_nik)
    TextFieldBoxes tf_nik;
    @BindView(R.id.et_nik)
    EditText et_nik;
    @BindView(R.id.tf_expirednik)
    TextFieldBoxes tf_expirednik;
    @BindView(R.id.et_expirednik)
    EditText et_expirednik;
    @BindView(R.id.tf_npwp)
    TextFieldBoxes tf_npwp;
    @BindView(R.id.et_npwp)
    EditText et_npwp;
    @BindView(R.id.tf_nama)
    TextFieldBoxes tf_nama;
    @BindView(R.id.et_nama)
    EditText et_nama;
    @BindView(R.id.tf_namaalias)
    TextFieldBoxes tf_namaalias;
    @BindView(R.id.et_namaalias)
    EditText et_namaalias;
    @BindView(R.id.tf_tempatlahir)
    TextFieldBoxes tf_tempatlahir;
    @BindView(R.id.et_tempatlahir)
    EditText et_tempatlahir;
    @BindView(R.id.tf_tanggallahir)
    TextFieldBoxes tf_tanggallahir;
    @BindView(R.id.et_tanggallahir)
    EditText et_tanggallahir;
    @BindView(R.id.tf_jeniskelamin)
    TextFieldBoxes tf_jeniskelamin;
    @BindView(R.id.et_jeniskelamin)
    EditText et_jeniskelamin;
    @BindView(R.id.tf_nomorhp)
    TextFieldBoxes tf_nomorhp;
    @BindView(R.id.et_nomorhp)
    EditText et_nomorhp;
    @BindView(R.id.tf_email)
    TextFieldBoxes tf_email;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.tf_agama)
    TextFieldBoxes tf_agama;
    @BindView(R.id.et_agama)
    EditText et_agama;
    @BindView(R.id.tf_ketagama)
    TextFieldBoxes tf_ketagama;
    @BindView(R.id.et_ketagama)
    EditText et_ketagama;
    @BindView(R.id.tf_namaibukandung)
    TextFieldBoxes tf_namaibukandung;
    @BindView(R.id.et_namaibukandung)
    EditText et_namaibukandung;
    @BindView(R.id.tf_statusnikah)
    TextFieldBoxes tf_statusnikah;
    @BindView(R.id.et_statusnikah)
    EditText et_statusnikah;

    @BindView(R.id.btn_cek_nik_pasangan)
    Button btn_cek_nik_pasangan;

    @BindView(R.id.tf_nikpasangan)
    TextFieldBoxes tf_nikpasangan;
    @BindView(R.id.et_nikpasangan)
    EditText et_nikpasangan;
    @BindView(R.id.tf_namapasangan)
    TextFieldBoxes tf_namapasangan;
    @BindView(R.id.et_namapasangan)
    EditText et_namapasangan;
    @BindView(R.id.tf_tanggallahirpasangan)
    TextFieldBoxes tf_tanggallahirpasangan;
    @BindView(R.id.et_tanggallahirpasangan)
    EditText et_tanggallahirpasangan;
    @BindView(R.id.tf_namakeluarga)
    TextFieldBoxes tf_namakeluarga;
    @BindView(R.id.et_namakeluarga)
    EditText et_namakeluarga;
    @BindView(R.id.tf_nomorhpkeluarga)
    TextFieldBoxes tf_nomorhpkeluarga;
    @BindView(R.id.et_nomorhpkeluarga)
    EditText et_nomorhpkeluarga;
    @BindView(R.id.tf_jumlahtanggungan)
    TextFieldBoxes tf_jumlahtanggungan;
    @BindView(R.id.et_jumlahtanggungan)
    EditText et_jumlahtanggungan;
    @BindView(R.id.tf_tipependapatan)
    TextFieldBoxes tf_tipependapatan;
    @BindView(R.id.et_tipependapatan)
    EditText et_tipependapatan;
    @BindView(R.id.tf_pendidikanterakhir)
    TextFieldBoxes tf_pendidikanterakhir;
    @BindView(R.id.et_pendidikanterakhir)
    EditText et_pendidikanterakhir;
    @BindView(R.id.tf_referensi)
    TextFieldBoxes tf_referensi;
    @BindView(R.id.et_referensi)
    EditText et_referensi;
    @BindView(R.id.tf_gelar)
    TextFieldBoxes tf_gelar;
    @BindView(R.id.et_gelar)
    EditText et_gelar;

    @BindView(R.id.tf_jenis_kmg)
    TextFieldBoxes tf_jenis_kmg;
    @BindView(R.id.et_jenis_kmg)
    EditText et_jenis_kmg;

    @BindView(R.id.ll_pasangan)
    LinearLayout ll_pasangan;
    @BindView(R.id.ll_datapribadi)
    LinearLayout ll_datapribadi;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    @BindView(R.id.sv_content)
    ScrollView sv_content;

    private Calendar calLahirPasangan;
    private Calendar calLahir;
    private Calendar calExpiredNik;

    boolean nikPasanganBerubah=false;

    private DatePickerDialog dpTanggalLahirPasangan;
    private DatePickerDialog dpTanggalLahir;
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



    public FragmentDataPribadi() {
    }

//    public KmgFragmentDataPribadi(DataLengkap mdataLengkap) {
//        dataLengkap = mdataLengkap;
//    }

    public FragmentDataPribadi(DataLengkap mdataLengkap, String maprroved) {
        dataLengkap = mdataLengkap;
        approved = maprroved;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_datapribadi, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences=new AppPreferences(getContext());
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        onSelectDialog();
        if (approved.equalsIgnoreCase("no")) {
        }

        //ini method untuk long click listeners, biar mbak winda bisa ubah status nikah dan tanggal lahir jadi dibawah 21 tahun
        //comment below when done testing phase
//        Toast.makeText(getContext(), "Halaman aktif dengan parameter testing(tanggal lahir,status nikah)", Toast.LENGTH_LONG).show();
//        testingSpecialListeners();

        //pantekan qanun
//        dataLengkap.setProvPerusahaan("nanggroe aceh darussalam");


        //ini diatas karena dia on text change listener
//        setDynamicIcon();

        npwpFormattingTextChange(et_npwp);

        setData();

        //ini dibawah karena dia checking is empty atau tidak, jadi kalau diatas dia pasti nilainya isempty, padahal emang datanya belum diterima
//        setDynamicIconDropDown();


        return view;
    }

    private void setData(){

        et_nik.setText(dataLengkap.getNoKtp());
        et_expirednik.setText(AppUtil.parseTanggalGeneral(dataLengkap.getExpId(), "ddMMyyyy", "dd-MM-yyyy"));
//            et_npwp.setText(dataLengkap.getNpwp().replaceAll("[-.]", ""));
        et_npwp.setText(AppUtil.parseNpwp(dataLengkap.getNpwp()));
        et_nama.setText(dataLengkap.getNamaNasabah());
        et_namaalias.setText(dataLengkap.getNamaAlias());
        et_tempatlahir.setText(dataLengkap.getTptLahir());

        //pantekan tanggal lahir dan status nikah, comment this when done
//        dataLengkap.setTglLahir("22011996");
//        dataLengkap.setStatusNikah("1");


        //parameter untuk testing
        tglLahirOri=dataLengkap.getTglLahir();

        et_tanggallahir.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglLahir(), "ddMMyyyy", "dd-MM-yyyy"));
        et_statusnikah.setText(KeyValue.getKeyStatusNikah(dataLengkap.getStatusNikah()));

        et_jeniskelamin.setText(KeyValue.getKeyJenisKelamin(dataLengkap.getJenkel()));
        et_nomorhp.setText(dataLengkap.getNoHp());
        et_email.setText(dataLengkap.getEmail());
        et_agama.setText(KeyValue.getKeyAgama(dataLengkap.getAgama()));
        et_ketagama.setText(dataLengkap.getKetAgama());
        et_namaibukandung.setText(dataLengkap.getNamaIbu());
        et_nikpasangan.setText(dataLengkap.getNoKtpPasangan());
            et_namapasangan.setText(dataLengkap.getNamaPasangan());
            et_tanggallahirpasangan.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTgllahirPasangan(), "ddMMyyyy", "dd-MM-yyyy"));
        et_namakeluarga.setText(dataLengkap.getKeluarga());
        et_nomorhpkeluarga.setText(dataLengkap.getTelpKeluarga());
        et_jumlahtanggungan.setText(String.valueOf(dataLengkap.getJlhTanggungan()));
//            et_tipependapatan.setText(KeyValue.getKeyTipePendapatan(dataLengkap.getTipePendapatan()));
        et_pendidikanterakhir.setText(KeyValue.getKeyPendidikanTerakhir(dataLengkap.getPendidikanTerakhir()));
        et_referensi.setText(KeyValue.getKeyReferensi(dataLengkap.getReferensi()));



        if (dataLengkap.getStatusNikah().equalsIgnoreCase("2")){
            ll_pasangan.setVisibility(View.VISIBLE);
//            et_namapasangan.setFocusable(false);
            et_tanggallahirpasangan.setFocusable(false);
            btn_cek_nik_pasangan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //kalo nik masi kosong udah main pencat pencet bae, validasi
                    if(et_nikpasangan.getText().toString().isEmpty()){
                        tf_nikpasangan.setError("Isi NIK pasangan",true);
                    }

                    //kalau format nik gak bener, validasi
                    else if(!Validator.validateKtpRequired(et_nikpasangan.getText().toString().trim())){
                        tf_nikpasangan.setError("Format NIK belum benar",true);
                    }

                    else{
                        //kalo udah ada pasangan, gausah  pencet lagi ni tombol, eh gajadi deng, bisa aja dia salah ketik, jadi di comment dulu
//                        if(!et_namapasangan.getText().toString().isEmpty()){
//                            AppUtil.notifinfo(getContext(),getActivity().findViewById(android.R.id.content),"Data Pasangan Sudah Didapatkan");
//                        }
//                        else{
                            cekDukcapilPasangan();

//                        }
                    }


                }
            });
        }
        if (dataLengkap.getAgama().equalsIgnoreCase("ZZZ")){
            tf_ketagama.setVisibility(View.VISIBLE);
        }

        if (approved.equalsIgnoreCase("yes")){
            AppUtil.disableEditTexts(ll_datapribadi);
        }

        //info onclick nama pasangan dan tanggal lahir pasangan
//        tf_namapasangan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "Klik tombol cek NIK Pasangan, untuk mengisi nama dan tanggal lahir", Toast.LENGTH_LONG).show();
//            }
//        });
//
//
//        tf_tanggallahirpasangan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "Klik tombol cek NIK Pasangan, untuk mengisi nama dan tanggal lahir", Toast.LENGTH_LONG).show();
//            }
//        });

        //listener jika ngubah nik pasangan, maka status diubah

        et_nikpasangan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (dataLengkap.getStatusNikah().equalsIgnoreCase("2")){
                    nikPasanganBerubah=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void cekDukcapilPasangan() {
        loading.setVisibility(View.VISIBLE);
        inquiryNikPasangan req = new inquiryNikPasangan(et_nikpasangan.getText().toString().trim());

        Call<ParseResponseDataDukcapil> call = apiClientAdapter.getApiInterface().cekNikPasangan(req);
        call.enqueue(new Callback<ParseResponseDataDukcapil>() {
            @Override
            public void onResponse(Call<ParseResponseDataDukcapil> call, Response<ParseResponseDataDukcapil> response) {
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            loading.setVisibility(View.GONE);
                            AppUtil.notifsuccess(getContext(),getActivity().findViewById(android.R.id.content),"Cek DUKCAPIL Pasangan Berhasil");
                            Gson gson = new Gson();
                            dataDukcapilString = response.body().getDukcapil().toString();
                            dataDukcapilPasangan = gson.fromJson(dataDukcapilString, DataDukcapilPasangan.class);

                            et_namapasangan.setText(dataDukcapilPasangan.getNama());
                            et_tanggallahirpasangan.setText(AppUtil.parseTanggalGeneral(dataDukcapilPasangan.getTgl_lahir(), "yyyy-MM-dd", "dd-MM-yyyy"));

                            nikPasanganBerubah=false;

                            //save ke realm ketika sukses dapat nik nasabah

                            try {
                                DataLengkapPojo saveNikPasangan =realm.where(DataLengkapPojo.class).equalTo("noKtpPasangan", et_nikpasangan.getText().toString()).findFirst();
                                DataLengkapPojo saveNikPasanganCopy=new DataLengkapPojo();

                                if(saveNikPasangan!=null){
                                    saveNikPasanganCopy=realm.copyFromRealm(saveNikPasangan);
                                }
                                realm.beginTransaction();
                                saveNikPasanganCopy.setNoKtpPasangan(et_nikpasangan.getText().toString());
                                realm.insertOrUpdate(saveNikPasanganCopy);
                                realm.commitTransaction();
                                realm.close();
                            }
                            catch (NullPointerException e){
                                Log.d("ada error di ","realm pasangan");
                            }

                        }
                        else{
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());

                        }
                    }
                    else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseDataDukcapil> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), getString(R.string.txt_connection_failure));

            }
        });
    }

    private void dpExpiredNik(){
        calExpiredNik = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ls_tanggalLahir = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calExpiredNik.set(Calendar.YEAR, year);
                calExpiredNik.set(Calendar.MONTH, month);
                calExpiredNik.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calLahirString = dateClient.format(calExpiredNik.getTime());
                et_expirednik.setText(calLahirString);
            }
        };

        dpExpiredNik = new DatePickerDialog(getContext(), R.style.AppTheme_TimePickerTheme, ls_tanggalLahir, calExpiredNik.get(Calendar.YEAR),
                calExpiredNik.get(Calendar.MONTH), calExpiredNik.get(Calendar.DAY_OF_MONTH));
        dpExpiredNik.getDatePicker().setMinDate(calExpiredNik.getTimeInMillis());
        dpExpiredNik.show();
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
                et_tanggallahirpasangan.setText(calLahirPasanganString);

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
                et_tanggallahir.setText(calLahirString);
            }
        };

        dpTanggalLahir = new DatePickerDialog(getContext(), R.style.AppTheme_TimePickerTheme, ls_tanggalLahirPasangan, calLahir.get(Calendar.YEAR),
                calLahir.get(Calendar.MONTH), calLahir.get(Calendar.DAY_OF_MONTH));
        dpTanggalLahir.getDatePicker().setMaxDate(calLahir.getTimeInMillis());
        dpTanggalLahir.show();
    }

    private void onSelectDialog(){

//        et_npwp.addTextChangedListener(new NpwpTextWatcher(et_npwp));
//        et_npwp.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DEL)
//                {
//                    et_npwp.getText().clear();
//                }
//                return false;
//            }
//        });
        et_npwp.setLongClickable(false);
        et_npwp.setTextIsSelectable(false);

        //EXPIRED NIK
        et_expirednik.setFocusable(false);
        et_expirednik.setInputType(InputType.TYPE_NULL);
        et_expirednik.setOnFocusChangeListener(this);

        //TANGGAL LAHIR PASANGAN
//        et_tanggallahirpasangan.setFocusable(false);
//        et_tanggallahirpasangan.setInputType(InputType.TYPE_NULL);
//        et_tanggallahirpasangan.setOnFocusChangeListener(this);

        //REFERENSI
        et_referensi.setFocusable(false);
        et_referensi.setInputType(InputType.TYPE_NULL);
        et_referensi.setOnFocusChangeListener(this);

        //PENDIDIKAN TERAKHIR
        et_pendidikanterakhir.setFocusable(false);
        et_pendidikanterakhir.setInputType(InputType.TYPE_NULL);
        et_pendidikanterakhir.setOnFocusChangeListener(this);

        //AGAMA
        et_agama.setFocusable(false);
        et_agama.setInputType(InputType.TYPE_NULL);
        et_agama.setOnFocusChangeListener(this);

        //TANGGAL LAHIR
        et_tanggallahir.setFocusable(false);
        et_tanggallahir.setInputType(InputType.TYPE_NULL);
        et_tanggallahir.setOnFocusChangeListener(this);

        //TIPE PENDAPATAN dipantek "pendapatan tetap"
        et_tipependapatan.setFocusable(false);
        et_tipependapatan.setInputType(InputType.TYPE_NULL);
        val_TipePendapatan="pendapatan tetap";
//        et_tipependapatan.setOnClickListener(this);
//        et_tipependapatan.setOnFocusChangeListener(this);
//        tf_tipependapatan.setOnClickListener(this);
//        tf_tipependapatan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openKeyValueDialog(tf_tipependapatan.getLabelText().toString().trim());
//            }
//        });

        //SET DROPDOWN OPTION
        if (approved.equalsIgnoreCase("no")){
            et_expirednik.setOnClickListener(this);
            tf_expirednik.setOnClickListener(this);
            tf_expirednik.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dpExpiredNik();
                }
            });

            et_agama.setOnClickListener(this);
            tf_agama.setOnClickListener(this);
            tf_agama.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openKeyValueDialog(tf_agama.getLabelText().toString().trim());
                }
            });

            et_tipependapatan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "Tipe Pendapatan KMG EmBP Otomatis Diisi Pendapatan Tetap", Toast.LENGTH_LONG).show();
                }
            });
            tf_tipependapatan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Tipe Pendapatan KMG EmBP Otomatis Diisi Pendapatan Tetap", Toast.LENGTH_LONG).show();
                }
            });
            tf_tipependapatan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Tipe Pendapatan KMG EmBP Otomatis Diisi Pendapatan Tetap", Toast.LENGTH_LONG).show();
                }
            });

            et_referensi.setOnClickListener(this);
            tf_referensi.setOnClickListener(this);
            tf_referensi.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openKeyValueDialog(tf_referensi.getLabelText().toString().trim());
                }
            });

            et_pendidikanterakhir.setOnClickListener(this);
            tf_pendidikanterakhir.setOnClickListener(this);
            tf_pendidikanterakhir.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openKeyValueDialog(tf_pendidikanterakhir.getLabelText().toString().trim());
                }
            });
        }

        //ambil flag enable text
        if(dataLengkap.getEnableNamaNasabah()!=null&&dataLengkap.getEnableNamaNasabah().equalsIgnoreCase("1")){
            et_nama.setFocusable(true);
            tf_nama.setPanelBackgroundColor(getResources().getColor(R.color.colorBgEdittext));
        }
        else{
            et_nama.setInputType(InputType.TYPE_NULL);
            et_nama.setFocusable(false);
        }

        if(dataLengkap.getEnableTptlahir()!=null&&dataLengkap.getEnableTptlahir().equalsIgnoreCase("1")){
            et_tempatlahir.setFocusable(true);
            tf_tempatlahir.setPanelBackgroundColor(getResources().getColor(R.color.colorBgEdittext));
        }
        else{
            et_tempatlahir.setInputType(InputType.TYPE_NULL);
            et_tempatlahir.setFocusable(false);
        }


        if(dataLengkap.getEnableTglLahir()!=null&&dataLengkap.getEnableTglLahir().equalsIgnoreCase("1")){

            tf_tanggallahir.setPanelBackgroundColor(getResources().getColor(R.color.colorBgEdittext));
            //TANGGAL LAHIR
            et_tanggallahir.setFocusable(false);
            et_tanggallahir.setInputType(InputType.TYPE_NULL);
            et_tanggallahir.setOnFocusChangeListener(this);
            tf_tanggallahir.setOnClickListener(this);
            tf_tanggallahir.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dpCalLahir();
                }
            });
        }
        else{
            et_tanggallahir.setInputType(InputType.TYPE_NULL);
            et_tanggallahir.setFocusable(false);
        }

        if(dataLengkap.getEnableJenkel()!=null&&dataLengkap.getEnableJenkel().equalsIgnoreCase("1")){

            tf_jeniskelamin.setPanelBackgroundColor(getResources().getColor(R.color.colorBgEdittext));
            //STATUS NIKAH
            et_jeniskelamin.setFocusable(false);
            et_jeniskelamin.setInputType(InputType.TYPE_NULL);
            et_jeniskelamin.setOnFocusChangeListener(this);
            tf_jeniskelamin.setOnClickListener(this);
            tf_jeniskelamin.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openKeyValueDialog(tf_jeniskelamin.getLabelText().toString().trim());
                }
            });
        }
        else{
            et_jeniskelamin.setInputType(InputType.TYPE_NULL);
            et_jeniskelamin.setFocusable(false);
        }

        if(dataLengkap.getEnableNamaIbu()!=null&&dataLengkap.getEnableNamaIbu().equalsIgnoreCase("1")){
            et_namaibukandung.setFocusable(true);
            tf_namaibukandung.setPanelBackgroundColor(getResources().getColor(R.color.colorBgEdittext));
        }
        else{
            et_namaibukandung.setInputType(InputType.TYPE_NULL);
            et_namaibukandung.setFocusable(false);
        }

        if(dataLengkap.getEnableStatusNikah()!=null&&dataLengkap.getEnableStatusNikah().equalsIgnoreCase("1")){
            tf_statusnikah.setPanelBackgroundColor(getResources().getColor(R.color.colorBgEdittext));
            tf_namapasangan.setPanelBackgroundColor(getResources().getColor(R.color.colorBgEdittext));
            tf_tanggallahirpasangan.setPanelBackgroundColor(getResources().getColor(R.color.colorBgEdittext));

            //NAMA PASANGAN
            et_namapasangan.setFocusable(true);

            //STATUS NIKAH
            btn_cek_nik_pasangan.setVisibility(View.GONE);
            et_statusnikah.setFocusable(false);
            et_statusnikah.setInputType(InputType.TYPE_NULL);
            et_statusnikah.setOnFocusChangeListener(this);
            tf_statusnikah.setOnClickListener(this);
            tf_statusnikah.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openKeyValueDialog(tf_statusnikah.getLabelText().toString().trim());
                }
            });

            //TANGGAL LAHIR PASANGAN
            et_tanggallahirpasangan.setFocusable(false);
            et_tanggallahirpasangan.setInputType(InputType.TYPE_NULL);
            et_tanggallahirpasangan.setOnFocusChangeListener(this);
            tf_tanggallahirpasangan.setOnClickListener(this);
            tf_tanggallahirpasangan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dpCalLahirPasangan();
                }
            });

//
        }
        else{
            //NAMA PASANGAN
            et_namapasangan.setFocusable(false);
            et_statusnikah.setInputType(InputType.TYPE_NULL);
            et_statusnikah.setFocusable(false);
        }




    }

    private void openKeyValueDialog(String title){
        DialogKeyValue.display(getFragmentManager(), title, this);
    }

    private VerificationError validateForm(){
        if (Validator.validateKtpRequired(et_nik.getText().toString().trim()) == false){
            tf_nik.setError("Format "+ tf_nik.getLabelText()+" "+getString(R.string.title_invalid_field), true);
            return new VerificationError("Format "+ tf_nik.getLabelText()+" "+getString(R.string.title_invalid_field));
        }
        else if (et_expirednik.getText().toString().isEmpty() || et_expirednik.getText().toString().equalsIgnoreCase("")){
            tf_expirednik.setError("Format "+ tf_expirednik.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_expirednik.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_expirednik.getText().toString().isEmpty() || et_expirednik.getText().toString().equalsIgnoreCase("")){
            tf_expirednik.setError("Format "+ tf_expirednik.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_expirednik.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_npwp.getText().toString().isEmpty() || et_npwp.getText().toString().equalsIgnoreCase("")){
            tf_npwp.setError("Format "+ tf_npwp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_npwp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (!Validator.validateNpwpRequired(DataLengkapActivity.plafond >= Constants.VALIDATE_PLAFOND_NPWP, et_npwp.getText().toString().trim())){
            tf_npwp.setError("Format "+ tf_npwp.getLabelText()+" "+getString(R.string.title_invalid_field), true);
            return new VerificationError("Format "+ tf_npwp.getLabelText()+" "+getString(R.string.title_invalid_field));
        }

        else if (et_nama.getText().toString().isEmpty() || et_nama.getText().toString().equalsIgnoreCase("")){
            tf_nama.setError("Format "+ tf_nama.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nama.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_namaalias.getText().toString().isEmpty() || et_namaalias.getText().toString().equalsIgnoreCase("")){
            tf_namaalias.setError("Format "+ tf_namaalias.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_namaalias.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_tempatlahir.getText().toString().isEmpty() || et_tempatlahir.getText().toString().equalsIgnoreCase("")){
            tf_tempatlahir.setError("Format "+ tf_tempatlahir.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_tempatlahir.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_tanggallahir.getText().toString().isEmpty() || et_tanggallahir.getText().toString().equalsIgnoreCase("")){
            tf_tanggallahir.setError("Format "+ tf_tanggallahir.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_tanggallahir.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_jeniskelamin.getText().toString().isEmpty() || et_jeniskelamin.getText().toString().equalsIgnoreCase("")){
            tf_jeniskelamin.setError("Format "+ tf_jeniskelamin.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_jeniskelamin.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_nomorhp.getText().toString().isEmpty() || et_nomorhp.getText().toString().equalsIgnoreCase("")){
            tf_nomorhp.setError("Format "+ tf_nomorhp.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nomorhp.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_agama.getText().toString().isEmpty() || et_agama.getText().toString().equalsIgnoreCase("")){
            tf_agama.setError("Format "+ tf_agama.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_agama.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (tf_ketagama.getVisibility() == View.VISIBLE && (et_ketagama.getText().toString().isEmpty() || et_ketagama.getText().toString().equalsIgnoreCase(""))){
            tf_ketagama.setError("Format "+ tf_ketagama.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_ketagama.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_namaibukandung.getText().toString().isEmpty() || et_namaibukandung.getText().toString().equalsIgnoreCase("")){
            tf_namaibukandung.setError("Format "+ tf_namaibukandung.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_namaibukandung.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_statusnikah.getText().toString().isEmpty() || et_statusnikah.getText().toString().equalsIgnoreCase("")){
            tf_statusnikah.setError("Format "+ tf_statusnikah.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_statusnikah.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (ll_pasangan.getVisibility() == View.VISIBLE && Validator.validateKtpRequired(et_nikpasangan.getText().toString().trim()) == false){
            tf_nikpasangan.setError("Format "+ tf_nikpasangan.getLabelText()+" "+getString(R.string.title_invalid_field), true);
            return new VerificationError("Format "+ tf_nikpasangan.getLabelText()+" "+getString(R.string.title_invalid_field));
        }

        else if (ll_pasangan.getVisibility() == View.VISIBLE && (et_namapasangan.getText().toString().isEmpty() || et_namapasangan.getText().toString().equalsIgnoreCase(""))){
            tf_namapasangan.setError("Format "+ tf_namapasangan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_namapasangan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (ll_pasangan.getVisibility() == View.VISIBLE && (et_tanggallahirpasangan.getText().toString().isEmpty() || et_tanggallahirpasangan.getText().toString().equalsIgnoreCase(""))){
            tf_tanggallahirpasangan.setError("Format "+ tf_tanggallahirpasangan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_tanggallahirpasangan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_namakeluarga.getText().toString().isEmpty() || et_namakeluarga.getText().toString().equalsIgnoreCase("")){
            tf_namakeluarga.setError("Format "+ tf_namakeluarga.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_namakeluarga.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_nomorhpkeluarga.getText().toString().isEmpty() || et_nomorhpkeluarga.getText().toString().equalsIgnoreCase("")){
            tf_nomorhpkeluarga.setError("Format "+ tf_nomorhpkeluarga.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nomorhpkeluarga.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_jumlahtanggungan.getText().toString().isEmpty() || et_jumlahtanggungan.getText().toString().equalsIgnoreCase("")){
            tf_jumlahtanggungan.setError("Format "+ tf_jumlahtanggungan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_jumlahtanggungan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_tipependapatan.getText().toString().isEmpty() || et_tipependapatan.getText().toString().equalsIgnoreCase("")){
            tf_tipependapatan.setError("Format "+ tf_tipependapatan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_tipependapatan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_pendidikanterakhir.getText().toString().isEmpty() || et_pendidikanterakhir.getText().toString().equalsIgnoreCase("")){
            tf_pendidikanterakhir.setError("Format "+ tf_pendidikanterakhir.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_pendidikanterakhir.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_referensi.getText().toString().isEmpty() || et_referensi.getText().toString().equalsIgnoreCase("Pilih")){
            tf_referensi.setError("Format "+ tf_referensi.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_referensi.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        //cek nik pasangan yang diedit untuk dilakukan cek dulu
        DataLengkapPojo nikPasangan =realm.where(DataLengkapPojo.class).equalTo("noKtpPasangan", et_nikpasangan.getText().toString()).findFirst();

        if(nikPasangan!=null&&!nikPasangan.getNoKtpPasangan().equalsIgnoreCase(et_nikpasangan.getText().toString())){
            tf_nikpasangan.setError("Klik tombol cek NIK setelah mengubah NIK pasangan", true);
            return new VerificationError("Klik tombol cek NIK setelah mengubah NIK pasangan");
        }

        //cek kalau belum klik tombol, setelah merubah nik pasangan
//        else if(nikPasanganBerubah){
//            et_nikpasangan.requestFocus();
//            return new VerificationError("Harap klik tombol cek nik pasangan terlebih dahulu");
//
//        }


        //validasi umur nasabah , di comment dlu, karena sudah ada di servicenya
//        hitungUmur();

//        if(umur<21&&dataLengkap.getStatusNikah().equalsIgnoreCase("1")){
//
//            //validasi kalo dia PNS, dan berasal dari aceh, maka ga kena validasi umur , BELUM ADA FLAG QANUN,
//            // jadi tau dia aceh dari provinsi perusahaan, pasti dari Nanggroe aceh darussalam, harus prov perusahaan, jgn prov domisili/ktp
//            if((dataLengkap.getJenisPekerjaan().equalsIgnoreCase("LEMBAGA PEMERINTAH")||dataLengkap.getJenisPekerjaan().equalsIgnoreCase("Lembaga Departemen, Non Departemen, Pemda")||dataLengkap.getJenisPekerjaan().equalsIgnoreCase("TNI, Polri, Kejaksaan, PN, PT, MA"))&& dataLengkap.getProvPerusahaan().equalsIgnoreCase("NANGGROE ACEH DARUSSALAM")){
//
////                Toast.makeText(getContext(), "Nasabah lolos validasi usia, pastikan nasabah memiliki persetujuan dari Orang tua/Wali", Toast.LENGTH_LONG).show();
//                AppUtil.notifinfoLong(getContext(),getActivity().findViewById(android.R.id.content),"Nasabah lolos validasi usia, pastikan nasabah memiliki persetujuan dari Orang tua/Wali");
//                setDataOnListerner();
//                return null;
//            }
//            else{
//                return new VerificationError("Umur nasabah dibawah 21 dan belum menikah, pembiayaan tidak bisa dilanjutkan");
//            }
//
//        }
        else {
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner(){
        val_NamaAlias = (et_namaalias.getText().toString().trim());
        val_NoKtpPasangan = (et_nikpasangan.getText().toString().trim());
        val_StatusNikah = (KeyValue.getTypeStatusNikah(et_statusnikah.getText().toString().trim()));
        val_NoHp = (et_nomorhp.getText().toString().trim());
        val_NamaNasabah = (et_nama.getText().toString().trim());
        val_Npwp = (et_npwp.getText().toString().trim());
        val_PendTerakhir = (KeyValue.getTypePendidikanTerakhir(et_pendidikanterakhir.getText().toString().trim()));
        val_KetGelar = (et_gelar.getText().toString().trim());
        val_KetAgama = (et_ketagama.getText().toString().trim());
        val_Agama = (KeyValue.getTypeAgama(et_agama.getText().toString().trim()));
        val_NamaIbu = (et_namaibukandung.getText().toString().trim());
        val_NamaPasangan = (et_namapasangan.getText().toString().trim());
        val_Email = (et_email.getText().toString().trim());
        val_TelpKeluarga = (et_nomorhpkeluarga.getText().toString().trim());
        val_ExpId = (AppUtil.parseTanggalGeneral(et_expirednik.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
        val_TglLahirPasangan = (AppUtil.parseTanggalGeneral(et_tanggallahirpasangan.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
        val_TglLahir = (AppUtil.parseTanggalGeneral(et_tanggallahir.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
        val_NoKtp = (et_nik.getText().toString().trim());
        val_JlhTanggungan = et_jumlahtanggungan.getText().toString();
        val_Keluarga = (et_namakeluarga.getText().toString().trim());
        val_TptLahir = (et_tempatlahir.getText().toString().trim());
        val_TipePendapatan = (KeyValue.getTypeTipePendapatan(et_tipependapatan.getText().toString().trim()));
        val_Jenkel = (KeyValue.getTypeJenisKelamin(et_jeniskelamin.getText().toString().trim()));
        val_Referensi = (KeyValue.getTypeReferensi(et_referensi.getText().toString().trim()));


//        final DataLengkapPojo d = new DataLengkapPojo();

        //pantekan
//        d.setCif("81272");
//        d.setUid(7983);

        DataLengkapPojo d = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", DataLengkapActivity.idAplikasi).findFirst();
        DataLengkapPojo copyRealm=new DataLengkapPojo();

        if(!realm.isInTransaction()){
            realm.beginTransaction();
        }

        if(d!=null){
            copyRealm=realm.copyFromRealm(d);
        }
        else{
            //karena id aplikasi primary key, jadi hanya ditambahkan jika sudah dipastikan data dari realm itu null
//            realm.beginTransaction();
            copyRealm.setIdAplikasi(DataLengkapActivity.idAplikasi);
        }



        copyRealm.setCif(DataLengkapActivity.cif);
        copyRealm.setUid(DataLengkapActivity.uid);
//        d.setIdAplikasi(DataLengkapActivity.idAplikasi);
        copyRealm.setNamaAlias(val_NamaAlias);
        copyRealm.setNoKtpPasangan(val_NoKtpPasangan);
        copyRealm.setStatusNikah(val_StatusNikah);
        copyRealm.setNoHp(val_NoHp);
        copyRealm.setNamaNasabah(val_NamaNasabah);
        copyRealm.setNpwp(val_Npwp);
        copyRealm.setPendidikanTerakhir(val_PendTerakhir);
        copyRealm.setKetAgama(val_KetAgama);
        copyRealm.setAgama(val_Agama);
        copyRealm.setNamaIbu(val_NamaIbu);
        copyRealm.setNamaPasangan(val_NamaPasangan);
        copyRealm.setEmail(val_Email);
        copyRealm.setTelpKeluarga(val_TelpKeluarga);
        copyRealm.setExpId(val_ExpId);
        copyRealm.setTgllahirPasangan(val_TglLahirPasangan);
        copyRealm.setNoKtp(val_NoKtp);
        copyRealm.setJlhTanggungan(val_JlhTanggungan);
        copyRealm.setTglLahir(val_TglLahir);
        copyRealm.setKeluarga(val_Keluarga);
        copyRealm.setTptLahir(val_TptLahir);
        copyRealm.setTipePendapatan(val_TipePendapatan);
        copyRealm.setJenkel(val_Jenkel);
        copyRealm.setReferensi(Integer.parseInt(val_Referensi));

        realm.insertOrUpdate(copyRealm);
        realm.close();
//        realm.commitTransaction();
//        realm.close();


//        DataLengkapPojo data = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", DataLengkapActivity.idAplikasi).findFirst();
//        realm.executeTransaction (new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.insertOrUpdate(d);
//            }
//        });
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
        if (title.equalsIgnoreCase("jenis kelamin")){
            et_jeniskelamin.setText(data.getName());

            val_Jenkel=KeyValue.getKeyJenisKelamin(et_jeniskelamin.getText().toString());
        }
        else if (title.equalsIgnoreCase("agama")){
            et_agama.setText(data.getName());
            val_Agama=KeyValue.getKeyAgama(et_agama.getText().toString());

            if (data.getValue().equalsIgnoreCase("ZZZ")){
                tf_ketagama.setVisibility(View.VISIBLE);
            }
            else {
                tf_ketagama.setVisibility(View.GONE);
                val_KetAgama=KeyValue.getKeyPendidikanTerakhir(et_ketagama.getText().toString());
            }
        }
        else if (title.equalsIgnoreCase("status nikah")){
            et_statusnikah.setText(data.getName());
            if(data.getValue().equalsIgnoreCase("2")){
                ll_pasangan.setVisibility(View.VISIBLE);
            }
            else {
                ll_pasangan.setVisibility(View.GONE);
                et_nikpasangan.getText().clear();
                et_namapasangan.getText().clear();
                et_tanggallahirpasangan.getText().clear();
            }
        }
        else if (title.equalsIgnoreCase("tipe pendapatan")){
            et_tipependapatan.setText(data.getName());
            val_TipePendapatan=KeyValue.getKeyTipePendapatan(et_tipependapatan.getText().toString());
        }
        else if (title.equalsIgnoreCase("pendidikan terakhir")){
            et_pendidikanterakhir.setText(data.getName());
            val_PendTerakhir=KeyValue.getKeyPendidikanTerakhir(et_pendidikanterakhir.getText().toString());
        }
        else if (title.equalsIgnoreCase("referensi")){
            et_referensi.setText(data.getName());
            val_Referensi=KeyValue.getKeyReferensi(et_referensi.getText().toString());

            //something wrong with this specific field, harus dipantek
            if(et_referensi.getText().toString().equalsIgnoreCase("lain-lain")){
                val_Referensi= Integer.toString(1);
            }
            else if(et_referensi.getText().toString().equalsIgnoreCase("nasabah")){
                val_Referensi= Integer.toString(2);
            }
            else if(et_referensi.getText().toString().equalsIgnoreCase("pihak terkait bank")){
                val_Referensi= Integer.toString(3);
            }
            else if(et_referensi.getText().toString().equalsIgnoreCase("prime customer bank")){
                val_Referensi= Integer.toString(4);
            }
//            Toast.makeText(getContext(), KeyValue.getKeyReferensi(et_referensi.getText().toString().trim()), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //EXPIRED NIK
            case R.id.et_expirednik:
            case R.id.tf_expirednik:
                dpExpiredNik();
                break;

            //AGAMA
            case R.id.et_agama:
            case R.id.tf_agama:
                openKeyValueDialog(tf_agama.getLabelText().toString().trim());
                break;


            //REFERENSI
            case R.id.et_referensi:
            case R.id.tf_referensi:
                openKeyValueDialog(tf_referensi.getLabelText().toString().trim());
                break;


            //TIPE PENDAPATAN
            case R.id.et_tipependapatan:
            case R.id.tf_tipependapatan:
                openKeyValueDialog(tf_tipependapatan.getLabelText().toString().trim());
                break;

            //PENDIDIKAN TERAKHIR
            case R.id.et_pendidikanterakhir:
            case R.id.tf_pendidikanterakhir:
                openKeyValueDialog(tf_pendidikanterakhir.getLabelText().toString().trim());
                break;

            //TANGGAL LAHIR PASANGAN
            case R.id.et_tanggallahirpasangan:
            case R.id.tf_tanggallahirpasangan:
                dpCalLahirPasangan();
                break;

            //TANGGAL LAHIR
            case R.id.et_tanggallahir:
            case R.id.tf_tanggallahir:
                dpCalLahir();
                break;

            //STATUS NIKAH
            case R.id.et_statusnikah:
            case R.id.tf_statusnikah:
                openKeyValueDialog(tf_statusnikah.getLabelText().toString().trim());
                break;

            //JENIS KELAMIN
            case R.id.et_jeniskelamin:
            case R.id.tf_jeniskelamin:
                openKeyValueDialog(tf_jeniskelamin.getLabelText().toString().trim());
                break;


        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            switch (v.getId()){
                //EXPIRED NIK
                case R.id.et_expirednik:
                    dpExpiredNik();
                    break;

                //AGAMA
//                case R.id.et_agama:
//                    openKeyValueDialog(tf_agama.getLabelText().toString().trim());
//                    break;

                //TANGGAL LAHIR PASANGAN
                case R.id.et_tanggallahirpasangan:
                    dpCalLahirPasangan();
                    break;

                //TANGGAL LAHIR
//                case R.id.et_tanggal_lahir:
//                    dpCalLahir();
//                    break;

                //TIPE PENDAPATAN
                case R.id.et_tipependapatan:
                    openKeyValueDialog(tf_tipependapatan.getLabelText().toString().trim());
                    break;

                //PENDIDIKAN TERAKHIR
//                case R.id.et_pendidikanterakhir:
//                    openKeyValueDialog(tf_pendidikanterakhir.getLabelText().toString().trim());
//                    break;
            }
        }
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

    private void hitungUmur(){
        Calendar now = Calendar.getInstance();
        Calendar dob = Calendar.getInstance();
        dob.set(Calendar.YEAR, Integer.parseInt(dataLengkap.getTglLahir().substring(4)));
        dob.set(Calendar.MONTH, Integer.parseInt(dataLengkap.getTglLahir().substring(2,4)));
        dob.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dataLengkap.getTglLahir().substring(0,2)));
        if (dob.after(now)) {
            throw new IllegalArgumentException("Can't be born in the future");
        }
        int year1 = now.get(Calendar.YEAR);
        int year2 = dob.get(Calendar.YEAR);
        umur = year1 - year2;
        int month1 = now.get(Calendar.MONTH);
        int month2 = dob.get(Calendar.MONTH);
        if (month2 > month1) {
            umur--;
        } else if (month1 == month2) {
            int day1 = now.get(Calendar.DAY_OF_MONTH);
            int day2 = dob.get(Calendar.DAY_OF_MONTH);
            if (day2 > day1) {
                umur--;
            }
        }
    }

    //ini method untuk long click listeners, biar mbak winda bisa ubah status nikah dan tanggal lahir jadi dibawah 21 tahun
    private void testingSpecialListeners(){
        tf_tanggallahir.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(dataLengkap.getTglLahir().equalsIgnoreCase("22012002")){
                    dataLengkap.setTglLahir(tglLahirOri);
                }
                else{
                    dataLengkap.setTglLahir("22012002");
                }
                et_tanggallahir.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglLahir(), "ddMMyyyy", "dd-MM-yyyy"));
                return false;
            }
        });

        tf_statusnikah.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(dataLengkap.getStatusNikah().equalsIgnoreCase("2")){
                    dataLengkap.setStatusNikah("1");
                }
                else{
                    dataLengkap.setStatusNikah("2");
                }
                et_statusnikah.setText(KeyValue.getKeyStatusNikah(dataLengkap.getStatusNikah()));


                return false;
            }
        });

        tf_tanggallahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dataLengkap.getTglLahir().equalsIgnoreCase("22032001")){
                    dataLengkap.setTglLahir(tglLahirOri);
                }
                else{
                    dataLengkap.setTglLahir("22032001");
                }
                hitungUmur();
                Toast.makeText(getContext(), "Umur Nasabah : "+ Integer.toString(umur), Toast.LENGTH_SHORT).show();
                et_tanggallahir.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglLahir(), "ddMMyyyy", "dd-MM-yyyy"));

            }
        });

        tf_statusnikah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dataLengkap.getStatusNikah().equalsIgnoreCase("2")){
                    dataLengkap.setStatusNikah("1");
                }
                else{
                    dataLengkap.setStatusNikah("2");
                }
                et_statusnikah.setText(KeyValue.getKeyStatusNikah(dataLengkap.getStatusNikah()));


            }
        });
    }
}
