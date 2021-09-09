package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 27/06/2019.
 */

public class DataScoring {
    @SerializedName("KETERGANTUNGAN_PELANGGAN")
    public Long kETERGANTUNGANPELANGGAN;
    @SerializedName("JENIS_PEMBIAYAAN")
    public Long jENISPEMBIAYAAN;
    @SerializedName("LAMA_USAHA")
    public Long lAMAUSAHA;
    @SerializedName("DEVIASI_MIKRO")
    public Long dEVIASIMIKRO;
    @SerializedName("JENIS_PRODUK")
    public Long jENISPRODUK;
    @SerializedName("HUBUNGAN_BANK")
    public Long hUBUNGANBANK;
    @SerializedName("JANGKA_WAKTU")
    public Long jANGKAWAKTU;
    @SerializedName("WILAYAH_PEMASARAN")
    public Long wILAYAHPEMASARAN;
    @SerializedName("pesan")
    public String pesan;
    @SerializedName("RPC_RATIO")
    public Long rPCRATIO;
    @SerializedName("REPUTASI_USAHA")
    public Long rEPUTASIUSAHA;
    @SerializedName("hasil")
    public String hasil;
    @SerializedName("KETERGANTUNGAN_SUPPLIER")
    public Long kETERGANTUNGANSUPPLIER;
    @SerializedName("PROSPEK_USAHA")
    public Long pROSPEKUSAHA;

    @SerializedName("CURRENT_RATIO")
    public Long cURRENTRATIO;
    @SerializedName("PROFITABILITY")
    public Long pROFITABILITY;
    @SerializedName("RATIO_AGUNAN")
    public Long rATIOAGUNAN;


    public DataScoring(Long kETERGANTUNGANPELANGGAN, Long jENISPEMBIAYAAN, Long lAMAUSAHA, Long dEVIASIMIKRO, Long jENISPRODUK, Long hUBUNGANBANK, Long jANGKAWAKTU, Long wILAYAHPEMASARAN, String pesan, Long rPCRATIO, Long rEPUTASIUSAHA, String hasil, Long kETERGANTUNGANSUPPLIER, Long pROSPEKUSAHA, Long cURRENTRATIO, Long pROFITABILITY, Long rATIOAGUNAN) {
        this.kETERGANTUNGANPELANGGAN = kETERGANTUNGANPELANGGAN;
        this.jENISPEMBIAYAAN = jENISPEMBIAYAAN;
        this.lAMAUSAHA = lAMAUSAHA;
        this.dEVIASIMIKRO = dEVIASIMIKRO;
        this.jENISPRODUK = jENISPRODUK;
        this.hUBUNGANBANK = hUBUNGANBANK;
        this.jANGKAWAKTU = jANGKAWAKTU;
        this.wILAYAHPEMASARAN = wILAYAHPEMASARAN;
        this.pesan = pesan;
        this.rPCRATIO = rPCRATIO;
        this.rEPUTASIUSAHA = rEPUTASIUSAHA;
        this.hasil = hasil;
        this.kETERGANTUNGANSUPPLIER = kETERGANTUNGANSUPPLIER;
        this.pROSPEKUSAHA = pROSPEKUSAHA;
        this.cURRENTRATIO = cURRENTRATIO;
        this.pROFITABILITY = pROFITABILITY;
        this.rATIOAGUNAN = rATIOAGUNAN;
    }

    public Long getkETERGANTUNGANPELANGGAN() {
        return kETERGANTUNGANPELANGGAN;
    }

    public Long getjENISPEMBIAYAAN() {
        return jENISPEMBIAYAAN;
    }

    public Long getlAMAUSAHA() {
        return lAMAUSAHA;
    }

    public Long getdEVIASIMIKRO() {
        return dEVIASIMIKRO;
    }

    public Long getjENISPRODUK() {
        return jENISPRODUK;
    }

    public Long gethUBUNGANBANK() {
        return hUBUNGANBANK;
    }

    public Long getjANGKAWAKTU() {
        return jANGKAWAKTU;
    }

    public Long getwILAYAHPEMASARAN() {
        return wILAYAHPEMASARAN;
    }

    public String getPesan() {
        return pesan;
    }

    public Long getrPCRATIO() {
        return rPCRATIO;
    }

    public Long getrEPUTASIUSAHA() {
        return rEPUTASIUSAHA;
    }

    public String getHasil() {
        return hasil;
    }

    public Long getkETERGANTUNGANSUPPLIER() {
        return kETERGANTUNGANSUPPLIER;
    }

    public Long getpROSPEKUSAHA() {
        return pROSPEKUSAHA;
    }

    public Long getcURRENTRATIO() {
        return cURRENTRATIO;
    }

    public Long getpROFITABILITY() {
        return pROFITABILITY;
    }

    public Long getrATIOAGUNAN() {
        return rATIOAGUNAN;
    }
}
