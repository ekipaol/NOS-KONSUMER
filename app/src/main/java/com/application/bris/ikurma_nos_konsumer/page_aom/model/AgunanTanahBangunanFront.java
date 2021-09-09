package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class AgunanTanahBangunanFront {

    @SerializedName("STATUS")
    private int STATUS;
    @SerializedName("ATAS_NAMA_SERTIFIKAT")
    private String ATAS_NAMA_SERTIFIKAT;
    @SerializedName("LOKASI")
    private String LOKASI;
    @SerializedName("JENIS_SURAT_TANAH")
    private String JENIS_SURAT_TANAH;
    @SerializedName("FID_AGUNAN")
    private String FID_AGUNAN;
    @SerializedName("NILAI_PASAR")
    private String NILAI_PASAR;
    @SerializedName("NO_SERTIFIKAT_TANAH")
    private String NO_SERTIFIKAT_TANAH;

    @SerializedName("UUID")
    private String uuid;

    public AgunanTanahBangunanFront(int STATUS, String ATAS_NAMA_SERTIFIKAT, String LOKASI, String JENIS_SURAT_TANAH, String FID_AGUNAN, String NILAI_PASAR, String NO_SERTIFIKAT_TANAH, String uuid) {
        this.STATUS = STATUS;
        this.ATAS_NAMA_SERTIFIKAT = ATAS_NAMA_SERTIFIKAT;
        this.LOKASI = LOKASI;
        this.JENIS_SURAT_TANAH = JENIS_SURAT_TANAH;
        this.FID_AGUNAN = FID_AGUNAN;
        this.NILAI_PASAR = NILAI_PASAR;
        this.NO_SERTIFIKAT_TANAH = NO_SERTIFIKAT_TANAH;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public String getATAS_NAMA_SERTIFIKAT() {
        return ATAS_NAMA_SERTIFIKAT;
    }

    public void setATAS_NAMA_SERTIFIKAT(String ATAS_NAMA_SERTIFIKAT) {
        this.ATAS_NAMA_SERTIFIKAT = ATAS_NAMA_SERTIFIKAT;
    }

    public String getLOKASI() {
        return LOKASI;
    }

    public void setLOKASI(String LOKASI) {
        this.LOKASI = LOKASI;
    }

    public String getJENIS_SURAT_TANAH() {
        return JENIS_SURAT_TANAH;
    }

    public void setJENIS_SURAT_TANAH(String JENIS_SURAT_TANAH) {
        this.JENIS_SURAT_TANAH = JENIS_SURAT_TANAH;
    }

    public String getFID_AGUNAN() {
        return FID_AGUNAN;
    }

    public void setFID_AGUNAN(String FID_AGUNAN) {
        this.FID_AGUNAN = FID_AGUNAN;
    }

    public String getNILAI_PASAR() {
        return NILAI_PASAR;
    }

    public void setNILAI_PASAR(String NILAI_PASAR) {
        this.NILAI_PASAR = NILAI_PASAR;
    }

    public String getNO_SERTIFIKAT_TANAH() {
        return NO_SERTIFIKAT_TANAH;
    }

    public void setNO_SERTIFIKAT_TANAH(String NO_SERTIFIKAT_TANAH) {
        this.NO_SERTIFIKAT_TANAH = NO_SERTIFIKAT_TANAH;
    }
}
