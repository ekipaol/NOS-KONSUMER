package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class RekeningAmanah {

    @SerializedName("statusDormant")
    private String statusDormant;

    @SerializedName("namaNasabah")
    private String namaNasabah;

    public String getStatusDormant() {
        return statusDormant;
    }

    public void setStatusDormant(String statusDormant) {
        this.statusDormant = statusDormant;
    }

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }
}
