package com.application.bris.ikurma_nos_konsumer.model.general;

import com.google.gson.annotations.SerializedName;

public class DataLoginBranch {
    @SerializedName("id")
    private int id;
    @SerializedName("branch_code")
    private String branch_code;
    @SerializedName("branch_name")
    private String branch_name;
    @SerializedName("address")
    private String address;
    @SerializedName("phone")
    private String phone;
    @SerializedName("branch_level")
    private String branch_level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBranch_level() {
        return branch_level;
    }

    public void setBranch_level(String branch_level) {
        this.branch_level = branch_level;
    }
}
