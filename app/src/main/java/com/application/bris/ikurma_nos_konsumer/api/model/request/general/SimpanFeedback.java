package com.application.bris.ikurma_nos_konsumer.api.model.request.general;

import com.google.gson.annotations.SerializedName;

public class SimpanFeedback {

    @SerializedName("NAMA")
    public String NAMA;
    @SerializedName("KANTOR")
    public String KANTOR;
    @SerializedName("JABATAN")
    public String JABATAN;
    @SerializedName("FID_UID")
    public Integer FID_UID;
    @SerializedName("CATATAN")
    public String CATATAN;

    public String getCATATAN() {
        return CATATAN;
    }

    public void setCATATAN(String CATATAN) {
        this.CATATAN = CATATAN;
    }

    public String getNAMA() {
        return NAMA;
    }

    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }

    public String getKANTOR() {
        return KANTOR;
    }

    public void setKANTOR(String KANTOR) {
        this.KANTOR = KANTOR;
    }

    public String getJABATAN() {
        return JABATAN;
    }

    public void setJABATAN(String JABATAN) {
        this.JABATAN = JABATAN;
    }

    public Integer getFID_UID() {
        return FID_UID;
    }

    public void setFID_UID(Integer FID_UID) {
        this.FID_UID = FID_UID;
    }
}
