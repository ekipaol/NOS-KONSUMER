package com.application.bris.ikurma_nos_konsumer.database.pojo;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FlagAplikasiPojo extends RealmObject {
    @PrimaryKey
    private Long idAplikasi;
    private Boolean flagD1DataPembiayaan=false;
    private Boolean flagD1DataDedupe=false;;
    private Boolean flagD1DataNasabah=false;;
    private Boolean flagD1DataDataMarketing=false;;
    private Boolean flagD3Kalkulator=false;;
    private Boolean flagD3Jaminan=false;;
    private Boolean flagD3Pendapatan=false;;
    private Boolean flagD3Ideb=false;;
    private Boolean flagD3Kewajiban=false;;

    public Long getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(Long idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public Boolean getFlagD1DataPembiayaan() {
        return flagD1DataPembiayaan;
    }

    public void setFlagD1DataPembiayaan(Boolean flagD1DataPembiayaan) {
        this.flagD1DataPembiayaan = flagD1DataPembiayaan;
    }

    public Boolean getFlagD1DataDedupe() {
        return flagD1DataDedupe;
    }

    public void setFlagD1DataDedupe(Boolean flagD1DataDedupe) {
        this.flagD1DataDedupe = flagD1DataDedupe;
    }

    public Boolean getFlagD1DataNasabah() {
        return flagD1DataNasabah;
    }

    public void setFlagD1DataNasabah(Boolean flagD1DataNasabah) {
        this.flagD1DataNasabah = flagD1DataNasabah;
    }

    public Boolean getFlagD1DataDataMarketing() {
        return flagD1DataDataMarketing;
    }

    public void setFlagD1DataDataMarketing(Boolean flagD1DataDataMarketing) {
        this.flagD1DataDataMarketing = flagD1DataDataMarketing;
    }

    public Boolean getFlagD3Kalkulator() {
        return flagD3Kalkulator;
    }

    public void setFlagD3Kalkulator(Boolean flagD3Kalkulator) {
        this.flagD3Kalkulator = flagD3Kalkulator;
    }

    public Boolean getFlagD3Jaminan() {
        return flagD3Jaminan;
    }

    public void setFlagD3Jaminan(Boolean flagD3Jaminan) {
        this.flagD3Jaminan = flagD3Jaminan;
    }

    public Boolean getFlagD3Pendapatan() {
        return flagD3Pendapatan;
    }

    public void setFlagD3Pendapatan(Boolean flagD3Pendapatan) {
        this.flagD3Pendapatan = flagD3Pendapatan;
    }

    public Boolean getFlagD3Ideb() {
        return flagD3Ideb;
    }

    public void setFlagD3Ideb(Boolean flagD3Ideb) {
        this.flagD3Ideb = flagD3Ideb;
    }

    public Boolean getFlagD3Kewajiban() {
        return flagD3Kewajiban;
    }

    public void setFlagD3Kewajiban(Boolean flagD3Kewajiban) {
        this.flagD3Kewajiban = flagD3Kewajiban;
    }
}
