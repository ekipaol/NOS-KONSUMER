package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class DataRumahFlpp {
    @SerializedName("id_lokasi")
    private String id_lokasi;
    @SerializedName("id_rumah")
    private String id_rumah;
    @SerializedName("blok")
    private String blok;
    @SerializedName("id_tipe")
    private String id_tipe;
    @SerializedName("nomor")
    private String nomor;
    @SerializedName("npwpMK")
    private String npwpMK;
    @SerializedName("status")
    private String status;

    private String namaBlok;

    public String getNamaBlok() {
        return namaBlok;
    }

    public void setNamaBlok(String namaBlok) {
        this.namaBlok = namaBlok;
    }

    public String getId_lokasi() {
        return id_lokasi;
    }

    public void setId_lokasi(String id_lokasi) {
        this.id_lokasi = id_lokasi;
    }

    public String getId_rumah() {
        return id_rumah;
    }

    public void setId_rumah(String id_rumah) {
        this.id_rumah = id_rumah;
    }

    public String getBlok() {
        return blok;
    }

    public void setBlok(String blok) {
        this.blok = blok;
    }

    public String getId_tipe() {
        return id_tipe;
    }

    public void setId_tipe(String id_tipe) {
        this.id_tipe = id_tipe;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getNpwpMK() {
        return npwpMK;
    }

    public void setNpwpMK(String npwpMK) {
        this.npwpMK = npwpMK;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
