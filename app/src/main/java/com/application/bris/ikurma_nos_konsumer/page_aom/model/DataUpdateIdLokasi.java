package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class DataUpdateIdLokasi {
    @SerializedName("NAMA_DEBITUR_1")
    private String namaDebitur;
    @SerializedName("NO_ID")
    private String noKtp;
    @SerializedName("nama_pengembang")
    private String namaPengembang;
    @SerializedName("nama_perumahan")
    private String namaPerumahan;
    @SerializedName("npwp_pengembang")
    private String npwpPengembang;
    @SerializedName("id_rumah")
    private String idRumah;

    public String getIdRumah() {
        return idRumah;
    }

    public void setIdRumah(String idRumah) {
        this.idRumah = idRumah;
    }

    public String getNpwpPengembang() {
        return npwpPengembang;
    }

    public void setNpwpPengembang(String npwpPengembang) {
        this.npwpPengembang = npwpPengembang;
    }

    public String getNamaDebitur() {
        return namaDebitur;
    }

    public void setNamaDebitur(String namaDebitur) {
        this.namaDebitur = namaDebitur;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getNamaPengembang() {
        return namaPengembang;
    }

    public void setNamaPengembang(String namaPengembang) {
        this.namaPengembang = namaPengembang;
    }

    public String getNamaPerumahan() {
        return namaPerumahan;
    }

    public void setNamaPerumahan(String namaPerumahan) {
        this.namaPerumahan = namaPerumahan;
    }
}
