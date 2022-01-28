package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.application.bris.ikurma_nos_konsumer.page_aom.model.MsektorEkonomi;
import com.google.gson.annotations.SerializedName;

public class DropdownOJK extends MsektorEkonomi {
    @SerializedName("Name")
    private String name;
    @SerializedName("Description")
    private String Description;
    @SerializedName("Kode")
    private String Kode;

    public DropdownOJK(String sektorEkonomiText, String sektorEkonomiSID, String sektorEkonomiLBU) {
        super(sektorEkonomiText, sektorEkonomiSID, sektorEkonomiLBU);
    }

    public String getKode() {
        return Kode;
    }

    public void setKode(String kode) {
        Kode = kode;
    }

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
