package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataCabang {
    @SerializedName("NamaCabang")
    @Expose
    private String namaCabang;
    @SerializedName("AlamatCabang")
    @Expose
    private String alamatCabang;
    @SerializedName("Telp")
    @Expose
    private String telpCabang;
    @SerializedName("Fax")
    @Expose
    private String faxCabang;

    public String getNamaCabang() {
        return namaCabang;
    }

    public void setNamaCabang(String namaCabang) {
        this.namaCabang = namaCabang;
    }

    public String getAlamatCabang() {
        return alamatCabang;
    }

    public void setAlamatCabang(String alamatCabang) {
        this.alamatCabang = alamatCabang;
    }

    public String getTelpCabang() {
        return telpCabang;
    }

    public void setTelpCabang(String telpCabang) {
        this.telpCabang = telpCabang;
    }

    public String getFaxCabang() {
        return faxCabang;
    }

    public void setFaxCabang(String faxCabang) {
        this.faxCabang = faxCabang;
    }
}
