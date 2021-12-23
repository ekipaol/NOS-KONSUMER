package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UploadImage;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataIdeb implements Serializable {
    @SerializedName("Nama_Lembaga_Keuangan")
    private String namaLembagaKeuangan;
    @SerializedName("Baki_Debet_Terakhir")
    private String bakiDebet;
    @SerializedName("Kualitas_Pembiayaan")
    private String kualitasPembiayaan;
    @SerializedName("Perkiraan_Angsuran_Bulanan")
    private String perkiraanAngsuranBulanan;
    @SerializedName("Treatment_Pembiayaan")
    private String treatmentPembiayaan;
    @SerializedName("catatan")
    private String catatan;
    @SerializedName("nama")
    private String nama;
    @SerializedName("Fasilitas_Aktif_Id")
    private Long idDokumen;
    @SerializedName("Jenis_Kredit")
    private String jenisKredit;
    @SerializedName("StatusIDEB")
    private String statusIdeb;
    @SerializedName("Dokumen")
    private UploadImage dokumen;


    public String getJenisKredit() {
        return jenisKredit;
    }

    public void setJenisKredit(String jenisKredit) {
        this.jenisKredit = jenisKredit;
    }

    public String getStatusIdeb() {
        return statusIdeb;
    }

    public void setStatusIdeb(String statusIdeb) {
        this.statusIdeb = statusIdeb;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public UploadImage getDokumen() {
        return dokumen;
    }

    public void setDokumen(UploadImage dokumen) {
        this.dokumen = dokumen;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getNamaLembagaKeuangan() {
        return namaLembagaKeuangan;
    }

    public void setNamaLembagaKeuangan(String namaLembagaKeuangan) {
        this.namaLembagaKeuangan = namaLembagaKeuangan;
    }

    public String getBakiDebet() {
        return bakiDebet;
    }

    public void setBakiDebet(String bakiDebet) {
        this.bakiDebet = bakiDebet;
    }

    public String getKualitasPembiayaan() {
        return kualitasPembiayaan;
    }

    public void setKualitasPembiayaan(String kualitasPembiayaan) {
        this.kualitasPembiayaan = kualitasPembiayaan;
    }

    public String getPerkiraanAngsuranBulanan() {
        return perkiraanAngsuranBulanan;
    }

    public void setPerkiraanAngsuranBulanan(String perkiraanAngsuranBulanan) {
        this.perkiraanAngsuranBulanan = perkiraanAngsuranBulanan;
    }

    public String getTreatmentPembiayaan() {
        return treatmentPembiayaan;
    }

    public void setTreatmentPembiayaan(String treatmentPembiayaan) {
        this.treatmentPembiayaan = treatmentPembiayaan;
    }

    public Long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(Long idDokumen) {
        this.idDokumen = idDokumen;
    }
}
