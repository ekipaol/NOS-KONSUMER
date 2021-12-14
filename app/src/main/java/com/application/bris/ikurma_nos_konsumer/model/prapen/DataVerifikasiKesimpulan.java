package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataVerifikasiKesimpulan {
    @SerializedName("SesuaiRAC")
    @Expose
    private String SesuaiRAC;
    @SerializedName("SesuaiFiturPembiayaan")
    @Expose
    private String SesuaiFiturPembiayaan;
    @SerializedName("MaksimalJangkaWaktu")
    @Expose
    private String MaksimalJangkaWaktu;
    @SerializedName("MaksimalAngsuranBulanan")
    @Expose
    private String MaksimalAngsuranBulanan;
    @SerializedName("RekomendasiScoring")
    @Expose
    private String RekomendasiScoring;

    public String getRekomendasiScoring() {
        return RekomendasiScoring;
    }

    public void setRekomendasiScoring(String rekomendasiScoring) {
        RekomendasiScoring = rekomendasiScoring;
    }

    public String getSesuaiRAC() {
        return SesuaiRAC;
    }

    public void setSesuaiRAC(String sesuaiRAC) {
        SesuaiRAC = sesuaiRAC;
    }

    public String getSesuaiFiturPembiayaan() {
        return SesuaiFiturPembiayaan;
    }

    public void setSesuaiFiturPembiayaan(String sesuaiFiturPembiayaan) {
        SesuaiFiturPembiayaan = sesuaiFiturPembiayaan;
    }

    public String getMaksimalJangkaWaktu() {
        return MaksimalJangkaWaktu;
    }

    public void setMaksimalJangkaWaktu(String maksimalJangkaWaktu) {
        MaksimalJangkaWaktu = maksimalJangkaWaktu;
    }

    public String getMaksimalAngsuranBulanan() {
        return MaksimalAngsuranBulanan;
    }

    public void setMaksimalAngsuranBulanan(String maksimalAngsuranBulanan) {
        MaksimalAngsuranBulanan = maksimalAngsuranBulanan;
    }
}
