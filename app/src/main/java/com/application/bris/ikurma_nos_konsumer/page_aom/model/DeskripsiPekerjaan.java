package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class DeskripsiPekerjaan {

    @SerializedName("DETIL_PEKERJAAN_DESC")
    public String DETIL_PEKERJAAN_DESC;

    @SerializedName("DETIL_PEKERJAAN")
    public String DETIL_PEKERJAAN;

    public String getDETIL_PEKERJAAN_DESC() {
        return DETIL_PEKERJAAN_DESC;
    }

    public void setDETIL_PEKERJAAN_DESC(String DETIL_PEKERJAAN_DESC) {
        this.DETIL_PEKERJAAN_DESC = DETIL_PEKERJAAN_DESC;
    }

    public String getDETIL_PEKERJAAN() {
        return DETIL_PEKERJAAN;
    }

    public void setDETIL_PEKERJAAN(String DETIL_PEKERJAAN) {
        this.DETIL_PEKERJAAN = DETIL_PEKERJAAN;
    }
}
