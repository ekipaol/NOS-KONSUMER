package com.application.bris.ikurma_nos_konsumer.config.menu;

import android.content.Context;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.model.menu.ListViewMenu;
import com.application.bris.ikurma_nos_konsumer.model.menu.ListViewSubmenuHotprospek;

import java.util.List;

/**
 * Created by PID on 28/04/2019.
 */

public class Menu {

    public static String MENU_ID = "idMenu";
    public static String MENU_ROOT = "rootMenu";

    /*************************** Main Menu AO***************************/
    public static void mainMenuAO(Context context, List<ListViewMenu> menu) {
        menu.add(new ListViewMenu(R.drawable.ico_pipeline, context.getString(R.string.menu_pipeline), 0, 0, 0, 0));
        menu.add(new ListViewMenu(R.drawable.ico_hotprospek, context.getString(R.string.menu_hotprospek), 0, 0, 0, 0));
        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved), 0, 0, 0, 0));;
        menu.add(new ListViewMenu(R.drawable.ico_rejected, context.getString(R.string.menu_rejected), 0,0,0, 0));
        menu.add(new ListViewMenu(R.drawable.ic_followup_flpp, "FLPP", 0, 0, 0, 0));
        menu.add(new ListViewMenu(R.drawable.ico_appraisal, context.getString(R.string.menu_appraisal), 0, 0, 0, 0));
        menu.add(new ListViewMenu(R.drawable.ic_generalusericon, "Monitoring", 0, 0, 0, 0));
        menu.add(new ListViewMenu(R.drawable.ic_feedback, "Feedback", 0,0,0, 0));
        menu.add(new ListViewMenu(R.drawable.ic_logout_front, "Logout", 0,0,0, 0));
    }

    public static void mainMenuAONpf(Context context, List<ListViewMenu> menu) {
        menu.add(new ListViewMenu(R.drawable.ic_generalusericon, "Monitoring", 0, 0, 0, 0));
        menu.add(new ListViewMenu(R.drawable.ic_feedback, "Feedback", 0,0,0, 0));
        menu.add(new ListViewMenu(R.drawable.ic_logout_front, "Logout", 0,0,0, 0));
    }

    /************************** Sub Menu Hotprospek ********************/
    //prapen nos
    public static void SubmenuD1(Context context, List<ListViewSubmenuHotprospek> menu){
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d1_data_pembiayaan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d1_data_nasabah)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d1_data_marketing)));

    }

    public static void SubmenuD3(Context context, List<ListViewSubmenuHotprospek> menu){
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d3_canvassing)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d3_kalkulator)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d3_jaminan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d3_data_pendapatan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d3_data_hutang)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d3_ideb)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d3_memo)));

    }

    public static void SubmenuD4(Context context, List<ListViewSubmenuHotprospek> menu){
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d4_canvassing)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d4_kalkulator_verin)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d4_hasil_rac)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d4_hasil_fitur)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d4_data_tempat_kerja)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d4_pendapatan_verin)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d4_hutang_verin)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d4_ideb_verin)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d4_kualitas_pembiayaan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d4_jangka_waktu)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d4_verifikasi)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,context.getString(R.string.submenu_detil_aplikasi_d4_memo)));
    }

    public static void SubmenuD5(Context context, List<ListViewSubmenuHotprospek> menu){
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Hasil Canvassing"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Kalkulator Verin"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Hasil RAC"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Hasil Fitur"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Data Tempat Kerja"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Pendapatan Verin"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Hutang Verin"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Kualitas Pembiayaan"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Data Jangka Waktu"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Verifikasi"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Memo"));
    }

    public static void SubmenuD6(Context context, List<ListViewSubmenuHotprospek> menu){
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Hasil Canvassing"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Hasil RAC"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Hasil Fitur"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Data Tempat Kerja"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Pendapatan Verin"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Hutang Verin"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Kualitas Pembiayaan"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Data Jangka Waktu"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Kalkulator Pemutus"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Verifikasi"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.banner_placeholder,"Memo"));
    }

    public static void SubmenuHotprospek(Context context, List<ListViewSubmenuHotprospek> menu){
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_data_lengkap, context.getString(R.string.submenu_hotprospek_datalengkap)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_prescreening, context.getString(R.string.submenu_hotprospek_prescreening)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_economysector, context.getString(R.string.submenu_hotprospek_sektorekonomi)));
//        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_lkn, context.getString(R.string.submenu_hotprospek_lkn)));
//        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_rpc, context.getString(R.string.submenu_hotprospek_rpc)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_rpc, "Data Finansial"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_agunan, context.getString(R.string.submenu_hotprospek_agunan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_scoring, context.getString(R.string.submenu_hotprospek_scoring)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_documentcomplete, context.getString(R.string.submenu_hotprospek_kelengkapandokumen)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_history, context.getString(R.string.submenu_hotprospek_history)));
    }


    public static void SubmenuHotprospekKpr(Context context, List<ListViewSubmenuHotprospek> menu){
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_data_lengkap, context.getString(R.string.submenu_hotprospek_datalengkap)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_prescreening, context.getString(R.string.submenu_hotprospek_prescreening)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_economysector, context.getString(R.string.submenu_hotprospek_sektorekonomi)));
//        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_lkn, context.getString(R.string.submenu_hotprospek_lkn)));
//        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_rpc, context.getString(R.string.submenu_hotprospek_rpc)));

        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_rpc, "Data Finansial"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_agunan, context.getString(R.string.submenu_hotprospek_agunan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_scoring, context.getString(R.string.submenu_hotprospek_scoring)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_documentcomplete, context.getString(R.string.submenu_hotprospek_kelengkapandokumen)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_history, context.getString(R.string.submenu_hotprospek_history)));
    }

    public static void SubmenuHotprospekAppraisal(Context context, List<ListViewSubmenuHotprospek> menu){
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_data_lengkap, context.getString(R.string.submenu_hotprospek_datalengkap)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_prescreening, context.getString(R.string.submenu_hotprospek_prescreening)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_economysector, context.getString(R.string.submenu_hotprospek_sektorekonomi)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_rpc, "Data Finansial"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_agunan, context.getString(R.string.submenu_hotprospek_agunan)));
    }
}
