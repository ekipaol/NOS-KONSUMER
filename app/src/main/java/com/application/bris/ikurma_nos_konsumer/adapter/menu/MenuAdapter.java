package com.application.bris.ikurma_nos_konsumer.adapter.menu;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.listener.menu.MenuClickListener;
import com.application.bris.ikurma_nos_konsumer.model.menu.ListViewMenu;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private List<ListViewMenu> listMenu;
    private Context context;
    private MenuClickListener mMenuClickListener;

    public MenuAdapter(Context context, List<ListViewMenu> menu, MenuClickListener menuClickListener) {
        this.context = context;
        this.listMenu = menu;
        this.mMenuClickListener = menuClickListener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MenuViewHolder holder, final int position) {
        holder.iv_iconmenu.setImageResource(listMenu.get(position).getIcon());
        holder.tv_titlemenu.setText(listMenu.get(position).getTitle());
        holder.rl_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMenuClickListener.onMenuClick(listMenu.get(position).getTitle());
            }
        });

        if (listMenu.get(position).getJmlPipeline() > 0){
            holder.tv_badgemenu.setVisibility(View.VISIBLE);
            holder.tv_badgemenu.setText(Integer.toString(listMenu.get(position).getJmlPipeline()));
        }

        if (listMenu.get(position).getJmlHotprospek() > 0){
            holder.tv_badgemenu.setVisibility(View.VISIBLE);
            holder.tv_badgemenu.setText(Integer.toString(listMenu.get(position).getJmlHotprospek()));
        }

        if (listMenu.get(position).getJmlApproved() > 0){
            holder.tv_badgemenu.setVisibility(View.VISIBLE);
            holder.tv_badgemenu.setText(Integer.toString(listMenu.get(position).getJmlApproved()));
        }

        if (listMenu.get(position).getJmlRejected() > 0){
            holder.tv_badgemenu.setVisibility(View.VISIBLE);
            holder.tv_badgemenu.setText(Integer.toString(listMenu.get(position).getJmlRejected()));
        }
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rl_menu;
        private ImageView iv_iconmenu;
        private TextView tv_titlemenu, tv_badgemenu;

        public MenuViewHolder(View itemView) {
            super(itemView);
            rl_menu = (RelativeLayout) itemView.findViewById(R.id.rl_menu);
            iv_iconmenu = (ImageView) itemView.findViewById(R.id.iv_iconmenu);
            tv_titlemenu = (TextView) itemView.findViewById(R.id.tv_titlemenu);
            tv_badgemenu = (TextView) itemView.findViewById(R.id.tv_badgemenu);
        }

    }
}