package com.application.bris.ikurma_nos_konsumer.page_aom.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/26/2019.
 */

public class MListBidangPekerjaan {

    @SerializedName("DESC1")
    private String DESC1;
    @SerializedName("DESC2")
    private String DESC2;

    public String getDESC1() {
        return DESC1;
    }

    public String getDESC2() {
        return DESC2;
    }
}
