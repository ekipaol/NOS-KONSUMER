package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class MparseTotalKualitasPembiayaan {

    @SerializedName("CatatanVerifikasi")
    @Expose
    private String catatanVerifikasi;
    @SerializedName("Img")
    @Expose
    private String img;
    @SerializedName("File_Name")
    @Expose
    private String fileName;
    @SerializedName("Sisa_Pendapatan_Gaji_Aktif")
    @Expose
    private BigDecimal sisaPendapatanGajiAktif;
    @SerializedName("Sisa_Pendapatan_Manfaat_Pensiun")
    @Expose
    private BigDecimal sisaPendapatanManfaatPensiun;
    @SerializedName("TotalPendapatanGajiAktif")
    @Expose
    private BigDecimal totalPendapatanGajiAktif;
    @SerializedName("TotalPendapatanManfaatPensiun")
    @Expose
    private BigDecimal totalPendapatanManfaatPensiun;
    @SerializedName("Total_Angs_Pemb_Eks_NotLunas_DBR")
    @Expose
    private BigDecimal totalAngsPembEksNotLunasDBR;
    @SerializedName("Total_Angs_Pemb_Eks_NotLunas_DSR")
    @Expose
    private BigDecimal totalAngsPembEksNotLunasDSR;
    @SerializedName("GajiAktifDigunakan")
    @Expose
    private String gajiAktifDigunakan;
    @SerializedName("ManfaatPensiunDigunakan")
    @Expose
    private String manfaatPensiunDigunakan;
    @SerializedName("SisaGajiAktif")
    @Expose
    private String sisaGajiAktif;
    @SerializedName("SisaManfaatPensiun")
    @Expose
    private String sisaManfaatPensiun;
    @SerializedName("MaksimalAngsuranBulananGajiAktif")
    @Expose
    private String maksimalAngsuranBulananGajiAktif;
    @SerializedName("MaksimalAngsuranManfaatPensiun")
    @Expose
    private String maksimalAngsuranManfaatPensiun;
    @SerializedName("KetentuanGajiAktif")
    @Expose
    private String ketentuanGajiAktif;
    @SerializedName("KetentuanManfaatPensiun")
    @Expose
    private String ketentuanManfaatPensiun;
    @SerializedName("TreatmentPembayaran")
    @Expose
    private String TreatmentPembayaran;

    public String getCatatanVerifikasi() {
        return catatanVerifikasi;
    }

    public String getImg() {
        return img;
    }

    public String getFileName() {
        return fileName;
    }

    public BigDecimal getSisaPendapatanGajiAktif() {
        return sisaPendapatanGajiAktif;
    }

    public BigDecimal getSisaPendapatanManfaatPensiun() {
        return sisaPendapatanManfaatPensiun;
    }

    public BigDecimal getTotalPendapatanGajiAktif() {
        return totalPendapatanGajiAktif;
    }

    public BigDecimal getTotalPendapatanManfaatPensiun() {
        return totalPendapatanManfaatPensiun;
    }

    public BigDecimal getTotalAngsPembEksNotLunasDBR() {
        return totalAngsPembEksNotLunasDBR;
    }

    public BigDecimal getTotalAngsPembEksNotLunasDSR() {
        return totalAngsPembEksNotLunasDSR;
    }

    public String getGajiAktifDigunakan() {
        return gajiAktifDigunakan;
    }

    public String getManfaatPensiunDigunakan() {
        return manfaatPensiunDigunakan;
    }

    public String getSisaGajiAktif() {
        return sisaGajiAktif;
    }

    public String getSisaManfaatPensiun() {
        return sisaManfaatPensiun;
    }

    public String getMaksimalAngsuranBulananGajiAktif() {
        return maksimalAngsuranBulananGajiAktif;
    }

    public String getMaksimalAngsuranManfaatPensiun() {
        return maksimalAngsuranManfaatPensiun;
    }

    public String getKetentuanGajiAktif() {
        return ketentuanGajiAktif;
    }

    public String getKetentuanManfaatPensiun() {
        return ketentuanManfaatPensiun;
    }

    public String getTreatmentPembayaran() {
        return TreatmentPembayaran;
    }

    public void setCatatanVerifikasi(String catatanVerifikasi) {
        this.catatanVerifikasi = catatanVerifikasi;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setSisaPendapatanGajiAktif(BigDecimal sisaPendapatanGajiAktif) {
        this.sisaPendapatanGajiAktif = sisaPendapatanGajiAktif;
    }

    public void setSisaPendapatanManfaatPensiun(BigDecimal sisaPendapatanManfaatPensiun) {
        this.sisaPendapatanManfaatPensiun = sisaPendapatanManfaatPensiun;
    }

    public void setTotalPendapatanGajiAktif(BigDecimal totalPendapatanGajiAktif) {
        this.totalPendapatanGajiAktif = totalPendapatanGajiAktif;
    }

    public void setTotalPendapatanManfaatPensiun(BigDecimal totalPendapatanManfaatPensiun) {
        this.totalPendapatanManfaatPensiun = totalPendapatanManfaatPensiun;
    }

    public void setTotalAngsPembEksNotLunasDBR(BigDecimal totalAngsPembEksNotLunasDBR) {
        this.totalAngsPembEksNotLunasDBR = totalAngsPembEksNotLunasDBR;
    }

    public void setTotalAngsPembEksNotLunasDSR(BigDecimal totalAngsPembEksNotLunasDSR) {
        this.totalAngsPembEksNotLunasDSR = totalAngsPembEksNotLunasDSR;
    }

    public void setGajiAktifDigunakan(String gajiAktifDigunakan) {
        this.gajiAktifDigunakan = gajiAktifDigunakan;
    }

    public void setManfaatPensiunDigunakan(String manfaatPensiunDigunakan) {
        this.manfaatPensiunDigunakan = manfaatPensiunDigunakan;
    }

    public void setSisaGajiAktif(String sisaGajiAktif) {
        this.sisaGajiAktif = sisaGajiAktif;
    }

    public void setSisaManfaatPensiun(String sisaManfaatPensiun) {
        this.sisaManfaatPensiun = sisaManfaatPensiun;
    }

    public void setMaksimalAngsuranBulananGajiAktif(String maksimalAngsuranBulananGajiAktif) {
        this.maksimalAngsuranBulananGajiAktif = maksimalAngsuranBulananGajiAktif;
    }

    public void setMaksimalAngsuranManfaatPensiun(String maksimalAngsuranManfaatPensiun) {
        this.maksimalAngsuranManfaatPensiun = maksimalAngsuranManfaatPensiun;
    }

    public void setKetentuanGajiAktif(String ketentuanGajiAktif) {
        this.ketentuanGajiAktif = ketentuanGajiAktif;
    }

    public void setKetentuanManfaatPensiun(String ketentuanManfaatPensiun) {
        this.ketentuanManfaatPensiun = ketentuanManfaatPensiun;
    }

    public void setTreatmentPembayaran(String treatmentPembayaran) {
        TreatmentPembayaran = treatmentPembayaran;
    }
}
