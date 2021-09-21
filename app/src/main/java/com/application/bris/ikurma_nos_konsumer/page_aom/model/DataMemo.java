package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class DataMemo {
    @SerializedName("nama")
    private String nama;
    @SerializedName("jabatan")
    private String jabatan;
    @SerializedName("tahapAplikasi")
    private String tahapAplikasi;
    @SerializedName("jenisPutusan")
    private String jenisPutusan;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("memo")
    private String memo;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getTahapAplikasi() {
        return tahapAplikasi;
    }

    public void setTahapAplikasi(String tahapAplikasi) {
        this.tahapAplikasi = tahapAplikasi;
    }

    public String getJenisPutusan() {
        return jenisPutusan;
    }

    public void setJenisPutusan(String jenisPutusan) {
        this.jenisPutusan = jenisPutusan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
