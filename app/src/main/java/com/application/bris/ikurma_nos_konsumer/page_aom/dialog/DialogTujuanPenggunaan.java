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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.TujuanPenggunaanListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MTujuanPenggunaan;

import java.util.List;

public class DialogTujuanPenggunaan extends DialogFragment{
    private ImageView btn_close;
    private TextView tv_title;
    private DialogTujuanPenggunaan.ProdukAdapater produkAdapater;
    private RecyclerView rv_produk;
    private static List<?> data;
    private static TujuanPenggunaanListener listener;
    public static final String TAG = "example_dialog";
    public List<MTujuanPenggunaan> dataKeyvalue;

    private static String title;

    public static DialogTujuanPenggunaan display(FragmentManager fragmentManager, String titleId, List<?> mdata, TujuanPenggunaanListener mlistener) {
        title = titleId;
        data = (List<MTujuanPenggunaan>) mdata;
        listener = mlistener;
        DialogTujuanPenggunaan dialogAddress = new DialogTujuanPenggunaan();
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
        View view = inflater.inflate(R.layout.dialog_produk, container, false);
        btn_close = (ImageView) view.findViewById(R.id.btn_close);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        rv_produk = (RecyclerView) view.findViewById(R.id.rv_produk);
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
        dataKeyvalue = (List<MTujuanPenggunaan>) data;
        produkAdapater = new DialogTujuanPenggunaan.ProdukAdapater(getContext(), dataKeyvalue, title, listener);
        rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_produk.setItemAnimator(new DefaultItemAnimator());
        rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rv_produk.setAdapter(produkAdapater);
    }


    //CLASS ADAPTER PRODUCT
    public class ProdukAdapater extends RecyclerView.Adapter<ProdukAdapater.ProductViewHolder> {

        private Context context;
        private List<?> data;
        private String title;
        private TujuanPenggunaanListener listener;

        public ProdukAdapater(Context context, List<?> data, String title, TujuanPenggunaanListener listener) {
            this.context = context;
            this.data = (List<MTujuanPenggunaan>) data;
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
            final MTujuanPenggunaan datas  = (MTujuanPenggunaan) data.get(position);

            holder.tv_title.setVisibility(View.GONE);

            holder.tv_product.setText(datas.getDESC1());
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
            return data.size();
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

