package com.application.bris.ikurma_nos_konsumer.page_aom.dialog;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.AgunanListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogBottomAgunan extends BottomSheetDialogFragment implements View.OnClickListener{

    @BindView(R.id.ll_tanah_bangunan)
    LinearLayout ll_tanah_bangunan;
    @BindView(R.id.ll_tanah_kosong)
    LinearLayout ll_tanah_kosong;
    @BindView(R.id.btn_close)
    ImageView btn_close;

    private static AgunanListener agunanListener;

    public static DialogBottomAgunan display(FragmentManager fragmentManager, AgunanListener agunanListenerId){
        agunanListener = agunanListenerId;
        DialogBottomAgunan dialogBottomAgunan = new DialogBottomAgunan();
        dialogBottomAgunan.show(fragmentManager, "Open Menu AgunanTanahBangunan");
        return dialogBottomAgunan;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.bottomsheet_agunan, container, false);
        ButterKnife.bind(this, v);

        ll_tanah_bangunan.setOnClickListener(this);
        ll_tanah_kosong.setOnClickListener(this);
        btn_close.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_tanah_bangunan :
                agunanListener.onSelectMenuAgunan("AgunanTanahBangunan Tanah dan Bangunan");
                dismiss();
                break;
            case R.id.ll_tanah_kosong :
                agunanListener.onSelectMenuAgunan("AgunanTanahBangunan Tanah Kosong");
                dismiss();
                break;

            case R.id.btn_close:
                dismiss();
                break;
        }
    }
}
