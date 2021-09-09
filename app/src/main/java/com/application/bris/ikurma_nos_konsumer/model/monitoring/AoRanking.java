package com.application.bris.ikurma_nos_konsumer.model.monitoring;

import com.google.gson.annotations.SerializedName;

public class AoRanking {

    @SerializedName("NAMA_PEGAWAI")
    private String NAMA_PEGAWAI;

    @SerializedName("TOTAL_OS")
    private String TOTAL_OS;

    @SerializedName("TOTAL_PENCAIRAN")
    private String TOTAL_PENCAIRAN;

    @SerializedName("TOTAL_DPK")
    private String TOTAL_DPK;

    @SerializedName("TOTAL_KOL2")
    private String TOTAL_KOL2;

    @SerializedName("NAMA_CABANG")
    private String NAMA_CABANG;

    @SerializedName("TOTAL_NPF")
    private String TOTAL_NPF;

    @SerializedName("UID")
    private String UID;

    @SerializedName("NO_PEGAWAI")
    private String NO_PEGAWAI;

    @SerializedName("TARGET_PENCAIRAN")
    private Long TARGET_PENCAIRAN;

    public Long getTARGET_PENCAIRAN() {
        return TARGET_PENCAIRAN;
    }

    public void setTARGET_PENCAIRAN(Long TARGET_PENCAIRAN) {
        this.TARGET_PENCAIRAN = TARGET_PENCAIRAN;
    }

    public String getNO_PEGAWAI() {
        return NO_PEGAWAI;
    }

    public void setNO_PEGAWAI(String NO_PEGAWAI) {
        this.NO_PEGAWAI = NO_PEGAWAI;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getTOTAL_NPF() {
        return TOTAL_NPF;
    }

    public void setTOTAL_NPF(String TOTAL_NPF) {
        this.TOTAL_NPF = TOTAL_NPF;
    }

    public String getNAMA_CABANG() {
        return NAMA_CABANG;
    }

    public void setNAMA_CABANG(String NAMA_CABANG) {
        this.NAMA_CABANG = NAMA_CABANG;
    }

    public String getNAMA_PEGAWAI() {
        return NAMA_PEGAWAI;
    }

    public void setNAMA_PEGAWAI(String NAMA_PEGAWAI) {
        this.NAMA_PEGAWAI = NAMA_PEGAWAI;
    }

    public String getTOTAL_OS() {
        return TOTAL_OS;
    }

    public void setTOTAL_OS(String TOTAL_OS) {
        this.TOTAL_OS = TOTAL_OS;
    }

    public String getTOTAL_PENCAIRAN() {
        return TOTAL_PENCAIRAN;
    }

    public void setTOTAL_PENCAIRAN(String TOTAL_PENCAIRAN) {
        this.TOTAL_PENCAIRAN = TOTAL_PENCAIRAN;
    }

    public String getTOTAL_DPK() {
        return TOTAL_DPK;
    }

    public void setTOTAL_DPK(String TOTAL_DPK) {
        this.TOTAL_DPK = TOTAL_DPK;
    }

    public String getTOTAL_KOL2() {
        return TOTAL_KOL2;
    }

    public void setTOTAL_KOL2(String TOTAL_KOL2) {
        this.TOTAL_KOL2 = TOTAL_KOL2;
    }
}
