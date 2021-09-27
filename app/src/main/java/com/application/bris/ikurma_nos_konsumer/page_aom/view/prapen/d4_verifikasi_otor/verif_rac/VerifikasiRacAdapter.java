package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_rac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.databinding.PrapenItemDataVerifRacBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiRac;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;

import java.util.List;

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
        holder.tv_nama_rac.setText(data.get(position).getNamaRac());
        holder.tv_hasil_engine.setText(data.get(position).getHasilVerifikasiEngine());
        holder.et_hasil_cek_verif.setText(data.get(position).getHasilVerifikasiVerif());
        holder.et_catatan.setText(data.get(position).getCatatan());

        onClicks(position);



    }

    private void onClicks(int currentPosition){
        binding.tfHasilCekVerifikator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition);
            }
        });

        binding.etHasilCekVerifikator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition);
            }
        });

        binding.tfHasilCekVerifikator.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition);
            }
        });

        binding.btUploadDokumenTambahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "clicking upload", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setItems(List<DataVerifikasiRac> dataVerif) {
        this.data = dataVerif;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nama_rac,tv_hasil_engine;
        EditText et_hasil_cek_verif,et_catatan;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tv_nama_rac=binding.tvNamaRac;
            tv_hasil_engine=binding.tvHasilCekEngine;
            et_hasil_cek_verif=binding.etHasilCekVerifikator;
            et_catatan=binding.etCatatan;

        }

    }
}
