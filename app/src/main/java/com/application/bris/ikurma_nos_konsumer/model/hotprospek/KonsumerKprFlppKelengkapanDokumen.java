package com.application.bris.ikurma_nos_konsumer.model.hotprospek;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class KonsumerKprFlppKelengkapanDokumen extends RealmObject {

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
    @SerializedName("SLIP_GAJI")
    public Boolean SLIP_GAJI;
    @SerializedName("ID_DOKUMEN_SLIP_GAJI")
    public int ID_DOKUMEN_SLIP_GAJI; //INI MEMANG MIRIP SAMA ATASNYA, INI KHUSUS PURNA
    @SerializedName("ID_SLIP_GAJI")
    public int ID_SLIP_GAJI;
    @SerializedName("DOKUMEN")
    public Boolean DOKUMEN;
    @SerializedName("ID_DOKUMEN_LAINNYA")
    public int ID_DOKUMEN_LAINNYA;

    @SerializedName("SPR_SPP_RAB")
    public Boolean SPR_SPP_RAB;
    @SerializedName("ID_DOKUMEN_SPR_SPP_RAB")
    public int ID_DOKUMEN_SPR_SPP_RAB;


    @SerializedName("PERNYATAAN_PENGHASILAN")
    public Boolean PERNYATAAN_PENGHASILAN;
    @SerializedName("ID_DOKUMEN_PERNYATAAN_PENGHASILAN")
    public int ID_DOKUMEN_PERNYATAAN_PENGHASILAN;

    @SerializedName("PEMBERITAHUAN_TAHUNAN")
    public Boolean PEMBERITAHUAN_TAHUNAN;
    @SerializedName("ID_DOKUMEN_PEMBERITAHUAN_TAHUNAN")
    public int ID_DOKUMEN_PEMBERITAHUAN_TAHUNAN;

    @SerializedName("PERNYATAAN_RUMAH")
    public Boolean PERNYATAAN_RUMAH;
    @SerializedName("ID_DOKUMEN_PERNYATAAN_RUMAH")
    public int ID_DOKUMEN_PERNYATAAN_RUMAH;

    @SerializedName("PERNYATAAN_PEMOHON_SUBSIDI")
    public Boolean PERNYATAAN_PEMOHON_SUBSIDI;
    @SerializedName("ID_DOKUMEN_PERNYATAAN_SUBSIDI")
    public int ID_DOKUMEN_PERNYATAAN_PEMOHON_SUBSIDI;

    @SerializedName("PERNYATAAN_PEMOHON_SEJAHTERA")
    public Boolean PERNYATAAN_PEMOHON_SEJAHTERA;
    @SerializedName("ID_DOKUMEN_PERNYATAAN_SEJAHTERA")
    public int ID_DOKUMEN_PERNYATAAN_PEMOHON_SEJAHTERA;

    @SerializedName("PERNYATAAN_KESEDIAAN")
    public Boolean PERNYATAAN_KESEDIAAN;
    @SerializedName("ID_DOKUMEN_PERNYATAAN_KESEDIAAN")
    public int ID_DOKUMEN_PERNYATAAN_KESEDIAAN;

    @SerializedName("PERNYATAAN_KELAIKAN")
    public Boolean PERNYATAAN_KELAIKAN;
    @SerializedName("ID_DOKUMEN_PERNYATAAN_KELAIKAN")
    public int ID_DOKUMEN_PERNYATAAN_KELAIKAN;

    @SerializedName("BERITA_ACARA_SERAH_TERIMA")
    public Boolean BERITA_ACARA_SERAH_TERIMA;
    @SerializedName("ID_DOKUMEN_BERITA_ACARA_SERAH_TERIMA")
    public int ID_DOKUMEN_BERITA_ACARA_SERAH_TERIMA;


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

    public Boolean getSLIP_GAJI() {
        return SLIP_GAJI;
    }

    public void setSLIP_GAJI(Boolean SLIP_GAJI) {
        this.SLIP_GAJI = SLIP_GAJI;
    }

    public int getID_DOKUMEN_SLIP_GAJI() {
        return ID_DOKUMEN_SLIP_GAJI;
    }

    public void setID_DOKUMEN_SLIP_GAJI(int ID_DOKUMEN_SLIP_GAJI) {
        this.ID_DOKUMEN_SLIP_GAJI = ID_DOKUMEN_SLIP_GAJI;
    }

    public int getID_SLIP_GAJI() {
        return ID_SLIP_GAJI;
    }

    public void setID_SLIP_GAJI(int ID_SLIP_GAJI) {
        this.ID_SLIP_GAJI = ID_SLIP_GAJI;
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

    public Boolean getPERNYATAAN_PENGHASILAN() {
        return PERNYATAAN_PENGHASILAN;
    }

    public void setPERNYATAAN_PENGHASILAN(Boolean PERNYATAAN_PENGHASILAN) {
        this.PERNYATAAN_PENGHASILAN = PERNYATAAN_PENGHASILAN;
    }

    public int getID_DOKUMEN_PERNYATAAN_PENGHASILAN() {
        return ID_DOKUMEN_PERNYATAAN_PENGHASILAN;
    }

    public void setID_DOKUMEN_PERNYATAAN_PENGHASILAN(int ID_DOKUMEN_PERNYATAAN_PENGHASILAN) {
        this.ID_DOKUMEN_PERNYATAAN_PENGHASILAN = ID_DOKUMEN_PERNYATAAN_PENGHASILAN;
    }

    public Boolean getPEMBERITAHUAN_TAHUNAN() {
        return PEMBERITAHUAN_TAHUNAN;
    }

    public void setPEMBERITAHUAN_TAHUNAN(Boolean PEMBERITAHUAN_TAHUNAN) {
        this.PEMBERITAHUAN_TAHUNAN = PEMBERITAHUAN_TAHUNAN;
    }

    public int getID_DOKUMEN_PEMBERITAHUAN_TAHUNAN() {
        return ID_DOKUMEN_PEMBERITAHUAN_TAHUNAN;
    }

    public void setID_DOKUMEN_PEMBERITAHUAN_TAHUNAN(int ID_DOKUMEN_PEMBERITAHUAN_TAHUNAN) {
        this.ID_DOKUMEN_PEMBERITAHUAN_TAHUNAN = ID_DOKUMEN_PEMBERITAHUAN_TAHUNAN;
    }

    public Boolean getPERNYATAAN_RUMAH() {
        return PERNYATAAN_RUMAH;
    }

    public void setPERNYATAAN_RUMAH(Boolean PERNYATAAN_RUMAH) {
        this.PERNYATAAN_RUMAH = PERNYATAAN_RUMAH;
    }

    public int getID_DOKUMEN_PERNYATAAN_RUMAH() {
        return ID_DOKUMEN_PERNYATAAN_RUMAH;
    }

    public void setID_DOKUMEN_PERNYATAAN_RUMAH(int ID_DOKUMEN_PERNYATAAN_RUMAH) {
        this.ID_DOKUMEN_PERNYATAAN_RUMAH = ID_DOKUMEN_PERNYATAAN_RUMAH;
    }

    public Boolean getPERNYATAAN_PEMOHON_SUBSIDI() {
        return PERNYATAAN_PEMOHON_SUBSIDI;
    }

    public void setPERNYATAAN_PEMOHON_SUBSIDI(Boolean PERNYATAAN_PEMOHON_SUBSIDI) {
        this.PERNYATAAN_PEMOHON_SUBSIDI = PERNYATAAN_PEMOHON_SUBSIDI;
    }

    public int getID_DOKUMEN_PERNYATAAN_PEMOHON_SUBSIDI() {
        return ID_DOKUMEN_PERNYATAAN_PEMOHON_SUBSIDI;
    }

    public void setID_DOKUMEN_PERNYATAAN_PEMOHON_SUBSIDI(int ID_DOKUMEN_PERNYATAAN_PEMOHON_SUBSIDI) {
        this.ID_DOKUMEN_PERNYATAAN_PEMOHON_SUBSIDI = ID_DOKUMEN_PERNYATAAN_PEMOHON_SUBSIDI;
    }

    public Boolean getPERNYATAAN_PEMOHON_SEJAHTERA() {
        return PERNYATAAN_PEMOHON_SEJAHTERA;
    }

    public void setPERNYATAAN_PEMOHON_SEJAHTERA(Boolean PERNYATAAN_PEMOHON_SEJAHTERA) {
        this.PERNYATAAN_PEMOHON_SEJAHTERA = PERNYATAAN_PEMOHON_SEJAHTERA;
    }

    public int getID_DOKUMEN_PERNYATAAN_PEMOHON_SEJAHTERA() {
        return ID_DOKUMEN_PERNYATAAN_PEMOHON_SEJAHTERA;
    }

    public void setID_DOKUMEN_PERNYATAAN_PEMOHON_SEJAHTERA(int ID_DOKUMEN_PERNYATAAN_PEMOHON_SEJAHTERA) {
        this.ID_DOKUMEN_PERNYATAAN_PEMOHON_SEJAHTERA = ID_DOKUMEN_PERNYATAAN_PEMOHON_SEJAHTERA;
    }

    public Boolean getPERNYATAAN_KESEDIAAN() {
        return PERNYATAAN_KESEDIAAN;
    }

    public void setPERNYATAAN_KESEDIAAN(Boolean PERNYATAAN_KESEDIAAN) {
        this.PERNYATAAN_KESEDIAAN = PERNYATAAN_KESEDIAAN;
    }

    public int getID_DOKUMEN_PERNYATAAN_KESEDIAAN() {
        return ID_DOKUMEN_PERNYATAAN_KESEDIAAN;
    }

    public void setID_DOKUMEN_PERNYATAAN_KESEDIAAN(int ID_DOKUMEN_PERNYATAAN_KESEDIAAN) {
        this.ID_DOKUMEN_PERNYATAAN_KESEDIAAN = ID_DOKUMEN_PERNYATAAN_KESEDIAAN;
    }

    public Boolean getPERNYATAAN_KELAIKAN() {
        return PERNYATAAN_KELAIKAN;
    }

    public void setPERNYATAAN_KELAIKAN(Boolean PERNYATAAN_KELAIKAN) {
        this.PERNYATAAN_KELAIKAN = PERNYATAAN_KELAIKAN;
    }

    public int getID_DOKUMEN_PERNYATAAN_KELAIKAN() {
        return ID_DOKUMEN_PERNYATAAN_KELAIKAN;
    }

    public void setID_DOKUMEN_PERNYATAAN_KELAIKAN(int ID_DOKUMEN_PERNYATAAN_KELAIKAN) {
        this.ID_DOKUMEN_PERNYATAAN_KELAIKAN = ID_DOKUMEN_PERNYATAAN_KELAIKAN;
    }

    public Boolean getBERITA_ACARA_SERAH_TERIMA() {
        return BERITA_ACARA_SERAH_TERIMA;
    }

    public void setBERITA_ACARA_SERAH_TERIMA(Boolean BERITA_ACARA_SERAH_TERIMA) {
        this.BERITA_ACARA_SERAH_TERIMA = BERITA_ACARA_SERAH_TERIMA;
    }

    public int getID_DOKUMEN_BERITA_ACARA_SERAH_TERIMA() {
        return ID_DOKUMEN_BERITA_ACARA_SERAH_TERIMA;
    }

    public void setID_DOKUMEN_BERITA_ACARA_SERAH_TERIMA(int ID_DOKUMEN_BERITA_ACARA_SERAH_TERIMA) {
        this.ID_DOKUMEN_BERITA_ACARA_SERAH_TERIMA = ID_DOKUMEN_BERITA_ACARA_SERAH_TERIMA;
    }
}
