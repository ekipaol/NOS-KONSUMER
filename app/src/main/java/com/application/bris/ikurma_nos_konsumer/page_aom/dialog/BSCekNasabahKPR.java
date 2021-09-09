package com.application.bris.ikurma_nos_konsumer.page_aom.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.pipeline.CeknasabahActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PID on 5/19/2019.
 */

public class BSCekNasabahKPR extends BottomSheetDialogFragment implements View.OnClickListener{

    @BindView(R.id.et_nasabahid)
    EditText et_nasabahid;
    @BindView(R.id.btn_inquiry)
    Button btn_inquiry;
    @BindView(R.id.btn_close)
    ImageView btn_close;


    public static BSCekNasabahKPR display(FragmentManager fragmentManager){
        BSCekNasabahKPR BSCekNasabah = new BSCekNasabahKPR();
        BSCekNasabah.show(fragmentManager, "Open Cek Nasabah");
        return BSCekNasabah;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.bottomsheet_inquiryktp, container, false);
        ButterKnife.bind(this, v);
        btn_inquiry.setOnClickListener(this);
        btn_close.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_inquiry :
                if(validateInquiry()){
                    Intent it = new Intent(getContext(), CeknasabahActivity.class);
                    it.putExtra("nasabahId", et_nasabahid.getText().toString().trim());
                    it.putExtra("produk", "kpr");
                    startActivity(it);
                    getActivity().finish();
                    dismiss();
                }
                break;
            case R.id.btn_close:
                dismiss();
                break;
        }
    }

    private boolean validateInquiry()
    {
        if(et_nasabahid.getText().toString().trim().isEmpty() || et_nasabahid.getText().toString().trim().equalsIgnoreCase("") || et_nasabahid.getText().toString().trim().length() == 0){
            AppUtil.notifwarning(getContext(), getDialog().getWindow().findViewById(android.R.id.content), "Nomor KTP / Nomor HP tidak boleh kosong");
            return false;
        }
        return true;
    }
}
