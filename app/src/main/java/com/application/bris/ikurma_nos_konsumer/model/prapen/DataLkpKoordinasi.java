package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataLkpKoordinasi {
    @SerializedName("Nama_Cabang")
    @Expose
    private String namaCabang;
    @SerializedName("Kode_Cabang")
    @Expose
    private String kodeCabang;
    @SerializedName("Tanggal_LKP")
    @Expose
    private String tanggalLkp;
    @SerializedName("Tanggal_LKP_Kadaluarsa")
    @Expose
    private String tanggalKadaluarsa;
    @SerializedName("Img")
    @Expose
    private String idDokumen;
    @SerializedName("File_Name")
    @Expose
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getNamaCabang() {
        return namaCabang;
    }

    public void setNamaCabang(String namaCabang) {
        this.namaCabang = namaCabang;
    }

    public String getKodeCabang() {
        return kodeCabang;
    }

    public void setKodeCabang(String kodeCabang) {
        this.kodeCabang = kodeCabang;
    }

    public String getTanggalLkp() {
        return tanggalLkp;
    }

    public void setTanggalLkp(String tanggalLkp) {
        this.tanggalLkp = tanggalLkp;
    }

    public String getTanggalKadaluarsa() {
        return tanggalKadaluarsa;
    }

    public void setTanggalKadaluarsa(String tanggalKadaluarsa) {
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }
}
