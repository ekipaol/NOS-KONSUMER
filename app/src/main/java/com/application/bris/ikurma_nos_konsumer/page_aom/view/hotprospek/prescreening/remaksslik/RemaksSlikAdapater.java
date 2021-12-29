package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.prescreening.remaksslik;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.SlikListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.prescreening.model.ModelRemaksSlik;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.KeyValue;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedDrawable;
import java.util.List;

/**
 * Created by idong on 17/09/2019.
 */

public class RemaksSlikAdapater extends RecyclerView.Adapter<RemaksSlikAdapater.TlViewHolder>{

    private Context context;
    private List<ModelRemaksSlik> data;
    private SlikListener slikListener;

    public RemaksSlikAdapater(Context context, List<ModelRemaksSlik> mdata, SlikListener slikListener) {
        this.context = context;
        this.data = mdata;
        this.slikListener = slikListener;
    }

    @NonNull
    @Override
    public TlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ao_item_remaksslik, parent, false);
        return new RemaksSlikAdapater.TlViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull final TlViewHolder holder, final int position) {
        final ModelRemaksSlik datas = data.get(position);
        holder.tv_bank.setText(datas.getkODEBANK()+" - "+datas.getnAMABANK());
        holder.tv_plafond.setText(AppUtil.parseRupiah(String.valueOf(datas.getpLAFOND())));
        holder.tv_outstanding.setText(AppUtil.parseRupiah(String.valueOf(datas.getoS())));
        holder.tv_angsuran.setText(AppUtil.parseRupiah(String.valueOf(datas.getaNGSURAN())));
        holder.tv_tanggalmulai.setText(AppUtil.parseTanggalGeneral(datas.gettANGGALMULAI(), "yyyyMMdd", "dd-MM-yyyy"));
        holder.tv_tanggaljatuhtempo.setText(AppUtil.parseTanggalGeneral(datas.gettANGGALJATUHTEMPO(), "yyyyMMdd", "dd-MM-yyyy"));
        holder.tv_kolektabilitas.setText(String.valueOf(datas.getkOL()));
        holder.tv_status.setText(datas.getkONDISIKET());
        holder.tv_remaks.setText(KeyValue.getKeyRemaksSlik(String.valueOf(datas.getrEMARK())));

        if (datas.getrEMARK() == 1){
           holder.ll_bukti_lunas.setVisibility(View.VISIBLE);
            Glide
                    .with(context)
                    .load(UriApi.Baseurl.URL+UriApi.foto.urlFile +datas.getfIDPHOTO())
                    .centerCrop()
                    .placeholder(R.drawable.banner_placeholder)
                    .into(holder.iv_foto);
        }

        holder.iv_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPreviewPhoto.display(((AppCompatActivity) context).getSupportFragmentManager(), "Bukti Lunas", ((RoundedDrawable)holder.iv_foto.getDrawable()).getSourceBitmap());
            }
        });

        holder.cv_remaks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datas.getkONDISI().equalsIgnoreCase("00")){
                    slikListener.onSelectSlik(data, datas, position);
                }
                else {
                    AppUtil.showToastShort(context, "Angsuran tidak bisa diRemarks karna sudah "+datas.getkONDISIKET());
                }
            }
        });

        if (!datas.getkONDISI().equalsIgnoreCase("00")){
            holder.tv_bank.setBackgroundColor(context.getResources().getColor(R.color.colorGreenSoft));
        }
        else{
            holder.tv_bank.setBackgroundColor(context.getResources().getColor(R.color.colorBackgroundGrey1));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class TlViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_bank, tv_plafond, tv_outstanding, tv_angsuran, tv_tanggalmulai, tv_tanggaljatuhtempo,
                        tv_kolektabilitas, tv_status, tv_remaks;
        private ImageView iv_foto;
        private CardView cv_remaks;
        private LinearLayout ll_bukti_lunas;

        public TlViewHolder(View view, int viewType) {
            super(view);
            tv_bank = view.findViewById(R.id.tv_bank);
            tv_plafond = view.findViewById(R.id.tv_plafond);
            tv_outstanding = view.findViewById(R.id.tv_outstanding);
            tv_angsuran = view.findViewById(R.id.tv_angsuran);
            tv_tanggalmulai = view.findViewById(R.id.tv_tanggalmulai);
            tv_tanggaljatuhtempo = view.findViewById(R.id.tv_tanggaljatuhtempo);
            tv_kolektabilitas = view.findViewById(R.id.tv_kolektabilitas);
            tv_status = view.findViewById(R.id.tv_status);
            tv_remaks = view.findViewById(R.id.tv_remaks);
            iv_foto = view.findViewById(R.id.iv_foto);
            cv_remaks = view.findViewById(R.id.cv_remaks);
            ll_bukti_lunas = view.findViewById(R.id.ll_bukti_lunas);
        }
    }
}
