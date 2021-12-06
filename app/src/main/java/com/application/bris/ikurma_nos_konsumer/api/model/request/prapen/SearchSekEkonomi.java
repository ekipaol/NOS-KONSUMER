package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.SerializedName;

public class SearchSekEkonomi {

    @SerializedName("kategori")
    private String kategori;
    @SerializedName("kriteria")
    private String kriteria;
    @SerializedName("keyword")
    private String keyword;

    public SearchSekEkonomi(String keyword) {
        this.kategori = kategori;
        this.kriteria = kriteria;
        this.keyword = keyword;
    }

    public SearchSekEkonomi() {

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


