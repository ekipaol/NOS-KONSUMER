package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;

import android.content.Intent;
import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
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


import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.ReqKodeWilayah;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.ReqIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.pojo.AgunanTanahBangunanPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogAddress;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.AddressListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanTanahBangunan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.WilayahFlpp;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.address;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.Permission;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunan1Identifikasi extends Fragment implements Step, AddressListener, View.OnClickListener, GenericListenerOnSelect {

//VIEW BINDING
    @BindView(R.id.tf_lokasi_tanah)
    TextFieldBoxes tf_lokasi_tanah;
    @BindView(R.id.et_lokasi_tanah)
    ExtendedEditText et_lokasi_tanah;

    @BindView(R.id.tf_kodepos_agunan)
    TextFieldBoxes tf_kodepos_agunan;
    @BindView(R.id.et_kodepos_agunan)
    ExtendedEditText et_kodepos_agunan;

    @BindView(R.id.tf_kelurahan_agunan)
    TextFieldBoxes tf_kelurahan_agunan;
    @BindView(R.id.et_kelurahan_agunan)
    ExtendedEditText et_kelurahan_agunan;

    @BindView(R.id.tf_kecamatan_agunan)
    TextFieldBoxes tf_kecamatan_agunan;
    @BindView(R.id.et_kecamatan_agunan)
    ExtendedEditText et_kecamatan_agunan;

    @BindView(R.id.tf_kota_agunan)
    TextFieldBoxes tf_kota_agunan;
    @BindView(R.id.et_kota_agunan)
    ExtendedEditText et_kota_agunan;

    @BindView(R.id.tf_propinsi_agunan)
    TextFieldBoxes tf_propinsi_agunan;
    @BindView(R.id.et_propinsi_agunan)
    ExtendedEditText et_propinsi_agunan;

    @BindView(R.id.tf_batas_utara)
    TextFieldBoxes tf_batas_utara;
    @BindView(R.id.et_batas_utara)
    ExtendedEditText et_batas_utara;

    @BindView(R.id.tf_batas_selatan)
    TextFieldBoxes tf_batas_selatan;
    @BindView(R.id.et_batas_selatan)
    ExtendedEditText et_batas_selatan;

    @BindView(R.id.tf_batas_barat)
    TextFieldBoxes tf_batas_barat;
    @BindView(R.id.et_batas_barat)
    ExtendedEditText et_batas_barat;

    @BindView(R.id.tf_batas_timur)
    TextFieldBoxes tf_batas_timur;
    @BindView(R.id.et_batas_timur)
    ExtendedEditText et_batas_timur;

    @BindView(R.id.tf_kav_blok)
    TextFieldBoxes tf_kav_blok;
    @BindView(R.id.et_kavblok)
    ExtendedEditText et_kavblok;

    @BindView(R.id.tf_rt_agunan)
    TextFieldBoxes tf_rt_agunan;
    @BindView(R.id.et_rt_agunan)
    ExtendedEditText et_rt_agunan;

    @BindView(R.id.tf_rw_agunan)
    TextFieldBoxes tf_rw_agunan;
    @BindView(R.id.et_rw_agunan)
    ExtendedEditText et_rw_agunan;

    @BindView(R.id.tf_no_rumah)
    TextFieldBoxes tf_no_rumah;
    @BindView(R.id.et_no_rumah)
    ExtendedEditText et_no_rumah;

    @BindView(R.id.tf_kode_wilayah)
    TextFieldBoxes tf_kode_wilayah;
    @BindView(R.id.et_kode_wilayah)
    ExtendedEditText et_kode_wilayah;

    @BindView(R.id.tf_kelurahan_wilayah)
    TextFieldBoxes tf_kelurahan_wilayah;
    @BindView(R.id.et_kelurahan_wilayah)
    ExtendedEditText et_kelurahan_wilayah;

    @BindView(R.id.btn_kode_wilayah)
    Button btn_kode_wilayah;

    @BindView(R.id.tv_warning_kode_wilayah)
    TextView tv_warning_kode_wilayah;

    @BindView(R.id.ll_kode_wilayah)
    LinearLayout ll_kode_wilayah;
    @BindView(R.id.ll_kode_wilayah_2)
    LinearLayout ll_kode_wilayah_2;

    @BindView(R.id.btn_dom)
    Button btn_dom;

    @BindView(R.id.btn_set_loc)
    Button btn_set_loc;

    @BindView(R.id.tv_set_loc)
    TextView tvSetLoc;

    @BindView(R.id.cl_content)
    CoordinatorLayout cl_content;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    //END OF VIEW BINDING

    private String idAgunan="";
    private AgunanTanahBangunan dataAgunan;

    public static String val_LokasiTanah ="";
    public static String val_KodeposAgunan ="";
    public static String val_KelurahanAgunan ="";
    public static String val_KecamatanAgunan ="";
    public static String val_KotaAgunan ="";
    public static String val_PropinsiAgunan ="";
    public static String val_BatasUtara ="";
    public static String val_BatasSelatan ="";
    public static String val_BatasBarat ="";
    public static String val_BatasTimur ="";
    public static String val_KavBlok ="";
    public static String val_RtAgunan ="";
    public static String val_RwAgunan ="";
    public static String val_Koordinat="Lokasi Belum Diset";
    public static String val_noRumah ="";
    public static String val_kodeWilayah ="";

    //Start Empty String
    public static String val_BentukBidangTanah = "";
    public static String val_PermukaanTanah = "";
    // END Empty String

    public static int val_IdAgunan = 0;

    private String btnAddressString = "";
    String latitude = "";
    String longitude = "";

    private Realm realm;
    private Permission permission;
    private boolean enableEditText;
    private String idProgram="";
    ApiClientAdapter apiClientAdapter;
    List<MGenericModel> dropdownKelurahan=new ArrayList<>();

    public FragmentAgunan1Identifikasi() {
    }

    public FragmentAgunan1Identifikasi(String midAgunan, AgunanTanahBangunan magunanTanahBangunan) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
    }

    public FragmentAgunan1Identifikasi(String midAgunan, AgunanTanahBangunan magunanTanahBangunan,boolean enableEditText) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
        this.enableEditText=enableEditText;
    }

    public FragmentAgunan1Identifikasi(String midAgunan, AgunanTanahBangunan magunanTanahBangunan,boolean enableEditText,String idProgram) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
        this.idProgram=idProgram;
        this.enableEditText=enableEditText;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_1_identifikasi, container, false);
        ButterKnife.bind(this,view);
        realm = Realm.getDefaultInstance();
        permission = new Permission(getContext());
        apiClientAdapter=new ApiClientAdapter(getContext());
        btn_dom.setOnClickListener(this);
        btn_set_loc.setOnClickListener(this);
        btn_kode_wilayah.setOnClickListener(this);


        et_kodepos_agunan.setFocusable(false);
        et_kelurahan_agunan.setFocusable(false);
        et_kecamatan_agunan.setFocusable(false);
        et_kota_agunan.setFocusable(false);
        et_propinsi_agunan.setFocusable(false);
        et_kode_wilayah.setFocusable(false);
        et_kelurahan_wilayah.setFocusable(false);
//        if(!String.valueOf(idAgunan).equalsIgnoreCase("0")) {
//            setData();
//        }

        setData();

//        loadKodeWilayah();

        otherViewChanges();

        return view;
    }

    private void setData(){
        try {
            if(dataAgunan.getKoordinat()==null){
                val_Koordinat = "Lokasi Belum Diset";
            }
            else{
                val_Koordinat = dataAgunan.getKoordinat();
            }
            et_lokasi_tanah.setText(dataAgunan.getLokasiTanah());
            et_kodepos_agunan.setText(dataAgunan.getKodePos());
            et_kelurahan_agunan.setText(dataAgunan.getKelurahan());
            et_kecamatan_agunan.setText(dataAgunan.getKecamatan());
            et_kota_agunan.setText(dataAgunan.getKota());
            et_propinsi_agunan.setText(dataAgunan.getPropinsi());
            tvSetLoc.setText(val_Koordinat);
            et_batas_utara.setText(dataAgunan.getBatasUtaraTanah());
            et_batas_selatan.setText(dataAgunan.getBatasSelatanTanah());
            et_batas_barat.setText(dataAgunan.getBatasBaratTanah());
            et_batas_timur.setText(dataAgunan.getBatasTimurTanah());
            et_kavblok.setText(dataAgunan.getKavBlok());
            et_rt_agunan.setText(dataAgunan.getrT());
            et_rw_agunan.setText(dataAgunan.getrW());


            if(idProgram.equalsIgnoreCase("222")){
                if(dataAgunan.getKodeWilayah()==null||dataAgunan.getKodeWilayah().isEmpty()){
                    loadKodeWilayah();
                }
                else{
                    //jika kode wilayah sudah ada dari database, maka ambil nama kelurahannya dari service untuk dropdown kelurahan
                    //dengan parameter false, yang artinya gak menampilkan dialog
                    loadKelurahanDanDialog(false);

                }
            }



            et_no_rumah.setText(dataAgunan.getNoAlamat());

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
            case R.id.btn_dom:
                btnAddressString = "dom";
                openAddressDialog();
                break;
            case R.id.btn_set_loc:
                if(permission.Location())
                {
                    Intent intent = new Intent(getActivity(), MapsActivity.class);
                    startActivityForResult(intent, 1);
                }
                break;

            case R.id.btn_kode_wilayah:
                loadKelurahanDanDialog(true);
//                DialogGenericDataFromService.display(getFragmentManager(),tf_kode_wilayah.getLabelText(),dropdownKelurahan,this);
                break;
        }
    }

    private void openAddressDialog(){
        DialogAddress.display(getFragmentManager(), this);
    }

    @Override
    public void onAddressSelect(address data) {
        if (btnAddressString.equalsIgnoreCase("dom")){
            et_propinsi_agunan.setText(data.getProvinsi());
            et_kota_agunan.setText(data.getKota());
            et_kecamatan_agunan.setText(data.getKecamatan());
            et_kelurahan_agunan.setText(data.getKelurahan());
            et_kodepos_agunan.setText(data.getKodepos());
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data != null) {
            if (data.hasExtra("latitude")) {
                latitude = data.getStringExtra("latitude");
            }
            if (data.hasExtra("longitude")) {
                longitude = data.getStringExtra("longitude");
            }

            Toast.makeText(getContext(), "latitude : "+latitude+"\nlongitude : "+longitude, Toast.LENGTH_SHORT).show();
            if (!(latitude.isEmpty() || latitude.equalsIgnoreCase("") || latitude.equalsIgnoreCase("0"))) {
                tvSetLoc.setText(latitude + "," + longitude);
                val_Koordinat = latitude + "," + longitude;
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
        tvSetLoc.setText(val_Koordinat);
    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }

    private VerificationError validateForm(){
//        Log.d("KOORDINAT", val_Koordinat);
        if (et_lokasi_tanah.getText().toString().isEmpty() || et_lokasi_tanah.getText().toString().equalsIgnoreCase("")){
            tf_lokasi_tanah.setError("Format "+ tf_lokasi_tanah.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_lokasi_tanah.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_kodepos_agunan.getText().toString().isEmpty() || et_kodepos_agunan.getText().toString().equalsIgnoreCase("")){
            tf_kodepos_agunan.setError("Format "+ tf_kodepos_agunan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kodepos_agunan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kelurahan_agunan.getText().toString().isEmpty() || et_kelurahan_agunan.getText().toString().equalsIgnoreCase("")){
            tf_kelurahan_agunan.setError("Format "+ tf_kelurahan_agunan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kelurahan_agunan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kecamatan_agunan.getText().toString().isEmpty() || et_kecamatan_agunan.getText().toString().equalsIgnoreCase("")){
            tf_kecamatan_agunan.setError("Format "+ tf_kecamatan_agunan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kecamatan_agunan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kota_agunan.getText().toString().isEmpty() || et_kota_agunan.getText().toString().equalsIgnoreCase("")){
            tf_kota_agunan.setError("Format "+ tf_kota_agunan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kota_agunan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_propinsi_agunan.getText().toString().isEmpty() || et_propinsi_agunan.getText().toString().equalsIgnoreCase("")){
            tf_propinsi_agunan.setError("Format "+ tf_propinsi_agunan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_propinsi_agunan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_batas_utara.getText().toString().isEmpty() || et_batas_utara.getText().toString().equalsIgnoreCase("")){
            tf_batas_utara.setError("Format "+ tf_batas_utara.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_batas_utara.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_batas_selatan.getText().toString().isEmpty() || et_batas_selatan.getText().toString().equalsIgnoreCase("")){
            tf_batas_selatan.setError("Format "+ tf_batas_selatan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_batas_selatan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_batas_barat.getText().toString().isEmpty() || et_batas_barat.getText().toString().equalsIgnoreCase("")){
            tf_batas_barat.setError("Format "+ tf_batas_barat.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_batas_barat.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_batas_timur.getText().toString().isEmpty() || et_batas_timur.getText().toString().equalsIgnoreCase("")){
            tf_batas_timur.setError("Format "+ tf_batas_timur.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_batas_timur.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kavblok.getText().toString().isEmpty() || et_kavblok.getText().toString().equalsIgnoreCase("")){
            tf_kav_blok.setError("Format "+ tf_kav_blok.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kav_blok.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_rt_agunan.getText().toString().isEmpty() || et_rt_agunan.getText().toString().equalsIgnoreCase("")){
            tf_rt_agunan.setError("Format "+ tf_rt_agunan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_rt_agunan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_rw_agunan.getText().toString().isEmpty() || et_rw_agunan.getText().toString().equalsIgnoreCase("")){
            tf_rw_agunan.setError("Format "+ tf_rw_agunan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_rw_agunan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (val_Koordinat.equalsIgnoreCase("Lokasi Belum Diset") || val_Koordinat.equalsIgnoreCase("")) {
            return new VerificationError("Lokasi Harus Diset");
        }

        //validasi khusus flpp
        else if(idProgram.equalsIgnoreCase("222")&&(et_kode_wilayah.getText().toString().isEmpty() || et_kode_wilayah.getText().toString().length()<10)){
            tf_kode_wilayah.setError("Pilih kode wilayah kelurahan terlebih dahulu", true);
            return new VerificationError("Format "+ tf_kode_wilayah.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if(idProgram.equalsIgnoreCase("222")&&et_no_rumah.getText().toString().isEmpty() ){
            tf_no_rumah.setError("Isi nomor rumah terlebih dahulu", true);
            return new VerificationError("Format "+ tf_no_rumah.getLabelText()+" "+getString(R.string.title_validate_field));

        }

        else {
            setDataOnListerner();
            return null;
        }
    }

    private void loadKelurahanDanDialog(boolean tampilkanDialog) {
        loading.setVisibility(View.VISIBLE);
        ReqKodeWilayah req=new ReqKodeWilayah();

        //real kode wilayah

        if(tampilkanDialog){
            req.setKodeWilayah(et_kode_wilayah.getText().toString().trim().substring(0,6));
        }
        else{
            req.setKodeWilayah(dataAgunan.getKodeWilayah());
        }


        //pantekan kode wilayah
//        req.setKodeWilayah("320701");
//        Toast.makeText(getContext(), "Ada pantekan kode wilayah ya", Toast.LENGTH_SHORT).show();
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listKelurahanFlpp(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<WilayahFlpp>>() {}.getType();


                            //dapet data kelurahan
                            List<WilayahFlpp> listKelurahan = gson.fromJson(listDataString, type);

                            dropdownKelurahan.clear();


                            //mapping data kelurahan dalam bentuk dropdown
                            for (int i = 0; i <listKelurahan.size() ; i++) {
                                dropdownKelurahan.add(new MGenericModel(listKelurahan.get(i).getID_KODE_WILAYAH(),listKelurahan.get(i).getNAMA_WILAYAH()));
                            }

                            loading.setVisibility(View.GONE);

                            if(tampilkanDialog){
                                //tampilin dialog setelah selesai proses
                                DialogGenericDataFromService.display(getFragmentManager(),tf_kode_wilayah.getLabelText(),dropdownKelurahan,FragmentAgunan1Identifikasi.this);
                            }
                            else{
                                et_kelurahan_wilayah.setText(dropdownKelurahan.get(0).getDESC());
                                et_kode_wilayah.setText(dataAgunan.getKodeWilayah());
                                tv_warning_kode_wilayah.setVisibility(View.GONE);
                                btn_kode_wilayah.setVisibility(View.INVISIBLE);
                            }





                        } else {
//                            finish();
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    loading.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                finish();
                loading.setVisibility(View.GONE);
                t.printStackTrace();
                Toast.makeText(getContext(), "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void loadKodeWilayah() {
        loading.setVisibility(View.VISIBLE);
        ReqIdAplikasi req=new ReqIdAplikasi();


        req.setIdAplikasi(AppUtil.parseIntWithDefault(AgunanTanahBangunanInputActivity.idAplikasi, 0));
//        Toast.makeText(getContext(), "Ada pantekan kode wilayah ya", Toast.LENGTH_SHORT).show();
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryKodeWilayah(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {

                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<WilayahFlpp>() {}.getType();


                            //dapet data WILAYAH
                            WilayahFlpp dataWilayah = gson.fromJson(listDataString, type);

                                et_kode_wilayah.setText(dataWilayah.getID_KODE_WILAYAH());

                                if(dataWilayah.getID_KODE_WILAYAH().length()<10){
                                    tv_warning_kode_wilayah.setVisibility(View.VISIBLE);

                                    et_kelurahan_wilayah.setText("");
                                    btn_kode_wilayah.setVisibility(View.VISIBLE);
                                }
                                else{
                                    tv_warning_kode_wilayah.setVisibility(View.GONE);
                                    et_kelurahan_wilayah.setText(dataWilayah.getNAMA_WILAYAH());
                                    btn_kode_wilayah.setVisibility(View.INVISIBLE);
                                }







                            //mapping data kelurahan dalam bentuk dropdown

                            loading.setVisibility(View.GONE);





                        } else {
//                            finish();
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
//                        finish();
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    loading.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
//                finish();
                loading.setVisibility(View.GONE);
                t.printStackTrace();
                Toast.makeText(getContext(), "Gagal Terhubung Ke Server", Toast.LENGTH_SHORT).show();
                AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void setDataOnListerner(){
        try{
            val_LokasiTanah = (et_lokasi_tanah.getText().toString().trim());
            val_KodeposAgunan = (et_kodepos_agunan.getText().toString().trim());
            val_KelurahanAgunan = (et_kelurahan_agunan.getText().toString().trim());
            val_KecamatanAgunan = (et_kecamatan_agunan.getText().toString().trim());
            val_KotaAgunan = (et_kota_agunan.getText().toString().trim());
            val_PropinsiAgunan = (et_propinsi_agunan.getText().toString().trim());
            val_BatasUtara = (et_batas_utara.getText().toString().trim());
            val_BatasSelatan = (et_batas_selatan.getText().toString().trim());
            val_BatasBarat = (et_batas_barat.getText().toString().trim());
            val_BatasTimur = (et_batas_timur.getText().toString().trim());
            val_KavBlok = (et_kavblok.getText().toString().trim());
            val_RtAgunan = (et_rt_agunan.getText().toString().trim());
            val_RwAgunan = (et_rw_agunan.getText().toString().trim());
            val_noRumah=(et_no_rumah.getText().toString().trim());


            final AgunanTanahBangunanPojo d = new AgunanTanahBangunanPojo();
            d.setUuid(AgunanTanahBangunanInputActivity.uuid);
            d.setIdAgunan(AppUtil.parseIntWithDefault(AgunanTanahBangunanInputActivity.idAgunan, 0));
            d.setIdApl(AppUtil.parseIntWithDefault(AgunanTanahBangunanInputActivity.idAplikasi, 0));
            d.setCif(AgunanTanahBangunanInputActivity.cif);

            d.setLokasiTanah(val_LokasiTanah);
            d.setKodePos(val_KodeposAgunan);
            d.setKelurahan(val_KelurahanAgunan);
            d.setKecamatan(val_KecamatanAgunan);
            d.setKota(val_KotaAgunan);
            d.setPropinsi(val_PropinsiAgunan);
            d.setBatasUtaraTanah(val_BatasUtara);
            d.setBatasSelatanTanah(val_BatasSelatan);
            d.setBatasBaratTanah(val_BatasBarat);
            d.setBatasTimurTanah(val_BatasTimur);
            d.setKavBlok(val_KavBlok);
            d.setRT(val_RtAgunan);
            d.setRW(val_RwAgunan);
            d.setKoordinat(val_Koordinat);
            d.setBentukTanah(val_BentukBidangTanah);
            d.setPermukaanTanah(val_PermukaanTanah);
            d.setNoAlamat(val_noRumah);
            d.setKodeWilayah(val_kodeWilayah);

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

    private void otherViewChanges(){
        if(enableEditText==false){
            btn_set_loc.setVisibility(View.GONE);
            btn_dom.setVisibility(View.GONE);
        }

        if(idProgram.equalsIgnoreCase("222")){

            //kode wilayah harus 10 digit (sudah termasuk kelurahan) di comment dlu
//            if(dataAgunan.getKodeWilayah().length()<10){
//                tv_warning_kode_wilayah.setVisibility(View.VISIBLE);
//            }

            ll_kode_wilayah.setVisibility(View.VISIBLE);
            ll_kode_wilayah_2.setVisibility(View.VISIBLE);
            tf_no_rumah.setVisibility(View.VISIBLE);
        }
        else{
            tv_warning_kode_wilayah.setVisibility(View.GONE);
            ll_kode_wilayah.setVisibility(View.GONE);
            ll_kode_wilayah_2.setVisibility(View.GONE);
            tf_no_rumah.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSelect(String title, MGenericModel data) {
        if(title.equalsIgnoreCase(tf_kode_wilayah.getLabelText())){
            et_kode_wilayah.setText(data.getID());
            et_kelurahan_wilayah.setText(data.getDESC());
            val_kodeWilayah=data.getID();
        }
    }


}

