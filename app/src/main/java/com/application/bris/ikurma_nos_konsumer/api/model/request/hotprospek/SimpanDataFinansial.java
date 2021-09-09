package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimpanDataFinansial {

    @SerializedName("idAplikasi")
    @Expose
    private String idAplikasi;
    @SerializedName("idCif")
    @Expose
    private String idCif;
    @SerializedName("typeProduk")
    @Expose
    private String typeProduk;
    @SerializedName("indexNpw")
    @Expose
    private String indexNpw;
    @SerializedName("idPrescoring")
    @Expose
    private int idPrescoring;
    @SerializedName("penghasilanLain")
    @Expose
    private Long penghasilanLain;
    @SerializedName("angsuran")
    @Expose
    private Long angsuran;
    @SerializedName("sukuMargin")
    @Expose
    private Double sukuMargin;
    @SerializedName("sukuMarginKonsumtif")
    @Expose
    private Double sukuMarginKonsumtif;
    @SerializedName("JANGKA_WAKTU")
    @Expose
    private int jANGKAWAKTU;
    @SerializedName("JANGKA_WAKTU_TO")
    @Expose
    private int JANGKA_WAKTU_TO;
    @SerializedName("MAKSIMUM_PLAFOND")
    @Expose
    private Long mAKSIMUMPLAFOND;
    @SerializedName("INPUT_PERMOHONAN")
    @Expose
    private Long iNPUTPERMOHONAN;
    @SerializedName("PlafondUsul")
    @Expose
    private Long plafondUsul;
    @SerializedName("PlafondKonsumtif")
    @Expose
    private Long PlafondKonsumtif;
    @SerializedName("OMZET_PERBULAN")
    @Expose
    private String oMZETPERBULAN;
    @SerializedName("omzetSetelahPotongan")
    @Expose
    private String omzetSetelahPotongan;
    @SerializedName("omzetBersih")
    @Expose
    private String omzetBersih;
    @SerializedName("hargaBeli")
    @Expose
    private Long hargaBeli;
    @SerializedName("besarUangMuka")
    @Expose
    private String besarUangMuka;
    @SerializedName("idScoring")
    @Expose
    private int idScoring;
    @SerializedName("netIncome")
    @Expose
    private String netIncome;
    @SerializedName("reqLoanAmount")
    @Expose
    private String reqLoanAmount;
    @SerializedName("reqPeriode")
    @Expose
    private String reqPeriode;
    @SerializedName("sukuMarginBulanan")
    @Expose
    private Double sukuMarginBulanan;
    @SerializedName("angsuranScoring")
    @Expose
    private String angsuranScoring;
    @SerializedName("angsuranKonsumtif")
    @Expose
    private Long angsuranKonsumtif;
    @SerializedName("deviasiDir")
    @Expose
    private String deviasiDir;
    @SerializedName("ltv")
    @Expose
    private String ltv;
    @SerializedName("namaAOS")
    @Expose
    private String namaAOS;
    @SerializedName("uidAOS")
    @Expose
    private String uidAOS;
    @SerializedName("kewajibanLain")
    @Expose
    private Long kewajibanLain;

    @SerializedName("idTujuan")
    @Expose
    private String idTujuan;

    @SerializedName("ijk")
    @Expose
    private String ijk;

    @SerializedName("rate1")
    @Expose
    private String rate1;

    @SerializedName("rate2")
    @Expose
    private String rate2;

    @SerializedName("besaranFee")
    @Expose
    private String besaranFee;

    @SerializedName("noticeJamsayar")
    @Expose
    private String noticeJamsyar;

    @SerializedName("asuransiPenjaminan")
    @Expose
    private String asuransiPenjaminan;

    @SerializedName("RPCfinal")
    private String RPCfinal;

    @SerializedName("rumahKeBerapa")
    private String rumahKeBerapa;

    public String getRumahKeBerapa() {
        return rumahKeBerapa;
    }

    public void setRumahKeBerapa(String rumahKeBerapa) {
        this.rumahKeBerapa = rumahKeBerapa;
    }

    public String getRPCfinal() {
        return RPCfinal;
    }

    public void setRPCfinal(String RPCfinal) {
        this.RPCfinal = RPCfinal;
    }

    public String getIjk() {
        return ijk;
    }

    public void setIjk(String ijk) {
        this.ijk = ijk;
    }

    public String getRate1() {
        return rate1;
    }

    public void setRate1(String rate1) {
        this.rate1 = rate1;
    }

    public String getRate2() {
        return rate2;
    }

    public void setRate2(String rate2) {
        this.rate2 = rate2;
    }

    public String getBesaranFee() {
        return besaranFee;
    }

    public void setBesaranFee(String besaranFee) {
        this.besaranFee = besaranFee;
    }

    public String getNoticeJamsyar() {
        return noticeJamsyar;
    }

    public void setNoticeJamsyar(String noticeJamsyar) {
        this.noticeJamsyar = noticeJamsyar;
    }

    public String getAsuransiPenjaminan() {
        return asuransiPenjaminan;
    }

    public void setAsuransiPenjaminan(String asuransiPenjaminan) {
        this.asuransiPenjaminan = asuransiPenjaminan;
    }

    public Double getSukuMarginKonsumtif() {
        return sukuMarginKonsumtif;
    }

    public void setSukuMarginKonsumtif(Double sukuMarginKonsumtif) {
        this.sukuMarginKonsumtif = sukuMarginKonsumtif;
    }

    public Long getAngsuranKonsumtif() {
        return angsuranKonsumtif;
    }

    public void setAngsuranKonsumtif(Long angsuranKonsumtif) {
        this.angsuranKonsumtif = angsuranKonsumtif;
    }

    public Long getPlafondKonsumtif() {
        return PlafondKonsumtif;
    }

    public void setPlafondKonsumtif(Long plafondKonsumtif) {
        PlafondKonsumtif = plafondKonsumtif;
    }

    public int getJANGKA_WAKTU_TO() {
        return JANGKA_WAKTU_TO;
    }

    public void setJANGKA_WAKTU_TO(int JANGKA_WAKTU_TO) {
        this.JANGKA_WAKTU_TO = JANGKA_WAKTU_TO;
    }

    public String getIdTujuan() {
        return idTujuan;
    }

    public void setIdTujuan(String idTujuan) {
        this.idTujuan = idTujuan;
    }

    public Long getKewajibanLain() {
        return kewajibanLain;
    }

    public void setKewajibanLain(Long kewajibanLain) {
        this.kewajibanLain = kewajibanLain;
    }

    public String getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(String idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public String getIdCif() {
        return idCif;
    }

    public void setIdCif(String idCif) {
        this.idCif = idCif;
    }

    public String getTypeProduk() {
        return typeProduk;
    }

    public void setTypeProduk(String typeProduk) {
        this.typeProduk = typeProduk;
    }

    public String getIndexNpw() {
        return indexNpw;
    }

    public void setIndexNpw(String indexNpw) {
        this.indexNpw = indexNpw;
    }

    public int getIdPrescoring() {
        return idPrescoring;
    }

    public void setIdPrescoring(int idPrescoring) {
        this.idPrescoring = idPrescoring;
    }

    public Long getPenghasilanLain() {
        return penghasilanLain;
    }

    public void setPenghasilanLain(Long penghasilanLain) {
        this.penghasilanLain = penghasilanLain;
    }

    public Long getAngsuran() {
        return angsuran;
    }

    public void setAngsuran(Long angsuran) {
        this.angsuran = angsuran;
    }

    public Double getSukuMargin() {
        return sukuMargin;
    }

    public void setSukuMargin(Double sukuMargin) {
        this.sukuMargin = sukuMargin;
    }

    public int getjANGKAWAKTU() {
        return jANGKAWAKTU;
    }

    public void setjANGKAWAKTU(int jANGKAWAKTU) {
        this.jANGKAWAKTU = jANGKAWAKTU;
    }

    public Long getmAKSIMUMPLAFOND() {
        return mAKSIMUMPLAFOND;
    }

    public void setmAKSIMUMPLAFOND(Long mAKSIMUMPLAFOND) {
        this.mAKSIMUMPLAFOND = mAKSIMUMPLAFOND;
    }

    public Long getiNPUTPERMOHONAN() {
        return iNPUTPERMOHONAN;
    }

    public void setiNPUTPERMOHONAN(Long iNPUTPERMOHONAN) {
        this.iNPUTPERMOHONAN = iNPUTPERMOHONAN;
    }

    public Long getPlafondUsul() {
        return plafondUsul;
    }

    public void setPlafondUsul(Long plafondUsul) {
        this.plafondUsul = plafondUsul;
    }

    public String getoMZETPERBULAN() {
        return oMZETPERBULAN;
    }

    public void setoMZETPERBULAN(String oMZETPERBULAN) {
        this.oMZETPERBULAN = oMZETPERBULAN;
    }

    public String getOmzetSetelahPotongan() {
        return omzetSetelahPotongan;
    }

    public void setOmzetSetelahPotongan(String omzetSetelahPotongan) {
        this.omzetSetelahPotongan = omzetSetelahPotongan;
    }

    public String getOmzetBersih() {
        return omzetBersih;
    }

    public void setOmzetBersih(String omzetBersih) {
        this.omzetBersih = omzetBersih;
    }

    public Long getHargaBeli() {
        return hargaBeli;
    }

    public void setHargaBeli(Long hargaBeli) {
        this.hargaBeli = hargaBeli;
    }

    public String getBesarUangMuka() {
        return besarUangMuka;
    }

    public void setBesarUangMuka(String besarUangMuka) {
        this.besarUangMuka = besarUangMuka;
    }

    public int getIdScoring() {
        return idScoring;
    }

    public void setIdScoring(int idScoring) {
        this.idScoring = idScoring;
    }

    public String getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(String netIncome) {
        this.netIncome = netIncome;
    }

    public String getReqLoanAmount() {
        return reqLoanAmount;
    }

    public void setReqLoanAmount(String reqLoanAmount) {
        this.reqLoanAmount = reqLoanAmount;
    }

    public String getReqPeriode() {
        return reqPeriode;
    }

    public void setReqPeriode(String reqPeriode) {
        this.reqPeriode = reqPeriode;
    }

    public Double getSukuMarginBulanan() {
        return sukuMarginBulanan;
    }

    public void setSukuMarginBulanan(Double sukuMarginBulanan) {
        this.sukuMarginBulanan = sukuMarginBulanan;
    }

    public String getAngsuranScoring() {
        return angsuranScoring;
    }

    public void setAngsuranScoring(String angsuranScoring) {
        this.angsuranScoring = angsuranScoring;
    }

    public String getDeviasiDir() {
        return deviasiDir;
    }

    public void setDeviasiDir(String deviasiDir) {
        this.deviasiDir = deviasiDir;
    }

    public String getLtv() {
        return ltv;
    }

    public void setLtv(String ltv) {
        this.ltv = ltv;
    }

    public String getNamaAOS() {
        return namaAOS;
    }

    public void setNamaAOS(String namaAOS) {
        this.namaAOS = namaAOS;
    }

    public String getUidAOS() {
        return uidAOS;
    }

    public void setUidAOS(String uidAOS) {
        this.uidAOS = uidAOS;
    }
}
