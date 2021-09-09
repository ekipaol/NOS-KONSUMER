package com.application.bris.ikurma_nos_konsumer.page_aom.adapter.tindaklanjut;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.tindaklanjut;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

/**
 * Created by idong on 10/05/2019.
 */

public class TindaklanjutAdapater extends RecyclerView.Adapter<TindaklanjutAdapater.TlViewHolder>{

    private Context context;
    private List<tindaklanjut> data;

    public TindaklanjutAdapater(Context context, List<tindaklanjut> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public TlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_timeline, parent, false);
        return new TindaklanjutAdapater.TlViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull TlViewHolder holder, int position) {
        tindaklanjut datas = data.get(position);
        holder.tv_doing.setText(datas.getTipe()+" - "+ AppUtil.parseDateWithName(datas.getDatetime())+" WIB");
        holder.tv_notice.setText(datas.getCatatan());
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
        private TimelineView tl_tindaklanjut;
        private TextView tv_doing;
        private TextView tv_notice;

        public TlViewHolder(View view, int viewType) {
            super(view);
            tl_tindaklanjut = view.findViewById(R.id.tl_tindaklanjut);
            tl_tindaklanjut.initLine(viewType);
            tv_doing = view.findViewById(R.id.tv_doing);
            tv_notice = view.findViewById(R.id.tv_notice);
        }
    }
}
