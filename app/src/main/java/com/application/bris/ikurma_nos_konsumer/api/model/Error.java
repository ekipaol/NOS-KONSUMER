package com.application.bris.ikurma_nos_konsumer.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/6/2019.
 */

public class Error {
    @SerializedName("status")
    private String status;
    @SerializedName("statusMsg")
    private String message;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
