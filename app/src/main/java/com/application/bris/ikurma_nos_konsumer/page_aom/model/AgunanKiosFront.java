package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 11/07/2019.
 */

public class AgunanKiosFront {
    @SerializedName("STATUS")
    public Integer sTATUS;
    @SerializedName("LOKASI")
    public String lOKASI;
    @SerializedName("KATEGORI")
    public String kATEGORI;
    @SerializedName("FID_AGUNAN")
    public Integer fIDAGUNAN;
    @SerializedName("NAMA_PEMEGANG_HAK")
    public String nAMAPEMEGANGHAK;
    @SerializedName("NILAI_PASAR")
    public String nILAIPASAR;
    @SerializedName("ID")
    public Integer iD;
    @SerializedName("JENIS_JAMINAN")
    public String jENISJAMINAN;
    @SerializedName("DOKUMEN")
    public String dOKUMEN;

    public Integer getsTATUS() {
        return sTATUS;
    }

    public String getlOKASI() {
        return lOKASI;
    }

    public String getkATEGORI() {
        return kATEGORI;
    }

    public Integer getfIDAGUNAN() {
        return fIDAGUNAN;
    }

    public String getnAMAPEMEGANGHAK() {
        return nAMAPEMEGANGHAK;
    }

    public String getnILAIPASAR() {
        return nILAIPASAR;
    }

    public Integer getiD() {
        return iD;
    }

    public String getjENISJAMINAN() {
        return jENISJAMINAN;
    }

    public String getdOKUMEN() {
        return dOKUMEN;
    }
}
