package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_dedupe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoItemDataHutangBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenItemDedupeBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenItemVerifikasiHutangBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataDedupe;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataHutang;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiHutang;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class DataDedupeAdapter extends RecyclerView.Adapter<DataDedupeAdapter.MenuViewHolder> {
    private List<DataDedupe> data;
    private Context context;
    private PrapenItemDedupeBinding binding;
    private DropdownRecyclerListener dropdownRecyclerListener;

    public DataDedupeAdapter(Context context, List<DataDedupe> mdata) {
        this.context = context;
        this.data = mdata;

    }

    @NonNull
    @Override
    public DataDedupeAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = PrapenItemDedupeBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        return new DataDedupeAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataDedupeAdapter.MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        holder.tvNamaNasabah.setText(data.get(position).getNamaNasabah());
        holder.tvProduk.setText(data.get(position).getProduk());
        holder.tvStatusDalamAplikasi.setText(data.get(position).getStatusDalamAplikasi());
        holder.tvStatusTerakhir.setText(data.get(position).getStatusTerakhir());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaNasabah,tvProduk,tvStatusDalamAplikasi,tvStatusTerakhir;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvNamaNasabah =binding.tvNamaNasabah;
            tvProduk=binding.tvProduk;
            tvStatusDalamAplikasi=binding.tvStatusDalamAplikasi;
            tvStatusTerakhir=binding.tvStatusTerakhir;



        }

    }
}