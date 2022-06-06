package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPribadiLainya {
    @SerializedName("ApplicationId")
    @Expose
    private Long applicationId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statusMsg")
    @Expose
    private String statusMsg;
    @SerializedName("UID")
    @Expose
    private String uid;
    @SerializedName("data")
    @Expose
    private ReqDataPribadiLainya ReqDataPribadiLainya;

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

    public ReqDataPribadiLainya getUploadDokumen() {
        return ReqDataPribadiLainya;
    }

    public void setUploadDokumen(ReqDataPribadiLainya ReqDataPribadiLainya) {
        this.ReqDataPribadiLainya = ReqDataPribadiLainya;
    }

    public com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDataPribadiLainya getReqDataPribadiLainya() {
        return ReqDataPribadiLainya;
    }

    public void setReqDataPribadiLainya(com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDataPribadiLainya reqDataPribadiLainya) {
        ReqDataPribadiLainya = reqDataPribadiLainya;
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
}
