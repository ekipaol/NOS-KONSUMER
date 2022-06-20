package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqUpdateLngp {
    @SerializedName("InstansiId")
    @Expose
    private Long idInstansi;
    @SerializedName("UID")
    @Expose
    private String uid;
    @SerializedName("data")
    @Expose
    private DataListLngp data;

    public Long getIdInstansi() {
        return idInstansi;
    }

    public void setIdInstansi(Long idInstansi) {
        this.idInstansi = idInstansi;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public DataListLngp getData() {
        return data;
    }

    public void setData(DataListLngp data) {
        this.data = data;
    }
}
