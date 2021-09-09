package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class FotoFlpp {
    @SerializedName("nama")
    private String nama;

    @SerializedName("idRegistrasi")
    private String idRegistrasi;

    @SerializedName("ktp")
    private String ktp;

    @SerializedName("qrcode")
    private String qrcode;

    @SerializedName("foto_ktp")
    private String foto_ktp;

    @SerializedName("foto_wajah")
    private String foto_wajah;

    @SerializedName("npwp")
    private String npwp;

    @SerializedName("err_desc")
    private String err_desc;

    public String getErr_desc() {
        return err_desc;
    }

    public void setErr_desc(String err_desc) {
        this.err_desc = err_desc;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIdRegistrasi() {
        return idRegistrasi;
    }

    public void setIdRegistrasi(String idRegistrasi) {
        this.idRegistrasi = idRegistrasi;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getFoto_ktp() {
        return foto_ktp;
    }

    public void setFoto_ktp(String foto_ktp) {
        this.foto_ktp = foto_ktp;
    }

    public String getFoto_wajah() {
        return foto_wajah;
    }

    public void setFoto_wajah(String foto_wajah) {
        this.foto_wajah = foto_wajah;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }
}
