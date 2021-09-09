package com.application.bris.ikurma_nos_konsumer.api.model;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/5/2019.
 */

public class ParseResponseArr {
    @SerializedName("status")
    private String status;
    @SerializedName("statusMsg")
    private String message;
    @SerializedName("data")
    private JsonArray data;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public JsonArray getData() {
        return data;
    }
}
