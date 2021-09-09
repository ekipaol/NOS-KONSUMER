package com.application.bris.ikurma_nos_konsumer.page_aom.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/26/2019.
 */

public class MListJenisKPR {

    @SerializedName("key")
    private String key;
    @SerializedName("value")
    private String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
