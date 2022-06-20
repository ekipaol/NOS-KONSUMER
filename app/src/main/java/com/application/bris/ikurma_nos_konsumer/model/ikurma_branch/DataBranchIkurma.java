package com.application.bris.ikurma_nos_konsumer.model.ikurma_branch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataBranchIkurma {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("mappingAreaBranches")
    @Expose
    private String mappingAreaBranches;
    @SerializedName("branch_code")
    @Expose
    private String branch_code;
    @SerializedName("branch_name")
    @Expose
    private String branch_name;
    @SerializedName("branch_level")
    @Expose
    private String branch_level;
    @SerializedName("kode_dati2")
    @Expose
    private String kode_dati2;

    @SerializedName("deskripsi_dati2")
    @Expose
    private String deskripsi_dati2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMappingAreaBranches() {
        return mappingAreaBranches;
    }

    public void setMappingAreaBranches(String mappingAreaBranches) {
        this.mappingAreaBranches = mappingAreaBranches;
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

    public String getBranch_level() {
        return branch_level;
    }

    public void setBranch_level(String branch_level) {
        this.branch_level = branch_level;
    }

    public String getKode_dati2() {
        return kode_dati2;
    }

    public void setKode_dati2(String kode_dati2) {
        this.kode_dati2 = kode_dati2;
    }

    public String getDeskripsi_dati2() {
        return deskripsi_dati2;
    }

    public void setDeskripsi_dati2(String deskripsi_dati2) {
        this.deskripsi_dati2 = deskripsi_dati2;
    }
}
