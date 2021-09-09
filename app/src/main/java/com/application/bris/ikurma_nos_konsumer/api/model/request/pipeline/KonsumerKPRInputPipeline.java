package com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline;


import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 16/05/2019.
 */

public class KonsumerKPRInputPipeline {
    @SerializedName("uid")
    private String uid;
    @SerializedName("id")
    private int id;
    @SerializedName("fidPhoto")
    private int fidPhoto;
    @SerializedName("segmen")
    private String segmen;
    @SerializedName("jenisProduk")
    private String jenisProduk;
    @SerializedName("gimmick")
    private int gimmick;
    @SerializedName("fidPihakKetiga")
    private int fidPihakKetiga;
    @SerializedName("fidProject")
    private int fidProject;
    @SerializedName("fidTujuanPenggunaan")
    private int fidTujuanPenggunaan;
    @SerializedName("descTujuanPenggunaan")
    private String descTujuanPenggunaan;
    @SerializedName("rencanaPlafond")
    private Long rencanaPlafond;
    @SerializedName("tenor")
    private int tenor;
    @SerializedName("noKtp")
    private String noKtp;
    @SerializedName("namaNasabah")
    private String namaNasabah;
    @SerializedName("tptLahir")
    private String tptLahir;
    @SerializedName("tglLahir")
    private String tglLahir;
    @SerializedName("noHp")
    private String noHp;
    @SerializedName("lokasiGps")
    private String lokasiGps;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("prov")
    private String prov;
    @SerializedName("kota")
    private String kota;
    @SerializedName("kec")
    private String kec;
    @SerializedName("kel")
    private String kel;
    @SerializedName("kodePos")
    private String kodePos;
    @SerializedName("rt")
    private String rt;
    @SerializedName("rw")
    private String rw;
    @SerializedName("jenisUsaha")
    private String jenisUsaha;
    @SerializedName("jenisKPR")
    private String jenisKPR;
    @SerializedName("omzetPerHari")
    private int omzetPerHari;
    @SerializedName("jenisTindak")
    private String jenisTindak;
    @SerializedName("tindakLanjut")
    private String tindakLanjut;
    @SerializedName("noRekDeveloper")
    private String noRekDeveloper;
    @SerializedName("npwpDeveloper")
    private String npwpDeveloper;

    public KonsumerKPRInputPipeline(String uid, int id, int fidPhoto, String segmen, String jenisProduk, int gimmick, int fidPihakKetiga, int fidProject, int fidTujuanPenggunaan, String descTujuanPenggunaan, Long rencanaPlafond, int tenor, String noKtp, String namaNasabah, String tptLahir, String tglLahir, String noHp, String lokasiGps, String alamat, String prov, String kota, String kec, String kel, String kodePos, String rt, String rw, String jenisUsaha, String jenisKPR, int omzetPerHari, String jenisTindak, String tindakLanjut) {
        this.uid = uid;
        this.id = id;
        this.fidPhoto = fidPhoto;
        this.segmen = segmen;
        this.jenisProduk = jenisProduk;
        this.gimmick = gimmick;
        this.fidPihakKetiga = fidPihakKetiga;
        this.fidProject = fidProject;
        this.fidTujuanPenggunaan = fidTujuanPenggunaan;
        this.descTujuanPenggunaan = descTujuanPenggunaan;
        this.rencanaPlafond = rencanaPlafond;
        this.tenor = tenor;
        this.noKtp = noKtp;
        this.namaNasabah = namaNasabah;
        this.tptLahir = tptLahir;
        this.tglLahir = tglLahir;
        this.noHp = noHp;
        this.lokasiGps = lokasiGps;
        this.alamat = alamat;
        this.prov = prov;
        this.kota = kota;
        this.kec = kec;
        this.kel = kel;
        this.kodePos = kodePos;
        this.rt = rt;
        this.rw = rw;
        this.jenisUsaha = jenisUsaha;
        this.jenisKPR = jenisKPR;
        this.omzetPerHari = omzetPerHari;
        this.jenisTindak = jenisTindak;
        this.tindakLanjut = tindakLanjut;
    }

    public String getNpwpDeveloper() {
        return npwpDeveloper;
    }

    public void setNpwpDeveloper(String npwpDeveloper) {
        this.npwpDeveloper = npwpDeveloper;
    }

    public String getNoRekDeveloper() {
        return noRekDeveloper;
    }

    public void setNoRekDeveloper(String noRekDeveloper) {
        this.noRekDeveloper = noRekDeveloper;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFidPhoto(int fidPhoto) {
        this.fidPhoto = fidPhoto;
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

    public void setFidPihakKetiga(int fidPihakKetiga) {
        this.fidPihakKetiga = fidPihakKetiga;
    }

    public void setFidProject(int fidProject) {
        this.fidProject = fidProject;
    }

    public void setFidTujuanPenggunaan(int fidTujuanPenggunaan) {
        this.fidTujuanPenggunaan = fidTujuanPenggunaan;
    }

    public void setDescTujuanPenggunaan(String descTujuanPenggunaan) {
        this.descTujuanPenggunaan = descTujuanPenggunaan;
    }

    public void setRencanaPlafond(Long rencanaPlafond) {
        this.rencanaPlafond = rencanaPlafond;
    }

    public void setTenor(int tenor) {
        this.tenor = tenor;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }

    public void setTptLahir(String tptLahir) {
        this.tptLahir = tptLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public void setLokasiGps(String lokasiGps) {
        this.lokasiGps = lokasiGps;
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

    public void setKec(String kec) {
        this.kec = kec;
    }

    public void setKel(String kel) {
        this.kel = kel;
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

    public void setJenisUsaha(String jenisUsaha) {
        this.jenisUsaha = jenisUsaha;
    }

    public void setJenisKPR(String jenisKPR) {
        this.jenisKPR = jenisKPR;
    }

    public void setOmzetPerHari(int omzetPerHari) {
        this.omzetPerHari = omzetPerHari;
    }

    public void setJenisTindak(String jenisTindak) {
        this.jenisTindak = jenisTindak;
    }

    public void setTindakLanjut(String tindakLanjut) {
        this.tindakLanjut = tindakLanjut;
    }
}
