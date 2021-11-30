package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataMmqLainnya {
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
    @SerializedName("Jenis_Aset")
    @Expose
    private String jenisAset;
    @SerializedName("Dokumen_Kepemilikan")
    @Expose
    private String dokumenKepemilikan;
    @SerializedName("Alamat")
    @Expose
    private String alamat;
    @SerializedName("Kondisi")
    @Expose
    private String kondisi;
    @SerializedName("Sumber_Data")
    @Expose
    private String sumberData;
    @SerializedName("Nilai_Aset")
    @Expose
    private Double nilaiAset;

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

    public String getJenisAset() {
        return jenisAset;
    }

    public void setJenisAset(String jenisAset) {
        this.jenisAset = jenisAset;
    }

    public String getDokumenKepemilikan() {
        return dokumenKepemilikan;
    }

    public void setDokumenKepemilikan(String dokumenKepemilikan) {
        this.dokumenKepemilikan = dokumenKepemilikan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getSumberData() {
        return sumberData;
    }

    public void setSumberData(String sumberData) {
        this.sumberData = sumberData;
    }

    public Double getNilaiAset() {
        return nilaiAset;
    }

    public void setNilaiAset(Double nilaiAset) {
        this.nilaiAset = nilaiAset;
    }
}
