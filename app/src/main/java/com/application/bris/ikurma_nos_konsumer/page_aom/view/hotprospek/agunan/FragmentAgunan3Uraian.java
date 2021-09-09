package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.InputType;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.database.pojo.AgunanTanahBangunanPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPasar;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.PasarListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanTanahBangunan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.pasar;
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
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunan3Uraian extends Fragment implements Step, PasarListener, KeyValueListener, View.OnClickListener, View.OnFocusChangeListener {


    @BindView(R.id.tf_jenis_bangunan)
    TextFieldBoxes tf_jenis_bangunan;
    @BindView(R.id.et_jenis_bangunan)
    ExtendedEditText et_jenis_bangunan;

    @BindView(R.id.tf_lokasi_bangunan)
    TextFieldBoxes tf_lokasi_bangunan;
    @BindView(R.id.et_lokasi_bangunan)
    ExtendedEditText et_lokasi_bangunan;

    @BindView(R.id.ll_pasar)
    LinearLayout ll_pasar;

//    @BindView(R.id.tf_rate_pasar)
//    TextFieldBoxes tf_rate_pasar;
//    @BindView(R.id.et_rate_pasar)
//    ExtendedEditText et_rate_pasar;

    @BindView(R.id.tf_nkr_pasar)
    TextFieldBoxes tf_nkr_pasar;
    @BindView(R.id.et_nkr_pasar)
    ExtendedEditText et_nkr_pasar;

    @BindView(R.id.tf_propinsi_pasar)
    TextFieldBoxes tf_propinsi_pasar;
    @BindView(R.id.et_propinsi_pasar)
    ExtendedEditText et_propinsi_pasar;

    @BindView(R.id.tf_kota_pasar)
    TextFieldBoxes tf_kota_pasar;
    @BindView(R.id.et_kota_pasar)
    ExtendedEditText et_kota_pasar;

    @BindView(R.id.tf_nama_pasar)
    TextFieldBoxes tf_nama_pasar;
    @BindView(R.id.et_nama_pasar)
    ExtendedEditText et_nama_pasar;

    @BindView(R.id.tf_alamat_pasar)
    TextFieldBoxes tf_alamat_pasar;
    @BindView(R.id.et_alamat_pasar)
    ExtendedEditText et_alamat_pasar;

    @BindView(R.id.tf_no_imb)
    TextFieldBoxes tf_no_imb;
    @BindView(R.id.et_no_imb)
    ExtendedEditText et_no_imb;
    @BindView(R.id.tf_jenis_agunan_xbrl)
    TextFieldBoxes tf_jenis_agunan_xbrl;
    @BindView(R.id.et_jenis_agunan_xbrl)
    ExtendedEditText et_jenis_agunan_xbrl;

    @BindView(R.id.tf_hub_penghuni_pemegang_hak)
    TextFieldBoxes tf_hub_penghuni_pemegang_hak;
    @BindView(R.id.et_hub_penghuni_pemegang_hak)
    ExtendedEditText et_hub_penghuni_pemegang_hak;

    @BindView(R.id.tf_jumlah_lantai)
    TextFieldBoxes tf_jumlah_lantai;
    @BindView(R.id.et_jumlah_lantai)
    ExtendedEditText et_jumlah_lantai;

    @BindView(R.id.tf_tahun_mendirikan)
    TextFieldBoxes tf_tahun_mendirikan;
    @BindView(R.id.et_tahun_mendirikan)
    ExtendedEditText et_tahun_mendirikan;

    @BindView(R.id.tf_no_bast)
    TextFieldBoxes tf_no_bast;
    @BindView(R.id.et_no_bast)
    ExtendedEditText et_no_bast;

    @BindView(R.id.tf_tanggal_bast)
    TextFieldBoxes tf_tanggal_bast;
    @BindView(R.id.et_tanggal_bast)
    ExtendedEditText et_tanggal_bast;

    @BindView(R.id.tf_no_slf)
    TextFieldBoxes tf_no_slf;
    @BindView(R.id.et_no_slf)
    ExtendedEditText et_no_slf;

    @BindView(R.id.tf_tanggal_slf)
    TextFieldBoxes tf_tanggal_slf;
    @BindView(R.id.et_tanggal_slf)
    ExtendedEditText et_tanggal_slf;

    @BindView(R.id.cl_content)
    ScrollView cl_content;


    @BindView(R.id.btn_cari_pasar)
    Button btn_cari_pasar;

    private AgunanTanahBangunan dataAgunan;
    private String idAgunan="";



    public static String val_JenisBangunan = "";
    public static String val_LokasiBangunan = "";
    public static String val_RatePasar = "";
    public static String val_NkrPasar = "";
    public static String val_PropinsiPasar = "";
    public static String val_KotaPasar = "";
    public static String val_NamaPasar = "";
    public static String val_AlamatPasar = "";
    public static String val_NoImb = "";
    public static String val_JenisAgunanXbrl = "";
    public static String val_HubPenghuniPemegangHak = "";
    public static String val_Tingkat = "";
    public static String val_TahunMendirikan = "";

    //BEGIN EMPTY STRING
    public static String val_AlamatImb = "";
    public static String val_NamaPenghuni = "";
    public static String val_StatusPenghuni = "";
    public static String val_AtasNamaIjin = "";
    //END EMPTY STRING

    private Realm realm;

    private boolean enableEditText;
    private String idProgram="";

    public FragmentAgunan3Uraian() {
    }

    public FragmentAgunan3Uraian(String midAgunan, AgunanTanahBangunan magunanTanahBangunan,boolean enableEditText) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
        this.enableEditText=enableEditText;
    }

    public FragmentAgunan3Uraian(String midAgunan, AgunanTanahBangunan magunanTanahBangunan,boolean enableEditText,String idProgram) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
        this.idProgram=idProgram;
        this.enableEditText=enableEditText;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_3_uraian_bangunan, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        onSelectDialog();

        ll_pasar.setVisibility(View.GONE);

//        if (!idAgunan.equalsIgnoreCase("0")) {
//            setData();
//        }
        setData();
        otherViewChanges();
        return view;
    }

    private void onSelectDialog() {

        et_jenis_bangunan.setFocusable(false);
        et_jenis_bangunan.setInputType(InputType.TYPE_NULL);
        et_jenis_bangunan.setOnClickListener(this);
        et_jenis_bangunan.setOnFocusChangeListener(this);
        tf_jenis_bangunan.setOnClickListener(this);
        tf_jenis_bangunan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_jenis_bangunan.getLabelText().toString().trim());
            }
        });

        et_lokasi_bangunan.setFocusable(false);
        et_lokasi_bangunan.setInputType(InputType.TYPE_NULL);
        et_lokasi_bangunan.setOnClickListener(this);
        et_lokasi_bangunan.setOnFocusChangeListener(this);
        tf_lokasi_bangunan.setOnClickListener(this);
        tf_lokasi_bangunan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_lokasi_bangunan.getLabelText().toString().trim());
            }
        });

        et_jenis_agunan_xbrl.setFocusable(false);
        et_jenis_agunan_xbrl.setInputType(InputType.TYPE_NULL);
        et_jenis_agunan_xbrl.setOnFocusChangeListener(this);
        tf_jenis_agunan_xbrl.setOnClickListener(this);
        tf_jenis_agunan_xbrl.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_jenis_agunan_xbrl.getLabelText().toString().trim());
            }
        });

        et_hub_penghuni_pemegang_hak.setFocusable(false);
        et_hub_penghuni_pemegang_hak.setInputType(InputType.TYPE_NULL);
        et_hub_penghuni_pemegang_hak.setOnFocusChangeListener(this);
        tf_hub_penghuni_pemegang_hak.setOnClickListener(this);
        tf_hub_penghuni_pemegang_hak.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_hub_penghuni_pemegang_hak.getLabelText().toString().trim());
            }
        });

        //flpp
        et_tanggal_bast.setFocusable(false);
        et_tanggal_slf.setFocusable(false);

        et_tanggal_bast.setOnClickListener(this);
        tf_tanggal_bast.setOnClickListener(this);

        et_tanggal_slf.setOnClickListener(this);
        tf_tanggal_slf.setOnClickListener(this);

        tf_tanggal_bast.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpInputTanggal(et_tanggal_bast);
            }
        });

        tf_tanggal_slf.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpInputTanggal(et_tanggal_slf);
            }
        });
    }

    private void openKeyValueDialog(String title) {
        DialogKeyValue.display(getFragmentManager(), title, this);
    }

    private void setData() {
        try {
            et_jenis_bangunan.setText(KeyValue.getKeyJenisBangunan(dataAgunan.getJenisBangunan()));
            if (et_jenis_bangunan.getText().toString().equalsIgnoreCase("Ruko / Rukan")) {
                tf_lokasi_bangunan.setVisibility(View.VISIBLE);
                et_lokasi_bangunan.setText(KeyValue.getKeyLokasiBangunan(dataAgunan.getLokasiBangunanBrins()));

                if(et_lokasi_bangunan.getText().toString().equalsIgnoreCase("Pasar")){
                    ll_pasar.setVisibility(View.VISIBLE);

                    et_nkr_pasar.setFocusable(false);
                    et_propinsi_pasar.setFocusable(false);
                    et_kota_pasar.setFocusable(false);
                    et_nama_pasar.setFocusable(false);
                    et_alamat_pasar.setFocusable(false);



                    et_nkr_pasar.setText(dataAgunan.getNkrPasarBRINS());
                    tf_propinsi_pasar.setVisibility(View.GONE);
                    tf_kota_pasar.setVisibility(View.GONE);
                    et_nama_pasar.setText(dataAgunan.getNamaPasarBRINS());
                    tf_alamat_pasar.setVisibility(View.GONE);
                    btn_cari_pasar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openPasarDialog();
                        }
                    });
                }
            } else {
                tf_lokasi_bangunan.setVisibility(View.GONE);
                et_lokasi_bangunan.getText().clear();
                ll_pasar.setVisibility(View.GONE);;
            }
            et_no_imb.setText(dataAgunan.getNoIMB());
            et_jenis_agunan_xbrl.setText(KeyValue.getKeyJenisAgunanXBRL(dataAgunan.getJenisAgunanXBRL()));
            et_hub_penghuni_pemegang_hak.setText(KeyValue.getKeyHubPenghuniDenganPemegangHak(dataAgunan.getHubPenghuniDgPemilik()));
            et_jumlah_lantai.setText(dataAgunan.getTingkat());
            et_tahun_mendirikan.setText(dataAgunan.getTahunBangunan());




            //khusus flpp, belum dipake dlu karena belum ada inquiry
            et_no_bast.setText(dataAgunan.getNoBast());
            et_no_slf.setText(dataAgunan.getNoSlf());
            et_tanggal_bast.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTglBast(), "ddMMyyyy","dd-MM-yyyy"));
            et_tanggal_slf.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTglSlf(), "ddMMyyyy","dd-MM-yyyy"));



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
        switch (v.getId()) {
            case R.id.et_jenis_bangunan:
            case R.id.tf_jenis_bangunan:
                openKeyValueDialog(tf_jenis_bangunan.getLabelText().toString().trim());
                break;

            case R.id.et_lokasi_bangunan:
            case R.id.tf_lokasi_bangunan:
                openKeyValueDialog(tf_lokasi_bangunan.getLabelText().toString().trim());
                break;

            case R.id.et_jenis_agunan_xbrl:
            case R.id.tf_jenis_agunan_xbrl:
                openKeyValueDialog(tf_jenis_agunan_xbrl.getLabelText().toString().trim());
                break;

            case R.id.et_hub_penghuni_pemegang_hak:
            case R.id.tf_hub_penghuni_pemegang_hak:
                openKeyValueDialog(tf_hub_penghuni_pemegang_hak.getLabelText().toString().trim());
                break;
            case R.id.et_tanggal_bast:
            case R.id.tf_tanggal_bast:
              dpInputTanggal(et_tanggal_bast);
                break;
            case R.id.et_tanggal_slf:
            case R.id.tf_tanggal_slf:
                dpInputTanggal(et_tanggal_slf);
                break;
        }
    }

    private void openPasarDialog(){
        DialogPasar.display(getFragmentManager(), this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            switch (v.getId()) {
                case R.id.et_jenis_bangunan:
                case R.id.tf_jenis_bangunan:
                    openKeyValueDialog(tf_jenis_bangunan.getLabelText().toString().trim());
                    break;

                case R.id.et_lokasi_bangunan:
                case R.id.tf_lokasi_bangunan:
                    openKeyValueDialog(tf_lokasi_bangunan.getLabelText().toString().trim());
                    break;

                case R.id.et_jenis_agunan_xbrl:
                case R.id.tf_jenis_agunan_xbrl:
                    openKeyValueDialog(tf_jenis_agunan_xbrl.getLabelText().toString().trim());
                    break;

                case R.id.et_hub_penghuni_pemegang_hak:
                case R.id.tf_hub_penghuni_pemegang_hak:
                    openKeyValueDialog(tf_hub_penghuni_pemegang_hak.getLabelText().toString().trim());
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
        if (title.equalsIgnoreCase("jenis bangunan")) {
            et_jenis_bangunan.setText(data.getName());
            et_lokasi_bangunan.getText().clear();

            if (data.getName().equalsIgnoreCase("Ruko / Rukan")) {
                tf_lokasi_bangunan.setVisibility(View.VISIBLE);
            } else {
                tf_lokasi_bangunan.setVisibility(View.GONE);
                ll_pasar.setVisibility(View.GONE);
            }
        } else if (title.equalsIgnoreCase(tf_lokasi_bangunan.getLabelText().toString())) {
            et_lokasi_bangunan.setText(data.getName());

            if(data.getName().equalsIgnoreCase("Pasar")){
                ll_pasar.setVisibility(View.VISIBLE);
                et_nkr_pasar.setFocusable(false);
                et_propinsi_pasar.setFocusable(false);
                et_kota_pasar.setFocusable(false);
                et_nama_pasar.setFocusable(false);
                et_alamat_pasar.setFocusable(false);
                btn_cari_pasar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openPasarDialog();
                    }
                });
            }
            else{
                ll_pasar.setVisibility(View.GONE);
            }

        } else if (title.equalsIgnoreCase("hub penghuni dengan pemegang hak")) {
            et_hub_penghuni_pemegang_hak.setText(data.getName());
        } else if (title.equalsIgnoreCase("Jenis Agunan XBRL")) {
            et_jenis_agunan_xbrl.setText(data.getName());
        }
    }

    @Override
    public void onPasarSelect(pasar data) {
        val_RatePasar = data.getRATE();
        et_nkr_pasar.setText(data.getNKR());
        tf_propinsi_pasar.setVisibility(View.VISIBLE);
        et_propinsi_pasar.setText(data.getPROVINSI());
        tf_kota_pasar.setVisibility(View.VISIBLE);
        et_kota_pasar.setText(data.getKOTA_KAB());
        et_nama_pasar.setText(data.getNAMA_PASAR());
        tf_alamat_pasar.setVisibility(View.VISIBLE);
        et_alamat_pasar.setText(data.getALAMAT_PASAR());
    }

    private VerificationError validateForm() {
        if (et_jenis_bangunan.getText().toString().isEmpty() || et_jenis_bangunan.getText().toString().equalsIgnoreCase("Pilih")) {
            tf_jenis_bangunan.setError("Format " + tf_jenis_bangunan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_jenis_bangunan.getLabelText() + " " + getString(R.string.title_validate_field));
        } else if (tf_lokasi_bangunan.getVisibility() == View.VISIBLE && (et_lokasi_bangunan.getText().toString().isEmpty() || et_lokasi_bangunan.getText().toString().equalsIgnoreCase("Pilih"))) {
            tf_lokasi_bangunan.setError("Format " + tf_lokasi_bangunan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_lokasi_bangunan.getLabelText() + " " + getString(R.string.title_validate_field));
        }

        //PROPINSI SAMA KOTA PASAR GAUSAH DIVALIDASI, BIKIN REPOT, PADAHAL DATANYA JUGA GAK DIPAKE, DAN SUDAH OTOMATIS TERISI KALAU PILIH PASAR

//        else if (ll_pasar.getVisibility() == View.VISIBLE && (et_propinsi_pasar.getText().toString().isEmpty() || et_propinsi_pasar.getText().toString().equalsIgnoreCase(""))) {
//            tf_propinsi_pasar.setError("Format " + tf_propinsi_pasar.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return new VerificationError("Format " + tf_propinsi_pasar.getLabelText() + " " + getString(R.string.title_validate_field));
//        } else if (ll_pasar.getVisibility() == View.VISIBLE && (et_kota_pasar.getText().toString().isEmpty() || et_kota_pasar.getText().toString().equalsIgnoreCase(""))) {
//            tf_kota_pasar.setError("Format " + tf_kota_pasar.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return new VerificationError("Format " + tf_kota_pasar.getLabelText() + " " + getString(R.string.title_validate_field));
//        }
//        else if (ll_pasar.getVisibility() == View.VISIBLE && (et_alamat_pasar.getText().toString().isEmpty() || et_alamat_pasar.getText().toString().equalsIgnoreCase(""))) {
//            tf_alamat_pasar.setError("Format " + tf_alamat_pasar.getLabelText() + " " + getString(R.string.title_validate_field), true);
//            return new VerificationError("Format " + tf_alamat_pasar.getLabelText() + " " + getString(R.string.title_validate_field));
//        }

        else if (ll_pasar.getVisibility() == View.VISIBLE && (et_nama_pasar.getText().toString().isEmpty() || et_nama_pasar.getText().toString().equalsIgnoreCase(""))) {
            tf_nama_pasar.setError("Format " + tf_nama_pasar.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_nama_pasar.getLabelText() + " " + getString(R.string.title_validate_field));
        } else if (ll_pasar.getVisibility() == View.VISIBLE && (et_nkr_pasar.getText().toString().isEmpty() || et_nkr_pasar.getText().toString().equalsIgnoreCase(""))) {
            tf_nkr_pasar.setError("Format " + tf_nkr_pasar.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_nkr_pasar.getLabelText() + " " + getString(R.string.title_validate_field));
        }

        else if (et_no_imb.getText().toString().isEmpty() || et_no_imb.getText().toString().equalsIgnoreCase("")) {
            tf_no_imb.setError("Format " + tf_no_imb.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_no_imb.getLabelText() + " " + getString(R.string.title_validate_field));
        }

        else if (et_jenis_agunan_xbrl.getText().toString().isEmpty() || et_jenis_agunan_xbrl.getText().toString().equalsIgnoreCase("Pilih")) {
            tf_jenis_agunan_xbrl.setError("Format " + tf_jenis_agunan_xbrl.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_jenis_agunan_xbrl.getLabelText() + " " + getString(R.string.title_validate_field));
        }

        else if (et_hub_penghuni_pemegang_hak.getText().toString().isEmpty() || et_hub_penghuni_pemegang_hak.getText().toString().equalsIgnoreCase("Pilih")) {
            tf_hub_penghuni_pemegang_hak.setError("Format " + tf_hub_penghuni_pemegang_hak.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_hub_penghuni_pemegang_hak.getLabelText() + " " + getString(R.string.title_validate_field));
        }
        else if (et_jumlah_lantai.getText().toString().isEmpty() || et_jumlah_lantai.getText().toString().equalsIgnoreCase("")) {
            tf_jumlah_lantai.setError("Format " + tf_jumlah_lantai.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_jumlah_lantai.getLabelText() + " " + getString(R.string.title_validate_field));
        }
        else if (et_tahun_mendirikan.getText().toString().isEmpty() || et_tahun_mendirikan.getText().toString().equalsIgnoreCase("")) {
            tf_tahun_mendirikan.setError("Format " + tf_tahun_mendirikan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_tahun_mendirikan.getLabelText() + " " + getString(R.string.title_validate_field));
        }
        else if (idProgram.equalsIgnoreCase("222")&&(et_no_bast.getText().toString().isEmpty() || et_no_bast.getText().toString().equalsIgnoreCase(""))) {
            tf_no_bast.setError("Format " + tf_no_bast.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_no_bast.getLabelText() + " " + getString(R.string.title_validate_field));
        }
        else if (idProgram.equalsIgnoreCase("222")&&(et_tanggal_bast.getText().toString().isEmpty() || et_tanggal_bast.getText().toString().equalsIgnoreCase("")|| et_tanggal_bast.getText().toString().equalsIgnoreCase("pilih tanggal"))) {
            tf_tanggal_bast.setError("Format " + tf_tanggal_bast.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_tanggal_bast.getLabelText() + " " + getString(R.string.title_validate_field));
        }
        else if (idProgram.equalsIgnoreCase("222")&&(et_no_slf.getText().toString().isEmpty() || et_no_slf.getText().toString().equalsIgnoreCase(""))) {
            tf_no_slf.setError("Format " + tf_no_slf.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_no_slf.getLabelText() + " " + getString(R.string.title_validate_field));
        }
        else if (idProgram.equalsIgnoreCase("222")&&(et_tanggal_slf.getText().toString().isEmpty() || et_tanggal_slf.getText().toString().equalsIgnoreCase("")|| et_tanggal_slf.getText().toString().equalsIgnoreCase("pilih tanggal"))) {
            tf_tanggal_slf.setError("Format " + tf_tanggal_slf.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_tanggal_slf.getLabelText() + " " + getString(R.string.title_validate_field));
        }

        else {
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner() {
        val_JenisBangunan = (KeyValue.getTypeJenisBangunan(et_jenis_bangunan.getText().toString().trim()));
        if (tf_lokasi_bangunan.getVisibility() == View.VISIBLE) {
            val_LokasiBangunan = (et_lokasi_bangunan.getText().toString().trim());
            Log.d("lokasipasar",": masukkelokasipasar");
            if (ll_pasar.getVisibility() == View.VISIBLE){
                Log.d("lokasipasar","masuk ke ll pasar");
                val_NkrPasar = et_nkr_pasar.getText().toString().trim();
                val_PropinsiPasar = et_propinsi_pasar.getText().toString().trim();
                val_KotaPasar = et_kota_pasar.getText().toString().trim();
                val_NamaPasar = et_nama_pasar.getText().toString().trim();
                val_AlamatPasar = et_alamat_pasar.getText().toString().trim();
            }
        } else {
            val_LokasiBangunan = "NonPasar";
        }
        val_NoImb = (et_no_imb.getText().toString().trim());
        val_JenisAgunanXbrl = (KeyValue.getTypeJenisAgunanXBRL(et_jenis_agunan_xbrl.getText().toString().trim()));
        val_HubPenghuniPemegangHak = (KeyValue.getTypeHubPenghuniDenganPemegangHak(et_hub_penghuni_pemegang_hak.getText().toString().trim()));
        val_Tingkat = (et_jumlah_lantai.getText().toString().trim());
        val_TahunMendirikan = (et_tahun_mendirikan.getText().toString().trim());

        final AgunanTanahBangunanPojo d = new AgunanTanahBangunanPojo();
        d.setJenisBangunan(val_JenisBangunan);
        d.setLokasiBangunanBrins(val_LokasiBangunan);
        d.setRate(val_RatePasar);
        d.setNamaPasar(val_NamaPasar);
        d.setNkrBrins(val_NkrPasar);
        d.setProvinsi(val_PropinsiPasar);
        d.setKota_kab(val_KotaPasar);
        d.setNama_pasar(val_NamaPasar);
        d.setAlamat_pasar(val_AlamatPasar);
        d.setNoIMB(val_NoImb);
        d.setJenisAgunanXBRL(val_JenisAgunanXbrl);
        d.setHubPenghuniDgPemilik(val_HubPenghuniPemegangHak);
        d.setTingkat(val_Tingkat);
        d.setAlamatIMB(val_AlamatImb);
        d.setNamaPenghuni(val_NamaPenghuni);
        d.setStatusPenghuni(val_StatusPenghuni);
        d.setAtasNamaIMB(val_AtasNamaIjin);
        d.setTahunBangunan(val_TahunMendirikan);

        d.setNoBast(et_no_bast.getText().toString().trim());
        d.setTglBast(et_tanggal_bast.getText().toString().trim().replace("-",""));
        d.setNoSlf(et_no_slf.getText().toString().trim());
        d.setTglSlf(et_tanggal_slf.getText().toString().trim().replace("-",""));

        Log.d("lokasipasar","namapasar :"+d.getNamaPasar());
        Log.d("lokasipasar","nkr pasar : "+d.getNkrBrins());

        AgunanTanahBangunanPojo dataR = realm.where(AgunanTanahBangunanPojo.class).equalTo("uuid", AgunanTanahBangunanInputActivity.uuid).findFirst();



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
        d.setIdDokPBB(dataR.getIdDokIMB());
        d.setIdDokIMB(dataR.getIdDokPBB());

        //flpp
        d.setKodeWilayah(dataR.getKodeWilayah());
        d.setNoAlamat(dataR.getNoAlamat());

        //halaman 4 keatas
        d.setPendapatKondisiJaminan("");
        d.setIdBangunan(0);
        d.setIdBatas_Utara(0);
        d.setIdBatas_Selatan(0);
        d.setIdBatas_Timur(0);
        d.setIdBatas_Barat(0);
        d.setPendapatHarga("");
        d.setKondisiSekitar("");
        d.setKodeDati("");
        d.setCollID("");
        d.setRate("");
        d.setIdTanah(0);
        d.setTipeProduk(AppUtil.parseIntWithDefault(AgunanTanahBangunanInputActivity.tp_produk, 0));
        d.setTanggalPemeriksaan("");
        d.setDataPembanding1("");
        d.setDataPembanding2("");
        d.setLuasTanahFisik("");
        d.setHargaMeterTanah(0);
        d.setLuasBangunan1(0);
        d.setLuasBangunan2(0);
        d.setHargaBangunan1(0);
        d.setHargaBangunan2(0);
        d.setNLTanahBangunan(0);
        d.setNPWTanahBangunan(0);
        d.setNPWBangunan(0);
        d.setNPWBangunanBrins(0);
        d.setDataPembanding3("");
        d.setPeruntukanLokasi("");
        d.setAksesJalanObjek("");
        d.setFasilitasSosial("");
        d.setLebarJalanDepan(0);
        d.setPerkembanganLingkungan("");
        d.setKepadatan("");
        d.setAksesPencapaian("");
        d.setPerawatan("");
        d.setJenisBangunanBRINS("");
        d.setPondasi("");
        d.setDinding("");
        d.setAtap("");
        d.setFasilitasListrik(false);
        d.setFasilitasPAM(false);
        d.setTelepon(false);
        d.setTeganganListrik("");
        d.setJenisAir("");
        d.setNoTelepon("");
        d.setPlafond("");
        d.setLantai("");
        d.setTeras("");
        d.setPagar("");
        d.setGarasiCarport("");

//        d.setJenisBangunan(dataR.getJenisBangunan());
//        d.setLokasiBangunanBrins(dataR.getLokasiBangunanBrins());
//        d.setNoIMB(dataR.getNoIMB());
//        d.setJenisAgunanXBRL(dataR.getJenisAgunanXBRL());
//        d.setHubPenghuniDgPemilik(dataR.getHubPenghuniDgPemilik());
//        d.setTingkat(dataR.getTingkat());
//        d.setAlamatIMB(dataR.getAlamatIMB());
//        d.setNamaPenghuni(dataR.getNamaPenghuni());
//        d.setStatusPenghuni(dataR.getStatusPenghuni());
//        d.setAtasNamaIMB(dataR.getAtasNamaIMB());
//        d.setTahunBangunan(dataR.getTahunBangunan());
//        d.setJenisSuratTanah(dataR.getJenisSuratTanah());
//        d.setNoSertifikat(dataR.getNoSertifikat());
//        d.setAtasNamaSertifikat(dataR.getAtasNamaSertifikat());
//        d.setTanggalSertifikat(dataR.getTanggalSertifikat());
//        d.setLuasTanahSertifikat(dataR.getLuasTanahSertifikat());
//        d.setIdDok_BPN(dataR.getIdDok_BPN());
//        d.setNoGS(dataR.getNoGS());
//        d.setHubNasabahDgnPemegangHak(dataR.getHubNasabahDgnPemegangHak());
//        d.setMasaHakAtasTanah(dataR.getMasaHakAtasTanah());
//        d.setUuid(dataR.getUuid());
//        d.setIdAgunan(dataR.getIdAgunan());
//        d.setIdApl(dataR.getIdApl());
//        d.setCif(dataR.getCif());
//        d.setLokasiTanah(dataR.getLokasiTanah());
//        d.setKodePos(dataR.getKodePos());
//        d.setKelurahan(dataR.getKelurahan());
//        d.setKecamatan(dataR.getKecamatan());
//        d.setKota(dataR.getKota());
//        d.setPropinsi(dataR.getPropinsi());
//        d.setBatasUtaraTanah(dataR.getBatasUtaraTanah());
//        d.setBatasSelatanTanah(dataR.getBatasSelatanTanah());
//        d.setBatasBaratTanah(dataR.getBatasBaratTanah());
//        d.setBatasTimurTanah(dataR.getBatasTimurTanah());
//        d.setKavBlok(dataR.getKavBlok());
//        d.setRT(dataR.getRT());
//        d.setRW(dataR.getRW());
//        d.setKoordinat(dataR.getKoordinat());
//        d.setBentukTanah(dataR.getBentukTanah());
//        d.setPermukaanTanah(dataR.getPermukaanTanah());
//        d.setNamaPasar(dataR.getNamaPasar());
//        d.setNkrBrins(dataR.getNkrBrins());
//        d.setProvinsi(dataR.getProvinsi());
//        d.setKota_kab(dataR.getKota_kab());
//        d.setNama_pasar(dataR.getNama_pasar());
//        d.setAlamat_pasar(dataR.getAlamat_pasar());

        realm.executeTransaction (new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(d);
            }
        });

    }

    private void dpInputTanggal(EditText editText){
        Calendar calPemeriksaan = Calendar.getInstance();
        SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        DatePickerDialog.OnDateSetListener ls_expiredDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calPemeriksaan.set(Calendar.YEAR, year);
                calPemeriksaan.set(Calendar.MONTH, month);
                calPemeriksaan.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calExpiredString = dateClient.format(calPemeriksaan.getTime());
                editText.setText(calExpiredString);
            }
        };

        DatePickerDialog dpTglPemeriksaan = new DatePickerDialog(getContext(), R.style.AppTheme_TimePickerTheme, ls_expiredDate, calPemeriksaan.get(Calendar.YEAR), calPemeriksaan.get(Calendar.MONTH), calPemeriksaan.get(Calendar.DAY_OF_MONTH));
//        dpTglPemeriksaan.getDatePicker().setMaxDate(calPemeriksaan.getTimeInMillis());
        dpTglPemeriksaan.show();
    }

    private void otherViewChanges(){
        if(enableEditText==false){
            btn_cari_pasar.setVisibility(View.GONE);

        }
        if(idProgram.equalsIgnoreCase("222")){

            //kode wilayah harus 10 digit (sudah termasuk kelurahan) di comment dlu
//            if(dataAgunan.getKodeWilayah().length()<10){
//                tv_warning_kode_wilayah.setVisibility(View.VISIBLE);
//            }

            tf_tanggal_slf.setVisibility(View.VISIBLE);
            tf_tanggal_bast.setVisibility(View.VISIBLE);
            tf_no_bast.setVisibility(View.VISIBLE);
            tf_no_slf.setVisibility(View.VISIBLE);
        }
        else{
            tf_tanggal_slf.setVisibility(View.GONE);
            tf_tanggal_bast.setVisibility(View.GONE);
            tf_no_bast.setVisibility(View.GONE);
            tf_no_slf.setVisibility(View.GONE);
        }
    }
}



