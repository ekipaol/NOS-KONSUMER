package com.application.bris.ikurma_nos_konsumer.api.model.request.general;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 23/05/2019.
 */

public class Activation {
    @SerializedName("data")
    private String data;
    @SerializedName("deviceID")
    private String deviceID;
    @SerializedName("deviceName")
    private String deviceName;
    @SerializedName("appID")
    private String appID;

    public Activation(String data, String deviceID, String deviceName, String appID) {
        this.data = data;
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.appID = appID;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }
}
