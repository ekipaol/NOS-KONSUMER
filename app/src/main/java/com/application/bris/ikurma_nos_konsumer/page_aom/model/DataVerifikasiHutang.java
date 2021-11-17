package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataVerifikasiHutang {
    @SerializedName("Id")
    @Expose
    private Long id;
    @SerializedName("NamaPemberiUtang")
    @Expose
    private String namaPemberiUtang;
    @SerializedName("NominalPinjaman")
    @Expose
    private String nominalPinjaman;
    @SerializedName("SisaWaktuPinjaman")
    @Expose
    private String sisaWaktuPinjaman;
    @SerializedName("AngsuranBulanan")
    @Expose
    private String angsuranBulanan;
    @SerializedName("TreatmentPembiayaan")
    @Expose
    private String treatmentPembiayaan;
    @SerializedName("HasilVerifikasi")
    @Expose
    private String hasilVerifikasi;
    @SerializedName("AngsuranHasilVerifikasi")
    @Expose
    private String angsuranHasilVerifikasi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaPemberiUtang() {
        return namaPemberiUtang;
    }

    public void setNamaPemberiUtang(String namaPemberiUtang) {
        this.namaPemberiUtang = namaPemberiUtang;
    }

    public String getNominalPinjaman() {
        return nominalPinjaman;
    }

    public void setNominalPinjaman(String nominalPinjaman) {
        this.nominalPinjaman = nominalPinjaman;
    }

    public String getSisaWaktuPinjaman() {
        return sisaWaktuPinjaman;
    }

    public void setSisaWaktuPinjaman(String sisaWaktuPinjaman) {
        this.sisaWaktuPinjaman = sisaWaktuPinjaman;
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

    public String getHasilVerifikasi() {
        return hasilVerifikasi;
    }

    public void setHasilVerifikasi(String hasilVerifikasi) {
        this.hasilVerifikasi = hasilVerifikasi;
    }

    public String getAngsuranHasilVerifikasi() {
        return angsuranHasilVerifikasi;
    }

    public void setAngsuranHasilVerifikasi(String angsuranHasilVerifikasi) {
        this.angsuranHasilVerifikasi = angsuranHasilVerifikasi;
    }
}
