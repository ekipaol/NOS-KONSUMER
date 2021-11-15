package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataMarketing   {

    @SerializedName("ApplicationId")
    @Expose
    private Long applicationId;
    @SerializedName("Sumber_Aplikasi")
    @Expose
    private String sumberAplikasi;
    @SerializedName("Kode_AO")
    @Expose
    private String kodeAO;
    @SerializedName("Nama_AO")
    @Expose
    private String namaAO;
    @SerializedName("Kode_AO2")
    @Expose
    private String kodeAO2;
    @SerializedName("Kode_Cabang_Pembukuan")
    @Expose
    private String kodeCabangPembukuan;
    @SerializedName("Kode_Cabang_Referral")
    @Expose
    private String kodeCabangReferral;
    @SerializedName("Mitra_Fronting")
    @Expose
    private String mitraFronting;
    @SerializedName("Kode_AO_Mitra_Fronting")
    @Expose
    private String kodeAOMitraFronting;
    @SerializedName("UID")
    @Expose
    private String uid;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getSumberAplikasi() {
        return sumberAplikasi;
    }

    public void setSumberAplikasi(String sumberAplikasi) {
        this.sumberAplikasi = sumberAplikasi;
    }

    public String getKodeAO() {
        return kodeAO;
    }

    public void setKodeAO(String kodeAO) {
        this.kodeAO = kodeAO;
    }

    public String getNamaAO() {
        return namaAO;
    }

    public void setNamaAO(String namaAO) {
        this.namaAO = namaAO;
    }

    public String getKodeAO2() {
        return kodeAO2;
    }

    public void setKodeAO2(String kodeAO2) {
        this.kodeAO2 = kodeAO2;
    }

    public String getKodeCabangPembukuan() {
        return kodeCabangPembukuan;
    }

    public void setKodeCabangPembukuan(String kodeCabangPembukuan) {
        this.kodeCabangPembukuan = kodeCabangPembukuan;
    }

    public String getKodeCabangReferral() {
        return kodeCabangReferral;
    }

    public void setKodeCabangReferral(String kodeCabangReferral) {
        this.kodeCabangReferral = kodeCabangReferral;
    }

    public String getMitraFronting() {
        return mitraFronting;
    }

    public void setMitraFronting(String mitraFronting) {
        this.mitraFronting = mitraFronting;
    }

    public String getKodeAOMitraFronting() {
        return kodeAOMitraFronting;
    }

    public void setKodeAOMitraFronting(String kodeAOMitraFronting) {
        this.kodeAOMitraFronting = kodeAOMitraFronting;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
