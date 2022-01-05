package com.application.bris.ikurma_nos_konsumer.view.corelayout.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.databinding.ItemListAplikasiPrapenBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.ItemListAplikasiPrapenFrontBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataListAplikasi;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.DetilAplikasiActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class AdapterListAplikasiFront extends RecyclerView.Adapter<AdapterListAplikasiFront.MenuViewHolder> implements Filterable {
    private List<DataListAplikasi> data;
    private List<DataListAplikasi> datafiltered;
    private Context context;
    private ItemListAplikasiPrapenFrontBinding binding;

    public AdapterListAplikasiFront(Context context,List< DataListAplikasi> mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = data;
    }

    @NonNull
    @Override
    public AdapterListAplikasiFront.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding= ItemListAplikasiPrapenFrontBinding.inflate(layoutInflater,parent,false);
        View view = binding.getRoot();
        return new AdapterListAplikasiFront.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListAplikasiFront.MenuViewHolder holder, final int position) {
        final DataListAplikasi datas = datafiltered.get(position);

        holder.tvNama.setText(datas.getNama());
        holder.tvStatus.setText(datas.getStatusAplikasi());
        holder.tvTenor.setText(datas.getJangkaWaktu()+" Bulan");
        holder.tvPlafon.setText(AppUtil.parseRupiah(datas.getPlafond()));
        holder.tvTanggalPengajuan.setText(AppUtil.parseTanggalGeneral("Tgl Input : "+datas.getCreateDate(),"yyyyMMdd","dd-MM-yyyy"));

        if(datas.getJangkaWaktu().equalsIgnoreCase("0")){
            holder.tvTenor.setText("Belum ada tenor");
        }

        if(datas.getPlafond().equalsIgnoreCase("0")){
            holder.tvPlafon.setText("Belum ada plafond");
        }

        if(datas.getNama()==null||datas.getNama().isEmpty()){
            holder.tvNama.setText("Nama : -");
        }

        holder.cvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //testing jadi dicomment dlu
//                if(!datas.getStatusAplikasiId().equalsIgnoreCase("d.1")&&!datas.getStatusAplikasiId().equalsIgnoreCase("d.3")&&!datas.getStatusAplikasiId().equalsIgnoreCase("d.5")){
//                    Toast.makeText(context, "Tidak dapat mengubah aplikasi dengan status : "+datas.getStatusAplikasi(), Toast.LENGTH_LONG).show();
//                }
//                else{
                Intent intent=new Intent(context, DetilAplikasiActivity.class);
                intent.putExtra("idAplikasi",datas.getApplicationId());
                intent.putExtra("nama",datas.getNama());
                intent.putExtra("plafond",datas.getPlafond());
                intent.putExtra("noAplikasi",datas.getApplicationNo());
                intent.putExtra("status",datas.getStatusAplikasi());
                intent.putExtra("statusId",datas.getStatusAplikasiId());
                context.startActivity(intent);
//                }


            }
        });

        holder.btDetil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //testing jadi dicomment dlu
//                if(!datas.getStatusAplikasiId().equalsIgnoreCase("d.1")&&!datas.getStatusAplikasiId().equalsIgnoreCase("d.3")&&!datas.getStatusAplikasiId().equalsIgnoreCase("d.5")){
//                    Toast.makeText(context, "Tidak dapat mengubah aplikasi dengan status : "+datas.getStatusAplikasi(), Toast.LENGTH_LONG).show();
//                }
//                else{
                Intent intent=new Intent(context, DetilAplikasiActivity.class);
                intent.putExtra("idAplikasi",datas.getApplicationId());
                intent.putExtra("nama",datas.getNama());
                intent.putExtra("plafond",datas.getPlafond());
                intent.putExtra("noAplikasi",datas.getApplicationNo());
                intent.putExtra("status",datas.getStatusAplikasi());
                intent.putExtra("statusId",datas.getStatusAplikasiId());
                context.startActivity(intent);
//                }
            }
        });

//        AppUtil.convertBase64ToImage(datas.getImg(),holder.ivFoto);


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
                    List<DataListAplikasi> filteredList = new ArrayList<>();
                    for (DataListAplikasi row : data){
                        if(row.getNama().toLowerCase().contains(charString.toLowerCase()) || row.getApplicationNo().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<DataListAplikasi>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama,tvStatus,tvPlafon,tvTenor,tvTanggalPengajuan;
        ImageView ivFoto;
        CardView cvData;
        Button btDetil;

        public MenuViewHolder(View itemView) {
            super(itemView);

            tvNama=binding.tvNama;
            tvStatus=binding.tvStatus;
            tvPlafon=binding.tvPlafond;
            tvTenor=binding.tvTenor;
            cvData=binding.cvHotprospekFront;
            btDetil=binding.btPilih;
            tvTanggalPengajuan=binding.tvTanggalEntry;
        }

    }

}
