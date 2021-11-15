package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.SerializedName;

public class DataCIfRekening {

    @SerializedName("CIF")
    private String CIF;

    @SerializedName("OpeningDate")
    private String OpeningDate;

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public String getOpeningDate() {
        return OpeningDate;
    }

    public void setOpeningDate(String openingDate) {
        OpeningDate = openingDate;
    }
}
