package com.application.bris.ikurma_nos_konsumer.model.monitoring;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NasabahMonitoring implements Serializable {
    @SerializedName("NAMA_NASABAH")
    private String namaNasabah;

    @SerializedName("OS")
    private String os;

    @SerializedName("PENCAIRAN")
    private String pencairan;

    @SerializedName("DPK")
    private String dpk;

    @SerializedName("PRODUK")
    private String produk;

    @SerializedName("NAMA_PRODUK")
    private String namaProduk;

    @SerializedName("TGL_JATUH_TEMPO")
    private String tglJtTempo;

    @SerializedName("JANGKA_WAKTU")
    private String jangkaWaktu;

    @SerializedName("PLAFOND_AWAL")
    private String plafondAwal;

    @SerializedName("TGL_REALISASI")
    private String tglRealisasi;

    @SerializedName("KOL")
    private String kol;

    @SerializedName("NO_REK_AGF")
    private String noRekening;

    @SerializedName("NO_TELP")
    private String noTelp;

    @SerializedName("ALAMAT")
    private String alamat;

    @SerializedName("DAY_PASTDUE")
    private String dayPastDue;

    @SerializedName("TANGGAL_PASTDUE")
    private String tanggalPastDue;


    @SerializedName("STATUS")
    private String status;


    @SerializedName("TUNGGAKAN")
    private String tunggakan;

    @SerializedName("NOMINAL_ANGSURAN")
    private String nominalAngsuran;

    @SerializedName("SISA_HARI")
    private String sisaDurasi;

    public String getSisaDurasi() {
        return sisaDurasi;
    }

    public void setSisaDurasi(String sisaDurasi) {
        this.sisaDurasi = sisaDurasi;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getTunggakan() {
        return tunggakan;
    }

    public void setTunggakan(String tunggakan) {
        this.tunggakan = tunggakan;
    }

    public String getNominalAngsuran() {
        return nominalAngsuran;
    }

    public void setNominalAngsuran(String nominalAngsuran) {
        this.nominalAngsuran = nominalAngsuran;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDayPastDue() {
        return dayPastDue;
    }

    public void setDayPastDue(String dayPastDue) {
        this.dayPastDue = dayPastDue;
    }

    public String getTanggalPastDue() {
        return tanggalPastDue;
    }

    public void setTanggalPastDue(String tanggalPastDue) {
        this.tanggalPastDue = tanggalPastDue;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public String getKol() {
        return kol;
    }

    public void setKol(String kol) {
        this.kol = kol;
    }

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getPencairan() {
        return pencairan;
    }

    public void setPencairan(String pencairan) {
        this.pencairan = pencairan;
    }

    public String getDpk() {
        return dpk;
    }

    public void setDpk(String dpk) {
        this.dpk = dpk;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public String getTglJtTempo() {
        return tglJtTempo;
    }

    public void setTglJtTempo(String tglJtTempo) {
        this.tglJtTempo = tglJtTempo;
    }

    public String getJangkaWaktu() {
        return jangkaWaktu;
    }

    public void setJangkaWaktu(String jangkaWaktu) {
        this.jangkaWaktu = jangkaWaktu;
    }

    public String getPlafondAwal() {
        return plafondAwal;
    }

    public void setPlafondAwal(String plafondAwal) {
        this.plafondAwal = plafondAwal;
    }

    public String getTglRealisasi() {
        return tglRealisasi;
    }

    public void setTglRealisasi(String tglRealisasi) {
        this.tglRealisasi = tglRealisasi;
    }
}
