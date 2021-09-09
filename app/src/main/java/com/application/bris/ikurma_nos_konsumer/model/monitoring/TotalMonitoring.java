package com.application.bris.ikurma_nos_konsumer.model.monitoring;

import com.google.gson.annotations.SerializedName;

public class TotalMonitoring {

    @SerializedName("totalOs")
    private String totalOs;

    @SerializedName("totalNpf")
    private String totalNpf;

    @SerializedName("totalDpk")
    private String totalDpk;

    @SerializedName("totalKol2")
    private String totalKol2;

    @SerializedName("totalPencairan")
    private String totalPencairan;

    public String getTotalOs() {
        return totalOs;
    }

    public void setTotalOs(String totalOs) {
        this.totalOs = totalOs;
    }

    public String getTotalNpf() {
        return totalNpf;
    }

    public void setTotalNpf(String totalNpf) {
        this.totalNpf = totalNpf;
    }

    public String getTotalDpk() {
        return totalDpk;
    }

    public void setTotalDpk(String totalDpk) {
        this.totalDpk = totalDpk;
    }

    public String getTotalKol2() {
        return totalKol2;
    }

    public void setTotalKol2(String totalKol2) {
        this.totalKol2 = totalKol2;
    }

    public String getTotalPencairan() {
        return totalPencairan;
    }

    public void setTotalPencairan(String totalPencairan) {
        this.totalPencairan = totalPencairan;
    }
}
