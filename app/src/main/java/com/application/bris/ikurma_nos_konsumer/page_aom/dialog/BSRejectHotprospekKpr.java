package com.application.bris.ikurma_nos_konsumer.page_aom.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.rejectHotprospek;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.HotprospekActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.HotprospekDetailKprActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BSRejectHotprospekKpr extends BottomSheetDialogFragment implements View.OnClickListener, ConfirmListener {

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.sp_opsireject)
    Spinner sp_opsireject;
    @BindView(R.id.et_catatanreject)
    EditText et_catatanreject;
    @BindView(R.id.btn_sendnow)
    Button btn_sendnow;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    private List listOpsiReject = new ArrayList<>();
    private ArrayAdapter adapterOpsiReject;
    private static Context context;

    public static BSRejectHotprospekKpr display(FragmentManager fragmentManager, Context mcontex){
        context = mcontex;
        BSRejectHotprospekKpr BSCekNasabah = new BSRejectHotprospekKpr();
        BSCekNasabah.show(fragmentManager, "Open Reject Hotprospek");
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
        View v = inflater.inflate(R.layout.bottomsheet_rejecthotprospek, container, false);
        ButterKnife.bind(this, v);
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());
        loadOpsiReject();
        btn_sendnow.setOnClickListener(this);

        return v;
    }

    private void loadOpsiReject() {
        String[] dataOpsi = getResources().getStringArray(R.array.opsi_reject);
        List<String> dataOpsiList = new ArrayList<>(Arrays.asList(dataOpsi));
        adapterOpsiReject = new ArrayAdapter<String>(context, R.layout.spinner_style_lefttext, dataOpsiList);
        adapterOpsiReject.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_opsireject.setAdapter(adapterOpsiReject);
    }

    public void sendReject(){
        loading.setVisibility(View.VISIBLE);
        String keterangan = sp_opsireject.getSelectedItem().toString().trim()+" : "+et_catatanreject.getText().toString().trim();
        rejectHotprospek req = new rejectHotprospek(HotprospekDetailKprActivity.idAplikasi, String.valueOf(appPreferences.getUid()), keterangan);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().rejectHotprospekKpr(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            CustomDialog.DialogSuccess(getContext(), "Success!", "ID Aplikasi "+String.valueOf(HotprospekDetailKprActivity.idAplikasi)+" Sukses ditolak", BSRejectHotprospekKpr.this);
                        }
                        else{
                            AppUtil.notiferror(getContext(), getDialog().findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(getContext(), getDialog().findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getDialog().findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sendnow :
                if (validateForm())
                    sendReject();
                break;
        }
    }

    public boolean validateForm(){
        if(et_catatanreject.getText().toString().trim().isEmpty() || et_catatanreject.getText().toString().trim().equalsIgnoreCase("")){
            AppUtil.notifwarning(getContext(), getDialog().findViewById(android.R.id.content), "Keterangan tolak harus diisi");
            return false;
        }
        return true;
    }

    @Override
    public void success(boolean val) {
        if (val){
            dismiss();
            Intent intent = new Intent(context, HotprospekActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
    }

    @Override
    public void confirm(boolean val) {

    }
}