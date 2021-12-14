package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UploadDokumen {

    @SerializedName("Form_Mutas_Kantor_Bayar")
    @Expose
    private ReqDocument Form_Mutas_Kantor_Bayar;

    @SerializedName("Form_Mutasi_Kantor_Bayar_Taspen")
    @Expose
    private ReqDocument Form_Mutasi_Kantor_Bayar_Taspen;

    @SerializedName("Foto_Bukti_Otentikasi_Nasabah")
    @Expose
    private ReqDocument Foto_Bukti_Otentikasi_Nasabah;

    @SerializedName("Foto_Covernote_Asuransi")
    @Expose
    private ReqDocument Foto_Covernote_Asuransi;

    @SerializedName("Foto_Proses_Penandatanganan_Akad")
    @Expose
    private ReqDocument Foto_Proses_Penandatanganan_Akad;

    @SerializedName("Foto_SK_Pengangkatan")
    @Expose
    private ReqDocument Foto_SK_Pengangkatan;

    @SerializedName("Foto_SK_Pensiun")
    @Expose
    private ReqDocument Foto_SK_Pensiun;

    @SerializedName("Foto_SK_Terakhir")
    @Expose
    private ReqDocument Foto_SK_Terakhir;

    @SerializedName("Ijarah_Akad_Ijarah")
    @Expose
    private ReqDocument Ijarah_Akad_Ijarah;

    @SerializedName("Ijarah_Akad_Wakalah")
    @Expose
    private ReqDocument Ijarah_Akad_Wakalah;

    @SerializedName("Ijarah_Lampiran_Jadwal_Angsuran")
    @Expose
    private ReqDocument Ijarah_Lampiran_Jadwal_Angsuran;

    @SerializedName("Ijarah_Purchase_Order")
    @Expose
    private ReqDocument Ijarah_Purchase_Order;

    @SerializedName("MMQ_Akad_MMQ")
    @Expose
    private ReqDocument MMQ_Akad_MMQ;

    @SerializedName("MMQ_Lampiran_Jadwal_Pengambil_Alihan")
    @Expose
    private ReqDocument MMQ_Lampiran_Jadwal_Pengambil_Alihan;

    @SerializedName("MMQ_Laporan_Penilaian_Aset")
    @Expose
    private ReqDocument MMQ_Laporan_Penilaian_Aset;

    @SerializedName("MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga")
    @Expose
    private ReqDocument MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga;

    @SerializedName("MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri")
    @Expose
    private ReqDocument MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri;

    @SerializedName("Murabahah_Akad_Murabahah")
    @Expose
    private ReqDocument Murabahah_Akad_Murabahah;

    @SerializedName("Murabahah_Akad_Wakalah")
    @Expose
    private ReqDocument Murabahah_Akad_Wakalah;

    @SerializedName("Murabahah_Lampiran_Jadwal_Angsuran")
    @Expose
    private ReqDocument Murabahah_Lampiran_Jadwal_Angsuran;

    @SerializedName("Murabahah_Purchase_Order")
    @Expose
    private ReqDocument Murabahah_Purchase_Order;

    @SerializedName("Murabahah_Surat_Tanda_Terima_Barang")
    @Expose
    private ReqDocument Murabahah_Surat_Tanda_Terima_Barang;

    @SerializedName("Rahn_Akad_Rahn")
    @Expose
    private ReqDocument Rahn_Akad_Rahn;

    @SerializedName("Rahn_Lampiran_Jadwal_Angsuran")
    @Expose
    private ReqDocument Rahn_Lampiran_Jadwal_Angsuran;

    @SerializedName("SUP")
    @Expose
    private ReqDocument SUP;

    @SerializedName("Surat_Kuasa_Pernyataan_Flagging")
    @Expose
    private ReqDocument Surat_Kuasa_Pernyataan_Flagging;

    @SerializedName("Surat_Pernyataan_Kuasa_Pembiayaan")
    @Expose
    private ReqDocument Surat_Pernyataan_Kuasa_Pembiayaan;

    @SerializedName("Tanda_Terima_Dokumen_SK")
    @Expose
    private ReqDocument Tanda_Terima_Dokumen_SK;

    @SerializedName("DokumenUmum")
    @Expose
    private List<ReqDocumentUmum> DokumenUmum;

    public ReqDocument getForm_Mutas_Kantor_Bayar() {
        return Form_Mutas_Kantor_Bayar;
    }

    public void setForm_Mutas_Kantor_Bayar(ReqDocument form_Mutas_Kantor_Bayar) {
        Form_Mutas_Kantor_Bayar = form_Mutas_Kantor_Bayar;
    }

    public ReqDocument getForm_Mutasi_Kantor_Bayar_Taspen() {
        return Form_Mutasi_Kantor_Bayar_Taspen;
    }

    public void setForm_Mutasi_Kantor_Bayar_Taspen(ReqDocument form_Mutasi_Kantor_Bayar_Taspen) {
        Form_Mutasi_Kantor_Bayar_Taspen = form_Mutasi_Kantor_Bayar_Taspen;
    }

    public ReqDocument getFoto_Bukti_Otentikasi_Nasabah() {
        return Foto_Bukti_Otentikasi_Nasabah;
    }

    public void setFoto_Bukti_Otentikasi_Nasabah(ReqDocument foto_Bukti_Otentikasi_Nasabah) {
        Foto_Bukti_Otentikasi_Nasabah = foto_Bukti_Otentikasi_Nasabah;
    }

    public ReqDocument getFoto_Covernote_Asuransi() {
        return Foto_Covernote_Asuransi;
    }

    public void setFoto_Covernote_Asuransi(ReqDocument foto_Covernote_Asuransi) {
        Foto_Covernote_Asuransi = foto_Covernote_Asuransi;
    }

    public ReqDocument getFoto_Proses_Penandatanganan_Akad() {
        return Foto_Proses_Penandatanganan_Akad;
    }

    public void setFoto_Proses_Penandatanganan_Akad(ReqDocument foto_Proses_Penandatanganan_Akad) {
        Foto_Proses_Penandatanganan_Akad = foto_Proses_Penandatanganan_Akad;
    }

    public ReqDocument getFoto_SK_Pengangkatan() {
        return Foto_SK_Pengangkatan;
    }

    public void setFoto_SK_Pengangkatan(ReqDocument foto_SK_Pengangkatan) {
        Foto_SK_Pengangkatan = foto_SK_Pengangkatan;
    }

    public ReqDocument getFoto_SK_Pensiun() {
        return Foto_SK_Pensiun;
    }

    public void setFoto_SK_Pensiun(ReqDocument foto_SK_Pensiun) {
        Foto_SK_Pensiun = foto_SK_Pensiun;
    }

    public ReqDocument getFoto_SK_Terakhir() {
        return Foto_SK_Terakhir;
    }

    public void setFoto_SK_Terakhir(ReqDocument foto_SK_Terakhir) {
        Foto_SK_Terakhir = foto_SK_Terakhir;
    }

    public ReqDocument getIjarah_Akad_Ijarah() {
        return Ijarah_Akad_Ijarah;
    }

    public void setIjarah_Akad_Ijarah(ReqDocument ijarah_Akad_Ijarah) {
        Ijarah_Akad_Ijarah = ijarah_Akad_Ijarah;
    }

    public ReqDocument getIjarah_Akad_Wakalah() {
        return Ijarah_Akad_Wakalah;
    }

    public void setIjarah_Akad_Wakalah(ReqDocument ijarah_Akad_Wakalah) {
        Ijarah_Akad_Wakalah = ijarah_Akad_Wakalah;
    }

    public ReqDocument getIjarah_Lampiran_Jadwal_Angsuran() {
        return Ijarah_Lampiran_Jadwal_Angsuran;
    }

    public void setIjarah_Lampiran_Jadwal_Angsuran(ReqDocument ijarah_Lampiran_Jadwal_Angsuran) {
        Ijarah_Lampiran_Jadwal_Angsuran = ijarah_Lampiran_Jadwal_Angsuran;
    }

    public ReqDocument getIjarah_Purchase_Order() {
        return Ijarah_Purchase_Order;
    }

    public void setIjarah_Purchase_Order(ReqDocument ijarah_Purchase_Order) {
        Ijarah_Purchase_Order = ijarah_Purchase_Order;
    }

    public ReqDocument getMMQ_Akad_MMQ() {
        return MMQ_Akad_MMQ;
    }

    public void setMMQ_Akad_MMQ(ReqDocument MMQ_Akad_MMQ) {
        this.MMQ_Akad_MMQ = MMQ_Akad_MMQ;
    }

    public ReqDocument getMMQ_Lampiran_Jadwal_Pengambil_Alihan() {
        return MMQ_Lampiran_Jadwal_Pengambil_Alihan;
    }

    public void setMMQ_Lampiran_Jadwal_Pengambil_Alihan(ReqDocument MMQ_Lampiran_Jadwal_Pengambil_Alihan) {
        this.MMQ_Lampiran_Jadwal_Pengambil_Alihan = MMQ_Lampiran_Jadwal_Pengambil_Alihan;
    }

    public ReqDocument getMMQ_Laporan_Penilaian_Aset() {
        return MMQ_Laporan_Penilaian_Aset;
    }

    public void setMMQ_Laporan_Penilaian_Aset(ReqDocument MMQ_Laporan_Penilaian_Aset) {
        this.MMQ_Laporan_Penilaian_Aset = MMQ_Laporan_Penilaian_Aset;
    }

    public ReqDocument getMMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga() {
        return MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga;
    }

    public void setMMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga(ReqDocument MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga) {
        this.MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga = MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga;
    }

    public ReqDocument getMMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri() {
        return MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri;
    }

    public void setMMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri(ReqDocument MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri) {
        this.MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri = MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri;
    }

    public ReqDocument getMurabahah_Akad_Murabahah() {
        return Murabahah_Akad_Murabahah;
    }

    public void setMurabahah_Akad_Murabahah(ReqDocument murabahah_Akad_Murabahah) {
        Murabahah_Akad_Murabahah = murabahah_Akad_Murabahah;
    }

    public ReqDocument getMurabahah_Akad_Wakalah() {
        return Murabahah_Akad_Wakalah;
    }

    public void setMurabahah_Akad_Wakalah(ReqDocument murabahah_Akad_Wakalah) {
        Murabahah_Akad_Wakalah = murabahah_Akad_Wakalah;
    }

    public ReqDocument getMurabahah_Lampiran_Jadwal_Angsuran() {
        return Murabahah_Lampiran_Jadwal_Angsuran;
    }

    public void setMurabahah_Lampiran_Jadwal_Angsuran(ReqDocument murabahah_Lampiran_Jadwal_Angsuran) {
        Murabahah_Lampiran_Jadwal_Angsuran = murabahah_Lampiran_Jadwal_Angsuran;
    }

    public ReqDocument getMurabahah_Purchase_Order() {
        return Murabahah_Purchase_Order;
    }

    public void setMurabahah_Purchase_Order(ReqDocument murabahah_Purchase_Order) {
        Murabahah_Purchase_Order = murabahah_Purchase_Order;
    }

    public ReqDocument getMurabahah_Surat_Tanda_Terima_Barang() {
        return Murabahah_Surat_Tanda_Terima_Barang;
    }

    public void setMurabahah_Surat_Tanda_Terima_Barang(ReqDocument murabahah_Surat_Tanda_Terima_Barang) {
        Murabahah_Surat_Tanda_Terima_Barang = murabahah_Surat_Tanda_Terima_Barang;
    }

    public ReqDocument getRahn_Akad_Rahn() {
        return Rahn_Akad_Rahn;
    }

    public void setRahn_Akad_Rahn(ReqDocument rahn_Akad_Rahn) {
        Rahn_Akad_Rahn = rahn_Akad_Rahn;
    }

    public ReqDocument getRahn_Lampiran_Jadwal_Angsuran() {
        return Rahn_Lampiran_Jadwal_Angsuran;
    }

    public void setRahn_Lampiran_Jadwal_Angsuran(ReqDocument rahn_Lampiran_Jadwal_Angsuran) {
        Rahn_Lampiran_Jadwal_Angsuran = rahn_Lampiran_Jadwal_Angsuran;
    }

    public ReqDocument getSUP() {
        return SUP;
    }

    public void setSUP(ReqDocument SUP) {
        this.SUP = SUP;
    }

    public ReqDocument getSurat_Kuasa_Pernyataan_Flagging() {
        return Surat_Kuasa_Pernyataan_Flagging;
    }

    public void setSurat_Kuasa_Pernyataan_Flagging(ReqDocument surat_Kuasa_Pernyataan_Flagging) {
        Surat_Kuasa_Pernyataan_Flagging = surat_Kuasa_Pernyataan_Flagging;
    }

    public ReqDocument getSurat_Pernyataan_Kuasa_Pembiayaan() {
        return Surat_Pernyataan_Kuasa_Pembiayaan;
    }

    public void setSurat_Pernyataan_Kuasa_Pembiayaan(ReqDocument surat_Pernyataan_Kuasa_Pembiayaan) {
        Surat_Pernyataan_Kuasa_Pembiayaan = surat_Pernyataan_Kuasa_Pembiayaan;
    }

    public ReqDocument getTanda_Terima_Dokumen_SK() {
        return Tanda_Terima_Dokumen_SK;
    }

    public void setTanda_Terima_Dokumen_SK(ReqDocument tanda_Terima_Dokumen_SK) {
        Tanda_Terima_Dokumen_SK = tanda_Terima_Dokumen_SK;
    }

    public List<ReqDocumentUmum> getDokumenUmum() {
        return DokumenUmum;
    }

    public void setDokumenUmum(List<ReqDocumentUmum> dokumenUmum) {
        DokumenUmum = dokumenUmum;
    }
}
