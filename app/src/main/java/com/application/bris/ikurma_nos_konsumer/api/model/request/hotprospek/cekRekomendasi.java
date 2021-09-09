package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 24/06/2019.
 */

public class cekRekomendasi {

    @SerializedName("cif")
    private String cif;
    @SerializedName("uid")
    private Integer uid;
    @SerializedName("idAplikasi")
    private Integer idAplikasi;
    @SerializedName("marginFlat")
    private Double marginFlat;
    @SerializedName("rekomendasiNilaiPbyModalKerja")
    private Integer rekomendasiNilaiPbyModalKerja;
    @SerializedName("rekomendasiNilaiPbyInvestasi")
    private Integer rekomendasiNilaiPbyInvestasi;
    @SerializedName("rekomendasiNilaiPbyKonsumtif")
    private Integer rekomendasiNilaiPbyKonsumtif;
    @SerializedName("rekomendasiNilaiPbyTakeover")
    private Integer rekomendasiNilaiPbyTakeover;
    @SerializedName("jangkaWaktuPbyModalKerja")
    private Integer jangkaWaktuPbyModalKerja;
    @SerializedName("jangkaWaktuPbyInvestasi")
    private Integer jangkaWaktuPbyInvestasi;
    @SerializedName("jangkaWaktuPbyKonsumtif")
    private Integer jangkaWaktuPbyKonsumtif;
    @SerializedName("jangkaWaktuPbyTakeover")
    private Integer jangkaWaktuPbyTakeover;
    @SerializedName("sisaPenghasilan")
    private Integer sisaPenghasilan;
    @SerializedName("angsuranPinjaman")
    private Integer angsuranPinjaman;
    @SerializedName("totalRekomendasi")
    private Integer totalRekomendasi;
    @SerializedName("totalEksposure")
    private Integer totalEksposure;

    public cekRekomendasi(String cif, Integer uid, Integer idAplikasi, Double marginFlat, Integer rekomendasiNilaiPbyModalKerja, Integer rekomendasiNilaiPbyInvestasi, Integer rekomendasiNilaiPbyKonsumtif, Integer rekomendasiNilaiPbyTakeover, Integer jangkaWaktuPbyModalKerja, Integer jangkaWaktuPbyInvestasi, Integer jangkaWaktuPbyKonsumtif, Integer jangkaWaktuPbyTakeover, Integer sisaPenghasilan, Integer angsuranPinjaman, Integer totalRekomendasi, Integer totalEksposure) {
        this.cif = cif;
        this.uid = uid;
        this.idAplikasi = idAplikasi;
        this.marginFlat = marginFlat;
        this.rekomendasiNilaiPbyModalKerja = rekomendasiNilaiPbyModalKerja;
        this.rekomendasiNilaiPbyInvestasi = rekomendasiNilaiPbyInvestasi;
        this.rekomendasiNilaiPbyKonsumtif = rekomendasiNilaiPbyKonsumtif;
        this.rekomendasiNilaiPbyTakeover = rekomendasiNilaiPbyTakeover;
        this.jangkaWaktuPbyModalKerja = jangkaWaktuPbyModalKerja;
        this.jangkaWaktuPbyInvestasi = jangkaWaktuPbyInvestasi;
        this.jangkaWaktuPbyKonsumtif = jangkaWaktuPbyKonsumtif;
        this.jangkaWaktuPbyTakeover = jangkaWaktuPbyTakeover;
        this.sisaPenghasilan = sisaPenghasilan;
        this.angsuranPinjaman = angsuranPinjaman;
        this.totalRekomendasi = totalRekomendasi;
        this.totalEksposure = totalEksposure;
    }


    public void setCif(String cif) {
        this.cif = cif;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setIdAplikasi(Integer idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public void setMarginFlat(Double marginFlat) {
        this.marginFlat = marginFlat;
    }

    public void setRekomendasiNilaiPbyModalKerja(Integer rekomendasiNilaiPbyModalKerja) {
        this.rekomendasiNilaiPbyModalKerja = rekomendasiNilaiPbyModalKerja;
    }

    public void setRekomendasiNilaiPbyInvestasi(Integer rekomendasiNilaiPbyInvestasi) {
        this.rekomendasiNilaiPbyInvestasi = rekomendasiNilaiPbyInvestasi;
    }

    public void setRekomendasiNilaiPbyKonsumtif(Integer rekomendasiNilaiPbyKonsumtif) {
        this.rekomendasiNilaiPbyKonsumtif = rekomendasiNilaiPbyKonsumtif;
    }

    public void setRekomendasiNilaiPbyTakeover(Integer rekomendasiNilaiPbyTakeover) {
        this.rekomendasiNilaiPbyTakeover = rekomendasiNilaiPbyTakeover;
    }

    public void setJangkaWaktuPbyModalKerja(Integer jangkaWaktuPbyModalKerja) {
        this.jangkaWaktuPbyModalKerja = jangkaWaktuPbyModalKerja;
    }

    public void setJangkaWaktuPbyInvestasi(Integer jangkaWaktuPbyInvestasi) {
        this.jangkaWaktuPbyInvestasi = jangkaWaktuPbyInvestasi;
    }

    public void setJangkaWaktuPbyKonsumtif(Integer jangkaWaktuPbyKonsumtif) {
        this.jangkaWaktuPbyKonsumtif = jangkaWaktuPbyKonsumtif;
    }

    public void setJangkaWaktuPbyTakeover(Integer jangkaWaktuPbyTakeover) {
        this.jangkaWaktuPbyTakeover = jangkaWaktuPbyTakeover;
    }

    public void setSisaPenghasilan(Integer sisaPenghasilan) {
        this.sisaPenghasilan = sisaPenghasilan;
    }

    public void setAngsuranPinjaman(Integer angsuranPinjaman) {
        this.angsuranPinjaman = angsuranPinjaman;
    }

    public void setTotalRekomendasi(Integer totalRekomendasi) {
        this.totalRekomendasi = totalRekomendasi;
    }

    public void setTotalEksposure(Integer totalEksposure) {
        this.totalEksposure = totalEksposure;
    }
}
