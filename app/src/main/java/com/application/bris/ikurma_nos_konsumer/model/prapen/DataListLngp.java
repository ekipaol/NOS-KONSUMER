package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataListLngp {
    @SerializedName("LNGP")
    @Expose
    private String noLngp;

    @SerializedName("Nama")
    @Expose
    private String namaInstansi;

    @SerializedName("Id")
    @Expose
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
