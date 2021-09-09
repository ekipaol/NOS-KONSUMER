package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 05/07/2019.
 */

public class DataDeviasi {
    @SerializedName("UID")
    private int uid;
    @SerializedName("FID_ROLE")
    private int fid_role;
    @SerializedName("NAMA_PEGAWAI")
    private String nama_pegawai;

    public DataDeviasi(int uid, int fid_role, String nama_pegawai) {
        this.uid = uid;
        this.fid_role = fid_role;
        this.nama_pegawai = nama_pegawai;
    }

    public int getUid() {
        return uid;
    }

    public int getFid_role() {
        return fid_role;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }
}
