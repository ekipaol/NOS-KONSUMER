package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.master_instansi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ItemListLngpBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataListLngp;
import java.util.ArrayList;
import java.util.List;


public class AdapterListLngp extends RecyclerView.Adapter<AdapterListLngp.MenuViewHolder> implements Filterable {
    private List<DataListLngp> data;
    private List<DataListLngp> datafiltered;
    private Context context;
    private ItemListLngpBinding binding;
    AppPreferences appPreferences;

    public AdapterListLngp(Context context,List< DataListLngp> mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = data;
    }

    @NonNull
    @Override
    public AdapterListLngp.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding= ItemListLngpBinding.inflate(layoutInflater,parent,false);
        View view = binding.getRoot();
        appPreferences=new AppPreferences(context);
        return new AdapterListLngp.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListLngp.MenuViewHolder holder, final int position) {
        final DataListLngp datas = datafiltered.get(position);

        holder.tvNamaInstansi.setText(datas.getNamaInstansi());
        holder.tvNoLngp.setText(datas.getNoLngp());


        holder.llLngp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Klik tombol refresh di pojok kanan untuk menambah/update LNGP otomatis dari core banking", Toast.LENGTH_SHORT).show();
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
                    List<DataListLngp> filteredList = new ArrayList<>();
                    for (DataListLngp row : data){
                        if((row.getNamaInstansi()!=null)&&(row.getNamaInstansi().toLowerCase().contains(charString.toLowerCase())) || row.getNoLngp().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<DataListLngp>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView tvNoLngp,tvNamaInstansi;
        LinearLayout llLngp;

        public MenuViewHolder(View itemView) {
            super(itemView);

            tvNoLngp=binding.tvNomorLngp;
            tvNamaInstansi=binding.tvNamaInstansi;
            llLngp=binding.llLngp;
        }

    }

}