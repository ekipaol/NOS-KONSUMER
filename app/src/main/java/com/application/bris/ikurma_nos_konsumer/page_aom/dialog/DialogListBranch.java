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
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.DataSearchOjk;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.model.ikurma_branch.DataBranchIkurma;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DropdownOJK;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ListBranchListener;
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

public class DialogListBranch extends DialogFragment implements View.OnClickListener{
    private ImageView btn_close;
    private TextView tv_title;
    private List<DataBranchIkurma> data;
    private ListBranchAdapter listBranchAdapter;
    private RecyclerView rv_list_branch;
    private ImageView iv_cari;
    private RelativeLayout loading;
    private LinearLayout ll_emptydata;
    private SearchView searchView;
    private Realm realm;

    public static final String TAG = "example_dialog";

    private EditText et_keyword;

    private static ListBranchListener listBranchListener;
    private ApiClientAdapter apiClientAdapter;



    public static DialogListBranch display(FragmentManager fragmentManager, ListBranchListener mBranchListener) {
        listBranchListener = mBranchListener;
        DialogListBranch dialogAddress = new DialogListBranch();
        dialogAddress.show(fragmentManager, TAG);
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
        rv_list_branch = (RecyclerView) view.findViewById(R.id.rv_list_sek_ekonomi);
        iv_cari = (ImageView) view.findViewById(R.id.iv_cari);
        et_keyword = (EditText) view.findViewById(R.id.et_keyword);
        loading = (RelativeLayout) view.findViewById(R.id.progressbar_loading);
        ll_emptydata = (LinearLayout) view.findViewById(R.id.ll_emptydata);
        searchView = (SearchView) view.findViewById(R.id.searchView);
        apiClientAdapter=new ApiClientAdapter(getContext(),true);
        iv_cari.setOnClickListener(this);

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customToolbar();
    }

    public void customToolbar(){
        backgroundStatusBar();
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_title.setText("Cari Cabang Pengusul");
    }

    private void backgroundStatusBar(){
        Window window = getDialog().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void processSearch(){
        loading.setVisibility(View.VISIBLE);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().getListBranch("B","100",et_keyword.getText().toString());
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().get("content").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<DataBranchIkurma>>() {}.getType();
                            data = gson.fromJson(listDataString, type);


                            if (data == null||data.size()<=0){
                                ll_emptydata.setVisibility(View.VISIBLE);
                                rv_list_branch.setVisibility(View.GONE);
                            }
                            else{
                                rv_list_branch.setVisibility(View.VISIBLE);
                                ll_emptydata.setVisibility(View.GONE);

                                listBranchAdapter = new ListBranchAdapter(getContext(), data, listBranchListener);
                                listBranchAdapter.notifyDataSetChanged();
                                rv_list_branch.setLayoutManager(new LinearLayoutManager(getContext()));
                                rv_list_branch.setItemAnimator(new DefaultItemAnimator());
                                rv_list_branch.setAdapter(listBranchAdapter);
                                searchView.setVisibility(View.VISIBLE);
                                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                    @Override
                                    public boolean onQueryTextSubmit(String query) {
                                        try {
                                            listBranchAdapter.getFilter().filter(query);
                                        }
                                        catch (Exception e){
                                            e.printStackTrace();
                                        }
                                        return false;
                                    }

                                    @Override
                                    public boolean onQueryTextChange(String query) {
                                        try {
                                            listBranchAdapter.getFilter().filter(query);
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
        if(et_keyword.getText().toString().trim().isEmpty() || et_keyword.getText().toString().trim().equalsIgnoreCase("") || et_keyword.getText().toString().trim().length() == 0){
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

    public class ListBranchAdapter extends RecyclerView.Adapter<ListBranchAdapter.ListBranchViewHolder> implements Filterable {

        private Context context;
        private List<DataBranchIkurma> data;
        private List<DataBranchIkurma> datafiltered;
        private ListBranchListener sektorEkonomiListener;

        public ListBranchAdapter(Context context, List<DataBranchIkurma> data, ListBranchListener msektorEkonomiListener) {
            this.context = context;
            this.data = data;
            this.datafiltered = data;
            sektorEkonomiListener = msektorEkonomiListener;
        }

        @NonNull
        @Override
        public ListBranchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_product, parent, false);
            return new ListBranchViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ListBranchViewHolder holder, final int position) {
            final DataBranchIkurma datas = datafiltered.get(position);
            holder.tv_title.setVisibility(View.GONE);
            holder.tv_generic.setText(datas.getBranch_name());
            holder.rl_generic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sektorEkonomiListener.onBranchSelect(datas);
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
                        List<DataBranchIkurma> filteredList = new ArrayList<>();
                        for (DataBranchIkurma row : data){
                            if(row.getBranch_name().toLowerCase().contains(charString.toLowerCase())){
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
                    datafiltered = (ArrayList<DataBranchIkurma>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
        }

        public class ListBranchViewHolder extends RecyclerView.ViewHolder{
            public TextView tv_title, tv_generic;
            public RelativeLayout rl_generic;
            public ListBranchViewHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                tv_generic = (TextView) itemView.findViewById(R.id.tv_product);
                rl_generic = (RelativeLayout) itemView.findViewById(R.id.rl_product);
            }
        }
    }
}
