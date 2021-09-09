package com.application.bris.ikurma_nos_konsumer.database.pojo;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by idong on 26/06/2019.
 */

public class LknPojo extends RealmObject {

    @PrimaryKey
    @SerializedName("uuid")
    private String uuid;
    @SerializedName("FID_APLIKASI")
    public String fIDAPLIKASI;
    @SerializedName("FID_CIF_LAS")
    public String fIDCIFLAS;
    @SerializedName("TANGGAL_PENILAIAN")
    public String tANGGALPENILAIAN;
    @SerializedName("NAMA_ORANG_DITEMUI")
    public String nAMAORANGDITEMUI;
    @SerializedName("STATUS_PERMOHONAN")
    public String sTATUSPERMOHONAN;
    @SerializedName("HUBUNGAN")
    public String hUBUNGAN;
    @SerializedName("NAMA_USAHA")
    public String nAMAUSAHA;
    @SerializedName("BIDANG_USAHA")
    public String bIDANGUSAHA;
    @SerializedName("ALAMAT_USAHA")
    public String aLAMATUSAHA;
    @SerializedName("TELEPON")
    public String tELEPON;
    @SerializedName("LAMA_USAHA")
    public Long lAMAUSAHA;
    @SerializedName("STATUS_TEMPAT_USAHA")
    public String sTATUSTEMPATUSAHA;
    @SerializedName("JENIS_TEMPAT_USAHA")
    public String jENISTEMPATUSAHA;
    @SerializedName("ASPEK_PEMASARAN")
    public String aSPEKPEMASARAN;
    @SerializedName("JENIS_USAHA")
    public String jENISUSAHA;
    @SerializedName("LOKASI_USAHA")
    public String lOKASIUSAHA;
    @SerializedName("JARAK_LOKASI")
    public Long jARAKLOKASI;
    @SerializedName("FID_PHOTO_DEPAN")
    public int fIDPHOTODEPAN;
    @SerializedName("FID_PHOTO_DALAM")
    public int fIDPHOTODALAM;
    @SerializedName("FID_PHOTO_LINGKUNGAN")
    public int fIDPHOTOLINGKUNGAN;
    @SerializedName("PENDAPATAN_USAHA")
    public String pENDAPATANUSAHA;
    @SerializedName("HARGA_POKOK_PENJUALAN")
    public String hARGAPOKOKPENJUALAN;
    @SerializedName("HARGA_SEWA")
    public String hARGASEWA;
    @SerializedName("GAJI_PEGAWAI")
    public String gAJIPEGAWAI;
    @SerializedName("BIAYA_TELEPON_LISTRIK")
    public String bIAYATELEPONLISTRIK;
    @SerializedName("BIAYA_TRANSPORTASI")
    public String bIAYATRANSPORTASI;
    @SerializedName("PENGELUARAN_LAINNYA")
    public String pENGELUARANLAINNYA;
    @SerializedName("PENGELUARAN_USAHA")
    public String pENGELUARANUSAHA;
    @SerializedName("KEUNTUNGAN_USAHA")
    public String kEUNTUNGANUSAHA;
    @SerializedName("PENGHASILAN_LAINNYA")
    public String pENGHASILANLAINNYA;
    @SerializedName("TOTAL_PENGHASILAN")
    public String tOTALPENGHASILAN;
    @SerializedName("PAJAK")
    public String pAJAK;
    @SerializedName("BELANJA_RT")
    public String bELANJART;
    @SerializedName("BIAYA_SEWA_RUMAH_RT")
    public String bIAYASEWARUMAHRT;
    @SerializedName("BIAYA_PENDIDIKAN")
    public String bIAYAPENDIDIKAN;
    @SerializedName("BIAYA_TELEPON_RT")
    public String bIAYATELEPONRT;
    @SerializedName("BIAYA_TRANSPORTASI_RT")
    public String bIAYATRANSPORTASIRT;
    @SerializedName("PENGELUARAN_LAINNYA_RT")
    public String pENGELUARANLAINNYART;
    @SerializedName("TOTAL_PENGELUARAN_RT")
    public String tOTALPENGELUARANRT;
    @SerializedName("SISA_PENGHASILAN")
    public String sISAPENGHASILAN;
    @SerializedName("ANGSURAN_EXISTING")
    public String aNGSURANEXISTING;
    @SerializedName("INVENTORY")
    public String iNVENTORY;
    @SerializedName("PIUTANG_DAGANG")
    public String pIUTANGDAGANG;
    @SerializedName("UTANG_DAGANG")
    public String uTANGDAGANG;
    @SerializedName("WI_NERACA")
    public String wINERACA;
    @SerializedName("PERPUTARAN_PERSEDIAAN")
    public Long pERPUTARANPERSEDIAAN;
    @SerializedName("PERPUTARAN_PIUTANG")
    public Long pERPUTARANPIUTANG;
    @SerializedName("PERPUTARAN_UTANG")
    public Long pERPUTARANUTANG;
    @SerializedName("KEBUTUHAN_MODAL")
    public Long kEBUTUHANMODAL;
    @SerializedName("WI_NORMAL")
    public String wINORMAL;
    @SerializedName("PEMBIAYAAN_PRODUKTIF1")
    public String pEMBIAYAANPRODUKTIF1;
    @SerializedName("PEMBIAYAAN_PRODUKTIF2")
    public String pEMBIAYAANPRODUKTIF2;
    @SerializedName("PEMBIAYAAN_KONSUMTIF")
    public String pEMBIAYAANKONSUMTIF;
    @SerializedName("JW_PRODUKTIF1")
    public String jWPRODUKTIF1;
    @SerializedName("JW_PRODUKTIF2")
    public String jWPRODUKTIF2;
    @SerializedName("JW_KONSUMTIF")
    public String jWKONSUMTIF;
    @SerializedName("TOTAL_PEMBIAYAAN")
    public String tOTALPEMBIAYAAN;
    @SerializedName("MARGIN_FLAT_BULAN")
    public String mARGINFLATBULAN;
    @SerializedName("MARGIN_EFEKTIF_PRODUKTIF1")
    public String mARGINEFEKTIFPRODUKTIF1;
    @SerializedName("MARGIN_EFEKTIF_PRODUKTIF2")
    public String mARGINEFEKTIFPRODUKTIF2;
    @SerializedName("MARGIN_EFEKTIF_KONSUMTIF")
    public String mARGINEFEKTIFKONSUMTIF;
    @SerializedName("REKOMENDASI_PUTUSAN")
    public String rEKOMENDASIPUTUSAN;
    @SerializedName("PEMBIAYAAN_TAKEOVER")
    public String pEMBIAYAANTAKEOVER;
    @SerializedName("JW_TAKEOVER")
    public String jWTAKEOVER;
    @SerializedName("MARGIN_EFEKTIF_TAKEOVER")
    public String mARGINEFEKTIFTAKEOVER;
    @SerializedName("JATUH_TEMPO_QARDH")
    public String jATUHTEMPOQARDH;
    @SerializedName("JENIS_ANGSURAN")
    public String jENISANGSURAN;
    @SerializedName("INTERVAL_IRREGULAR")
    public String iNTERVALIRREGULAR;
    @SerializedName("DOH_UTANG")
    public String dOHUTANG;
    @SerializedName("DOH_PIUTANG")
    public String dOHPIUTANG;
    @SerializedName("DOH_INVENTORY")
    public String dOHINVENTORY;
    @SerializedName("GPM")
    public String gPM;
    @SerializedName("REKOMENDASI_ANGSURAN")
    public String rEKOMENDASIANGSURAN;

    public LknPojo(){
        super();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getfIDAPLIKASI() {
        return fIDAPLIKASI;
    }

    public void setfIDAPLIKASI(String fIDAPLIKASI) {
        this.fIDAPLIKASI = fIDAPLIKASI;
    }

    public String getfIDCIFLAS() {
        return fIDCIFLAS;
    }

    public void setfIDCIFLAS(String fIDCIFLAS) {
        this.fIDCIFLAS = fIDCIFLAS;
    }

    public String gettANGGALPENILAIAN() {
        return tANGGALPENILAIAN;
    }

    public void settANGGALPENILAIAN(String tANGGALPENILAIAN) {
        this.tANGGALPENILAIAN = tANGGALPENILAIAN;
    }

    public String getnAMAORANGDITEMUI() {
        return nAMAORANGDITEMUI;
    }

    public void setnAMAORANGDITEMUI(String nAMAORANGDITEMUI) {
        this.nAMAORANGDITEMUI = nAMAORANGDITEMUI;
    }

    public String getsTATUSPERMOHONAN() {
        return sTATUSPERMOHONAN;
    }

    public void setsTATUSPERMOHONAN(String sTATUSPERMOHONAN) {
        this.sTATUSPERMOHONAN = sTATUSPERMOHONAN;
    }

    public String gethUBUNGAN() {
        return hUBUNGAN;
    }

    public void sethUBUNGAN(String hUBUNGAN) {
        this.hUBUNGAN = hUBUNGAN;
    }

    public String getnAMAUSAHA() {
        return nAMAUSAHA;
    }

    public void setnAMAUSAHA(String nAMAUSAHA) {
        this.nAMAUSAHA = nAMAUSAHA;
    }

    public String getbIDANGUSAHA() {
        return bIDANGUSAHA;
    }

    public void setbIDANGUSAHA(String bIDANGUSAHA) {
        this.bIDANGUSAHA = bIDANGUSAHA;
    }

    public String getaLAMATUSAHA() {
        return aLAMATUSAHA;
    }

    public void setaLAMATUSAHA(String aLAMATUSAHA) {
        this.aLAMATUSAHA = aLAMATUSAHA;
    }

    public String gettELEPON() {
        return tELEPON;
    }

    public void settELEPON(String tELEPON) {
        this.tELEPON = tELEPON;
    }

    public Long getlAMAUSAHA() {
        return lAMAUSAHA;
    }

    public void setlAMAUSAHA(Long lAMAUSAHA) {
        this.lAMAUSAHA = lAMAUSAHA;
    }

    public String getsTATUSTEMPATUSAHA() {
        return sTATUSTEMPATUSAHA;
    }

    public void setsTATUSTEMPATUSAHA(String sTATUSTEMPATUSAHA) {
        this.sTATUSTEMPATUSAHA = sTATUSTEMPATUSAHA;
    }

    public String getjENISTEMPATUSAHA() {
        return jENISTEMPATUSAHA;
    }

    public void setjENISTEMPATUSAHA(String jENISTEMPATUSAHA) {
        this.jENISTEMPATUSAHA = jENISTEMPATUSAHA;
    }

    public String getaSPEKPEMASARAN() {
        return aSPEKPEMASARAN;
    }

    public void setaSPEKPEMASARAN(String aSPEKPEMASARAN) {
        this.aSPEKPEMASARAN = aSPEKPEMASARAN;
    }

    public String getjENISUSAHA() {
        return jENISUSAHA;
    }

    public void setjENISUSAHA(String jENISUSAHA) {
        this.jENISUSAHA = jENISUSAHA;
    }

    public String getlOKASIUSAHA() {
        return lOKASIUSAHA;
    }

    public void setlOKASIUSAHA(String lOKASIUSAHA) {
        this.lOKASIUSAHA = lOKASIUSAHA;
    }

    public Long getjARAKLOKASI() {
        return jARAKLOKASI;
    }

    public void setjARAKLOKASI(Long jARAKLOKASI) {
        this.jARAKLOKASI = jARAKLOKASI;
    }


    public int getfIDPHOTODEPAN() {
        return fIDPHOTODEPAN;
    }

    public void setfIDPHOTODEPAN(int fIDPHOTODEPAN) {
        this.fIDPHOTODEPAN = fIDPHOTODEPAN;
    }

    public int getfIDPHOTODALAM() {
        return fIDPHOTODALAM;
    }

    public void setfIDPHOTODALAM(int fIDPHOTODALAM) {
        this.fIDPHOTODALAM = fIDPHOTODALAM;
    }

    public int getfIDPHOTOLINGKUNGAN() {
        return fIDPHOTOLINGKUNGAN;
    }

    public void setfIDPHOTOLINGKUNGAN(int fIDPHOTOLINGKUNGAN) {
        this.fIDPHOTOLINGKUNGAN = fIDPHOTOLINGKUNGAN;
    }

    public String getpENDAPATANUSAHA() {
        return pENDAPATANUSAHA;
    }

    public void setpENDAPATANUSAHA(String pENDAPATANUSAHA) {
        this.pENDAPATANUSAHA = pENDAPATANUSAHA;
    }

    public String gethARGAPOKOKPENJUALAN() {
        return hARGAPOKOKPENJUALAN;
    }

    public void sethARGAPOKOKPENJUALAN(String hARGAPOKOKPENJUALAN) {
        this.hARGAPOKOKPENJUALAN = hARGAPOKOKPENJUALAN;
    }

    public String gethARGASEWA() {
        return hARGASEWA;
    }

    public void sethARGASEWA(String hARGASEWA) {
        this.hARGASEWA = hARGASEWA;
    }

    public String getgAJIPEGAWAI() {
        return gAJIPEGAWAI;
    }

    public void setgAJIPEGAWAI(String gAJIPEGAWAI) {
        this.gAJIPEGAWAI = gAJIPEGAWAI;
    }

    public String getbIAYATELEPONLISTRIK() {
        return bIAYATELEPONLISTRIK;
    }

    public void setbIAYATELEPONLISTRIK(String bIAYATELEPONLISTRIK) {
        this.bIAYATELEPONLISTRIK = bIAYATELEPONLISTRIK;
    }

    public String getbIAYATRANSPORTASI() {
        return bIAYATRANSPORTASI;
    }

    public void setbIAYATRANSPORTASI(String bIAYATRANSPORTASI) {
        this.bIAYATRANSPORTASI = bIAYATRANSPORTASI;
    }

    public String getpENGELUARANLAINNYA() {
        return pENGELUARANLAINNYA;
    }

    public void setpENGELUARANLAINNYA(String pENGELUARANLAINNYA) {
        this.pENGELUARANLAINNYA = pENGELUARANLAINNYA;
    }

    public String getpENGELUARANUSAHA() {
        return pENGELUARANUSAHA;
    }

    public void setpENGELUARANUSAHA(String pENGELUARANUSAHA) {
        this.pENGELUARANUSAHA = pENGELUARANUSAHA;
    }

    public String getkEUNTUNGANUSAHA() {
        return kEUNTUNGANUSAHA;
    }

    public void setkEUNTUNGANUSAHA(String kEUNTUNGANUSAHA) {
        this.kEUNTUNGANUSAHA = kEUNTUNGANUSAHA;
    }

    public String getpENGHASILANLAINNYA() {
        return pENGHASILANLAINNYA;
    }

    public void setpENGHASILANLAINNYA(String pENGHASILANLAINNYA) {
        this.pENGHASILANLAINNYA = pENGHASILANLAINNYA;
    }

    public String gettOTALPENGHASILAN() {
        return tOTALPENGHASILAN;
    }

    public void settOTALPENGHASILAN(String tOTALPENGHASILAN) {
        this.tOTALPENGHASILAN = tOTALPENGHASILAN;
    }

    public String getpAJAK() {
        return pAJAK;
    }

    public void setpAJAK(String pAJAK) {
        this.pAJAK = pAJAK;
    }

    public String getbELANJART() {
        return bELANJART;
    }

    public void setbELANJART(String bELANJART) {
        this.bELANJART = bELANJART;
    }

    public String getbIAYASEWARUMAHRT() {
        return bIAYASEWARUMAHRT;
    }

    public void setbIAYASEWARUMAHRT(String bIAYASEWARUMAHRT) {
        this.bIAYASEWARUMAHRT = bIAYASEWARUMAHRT;
    }

    public String getbIAYAPENDIDIKAN() {
        return bIAYAPENDIDIKAN;
    }

    public void setbIAYAPENDIDIKAN(String bIAYAPENDIDIKAN) {
        this.bIAYAPENDIDIKAN = bIAYAPENDIDIKAN;
    }

    public String getbIAYATELEPONRT() {
        return bIAYATELEPONRT;
    }

    public void setbIAYATELEPONRT(String bIAYATELEPONRT) {
        this.bIAYATELEPONRT = bIAYATELEPONRT;
    }

    public String getbIAYATRANSPORTASIRT() {
        return bIAYATRANSPORTASIRT;
    }

    public void setbIAYATRANSPORTASIRT(String bIAYATRANSPORTASIRT) {
        this.bIAYATRANSPORTASIRT = bIAYATRANSPORTASIRT;
    }

    public String getpENGELUARANLAINNYART() {
        return pENGELUARANLAINNYART;
    }

    public void setpENGELUARANLAINNYART(String pENGELUARANLAINNYART) {
        this.pENGELUARANLAINNYART = pENGELUARANLAINNYART;
    }

    public String gettOTALPENGELUARANRT() {
        return tOTALPENGELUARANRT;
    }

    public void settOTALPENGELUARANRT(String tOTALPENGELUARANRT) {
        this.tOTALPENGELUARANRT = tOTALPENGELUARANRT;
    }

    public String getsISAPENGHASILAN() {
        return sISAPENGHASILAN;
    }

    public void setsISAPENGHASILAN(String sISAPENGHASILAN) {
        this.sISAPENGHASILAN = sISAPENGHASILAN;
    }

    public String getaNGSURANEXISTING() {
        return aNGSURANEXISTING;
    }

    public void setaNGSURANEXISTING(String aNGSURANEXISTING) {
        this.aNGSURANEXISTING = aNGSURANEXISTING;
    }

    public String getiNVENTORY() {
        return iNVENTORY;
    }

    public void setiNVENTORY(String iNVENTORY) {
        this.iNVENTORY = iNVENTORY;
    }

    public String getpIUTANGDAGANG() {
        return pIUTANGDAGANG;
    }

    public void setpIUTANGDAGANG(String pIUTANGDAGANG) {
        this.pIUTANGDAGANG = pIUTANGDAGANG;
    }

    public String getuTANGDAGANG() {
        return uTANGDAGANG;
    }

    public void setuTANGDAGANG(String uTANGDAGANG) {
        this.uTANGDAGANG = uTANGDAGANG;
    }

    public String getwINERACA() {
        return wINERACA;
    }

    public void setwINERACA(String wINERACA) {
        this.wINERACA = wINERACA;
    }

    public Long getpERPUTARANPERSEDIAAN() {
        return pERPUTARANPERSEDIAAN;
    }

    public void setpERPUTARANPERSEDIAAN(Long pERPUTARANPERSEDIAAN) {
        this.pERPUTARANPERSEDIAAN = pERPUTARANPERSEDIAAN;
    }

    public Long getpERPUTARANPIUTANG() {
        return pERPUTARANPIUTANG;
    }

    public void setpERPUTARANPIUTANG(Long pERPUTARANPIUTANG) {
        this.pERPUTARANPIUTANG = pERPUTARANPIUTANG;
    }

    public Long getpERPUTARANUTANG() {
        return pERPUTARANUTANG;
    }

    public void setpERPUTARANUTANG(Long pERPUTARANUTANG) {
        this.pERPUTARANUTANG = pERPUTARANUTANG;
    }

    public Long getkEBUTUHANMODAL() {
        return kEBUTUHANMODAL;
    }

    public void setkEBUTUHANMODAL(Long kEBUTUHANMODAL) {
        this.kEBUTUHANMODAL = kEBUTUHANMODAL;
    }

    public String getwINORMAL() {
        return wINORMAL;
    }

    public void setwINORMAL(String wINORMAL) {
        this.wINORMAL = wINORMAL;
    }

    public String getpEMBIAYAANPRODUKTIF1() {
        return pEMBIAYAANPRODUKTIF1;
    }

    public void setpEMBIAYAANPRODUKTIF1(String pEMBIAYAANPRODUKTIF1) {
        this.pEMBIAYAANPRODUKTIF1 = pEMBIAYAANPRODUKTIF1;
    }

    public String getpEMBIAYAANPRODUKTIF2() {
        return pEMBIAYAANPRODUKTIF2;
    }

    public void setpEMBIAYAANPRODUKTIF2(String pEMBIAYAANPRODUKTIF2) {
        this.pEMBIAYAANPRODUKTIF2 = pEMBIAYAANPRODUKTIF2;
    }

    public String getpEMBIAYAANKONSUMTIF() {
        return pEMBIAYAANKONSUMTIF;
    }

    public void setpEMBIAYAANKONSUMTIF(String pEMBIAYAANKONSUMTIF) {
        this.pEMBIAYAANKONSUMTIF = pEMBIAYAANKONSUMTIF;
    }

    public String getjWPRODUKTIF1() {
        return jWPRODUKTIF1;
    }

    public void setjWPRODUKTIF1(String jWPRODUKTIF1) {
        this.jWPRODUKTIF1 = jWPRODUKTIF1;
    }

    public String getjWPRODUKTIF2() {
        return jWPRODUKTIF2;
    }

    public void setjWPRODUKTIF2(String jWPRODUKTIF2) {
        this.jWPRODUKTIF2 = jWPRODUKTIF2;
    }

    public String getjWKONSUMTIF() {
        return jWKONSUMTIF;
    }

    public void setjWKONSUMTIF(String jWKONSUMTIF) {
        this.jWKONSUMTIF = jWKONSUMTIF;
    }

    public String gettOTALPEMBIAYAAN() {
        return tOTALPEMBIAYAAN;
    }

    public void settOTALPEMBIAYAAN(String tOTALPEMBIAYAAN) {
        this.tOTALPEMBIAYAAN = tOTALPEMBIAYAAN;
    }

    public String getmARGINFLATBULAN() {
        return mARGINFLATBULAN;
    }

    public void setmARGINFLATBULAN(String mARGINFLATBULAN) {
        this.mARGINFLATBULAN = mARGINFLATBULAN;
    }

    public String getmARGINEFEKTIFPRODUKTIF1() {
        return mARGINEFEKTIFPRODUKTIF1;
    }

    public void setmARGINEFEKTIFPRODUKTIF1(String mARGINEFEKTIFPRODUKTIF1) {
        this.mARGINEFEKTIFPRODUKTIF1 = mARGINEFEKTIFPRODUKTIF1;
    }

    public String getmARGINEFEKTIFPRODUKTIF2() {
        return mARGINEFEKTIFPRODUKTIF2;
    }

    public void setmARGINEFEKTIFPRODUKTIF2(String mARGINEFEKTIFPRODUKTIF2) {
        this.mARGINEFEKTIFPRODUKTIF2 = mARGINEFEKTIFPRODUKTIF2;
    }

    public String getmARGINEFEKTIFKONSUMTIF() {
        return mARGINEFEKTIFKONSUMTIF;
    }

    public void setmARGINEFEKTIFKONSUMTIF(String mARGINEFEKTIFKONSUMTIF) {
        this.mARGINEFEKTIFKONSUMTIF = mARGINEFEKTIFKONSUMTIF;
    }

    public String getrEKOMENDASIPUTUSAN() {
        return rEKOMENDASIPUTUSAN;
    }

    public void setrEKOMENDASIPUTUSAN(String rEKOMENDASIPUTUSAN) {
        this.rEKOMENDASIPUTUSAN = rEKOMENDASIPUTUSAN;
    }

    public String getpEMBIAYAANTAKEOVER() {
        return pEMBIAYAANTAKEOVER;
    }

    public void setpEMBIAYAANTAKEOVER(String pEMBIAYAANTAKEOVER) {
        this.pEMBIAYAANTAKEOVER = pEMBIAYAANTAKEOVER;
    }

    public String getjWTAKEOVER() {
        return jWTAKEOVER;
    }

    public void setjWTAKEOVER(String jWTAKEOVER) {
        this.jWTAKEOVER = jWTAKEOVER;
    }

    public String getmARGINEFEKTIFTAKEOVER() {
        return mARGINEFEKTIFTAKEOVER;
    }

    public void setmARGINEFEKTIFTAKEOVER(String mARGINEFEKTIFTAKEOVER) {
        this.mARGINEFEKTIFTAKEOVER = mARGINEFEKTIFTAKEOVER;
    }

    public String getjATUHTEMPOQARDH() {
        return jATUHTEMPOQARDH;
    }

    public void setjATUHTEMPOQARDH(String jATUHTEMPOQARDH) {
        this.jATUHTEMPOQARDH = jATUHTEMPOQARDH;
    }

    public String getjENISANGSURAN() {
        return jENISANGSURAN;
    }

    public void setjENISANGSURAN(String jENISANGSURAN) {
        this.jENISANGSURAN = jENISANGSURAN;
    }

    public String getiNTERVALIRREGULAR() {
        return iNTERVALIRREGULAR;
    }

    public void setiNTERVALIRREGULAR(String iNTERVALIRREGULAR) {
        this.iNTERVALIRREGULAR = iNTERVALIRREGULAR;
    }

    public String getdOHUTANG() {
        return dOHUTANG;
    }

    public void setdOHUTANG(String dOHUTANG) {
        this.dOHUTANG = dOHUTANG;
    }

    public String getdOHPIUTANG() {
        return dOHPIUTANG;
    }

    public void setdOHPIUTANG(String dOHPIUTANG) {
        this.dOHPIUTANG = dOHPIUTANG;
    }

    public String getdOHINVENTORY() {
        return dOHINVENTORY;
    }

    public void setdOHINVENTORY(String dOHINVENTORY) {
        this.dOHINVENTORY = dOHINVENTORY;
    }

    public String getgPM() {
        return gPM;
    }

    public void setgPM(String gPM) {
        this.gPM = gPM;
    }

    public String getrEKOMENDASIANGSURAN() {
        return rEKOMENDASIANGSURAN;
    }

    public void setrEKOMENDASIANGSURAN(String rEKOMENDASIANGSURAN) {
        this.rEKOMENDASIANGSURAN = rEKOMENDASIANGSURAN;
    }
}
