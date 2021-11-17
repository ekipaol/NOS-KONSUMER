package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataVerifikasiFitur {
    @SerializedName("Id")
    @Expose
    private Long id;
    @SerializedName("FITUR_LOG_Id")
    @Expose
    private Long fITURLOGId;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getfITURLOGId() {
        return fITURLOGId;
    }

    public void setfITURLOGId(Long fITURLOGId) {
        this.fITURLOGId = fITURLOGId;
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
