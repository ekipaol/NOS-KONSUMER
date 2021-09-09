package com.application.bris.ikurma_nos_konsumer.api.model.request.agunan;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/5/2019.
 */

public class rejectAppraisal {
    @SerializedName("uid")
    private String uid;
    @SerializedName("idApl")
    private String idApl;
    @SerializedName("keterangan")
    private String keterangan;

    public rejectAppraisal(String uid, String idApl, String keterangan) {
        this.uid = uid;
        this.idApl = idApl;
        this.keterangan = keterangan;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setIdApl(String idApl) {
        this.idApl = idApl;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
