package com.application.bris.ikurma_nos_konsumer.page_aom.adapter.address;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.address;

import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class AddressAdapater extends RecyclerView.Adapter<AddressAdapater.PipelineViewHolder> {

    private Context context;
    private List<address> data;

    public AddressAdapater(Context context, List<address> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_address, parent, false);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PipelineViewHolder holder, int position) {
        holder.tv_provinsi.setText(data.get(position).getProvinsi());
        holder.tv_kota.setText(data.get(position).getKota());
        holder.tv_kecamatan.setText(data.get(position).getKecamatan());
        holder.tv_kelurahan.setText(data.get(position).getKelurahan());
        holder.tv_kodepos.setText(data.get(position).getKodepos());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_provinsi, tv_kota, tv_kecamatan, tv_kelurahan, tv_kodepos;
        private Button btn_pilih;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            tv_provinsi = (TextView) itemView.findViewById(R.id.tv_provinsi);
            tv_kota = (TextView) itemView.findViewById(R.id.tv_kota);
            tv_kecamatan = (TextView) itemView.findViewById(R.id.tv_kecamatan);
            tv_kelurahan = (TextView) itemView.findViewById(R.id.tv_kelurahan);
            tv_kodepos = (TextView) itemView.findViewById(R.id.tv_kodepos);
            btn_pilih = (Button) itemView.findViewById(R.id.btn_pilih);
        }
    }
}
