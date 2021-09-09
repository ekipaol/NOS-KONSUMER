package com.application.bris.ikurma_nos_konsumer.view.corelayout.activation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IDONG on 07/08/2019.
 */

public class ScanActivity extends AppCompatActivity implements DecoratedBarcodeView.TorchListener{

    @BindView(R.id.switch_flashlight) Button switchFlashlightButton;
    @BindView(R.id.iv_flash) ImageView flashButton;
    @BindView(R.id.qr_scanner_layout)
    DecoratedBarcodeView barcodeScannerView;
    @BindView(R.id.progressbar_loading) RelativeLayout loading;

    private CaptureManager capture;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        ButterKnife.bind(this);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Aktivasi i-Kurma");
        barcodeScannerView.setTorchListener(this);
        checkPermissionCamera();

        if (!hasFlash()) {
            flashButton.setVisibility(View.GONE);
        }
        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();

        flashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getString(R.string.turn_on_flashlight).equals(switchFlashlightButton.getText())) {
                        barcodeScannerView.setTorchOn();
                    } else {
                        barcodeScannerView.setTorchOff();
                    }

            }
//                if(flashButton.getResources().getR)
//                {
//                    Log.d("TEST MASUL", "2222");
//                    barcodeScannerView.setTorchOn();
//                }
//                else
//                {
//                    Log.d("TEST MASUL", "3333");
//                    barcodeScannerView.setTorchOff();
//                }
//            }
        });
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    public void checkPermissionCamera()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_CAMERA_REQUEST_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                AppUtil.showToastLong(this, "Camera Permission Granted");
            }
            else {
                AppUtil.showToastLong(this, "Camera Permission Denied");
                onBackPressed();
            }
        }
    }

    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }


    public void switchFlashlight(View view) {

        if(flashButton.getResources().equals(R.drawable.ic_flashon))
        {
            barcodeScannerView.setTorchOn();
        }
        else
        {
            barcodeScannerView.setTorchOff();
        }
    }

    public void switchFlashlight2 (View view)
    {
        if (getString(R.string.turn_on_flashlight).equals(switchFlashlightButton.getText())) {
            barcodeScannerView.setTorchOn();
        } else {
            barcodeScannerView.setTorchOff();
        }
    }

    @Override
    public void onTorchOn() {
        switchFlashlightButton.setText("Turn off Flashlight");
        flashButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_flashoff));
    }

    @Override
    public void onTorchOff() {
        switchFlashlightButton.setText("Turn on Flashlight");
        flashButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_flashon));
    }
}