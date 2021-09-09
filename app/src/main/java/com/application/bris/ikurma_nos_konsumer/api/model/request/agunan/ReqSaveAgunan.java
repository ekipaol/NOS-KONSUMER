package com.application.bris.ikurma_nos_konsumer.api.model.request.agunan;

import com.google.gson.annotations.SerializedName;

public class ReqSaveAgunan {

    @SerializedName("idApl")
    private int idApl;
    @SerializedName("Plafond")
    private long Plafond;
    @SerializedName("TotalPlafondCover")
    private long TotalPlafondCover;
    @SerializedName("rekomendasiAgunan")
    private String rekomendasiAgunan;
    @SerializedName("desc_rekomendasi")
    private String desc_rekomendasi;

    public int getIdApl() {
        return idApl;
    }

    public void setIdApl(int idApl) {
        this.idApl = idApl;
    }

    public long getPlafond() {
        return Plafond;
    }

    public void setPlafond(long plafond) {
        Plafond = plafond;
    }

    public long getTotalPlafondCover() {
        return TotalPlafondCover;
    }

    public void setTotalPlafondCover(long totalPlafondCover) {
        TotalPlafondCover = totalPlafondCover;
    }

    public String getRekomendasiAgunan() {
        return rekomendasiAgunan;
    }

    public void setRekomendasiAgunan(String rekomendasiAgunan) {
        this.rekomendasiAgunan = rekomendasiAgunan;
    }

    public String getDesc_rekomendasi() {
        return desc_rekomendasi;
    }

    public void setDesc_rekomendasi(String desc_rekomendasi) {
        this.desc_rekomendasi = desc_rekomendasi;
    }
}
