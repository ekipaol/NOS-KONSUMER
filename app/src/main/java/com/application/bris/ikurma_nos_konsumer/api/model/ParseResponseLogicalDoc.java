package com.application.bris.ikurma_nos_konsumer.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParseResponseLogicalDoc {
    @SerializedName("FileName")
    @Expose
    private String FileName;

    @SerializedName("BinaryData")
    @Expose
    private String BinaryData;

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getBinaryData() {
        return BinaryData;
    }

    public void setBinaryData(String binaryData) {
        BinaryData = binaryData;
    }
}
