package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.general;

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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.databinding.ItemListAplikasiGadaiBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataListAplikasi;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.DetilAplikasiActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class AdapterListAplikasi extends RecyclerView.Adapter<AdapterListAplikasi.MenuViewHolder> implements Filterable {
    private List<DataListAplikasi> data;
    private List<DataListAplikasi> datafiltered;
    private Context context;
    private ItemListAplikasiGadaiBinding binding;

    public AdapterListAplikasi(Context context,List< DataListAplikasi> mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = data;
    }

    @NonNull
    @Override
    public AdapterListAplikasi.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding= ItemListAplikasiGadaiBinding.inflate(layoutInflater,parent,false);
        View view = binding.getRoot();
        return new AdapterListAplikasi.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListAplikasi.MenuViewHolder holder, final int position) {
        final DataListAplikasi datas = datafiltered.get(position);

        holder.tvNama.setText(datas.getNama());
        holder.tvStatus.setText(datas.getStatusAplikasi());
        holder.tvTenor.setText(datas.getJangkaWaktu());
        holder.tvNoAplikasi.setText(datas.getApplicationNo());
        holder.tvPlafon.setText(AppUtil.parseRupiah(datas.getPlafond()));

        holder.cvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetilAplikasiActivity.class);
                intent.putExtra("idAplikasi",datas.getApplicationId());
                intent.putExtra("nama",datas.getNama());
                intent.putExtra("plafond",datas.getPlafond());
                intent.putExtra("noAplikasi",datas.getApplicationNo());
                intent.putExtra("status",datas.getStatusAplikasi());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        if(datafiltered==null){
            return 0;
        }
        else{
            return datafiltered.size();
        }
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
                    List<DataListAplikasi> filteredList = new ArrayList<>();
                    for (DataListAplikasi row : data){
                        if(row.getNama().toLowerCase().contains(charString.toLowerCase()) || row.getApplicationNo().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<DataListAplikasi>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama,tvStatus,tvNoAplikasi,tvProduk,tvPlafon,tvTenor;
        CardView cvData;

        public MenuViewHolder(View itemView) {
            super(itemView);

            tvNama=binding.tvNama;
            tvStatus=binding.tvStatus;
            tvNoAplikasi=binding.tvIdAplikasi;
            tvPlafon=binding.tvPlafond;
            tvTenor=binding.tvTenor;
            cvData=binding.cvHotprospekFront;
        }

    }

}