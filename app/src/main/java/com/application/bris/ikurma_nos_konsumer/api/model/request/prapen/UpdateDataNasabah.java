package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.application.bris.ikurma_nos_konsumer.model.prapen.DataNasabahPrapen;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateDataNasabah {
    @SerializedName("DataDukcapil")
    @Expose
    private DataNasabahPrapen dataNasabah;

    @SerializedName("DataDukcapilIMG")
    @Expose
    private UploadImage dataFoto;

    @SerializedName("DataDukcapilSign")
    @Expose
    private UploadImage DataDukcapilSign;

    public UploadImage getDataDukcapilSign() {
        return DataDukcapilSign;
    }

    public void setDataDukcapilSign(UploadImage dataDukcapilSign) {
        DataDukcapilSign = dataDukcapilSign;
    }

    public DataNasabahPrapen getDataNasabah() {
        return dataNasabah;
    }

    public void setDataNasabah(DataNasabahPrapen dataNasabah) {
        this.dataNasabah = dataNasabah;
    }

    public UploadImage getDataFoto() {
        return dataFoto;
    }

    public void setDataFoto(UploadImage dataFoto) {
        this.dataFoto = dataFoto;
    }
}
