package com.application.bris.ikurma_nos_konsumer.api.model.request.agunan;

import com.google.gson.annotations.SerializedName;

public class ReqSaveAgunanTanahKosong {
    @SerializedName("idAgunan")
    private int idAgunan;
    @SerializedName("idApl")
    private int idApl;
    @SerializedName("idCif")
    private int idCif;
    @SerializedName("typeProduk")
    private int typeProduk;
    @SerializedName("tglPemeriksaan")
    private String tglPemeriksaan;
    @SerializedName("Koordinat")
    private String Koordinat;
    @SerializedName("alamatJaminan")
    private String alamatJaminan;
    @SerializedName("kelKec")
    private String kelKec;
    @SerializedName("wilayahKota")
    private String wilayahKota;
    @SerializedName("NamaPemegangHak")
    private String NamaPemegangHak;
    @SerializedName("HubPemegangHak")
    private String HubPemegangHak;
    @SerializedName("namaPenggarap")
    private String namaPenggarap;
    @SerializedName("HubPenggarap")
    private String HubPenggarap;
    @SerializedName("jenisDokumen")
    private String jenisDokumen;
    @SerializedName("noBuktiHak")
    private String noBuktiHak;
    @SerializedName("statusPenggarap")
    private String statusPenggarap;
    @SerializedName("noGs")
    private String noGs;
    @SerializedName("tglJatuhTempoSertifikat")
    private String tglJatuhTempoSertifikat;
    @SerializedName("luasTanah")
    private int luasTanah;
    @SerializedName("hargaTanah")
    private String hargaTanah;
    @SerializedName("nilaiMarketTanah")
    private String nilaiMarketTanah;
    @SerializedName("namaPemberiInfo1")
    private String namaPemberiInfo1;
    @SerializedName("alamatPemberiInfo1")
    private String alamatPemberiInfo1;
    @SerializedName("noTelpPemberiInfo1")
    private String noTelpPemberiInfo1;
    @SerializedName("checkBpn")
    private String checkBpn;
    @SerializedName("namaCheckBpn")
    private String namaCheckBpn;
    @SerializedName("notelCheckBpn")
    private String notelCheckBpn;
    @SerializedName("lebarJalan")
    private String lebarJalan;
    @SerializedName("pendapatPemeriksa")
    private String pendapatPemeriksa;
    @SerializedName("nilaiTaksasi")
    private String nilaiTaksasi;
    @SerializedName("Ftv")
    private String Ftv;
    @SerializedName("hasilBpn")
    private String hasilBpn;
    @SerializedName("nilaiPengikatan")
    private String nilaiPengikatan;
    @SerializedName("klasifikasiAgunan")
    private String klasifikasiAgunan;
    @SerializedName("jenisPengikatan")
    private String jenisPengikatan;
    @SerializedName("descPengikatan")
    private String descPengikatan;
    @SerializedName("fotoAgunanTanahKosong")
    private String fotoAgunanTanahKosong;
    @SerializedName("collIdSyiar")
    private String collIdSyiar;
    @SerializedName("idPhotoTKutama")
    private long idPhotoTKutama;
    @SerializedName("idPhotoTKbarat")
    private long idPhotoTKbarat;
    @SerializedName("idPhotoTKutara")
    private long idPhotoTKutara;
    @SerializedName("idPhotoTKselatan")
    private long idPhotoTKselatan;
    @SerializedName("idPhotoTKtimur")
    private long idPhotoTKtimur;
    @SerializedName("idPhotoTKbpn")
    private long idPhotoTKbpn;

    public int getIdAgunan() {
        return idAgunan;
    }

    public void setIdAgunan(int idAgunan) {
        this.idAgunan = idAgunan;
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

    public int getTypeProduk() {
        return typeProduk;
    }

    public void setTypeProduk(int typeProduk) {
        this.typeProduk = typeProduk;
    }

    public String getTglPemeriksaan() {
        return tglPemeriksaan;
    }

    public void setTglPemeriksaan(String tglPemeriksaan) {
        this.tglPemeriksaan = tglPemeriksaan;
    }

    public String getKoordinat() {
        return Koordinat;
    }

    public void setKoordinat(String koordinat) {
        Koordinat = koordinat;
    }

    public String getAlamatJaminan() {
        return alamatJaminan;
    }

    public void setAlamatJaminan(String alamatJaminan) {
        this.alamatJaminan = alamatJaminan;
    }

    public String getKelKec() {
        return kelKec;
    }

    public void setKelKec(String kelKec) {
        this.kelKec = kelKec;
    }

    public String getWilayahKota() {
        return wilayahKota;
    }

    public void setWilayahKota(String wilayahKota) {
        this.wilayahKota = wilayahKota;
    }

    public String getNamaPemegangHak() {
        return NamaPemegangHak;
    }

    public void setNamaPemegangHak(String namaPemegangHak) {
        NamaPemegangHak = namaPemegangHak;
    }

    public String getHubPemegangHak() {
        return HubPemegangHak;
    }

    public void setHubPemegangHak(String hubPemegangHak) {
        HubPemegangHak = hubPemegangHak;
    }

    public String getNamaPenggarap() {
        return namaPenggarap;
    }

    public void setNamaPenggarap(String namaPenggarap) {
        this.namaPenggarap = namaPenggarap;
    }

    public String getHubPenggarap() {
        return HubPenggarap;
    }

    public void setHubPenggarap(String hubPenggarap) {
        HubPenggarap = hubPenggarap;
    }

    public String getJenisDokumen() {
        return jenisDokumen;
    }

    public void setJenisDokumen(String jenisDokumen) {
        this.jenisDokumen = jenisDokumen;
    }

    public String getNoBuktiHak() {
        return noBuktiHak;
    }

    public void setNoBuktiHak(String noBuktiHak) {
        this.noBuktiHak = noBuktiHak;
    }

    public String getStatusPenggarap() {
        return statusPenggarap;
    }

    public void setStatusPenggarap(String statusPenggarap) {
        this.statusPenggarap = statusPenggarap;
    }

    public String getNoGs() {
        return noGs;
    }

    public void setNoGs(String noGs) {
        this.noGs = noGs;
    }

    public String getTglJatuhTempoSertifikat() {
        return tglJatuhTempoSertifikat;
    }

    public void setTglJatuhTempoSertifikat(String tglJatuhTempoSertifikat) {
        this.tglJatuhTempoSertifikat = tglJatuhTempoSertifikat;
    }

    public int getLuasTanah() {
        return luasTanah;
    }

    public void setLuasTanah(int luasTanah) {
        this.luasTanah = luasTanah;
    }

    public String getHargaTanah() {
        return hargaTanah;
    }

    public void setHargaTanah(String hargaTanah) {
        this.hargaTanah = hargaTanah;
    }

    public String getNilaiMarketTanah() {
        return nilaiMarketTanah;
    }

    public void setNilaiMarketTanah(String nilaiMarketTanah) {
        this.nilaiMarketTanah = nilaiMarketTanah;
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

    public String getCheckBpn() {
        return checkBpn;
    }

    public void setCheckBpn(String checkBpn) {
        this.checkBpn = checkBpn;
    }

    public String getNamaCheckBpn() {
        return namaCheckBpn;
    }

    public void setNamaCheckBpn(String namaCheckBpn) {
        this.namaCheckBpn = namaCheckBpn;
    }

    public String getNotelCheckBpn() {
        return notelCheckBpn;
    }

    public void setNotelCheckBpn(String notelCheckBpn) {
        this.notelCheckBpn = notelCheckBpn;
    }

    public String getLebarJalan() {
        return lebarJalan;
    }

    public void setLebarJalan(String lebarJalan) {
        this.lebarJalan = lebarJalan;
    }

    public String getPendapatPemeriksa() {
        return pendapatPemeriksa;
    }

    public void setPendapatPemeriksa(String pendapatPemeriksa) {
        this.pendapatPemeriksa = pendapatPemeriksa;
    }

    public String getNilaiTaksasi() {
        return nilaiTaksasi;
    }

    public void setNilaiTaksasi(String nilaiTaksasi) {
        this.nilaiTaksasi = nilaiTaksasi;
    }

    public String getFtv() {
        return Ftv;
    }

    public void setFtv(String ftv) {
        Ftv = ftv;
    }

    public String getHasilBpn() {
        return hasilBpn;
    }

    public void setHasilBpn(String hasilBpn) {
        this.hasilBpn = hasilBpn;
    }

    public String getNilaiPengikatan() {
        return nilaiPengikatan;
    }

    public void setNilaiPengikatan(String nilaiPengikatan) {
        this.nilaiPengikatan = nilaiPengikatan;
    }

    public String getKlasifikasiAgunan() {
        return klasifikasiAgunan;
    }

    public void setKlasifikasiAgunan(String klasifikasiAgunan) {
        this.klasifikasiAgunan = klasifikasiAgunan;
    }

    public String getJenisPengikatan() {
        return jenisPengikatan;
    }

    public void setJenisPengikatan(String jenisPengikatan) {
        this.jenisPengikatan = jenisPengikatan;
    }

    public String getDescPengikatan() {
        return descPengikatan;
    }

    public void setDescPengikatan(String descPengikatan) {
        this.descPengikatan = descPengikatan;
    }

    public String getFotoAgunanTanahKosong() {
        return fotoAgunanTanahKosong;
    }

    public void setFotoAgunanTanahKosong(String fotoAgunanTanahKosong) {
        this.fotoAgunanTanahKosong = fotoAgunanTanahKosong;
    }

    public String getCollIdSyiar() {
        return collIdSyiar;
    }

    public void setCollIdSyiar(String collIdSyiar) {
        this.collIdSyiar = collIdSyiar;
    }

    public long getIdPhotoTKutama() {
        return idPhotoTKutama;
    }

    public void setIdPhotoTKutama(long idPhotoTKutama) {
        this.idPhotoTKutama = idPhotoTKutama;
    }

    public long getIdPhotoTKbarat() {
        return idPhotoTKbarat;
    }

    public void setIdPhotoTKbarat(long idPhotoTKbarat) {
        this.idPhotoTKbarat = idPhotoTKbarat;
    }

    public long getIdPhotoTKutara() {
        return idPhotoTKutara;
    }

    public void setIdPhotoTKutara(long idPhotoTKutara) {
        this.idPhotoTKutara = idPhotoTKutara;
    }

    public long getIdPhotoTKselatan() {
        return idPhotoTKselatan;
    }

    public void setIdPhotoTKselatan(long idPhotoTKselatan) {
        this.idPhotoTKselatan = idPhotoTKselatan;
    }

    public long getIdPhotoTKtimur() {
        return idPhotoTKtimur;
    }

    public void setIdPhotoTKtimur(long idPhotoTKtimur) {
        this.idPhotoTKtimur = idPhotoTKtimur;
    }

    public long getIdPhotoTKbpn() {
        return idPhotoTKbpn;
    }

    public void setIdPhotoTKbpn(long idPhotoTKbpn) {
        this.idPhotoTKbpn = idPhotoTKbpn;
    }
}
