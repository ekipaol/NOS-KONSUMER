package com.application.bris.ikurma_nos_konsumer.api.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/5/2019.
 */

public class ParseResponseDataInstansi {
    @SerializedName("status")
    private String status;
    @SerializedName("statusMsg")
    private String message;
    @SerializedName("data")
    private JsonObject data;
    @SerializedName("data_instansi")
    private JsonObject dataInstansi;

    @SerializedName("data_angsuran")
    private JsonObject dataAngsuran;
    @SerializedName("dataqanun")
    private String data_qanun;

    public String getData_qanun() {
        return data_qanun;
    }

    public void setData_qanun(String data_qanun) {
        this.data_qanun = data_qanun;
    }

    public JsonObject getDataInstansi() {
        return dataInstansi;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public JsonObject getData() {
        return data;
    }

    public JsonObject getDataAngsuran() {
        return dataAngsuran;
    }
}