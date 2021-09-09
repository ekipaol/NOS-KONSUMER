package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.InputType;
import android.widget.ScrollView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.database.pojo.AgunanTanahBangunanPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanTanahBangunan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunan5Lingkungan extends Fragment implements Step, KeyValueListener, View.OnClickListener, View.OnFocusChangeListener {

    @BindView(R.id.tf_peruntukan_lokasi)
    TextFieldBoxes tf_peruntukan_lokasi;
    @BindView(R.id.et_peruntukan_lokasi)
    ExtendedEditText et_peruntukan_lokasi;

    @BindView(R.id.tf_akses_jalan)
    TextFieldBoxes tf_akses_jalan;
    @BindView(R.id.et_akses_jalan)
    ExtendedEditText et_akses_jalan;

    @BindView(R.id.tf_fasilitas_sosial)
    TextFieldBoxes tf_fasilitas_sosial;
    @BindView(R.id.et_fasilitas_sosial)
    ExtendedEditText et_fasilitas_sosial;

    @BindView(R.id.tf_lebar_jalan_depan)
    TextFieldBoxes tf_lebar_jalan_depan;
    @BindView(R.id.et_lebar_jalan_depan)
    ExtendedEditText et_lebar_jalan_depan;

    @BindView(R.id.cl_content)
    ScrollView cl_content;

    private AgunanTanahBangunan dataAgunan;
    private String idAgunan="";

    public static String val_PeruntukanLokasi ="";
    public static String val_AksesJalan ="";
    public static String val_FasilitasSosial ="";
    public static String val_LebarJalanDepan ="";

    //BEGIN EMPTY STRING
    public static String val_PerkembanganLingkungan ="";
    public static String val_Kepadatan ="";
    public static String val_AksesCapai ="";

    private Realm realm;
    private boolean enableEditText;

    public FragmentAgunan5Lingkungan() {
    }

    public FragmentAgunan5Lingkungan(String midAgunan, AgunanTanahBangunan magunanTanahBangunan) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
    }

    public FragmentAgunan5Lingkungan(String midAgunan, AgunanTanahBangunan magunanTanahBangunan,boolean enableEditText) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
        this.enableEditText=enableEditText;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.agunan_5_lingkungan, container, false);

        try {
            ButterKnife.bind(this, view);
            realm = Realm.getDefaultInstance();
            onSelectDialog();
//            if(!idAgunan.equalsIgnoreCase("0")) {
//                setData();
//            }
            setData();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return view;
    }

    private void onSelectDialog(){

        et_peruntukan_lokasi.setFocusable(false);
        et_peruntukan_lokasi.setInputType(InputType.TYPE_NULL);
        et_peruntukan_lokasi.setOnClickListener(this);
        et_peruntukan_lokasi.setOnFocusChangeListener(this);
        tf_peruntukan_lokasi.setOnClickListener(this);
        tf_peruntukan_lokasi.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_peruntukan_lokasi.getLabelText().toString().trim());
            }
        });

        et_akses_jalan.setFocusable(false);
        et_akses_jalan.setInputType(InputType.TYPE_NULL);
        et_akses_jalan.setOnClickListener(this);
        et_akses_jalan.setOnFocusChangeListener(this);
        tf_akses_jalan.setOnClickListener(this);
        tf_akses_jalan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_akses_jalan.getLabelText().toString().trim());
            }
        });

        et_fasilitas_sosial.setFocusable(false);
        et_fasilitas_sosial.setInputType(InputType.TYPE_NULL);
        et_fasilitas_sosial.setOnClickListener(this);
        et_fasilitas_sosial.setOnFocusChangeListener(this);
        tf_fasilitas_sosial.setOnClickListener(this);
        tf_fasilitas_sosial.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_fasilitas_sosial.getLabelText().toString().trim());
            }
        });
    }

    private void openKeyValueDialog(String title){
        DialogKeyValue.display(getFragmentManager(), title, this);
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase("Peruntukan Lokasi")){
            et_peruntukan_lokasi.setText(data.getName());
        }

        else if (title.equalsIgnoreCase("Akses Jalan Objek")){
            et_akses_jalan.setText(data.getName());
        }
        else if (title.equalsIgnoreCase("Fasilitas Sosial")){
            et_fasilitas_sosial.setText(data.getName());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_peruntukan_lokasi:
            case R.id.tf_peruntukan_lokasi:
                openKeyValueDialog(tf_peruntukan_lokasi.getLabelText().toString().trim());
                break;

            case R.id.et_akses_jalan:
            case R.id.tf_akses_jalan:
                openKeyValueDialog(tf_akses_jalan.getLabelText().toString().trim());
                break;

            case R.id.et_fasilitas_sosial:
            case R.id.tf_fasilitas_sosial:
                openKeyValueDialog(tf_fasilitas_sosial.getLabelText().toString().trim());
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            switch (v.getId()){
                case R.id.et_peruntukan_lokasi:
                case R.id.tf_peruntukan_lokasi:
                    openKeyValueDialog(tf_peruntukan_lokasi.getLabelText().toString().trim());
                    break;

                case R.id.et_akses_jalan:
                case R.id.tf_akses_jalan:
                    openKeyValueDialog(tf_akses_jalan.getLabelText().toString().trim());
                    break;

                case R.id.et_fasilitas_sosial:
                case R.id.tf_fasilitas_sosial:
                    openKeyValueDialog(tf_fasilitas_sosial.getLabelText().toString().trim());
                    break;
            }
        }
    }

    private void setData(){
        try {
            et_peruntukan_lokasi.setText(dataAgunan.getPeruntukanLokasi());
            et_akses_jalan.setText(dataAgunan.getAksesJalanObjek());
            et_fasilitas_sosial.setText(dataAgunan.getFasilitasSosial());
            et_lebar_jalan_depan.setText(dataAgunan.getLebarJalanDepan());

            if(enableEditText==false){
                AppUtil.disableEditTexts(cl_content);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
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

    private VerificationError validateForm(){
        if (et_peruntukan_lokasi.getText().toString().isEmpty() || et_peruntukan_lokasi.getText().toString().equalsIgnoreCase("Pilih")){
            tf_peruntukan_lokasi.setError("Format "+ tf_peruntukan_lokasi.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_peruntukan_lokasi.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_akses_jalan.getText().toString().isEmpty() || et_akses_jalan.getText().toString().equalsIgnoreCase("Pilih")){
            tf_akses_jalan.setError("Format "+ tf_akses_jalan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_akses_jalan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_fasilitas_sosial.getText().toString().isEmpty() || et_fasilitas_sosial.getText().toString().equalsIgnoreCase("Pilih")){
            tf_fasilitas_sosial.setError("Format "+ tf_fasilitas_sosial.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_fasilitas_sosial.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_lebar_jalan_depan.getText().toString().isEmpty() || et_lebar_jalan_depan.getText().toString().equalsIgnoreCase("")){
            tf_lebar_jalan_depan.setError("Format "+ tf_lebar_jalan_depan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_lebar_jalan_depan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else {
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner(){

        try {
            val_PeruntukanLokasi = (et_peruntukan_lokasi.getText().toString().trim());
            val_AksesJalan = (et_akses_jalan.getText().toString().trim());
            val_FasilitasSosial = (et_fasilitas_sosial.getText().toString().trim());
            val_LebarJalanDepan = (et_lebar_jalan_depan.getText().toString().trim());

            final AgunanTanahBangunanPojo d = new AgunanTanahBangunanPojo();
            d.setPeruntukanLokasi(val_PeruntukanLokasi);
            d.setAksesJalanObjek(val_AksesJalan);
            d.setFasilitasSosial(val_FasilitasSosial);
            d.setLebarJalanDepan(AppUtil.parseDoubleWithDefault(val_LebarJalanDepan, 0.0));
            d.setPerkembanganLingkungan(val_PerkembanganLingkungan);
            d.setKepadatan(val_Kepadatan);
            d.setAksesPencapaian(val_AksesCapai);

            AgunanTanahBangunanPojo dataR = realm.where(AgunanTanahBangunanPojo.class).equalTo("uuid", AgunanTanahBangunanInputActivity.uuid).findFirst();

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



            realm.executeTransaction (new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(d);

                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}


