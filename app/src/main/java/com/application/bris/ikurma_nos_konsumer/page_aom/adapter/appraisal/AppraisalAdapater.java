package com.application.bris.ikurma_nos_konsumer.page_aom.adapter.appraisal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ApproveRejectListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.Appraisal;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class AppraisalAdapater extends RecyclerView.Adapter<AppraisalAdapater.HotprospekViewHolder> implements Filterable {

    private Context context;
    private List<Appraisal> data;
    private List<Appraisal> datafiltered;
    private ApproveRejectListener approvedListener;

    public AppraisalAdapater(Context context, List<Appraisal> data, ApproveRejectListener approvedListener) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;
        this.approvedListener = approvedListener;
    }

    @NonNull
    @Override
    public HotprospekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ao_item_appraisal, parent, false);
        return new HotprospekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HotprospekViewHolder holder, final int position) {
        final Appraisal datas = datafiltered.get(position);
        Glide
                .with(context)
                .load(UriApi.Baseurl.URL+UriApi.foto.urlFile +datas.getFID_PHOTO())
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .into(holder.iv_foto);

        holder.tv_id_aplikasi.setText(String.valueOf(datas.getID_APLIKASI()));
        holder.tv_nama.setText(datas.getNAMA_DEBITUR_1());
        holder.tv_produk.setText(datas.getNAMA_PRODUK());
        holder.tv_plafond.setText(AppUtil.parseRupiah(String.valueOf(datas.getPLAFOND_INDUK())));
        holder.tv_tenor.setText(String.valueOf(datas.getJW())+" Bulan");
        holder.tv_tanggalentry.setText(AppUtil.parseTanggalGeneral(datas.getTANGGAL_ENTRY(), "ddMMyyyy", "dd-MM-yyyy"));
        holder.cv_approved_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                approvedListener.onApproveRejectSelect(datas.getFID_CIF_LAS(), datas.getID_APLIKASI());
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
                    List<Appraisal> filteredList = new ArrayList<>();
                    for (Appraisal row : data){
                        if(row.getNAMA_DEBITUR_1().toLowerCase().contains(charString.toLowerCase()) || row.getNAMA_PRODUK().toLowerCase().contains(charString.toLowerCase())
                            || Long.toString(row.getPLAFOND_INDUK()).toLowerCase().contains(charString.toLowerCase()) || Integer.toString(row.getJW()).toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<Appraisal>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class HotprospekViewHolder extends RecyclerView.ViewHolder {
        private CardView cv_approved_front;
        private ImageView iv_foto;
        private TextView tv_id_aplikasi, tv_nama, tv_produk, tv_plafond, tv_tenor, tv_tanggalentry;

        public HotprospekViewHolder(View itemView) {
            super(itemView);
            cv_approved_front = (CardView) itemView.findViewById(R.id.cv_hotprospek_front);
            tv_id_aplikasi = (TextView) itemView.findViewById(R.id.tv_id_aplikasi);
            iv_foto = (ImageView) itemView.findViewById(R.id.iv_foto);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_produk = (TextView) itemView.findViewById(R.id.tv_produk);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond);
            tv_tenor = (TextView) itemView.findViewById(R.id.tv_tenor);
            tv_tanggalentry = (TextView) itemView.findViewById(R.id.tv_tanggal_entry);
        }
    }
}
