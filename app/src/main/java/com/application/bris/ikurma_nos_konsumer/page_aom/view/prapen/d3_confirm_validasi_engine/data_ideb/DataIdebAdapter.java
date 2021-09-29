package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_ideb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoDataIdebActivityBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoItemDataHutangBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoItemDataIdebBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataHutang;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataIdeb;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DataIdebAdapter extends RecyclerView.Adapter<DataIdebAdapter.MenuViewHolder> {
    private List<DataIdeb> data;
    private Context context;
    private PrapenAoItemDataIdebBinding binding;

    public DataIdebAdapter(Context context,List< DataIdeb> mdata) {
        this.context = context;
        this.data = mdata;
    }

    @NonNull
    @Override
    public DataIdebAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding= PrapenAoItemDataIdebBinding.inflate(layoutInflater,parent,false);
        View view = binding.getRoot();
        return new DataIdebAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataIdebAdapter.MenuViewHolder holder, final int position) {
        holder.tvNamaLembagaKeuangan.setText(data.get(position).getNamaLembagaKeuangan());
        holder.tvBakiDebet.setText(AppUtil.parseRupiah(data.get(position).getBakiDebet()));
        holder.tvKualitasPembiayaan.setText(data.get(position).getKualitasPembiayaan());
        holder.tvPerkiraanAngsuranBulanan.setText(AppUtil.parseRupiah(data.get(position).getPerkiraanAngsuranBulanan()));
        holder.tvTreatmentPembiayaanEksisting.setText(data.get(position).getTreatmentPembiayaan());


        holder.btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "clicking", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,EditIdebActivity.class);
                intent.putExtra("dataIdeb",data.get(position));
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaLembagaKeuangan,tvBakiDebet,tvKualitasPembiayaan,tvPerkiraanAngsuranBulanan,tvTreatmentPembiayaanEksisting;
        Button btnUbah;

        public MenuViewHolder(View itemView) {
            super(itemView);
            btnUbah=binding.btnUbah;

            tvNamaLembagaKeuangan=binding.tvNamaLembagaKeuangan;
            tvBakiDebet=binding.tvBakiDebet;
            tvKualitasPembiayaan=binding.tvKualitasPembiayaan;
            tvPerkiraanAngsuranBulanan=binding.tvPerkiraanAngsuranBulanan;
            tvTreatmentPembiayaanEksisting=binding.tvTreatmentPembiayaanEksisting;
        }

    }

}
