package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class MparseResponseSimulasiBiayaBiaya {

    @SerializedName("AsuransiPenjaminId")
    @Expose
    private Long asuransiPenjaminId;
    @SerializedName("NamaAsuransi")
    @Expose
    private String namaAsuransi;
    @SerializedName("BiayaAsuransiPenjamin")
    @Expose
    private BigDecimal biayaAsuransiPenjamin;
    @SerializedName("TreatementBiayaAsuransiPenjamin")
    @Expose
    private String treatementBiayaAsuransiPenjamin;
    @SerializedName("BiayaAsuransiKhusus")
    @Expose
    private BigDecimal biayaAsuransiKhusus;
    @SerializedName("TreatmentBiayaAsuransiKhusus")
    @Expose
    private String treatmentBiayaAsuransiKhusus;
    @SerializedName("BiayaAdministrasi")
    @Expose
    private BigDecimal biayaAdministrasi;
    @SerializedName("TreatementBiayaAdministrasi")
    @Expose
    private String treatementBiayaAdministrasi;
    @SerializedName("BiayaPenalti")
    @Expose
    private BigDecimal biayaPenalti;
    @SerializedName("TreatmentBiayaPenalti")
    @Expose
    private String treatmentBiayaPenalti;
    @SerializedName("BiayaMaterai")
    @Expose
    private BigDecimal biayaMaterai;
    @SerializedName("TreatmentBiayaMaterai")
    @Expose
    private String treatmentBiayaMaterai;
    @SerializedName("ManfaatAsuransi")
    @Expose
    private String ManfaatAsuransi;
    @SerializedName("KeteranganManfaatAsuransi")
    @Expose
    private String keteranganManfaatAsuransi;

    public String getKeteranganManfaatAsuransi() {
        return keteranganManfaatAsuransi;
    }

    public void setKeteranganManfaatAsuransi(String keteranganManfaatAsuransi) {
        this.keteranganManfaatAsuransi = keteranganManfaatAsuransi;
    }

    public String getManfaatAsuransi() {
        return ManfaatAsuransi;
    }

    public void setManfaatAsuransi(String manfaatAsuransi) {
        ManfaatAsuransi = manfaatAsuransi;
    }

    public Long getAsuransiPenjaminId() {
        return asuransiPenjaminId;
    }

    public void setAsuransiPenjaminId(Long asuransiPenjamin) {
        this.asuransiPenjaminId = asuransiPenjamin;
    }
    public String getNamaAsuransi() {
        return namaAsuransi;
    }

    public void setNamaAsuransi(String NamaAsuransi) {
        this.namaAsuransi = NamaAsuransi;
    }

    public BigDecimal getBiayaAsuransiPenjamin() {
        return biayaAsuransiPenjamin;
    }

    public void setBiayaAsuransiPenjamin(BigDecimal biayaAsuransiPenjamin) {
        this.biayaAsuransiPenjamin = biayaAsuransiPenjamin;
    }

    public String getTreatementBiayaAsuransiPenjamin() {
        return treatementBiayaAsuransiPenjamin;
    }

    public void setTreatementBiayaAsuransiPenjamin(String treatementBiayaAsuransiPenjamin) {
        this.treatementBiayaAsuransiPenjamin = treatementBiayaAsuransiPenjamin;
    }

    public BigDecimal getBiayaAsuransiKhusus() {
        return biayaAsuransiKhusus;
    }

    public void setBiayaAsuransiKhusus(BigDecimal biayaAsuransiKhusus) {
        this.biayaAsuransiKhusus = biayaAsuransiKhusus;
    }

    public String getTreatmentBiayaAsuransiKhusus() {
        return treatmentBiayaAsuransiKhusus;
    }

    public void setTreatmentBiayaAsuransiKhusus(String treatmentBiayaAsuransiKhusus) {
        this.treatmentBiayaAsuransiKhusus = treatmentBiayaAsuransiKhusus;
    }

    public BigDecimal getBiayaAdministrasi() {
        return biayaAdministrasi;
    }

    public void setBiayaAdministrasi(BigDecimal biayaAdministrasi) {
        this.biayaAdministrasi = biayaAdministrasi;
    }

    public String getTreatementBiayaAdministrasi() {
        return treatementBiayaAdministrasi;
    }

    public void setTreatementBiayaAdministrasi(String treatementBiayaAdministrasi) {
        this.treatementBiayaAdministrasi = treatementBiayaAdministrasi;
    }

    public BigDecimal getBiayaPenalti() {
        return biayaPenalti;
    }

    public void setBiayaPenalti(BigDecimal biayaPenalti) {
        this.biayaPenalti = biayaPenalti;
    }

    public String getTreatmentBiayaPenalti() {
        return treatmentBiayaPenalti;
    }

    public void setTreatmentBiayaPenalti(String treatmentBiayaPenalti) {
        this.treatmentBiayaPenalti = treatmentBiayaPenalti;
    }

    public BigDecimal getBiayaMaterai() {
        return biayaMaterai;
    }

    public void setBiayaMaterai(BigDecimal biayaMaterai) {
        this.biayaMaterai = biayaMaterai;
    }

    public String getTreatmentBiayaMaterai() {
        return treatmentBiayaMaterai;
    }

    public void setTreatmentBiayaMaterai(String treatmentBiayaMaterai) {
        this.treatmentBiayaMaterai = treatmentBiayaMaterai;
    }
}
