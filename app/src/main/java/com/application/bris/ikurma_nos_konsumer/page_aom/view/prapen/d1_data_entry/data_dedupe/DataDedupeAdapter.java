package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_dedupe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.databinding.PrapenItemDedupeBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataDedupe;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.DropdownRecyclerListener;

import java.util.List;

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
        holder.tvProduk.setText(data.get(position).getProdukNOS());
        holder.tvStatusDalamAplikasi.setText(data.get(position).getStatusNasabah());
        holder.tvStatusTerakhir.setText(data.get(position).getStateAplikasi());

        holder.cv_data_dedupe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Nit Not, masuk ke data detil aplikasi ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaNasabah,tvProduk,tvStatusDalamAplikasi,tvStatusTerakhir;
        CardView cv_data_dedupe;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvNamaNasabah =binding.tvNamaNasabah;
            cv_data_dedupe =binding.cvDataDedupe;
            tvProduk=binding.tvProduk;
            tvStatusDalamAplikasi=binding.tvStatusDalamAplikasi;
            tvStatusTerakhir=binding.tvStatusTerakhir;



        }

    }
}