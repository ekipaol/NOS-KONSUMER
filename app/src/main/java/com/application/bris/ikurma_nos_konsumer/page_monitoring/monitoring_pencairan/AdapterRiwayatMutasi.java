package com.application.bris.ikurma_nos_konsumer.page_monitoring.monitoring_pencairan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.model.monitoring.RiwayatMutasi;

import java.util.ArrayList;
import java.util.List;

public class AdapterRiwayatMutasi extends RecyclerView.Adapter<AdapterRiwayatMutasi.HotprospekViewHolder> implements Filterable {

    private Context context;
    private List<RiwayatMutasi> data;
    private List<RiwayatMutasi> datafiltered;

    public AdapterRiwayatMutasi(Context context, List<RiwayatMutasi> data) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;

    }

    @NonNull
    @Override
    public AdapterRiwayatMutasi.HotprospekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_riwayat_mutasi, parent, false);
        return new AdapterRiwayatMutasi.HotprospekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterRiwayatMutasi.HotprospekViewHolder holder, final int position) {
        final RiwayatMutasi datas = datafiltered.get(position);


            holder.tv_keterangan.setText(datas.getKeterangan());
            if(datas.getKredit().isEmpty()){
                holder.tv_nilai.setText("Rp."+(datas.getDebit()));
            }
            else{
                holder.tv_nilai.setText("Rp."+(datas.getKredit()));
            }

        if(datas.getDebit().equalsIgnoreCase("")){
                holder.tv_status.setText("Kredit");
                holder.tv_status.setBackgroundColor(context.getResources().getColor(R.color.colorGreenSoft));
            }
            else{
                holder.tv_status.setText("Debit");
                holder.tv_status.setBackgroundColor(context.getResources().getColor(R.color.red_btn_bg_color));
            }

            holder.tv_referensi.setText((datas.getNo_referensi()));
            holder.tv_tanggal_posting.setText(datas.getTgl_posting());





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
                    List<RiwayatMutasi> filteredList = new ArrayList<>();
                    for (RiwayatMutasi row : data){
                        if(row.getNo_referensi().toLowerCase().contains(charString.toLowerCase()) || row.getTgl_posting().toLowerCase().contains(charString.toLowerCase())
                        ){
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
                datafiltered = (ArrayList<RiwayatMutasi>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class HotprospekViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_keterangan, tv_status, tv_tanggal_posting, tv_referensi, tv_nilai;

        public HotprospekViewHolder(View itemView) {
            super(itemView);
            tv_keterangan = (TextView) itemView.findViewById(R.id.tv_keterangan_mutasi);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status_debet_kredit);
            tv_tanggal_posting = (TextView) itemView.findViewById(R.id.tv_tanggal_posting);
            tv_referensi = (TextView) itemView.findViewById(R.id.tv_referensi);
            tv_nilai = (TextView) itemView.findViewById(R.id.tv_nilai_debet_kredit);


        }
    }
}