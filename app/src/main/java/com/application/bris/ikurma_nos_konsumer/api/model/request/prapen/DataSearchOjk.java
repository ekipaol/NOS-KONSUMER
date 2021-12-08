package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.SerializedName;

public class DataSearchOjk {

    @SerializedName("kategori")
    private String kategori;
    @SerializedName("kriteria")
    private String kriteria;
    @SerializedName("keyword")
    private String keyword;

    public DataSearchOjk(String keyword) {
        this.kategori = kategori;
        this.kriteria = kriteria;
        this.keyword = keyword;
    }

    public DataSearchOjk() {

    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setKriteria(String kriteria) {
        this.kriteria = kriteria;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}


