package com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.kpr;

import com.google.gson.annotations.SerializedName;

public class SelesaiAppraisal {
    @SerializedName("uid")
    private String uid;
    @SerializedName("idApl")
    private String idApl;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIdApl() {
        return idApl;
    }

    public void setIdApl(String idApl) {
        this.idApl = idApl;
    }
}
