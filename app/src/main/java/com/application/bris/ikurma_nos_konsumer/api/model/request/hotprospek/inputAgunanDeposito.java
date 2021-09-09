package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 10/07/2019.
 */

public class inputAgunanDeposito {
    @SerializedName("idApl")
    public Integer idApl;
    @SerializedName("idCif")
    public Integer idCif;
    @SerializedName("idAgunan")
    public Integer idAgunan;
    @SerializedName("tipeProduk")
    public Integer tipeProduk;
    @SerializedName("tglPemeriksaan")
    public String tglPemeriksaan;
    @SerializedName("JenisDeposito")
    public String jenisDeposito;
    @SerializedName("NamaPemilik")
    public String namaPemilik;
    @SerializedName("AlamatPemilik")
    public String alamatPemilik;
    @SerializedName("Hubungan")
    public String hubungan;
    @SerializedName("NoBilyet")
    public String noBilyet;
    @SerializedName("BankPenerbit")
    public String bankPenerbit;
    @SerializedName("TanggalPenerbitan")
    public String tanggalPenerbitan;
    @SerializedName("TanggalJatuhTempo")
    public String tanggalJatuhTempo;
    @SerializedName("IsAro")
    public String isAro;
    @SerializedName("NilaiNominal")
    public String nilaiNominal;
    @SerializedName("NilaiTaksasi")
    public String nilaiTaksasi;
    @SerializedName("Keterangan")
    public String keterangan;
    @SerializedName("idPhoto")
    public Integer idPhoto;

    public inputAgunanDeposito(Integer idApl, Integer idCif, Integer idAgunan, Integer tipeProduk, String tglPemeriksaan, String jenisDeposito, String namaPemilik, String alamatPemilik, String hubungan, String noBilyet, String bankPenerbit, String tanggalPenerbitan, String tanggalJatuhTempo, String isAro, String nilaiNominal, String nilaiTaksasi, String keterangan, Integer idPhoto) {
        this.idApl = idApl;
        this.idCif = idCif;
        this.idAgunan = idAgunan;
        this.tipeProduk = tipeProduk;
        this.tglPemeriksaan = tglPemeriksaan;
        this.jenisDeposito = jenisDeposito;
        this.namaPemilik = namaPemilik;
        this.alamatPemilik = alamatPemilik;
        this.hubungan = hubungan;
        this.noBilyet = noBilyet;
        this.bankPenerbit = bankPenerbit;
        this.tanggalPenerbitan = tanggalPenerbitan;
        this.tanggalJatuhTempo = tanggalJatuhTempo;
        this.isAro = isAro;
        this.nilaiNominal = nilaiNominal;
        this.nilaiTaksasi = nilaiTaksasi;
        this.keterangan = keterangan;
        this.idPhoto = idPhoto;
    }

    public void setIdApl(Integer idApl) {
        this.idApl = idApl;
    }

    public void setIdCif(Integer idCif) {
        this.idCif = idCif;
    }

    public void setIdAgunan(Integer idAgunan) {
        this.idAgunan = idAgunan;
    }

    public void setTipeProduk(Integer tipeProduk) {
        this.tipeProduk = tipeProduk;
    }

    public void setTglPemeriksaan(String tglPemeriksaan) {
        this.tglPemeriksaan = tglPemeriksaan;
    }

    public void setJenisDeposito(String jenisDeposito) {
        this.jenisDeposito = jenisDeposito;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public void setAlamatPemilik(String alamatPemilik) {
        this.alamatPemilik = alamatPemilik;
    }

    public void setHubungan(String hubungan) {
        this.hubungan = hubungan;
    }

    public void setNoBilyet(String noBilyet) {
        this.noBilyet = noBilyet;
    }

    public void setBankPenerbit(String bankPenerbit) {
        this.bankPenerbit = bankPenerbit;
    }

    public void setTanggalPenerbitan(String tanggalPenerbitan) {
        this.tanggalPenerbitan = tanggalPenerbitan;
    }

    public void setTanggalJatuhTempo(String tanggalJatuhTempo) {
        this.tanggalJatuhTempo = tanggalJatuhTempo;
    }

    public void setIsAro(String isAro) {
        this.isAro = isAro;
    }

    public void setNilaiNominal(String nilaiNominal) {
        this.nilaiNominal = nilaiNominal;
    }

    public void setNilaiTaksasi(String nilaiTaksasi) {
        this.nilaiTaksasi = nilaiTaksasi;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public void setIdPhoto(Integer idPhoto) {
        this.idPhoto = idPhoto;
    }
}
