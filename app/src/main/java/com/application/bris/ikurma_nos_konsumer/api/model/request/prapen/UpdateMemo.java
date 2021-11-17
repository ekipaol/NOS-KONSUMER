package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateMemo {
    @SerializedName("ApplicationId")
    @Expose
    private Long ApplicationId;
    @SerializedName("UID")
    @Expose
    private String UID;
    @SerializedName("Memo")
    @Expose
    private String Memo;

    public Long getApplicationId() {
        return ApplicationId;
    }

    public void setApplicationId(Long applicationId) {
        ApplicationId = applicationId;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String memo) {
        Memo = memo;
    }
}
