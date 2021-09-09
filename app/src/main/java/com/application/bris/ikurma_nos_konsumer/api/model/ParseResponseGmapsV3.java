package com.application.bris.ikurma_nos_konsumer.api.model;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/5/2019.
 */

public class ParseResponseGmapsV3 {
    @SerializedName("status")
    private String status;
    @SerializedName("results")
    private JsonArray results;

    public String getStatus() {
        return status;
    }

    public JsonArray getResults() {
        return results;
    }
}
