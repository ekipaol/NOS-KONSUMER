package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class AgunanKendaraanFront {

    @SerializedName("STATUS")
    private int STATUS;
    @SerializedName("JENIS_KENDARAAN")
    private String JENIS_KENDARAAN;
    @SerializedName("FID_AGUNAN")
    private String FID_AGUNAN;
    @SerializedName("NOMOR_BPKB")
    private String NOMOR_BPKB;
    @SerializedName("NAMA_PEMILIK_BPKB")
    private String NAMA_PEMILIK_BPKB;
    @SerializedName("NILAI_PASAR")
    private String NILAI_PASAR;
    @SerializedName("MODEL_KENDARAAN")
    private String MODEL_KENDARAAN;

    public int getSTATUS() {
        return STATUS;
    }

    public String getJENIS_KENDARAAN() {
        return JENIS_KENDARAAN;
    }

    public String getFID_AGUNAN() {
        return FID_AGUNAN;
    }

    public String getNOMOR_BPKB() {
        return NOMOR_BPKB;
    }

    public String getNAMA_PEMILIK_BPKB() {
        return NAMA_PEMILIK_BPKB;
    }

    public String getNILAI_PASAR() {
        return NILAI_PASAR;
    }

    public String getMODEL_KENDARAAN() {
        return MODEL_KENDARAAN;
    }
}
