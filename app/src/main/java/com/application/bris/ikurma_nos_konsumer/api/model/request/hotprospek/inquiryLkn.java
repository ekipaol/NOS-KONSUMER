package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 26/06/2019.
 */

public class inquiryLkn {
    @SerializedName("fid_aplikasi")
    private String fid_aplikasi;
    @SerializedName("cif")
    private String cif;

    public inquiryLkn(String fid_aplikasi, String cif) {
        this.fid_aplikasi = fid_aplikasi;
        this.cif = cif;
    }

    public void setFid_aplikasi(String fid_aplikasi) {
        this.fid_aplikasi = fid_aplikasi;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }
}
