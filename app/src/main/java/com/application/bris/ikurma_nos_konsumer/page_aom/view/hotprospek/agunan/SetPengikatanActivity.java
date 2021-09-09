package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqAgunanJenisKlasifikasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqIkatAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqSetPengikatan;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.InquirySetPengikatan;
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

public class SetPengikatanActivity extends AppCompatActivity implements ConfirmListener {

    @BindView(R.id.tv_id_plafond_pengajuan_set_pengikatan)
    TextView tv_id_plafond_pengajuan_set_pengikatan;
    @BindView(R.id.tv_jenis_agunan_set_pengikatan)
    TextView tv_jenis_agunan_set_pengikatan;
    @BindView(R.id.tv_id_agunan_set_pengikatan)
    TextView tv_id_agunan_set_pengikatan;
    @BindView(R.id.tv_nilai_market)
    TextView tv_nilai_market;
    @BindView(R.id.tv_nominal_pengikatan_aplikasi_lain)
    TextView tv_nominal_pengikatan_aplikasi_lain;
    @BindView(R.id.tv_nominal_akan_diikat)
    TextView tv_nominal_akan_diikat;
    @BindView(R.id.tv_plafon_cover_pengikatan)
    TextView tv_cover_plafon;
    @BindView(R.id.et_no_bukti_pengikatan)
    EditText et_no_bukti_pengikatan;
    @BindView(R.id.sp_jenis_pengikatan)
    Spinner sp_jenis_pengikatan;
    @BindView(R.id.sp_klasifikasi_agunan)
    Spinner sp_klasifikasi_agunan;
    @BindView(R.id.progressbar_loading)
    RelativeLayout progressbar_loading;
    @BindView(R.id.btn_ikat_set_pengikatan)
    Button btn_ikat_set_pengikatan;
    @BindView(R.id.btn_kembali_set_pengikatan)
    Button btn_kembali_set_pengikatan;

    String fidjenisAgunan;

    List<ReqAgunanJenisKlasifikasi> dataJenisKlasifikasi;
    InquirySetPengikatan dataInquirySetPengikatan;

    private ApiClientAdapter apiClientAdapter;
    private String dataString;
    private String namaDebitur, plafondCover, tipeProduk, klasifikasiAgunan, jenisPengikatan, pengikatanAplikasi;
    private List<String> dataJenisKlasifikasiString = new ArrayList<>();
    private List<String> dataJenisKlasifikasiInt = new ArrayList<>();
    ArrayAdapter<String> adapter;

    private List jenisPengikatanArray = new ArrayList<>();
    private ArrayAdapter adapterJenisPengikatan;
    private int itemPositionJenisPengikatan;
    private String idJenisPengikatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agunan_set_pengikatan);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Pengikatan Agunan");
        loadData();

        //LISTENERS FOR BUTTONS
        sp_jenis_pengikatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemPositionJenisPengikatan = position;
                idJenisPengikatan = dataJenisKlasifikasi.get(itemPositionJenisPengikatan).getValuePengikatan();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_kembali_set_pengikatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_ikat_set_pengikatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonIkat();
            }
        });

    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }


    private void loadData() {
        progressbar_loading.setVisibility(View.VISIBLE);
        ReqSetPengikatan req = new ReqSetPengikatan();
        req.setIdApl(Integer.parseInt(getIntent().getStringExtra("idAPlikasi")));
        req.setIdCif(Integer.parseInt(getIntent().getStringExtra("idCif")));
        req.setIdAgunan(Integer.parseInt(getIntent().getStringExtra("idAgunan")));
        req.setFidjenisAgunan(Integer.parseInt(getIntent().getStringExtra("fidJenisAgunan")));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().setPengikatan(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();

                            String listInquirySetPengikatan = response.body().getData().toString();
                            dataInquirySetPengikatan = gson.fromJson(listInquirySetPengikatan, InquirySetPengikatan.class);

                            namaDebitur = dataInquirySetPengikatan.getNamaDebitur();
                            plafondCover = dataInquirySetPengikatan.getPlafondCover();
                            tipeProduk = dataInquirySetPengikatan.getTipeProduk();
                            pengikatanAplikasi = dataInquirySetPengikatan.getPengikatanAplikasi();

                            //REQUEST klasifikasi agunan spinner

                            Call<ParseResponseArr> call2 = apiClientAdapter.getApiInterface().jenisKlasifikasi(EmptyRequest.INSTANCE);
                            call2.enqueue(new Callback<ParseResponseArr>() {
                                @Override
                                public void onResponse(Call<ParseResponseArr> call2, Response<ParseResponseArr> response2) {

                                    try {
                                        if (response2.isSuccessful()){
                                            if(response2.body().getStatus().equalsIgnoreCase("00")){

                                                String listDataString = response2.body().getData().toString();
                                                Gson gson = new Gson();
                                                Type type = new TypeToken<List<ReqAgunanJenisKlasifikasi>>() {}.getType();

                                                dataJenisKlasifikasi = gson.fromJson(listDataString, type);

                                                if (dataJenisKlasifikasi.size() > 0){
                                                    for (int i = 0; i < dataJenisKlasifikasi.size(); i++){
                                                        jenisPengikatanArray.add(dataJenisKlasifikasi.get(i).getJenisPengikatan());
                                                    }
                                                    adapterJenisPengikatan = new ArrayAdapter<String>(SetPengikatanActivity.this, R.layout.spinner_style, jenisPengikatanArray);
                                                    adapterJenisPengikatan.setDropDownViewResource(android.R.layout.simple_list_item_1);
                                                    sp_jenis_pengikatan.setAdapter(adapterJenisPengikatan);
                                                    itemPositionJenisPengikatan = sp_jenis_pengikatan.getSelectedItemPosition();
                                                }
                                            }
                                        }
                                        else {
                                            Error error = ParseResponseError.confirmEror(response2.errorBody());
                                            AppUtil.notiferror(SetPengikatanActivity.this, findViewById(android.R.id.content), error.getMessage());
                                        }
                                    }
                                    catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ParseResponseArr> call2, Throwable t) {
                                    AppUtil.notiferror(SetPengikatanActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
                                }
                            });
                            progressbar_loading.setVisibility(View.GONE);
                            setData();
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(SetPengikatanActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(SetPengikatanActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
            }
        });
    }

    public void setData(){
        tv_id_plafond_pengajuan_set_pengikatan.setText(AppUtil.parseRupiah(String.valueOf(dataInquirySetPengikatan.getPlafond())));

        fidjenisAgunan = getIntent().getStringExtra("fidJenisAgunan");

        if(fidjenisAgunan.equalsIgnoreCase("30")){
            tv_jenis_agunan_set_pengikatan.setText("Tanah Kosong / Sawah");
        }
        else if(fidjenisAgunan.equalsIgnoreCase("31")){
            tv_jenis_agunan_set_pengikatan.setText("Deposito");
        }
        else if(fidjenisAgunan.equalsIgnoreCase("32")){
            tv_jenis_agunan_set_pengikatan.setText("Kendaraan Bermotor");
        }
        else if(fidjenisAgunan.equalsIgnoreCase("33")){
            tv_jenis_agunan_set_pengikatan.setText("Kios");
        }
        else if(fidjenisAgunan.equalsIgnoreCase("7")){
            tv_jenis_agunan_set_pengikatan.setText("Tanah dan Bangunan");
        }
        else if(fidjenisAgunan.equalsIgnoreCase("8")){
            tv_jenis_agunan_set_pengikatan.setText("Mesin-mesin");
        }
        tv_id_agunan_set_pengikatan.setText(getIntent().getStringExtra("idAgunan"));
        tv_nilai_market.setText(AppUtil.parseRupiah(String.valueOf(dataInquirySetPengikatan.getNilaiMarket())));
        tv_nominal_pengikatan_aplikasi_lain.setText(AppUtil.parseRupiah(String.valueOf(dataInquirySetPengikatan.getPengikatanLain())));
        tv_nominal_akan_diikat.setText(AppUtil.parseRupiah(dataInquirySetPengikatan.getPengikatanAplikasi()));
        tv_cover_plafon.setText(AppUtil.parseRupiah(dataInquirySetPengikatan.getPlafondCover()));

    }

    private void buttonIkat(){

        if (et_no_bukti_pengikatan.getText().toString().isEmpty() || et_no_bukti_pengikatan.getText().toString().trim().equalsIgnoreCase("")){
            AppUtil.notifwarning(this, findViewById(android.R.id.content), "No Bukti Pengikatan Wajib diisi");
        }
        else{
            new AlertDialog.Builder(SetPengikatanActivity.this)
                    .setTitle("Ikat")
                    .setMessage("Apakah anda yakin ingin mengikat agunan?")
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            final ProgressDialog loadingDialog = ProgressDialog.show(SetPengikatanActivity.this, "",
                                    "Memproses", true);
                            ReqIkatAgunan req = new ReqIkatAgunan();

                            //data masih dipantek semua yaaaa
                            req.setIdAgunan(getIntent().getStringExtra("idAgunan"));
                            req.setIdApl(getIntent().getStringExtra("idAPlikasi"));
                            req.setIdCif(getIntent().getStringExtra("idCif"));
                            req.setFidjenisAgunan(Integer.valueOf(getIntent().getStringExtra("fidJenisAgunan"))); //ID AGUNAN TANAH & BANGUNAN
                            req.setPlafondCover(plafondCover);
                            req.setNamaDebitur(namaDebitur);
                            req.setTipeProduk(tipeProduk);
                            req.setKlasifikasiAgunan(sp_klasifikasi_agunan.getSelectedItem().toString());
                            req.setJenisPengikatan(idJenisPengikatan);


                            req.setPengikatanAplikasi(pengikatanAplikasi);
                            req.setDescPengikatan(et_no_bukti_pengikatan.getText().toString().trim());
                            Call<ParseResponse> call = apiClientAdapter.getApiInterface().ikatAgunan(req);
                            call.enqueue(new Callback<ParseResponse>() {
                                @Override
                                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response1) {
                                    loadingDialog.dismiss();
                                    try {
                                        if (response1.isSuccessful()){
                                            if(response1.body().getStatus().equalsIgnoreCase("00")){
                                                // END OF SPINNER KLASIFIKASI AGUNAN REQUEST
                                                CustomDialog.DialogSuccess(SetPengikatanActivity.this, "Success!", "Berhasil Mengikat AgunanTanahBangunan", SetPengikatanActivity.this);
                                                Toast.makeText(SetPengikatanActivity.this, "Berhasil Mengikat Agunan", Toast.LENGTH_SHORT).show();

                                            } else {
                                                AppUtil.notiferror(SetPengikatanActivity.this, findViewById(android.R.id.content), response1.body().getMessage());
                                            }
                                        }
                                        else {
                                            Error error = ParseResponseError.confirmEror(response1.errorBody());
                                            AppUtil.notiferror(SetPengikatanActivity.this, findViewById(android.R.id.content), error.getMessage());
                                        }
                                    }
                                    catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ParseResponse> call, Throwable t) {
                                    AppUtil.notiferror(SetPengikatanActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
                                }
                            });

                        }
                    })
                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton("Batal", null)
                    .show();
        }
    }

    @Override
    public void success(boolean val) {
        Intent it = new Intent(SetPengikatanActivity.this, AgunanTerikatActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(it);
    }

    @Override
    public void confirm(boolean val) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}