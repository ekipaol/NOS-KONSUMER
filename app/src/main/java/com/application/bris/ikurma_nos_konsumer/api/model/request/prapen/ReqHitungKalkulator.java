package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseDataPembiayaan;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseSimulasiBiayaBiaya;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseSimulasiInqCal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqHitungKalkulator {

    @SerializedName("ApplicationId")
    @Expose
    private Integer applicationId;
    @SerializedName("UID")
    @Expose
    private String uid;
    @SerializedName("SimulasiAngsuranCalc")
    @Expose
    private MparseResponseSimulasiInqCal simulasiAngsuranCalc;
    @SerializedName("SimulasiBiayaBiaya")
    @Expose
    private MparseResponseSimulasiBiayaBiaya simulasiBiayaBiaya;
    @SerializedName("DataPembiayaan")
    @Expose
    private MparseResponseDataPembiayaan dataPembiayaan;

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public MparseResponseSimulasiInqCal getSimulasiAngsuranCalc() {
        return simulasiAngsuranCalc;
    }

    public void setSimulasiAngsuranCalc(MparseResponseSimulasiInqCal simulasiAngsuranCalc) {
        this.simulasiAngsuranCalc = simulasiAngsuranCalc;
    }

    public MparseResponseSimulasiBiayaBiaya getSimulasiBiayaBiaya() {
        return simulasiBiayaBiaya;
    }

    public void setSimulasiBiayaBiaya(MparseResponseSimulasiBiayaBiaya simulasiBiayaBiaya) {
        this.simulasiBiayaBiaya = simulasiBiayaBiaya;
    }

    public MparseResponseDataPembiayaan getDataPembiayaan() {
        return dataPembiayaan;
    }

    public void setDataPembiayaan(MparseResponseDataPembiayaan dataPembiayaan) {
        this.dataPembiayaan = dataPembiayaan;
    }
}
