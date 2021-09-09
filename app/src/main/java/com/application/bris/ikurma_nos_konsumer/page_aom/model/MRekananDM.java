package com.application.bris.ikurma_nos_konsumer.page_aom.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/26/2019.
 */

public class MRekananDM {

    @SerializedName("ID_REKANAN")
    private String ID_REKANAN;
    @SerializedName("REKANAN_DIRECT_MARKETING")
    private String REKANAN_DIRECT_MARKETING;

    public String getID_REKANAN() {
        return ID_REKANAN;
    }

    public String getREKANAN_DIRECT_MARKETING() {
        return REKANAN_DIRECT_MARKETING;
    }
}
