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
import com.application.bris.ikurma_nos_konsumer.database.pojo.FlagAplikasiPojo;
import com.application.bris.ikurma_nos_konsumer.databinding.ItemSubmenuHotprospekBinding;
import com.application.bris.ikurma_nos_konsumer.listener.menu.MenuClickListener;
import com.application.bris.ikurma_nos_konsumer.model.menu.ListViewSubmenuHotprospek;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.hotprospek;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;

import java.util.List;

import io.realm.Realm;

public class SubmenuDetilAplikasiAdapter extends RecyclerView.Adapter<SubmenuDetilAplikasiAdapter.MenuViewHolder> {
    private List<ListViewSubmenuHotprospek> listMenu;
    private hotprospek data;
    private Context context;
    private MenuClickListener mMenuClickListener;
    private ItemSubmenuHotprospekBinding binding;
    private Realm realm;
    private long idAplikasi;

    public SubmenuDetilAplikasiAdapter(Context context, List<ListViewSubmenuHotprospek> menu, Long idAplikasi, MenuClickListener menuClickListener) {
        this.context = context;
        this.listMenu = menu;
        this.idAplikasi=idAplikasi;
        this.mMenuClickListener = menuClickListener;
    }

    @NonNull
    @Override
    public SubmenuDetilAplikasiAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding=ItemSubmenuHotprospekBinding.inflate(layoutInflater,parent,false);
        View view = binding.getRoot();
        realm=Realm.getDefaultInstance();
        return new SubmenuDetilAplikasiAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubmenuDetilAplikasiAdapter.MenuViewHolder holder, final int position) {
        setGreyorRegularIcon(holder.iv_iconmenu, 1);

        holder.iv_iconmenu.setImageResource(listMenu.get(position).getIcon());
        holder.tv_titlemenu.setText(listMenu.get(position).getTitle());
        holder.rl_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMenuClickListener.onMenuClick(listMenu.get(position).getTitle());
            }
        });

        FlagAplikasiPojo dataFlag= realm.where(FlagAplikasiPojo.class).equalTo("idAplikasi", idAplikasi).findFirst();

        if(dataFlag!=null){
            validateMenu(listMenu.get(position).getTitle(),R.string.submenu_detil_aplikasi_d1_data_dedupe,dataFlag.getFlagD1DataPembiayaan(),holder);
            validateMenu(listMenu.get(position).getTitle(),R.string.submenu_detil_aplikasi_d1_data_nasabah,dataFlag.getFlagD1DataDedupe(),holder);
            validateMenu(listMenu.get(position).getTitle(),R.string.submenu_detil_aplikasi_d1_data_marketing,dataFlag.getFlagD1DataNasabah(),holder);
//            validateMenu(listMenu.get(position).getTitle(),R.string.submenu_detil_aplikasi_d3_jaminan,dataFlag.getFlagD3Kalkulator(),holder);
//            validateMenu(listMenu.get(position).getTitle(),R.string.submenu_detil_aplikasi_d3_data_pendapatan,dataFlag.getFlagD3Jaminan(),holder);
//            validateMenu(listMenu.get(position).getTitle(),R.string.submenu_detil_aplikasi_d3_ideb,dataFlag.getFlagD3Pendapatan(),holder);
//            validateMenu(listMenu.get(position).getTitle(),R.string.submenu_detil_aplikasi_d3_data_hutang,dataFlag.getFlagD3Ideb(),holder);
//            validateMenu(listMenu.get(position).getTitle(),R.string.submenu_detil_aplikasi_d3_memo,dataFlag.getFlagD3Kewajiban(),holder);
        }
        else{
            if(listMenu.get(position).getTitle().equalsIgnoreCase(context.getString(R.string.submenu_detil_aplikasi_d1_data_pembiayaan))){
                setGreyorRegularIcon(holder.iv_iconmenu, 1);
            }
            else if(listMenu.get(position).getTitle().equalsIgnoreCase(context.getString(R.string.submenu_detil_aplikasi_d3_canvassing))){
                setGreyorRegularIcon(holder.iv_iconmenu, 1);
            }
            else if(listMenu.get(position).getTitle().equalsIgnoreCase(context.getString(R.string.submenu_detil_aplikasi_d3_kalkulator))){
                setGreyorRegularIcon(holder.iv_iconmenu, 1);
            }
            else if(listMenu.get(position).getTitle().equalsIgnoreCase(context.getString(R.string.submenu_detil_aplikasi_d3_memo))){
                setGreyorRegularIcon(holder.iv_iconmenu, 1);
            }
            else{
                setGreyorRegularIcon(holder.iv_iconmenu, 0);
            }
        }



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

    public void setGreyorRegularIcon (ImageView iv, int sat){
        ColorMatrix gray = new ColorMatrix();
        gray.setSaturation(sat);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(gray);
        iv.setColorFilter(filter);
    }

    private void validateMenu(String title,int idString,boolean flag,SubmenuDetilAplikasiAdapter.MenuViewHolder holder){
        if(title.equalsIgnoreCase(context.getString(idString))){
            if(title.equalsIgnoreCase(context.getString(idString))&&!flag){
                setGreyorRegularIcon(holder.iv_iconmenu, 0);
                AppUtil.logSecure("warnawarni",title+" masuk kondisi 1");
                holder.rl_menu.setEnabled(false);
            }
            else if(title.equalsIgnoreCase(context.getString(idString))&&flag){
                setGreyorRegularIcon(holder.iv_iconmenu, 1);
                AppUtil.logSecure("warnawarni",title+" masuk kondisi 2");
                holder.rl_menu.setEnabled(true);
            }
            else{
                setGreyorRegularIcon(holder.iv_iconmenu, 1);
                AppUtil.logSecure("warnawarni",title+" masuk kondisi 3");
                holder.rl_menu.setEnabled(true);
            }
        }

    }

}