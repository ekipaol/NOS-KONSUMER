package com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/5/2019.
 */

public class listPipeline {
    @SerializedName("uid")
    private String uid;
    @SerializedName("kode_skk")
    private String kodeSkk;
    @SerializedName("namaNasabah")
    private String namaNasabah;

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setKodeSkk(String kodeSkk) {
        this.kodeSkk = kodeSkk;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }
}
