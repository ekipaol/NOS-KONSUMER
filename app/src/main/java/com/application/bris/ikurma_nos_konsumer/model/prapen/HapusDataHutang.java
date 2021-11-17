package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HapusDataHutang {
    @SerializedName("ApplicationId")
    @Expose
    private Long applicationId;
    @SerializedName("Utang_Id")
    @Expose
    private Long Utang_Id;
    @SerializedName("UID")
    @Expose
    private String UID;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getUtang_Id() {
        return Utang_Id;
    }

    public void setUtang_Id(Long utang_Id) {
        Utang_Id = utang_Id;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
