package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BsiBisa {
    @SerializedName("IsProhibited")
    @Expose
    private Boolean isProhibited;
    @SerializedName("CheckDateAML")
    @Expose
    private String checkDate;
    @SerializedName("IsDHN")
    @Expose
    private Boolean isDHN;
    @SerializedName("HasilDHN")
    @Expose
    private String hasilDHN;
    @SerializedName("IsPPATK")
    @Expose
    private Boolean isPPATK;
    @SerializedName("HasilPPATK")
    @Expose
    private String hasilPPATK;
    @SerializedName("IsDTTOT")
    @Expose
    private Boolean isDTTOT;
    @SerializedName("HasilDTTOT")
    @Expose
    private String hasilDTTOT;
    @SerializedName("IsOFAC")
    @Expose
    private Boolean isOFAC;
    @SerializedName("HasilOFAC")
    @Expose
    private String hasilOFAC;
    @SerializedName("IsUNLIST")
    @Expose
    private Boolean isUNLIST;
    @SerializedName("HasilUNLIST")
    @Expose
    private String hasilUNLIST;
    @SerializedName("IsPEP")
    @Expose
    private Boolean isPEP;
    @SerializedName("HasilPEP")
    @Expose
    private String hasilPEP;
    @SerializedName("IsINTERNAL")
    @Expose
    private Boolean isINTERNAL;
    @SerializedName("HasilINTERNAL")
    @Expose
    private String hasilINTERNAL;




    public Boolean getProhibited() {
        return isProhibited;
    }

    public void setProhibited(Boolean prohibited) {
        isProhibited = prohibited;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public Boolean getDHN() {
        return isDHN;
    }

    public void setDHN(Boolean DHN) {
        isDHN = DHN;
    }

    public String getHasilDHN() {
        return hasilDHN;
    }

    public void setHasilDHN(String hasilDHN) {
        this.hasilDHN = hasilDHN;
    }

    public Boolean getPPATK() {
        return isPPATK;
    }

    public void setPPATK(Boolean PPATK) {
        isPPATK = PPATK;
    }

    public String getHasilPPATK() {
        return hasilPPATK;
    }

    public void setHasilPPATK(String hasilPPATK) {
        this.hasilPPATK = hasilPPATK;
    }

    public Boolean getDTTOT() {
        return isDTTOT;
    }

    public void setDTTOT(Boolean DTTOT) {
        isDTTOT = DTTOT;
    }

    public String getHasilDTTOT() {
        return hasilDTTOT;
    }

    public void setHasilDTTOT(String hasilDTTOT) {
        this.hasilDTTOT = hasilDTTOT;
    }

    public Boolean getOFAC() {
        return isOFAC;
    }

    public void setOFAC(Boolean OFAC) {
        isOFAC = OFAC;
    }

    public String getHasilOFAC() {
        return hasilOFAC;
    }

    public void setHasilOFAC(String hasilOFAC) {
        this.hasilOFAC = hasilOFAC;
    }

    public Boolean getUNLIST() {
        return isUNLIST;
    }

    public void setUNLIST(Boolean UNLIST) {
        isUNLIST = UNLIST;
    }

    public String getHasilUNLIST() {
        return hasilUNLIST;
    }

    public void setHasilUNLIST(String hasilUNLIST) {
        this.hasilUNLIST = hasilUNLIST;
    }

    public Boolean getPEP() {
        return isPEP;
    }

    public void setPEP(Boolean PEP) {
        isPEP = PEP;
    }

    public String getHasilPEP() {
        return hasilPEP;
    }

    public void setHasilPEP(String hasilPEP) {
        this.hasilPEP = hasilPEP;
    }

    public Boolean getINTERNAL() {
        return isINTERNAL;
    }

    public void setINTERNAL(Boolean INTERNAL) {
        isINTERNAL = INTERNAL;
    }

    public String getHasilINTERNAL() {
        return hasilINTERNAL;
    }

    public void setHasilINTERNAL(String hasilINTERNAL) {
        this.hasilINTERNAL = hasilINTERNAL;
    }
}
