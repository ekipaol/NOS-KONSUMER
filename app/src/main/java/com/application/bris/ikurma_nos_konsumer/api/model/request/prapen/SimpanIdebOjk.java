package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimpanIdebOjk {
    @SerializedName("ApplicationId")
    @Expose
    private Long ApplicationId;
    @SerializedName("Catatan")
    @Expose
    private String Catatan;
    @SerializedName("Nama")
    @Expose
    private String Nama;
    @SerializedName("UID")
    @Expose
    private String UID;

    public Long getApplicationId() {
        return ApplicationId;
    }

    public void setApplicationId(Long applicationId) {
        ApplicationId = applicationId;
    }

    public String getCatatan() {
        return Catatan;
    }

    public void setCatatan(String catatan) {
        Catatan = catatan;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
