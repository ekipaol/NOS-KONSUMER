package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.history;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.history.model.ModelHistoryCovenance;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.List;

/**
 * Created by idong on 09/09/2019.
 */

public class HistoryCovenanceAdapater extends RecyclerView.Adapter<HistoryCovenanceAdapater.TlViewHolder>{

    private Context context;
    private List<ModelHistoryCovenance> data;

    public HistoryCovenanceAdapater(Context context, List<ModelHistoryCovenance> mdata) {
        this.context = context;
        this.data = mdata;
    }

    @NonNull
    @Override
    public TlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ao_item_historycovenance, parent, false);
        return new HistoryCovenanceAdapater.TlViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull TlViewHolder holder, int position) {
        final ModelHistoryCovenance datas = data.get(position);
        holder.tv_namapemutus.setText(datas.getNama_pemutus());
        holder.tv_jabatanpemutus.setText(datas.getJabatan());
        holder.tv_putusanpemutus.setText(datas.getPutusan_pemutus());
        holder.tv_jenisputusan.setText(datas.getJenis_putusan());
        holder.tv_catatan.setText(Html.fromHtml(datas.getCatatan_pemutus()));
        holder.btn_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("text", datas.getCatatan_pemutus());
                    clipboardManager.setPrimaryClip(clipData);
                    AppUtil.showToastShort(context, "Catatan "+datas.getNama_pemutus()+" sudah dicopy");
                }
                catch (Exception e)
                {
                    AppUtil.showToastShort(context, e.getMessage());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class TlViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_namapemutus, tv_jabatanpemutus, tv_putusanpemutus, tv_jenisputusan, tv_catatan, btn_copy;

        public TlViewHolder(View view, int viewType) {
            super(view);
            tv_namapemutus = view.findViewById(R.id.tv_namapemutus);
            tv_jabatanpemutus = view.findViewById(R.id.tv_jabatanpemutus);
            tv_putusanpemutus = view.findViewById(R.id.tv_putusanpemutus);
            tv_jenisputusan = view.findViewById(R.id.tv_jenisputusan);
            tv_catatan = view.findViewById(R.id.tv_catatan);
            btn_copy = view.findViewById(R.id.btn_copy);
        }
    }
}
