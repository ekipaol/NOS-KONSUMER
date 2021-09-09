package com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline;


import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 16/05/2019.
 */

public class KonsumerKMGInputPipeline {
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
    @SerializedName("loan_type")
    private String loan_type;
    @SerializedName("Institusi")
    private String Institusi;
    @SerializedName("rekDM")
    private String rekDM;
    @SerializedName("fidTujuanPenggunaan")
    private int fidTujuanPenggunaan;
    @SerializedName("descTujuanPenggunaan")
    private String descTujuanPenggunaan;
    @SerializedName("rencanaPlafond")
    private long rencanaPlafond;
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
    @SerializedName("fidInstansi")
    private int fidInstansi;
    @SerializedName("jenisUsaha")
    private String jenisUsaha;
    @SerializedName("jenisKMG")
    private String jenisKMG;
    @SerializedName("kategNasabah")
    private String kategNasabah;
    @SerializedName("omzetPerHari")
    private int omzetPerHari;
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
    @SerializedName("lokasiGps")
    private String lokasiGps;
    @SerializedName("jenisTindak")
    private String jenisTindak;
    @SerializedName("tindakLanjut")
    private String tindakLanjut;
    @SerializedName("fidPihakKetiga")
    private int fidPihakKetiga;
    @SerializedName("flagEMBP")
    private int flagEMBP;

    public KonsumerKMGInputPipeline(String uid, int id, int fidPhoto, String segmen, String jenisProduk, int gimmick, String loan_type, String institusi, String rekDM, int fidTujuanPenggunaan, String descTujuanPenggunaan, long rencanaPlafond, int tenor, String noKtp, String namaNasabah, String tptLahir, String tglLahir, String noHp, int fidInstansi, String jenisUsaha, String jenisKMG, String kategNasabah, int omzetPerHari, String alamat, String prov, String kota, String kec, String kel, String kodePos, String rt, String rw, String lokasiGps, String jenisTindak, String tindakLanjut, int fidPihakKetiga, int flagEMBP) {
        this.uid = uid;
        this.id = id;
        this.fidPhoto = fidPhoto;
        this.segmen = segmen;
        this.jenisProduk = jenisProduk;
        this.gimmick = gimmick;
        this.loan_type = loan_type;
        Institusi = institusi;
        this.rekDM = rekDM;
        this.fidTujuanPenggunaan = fidTujuanPenggunaan;
        this.descTujuanPenggunaan = descTujuanPenggunaan;
        this.rencanaPlafond = rencanaPlafond;
        this.tenor = tenor;
        this.noKtp = noKtp;
        this.namaNasabah = namaNasabah;
        this.tptLahir = tptLahir;
        this.tglLahir = tglLahir;
        this.noHp = noHp;
        this.fidInstansi = fidInstansi;
        this.jenisUsaha = jenisUsaha;
        this.jenisKMG = jenisKMG;
        this.kategNasabah = kategNasabah;
        this.omzetPerHari = omzetPerHari;
        this.alamat = alamat;
        this.prov = prov;
        this.kota = kota;
        this.kec = kec;
        this.kel = kel;
        this.kodePos = kodePos;
        this.rt = rt;
        this.rw = rw;
        this.lokasiGps = lokasiGps;
        this.jenisTindak = jenisTindak;
        this.tindakLanjut = tindakLanjut;
        this.fidPihakKetiga = fidPihakKetiga;
        this.flagEMBP = flagEMBP;
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

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public void setInstitusi(String institusi) {
        Institusi = institusi;
    }

    public void setRekDM(String rekDM) {
        this.rekDM = rekDM;
    }

    public void setFidTujuanPenggunaan(int fidTujuanPenggunaan) {
        this.fidTujuanPenggunaan = fidTujuanPenggunaan;
    }

    public void setDescTujuanPenggunaan(String descTujuanPenggunaan) {
        this.descTujuanPenggunaan = descTujuanPenggunaan;
    }

    public void setRencanaPlafond(long rencanaPlafond) {
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

    public void setFidInstansi(int fidInstansi) {
        this.fidInstansi = fidInstansi;
    }

    public void setJenisUsaha(String jenisUsaha) {
        this.jenisUsaha = jenisUsaha;
    }

    public void setJenisKMG(String jenisKMG) {
        this.jenisKMG = jenisKMG;
    }

    public void setKategNasabah(String kategNasabah) {
        this.kategNasabah = kategNasabah;
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

    public void setLokasiGps(String lokasiGps) {
        this.lokasiGps = lokasiGps;
    }

    public void setJenisTindak(String jenisTindak) {
        this.jenisTindak = jenisTindak;
    }

    public void setTindakLanjut(String tindakLanjut) {
        this.tindakLanjut = tindakLanjut;
    }

    public void setFidPihakKetiga(int fidPihakKetiga) {
        this.fidPihakKetiga = fidPihakKetiga;
    }

    public void setFlagEMBP(int flagEMBP) {
        this.flagEMBP = flagEMBP;
    }
}
