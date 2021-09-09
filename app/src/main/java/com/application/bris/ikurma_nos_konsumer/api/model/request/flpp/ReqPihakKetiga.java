package com.application.bris.ikurma_nos_konsumer.api.model.request.flpp;


import com.google.gson.annotations.SerializedName;

public class ReqPihakKetiga {
    @SerializedName("idPihakKetiga")
    private int idPihakKetiga;

    public int getIdPihakKetiga() {
        return idPihakKetiga;
    }

    public void setIdPihakKetiga(int idPihakKetiga) {
        this.idPihakKetiga = idPihakKetiga;
    }
}
