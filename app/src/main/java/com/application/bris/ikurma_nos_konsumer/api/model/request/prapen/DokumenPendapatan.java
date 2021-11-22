package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DokumenPendapatan {
    @SerializedName("Simulasi_Pendapatan_Saat_Pen")
    @Expose
    private Double simulasiPendapatanSaatPen;
    @SerializedName("Akseptasi_Pendapatan")
    @Expose
    private String akseptasiPendapatan;
    @SerializedName("Total_Gaji_Bersih_P1")
    @Expose
    private Long totalGajiBersihP1;
    @SerializedName("Periode_Gaji_P1")
    @Expose
    private String periodeGajiP1;
    @SerializedName("Total_Tunjangan_Bersih_P1")
    @Expose
    private Long totalTunjanganBersihP1;
    @SerializedName("Periode_Tunjangan_P1")
    @Expose
    private String periodeTunjanganP1;
    @SerializedName("Total_Gaji_Bersih_P2")
    @Expose
    private Long totalGajiBersihP2;
    @SerializedName("Periode_Gaji_P2")
    @Expose
    private String periodeGajiP2;
    @SerializedName("Total_Tunjangan_Bersih_P2")
    @Expose
    private Long totalTunjanganBersihP2;
    @SerializedName("Periode_Tunjangan_P2")
    @Expose
    private String periodeTunjanganP2;
    @SerializedName("Total_Gaji_Bersih_P3")
    @Expose
    private Long totalGajiBersihP3;
    @SerializedName("Periode_Gaji_P3")
    @Expose
    private String periodeGajiP3;
    @SerializedName("Total_Tunjangan_Bersih_P3")
    @Expose
    private Long totalTunjanganBersihP3;
    @SerializedName("Periode_Tunjangan_P3")
    @Expose
    private String periodeTunjanganP3;
    @SerializedName("Cerminan_Gaji_dan_TunjD3")
    @Expose
    private String cerminanGajiDanTunjD3;
    @SerializedName("Nama_Bank_Gaji")
    @Expose
    private String namaBankGaji;
    @SerializedName("Nomor_Rek_Bank_Gaji")
    @Expose
    private String nomorRekBankGaji;
    @SerializedName("Periode_Date_FromGaji")
    @Expose
    private String periodeDateFromGaji;
    @SerializedName("Periode_Date_ToGaji")
    @Expose
    private String periodeDateToGaji;
    @SerializedName("Total_KreditGaji")
    @Expose
    private Long totalKreditGaji;
    @SerializedName("Total_DebitGaji")
    @Expose
    private Long totalDebitGaji;
    @SerializedName("Nama_Bank_Tunjangan")
    @Expose
    private String namaBankTunjangan;
    @SerializedName("No_Rekening_Tunjangan")
    @Expose
    private String noRekeningTunjangan;
    @SerializedName("Periode_Date_FromTunjangan")
    @Expose
    private String periodeDateFromTunjangan;
    @SerializedName("Periode_Date_ToTunjangan")
    @Expose
    private String periodeDateToTunjangan;
    @SerializedName("Total_KreditTunjangan")
    @Expose
    private Long totalKreditTunjangan;
    @SerializedName("Total_DebitTunjangan")
    @Expose
    private Long totalDebitTunjangan;

    public Double getSimulasiPendapatanSaatPen() {
        return simulasiPendapatanSaatPen;
    }

    public void setSimulasiPendapatanSaatPen(Double simulasiPendapatanSaatPen) {
        this.simulasiPendapatanSaatPen = simulasiPendapatanSaatPen;
    }

    public String getAkseptasiPendapatan() {
        return akseptasiPendapatan;
    }

    public void setAkseptasiPendapatan(String akseptasiPendapatan) {
        this.akseptasiPendapatan = akseptasiPendapatan;
    }

    public Long getTotalGajiBersihP1() {
        return totalGajiBersihP1;
    }

    public void setTotalGajiBersihP1(Long totalGajiBersihP1) {
        this.totalGajiBersihP1 = totalGajiBersihP1;
    }

    public String getPeriodeGajiP1() {
        return periodeGajiP1;
    }

    public void setPeriodeGajiP1(String periodeGajiP1) {
        this.periodeGajiP1 = periodeGajiP1;
    }

    public Long getTotalTunjanganBersihP1() {
        return totalTunjanganBersihP1;
    }

    public void setTotalTunjanganBersihP1(Long totalTunjanganBersihP1) {
        this.totalTunjanganBersihP1 = totalTunjanganBersihP1;
    }

    public String getPeriodeTunjanganP1() {
        return periodeTunjanganP1;
    }

    public void setPeriodeTunjanganP1(String periodeTunjanganP1) {
        this.periodeTunjanganP1 = periodeTunjanganP1;
    }

    public Long getTotalGajiBersihP2() {
        return totalGajiBersihP2;
    }

    public void setTotalGajiBersihP2(Long totalGajiBersihP2) {
        this.totalGajiBersihP2 = totalGajiBersihP2;
    }

    public String getPeriodeGajiP2() {
        return periodeGajiP2;
    }

    public void setPeriodeGajiP2(String periodeGajiP2) {
        this.periodeGajiP2 = periodeGajiP2;
    }

    public Long getTotalTunjanganBersihP2() {
        return totalTunjanganBersihP2;
    }

    public void setTotalTunjanganBersihP2(Long totalTunjanganBersihP2) {
        this.totalTunjanganBersihP2 = totalTunjanganBersihP2;
    }

    public String getPeriodeTunjanganP2() {
        return periodeTunjanganP2;
    }

    public void setPeriodeTunjanganP2(String periodeTunjanganP2) {
        this.periodeTunjanganP2 = periodeTunjanganP2;
    }

    public Long getTotalGajiBersihP3() {
        return totalGajiBersihP3;
    }

    public void setTotalGajiBersihP3(Long totalGajiBersihP3) {
        this.totalGajiBersihP3 = totalGajiBersihP3;
    }

    public String getPeriodeGajiP3() {
        return periodeGajiP3;
    }

    public void setPeriodeGajiP3(String periodeGajiP3) {
        this.periodeGajiP3 = periodeGajiP3;
    }

    public Long getTotalTunjanganBersihP3() {
        return totalTunjanganBersihP3;
    }

    public void setTotalTunjanganBersihP3(Long totalTunjanganBersihP3) {
        this.totalTunjanganBersihP3 = totalTunjanganBersihP3;
    }

    public String getPeriodeTunjanganP3() {
        return periodeTunjanganP3;
    }

    public void setPeriodeTunjanganP3(String periodeTunjanganP3) {
        this.periodeTunjanganP3 = periodeTunjanganP3;
    }

    public String getCerminanGajiDanTunjD3() {
        return cerminanGajiDanTunjD3;
    }

    public void setCerminanGajiDanTunjD3(String cerminanGajiDanTunjD3) {
        this.cerminanGajiDanTunjD3 = cerminanGajiDanTunjD3;
    }

    public String getNamaBankGaji() {
        return namaBankGaji;
    }

    public void setNamaBankGaji(String namaBankGaji) {
        this.namaBankGaji = namaBankGaji;
    }

    public String getNomorRekBankGaji() {
        return nomorRekBankGaji;
    }

    public void setNomorRekBankGaji(String nomorRekBankGaji) {
        this.nomorRekBankGaji = nomorRekBankGaji;
    }

    public String getPeriodeDateFromGaji() {
        return periodeDateFromGaji;
    }

    public void setPeriodeDateFromGaji(String periodeDateFromGaji) {
        this.periodeDateFromGaji = periodeDateFromGaji;
    }

    public String getPeriodeDateToGaji() {
        return periodeDateToGaji;
    }

    public void setPeriodeDateToGaji(String periodeDateToGaji) {
        this.periodeDateToGaji = periodeDateToGaji;
    }

    public Long getTotalKreditGaji() {
        return totalKreditGaji;
    }

    public void setTotalKreditGaji(Long totalKreditGaji) {
        this.totalKreditGaji = totalKreditGaji;
    }

    public Long getTotalDebitGaji() {
        return totalDebitGaji;
    }

    public void setTotalDebitGaji(Long totalDebitGaji) {
        this.totalDebitGaji = totalDebitGaji;
    }

    public String getNamaBankTunjangan() {
        return namaBankTunjangan;
    }

    public void setNamaBankTunjangan(String namaBankTunjangan) {
        this.namaBankTunjangan = namaBankTunjangan;
    }

    public String getNoRekeningTunjangan() {
        return noRekeningTunjangan;
    }

    public void setNoRekeningTunjangan(String noRekeningTunjangan) {
        this.noRekeningTunjangan = noRekeningTunjangan;
    }

    public String getPeriodeDateFromTunjangan() {
        return periodeDateFromTunjangan;
    }

    public void setPeriodeDateFromTunjangan(String periodeDateFromTunjangan) {
        this.periodeDateFromTunjangan = periodeDateFromTunjangan;
    }

    public String getPeriodeDateToTunjangan() {
        return periodeDateToTunjangan;
    }

    public void setPeriodeDateToTunjangan(String periodeDateToTunjangan) {
        this.periodeDateToTunjangan = periodeDateToTunjangan;
    }

    public Long getTotalKreditTunjangan() {
        return totalKreditTunjangan;
    }

    public void setTotalKreditTunjangan(Long totalKreditTunjangan) {
        this.totalKreditTunjangan = totalKreditTunjangan;
    }

    public Long getTotalDebitTunjangan() {
        return totalDebitTunjangan;
    }

    public void setTotalDebitTunjangan(Long totalDebitTunjangan) {
        this.totalDebitTunjangan = totalDebitTunjangan;
    }
}
