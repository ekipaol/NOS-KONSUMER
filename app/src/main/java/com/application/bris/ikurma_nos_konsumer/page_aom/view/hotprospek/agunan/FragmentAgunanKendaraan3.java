package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.database.pojo.AgunanKendaraanPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanKendaraan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherForThousand;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
public class FragmentAgunanKendaraan3 extends Fragment implements Step, KeyValueListener, View.OnClickListener, View.OnFocusChangeListener, TextWatcher {

    //VIEW BINDING
    @BindView(R.id.tf_tipe_kendaraan)
    TextFieldBoxes tf_tipe_kendaraan;
    @BindView(R.id.et_tipe_kendaraan)
    ExtendedEditText et_tipe_kendaraan;

    @BindView(R.id.tf_merk_kendaraan)
    TextFieldBoxes tf_merk_kendaraan;
    @BindView(R.id.et_merk_kendaraan)
    ExtendedEditText et_merk_kendaraan;

    @BindView(R.id.tf_kendaraan_jepang)
    TextFieldBoxes tf_kendaraan_jepang;
    @BindView(R.id.et_kendaraan_jepang)
    ExtendedEditText et_kendaraan_jepang;

    @BindView(R.id.tf_daya_angkut)
    TextFieldBoxes tf_daya_angkut;
    @BindView(R.id.et_daya_angkut)
    ExtendedEditText et_daya_angkut;

    @BindView(R.id.tf_kapasitas)
    TextFieldBoxes tf_kapasitas;
    @BindView(R.id.et_kapasitas)
    ExtendedEditText et_kapasitas;

    @BindView(R.id.tf_check_samsat)
    TextFieldBoxes tf_check_samsat;
    @BindView(R.id.et_check_samsat)
    ExtendedEditText et_check_samsat;

    @BindView(R.id.tf_dengan_siapa)
    TextFieldBoxes tf_dengan_siapa;
    @BindView(R.id.et_dengan_siapa)
    ExtendedEditText et_dengan_siapa;

    @BindView(R.id.tf_no_telp)
    TextFieldBoxes tf_no_telp;
    @BindView(R.id.et_no_telp)
    ExtendedEditText et_no_telp;

    @BindView(R.id.tf_hasil)
    TextFieldBoxes tf_hasil;
    @BindView(R.id.et_hasil)
    ExtendedEditText et_hasil;

    @BindView(R.id.tf_nilai_market)
    TextFieldBoxes tf_nilai_market;
    @BindView(R.id.et_nilai_market)
    ExtendedEditText et_nilai_market;

    @BindView(R.id.tf_nilai_likuidasi)
    TextFieldBoxes tf_nilai_likuidasi;
    @BindView(R.id.et_nilai_likuidasi)
    ExtendedEditText et_nilai_likuidasi;

    //END OF VIEW BINDING
    private String idAgunan;
    private AgunanKendaraan dataAgunan;

    public static String val_TipeKendaraan = "";
    public static String val_MerkKendaraan = "";
    public static String val_KendaraanJepang = "";
    public static String val_DayaAngkut = "";
    public static String val_Kapasitas = "";
    public static String val_CheckSamsat = "";
    public static String val_DenganSiapa = "";
    public static String val_NoTelp = "";
    public static String val_Hasil = "";
    public static String val_NilaiMarket = "";
    public static String val_NilaiLikuidasi = "";

    private Realm realm;

    public FragmentAgunanKendaraan3() {
    }

    public FragmentAgunanKendaraan3(String midAgunan, AgunanKendaraan magunanData) {
        dataAgunan = magunanData;
        idAgunan = midAgunan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_kendaraan_3, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();

        onSelectDialog();
        if (!String.valueOf(idAgunan).equalsIgnoreCase("0")) {
            setData();
        }
        onChangeText();

        return view;
    }

    private void onChangeText() {
        et_nilai_market.addTextChangedListener(new NumberTextWatcherForThousand(et_nilai_market));
        et_nilai_market.addTextChangedListener(this);
        et_nilai_likuidasi.addTextChangedListener(new NumberTextWatcherForThousand(et_nilai_likuidasi));
    }

    private void onSelectDialog() {
        et_kendaraan_jepang.setFocusable(false);
        et_kendaraan_jepang.setInputType(InputType.TYPE_NULL);
        et_kendaraan_jepang.setOnClickListener(this);
        et_kendaraan_jepang.setOnFocusChangeListener(this);
        tf_kendaraan_jepang.setOnClickListener(this);
        tf_kendaraan_jepang.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_kendaraan_jepang.getLabelText().toString().trim());
            }
        });

        et_check_samsat.setFocusable(false);
        et_check_samsat.setInputType(InputType.TYPE_NULL);
        et_check_samsat.setOnClickListener(this);
        et_check_samsat.setOnFocusChangeListener(this);
        tf_check_samsat.setOnClickListener(this);
        tf_check_samsat.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_check_samsat.getLabelText().toString().trim());
            }
        });

        et_hasil.setFocusable(false);
        et_hasil.setInputType(InputType.TYPE_NULL);
        et_hasil.setOnClickListener(this);
        et_hasil.setOnFocusChangeListener(this);
        tf_hasil.setOnClickListener(this);
        tf_hasil.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_hasil.getLabelText().toString().trim());
            }
        });
    }

    private void openKeyValueDialog(String title) {
        DialogKeyValue.display(getFragmentManager(), title, this);
    }

    private void setData() {
        try {
            et_tipe_kendaraan.setText(dataAgunan.getTipeKendaraan());
            et_merk_kendaraan.setText(dataAgunan.getMerkKendaraan());
            et_kendaraan_jepang.setText(dataAgunan.getMobiljepang());
            et_daya_angkut.setText(dataAgunan.getDayaAngkut());
            et_kapasitas.setText(dataAgunan.getKapasitas());
            et_check_samsat.setText(dataAgunan.getCheckSamsat());
            et_dengan_siapa.setText(dataAgunan.getDenganSiapa());
            et_no_telp.setText(dataAgunan.getNoTelpon());
            et_hasil.setText(dataAgunan.getHasil());
            et_nilai_market.setText(dataAgunan.getNilaiMarket());
            et_nilai_likuidasi.setText(dataAgunan.getNilaiTaksasi());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_kendaraan_jepang:
            case R.id.tf_kendaraan_jepang:
                openKeyValueDialog(tf_kendaraan_jepang.getLabelText().toString().trim());
                break;
            case R.id.et_check_samsat:
            case R.id.tf_check_samsat:
                openKeyValueDialog(tf_check_samsat.getLabelText().toString().trim());
                break;
            case R.id.et_hasil:
            case R.id.tf_hasil:
                openKeyValueDialog(tf_hasil.getLabelText().toString().trim());
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            switch (v.getId()) {
                case R.id.et_kendaraan_jepang:
                case R.id.tf_kendaraan_jepang:
                    openKeyValueDialog(tf_kendaraan_jepang.getLabelText().toString().trim());
                    break;
                case R.id.et_check_samsat:
                case R.id.tf_check_samsat:
                    openKeyValueDialog(tf_check_samsat.getLabelText().toString().trim());
                    break;
                case R.id.et_hasil:
                case R.id.tf_hasil:
                    openKeyValueDialog(tf_hasil.getLabelText().toString().trim());
                    break;
            }
        }
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return validateForm();
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
        if (title.equalsIgnoreCase("Kendaraan Jepang")) {
            et_kendaraan_jepang.setText(data.getName());
        } else if (title.equalsIgnoreCase("Check Samsat")) {
            et_check_samsat.setText(data.getName());
        } else if (title.equalsIgnoreCase("Hasil")) {
            et_hasil.setText(data.getName());
        }
    }

    private VerificationError validateForm() {
        if (et_tipe_kendaraan.getText().toString().isEmpty() || et_tipe_kendaraan.getText().toString().equalsIgnoreCase("")) {
            tf_tipe_kendaraan.setError("Format " + tf_tipe_kendaraan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_tipe_kendaraan.getLabelText() + " " + getString(R.string.title_validate_field));
        } else if (et_merk_kendaraan.getText().toString().isEmpty() || et_merk_kendaraan.getText().toString().equalsIgnoreCase("")) {
            tf_merk_kendaraan.setError("Format " + tf_merk_kendaraan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_merk_kendaraan.getLabelText() + " " + getString(R.string.title_validate_field));
        } else if (et_kendaraan_jepang.getText().toString().isEmpty() || et_kendaraan_jepang.getText().toString().equalsIgnoreCase("Pilih")) {
            tf_kendaraan_jepang.setError("Format " + tf_kendaraan_jepang.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_kendaraan_jepang.getLabelText() + " " + getString(R.string.title_validate_field));
        } else if (et_daya_angkut.getText().toString().isEmpty() || et_daya_angkut.getText().toString().equalsIgnoreCase("")) {
            tf_daya_angkut.setError("Format " + tf_daya_angkut.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_daya_angkut.getLabelText() + " " + getString(R.string.title_validate_field));
        } else if (et_kapasitas.getText().toString().isEmpty() || et_kapasitas.getText().toString().equalsIgnoreCase("")) {
            tf_kapasitas.setError("Format " + tf_kapasitas.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_kapasitas.getLabelText() + " " + getString(R.string.title_validate_field));
        } else if (et_check_samsat.getText().toString().isEmpty() || et_check_samsat.getText().toString().equalsIgnoreCase("Pilih")) {
            tf_check_samsat.setError("Format " + tf_check_samsat.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_check_samsat.getLabelText() + " " + getString(R.string.title_validate_field));
        } else if (et_check_samsat.getText().toString().trim().equalsIgnoreCase("Ya") && (et_dengan_siapa.getText().toString().isEmpty() || et_dengan_siapa.getText().toString().equalsIgnoreCase(""))){
            tf_dengan_siapa.setError("Format " + tf_dengan_siapa.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_dengan_siapa.getLabelText() + " " + getString(R.string.title_validate_field));
        } else if (et_no_telp.getText().toString().isEmpty() || et_no_telp.getText().toString().equalsIgnoreCase("")) {
            tf_no_telp.setError("Format " + tf_no_telp.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_no_telp.getLabelText() + " " + getString(R.string.title_validate_field));
        } else if (et_hasil.getText().toString().isEmpty() || et_hasil.getText().toString().equalsIgnoreCase("Pilih")) {
            tf_hasil.setError("Format " + tf_hasil.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_hasil.getLabelText() + " " + getString(R.string.title_validate_field));
        } else if (et_nilai_market.getText().toString().isEmpty() || et_nilai_market.getText().toString().equalsIgnoreCase("")) {
            tf_nilai_market.setError("Format " + tf_nilai_market.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_nilai_market.getLabelText() + " " + getString(R.string.title_validate_field));
        } else if (et_nilai_likuidasi.getText().toString().isEmpty() || et_nilai_likuidasi.getText().toString().equalsIgnoreCase("")) {
            tf_nilai_likuidasi.setError("Format " + tf_nilai_likuidasi.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_nilai_likuidasi.getLabelText() + " " + getString(R.string.title_validate_field));
        } else {
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner() {
        try {
            val_TipeKendaraan = (et_tipe_kendaraan.getText().toString().trim());
            val_MerkKendaraan = (et_merk_kendaraan.getText().toString().trim());
            val_KendaraanJepang = (et_kendaraan_jepang.getText().toString().trim());
            val_DayaAngkut = (et_daya_angkut.getText().toString().trim());
            val_Kapasitas = (et_kapasitas.getText().toString().trim());
            val_CheckSamsat = (et_check_samsat.getText().toString().trim());
            val_DenganSiapa = (et_dengan_siapa.getText().toString().trim());
            val_NoTelp = (et_no_telp.getText().toString().trim());
            val_Hasil = (et_hasil.getText().toString().trim());
            val_NilaiMarket = (NumberTextWatcherForThousand.trimCommaOfString(et_nilai_market.getText().toString().trim()));
            val_NilaiLikuidasi = (NumberTextWatcherForThousand.trimCommaOfString(et_nilai_likuidasi.getText().toString().trim()));

            final AgunanKendaraanPojo d = new AgunanKendaraanPojo();
            d.setTipeKendaraan(val_TipeKendaraan);
            d.setMerkKendaraan(val_MerkKendaraan);
            d.setMobiljepang(val_KendaraanJepang);
            d.setDayaAngkut(val_DayaAngkut);
            d.setKapasitas(val_Kapasitas);
            d.setCheckSamsat(val_CheckSamsat);
            d.setDenganSiapa(val_DenganSiapa);
            d.setNoTelpon(val_NoTelp);
            d.setHasil(val_Hasil);
            d.setNilaiMarket(val_NilaiMarket);
            d.setNilaiTaksasi(val_NilaiLikuidasi);

            AgunanKendaraanPojo dataR = realm.where(AgunanKendaraanPojo.class).equalTo("uuid", AgunanKendaraanInputActivity.UUIDR).findFirst();

            d.setHubungan(dataR.getHubungan());
            d.setNoFaktur(dataR.getNoFaktur());
            d.setNoMesin(dataR.getNoMesin());
            d.setBuktiGesekMesin(dataR.getBuktiGesekMesin());
            d.setNoRangka(dataR.getNoRangka());
            d.setBuktiGesekRangka(dataR.getBuktiGesekRangka());
            d.setNoPolisi(dataR.getNoPolisi());
            d.setJenisPlat(dataR.getJenisPlat());
            d.setNoBKPB(dataR.getNoBKPB());
            d.setNoSTNK(dataR.getNoSTNK());
            d.setWarna(dataR.getWarna());
            d.setTahunPembuatan(dataR.getTahunPembuatan());
            d.setIdKendaraan_Dok(dataR.getIdKendaraan_Dok());
            d.setUuid(dataR.getUuid());
            d.setIdApl(dataR.getIdApl());
            d.setIdCif(dataR.getIdCif());
            d.setIdAgunan(dataR.getIdAgunan());
            d.setTglPemeriksaan(dataR.getTglPemeriksaan());
            d.setJenisKendaraan(dataR.getJenisKendaraan());
            d.setKategKendaraan(dataR.getKategKendaraan());
            d.setPenggunaanJaminan(dataR.getPenggunaanJaminan());
            d.setDaerahOperasional(dataR.getDaerahOperasional());
            d.setKondisi(dataR.getKondisi());
            d.setNamaPemilikBPKB(dataR.getNamaPemilikBPKB());
            d.setNamaPemilikSaatIni(dataR.getNamaPemilikSaatIni());
            d.setAlamatPemilik(dataR.getAlamatPemilik());

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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        try {
            if (et_nilai_market.getText().toString().trim().length() > 0 || !et_nilai_market.getText().toString().isEmpty()) {

                BigDecimal nilai_likuidasi = new BigDecimal(0);
                BigDecimal nilai_market = new BigDecimal(0);

                nilai_market = new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_nilai_market.getText().toString()));
                nilai_likuidasi = nilai_market
                                    .multiply(new BigDecimal(80)
                                    .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP))
                                    .setScale(0, RoundingMode.HALF_UP);
                et_nilai_likuidasi.setText(String.valueOf(nilai_likuidasi));
            }
        }
        catch (Exception e){
            e.printStackTrace();
            AppUtil.showToastShort(getContext(), e.getMessage());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}

