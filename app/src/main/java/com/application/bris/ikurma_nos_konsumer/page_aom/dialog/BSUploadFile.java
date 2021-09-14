package com.application.bris.ikurma_nos_konsumer.page_aom.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.databinding.BottomsheetUploadfileBinding ;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


/**
 * Created by PID on 5/19/2019.
 */

public class BSUploadFile extends BottomSheetDialogFragment implements View.OnClickListener{


    private static CameraListener cameraListener;
    private static String title = "Foto Nasabah";
    private BottomsheetUploadfileBinding binding;

    public static BSUploadFile display(FragmentManager fragmentManager,CameraListener cameraListenerId){
        cameraListener = cameraListenerId;
        BSUploadFile BSBottomCamera = new BSUploadFile();
        BSBottomCamera.show(fragmentManager, "Open Menu Camera");
        return BSBottomCamera;
    }

    public static BSUploadFile displayWithTitle(FragmentManager fragmentManager, CameraListener cameraListenerId, String titleId){
        cameraListener = cameraListenerId;
        title = titleId;
        BSUploadFile BSBottomCamera = new BSUploadFile();
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
        binding = BottomsheetUploadfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        binding.tvTitle.setText(title);
        binding.llPickphoto.setOnClickListener(this);
        binding.llPickgallery.setOnClickListener(this);
        binding.llTakefile.setOnClickListener(this);
        binding.btnClose.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_pickphoto:
                cameraListener.onSelectMenuCamera("Take Photo");
                dismiss();
                break;
            case R.id.ll_pickgallery:
                cameraListener.onSelectMenuCamera("Pick Photo");
                dismiss();
                break;
            case R.id.ll_takefile:
                cameraListener.onSelectMenuCamera("Pick File");
                dismiss();
                break;
            case R.id.btn_close:
                dismiss();
                break;
        }
    }
}
