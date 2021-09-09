package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.InputType;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;


import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.database.pojo.AgunanTanahBangunanPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanTanahBangunan;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherForThousand;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunan6Hasil extends Fragment implements Step, View.OnClickListener, View.OnFocusChangeListener, TextWatcher {

    @BindView(R.id.tf_tanggal_pemeriksaan)
    TextFieldBoxes tf_tanggal_pemeriksaan;
    @BindView(R.id.et_tanggal_pemeriksaan)
    EditText et_tanggal_pemeriksaan;

    @BindView(R.id.tf_data_pembanding_1)
    TextFieldBoxes tf_data_pembanding_1;
    @BindView(R.id.et_data_pembanding_1)
    EditText et_data_pembanding_1;

    @BindView(R.id.tf_data_pembanding_2)
    TextFieldBoxes tf_data_pembanding_2;
    @BindView(R.id.et_data_pembanding_2)
    EditText et_data_pembanding_2;

    @BindView(R.id.tf_luas_tanah_hasil)
    TextFieldBoxes tf_luas_tanah_hasil;
    @BindView(R.id.et_luas_tanah_hasil)
    EditText et_luas_tanah_hasil;

    @BindView(R.id.tf_harga_tanah)
    TextFieldBoxes tf_harga_tanah;
    @BindView(R.id.et_harga_tanah)
    EditText et_harga_tanah;

    @BindView(R.id.tf_luas_bangunan_imb)
    TextFieldBoxes tf_luas_bangunan_imb;
    @BindView(R.id.et_luas_bangunan_imb)
    EditText et_luas_bangunan_imb;

    @BindView(R.id.tf_luas_bangunan_tidak_imb)
    TextFieldBoxes tf_luas_bangunan_tidak_imb;
    @BindView(R.id.et_luas_bangunan_tidak_imb)
    EditText et_luas_bangunan_tidak_imb;

    @BindView(R.id.tf_harga_bangunan_imb)
    TextFieldBoxes tf_harga_bangunan_imb;
    @BindView(R.id.et_harga_bangunan)
    EditText et_harga_bangunan;

    @BindView(R.id.tf_nilai_likuidasi)
    TextFieldBoxes tf_nilai_likuidasi;
    @BindView(R.id.et_nilai_likudasi)
    EditText et_nilai_likudasi;

    @BindView(R.id.tf_luas_total)
    TextFieldBoxes tf_luas_total;
    @BindView(R.id.et_luas_total)
    EditText et_luas_total;

    @BindView(R.id.tf_npw_tanah)
    TextFieldBoxes tf_npw_tanah;
    @BindView(R.id.et_npw_tanah)
    EditText et_npw_tanah;

    @BindView(R.id.tf_npw_bangunan)
    TextFieldBoxes tf_npw_bangunan;
    @BindView(R.id.et_npw_bangunan)
    EditText et_npw_bangunan;

    @BindView(R.id.tf_npw_tanah_dan_bangunan)
    TextFieldBoxes tf_npw_tanah_dan_bangunan;
    @BindView(R.id.et_npw_tanah_dan_bangunan)
    EditText et_npw_tanah_dan_bangunan;

    @BindView(R.id.tf_npw_bangunan_diasuransikan)
    TextFieldBoxes tf_npw_bangunan_diasuransikan;
    @BindView(R.id.et_npw_bangunan_diasuransikan)
    EditText et_npw_bangunan_diasuransikan;

    @BindView(R.id.cl_content)
    ScrollView cl_content;

    private Calendar calPemeriksaan;
    private DatePickerDialog dpTglPemeriksaan;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    private AgunanTanahBangunan dataAgunan;
    private String idAgunan="";
    private String loan_type, tp_produk;

    public static String val_TanggalPemeriksaan ="";
    public static String val_DataPembanding1 ="";
    public static String val_DataPembanding2 ="";
    public static String val_LuasTanahHasil;
    public static String val_HargaTanah ="";
    public static String val_LuasBangunanImb ="";
    public static String val_LuasBangunanTidakImb ="";
    public static String val_HargaBangunan ="";
    public static String val_NilaiLikuidasi ="";
    public static String val_LuasTotal ="";
    public static String val_NpwTanah ="";
    public static String val_NpwBangunan ="";
    public static String val_NpwTanahDanBangunan ="";
    public static String val_NpwBangunanDiasuransikan ="";

    //BEGIN EMPTY STRING
    public static String val_DataPembanding3 ="";
    //END EMPTY STRING

    private BigDecimal luas_tanah_big = new BigDecimal(0);
    private BigDecimal harga_tanah = new BigDecimal(0);
    private BigDecimal npw_tanah = new BigDecimal(0);
    private BigDecimal valueIMBByTPProduct = new BigDecimal("1");
    private BigDecimal valueTidakIMBByTPProduct = new BigDecimal(0);

    private Realm realm;
    private boolean enableEditText;

    public FragmentAgunan6Hasil() {
    }

    public FragmentAgunan6Hasil(String midAgunan, AgunanTanahBangunan magunanTanahBangunan, String mloan_type, String mtp_produk) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
        loan_type = mloan_type;
        tp_produk = mtp_produk;
    }

    public FragmentAgunan6Hasil(String midAgunan, AgunanTanahBangunan magunanTanahBangunan, String mloan_type, String mtp_produk,boolean enableEditText) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
        loan_type = mloan_type;
        tp_produk = mtp_produk;
        this.enableEditText=enableEditText;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_6_hasil_penilaian, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        disableTextfield();
        onSelectDialog();
//        if(!idAgunan.equalsIgnoreCase("0")) {
//            setData();
//        }

//        onChangeText(); //emang 2 kali dipanggil

        setData();
        onChangeText();
        hitungNilai();

        tf_tanggal_pemeriksaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTglPemeriksaan();
            }
        });

        return view;
    }

    private void onChangeText(){
        et_harga_tanah.addTextChangedListener(this);
        et_harga_tanah.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_harga_tanah));
        et_luas_bangunan_imb.addTextChangedListener(this);
        et_luas_bangunan_tidak_imb.addTextChangedListener(this);
        et_harga_bangunan.addTextChangedListener(this);
        et_harga_bangunan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_harga_bangunan));
        et_nilai_likudasi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilai_likudasi));
        et_npw_tanah.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_npw_tanah));
        et_npw_bangunan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_npw_bangunan));
        et_npw_tanah_dan_bangunan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_npw_tanah_dan_bangunan));
        et_npw_bangunan_diasuransikan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_npw_bangunan_diasuransikan));
    }

    private void disableTextfield(){
        et_luas_total.setInputType(InputType.TYPE_NULL);
        et_luas_total.setFocusable(false);
        et_luas_tanah_hasil.setInputType(InputType.TYPE_NULL);
        et_luas_tanah_hasil.setFocusable(false);
        et_npw_tanah.setInputType(InputType.TYPE_NULL);
        et_npw_tanah.setFocusable(false);
        et_npw_bangunan.setInputType(InputType.TYPE_NULL);
        et_npw_bangunan.setFocusable(false);
        et_npw_tanah_dan_bangunan.setInputType(InputType.TYPE_NULL);
        et_npw_tanah_dan_bangunan.setFocusable(false);
        et_npw_bangunan_diasuransikan.setInputType(InputType.TYPE_NULL);
        et_npw_bangunan_diasuransikan.setFocusable(false);
    }

    private void onSelectDialog(){
        et_luas_bangunan_tidak_imb.setText("0");

        et_data_pembanding_1.setText("Alamat" + "\t\t" + ": " + "\n\n" +
                                        "Contact Person dan Telp." + "\t\t" + ": " + "\n\n" +
                                        "Luas Tanah Pembanding" + "\t\t" + ": " + "\n\n" +
                                        "Luas Bangunan Pembanding" + "\t\t" + ": " + "\n\n" +
                                        "Harga Penawaran" + "\t\t" + ": " + "\n\n" +
                                        "Estimasi Harga Tanah" + "\t\t" + ": " + "\n");

        et_data_pembanding_2.setText("Alamat" + "\t\t" + ": " + "\n\n" +
                                        "Contact Person dan Telp." + "\t\t" + ": " + "\n\n" +
                                        "Luas Tanah Pembanding" + "\t\t" + ": " + "\n\n" +
                                        "Luas Bangunan Pembanding" + "\t\t" + ": " + "\n\n" +
                                        "Harga Penawaran" + "\t\t" + ": " + "\n\n" +
                                        "Estimasi Harga Tanah" + "\t\t" + ": " + "\n");

        et_tanggal_pemeriksaan.setFocusable(false);
        et_tanggal_pemeriksaan.setInputType(InputType.TYPE_NULL);
        et_tanggal_pemeriksaan.setOnFocusChangeListener(this);
        tf_tanggal_pemeriksaan.setOnClickListener(this);
        tf_tanggal_pemeriksaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTglPemeriksaan();
            }
        });

        int tp = Integer.valueOf(tp_produk);
        if (tp == 20) {
            if (loan_type.equalsIgnoreCase("136")) {
                valueTidakIMBByTPProduct = new BigDecimal("1");
            } else {
                valueTidakIMBByTPProduct = new BigDecimal("0.5");
            }
        } else {
            valueTidakIMBByTPProduct = new BigDecimal("0");
        }
    }

    private void setData(){
        try {
            et_tanggal_pemeriksaan.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTanggalPemeriksaan(), "ddMMyyyy", "dd-MM-yyyy"));

            if(dataAgunan.getDataPembanding1()!=null&&!dataAgunan.getDataPembanding1().isEmpty()){
                et_data_pembanding_1.setText(dataAgunan.getDataPembanding1());
            }
            if(dataAgunan.getDataPembanding2()!=null&&!dataAgunan.getDataPembanding2().isEmpty()){
                et_data_pembanding_2.setText(dataAgunan.getDataPembanding2());
            }
//            et_data_pembanding_1.setText(dataAgunan.getDataPembanding1());
//            et_data_pembanding_2.setText(dataAgunan.getDataPembanding2());

            et_harga_tanah.setText(Long.toString(dataAgunan.getHargaMeterTanah()));
            et_luas_bangunan_imb.setText(dataAgunan.getLuasBangunan1());
            et_harga_bangunan.setText(dataAgunan.getHargaBangunan1());
            et_luas_bangunan_tidak_imb.setText(dataAgunan.getLuasBangunan2());
            et_nilai_likudasi.setText(dataAgunan.getnLTanahBangunan());

            if(enableEditText==false){
                AppUtil.disableEditTexts(cl_content);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_tanggal_pemeriksaan:
            case R.id.tf_tanggal_pemeriksaan:
                dpTglPemeriksaan();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            switch (v.getId()){
                case R.id.et_tanggal_pemeriksaan:
                case R.id.tf_tanggal_pemeriksaan:
                    dpTglPemeriksaan();
                    break;
            }
        }
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return  validateForm();
    }

    @Override
    public void onSelected() {
        AgunanTanahBangunanPojo dataRealm = realm.where(AgunanTanahBangunanPojo.class).equalTo("uuid", AgunanTanahBangunanInputActivity.uuid).findFirst();
        et_luas_tanah_hasil.setText(dataRealm.getLuasTanahSertifikat());
        if(!idAgunan.equalsIgnoreCase("0")) {
            setData();
        }
    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }

    private void dpTglPemeriksaan(){
        calPemeriksaan = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ls_expiredDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calPemeriksaan.set(Calendar.YEAR, year);
                calPemeriksaan.set(Calendar.MONTH, month);
                calPemeriksaan.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calExpiredString = dateClient.format(calPemeriksaan.getTime());
                et_tanggal_pemeriksaan.setText(calExpiredString);
            }
        };

        dpTglPemeriksaan = new DatePickerDialog(getContext(), R.style.AppTheme_TimePickerTheme, ls_expiredDate, calPemeriksaan.get(Calendar.YEAR),
                calPemeriksaan.get(Calendar.MONTH), calPemeriksaan.get(Calendar.DAY_OF_MONTH));
        dpTglPemeriksaan.getDatePicker().setMaxDate(calPemeriksaan.getTimeInMillis());
        dpTglPemeriksaan.show();
    }

    private VerificationError validateForm(){
        if (et_tanggal_pemeriksaan.getText().toString().isEmpty() || et_tanggal_pemeriksaan.getText().toString().equalsIgnoreCase("")){
            tf_tanggal_pemeriksaan.setError("Format "+ tf_tanggal_pemeriksaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_tanggal_pemeriksaan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_data_pembanding_1.getText().toString().isEmpty() || et_data_pembanding_1.getText().toString().equalsIgnoreCase("")){
            tf_data_pembanding_1.setError("Format "+ tf_data_pembanding_1.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_data_pembanding_1.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_data_pembanding_2.getText().toString().isEmpty() || et_data_pembanding_2.getText().toString().equalsIgnoreCase("")){
            tf_data_pembanding_2.setError("Format "+ tf_data_pembanding_2.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_data_pembanding_2.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_harga_tanah.getText().toString().isEmpty() || et_harga_tanah.getText().toString().equalsIgnoreCase("")){
            tf_harga_tanah.setError("Format "+ tf_harga_tanah.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_harga_tanah.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_luas_bangunan_imb.getText().toString().isEmpty() || et_luas_bangunan_imb.getText().toString().equalsIgnoreCase("")){
            tf_luas_bangunan_imb.setError("Format "+ tf_luas_bangunan_imb.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_luas_bangunan_imb.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_luas_bangunan_tidak_imb.getText().toString().isEmpty() || et_luas_bangunan_tidak_imb.getText().toString().equalsIgnoreCase("")){
            tf_luas_bangunan_tidak_imb.setError("Format "+ tf_luas_bangunan_tidak_imb.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_luas_bangunan_tidak_imb.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_harga_bangunan.getText().toString().isEmpty() || et_harga_bangunan.getText().toString().equalsIgnoreCase("")){
            tf_harga_bangunan_imb.setError("Format "+ tf_harga_bangunan_imb.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_harga_bangunan_imb.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_nilai_likudasi.getText().toString().isEmpty() || et_nilai_likudasi.getText().toString().equalsIgnoreCase("")){
            tf_nilai_likuidasi.setError("Format "+ tf_nilai_likuidasi.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nilai_likuidasi.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else {
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner(){
        try {
            val_TanggalPemeriksaan = (AppUtil.parseTanggalGeneral(et_tanggal_pemeriksaan.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
            val_DataPembanding1 = (et_data_pembanding_1.getText().toString().trim());
            val_DataPembanding2 = (et_data_pembanding_2.getText().toString().trim());
            val_LuasTanahHasil = (et_luas_tanah_hasil.getText().toString().trim());
            val_HargaTanah = NumberTextWatcherForThousand.trimCommaOfString(et_harga_tanah.getText().toString().trim());
            val_LuasBangunanImb = (et_luas_bangunan_imb.getText().toString().trim());
            val_HargaBangunan = NumberTextWatcherForThousand.trimCommaOfString(et_harga_bangunan.getText().toString().trim());
            val_LuasBangunanTidakImb = (et_luas_bangunan_tidak_imb.getText().toString().trim());
            val_NilaiLikuidasi = NumberTextWatcherForThousand.trimCommaOfString(et_nilai_likudasi.getText().toString().trim());
            val_LuasTotal = (et_luas_total.getText().toString().trim());
            val_NpwTanah = NumberTextWatcherForThousand.trimCommaOfString(et_npw_tanah.getText().toString().trim());
            val_NpwBangunan = NumberTextWatcherForThousand.trimCommaOfString(et_npw_bangunan.getText().toString().trim());
            val_NpwTanahDanBangunan = NumberTextWatcherForThousand.trimCommaOfString(et_npw_tanah_dan_bangunan.getText().toString().trim());
            val_NpwBangunanDiasuransikan = NumberTextWatcherForThousand.trimCommaOfString(et_npw_bangunan_diasuransikan.getText().toString().trim());

            final AgunanTanahBangunanPojo d = new AgunanTanahBangunanPojo();
            d.setTanggalPemeriksaan(val_TanggalPemeriksaan);
            d.setDataPembanding1(val_DataPembanding1);
            d.setDataPembanding2(val_DataPembanding2);
            d.setLuasTanahFisik(val_LuasTanahHasil);
            d.setHargaMeterTanah(AppUtil.parseLongWithDefault(val_HargaTanah, 0));
            d.setLuasBangunan1(AppUtil.parseDoubleWithDefault(val_LuasBangunanImb, 0.0));
            d.setLuasBangunan2(AppUtil.parseDoubleWithDefault(val_LuasBangunanTidakImb, 0.0));
            d.setHargaBangunan1(AppUtil.parseLongWithDefault(val_HargaBangunan, 0));
            d.setHargaBangunan2(AppUtil.parseLongWithDefault(val_HargaBangunan, 0));
            d.setNLTanahBangunan(AppUtil.parseLongWithDefault(val_NilaiLikuidasi, 0));
            d.setNPWTanahBangunan(AppUtil.parseLongWithDefault(val_NpwTanahDanBangunan, 0));
            d.setNPWBangunan(AppUtil.parseLongWithDefault(val_NpwBangunan, 0));
            d.setNPWBangunanBrins(AppUtil.parseLongWithDefault(val_NpwBangunanDiasuransikan, 0));
            d.setDataPembanding3(val_DataPembanding3);

            AgunanTanahBangunanPojo dataR = realm.where(AgunanTanahBangunanPojo.class).equalTo("uuid", AgunanTanahBangunanInputActivity.uuid).findFirst();
            d.setPeruntukanLokasi(dataR.getPeruntukanLokasi());
            d.setAksesJalanObjek(dataR.getAksesJalanObjek());
            d.setFasilitasSosial(dataR.getFasilitasSosial());
            d.setLebarJalanDepan(dataR.getLebarJalanDepan());
            d.setPerkembanganLingkungan(dataR.getPerkembanganLingkungan());
            d.setKepadatan(dataR.getKepadatan());
            d.setAksesPencapaian(dataR.getAksesPencapaian());
            d.setPerawatan(dataR.getPerawatan());
            d.setJenisBangunanBRINS(dataR.getJenisBangunanBRINS());
            d.setPondasi(dataR.getPondasi());
            d.setDinding(dataR.getDinding());
            d.setAtap(dataR.getAtap());
            d.setFasilitasListrik(dataR.isFasilitasListrik());
            d.setFasilitasPAM(dataR.isFasilitasPAM());
            d.setTelepon(dataR.isTelepon());
            d.setTeganganListrik(dataR.getTeganganListrik());
            d.setJenisAir(dataR.getJenisAir());
            d.setNoTelepon(dataR.getNoTelepon());
            d.setPlafond(dataR.getPlafond());
            d.setLantai(dataR.getLantai());
            d.setTeras(dataR.getTeras());
            d.setPagar(dataR.getPagar());
            d.setGarasiCarport(dataR.getGarasiCarport());
            d.setJenisBangunan(dataR.getJenisBangunan());
            d.setLokasiBangunanBrins(dataR.getLokasiBangunanBrins());
            d.setNoIMB(dataR.getNoIMB());
            d.setJenisAgunanXBRL(dataR.getJenisAgunanXBRL());
            d.setHubPenghuniDgPemilik(dataR.getHubPenghuniDgPemilik());
            d.setTingkat(dataR.getTingkat());
            d.setAlamatIMB(dataR.getAlamatIMB());
            d.setNamaPenghuni(dataR.getNamaPenghuni());
            d.setStatusPenghuni(dataR.getStatusPenghuni());
            d.setAtasNamaIMB(dataR.getAtasNamaIMB());
            d.setTahunBangunan(dataR.getTahunBangunan());
            d.setJenisSuratTanah(dataR.getJenisSuratTanah());
            d.setNoSertifikat(dataR.getNoSertifikat());
            d.setAtasNamaSertifikat(dataR.getAtasNamaSertifikat());
            d.setTanggalSertifikat(dataR.getTanggalSertifikat());
            d.setLuasTanahSertifikat(dataR.getLuasTanahSertifikat());
            d.setIdDok_BPN(dataR.getIdDok_BPN());
            d.setNoGS(dataR.getNoGS());
            d.setHubNasabahDgnPemegangHak(dataR.getHubNasabahDgnPemegangHak());
            d.setMasaHakAtasTanah(dataR.getMasaHakAtasTanah());
            d.setUuid(dataR.getUuid());
            d.setIdAgunan(dataR.getIdAgunan());
            d.setIdApl(dataR.getIdApl());
            d.setCif(dataR.getCif());
            d.setLokasiTanah(dataR.getLokasiTanah());
            d.setKodePos(dataR.getKodePos());
            d.setKelurahan(dataR.getKelurahan());
            d.setKecamatan(dataR.getKecamatan());
            d.setKota(dataR.getKota());
            d.setPropinsi(dataR.getPropinsi());
            d.setBatasUtaraTanah(dataR.getBatasUtaraTanah());
            d.setBatasSelatanTanah(dataR.getBatasSelatanTanah());
            d.setBatasBaratTanah(dataR.getBatasBaratTanah());
            d.setBatasTimurTanah(dataR.getBatasTimurTanah());
            d.setKavBlok(dataR.getKavBlok());
            d.setRT(dataR.getRT());
            d.setRW(dataR.getRW());
            d.setKoordinat(dataR.getKoordinat());
            d.setBentukTanah(dataR.getBentukTanah());
            d.setPermukaanTanah(dataR.getPermukaanTanah());
            d.setNamaPasar(dataR.getNamaPasar());
            d.setNkrBrins(dataR.getNkrBrins());
            d.setProvinsi(dataR.getProvinsi());
            d.setKota_kab(dataR.getKota_kab());
            d.setNama_pasar(dataR.getNama_pasar());
            d.setAlamat_pasar(dataR.getAlamat_pasar());

            d.setIdDokPBB(dataR.getIdDokIMB());
            d.setIdDokIMB(dataR.getIdDokPBB());

            //flpp
            d.setKodeWilayah(dataR.getKodeWilayah());
            d.setNoAlamat(dataR.getNoAlamat());
            d.setNoBast(dataR.getNoBast());
            d.setTglBast(dataR.getTglBast());
            d.setNoSlf(dataR.getNoSlf());
            d.setTglSlf(dataR.getTglSlf());


            Log.d("lokasipasar","namapasar :"+dataR.getNamaPasar());
            Log.d("lokasipasar","nkr pasar : "+dataR.getNkrBrins());

            realm.executeTransaction (new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(d);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        hitungNilai();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void hitungNilai(){

        try {
            BigDecimal luas_total = new BigDecimal(0);
            BigDecimal npw_imb = new BigDecimal(0);
            BigDecimal npw_tidak_imb = new BigDecimal(0);
            BigDecimal npw_bangunan = new BigDecimal(0);
            BigDecimal npw_tanah_bangunan = new BigDecimal(0);
            BigDecimal npw_imb_asuransi = new BigDecimal(0);
            BigDecimal npw_tidakimb_asuransi = new BigDecimal(0);
            BigDecimal npw_bangunan_asuransi = new BigDecimal(0);
            BigDecimal nilai_likuidasi = new BigDecimal(0);

            if (et_harga_tanah.getText().toString().trim().length() > 0 || !et_harga_tanah.getText().toString().isEmpty()){
                npw_tanah = new BigDecimal(et_luas_tanah_hasil.getText().toString().trim())
                        .multiply(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_harga_tanah.getText().toString().trim())))
                        .setScale(0, RoundingMode.HALF_UP);
            }
            et_npw_tanah.setText(String.valueOf(npw_tanah));



            if (et_luas_bangunan_imb.getText().toString().trim().length() > 0 || !et_luas_bangunan_imb.getText().toString().isEmpty()){
                luas_total = luas_total.add(new BigDecimal(et_luas_bangunan_imb.getText().toString()));
            }

            if (et_luas_bangunan_tidak_imb.getText().toString().trim().length() > 0 || !et_luas_bangunan_tidak_imb.getText().toString().isEmpty()){
                luas_total = luas_total.add(new BigDecimal(et_luas_bangunan_tidak_imb.getText().toString()));
            }

            et_luas_total.setText(String.valueOf(luas_total));

            if (et_harga_bangunan.getText().toString().trim().length() > 0 || !et_harga_bangunan.getText().toString().isEmpty()){
                npw_imb = new BigDecimal(et_luas_bangunan_imb.getText().toString())
                        .multiply(valueIMBByTPProduct)
                        .multiply(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_harga_bangunan.getText().toString())))
                        .setScale(0, RoundingMode.HALF_UP);
                npw_imb_asuransi = new BigDecimal(et_luas_bangunan_imb.getText().toString())
                        .multiply(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_harga_bangunan.getText().toString())))
                        .setScale(0, RoundingMode.HALF_UP);
            }

            if (et_harga_bangunan.getText().toString().trim().length() > 0 || !et_harga_bangunan.getText().toString().isEmpty()){
                npw_tidak_imb = new BigDecimal(et_luas_bangunan_tidak_imb.getText().toString())
                        .multiply(valueTidakIMBByTPProduct)
                        .multiply(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_harga_bangunan.getText().toString())))
                        .setScale(0, RoundingMode.HALF_UP);
                npw_tidakimb_asuransi = new BigDecimal(et_luas_bangunan_tidak_imb.getText().toString())
                        .multiply(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_harga_bangunan.getText().toString())))
                        .setScale(0, RoundingMode.HALF_UP);
            }

            npw_bangunan = npw_imb.add(npw_tidak_imb);
            et_npw_bangunan.setText(String.valueOf(npw_bangunan));

            npw_tanah_bangunan = npw_tanah.add(npw_bangunan);
            et_npw_tanah_dan_bangunan.setText(String.valueOf(npw_tanah_bangunan));

            npw_bangunan_asuransi = npw_imb_asuransi.add(npw_tidakimb_asuransi);
            et_npw_bangunan_diasuransikan.setText(String.valueOf(npw_bangunan_asuransi));

            nilai_likuidasi = npw_tanah_bangunan
                    .multiply(new BigDecimal(70)
                            .divide(new BigDecimal(100),2, BigDecimal.ROUND_HALF_UP))
                    .setScale(0, RoundingMode.HALF_UP);
            et_nilai_likudasi.setText(String.valueOf(nilai_likuidasi));
        }
        catch (Exception e){
            e.printStackTrace();
//            AppUtil.showToastShort(getContext(), e.getMessage());
        }

    }
}



