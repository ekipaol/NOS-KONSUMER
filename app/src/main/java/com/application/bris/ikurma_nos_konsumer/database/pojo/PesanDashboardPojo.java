package com.application.bris.ikurma_nos_konsumer.database.pojo;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PesanDashboardPojo extends RealmObject {

    @PrimaryKey
    @SerializedName("idPesan")
    private int idPesan;

    @SerializedName("isiPesan")
    private String isiPesan;

    @SerializedName("pesanAktif")
    private boolean pesanAktif;

    public int getIdPesan() {
        return idPesan;
    }

    public void setIdPesan(int idPesan) {
        this.idPesan = idPesan;
    }

    public String getIsiPesan() {
        return isiPesan;
    }

    public void setIsiPesan(String isiPesan) {
        this.isiPesan = isiPesan;
    }

    public boolean isPesanAktif() {
        return pesanAktif;
    }

    public void setPesanAktif(boolean pesanAktif) {
        this.pesanAktif = pesanAktif;
    }


}
