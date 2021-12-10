package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MParseArray {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statusMsg")
    @Expose
    private String statusMsg;
    @SerializedName("data")
    @Expose
    private JsonArray data;

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

    public JsonArray getData() {
        return data;
    }

    public void setData(JsonArray data) {
        this.data = data;
    }
}
