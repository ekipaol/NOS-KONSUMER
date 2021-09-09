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
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.LoadDataInputPipelineKPRListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListBidangPekerjaan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListJenisKPR;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListPihakKetiga;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MTujuanPenggunaan;

import java.util.List;
import io.realm.Realm;

public class DialogInputDataPipelineKPR extends DialogFragment{
    private ImageView btn_close;
    private TextView tv_title;
    private PihakKetigaAdapater produkAdapaterPihakKetiga;;
    private TujuanAdapter produkAdapterTujuan;
    private BidangPekerjaanAdapter produkAdapterBidangPekerjaan;
    private JenisKPRAdapter produkAdapterJenisKPR;

    private RecyclerView rv_produk;
    private static List<?> dataPihakKetiga, dataTujuan, dataBidangPekerjaan, dataJenisKPR;
    private static LoadDataInputPipelineKPRListener listener;
    public static final String TAG = "example_dialog";
    public List<MListPihakKetiga> dataKeyValuePihakKetiga;
    public List<MTujuanPenggunaan> dataKeyValueTujuan;
    public List<MListBidangPekerjaan> dataKeyValueBidangPekerjaan;
    public List<MListJenisKPR> dataKeyValueJenisKPR;
    public static String dialog;
    private static Realm realm;

    private static String title;

    public static DialogInputDataPipelineKPR display(FragmentManager fragmentManager, String titleId, List<?> mdata, LoadDataInputPipelineKPRListener mlistener, String mdialog) {
        dialog = mdialog;
        title = titleId;
        listener = mlistener;

        if (dialog.equalsIgnoreCase("pihak ketiga")) {
            dataPihakKetiga = (List<MListPihakKetiga>) mdata;
        } else if (dialog.equalsIgnoreCase("tujuan")) {
            dataTujuan = (List<MTujuanPenggunaan>) mdata;
        } else if (dialog.equalsIgnoreCase("bidang pekerjaan")) {
            dataBidangPekerjaan = (List<MListBidangPekerjaan>) mdata;
        } else if (dialog.equalsIgnoreCase("jenis kpr")) {
            dataJenisKPR = (List<MListJenisKPR>) mdata;
        }

        DialogInputDataPipelineKPR dialogAddress = new DialogInputDataPipelineKPR();
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
        initializeProduct(dialog);
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

    public void initializeProduct(String dialog){

        if (dialog.equalsIgnoreCase("pihak ketiga")) {
            dataKeyValuePihakKetiga = (List<MListPihakKetiga>) dataPihakKetiga;

            produkAdapaterPihakKetiga = new PihakKetigaAdapater(getContext(), dataKeyValuePihakKetiga, title, listener);
            rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_produk.setItemAnimator(new DefaultItemAnimator());
            rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            rv_produk.setAdapter(produkAdapaterPihakKetiga);
        } else if (dialog.equalsIgnoreCase("tujuan")) {
            dataKeyValueTujuan = (List<MTujuanPenggunaan>) dataTujuan;

            produkAdapterTujuan = new TujuanAdapter(getContext(), dataKeyValueTujuan, title, listener);
            rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_produk.setItemAnimator(new DefaultItemAnimator());
            rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            rv_produk.setAdapter(produkAdapterTujuan);
        } else if (dialog.equalsIgnoreCase("bidang pekerjaan")) {
            dataKeyValueBidangPekerjaan = (List<MListBidangPekerjaan>) dataBidangPekerjaan;

            produkAdapterBidangPekerjaan = new BidangPekerjaanAdapter(getContext(), dataKeyValueBidangPekerjaan, title, listener);
            rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_produk.setItemAnimator(new DefaultItemAnimator());
            rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            rv_produk.setAdapter(produkAdapterBidangPekerjaan);
        } else if (dialog.equalsIgnoreCase("jenis kpr")) {
            dataKeyValueJenisKPR = (List<MListJenisKPR>) dataJenisKPR;

            produkAdapterJenisKPR = new JenisKPRAdapter(getContext(), dataKeyValueJenisKPR, title, listener);
            rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_produk.setItemAnimator(new DefaultItemAnimator());
            rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            rv_produk.setAdapter(produkAdapterJenisKPR);
        }
    }

    //CLASS ADAPTER PRODUCT
    public class PihakKetigaAdapater extends RecyclerView.Adapter<PihakKetigaAdapater.ProductViewHolder> {

        private Context context;
        private List<?> data;
        private String title;
        private LoadDataInputPipelineKPRListener listener;

        public PihakKetigaAdapater(Context context, List<?> data, String title, LoadDataInputPipelineKPRListener listener) {
            this.context = context;
            this.data = (List<MListPihakKetiga>) data;
            this.title = title;
            this.listener = listener;
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
            final MListPihakKetiga datas  = (MListPihakKetiga) data.get(position);

            holder.tv_title.setVisibility(View.GONE);

            holder.tv_product.setText(datas.getNAMA());
            holder.rl_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelectPihakKetiga(title, datas);
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

    public class TujuanAdapter extends RecyclerView.Adapter<TujuanAdapter.ProductViewHolder> {

        private Context context;
        private List<?> data;
        private String title;
        private LoadDataInputPipelineKPRListener listener;

        public TujuanAdapter(Context context, List<?> data, String title, LoadDataInputPipelineKPRListener listener) {
            this.context = context;
            this.data = (List<MTujuanPenggunaan>) data;
            this.title = title;
            this.listener = listener;
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
            final MTujuanPenggunaan datas  = (MTujuanPenggunaan) data.get(position);

            holder.tv_title.setVisibility(View.GONE);

            holder.tv_product.setText(datas.getDESC1());
            holder.rl_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelectTujuan(title, datas);
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

    public class BidangPekerjaanAdapter extends RecyclerView.Adapter<BidangPekerjaanAdapter.ProductViewHolder> {

        private Context context;
        private List<?> data;
        private String title;
        private LoadDataInputPipelineKPRListener listener;

        public BidangPekerjaanAdapter(Context context, List<?> data, String title, LoadDataInputPipelineKPRListener listener) {
            this.context = context;
            this.data = (List<MListBidangPekerjaan>) data;
            this.title = title;
            this.listener = listener;
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
            final MListBidangPekerjaan datas  = (MListBidangPekerjaan) data.get(position);

            holder.tv_title.setVisibility(View.GONE);

            holder.tv_product.setText(datas.getDESC2());
            holder.rl_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelectBidangPekerjaan(title, datas);
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

    public class JenisKPRAdapter extends RecyclerView.Adapter<JenisKPRAdapter.ProductViewHolder> {

        private Context context;
        private List<?> data;
        private String title;
        private LoadDataInputPipelineKPRListener listener;

        public JenisKPRAdapter(Context context, List<?> data, String title, LoadDataInputPipelineKPRListener listener) {
            this.context = context;
            this.data = (List<MListJenisKPR>) data;
            this.title = title;
            this.listener = listener;
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
            final MListJenisKPR datas  = (MListJenisKPR) data.get(position);

            holder.tv_title.setVisibility(View.GONE);

            holder.tv_product.setText(datas.getValue());
            holder.rl_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelectJenisKPR(title, datas);
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

