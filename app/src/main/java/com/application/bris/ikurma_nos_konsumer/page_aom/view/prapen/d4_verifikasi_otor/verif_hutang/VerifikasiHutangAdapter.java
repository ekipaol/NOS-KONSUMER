package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_hutang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.databinding.PrapenItemDataFiturBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenItemVerifikasiHutangBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiFitur;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiHutang;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.List;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class VerifikasiHutangAdapter extends RecyclerView.Adapter<VerifikasiHutangAdapter.MenuViewHolder> {
    private List<DataVerifikasiHutang> data;
    private Context context;
    private PrapenItemVerifikasiHutangBinding binding;
    private DropdownRecyclerListener dropdownRecyclerListener;

    public VerifikasiHutangAdapter(Context context, List<DataVerifikasiHutang> mdata,DropdownRecyclerListener dropdownRecyclerListener1) {
        this.context = context;
        this.data = mdata;
        this.dropdownRecyclerListener=dropdownRecyclerListener1;
    }

    @NonNull
    @Override
    public VerifikasiHutangAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = PrapenItemVerifikasiHutangBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        return new VerifikasiHutangAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerifikasiHutangAdapter.MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        holder.etVerifikasiFasilitas.setFocusable(false);
        holder.etVerifikasiFasilitas.setText(data.get(position).getHasilVerifikasiHutang());
        holder.tvNamaPemberiHutang.setText(data.get(position).getNamaPemberiHutang());
        holder.tvAngsuranBulanan.setText(AppUtil.parseRupiah(data.get(position).getAngsuranBulanan()));
        holder.tvNominalPinjaman.setText(AppUtil.parseRupiah(data.get(position).getNominalPinjaman()));
        holder.tvSisaJangkaWaktu.setText(data.get(position).getSisaJangkaWaktu()+ " Bulan");
        holder.tvTreatmentPembiayaanEksisting.setText(data.get(position).getTreatmentPembiayaan());

        onClicks(position,holder);



    }

    private void onClicks(int currentPosition,@NonNull VerifikasiHutangAdapter.MenuViewHolder holder){
        holder.tfVerifikasiFasilitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition);
            }
        });

        holder.etVerifikasiFasilitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition);
            }
        });

        holder.tfVerifikasiFasilitas.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition);
            }
        });


    }


    @Override
    public int getItemCount() {
        return data.size();
    }



    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaPemberiHutang,tvAngsuranBulanan,tvNominalPinjaman,tvSisaJangkaWaktu,tvTreatmentPembiayaanEksisting;
        TextFieldBoxes tfVerifikasiFasilitas;
        EditText etVerifikasiFasilitas;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvNamaPemberiHutang =binding.tvNamaPemberiHutang;
            tvAngsuranBulanan=binding.tvAngsuranBulanan;
            tvNominalPinjaman=binding.tvNominalPinjaman;
            tvSisaJangkaWaktu=binding.tvSisaJangkaWaktu;
            tvTreatmentPembiayaanEksisting=binding.tvTreatmentPembiayaanEksisting;
            etVerifikasiFasilitas=binding.etHasilVerifikasiFasilitas;
            tfVerifikasiFasilitas=binding.tfVerifikasiFasilitas;


        }

    }
}