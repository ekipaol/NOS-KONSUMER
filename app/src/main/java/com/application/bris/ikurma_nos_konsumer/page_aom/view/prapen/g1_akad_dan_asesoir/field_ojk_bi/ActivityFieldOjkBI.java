package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g1_akad_dan_asesoir.field_ojk_bi;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqInquery;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.SearchSekEkonomi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateDataOjkBi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityFieldOjkBiBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DropdownGlobalPrapen;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DropdownOJK;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogListSektorEkonomi;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogSektorEkonomi;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.SektorEkonomiListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MsektorEkonomi;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.sektorekonomi.SektorEkonomiActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class ActivityFieldOjkBI extends AppCompatActivity implements View.OnClickListener, GenericListenerOnSelect, SektorEkonomiListener {
    private ActivityFieldOjkBiBinding binding;
    List<MGenericModel> dataDropdownGraduate = new ArrayList<>(), dataDropdownKodeBUTK = new ArrayList<>(), dataDropdownJepen = new ArrayList<>();
    List<MGenericModel> dataDropdownKodePekerjaan = new ArrayList<>();
    List<DropdownOJK> dropdownSektorEkonomi = new ArrayList<>();
    UpdateDataOjkBi dataOjk;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String title;
    public static int idAplikasi;
    private boolean adaFieldBelumDiisi =false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        binding = ActivityFieldOjkBiBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);
        disableEditText();
        endIconClick();
        setParameterDropdown();
        onclickSelectDialog();
        allOnclick();
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "OJK / BI");
        loadDropdownKodePekerjaan();
        loadData();
    }

    private void setData() {
        binding.etPendidikanTerakhir.setText(dataOjk.getPendidikanTerakhir());
        binding.etKodePekerjaan.setText(dataOjk.getKodePekerjaan());
        binding.etKodeBidangUsahaTempatKerja.setText(dataOjk.getKodeBidangUsahaTempatKer());
        binding.etJumlahTanggunganNasabah.setText(dataOjk.getJumlahTanggunanNasabah());
        binding.etAlamatTempatKerja.setText(dataOjk.getAlamatTempatBekerja());
        binding.etPendidikanTerakhir.setText(dataOjk.getPendidikanTerakhir());
        binding.etKodeJenisPenggunaan.setText(dataOjk.getKodeJenisPenggunaan());
        binding.etKodeSektorEkonomi.setText(dataOjk.getKodeSektorEkonomi());

    }

    private void loadData() {
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        ReqInquery req = new ReqInquery();
        req.setUID(String.valueOf(appPreferences.getUid()));
        req.setApplicationId(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryOjkBi(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                if (response.isSuccessful()) {
                    binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<UpdateDataOjkBi>() {
                        }.getType();
                        dataOjk = gson.fromJson(listDataString, type);

                        if (dataOjk != null) {
                            setData();
                        }

                    } else {
                        AppUtil.notiferror(ActivityFieldOjkBI.this, findViewById(android.R.id.content), response.body().getMessage());
                    }

                } else {
                    binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                    Error error = ParseResponseError.confirmEror(response.errorBody());
                    AppUtil.notiferror(ActivityFieldOjkBI.this, findViewById(android.R.id.content), error.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityFieldOjkBI.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void loadDropdownKodePekerjaan() {
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        //pantekan no aplikasi dan aktifitas

        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().dropdownKodePekerjaan(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<DropdownGlobalPrapen>>() {
                        }.getType();
                        List<DropdownGlobalPrapen> dropdownTemp= gson.fromJson(listDataString, type);
                        dataDropdownKodePekerjaan.clear();
                        for (int i = 0; i <dropdownTemp.size(); i++) {
                            dataDropdownKodePekerjaan.add(new MGenericModel(dropdownTemp.get(i).getName(),dropdownTemp.get(i).getName()));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityFieldOjkBI.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
//        loadDropdownSekEkonomi();
    }

    //    public void loadDropdownSekEkonomi() {
//        //  dataUser = getListUser();
//        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
//        //pantekan no aplikasi dan aktifitas
//        SearchSekEkonomi req = new SearchSekEkonomi();
//        ;
//        Call<ParseResponse> call = apiClientAdapter.getApiInterface().dropdownSektorekonomi(req);
//        call.enqueue(new Callback<ParseResponse>() {
//            @Override
//            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
//                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
//                if (response.isSuccessful()) {
//                    if (response.body().getStatus().equalsIgnoreCase("00")) {
//                        String listDataString = response.body().getData().toString();
//                        Gson gson = new Gson();
//                        Type type = new TypeToken<List<DropdownGlobalPrapen>>() {
//                        }.getType();
//                        List<DropdownGlobalPrapen> dropdownTemp= gson.fromJson(listDataString, type);
//                        dropdownSektorEkonomi.clear();
//                        for (int i = 0; i <dropdownTemp.size(); i++) {
//                            dropdownSektorEkonomi.add(new MGenericModel(dropdownTemp.get(i).getName(),dropdownTemp.get(i).getName()));
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ParseResponse> call, Throwable t) {
//                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
//                AppUtil.notiferror(ActivityFieldOjkBI.this, findViewById(android.R.id.content), "Terjadi kesalahan");
//                Log.d("LOG D", t.getMessage());
//            }
//        });
//    }
    private void sendDataOJK() {
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        UpdateDataOjkBi req = new UpdateDataOjkBi();
        AppPreferences appPreferences = new AppPreferences(this);
        req.setUID(String.valueOf(appPreferences.getUid()));
        req.setApplicationId(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
        req.setKodeBidangUsahaTempatKer(binding.etKodeBidangUsahaTempatKerja.getText().toString());
        req.setJumlahTanggunanNasabah(binding.etJumlahTanggunganNasabah.getText().toString());
        req.setKodeJenisPenggunaan(binding.etKodeJenisPenggunaan.getText().toString());
        req.setKodeSektorEkonomi(binding.etKodeSektorEkonomi.getText().toString());
        req.setPendidikanTerakhir(binding.etPendidikanTerakhir.getText().toString());
        req.setKodePekerjaan(binding.etKodePekerjaan.getText().toString());
        req.setAlamatTempatBekerja(binding.etAlamatTempatKerja.getText().toString());
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updateDataOjkBi(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            finish();
                        } else {
                            AppUtil.notiferror(ActivityFieldOjkBI.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ActivityFieldOjkBI.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityFieldOjkBI.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }



    private void onclickSelectDialog() {
        binding.etPendidikanTerakhir.setOnClickListener(this);
        binding.etKodePekerjaan.setOnClickListener(this);
        binding.etKodeBidangUsahaTempatKerja.setOnClickListener(this);
        binding.etAlamatTempatKerja.setOnClickListener(this);
        binding.etJumlahTanggunganNasabah.setOnClickListener(this);
        binding.etKodeJenisPenggunaan.setOnClickListener(this);
        binding.etKodeSektorEkonomi.setOnClickListener(this);

        binding.tfPendidikanTerakhir.setOnClickListener(this);
        binding.tfKodePekerjaan.setOnClickListener(this);
        binding.tfPendidikanTerakhir.setOnClickListener(this);
        binding.tfKodeBidangUsahaTempatKerja.setOnClickListener(this);
        binding.tfAlamatTempatKerja.setOnClickListener(this);
        binding.tfJumlahTanggunganNasabah.setOnClickListener(this);
        binding.tfKodeJenisPenggunaan.setOnClickListener(this);
        binding.tfKodeSektorEkonomi.setOnClickListener(this);

        binding.btnSendojkbi.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_pendidikan_terakhir:
            case R.id.tf_pendidikan_terakhir:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfPendidikanTerakhir.getLabelText(), dataDropdownGraduate, ActivityFieldOjkBI.this);
                break;
            case R.id.et_kode_pekerjaan:
            case R.id.tf_kode_pekerjaan:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfKodePekerjaan.getLabelText(), dataDropdownKodePekerjaan, ActivityFieldOjkBI.this);
                break;
            case R.id.et_kode_bidang_usaha_tempat_kerja:
            case R.id.tf_kode_bidang_usaha_tempat_kerja:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfKodeBidangUsahaTempatKerja.getLabelText(), dataDropdownKodeBUTK, ActivityFieldOjkBI.this);
                break;
            case R.id.et_kode_jenis_penggunaan:
            case R.id.tf_kode_jenis_penggunaan:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfKodeJenisPenggunaan.getLabelText(), dataDropdownJepen, ActivityFieldOjkBI.this);
                break;
            case R.id.et_kode_sektor_ekonomi:
            case R.id.tf_kode_sektor_ekonomi:
                DialogListSektorEkonomi.display(getSupportFragmentManager(), idAplikasi, binding.tfKodeSektorEkonomi.getLabelText().toString().trim(),  ActivityFieldOjkBI.this);
//                DialogListSektorEkonomi.display(getSupportFragmentManager(), idAplikasi, binding.etKodeSektorEkonomi.getText().toString().trim(),  ActivityFieldOjkBI.this);
                break;
            case R.id.btn_sendojkbi:
                adaFieldBelumDiisi=false;
                validateField(binding.etAlamatTempatKerja,binding.tfAlamatTempatKerja);
                validateField(binding.etJumlahTanggunganNasabah,binding.tfJumlahTanggunganNasabah);
                validateField(binding.etKodePekerjaan,binding.tfKodePekerjaan);
                validateField(binding.etPendidikanTerakhir,binding.tfPendidikanTerakhir);
                validateField(binding.etKodeBidangUsahaTempatKerja,binding.tfKodeBidangUsahaTempatKerja);
                validateField(binding.etKodeJenisPenggunaan,binding.tfKodeJenisPenggunaan);
                validateField(binding.etKodeSektorEkonomi,binding.tfKodeSektorEkonomi);
                sendDataOJK();
                if(!adaFieldBelumDiisi){
                    sendDataOJK();
                }


            default:break;

        }
    }

    public void onSelect(String title, MGenericModel data) {
        if (title.equalsIgnoreCase(binding.tfPendidikanTerakhir.getLabelText())) {
            binding.etPendidikanTerakhir.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfKodePekerjaan.getLabelText())) {
            binding.etKodePekerjaan.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfKodeBidangUsahaTempatKerja.getLabelText())) {
            binding.etKodeBidangUsahaTempatKerja.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(binding.tfKodeJenisPenggunaan.getLabelText())) {
            binding.etKodeJenisPenggunaan.setText(data.getDESC());
        }
    }

    public void onSelect(String title, DropdownOJK data) {
        if (title.equalsIgnoreCase(binding.tfKodeSektorEkonomi.getLabelText())) {
            binding.etKodeSektorEkonomi.setText(data.getName());

        }
    }

    private void allOnclick() {
        binding.etPendidikanTerakhir.setOnClickListener(this);
        binding.etKodePekerjaan.setOnClickListener(this);
        binding.etKodeBidangUsahaTempatKerja.setOnClickListener(this);
        binding.etKodeJenisPenggunaan.setOnClickListener(this);
        binding.etKodeSektorEkonomi.setOnClickListener(this);

        binding.tfPendidikanTerakhir.setOnClickListener(this);
        binding.tfKodePekerjaan.setOnClickListener(this);
        binding.tfKodeBidangUsahaTempatKerja.setOnClickListener(this);
        binding.tfKodeJenisPenggunaan.setOnClickListener(this);
        binding.tfKodeSektorEkonomi.setOnClickListener(this);

    }


    private void endIconClick() {
        binding.tfPendidikanTerakhir.getEndIconImageButton().setOnClickListener(v -> DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfPendidikanTerakhir.getLabelText(), dataDropdownGraduate, ActivityFieldOjkBI.this));
        binding.tfKodePekerjaan.getEndIconImageButton().setOnClickListener(v -> DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfKodePekerjaan.getLabelText(), dataDropdownKodePekerjaan, ActivityFieldOjkBI.this));
        binding.tfKodeBidangUsahaTempatKerja.getEndIconImageButton().setOnClickListener(v -> DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfKodeBidangUsahaTempatKerja.getLabelText(), dataDropdownKodeBUTK, ActivityFieldOjkBI.this));
        binding.tfKodeJenisPenggunaan.getEndIconImageButton().setOnClickListener(v -> DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfKodeJenisPenggunaan.getLabelText(), dataDropdownJepen, ActivityFieldOjkBI.this));
        binding.tfKodeSektorEkonomi.getEndIconImageButton().setOnClickListener(v ->  DialogListSektorEkonomi.display(getSupportFragmentManager(), idAplikasi, binding.tfKodeSektorEkonomi.getLabelText().toString().trim(),  ActivityFieldOjkBI.this));



    }


    private void validateField(EditText editText, TextFieldBoxes textFieldBoxes){
        if(editText.getText().toString().isEmpty()){
            adaFieldBelumDiisi=true;
            AppUtil.notiferror(ActivityFieldOjkBI.this, findViewById(android.R.id.content), "Harap Isi "+textFieldBoxes.getLabelText());
        }
    }


    private void setParameterDropdown() {
        //dropdown Pendidikan Terakhir
        dataDropdownGraduate.add(new MGenericModel("1", "SD/sederajat"));
        dataDropdownGraduate.add(new MGenericModel("2", "SMP"));
        dataDropdownGraduate.add(new MGenericModel("3", "SMA"));
        dataDropdownGraduate.add(new MGenericModel("4", "D1"));
        dataDropdownGraduate.add(new MGenericModel("5", "D2"));
        dataDropdownGraduate.add(new MGenericModel("6", "D3"));
        dataDropdownGraduate.add(new MGenericModel("7", "S1"));
        dataDropdownGraduate.add(new MGenericModel("8", "S2"));
        dataDropdownGraduate.add(new MGenericModel("9", "S3"));
        //dropdown Kode Bidang Usaha Tempat Kerja
        dataDropdownKodeBUTK.add(new MGenericModel("Kode BUTK", ""));
        //dropdown Jenis Penggunaan
        dataDropdownJepen.add(new MGenericModel("3", "Konsumtif"));
        dataDropdownJepen.add(new MGenericModel("1", "Produktif ( modal kerja )"));
        dataDropdownJepen.add(new MGenericModel("2", "Produktif ( investasi )"));

    }

    private void disableEditText() {
        binding.etPendidikanTerakhir.setFocusable(false);
        binding.etKodePekerjaan.setFocusable(false);
        binding.etKodeBidangUsahaTempatKerja.setFocusable(false);
        binding.etKodeJenisPenggunaan.setFocusable(false);
        binding.etKodeSektorEkonomi.setFocusable(false);

        binding.tfPendidikanTerakhir.setFocusable(false);
        binding.tfKodePekerjaan.setFocusable(false);
        binding.tfKodeBidangUsahaTempatKerja.setFocusable(false);
        binding.tfKodeJenisPenggunaan.setFocusable(false);
        binding.tfKodeSektorEkonomi.setFocusable(false);
    }


    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public void onSectorSelect(MsektorEkonomi data) {
        title.equalsIgnoreCase(binding.tfKodeSektorEkonomi.getLabelText()); {
            binding.etKodeSektorEkonomi.setText(data.getSektorEkonomiText());

        }
    }
}