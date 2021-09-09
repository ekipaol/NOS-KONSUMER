package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan.kpr_only;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqDeleteAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqInfoAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.kpr.ReqAppraisal;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.AgunanTanahBangunanPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.InfoAgunan;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.HotprospekDetailKprActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan.ActivityAgunanTanahKosong;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan.AgunanByCifActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan.AgunanDepositoInputActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan.AgunanKendaraanInputActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan.AgunanKiosInputActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan.AgunanTanahBangunanInputActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan.AgunanTerikatActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoAgunanAoSilangActivity extends AppCompatActivity implements ConfirmListener {

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
    @BindView(R.id.btn_edit_info_bangunan)
    Button btn_edit_info_bangunan;
    @BindView(R.id.btn_lihat_info_agunan)
    Button btn_lihat_info_agunan;
    @BindView(R.id.btn_request_appraisal)
    Button btn_request_appraisal;
    @BindView(R.id.btn_selesai)
    Button btn_selesai;
    @BindView(R.id.btn_hapus_lokal_info_bangunan)
    Button btn_hapus_lokal_info_bangunan;
    @BindView(R.id.tv_info)
    TextView tv_info;
    @BindView(R.id.tv_status_appraisal)
    TextView tv_status_appraisal;
    @BindView(R.id.progressbar_loading)
    RelativeLayout progressbar_loading;

    private String jenisAgunan;
    private String prev;
    private String uuid;
    private int idAplikasi, idCif, idAgunan, fidJenisAgunan;

    private ApiClientAdapter apiClientAdapter;
    private String dataString;
    private InfoAgunan data;
    private int flagAgunan;
    AppPreferences appPreferences;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agunan_info_agunan_ao_silang);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Info Agunan");
        appPreferences=new AppPreferences(this);
        realm = Realm.getDefaultInstance();

        uuid=getIntent().getStringExtra("uuid");
        idAplikasi = Integer.valueOf(getIntent().getStringExtra("idAplikasi"));
        idCif = Integer.valueOf(getIntent().getStringExtra("idCif"));
        idAgunan = AppUtil.parseIntWithDefault(getIntent().getStringExtra("idAgunan"), 0);
        jenisAgunan = getIntent().getStringExtra("jenisAgunan");
        flagAgunan=getIntent().getIntExtra("flagAgunan",3);
        prev=getIntent().getStringExtra("prev");

        if (getIntent().hasExtra("prev")){
            if (getIntent().getStringExtra("prev").equalsIgnoreCase("cif")) {
                btn_hapus_info_agunan.setVisibility(View.GONE);
            }
        }

//        otherViewChanges();

        loadData();

        //LISTENERS FOR BUTTONS
        btn_hapus_info_agunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAgunan();
            }
        });

        btn_edit_info_bangunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("tanah dan bangunan"))
                    intent = new Intent(InfoAgunanAoSilangActivity.this, AgunanTanahBangunanInputActivity.class);
                else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("kendaraan")) {
                    intent = new Intent(InfoAgunanAoSilangActivity.this, AgunanKendaraanInputActivity.class);
                } else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("tanah kosong")) {
                    intent = new Intent(InfoAgunanAoSilangActivity.this, ActivityAgunanTanahKosong.class);
                } else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("kios")) {
                    intent = new Intent(InfoAgunanAoSilangActivity.this, AgunanKiosInputActivity.class);
                } else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("deposito")) {
                    intent = new Intent(InfoAgunanAoSilangActivity.this, AgunanDepositoInputActivity.class);
                }

                intent.putExtra("idAplikasi", String.valueOf(idAplikasi));
                intent.putExtra("idAgunan", String.valueOf(idAgunan));
                intent.putExtra("loan_type", getIntent().getStringExtra("loan_type"));
                intent.putExtra("tp_produk", getIntent().getStringExtra("tp_produk"));
                intent.putExtra("cif", getIntent().getStringExtra("idCif"));
                intent.putExtra("jenisAo", "ao silang");
//                intent.putExtra("dariEdit", "ya");

                if(uuid!=null&&!uuid.isEmpty()){
                    intent.putExtra("uuid", uuid);
                }

                startActivity(intent);

            }
        });

        btn_request_appraisal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              requestAppraisal();
            }
        });

        btn_selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(prev!=null&&prev.equalsIgnoreCase("terikat")){
                    Intent intent=new Intent(InfoAgunanAoSilangActivity.this, AgunanTerikatActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                    startActivity(intent);
                }
                else{
                    Intent intent=new Intent(InfoAgunanAoSilangActivity.this, AgunanByCifActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                    startActivity(intent);
                }

            }
        });

        btn_lihat_info_agunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("tanah dan bangunan"))
                    intent = new Intent(InfoAgunanAoSilangActivity.this, AgunanTanahBangunanInputActivity.class);
                else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("kendaraan")) {
                    intent = new Intent(InfoAgunanAoSilangActivity.this, AgunanKendaraanInputActivity.class);
                } else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("tanah kosong")) {
                    intent = new Intent(InfoAgunanAoSilangActivity.this, ActivityAgunanTanahKosong.class);
                } else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("kios")) {
                    intent = new Intent(InfoAgunanAoSilangActivity.this, AgunanKiosInputActivity.class);
                } else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("deposito")) {
                    intent = new Intent(InfoAgunanAoSilangActivity.this, AgunanDepositoInputActivity.class);
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

                new AlertDialog.Builder(InfoAgunanAoSilangActivity.this)
                        .setTitle("Hapus")
                        .setMessage("Anda yakin ingin menghapus agunan dari penyimpanan lokal?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                final ProgressDialog loadingDialog = ProgressDialog.show(InfoAgunanAoSilangActivity.this, "",
                                        "Memproses", true);


                                AgunanTanahBangunanPojo dataR = realm.where(AgunanTanahBangunanPojo.class).equalTo("uuid", uuid).findFirst();

                                if(dataR!=null){
                                    loadingDialog.dismiss();
                                    realm.beginTransaction();
                                    dataR.deleteFromRealm();
                                    realm.close();
                                    CustomDialog.DialogSuccess(InfoAgunanAoSilangActivity.this, "Success!", "Berhasil Menghapus Agunan", InfoAgunanAoSilangActivity.this);
                                }
                                else{
                                    Toast.makeText(InfoAgunanAoSilangActivity.this, "Gagal mendapatkan data agunan", Toast.LENGTH_SHORT).show();
                                    loadingDialog.dismiss();
                                }

                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton("Batal", null)
                        .show();


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

    public void otherViewChanges(){
//        String text = "<font color='black'><p>Nilai pengikatan dan cover plafond akan muncul setelah agunan diikat</p><font color='black'><p>Pengikatan agunan dapat dilakukan setelah agunan selesai diproses oleh AO silang</p>- Klik tombol </font><font color='#01C6DB'>'Simpan dan Request Appraisal' </font><font color='black'> di halaman daftar agunan untuk melakukan permintaan AO Silang<p>- AO silang akan dipilih oleh pemutus.</p><p>- AO silang akan melakukan verifikasi terhadap inputan agunan, dan dapat melakukan perubahan atas data agunan</p><p>- Klik tombol selesai untuk kembali ke halaman daftar agunan.</p></font>";

        String text = "<font color='black'><p>Nilai pengikatan dan cover plafond akan muncul setelah agunan diikat</p><font color='black'><p>Pengikatan agunan dilakukan oleh AO pemrakarsa setelah AO silang selesai melakukan proses appraisal</p>- Klik tombol </font><font color='#01C6DB'>'Edit' </font><font color='black'> untuk melanjutkan mengisi data agunan<p>- Klik tombol </font><font color='#57D77D'>'Selesai' </font><font color='black'> untuk kembali ke halaman daftar agunan.</p></font>";
        tv_info.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);

        //ini kalau dia dapet data dari realm
        if(idAgunan==0){
            btn_request_appraisal.setVisibility(View.GONE);
            btn_edit_info_bangunan.setText("Lanjutkan");
            btn_hapus_lokal_info_bangunan.setVisibility(View.VISIBLE);
            String text2="Data agunan ini adalah data yang tersimpan secara lokal di memori perangkat anda dan belum tersimpan di sistem<p>- Klik tombol <font color='#01C6DB'>'Lanjutkan' </font> untuk melanjutkan mengisi data agunan</p><p>- Klik tombol <font color='#199110'>'Selesai' </font> untuk kembali ke halaman daftar agunan</p><p>- Klik tombol <font color='#ff1744'>'Hapus' </font> untuk menghapus data agunan dari penyimpanan lokal</p>";

            tv_info.setText(Html.fromHtml(text2), TextView.BufferType.SPANNABLE);

//            tv_info.setText("Data agunan ini adalah data yang tersimpan secara lokal di memori perangkat anda dan belum tersimpan di sistem\n\n- Klik tombol <font color='#01C6DB'>'Lanjutkan' </font> untuk melanjutkan mengisi data agunan");
        }

        else if(data.getStatusApraisal().equalsIgnoreCase("1")){
//        else if(idAgunan!=0&&flagAgunan==1){
//            btn_edit_info_bangunan.setVisibility(View.GONE);
//            btn_selesai.setVisibility(View.GONE);
//            btn_lihat_info_agunan.setVisibility(View.VISIBLE);
//
//            String text3="Data agunan sudah diikat oleh AO silang<p>- Klik tombol <font color='#01C6DB'>'Lihat' </font> untuk melihat hasil appraisal agunan</p><p>- Agunan yang sudah diikat tidak dapat diedit lagi oleh AO pemrakarsa</p>";

            btn_edit_info_bangunan.setVisibility(View.VISIBLE);
            btn_selesai.setVisibility(View.VISIBLE);
            btn_lihat_info_agunan.setVisibility(View.GONE);

            String text3= "<font color='black'><p>Nilai pengikatan dan cover plafond akan muncul setelah agunan diikat</p><font color='black'><p>Pengikatan agunan dilakukan oleh AO pemrakarsa setelah AO silang selesai melakukan proses appraisal</p>- Klik tombol </font><font color='#01C6DB'>'Edit' </font><font color='black'> untuk melanjutkan mengisi data agunan<p>- Klik tombol </font><font color='#57D77D'>'Selesai' </font><font color='black'> untuk kembali ke halaman daftar agunan.</p></font>";

            tv_info.setText(Html.fromHtml(text3), TextView.BufferType.SPANNABLE);

        }
//        else if(idAgunan!=0&&flagAgunan==0){
//            btn_edit_info_bangunan.setVisibility(View.VISIBLE);
//            btn_selesai.setVisibility(View.VISIBLE);
//            btn_lihat_info_agunan.setVisibility(View.GONE);
//
//        }
//        else if(idAgunan==0&&flagAgunan==0){
//            btn_edit_info_bangunan.setVisibility(View.VISIBLE);
//            btn_selesai.setVisibility(View.VISIBLE);
//            btn_lihat_info_agunan.setVisibility(View.GONE);
//
//        }
        else {
//            btn_edit_info_bangunan.setVisibility(View.GONE);
//            btn_selesai.setVisibility(View.GONE);
//            btn_lihat_info_agunan.setVisibility(View.VISIBLE);
            btn_edit_info_bangunan.setVisibility(View.VISIBLE);
            btn_selesai.setVisibility(View.VISIBLE);
            btn_lihat_info_agunan.setVisibility(View.GONE);

        }
    }

    private void deleteAgunan() {
        new AlertDialog.Builder(InfoAgunanAoSilangActivity.this)
                .setTitle("Hapus")
                .setMessage("Anda yakin ingin menghapus agunan?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog loadingDialog = ProgressDialog.show(InfoAgunanAoSilangActivity.this, "",
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
                                            CustomDialog.DialogSuccess(InfoAgunanAoSilangActivity.this, "Success!", "Berhasil Menghapus Agunan", InfoAgunanAoSilangActivity.this);
                                            Toast.makeText(InfoAgunanAoSilangActivity.this, "Berhasil Menghapus Agunan", Toast.LENGTH_SHORT).show();
                                            Intent it = new Intent(InfoAgunanAoSilangActivity.this, AgunanTerikatActivity.class);
                                            it.putExtra("cif", getIntent().getStringExtra("idCif"));
                                            it.putExtra("idAplikasi", getIntent().getStringExtra("idAplikasi"));
                                            startActivity(it);
                                            finish();
                                        } else {
                                            AppUtil.notiferror(InfoAgunanAoSilangActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                                        }
                                    } else {
                                        Error error = ParseResponseError.confirmEror(response.errorBody());
                                        AppUtil.notiferror(InfoAgunanAoSilangActivity.this, findViewById(android.R.id.content), error.getMessage());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ParseResponse> call, Throwable t) {
                                AppUtil.notiferror(InfoAgunanAoSilangActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
                            }
                        });

                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("Batal", null)
                .show();
    }

    private void requestAppraisal() {
        new AlertDialog.Builder(InfoAgunanAoSilangActivity.this)
                .setTitle("Request Appraisal?")
                .setMessage("Data agunan tidak bisa diubah setelah melakukan request appraisal, lanjutkan?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog loadingDialog = ProgressDialog.show(InfoAgunanAoSilangActivity.this, "",
                                "Memproses", true);

                        ReqAppraisal req = new ReqAppraisal();
                        req.setIdApl(idAplikasi);
                        req.setUid(appPreferences.getUid());
                        Call<ParseResponse> call = apiClientAdapter.getApiInterface().requestAppraisal(req);
                        call.enqueue(new Callback<ParseResponse>() {
                            @Override
                            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                                try {
                                    if (response.isSuccessful()) {
                                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                                            loadingDialog.dismiss();
                                            CustomDialog.DialogSuccess(InfoAgunanAoSilangActivity.this, "Sukses!", "Berhasil request appraisal, silahkan monitor secara berkala status aplikasi hingga agunan selesai diproses oleh AO Silang", InfoAgunanAoSilangActivity.this);
//                                            Toast.makeText(InfoAgunanAoSilangActivity.this, "Berhasil Menghapus Agunan", Toast.LENGTH_SHORT).show();
//                                            finish();
                                        } else {
                                            AppUtil.notiferror(InfoAgunanAoSilangActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                                        }
                                    } else {
                                        Error error = ParseResponseError.confirmEror(response.errorBody());
                                        AppUtil.notiferror(InfoAgunanAoSilangActivity.this, findViewById(android.R.id.content), error.getMessage());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ParseResponse> call, Throwable t) {
                                AppUtil.notiferror(InfoAgunanAoSilangActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
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
        else{
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

//                            btn_request_appraisal.setVisibility(View.GONE);
                                AppUtil.notiferror(InfoAgunanAoSilangActivity.this, findViewById(android.R.id.content), response.body().getMessage() + " Silahkan edit agunan terlebih dahulu");
                            }
                        } else {
                            Error error = ParseResponseError.confirmEror(response.errorBody());
                            AppUtil.notiferror(InfoAgunanAoSilangActivity.this, findViewById(android.R.id.content), error.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ParseResponse> call, Throwable t) {
                    progressbar_loading.setVisibility(View.GONE);
                    AppUtil.notiferror(InfoAgunanAoSilangActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
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
    }

    @Override
    public void success(boolean val) {

        Intent intent=new Intent(InfoAgunanAoSilangActivity.this, HotprospekDetailKprActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
        startActivity(intent);
    }

    @Override
    public void confirm(boolean val) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
