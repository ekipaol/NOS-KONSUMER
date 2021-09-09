package com.application.bris.ikurma_nos_konsumer.model.hotprospek;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by PID on 6/23/2019.
 */

public class KonsumerKmgKelengkapanDokumen extends RealmObject {
    @PrimaryKey
    @SerializedName("ID_APLIKASI")
    public int ID_APLIKASI;
    @SerializedName("FC_KTP")
    public Boolean FC_KTP;
    @SerializedName("ID_DOKUMEN_KTP")
    public Integer ID_DOKUMEN_KTP;
    @SerializedName("FC_KK")
    public Boolean FC_KK;
    @SerializedName("ID_DOKUMEN_KK")
    public Integer ID_DOKUMEN_KK;
    @SerializedName("FC_SURAT_NIKAH")
    public Boolean FC_SURAT_NIKAH;
    @SerializedName("ID_DOKUMEN_SURAT_NIKAH")
    public Integer ID_DOKUMEN_SURAT_NIKAH;
    @SerializedName("PAS_PHOTO")
    public Boolean PAS_PHOTO;
    @SerializedName("ID_DOKUMEN_PAS_PHOTO")
    public Integer ID_DOKUMEN_PAS_PHOTO;
    @SerializedName("FC_NPWP_PRIBADI")
    public Boolean FC_NPWP_PRIBADI;
    @SerializedName("ID_DOKUMEN_NPWP_PRIBADI")
    public Integer ID_DOKUMEN_NPWP_PRIBADI;
    @SerializedName("ID_FC_NPWP_PRIBADI")
    public Integer ID_FC_NPWP_PRIBADI;  //INI MEMANG MIRIP SAMA ATASNYA, INI KHUSUS PURNA
    @SerializedName("APLIKASI_PERMOHONAN")
    public Boolean APLIKASI_PERMOHONAN;
    @SerializedName("ID_DOKUMEN_APLIKASI")
    public Integer ID_DOKUMEN_APLIKASI;
    @SerializedName("SURAT_KETERANGAN_PEGAWAI_TETAP")
    public Boolean SURAT_KETERANGAN_PEGAWAI_TETAP;
    @SerializedName("ID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP")
    public Integer ID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP;
    @SerializedName("ID_SURAT_KETERANGAN_PEGAWAI_TETAP")
    public Integer  ID_SURAT_KETERANGAN_PEGAWAI_TETAP;  //INI MEMANG MIRIP SAMA ATASNYA, INI KHUSUS PURNA
    @SerializedName("SK_JABATAN_TERAKHIR_PNS")
    public Boolean SK_JABATAN_TERAKHIR_PNS;
    @SerializedName("ID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS")
    public Integer ID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS;
    @SerializedName("ID_SK_JABATAN_TERAKHIR_PNS")
    public Integer ID_SK_JABATAN_TERAKHIR_PNS; //INI MEMANG MIRIP SAMA ATASNYA, INI KHUSUS PURNA
    @SerializedName("SURAT_REKOMENDASI")
    public Boolean SURAT_REKOMENDASI;
    @SerializedName("ID_DOKUMEN_SURAT_REKOMENDASI")
    public Integer ID_DOKUMEN_SURAT_REKOMENDASI;
    @SerializedName("SURAT_KUASA_POTONG_GAJI")
    public Boolean SURAT_KUASA_POTONG_GAJI;
    @SerializedName("ID_DOKUMEN_SURAT_KUASA_POTONG_GAJI")
    public Integer ID_DOKUMEN_SURAT_KUASA_POTONG_GAJI;
    @SerializedName("SURAT_KUASA_DEBET_REKENING")
    public Boolean SURAT_KUASA_DEBET_REKENING;
    @SerializedName("ID_DOKUMEN_SURAT_KUASA_DEBET_REKENING")
    public Integer ID_DOKUMEN_SURAT_KUASA_DEBET_REKENING;
    @SerializedName("SLIP_GAJI")
    public Boolean SLIP_GAJI;
    @SerializedName("ID_DOKUMEN_SLIP_GAJI")
    public Integer ID_DOKUMEN_SLIP_GAJI; //INI MEMANG MIRIP SAMA ATASNYA, INI KHUSUS PURNA
    @SerializedName("ID_SLIP_GAJI")
    public Integer ID_SLIP_GAJI;
    @SerializedName("REKENING_KORAN")
    public Boolean REKENING_KORAN;
    @SerializedName("ID_DOKUMEN_REKENING_KORAN")
    public Integer ID_DOKUMEN_REKENING_KORAN;
    @SerializedName("KWITANSI_PEMBELIAN_BARANG_JASA")
    public Boolean KWITANSI_PEMBELIAN_BARANG_JASA;
    @SerializedName("ID_DOKUMEN_KWITANSI_PEMBELIAN_BARANG_JASA")
    public Integer ID_DOKUMEN_KWITANSI_PEMBELIAN_BARANG_JASA;
    @SerializedName("DOKUMEN")
    public Boolean DOKUMEN;
    @SerializedName("ID_DOKUMEN_LAINNYA")
    public Integer ID_DOKUMEN_LAINNYA;
    @SerializedName("SURAT_PERNYATAAN_NASABAH_RPC")
    public Boolean suratNasabahRpc;
    @SerializedName("ID_DOKUMEN_PERNYATAAN_RPC")
    public Integer idPhotosuratNasabahRpc;
    @SerializedName("SPK_MEDICAL")
    public Integer spkMedical;
    @SerializedName("ID_SPK_MEDICAL")
    public Integer idPhotospkMedical;
    @SerializedName("SURAT_PERNYATAAN_KESEHATAN")
    public Boolean suratKesehatan;
    @SerializedName("ID_SURAT_PERNYATAAN_KESEHATAN")
    public Integer idPhotosuratKesehatan;
    @SerializedName("SURAT_KETERANGAN_JANDA_DUDA_PENSIUN")
    public Boolean suratJandaDuda;
    @SerializedName("ID_SURAT_KETERANGAN_JANDA_DUDA_PENSIUN")
    public Integer idPhotosuratJandaDuda;
    @SerializedName("FC_IDENTITAS_PENSIUN")
    public Boolean identitasPensiun;
    @SerializedName("ID_FC_IDENTITAS_PENSIUN")
    public Integer idPhotoidentitasPensiun;
    @SerializedName("SURAT_KETERANGAN_PENSIUN")
    public Boolean suratPensiun;
    @SerializedName("ID_SURAT_KETERANGAN_PENSIUN")
    public Integer idPhotosuratPensiun;
    @SerializedName("buktiPensiun")
    public Boolean buktiPensiun;
    @SerializedName("idPhotoBuktiPensiun")
    public Integer idPhotoBuktiPensiun;



    public KonsumerKmgKelengkapanDokumen(Boolean FC_KTP, Integer ID_DOKUMEN_KTP, Boolean FC_KK, Integer ID_DOKUMEN_KK, Boolean FC_SURAT_NIKAH, Integer ID_DOKUMEN_SURAT_NIKAH, Boolean PAS_PHOTO, Integer ID_DOKUMEN_PAS_PHOTO, Boolean FC_NPWP_PRIBADI, Integer ID_DOKUMEN_NPWP_PRIBADI, Boolean APLIKASI_PERMOHONAN, Integer ID_DOKUMEN_APLIKASI, Boolean SURAT_KETERANGAN_PEGAWAI_TETAP, Integer ID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP, Boolean SK_JABATAN_TERAKHIR_PNS, Integer ID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS, Boolean SURAT_REKOMENDASI, Integer ID_DOKUMEN_SURAT_REKOMENDASI, Boolean SURAT_KUASA_POTONG_GAJI, Integer ID_DOKUMEN_SURAT_KUASA_POTONG_GAJI, Boolean SURAT_KUASA_DEBET_REKENING, Integer ID_DOKUMEN_SURAT_KUASA_DEBET_REKENING, Boolean SLIP_GAJI, Integer ID_DOKUMEN_SLIP_GAJI, Boolean REKENING_KORAN, Integer ID_DOKUMEN_REKENING_KORAN, Boolean KWITANSI_PEMBELIAN_BARANG_JASA, Integer ID_DOKUMEN_KWITANSI_PEMBELIAN_BARANG_JASA, Boolean DOKUMEN, Integer ID_DOKUMEN_LAINNYA) {
        this.FC_KTP = FC_KTP;
        this.ID_DOKUMEN_KTP = ID_DOKUMEN_KTP;
        this.FC_KK = FC_KK;
        this.ID_DOKUMEN_KK = ID_DOKUMEN_KK;
        this.FC_SURAT_NIKAH = FC_SURAT_NIKAH;
        this.ID_DOKUMEN_SURAT_NIKAH = ID_DOKUMEN_SURAT_NIKAH;
        this.PAS_PHOTO = PAS_PHOTO;
        this.ID_DOKUMEN_PAS_PHOTO = ID_DOKUMEN_PAS_PHOTO;
        this.FC_NPWP_PRIBADI = FC_NPWP_PRIBADI;
        this.ID_DOKUMEN_NPWP_PRIBADI = ID_DOKUMEN_NPWP_PRIBADI;
        this.APLIKASI_PERMOHONAN = APLIKASI_PERMOHONAN;
        this.ID_DOKUMEN_APLIKASI = ID_DOKUMEN_APLIKASI;
        this.SURAT_KETERANGAN_PEGAWAI_TETAP = SURAT_KETERANGAN_PEGAWAI_TETAP;
        this.ID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP = ID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP;
        this.SK_JABATAN_TERAKHIR_PNS = SK_JABATAN_TERAKHIR_PNS;
        this.ID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS = ID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS;
        this.SURAT_REKOMENDASI = SURAT_REKOMENDASI;
        this.ID_DOKUMEN_SURAT_REKOMENDASI = ID_DOKUMEN_SURAT_REKOMENDASI;
        this.SURAT_KUASA_POTONG_GAJI = SURAT_KUASA_POTONG_GAJI;
        this.ID_DOKUMEN_SURAT_KUASA_POTONG_GAJI = ID_DOKUMEN_SURAT_KUASA_POTONG_GAJI;
        this.SURAT_KUASA_DEBET_REKENING = SURAT_KUASA_DEBET_REKENING;
        this.ID_DOKUMEN_SURAT_KUASA_DEBET_REKENING = ID_DOKUMEN_SURAT_KUASA_DEBET_REKENING;
        this.SLIP_GAJI = SLIP_GAJI;
        this.ID_DOKUMEN_SLIP_GAJI = ID_DOKUMEN_SLIP_GAJI;
        this.REKENING_KORAN = REKENING_KORAN;
        this.ID_DOKUMEN_REKENING_KORAN = ID_DOKUMEN_REKENING_KORAN;
        this.KWITANSI_PEMBELIAN_BARANG_JASA = KWITANSI_PEMBELIAN_BARANG_JASA;
        this.ID_DOKUMEN_KWITANSI_PEMBELIAN_BARANG_JASA = ID_DOKUMEN_KWITANSI_PEMBELIAN_BARANG_JASA;
        this.DOKUMEN = DOKUMEN;
        this.ID_DOKUMEN_LAINNYA = ID_DOKUMEN_LAINNYA;
    }

    public KonsumerKmgKelengkapanDokumen() {
    }

    public Integer getID_SK_JABATAN_TERAKHIR_PNS() {
        return ID_SK_JABATAN_TERAKHIR_PNS;
    }

    public void setID_SK_JABATAN_TERAKHIR_PNS(Integer ID_SK_JABATAN_TERAKHIR_PNS) {
        this.ID_SK_JABATAN_TERAKHIR_PNS = ID_SK_JABATAN_TERAKHIR_PNS;
    }

    public Integer getID_SURAT_KETERANGAN_PEGAWAI_TETAP() {
        return ID_SURAT_KETERANGAN_PEGAWAI_TETAP;
    }

    public void setID_SURAT_KETERANGAN_PEGAWAI_TETAP(Integer ID_SURAT_KETERANGAN_PEGAWAI_TETAP) {
        this.ID_SURAT_KETERANGAN_PEGAWAI_TETAP = ID_SURAT_KETERANGAN_PEGAWAI_TETAP;
    }

    public Integer getID_FC_NPWP_PRIBADI() {
        return ID_FC_NPWP_PRIBADI;
    }

    public void setID_FC_NPWP_PRIBADI(Integer ID_FC_NPWP_PRIBADI) {
        this.ID_FC_NPWP_PRIBADI = ID_FC_NPWP_PRIBADI;
    }

    public Integer getID_SLIP_GAJI() {
        return ID_SLIP_GAJI;
    }

    public void setID_SLIP_GAJI(Integer ID_SLIP_GAJI) {
        this.ID_SLIP_GAJI = ID_SLIP_GAJI;
    }

    public Boolean getBuktiPensiun() {
        return buktiPensiun;
    }

    public void setBuktiPensiun(Boolean buktiPensiun) {
        this.buktiPensiun = buktiPensiun;
    }

    public Integer getIdPhotoBuktiPensiun() {
        return idPhotoBuktiPensiun;
    }

    public void setIdPhotoBuktiPensiun(Integer idPhotoBuktiPensiun) {
        this.idPhotoBuktiPensiun = idPhotoBuktiPensiun;
    }

    public Boolean getSuratNasabahRpc() {
        return suratNasabahRpc;
    }

    public void setSuratNasabahRpc(Boolean suratNasabahRpc) {
        this.suratNasabahRpc = suratNasabahRpc;
    }

    public Integer getIdPhotosuratNasabahRpc() {
        return idPhotosuratNasabahRpc;
    }

    public void setIdPhotosuratNasabahRpc(Integer idPhotosuratNasabahRpc) {
        this.idPhotosuratNasabahRpc = idPhotosuratNasabahRpc;
    }

    public Integer getSpkMedical() {
        return spkMedical;
    }

    public void setSpkMedical(Integer spkMedical) {
        this.spkMedical = spkMedical;
    }

    public Integer getIdPhotospkMedical() {
        return idPhotospkMedical;
    }

    public void setIdPhotospkMedical(Integer idPhotospkMedical) {
        this.idPhotospkMedical = idPhotospkMedical;
    }

    public Boolean getSuratKesehatan() {
        return suratKesehatan;
    }

    public void setSuratKesehatan(Boolean suratKesehatan) {
        this.suratKesehatan = suratKesehatan;
    }

    public Integer getIdPhotosuratKesehatan() {
        return idPhotosuratKesehatan;
    }

    public void setIdPhotosuratKesehatan(Integer idPhotosuratKesehatan) {
        this.idPhotosuratKesehatan = idPhotosuratKesehatan;
    }

    public Boolean getSuratJandaDuda() {
        return suratJandaDuda;
    }

    public void setSuratJandaDuda(Boolean suratJandaDuda) {
        this.suratJandaDuda = suratJandaDuda;
    }

    public Integer getIdPhotosuratJandaDuda() {
        return idPhotosuratJandaDuda;
    }

    public void setIdPhotosuratJandaDuda(Integer idPhotosuratJandaDuda) {
        this.idPhotosuratJandaDuda = idPhotosuratJandaDuda;
    }

    public Boolean getIdentitasPensiun() {
        return identitasPensiun;
    }

    public void setIdentitasPensiun(Boolean identitasPensiun) {
        this.identitasPensiun = identitasPensiun;
    }

    public Integer getIdPhotoidentitasPensiun() {
        return idPhotoidentitasPensiun;
    }

    public void setIdPhotoidentitasPensiun(Integer idPhotoidentitasPensiun) {
        this.idPhotoidentitasPensiun = idPhotoidentitasPensiun;
    }

    public Boolean getSuratPensiun() {
        return suratPensiun;
    }

    public void setSuratPensiun(Boolean suratPensiun) {
        this.suratPensiun = suratPensiun;
    }

    public Integer getIdPhotosuratPensiun() {
        return idPhotosuratPensiun;
    }

    public void setIdPhotosuratPensiun(Integer idPhotosuratPensiun) {
        this.idPhotosuratPensiun = idPhotosuratPensiun;
    }

    public int getID_APLIKASI() {
        return ID_APLIKASI;
    }

    public void setID_APLIKASI(int ID_APLIKASI) {
        this.ID_APLIKASI = ID_APLIKASI;
    }

    public Boolean getFC_KTP() {
        return FC_KTP;
    }

    public Integer getID_DOKUMEN_KTP() {
        return ID_DOKUMEN_KTP;
    }

    public Boolean getFC_KK() {
        return FC_KK;
    }

    public Integer getID_DOKUMEN_KK() {
        return ID_DOKUMEN_KK;
    }

    public Boolean getFC_SURAT_NIKAH() {
        return FC_SURAT_NIKAH;
    }

    public Integer getID_DOKUMEN_SURAT_NIKAH() {
        return ID_DOKUMEN_SURAT_NIKAH;
    }

    public Boolean getPAS_PHOTO() {
        return PAS_PHOTO;
    }

    public Integer getID_DOKUMEN_PAS_PHOTO() {
        return ID_DOKUMEN_PAS_PHOTO;
    }

    public Boolean getFC_NPWP_PRIBADI() {
        return FC_NPWP_PRIBADI;
    }

    public Integer getID_DOKUMEN_NPWP_PRIBADI() {
        return ID_DOKUMEN_NPWP_PRIBADI;
    }

    public Boolean getAPLIKASI_PERMOHONAN() {
        return APLIKASI_PERMOHONAN;
    }

    public Integer getID_DOKUMEN_APLIKASI() {
        return ID_DOKUMEN_APLIKASI;
    }

    public Boolean getSURAT_KETERANGAN_PEGAWAI_TETAP() {
        return SURAT_KETERANGAN_PEGAWAI_TETAP;
    }

    public Integer getID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP() {
        return ID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP;
    }

    public Boolean getSK_JABATAN_TERAKHIR_PNS() {
        return SK_JABATAN_TERAKHIR_PNS;
    }

    public Integer getID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS() {
        return ID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS;
    }

    public Boolean getSURAT_REKOMENDASI() {
        return SURAT_REKOMENDASI;
    }

    public Integer getID_DOKUMEN_SURAT_REKOMENDASI() {
        return ID_DOKUMEN_SURAT_REKOMENDASI;
    }

    public Boolean getSURAT_KUASA_POTONG_GAJI() {
        return SURAT_KUASA_POTONG_GAJI;
    }

    public Integer getID_DOKUMEN_SURAT_KUASA_POTONG_GAJI() {
        return ID_DOKUMEN_SURAT_KUASA_POTONG_GAJI;
    }

    public Boolean getSURAT_KUASA_DEBET_REKENING() {
        return SURAT_KUASA_DEBET_REKENING;
    }

    public Integer getID_DOKUMEN_SURAT_KUASA_DEBET_REKENING() {
        return ID_DOKUMEN_SURAT_KUASA_DEBET_REKENING;
    }

    public Boolean getSLIP_GAJI() {
        return SLIP_GAJI;
    }

    public Integer getID_DOKUMEN_SLIP_GAJI() {
        return ID_DOKUMEN_SLIP_GAJI;
    }

    public Boolean getREKENING_KORAN() {
        return REKENING_KORAN;
    }

    public Integer getID_DOKUMEN_REKENING_KORAN() {
        return ID_DOKUMEN_REKENING_KORAN;
    }

    public Boolean getKWITANSI_PEMBELIAN_BARANG_JASA() {
        return KWITANSI_PEMBELIAN_BARANG_JASA;
    }

    public Integer getID_DOKUMEN_KWITANSI_PEMBELIAN_BARANG_JASA() {
        return ID_DOKUMEN_KWITANSI_PEMBELIAN_BARANG_JASA;
    }

    public Boolean getDOKUMEN() {
        return DOKUMEN;
    }

    public Integer getID_DOKUMEN_LAINNYA() {
        return ID_DOKUMEN_LAINNYA;
    }

    public void setFC_KTP(Boolean FC_KTP) {
        this.FC_KTP = FC_KTP;
    }

    public void setID_DOKUMEN_KTP(Integer ID_DOKUMEN_KTP) {
        this.ID_DOKUMEN_KTP = ID_DOKUMEN_KTP;
    }

    public void setFC_KK(Boolean FC_KK) {
        this.FC_KK = FC_KK;
    }

    public void setID_DOKUMEN_KK(Integer ID_DOKUMEN_KK) {
        this.ID_DOKUMEN_KK = ID_DOKUMEN_KK;
    }

    public void setFC_SURAT_NIKAH(Boolean FC_SURAT_NIKAH) {
        this.FC_SURAT_NIKAH = FC_SURAT_NIKAH;
    }

    public void setID_DOKUMEN_SURAT_NIKAH(Integer ID_DOKUMEN_SURAT_NIKAH) {
        this.ID_DOKUMEN_SURAT_NIKAH = ID_DOKUMEN_SURAT_NIKAH;
    }

    public void setPAS_PHOTO(Boolean PAS_PHOTO) {
        this.PAS_PHOTO = PAS_PHOTO;
    }

    public void setID_DOKUMEN_PAS_PHOTO(Integer ID_DOKUMEN_PAS_PHOTO) {
        this.ID_DOKUMEN_PAS_PHOTO = ID_DOKUMEN_PAS_PHOTO;
    }

    public void setFC_NPWP_PRIBADI(Boolean FC_NPWP_PRIBADI) {
        this.FC_NPWP_PRIBADI = FC_NPWP_PRIBADI;
    }

    public void setID_DOKUMEN_NPWP_PRIBADI(Integer ID_DOKUMEN_NPWP_PRIBADI) {
        this.ID_DOKUMEN_NPWP_PRIBADI = ID_DOKUMEN_NPWP_PRIBADI;
    }

    public void setAPLIKASI_PERMOHONAN(Boolean APLIKASI_PERMOHONAN) {
        this.APLIKASI_PERMOHONAN = APLIKASI_PERMOHONAN;
    }

    public void setID_DOKUMEN_APLIKASI(Integer ID_DOKUMEN_APLIKASI) {
        this.ID_DOKUMEN_APLIKASI = ID_DOKUMEN_APLIKASI;
    }

    public void setSURAT_KETERANGAN_PEGAWAI_TETAP(Boolean SURAT_KETERANGAN_PEGAWAI_TETAP) {
        this.SURAT_KETERANGAN_PEGAWAI_TETAP = SURAT_KETERANGAN_PEGAWAI_TETAP;
    }

    public void setID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP(Integer ID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP) {
        this.ID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP = ID_DOKUMEN_SURAT_KETERANGAN_PEGAWAI_TETAP;
    }

    public void setSK_JABATAN_TERAKHIR_PNS(Boolean SK_JABATAN_TERAKHIR_PNS) {
        this.SK_JABATAN_TERAKHIR_PNS = SK_JABATAN_TERAKHIR_PNS;
    }

    public void setID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS(Integer ID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS) {
        this.ID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS = ID_DOKUMEN_SK_JABATAN_TERAKHIR_PNS;
    }

    public void setSURAT_REKOMENDASI(Boolean SURAT_REKOMENDASI) {
        this.SURAT_REKOMENDASI = SURAT_REKOMENDASI;
    }

    public void setID_DOKUMEN_SURAT_REKOMENDASI(Integer ID_DOKUMEN_SURAT_REKOMENDASI) {
        this.ID_DOKUMEN_SURAT_REKOMENDASI = ID_DOKUMEN_SURAT_REKOMENDASI;
    }

    public void setSURAT_KUASA_POTONG_GAJI(Boolean SURAT_KUASA_POTONG_GAJI) {
        this.SURAT_KUASA_POTONG_GAJI = SURAT_KUASA_POTONG_GAJI;
    }

    public void setID_DOKUMEN_SURAT_KUASA_POTONG_GAJI(Integer ID_DOKUMEN_SURAT_KUASA_POTONG_GAJI) {
        this.ID_DOKUMEN_SURAT_KUASA_POTONG_GAJI = ID_DOKUMEN_SURAT_KUASA_POTONG_GAJI;
    }

    public void setSURAT_KUASA_DEBET_REKENING(Boolean SURAT_KUASA_DEBET_REKENING) {
        this.SURAT_KUASA_DEBET_REKENING = SURAT_KUASA_DEBET_REKENING;
    }

    public void setID_DOKUMEN_SURAT_KUASA_DEBET_REKENING(Integer ID_DOKUMEN_SURAT_KUASA_DEBET_REKENING) {
        this.ID_DOKUMEN_SURAT_KUASA_DEBET_REKENING = ID_DOKUMEN_SURAT_KUASA_DEBET_REKENING;
    }

    public void setSLIP_GAJI(Boolean SLIP_GAJI) {
        this.SLIP_GAJI = SLIP_GAJI;
    }

    public void setID_DOKUMEN_SLIP_GAJI(Integer ID_DOKUMEN_SLIP_GAJI) {
        this.ID_DOKUMEN_SLIP_GAJI = ID_DOKUMEN_SLIP_GAJI;
    }

    public void setREKENING_KORAN(Boolean REKENING_KORAN) {
        this.REKENING_KORAN = REKENING_KORAN;
    }

    public void setID_DOKUMEN_REKENING_KORAN(Integer ID_DOKUMEN_REKENING_KORAN) {
        this.ID_DOKUMEN_REKENING_KORAN = ID_DOKUMEN_REKENING_KORAN;
    }

    public void setKWITANSI_PEMBELIAN_BARANG_JASA(Boolean KWITANSI_PEMBELIAN_BARANG_JASA) {
        this.KWITANSI_PEMBELIAN_BARANG_JASA = KWITANSI_PEMBELIAN_BARANG_JASA;
    }

    public void setID_DOKUMEN_KWITANSI_PEMBELIAN_BARANG_JASA(Integer ID_DOKUMEN_KWITANSI_PEMBELIAN_BARANG_JASA) {
        this.ID_DOKUMEN_KWITANSI_PEMBELIAN_BARANG_JASA = ID_DOKUMEN_KWITANSI_PEMBELIAN_BARANG_JASA;
    }

    public void setDOKUMEN(Boolean DOKUMEN) {
        this.DOKUMEN = DOKUMEN;
    }

    public void setID_DOKUMEN_LAINNYA(Integer ID_DOKUMEN_LAINNYA) {
        this.ID_DOKUMEN_LAINNYA = ID_DOKUMEN_LAINNYA;
    }
}
