package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.SerializedName;

public class ReqKodeBookingCabang {

    @SerializedName("KodeCabang")
    private String kodeBookingCabang;

    public String getKodeBookingCabang() {
        return kodeBookingCabang;
    }

    public void setKodeBookingCabang(String kodeBookingCabang) {
        this.kodeBookingCabang = kodeBookingCabang;
    }
}
