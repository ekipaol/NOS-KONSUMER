package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class ListJenisBangunan {

    @SerializedName("KODE_OKUPASI")
    private String KODE_OKUPASI;
    @SerializedName("DESC_BRINS")
    private String DESC_BRINS;

    public String getKODE_OKUPASI() {
        return KODE_OKUPASI;
    }

    public String getDESC_BRINS() {
        return DESC_BRINS;
    }
}
