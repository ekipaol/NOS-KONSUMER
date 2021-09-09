package com.application.bris.ikurma_nos_konsumer.api.model.request.monitoring;

import com.google.gson.annotations.SerializedName;

public class ReqMonitoringNasabah {
    @SerializedName("uid")
    private int uid;
    @SerializedName("noPegawai")
    private long noPegawai;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getNoPegawai() {
        return noPegawai;
    }

    public void setNoPegawai(long noPegawai) {
        this.noPegawai = noPegawai;
    }
}
