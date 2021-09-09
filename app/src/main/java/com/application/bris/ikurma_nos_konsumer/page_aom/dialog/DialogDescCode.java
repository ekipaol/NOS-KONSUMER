package com.application.bris.ikurma_nos_konsumer.page_aom.dialog;

/**
 * Created by idong on 06/05/2019.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.DescCodeListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.CodeDesc;

import java.util.ArrayList;
import java.util.List;

public class DialogDescCode extends DialogFragment{
    private ImageView btn_close;
    private TextView tv_title;
    private DialogDescCode.ProdukAdapater produkAdapater;
    private RecyclerView rv_produk;
    private static List<?> data;
    private static DescCodeListener listener;
    public static final String TAG = "example_dialog";
    public List<CodeDesc> dataKeyvalue;
    private SearchView searchView;

    private static String title;

    public static DialogDescCode display(FragmentManager fragmentManager, String titleId, List<?> mdata, DescCodeListener mlistener) {
        title = titleId;
        data = (List<CodeDesc>) mdata;
        listener = mlistener;
        DialogDescCode dialogAddress = new DialogDescCode();
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
        View view = inflater.inflate(R.layout.dialog_desccode, container, false);
        btn_close = (ImageView) view.findViewById(R.id.btn_close);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        rv_produk = (RecyclerView) view.findViewById(R.id.rv_produk);
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
        dataKeyvalue = (List<CodeDesc>) data;
        produkAdapater = new DialogDescCode.ProdukAdapater(getContext(), dataKeyvalue, title, listener);
        rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_produk.setItemAnimator(new DefaultItemAnimator());
        rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rv_produk.setAdapter(produkAdapater);

        if (dataKeyvalue.size() > 0)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    try {
                        produkAdapater.getFilter().filter(query);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    try {
                        produkAdapater.getFilter().filter(query);
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
    public class ProdukAdapater extends RecyclerView.Adapter<ProdukAdapater.ProductViewHolder> implements Filterable {

        private Context context;
        private List<CodeDesc> data;
        private List<CodeDesc> datafiltered;
        private String title;
        private DescCodeListener listener;

        public ProdukAdapater(Context context, List<CodeDesc> data, String title, DescCodeListener listener) {
            this.context = context;
            this.data = data;
            this.datafiltered = data;
            this.title = title;
            this.listener = listener;
        }

        @NonNull
        @Override
        public ProdukAdapater.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_product, parent, false);
            return new ProdukAdapater.ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
            final CodeDesc datas  = datafiltered.get(position);

            holder.tv_title.setVisibility(View.GONE);

            holder.tv_product.setText(datas.getDesc2());
            holder.rl_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelect(title, datas);
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
                        List<CodeDesc> filteredList = new ArrayList<>();
                        for (CodeDesc row : data){
                            if(row.getDesc1().toLowerCase().contains(charString.toLowerCase()) || row.getDesc2().toLowerCase().contains(charString.toLowerCase())){
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
                    datafiltered = (ArrayList<CodeDesc>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
        }

        public class ProductViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_title, tv_product;
            public RelativeLayout rl_product;
            public ProductViewHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                tv_product = (TextView) itemView.findViewById(R.id.tv_product);
                rl_product = (RelativeLayout) itemView.findViewById(R.id.rl_product);
            }
        }
    }
}
