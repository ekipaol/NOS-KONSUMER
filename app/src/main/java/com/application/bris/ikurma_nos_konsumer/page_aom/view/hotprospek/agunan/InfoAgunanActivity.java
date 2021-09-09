package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Build;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqDeleteAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqInfoAgunan;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.AgunanTanahBangunanPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.InfoAgunan;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.widget.Toast;

public class InfoAgunanActivity extends AppCompatActivity implements ConfirmListener {

    @BindView(R.id.tv_id_agunan_info)
    TextView tv_id_agunan_info;
    @BindView(R.id.tv_fid_jenis_agunan_info)
    TextView tv_fid_jenis_agunan_info;
    @BindView(R.id.tv_pengikatan_eksisting_info)
    TextView tv_pengikatan_eksisting_info;
    @BindView(R.id.tv_id_cif_appel_info)
    TextView tv_id_cif_appel_info;
    @BindView(R.id.tv_persen_ftv_info)
    TextView tv_persen_ftv_info;
    @BindView(R.id.tv_jenis_pengikatan)
    TextView tv_jenis_pengikatan;
    @BindView(R.id.tv_cover_plafon)
    TextView tv_cover_plafon;
    @BindView(R.id.tv_nilai_pengikatan)
    TextView tv_nilai_pengikatan;
    @BindView(R.id.btn_hapus_info_agunan)
    Button btn_hapus_info_agunan;
    @BindView(R.id.btn_hapus_lokal_info_bangunan)
    Button btn_hapus_lokal_info_bangunan;
    @BindView(R.id.btn_edit_info_bangunan)
    Button btn_edit_info_bangunan;
    @BindView(R.id.btn_ikat_info_bangunan)
    Button btn_ikat_info_bangunan;
    @BindView(R.id.btn_lihat_info_agunan)
    Button btn_lihat_info_agunan;
    @BindView(R.id.tv_status_appraisal)
    TextView tv_status_appraisal;
    @BindView(R.id.tv_info)
    TextView tv_info;
    @BindView(R.id.progressbar_loading)
    RelativeLayout progressbar_loading;

    private String jenisAgunan;
    private int idAplikasi, idCif, idAgunan, fidJenisAgunan;

    private ApiClientAdapter apiClientAdapter;
    private String dataString;
    private InfoAgunan data;
    private AppPreferences appPreferences;
    private String uuid;
    private Realm realm;
    private String idProgram="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agunan_info_agunan);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Info Agunan");
        appPreferences=new AppPreferences(this);
        realm = Realm.getDefaultInstance();

        idAplikasi = Integer.valueOf(getIntent().getStringExtra("idAplikasi"));
        idCif = Integer.valueOf(getIntent().getStringExtra("idCif"));
        idAgunan = AppUtil.parseIntWithDefault(getIntent().getStringExtra("idAgunan"), 0);
        jenisAgunan = getIntent().getStringExtra("jenisAgunan");
        uuid=getIntent().getStringExtra("uuid");

        if(getIntent().hasExtra("idProgram")){
            idProgram=getIntent().getStringExtra("idProgram");
        }

        if (getIntent().hasExtra("prev")){
            if (getIntent().getStringExtra("prev").equalsIgnoreCase("cif")) {
                btn_hapus_info_agunan.setVisibility(View.GONE);
            }
        }



        loadData();

        //LISTENERS FOR BUTTONS
        btn_hapus_info_agunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAgunan();
            }
        });

        //INI MASIH BELUM DIPAKE KARENA AO PEMRAKARSA TIDAK BISA MENGEDIT DATA AGUNAN YANG SUDAH DIISI OLEH AO SILANG
        btn_edit_info_bangunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("tanah dan bangunan")) {
                    intent = new Intent(InfoAgunanActivity.this, AgunanTanahBangunanInputActivity.class);
                } else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("kendaraan")) {
                    intent = new Intent(InfoAgunanActivity.this, AgunanKendaraanInputActivity.class);
                } else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("tanah kosong")) {
                    intent = new Intent(InfoAgunanActivity.this, ActivityAgunanTanahKosong.class);
                } else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("kios")) {
                    intent = new Intent(InfoAgunanActivity.this, AgunanKiosInputActivity.class);
                } else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("deposito")) {
                    intent = new Intent(InfoAgunanActivity.this, AgunanDepositoInputActivity.class);
                }

                intent.putExtra("idAplikasi", String.valueOf(idAplikasi));
                intent.putExtra("idAgunan", String.valueOf(idAgunan));
                intent.putExtra("loan_type", getIntent().getStringExtra("loan_type"));
                intent.putExtra("tp_produk", getIntent().getStringExtra("tp_produk"));
                intent.putExtra("cif", getIntent().getStringExtra("idCif"));
                intent.putExtra("dariEdit", "ya");
                intent.putExtra("idProgram", idProgram);

                if(uuid!=null&&!uuid.isEmpty()){
                    intent.putExtra("uuid", uuid);
                }

                startActivity(intent);

            }
        });

        btn_lihat_info_agunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("tanah dan bangunan")) {
                    intent = new Intent(InfoAgunanActivity.this, AgunanTanahBangunanInputActivity.class);
                } else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("kendaraan")) {
                    intent = new Intent(InfoAgunanActivity.this, AgunanKendaraanInputActivity.class);
                } else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("tanah kosong")) {
                    intent = new Intent(InfoAgunanActivity.this, ActivityAgunanTanahKosong.class);
                } else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("kios")) {
                    intent = new Intent(InfoAgunanActivity.this, AgunanKiosInputActivity.class);
                } else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("deposito")) {
                    intent = new Intent(InfoAgunanActivity.this, AgunanDepositoInputActivity.class);
                }

                intent.putExtra("idAplikasi", String.valueOf(idAplikasi));
                intent.putExtra("idAgunan", String.valueOf(idAgunan));
                intent.putExtra("loan_type", getIntent().getStringExtra("loan_type"));
                intent.putExtra("tp_produk", getIntent().getStringExtra("tp_produk"));
                intent.putExtra("cif", getIntent().getStringExtra("idCif"));
                intent.putExtra("jenisTombol", "lihat");



                startActivity(intent);

            }
        });

        btn_hapus_lokal_info_bangunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(InfoAgunanActivity.this)
                        .setTitle("Hapus")
                        .setMessage("Anda yakin ingin menghapus agunan dari penyimpanan lokal?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                final ProgressDialog loadingDialog = ProgressDialog.show(InfoAgunanActivity.this, "",
                                        "Memproses", true);


                                AgunanTanahBangunanPojo dataR = realm.where(AgunanTanahBangunanPojo.class).equalTo("uuid", uuid).findFirst();

                                if(dataR!=null){
                                    loadingDialog.dismiss();
                                    realm.beginTransaction();
                                    dataR.deleteFromRealm();
                                    realm.close();
                                    CustomDialog.DialogSuccess(InfoAgunanActivity.this, "Success!", "Berhasil Menghapus Agunan", InfoAgunanActivity.this);
                                }
                                else{
                                    Toast.makeText(InfoAgunanActivity.this, "Gagal mendapatkan data agunan", Toast.LENGTH_SHORT).show();
                                    loadingDialog.dismiss();
                                }

                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton("Batal", null)
                        .show();


            }
        });




        btn_ikat_info_bangunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoAgunanActivity.this, SetPengikatanActivity.class);
                intent.putExtra("idAPlikasi", String.valueOf(idAplikasi));
                intent.putExtra("idAgunan", tv_id_agunan_info.getText().toString());
                intent.putExtra("idCif", tv_id_cif_appel_info.getText().toString());
                intent.putExtra("fidJenisAgunan", String.valueOf(fidJenisAgunan));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void deleteAgunan() {
        new AlertDialog.Builder(InfoAgunanActivity.this)
                .setTitle("Hapus")
                .setMessage("Anda yakin ingin melepas ikatan agunan?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog loadingDialog = ProgressDialog.show(InfoAgunanActivity.this, "",
                                "Memproses", true);

                        ReqDeleteAgunan req = new ReqDeleteAgunan();
                        req.setIdAgunan(idAgunan);
                        req.setIdCif(idCif);
                        req.setIdApl(idAplikasi);
                        req.setFidjenisAgunan(fidJenisAgunan);
                        Call<ParseResponse> call = apiClientAdapter.getApiInterface().deleteAgunan(req);
                        call.enqueue(new Callback<ParseResponse>() {
                            @Override
                            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                                try {
                                    if (response.isSuccessful()) {
                                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                                            loadingDialog.dismiss();
                                            CustomDialog.DialogSuccess(InfoAgunanActivity.this, "Success!", "Berhasil Menghapus Agunan", InfoAgunanActivity.this);
                                            Toast.makeText(InfoAgunanActivity.this, "Berhasil Melepas Ikatan Agunan", Toast.LENGTH_SHORT).show();
                                            Intent it = new Intent(InfoAgunanActivity.this, AgunanTerikatActivity.class);
                                            it.putExtra("cif", getIntent().getStringExtra("idCif"));
                                            it.putExtra("idAplikasi", getIntent().getStringExtra("idAplikasi"));
                                            startActivity(it);
                                            finish();
                                        } else {
                                            AppUtil.notiferror(InfoAgunanActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                                        }
                                    } else {
                                        Error error = ParseResponseError.confirmEror(response.errorBody());
                                        AppUtil.notiferror(InfoAgunanActivity.this, findViewById(android.R.id.content), error.getMessage());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ParseResponse> call, Throwable t) {
                                AppUtil.notiferror(InfoAgunanActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
                            }
                        });

                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("Batal", null)
                .show();
    }

    private void loadData() {
        if(getIntent().getStringExtra("idAgunan").equalsIgnoreCase("0")){
            data=new InfoAgunan();
            data.setCoverPlafond("0");
            data.setFidjenisAgunan("0");
            data.setIdAgunan("0");
            data.setIdCif(Integer.toString(idCif));
            data.setJenisPengikatan("-");
            data.setNilaiPengikatan("0");
            data.setPengikatanEksisting("-");
            data.setPersenFTV("-");

            setDataKhususLokal();

        }
        else {
            progressbar_loading.setVisibility(View.VISIBLE);
            ReqInfoAgunan req = new ReqInfoAgunan();
            req.setIdApl(Integer.valueOf(getIntent().getStringExtra("idAplikasi")));
            req.setIdAgunan(Integer.valueOf(getIntent().getStringExtra("idAgunan")));
            req.setIdCif(idCif);
            Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryInfoAgunan(req);
            call.enqueue(new Callback<ParseResponse>() {
                @Override
                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                    progressbar_loading.setVisibility(View.GONE);
                    try {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase("00")) {
                                Gson gson = new Gson();
                                dataString = response.body().getData().toString();
                                data = gson.fromJson(dataString, InfoAgunan.class);

                                otherViewChanges();
                                setData();


                            } else {
                                switch (jenisAgunan.toLowerCase()) {
                                    case "tanah dan bangunan":
                                        fidJenisAgunan = 7;
                                        break;

                                    case "kendaraan":
                                        fidJenisAgunan = 32;
                                        break;

                                    case "tanah kosong":
                                        fidJenisAgunan = 30;
                                        break;

                                    case "kios":
                                        fidJenisAgunan = 33;
                                        break;

                                    case "deposito":
                                        fidJenisAgunan = 31;
                                        break;
                                }

                                btn_ikat_info_bangunan.setVisibility(View.GONE);
                                btn_edit_info_bangunan.setVisibility(View.VISIBLE);
                                AppUtil.notiferror(InfoAgunanActivity.this, findViewById(android.R.id.content), response.body().getMessage() + " Silahkan edit agunan terlebih dahulu");
                            }
                        } else {
                            Error error = ParseResponseError.confirmEror(response.errorBody());
                            AppUtil.notiferror(InfoAgunanActivity.this, findViewById(android.R.id.content), error.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ParseResponse> call, Throwable t) {
                    progressbar_loading.setVisibility(View.GONE);
                    AppUtil.notiferror(InfoAgunanActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
                }
            });
        }
    }

    public void setData() {
        tv_id_agunan_info.setText(String.valueOf(data.getIdAgunan()));

        //fid jenis agunan special treatment
        if (data.getFidjenisAgunan().equalsIgnoreCase("30")) {
            tv_fid_jenis_agunan_info.setText("Tanah Kosong / Sawah");
        } else if (data.getFidjenisAgunan().equalsIgnoreCase("31")) {
            tv_fid_jenis_agunan_info.setText("Deposito");
        } else if (data.getFidjenisAgunan().equalsIgnoreCase("32")) {
            tv_fid_jenis_agunan_info.setText("Kendaraan Bermotor");
        } else if (data.getFidjenisAgunan().equalsIgnoreCase("33")) {
            tv_fid_jenis_agunan_info.setText("Kios");
        } else if (data.getFidjenisAgunan().equalsIgnoreCase("7")) {
            tv_fid_jenis_agunan_info.setText("Tanah dan Bangunan");
        } else if (data.getFidjenisAgunan().equalsIgnoreCase("8")) {
            tv_fid_jenis_agunan_info.setText("Mesin-mesin");
        }
        //END OF FID SPECIAL TREATMENT
        fidJenisAgunan = AppUtil.parseIntWithDefault(data.getFidjenisAgunan(), 0);
        tv_pengikatan_eksisting_info.setText(data.getPengikatanEksisting());
        tv_id_cif_appel_info.setText(data.getIdCif());
        tv_persen_ftv_info.setText(data.getPersenFTV());
        tv_jenis_pengikatan.setText(data.getJenisPengikatan());
        tv_cover_plafon.setText(AppUtil.parseRupiah(data.getCoverPlafond()));
        tv_nilai_pengikatan.setText(AppUtil.parseRupiah(data.getNilaiPengikatan()));

        //status appraisal information
        if(data.getStatusApraisal().equalsIgnoreCase("0")){
            tv_status_appraisal.setText("BELUM DI APPRAISE");
            tv_status_appraisal.setTextColor(getResources().getColor(R.color.red_btn_bg_color));
        }
        else if(data.getStatusApraisal().equalsIgnoreCase("1")){
            tv_status_appraisal.setText("SUDAH DI APPRAISE");
            tv_status_appraisal.setTextColor(getResources().getColor(R.color.colorGreenSuccess));
        }
    }

    @Override
    public void success(boolean val) {
        finish();
    }

    @Override
    public void confirm(boolean val) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void otherViewChanges(){

        //KHUSUS ID PROGRAM FLPP
        if(idProgram.equalsIgnoreCase("222")){
            btn_lihat_info_agunan.setVisibility(View.GONE);
            btn_edit_info_bangunan.setVisibility(View.VISIBLE);
            btn_ikat_info_bangunan.setVisibility(View.VISIBLE);
            btn_hapus_lokal_info_bangunan.setVisibility(View.GONE);
//            btn_hapus_info_agunan.setVisibility(View.VISIBLE);
        }

        //UNTUK KPR BIASA
        else{
            //artinya sudah pernah diappraisal, jadi tidak bisa diedit lagi oleh AO pemrakarsa, dan bisa diikat
            if(data.getStatusApraisal().equalsIgnoreCase("1")){
                btn_lihat_info_agunan.setVisibility(View.VISIBLE);
                btn_edit_info_bangunan.setVisibility(View.GONE);
                btn_ikat_info_bangunan.setVisibility(View.VISIBLE);
                btn_hapus_lokal_info_bangunan.setVisibility(View.GONE);
            }

            //belum pernah diappraisal, jadi boleh diedit sesuka hati oleh si Ao pemrakarsa, tapi tidak bisa diikat
            else{
                btn_lihat_info_agunan.setVisibility(View.GONE);
                btn_edit_info_bangunan.setVisibility(View.VISIBLE);
                btn_ikat_info_bangunan.setVisibility(View.GONE);
                btn_hapus_lokal_info_bangunan.setVisibility(View.GONE);
                btn_hapus_info_agunan.setVisibility(View.GONE);
            }
        }



        //MENAMPILKAN TEKS INFORMASI

        //teks FLPP
        if(idProgram.equalsIgnoreCase("222")){
            String text2="<p>- Klik tombol <font color='#01C6DB'>'Ikat' </font> untuk mengikat agunan ke aplikasi</p><p>- Klik tombol <font color='#199110'>'Edit' </font> untuk mengedit kembali isian agunan</p> ";

            tv_info.setText(Html.fromHtml(text2), TextView.BufferType.SPANNABLE);
        }

        //teks KPR biasa
        else{
            if(data.getStatusApraisal().equalsIgnoreCase("1")){
                String text2="Agunan ini sudah di appraise oleh AO Silang, dan tidak bisa diedit lagi oleh AO pemrakarsa (hanya bisa di lihat/preview)<p>- Klik tombol <font color='#01C6DB'>'Ikat' </font> untuk mengikat agunan ke aplikasi</p><p>- Klik tombol <font color='#199110'>'Lihat' </font> untuk melihat hasil appraise data agunan oleh AO silang</p>";

                tv_info.setText(Html.fromHtml(text2), TextView.BufferType.SPANNABLE);

            }
            else if(data.getStatusApraisal().equalsIgnoreCase("0")){
                String text2="Agunan ini belum di-appraise oleh AO Silang, pastikan data yang diinputkan sudah sesuai, lalu klik tombol <font color='#01C6DB'>'Request Appraisal' </font> di halaman daftar agunan, untuk mengirim permintaan Appraisal ke pemutus.<p>- Klik tombol <font color='#01C6DB'>'Edit' </font> untuk mengubah data agunan</p>";

                tv_info.setText(Html.fromHtml(text2), TextView.BufferType.SPANNABLE);

            }
        }


    }

    public void setDataKhususLokal() {
        tv_id_agunan_info.setText(String.valueOf(data.getIdAgunan()));

        switch (jenisAgunan.toLowerCase()) {
            case "tanah dan bangunan":
                fidJenisAgunan = 7;
                tv_fid_jenis_agunan_info.setText("Tanah dan Bangunan");
                break;

            case "tanah kosong":
                fidJenisAgunan = 30;
                tv_fid_jenis_agunan_info.setText("Tanah Kosong / Sawah");
                break;

        }

        fidJenisAgunan = AppUtil.parseIntWithDefault(data.getFidjenisAgunan(), 0);
        tv_pengikatan_eksisting_info.setText(data.getPengikatanEksisting());
        tv_id_cif_appel_info.setText(data.getIdCif());
        tv_persen_ftv_info.setText(data.getPersenFTV());
        tv_jenis_pengikatan.setText(data.getJenisPengikatan());
        tv_cover_plafon.setText(AppUtil.parseRupiah(data.getCoverPlafond()));
        tv_nilai_pengikatan.setText(AppUtil.parseRupiah(data.getNilaiPengikatan()));

        tv_status_appraisal.setText("BELUM DI APPRAISE");
        tv_status_appraisal.setTextColor(getResources().getColor(R.color.red_btn_bg_color));

        //karna ini masi data lokal, boleh diedit ao pemrakarsa sesuka hati
        btn_lihat_info_agunan.setVisibility(View.GONE);
        btn_edit_info_bangunan.setVisibility(View.VISIBLE);
        btn_ikat_info_bangunan.setVisibility(View.GONE);
        btn_hapus_info_agunan.setVisibility(View.GONE);

        //KONDISI ID AGUNAN 0 berarti data lokal

            btn_edit_info_bangunan.setText("Lanjutkan");
            String text2="Data agunan ini adalah data yang tersimpan secara lokal di memori perangkat anda dan belum tersimpan di sistem<p>- Klik tombol <font color='#01C6DB'>'Lanjutkan' </font> untuk melanjutkan mengisi data agunan</p><p>- Klik tombol <font color='#ff1744'>'Hapus' </font> untuk menghapus data agunan dari penyimpanan lokal</p>";

            tv_info.setText(Html.fromHtml(text2), TextView.BufferType.SPANNABLE);
            btn_hapus_lokal_info_bangunan.setVisibility(View.VISIBLE);

//            tv_info.setText("Data agunan ini adalah data yang tersimpan secara lokal di memori perangkat anda dan belum tersimpan di sistem\n\n- Klik tombol <font color='#01C6DB'>'Lanjutkan' </font> untuk melanjutkan mengisi data agunan");


    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (getIntent().hasExtra("prev")){
            if (getIntent().getStringExtra("prev").equalsIgnoreCase("cif")) {
                Intent intent = new Intent(InfoAgunanActivity.this, AgunanByCifActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(InfoAgunanActivity.this, AgunanTerikatActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        }
        else{
            super.onBackPressed();
        }
    }
}