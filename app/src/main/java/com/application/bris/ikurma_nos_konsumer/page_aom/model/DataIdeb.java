package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataIdeb implements Serializable {
    @SerializedName("namaLembagaKeuangan")
    private String namaLembagaKeuangan;
    @SerializedName("bakiDebet")
    private String bakiDebet;
    @SerializedName("kualitasPembiayaan")
    private String kualitasPembiayaan;
    @SerializedName("perkiraanAngsuranBulanan")
    private String perkiraanAngsuranBulanan;
    @SerializedName("treatmentPembiayaan")
    private String treatmentPembiayaan;
    @SerializedName("idDokumen")
    private String idDokumen;

    public String getNamaLembagaKeuangan() {
        return namaLembagaKeuangan;
    }

    public void setNamaLembagaKeuangan(String namaLembagaKeuangan) {
        this.namaLembagaKeuangan = namaLembagaKeuangan;
    }

    public String getBakiDebet() {
        return bakiDebet;
    }

    public void setBakiDebet(String bakiDebet) {
        this.bakiDebet = bakiDebet;
    }

    public String getKualitasPembiayaan() {
        return kualitasPembiayaan;
    }

    public void setKualitasPembiayaan(String kualitasPembiayaan) {
        this.kualitasPembiayaan = kualitasPembiayaan;
    }

    public String getPerkiraanAngsuranBulanan() {
        return perkiraanAngsuranBulanan;
    }

    public void setPerkiraanAngsuranBulanan(String perkiraanAngsuranBulanan) {
        this.perkiraanAngsuranBulanan = perkiraanAngsuranBulanan;
    }

    public String getTreatmentPembiayaan() {
        return treatmentPembiayaan;
    }

    public void setTreatmentPembiayaan(String treatmentPembiayaan) {
        this.treatmentPembiayaan = treatmentPembiayaan;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }
}
