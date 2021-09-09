package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 15/07/2019.
 */

public class AgunanDeposito {
    @SerializedName("JenisDeposito")
    public String jenisDeposito;
    @SerializedName("NamaPemilik")
    public String namaPemilik;
    @SerializedName("AlamatPemilik")
    public String alamatPemilik;
    @SerializedName("tglPemeriksaan")
    public String tglPemeriksaan;
    @SerializedName("BankPenerbit")
    public String bankPenerbit;
    @SerializedName("Hubungan")
    public String hubungan;
    @SerializedName("NoBilyet")
    public String noBilyet;
    @SerializedName("IsAro")
    public String isAro;
    @SerializedName("TanggalJatuhTempo")
    public String tanggalJatuhTempo;
    @SerializedName("TanggalPenerbitan")
    public String tanggalPenerbitan;
    @SerializedName("NilaiTaksasi")
    public String nilaiTaksasi;
    @SerializedName("Keterangan")
    public String keterangan;
    @SerializedName("NilaiNominal")
    public String nilaiNominal;
    @SerializedName("idPhoto")
    public Integer idPhoto;

    public String getJenisDeposito() {
        return jenisDeposito;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public String getAlamatPemilik() {
        return alamatPemilik;
    }

    public String getTglPemeriksaan() {
        return tglPemeriksaan;
    }

    public String getBankPenerbit() {
        return bankPenerbit;
    }

    public String getHubungan() {
        return hubungan;
    }

    public String getNoBilyet() {
        return noBilyet;
    }

    public String getIsAro() {
        return isAro;
    }

    public String getTanggalJatuhTempo() {
        return tanggalJatuhTempo;
    }

    public String getTanggalPenerbitan() {
        return tanggalPenerbitan;
    }

    public String getNilaiTaksasi() {
        return nilaiTaksasi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getNilaiNominal() {
        return nilaiNominal;
    }

    public Integer getIdPhoto() {
        return idPhoto;
    }
}
