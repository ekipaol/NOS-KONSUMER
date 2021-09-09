package com.application.bris.ikurma_nos_konsumer.view.corelayout.login;

import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.general.login;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.baseapp.RouteApp;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.model.general.dataLogin;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.Constants;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.CoreLayoutActivity;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.activation.WelcomeActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ConfirmListener{
    @BindView(R.id.iv_avatarlogin)
    ImageView iv_avatarlogin;
    @BindView(R.id.tv_titlelogin)
    TextView tv_titlelogin;
    @BindView(R.id.tv_subtitlelogin)
    TextView tv_subtitlelogin;
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private boolean expiredToken=false;

    public int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this, 0);
        appPreferences = new AppPreferences(this);
        backgroundStatusBar();
        loadProfil();
        btn_login.setOnClickListener(this);
        iv_avatarlogin.setOnClickListener(this);
        expiredToken=getIntent().getBooleanExtra("expiredToken",false);

        if(expiredToken){
            SweetAlertDialog dialog=new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.WARNING_TYPE);
                                dialog.setCanceledOnTouchOutside(false);
                                dialog.setTitle("Sesi habis");
                                dialog.setContentText("Silahkan lakukan login ulang untuk kembali dapat mengakses aplikasi i-Kurma");
                                dialog.setConfirmText("OK");

                                    dialog.show();
                                    dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            dialog.dismissWithAnimation();
                                            et_password.requestFocus();
                                        }
                                    });
        }



    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    private void loadProfil(){

        if (appPreferences.getImageProfilBase64().equalsIgnoreCase("")){
            Glide.
                    with(this)
                    .load(R.drawable.ic_generalusericon)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(iv_avatarlogin);
        }
        else{
            Glide
                    .with(this)
                    .asBitmap()
                    .load(AppUtil.decodeImageTobase64(appPreferences.getImageProfilBase64()))
                    .centerCrop()
                    .placeholder(R.drawable.banner_placeholder)
                    .into(iv_avatarlogin);
        }

        String title = "Welcome <b>"+appPreferences.getNama()+"</b>,";
        String subtitle = "Login to <b> Continue </b>";
        tv_titlelogin.setText(Html.fromHtml(title));
        tv_subtitlelogin.setText(Html.fromHtml(subtitle));
        String username = appPreferences.getUsername();
        et_username.setEnabled(false);
        et_username.setText(username);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                if (validateForm())
                    doLogin();
                break;
            case R.id.iv_avatarlogin:
            {
                ++counter;
                if (counter > 9){
                    CustomDialog.DialogInput(this, "Input Developer Key", this);
                    counter = 0;
                }
            }
            break;
        }
    }

    private void doLogin() {
        loading.setVisibility(View.VISIBLE);
        login req = new login(appPreferences.getUsername(), AppUtil.hashMd5(et_password.getText().toString().trim()).toUpperCase(), getDeviceId(), "BRISI_KONSUMER");
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().login(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if(response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            dataLogin data = gson.fromJson(response.body().getData().toString(), dataLogin.class);
                            setPrefLogin(data);
                            RouteApp router = new RouteApp(LoginActivity.this);
                            router.openActivityAndClearAllPrevious(CoreLayoutActivity.class);
                        }
                        else if (response.body().getStatus().equalsIgnoreCase("21")){
                            CustomDialog.DialogError(LoginActivity.this, "Upss Sorry", "Akun anda telah digunakan di perangkat lain, Silahkan aktivasi ulang jika ingin menggunakan di perangkat ini.", LoginActivity.this);
                        }
                        else {
                            AppUtil.notiferror(LoginActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(LoginActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(LoginActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public boolean validateForm(){
        if (et_password.getText().toString().trim().isEmpty() || et_password.getText().toString().trim().equalsIgnoreCase("")){
            AppUtil.notifwarning(this, findViewById(android.R.id.content), "Password tidak boleh kosong");
            return false;
        }
        return true;
    }

    public void setPrefLogin(dataLogin data){

        appPreferences.setRoleType(AppUtil.encrypt(String.valueOf(data.getRole_type())));
        appPreferences.setJabatan(AppUtil.encrypt(data.getJabatan()));
        appPreferences.setNamaKantor(AppUtil.encrypt(data.getNama_kantor()));
        appPreferences.setKodeKantor(AppUtil.encrypt(data.getKode_skk()));
        appPreferences.setNamaKanwil(AppUtil.encrypt(data.getNama_kanwil()));
        appPreferences.setKodeKanwil(AppUtil.encrypt(data.getKode_kanwil()));
        appPreferences.setFidRole(AppUtil.encrypt(String.valueOf(data.getFid_role())));
        appPreferences.setToken(data.getToken());
        appPreferences.setUid(AppUtil.encrypt(String.valueOf(data.getUid())));
        appPreferences.setNik(AppUtil.encrypt(data.getNik()));
        appPreferences.setKodeAo(AppUtil.encrypt(data.getKode_ao()));
        appPreferences.setKodeCabang(AppUtil.encrypt(data.getKode_cabang()));

        if(data.getCbAmanah()!=null){
            appPreferences.setCbAmanah(AppUtil.encrypt(data.getCbAmanah()));
        }
        else{
            appPreferences.setCbAmanah(AppUtil.encrypt("false"));
        }

    }

    private String getDeviceId() {
        HashMap<String, String> deviceInfo = AppUtil.getDeviceInfo(this);
        String deviceId = deviceInfo.get(Constants.DEVICE_ID);
        return deviceId;
    }

    @Override
    public void success(boolean val) {
        if (val){
            appPreferences.clearAll(this);
            final Realm realm = Realm.getDefaultInstance();
            try {
                realm.close();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.deleteAll();
                    }
                });
            }
            catch (Exception e){
                e.printStackTrace();
            }

            RouteApp routeApp = new RouteApp(this);
            routeApp.openActivityAndClearAllPrevious(WelcomeActivity.class);
        }
    }

    @Override
    public void confirm(boolean val) {
        if (val){
            Bundle mbundle = new Bundle();
            mbundle.putString("type", "bdlogin");
            RouteApp router = new RouteApp(LoginActivity.this);
            router.openActivityWithDataAndClearAllPrevious(LoginActivity2.class, mbundle);
            counter = 0;
        }
        else {
            AppUtil.showToastShort(this, "Wrong Developer Key");
        }
    }
}
