package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 6/23/2019.
 */

public class KelengkapanDokumen {
    @SerializedName("SURAT_PERNYATAAN_NASABAH")
    public Boolean sURATPERNYATAANNASABAH;
    @SerializedName("FC_NPWP_PRIBADI")
    public Boolean fCNPWPPRIBADI;
    @SerializedName("ID_DOKUMEN_APLIKASI")
    public Integer iDDOKUMENAPLIKASI;
    @SerializedName("ID_DOKUMEN_SURAT_NIKAH")
    public Integer iDDOKUMENSURATNIKAH;
    @SerializedName("FC_SURAT_NIKAH")
    public Boolean fCSURATNIKAH;
    @SerializedName("ID_DOKUMEN_KK")
    public Integer iDDOKUMENKK;
    @SerializedName("FC_KTP")
    public Boolean fCKTP;
    @SerializedName("LAPORAN_KEUANGAN")
    public Boolean lAPORANKEUANGAN;
    @SerializedName("PAS_PHOTO")
    public Boolean pASPHOTO;
    @SerializedName("ID_DOKUMEN_KTP")
    public Integer iDDOKUMENKTP;
    @SerializedName("ID_DOKUMEN_SIUP")
    public Integer iDDOKUMENSIUP;
    @SerializedName("APLIKASI_PERMOHONAN")
    public Boolean aPLIKASIPERMOHONAN;
    @SerializedName("ID_DOKUMEN_NPWP_PRIBADI")
    public Integer iDDOKUMENNPWPPRIBADI;
    @SerializedName("ID_DOKUMEN_LAPORAN_KEUANGAN")
    public Integer iDDOKUMENLAPORANKEUANGAN;
    @SerializedName("FC_KK")
    public Boolean fCKK;
    @SerializedName("FC_SIUP")
    public Boolean fCSIUP;
    @SerializedName("ID_DOKUMEN_SURAT_PERNYATAAN_KUR")
    public Integer iDDOKUMENSURATPERNYATAANKUR;
    @SerializedName("ID_DOKUMEN_SURAT_PERNYATAAN_NASABAH")
    public Integer iDDOKUMENSURATPERNYATAANNASABAH;
    @SerializedName("ID_DOKUMEN_SURAT_KETERANGAN_LUNAS_KUR")
    public Integer iDDOKUMENSURATKETERANGANLUNASKUR;
    @SerializedName("SURAT_KETERANGAN_LUNAS_KUR")
    public Boolean sURATKETERANGANLUNASKUR;
    @SerializedName("ID_DOKUMEN_PAS_PHOTO")
    public Integer iDDOKUMENPASPHOTO;
    @SerializedName("SURAT_PERNYATAAN_KUR")
    public Boolean sURATPERNYATAANKUR;
    @SerializedName("NO_SKU")
    public String nOSKU;

    public KelengkapanDokumen(Boolean sURATPERNYATAANNASABAH, Boolean fCNPWPPRIBADI, Integer iDDOKUMENAPLIKASI, Integer iDDOKUMENSURATNIKAH, Boolean fCSURATNIKAH, Integer iDDOKUMENKK, Boolean fCKTP, Boolean lAPORANKEUANGAN, Boolean pASPHOTO, Integer iDDOKUMENKTP, Integer iDDOKUMENSIUP, Boolean aPLIKASIPERMOHONAN, Integer iDDOKUMENNPWPPRIBADI, Integer iDDOKUMENLAPORANKEUANGAN, Boolean fCKK, Boolean fCSIUP, Integer iDDOKUMENSURATPERNYATAANKUR, Integer iDDOKUMENSURATPERNYATAANNASABAH, Integer iDDOKUMENSURATKETERANGANLUNASKUR, Boolean sURATKETERANGANLUNASKUR, Integer iDDOKUMENPASPHOTO, Boolean sURATPERNYATAANKUR, String nOSKU) {
        this.sURATPERNYATAANNASABAH = sURATPERNYATAANNASABAH;
        this.fCNPWPPRIBADI = fCNPWPPRIBADI;
        this.iDDOKUMENAPLIKASI = iDDOKUMENAPLIKASI;
        this.iDDOKUMENSURATNIKAH = iDDOKUMENSURATNIKAH;
        this.fCSURATNIKAH = fCSURATNIKAH;
        this.iDDOKUMENKK = iDDOKUMENKK;
        this.fCKTP = fCKTP;
        this.lAPORANKEUANGAN = lAPORANKEUANGAN;
        this.pASPHOTO = pASPHOTO;
        this.iDDOKUMENKTP = iDDOKUMENKTP;
        this.iDDOKUMENSIUP = iDDOKUMENSIUP;
        this.aPLIKASIPERMOHONAN = aPLIKASIPERMOHONAN;
        this.iDDOKUMENNPWPPRIBADI = iDDOKUMENNPWPPRIBADI;
        this.iDDOKUMENLAPORANKEUANGAN = iDDOKUMENLAPORANKEUANGAN;
        this.fCKK = fCKK;
        this.fCSIUP = fCSIUP;
        this.iDDOKUMENSURATPERNYATAANKUR = iDDOKUMENSURATPERNYATAANKUR;
        this.iDDOKUMENSURATPERNYATAANNASABAH = iDDOKUMENSURATPERNYATAANNASABAH;
        this.iDDOKUMENSURATKETERANGANLUNASKUR = iDDOKUMENSURATKETERANGANLUNASKUR;
        this.sURATKETERANGANLUNASKUR = sURATKETERANGANLUNASKUR;
        this.iDDOKUMENPASPHOTO = iDDOKUMENPASPHOTO;
        this.sURATPERNYATAANKUR = sURATPERNYATAANKUR;
        this.nOSKU = nOSKU;
    }

    public Boolean getsURATPERNYATAANNASABAH() {
        return sURATPERNYATAANNASABAH;
    }

    public Boolean getfCNPWPPRIBADI() {
        return fCNPWPPRIBADI;
    }

    public Integer getiDDOKUMENAPLIKASI() {
        return iDDOKUMENAPLIKASI;
    }

    public Integer getiDDOKUMENSURATNIKAH() {
        return iDDOKUMENSURATNIKAH;
    }

    public Boolean getfCSURATNIKAH() {
        return fCSURATNIKAH;
    }

    public Integer getiDDOKUMENKK() {
        return iDDOKUMENKK;
    }

    public Boolean getfCKTP() {
        return fCKTP;
    }

    public Boolean getlAPORANKEUANGAN() {
        return lAPORANKEUANGAN;
    }

    public Boolean getpASPHOTO() {
        return pASPHOTO;
    }

    public Integer getiDDOKUMENKTP() {
        return iDDOKUMENKTP;
    }

    public Integer getiDDOKUMENSIUP() {
        return iDDOKUMENSIUP;
    }

    public Boolean getaPLIKASIPERMOHONAN() {
        return aPLIKASIPERMOHONAN;
    }

    public Integer getiDDOKUMENNPWPPRIBADI() {
        return iDDOKUMENNPWPPRIBADI;
    }

    public Integer getiDDOKUMENLAPORANKEUANGAN() {
        return iDDOKUMENLAPORANKEUANGAN;
    }

    public Boolean getfCKK() {
        return fCKK;
    }

    public Boolean getfCSIUP() {
        return fCSIUP;
    }

    public Integer getiDDOKUMENSURATPERNYATAANKUR() {
        return iDDOKUMENSURATPERNYATAANKUR;
    }

    public Integer getiDDOKUMENSURATPERNYATAANNASABAH() {
        return iDDOKUMENSURATPERNYATAANNASABAH;
    }

    public Integer getiDDOKUMENSURATKETERANGANLUNASKUR() {
        return iDDOKUMENSURATKETERANGANLUNASKUR;
    }

    public Boolean getsURATKETERANGANLUNASKUR() {
        return sURATKETERANGANLUNASKUR;
    }

    public Integer getiDDOKUMENPASPHOTO() {
        return iDDOKUMENPASPHOTO;
    }

    public Boolean getsURATPERNYATAANKUR() {
        return sURATPERNYATAANKUR;
    }

    public String getnOSKU() {
        return nOSKU;
    }
}
