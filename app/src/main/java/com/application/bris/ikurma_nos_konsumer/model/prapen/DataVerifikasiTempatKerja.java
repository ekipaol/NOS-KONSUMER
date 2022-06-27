package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UploadImage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataVerifikasiTempatKerja {
    @SerializedName("Id")
    @Expose
    private Long id;
    @SerializedName("ApplicationId")
    @Expose
    private Long applicationId;
    @SerializedName("Kesimpulan")
    @Expose
    private String kesimpulan;
    @SerializedName("Hasil_Verifikasi")
    @Expose
    private String hasilVerifikasi;
    @SerializedName("NamaInstansiSPAN")
    @Expose
    private String namaInstansiSPAN;
    @SerializedName("Nama_Instansi_LNGP")
    @Expose
    private String namaInstansiLNGP;
    @SerializedName("NamaInstansiTaspen")
    @Expose
    private String NamaInstansiTaspen;
    @SerializedName("Rate_LNGP")
    @Expose
    private Double rateLNGP;
    @SerializedName("Kota_Tempat_Bekerja")
    @Expose
    private String kotaTempatBekerja;
    @SerializedName("Perkiraan_Gaji")
    @Expose
    private String perkiraanGaji;
    @SerializedName("Perkiraan_Tunjangan")
    @Expose
    private String perkiraanTunjangan;
    @SerializedName("Total_Pendapatan")
    @Expose
    private String totalPendapatan;
    @SerializedName("Created_By")
    @Expose
    private String createdBy;
    @SerializedName("Created_Date")
    @Expose
    private String createdDate;
    @SerializedName("Modified_By")
    @Expose
    private String modifiedBy;
    @SerializedName("Modified_Date")
    @Expose
    private String modifiedDate;
    @SerializedName("NoLNGP")
    @Expose
    private String NoLNGP;
    @SerializedName("IsLNGP")
    @Expose
    private String IsLNGP;
    @SerializedName("NamaInstansi")
    @Expose
    private String NamaInstansi;
    @SerializedName("TempatKerja")
    @Expose
    private DataVerifikasiTempatKerja TempatKerja;
    @SerializedName("InstansiDapen")
    @Expose
    private DataVerifikasiTempatKerja InstansiDapen;
    @SerializedName("TempatKerjaDokumen")
    @Expose
    private UploadImage TempatKerjaDokumen;
    @SerializedName("TempatKerjaFotoSuratRekomendasiInstansi")
    @Expose
    private UploadImage TempatKerjaFotoSuratRekomendasiInstansi;
    @SerializedName("FormAplikasi")
    @Expose
    private UploadImage FormAplikasi;
    @SerializedName("FormAplikasi2")
    @Expose
    private UploadImage FormAplikasi2;
    @SerializedName("IDCard")
    @Expose
    private UploadImage IDCard;
    @SerializedName("KTPNasabah")
    @Expose
    private UploadImage KTPNasabah;
    @SerializedName("KTPPasangan")
    @Expose
    private UploadImage KTPPasangan;
    @SerializedName("NPWP")
    @Expose
    private UploadImage NPWP;
    @SerializedName("SKPengangkatan")
    @Expose
    private UploadImage SKPengangkatan;
    @SerializedName("SKPensiun")
    @Expose
    private UploadImage SKPensiun;
    @SerializedName("SKTerakhir")
    @Expose
    private UploadImage SKTerakhir;
    @SerializedName("Aset")
    @Expose
    private UploadImage Aset;
    @SerializedName("IsMarried")
    @Expose
    private String IsMarried;
    @SerializedName("IsPensiun")
    @Expose
    private String IsPensiun;

    @SerializedName("Tanggal_Expired_LNGP")
    @Expose
    private String tanggalExpiredLngp;
    @SerializedName("Escrow")
    @Expose
    private String escrow;
    @SerializedName("Nama_Nasabah_SPAN")
    @Expose
    private String namaNasabahSpan;
    @SerializedName("Gaji_SPAN")
    @Expose
    private String gajiSpan;
    @SerializedName("Tunjangan_SPAN")
    @Expose
    private String tunjanganSpan;
    @SerializedName("No_Rekening_SPAN")
    @Expose
    private String rekeningSpan;

    public String getTanggalExpiredLngp() {
        return tanggalExpiredLngp;
    }

    public void setTanggalExpiredLngp(String tanggalExpiredLngp) {
        this.tanggalExpiredLngp = tanggalExpiredLngp;
    }

    public String getEscrow() {
        return escrow;
    }

    public void setEscrow(String escrow) {
        this.escrow = escrow;
    }

    public String getNamaNasabahSpan() {
        return namaNasabahSpan;
    }

    public void setNamaNasabahSpan(String namaNasabahSpan) {
        this.namaNasabahSpan = namaNasabahSpan;
    }

    public String getGajiSpan() {
        return gajiSpan;
    }

    public void setGajiSpan(String gajiSpan) {
        this.gajiSpan = gajiSpan;
    }

    public String getTunjanganSpan() {
        return tunjanganSpan;
    }

    public void setTunjanganSpan(String tunjanganSpan) {
        this.tunjanganSpan = tunjanganSpan;
    }

    public String getRekeningSpan() {
        return rekeningSpan;
    }

    public void setRekeningSpan(String rekeningSpan) {
        this.rekeningSpan = rekeningSpan;
    }

    public UploadImage getFormAplikasi2() {
        return FormAplikasi2;
    }

    public void setFormAplikasi2(UploadImage formAplikasi2) {
        FormAplikasi2 = formAplikasi2;
    }

    public String getNamaInstansiTaspen() {
        return NamaInstansiTaspen;
    }

    public void setNamaInstansiTaspen(String namaInstansiTaspen) {
        NamaInstansiTaspen = namaInstansiTaspen;
    }

    public UploadImage getAset() {
        return Aset;
    }
    public void setAset(UploadImage aset) {
        Aset = aset;
    }

    public String getNoLNGP() {
        return NoLNGP;
    }

    public void setNoLNGP(String noLNGP) {
        NoLNGP = noLNGP;
    }

    public String getIsLNGP() {
        return IsLNGP;
    }

    public void setIsLNGP(String isLNGP) {
        IsLNGP = isLNGP;
    }

    public String getNamaInstansi() {
        return NamaInstansi;
    }

    public void setNamaInstansi(String namaInstansi) {
        NamaInstansi = namaInstansi;
    }

    public DataVerifikasiTempatKerja getInstansiDapen() {
        return InstansiDapen;
    }

    public void setInstansiDapen(DataVerifikasiTempatKerja instansiDapen) {
        InstansiDapen = instansiDapen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getKesimpulan() {
        return kesimpulan;
    }

    public void setKesimpulan(String kesimpulan) {
        this.kesimpulan = kesimpulan;
    }

    public String getHasilVerifikasi() {
        return hasilVerifikasi;
    }

    public void setHasilVerifikasi(String hasilVerifikasi) {
        this.hasilVerifikasi = hasilVerifikasi;
    }

    public String getNamaInstansiSPAN() {
        return namaInstansiSPAN;
    }

    public void setNamaInstansiSPAN(String namaInstansiSPAN) {
        this.namaInstansiSPAN = namaInstansiSPAN;
    }

    public String getNamaInstansiLNGP() {
        return namaInstansiLNGP;
    }

    public void setNamaInstansiLNGP(String namaInstansiLNGP) {
        this.namaInstansiLNGP = namaInstansiLNGP;
    }

    public Double getRateLNGP() {
        return rateLNGP;
    }

    public void setRateLNGP(Double rateLNGP) {
        this.rateLNGP = rateLNGP;
    }

    public String getKotaTempatBekerja() {
        return kotaTempatBekerja;
    }

    public void setKotaTempatBekerja(String kotaTempatBekerja) {
        this.kotaTempatBekerja = kotaTempatBekerja;
    }

    public String getPerkiraanGaji() {
        return perkiraanGaji;
    }

    public void setPerkiraanGaji(String perkiraanGaji) {
        this.perkiraanGaji = perkiraanGaji;
    }

    public String getPerkiraanTunjangan() {
        return perkiraanTunjangan;
    }

    public void setPerkiraanTunjangan(String perkiraanTunjangan) {
        this.perkiraanTunjangan = perkiraanTunjangan;
    }

    public String getTotalPendapatan() {
        return totalPendapatan;
    }

    public void setTotalPendapatan(String totalPendapatan) {
        this.totalPendapatan = totalPendapatan;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public DataVerifikasiTempatKerja getTempatKerja() {
        return TempatKerja;
    }

    public void setTempatKerja(DataVerifikasiTempatKerja tempatKerja) {
        TempatKerja = tempatKerja;
    }

    public UploadImage getTempatKerjaDokumen() {
        return TempatKerjaDokumen;
    }

    public void setTempatKerjaDokumen(UploadImage tempatKerjaDokumen) {
        TempatKerjaDokumen = tempatKerjaDokumen;
    }

    public UploadImage getTempatKerjaFotoSuratRekomendasiInstansi() {
        return TempatKerjaFotoSuratRekomendasiInstansi;
    }

    public void setTempatKerjaFotoSuratRekomendasiInstansi(UploadImage tempatKerjaFotoSuratRekomendasiInstansi) {
        TempatKerjaFotoSuratRekomendasiInstansi = tempatKerjaFotoSuratRekomendasiInstansi;
    }

    public UploadImage getFormAplikasi() {
        return FormAplikasi;
    }

    public void setFormAplikasi(UploadImage formAplikasi) {
        FormAplikasi = formAplikasi;
    }

    public UploadImage getIDCard() {
        return IDCard;
    }

    public void setIDCard(UploadImage IDCard) {
        this.IDCard = IDCard;
    }

    public UploadImage getKTPNasabah() {
        return KTPNasabah;
    }

    public void setKTPNasabah(UploadImage KTPNasabah) {
        this.KTPNasabah = KTPNasabah;
    }

    public UploadImage getKTPPasangan() {
        return KTPPasangan;
    }

    public void setKTPPasangan(UploadImage KTPPasangan) {
        this.KTPPasangan = KTPPasangan;
    }

    public UploadImage getNPWP() {
        return NPWP;
    }

    public void setNPWP(UploadImage NPWP) {
        this.NPWP = NPWP;
    }

    public UploadImage getSKPengangkatan() {
        return SKPengangkatan;
    }

    public void setSKPengangkatan(UploadImage SKPengangkatan) {
        this.SKPengangkatan = SKPengangkatan;
    }

    public UploadImage getSKPensiun() {
        return SKPensiun;
    }

    public void setSKPensiun(UploadImage SKPensiun) {
        this.SKPensiun = SKPensiun;
    }

    public UploadImage getSKTerakhir() {
        return SKTerakhir;
    }

    public void setSKTerakhir(UploadImage SKTerakhir) {
        this.SKTerakhir = SKTerakhir;
    }

    public String getIsMarried() {
        return IsMarried;
    }

    public void setIsMarried(String isMarried) {
        IsMarried = isMarried;
    }

    public String getIsPensiun() {
        return IsPensiun;
    }

    public void setIsPensiun(String isPensiun) {
        IsPensiun = isPensiun;
    }
}
