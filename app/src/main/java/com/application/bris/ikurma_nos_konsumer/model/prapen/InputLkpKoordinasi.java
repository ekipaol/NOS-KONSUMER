package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InputLkpKoordinasi {
    @SerializedName("InstansiId")
    @Expose
    private Long instansiId;
    @SerializedName("UID")
    @Expose
    private String uid;
    @SerializedName("Data")
    @Expose
    private DataLkpKoordinasi dataLkp;

    public Long getInstansiId() {
        return instansiId;
    }

    public void setInstansiId(Long instansiId) {
        this.instansiId = instansiId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public DataLkpKoordinasi getDataLkp() {
        return dataLkp;
    }

    public void setDataLkp(DataLkpKoordinasi dataLkp) {
        this.dataLkp = dataLkp;
    }
}
