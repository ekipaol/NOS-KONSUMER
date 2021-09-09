package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 27/06/2019.
 */

public class kirimPutusanMikro {

    @SerializedName("idApl")
    private int idApl;
    @SerializedName("uid")
    private String uid;
    @SerializedName("catatanPemutus")
    private String catatanPemutus;

    public kirimPutusanMikro(int idApl, String uid, String catatanPemutus) {
        this.idApl = idApl;
        this.uid = uid;
        this.catatanPemutus = catatanPemutus;
    }

    public void setIdApl(int idApl) {
        this.idApl = idApl;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setCatatanPemutus(String catatanPemutus) {
        this.catatanPemutus = catatanPemutus;
    }
}
