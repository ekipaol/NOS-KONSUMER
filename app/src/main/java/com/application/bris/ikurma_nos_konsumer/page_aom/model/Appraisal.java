package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 09/07/2019.
 */

public class Appraisal {
    @SerializedName("ID_ST_APLIKASI")
    public Integer ID_ST_APLIKASI;
    @SerializedName("KODE_PRODUK")
    public String KODE_PRODUK;
    @SerializedName("NO_AKAD")
    public String NO_AKAD;
    @SerializedName("PROGRAM_NAME")
    public String PROGRAM_NAME;
    @SerializedName("PLAFOND_INDUK")
    public Long PLAFOND_INDUK;
    @SerializedName("KLASIFIKASI_KREDIT")
    public String KLASIFIKASI_KREDIT;
    @SerializedName("ID_APLIKASI")
    public Integer ID_APLIKASI;
    @SerializedName("GIMMICK_ID")
    public Integer GIMMICK_ID;
    @SerializedName("TANGGAL_ENTRY")
    public String TANGGAL_ENTRY;
    @SerializedName("FID_PHOTO")
    public Integer FID_PHOTO;
    @SerializedName("JW")
    public Integer JW;
    @SerializedName("NAMA_DEBITUR_1")
    public String NAMA_DEBITUR_1;
    @SerializedName("FID_CIF_LAS")
    public Integer FID_CIF_LAS;
    @SerializedName("STATUS_APLIKASI")
    public String STATUS_APLIKASI;
    @SerializedName("NAMA_PRODUK")
    public String NAMA_PRODUK;

    public Appraisal(Integer ID_ST_APLIKASI, String KODE_PRODUK, String NO_AKAD, String PROGRAM_NAME, Long PLAFOND_INDUK, String KLASIFIKASI_KREDIT, Integer ID_APLIKASI, Integer GIMMICK_ID, String TANGGAL_ENTRY, Integer FID_PHOTO, Integer JW, String NAMA_DEBITUR_1, Integer FID_CIF_LAS, String STATUS_APLIKASI, String NAMA_PRODUK) {
        this.ID_ST_APLIKASI = ID_ST_APLIKASI;
        this.KODE_PRODUK = KODE_PRODUK;
        this.NO_AKAD = NO_AKAD;
        this.PROGRAM_NAME = PROGRAM_NAME;
        this.PLAFOND_INDUK = PLAFOND_INDUK;
        this.KLASIFIKASI_KREDIT = KLASIFIKASI_KREDIT;
        this.ID_APLIKASI = ID_APLIKASI;
        this.GIMMICK_ID = GIMMICK_ID;
        this.TANGGAL_ENTRY = TANGGAL_ENTRY;
        this.FID_PHOTO = FID_PHOTO;
        this.JW = JW;
        this.NAMA_DEBITUR_1 = NAMA_DEBITUR_1;
        this.FID_CIF_LAS = FID_CIF_LAS;
        this.STATUS_APLIKASI = STATUS_APLIKASI;
        this.NAMA_PRODUK = NAMA_PRODUK;
    }

    public Integer getID_ST_APLIKASI() {
        return ID_ST_APLIKASI;
    }

    public String getKODE_PRODUK() {
        return KODE_PRODUK;
    }

    public String getNO_AKAD() {
        return NO_AKAD;
    }

    public String getPROGRAM_NAME() {
        return PROGRAM_NAME;
    }

    public Long getPLAFOND_INDUK() {
        return PLAFOND_INDUK;
    }

    public String getKLASIFIKASI_KREDIT() {
        return KLASIFIKASI_KREDIT;
    }

    public Integer getID_APLIKASI() {
        return ID_APLIKASI;
    }

    public Integer getGIMMICK_ID() {
        return GIMMICK_ID;
    }

    public String getTANGGAL_ENTRY() {
        return TANGGAL_ENTRY;
    }

    public Integer getFID_PHOTO() {
        return FID_PHOTO;
    }

    public Integer getJW() {
        return JW;
    }

    public String getNAMA_DEBITUR_1() {
        return NAMA_DEBITUR_1;
    }

    public Integer getFID_CIF_LAS() {
        return FID_CIF_LAS;
    }

    public String getSTATUS_APLIKASI() {
        return STATUS_APLIKASI;
    }

    public String getNAMA_PRODUK() {
        return NAMA_PRODUK;
    }
}
