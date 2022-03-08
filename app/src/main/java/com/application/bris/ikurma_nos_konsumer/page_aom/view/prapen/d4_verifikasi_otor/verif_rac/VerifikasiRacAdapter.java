package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_rac;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseLogicalDoc;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenItemDataVerifRacBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiRac;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.jaminan.DataJaminanActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifikasiRacAdapter extends RecyclerView.Adapter<VerifikasiRacAdapter.MenuViewHolder> {
    private List<DataVerifikasiRac> data;
    private List<DataVerifikasiRac> dataBaru;
    private Context context;
    private PrapenItemDataVerifRacBinding binding;
    private DropdownRecyclerListener dropdownRecyclerListener;

    public VerifikasiRacAdapter(Context context, List<DataVerifikasiRac> mdata,DropdownRecyclerListener dropdownRecyclerListener1) {
        this.context = context;
        this.data = mdata;
        this.dropdownRecyclerListener=dropdownRecyclerListener1;
    }

    @NonNull
    @Override
    public VerifikasiRacAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = PrapenItemDataVerifRacBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        return new VerifikasiRacAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerifikasiRacAdapter.MenuViewHolder holder,  int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        holder.et_hasil_cek_verif.setFocusable(false);
        holder.tv_nama_rac.setText(data.get(position).getParameter());
        holder.tv_hasil_engine.setText(data.get(position).getParameterDesc());
        holder.et_hasil_cek_verif.setText(data.get(position).getParameterDescVerifikator());
        holder.et_catatan.setText(data.get(position).getCatatanHasilVerifikasi());
//        holder.tv_ketentuan.setText(data.get(position).getKetentuan());
        holder.tv_hasil.setText(data.get(position).getValue());

        try{
            checkFileTypeThenSet(context,data.get(position).getImg(),holder.iv_dokumen,data.get(position).getFileName(),holder);
        }
        catch (Exception e){
            e.printStackTrace();
        }


//        onClicks(position);



    }

    private void onClicks(int currentPosition){
        binding.tfHasilCekVerifikator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition,binding.tfHasilCekVerifikator.getLabelText());
            }
        });

        binding.etHasilCekVerifikator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition,binding.tfHasilCekVerifikator.getLabelText());
            }
        });

        binding.tfHasilCekVerifikator.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition,binding.tfHasilCekVerifikator.getLabelText());
            }
        });

        binding.btUploadDokumenTambahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "clicking upload", Toast.LENGTH_SHORT).show();
            }
        });
        
        

    }

    public void loadFileJson(String idFoto,ImageView imageView,VerifikasiRacAdapter.MenuViewHolder holder) {
        ApiClientAdapter apiClientAdapter=new ApiClientAdapter(context);
        Call<ParseResponseLogicalDoc> call = apiClientAdapter.getApiInterface().getFileJson(idFoto);
        call.enqueue(new Callback<ParseResponseLogicalDoc>() {
            @Override
            public void onResponse(Call<ParseResponseLogicalDoc> call, Response<ParseResponseLogicalDoc> response) {
//                binding.loadingLayout.progressbarLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getBinaryData()!=null){
                        AppUtil.convertBase64ToFileWithOnClick(context,response.body().getBinaryData(),imageView,response.body().getFileName());
                    }
                    else{
                        AppUtil.notiferror(context,holder.et_catatan.findViewById(android.R.id.content), "Data PDF Tidak Didapatkan");
                    }


                }
                else{
                    AppUtil.notiferror(context,holder.et_catatan.findViewById(android.R.id.content), "Terjadi kesalahan");
                }
            }

            @Override
            public void onFailure(Call<ParseResponseLogicalDoc> call, Throwable t) {
                AppUtil.notiferror(context,holder.et_catatan.findViewById(android.R.id.content), "Terjadi kesalahan");
                Log.d("LOG D", t.getMessage());
                t.printStackTrace();
            }
        });

    }

    private void checkFileTypeThenSet(Context context,String idDok,ImageView imageView,String fileName,VerifikasiRacAdapter.MenuViewHolder holder){

        if(fileName.substring(fileName.length()-3,fileName.length()).equalsIgnoreCase("pdf")){
            loadFileJson(idDok,imageView,holder);
        }
        else{
            AppUtil.setImageGlide(context,idDok,imageView);
        }
    }

    public void setItems(List<DataVerifikasiRac> dataVerif) {
        this.data = dataVerif;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nama_rac,tv_hasil_engine,tv_ketentuan,tv_hasil;
        EditText et_hasil_cek_verif,et_catatan;
        ImageView iv_dokumen;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tv_nama_rac=binding.tvNamaRac;
            tv_hasil_engine=binding.tvHasilCekEngine;
            et_hasil_cek_verif=binding.etHasilCekVerifikator;
            et_catatan=binding.etCatatan;
            tv_ketentuan=binding.tvKetentuan;
            tv_hasil=binding.tvHasil;
            iv_dokumen=binding.ivDokumen;

            //disable text
            et_catatan.setFocusable(false);

        }

    }
}
