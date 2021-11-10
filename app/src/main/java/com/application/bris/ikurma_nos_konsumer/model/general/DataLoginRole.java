package com.application.bris.ikurma_nos_konsumer.model.general;

import com.google.gson.annotations.SerializedName;

public class DataLoginRole {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String roleName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
