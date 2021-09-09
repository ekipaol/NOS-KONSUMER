package com.application.bris.ikurma_nos_konsumer.model.hotprospek;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class KonsumerKprWiraswastaKelengkapanDokumen extends RealmObject {



    @PrimaryKey
    @SerializedName("ID_APLIKASI")
    public int ID_APLIKASI;
    @SerializedName("FC_KTP")
    public Boolean FC_KTP;
    @SerializedName("ID_DOKUMEN_KTP")
    public int ID_DOKUMEN_KTP;
    @SerializedName("FC_KK")
    public Boolean FC_KK;
    @SerializedName("ID_DOKUMEN_KK")
    public int ID_DOKUMEN_KK;
    @SerializedName("FC_SURAT_NIKAH")
    public Boolean FC_SURAT_NIKAH;
    @SerializedName("ID_DOKUMEN_SURAT_NIKAH")
    public int ID_DOKUMEN_SURAT_NIKAH;
    @SerializedName("PAS_PHOTO")
    public Boolean PAS_PHOTO;
    @SerializedName("ID_DOKUMEN_PAS_PHOTO")
    public int ID_DOKUMEN_PAS_PHOTO;
    @SerializedName("FC_NPWP_PRIBADI")
    public Boolean FC_NPWP_PRIBADI;
    @SerializedName("ID_DOKUMEN_NPWP_PRIBADI")
    public int ID_DOKUMEN_NPWP_PRIBADI;
    @SerializedName("ID_DOKUMEN_FC_NPWP_PRIBADI")
    public int ID_FC_NPWP_PRIBADI;  //INI MEMANG MIRIP SAMA ATASNYA, INI KHUSUS PURNA
    @SerializedName("APLIKASI_PERMOHONAN")
    public Boolean APLIKASI_PERMOHONAN;
    @SerializedName("ID_DOKUMEN_APLIKASI")
    public int ID_DOKUMEN_APLIKASI;
    @SerializedName("FC_SIUP")
    public Boolean FC_SIUP;
    @SerializedName("ID_DOKUMEN_FC_SIUP")
    public int ID_DOKUMEN_FC_SIUP;
    @SerializedName("LAPORAN_KEUANGAN")
    public Boolean LAPORAN_KEUANGAN;
    @SerializedName("ID_DOKUMEN_LAPORAN_KEUANGAN")
    public int ID_DOKUMEN_LAPORAN_KEUANGAN;
    @SerializedName("REKAP_BON_BUKU_BESAR")
    public Boolean REKAP_BON_BUKU_BESAR;
    @SerializedName("ID_DOKUMEN_REKAP_BON_BUKU_BESAR")
    public int ID_DOKUMEN_REKAP_BON_BUKU_BESAR;
    @SerializedName("FC_TDP")
    public Boolean FC_TDP;
    @SerializedName("ID_DOKUMEN_FC_TDP")
    public int ID_DOKUMEN_FC_TDP;
    @SerializedName("DOKUMEN")
    public Boolean DOKUMEN;
    @SerializedName("ID_DOKUMEN_LAINNYA")
    public int ID_DOKUMEN_LAINNYA;

    @SerializedName("SPR_SPP_RAB")
    public Boolean SPR_SPP_RAB;
    @SerializedName("ID_DOKUMEN_SPR_SPP_RAB")
    public int ID_DOKUMEN_SPR_SPP_RAB;

    @SerializedName("SURAT_KETERANGAN_USAHA")
    public Boolean SURAT_KETERANGAN_USAHA;
    @SerializedName("ID_DOKUMEN_SURAT_KETERANGAN_USAHA")
    public int ID_DOKUMEN_SURAT_KETERANGAN_USAHA;

    @SerializedName("FC_NPWP_BADAN_USAHA")
    public Boolean FC_NPWP_BADAN_USAHA;
    @SerializedName("ID_DOKUMEN_FC_NPWP_BADAN_USAHA")
    public int ID_DOKUMEN_FC_NPWP_BADAN_USAHA;


    @SerializedName("REKENING_KORAN")
    public Boolean REKENING_KORAN;
    @SerializedName("ID_DOKUMEN_REKENING_KORAN")
    public int ID_DOKUMEN_REKENING_KORAN;

    public Boolean getREKENING_KORAN() {
        return REKENING_KORAN;
    }

    public void setREKENING_KORAN(Boolean REKENING_KORAN) {
        this.REKENING_KORAN = REKENING_KORAN;
    }

    public int getID_DOKUMEN_REKENING_KORAN() {
        return ID_DOKUMEN_REKENING_KORAN;
    }

    public void setID_DOKUMEN_REKENING_KORAN(int ID_DOKUMEN_REKENING_KORAN) {
        this.ID_DOKUMEN_REKENING_KORAN = ID_DOKUMEN_REKENING_KORAN;
    }

    public int getID_APLIKASI() {
        return ID_APLIKASI;
    }

    public void setID_APLIKASI(int ID_APLIKASI) {
        this.ID_APLIKASI = ID_APLIKASI;
    }

    public Boolean getFC_KTP() {
        return FC_KTP;
    }

    public void setFC_KTP(Boolean FC_KTP) {
        this.FC_KTP = FC_KTP;
    }

    public int getID_DOKUMEN_KTP() {
        return ID_DOKUMEN_KTP;
    }

    public void setID_DOKUMEN_KTP(int ID_DOKUMEN_KTP) {
        this.ID_DOKUMEN_KTP = ID_DOKUMEN_KTP;
    }

    public Boolean getFC_KK() {
        return FC_KK;
    }

    public void setFC_KK(Boolean FC_KK) {
        this.FC_KK = FC_KK;
    }

    public int getID_DOKUMEN_KK() {
        return ID_DOKUMEN_KK;
    }

    public void setID_DOKUMEN_KK(int ID_DOKUMEN_KK) {
        this.ID_DOKUMEN_KK = ID_DOKUMEN_KK;
    }

    public Boolean getFC_SURAT_NIKAH() {
        return FC_SURAT_NIKAH;
    }

    public void setFC_SURAT_NIKAH(Boolean FC_SURAT_NIKAH) {
        this.FC_SURAT_NIKAH = FC_SURAT_NIKAH;
    }

    public int getID_DOKUMEN_SURAT_NIKAH() {
        return ID_DOKUMEN_SURAT_NIKAH;
    }

    public void setID_DOKUMEN_SURAT_NIKAH(int ID_DOKUMEN_SURAT_NIKAH) {
        this.ID_DOKUMEN_SURAT_NIKAH = ID_DOKUMEN_SURAT_NIKAH;
    }

    public Boolean getPAS_PHOTO() {
        return PAS_PHOTO;
    }

    public void setPAS_PHOTO(Boolean PAS_PHOTO) {
        this.PAS_PHOTO = PAS_PHOTO;
    }

    public int getID_DOKUMEN_PAS_PHOTO() {
        return ID_DOKUMEN_PAS_PHOTO;
    }

    public void setID_DOKUMEN_PAS_PHOTO(int ID_DOKUMEN_PAS_PHOTO) {
        this.ID_DOKUMEN_PAS_PHOTO = ID_DOKUMEN_PAS_PHOTO;
    }

    public Boolean getFC_NPWP_PRIBADI() {
        return FC_NPWP_PRIBADI;
    }

    public void setFC_NPWP_PRIBADI(Boolean FC_NPWP_PRIBADI) {
        this.FC_NPWP_PRIBADI = FC_NPWP_PRIBADI;
    }

    public int getID_DOKUMEN_NPWP_PRIBADI() {
        return ID_DOKUMEN_NPWP_PRIBADI;
    }

    public void setID_DOKUMEN_NPWP_PRIBADI(int ID_DOKUMEN_NPWP_PRIBADI) {
        this.ID_DOKUMEN_NPWP_PRIBADI = ID_DOKUMEN_NPWP_PRIBADI;
    }

    public int getID_FC_NPWP_PRIBADI() {
        return ID_FC_NPWP_PRIBADI;
    }

    public void setID_FC_NPWP_PRIBADI(int ID_FC_NPWP_PRIBADI) {
        this.ID_FC_NPWP_PRIBADI = ID_FC_NPWP_PRIBADI;
    }

    public Boolean getAPLIKASI_PERMOHONAN() {
        return APLIKASI_PERMOHONAN;
    }

    public void setAPLIKASI_PERMOHONAN(Boolean APLIKASI_PERMOHONAN) {
        this.APLIKASI_PERMOHONAN = APLIKASI_PERMOHONAN;
    }

    public int getID_DOKUMEN_APLIKASI() {
        return ID_DOKUMEN_APLIKASI;
    }

    public void setID_DOKUMEN_APLIKASI(int ID_DOKUMEN_APLIKASI) {
        this.ID_DOKUMEN_APLIKASI = ID_DOKUMEN_APLIKASI;
    }

    public Boolean getFC_SIUP() {
        return FC_SIUP;
    }

    public void setFC_SIUP(Boolean FC_SIUP) {
        this.FC_SIUP = FC_SIUP;
    }

    public int getID_DOKUMEN_FC_SIUP() {
        return ID_DOKUMEN_FC_SIUP;
    }

    public void setID_DOKUMEN_FC_SIUP(int ID_DOKUMEN_FC_SIUP) {
        this.ID_DOKUMEN_FC_SIUP = ID_DOKUMEN_FC_SIUP;
    }

    public Boolean getLAPORAN_KEUANGAN() {
        return LAPORAN_KEUANGAN;
    }

    public void setLAPORAN_KEUANGAN(Boolean LAPORAN_KEUANGAN) {
        this.LAPORAN_KEUANGAN = LAPORAN_KEUANGAN;
    }

    public int getID_DOKUMEN_LAPORAN_KEUANGAN() {
        return ID_DOKUMEN_LAPORAN_KEUANGAN;
    }

    public void setID_DOKUMEN_LAPORAN_KEUANGAN(int ID_DOKUMEN_LAPORAN_KEUANGAN) {
        this.ID_DOKUMEN_LAPORAN_KEUANGAN = ID_DOKUMEN_LAPORAN_KEUANGAN;
    }

    public Boolean getREKAP_BON_BUKU_BESAR() {
        return REKAP_BON_BUKU_BESAR;
    }

    public void setREKAP_BON_BUKU_BESAR(Boolean REKAP_BON_BUKU_BESAR) {
        this.REKAP_BON_BUKU_BESAR = REKAP_BON_BUKU_BESAR;
    }

    public int getID_DOKUMEN_REKAP_BON_BUKU_BESAR() {
        return ID_DOKUMEN_REKAP_BON_BUKU_BESAR;
    }

    public void setID_DOKUMEN_REKAP_BON_BUKU_BESAR(int ID_DOKUMEN_REKAP_BON_BUKU_BESAR) {
        this.ID_DOKUMEN_REKAP_BON_BUKU_BESAR = ID_DOKUMEN_REKAP_BON_BUKU_BESAR;
    }

    public Boolean getFC_TDP() {
        return FC_TDP;
    }

    public void setFC_TDP(Boolean FC_TDP) {
        this.FC_TDP = FC_TDP;
    }

    public int getID_DOKUMEN_FC_TDP() {
        return ID_DOKUMEN_FC_TDP;
    }

    public void setID_DOKUMEN_FC_TDP(int ID_DOKUMEN_FC_TDP) {
        this.ID_DOKUMEN_FC_TDP = ID_DOKUMEN_FC_TDP;
    }

    public Boolean getDOKUMEN() {
        return DOKUMEN;
    }

    public void setDOKUMEN(Boolean DOKUMEN) {
        this.DOKUMEN = DOKUMEN;
    }

    public int getID_DOKUMEN_LAINNYA() {
        return ID_DOKUMEN_LAINNYA;
    }

    public void setID_DOKUMEN_LAINNYA(int ID_DOKUMEN_LAINNYA) {
        this.ID_DOKUMEN_LAINNYA = ID_DOKUMEN_LAINNYA;
    }

    public Boolean getSPR_SPP_RAB() {
        return SPR_SPP_RAB;
    }

    public void setSPR_SPP_RAB(Boolean SPR_SPP_RAB) {
        this.SPR_SPP_RAB = SPR_SPP_RAB;
    }

    public int getID_DOKUMEN_SPR_SPP_RAB() {
        return ID_DOKUMEN_SPR_SPP_RAB;
    }

    public void setID_DOKUMEN_SPR_SPP_RAB(int ID_DOKUMEN_SPR_SPP_RAB) {
        this.ID_DOKUMEN_SPR_SPP_RAB = ID_DOKUMEN_SPR_SPP_RAB;
    }

    public Boolean getSURAT_KETERANGAN_USAHA() {
        return SURAT_KETERANGAN_USAHA;
    }

    public void setSURAT_KETERANGAN_USAHA(Boolean SURAT_KETERANGAN_USAHA) {
        this.SURAT_KETERANGAN_USAHA = SURAT_KETERANGAN_USAHA;
    }

    public int getID_DOKUMEN_SURAT_KETERANGAN_USAHA() {
        return ID_DOKUMEN_SURAT_KETERANGAN_USAHA;
    }

    public void setID_DOKUMEN_SURAT_KETERANGAN_USAHA(int ID_DOKUMEN_SURAT_KETERANGAN_USAHA) {
        this.ID_DOKUMEN_SURAT_KETERANGAN_USAHA = ID_DOKUMEN_SURAT_KETERANGAN_USAHA;
    }

    public Boolean getFC_NPWP_BADAN_USAHA() {
        return FC_NPWP_BADAN_USAHA;
    }

    public void setFC_NPWP_BADAN_USAHA(Boolean FC_NPWP_BADAN_USAHA) {
        this.FC_NPWP_BADAN_USAHA = FC_NPWP_BADAN_USAHA;
    }

    public int getID_DOKUMEN_FC_NPWP_BADAN_USAHA() {
        return ID_DOKUMEN_FC_NPWP_BADAN_USAHA;
    }

    public void setID_DOKUMEN_FC_NPWP_BADAN_USAHA(int ID_DOKUMEN_FC_NPWP_BADAN_USAHA) {
        this.ID_DOKUMEN_FC_NPWP_BADAN_USAHA = ID_DOKUMEN_FC_NPWP_BADAN_USAHA;
    }
}
