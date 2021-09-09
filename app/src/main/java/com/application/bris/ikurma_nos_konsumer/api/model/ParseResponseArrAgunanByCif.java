package com.application.bris.ikurma_nos_konsumer.api.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class ParseResponseArrAgunanByCif {
    @SerializedName("status")
    private String status;
    @SerializedName("statusMsg")
    private String message;
    @SerializedName("data")
    private JsonObject data;
    @SerializedName("info")
    private JsonArray info;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public JsonObject getData() {
        return data;
    }

    public JsonArray getInfo() {
        return info;
    }
}
