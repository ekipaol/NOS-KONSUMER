package com.application.bris.ikurma_nos_konsumer.api.model.request.ceknasabah;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 14/05/2019.
 */

public class cekNasabah {
    @SerializedName("key")
    private String key;

    public void setKey(String key) {
        this.key = key;
    }
}
