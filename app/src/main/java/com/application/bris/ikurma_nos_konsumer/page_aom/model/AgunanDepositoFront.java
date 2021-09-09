package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 11/07/2019.
 */

public class AgunanDepositoFront {
    @SerializedName("STATUS")
    public Integer sTATUS;
    @SerializedName("KATEGORI")
    public String kATEGORI;
    @SerializedName("FID_AGUNAN")
    public Integer fIDAGUNAN;
    @SerializedName("JENIS_DEPOSITO")
    public String jENISDEPOSITO;
    @SerializedName("BANK_PENERBIT")
    public String bANKPENERBIT;
    @SerializedName("NILAI_PASAR")
    public String nILAIPASAR;
    @SerializedName("ID")
    public Integer iD;
    @SerializedName("NAMA_PEMILIK")
    public String nAMAPEMILIK;
    @SerializedName("NO_BILYET")
    public String nOBILYET;

    public Integer getsTATUS() {
        return sTATUS;
    }

    public String getkATEGORI() {
        return kATEGORI;
    }

    public Integer getfIDAGUNAN() {
        return fIDAGUNAN;
    }

    public String getjENISDEPOSITO() {
        return jENISDEPOSITO;
    }

    public String getbANKPENERBIT() {
        return bANKPENERBIT;
    }

    public String getnILAIPASAR() {
        return nILAIPASAR;
    }

    public Integer getiD() {
        return iD;
    }

    public String getnAMAPEMILIK() {
        return nAMAPEMILIK;
    }

    public String getnOBILYET() {
        return nOBILYET;
    }
}
