package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.history.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 13/05/2019.
 */

public class ModelHistoryCovenance {
    @SerializedName("JABATAN")
    private String jabatan;
    @SerializedName("NAMA_PEMUTUS")
    private String nama_pemutus;
    @SerializedName("CATATAN_PEMUTUS")
    private String catatan_pemutus;
    @SerializedName("PUTUSAN_PEMUTUS")
    private String putusan_pemutus;
    @SerializedName("JENIS_PUTUSAN")
    private String jenis_putusan;

    public ModelHistoryCovenance(String jabatan, String nama_pemutus, String catatan_pemutus, String putusan_pemutus, String jenis_putusan) {
        this.jabatan = jabatan;
        this.nama_pemutus = nama_pemutus;
        this.catatan_pemutus = catatan_pemutus;
        this.putusan_pemutus = putusan_pemutus;
        this.jenis_putusan = jenis_putusan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public String getNama_pemutus() {
        return nama_pemutus;
    }

    public String getCatatan_pemutus() {
        return catatan_pemutus;
    }

    public String getPutusan_pemutus() {
        return putusan_pemutus;
    }

    public String getJenis_putusan() {
        return jenis_putusan;
    }
}
