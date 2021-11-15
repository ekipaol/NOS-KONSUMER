package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UploadImage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DataNasabahPrapen extends RealmObject {
    @PrimaryKey
    @SerializedName("ApplicationId")
    @Expose
    private Long applicationId;
    @SerializedName("No_KTP")
    @Expose
    private String noKTP;
    @SerializedName("Nama_Lengkap")
    @Expose
    private String namaLengkap;
    @SerializedName("Nama_Lengkap_Sesuai_KTP")
    @Expose
    private String namaLengkapSesuaiKTP;
    @SerializedName("Status_Pernikahan")
    @Expose
    private String statusPernikahan;
    @SerializedName("Status_Pernikahan_Sesuai_KTP")
    @Expose
    private String statusPernikahanSesuaiKTP;
    @SerializedName("Jenis_Kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("Tempat_Lahir")
    @Expose
    private String tempatLahir;
    @SerializedName("Tanggal_Lahir")
    @Expose
    private String tanggalLahir;
    @SerializedName("Nama_Ibu_Kandung")
    @Expose
    private String namaIbuKandung;
    @SerializedName("Kode_Pos")
    @Expose
    private String kodePos;
    @SerializedName("Alamat")
    @Expose
    private String alamat;
    @SerializedName("RT")
    @Expose
    private String rt;
    @SerializedName("RW")
    @Expose
    private String rw;
    @SerializedName("Provinsi")
    @Expose
    private String provinsi;
    @SerializedName("Kabupaten")
    @Expose
    private String kabupaten;
    @SerializedName("Kecamatan")
    @Expose
    private String kecamatan;
    @SerializedName("Kelurahan")
    @Expose
    private String kelurahan;
    @SerializedName("Agama")
    @Expose
    private String agama;
    @SerializedName("No_NPWP")
    @Expose
    private String noNPWP;
    @SerializedName("Nomor_Handphone")
    @Expose
    private String nomorHandphone;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("UID")
    @Expose
    private String uid;
    @SerializedName("Tanggal_Penerbitan_KTP")
    @Expose
    private String tanggalPenerbitanKTP;
    @SerializedName("No_Telp_Rumah")
    @Expose
    private String noTelpRumah;
    @SerializedName("No_KTP_Pasangan")
    @Expose
    private String noKTPPasangan;
    @SerializedName("Nama_Lengkap_Pasangan")
    @Expose
    private String namaLengkapPasangan;
    @SerializedName("Tanggal_Lahir_Pasangan")
    @Expose
    private String tanggalLahirPasangan;
    @SerializedName("ImgKtp")
    @Expose
    private String ImgKtp;
    @SerializedName("File_Name_Ktp")
    @Expose
    private String File_Name_Ktp;
    @SerializedName("ImgIdeb")
    @Expose
    private String ImgIdeb;
    @SerializedName("File_Name_Ideb")
    @Expose
    private String File_Name_Ideb;

//    @SerializedName("DataDukcapilIMG2")
//    @Expose
//    private UploadImage DataDukcapilIMG;
//
//    public UploadImage getDataDukcapilIMG() {
//        return DataDukcapilIMG;
//    }
//
//    public void setDataDukcapilIMG(UploadImage dataDukcapilIMG) {
//        DataDukcapilIMG = dataDukcapilIMG;
//    }

    public String getImgIdeb() {
        return ImgIdeb;
    }

    public void setImgIdeb(String imgIdeb) {
        ImgIdeb = imgIdeb;
    }

    public String getFile_Name_Ideb() {
        return File_Name_Ideb;
    }

    public void setFile_Name_Ideb(String file_Name_Ideb) {
        File_Name_Ideb = file_Name_Ideb;
    }

    public String getImgKtp() {
        return ImgKtp;
    }

    public void setImgKtp(String imgKtp) {
        ImgKtp = imgKtp;
    }

    public String getFile_Name_Ktp() {
        return File_Name_Ktp;
    }

    public void setFile_Name_Ktp(String file_Name_Ktp) {
        File_Name_Ktp = file_Name_Ktp;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getNoKTP() {
        return noKTP;
    }

    public void setNoKTP(String noKTP) {
        this.noKTP = noKTP;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNamaLengkapSesuaiKTP() {
        return namaLengkapSesuaiKTP;
    }

    public void setNamaLengkapSesuaiKTP(String namaLengkapSesuaiKTP) {
        this.namaLengkapSesuaiKTP = namaLengkapSesuaiKTP;
    }

    public String getStatusPernikahan() {
        return statusPernikahan;
    }

    public void setStatusPernikahan(String statusPernikahan) {
        this.statusPernikahan = statusPernikahan;
    }

    public String getStatusPernikahanSesuaiKTP() {
        return statusPernikahanSesuaiKTP;
    }

    public void setStatusPernikahanSesuaiKTP(String statusPernikahanSesuaiKTP) {
        this.statusPernikahanSesuaiKTP = statusPernikahanSesuaiKTP;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getNamaIbuKandung() {
        return namaIbuKandung;
    }

    public void setNamaIbuKandung(String namaIbuKandung) {
        this.namaIbuKandung = namaIbuKandung;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
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

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getNoNPWP() {
        return noNPWP;
    }

    public void setNoNPWP(String noNPWP) {
        this.noNPWP = noNPWP;
    }

    public String getNomorHandphone() {
        return nomorHandphone;
    }

    public void setNomorHandphone(String nomorHandphone) {
        this.nomorHandphone = nomorHandphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTanggalPenerbitanKTP() {
        return tanggalPenerbitanKTP;
    }

    public void setTanggalPenerbitanKTP(String tanggalPenerbitanKTP) {
        this.tanggalPenerbitanKTP = tanggalPenerbitanKTP;
    }

    public String getNoTelpRumah() {
        return noTelpRumah;
    }

    public void setNoTelpRumah(String noTelpRumah) {
        this.noTelpRumah = noTelpRumah;
    }

    public String getNoKTPPasangan() {
        return noKTPPasangan;
    }

    public void setNoKTPPasangan(String noKTPPasangan) {
        this.noKTPPasangan = noKTPPasangan;
    }

    public String getNamaLengkapPasangan() {
        return namaLengkapPasangan;
    }

    public void setNamaLengkapPasangan(String namaLengkapPasangan) {
        this.namaLengkapPasangan = namaLengkapPasangan;
    }

    public String getTanggalLahirPasangan() {
        return tanggalLahirPasangan;
    }

    public void setTanggalLahirPasangan(String tanggalLahirPasangan) {
        this.tanggalLahirPasangan = tanggalLahirPasangan;
    }
}
