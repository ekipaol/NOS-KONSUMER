package com.application.bris.ikurma_nos_konsumer.api.model.request.foto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqUploadFile {
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("folderId")
    @Expose
    private String folderId ;
    @SerializedName("fileBinary")
    @Expose
    private String fileB64;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getFileB64() {
        return fileB64;
    }

    public void setFileB64(String fileB64) {
        this.fileB64 = fileB64;
    }
}
