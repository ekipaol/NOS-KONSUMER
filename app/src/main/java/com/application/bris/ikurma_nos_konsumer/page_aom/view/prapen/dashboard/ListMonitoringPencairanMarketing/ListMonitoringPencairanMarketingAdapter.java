package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.dashboard.ListMonitoringPencairanMarketing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.SubAkseptasiPendapatan;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.DataListMonitoringMarketing;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ItemHasilCanvasingBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.ItemListMonitoringPencairanMarketingBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.canvasing.ResumeFiturAdapter;

import java.util.Arrays;
import java.util.List;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class ListMonitoringPencairanMarketingAdapter extends RecyclerView.Adapter<ListMonitoringPencairanMarketingAdapter.MenuViewHolder> {
    ItemListMonitoringPencairanMarketingBinding binding;
    private Context context;
    private List<DataListMonitoringMarketing> list;
    private List<String> title;

    public ListMonitoringPencairanMarketingAdapter(Context context, List<DataListMonitoringMarketing> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemListMonitoringPencairanMarketingBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        return new ListMonitoringPencairanMarketingAdapter.MenuViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        final DataListMonitoringMarketing datas = list.get(position);
        title = Arrays.asList(context.getResources().getStringArray(R.array.alphabetically));
        holder.tvTitle.setText("List Monitoring " + String.valueOf(position + 1));
        holder.tvApplicationId.setText(String.valueOf(datas.getApplicationId()));
        holder.tvNoAplikasi.setText(datas.getNoAplikasi());
        holder.tvProduk.setText(datas.getProduk());
        holder.tvFileNamePencairan.setText(datas.getFileNamePencairan());
        holder.tvSequence.setText(datas.getSequence());
        holder.tvStatusBlokir.setText(datas.getStatusBlokir());
        holder.tvKeteranganBlokir.setText(datas.getKeteranganBlokir());
        holder.tvStatusPembentukanLD.setText(datas.getStatusPembentukanLD());
        holder.tvKeteranganPembentukanLD.setText(datas.getKeteranganPembentukanLD());
        holder.tvNoLD.setText(datas.getNoLD());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvApplicationId, tvNoAplikasi, tvProduk, tvFileNamePencairan, tvSequence, tvStatusBlokir, tvKeteranganBlokir, tvStatusPembentukanLD, tvKeteranganPembentukanLD, tvNoLD;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvTitle = binding.tvTitle;
            tvApplicationId = binding.tvApplicationId;
            tvNoAplikasi = binding.tvNomorAplikasi;
            tvProduk = binding.tvProduk;
            tvFileNamePencairan = binding.tvFileName;
            tvSequence = binding.tvSequence;
            tvStatusBlokir = binding.tvStatusPembentukanBlokir;
            tvKeteranganBlokir = binding.tvKeteranganBlokir;
            tvStatusPembentukanLD = binding.tvStatusPembentukanLd;
            tvKeteranganPembentukanLD = binding.tvKeteranganLd;
            tvNoLD = binding.tvNomorLd;
        }
    }
}
