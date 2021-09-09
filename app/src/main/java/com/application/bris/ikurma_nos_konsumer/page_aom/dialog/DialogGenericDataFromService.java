package com.application.bris.ikurma_nos_konsumer.page_aom.dialog;


/**
 * Created by idong on 06/05/2019.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;

import java.util.ArrayList;
import java.util.List;

public class DialogGenericDataFromService extends DialogFragment  {
    private ImageView btn_close;
    private TextView tv_title;
    private GenericAdapter genericAdapter;
    private RecyclerView rv_generic;
    private static List<MGenericModel> data;
    private static GenericListenerOnSelect listener;
    public static final String TAG = "example_dialog";
    public List<MGenericModel> dataKeyvalue;
    private SearchView searchView;

    private static String title;

    public static DialogGenericDataFromService display(FragmentManager fragmentManager, String titleId, List<MGenericModel> mdata, GenericListenerOnSelect mlistener) {
        title = titleId;
        data =  mdata;
        listener = mlistener;
        DialogGenericDataFromService dialogAddress = new DialogGenericDataFromService();
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
            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide_Produk);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_generic, container, false);
        btn_close = (ImageView) view.findViewById(R.id.btn_close);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        rv_generic = (RecyclerView) view.findViewById(R.id.rv_generic);
        searchView = (SearchView) view.findViewById(R.id.searchView);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customToolbar();
        initializeProduct();
    }

    public void customToolbar(){
        backgroundStatusBar();
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_title.setTextSize(13);
        tv_title.setText("Pilih "+title);
    }

    private void backgroundStatusBar(){
        Window window = getDialog().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void initializeProduct(){
        dataKeyvalue =  data;
        genericAdapter = new GenericAdapter(getContext(), dataKeyvalue, title, listener);
        rv_generic.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_generic.setItemAnimator(new DefaultItemAnimator());
        rv_generic.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rv_generic.setAdapter(genericAdapter);

        if (dataKeyvalue.size() > 0)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    try {
                        genericAdapter.getFilter().filter(query);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    try {
                        genericAdapter.getFilter().filter(query);
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


    //CLASS ADAPTER PRODUCT
    public class GenericAdapter extends RecyclerView.Adapter<GenericAdapter.ProductViewHolder> implements Filterable{

        private Context context;
        private List<MGenericModel> data;
        private List<MGenericModel> datafiltered;
        private String title;
        private GenericListenerOnSelect listener;

        public GenericAdapter(Context context, List<MGenericModel> data, String title, GenericListenerOnSelect listener) {
            this.context = context;
            this.data =  data;
            this.datafiltered =  data;
            this.title = title;
            this.listener = listener;
        }

        @NonNull
        @Override
        public GenericAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_product, parent, false);
            return new GenericAdapter.ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
            final MGenericModel datas  = datafiltered.get(position);

            holder.tv_title.setVisibility(View.GONE);

            if(datas.getDESC()!=null){
                holder.tv_generic.setText(datas.getDESC());
            }
            else{
                holder.tv_generic.setText(datas.getNAMA());
            }

            holder.rl_generic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelect(title, datas);
                    dismiss();
                }
            });
        }



        @Override
        public int getItemCount() {
            return datafiltered==null?0:datafiltered.size();
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
                        List<MGenericModel> filteredList = new ArrayList<>();
                        for (MGenericModel row : data){
                            if(row.getDESC().toLowerCase().contains(charString.toLowerCase()) || row.getID().toLowerCase().contains(charString.toLowerCase())){
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
                    datafiltered = (ArrayList<MGenericModel>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
        }

        public class ProductViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_title, tv_generic;
            public RelativeLayout rl_generic;
            public ProductViewHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                tv_generic = (TextView) itemView.findViewById(R.id.tv_product);
                rl_generic = (RelativeLayout) itemView.findViewById(R.id.rl_product);
            }
        }
    }
}

