package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataDetilInstansi {
    @SerializedName("Nama_Instansi")
    @Expose
    private String namaInstansi;
    @SerializedName("Escrow")
    @Expose
    private String escrow;
    @SerializedName("Branch_Office")
    @Expose
    private String branchOffice;
    @SerializedName("Area_Office")
    @Expose
    private String areaOffice;
    @SerializedName("Regional")
    @Expose
    private String regional;
    @SerializedName("CIF")
    @Expose
    private String cif;
    @SerializedName("ID_Master_Instansi")
    @Expose
    private String iDMasterInstansi;
    @SerializedName("Instansi_Induk")
    @Expose
    private String instansiInduk;
    @SerializedName("Tipe_Pembayaran")
    @Expose
    private String tipePembayaran;
    @SerializedName("Tipe_Produk")
    @Expose
    private String tipeProduk;
    @SerializedName("Tahun_Berdiri")
    @Expose
    private String tahunBerdiri;
    @SerializedName("PKS")
    @Expose
    private String pks;
    @SerializedName("Ruang_Lingkup_PKS")
    @Expose
    private String ruangLingkupPKS;
    @SerializedName("Unit_Bisnis_Pengusul_PKS")
    @Expose
    private String unitBisnisPengusulPKS;
    @SerializedName("No_PKS")
    @Expose
    private String noPKS;
    @SerializedName("Mulai_PKS")
    @Expose
    private String mulaiPKS;
    @SerializedName("Akhir_PKS")
    @Expose
    private String akhirPKS;
    @SerializedName("Jangka_Waktu_PKS")
    @Expose
    private Integer jangkaWaktuPKS;
    @SerializedName("Perpanjangan_Otomatis_PKS")
    @Expose
    private Boolean perpanjanganOtomatisPKS;
    @SerializedName("Keterangan_Status_PKS")
    @Expose
    private String keteranganStatusPKS;
    @SerializedName("Img_PKS_Induk")
    @Expose
    private String imgPKSInduk;
    @SerializedName("Filename_PKS_Induk")
    @Expose
    private String filenamePksInduk;
    @SerializedName("Status_PKS")
    @Expose
    private String statusPKS;
    @SerializedName("Alamat_Korespondensi")
    @Expose
    private String alamatKorespondensi;
    @SerializedName("Key_Person")
    @Expose
    private String keyPerson;
    @SerializedName("Telepon_Key_Person")
    @Expose
    private String teleponKeyPerson;
    @SerializedName("No_Telp_Instansi")
    @Expose
    private String noTelpInstansi;
    @SerializedName("Jasa_Pengolahan")
    @Expose
    private String jasaPengolahan;
    @SerializedName("Data_LKP_Utama")
    @Expose
    private DataLkpUtama dataLkpUtama;
    @SerializedName("Data_Instansi_Dokumen_Lainnya")
    @Expose
    private List<UploadImage> dokumenLainnya;

    public String getFilenamePksInduk() {
        return filenamePksInduk;
    }

    public void setFilenamePksInduk(String filenamePksInduk) {
        this.filenamePksInduk = filenamePksInduk;
    }

    public String getNamaInstansi() {
        return namaInstansi;
    }

    public void setNamaInstansi(String namaInstansi) {
        this.namaInstansi = namaInstansi;
    }

    public String getEscrow() {
        return escrow;
    }

    public void setEscrow(String escrow) {
        this.escrow = escrow;
    }

    public String getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(String branchOffice) {
        this.branchOffice = branchOffice;
    }

    public String getAreaOffice() {
        return areaOffice;
    }

    public void setAreaOffice(String areaOffice) {
        this.areaOffice = areaOffice;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getiDMasterInstansi() {
        return iDMasterInstansi;
    }

    public void setiDMasterInstansi(String iDMasterInstansi) {
        this.iDMasterInstansi = iDMasterInstansi;
    }

    public String getInstansiInduk() {
        return instansiInduk;
    }

    public void setInstansiInduk(String instansiInduk) {
        this.instansiInduk = instansiInduk;
    }

    public String getTipePembayaran() {
        return tipePembayaran;
    }

    public void setTipePembayaran(String tipePembayaran) {
        this.tipePembayaran = tipePembayaran;
    }

    public String getTipeProduk() {
        return tipeProduk;
    }

    public void setTipeProduk(String tipeProduk) {
        this.tipeProduk = tipeProduk;
    }

    public String getTahunBerdiri() {
        return tahunBerdiri;
    }

    public void setTahunBerdiri(String tahunBerdiri) {
        this.tahunBerdiri = tahunBerdiri;
    }

    public String getPks() {
        return pks;
    }

    public void setPks(String pks) {
        this.pks = pks;
    }

    public String getRuangLingkupPKS() {
        return ruangLingkupPKS;
    }

    public void setRuangLingkupPKS(String ruangLingkupPKS) {
        this.ruangLingkupPKS = ruangLingkupPKS;
    }

    public String getUnitBisnisPengusulPKS() {
        return unitBisnisPengusulPKS;
    }

    public void setUnitBisnisPengusulPKS(String unitBisnisPengusulPKS) {
        this.unitBisnisPengusulPKS = unitBisnisPengusulPKS;
    }

    public String getNoPKS() {
        return noPKS;
    }

    public void setNoPKS(String noPKS) {
        this.noPKS = noPKS;
    }

    public String getMulaiPKS() {
        return mulaiPKS;
    }

    public void setMulaiPKS(String mulaiPKS) {
        this.mulaiPKS = mulaiPKS;
    }

    public String getAkhirPKS() {
        return akhirPKS;
    }

    public void setAkhirPKS(String akhirPKS) {
        this.akhirPKS = akhirPKS;
    }

    public Integer getJangkaWaktuPKS() {
        return jangkaWaktuPKS;
    }

    public void setJangkaWaktuPKS(Integer jangkaWaktuPKS) {
        this.jangkaWaktuPKS = jangkaWaktuPKS;
    }

    public Boolean getPerpanjanganOtomatisPKS() {
        return perpanjanganOtomatisPKS;
    }

    public void setPerpanjanganOtomatisPKS(Boolean perpanjanganOtomatisPKS) {
        this.perpanjanganOtomatisPKS = perpanjanganOtomatisPKS;
    }

    public String getKeteranganStatusPKS() {
        return keteranganStatusPKS;
    }

    public void setKeteranganStatusPKS(String keteranganStatusPKS) {
        this.keteranganStatusPKS = keteranganStatusPKS;
    }

    public String getImgPKSInduk() {
        return imgPKSInduk;
    }

    public void setImgPKSInduk(String imgPKSInduk) {
        this.imgPKSInduk = imgPKSInduk;
    }

    public String getStatusPKS() {
        return statusPKS;
    }

    public void setStatusPKS(String statusPKS) {
        this.statusPKS = statusPKS;
    }

    public String getAlamatKorespondensi() {
        return alamatKorespondensi;
    }

    public void setAlamatKorespondensi(String alamatKorespondensi) {
        this.alamatKorespondensi = alamatKorespondensi;
    }

    public String getKeyPerson() {
        return keyPerson;
    }

    public void setKeyPerson(String keyPerson) {
        this.keyPerson = keyPerson;
    }

    public String getTeleponKeyPerson() {
        return teleponKeyPerson;
    }

    public void setTeleponKeyPerson(String teleponKeyPerson) {
        this.teleponKeyPerson = teleponKeyPerson;
    }

    public String getNoTelpInstansi() {
        return noTelpInstansi;
    }

    public void setNoTelpInstansi(String noTelpInstansi) {
        this.noTelpInstansi = noTelpInstansi;
    }

    public String getJasaPengolahan() {
        return jasaPengolahan;
    }

    public void setJasaPengolahan(String jasaPengolahan) {
        this.jasaPengolahan = jasaPengolahan;
    }

    public DataLkpUtama getDataLkpUtama() {
        return dataLkpUtama;
    }

    public void setDataLkpUtama(DataLkpUtama dataLkpUtama) {
        this.dataLkpUtama = dataLkpUtama;
    }

    public List<UploadImage> getDokumenLainnya() {
        return dokumenLainnya;
    }

    public void setDokumenLainnya(List<UploadImage> dokumenLainnya) {
        this.dokumenLainnya = dokumenLainnya;
    }
}
