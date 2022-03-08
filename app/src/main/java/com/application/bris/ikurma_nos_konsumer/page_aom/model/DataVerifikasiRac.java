package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataVerifikasiRac {
    @SerializedName("Id")
    @Expose
    private Long id;
    @SerializedName("RAC_LOG_Id")
    @Expose
    private Long rACLOGId;
    @SerializedName("Value")
    @Expose
    private String value;
    @SerializedName("Parameter")
    @Expose
    private String parameter;
    @SerializedName("Parameter_Desc")
    @Expose
    private String parameterDesc;
    @SerializedName("Parameter_Desc_Verifikator")
    @Expose
    private String parameterDescVerifikator;
    @SerializedName("Catatan_Hasil_Verifikasi")
    @Expose
    private String catatanHasilVerifikasi;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Created_By")
    @Expose
    private String createdBy;
    @SerializedName("Created_Date")
    @Expose
    private String createdDate;
    @SerializedName("Modified_By")
    @Expose
    private String modifiedBy;
    @SerializedName("Modified_Date")
    @Expose
    private String modifiedDate;
    @SerializedName("Img")
    @Expose
    private String img;
    @SerializedName("File_Name")
    @Expose
    private String fileName;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getrACLOGId() {
        return rACLOGId;
    }

    public void setrACLOGId(Long rACLOGId) {
        this.rACLOGId = rACLOGId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getParameterDesc() {
        return parameterDesc;
    }

    public void setParameterDesc(String parameterDesc) {
        this.parameterDesc = parameterDesc;
    }

    public String getParameterDescVerifikator() {
        return parameterDescVerifikator;
    }

    public void setParameterDescVerifikator(String parameterDescVerifikator) {
        this.parameterDescVerifikator = parameterDescVerifikator;
    }

    public String getCatatanHasilVerifikasi() {
        return catatanHasilVerifikasi;
    }

    public void setCatatanHasilVerifikasi(String catatanHasilVerifikasi) {
        this.catatanHasilVerifikasi = catatanHasilVerifikasi;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
