package com.application.bris.ikurma_nos_konsumer.api.model.request.flpp;

import com.google.gson.annotations.SerializedName;

public class ReqFotoFlpp {

    @SerializedName("kode")
    private String kodeBank;

    @SerializedName("batchId")
    private String batchId;

    @SerializedName("ktp")
    private String ktpDebitur;

    public String getKodeBank() {
        return kodeBank;
    }

    public void setKodeBank(String kodeBank) {
        this.kodeBank = kodeBank;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getKtpDebitur() {
        return ktpDebitur;
    }

    public void setKtpDebitur(String ktpDebitur) {
        this.ktpDebitur = ktpDebitur;
    }
}
