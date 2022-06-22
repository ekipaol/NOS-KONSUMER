package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class MGenericModel {
    @SerializedName("ID")
    private String ID;

    @SerializedName("DESC")
    private String DESC;

    @SerializedName("Nama")
    private String NAMA;

    @SerializedName("Nilai")
    private Float Nilai;


    public MGenericModel(String ID, String DESC) {
        this.ID = ID;
        this.DESC = DESC;
    }

    public MGenericModel(String ID, String DESC, Float Nilai,String Nama) {
        this.ID = ID;
        this.DESC = DESC;
        this.Nilai = Nilai;
        this.NAMA = Nama;
    }

    public Float getNilai() {
        return Nilai;
    }

    public void setNilai(Float nilai) {
        Nilai = nilai;
    }

    public MGenericModel() {
    }

    public String getNAMA() {
        return NAMA;
    }

    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDESC() {
        return DESC;
    }

    public void setDESC(String DESC) {
        this.DESC = DESC;
    }
}
