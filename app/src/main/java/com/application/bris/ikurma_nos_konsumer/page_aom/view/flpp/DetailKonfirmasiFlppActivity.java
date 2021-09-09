package com.application.bris.ikurma_nos_konsumer.page_aom.view.flpp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.flpp.ReqFotoFlpp;
import com.application.bris.ikurma_nos_konsumer.api.model.request.flpp.ReqLoginSikasep;
import com.application.bris.ikurma_nos_konsumer.api.model.request.flpp.ReqSimpanKonfirmasiFlpp;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.Flpp;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.FotoFlpp;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class DetailKonfirmasiFlppActivity extends AppCompatActivity implements   View.OnClickListener, GenericListenerOnSelect, ConfirmListener {

    @BindView(R.id.tf_nama_nasabah)
    TextFieldBoxes tf_nama_nasabah;
    @BindView(R.id.et_nama_nasabah)
    EditText et_nama_nasabah;
    @BindView(R.id.tf_ktp_nasabah)
    TextFieldBoxes tf_ktp_nasabah;
    @BindView(R.id.et_ktp_nasabah)
    EditText et_ktp_nasabah;
    @BindView(R.id.tf_npwp_nasabah)
    TextFieldBoxes tf_npwp_nasabah;
    @BindView(R.id.et_npwp_nasabah)
    EditText et_npwp_nasabah;
    @BindView(R.id.tf_nama_pengembang)
    TextFieldBoxes tf_nama_pengembang;
    @BindView(R.id.et_nama_pengembang)
    EditText et_nama_pengembang;
    @BindView(R.id.tf_nama_perumahan)
    TextFieldBoxes tf_nama_perumahan;
    @BindView(R.id.et_nama_perumahan)
    EditText et_nama_perumahan;

    @BindView(R.id.tf_verifikasi_status)
    TextFieldBoxes tf_verifikasi_status;
    @BindView(R.id.et_verifikasi_status)
    EditText et_verifikasi_status;
    @BindView(R.id.tf_verifikasi_foto_ktp)
    TextFieldBoxes tf_verifikasi_foto_ktp;
    @BindView(R.id.et_verifikasi_foto_ktp)
    EditText et_verifikasi_foto_ktp;
    @BindView(R.id.tf_verifikasi_foto_selfie)
    TextFieldBoxes tf_verifikasi_foto_selfie;
    @BindView(R.id.et_verifikasi_foto_selfie)
    EditText et_verifikasi_foto_selfie;
    @BindView(R.id.tf_kolektabilitas)
    TextFieldBoxes tf_kolektabilitas;
    @BindView(R.id.et_kolektabilitas)
    EditText et_kolektabilitas;
    @BindView(R.id.tf_alasan_batal)
    TextFieldBoxes tf_alasan_batal;
    @BindView(R.id.et_alasan_batal)
    EditText et_alasan_batal;
    @BindView(R.id.tf_catatan_ao)
    TextFieldBoxes tf_catatan_ao;
    @BindView(R.id.et_catatan_ao)
    EditText et_catatan_ao;

    @BindView(R.id.img_ktp)
    ImageView img_ktp;

    @BindView(R.id.img_selfie)
    ImageView img_selfie;

    @BindView(R.id.img_qr)
    ImageView img_qr;



    @BindView(R.id.tv_page_title)
    TextView tv_page_title;

    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.btn_send)
    Button btn_send;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    @BindView(R.id.sw_tembak_sikasep)
    Switch sw_tembak_sikasep;


    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    Flpp dataFlpp;
    List<MGenericModel> dataDropdownVerifikasiFoto=new ArrayList<>();
    List<MGenericModel> dataDropdownKolektabilitas=new ArrayList<>();
    List<MGenericModel> dataDropdownStatus=new ArrayList<>();

    String batchId="";

    private boolean validasiOk=true;

    private FotoFlpp dataFoto;

    private String val_verifikasi_ktp="";
    private String val_verifikasi_selfie="";
    private String val_verifikasi_kolektabilitas="";
    private String val_verifikasi_status="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_konfirmasi_flpp);

        ButterKnife.bind(this);

        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);

        tv_page_title.setText("Detail Konfirmasi Nasabah");

        btn_back.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        img_ktp.setOnClickListener(this);
        img_qr.setOnClickListener(this);
        img_selfie.setOnClickListener(this);

        dataFlpp=(Flpp)getIntent().getSerializableExtra("dataKonfirmasi");

        setData();

        loadDataFoto();

        otherViewChanges();
//        loginSikasep();


    }

    private void setData(){
        et_nama_nasabah.setInputType(InputType.TYPE_NULL);
        et_nama_nasabah.setFocusable(false);

        et_ktp_nasabah.setInputType(InputType.TYPE_NULL);
        et_ktp_nasabah.setFocusable(false);

        et_npwp_nasabah.setInputType(InputType.TYPE_NULL);
        et_npwp_nasabah.setFocusable(false);

        et_nama_pengembang.setInputType(InputType.TYPE_NULL);
        et_nama_pengembang.setFocusable(false);

        et_nama_perumahan.setInputType(InputType.TYPE_NULL);
        et_nama_perumahan.setFocusable(false);

        et_verifikasi_foto_ktp.setFocusable(false);
        et_verifikasi_foto_selfie.setFocusable(false);
        et_kolektabilitas.setFocusable(false);
        et_verifikasi_status.setFocusable(false);



        et_nama_nasabah.setText(dataFlpp.getNamaDebitur());
        et_ktp_nasabah.setText(dataFlpp.getKtpDebitur());

        et_npwp_nasabah.setText(dataFlpp.getNpwpDebitur());
        et_nama_pengembang.setText(dataFlpp.getNamaPengembang());
        et_nama_perumahan.setText(dataFlpp.getNamaPerumahan());

        setParameterDropdown();

        //on click listeners
        tf_verifikasi_foto_ktp.setOnClickListener(this);
        et_verifikasi_foto_ktp.setOnClickListener(this);
        tf_verifikasi_foto_ktp.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),tf_verifikasi_foto_ktp.getLabelText(),dataDropdownVerifikasiFoto,DetailKonfirmasiFlppActivity.this);
            }
        });

        tf_kolektabilitas.setOnClickListener(this);
        et_kolektabilitas.setOnClickListener(this);
        tf_kolektabilitas.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),tf_kolektabilitas.getLabelText(),dataDropdownKolektabilitas,DetailKonfirmasiFlppActivity.this);
            }
        });

        tf_verifikasi_foto_selfie.setOnClickListener(this);
        et_verifikasi_foto_selfie.setOnClickListener(this);
        tf_verifikasi_foto_selfie.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),tf_verifikasi_foto_selfie.getLabelText(),dataDropdownVerifikasiFoto,DetailKonfirmasiFlppActivity.this);
            }
        });

        tf_verifikasi_status.setOnClickListener(this);
        et_verifikasi_status.setOnClickListener(this);
        tf_verifikasi_status.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),tf_verifikasi_status.getLabelText(),dataDropdownStatus,DetailKonfirmasiFlppActivity.this);
            }
        });




    }

    private void setParameterDropdown(){
        //dropdown verifikasi foto (KTP dan Selfie)
        dataDropdownVerifikasiFoto.add(new MGenericModel("0","Sesuai"));
        dataDropdownVerifikasiFoto.add(new MGenericModel("1","Tidak Sesuai"));

        //dropdown  kolektabilitas
        dataDropdownKolektabilitas.add(new MGenericModel("K1","Kolektabilitas 1"));
        dataDropdownKolektabilitas.add(new MGenericModel("K2","Kolektabilitas 2"));
        dataDropdownKolektabilitas.add(new MGenericModel("K3","Kolektabilitas 3"));
        dataDropdownKolektabilitas.add(new MGenericModel("K4","Kolektabilitas 4"));
        dataDropdownKolektabilitas.add(new MGenericModel("K5","Kolektabilitas 5"));

        //dropdown  status
        dataDropdownStatus.add(new MGenericModel("0","Lanjut Verifikasi"));
        dataDropdownStatus.add(new MGenericModel("1","Batal"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                CustomDialog.DialogBackpress(this);
                break;

            case R.id.btn_send:

                if(validateForm()){
                    simpanKonfirmasi();
//                    Toast.makeText(this, "yay kamu lolos validasi dan berhasil menyimpan", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.tf_verifikasi_foto_ktp:

            case R.id.et_verifikasi_foto_ktp:
                DialogGenericDataFromService.display(getSupportFragmentManager(),tf_verifikasi_foto_ktp.getLabelText(),dataDropdownVerifikasiFoto,DetailKonfirmasiFlppActivity.this);

                break;

            case R.id.tf_verifikasi_foto_selfie:

            case R.id.et_verifikasi_foto_selfie:
                DialogGenericDataFromService.display(getSupportFragmentManager(),tf_verifikasi_foto_selfie.getLabelText(),dataDropdownVerifikasiFoto,DetailKonfirmasiFlppActivity.this);

                break;

            case R.id.tf_kolektabilitas:

            case R.id.et_kolektabilitas:
                DialogGenericDataFromService.display(getSupportFragmentManager(),tf_kolektabilitas.getLabelText(),dataDropdownKolektabilitas,DetailKonfirmasiFlppActivity.this);

                break;

            case R.id.tf_verifikasi_status:

            case R.id.et_verifikasi_status:
                DialogGenericDataFromService.display(getSupportFragmentManager(),tf_verifikasi_status.getLabelText(),dataDropdownStatus,DetailKonfirmasiFlppActivity.this);

                break;

            case R.id.img_ktp:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)img_ktp.getDrawable()).getSourceBitmap());
                break;

            case R.id.img_qr:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)img_qr.getDrawable()).getSourceBitmap());
                break;

            case R.id.img_selfie:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)img_selfie.getDrawable()).getSourceBitmap());
                break;

        }
    }

    private boolean validateForm(){

        //reset validation parameter everytime click send
        validasiOk=true;

        subvalidateField(et_verifikasi_foto_ktp,tf_verifikasi_foto_ktp);
        subvalidateField(et_verifikasi_foto_selfie,tf_verifikasi_foto_selfie);
        subvalidateField(et_kolektabilitas,tf_kolektabilitas);
        subvalidateField(et_catatan_ao,tf_catatan_ao);
        subvalidateField(et_verifikasi_status,tf_verifikasi_status);

        if(et_verifikasi_status.getText().toString().equalsIgnoreCase("batal")){
            subvalidateField(et_alasan_batal,tf_alasan_batal);
        }

        //logika validasi :
        //semua field akan di cek isinya, jika ada yang minimal 1 yang kosong, maka akan mengubah nilai validasiOk menjadi false
        //nilai validasiOk hanya akan direset menjadi true jika user klik tombol simpan,
        //jadi di validasi awal, seluruh field yang kosong/belum diisi akan terhighlight error

        if(validasiOk){
            return true;

        }
        else{
            return false;
        }
    }

    private void subvalidateField(EditText editText,TextFieldBoxes textFieldBoxes){

        if(editText.getText().toString().isEmpty()||editText.getText().toString().equalsIgnoreCase("pilih")){
            textFieldBoxes.setError("Harap isi "+textFieldBoxes.getLabelText(),true);
            AppUtil.notiferror(DetailKonfirmasiFlppActivity.this, findViewById(android.R.id.content), "Harap isi "+textFieldBoxes.getLabelText());
            validasiOk =false;
        }

    }

    @Override
    public void onSelect(String title, MGenericModel data) {

        if(title.equalsIgnoreCase(tf_verifikasi_foto_ktp.getLabelText())){
            et_verifikasi_foto_ktp.setText(data.getDESC());
            val_verifikasi_ktp=data.getID();
        }
        else if(title.equalsIgnoreCase(tf_verifikasi_foto_selfie.getLabelText())){
            et_verifikasi_foto_selfie.setText(data.getDESC());
            val_verifikasi_selfie=data.getID();
        }
        else if(title.equalsIgnoreCase(tf_kolektabilitas.getLabelText())){
            et_kolektabilitas.setText(data.getDESC());
            val_verifikasi_kolektabilitas=data.getID();
        }
        else if(title.equalsIgnoreCase(tf_verifikasi_status.getLabelText())){
            et_verifikasi_status.setText(data.getDESC());
            val_verifikasi_status=data.getID();
            if(data.getDESC().equalsIgnoreCase("batal")){
                tf_alasan_batal.setVisibility(View.VISIBLE);
            }
            else{
                tf_alasan_batal.setVisibility(View.GONE);
            }
        }

    }

    public void loadDataFoto(){
        loading.setVisibility(View.VISIBLE);
        ReqFotoFlpp req = new ReqFotoFlpp();
        
        //pantekan
        req.setBatchId(batchId);
        req.setKodeBank("422");
        req.setKtpDebitur(dataFlpp.getKtpDebitur());


//        Toast.makeText(this, "ada pantekan parameter ketika get foto", Toast.LENGTH_SHORT).show();
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().getFotoFlpp(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {


                try {
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<FotoFlpp>() {}.getType();
                            dataFoto = gson.fromJson(listDataString, type);

                            //set foto dari sikasep
                            AppUtil.convertBase64ToImage(dataFoto.getFoto_ktp(),img_ktp);
                            AppUtil.convertBase64ToImage(dataFoto.getFoto_wajah(),img_selfie);
                            AppUtil.convertBase64ToImage(dataFoto.getQrcode(),img_qr);

                            loading.setVisibility(View.GONE);


                        }
                        else if(response.body().getStatus().equalsIgnoreCase("98")){

                            Toast.makeText(DetailKonfirmasiFlppActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<FotoFlpp>() {}.getType();
                           FotoFlpp dataFoto2 = gson.fromJson(listDataString, type);
                            loading.setVisibility(View.GONE);
//                            AppUtil.notiferror(DetailKonfirmasiFlppActivity.this, findViewById(android.R.id.content), dataFoto2.getErr_desc());
                            CustomDialog.DialogError(DetailKonfirmasiFlppActivity .this, "Gagal!",  "Pesan SIKASEP : "+dataFoto2.getErr_desc(), DetailKonfirmasiFlppActivity.this);

                        }
                    }
                    else{
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DetailKonfirmasiFlppActivity.this, findViewById(android.R.id.content), error.getMessage());
                        Toast.makeText(DetailKonfirmasiFlppActivity.this, "Terjadi Kesalahan : "+error.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                catch (Exception e){
                    loading.setVisibility(View.GONE);
                    e.printStackTrace();
                    Toast.makeText(DetailKonfirmasiFlppActivity.this, getString(R.string.txt_connection_failure), Toast.LENGTH_SHORT).show();
                    finish();


                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Toast.makeText(DetailKonfirmasiFlppActivity.this, getString(R.string.txt_connection_failure), Toast.LENGTH_SHORT).show();
            finish();
            }
        });
    }

    public void loginSikasep(){
        loading.setVisibility(View.VISIBLE);
        Log.d("welehweleh1","woy bangsyat");
        ReqLoginSikasep req = new ReqLoginSikasep();

        //pantekan
        req.setKodeBank("422");
        req.setPassword("TES_BRIS");

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().loginSikasep(req);
        call.enqueue(new Callback<ParseResponse>() {

            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {


                try {

                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                             batchId = response.body().getData().get("token").toString().replace("\"","");


                           loadDataFoto();

                        }
                        else{

                            AppUtil.notiferror(DetailKonfirmasiFlppActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{

                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DetailKonfirmasiFlppActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){

                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(DetailKonfirmasiFlppActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));




            }
        });
    }

    public void simpanKonfirmasi(){
        loading.setVisibility(View.VISIBLE);
        ReqSimpanKonfirmasiFlpp req = new ReqSimpanKonfirmasiFlpp();

        //dikirim untuk sikasep
        req.setKodeBank("422");
        req.setKtpDebitur(et_ktp_nasabah.getText().toString());
        req.setKonfirmasiFotoKtp(val_verifikasi_ktp);
        req.setKonfirmasiFotoSelfieKtp(val_verifikasi_selfie);
        req.setKolektabilitas(val_verifikasi_kolektabilitas);
        req.setKonfirmasiStatus(val_verifikasi_status);

        if (val_verifikasi_status.equalsIgnoreCase("0")){
            req.setKonfirmasiAlasan(et_catatan_ao.getText().toString());
        }
        else{
            req.setKonfirmasiAlasan(et_alasan_batal.getText().toString());
        }

        //dikirim untuk pipeline

        req.setUid(Integer.toString(appPreferences.getUid()));
        req.setId(0);
        req.setJenisProduk("KPR");
        req.setLokasiGps("0.0 0.0");
        req.setSegmen("KONSUMER");
        req.setTenor(12); //pantek tenor 12 bulan, karena minimal segitu di service pipeline
        req.setNamaNasabah(dataFlpp.getNamaDebitur());
        req.setGimmick(222); //gimmick dipantek karena ini flpp
        req.setNoKtp(et_ktp_nasabah.getText().toString());
        req.setNoHp(dataFlpp.getNoPonsel());
        req.setFidPhoto(0);//pantek id photo 0 dulu, nanti langsung upload aja ke tabel brisi
        req.setRencanaPlafond(0l);
        req.setJenisKPR("Karyawan / PNS Program FLPP");//flpp hanya support karyawan pns flpp
        req.setFidTujuanPenggunaan(25);//pantekan tujuan penggunaan
        req.setDescTujuanPenggunaan(" ");
        req.setJenisUsaha("9999");
        req.setJenisTindak("VISIT");
        req.setTindakLanjut("Konfirmasi melalui halaman FLPP");

        if(appPreferences.getNama().equalsIgnoreCase("developer")&&!sw_tembak_sikasep.isChecked()){
            //kalo lagi testing service  diisi 1, kalo lagi prod dibikin 0 atau gausah diisi yach xixixi
            req.setIsTesting("1");
            Toast.makeText(this, "melempar parameter testing true, ke service followup flpp", Toast.LENGTH_SHORT).show();
        }
        else{
            req.setIsTesting("0");
        }

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().simpanKonfirmasiFlpp(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                try {
                    if(response.isSuccessful()){


                        if(response.body().getStatus().equalsIgnoreCase("00")){
//                            String keterangan = response.body().getData().get("KETERANGAN").toString();
                            if(val_verifikasi_status.equalsIgnoreCase("1")){
                                CustomDialog.DialogSuccess(DetailKonfirmasiFlppActivity .this, "Success!", "Berhasil Membatalkan Data", DetailKonfirmasiFlppActivity.this);
                            }
                            else{
                                CustomDialog.DialogSuccess(DetailKonfirmasiFlppActivity .this, "Success!", "Berhasil Melakukan Konfirmasi, Silahkan Lanjutkan di Menu Pipeline Anda", DetailKonfirmasiFlppActivity.this);
                            }

                            loading.setVisibility(View.GONE);

                        }
                        else{
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(DetailKonfirmasiFlppActivity.this, findViewById(android.R.id.content),response.body().getMessage() );
                        }
                    }
                    else{
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DetailKonfirmasiFlppActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    loading.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(DetailKonfirmasiFlppActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void otherViewChanges(){
        if(appPreferences.getNama().equalsIgnoreCase("developer")){
            sw_tembak_sikasep.setVisibility(View.VISIBLE);
        }
        else{
            sw_tembak_sikasep.setVisibility(View.GONE);
        }
    }

    @Override
    public void success(boolean val) {
        if(val){
            finish();
        }
    }

    @Override
    public void confirm(boolean val) {

    }
}