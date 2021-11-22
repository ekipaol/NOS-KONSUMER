package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class isiReqDokumenPendapatan {

    @SerializedName("DokumenPendapatan")
    @Expose
    private DokumenPendapatan DokumenPendapatan;

    @SerializedName("DokumenPendapatanKoranBank")
    @Expose
    private ReqDocument DokumenPendapatanKoranBank;

    @SerializedName("DokumenPendapatanKoranBSI")
    @Expose
    private ReqDocument DokumenPendapatanKoranBSI;

    @SerializedName("DokumenPendapatanSlipGajiP1")
    @Expose
    private ReqDocument DokumenPendapatanSlipGajiP1;

    @SerializedName("DokumenPendapatanSlipTunjanganP1")
    @Expose
    private ReqDocument DokumenPendapatanSlipTunjanganP1;

    @SerializedName("DataJaminanIDCard")
    @Expose
    private ReqDocument DataJaminanIDCard;

    @SerializedName("DokumenPendapatanSlipGajiP2")
    @Expose
    private ReqDocument DokumenPendapatanSlipGajiP2;

    @SerializedName("DokumenPendapatanSlipTunjanganP2")
    @Expose
    private ReqDocument DokumenPendapatanSlipTunjanganP2;

    @SerializedName("DokumenPendapatanSlipGajiP3")
    @Expose
    private ReqDocument DokumenPendapatanSlipGajiP3;

    @SerializedName("DokumenPendapatanSlipTunjanganP3")
    @Expose
    private ReqDocument DokumenPendapatanSlipTunjanganP3;


    public com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.DokumenPendapatan getDokumenPendapatan() {
        return DokumenPendapatan;
    }

    public void setDokumenPendapatan(com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.DokumenPendapatan dokumenPendapatan) {
        DokumenPendapatan = dokumenPendapatan;
    }

    public ReqDocument getDokumenPendapatanKoranBank() {
        return DokumenPendapatanKoranBank;
    }

    public void setDokumenPendapatanKoranBank(ReqDocument dokumenPendapatanKoranBank) {
        DokumenPendapatanKoranBank = dokumenPendapatanKoranBank;
    }

    public ReqDocument getDokumenPendapatanKoranBSI() {
        return DokumenPendapatanKoranBSI;
    }

    public void setDokumenPendapatanKoranBSI(ReqDocument dokumenPendapatanKoranBSI) {
        DokumenPendapatanKoranBSI = dokumenPendapatanKoranBSI;
    }

    public ReqDocument getDokumenPendapatanSlipGajiP1() {
        return DokumenPendapatanSlipGajiP1;
    }

    public void setDokumenPendapatanSlipGajiP1(ReqDocument dokumenPendapatanSlipGajiP1) {
        DokumenPendapatanSlipGajiP1 = dokumenPendapatanSlipGajiP1;
    }

    public ReqDocument getDokumenPendapatanSlipTunjanganP1() {
        return DokumenPendapatanSlipTunjanganP1;
    }

    public void setDokumenPendapatanSlipTunjanganP1(ReqDocument dokumenPendapatanSlipTunjanganP1) {
        DokumenPendapatanSlipTunjanganP1 = dokumenPendapatanSlipTunjanganP1;
    }

    public ReqDocument getDataJaminanIDCard() {
        return DataJaminanIDCard;
    }

    public void setDataJaminanIDCard(ReqDocument dataJaminanIDCard) {
        DataJaminanIDCard = dataJaminanIDCard;
    }

    public ReqDocument getDokumenPendapatanSlipGajiP2() {
        return DokumenPendapatanSlipGajiP2;
    }

    public void setDokumenPendapatanSlipGajiP2(ReqDocument dokumenPendapatanSlipGajiP2) {
        DokumenPendapatanSlipGajiP2 = dokumenPendapatanSlipGajiP2;
    }

    public ReqDocument getDokumenPendapatanSlipTunjanganP2() {
        return DokumenPendapatanSlipTunjanganP2;
    }

    public void setDokumenPendapatanSlipTunjanganP2(ReqDocument dokumenPendapatanSlipTunjanganP2) {
        DokumenPendapatanSlipTunjanganP2 = dokumenPendapatanSlipTunjanganP2;
    }

    public ReqDocument getDokumenPendapatanSlipGajiP3() {
        return DokumenPendapatanSlipGajiP3;
    }

    public void setDokumenPendapatanSlipGajiP3(ReqDocument dokumenPendapatanSlipGajiP3) {
        DokumenPendapatanSlipGajiP3 = dokumenPendapatanSlipGajiP3;
    }

    public ReqDocument getDokumenPendapatanSlipTunjanganP3() {
        return DokumenPendapatanSlipTunjanganP3;
    }

    public void setDokumenPendapatanSlipTunjanganP3(ReqDocument dokumenPendapatanSlipTunjanganP3) {
        DokumenPendapatanSlipTunjanganP3 = dokumenPendapatanSlipTunjanganP3;
    }
}
