package com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 16/05/2019.
 */

public class inputPipeline {
    @SerializedName("uid")
    private String uid;
    @SerializedName("id")
    private int id;
    @SerializedName("namaNasabah")
    private String namaNasabah;
    @SerializedName("noKtp")
    private String noKtp;
    @SerializedName("noHp")
    private String noHp;
    @SerializedName("tptLahir")
    private String tptLahir;
    @SerializedName("tglLahir")
    private String tglLahir;
    @SerializedName("fidPhoto")
    private int fidPhoto;
    @SerializedName("lokasiGps")
    private String lokasiGps;
    @SerializedName("jenisUsaha")
    private String jenisUsaha;
    @SerializedName("omzetPerHari")
    private int omzetPerHari;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("prov")
    private String prov;
    @SerializedName("kota")
    private String kota;
    @SerializedName("kel")
    private String kel;
    @SerializedName("kec")
    private String kec;
    @SerializedName("kodePos")
    private String kodePos;
    @SerializedName("rt")
    private String rt;
    @SerializedName("rw")
    private String rw;
    @SerializedName("rencanaPlafond")
    private int rencanaPlafond;
    @SerializedName("tenor")
    private int tenor;
    @SerializedName("segmen")
    private String segmen;
    @SerializedName("jenisProduk")
    private String jenisProduk;
    @SerializedName("gimmick")
    private int gimmick;
    @SerializedName("tindakLanjut")
    private String tindakLanjut;
    @SerializedName("jenisTindak")
    private String jenisTindak;

    public inputPipeline(String uid, int id, String namaNasabah, String noKtp, String noHp, String tptLahir, String tglLahir, int fidPhoto, String lokasiGps, String jenisUsaha, int omzetPerHari, String alamat, String prov, String kota, String kel, String kec, String kodePos, String rt, String rw, int rencanaPlafond, int tenor, String segmen, String jenisProduk, int gimmick, String tindakLanjut, String jenisTindak) {
        this.uid = uid;
        this.id = id;
        this.namaNasabah = namaNasabah;
        this.noKtp = noKtp;
        this.noHp = noHp;
        this.tptLahir = tptLahir;
        this.tglLahir = tglLahir;
        this.fidPhoto = fidPhoto;
        this.lokasiGps = lokasiGps;
        this.jenisUsaha = jenisUsaha;
        this.omzetPerHari = omzetPerHari;
        this.alamat = alamat;
        this.prov = prov;
        this.kota = kota;
        this.kel = kel;
        this.kec = kec;
        this.kodePos = kodePos;
        this.rt = rt;
        this.rw = rw;
        this.rencanaPlafond = rencanaPlafond;
        this.tenor = tenor;
        this.segmen = segmen;
        this.jenisProduk = jenisProduk;
        this.gimmick = gimmick;
        this.tindakLanjut = tindakLanjut;
        this.jenisTindak = jenisTindak;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public void setTptLahir(String tptLahir) {
        this.tptLahir = tptLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public void setFidPhoto(int fidPhoto) {
        this.fidPhoto = fidPhoto;
    }

    public void setLokasiGps(String lokasiGps) {
        this.lokasiGps = lokasiGps;
    }

    public void setJenisUsaha(String jenisUsaha) {
        this.jenisUsaha = jenisUsaha;
    }

    public void setOmzetPerHari(int omzetPerHari) {
        this.omzetPerHari = omzetPerHari;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public void setKel(String kel) {
        this.kel = kel;
    }

    public void setKec(String kec) {
        this.kec = kec;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public void setRencanaPlafond(int rencanaPlafond) {
        this.rencanaPlafond = rencanaPlafond;
    }

    public void setTenor(int tenor) {
        this.tenor = tenor;
    }

    public void setSegmen(String segmen) {
        this.segmen = segmen;
    }

    public void setJenisProduk(String jenisProduk) {
        this.jenisProduk = jenisProduk;
    }

    public void setGimmick(int gimmick) {
        this.gimmick = gimmick;
    }

    public void setTindakLanjut(String tindakLanjut) {
        this.tindakLanjut = tindakLanjut;
    }

    public void setJenisTindak(String jenisTindak) {
        this.jenisTindak = jenisTindak;
    }
}
