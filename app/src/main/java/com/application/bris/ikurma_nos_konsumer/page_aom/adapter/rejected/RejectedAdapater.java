package com.application.bris.ikurma_nos_konsumer.page_aom.adapter.rejected;

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
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ApproveRejectListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.Rejected;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class RejectedAdapater extends RecyclerView.Adapter<RejectedAdapater.HotprospekViewHolder> implements Filterable {

    private Context context;
    private List<Rejected> data;
    private List<Rejected> datafiltered;
    private ApproveRejectListener rejectedListener;

    public RejectedAdapater(Context context, List<Rejected> data, ApproveRejectListener rejectedListener) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;
        this.rejectedListener = rejectedListener;
    }

    @NonNull
    @Override
    public HotprospekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ao_item_rejected, parent, false);
        return new HotprospekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HotprospekViewHolder holder, final int position) {
        final Rejected datas = datafiltered.get(position);
        Glide
                .with(context)
                .load(UriApi.Baseurl.URL+UriApi.foto.urlPhoto+datas.getfIDPHOTO())
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .into(holder.iv_foto);

        holder.tv_id_aplikasi.setText(String.valueOf(datas.getiDAPLIKASI()));
        holder.tv_nama.setText(datas.getnAMADEBITUR1());
        holder.tv_produk.setText(datas.getnAMAPRODUK());
        holder.tv_plafond.setText(AppUtil.parseRupiah(String.valueOf(datas.getpLAFONDINDUK())));
        holder.tv_tenor.setText(String.valueOf(datas.getjW())+" Bulan");
        holder.tv_tanggalentry.setText(AppUtil.parseTanggalGeneral(datas.gettANGGALENTRY(), "ddMMyyyy", "dd-MM-yyyy"));
        holder.tv_status.setText(datas.getsTATUSAPLIKASI());
        holder.cv_rejected_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rejectedListener.onApproveRejectSelect(datas.getfIDCIFLAS(), datas.getiDAPLIKASI());
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
                    List<Rejected> filteredList = new ArrayList<>();
                    for (Rejected row : data){
                        if(row.getnAMADEBITUR1().toLowerCase().contains(charString.toLowerCase()) || row.getnAMAPRODUK().toLowerCase().contains(charString.toLowerCase())
                            || Long.toString(row.getpLAFONDINDUK()).toLowerCase().contains(charString.toLowerCase()) || Integer.toString(row.getjW()).toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<Rejected>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class HotprospekViewHolder extends RecyclerView.ViewHolder {
        private CardView cv_rejected_front;
        private ImageView iv_foto;
        private TextView tv_id_aplikasi, tv_nama, tv_produk, tv_plafond, tv_tenor, tv_tanggalentry, tv_status;

        public HotprospekViewHolder(View itemView) {
            super(itemView);
            cv_rejected_front = (CardView) itemView.findViewById(R.id.cv_rejected_front);
            tv_id_aplikasi = (TextView) itemView.findViewById(R.id.tv_id_aplikasi);
            iv_foto = (ImageView) itemView.findViewById(R.id.iv_foto);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_produk = (TextView) itemView.findViewById(R.id.tv_produk);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond);
            tv_tenor = (TextView) itemView.findViewById(R.id.tv_tenor);
            tv_tanggalentry = (TextView) itemView.findViewById(R.id.tv_tanggal_entry);
            tv_status = (TextView) itemView.findViewById(R.id.tv_do);
        }
    }
}
