package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_dedupe_all;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ItemPengecekanWiseBinding;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MISAdapter extends RecyclerView.Adapter<MISAdapter.MenuViewHolder> {
    private JsonArray data;
    private Context context;
    private ItemPengecekanWiseBinding binding;
    private AppPreferences appPreferences;

    public MISAdapter(Context context, JsonArray mdata) {
        this.context = context;
        this.data = mdata;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemPengecekanWiseBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        appPreferences = new AppPreferences(context);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        JsonObject mdata = data.get(position).getAsJsonObject();
        if (position == 0) {
            binding.listTitik.setVisibility(View.GONE);
        }
        holder.tvNoAplikasi.setText(mdata.get("No_Aplikasi").getAsString());
        holder.tvPlafond.setText(mdata.get("Produk_NOS").getAsString());
        holder.tvProduk.setText(mdata.get("Plafond").getAsString());
        holder.tvStage.setText(mdata.get("StateAplikasi").getAsString());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvNoAplikasi, tvPlafond, tvProduk, tvStage;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvNoAplikasi = binding.tvNoAplikasi;
            tvPlafond = binding.tvPlafond;
            tvProduk = binding.tvProduk;
            tvStage = binding.tvStage;
        }

    }
}