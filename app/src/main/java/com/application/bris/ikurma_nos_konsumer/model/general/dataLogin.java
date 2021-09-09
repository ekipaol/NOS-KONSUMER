package com.application.bris.ikurma_nos_konsumer.model.general;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 23/05/2019.
 */

public class dataLogin {
    @SerializedName("role_type")
    private int role_type;
    @SerializedName("nama_kanwil")
    private String nama_kanwil;
    @SerializedName("fid_kantor")
    private int fid_kantor;
    @SerializedName("jabatan")
    private String jabatan;
    @SerializedName("nama_kantor")
    private String nama_kantor;
    @SerializedName("kode_skk")
    private String kode_skk;
    @SerializedName("dsn_code")
    private String dsn_code;
    @SerializedName("kode_kanwil")
    private String kode_kanwil;
    @SerializedName("fid_role")
    private int fid_role;
    @SerializedName("token")
    private String token;
    @SerializedName("uid")
    private int uid;
    @SerializedName("nik")
    private String nik;
    @SerializedName("nama")
    private String nama;
    @SerializedName("kode_cabang")
    private String kode_cabang;
    @SerializedName("uker")
    private String uker;
    @SerializedName("nama_skk")
    private String nama_skk;
    @SerializedName("kode_ao")
    private String kode_ao;
    @SerializedName("kantor")
    private String kantor;
    @SerializedName("cb_amanah")
    private String cbAmanah;

    public dataLogin(int role_type, String nama_kanwil, int fid_kantor, String jabatan, String nama_kantor, String kode_skk, String dsn_code, String kode_kanwil, int fid_role, String token, int uid, String nik, String nama, String kode_cabang, String uker, String nama_skk, String kode_ao, String kantor) {
        this.role_type = role_type;
        this.nama_kanwil = nama_kanwil;
        this.fid_kantor = fid_kantor;
        this.jabatan = jabatan;
        this.nama_kantor = nama_kantor;
        this.kode_skk = kode_skk;
        this.dsn_code = dsn_code;
        this.kode_kanwil = kode_kanwil;
        this.fid_role = fid_role;
        this.token = token;
        this.uid = uid;
        this.nik = nik;
        this.nama = nama;
        this.kode_cabang = kode_cabang;
        this.uker = uker;
        this.nama_skk = nama_skk;
        this.kode_ao = kode_ao;
        this.kantor = kantor;
    }

    public String getCbAmanah() {
        return cbAmanah;
    }

    public void setCbAmanah(String cbAmanah) {
        this.cbAmanah = cbAmanah;
    }

    public int getRole_type() {
        return role_type;
    }

    public void setRole_type(int role_type) {
        this.role_type = role_type;
    }

    public String getNama_kanwil() {
        return nama_kanwil;
    }

    public void setNama_kanwil(String nama_kanwil) {
        this.nama_kanwil = nama_kanwil;
    }

    public int getFid_kantor() {
        return fid_kantor;
    }

    public void setFid_kantor(int fid_kantor) {
        this.fid_kantor = fid_kantor;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getNama_kantor() {
        return nama_kantor;
    }

    public void setNama_kantor(String nama_kantor) {
        this.nama_kantor = nama_kantor;
    }

    public String getKode_skk() {
        return kode_skk;
    }

    public void setKode_skk(String kode_skk) {
        this.kode_skk = kode_skk;
    }

    public String getDsn_code() {
        return dsn_code;
    }

    public void setDsn_code(String dsn_code) {
        this.dsn_code = dsn_code;
    }

    public String getKode_kanwil() {
        return kode_kanwil;
    }

    public void setKode_kanwil(String kode_kanwil) {
        this.kode_kanwil = kode_kanwil;
    }

    public int getFid_role() {
        return fid_role;
    }

    public void setFid_role(int fid_role) {
        this.fid_role = fid_role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode_cabang() {
        return kode_cabang;
    }

    public void setKode_cabang(String kode_cabang) {
        this.kode_cabang = kode_cabang;
    }

    public String getUker() {
        return uker;
    }

    public void setUker(String uker) {
        this.uker = uker;
    }

    public String getNama_skk() {
        return nama_skk;
    }

    public void setNama_skk(String nama_skk) {
        this.nama_skk = nama_skk;
    }

    public String getKode_ao() {
        return kode_ao;
    }

    public void setKode_ao(String kode_ao) {
        this.kode_ao = kode_ao;
    }

    public String getKantor() {
        return kantor;
    }

    public void setKantor(String kantor) {
        this.kantor = kantor;
    }
}
