package com.application.bris.ikurma_nos_konsumer.api.model.request.agunan;

import com.google.gson.annotations.SerializedName;

public class ReqInfoAgunan {
    @SerializedName("idApl")
    private int idApl;
    @SerializedName("idAgunan")
    private int idAgunan;
    @SerializedName("idCif")
    private int idCif;

    public void setIdApl(int idApl) {
        this.idApl = idApl;
    }

    public void setIdAgunan(int idAgunan) {
        this.idAgunan = idAgunan;
    }

    public void setIdCif(int idCif) {
        this.idCif = idCif;
    }
}
