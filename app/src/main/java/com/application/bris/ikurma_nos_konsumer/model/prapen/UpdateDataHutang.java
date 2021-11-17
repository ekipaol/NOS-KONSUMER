package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateDataHutang {
    @SerializedName("ApplicationId")
    @Expose
    private Long applicationId;
    @SerializedName("NamaPemberiUtang")
    @Expose
    private String namaPemberiUtang;
    @SerializedName("NominalPinjaman")
    @Expose
    private Double nominalPinjaman;
    @SerializedName("SisaWaktuPinjaman")
    @Expose
    private Long sisaWaktuPinjaman;
    @SerializedName("AnguranBulanan")
    @Expose
    private Double anguranBulanan;
    @SerializedName("TreatmentPembiayaan")
    @Expose
    private String treatmentPembiayaan;
    @SerializedName("UID")
    @Expose
    private String uid;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getNamaPemberiUtang() {
        return namaPemberiUtang;
    }

    public void setNamaPemberiUtang(String namaPemberiUtang) {
        this.namaPemberiUtang = namaPemberiUtang;
    }

    public Double getNominalPinjaman() {
        return nominalPinjaman;
    }

    public void setNominalPinjaman(Double nominalPinjaman) {
        this.nominalPinjaman = nominalPinjaman;
    }

    public Long getSisaWaktuPinjaman() {
        return sisaWaktuPinjaman;
    }

    public void setSisaWaktuPinjaman(Long sisaWaktuPinjaman) {
        this.sisaWaktuPinjaman = sisaWaktuPinjaman;
    }

    public Double getAnguranBulanan() {
        return anguranBulanan;
    }

    public void setAnguranBulanan(Double anguranBulanan) {
        this.anguranBulanan = anguranBulanan;
    }

    public String getTreatmentPembiayaan() {
        return treatmentPembiayaan;
    }

    public void setTreatmentPembiayaan(String treatmentPembiayaan) {
        this.treatmentPembiayaan = treatmentPembiayaan;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
