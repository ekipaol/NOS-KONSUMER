package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.dashboard.ListMonitoringPencairanMarketing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseLogicalDoc;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.ReqIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.DataListMonitoringMarketing;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ItemListMonitoringMarketingV2Binding;
import com.application.bris.ikurma_nos_konsumer.databinding.ItemListMonitoringPencairanMarketingBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataLkpKoordinasi;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMonitoringPencairanMarketingAdapter extends RecyclerView.Adapter<ListMonitoringPencairanMarketingAdapter.MenuViewHolder> implements Filterable {
    ItemListMonitoringMarketingV2Binding binding;
    private Context context;
    private List<DataListMonitoringMarketing> data;
    private List<DataListMonitoringMarketing> datafiltered;
    private AppPreferences appPreferences;

    public ListMonitoringPencairanMarketingAdapter(Context context, List<DataListMonitoringMarketing> mdata) {
        this.data = mdata;
        this.context = context;
        this.datafiltered=data;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemListMonitoringMarketingV2Binding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        appPreferences=new AppPreferences(context);
        return new ListMonitoringPencairanMarketingAdapter.MenuViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        final DataListMonitoringMarketing datas = datafiltered.get(position);
        holder.tvTitle.setText(datas.getNama());
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

        holder.btnLihatCoverNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadCovernote(binding.btnLihatCoverNote,datafiltered.get(position).getApplicationId(),holder.loading);
            }
        });

        holder.clLihatCoverNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadCovernote(binding.btnLihatCoverNote,datafiltered.get(position).getApplicationId(),holder.loading);
            }
        });
    }

    public void downloadCovernote(ImageView imageView,Long idAplikasi,RelativeLayout loading) {
        loading.setVisibility(View.VISIBLE);
        ApiClientAdapter apiClientAdapter=new ApiClientAdapter(context);
        ReqUidIdAplikasi req=new ReqUidIdAplikasi();
        req.setApplicationId(idAplikasi);
        req.setUID(Integer.toString(appPreferences.getUid()));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().downloadCoverNoteAsuransi(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
//                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    loading.setVisibility(View.GONE);
                    if (response.body().getData()!=null){
                        AppUtil.convertBase64ToFileDirect(context,response.body().getData().get("File").toString(),imageView,"fileCoverNote.pdf");
                    }
                    else{
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    loading.setVisibility(View.GONE);
                    Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();

                t.printStackTrace();
            }
        });

    }

    @Override
    public int getItemCount() {
        if(datafiltered==null){
            return 0;
        }
        else{
            return datafiltered.size();
        }
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
                    List<DataListMonitoringMarketing> filteredList = new ArrayList<>();
                    for (DataListMonitoringMarketing row : data){
                        if((row.getNoAplikasi()!=null)&&(row.getNama().toLowerCase().contains(charString.toLowerCase())) ||row.getNoLD().toLowerCase().contains(charString.toLowerCase())||row.getStatusPembentukanLD().toLowerCase().contains(charString.toLowerCase())||row.getStatusBlokir().toLowerCase().contains(charString.toLowerCase())||row.getNoAplikasi().toLowerCase().contains(charString.toLowerCase()) || Long.toString(row.getApplicationId()).toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<DataListMonitoringMarketing>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvApplicationId, tvNoAplikasi, tvProduk, tvFileNamePencairan, tvSequence, tvStatusBlokir, tvKeteranganBlokir, tvStatusPembentukanLD, tvKeteranganPembentukanLD, tvNoLD;
        ImageView btnLihatCoverNote;
        RelativeLayout loading;
        ConstraintLayout clLihatCoverNote;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvTitle = binding.tvNama;
            tvApplicationId = binding.tvIdAplikasi;
            tvNoAplikasi = binding.tvNomorAplikasi;
            tvProduk = binding.tvProduk;
            tvFileNamePencairan = binding.tvNamaFile;
            tvSequence = binding.tvSequence;
            tvStatusBlokir = binding.tvStatusBlokir;
            tvKeteranganBlokir = binding.tvKeteranganBlokir;
            tvStatusPembentukanLD = binding.tvStatusLd;
            tvKeteranganPembentukanLD = binding.tvKeteranganLD;
            tvNoLD = binding.tvNomorLd;
            btnLihatCoverNote=binding.btnLihatCoverNote;
            loading=binding.loading.progressbarLoading;
            clLihatCoverNote=binding.clLihatCovernote;
        }
    }
}
