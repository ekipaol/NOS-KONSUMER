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
    @SerializedName("Cerminan_Gaji_dan_Tunjangan")
    @Expose
    private String cerminanGajiDanTunjangan;
    @SerializedName("Nama_Bank")
    @Expose
    private String namaBank;
    @SerializedName("Nomor_Rek_Bank")
    @Expose
    private String nomorRekBank;
    @SerializedName("Periode_Date_From")
    @Expose
    private String periodeDateFrom;
    @SerializedName("Periode_Date_To")
    @Expose
    private String periodeDateTo;
    @SerializedName("Total_Kredit")
    @Expose
    private Long totalKredit;
    @SerializedName("Total_Debit")
    @Expose
    private Long totalDebit;
    @SerializedName("Nama_Bank_Tunjangan")
    @Expose
    private String NamaBankTunjangan;
    @SerializedName("No_Rekening_Tunjangan")
    @Expose
    private String NoRekeningTunjangan;
    @SerializedName("Periode_Date_From_Tunjangan")
    @Expose
    private String PeriodeDateFromTunjangan;
    @SerializedName("Periode_Date_To_Tunjangan")
    @Expose
    private String PeriodeDateToTunjangan;
    @SerializedName("Total_Kredit_Tunjangan")
    @Expose
    private Long TotalKreditTunjangan;
    @SerializedName("Total_Debit_Tunjangan")
    @Expose
    private Long TotalDebitTunjangan;

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

    public String getCerminanGajiDanTunjangan() {
        return cerminanGajiDanTunjangan;
    }

    public void setCerminanGajiDanTunjangan(String cerminanGajiDanTunjangan) {
        this.cerminanGajiDanTunjangan = cerminanGajiDanTunjangan;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public String getNomorRekBank() {
        return nomorRekBank;
    }

    public void setNomorRekBank(String nomorRekBank) {
        this.nomorRekBank = nomorRekBank;
    }

    public String getPeriodeDateFrom() {
        return periodeDateFrom;
    }

    public void setPeriodeDateFrom(String periodeDateFrom) {
        this.periodeDateFrom = periodeDateFrom;
    }

    public String getPeriodeDateTo() {
        return periodeDateTo;
    }

    public void setPeriodeDateTo(String periodeDateTo) {
        this.periodeDateTo = periodeDateTo;
    }

    public Long getTotalKredit() {
        return totalKredit;
    }

    public void setTotalKredit(Long totalKredit) {
        this.totalKredit = totalKredit;
    }

    public Long getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(Long totalDebit) {
        this.totalDebit = totalDebit;
    }

    public String getNamaBankTunjangan() {
        return NamaBankTunjangan;
    }

    public void setNamaBankTunjangan(String namaBankTunjangan) {
        NamaBankTunjangan = namaBankTunjangan;
    }

    public String getNoRekeningTunjangan() {
        return NoRekeningTunjangan;
    }

    public void setNoRekeningTunjangan(String noRekeningTunjangan) {
        NoRekeningTunjangan = noRekeningTunjangan;
    }

    public String getPeriodeDateFromTunjangan() {
        return PeriodeDateFromTunjangan;
    }

    public void setPeriodeDateFromTunjangan(String periodeDateFromTunjangan) {
        PeriodeDateFromTunjangan = periodeDateFromTunjangan;
    }

    public String getPeriodeDateToTunjangan() {
        return PeriodeDateToTunjangan;
    }

    public void setPeriodeDateToTunjangan(String periodeDateToTunjangan) {
        PeriodeDateToTunjangan = periodeDateToTunjangan;
    }

    public Long getTotalKreditTunjangan() {
        return TotalKreditTunjangan;
    }

    public void setTotalKreditTunjangan(Long totalKreditTunjangan) {
        TotalKreditTunjangan = totalKreditTunjangan;
    }

    public Long getTotalDebitTunjangan() {
        return TotalDebitTunjangan;
    }

    public void setTotalDebitTunjangan(Long totalDebitTunjangan) {
        TotalDebitTunjangan = totalDebitTunjangan;
    }
}
