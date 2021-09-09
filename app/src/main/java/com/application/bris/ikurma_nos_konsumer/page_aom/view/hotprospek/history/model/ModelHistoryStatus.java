package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.history.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 13/05/2019.
 */

public class ModelHistoryStatus {
    @SerializedName("FID_UID")
    private int exsposure;
    @SerializedName("JAM")
    private String jam;
    @SerializedName("NAMA_PEGAWAI")
    private String nama_pegawai;
    @SerializedName("TIPE_PRODUK")
    private String tipe_produk;
    @SerializedName("FID_ST_APLIKASI")
    private int fid_st_aplikasi;
    @SerializedName("DESC1")
    private String desc1;
    @SerializedName("TANGGAL")
    private String tanggal;

    public ModelHistoryStatus(int exsposure, String jam, String nama_pegawai, String tipe_produk, int fid_st_aplikasi, String desc1, String tanggal) {
        this.exsposure = exsposure;
        this.jam = jam;
        this.nama_pegawai = nama_pegawai;
        this.tipe_produk = tipe_produk;
        this.fid_st_aplikasi = fid_st_aplikasi;
        this.desc1 = desc1;
        this.tanggal = tanggal;
    }

    public int getExsposure() {
        return exsposure;
    }

    public String getJam() {
        return jam;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public String getTipe_produk() {
        return tipe_produk;
    }

    public int getFid_st_aplikasi() {
        return fid_st_aplikasi;
    }

    public String getDesc1() {
        return desc1;
    }

    public String getTanggal() {
        return tanggal;
    }
}
