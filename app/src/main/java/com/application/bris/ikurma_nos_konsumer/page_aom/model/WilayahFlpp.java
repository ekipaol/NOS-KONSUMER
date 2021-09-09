package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class WilayahFlpp {

    @SerializedName("ID_KODE_WILAYAH")
    private String ID_KODE_WILAYAH;

    @SerializedName("NAMA_WILAYAH")
    private String NAMA_WILAYAH;

    @SerializedName("JENIS_WILAYAH")
    private String JENIS_WILAYAH;

    @SerializedName("FID_KODE_PROVINSI")
    private String FIDE_KODE_PROVINSI;

    public String getID_KODE_WILAYAH() {
        return ID_KODE_WILAYAH;
    }

    public void setID_KODE_WILAYAH(String ID_KODE_WILAYAH) {
        this.ID_KODE_WILAYAH = ID_KODE_WILAYAH;
    }

    public String getNAMA_WILAYAH() {
        return NAMA_WILAYAH;
    }

    public void setNAMA_WILAYAH(String NAMA_WILAYAH) {
        this.NAMA_WILAYAH = NAMA_WILAYAH;
    }

    public String getJENIS_WILAYAH() {
        return JENIS_WILAYAH;
    }

    public void setJENIS_WILAYAH(String JENIS_WILAYAH) {
        this.JENIS_WILAYAH = JENIS_WILAYAH;
    }

    public String getFIDE_KODE_PROVINSI() {
        return FIDE_KODE_PROVINSI;
    }

    public void setFIDE_KODE_PROVINSI(String FIDE_KODE_PROVINSI) {
        this.FIDE_KODE_PROVINSI = FIDE_KODE_PROVINSI;
    }
}
