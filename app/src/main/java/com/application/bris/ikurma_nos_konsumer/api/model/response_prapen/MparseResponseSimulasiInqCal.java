package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class MparseResponseSimulasiInqCal {

    @SerializedName("Term")
    @Expose
    private Integer term;
    @SerializedName("PV")
    @Expose
    private BigDecimal pv;
    @SerializedName("PricingPrapen")
    @Expose
    private Double pricingPrapen;
    @SerializedName("PricingPensiun")
    @Expose
    private Double pricingPensiun;
    @SerializedName("PMTPrapen")
    @Expose
    private Double pMTPrapen;
    @SerializedName("PMTPensiun")
    @Expose
    private Double pMTPensiun;
    @SerializedName("PricingPrapenAfter")
    @Expose
    private Double pricingPrapenAfter;
    @SerializedName("PricingPensiunAfter")
    @Expose
    private Double pricingPensiunAfter;
    @SerializedName("BatasAtasAngsuranPrapen")
    @Expose
    private BigDecimal batasAtasAngsuranPrapen;

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getPv() {
        return pv;
    }

    public void setPv(BigDecimal pv) {
        this.pv = pv;
    }

    public Double getPricingPrapen() {
        return pricingPrapen;
    }

    public void setPricingPrapen(Double pricingPrapen) {
        this.pricingPrapen = pricingPrapen;
    }

    public Double getPricingPensiun() {
        return pricingPensiun;
    }

    public void setPricingPensiun(Double pricingPensiun) {
        this.pricingPensiun = pricingPensiun;
    }

    public Double getpMTPrapen() {
        return pMTPrapen;
    }

    public void setpMTPrapen(Double pMTPrapen) {
        this.pMTPrapen = pMTPrapen;
    }

    public Double getpMTPensiun() {
        return pMTPensiun;
    }

    public void setpMTPensiun(Double pMTPensiun) {
        this.pMTPensiun = pMTPensiun;
    }

    public Double getPricingPrapenAfter() {
        return pricingPrapenAfter;
    }

    public void setPricingPrapenAfter(Double pricingPrapenAfter) {
        this.pricingPrapenAfter = pricingPrapenAfter;
    }

    public Double getPricingPensiunAfter() {
        return pricingPensiunAfter;
    }

    public void setPricingPensiunAfter(Double pricingPensiunAfter) {
        this.pricingPensiunAfter = pricingPensiunAfter;
    }

    public BigDecimal getBatasAtasAngsuranPrapen() {
        return batasAtasAngsuranPrapen;
    }

    public void setBatasAtasAngsuranPrapen(BigDecimal batasAtasAngsuranPrapen) {
        this.batasAtasAngsuranPrapen = batasAtasAngsuranPrapen;
    }
}
