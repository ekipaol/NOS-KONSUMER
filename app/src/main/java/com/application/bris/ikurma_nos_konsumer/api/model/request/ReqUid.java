package com.application.bris.ikurma_nos_konsumer.api.model.request;

import com.google.gson.annotations.SerializedName;

public class ReqUid {

    @SerializedName("uid")
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
