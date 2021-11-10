package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.SerializedName;

public class DropdownGlobalPrapen {
    @SerializedName("Name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
