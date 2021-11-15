package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.SerializedName;

public class ReqKodeAo {
    @SerializedName("KodeAO")
    private String KodeAO;

    public String getKodeAO() {
        return KodeAO;
    }

    public void setKodeAO(String kodeAO) {
        KodeAO = kodeAO;
    }
}
