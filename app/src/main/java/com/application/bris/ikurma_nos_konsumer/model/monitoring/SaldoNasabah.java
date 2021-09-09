package com.application.bris.ikurma_nos_konsumer.model.monitoring;

import com.google.gson.annotations.SerializedName;

public class SaldoNasabah {
    @SerializedName("cabang")
    private String cabang;
    @SerializedName("saldo_yang_diblokir")
    private String saldo_yang_diblokir;
    @SerializedName("no_rek_cantik")
    private String no_rek_cantik;
    @SerializedName("klp_rekening")
    private String klp_rekening;
    @SerializedName("header23")
    private String header23;
    @SerializedName("header24")
    private String header24;
    @SerializedName("saldo_tersedia")
    private String saldo_tersedia;
    @SerializedName("header21")
    private String header21;
    @SerializedName("kategori")
    private String kategori;
    @SerializedName("header22")
    private String header22;
    @SerializedName("saldo")
    private String saldo;
    @SerializedName("nama")
    private String nama;
    @SerializedName("produk")
    private String produk;
    @SerializedName("kode_cabang")
    private String kode_cabang;
    @SerializedName("mata_uang")
    private String mata_uang;
    @SerializedName("nasabah")
    private String noRekening;

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public String getCabang() {
        return cabang;
    }

    public void setCabang(String cabang) {
        this.cabang = cabang;
    }

    public String getSaldo_yang_diblokir() {
        return saldo_yang_diblokir;
    }

    public void setSaldo_yang_diblokir(String saldo_yang_diblokir) {
        this.saldo_yang_diblokir = saldo_yang_diblokir;
    }

    public String getNo_rek_cantik() {
        return no_rek_cantik;
    }

    public void setNo_rek_cantik(String no_rek_cantik) {
        this.no_rek_cantik = no_rek_cantik;
    }

    public String getKlp_rekening() {
        return klp_rekening;
    }

    public void setKlp_rekening(String klp_rekening) {
        this.klp_rekening = klp_rekening;
    }

    public String getHeader23() {
        return header23;
    }

    public void setHeader23(String header23) {
        this.header23 = header23;
    }

    public String getHeader24() {
        return header24;
    }

    public void setHeader24(String header24) {
        this.header24 = header24;
    }

    public String getSaldo_tersedia() {
        return saldo_tersedia;
    }

    public void setSaldo_tersedia(String saldo_tersedia) {
        this.saldo_tersedia = saldo_tersedia;
    }

    public String getHeader21() {
        return header21;
    }

    public void setHeader21(String header21) {
        this.header21 = header21;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getHeader22() {
        return header22;
    }

    public void setHeader22(String header22) {
        this.header22 = header22;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
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

    public String getKode_cabang() {
        return kode_cabang;
    }

    public void setKode_cabang(String kode_cabang) {
        this.kode_cabang = kode_cabang;
    }

    public String getMata_uang() {
        return mata_uang;
    }

    public void setMata_uang(String mata_uang) {
        this.mata_uang = mata_uang;
    }


}