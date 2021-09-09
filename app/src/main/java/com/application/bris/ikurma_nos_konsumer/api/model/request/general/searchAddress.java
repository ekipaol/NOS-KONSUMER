package com.application.bris.ikurma_nos_konsumer.api.model.request.general;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 17/05/2019.
 */

public class searchAddress{
    @SerializedName("kriteria")
    private String kriteria;
    @SerializedName("keyword")
    private String keyword;

    public searchAddress(String kriteria, String keyword) {
        this.kriteria = kriteria;
        this.keyword = keyword;
    }

    public void setKriteria(String kriteria) {
        this.kriteria = kriteria;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
