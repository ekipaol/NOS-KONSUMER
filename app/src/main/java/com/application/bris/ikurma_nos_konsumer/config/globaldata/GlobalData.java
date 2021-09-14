package com.application.bris.ikurma_nos_konsumer.config.globaldata;

import android.content.Context;

import com.application.bris.ikurma_nos_konsumer.page_aom.model.address;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;

import java.util.List;

/**
 * Created by PID on 28/04/2019.
 */

public class GlobalData {

    public static String MENU_ID = "idMenu";
    public static String MENU_ROOT = "rootMenu";


    //PRAPEN NOS
    public static void yaTidak(Context context, List<keyvalue> data){
        data.add(new keyvalue("Ya", "Ya"));
        data.add(new keyvalue("Tidak", "Tidak"));

    }
    public static void sumberAplikasi(Context context, List<keyvalue> data){
        data.add(new keyvalue("Pegawai Bank/ Reguler", "Pegawai Bank/ Reguler"));
        data.add(new keyvalue("Mitra Fronting", "Mitra Fronting"));

    }




    //KONSUMER

    public static void referensi(Context context, List<keyvalue> data){
        data.add(new keyvalue("Lain-Lain", "1"));
        data.add(new keyvalue("Nasabah", "2"));
        data.add(new keyvalue("Pihak Terkait Bank", "3"));
        data.add(new keyvalue("Prime Customer Bank", "4"));
    }

    public static void pembayaranGajiMelalui(Context context, List<keyvalue> data){
        data.add(new keyvalue("BRI", "A"));
        data.add(new keyvalue("BRI Syariah", "B"));
        data.add(new keyvalue("Lainnya", "C"));
    }


    public static void posisiJabatan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Staff / Gol < 4A / sd Kapten atau Setaranya", "01"));
        data.add(new keyvalue("Middle Management (Dua/Tiga level dibawah Direktur) / Gol >= 4A >= Mayor atau Setaranya", "02"));
        data.add(new keyvalue("Top Management (Direktur, Komisaris & Satu Level Dibawah Direktur", "03"));
    }

    public static void jenisTiering(Context context, List<keyvalue> data){
        data.add(new keyvalue("Tiering Normal 5 Tahun", "222"));
        data.add(new keyvalue("Tiering Normal 10 Tahun", "222"));
        data.add(new keyvalue("Tiering Normal 15 Tahun", "222"));
    }

    public static void referalDariKpr(Context context, List<keyvalue> data){
        data.add(new keyvalue("KLS", "KLS"));
        data.add(new keyvalue("REGULER", "REGULER"));
    }

    public static void rekomendasiAppraisal(Context context, List<keyvalue> data){
        data.add(new keyvalue("Tidak Direkomendasikan", "1"));
        data.add(new keyvalue("Tidak Marketable", "2"));
        data.add(new keyvalue("Cukup Marketable", "3"));
        data.add(new keyvalue("Marketable", "4"));
    }

    public static void paketKpr(Context context, List<keyvalue> data){
        data.add(new keyvalue("Non Paket", "Non Paket"));
    }
    public static void programKpr(Context context, List<keyvalue> data){
        data.add(new keyvalue("KPR Murabahah Reguler", "222"));
        data.add(new keyvalue("KPR MRBH Non Fixed Income s/d 5th", "236"));
        data.add(new keyvalue("KPR MRBH Non Fixed Income s/d 10th", "237"));
        data.add(new keyvalue("KPR FLPP Rumah Tapak Program DP 0%", "239"));
    }
    public static void pihakKetiga(Context context, List<keyvalue> data){
        //ini valuenya masih perlu penyesuaian
        data.add(new keyvalue("KPR Ready Stock Sertifikat Pecah dari Non Developer KJS/Rumah Bekas/Take Over/Pembangunan/Renovasi", "222"));
        data.add(new keyvalue("Anugrah Agung Sentosa, PT", "236"));
        data.add(new keyvalue("Atlantis Nusa Persada, PT", "237"));
        data.add(new keyvalue("Budi Griya Murni, PT", "239"));
        data.add(new keyvalue("Bumi Casa Manggala Utama, PT", "222"));
        data.add(new keyvalue("Cahaya Sukses Gemilang, PT", "236"));
        data.add(new keyvalue("Defris Sartika Ariani, PT", "237"));
        data.add(new keyvalue("Delta Pinangmas, PT", "239"));
        data.add(new keyvalue("Dinamika Alam Sejahtera, PT", "239"));
        data.add(new keyvalue("Dwikarsa Semestaguna, PT", "239"));
        data.add(new keyvalue("Elang Pinangmas, PT", "239"));
        data.add(new keyvalue("Griya Nusa Agung, PT", "239"));
        data.add(new keyvalue("H. Tatang Rahmat, Perorangan", "239"));
        data.add(new keyvalue("Delta Pinangmas, PT", "239"));
        data.add(new keyvalue("Hikmah Agung Sejahtera, PT", "239"));
        data.add(new keyvalue("Karsatama Bumisejahtera, PT", "239"));
        data.add(new keyvalue("Mekar Agung Sejahtera, PT", "239"));
        data.add(new keyvalue("Puncak Mahameru, PT", "239"));
        data.add(new keyvalue("Retno, Perorangan", "239"));
        data.add(new keyvalue("Satria Riandi, PT", "239"));
        data.add(new keyvalue("Star Firdy Gemilang, PT", "239"));
        data.add(new keyvalue("Summit Jaya Abadi, PT", "239"));
        data.add(new keyvalue("Tasnim Sabikun Hasan, PT", "239"));
        data.add(new keyvalue("Trimitra Prawara Goldland, PT", "239"));
        data.add(new keyvalue("Watudipta Adhi Graha, PT", "239"));

    }

    public static void tujuanPenggunaanKpr(Context context, List<keyvalue> data){
        //ini valuenya masih perlu penyesuaian
        data.add(new keyvalue("Alih Pembiayaan Rumah (Take Over)", "222"));
        data.add(new keyvalue("Pembelian Apartemen Baru Ready Stok dari Developer Kerjasama", "236"));
        data.add(new keyvalue("Pembelian Apartemen Baru Ready Stok dari Developer Non Kerjasama (Sertifikat Splitzing)", "236"));
        data.add(new keyvalue("Pembelian Apartemen Bekas (Second)", "236"));
        data.add(new keyvalue("Pembelian Bahan-Bahan Material untuk Pembangunan Rumah (Tanah Wajib Sudah Bersertifikat dan Sudah Dimiliki Pemohon serta IMB Tersedia)", "236"));
        data.add(new keyvalue("Pembelian Bahan-Bahan Material untuk Renovasi Rumah", "236"));
        data.add(new keyvalue("Pembelian Ruko Baru Ready Stok dari Developer Kerjasama", "236"));
        data.add(new keyvalue("Pembelian Ruko Baru Ready Stok dari Developer Non Kerjasama (Sertifikat dan IMB Pecah Perkavling)", "236"));
        data.add(new keyvalue("Pembelian Rumah Baru Indent dari Developer Kerjasama", "236"));
        data.add(new keyvalue("Pembelian Rumah Baru Ready Stock dari Developer Kerjasama", "236"));
        data.add(new keyvalue("Pembelian Rumah Baru Indent dari Developer Kerjasama", "236"));
        data.add(new keyvalue("Pembelian Rumah Baru Ready Stok dari Developer Non Kerjasama (Sertifikat dan IMB Pecah Perkavling)", "236"));
        data.add(new keyvalue("Pembelian Rumah Bekas (Second)", "236"));
        data.add(new keyvalue("Pembelian Tanah Kavling dengan Luas <=2.500 m2 di Dalam Kompleks Perumahan", "236"));
        data.add(new keyvalue("Pembelian Tanah Kavling dengan Luas <=2.500 m2 di Luar Kompleks Perumahan", "236"));



    }

    public static void jenisPekerjaan(Context context, List<keyvalue> data){
        //ini valuenya masih perlu penyesuaian
        data.add(new keyvalue("Karyawan/PNS", "222"));
        data.add(new keyvalue("Karyawan/PNS Program FLPP", "222"));
        data.add(new keyvalue("Karyawan/PNS Program EMBP", "222"));
        data.add(new keyvalue("Karyawan BRIS", "222"));
        data.add(new keyvalue("Wiraswasta", "222"));
        data.add(new keyvalue("Profesional", "222"));
        data.add(new keyvalue("Karyawan/PNS + Wiraswasta", "222"));
        data.add(new keyvalue("Karyawan/PNS + Profesional", "222"));
        data.add(new keyvalue("Profesional + Wiraswasta", "222"));


    }

    public static void statusKepegawaian(Context context, List<keyvalue> data){
        //ini valuenya masih perlu penyesuaian
        data.add(new keyvalue("Pegawai Tetap", "1"));
        data.add(new keyvalue("Kontrak", "2"));
        data.add(new keyvalue("Honorer", "3"));
        data.add(new keyvalue("Lainnya", "4"));



    }

    //KONSUMER DATA NASABAH
    public static void kepemilikanTempatTinggal(Context context, List<keyvalue> data){

        data.add(new keyvalue("Milik Sendiri", "222"));
        data.add(new keyvalue("Milik Sendiri dan Masih Diangsur", "222"));
        data.add(new keyvalue("Sewa / Kontrak", "222"));
        data.add(new keyvalue("Lain-Lain", "222"));



    }

    public static void noTelpFixedLine(Context context, List<keyvalue> data){

        data.add(new keyvalue("Ya", "222"));
        data.add(new keyvalue("Tidak", "222"));

    }

    //KONSUMER FORM PRESCORING

    public static void jenisPreScoring(Context context, List<keyvalue> data){

        data.add(new keyvalue("PRESCORING PT", "PRESCORING PT"));
        data.add(new keyvalue("PRESCORING CV", "PRESCORING CV"));
        data.add(new keyvalue("PRESCORING YAYASAN", "PRESCORING YAYASAN"));
        data.add(new keyvalue("PRESCORING LAINNYA", "PRESCORING LAINNYA"));

    }


    public static void statusPegawai(Context context, List<keyvalue> data){

        data.add(new keyvalue("Pegawai Tetap", "222"));
        data.add(new keyvalue("Pegawai Tidak Tetap", "222"));

    }

    public static void kepemilikanKartuKredit(Context context, List<keyvalue> data){

        data.add(new keyvalue("Platinum", "222"));
        data.add(new keyvalue("Gold", "222"));
        data.add(new keyvalue("Silver", "222"));
        data.add(new keyvalue("Tidak Ada", "222"));

    }

    public static void idNamePegawai(Context context, List<keyvalue> data){

        data.add(new keyvalue("Ada", "222"));
        data.add(new keyvalue("Tidak Ada", "222"));

    }

    public static void slipGaji(Context context, List<keyvalue> data){

        data.add(new keyvalue("Carbonized/HRIS", "222"));
        data.add(new keyvalue("Non Carbonized/Non HRIS dan Terdapat Dokumen Pendukung (contoh:SPT Pph 21", "222"));
        data.add(new keyvalue("Non Carbonized/Non HRIS dan Tanpa Dokumen Pendukung", "222"));
    }

    public static void rekeningGaji(Context context, List<keyvalue> data){

        data.add(new keyvalue("Tercermin di Tabungan", "222"));
        data.add(new keyvalue("Tidak Tercermin di Tabungan tetapi Terdapat Dokumen Pendukung", "222"));
        data.add(new keyvalue("Tidak Tercermin di Tabungan dan Tanpa Dokumen Pendukung", "222"));

    }

    public static void kebenaranInformasiTempatBekerja(Context context, List<keyvalue> data){

        data.add(new keyvalue("Calon Nasabah Benar Bekerja di Kantor/Perusahaan Dimaksud", "222"));
        data.add(new keyvalue("Calon Nasabah Tidak Bekerja di Kantor/Perusahaan Dimaksud", "222"));

    }

    public static void operasionalKantor(Context context, List<keyvalue> data){

        data.add(new keyvalue("Beroperasi", "222"));
        data.add(new keyvalue("Tidak Beroperasi", "222"));

    }

    public static void prospekUsahaKonsumer(Context context, List<keyvalue> data){

        data.add(new keyvalue("Untung", "222"));
        data.add(new keyvalue("Rugi", "222"));

    }

    public static void lamaBeroperasiKantor(Context context, List<keyvalue> data){

        data.add(new keyvalue("Lebih Besar atau Sama Dengan 5 Tahun", "222"));
        data.add(new keyvalue("Kurang dari 5 Tahun", "222"));

    }

    public static void aksesLokasiKantor(Context context, List<keyvalue> data){

        data.add(new keyvalue("Dapat Dilalui 2 Mobil Atau Lebih", "222"));
        data.add(new keyvalue("Dapat Dilalui 1 Mobil", "222"));
        data.add(new keyvalue("Tidak Dapat Dilalui Mobil", "222"));
    }

    public static void jenisKantor(Context context, List<keyvalue> data){

        data.add(new keyvalue("Gedung Perkantoran", "222"));
        data.add(new keyvalue("Ruko/Rukan", "222"));
        data.add(new keyvalue("Rumah", "222"));
    }

    public static void papanNamaKantor(Context context, List<keyvalue> data){

        data.add(new keyvalue("Permanen", "222"));
        data.add(new keyvalue("Semi ", "222"));
        data.add(new keyvalue("Rumah", "222"));
    }

    public static void jumlahKaryawan(Context context, List<keyvalue> data){

        data.add(new keyvalue("Lebih Dari 100", "222"));
        data.add(new keyvalue("50-100 ", "222"));
        data.add(new keyvalue("Kurang dari 50", "222"));
    }

    public static void kepemilikanBadanUsaha(Context context, List<keyvalue> data){

        data.add(new keyvalue("Memiliki Badan Usaha", "222"));
        data.add(new keyvalue("Tidak Memiliki Badan Usaha ", "222"));
    }

    public static void keteranganDomisiliYayasan(Context context, List<keyvalue> data){

        data.add(new keyvalue("Berdomisili dan Terdaftar di Indonesia", "222"));
        data.add(new keyvalue("Tidak Berdomisili dan Terdaftar di Indonesia ", "222"));
        data.add(new keyvalue("Berdomisili dan Tidak Terdaftar di Indonesia", "222"));
        data.add(new keyvalue("Tidak Berdomisili dan Tidak Terdaftar di Indonesia ", "222"));
        data.add(new keyvalue("Nihil", "222"));

    }

    public static void lamaBerdiriYayasan(Context context, List<keyvalue> data){

        data.add(new keyvalue("Lebih Dari Atau Sama Dengan 5 Tahun", "222"));
        data.add(new keyvalue("Kurang dari 5 Tahun ", "222"));
        data.add(new keyvalue("Nihil", "222"));

    }

    public static void totalPenyertaanYayasan(Context context, List<keyvalue> data){

        data.add(new keyvalue("Lebih Dari 15%", "222"));
        data.add(new keyvalue("Kurang Dari 15%", "222"));
        data.add(new keyvalue("Nihil", "222"));

    }

    public static void validitasTempatTinggal(Context context, List<keyvalue> data){

        data.add(new keyvalue("Sesuai dengan data aplikasi", "Y"));
        data.add(new keyvalue("Tidak sesuai dengan data aplikasi", "T"));

    }

    public static void jumlahPenyertaanYayasan(Context context, List<keyvalue> data){

        data.add(new keyvalue("Lebih Dari 50%", "222"));
        data.add(new keyvalue("Kurang Dari 50%", "222"));
        data.add(new keyvalue("Nihil", "222"));

    }

    public static void kesesuaianPendirianBadanUsaha(Context context, List<keyvalue> data){

        data.add(new keyvalue("Sesuai dengan Maksud dan Tujuan Yayasan", "222"));
        data.add(new keyvalue("Tidak  Sesuai dengan Maksud dan Tujuan Yayasan", "222"));
        data.add(new keyvalue("Nihil", "222"));

    }

    public static void kesesuaianBadanUsahaDenganBris(Context context, List<keyvalue> data){

        data.add(new keyvalue("Sesuai dengan Ketentuan", "222"));
        data.add(new keyvalue("Tidak Sesuai Dengan Ketentuan", "222"));
        data.add(new keyvalue("Nihil", "222"));

    }

    public static void strukturManajemen(Context context, List<keyvalue> data){

        data.add(new keyvalue("Dewan Direksi dan Komisaris Tidak Dirangkap oleh Dewan Pembinan dan Dewan Pengurus", "222"));
        data.add(new keyvalue("Dewan Direksi dan Komisaris Dirangkap oleh Dewan Pembinan dan Dewan Pengurus", "222"));
        data.add(new keyvalue("Nihil", "222"));

    }

    public static void tujuanPenggunaanKmg(Context context, List<keyvalue> data){

        data.add(new keyvalue("Pembelian Kendaraan Bermotor Roda Dua", "89"));
        data.add(new keyvalue("Pembelian Barang Konsumtif Multiguna (non tanah dan/atau bangunan & non kendaraan bermotor roda empat)", "90"));

        //di hide sementara
//        data.add(new keyvalue("Take Over Kredit Multi Guna dari Bank Konvensional", "74"));

    }

    public static void embp(Context context, List<keyvalue> data){

        data.add(new keyvalue("EmBp", "222"));
        data.add(new keyvalue("Non EmBp", "222"));

    }

    public static void lamaBerdiriBadanUsaha(Context context, List<keyvalue> data){

        data.add(new keyvalue("Lebih Dari Atau Sama Dengan 4 tahun", "222"));
        data.add(new keyvalue("Kurang Dari 4 Tahun", "222"));
        data.add(new keyvalue("Nihil", "222"));

    }










    //MIKRO


    public static void sampleAddress(Context context, List<address> data){
        data.add(new address("Jakarta", "Jakarta Selatan", "Mampang Prapatan", "Pela Mampang", "12730"));
        data.add(new address("Jakarta", "Jakarta Timur", "Rawamangun", "Mangun Raya", "12570"));
        data.add(new address("Lampung", "Mesuji", "Simpang Pematang", "Simpang Pematang", "34569"));
        data.add(new address("Lampung", "Mesuji", "Simpang Pematang", "Simpang Pematang", "34569"));
        data.add(new address("Lampung", "Mesuji", "Simpang Pematang", "Simpang Pematang", "34569"));
    }

    public static void sampleSegmen(Context context, List<keyvalue> data){
        data.add(new keyvalue("Mikro", "MIKRO"));
        data.add(new keyvalue("Konsumer", "KONSUMER"));
    }

    public static void sampleProductMikro(Context context, List<keyvalue> data){
        data.add(new keyvalue("Mikro 25", "131"));
        data.add(new keyvalue("Mikro 75", "136"));
        data.add(new keyvalue("Mikro 200", "141"));
        data.add(new keyvalue("KUR", "127"));
    }

    public static void sampleProductKonsumer(Context context, List<keyvalue> data){
        data.add(new keyvalue("KPR", "KPR"));
        data.add(new keyvalue("KMJ", "KMJ"));
        data.add(new keyvalue("KMG", "KMG"));
    }

    public static void sampleProgramKpr(Context context, List<keyvalue> data){
        data.add(new keyvalue("KPR FLPP Rumah Tapak Regular", "222"));
        data.add(new keyvalue("KPR MRBH Non Fixed Income s/d 5th", "236"));
        data.add(new keyvalue("KPR MRBH Non Fixed Income s/d 10th", "237"));
        data.add(new keyvalue("KPR FLPP Rumah Tapak Program DP 5%", "239"));
    }

    public static void sampleProgramKmj(Context context, List<keyvalue> data){
        data.add(new keyvalue("KMJ Purna", "98"));
        data.add(new keyvalue("KMJ Purwa", "99"));
    }

    public static void sampleProgramKmg(Context context, List<keyvalue> data){
        data.add(new keyvalue("KMG EmBP", "1"));
        data.add(new keyvalue("KMG non EmBP", "2"));
    }

    public static void bidangUsaha(Context context, List<keyvalue> data){
        data.add(new keyvalue("Perkebunan", "1010"));
        data.add(new keyvalue("Pertanian/Peternakan/Perikanan", "1011"));
        data.add(new keyvalue("Bahan Mentah", "1012"));
        data.add(new keyvalue("Petrolium", "1110"));
        data.add(new keyvalue("Hortikultura", "1130"));
        data.add(new keyvalue("Komputer", "2010"));
        data.add(new keyvalue("Komputer Servis", "2011"));
        data.add(new keyvalue("Medikal", "2110"));
        data.add(new keyvalue("Kimia", "2111"));
        data.add(new keyvalue("Perhotelan", "2210"));
        data.add(new keyvalue("Perbankan", "3010"));
        data.add(new keyvalue("Institusi Financial", "3011"));
        data.add(new keyvalue("Konstruksi", "4010"));
        data.add(new keyvalue("Real Estate", "4020"));
        data.add(new keyvalue("Jasa Real Estate", "5010"));
        data.add(new keyvalue("Jasa Entertainment", "5020"));
        data.add(new keyvalue("Trading/Perdagangan", "5030"));
        data.add(new keyvalue("Jasa Angkutan", "5040"));
//        data.add(new keyvalue("Jasa Lainnya", "5030"));
    }

    public static void bidangKerjaan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Karyawan / PNS", "1"));
        data.add(new keyvalue("Karyawan / PNS Program FLPP", "2"));
        data.add(new keyvalue("Karyawan / PNS Program EMBP", "3"));
        data.add(new keyvalue("Karyawan BRIS", "4"));
        data.add(new keyvalue("Wiraswasta", "5"));
        data.add(new keyvalue("Profesional", "6"));
        data.add(new keyvalue("Karyawan / PNS + Wiraswasta", "7"));
        data.add(new keyvalue("Karyawan / PNS + Profesional", "8"));
        data.add(new keyvalue("Profesional + Wiraswasta", "9"));
    }

    public static void tujuanPenggunaan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Barang Modal Kerja", "40"));
        data.add(new keyvalue("Investasi", "41"));
        data.add(new keyvalue("Investasi & Barang Modal Kerja", "46"));
        data.add(new keyvalue("Konsumtif", "45"));
        data.add(new keyvalue("Modal Kerja, Investasi & Konsumtif", "49"));
        data.add(new keyvalue("Produktif Investasi & Konsumtif", "47"));
        data.add(new keyvalue("Produktif Modal Kerja & Konsumtif", "48"));
        data.add(new keyvalue("Takeover", "50"));
        data.add(new keyvalue("Takeover & Barang Modal Kerja", "51"));
        data.add(new keyvalue("Takeover & Investasi", "52"));
        data.add(new keyvalue("Takeover & Konsumtif", "53"));
        data.add(new keyvalue("Takeover, Investasi dan Barang Modal Kerja", "54"));
        data.add(new keyvalue("Takeover, Modal Kerja, Investasi & Konsumtif", "57"));
        data.add(new keyvalue("Takeover, Produktif Investasi & Konsumtif", "55"));
        data.add(new keyvalue("Takeover, Produktif Modal Kerja & Konsumtif", "56"));
    }

    public static void jenisKelamin(Context context, List<keyvalue> data){
        data.add(new keyvalue("Laki - Laki", "L"));
        data.add(new keyvalue("Perempuan", "P"));
    }

    public static void agama(Context context, List<keyvalue> data){
        data.add(new keyvalue("Islam", "ISL"));
        data.add(new keyvalue("Kristen", "KRI"));
        data.add(new keyvalue("Katholik", "KAT"));
        data.add(new keyvalue("Hindu", "HIN"));
        data.add(new keyvalue("Budha", "BUD"));
        data.add(new keyvalue("Lainnya", "ZZZ"));
    }

    public static void statusMenikah(Context context, List<keyvalue> data){
        data.add(new keyvalue("Belum Kawin", "Belum Kawin"));
        data.add(new keyvalue("Kawin", "Kawin"));
        data.add(new keyvalue("Cerai Mati", "Cerai Mati"));
        data.add(new keyvalue("Cerai Hidup", "Cerai Hidup"));
    }

    public static void tipePendapatan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Gaji", "1"));
        data.add(new keyvalue("Hasil Usaha", "2"));
        data.add(new keyvalue("Lain-lain", "3"));
    }

    public static void pendidikanTerakhir(Context context, List<keyvalue> data){
        data.add(new keyvalue("SDTT", "1"));
        data.add(new keyvalue("SD", "2"));
        data.add(new keyvalue("SMP", "3"));
        data.add(new keyvalue("SMA", "4"));
        data.add(new keyvalue("Diploma 1", "5"));
        data.add(new keyvalue("Diploma 2", "6"));
        data.add(new keyvalue("Diploma 3", "7"));
        data.add(new keyvalue("S-1", "8"));
        data.add(new keyvalue("S-2", "9"));
        data.add(new keyvalue("S-3", "10"));
    }

    public static void statusTempatDomisili(Context context, List<keyvalue> data){
        data.add(new keyvalue("Milik Sendiri", "0"));
        data.add(new keyvalue("Milik sendiri dan masih diangsur", "1"));
        data.add(new keyvalue("Milik Keluarga", "4"));
        data.add(new keyvalue("Warisan", "5"));
        data.add(new keyvalue("Kontrak", "2"));
        data.add(new keyvalue("Kost", "6"));
        data.add(new keyvalue("Lainnya", "1"));
    }

    public static void kriteriaSekom(Context context, List<keyvalue> data){
        data.add(new keyvalue("sektorEkonomiText", "sektorEkonomiText"));
        data.add(new keyvalue("sektorEkonomiSID", "sektorEkonomiSID"));
    }

    public static void statusPermohonan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Baru", "Baru"));
        data.add(new keyvalue("Lama", "Lama"));
    }

    public static void hubunganKeluarga(Context context, List<keyvalue> data){
        data.add(new keyvalue("Pemohon Sendiri", "Pemohon Sendiri"));
        data.add(new keyvalue("Pejabat Setempat", "Pejabat Setempat"));
        data.add(new keyvalue("Istri/Suami YMP", "Istri/Suami YMP"));
        data.add(new keyvalue("Tetangga Usaha", "Tetangga Usaha"));
        data.add(new keyvalue("Karyawan YMP", "Karyawan YMP"));
        data.add(new keyvalue("Kerabat Pemohon", "Kerabat Pemohon"));
        data.add(new keyvalue("Anak/Saudara YMP", "Anak/Saudara YMP"));
        data.add(new keyvalue("Tetangga Rumah", "Tetangga Rumah"));
    }

    public static void lokasiUsaha(Context context, List<keyvalue> data){
        data.add(new keyvalue("Pasar Utama", "Pasar Utama"));
        data.add(new keyvalue("Pasar Sekunder", "Pasar Sekunder"));
        data.add(new keyvalue("Plasma", "Plasma"));
    }

    public static void statusTempatUsaha(Context context, List<keyvalue> data){
        data.add(new keyvalue("Milik Sendiri - Beli", "Milik Sendiri - Beli"));
        data.add(new keyvalue("Milik Sendiri - Warisan", "Milik Sendiri - Warisan"));
        data.add(new keyvalue("Milik Keluarga", "Milik Keluarga"));
        data.add(new keyvalue("Kredit/Masih Mencicil", "Kredit/Masih Mencicil"));
        data.add(new keyvalue("Sewa", "Sewa"));
    }

    public static void jenisTempatUsaha(Context context, List<keyvalue> data){
        data.add(new keyvalue("Los/Lapak/Dasaran", "Los/Lapak/Dasaran"));
        data.add(new keyvalue("Toko/Ruko/Kios", "Toko/Ruko/Kios"));
        data.add(new keyvalue("Warung/Tenda", "Warung/Tenda"));
        data.add(new keyvalue("Gerobak/Berpindah", "Gerobak/Berpindah"));
        data.add(new keyvalue("Rumahan", "Rumahan"));
    }

    public static void aspekPemasaran(Context context, List<keyvalue> data){
        data.add(new keyvalue("Eceran", "Eceran"));
        data.add(new keyvalue("Grosir", "Grosir"));
        data.add(new keyvalue("Jasa", "Jasa"));
        data.add(new keyvalue("Agen", "Agen"));
        data.add(new keyvalue("Lainnya", "Lainnya"));
    }

    public static void jenisUsaha(Context context, List<keyvalue> data){
        data.add(new keyvalue("Sayur-mayur/Buah-buahan/Padi", "Sayur-mayur/Buah-buahan/Padi"));
        data.add(new keyvalue("Sembako/Kelontong", "Sembako/Kelontong"));
        data.add(new keyvalue("Pakaian/Alas Kaki", "Pakaian/Alas Kaki"));
        data.add(new keyvalue("Rongsokan/Barang Bekas", "Rongsokan/Barang Bekas"));
        data.add(new keyvalue("Rumah makan/Cathering (makanan matang)", "Rumah makan/Cathering (makanan matang)"));
        data.add(new keyvalue("Elektronik", "Elektronik"));
        data.add(new keyvalue("Daging/Unggas/Ikan", "Daging/Unggas/Ikan"));
        data.add(new keyvalue("Peralatan Rumah Tangga", "Peralatan Rumah Tangga"));
        data.add(new keyvalue("Industri Rumahan", "Industri Rumahan"));
        data.add(new keyvalue("Bahan Bangunan/Material", "Bahan Bangunan/Material"));
        data.add(new keyvalue("Transportasi", "Transportasi"));
        data.add(new keyvalue("Mebel", "Mebel"));
        data.add(new keyvalue("Supplier (pemasok)", "Supplier (pemasok)"));
        data.add(new keyvalue("Bengkel", "Bengkel"));
        data.add(new keyvalue("Usaha Isi Ulang", "Usaha Isi Ulang"));
        data.add(new keyvalue("Lainnya", "Lainnya"));
    }



    public static void rpcRatio(Context context, List<keyvalue> data){
        data.add(new keyvalue("< 1x", "1"));
        data.add(new keyvalue("1  - 2x", "2"));
        data.add(new keyvalue("> 2x", "3"));
    }

    public static void reputasiUsaha(Context context, List<keyvalue> data){
        data.add(new keyvalue("Opini Negatif dari Pelanggan", "1"));
        data.add(new keyvalue("Opini Positif/Negatif", "2"));
        data.add(new keyvalue("Opini Positif", "3"));
    }

    public static void riwayatHubdgnBank(Context context, List<keyvalue> data){
        data.add(new keyvalue("Sering Terjadi Tunggakan", "1"));
        data.add(new keyvalue("Pernah Terjadi Tunggakan", "2"));
        data.add(new keyvalue("Pembayaran Selalu Tepat Waktu", "3"));
    }


    public static void lamaUsaha(Context context, List<keyvalue> data){
        data.add(new keyvalue("= 2 tahun", "1"));
        data.add(new keyvalue("> 2 - 5 tahun", "2"));
        data.add(new keyvalue("> 5 tahun", "3"));
    }

    public static void prospekUsaha(Context context, List<keyvalue> data){
        data.add(new keyvalue("Stabil", "1"));
        data.add(new keyvalue("Berkembang", "2"));
        data.add(new keyvalue("Maju", "3"));
    }

    public static void ketergantunganSupplierdanPelanggan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Terbatas", "1"));
        data.add(new keyvalue("Beberapa", "2"));
        data.add(new keyvalue("Banyak dan Beragam", "3"));
    }

    public static void wilayahPemasaran(Context context, List<keyvalue> data){
        data.add(new keyvalue("Lokal", "1"));
        data.add(new keyvalue("Dalam Kota", "2"));
        data.add(new keyvalue("Antar Kota", "3"));
    }

    public static void jenisProduk(Context context, List<keyvalue> data){
        data.add(new keyvalue("Barang Mewah", "1"));
        data.add(new keyvalue("Barang & Jasa Sekunder", "2"));
        data.add(new keyvalue("Barang & Jasa Primer", "3"));
    }

    public static void jangkaWaktu(Context context, List<keyvalue> data){
        data.add(new keyvalue("> 3 tahun", "1"));
        data.add(new keyvalue("1 - 3 tahun", "2"));
        data.add(new keyvalue("<= 1 tahun", "3"));
    }

    public static void jenisPembiayaan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Musyarakah", "1"));
        data.add(new keyvalue("Mudharabah", "2"));
        data.add(new keyvalue("Murabahah", "3"));
    }



    //added by eki
    public static void bentukBidangTanah(Context context, List<keyvalue> data){
        data.add(new keyvalue("Segitiga", "1"));
        data.add(new keyvalue("Segiempat", "2"));
        data.add(new keyvalue("Trapesium", "3"));
        data.add(new keyvalue("Tak Beraturan", "4"));
    }

    public static void permukaanTanah(Context context, List<keyvalue> data){
        data.add(new keyvalue("Rata", "1"));
        data.add(new keyvalue("Bergelombang", "2"));
        data.add(new keyvalue("Landai", "3"));
        data.add(new keyvalue("Berkontur", "4"));
    }

    public static void jenisSuratTanah(Context context, List<keyvalue> data){
        data.add(new keyvalue("SHM", "1"));
        data.add(new keyvalue("SHGB", "2"));
        data.add(new keyvalue("SHMASRS", "3"));
        data.add(new keyvalue("Girik", "4"));
        data.add(new keyvalue("Letter C", "5"));
        data.add(new keyvalue("Letter D", "6"));
        data.add(new keyvalue("APHB", "7"));
        data.add(new keyvalue("AJB", "8"));
        data.add(new keyvalue("Surat Keterangan Camat", "9"));
        data.add(new keyvalue("Surat Hijau Surabaya", "10"));
        data.add(new keyvalue("Surat Keterangan Tanah", "11"));
        data.add(new keyvalue("Lainnya", "12"));
    }

    public static void hubDenganPemegangHak(Context context, List<keyvalue> data){
        data.add(new keyvalue("Sendiri", "1"));
        data.add(new keyvalue("Suami/Istri", "2"));
        data.add(new keyvalue("Orangtua", "3"));
        data.add(new keyvalue("Anak", "4"));
        data.add(new keyvalue("Pemilik Sebelumnya", "5"));
        data.add(new keyvalue("Lainnya", "6"));
    }

    public static void jenisBangunan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Rumah Tinggal", "1"));
        data.add(new keyvalue("Ruko / Rukan", "2"));
        data.add(new keyvalue("Gedung / Kantor", "3"));
        data.add(new keyvalue("Pabrik", "4"));
        data.add(new keyvalue("Gudang", "5"));
        data.add(new keyvalue("Rumah Panggung Kayu", "6"));
    }

    public static void lokasiBangunan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Pasar", "Pasar"));
        data.add(new keyvalue("Non Pasar", "NonPasar"));
    }

    public static void jenisAgunanXBRL(Context context, List<keyvalue> data){
        data.add(new keyvalue("Tanah dan Gedung / Ruang Kantor", "302"));
        data.add(new keyvalue("Tanah dan Gudang", "304"));
        data.add(new keyvalue("Tanah dan Rumah Toko/Rumah Kantor untuk tempat tinggal", "306"));
        data.add(new keyvalue("Tanah dan Rumah Tinggal untuk tempat tinggal", "312"));
    }

    public static void hubPenghuniDenganPemegangHak(Context context, List<keyvalue> data){
        data.add(new keyvalue("Sendiri", "1"));
        data.add(new keyvalue("Suami/Istri", "2"));
        data.add(new keyvalue("Orangtua", "3"));
        data.add(new keyvalue("Anak", "4"));
        data.add(new keyvalue("Pemilik Sebelumnya", "5"));
        data.add(new keyvalue("Penyewa", "6"));
        data.add(new keyvalue("Lainnya", "99"));
    }

    public static void kondisiBangunan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Bangunan Baru", "1"));
        data.add(new keyvalue("Terawat", "2"));
        data.add(new keyvalue("Tidak Terawat", "3"));
    }

    public static void pondasi(Context context, List<keyvalue> data){
        data.add(new keyvalue("Besi", "1"));
        data.add(new keyvalue("Baja", "2"));
        data.add(new keyvalue("Beton", "3"));
        data.add(new keyvalue("Kayu", "4"));
    }

    public static void jenisBangunanSpek(Context context, List<keyvalue> data){
        data.add(new keyvalue("Rumah Tinggal", "2976"));
    }

    public static void dinding(Context context, List<keyvalue> data){
        data.add(new keyvalue("Batu Bata", "1"));
        data.add(new keyvalue("Logam", "2"));
        data.add(new keyvalue("Bilik Bambu", "3"));
        data.add(new keyvalue("Kayu", "4"));
    }

    public static void atap(Context context, List<keyvalue> data){
        data.add(new keyvalue("Genteng", "1"));
        data.add(new keyvalue("Seng", "2"));
        data.add(new keyvalue("Asbes", "3"));
        data.add(new keyvalue("Beton", "4"));
        data.add(new keyvalue("Sirap", "5"));
        data.add(new keyvalue("Kayu", "6"));
        data.add(new keyvalue("Jerami", "7"));
    }

    public static void peruntukan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Pemukiman", "Pemukiman"));
        data.add(new keyvalue("Industri", "Industri"));
        data.add(new keyvalue("Perniagaan", "Perniagaan"));
        data.add(new keyvalue("Lainnya", "Lainnya"));
    }

    public static void perkembangan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Berkembang", "Berkembang"));
        data.add(new keyvalue("Sedang Berkembang", "Sedang Berkembang"));
        data.add(new keyvalue("Belum Berkembang", "Belum Berkembang"));
    }

    public static void kepadatan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Padat", "Padat"));
        data.add(new keyvalue("Cukup Padat", "Cukup Padat"));
        data.add(new keyvalue("Kurang Padat", "Kurang Padat"));
    }

    public static void aksesCapai(Context context, List<keyvalue> data){
        data.add(new keyvalue("Mudah", "Mudah"));
        data.add(new keyvalue("Cukup Mudah", "Cukup Mudah"));
        data.add(new keyvalue("Susah", "Susah"));
    }

    public static void aksesJalan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Jalan Propinsi", "Jalan Propinsi"));
        data.add(new keyvalue("Jalan Kabupaten", "Jalan Kabupaten"));
        data.add(new keyvalue("Jalan Komplek", "Jalan Komplek"));
        data.add(new keyvalue("Desa", "Desa"));
        data.add(new keyvalue("Gang", "Gang"));
        data.add(new keyvalue("Tidak Ada Akses", "Tidak Ada Akses"));
    }

    public static void fasilitasSosial(Context context, List<keyvalue> data){
        data.add(new keyvalue("Masjid", "Masjid"));
        data.add(new keyvalue("Angkutan Umum", "Angkutan Umum"));
        data.add(new keyvalue("Pasar", "Pasar"));
        data.add(new keyvalue("Sekolah", "Sekolah"));
        data.add(new keyvalue("Pusat Kesehatan", "Pusat Kesehatan"));
        data.add(new keyvalue("Taman", "Taman"));
    }

    public static void jenisAgunan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Kios", "Kios"));
        data.add(new keyvalue("Dasaran", "Dasaran"));
        data.add(new keyvalue("Los", "Los"));
        data.add(new keyvalue("Lapak", "Lapak"));
        data.add(new keyvalue("Toko", "Toko"));
        data.add(new keyvalue("Ruko", "Ruko"));
    }

    public static void hubunganPemegangHak(Context context, List<keyvalue> data){
        data.add(new keyvalue("Sendiri", "Sendiri"));
        data.add(new keyvalue("Suami/Istri", "Suami/Istri"));
        data.add(new keyvalue("Orangtua", "Orangtua"));
        data.add(new keyvalue("Anak", "Anak"));
        data.add(new keyvalue("Pemilik Sebelumnya", "Pemilik Sebelumnya"));
        data.add(new keyvalue("Lainnya", "Lainnya"));
    }

    public static void lokasiAgunan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Di Depan", "Di Depan"));
        data.add(new keyvalue("Di Samping", "Di Samping"));
        data.add(new keyvalue("Di Belakang", "Di Belakang"));
        data.add(new keyvalue("Di Dalam", "Di Dalam"));
    }

    public static void adaTidak(Context context, List<keyvalue> data){
        data.add(new keyvalue("Ada", "Ada"));
        data.add(new keyvalue("Tidak", "Tidak"));
    }

    public static void statusPenggarap(Context context, List<keyvalue> data){
        data.add(new keyvalue("Sewa", "Sewa"));
        data.add(new keyvalue("Penjaga", "Penjaga"));
        data.add(new keyvalue("Pemilik", "Pemilik"));
        data.add(new keyvalue("Lainnya", "Lainnya"));
    }

    public static void hubPenggarapDgPemegang(Context context, List<keyvalue> data){
        data.add(new keyvalue("Sendiri", "Sendiri"));
        data.add(new keyvalue("Suami/Istri", "Suami/Istri"));
        data.add(new keyvalue("Orangtua", "Orangtua"));
        data.add(new keyvalue("Anak", "Anak"));
        data.add(new keyvalue("Pemilik Sebelumnya", "Pemilik Sebelumnya"));
        data.add(new keyvalue("Lain", "Lain"));


    }

    public static void jenisDokumen(Context context, List<keyvalue> data){
        data.add(new keyvalue("SHM", "SHM"));
        data.add(new keyvalue("SHGB", "SHGB"));
        data.add(new keyvalue("SHMRS", "SHMRS"));
        data.add(new keyvalue("Girik", "Girik"));
        data.add(new keyvalue("Letter C", "Letter C"));
        data.add(new keyvalue("Letter D", "Letter D"));
        data.add(new keyvalue("APHB", "APHB"));
        data.add(new keyvalue("AJB", "AJB"));
        data.add(new keyvalue("Surat Keterangan Camat", "Surat Keterangan Camat"));
        data.add(new keyvalue("Surat Hijau Surabaya", "Surat Hijau Surabaya"));
        data.add(new keyvalue("Surat Keterangan Tanah / Sporadik", "Surat Keterangan Tanah / Sporadik"));
        data.add(new keyvalue("Lain", "Lain"));


    }

    public static void checkBpn(Context context, List<keyvalue> data){
        data.add(new keyvalue("Ya", "Ya"));
        data.add(new keyvalue("Tidak", "Tidak"));
    }

    public static void hasilBpn(Context context, List<keyvalue> data){
        data.add(new keyvalue("Ok", "Ok"));
        data.add(new keyvalue("Tidak OK", "Tidak OK"));
    }


    public static void currentRatio(Context context, List<keyvalue> data){
        data.add(new keyvalue("< 1", "1"));
        data.add(new keyvalue("1 - 2", "2"));
        data.add(new keyvalue("> 2", "3"));
    }

    public static void profitability(Context context, List<keyvalue> data){
        data.add(new keyvalue("< 15%", "1"));
        data.add(new keyvalue("15% - 25%", "2"));
        data.add(new keyvalue("> 25%", "3"));
    }

    public static void jenisDeposito(Context context, List<keyvalue> data){
        data.add(new keyvalue("Deposito Rupiah", "Deposito Rupiah"));
        data.add(new keyvalue("Deposito Valas", "Deposito Valas"));
    }

    public static void aro(Context context, List<keyvalue> data){
        data.add(new keyvalue("Ya", "1"));
        data.add(new keyvalue("Tidak", "2"));
    }

    public static void dokumenBuktiHakKios(Context context, List<keyvalue> data){
        data.add(new keyvalue("SHPTU", "SHPTU"));
        data.add(new keyvalue("SIPTU", "SIPTU"));
        data.add(new keyvalue("SIPTB", "SIPTB"));
        data.add(new keyvalue("SHM", "SHM"));
        data.add(new keyvalue("SHGB", "SHGB"));
        data.add(new keyvalue("SHMRS", "SHMRS"));
        data.add(new keyvalue("Girik", "Girik"));
        data.add(new keyvalue("Surat Keterangan Lainya", "Surat Keterangan Lainya"));
    }

    public static void jenisKendaraan(Context context, List<keyvalue> data) {
        data.add(new keyvalue("Motor", "Motor"));
        data.add(new keyvalue("Mobil", "Mobil"));
        data.add(new keyvalue("Truk", "Truk"));
        data.add(new keyvalue("Pick Up", "Pick Up"));
        data.add(new keyvalue("Bus", "Bus"));
    }

    public static void kategoriKendaraan(Context context, List<keyvalue> data) {
        data.add(new keyvalue("Passenger", "Passenger"));
        data.add(new keyvalue("Non Passenger", "Non Passenger"));
    }

    public static void penggunaanJaminan(Context context, List<keyvalue> data) {
        data.add(new keyvalue("Kendaraan Pribadi", "Kendaraan Pribadi"));
        data.add(new keyvalue("Kendaraan Operasional Usaha", "Kendaraan Operasional Usaha"));
        data.add(new keyvalue("Kendaraan Usaha (disewakan)", "Kendaraan Usaha (disewakan)"));
    }

    public static void daerahOperasional(Context context, List<keyvalue> data) {
        data.add(new keyvalue("Radius <= 25 km", "25"));
        data.add(new keyvalue("Radius <= 50 km", "50"));
        data.add(new keyvalue("Radius > 50 km", "55"));
    }

    public static void kondisiJaminan(Context context, List<keyvalue> data) {
        data.add(new keyvalue("Baru", "Baru"));
        data.add(new keyvalue("Bekas", "Bekas"));
    }

    public static void hubPemilikDenganNasabah(Context context, List<keyvalue> data) {
        data.add(new keyvalue("Sendiri", "Sendiri"));
        data.add(new keyvalue("Suami/Istri", "Suami/Istri"));
        data.add(new keyvalue("Orangtua", "Orangtua"));
        data.add(new keyvalue("Anak", "Anak"));
        data.add(new keyvalue("Pemilik Sebelumnya", "Pemilik Sebelumnya"));
        data.add(new keyvalue("Lainnya", "Lainnya"));
    }

    public static void buktiGesekMesin(Context context, List<keyvalue> data) {
        data.add(new keyvalue("Ada", "1"));
        data.add(new keyvalue("Tidak Ada", "2"));
    }

    public static void buktiGesekRangka(Context context, List<keyvalue> data) {
        data.add(new keyvalue("Ada", "1"));
        data.add(new keyvalue("Tidak Ada", "2"));
    }

    public static void kendaraanJepang(Context context, List<keyvalue> data) {
        data.add(new keyvalue("Ya", "Ya"));
        data.add(new keyvalue("Tidak", "Tidak"));
    }

    public static void platKuning(Context context, List<keyvalue> data) {
        data.add(new keyvalue("Ya", "Ya"));
        data.add(new keyvalue("Tidak", "Tidak"));
    }

    public static void checkSamsat(Context context, List<keyvalue> data) {
        data.add(new keyvalue("Ya", "Ya"));
        data.add(new keyvalue("Tidak", "Tidak"));
    }

    public static void hasil(Context context, List<keyvalue> data) {
        data.add(new keyvalue("OK", "OK"));
        data.add(new keyvalue("Tidak OK", "Tidak OK"));
    }

    //Purna

    public static void instansiNonEmBp (Context context, List<keyvalue> data) {
        data.add(new keyvalue("Individual Tanpa Instansi", "0"));
    }



//    public static void sampleTindakLanjut(Context context, List<tindaklanjut> data){
//        data.add(new tindaklanjut("By Come - Rabu, 12 April 2019 16.30 WIB", "Menginput data nasabah yang mau pembiayaan"));
//        data.add(new tindaklanjut("By Phone - Senin, 19 Mei 2019 10.30 WIB", "Nelpon nasabah yang mau pembiayaan"));
//        data.add(new tindaklanjut("By Come - Kamis, 26 Mei 2019 10.30 WIB", "Datangin nasabah yang mau pembiayaan"));
//    }

//    public static void sampleDataPembiayaan(Context context, List<datapembiayaan> data){
//        data.add(new datapembiayaan("12000", "Mikro 25", "25000000", "12-02-2018", "Dalam Proses Pengisian Paket Aplikasi"));
//        data.add(new datapembiayaan("13000", "Mikro 75", "75000000", "17-07-2015", "Proses Pengiriman Data ke SYIAR"));
//        data.add(new datapembiayaan("15000", "KUR", "15000000", "17-09-2019", "Dalam Proses Pengisian Paket Aplikasi"));
//    }
}
