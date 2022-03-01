package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateJaminandanDokumen {
        @SerializedName("ApplicationId")
        @Expose
        private Integer applicationId;

        @SerializedName("UID")
        @Expose
        private String uid;

        @SerializedName("JaminandanDokumen")
        @Expose
        private JaminandanDokumen jaminandanDokumen;

        @SerializedName("LembagaPenerbitSk")
        @Expose
        private String LembagaPenerbitSk;

        @SerializedName("DataJaminanKTP")
        @Expose
        private ReqDocument DataJaminanKTP;

        @SerializedName("DataJaminanKTPPasangan")
        @Expose
        private ReqDocument DataJaminanKTPPasangan;

        @SerializedName("DataJaminanNPWP")
        @Expose
        private ReqDocument DataJaminanNPWP;

        @SerializedName("DataJaminanFormAplikasi")
        @Expose
        private ReqDocument DataJaminanFormAplikasi;

        @SerializedName("DataJaminanAsetAKAD")
        @Expose
        private ReqDocument DataJaminanAsetAKAD;

        @SerializedName("DataJaminanSKPensiun")
        @Expose
        private ReqDocument DataJaminanSKPensiun;

        @SerializedName("DataJaminanSKPengangkatan")
        @Expose
        private ReqDocument DataJaminanSKPengangkatan;

        @SerializedName("DataJaminanSKTerakhir")
        @Expose
        private ReqDocument DataJaminanSKTerakhir;

        @SerializedName("DataJaminanSuratRekomendasiInstansi")
        @Expose
        private ReqDocument DataJaminanSuratRekomendasiInstansi;

        @SerializedName("DataJaminanIDCard")
        @Expose
        private ReqDocument DataJaminanIDCard;

    @SerializedName("DataJaminanFormAplikasi2")
    @Expose
    private ReqDocument DataJaminanFormAplikasi2;

    public ReqDocument getDataJaminanFormAplikasi2() {
        return DataJaminanFormAplikasi2;
    }

    public void setDataJaminanFormAplikasi2(ReqDocument dataJaminanFormAplikasi2) {
        DataJaminanFormAplikasi2 = dataJaminanFormAplikasi2;
    }

    public UpdateJaminandanDokumen() {
    }

    public Integer getApplicationId() {
            return applicationId;
        }

        public void setApplicationId(Integer applicationId) {
            this.applicationId = applicationId;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public JaminandanDokumen getJaminandanDokumen() {
            return jaminandanDokumen;
        }

        public void setJaminandanDokumen(JaminandanDokumen jaminandanDokumen) {
            this.jaminandanDokumen = jaminandanDokumen;
        }

    public String getLembagaPenerbitSk() {
        return LembagaPenerbitSk;
    }

    public void setLembagaPenerbitSk(String lembagaPenerbitSk) {
        LembagaPenerbitSk = lembagaPenerbitSk;
    }
    public ReqDocument getDataJaminanKTP() {
        return DataJaminanKTP;
    }

    public void setDataJaminanKTP(ReqDocument dataJaminanKTP) {
        DataJaminanKTP = dataJaminanKTP;
    }

    public ReqDocument getDataJaminanKTPPasangan() {
        return DataJaminanKTPPasangan;
    }

    public void setDataJaminanKTPPasangan(ReqDocument dataJaminanKTPPasangan) {
        DataJaminanKTPPasangan = dataJaminanKTPPasangan;
    }

    public ReqDocument getDataJaminanNPWP() {
        return DataJaminanNPWP;
    }

    public void setDataJaminanNPWP(ReqDocument dataJaminanNPWP) {
        DataJaminanNPWP = dataJaminanNPWP;
    }

    public ReqDocument getDataJaminanFormAplikasi() {
        return DataJaminanFormAplikasi;
    }

    public void setDataJaminanFormAplikasi(ReqDocument dataJaminanFormAplikasi) {
        DataJaminanFormAplikasi = dataJaminanFormAplikasi;
    }

    public ReqDocument getDataJaminanAsetAKAD() {
        return DataJaminanAsetAKAD;
    }

    public void setDataJaminanAsetAKAD(ReqDocument dataJaminanAsetAKAD) {
        DataJaminanAsetAKAD = dataJaminanAsetAKAD;
    }

    public ReqDocument getDataJaminanSKPensiun() {
        return DataJaminanSKPensiun;
    }

    public void setDataJaminanSKPensiun(ReqDocument dataJaminanSKPensiun) {
        DataJaminanSKPensiun = dataJaminanSKPensiun;
    }

    public ReqDocument getDataJaminanSKPengangkatan() {
        return DataJaminanSKPengangkatan;
    }

    public void setDataJaminanSKPengangkatan(ReqDocument dataJaminanSKPengangkatan) {
        DataJaminanSKPengangkatan = dataJaminanSKPengangkatan;
    }

    public ReqDocument getDataJaminanSKTerakhir() {
        return DataJaminanSKTerakhir;
    }

    public void setDataJaminanSKTerakhir(ReqDocument dataJaminanSKTerakhir) {
        DataJaminanSKTerakhir = dataJaminanSKTerakhir;
    }

    public ReqDocument getDataJaminanSuratRekomendasiInstansi() {
        return DataJaminanSuratRekomendasiInstansi;
    }

    public void setDataJaminanSuratRekomendasiInstansi(ReqDocument dataJaminanSuratRekomendasiInstansi) {
        DataJaminanSuratRekomendasiInstansi = dataJaminanSuratRekomendasiInstansi;
    }

    public ReqDocument getDataJaminanIDCard() {
        return DataJaminanIDCard;
    }

    public void setDataJaminanIDCard(ReqDocument dataJaminanIDCard) {
        DataJaminanIDCard = dataJaminanIDCard;
    }
    }
