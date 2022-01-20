package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_hutang;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.databinding.PrapenItemVerifikasiHutangBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiHutang;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;

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
        holder.ethasilverifikasi.setFocusable(false);
        holder.etAngsuranVerifikator.setFocusable(false);
        holder.ethasilverifikasi.setText(data.get(position).getHasilVerifikasi());
        holder.etVerifikasiFasilitas.setText(data.get(position).getHasilVerifikasi());
        holder.tvNamaPemberiHutang.setText(data.get(position).getNamaPemberiUtang());
        holder.tvAngsuranBulanan.setText(AppUtil.parseRupiah(data.get(position).getAngsuranBulanan()));
        holder.tvNominalPinjaman.setText(AppUtil.parseRupiah(data.get(position).getNominalPinjaman()));
        holder.tvSisaJangkaWaktu.setText(data.get(position).getSisaWaktuPinjaman()+ " Bulan");
        holder.tvTreatmentPembiayaanEksisting.setText(data.get(position).getTreatmentPembiayaan());

        holder.etAngsuranVerifikator.addTextChangedListener(new NumberTextWatcherCanNolForThousand(holder.etAngsuranVerifikator));
        holder.etAngsuranVerifikator.setText(data.get(position).getAngsuranBulanan());

        onClicks(position,holder);



    }

    private void onClicks(int currentPosition,@NonNull VerifikasiHutangAdapter.MenuViewHolder holder){

        //VERIFIKASI FASILITAS
//        holder.tfVerifikasiFasilitas.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition,holder.tfVerifikasiFasilitas.getLabelText());
//            }
//        });
//        holder.tfVerifikasiFasilitas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition,holder.tfVerifikasiFasilitas.getLabelText());
//            }
//        });
//
//        holder.etVerifikasiFasilitas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition,holder.tfVerifikasiFasilitas.getLabelText());
//            }
//        });
//
//        //HASIL VERIFIKASI
//
//        holder.tfHasilVerifikasi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition,holder.tfHasilVerifikasi.getLabelText());
//            }
//        });
//
//        holder.tfHasilVerifikasi.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition,holder.tfHasilVerifikasi.getLabelText());
//            }
//        });
//
//        holder.ethasilverifikasi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition,holder.tfHasilVerifikasi.getLabelText());
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }



    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaPemberiHutang,tvAngsuranBulanan,tvNominalPinjaman,tvSisaJangkaWaktu,tvTreatmentPembiayaanEksisting;
        TextFieldBoxes tfVerifikasiFasilitas,tfHasilVerifikasi;
        EditText etVerifikasiFasilitas,etAngsuranVerifikator,ethasilverifikasi;
        public MenuViewHolder(View itemView) {
            super(itemView);
            tvNamaPemberiHutang =binding.tvNamaPemberiHutang;
            tvAngsuranBulanan=binding.tvAngsuranBulanan;
            tvNominalPinjaman=binding.tvNominalPinjaman;
            tvSisaJangkaWaktu=binding.tvSisaJangkaWaktu;
            tvTreatmentPembiayaanEksisting=binding.tvTreatmentPembiayaanEksisting;
            etVerifikasiFasilitas=binding.etHasilVerifikasiFasilitas;
            tfVerifikasiFasilitas=binding.tfVerifikasiFasilitas;
            etAngsuranVerifikator=binding.etAngsuranVerifikator;
            ethasilverifikasi=binding.etHasilVerifikasi;
            tfHasilVerifikasi=binding.tfHasilVerifikasi;


        }

    }
}