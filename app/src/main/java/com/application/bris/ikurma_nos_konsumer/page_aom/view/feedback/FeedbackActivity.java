package com.application.bris.ikurma_nos_konsumer.page_aom.view.feedback;


import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.general.SimpanFeedback;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class FeedbackActivity extends AppCompatActivity {

    @BindView(R.id.tf_nama_feedback)
    TextFieldBoxes tf_nama_feedback;
    @BindView(R.id.et_nama_feedback)
    EditText et_nama_feedback;


    @BindView(R.id.tf_kantor_feedback)
    TextFieldBoxes tf_kantor_feedback;
    @BindView(R.id.et_kantor_feedback)
    EditText et_kantor_feedback;


    @BindView(R.id.tf_jabatan_feedback)
    TextFieldBoxes tf_jabatan_feedback;
    @BindView(R.id.et_jabatan_feedback)
    EditText et_jabatan_feedback;


    @BindView(R.id.tf_catatan_feedback)
    TextFieldBoxes tf_catatan_feedback;
    @BindView(R.id.et_catatan_feedback)
    EditText et_catatan_feedback;


    @BindView(R.id.btn_feedback)
    Button btn_feedback;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    private ApiClientAdapter apiClientAdapter;
    SweetAlertDialog dialog;
    AppPreferences appPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        appPreferences=new AppPreferences(FeedbackActivity.this);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Kritik dan Saran");

        et_nama_feedback.setFocusable(false);
        et_kantor_feedback.setFocusable(false);
        et_jabatan_feedback.setFocusable(false);

        et_nama_feedback.setText(appPreferences.getNama());
        et_kantor_feedback.setText(appPreferences.getNamaKantor());
        et_jabatan_feedback.setText(appPreferences.getJabatan());


        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new SweetAlertDialog(FeedbackActivity.this,SweetAlertDialog.WARNING_TYPE);
                dialog.setTitleText("Kirim?");
                dialog.setContentText("Apakah anda yakin mengirim feedback anda?");
                dialog.setConfirmText("Ya");
                dialog.setCancelText("Batal");
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        if(validateForm()){
                            simpanData();
                        }

                    }
                });
                dialog.show();


            }
        });




    }

    private boolean validateForm(){
        if(et_catatan_feedback.getText().toString().isEmpty()){
            tf_catatan_feedback.setError("Kolom ini tidak boleh kosong",true);
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        simpanData();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }


    private void simpanData() {
//        loading.setVisibility(View.VISIBLE);
        dialog=new SweetAlertDialog(FeedbackActivity.this,SweetAlertDialog.PROGRESS_TYPE);
        dialog.setContentText("Menyimpan");
        dialog.showCancelButton(false);
        dialog.show();

        SimpanFeedback req = new SimpanFeedback(); //101928



        //pantekan
//        req.setIdAplikasi(101928);
//        req.setCif(81862);

        req.setNAMA(appPreferences.getNama());
        req.setJABATAN(appPreferences.getJabatan());
        req.setKANTOR(appPreferences.getNamaKantor());
        req.setFID_UID(appPreferences.getUid());
        req.setCATATAN(et_catatan_feedback.getText().toString());
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().simpanFeedback(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                //pantekan
//                Toast.makeText(ScoringKonsumerActivity.this, "id aplikasi masih hardcoded", Toast.LENGTH_SHORT).show();

//                loading.setVisibility(View.GONE);

                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            dialog.setTitle("Terima Kasih");
                            dialog.setContentText("Feedback anda berhasil dikirim, semoga saran anda dapat membantu meningkatkan kualitas aplikasi ini \n");
                            dialog.setConfirmText("OK");
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    dialog.dismissWithAnimation();
                                    finish();
                                }
                            });
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
//                        AppUtil.notiferror(FeedbackActivity.this, findViewById(android.R.id.content), error.getMessage());
                        dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitle("Terjadi Kesalahan");
                        dialog.setContentText(error.getMessage());
                        dialog.setConfirmText("Coba Lagi");
                        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                simpanData();
                            }
                        });
                        dialog.setCancelText("Batal");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(FeedbackActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));

                dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                dialog.setTitle("Terjadi Kesalahan");
                dialog.setContentText("Gagal Terhubung Ke Server");
                dialog.setConfirmText("Coba Lagi");
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        simpanData();
                    }
                });
                dialog.setCancelText("Batal");
            }
        });
    }
}
