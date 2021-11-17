package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.SerializedName;

public class ReqInquery {

    @SerializedName("ApplicationId")
    private int ApplicationId;
    @SerializedName("UID")
    private String UID;

    public void setApplicationId(int ApplicationId) {
        this.ApplicationId = ApplicationId;
    }

    public int getApplicationId() {
        return ApplicationId;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUID(String UID) {
        return UID;
    }
}
