package com.application.bris.ikurma_nos_konsumer.api.model.request.flpp;

import com.google.gson.annotations.SerializedName;

public class ReqDataSikasep {
    @SerializedName("ktpDebitur")
    private String ktpDebitur;

    public String getKtpDebitur() {
        return ktpDebitur;
    }

    public void setKtpDebitur(String ktpDebitur) {
        this.ktpDebitur = ktpDebitur;
    }
}
