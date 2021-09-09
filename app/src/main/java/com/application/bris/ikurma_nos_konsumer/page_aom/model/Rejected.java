package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 09/07/2019.
 */

public class Rejected {
    @SerializedName("FID_CIF_LAS")
    public Integer fIDCIFLAS;
    @SerializedName("ID_ST_APLIKASI")
    public Integer iDSTAPLIKASI;
    @SerializedName("KODE_PRODUK")
    public String kODEPRODUK;
    @SerializedName("PLAFOND_INDUK")
    public Long pLAFONDINDUK;
    @SerializedName("KLASIFIKASI_KREDIT")
    public String kLASIFIKASIKREDIT;
    @SerializedName("STATUS_APLIKASI")
    public String sTATUSAPLIKASI;
    @SerializedName("NAMA_PRODUK")
    public String nAMAPRODUK;
    @SerializedName("ID_APLIKASI")
    public Integer iDAPLIKASI;
    @SerializedName("TANGGAL_ENTRY")
    public String tANGGALENTRY;
    @SerializedName("FID_PHOTO")
    public Integer fIDPHOTO;
    @SerializedName("JW")
    public Integer jW;
    @SerializedName("NAMA_DEBITUR_1")
    public String nAMADEBITUR1;

    public Rejected(Integer fIDCIFLAS, Integer iDSTAPLIKASI, String kODEPRODUK, Long pLAFONDINDUK, String kLASIFIKASIKREDIT, String sTATUSAPLIKASI, String nAMAPRODUK, Integer iDAPLIKASI, String tANGGALENTRY, Integer fIDPHOTO, Integer jW, String nAMADEBITUR1) {
        this.fIDCIFLAS = fIDCIFLAS;
        this.iDSTAPLIKASI = iDSTAPLIKASI;
        this.kODEPRODUK = kODEPRODUK;
        this.pLAFONDINDUK = pLAFONDINDUK;
        this.kLASIFIKASIKREDIT = kLASIFIKASIKREDIT;
        this.sTATUSAPLIKASI = sTATUSAPLIKASI;
        this.nAMAPRODUK = nAMAPRODUK;
        this.iDAPLIKASI = iDAPLIKASI;
        this.tANGGALENTRY = tANGGALENTRY;
        this.fIDPHOTO = fIDPHOTO;
        this.jW = jW;
        this.nAMADEBITUR1 = nAMADEBITUR1;
    }

    public Integer getfIDCIFLAS() {
        return fIDCIFLAS;
    }

    public Integer getiDSTAPLIKASI() {
        return iDSTAPLIKASI;
    }

    public String getkODEPRODUK() {
        return kODEPRODUK;
    }

    public Long getpLAFONDINDUK() {
        return pLAFONDINDUK;
    }

    public String getkLASIFIKASIKREDIT() {
        return kLASIFIKASIKREDIT;
    }

    public String getsTATUSAPLIKASI() {
        return sTATUSAPLIKASI;
    }

    public String getnAMAPRODUK() {
        return nAMAPRODUK;
    }

    public Integer getiDAPLIKASI() {
        return iDAPLIKASI;
    }

    public String gettANGGALENTRY() {
        return tANGGALENTRY;
    }

    public Integer getfIDPHOTO() {
        return fIDPHOTO;
    }

    public Integer getjW() {
        return jW;
    }

    public String getnAMADEBITUR1() {
        return nAMADEBITUR1;
    }
}
