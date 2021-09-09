package com.application.bris.ikurma_nos_konsumer.api.model.request.flpp;

import com.google.gson.annotations.SerializedName;

public class ReqSimpanUpdateIdLokasi {
    @SerializedName("ktp")
    private String ktp;
    @SerializedName("npwp_pengembang")
    private String npwpPengembang;
    @SerializedName("nama_pengembang")
    private String namaPengembang;
    @SerializedName("nama_perumahan")
    private String namaPerumahan;
    @SerializedName("id_rumah_baru")
    private String idRumahBaru;
    @SerializedName("idAplikasi")
    private String idAplikasi;
    @SerializedName("parameterTesting")
    private String parameterTesting;
    @SerializedName("uid")
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getParameterTesting() {
        return parameterTesting;
    }

    public void setParameterTesting(String parameterTesting) {
        this.parameterTesting = parameterTesting;
    }

    public String getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(String idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getNpwpPengembang() {
        return npwpPengembang;
    }

    public void setNpwpPengembang(String npwpPengembang) {
        this.npwpPengembang = npwpPengembang;
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

    public String getIdRumahBaru() {
        return idRumahBaru;
    }

    public void setIdRumahBaru(String idRumahBaru) {
        this.idRumahBaru = idRumahBaru;
    }
}
