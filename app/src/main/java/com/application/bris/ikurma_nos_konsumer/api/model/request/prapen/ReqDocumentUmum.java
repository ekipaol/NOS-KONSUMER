package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqDocumentUmum {

    @SerializedName("NamaDokumen")
    @Expose
    private String namaDokumen;
    @SerializedName("KeteranganDokumen")
    @Expose
    private String keteranganDokumen;
    @SerializedName("File_Name")
    @Expose
    private String filename;
    @SerializedName("Img")
    @Expose
    private String img;

    public ReqDocumentUmum(String namaDokumen, String keteranganDokumen, String filename, String img) {
        this.namaDokumen = namaDokumen;
        this.keteranganDokumen = keteranganDokumen;
        this.filename = filename;
        this.img = img;
    }

    public ReqDocumentUmum() {

    }

    public String getNamaDokumen() {
        return namaDokumen;
    }

    public void setNamaDokumen(String namaDokumen) {
        this.namaDokumen = namaDokumen;
    }

    public String getKeteranganDokumen() {
        return keteranganDokumen;
    }

    public void setKeteranganDokumen(String keteranganDokumen) {
        this.keteranganDokumen = keteranganDokumen;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
