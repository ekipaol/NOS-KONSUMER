package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class BlokRumahFlpp {
    @SerializedName("id_lokasi")
    private String id_lokasi;

    @SerializedName("blok")
    private String blok;

    @SerializedName("jumlah_rumah")
    private String jumlah_rumah;

    @SerializedName("id")
    private String id;

    public String getId_lokasi() {
        return id_lokasi;
    }

    public void setId_lokasi(String id_lokasi) {
        this.id_lokasi = id_lokasi;
    }

    public String getBlok() {
        return blok;
    }

    public void setBlok(String blok) {
        this.blok = blok;
    }

    public String getJumlah_rumah() {
        return jumlah_rumah;
    }

    public void setJumlah_rumah(String jumlah_rumah) {
        this.jumlah_rumah = jumlah_rumah;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
