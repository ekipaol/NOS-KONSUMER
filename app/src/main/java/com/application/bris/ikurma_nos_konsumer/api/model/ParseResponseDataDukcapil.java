package com.application.bris.ikurma_nos_konsumer.api.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class ParseResponseDataDukcapil {
    @SerializedName("status")
    private String status;
    @SerializedName("statusMsg")
    private String message;

    @SerializedName("dukcapil")
    private JsonObject dukcapil;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public JsonObject getDukcapil() {
        return dukcapil;
    }
}
