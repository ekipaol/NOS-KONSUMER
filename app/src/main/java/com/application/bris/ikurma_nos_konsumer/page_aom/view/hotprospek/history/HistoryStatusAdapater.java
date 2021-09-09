package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.history;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.history.model.ModelHistoryStatus;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

/**
 * Created by idong on 10/05/2019.
 */

public class HistoryStatusAdapater extends RecyclerView.Adapter<HistoryStatusAdapater.TlViewHolder>{

    private Context context;
    private List<ModelHistoryStatus> data;

    public HistoryStatusAdapater(Context context, List<ModelHistoryStatus> mdata) {
        this.context = context;
        this.data = mdata;
    }

    @NonNull
    @Override
    public TlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ao_item_historystatus, parent, false);
        return new HistoryStatusAdapater.TlViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull TlViewHolder holder, int position) {
        ModelHistoryStatus datas = data.get(position);

        holder.tv_namapegawai.setText(datas.getNama_pegawai());
        holder.tv_desc1.setText(datas.getDesc1());
        holder.tv_tanggalwaktu.setText(AppUtil.parseTanggalGeneral(datas.getTanggal()+" "+datas.getJam(), "ddMMyyyy HH:mm:ss", "EEEE, dd MMM yyyy HH:mm"));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    public class TlViewHolder extends RecyclerView.ViewHolder {
        private TimelineView tl_historystatus;
        private TextView tv_namapegawai;
        private TextView tv_desc1;
        private TextView tv_tanggalwaktu;

        public TlViewHolder(View view, int viewType) {
            super(view);
            tl_historystatus = view.findViewById(R.id.tl_historystatus);
            tl_historystatus.initLine(viewType);
            tv_namapegawai = view.findViewById(R.id.tv_namapegawai);
            tv_desc1 = view.findViewById(R.id.tv_desc1);
            tv_tanggalwaktu = view.findViewById(R.id.tv_tanggalwaktu);
        }
    }
}
