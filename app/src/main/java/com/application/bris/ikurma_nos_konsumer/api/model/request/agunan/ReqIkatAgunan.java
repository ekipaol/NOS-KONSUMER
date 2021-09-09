package com.application.bris.ikurma_nos_konsumer.api.model.request.agunan;

import com.google.gson.annotations.SerializedName;

public class ReqIkatAgunan {
    @SerializedName("idAgunan")
    private String idAgunan;
    @SerializedName("idApl")
    private String idApl;
    @SerializedName("idCif")
    private String idCif;
    @SerializedName("fidjenisAgunan")
    private int fidjenisAgunan;
    @SerializedName("PlafondCover")
    private String PlafondCover;
    @SerializedName("namaDebitur")
    private String namaDebitur;
    @SerializedName("tipeProduk")
    private String tipeProduk;
    @SerializedName("KlasifikasiAgunan")
    private String KlasifikasiAgunan;
    @SerializedName("JenisPengikatan")
    private String JenisPengikatan;
    @SerializedName("PengikatanAplikasi")
    private String PengikatanAplikasi;
    @SerializedName("DescPengikatan")
    private String DescPengikatan;
    @SerializedName("rekomendasiAgunan")
    private String rekomendasiAgunan;
    @SerializedName("desc_rekomendasi")
    private String desc_rekomendasi;

    public String getIdAgunan() {
        return idAgunan;
    }

    public void setIdAgunan(String idAgunan) {
        this.idAgunan = idAgunan;
    }

    public String getIdApl() {
        return idApl;
    }

    public void setIdApl(String idApl) {
        this.idApl = idApl;
    }

    public String getIdCif() {
        return idCif;
    }

    public void setIdCif(String idCif) {
        this.idCif = idCif;
    }

    public int getFidjenisAgunan() {
        return fidjenisAgunan;
    }

    public void setFidjenisAgunan(int fidjenisAgunan) {
        this.fidjenisAgunan = fidjenisAgunan;
    }

    public String getPlafondCover() {
        return PlafondCover;
    }

    public void setPlafondCover(String plafondCover) {
        PlafondCover = plafondCover;
    }

    public String getNamaDebitur() {
        return namaDebitur;
    }

    public void setNamaDebitur(String namaDebitur) {
        this.namaDebitur = namaDebitur;
    }

    public String getTipeProduk() {
        return tipeProduk;
    }

    public void setTipeProduk(String tipeProduk) {
        this.tipeProduk = tipeProduk;
    }

    public String getKlasifikasiAgunan() {
        return KlasifikasiAgunan;
    }

    public void setKlasifikasiAgunan(String klasifikasiAgunan) {
        KlasifikasiAgunan = klasifikasiAgunan;
    }

    public String getJenisPengikatan() {
        return JenisPengikatan;
    }

    public void setJenisPengikatan(String jenisPengikatan) {
        JenisPengikatan = jenisPengikatan;
    }

    public String getPengikatanAplikasi() {
        return PengikatanAplikasi;
    }

    public void setPengikatanAplikasi(String pengikatanAplikasi) {
        PengikatanAplikasi = pengikatanAplikasi;
    }

    public String getDescPengikatan() {
        return DescPengikatan;
    }

    public void setDescPengikatan(String descPengikatan) {
        DescPengikatan = descPengikatan;
    }

    public String getRekomendasiAgunan() {
        return rekomendasiAgunan;
    }

    public void setRekomendasiAgunan(String rekomendasiAgunan) {
        this.rekomendasiAgunan = rekomendasiAgunan;
    }

    public String getDesc_rekomendasi() {
        return desc_rekomendasi;
    }

    public void setDesc_rekomendasi(String desc_rekomendasi) {
        this.desc_rekomendasi = desc_rekomendasi;
    }
}
