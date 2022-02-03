package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_ideb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.databinding.PrapenItemVerifikasiHutangBinding;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenItemVerifikasiIdebBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.DropdownRecyclerListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiHutang;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiIdeb;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;

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
        holder.etHasilVerifikasi.setFocusable(false);
        holder.etAngsuranBulanan.setFocusable(false);
        holder.etHasilVerifikasi.setText(data.get(position).getTreatmentFasilitas());
        holder.etHasilVerifikasiFasilitas.setText(data.get(position).getTreatmentPembiayaan());
        holder.tvNamaLembagaKeuangan.setText(data.get(position).getNamaLembagaKeuangan());
        holder.tvKualitasPembiayaan.setText(data.get(position).getKualitasPembiayaan());
        holder.tvBakiDebet.setText(AppUtil.parseRupiah(data.get(position).getBakiDebetTerakhir()));
        holder.tvPerkiraanAngsuranBulanan.setText(AppUtil.parseRupiah(data.get(position).getPerkiraanAngsuranBulanan()));
        holder.tvTreatmentPembiayaanEksisting.setText(data.get(position).getTreatmentPembiayaan());
        holder.tvStatus.setText(data.get(position).getStatusIdeb());
        holder.tvJenisKredit.setText(data.get(position).getJenisKredit());
        holder.etAngsuranBulanan.setText(AppUtil.parseRupiah(data.get(position).getAngsuranVerifikasi()));

        holder.etAngsuranBulanan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(holder.etAngsuranBulanan));
        onClicks(position,holder);

        try{
            if(data.get(position).getDokumen().getFile_Name().substring(data.get(position).getDokumen().getFile_Name().length()-3,data.get(position).getDokumen().getFile_Name().length()).equalsIgnoreCase("pdf")){
                AppUtil.convertBase64ToFileWithOnClick(context,data.get(position).getDokumen().getImg(),holder.ivDokumen,data.get(position).getDokumen().getFile_Name());
            }
            else{
                AppUtil.convertBase64ToImage(data.get(position).getDokumen().getImg(),holder.ivDokumen);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }



    }

    private void onClicks(int currentPosition,@NonNull VerifikasiIdebAdapter.MenuViewHolder holder){

        //VERIFIKASI FASILITAS
//        holder.tfVerifikasiFasilitas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition,binding.tfVerifikasiFasilitas.getLabelText());
//            }
//        });
//
//        holder.etHasilVerifikasiFasilitas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition,binding.tfVerifikasiFasilitas.getLabelText());
//            }
//        });
//
//        holder.tfVerifikasiFasilitas.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition,binding.tfVerifikasiFasilitas.getLabelText());
//            }
//        });
//
//
//        //HASIL VERIFIKASI
//        holder.tfHasilVerifikasi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition,binding.tfHasilVerifikasi.getLabelText());
//            }
//        });
//
//        holder.etHasilVerifikasi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition,binding.tfHasilVerifikasi.getLabelText());
//            }
//        });
//
//        holder.tfHasilVerifikasi.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dropdownRecyclerListener.onDropdownRecyclerClick(currentPosition,binding.tfHasilVerifikasi.getLabelText());
//            }
//        });


    }


    @Override
    public int getItemCount() {
        return data.size();
    }



    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaLembagaKeuangan,tvBakiDebet,tvKualitasPembiayaan,tvPerkiraanAngsuranBulanan,tvTreatmentPembiayaanEksisting,tvStatus,tvJenisKredit;
        TextFieldBoxes tfVerifikasiFasilitas,tfHasilVerifikasi;
        EditText etHasilVerifikasiFasilitas,etHasilVerifikasi,etAngsuranBulanan;

        ImageView ivDokumen;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvNamaLembagaKeuangan =binding.tvNamaLembagaKeuangan;
            tvBakiDebet=binding.tvBakiDebet;
            tvKualitasPembiayaan=binding.tvKualitasPembiayaan;
            tvPerkiraanAngsuranBulanan=binding.tvPerkiraanAngsuranBulanan;
            tvTreatmentPembiayaanEksisting=binding.tvTreatmentPembiayaanEksisting;
            tfVerifikasiFasilitas=binding.tfVerifikasiFasilitas;
            etHasilVerifikasiFasilitas=binding.etHasilVerifikasiFasilitas;
            tfHasilVerifikasi=binding.tfHasilVerifikasi;
            etHasilVerifikasi=binding.etHasilVerifikasi;
            etAngsuranBulanan=binding.etAngsuranVerifikator;
            tvStatus=binding.tvStatus;
            tvJenisKredit=binding.tvJenisKredit;
            ivDokumen=binding.ivDokumen;


        }

    }
}
