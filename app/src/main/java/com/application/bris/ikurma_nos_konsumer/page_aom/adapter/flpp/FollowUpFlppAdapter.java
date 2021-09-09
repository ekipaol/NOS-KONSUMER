package com.application.bris.ikurma_nos_konsumer.page_aom.adapter.flpp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.Flpp;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.flpp.DetailFollowUpFlppActivity;

import java.util.ArrayList;
import java.util.List;

public class FollowUpFlppAdapter extends RecyclerView.Adapter<FollowUpFlppAdapter.PipelineViewHolder> implements Filterable {

    private Context context;
    private List<Flpp> data;
    private List<Flpp> datafiltered;

    public FollowUpFlppAdapter(Context context, List<Flpp> data) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;
    }

    @NonNull
    @Override
    public FollowUpFlppAdapter.PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ao_item_followup_flpp, parent, false);
        return new FollowUpFlppAdapter.PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FollowUpFlppAdapter.PipelineViewHolder holder, final int position) {
        final Flpp datas = datafiltered.get(position);


        holder.tv_nama.setText(datas.getNamaDebitur());
        holder.tv_pengembang.setText(datas.getNamaPengembang());
        holder.tv_perumahan.setText(datas.getNamaPerumahan());
        holder.tv_provinsi.setText(datas.getPROVINSI());
        holder.tv_kota.setText(datas.getKOTA());
        holder.tv_kecamatan.setText(datas.getKECAMATAN());

        if(datas.getKECAMATAN().equalsIgnoreCase(datas.getKELURAHAN())){
            holder.tv_kelurahan.setText("-");
        }
        else{
            holder.tv_kelurahan.setText(datas.getKELURAHAN());
        }

        holder.btn_process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailFollowUpFlppActivity.class);
                intent.putExtra("dataFollowUp",datas);
                context.startActivity(intent);
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
                    List<Flpp> filteredList = new ArrayList<>();
                    for (Flpp row : data){
                        if(row.getNamaDebitur().toLowerCase().contains(charString.toLowerCase()) || row.getKELURAHAN().toLowerCase().contains(charString.toLowerCase())|| row.getPROVINSI().toLowerCase().contains(charString.toLowerCase())|| row.getNamaPengembang().toLowerCase().contains(charString.toLowerCase()) || row.getKECAMATAN().toLowerCase().contains(charString.toLowerCase()) || row.getKOTA().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<Flpp>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_nama, tv_pengembang, tv_perumahan, tv_provinsi, tv_kota,tv_kecamatan,tv_kelurahan;
        private Button btn_process;

        public PipelineViewHolder(View itemView) {
            super(itemView);

            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama_nasabah);
            tv_pengembang = (TextView) itemView.findViewById(R.id.tv_nama_pengembang);
            tv_perumahan = (TextView) itemView.findViewById(R.id.tv_nama_perumahan);
            tv_provinsi = (TextView) itemView.findViewById(R.id.tv_provinsi);
            tv_kota = (TextView) itemView.findViewById(R.id.tv_kota);
            tv_kecamatan = (TextView) itemView.findViewById(R.id.tv_kecamatan);
            tv_kelurahan = (TextView) itemView.findViewById(R.id.tv_kelurahan);
            btn_process = (Button) itemView.findViewById(R.id.bt_detil_followup_flpp);
        }
    }
}
