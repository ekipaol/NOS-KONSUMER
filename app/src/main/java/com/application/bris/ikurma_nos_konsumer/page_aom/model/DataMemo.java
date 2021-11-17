package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataMemo {
    @SerializedName("Id")
    @Expose
    private Long id;
    @SerializedName("Application_Id")
    @Expose
    private Long applicationId;
    @SerializedName("Nama")
    @Expose
    private String nama;
    @SerializedName("Jabatan")
    @Expose
    private String jabatan;
    @SerializedName("TahapAplikasi")
    @Expose
    private String tahapAplikasi;
    @SerializedName("Memo")
    @Expose
    private String memo;
    @SerializedName("TanggalMemo")
    @Expose
    private String tanggalMemo;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("ModifiedBy")
    @Expose
    private String modifiedBy;
    @SerializedName("ModifiedDate")
    @Expose
    private String modifiedDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getTahapAplikasi() {
        return tahapAplikasi;
    }

    public void setTahapAplikasi(String tahapAplikasi) {
        this.tahapAplikasi = tahapAplikasi;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTanggalMemo() {
        return tanggalMemo;
    }

    public void setTanggalMemo(String tanggalMemo) {
        this.tanggalMemo = tanggalMemo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
