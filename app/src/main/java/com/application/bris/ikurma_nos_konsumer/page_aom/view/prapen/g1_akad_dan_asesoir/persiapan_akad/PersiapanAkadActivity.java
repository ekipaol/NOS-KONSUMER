package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g1_akad_dan_asesoir.persiapan_akad;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDownloadFile;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.ParseResponseFile;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityPersiapanAkadBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_dsr_dbr.Activity_DSR_DBR_Nasabah;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersiapanAkadActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityPersiapanAkadBinding binding;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        binding = ActivityPersiapanAkadBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        onclickSelectDialog();
        setContentView(view);
        AppUtil.toolbarRegular(this, "Dokumen Persiapan Akad");
    }

    private void onclickSelectDialog() {
        binding.btnSend.setOnClickListener(this);
        binding.llBtnSend.setOnClickListener(this);
        binding.btnSk.setOnClickListener(this);
        binding.btnAkadIjarah.setOnClickListener(this);
        binding.btnSup.setOnClickListener(this);
        binding.btnAkadMmq.setOnClickListener(this);
        binding.btnAkadMurabahah.setOnClickListener(this);
        binding.btnAkadRahn.setOnClickListener(this);
        binding.btnMutasi.setOnClickListener(this);
        binding.btnKuasa.setOnClickListener(this);
        binding.btnPernyataan.setOnClickListener(this);
        binding.btnAkadWakalah.setOnClickListener(this);
        binding.btnAkadWakalahIjarah.setOnClickListener(this);
        binding.btnPo.setOnClickListener(this);
        binding.btnPoIjarah.setOnClickListener(this);
        binding.btnJadwalAngsuran.setOnClickListener(this);
        binding.btnJadwalAngsuranRahn.setOnClickListener(this);
        binding.btnJadwalPengambilalihan.setOnClickListener(this);
        binding.btnAkadWakalahIjarah.setOnClickListener(this);
        binding.btnAssetMmq.setOnClickListener(this);
        binding.btnTerimaBarang.setOnClickListener(this);
        binding.btnAngsuranUjrah.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        ReqDownloadFile req = new ReqDownloadFile();
        switch (v.getId()) {
            case R.id.btn_send:
            case R.id.ll_btn_send:
                finish();
                break;
            case R.id.btn_sk:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("sk.pdf", apiClientAdapter.getApiInterface().DownloadTandaTerimaSK(req));
                break;
            case R.id.btn_akad_ijarah:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("AkadIjarah.pdf", apiClientAdapter.getApiInterface().DownloadAkadIjarah(req));
                break;
            case R.id.btn_sup:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("SUP.pdf", apiClientAdapter.getApiInterface().DownloadSUP(req));
                break;
            case R.id.btn_akad_mmq:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("AkadMMQ.pdf", apiClientAdapter.getApiInterface().DownloadAkadMMQ(req));
                break;
            case R.id.btn_akad_murabahah:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("AkadMurabahah.pdf", apiClientAdapter.getApiInterface().DownloadAkadMurabahah(req));
                break;
            case R.id.btn_akad_rahn:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("AkadRahn.pdf", apiClientAdapter.getApiInterface().DownloadAkadRahn(req));
                break;
            case R.id.btn_mutasi:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("FormMutasi.pdf", apiClientAdapter.getApiInterface().DownloadFormMutasi(req));
                break;
            case R.id.btn_kuasa:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("LampiranSuratKuasa.pdf", apiClientAdapter.getApiInterface().DownloadLampiranSuratKuasa(req));
                break;
            case R.id.btn_pernyataan:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("LampiranSuratPernyataan.pdf", apiClientAdapter.getApiInterface().DownloadLampiranSuratPernyataan(req));
                break;
            case R.id.btn_akad_wakalah:
            case R.id.btn_akad_wakalah_Ijarah:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("akadwakalah.pdf", apiClientAdapter.getApiInterface().DownloadWakalah(req));
                break;
            case R.id.btn_po:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("PurchaseMurabahah.pdf", apiClientAdapter.getApiInterface().DownloadPurchaseMurabahah(req));
                break;
            case R.id.btn_po_Ijarah:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("PurchaseIjarah.pdf", apiClientAdapter.getApiInterface().DownloadPurchaseIjarah(req));
                break;
            case R.id.btn_jadwal_angsuran:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("LampiranAngsMur.pdf", apiClientAdapter.getApiInterface().DownloadLampiranAngsMur(req));
                break;
            case R.id.btn_angsuran_ujrah:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("JadwalAngsuranUjrah.pdf", apiClientAdapter.getApiInterface().DownloadJadwalAngsuranUjrah(req));
                break;
            case R.id.btn_jadwal_angsuran_rahn:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("JdwlAngsRahn.pdf", apiClientAdapter.getApiInterface().DownloadJdwlAngsRahn(req));
                break;
            case R.id.btn_jadwal_pengambilalihan:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("JadwalPengambilAlihan.pdf", apiClientAdapter.getApiInterface().DownloadJadwalPengambilAlihan(req));
                break;
            case R.id.btn_asset_mmq:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("LaporanPenilaianAset.pdf", apiClientAdapter.getApiInterface().DownloadLaporanPenilaianAset(req));
                break;
            case R.id.btn_terima_barang:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("SuratTandaTerima.pdf", apiClientAdapter.getApiInterface().DownloadSuratTandaTerima(req));
                break;
        }
    }

    private void downloadFile(String namafile, Call<ParseResponseFile> api) {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        Call<ParseResponseFile> call = api;
        call.enqueue(new Callback<ParseResponseFile>() {
            @Override
            public void onResponse(Call<ParseResponseFile> call, Response<ParseResponseFile> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                if (response.body().getStatus().equalsIgnoreCase("00")) {
                    if (response.body().getBase64() != null)
                    AppUtil.convertBase64ToFileAutoOpen(PersiapanAkadActivity.this, response.body().getBase64(), namafile);
                }else{
                    AppUtil.notiferror(PersiapanAkadActivity.this,findViewById(android.R.id.content),response.body().getStatusMsg());
                }
            }

            @Override
            public void onFailure(Call<ParseResponseFile> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(PersiapanAkadActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));

            }
        });
    }
}
