package com.application.bris.ikurma_nos_konsumer.api.model.request.flpp;

import com.google.gson.annotations.SerializedName;

public class ReqSimpanKonfirmasiFlpp {

    //KIRIM UNTUK SIKASEP
    @SerializedName("kode")
    private String kodeBank;

    @SerializedName("batchId")
    private String batchId;

    @SerializedName("ktpDebitur")
    private String ktpDebitur;

    @SerializedName("konfirmasiFotoKtp")
    private String konfirmasiFotoKtp;

    @SerializedName("konfirmasiFotoSelfieKtp")
    private String konfirmasiFotoSelfieKtp;

    @SerializedName("kolektabilitas")
    private String kolektabilitas;

    @SerializedName("konfirmasiStatus")
    private String konfirmasiStatus;

    @SerializedName("konfirmasiAlasan")
    private String konfirmasiAlasan;

    //KIRIM UNTUK PIPELINE

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

    //PARAM TESTING
    @SerializedName("isTesting")
    private String isTesting;

    public String getIsTesting() {
        return isTesting;
    }

    public void setIsTesting(String isTesting) {
        this.isTesting = isTesting;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFidPhoto() {
        return fidPhoto;
    }

    public void setFidPhoto(int fidPhoto) {
        this.fidPhoto = fidPhoto;
    }

    public String getSegmen() {
        return segmen;
    }

    public void setSegmen(String segmen) {
        this.segmen = segmen;
    }

    public String getJenisProduk() {
        return jenisProduk;
    }

    public void setJenisProduk(String jenisProduk) {
        this.jenisProduk = jenisProduk;
    }

    public int getGimmick() {
        return gimmick;
    }

    public void setGimmick(int gimmick) {
        this.gimmick = gimmick;
    }

    public int getFidPihakKetiga() {
        return fidPihakKetiga;
    }

    public void setFidPihakKetiga(int fidPihakKetiga) {
        this.fidPihakKetiga = fidPihakKetiga;
    }

    public int getFidProject() {
        return fidProject;
    }

    public void setFidProject(int fidProject) {
        this.fidProject = fidProject;
    }

    public int getFidTujuanPenggunaan() {
        return fidTujuanPenggunaan;
    }

    public void setFidTujuanPenggunaan(int fidTujuanPenggunaan) {
        this.fidTujuanPenggunaan = fidTujuanPenggunaan;
    }

    public String getDescTujuanPenggunaan() {
        return descTujuanPenggunaan;
    }

    public void setDescTujuanPenggunaan(String descTujuanPenggunaan) {
        this.descTujuanPenggunaan = descTujuanPenggunaan;
    }

    public Long getRencanaPlafond() {
        return rencanaPlafond;
    }

    public void setRencanaPlafond(Long rencanaPlafond) {
        this.rencanaPlafond = rencanaPlafond;
    }

    public int getTenor() {
        return tenor;
    }

    public void setTenor(int tenor) {
        this.tenor = tenor;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }

    public String getTptLahir() {
        return tptLahir;
    }

    public void setTptLahir(String tptLahir) {
        this.tptLahir = tptLahir;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getLokasiGps() {
        return lokasiGps;
    }

    public void setLokasiGps(String lokasiGps) {
        this.lokasiGps = lokasiGps;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKec() {
        return kec;
    }

    public void setKec(String kec) {
        this.kec = kec;
    }

    public String getKel() {
        return kel;
    }

    public void setKel(String kel) {
        this.kel = kel;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getJenisUsaha() {
        return jenisUsaha;
    }

    public void setJenisUsaha(String jenisUsaha) {
        this.jenisUsaha = jenisUsaha;
    }

    public String getJenisKPR() {
        return jenisKPR;
    }

    public void setJenisKPR(String jenisKPR) {
        this.jenisKPR = jenisKPR;
    }

    public int getOmzetPerHari() {
        return omzetPerHari;
    }

    public void setOmzetPerHari(int omzetPerHari) {
        this.omzetPerHari = omzetPerHari;
    }

    public String getJenisTindak() {
        return jenisTindak;
    }

    public void setJenisTindak(String jenisTindak) {
        this.jenisTindak = jenisTindak;
    }

    public String getTindakLanjut() {
        return tindakLanjut;
    }

    public void setTindakLanjut(String tindakLanjut) {
        this.tindakLanjut = tindakLanjut;
    }

    public String getKodeBank() {
        return kodeBank;
    }

    public void setKodeBank(String kodeBank) {
        this.kodeBank = kodeBank;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getKtpDebitur() {
        return ktpDebitur;
    }

    public void setKtpDebitur(String ktpDebitur) {
        this.ktpDebitur = ktpDebitur;
    }

    public String getKonfirmasiFotoKtp() {
        return konfirmasiFotoKtp;
    }

    public void setKonfirmasiFotoKtp(String konfirmasiFotoKtp) {
        this.konfirmasiFotoKtp = konfirmasiFotoKtp;
    }

    public String getKonfirmasiFotoSelfieKtp() {
        return konfirmasiFotoSelfieKtp;
    }

    public void setKonfirmasiFotoSelfieKtp(String konfirmasiFotoSelfieKtp) {
        this.konfirmasiFotoSelfieKtp = konfirmasiFotoSelfieKtp;
    }

    public String getKolektabilitas() {
        return kolektabilitas;
    }

    public void setKolektabilitas(String kolektabilitas) {
        this.kolektabilitas = kolektabilitas;
    }

    public String getKonfirmasiStatus() {
        return konfirmasiStatus;
    }

    public void setKonfirmasiStatus(String konfirmasiStatus) {
        this.konfirmasiStatus = konfirmasiStatus;
    }

    public String getKonfirmasiAlasan() {
        return konfirmasiAlasan;
    }

    public void setKonfirmasiAlasan(String konfirmasiAlasan) {
        this.konfirmasiAlasan = konfirmasiAlasan;
    }
}
