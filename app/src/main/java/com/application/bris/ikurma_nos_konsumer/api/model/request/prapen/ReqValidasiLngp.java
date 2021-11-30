package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.SerializedName;

public class ReqValidasiLngp {
    @SerializedName("ApplicationId")
    private Long applicationid;
    @SerializedName("NO_LNGP")
    private String noLngp ;
    @SerializedName("UID")
    private String uid;

    public Long getApplicationid() {
        return applicationid;
    }

    public void setApplicationid(Long applicationid) {
        this.applicationid = applicationid;
    }

    public String getNoLngp() {
        return noLngp;
    }

    public void setNoLngp(String noLngp) {
        this.noLngp = noLngp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
