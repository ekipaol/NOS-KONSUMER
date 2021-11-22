package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MParseDataUpdateVerifikasi {

    @SerializedName("Cerminan_Gaji_dan_TunjD4")
    @Expose
    private String cerminanGajiDanTunjD4;
    @SerializedName("NomorRekeningTercerminD4")
    @Expose
    private String nomorRekeningTercerminD4;
    @SerializedName("HasilVerifikasiGajiD4")
    @Expose
    private String hasilVerifikasiGajiD4;
    @SerializedName("HasilVerifikasiTunjanganD4")
    @Expose
    private String hasilVerifikasiTunjanganD4;
    @SerializedName("TotalPendTerverifikasiD4")
    @Expose
    private Integer totalPendTerverifikasiD4;
    @SerializedName("CttnTotalendTerverifikasiD4")
    @Expose
    private String cttnTotalendTerverifikasiD4;
    @SerializedName("PerhitPendapatanSaatPensD4")
    @Expose
    private String perhitPendapatanSaatPensD4;
    @SerializedName("CttnHItPendapatanSaatPensD4")
    @Expose
    private String cttnHItPendapatanSaatPensD4;

    public String getCerminanGajiDanTunjD4() {
        return cerminanGajiDanTunjD4;
    }

    public void setCerminanGajiDanTunjD4(String cerminanGajiDanTunjD4) {
        this.cerminanGajiDanTunjD4 = cerminanGajiDanTunjD4;
    }

    public String getNomorRekeningTercerminD4() {
        return nomorRekeningTercerminD4;
    }

    public void setNomorRekeningTercerminD4(String nomorRekeningTercerminD4) {
        this.nomorRekeningTercerminD4 = nomorRekeningTercerminD4;
    }

    public String getHasilVerifikasiGajiD4() {
        return hasilVerifikasiGajiD4;
    }

    public void setHasilVerifikasiGajiD4(String hasilVerifikasiGajiD4) {
        this.hasilVerifikasiGajiD4 = hasilVerifikasiGajiD4;
    }

    public String getHasilVerifikasiTunjanganD4() {
        return hasilVerifikasiTunjanganD4;
    }

    public void setHasilVerifikasiTunjanganD4(String hasilVerifikasiTunjanganD4) {
        this.hasilVerifikasiTunjanganD4 = hasilVerifikasiTunjanganD4;
    }

    public Integer getTotalPendTerverifikasiD4() {
        return totalPendTerverifikasiD4;
    }

    public void setTotalPendTerverifikasiD4(Integer totalPendTerverifikasiD4) {
        this.totalPendTerverifikasiD4 = totalPendTerverifikasiD4;
    }

    public String getCttnTotalendTerverifikasiD4() {
        return cttnTotalendTerverifikasiD4;
    }

    public void setCttnTotalendTerverifikasiD4(String cttnTotalendTerverifikasiD4) {
        this.cttnTotalendTerverifikasiD4 = cttnTotalendTerverifikasiD4;
    }

    public String getPerhitPendapatanSaatPensD4() {
        return perhitPendapatanSaatPensD4;
    }

    public void setPerhitPendapatanSaatPensD4(String perhitPendapatanSaatPensD4) {
        this.perhitPendapatanSaatPensD4 = perhitPendapatanSaatPensD4;
    }

    public String getCttnHItPendapatanSaatPensD4() {
        return cttnHItPendapatanSaatPensD4;
    }

    public void setCttnHItPendapatanSaatPensD4(String cttnHItPendapatanSaatPensD4) {
        this.cttnHItPendapatanSaatPensD4 = cttnHItPendapatanSaatPensD4;
    }
}
