package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/26/2019.
 */

public class pipeline {

    @SerializedName("KELURAHAN")
    private String kelurahan;
    @SerializedName("TIPE_PRODUK")
    private String tipe_produk;
    @SerializedName("RW")
    private String rw;
    @SerializedName("NAMA_SEGMEN")
    private String nama_segmen;
    @SerializedName("JW")
    private int jw;
    @SerializedName("FID_PHOTO")
    private int fid_photo;
    @SerializedName("PLAFOND")
    private long plafond;
    @SerializedName("NO_KTP")
    private String no_ktp;
    @SerializedName("BIDANG_USAHA")
    private String bidang_usaha;
    @SerializedName("LOKASI_GPS")
    private String lokasi_gps;
    @SerializedName("NAMA_PRODUK")
    private String nama_produk;
    @SerializedName("ID")
    private int id;
    @SerializedName("PROVINSI")
    private String provinsi;
    @SerializedName("KODE_POS")
    private String kode_pos;
    @SerializedName("RT")
    private String rt;
    @SerializedName("FID_APLIKASI")
    private int fid_aplikasi;
    @SerializedName("OMZET_PER_HARI")
    private int omzet_per_hari;
    @SerializedName("KODE_PIPELINE")
    private String kode_pipeline;
    @SerializedName("GIMMICK")
    private int gimmick;
    @SerializedName("KODE_PRODUK")
    private String kode_produk;
    @SerializedName("TGL_INPUT")
    private String tgl_input;
    @SerializedName("NAMA")
    private String nama;
    @SerializedName("KECAMATAN")
    private String kecamatan;
    @SerializedName("TEMPAT_LAHIR")
    private String tempat_lahir;
    @SerializedName("KOTA")
    private String kota;
    @SerializedName("JENIS_PRODUK")
    private String jenis_produk;
    @SerializedName("UID")
    private int uid;
    @SerializedName("NAMA_GIMMICK")
    private String nama_gimmick;
    @SerializedName("KODE_SEGMEN")
    private String kode_segmen;
    @SerializedName("ALAMAT")
    private String alamat;
    @SerializedName("NO_HP")
    private String no_hp;
    @SerializedName("TANGGAL_LAHIR")
    private String tanggal_lahir;
    @SerializedName("KODE_GIMMICK")
    private String kode_gimmick;

    public pipeline(String kelurahan, String tipe_produk, String rw, String nama_segmen, int jw, int fid_photo, long plafond, String no_ktp, String bidang_usaha, String lokasi_gps, String nama_produk, int id, String provinsi, String kode_pos, String rt, int fid_aplikasi, int omzet_per_hari, String kode_pipeline, int gimmick, String kode_produk, String tgl_input, String nama, String kecamatan, String tempat_lahir, String kota, String jenis_produk, int uid, String nama_gimmick, String kode_segmen, String alamat, String no_hp, String tanggal_lahir, String kode_gimmick) {
        this.kelurahan = kelurahan;
        this.tipe_produk = tipe_produk;
        this.rw = rw;
        this.nama_segmen = nama_segmen;
        this.jw = jw;
        this.fid_photo = fid_photo;
        this.plafond = plafond;
        this.no_ktp = no_ktp;
        this.bidang_usaha = bidang_usaha;
        this.lokasi_gps = lokasi_gps;
        this.nama_produk = nama_produk;
        this.id = id;
        this.provinsi = provinsi;
        this.kode_pos = kode_pos;
        this.rt = rt;
        this.fid_aplikasi = fid_aplikasi;
        this.omzet_per_hari = omzet_per_hari;
        this.kode_pipeline = kode_pipeline;
        this.gimmick = gimmick;
        this.kode_produk = kode_produk;
        this.tgl_input = tgl_input;
        this.nama = nama;
        this.kecamatan = kecamatan;
        this.tempat_lahir = tempat_lahir;
        this.kota = kota;
        this.jenis_produk = jenis_produk;
        this.uid = uid;
        this.nama_gimmick = nama_gimmick;
        this.kode_segmen = kode_segmen;
        this.alamat = alamat;
        this.no_hp = no_hp;
        this.tanggal_lahir = tanggal_lahir;
        this.kode_gimmick = kode_gimmick;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public String getTipe_produk() {
        return tipe_produk;
    }

    public String getRw() {
        return rw;
    }

    public String getNama_segmen() {
        return nama_segmen;
    }

    public int getJw() {
        return jw;
    }

    public int getFid_photo() {
        return fid_photo;
    }

    public long getPlafond() {
        return plafond;
    }

    public String getNo_ktp() {
        return no_ktp;
    }

    public String getBidang_usaha() {
        return bidang_usaha;
    }

    public String getLokasi_gps() {
        return lokasi_gps;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public int getId() {
        return id;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public String getKode_pos() {
        return kode_pos;
    }

    public String getRt() {
        return rt;
    }

    public int getFid_aplikasi() {
        return fid_aplikasi;
    }

    public int getOmzet_per_hari() {
        return omzet_per_hari;
    }

    public String getKode_pipeline() {
        return kode_pipeline;
    }

    public int getGimmick() {
        return gimmick;
    }

    public String getKode_produk() {
        return kode_produk;
    }

    public String getTgl_input() {
        return tgl_input;
    }

    public String getNama() {
        return nama;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public String getKota() {
        return kota;
    }

    public String getJenis_produk() {
        return jenis_produk;
    }

    public int getUid() {
        return uid;
    }

    public String getNama_gimmick() {
        return nama_gimmick;
    }

    public String getKode_segmen() {
        return kode_segmen;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public String getKode_gimmick() {
        return kode_gimmick;
    }
}


