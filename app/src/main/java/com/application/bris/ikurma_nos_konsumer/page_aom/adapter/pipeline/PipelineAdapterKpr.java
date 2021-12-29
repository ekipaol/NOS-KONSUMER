package com.application.bris.ikurma_nos_konsumer.page_aom.adapter.pipeline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.PipelineListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.PipelineKpr;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PipelineAdapterKpr extends RecyclerView.Adapter<PipelineAdapterKpr.PipelineViewHolder> implements Filterable {

    private Context context;
    private List<PipelineKpr> data;
    private List<PipelineKpr> datafiltered;
    private PipelineListener pipelineListener;

    public PipelineAdapterKpr(Context context, List<PipelineKpr> data, PipelineListener pipelineListener) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;
        this.pipelineListener = pipelineListener;
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ao_item_pipeline, parent, false);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PipelineViewHolder holder, final int position) {
        final PipelineKpr datas = datafiltered.get(position);

        Glide
                .with(context)
                .load(UriApi.Baseurl.URL+UriApi.foto.urlFile +datas.getFid_photo())
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .into(holder.iv_foto);
        holder.tv_nama.setText(datas.getNama());
        holder.tv_produk.setText(datas.getNama_produk());
        holder.tv_plafond.setText(AppUtil.parseRupiah(Long.toString(datas.getPlafond())));
        holder.tv_tenor.setText(datas.getJw()+" Bulan");
        holder.tv_waktu.setText(datas.getTgl_input());
        holder.btn_process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pipelineListener.onPipelineSelect(datas.getId());
            }
        });
        holder.cv_pipeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pipelineListener.onPipelineSelect(datas.getId());
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
                    List<PipelineKpr> filteredList = new ArrayList<>();
                    for (PipelineKpr row : data){
                        if(row.getNama().toLowerCase().contains(charString.toLowerCase()) || row.getNama_produk().toLowerCase().contains(charString.toLowerCase())
                                || Long.toString(row.getPlafond()).toLowerCase().contains(charString.toLowerCase()) || Integer.toString(row.getJw()).toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<PipelineKpr>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private CardView cv_pipeline;
        private ImageView iv_foto;
        private TextView tv_nama, tv_produk, tv_plafond, tv_tenor, tv_waktu;
        private Button btn_process;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            cv_pipeline = (CardView) itemView.findViewById(R.id.cv_pipeline);
            iv_foto = (ImageView) itemView.findViewById(R.id.iv_foto);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_produk = (TextView) itemView.findViewById(R.id.tv_produk);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond);
            tv_tenor = (TextView) itemView.findViewById(R.id.tv_tenor);
            tv_waktu = (TextView) itemView.findViewById(R.id.tv_waktu);
            btn_process = (Button) itemView.findViewById(R.id.btn_proses);
        }
    }
}