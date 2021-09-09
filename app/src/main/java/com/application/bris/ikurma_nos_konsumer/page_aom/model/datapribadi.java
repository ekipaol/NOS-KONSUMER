package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 14/05/2019.
 */

public class datapribadi {

    @SerializedName("NAMA_IBU_KANDUNG")
    private String nama_ibu_kandung;
    @SerializedName("KELURAHAN_DOMISILI")
    private String kelurahan_domisili;
    @SerializedName("PROPINSI_DOMISILI")
    private String propinsi_domisili;
    @SerializedName("NAMA_NASABAH")
    private String nama_nasabah;
    @SerializedName("TEMPAT_LAHIR")
    private String tempat_lahir;
    @SerializedName("FID_PHOTO")
    private int fid_photo;
    @SerializedName("KODE_POS_DOMISILI")
    private String kode_pos_domisili;
    @SerializedName("ALAMAT_DOMISILI")
    private String alamat_domisili;
    @SerializedName("CIF_BRINETS")
    private String cif_brinets;
    @SerializedName("TIPE_DEBITUR")
    private String tipe_debitur;
    @SerializedName("KECAMATAN_DOMISILI")
    private String kecamatan_domisili;
    @SerializedName("NO_KTP")
    private String no_ktp;
    @SerializedName("CIF_CLAS")
    private String cif_clas;
    @SerializedName("TANGGAL_LAHIR")
    private String tanggal_lahir;
    @SerializedName("RT_DOMISILI")
    private String rt_domisili;
    @SerializedName("RW_DOMISILI")
    private String rw_domisili;
    @SerializedName("KOTA_DOMISILI")
    private String kota_domisili;

    public datapribadi(String nama_ibu_kandung, String kelurahan_domisili, String propinsi_domisili, String nama_nasabah, String tempat_lahir, int fid_photo, String kode_pos_domisili, String alamat_domisili, String cif_brinets, String tipe_debitur, String kecamatan_domisili, String no_ktp, String cif_clas, String tanggal_lahir, String rt_domisili, String rw_domisili, String kota_domisili) {
        this.nama_ibu_kandung = nama_ibu_kandung;
        this.kelurahan_domisili = kelurahan_domisili;
        this.propinsi_domisili = propinsi_domisili;
        this.nama_nasabah = nama_nasabah;
        this.tempat_lahir = tempat_lahir;
        this.fid_photo = fid_photo;
        this.kode_pos_domisili = kode_pos_domisili;
        this.alamat_domisili = alamat_domisili;
        this.cif_brinets = cif_brinets;
        this.tipe_debitur = tipe_debitur;
        this.kecamatan_domisili = kecamatan_domisili;
        this.no_ktp = no_ktp;
        this.cif_clas = cif_clas;
        this.tanggal_lahir = tanggal_lahir;
        this.rt_domisili = rt_domisili;
        this.rw_domisili = rw_domisili;
        this.kota_domisili = kota_domisili;
    }


    public String getNama_ibu_kandung() {
        return nama_ibu_kandung;
    }

    public void setNama_ibu_kandung(String nama_ibu_kandung) {
        this.nama_ibu_kandung = nama_ibu_kandung;
    }

    public String getKelurahan_domisili() {
        return kelurahan_domisili;
    }

    public void setKelurahan_domisili(String kelurahan_domisili) {
        this.kelurahan_domisili = kelurahan_domisili;
    }

    public String getPropinsi_domisili() {
        return propinsi_domisili;
    }

    public void setPropinsi_domisili(String propinsi_domisili) {
        this.propinsi_domisili = propinsi_domisili;
    }

    public String getNama_nasabah() {
        return nama_nasabah;
    }

    public void setNama_nasabah(String nama_nasabah) {
        this.nama_nasabah = nama_nasabah;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }


    public int getFid_photo() {
        return fid_photo;
    }

    public void setFid_photo(int fid_photo) {
        this.fid_photo = fid_photo;
    }

    public String getKode_pos_domisili() {
        return kode_pos_domisili;
    }

    public void setKode_pos_domisili(String kode_pos_domisili) {
        this.kode_pos_domisili = kode_pos_domisili;
    }

    public String getAlamat_domisili() {
        return alamat_domisili;
    }

    public void setAlamat_domisili(String alamat_domisili) {
        this.alamat_domisili = alamat_domisili;
    }

    public String getCif_brinets() {
        return cif_brinets;
    }

    public void setCif_brinets(String cif_brinets) {
        this.cif_brinets = cif_brinets;
    }

    public String getTipe_debitur() {
        return tipe_debitur;
    }

    public void setTipe_debitur(String tipe_debitur) {
        this.tipe_debitur = tipe_debitur;
    }

    public String getKecamatan_domisili() {
        return kecamatan_domisili;
    }

    public void setKecamatan_domisili(String kecamatan_domisili) {
        this.kecamatan_domisili = kecamatan_domisili;
    }

    public String getNo_ktp() {
        return no_ktp;
    }

    public void setNo_ktp(String no_ktp) {
        this.no_ktp = no_ktp;
    }

    public String getCif_clas() {
        return cif_clas;
    }

    public void setCif_clas(String cif_clas) {
        this.cif_clas = cif_clas;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getRt_domisili() {
        return rt_domisili;
    }

    public void setRt_domisili(String rt_domisili) {
        this.rt_domisili = rt_domisili;
    }

    public String getRw_domisili() {
        return rw_domisili;
    }

    public void setRw_domisili(String rw_domisili) {
        this.rw_domisili = rw_domisili;
    }

    public String getKota_domisili() {
        return kota_domisili;
    }

    public void setKota_domisili(String kota_domisili) {
        this.kota_domisili = kota_domisili;
    }
}
