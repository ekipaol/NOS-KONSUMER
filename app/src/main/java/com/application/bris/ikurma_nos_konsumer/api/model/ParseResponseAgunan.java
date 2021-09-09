package com.application.bris.ikurma_nos_konsumer.api.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/5/2019.
 */

public class ParseResponseAgunan {
    @SerializedName("status")
    private String status;
    @SerializedName("statusMsg")
    private String message;
    @SerializedName("idAgunan")
    private String idAgunan;
    @SerializedName("data")
    private JsonObject data;
    @SerializedName("listPoto")
    private JsonArray listPoto;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getIdAgunan() {
        return idAgunan;
    }

    public JsonObject getData() {
        return data;
    }

    public JsonArray getListPoto() {
        return listPoto;
    }
}
