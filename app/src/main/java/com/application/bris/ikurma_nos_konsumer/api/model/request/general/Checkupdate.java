package com.application.bris.ikurma_nos_konsumer.api.model.request.general;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 23/05/2019.
 */

public class Checkupdate {
    @SerializedName("appID")
    private String appID;

    public Checkupdate(String appID) {
        this.appID = appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }
}
