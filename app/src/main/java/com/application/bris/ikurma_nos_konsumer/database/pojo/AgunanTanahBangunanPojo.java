package com.application.bris.ikurma_nos_konsumer.database.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by idong on 26/06/2019.
 */

public class AgunanTanahBangunanPojo extends RealmObject {

    @PrimaryKey
    @SerializedName("uuid")
    private String uuid;
    @SerializedName("idAgunan")
    private int idAgunan;
    @SerializedName("idApl")
    private int idApl;
    @SerializedName("cif")
    private String cif;
    @SerializedName("LokasiTanah")
    private String LokasiTanah;
    @SerializedName("KavBlok")
    private String KavBlok;
    @SerializedName("RT")
    private String RT;
    @SerializedName("RW")
    private String RW;
    @SerializedName("KodePos")
    private String KodePos;
    @SerializedName("kodeDati")
    private String kodeDati;
    @SerializedName("Kelurahan")
    private String Kelurahan;
    @SerializedName("Kecamatan")
    private String Kecamatan;
    @SerializedName("Kota")
    private String Kota;
    @SerializedName("Propinsi")
    private String Propinsi;
    @SerializedName("BentukTanah")
    private String BentukTanah;
    @SerializedName("PermukaanTanah")
    private String PermukaanTanah;
    @SerializedName("LuasTanahFisik")
    private String LuasTanahFisik;
    @SerializedName("BatasUtaraTanah")
    private String BatasUtaraTanah;
    @SerializedName("BatasTimurTanah")
    private String BatasTimurTanah;
    @SerializedName("BatasSelatanTanah")
    private String BatasSelatanTanah;
    @SerializedName("BatasBaratTanah")
    private String BatasBaratTanah;
    @SerializedName("jenisSuratTanah")
    private String jenisSuratTanah;
    @SerializedName("NoSertifikat")
    private String NoSertifikat;
    @SerializedName("LuasTanahSertifikat")
    private String LuasTanahSertifikat;
    @SerializedName("NoGS")
    private String NoGS;
    @SerializedName("AtasNamaSertifikat")
    private String AtasNamaSertifikat;
    @SerializedName("HubNasabahDgnPemegangHak")
    private String HubNasabahDgnPemegangHak;
    @SerializedName("TanggalSertifikat")
    private String TanggalSertifikat;
    @SerializedName("MasaHakAtasTanah")
    private String MasaHakAtasTanah;
    @SerializedName("JenisBangunan")
    private String JenisBangunan;
    @SerializedName("Tingkat")
    private String Tingkat;
    @SerializedName("NoIMB")
    private String NoIMB;
    @SerializedName("AtasNamaIMB")
    private String AtasNamaIMB;
    @SerializedName("AlamatIMB")
    private String AlamatIMB;
    @SerializedName("TahunBangunan")
    private String TahunBangunan;
    @SerializedName("JenisAgunanXBRL")
    private String JenisAgunanXBRL;
    @SerializedName("NamaPenghuni")
    private String NamaPenghuni;
    @SerializedName("StatusPenghuni")
    private String StatusPenghuni;
    @SerializedName("HubPenghuniDgPemilik")
    private String HubPenghuniDgPemilik;
    @SerializedName("Perawatan")
    private String Perawatan;
    @SerializedName("Pondasi")
    private String Pondasi;
    @SerializedName("Dinding")
    private String Dinding;
    @SerializedName("Plafond")
    private String Plafond;
    @SerializedName("Atap")
    private String Atap;
    @SerializedName("Lantai")
    private String Lantai;
    @SerializedName("Teras")
    private String Teras;
    @SerializedName("Pagar")
    private String Pagar;
    @SerializedName("GarasiCarport")
    private String GarasiCarport;
    @SerializedName("fasilitasListrik")
    private boolean fasilitasListrik;
    @SerializedName("fasilitasPAM")
    private boolean fasilitasPAM;
    @SerializedName("Telepon")
    private boolean Telepon;
    @SerializedName("teganganListrik")
    private String teganganListrik;
    @SerializedName("jenisAir")
    private String jenisAir;
    @SerializedName("noTelepon")
    private String noTelepon;
    @SerializedName("PeruntukanLokasi")
    private String PeruntukanLokasi;
    @SerializedName("PerkembanganLingkungan")
    private String PerkembanganLingkungan;
    @SerializedName("Kepadatan")
    private String Kepadatan;
    @SerializedName("AksesPencapaian")
    private String AksesPencapaian;
    @SerializedName("AksesJalanObjek")
    private String AksesJalanObjek;
    @SerializedName("LebarJalanDepan")
    private double LebarJalanDepan;
    @SerializedName("FasilitasSosial")
    private String FasilitasSosial;
    @SerializedName("TanggalPemeriksaan")
    private String TanggalPemeriksaan;
    @SerializedName("DataPembanding1")
    private String DataPembanding1;
    @SerializedName("DataPembanding2")
    private String DataPembanding2;
    @SerializedName("DataPembanding3")
    private String DataPembanding3;
    @SerializedName("HargaMeterTanah")
    private long HargaMeterTanah;
    @SerializedName("LuasBangunan1")
    private double LuasBangunan1;
    @SerializedName("HargaBangunan1")
    private long HargaBangunan1;
    @SerializedName("LuasBangunan2")
    private double LuasBangunan2;
    @SerializedName("HargaBangunan2")
    private long HargaBangunan2;
    @SerializedName("NPWTanahBangunan")
    private long NPWTanahBangunan;
    @SerializedName("NLTanahBangunan")
    private long NLTanahBangunan;
    @SerializedName("PendapatHarga")
    private String PendapatHarga;
    @SerializedName("KondisiSekitar")
    private String KondisiSekitar;
    @SerializedName("PendapatKondisiJaminan")
    private String PendapatKondisiJaminan;
    @SerializedName("CollID")
    private String CollID;
    @SerializedName("JenisBangunanBRINS")
    private String JenisBangunanBRINS;
    @SerializedName("lokasiBangunanBrins")
    private String lokasiBangunanBrins;
    @SerializedName("rate")
    private String rate;
    @SerializedName("namaPasar")
    private String namaPasar;
    @SerializedName("nkrBrins")
    private String nkrBrins;
    @SerializedName("NPWBangunan")
    private long NPWBangunan;
    @SerializedName("NPWBangunanBrins")
    private long NPWBangunanBrins;
    @SerializedName("provinsi")
    private String provinsi;
    @SerializedName("kota_kab")
    private String kota_kab;
    @SerializedName("nama_pasar")
    private String nama_pasar;
    @SerializedName("alamat_pasar")
    private String alamat_pasar;
    @SerializedName("idBangunan")
    private int idBangunan;
    @SerializedName("idTanah")
    private int idTanah;
    @SerializedName("idBatas_Timur")
    private int idBatas_Timur;
    @SerializedName("idBatas_Selatan")
    private int idBatas_Selatan;
    @SerializedName("idBatas_Barat")
    private int idBatas_Barat;
    @SerializedName("idBatas_Utara")
    private int idBatas_Utara;
    @SerializedName("tipeProduk")
    private int tipeProduk;
    @SerializedName("Koordinat")
    private String Koordinat;
    @SerializedName("ketBangunan")
    private String ketBangunan;
    @SerializedName("idDok_BPN")
    private int idDok_BPN;
    @SerializedName("idDokIMB")
    private int idDokIMB;
    @SerializedName("idDokPBB")
    private int idDokPBB;
    @SerializedName("Marketable")
    private String idRekomendasiPenilai;
    @SerializedName("MarketableDesc")
    private String descRekomendasiPenilai;

    @SerializedName("noBast")
    @Expose
    private String noBast;

    @SerializedName("tglBast")
    @Expose
    private String tglBast;

    @SerializedName("noSlf")
    @Expose
    private String noSlf;

    @SerializedName("tglSlf")
    @Expose
    private String tglSlf;

    @SerializedName("kodeWilayah")
    @Expose
    private String kodeWilayah;

    @SerializedName("noAlamat")
    @Expose
    private String noAlamat;

    @SerializedName("idDok41")
    @Expose
    private String idDok41;

    @SerializedName("idDok41b")
    @Expose
    private String idDok41b;

    public String getNoBast() {
        return noBast;
    }

    public void setNoBast(String noBast) {
        this.noBast = noBast;
    }

    public String getTglBast() {
        return tglBast;
    }

    public void setTglBast(String tglBast) {
        this.tglBast = tglBast;
    }

    public String getNoSlf() {
        return noSlf;
    }

    public void setNoSlf(String noSlf) {
        this.noSlf = noSlf;
    }

    public String getTglSlf() {
        return tglSlf;
    }

    public void setTglSlf(String tglSlf) {
        this.tglSlf = tglSlf;
    }

    public String getKodeWilayah() {
        return kodeWilayah;
    }

    public void setKodeWilayah(String kodeWilayah) {
        this.kodeWilayah = kodeWilayah;
    }

    public String getNoAlamat() {
        return noAlamat;
    }

    public void setNoAlamat(String noAlamat) {
        this.noAlamat = noAlamat;
    }

    public String getIdDok41() {
        return idDok41;
    }

    public void setIdDok41(String idDok41) {
        this.idDok41 = idDok41;
    }

    public String getIdDok41b() {
        return idDok41b;
    }

    public void setIdDok41b(String idDok41b) {
        this.idDok41b = idDok41b;
    }

    public String getIdRekomendasiPenilai() {
        return idRekomendasiPenilai;
    }

    public void setIdRekomendasiPenilai(String idRekomendasiPenilai) {
        this.idRekomendasiPenilai = idRekomendasiPenilai;
    }

    public String getDescRekomendasiPenilai() {
        return descRekomendasiPenilai;
    }

    public void setDescRekomendasiPenilai(String descRekomendasiPenilai) {
        this.descRekomendasiPenilai = descRekomendasiPenilai;
    }

    public AgunanTanahBangunanPojo(){
        super();
    }

    public int getIdDokIMB() {
        return idDokIMB;
    }

    public void setIdDokIMB(int idDokIMB) {
        this.idDokIMB = idDokIMB;
    }

    public int getIdDokPBB() {
        return idDokPBB;
    }

    public void setIdDokPBB(int idDokPBB) {
        this.idDokPBB = idDokPBB;
    }

    public String getKetBangunan() {
        return ketBangunan;
    }

    public void setKetBangunan(String ketBangunan) {
        this.ketBangunan = ketBangunan;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getLokasiTanah() {
        return LokasiTanah;
    }

    public void setLokasiTanah(String lokasiTanah) {
        LokasiTanah = lokasiTanah;
    }

    public String getKavBlok() {
        return KavBlok;
    }

    public void setKavBlok(String kavBlok) {
        KavBlok = kavBlok;
    }

    public String getRT() {
        return RT;
    }

    public void setRT(String RT) {
        this.RT = RT;
    }

    public String getRW() {
        return RW;
    }

    public void setRW(String RW) {
        this.RW = RW;
    }

    public String getKodePos() {
        return KodePos;
    }

    public void setKodePos(String kodePos) {
        KodePos = kodePos;
    }

    public String getKodeDati() {
        return kodeDati;
    }

    public void setKodeDati(String kodeDati) {
        this.kodeDati = kodeDati;
    }

    public String getKelurahan() {
        return Kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        Kelurahan = kelurahan;
    }

    public String getKecamatan() {
        return Kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        Kecamatan = kecamatan;
    }

    public String getKota() {
        return Kota;
    }

    public void setKota(String kota) {
        Kota = kota;
    }

    public String getPropinsi() {
        return Propinsi;
    }

    public void setPropinsi(String propinsi) {
        Propinsi = propinsi;
    }

    public String getBentukTanah() {
        return BentukTanah;
    }

    public void setBentukTanah(String bentukTanah) {
        BentukTanah = bentukTanah;
    }

    public String getPermukaanTanah() {
        return PermukaanTanah;
    }

    public void setPermukaanTanah(String permukaanTanah) {
        PermukaanTanah = permukaanTanah;
    }

    public String getLuasTanahFisik() {
        return LuasTanahFisik;
    }

    public void setLuasTanahFisik(String luasTanahFisik) {
        LuasTanahFisik = luasTanahFisik;
    }

    public String getBatasUtaraTanah() {
        return BatasUtaraTanah;
    }

    public void setBatasUtaraTanah(String batasUtaraTanah) {
        BatasUtaraTanah = batasUtaraTanah;
    }

    public String getBatasTimurTanah() {
        return BatasTimurTanah;
    }

    public void setBatasTimurTanah(String batasTimurTanah) {
        BatasTimurTanah = batasTimurTanah;
    }

    public String getBatasSelatanTanah() {
        return BatasSelatanTanah;
    }

    public void setBatasSelatanTanah(String batasSelatanTanah) {
        BatasSelatanTanah = batasSelatanTanah;
    }

    public String getBatasBaratTanah() {
        return BatasBaratTanah;
    }

    public void setBatasBaratTanah(String batasBaratTanah) {
        BatasBaratTanah = batasBaratTanah;
    }

    public String getJenisSuratTanah() {
        return jenisSuratTanah;
    }

    public void setJenisSuratTanah(String jenisSuratTanah) {
        this.jenisSuratTanah = jenisSuratTanah;
    }

    public String getNoSertifikat() {
        return NoSertifikat;
    }

    public void setNoSertifikat(String noSertifikat) {
        NoSertifikat = noSertifikat;
    }

    public String getLuasTanahSertifikat() {
        return LuasTanahSertifikat;
    }

    public void setLuasTanahSertifikat(String luasTanahSertifikat) {
        LuasTanahSertifikat = luasTanahSertifikat;
    }

    public String getNoGS() {
        return NoGS;
    }

    public void setNoGS(String noGS) {
        NoGS = noGS;
    }

    public String getAtasNamaSertifikat() {
        return AtasNamaSertifikat;
    }

    public void setAtasNamaSertifikat(String atasNamaSertifikat) {
        AtasNamaSertifikat = atasNamaSertifikat;
    }

    public String getHubNasabahDgnPemegangHak() {
        return HubNasabahDgnPemegangHak;
    }

    public void setHubNasabahDgnPemegangHak(String hubNasabahDgnPemegangHak) {
        HubNasabahDgnPemegangHak = hubNasabahDgnPemegangHak;
    }

    public String getTanggalSertifikat() {
        return TanggalSertifikat;
    }

    public void setTanggalSertifikat(String tanggalSertifikat) {
        TanggalSertifikat = tanggalSertifikat;
    }

    public String getMasaHakAtasTanah() {
        return MasaHakAtasTanah;
    }

    public void setMasaHakAtasTanah(String masaHakAtasTanah) {
        MasaHakAtasTanah = masaHakAtasTanah;
    }

    public String getJenisBangunan() {
        return JenisBangunan;
    }

    public void setJenisBangunan(String jenisBangunan) {
        JenisBangunan = jenisBangunan;
    }

    public String getTingkat() {
        return Tingkat;
    }

    public void setTingkat(String tingkat) {
        Tingkat = tingkat;
    }

    public String getNoIMB() {
        return NoIMB;
    }

    public void setNoIMB(String noIMB) {
        NoIMB = noIMB;
    }

    public String getAtasNamaIMB() {
        return AtasNamaIMB;
    }

    public void setAtasNamaIMB(String atasNamaIMB) {
        AtasNamaIMB = atasNamaIMB;
    }

    public String getAlamatIMB() {
        return AlamatIMB;
    }

    public void setAlamatIMB(String alamatIMB) {
        AlamatIMB = alamatIMB;
    }

    public String getTahunBangunan() {
        return TahunBangunan;
    }

    public void setTahunBangunan(String tahunBangunan) {
        TahunBangunan = tahunBangunan;
    }

    public String getJenisAgunanXBRL() {
        return JenisAgunanXBRL;
    }

    public void setJenisAgunanXBRL(String jenisAgunanXBRL) {
        JenisAgunanXBRL = jenisAgunanXBRL;
    }

    public String getNamaPenghuni() {
        return NamaPenghuni;
    }

    public void setNamaPenghuni(String namaPenghuni) {
        NamaPenghuni = namaPenghuni;
    }

    public String getStatusPenghuni() {
        return StatusPenghuni;
    }

    public void setStatusPenghuni(String statusPenghuni) {
        StatusPenghuni = statusPenghuni;
    }

    public String getHubPenghuniDgPemilik() {
        return HubPenghuniDgPemilik;
    }

    public void setHubPenghuniDgPemilik(String hubPenghuniDgPemilik) {
        HubPenghuniDgPemilik = hubPenghuniDgPemilik;
    }

    public String getPerawatan() {
        return Perawatan;
    }

    public void setPerawatan(String perawatan) {
        Perawatan = perawatan;
    }

    public String getPondasi() {
        return Pondasi;
    }

    public void setPondasi(String pondasi) {
        Pondasi = pondasi;
    }

    public String getDinding() {
        return Dinding;
    }

    public void setDinding(String dinding) {
        Dinding = dinding;
    }

    public String getPlafond() {
        return Plafond;
    }

    public void setPlafond(String plafond) {
        Plafond = plafond;
    }

    public String getAtap() {
        return Atap;
    }

    public void setAtap(String atap) {
        Atap = atap;
    }

    public String getLantai() {
        return Lantai;
    }

    public void setLantai(String lantai) {
        Lantai = lantai;
    }

    public String getTeras() {
        return Teras;
    }

    public void setTeras(String teras) {
        Teras = teras;
    }

    public String getPagar() {
        return Pagar;
    }

    public void setPagar(String pagar) {
        Pagar = pagar;
    }

    public String getGarasiCarport() {
        return GarasiCarport;
    }

    public void setGarasiCarport(String garasiCarport) {
        GarasiCarport = garasiCarport;
    }

    public boolean isFasilitasListrik() {
        return fasilitasListrik;
    }

    public void setFasilitasListrik(boolean fasilitasListrik) {
        this.fasilitasListrik = fasilitasListrik;
    }

    public boolean isFasilitasPAM() {
        return fasilitasPAM;
    }

    public void setFasilitasPAM(boolean fasilitasPAM) {
        this.fasilitasPAM = fasilitasPAM;
    }

    public boolean isTelepon() {
        return Telepon;
    }

    public void setTelepon(boolean telepon) {
        Telepon = telepon;
    }

    public String getTeganganListrik() {
        return teganganListrik;
    }

    public void setTeganganListrik(String teganganListrik) {
        this.teganganListrik = teganganListrik;
    }

    public String getJenisAir() {
        return jenisAir;
    }

    public void setJenisAir(String jenisAir) {
        this.jenisAir = jenisAir;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getPeruntukanLokasi() {
        return PeruntukanLokasi;
    }

    public void setPeruntukanLokasi(String peruntukanLokasi) {
        PeruntukanLokasi = peruntukanLokasi;
    }

    public String getPerkembanganLingkungan() {
        return PerkembanganLingkungan;
    }

    public void setPerkembanganLingkungan(String perkembanganLingkungan) {
        PerkembanganLingkungan = perkembanganLingkungan;
    }

    public String getKepadatan() {
        return Kepadatan;
    }

    public void setKepadatan(String kepadatan) {
        Kepadatan = kepadatan;
    }

    public String getAksesPencapaian() {
        return AksesPencapaian;
    }

    public void setAksesPencapaian(String aksesPencapaian) {
        AksesPencapaian = aksesPencapaian;
    }

    public String getAksesJalanObjek() {
        return AksesJalanObjek;
    }

    public void setAksesJalanObjek(String aksesJalanObjek) {
        AksesJalanObjek = aksesJalanObjek;
    }

    public double getLebarJalanDepan() {
        return LebarJalanDepan;
    }

    public void setLebarJalanDepan(double lebarJalanDepan) {
        LebarJalanDepan = lebarJalanDepan;
    }

    public String getFasilitasSosial() {
        return FasilitasSosial;
    }

    public void setFasilitasSosial(String fasilitasSosial) {
        FasilitasSosial = fasilitasSosial;
    }

    public String getTanggalPemeriksaan() {
        return TanggalPemeriksaan;
    }

    public void setTanggalPemeriksaan(String tanggalPemeriksaan) {
        TanggalPemeriksaan = tanggalPemeriksaan;
    }

    public String getDataPembanding1() {
        return DataPembanding1;
    }

    public void setDataPembanding1(String dataPembanding1) {
        DataPembanding1 = dataPembanding1;
    }

    public String getDataPembanding2() {
        return DataPembanding2;
    }

    public void setDataPembanding2(String dataPembanding2) {
        DataPembanding2 = dataPembanding2;
    }

    public String getDataPembanding3() {
        return DataPembanding3;
    }

    public void setDataPembanding3(String dataPembanding3) {
        DataPembanding3 = dataPembanding3;
    }

    public long getHargaMeterTanah() {
        return HargaMeterTanah;
    }

    public void setHargaMeterTanah(long hargaMeterTanah) {
        HargaMeterTanah = hargaMeterTanah;
    }

    public double getLuasBangunan1() {
        return LuasBangunan1;
    }

    public void setLuasBangunan1(double luasBangunan1) {
        LuasBangunan1 = luasBangunan1;
    }

    public long getHargaBangunan1() {
        return HargaBangunan1;
    }

    public void setHargaBangunan1(long hargaBangunan1) {
        HargaBangunan1 = hargaBangunan1;
    }

    public double getLuasBangunan2() {
        return LuasBangunan2;
    }

    public void setLuasBangunan2(double luasBangunan2) {
        LuasBangunan2 = luasBangunan2;
    }

    public long getHargaBangunan2() {
        return HargaBangunan2;
    }

    public void setHargaBangunan2(long hargaBangunan2) {
        HargaBangunan2 = hargaBangunan2;
    }

    public long getNPWTanahBangunan() {
        return NPWTanahBangunan;
    }

    public void setNPWTanahBangunan(long NPWTanahBangunan) {
        this.NPWTanahBangunan = NPWTanahBangunan;
    }

    public long getNLTanahBangunan() {
        return NLTanahBangunan;
    }

    public void setNLTanahBangunan(long NLTanahBangunan) {
        this.NLTanahBangunan = NLTanahBangunan;
    }

    public String getPendapatHarga() {
        return PendapatHarga;
    }

    public void setPendapatHarga(String pendapatHarga) {
        PendapatHarga = pendapatHarga;
    }

    public String getKondisiSekitar() {
        return KondisiSekitar;
    }

    public void setKondisiSekitar(String kondisiSekitar) {
        KondisiSekitar = kondisiSekitar;
    }

    public String getPendapatKondisiJaminan() {
        return PendapatKondisiJaminan;
    }

    public void setPendapatKondisiJaminan(String pendapatKondisiJaminan) {
        PendapatKondisiJaminan = pendapatKondisiJaminan;
    }

    public String getCollID() {
        return CollID;
    }

    public void setCollID(String collID) {
        CollID = collID;
    }

    public String getJenisBangunanBRINS() {
        return JenisBangunanBRINS;
    }

    public void setJenisBangunanBRINS(String jenisBangunanBRINS) {
        JenisBangunanBRINS = jenisBangunanBRINS;
    }

    public String getLokasiBangunanBrins() {
        return lokasiBangunanBrins;
    }

    public void setLokasiBangunanBrins(String lokasiBangunanBrins) {
        this.lokasiBangunanBrins = lokasiBangunanBrins;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getNamaPasar() {
        return namaPasar;
    }

    public void setNamaPasar(String namaPasar) {
        this.namaPasar = namaPasar;
    }

    public String getNkrBrins() {
        return nkrBrins;
    }

    public void setNkrBrins(String nkrBrins) {
        this.nkrBrins = nkrBrins;
    }

    public long getNPWBangunan() {
        return NPWBangunan;
    }

    public void setNPWBangunan(long NPWBangunan) {
        this.NPWBangunan = NPWBangunan;
    }

    public long getNPWBangunanBrins() {
        return NPWBangunanBrins;
    }

    public void setNPWBangunanBrins(long NPWBangunanBrins) {
        this.NPWBangunanBrins = NPWBangunanBrins;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKota_kab() {
        return kota_kab;
    }

    public void setKota_kab(String kota_kab) {
        this.kota_kab = kota_kab;
    }

    public String getNama_pasar() {
        return nama_pasar;
    }

    public void setNama_pasar(String nama_pasar) {
        this.nama_pasar = nama_pasar;
    }

    public String getAlamat_pasar() {
        return alamat_pasar;
    }

    public void setAlamat_pasar(String alamat_pasar) {
        this.alamat_pasar = alamat_pasar;
    }

    public int getIdBangunan() {
        return idBangunan;
    }

    public void setIdBangunan(int idBangunan) {
        this.idBangunan = idBangunan;
    }

    public int getIdTanah() {
        return idTanah;
    }

    public void setIdTanah(int idTanah) {
        this.idTanah = idTanah;
    }

    public int getIdBatas_Timur() {
        return idBatas_Timur;
    }

    public void setIdBatas_Timur(int idBatas_Timur) {
        this.idBatas_Timur = idBatas_Timur;
    }

    public int getIdBatas_Selatan() {
        return idBatas_Selatan;
    }

    public void setIdBatas_Selatan(int idBatas_Selatan) {
        this.idBatas_Selatan = idBatas_Selatan;
    }

    public int getIdBatas_Barat() {
        return idBatas_Barat;
    }

    public void setIdBatas_Barat(int idBatas_Barat) {
        this.idBatas_Barat = idBatas_Barat;
    }

    public int getIdBatas_Utara() {
        return idBatas_Utara;
    }

    public void setIdBatas_Utara(int idBatas_Utara) {
        this.idBatas_Utara = idBatas_Utara;
    }

    public int getTipeProduk() {
        return tipeProduk;
    }

    public void setTipeProduk(int tipeProduk) {
        this.tipeProduk = tipeProduk;
    }

    public String getKoordinat() {
        return Koordinat;
    }

    public void setKoordinat(String koordinat) {
        Koordinat = koordinat;
    }

    public int getIdDok_BPN() {
        return idDok_BPN;
    }

    public void setIdDok_BPN(int idDok_BPN) {
        this.idDok_BPN = idDok_BPN;
    }
}
