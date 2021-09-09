package com.application.bris.ikurma_nos_konsumer.api.model.request;

import com.google.gson.annotations.SerializedName;

public class ReqKodeWilayah {


    @SerializedName("kodeWilayah")
    private String kodeWilayah;

    public String getKodeWilayah() {
        return kodeWilayah;
    }

    public void setKodeWilayah(String kodeWilayah) {
        this.kodeWilayah = kodeWilayah;
    }
}
