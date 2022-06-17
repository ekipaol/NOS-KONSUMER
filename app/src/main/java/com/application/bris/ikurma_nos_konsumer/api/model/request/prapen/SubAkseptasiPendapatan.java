package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubAkseptasiPendapatan {
    @SerializedName("Nama_Komponen_Pendapatan")
    @Expose
    private String Nama_Komponen_Pendapatan;
    @SerializedName("Input_Nominal")
    @Expose
    private Long Input_Nominal;
    @SerializedName("Setelah_Akseptasi")
    @Expose
    private Long Setelah_Akseptasi;
    @SerializedName("Treatment_Pendapatan")
    @Expose
    private String Treatment_Pendapatan;

    public String getNama_Komponen_Pendapatan() {
        return Nama_Komponen_Pendapatan;
    }

    public void setNama_Komponen_Pendapatan(String nama_Komponen_Pendapatan) {
        Nama_Komponen_Pendapatan = nama_Komponen_Pendapatan;
    }

    public Long getInput_Nominal() {
        return Input_Nominal;
    }

    public void setInput_Nominal(Long input_Nominal) {
        Input_Nominal = input_Nominal;
    }

    public Long getSetelah_Akseptasi() {
        return Setelah_Akseptasi;
    }

    public void setSetelah_Akseptasi(Long setelah_Akseptasi) {
        Setelah_Akseptasi = setelah_Akseptasi;
    }

    public String getTreatment_Pendapatan() {
        return Treatment_Pendapatan;
    }

    public void setTreatment_Pendapatan(String treatment_Pendapatan) {
        Treatment_Pendapatan = treatment_Pendapatan;
    }
}
