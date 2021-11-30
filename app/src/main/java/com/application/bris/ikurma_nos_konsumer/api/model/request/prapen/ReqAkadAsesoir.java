package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.SerializedName;

public class ReqAkadAsesoir {
    @SerializedName("ApplicationId")
    private long ApplicationId;
    @SerializedName("UID")
    private String UID;
    @SerializedName("data")
    private ReqDataAkadAsesoir data;

    public long getApplicationId() {
        return ApplicationId;
    }

    public void setApplicationId(long applicationId) {
        ApplicationId = applicationId;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public ReqDataAkadAsesoir getData() {
        return data;
    }

    public void setData(ReqDataAkadAsesoir data) {
        this.data = data;
    }
}
