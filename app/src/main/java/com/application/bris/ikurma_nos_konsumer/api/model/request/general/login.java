package com.application.bris.ikurma_nos_konsumer.api.model.request.general;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 23/05/2019.
 */

public class login {
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("deviceID")
    private String deviceID;
    @SerializedName("appID")
    private String appID;

    public login(String username, String password, String deviceID, String appID) {
        this.username = username;
        this.password = password;
        this.deviceID = deviceID;
        this.appID = appID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }
}
