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
import com.application.bris.ikurma_nos_konsumer.page_aom.model.HotprospekKpr;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class HotprospekAdapterKpr extends RecyclerView.Adapter<HotprospekAdapterKpr.HotprospekViewHolder> implements Filterable {

    private Context context;
    private List<HotprospekKpr> data;
    private List<HotprospekKpr> datafiltered;
    private HotprospekListener hotprospekListener;

    public HotprospekAdapterKpr(Context context, List<HotprospekKpr> data, HotprospekListener hotprospekListener) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;
        this.hotprospekListener = hotprospekListener;
    }

    @NonNull
    @Override
    public HotprospekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ao_item_hotprospek, parent, false);
        return new HotprospekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HotprospekViewHolder holder, final int position) {
        final HotprospekKpr datas = datafiltered.get(position);
        Glide
                .with(context)
                .load(UriApi.Baseurl.URL+UriApi.foto.urlFile +datas.getFid_photo())
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .into(holder.iv_foto);

        holder.tv_id_aplikasi.setText(String.valueOf(datas.getId_aplikasi()));
        holder.tv_nama.setText(datas.getNama_debitur_1());
        holder.tv_produk.setText(datas.getNama_produk());
        holder.tv_plafond.setText(AppUtil.parseRupiah(String.valueOf(datas.getPlafond_induk())));
        holder.tv_tenor.setText(String.valueOf(datas.getJw())+" Bulan");
        holder.tv_tanggalentry.setText(AppUtil.parseTanggalGeneral(datas.getTanggal_entry(), "ddMMyyyy", "dd-MM-yyyy"));
        holder.tv_status.setText(datas.getStatus_aplikasi());
        holder.cv_hotprospek_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotprospekListener.onHotprospekSelect(datas.getId_aplikasi());
            }
        });

        //ubah warna header kalau dia menunggu putusan
        if (datas.getId_st_aplikasi()==14){
            holder.tv_status.setBackgroundColor(context.getResources().getColor(R.color.colorGreenSoft));
        }
        else if (datas.getId_st_aplikasi()==-14||datas.getId_st_aplikasi()==-165){
            holder.tv_status.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryRed));
        }
        else{
            holder.tv_status.setBackgroundColor(context.getResources().getColor(R.color.colorPrimarySoft));
        }
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
                    List<HotprospekKpr> filteredList = new ArrayList<>();
                    for (HotprospekKpr row : data){
                        if(row.getNama_debitur_1().toLowerCase().contains(charString.toLowerCase()) || row.getNama_produk().toLowerCase().contains(charString.toLowerCase())
                                || Long.toString(row.getPlafond_induk()).toLowerCase().contains(charString.toLowerCase()) || Integer.toString(row.getJw()).toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<HotprospekKpr>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class HotprospekViewHolder extends RecyclerView.ViewHolder {
        private CardView cv_hotprospek_front;
        private ImageView iv_foto;
        private TextView tv_id_aplikasi, tv_nama, tv_produk, tv_plafond, tv_tenor, tv_tanggalentry, tv_status;

        public HotprospekViewHolder(View itemView) {
            super(itemView);
            cv_hotprospek_front = (CardView) itemView.findViewById(R.id.cv_hotprospek_front);
            iv_foto = (ImageView) itemView.findViewById(R.id.iv_foto);
            tv_id_aplikasi = (TextView) itemView.findViewById(R.id.tv_id_aplikasi);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_produk = (TextView) itemView.findViewById(R.id.tv_produk);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond);
            tv_tenor = (TextView) itemView.findViewById(R.id.tv_tenor);
            tv_tanggalentry = (TextView) itemView.findViewById(R.id.tv_tanggal_entry);
            tv_status = (TextView) itemView.findViewById(R.id.tv_do);
        }
    }
}