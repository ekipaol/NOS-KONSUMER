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
import com.application.bris.ikurma_nos_konsumer.page_aom.model.AgunanDepositoFront;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class FrontAgunanDepositoByCifAdapater extends RecyclerView.Adapter<FrontAgunanDepositoByCifAdapater.PipelineViewHolder> {

    private Context context;
    private List<AgunanDepositoFront> dataDeposito;
    private AgunanByCifListener agunanListener;
    private String idAplikasi;
    private String idCif;
    private String status;

    public FrontAgunanDepositoByCifAdapater(Context context, List<AgunanDepositoFront> data, AgunanByCifListener agunanListener, String idAplikasi, String idCif) {
        this.context = context;
        this.dataDeposito = data;
        this.agunanListener = agunanListener;
        this.idAplikasi = idAplikasi;
        this.idCif = idCif;
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_agunan_deposito_by_cif, parent, false);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PipelineViewHolder holder, int position) {
        final AgunanDepositoFront data = dataDeposito.get(position);
        if (data.getsTATUS() == 1) {
            status = "Sudah Terikat";
        }
        else {
            status = "Belum Terikat";
            holder.cv_hotprospek_front.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    agunanListener.onAgunanByCifSelect(idAplikasi, idCif, String.valueOf(data.getfIDAGUNAN()), "deposito");
                }
            });
        }

        holder.tv_status.setText(status);
        holder.tv_idagunan.setText(String.valueOf(data.getfIDAGUNAN()));
        holder.tv_jenisdeposito.setText(data.getjENISDEPOSITO());
        holder.tv_namapemilik.setText(data.getnAMAPEMILIK());
        holder.tv_nomorbilyet.setText(data.getnOBILYET());
        holder.tv_bankpenerbit.setText(data.getbANKPENERBIT());

        if (data.getjENISDEPOSITO().equalsIgnoreCase("Deposito Rupiah")){
            holder.tv_nilainominal.setText(AppUtil.parseRupiah(data.getnILAIPASAR()));
        }
        else {
            holder.tv_nilainominal.setText(AppUtil.parseValas(data.getnILAIPASAR()));
        }
    }

    @Override
    public int getItemCount() {
        return (dataDeposito == null) ? 0 : dataDeposito.size();
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private CardView cv_hotprospek_front;
        private TextView tv_status, tv_idagunan, tv_jenisdeposito, tv_namapemilik, tv_nomorbilyet, tv_bankpenerbit, tv_nilainominal;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            cv_hotprospek_front = (CardView) itemView.findViewById(R.id.cv_hotprospek_front);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            tv_idagunan = (TextView) itemView.findViewById(R.id.tv_idagunan);
            tv_jenisdeposito = (TextView) itemView.findViewById(R.id.tv_jenisdeposito);
            tv_namapemilik = (TextView) itemView.findViewById(R.id.tv_namapemilik);
            tv_nomorbilyet = (TextView) itemView.findViewById(R.id.tv_nomorbilyet);
            tv_bankpenerbit = (TextView) itemView.findViewById(R.id.tv_bankpenerbit);
            tv_nilainominal = (TextView) itemView.findViewById(R.id.tv_nilainominal);
        }
    }
}
