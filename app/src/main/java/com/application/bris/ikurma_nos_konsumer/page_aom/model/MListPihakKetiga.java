package com.application.bris.ikurma_nos_konsumer.page_aom.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/26/2019.
 */

public class MListPihakKetiga {

    @SerializedName("NAMA")
    private String NAMA;
    @SerializedName("ID_PIHAK_KETIGA")
    private int ID_PIHAK_KETIGA;

    public String getNAMA() {
        return NAMA;
    }

    public int getID_PIHAK_KETIGA() {
        return ID_PIHAK_KETIGA;
    }
}
