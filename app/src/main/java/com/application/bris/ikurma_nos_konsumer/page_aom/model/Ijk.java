package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class Ijk {

    @SerializedName("rate1")
    private String rate1;

    @SerializedName("rate2")
    private String rate2;

    @SerializedName("nilaiPlafond")
    private String nilaiPlafond;

    @SerializedName("IJK")
    private String IJK;

    @SerializedName("besaranFee")
    private String besaranFee;

    @SerializedName("noticeJamsyar")
    private String noticeJamsyar;

    public String getNoticeJamsyar() {
        return noticeJamsyar;
    }

    public void setNoticeJamsyar(String noticeJamsyar) {
        this.noticeJamsyar = noticeJamsyar;
    }

    public String getRate1() {
        return rate1;
    }

    public void setRate1(String rate1) {
        this.rate1 = rate1;
    }

    public String getRate2() {
        return rate2;
    }

    public void setRate2(String rate2) {
        this.rate2 = rate2;
    }

    public String getNilaiPlafond() {
        return nilaiPlafond;
    }

    public void setNilaiPlafond(String nilaiPlafond) {
        this.nilaiPlafond = nilaiPlafond;
    }

    public String getIJK() {
        return IJK;
    }

    public void setIJK(String IJK) {
        this.IJK = IJK;
    }

    public String getBesaranFee() {
        return besaranFee;
    }

    public void setBesaranFee(String besaranFee) {
        this.besaranFee = besaranFee;
    }
}
