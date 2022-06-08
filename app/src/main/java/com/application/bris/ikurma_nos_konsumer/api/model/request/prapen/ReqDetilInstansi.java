package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqDetilInstansi {
    @SerializedName("InstansiId")
    @Expose
    private Long idInstansi;

    public Long getIdInstansi() {
        return idInstansi;
    }

    public void setIdInstansi(Long idInstansi) {
        this.idInstansi = idInstansi;
    }
}
