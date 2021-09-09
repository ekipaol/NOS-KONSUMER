package com.application.bris.ikurma_nos_konsumer.page_aom.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/26/2019.
 */

public class MlistInstansi {

    @SerializedName("ID_INSTANSI")
    private int ID_INSTANSI;
    @SerializedName("NAMA")
    private String NAMA;
    @SerializedName("FID_BIDANG_PEKERJAAN")
    private String FID_BIDANG_PEKERJAAN;
    @SerializedName("DESC_BIDANG_PEKERJAAN")
    private String DESC_BIDANG_PEKERJAAN;

    public MlistInstansi(int ID_INSTANSI, String NAMA, String FID_BIDANG_PEKERJAAN, String DESC_BIDANG_PEKERJAAN) {
        this.ID_INSTANSI = ID_INSTANSI;
        this.NAMA = NAMA;
        this.FID_BIDANG_PEKERJAAN = FID_BIDANG_PEKERJAAN;
        this.DESC_BIDANG_PEKERJAAN = DESC_BIDANG_PEKERJAAN;
    }

    public int getID_INSTANSI() {
        return ID_INSTANSI;
    }

    public String getNAMA() {
        return NAMA;
    }

    public String getFID_BIDANG_PEKERJAAN() {
        return FID_BIDANG_PEKERJAAN;
    }

    public String getDESC_BIDANG_PEKERJAAN() {
        return DESC_BIDANG_PEKERJAAN;
    }


}
