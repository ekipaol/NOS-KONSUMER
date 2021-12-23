package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UploadImage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataVerifikasiIdeb {
    @SerializedName("Fasilitas_Aktif_Id")
    @Expose
    private Long fasilitasAktifId;
    @SerializedName("Nama_Lembaga_Keuangan")
    @Expose
    private String namaLembagaKeuangan;
    @SerializedName("Baki_Debet_Terakhir")
    @Expose
    private String bakiDebetTerakhir;
    @SerializedName("Kualitas_Pembiayaan")
    @Expose
    private String kualitasPembiayaan;
    @SerializedName("Perkiraan_Angsuran_Bulanan")
    @Expose
    private String perkiraanAngsuranBulanan;
    @SerializedName("Treatment_Pembiayaan")
    @Expose
    private String treatmentPembiayaan;
    @SerializedName("Catatan_BU_untuk_Verifikator")
    @Expose
    private String catatanBUUntukVerifikator;
    @SerializedName("HasilVerifikasi")
    @Expose
    private String treatmentFasilitas;
    @SerializedName("AngsuranHasilVerifikasi")
    @Expose
    private String angsuranVerifikasi;
    @SerializedName("Jenis_Kredit")
    private String jenisKredit;
    @SerializedName("StatusIDEB")
    private String statusIdeb;
    @SerializedName("Dokumen")
    @Expose
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

    public String getAngsuranVerifikasi() {
        return angsuranVerifikasi;
    }

    public void setAngsuranVerifikasi(String angsuranVerifikasi) {
        this.angsuranVerifikasi = angsuranVerifikasi;
    }

    public UploadImage getDokumen() {
        return dokumen;
    }

    public void setDokumen(UploadImage dokumen) {
        this.dokumen = dokumen;
    }

    public String getTreatmentFasilitas() {
        return treatmentFasilitas;
    }

    public void setTreatmentFasilitas(String treatmentFasilitas) {
        this.treatmentFasilitas = treatmentFasilitas;
    }

    public Long getFasilitasAktifId() {
        return fasilitasAktifId;
    }

    public void setFasilitasAktifId(Long fasilitasAktifId) {
        this.fasilitasAktifId = fasilitasAktifId;
    }

    public String getNamaLembagaKeuangan() {
        return namaLembagaKeuangan;
    }

    public void setNamaLembagaKeuangan(String namaLembagaKeuangan) {
        this.namaLembagaKeuangan = namaLembagaKeuangan;
    }

    public String getBakiDebetTerakhir() {
        return bakiDebetTerakhir;
    }

    public void setBakiDebetTerakhir(String bakiDebetTerakhir) {
        this.bakiDebetTerakhir = bakiDebetTerakhir;
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

    public String getCatatanBUUntukVerifikator() {
        return catatanBUUntukVerifikator;
    }

    public void setCatatanBUUntukVerifikator(String catatanBUUntukVerifikator) {
        this.catatanBUUntukVerifikator = catatanBUUntukVerifikator;
    }
}
