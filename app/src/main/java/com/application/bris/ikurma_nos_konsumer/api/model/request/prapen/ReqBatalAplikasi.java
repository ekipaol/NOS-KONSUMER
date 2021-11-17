package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqBatalAplikasi {
    @SerializedName("UID")
    @Expose
    private String UID;
    @SerializedName("ApplicationId")
    @Expose
    private long ApplicationId;
    @SerializedName("ReasonCode")
    @Expose
    private String ReasonCode;
    @SerializedName("ReasonDescription")
    @Expose
    private String ReasonDescription;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public long getApplicationId() {
        return ApplicationId;
    }

    public void setApplicationId(long applicationId) {
        ApplicationId = applicationId;
    }

    public String getReasonCode() {
        return ReasonCode;
    }

    public void setReasonCode(String reasonCode) {
        ReasonCode = reasonCode;
    }

    public String getReasonDescription() {
        return ReasonDescription;
    }

    public void setReasonDescription(String reasonDescription) {
        ReasonDescription = reasonDescription;
    }
}
