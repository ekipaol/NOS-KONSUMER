package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class MparsePendapatanTunjangan {
    @SerializedName("Total_Pendapatan_Tercermin")
    private BigDecimal Total_Pendapatan_Tercermin;
    @SerializedName("Total_Pendapatan_Akseptasi")
    private BigDecimal Total_Pendapatan_Akseptasi;
    @SerializedName("Total_Maksimal_Angsuran")
    private BigDecimal Total_Maksimal_Angsuran;
    @SerializedName("AkseptasiPendapatan")
    private Long AkseptasiPendapatan;
    @SerializedName("ManfaatPensiun")
    private BigDecimal ManfaatPensiun;

    public BigDecimal getTotal_Pendapatan_Tercermin() {
        return Total_Pendapatan_Tercermin;
    }

    public void setTotal_Pendapatan_Tercermin(BigDecimal total_Pendapatan_Tercermin) {
        Total_Pendapatan_Tercermin = total_Pendapatan_Tercermin;
    }

    public BigDecimal getTotal_Pendapatan_Akseptasi() {
        return Total_Pendapatan_Akseptasi;
    }

    public void setTotal_Pendapatan_Akseptasi(BigDecimal total_Pendapatan_Akseptasi) {
        Total_Pendapatan_Akseptasi = total_Pendapatan_Akseptasi;
    }

    public BigDecimal getTotal_Maksimal_Angsuran() {
        return Total_Maksimal_Angsuran;
    }

    public void setTotal_Maksimal_Angsuran(BigDecimal total_Maksimal_Angsuran) {
        Total_Maksimal_Angsuran = total_Maksimal_Angsuran;
    }

    public Long getAkseptasiPendapatan() {
        return AkseptasiPendapatan;
    }

    public void setAkseptasiPendapatan(Long akseptasiPendapatan) {
        AkseptasiPendapatan = akseptasiPendapatan;
    }

    public BigDecimal getManfaatPensiun() {
        return ManfaatPensiun;
    }

    public void setManfaatPensiun(BigDecimal manfaatPensiun) {
        ManfaatPensiun = manfaatPensiun;
    }
}
