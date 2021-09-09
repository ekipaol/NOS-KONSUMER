package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;




import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.InputType;
import android.widget.ScrollView;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.pojo.AgunanTanahBangunanPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogListJenisBangunan;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ListJenisBangunanListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanTanahBangunan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.ListJenisBangunan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.KeyValue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunan4Spek extends Fragment implements Step, KeyValueListener, ListJenisBangunanListener, View.OnClickListener, View.OnFocusChangeListener {

    @BindView(R.id.tf_kondisi_bangunan)
    TextFieldBoxes tf_kondisi_bangunan;
    @BindView(R.id.et_kondisi_bangunan)
    ExtendedEditText et_kondisi_bangunan;

    @BindView(R.id.tf_jenis_bangunan_spek)
    TextFieldBoxes tf_jenis_bangunan_spek;
    @BindView(R.id.et_jenis_bangunan_spek)
    ExtendedEditText et_jenis_bangunan_spek;

    @BindView(R.id.tf_pondasi)
    TextFieldBoxes tf_pondasi;
    @BindView(R.id.et_pondasi)
    ExtendedEditText et_pondasi;

    @BindView(R.id.tf_dinding)
    TextFieldBoxes tf_dinding;
    @BindView(R.id.et_dinding)
    ExtendedEditText et_dinding;

    @BindView(R.id.tf_atap)
    TextFieldBoxes tf_atap;
    @BindView(R.id.et_atap)
    ExtendedEditText et_atap;

    @BindView(R.id.ada_listrik)
    CheckBox ada_listrik;
    @BindView(R.id.ada_air)
    CheckBox ada_air;
    @BindView(R.id.ada_telepon)
    CheckBox ada_telepon;

    @BindView(R.id.tf_listrik)
    TextFieldBoxes tf_listrik;
    @BindView(R.id.et_listrik)
    ExtendedEditText et_listrik;

    @BindView(R.id.tf_air)
    TextFieldBoxes tf_air;
    @BindView(R.id.et_air)
    ExtendedEditText et_air;

    @BindView(R.id.tf_telepon)
    TextFieldBoxes tf_telepon;
    @BindView(R.id.et_telepon)
    ExtendedEditText et_telepon;

    @BindView(R.id.cl_content)
    ScrollView cl_content;



    @BindView(R.id.ll_kondisi_bangunan)
    RelativeLayout ll_kondisi_bangunan;

    List<ListJenisBangunan> listJenisBangunan;
    private ApiClientAdapter apiClientAdapter;
    private List listJenisBangunanArray = new ArrayList<>();
    private ArrayAdapter adapterListJenisBangunan;

    private AgunanTanahBangunan dataAgunan;
    private String idAgunan="";
    private boolean listrik, air, telepon;

    public static String val_KondisiBangunan ="";
    public static String val_JenisBangunanSpek ="";
    public static String val_Pondasi ="";
    public static String val_Dinding ="";
    public static boolean val_AdaListrik = true;
    public static boolean val_AdaAir = true;
    public static boolean val_AdaTelepon = true;
    public static String val_Listrik ="";
    public static String val_Air ="";
    public static String val_Telepon ="";
    public static String val_Atap ="";

    //BEGIN EMPTY STRING
    public static String val_PlafonAgunan ="";
    public static String val_LantaiSpek ="";
    public static String val_Teras ="";
    public static String val_Pagar ="";
    public static String val_Garasi ="";

    private Realm realm;

    private boolean enableEditText;

    public FragmentAgunan4Spek() {
    }

    public FragmentAgunan4Spek(String midAgunan, AgunanTanahBangunan magunanTanahBangunan,boolean enableEditText) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
        this.enableEditText=enableEditText;
    }

    public FragmentAgunan4Spek(String midAgunan, AgunanTanahBangunan magunanTanahBangunan,boolean enableEditText, List<ListJenisBangunan> listJenisBangunan) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
        this.enableEditText=enableEditText;
        this.listJenisBangunan=listJenisBangunan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_4_spesifikasi, container, false);
        ButterKnife.bind(this, view);
        apiClientAdapter = new ApiClientAdapter(getContext());
        realm = Realm.getDefaultInstance();
        onSelectDialog();
//        if(!idAgunan.equalsIgnoreCase("0")) {
//            setData();
//        }

        setData();



        ada_listrik.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ada_listrik.isChecked()) {
                    tf_listrik.setEnabled(true);
                    listrik = true;
                } else {
                    tf_listrik.setEnabled(false);
                    listrik = false;
                }
            }
        });

        ada_air.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ada_air.isChecked()) {
                    tf_air.setEnabled(true);
                    air = true;
                } else {
                    tf_air.setEnabled(false);
                    air = false;
                }
            }
        });

        ada_telepon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ada_telepon.isChecked()) {
                    tf_telepon.setEnabled(true);
                    telepon = true;
                } else {
                    tf_telepon.setEnabled(false);
                    telepon = false;
                }
            }
        });

        loadJenisBangunan();




        return view;
    }

    private void loadJenisBangunan() {
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listJenisBangunan(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<ListJenisBangunan>>() {}.getType();

                            listJenisBangunan = gson.fromJson(listDataString, type);

                            onclickSelectDialogJenisBangunan();

                            otherViewChanges();

                        } else {
//                            finish();
                            AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                t.printStackTrace();
                Toast.makeText(getContext(), "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void onclickSelectDialogJenisBangunan() {

        et_jenis_bangunan_spek.setFocusable(false);
        et_jenis_bangunan_spek.setInputType(InputType.TYPE_NULL);
        et_jenis_bangunan_spek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogListJenisBangunan.display(getFragmentManager(), tf_jenis_bangunan_spek.getLabelText().toString().trim(), listJenisBangunan, FragmentAgunan4Spek.this);
            }
        });
        et_jenis_bangunan_spek.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogListJenisBangunan.display(getFragmentManager(), tf_jenis_bangunan_spek.getLabelText().toString().trim(), listJenisBangunan, FragmentAgunan4Spek.this);
            }
        });
        tf_jenis_bangunan_spek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogListJenisBangunan.display(getFragmentManager(), tf_jenis_bangunan_spek.getLabelText().toString().trim(), listJenisBangunan, FragmentAgunan4Spek.this);
            }
        });
        tf_jenis_bangunan_spek.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogListJenisBangunan.display(getFragmentManager(), tf_jenis_bangunan_spek.getLabelText().toString().trim(), listJenisBangunan, FragmentAgunan4Spek.this);
            }
        });
    }

    @Override
    public void onSelectJenisBangunan(String title, ListJenisBangunan data) {
        et_jenis_bangunan_spek.setText(data.getDESC_BRINS());
        val_JenisBangunanSpek = data.getKODE_OKUPASI();

    }

    private void onSelectDialog(){
        et_kondisi_bangunan.setFocusable(false);
        et_kondisi_bangunan.setInputType(InputType.TYPE_NULL);
        et_kondisi_bangunan.setOnClickListener(this);
        et_kondisi_bangunan.setOnFocusChangeListener(this);
        tf_kondisi_bangunan.setOnClickListener(this);
        tf_kondisi_bangunan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_kondisi_bangunan.getLabelText().toString().trim());
            }
        });

//        et_jenis_bangunan_spek.setFocusable(false);
//        et_jenis_bangunan_spek.setInputType(InputType.TYPE_NULL);
//        et_jenis_bangunan_spek.setOnClickListener(this);
//        et_jenis_bangunan_spek.setOnFocusChangeListener(this);
//        tf_jenis_bangunan_spek.setOnClickListener(this);
//        tf_jenis_bangunan_spek.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openKeyValueDialog(tf_jenis_bangunan_spek.getLabelText().toString().trim());
//            }
//        });

        et_pondasi.setFocusable(false);
        et_pondasi.setInputType(InputType.TYPE_NULL);
        et_pondasi.setOnFocusChangeListener(this);
        tf_pondasi.setOnClickListener(this);
        tf_pondasi.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_pondasi.getLabelText().toString().trim());
            }
        });

        et_dinding.setFocusable(false);
        et_dinding.setInputType(InputType.TYPE_NULL);
        et_dinding.setOnFocusChangeListener(this);
        tf_dinding.setOnClickListener(this);
        tf_dinding.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_dinding.getLabelText().toString().trim());
            }
        });

        et_atap.setFocusable(false);
        et_atap.setInputType(InputType.TYPE_NULL);
        et_atap.setOnFocusChangeListener(this);
        tf_atap.setOnClickListener(this);
        tf_atap.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_atap.getLabelText().toString().trim());
            }
        });
    }

    private void openKeyValueDialog(String title){
        DialogKeyValue.display(getFragmentManager(), title, this);
    }

    private void setData(){
        try {
            et_kondisi_bangunan.setText(KeyValue.getKeyKondisiBangunan(dataAgunan.getPerawatan()));
//            et_jenis_bangunan_spek.setText(KeyValue.getKeyJenisBangunanSpek(dataAgunan.getJenisBangunanBRINS()));
            et_jenis_bangunan_spek.setText(dataAgunan.getKetBangunan());
            val_JenisBangunanSpek = dataAgunan.getJenisBangunanBRINS();

            et_pondasi.setText(KeyValue.getKeyPondasi(dataAgunan.getPondasi()));
            et_dinding.setText(KeyValue.getKeyDinding(dataAgunan.getDinding()));
            et_atap.setText(KeyValue.getKeyAtap(dataAgunan.getAtap()));
            if (dataAgunan.getTeganganListrik()!=null&&!dataAgunan.getTeganganListrik().isEmpty()) {
                ada_listrik.setChecked(true);
                tf_listrik.setEnabled(true);
                listrik = true;
            }
            else{
                listrik = false;
            }
            if (dataAgunan.getJenisAir()!=null&&!dataAgunan.getJenisAir().isEmpty()) {
                ada_air.setChecked(true);
                tf_air.setEnabled(true);
                air = true;
            }
            else {
                air = false;
            }
            if (dataAgunan.getNoTelepon()!=null&&!dataAgunan.getNoTelepon().isEmpty()) {
                ada_telepon.setChecked(true);
                tf_telepon.setEnabled(true);
                telepon = true;
            }
            else {
                telepon = false;
            }
            et_listrik.setText(dataAgunan.getTeganganListrik());
            et_air.setText(dataAgunan.getJenisAir());
            et_telepon.setText(dataAgunan.getNoTelepon());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase("Kondisi Bangunan")){
            et_kondisi_bangunan.setText(data.getName());
        }
//        else if (title.equalsIgnoreCase("Jenis Okupasi Asuransi")){
//            et_jenis_bangunan_spek.setText(data.getName());
//        }
        else if (title.equalsIgnoreCase("Pondasi")){
            et_pondasi.setText(data.getName());
        }
        else if (title.equalsIgnoreCase("Dinding")){
            et_dinding.setText(data.getName());
        }
        else if (title.equalsIgnoreCase("Atap")){
            et_atap.setText(data.getName());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_kondisi_bangunan:
            case R.id.tf_kondisi_bangunan:
                openKeyValueDialog(tf_kondisi_bangunan.getLabelText().toString().trim());
                break;

//            case R.id.et_jenis_bangunan_spek:
//            case R.id.tf_jenis_bangunan_spek:
//                openKeyValueDialog(tf_jenis_bangunan_spek.getLabelText().toString().trim());
//                break;

            case R.id.et_pondasi:
            case R.id.tf_pondasi:
                openKeyValueDialog(tf_pondasi.getLabelText().toString().trim());
                break;

            case R.id.et_dinding:
            case R.id.tf_dinding:
                openKeyValueDialog(tf_dinding.getLabelText().toString().trim());
                break;

            case R.id.et_atap:
            case R.id.tf_atap:
                openKeyValueDialog(tf_atap.getLabelText().toString().trim());
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            switch (v.getId()){
                case R.id.et_kondisi_bangunan:
                case R.id.tf_kondisi_bangunan:
                    openKeyValueDialog(tf_kondisi_bangunan.getLabelText().toString().trim());
                    break;

//                case R.id.et_jenis_bangunan_spek:
//                case R.id.tf_jenis_bangunan_spek:
//                    openKeyValueDialog(tf_jenis_bangunan_spek.getLabelText().toString().trim());
//                    break;

                case R.id.et_pondasi:
                case R.id.tf_pondasi:
                    openKeyValueDialog(tf_pondasi.getLabelText().toString().trim());
                    break;

                case R.id.et_dinding:
                case R.id.tf_dinding:
                    openKeyValueDialog(tf_dinding.getLabelText().toString().trim());
                    break;

                case R.id.et_atap:
                case R.id.tf_atap:
                    openKeyValueDialog(tf_atap.getLabelText().toString().trim());
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

    private VerificationError validateForm() {
        if (et_kondisi_bangunan.getText().toString().isEmpty() || et_kondisi_bangunan.getText().toString().equalsIgnoreCase("Pilih")) {
            tf_kondisi_bangunan.setError("Format " + tf_kondisi_bangunan.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_kondisi_bangunan.getLabelText() + " " + getString(R.string.title_validate_field));
        } else if (et_jenis_bangunan_spek.getText().toString().isEmpty() || et_jenis_bangunan_spek.getText().toString().equalsIgnoreCase("Pilih")) {
            tf_jenis_bangunan_spek.setError("Format " + tf_jenis_bangunan_spek.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_jenis_bangunan_spek.getLabelText() + " " + getString(R.string.title_validate_field));
        } else if (et_pondasi.getText().toString().isEmpty() || et_pondasi.getText().toString().equalsIgnoreCase("Pilih")) {
            tf_pondasi.setError("Format " + tf_pondasi.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_pondasi.getLabelText() + " " + getString(R.string.title_validate_field));
        } else if (et_dinding.getText().toString().isEmpty() || et_dinding.getText().toString().equalsIgnoreCase("Pilih")) {
            tf_dinding.setError("Format " + tf_dinding.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_dinding.getLabelText() + " " + getString(R.string.title_validate_field));
        }

        else if (et_atap.getText().toString().isEmpty() || et_atap.getText().toString().equalsIgnoreCase("Pilih")) {
            tf_atap.setError("Format " + tf_atap.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_atap.getLabelText() + " " + getString(R.string.title_validate_field));
        }

        else if (ada_listrik.isChecked() && (et_listrik.getText().toString().isEmpty() || et_listrik.getText().toString().equalsIgnoreCase(""))){
            tf_listrik.setError("Format " + tf_listrik.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_listrik.getLabelText() + " " + getString(R.string.title_validate_field));
        }

        else if (ada_air.isChecked() && (et_air.getText().toString().isEmpty() || et_air.getText().toString().equalsIgnoreCase(""))){
            tf_air.setError("Format " + tf_air.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_air.getLabelText() + " " + getString(R.string.title_validate_field));
        }

        else if (ada_telepon.isChecked() && (et_telepon.getText().toString().isEmpty() || et_telepon.getText().toString().equalsIgnoreCase(""))){
            tf_telepon.setError("Format " + tf_telepon.getLabelText() + " " + getString(R.string.title_validate_field), true);
            return new VerificationError("Format " + tf_telepon.getLabelText() + " " + getString(R.string.title_validate_field));
        }

        else
            setDataOnListerner();
        return null;
    }

    public void setDataOnListerner(){
        try {
            val_KondisiBangunan = (KeyValue.getTypeKondisiBangunan(et_kondisi_bangunan.getText().toString().trim()));
//            val_JenisBangunanSpek = (KeyValue.getTypeJenisBangunanSpek(et_jenis_bangunan_spek.getText().toString().trim()));
            val_Pondasi = (KeyValue.getTypePondasi(et_pondasi.getText().toString().trim()));
            val_Dinding = (KeyValue.getTypeDinding(et_dinding.getText().toString().trim()));
            val_Atap = (KeyValue.getTypeAtap(et_atap.getText().toString().trim()));
            val_AdaListrik = listrik;
            val_AdaAir = air;
            val_AdaTelepon = telepon;
            val_Listrik = (et_listrik.getText().toString().trim());
            val_Air = (et_air.getText().toString().trim());
            val_Telepon = (et_telepon.getText().toString().trim());

            final AgunanTanahBangunanPojo d = new AgunanTanahBangunanPojo();

            d.setPerawatan(val_KondisiBangunan);
            d.setJenisBangunanBRINS(val_JenisBangunanSpek);
            d.setPondasi(val_Pondasi);
            d.setDinding(val_Dinding);
            d.setAtap(val_Atap);
            d.setFasilitasListrik(val_AdaListrik);
            d.setFasilitasPAM(val_AdaAir);
            d.setTelepon(val_AdaTelepon);
            d.setTeganganListrik(val_Listrik);
            d.setJenisAir(val_Air);
            d.setNoTelepon(val_Telepon);
            d.setPlafond(val_PlafonAgunan);
            d.setLantai(val_LantaiSpek);
            d.setTeras(val_Teras);
            d.setPagar(val_Pagar);
            d.setGarasiCarport(val_Garasi);
            d.setKetBangunan(et_jenis_bangunan_spek.getText().toString());

            AgunanTanahBangunanPojo dataR = realm.where(AgunanTanahBangunanPojo.class).equalTo("uuid", AgunanTanahBangunanInputActivity.uuid).findFirst();

            d.setJenisBangunan(dataR.getJenisBangunan());
            d.setLokasiBangunanBrins(dataR.getLokasiBangunanBrins());
            d.setRate(dataR.getRate());
            d.setNamaPasar(dataR.getNamaPasar());
            d.setNkrBrins(dataR.getNkrBrins());
            d.setProvinsi(dataR.getProvinsi());
            d.setKota_kab(dataR.getKota_kab());
            d.setNama_pasar(dataR.getNama_pasar());
            d.setAlamat_pasar(dataR.getAlamat_pasar());
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

            d.setIdDokPBB(dataR.getIdDokIMB());
            d.setIdDokIMB(dataR.getIdDokPBB());

            //flpp
            d.setKodeWilayah(dataR.getKodeWilayah());
            d.setNoAlamat(dataR.getNoAlamat());
            d.setNoBast(dataR.getNoBast());
            d.setTglBast(dataR.getTglBast());
            d.setNoSlf(dataR.getNoSlf());
            d.setTglSlf(dataR.getTglSlf());




            realm.executeTransaction (new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(d);
                }
            });
        }
        catch (Exception e){
            Log.d("ERROR COK", e.getMessage());
            e.printStackTrace();
        }

    }

    private void otherViewChanges(){
        if(enableEditText==false){
            ada_air.setClickable(false);
            ada_listrik.setClickable(false);
            ada_telepon.setClickable(false);
            AppUtil.disableEditTexts(cl_content);

        }
    }
}


