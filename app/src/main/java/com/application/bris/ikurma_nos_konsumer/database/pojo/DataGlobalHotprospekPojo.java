package com.application.bris.ikurma_nos_konsumer.database.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DataGlobalHotprospekPojo extends RealmObject {

    @PrimaryKey
    @SerializedName("idAplikasi")
    private int idAplikasi;
    @SerializedName("usia")
    @Expose
    private int usia;
    @SerializedName("rpc")
    @Expose
    private String rpc;
    @SerializedName("adalahJandaDuda")
    @Expose
    private boolean adalahJandaDuda;

    public boolean isAdalahJandaDuda() {
        return adalahJandaDuda;
    }

    public void setAdalahJandaDuda(boolean adalahJandaDuda) {
        this.adalahJandaDuda = adalahJandaDuda;
    }

    public int getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public String getRpc() {
        return rpc;
    }

    public void setRpc(String rpc) {
        this.rpc = rpc;
    }
}
