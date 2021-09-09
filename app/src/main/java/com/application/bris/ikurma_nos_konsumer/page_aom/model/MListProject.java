package com.application.bris.ikurma_nos_konsumer.page_aom.model;


import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by PID on 4/26/2019.
 */

public class MListProject extends RealmObject {

    @SerializedName("NAMA")
    private String NAMA;
    @SerializedName("FID_PIHAK_KETIGA")
    private String FID_PIHAK_KETIGA;
    @SerializedName("tipe")
    private String tipe;
    @SerializedName("ID_PROJECT")
    private int ID_PROJECT;

    public MListProject(){
        super();
    }

    public String getNAMA() {
        return NAMA;
    }

    public String getFID_PIHAK_KETIGA() {
        return FID_PIHAK_KETIGA;
    }

    public String getTipe() {
        return tipe;
    }

    public int getID_PROJECT() {
        return ID_PROJECT;
    }

    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }

    public void setFID_PIHAK_KETIGA(String FID_PIHAK_KETIGA) {
        this.FID_PIHAK_KETIGA = FID_PIHAK_KETIGA;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public void setID_PROJECT(int ID_PROJECT) {
        this.ID_PROJECT = ID_PROJECT;
    }
}
