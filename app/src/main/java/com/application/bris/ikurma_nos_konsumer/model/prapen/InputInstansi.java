package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.DataDetilInstansi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.DataLkpUtama;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UploadImage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InputInstansi {
    @SerializedName("UID")
    @Expose
    private String uid;
    @SerializedName("Data_LKP_Utama")
    @Expose
    private DataLkpUtama dataLkpUtama;
    @SerializedName("CreateOrUpdate_Instansi")
    @Expose
    private DataDetilInstansi dataInstansi;
    @SerializedName("Dokumen_Lainnya")
    @Expose
    private List<UploadImage> dokumenLain;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public DataLkpUtama getDataLkpUtama() {
        return dataLkpUtama;
    }

    public void setDataLkpUtama(DataLkpUtama dataLkpUtama) {
        this.dataLkpUtama = dataLkpUtama;
    }

    public DataDetilInstansi getDataInstansi() {
        return dataInstansi;
    }

    public void setDataInstansi(DataDetilInstansi dataInstansi) {
        this.dataInstansi = dataInstansi;
    }

    public List<UploadImage> getDokumenLain() {
        return dokumenLain;
    }

    public void setDokumenLain(List<UploadImage> dokumenLain) {
        this.dokumenLain = dokumenLain;
    }
}
