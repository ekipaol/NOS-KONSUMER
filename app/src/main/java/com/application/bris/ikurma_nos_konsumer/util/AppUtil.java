package com.application.bris.ikurma_nos_konsumer.util;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.media.FaceDetector;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseFile;
import com.application.bris.ikurma_nos_konsumer.api.model.request.foto.ReqUploadFile;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.PesanDashboardPojo;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataMarketing;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhotoFromIdLogicalDoc;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.kelengkapandokumen.KelengkapanDokumenActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_marketing.DataMarketingActivity;
import com.application.bris.ikurma_nos_konsumer.util.service_encrypt.MagicCryptHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.fragment.app.FragmentManager;

import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.util.magiccrypt.MagicCrypt;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by Idong
 */

public class AppUtil {
    public static MagicCrypt magicCrypt = new MagicCrypt();
    private Snackbar snackbar;
    public static String idFoto="0";

    private static AppPreferences appPreferences;

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

    public static void disableEditTexts(View viewInduk) {

        if (viewInduk instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) viewInduk;
            for (int i = 0, count = vg.getChildCount(); i < count; ++i) {
                View view = vg.getChildAt(i);
                disableEditTexts(view);
                if (view instanceof ExtendedEditText) {
                    ((ExtendedEditText) view).setFocusable(false);
                    ((ExtendedEditText) view).setOnClickListener(null);

                } else if (view instanceof TextFieldBoxes) {
//    ((TextFieldBoxes) view).setEnabled(false);
//    ((TextFieldBoxes) view).setClickable(false);
                    ((TextFieldBoxes) view).setOnClickListener(null);
//    ((TextFieldBoxes) view).getEndIconImageButton(false);
                    ((TextFieldBoxes) view).removeEndIcon();


                }


            }
        }

    }

    public static boolean isPegawaiOrganik(int fidRole) {
        //- 149, Consumer Business Relation Manager : tom.holland
        //- 150, Consumer Business Staff : hayamwuruk
        switch (fidRole) {
            case 149:
            case 150:
                return true;
            default:
                return false;
        }
    }

    public static boolean isPegawaiMitra(int fidRole) {
        //- 151, Consumer Financing Eksekutif : cfe010101
        //- 152, Sales Force : sf010101
        switch (fidRole) {
            case 151:
            case 152:
                return true;
            default:
                return false;
        }
    }

    public static void disableButtons(View viewInduk) {

        if (viewInduk instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) viewInduk;
            for (int i = 0, count = vg.getChildCount(); i < count; ++i) {
                View view = vg.getChildAt(i);
                disableButtons(view);
                if (view instanceof Button) {
                    ((Button) view).setVisibility(View.GONE);

                } else if (view instanceof Button) {
//    ((TextFieldBoxes) view).setEnabled(false);
//    ((TextFieldBoxes) view).setClickable(false);
                    ((Button) view).setOnClickListener(null);
//    ((TextFieldBoxes) view).getEndIconImageButton(false);


                }


            }
        }

    }

    public static String hashSha256(String text){

        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();

            byte[] byteData = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < byteData.length; i++){
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
        catch(NoSuchAlgorithmException e){
            logSecure("ALGO EXEPTION","no such algorithm");
            return "";
        }


    }

    public static String formatStringDoubleBelakangKoma(String string, int berapaAngkaBelakangKoma) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(berapaAngkaBelakangKoma);

        Double pencairan = Double.parseDouble(string);

        return df.format(pencairan);
    }


    public static String getInitials(String label) {
        String initials = "";
        String[] parts = label.split(" ");
        char initial;
        for (int i = 0; i < parts.length; i++) {
            initial = parts[i].charAt(0);
            initials += initial;
        }
        return (initials.toUpperCase());
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
//  int[] grantResults)
// to handle the case where the user grants the permission. See the documentation
// for ActivityCompat#requestPermissions for more details.
            }
            deviceName = Build.MANUFACTURER
                    + " " + Build.MODEL + " Android " + Build.VERSION.RELEASE
                    + " " + Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName();
            imei = telephonyManager.getDeviceId().toString();
        } catch (Exception e) {
        }

        deviceInfo.put(Constants.DEVICE_ID, deviceId);
        deviceInfo.put(Constants.DEVICE_NAME, deviceName);
        deviceInfo.put(Constants.IMEI, imei);

        return deviceInfo;
    }


    public static String parseRupiah(String amount) {
        Double amountDouble = Double.valueOf(amount);
        DecimalFormat kursIDN = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRP = new DecimalFormatSymbols();
        formatRP.setCurrencySymbol("Rp. ");
        formatRP.setMonetaryDecimalSeparator(',');
        formatRP.setGroupingSeparator('.');
        kursIDN.setDecimalFormatSymbols(formatRP);
        return kursIDN.format(amountDouble);
    }

    public static String parseRupiahNoSymbol(String amount) {
        Double amountDouble = Double.valueOf(amount);
        DecimalFormat kursIDN = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRP = new DecimalFormatSymbols();
        formatRP.setCurrencySymbol("");
        formatRP.setMonetaryDecimalSeparator(',');
        formatRP.setGroupingSeparator('.');
        kursIDN.setDecimalFormatSymbols(formatRP);
        return kursIDN.format(amountDouble);
    }

    public static String parseNumberWatcher(String amount) {
        Double amountDouble = Double.valueOf(amount);
        DecimalFormat kursIDN = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRP = new DecimalFormatSymbols();
        formatRP.setCurrencySymbol("");
        formatRP.setMonetaryDecimalSeparator('.');
        formatRP.setGroupingSeparator(',');
        kursIDN.setDecimalFormatSymbols(formatRP);
        return kursIDN.format(amountDouble);
    }

    public static void genericCalendarDialog(Context context, EditText editText) {
        SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        Calendar calendar;
        DatePickerDialog datePickerDialog;
        calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener datepickerDialog = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calString = dateClient.format(calendar.getTime());
                editText.setText(calString);
            }
        };

        datePickerDialog = new DatePickerDialog(context, R.style.AppTheme_TimePickerTheme, datepickerDialog, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    public static void customCalendarDialog(Context context, EditText editText, String dateFormat) {
        SimpleDateFormat dateClient = new SimpleDateFormat(dateFormat, Locale.US);
        Calendar calendar;
        DatePickerDialog datePickerDialog;
        calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener ls_tanggalLahirPasangan = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calString = dateClient.format(calendar.getTime());
                editText.setText(calString);
            }
        };

        datePickerDialog = new DatePickerDialog(context, R.style.AppTheme_TimePickerTheme, ls_tanggalLahirPasangan, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }


//    }

    public static String parseValas(String amount) {
        Double amountDouble = Double.valueOf(amount);
        DecimalFormat kursIDN = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRP = new DecimalFormatSymbols();
        formatRP.setCurrencySymbol("$ ");
        formatRP.setMonetaryDecimalSeparator(',');
        formatRP.setGroupingSeparator('.');
        kursIDN.setDecimalFormatSymbols(formatRP);
        return kursIDN.format(amountDouble);
    }

    public static String parseRupiahInt(Integer amount) {
        Double amountDouble = new Double(amount);
        DecimalFormat kursIDN = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRP = new DecimalFormatSymbols();
        formatRP.setCurrencySymbol("Rp. ");
        formatRP.setMonetaryDecimalSeparator(',');
        formatRP.setGroupingSeparator('.');
        kursIDN.setDecimalFormatSymbols(formatRP);
        return kursIDN.format(amountDouble);
    }

    public static String parseRupiahLong(Long amount) {
        Double amountDouble = new Double(amount);
        DecimalFormat kursIDN = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRP = new DecimalFormatSymbols();
        formatRP.setCurrencySymbol("Rp. ");
        formatRP.setMonetaryDecimalSeparator(',');
        formatRP.setGroupingSeparator('.');
        kursIDN.setDecimalFormatSymbols(formatRP);
        return kursIDN.format(amountDouble);
    }

    public static String parseRupiahLongNoSymbol(Long amount) {
        Double amountDouble = new Double(amount);
        DecimalFormat kursIDN = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRP = new DecimalFormatSymbols();
        formatRP.setCurrencySymbol("");
        formatRP.setMonetaryDecimalSeparator(',');
        formatRP.setGroupingSeparator('.');
        kursIDN.setDecimalFormatSymbols(formatRP);
        return kursIDN.format(amountDouble);
    }

    public static Bitmap getBitmapFromURL(String src) {
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

    public static String encrypt(String data) {
        //added to check if another cipher not initialized error occured
        MagicCrypt magicCrypt = new MagicCrypt();

        if (data == null) {
            return "";
        } else {
            return magicCrypt.encrypt(data);
        }
    }

    public static String decrypt(String data) {
        //added to check if another cipher not initialized error occured
        MagicCrypt magicCrypt = new MagicCrypt();

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

    public static void notifInfoDashboard(Context mcontex, View root, String snackTitle, int idPesan) {
        Snackbar snackbar = Snackbar.make(root, snackTitle, 5000);
        snackbar.setAction("Hilangkan", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetAlertDialog dialog = new SweetAlertDialog(mcontex, SweetAlertDialog.WARNING_TYPE);
                dialog.setTitle("Hilangkan Pesan?");
                dialog.setContentText("Pesan ini tidak akan muncul lagi untuk seterusnya\n");
                dialog.setConfirmText("Hapus pesan");
                dialog.setCancelText("Batal");
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Realm realm = Realm.getDefaultInstance();
                        PesanDashboardPojo dataRealm = realm.where(PesanDashboardPojo.class).equalTo("idPesan", idPesan).findFirst();
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
        InputMethodManager imm = (InputMethodManager) mcontex.getSystemService(Activity.INPUT_METHOD_SERVICE);
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
    public static void dynamicIconLogoChange(final Context context, EditText editText, final TextFieldBoxes textFieldBoxes) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 0 || s.toString().equalsIgnoreCase("pilih")) {
                    textFieldBoxes.setIsResponsiveIconColor(false);
                    textFieldBoxes.getIconImageButton().setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryRed));

                    textFieldBoxes.getIconImageButton().setImageResource(R.drawable.ic_error_outline_secondary_24dp);

                } else {
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
    public static void dynamicIconLogoChangeDropdown(Context context, final TextFieldBoxes textFieldBoxes) {


        textFieldBoxes.setIsResponsiveIconColor(false);
        textFieldBoxes.getIconImageButton().setColorFilter(ContextCompat.getColor(context, R.color.colorGreenSuccess));
        textFieldBoxes.getIconImageButton().setImageResource(R.drawable.ic_checked);


    }

    public static void dynamicIconLogoChangeDropdown(Context context, final TextFieldBoxes textFieldBoxes, final EditText editText) {


        if (editText.getText().toString().isEmpty() || editText.getText().toString().equalsIgnoreCase("pilih")) {
            textFieldBoxes.setIsResponsiveIconColor(false);
            textFieldBoxes.getIconImageButton().setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryRed));
            textFieldBoxes.getIconImageButton().setImageResource(R.drawable.ic_error_outline_secondary_24dp);
        } else {
            textFieldBoxes.setIsResponsiveIconColor(false);
            textFieldBoxes.getIconImageButton().setColorFilter(ContextCompat.getColor(context, R.color.colorGreenSuccess));
            textFieldBoxes.getIconImageButton().setImageResource(R.drawable.ic_checked);
        }


    }

    public static void logSecure(String tag, String message) {

        if (BuildConfig.SHOW_LOG) {
            Log.d(tag, message);
        }

    }

    public static void setLoadPdf(Context context, int idPdf, ImageView imageView) {
        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_pdf_hd));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        String url_pdf = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + idPdf;
        Uri external = Uri.parse(url_pdf);
        Intent intentPdf;
        intentPdf = new Intent(Intent.ACTION_VIEW);
        intentPdf.setDataAndType(external, "application/pdf");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    context.startActivity(intentPdf);
                } catch (ActivityNotFoundException e) {
                    // No application to view, ask to download one
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Aplikasi PDF Tidak Ditemukan");
                    builder.setMessage("Download Dari Playstore?");
                    builder.setPositiveButton("Ya",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent marketIntent = new Intent(Intent.ACTION_VIEW);
                                    marketIntent
                                            .setData(Uri
                                                    .parse("market://details?id=com.adobe.reader"));
                                    context.startActivity(marketIntent);
                                }
                            });
                    builder.setNegativeButton("Tidak", null);
                    builder.create().show();
                }
            }
        });

    }



    public static void setLoadPdf(Context context, String idPdf, ImageView imageView) {
        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_pdf_hd));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        String url_pdf = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + idPdf;
        Uri external = Uri.parse(url_pdf);
        Intent intentPdf;
        intentPdf = new Intent(Intent.ACTION_VIEW);
        intentPdf.setDataAndType(external, "application/pdf");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    context.startActivity(intentPdf);
                } catch (ActivityNotFoundException e) {
                    // No application to view, ask to download one
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Aplikasi PDF Tidak Ditemukan");
                    builder.setMessage("Download Dari Playstore?");
                    builder.setPositiveButton("Ya",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent marketIntent = new Intent(Intent.ACTION_VIEW);
                                    marketIntent
                                            .setData(Uri
                                                    .parse("market://details?id=com.adobe.reader"));
                                    context.startActivity(marketIntent);
                                }
                            });
                    builder.setNegativeButton("Tidak", null);
                    builder.create().show();
                }
            }
        });

    }

    public static void setLoadPdfClickOnly(Context context, String idPdf, ImageView imageView) {
        String url_pdf = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + idPdf;
        Uri external = Uri.parse(url_pdf);
        Intent intentPdf;
        intentPdf = new Intent(Intent.ACTION_VIEW);
        intentPdf.setDataAndType(external, "application/pdf");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    context.startActivity(intentPdf);
                } catch (ActivityNotFoundException e) {
                    // No application to view, ask to download one
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Aplikasi PDF Tidak Ditemukan");
                    builder.setMessage("Download Dari Playstore?");
                    builder.setPositiveButton("Ya",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent marketIntent = new Intent(Intent.ACTION_VIEW);
                                    marketIntent
                                            .setData(Uri
                                                    .parse("market://details?id=com.adobe.reader"));
                                    context.startActivity(marketIntent);
                                }
                            });
                    builder.setNegativeButton("Tidak", null);
                    builder.create().show();
                }
            }
        });

    }

    public static void loadPhotoUserWithCache(Context context, ImageView imageView, String idFoto) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .error(R.drawable.banner_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(context)
                .load(UriApi.Baseurl.URL + UriApi.foto.urlPhotoProfil + idFoto)
                .apply(options)
                .into(imageView);
    }

    public static void setImageGlide(Context context,int fidPhoto,ImageView iv_foto){
        MagicCryptHelper encryptor=new MagicCryptHelper();
        appPreferences=new AppPreferences(context);

        String imageUrlToEncode= encryptor.encrypt(Integer.toString(appPreferences.getUid())+"_"+fidPhoto);

        String url_photo = null;
        GlideUrl urlGlide=null;
        try {
//            url_photo = UriApi.Baseurl.URL + UriApi.foto.urlPhotoSecure + URLEncoder.encode(imageUrlToEncode, StandardCharsets.UTF_8.toString());
            url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + fidPhoto;

            urlGlide = new GlideUrl(url_photo, new LazyHeaders.Builder()
                    .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                    .build());

//            logSecure("whatisdat","Bearer "+appPreferences.getToken());
        } catch (Exception e) {
            e.printStackTrace();
        }



        Glide

                .with(context)
                .asBitmap()
                .load(urlGlide)
//                .load(url_photo)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.banner_placeholder)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv_foto.setImageBitmap(resource);
                    }
                });

        iv_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    DialogPreviewPhoto.display(((AppCompatActivity) iv_foto.getContext()).getSupportFragmentManager(), "Preview Foto", ((BitmapDrawable)iv_foto.getDrawable()).getBitmap());
                }
                catch (ClassCastException e){
                    DialogPreviewPhoto.display(((AppCompatActivity) iv_foto.getContext()).getSupportFragmentManager(), "Preview Foto", (((RoundedDrawable)iv_foto.getDrawable()).getSourceBitmap()));
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    public static void setImageGlide(Context context,String fidPhoto,ImageView iv_foto){
        MagicCryptHelper encryptor=new MagicCryptHelper();

         appPreferences=new AppPreferences(context);

        String imageUrlToEncode= encryptor.encrypt(Integer.toString(appPreferences.getUid())+"_"+fidPhoto);

        String url_photo = null;

        GlideUrl urlGlide=null;
        try {
//            url_photo = UriApi.Baseurl.URL + UriApi.foto.urlPhotoSecure + URLEncoder.encode(imageUrlToEncode, StandardCharsets.UTF_8.toString());
            url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + fidPhoto;

            urlGlide = new GlideUrl(url_photo, new LazyHeaders.Builder()
                    .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                    .build());

            logSecure("whatisdat","Bearer "+appPreferences.getToken());
        } catch (Exception e) {
            e.printStackTrace();
        }


        Glide

                .with(context)
                .asBitmap()
                .load(urlGlide)
                .placeholder(R.drawable.banner_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv_foto.setImageBitmap(resource);
                    }
                });

        iv_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    DialogPreviewPhoto.display(((AppCompatActivity) iv_foto.getContext()).getSupportFragmentManager(), "Preview Foto", ((BitmapDrawable)iv_foto.getDrawable()).getBitmap());
                }
                catch (ClassCastException e){
                    DialogPreviewPhoto.display(((AppCompatActivity) iv_foto.getContext()).getSupportFragmentManager(), "Preview Foto", (((RoundedDrawable)iv_foto.getDrawable()).getSourceBitmap()));
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    public static void setImageGlideNoOnclick(Context context,String fidPhoto,ImageView iv_foto){
        MagicCryptHelper encryptor=new MagicCryptHelper();

        appPreferences=new AppPreferences(context);

        String imageUrlToEncode= encryptor.encrypt(Integer.toString(appPreferences.getUid())+"_"+fidPhoto);

        String url_photo = null;

        GlideUrl urlGlide=null;
        try {
//            url_photo = UriApi.Baseurl.URL + UriApi.foto.urlPhotoSecure + URLEncoder.encode(imageUrlToEncode, StandardCharsets.UTF_8.toString());
            url_photo = UriApi.Baseurl.URL + UriApi.foto.urlFileDirect + fidPhoto;

            urlGlide = new GlideUrl(url_photo, new LazyHeaders.Builder()
                    .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                    .build());

            logSecure("whatisdat","Bearer "+appPreferences.getToken());
        } catch (Exception e) {
            e.printStackTrace();
        }


        Glide

                .with(context)
                .asBitmap()
                .load(urlGlide)
                .placeholder(R.drawable.banner_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv_foto.setImageBitmap(resource);
                    }
                });
    }

    public static void setImageGlideClickOnly(Context context,String fidPhoto,ImageView iv_foto){

        iv_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    DialogPreviewPhotoFromIdLogicalDoc.display(((AppCompatActivity) iv_foto.getContext()).getSupportFragmentManager(), "Preview Foto", fidPhoto);
                }
                catch (ClassCastException e){
                    DialogPreviewPhotoFromIdLogicalDoc.display(((AppCompatActivity) iv_foto.getContext()).getSupportFragmentManager(), "Preview Foto", fidPhoto);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
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

    public static Bitmap rotateImageIfRequired(Context context, Bitmap img, Uri selectedImage) {
        ExifInterface ei = null;
        InputStream inputStream;
        try {
            inputStream = context.getContentResolver().openInputStream(selectedImage);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                ei = new ExifInterface(inputStream);
            } else {
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

    public static String parseDateWithName(String tgl) {
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

    public static String parseTanggalGeneral(String tgl, String formatDate, String formatDateTo) {
        if (tgl == null) {
            return null;
        } else {
            SimpleDateFormat format = new SimpleDateFormat(formatDate);
            SimpleDateFormat formatTo = new SimpleDateFormat(formatDateTo);
            String tglTo = "";
            try {
                Date date = format.parse(tgl);
                tglTo = formatTo.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return tglTo;
        }
    }

    public static String parseTanggalLahir(String tgl) {
        if (tgl == null) {
            return null;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatTo = new SimpleDateFormat("dd-MM-yyyy");
            String tglTo = "";
            try {
                Date date = format.parse(tgl);
                tglTo = formatTo.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return tglTo;
        }
    }

    public static int parseIntWithDefault(String data, int defaultValue) {
        try {
            return Integer.parseInt(data);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static long parseLongWithDefault(String data, long defaultValue) {
        try {
            return Long.parseLong(data);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static void loadImageWithFileNameCheck(Context context,String filename,String idFoto,ImageView imageView){
        try{
            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik

            if(filename.substring(filename.length()-3,filename.length()).equalsIgnoreCase("pdf")){
                AppUtil.setLoadPdf(context,idFoto,imageView);
            }
            else{
                AppUtil.setImageGlide(context,idFoto,imageView);
            }

        }
        catch (Exception e){
            e.printStackTrace();
            logSecure("error setdata",e.getMessage());
        }
    }

    public static void openDocumentWithFileNameCheck(Context context,String filename,String idFoto,ImageView imageView){
        try{
            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik

            if(filename.substring(filename.length()-3,filename.length()).equalsIgnoreCase("pdf")){
                AppUtil.setLoadPdfClickOnly(context,idFoto,imageView);
            }
            else{
                AppUtil.setImageGlideClickOnly(context,idFoto,imageView);
            }

        }
        catch (Exception e){
            e.printStackTrace();
            logSecure("error setdata",e.getMessage());
        }
    }

    public static void loadImageWithFileNameCheck(Context context,String filename,int idFoto,ImageView imageView){
        try{
            //kalau file name ada tulisan PDF, maka convert base 64 ke pdf biar bisa di klik

            if(filename.substring(filename.length()-3,filename.length()).equalsIgnoreCase("pdf")){
                AppUtil.setLoadPdf(context,idFoto,imageView);
            }
            else{
                AppUtil.setImageGlide(context,idFoto,imageView);
            }

        }
        catch (Exception e){
            logSecure("error setdata",e.getMessage());
        }
    }

    public static Double parseDoubleWithDefault(String data, Double defaultValue) {
        try {
            return Double.parseDouble(data);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static String hashMd5(String data) {
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

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void customMainFonts(Context context, View view, int resources) {
        CustomFonts customFonts = new CustomFonts(context, resources);
        customFonts.replaceFonts((ViewGroup) view);
    }

    public static int hasFaceImage(Bitmap image) {
        try {
            Bitmap bmp = image.copy(Bitmap.Config.RGB_565, true);
            int w = bmp.getWidth();
            int h = bmp.getHeight();

            if (w % 2 == 1) {
                w++;
                bmp = Bitmap.createScaledBitmap(bmp,
                        bmp.getWidth() + 1, bmp.getHeight(), false);
            }
            if (h % 2 == 1) {
                h++;
                bmp = Bitmap.createScaledBitmap(bmp,
                        bmp.getWidth(), bmp.getHeight() + 1, false);
            }

            FaceDetector fd = new FaceDetector(bmp.getWidth(), bmp.getHeight(), 1);

            FaceDetector.Face[] faces = new FaceDetector.Face[1];

            int numFace = fd.findFaces(bmp, faces);
            bmp.recycle();

            return numFace;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String encodeImageToBinary(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        return b.toString();
    }

    public static String encodeFileToBinary(Context context, Uri uri) {
        InputStream inputStream = null;
        String encodedFile = "", lastVal;
        try {
            inputStream = context.getContentResolver().openInputStream(uri);

            byte[] buffer = new byte[10240];//specify the size to allow
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            output.close();
            encodedFile = output.toString();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lastVal = encodedFile;
        return lastVal;
    }

    public static String encodeImageTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    public static byte[] decodeImageTobase64(String image) {
        byte[] imageByteArray = Base64.decode(image, Base64.DEFAULT);
        return imageByteArray;
    }

    public static String encodeFileToBase64(Context context, Uri uri) {
        InputStream inputStream = null;
        String encodedFile = "", lastVal;
        try {
            inputStream = context.getContentResolver().openInputStream(uri);

            byte[] buffer = new byte[10240];//specify the size to allow
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Base64OutputStream output64 = new Base64OutputStream(output, Base64.DEFAULT);

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output64.write(buffer, 0, bytesRead);
            }
            output64.close();
            encodedFile = output.toString();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lastVal = encodedFile;
        return lastVal;
    }

    public static byte[] decodeFileTobase64(String file) {
        byte[] fileByteArray = Base64.decode(file, Base64.DEFAULT);
        return fileByteArray;
    }

    public static String maskString(String strText, int start, int end, char maskChar)
            throws Exception {

        if (strText == null || strText.equals(""))
            return "";

        if (start < 0)
            start = 0;

        if (end > strText.length())
            end = strText.length();

        if (start > end)
            throw new Exception("End index cannot be greater than start index");

        int maskLength = end - start;

        if (maskLength == 0)
            return strText;

        StringBuilder sbMaskString = new StringBuilder(maskLength);

        for (int i = 0; i < maskLength; i++) {
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
        } catch (Exception e) {
            AppUtil.showToastShort(context, e.getMessage());
            return false;
        }
    }

    public final static String getGeocodingKey() {
        String[] arr = BuildConfig.GEOCODING_KEY;
        Random r = new Random();
        int randomNumber = r.nextInt(arr.length);
        return arr[randomNumber];
    }

    public static void convertBase64ToImage(String base64String, ImageView imageView) {
        byte[] imageAsBytes = Base64.decode(base64String.getBytes(), Base64.DEFAULT);
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));

//        imageView.buildDrawingCache();
//        Bitmap bmap = imageView.getDrawingCache();


            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        DialogPreviewPhoto.display(((AppCompatActivity) imageView.getContext()).getSupportFragmentManager(), "Preview Foto", ((BitmapDrawable)imageView.getDrawable()).getBitmap());
                    }
                    catch (ClassCastException e){
                        DialogPreviewPhoto.display(((AppCompatActivity) imageView.getContext()).getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)imageView.getDrawable()).getSourceBitmap());
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });
    }

    public static void convertBase64ToImageUsingGlide(Context context,String base64String, ImageView imageView) {
        byte[] imageAsBytes = Base64.decode(base64String.getBytes(), Base64.DEFAULT);
        Glide
                .with(context)
                .load(imageAsBytes)
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .into(imageView);
    }

    public static boolean checkIsPemutus(int fidRole){
        //76 BM
        //130 AM
        //159 ACFM
        if(fidRole==76||fidRole==130||fidRole==159){
            return true;
        }
        else{
            return false;
        }
    }


    public static void convertBase64ToFileWithOnClick(Context context, String base64String, ImageView imageView, String namaPdf) {
        File dwldsPath = new File(context.getExternalCacheDir() + "/" + namaPdf);
        try {
            if (base64String != null) {
                byte[] pdfAsBytes = Base64.decode(base64String, 0);
                FileOutputStream os;
                os = new FileOutputStream(dwldsPath, false);
                os.write(pdfAsBytes);
                os.flush();
                os.close();

                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_pdf_hd));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Uri path = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", dwldsPath);

                        Intent target = new Intent(Intent.ACTION_VIEW);
                        target.setDataAndType(path, "application/pdf");
                        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                        Intent intent = Intent.createChooser(target, "Open File");
                        try {
                            context.startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            // Instruct the user to install a PDF reader here, or something
                        }
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void convertBase64ToFileDirect(Context context, String base64String, ImageView imageView, String namaPdf) {
        File dwldsPath = new File(context.getExternalCacheDir() + "/" + namaPdf);
        try {
            if (base64String != null) {
                byte[] pdfAsBytes = Base64.decode(base64String, 0);
                FileOutputStream os;
                os = new FileOutputStream(dwldsPath, false);
                os.write(pdfAsBytes);
                os.flush();
                os.close();

                        Uri path = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", dwldsPath);

                        Intent target = new Intent(Intent.ACTION_VIEW);
                        target.setDataAndType(path, "application/pdf");
                        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                        Intent intent = Intent.createChooser(target, "Open File");
                        try {
                            context.startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            // Instruct the user to install a PDF reader here, or something
                        }
                    }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void convertBinaryToImage(String binaryString, ImageView imageView) {
        byte[] imageAsBytes = binaryString.getBytes();
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
        );
    }


    public static void convertBinaryToFileWithOnClick(Context context, String binaryString, ImageView imageView, String namaPdf) {
        File dwldsPath = new File(context.getExternalCacheDir() + "/" + namaPdf);
        try {
            if (binaryString != null) {
                byte[] pdfAsBytes =binaryString.getBytes();
                FileOutputStream os;
                os = new FileOutputStream(dwldsPath, false);
                os.write(pdfAsBytes);
                os.flush();
                os.close();

                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_pdf_hd));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Uri path = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", dwldsPath);

                        Intent target = new Intent(Intent.ACTION_VIEW);
                        target.setDataAndType(path, "application/pdf");
                        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                        Intent intent = Intent.createChooser(target, "Open File");
                        try {
                            context.startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            // Instruct the user to install a PDF reader here, or something
                        }
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void convertBase64ToFileAutoOpen(Context context, String base64String, String namaPdf) {
        File dwldsPath = new File(context.getExternalCacheDir() + "/" + namaPdf);
        try {
            if (base64String != null) {
                byte[] pdfAsBytes = Base64.decode(base64String, 0);
                FileOutputStream os;
                os = new FileOutputStream(dwldsPath, false);
                os.write(pdfAsBytes);
                os.flush();
                os.close();

                Uri path = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", dwldsPath);

                Intent target = new Intent(Intent.ACTION_VIEW);
                target.setDataAndType(path, "application/pdf");
                target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Intent intent = Intent.createChooser(target, "Open File");
                try {
                    context.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(context, "Anda belum memiliki aplikasi untuk buka PDF", Toast.LENGTH_LONG).show();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Terjadi Kesalahan Ketika Download PDF", Toast.LENGTH_LONG).show();
        }

    }

    public static void convertBase64ToFileAutoOpenDocx(Context context, String base64String, String namaPdf) {
        File dwldsPath = new File(context.getExternalCacheDir() + "/" + namaPdf);
        try {
            if (base64String != null) {
                byte[] pdfAsBytes = Base64.decode(base64String, 0);
                FileOutputStream os;
                os = new FileOutputStream(dwldsPath, false);
                os.write(pdfAsBytes);
                os.flush();
                os.close();

                Uri path = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", dwldsPath);

                Intent target = new Intent(Intent.ACTION_VIEW);
                target.setDataAndType(path, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Intent intent = Intent.createChooser(target, "Open File");
                try {
                    context.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(context, "Anda belum memiliki aplikasi untuk buka Document", Toast.LENGTH_LONG).show();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Terjadi Kesalahan Ketika Download Document", Toast.LENGTH_LONG).show();
        }

    }


    public final static String parseNpwp(String data) {
        String strfix = "";
        try {
            if (data.length() == 15) {
                String str1 = data.substring(0, 2);
                String str2 = data.substring(2, 5);
                String str3 = data.substring(5, 8);
                String str4 = data.substring(8, 9);
                String str5 = data.substring(9, 12);
                String str6 = data.substring(12, 15);
                strfix = str1 + "." + str2 + "." + str3 + "." + str4 + "-" + str5 + "." + str6;
            } else {
                strfix = data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strfix;
    }

    public void showFragmentDialog(FragmentManager fragmentManager, String title, List<MGenericModel> mGenericModel, GenericListenerOnSelect listenerOnSelect) {
        DialogGenericDataFromService.display(fragmentManager, title, mGenericModel, listenerOnSelect);
    }

    public static String getIdFolderLogicalDoc(){
        return "415";
    }


    //METHOD INI BELUM BISA DIPAKE, KARENA TETEP HARUS PAKE CALLBACK KE KELAS YANG MANGGILNYA
    //JADI DARIPADA REPOT YAUDAH SAK METHOD INI LANGSUNG TARUH DI MASING MASING KELAS BAE
    //TADINYA INI BIAR DINAMIS GITU, EH TERNYATA GABISA YOWIS LAH
    public static String uploadFile(Context context, String base64, String fileName, RelativeLayout loading, Activity activity) {
        ApiClientAdapter apiClientAdapter=new ApiClientAdapter(context);
        //  dataUser = getListUser();
        loading.setVisibility(View.VISIBLE);
        ReqUploadFile req=new ReqUploadFile();
        //pantekan uid
        req.setFolderId(AppUtil.getIdFolderLogicalDoc());
        req.setLanguage("en");
        req.setFileB64(base64);
        req.setFileName(fileName);
        Call<ParseResponseFile> call = apiClientAdapter.getApiInterface().uploadFileLogicalDoc(req);
        call.enqueue(new Callback<ParseResponseFile>() {
            @Override
            public void onResponse(Call<ParseResponseFile> call, Response<ParseResponseFile> response) {
                loading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    idFoto=response.body().getId();
                    logSecure("dataFile","idFoto1: "+response.body().getId());
                    AppUtil.notifsuccess(context, activity.findViewById(android.R.id.content), "Upload Berhasil");
                }
                else{
                    AppUtil.notiferror(context, activity.findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseFile> call, Throwable t) {
               loading.setVisibility(View.GONE);
                AppUtil.notiferror(context, activity.findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
            }
        });
        logSecure("dataFile","idFoto: "+idFoto);
        logSecure("dataFile","fileName: "+fileName);
        logSecure("dataFile","folderId: "+req.getFolderId());
        return idFoto;

    }


}
