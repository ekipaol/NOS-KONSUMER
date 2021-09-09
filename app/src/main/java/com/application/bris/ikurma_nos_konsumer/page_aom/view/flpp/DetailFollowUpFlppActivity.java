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
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.ReqKodeProvinsi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.flpp.ReqSimpanFollowUpFlpp;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.Flpp;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.WilayahFlpp;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class DetailFollowUpFlppActivity extends AppCompatActivity implements   View.OnClickListener, GenericListenerOnSelect , ConfirmListener {

    @BindView(R.id.tf_nama_nasabah)
    TextFieldBoxes tf_nama_nasabah;
    @BindView(R.id.et_nama_nasabah)
    EditText et_nama_nasabah;
    @BindView(R.id.tf_ktp_nasabah)
    TextFieldBoxes tf_ktp_nasabah;
    @BindView(R.id.et_ktp_nasabah)
    EditText et_ktp_nasabah;
    @BindView(R.id.tf_no_ponsel)
    TextFieldBoxes tf_no_ponsel;
    @BindView(R.id.et_no_ponsel)
    EditText et_no_ponsel;

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
    @BindView(R.id.tf_nama_ao)
    TextFieldBoxes tf_nama_ao;
    @BindView(R.id.et_nama_ao)
    EditText et_nama_ao;
    @BindView(R.id.tf_ktp_ao)
    TextFieldBoxes tf_ktp_ao;
    @BindView(R.id.et_ktp_ao)
    EditText et_ktp_ao;
    @BindView(R.id.tf_nip_ao)
    TextFieldBoxes tf_nip_ao;
    @BindView(R.id.et_nip_ao)
    EditText et_nip_ao;
    @BindView(R.id.tf_no_ponsel_ao)
    TextFieldBoxes tf_no_ponsel_ao;
    @BindView(R.id.et_no_ponsel_ao)
    EditText et_no_ponsel_ao;
    @BindView(R.id.tf_alamat_cabang)
    TextFieldBoxes tf_alamat_cabang;
    @BindView(R.id.et_alamat_cabang)
    EditText et_alamat_cabang;
    @BindView(R.id.tf_kab_kota_cabang)
    TextFieldBoxes tf_kab_kota_cabang;
    @BindView(R.id.et_kab_kota_cabang)
    EditText et_kab_kota_cabang;
    @BindView(R.id.tf_nama_kantor_cabang)
    TextFieldBoxes tf_nama_kantor_cabang;
    @BindView(R.id.et_nama_kantor_cabang)
    EditText et_nama_kantor_cabang;
    @BindView(R.id.tf_status)
    TextFieldBoxes tf_status;
    @BindView(R.id.et_status)
    EditText et_status;
    @BindView(R.id.tf_alasan_batal)
    TextFieldBoxes tf_alasan_batal;
    @BindView(R.id.et_alasan_batal)
    EditText et_alasan_batal;
    @BindView(R.id.tf_catatan_ao)
    TextFieldBoxes tf_catatan_ao;
    @BindView(R.id.et_catatan_ao)
    EditText et_catatan_ao;

    @BindView(R.id.tv_page_title)
    TextView tv_page_title;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.btn_send)
    Button btn_send;

    @BindView(R.id.sw_tembak_sikasep)
    Switch sw_tembak_sikasep;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private List<WilayahFlpp> wilayahFlpp;
    private List<MGenericModel> genericKotaKabupaten=new ArrayList<>();
    private List<MGenericModel> genericStatus=new ArrayList<>();

    Flpp dataFlpp;

    private String var_kab_kota="";
    private String var_status="";
    private boolean validasiOk =true;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_followup_flpp);

        ButterKnife.bind(this);

        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);

        tv_page_title.setText("Detail Follow Up Nasabah");

        btn_back.setOnClickListener(this);
        btn_send.setOnClickListener(this);

        dataFlpp=(Flpp)getIntent().getSerializableExtra("dataFollowUp");

        setData();
        loadData();
        otherViewChanges();


    }

    private void setData(){
        et_nama_nasabah.setInputType(InputType.TYPE_NULL);
        et_nama_nasabah.setFocusable(false);

        et_no_ponsel.setInputType(InputType.TYPE_NULL);
        et_no_ponsel.setFocusable(false);

        et_nama_pengembang.setInputType(InputType.TYPE_NULL);
        et_nama_pengembang.setFocusable(false);

        et_nama_perumahan.setInputType(InputType.TYPE_NULL);
        et_nama_perumahan.setFocusable(false);

        et_nama_ao.setInputType(InputType.TYPE_NULL);
        et_nama_ao.setFocusable(false);


        et_nip_ao.setInputType(InputType.TYPE_NULL);
        et_nip_ao.setFocusable(false);

        et_nama_kantor_cabang.setInputType(InputType.TYPE_NULL);
        et_nama_kantor_cabang.setFocusable(false);

        et_kab_kota_cabang.setInputType(InputType.TYPE_NULL);
        et_kab_kota_cabang.setFocusable(false);

        et_alamat_cabang.setInputType(InputType.TYPE_NULL);
        et_alamat_cabang.setFocusable(false);

        et_status.setInputType(InputType.TYPE_NULL);
        et_status.setFocusable(false);




        et_nama_nasabah.setText(dataFlpp.getNamaDebitur());

        //ktp dan npwp dikosongin aja, untuk menghindari fraud katanya mah
//        et_ktp_nasabah.setText(dataFlpp.getKtpDebitur());
//        et_npwp_nasabah.setText(dataFlpp.getNpwpDebitur());

        et_no_ponsel.setText(dataFlpp.getNoPonsel());
        et_nama_pengembang.setText(dataFlpp.getNamaPengembang());
        et_nama_perumahan.setText(dataFlpp.getNamaPerumahan());
        et_nama_ao.setText(appPreferences.getNama());
        et_nip_ao.setText(appPreferences.getNik());
        et_alamat_cabang.setText(dataFlpp.getAlamatKantor());
        et_nama_kantor_cabang.setText(appPreferences.getNamaKantor());

        if(dataFlpp.getKtpPicCabang()==null||dataFlpp.getKtpPicCabang().isEmpty()){
            et_ktp_ao.setText(dataFlpp.getKtpFromPic());
            et_no_ponsel_ao.setText(dataFlpp.getNoPonselFromPic());
        }
        else{
            et_ktp_ao.setText(dataFlpp.getKtpPicCabang());
            et_no_ponsel_ao.setText(dataFlpp.getNoPonselFromPic());
        }

        //set dropdown status
        genericStatus.add(new MGenericModel("0","Lanjut Verifikasi"));
        genericStatus.add(new MGenericModel("1","Batal"));


        //on clicks
        et_kab_kota_cabang.setOnClickListener(this);
        tf_kab_kota_cabang.setOnClickListener(this);
        tf_kab_kota_cabang.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),tf_kab_kota_cabang.getLabelText(),genericKotaKabupaten,DetailFollowUpFlppActivity.this);
            }
        });

        et_status.setOnClickListener(this);
        tf_status.setOnClickListener(this);
        tf_status.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(),tf_status.getLabelText(),genericStatus,DetailFollowUpFlppActivity.this);
            }
        });

        //end of onclicks

    }

    public void loadData(){
        ReqKodeProvinsi req = new ReqKodeProvinsi();
        req.setKodeProvinsi(dataFlpp.getKodeWilayah().substring(0,2));
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listKotaKabupatenFlpp(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
             loading.setVisibility(View.VISIBLE);
                try {
                    if(response.isSuccessful()){

                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<WilayahFlpp>>() {}.getType();
                            wilayahFlpp = gson.fromJson(listDataString, type);

                            for (int i = 0; i < wilayahFlpp.size() ; i++) {
                                genericKotaKabupaten.add(new MGenericModel(wilayahFlpp.get(i).getID_KODE_WILAYAH(), wilayahFlpp.get(i).getNAMA_WILAYAH()));
                                Log.d("generickotakabupaten",genericKotaKabupaten.get(i).getDESC());
                            }
                            loading.setVisibility(View.GONE);

                        }
                        else{
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(DetailFollowUpFlppActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DetailFollowUpFlppActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    loading.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                AppUtil.notiferror(DetailFollowUpFlppActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void simpanFollowUp(){
        loading.setVisibility(View.VISIBLE);
        ReqSimpanFollowUpFlpp req = new ReqSimpanFollowUpFlpp();
      req.setKodeBank("422");
      req.setKtpDebitur(et_ktp_nasabah.getText().toString().trim());
//        req.setKtpDebitur("12123123123123123");
      req.setNpwpDebitur(et_npwp_nasabah.getText().toString().trim());
      req.setNoPonselDebitur(et_no_ponsel.getText().toString().trim());
        req.setNamaPicBankCabang(et_nama_ao.getText().toString().trim());
        req.setKtpPicBankCabang(et_ktp_ao.getText().toString().trim());
        req.setNipPicBankCabang(et_nip_ao.getText().toString().trim());
        req.setNoPonselPicBankCabang(et_no_ponsel_ao.getText().toString().trim());
        req.setAlamatCabang(et_alamat_cabang.getText().toString().trim());
        req.setKabKotaCabang(var_kab_kota);
        req.setNamaKantorCabang(et_nama_kantor_cabang.getText().toString().trim());
        req.setStatus(var_status);
        req.setAlasanBatal(et_alasan_batal.getText().toString().trim());
        req.setUid(Integer.toString(appPreferences.getUid()));
        req.setCatatan(et_catatan_ao.getText().toString().trim());

        if(appPreferences.getNama().equalsIgnoreCase("developer")&&!sw_tembak_sikasep.isChecked()){
            //kalo lagi testing service  diisi 1, kalo lagi prod dibikin 0 atau gausah diisi yach xixixi
            req.setIsTesting("1");
            Toast.makeText(this, "melempar parameter testing true, ke service followup flpp", Toast.LENGTH_SHORT).show();
        }
        else{
            req.setIsTesting("0");
        }





        Call<ParseResponse> call = apiClientAdapter.getApiInterface().simpanFollowUpFlpp(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                try {
                    if(response.isSuccessful()){


                        if(response.body().getStatus().equalsIgnoreCase("00")){
//                            String keterangan = response.body().getData().get("KETERANGAN").toString();

                            if(var_status.equalsIgnoreCase("1")){
                                CustomDialog.DialogSuccess(DetailFollowUpFlppActivity .this, "Success!", "Berhasil Membatalkan Data", DetailFollowUpFlppActivity.this);
                            }
                            else{
                                CustomDialog.DialogSuccess(DetailFollowUpFlppActivity .this, "Success!", "Berhasil Melakukan Follow Up, Silahkan Lanjutkan di Menu Konfirmasi Flpp", DetailFollowUpFlppActivity.this);
                            }



                            loading.setVisibility(View.GONE);

                        }
                        else{
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(DetailFollowUpFlppActivity.this, findViewById(android.R.id.content),response.body().getMessage() );
                        }
                    }
                    else{
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DetailFollowUpFlppActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    loading.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(DetailFollowUpFlppActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                CustomDialog.DialogBackpress(this);
                break;

            case R.id.btn_send:

                if(validateForm()){
                   simpanFollowUp();
                }

                break;
            case R.id.et_kab_kota_cabang:
            case R.id.tf_kab_kota_cabang:
                DialogGenericDataFromService.display(getSupportFragmentManager(),tf_kab_kota_cabang.getLabelText(),genericKotaKabupaten,this);


                break;


            case R.id.tf_status:
            case R.id.et_status:
                DialogGenericDataFromService.display(getSupportFragmentManager(),tf_status.getLabelText(),genericStatus,DetailFollowUpFlppActivity.this);


                break;


        }
    }

    @Override
    public void onSelect(String title, MGenericModel data) {

        //jangan lupa buat variabel VAL untuk masing masing field yang dikirim
      if(title.equalsIgnoreCase(tf_kab_kota_cabang.getLabelText())){
          et_kab_kota_cabang.setText(data.getDESC());
          var_kab_kota=data.getID();
      }
      else  if(title.equalsIgnoreCase(tf_status.getLabelText())){
          et_status.setText(data.getDESC());
          var_status=data.getID();
          if(data.getDESC().equalsIgnoreCase("batal")){
              tf_alasan_batal.setVisibility(View.VISIBLE);
          }
          else{
              tf_alasan_batal.setVisibility(View.GONE);
          }
      }

    }

    private boolean validateForm(){

        //reset validation parameter everytime click send
        validasiOk=true;

         subvalidateField(et_ktp_nasabah,tf_ktp_nasabah);
         subvalidateField(et_npwp_nasabah,tf_npwp_nasabah);
         subvalidateField(et_ktp_ao,tf_ktp_ao);
         subvalidateField(et_no_ponsel_ao,tf_no_ponsel_ao);
         subvalidateField(et_kab_kota_cabang,tf_kab_kota_cabang);
         subvalidateField(et_status,tf_status);
         subvalidateField(et_catatan_ao,tf_catatan_ao);

        if(et_status.getText().toString().equalsIgnoreCase("batal")){
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

    private void otherViewChanges(){
        if(appPreferences.getNama().equalsIgnoreCase("developer")){
            sw_tembak_sikasep.setVisibility(View.VISIBLE);
        }
        else{
            sw_tembak_sikasep.setVisibility(View.GONE);
        }
    }

    private void subvalidateField(EditText editText,TextFieldBoxes textFieldBoxes){

            if(editText.getText().toString().isEmpty()||editText.getText().toString().equalsIgnoreCase("pilih")){
                textFieldBoxes.setError("Harap isi "+textFieldBoxes.getLabelText(),true);
                AppUtil.notiferror(DetailFollowUpFlppActivity.this, findViewById(android.R.id.content), "Harap isi "+textFieldBoxes.getLabelText());
                validasiOk =false;
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