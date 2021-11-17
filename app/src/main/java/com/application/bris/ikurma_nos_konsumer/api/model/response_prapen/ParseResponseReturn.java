package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

public class ParseResponseReturn {
    @SerializedName("status")
    private String status;
    @SerializedName("statusMsg")
    private String message;
    @SerializedName("data")
    private JsonArray data;
    @SerializedName("Application_No")
    private String Application_No;


    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public JsonArray getData() {
        return data;
    }

    public String getApplication_No() {
        return Application_No;
    }


}
