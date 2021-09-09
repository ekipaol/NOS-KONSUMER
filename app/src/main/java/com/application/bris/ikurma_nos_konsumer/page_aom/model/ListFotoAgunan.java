package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class ListFotoAgunan {

    @SerializedName("LOCATION")
    private String LOCATION;
    @SerializedName("KATEGORI")
    private String KATEGORI;
    @SerializedName("ID")
    private int ID;
    @SerializedName("FID_TABLE")
    private int FID_TABLE;

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getKATEGORI() {
        return KATEGORI;
    }

    public void setKATEGORI(String KATEGORI) {
        this.KATEGORI = KATEGORI;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getFID_TABLE() {
        return FID_TABLE;
    }

    public void setFID_TABLE(int FID_TABLE) {
        this.FID_TABLE = FID_TABLE;
    }
}
