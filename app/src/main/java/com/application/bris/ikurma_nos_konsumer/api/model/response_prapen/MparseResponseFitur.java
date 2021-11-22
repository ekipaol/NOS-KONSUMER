package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MparseResponseFitur {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("FITUR_LOG_Id")
    @Expose
    private Integer fITURLOGId;
    @SerializedName("Value")
    @Expose
    private String value;
    @SerializedName("Parameter")
    @Expose
    private String parameter;
    @SerializedName("Parameter_Desc")
    @Expose
    private String parameterDesc;
    @SerializedName("Created_By")
    @Expose
    private String createdBy;
    @SerializedName("Created_Date")
    @Expose
    private String createdDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getfITURLOGId() {
        return fITURLOGId;
    }

    public void setfITURLOGId(Integer fITURLOGId) {
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
}
