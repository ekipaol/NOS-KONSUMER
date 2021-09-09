package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 13/05/2019.
 */

public class datapembiayaan {
    @SerializedName("PLAFOND")
    private Double plafond;
    @SerializedName("STATUS_APLIKASI")
    private String status_aplikasi;
    @SerializedName("ID_APLIKASI")
    private String id_aplikasi;
    @SerializedName("TIPE_PRODUK")
    private String tipe_produk;
    @SerializedName("FID_STATUS")
    private String fid_status;
    @SerializedName("TANGGAL_ENTRY")
    private String tanggal_entry;
    @SerializedName("UKER_PEMRAKARSA")
    private String uker_pemrakarsa;
    @SerializedName("NAMA_PEMRAKARSA")
    private String nama_pemrakarsa;
    @SerializedName("NOREK_PENCAIRAN")
    private String norek_pencairan;

    public datapembiayaan(Double plafond, String status_aplikasi, String id_aplikasi, String tipe_produk, String fid_status, String tanggal_entry, String uker_pemrakarsa, String nama_pemrakarsa, String norek_pencairan) {
        this.plafond = plafond;
        this.status_aplikasi = status_aplikasi;
        this.id_aplikasi = id_aplikasi;
        this.tipe_produk = tipe_produk;
        this.fid_status = fid_status;
        this.tanggal_entry = tanggal_entry;
        this.uker_pemrakarsa = uker_pemrakarsa;
        this.nama_pemrakarsa = nama_pemrakarsa;
        this.norek_pencairan = norek_pencairan;
    }


    public Double getPlafond() {
        return plafond;
    }

    public void setPlafond(Double plafond) {
        this.plafond = plafond;
    }

    public String getStatus_aplikasi() {
        return status_aplikasi;
    }

    public void setStatus_aplikasi(String status_aplikasi) {
        this.status_aplikasi = status_aplikasi;
    }

    public String getId_aplikasi() {
        return id_aplikasi;
    }

    public void setId_aplikasi(String id_aplikasi) {
        this.id_aplikasi = id_aplikasi;
    }

    public String getTipe_produk() {
        return tipe_produk;
    }

    public void setTipe_produk(String tipe_produk) {
        this.tipe_produk = tipe_produk;
    }

    public String getFid_status() {
        return fid_status;
    }

    public void setFid_status(String fid_status) {
        this.fid_status = fid_status;
    }

    public String getTanggal_entry() {
        return tanggal_entry;
    }

    public void setTanggal_entry(String tanggal_entry) {
        this.tanggal_entry = tanggal_entry;
    }

    public String getUker_pemrakarsa() {
        return uker_pemrakarsa;
    }

    public void setUker_pemrakarsa(String uker_pemrakarsa) {
        this.uker_pemrakarsa = uker_pemrakarsa;
    }

    public String getNama_pemrakarsa() {
        return nama_pemrakarsa;
    }

    public void setNama_pemrakarsa(String nama_pemrakarsa) {
        this.nama_pemrakarsa = nama_pemrakarsa;
    }

    public String getNorek_pencairan() {
        return norek_pencairan;
    }

    public void setNorek_pencairan(String norek_pencairan) {
        this.norek_pencairan = norek_pencairan;
    }
}
