package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 17/05/2019.
 */

public class inquiryDataLengkap {
    @SerializedName("cif")
    private String cif;
    @SerializedName("idAplikasi")
    private String idAplikasi;

    public inquiryDataLengkap(String cif, String idAplikasi ) {
        this.cif = cif;
        this.idAplikasi = idAplikasi;
    }

    public inquiryDataLengkap( ) {
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(String idAplikasi) {
        this.idAplikasi = idAplikasi;
    }
}
