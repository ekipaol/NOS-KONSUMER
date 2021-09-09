package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.database.pojo.AgunanKendaraanPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanKendaraan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.KeyValue;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * A simple {@link Fragment} subclass.
 */

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunanKendaraan1 extends Fragment implements Step, KeyValueListener, View.OnClickListener, View.OnFocusChangeListener {

//VIEW BINDING
    @BindView(R.id.tf_tanggal_pemeriksaan)
    TextFieldBoxes tf_tanggal_pemeriksaan;
    @BindView(R.id.et_tanggal_pemeriksaan)
    ExtendedEditText et_tanggal_pemeriksaan;

    @BindView(R.id.tf_jenis_kendaraan)
    TextFieldBoxes tf_jenis_kendaraan;
    @BindView(R.id.et_jenis_kendaraan)
    ExtendedEditText et_jenis_kendaraan;

    @BindView(R.id.tf_kategori_kendaraan)
    TextFieldBoxes tf_kategori_kendaraan;
    @BindView(R.id.et_kategori_kendaraan)
    ExtendedEditText et_kategori_kendaraan;

    @BindView(R.id.tf_penggunaan_jaminan)
    TextFieldBoxes tf_penggunaan_jaminan;
    @BindView(R.id.et_penggunaan_jaminan)
    ExtendedEditText et_penggunaan_jaminan;

    @BindView(R.id.tf_daerah_operasional)
    TextFieldBoxes tf_daerah_operasional;
    @BindView(R.id.et_daerah_operasional)
    ExtendedEditText et_daerah_operasional;

    @BindView(R.id.tf_kondisi_jaminan)
    TextFieldBoxes tf_kondisi_jaminan;
    @BindView(R.id.et_kondisi_jaminan)
    ExtendedEditText et_kondisi_jaminan;

    @BindView(R.id.tf_nama_pemilik)
    TextFieldBoxes tf_nama_pemilik;
    @BindView(R.id.et_nama_pemilik)
    ExtendedEditText et_nama_pemilik;

    @BindView(R.id.tf_nama_pemilik_saat_ini)
    TextFieldBoxes tf_nama_pemilik_saat_ini;
    @BindView(R.id.et_nama_pemilik_saat_ini)
    ExtendedEditText et_nama_pemilik_saat_ini;

    @BindView(R.id.tf_alamat)
    TextFieldBoxes tf_alamat;
    @BindView(R.id.et_alamat)
    ExtendedEditText et_alamat;

    //END OF VIEW BINDING
    private String idAgunan;
    private AgunanKendaraan dataAgunan;
    private Calendar calPeriksa;
    private DatePickerDialog dpTanggalPeriksa;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    public static String val_TanggalPemeriksaan ="";
    public static String val_JenisKendaraan ="";
    public static String val_KategoriKendaraan ="";
    public static String val_PenggunaanJaminan ="";
    public static String val_DaerahOperasional ="";
    public static String val_KondisiJaminan ="";
    public static String val_NamaPemilik ="";
    public static String val_NamaPemilikSaatIni ="";
    public static String val_Alamat ="";

    private Realm realm;

    public FragmentAgunanKendaraan1() {
    }

    public FragmentAgunanKendaraan1(String midAgunan, AgunanKendaraan magunanData) {
        dataAgunan = magunanData;
        idAgunan = midAgunan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_kendaraan_1, container, false);
        ButterKnife.bind(this,view);
        realm = Realm.getDefaultInstance();
        onSelectDialog();
        if(!String.valueOf(idAgunan).equalsIgnoreCase("0")) {
            setData();
        }

        return view;
    }

    private void onSelectDialog(){

        et_tanggal_pemeriksaan.setFocusable(false);
        et_tanggal_pemeriksaan.setInputType(InputType.TYPE_NULL);
        et_tanggal_pemeriksaan.setOnClickListener(this);
        et_tanggal_pemeriksaan.setOnFocusChangeListener(this);
        tf_tanggal_pemeriksaan.setOnClickListener(this);
        tf_tanggal_pemeriksaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTglPeriksa();
            }
        });

        et_jenis_kendaraan.setFocusable(false);
        et_jenis_kendaraan.setInputType(InputType.TYPE_NULL);
        et_jenis_kendaraan.setOnClickListener(this);
        et_jenis_kendaraan.setOnFocusChangeListener(this);
        tf_jenis_kendaraan.setOnClickListener(this);
        tf_jenis_kendaraan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_jenis_kendaraan.getLabelText().toString().trim());
            }
        });

        et_kategori_kendaraan.setFocusable(false);
        et_kategori_kendaraan.setInputType(InputType.TYPE_NULL);
        et_kategori_kendaraan.setOnClickListener(this);
        et_kategori_kendaraan.setOnFocusChangeListener(this);
        tf_kategori_kendaraan.setOnClickListener(this);
        tf_kategori_kendaraan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_kategori_kendaraan.getLabelText().toString().trim());
            }
        });

        et_penggunaan_jaminan.setFocusable(false);
        et_penggunaan_jaminan.setInputType(InputType.TYPE_NULL);
        et_penggunaan_jaminan.setOnClickListener(this);
        et_penggunaan_jaminan.setOnFocusChangeListener(this);
        tf_penggunaan_jaminan.setOnClickListener(this);
        tf_penggunaan_jaminan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_penggunaan_jaminan.getLabelText().toString().trim());
            }
        });

        et_daerah_operasional.setFocusable(false);
        et_daerah_operasional.setInputType(InputType.TYPE_NULL);
        et_daerah_operasional.setOnClickListener(this);
        et_daerah_operasional.setOnFocusChangeListener(this);
        tf_daerah_operasional.setOnClickListener(this);
        tf_daerah_operasional.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_daerah_operasional.getLabelText().toString().trim());
            }
        });

        et_kondisi_jaminan.setFocusable(false);
        et_kondisi_jaminan.setInputType(InputType.TYPE_NULL);
        et_kondisi_jaminan.setOnClickListener(this);
        et_kondisi_jaminan.setOnFocusChangeListener(this);
        tf_kondisi_jaminan.setOnClickListener(this);
        tf_kondisi_jaminan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_kondisi_jaminan.getLabelText().toString().trim());
            }
        });
    }

    private void openKeyValueDialog(String title){
        DialogKeyValue.display(getFragmentManager(), title, this);
    }

    private void setData(){
        try {
            et_tanggal_pemeriksaan.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTglPemeriksaan(), "ddMMyyyy", "dd-MM-yyyy"));
            et_jenis_kendaraan.setText(dataAgunan.getJenisKendaraan());
            et_kategori_kendaraan.setText(dataAgunan.getKategKendaraan());
            et_penggunaan_jaminan.setText(dataAgunan.getPenggunaanJaminan());
            et_daerah_operasional.setText(KeyValue.getKeyOperasional(dataAgunan.getDaerahOperasional()));
            et_kondisi_jaminan.setText(dataAgunan.getKondisi());
            et_nama_pemilik.setText(dataAgunan.getNamaPemilikBPKB());
            et_nama_pemilik_saat_ini.setText(dataAgunan.getNamaPemilikSaatIni());
            et_alamat.setText(dataAgunan.getAlamatPemilik());
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
                dpTglPeriksa();
                break;
            case R.id.et_jenis_kendaraan:
            case R.id.tf_jenis_kendaraan:
                openKeyValueDialog(tf_jenis_kendaraan.getLabelText().toString().trim());
                break;
            case R.id.et_kategori_kendaraan:
            case R.id.tf_kategori_kendaraan:
                openKeyValueDialog(tf_kategori_kendaraan.getLabelText().toString().trim());
                break;
            case R.id.et_penggunaan_jaminan:
            case R.id.tf_penggunaan_jaminan:
                openKeyValueDialog(tf_penggunaan_jaminan.getLabelText().toString().trim());
                break;
            case R.id.et_daerah_operasional:
            case R.id.tf_daerah_operasional:
                openKeyValueDialog(tf_daerah_operasional.getLabelText().toString().trim());
                break;
            case R.id.et_kondisi_jaminan:
            case R.id.tf_kondisi_jaminan:
                openKeyValueDialog(tf_kondisi_jaminan.getLabelText().toString().trim());
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            switch (v.getId()){
                case R.id.et_tanggal_pemeriksaan:
                case R.id.tf_tanggal_pemeriksaan:
                    dpTglPeriksa();
                    break;
                case R.id.et_jenis_kendaraan:
                case R.id.tf_jenis_kendaraan:
                    openKeyValueDialog(tf_jenis_kendaraan.getLabelText().toString().trim());
                    break;
                case R.id.et_kategori_kendaraan:
                case R.id.tf_kategori_kendaraan:
                    openKeyValueDialog(tf_kategori_kendaraan.getLabelText().toString().trim());
                    break;
                case R.id.et_penggunaan_jaminan:
                case R.id.tf_penggunaan_jaminan:
                    openKeyValueDialog(tf_penggunaan_jaminan.getLabelText().toString().trim());
                    break;
                case R.id.et_daerah_operasional:
                case R.id.tf_daerah_operasional:
                    openKeyValueDialog(tf_daerah_operasional.getLabelText().toString().trim());
                    break;
                case R.id.et_kondisi_jaminan:
                case R.id.tf_kondisi_jaminan:
                    openKeyValueDialog(tf_kondisi_jaminan.getLabelText().toString().trim());
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

    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase("Jenis Kendaraan")){
            et_jenis_kendaraan.setText(data.getName());
        }
        else if (title.equalsIgnoreCase("Kategori Kendaraan")){
            et_kategori_kendaraan.setText(data.getName());
        }
        else if (title.equalsIgnoreCase("Penggunaan Jaminan")){
            et_penggunaan_jaminan.setText(data.getName());
        }
        else if (title.equalsIgnoreCase("Daerah Operasional Jaminan")){
            et_daerah_operasional.setText(data.getName());
        }
        else if (title.equalsIgnoreCase("Kondisi Jaminan")){
            et_kondisi_jaminan.setText(data.getName());
        }
    }

    private void dpTglPeriksa(){
        calPeriksa = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ls_expiredDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calPeriksa.set(Calendar.YEAR, year);
                calPeriksa.set(Calendar.MONTH, month);
                calPeriksa.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calExpiredString = dateClient.format(calPeriksa.getTime());
                et_tanggal_pemeriksaan.setText(calExpiredString);
            }
        };

        dpTanggalPeriksa = new DatePickerDialog(getContext(), R.style.AppTheme_TimePickerTheme, ls_expiredDate, calPeriksa.get(Calendar.YEAR),
                calPeriksa.get(Calendar.MONTH), calPeriksa.get(Calendar.DAY_OF_MONTH));
        dpTanggalPeriksa.getDatePicker().setMaxDate(calPeriksa.getTimeInMillis());
        dpTanggalPeriksa.show();
    }

    private VerificationError validateForm(){
        if (et_tanggal_pemeriksaan.getText().toString().isEmpty() || et_tanggal_pemeriksaan.getText().toString().equalsIgnoreCase("")){
            tf_tanggal_pemeriksaan.setError("Format "+ tf_tanggal_pemeriksaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_tanggal_pemeriksaan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_jenis_kendaraan.getText().toString().isEmpty() || et_jenis_kendaraan.getText().toString().equalsIgnoreCase("Pilih")){
            tf_jenis_kendaraan.setError("Format "+ tf_jenis_kendaraan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_jenis_kendaraan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kategori_kendaraan.getText().toString().isEmpty() || et_kategori_kendaraan.getText().toString().equalsIgnoreCase("Pilih")){
            tf_kategori_kendaraan.setError("Format "+ tf_kategori_kendaraan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kategori_kendaraan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_penggunaan_jaminan.getText().toString().isEmpty() || et_penggunaan_jaminan.getText().toString().equalsIgnoreCase("Pilih")){
            tf_penggunaan_jaminan.setError("Format "+ tf_penggunaan_jaminan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_penggunaan_jaminan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_daerah_operasional.getText().toString().isEmpty() || et_daerah_operasional.getText().toString().equalsIgnoreCase("Pilih")){
            tf_daerah_operasional.setError("Format "+ tf_daerah_operasional.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_daerah_operasional.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kondisi_jaminan.getText().toString().isEmpty() || et_kondisi_jaminan.getText().toString().equalsIgnoreCase("Pilih")){
            tf_kondisi_jaminan.setError("Format "+ tf_kondisi_jaminan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kondisi_jaminan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_nama_pemilik.getText().toString().isEmpty() || et_nama_pemilik.getText().toString().equalsIgnoreCase("")){
            tf_nama_pemilik.setError("Format "+ tf_nama_pemilik.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nama_pemilik.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_nama_pemilik_saat_ini.getText().toString().isEmpty() || et_nama_pemilik_saat_ini.getText().toString().equalsIgnoreCase("")){
            tf_nama_pemilik_saat_ini.setError("Format "+ tf_nama_pemilik_saat_ini.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_nama_pemilik_saat_ini.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_alamat.getText().toString().isEmpty() || et_alamat.getText().toString().equalsIgnoreCase("")){
            tf_alamat.setError("Format "+ tf_alamat.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_alamat.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else {
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner(){
        try {
            val_TanggalPemeriksaan = (AppUtil.parseTanggalGeneral(et_tanggal_pemeriksaan.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
            val_JenisKendaraan = (et_jenis_kendaraan.getText().toString().trim());
            val_KategoriKendaraan = (et_kategori_kendaraan.getText().toString().trim());
            val_PenggunaanJaminan = (et_penggunaan_jaminan.getText().toString().trim());
            val_DaerahOperasional = (KeyValue.getTypeOperasional(et_daerah_operasional.getText().toString().trim()));
            val_KondisiJaminan = (et_kondisi_jaminan.getText().toString().trim());
            val_NamaPemilik = (et_nama_pemilik.getText().toString().trim());
            val_NamaPemilikSaatIni = (et_nama_pemilik_saat_ini.getText().toString().trim());
            val_Alamat = (et_alamat.getText().toString().trim());

            final AgunanKendaraanPojo d = new AgunanKendaraanPojo();
            d.setUuid(AgunanKendaraanInputActivity.UUIDR);
            d.setIdApl(AppUtil.parseIntWithDefault(AgunanKendaraanInputActivity.idAplikasi, 0));
            d.setIdCif(AppUtil.parseIntWithDefault(AgunanKendaraanInputActivity.cif, 0));
            d.setIdAgunan(AppUtil.parseIntWithDefault(AgunanKendaraanInputActivity.idAgunan, 0));
            d.setTglPemeriksaan(val_TanggalPemeriksaan);
            d.setJenisKendaraan(val_JenisKendaraan);
            d.setKategKendaraan(val_KategoriKendaraan);
            d.setPenggunaanJaminan(val_PenggunaanJaminan);
            d.setDaerahOperasional(val_DaerahOperasional);
            d.setKondisi(val_KondisiJaminan);
            d.setNamaPemilikBPKB(val_NamaPemilik);
            d.setNamaPemilikSaatIni(val_NamaPemilikSaatIni);
            d.setAlamatPemilik(val_Alamat);

            realm.executeTransaction (new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(d);
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

