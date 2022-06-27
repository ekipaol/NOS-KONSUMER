package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseDataGajiTunjangan;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateAkseptasiPendapatan {
    @SerializedName("AkseptasiPendapatan")
    private String AkseptasiPendapatan;
    @SerializedName("PendapatanTunjangan")
    private List<SubAkseptasiPendapatan> PendapatanTunjangan;
    @SerializedName("DokumenPendapatan")
    private ReqDocument DokumenPendapatan;
    @SerializedName("DataGajiTunjangan")
    private MparseDataGajiTunjangan DataGajiTunjangan;

    public String getAkseptasiPendapatan() {
        return AkseptasiPendapatan;
    }

    public void setAkseptasiPendapatan(String akseptasiPendapatan) {
        AkseptasiPendapatan = akseptasiPendapatan;
    }

    public List<SubAkseptasiPendapatan> getPendapatanTunjangan() {
        return PendapatanTunjangan;
    }

    public void setPendapatanTunjangan(List<SubAkseptasiPendapatan> pendapatanTunjangan) {
        PendapatanTunjangan = pendapatanTunjangan;
    }

    public ReqDocument getDokumenPendapatan() {
        return DokumenPendapatan;
    }

    public void setDokumenPendapatan(ReqDocument dokumenPendapatan) {
        DokumenPendapatan = dokumenPendapatan;
    }

    public MparseDataGajiTunjangan getDataGajiTunjangan() {
        return DataGajiTunjangan;
    }

    public void setDataGajiTunjangan(MparseDataGajiTunjangan dataGajiTunjangan) {
        DataGajiTunjangan = dataGajiTunjangan;
    }
}
