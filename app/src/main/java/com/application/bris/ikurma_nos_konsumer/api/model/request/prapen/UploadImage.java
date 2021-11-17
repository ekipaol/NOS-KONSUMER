
package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.application.bris.ikurma_nos_konsumer.model.prapen.DataNasabahPrapen;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

public class UploadImage extends RealmObject implements Serializable {

    @SerializedName("Img")
    @Expose
    private String Img;
    @SerializedName("File_Name")
    @Expose
    private String File_Name;

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
