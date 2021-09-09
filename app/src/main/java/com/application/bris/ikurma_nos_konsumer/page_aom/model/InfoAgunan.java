package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class InfoAgunan {

    @SerializedName("idAgunan")
    private String idAgunan;
    @SerializedName("fidjenisAgunan")
    private String fidjenisAgunan;
    @SerializedName("pengikatanEksisting")
    private String pengikatanEksisting;
    @SerializedName("idCif")
    private String idCif;
    @SerializedName("persenFTV")
    private String persenFTV;
    @SerializedName("jenisPengikatan")
    private String jenisPengikatan;
    @SerializedName("coverPlafond")
    private String coverPlafond;
    @SerializedName("nilaiPengikatan")
    private String nilaiPengikatan;
    @SerializedName("statusApraisal")
    private String statusApraisal;

    public String getStatusApraisal() {
        return statusApraisal;
    }

    public void setStatusApraisal(String statusApraisal) {
        this.statusApraisal = statusApraisal;
    }

    public String getIdAgunan() {
        return idAgunan;
    }

    public void setIdAgunan(String idAgunan) {
        this.idAgunan = idAgunan;
    }

    public String getFidjenisAgunan() {
        return fidjenisAgunan;
    }

    public void setFidjenisAgunan(String fidjenisAgunan) {
        this.fidjenisAgunan = fidjenisAgunan;
    }

    public String getPengikatanEksisting() {
        return pengikatanEksisting;
    }

    public void setPengikatanEksisting(String pengikatanEksisting) {
        this.pengikatanEksisting = pengikatanEksisting;
    }

    public String getIdCif() {
        return idCif;
    }

    public void setIdCif(String idCif) {
        this.idCif = idCif;
    }

    public String getPersenFTV() {
        return persenFTV;
    }

    public void setPersenFTV(String persenFTV) {
        this.persenFTV = persenFTV;
    }

    public String getJenisPengikatan() {
        return jenisPengikatan;
    }

    public void setJenisPengikatan(String jenisPengikatan) {
        this.jenisPengikatan = jenisPengikatan;
    }

    public String getCoverPlafond() {
        return coverPlafond;
    }

    public void setCoverPlafond(String coverPlafond) {
        this.coverPlafond = coverPlafond;
    }

    public String getNilaiPengikatan() {
        return nilaiPengikatan;
    }

    public void setNilaiPengikatan(String nilaiPengikatan) {
        this.nilaiPengikatan = nilaiPengikatan;
    }
}
