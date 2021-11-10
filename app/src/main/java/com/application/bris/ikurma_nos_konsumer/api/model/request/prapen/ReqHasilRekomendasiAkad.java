package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.SerializedName;

public class ReqHasilRekomendasiAkad {

    @SerializedName("Tipe_Produk")
    private String Tipe_Produk;

    @SerializedName("Jenis_Produk")
    private String Jenis_Produk;

    @SerializedName("Tujuan_Pembiayaan")
    private String Tujuan_Pembiayaan;

    @SerializedName("Have_Asset")
    private String Have_Asset;

    public String getTipe_Produk() {
        return Tipe_Produk;
    }

    public void setTipe_Produk(String tipe_Produk) {
        Tipe_Produk = tipe_Produk;
    }

    public String getJenis_Produk() {
        return Jenis_Produk;
    }

    public void setJenis_Produk(String jenis_Produk) {
        Jenis_Produk = jenis_Produk;
    }

    public String getTujuan_Pembiayaan() {
        return Tujuan_Pembiayaan;
    }

    public void setTujuan_Pembiayaan(String tujuan_Pembiayaan) {
        Tujuan_Pembiayaan = tujuan_Pembiayaan;
    }

    public String getHave_Asset() {
        return Have_Asset;
    }

    public void setHave_Asset(String have_Asset) {
        Have_Asset = have_Asset;
    }
}
