package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqProgram {
    @SerializedName("NamaProduk")
    @Expose
    private String NamaProduk;

    public String getNamaProduk() {
        return NamaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        NamaProduk = namaProduk;
    }
}
