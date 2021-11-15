package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.annotations.PrimaryKey;

public class UploadImagePojo {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("Img")
    @Expose
    private String Img;
    @SerializedName("File_Name")
    @Expose
    private String File_Name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getFile_Name() {
        return File_Name;
    }

    public void setFile_Name(String file_Name) {
        File_Name = file_Name;
    }
}
