package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 10/05/2019.
 */

public class tindaklanjut {
    @SerializedName("DATETIME")
    private String datetime;
    @SerializedName("FID_PIPELINE")
    private int fid_pipeline;
    @SerializedName("ID")
    private int id;
    @SerializedName("CATATAN")
    private String catatan;
    @SerializedName("TIPE")
    private String tipe;

    public tindaklanjut(String datetime, int fid_pipeline, int id, String catatan, String tipe) {
        this.datetime = datetime;
        this.fid_pipeline = fid_pipeline;
        this.id = id;
        this.catatan = catatan;
        this.tipe = tipe;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getFid_pipeline() {
        return fid_pipeline;
    }

    public void setFid_pipeline(int fid_pipeline) {
        this.fid_pipeline = fid_pipeline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
}
