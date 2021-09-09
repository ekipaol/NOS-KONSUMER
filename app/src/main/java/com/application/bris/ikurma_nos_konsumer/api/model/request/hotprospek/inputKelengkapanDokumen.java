package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 20/06/2019.
 */

public class inputKelengkapanDokumen {
    @SerializedName("idAplikasi")
    public Integer idAplikasi;
    @SerializedName("aplikasiPermohonan")
    public Boolean aplikasiPermohonan;
    @SerializedName("fcKK")
    public Boolean fcKK;
    @SerializedName("fcKtp")
    public Boolean fcKtp;
    @SerializedName("fcNpwp")
    public Boolean fcNpwp;
    @SerializedName("fcPasPhoto")
    public Boolean fcPasPhoto;
    @SerializedName("fcSIUP")
    public Boolean fcSIUP;
    @SerializedName("fcSuratNikah")
    public Boolean fcSuratNikah;
    @SerializedName("laporanKeuangan")
    public Boolean laporanKeuangan;
    @SerializedName("suratKeteranganLunasKUR")
    public Boolean suratKeteranganLunasKUR;
    @SerializedName("suratPernyataanKUR")
    public Boolean suratPernyataanKUR;
    @SerializedName("suratPernyataanNasabah")
    public Boolean suratPernyataanNasabah;
    @SerializedName("idPhotoaplikasiPermohonan")
    public Integer idPhotoaplikasiPermohonan;
    @SerializedName("idPhotofcKK")
    public Integer idPhotofcKK;
    @SerializedName("idPhotofcKtp")
    public Integer idPhotofcKtp;
    @SerializedName("idPhotofcNpwp")
    public Integer idPhotofcNpwp;
    @SerializedName("idPhotofcPasPhoto")
    public Integer idPhotofcPasPhoto;
    @SerializedName("idPhotofcSIUP")
    public Integer idPhotofcSIUP;
    @SerializedName("idPhotofcSuratNikah")
    public Integer idPhotofcSuratNikah;
    @SerializedName("idPhotolaporanKeuangan")
    public Integer idPhotolaporanKeuangan;
    @SerializedName("idPhotosuratKeteranganLunasKUR")
    public Integer idPhotosuratKeteranganLunasKUR;
    @SerializedName("idPhotosuratPernyataanKUR")
    public Integer idPhotosuratPernyataanKUR;
    @SerializedName("idPhotosuratPernyataanNasabah")
    public Integer idPhotosuratPernyataanNasabah;
    @SerializedName("noSKU")
    public String noSKU;

    public inputKelengkapanDokumen(Integer idAplikasi, Boolean aplikasiPermohonan, Boolean fcKK, Boolean fcKtp, Boolean fcNpwp, Boolean fcPasPhoto, Boolean fcSIUP, Boolean fcSuratNikah, Boolean laporanKeuangan, Boolean suratKeteranganLunasKUR, Boolean suratPernyataanKUR, Boolean suratPernyataanNasabah, Integer idPhotoaplikasiPermohonan, Integer idPhotofcKK, Integer idPhotofcKtp, Integer idPhotofcNpwp, Integer idPhotofcPasPhoto, Integer idPhotofcSIUP, Integer idPhotofcSuratNikah, Integer idPhotolaporanKeuangan, Integer idPhotosuratKeteranganLunasKUR, Integer idPhotosuratPernyataanKUR, Integer idPhotosuratPernyataanNasabah, String noSKU) {
        this.idAplikasi = idAplikasi;
        this.aplikasiPermohonan = aplikasiPermohonan;
        this.fcKK = fcKK;
        this.fcKtp = fcKtp;
        this.fcNpwp = fcNpwp;
        this.fcPasPhoto = fcPasPhoto;
        this.fcSIUP = fcSIUP;
        this.fcSuratNikah = fcSuratNikah;
        this.laporanKeuangan = laporanKeuangan;
        this.suratKeteranganLunasKUR = suratKeteranganLunasKUR;
        this.suratPernyataanKUR = suratPernyataanKUR;
        this.suratPernyataanNasabah = suratPernyataanNasabah;
        this.idPhotoaplikasiPermohonan = idPhotoaplikasiPermohonan;
        this.idPhotofcKK = idPhotofcKK;
        this.idPhotofcKtp = idPhotofcKtp;
        this.idPhotofcNpwp = idPhotofcNpwp;
        this.idPhotofcPasPhoto = idPhotofcPasPhoto;
        this.idPhotofcSIUP = idPhotofcSIUP;
        this.idPhotofcSuratNikah = idPhotofcSuratNikah;
        this.idPhotolaporanKeuangan = idPhotolaporanKeuangan;
        this.idPhotosuratKeteranganLunasKUR = idPhotosuratKeteranganLunasKUR;
        this.idPhotosuratPernyataanKUR = idPhotosuratPernyataanKUR;
        this.idPhotosuratPernyataanNasabah = idPhotosuratPernyataanNasabah;
        this.noSKU = noSKU;
    }

    public void setIdAplikasi(Integer idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public void setAplikasiPermohonan(Boolean aplikasiPermohonan) {
        this.aplikasiPermohonan = aplikasiPermohonan;
    }

    public void setFcKK(Boolean fcKK) {
        this.fcKK = fcKK;
    }

    public void setFcKtp(Boolean fcKtp) {
        this.fcKtp = fcKtp;
    }

    public void setFcNpwp(Boolean fcNpwp) {
        this.fcNpwp = fcNpwp;
    }

    public void setFcPasPhoto(Boolean fcPasPhoto) {
        this.fcPasPhoto = fcPasPhoto;
    }

    public void setFcSIUP(Boolean fcSIUP) {
        this.fcSIUP = fcSIUP;
    }

    public void setFcSuratNikah(Boolean fcSuratNikah) {
        this.fcSuratNikah = fcSuratNikah;
    }

    public void setLaporanKeuangan(Boolean laporanKeuangan) {
        this.laporanKeuangan = laporanKeuangan;
    }

    public void setSuratKeteranganLunasKUR(Boolean suratKeteranganLunasKUR) {
        this.suratKeteranganLunasKUR = suratKeteranganLunasKUR;
    }

    public void setSuratPernyataanKUR(Boolean suratPernyataanKUR) {
        this.suratPernyataanKUR = suratPernyataanKUR;
    }

    public void setSuratPernyataanNasabah(Boolean suratPernyataanNasabah) {
        this.suratPernyataanNasabah = suratPernyataanNasabah;
    }

    public void setIdPhotoaplikasiPermohonan(Integer idPhotoaplikasiPermohonan) {
        this.idPhotoaplikasiPermohonan = idPhotoaplikasiPermohonan;
    }

    public void setIdPhotofcKK(Integer idPhotofcKK) {
        this.idPhotofcKK = idPhotofcKK;
    }

    public void setIdPhotofcKtp(Integer idPhotofcKtp) {
        this.idPhotofcKtp = idPhotofcKtp;
    }

    public void setIdPhotofcNpwp(Integer idPhotofcNpwp) {
        this.idPhotofcNpwp = idPhotofcNpwp;
    }

    public void setIdPhotofcPasPhoto(Integer idPhotofcPasPhoto) {
        this.idPhotofcPasPhoto = idPhotofcPasPhoto;
    }

    public void setIdPhotofcSIUP(Integer idPhotofcSIUP) {
        this.idPhotofcSIUP = idPhotofcSIUP;
    }

    public void setIdPhotofcSuratNikah(Integer idPhotofcSuratNikah) {
        this.idPhotofcSuratNikah = idPhotofcSuratNikah;
    }

    public void setIdPhotolaporanKeuangan(Integer idPhotolaporanKeuangan) {
        this.idPhotolaporanKeuangan = idPhotolaporanKeuangan;
    }

    public void setIdPhotosuratKeteranganLunasKUR(Integer idPhotosuratKeteranganLunasKUR) {
        this.idPhotosuratKeteranganLunasKUR = idPhotosuratKeteranganLunasKUR;
    }

    public void setIdPhotosuratPernyataanKUR(Integer idPhotosuratPernyataanKUR) {
        this.idPhotosuratPernyataanKUR = idPhotosuratPernyataanKUR;
    }

    public void setIdPhotosuratPernyataanNasabah(Integer idPhotosuratPernyataanNasabah) {
        this.idPhotosuratPernyataanNasabah = idPhotosuratPernyataanNasabah;
    }

    public void setNoSKU(String noSKU) {
        this.noSKU = noSKU;
    }
}
