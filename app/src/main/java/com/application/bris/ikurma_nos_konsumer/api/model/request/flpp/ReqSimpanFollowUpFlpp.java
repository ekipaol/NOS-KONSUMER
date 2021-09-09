package com.application.bris.ikurma_nos_konsumer.api.model.request.flpp;

import com.google.gson.annotations.SerializedName;

public class ReqSimpanFollowUpFlpp {


    @SerializedName("kode")
    private String kodeBank;

    @SerializedName("batchId")
    private String batchId;

    @SerializedName("ktpDebitur")
    private String ktpDebitur;

    @SerializedName("noPonselDebitur")
    private String noPonselDebitur;

    @SerializedName("npwpDebitur")
    private String npwpDebitur;

    @SerializedName("namaPicBankCabang")
    private String namaPicBankCabang;

    @SerializedName("ktpPicBankCabang")
    private String ktpPicBankCabang;

    @SerializedName("nipPicBankCabang")
    private String nipPicBankCabang;

    @SerializedName("noPonselPicBankCabang")
    private String noPonselPicBankCabang;

    @SerializedName("alamatCabang")
    private String alamatCabang;

    @SerializedName("kabKotaCabang")
    private String kabKotaCabang;

    @SerializedName("namaKantorCabang")
    private String namaKantorCabang;

    @SerializedName("status")
    private String status;

    @SerializedName("alasanBatal")
    private String alasanBatal;

    @SerializedName("uid")
    private String uid;

    @SerializedName("catatan")
    private String catatan;

    @SerializedName("isTesting")
    private String isTesting;

    public String getIsTesting() {
        return isTesting;
    }

    public void setIsTesting(String isTesting) {
        this.isTesting = isTesting;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKodeBank() {
        return kodeBank;
    }

    public void setKodeBank(String kodeBank) {
        this.kodeBank = kodeBank;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getKtpDebitur() {
        return ktpDebitur;
    }

    public void setKtpDebitur(String ktpDebitur) {
        this.ktpDebitur = ktpDebitur;
    }

    public String getNoPonselDebitur() {
        return noPonselDebitur;
    }

    public void setNoPonselDebitur(String noPonselDebitur) {
        this.noPonselDebitur = noPonselDebitur;
    }

    public String getNpwpDebitur() {
        return npwpDebitur;
    }

    public void setNpwpDebitur(String npwpDebitur) {
        this.npwpDebitur = npwpDebitur;
    }

    public String getNamaPicBankCabang() {
        return namaPicBankCabang;
    }

    public void setNamaPicBankCabang(String namaPicBankCabang) {
        this.namaPicBankCabang = namaPicBankCabang;
    }

    public String getKtpPicBankCabang() {
        return ktpPicBankCabang;
    }

    public void setKtpPicBankCabang(String ktpPicBankCabang) {
        this.ktpPicBankCabang = ktpPicBankCabang;
    }

    public String getNipPicBankCabang() {
        return nipPicBankCabang;
    }

    public void setNipPicBankCabang(String nipPicBankCabang) {
        this.nipPicBankCabang = nipPicBankCabang;
    }

    public String getNoPonselPicBankCabang() {
        return noPonselPicBankCabang;
    }

    public void setNoPonselPicBankCabang(String noPonselPicBankCabang) {
        this.noPonselPicBankCabang = noPonselPicBankCabang;
    }

    public String getAlamatCabang() {
        return alamatCabang;
    }

    public void setAlamatCabang(String alamatCabang) {
        this.alamatCabang = alamatCabang;
    }

    public String getKabKotaCabang() {
        return kabKotaCabang;
    }

    public void setKabKotaCabang(String kabKotaCabang) {
        this.kabKotaCabang = kabKotaCabang;
    }

    public String getNamaKantorCabang() {
        return namaKantorCabang;
    }

    public void setNamaKantorCabang(String namaKantorCabang) {
        this.namaKantorCabang = namaKantorCabang;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlasanBatal() {
        return alasanBatal;
    }

    public void setAlasanBatal(String alasanBatal) {
        this.alasanBatal = alasanBatal;
    }
}
