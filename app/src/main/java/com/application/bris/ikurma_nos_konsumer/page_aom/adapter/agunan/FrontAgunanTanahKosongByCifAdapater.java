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
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanTanahKosongFront;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class FrontAgunanTanahKosongByCifAdapater extends RecyclerView.Adapter<FrontAgunanTanahKosongByCifAdapater.PipelineViewHolder> {

    private Context context;
    private List<AgunanTanahKosongFront> dataTanahBangunan;
    private AgunanByCifListener hotprospekListener;
    private String idAplikasi;
    private String idCif;
    private String status;

    public FrontAgunanTanahKosongByCifAdapater(Context context, List<AgunanTanahKosongFront> data, AgunanByCifListener hotprospekListener, String idAplikasi, String idCif) {
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
        View view = layoutInflater.inflate(R.layout.item_agunan_tanah_kosong_by_cif, parent, false);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PipelineViewHolder holder, int position) {
        final AgunanTanahKosongFront data = dataTanahBangunan.get(position);
        if (data.getSTATUS() == 1) {
            status = "Sudah Terikat";
        } else {
            status = "Belum Terikat";
            holder.cv_hotprospek_front.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hotprospekListener.onAgunanByCifSelect(idAplikasi, idCif, data.getFID_AGUNAN(), "tanah kosong");
                }
            });
        }
        holder.tv_status.setText(status);
        holder.tv_id_agunan.setText(String.valueOf(data.getFID_AGUNAN()));
        holder.tv_no_bukti_hak.setText(data.getNO_BUKTI_HAK());
        holder.tv_nama.setText(data.getNAMA_PEMEGANG_HAK());
        holder.tv_jenis_dokumen.setText(data.getJENIS_DOKUMEN());
        holder.tv_alamat.setText(data.getALAMAT_JAMINAN());
        holder.tv_harga_pasar.setText(AppUtil.parseRupiah(data.getNILAI_PASAR()));
    }

    @Override
    public int getItemCount() {
        return (dataTanahBangunan == null) ? 0 : dataTanahBangunan.size();
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private CardView cv_hotprospek_front;
        private TextView tv_status, tv_id_agunan, tv_no_bukti_hak, tv_nama, tv_jenis_dokumen, tv_alamat, tv_harga_pasar;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            cv_hotprospek_front = (CardView) itemView.findViewById(R.id.cv_hotprospek_front);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            tv_id_agunan = (TextView) itemView.findViewById(R.id.tv_id_agunan);
            tv_no_bukti_hak = (TextView) itemView.findViewById(R.id.tv_no_bukti_hak);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_jenis_dokumen = (TextView) itemView.findViewById(R.id.tv_jenis_dokumen);
            tv_alamat = (TextView) itemView.findViewById(R.id.tv_alamat);
            tv_harga_pasar = (TextView) itemView.findViewById(R.id.tv_harga_pasar);
        }
    }
}
