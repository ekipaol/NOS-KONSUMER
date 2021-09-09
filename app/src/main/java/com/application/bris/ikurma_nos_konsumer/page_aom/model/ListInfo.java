package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ListInfo implements Serializable {

    @SerializedName("LOAN_TYPE")
    private String LOAN_TYPE;
    @SerializedName("FID_TP_PRODUK")
    private int FID_TP_PRODUK;

    public String getLOAN_TYPE() {
        return LOAN_TYPE;
    }

    public void setLOAN_TYPE(String LOAN_TYPE) {
        this.LOAN_TYPE = LOAN_TYPE;
    }

    public int getFID_TP_PRODUK() {
        return FID_TP_PRODUK;
    }

    public void setFID_TP_PRODUK(int FID_TP_PRODUK) {
        this.FID_TP_PRODUK = FID_TP_PRODUK;
    }

}
