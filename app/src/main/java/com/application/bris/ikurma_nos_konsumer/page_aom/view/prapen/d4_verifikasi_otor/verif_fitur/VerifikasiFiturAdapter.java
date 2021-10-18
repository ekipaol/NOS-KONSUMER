package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_fitur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.databinding.PrapenItemDataFiturBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiFitur;

import java.util.List;

public class VerifikasiFiturAdapter extends RecyclerView.Adapter<VerifikasiFiturAdapter.MenuViewHolder> {
    private List<DataVerifikasiFitur> data;
    private Context context;
    private PrapenItemDataFiturBinding binding;
    private DropdownRecyclerListener dropdownRecyclerListener;

    public VerifikasiFiturAdapter(Context context, List<DataVerifikasiFitur> mdata,DropdownRecyclerListener dropdownRecyclerListener1) {
        this.context = context;
        this.data = mdata;
        this.dropdownRecyclerListener=dropdownRecyclerListener1;
    }

    @NonNull
    @Override
    public VerifikasiFiturAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = PrapenItemDataFiturBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        return new VerifikasiFiturAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerifikasiFiturAdapter.MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        holder.et_hasil_cek_verif.setFocusable(false);
        holder.tv_nama_fitur.setText(data.get(position).getNamaFitur());
        holder.tv_hasil_engine.setText(data.get(position).getHasilVerifikasiEngine());
        holder.et_hasil_cek_verif.setText(data.get(position).getHasilVerifikasiVerif());
        holder.et_catatan.setText(data.get(position).getCatatan());
        holder.tv_hasil.setText(data.get(position).getHasil());
        holder.tv_ketentuan.setText(data.get(position).getKetentuan());

        onClicks(position);



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


    @Override
    public int getItemCount() {
        return data.size();
    }



    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nama_fitur,tv_hasil_engine,tv_ketentuan,tv_hasil;
        EditText et_hasil_cek_verif,et_catatan;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tv_nama_fitur =binding.tvNamaFitur;
            tv_hasil_engine=binding.tvHasilCekEngine;
            et_hasil_cek_verif=binding.etHasilCekVerifikator;
            et_catatan=binding.etCatatan;
            tv_ketentuan=binding.tvKetentuan;
            tv_hasil=binding.tvHasil;

        }

    }
}
