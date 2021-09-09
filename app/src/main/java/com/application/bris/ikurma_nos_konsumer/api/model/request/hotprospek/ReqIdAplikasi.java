package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 20/06/2019.
 */

public class ReqIdAplikasi {
    @SerializedName("idAplikasi")
    private int idAplikasi;

    public ReqIdAplikasi(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }
    public ReqIdAplikasi() {

    }

    public void setIdAplikasi(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }
}
