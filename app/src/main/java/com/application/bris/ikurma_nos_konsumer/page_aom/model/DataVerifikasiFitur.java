package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class DataVerifikasiFitur {
    @SerializedName("namaFitur")
    private String namaFitur;
    @SerializedName("hasilVerifikasiEngine")
    private String hasilVerifikasiEngine;
    @SerializedName("hasilVerifikasiVerif")
    private String hasilVerifikasiVerif;
    @SerializedName("ketentuan")
    private String ketentuan;
    @SerializedName("hasil")
    private String hasil;
    @SerializedName("Catatan")
    private String Catatan;
    @SerializedName("idDokumen")
    private String idDokumen;

    public String getKetentuan() {
        return ketentuan;
    }

    public void setKetentuan(String ketentuan) {
        this.ketentuan = ketentuan;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public String getNamaFitur() {
        return namaFitur;
    }

    public void setNamaFitur(String namaFitur) {
        this.namaFitur = namaFitur;
    }

    public String getHasilVerifikasiEngine() {
        return hasilVerifikasiEngine;
    }

    public void setHasilVerifikasiEngine(String hasilVerifikasiEngine) {
        this.hasilVerifikasiEngine = hasilVerifikasiEngine;
    }

    public String getHasilVerifikasiVerif() {
        return hasilVerifikasiVerif;
    }

    public void setHasilVerifikasiVerif(String hasilVerifikasiVerif) {
        this.hasilVerifikasiVerif = hasilVerifikasiVerif;
    }

    public String getCatatan() {
        return Catatan;
    }

    public void setCatatan(String catatan) {
        Catatan = catatan;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }
}
