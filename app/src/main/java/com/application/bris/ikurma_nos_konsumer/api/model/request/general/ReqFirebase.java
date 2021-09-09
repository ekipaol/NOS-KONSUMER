package com.application.bris.ikurma_nos_konsumer.api.model.request.general;

import com.google.gson.annotations.SerializedName;

public class ReqFirebase {
    @SerializedName("deviceID")
    private String deviceID;
    @SerializedName("firebaseMessagingID")
    private String firebaseMessagingID;
    @SerializedName("appID")
    private String appID;

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getFirebaseMessagingID() {
        return firebaseMessagingID;
    }

    public void setFirebaseMessagingID(String firebaseMessagingID) {
        this.firebaseMessagingID = firebaseMessagingID;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }
}
