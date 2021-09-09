package com.application.bris.ikurma_nos_konsumer.adapter.menu;

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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.listener.menu.MenuClickListener;
import com.application.bris.ikurma_nos_konsumer.model.menu.ListViewSubmenuHotprospek;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.HotprospekKpr;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.List;

public class SubmenuAppraisalAdapter extends RecyclerView.Adapter<SubmenuAppraisalAdapter.MenuViewHolder> {
    private List<ListViewSubmenuHotprospek> listMenu;
    private HotprospekKpr data;
    private Context context;
    private MenuClickListener mMenuClickListener;

    public SubmenuAppraisalAdapter(Context context, List<ListViewSubmenuHotprospek> menu, HotprospekKpr mdata, MenuClickListener menuClickListener) {
        this.context = context;
        this.listMenu = menu;
        this.data = mdata;
        this.mMenuClickListener = menuClickListener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_submenu_hotprospek, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, final int position) {
        holder.iv_iconmenu.setImageResource(listMenu.get(position).getIcon());
        holder.tv_titlemenu.setText(listMenu.get(position).getTitle());

        holder.rl_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.getId_st_aplikasi()==1||data.getId_st_aplikasi()==-34){
                    AppUtil.notifinfoLong(context,holder.rl_menu.getRootView().findViewById(android.R.id.content),"Tidak bisa mengakses menu ini karena aplikasi sudah kembali ke AO pemrakarsa");
                }
                else{
                    mMenuClickListener.onMenuClick(listMenu.get(position).getTitle());
                }

            }
        });


        //bikin border ijo untuk menu agunan di AO silang
        if(listMenu.get(position).getTitle().equalsIgnoreCase("agunan")){
            holder.cv_agunan.setBackground(context.getResources().getDrawable(R.drawable.shapebordergreen));
        }

        if(data.getId_st_aplikasi()==1||data.getId_st_aplikasi()==-34){
//            holder.rl_menu.setEnabled(false);
            setGreyorRegularIcon(holder.iv_iconmenu,0);
        }

    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rl_menu;
        private ImageView iv_iconmenu, iv_complete;
        private TextView tv_titlemenu;
        private CardView cv_agunan;
        private TextView tv_opsi;

        public MenuViewHolder(View itemView) {
            super(itemView);
            rl_menu = (RelativeLayout) itemView.findViewById(R.id.rl_menu);
            iv_iconmenu = (ImageView) itemView.findViewById(R.id.iv_iconmenu);
            tv_titlemenu = (TextView) itemView.findViewById(R.id.tv_titlemenu);
            iv_complete = (ImageView) itemView.findViewById(R.id.iv_complete);
            tv_opsi = (TextView) itemView.findViewById(R.id.tv_opsi);
            cv_agunan=(CardView) itemView.findViewById(R.id.cv_agunan);
        }

    }

    public void setGreyorRegularIcon (ImageView iv, int sat){
        ColorMatrix gray = new ColorMatrix();
        gray.setSaturation(sat);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(gray);
        iv.setColorFilter(filter);
    }
}