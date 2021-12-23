package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.canvasing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseFitur;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseFitur;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.ItemHasilCanvasingBinding;

import java.util.List;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class ResumeFiturAdapter extends RecyclerView.Adapter<ResumeFiturAdapter.MenuViewHolder> {

    private List<MparseResponseFitur> data;
    private Context context;
    private ItemHasilCanvasingBinding binding;
    private AppPreferences appPreferences;

    public ResumeFiturAdapter(Context context, List<MparseResponseFitur> mdata) {
        this.context = context;
        this.data = mdata;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemHasilCanvasingBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        appPreferences = new AppPreferences(context);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        //never user BINDING ON ON BIND VIEW HOLDER DUDE!!!, USE HOLDER INSTEAD
        //NEVER, IT GONNA F UP YOUR DATA ORDER
        final MparseResponseFitur datas = data.get(position);
        holder.etRAC.setText(datas.getValue());
        holder.tvRAC.setLabelText(datas.getParameter());
        holder.etRAC.setFocusable(false);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextFieldBoxes tvRAC;
        ExtendedEditText etRAC;

        public MenuViewHolder(View itemView) {
            super(itemView);
            tvRAC = binding.tfResumeRac;
            etRAC = binding.etResumeRac;
        }

    }
}
