package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataListLngpByEscrow {
    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("DESCRIPTION")
    @Expose
    private String description;
    @SerializedName("CURRENCY")
    @Expose
    private String currency;
    @SerializedName("MAXIMUMAMOUNT")
    @Expose
    private String maximumamount;
    @SerializedName("LIMITLINES")
    @Expose
    private String limitlines;
    @SerializedName("UTILISEDAMOUNT")
    @Expose
    private String utilisedamount;
    @SerializedName("EXPDATELIM")
    @Expose
    private String expdatelim;
    @SerializedName("TENOR")
    @Expose
    private String tenor;
    @SerializedName("FEEAGENT")
    @Expose
    private String feeagent;
    @SerializedName("m")
    @Expose
    private String m;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMaximumamount() {
        return maximumamount;
    }

    public void setMaximumamount(String maximumamount) {
        this.maximumamount = maximumamount;
    }

    public String getLimitlines() {
        return limitlines;
    }

    public void setLimitlines(String limitlines) {
        this.limitlines = limitlines;
    }

    public String getUtilisedamount() {
        return utilisedamount;
    }

    public void setUtilisedamount(String utilisedamount) {
        this.utilisedamount = utilisedamount;
    }

    public String getExpdatelim() {
        return expdatelim;
    }

    public void setExpdatelim(String expdatelim) {
        this.expdatelim = expdatelim;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public String getFeeagent() {
        return feeagent;
    }

    public void setFeeagent(String feeagent) {
        this.feeagent = feeagent;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }
}
