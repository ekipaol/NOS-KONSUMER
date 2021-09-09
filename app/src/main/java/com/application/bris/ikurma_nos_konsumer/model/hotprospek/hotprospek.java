package com.application.bris.ikurma_nos_konsumer.model.hotprospek;

/**
 * Created by PID on 4/26/2019.
 */

public class hotprospek {
    private int foto;
    private String nama;
    private String produk;
    private String plafond;
    private String tenor;
    private String tujuanpenggunaan;
    private String status;

    public hotprospek(int foto, String nama, String produk, String plafond, String tenor, String tujuanpenggunaan, String status) {
        this.foto = foto;
        this.nama = nama;
        this.produk = produk;
        this.plafond = plafond;
        this.tenor = tenor;
        this.tujuanpenggunaan = tujuanpenggunaan;
        this.status = status;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public String getPlafond() {
        return plafond;
    }

    public void setPlafond(String plafond) {
        this.plafond = plafond;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public String getTujuanpenggunaan() {
        return tujuanpenggunaan;
    }

    public void setTujuanpenggunaan(String tujuanpenggunaan) {
        this.tujuanpenggunaan = tujuanpenggunaan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
