package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Picklist {
    @SerializedName("KomponenId")
    @Expose
    private Long Id;
    @SerializedName("PendapatanTunjanganId")
    @Expose
    private Long PendapatanTunjanganId;
    @SerializedName("Name")
    @Expose
    private String Name;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getPendapatanTunjanganId() {
        return PendapatanTunjanganId;
    }

    public void setPendapatanTunjanganId(Long pendapatanTunjanganId) {
        PendapatanTunjanganId = pendapatanTunjanganId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
