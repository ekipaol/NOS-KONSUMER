package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class MparseResponseSimulasiInqCal {

    @SerializedName("Term")
    @Expose
    private Double term;
    @SerializedName("PV")
    @Expose
    private BigDecimal pv;
    @SerializedName("Rate")
    @Expose
    private Double rate;
    @SerializedName("PMT")
    @Expose
    private Double pmt;

    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public BigDecimal getPv() {
        return pv;
    }

    public void setPv(BigDecimal pv) {
        this.pv = pv;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getPmt() {
        return pmt;
    }

    public void setPmt(Double pmt) {
        this.pmt = pmt;
    }
}
