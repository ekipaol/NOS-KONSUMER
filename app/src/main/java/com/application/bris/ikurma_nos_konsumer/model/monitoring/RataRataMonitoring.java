package com.application.bris.ikurma_nos_konsumer.model.monitoring;

import com.google.gson.annotations.SerializedName;

public class RataRataMonitoring {
    @SerializedName("totalOs")
    private String totalOs;

    @SerializedName("avrKol2")
    private String avrKol2;

    @SerializedName("totalNpf")
    private String totalNpf;

    @SerializedName("totalPencairan")
    private String totalPencairan;

    @SerializedName("totalKol2")
    private String totalKol2;

    @SerializedName("avrPencairan")
    private String avrPencairan;

    @SerializedName("avrNpf")
    private String avrNpf;

    @SerializedName("avrOs")
    private String avrOs;

    public String getTotalOs() {
        return totalOs;
    }

    public void setTotalOs(String totalOs) {
        this.totalOs = totalOs;
    }

    public String getAvrKol2() {
        return avrKol2;
    }

    public void setAvrKol2(String avrKol2) {
        this.avrKol2 = avrKol2;
    }

    public String getTotalNpf() {
        return totalNpf;
    }

    public void setTotalNpf(String totalNpf) {
        this.totalNpf = totalNpf;
    }

    public String getTotalPencairan() {
        return totalPencairan;
    }

    public void setTotalPencairan(String totalPencairan) {
        this.totalPencairan = totalPencairan;
    }

    public String getTotalKol2() {
        return totalKol2;
    }

    public void setTotalKol2(String totalKol2) {
        this.totalKol2 = totalKol2;
    }

    public String getAvrPencairan() {
        return avrPencairan;
    }

    public void setAvrPencairan(String avrPencairan) {
        this.avrPencairan = avrPencairan;
    }

    public String getAvrNpf() {
        return avrNpf;
    }

    public void setAvrNpf(String avrNpf) {
        this.avrNpf = avrNpf;
    }

    public String getAvrOs() {
        return avrOs;
    }

    public void setAvrOs(String avrOs) {
        this.avrOs = avrOs;
    }
}
