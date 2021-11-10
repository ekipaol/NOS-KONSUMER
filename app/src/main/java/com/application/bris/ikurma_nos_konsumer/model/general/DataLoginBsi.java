package com.application.bris.ikurma_nos_konsumer.model.general;

import com.google.gson.annotations.SerializedName;

public class DataLoginBsi {
    @SerializedName("id")
    private int id;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("officer_code")
    private String officer_code;
    @SerializedName("name")
    private String name;
    @SerializedName("limit")
    private String limit;
    @SerializedName("token")
    private String token;
    @SerializedName("branch")
    private DataLoginBranch branch;
    @SerializedName("role")
    private DataLoginRole role;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficer_code() {
        return officer_code;
    }

    public void setOfficer_code(String officer_code) {
        this.officer_code = officer_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public DataLoginBranch getBranch() {
        return branch;
    }

    public void setBranch(DataLoginBranch branch) {
        this.branch = branch;
    }

    public DataLoginRole getRole() {
        return role;
    }

    public void setRole(DataLoginRole role) {
        this.role = role;
    }
}