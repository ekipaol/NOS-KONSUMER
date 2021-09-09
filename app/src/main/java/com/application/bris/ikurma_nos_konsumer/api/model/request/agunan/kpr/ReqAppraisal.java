package com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.kpr;

import com.google.gson.annotations.SerializedName;

public class ReqAppraisal {
    @SerializedName("idApl")
    private int idApl;

    @SerializedName("uid")
    private int uid;

    public int getIdApl() {
        return idApl;
    }

    public void setIdApl(int idApl) {
        this.idApl = idApl;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
