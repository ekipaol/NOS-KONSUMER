package com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.kpr;


import com.google.gson.annotations.SerializedName;

public class ReqKirimAppraisal {
    @SerializedName("idApl")
    private int idApl;
    @SerializedName("uidApraisal")
    private int uidApraisal;

    public ReqKirimAppraisal() {
    }

    public int getIdApl() {
        return idApl;
    }

    public void setIdApl(int idApl) {
        this.idApl = idApl;
    }

    public int getUidApraisal() {
        return uidApraisal;
    }

    public void setUidApraisal(int uidApraisal) {
        this.uidApraisal = uidApraisal;
    }
}
