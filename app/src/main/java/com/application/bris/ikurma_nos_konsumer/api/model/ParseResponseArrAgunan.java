package com.application.bris.ikurma_nos_konsumer.api.model;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

public class ParseResponseArrAgunan {
    @SerializedName("status")
    private String status;
    @SerializedName("statusMsg")
    private String message;
    @SerializedName("data")
    private JsonArray data;
    @SerializedName("info")
    private JsonArray info;
    @SerializedName("dtUserApraisal")
    private JsonArray dtUserApraisal;

    @SerializedName("dtRekomApraisal")
    private JsonArray dtRekomApraisal;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public JsonArray getData() {
        return data;
    }

    public JsonArray getInfo() {
        return info;
    }

    public JsonArray getDtUserApraisal() {
        return dtUserApraisal;
    }


    public JsonArray getDtRekomApraisal() {
        return dtRekomApraisal;
    }
}
