package com.application.bris.ikurma_nos_konsumer.page_aom.dialog;

import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PID on 5/19/2019.
 */

public class BSBottomCamera extends BottomSheetDialogFragment implements View.OnClickListener{

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ll_takephoto)
    LinearLayout ll_takephoto;
    @BindView(R.id.ll_pickphoto)
    LinearLayout ll_pickphoto;
    @BindView(R.id.btn_close)
    ImageView btn_close;
    private static CameraListener cameraListener;
    private static String title = "Foto Nasabah";

    public static BSBottomCamera display(FragmentManager fragmentManager, CameraListener cameraListenerId){
        cameraListener = cameraListenerId;
        BSBottomCamera BSBottomCamera = new BSBottomCamera();
        BSBottomCamera.show(fragmentManager, "Open Menu Camera");
        return BSBottomCamera;
    }

    public static BSBottomCamera displayWithTitle(FragmentManager fragmentManager, CameraListener cameraListenerId, String titleId){
        cameraListener = cameraListenerId;
        title = titleId;
        BSBottomCamera BSBottomCamera = new BSBottomCamera();
        BSBottomCamera.show(fragmentManager, "Open Menu Camera");
        return BSBottomCamera;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.bottomsheet_takepicture, container, false);
        ButterKnife.bind(this, v);
        tv_title.setText(title);
        ll_pickphoto.setOnClickListener(this);
        ll_takephoto.setOnClickListener(this);
        btn_close.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_takephoto :
                cameraListener.onSelectMenuCamera("Take Photo");
                dismiss();
                break;
            case R.id.ll_pickphoto:
                cameraListener.onSelectMenuCamera("Pick Photo");
                dismiss();
                break;
            case R.id.btn_close:
                dismiss();
                break;
        }
    }
}
