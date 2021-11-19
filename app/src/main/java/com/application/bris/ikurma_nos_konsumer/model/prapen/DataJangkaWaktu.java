package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataJangkaWaktu {
    @SerializedName("UmurNasabahTASPEN")
    @Expose
    private String umurNasabahTASPEN;
    @SerializedName("umurNasabahDukCapil")
    @Expose
    private String umurNasabahDukCapil;
    @SerializedName("JangkaWaktuProduk")
    @Expose
    private String jangkaWaktuProduk;
    @SerializedName("UmurMaksimalNasabahRAC")
    @Expose
    private String umurMaksimalNasabahRAC;
    @SerializedName("MaksimalJangkaWaktu")
    @Expose
    private String maksimalJangkaWaktu;


    public String getUmurNasabahTASPEN() {
        return umurNasabahTASPEN;
    }

    public void setUmurNasabahTASPEN(String umurNasabahTASPEN) {
        this.umurNasabahTASPEN = umurNasabahTASPEN;
    }

    public String getUmurNasabahDukCapil() {
        return umurNasabahDukCapil;
    }

    public void setUmurNasabahDukCapil(String umurNasabahDukCapil) {
        this.umurNasabahDukCapil = umurNasabahDukCapil;
    }

    public String getJangkaWaktuProduk() {
        return jangkaWaktuProduk;
    }

    public void setJangkaWaktuProduk(String jangkaWaktuProduk) {
        this.jangkaWaktuProduk = jangkaWaktuProduk;
    }

    public String getUmurMaksimalNasabahRAC() {
        return umurMaksimalNasabahRAC;
    }

    public void setUmurMaksimalNasabahRAC(String umurMaksimalNasabahRAC) {
        this.umurMaksimalNasabahRAC = umurMaksimalNasabahRAC;
    }

    public String getMaksimalJangkaWaktu() {
        return maksimalJangkaWaktu;
    }

    public void setMaksimalJangkaWaktu(String maksimalJangkaWaktu) {
        this.maksimalJangkaWaktu = maksimalJangkaWaktu;
    }
}
