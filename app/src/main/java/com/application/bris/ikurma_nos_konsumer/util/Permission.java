package com.application.bris.ikurma_nos_konsumer.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
/**
 * Created by PID on 10/30/2019.
 */

public class Permission {

    private Context context;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public Permission(Context context) {
        this.context = context;
    }

    public boolean Location()
    {
        // build case for Android M
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkLocationPermission())
            {
                LocationManager locateManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                if (!locateManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    CustomDialog.dialogEnabledLocationService((AppCompatActivity) context);
                    return false;
                }
                if (!AppUtil.isGooglePlayServicesAvailable(context)) {
                    ((AppCompatActivity)context).finish();
                    AppUtil.showToastShort(context, "Google Play Services not available. Ending Test case.");
                    return false;
                }
            }
            return true;
        }
        else
        {
            LocationManager locateManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            if (!locateManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                CustomDialog.dialogEnabledLocationService((AppCompatActivity) context);
                return false;
            }
            if (!AppUtil.isGooglePlayServicesAvailable(context)) {
                ((AppCompatActivity)context).finish();
                AppUtil.showToastShort(context, "Google Play Services not available. Ending Test case.");
                return false;
            }
            return true;
        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
}
