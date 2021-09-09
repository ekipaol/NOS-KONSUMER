package com.application.bris.ikurma_nos_konsumer.page_aom.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.general.searchAddress;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.PasarListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.pasar;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.KeyValue;
import com.application.bris.ikurma_nos_konsumer.util.NothingSelectedSpinnerAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogPasar extends DialogFragment implements View.OnClickListener {
    private ImageView btn_close;
    private TextView tv_title;
    private List<pasar> dataPasar;
    private DialogPasar.PasarAdapater pasarAdapater;
    private RecyclerView rv_address;
    private ImageView iv_cari;
    private RelativeLayout loading;
    private LinearLayout ll_emptydata;

    public static final String TAG = "example_dialog";

    private String[] typeAddress;
    private List<String> listTypeAddress;
    private Spinner sp_typeaddress;
    private ArrayAdapter adapterTypeAddress;
    private EditText et_keyword;

    private static PasarListener pasarListener;

    private ApiClientAdapter apiClientAdapter;



    public static DialogPasar display(FragmentManager fragmentManager, PasarListener pasarListenerId) {
        pasarListener = pasarListenerId;
        DialogPasar dialogAddress = new DialogPasar();
        dialogAddress.show(fragmentManager, TAG);
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
        View view = inflater.inflate(R.layout.dialog_address, container, false);
        btn_close = (ImageView) view.findViewById(R.id.btn_close);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        rv_address = (RecyclerView) view.findViewById(R.id.rv_address);
        iv_cari = (ImageView) view.findViewById(R.id.iv_cari);
        sp_typeaddress = (Spinner) view.findViewById(R.id.sp_typesearch);
        et_keyword = (EditText) view.findViewById(R.id.et_keyword);
        loading = (RelativeLayout) view.findViewById(R.id.progressbar_loading);
        ll_emptydata = (LinearLayout) view.findViewById(R.id.ll_emptydata);
        iv_cari.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customToolbar();
        initializeTypeSearch();
        apiClientAdapter = new ApiClientAdapter(getContext());
    }

    public void customToolbar(){
        backgroundStatusBar();
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_title.setText("Pilih pasar");
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
        typeAddress = getResources().getStringArray(R.array.type_pencarian_pasar);
        listTypeAddress = new ArrayList<>(Arrays.asList(typeAddress));
        adapterTypeAddress = new ArrayAdapter<String>(getContext(), R.layout.spinner_style, listTypeAddress);
        adapterTypeAddress.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_typeaddress.setAdapter(adapterTypeAddress);
        sp_typeaddress.setAdapter(new NothingSelectedSpinnerAdapter(adapterTypeAddress, R.layout.spinner_nothing_selected_typeaddresssearch, getContext()));
    }

//    public void initializeAddress(){
//        dataPasar = getData();
//        pasarAdapater = new PasarAdapater(getContext(), dataPasar, pasarListener);
//        rv_address.setLayoutManager(new LinearLayoutManager(getContext()));
//        rv_address.setItemAnimator(new DefaultItemAnimator());
//        rv_address.setAdapter(pasarAdapater);
//    }

    public void processSearch(){
        loading.setVisibility(View.VISIBLE);
        String val_typeSearch = KeyValue.getTypePasarSearch(sp_typeaddress.getSelectedItem().toString().trim());
        String val_keyword = et_keyword.getText().toString().trim().toUpperCase();
        searchAddress req = new searchAddress(val_typeSearch, val_keyword);
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().searchPasar(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().toString();
                            if (listDataString.equalsIgnoreCase("[]")){
                                ll_emptydata.setVisibility(View.VISIBLE);
                                rv_address.setVisibility(View.GONE);
                            }
                            else{
                                rv_address.setVisibility(View.VISIBLE);
                                ll_emptydata.setVisibility(View.GONE);
                                Gson gson = new Gson();
                                Type type = new TypeToken<List<pasar>>() {}.getType();
                                dataPasar = gson.fromJson(listDataString, type);
                                if(dataPasar != null){
                                    pasarAdapater = new PasarAdapater(getContext(), dataPasar, pasarListener);
                                    pasarAdapater.notifyDataSetChanged();
                                    rv_address.setLayoutManager(new LinearLayoutManager(getContext()));
                                    rv_address.setItemAnimator(new DefaultItemAnimator());
                                    rv_address.setAdapter(pasarAdapater);
                                }
                            }
                        }
                        else {
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
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getDialog().findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }

//    private List<pasar> getData(){
//        List<pasar> data = new ArrayList<>();
//        GlobalData.sampleAddress(getContext(), data);
//        return data;
//    }

    private boolean validateSearch(){
        if (sp_typeaddress.getSelectedItem() == null){
            AppUtil.notifwarning(getContext(), getDialog().findViewById(android.R.id.content), "Silahkan pilih jenis pencarian");
            return false;
        }
        else if(et_keyword.getText().toString().trim().isEmpty() || et_keyword.getText().toString().trim().equalsIgnoreCase("") || et_keyword.getText().toString().trim().length() == 0){
            AppUtil.notifwarning(getContext(), getDialog().findViewById(android.R.id.content), "Silahkan masukkan keyword");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_cari:
                if (validateSearch())
                    processSearch();
                break;
        }
    }


    public class PasarAdapater extends RecyclerView.Adapter<PasarAdapater.AddressViewHolder> {

        private Context context;
        private List<pasar> data;
        private PasarListener addressListenerAdp;

        public PasarAdapater(Context context, List<pasar> data, PasarListener addressListener) {
            this.context = context;
            this.data = data;
            addressListenerAdp = addressListener;
        }

        @NonNull
        @Override
        public PasarAdapater.AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_pasar, parent, false);
            return new PasarAdapater.AddressViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AddressViewHolder holder, final int position) {
            holder.tv_provinsi.setText(data.get(position).getPROVINSI());
            holder.tv_kota.setText(data.get(position).getKOTA_KAB());
            holder.tv_nama.setText(data.get(position).getNAMA_PASAR());
            holder.tv_alamat.setText(data.get(position).getALAMAT_PASAR());
            holder.btn_pilih.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addressListenerAdp.onPasarSelect(data.get(position));
                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class AddressViewHolder  extends RecyclerView.ViewHolder{
            private TextView tv_provinsi, tv_kota, tv_nama, tv_alamat;
            private Button btn_pilih;
            public AddressViewHolder(View itemView) {
                super(itemView);
                tv_provinsi = (TextView) itemView.findViewById(R.id.tv_provinsi);
                tv_kota = (TextView) itemView.findViewById(R.id.tv_kota);
                tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
                tv_alamat = (TextView) itemView.findViewById(R.id.tv_alamat);
                btn_pilih = (Button) itemView.findViewById(R.id.btn_pilih);
            }
        }
    }
}