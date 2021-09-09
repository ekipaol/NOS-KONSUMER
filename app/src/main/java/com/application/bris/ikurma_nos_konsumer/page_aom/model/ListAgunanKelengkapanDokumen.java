package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 17/07/2019.
 */

public class ListAgunanKelengkapanDokumen {

    @SerializedName("KATEGORI")
    public String kATEGORI;
    @SerializedName("FID_AGUNAN")
    public Integer fIDAGUNAN;
    @SerializedName("ID")
    public Integer iD;
    @SerializedName("NO_SERTIFIKAT")
    public String nOSERTIFIKAT;

    public String getkATEGORI() {
        return kATEGORI;
    }

    public Integer getfIDAGUNAN() {
        return fIDAGUNAN;
    }

    public Integer getiD() {
        return iD;
    }

    public String getnOSERTIFIKAT() {
        return nOSERTIFIKAT;
    }
}
