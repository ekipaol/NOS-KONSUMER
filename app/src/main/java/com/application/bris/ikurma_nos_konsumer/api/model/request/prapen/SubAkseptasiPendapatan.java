package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class SubAkseptasiPendapatan {
    @SerializedName("PendapatanTunjanganId")
    private Long PendapatanTunjanganId;

    @SerializedName("PendapatanId")
    private Long PendapatanId;

    @SerializedName("Keterangan")
    private String Keterangan;

    @SerializedName("Pendapatan_Tercermin")
    private BigDecimal Pendapatan_Tercermin;

    @SerializedName("NilaiSetelahAkseptasi")
    private BigDecimal NilaiSetelahAkseptasi;

    @SerializedName("Status_Payroll")
    private String Status_Payroll;

    public Long getPendapatanTunjanganId() {
        return PendapatanTunjanganId;
    }

    public void setPendapatanTunjanganId(Long pendapatanTunjanganId) {
        PendapatanTunjanganId = pendapatanTunjanganId;
    }

    public Long getPendapatanId() {
        return PendapatanId;
    }

    public void setPendapatanId(Long pendapatanId) {
        PendapatanId = pendapatanId;
    }

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String keterangan) {
        Keterangan = keterangan;
    }

    public BigDecimal getPendapatan_Tercermin() {
        return Pendapatan_Tercermin;
    }

    public void setPendapatan_Tercermin(BigDecimal pendapatan_Tercermin) {
        Pendapatan_Tercermin = pendapatan_Tercermin;
    }

    public BigDecimal getNilaiSetelahAkseptasi() {
        return NilaiSetelahAkseptasi;
    }

    public void setNilaiSetelahAkseptasi(BigDecimal nilaiSetelahAkseptasi) {
        NilaiSetelahAkseptasi = nilaiSetelahAkseptasi;
    }

    public String getStatus_Payroll() {
        return Status_Payroll;
    }

    public void setStatus_Payroll(String status_Payroll) {
        Status_Payroll = status_Payroll;
    }
}
