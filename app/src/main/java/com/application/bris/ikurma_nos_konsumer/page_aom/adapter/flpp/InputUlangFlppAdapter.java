package com.application.bris.ikurma_nos_konsumer.page_aom.adapter.flpp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.flpp.ReqSimpanKonfirmasiFlpp;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.Flpp;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.pipeline.kpr.KonsumerKprPipelineActivity;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputUlangFlppAdapter extends RecyclerView.Adapter<InputUlangFlppAdapter.PipelineViewHolder> implements Filterable {

    private Context context;
    private List<Flpp> data;
    private List<Flpp> datafiltered;
    AppPreferences appPreferences;
    ApiClientAdapter apiClientAdapter;

    public InputUlangFlppAdapter(Context context, List<Flpp> data) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;
        appPreferences=new AppPreferences(context);
        apiClientAdapter=new ApiClientAdapter(context);
    }

    @NonNull
    @Override
    public InputUlangFlppAdapter.PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ao_item_konfirmasi_flpp, parent, false);
        return new InputUlangFlppAdapter.PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final InputUlangFlppAdapter.PipelineViewHolder holder, final int position) {
        final Flpp datas = datafiltered.get(position);


        holder.tv_nama.setText(datas.getNamaDebitur());
        holder.tv_pengembang.setText(datas.getNamaPengembang());
        holder.tv_perumahan.setText(datas.getNamaPerumahan());
        holder.tv_provinsi.setText(datas.getPROVINSI());
        holder.tv_kota.setText(datas.getKOTA());
        holder.tv_kecamatan.setText(datas.getKECAMATAN());
        holder.btn_process.setText("Kirim Ulang Ke Pipeline");

        if(datas.getKECAMATAN().equalsIgnoreCase(datas.getKELURAHAN())){
            holder.tv_kelurahan.setText("-");
        }
        else{
            holder.tv_kelurahan.setText(datas.getKELURAHAN());
        }

        holder.btn_process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              SweetAlertDialog dialog=new SweetAlertDialog(context,SweetAlertDialog.NORMAL_TYPE);
                dialog.setTitleText("Konfirmasi");
                dialog.setContentText("Apakah anda yakin ingin menambahkan data nasabah : "+datafiltered.get(position).getNamaDebitur()+" ke halaman pipeline? \n");
                dialog.setConfirmText("OK");
                dialog.setCancelText("Batal");
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                     kirimUlangPipelineFlpp(position,dialog);

                    }
                });
                dialog.show();
            }
        });


    }

    private void kirimUlangPipelineFlpp(int i, SweetAlertDialog dialog){

        dialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);

            ReqSimpanKonfirmasiFlpp req = new ReqSimpanKonfirmasiFlpp();

            //dikirim untuk pipeline

            req.setUid(Integer.toString(appPreferences.getUid()));
            req.setId(0);
            req.setJenisProduk("KPR");
            req.setLokasiGps("0.0 0.0");
            req.setSegmen("KONSUMER");
            req.setTenor(12); //pantek tenor 12 bulan, karena minimal segitu di service pipeline
            req.setNamaNasabah(datafiltered.get(i).getNamaDebitur());
            req.setGimmick(222); //gimmick dipantek karena ini flpp
            req.setNoKtp(datafiltered.get(i).getKtpDebitur());
            req.setNoHp(datafiltered.get(i).getNoPonsel());
            req.setFidPhoto(0);//pantek id photo 0 dulu, nanti langsung upload aja ke tabel brisi
            req.setRencanaPlafond(0l);
            req.setJenisKPR("Karyawan / PNS Program FLPP");//flpp hanya support karyawan pns flpp
            req.setFidTujuanPenggunaan(25);//pantekan tujuan penggunaan
            req.setDescTujuanPenggunaan(" ");
            req.setJenisUsaha("9999");
            req.setJenisTindak("VISIT");
            req.setTindakLanjut("Input ulang data FLPP");

            Call<ParseResponse> call = apiClientAdapter.getApiInterface().updatePipelineFlpp(req);
            call.enqueue(new Callback<ParseResponse>() {
                @Override
                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                    try {
                        if(response.isSuccessful()){


                            if(response.body().getStatus().equalsIgnoreCase("00")){
                                dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//                            String keterangan = response.body().getData().get("KETERANGAN").toString();

//                                    CustomDialog.DialogSuccess(context, "Success!", "Berhasil Melakukan Konfirmasi, Silahkan Lanjutkan di Menu Pipeline Anda", dialog.getContext());

                                dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                dialog.setTitleText("Success");
                                dialog.setContentText("Berhasil Melakukan Konfirmasi, Klik OK untuk melanjutkan ke Menu Pipeline\n");
                                dialog.setConfirmText("OK");
                                dialog.setCancelText("Tutup");
                                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        dialog.dismissWithAnimation();
                                        Intent intent=new Intent(context, KonsumerKprPipelineActivity.class);
                                        context.startActivity(intent);

                                    }
                                });

                            }
                            else{
                                dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                dialog.setTitleText("Gagal");
                                dialog.setContentText(response.body().getMessage()+"\n");
                                dialog.setConfirmText("OK");
                                dialog.showCancelButton(false);
                                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        dialog.dismissWithAnimation();
                                    }
                                });
                                //AppUtil.notiferror(context, dialog.findViewById(android.R.id.content),response.body().getMessage() );
                            }
                        }
                        else{
                            Error error = ParseResponseError.confirmEror(response.errorBody());
                            dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            dialog.setTitleText("Gagal");
                            dialog.setContentText(response.body().getMessage());
                            dialog.setConfirmText("OK");
                            dialog.showCancelButton(false);
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    dialog.dismissWithAnimation();
                                }
                            });
                        }
                    }
                    catch (Exception e){
                        dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText("Gagal");
                        dialog.setContentText("Terjadi kesalahan pada server\n");
                        dialog.setConfirmText("OK");
                        dialog.showCancelButton(false);
                        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                dialog.dismissWithAnimation();
                            }
                        });
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ParseResponse> call, Throwable t) {
                    dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    dialog.setTitleText("Gagal");
                    dialog.setContentText("Terjadi kesalahan pada server");
                    dialog.setConfirmText("OK");
                    dialog.showCancelButton(false);
                    dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            dialog.dismissWithAnimation();
                        }
                    });
                }
            });

    }

    private void cekStatusFlpp(int i, SweetAlertDialog dialog){

        dialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);

        ReqSimpanKonfirmasiFlpp req = new ReqSimpanKonfirmasiFlpp();

        //dikirim untuk pipeline

        req.setUid(Integer.toString(appPreferences.getUid()));
        req.setId(0);
        req.setJenisProduk("KPR");
        req.setLokasiGps("0.0 0.0");
        req.setSegmen("KONSUMER");
        req.setTenor(12); //pantek tenor 12 bulan, karena minimal segitu di service pipeline
        req.setNamaNasabah(datafiltered.get(i).getNamaDebitur());
        req.setGimmick(222); //gimmick dipantek karena ini flpp
        req.setNoKtp(datafiltered.get(i).getKtpDebitur());
        req.setNoHp(datafiltered.get(i).getNoPonsel());
        req.setFidPhoto(0);//pantek id photo 0 dulu, nanti langsung upload aja ke tabel brisi
        req.setRencanaPlafond(0l);
        req.setJenisKPR("Karyawan / PNS Program FLPP");//flpp hanya support karyawan pns flpp
        req.setFidTujuanPenggunaan(25);//pantekan tujuan penggunaan
        req.setDescTujuanPenggunaan(" ");
        req.setJenisUsaha("9999");
        req.setJenisTindak("VISIT");
        req.setTindakLanjut("Input ulang data FLPP");

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updatePipelineFlpp(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                try {
                    if(response.isSuccessful()){


                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//                            String keterangan = response.body().getData().get("KETERANGAN").toString();

//                                    CustomDialog.DialogSuccess(context, "Success!", "Berhasil Melakukan Konfirmasi, Silahkan Lanjutkan di Menu Pipeline Anda", dialog.getContext());

                            dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            dialog.setTitleText("Success");
                            dialog.setContentText("Berhasil Melakukan Konfirmasi, Klik OK untuk melanjutkan ke Menu Pipeline\n");
                            dialog.setConfirmText("OK");
                            dialog.setCancelText("Tutup");
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    dialog.dismissWithAnimation();
                                    Intent intent=new Intent(context, KonsumerKprPipelineActivity.class);
                                    context.startActivity(intent);

                                }
                            });

                        }
                        else{
                            dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            dialog.setTitleText("Gagal");
                            dialog.setContentText(response.body().getMessage()+"\n");
                            dialog.setConfirmText("OK");
                            dialog.showCancelButton(false);
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    dialog.dismissWithAnimation();
                                }
                            });
                            //AppUtil.notiferror(context, dialog.findViewById(android.R.id.content),response.body().getMessage() );
                        }
                    }
                    else{
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText("Gagal");
                        dialog.setContentText(response.body().getMessage());
                        dialog.setConfirmText("OK");
                        dialog.showCancelButton(false);
                        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                dialog.dismissWithAnimation();
                            }
                        });
                    }
                }
                catch (Exception e){
                    dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    dialog.setTitleText("Gagal");
                    dialog.setContentText("Terjadi kesalahan pada server\n");
                    dialog.setConfirmText("OK");
                    dialog.showCancelButton(false);
                    dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            dialog.dismissWithAnimation();
                        }
                    });
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("Gagal");
                dialog.setContentText("Terjadi kesalahan pada server");
                dialog.setConfirmText("OK");
                dialog.showCancelButton(false);
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
        return datafiltered.size();
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
                    List<Flpp> filteredList = new ArrayList<>();
                    for (Flpp row : data){
                        if(row.getNamaDebitur().toLowerCase().contains(charString.toLowerCase()) || row.getKELURAHAN().toLowerCase().contains(charString.toLowerCase())|| row.getPROVINSI().toLowerCase().contains(charString.toLowerCase())|| row.getNamaPengembang().toLowerCase().contains(charString.toLowerCase()) || row.getKECAMATAN().toLowerCase().contains(charString.toLowerCase()) || row.getKOTA().toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<Flpp>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_nama, tv_pengembang, tv_perumahan, tv_provinsi, tv_kota,tv_kecamatan,tv_kelurahan;
        private Button btn_process;

        public PipelineViewHolder(View itemView) {
            super(itemView);

            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama_nasabah);
            tv_pengembang = (TextView) itemView.findViewById(R.id.tv_nama_pengembang);
            tv_perumahan = (TextView) itemView.findViewById(R.id.tv_nama_perumahan);
            tv_provinsi = (TextView) itemView.findViewById(R.id.tv_provinsi);
            tv_kota = (TextView) itemView.findViewById(R.id.tv_kota);
            tv_kecamatan = (TextView) itemView.findViewById(R.id.tv_kecamatan);
            tv_kelurahan = (TextView) itemView.findViewById(R.id.tv_kelurahan);
            btn_process = (Button) itemView.findViewById(R.id.bt_detil_followup_flpp);
        }
    }
}
