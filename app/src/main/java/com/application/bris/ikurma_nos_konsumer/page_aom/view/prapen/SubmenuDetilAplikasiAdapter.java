package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.databinding.ItemSubmenuHotprospekBinding;
import com.application.bris.ikurma_nos_konsumer.listener.menu.MenuClickListener;
import com.application.bris.ikurma_nos_konsumer.model.menu.ListViewSubmenuHotprospek;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.hotprospek;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.List;

public class SubmenuDetilAplikasiAdapter extends RecyclerView.Adapter<SubmenuDetilAplikasiAdapter.MenuViewHolder> {
    private List<ListViewSubmenuHotprospek> listMenu;
    private hotprospek data;
    private Context context;
    private MenuClickListener mMenuClickListener;
    private ItemSubmenuHotprospekBinding binding;

    public SubmenuDetilAplikasiAdapter(Context context, List<ListViewSubmenuHotprospek> menu, hotprospek mdata, MenuClickListener menuClickListener) {
        this.context = context;
        this.listMenu = menu;
        this.data = mdata;
        this.mMenuClickListener = menuClickListener;
    }

    @NonNull
    @Override
    public SubmenuDetilAplikasiAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding=ItemSubmenuHotprospekBinding.inflate(layoutInflater,parent,false);
        View view = binding.getRoot();
        return new SubmenuDetilAplikasiAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubmenuDetilAplikasiAdapter.MenuViewHolder holder, final int position) {
        holder.iv_iconmenu.setImageResource(listMenu.get(position).getIcon());
        holder.tv_titlemenu.setText(listMenu.get(position).getTitle());

        holder.rl_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMenuClickListener.onMenuClick(listMenu.get(position).getTitle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rl_menu;
        private ImageView iv_iconmenu;
        private TextView tv_titlemenu;
//        private TextView tv_opsi;

        public MenuViewHolder(View itemView) {
            super(itemView);
            rl_menu = binding.rlMenu;
            iv_iconmenu = binding.ivIconmenu;
            tv_titlemenu = binding.tvTitlemenu;
//            iv_complete = binding.ivComplete;
//            tv_opsi = binding.tvOpsi;
        }

    }

}