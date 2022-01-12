package com.application.bris.ikurma_nos_konsumer.view.corelayout.login;

import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.print.PdfConverter;
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
import com.application.bris.ikurma_nos_konsumer.model.general.DataLoginBsi;
import com.application.bris.ikurma_nos_konsumer.model.general.dataLogin;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.Constants;
import com.application.bris.ikurma_nos_konsumer.util.service_encrypt.MagicCryptHelper;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.CoreLayoutActivity;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.activation.WelcomeActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity2 extends AppCompatActivity implements View.OnClickListener, ConfirmListener{
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
    private boolean expiredToken;
    private DataLoginBsi dataUserBsi;

    public static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this,"http://10.0.116.37:8054/");
        appPreferences = new AppPreferences(this);
        appPreferences.setNama(AppUtil.encrypt("Developer"));
        backgroundStatusBar();
        Bundle extras = getIntent().getExtras();
//        if (extras != null){
//            if (extras.getString("type").equalsIgnoreCase("bdwelcome")){
//                loadProfilWelcome();
//            }
//            else{
//                loadProfilLogin();
//            }
//        }
        loadProfilLogin();
        btn_login.setOnClickListener(this);
        iv_avatarlogin.setOnClickListener(this);

        expiredToken=getIntent().getBooleanExtra("expiredToken",false);

        if(expiredToken){
            SweetAlertDialog dialog=new SweetAlertDialog(LoginActivity2.this, SweetAlertDialog.WARNING_TYPE);
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

    private void loadProfilWelcome(){
        try {
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
            String title = "Welcome <b> Developer</b>";
            String subtitle = "Login to <b> Continue </b>";
            tv_titlelogin.setText(Html.fromHtml(title));
            tv_subtitlelogin.setText(Html.fromHtml(subtitle));
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }


    private void loadProfilLogin(){
        try {
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
            et_username.setText(username);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                doLogin();
                break;
            case R.id.iv_avatarlogin:

             String htmlString="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n" +
                     "<HTML>\n" +
                     "    <HEAD>\n" +
                     "    <META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                     "    <TITLE>Akad Murabahah</TITLE>\n" +
                     "    <META name=\"generator\" content=\"BCL easyConverter SDK 5.0.252\">\n" +
                     "    <META name=\"title\" content=\"Akad Murabahah Tanpa Agunan\">\n" +
                     " \n" +
                     "    <STYLE type=\"text/css\">\n" +
                     "        body {margin-top: 0px;margin-left: 0px;}\n" +
                     " \n" +
                     " \n" +
                     "        #page-number:after {counter-increment: page_number;content: \"Page \" counter(page_number);}\n" +
                     " \n" +
                     " \n" +
                     "        #page_1 {position:relative; overflow: hidden;margin: 20px 0px 29px 45px;padding: 0px;border: none;width: 749px;}\n" +
                     "        #page_1 #id1_1 {border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 749px;overflow: hidden;}\n" +
                     "        #page_1 #id1_2 {border:none;margin: 0px 0px 0px 292px;padding: 0px;border:none;width: 479px;overflow: hidden;}\n" +
                     "        #page_1 #id1_2 #id1_2_1 {float:left;border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 354px;overflow: hidden;}\n" +
                     "        #page_1 #id1_2 #id1_2_2 {float:left;border:none;margin: 36px 0px 0px 0px;padding: 0px;border:none;width: 125px;overflow: hidden;}\n" +
                     " \n" +
                     " \n" +
                     "        #page_2 {position:relative; overflow: hidden;margin: 20px 0px 29px 45px;padding: 0px;border: none;width: 749px;}\n" +
                     "        #page_2 #id2_1 {border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 749px;overflow: hidden;}\n" +
                     "        #page_2 #id2_2 {border:none;margin: 214px 0px 0px 270px;padding: 0px;border:none;width: 479px;overflow: hidden;}\n" +
                     "        #page_2 #id2_2 #id2_2_1 {float:left;border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 354px;overflow: hidden;}\n" +
                     "        #page_2 #id2_2 #id2_2_2 {float:left;border:none;margin: 36px 0px 0px 0px;padding: 0px;border:none;width: 125px;overflow: hidden;}\n" +
                     " \n" +
                     "        #page_2 #p2dimg1 {position:absolute;top:313px;left:31px;z-index:-1;width:9px;height:374px;}\n" +
                     "        #page_2 #p2dimg1 #p2img1 {width:9px;height:374px;}\n" +
                     " \n" +
                     " \n" +
                     "        #page_3 {position:relative; overflow: hidden;margin: 20px 0px 29px 45px;padding: 0px;border: none;width: 749px;}\n" +
                     "        #page_3 #id3_1 {border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 749px;overflow: hidden;}\n" +
                     "        #page_3 #id3_2 {border:none;margin: 0px 0px 0px 67px;padding: 0px;border:none;width: 682px;overflow: hidden;}\n" +
                     "        #page_3 #id3_2 #id3_2_1 {float:left;border:none;margin: 4px 0px 0px 0px;padding: 0px;border:none;width: 347px;overflow: hidden;}\n" +
                     "        #page_3 #id3_2 #id3_2_2 {float:left;border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 335px;overflow: hidden;}\n" +
                     "        #page_3 #id3_3 {border:none;margin: 134px 0px 0px 270px;padding: 0px;border:none;width: 479px;overflow: hidden;}\n" +
                     "        #page_3 #id3_3 #id3_3_1 {float:left;border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 354px;overflow: hidden;}\n" +
                     "        #page_3 #id3_3 #id3_3_2 {float:left;border:none;margin: 36px 0px 0px 0px;padding: 0px;border:none;width: 125px;overflow: hidden;}\n" +
                     " \n" +
                     " \n" +
                     "        #page_4 {position:relative; overflow: hidden;margin: 20px 0px 30px 0px;padding: 0px;border: none;width: 794px;height: 1073px;}\n" +
                     "        #page_4 #id4_1 {border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 794px;overflow: hidden;}\n" +
                     "        #page_4 #id4_2 {border:none;margin: 20px 0px 0px 341px;padding: 0px;border:none;width: 453px;overflow: hidden;}\n" +
                     "        #page_4 #id4_2 #id4_2_1 {float:left;border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 73px;overflow: hidden;}\n" +
                     "        #page_4 #id4_2 #id4_2_2 {float:left;border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 380px;overflow: hidden;}\n" +
                     " \n" +
                     "        #page_4 #p4dimg1 {position:absolute;top:109px;left:12px;z-index:-1;width:771px;height:964px;}\n" +
                     "        #page_4 #p4dimg1 #p4img1 {width:771px;height:964px;}\n" +
                     " \n" +
                     " \n" +
                     "        #page_5 {position:relative; overflow: hidden;margin: 20px 0px 30px 0px;padding: 0px;border: none;width: 794px;height: 1073px;}\n" +
                     "        #page_5 #id5_1 {border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 794px;overflow: hidden;}\n" +
                     "        #page_5 #id5_2 {border:none;margin: 20px 0px 0px 341px;padding: 0px;border:none;width: 453px;overflow: hidden;}\n" +
                     "        #page_5 #id5_2 #id5_2_1 {float:left;border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 73px;overflow: hidden;}\n" +
                     "        #page_5 #id5_2 #id5_2_2 {float:left;border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 380px;overflow: hidden;}\n" +
                     " \n" +
                     "        #page_5 #p5dimg1 {position:absolute;top:1037px;left:315px;z-index:-1;width:166px;height:36px;}\n" +
                     "        #page_5 #p5dimg1 #p5img1 {width:166px;height:36px;}\n" +
                     " \n" +
                     " \n" +
                     "        #page_6 {position:relative; overflow: hidden;margin: 20px 0px 30px 45px;padding: 0px;border: none;width: 749px;height: 1073px;}\n" +
                     "        #page_6 #id6_1 {border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 749px;overflow: hidden;}\n" +
                     "        #page_6 #id6_2 {border:none;margin: 20px 0px 0px 296px;padding: 0px;border:none;width: 453px;overflow: hidden;}\n" +
                     "        #page_6 #id6_2 #id6_2_1 {float:left;border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 73px;overflow: hidden;}\n" +
                     "        #page_6 #id6_2 #id6_2_2 {float:left;border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 380px;overflow: hidden;}\n" +
                     " \n" +
                     "        #page_6 #p6dimg1 {position:absolute;top:1037px;left:270px;z-index:-1;width:166px;height:36px;}\n" +
                     "        #page_6 #p6dimg1 #p6img1 {width:166px;height:36px;}\n" +
                     " \n" +
                     " \n" +
                     "        #page_7 {position:relative; overflow: hidden;margin: 20px 0px 30px 45px;padding: 0px;border: none;width: 749px;height: 1073px;}\n" +
                     "        #page_7 #id7_1 {border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 749px;overflow: hidden;}\n" +
                     "        #page_7 #id7_2 {border:none;margin: 20px 0px 0px 296px;padding: 0px;border:none;width: 453px;overflow: hidden;}\n" +
                     "        #page_7 #id7_2 #id7_2_1 {float:left;border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 73px;overflow: hidden;}\n" +
                     "        #page_7 #id7_2 #id7_2_2 {float:left;border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 380px;overflow: hidden;}\n" +
                     " \n" +
                     "        #page_7 #p7dimg1 {position:absolute;top:1037px;left:270px;z-index:-1;width:166px;height:36px;}\n" +
                     "        #page_7 #p7dimg1 #p7img1 {width:166px;height:36px;}\n" +
                     " \n" +
                     " \n" +
                     "        #page_8 {position:relative; overflow: hidden;margin: 20px 0px 30px 45px;padding: 0px;border: none;width: 749px;height: 1073px;}\n" +
                     "        #page_8 #id8_1 {border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 749px;overflow: hidden;}\n" +
                     "        #page_8 #id8_2 {border:none;margin: 20px 0px 0px 296px;padding: 0px;border:none;width: 453px;overflow: hidden;}\n" +
                     "        #page_8 #id8_2 #id8_2_1 {float:left;border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 73px;overflow: hidden;}\n" +
                     "        #page_8 #id8_2 #id8_2_2 {float:left;border:none;margin: 0px 0px 0px 0px;padding: 0px;border:none;width: 380px;overflow: hidden;}\n" +
                     "e\n" +
                     "        #page_8 #p8dimg1 {position:absolute;top:1037px;left:270px;z-index:-1;width:166px;height:36px;}\n" +
                     "        #page_8 #p8dimg1 #p8img1 {width:166px;height:36px;}\n" +
                     " \n" +
                     " \n" +
                     "        .ft0{font: italic 13px 'Times';text-decoration: underline;color: #999999;line-height: 15px;}\n" +
                     "        .ft1{font: bold 15px 'Helvetica';line-height: 18px;}\n" +
                     "        .ft2{font: bold 14px 'Helvetica';line-height: 16px;}\n" +
                     "        .ft3{font: 14px 'Helvetica';line-height: 16px;}\n" +
                     "        .ft4{font: 12px 'Helvetica';line-height: 15px;}\n" +
                     "        .ft5{font: bold 14px 'Helvetica';margin-left: 22px;line-height: 17px;}\n" +
                     "        .ft6{font: 14px 'Helvetica';line-height: 17px;}\n" +
                     "        .ft7{font: 14px 'Helvetica';margin-left: 22px;line-height: 16px;}\n" +
                     "        .ft8{font: 13px 'Helvetica';line-height: 16px;}\n" +
                     "        .ft9{font: 1px 'Helvetica';line-height: 1px;}\n" +
                     "        .ft10{font: 14px 'Helvetica';margin-left: 22px;line-height: 17px;}\n" +
                     "        .ft11{font: 13px 'Helvetica';margin-left: 22px;line-height: 17px;}\n" +
                     "        .ft12{font: 13px 'Helvetica';line-height: 17px;}\n" +
                     "        .ft13{font: 14px 'Helvetica';margin-left: 23px;line-height: 16px;}\n" +
                     "        .ft14{font: 12px 'Helvetica';color: #adb5bd;line-height: 15px;}\n" +
                     "        .ft15{font: bold 12px 'Helvetica';color: #cccccc;line-height: 15px;}\n" +
                     "        .ft16{font: 14px 'Times';line-height: 16px;}\n" +
                     "        .ft17{font: 14px 'Helvetica';margin-left: 39px;line-height: 16px;}\n" +
                     "        .ft18{font: 14px 'Helvetica';margin-left: 23px;line-height: 17px;}\n" +
                     "        .ft19{font: bold 14px 'Helvetica';line-height: 17px;}\n" +
                     "        .ft20{font: 14px 'Helvetica';margin-left: 4px;line-height: 16px;}\n" +
                     "        .ft21{font: 14px 'Helvetica';margin-left: 3px;line-height: 16px;}\n" +
                     "        .ft22{font: 14px 'Helvetica';color: #333333;line-height: 16px;}\n" +
                     "        .ft23{font: 15px 'Helvetica';line-height: 17px;}\n" +
                     "        .ft24{font: 11px 'Times';color: #808080;line-height: 14px;}\n" +
                     "        .ft25{font: bold 14px 'Helvetica';color: #ffffff;line-height: 16px;}\n" +
                     "        .ft26{font: 1px 'Helvetica';line-height: 2px;}\n" +
                     "        .ft27{font: 8px 'Helvetica';line-height: 10px;}\n" +
                     " \n" +
                     " \n" +
                     "        .p0{text-align: left;padding-left: 454px;margin-top: 0px;margin-bottom: 0px;}\n" +
                     "        .p1{text-align: left;padding-left: 133px;margin-top: 11px;margin-bottom: 0px;}\n" +
                     "        .p2{text-align: left;padding-left: 313px;margin-top: 0px;margin-bottom: 0px;}\n" +
                     "        .p3{text-align: left;margin-top: 17px;margin-bottom: 0px;}\n" +
                     "        .p4{text-align: left;margin-top: 0px;margin-bottom: 0px;white-space: nowrap;}\n" +
                     "        .p5{text-align: left;margin-top: 0px;margin-bottom: 0px;white-space: nowrap;}\n" +
                     "        .p5::after{display:inline-block;width:0;white-space:nowrap;content:\" ..............................................\"}\n" +
                     "        .p6{text-align: left;margin-top: 0px;margin-bottom: 0px;white-space: nowrap;}\n" +
                     "        .p6::after{display:inline-block;width:0;white-space:nowrap;content:\" ....................................................................\"}\n" +
                     "        .p7{text-align: left;padding-left: 30px;margin-top: 0px;margin-bottom: 0px;white-space: nowrap;}\n" +
                     "        .p8{text-align: left;padding-left: 34px;padding-right: 23px;margin-top: 20px;margin-bottom: 0px;text-indent: -34px;}\n" +
                     "        .p9{text-align: left;padding-left: 34px;margin-top: 1px;margin-bottom: 0px;}\n" +
                     "        .p10{text-align: justify;padding-left: 34px;padding-right: 27px;margin-top: 1px;margin-bottom: 0px;text-indent: -34px;}\n" +
                     "        .p11{text-align: left;margin-top: 19px;margin-bottom: 0px;}\n" +
                     "        .p12{text-align: left;margin-top: 21px;margin-bottom: 0px;}\n" +
                     "        .p13{text-align: left;padding-left: 34px;padding-right: 27px;margin-top: 1px;margin-bottom: 0px;text-indent: -34px;}\n" +
                     "        .p14{text-align: left;margin-top: 2px;margin-bottom: 0px;}\n" +
                     "        .p15{text-align: left;padding-left: 34px;margin-top: 0px;margin-bottom: 0px;white-space: nowrap;}\n" +
                     "        .p15::after{display:inline-block;width:0;white-space:nowrap;content:\" ...................................\"}\n" +
                     "        .p16{text-align: left;padding-left: 320px;margin-top: 20px;margin-bottom: 0px;}\n" +
                     "        .p17{text-align: left;padding-left: 245px;margin-top: 1px;margin-bottom: 0px;}\n" +
                     "        .p18{text-align: justify;padding-right: 46px;margin-top: 0px;margin-bottom: 0px;}\n" +
                     "        .p19{text-align: left;padding-left: 320px;margin-top: 19px;margin-bottom: 0px;}\n" +
                     "        .p20{text-align: left;padding-left: 190px;margin-top: 1px;margin-bottom: 0px;}\n" +
                     "        .p21{text-align: left;margin-top: 0px;margin-bottom: 0px;}\n" +
                     "        .p22{text-align: justify;padding-left: 34px;padding-right: 21px;margin-top: 1px;margin-bottom: 0px;text-indent: -34px;}\n" +
                     "        .p23{text-align: left;padding-left: 34px;padding-right: 27px;margin-top: 0px;margin-bottom: 0px;text-indent: -34px;}\n" +
                     "        .p24{text-align: left;padding-left: 34px;padding-right: 27px;margin-top: 2px;margin-bottom: 0px;text-indent: -34px;}\n" +
                     "        .p25{text-align: left;padding-left: 66px;margin-top: 0px;margin-bottom: 0px;}\n" +
                     "        .p26{text-align: left;padding-left: 26px;margin-top: 0px;margin-bottom: 0px;white-space: nowrap;}\n" +
                     "        .p27{text-align: left;padding-left: 17px;margin-top: 0px;margin-bottom: 0px;white-space: nowrap;}\n" +
                     "        .p28{text-align: left;padding-left: 47px;padding-right: 27px;margin-top: 12px;margin-bottom: 0px;text-indent: -47px;}\n" +
                     "        .p29{text-align: left;padding-left: 320px;margin-top: 1px;margin-bottom: 0px;}\n" +
                     "        .p30{text-align: left;padding-left: 226px;margin-top: 1px;margin-bottom: 0px;}\n" +
                     "        .p31{text-align: justify;padding-left: 34px;padding-right: 27px;margin-top: 0px;margin-bottom: 0px;text-indent: -34px;}\n" +
                     "        .p32{text-align: left;padding-left: 320px;margin-top: 21px;margin-bottom: 0px;}\n" +
                     "        .p33{text-align: left;padding-left: 82px;margin-top: 0px;margin-bottom: 0px;}\n" +
                     "        .p34{text-align: justify;padding-left: 35px;padding-right: 27px;margin-top: 1px;margin-bottom: 0px;text-indent: -35px;}\n" +
                     "        .p35{text-align: left;padding-left: 35px;padding-right: 27px;margin-top: 1px;margin-bottom: 0px;text-indent: -35px;}\n" +
                     "        .p36{text-align: left;margin-top: 3px;margin-bottom: 0px;}\n" +
                     "        .p37{text-align: left;padding-left: 35px;margin-top: 1px;margin-bottom: 0px;}\n" +
                     "        .p38{text-align: left;margin-top: 1px;margin-bottom: 0px;}\n" +
                     "        .p39{text-align: justify;padding-left: 35px;padding-right: 25px;margin-top: 1px;margin-bottom: 0px;text-indent: -35px;}\n" +
                     "        .p40{text-align: left;padding-left: 35px;padding-right: 27px;margin-top: 2px;margin-bottom: 0px;text-indent: -35px;}\n" +
                     "        .p41{text-align: left;padding-left: 320px;margin-top: 11px;margin-bottom: 0px;}\n" +
                     "        .p42{text-align: left;padding-left: 325px;margin-top: 0px;margin-bottom: 0px;}\n" +
                     "        .p43{text-align: justify;padding-right: 40px;margin-top: 1px;margin-bottom: 0px;}\n" +
                     "        .p44{text-align: left;padding-left: 286px;margin-top: 1px;margin-bottom: 0px;}\n" +
                     "        .p45{text-align: justify;padding-left: 34px;padding-right: 27px;margin-top: 2px;margin-bottom: 0px;}\n" +
                     "        .p46{text-align: left;padding-left: 6px;margin-top: 0px;margin-bottom: 0px;white-space: nowrap;}\n" +
                     "        .p47{text-align: left;padding-left: 5px;margin-top: 0px;margin-bottom: 0px;white-space: nowrap;}\n" +
                     "        .p48{text-align: left;padding-left: 34px;margin-top: 2px;margin-bottom: 0px;}\n" +
                     "        .p49{text-align: left;padding-left: 34px;margin-top: 4px;margin-bottom: 0px;}\n" +
                     "        .p50{text-align: left;padding-left: 316px;margin-top: 0px;margin-bottom: 0px;}\n" +
                     "        .p51{text-align: justify;padding-left: 34px;padding-right: 23px;margin-top: 0px;margin-bottom: 0px;text-indent: -34px;}\n" +
                     "        .p52{text-align: left;padding-left: 34px;padding-right: 27px;margin-top: 18px;margin-bottom: 0px;text-indent: -34px;}\n" +
                     "        .p53{text-align: left;padding-left: 67px;margin-top: 17px;margin-bottom: 0px;}\n" +
                     "        .p54{text-align: left;margin-top: 43px;margin-bottom: 0px;}\n" +
                     "        .p55{text-align: left;margin-top: 40px;margin-bottom: 0px;}\n" +
                     "        .p56{text-align: right;padding-right: 58px;margin-top: 0px;margin-bottom: 0px;}\n" +
                     "        .p57{text-align: left;padding-left: 45px;margin-top: 11px;margin-bottom: 0px;}\n" +
                     "        .p58{text-align: left;padding-left: 45px;margin-top: 1px;margin-bottom: 0px;}\n" +
                     "        .p59{text-align: left;margin-top: 36px;margin-bottom: 0px;}\n" +
                     "        .p60{text-align: left;padding-left: 381px;margin-top: 910px;margin-bottom: 0px;}\n" +
                     "        .p61{text-align: left;padding-left: 45px;margin-top: 0px;margin-bottom: 0px;}\n" +
                     "        .p62{text-align: left;padding-left: 1px;margin-top: 0px;margin-bottom: 0px;white-space: nowrap;}\n" +
                     "        .p63{text-align: right;padding-right: 38px;margin-top: 0px;margin-bottom: 0px;white-space: nowrap;}\n" +
                     "        .p64{text-align: center;padding-right: 2px;margin-top: 0px;margin-bottom: 0px;white-space: nowrap;}\n" +
                     "        .p65{text-align: left;padding-left: 381px;margin-top: 902px;margin-bottom: 0px;}\n" +
                     "        .p66{text-align: left;padding-left: 289px;margin-top: 11px;margin-bottom: 0px;}\n" +
                     "        .p67{text-align: left;margin-top: 0px;margin-bottom: 0px;white-space: nowrap;}\n" +
                     "        .p67::after{display:inline-block;width:0;white-space:nowrap;content:\" ..................................\"}\n" +
                     "        .p68{text-align: right;margin-top: 0px;margin-bottom: 0px;white-space: nowrap;}\n" +
                     "        .p69{text-align: right;padding-right: 157px;margin-top: 0px;margin-bottom: 0px;white-space: nowrap;}\n" +
                     "        .p70{text-align: justify;padding-right: 46px;margin-top: 20px;margin-bottom: 0px;}\n" +
                     "        .p71{text-align: left;padding-right: 625px;margin-top: 1px;margin-bottom: 0px;}\n" +
                     "        .p72{text-align: left;padding-left: 262px;margin-top: 21px;margin-bottom: 0px;}\n" +
                     "        .p73{text-align: left;padding-right: 46px;margin-top: 20px;margin-bottom: 0px;}\n" +
                     "        .p74{text-align: left;padding-left: 336px;margin-top: 608px;margin-bottom: 0px;}\n" +
                     "        .p75{text-align: justify;padding-right: 46px;margin-top: 12px;margin-bottom: 0px;}\n" +
                     "        .p76{text-align: justify;padding-right: 46px;margin-top: 19px;margin-bottom: 0px;}\n" +
                     "        .p77{text-align: left;padding-left: 336px;margin-top: 565px;margin-bottom: 0px;}\n" +
                     "        .p78{text-align: left;padding-left: 232px;margin-top: 11px;margin-bottom: 0px;}\n" +
                     "        .p79{text-align: left;padding-left: 2px;margin-top: 0px;margin-bottom: 0px;white-space: nowrap;}\n" +
                     "        .p79::after{display:inline-block;width:0;white-space:nowrap;content:\" ..............................................\"}\n" +
                     "        .p80{text-align: left;padding-left: 98px;margin-top: 0px;margin-bottom: 0px;white-space: nowrap;}\n" +
                     "        .p80::after{display:inline-block;width:0;white-space:nowrap;content:\" ..............................................\"}\n" +
                     "        .p81{text-align: left;padding-left: 74px;margin-top: 0px;margin-bottom: 0px;white-space: nowrap;}\n" +
                     "        .p82{text-align: left;margin-top: 96px;margin-bottom: 0px;}\n" +
                     "        .p83{text-align: left;padding-left: 336px;margin-top: 651px;margin-bottom: 0px;}\n" +
                     " \n" +
                     " \n" +
                     "        .td0{padding: 0px;margin: 0px;width: 289px;vertical-align: bottom;}\n" +
                     "        .td1{padding: 0px;margin: 0px;width: 414px;vertical-align: bottom;}\n" +
                     "        .td2{padding: 0px;margin: 0px;width: 277px;vertical-align: bottom;}\n" +
                     "        .td3{padding: 0px;margin: 0px;width: 426px;vertical-align: bottom;}\n" +
                     "        .td4{padding: 0px;margin: 0px;width: 409px;vertical-align: bottom;}\n" +
                     "        .td5{padding: 0px;margin: 0px;width: 294px;vertical-align: bottom;}\n" +
                     "        .td6{border-left: #787878 1px solid;border-right: #787878 1px solid;border-top: #787878 1px solid;border-bottom: #f5f5f5 1px solid;padding: 0px;margin: 0px;width: 80px;vertical-align: bottom;}\n" +
                     "        .td7{border-right: #f5f5f5 1px solid;border-top: #787878 1px solid;border-bottom: #f5f5f5 1px solid;padding: 0px;margin: 0px;width: 83px;vertical-align: bottom;}\n" +
                     "        .td8{padding: 0px;margin: 0px;width: 253px;vertical-align: bottom;}\n" +
                     "        .td9{padding: 0px;margin: 0px;width: 275px;vertical-align: bottom;}\n" +
                     "        .td10{padding: 0px;margin: 0px;width: 97px;vertical-align: bottom;}\n" +
                     "        .td11{border-left: #000000 1px solid;border-right: #000000 1px solid;border-top: #000000 1px solid;border-bottom: #000000 1px solid;padding: 0px;margin: 0px;width: 342px;vertical-align: bottom;}\n" +
                     "        .td12{border-right: #292929 1px solid;border-top: #000000 1px solid;border-bottom: #000000 1px solid;padding: 0px;margin: 0px;width: 328px;vertical-align: bottom;}\n" +
                     "        .td13{border-left: #000000 1px solid;border-right: #000000 1px solid;border-bottom: #000000 1px solid;padding: 0px;margin: 0px;width: 342px;vertical-align: bottom;}\n" +
                     "        .td14{border-right: #292929 1px solid;border-bottom: #000000 1px solid;padding: 0px;margin: 0px;width: 328px;vertical-align: bottom;}\n" +
                     "        .td15{border-left: #000000 1px solid;border-right: #000000 1px solid;border-bottom: #292929 1px solid;padding: 0px;margin: 0px;width: 342px;vertical-align: bottom;}\n" +
                     "        .td16{border-right: #292929 1px solid;border-bottom: #292929 1px solid;padding: 0px;margin: 0px;width: 328px;vertical-align: bottom;}\n" +
                     "        .td17{padding: 0px;margin: 0px;width: 76px;vertical-align: bottom;background: #333333;}\n" +
                     "        .td18{padding: 0px;margin: 0px;width: 69px;vertical-align: bottom;background: #333333;}\n" +
                     "        .td19{padding: 0px;margin: 0px;width: 61px;vertical-align: bottom;background: #333333;}\n" +
                     "        .td20{padding: 0px;margin: 0px;width: 76px;vertical-align: bottom;background: #cccccc;}\n" +
                     "        .td21{padding: 0px;margin: 0px;width: 69px;vertical-align: bottom;background: #cccccc;}\n" +
                     "        .td22{padding: 0px;margin: 0px;width: 61px;vertical-align: bottom;background: #cccccc;}\n" +
                     "        .td23{padding: 0px;margin: 0px;width: 228px;vertical-align: bottom;}\n" +
                     "        .td24{padding: 0px;margin: 0px;width: 54px;vertical-align: bottom;}\n" +
                     "        .td25{padding: 0px;margin: 0px;width: 415px;vertical-align: bottom;}\n" +
                     "        .td26{padding: 0px;margin: 0px;width: 6px;vertical-align: bottom;}\n" +
                     "        .td27{padding: 0px;margin: 0px;width: 67px;vertical-align: bottom;}\n" +
                     "        .td28{padding: 0px;margin: 0px;width: 161px;vertical-align: bottom;}\n" +
                     "        .td29{padding: 0px;margin: 0px;width: 347px;vertical-align: bottom;}\n" +
                     "        .td30{padding: 0px;margin: 0px;width: 136px;vertical-align: bottom;}\n" +
                     "        .td31{padding: 0px;margin: 0px;width: 226px;vertical-align: bottom;}\n" +
                     "        .td32{padding: 0px;margin: 0px;width: 63px;vertical-align: bottom;}\n" +
                     "        .td33{padding: 0px;margin: 0px;width: 273px;vertical-align: bottom;}\n" +
                     "        .td34{padding: 0px;margin: 0px;width: 357px;vertical-align: bottom;}\n" +
                     "        .td35{padding: 0px;margin: 0px;width: 56px;vertical-align: bottom;}\n" +
                     "        .td36{padding: 0px;margin: 0px;width: 630px;vertical-align: bottom;}\n" +
                     " \n" +
                     " \n" +
                     "        .tr0{height: 18px;}\n" +
                     "        .tr1{height: 16px;}\n" +
                     "        .tr2{height: 17px;}\n" +
                     "        .tr3{height: 32px;}\n" +
                     "        .tr4{height: 19px;}\n" +
                     "        .tr5{height: 21px;}\n" +
                     "        .tr6{height: 23px;}\n" +
                     "        .tr7{height: 2px;}\n" +
                     "        .tr8{height: 20px;}\n" +
                     "        .tr9{height: 36px;}\n" +
                     "        .tr10{height: 22px;}\n" +
                     "        .tr11{height: 34px;}\n" +
                     "        .tr12{height: 56px;}\n" +
                     " \n" +
                     " \n" +
                     "        .t0{width: 703px;font: 12px 'Helvetica';}\n" +
                     "        .t1{width: 703px;font: 13px 'Helvetica';}\n" +
                     "        .t2{width: 165px;margin-top: 2px;font: bold 12px 'Helvetica';color: #cccccc;}\n" +
                     "        .t3{width: 528px;margin-left: 47px;font: 14px 'Helvetica';}\n" +
                     "        .t4{width: 350px;margin-left: 47px;font: 14px 'Helvetica';}\n" +
                     "        .t5{width: 672px;margin-left: 41px;font: 14px 'Helvetica';}\n" +
                     "        .t6{width: 206px;margin-left: 45px;margin-top: 16px;font: bold 14px 'Helvetica';color: #ffffff;}\n" +
                     "        .t7{width: 703px;margin-top: 17px;font: 14px 'Helvetica';}\n" +
                     "        .t8{width: 483px;margin-top: 55px;font: 14px 'Helvetica';}\n" +
                     "        .t9{width: 703px;margin-top: 36px;font: 14px 'Helvetica';}\n" +
                     "        .t10{width: 686px;font: 14px 'Helvetica';}\n" +
                     "    </STYLE>\n" +
                     "    </HEAD>\n" +
                     " \n" +
                     "    <BODY>\n" +
                     "        <DIV id=\"page_1\">\n" +
                     "        <DIV id=\"id1_1\">\n" +
                     "        <P style=\"text-decoration:underline;font-style:italic;color:#6a7178;font-size:10px;position:absolute;top:0;right:0\">Akad Murabahah Retail BSI - Tanpa Agunan</P>\n" +
                     "        <P style=\"text-align:center;font-size:16px;padding-top:45px\">\n" +
                     "            <b>AKAD PEMBIAYAAN BERDASARKAN PRINSIP MURABAHAH\n" +
                     "            <br>\n" +
                     "            No.\" + EncodeHtml(GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_APP_DATA_PEMBIAYAAN.Application_No + \"-\" + SeqAkadMRBH.Seq_Akad) + \"/MRBH</b>\n" +
                     "        </P>\n" +
                     "        <P style=\"text-align:justify\">Akad Pembiayaan Berdasarkan Prinsip Murabahah ini (selanjutnya disebut \"<b>Akad</b>\") dibuat dan ditandatangani pada hari ini, <b>\" + EncodeHtml(ConvertDateKeHuruf.Return.Hari) + \"</b> tanggal <b>\" + EncodeHtml(ConvertDateKeHuruf.Return.Tanggal) + \"</b>, bulan <b>\" + EncodeHtml(ConvertDateKeHuruf.Return.Bulan) + \"</b>, tahun <b>\" + EncodeHtml(ConvertDateKeHuruf.Return.Tahun) + \"</b>, oleh dan antara:</P>\n" +
                     " \n" +
                     "        <table border=\"0\" style=\"text-align:justify\">\n" +
                     "            <tbody>\n" +
                     "                <tr>\n" +
                     "                    <td style=\"vertical-align:top;width:2vw\">\n" +
                     "                        1.\n" +
                     "                    </td>\n" +
                     "                    <td>\n" +
                     "                        <b>PT BANK SYARIAH INDONESIA TBK</b>, berkedudukan di Jakarta Pusat, Jl Abdul Muis No 2 -4, dalam hal ini diwakili oleh <b>\" + EncodeHtml(GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_APP_AKAD_DAN_ASESOIR.Nama_Pejabat_Penandatangan) + \"</b> selaku <b>\" + EncodeHtml(GetRoleText.Return) + \"</b> \" + EncodeHtml(GetBranchByBranchCode.List.Current.branch.branch_name) + \" berdasarkan Surat Kuasa dari Nomor \" + EncodeHtml(GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_APP_AKAD_DAN_ASESOIR.Tanggal_Surat_Kuasa) + \" tanggal \" + EncodeHtml(FormatDateTime(GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_APP_AKAD_DAN_ASESOIR.Tanggal_Surat_Kuasa,\"dd-MM-yyyy\")) + \" dan Surat Keputusan/Surat Ketetapan Penempatan dan Penugasan (SKPP) Nomor \" + EncodeHtml(GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_APP_AKAD_DAN_ASESOIR.Nomor_SKPP_Pejabat) + \" tanggal \" + EncodeHtml(FormatDateTime(GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_APP_AKAD_DAN_ASESOIR.Tanggal_Surat_Keputusan,\"dd-MM-yyyy\")) + \" karenanya sah bertindak untuk dan atas nama PT Bank Syariah Indonesia Tbk, (untuk selanjutnya disebut \"<b>BANK</b>\")\n" +
                     "                    </td>\n" +
                     "                </tr>\n" +
                     "                <tr>\n" +
                     "                    <td style=\"vertical-align:top;\">\n" +
                     "                        2.\n" +
                     "                    </td>\n" +
                     "                    <td>\n" +
                     "                        <b>\" + EncodeHtml(GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_APP_DATA_PRIBADI.Nama_Lengkap) + \"</b> bertempat tinggal di \" +\n" +
                     "EncodeHtml(GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_APP_DATA_PRIBADI.Kabupaten + \", \" + GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_APP_DATA_PRIBADI.Alamat + \" Kelurahan \" + GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_APP_DATA_PRIBADI.Kelurahan + \" Kecamatan \" + GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_APP_DATA_PRIBADI.Kecamatan + \" Kota \" + GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_APP_DATA_PRIBADI.Kabupaten + \" sesuai dengan KTP No. \" + GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_APP_DATA_PRIBADI.No_KTP) + \" tanggal \" + EncodeHtml(FormatDateTime(GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_APP_DATA_PRIBADI.Tanggal_Penerbitan_KTP,\"dd-MM-yyyy\")) + \" berlaku sampai dengan \"Seumur Hidup\" dalam hal ini bertindak untuk dan atas nama sendiri. Sebagai NASABAH Penerima fasilitas (untuk selanjutnya disebut \"<b>NASABAH</b>\")\n" +
                     "                    </td>\n" +
                     "                </tr>\n" +
                     "            </tbody>\n" +
                     "        </table>\n" +
                     " \n" +
                     "        <P>BANK dan NASABAH selanjutnya secara <NOBR>bersama-sama</NOBR> disebut \"Para Pihak\".</P>\n" +
                     "       \n" +
                     "        Para Pihak terlebih dahulu menerangkan <NOBR>hal-hal</NOBR> sebagai berikut:\n" +
                     "            <table border=\"0\" style=\"text-align:justify\">\n" +
                     "                <tbody>\n" +
                     "                    <tr>\n" +
                     "                        <td style=\"vertical-align:top;width:2vw\">\n" +
                     "                            1.\n" +
                     "                        </td>\n" +
                     "                        <td>\n" +
                     "                            Bahwa NASABAH telah mengajukan permohonan fasilitas Pembiayaan kepada BANK untuk membeli Obyek Akad yang uraiannya akan disebutkan dalam Akad ini.\n" +
                     "                        </td>\n" +
                     "                    </tr>\n" +
                     "                    <tr>\n" +
                     "                        <td style=\"vertical-align:top;\">\n" +
                     "                            2.\n" +
                     "                        </td>\n" +
                     "                        <td>\n" +
                     "                            BANK dan NASABAH telah menandatangani dan menundukkan diri pada ketentuan-ketentuan Syarat-syarat Umum tanggal \" + EncodeHtml(TranslateDateToBahasa(CurrDate())) + \"  yang merupakan bagian yang tidak terpisahkan dari Akad ini.\n" +
                     "                        </td>\n" +
                     "                    </tr>\n" +
                     "                </tbody>\n" +
                     "            </table>\n" +
                     "        <P style=\"margin-top:0;text-align:justify\">Selanjutnya Para Pihak dalam kedudukannya tersebut diatas sepakat dan setuju untuk membuat Akad ini dengan syarat-syarat serta ketentuan-ketentuan sebagai berikut:</P>\n" +
                     " \n" +
                     "        <P style=\"text-align:center;font-weight:bold;font-size:16px;margin-bottom:0px;\">PASAL 1\n" +
                     "        <br>\n" +
                     "        DEFINISI DAN INTERPRETASI\n" +
                     "        </P>\n" +
                     "       \n" +
                     "        <P style=\"margin-top-:0px;text-align:justify\">\n" +
                     "        Jika tidak secara tegas dinyatakan lain dalam Akad ini, maka <NOBR>kata-kata</NOBR> yang dimulai dengan huruf besar atau <NOBR>definisi-definisi</NOBR> dan <NOBR>istilah-istilah</NOBR> yang dipergunakan dalam Akad ini, mengacu kepada <NOBR>Syarat-syarat</NOBR> Umum.\n" +
                     "        </P>\n" +
                     "       \n" +
                     "        <P style=\"text-align:center;font-weight:bold;font-size:16px;margin-bottom:0px;\">PASAL 2\n" +
                     "        <br>\n" +
                     "        PELAKSANAAN PEMBIAYAAN MURABAHAH\n" +
                     "        </P>\n" +
                     " \n" +
                     "        <table border=\"0\" style=\"text-align:justify\">\n" +
                     "            <tbody>\n" +
                     "                <tr>\n" +
                     "                    <td style=\"vertical-align:top;width:2vw\">\n" +
                     "                        a.\n" +
                     "                    </td>\n" +
                     "                    <td>\n" +
                     "                        BANK berdasarkan Akad Wakalah memberikan kuasa secara penuh kepada NASABAH untuk mencari, membeli dan menerima Obyek Akad dari Pemasok.\n" +
                     "                    </td>\n" +
                     "                </tr>\n" +
                     "                <tr>\n" +
                     "                    <td style=\"vertical-align:top;\">\n" +
                     "                        b.\n" +
                     "                    </td>\n" +
                     "                    <td>\n" +
                     "                        NASABAH atas beban dan tanggung jawabnya, berdasarkan Akad Wakalah, berkewajiban melakukan pemeriksaan, baik terhadap kondisi Pemasok, keadaan fisik Obyek Akad maupun sahnya surat- surat dan atau yang berkaitan dengan kepemilikan atau lainnya atas Obyek Akad.\n" +
                     "                    </td>\n" +
                     "                </tr>\n" +
                     "                <tr>\n" +
                     "                    <td style=\"vertical-align:top;\">\n" +
                     "                        c.\n" +
                     "                    </td>\n" +
                     "                    <td>\n" +
                     "                        Setelah Pemasok diperoleh, BANK atau NASABAH menerbitkan purchase order pembelian Obyek Akad atau dokumen sejenis lainnya.\n" +
                     "                    </td>\n" +
                     "                </tr>\n" +
                     "                <tr>\n" +
                     "                    <td style=\"vertical-align:top;\">\n" +
                     "                        d.\n" +
                     "                    </td>\n" +
                     "                    <td>\n" +
                     "                        Pemasok mengeluarkan dokumen yang merinci Harga Beli serta spesifikasi Obyek Akad yang akan dibeli. BANK akan membayar Harga Beli berdasarkan dokumen yang dikeluarkan oleh Pemasok.\n" +
                     "                    </td>\n" +
                     "                </tr>\n" +
                     "                <tr>\n" +
                     "                    <td style=\"vertical-align:top;\">\n" +
                     "                        e.\n" +
                     "                    </td>\n" +
                     "                    <td>\n" +
                     "                        Segera setelah jual beli Obyek Akad antara BANK (melalui NASABAH selaku wakil BANK) dengan Pemasok terlaksana, NASABAH membeli Obyek Akad dari BANK dengan Harga Jual.\n" +
                     "                    </td>\n" +
                     "                </tr>\n" +
                     "            </tbody>\n" +
                     "        </table>\n" +
                     "        </DIV>\n" +
                     " \n" +
                     "        <DIV id=\"id1_2\" style=\"position:fixed;bottom:0\">\n" +
                     "        <DIV id=\"id1_2_1\">\n" +
                     "        <P class=\"p25 ft14\" style=\"margin-left:-1px\">Paraf</P>\n" +
                     "        <TABLE cellspacing=\"0\">\n" +
                     "        <TR>\n" +
                     "            <TD class=\"ft14\" style=\"border-style:solid;border-width:1px 0px 1px 1px;border-color:#adb5bd;width:80px;height:50px\">\n" +
                     "                <P style=\"text-align:center;margin-bottom:-20px\">Bank</P></TD>\n" +
                     "            <TD class=\"ft14\" style=\"border: 1px solid #adb5bd;width:80px;height:50px\">\n" +
                     "                <P style=\"text-align:center;margin-bottom:-20px\">Nasabah</P></TD>\n" +
                     "        </TR>\n" +
                     "        </TABLE>\n" +
                     "        </DIV>\n" +
                     "        <DIV style=\"margin-top:45px;text-align:right;padding-right:25px\">\n" +
                     "        <P style=\"margin-top:0px;margin-bottom:0px\">Page 1 of 3</P>\n" +
                     "        </DIV>\n" +
                     "        </DIV>\n" +
                     "        </DIV>\n" +
                     " \n" +
                     "        <DIV style=\"page-break-before: always;display: block;\"></DIV>\n" +
                     " \n" +
                     "        <DIV id=\"page_2\">\n" +
                     "        <DIV id=\"id2_1\">\n" +
                     "        <P style=\"text-decoration:underline;font-style:italic;color:#6a7178;font-size:10px;position:absolute;top:0;right:0\">Akad Murabahah Retail BSI - Tanpa Agunan</P>\n" +
                     "       \n" +
                     "        <table border=\"0\" style=\"text-align:justify;padding-top:45px\">\n" +
                     "            <tbody>\n" +
                     "                <tr>\n" +
                     "                    <td style=\"vertical-align:top;width:2vw\">\n" +
                     "                        f.\n" +
                     "                    </td>\n" +
                     "                    <td>\n" +
                     "                        NASABAH bersedia membayar Harga Jual kepada BANK sesuai Akad, dan Harga Jual tersebut tidak dapat berubah selama berlakunya Akad.\n" +
                     "                    </td>\n" +
                     "                </tr>\n" +
                     "            </tbody>\n" +
                     "        </table>\n" +
                     "       \n" +
                     "        <P style=\"text-align:center;font-weight:bold;font-size:16px;margin-bottom:0px;\">PASAL 3\n" +
                     "        <br>\n" +
                     "        SYARAT REALISASI PEMBIAYAAN\n" +
                     "        </P>\n" +
                     " \n" +
                     "        <table border=\"0\" style=\"text-align:justify\">\n" +
                     "            <tbody>\n" +
                     "                <tr>\n" +
                     "                    <td style=\"vertical-align:top;width:2vw\">\n" +
                     "                        1.\n" +
                     "                    </td>\n" +
                     "                    <td>\n" +
                     "                        Pemberian Pembiayaan sebagaimana disebutkan dalam Akad ini hanya akan diberikan oleh BANK jika NASABAH telah memenuhi persyaratan dan menyerahkan seluruh dokumen yang dipersyaratkan dalam Akad ini, </SPAN><NOBR>Syarat-syarat</NOBR> Umum dan SP3 (jika ada) serta <NOBR>lampiran-lampirannya</NOBR> dan dokumen lainnya sebagaimana disebutkan dalam Akad ini.\n" +
                     "                    </td>\n" +
                     "                </tr>\n" +
                     "                <tr>\n" +
                     "                    <td style=\"vertical-align:top;\">\n" +
                     "                        2.\n" +
                     "                    </td>\n" +
                     "                    <td>\n" +
                     "                        Selain syarat sebagaimana dinyatakan dalam butir 1) di atas, untuk Penarikan Pembiayaan, Nasabah wajib memenuhi persyaratan sebagai berikut:\n" +
                     "                    </td>\n" +
                     "                </tr>\n" +
                     "            </tbody>\n" +
                     "        </table>\n" +
                     "       \n" +
                     "        <P style=\"text-align:center;font-weight:bold;font-size:16px;margin-bottom:0px;\">PASAL 4\n" +
                     "        <br>\n" +
                     "        POKOK AKAD, BIAYA, OBYEK AKAD, DAN JANGKA WAKTU PEMBIAYAAN\n" +
                     "        </P>\n" +
                     " \n" +
                     "        <table border=\"0\" style=\"text-align:justify\">\n" +
                     "            <tbody>\n" +
                     "                <tr>\n" +
                     "                    <td style=\"vertical-align:top;width:2vw\">\n" +
                     "                        1.\n" +
                     "                    </td>\n" +
                     "                    <td>\n" +
                     "                        BANK dengan ini memberikan fasilitas Pembiayaan kepada NASABAH berdasarkan Prinsip Murabahah yang akan digunakan untuk membeli Obyek Akad berupa <b>\" + EncodeHtml(GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_APP_DATA_PEMBIAYAAN.Tipe_Produk) + \"</b>, dan NASABAH dengan ini menerima penyediaan fasilitas Pembiayaan tersebut dari BANK dengan rincian sebagai berikut:\n" +
                     "                   \n" +
                     "                    <table>\n" +
                     "                        <tr>\n" +
                     "                            <td style=\"vertical-align:top;width:3vw\">\n" +
                     "                                -\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:30vw\">\n" +
                     "                                Harga Perolehan (a):\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:2vw\">\n" +
                     "                                :\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:60vw\">\n" +
                     "                                \" + FormatCurrency(GetAkadMRBHIJRHMMQ.DataAkad.MRBHIJRHAset.Harga_Barang,\"Rp \",2,\".\",\",\") + \" [harga pokok barang]\n" +
                     "                            </td>\n" +
                     "                        </tr>\n" +
                     "                        <tr>\n" +
                     "                            <td style=\"vertical-align:top;width:3vw\">\n" +
                     "                                -\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:30vw\">\n" +
                     "                                Margin (b)\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:2vw\">\n" +
                     "                                :\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:60vw\">\n" +
                     "                                \" + FormatCurrency(SumD6Margin.List.Current.AngsuranMarginSum,\"Rp \",2,\".\",\",\") + \"\n" +
                     "                            </td>\n" +
                     "                        </tr>\n" +
                     "                        <tr>\n" +
                     "                            <td style=\"vertical-align:top;width:3vw\">\n" +
                     "                                -\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:30vw\">\n" +
                     "                                Harga Jual (a+b)\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:2vw\">\n" +
                     "                                :\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:60vw\">\n" +
                     "                                \" + FormatCurrency(GetAkadMRBHIJRHMMQ.DataAkad.MRBHIJRHAset.Harga_Barang + SumD6Margin.List.Current.AngsuranMarginSum,\"Rp \",2,\".\",\",\") + \"\n" +
                     "                            </td>\n" +
                     "                        </tr>\n" +
                     "                        <tr>\n" +
                     "                            <td style=\"vertical-align:top;width:3vw\">\n" +
                     "                                -\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:30vw\">\n" +
                     "                                Uang Muka (a-p)\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:2vw\">\n" +
                     "                                :\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:60vw\">\n" +
                     "                                \" + FormatCurrency(GetAkadMRBHIJRHMMQ.DataAkad.MRBHIJRHAset.Harga_Barang - GetNOSAPPDATAKALKULATORD6Details.List.Current.NOS_APP_DATA_KALKULATOR_D6.Plafond,\"Rp \",2,\".\",\",\") + \"\n" +
                     "                            </td>\n" +
                     "                        </tr>\n" +
                     "                        <tr>\n" +
                     "                            <td style=\"vertical-align:top;width:3vw\">\n" +
                     "                                -\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:30vw\">\n" +
                     "                                Pembiayaan Bank (p)\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:2vw\">\n" +
                     "                                :\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:60vw\">\n" +
                     "                                \" + FormatCurrency(GetNOSAPPDATAKALKULATORD6Details.List.Current.NOS_APP_DATA_KALKULATOR_D6.Plafond,\"Rp \",2,\".\",\",\") + \" [limit pembiayaan]\n" +
                     "                            </td>\n" +
                     "                        </tr>\n" +
                     "                        <tr>\n" +
                     "                            <td style=\"vertical-align:top;width:3vw\">\n" +
                     "                                -\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:30vw\">\n" +
                     "                                Jumlah Kewajiban (p+b)\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:2vw\">\n" +
                     "                                :\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:60vw\">\n" +
                     "                                \" + FormatCurrency(GetNOSAPPDATAKALKULATORD6Details.List.Current.NOS_APP_DATA_KALKULATOR_D6.Plafond +\n" +
                     "SumD6Margin.List.Current.AngsuranMarginSum,\"Rp \",2,\".\",\",\") + \" [total kewajiban/hutang murabahah]\n" +
                     "                            </td>\n" +
                     "                        </tr>\n" +
                     "                        <tr>\n" +
                     "                            <td style=\"vertical-align:top;width:3vw\">\n" +
                     "                                -\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:30vw\">\n" +
                     "                                Besarnya Angsuran\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:2vw\">\n" +
                     "                                :\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:60vw\">\n" +
                     "                                \" + FormatCurrency(GetNOSAPPDATAKALKULATORD6Details.List.Current.NOS_APP_DATA_KALKULATOR_D6.AngsuranBulananPrapen,\"Rp \",2,\".\",\",\") + \"\n" +
                     "                            </td>\n" +
                     "                        </tr>\n" +
                     "                        <tr>\n" +
                     "                            <td style=\"vertical-align:top;width:3vw\">\n" +
                     "                               \n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:30vw\">\n" +
                     "                               \n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:2vw\">\n" +
                     "                               \n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:60vw\">\n" +
                     "                                \" + FormatCurrency(GetNOSAPPDATAKALKULATORD6Details.List.Current.NOS_APP_DATA_KALKULATOR_D6.AngsuranBulananPensiun,\"Rp \",2,\".\",\",\") + \"\n" +
                     "                            </td>\n" +
                     "                        </tr>\n" +
                     "                    </table>\n" +
                     " \n" +
                     "                    </td>\n" +
                     "                </tr>\n" +
                     "                <tr>\n" +
                     "                    <td style=\"vertical-align:top;\">\n" +
                     "                        2.\n" +
                     "                    </td>\n" +
                     "                    <td>\n" +
                     "                        BANK dengan ini menjual Obyek Akad kepada NASABAH, dan NASABAH membeli Obyek Akad dimaksud dari BANK dengan Margin yang disepakati Para Pihak.\n" +
                     "                    </td>\n" +
                     "                </tr>\n" +
                     "                <tr>\n" +
                     "                    <td style=\"vertical-align:top;\">\n" +
                     "                        3.\n" +
                     "                    </td>\n" +
                     "                    <td>\n" +
                     "                        Para Pihak sepakat bahwa penyerahan Obyek Akad akan dilakukan langsung oleh Pemasok kepada NASABAH.\n" +
                     "                    </td>\n" +
                     "                </tr>\n" +
                     "                <tr>\n" +
                     "                    <td style=\"vertical-align:top;\">\n" +
                     "                        4.\n" +
                     "                    </td>\n" +
                     "                    <td>\n" +
                     "                        NASABAH setuju untuk membayar Biaya yang terkait dengan pemberian fasilitas Pembiayaan ini, yaitu:\n" +
                     " \n" +
                     "                        <table>\n" +
                     "                        <tr>\n" +
                     "                            <td style=\"vertical-align:top;width:3vw\">\n" +
                     "                                -\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:30vw\">\n" +
                     "                                Biaya Administrasi\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:2vw\">\n" +
                     "                               \n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:60vw\">\n" +
                     "                                \" + FormatCurrency(GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_APP_DATA_KALKULATOR_D6.BiayaAdministrasi,\"Rp \",2,\".\",\",\") + \"\n" +
                     "                            </td>\n" +
                     "                        </tr>\n" +
                     "                        <tr>\n" +
                     "                            <td style=\"vertical-align:top;width:3vw\">\n" +
                     "                                -\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:30vw\">\n" +
                     "                                Biaya Asuransi Jiwa/Penjamin\n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:2vw\">\n" +
                     "                               \n" +
                     "                            </td>\n" +
                     "                            <td style=\"width:60vw\">\n" +
                     "                                Sesuai Tagihan\n" +
                     "                            </td>\n" +
                     "                        </tr>\n" +
                     "                    </table>\n" +
                     " \n" +
                     "                    </td>\n" +
                     "                </tr>\n" +
                     "                <tr>\n" +
                     "                    <td style=\"vertical-align:top;\">\n" +
                     "                        5.\n" +
                     "                    </td>\n" +
                     "                    <td>\n" +
                     "                        Nasabah melakukan pembayaran Angsuran pada setiap tanggal \" + EncodeHtml(FormatText(GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_MST_PROGRAM.Tanggal,2,2,True,\"0\")) + \" dalam jangka waktu \" + EncodeHtml(GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_APP_DATA_KALKULATOR_D6.JangkaWaktu) + \" (\" + EncodeHtml(ConvertIntegerKeHuruf(GetNosAppDataPembiayaanByApplicationId.List.Current.NOS_APP_DATA_KALKULATOR_D6.JangkaWaktu).Text) + \") terhitung dari tanggal pencairan Pembiayaan, sampai dengan seluruh Jumlah Kewajiban lunas, sesuai dengan jadwal Angsuran yang menjadi Lampiran Akad ini.\n" +
                     "                    </td>\n" +
                     "                </tr>\n" +
                     "                <tr>\n" +
                     "                    <td style=\"vertical-align:top;\">\n" +
                     "                        6.\n" +
                     "                    </td>\n" +
                     "                    <td>\n" +
                     "                        Selama Jumlah Kewajiban belum dilunasi oleh NASABAH, NASABAH dengan ini mengaku berhutang kepada BANK sebesar Jumlah Kewajiban yang wajib dibayar oleh NASABAH kepada BANK berdasarkan Akad ini.\n" +
                     "                    </td>\n" +
                     "                </tr>\n" +
                     "                <tr>\n" +
                     "                    <td style=\"vertical-align:top;\">\n" +
                     "                        7.\n" +
                     "                    </td>\n" +
                     "                    <td>\n" +
                     "                        Setiap pembayaran oleh NASABAH kepada BANK lebih dahulu digunakan untuk melunasi Biaya dan sisanya baru dihitung sebagai pembayaran Angsuran atas Jumlah Kewajiban.\n" +
                     "                    </td>\n" +
                     "                </tr>\n" +
                     "            </tbody>\n" +
                     "        </table>\n" +
                     "        </DIV>\n" +
                     "        </DIV>\n" +
                     " \n" +
                     "        <DIV style=\"page-break-before: always;display: block;\"></DIV>\n" +
                     " \n" +
                     "        <DIV id=\"page_3\">\n" +
                     "        <DIV id=\"id3_1\">\n" +
                     "       \n" +
                     "        <P style=\"text-decoration:underline;font-style:italic;color:#6a7178;font-size:10px;position:absolute;top:0;right:0\">Akad Murabahah Retail BSI - Tanpa Agunan</P>\n" +
                     " \n" +
                     "        <P style=\"text-align:center;font-weight:bold;font-size:16px;margin-bottom:0px;\">PASAL 5\n" +
                     "        <br>\n" +
                     "        JAMINAN\n" +
                     "        </P>\n" +
                     "       \n" +
                     "        <P style=\"margin-top-:0px;text-align:justify\">\n" +
                     "        Jika tidak secara tegas dinyatakan lain dalam Akad ini, maka <NOBR>kata-kata</NOBR> yang dimulai dengan huruf besar atau <NOBR>definisi-definisi</NOBR> dan <NOBR>istilah-istilah</NOBR> yang dipergunakan dalam Akad ini, mengacu kepada <NOBR>Syarat-syarat</NOBR> Umum.\n" +
                     "        </P>\n" +
                     " \n" +
                     " \n" +
                     " \n" +
                     " \n" +
                     " \n" +
                     " \n" +
                     " \n" +
                     " \n" +
                     " \n" +
                     " \n" +
                     " \n" +
                     " \n" +
                     " \n" +
                     "        <P class=\"p41 ft1\">PASAL 5</P>\n" +
                     "        <P class=\"p42 ft1\">KUASA</P>\n" +
                     "        <P class=\"p43 ft6\">NASABAH bersama ini memberi kuasa penuh kepada BANK khusus untuk memblokir, mencairkan dan atau mendebet rekening NASABAH pada BANK No. dan rekening lainnya, untuk melunasi hutang/kewajiban NASABAH kepada BANK. NASABAH menerima dan menyetujui segala tindakan BANK atas rekening NASABAH tersebut di atas. Kuasa ini akan terus berlaku dan tidak akan dicabut oleh NASABAH hingga Jumlah Kewajiban NASABAH lunas.</P>\n" +
                     "        <P class=\"p19 ft1\">PASAL 6</P>\n" +
                     "        <P class=\"p44 ft1\">PEMBERITAHUAN</P>\n" +
                     "        <P class=\"p21 ft3\"><SPAN class=\"ft3\">1.</SPAN><SPAN class=\"ft7\">Alamat Pemberitahuan</SPAN></P>\n" +
                     "        <P class=\"p45 ft3\">Semua surat menyurat atau pemberitahuan yang dikirim oleh <NOBR>masing-masing</NOBR> pihak kepada pihak yang lain harus dilakukan dengan surat tercatat, melalui kurir (ekspedisi), atau faksimili ke <NOBR>alamat-alamat</NOBR> sebagai berikut:</P>\n" +
                     "        <TABLE cellpadding=0 cellspacing=0 class=\"t5\">\n" +
                     "        <TR>\n" +
                     "            <TD class=\"tr5 td11\"><P class=\"p46 ft1\">Untuk BANK:</P></TD>\n" +
                     "            <TD class=\"tr5 td12\"><P class=\"p47 ft1\">Untuk NASABAH:</P></TD>\n" +
                     "        </TR>\n" +
                     "        <TR>\n" +
                     "            <TD class=\"tr0 td13\"><P class=\"p46 ft3\">Cabang: KCP Terban</P></TD>\n" +
                     "            <TD class=\"tr0 td14\"><P class=\"p47 ft3\">Alamat:</P></TD>\n" +
                     "        </TR>\n" +
                     "        <TR>\n" +
                     "            <TD class=\"tr2 td13\"><P class=\"p46 ft3\">Alamat: Jl. Bulaksumur Blok H No. 4 Kab. Sleman</P></TD>\n" +
                     "            <TD class=\"tr2 td14\"><P class=\"p47 ft3\">Telepon:</P></TD>\n" +
                     "        </TR>\n" +
                     "        <TR>\n" +
                     "            <TD class=\"tr0 td13\"><P class=\"p46 ft3\">Telepon: <NOBR>0274-557087</NOBR></P></TD>\n" +
                     "            <TD class=\"tr0 td14\"><P class=\"p47 ft3\">Faksimili:</P></TD>\n" +
                     "        </TR>\n" +
                     "        <TR>\n" +
                     "            <TD class=\"tr4 td15\"><P class=\"p46 ft3\">Faksimili:</P></TD>\n" +
                     "            <TD class=\"tr4 td16\"><P class=\"p4 ft9\">&nbsp;</P></TD>\n" +
                     "        </TR>\n" +
                     "        </TABLE>\n" +
                     "        <P class=\"p38 ft3\"><SPAN class=\"ft3\">2.</SPAN><SPAN class=\"ft7\">Pemberitahuan dari salah satu pihak kepada pihak lainnya dianggap diterima:</SPAN></P>\n" +
                     "        <P class=\"p48 ft3\"><SPAN class=\"ft8\">a.</SPAN><SPAN class=\"ft20\">Jika dikirim melalui kurir (ekspedisi) pada tanggal penerimaan;</SPAN></P>\n" +
                     "        <P class=\"p48 ft3\"><SPAN class=\"ft8\">b.</SPAN><SPAN class=\"ft20\">Jika dikirim melalui pos tercatat ,7 (tujuh) hari setelah tanggal pengirimannya, dan/atau;</SPAN></P>\n" +
                     "        <P class=\"p49 ft3\"><SPAN class=\"ft3\">c.</SPAN><SPAN class=\"ft21\">Jika dikirim melalui faksimili, pada hari pengirimannya.</SPAN></P>\n" +
                     "        <P class=\"p14 ft3\"><SPAN class=\"ft3\">3.</SPAN><SPAN class=\"ft7\">Salah satu pihak dapat mengganti alamatnya dengan memberitahukan secara tertulis kepada pihak lainnya.</SPAN></P>\n" +
                     "        <P class=\"p16 ft1\">PASAL 7</P>\n" +
                     "        <P class=\"p50 ft1\">PENUTUP</P>\n" +
                     "        <P class=\"p10 ft6\"><SPAN class=\"ft3\">1.</SPAN><SPAN class=\"ft10\">Apabila ada </SPAN><NOBR>hal-hal</NOBR> yang belum diatur atau belum cukup diatur dalam Akad, Para Pihak akan mengaturnya bersama secara musyawarah untuk mufakat untuk suatu addendum atau dokumen tertulis lainnya yang merupakan satu kesatuan yang tidak terpisahkan dalam Akad.</P>\n" +
                     "        <P class=\"p51 ft6\"><SPAN class=\"ft3\">2.</SPAN><SPAN class=\"ft10\">Sebelum Akad ini ditandatangani oleh NASABAH, NASABAH mengakui dengan sebenarnya bahwa NASABAH telah membaca dengan cermat atau dibacakan kepadanya seluruh isi Akad ini berikut Syarat- syarat Umum serta semua surat dan/atau dokumen yang menjadi lampiran Akad ini, sehingga NASABAH memahami sepenuhnya segala yang akan menjadi akibat hukum setelah NASABAH menandatangani Akad ini.</SPAN></P>\n" +
                     "        <P class=\"p52 ft3\"><SPAN class=\"ft3\">3.</SPAN><SPAN class=\"ft7\">Jika salah satu atau sebagian </SPAN><NOBR>ketentuan-ketentuan</NOBR> dalam Akad ini menjadi batal atau tidak berlaku, maka tidak mengakibatkan seluruh Akad ini menjadi batal atau tidak berlaku seluruhnya.</P>\n" +
                     "        <P class=\"p24 ft3\"><SPAN class=\"ft3\">4.</SPAN><SPAN class=\"ft7\">Akad ini dibuat dan ditandatangani oleh Para Pihak dalam rangkap 2 (dua) yang </SPAN><NOBR>masing-masing</NOBR> berlaku sebagai asli.</P>\n" +
                     "        <P class=\"p53 ft1\">PT BANK SYARIAH INDONESIA TBK</P>\n" +
                     "        </DIV>\n" +
                     "        <DIV id=\"id3_2\">\n" +
                     "        <DIV id=\"id3_2_1\">\n" +
                     "        <P class=\"p21 ft1\">KCP TERBAN</P>\n" +
                     "        </DIV>\n" +
                     "        <DIV id=\"id3_2_2\">\n" +
                     "        <P class=\"p21 ft1\">NASABAH</P>\n" +
                     "        <P class=\"p54 ft22\">Materai Rp. 10.000,-</P>\n" +
                     "        <P class=\"p55 ft3\">Tes</P>\n" +
                     "        </DIV>\n" +
                     "        </DIV>\n" +
                     "        <DIV id=\"id3_3\">\n" +
                     "        <DIV id=\"id3_3_1\">\n" +
                     "        <P class=\"p25 ft14\">Paraf</P>\n" +
                     "        <TABLE cellpadding=0 cellspacing=0 class=\"t2\">\n" +
                     "        <TR>\n" +
                     "            <TD class=\"tr3 td6\"><P class=\"p26 ft15\">Bank</P></TD>\n" +
                     "            <TD class=\"tr3 td7\"><P class=\"p27 ft15\">Nasabah</P></TD>\n" +
                     "        </TR>\n" +
                     "        </TABLE>\n" +
                     "        </DIV>\n" +
                     "        <DIV id=\"id3_3_2\">\n" +
                     "        <P class=\"p21 ft16\">Page 3 of 3</P>\n" +
                     "        </DIV>\n" +
                     "        </DIV>\n" +
                     "        </DIV>\n" +
                     "</BODY>";

                PdfConverter converter = PdfConverter.getInstance();
                File file = new File(Environment.getExternalStorageDirectory().toString(), "fileConvert.pdf");
                converter.convert(LoginActivity2.this, htmlString, file);



                break;
            }
        }


    private void doLogin() {
        loading.setVisibility(View.VISIBLE);
        MagicCryptHelper encryptor=new MagicCryptHelper();
        login req = new login(et_username.getText().toString().trim(), encryptor.encrypt("12345678"), getDeviceId(), "NOS_KONSUMER");
//        req.setPassword(encryptor.encrypt(et_password.getText().toString()));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().login2(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if(response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<DataLoginBsi>() {}.getType();
                            dataUserBsi = gson.fromJson(listDataString, type);
                            setPrefLoginBsi(dataUserBsi);
                            RouteApp router = new RouteApp(LoginActivity2.this);
                            router.openActivityAndClearAllPrevious(CoreLayoutActivity.class);
                        }
                        else if (response.body().getStatus().equalsIgnoreCase("21")){
                            CustomDialog.DialogError(LoginActivity2.this, "Upss Sorry", "Akun anda telah digunakan di perangkat lain, Silahkan aktivasi ulang jika ingin menggunakan di perangkat ini.", LoginActivity2.this);
                        }
                        else {
                            AppUtil.notiferror(LoginActivity2.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(LoginActivity2.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(LoginActivity2.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                t.printStackTrace();
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

    private void setPrefLoginBsi(DataLoginBsi dataLoginBsi){
        appPreferences.setNama(AppUtil.encrypt(dataLoginBsi.getName()));
        appPreferences.setJabatan(AppUtil.encrypt(dataLoginBsi.getRole().getRoleName()));
        appPreferences.setNamaKantor(AppUtil.encrypt(dataLoginBsi.getBranch().getBranch_name()));
        appPreferences.setKodeKantor(AppUtil.encrypt(String.valueOf(dataLoginBsi.getBranch().getBranch_code())));
        appPreferences.setKodeKanwil((AppUtil.encrypt(String.valueOf(dataLoginBsi.getBranch().getId()))));
        appPreferences.setFidRole(AppUtil.encrypt(String.valueOf(dataLoginBsi.getRole().getId())));
        appPreferences.setUid(Integer.toString(dataLoginBsi.getId()));
        appPreferences.setKodeAo(AppUtil.encrypt(dataLoginBsi.getOfficer_code()));
        appPreferences.setKodeCabang(AppUtil.encrypt(String.valueOf(dataLoginBsi.getBranch().getId())));
        appPreferences.setToken(AppUtil.encrypt(dataLoginBsi.getToken()));
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
            RouteApp router = new RouteApp(LoginActivity2.this);
            router.openActivityAndClearAllPrevious(LoginActivity2.class);
        }
    }
}
