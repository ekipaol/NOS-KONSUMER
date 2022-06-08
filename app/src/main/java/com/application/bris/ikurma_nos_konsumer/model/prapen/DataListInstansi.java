package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataListInstansi {
    @SerializedName("Id")
    @Expose
    private String idInstansi;
    @SerializedName("Nama")
    @Expose
    private String namaInstansi;
    @SerializedName("jenisInstansi")
    @Expose
    private String jenisInstansi;
    @SerializedName("Status")
    @Expose
    private String statusInstansi;
    @SerializedName("Kode_Instansi_Induk")
    @Expose
    private String kodeInstansiInduk;
    @SerializedName("Escrow")
    @Expose
    private String escrow;
    @SerializedName("Cabang")
    @Expose
    private String cabangAsal;

    public String getIdInstansi() {
        return idInstansi;
    }

    public void setIdInstansi(String idInstansi) {
        this.idInstansi = idInstansi;
    }

    public String getEscrow() {
        return escrow;
    }

    public void setEscrow(String escrow) {
        this.escrow = escrow;
    }

    public String getCabangAsal() {
        return cabangAsal;
    }

    public void setCabangAsal(String cabangAsal) {
        this.cabangAsal = cabangAsal;
    }

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

    public String getKodeInstansiInduk() {
        return kodeInstansiInduk;
    }

    public void setKodeInstansiInduk(String kodeInstansiInduk) {
        this.kodeInstansiInduk = kodeInstansiInduk;
    }
}
