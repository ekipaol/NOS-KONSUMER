package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class UserAppraisalAgunanTerikat {
    @SerializedName("FID_UID")
    private String FID_UID;
    @SerializedName("TANGGAL_REQUEST")
    private String TANGGAL_REQUEST;
    @SerializedName("ID_REQUEST")
    private String ID_REQUEST;
    @SerializedName("NAMA_PEGAWAI")
    private String NAMA_PEGAWAI;
    @SerializedName("FID_STATUS")
    private String FID_STATUS;
    @SerializedName("FID_APLIKASI")
    private String FID_APLIKASI;

    public String getFID_UID() {
        return FID_UID;
    }

    public void setFID_UID(String FID_UID) {
        this.FID_UID = FID_UID;
    }

    public String getTANGGAL_REQUEST() {
        return TANGGAL_REQUEST;
    }

    public void setTANGGAL_REQUEST(String TANGGAL_REQUEST) {
        this.TANGGAL_REQUEST = TANGGAL_REQUEST;
    }

    public String getID_REQUEST() {
        return ID_REQUEST;
    }

    public void setID_REQUEST(String ID_REQUEST) {
        this.ID_REQUEST = ID_REQUEST;
    }

    public String getNAMA_PEGAWAI() {
        return NAMA_PEGAWAI;
    }

    public void setNAMA_PEGAWAI(String NAMA_PEGAWAI) {
        this.NAMA_PEGAWAI = NAMA_PEGAWAI;
    }

    public String getFID_STATUS() {
        return FID_STATUS;
    }

    public void setFID_STATUS(String FID_STATUS) {
        this.FID_STATUS = FID_STATUS;
    }

    public String getFID_APLIKASI() {
        return FID_APLIKASI;
    }

    public void setFID_APLIKASI(String FID_APLIKASI) {
        this.FID_APLIKASI = FID_APLIKASI;
    }
}
