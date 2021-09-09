package com.application.bris.ikurma_nos_konsumer.database.pojo;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by idong on 26/06/2019.
 */

public class AgunanKendaraanPojo extends RealmObject {

    @PrimaryKey
    @SerializedName("uuid")
    private String uuid;
    @SerializedName("idApl")
    private int idApl;
    @SerializedName("idCif")
    private int idCif;
    @SerializedName("idAgunan")
    private int idAgunan;
    @SerializedName("tipeProduk")
    private int tipeProduk;
    @SerializedName("tglPemeriksaan")
    private String tglPemeriksaan;
    @SerializedName("JenisKendaraan")
    private String JenisKendaraan;
    @SerializedName("PenggunaanJaminan")
    private String PenggunaanJaminan;
    @SerializedName("DaerahOperasional")
    private String DaerahOperasional;
    @SerializedName("NamaPemilikBPKB")
    private String NamaPemilikBPKB;
    @SerializedName("NamaPemilikSaatIni")
    private String NamaPemilikSaatIni;
    @SerializedName("AlamatPemilik")
    private String AlamatPemilik;
    @SerializedName("Hubungan")
    private String Hubungan;
    @SerializedName("KlasifikasiAgunan")
    private String KlasifikasiAgunan;
    @SerializedName("NoFaktur")
    private String NoFaktur;
    @SerializedName("NoMesin")
    private String NoMesin;
    @SerializedName("BuktiGesekMesin")
    private String BuktiGesekMesin;
    @SerializedName("NoRangka")
    private String NoRangka;
    @SerializedName("BuktiGesekRangka")
    private String BuktiGesekRangka;
    @SerializedName("NoPolisi")
    private String NoPolisi;
    @SerializedName("Warna")
    private String Warna;
    @SerializedName("NoBKPB")
    private String NoBKPB;
    @SerializedName("TahunPembuatan")
    private String TahunPembuatan;
    @SerializedName("NoSTNK")
    private String NoSTNK;
    @SerializedName("MerkKendaraan")
    private String MerkKendaraan;
    @SerializedName("TipeKendaraan")
    private String TipeKendaraan;
    @SerializedName("CheckSamsat")
    private String CheckSamsat;
    @SerializedName("DenganSiapa")
    private String DenganSiapa;
    @SerializedName("NoTelpon")
    private String NoTelpon;
    @SerializedName("Hasil")
    private String Hasil;
    @SerializedName("NilaiMarket")
    private String NilaiMarket;
    @SerializedName("NilaiTaksasi")
    private String NilaiTaksasi;
    @SerializedName("namaPemberiInfo1")
    private String namaPemberiInfo1;
    @SerializedName("alamatPemberiInfo1")
    private String alamatPemberiInfo1;
    @SerializedName("noTelpPemberiInfo1")
    private String noTelpPemberiInfo1;
    @SerializedName("mobiljepang")
    private String mobiljepang;
    @SerializedName("KategKendaraan")
    private String KategKendaraan;
    @SerializedName("JenisPlat")
    private String JenisPlat;
    @SerializedName("dayaAngkut")
    private String dayaAngkut;
    @SerializedName("kapasitas")
    private String kapasitas;
    @SerializedName("kondisi")
    private String kondisi;
    @SerializedName("Keterangan")
    private String Keterangan;
    @SerializedName("idKendaraan")
    private int idKendaraan;
    @SerializedName("idKendaraan_Interior")
    private int idKendaraan_Interior;
    @SerializedName("idKendaraan_Mesin")
    private int idKendaraan_Mesin;
    @SerializedName("idKendaraan_Dok")
    private int idKendaraan_Dok;

    public AgunanKendaraanPojo() {
        super();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getIdApl() {
        return idApl;
    }

    public void setIdApl(int idApl) {
        this.idApl = idApl;
    }

    public int getIdCif() {
        return idCif;
    }

    public void setIdCif(int idCif) {
        this.idCif = idCif;
    }

    public int getIdAgunan() {
        return idAgunan;
    }

    public void setIdAgunan(int idAgunan) {
        this.idAgunan = idAgunan;
    }

    public int getTipeProduk() {
        return tipeProduk;
    }

    public void setTipeProduk(int tipeProduk) {
        this.tipeProduk = tipeProduk;
    }

    public String getTglPemeriksaan() {
        return tglPemeriksaan;
    }

    public void setTglPemeriksaan(String tglPemeriksaan) {
        this.tglPemeriksaan = tglPemeriksaan;
    }

    public String getJenisKendaraan() {
        return JenisKendaraan;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        JenisKendaraan = jenisKendaraan;
    }

    public String getPenggunaanJaminan() {
        return PenggunaanJaminan;
    }

    public void setPenggunaanJaminan(String penggunaanJaminan) {
        PenggunaanJaminan = penggunaanJaminan;
    }

    public String getDaerahOperasional() {
        return DaerahOperasional;
    }

    public void setDaerahOperasional(String daerahOperasional) {
        DaerahOperasional = daerahOperasional;
    }

    public String getNamaPemilikBPKB() {
        return NamaPemilikBPKB;
    }

    public void setNamaPemilikBPKB(String namaPemilikBPKB) {
        NamaPemilikBPKB = namaPemilikBPKB;
    }

    public String getNamaPemilikSaatIni() {
        return NamaPemilikSaatIni;
    }

    public void setNamaPemilikSaatIni(String namaPemilikSaatIni) {
        NamaPemilikSaatIni = namaPemilikSaatIni;
    }

    public String getAlamatPemilik() {
        return AlamatPemilik;
    }

    public void setAlamatPemilik(String alamatPemilik) {
        AlamatPemilik = alamatPemilik;
    }

    public String getHubungan() {
        return Hubungan;
    }

    public void setHubungan(String hubungan) {
        Hubungan = hubungan;
    }

    public String getKlasifikasiAgunan() {
        return KlasifikasiAgunan;
    }

    public void setKlasifikasiAgunan(String klasifikasiAgunan) {
        KlasifikasiAgunan = klasifikasiAgunan;
    }

    public String getNoFaktur() {
        return NoFaktur;
    }

    public void setNoFaktur(String noFaktur) {
        NoFaktur = noFaktur;
    }

    public String getNoMesin() {
        return NoMesin;
    }

    public void setNoMesin(String noMesin) {
        NoMesin = noMesin;
    }

    public String getBuktiGesekMesin() {
        return BuktiGesekMesin;
    }

    public void setBuktiGesekMesin(String buktiGesekMesin) {
        BuktiGesekMesin = buktiGesekMesin;
    }

    public String getNoRangka() {
        return NoRangka;
    }

    public void setNoRangka(String noRangka) {
        NoRangka = noRangka;
    }

    public String getBuktiGesekRangka() {
        return BuktiGesekRangka;
    }

    public void setBuktiGesekRangka(String buktiGesekRangka) {
        BuktiGesekRangka = buktiGesekRangka;
    }

    public String getNoPolisi() {
        return NoPolisi;
    }

    public void setNoPolisi(String noPolisi) {
        NoPolisi = noPolisi;
    }

    public String getWarna() {
        return Warna;
    }

    public void setWarna(String warna) {
        Warna = warna;
    }

    public String getNoBKPB() {
        return NoBKPB;
    }

    public void setNoBKPB(String noBKPB) {
        NoBKPB = noBKPB;
    }

    public String getTahunPembuatan() {
        return TahunPembuatan;
    }

    public void setTahunPembuatan(String tahunPembuatan) {
        TahunPembuatan = tahunPembuatan;
    }

    public String getNoSTNK() {
        return NoSTNK;
    }

    public void setNoSTNK(String noSTNK) {
        NoSTNK = noSTNK;
    }

    public String getMerkKendaraan() {
        return MerkKendaraan;
    }

    public void setMerkKendaraan(String merkKendaraan) {
        MerkKendaraan = merkKendaraan;
    }

    public String getTipeKendaraan() {
        return TipeKendaraan;
    }

    public void setTipeKendaraan(String tipeKendaraan) {
        TipeKendaraan = tipeKendaraan;
    }

    public String getCheckSamsat() {
        return CheckSamsat;
    }

    public void setCheckSamsat(String checkSamsat) {
        CheckSamsat = checkSamsat;
    }

    public String getDenganSiapa() {
        return DenganSiapa;
    }

    public void setDenganSiapa(String denganSiapa) {
        DenganSiapa = denganSiapa;
    }

    public String getNoTelpon() {
        return NoTelpon;
    }

    public void setNoTelpon(String noTelpon) {
        NoTelpon = noTelpon;
    }

    public String getHasil() {
        return Hasil;
    }

    public void setHasil(String hasil) {
        Hasil = hasil;
    }

    public String getNilaiMarket() {
        return NilaiMarket;
    }

    public void setNilaiMarket(String nilaiMarket) {
        NilaiMarket = nilaiMarket;
    }

    public String getNilaiTaksasi() {
        return NilaiTaksasi;
    }

    public void setNilaiTaksasi(String nilaiTaksasi) {
        NilaiTaksasi = nilaiTaksasi;
    }

    public String getNamaPemberiInfo1() {
        return namaPemberiInfo1;
    }

    public void setNamaPemberiInfo1(String namaPemberiInfo1) {
        this.namaPemberiInfo1 = namaPemberiInfo1;
    }

    public String getAlamatPemberiInfo1() {
        return alamatPemberiInfo1;
    }

    public void setAlamatPemberiInfo1(String alamatPemberiInfo1) {
        this.alamatPemberiInfo1 = alamatPemberiInfo1;
    }

    public String getNoTelpPemberiInfo1() {
        return noTelpPemberiInfo1;
    }

    public void setNoTelpPemberiInfo1(String noTelpPemberiInfo1) {
        this.noTelpPemberiInfo1 = noTelpPemberiInfo1;
    }

    public String getMobiljepang() {
        return mobiljepang;
    }

    public void setMobiljepang(String mobiljepang) {
        this.mobiljepang = mobiljepang;
    }

    public String getKategKendaraan() {
        return KategKendaraan;
    }

    public void setKategKendaraan(String kategKendaraan) {
        KategKendaraan = kategKendaraan;
    }

    public String getJenisPlat() {
        return JenisPlat;
    }

    public void setJenisPlat(String jenisPlat) {
        JenisPlat = jenisPlat;
    }

    public String getDayaAngkut() {
        return dayaAngkut;
    }

    public void setDayaAngkut(String dayaAngkut) {
        this.dayaAngkut = dayaAngkut;
    }

    public String getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(String kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String keterangan) {
        Keterangan = keterangan;
    }

    public int getIdKendaraan() {
        return idKendaraan;
    }

    public void setIdKendaraan(int idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

    public int getIdKendaraan_Interior() {
        return idKendaraan_Interior;
    }

    public void setIdKendaraan_Interior(int idKendaraan_Interior) {
        this.idKendaraan_Interior = idKendaraan_Interior;
    }

    public int getIdKendaraan_Mesin() {
        return idKendaraan_Mesin;
    }

    public void setIdKendaraan_Mesin(int idKendaraan_Mesin) {
        this.idKendaraan_Mesin = idKendaraan_Mesin;
    }

    public int getIdKendaraan_Dok() {
        return idKendaraan_Dok;
    }

    public void setIdKendaraan_Dok(int idKendaraan_Dok) {
        this.idKendaraan_Dok = idKendaraan_Dok;
    }
}
