package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataListLngp {
    @SerializedName("noLngp")
    @Expose
    private String noLngp;

    @SerializedName("namaInstansi")
    @Expose
    private String namaInstansi;

    public String getNoLngp() {
        return noLngp;
    }

    public void setNoLngp(String noLngp) {
        this.noLngp = noLngp;
    }

    public String getNamaInstansi() {
        return namaInstansi;
    }

    public void setNamaInstansi(String namaInstansi) {
        this.namaInstansi = namaInstansi;
    }
}
