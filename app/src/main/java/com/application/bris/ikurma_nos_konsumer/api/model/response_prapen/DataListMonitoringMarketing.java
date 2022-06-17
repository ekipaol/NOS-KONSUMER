package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataListMonitoringMarketing {

    @SerializedName("ApplicationId")
    @Expose
    private Long applicationId;
    @SerializedName("NoAplikasi")
    @Expose
    private String noAplikasi;
    @SerializedName("Produk")
    @Expose
    private String produk;
    @SerializedName("FileNamePencairan")
    @Expose
    private String fileNamePencairan;
    @SerializedName("Sequence")
    @Expose
    private String sequence;
    @SerializedName("StatusBlokir")
    @Expose
    private String statusBlokir;
    @SerializedName("KeteranganBlokir")
    @Expose
    private String keteranganBlokir;
    @SerializedName("StatusPembentukanLD")
    @Expose
    private String statusPembentukanLD;
    @SerializedName("KeteranganPembentukanLD")
    @Expose
    private String keteranganPembentukanLD;
    @SerializedName("NoLD")
    @Expose
    private String noLD;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getNoAplikasi() {
        return noAplikasi;
    }

    public void setNoAplikasi(String noAplikasi) {
        this.noAplikasi = noAplikasi;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public String getFileNamePencairan() {
        return fileNamePencairan;
    }

    public void setFileNamePencairan(String fileNamePencairan) {
        this.fileNamePencairan = fileNamePencairan;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getStatusBlokir() {
        return statusBlokir;
    }

    public void setStatusBlokir(String statusBlokir) {
        this.statusBlokir = statusBlokir;
    }

    public String getKeteranganBlokir() {
        return keteranganBlokir;
    }

    public void setKeteranganBlokir(String keteranganBlokir) {
        this.keteranganBlokir = keteranganBlokir;
    }

    public String getStatusPembentukanLD() {
        return statusPembentukanLD;
    }

    public void setStatusPembentukanLD(String statusPembentukanLD) {
        this.statusPembentukanLD = statusPembentukanLD;
    }

    public String getKeteranganPembentukanLD() {
        return keteranganPembentukanLD;
    }

    public void setKeteranganPembentukanLD(String keteranganPembentukanLD) {
        this.keteranganPembentukanLD = keteranganPembentukanLD;
    }

    public String getNoLD() {
        return noLD;
    }

    public void setNoLD(String noLD) {
        this.noLD = noLD;
    }
}
