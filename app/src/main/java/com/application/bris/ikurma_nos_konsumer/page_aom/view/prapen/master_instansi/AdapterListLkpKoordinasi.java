package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.master_instansi;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseLogicalDoc;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ItemListAplikasiPrapenBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.ItemListLkpKoordinasiBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataListAplikasi;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataLkpKoordinasi;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhotoFromIdLogicalDoc;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.DetilAplikasiActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterListLkpKoordinasi extends RecyclerView.Adapter<AdapterListLkpKoordinasi.MenuViewHolder> implements Filterable {
    private List<DataLkpKoordinasi> data;
    private List<DataLkpKoordinasi> datafiltered;
    private Context context;
    private ItemListLkpKoordinasiBinding binding;
    AppPreferences appPreferences;

    public AdapterListLkpKoordinasi(Context context,List< DataLkpKoordinasi> mdata) {
        this.context = context;
        this.data = mdata;
        this.datafiltered = data;
    }

    @NonNull
    @Override
    public AdapterListLkpKoordinasi.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding= ItemListLkpKoordinasiBinding.inflate(layoutInflater,parent,false);
        View view = binding.getRoot();
        appPreferences=new AppPreferences(context);
        return new AdapterListLkpKoordinasi.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListLkpKoordinasi.MenuViewHolder holder, final int position) {
        final DataLkpKoordinasi datas = datafiltered.get(position);

        holder.tvNamaCabang.setText(datas.getNamaCabang());
        holder.tvKodeCabang.setText(datas.getKodeCabang());
        holder.tvTanggalKadaluarsa.setText(AppUtil.parseTanggalGeneral(datas.getTanggalKadaluarsa(),"ddMMyyyy","dd-MM-yyyy"));
        holder.tvTanggalLkp.setText(AppUtil.parseTanggalGeneral(datas.getTanggalLkp(),"ddMMyyyy","dd-MM-yyyy"));
        holder.llLihatDokumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                AppUtil.openDocumentWithFileNameCheck(context,datas.getFileName(),datas.getIdDokumen(),holder.btLihatDokumen);
//                Toast.makeText(context, "Click linear", Toast.LENGTH_SHORT).show();
                checkFileTypeThenSet(context,datas.getIdDokumen(),holder.btLihatDokumen,datas.getFileName());
            }
        });

        holder.btLihatDokumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AppUtil.openDocumentWithFileNameCheck(context,datas.getFileName(),datas.getIdDokumen(),holder.btLihatDokumen);
//                Toast.makeText(context, "Click button", Toast.LENGTH_SHORT).show();
                checkFileTypeThenSet(context,datas.getIdDokumen(),holder.btLihatDokumen,datas.getFileName());
            }
        });



    }

    private void checkFileTypeThenSet(Context context, String idDok, ImageView imageView, String fileName){
        if(fileName.substring(fileName.length()-3,fileName.length()).equalsIgnoreCase("pdf")){

            if(idDok.length()<10){
                loadFileJson(idDok,imageView);
            }
            else{
                AppUtil.convertBase64ToFileDirect(context,idDok,imageView,fileName);
            }

        }
        else{

            if(idDok.length()<10){
                try{
                    DialogPreviewPhotoFromIdLogicalDoc.display(((AppCompatActivity) imageView.getContext()).getSupportFragmentManager(), "Preview Foto", idDok);
                }
                catch (ClassCastException e){
                    DialogPreviewPhotoFromIdLogicalDoc.display(((AppCompatActivity) imageView.getContext()).getSupportFragmentManager(), "Preview Foto", idDok);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            else{
                // TODO: 24/05/22
                //belum bisa handle kalau base 64 gaisss
//                AppUtil.convertBase64ToImage(idDok,imageView);
            }

        }
    }


    public void loadFileJson(String idFoto,ImageView imageView) {
        ApiClientAdapter apiClientAdapter=new ApiClientAdapter(context);
        Call<ParseResponseLogicalDoc> call = apiClientAdapter.getApiInterface().getFileJson(idFoto);
        call.enqueue(new Callback<ParseResponseLogicalDoc>() {
            @Override
            public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
//                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getBinaryData()!=null){
                        AppUtil.convertBase64ToFileDirect(context,response.body().getBinaryData(),imageView,response.body().getFileName());
                    }
                    else{
                        Toast.makeText(context, "Data PDF Tidak Didapatkan", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
//                binding.loading.progressbarLoading.setVisibility(View.GONE);
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
                    List<DataLkpKoordinasi> filteredList = new ArrayList<>();
                    for (DataLkpKoordinasi row : data){
                        if((row.getNamaCabang()!=null)&&(row.getNamaCabang().toLowerCase().contains(charString.toLowerCase())) || row.getKodeCabang().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<DataLkpKoordinasi>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaCabang,tvKodeCabang,tvTanggalLkp,tvTanggalKadaluarsa;
        CardView cvData;
        ImageView btLihatDokumen;
        LinearLayout llLihatDokumen;

        public MenuViewHolder(View itemView) {
            super(itemView);

            tvNamaCabang=binding.tvNamaCabang;
            tvKodeCabang=binding.tvKodeCabang;
            tvTanggalLkp=binding.tvTanggalLkpKoordinasi;
            tvTanggalKadaluarsa=binding.tvTanggalKadaluarsa;
            cvData=binding.cvDataLkpKoordinasi;
            btLihatDokumen=binding.btnLihatDokumen;
            llLihatDokumen=binding.llLihatDokumen;
        }

    }

}