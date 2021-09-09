package com.application.bris.ikurma_nos_konsumer.api.model.request;

import com.google.gson.annotations.SerializedName;

public class ReqFollowUpFlpp {

    @SerializedName("kodeKanwil")
    private String kodeKanwil;

    @SerializedName("kodeCabang")
    private String kodeCabang;

    @SerializedName("uid")
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKodeKanwil() {
        return kodeKanwil;
    }

    public void setKodeKanwil(String kodeKanwil) {
        this.kodeKanwil = kodeKanwil;
    }

    public String getKodeCabang() {
        return kodeCabang;
    }

    public void setKodeCabang(String kodeCabang) {
        this.kodeCabang = kodeCabang;
    }
}
