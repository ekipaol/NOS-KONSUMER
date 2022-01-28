package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateDataOjkBi {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("ApplicationId")
    private int ApplicationId;
    @SerializedName("UID")
    private String UID;
    @SerializedName("Pendidikan_Terakhir")
    @Expose
    private String pendidikanTerakhir;
    @SerializedName("Kode_Pekerjaan")
    @Expose
    private String kodePekerjaan;
    @SerializedName("Kode_Bidang_Usaha_Tempat_Ker")
    @Expose
    private String kodeBidangUsahaTempatKer;
    @SerializedName("Alamat_Tempat_Bekerja")
    @Expose
    private String alamatTempatBekerja;
    @SerializedName("Jumlah_Tanggunan_Nasabah")
    @Expose
    private String jumlahTanggunanNasabah;
    @SerializedName("Kode_Jenis_Penggunaan")
    @Expose
    private String kodeJenisPenggunaan;
    @SerializedName("Kode_Sektor_Ekonomi")
    @Expose
    private String kodeSektorEkonomi;
    @SerializedName("Deskripsi_Bidang_Usaha_Tempat_Kerja")
    @Expose
    private String deskripsiBidangUsahaTempatKerja;
    @SerializedName("Deskripsi_Pekerjaan")
    @Expose
    private String deskripsiPekerjaan;
    @SerializedName("Deskripsi_Sektor_Ekonomi")
    @Expose
    private String deskripsiSektorEkonomi;
    @SerializedName("Deskripsi_Jenis_Penggunaan")
    @Expose
    private String deskripsiJenisPenggunaan;

    public String getDeskripsiBidangUsahaTempatKerja() {
        return deskripsiBidangUsahaTempatKerja;
    }

    public void setDeskripsiBidangUsahaTempatKerja(String deskripsiBidangUsahaTempatKerja) {
        this.deskripsiBidangUsahaTempatKerja = deskripsiBidangUsahaTempatKerja;
    }

    public String getDeskripsiPekerjaan() {
        return deskripsiPekerjaan;
    }

    public void setDeskripsiPekerjaan(String deskripsiPekerjaan) {
        this.deskripsiPekerjaan = deskripsiPekerjaan;
    }

    public String getDeskripsiSektorEkonomi() {
        return deskripsiSektorEkonomi;
    }

    public void setDeskripsiSektorEkonomi(String deskripsiSektorEkonomi) {
        this.deskripsiSektorEkonomi = deskripsiSektorEkonomi;
    }

    public String getDeskripsiJenisPenggunaan() {
        return deskripsiJenisPenggunaan;
    }

    public void setDeskripsiJenisPenggunaan(String deskripsiJenisPenggunaan) {
        this.deskripsiJenisPenggunaan = deskripsiJenisPenggunaan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setApplicationId(int ApplicationId) {
        this.ApplicationId = ApplicationId;
    }

    public int getApplicationId() {
        return ApplicationId;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUID(String UID) {
        return UID;
    }

    public String getPendidikanTerakhir() {
        return pendidikanTerakhir;
    }

    public void setPendidikanTerakhir(String pendidikanTerakhir) {
        this.pendidikanTerakhir = pendidikanTerakhir;
    }

    public String getKodePekerjaan() {
        return kodePekerjaan;
    }

    public void setKodePekerjaan(String kodePekerjaan) {
        this.kodePekerjaan = kodePekerjaan;
    }

    public String getKodeBidangUsahaTempatKer() {
        return kodeBidangUsahaTempatKer;
    }

    public void setKodeBidangUsahaTempatKer(String kodeBidangUsahaTempatKer) {
        this.kodeBidangUsahaTempatKer = kodeBidangUsahaTempatKer;
    }

    public String getAlamatTempatBekerja() {
        return alamatTempatBekerja;
    }

    public void setAlamatTempatBekerja(String alamatTempatBekerja) {
        this.alamatTempatBekerja = alamatTempatBekerja;
    }

    public String getJumlahTanggunanNasabah() {
        return jumlahTanggunanNasabah;
    }

    public void setJumlahTanggunanNasabah(String jumlahTanggunanNasabah) {
        this.jumlahTanggunanNasabah = jumlahTanggunanNasabah;
    }

    public String getKodeJenisPenggunaan() {
        return kodeJenisPenggunaan;
    }

    public void setKodeJenisPenggunaan(String kodeJenisPenggunaan) {
        this.kodeJenisPenggunaan = kodeJenisPenggunaan;
    }

    public String getKodeSektorEkonomi() {
        return kodeSektorEkonomi;
    }

    public void setKodeSektorEkonomi(String kodeSektorEkonomi) {
        this.kodeSektorEkonomi = kodeSektorEkonomi;
    }
}

