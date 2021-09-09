package com.application.bris.ikurma_nos_konsumer.page_aom.dialog;

/**
 * Created by idong on 06/05/2019.
 */

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.SaveAgunanListener;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NothingSelectedSpinnerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DialogSaveAgunan extends DialogFragment implements View.OnClickListener {
    private ImageView btn_close;
    private TextView tv_title;

    public static final String TAG = "example_dialog";

    private String[] typeAddress;
    private List<String> listTypeAddress;
    private Spinner sp_typeaddress;
    private Button btn_save;
    private ArrayAdapter adapterTypeAddress;

    private static SaveAgunanListener addressListener;
    private static int idMarketable;



    public static DialogSaveAgunan display(FragmentManager fragmentManager, SaveAgunanListener addressListenerId, int marketable) {
        addressListener = addressListenerId;
        DialogSaveAgunan dialogAddress = new DialogSaveAgunan();
        dialogAddress.show(fragmentManager, TAG);
        idMarketable=marketable;
        return dialogAddress;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_save_agunan, container, false);
        btn_close = (ImageView) view.findViewById(R.id.btn_close);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        sp_typeaddress = (Spinner) view.findViewById(R.id.sp_rekomendasi);
        btn_save = (Button) view.findViewById(R.id.btn_save);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customToolbar();
        initializeTypeSearch();
    }

    public void customToolbar(){
        backgroundStatusBar();
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_title.setText("Hasil Rekomendasi");
        btn_save.setOnClickListener(this);
    }

    private void backgroundStatusBar(){
        Window window = getDialog().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void initializeTypeSearch(){
        typeAddress = getResources().getStringArray(R.array.rekomendasi_penilai);
        listTypeAddress = new ArrayList<>(Arrays.asList(typeAddress));
        adapterTypeAddress = new ArrayAdapter<String>(getContext(), R.layout.spinner_style, listTypeAddress);
        adapterTypeAddress.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_typeaddress.setAdapter(adapterTypeAddress);
        sp_typeaddress.setAdapter(new NothingSelectedSpinnerAdapter(adapterTypeAddress, R.layout.spinner_nothing_selected_typeaddresssearch, getContext()));

        sp_typeaddress.setSelection(idMarketable);
        sp_typeaddress.setEnabled(false);

    }

    private boolean validateSearch(){
        if (sp_typeaddress.getSelectedItem() == null){
            AppUtil.notifwarning(getContext(), getDialog().findViewById(android.R.id.content), "Silahkan pilih rekomendasi");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:
                if (validateSearch()) {
                    int rekomendasi = (sp_typeaddress.getSelectedItemPosition());
                    addressListener.onSelectMenuAgunan(rekomendasi, sp_typeaddress.getSelectedItem().toString().trim());
                    dismiss();
                }
                break;
        }
    }
}