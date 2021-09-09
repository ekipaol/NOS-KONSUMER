package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;



public class MDataPembiayaan {

    @SerializedName("jenisPembiayaanLBU")
    private String jenisKreditLBU;
    @SerializedName("sifatPembiayaan")
    private String sifatKredit;
    @SerializedName("jenisPenggunaanLBUText")
    private String jenisPenggunaanLBUText;
    @SerializedName("jenisPenggunaanLBU")
    private String jenisPenggunaanLBU;
    @SerializedName("jenisPenggunaanText")
    private String jenisPenggunaanText;
    @SerializedName("jenisPenggunaan")
    private String jenisPenggunaan;
    @SerializedName("hubDebiturDgnBankText")
    private String hubDebiturDgnBankText;
    @SerializedName("sifatKreditText")
    private String sifatKreditText;
    @SerializedName("sifatPembiayaanLBU")
    private String sifatKreditLBU;
    @SerializedName("kategoriKreditLBUText")
    private String kategoriKreditLBUText;
    @SerializedName("sektorEkonomiLBU")
    private String sektorEkonomiLBU;
    @SerializedName("hubDebiturDgnBank")
    private String hubDebiturDgnBank;
    @SerializedName("bidangUsaha")
    private String bidangUsaha;
    @SerializedName("bidangUsahaText")
    private String bidangUsahaText;
    @SerializedName("jenisKreditLBUText")
    private String jenisKreditLBUText;
    @SerializedName("sektorEkonomiText")
    private String sektorEkonomiText;
    @SerializedName("kategoriKreditLBU")
    private String kategoriKreditLBU;
    @SerializedName("tujuanPenggunaan")
    private String tujuanPenggunaan;
    @SerializedName("sektorEkonomiSID")
    private String sektorEkonomiSID;
    @SerializedName("sifatKreditLBUText")
    private String sifatKreditLBUText;
    @SerializedName("tujuanPengajuan")
    private String tujuanPengajuan;



    public MDataPembiayaan(String jenisKreditLBU, String sifatKredit, String jenisPenggunaanLBUText, String jenisPenggunaanLBU, String jenisPenggunaanText, String jenisPenggunaan, String hubDebiturDgnBankText, String sifatKreditText, String sifatKreditLBU, String kategoriKreditLBUText, String sektorEkonomiLBU, String hubDebiturDgnBank, String bidangUsaha, String bidangUsahaText, String jenisKreditLBUText, String sektorEkonomiText, String kategoriKreditLBU, String tujuanPenggunaan, String sektorEkonomiSID, String sifatKreditLBUText) {
        this.jenisKreditLBU = jenisKreditLBU;
        this.sifatKredit = sifatKredit;
        this.jenisPenggunaanLBUText = jenisPenggunaanLBUText;
        this.jenisPenggunaanLBU = jenisPenggunaanLBU;
        this.jenisPenggunaanText = jenisPenggunaanText;
        this.jenisPenggunaan = jenisPenggunaan;
        this.hubDebiturDgnBankText = hubDebiturDgnBankText;
        this.sifatKreditText = sifatKreditText;
        this.sifatKreditLBU = sifatKreditLBU;
        this.kategoriKreditLBUText = kategoriKreditLBUText;
        this.sektorEkonomiLBU = sektorEkonomiLBU;
        this.hubDebiturDgnBank = hubDebiturDgnBank;
        this.bidangUsaha = bidangUsaha;
        this.bidangUsahaText = bidangUsahaText;
        this.jenisKreditLBUText = jenisKreditLBUText;
        this.sektorEkonomiText = sektorEkonomiText;
        this.kategoriKreditLBU = kategoriKreditLBU;
        this.tujuanPenggunaan = tujuanPenggunaan;
        this.sektorEkonomiSID = sektorEkonomiSID;
        this.sifatKreditLBUText = sifatKreditLBUText;
    }

    public String getTujuanPengajuan() {
        return tujuanPengajuan;
    }

    public void setTujuanPengajuan(String tujuanPengajuan) {
        this.tujuanPengajuan = tujuanPengajuan;
    }

    public String getJenisKreditLBU() {
        return jenisKreditLBU;
    }

    public String getSifatKredit() {
        return sifatKredit;
    }

    public String getJenisPenggunaanLBUText() {
        return jenisPenggunaanLBUText;
    }

    public String getJenisPenggunaanLBU() {
        return jenisPenggunaanLBU;
    }

    public String getJenisPenggunaanText() {
        return jenisPenggunaanText;
    }

    public String getJenisPenggunaan() {
        return jenisPenggunaan;
    }

    public String getHubDebiturDgnBankText() {
        return hubDebiturDgnBankText;
    }

    public String getSifatKreditText() {
        return sifatKreditText;
    }

    public String getSifatKreditLBU() {
        return sifatKreditLBU;
    }

    public String getKategoriKreditLBUText() {
        return kategoriKreditLBUText;
    }

    public String getSektorEkonomiLBU() {
        return sektorEkonomiLBU;
    }

    public String getHubDebiturDgnBank() {
        return hubDebiturDgnBank;
    }

    public String getBidangUsaha() {
        return bidangUsaha;
    }

    public String getBidangUsahaText() {
        return bidangUsahaText;
    }

    public String getJenisKreditLBUText() {
        return jenisKreditLBUText;
    }

    public String getSektorEkonomiText() {
        return sektorEkonomiText;
    }

    public String getKategoriKreditLBU() {
        return kategoriKreditLBU;
    }

    public String getTujuanPenggunaan() {
        return tujuanPenggunaan;
    }

    public String getSektorEkonomiSID() {
        return sektorEkonomiSID;
    }

    public String getSifatKreditLBUText() {
        return sifatKreditLBUText;
    }
}
