package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;


import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 21/06/2019.
 */

public class InputSektorEkonomiKpr {

    @SerializedName("idAplikasi")
    private Integer idAplikasi;
    @SerializedName("cifLas")
    private Integer cifLas;
    @SerializedName("hubDebiturDgnBank")
    private String hubDebiturDgnBank;
    @SerializedName("bidangUsaha")
    private String bidangUsaha;
    @SerializedName("sifatPembiayaan")
    private String sifatKredit;
    @SerializedName("jenisPenggunaan")
    private String jenisPenggunaan;
    @SerializedName("jenisPenggunaanLBU")
    private String jenisPenggunaanLBU;
    @SerializedName("jenisPembiayaanLBU")
    private Integer jenisKreditLBU;
    @SerializedName("sifatPembiayaanLBU")
    private Integer sifatKreditLBU;
    @SerializedName("kategoriKreditLBU")
    private String kategoriKreditLBU;
    @SerializedName("sektorEkonomiSID")
    private String sektorEkonomiSID;
    @SerializedName("sektorEkonomiLBU")
    private String sektorEkonomiLBU;

    @SerializedName("klasifikasiKPR")
    private String klasifikasiKPR;
    @SerializedName("sumberAplikasi")
    private String sumberAplikasi;
    @SerializedName("tujuanMembukaRekening")
    private Integer tujuanMembukaRekening;
    @SerializedName("referensi")
    private Integer referensi;



    public InputSektorEkonomiKpr(Integer idAplikasi, Integer cifLas, String hubDebiturDgnBank, String bidangUsaha, String sifatKredit, String jenisPenggunaan, String jenisPenggunaanLBU, Integer jenisKreditLBU, Integer sifatKreditLBU, String kategoriKreditLBU, String sektorEkonomiSID, String sektorEkonomiLBU) {
        this.idAplikasi = idAplikasi;
        this.cifLas = cifLas;
        this.hubDebiturDgnBank = hubDebiturDgnBank;
        this.bidangUsaha = bidangUsaha;
        this.sifatKredit = sifatKredit;
        this.jenisPenggunaan = jenisPenggunaan;
        this.jenisPenggunaanLBU = jenisPenggunaanLBU;
        this.jenisKreditLBU = jenisKreditLBU;
        this.sifatKreditLBU = sifatKreditLBU;
        this.kategoriKreditLBU = kategoriKreditLBU;
        this.sektorEkonomiSID = sektorEkonomiSID;
        this.sektorEkonomiLBU = sektorEkonomiLBU;
    }

    public String getKlasifikasiKPR() {
        return klasifikasiKPR;
    }

    public void setKlasifikasiKPR(String klasifikasiKPR) {
        this.klasifikasiKPR = klasifikasiKPR;
    }

    public String getSumberAplikasi() {
        return sumberAplikasi;
    }

    public void setSumberAplikasi(String sumberAplikasi) {
        this.sumberAplikasi = sumberAplikasi;
    }

    public Integer getTujuanMembukaRekening() {
        return tujuanMembukaRekening;
    }

    public void setTujuanMembukaRekening(Integer tujuanMembukaRekening) {
        this.tujuanMembukaRekening = tujuanMembukaRekening;
    }

    public Integer getReferensi() {
        return referensi;
    }

    public void setReferensi(Integer referensi) {
        this.referensi = referensi;
    }

    public void setIdAplikasi(Integer idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public void setCifLas(Integer cifLas) {
        this.cifLas = cifLas;
    }

    public void setHubDebiturDgnBank(String hubDebiturDgnBank) {
        this.hubDebiturDgnBank = hubDebiturDgnBank;
    }

    public void setBidangUsaha(String bidangUsaha) {
        this.bidangUsaha = bidangUsaha;
    }

    public void setSifatKredit(String sifatKredit) {
        this.sifatKredit = sifatKredit;
    }

    public void setJenisPenggunaan(String jenisPenggunaan) {
        this.jenisPenggunaan = jenisPenggunaan;
    }

    public void setJenisPenggunaanLBU(String jenisPenggunaanLBU) {
        this.jenisPenggunaanLBU = jenisPenggunaanLBU;
    }

    public void setJenisKreditLBU(Integer jenisKreditLBU) {
        this.jenisKreditLBU = jenisKreditLBU;
    }

    public void setSifatKreditLBU(Integer sifatKreditLBU) {
        this.sifatKreditLBU = sifatKreditLBU;
    }

    public void setKategoriKreditLBU(String kategoriKreditLBU) {
        this.kategoriKreditLBU = kategoriKreditLBU;
    }

    public void setSektorEkonomiSID(String sektorEkonomiSID) {
        this.sektorEkonomiSID = sektorEkonomiSID;
    }

    public void setSektorEkonomiLBU(String sektorEkonomiLBU) {
        this.sektorEkonomiLBU = sektorEkonomiLBU;
    }
}
