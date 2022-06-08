package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataLkpUtama {
    @SerializedName("Tanggal_LKP")
    @Expose
    private String tanggalLKP;
    @SerializedName("Tanggal_LKP_Kadaluarsa")
    @Expose
    private String tanggalLKPKadaluarsa;
    @SerializedName("Filename")
    @Expose
    private String filename;
    @SerializedName("Img")
    @Expose
    private String img;

    public String getTanggalLKP() {
        return tanggalLKP;
    }

    public void setTanggalLKP(String tanggalLKP) {
        this.tanggalLKP = tanggalLKP;
    }

    public String getTanggalLKPKadaluarsa() {
        return tanggalLKPKadaluarsa;
    }

    public void setTanggalLKPKadaluarsa(String tanggalLKPKadaluarsa) {
        this.tanggalLKPKadaluarsa = tanggalLKPKadaluarsa;
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
