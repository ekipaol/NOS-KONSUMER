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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.database.pojo.ProductPojo;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ProductListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;

import java.util.ArrayList;
import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

public class DialogSelect extends DialogFragment{
    private ImageView btn_close;
    private TextView tv_title;
    private DialogSelect.ProdukAdapater produkAdapater;
    private RecyclerView rv_produk;
    private List<keyvalue> dataKeyvalue;
    private static ProductListener productListener;
    private Realm realm;
    private List<ProductPojo> data = new ArrayList<>();

    public static final String TAG = "example_dialog";

    private static String title;
    private static String segmen;
    private static String product;

    public static DialogSelect display(FragmentManager fragmentManager, String titleId, String segmenId, String productId, ProductListener productListenerId) {
        title = titleId;
        segmen = segmenId;
        product = productId;
        productListener = productListenerId;
        DialogSelect dialogAddress = new DialogSelect();
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
        if (title.equalsIgnoreCase("segmen")){
            data = getDataSegmen();
        }
        else if(title.equalsIgnoreCase("produk") && segmen.equalsIgnoreCase("mikro")){
            data = getDataProductMikro();
        }
        else if(title.equalsIgnoreCase("produk") && segmen.equalsIgnoreCase("konsumer")){
            data = getDataProductKonsumer();
        }

        else if(title.equalsIgnoreCase("program") && product.equalsIgnoreCase("kpr")){
            data = getDataProductKpr();
        }

        else if(title.equalsIgnoreCase("program") && product.equalsIgnoreCase("kmj")){
            data = getDataProductKmj();
        }

        else if(title.equalsIgnoreCase("program") && product.equalsIgnoreCase("kmg")){
            data = getDataProductKmg();
        }

        produkAdapater = new DialogSelect.ProdukAdapater(getContext(), data, title, productListener);
        rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_produk.setItemAnimator(new DefaultItemAnimator());
        rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rv_produk.setAdapter(produkAdapater);
    }

    private List<ProductPojo> getDataSegmen(){
        RealmResults<ProductPojo> datas = realm.where(ProductPojo.class).distinct("nama_segmen").findAll();
        return datas;
    }

    private List<ProductPojo> getDataProductMikro(){
        RealmResults<ProductPojo> datas = realm.where(ProductPojo.class)
                                            .equalTo("nama_segmen", "mikro", Case.INSENSITIVE)
                                            .distinct("nama_produk")
                                            .findAll();
        return datas;
    }

    private List<ProductPojo> getDataProductKonsumer(){
        RealmResults<ProductPojo> datas = realm.where(ProductPojo.class)
                                            .equalTo("nama_segmen", "konsumer", Case.INSENSITIVE)
                                            .distinct("nama_produk")
                                            .findAll();
        return datas;
    }

    private List<ProductPojo> getDataProductKpr(){
        RealmResults<ProductPojo> datas = realm.where(ProductPojo.class)
                                            .equalTo("nama_produk", "kpr", Case.INSENSITIVE)
                                            .distinct("nama_gimmick")
                                            .findAll();
        return datas;
    }

    private List<ProductPojo> getDataProductKmj(){
        RealmResults<ProductPojo> datas = realm.where(ProductPojo.class)
                                            .equalTo("nama_produk", "kmj", Case.INSENSITIVE)
                                            .distinct("nama_gimmick")
                                            .findAll();
        return datas;
    }

    private List<ProductPojo> getDataProductKmg(){
        RealmResults<ProductPojo> datas = realm.where(ProductPojo.class)
                                            .equalTo("nama_produk", "kmg", Case.INSENSITIVE)
                                            .distinct("nama_gimmick")
                                            .findAll();
        return datas;
    }


    //CLASS ADAPTER PRODUCT
    public class ProdukAdapater extends RecyclerView.Adapter<ProdukAdapater.ProductViewHolder> {

        private Context context;
        private List<ProductPojo> data;
        private String title;
        private ProductListener productListenerAdp;

        public ProdukAdapater(Context context, List<ProductPojo> data, String title, ProductListener productListener) {
            this.context = context;
            this.data = data;
            this.title = title;
            this.productListenerAdp = productListener;
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
            holder.tv_title.setText(title);
            switch (title.toLowerCase()){
                case "segmen" :
                    holder.tv_product.setText(data.get(position).getNama_segmen());
                    break;
                case "produk" :
                    holder.tv_product.setText(data.get(position).getNama_produk());
                    break;
                case "program" :
                    holder.tv_product.setText(data.get(position).getNama_gimmick());
                    break;
            }
            holder.rl_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productListenerAdp.onProductSelect(title, data.get(position));
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
