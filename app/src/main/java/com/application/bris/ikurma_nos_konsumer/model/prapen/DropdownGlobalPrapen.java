package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.SerializedName;

public class DropdownGlobalPrapen {
    @SerializedName("Name")
    private String name;
    @SerializedName("Description")
    private String Description;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
