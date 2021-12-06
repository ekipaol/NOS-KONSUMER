package com.application.bris.ikurma_nos_konsumer.api.config;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;

/**
 * Created by PID on 4/5/2019.
 */

public class UriApi {

    public static class Baseurl{
//        public static final String URLDEV = "http://10.1.25.55:8080/MobileBRISIAPI-EKI/webresources/"; //DEV
        public static final String URLDEV = "https://10.0.116.105/"; //DEV BSI
        public static final String URLPROD = "https://intel.brisyariah.co.id:55056/MobileBRISIAPI/webresources/"; //PROD
//        public static final String URLPROD = "https://intel.brisyariah.co.id:55056/MobileBRISIAPI-EKI/webresources/"; //PROD EKI

        public static String URL = (BuildConfig.IS_PRODUCTION) ? URLPROD : URLDEV ; //ENV BASED URI SELECTOR

        public static final String URL_MAPS = "https://api.opencagedata.com/";
    }

    public class general {
        public static final String searchAddress = "NOS_MIKRO_PROC/rest/API_NOS/pencarianKodepos";
        public static final String login = "user-service/logins";
        public static final String simpanFeedback = "generic/simpanFeedback";
        public static final String login2 = "user-service/logins";
        public static final String home = "generic/dashboardKmg";
        public static final String getProduct = "generic/getProduk";
        public static final String getKategSektorEkonomii = "generic/mikro/hotprospek/datapembiayaan/kategoriSektorEkonomi";
        public static final String searcSektorEkonomi = "generic/mikro/hotprospek/datapembiayaan/pencarianSektorEkonomi";
        public static final String listDeviasi = "generic/mikro/hotprospek/kirimPutusan/listDeviasiMikro";
        public static final String activation = "generic/aktivasi/activate";
        public static final String checkUpdate = "generic/getAppVersion";
        public static final String geocoding = "geocode/v1/google-v3-json?";
        public static final String updateFirebase = "generic/aktivasi/updateFirebaseMessagingID";
        public static final String brisnotifRegister = "generic/brisnotifRegister";
    }

    public class pipeline {

        public static final String listPipeline = "generic/mikro/pipeline/listPipeline";
        public static final String inquiryPipeline = "generic/mikro/pipelinek/inquirePipeline";
        public static final String uploadFoto = "generic/uploadImage";
        public static final String sendDataPipeline = "generic/mikro/pipeline/updatePipeline";
        public static final String pipelineToHotprospek = "generic/mikro/pipeline/kirimHotProspek";
        public static final String rejectPipeline = "generic/mikro/pipeline/tolak";
        public static final String savePipelineHotprospek = "generic/mikro/pipeline/simpanDanKirimHotProspek";

        public static final String getListInstansi = "generic/konsumer/pipeline/listInstansi";
        public static final String listPipelineKonsumer = "generic/konsumer/pipeline/listPipeline";
        public static final String inquiryPipelineKonsumer = "generic/konsumer/pipeline/inquirePipeline";
        public static final String sendDataPipelineKonsumer = "generic/konsumer/pipeline/updatePipeline";
        public static final String pipelineToHotprospekKonsumer = "generic/konsumer/pipeline/kirimHotProspek";
        public static final String rejectPipelineKonsumer = "generic/konsumer/pipeline/tolak";
        public static final String savePipelineHotprospekKonsumer = "generic/konsumer/pipeline/simpanDanKirimHotProspek";
        public static final String getTujuanPenggunaan = "generic/konsumer/pipeline/listTujuanPenggunaan";


        //Purna
        public static final String listProgram = "generic/konsumer/pipeline/listProduct";
        public static final String listInstitusi = "generic/konsumer/pipeline/listInstitusi";
        public static final String listRekananDM = "generic/konsumer/pipeline/listRekananDM";
        public static final String listKategNasabah = "generic/konsumer/pipeline/listKategNasabah";

        //KPR
        public static final String listPipelineKpr = "generic/konsumer/kpr/pipeline/listPipeline";
        public static final String listPihakKetiga = "generic/konsumer/kpr/pipeline/listPihakKetiga";
        public static final String listProject = "generic/konsumer/kpr/pipeline/listProject";
        public static final String listTujuanPenggunaanKpr = "generic/konsumer/kpr/pipeline/listTujuanPenggunaan";
        public static final String listBidangPekerjaan = "generic/konsumer/kpr/pipeline/listBidangPekerjaan";
        public static final String listJenisKPR = "generic/konsumer/kpr/pipeline/listJenisKPR";
        public static final String inquiryPipelineKpr = "generic/konsumer/kpr/pipeline/inquirePipeline";

        public static final String sendDataPipelineKpr = "generic/konsumer/kpr/pipeline/updatePipeline";
        public static final String pipelineToHotprospekKpr = "generic/konsumer/kpr/pipeline/kirimHotProspek";
        public static final String rejectPipelineKpr = "generic/konsumer/kpr/pipeline/tolak";
        public static final String savePipelineHotprospekKpr= "generic/konsumer/kpr/pipeline/simpanDanKirimHotProspek";

        public static final String listProgramKpr = "generic/konsumer/kpr/pipeline/listProgramKpr";





    }



    public class hotprospek {
        public static final String cekSikp = "generic/mikro/hotprospek/prescreening/checkSIKP";
//        public static final String listHotprospek = "generic/mikro/hotprospek/listHotProspek";
        public static final String inquiryHotprospek = "generic/konsumer/kmg/hotprospek/inquireHotprospek";

        public static final String rejectHotprospek = "generic/mikro/hotprospek/tolakHotprospek";
//        public static final String inquiryDataLengkap = "generic/mikro/hotprospek/datalengkap/inquireDataLengkap";
//        public static final String sendDataLengkap = "generic/mikro/hotprospek/datalengkap/updateDataLengkap";
//        public static final String inquiryHistory = "generic/mikro/hotprospek/history/history";
        public static final String inquirySektorEkonomi = "generic/konsumer/kmg/hotprospek/datapby/inquireDataPembiayaan";
        public static final String sendDataSektorEkonomi = "generic/konsumer/kmg/hotprospek/datapby/updateDataPembiayaan";
        public static final String hitungRPC = "generic/mikro/hotprospek/rpc/hitungRPC";
        public static final String inquiryRPC = "generic/mikro/hotprospek/rpc/inquireRPC";

        public static final String inquiryKelengkapanDokumen = "generic/mikro/hotprospek/kelengkapandokumen/inquireKelengkapanDokumen";
        public static final String sendKelengkapanDokumen = "generic/mikro/hotprospek/kelengkapandokumen/updateKelengkapanDokumen";


        public static final String cekDHNKonsumer = "generic/konsumer/kmg/hotprospek/prescreening/checkDHN";
        public static final String cekDukcapilKonsumer = "generic/konsumer/kmg/hotprospek/prescreening/checkDUKCAPIL";
        public static final String cekSlikKonsumer = "generic/konsumer/kmg/hotprospek/prescreening/checkSLIK";
        public static final String inquiryPrescreeningKonsumer = "generic/konsumer/kmg/hotprospek/prescreening/inquirePrescreening";
        public static final String sendPrescreeningKonsumer = "generic/konsumer/kmg/hotprospek/prescreening/updatePrescreening";
        public static final String downloadSlikKonsumer = "generic/konsumer/kmg/hotprospek/prescreening/downloadSLIK";
        public static final String downloadSlikPasanganKonsumer = "generic/konsumer/kmg/hotprospek/prescreening/downloadSLIKPasangan";
        public static final String inquiryRemaksSlikKonsumer = "generic/konsumer/kmg/hotprospek/memosales/inquiryMemosales";
        public static final String sendRemaksSlikKonsumer = "generic/konsumer/kmg/hotprospek/memosales/updateMemosales";



        public static final String cekRekomendasi = "generic/mikro/hotprospek/lkn/validateLKN";
        public static final String sendLkn = "generic/mikro/hotprospek/lkn/updateLKN";
        public static final String inquiryLKN = "generic/mikro/hotprospek/lkn/inquireLKN";

        public static final String inquiryScoring = "generic/mikro/hotprospek/scoringtanpajaminan/inquireScoring";
        public static final String sendScoring = "generic/mikro/hotprospek/scoringtanpajaminan/updateScoring";

        public static final String sendPutusanMikroDeviasi = "generic/mikro/hotprospek/kirimPutusan/kirimPutusanDeviasiMikro";
        public static final String sendPutusanMikro = "generic/mikro/hotprospek/kirimPutusan/KirimPutusanMikro";



        public static final String listAgunan = "generic/mikro/hotprospek/agunan/CariDataAgunan_Pengikatan";
        public static final String listAgunanAll = "generic/mikro/hotprospek/agunan/cekDataAgunan_Existing";


        public static final String inquiryAgunanTanahBangunan = "generic/mikro/hotprospek/agunan/inquiryDataAgunan_2";
        public static final String sendAgunanTanahBangunan = "generic/mikro/hotprospek/agunan/saveDataAgunan";
        public static final String inquiryInfoAgunan = "generic/mikro/hotprospek/agunan/inquiryDataAgunan_Pengikatan";
        public static final String deleteAgunan= "generic/mikro/hotprospek/agunan/HapusDataAgunan";
        public static final String setPengikatan= "generic/mikro/hotprospek/agunan/inquiryDataAgunan_SetPengikatan";
        public static final String ikatAgunan= "generic/mikro/hotprospek/agunan/SavePengikatan";
        public static final String jenisKlasifikasi= "generic/mikro/hotprospek/agunan/jenisKlasifikasi";
        public static final String saveAgunanTerikat= "generic/mikro/hotprospek/agunan/SaveAgunanTerikat";

        public static final String saveKendaraan = "generic/mikro/hotprospek/agunan/saveDataAgunan_Kendaraan";
        public static final String inquiryKendaraan = "generic/mikro/hotprospek/agunan/inquiryDataAgunan_Kendaraan";

        public static final String saveTanahKosong = "generic/mikro/hotprospek/agunan/saveDataAgunan_Tanahkosong";
        public static final String inquiryAgunanTanahKosong = "generic/mikro/hotprospek/agunan/inquiryDataAgunan_Tanahkosong";

        public static final String inquiryAgunanKios = "generic/mikro/hotprospek/agunan/inquiryDataAgunan_Kios";
        public static final String sendAgunanKios = "generic/mikro/hotprospek/agunan/saveDataAgunan_Kios";

        public static final String inquiryAgunanDeposito = "generic/mikro/hotprospek/agunan/inquiryDataAgunan_Deposito";
        public static final String sendAgunanDeposito = "generic/mikro/hotprospek/agunan/saveDataAgunan_Deposito";

        public static final String inquiryAgunanKendaraan = "generic/mikro/hotprospek/agunan/inquiryDataAgunan_Kendaraan";
        public static final String sendAgunanKendaraan = "generic/mikro/hotprospek/agunan/saveDataAgunan_Kendaraan";



        //KONSUMER KMG

        public static final String inquiryHotprospekKpr = "generic/konsumer/kpr/hotprospek/inquireHotprospek";
        public static final String listHotprospek = "generic/konsumer/kmg/hotprospek/listHotProspek";
        public static final String sendDataHotprospek = "generic/konsumer/kmg/hotprospek/updateHotprospek";
        public static final String inquiryDataLengkap = "generic/konsumer/kmg/hotprospek/datalengkap/inquireDataLengkap";
        public static final String sendPutusanKmg = "generic/konsumer/kmg/hotprospek/kirimPutusan/KirimPutusan";


        public static final String sendDataLengkap = "generic/konsumer/kmg/hotprospek/datalengkap/updateDataLengkap";

        public static final String inquiryDataFinansial = "generic/konsumer/kmg/hotprospek/datafinansial/inquiryLoadPrescoring";
        public static final String simpanDataFinansial = " generic/konsumer/kmg/hotprospek/datafinansial/simpanDataFinansial";


        public static final String validasiDataFinansial = "generic/konsumer/kmg/hotprospek/datafinansial/validasiPlafond";
        public static final String inquiryScoringKmg = "generic/konsumer/kmg/hotprospek/scoring/inquireScoring";
        public static final String updateScoringKmg = "generic/konsumer/kmg/hotprospek/scoring/updateScoring";
        public static final String inquiryKelengkapanDokumenKonsumer = "generic/konsumer/kmg/hotprospek/kelengkapandokumen/inquireKelengkapanDokumen";
        public static final String sendKelengkapanDokumenKonsumer = "generic/konsumer/kmg/hotprospek/kelengkapandokumen/updateKelengkapanDokumen";

        public static final String inquiryHistory = "generic/konsumer/kmg/hotprospek/history/history";

        public static final String cekNikPasangan = "generic/mikro/konsumer/kmg/hotprospek/inquiryPasangan";

        public static final String listAsuransi = "generic/konsumer/kmg/hotprospek/datafinansial/ddlAsuransiPenjaminan";
        public static final String inquiryIjk = "generic/konsumer/kmg/hotprospek/datafinansial/inquiryIjkJamsyar";



        public static final String urlPdf = "generic/getPdf/";
        public static final String uploadFile = "generic/uploadFile";


        //KONSUMER KPR

        public static final String inquiryDataLengkapKpr = "generic/konsumer/kpr/hotprospek/datalengkap/inquireDataLengkap";
        public static final String sendDataLengkapKpr = "generic/konsumer/kpr/hotprospek/datalengkap/updateDataLengkap";
        public static final String listHotprospekKpr = "generic/konsumer/kpr/hotprospek/listHotProspek";




        public static final String updateScoringKpr = "generic/konsumer/kpr/hotprospek/scoring/updateScoring";


        public static final String inquiryPrescreeningKpr = "generic/konsumer/kpr/hotprospek/prescreening/inquirePrescreening";
        public static final String cekDHNKpr = "generic/konsumer/kpr/hotprospek/prescreening/checkDHN";
        public static final String cekDukcapilKpr = "generic/konsumer/kpr/hotprospek/prescreening/checkDUKCAPIL";
        public static final String cekSlikKpr = "generic/konsumer/kpr/hotprospek/prescreening/checkSLIK";
        public static final String sendPrescreeningKpr = "generic/konsumer/kpr/hotprospek/prescreening/updatePrescreening";
        public static final String downloadSlikKpr = "generic/konsumer/kpr/hotprospek/prescreening/downloadSLIK";
        public static final String downloadSlikPasanganKpr = "generic/konsumer/kpr/hotprospek/prescreening/downloadSLIKPasangan";
        public static final String inquiryRemaksSlikKpr = "generic/konsumer/kpr/hotprospek/memosales/inquiryMemosales";
        public static final String sendRemaksSlikKpr = "generic/konsumer/kpr/hotprospek/memosales/updateMemosales";
        public static final String listJenisPekerjaan = " generic/konsumer/kpr/hotprospek/datalengkap/listJenisPekerjaan";

        public static final String inquiryDataFinansialKpr = "generic/konsumer/kpr/hotprospek/datafinansial/inquiryLoadPrescoring";

        public static final String simpanDataFinansialKpr = " generic/konsumer/kpr/hotprospek/datafinansial/simpanDataFinansial";

        public static final String validasiDataFinansialKpr = "generic/konsumer/kpr/hotprospek/datafinansial/validasiPlafond";

        public static final String sendKelengkapanDokumenKprKaryawanPns = "generic/konsumer/kpr/hotprospek/kelengkapandokumen/updateKelengkapanDokumen";

        public static final String getKategSektorEkonomiKpr = "generic/konsumer/kpr/hotprospek/datapby/kategoriSektorEkonomi";
        public static final String searcSektorEkonomiKpr = "generic/konsumer/kpr/hotprospek/datapby/pencarianSektorEkonomi";

        public static final String inquiryKelengkapanDokumenKpr = "generic/konsumer/kpr/hotprospek/kelengkapandokumen/inquireKelengkapanDokumen";

        public static final String inquirySektorEkonomiKpr = "generic/konsumer/kpr/hotprospek/datapby/inquireDataPembiayaan";

        public static final String sendDataSektorEkonomiKpr = "generic/konsumer/kpr/hotprospek/datapby/updateDataPembiayaan";

        public static final String requestAppraisal = "generic/mikro/hotprospek/agunan/kirimPermintaanApraisal";

        public static final String searchPasar = "generic/mikro/hotprospek/agunan/pencarianNamaPasar";

        public static final String listJenisBangunan = "generic/mikro/hotprospek/agunan/listJenisBangunan";

        public static final String sendPutusanKpr = "generic/konsumer/kpr/hotprospek/kirimPutusan/KirimPutusan";

        public static final String rejectHotprospekKpr = "generic/konsumer/kpr/hotprospek/tolakHotprospek";

        //AO SILANG
        public static final String listPenilaianAgunan = "generic/konsumer/kpr/hotprospek/listPenilaianAgunan";
        public static final String rejectAppraisal = "generic/pemutus/apraisalKembalikanKeAO";
        public static final String selesaiAppraisal = "generic/konsumer/kpr/hotprospek/selesaiApraisal";
        public static final String kirimApraisal = "generic/pemutus/apraisalKirimKeApraisal";

        //MONITORING
        public static final String listMonitoringNasabah = "generic/monitor/listNasabah";
        public static final String listMonitoringNasabahNpf = "generic/monitor/listNasabahNpf";
        public static final String getSaldoNasabah = "generic/getSaldoNasabah";
        public static final String inquiryRekeningAmanah = "generic/inquiryRekeningAmanah";
        public static final String rankingAoTop = "generic/monitor/getRankingAoTop";
        public static final String rankingAoBottom = "generic/monitor/getRankingAoWorst";
        public static final String getRiwayatMutasi = "generic/monitor/getRiwayatMutasi";
        public static final String getRataRata = "generic/monitor/getRatarata";











    }



    public class approved{
        public static final String listApproved = "generic/konsumer/kmg/hotprospek/listApproved";
    }

    public class rejected{
        public static final String listRejected = "generic/konsumer/kmg/hotprospek/listDitolak";
    }

    public class ceknasabah{
        public static final String cekNasabah = "generic/konsumer/pipeline/cekNasabah";
    }

    public class foto{
        public static final String urlPhoto = "generic/getImage/";
        public static final String urlPhotoProfil = "generic/getImageProfile/";


    }



    public class flpp{
        public static final String listFollowupFlpp = "generic/konsumer/kpr/pipeline/listFollowUpFlpp";
        public static final String listKonfirmasiFlpp = "generic/konsumer/kpr/pipeline/listKonfirmasiFlpp";
        public static final String listKotaKabupatenFlpp = "generic/konsumer/kpr/pipeline/listKotaKabupatenFlpp";
        public static final String listKelurahanFlpp = "generic/konsumer/kpr/hotprospek/listKelurahanFlpp";
        public static final String sendAgunanTanahBangunanFlpp = "generic/mikro/hotprospek/agunan/saveDataAgunanFlpp";
        public static final String inquiryKodeWilayah = "generic/mikro/hotprospek/agunan/inquiryKodeWilayah";
        public static final String getFotoFlpp = "generic/konsumer/kpr/hotprospek/getFotoFlpp";
        public static final String loginSikasep = "generic/konsumer/kpr/hotprospek/loginSikasep";
        public static final String simpanFollowUpFlpp = "generic/konsumer/kpr/hotprospek/followUpFlpp";
        public static final String simpanKonfirmasiFlpp = "generic/konsumer/kpr/hotprospek/konfirmasiFlpp";
        public static final String sendDataPipelineKprFlpp = "generic/konsumer/kpr/pipeline/updatePipelineFlpp";
        public static final String getPihakketiga = "generic/konsumer/kpr/pipeline/getPihakKetiga";
        public static final String getDataSikasep = "generic/konsumer/kpr/hotprospek/getDataSikasep";

        public static final String simpanDataFinansialFlpp = " generic/konsumer/kpr/hotprospek/datafinansial/simpanDataFinansialFlpp";

        public static final String validasiDataFinansialFlpp = "generic/konsumer/kpr/hotprospek/datafinansial/validasiPlafondFlpp";

        public static final String listRumahFlpp = "generic/konsumer/kpr/hotprospek/getIdRumahTapak";
        public static final String getBlokByIdLokasi = "generic/konsumer/kpr/hotprospek/getBlokByIdLokasi";
        public static final String sendDataLengkapFlpp = "generic/konsumer/kpr/hotprospek/datalengkap/updateDataLengkapFlpp";
        public static final String inquiryDataFinansialKprFlpp = "generic/konsumer/kpr/hotprospek/datafinansial/inquiryLoadPrescoringFlpp";

        public static final String listJenisPekerjaanFlpp = " generic/konsumer/kpr/hotprospek/datalengkap/listJenisPekerjaanFlpp";
        public static final String updateScoringFlpp = "generic/konsumer/kpr/hotprospek/scoring/updateScoringFlpp";

        public static final String sendKelengkapanDokumenKprFlpp = "generic/konsumer/kpr/hotprospek/kelengkapandokumen/updateKelengkapanDokumenFlpp";

        public static final String inquirySektorEkonomiFlpp = "generic/konsumer/kpr/hotprospek/datapby/inquireDataPembiayaanFlpp";

        public static final String savePipelineHotprospekFlpp= "generic/konsumer/kpr/pipeline/simpanDanKirimHotProspekFlpp";

        public static final String inquiryKelengkapanDokumenFlpp = "generic/konsumer/kpr/hotprospek/kelengkapandokumen/inquireKelengkapanDokumenFlpp";

        public static final String updatePipelineFlpp = "generic/konsumer/kpr/pipeline/updatePipelineFlpp";

        public static final String listTelahKonfirmasiFlpp = "generic/konsumer/kpr/pipeline/listTelahKonfirmasiFlpp";

        public static final String cekSikasepByKtp = "generic/konsumer/kpr/pipeline/cekSikasepByKtp";

        public static final String getDataUpdateIdLokasi = "generic/konsumer/kpr/hotprospek/getDataUpdateIdLokasi";

        public static final String updateIdLokasiFlpp = "generic/konsumer/kpr/hotprospek/updateIdLokasiFlpp";

    }

    public class prapen{

        //D1
        public static final String dropdownTipeProduk = "NOS_PRAPEN_Services/rest/Picklist/TipeProduk";
        public static final String dropdownSegmen = "NOS_PRAPEN_Services/rest/Picklist/Segmen";
        public static final String dropdownJenisPembiayaan = "NOS_PRAPEN_Services/rest/Picklist/JenisPembiayaan";
        public static final String dropdownTujuanPembiayaan = "NOS_PRAPEN_Services/rest/Picklist/TujuanPembiayaan";
        public static final String dropdownProgram = "NOS_PRAPEN_Services/rest/Picklist/Program";
        public static final String dropdownLembagaPengelolaPensiun = "NOS_PRAPEN_Services/rest/Picklist/LembagaPengelolaPensiun";
        public static final String dropdownTreatmentRekening = "NOS_PRAPEN_Services/rest/Picklist/Treatment";
        public static final String dropdownMitraFronting  = "NOS_PRAPEN_Services/rest/Picklist/MitraFronting";
        public static final String cekHasilRekomendasiAkad = "NOS_PRAPEN_Services/rest/D1/InquiryHasilRekomendasiAkad";
        public static final String updateDataPembiayaan = "NOS_PRAPEN_Services/rest/D1/UpdateDataPembiayaan";
        public static final String inquiryDataPembiayaan = "NOS_PRAPEN_Services/rest/D1/InquiryDataPembiayaan";
        public static final String listAplikasiMarketing = "NOS_PRAPEN_Services/rest/APIUmum/ListDataAplikasiMarketing";
        public static final String inquiryDedupe = "NOS_PRAPEN_Services/rest/D1/InquiryCheckDedupe";
        public static final String updateDedupe = "NOS_PRAPEN_Services/rest/D1/UpdateCheckDedupe";
        public static final String updateDataNasabah = "NOS_PRAPEN_Services/rest/D1/UpdateDataDukcapil";
        public static final String inquiryDataNasabah = "NOS_PRAPEN_Services/rest/D1/InquiryDukcapilPribadiPasangan";
        public static final String updateDataInstansiDapen = "NOS_PRAPEN_Services/rest/D1/UpdateInstansidanDapen";
        public static final String inquiryDataInstansiDapen = "NOS_PRAPEN_Services/rest/D1/InquiryInstansidanDapen";
        public static final String validasiPayroll = "NOS_PRAPEN_Services/rest/D1/ValidasiNoRekPayroll";
        public static final String validasiLngp = "NOS_PRAPEN_Services/rest/D1/ValidasiLNGP";
        public static final String validasiDukcapil = "NOS_PRAPEN_Services/rest/D1/ValidasiDataDukcapil";
        public static final String inquiryNamaKodeAo = "NOS_PRAPEN_Services/rest/D1/InquiryNamaKodeAO";
        public static final String updateDataMarketing = "NOS_PRAPEN_Services/rest/D1/UpdateMitraMarketing";
        public static final String inquiryDataMarketing = "NOS_PRAPEN_Services/rest/D1/InquiryMitraMarketing";
        public static final String lanjutHotprospek = "NOS_PRAPEN_Services/rest/D1/UpdatelanjutHotProspek";

        //D3
        public static final String inquiryIdebOjk = "NOS_PRAPEN_Services/rest/D3/InquiryIDebSlikOJK";
        public static final String updateIdebOjk = "NOS_PRAPEN_Services/rest/D3/UpdateIDebSlikOJK";
        public static final String downloadIdeb = "NOS_PRAPEN_Services/rest/D3/DownloadIDEB";
        public static final String simpanIdebOjk = "NOS_PRAPEN_Services/rest/D3/SimpanIDEBSlikOJK";
        public static final String inquiryDataHutang = "NOS_PRAPEN_Services/rest/D3/InquiryDataUtang";
        public static final String updateDataHutang = "NOS_PRAPEN_Services/rest/D3/SimpanDataUtang";
        public static final String hapusDataHutang = "NOS_PRAPEN_Services/rest/D3/HapusDataUtang";
        public static final String lanjutPembiayaanKeVerifikator = "NOS_PRAPEN_Services/rest/D3/UpdateLanjutVerifikator";
        public static final String kembalikanPembiayaan = "NOS_PRAPEN_Services/rest/D3/UpdateBackIDEB";
        public static final String batalPembiayaan = "NOS_PRAPEN_Services/rest/D3/UpdateBatalPembiayaan";
        public static final String UpdateDataPendapatan = "NOS_PRAPEN_Services/rest/D3/UpdateDataPendapatanD3";
        public static final String inqueryDataPendapatan = "NOS_PRAPEN_Services/rest/D3/InquiryDataPendapatanD3";
        public static final String InquiryHasilCanvasing = "NOS_PRAPEN_Services/rest/D3/InquiryHasilCanvasingD3";
        public static final String UpdateJaminandanDokumen = "NOS_PRAPEN_Services/rest/D3/UpdateJaminandanDokumen";
        public static final String InquiryJaminandanDokumen = "NOS_PRAPEN_Services/rest/D3/InquiryJaminandanDokumen";
        public static final String InquiryTotalKualitasPemb = "NOS_PRAPEN_Services/rest/D4/InquiryTotalKualitasPemb";
        public static final String UpdateTotalKualitasPemb = "NOS_PRAPEN_Services/rest/D4/UpdateTotalKualitasPemb";
        public static final String InquiryDataPendapatanD4 = "NOS_PRAPEN_Services/rest/D4/InquiryDataPendapatanD4";

        //D4
        public static final String inquiryRac = "NOS_PRAPEN_Services/rest/D4/InquiryParamRAC";
        public static final String inquiryFitur = "NOS_PRAPEN_Services/rest/D4/InquiryParamFitur";
        public static final String inquiryVerifikasiIdeb = "NOS_PRAPEN_Services/rest/D4/InqIDebSlikOjkVerifikator";
        public static final String inquiryVerifikasiHutang = "NOS_PRAPEN_Services/rest/D4/InquiryDataUtangVerifikator";
        public static final String inquiryJangkaWaktu = "NOS_PRAPEN_Services/rest/D4/InquiryJangkaWaktu";
        public static final String inquiryKesimpulanVerifikasi = "NOS_PRAPEN_Services/rest/D4/InquiryVerifikasi";
        public static final String inquiryTempatKerja = "NOS_PRAPEN_Services/rest/D4/InquiryTempatKerjaVerifikator";
        public static final String lanjutPembiayaanKeOtorVerifikator = "NOS_PRAPEN_Services/rest/D4/UpdateLanjutOtorVerifikator";
        public static final String kembalikanPembiayaanVerifikator = "NOS_PRAPEN_Services/rest/D4/UpdateBackVerifikator";
        public static final String batalPembiayaanVerifikator = "NOS_PRAPEN_Services/rest/D4/UpdateBatalPembiayaanVerifikator";


        //D4.1
        public static final String lanjutPembiayaanKeD5 = "NOS_PRAPEN_Services/rest/D4/UpdateLanjutOtorVerifikator";
        public static final String kembalikanPembiayaanOtorVerifikator = "NOS_PRAPEN_Services/rest/D4/UpdateBackVerifikator";
        public static final String batalPembiayaanOtorVerifikator = "NOS_PRAPEN_Services/rest/D4/UpdateBatalPembiayaanVerifikator";

        //D5
        public static final String lanjutPembiayaanKePemutus = "NOS_PRAPEN_Services/rest/D4/UpdateLanjutOtorVerifikator";
        public static final String kembalikanPembiayaanOtorMarketing = "NOS_PRAPEN_Services/rest/D4/UpdateBackVerifikator";
        public static final String batalPembiayaanD5 = "NOS_PRAPEN_Services/rest/D4/UpdateBatalPembiayaanVerifikator";

        //D6
        public static final String lanjutPembiayaanKeAkad = "NOS_PRAPEN_Services/rest/D4/UpdateLanjutOtorVerifikator";
        public static final String kembalikanD6 = "NOS_PRAPEN_Services/rest/D4/UpdateBackVerifikator";
        public static final String batalPembiayaanD6 = "NOS_PRAPEN_Services/rest/D4/UpdateBatalPembiayaanVerifikator";

        //G1
        public static final String inquiryAkadMmq = "NOS_PRAPEN_Services/rest/G1/InqAkadMMQ";
        public static final String inquiryAkadMurabahahIjarah = "NOS_PRAPEN_Services/rest/G1/InqAkadMurabahahdanIjarah";
        public static final String updateAkadMurabahahIjarah = "NOS_PRAPEN_Services/rest/G1/UpdateAkadMurabahahdanIjarah";
        public static final String updateAkadMmq = "NOS_PRAPEN_Services/rest/G1/UpdateAkadMMQ";
        public static final String inquiryDataPejabat = "NOS_PRAPEN_Services/rest/G1/InqAkaddanAsesoir";
        public static final String updateDataPejabat = "NOS_PRAPEN_Services/rest/G1/UpdateAkaddanAsesoir";
        public static final String dropdownPejabat = "NOS_PRAPEN_Services/rest/G1/InqListPejabatAkad";
        public static final String dropdownSektorEkonomi = "NOS_PRAPEN_Services/rest/Picklist/SektorEkonomi";
        public static final String dropdownKodePekerjaan = "NOS_PRAPEN_Services/rest/Picklist/KodePekerjaan";
        public static final String dropdownBidangUsahaTempatKerja = "NOS_PRAPEN_Services/rest/Picklist/BidangUsahaTempatKerja";
        public static final String inquiryOjkBi = "NOS_PRAPEN_Services/rest/G1/InqOJKBI";
        public static final String updateDataOjkBi = "NOS_PRAPEN_Services/rest/G1/UpdateOJKBI";


        //api umum
        public static final String inquiryDetailAplikasi = "NOS_PRAPEN_Services/rest/APIUmum/InqDetailAplikasi";
        public static final String inquiryMemo = "NOS_PRAPEN_Services/rest/D3/InquiryMemo";
        public static final String updateMemo = "NOS_PRAPEN_Services/rest/D3/UpdateMemo";
    }
}


