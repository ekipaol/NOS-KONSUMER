package com.application.bris.ikurma_nos_konsumer.api.model.request.general;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 23/05/2019.
 */

public class home {
    @SerializedName("uid")
    private int uid;

    @SerializedName("kode_skk")
    private String kodeSkk;

    public home(int uid, String kodeSkk) {
        this.uid = uid;
        this.kodeSkk = kodeSkk;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setKodeSkk(String kodeSkk) {
        this.kodeSkk = kodeSkk;
    }
}
