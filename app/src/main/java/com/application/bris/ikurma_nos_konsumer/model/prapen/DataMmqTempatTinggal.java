package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataMmqTempatTinggal {
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
    @SerializedName("Jenis_Dokumen")
    @Expose
    private String jenisDokumen;
    @SerializedName("Nomor_Dokumen")
    @Expose
    private String nomorDokumen;
    @SerializedName("Nomor_IMB")
    @Expose
    private String nomorIMB;
    @SerializedName("Alamat")
    @Expose
    private String alamat;
    @SerializedName("Luas_Aset_Tanah")
    @Expose
    private Double luasAsetTanah;
    @SerializedName("Harga_Aset_Tanah")
    @Expose
    private Double hargaAsetTanah;
    @SerializedName("Total_Nilai_Tanah")
    @Expose
    private Double totalNilaiTanah;
    @SerializedName("Luas_Aset_Bangunan")
    @Expose
    private Double luasAsetBangunan;
    @SerializedName("Harga_Aset_Bangunan")
    @Expose
    private Double hargaAsetBangunan;
    @SerializedName("Total_Nilai_Bangunan")
    @Expose
    private Double totalNilaiBangunan;
    @SerializedName("Kondisi_Aset")
    @Expose
    private String kondisiAset;
    @SerializedName("Sumber_Data")
    @Expose
    private String sumberData;
    @SerializedName("Total_Nilai_Aset")
    @Expose
    private Double totalNilaiAset;

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

    public String getJenisDokumen() {
        return jenisDokumen;
    }

    public void setJenisDokumen(String jenisDokumen) {
        this.jenisDokumen = jenisDokumen;
    }

    public String getNomorDokumen() {
        return nomorDokumen;
    }

    public void setNomorDokumen(String nomorDokumen) {
        this.nomorDokumen = nomorDokumen;
    }

    public String getNomorIMB() {
        return nomorIMB;
    }

    public void setNomorIMB(String nomorIMB) {
        this.nomorIMB = nomorIMB;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Double getLuasAsetTanah() {
        return luasAsetTanah;
    }

    public void setLuasAsetTanah(Double luasAsetTanah) {
        this.luasAsetTanah = luasAsetTanah;
    }

    public Double getHargaAsetTanah() {
        return hargaAsetTanah;
    }

    public void setHargaAsetTanah(Double hargaAsetTanah) {
        this.hargaAsetTanah = hargaAsetTanah;
    }

    public Double getTotalNilaiTanah() {
        return totalNilaiTanah;
    }

    public void setTotalNilaiTanah(Double totalNilaiTanah) {
        this.totalNilaiTanah = totalNilaiTanah;
    }

    public Double getLuasAsetBangunan() {
        return luasAsetBangunan;
    }

    public void setLuasAsetBangunan(Double luasAsetBangunan) {
        this.luasAsetBangunan = luasAsetBangunan;
    }

    public Double getHargaAsetBangunan() {
        return hargaAsetBangunan;
    }

    public void setHargaAsetBangunan(Double hargaAsetBangunan) {
        this.hargaAsetBangunan = hargaAsetBangunan;
    }

    public Double getTotalNilaiBangunan() {
        return totalNilaiBangunan;
    }

    public void setTotalNilaiBangunan(Double totalNilaiBangunan) {
        this.totalNilaiBangunan = totalNilaiBangunan;
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

    public Double getTotalNilaiAset() {
        return totalNilaiAset;
    }

    public void setTotalNilaiAset(Double totalNilaiAset) {
        this.totalNilaiAset = totalNilaiAset;
    }
}
