package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqDocument {

    @SerializedName("Img")
    @Expose
    private String Img;
    @SerializedName("File_Name")
    @Expose
    private String FileName;
    @SerializedName("Tanggal_Dokumen")
    @Expose
    private String Tanggal_Dokumen;

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getTanggal_Dokumen() {
        return Tanggal_Dokumen;
    }

    public void setTanggal_Dokumen(String tanggal_Dokumen) {
        Tanggal_Dokumen = tanggal_Dokumen;
    }
}
