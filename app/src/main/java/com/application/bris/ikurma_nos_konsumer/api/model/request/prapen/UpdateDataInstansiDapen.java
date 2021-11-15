package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.application.bris.ikurma_nos_konsumer.model.prapen.DataInstansiDapen;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateDataInstansiDapen {
    @SerializedName("ApplicationId")
    @Expose
    private Long ApplicationId;
    @SerializedName("UID")
    @Expose
    private String UID;
    @SerializedName("DataInstansi")
    @Expose
    private DataInstansiDapen DataInstansi;

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

    public DataInstansiDapen getDataInstansi() {
        return DataInstansi;
    }

    public void setDataInstansi(DataInstansiDapen dataInstansi) {
        DataInstansi = dataInstansi;
    }
}
