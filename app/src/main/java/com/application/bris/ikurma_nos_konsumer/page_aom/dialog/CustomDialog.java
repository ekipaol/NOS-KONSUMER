package com.application.bris.ikurma_nos_konsumer.page_aom.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.DownloadListener;

/**
 * Created by idong on 21/05/2019.
 */

public class CustomDialog {
    private static AlertDialog alertDialog = null;
    private Context mContext;

    public CustomDialog(Context context){
        this.mContext = context;
    }

    public static void DialogReguler(Context context, String message, String header){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_dialog, null);
        Button btn_close = (Button) view.findViewById(R.id.btn_close);
        TextView tv_header = (TextView) view.findViewById(R.id.tv_header);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        tv_header.setText(header);
        tv_message.setText(message);
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(view);
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        animateDialog(dialog);
        dialog.show();
    }

    public static void DialogSuccess(Context context, String header, String message, final ConfirmListener confirmListener){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_dialog_action, null);
        Button btn_close = (Button) view.findViewById(R.id.btn_close);
        TextView tv_header = (TextView) view.findViewById(R.id.tv_header);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        tv_header.setText(header);
        tv_message.setText(message);
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(view);
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmListener.success(true);
                dialog.dismiss();
            }
        });
        animateDialog(dialog);
        dialog.show();
    }

    public static void DialogError(Context context, String header, String message, final ConfirmListener confirmListener){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_dialog_action, null);
        ImageView iv_header = (ImageView) view.findViewById(R.id.iv_header);
        Button btn_close = (Button) view.findViewById(R.id.btn_close);
        TextView tv_header = (TextView) view.findViewById(R.id.tv_header);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        iv_header.setImageResource(R.drawable.ic_error);
        tv_header.setText(header);
        tv_message.setText(message);
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(view);
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmListener.success(true);
                dialog.dismiss();
            }
        });
        animateDialog(dialog);
        dialog.show();
    }

    public static void DialogConfirmation(Context context, String header, String message, final ConfirmListener confirmListener){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_dialog_confirm, null);
        Button btn_send = (Button) view.findViewById(R.id.btn_send);
        Button btn_reject = (Button) view.findViewById(R.id.btn_reject);
        TextView tv_header = (TextView) view.findViewById(R.id.tv_header);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        tv_header.setText(header);
        tv_message.setText(message);
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(view);
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();
        btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmListener.confirm(true);
                dialog.dismiss();
            }
        });
        animateDialog(dialog);
        dialog.show();
    }

    public static void DialogUpdate(Context context, String header, final ConfirmListener confirmListener){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dialog_update, null);
        Button btn_close = (Button) view.findViewById(R.id.btn_close);
        TextView tv_header = (TextView) view.findViewById(R.id.tv_header);
        tv_header.setText(header);
        android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(context);
        alert.setView(view);
        alert.setCancelable(false);
        final android.app.AlertDialog dialog = alert.create();

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmListener.confirm(true);
                dialog.dismiss();
            }
        });
        animateDialog(dialog);
        dialog.show();
    }

    public static void DialogDownloadSlik(Context context, final boolean isKawin, String header, String message, final DownloadListener downloadListener){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_dialog_confirm, null);
        Button btn_two = (Button) view.findViewById(R.id.btn_send);
        Button btn_one = (Button) view.findViewById(R.id.btn_reject);
        TextView tv_header = (TextView) view.findViewById(R.id.tv_header);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        tv_header.setText(header);
        tv_message.setText(message);
        btn_one.setBackgroundResource(R.drawable.button_primary);
        btn_one.setText("SLIK Nasabah");
        btn_one.setTextColor(context.getResources().getColor(R.color.colorWhite));
        btn_two.setBackgroundResource(R.drawable.button_primary);
        btn_two.setTextColor(context.getResources().getColor(R.color.colorWhite));
        btn_two.setText("SLIK Pasangan");
        if(!isKawin){
            btn_two.setVisibility(View.GONE);
        }

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(view);
        final AlertDialog dialog = alert.create();

        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                downloadListener.downloadSLik(1);
            }
        });
        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                downloadListener.downloadSLik(2);
            }
        });
        animateDialog(dialog);
        dialog.show();
    }

    public static void DialogUpdateInformation(Context context, String header, String message){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        AppPreferences appPreferences=new AppPreferences(context);
        View view = layoutInflater.inflate(R.layout.custom_dialog_update_information, null);
        Button btn_confirm = (Button) view.findViewById(R.id.btn_send);
        TextView tv_header = (TextView) view.findViewById(R.id.tv_header);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        tv_header.setText(header);
        tv_message.setText(message);

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(view);
        final AlertDialog dialog = alert.create();

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                appPreferences.setUpdateNotification("false");
            }
        });
        animateDialog(dialog);
        dialog.show();
    }

    public static void DialogInput(Context context, String header, final ConfirmListener confirmListener){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_dialog_input, null);
        Button btn_close = (Button) view.findViewById(R.id.btn_close);
        TextView tv_header = (TextView) view.findViewById(R.id.tv_header);
        final EditText et_inputkey = (EditText) view.findViewById(R.id.et_inputkey);
        btn_close.setText("Submit");
        tv_header.setText(header);
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(view);
        final AlertDialog dialog = alert.create();

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_inputkey.getText().toString().trim().equalsIgnoreCase(BuildConfig.DEVELOPER_KEY)){
                    confirmListener.confirm(true);
                    dialog.dismiss();
                }
                else{
                    confirmListener.confirm(false);
                }
            }
        });
        animateDialog(dialog);
        dialog.show();
    }

    public static void animateDialog(Dialog dialog) {
        dialog.getWindow().getAttributes().windowAnimations = R.style.AppTheme_Slide;
    }

    public static void DialogBackpress(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setMessage(R.string.title_confirm_backpress);
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((AppCompatActivity) context).finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void DialogBackpressSaved(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setMessage(R.string.title_confirm_backpress_saved);
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((AppCompatActivity) context).finish();

                Toast.makeText(context, "Data sudah tersimpan sementara di perangkat", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void DialogAction(final Context context, String message, final ConfirmListener confirmListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setMessage(message);
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confirmListener.confirm(true);
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void dialogEnabledLocationService(final Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(false);
        dialog.setMessage("Silahkan izinkan aplikasi aktifkan GPS");
        dialog.setPositiveButton("Izinkan",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        context.startActivity(myIntent);
                    }
                });
        dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                ((AppCompatActivity) context).setResult(Activity.RESULT_OK,
                        new Intent().putExtra("latitude", "0").putExtra("longitude", "0"));
                ((AppCompatActivity) context).finish();
            }
        });
        dialog.show();
    }
}
