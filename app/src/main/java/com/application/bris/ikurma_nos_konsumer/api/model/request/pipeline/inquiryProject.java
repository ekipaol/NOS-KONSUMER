package com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 20/06/2019.
 */

public class inquiryProject {
    @SerializedName("kode_kanwil")
    private String kode_kanwil;

    public inquiryProject(String kode_kanwil) {
        this.kode_kanwil = kode_kanwil;
    }

    public void setKode_kanwil(String kode_kanwil) {
        this.kode_kanwil = kode_kanwil;
    }
}