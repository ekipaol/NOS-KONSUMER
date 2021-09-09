package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 17/05/2019.
 */

public class inquiryHotprospek {
    @SerializedName("idAplikasi")
    private int idAplikasi;

    public inquiryHotprospek(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public int getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }
}
