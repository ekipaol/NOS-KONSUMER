package com.application.bris.ikurma_nos_konsumer.api.model.request.general;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 28/10/2019.
 */

public class searchListSektorEkonomi {

    @SerializedName("idAplikasi")
    private int idAplikasi;
    @SerializedName("jenisPenggunaan")
    private String jenisPenggunaan;

    public void setIdAplikasi(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public void setJenisPenggunaan(String jenisPenggunaan) {
        this.jenisPenggunaan = jenisPenggunaan;
    }
}
