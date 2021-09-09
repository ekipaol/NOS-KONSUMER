package com.application.bris.ikurma_nos_konsumer.model.monitoring;

import com.google.gson.annotations.SerializedName;

public class RiwayatMutasi {
    @SerializedName("kredit")
    private String kredit;
    @SerializedName("debit")
    private String debit;
    @SerializedName("saldo_akhir")
    private String saldo_akhir;
    @SerializedName("tanda")
    private String status;
    @SerializedName("tgl_posting")
    private String tgl_posting;
    @SerializedName("tgl_valuta")
    private String tgl_valuta;
    @SerializedName("keterangan")
    private String keterangan;
    @SerializedName("no_referensi")
    private String no_referensi;

    public String getKredit() {
        return kredit;
    }

    public void setKredit(String kredit) {
        this.kredit = kredit;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getSaldo_akhir() {
        return saldo_akhir;
    }

    public void setSaldo_akhir(String saldo_akhir) {
        this.saldo_akhir = saldo_akhir;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTgl_posting() {
        return tgl_posting;
    }

    public void setTgl_posting(String tgl_posting) {
        this.tgl_posting = tgl_posting;
    }

    public String getTgl_valuta() {
        return tgl_valuta;
    }

    public void setTgl_valuta(String tgl_valuta) {
        this.tgl_valuta = tgl_valuta;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNo_referensi() {
        return no_referensi;
    }

    public void setNo_referensi(String no_referensi) {
        this.no_referensi = no_referensi;
    }
}
