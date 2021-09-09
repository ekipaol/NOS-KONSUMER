package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 10/07/2019.
 */

public class inputAgunanKios {
    @SerializedName("idAgunan")
    public Integer idAgunan;
    @SerializedName("idApl")
    public Integer idApl;
    @SerializedName("idCif")
    public Integer idCif;
    @SerializedName("typeProduk")
    public Integer typeProduk;
    @SerializedName("tanggalPemeriksaan")
    public String tanggalPemeriksaan;
    @SerializedName("jenisJaminan")
    public String jenisJaminan;
    @SerializedName("dokumenBukti")
    public String dokumenBukti;
    @SerializedName("noDokumen")
    public String noDokumen;
    @SerializedName("namaPemegangHak")
    public String namaPemegangHak;
    @SerializedName("hubunganPemegangHak")
    public String hubunganPemegangHak;
    @SerializedName("masaBerlakuIjin")
    public String masaBerlakuIjin;
    @SerializedName("luasKios")
    public Integer luasKios;
    @SerializedName("namaPasar")
    public String namaPasar;
    @SerializedName("losKios")
    public String losKios;
    @SerializedName("noKios")
    public String noKios;
    @SerializedName("Koordinat")
    private String Koordinat;
    @SerializedName("wilayahKota")
    public String wilayahKota;
    @SerializedName("lokasiJaminan")
    public String lokasiJaminan;
    @SerializedName("tahunRenovasi")
    public String tahunRenovasi;
    @SerializedName("retribusi")
    public String retribusi;
    @SerializedName("listrik")
    public String listrik;
    @SerializedName("besaranDaya")
    public String besaranDaya;
    @SerializedName("telepon")
    public String telepon;
    @SerializedName("noTelp")
    public String noTelp;
    @SerializedName("nilaiMarket")
    public String nilaiMarket;
    @SerializedName("nilaiTaksasi")
    public String nilaiTaksasi;
    @SerializedName("ftv")
    public String ftv;
    @SerializedName("namaPemberiInfo1")
    public String namaPemberiInfo1;
    @SerializedName("alamatPemberiInfo1")
    public String alamatPemberiInfo1;
    @SerializedName("noTelpPemberiInfo1")
    public String noTelpPemberiInfo1;
    @SerializedName("pendapatPemeriksa")
    public String pendapatPemeriksa;
    @SerializedName("klasifikasiAgunan")
    public String klasifikasiAgunan;
    @SerializedName("jenisPengikatan")
    public String jenisPengikatan;
    @SerializedName("descPengikatan")
    public String descPengikatan;
    @SerializedName("fotoAgunanKios")
    public String fotoAgunanKios;
    @SerializedName("nilaiPengikatan")
    public String nilaiPengikatan;
    @SerializedName("collIdSyiar")
    public String collIdSyiar;
    @SerializedName("idPhotoKutama")
    public Integer idPhotoKutama;
    @SerializedName("idPhotoKbarat")
    public Integer idPhotoKbarat;
    @SerializedName("idPhotoKutara")
    public Integer idPhotoKutara;
    @SerializedName("idPhotoKselatan")
    public Integer idPhotoKselatan;
    @SerializedName("idPhotoKtimur")
    public Integer idPhotoKtimur;
    @SerializedName("idPhotoKbpn")
    public Integer idPhotoKbpn;


    public inputAgunanKios(Integer idAgunan, Integer idApl, Integer idCif, Integer typeProduk, String tanggalPemeriksaan, String jenisJaminan, String dokumenBukti, String noDokumen, String namaPemegangHak, String hubunganPemegangHak, String masaBerlakuIjin, Integer luasKios, String namaPasar, String losKios, String noKios, String Koordinat, String wilayahKota, String lokasiJaminan, String tahunRenovasi, String retribusi, String listrik, String besaranDaya, String telepon, String noTelp, String nilaiMarket, String nilaiTaksasi, String ftv, String namaPemberiInfo1, String alamatPemberiInfo1, String noTelpPemberiInfo1, String pendapatPemeriksa, String klasifikasiAgunan, String jenisPengikatan, String descPengikatan, String fotoAgunanKios, String nilaiPengikatan, String collIdSyiar, Integer idPhotoKutama, Integer idPhotoKbarat, Integer idPhotoKutara, Integer idPhotoKselatan, Integer idPhotoKtimur, Integer idPhotoKbpn) {
        this.idAgunan = idAgunan;
        this.idApl = idApl;
        this.idCif = idCif;
        this.typeProduk = typeProduk;
        this.tanggalPemeriksaan = tanggalPemeriksaan;
        this.jenisJaminan = jenisJaminan;
        this.dokumenBukti = dokumenBukti;
        this.noDokumen = noDokumen;
        this.namaPemegangHak = namaPemegangHak;
        this.hubunganPemegangHak = hubunganPemegangHak;
        this.masaBerlakuIjin = masaBerlakuIjin;
        this.luasKios = luasKios;
        this.namaPasar = namaPasar;
        this.losKios = losKios;
        this.noKios = noKios;
        this.Koordinat = Koordinat;
        this.wilayahKota = wilayahKota;
        this.lokasiJaminan = lokasiJaminan;
        this.tahunRenovasi = tahunRenovasi;
        this.retribusi = retribusi;
        this.listrik = listrik;
        this.besaranDaya = besaranDaya;
        this.telepon = telepon;
        this.noTelp = noTelp;
        this.nilaiMarket = nilaiMarket;
        this.nilaiTaksasi = nilaiTaksasi;
        this.ftv = ftv;
        this.namaPemberiInfo1 = namaPemberiInfo1;
        this.alamatPemberiInfo1 = alamatPemberiInfo1;
        this.noTelpPemberiInfo1 = noTelpPemberiInfo1;
        this.pendapatPemeriksa = pendapatPemeriksa;
        this.klasifikasiAgunan = klasifikasiAgunan;
        this.jenisPengikatan = jenisPengikatan;
        this.descPengikatan = descPengikatan;
        this.fotoAgunanKios = fotoAgunanKios;
        this.nilaiPengikatan = nilaiPengikatan;
        this.collIdSyiar = collIdSyiar;
        this.idPhotoKutama = idPhotoKutama;
        this.idPhotoKbarat = idPhotoKbarat;
        this.idPhotoKutara = idPhotoKutara;
        this.idPhotoKselatan = idPhotoKselatan;
        this.idPhotoKtimur = idPhotoKtimur;
        this.idPhotoKbpn = idPhotoKbpn;
    }

    public void setIdAgunan(Integer idAgunan) {
        this.idAgunan = idAgunan;
    }

    public void setIdApl(Integer idApl) {
        this.idApl = idApl;
    }

    public void setIdCif(Integer idCif) {
        this.idCif = idCif;
    }

    public void setTypeProduk(Integer typeProduk) {
        this.typeProduk = typeProduk;
    }

    public void setTanggalPemeriksaan(String tanggalPemeriksaan) {
        this.tanggalPemeriksaan = tanggalPemeriksaan;
    }

    public void setJenisJaminan(String jenisJaminan) {
        this.jenisJaminan = jenisJaminan;
    }

    public void setDokumenBukti(String dokumenBukti) {
        this.dokumenBukti = dokumenBukti;
    }

    public void setNoDokumen(String noDokumen) {
        this.noDokumen = noDokumen;
    }

    public void setNamaPemegangHak(String namaPemegangHak) {
        this.namaPemegangHak = namaPemegangHak;
    }

    public void setHubunganPemegangHak(String hubunganPemegangHak) {
        this.hubunganPemegangHak = hubunganPemegangHak;
    }

    public void setMasaBerlakuIjin(String masaBerlakuIjin) {
        this.masaBerlakuIjin = masaBerlakuIjin;
    }

    public void setLuasKios(Integer luasKios) {
        this.luasKios = luasKios;
    }

    public void setNamaPasar(String namaPasar) {
        this.namaPasar = namaPasar;
    }

    public void setLosKios(String losKios) {
        this.losKios = losKios;
    }

    public void setNoKios(String noKios) {
        this.noKios = noKios;
    }

    public void setKoordinat(String koordinat) {
        Koordinat = koordinat;
    }

    public void setWilayahKota(String wilayahKota) {
        this.wilayahKota = wilayahKota;
    }

    public void setLokasiJaminan(String lokasiJaminan) {
        this.lokasiJaminan = lokasiJaminan;
    }

    public void setTahunRenovasi(String tahunRenovasi) {
        this.tahunRenovasi = tahunRenovasi;
    }

    public void setRetribusi(String retribusi) {
        this.retribusi = retribusi;
    }

    public void setListrik(String listrik) {
        this.listrik = listrik;
    }

    public void setBesaranDaya(String besaranDaya) {
        this.besaranDaya = besaranDaya;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public void setNilaiMarket(String nilaiMarket) {
        this.nilaiMarket = nilaiMarket;
    }

    public void setNilaiTaksasi(String nilaiTaksasi) {
        this.nilaiTaksasi = nilaiTaksasi;
    }

    public void setFtv(String ftv) {
        this.ftv = ftv;
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

    public void setPendapatPemeriksa(String pendapatPemeriksa) {
        this.pendapatPemeriksa = pendapatPemeriksa;
    }

    public void setKlasifikasiAgunan(String klasifikasiAgunan) {
        this.klasifikasiAgunan = klasifikasiAgunan;
    }

    public void setJenisPengikatan(String jenisPengikatan) {
        this.jenisPengikatan = jenisPengikatan;
    }

    public void setDescPengikatan(String descPengikatan) {
        this.descPengikatan = descPengikatan;
    }

    public void setFotoAgunanKios(String fotoAgunanKios) {
        this.fotoAgunanKios = fotoAgunanKios;
    }

    public void setNilaiPengikatan(String nilaiPengikatan) {
        this.nilaiPengikatan = nilaiPengikatan;
    }

    public void setCollIdSyiar(String collIdSyiar) {
        this.collIdSyiar = collIdSyiar;
    }

    public void setIdPhotoKutama(Integer idPhotoKutama) {
        this.idPhotoKutama = idPhotoKutama;
    }

    public void setIdPhotoKbarat(Integer idPhotoKbarat) {
        this.idPhotoKbarat = idPhotoKbarat;
    }

    public void setIdPhotoKutara(Integer idPhotoKutara) {
        this.idPhotoKutara = idPhotoKutara;
    }

    public void setIdPhotoKselatan(Integer idPhotoKselatan) {
        this.idPhotoKselatan = idPhotoKselatan;
    }

    public void setIdPhotoKtimur(Integer idPhotoKtimur) {
        this.idPhotoKtimur = idPhotoKtimur;
    }

    public void setIdPhotoKbpn(Integer idPhotoKbpn) {
        this.idPhotoKbpn = idPhotoKbpn;
    }
}
