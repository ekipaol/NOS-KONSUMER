package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateDataPendapatan {
    @SerializedName("ApplicationId")
    @Expose
    private int ApplicationId;

    @SerializedName("UID")
    @Expose
    private String UID;

    @SerializedName("data")
    @Expose
    private isiReqDokumenPendapatan data;


    public isiReqDokumenPendapatan getData() {
        return data;
    }

    public void setData(isiReqDokumenPendapatan data) {
        this.data = data;
    }

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

}
