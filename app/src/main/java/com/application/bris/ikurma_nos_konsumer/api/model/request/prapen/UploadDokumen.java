package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UploadDokumen {

    @SerializedName("Form_Mutas_Kantor_Bayar")
    @Expose
    private ReqDocument formMutasKantorBayar;
    @SerializedName("Form_Mutasi_Kantor_Bayar_Taspen")
    @Expose
    private ReqDocument formMutasiKantorBayarTaspen;
    @SerializedName("Foto_Bukti_Otentikasi_Nasabah")
    @Expose
    private ReqDocument fotoBuktiOtentikasiNasabah;
    @SerializedName("Foto_Covernote_Asuransi")
    @Expose
    private ReqDocument fotoCovernoteAsuransi;
    @SerializedName("Foto_Proses_Penandatanganan_Akad")
    @Expose
    private ReqDocument fotoProsesPenandatangananAkad;
    @SerializedName("Foto_SK_Pengangkatan")
    @Expose
    private ReqDocument fotoSKPengangkatan;
    @SerializedName("Foto_SK_Pensiun")
    @Expose
    private ReqDocument fotoSKPensiun;
    @SerializedName("Foto_SK_Terakhir")
    @Expose
    private ReqDocument fotoSKTerakhir;
    @SerializedName("Ijarah_Akad_Ijarah")
    @Expose
    private ReqDocument ijarahAkadIjarah;
    @SerializedName("Ijarah_Akad_Wakalah")
    @Expose
    private ReqDocument ijarahAkadWakalah;
    @SerializedName("Ijarah_Lampiran_Jadwal_Angsuran")
    @Expose
    private ReqDocument ijarahLampiranJadwalAngsuran;
    @SerializedName("Ijarah_Purchase_Order")
    @Expose
    private ReqDocument ijarahPurchaseOrder;
    @SerializedName("MMQ_Akad_MMQ")
    @Expose
    private ReqDocument mMQAkadMMQ;
    @SerializedName("MMQ_Lampiran_Jadwal_Pengambil_Alihan")
    @Expose
    private ReqDocument mMQLampiranJadwalPengambilAlihan;
    @SerializedName("MMQ_Laporan_Penilaian_Aset")
    @Expose
    private ReqDocument mMQLaporanPenilaianAset;
    @SerializedName("MMQ_Surat_Pernyataan_Kuasa_Aset_Ketiga")
    @Expose
    private ReqDocument mMQSuratPernyataanKuasaAsetKetiga;
    @SerializedName("MMQ_Surat_Pernyataan_Kuasa_Aset_Sendiri")
    @Expose
    private ReqDocument mMQSuratPernyataanKuasaAsetSendiri;
    @SerializedName("Murabahah_Akad_Murabahah")
    @Expose
    private ReqDocument murabahahAkadMurabahah;
    @SerializedName("Murabahah_Akad_Wakalah")
    @Expose
    private ReqDocument murabahahAkadWakalah;
    @SerializedName("Murabahah_Lampiran_Jadwal_Angsuran")
    @Expose
    private ReqDocument murabahahLampiranJadwalAngsuran;
    @SerializedName("Murabahah_Purchase_Order")
    @Expose
    private ReqDocument murabahahPurchaseOrder;
    @SerializedName("Murabahah_Surat_Tanda_Terima_Barang")
    @Expose
    private ReqDocument murabahahSuratTandaTerimaBarang;
    @SerializedName("Rahn_Akad_Rahn")
    @Expose
    private ReqDocument rahnAkadRahn;
    @SerializedName("Rahn_Lampiran_Jadwal_Angsuran")
    @Expose
    private ReqDocument rahnLampiranJadwalAngsuran;
    @SerializedName("SUP")
    @Expose
    private ReqDocument sup;
    @SerializedName("Surat_Kuasa_Pernyataan_Flagging")
    @Expose
    private ReqDocument suratKuasaPernyataanFlagging;
    @SerializedName("Surat_Pernyataan_Kuasa_Pembiayaan")
    @Expose
    private ReqDocument suratPernyataanKuasaPembiayaan;
    @SerializedName("Tanda_Terima_Dokumen_SK")
    @Expose
    private ReqDocument tandaTerimaDokumenSK;

    @SerializedName("DokumenUmum")
    @Expose
    private List<ReqDocumentUmum> DokumenUmum;

    public void setFormMutasKantorBayar(ReqDocument formMutasKantorBayar) {
        this.formMutasKantorBayar = formMutasKantorBayar;
    }

    public void setFormMutasiKantorBayarTaspen(ReqDocument formMutasiKantorBayarTaspen) {
        this.formMutasiKantorBayarTaspen = formMutasiKantorBayarTaspen;
    }

    public void setFotoBuktiOtentikasiNasabah(ReqDocument fotoBuktiOtentikasiNasabah) {
        this.fotoBuktiOtentikasiNasabah = fotoBuktiOtentikasiNasabah;
    }

    public void setFotoCovernoteAsuransi(ReqDocument fotoCovernoteAsuransi) {
        this.fotoCovernoteAsuransi = fotoCovernoteAsuransi;
    }

    public void setFotoProsesPenandatangananAkad(ReqDocument fotoProsesPenandatangananAkad) {
        this.fotoProsesPenandatangananAkad = fotoProsesPenandatangananAkad;
    }

    public void setFotoSKPengangkatan(ReqDocument fotoSKPengangkatan) {
        this.fotoSKPengangkatan = fotoSKPengangkatan;
    }

    public void setFotoSKPensiun(ReqDocument fotoSKPensiun) {
        this.fotoSKPensiun = fotoSKPensiun;
    }

    public void setFotoSKTerakhir(ReqDocument fotoSKTerakhir) {
        this.fotoSKTerakhir = fotoSKTerakhir;
    }

    public void setIjarahAkadIjarah(ReqDocument ijarahAkadIjarah) {
        this.ijarahAkadIjarah = ijarahAkadIjarah;
    }

    public void setIjarahAkadWakalah(ReqDocument ijarahAkadWakalah) {
        this.ijarahAkadWakalah = ijarahAkadWakalah;
    }

    public void setIjarahLampiranJadwalAngsuran(ReqDocument ijarahLampiranJadwalAngsuran) {
        this.ijarahLampiranJadwalAngsuran = ijarahLampiranJadwalAngsuran;
    }

    public void setIjarahPurchaseOrder(ReqDocument ijarahPurchaseOrder) {
        this.ijarahPurchaseOrder = ijarahPurchaseOrder;
    }

    public void setmMQAkadMMQ(ReqDocument mMQAkadMMQ) {
        this.mMQAkadMMQ = mMQAkadMMQ;
    }

    public void setmMQLampiranJadwalPengambilAlihan(ReqDocument mMQLampiranJadwalPengambilAlihan) {
        this.mMQLampiranJadwalPengambilAlihan = mMQLampiranJadwalPengambilAlihan;
    }

    public void setmMQLaporanPenilaianAset(ReqDocument mMQLaporanPenilaianAset) {
        this.mMQLaporanPenilaianAset = mMQLaporanPenilaianAset;
    }

    public void setmMQSuratPernyataanKuasaAsetKetiga(ReqDocument mMQSuratPernyataanKuasaAsetKetiga) {
        this.mMQSuratPernyataanKuasaAsetKetiga = mMQSuratPernyataanKuasaAsetKetiga;
    }

    public void setmMQSuratPernyataanKuasaAsetSendiri(ReqDocument mMQSuratPernyataanKuasaAsetSendiri) {
        this.mMQSuratPernyataanKuasaAsetSendiri = mMQSuratPernyataanKuasaAsetSendiri;
    }

    public void setMurabahahAkadMurabahah(ReqDocument murabahahAkadMurabahah) {
        this.murabahahAkadMurabahah = murabahahAkadMurabahah;
    }

    public void setMurabahahAkadWakalah(ReqDocument murabahahAkadWakalah) {
        this.murabahahAkadWakalah = murabahahAkadWakalah;
    }

    public void setMurabahahLampiranJadwalAngsuran(ReqDocument murabahahLampiranJadwalAngsuran) {
        this.murabahahLampiranJadwalAngsuran = murabahahLampiranJadwalAngsuran;
    }

    public void setMurabahahPurchaseOrder(ReqDocument murabahahPurchaseOrder) {
        this.murabahahPurchaseOrder = murabahahPurchaseOrder;
    }

    public void setMurabahahSuratTandaTerimaBarang(ReqDocument murabahahSuratTandaTerimaBarang) {
        this.murabahahSuratTandaTerimaBarang = murabahahSuratTandaTerimaBarang;
    }

    public void setRahnAkadRahn(ReqDocument rahnAkadRahn) {
        this.rahnAkadRahn = rahnAkadRahn;
    }

    public void setRahnLampiranJadwalAngsuran(ReqDocument rahnLampiranJadwalAngsuran) {
        this.rahnLampiranJadwalAngsuran = rahnLampiranJadwalAngsuran;
    }

    public void setSup(ReqDocument sup) {
        this.sup = sup;
    }

    public void setSuratKuasaPernyataanFlagging(ReqDocument suratKuasaPernyataanFlagging) {
        this.suratKuasaPernyataanFlagging = suratKuasaPernyataanFlagging;
    }

    public void setSuratPernyataanKuasaPembiayaan(ReqDocument suratPernyataanKuasaPembiayaan) {
        this.suratPernyataanKuasaPembiayaan = suratPernyataanKuasaPembiayaan;
    }

    public void setTandaTerimaDokumenSK(ReqDocument tandaTerimaDokumenSK) {
        this.tandaTerimaDokumenSK = tandaTerimaDokumenSK;
    }

    public ReqDocument getFormMutasKantorBayar() {
        return formMutasKantorBayar;
    }

    public ReqDocument getFormMutasiKantorBayarTaspen() {
        return formMutasiKantorBayarTaspen;
    }

    public ReqDocument getFotoBuktiOtentikasiNasabah() {
        return fotoBuktiOtentikasiNasabah;
    }

    public ReqDocument getFotoCovernoteAsuransi() {
        return fotoCovernoteAsuransi;
    }

    public ReqDocument getFotoProsesPenandatangananAkad() {
        return fotoProsesPenandatangananAkad;
    }

    public ReqDocument getFotoSKPengangkatan() {
        return fotoSKPengangkatan;
    }

    public ReqDocument getFotoSKPensiun() {
        return fotoSKPensiun;
    }

    public ReqDocument getFotoSKTerakhir() {
        return fotoSKTerakhir;
    }

    public ReqDocument getIjarahAkadIjarah() {
        return ijarahAkadIjarah;
    }

    public ReqDocument getIjarahAkadWakalah() {
        return ijarahAkadWakalah;
    }

    public ReqDocument getIjarahLampiranJadwalAngsuran() {
        return ijarahLampiranJadwalAngsuran;
    }

    public ReqDocument getIjarahPurchaseOrder() {
        return ijarahPurchaseOrder;
    }

    public ReqDocument getmMQAkadMMQ() {
        return mMQAkadMMQ;
    }

    public ReqDocument getmMQLampiranJadwalPengambilAlihan() {
        return mMQLampiranJadwalPengambilAlihan;
    }

    public ReqDocument getmMQLaporanPenilaianAset() {
        return mMQLaporanPenilaianAset;
    }

    public ReqDocument getmMQSuratPernyataanKuasaAsetKetiga() {
        return mMQSuratPernyataanKuasaAsetKetiga;
    }

    public ReqDocument getmMQSuratPernyataanKuasaAsetSendiri() {
        return mMQSuratPernyataanKuasaAsetSendiri;
    }

    public ReqDocument getMurabahahAkadMurabahah() {
        return murabahahAkadMurabahah;
    }

    public ReqDocument getMurabahahAkadWakalah() {
        return murabahahAkadWakalah;
    }

    public ReqDocument getMurabahahLampiranJadwalAngsuran() {
        return murabahahLampiranJadwalAngsuran;
    }

    public ReqDocument getMurabahahPurchaseOrder() {
        return murabahahPurchaseOrder;
    }

    public ReqDocument getMurabahahSuratTandaTerimaBarang() {
        return murabahahSuratTandaTerimaBarang;
    }

    public ReqDocument getRahnAkadRahn() {
        return rahnAkadRahn;
    }

    public ReqDocument getRahnLampiranJadwalAngsuran() {
        return rahnLampiranJadwalAngsuran;
    }

    public ReqDocument getSup() {
        return sup;
    }

    public ReqDocument getSuratKuasaPernyataanFlagging() {
        return suratKuasaPernyataanFlagging;
    }

    public ReqDocument getSuratPernyataanKuasaPembiayaan() {
        return suratPernyataanKuasaPembiayaan;
    }

    public ReqDocument getTandaTerimaDokumenSK() {
        return tandaTerimaDokumenSK;
    }

    public List<ReqDocumentUmum> getDokumenUmum() {
        return DokumenUmum;
    }

    public void setDokumenUmum(List<ReqDocumentUmum> dokumenUmum) {
        DokumenUmum = dokumenUmum;
    }
}
