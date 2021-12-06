package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MparseTotalKualitasPembiayaan {

    @SerializedName("CatatanVerifikasi2")
    @Expose
    private String catatanVerifikasi2;
    @SerializedName("File_Name")
    @Expose
    private String fileName;
    @SerializedName("Img")
    @Expose
    private String img;
    @SerializedName("TotalAngsuranPembiayaan")
    @Expose
    private Long totalAngsuranPembiayaan;
    @SerializedName("SisaPendapatan")
    @Expose
    private String sisaPendapatan;
    @SerializedName("DSRyangdigunakan")
    @Expose
    private String dSRyangdigunakan;
    @SerializedName("DBRyangdigunakan")
    @Expose
    private String dBRyangdigunakan;
    @SerializedName("SisaDSR")
    @Expose
    private String sisaDSR;
    @SerializedName("SisaDBR")
    @Expose
    private String sisaDBR;
    @SerializedName("MaksimalAngsuranBulananDBR")
    @Expose
    private String maksimalAngsuranBulananDBR;
    @SerializedName("MaksimalAngsuranBulananDSR")
    @Expose
    private String maksimalAngsuranBulananDSR;
    @SerializedName("DSRFinal")
    @Expose
    private String dSRFinal;
    @SerializedName("DBRFinal")
    @Expose
    private String dBRFinal;

    public String getCatatanVerifikasi2() {
        return catatanVerifikasi2;
    }

    public void setCatatanVerifikasi2(String catatanVerifikasi2) {
        this.catatanVerifikasi2 = catatanVerifikasi2;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getTotalAngsuranPembiayaan() {
        return totalAngsuranPembiayaan;
    }

    public void setTotalAngsuranPembiayaan(Long totalAngsuranPembiayaan) {
        this.totalAngsuranPembiayaan = totalAngsuranPembiayaan;
    }

    public String getSisaPendapatan() {
        return sisaPendapatan;
    }

    public void setSisaPendapatan(String sisaPendapatan) {
        this.sisaPendapatan = sisaPendapatan;
    }

    public String getdSRyangdigunakan() {
        return dSRyangdigunakan;
    }

    public void setdSRyangdigunakan(String dSRyangdigunakan) {
        this.dSRyangdigunakan = dSRyangdigunakan;
    }

    public String getdBRyangdigunakan() {
        return dBRyangdigunakan;
    }

    public void setdBRyangdigunakan(String dBRyangdigunakan) {
        this.dBRyangdigunakan = dBRyangdigunakan;
    }

    public String getSisaDSR() {
        return sisaDSR;
    }

    public void setSisaDSR(String sisaDSR) {
        this.sisaDSR = sisaDSR;
    }

    public String getSisaDBR() {
        return sisaDBR;
    }

    public void setSisaDBR(String sisaDBR) {
        this.sisaDBR = sisaDBR;
    }

    public String getMaksimalAngsuranBulananDBR() {
        return maksimalAngsuranBulananDBR;
    }

    public void setMaksimalAngsuranBulananDBR(String maksimalAngsuranBulananDBR) {
        this.maksimalAngsuranBulananDBR = maksimalAngsuranBulananDBR;
    }

    public String getMaksimalAngsuranBulananDSR() {
        return maksimalAngsuranBulananDSR;
    }

    public void setMaksimalAngsuranBulananDSR(String maksimalAngsuranBulananDSR) {
        this.maksimalAngsuranBulananDSR = maksimalAngsuranBulananDSR;
    }

    public String getdSRFinal() {
        return dSRFinal;
    }

    public void setdSRFinal(String dSRFinal) {
        this.dSRFinal = dSRFinal;
    }

    public String getdBRFinal() {
        return dBRFinal;
    }

    public void setdBRFinal(String dBRFinal) {
        this.dBRFinal = dBRFinal;
    }
}
