package com.application.bris.ikurma_nos_konsumer.page_aom.adapter.hotprospek;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.HotprospekListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.hotprospek;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class HotprospekAdapater extends RecyclerView.Adapter<HotprospekAdapater.HotprospekViewHolder> implements Filterable {

    private Context context;
    private List<hotprospek> data;
    private List<hotprospek> datafiltered;
    private HotprospekListener hotprospekListener;

    public HotprospekAdapater(Context context, List<hotprospek> data, HotprospekListener hotprospekListener) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;
        this.hotprospekListener = hotprospekListener;
    }

    @NonNull
    @Override
    public HotprospekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_sms, parent, false);
        return new HotprospekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HotprospekViewHolder holder, final int position) {
        final hotprospek datas = datafiltered.get(position);


        holder.tv_isi.setText(String.valueOf(datas.getNama_debitur_1()));
        holder.tv_sender.setText(datas.getStatus_aplikasi());
        holder.tv_tanggal.setText(datas.getTanggal_entry());

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
                    List<hotprospek> filteredList = new ArrayList<>();
                    for (hotprospek row : data){
                        if(row.getStatus_aplikasi().toLowerCase().contains(charString.toLowerCase()) || row.getNama_debitur_1().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<hotprospek>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class HotprospekViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_isi,tv_sender,tv_tanggal;

        public HotprospekViewHolder(View itemView) {
            super(itemView);

            tv_isi = (TextView) itemView.findViewById(R.id.tv_isi_sms);
            tv_sender = (TextView) itemView.findViewById(R.id.tv_sender);
            tv_tanggal = (TextView) itemView.findViewById(R.id.tv_tanggal);

        }
    }
}
