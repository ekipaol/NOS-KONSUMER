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
        menu.add(new ListViewMenu(R.drawable.ic_menu_putusan_vector, context.getString(R.string.menu_input), 0, 0, 0, 0));
        menu.add(new ListViewMenu(R.drawable.ic_menu_list_aplikasi_vector, context.getString(R.string.menu_aplikasi), 0, 0, 0, 0));
        menu.add(new ListViewMenu(R.drawable.ic_instansi, context.getString(R.string.menu_instansi), 0, 0, 0, 0));
        menu.add(new ListViewMenu(R.drawable.ic_menu_monitoring_vector, context.getString(R.string.menu_monitoring), 0, 0, 0, 0));
        menu.add(new ListViewMenu(R.drawable.ic_logout_front, "Logout", 0,0,0, 0));
    }

    public static void mainMenuAONpf(Context context, List<ListViewMenu> menu) {
        menu.add(new ListViewMenu(R.drawable.ic_generalusericon, "Monitoring", 0, 0, 0, 0));
        menu.add(new ListViewMenu(R.drawable.ic_feedback, "Feedback", 0,0,0, 0));
        menu.add(new ListViewMenu(R.drawable.ic_logout_front, "Logout", 0,0,0, 0));
    }

    /************************** Sub Menu Hotprospek ********************/
    //prapen nos

    public static void SubmenuD05(Context context, List<ListViewSubmenuHotprospek> menu){
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_pembiayaan,context.getString(R.string.submenu_detil_aplikasi_d1_data_pembiayaan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_verif,context.getString(R.string.submenu_detil_aplikasi_d1_data_dedupe)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_history_nasabah,context.getString(R.string.submenu_detil_aplikasi_d1_data_nasabah)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_marketing,context.getString(R.string.submenu_detil_aplikasi_d1_data_marketing)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_memo,context.getString(R.string.submenu_detil_aplikasi_d1_memo)));
    }

    public static void SubmenuD1(Context context, List<ListViewSubmenuHotprospek> menu){
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_pembiayaan,context.getString(R.string.submenu_detil_aplikasi_d1_data_pembiayaan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_verif,context.getString(R.string.submenu_detil_aplikasi_d1_data_dedupe)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_history_nasabah,context.getString(R.string.submenu_detil_aplikasi_d1_data_nasabah)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_assesoir,context.getString(R.string.submenu_detil_aplikasi_d1_bsi_bisa)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_marketing,context.getString(R.string.submenu_detil_aplikasi_d1_data_marketing)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_checked,context.getString(R.string.submenu_detil_aplikasi_d1_lanjut)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_reject,context.getString(R.string.submenu_detil_aplikasi_d1_batal)));
//        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_memo,context.getString(R.string.submenu_detil_aplikasi_d1_memo)));

    }

    public static void SubmenuD3(Context context, List<ListViewSubmenuHotprospek> menu){
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_hasil_canvasing,context.getString(R.string.submenu_detil_aplikasi_d3_canvassing)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_jaminan_document,context.getString(R.string.submenu_detil_aplikasi_d3_jaminan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_data_pendapatan,context.getString(R.string.submenu_detil_aplikasi_d3_data_pendapatan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_ideb,context.getString(R.string.submenu_detil_aplikasi_d3_ideb)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_data_hutang,context.getString(R.string.submenu_detil_aplikasi_d3_data_hutang)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_verif,context.getString(R.string.submenu_detil_aplikasi_d3_data_tambahan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_assesoir,context.getString(R.string.submenu_detil_aplikasi_d3_data_objek_akad)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_kalkulator,context.getString(R.string.submenu_detil_aplikasi_d3_kalkulator)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_memo,context.getString(R.string.submenu_detil_aplikasi_d3_memo)));

    }

    public static void SubmenuD4(Context context, List<ListViewSubmenuHotprospek> menu){
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_hasil_canvasing,context.getString(R.string.submenu_detil_aplikasi_d4_canvassing)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_economysector,context.getString(R.string.submenu_detil_aplikasi_d4_dedupe_all)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_verif,context.getString(R.string.submenu_detil_aplikasi_d4_hasil_rac)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_verif,context.getString(R.string.submenu_detil_aplikasi_d4_hasil_fitur)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_tempat_kerja,context.getString(R.string.submenu_detil_aplikasi_d4_data_tempat_kerja)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_data_pendapatan,context.getString(R.string.submenu_detil_aplikasi_d4_pendapatan_verin)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_ideb,context.getString(R.string.submenu_detil_aplikasi_d4_ideb_verin)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_data_hutang,context.getString(R.string.submenu_detil_aplikasi_d4_hutang_verin)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_history_nasabah,context.getString(R.string.submenu_detil_aplikasi_d3_data_tambahan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_pembiayaan,context.getString(R.string.submenu_detil_aplikasi_d4_kualitas_pembiayaan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_jangka_waktu,context.getString(R.string.submenu_detil_aplikasi_d4_jangka_waktu)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_verif,context.getString(R.string.submenu_detil_aplikasi_d4_verifikasi)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_kalkulator,context.getString(R.string.submenu_detil_aplikasi_d4_kalkulator_verin)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_memo,context.getString(R.string.submenu_detil_aplikasi_d4_memo)));
    }

    public static void SubmenuD5(Context context, List<ListViewSubmenuHotprospek> menu){
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_hasil_canvasing,context.getString(R.string.submenu_detil_aplikasi_d5_canvassing)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_economysector,context.getString(R.string.submenu_detil_aplikasi_d4_dedupe_all)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_verif,context.getString(R.string.submenu_detil_aplikasi_d5_hasil_rac)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_verif,context.getString(R.string.submenu_detil_aplikasi_d5_hasil_fitur)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_tempat_kerja,context.getString(R.string.submenu_detil_aplikasi_d5_data_tempat_kerja)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_data_pendapatan,context.getString(R.string.submenu_detil_aplikasi_d5_pendapatan_verin)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_ideb,context.getString(R.string.submenu_detil_aplikasi_d5_ideb_verin)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_data_hutang,context.getString(R.string.submenu_detil_aplikasi_d5_hutang_verin)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_history_nasabah,context.getString(R.string.submenu_detil_aplikasi_d3_data_tambahan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_pembiayaan,context.getString(R.string.submenu_detil_aplikasi_d5_kualitas_pembiayaan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_jangka_waktu,context.getString(R.string.submenu_detil_aplikasi_d5_jangka_waktu)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_assesoir,context.getString(R.string.submenu_detil_aplikasi_d3_data_objek_akad)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_verif,context.getString(R.string.submenu_detil_aplikasi_d5_verifikasi)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_kalkulator,context.getString(R.string.submenu_detil_aplikasi_d5_kalkulator_verin)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_memo,context.getString(R.string.submenu_detil_aplikasi_d5_memo)));
    }

    public static void SubmenuD6(Context context, List<ListViewSubmenuHotprospek> menu){
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_hasil_canvasing,context.getString(R.string.submenu_detil_aplikasi_d6_canvassing)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_economysector,context.getString(R.string.submenu_detil_aplikasi_d4_dedupe_all)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_verif,context.getString(R.string.submenu_detil_aplikasi_d6_hasil_rac)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_verif,context.getString(R.string.submenu_detil_aplikasi_d6_hasil_fitur)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_tempat_kerja,context.getString(R.string.submenu_detil_aplikasi_d6_data_tempat_kerja)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_data_pendapatan,context.getString(R.string.submenu_detil_aplikasi_d6_pendapatan_verin)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_ideb,context.getString(R.string.submenu_detil_aplikasi_d6_ideb_verin)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_data_hutang,context.getString(R.string.submenu_detil_aplikasi_d6_hutang_verin)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_history_nasabah,context.getString(R.string.submenu_detil_aplikasi_d3_data_tambahan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_pembiayaan,context.getString(R.string.submenu_detil_aplikasi_d6_kualitas_pembiayaan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_jangka_waktu,context.getString(R.string.submenu_detil_aplikasi_d6_jangka_waktu)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_assesoir,context.getString(R.string.submenu_detil_aplikasi_d3_data_objek_akad)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_kalkulator,context.getString(R.string.submenu_detil_aplikasi_d6_kalkulator_pemutus)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_verif,context.getString(R.string.submenu_detil_aplikasi_d6_verifikasi)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_verif,context.getString(R.string.submenu_detil_aplikasi_d6_memo)));
    }

    public static void SubmenuG1(Context context, List<ListViewSubmenuHotprospek> menu){
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_ideb,context.getString(R.string.submenu_detil_aplikasi_g1_OJK_BI)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_assesoir,context.getString(R.string.submenu_detil_aplikasi_g1_asesoir)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_akad,context.getString(R.string.submenu_detil_aplikasi_g1_akad)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_kalkulator,context.getString(R.string.submenu_detil_aplikasi_g1_kalkulator)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_jaminan_document,context.getString(R.string.submenu_detil_aplikasi_g1_dokumen_persiapan_akad)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_memo,context.getString(R.string.submenu_detil_aplikasi_g1_memo)));
    }

    public static void SubmenuG3(Context context, List<ListViewSubmenuHotprospek> menu){
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_verif,context.getString(R.string.submenu_detil_aplikasi_g3_upload_dokumen)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_memo,context.getString(R.string.submenu_detil_aplikasi_g3_memo)));
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


