package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MparseResponseJadwalAngsuran {

    @SerializedName("TotalInstalment")
    @Expose
    private BigDecimal totalInstalment;
    @SerializedName("InstalmentMargin")
    @Expose
    private BigDecimal instalmentMargin;
    @SerializedName("MainInstalment")
    @Expose
    private BigDecimal mainInstalment;
    @SerializedName("MainOutstandingRemnant")
    @Expose
    private BigDecimal mainOutstandingRemnant;
    @SerializedName("RemnantTerm")
    @Expose
    private BigInteger remnantTerm;
    @SerializedName("IsPrapen")
    @Expose
    private Boolean isPrapen;

    public BigDecimal getTotalInstalment() {
        return totalInstalment;
    }

    public void setTotalInstalment(BigDecimal totalInstalment) {
        this.totalInstalment = totalInstalment;
    }

    public BigDecimal getInstalmentMargin() {
        return instalmentMargin;
    }

    public void setInstalmentMargin(BigDecimal instalmentMargin) {
        this.instalmentMargin = instalmentMargin;
    }

    public BigDecimal getMainInstalment() {
        return mainInstalment;
    }

    public void setMainInstalment(BigDecimal mainInstalment) {
        this.mainInstalment = mainInstalment;
    }

    public BigDecimal getMainOutstandingRemnant() {
        return mainOutstandingRemnant;
    }

    public void setMainOutstandingRemnant(BigDecimal mainOutstandingRemnant) {
        this.mainOutstandingRemnant = mainOutstandingRemnant;
    }

    public BigInteger getRemnantTerm() {
        return remnantTerm;
    }

    public void setRemnantTerm(BigInteger remnantTerm) {
        this.remnantTerm = remnantTerm;
    }

    public Boolean getPrapen() {
        return isPrapen;
    }

    public void setPrapen(Boolean prapen) {
        isPrapen = prapen;
    }
}
