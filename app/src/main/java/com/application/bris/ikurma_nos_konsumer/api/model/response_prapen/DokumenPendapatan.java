package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

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
    private Integer totalTunjanganBersihP1;
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
    private Integer totalTunjanganBersihP2;
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
    private Integer totalTunjanganBersihP3;
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
    private Integer totalKredit;
    @SerializedName("Total_Debit")
    @Expose
    private Integer totalDebit;
    @SerializedName("Nama_Bank_Tunjangan")
    @Expose
    private String namaBankTunjangan;
    @SerializedName("No_Rekening_Tunjangan")
    @Expose
    private String noRekeningTunjangan;
    @SerializedName("Periode_Date_From_Tunjangan")
    @Expose
    private String periodeDateFromTunjangan;
    @SerializedName("Periode_Date_To_Tunjangan")
    @Expose
    private String periodeDateToTunjangan;
    @SerializedName("Total_Kredit_Tunjangan")
    @Expose
    private Integer totalKreditTunjangan;
    @SerializedName("Total_Debit_Tunjangan")
    @Expose
    private Integer totalDebitTunjangan;

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

    public Integer getTotalTunjanganBersihP1() {
        return totalTunjanganBersihP1;
    }

    public void setTotalTunjanganBersihP1(Integer totalTunjanganBersihP1) {
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

    public Integer getTotalTunjanganBersihP2() {
        return totalTunjanganBersihP2;
    }

    public void setTotalTunjanganBersihP2(Integer totalTunjanganBersihP2) {
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

    public Integer getTotalTunjanganBersihP3() {
        return totalTunjanganBersihP3;
    }

    public void setTotalTunjanganBersihP3(Integer totalTunjanganBersihP3) {
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

    public Integer getTotalKredit() {
        return totalKredit;
    }

    public void setTotalKredit(Integer totalKredit) {
        this.totalKredit = totalKredit;
    }

    public Integer getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(Integer totalDebit) {
        this.totalDebit = totalDebit;
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

    public Integer getTotalKreditTunjangan() {
        return totalKreditTunjangan;
    }

    public void setTotalKreditTunjangan(Integer totalKreditTunjangan) {
        this.totalKreditTunjangan = totalKreditTunjangan;
    }

    public Integer getTotalDebitTunjangan() {
        return totalDebitTunjangan;
    }

    public void setTotalDebitTunjangan(Integer totalDebitTunjangan) {
        this.totalDebitTunjangan = totalDebitTunjangan;
    }
}
