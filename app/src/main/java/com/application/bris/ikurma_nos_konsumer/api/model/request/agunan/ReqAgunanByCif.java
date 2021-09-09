package com.application.bris.ikurma_nos_konsumer.api.model.request.agunan;

import com.google.gson.annotations.SerializedName;

public class ReqAgunanByCif {

    @SerializedName("idApl")
    private String idApl;
    @SerializedName("idCif")
    private String idCif;

    public String getIdApl() {
        return idApl;
    }

    public void setIdApl(String idApl) {
        this.idApl = idApl;
    }

    public String getIdCif() {
        return idCif;
    }

    public void setIdCif(String idCif) {
        this.idCif = idCif;
    }
}
