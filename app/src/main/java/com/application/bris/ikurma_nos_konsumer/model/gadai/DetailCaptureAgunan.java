package com.application.bris.ikurma_nos_konsumer.model.gadai;

import com.google.gson.annotations.SerializedName;

public class DetailCaptureAgunan {
    @SerializedName("NoAplikasi")
    private String NoAplikasi;
    @SerializedName("KodeCabang")
    private String KodeCabang;
    @SerializedName("NamaSesuaiKTP")
    private String NamaSesuaiKTP;
    @SerializedName("NoKTP")
    private String NoKTP;


    public String getNoAplikasi() {
        return NoAplikasi;
    }

    public void setNoAplikasi(String NoAplikasi) {
        this.NoAplikasi = NoAplikasi;
    }

    public String getKodeCabang() {
        return KodeCabang;
    }

    public void setKodeCabang(String cabang) {
        this.KodeCabang = KodeCabang;
    }

    public String getNamaSesuaiKTP() {
        return NamaSesuaiKTP;
    }

    public void setNamaSesuaiKTP(String NamaSesuaiKTP) {
        this.NamaSesuaiKTP = NamaSesuaiKTP;
    }

    public String getNoKTP() {
        return NoKTP;
    }

    public void setNoKTP(String NoKTP) {
        this.NoKTP = NoKTP;
    }

}
