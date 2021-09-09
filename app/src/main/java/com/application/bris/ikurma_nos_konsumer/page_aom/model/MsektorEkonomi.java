package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 07/05/2019.
 */

public class MsektorEkonomi {
    @SerializedName("sektorEkonomiText")
    private String sektorEkonomiText;
    @SerializedName("sektorEkonomiSID")
    private String sektorEkonomiSID;
    @SerializedName("sektorEkonomiLBU")
    private String sektorEkonomiLBU;

    public MsektorEkonomi(String sektorEkonomiText, String sektorEkonomiSID, String sektorEkonomiLBU) {
        this.sektorEkonomiText = sektorEkonomiText;
        this.sektorEkonomiSID = sektorEkonomiSID;
        this.sektorEkonomiLBU = sektorEkonomiLBU;
    }

    public String getSektorEkonomiText() {
        return sektorEkonomiText;
    }

    public void setSektorEkonomiText(String sektorEkonomiText) {
        this.sektorEkonomiText = sektorEkonomiText;
    }

    public String getSektorEkonomiSID() {
        return sektorEkonomiSID;
    }

    public void setSektorEkonomiSID(String sektorEkonomiSID) {
        this.sektorEkonomiSID = sektorEkonomiSID;
    }

    public String getSektorEkonomiLBU() {
        return sektorEkonomiLBU;
    }

    public void setSektorEkonomiLBU(String sektorEkonomiLBU) {
        this.sektorEkonomiLBU = sektorEkonomiLBU;
    }
}
