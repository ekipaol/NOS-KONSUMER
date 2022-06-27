package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReqHitungAkseptasiPendapatan {
    @SerializedName("ApplicationId")
    private int ApplicationId;
    @SerializedName("UID")
    private String UID;
    @SerializedName("data")
    private List<SubAkseptasiPendapatan> data;

    public int getApplicationId() {
        return ApplicationId;
    }

    public void setApplicationId(int applicationId) {
        ApplicationId = applicationId;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public List<SubAkseptasiPendapatan> getData() {
        return data;
    }

    public void setData(List<SubAkseptasiPendapatan> data) {
        this.data = data;
    }
}
