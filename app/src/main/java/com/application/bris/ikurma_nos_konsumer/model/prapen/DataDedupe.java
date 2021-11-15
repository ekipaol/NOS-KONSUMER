package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataDedupe {
    @SerializedName("NIK_Nasabah")
    @Expose
    private String nIKNasabah;
    @SerializedName("Nama_Nasabah")
    @Expose
    private String namaNasabah;
    @SerializedName("Produk_NOS")
    @Expose
    private String produkNOS;
    @SerializedName("StatusNasabah")
    @Expose
    private String statusNasabah;
    @SerializedName("StateAplikasi")
    @Expose
    private String stateAplikasi;

    public String getnIKNasabah() {
        return nIKNasabah;
    }

    public void setnIKNasabah(String nIKNasabah) {
        this.nIKNasabah = nIKNasabah;
    }

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }

    public String getProdukNOS() {
        return produkNOS;
    }

    public void setProdukNOS(String produkNOS) {
        this.produkNOS = produkNOS;
    }

    public String getStatusNasabah() {
        return statusNasabah;
    }

    public void setStatusNasabah(String statusNasabah) {
        this.statusNasabah = statusNasabah;
    }

    public String getStateAplikasi() {
        return stateAplikasi;
    }

    public void setStateAplikasi(String stateAplikasi) {
        this.stateAplikasi = stateAplikasi;
    }
}
