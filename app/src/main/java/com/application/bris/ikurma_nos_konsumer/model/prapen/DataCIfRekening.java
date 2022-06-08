package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.SerializedName;

public class DataCIfRekening {

    @SerializedName("CIF")
    private String CIF;

    @SerializedName("OpeningDate")
    private String OpeningDate;

    @SerializedName("Nama")
    private String Nama;

    @SerializedName("NamaSPAN")
    private String namaSpan;

    @SerializedName("NoRekeningSPAN")
    private String rekeningSpan;

    @SerializedName("GajiSPAN")
    private String gajiSpan;

    @SerializedName("TunjanganSPAN")
    private String tunjanganSpan;

    @SerializedName("KeteranganSPAN")
    private String keteranganSpan;

    public String getNamaSpan() {
        return namaSpan;
    }

    public void setNamaSpan(String namaSpan) {
        this.namaSpan = namaSpan;
    }

    public String getRekeningSpan() {
        return rekeningSpan;
    }

    public void setRekeningSpan(String rekeningSpan) {
        this.rekeningSpan = rekeningSpan;
    }

    public String getGajiSpan() {
        return gajiSpan;
    }

    public void setGajiSpan(String gajiSpan) {
        this.gajiSpan = gajiSpan;
    }

    public String getTunjanganSpan() {
        return tunjanganSpan;
    }

    public void setTunjanganSpan(String tunjanganSpan) {
        this.tunjanganSpan = tunjanganSpan;
    }

    public String getKeteranganSpan() {
        return keteranganSpan;
    }

    public void setKeteranganSpan(String keteranganSpan) {
        this.keteranganSpan = keteranganSpan;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public String getOpeningDate() {
        return OpeningDate;
    }

    public void setOpeningDate(String openingDate) {
        OpeningDate = openingDate;
    }
}
