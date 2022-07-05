package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_dedupe_all;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ItemPengecekanPendanaanBinding;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class PendanaanAdapter extends RecyclerView.Adapter<PendanaanAdapter.MenuViewHolder> {
    private JsonArray data;
    private Context context;
    private ItemPengecekanPendanaanBinding binding;
    private AppPreferences appPreferences;

    public PendanaanAdapter(Context context, JsonArray mdata) {
        this.context = context;
        this.data = mdata;
    }

    @NonNull
    @Override
    public PendanaanAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemPengecekanPendanaanBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        appPreferences = new AppPreferences(context);
        return new PendanaanAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendanaanAdapter.MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        JsonObject mdata = data.get(position).getAsJsonObject();
        if (position == 0) {
            binding.listTitik.setVisibility(View.GONE);
        }
        holder.tvNomorRekening.setText(mdata.get("NoRekening").getAsString());
        holder.tvProduk.setText(mdata.get("TipeProduk").getAsString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomorRekening, tvProduk;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvNomorRekening = binding.tvNomorRekening;
            tvProduk = binding.tvProduk;
        }

    }
}
