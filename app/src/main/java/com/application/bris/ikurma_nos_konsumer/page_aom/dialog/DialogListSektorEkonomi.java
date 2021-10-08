package com.application.bris.ikurma_nos_konsumer.page_aom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.application.bris.ikurma_nos_konsumer.databinding.DialogListKodeSektorEkonomiBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.g1_akad_dan_asesoir.field_ojk_bi.ActivityFieldOjkBI;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.SearchView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.general.searchListSektorEkonomi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.general.searchSektorEkonomi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.config.globaldata.GlobalData;
import com.application.bris.ikurma_nos_konsumer.listener.menu.OnSelectListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.SektorEkonomiListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.ListSektorEkonomi;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MsektorEkonomi;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogListSektorEkonomi extends DialogFragment implements View.OnClickListener, OnSelectListener{
    private static Object String;
    private static Object ActivityFieldOjkBI;
    private ImageView btn_close;
    private TextView tv_title;
    private List<MsektorEkonomi> data;
    private SektorEkonomiAdapter sektorEkonomiAdapter;
    private RecyclerView rv_sektorekonomi;
    private ImageView iv_cari;
    private RelativeLayout loading;
    private LinearLayout ll_emptydata;
    private RecyclerView rv_kategori;
    private View ll_kategori;
    private SearchView searchView;

    private BottomSheetBehavior bottomSheetBehavior;
    private Realm realm;
    private KategoriAdapter kategoriAdapter;

    public static final String TAG = "example_dialog";

    private String[] typeAddress;
    private List<String> listTypeAddress;

    private ArrayAdapter adapterTypeAddress;
    private TextView tv_titleBottomsheet;
    private EditText et_kategori;
    private EditText et_kriteria;
    private EditText et_keyword;

    private static ActivityFieldOjkBI activityFieldOjkBI;
    private static int idAplikasi;
    private static String ListKodeSekom;
    private List<ListSektorEkonomi> dataListKodeSektorEkonomi = null;
    private ApiClientAdapter apiClientAdapter;



    public static DialogListSektorEkonomi display(FragmentManager supportFragmentManager, int midAplikasi, String mlistkodeSekom, String dataListSektorEkonomi, ActivityFieldOjkBI activityFieldOjkBI) {
        ActivityFieldOjkBI = activityFieldOjkBI;
        idAplikasi = midAplikasi;
        ListKodeSekom = mlistkodeSekom;
        DialogListSektorEkonomi dialogAddress = new DialogListSektorEkonomi();
        dialogAddress.show(supportFragmentManager, TAG);
        return dialogAddress;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
        realm = Realm.getDefaultInstance();
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
        View view = inflater.inflate(R.layout.dialog_list_kode_sektor_ekonomi, container, false);
        btn_close = (ImageView) view.findViewById(R.id.btn_close);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        rv_sektorekonomi = (RecyclerView) view.findViewById(R.id.rv_sektorekonomi);
        iv_cari = (ImageView) view.findViewById(R.id.iv_cari);
        et_kategori = (EditText) view.findViewById(R.id.et_kategori);
        et_kriteria = (EditText) view.findViewById(R.id.et_kriteria);
        et_keyword = (EditText) view.findViewById(R.id.et_keyword);
        loading = (RelativeLayout) view.findViewById(R.id.progressbar_loading);
        ll_emptydata = (LinearLayout) view.findViewById(R.id.ll_emptydata);
        ll_kategori = view.findViewById(R.id.ll_kategori);
        rv_kategori = (RecyclerView) view.findViewById(R.id.rv_kategori);
        tv_titleBottomsheet = (TextView) view.findViewById(R.id.tv_titleBottomsheet);
        searchView = (SearchView) view.findViewById(R.id.searchView);
        et_kategori.setEnabled(false);
        et_kategori.setInputType(InputType.TYPE_NULL);
        et_kategori.setFocusable(false);
        et_kategori.setOnClickListener(this);

        et_kriteria.setInputType(InputType.TYPE_NULL);
        et_kriteria.setFocusable(false);
        et_kriteria.setOnClickListener(this);

        iv_cari.setOnClickListener(this);

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customToolbar();
        apiClientAdapter = new ApiClientAdapter(getContext());
        loadDataKategSekom();
    }

    public void customToolbar(){
        backgroundStatusBar();
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_title.setText("Pilih Sektor Ekonomi");
    }

    private void backgroundStatusBar(){
        Window window = getDialog().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void intializeKategori(String title){
        tv_titleBottomsheet.setText("Pilih "+title);
        bottomSheetBehavior = BottomSheetBehavior.from(ll_kategori);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        rv_kategori.setHasFixedSize(true);
        rv_kategori.setLayoutManager(new LinearLayoutManager(getContext()));
        kategoriAdapter = new KategoriAdapter(getContext(), "Kategori", getDataKategSekom(), this);
        rv_kategori.setAdapter(kategoriAdapter);
    }

//    public List<SektorEkonomiPojo> getDataKategSekom(){
//        RealmResults<SektorEkonomiPojo> datas = realm.where(SektorEkonomiPojo.class).findAll();
//        return datas;
//    }


    public void loadDataKategSekom()
    {
        loading.setVisibility(View.VISIBLE);
        searchListSektorEkonomi req = new searchListSektorEkonomi();
        req.setIdAplikasi(idAplikasi);
//        req.setJenisPenggunaan(jenisPenggunaan);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().getKategSektorEkonomiiByGroupKpr(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            String listDataString = response.body().getData().get("dtKategoriSektorEko").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<DialogListKodeSektorEkonomiBinding>>() {
                            }.getType();
                            dataListKodeSektorEkonomi = gson.fromJson(listDataString, type);
                            et_kategori.setEnabled(true);
                        } else {
                            AppUtil.showToastShort(getContext(), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.showToastShort(getContext(), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.showToastShort(getContext(), getString(R.string.txt_connection_failure));
            }
        });
    }

    public List<ListSektorEkonomi> getDataKategSekom(){
        return dataListKodeSektorEkonomi;
    }

    public void intializeKriteria(String title){
        tv_titleBottomsheet.setText("Pilih "+title);
        bottomSheetBehavior = BottomSheetBehavior.from(ll_kategori);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        rv_kategori.setHasFixedSize(true);
        rv_kategori.setLayoutManager(new LinearLayoutManager(getContext()));
        kategoriAdapter = new KategoriAdapter(getContext(), "Kriteria", getDataKriteriaSekom(), this);
        rv_kategori.setAdapter(kategoriAdapter);
    }

    public List<keyvalue> getDataKriteriaSekom(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.kriteriaSekom(getContext(), data);
        return  data;
    }

    public void processSearch(){
        loading.setVisibility(View.VISIBLE);
        searchSektorEkonomi req = new searchSektorEkonomi(et_kategori.getText().toString().trim(), et_kriteria.getText().toString().trim(), et_keyword.getText().toString().trim());
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().searcSektorEkonomiKpr(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().get("dtSektorEkoLBU").toString();
                            if (listDataString.equalsIgnoreCase("[]")){
                                ll_emptydata.setVisibility(View.VISIBLE);
                                rv_sektorekonomi.setVisibility(View.GONE);
                            }
                            else{
                                rv_sektorekonomi.setVisibility(View.VISIBLE);
                                ll_emptydata.setVisibility(View.GONE);
                                Gson gson = new Gson();
                                Type type = new TypeToken<List<MsektorEkonomi>>() {}.getType();
                                data = gson.fromJson(listDataString, type);
                                if(data != null){
                                    sektorEkonomiAdapter = new SektorEkonomiAdapter(getContext(), data, activityFieldOjkBI);
                                    sektorEkonomiAdapter.notifyDataSetChanged();
                                    rv_sektorekonomi.setLayoutManager(new LinearLayoutManager(getContext()));
                                    rv_sektorekonomi.setItemAnimator(new DefaultItemAnimator());
                                    rv_sektorekonomi.setAdapter(sektorEkonomiAdapter);

                                    if (data.size() > 0){
                                        searchView.setVisibility(View.VISIBLE);
                                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                            @Override
                                            public boolean onQueryTextSubmit(String query) {
                                                try {
                                                    sektorEkonomiAdapter.getFilter().filter(query);
                                                }
                                                catch (Exception e){
                                                    e.printStackTrace();
                                                }
                                                return false;
                                            }

                                            @Override
                                            public boolean onQueryTextChange(String query) {
                                                try {
                                                    sektorEkonomiAdapter.getFilter().filter(query);
                                                    return false;
                                                }
                                                catch (Exception e){
                                                    e.printStackTrace();
                                                    return false;
                                                }
                                            }
                                        });
                                    }
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
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getDialog().findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }


    private boolean validateSearch(){
        if(et_kategori.getText().toString().trim().isEmpty() || et_kategori.getText().toString().trim().equalsIgnoreCase("") || et_kategori.getText().toString().trim().length() == 0){
            AppUtil.notifwarning(getContext(), getDialog().findViewById(android.R.id.content), "Silahkan masukkan kategori");
            return false;
        }
        else if(et_kriteria.getText().toString().trim().isEmpty() || et_kriteria.getText().toString().trim().equalsIgnoreCase("") || et_kriteria.getText().toString().trim().length() == 0){
            AppUtil.notifwarning(getContext(), getDialog().findViewById(android.R.id.content), "Silahkan masukkan kriteria");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_kategori:
                intializeKategori("Kategori");
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.et_kriteria:
                intializeKriteria("Kriteria");
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.iv_cari:
                if (validateSearch())
                    processSearch();
                break;
        }
    }


    @Override
    public void onSelect(String title, String desc, String value) {
        if(title.equalsIgnoreCase("kategori")){
            et_kategori.setText(desc);
        }
        else {
            et_kriteria.setText(desc);
        }
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }


    public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.KategoriViewHolder>{
        private Context context;
        private String title;
        private List<?> data;
        private OnSelectListener listener;

        public KategoriAdapter(Context context, String title, List<?> data, OnSelectListener listener) {
            this.context = context;
            this.title = title;
            if (title.equalsIgnoreCase("kategori")){
                this.data = (List<ListSektorEkonomi>) data;
            }
            else{
                this.data = (List<keyvalue>) data;
            }

            this.listener = listener;
        }



        @NonNull
        @Override
        public KategoriAdapter.KategoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new KategoriViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_kategorisekom, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull KategoriAdapter.KategoriViewHolder holder, int position) {
            if (title.equalsIgnoreCase("kategori")){
                final ListSektorEkonomi datas  = (ListSektorEkonomi) data.get(position);
                holder.tv_kategori.setText(datas.getdESC3());

                holder.rl_kategori.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onSelect(title, datas.getdESC3(), "");
                    }
                });
            }
            else {
                final keyvalue datas = (keyvalue) data.get(position);
                if(datas.getName().equalsIgnoreCase("sektorEkonomiText")){
                    holder.tv_kategori.setText("Cari dengan teks");
                }
                else if(datas.getName().equalsIgnoreCase("sektorEkonomiSID")){
                    holder.tv_kategori.setText("Cari dengan kode");
                }
                else{
                    holder.tv_kategori.setText(datas.getName());
                }

                holder.rl_kategori.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onSelect(title, datas.getName(), datas.getValue());
                    }
                });
            }

        }

        @Override
        public int getItemCount() {
            return (data != null) ? data.size() : 0;
        }

        public class KategoriViewHolder extends RecyclerView.ViewHolder {
            private RelativeLayout rl_kategori;
            private TextView tv_kategori;
            public KategoriViewHolder(View itemView) {
                super(itemView);
                rl_kategori = (RelativeLayout) itemView.findViewById(R.id.rl_kategori);
                tv_kategori = (TextView) itemView.findViewById(R.id.tv_kategori);
            }
        }
    }



    public class SektorEkonomiAdapter extends RecyclerView.Adapter<SektorEkonomiAdapter.SektorEkonomiViewHolder> implements Filterable {

        private Context context;
        private List<MsektorEkonomi> data;
        private List<MsektorEkonomi> datafiltered;
        private SektorEkonomiListener sektorEkonomiListener;

        public SektorEkonomiAdapter(Context context, List<MsektorEkonomi> data, SektorEkonomiListener msektorEkonomiListener) {
            this.context = context;
            this.data = data;
            this.datafiltered = data;
            sektorEkonomiListener = msektorEkonomiListener;
        }

        @NonNull
        @Override
        public SektorEkonomiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_sektorekonomi, parent, false);
            return new SektorEkonomiViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SektorEkonomiViewHolder holder, final int position) {
            final MsektorEkonomi datas = datafiltered.get(position);
            holder.tv_sektorekonomi.setText(datas.getSektorEkonomiSID()+" - "+datas.getSektorEkonomiText());
            holder.btn_pilih.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sektorEkonomiListener.onSectorSelect(datas);
                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return datafiltered.size();
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    String charString = charSequence.toString();
                    if (charString.isEmpty()){
                        datafiltered = data;
                    } else {
                        List<MsektorEkonomi> filteredList = new ArrayList<>();
                        for (MsektorEkonomi row : data){
                            if(row.getSektorEkonomiSID().toLowerCase().contains(charString.toLowerCase()) || row.getSektorEkonomiText().toLowerCase().contains(charString.toLowerCase())){
                                filteredList.add(row);
                            }
                        }
                        datafiltered = filteredList;
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = datafiltered;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                    datafiltered = (ArrayList<MsektorEkonomi>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
        }

        public class SektorEkonomiViewHolder extends RecyclerView.ViewHolder{
            private TextView tv_sektorekonomi;
            private Button btn_pilih;
            public SektorEkonomiViewHolder(View itemView) {
                super(itemView);
                tv_sektorekonomi = (TextView) itemView.findViewById(R.id.tv_sektorekonomi);
                btn_pilih = (Button) itemView.findViewById(R.id.btn_pilih);
            }
        }
    }
}
