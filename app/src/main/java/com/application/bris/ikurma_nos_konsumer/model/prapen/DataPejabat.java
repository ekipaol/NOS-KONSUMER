package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPejabat {
    @SerializedName("Id")
    @Expose
    private Long id;
    @SerializedName("Application_Id")
    @Expose
    private Long applicationId;
    @SerializedName("UID_Pejabat_Penandatangan")
    @Expose
    private String uIDPejabatPenandatangan;
    @SerializedName("Nama_Pejabat_Penandatangan")
    @Expose
    private String namaPejabatPenandatangan;
    @SerializedName("Nomor_SKPP_Pejabat")
    @Expose
    private String nomorSKPPPejabat;
    @SerializedName("Tanggal_Surat_Keputusan")
    @Expose
    private String tanggalSuratKeputusan;
    @SerializedName("Nomor_KUA")
    @Expose
    private String nomorKUA;
    @SerializedName("Tanggal_Surat_Kuasa")
    @Expose
    private String tanggalSuratKuasa;
    @SerializedName("Kota_Penandatanganan_Dokumen")
    @Expose
    private String kotaPenandatangananDokumen;
    @SerializedName("No_Rekening_BSI")
    @Expose
    private String noRekeningBSI;
    @SerializedName("Atas_Nama_Rekening")
    @Expose
    private String atasNamaRekening;
    @SerializedName("Nomor_CIF")
    @Expose
    private String nomorCIF;
    @SerializedName("Akad_Pembiayaan")
    @Expose
    private Long akadPembiayaan;
    @SerializedName("Rencana_Penandatanganan_Akad")
    @Expose
    private String rencanaPenandatangananAkad;
    @SerializedName("Bulan_Angsuran_Pertama")
    @Expose
    private String bulanAngsuranPertama;
    @SerializedName("Tanggal_Penilaian")
    @Expose
    private String tanggalPenilaian;
    @SerializedName("Nama_Penilai")
    @Expose
    private String namaPenilai;
    @SerializedName("Kota_Penandatanganan_Laporan")
    @Expose
    private String kotaPenandatangananLaporan;
    @SerializedName("Cabang")
    @Expose
    private String cabang;

    public String getCabang() {
        return cabang;
    }

    public void setCabang(String cabang) {
        this.cabang = cabang;
    }

    public String getNamaPejabatPenandatangan() {
        return namaPejabatPenandatangan;
    }

    public void setNamaPejabatPenandatangan(String namaPejabatPenandatangan) {
        this.namaPejabatPenandatangan = namaPejabatPenandatangan;
    }

    public String getNoRekeningBSI() {
        return noRekeningBSI;
    }

    public void setNoRekeningBSI(String noRekeningBSI) {
        this.noRekeningBSI = noRekeningBSI;
    }

    public String getAtasNamaRekening() {
        return atasNamaRekening;
    }

    public void setAtasNamaRekening(String atasNamaRekening) {
        this.atasNamaRekening = atasNamaRekening;
    }

    public String getNomorCIF() {
        return nomorCIF;
    }

    public void setNomorCIF(String nomorCIF) {
        this.nomorCIF = nomorCIF;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getuIDPejabatPenandatangan() {
        return uIDPejabatPenandatangan;
    }

    public void setuIDPejabatPenandatangan(String uIDPejabatPenandatangan) {
        this.uIDPejabatPenandatangan = uIDPejabatPenandatangan;
    }

    public String getNomorSKPPPejabat() {
        return nomorSKPPPejabat;
    }

    public void setNomorSKPPPejabat(String nomorSKPPPejabat) {
        this.nomorSKPPPejabat = nomorSKPPPejabat;
    }

    public String getTanggalSuratKeputusan() {
        return tanggalSuratKeputusan;
    }

    public void setTanggalSuratKeputusan(String tanggalSuratKeputusan) {
        this.tanggalSuratKeputusan = tanggalSuratKeputusan;
    }

    public String getNomorKUA() {
        return nomorKUA;
    }

    public void setNomorKUA(String nomorKUA) {
        this.nomorKUA = nomorKUA;
    }

    public String getTanggalSuratKuasa() {
        return tanggalSuratKuasa;
    }

    public void setTanggalSuratKuasa(String tanggalSuratKuasa) {
        this.tanggalSuratKuasa = tanggalSuratKuasa;
    }

    public String getKotaPenandatangananDokumen() {
        return kotaPenandatangananDokumen;
    }

    public void setKotaPenandatangananDokumen(String kotaPenandatangananDokumen) {
        this.kotaPenandatangananDokumen = kotaPenandatangananDokumen;
    }

    public Long getAkadPembiayaan() {
        return akadPembiayaan;
    }

    public void setAkadPembiayaan(Long akadPembiayaan) {
        this.akadPembiayaan = akadPembiayaan;
    }

    public String getRencanaPenandatangananAkad() {
        return rencanaPenandatangananAkad;
    }

    public void setRencanaPenandatangananAkad(String rencanaPenandatangananAkad) {
        this.rencanaPenandatangananAkad = rencanaPenandatangananAkad;
    }

    public String getBulanAngsuranPertama() {
        return bulanAngsuranPertama;
    }

    public void setBulanAngsuranPertama(String bulanAngsuranPertama) {
        this.bulanAngsuranPertama = bulanAngsuranPertama;
    }


    public String getTanggalPenilaian() {
        return tanggalPenilaian;
    }

    public void setTanggalPenilaian(String tanggalPenilaian) {
        this.tanggalPenilaian = tanggalPenilaian;
    }

    public String getNamaPenilai() {
        return namaPenilai;
    }

    public void setNamaPenilai(String namaPenilai) {
        this.namaPenilai = namaPenilai;
    }

    public String getKotaPenandatangananLaporan() {
        return kotaPenandatangananLaporan;
    }

    public void setKotaPenandatangananLaporan(String kotaPenandatangananLaporan) {
        this.kotaPenandatangananLaporan = kotaPenandatangananLaporan;
    }
}
