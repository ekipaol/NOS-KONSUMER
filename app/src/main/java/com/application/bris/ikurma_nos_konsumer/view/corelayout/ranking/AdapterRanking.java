package com.application.bris.ikurma_nos_konsumer.view.corelayout.ranking;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.model.monitoring.AoRanking;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterRanking extends RecyclerView.Adapter<AdapterRanking.PipelineViewHolder> {

    private Context context;
    private List<AoRanking> data;
    private boolean isBottom;
    private ScrollView mScrollView;


    public AdapterRanking(Context context, List<AoRanking> data,boolean statusBottom,ScrollView scrollView) {
        this.context = context;
        this.data = data;
        isBottom=statusBottom;
        mScrollView=scrollView;

    }

    @NonNull
    @Override
    public AdapterRanking.PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_ranking_ao_top10, parent, false);
        return new AdapterRanking.PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRanking.PipelineViewHolder holder, int position) {
        final AoRanking datas = data.get(position);

        if(datas.getNAMA_PEGAWAI()==null){
            holder.tv_nama.setText("");
        }
        else{
            holder.tv_nama.setText(datas.getNAMA_PEGAWAI().toUpperCase());
        }
        holder.tv_kantor.setText(datas.getNAMA_CABANG());
        holder.tv_nomor.setText(Integer.toString(position+1));

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        Double pencairan=Double.parseDouble(datas.getTOTAL_PENCAIRAN());
        Double os=Double.parseDouble(datas.getTOTAL_OS());
        Double dpk=Double.parseDouble(datas.getTOTAL_DPK());
        Double npf=Double.parseDouble(datas.getTOTAL_NPF());
        Double kol2=Double.parseDouble(datas.getTOTAL_KOL2());

        holder.tv_pencairan.setText(df.format(pencairan)+"%");
        holder.tv_os.setText(df.format(os)+"%");
        holder.tv_dpk.setText(df.format(dpk)+"%");
        holder.tv_kol2.setText(df.format(kol2)+"%");
        holder.tv_npf.setText(df.format(npf)+"%");


        //kalau dia list untuk 10 terbawah, maka peringkat 1-3 gausah dikasi border special
        if(isBottom){
            holder.ll_item_ranking_ao.setBackground(context.getResources().getDrawable(R.drawable.shapeborder));
        }
        else{
            if(position==0){
                holder.ll_item_ranking_ao.setBackground(context.getResources().getDrawable(R.drawable.border_gold));
            }
            else if(position==1){
                holder.ll_item_ranking_ao.setBackground(context.getResources().getDrawable(R.drawable.border_silver));
            }
            else if(position==2){
                holder.ll_item_ranking_ao.setBackground(context.getResources().getDrawable(R.drawable.border_bronze));
            }
            else{
                holder.ll_item_ranking_ao.setBackground(context.getResources().getDrawable(R.drawable.shapeborder));
            }
        }



        holder.bt_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.bt_more.setVisibility(View.GONE);
                holder.bt_hide.setVisibility(View.VISIBLE);
                holder.ll_detail_ao.setVisibility(View.VISIBLE);


                //autoscroller, masih belom bisa
//                mScrollView.smoothScrollTo(0, holder.cv_ranking.getBottom());
//                mScrollView.post(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        holder.cv_ranking.requestFocus();
//                                    }
//                                }
//                );
            }
        });


        holder.bt_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.bt_more.setVisibility(View.VISIBLE);
                holder.bt_hide.setVisibility(View.GONE);
                holder.ll_detail_ao.setVisibility(View.GONE);
            }
        });


        holder.cv_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.bt_more.getVisibility()==View.VISIBLE){
                    holder.bt_more.setVisibility(View.GONE);
                    holder.bt_hide.setVisibility(View.VISIBLE);
                    holder.ll_detail_ao.setVisibility(View.VISIBLE);

                    //autoscroller, masih belom bisa
//                    mScrollView.smoothScrollTo(0, holder.cv_ranking.getBottom());
//                    mScrollView.post(new Runnable() {
//                                         @Override
//                                         public void run() {
//                                             holder.cv_ranking.requestFocus();
//                                         }
//                                     }
//                    );
                }
                else{
                    holder.bt_more.setVisibility(View.VISIBLE);
                    holder.bt_hide.setVisibility(View.GONE);
                    holder.ll_detail_ao.setVisibility(View.GONE);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return (data == null) ? 0 : data.size();
    }


    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_kantor, tv_nama, tv_os, tv_pencairan, tv_dpk,tv_kol2,tv_nomor,tv_npf;
        private ImageView bt_more,bt_hide;
        private LinearLayout ll_detail_ao,ll_item_ranking_ao;
        private CardView cv_ranking;
        public PipelineViewHolder(View itemView) {
            super(itemView);

            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_nomor = (TextView) itemView.findViewById(R.id.tv_nomor_urutan_ranking);

            tv_kantor = (TextView) itemView.findViewById(R.id.tv_nama_kantor);
            tv_pencairan = (TextView) itemView.findViewById(R.id.tv_pencairan_nasabah);
            tv_os = (TextView) itemView.findViewById(R.id.tv_outstanding_nasabah);
            tv_dpk = (TextView) itemView.findViewById(R.id.tv_dpk_nasabah);
            tv_kol2 = (TextView) itemView.findViewById(R.id.tv_kol_nasabah);
            tv_npf = (TextView) itemView.findViewById(R.id.tv_npf);

            bt_more = (ImageView) itemView.findViewById(R.id.id_show_more_ranking);
            bt_hide = (ImageView) itemView.findViewById(R.id.iv_show_less_ranking);

            ll_detail_ao = (LinearLayout) itemView.findViewById(R.id.ll_detail_ranking_ao);
            ll_item_ranking_ao = (LinearLayout) itemView.findViewById(R.id.ll_item_ranking_ao);

            cv_ranking = (CardView) itemView.findViewById(R.id.cv_ranking_ao);

            bt_hide.setVisibility(View.GONE);
            ll_detail_ao.setVisibility(View.GONE);

        }
    }
}