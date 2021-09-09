package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 24/06/2019.
 */

public class rejectHotprospek {
    @SerializedName("idAplikasi")
    private int idAplikasi;
    @SerializedName("uid")
    private String uid;
    @SerializedName("keterangan")
    private String keterangan;

    public rejectHotprospek(int idAplikasi, String uid, String keterangan) {
        this.idAplikasi = idAplikasi;
        this.uid = uid;
        this.keterangan = keterangan;
    }

    public int getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
