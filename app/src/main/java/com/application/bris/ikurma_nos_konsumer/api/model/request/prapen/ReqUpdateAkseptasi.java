package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqUpdateAkseptasi {
    @SerializedName("ApplicationId")
    private Long applicationId;
    @SerializedName("status")
    private String status;
    @SerializedName("statusMsg")
    private String statusMsg;
    @SerializedName("UID")
    private String uid;
    @SerializedName("data")
    private UpdateAkseptasiPendapatan UpdateAkseptasiPendapatan;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateAkseptasiPendapatan getUpdateAkseptasiPendapatan() {
        return UpdateAkseptasiPendapatan;
    }

    public void setUpdateAkseptasiPendapatan(com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateAkseptasiPendapatan updateAkseptasiPendapatan) {
        UpdateAkseptasiPendapatan = updateAkseptasiPendapatan;
    }
}
