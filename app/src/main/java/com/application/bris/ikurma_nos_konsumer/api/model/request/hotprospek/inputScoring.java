package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 27/06/2019.
 */

public class inputScoring {
    @SerializedName("cif")
    private int cif;
    @SerializedName("idAplikasi")
    private int idAplikasi;
    @SerializedName("uid")
    private int uid;
    @SerializedName("kodeLengkap")
    private String kodeLengkap;
    @SerializedName("rpcRatio")
    private Long rpcRatio;
    @SerializedName("ratioAgunan")
    private Long ratioAgunan;
    @SerializedName("currentRatio")
    private Long currentRatio;
    @SerializedName("profitability")
    private Long profitability;
    @SerializedName("reputasiUsaha")
    private Long reputasiUsaha;
    @SerializedName("riwayatDgnBank")
    private Long riwayatDgnBank;
    @SerializedName("pengalamanUsaha")
    private Long pengalamanUsaha;
    @SerializedName("prospekUsaha")
    private Long prospekUsaha;
    @SerializedName("ketSupplier")
    private Long ketSupplier;
    @SerializedName("ketPelanggan")
    private Long ketPelanggan;
    @SerializedName("wilPemasaran")
    private Long wilPemasaran;
    @SerializedName("jenisProduk")
    private Long jenisProduk;
    @SerializedName("jw")
    private Long jw;
    @SerializedName("jenisPby")
    private Long jenisPby;


    public inputScoring(int cif, int idAplikasi, int uid, String kodeLengkap, Long rpcRatio, Long ratioAgunan, Long currentRatio, Long profitability, Long reputasiUsaha, Long riwayatDgnBank, Long pengalamanUsaha, Long prospekUsaha, Long ketSupplier, Long ketPelanggan, Long wilPemasaran, Long jenisProduk, Long jw, Long jenisPby) {
        this.cif = cif;
        this.idAplikasi = idAplikasi;
        this.uid = uid;
        this.kodeLengkap = kodeLengkap;
        this.rpcRatio = rpcRatio;
        this.ratioAgunan = ratioAgunan;
        this.currentRatio = currentRatio;
        this.profitability = profitability;
        this.reputasiUsaha = reputasiUsaha;
        this.riwayatDgnBank = riwayatDgnBank;
        this.pengalamanUsaha = pengalamanUsaha;
        this.prospekUsaha = prospekUsaha;
        this.ketSupplier = ketSupplier;
        this.ketPelanggan = ketPelanggan;
        this.wilPemasaran = wilPemasaran;
        this.jenisProduk = jenisProduk;
        this.jw = jw;
        this.jenisPby = jenisPby;
    }

    public void setCif(int cif) {
        this.cif = cif;
    }

    public void setIdAplikasi(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setKodeLengkap(String kodeLengkap) {
        this.kodeLengkap = kodeLengkap;
    }

    public void setRpcRatio(Long rpcRatio) {
        this.rpcRatio = rpcRatio;
    }

    public void setRatioAgunan(Long ratioAgunan) {
        this.ratioAgunan = ratioAgunan;
    }

    public void setCurrentRatio(Long currentRatio) {
        this.currentRatio = currentRatio;
    }

    public void setProfitability(Long profitability) {
        this.profitability = profitability;
    }

    public void setReputasiUsaha(Long reputasiUsaha) {
        this.reputasiUsaha = reputasiUsaha;
    }

    public void setRiwayatDgnBank(Long riwayatDgnBank) {
        this.riwayatDgnBank = riwayatDgnBank;
    }

    public void setPengalamanUsaha(Long pengalamanUsaha) {
        this.pengalamanUsaha = pengalamanUsaha;
    }

    public void setProspekUsaha(Long prospekUsaha) {
        this.prospekUsaha = prospekUsaha;
    }

    public void setKetSupplier(Long ketSupplier) {
        this.ketSupplier = ketSupplier;
    }

    public void setKetPelanggan(Long ketPelanggan) {
        this.ketPelanggan = ketPelanggan;
    }

    public void setWilPemasaran(Long wilPemasaran) {
        this.wilPemasaran = wilPemasaran;
    }

    public void setJenisProduk(Long jenisProduk) {
        this.jenisProduk = jenisProduk;
    }

    public void setJw(Long jw) {
        this.jw = jw;
    }

    public void setJenisPby(Long jenisPby) {
        this.jenisPby = jenisPby;
    }
}
