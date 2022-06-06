package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.dokumen_tambahan;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseFile;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseLogicalDoc;
import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.model.request.foto.ReqUploadFile;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.DataPribadiExt;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.DataPribadiLainya;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDataPribadiLainya;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDocument;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDocumentUmum;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UploadDokumenTambahan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqInquery;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateUploadDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UploadDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UploadDokumenTambahan;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MParseArray;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityDataPribadiTambahanBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.UploadDokumenActivityBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSUploadFile;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDokumenTambahan extends AppCompatActivity implements GenericListenerOnSelect, CameraListener, View.OnClickListener, ConfirmListener {

    ActivityDataPribadiTambahanBinding binding;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private long idAplikasi, id;
    private String produk, tipeFile, fileload, statusId = "";

    Integer addImg = 0;
    Bitmap dokumenA, dokumenB, dokumenC, dokumenD, dokumenE, dokumenF;
    Uri UridokumenA, UridokumenB, UridokumenC, UridokumenD, UridokumenE, UridokumenF;
    final int IntDokumenA = 1, IntDokumenB = 2, IntDokumenC = 3, IntDokumenD = 4, IntDokumenE = 5, IntDokumenF = 34;
    String clicker, valDokumenA, valDokumenB, valDokumenC, valDokumenD, valDokumenE, valDokumenF;
    String idDokumenA = "", idDokumenB = "", idDokumenC = "", idDokumenD = "", idDokumenE = "", idDokumenF = "";
    String nameDokumenA = "", nameDokumenB = "", nameDokumenC = "", nameDokumenD = "", nameDokumenE = "", nameDokumenF = "";

    List<UploadDokumenTambahan> DokumenUmum = new ArrayList<UploadDokumenTambahan>();
    List<MGenericModel> dropdown = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        if (getIntent().hasExtra("statusId")) {
            statusId = getIntent().getStringExtra("statusId");
        }
        super.onCreate(savedInstanceState);
        binding = ActivityDataPribadiTambahanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idAplikasi = Long.parseLong(getIntent().getStringExtra("idAplikasi"));
        if (getIntent().hasExtra("produk")) {
            produk = getIntent().getStringExtra("produk");
        } else {
            produk = "";
        }
        hideUploadDokumen();
        setContentView(view);
        onclickSelectDialog();
        initdata();
        isidropdown();
        disabledEdit();
        endIconClick();
        AppUtil.toolbarRegular(this, "Upload Dokumen");

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.DialogBackpress(ActivityDokumenTambahan.this);
            }
        });

        if (statusId.equalsIgnoreCase("g.3")) {
            binding.btnTambahanDokumen.setVisibility(View.GONE);
            binding.btnDeleteDokumen.setVisibility(View.GONE);
            binding.btnSend.setVisibility(View.GONE);
            binding.btnUploadDokumen1.setVisibility(View.GONE);
            binding.btnUploadDokumen2.setVisibility(View.GONE);
            binding.btnUploadDokumen3.setVisibility(View.GONE);
            binding.btnUploadDokumen4.setVisibility(View.GONE);
            binding.btnUploadDokumen5.setVisibility(View.GONE);
            binding.btnUploadDokumen6.setVisibility(View.GONE);
        }
    }

    private void endIconClick() {
        binding.tfStatusKepemilikan.getEndIconImageButton().setOnClickListener(
                v -> DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfStatusKepemilikan.getLabelText(), dropdown, ActivityDokumenTambahan.this)
        );
    }

    private void disabledEdit() {
        binding.etStatusKepemilikan.setFocusable(false);
        binding.etLamaNasabah.setFocusable(false);
    }

    private void isidropdown() {
        EmptyRequest emptyRequest = new EmptyRequest();
        Call<ParseResponseArr> calli = apiClientAdapter.getApiInterface().dropdownstatusKepemilikan(emptyRequest);
        calli.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(@NonNull Call<ParseResponseArr> call, @NonNull Response<ParseResponseArr> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        dropdown.add(new MGenericModel(response.body().getData().get(i).getAsJsonObject().get("Id").toString(), response.body().getData().get(i).getAsJsonObject().get("Name").getAsString()));
                    }
                } else {
                    AppUtil.notiferror(ActivityDokumenTambahan.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ParseResponseArr> call, @NonNull Throwable t) {
                AppUtil.notiferror(ActivityDokumenTambahan.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void initdata() {
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        ReqInquery req = new ReqInquery();
        req.setUID(String.valueOf(appPreferences.getUid()));
        req.setApplicationId(Integer.parseInt(getIntent().getStringExtra("idAplikasi")));
        Call<DataPribadiLainya> call = apiClientAdapter.getApiInterface().InquiryDokDataPribadiLainnya(req);
        call.enqueue(new Callback<DataPribadiLainya>() {
            @Override
            public void onResponse(Call<DataPribadiLainya> call, Response<DataPribadiLainya> response) {
                if (response.isSuccessful()) {
                    binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);

                    if (response.body().getStatus().equalsIgnoreCase("00") && response.body().getReqDataPribadiLainya() != null) {
                        Gson gson = new Gson();

                        // Data Textfield
                        if (response.body().getReqDataPribadiLainya().getDataPribadiExt() != null) {
                            id = response.body().getReqDataPribadiLainya().getDataPribadiExt().getStatusKepemilikan();
                            binding.etLamaNasabah.setText(String.valueOf(response.body().getReqDataPribadiLainya().getDataPribadiExt().getLamaMenjadiNasabahBank()));
                            binding.etStatusKepemilikan.setText(String.valueOf(response.body().getReqDataPribadiLainya().getDataPribadiExt().getStatusKepemilikanText()));
                            binding.etLamaTinggal.setText(String.valueOf(response.body().getReqDataPribadiLainya().getDataPribadiExt().getLamaTempatDomisili()));
                            binding.etJumlahTanggungan.setText(String.valueOf(response.body().getReqDataPribadiLainya().getDataPribadiExt().getJumlahTangungan()));
                        }
                        // Dokumen Tambahan
                        if (response.body().getReqDataPribadiLainya().getUploadDokumenTambahan() != null) {
                            DokumenUmum = response.body().getReqDataPribadiLainya().getUploadDokumenTambahan();
                            addImg = DokumenUmum.size() - 1;
                            if (DokumenUmum.size() == 1) {
                                binding.llDeleteDokumen.setVisibility(View.GONE);
                                binding.btnDeleteDokumen.setVisibility(View.GONE);
                            } else if (DokumenUmum.size() == 5) {
                                binding.btnTambahanDokumen.setVisibility(View.GONE);
                                binding.llTambahanDokumen.setVisibility(View.GONE);
                            }
                            for (int i = 0; i < DokumenUmum.size(); i++) {
                                if (i == 0) {
                                    binding.etKeteranganDokumen1.setText(DokumenUmum.get(i).getDescription());
                                    binding.etNamaDokumen1.setText(DokumenUmum.get(i).getName());
                                    binding.cvUploadDokumen1.setVisibility(View.VISIBLE);
                                    binding.tvUploadDokumen.setVisibility(View.VISIBLE);
                                    binding.tfNamaDokumen1.setVisibility(View.VISIBLE);
                                    binding.tfKeteranganDokumen1.setVisibility(View.VISIBLE);
                                    binding.tfUploadDokumen1.setVisibility(View.VISIBLE);
                                    binding.etNamaDokumen1.setVisibility(View.VISIBLE);
                                    binding.etKeteranganDokumen1.setVisibility(View.VISIBLE);
                                    binding.ivUploadDokumen1.setVisibility(View.VISIBLE);
                                    binding.btnUploadDokumen1.setVisibility(View.VISIBLE);
                                    try {
                                        checkFileTypeThenSet(ActivityDokumenTambahan.this, DokumenUmum.get(i).getImg(), binding.ivUploadDokumen1, DokumenUmum.get(i).getFilename());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if (i == 1) {
                                    try {
                                        binding.etKeteranganDokumen2.setText(DokumenUmum.get(i).getDescription());
                                        binding.etNamaDokumen2.setText(DokumenUmum.get(i).getName());
                                        binding.cvUploadDokumen2.setVisibility(View.VISIBLE);
                                        binding.tvUploadDokumen2.setVisibility(View.VISIBLE);
                                        binding.tfNamaDokumen2.setVisibility(View.VISIBLE);
                                        binding.tfKeteranganDokumen2.setVisibility(View.VISIBLE);
                                        binding.tfUploadDokumen2.setVisibility(View.VISIBLE);
                                        binding.etNamaDokumen2.setVisibility(View.VISIBLE);
                                        binding.etKeteranganDokumen2.setVisibility(View.VISIBLE);
                                        binding.ivUploadDokumen2.setVisibility(View.VISIBLE);
                                        binding.btnUploadDokumen2.setVisibility(View.VISIBLE);
                                        try {
                                            checkFileTypeThenSet(ActivityDokumenTambahan.this, DokumenUmum.get(i).getImg(), binding.ivUploadDokumen2, DokumenUmum.get(i).getFilename());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if (i == 2) {
                                    try {
                                        binding.etKeteranganDokumen3.setText(DokumenUmum.get(i).getDescription());
                                        binding.etNamaDokumen3.setText(DokumenUmum.get(i).getName());
                                        binding.cvUploadDokumen3.setVisibility(View.VISIBLE);
                                        binding.tvUploadDokumen3.setVisibility(View.VISIBLE);
                                        binding.tfNamaDokumen3.setVisibility(View.VISIBLE);
                                        binding.tfKeteranganDokumen3.setVisibility(View.VISIBLE);
                                        binding.tfUploadDokumen3.setVisibility(View.VISIBLE);
                                        binding.etNamaDokumen3.setVisibility(View.VISIBLE);
                                        binding.etKeteranganDokumen3.setVisibility(View.VISIBLE);
                                        binding.ivUploadDokumen3.setVisibility(View.VISIBLE);
                                        binding.btnUploadDokumen3.setVisibility(View.VISIBLE);
                                        try {
                                            checkFileTypeThenSet(ActivityDokumenTambahan.this, DokumenUmum.get(i).getImg(), binding.ivUploadDokumen3, DokumenUmum.get(i).getFilename());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if (i == 3) {
                                    try {
                                        binding.etKeteranganDokumen4.setText(DokumenUmum.get(i).getDescription());
                                        binding.etNamaDokumen4.setText(DokumenUmum.get(i).getName());
                                        binding.cvUploadDokumen4.setVisibility(View.VISIBLE);
                                        binding.tvUploadDokumen4.setVisibility(View.VISIBLE);
                                        binding.tfNamaDokumen4.setVisibility(View.VISIBLE);
                                        binding.tfKeteranganDokumen4.setVisibility(View.VISIBLE);
                                        binding.tfUploadDokumen4.setVisibility(View.VISIBLE);
                                        binding.etNamaDokumen4.setVisibility(View.VISIBLE);
                                        binding.etKeteranganDokumen4.setVisibility(View.VISIBLE);
                                        binding.ivUploadDokumen4.setVisibility(View.VISIBLE);
                                        binding.btnUploadDokumen4.setVisibility(View.VISIBLE);
                                        try {
                                            checkFileTypeThenSet(ActivityDokumenTambahan.this, DokumenUmum.get(i).getImg(), binding.ivUploadDokumen4, DokumenUmum.get(i).getFilename());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if (i == 4) {
                                    try {
                                        binding.etKeteranganDokumen5.setText(DokumenUmum.get(i).getDescription());
                                        binding.etNamaDokumen5.setText(DokumenUmum.get(i).getName());
                                        binding.cvUploadDokumen5.setVisibility(View.VISIBLE);
                                        binding.tvUploadDokumen5.setVisibility(View.VISIBLE);
                                        binding.tfNamaDokumen5.setVisibility(View.VISIBLE);
                                        binding.tfKeteranganDokumen5.setVisibility(View.VISIBLE);
                                        binding.tfUploadDokumen5.setVisibility(View.VISIBLE);
                                        binding.etNamaDokumen5.setVisibility(View.VISIBLE);
                                        binding.etKeteranganDokumen5.setVisibility(View.VISIBLE);
                                        binding.ivUploadDokumen5.setVisibility(View.VISIBLE);
                                        binding.btnUploadDokumen5.setVisibility(View.VISIBLE);
                                        try {
                                            checkFileTypeThenSet(ActivityDokumenTambahan.this, DokumenUmum.get(i).getImg(), binding.ivUploadDokumen5, DokumenUmum.get(i).getFilename());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if (i == 5) {
                                    try {
                                        binding.etKeteranganDokumen6.setText(DokumenUmum.get(i).getDescription());
                                        binding.etNamaDokumen6.setText(DokumenUmum.get(i).getName());
                                        binding.cvUploadDokumen6.setVisibility(View.VISIBLE);
                                        binding.tvUploadDokumen6.setVisibility(View.VISIBLE);
                                        binding.tfNamaDokumen6.setVisibility(View.VISIBLE);
                                        binding.tfKeteranganDokumen6.setVisibility(View.VISIBLE);
                                        binding.tfUploadDokumen6.setVisibility(View.VISIBLE);
                                        binding.etNamaDokumen6.setVisibility(View.VISIBLE);
                                        binding.etKeteranganDokumen6.setVisibility(View.VISIBLE);
                                        binding.ivUploadDokumen6.setVisibility(View.VISIBLE);
                                        binding.btnUploadDokumen6.setVisibility(View.VISIBLE);
                                        try {
                                            checkFileTypeThenSet(ActivityDokumenTambahan.this, DokumenUmum.get(i).getImg(), binding.ivUploadDokumen6, DokumenUmum.get(i).getFilename());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                    }
                } else {
                    binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                    Error error = ParseResponseError.confirmEror(response.errorBody());
                    AppUtil.notiferror(ActivityDokumenTambahan.this, findViewById(android.R.id.content), error.getMessage());
                }
            }

            @Override
            public void onFailure(Call<DataPribadiLainya> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityDokumenTambahan.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void hideUploadDokumen() {
        binding.cvUploadDokumen2.setVisibility(View.GONE);
        binding.cvUploadDokumen3.setVisibility(View.GONE);
        binding.cvUploadDokumen4.setVisibility(View.GONE);
        binding.cvUploadDokumen5.setVisibility(View.GONE);
        binding.cvUploadDokumen6.setVisibility(View.GONE);

    }

    private void onclickSelectDialog() {

        binding.btnUploadDokumen1.setOnClickListener(this);
        binding.btnUploadDokumen2.setOnClickListener(this);
        binding.btnUploadDokumen3.setOnClickListener(this);
        binding.btnUploadDokumen4.setOnClickListener(this);
        binding.btnUploadDokumen5.setOnClickListener(this);
        binding.btnUploadDokumen6.setOnClickListener(this);

        binding.ivUploadDokumen1.setOnClickListener(this);
        binding.ivUploadDokumen2.setOnClickListener(this);
        binding.ivUploadDokumen3.setOnClickListener(this);
        binding.ivUploadDokumen4.setOnClickListener(this);
        binding.ivUploadDokumen5.setOnClickListener(this);
        binding.ivUploadDokumen6.setOnClickListener(this);

        binding.etNamaDokumen1.setOnClickListener(this);
        binding.etNamaDokumen2.setOnClickListener(this);
        binding.etNamaDokumen3.setOnClickListener(this);
        binding.etNamaDokumen4.setOnClickListener(this);
        binding.etNamaDokumen5.setOnClickListener(this);
        binding.etNamaDokumen6.setOnClickListener(this);

        binding.etKeteranganDokumen1.setOnClickListener(this);
        binding.etKeteranganDokumen2.setOnClickListener(this);
        binding.etKeteranganDokumen3.setOnClickListener(this);
        binding.etKeteranganDokumen4.setOnClickListener(this);
        binding.etKeteranganDokumen5.setOnClickListener(this);
        binding.etKeteranganDokumen6.setOnClickListener(this);

        binding.llTambahanDokumen.setOnClickListener(this);
        binding.btnTambahanDokumen.setOnClickListener(this);
        binding.llDeleteDokumen.setOnClickListener(this);
        binding.btnDeleteDokumen.setOnClickListener(this);
        binding.btnSend.setOnClickListener(this);
        binding.llBtnSend.setOnClickListener(this);
        binding.etStatusKepemilikan.setOnClickListener(this);
        binding.tfStatusKepemilikan.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tf_status_kepemilikan:
            case R.id.et_status_kepemilikan:
                DialogGenericDataFromService.display(getSupportFragmentManager(), binding.tfStatusKepemilikan.getLabelText(), dropdown, ActivityDokumenTambahan.this);
                break;
            case R.id.btn_upload_dokumen1:
            case R.id.iv_upload_dokumen1:
                clicker = "dokumenA";
                if (String.valueOf(binding.etNamaDokumen1.getText()) != "")
                    BSUploadFile.displayWithTitle(ActivityDokumenTambahan.this.getSupportFragmentManager(), this, "");
                else
                    AppUtil.notiferror(this, findViewById(android.R.id.content), "Isi Nama File Terlebih dahulu");
                break;
            case R.id.btn_upload_dokumen2:
            case R.id.iv_upload_dokumen2:
                clicker = "dokumenB";
                if (String.valueOf(binding.etNamaDokumen2.getText()) != "")
                BSUploadFile.displayWithTitle(ActivityDokumenTambahan.this.getSupportFragmentManager(), this, "");
                else
                    AppUtil.notiferror(this, findViewById(android.R.id.content), "Isi Nama File Terlebih dahulu");
                break;
            case R.id.btn_upload_dokumen3:
            case R.id.iv_upload_dokumen3:
                clicker = "dokumenC";
                 if (String.valueOf(binding.etNamaDokumen3.getText()) != "")
                BSUploadFile.displayWithTitle(ActivityDokumenTambahan.this.getSupportFragmentManager(), this, "");
                else
                    AppUtil.notiferror(this, findViewById(android.R.id.content), "Isi Nama File Terlebih dahulu");
                break;
            case R.id.btn_upload_dokumen4:
            case R.id.iv_upload_dokumen4:
                clicker = "dokumenD";
                 if (String.valueOf(binding.etNamaDokumen4.getText()) != "")
                BSUploadFile.displayWithTitle(ActivityDokumenTambahan.this.getSupportFragmentManager(), this, "");
                else
                    AppUtil.notiferror(this, findViewById(android.R.id.content), "Isi Nama File Terlebih dahulu");
                break;
            case R.id.btn_upload_dokumen5:
            case R.id.iv_upload_dokumen5:
                clicker = "dokumenE";
                 if (String.valueOf(binding.etNamaDokumen5.getText()) != "")
                BSUploadFile.displayWithTitle(ActivityDokumenTambahan.this.getSupportFragmentManager(), this, "");
                else
                    AppUtil.notiferror(this, findViewById(android.R.id.content), "Isi Nama File Terlebih dahulu");
                break;
            case R.id.btn_upload_dokumen6:
            case R.id.iv_upload_dokumen6:
                clicker = "dokumenF";
                 if (String.valueOf(binding.etNamaDokumen6.getText()) != "")
                BSUploadFile.displayWithTitle(ActivityDokumenTambahan.this.getSupportFragmentManager(), this, "");
                else
                    AppUtil.notiferror(this, findViewById(android.R.id.content), "Isi Nama File Terlebih dahulu");
                break;
            case R.id.ll_tambahan_dokumen:
            case R.id.btn_tambahan_dokumen:
                addImg += 1;
                AppUtil.logSecure("LogInt", addImg.toString());
                if (addImg == 1) {
                    binding.cvUploadDokumen2.setVisibility(View.VISIBLE);
                    binding.llDeleteDokumen.setVisibility(View.VISIBLE);
                } else if (addImg == 2) {
                    binding.cvUploadDokumen3.setVisibility(View.VISIBLE);
                } else if (addImg == 3) {
                    binding.cvUploadDokumen4.setVisibility(View.VISIBLE);
                } else if (addImg == 4) {
                    binding.cvUploadDokumen5.setVisibility(View.VISIBLE);
                } else {
                    binding.cvUploadDokumen6.setVisibility(View.VISIBLE);
                    binding.btnTambahanDokumen.setVisibility(View.GONE);
                    binding.llTambahanDokumen.setVisibility(View.GONE);
                }
                break;
            case R.id.ll_delete_dokumen:
            case R.id.btn_delete_dokumen:
                addImg -= 1;
                AppUtil.logSecure("LogInt", addImg.toString());
                Drawable res = getDrawable(R.mipmap.ico_img_for_upload);
                if (addImg == 4) {
                    if (DokumenUmum.size() == 6) {
                        DokumenUmum.remove(5);
                        binding.etNamaDokumen6.setText("");
                        binding.etKeteranganDokumen6.setText("");
                        binding.ivUploadDokumen6.setImageDrawable(res);
                    } else {
                        binding.etNamaDokumen6.setText("");
                        binding.etKeteranganDokumen6.setText("");
                        binding.ivUploadDokumen6.setImageDrawable(res);
                    }
                    binding.cvUploadDokumen6.setVisibility(View.GONE);
                    binding.ivUploadDokumen6.setOnClickListener(this);
                    binding.btnTambahanDokumen.setVisibility(View.VISIBLE);
                    binding.llTambahanDokumen.setVisibility(View.VISIBLE);
                } else if (addImg == 3) {
                    if (DokumenUmum.size() == 5) {
                        DokumenUmum.remove(4);
                        binding.etNamaDokumen5.setText("");
                        binding.etKeteranganDokumen5.setText("");
                        binding.ivUploadDokumen5.setImageDrawable(res);
                    } else {
                        binding.etNamaDokumen5.setText("");
                        binding.etKeteranganDokumen5.setText("");
                        binding.ivUploadDokumen5.setImageDrawable(res);
                    }
                    binding.cvUploadDokumen5.setVisibility(View.GONE);
                    binding.ivUploadDokumen5.setOnClickListener(this);
                } else if (addImg == 2) {
                    if (DokumenUmum.size() == 4) {
                        DokumenUmum.remove(3);
                        binding.etNamaDokumen4.setText("");
                        binding.etKeteranganDokumen4.setText("");
                        binding.ivUploadDokumen4.setImageDrawable(res);
                    } else {
                        binding.etNamaDokumen4.setText("");
                        binding.etKeteranganDokumen4.setText("");
                        binding.ivUploadDokumen4.setImageDrawable(res);
                    }
                    binding.cvUploadDokumen4.setVisibility(View.GONE);
                    binding.ivUploadDokumen4.setOnClickListener(this);
                } else if (addImg == 1) {
                    if (DokumenUmum.size() == 3) {
                        DokumenUmum.remove(2);
                        binding.etNamaDokumen3.setText("");
                        binding.etKeteranganDokumen3.setText("");
                        binding.ivUploadDokumen3.setImageDrawable(res);
                    } else {
                        binding.etNamaDokumen3.setText("");
                        binding.etKeteranganDokumen3.setText("");
                        binding.ivUploadDokumen3.setImageDrawable(res);
                    }
                    binding.cvUploadDokumen3.setVisibility(View.GONE);
                    binding.ivUploadDokumen3.setOnClickListener(this);
                } else {
                    if (DokumenUmum.size() == 2) {
                        DokumenUmum.remove(1);
                        binding.etNamaDokumen2.setText("");
                        binding.etKeteranganDokumen2.setText("");
                        binding.ivUploadDokumen2.setImageDrawable(res);
                    } else {
                        binding.etNamaDokumen2.setText("");
                        binding.etKeteranganDokumen2.setText("");
                        binding.ivUploadDokumen2.setImageDrawable(res);
                    }
                    binding.cvUploadDokumen2.setVisibility(View.GONE);
                    binding.ivUploadDokumen2.setOnClickListener(this);
                    binding.llDeleteDokumen.setVisibility(View.GONE);
                }
                break;
            case R.id.btn_cek_lngp:
            case R.id.ll_btn_send:
            case R.id.btn_send:
                sendData();
            default:
                break;
        }
    }

    private void sendData() {
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);

        DataPribadiExt dpe = new DataPribadiExt();
        dpe.setJumlahTangungan(Integer.valueOf(String.valueOf(binding.etJumlahTanggungan.getText())));
        dpe.setLamaMenjadiNasabahBank(Integer.valueOf(String.valueOf(binding.etLamaNasabah.getText())));
        dpe.setStatusKepemilikan(id);
        dpe.setLamaTempatDomisili(Integer.valueOf(String.valueOf(binding.etLamaTinggal.getText())));

        ReqDataPribadiLainya upD = new ReqDataPribadiLainya();
        upD.setUploadDokumenTambahan(DokumenUmum);
        upD.setDataPribadiExt(dpe);

        DataPribadiLainya req = new DataPribadiLainya();
        req.setApplicationId(idAplikasi);
        req.setUid(String.valueOf(appPreferences.getUid()));
        req.setUploadDokumen(upD);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().UpdateDokDataPribadiLainnya(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {

                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            CustomDialog.DialogSuccess(ActivityDokumenTambahan.this, "Success!", "Update Data Sukses", ActivityDokumenTambahan.this);

                        } else {
                            AppUtil.notiferror(ActivityDokumenTambahan.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ActivityDokumenTambahan.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityDokumenTambahan.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public void onBackPressed() {
        CustomDialog.DialogBackpress(ActivityDokumenTambahan.this);
    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        switch (idMenu) {
            case "Take Photo":
                tipeFile = "png";
                if (clicker.equalsIgnoreCase("dokumenA")) {
                    openCamera(IntDokumenA, "dokumenA");
                } else if (clicker.equalsIgnoreCase("dokumenB")) {
                    openCamera(IntDokumenB, "dokumenB");
                } else if (clicker.equalsIgnoreCase("dokumenC")) {
                    openCamera(IntDokumenC, "dokumenC");
                } else if (clicker.equalsIgnoreCase("dokumenD")) {
                    openCamera(IntDokumenD, "dokumenD");
                } else if (clicker.equalsIgnoreCase("dokumenE")) {
                    openCamera(IntDokumenE, "dokumenE");
                } else if (clicker.equalsIgnoreCase("dokumenF")) {
                    openCamera(IntDokumenF, "dokumenF");
                }
                break;
            case "Pick Photo":
                tipeFile = "png";
                if (clicker.equalsIgnoreCase("dokumenA")) {
                    openGalery(IntDokumenA);
                } else if (clicker.equalsIgnoreCase("dokumenB")) {
                    openGalery(IntDokumenB);
                } else if (clicker.equalsIgnoreCase("dokumenC")) {
                    openGalery(IntDokumenC);
                } else if (clicker.equalsIgnoreCase("dokumenD")) {
                    openGalery(IntDokumenD);
                } else if (clicker.equalsIgnoreCase("dokumenE")) {
                    openGalery(IntDokumenE);
                } else if (clicker.equalsIgnoreCase("dokumenF")) {
                    openGalery(IntDokumenF);
                }
                break;
            case "Pick File":
                tipeFile = "pdf";
                if (clicker.equalsIgnoreCase("dokumenA")) {
                    openFile(IntDokumenA);
                } else if (clicker.equalsIgnoreCase("dokumenB")) {
                    openFile(IntDokumenB);
                } else if (clicker.equalsIgnoreCase("dokumenC")) {
                    openFile(IntDokumenC);
                } else if (clicker.equalsIgnoreCase("dokumenD")) {
                    openFile(IntDokumenD);
                } else if (clicker.equalsIgnoreCase("dokumenE")) {
                    openFile(IntDokumenE);
                } else if (clicker.equalsIgnoreCase("dokumenF")) {
                    openFile(IntDokumenF);
                }
                break;
        }

    }

    private void openCamera(int cameraCode, String namaFoto) {
        checkCameraPermission(cameraCode, namaFoto);
    }

    public void openGalery(int cameraCode) {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(Intent.createChooser(it, "Select File"), cameraCode);
    }

    public void openFile(int cameraCode) {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("application/pdf");
        startActivityForResult(Intent.createChooser(it, "Select File"), cameraCode);
    }

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static int CAMERA_CODE_FORE_PERMISSION = 0;

    public void checkCameraPermission(int cameraCode, String namaFoto) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);
        } else {
            Uri outputFileUri = getCaptureImageOutputUri(namaFoto);
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(captureIntent, cameraCode);
        }
    }

    private Uri getCaptureImageOutputUri(String namaFoto) {
        Uri outputFileUri = null;
        File getImage = this.getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                outputFileUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), namaFoto + ".png"));
            } else {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), namaFoto + ".png"));
            }
        }
        return outputFileUri;
    }

    public Uri getPickImageResultUri(Intent data, String namaFoto) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri(namaFoto) : data.getData();
    }

    @Override
    public void success(boolean val) {
        finish();
    }

    @Override
    public void confirm(boolean val) {

    }

    //Logical Doc

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (resultCode != 0) {
            switch (requestCode) {
                // Dokumen Tambahan
                case IntDokumenA:
                    SetDataImage(binding.ivUploadDokumen1, imageReturnedIntent, binding.etNamaDokumen1.getText().toString(), IntDokumenA);
                    checkFileTypeThenUpload(nameDokumenA, "_" + binding.etNamaDokumen1.getText().toString(), binding.ivUploadDokumen1, valDokumenA, IntDokumenA);
                    break;
                case IntDokumenB:
                    SetDataImage(binding.ivUploadDokumen2, imageReturnedIntent, binding.etNamaDokumen2.getText().toString(), IntDokumenB);
                    checkFileTypeThenUpload(nameDokumenB, "_" + binding.etNamaDokumen2.getText().toString(), binding.ivUploadDokumen2, valDokumenB, IntDokumenB);
                    break;
                case IntDokumenC:
                    SetDataImage(binding.ivUploadDokumen3, imageReturnedIntent, binding.etNamaDokumen3.getText().toString(), IntDokumenC);
                    checkFileTypeThenUpload(nameDokumenC, "_" + binding.etNamaDokumen3.getText().toString(), binding.ivUploadDokumen3, valDokumenC, IntDokumenC);
                    break;
                case IntDokumenD:
                    SetDataImage(binding.ivUploadDokumen4, imageReturnedIntent, binding.etNamaDokumen4.getText().toString(), IntDokumenD);
                    checkFileTypeThenUpload(nameDokumenD, "_" + binding.etNamaDokumen4.getText().toString(), binding.ivUploadDokumen4, valDokumenD, IntDokumenD);
                    break;
                case IntDokumenE:
                    SetDataImage(binding.ivUploadDokumen5, imageReturnedIntent, binding.etNamaDokumen5.getText().toString(), IntDokumenE);
                    checkFileTypeThenUpload(nameDokumenE, "_" + binding.etNamaDokumen5.getText().toString(), binding.ivUploadDokumen5, valDokumenE, IntDokumenE);
                    break;
                case IntDokumenF:
                    SetDataImage(binding.ivUploadDokumen6, imageReturnedIntent, binding.etNamaDokumen6.getText().toString(), IntDokumenF);
                    checkFileTypeThenUpload(nameDokumenF, "_" + binding.etNamaDokumen6.getText().toString(), binding.ivUploadDokumen6, valDokumenF, IntDokumenF);
                    break;
            }
        }
    }

    private void SetDataImage(ImageView iv, Intent data, String namaFoto, int kode_upload) {
        if (getPickImageResultUri(data, namaFoto) != null) {
            Bitmap bitmap = null;
            Uri uri;
            if (getPickImageResultUri(data, namaFoto) != null) {
                uri = getPickImageResultUri(data, namaFoto);
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                    bitmap = AppUtil.rotateImageIfRequired(this, bitmap, uri);

                    if (iv != null) {
                        iv.setImageBitmap(bitmap);
                    }

                    // Dokumen Tambahan
                    if (clicker.equalsIgnoreCase("dokumenA")) {
                        dokumenA = bitmap;
                        valDokumenA = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("dokumenB")) {
                        dokumenB = bitmap;
                        valDokumenB = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("dokumenC")) {
                        dokumenC = bitmap;
                        valDokumenC = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("dokumenD")) {
                        dokumenD = bitmap;
                        valDokumenD = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("dokumenE")) {
                        dokumenE = bitmap;
                        valDokumenE = AppUtil.encodeImageTobase64(bitmap);
                    } else if (clicker.equalsIgnoreCase("dokumenF")) {
                        dokumenF = bitmap;
                        valDokumenF = AppUtil.encodeImageTobase64(bitmap);
                    }

                } catch (Exception e) {
                    if (data.getData() != null) {
                        if (iv != null) {
                            iv.setImageDrawable(getDrawable(R.drawable.ic_pdf_hd));
                        }
                        Uri uriPdf = data.getData();

                        // Dokumen Tambahan
                        if (clicker.equalsIgnoreCase("dokumenA")) {
                            valDokumenA = AppUtil.encodeFileToBase64(this, uriPdf);
                        } else if (clicker.equalsIgnoreCase("dokumenB")) {
                            valDokumenB = AppUtil.encodeFileToBase64(this, uriPdf);
                        } else if (clicker.equalsIgnoreCase("dokumenC")) {
                            valDokumenC = AppUtil.encodeFileToBase64(this, uriPdf);
                        } else if (clicker.equalsIgnoreCase("dokumenD")) {
                            valDokumenD = AppUtil.encodeFileToBase64(this, uriPdf);
                        } else if (clicker.equalsIgnoreCase("dokumenE")) {
                            valDokumenE = AppUtil.encodeFileToBase64(this, uriPdf);
                        } else if (clicker.equalsIgnoreCase("dokumenF")) {
                            valDokumenF = AppUtil.encodeFileToBase64(this, uriPdf);
                        }

                    }
                }
            }
        }
    }

    private void checkFileTypeThenUpload(String filename, String fileNameDoc, ImageView
            imageView, String val_base64, int uploadCode) {
        if (tipeFile.equalsIgnoreCase("pdf")) {
            filename = idAplikasi + fileNameDoc + ".pdf";
            uploadFile(val_base64, filename, uploadCode);
        } else {
            filename = idAplikasi + fileNameDoc + ".png";
            uploadFile(val_base64, filename, uploadCode);
        }
    }

    public void uploadFile(String base64, String fileName, int uploadCode) {
        ApiClientAdapter apiClientAdapter = new ApiClientAdapter(this);
        //  dataUser = getListUser();
        binding.loadingLayout.progressbarLoading.setVisibility(View.VISIBLE);
        ReqUploadFile req = new ReqUploadFile();
        //pantekan uid
        req.setFolderId(AppUtil.getIdFolderLogicalDoc());
        req.setLanguage("en");
        req.setFileB64(base64);
        req.setFileName(fileName);

        Call<ParseResponseFile> call = apiClientAdapter.getApiInterface().uploadFileLogicalDoc(req);
        call.enqueue(new Callback<ParseResponseFile>() {
            @Override
            public void onResponse(Call<ParseResponseFile> call, Response<ParseResponseFile> response) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {

                    // Dokumen Tambahan
                    if (uploadCode == IntDokumenA) {
                        idDokumenA = response.body().getId();
                        UploadDokumenTambahan doc = new UploadDokumenTambahan();
                        doc.setFilename(fileName);
                        doc.setImg(idDokumenA);
                        doc.setDescription(binding.etKeteranganDokumen1.getText().toString());
                        doc.setName(binding.etNamaDokumen1.getText().toString());
                        if (DokumenUmum.size() == 0) {
                            DokumenUmum.add(0, doc);
                        } else {
                            DokumenUmum.get(0).setFilename(fileName);
                            DokumenUmum.get(0).setImg(idDokumenA);
                            DokumenUmum.get(0).setDescription(binding.etKeteranganDokumen1.getText().toString());
                            DokumenUmum.get(0).setName(binding.etNamaDokumen1.getText().toString());
                        }
                    } else if (uploadCode == IntDokumenB) {
                        idDokumenB = response.body().getId();
                        UploadDokumenTambahan doc = new UploadDokumenTambahan();
                        doc.setFilename(fileName);
                        doc.setImg(idDokumenB);
                        doc.setDescription(binding.etKeteranganDokumen2.getText().toString());
                        doc.setName(binding.etNamaDokumen2.getText().toString());
                        if (DokumenUmum.size() < 1) {
                            AppUtil.notiferror(ActivityDokumenTambahan.this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
                        } else {
                            if (DokumenUmum.size() <= 1) {
                                DokumenUmum.add(1, doc);
                            } else {
                                DokumenUmum.get(1).setFilename(fileName);
                                DokumenUmum.get(1).setImg(idDokumenB);
                                DokumenUmum.get(1).setDescription(binding.etKeteranganDokumen2.getText().toString());
                                DokumenUmum.get(1).setName(binding.etNamaDokumen2.getText().toString());
                            }
                        }
                    } else if (uploadCode == IntDokumenC) {
                        idDokumenC = response.body().getId();
                        UploadDokumenTambahan doc = new UploadDokumenTambahan();
                        doc.setFilename(fileName);
                        doc.setImg(idDokumenC);
                        doc.setDescription(binding.etKeteranganDokumen3.getText().toString());
                        doc.setName(binding.etNamaDokumen3.getText().toString());
                        if (DokumenUmum.size() < 2) {
                            AppUtil.notiferror(ActivityDokumenTambahan.this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
                        } else {
                            if (DokumenUmum.size() <= 2) {
                                DokumenUmum.add(2, doc);
                            } else {
                                DokumenUmum.get(2).setFilename(fileName);
                                DokumenUmum.get(2).setImg(idDokumenC);
                                DokumenUmum.get(2).setDescription(binding.etKeteranganDokumen3.getText().toString());
                                DokumenUmum.get(2).setName(binding.etNamaDokumen3.getText().toString());
                            }
                        }
                    } else if (uploadCode == IntDokumenD) {
                        idDokumenD = response.body().getId();
                        UploadDokumenTambahan doc = new UploadDokumenTambahan();
                        doc.setFilename(fileName);
                        doc.setImg(idDokumenD);
                        doc.setDescription(binding.etKeteranganDokumen4.getText().toString());
                        doc.setName(binding.etNamaDokumen4.getText().toString());
                        if (DokumenUmum.size() < 3) {
                            AppUtil.notiferror(ActivityDokumenTambahan.this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
                        } else {
                            if (DokumenUmum.size() <= 3) {
                                DokumenUmum.add(3, doc);
                            } else {
                                DokumenUmum.get(3).setFilename(fileName);
                                DokumenUmum.get(3).setImg(idDokumenD);
                                DokumenUmum.get(3).setDescription(binding.etKeteranganDokumen4.getText().toString());
                                DokumenUmum.get(3).setName(binding.etNamaDokumen4.getText().toString());
                            }
                        }
                    } else if (uploadCode == IntDokumenE) {
                        idDokumenE = response.body().getId();
                        UploadDokumenTambahan doc = new UploadDokumenTambahan();
                        doc.setFilename(fileName);
                        doc.setImg(idDokumenE);
                        doc.setDescription(binding.etKeteranganDokumen5.getText().toString());
                        doc.setName(binding.etNamaDokumen5.getText().toString());
                        if (DokumenUmum.size() < 4) {
                            AppUtil.notiferror(ActivityDokumenTambahan.this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
                        } else {
                            if (DokumenUmum.size() <= 4) {
                                DokumenUmum.add(4, doc);
                            } else {
                                DokumenUmum.get(4).setFilename(fileName);
                                DokumenUmum.get(4).setImg(idDokumenE);
                                DokumenUmum.get(4).setDescription(binding.etKeteranganDokumen5.getText().toString());
                                DokumenUmum.get(4).setName(binding.etNamaDokumen5.getText().toString());
                            }
                        }
                        AppUtil.notifsuccess(ActivityDokumenTambahan.this, findViewById(android.R.id.content), "Upload Berhasil");
                    } else if (uploadCode == IntDokumenF) {
                        idDokumenF = response.body().getId();
                        UploadDokumenTambahan doc = new UploadDokumenTambahan();
                        doc.setFilename(fileName);
                        doc.setImg(idDokumenF);
                        doc.setDescription(binding.etKeteranganDokumen6.getText().toString());
                        doc.setName(binding.etNamaDokumen6.getText().toString());
                        if (DokumenUmum.size() < 5) {
                            AppUtil.notiferror(ActivityDokumenTambahan.this, findViewById(android.R.id.content), "Upload Dokuman Sebelumnya Terlebih Dahulu");
                        } else {
                            if (DokumenUmum.size() <= 5) {
                                DokumenUmum.add(5, doc);
                            } else {
                                DokumenUmum.get(5).setFilename(fileName);
                                DokumenUmum.get(5).setImg(idDokumenF);
                                DokumenUmum.get(5).setDescription(binding.etKeteranganDokumen6.getText().toString());
                                DokumenUmum.get(5).setName(binding.etNamaDokumen6.getText().toString());
                            }
                        }
                        AppUtil.notifsuccess(ActivityDokumenTambahan.this, findViewById(android.R.id.content), "Upload Berhasil");
                    }

                } else {
                    AppUtil.notiferror(ActivityDokumenTambahan.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseFile> call, Throwable t) {
                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                AppUtil.notiferror(ActivityDokumenTambahan.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });

    }

    @Override
    public void onSelect(String title, MGenericModel data) {
        if (title.equalsIgnoreCase(binding.tfStatusKepemilikan.getLabelText())) {
            binding.etStatusKepemilikan.setText(data.getDESC());
            id = Integer.valueOf(data.getID());
        }
    }

    private void checkFileTypeThenSet(Context context, String idDok, ImageView imageView, String fileName) {

        if (fileName.substring(fileName.length() - 3, fileName.length()).equalsIgnoreCase("pdf")) {

            if (idDok.length() < 10) {
                loadFileJson(idDok, imageView);
            } else {
                AppUtil.convertBase64ToFileWithOnClick(context, idDok, imageView, fileName);
            }

        } else {

            if (idDok.length() < 10) {
                AppUtil.setImageGlide(context, idDok, imageView);
            } else {
                AppUtil.convertBase64ToImage(idDok, imageView);
            }

        }
    }

    public void loadFileJson(String idFoto, ImageView imageView) {
        ApiClientAdapter apiClientAdapter = new ApiClientAdapter(ActivityDokumenTambahan.this);
        Call<ParseResponseLogicalDoc> call = apiClientAdapter.getApiInterface().getFileJson(idFoto);
        call.enqueue(new Callback<ParseResponseLogicalDoc>() {
            @Override
            public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
//                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getBinaryData() != null) {
                        AppUtil.convertBase64ToFileWithOnClick(ActivityDokumenTambahan.this, response.body().getBinaryData(), imageView, response.body().getFileName());
                    } else {
                        AppUtil.notiferror(ActivityDokumenTambahan.this, findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                    }


                } else {
                    AppUtil.notiferror(ActivityDokumenTambahan.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                AppUtil.notiferror(ActivityDokumenTambahan.this, findViewById(android.R.id.content), "Terjadi kesalahan");
                t.printStackTrace();
            }
        });

    }
}
