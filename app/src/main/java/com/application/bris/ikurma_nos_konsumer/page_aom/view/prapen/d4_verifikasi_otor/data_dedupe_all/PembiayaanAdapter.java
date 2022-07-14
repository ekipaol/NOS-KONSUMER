package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.data_dedupe_all;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ItemPengecekanPembiayaanBinding;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class PembiayaanAdapter extends RecyclerView.Adapter<PembiayaanAdapter.MenuViewHolder> {
    private JsonArray data;
    private Context context;
    private ItemPengecekanPembiayaanBinding binding;
    private AppPreferences appPreferences;

    public PembiayaanAdapter(Context context, JsonArray mdata) {
        this.context = context;
        this.data = mdata;
    }

    @NonNull
    @Override
    public PembiayaanAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemPengecekanPembiayaanBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        appPreferences = new AppPreferences(context);
        return new PembiayaanAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PembiayaanAdapter.MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        JsonObject mdata = data.get(position).getAsJsonObject();
        if (position == 0) {
            binding.listTitik.setVisibility(View.GONE);
        }
        holder.tvnold.setText(mdata.get("NoLD").getAsString());
        holder.tvproduk.setText(mdata.get("Produk").getAsString());
        holder.tvosterakhir.setText(mdata.get("OSTerakhir").getAsString());
        holder.tvangsuran.setText(mdata.get("Angsuran").getAsString());
        holder.tvkondisi.setText(mdata.get("KondisiKolektibilitas").getAsString());
        holder.tvstatusrestruk.setText(mdata.get("StatusRestruk").getAsString());
        holder.tvtanggalrestruk.setText(mdata.get("TanggalRestruk").getAsString());
        holder.tvketeranganrestruk.setText(mdata.get("KeteranganRestruk").getAsString());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvnold, tvproduk, tvosterakhir, tvangsuran, tvkondisi, tvstatusrestruk, tvtanggalrestruk, tvketeranganrestruk;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvnold = binding.tvNoLd;
            tvproduk = binding.tvProduk;
            tvosterakhir = binding.tvOsTerakhir;
            tvangsuran = binding.tvAngsuran;
            tvkondisi = binding.tvKondisi;
            tvstatusrestruk = binding.tvStatusRestruk;
            tvtanggalrestruk = binding.tvTanggalRestruk;
            tvketeranganrestruk = binding.tvKeteranganRestruk;
        }

    }
}
