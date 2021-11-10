package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqUpdateDataPembiayaan {
    @SerializedName("Kode_Cabang")
    @Expose
    private String kodeCabang;
    @SerializedName("Group_Produk")
    @Expose
    private String groupProduk;
    @SerializedName("Application_Id")
    @Expose
    private Long applicationId;
    @SerializedName("Tipe_Produk")
    @Expose
    private String tipeProduk;
    @SerializedName("Segmen")
    @Expose
    private String segmen;
    @SerializedName("Jenis_Pembiayaan")
    @Expose
    private String jenisPembiayaan;
    @SerializedName("Program")
    @Expose
    private String program;
    @SerializedName("Tujuan_Pembiayaan")
    @Expose
    private String tujuanPembiayaan;
    @SerializedName("Memiliki_Aset")
    @Expose
    private String memilikiAset;
    @SerializedName("Offering_Price")
    @Expose
    private Double offeringPrice;
    @SerializedName("Pilihan_Akad")
    @Expose
    private String pilihanAkad;
    @SerializedName("Syarat_Umum_Akad")
    @Expose
    private String syaratUmumAkad;
    @SerializedName("UID")
    @Expose
    private String uid;

    public String getKodeCabang() {
        return kodeCabang;
    }

    public void setKodeCabang(String kodeCabang) {
        this.kodeCabang = kodeCabang;
    }

    public String getGroupProduk() {
        return groupProduk;
    }

    public void setGroupProduk(String groupProduk) {
        this.groupProduk = groupProduk;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getTipeProduk() {
        return tipeProduk;
    }

    public void setTipeProduk(String tipeProduk) {
        this.tipeProduk = tipeProduk;
    }

    public String getSegmen() {
        return segmen;
    }

    public void setSegmen(String segmen) {
        this.segmen = segmen;
    }

    public String getJenisPembiayaan() {
        return jenisPembiayaan;
    }

    public void setJenisPembiayaan(String jenisPembiayaan) {
        this.jenisPembiayaan = jenisPembiayaan;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getTujuanPembiayaan() {
        return tujuanPembiayaan;
    }

    public void setTujuanPembiayaan(String tujuanPembiayaan) {
        this.tujuanPembiayaan = tujuanPembiayaan;
    }

    public String getMemilikiAset() {
        return memilikiAset;
    }

    public void setMemilikiAset(String memilikiAset) {
        this.memilikiAset = memilikiAset;
    }

    public Double getOfferingPrice() {
        return offeringPrice;
    }

    public void setOfferingPrice(Double offeringPrice) {
        this.offeringPrice = offeringPrice;
    }

    public String getPilihanAkad() {
        return pilihanAkad;
    }

    public void setPilihanAkad(String pilihanAkad) {
        this.pilihanAkad = pilihanAkad;
    }

    public String getSyaratUmumAkad() {
        return syaratUmumAkad;
    }

    public void setSyaratUmumAkad(String syaratUmumAkad) {
        this.syaratUmumAkad = syaratUmumAkad;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
