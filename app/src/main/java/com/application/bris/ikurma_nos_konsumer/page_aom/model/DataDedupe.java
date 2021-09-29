package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class DataDedupe {
    @SerializedName("namaNasabah")
    private String namaNasabah;
    @SerializedName("produk")
    private String produk;
    @SerializedName("statusDalamAplikasi")
    private String statusDalamAplikasi;
    @SerializedName("statusTerakhir")
    private String statusTerakhir;

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public String getStatusDalamAplikasi() {
        return statusDalamAplikasi;
    }

    public void setStatusDalamAplikasi(String statusDalamAplikasi) {
        this.statusDalamAplikasi = statusDalamAplikasi;
    }

    public String getStatusTerakhir() {
        return statusTerakhir;
    }

    public void setStatusTerakhir(String statusTerakhir) {
        this.statusTerakhir = statusTerakhir;
    }
}
