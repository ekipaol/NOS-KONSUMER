package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataMmqKendaraan {
    @SerializedName("idAplikasi")
    @Expose
    private Long idAplikasi;
    @SerializedName("Rencana_TandaTangan_Akad")
    @Expose
    private String rencanaTandaTanganAkad;
    @SerializedName("Bulan_Angsuran_Pertama")
    @Expose
    private String bulanAngsuranPertama;
    @SerializedName("No_Akta_Nikah")
    @Expose
    private String noAktaNikah;
    @SerializedName("Tanggal_Akta_Nikah")
    @Expose
    private String tanggalAktaNikah;
    @SerializedName("Tanggal_Penilaian")
    @Expose
    private String tanggalPenilaian;
    @SerializedName("Nama_Penilai")
    @Expose
    private String namaPenilai;
    @SerializedName("Kota_Penandatanganan_Laporan")
    @Expose
    private String kotaPenandatangananLaporan;
    @SerializedName("Kepemilikan_Aset")
    @Expose
    private String kepemilikanAset;
    @SerializedName("Jenis_Kendaraan")
    @Expose
    private String jenisKendaraan;
    @SerializedName("Dokumen_Kepemilikan")
    @Expose
    private String dokumenKepemilikan;
    @SerializedName("Nomor_Dokumen")
    @Expose
    private String nomorDokumen;
    @SerializedName("Nomor_Plat")
    @Expose
    private String nomorPlat;
    @SerializedName("Alamat")
    @Expose
    private String alamat;
    @SerializedName("Merek")
    @Expose
    private String merek;
    @SerializedName("Tipe")
    @Expose
    private String tipe;
    @SerializedName("Tahun_Pembuatan")
    @Expose
    private Integer tahunPembuatan;
    @SerializedName("Kondisi_Aset")
    @Expose
    private String kondisiAset;
    @SerializedName("Sumber_Data")
    @Expose
    private String sumberData;
    @SerializedName("Harga_Kendaraan")
    @Expose
    private Double hargaKendaraan;

    public Long getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(Long idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public String getRencanaTandaTanganAkad() {
        return rencanaTandaTanganAkad;
    }

    public void setRencanaTandaTanganAkad(String rencanaTandaTanganAkad) {
        this.rencanaTandaTanganAkad = rencanaTandaTanganAkad;
    }

    public String getBulanAngsuranPertama() {
        return bulanAngsuranPertama;
    }

    public void setBulanAngsuranPertama(String bulanAngsuranPertama) {
        this.bulanAngsuranPertama = bulanAngsuranPertama;
    }

    public String getNoAktaNikah() {
        return noAktaNikah;
    }

    public void setNoAktaNikah(String noAktaNikah) {
        this.noAktaNikah = noAktaNikah;
    }

    public String getTanggalAktaNikah() {
        return tanggalAktaNikah;
    }

    public void setTanggalAktaNikah(String tanggalAktaNikah) {
        this.tanggalAktaNikah = tanggalAktaNikah;
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

    public String getKepemilikanAset() {
        return kepemilikanAset;
    }

    public void setKepemilikanAset(String kepemilikanAset) {
        this.kepemilikanAset = kepemilikanAset;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    public String getDokumenKepemilikan() {
        return dokumenKepemilikan;
    }

    public void setDokumenKepemilikan(String dokumenKepemilikan) {
        this.dokumenKepemilikan = dokumenKepemilikan;
    }

    public String getNomorDokumen() {
        return nomorDokumen;
    }

    public void setNomorDokumen(String nomorDokumen) {
        this.nomorDokumen = nomorDokumen;
    }

    public String getNomorPlat() {
        return nomorPlat;
    }

    public void setNomorPlat(String nomorPlat) {
        this.nomorPlat = nomorPlat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public Integer getTahunPembuatan() {
        return tahunPembuatan;
    }

    public void setTahunPembuatan(Integer tahunPembuatan) {
        this.tahunPembuatan = tahunPembuatan;
    }

    public String getKondisiAset() {
        return kondisiAset;
    }

    public void setKondisiAset(String kondisiAset) {
        this.kondisiAset = kondisiAset;
    }

    public String getSumberData() {
        return sumberData;
    }

    public void setSumberData(String sumberData) {
        this.sumberData = sumberData;
    }

    public Double getHargaKendaraan() {
        return hargaKendaraan;
    }

    public void setHargaKendaraan(Double hargaKendaraan) {
        this.hargaKendaraan = hargaKendaraan;
    }
}
