package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 11/06/2019.
 */

public class hotprospek {

    @SerializedName("FID_CIF_LAS")
    private int fid_cif_las;
    @SerializedName("ID_ST_APLIKASI")
    private int id_st_aplikasi;
    @SerializedName("KODE_PRODUK")
    private String kode_produk;
    @SerializedName("PLAFOND_INDUK")
    private int plafond_induk;
    @SerializedName("KLASIFIKASI_KREDIT")
    private String klasifikasi_kredit;
    @SerializedName("STATUS_APLIKASI")
    private String status_aplikasi;
    @SerializedName("NAMA_PRODUK")
    private String nama_produk;
    @SerializedName("ID_APLIKASI")
    private int id_aplikasi;
    @SerializedName("TANGGAL_ENTRY")
    private String tanggal_entry;
    @SerializedName("FID_PHOTO")
    private int fid_photo;
    @SerializedName("JW")
    private int jw;
    @SerializedName("NAMA_DEBITUR_1")
    private String nama_debitur_1;
    @SerializedName("FLAG_DATA_LENGKAP")
    private int flag_data_lengkap;
    @SerializedName("FLAG_AGUNAN")
    private int flag_agunan;
    @SerializedName("FLAG_PRESCREENING")
    private int flag_prescreening;
    @SerializedName("FLAG_DATA_FINANSIAL")
    private int flag_finansial;
    @SerializedName("FLAG_LKN")
    private int flag_lkn;
    @SerializedName("FLAG_SCORING")
    private int flag_scoring;
    @SerializedName("ID_TUJUAN")
    private int id_tujuan;
    @SerializedName("NAMA_TUJUAN")
    private String nama_tujuan;
    @SerializedName("FLAG_DATA_PEMBIAYAAN")
    private int flag_data_pembiayaan;
    @SerializedName("FLAG_DOKUMEN")
    private int flag_dokumen;
    @SerializedName("FLAG_MEMO_SALES")
    private int flag_memo_sales;
    @SerializedName("FLAG_RPC")
    private int flag_rpc;
    @SerializedName("NO_AKAD")
    private String nOAKAD;
    @SerializedName("PROGRAM_NAME")
    private String PROGRAM_NAME;

    @SerializedName("GIMMICK_ID")
    private int GIMMICK_ID;

    @SerializedName("LOAN_TYPE")
    private String LOAN_TYPE;


    public hotprospek() {
    }

    public hotprospek(int fid_cif_las, int id_st_aplikasi, String kode_produk, int plafond_induk, String klasifikasi_kredit, String status_aplikasi, String nama_produk, int id_aplikasi, String tanggal_entry, int fid_photo, int jw, String nama_debitur_1, int flag_data_lengkap, int flag_agunan, int flag_prescreening, int flag_lkn, int flag_scoring, int id_tujuan, String nama_tujuan, int flag_data_pembiayaan, int flag_dokumen, int flag_rpc, String nOAKAD, int flag_finansial) {
        this.fid_cif_las = fid_cif_las;
        this.id_st_aplikasi = id_st_aplikasi;
        this.kode_produk = kode_produk;
        this.plafond_induk = plafond_induk;
        this.klasifikasi_kredit = klasifikasi_kredit;
        this.status_aplikasi = status_aplikasi;
        this.nama_produk = nama_produk;
        this.id_aplikasi = id_aplikasi;
        this.tanggal_entry = tanggal_entry;
        this.fid_photo = fid_photo;
        this.jw = jw;
        this.nama_debitur_1 = nama_debitur_1;
        this.flag_data_lengkap = flag_data_lengkap;
        this.flag_agunan = flag_agunan;
        this.flag_prescreening = flag_prescreening;
        this.flag_lkn = flag_lkn;
        this.flag_scoring = flag_scoring;
        this.id_tujuan = id_tujuan;
        this.nama_tujuan = nama_tujuan;
        this.flag_data_pembiayaan = flag_data_pembiayaan;
        this.flag_dokumen = flag_dokumen;
        this.flag_rpc = flag_rpc;
        this.nOAKAD = nOAKAD;
        this.flag_finansial=flag_finansial;
    }

    public String getLOAN_TYPE() {
        return LOAN_TYPE;
    }

    public void setLOAN_TYPE(String LOAN_TYPE) {
        this.LOAN_TYPE = LOAN_TYPE;
    }

    public int getGIMMICK_ID() {
        return GIMMICK_ID;
    }

    public void setGIMMICK_ID(int GIMMICK_ID) {
        this.GIMMICK_ID = GIMMICK_ID;
    }

    public String getPROGRAM_NAME() {
        return PROGRAM_NAME;
    }

    public void setPROGRAM_NAME(String PROGRAM_NAME) {
        this.PROGRAM_NAME = PROGRAM_NAME;
    }

    public int getFlag_memo_sales() {
        return flag_memo_sales;
    }

    public void setFlag_memo_sales(int flag_memo_sales) {
        this.flag_memo_sales = flag_memo_sales;
    }

    public int getFlag_finansial() {
        return flag_finansial;
    }

    public void setFlag_finansial(int flag_finansial) {
        this.flag_finansial = flag_finansial;
    }

    public int getFid_cif_las() {
        return fid_cif_las;
    }

    public void setFid_cif_las(int fid_cif_las) {
        this.fid_cif_las = fid_cif_las;
    }

    public int getId_st_aplikasi() {
        return id_st_aplikasi;
    }

    public void setId_st_aplikasi(int id_st_aplikasi) {
        this.id_st_aplikasi = id_st_aplikasi;
    }

    public String getKode_produk() {
        return kode_produk;
    }

    public void setKode_produk(String kode_produk) {
        this.kode_produk = kode_produk;
    }

    public int getPlafond_induk() {
        return plafond_induk;
    }

    public void setPlafond_induk(int plafond_induk) {
        this.plafond_induk = plafond_induk;
    }

    public String getKlasifikasi_kredit() {
        return klasifikasi_kredit;
    }

    public void setKlasifikasi_kredit(String klasifikasi_kredit) {
        this.klasifikasi_kredit = klasifikasi_kredit;
    }

    public String getStatus_aplikasi() {
        return status_aplikasi;
    }

    public void setStatus_aplikasi(String status_aplikasi) {
        this.status_aplikasi = status_aplikasi;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public int getId_aplikasi() {
        return id_aplikasi;
    }

    public void setId_aplikasi(int id_aplikasi) {
        this.id_aplikasi = id_aplikasi;
    }

    public String getTanggal_entry() {
        return tanggal_entry;
    }

    public void setTanggal_entry(String tanggal_entry) {
        this.tanggal_entry = tanggal_entry;
    }

    public int getFid_photo() {
        return fid_photo;
    }

    public void setFid_photo(int fid_photo) {
        this.fid_photo = fid_photo;
    }

    public int getJw() {
        return jw;
    }

    public void setJw(int jw) {
        this.jw = jw;
    }

    public String getNama_debitur_1() {
        return nama_debitur_1;
    }

    public void setNama_debitur_1(String nama_debitur_1) {
        this.nama_debitur_1 = nama_debitur_1;
    }

    public int getFlag_data_lengkap() {
        return flag_data_lengkap;
    }

    public void setFlag_data_lengkap(int flag_data_lengkap) {
        this.flag_data_lengkap = flag_data_lengkap;
    }

    public int getFlag_agunan() {
        return flag_agunan;
    }

    public void setFlag_agunan(int flag_agunan) {
        this.flag_agunan = flag_agunan;
    }

    public int getFlag_prescreening() {
        return flag_prescreening;
    }

    public void setFlag_prescreening(int flag_prescreening) {
        this.flag_prescreening = flag_prescreening;
    }

    public int getFlag_lkn() {
        return flag_lkn;
    }

    public void setFlag_lkn(int flag_lkn) {
        this.flag_lkn = flag_lkn;
    }

    public int getFlag_scoring() {
        return flag_scoring;
    }

    public void setFlag_scoring(int flag_scoring) {
        this.flag_scoring = flag_scoring;
    }

    public int getId_tujuan() {
        return id_tujuan;
    }

    public void setId_tujuan(int id_tujuan) {
        this.id_tujuan = id_tujuan;
    }

    public String getNama_tujuan() {
        return nama_tujuan;
    }

    public void setNama_tujuan(String nama_tujuan) {
        this.nama_tujuan = nama_tujuan;
    }

    public int getFlag_data_pembiayaan() {
        return flag_data_pembiayaan;
    }

    public void setFlag_data_pembiayaan(int flag_data_pembiayaan) {
        this.flag_data_pembiayaan = flag_data_pembiayaan;
    }

    public int getFlag_dokumen() {
        return flag_dokumen;
    }

    public void setFlag_dokumen(int flag_dokumen) {
        this.flag_dokumen = flag_dokumen;
    }

    public int getFlag_rpc() {
        return flag_rpc;
    }

    public void setFlag_rpc(int flag_rpc) {
        this.flag_rpc = flag_rpc;
    }

    public String getnOAKAD() {
        return nOAKAD;
    }

    public void setnOAKAD(String nOAKAD) {
        this.nOAKAD = nOAKAD;
    }
}
