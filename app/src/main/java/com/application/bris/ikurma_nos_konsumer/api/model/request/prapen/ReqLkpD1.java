package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.SerializedName;

public class ReqLkpD1 {
    @SerializedName("Escrow")
    private String escrow;
    @SerializedName("JenisLKP")
    private String jenisLkp;
    @SerializedName("KodeCabang")
    private String kodeCabang;

    public String getEscrow() {
        return escrow;
    }

    public void setEscrow(String escrow) {
        this.escrow = escrow;
    }

    public String getJenisLkp() {
        return jenisLkp;
    }

    public void setJenisLkp(String jenisLkp) {
        this.jenisLkp = jenisLkp;
    }

    public String getKodeCabang() {
        return kodeCabang;
    }

    public void setKodeCabang(String kodeCabang) {
        this.kodeCabang = kodeCabang;
    }
}
