package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class DataRekomAppraisal {

    @SerializedName("ID_REKOMENDASI")
    private String ID_REKOMENDASI;

    @SerializedName("DESC1")
    private String DESC1;

    @SerializedName("FID_APLIKASI")
    private String FID_APLIKASI;

    public String getID_REKOMENDASI() {
        return ID_REKOMENDASI;
    }

    public void setID_REKOMENDASI(String ID_REKOMENDASI) {
        this.ID_REKOMENDASI = ID_REKOMENDASI;
    }

    public String getDESC1() {
        return DESC1;
    }

    public void setDESC1(String DESC1) {
        this.DESC1 = DESC1;
    }

    public String getFID_APLIKASI() {
        return FID_APLIKASI;
    }

    public void setFID_APLIKASI(String FID_APLIKASI) {
        this.FID_APLIKASI = FID_APLIKASI;
    }
}
