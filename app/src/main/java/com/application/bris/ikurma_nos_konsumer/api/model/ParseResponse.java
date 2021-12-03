package com.application.bris.ikurma_nos_konsumer.api.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;

/**
 * Created by PID on 4/5/2019.
 */

public class ParseResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("statusMsg")
    private String message;
    @Nullable
    @SerializedName("data")
    private JsonObject data;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public JsonObject getData() {
        return data;
    }
}
