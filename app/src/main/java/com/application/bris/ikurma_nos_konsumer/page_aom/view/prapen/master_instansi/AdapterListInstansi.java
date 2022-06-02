package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.master_instansi;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseLogicalDoc;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ItemListInstansiBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.ItemListLkpKoordinasiBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataListInstansi;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataLkpKoordinasi;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhotoFromIdLogicalDoc;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterListInstansi extends RecyclerView.Adapter<AdapterListInstansi.MenuViewHolder>  {
    private List<DataListInstansi> data;
    private List<DataListInstansi> datafiltered;
    private Context context;
    private ItemListInstansiBinding binding;
    AppPreferences appPreferences;

    public AdapterListInstansi(Context context,List< DataListInstansi> mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = data;
    }

    @NonNull
    @Override
    public AdapterListInstansi.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding= ItemListInstansiBinding.inflate(layoutInflater,parent,false);
        View view = binding.getRoot();
        appPreferences=new AppPreferences(context);
        return new AdapterListInstansi.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListInstansi.MenuViewHolder holder, final int position) {
        final DataListInstansi datas = datafiltered.get(position);

        holder.tvNamaInstansi.setText(datas.getNamaInstansi());
        holder.tvKodeInstansi.setText(datas.getKodeInstansi());
        holder.tvStatusInstansi.setText(datas.getStatusInstansi());
        holder.tvJenisInstansi.setText(datas.getJenisInstansi());


        if(datas.getStatusInstansi().equalsIgnoreCase("aktif")){
            holder.tvStatusInstansi.setTextColor(context.getResources().getColor(R.color.main_green_color));
        }
        else{
            holder.tvStatusInstansi.setTextColor(context.getResources().getColor(R.color.red_btn_bg_color));
        }

        //ALL ONCLICKS

        holder.llLihatInstansi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,InputMasterInstansiActivity.class);
                context.startActivity(intent);

            }
        });

        holder.btLihatInstansi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,InputMasterInstansiActivity.class);
                context.startActivity(intent);
            }
        });

        holder.cvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,InputMasterInstansiActivity.class);
                context.startActivity(intent);
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

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaInstansi,tvKodeInstansi,tvStatusInstansi,tvJenisInstansi;
        CardView cvData;
        ImageView btLihatInstansi;
        LinearLayout llLihatInstansi;

        public MenuViewHolder(View itemView) {
            super(itemView);

            tvNamaInstansi=binding.tvNamaInstansi;
            tvKodeInstansi=binding.tvKodeInstansi;
            tvStatusInstansi=binding.tvStatusInstansi;
            tvJenisInstansi=binding.tvJenisInstansi;
            cvData=binding.cvDataInstansi;
            btLihatInstansi=binding.btnLihatInstansi;
            llLihatInstansi=binding.llLihatInstansi;
        }

    }

}