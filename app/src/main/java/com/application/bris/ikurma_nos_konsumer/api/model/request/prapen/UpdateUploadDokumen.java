package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateUploadDokumen {
    @SerializedName("ApplicationId")
    @Expose
    private Long applicationId;
    @SerializedName("UID")
    @Expose
    private String uid;
    @SerializedName("data")
    @Expose
    private UploadDokumen UploadDokumen;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public UploadDokumen getUploadDokumen() {
        return UploadDokumen;
    }

    public void setUploadDokumen(UploadDokumen UploadDokumen) {
        this.UploadDokumen = UploadDokumen;
    }
}
