package com.application.bris.ikurma_nos_konsumer.api.model.response_prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MparseResponseTaspen {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("ApplicationId")
    @Expose
    private Integer applicationId;
    @SerializedName("NIP")
    @Expose
    private String nip;
    @SerializedName("No_KPE")
    @Expose
    private String noKPE;
    @SerializedName("Nama_Lengkap_Peserta")
    @Expose
    private String namaLengkapPeserta;
    @SerializedName("Tanggal_Lahir_Peserta")
    @Expose
    private String tanggalLahirPeserta;
    @SerializedName("Tanggal_BUP")
    @Expose
    private String tanggalBUP;
    @SerializedName("Instansi_Kantor_Bayar")
    @Expose
    private String instansiKantorBayar;
    @SerializedName("Keterangan_Flagging")
    @Expose
    private String keteranganFlagging;
    @SerializedName("Tanggal_Mulai_Taspen")
    @Expose
    private String tanggalMulaiTaspen;
    @SerializedName("Pangkat")
    @Expose
    private String pangkat;
    @SerializedName("Tanggal_Pensiun")
    @Expose
    private String tanggalPensiun;
    @SerializedName("Hak_Tabungan_Hari_Tua")
    @Expose
    private String hakTabunganHariTua;
    @SerializedName("Hak_Pensiun")
    @Expose
    private String hakPensiun;
    @SerializedName("CreatedOn")
    @Expose
    private String createdOn;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;
    @SerializedName("ModifiedOn")
    @Expose
    private String modifiedOn;
    @SerializedName("ModiefiedBy")
    @Expose
    private String modiefiedBy;
    @SerializedName("nomorTaspen")
    @Expose
    private String nomorTaspen;

    public String getNomorTaspen() {
        return nomorTaspen;
    }

    public void setNomorTaspen(String nomorTaspen) {
        this.nomorTaspen = nomorTaspen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNoKPE() {
        return noKPE;
    }

    public void setNoKPE(String noKPE) {
        this.noKPE = noKPE;
    }

    public String getNamaLengkapPeserta() {
        return namaLengkapPeserta;
    }

    public void setNamaLengkapPeserta(String namaLengkapPeserta) {
        this.namaLengkapPeserta = namaLengkapPeserta;
    }

    public String getTanggalLahirPeserta() {
        return tanggalLahirPeserta;
    }

    public void setTanggalLahirPeserta(String tanggalLahirPeserta) {
        this.tanggalLahirPeserta = tanggalLahirPeserta;
    }

    public String getTanggalBUP() {
        return tanggalBUP;
    }

    public void setTanggalBUP(String tanggalBUP) {
        this.tanggalBUP = tanggalBUP;
    }

    public String getInstansiKantorBayar() {
        return instansiKantorBayar;
    }

    public void setInstansiKantorBayar(String instansiKantorBayar) {
        this.instansiKantorBayar = instansiKantorBayar;
    }

    public String getKeteranganFlagging() {
        return keteranganFlagging;
    }

    public void setKeteranganFlagging(String keteranganFlagging) {
        this.keteranganFlagging = keteranganFlagging;
    }

    public String getTanggalMulaiTaspen() {
        return tanggalMulaiTaspen;
    }

    public void setTanggalMulaiTaspen(String tanggalMulaiTaspen) {
        this.tanggalMulaiTaspen = tanggalMulaiTaspen;
    }

    public String getPangkat() {
        return pangkat;
    }

    public void setPangkat(String pangkat) {
        this.pangkat = pangkat;
    }

    public String getTanggalPensiun() {
        return tanggalPensiun;
    }

    public void setTanggalPensiun(String tanggalPensiun) {
        this.tanggalPensiun = tanggalPensiun;
    }

    public String getHakTabunganHariTua() {
        return hakTabunganHariTua;
    }

    public void setHakTabunganHariTua(String hakTabunganHariTua) {
        this.hakTabunganHariTua = hakTabunganHariTua;
    }

    public String getHakPensiun() {
        return hakPensiun;
    }

    public void setHakPensiun(String hakPensiun) {
        this.hakPensiun = hakPensiun;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getModiefiedBy() {
        return modiefiedBy;
    }

    public void setModiefiedBy(String modiefiedBy) {
        this.modiefiedBy = modiefiedBy;
    }
}
