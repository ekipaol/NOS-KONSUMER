package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class  JenisPekerjaan {

    @SerializedName("JENIS_PEKERJAAN_DESC")
    public String JENIS_PEKERJAAN_DESC;

    @SerializedName("JENIS_PEKERJAAN")
    public String JENIS_PEKERJAAN;

    public List<DeskripsiPekerjaan> deskripsiPekerjaans;

    public void addDeskripsiPekerjaan(DeskripsiPekerjaan deskripsiPekerjaan){
        this.deskripsiPekerjaans.add(deskripsiPekerjaan);
    }

    public String getJENIS_PEKERJAAN_DESC() {
        return JENIS_PEKERJAAN_DESC;
    }

    public void setJENIS_PEKERJAAN_DESC(String JENIS_PEKERJAAN_DESC) {
        this.JENIS_PEKERJAAN_DESC = JENIS_PEKERJAAN_DESC;
    }

    public String getJENIS_PEKERJAAN() {
        return JENIS_PEKERJAAN;
    }

    public void setJENIS_PEKERJAAN(String JENIS_PEKERJAAN) {
        this.JENIS_PEKERJAAN = JENIS_PEKERJAAN;
    }

    public List<DeskripsiPekerjaan> getDeskripsiPekerjaans() {
        return deskripsiPekerjaans;
    }

    public void setDeskripsiPekerjaans(List<DeskripsiPekerjaan> deskripsiPekerjaans) {
        this.deskripsiPekerjaans = deskripsiPekerjaans;
    }
}
