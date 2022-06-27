package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

import com.google.gson.annotations.SerializedName;

public class MparseDataGajiTunjangan {
    @SerializedName("Nama_Bank_Gaji")
    private String Nama_Bank_Gaji;
    @SerializedName("Nomor_Rek_Bank_Gaji")
    private String Nomor_Rek_Bank_Gaji;
    @SerializedName("Periode_Date_FromGaji")
    private String Periode_Date_FromGaji;
    @SerializedName("Periode_Date_ToGaji")
    private String Periode_Date_ToGaji;
    @SerializedName("Total_KreditGaji")
    private Long Total_KreditGaji;
    @SerializedName("Total_DebitGaji")
    private Long Total_DebitGaji;
    @SerializedName("Nama_Bank_Tunjangan")
    private String Nama_Bank_Tunjangan;
    @SerializedName("No_Rekening_Tunjangan")
    private String No_Rekening_Tunjangan;
    @SerializedName("Periode_Date_FromTunjangan")
    private String Periode_Date_FromTunjangan;
    @SerializedName("Periode_Date_ToTunjangan")
    private String Periode_Date_ToTunjangan;
    @SerializedName("Total_KreditTunjangan")
    private Long Total_KreditTunjangan;
    @SerializedName("Total_DebitTunjangan")
    private Long Total_DebitTunjangan;

    public String getNama_Bank_Gaji() {
        return Nama_Bank_Gaji;
    }

    public void setNama_Bank_Gaji(String nama_Bank_Gaji) {
        Nama_Bank_Gaji = nama_Bank_Gaji;
    }

    public String getNomor_Rek_Bank_Gaji() {
        return Nomor_Rek_Bank_Gaji;
    }

    public void setNomor_Rek_Bank_Gaji(String nomor_Rek_Bank_Gaji) {
        Nomor_Rek_Bank_Gaji = nomor_Rek_Bank_Gaji;
    }

    public String getPeriode_Date_FromGaji() {
        return Periode_Date_FromGaji;
    }

    public void setPeriode_Date_FromGaji(String periode_Date_FromGaji) {
        Periode_Date_FromGaji = periode_Date_FromGaji;
    }

    public String getPeriode_Date_ToGaji() {
        return Periode_Date_ToGaji;
    }

    public void setPeriode_Date_ToGaji(String periode_Date_ToGaji) {
        Periode_Date_ToGaji = periode_Date_ToGaji;
    }

    public Long getTotal_KreditGaji() {
        return Total_KreditGaji;
    }

    public void setTotal_KreditGaji(Long total_KreditGaji) {
        Total_KreditGaji = total_KreditGaji;
    }

    public Long getTotal_DebitGaji() {
        return Total_DebitGaji;
    }

    public void setTotal_DebitGaji(Long total_DebitGaji) {
        Total_DebitGaji = total_DebitGaji;
    }

    public String getNama_Bank_Tunjangan() {
        return Nama_Bank_Tunjangan;
    }

    public void setNama_Bank_Tunjangan(String nama_Bank_Tunjangan) {
        Nama_Bank_Tunjangan = nama_Bank_Tunjangan;
    }

    public String getNo_Rekening_Tunjangan() {
        return No_Rekening_Tunjangan;
    }

    public void setNo_Rekening_Tunjangan(String no_Rekening_Tunjangan) {
        No_Rekening_Tunjangan = no_Rekening_Tunjangan;
    }

    public String getPeriode_Date_FromTunjangan() {
        return Periode_Date_FromTunjangan;
    }

    public void setPeriode_Date_FromTunjangan(String periode_Date_FromTunjangan) {
        Periode_Date_FromTunjangan = periode_Date_FromTunjangan;
    }

    public String getPeriode_Date_ToTunjangan() {
        return Periode_Date_ToTunjangan;
    }

    public void setPeriode_Date_ToTunjangan(String periode_Date_ToTunjangan) {
        Periode_Date_ToTunjangan = periode_Date_ToTunjangan;
    }

    public Long getTotal_KreditTunjangan() {
        return Total_KreditTunjangan;
    }

    public void setTotal_KreditTunjangan(Long total_KreditTunjangan) {
        Total_KreditTunjangan = total_KreditTunjangan;
    }

    public Long getTotal_DebitTunjangan() {
        return Total_DebitTunjangan;
    }

    public void setTotal_DebitTunjangan(Long total_DebitTunjangan) {
        Total_DebitTunjangan = total_DebitTunjangan;
    }
}
