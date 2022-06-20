package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqListLngp {
    @SerializedName("InstansiId")
    @Expose
    private Long idInstansi;
    @SerializedName("Escrow")
    @Expose
    private String escrow;
    @SerializedName("CIF_Induk")
    @Expose
    private String cifInduk;

    public Long getIdInstansi() {
        return idInstansi;
    }

    public void setIdInstansi(Long idInstansi) {
        this.idInstansi = idInstansi;
    }

    public String getEscrow() {
        return escrow;
    }

    public void setEscrow(String escrow) {
        this.escrow = escrow;
    }

    public String getCifInduk() {
        return cifInduk;
    }

    public void setCifInduk(String cifInduk) {
        this.cifInduk = cifInduk;
    }
}
