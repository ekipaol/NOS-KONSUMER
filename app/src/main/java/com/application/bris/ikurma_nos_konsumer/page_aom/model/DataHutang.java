package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class DataHutang {
    @SerializedName("NamaPemberiUtang")
    private String namaPemberiHutang;
    @SerializedName("NominalPinjaman")
    private String nominalPinjaman;
    @SerializedName("SisaWaktuPinjaman")
    private String sisaJangkaWaktu;
    @SerializedName("AngsuranBulanan")
    private String angsuranBulanan;
    @SerializedName("TreatmentPembiayaan")
    private String treatmentPembiayaan;
    @SerializedName("Id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaPemberiHutang() {
        return namaPemberiHutang;
    }

    public void setNamaPemberiHutang(String namaPemberiHutang) {
        this.namaPemberiHutang = namaPemberiHutang;
    }

    public String getNominalPinjaman() {
        return nominalPinjaman;
    }

    public void setNominalPinjaman(String nominalPinjaman) {
        this.nominalPinjaman = nominalPinjaman;
    }

    public String getSisaJangkaWaktu() {
        return sisaJangkaWaktu;
    }

    public void setSisaJangkaWaktu(String sisaJangkaWaktu) {
        this.sisaJangkaWaktu = sisaJangkaWaktu;
    }

    public String getAngsuranBulanan() {
        return angsuranBulanan;
    }

    public void setAngsuranBulanan(String angsuranBulanan) {
        this.angsuranBulanan = angsuranBulanan;
    }

    public String getTreatmentPembiayaan() {
        return treatmentPembiayaan;
    }

    public void setTreatmentPembiayaan(String treatmentPembiayaan) {
        this.treatmentPembiayaan = treatmentPembiayaan;
    }
}
