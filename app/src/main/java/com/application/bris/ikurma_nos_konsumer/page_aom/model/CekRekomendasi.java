package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 24/06/2019.
 */

public class CekRekomendasi {
    @SerializedName("disposableIncome")
    private Long disposableIncome;
    @SerializedName("marginProduktif1")
    private Float marginProduktif1;
    @SerializedName("marginKonsumtif")
    private Float marginKonsumtif;
    @SerializedName("IDIR")
    private Float iDIR;
    @SerializedName("validasiLKN")
    private String validasiLKN;
    @SerializedName("marginProduktif2")
    private Float marginProduktif2;
    @SerializedName("marginTO")
    private Float marginTO;
    @SerializedName("rekomendasiAngsuran")
    private Long rekomendasiAngsuran;

    public CekRekomendasi(Long disposableIncome, Float marginProduktif1, Float marginKonsumtif, Float iDIR, String validasiLKN, Float marginProduktif2, Float marginTO, Long rekomendasiAngsuran) {
        this.disposableIncome = disposableIncome;
        this.marginProduktif1 = marginProduktif1;
        this.marginKonsumtif = marginKonsumtif;
        this.iDIR = iDIR;
        this.validasiLKN = validasiLKN;
        this.marginProduktif2 = marginProduktif2;
        this.marginTO = marginTO;
        this.rekomendasiAngsuran = rekomendasiAngsuran;
    }

    public Long getDisposableIncome() {
        return disposableIncome;
    }

    public Float getMarginProduktif1() {
        return marginProduktif1;
    }

    public Float getMarginKonsumtif() {
        return marginKonsumtif;
    }

    public Float getiDIR() {
        return iDIR;
    }

    public String getValidasiLKN() {
        return validasiLKN;
    }

    public Float getMarginProduktif2() {
        return marginProduktif2;
    }

    public Float getMarginTO() {
        return marginTO;
    }

    public Long getRekomendasiAngsuran() {
        return rekomendasiAngsuran;
    }
}
