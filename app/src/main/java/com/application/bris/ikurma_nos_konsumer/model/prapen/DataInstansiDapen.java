package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DataInstansiDapen extends RealmObject {
    @PrimaryKey
    @SerializedName("ApplicationId")
    @Expose
    private Long applicationId;
    @SerializedName("Lembaga_Pengelola_Pensiun")
    @Expose
    private String lembagaPengelolaPensiun;
    @SerializedName("Nomor_Pensiunan")
    @Expose
    private String nomorPensiunan;
    @SerializedName("Nomor_Kepegawaian")
    @Expose
    private String nomorKepegawaian;
    @SerializedName("Nama_Instansi")
    @Expose
    private String namaInstansi;
    @SerializedName("IsLNGP")
    @Expose
    private String isLNGP;
    @SerializedName("No_LNGP")
    @Expose
    private String noLNGP;
    @SerializedName("Nama_Instansi_LNGP")
    @Expose
    private String namaInstansiLNGP;
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
    private Long totalPendapatan;
    @SerializedName("Is_Nasabah_BSI")
    @Expose
    private String isNasabahBSI;
    @SerializedName("Treatment_Rekening_Pendapata")
    @Expose
    private String treatmentRekeningPendapata;
    @SerializedName("Nomor_Rekening")
    @Expose
    private String nomorRekening;
    @SerializedName("Is_Dapat_Bergerak_aktifitas")
    @Expose
    private Boolean isDapatBergerakAktifitas;
    @SerializedName("Is_Dalam_Pengawasan_Dokter")
    @Expose
    private Boolean isDalamPengawasanDokter;
    @SerializedName("Is_Riwayat_penyakit")
    @Expose
    private Boolean isRiwayatPenyakit;
    @SerializedName("Is_Satu_Rumah")
    @Expose
    private Boolean isSatuRumah;
    @SerializedName("Is_Memiliki_Usaha")
    @Expose
    private Boolean isMemilikiUsaha;
    @SerializedName("Is_Kiriman_Rutin")
    @Expose
    private Boolean isKirimanRutin;
    @SerializedName("Nominal_Per_Bulan")
    @Expose
    private String nominalPerBulan;
    @SerializedName("Catatan")
    @Expose
    private String catatan;
    @SerializedName("No_CIF")
    @Expose
    private String noCIF;
    @SerializedName("Tanggal_Input_Rekening")
    @Expose
    private String tanggalInputRekening;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getLembagaPengelolaPensiun() {
        return lembagaPengelolaPensiun;
    }

    public void setLembagaPengelolaPensiun(String lembagaPengelolaPensiun) {
        this.lembagaPengelolaPensiun = lembagaPengelolaPensiun;
    }

    public String getNomorPensiunan() {
        return nomorPensiunan;
    }

    public void setNomorPensiunan(String nomorPensiunan) {
        this.nomorPensiunan = nomorPensiunan;
    }

    public String getNomorKepegawaian() {
        return nomorKepegawaian;
    }

    public void setNomorKepegawaian(String nomorKepegawaian) {
        this.nomorKepegawaian = nomorKepegawaian;
    }

    public String getNamaInstansi() {
        return namaInstansi;
    }

    public void setNamaInstansi(String namaInstansi) {
        this.namaInstansi = namaInstansi;
    }

    public String getIsLNGP() {
        return isLNGP;
    }

    public void setIsLNGP(String isLNGP) {
        this.isLNGP = isLNGP;
    }

    public String getNoLNGP() {
        return noLNGP;
    }

    public void setNoLNGP(String noLNGP) {
        this.noLNGP = noLNGP;
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

    public Long getTotalPendapatan() {
        return totalPendapatan;
    }

    public void setTotalPendapatan(Long totalPendapatan) {
        this.totalPendapatan = totalPendapatan;
    }

    public String getIsNasabahBSI() {
        return isNasabahBSI;
    }

    public void setIsNasabahBSI(String isNasabahBSI) {
        this.isNasabahBSI = isNasabahBSI;
    }

    public String getTreatmentRekeningPendapata() {
        return treatmentRekeningPendapata;
    }

    public void setTreatmentRekeningPendapata(String treatmentRekeningPendapata) {
        this.treatmentRekeningPendapata = treatmentRekeningPendapata;
    }

    public String getNomorRekening() {
        return nomorRekening;
    }

    public void setNomorRekening(String nomorRekening) {
        this.nomorRekening = nomorRekening;
    }

    public Boolean getDapatBergerakAktifitas() {
        return isDapatBergerakAktifitas;
    }

    public void setDapatBergerakAktifitas(Boolean dapatBergerakAktifitas) {
        isDapatBergerakAktifitas = dapatBergerakAktifitas;
    }

    public Boolean getDalamPengawasanDokter() {
        return isDalamPengawasanDokter;
    }

    public void setDalamPengawasanDokter(Boolean dalamPengawasanDokter) {
        isDalamPengawasanDokter = dalamPengawasanDokter;
    }

    public Boolean getRiwayatPenyakit() {
        return isRiwayatPenyakit;
    }

    public void setRiwayatPenyakit(Boolean riwayatPenyakit) {
        isRiwayatPenyakit = riwayatPenyakit;
    }

    public Boolean getSatuRumah() {
        return isSatuRumah;
    }

    public void setSatuRumah(Boolean satuRumah) {
        isSatuRumah = satuRumah;
    }

    public Boolean getMemilikiUsaha() {
        return isMemilikiUsaha;
    }

    public void setMemilikiUsaha(Boolean memilikiUsaha) {
        isMemilikiUsaha = memilikiUsaha;
    }

    public Boolean getKirimanRutin() {
        return isKirimanRutin;
    }

    public void setKirimanRutin(Boolean kirimanRutin) {
        isKirimanRutin = kirimanRutin;
    }

    public String getNominalPerBulan() {
        return nominalPerBulan;
    }

    public void setNominalPerBulan(String nominalPerBulan) {
        this.nominalPerBulan = nominalPerBulan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getNoCIF() {
        return noCIF;
    }

    public void setNoCIF(String noCIF) {
        this.noCIF = noCIF;
    }

    public String getTanggalInputRekening() {
        return tanggalInputRekening;
    }

    public void setTanggalInputRekening(String tanggalInputRekening) {
        this.tanggalInputRekening = tanggalInputRekening;
    }
}
