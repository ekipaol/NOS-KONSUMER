package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataMurabahah {
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
    @SerializedName("Nama_Barang")
    @Expose
    private String namaBarang;
    @SerializedName("Jumlah_Satuan")
    @Expose
    private Integer jumlahSatuan;
    @SerializedName("Lokasi")
    @Expose
    private String lokasi;
    @SerializedName("Pemasok")
    @Expose
    private String pemasok;
    @SerializedName("Harga_Barang")
    @Expose
    private Double hargaBarang;

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

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public Integer getJumlahSatuan() {
        return jumlahSatuan;
    }

    public void setJumlahSatuan(Integer jumlahSatuan) {
        this.jumlahSatuan = jumlahSatuan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getPemasok() {
        return pemasok;
    }

    public void setPemasok(String pemasok) {
        this.pemasok = pemasok;
    }

    public Double getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(Double hargaBarang) {
        this.hargaBarang = hargaBarang;
    }
}
