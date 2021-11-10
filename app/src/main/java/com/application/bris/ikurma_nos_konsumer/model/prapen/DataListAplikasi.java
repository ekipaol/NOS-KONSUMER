package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataListAplikasi {
    @SerializedName("Nama")
    @Expose
    private String nama;
    @SerializedName("Plafond")
    @Expose
    private String plafond;
    @SerializedName("JangkaWaktu")
    @Expose
    private String jangkaWaktu;
    @SerializedName("TypeProduk")
    @Expose
    private String typeProduk;
    @SerializedName("TypeProgram")
    @Expose
    private String typeProgram;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;
    @SerializedName("StatusAplikasi")
    @Expose
    private String statusAplikasi;
    @SerializedName("ApplicationNo")
    @Expose
    private String applicationNo;
    @SerializedName("ApplicationId")
    @Expose
    private String applicationId;
    @SerializedName("StatusAplikasiId")
    @Expose
    private String statusAplikasiId;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPlafond() {
        return plafond;
    }

    public void setPlafond(String plafond) {
        this.plafond = plafond;
    }

    public String getJangkaWaktu() {
        return jangkaWaktu;
    }

    public void setJangkaWaktu(String jangkaWaktu) {
        this.jangkaWaktu = jangkaWaktu;
    }

    public String getTypeProduk() {
        return typeProduk;
    }

    public void setTypeProduk(String typeProduk) {
        this.typeProduk = typeProduk;
    }

    public String getTypeProgram() {
        return typeProgram;
    }

    public void setTypeProgram(String typeProgram) {
        this.typeProgram = typeProgram;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStatusAplikasi() {
        return statusAplikasi;
    }

    public void setStatusAplikasi(String statusAplikasi) {
        this.statusAplikasi = statusAplikasi;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getStatusAplikasiId() {
        return statusAplikasiId;
    }

    public void setStatusAplikasiId(String statusAplikasiId) {
        this.statusAplikasiId = statusAplikasiId;
    }
}
