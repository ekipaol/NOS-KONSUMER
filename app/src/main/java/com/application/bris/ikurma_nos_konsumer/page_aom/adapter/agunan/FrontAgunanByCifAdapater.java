package com.application.bris.ikurma_nos_konsumer.page_aom.adapter.agunan;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.AgunanByCifListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanTanahBangunanFront;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by PID on 4/26/2019.
 */

public class FrontAgunanByCifAdapater extends RecyclerView.Adapter<FrontAgunanByCifAdapater.PipelineViewHolder> {

    private Context context;
    private List<AgunanTanahBangunanFront> dataTanahBangunan;
    private AgunanByCifListener hotprospekListener;
    private String idAplikasi;
    private String idCif;
    private String status;

    public FrontAgunanByCifAdapater(Context context, List<AgunanTanahBangunanFront> data, AgunanByCifListener hotprospekListener, String idAplikasi, String idCif) {
        this.context = context;
        this.dataTanahBangunan = data;
        this.hotprospekListener = hotprospekListener;
        this.idAplikasi = idAplikasi;
        this.idCif = idCif;
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_agunan_by_cif, parent, false);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PipelineViewHolder holder, int position) {
        final AgunanTanahBangunanFront data = dataTanahBangunan.get(position);
        if (data.getSTATUS() == 1) {
            status = "Sudah Terikat";
        } else {
            status = "Belum Terikat";
            holder.cv_hotprospek_front.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(data.getUuid()!=null&&!data.getUuid().isEmpty()){
                        hotprospekListener.onAgunanByCifSelect(idAplikasi, idCif, data.getFID_AGUNAN(), "tanah dan bangunan",data.getUuid());
                    }
                    else{
                        hotprospekListener.onAgunanByCifSelect(idAplikasi, idCif, data.getFID_AGUNAN(), "tanah dan bangunan");
                    }
                }
            });


        }

        if(data.getFID_AGUNAN().equalsIgnoreCase("0")){
            holder.tv_id_agunan.setText("-");
            holder.tv_status.setText("Data Lokal");
            holder.iv_question.setVisibility(View.VISIBLE);

        }
        else{
            holder.tv_id_agunan.setText(data.getFID_AGUNAN());
            holder.tv_status.setText(status);
        }

        holder.tv_no_stf.setText(data.getNO_SERTIFIKAT_TANAH());
        holder.tv_jenis_surat.setText(data.getJENIS_SURAT_TANAH());
        holder.tv_lokasi.setText(data.getLOKASI());
        holder.tv_harga_pasar.setText(AppUtil.parseRupiah(data.getNILAI_PASAR()));

        holder.iv_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetAlertDialog dialog=new SweetAlertDialog(context,SweetAlertDialog.NORMAL_TYPE);
                dialog.setContentText("Data agunan ini sudah tersimpan di penyimpanan lokal perangkat anda, dan belum disimpan di sistem i-Kurma. Klik untuk melanjutkan mengisi data agunan.\n");
                dialog.setConfirmText("Oke");
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.dismissWithAnimation();
                    }
                });
                dialog.show();
            }
        });

        holder.rl_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetAlertDialog dialog=new SweetAlertDialog(context,SweetAlertDialog.NORMAL_TYPE);
                dialog.setContentText("Data agunan ini sudah tersimpan di penyimpanan lokal perangkat anda, dan belum disimpan di sistem i-Kurma. Klik untuk melanjutkan mengisi data agunan.\n");
                dialog.setConfirmText("Oke");
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.dismissWithAnimation();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataTanahBangunan == null) ? 0 : dataTanahBangunan.size();
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private CardView cv_hotprospek_front;
        private TextView tv_status, tv_id_agunan, tv_no_stf, tv_jenis_surat, tv_lokasi, tv_harga_pasar;
        private ImageView iv_question;
        private RelativeLayout rl_question;


        public PipelineViewHolder(View itemView) {
            super(itemView);
            cv_hotprospek_front = (CardView) itemView.findViewById(R.id.cv_hotprospek_front);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            tv_id_agunan = (TextView) itemView.findViewById(R.id.tv_id_agunan);
            tv_no_stf = (TextView) itemView.findViewById(R.id.tv_no_stf);
            tv_jenis_surat = (TextView) itemView.findViewById(R.id.tv_jenis_surat);
            tv_lokasi = (TextView) itemView.findViewById(R.id.tv_lokasi);
            tv_harga_pasar = (TextView) itemView.findViewById(R.id.tv_harga_pasar);
            iv_question=(ImageView) itemView.findViewById(R.id.iv_question);
            rl_question=(RelativeLayout) itemView.findViewById(R.id.rl_question);

        }
    }
}
