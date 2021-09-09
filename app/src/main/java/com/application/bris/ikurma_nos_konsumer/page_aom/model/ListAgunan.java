package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ListAgunan implements Serializable {

    @SerializedName("FID_CIF_LAS")
    private String FID_CIF_LAS;
    @SerializedName("NILAI_PENGIKATAN")
    private long NILAI_PENGIKATAN;
    @SerializedName("PLAFOND_INDUK")
    private long PLAFOND_INDUK;
    @SerializedName("FID_JENIS_AGUNAN")
    private String FID_JENIS_AGUNAN;
    @SerializedName("FID_AGUNAN")
    private String FID_AGUNAN;
    @SerializedName("ID_APLIKASI")
    private String ID_APLIKASI;
    @SerializedName("NILAI_COVER_PLAFOND")
    private long NILAI_COVER_PLAFOND;
    @SerializedName("DESC_AGUNAN")
    private String DESC_AGUNAN;
    @SerializedName("NAMA_DEBITUR_1")
    private String NAMA_DEBITUR_1;
    @SerializedName("ID")
    private String ID;

    public String getFID_CIF_LAS() {
        return FID_CIF_LAS;
    }

    public void setFID_CIF_LAS(String FID_CIF_LAS) {
        this.FID_CIF_LAS = FID_CIF_LAS;
    }

    public long getNILAI_PENGIKATAN() {
        return NILAI_PENGIKATAN;
    }

    public void setNILAI_PENGIKATAN(long NILAI_PENGIKATAN) {
        this.NILAI_PENGIKATAN = NILAI_PENGIKATAN;
    }

    public long getPLAFOND_INDUK() {
        return PLAFOND_INDUK;
    }

    public void setPLAFOND_INDUK(long PLAFOND_INDUK) {
        this.PLAFOND_INDUK = PLAFOND_INDUK;
    }

    public String getFID_JENIS_AGUNAN() {
        return FID_JENIS_AGUNAN;
    }

    public void setFID_JENIS_AGUNAN(String FID_JENIS_AGUNAN) {
        this.FID_JENIS_AGUNAN = FID_JENIS_AGUNAN;
    }

    public String getFID_AGUNAN() {
        return FID_AGUNAN;
    }

    public void setFID_AGUNAN(String FID_AGUNAN) {
        this.FID_AGUNAN = FID_AGUNAN;
    }

    public String getID_APLIKASI() {
        return ID_APLIKASI;
    }

    public void setID_APLIKASI(String ID_APLIKASI) {
        this.ID_APLIKASI = ID_APLIKASI;
    }

    public long getNILAI_COVER_PLAFOND() {
        return NILAI_COVER_PLAFOND;
    }

    public void setNILAI_COVER_PLAFOND(long NILAI_COVER_PLAFOND) {
        this.NILAI_COVER_PLAFOND = NILAI_COVER_PLAFOND;
    }

    public String getDESC_AGUNAN() {
        return DESC_AGUNAN;
    }

    public void setDESC_AGUNAN(String DESC_AGUNAN) {
        this.DESC_AGUNAN = DESC_AGUNAN;
    }

    public String getNAMA_DEBITUR_1() {
        return NAMA_DEBITUR_1;
    }

    public void setNAMA_DEBITUR_1(String NAMA_DEBITUR_1) {
        this.NAMA_DEBITUR_1 = NAMA_DEBITUR_1;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

}
