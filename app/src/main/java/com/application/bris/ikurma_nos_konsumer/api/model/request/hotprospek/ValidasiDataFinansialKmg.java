package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidasiDataFinansialKmg {

    @SerializedName("idAplikasi")
    private String idAplikasi;
    @SerializedName("PlafondUsul")
    private String PlafondUsul;
    @SerializedName("PlafondKonsumtif")
    private String PlafondKonsumtif;
    @SerializedName("RPCfinal")
    private String RPCfinal;
    @SerializedName("RPCfinalKonsumtif")
    private String RPCfinalKonsumtif;
    @SerializedName("MAKSIMUM_PLAFOND")
    private String MAKSIMUM_PLAFOND;
    @SerializedName("INPUT_PERMOHONAN")
    private String INPUT_PERMOHONAN;
    @SerializedName("cookie_jw_max")
    private String cookie_jw_max;
    @SerializedName("JANGKA_WAKTU")
    private String JANGKA_WAKTU;
    @SerializedName("JANGKA_WAKTU_TO")
    private String JANGKA_WAKTU_TO;
    @SerializedName("MAKS_TENOR_MPP")
    private String MAKS_TENOR_MPP;
    @SerializedName("OMZET_PERBULAN")
    private String OMZET_PERBULAN;
    @SerializedName("MARGIN")
    private String MARGIN;
    @SerializedName("omzetBersih")
    @Expose
    private String omzetBersih;

    public String getOmzetBersih() {
        return omzetBersih;
    }

    public void setOmzetBersih(String omzetBersih) {
        this.omzetBersih = omzetBersih;
    }

    @SerializedName("MARGIN_KONSUMTIF")
    private String MARGIN_KONSUMTIF;

    public String getRPCfinalKonsumtif() {
        return RPCfinalKonsumtif;
    }

    public void setRPCfinalKonsumtif(String RPCfinalKonsumtif) {
        this.RPCfinalKonsumtif = RPCfinalKonsumtif;
    }

    public String getMARGIN_KONSUMTIF() {
        return MARGIN_KONSUMTIF;
    }

    public void setMARGIN_KONSUMTIF(String MARGIN_KONSUMTIF) {
        this.MARGIN_KONSUMTIF = MARGIN_KONSUMTIF;
    }

    public String getPlafondKonsumtif() {
        return PlafondKonsumtif;
    }

    public void setPlafondKonsumtif(String plafondKonsumtif) {
        PlafondKonsumtif = plafondKonsumtif;
    }

    public String getJANGKA_WAKTU_TO() {
        return JANGKA_WAKTU_TO;
    }

    public void setJANGKA_WAKTU_TO(String JANGKA_WAKTU_TO) {
        this.JANGKA_WAKTU_TO = JANGKA_WAKTU_TO;
    }

    public String getMARGIN() {
        return MARGIN;
    }

    public void setMARGIN(String MARGIN) {
        this.MARGIN = MARGIN;
    }

    public String getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(String idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public String getPlafondUsul() {
        return PlafondUsul;
    }

    public void setPlafondUsul(String plafondUsul) {
        PlafondUsul = plafondUsul;
    }

    public String getRPCfinal() {
        return RPCfinal;
    }

    public void setRPCfinal(String RPCfinal) {
        this.RPCfinal = RPCfinal;
    }

    public String getMAKSIMUM_PLAFOND() {
        return MAKSIMUM_PLAFOND;
    }

    public void setMAKSIMUM_PLAFOND(String MAKSIMUM_PLAFOND) {
        this.MAKSIMUM_PLAFOND = MAKSIMUM_PLAFOND;
    }

    public String getINPUT_PERMOHONAN() {
        return INPUT_PERMOHONAN;
    }

    public void setINPUT_PERMOHONAN(String INPUT_PERMOHONAN) {
        this.INPUT_PERMOHONAN = INPUT_PERMOHONAN;
    }

    public String getCookie_jw_max() {
        return cookie_jw_max;
    }

    public void setCookie_jw_max(String cookie_jw_max) {
        this.cookie_jw_max = cookie_jw_max;
    }

    public String getJANGKA_WAKTU() {
        return JANGKA_WAKTU;
    }

    public void setJANGKA_WAKTU(String JANGKA_WAKTU) {
        this.JANGKA_WAKTU = JANGKA_WAKTU;
    }

    public String getMAKS_TENOR_MPP() {
        return MAKS_TENOR_MPP;
    }

    public void setMAKS_TENOR_MPP(String MAKS_TENOR_MPP) {
        this.MAKS_TENOR_MPP = MAKS_TENOR_MPP;
    }

    public String getOMZET_PERBULAN() {
        return OMZET_PERBULAN;
    }

    public void setOMZET_PERBULAN(String OMZET_PERBULAN) {
        this.OMZET_PERBULAN = OMZET_PERBULAN;
    }
}
