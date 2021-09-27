package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_ideb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.databinding.PrapenItemVerifikasiHutangBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenItemVerifikasiIdebBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiHutang;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiIdeb;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.List;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class VerifikasiIdebAdapter extends RecyclerView.Adapter<VerifikasiIdebAdapter.MenuViewHolder> {
    private List<DataVerifikasiIdeb> data;
    private Context context;
    private PrapenItemVerifikasiIdebBinding binding;
    private DropdownRecyclerListener dropdownRecyclerListener;

    public VerifikasiIdebAdapter(Context context, List<DataVerifikasiIdeb> mdata,DropdownRecyclerListener dropdownRecyclerListener1) {
        this.context = context;
        this.data = mdata;
        this.dropdownRecyclerListener=dropdownRecyclerListener1;
    }

    @NonNull
    @Override
    public VerifikasiIdebAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = PrapenItemVerifikasiIdebBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        return new VerifikasiIdebAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerifikasiIdebAdapter.MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        holder.etHasilVerifikasiFasilitas.setFocusable(false);
        holder.etHasilVerifikasiFasilitas.setText(data.get(position).getHasilVerifikasiIdeb());
        holder.tvNamaLembagaKeuangan.setText(data.get(position).getNamaLembagaKeuangan());
        holder.tvKualitasPembiayaan.setText(data.get(position).getKualitasPembiayaan());
        holder.tvBakiDebet.setText(AppUtil.parseRupiah(data.get(position).getBakiDebet()));
        holder.tvPerkiraanAngsuranBulanan.setText(AppUtil.parseRupiah(data.get(position).getPerkiraanAngsuranBulanan()));
        holder.tvTreatmentPembiayaanEksisting.setText(data.get(position).getTreatmentPembiayaan());

        onClicks(position,holder);



    }

    private void onClicks(int currentPosition,@NonNull VerifikasiIdebAdapter.MenuViewHolder holder){
        holder.tfVerifikasiFasilitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition);
            }
        });

        holder.etHasilVerifikasiFasilitas.setOnClickListener(new View.OnClickListener() {
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
        TextView tvNamaLembagaKeuangan,tvBakiDebet,tvKualitasPembiayaan,tvPerkiraanAngsuranBulanan,tvTreatmentPembiayaanEksisting;
        TextFieldBoxes tfVerifikasiFasilitas;
        EditText etHasilVerifikasiFasilitas;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvNamaLembagaKeuangan =binding.tvNamaLembagaKeuangan;
            tvBakiDebet=binding.tvBakiDebet;
            tvKualitasPembiayaan=binding.tvKualitasPembiayaan;
            tvPerkiraanAngsuranBulanan=binding.tvPerkiraanAngsuranBulanan;
            tvTreatmentPembiayaanEksisting=binding.tvTreatmentPembiayaanEksisting;
            tfVerifikasiFasilitas=binding.tfVerifikasiFasilitas;
            etHasilVerifikasiFasilitas=binding.etHasilVerifikasiFasilitas;


        }

    }
}
