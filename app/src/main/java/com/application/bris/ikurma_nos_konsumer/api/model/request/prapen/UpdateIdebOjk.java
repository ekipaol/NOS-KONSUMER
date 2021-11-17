package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateIdebOjk {
    @SerializedName("Fasilitas_Aktif_Id")
    @Expose
    private Long fasilitasAktifId;
    @SerializedName("ApplicationId")
    @Expose
    private Long applicationId;
    @SerializedName("Treatment_Pembiayaan")
    @Expose
    private String treatmentPembiayaan;
    @SerializedName("Catatan_BU_untuk_Verifikator")
    @Expose
    private String catatanBUUntukVerifikator;
    @SerializedName("UID")
    @Expose
    private String uid;
    @SerializedName("Img")
    @Expose
    private String img;
    @SerializedName("File_Name")
    @Expose
    private String fileName;

    public Long getFasilitasAktifId() {
        return fasilitasAktifId;
    }

    public void setFasilitasAktifId(Long fasilitasAktifId) {
        this.fasilitasAktifId = fasilitasAktifId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getTreatmentPembiayaan() {
        return treatmentPembiayaan;
    }

    public void setTreatmentPembiayaan(String treatmentPembiayaan) {
        this.treatmentPembiayaan = treatmentPembiayaan;
    }

    public String getCatatanBUUntukVerifikator() {
        return catatanBUUntukVerifikator;
    }

    public void setCatatanBUUntukVerifikator(String catatanBUUntukVerifikator) {
        this.catatanBUUntukVerifikator = catatanBUUntukVerifikator;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

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
}
