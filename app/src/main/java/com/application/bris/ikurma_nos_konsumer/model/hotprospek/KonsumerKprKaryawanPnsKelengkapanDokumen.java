package com.application.bris.ikurma_nos_konsumer.model.hotprospek;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class KonsumerKprKaryawanPnsKelengkapanDokumen extends RealmObject {


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
    @SerializedName("SURAT_KETERANGAN_PEGAWAI_TETAP")
    public Boolean SURAT_KETERANGAN_PEGAWAI_TETAP;
    @SerializedName("ID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP")
    public int ID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP;
    @SerializedName("ID_SURAT_KETERANGAN_PEGAWAI_TETAP")
    public int  ID_SURAT_KETERANGAN_PEGAWAI_TETAP;  //INI MEMANG MIRIP SAMA ATASNYA, INI KHUSUS PURNA
    @SerializedName("SK_JABATAN_TERAKHIR_PNS")
    public Boolean SK_JABATAN_TERAKHIR_PNS;
    @SerializedName("ID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS")
    public int ID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS;
    @SerializedName("ID_SK_JABATAN_TERAKHIR_PNS")
    public int ID_SK_JABATAN_TERAKHIR_PNS; //INI MEMANG MIRIP SAMA ATASNYA, INI KHUSUS PURNA
    @SerializedName("SURAT_REKOMENDASI")
    public Boolean SURAT_REKOMENDASI;
    @SerializedName("ID_DOKUMEN_SURAT_REKOMENDASI")
    public int ID_DOKUMEN_SURAT_REKOMENDASI;
    @SerializedName("SURAT_KUASA_POTONG_GAJI")
    public Boolean SURAT_KUASA_POTONG_GAJI;
    @SerializedName("ID_DOKUMEN_SURAT_KUASA_POTONG_GAJI")
    public int ID_DOKUMEN_SURAT_KUASA_POTONG_GAJI;
    @SerializedName("SLIP_GAJI")
    public Boolean SLIP_GAJI;
    @SerializedName("ID_DOKUMEN_SLIP_GAJI")
    public int ID_DOKUMEN_SLIP_GAJI; //INI MEMANG MIRIP SAMA ATASNYA, INI KHUSUS PURNA
    @SerializedName("ID_SLIP_GAJI")
    public int ID_SLIP_GAJI;
    @SerializedName("REKENING_KORAN")
    public Boolean REKENING_KORAN;
    @SerializedName("ID_DOKUMEN_REKENING_KORAN")
    public int ID_DOKUMEN_REKENING_KORAN;
    @SerializedName("DOKUMEN")
    public Boolean DOKUMEN;
    @SerializedName("ID_DOKUMEN_LAINNYA")
    public int ID_DOKUMEN_LAINNYA;

    @SerializedName("SPR_SPP_RAB")
    public Boolean SPR_SPP_RAB;
    @SerializedName("ID_DOKUMEN_SPR_SPP_RAB")
    public int ID_DOKUMEN_SPR_SPP_RAB;

    @SerializedName("SURAT_KETERANGAN_BEKERJA")
    public Boolean SURAT_KETERANGAN_BEKERJA;
    @SerializedName("ID_DOKUMEN_SURAT_KETERANGAN_BEKERJA")
    public int ID_DOKUMEN_SURAT_KETERANGAN_BEKERJA;

    public Boolean getSURAT_KETERANGAN_BEKERJA() {
        return SURAT_KETERANGAN_BEKERJA;
    }

    public void setSURAT_KETERANGAN_BEKERJA(Boolean SURAT_KETERANGAN_BEKERJA) {
        this.SURAT_KETERANGAN_BEKERJA = SURAT_KETERANGAN_BEKERJA;
    }

    public int getID_DOKUMEN_SURAT_KETERANGAN_BEKERJA() {
        return ID_DOKUMEN_SURAT_KETERANGAN_BEKERJA;
    }

    public void setID_DOKUMEN_SURAT_KETERANGAN_BEKERJA(int ID_DOKUMEN_SURAT_KETERANGAN_BEKERJA) {
        this.ID_DOKUMEN_SURAT_KETERANGAN_BEKERJA = ID_DOKUMEN_SURAT_KETERANGAN_BEKERJA;
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

    public Boolean getSURAT_KETERANGAN_PEGAWAI_TETAP() {
        return SURAT_KETERANGAN_PEGAWAI_TETAP;
    }

    public void setSURAT_KETERANGAN_PEGAWAI_TETAP(Boolean SURAT_KETERANGAN_PEGAWAI_TETAP) {
        this.SURAT_KETERANGAN_PEGAWAI_TETAP = SURAT_KETERANGAN_PEGAWAI_TETAP;
    }

    public int getID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP() {
        return ID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP;
    }

    public void setID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP(int ID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP) {
        this.ID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP = ID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP;
    }

    public int getID_SURAT_KETERANGAN_PEGAWAI_TETAP() {
        return ID_SURAT_KETERANGAN_PEGAWAI_TETAP;
    }

    public void setID_SURAT_KETERANGAN_PEGAWAI_TETAP(int ID_SURAT_KETERANGAN_PEGAWAI_TETAP) {
        this.ID_SURAT_KETERANGAN_PEGAWAI_TETAP = ID_SURAT_KETERANGAN_PEGAWAI_TETAP;
    }

    public Boolean getSK_JABATAN_TERAKHIR_PNS() {
        return SK_JABATAN_TERAKHIR_PNS;
    }

    public void setSK_JABATAN_TERAKHIR_PNS(Boolean SK_JABATAN_TERAKHIR_PNS) {
        this.SK_JABATAN_TERAKHIR_PNS = SK_JABATAN_TERAKHIR_PNS;
    }

    public int getID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS() {
        return ID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS;
    }

    public void setID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS(int ID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS) {
        this.ID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS = ID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS;
    }

    public int getID_SK_JABATAN_TERAKHIR_PNS() {
        return ID_SK_JABATAN_TERAKHIR_PNS;
    }

    public void setID_SK_JABATAN_TERAKHIR_PNS(int ID_SK_JABATAN_TERAKHIR_PNS) {
        this.ID_SK_JABATAN_TERAKHIR_PNS = ID_SK_JABATAN_TERAKHIR_PNS;
    }

    public Boolean getSURAT_REKOMENDASI() {
        return SURAT_REKOMENDASI;
    }

    public void setSURAT_REKOMENDASI(Boolean SURAT_REKOMENDASI) {
        this.SURAT_REKOMENDASI = SURAT_REKOMENDASI;
    }

    public int getID_DOKUMEN_SURAT_REKOMENDASI() {
        return ID_DOKUMEN_SURAT_REKOMENDASI;
    }

    public void setID_DOKUMEN_SURAT_REKOMENDASI(int ID_DOKUMEN_SURAT_REKOMENDASI) {
        this.ID_DOKUMEN_SURAT_REKOMENDASI = ID_DOKUMEN_SURAT_REKOMENDASI;
    }

    public Boolean getSURAT_KUASA_POTONG_GAJI() {
        return SURAT_KUASA_POTONG_GAJI;
    }

    public void setSURAT_KUASA_POTONG_GAJI(Boolean SURAT_KUASA_POTONG_GAJI) {
        this.SURAT_KUASA_POTONG_GAJI = SURAT_KUASA_POTONG_GAJI;
    }

    public int getID_DOKUMEN_SURAT_KUASA_POTONG_GAJI() {
        return ID_DOKUMEN_SURAT_KUASA_POTONG_GAJI;
    }

    public void setID_DOKUMEN_SURAT_KUASA_POTONG_GAJI(int ID_DOKUMEN_SURAT_KUASA_POTONG_GAJI) {
        this.ID_DOKUMEN_SURAT_KUASA_POTONG_GAJI = ID_DOKUMEN_SURAT_KUASA_POTONG_GAJI;
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
}
