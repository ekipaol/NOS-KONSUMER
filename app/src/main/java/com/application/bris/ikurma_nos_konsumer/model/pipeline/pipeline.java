package com.application.bris.ikurma_nos_konsumer.model.pipeline;

/**
 * Created by PID on 4/26/2019.
 */

public class pipeline {
    private int foto;
    private String nama;
    private String produk;
    private String plafond;
    private String tenor;
    private String waktu;

    public pipeline(int foto, String nama, String produk, String plafond, String tenor, String waktu) {
        this.foto = foto;
        this.nama = nama;
        this.produk = produk;
        this.plafond = plafond;
        this.tenor = tenor;
        this.waktu = waktu;
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

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
