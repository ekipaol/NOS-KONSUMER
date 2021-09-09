package com.application.bris.ikurma_nos_konsumer.page_aom.adapter.hotprospek;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.ListAgunanKelengkapanDokumen;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.util.List;

/**
 * Created by PID on 17/07/2019.
 */

public class AgunanKelengkapanDokumenAdapter extends RecyclerView.Adapter<AgunanKelengkapanDokumenAdapter.PipelineViewHolder> {

    private Context context;
    private List<ListAgunanKelengkapanDokumen> data;
    private Bitmap loadPicture, bitmap;

    public AgunanKelengkapanDokumenAdapter(Context context, List<ListAgunanKelengkapanDokumen> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_agunan_kelengkapandokumen, parent, false);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PipelineViewHolder holder, int position) {
        final ListAgunanKelengkapanDokumen datas = data.get(position);

        holder.cb_agunan.setChecked(true);
        holder.tv_agunan.setText(datas.getkATEGORI());

        loadPicture = setLoadImage(holder.iv_agunan, datas.getiD());

        holder.et_agunan.setText(datas.getnOSERTIFIKAT());

        holder.iv_agunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPreviewPhoto.display(((AppCompatActivity) context).getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)holder.iv_agunan.getDrawable()).getSourceBitmap());
            }
        });
    }

    @Override
    public int getItemCount() {
        return (data == null) ? 0 : data.size();
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cb_agunan;
        private TextView tv_agunan;
        private ImageView iv_agunan;
        private EditText et_agunan;


        public PipelineViewHolder(View itemView) {
            super(itemView);

            cb_agunan = (CheckBox) itemView.findViewById(R.id.cb_agunan);
            tv_agunan = (TextView) itemView.findViewById(R.id.tv_agunan);
            iv_agunan = (ImageView) itemView.findViewById(R.id.iv_agunan);
            et_agunan = (EditText) itemView.findViewById(R.id.et_agunan);
        }
    }

    public Bitmap setLoadImage(final ImageView iv, int idFoto){
        String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + idFoto;
        Glide
                .with(context)
                .asBitmap()
                .load(url_photo)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv.setImageBitmap(resource);
                        bitmap = resource;
                    }
                });
        return bitmap;
    }
}
