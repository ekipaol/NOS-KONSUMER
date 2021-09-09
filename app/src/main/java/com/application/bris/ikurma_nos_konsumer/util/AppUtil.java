package com.application.bris.ikurma_nos_konsumer.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.FaceDetector;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.database.pojo.PesanDashboardPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.util.magiccrypt.MagicCrypt;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by Idong
 */

public class AppUtil {
    public static MagicCrypt magicCrypt = new MagicCrypt();
    private Snackbar snackbar;

    public static void showToastShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void toolbarRegular(final Context context, String title) {

        Toolbar toolbar = (Toolbar) ((AppCompatActivity) context).findViewById(R.id.tb_regular);
        ImageView btnBack = (ImageView) ((AppCompatActivity) context).findViewById(R.id.btn_back);
        TextView tvPageTitle = (TextView) ((AppCompatActivity) context).findViewById(R.id.tv_page_title);
        tvPageTitle.setText(title);
        ((AppCompatActivity) context).setSupportActionBar(toolbar);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) context).onBackPressed();
            }
        });
    }


    //method super duper canggih, the power of rekursiv oh yes, bisa ngambil semua children dan mengeditnya tanpa perlu di deklarasi satu satu

    //Method ini akan disable seluruh edittect dan textfieldboxes agar tidak bisa diklik ya

    public static void disableEditTexts(View viewInduk){

        if(viewInduk instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) viewInduk;
            for (int i = 0, count = vg.getChildCount(); i < count; ++i) {
                View view = vg.getChildAt(i);
                disableEditTexts(view);
                if (view instanceof ExtendedEditText) {
                    ((ExtendedEditText) view).setFocusable(false);
                    ((ExtendedEditText) view).setOnClickListener(null);

                }
                else if (view instanceof TextFieldBoxes) {
//                    ((TextFieldBoxes) view).setEnabled(false);
//                    ((TextFieldBoxes) view).setClickable(false);
                    ((TextFieldBoxes) view).setOnClickListener(null);
//                    ((TextFieldBoxes) view).getEndIconImageButton(false);
                    ((TextFieldBoxes) view).removeEndIcon();


                }


            }
        }

    }

    public static String formatStringDoubleBelakangKoma(String string, int berapaAngkaBelakangKoma) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(berapaAngkaBelakangKoma);

        Double pencairan=Double.parseDouble(string);

        return df.format(pencairan);
    }


    public static String getInitials(String label) {
        String initials="";
        String[] parts = label.split(" ");
        char initial;
        for (int i=0; i<parts.length; i++){
            initial=parts[i].charAt(0);
            initials+=initial;
        }
        return(initials.toUpperCase());
    }

    public static HashMap<String, String> getDeviceInfo(Context context) {
        HashMap<String, String> deviceInfo = new HashMap<>();
        String deviceId = "";
        String deviceName = "";
        String imei = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
            }
            deviceName = Build.MANUFACTURER
                    + " " + Build.MODEL + " Android " + Build.VERSION.RELEASE
                    + " " + Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName();
            imei = telephonyManager.getDeviceId().toString();
        } catch (Exception e) {}

        deviceInfo.put(Constants.DEVICE_ID, deviceId);
        deviceInfo.put(Constants.DEVICE_NAME, deviceName);
        deviceInfo.put(Constants.IMEI, imei);

        return deviceInfo;
    }


    public static String parseRupiah(String amount){
        Double amountDouble = Double.valueOf(amount);
        DecimalFormat kursIDN = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRP = new DecimalFormatSymbols();
        formatRP.setCurrencySymbol("Rp. ");
        formatRP.setMonetaryDecimalSeparator(',');
        formatRP.setGroupingSeparator('.');
        kursIDN.setDecimalFormatSymbols(formatRP);
        return kursIDN.format(amountDouble);
    }

    public static String parseRupiahNoSymbol(String amount){
        Double amountDouble = Double.valueOf(amount);
        DecimalFormat kursIDN = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRP = new DecimalFormatSymbols();
        formatRP.setCurrencySymbol("");
        formatRP.setMonetaryDecimalSeparator(',');
        formatRP.setGroupingSeparator('.');
        kursIDN.setDecimalFormatSymbols(formatRP);
        return kursIDN.format(amountDouble);
    }

//    public static String parseRupiahLongNoSymbol(Long amount){
//        Double amountDouble = new Double(amount);
//        DecimalFormat kursIDN = (DecimalFormat) DecimalFormat.getCurrencyInstance();
//        DecimalFormatSymbols formatRP = new DecimalFormatSymbols();
//        formatRP.setCurrencySymbol("");
//        formatRP.setMonetaryDecimalSeparator(',');
//        formatRP.setGroupingSeparator('.');
//        kursIDN.setDecimalFormatSymbols(formatRP);
//        return kursIDN.format(amountDouble);
//    }

    public static String parseValas(String amount){
        Double amountDouble = Double.valueOf(amount);
        DecimalFormat kursIDN = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRP = new DecimalFormatSymbols();
        formatRP.setCurrencySymbol("$ ");
        formatRP.setMonetaryDecimalSeparator(',');
        formatRP.setGroupingSeparator('.');
        kursIDN.setDecimalFormatSymbols(formatRP);
        return kursIDN.format(amountDouble);
    }

    public static String parseRupiahInt(Integer amount){
        Double amountDouble = new Double(amount);
        DecimalFormat kursIDN = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRP = new DecimalFormatSymbols();
        formatRP.setCurrencySymbol("Rp. ");
        formatRP.setMonetaryDecimalSeparator(',');
        formatRP.setGroupingSeparator('.');
        kursIDN.setDecimalFormatSymbols(formatRP);
        return kursIDN.format(amountDouble);
    }

    public static String parseRupiahLong(Long amount){
        Double amountDouble = new Double(amount);
        DecimalFormat kursIDN = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRP = new DecimalFormatSymbols();
        formatRP.setCurrencySymbol("Rp. ");
        formatRP.setMonetaryDecimalSeparator(',');
        formatRP.setGroupingSeparator('.');
        kursIDN.setDecimalFormatSymbols(formatRP);
        return kursIDN.format(amountDouble);
    }

    public static String parseRupiahLongNoSymbol(Long amount){
        Double amountDouble = new Double(amount);
        DecimalFormat kursIDN = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRP = new DecimalFormatSymbols();
        formatRP.setCurrencySymbol("");
        formatRP.setMonetaryDecimalSeparator(',');
        formatRP.setGroupingSeparator('.');
        kursIDN.setDecimalFormatSymbols(formatRP);
        return kursIDN.format(amountDouble);
    }

    public static Bitmap getBitmapFromURL(String src){
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    public static String encrypt(String data){
        if (data == null){
            return "";
        }
        else{
            return magicCrypt.encrypt(data);
        }
    }

    public static String decrypt(String data){
        return magicCrypt.decrypt(data);
    }

    public static void notifwarning(Context mcontex, View root, String snackTitle) {
        Snackbar snackbar = Snackbar.make(root, snackTitle, Snackbar.LENGTH_SHORT);
        snackbar.show();
        View view = snackbar.getView();
        view.setBackgroundColor(ContextCompat.getColor(mcontex, R.color.colorWhite));
        TextView txtv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            txtv.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(mcontex, R.drawable.ic_info_outline_secondary_24dp), null, null, null);
        }
        txtv.setCompoundDrawablePadding(30);
        txtv.setMaxLines(5);
        txtv.setTextColor(ContextCompat.getColor(mcontex, R.color.colorWarning));
        txtv.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public static Snackbar notifWarningButton(Context mcontex, View root, String snackTitle) {
        Snackbar snackbar = Snackbar.make(root, snackTitle, Snackbar.LENGTH_LONG);
        snackbar.show();
        View view = snackbar.getView();
        view.setBackgroundColor(ContextCompat.getColor(mcontex, R.color.colorWhite));
        TextView txtv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            txtv.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(mcontex, R.drawable.ic_info_outline_secondary_24dp), null, null, null);
        }
        txtv.setCompoundDrawablePadding(30);
        txtv.setMaxLines(5);
        txtv.setTextColor(ContextCompat.getColor(mcontex, R.color.colorWarning));
        txtv.setGravity(Gravity.CENTER_HORIZONTAL);

        return snackbar;
    }

    public static void notifinfo(Context mcontex, View root, String snackTitle) {
        Snackbar snackbar = Snackbar.make(root, snackTitle, Snackbar.LENGTH_SHORT);
        snackbar.show();
        View view = snackbar.getView();
        view.setBackgroundColor(ContextCompat.getColor(mcontex, R.color.colorWhite));
        TextView txtv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            txtv.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(mcontex, R.drawable.ic_info_outline_blue), null, null, null);
        }
        txtv.setCompoundDrawablePadding(30);
        txtv.setMaxLines(5);
        txtv.setTextColor(ContextCompat.getColor(mcontex, R.color.colorInfo));
        txtv.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public static void notifinfoLong(Context mcontex, View root, String snackTitle) {
        Snackbar snackbar = Snackbar.make(root, snackTitle, Snackbar.LENGTH_LONG);
        snackbar.show();
        View view = snackbar.getView();
        view.setBackgroundColor(ContextCompat.getColor(mcontex, R.color.colorWhite));
        TextView txtv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            txtv.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(mcontex, R.drawable.ic_info_outline_blue), null, null, null);
        }
        txtv.setCompoundDrawablePadding(30);
        txtv.setMaxLines(5);
        txtv.setTextColor(ContextCompat.getColor(mcontex, R.color.colorInfo));
        txtv.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public static void notifInfoDashboard(Context mcontex, View root, String snackTitle,int idPesan) {
        Snackbar snackbar = Snackbar.make(root, snackTitle, 5000);
        snackbar.setAction("Hilangkan", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetAlertDialog dialog=new SweetAlertDialog(mcontex,SweetAlertDialog.WARNING_TYPE);
                dialog.setTitle("Hilangkan Pesan?");
                dialog.setContentText("Pesan ini tidak akan muncul lagi untuk seterusnya\n");
                dialog.setConfirmText("Hapus pesan");
                dialog.setCancelText("Batal");
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Realm realm = Realm.getDefaultInstance();
                        PesanDashboardPojo dataRealm=realm.where(PesanDashboardPojo.class).equalTo("idPesan",idPesan).findFirst();
                        realm.beginTransaction();
                        dataRealm.setPesanAktif(false);
                        realm.insertOrUpdate(dataRealm);
                        realm.commitTransaction();
                        realm.close();
                        dialog.dismissWithAnimation();
                    }
                });
                dialog.show();


            }
        });
        snackbar.show();
        View view = snackbar.getView();
        view.setBackgroundColor(ContextCompat.getColor(mcontex, R.color.colorWhite));
        TextView txtv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            txtv.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(mcontex, R.drawable.ic_info_outline_blue), null, null, null);
        }
        txtv.setCompoundDrawablePadding(30);
        txtv.setMaxLines(5);
        txtv.setTextColor(ContextCompat.getColor(mcontex, R.color.colorInfo));
        txtv.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public static void notiferror(Context mcontex, View root, String snackTitle) {

        //hide the keyboard
        InputMethodManager imm = (InputMethodManager)mcontex.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(root.getWindowToken(), 0);

        //then show the snackbar
        Snackbar snackbar = Snackbar.make(root, snackTitle, Snackbar.LENGTH_LONG);
        snackbar.show();
        View view = snackbar.getView();
        view.setBackgroundColor(ContextCompat.getColor(mcontex, R.color.colorWhite));
        TextView txtv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            txtv.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(mcontex, R.drawable.ic_error_outline_secondary_24dp), null, null, null);
        }
        txtv.setCompoundDrawablePadding(30);
        txtv.setMaxLines(5);
        txtv.setTextColor(ContextCompat.getColor(mcontex, R.color.colorError));

        txtv.setGravity(Gravity.CENTER_HORIZONTAL);


    }

    public static void notifsuccess(Context mcontex, View root, String snackTitle) {
        Snackbar snackbar = Snackbar.make(root, snackTitle, Snackbar.LENGTH_LONG);
        snackbar.show();
        View view = snackbar.getView();
        view.setBackgroundColor(ContextCompat.getColor(mcontex, R.color.colorWhite));
        TextView txtv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            txtv.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(mcontex, R.drawable.ic_success), null, null, null);
        }
        txtv.setCompoundDrawablePadding(30);
        txtv.setTextColor(ContextCompat.getColor(mcontex, R.color.colorGreenSuccess));
        txtv.setMaxLines(5);
        txtv.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public static Snackbar notifsuccessWithButton(Context mcontex, View root, String snackTitle) {
        Snackbar snackbar = Snackbar.make(root, snackTitle, Snackbar.LENGTH_LONG);
        snackbar.show();
        View view = snackbar.getView();
        view.setBackgroundColor(ContextCompat.getColor(mcontex, R.color.colorWhite));
        TextView txtv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            txtv.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(mcontex, R.drawable.ic_success), null, null, null);
        }
        txtv.setCompoundDrawablePadding(30);
        txtv.setMaxLines(5);
        txtv.setTextColor(ContextCompat.getColor(mcontex, R.color.colorGreenSuccess));
        txtv.setGravity(Gravity.CENTER_HORIZONTAL);

        return snackbar;
    }

    //METHOD UNTUK MENGUBAH ICON DI TEXTFIELD SECARA DINAMIS, KLAU SUDAH DIISI DIA CEKLIS, KALO ISINYA DIHAPUS DIA JADI WARNING
    public static void dynamicIconLogoChange(final Context context, EditText editText, final TextFieldBoxes textFieldBoxes){

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()==0||s.toString().equalsIgnoreCase("pilih")){
                    textFieldBoxes.setIsResponsiveIconColor(false);
                    textFieldBoxes.getIconImageButton().setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryRed));

                    textFieldBoxes.getIconImageButton().setImageResource(R.drawable.ic_error_outline_secondary_24dp);

                }
                else{
                    textFieldBoxes.setIsResponsiveIconColor(false);
                    textFieldBoxes.getIconImageButton().setColorFilter(ContextCompat.getColor(context, R.color.colorGreenSuccess));
                    textFieldBoxes.getIconImageButton().setImageResource(R.drawable.ic_checked);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    //METHOD MENGUBAH ICON TEXTFIELDBOXES KHUSUS UNTUK DROPDOWN/FRAGMENT DIALOG
    public static void dynamicIconLogoChangeDropdown( Context context,final TextFieldBoxes textFieldBoxes){


        textFieldBoxes.setIsResponsiveIconColor(false);
        textFieldBoxes.getIconImageButton().setColorFilter(ContextCompat.getColor(context, R.color.colorGreenSuccess));
        textFieldBoxes.getIconImageButton().setImageResource(R.drawable.ic_checked);


    }

    public static void dynamicIconLogoChangeDropdown( Context context,final TextFieldBoxes textFieldBoxes,final EditText editText){


        if(editText.getText().toString().isEmpty()||editText.getText().toString().equalsIgnoreCase("pilih")){
            textFieldBoxes.setIsResponsiveIconColor(false);
            textFieldBoxes.getIconImageButton().setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryRed));
            textFieldBoxes.getIconImageButton().setImageResource(R.drawable.ic_error_outline_secondary_24dp);
        }
        else{
            textFieldBoxes.setIsResponsiveIconColor(false);
            textFieldBoxes.getIconImageButton().setColorFilter(ContextCompat.getColor(context, R.color.colorGreenSuccess));
            textFieldBoxes.getIconImageButton().setImageResource(R.drawable.ic_checked);
        }



    }

    public static void logSecure( String tag,String message){

        if(BuildConfig.SHOW_LOG){
            Log.d(tag,message);
        }

    }

    public static void loadPhotoUserWithCache(Context context, ImageView imageView,String idFoto){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .error(R.drawable.banner_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(context)
                .load(UriApi.Baseurl.URL + UriApi.foto.urlPhotoProfil+idFoto)
                .apply(options)
                .into(imageView);
    }

    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public static Bitmap rotateImageIfRequired(Context context, Bitmap img, Uri selectedImage){
        ExifInterface ei = null;
        InputStream inputStream;
        try {
            inputStream = context.getContentResolver().openInputStream(selectedImage);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                ei = new ExifInterface(inputStream);
            }
            else{
                ei = new ExifInterface(selectedImage.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }

    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

    public static String parseDateWithName(String tgl){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        SimpleDateFormat formatTo = new SimpleDateFormat("EEEE, dd MMM yyyy HH:mm", Locale.getDefault());
        String tglTo = "";
        try {
            Date date = format.parse(tgl);
            tglTo = formatTo.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tglTo;
    }

    public static String parseTanggalGeneral(String tgl, String formatDate, String formatDateTo){
        if (tgl == null){
            return null;
        }
        else{
            SimpleDateFormat format = new SimpleDateFormat(formatDate);
            SimpleDateFormat formatTo = new SimpleDateFormat(formatDateTo);
            String tglTo = "";
            try {
                Date date = format.parse(tgl);
                tglTo = formatTo.format(date);
            }
            catch (ParseException e){
                e.printStackTrace();
            }
            return tglTo;
        }
    }

    public static String parseTanggalLahir(String tgl){
        if (tgl == null){
            return null;
        }
        else{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatTo = new SimpleDateFormat("dd-MM-yyyy");
            String tglTo = "";
            try {
                Date date = format.parse(tgl);
                tglTo = formatTo.format(date);
            }
            catch (ParseException e){
                e.printStackTrace();
            }
            return tglTo;
        }
    }

    public static int parseIntWithDefault(String data, int defaultValue){
        try {
            return Integer.parseInt(data);
        }
        catch (NumberFormatException e){
            return defaultValue;
        }
    }

    public static long parseLongWithDefault (String data, long defaultValue){
        try {
            return Long.parseLong(data);
        }
        catch (NumberFormatException e){
            return defaultValue;
        }
    }

    public static Double parseDoubleWithDefault (String data, Double defaultValue){
        try {
            return Double.parseDouble(data);
        }
        catch (NumberFormatException e){
            return defaultValue;
        }
    }

    public static String hashMd5(String data){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(data.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;

        }
        catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

    public static void customMainFonts(Context context, View view, int resources){
        CustomFonts customFonts = new CustomFonts(context, resources);
        customFonts.replaceFonts((ViewGroup) view);
    }

    public static int hasFaceImage(Bitmap image){
        try {
            Bitmap bmp = image.copy(Bitmap.Config.RGB_565, true);
            int w = bmp.getWidth();
            int h = bmp.getHeight();

            if (w % 2 == 1) {
                w++;
                bmp = Bitmap.createScaledBitmap(bmp,
                        bmp.getWidth()+1, bmp.getHeight(), false);
            }
            if (h % 2 == 1) {
                h++;
                bmp = Bitmap.createScaledBitmap(bmp,
                        bmp.getWidth(), bmp.getHeight()+1, false);
            }

            FaceDetector fd = new FaceDetector(bmp.getWidth(), bmp.getHeight(), 1);

            FaceDetector.Face[] faces = new FaceDetector.Face[1];

            int numFace = fd.findFaces(bmp, faces);
            bmp.recycle();

            return numFace;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public static String encodeImageTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    public static byte[] decodeImageTobase64(String image){
        byte[] imageByteArray = Base64.decode(image, Base64.DEFAULT);
        return  imageByteArray;
    }

    public static String maskString(String strText, int start, int end, char maskChar)
            throws Exception{

        if(strText == null || strText.equals(""))
            return "";

        if(start < 0)
            start = 0;

        if( end > strText.length() )
            end = strText.length();

        if(start > end)
            throw new Exception("End index cannot be greater than start index");

        int maskLength = end - start;

        if(maskLength == 0)
            return strText;

        StringBuilder sbMaskString = new StringBuilder(maskLength);

        for(int i = 0; i < maskLength; i++){
            sbMaskString.append(maskChar);
        }

        return strText.substring(0, start)
                + sbMaskString.toString()
                + strText.substring(start + maskLength);
    }

    public static boolean isGooglePlayServicesAvailable(final Context context) {
        try {
            GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
            int result = googleAPI.isGooglePlayServicesAvailable(context);
            if (result != ConnectionResult.SUCCESS) {
                if (googleAPI.isUserResolvableError(result)) {
                    googleAPI.getErrorDialog((AppCompatActivity) context, result,
                            0).show();
                }
                return false;
            }
            return true;
        }
        catch (Exception e)
        {
            AppUtil.showToastShort(context, e.getMessage());
            return false;
        }
    }

    public final static String getGeocodingKey()
    {
        String[] arr = BuildConfig.GEOCODING_KEY;
        Random r = new Random();
        int randomNumber = r.nextInt(arr.length);
        return arr[randomNumber];
    }

    public static void convertBase64ToImage(String base64String,ImageView imageView){
        byte[] imageAsBytes = Base64.decode(base64String.getBytes(), Base64.DEFAULT);
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
        );
    }

    public final static String parseNpwp(String data)
    {
        String strfix = "";
        try {
            if (data.length() == 15)
            {
                String str1 = data.substring(0, 2);
                String str2 = data.substring(2, 5);
                String str3 = data.substring(5, 8);
                String str4 = data.substring(8, 9);
                String str5 = data.substring(9, 12);
                String str6 = data.substring(12, 15);
                strfix = str1+"."+str2+"."+str3+"."+str4+"-"+str5+"."+str6;
            }
            else{
                strfix = data;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return strfix;
    }

    public void showFragmentDialog(FragmentManager fragmentManager, String title, List<MGenericModel> mGenericModel, GenericListenerOnSelect listenerOnSelect){
        DialogGenericDataFromService.display(fragmentManager, title, mGenericModel, listenerOnSelect);
    }



}