package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 6/23/2019.
 */

public class Rpc {

    @SerializedName("penghasilanLain")
    private Long penghasilanLain;
    @SerializedName("pengeluaranTransportasi")
    private Long pengeluaranTransportasi;
    @SerializedName("rpc")
    private Long rpc;
    @SerializedName("pengeluaranPajak")
    private Long pengeluaranPajak;
    @SerializedName("pendapatanUsaha")
    private Long pendapatanUsaha;
    @SerializedName("rpcRatio")
    private Double rpcRatio;
    @SerializedName("pendapatanBersih")
    private Long pendapatanBersih;
    @SerializedName("angsuranAplikasi")
    private Long angsuranAplikasi;
    @SerializedName("pengeluaranHargaSewa")
    private Long pengeluaranHargaSewa;
    @SerializedName("labaRugi")
    private Long labaRugi;
    @SerializedName("pengeluaranTelpListrik")
    private Long pengeluaranTelpListrik;
    @SerializedName("pengeluaranUsaha")
    private Long pengeluaranUsaha;
    @SerializedName("pengeluaranGajiPegawai")
    private Long pengeluaranGajiPegawai;
    @SerializedName("pengeluaranLainnya")
    private Long pengeluaranLainnya;
    @SerializedName("pengeluaranRumahTangga")
    private Long pengeluaranRumahTangga;
    @SerializedName("angsuranBrisExisting")
    private Long angsuranBrisExisting;
    @SerializedName("pengeluaranHargaPokokPenjualan")
    private Long pengeluaranHargaPokokPenjualan;

    public Rpc(Long penghasilanLain, Long pengeluaranTransportasi, Long rpc, Long pengeluaranPajak, Long pendapatanUsaha, Double rpcRatio, Long pendapatanBersih, Long angsuranAplikasi, Long pengeluaranHargaSewa, Long labaRugi, Long pengeluaranTelpListrik, Long pengeluaranUsaha, Long pengeluaranGajiPegawai, Long pengeluaranLainnya, Long pengeluaranRumahTangga, Long angsuranBrisExisting, Long pengeluaranHargaPokokPenjualan) {
        this.penghasilanLain = penghasilanLain;
        this.pengeluaranTransportasi = pengeluaranTransportasi;
        this.rpc = rpc;
        this.pengeluaranPajak = pengeluaranPajak;
        this.pendapatanUsaha = pendapatanUsaha;
        this.rpcRatio = rpcRatio;
        this.pendapatanBersih = pendapatanBersih;
        this.angsuranAplikasi = angsuranAplikasi;
        this.pengeluaranHargaSewa = pengeluaranHargaSewa;
        this.labaRugi = labaRugi;
        this.pengeluaranTelpListrik = pengeluaranTelpListrik;
        this.pengeluaranUsaha = pengeluaranUsaha;
        this.pengeluaranGajiPegawai = pengeluaranGajiPegawai;
        this.pengeluaranLainnya = pengeluaranLainnya;
        this.pengeluaranRumahTangga = pengeluaranRumahTangga;
        this.angsuranBrisExisting = angsuranBrisExisting;
        this.pengeluaranHargaPokokPenjualan = pengeluaranHargaPokokPenjualan;
    }

    public Long getPenghasilanLain() {
        return penghasilanLain;
    }

    public Long getPengeluaranTransportasi() {
        return pengeluaranTransportasi;
    }

    public Long getRpc() {
        return rpc;
    }

    public Long getPengeluaranPajak() {
        return pengeluaranPajak;
    }

    public Long getPendapatanUsaha() {
        return pendapatanUsaha;
    }

    public Double getRpcRatio() {
        return rpcRatio;
    }

    public Long getPendapatanBersih() {
        return pendapatanBersih;
    }

    public Long getAngsuranAplikasi() {
        return angsuranAplikasi;
    }

    public Long getPengeluaranHargaSewa() {
        return pengeluaranHargaSewa;
    }

    public Long getLabaRugi() {
        return labaRugi;
    }

    public Long getPengeluaranTelpListrik() {
        return pengeluaranTelpListrik;
    }

    public Long getPengeluaranUsaha() {
        return pengeluaranUsaha;
    }

    public Long getPengeluaranGajiPegawai() {
        return pengeluaranGajiPegawai;
    }

    public Long getPengeluaranLainnya() {
        return pengeluaranLainnya;
    }

    public Long getPengeluaranRumahTangga() {
        return pengeluaranRumahTangga;
    }

    public Long getAngsuranBrisExisting() {
        return angsuranBrisExisting;
    }

    public Long getPengeluaranHargaPokokPenjualan() {
        return pengeluaranHargaPokokPenjualan;
    }
}
