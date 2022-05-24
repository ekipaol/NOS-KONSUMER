package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g1_akad_dan_asesoir.persiapan_akad;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDownloadFile;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.ParseResponseFile;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityPersiapanAkadBinding;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.R;

import java.util.Objects;

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
        showDownloaded();
        onclickSelectDialog();
        setContentView(view);
        AppUtil.toolbarRegular(this, "Dokumen Persiapan Akad");

    }

    private void showDownloaded() {
        if (Objects.requireNonNull(getIntent().getStringExtra("akad")).equalsIgnoreCase("mmq")) {
            binding.akadmmq.setVisibility(View.VISIBLE);
            binding.akadijarah.setVisibility(View.GONE);
            binding.akadmurabahah.setVisibility(View.GONE);
            binding.akadrahn.setVisibility(View.GONE);
        } else if (Objects.requireNonNull(getIntent().getStringExtra("akad")).equalsIgnoreCase("ijarah")) {
            binding.akadmmq.setVisibility(View.GONE);
            binding.akadijarah.setVisibility(View.VISIBLE);
            binding.akadmurabahah.setVisibility(View.GONE);
            binding.akadrahn.setVisibility(View.GONE);
        } else if (Objects.requireNonNull(getIntent().getStringExtra("akad")).equalsIgnoreCase("murabahah")) {
            binding.akadmmq.setVisibility(View.GONE);
            binding.akadijarah.setVisibility(View.GONE);
            binding.akadmurabahah.setVisibility(View.VISIBLE);
            binding.akadrahn.setVisibility(View.GONE);
        } else if (Objects.requireNonNull(getIntent().getStringExtra("akad")).equalsIgnoreCase("rahn")) {
            binding.akadmmq.setVisibility(View.GONE);
            binding.akadijarah.setVisibility(View.GONE);
            binding.akadmurabahah.setVisibility(View.GONE);
            binding.akadrahn.setVisibility(View.VISIBLE);
        }
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
                downloadFile("dokumenSK_" +  getIntent().getStringExtra("namaNasabah") + ".docx", apiClientAdapter.getApiInterface().DownloadTandaTerimaSK(req));
                break;
            case R.id.btn_akad_ijarah:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("dokumenAkadIjarah_"+ getIntent().getStringExtra("namaNasabah")+".docx", apiClientAdapter.getApiInterface().DownloadAkadIjarah(req));
                break;
            case R.id.btn_sup:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("dokumenSUP_"+ getIntent().getStringExtra("namaNasabah")+".docx", apiClientAdapter.getApiInterface().DownloadSUP(req));
                break;
            case R.id.btn_akad_mmq:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("dokumenAkadMMQ_"+ getIntent().getStringExtra("namaNasabah")+".docx", apiClientAdapter.getApiInterface().DownloadAkadMMQ(req));
                break;
            case R.id.btn_akad_murabahah:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("dokumenAkadMurabahah_"+ getIntent().getStringExtra("namaNasabah")+".docx", apiClientAdapter.getApiInterface().DownloadAkadMurabahah(req));
                break;
            case R.id.btn_akad_rahn:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("dokumenAkadRahn_"+ getIntent().getStringExtra("namaNasabah")+".docx", apiClientAdapter.getApiInterface().DownloadAkadRahn(req));
                break;
            case R.id.btn_mutasi:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("dokumenFormMutasi_"+ getIntent().getStringExtra("namaNasabah")+".docx", apiClientAdapter.getApiInterface().DownloadFormMutasi(req));
                break;
            case R.id.btn_kuasa:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("dokumenLampiranSuratKuasa_"+ getIntent().getStringExtra("namaNasabah")+".docx", apiClientAdapter.getApiInterface().DownloadLampiranSuratKuasa(req));
                break;
            case R.id.btn_pernyataan:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("dokumenLampiranSuratPernyataan_"+ getIntent().getStringExtra("namaNasabah")+".docx", apiClientAdapter.getApiInterface().DownloadLampiranSuratPernyataan(req));
                break;
            case R.id.btn_akad_wakalah:
            case R.id.btn_akad_wakalah_Ijarah:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("dokumenakadwakalah_"+ getIntent().getStringExtra("namaNasabah")+".docx", apiClientAdapter.getApiInterface().DownloadWakalah(req));
                break;
            case R.id.btn_po:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("dokumenPurchaseMurabahah_"+ getIntent().getStringExtra("namaNasabah")+".docx", apiClientAdapter.getApiInterface().DownloadPurchaseMurabahah(req));
                break;
            case R.id.btn_po_Ijarah:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("dokumenPurchaseIjarah_"+ getIntent().getStringExtra("namaNasabah")+".docx", apiClientAdapter.getApiInterface().DownloadPurchaseIjarah(req));
                break;
            case R.id.btn_jadwal_angsuran:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("dokumenLampiranAngsMur_"+ getIntent().getStringExtra("namaNasabah")+".docx", apiClientAdapter.getApiInterface().DownloadLampiranAngsMur(req));
                break;
            case R.id.btn_angsuran_ujrah:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("dokumenJadwalAngsuranUjrah_"+ getIntent().getStringExtra("namaNasabah")+".docx", apiClientAdapter.getApiInterface().DownloadJadwalAngsuranUjrah(req));
                break;
            case R.id.btn_jadwal_angsuran_rahn:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("dokumenJadwalAngsuranRahn_"+ getIntent().getStringExtra("namaNasabah")+".docx", apiClientAdapter.getApiInterface().DownloadJdwlAngsRahn(req));
                break;
            case R.id.btn_jadwal_pengambilalihan:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("dokumenJadwalPengambilAlihan_"+ getIntent().getStringExtra("namaNasabah")+".docx", apiClientAdapter.getApiInterface().DownloadJadwalPengambilAlihan(req));
                break;
            case R.id.btn_asset_mmq:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("dokumenLaporanPenilaianAset_"+ getIntent().getStringExtra("namaNasabah")+".docx", apiClientAdapter.getApiInterface().DownloadLaporanPenilaianAset(req));
                break;
            case R.id.btn_terima_barang:
                req.setUid(String.valueOf(appPreferences.getUid()));
                req.setApplicationId(Long.parseLong(getIntent().getStringExtra("idAplikasi")));
                downloadFile("dokumenSuratTandaTerima_"+ getIntent().getStringExtra("namaNasabah")+".docx", apiClientAdapter.getApiInterface().DownloadSuratTandaTerima(req));
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
                        AppUtil.convertBase64ToFileAutoOpenDocx(PersiapanAkadActivity.this, response.body().getBase64(), namafile);
                } else {
                    AppUtil.notiferror(PersiapanAkadActivity.this, findViewById(android.R.id.content), response.body().getStatusMsg());
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
