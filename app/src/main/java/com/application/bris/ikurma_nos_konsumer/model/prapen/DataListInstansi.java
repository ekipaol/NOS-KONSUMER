package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataListInstansi {
    @SerializedName("namaInstansi")
    @Expose
    private String namaInstansi;
    @SerializedName("jenisInstansi")
    @Expose
    private String jenisInstansi;
    @SerializedName("statusInstansi")
    @Expose
    private String statusInstansi;
    @SerializedName("kodeInstansi")
    @Expose
    private String kodeInstansi;

    public String getNamaInstansi() {
        return namaInstansi;
    }

    public void setNamaInstansi(String namaInstansi) {
        this.namaInstansi = namaInstansi;
    }

    public String getJenisInstansi() {
        return jenisInstansi;
    }

    public void setJenisInstansi(String jenisInstansi) {
        this.jenisInstansi = jenisInstansi;
    }

    public String getStatusInstansi() {
        return statusInstansi;
    }

    public void setStatusInstansi(String statusInstansi) {
        this.statusInstansi = statusInstansi;
    }

    public String getKodeInstansi() {
        return kodeInstansi;
    }

    public void setKodeInstansi(String kodeInstansi) {
        this.kodeInstansi = kodeInstansi;
    }
}
