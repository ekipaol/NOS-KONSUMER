package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;


import com.google.gson.annotations.SerializedName;

public class inquiryNikPasangan {
    @SerializedName("noKtpPasangan")
    private String noKtpPasangan;

    public inquiryNikPasangan(String noKtpPasangan) {
        this.noKtpPasangan = noKtpPasangan;
    }
}

