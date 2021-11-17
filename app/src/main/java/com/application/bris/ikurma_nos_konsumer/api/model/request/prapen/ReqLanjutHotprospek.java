package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.SerializedName;

public class ReqLanjutHotprospek
{
    @SerializedName("ApplicationId")
    private Long ApplicationId;

    @SerializedName("UID")
    private String UID;

    @SerializedName("IsMitraFronting")
    private boolean IsMitraFronting;

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

    public boolean isMitraFronting() {
        return IsMitraFronting;
    }

    public void setMitraFronting(boolean mitraFronting) {
        IsMitraFronting = mitraFronting;
    }
}
