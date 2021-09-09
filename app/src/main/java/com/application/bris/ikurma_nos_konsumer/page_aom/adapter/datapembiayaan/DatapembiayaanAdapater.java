package com.application.bris.ikurma_nos_konsumer.page_aom.adapter.datapembiayaan;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.datapembiayaan;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class DatapembiayaanAdapater extends RecyclerView.Adapter<DatapembiayaanAdapater.PipelineViewHolder>{

    private Context context;
    private List<datapembiayaan> data;

    public DatapembiayaanAdapater(Context context, List<datapembiayaan> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ao_item_historyfasilitas, parent, false);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PipelineViewHolder holder, final int position) {

        final datapembiayaan datas = data.get(position);

        holder.tv_namapemrakarsa.setText(datas.getNama_pemrakarsa());
        holder.tv_ukerpemrakarsa.setText(datas.getUker_pemrakarsa());
        holder.tv_idaplikasi.setText(String.valueOf(datas.getId_aplikasi()));
        holder.tv_produk.setText(datas.getTipe_produk());
        holder.tv_plafond.setText(AppUtil.parseRupiah(String.format("%.0f", datas.getPlafond())));
        holder.tv_tanggalentry.setText(AppUtil.parseTanggalGeneral(datas.getTanggal_entry(), "ddMMyyyy", "dd-MM-yyyy"));
        holder.tv_status.setText(datas.getStatus_aplikasi());


//        holder.tv_idaplikasi.setText(datas.getId_aplikasi());
//        holder.tv_produk.setText(datas.getTipe_produk());
//        holder.tv_plafond.setText(String.format("%.0f", datas.getPlafond()));
//        holder.tv_tanggalentry.setText(datas.getTanggal_entry());
//        holder.tv_status.setText(datas.getStatus_aplikasi());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_namapemrakarsa, tv_ukerpemrakarsa, tv_idaplikasi, tv_produk, tv_plafond, tv_tanggalentry, tv_status;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            tv_namapemrakarsa = (TextView) itemView.findViewById(R.id.tv_namapemrakarsa);
            tv_ukerpemrakarsa = (TextView) itemView.findViewById(R.id.tv_ukerpemrakarsa);
            tv_idaplikasi = (TextView) itemView.findViewById(R.id.tv_idaplikasi);
            tv_produk = (TextView)itemView.findViewById(R.id.tv_produk);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond);
            tv_tanggalentry = (TextView) itemView.findViewById(R.id.tv_tanggalentry);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
        }
    }
}
