package com.application.bris.ikurma_nos_konsumer.api.model.request.agunan;

import com.google.gson.annotations.SerializedName;

public class ReqSaveAgunanKendaraan {

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

    public void setIdApl(int idApl) {
        this.idApl = idApl;
    }

    public void setIdCif(int idCif) {
        this.idCif = idCif;
    }

    public void setIdAgunan(int idAgunan) {
        this.idAgunan = idAgunan;
    }

    public void setTipeProduk(int tipeProduk) {
        this.tipeProduk = tipeProduk;
    }

    public void setTglPemeriksaan(String tglPemeriksaan) {
        this.tglPemeriksaan = tglPemeriksaan;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        JenisKendaraan = jenisKendaraan;
    }

    public void setPenggunaanJaminan(String penggunaanJaminan) {
        PenggunaanJaminan = penggunaanJaminan;
    }

    public void setDaerahOperasional(String daerahOperasional) {
        DaerahOperasional = daerahOperasional;
    }

    public void setNamaPemilikBPKB(String namaPemilikBPKB) {
        NamaPemilikBPKB = namaPemilikBPKB;
    }

    public void setNamaPemilikSaatIni(String namaPemilikSaatIni) {
        NamaPemilikSaatIni = namaPemilikSaatIni;
    }

    public void setAlamatPemilik(String alamatPemilik) {
        AlamatPemilik = alamatPemilik;
    }

    public void setHubungan(String hubungan) {
        Hubungan = hubungan;
    }

    public void setKlasifikasiAgunan(String klasifikasiAgunan) {
        KlasifikasiAgunan = klasifikasiAgunan;
    }

    public void setNoFaktur(String noFaktur) {
        NoFaktur = noFaktur;
    }

    public void setNoMesin(String noMesin) {
        NoMesin = noMesin;
    }

    public void setBuktiGesekMesin(String buktiGesekMesin) {
        BuktiGesekMesin = buktiGesekMesin;
    }

    public void setNoRangka(String noRangka) {
        NoRangka = noRangka;
    }

    public void setBuktiGesekRangka(String buktiGesekRangka) {
        BuktiGesekRangka = buktiGesekRangka;
    }

    public void setNoPolisi(String noPolisi) {
        NoPolisi = noPolisi;
    }

    public void setWarna(String warna) {
        Warna = warna;
    }

    public void setNoBKPB(String noBKPB) {
        NoBKPB = noBKPB;
    }

    public void setTahunPembuatan(String tahunPembuatan) {
        TahunPembuatan = tahunPembuatan;
    }

    public void setNoSTNK(String noSTNK) {
        NoSTNK = noSTNK;
    }

    public void setMerkKendaraan(String merkKendaraan) {
        MerkKendaraan = merkKendaraan;
    }

    public void setTipeKendaraan(String tipeKendaraan) {
        TipeKendaraan = tipeKendaraan;
    }

    public void setCheckSamsat(String checkSamsat) {
        CheckSamsat = checkSamsat;
    }

    public void setDenganSiapa(String denganSiapa) {
        DenganSiapa = denganSiapa;
    }

    public void setNoTelpon(String noTelpon) {
        NoTelpon = noTelpon;
    }

    public void setHasil(String hasil) {
        Hasil = hasil;
    }

    public void setNilaiMarket(String nilaiMarket) {
        NilaiMarket = nilaiMarket;
    }

    public void setNilaiTaksasi(String nilaiTaksasi) {
        NilaiTaksasi = nilaiTaksasi;
    }

    public void setNamaPemberiInfo1(String namaPemberiInfo1) {
        this.namaPemberiInfo1 = namaPemberiInfo1;
    }

    public void setAlamatPemberiInfo1(String alamatPemberiInfo1) {
        this.alamatPemberiInfo1 = alamatPemberiInfo1;
    }

    public void setNoTelpPemberiInfo1(String noTelpPemberiInfo1) {
        this.noTelpPemberiInfo1 = noTelpPemberiInfo1;
    }

    public void setMobiljepang(String mobiljepang) {
        this.mobiljepang = mobiljepang;
    }

    public void setKategKendaraan(String kategKendaraan) {
        KategKendaraan = kategKendaraan;
    }

    public void setJenisPlat(String jenisPlat) {
        JenisPlat = jenisPlat;
    }

    public void setDayaAngkut(String dayaAngkut) {
        this.dayaAngkut = dayaAngkut;
    }

    public void setKapasitas(String kapasitas) {
        this.kapasitas = kapasitas;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public void setKeterangan(String keterangan) {
        Keterangan = keterangan;
    }

    public void setIdKendaraan(int idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

    public void setIdKendaraan_Interior(int idKendaraan_Interior) {
        this.idKendaraan_Interior = idKendaraan_Interior;
    }

    public void setIdKendaraan_Mesin(int idKendaraan_Mesin) {
        this.idKendaraan_Mesin = idKendaraan_Mesin;
    }

    public void setIdKendaraan_Dok(int idKendaraan_Dok) {
        this.idKendaraan_Dok = idKendaraan_Dok;
    }

}
