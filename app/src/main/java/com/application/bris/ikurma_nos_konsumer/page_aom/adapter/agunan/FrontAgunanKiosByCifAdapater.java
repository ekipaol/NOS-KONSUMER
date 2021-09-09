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
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanKiosFront;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class FrontAgunanKiosByCifAdapater extends RecyclerView.Adapter<FrontAgunanKiosByCifAdapater.PipelineViewHolder> {

    private Context context;
    private List<AgunanKiosFront> dataKios;
    private AgunanByCifListener agunanListener;
    private String idAplikasi;
    private String idCif;
    private String status;

    public FrontAgunanKiosByCifAdapater(Context context, List<AgunanKiosFront> data, AgunanByCifListener agunanListener, String idAplikasi, String idCif) {
        this.context = context;
        this.dataKios = data;
        this.agunanListener = agunanListener;
        this.idAplikasi = idAplikasi;
        this.idCif = idCif;
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_agunan_kios_by_cif, parent, false);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PipelineViewHolder holder, int position) {
        final AgunanKiosFront data = dataKios.get(position);
        if (data.getsTATUS() == 1) {
            status = "Sudah Terikat";
        }
        else {
            status = "Belum Terikat";
            holder.cv_hotprospek_front.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    agunanListener.onAgunanByCifSelect(idAplikasi, idCif, String.valueOf(data.getfIDAGUNAN()), "kios");
                }
            });
        }

        holder.tv_status.setText(status);
        holder.tv_idagunan.setText(String.valueOf(data.getfIDAGUNAN()));
        holder.tv_jenisagunan.setText(data.getjENISJAMINAN());
        holder.tv_namapemeganghak.setText(data.getnAMAPEMEGANGHAK());
        holder.tv_jenisdokumen.setText(data.getdOKUMEN());
        holder.tv_alamatjaminan.setText(data.getlOKASI());
        holder.tv_nilaipasar.setText(AppUtil.parseRupiah(data.getnILAIPASAR()));
    }

    @Override
    public int getItemCount() {
        return (dataKios == null) ? 0 : dataKios.size();
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private CardView cv_hotprospek_front;
        private TextView tv_status, tv_idagunan, tv_jenisagunan, tv_namapemeganghak, tv_jenisdokumen, tv_alamatjaminan, tv_nilaipasar;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            cv_hotprospek_front = (CardView) itemView.findViewById(R.id.cv_hotprospek_front);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            tv_idagunan = (TextView) itemView.findViewById(R.id.tv_idagunan);
            tv_jenisagunan = (TextView) itemView.findViewById(R.id.tv_jenisagunan);
            tv_namapemeganghak = (TextView) itemView.findViewById(R.id.tv_namapemeganghak);
            tv_jenisdokumen = (TextView) itemView.findViewById(R.id.tv_jenisdokumen);
            tv_alamatjaminan = (TextView) itemView.findViewById(R.id.tv_alamatjaminan);
            tv_nilaipasar = (TextView) itemView.findViewById(R.id.tv_nilaipasar);
        }
    }
}
