package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.application.bris.ikurma_nos_konsumer.model.prapen.DataPejabat;
import com.google.gson.annotations.SerializedName;

public class ReqDataPejabat {
    @SerializedName("ApplicationId")
    private long ApplicationId;
    @SerializedName("UID")
    private String UID;
    @SerializedName("data")
    private DataPejabat data;

    public long getApplicationId() {
        return ApplicationId;
    }

    public void setApplicationId(long applicationId) {
        ApplicationId = applicationId;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public DataPejabat getData() {
        return data;
    }

    public void setData(DataPejabat data) {
        this.data = data;
    }
}
