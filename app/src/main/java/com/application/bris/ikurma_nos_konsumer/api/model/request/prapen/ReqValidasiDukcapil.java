package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqValidasiDukcapil {
    @SerializedName("CHANNEL_NAME")
    @Expose
    private String channelName;
    @SerializedName("NIK")
    @Expose
    private String nik;
    @SerializedName("NAMA_LGKP")
    @Expose
    private String namaLgkp;
    @SerializedName("JENIS_KLMIN")
    @Expose
    private String jenisKlmin;
    @SerializedName("TMPT_LHR")
    @Expose
    private String tmptLhr;
    @SerializedName("TGL_LHR")
    @Expose
    private String tglLhr;
    @SerializedName("STATUS_KAWIN")
    @Expose
    private String statusKawin;
    @SerializedName("JENIS_PKRJN")
    @Expose
    private String jenisPkrjn;
    @SerializedName("NAMA_LGKP_IBU")
    @Expose
    private String namaLgkpIbu;
    @SerializedName("ALAMAT")
    @Expose
    private String alamat;
    @SerializedName("NO_PROP")
    @Expose
    private String noProp;
    @SerializedName("NO_KAB")
    @Expose
    private String noKab;
    @SerializedName("NO_KEC")
    @Expose
    private String noKec;
    @SerializedName("NO_KEL")
    @Expose
    private String noKel;
    @SerializedName("PROP_NAME")
    @Expose
    private String propName;
    @SerializedName("KAB_NAME")
    @Expose
    private String kabName;
    @SerializedName("KEC_NAME")
    @Expose
    private String kecName;
    @SerializedName("KEL_NAME")
    @Expose
    private String kelName;
    @SerializedName("NO_RW")
    @Expose
    private String noRw;
    @SerializedName("NO_RT")
    @Expose
    private String noRt;
    @SerializedName("ApplicationId")
    @Expose
    private Long applicationId;
    @SerializedName("UID")
    @Expose
    private String uid;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNamaLgkp() {
        return namaLgkp;
    }

    public void setNamaLgkp(String namaLgkp) {
        this.namaLgkp = namaLgkp;
    }

    public String getJenisKlmin() {
        return jenisKlmin;
    }

    public void setJenisKlmin(String jenisKlmin) {
        this.jenisKlmin = jenisKlmin;
    }

    public String getTmptLhr() {
        return tmptLhr;
    }

    public void setTmptLhr(String tmptLhr) {
        this.tmptLhr = tmptLhr;
    }

    public String getTglLhr() {
        return tglLhr;
    }

    public void setTglLhr(String tglLhr) {
        this.tglLhr = tglLhr;
    }

    public String getStatusKawin() {
        return statusKawin;
    }

    public void setStatusKawin(String statusKawin) {
        this.statusKawin = statusKawin;
    }

    public String getJenisPkrjn() {
        return jenisPkrjn;
    }

    public void setJenisPkrjn(String jenisPkrjn) {
        this.jenisPkrjn = jenisPkrjn;
    }

    public String getNamaLgkpIbu() {
        return namaLgkpIbu;
    }

    public void setNamaLgkpIbu(String namaLgkpIbu) {
        this.namaLgkpIbu = namaLgkpIbu;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoProp() {
        return noProp;
    }

    public void setNoProp(String noProp) {
        this.noProp = noProp;
    }

    public String getNoKab() {
        return noKab;
    }

    public void setNoKab(String noKab) {
        this.noKab = noKab;
    }

    public String getNoKec() {
        return noKec;
    }

    public void setNoKec(String noKec) {
        this.noKec = noKec;
    }

    public String getNoKel() {
        return noKel;
    }

    public void setNoKel(String noKel) {
        this.noKel = noKel;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getKabName() {
        return kabName;
    }

    public void setKabName(String kabName) {
        this.kabName = kabName;
    }

    public String getKecName() {
        return kecName;
    }

    public void setKecName(String kecName) {
        this.kecName = kecName;
    }

    public String getKelName() {
        return kelName;
    }

    public void setKelName(String kelName) {
        this.kelName = kelName;
    }

    public String getNoRw() {
        return noRw;
    }

    public void setNoRw(String noRw) {
        this.noRw = noRw;
    }

    public String getNoRt() {
        return noRt;
    }

    public void setNoRt(String noRt) {
        this.noRt = noRt;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
