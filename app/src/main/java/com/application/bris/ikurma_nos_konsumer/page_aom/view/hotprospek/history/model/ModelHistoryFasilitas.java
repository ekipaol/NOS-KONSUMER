package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.history.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 13/05/2019.
 */

public class ModelHistoryFasilitas {
    @SerializedName("EKSPOSURE")
    private int exsposure;
    @SerializedName("STATUS_APLIKASI")
    private String status_aplikasi;
    @SerializedName("PLAFOND_INDUK")
    private Double plafond_induk;
    @SerializedName("ID_APLIKASI")
    private int id_aplikasi;
    @SerializedName("TIPE_PRODUK")
    private String tipe_produk;
    @SerializedName("TANGGAL_ENTRY")
    private String tanggal_entry;
    @SerializedName("UKER_PEMRAKARSA")
    private String uker_pemrakarsa;
    @SerializedName("NAMA_PEMRAKARSA")
    private String nama_pemrakarsa;

    public ModelHistoryFasilitas(int exsposure, String status_aplikasi, Double plafond_induk, int id_aplikasi, String tipe_produk, String tanggal_entry, String uker_pemrakarsa, String nama_pemrakarsa) {
        this.exsposure = exsposure;
        this.status_aplikasi = status_aplikasi;
        this.plafond_induk = plafond_induk;
        this.id_aplikasi = id_aplikasi;
        this.tipe_produk = tipe_produk;
        this.tanggal_entry = tanggal_entry;
        this.uker_pemrakarsa = uker_pemrakarsa;
        this.nama_pemrakarsa = nama_pemrakarsa;
    }

    public int getExsposure() {
        return exsposure;
    }

    public String getStatus_aplikasi() {
        return status_aplikasi;
    }

    public Double getPlafond_induk() {
        return plafond_induk;
    }

    public int getId_aplikasi() {
        return id_aplikasi;
    }

    public String getTipe_produk() {
        return tipe_produk;
    }

    public String getTanggal_entry() {
        return tanggal_entry;
    }

    public String getUker_pemrakarsa() {
        return uker_pemrakarsa;
    }

    public String getNama_pemrakarsa() {
        return nama_pemrakarsa;
    }
}
