package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JaminandanDokumen {
    @SerializedName("ApplicationId")
    @Expose
    private String applicationId;
    @SerializedName("No_SK_Pensiun")
    @Expose
    private String noSKPensiun;
    @SerializedName("Tanggal_Terbit_SK_Pensiun")
    @Expose
    private String tanggalTerbitSKPensiun;
    @SerializedName("Lembaga_Penerbit_SK_Pensiun")
    @Expose
    private String lembagaPenerbitSKPensiun;
    @SerializedName("No_SK_Pengangkatan")
    @Expose
    private String noSKPengangkatan;
    @SerializedName("Tanggal_Terbit_SK_Pengangkat")
    @Expose
    private String tanggalTerbitSKPengangkat;
    @SerializedName("Lembaga_Penerbit_SK_Pengangk")
    @Expose
    private String lembagaPenerbitSKPengangk;
    @SerializedName("No_SK_Terakhir")
    @Expose
    private String noSKTerakhir;
    @SerializedName("Tanggal_Terbit_SK_Terakhir")
    @Expose
    private String tanggalTerbitSKTerakhir;
    @SerializedName("Lembaga_Penerbit_SK_Terakhir")
    @Expose
    private String lembagaPenerbitSKTerakhir;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getNoSKPensiun() {
        return noSKPensiun;
    }

    public void setNoSKPensiun(String noSKPensiun) {
        this.noSKPensiun = noSKPensiun;
    }

    public String getTanggalTerbitSKPensiun() {
        return tanggalTerbitSKPensiun;
    }

    public void setTanggalTerbitSKPensiun(String tanggalTerbitSKPensiun) {
        this.tanggalTerbitSKPensiun = tanggalTerbitSKPensiun;
    }

    public String getLembagaPenerbitSKPensiun() {
        return lembagaPenerbitSKPensiun;
    }

    public void setLembagaPenerbitSKPensiun(String lembagaPenerbitSKPensiun) {
        this.lembagaPenerbitSKPensiun = lembagaPenerbitSKPensiun;
    }

    public String getNoSKPengangkatan() {
        return noSKPengangkatan;
    }

    public void setNoSKPengangkatan(String noSKPengangkatan) {
        this.noSKPengangkatan = noSKPengangkatan;
    }

    public String getTanggalTerbitSKPengangkat() {
        return tanggalTerbitSKPengangkat;
    }

    public void setTanggalTerbitSKPengangkat(String tanggalTerbitSKPengangkat) {
        this.tanggalTerbitSKPengangkat = tanggalTerbitSKPengangkat;
    }

    public String getLembagaPenerbitSKPengangk() {
        return lembagaPenerbitSKPengangk;
    }

    public void setLembagaPenerbitSKPengangk(String lembagaPenerbitSKPengangk) {
        this.lembagaPenerbitSKPengangk = lembagaPenerbitSKPengangk;
    }

    public String getNoSKTerakhir() {
        return noSKTerakhir;
    }

    public void setNoSKTerakhir(String noSKTerakhir) {
        this.noSKTerakhir = noSKTerakhir;
    }

    public String getTanggalTerbitSKTerakhir() {
        return tanggalTerbitSKTerakhir;
    }

    public void setTanggalTerbitSKTerakhir(String tanggalTerbitSKTerakhir) {
        this.tanggalTerbitSKTerakhir = tanggalTerbitSKTerakhir;
    }

    public String getLembagaPenerbitSKTerakhir() {
        return lembagaPenerbitSKTerakhir;
    }

    public void setLembagaPenerbitSKTerakhir(String lembagaPenerbitSKTerakhir) {
        this.lembagaPenerbitSKTerakhir = lembagaPenerbitSKTerakhir;
    }
}
