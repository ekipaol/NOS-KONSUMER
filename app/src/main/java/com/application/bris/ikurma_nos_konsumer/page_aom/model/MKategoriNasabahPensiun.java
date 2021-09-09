package com.application.bris.ikurma_nos_konsumer.page_aom.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/26/2019.
 */

public class MKategoriNasabahPensiun {

    @SerializedName("ID")
    private String ID;
    @SerializedName("Nama")
    private String Nama;

    public String getID() {
        return ID;
    }

    public String getNama() {
        return Nama;
    }
}
