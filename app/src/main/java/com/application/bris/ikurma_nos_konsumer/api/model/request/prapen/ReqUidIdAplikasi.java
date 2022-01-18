package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqUidIdAplikasi {
    @SerializedName("UID")
    @Expose
    private String UID;
    @SerializedName("ApplicationId")
    @Expose
    private long ApplicationId;
    @SerializedName("Role")
    @Expose
    private long Role;

    public long getRole() {
        return Role;
    }

    public void setRole(long role) {
        Role = role;
    }

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
}
