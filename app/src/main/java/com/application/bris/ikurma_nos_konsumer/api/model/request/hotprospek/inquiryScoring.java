package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 26/06/2019.
 */

public class inquiryScoring {
    @SerializedName("cif")
    private int cif;
    @SerializedName("idAplikasi")
    private int idAplikasi;

    public inquiryScoring(int cif, int idAplikasi) {
        this.cif = cif;
        this.idAplikasi = idAplikasi;
    }

    public void setCif(int cif) {
        this.cif = cif;
    }

    public void setIdAplikasi(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }
}
