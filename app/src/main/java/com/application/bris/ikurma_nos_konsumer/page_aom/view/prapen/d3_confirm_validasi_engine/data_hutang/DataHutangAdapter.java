package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_hutang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoItemDataHutangBinding;
import com.application.bris.ikurma_nos_konsumer.model.prapen.HapusDataHutang;
import com.application.bris.ikurma_nos_konsumer.model.prapen.UpdateDataHutang;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataHutang;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataHutangAdapter extends RecyclerView.Adapter<DataHutangAdapter.MenuViewHolder> {
    private List<DataHutang> data;
    private Context context;
    private PrapenAoItemDataHutangBinding binding;
    AppPreferences appPreferences;
    ApiClientAdapter apiClientAdapter;

    public DataHutangAdapter(Context context,List< DataHutang> mdata) {
        this.context = context;
        this.data = mdata;
        appPreferences=new AppPreferences(context);
        apiClientAdapter=new ApiClientAdapter(context);
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
                        updateDataHutang(position,dialog);
                    }
                });
                dialog.show();
            }
        });

    }

    public void updateDataHutang(int position,SweetAlertDialog dialog){
        dialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        dialog.setTitleText("Loading");
        dialog.setContentText("Harap Tunggu");

        HapusDataHutang req=new HapusDataHutang();
        req.setApplicationId(DataHutangActivity.idAplikasi);
        req.setUID(Integer.toString(appPreferences.getUid()));
        req.setUtang_Id(Long.parseLong(data.get(position).getId()));

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().hapusDataHutang(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            dialog.setTitleText("Berhasil");
                            dialog.setContentText("Data Berhasil Dihapus, Silahkan Refresh Halaman\n\n");
                            dialog.setConfirmText("OK");
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    dialog.dismissWithAnimation();
                                }
                            });

                        }
                        else{
                            dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            dialog.setTitleText("Gagal");
                            dialog.setContentText(response.body().getMessage());
                            dialog.setConfirmText("OK");
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    dialog.dismissWithAnimation();
                                }
                            });
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText("Gagal");
                        dialog.setContentText(error.getMessage());   dialog.setConfirmText("OK");
                        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                dialog.dismissWithAnimation();
                            }
                        });
                    }
                }
                catch (Exception e){
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("Gagal");
                dialog.setContentText("Gagal Terhubung ke Server");   dialog.setConfirmText("OK");
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.dismissWithAnimation();
                    }
                });
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