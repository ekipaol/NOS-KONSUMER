package com.application.bris.ikurma_nos_konsumer.page_aom.adapter.agunan;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.AgunanByCifListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanKendaraanFront;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class FrontAgunanKendaraanByCifAdapater extends RecyclerView.Adapter<FrontAgunanKendaraanByCifAdapater.PipelineViewHolder> {

    private Context context;
    private List<AgunanKendaraanFront> dataKendaraaan;
    private AgunanByCifListener hotprospekListener;
    private String idAplikasi;
    private String idCif;
    private String status;

    public FrontAgunanKendaraanByCifAdapater(Context context, List<AgunanKendaraanFront> data, AgunanByCifListener hotprospekListener, String idAplikasi, String idCif) {
        this.context = context;
        this.dataKendaraaan = data;
        this.hotprospekListener = hotprospekListener;
        this.idAplikasi = idAplikasi;
        this.idCif = idCif;
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_agunan_kendaraan_by_cif, parent, false);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PipelineViewHolder holder, int position) {
        final AgunanKendaraanFront data = dataKendaraaan.get(position);
        if (data.getSTATUS() == 1) {
            status = "Sudah Terikat";
        } else if (data.getSTATUS() == 0) {
            status = "Belum Terikat";
            holder.cv_hotprospek_front.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hotprospekListener.onAgunanByCifSelect(idAplikasi, idCif, data.getFID_AGUNAN(), "kendaraan");
                }
            });
        }
        holder.tv_status.setText(status);
        holder.tv_id_agunan.setText(String.valueOf(data.getFID_AGUNAN()));
        holder.tv_jenis_kendaraan.setText(data.getJENIS_KENDARAAN());
        holder.tv_nomor_bpkb.setText(data.getNOMOR_BPKB());
        holder.tv_nama_bpkb.setText(data.getNAMA_PEMILIK_BPKB());
        holder.tv_model_kendaraan.setText(data.getMODEL_KENDARAAN());
        holder.tv_harga_pasar.setText(AppUtil.parseRupiah(data.getNILAI_PASAR()));

    }

    @Override
    public int getItemCount() {
        return (dataKendaraaan == null) ? 0 : dataKendaraaan.size();
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private CardView cv_hotprospek_front;
        private TextView tv_status, tv_id_agunan, tv_jenis_kendaraan, tv_nomor_bpkb, tv_nama_bpkb, tv_model_kendaraan, tv_harga_pasar;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            cv_hotprospek_front = (CardView) itemView.findViewById(R.id.cv_hotprospek_front);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            tv_id_agunan = (TextView) itemView.findViewById(R.id.tv_id_agunan);
            tv_jenis_kendaraan = (TextView) itemView.findViewById(R.id.tv_jenis_kendaraan);
            tv_nomor_bpkb = (TextView) itemView.findViewById(R.id.tv_nomor_bpkb);
            tv_nama_bpkb = (TextView) itemView.findViewById(R.id.tv_nama_bpkb);
            tv_model_kendaraan = (TextView) itemView.findViewById(R.id.tv_model_kendaraan);
            tv_harga_pasar = (TextView) itemView.findViewById(R.id.tv_harga_pasar);
        }
    }
}
