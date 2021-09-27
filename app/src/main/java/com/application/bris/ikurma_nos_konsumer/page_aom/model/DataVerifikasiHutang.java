package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class DataVerifikasiHutang {
    @SerializedName("namaPemberiHutang")
    private String namaPemberiHutang;
    @SerializedName("nominalPinjaman")
    private String nominalPinjaman;
    @SerializedName("sisaJangkaWaktu")
    private String sisaJangkaWaktu;
    @SerializedName("angsuranBulanan")
    private String angsuranBulanan;
    @SerializedName("treatmentPembiayaan")
    private String treatmentPembiayaan;
    @SerializedName("hasilVerifikasi")
    private String hasilVerifikasiHutang;

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

    public String getHasilVerifikasiHutang() {
        return hasilVerifikasiHutang;
    }

    public void setHasilVerifikasiHutang(String hasilVerifikasiHutang) {
        this.hasilVerifikasiHutang = hasilVerifikasiHutang;
    }
}
