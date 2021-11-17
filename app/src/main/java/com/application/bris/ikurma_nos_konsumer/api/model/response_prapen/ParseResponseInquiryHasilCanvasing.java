package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ParseResponseInquiryHasilCanvasing {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statusMsg")
    @Expose
    private String statusMsg;
    @SerializedName("ResponseFitur")
    @Expose
    private List<MparseResponseFitur> ResponseFitur;
    @SerializedName("ResponseRAC")
    @Expose
    private List<MparseResponseRAC> ResponseRAC;

    @SerializedName("ResponseTaspen")
    @Expose
    private List<MparseResponseRAC> ResponseTaspen;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public List<MparseResponseFitur> getResponseFitur() {
        return ResponseFitur;
    }

    public void setResponseFitur(List<MparseResponseFitur> responseFitur) {
        ResponseFitur = responseFitur;
    }

    public List<MparseResponseRAC> getResponseRAC() {
        return ResponseRAC;
    }

    public void setResponseRAC(List<MparseResponseRAC> responseRAC) {
        ResponseRAC = responseRAC;
    }

    public List<MparseResponseRAC> getResponseTaspen() {
        return ResponseTaspen;
    }

    public void setResponseTaspen(List<MparseResponseRAC> responseTaspen) {
        ResponseTaspen = responseTaspen;
    }
}
