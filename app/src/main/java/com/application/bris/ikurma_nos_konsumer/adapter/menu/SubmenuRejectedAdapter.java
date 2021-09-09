package com.application.bris.ikurma_nos_konsumer.adapter.menu;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
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
import com.application.bris.ikurma_nos_konsumer.model.menu.ListViewSubmenuHotprospek;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.hotprospek;

import java.util.List;

public class SubmenuRejectedAdapter extends RecyclerView.Adapter<SubmenuRejectedAdapter.MenuViewHolder> {
    private List<ListViewSubmenuHotprospek> listMenu;
    private hotprospek data;
    private Context context;
    private MenuClickListener mMenuClickListener;
    private int flagPrescreening, flagDataLengkap, flagSektorEkonomi, flagLKN, flagRPC, flagAgunan, flagKelengkapanData, flagScoring;

    public SubmenuRejectedAdapter(Context context, List<ListViewSubmenuHotprospek> menu, hotprospek mdata, MenuClickListener menuClickListener) {
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
    public void onBindViewHolder(@NonNull SubmenuRejectedAdapter.MenuViewHolder holder, final int position) {
        holder.iv_iconmenu.setImageResource(listMenu.get(position).getIcon());
        holder.tv_titlemenu.setText(listMenu.get(position).getTitle());

        holder.rl_menu.setEnabled(false);
        setGreyorRegularIcon(holder.iv_iconmenu, 0);

        holder.rl_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMenuClickListener.onMenuClick(listMenu.get(position).getTitle());
            }
        });

        flagPrescreening = data.getFlag_prescreening();
        flagDataLengkap = data.getFlag_data_lengkap();
        flagSektorEkonomi = data.getFlag_data_pembiayaan();
        flagLKN = data.getFlag_lkn();
        flagRPC = data.getFlag_rpc();
        flagAgunan = data.getFlag_agunan();
        flagKelengkapanData = data.getFlag_dokumen();
        flagScoring = data.getFlag_scoring();

        if (flagDataLengkap == 1 && position == 0){
            holder.iv_complete.setVisibility(View.VISIBLE);
        }

        if (flagPrescreening == 1 && position == 1){
            holder.iv_complete.setVisibility(View.VISIBLE);
        }

        if (flagSektorEkonomi == 1 && position == 2){
            holder.iv_complete.setVisibility(View.VISIBLE);
        }

        if (flagLKN == 1 && position == 3){
            holder.iv_complete.setVisibility(View.VISIBLE);
        }

        if (flagRPC == 1 && position == 4){
            holder.iv_complete.setVisibility(View.VISIBLE);
        }

        if (flagAgunan == 1 && position == 5){
            holder.iv_complete.setVisibility(View.VISIBLE);
        }

        if (flagKelengkapanData == 1 && position == 6){
            holder.iv_complete.setVisibility(View.VISIBLE);
        }

        if (flagScoring == 1 && position == 7){
            holder.iv_complete.setVisibility(View.VISIBLE);
        }

        validateClick(holder.rl_menu, holder.iv_complete, holder.iv_iconmenu, position);

        if ((data.getKode_produk().equalsIgnoreCase("127") || data.getKode_produk().equalsIgnoreCase("131")) && position == 5){
            holder.tv_opsi.setVisibility(View.VISIBLE);
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
        private TextView tv_opsi;

        public MenuViewHolder(View itemView) {
            super(itemView);
            rl_menu = (RelativeLayout) itemView.findViewById(R.id.rl_menu);
            iv_iconmenu = (ImageView) itemView.findViewById(R.id.iv_iconmenu);
            tv_titlemenu = (TextView) itemView.findViewById(R.id.tv_titlemenu);
            iv_complete = (ImageView) itemView.findViewById(R.id.iv_complete);
            tv_opsi = (TextView) itemView.findViewById(R.id.tv_opsi);
        }

    }

    public void validateClick(RelativeLayout rl, ImageView ivComplete, ImageView ivMenu, int pos){
        if (pos == 0 || pos == 8){
            rl.setEnabled(true);
            setGreyorRegularIcon(ivMenu, 1);
        }

        else if (flagDataLengkap == 1 && pos == 1){
            rl.setEnabled(true);
            setGreyorRegularIcon(ivMenu, 1);
        }

        else if (flagPrescreening == 1 && pos == 2){
            rl.setEnabled(true);
            setGreyorRegularIcon(ivMenu, 1);
        }

        else if (flagSektorEkonomi == 1 && pos == 3){
            rl.setEnabled(true);
            setGreyorRegularIcon(ivMenu, 1);
        }
        else if (flagLKN == 1 && pos == 4){
            rl.setEnabled(true);
            setGreyorRegularIcon(ivMenu, 1);
        }
        else if (flagRPC == 1 && pos == 5){
            rl.setEnabled(true);
            setGreyorRegularIcon(ivMenu, 1);
        }
        else if (flagAgunan == 1 && pos == 6){
            rl.setEnabled(true);
            setGreyorRegularIcon(ivMenu, 1);
        }
        else if (flagKelengkapanData == 1 && pos == 7){
            rl.setEnabled(true);
            setGreyorRegularIcon(ivMenu, 1);
        }
    }

    public void setGreyorRegularIcon (ImageView iv, int sat){
        ColorMatrix gray = new ColorMatrix();
        gray.setSaturation(sat);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(gray);
        iv.setColorFilter(filter);
    }
}