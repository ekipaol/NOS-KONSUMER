package com.application.bris.ikurma_nos_konsumer.model.hotprospek;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataFinansialKpr {
    @SerializedName("OMZET_USAHA_BERSIH_PERBULAN")
    @Expose
    private String oMZETUSAHABERSIHPERBULAN;
    @SerializedName("OMZET_USAHA_SETELAH_POTONGAN_PERBULAN")
    @Expose
    private String oMZETUSAHASETELAHPOTONGANPERBULAN;
    @SerializedName("PROSENTASE_UANG_MUKA")
    @Expose
    private String pROSENTASEUANGMUKA;
    @SerializedName("ID_PRESCORING")
    @Expose
    private String iDPRESCORING;
    @SerializedName("BIAYA_RUMAH_TANGGA")
    @Expose
    private String bIAYARUMAHTANGGA;
    @SerializedName("BIAYA_LAIN_LAIN")
    @Expose
    private Long bIAYALAINLAIN;
    @SerializedName("LOAN_TO_VALUE")
    @Expose
    private String lOANTOVALUE;
    @SerializedName("NET_WORTH")
    @Expose
    private String nETWORTH;
    @SerializedName("PLAFOND_YANG_DIUSULKAN")
    @Expose
    private Long pLAFONDYANGDIUSULKAN;
    @SerializedName("PERMOHONAN_KREDIT")
    @Expose
    private Long pERMOHONANKREDIT;
    @SerializedName("ANGSURAN")
    @Expose
    private String aNGSURAN;
    @SerializedName("DEVIASI_DIR")
    @Expose
    private String dEVIASIDIR;
    @SerializedName("BESAR_UANG_MUKA")
    @Expose
    private String bESARUANGMUKA;
    @SerializedName("SUKU_BUNGA")
    @Expose
    private String sUKUBUNGA;
    @SerializedName("RUMAH_KEBERAPA")
    @Expose
    private String rUMAHKEBERAPA;
    @SerializedName("SUKU_COUNTER")
    @Expose
    private String sUKUCOUNTER;
    @SerializedName("ANGSURAN_USULAN")
    @Expose
    private String aNGSURANUSULAN;
    @SerializedName("BIAYA_TOTAL")
    @Expose
    private String bIAYATOTAL;
    @SerializedName("MAKSIMUM_PLAFOND")
    @Expose
    private Long mAKSIMUMPLAFOND;
    @SerializedName("MAKSIMUM_PLAFOND_COUNTER")
    @Expose
    private Long mAKSIMUMPLAFONDCOUNTER;
    @SerializedName("POTONGAN_PER_BULAN")
    @Expose
    private String pOTONGANPERBULAN;
    @SerializedName("BIAYA_SEKOLAH_ANAK")
    @Expose
    private String bIAYASEKOLAHANAK;
    @SerializedName("OMZET_PERBULAN")
    @Expose
    private String oMZETPERBULAN;
    @SerializedName("ID_SCORING")
    @Expose
    private String iDSCORING;
    @SerializedName("PENGHASILAN_TETAP_LAIN")
    @Expose
    private Long pENGHASILANTETAPLAIN;
    @SerializedName("JANGKA_WAKTU")
    @Expose
    private String jANGKAWAKTU;
    @SerializedName("HARGA_PROPERTY")
    @Expose
    private Long hARGAPROPERTY;
    @SerializedName("NET_MONTHLY_INCOME")
    @Expose
    private String nETMONTHLYINCOME;

    @SerializedName("SUKU_MARGIN")
    @Expose
    private String SUKU_MARGIN;

    @SerializedName("TUJUAN_QARDH")
    @Expose
    private String TUJUAN_QARDH;

    @SerializedName("JW_QARDH")
    @Expose
    private String JW_QARDH;

    @SerializedName("ID_TUJUAN")
    @Expose
    private String ID_TUJUAN;

    @SerializedName("SBUM")
    @Expose
    private String SBUM;

    @SerializedName("JOINT_INCOME")
    @Expose
    private String JOINT_INCOME;

    public String getJOINT_INCOME() {
        return JOINT_INCOME;
    }

    public void setJOINT_INCOME(String JOINT_INCOME) {
        this.JOINT_INCOME = JOINT_INCOME;
    }

    public String getSBUM() {
        return SBUM;
    }

    public void setSBUM(String SBUM) {
        this.SBUM = SBUM;
    }

    public String getTUJUAN_QARDH() {
        return TUJUAN_QARDH;
    }

    public void setTUJUAN_QARDH(String TUJUAN_QARDH) {
        this.TUJUAN_QARDH = TUJUAN_QARDH;
    }

    public String getJW_QARDH() {
        return JW_QARDH;
    }

    public void setJW_QARDH(String JW_QARDH) {
        this.JW_QARDH = JW_QARDH;
    }

    public String getID_TUJUAN() {
        return ID_TUJUAN;
    }

    public void setID_TUJUAN(String ID_TUJUAN) {
        this.ID_TUJUAN = ID_TUJUAN;
    }

    public String getSUKU_MARGIN() {
        return SUKU_MARGIN;
    }

    public void setSUKU_MARGIN(String SUKU_MARGIN) {
        this.SUKU_MARGIN = SUKU_MARGIN;
    }

    public String getoMZETUSAHABERSIHPERBULAN() {
        return oMZETUSAHABERSIHPERBULAN;
    }

    public void setoMZETUSAHABERSIHPERBULAN(String oMZETUSAHABERSIHPERBULAN) {
        this.oMZETUSAHABERSIHPERBULAN = oMZETUSAHABERSIHPERBULAN;
    }

    public String getoMZETUSAHASETELAHPOTONGANPERBULAN() {
        return oMZETUSAHASETELAHPOTONGANPERBULAN;
    }

    public void setoMZETUSAHASETELAHPOTONGANPERBULAN(String oMZETUSAHASETELAHPOTONGANPERBULAN) {
        this.oMZETUSAHASETELAHPOTONGANPERBULAN = oMZETUSAHASETELAHPOTONGANPERBULAN;
    }

    public String getpROSENTASEUANGMUKA() {
        return pROSENTASEUANGMUKA;
    }

    public void setpROSENTASEUANGMUKA(String pROSENTASEUANGMUKA) {
        this.pROSENTASEUANGMUKA = pROSENTASEUANGMUKA;
    }

    public String getiDPRESCORING() {
        return iDPRESCORING;
    }

    public void setiDPRESCORING(String iDPRESCORING) {
        this.iDPRESCORING = iDPRESCORING;
    }

    public String getbIAYARUMAHTANGGA() {
        return bIAYARUMAHTANGGA;
    }

    public void setbIAYARUMAHTANGGA(String bIAYARUMAHTANGGA) {
        this.bIAYARUMAHTANGGA = bIAYARUMAHTANGGA;
    }

    public Long getbIAYALAINLAIN() {
        return bIAYALAINLAIN;
    }

    public void setbIAYALAINLAIN(Long bIAYALAINLAIN) {
        this.bIAYALAINLAIN = bIAYALAINLAIN;
    }

    public String getlOANTOVALUE() {
        return lOANTOVALUE;
    }

    public void setlOANTOVALUE(String lOANTOVALUE) {
        this.lOANTOVALUE = lOANTOVALUE;
    }

    public String getnETWORTH() {
        return nETWORTH;
    }

    public void setnETWORTH(String nETWORTH) {
        this.nETWORTH = nETWORTH;
    }

    public Long getpLAFONDYANGDIUSULKAN() {
        return pLAFONDYANGDIUSULKAN;
    }

    public void setpLAFONDYANGDIUSULKAN(Long pLAFONDYANGDIUSULKAN) {
        this.pLAFONDYANGDIUSULKAN = pLAFONDYANGDIUSULKAN;
    }

    public Long getpERMOHONANKREDIT() {
        return pERMOHONANKREDIT;
    }

    public void setpERMOHONANKREDIT(Long pERMOHONANKREDIT) {
        this.pERMOHONANKREDIT = pERMOHONANKREDIT;
    }

    public String getaNGSURAN() {
        return aNGSURAN;
    }

    public void setaNGSURAN(String aNGSURAN) {
        this.aNGSURAN = aNGSURAN;
    }

    public String getdEVIASIDIR() {
        return dEVIASIDIR;
    }

    public void setdEVIASIDIR(String dEVIASIDIR) {
        this.dEVIASIDIR = dEVIASIDIR;
    }

    public String getbESARUANGMUKA() {
        return bESARUANGMUKA;
    }

    public void setbESARUANGMUKA(String bESARUANGMUKA) {
        this.bESARUANGMUKA = bESARUANGMUKA;
    }

    public String getsUKUBUNGA() {
        return sUKUBUNGA;
    }

    public void setsUKUBUNGA(String sUKUBUNGA) {
        this.sUKUBUNGA = sUKUBUNGA;
    }

    public String getrUMAHKEBERAPA() {
        return rUMAHKEBERAPA;
    }

    public void setrUMAHKEBERAPA(String rUMAHKEBERAPA) {
        this.rUMAHKEBERAPA = rUMAHKEBERAPA;
    }

    public String getsUKUCOUNTER() {
        return sUKUCOUNTER;
    }

    public void setsUKUCOUNTER(String sUKUCOUNTER) {
        this.sUKUCOUNTER = sUKUCOUNTER;
    }

    public String getaNGSURANUSULAN() {
        return aNGSURANUSULAN;
    }

    public void setaNGSURANUSULAN(String aNGSURANUSULAN) {
        this.aNGSURANUSULAN = aNGSURANUSULAN;
    }

    public String getbIAYATOTAL() {
        return bIAYATOTAL;
    }

    public void setbIAYATOTAL(String bIAYATOTAL) {
        this.bIAYATOTAL = bIAYATOTAL;
    }

    public Long getmAKSIMUMPLAFOND() {
        return mAKSIMUMPLAFOND;
    }

    public void setmAKSIMUMPLAFOND(Long mAKSIMUMPLAFOND) {
        this.mAKSIMUMPLAFOND = mAKSIMUMPLAFOND;
    }

    public Long getmAKSIMUMPLAFONDCOUNTER() {
        return mAKSIMUMPLAFONDCOUNTER;
    }

    public void setmAKSIMUMPLAFONDCOUNTER(Long mAKSIMUMPLAFONDCOUNTER) {
        this.mAKSIMUMPLAFONDCOUNTER = mAKSIMUMPLAFONDCOUNTER;
    }

    public String getpOTONGANPERBULAN() {
        return pOTONGANPERBULAN;
    }

    public void setpOTONGANPERBULAN(String pOTONGANPERBULAN) {
        this.pOTONGANPERBULAN = pOTONGANPERBULAN;
    }

    public String getbIAYASEKOLAHANAK() {
        return bIAYASEKOLAHANAK;
    }

    public void setbIAYASEKOLAHANAK(String bIAYASEKOLAHANAK) {
        this.bIAYASEKOLAHANAK = bIAYASEKOLAHANAK;
    }

    public String getoMZETPERBULAN() {
        return oMZETPERBULAN;
    }

    public void setoMZETPERBULAN(String oMZETPERBULAN) {
        this.oMZETPERBULAN = oMZETPERBULAN;
    }

    public String getiDSCORING() {
        return iDSCORING;
    }

    public void setiDSCORING(String iDSCORING) {
        this.iDSCORING = iDSCORING;
    }

    public Long getpENGHASILANTETAPLAIN() {
        return pENGHASILANTETAPLAIN;
    }

    public void setpENGHASILANTETAPLAIN(Long pENGHASILANTETAPLAIN) {
        this.pENGHASILANTETAPLAIN = pENGHASILANTETAPLAIN;
    }

    public String getjANGKAWAKTU() {
        return jANGKAWAKTU;
    }

    public void setjANGKAWAKTU(String jANGKAWAKTU) {
        this.jANGKAWAKTU = jANGKAWAKTU;
    }

    public Long gethARGAPROPERTY() {
        return hARGAPROPERTY;
    }

    public void sethARGAPROPERTY(Long hARGAPROPERTY) {
        this.hARGAPROPERTY = hARGAPROPERTY;
    }

    public String getnETMONTHLYINCOME() {
        return nETMONTHLYINCOME;
    }

    public void setnETMONTHLYINCOME(String nETMONTHLYINCOME) {
        this.nETMONTHLYINCOME = nETMONTHLYINCOME;
    }
}
