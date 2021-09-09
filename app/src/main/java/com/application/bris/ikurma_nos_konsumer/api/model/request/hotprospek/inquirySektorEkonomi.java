package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 20/06/2019.
 */

public class inquirySektorEkonomi {
    @SerializedName("idAplikasi")
    private int idAplikasi;
    @SerializedName("idRole")
    private int idRole;

    public inquirySektorEkonomi(int idAplikasi, int idRole) {
        this.idAplikasi = idAplikasi;
        this.idRole = idRole;
    }

    public void setIdAplikasi(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}
