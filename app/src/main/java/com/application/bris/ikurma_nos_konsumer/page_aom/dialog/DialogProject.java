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
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListProject;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ProjectListener;

import java.util.ArrayList;
import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

public class DialogProject extends DialogFragment{
    private ImageView btn_close;
    private TextView tv_title;
    private ProdukAdapater produkAdapater;
    private RecyclerView rv_produk;
    private static ProjectListener productListener;
    private Realm realm;
    private List<MListProject> data = new ArrayList<>();

    public static final String TAG = "example_dialog";

    private static String fid_pihak_ketiga, pihak_ketiga;

    public static DialogProject display(FragmentManager fragmentManager, String mFidPihakKetiga, String mPihakKetiga, ProjectListener productListenerId) {
        fid_pihak_ketiga = mFidPihakKetiga;
        pihak_ketiga = mPihakKetiga;
        productListener = productListenerId;
        DialogProject dialogAddress = new DialogProject();
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
        initializeProduct(fid_pihak_ketiga);
    }

    public void customToolbar(){
        backgroundStatusBar();
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_title.setText("Pilih Project " + pihak_ketiga);
    }

    private void backgroundStatusBar(){
        Window window = getDialog().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void initializeProduct(String fid_pihak_ketiga){
//        if (title.equalsIgnoreCase("segmen")){
//            data = getDataSegmen();
//        }
//        else if(title.equalsIgnoreCase("produk") && segmen.equalsIgnoreCase("mikro")){
//            data = getDataProductMikro();
//        }
//        else if(title.equalsIgnoreCase("produk") && segmen.equalsIgnoreCase("konsumer")){
//            data = getDataProductKonsumer();
//        }
//
//        else if(title.equalsIgnoreCase("program") && product.equalsIgnoreCase("kpr")){
//            data = getDataProductKpr();
//        }
//
//        else if(title.equalsIgnoreCase("program") && product.equalsIgnoreCase("kmj")){
//            data = getDataProductKmj();
//        }
//
//        else if(title.equalsIgnoreCase("program") && product.equalsIgnoreCase("kmg")){
//            data = getDataProductKmg();
//        }

        data = getDataProject(fid_pihak_ketiga);

        produkAdapater = new ProdukAdapater(getContext(), data, pihak_ketiga, productListener);
        rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_produk.setItemAnimator(new DefaultItemAnimator());
        rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rv_produk.setAdapter(produkAdapater);
    }

    private List<MListProject> getDataProject(String fid_pihak_ketiga){
        RealmResults<MListProject> datas = realm.where(MListProject.class)
                                            .equalTo("FID_PIHAK_KETIGA", fid_pihak_ketiga, Case.INSENSITIVE)
                                            .distinct("NAMA")
                                            .findAll();
        return datas;
    }


    //CLASS ADAPTER PRODUCT
    public class ProdukAdapater extends RecyclerView.Adapter<ProdukAdapater.ProductViewHolder> {

        private Context context;
        private List<MListProject> data;
        private String title;
        private ProjectListener productListenerAdp;

        public ProdukAdapater(Context context, List<MListProject> data, String title, ProjectListener productListener) {
            this.context = context;
            this.data = data;
            this.title = title;
            this.productListenerAdp = productListener;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_product, parent, false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
            holder.tv_title.setText(title);
            holder.tv_product.setText(data.get(position).getNAMA());
//            switch (title.toLowerCase()){
//                case "segmen" :
//                    holder.tv_product.setText(data.get(position).getNama_segmen());
//                    break;
//                case "produk" :
//                    holder.tv_product.setText(data.get(position).getNama_produk());
//                    break;
//                case "program" :
//                    holder.tv_product.setText(data.get(position).getNama_gimmick());
//                    break;
//            }
            holder.rl_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productListenerAdp.onProjectSelect(title, data.get(position));
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
