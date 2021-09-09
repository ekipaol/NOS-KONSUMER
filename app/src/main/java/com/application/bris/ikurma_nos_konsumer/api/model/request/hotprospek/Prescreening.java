package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 25/06/2019.
 */

public class Prescreening {
    @SerializedName("idAplikasi")
    private int idAplikasi;

    public Prescreening(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public void setIdAplikasi(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }
}
