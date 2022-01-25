package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.simulasi_angsuran;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqHitungKalkulator;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqInquery;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MParseArray;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseDataPembiayaan;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseJadwalAngsuran;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseSimulasiBiayaBiaya;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseSimulasiInqCal;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.FlagAplikasiPojo;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityKalkulatorSimulasiAngsuranBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KalkulatorActivity extends AppCompatActivity implements GenericListenerOnSelect, View.OnClickListener, ConfirmListener {
    private ActivityKalkulatorSimulasiAngsuranBinding binding;
    List<MGenericModel> dataDropdownKalkulator = new ArrayList<>(), dataAsuransi = new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    MparseResponseSimulasiInqCal DPSimulasi;
    MparseResponseSimulasiBiayaBiaya DPSimulasiBiayaBiaya;
    MparseResponseDataPembiayaan DPDataPembiayaan;
    List<MparseResponseJadwalAngsuran> DPJadwalAngsuran;
    Long idAplikasi;
    String hitung,statusId;
    Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialixxze List
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);

        binding = ActivityKalkulatorSimulasiAngsuranBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Kalkulator");

        if(getIntent().hasExtra("statusId")){
            statusId=getIntent().getStringExtra("statusId");
        }

        if(getIntent().hasExtra("idAplikasi")){
            idAplikasi=Long.parseLong(getIntent().getStringExtra("idAplikasi"));
        }

        setParameterDropdown();
        allOnClick();
        onClickEndIcon();
        disableEditText();
        initdata();
        binding.tfBiayaFlagging.setVisibility(View.GONE);
        binding.tfBiayaMitraFronting.setVisibility((View.GONE));
        binding.etPlafondYangDibutuhkan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etPlafondYangDibutuhkan));
        binding.etBiayaAsuransi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etBiayaAsuransi));
        binding.etBiayaAsuransiKhusus.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etBiayaAsuransiKhusus));
        binding.etBiayaAdministrasi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etBiayaAdministrasi));
        binding.etBiayapenaltiKhususTakeover.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etBiayapenaltiKhususTakeover));
        binding.etMaxAngsuran.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etMaxAngsuran));
        binding.etBiayamaterai.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etBiayamaterai));

    }

    private void initdata() {

        //pantekan khusus biaya asuransi dan biaya materai
        binding.etTreatmentBiayaAdministrasi.setText("Bayar Nasabah");
        binding.etTreatmentBiayaMaterai.setText("Bayar Nasabah");

        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        EmptyRequest emptyRequest = new EmptyRequest();

        //Inq PickList
        Call<MParseArray> calli = apiClientAdapter.getApiInterface().inqListAsusransi(emptyRequest);
        calli.enqueue(new Callback<MParseArray>() {
            @Override
            public void onResponse(@NonNull Call<MParseArray> call, @NonNull Response<MParseArray> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        dataAsuransi.add(new MGenericModel(response.body().getData().get(i).getAsJsonObject().get("Id").toString(), response.body().getData().get(i).getAsJsonObject().get("Nama_Asuransi").getAsString()));
                    }
                } else {
                    AppUtil.notiferror(KalkulatorActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                }
            }

            @Override
            public void onFailure(@NonNull Call<MParseArray> call, @NonNull Throwable t) {
                AppUtil.notiferror(KalkulatorActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
        //Inq Kalkulator
        ReqInquery req = new ReqInquery();
        req.setApplicationId(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
        req.setUID(Integer.toString(appPreferences.getUid()));
        Call<ParseResponseAgunan> call=null;

        if(statusId.equalsIgnoreCase("d.3")){
            call = apiClientAdapter.getApiInterface().inquiryKalkulatorMarketing(req);
        }
        else if(statusId.equalsIgnoreCase("d.5")){
            call = apiClientAdapter.getApiInterface().inqKalkulatorMarketingD5(req);
        }
        else if(statusId.equalsIgnoreCase("d.6")){
            call = apiClientAdapter.getApiInterface().inqKalkulatorD6(req);
        }

        //UNTUK SEMENTARA  KALO STATUSNYA DILUAR D3 D5, DIAMBIL DARI D3 DLU
        else{
            call = apiClientAdapter.getApiInterface().inquiryKalkulatorMarketing(req);
        }

        call.enqueue(new Callback<ParseResponseAgunan>() {
            @Override
            public void onResponse(@NonNull Call<ParseResponseAgunan> call, @NonNull Response<ParseResponseAgunan> response) {
                String SSSimulasi = "", SSSimulasiBiayaBiaya = "", SSDataPembiayaan = "", SSJadwalAngsuran = "";
                if (response.isSuccessful()) {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    if (response.body().getStatus().equalsIgnoreCase("00") && response.body().getData() != null) {
                        Gson gson = new Gson();
                        if (response.body().getData().get("SimulasiInqCal") != null) {
                            SSSimulasi = response.body().getData().get("SimulasiInqCal").getAsJsonObject().toString();
                            DPSimulasi = gson.fromJson(SSSimulasi, MparseResponseSimulasiInqCal.class);
                            if (DPSimulasi.getPv() != null)
                                binding.etPlafondYangDibutuhkan.setText(AppUtil.parseNumberWatcher(String.valueOf(DPSimulasi.getPv())));
                            if (DPSimulasi.getTerm() != null)
                                binding.etJangkaWaktu.setText(String.valueOf(DPSimulasi.getTerm()));

                            if (DPSimulasi.getPricingPensiun() != null)
                                binding.etPricingPensiun.setText(String.valueOf(DPSimulasi.getPricingPensiun()));
                            if (DPSimulasi.getPricingPrapen() != null)
                                binding.etPricingPraPensiun.setText(String.valueOf(DPSimulasi.getPricingPrapen()));

                            if (DPSimulasi.getBatasAtasAngsuranPrapen() != null)
                                binding.etMaxAngsuran.setText(AppUtil.parseNumberWatcher(String.valueOf(DPSimulasi.getBatasAtasAngsuranPrapen())));

                            if (DPSimulasi.getPricingPensiunAfter() != null)
                                binding.tvPricingAtribusiPensiun.setText(AppUtil.parseNumberWatcher(String.valueOf(DPSimulasi.getPricingPensiunAfter())) + "%");
                            if (DPSimulasi.getPricingPrapenAfter() != null)
                                binding.tvPricingAtribusiPrapen.setText(AppUtil.parseNumberWatcher(String.valueOf(DPSimulasi.getPricingPrapenAfter())) + "%");
                        }
                        if (response.body().getData().get("SimulasiBiayaBiaya") != null) {
                            SSSimulasiBiayaBiaya = response.body().getData().get("SimulasiBiayaBiaya").getAsJsonObject().toString();
                            DPSimulasiBiayaBiaya = gson.fromJson(SSSimulasiBiayaBiaya, MparseResponseSimulasiBiayaBiaya.class);
                            if (DPSimulasiBiayaBiaya.getAsuransiPenjaminId() != null) {
                                binding.etPilihanAsuransiPenjaminan.setText(DPSimulasiBiayaBiaya.getNamaAsuransi());
                                id = DPSimulasiBiayaBiaya.getAsuransiPenjaminId();
                            }
                            if (DPSimulasiBiayaBiaya.getBiayaAsuransiPenjamin() != null)
                                binding.etBiayaAsuransi.setText(String.valueOf(DPSimulasiBiayaBiaya.getBiayaAsuransiPenjamin().setScale(2)));
                            if (DPSimulasiBiayaBiaya.getTreatementBiayaAsuransiPenjamin() != null)
                                binding.etTreatmentBiayaAsuransi.setText(DPSimulasiBiayaBiaya.getTreatementBiayaAsuransiPenjamin());
                            if (DPSimulasiBiayaBiaya.getBiayaAsuransiKhusus() != null)
                                binding.etBiayaAsuransiKhusus.setText(String.valueOf(DPSimulasiBiayaBiaya.getBiayaAsuransiKhusus()));
                            if (DPSimulasiBiayaBiaya.getTreatmentBiayaAsuransiKhusus() != null)
                                binding.etTreatmentBiayaAsuransiKhusus.setText(DPSimulasiBiayaBiaya.getTreatmentBiayaAsuransiKhusus());
                            if (DPSimulasiBiayaBiaya.getBiayaAdministrasi() != null)
                                binding.etBiayaAdministrasi.setText(String.valueOf(DPSimulasiBiayaBiaya.getBiayaAdministrasi().setScale(2)));
//                            if (DPSimulasiBiayaBiaya.getTreatementBiayaAdministrasi() != null)
//                                binding.etTreatmentBiayaAdministrasi.setText(DPSimulasiBiayaBiaya.getTreatementBiayaAdministrasi());
                            if (DPSimulasiBiayaBiaya.getBiayaPenalti() != null)
                                binding.etBiayapenaltiKhususTakeover.setText(String.valueOf(DPSimulasiBiayaBiaya.getBiayaPenalti()));
                            if (DPSimulasiBiayaBiaya.getTreatmentBiayaPenalti() != null)
                                binding.etTreatmentBiayaPenalti.setText(DPSimulasiBiayaBiaya.getTreatmentBiayaPenalti());
//                            if (DPSimulasiBiayaBiaya.getBiayaMaterai() != null)
//                                binding.etBiayamaterai.setText(String.valueOf(DPSimulasiBiayaBiaya.getBiayaMaterai()));
                            if (DPSimulasiBiayaBiaya.getTreatmentBiayaMaterai() != null)
                                binding.etTreatmentBiayaMaterai.setText(String.valueOf(DPSimulasiBiayaBiaya.getTreatmentBiayaMaterai()));
                        }
                        if (response.body().getData().get("DataPembiayaan") != null) {
                            SSDataPembiayaan = response.body().getData().get("DataPembiayaan").getAsJsonObject().toString();
                            DPDataPembiayaan = gson.fromJson(SSDataPembiayaan, MparseResponseDataPembiayaan.class);
                            if (DPDataPembiayaan.getMonthInstalmentPrapen() != null)
                                binding.tvAngsuranBulananPrapen.setText("Rp. " + AppUtil.parseNumberWatcher(String.valueOf(DPDataPembiayaan.getMonthInstalmentPrapen())));
                            if (DPDataPembiayaan.getTotalAttributions() != null)
                                binding.tvTotalAttribusi.setText("Rp. " + AppUtil.parseNumberWatcher(String.valueOf(DPDataPembiayaan.getTotalAttributions())));
                            if (DPDataPembiayaan.getTotalCost() != null)
                                binding.tvTotalBiaya.setText("Rp. " + AppUtil.parseNumberWatcher(String.valueOf(DPDataPembiayaan.getTotalCost())));
                            if (DPDataPembiayaan.getMonthInstalmentPensiun() != null)
                                binding.tvAngsuranBulananPensiun.setText("Rp. " + AppUtil.parseNumberWatcher(String.valueOf(DPDataPembiayaan.getMonthInstalmentPensiun())));
                        }
                        if (response.body().getData().get("JadwalAngsuran") != null) {
                            SSJadwalAngsuran = response.body().getData().get("JadwalAngsuran").toString();
                            Type type = new TypeToken<List<MparseResponseJadwalAngsuran>>() {
                            }.getType();
                            DPJadwalAngsuran = gson.fromJson(SSJadwalAngsuran, type);
                        }
                    } else {
                        AppUtil.notiferror(KalkulatorActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ParseResponseAgunan> call, @NonNull Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(KalkulatorActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void onClickEndIcon() {
        binding.tfPilihanasuransipenjaminan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfPilihanasuransipenjaminan.getLabelText(), dataAsuransi, KalkulatorActivity.this);
            }
        });
        binding.tfTreatmentBiayaAsuransi.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfTreatmentBiayaAsuransi.getLabelText(), dataDropdownKalkulator, KalkulatorActivity.this);
            }
        });
        binding.tfTreatmentBiayaAsuransiKhusus.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfTreatmentBiayaAsuransiKhusus.getLabelText(), dataDropdownKalkulator, KalkulatorActivity.this);
            }
        });
        binding.tfTreatmentBiayaAdministrasi.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfTreatmentBiayaAdministrasi.getLabelText(), dataDropdownKalkulator, KalkulatorActivity.this);
            }
        });
        binding.tfTreatmentBiayaPenalti.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfTreatmentBiayaPenalti.getLabelText(), dataDropdownKalkulator, KalkulatorActivity.this);
            }
        });
        binding.tfTreatmentBiayaMaterai.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfTreatmentBiayaMaterai.getLabelText(), dataDropdownKalkulator, KalkulatorActivity.this);
            }
        });
    }


    private void setParameterDropdown() {
        //dropdown kalkulator
        dataDropdownKalkulator.add(new MGenericModel("Bayar Nasabah", "Bayar Nasabah"));
        dataDropdownKalkulator.add(new MGenericModel("Atribusi", "Atribusi"));
        dataDropdownKalkulator.add(new MGenericModel("Potong Pencairan", "Potong Pencairan"));
        dataDropdownKalkulator.add(new MGenericModel("Tidak Digunakan", "Tidak Digunakan"));


    }

    @Override
    public void onSelect(String title, MGenericModel data) {
        if (title.equalsIgnoreCase(binding.tfTreatmentBiayaAsuransi.getLabelText())) {
            binding.etTreatmentBiayaAsuransi.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfTreatmentBiayaAsuransiKhusus.getLabelText())) {
            binding.etTreatmentBiayaAsuransiKhusus.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfTreatmentBiayaAdministrasi.getLabelText())) {
            binding.etTreatmentBiayaAdministrasi.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfTreatmentBiayaPenalti.getLabelText())) {
            binding.etTreatmentBiayaPenalti.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfTreatmentBiayaMaterai.getLabelText())) {
            binding.etTreatmentBiayaMaterai.setText(data.getDESC());
        }
        if (title.equalsIgnoreCase(binding.tfPilihanasuransipenjaminan.getLabelText())) {
            binding.etPilihanAsuransiPenjaminan.setText(data.getDESC());
            id = Long.parseLong(data.getID());
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override

    public void onClick(View view) {
        switch (view.getId()) {

            //treatment_biaya_asuransi
            case R.id.et_treatment_biaya_asuransi:
            case R.id.tf_treatment_biaya_asuransi:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfTreatmentBiayaAsuransi.getLabelText(), dataDropdownKalkulator, KalkulatorActivity.this);
                break;

            //treatment_biaya_asuransi_khusus
            case R.id.et_treatment_biaya_asuransi_khusus:
            case R.id.tf_treatment_biaya_asuransi_khusus:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfTreatmentBiayaAsuransiKhusus.getLabelText(), dataDropdownKalkulator, KalkulatorActivity.this);
                break;

            //treatment_biaya_administrasi
            case R.id.et_treatment_biaya_administrasi:
            case R.id.tf_treatment_biaya_administrasi:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfTreatmentBiayaAdministrasi.getLabelText(), dataDropdownKalkulator, KalkulatorActivity.this);
                break;

            //treatment_biaya_penalti
            case R.id.et_treatment_biaya_penalti:
            case R.id.tf_treatment_biaya_penalti:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfTreatmentBiayaPenalti.getLabelText(), dataDropdownKalkulator, KalkulatorActivity.this);
                break;

            //treatment_biaya_materai
            case R.id.et_treatment_biaya_materai:
            case R.id.tf_treatment_biaya_materai:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfTreatmentBiayaMaterai.getLabelText(), dataDropdownKalkulator, KalkulatorActivity.this);
                break;

            //Button Send
            case R.id.btn_send:
            case R.id.ll_btn_send:
                if (hitung != null)
                    sendData();
                else
                    AppUtil.notiferror(KalkulatorActivity.this, findViewById(android.R.id.content), "Klik Button Hitung Terlebih dahulu");
                break;
            //Button Hitung
            case R.id.btn_hitung:
            case R.id.ll_btn_hitung:
                hitungData();
                break;
            //Button Pilih Asuransi
            case R.id.tf_pilihanasuransipenjaminan:
            case R.id.et_pilihan_asuransi_penjaminan:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfPilihanasuransipenjaminan.getLabelText(), dataAsuransi, KalkulatorActivity.this);
                break;
        }
    }

    private void sendData() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
//        AppUtil.logSecure("SSd",binding.tvAngsuranBulanan.getText().toString().substring(4, binding.tvAngsuranBulanan.getText().length()).replace(",",""));
        ReqHitungKalkulator req = new ReqHitungKalkulator();
        req.setApplicationId(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
        req.setUid(String.valueOf(appPreferences.getUid()));
        req.setSimulasiBiayaBiaya(DPSimulasiBiayaBiaya);
        req.setSimulasiAngsuranCalc(DPSimulasi);
        req.setDataPembiayaan(DPDataPembiayaan);
        req.setJadwalAngsuran(DPJadwalAngsuran);

        Call<ParseResponseAgunan> call=null;


        if(statusId.equalsIgnoreCase("d.3")){
            call = apiClientAdapter.getApiInterface().updateKalkulatorMarketing(req);
        }
        else    if(statusId.equalsIgnoreCase("d.5")){
            call = apiClientAdapter.getApiInterface().updateKalkulatorMarketingD5(req);
        }
        else    if(statusId.equalsIgnoreCase("d.6")){
            call = apiClientAdapter.getApiInterface().updateKalkulatorD6(req);
        }

        //UNTUK SEMENTARA  KALO STATUSNYA DILUAR D4 D5, DIAMBIL DARI D3 DLU
        else{
            call = apiClientAdapter.getApiInterface().updateKalkulatorMarketing(req);
        }
//        call = apiClientAdapter.getApiInterface().updateKalkulatorMarketing(req);
        call.enqueue(new Callback<ParseResponseAgunan>() {
            @Override
            public void onResponse(Call<ParseResponseAgunan> call, Response<ParseResponseAgunan> response) {
                if (response.isSuccessful()) {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
//                        finish();
                        CustomDialog.DialogSuccess(KalkulatorActivity.this, "Success!", "Update Data Kalkulator sukses", KalkulatorActivity.this);

                        //update Flagging
                        Realm realm=Realm.getDefaultInstance();
                        FlagAplikasiPojo dataFlag= realm.where(FlagAplikasiPojo.class).equalTo("idAplikasi", idAplikasi).findFirst();
                        if(!realm.isInTransaction()){
                            realm.beginTransaction();
                        }

                        if(dataFlag!=null){
                            dataFlag.setFlagD3Kalkulator(true);
                            realm.insertOrUpdate(dataFlag);
                        }
                        else{
                            dataFlag=new FlagAplikasiPojo();
                            dataFlag.setIdAplikasi(idAplikasi);
                            dataFlag.setFlagD3Kalkulator(true);
                            realm.insertOrUpdate(dataFlag);
                        }
                        realm.close();

                    } else {
                        AppUtil.notiferror(KalkulatorActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseAgunan> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(KalkulatorActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void hitungData() {
        binding.loading.progressbarLoading.setVisibility(View.VISIBLE);
        MparseResponseSimulasiInqCal scal = new MparseResponseSimulasiInqCal();
        scal.setTerm(Integer.parseInt(binding.etJangkaWaktu.getText().toString()));
        scal.setPv(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etPlafondYangDibutuhkan.getText().toString())));
        scal.setPricingPrapen(Double.parseDouble(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etPricingPraPensiun.getText().toString())));
        scal.setPricingPensiun(Double.parseDouble(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etPricingPensiun.getText().toString())));
        scal.setBatasAtasAngsuranPrapen(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etMaxAngsuran.getText().toString())));
        MparseResponseSimulasiBiayaBiaya sbiaya = new MparseResponseSimulasiBiayaBiaya();
        sbiaya.setAsuransiPenjaminId(id);
        sbiaya.setNamaAsuransi(binding.etPilihanAsuransiPenjaminan.getText().toString());
        sbiaya.setBiayaAsuransiPenjamin(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etBiayaAsuransi.getText().toString())));
        sbiaya.setTreatementBiayaAsuransiPenjamin(binding.etTreatmentBiayaAsuransi.getText().toString());
        sbiaya.setBiayaAsuransiKhusus(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etBiayaAsuransiKhusus.getText().toString())));
        sbiaya.setTreatmentBiayaAsuransiKhusus(binding.etTreatmentBiayaAsuransiKhusus.getText().toString());
        sbiaya.setBiayaAdministrasi(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etBiayaAdministrasi.getText().toString())));
        sbiaya.setTreatementBiayaAdministrasi(binding.etTreatmentBiayaAdministrasi.getText().toString());
        sbiaya.setBiayaPenalti(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etBiayapenaltiKhususTakeover.getText().toString())));
        sbiaya.setTreatmentBiayaPenalti(binding.etTreatmentBiayaPenalti.getText().toString());
        sbiaya.setBiayaMaterai(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(binding.etBiayamaterai.getText().toString())));
        sbiaya.setTreatmentBiayaMaterai(binding.etTreatmentBiayaMaterai.getText().toString());
        ReqHitungKalkulator req = new ReqHitungKalkulator();
        req.setApplicationId(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
        req.setUid(String.valueOf(appPreferences.getUid()));
        req.setSimulasiBiayaBiaya(sbiaya);
        req.setSimulasiAngsuranCalc(scal);
        req.setProcessFlow(statusId);
        req.setDataPembiayaan(new MparseResponseDataPembiayaan());
        Call<ParseResponseAgunan> call = apiClientAdapter.getApiInterface().SendHitungBiayadanAngsuran(req);
        call.enqueue(new Callback<ParseResponseAgunan>() {
            @Override
            public void onResponse(Call<ParseResponseAgunan> call, Response<ParseResponseAgunan> response) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                String SSSimulasi = "", SSSimulasiBiayaBiaya = "", SSDataPembiayaan = "", SSJadwalAngsuran = "";
                if (response.isSuccessful()) {
                    binding.loading.progressbarLoading.setVisibility(View.GONE);
                    if (response.body().getStatus().equalsIgnoreCase("00") && response.body().getData() != null) {
                        AppUtil.notifsuccess(KalkulatorActivity.this, findViewById(android.R.id.content), "Berhasil Menghitung Kalkulator");
                        hitung = "Selesai Hitung";
                        Gson gson = new Gson();

                        if (response.body().getData().get("SimulasiInqCal") != null) {
                            SSSimulasi = response.body().getData().get("SimulasiInqCal").getAsJsonObject().toString();
                            DPSimulasi = gson.fromJson(SSSimulasi, MparseResponseSimulasiInqCal.class);
                            if (DPSimulasi.getPv() != null)
                                binding.etPlafondYangDibutuhkan.setText(AppUtil.parseNumberWatcher(String.valueOf(DPSimulasi.getPv())));
                            if (DPSimulasi.getTerm() != null)
                                binding.etJangkaWaktu.setText(String.valueOf(DPSimulasi.getTerm()));

                            if (DPSimulasi.getPricingPensiun() != null)
                                binding.etPricingPensiun.setText(String.valueOf(DPSimulasi.getPricingPensiun()));
                            if (DPSimulasi.getPricingPrapen() != null)
                                binding.etPricingPraPensiun.setText(String.valueOf(DPSimulasi.getPricingPrapen()));

                            if (DPSimulasi.getBatasAtasAngsuranPrapen() != null)
                                binding.etMaxAngsuran.setText(AppUtil.parseNumberWatcher(String.valueOf(DPSimulasi.getBatasAtasAngsuranPrapen())));

                            if (DPSimulasi.getPricingPensiunAfter() != null)
                                binding.tvPricingAtribusiPensiun.setText(AppUtil.parseNumberWatcher(String.valueOf(DPSimulasi.getPricingPensiunAfter())) + "%");
                            if (DPSimulasi.getPricingPrapenAfter() != null)
                                binding.tvPricingAtribusiPrapen.setText(AppUtil.parseNumberWatcher(String.valueOf(DPSimulasi.getPricingPrapenAfter())) + "%");
                        }
                        if (response.body().getData().get("SimulasiBiayaBiaya") != null) {
                            SSSimulasiBiayaBiaya = response.body().getData().get("SimulasiBiayaBiaya").getAsJsonObject().toString();
                            DPSimulasiBiayaBiaya = gson.fromJson(SSSimulasiBiayaBiaya, MparseResponseSimulasiBiayaBiaya.class);
                            if (DPSimulasiBiayaBiaya.getAsuransiPenjaminId() != null) {
                                binding.etPilihanAsuransiPenjaminan.setText(DPSimulasiBiayaBiaya.getNamaAsuransi());
                                id = DPSimulasiBiayaBiaya.getAsuransiPenjaminId();
                            }
                            if (DPSimulasiBiayaBiaya.getBiayaAsuransiPenjamin() != null)
                                binding.etBiayaAsuransi.setText(String.valueOf(DPSimulasiBiayaBiaya.getBiayaAsuransiPenjamin().setScale(2)));
                            if (DPSimulasiBiayaBiaya.getTreatementBiayaAsuransiPenjamin() != null)
                                binding.etTreatmentBiayaAsuransi.setText(DPSimulasiBiayaBiaya.getTreatementBiayaAsuransiPenjamin());
                            if (DPSimulasiBiayaBiaya.getBiayaAsuransiKhusus() != null)
                                binding.etBiayaAsuransiKhusus.setText(String.valueOf(DPSimulasiBiayaBiaya.getBiayaAsuransiKhusus()));
                            if (DPSimulasiBiayaBiaya.getTreatmentBiayaAsuransiKhusus() != null)
                                binding.etTreatmentBiayaAsuransiKhusus.setText(DPSimulasiBiayaBiaya.getTreatmentBiayaAsuransiKhusus());
                            if (DPSimulasiBiayaBiaya.getBiayaAdministrasi() != null)
                                binding.etBiayaAdministrasi.setText(String.valueOf(DPSimulasiBiayaBiaya.getBiayaAdministrasi().setScale(2)));
//                            if (DPSimulasiBiayaBiaya.getTreatementBiayaAdministrasi() != null)
//                                binding.etTreatmentBiayaAdministrasi.setText(DPSimulasiBiayaBiaya.getTreatementBiayaAdministrasi());
                            if (DPSimulasiBiayaBiaya.getBiayaPenalti() != null)
                                binding.etBiayapenaltiKhususTakeover.setText(String.valueOf(DPSimulasiBiayaBiaya.getBiayaPenalti()));
                            if (DPSimulasiBiayaBiaya.getTreatmentBiayaPenalti() != null)
                                binding.etTreatmentBiayaPenalti.setText(DPSimulasiBiayaBiaya.getTreatmentBiayaPenalti());
//                            if (DPSimulasiBiayaBiaya.getBiayaMaterai() != null)
//                                binding.etBiayamaterai.setText(String.valueOf(DPSimulasiBiayaBiaya.getBiayaMaterai()));
                            if (DPSimulasiBiayaBiaya.getTreatmentBiayaMaterai() != null)
                                binding.etTreatmentBiayaMaterai.setText(String.valueOf(DPSimulasiBiayaBiaya.getTreatmentBiayaMaterai()));
                        }
                        if (response.body().getData().get("DataPembiayaan") != null) {
                            SSDataPembiayaan = response.body().getData().get("DataPembiayaan").getAsJsonObject().toString();
                            DPDataPembiayaan = gson.fromJson(SSDataPembiayaan, MparseResponseDataPembiayaan.class);
                            if (DPDataPembiayaan.getMonthInstalmentPrapen() != null)
                                binding.tvAngsuranBulananPrapen.setText("Rp. " + AppUtil.parseNumberWatcher(String.valueOf(DPDataPembiayaan.getMonthInstalmentPrapen())));
                            if (DPDataPembiayaan.getTotalAttributions() != null)
                                binding.tvTotalAttribusi.setText("Rp. " + AppUtil.parseNumberWatcher(String.valueOf(DPDataPembiayaan.getTotalAttributions())));
                            if (DPDataPembiayaan.getTotalCost() != null)
                                binding.tvTotalBiaya.setText("Rp. " + AppUtil.parseNumberWatcher(String.valueOf(DPDataPembiayaan.getTotalCost())));
                            if (DPDataPembiayaan.getMonthInstalmentPensiun() != null)
                                binding.tvAngsuranBulananPensiun.setText("Rp. " + AppUtil.parseNumberWatcher(String.valueOf(DPDataPembiayaan.getMonthInstalmentPensiun())));
                        }
                        if (response.body().getData().get("JadwalAngsuran") != null) {
                            SSJadwalAngsuran = response.body().getData().get("JadwalAngsuran").toString();
                            Type type = new TypeToken<List<MparseResponseJadwalAngsuran>>() {
                            }.getType();
                            DPJadwalAngsuran = gson.fromJson(SSJadwalAngsuran, type);
                        }

                    }
                    else  if (response.body().getStatus().equalsIgnoreCase("00") && response.body().getData() == null) {
                        AppUtil.notiferror(KalkulatorActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                    else {
                        AppUtil.notiferror(KalkulatorActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseAgunan> call, Throwable t) {
                binding.loading.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(KalkulatorActivity.this, findViewById(android.R.id.content), t.getMessage());
            }
        });
    }

    private void disableEditText() {
        binding.etPilihanAsuransiPenjaminan.setFocusable(false);
        binding.etTreatmentBiayaAsuransi.setFocusable(false);
        binding.etTreatmentBiayaAsuransiKhusus.setFocusable(false);
        binding.etTreatmentBiayaAdministrasi.setFocusable(false);
        binding.etTreatmentBiayaPenalti.setFocusable(false);
        binding.etTreatmentBiayaMaterai.setFocusable(false);
        binding.etBiayaAsuransi.setFocusable(false);
        binding.etBiayaAdministrasi.setFocusable(false);
        binding.etBiayaFlagging.setFocusable(false);
        binding.etBiayaMitraFronting.setFocusable(false);

    }

    private void allOnClick() {
        binding.tfTreatmentBiayaAsuransi.setOnClickListener(this);
        binding.etTreatmentBiayaAsuransi.setOnClickListener(this);
        binding.tfTreatmentBiayaAsuransiKhusus.setOnClickListener(this);
        binding.etTreatmentBiayaAsuransiKhusus.setOnClickListener(this);
//        binding.tfTreatmentBiayaAdministrasi.setOnClickListener(this);
//        binding.etTreatmentBiayaAdministrasi.setOnClickListener(this);
        binding.tfTreatmentBiayaPenalti.setOnClickListener(this);
        binding.etTreatmentBiayaPenalti.setOnClickListener(this);
//        binding.tfTreatmentBiayaMaterai.setOnClickListener(this);
//        binding.etTreatmentBiayaMaterai.setOnClickListener(this);
        binding.tfPilihanasuransipenjaminan.setOnClickListener(this);
        binding.etPilihanAsuransiPenjaminan.setOnClickListener(this);
        binding.btnHitung.setOnClickListener(this);
        binding.btnSend.setOnClickListener(this);
        binding.llBtnHitung.setOnClickListener(this);
        binding.llBtnSend.setOnClickListener(this);
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public void success(boolean val) {
        finish();
    }

    @Override
    public void confirm(boolean val) {

    }
}

