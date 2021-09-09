package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 25/06/2019.
 */

public class DataPrescreening {

    @SerializedName("ID_PRESCREENING")
    private Integer iDPRESCREENING;
    @SerializedName("DUKCAPIL")
    private Boolean dUKCAPIL;
    @SerializedName("DHN")
    private Boolean dHN;
    @SerializedName("SLIK")
    private Boolean sLIK;
    @SerializedName("SIKP")
    private Boolean sIKP;

    @SerializedName("SLIK_KET")
    private String slik_ket;
    @SerializedName("DUCKAPIL_KET")
    private String dukcapil_ket;
    @SerializedName("SIKP_KET")
    private String sikp_ket;
    @SerializedName("DHN_KET")
    private String dhn_ket;

    @SerializedName("RESULT")
    private String rESULT;
    @SerializedName("FID_APLIKASI")
    private Integer fIDAPLIKASI;
    @SerializedName("STATUS_KAWIN")
    private String sTATUSKAWIN;
    @SerializedName("FLAG_MEMO_SALES")
    private int flagMemoSales;

    @SerializedName("noPermin")
    private String noPermin;

    @SerializedName("STATUS_PERMINTAAN_SLIK")
    private String statusPermintaanSlik;

    public String getStatusPermintaanSlik() {
        return statusPermintaanSlik;
    }

    public void setStatusPermintaanSlik(String statusPermintaanSlik) {
        this.statusPermintaanSlik = statusPermintaanSlik;
    }

    public int getFlagMemoSales() {
        return flagMemoSales;
    }

    public void setFlagMemoSales(int flagMemoSales) {
        this.flagMemoSales = flagMemoSales;
    }

    public String getNoPermin() {
        return noPermin;
    }

    public void setNoPermin(String noPermin) {
        this.noPermin = noPermin;
    }

    public String getNoPerminPasangan() {
        return noPerminPasangan;
    }

    public void setNoPerminPasangan(String noPerminPasangan) {
        this.noPerminPasangan = noPerminPasangan;
    }

    @SerializedName("noPermin2")
    private String noPerminPasangan

            ;



    public DataPrescreening(Integer iDPRESCREENING, Boolean dUKCAPIL, Boolean dHN, Boolean sLIK, Boolean sIKP, String rESULT, Integer fIDAPLIKASI, String sTATUSKAWIN) {
        this.iDPRESCREENING = iDPRESCREENING;
        this.dUKCAPIL = dUKCAPIL;
        this.dHN = dHN;
        this.sLIK = sLIK;
        this.sIKP = sIKP;
        this.rESULT = rESULT;
        this.fIDAPLIKASI = fIDAPLIKASI;
        this.sTATUSKAWIN = sTATUSKAWIN;
    }

    public String getSlik_ket() {
        return slik_ket;
    }

    public void setSlik_ket(String slik_ket) {
        this.slik_ket = slik_ket;
    }

    public String getDukcapil_ket() {
        return dukcapil_ket;
    }

    public void setDukcapil_ket(String dukcapil_ket) {
        this.dukcapil_ket = dukcapil_ket;
    }

    public String getSikp_ket() {
        return sikp_ket;
    }

    public void setSikp_ket(String sikp_ket) {
        this.sikp_ket = sikp_ket;
    }

    public String getDhn_ket() {
        return dhn_ket;
    }

    public void setDhn_ket(String dhn_ket) {
        this.dhn_ket = dhn_ket;
    }

    public Integer getiDPRESCREENING() {
        return iDPRESCREENING;
    }

    public Boolean getdUKCAPIL() {
        return dUKCAPIL;
    }

    public Boolean getdHN() {
        return dHN;
    }

    public Boolean getsLIK() {
        return sLIK;
    }

    public Boolean getsIKP() {
        return sIKP;
    }

    public String getrESULT() {
        return rESULT;
    }

    public Integer getfIDAPLIKASI() {
        return fIDAPLIKASI;
    }

    public String getsTATUSKAWIN(){
        return  sTATUSKAWIN;
    }
}
