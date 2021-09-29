package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_hutang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoItemDataHutangBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataHutang;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DataHutangAdapter extends RecyclerView.Adapter<DataHutangAdapter.MenuViewHolder> {
    private List<DataHutang> data;
    private Context context;
    private PrapenAoItemDataHutangBinding binding;

    public DataHutangAdapter(Context context,List< DataHutang> mdata) {
        this.context = context;
        this.data = mdata;
    }

    @NonNull
    @Override
    public DataHutangAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding=PrapenAoItemDataHutangBinding.inflate(layoutInflater,parent,false);
        View view = binding.getRoot();
        return new DataHutangAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHutangAdapter.MenuViewHolder holder, final int position) {
        AppUtil.logSecure("bangke",data.get(position).getNamaPemberiHutang());
        holder.tvNamaPemberiHutang.setText(data.get(position).getNamaPemberiHutang());
        holder.tvNominalPinjaman.setText(AppUtil.parseRupiah(data.get(position).getNominalPinjaman()));
        holder.tvSisaJangkaWaktu.setText(data.get(position).getSisaJangkaWaktu()+" Bulan");
        holder.tvAngsuranBulanan.setText(AppUtil.parseRupiah(data.get(position).getAngsuranBulanan()));
        holder.tvTreatmentPembiayaanEksisting.setText(data.get(position).getTreatmentPembiayaan());

        holder.btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "clicking", Toast.LENGTH_SHORT).show();
                final SweetAlertDialog dialog=new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
                dialog.setTitleText("Konfirmasi?");
                dialog.setContentText("Anda yakin ingin menghapus data ini?\n");
                dialog.setConfirmText("Ya");
                dialog.setCancelText("Tidak");
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        //do the delete operation
//                        Toast.makeText(context, "Deleting : "+data.get(position).getNamaPemberiHutang(), Toast.LENGTH_SHORT).show();
                        data.remove(position);
//                        notifyItemRemoved(position);
                        notifyDataSetChanged();
                        dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        dialog.setTitleText("Berhasil");
                        dialog.setContentText("Data Berhasil Dihapus");
                        dialog.showCancelButton(false);
                        dialog.setConfirmText("OK");
                        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                dialog.dismissWithAnimation();
                            }
                        });


                    }
                });
                dialog.show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaPemberiHutang,tvNominalPinjaman,tvSisaJangkaWaktu,tvAngsuranBulanan,tvTreatmentPembiayaanEksisting;
        Button btnhapus;

        public MenuViewHolder(View itemView) {
            super(itemView);
            btnhapus=binding.btnHapus;
            tvNamaPemberiHutang=binding.tvNamaPemberiHutang;
            tvNominalPinjaman=binding.tvNominalPinjaman;
            tvSisaJangkaWaktu=binding.tvSisaJangkaWaktu;
            tvAngsuranBulanan=binding.tvAngsuranBulanan;
            tvTreatmentPembiayaanEksisting=binding.tvTreatmentPembiayaanEksisting;


        }

    }

}