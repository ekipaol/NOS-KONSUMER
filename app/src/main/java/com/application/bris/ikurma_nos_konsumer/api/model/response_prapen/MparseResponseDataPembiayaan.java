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
    @SerializedName("MonthInstalmentPrapen")
    @Expose
    private BigDecimal MonthInstalmentPrapen;
    @SerializedName("MonthInstalmentPensiun")
    @Expose
    private BigDecimal MonthInstalmentPensiun;

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

    public BigDecimal getMonthInstalmentPrapen() {
        return MonthInstalmentPrapen;
    }

    public void setMonthInstalmentPrapen(BigDecimal monthInstalmentPrapen) {
        MonthInstalmentPrapen = monthInstalmentPrapen;
    }

    public BigDecimal getMonthInstalmentPensiun() {
        return MonthInstalmentPensiun;
    }

    public void setMonthInstalmentPensiun(BigDecimal monthInstalmentPensiun) {
        MonthInstalmentPensiun = monthInstalmentPensiun;
    }
}
