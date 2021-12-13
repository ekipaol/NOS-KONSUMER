package com.application.bris.ikurma_nos_konsumer.page_monitoring.monitoring_pencairan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseSaldo;
import com.application.bris.ikurma_nos_konsumer.api.model.request.monitoring.ReqSaldoNasabah;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.model.monitoring.NasabahMonitoring;
import com.application.bris.ikurma_nos_konsumer.model.monitoring.SaldoNasabah;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;


public class DetailMonitoringNasabahActivity extends AppCompatActivity {

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;

    @BindView(R.id.tv_nama_nasabah)
    TextView tv_nama_nasabah;

    @BindView(R.id.tv_produk_nasabah)
    TextView tv_produk_nasabah;

    @BindView(R.id.tv_plafond_awal_nasabah)
    TextView tv_plafond_awal_nasabah;

    @BindView(R.id.tv_jangka_waktu_nasabah)
    TextView tv_jangka_waktu_nasabah;

    @BindView(R.id.tv_outstanding_nasabah)
    TextView tv_outstanding_nasabah;

    @BindView(R.id.tv_kol_nasabah)
    TextView tv_kol_nasabah;

    @BindView(R.id.tv_dpk_nasabah)
    TextView tv_dpk_nasabah;

    @BindView(R.id.tv_tanggal_pencairan)
    TextView tv_tanggal_pencairan;

    @BindView(R.id.tv_tanggal_jatuh_tempo_nasabah)
    TextView tv_tanggaljatuhtempo;

    @BindView(R.id.tv_nomor_rekening)
    TextView tv_nomor_rekening;



    @BindView(R.id.tv_saldo_nasabah)
    TextView tv_saldo_nasabah;

    @BindView(R.id.tv_saldo_diblokir)
    TextView tv_saldo_diblokir;

    @BindView(R.id.tv_alamat_nasabah)
    TextView tv_alamat_nasabah;

    @BindView(R.id.tv_nomor_telepon_nasabah)
    TextView tv_nomor_telepon_nasabah;

    @BindView(R.id.tv_day_past_due)
    TextView tv_day_past_due;

    @BindView(R.id.tv_tanggal_past_due)
    TextView tv_tanggal_past_due;

    @BindView(R.id.ll_day_past_due)
    LinearLayout ll_day_past_due;

    @BindView(R.id.ll_tanggal_past_due)
    LinearLayout ll_tanggal_past_due;

    @BindView(R.id.tv_tunggakan_nasabah)
    TextView tv_tunggakan_nasabah;

    @BindView(R.id.tv_angsuran_nasabah)
    TextView tv_angsuran_nasabah;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;


    @BindView(R.id.bt_lihat_riwayat_mutasi)
    Button bt_lihat_riwayat_mutasi;

    @BindView(R.id.ll_sisa_durasi)
    LinearLayout ll_sisa_durasi;

    @BindView(R.id.tv_sisa_durasi)
    TextView tv_sisa_durasi;



    SaldoNasabah dataSaldo;

    Call<ParseResponseSaldo> call;
    ApiClientAdapter apiClientAdapter;


   NasabahMonitoring dataNasabah;
    AdapterMonitoringPencairan adapterMonitoringPencairan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring_detil_nasabah);
        main();

    }

    public void main(){
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Monitoring Pencairan");
        apiClientAdapter = new ApiClientAdapter(this);
        dataNasabah = (NasabahMonitoring) getIntent().getSerializableExtra("dataMutasi");

        if(dataNasabah!=null&&dataNasabah.getStatus().equalsIgnoreCase("01")){
            loadData();
            setData();
        }
        else if(dataNasabah!=null&&dataNasabah.getStatus().equalsIgnoreCase("09")){
            setData();
            tv_nomor_rekening.setText("Tidak ada data");
            tv_saldo_diblokir.setText("Tidak ada data");
            tv_saldo_nasabah.setText("Tidak ada data");
        }
        else{
            AppUtil.notiferror(DetailMonitoringNasabahActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan saat mendapatkan data nasabah");
        }

        bt_lihat_riwayat_mutasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dataNasabah.getNoRekening()!=null&&!dataNasabah.getNoRekening().isEmpty()){
                    Intent intent=new Intent(DetailMonitoringNasabahActivity.this,ActivityRiwayatMutasi.class);
                    intent.putExtra("noRekening",dataNasabah.getNoRekening());
                    startActivity(intent);
                }
                else{
                    AppUtil.notiferror(DetailMonitoringNasabahActivity.this, findViewById(android.R.id.content), "Data Rekening Nasabah Tidak Ditemukan");
                }

            }
        });


    }

    private void setData(){
        tv_nama_nasabah.setText(dataNasabah.getNamaNasabah());

        tv_produk_nasabah.setText(dataNasabah.getNamaProduk());

        tv_plafond_awal_nasabah.setText(AppUtil.parseRupiah(dataNasabah.getPlafondAwal()));

        tv_dpk_nasabah.setText(AppUtil.parseRupiah(dataNasabah.getDpk()));

        tv_outstanding_nasabah.setText(AppUtil.parseRupiah(dataNasabah.getOs()));

        tv_tanggaljatuhtempo.setText(AppUtil.parseTanggalGeneral(dataNasabah.getTglJtTempo(), "ddMMyyyy", "dd-MM-yyyy"));

        tv_tanggal_pencairan.setText(AppUtil.parseTanggalGeneral(dataNasabah.getTglRealisasi(), "ddMMyyyy", "dd-MM-yyyy"));

        tv_kol_nasabah.setText(dataNasabah.getKol());

        tv_nomor_rekening.setText(dataNasabah.getNoRekening());

        tv_jangka_waktu_nasabah.setText(dataNasabah.getJangkaWaktu()+" Bulan");

        tv_nomor_telepon_nasabah.setText(dataNasabah.getNoTelp());

        tv_alamat_nasabah.setText(dataNasabah.getAlamat());

        tv_angsuran_nasabah.setText(AppUtil.parseRupiah(dataNasabah.getNominalAngsuran()));

        tv_tunggakan_nasabah.setText(AppUtil.parseRupiah(dataNasabah.getTunggakan()));




        //day past due
        if(dataNasabah.getDayPastDue()!=null&&!dataNasabah.getDayPastDue().equalsIgnoreCase("0")&&!dataNasabah.getDayPastDue().isEmpty()){
            ll_day_past_due.setVisibility(View.VISIBLE);
            tv_day_past_due.setText(dataNasabah.getDayPastDue()+" Hari");

            ll_tanggal_past_due.setVisibility(View.VISIBLE);
            tv_tanggal_past_due.setText(AppUtil.parseTanggalGeneral(dataNasabah.getTanggalPastDue().substring(0,10), "yyyy-MM-dd", "dd-MM-yyyy"));
        }
        else{
            ll_day_past_due.setVisibility(View.GONE);
            ll_tanggal_past_due.setVisibility(View.GONE);
        }

        //sisa durasi
        if(dataNasabah.getSisaDurasi()!=null&&!dataNasabah.getSisaDurasi().isEmpty()&&dataNasabah.getSisaDurasi()!="0"&&Integer.parseInt(dataNasabah.getSisaDurasi())>0){

            int tahunDurasi = Integer.parseInt(dataNasabah.getSisaDurasi())/365;

//            Log.d("sisaTahun",String.valueOf(calOne.get(Calendar.DAY_OF_YEAR)));
            int sisaTahun=Integer.parseInt(dataNasabah.getSisaDurasi())%365;

            int bulanDurasi =sisaTahun/30;
            int sisaBulan=sisaTahun%30;
            String tahun="";
            String bulan="";
            String hari="";

            if(tahunDurasi!=0){
                tahun=String.valueOf(tahunDurasi)+ " Tahun ";
            }

            if(bulanDurasi!=0){
                bulan=String.valueOf(bulanDurasi)+ " Bulan ";
            }

            if(sisaBulan!=0){
                hari=String.valueOf(sisaBulan)+ " Hari ";
            }
            tv_sisa_durasi.setText(tahun+bulan+hari+"("+dataNasabah.getSisaDurasi()+" Sisa Hari)");
        }
        else{
            ll_sisa_durasi.setVisibility(GONE);
        }


    }

    public void loadData(){
        //  dataKcp = getListUser();
        // progressbar_loading.setVisibility(View.VISIBLE);
//        loading.setVisibility(View.VISIBLE);
        ReqSaldoNasabah req = new ReqSaldoNasabah();

        //pantekan no rekening
//        req.setNoRekening("1033851495");
//        Toast.makeText(this, "Masih ada hardcode nomor rekening nasabah", Toast.LENGTH_SHORT).show();
        req.setNoRekening(dataNasabah.getNoRekening());


        //conditioning list yang ditampilkan
        call = apiClientAdapter.getApiInterface().getSaldoNasabah(req);

        call.enqueue(new Callback<ParseResponseSaldo>() {
            @Override
            public void onResponse(Call<ParseResponseSaldo> call, Response<ParseResponseSaldo> response) {
                // progressbar_loading.setVisibility(View.GONE);
                loading.setVisibility(GONE);
                if(response.isSuccessful()){

                    //tutorial overlay fitur sumary kalau data sukses
//                    AppUtil.tutorialOverlay(PerformanceAoActivity.this,bt_tampil_summary,"Sekarang anda dapat melihat summary performance pembiayaan","tutorial_summary_performance");

                    if(response.body().getStatus().equalsIgnoreCase("200")){

                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<SaldoNasabah>() {}.getType();
                        dataSaldo = gson.fromJson(listDataString, type);



                        if(dataSaldo==null){
                            tv_saldo_diblokir.setText("Gagal mendapatkan data");
                            tv_saldo_nasabah.setText("Gagal mendapatkan data");
                        }
                        else{
                            tv_saldo_nasabah.setText(dataSaldo.getSaldo_tersedia());
                            tv_saldo_diblokir.setText(dataSaldo.getSaldo_yang_diblokir());
//                            tv_nomor_rekening.setText(dataSaldo.getNoRekening());
                        }
                    }
                    else if(response.body().getStatus().equalsIgnoreCase("404")){
                        AppUtil.notiferror(DetailMonitoringNasabahActivity.this, findViewById(android.R.id.content), "Data Saldo Tidak Ditemukan");
                        tv_saldo_diblokir.setText("Tidak ada data");
                        tv_saldo_nasabah.setText("Tidak ada data");
                    }
                    else{
                        AppUtil.notiferror(DetailMonitoringNasabahActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        tv_saldo_diblokir.setText("Tidak ada data");
                        tv_saldo_nasabah.setText("Tidak ada data");
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseSaldo> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
                AppUtil.notiferror(DetailMonitoringNasabahActivity.this, findViewById(android.R.id.content), "Terjadi kesalahan pada server");
            }
        });
    }






}
