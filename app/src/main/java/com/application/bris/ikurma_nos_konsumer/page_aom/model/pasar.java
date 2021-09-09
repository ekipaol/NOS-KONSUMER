package com.application.bris.ikurma_nos_konsumer.page_aom.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 07/05/2019.
 */

public class pasar {
    @SerializedName("NKR")
    private String NKR;
    @SerializedName("KOTA_KAB")
    private String KOTA_KAB;
    @SerializedName("RATE")
    private String RATE;
    @SerializedName("NAMA_PASAR")
    private String NAMA_PASAR;
    @SerializedName("ALAMAT_PASAR")
    private String ALAMAT_PASAR;
    @SerializedName("PROVINSI")
    private String PROVINSI;

    public pasar(String NKR, String KOTA_KAB, String RATE, String NAMA_PASAR, String ALAMAT_PASAR, String PROVINSI) {
        this.NKR = NKR;
        this.KOTA_KAB = KOTA_KAB;
        this.RATE = RATE;
        this.NAMA_PASAR = NAMA_PASAR;
        this.ALAMAT_PASAR = ALAMAT_PASAR;
        this.PROVINSI = PROVINSI;
    }

    public String getNKR() {
        return NKR;
    }

    public String getKOTA_KAB() {
        return KOTA_KAB;
    }

    public String getRATE() {
        return RATE;
    }

    public String getNAMA_PASAR() {
        return NAMA_PASAR;
    }

    public String getALAMAT_PASAR() {
        return ALAMAT_PASAR;
    }

    public String getPROVINSI() {
        return PROVINSI;
    }
}