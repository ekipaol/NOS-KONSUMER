package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class MparseResponseDataPembiayaan {
    
    @SerializedName("TotalAttributions")
    @Expose
    private BigDecimal totalAttributions;
    @SerializedName("TotalCost")
    @Expose
    private BigDecimal totalCost;
    @SerializedName("MonthInstalment")
    @Expose
    private BigDecimal monthInstalment;
    @SerializedName("InsuranceCost")
    @Expose
    private BigDecimal InsuranceCost;
    @SerializedName("AdministrationsCost")
    @Expose
    private BigDecimal AdministrationsCost;

    public BigDecimal getTotalAttributions() {
        return totalAttributions;
    }

    public void setTotalAttributions(BigDecimal totalAttributions) {
        this.totalAttributions = totalAttributions;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getMonthInstalment() {
        return monthInstalment;
    }

    public void setMonthInstalment(BigDecimal monthInstalment) {
        this.monthInstalment = monthInstalment;
    }

    public BigDecimal getInsuranceCost() {
        return InsuranceCost;
    }

    public void setInsuranceCost(BigDecimal insuranceCost) {
        InsuranceCost = insuranceCost;
    }

    public BigDecimal getAdministrationsCost() {
        return AdministrationsCost;
    }

    public void setAdministrationsCost(BigDecimal administrationsCost) {
        AdministrationsCost = administrationsCost;
    }
}
