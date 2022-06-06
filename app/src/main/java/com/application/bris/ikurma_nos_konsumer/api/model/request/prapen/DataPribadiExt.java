package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPribadiExt {

    @SerializedName("Jumlah_Tangungan")
    @Expose
    private Integer JumlahTangungan;
    @SerializedName("Status_Kepemilikan")
    @Expose
    private Long StatusKepemilikan;
    @SerializedName("Status_Kepemilikan_Text")
    @Expose
    private String StatusKepemilikanText;
    @SerializedName("Lama_Tempat_Domisili")
    @Expose
    private Integer LamaTempatDomisili;
    @SerializedName("Lama_Menjadi_Nasabah_Bank")
    @Expose
    private Integer LamaMenjadiNasabahBank;

    public Integer getJumlahTangungan() {
        return JumlahTangungan;
    }

    public void setJumlahTangungan(Integer jumlahTangungan) {
        JumlahTangungan = jumlahTangungan;
    }

    public Long getStatusKepemilikan() {
        return StatusKepemilikan;
    }

    public void setStatusKepemilikan(Long statusKepemilikan) {
        StatusKepemilikan = statusKepemilikan;
    }

    public Integer getLamaTempatDomisili() {
        return LamaTempatDomisili;
    }

    public void setLamaTempatDomisili(Integer lamaTempatDomisili) {
        LamaTempatDomisili = lamaTempatDomisili;
    }

    public Integer getLamaMenjadiNasabahBank() {
        return LamaMenjadiNasabahBank;
    }

    public void setLamaMenjadiNasabahBank(Integer lamaMenjadiNasabahBank) {
        LamaMenjadiNasabahBank = lamaMenjadiNasabahBank;
    }

    public String getStatusKepemilikanText() {
        return StatusKepemilikanText;
    }

    public void setStatusKepemilikanText(String statusKepemilikanText) {
        StatusKepemilikanText = statusKepemilikanText;
    }
}
