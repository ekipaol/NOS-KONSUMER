package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MparseResponseHasilRAC {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("applicationId")
    @Expose
    private Integer applicationId;
    @SerializedName("TanggalCheck")
    @Expose
    private String tanggalCheck;
    @SerializedName("HasilDHN")
    @Expose
    private String hasilDHN;
    @SerializedName("IsDHN")
    @Expose
    private String isDHN;
    @SerializedName("HasilPPATK")
    @Expose
    private String hasilPPATK;
    @SerializedName("IsPPATK")
    @Expose
    private String isPPATK;
    @SerializedName("HasilDTTOT")
    @Expose
    private String hasilDTTOT;
    @SerializedName("IsDTTOT")
    @Expose
    private String isDTTOT;
    @SerializedName("HasilWO")
    @Expose
    private String hasilWO;
    @SerializedName("IsWo")
    @Expose
    private String isWo;
    @SerializedName("HasilRestruk")
    @Expose
    private String hasilRestruk;
    @SerializedName("IsRestruk")
    @Expose
    private String isRestruk;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getTanggalCheck() {
        return tanggalCheck;
    }

    public void setTanggalCheck(String tanggalCheck) {
        this.tanggalCheck = tanggalCheck;
    }

    public String getHasilDHN() {
        return hasilDHN;
    }

    public void setHasilDHN(String hasilDHN) {
        this.hasilDHN = hasilDHN;
    }

    public String getIsDHN() {
        return isDHN;
    }

    public void setIsDHN(String isDHN) {
        this.isDHN = isDHN;
    }

    public String getHasilPPATK() {
        return hasilPPATK;
    }

    public void setHasilPPATK(String hasilPPATK) {
        this.hasilPPATK = hasilPPATK;
    }

    public String getIsPPATK() {
        return isPPATK;
    }

    public void setIsPPATK(String isPPATK) {
        this.isPPATK = isPPATK;
    }

    public String getHasilDTTOT() {
        return hasilDTTOT;
    }

    public void setHasilDTTOT(String hasilDTTOT) {
        this.hasilDTTOT = hasilDTTOT;
    }

    public String getIsDTTOT() {
        return isDTTOT;
    }

    public void setIsDTTOT(String isDTTOT) {
        this.isDTTOT = isDTTOT;
    }

    public String getHasilWO() {
        return hasilWO;
    }

    public void setHasilWO(String hasilWO) {
        this.hasilWO = hasilWO;
    }

    public String getIsWo() {
        return isWo;
    }

    public void setIsWo(String isWo) {
        this.isWo = isWo;
    }

    public String getHasilRestruk() {
        return hasilRestruk;
    }

    public void setHasilRestruk(String hasilRestruk) {
        this.hasilRestruk = hasilRestruk;
    }

    public String getIsRestruk() {
        return isRestruk;
    }

    public void setIsRestruk(String isRestruk) {
        this.isRestruk = isRestruk;
    }
}
