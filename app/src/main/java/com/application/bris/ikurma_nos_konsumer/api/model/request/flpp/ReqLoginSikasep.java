package com.application.bris.ikurma_nos_konsumer.api.model.request.flpp;

import com.google.gson.annotations.SerializedName;

public class ReqLoginSikasep {
    @SerializedName("kode")
    private String kodeBank;

    @SerializedName("password")
    private String password;

    public String getKodeBank() {
        return kodeBank;
    }

    public void setKodeBank(String kodeBank) {
        this.kodeBank = kodeBank;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
