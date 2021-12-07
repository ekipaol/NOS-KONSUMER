package com.application.bris.ikurma_nos_konsumer.api.service;

import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArr;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArrAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseArrAgunanByCif;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseDataDukcapil;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseDataInstansi;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseGmapsV3;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseSaldo;
import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.model.request.ReqFollowUpFlpp;
import com.application.bris.ikurma_nos_konsumer.api.model.request.ReqKodeProvinsi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.ReqKodeWilayah;
import com.application.bris.ikurma_nos_konsumer.api.model.request.ReqUid;
import com.application.bris.ikurma_nos_konsumer.api.model.request.ReqUidLong;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqAgunanByCif;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqAgunanData;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqDeleteAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqIkatAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqInfoAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqSaveAgunan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqSaveAgunanKendaraan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqSaveAgunanTanahKosong;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.ReqSetPengikatan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.kpr.ReqAppraisal;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.kpr.ReqKirimAppraisal;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.kpr.SelesaiAppraisal;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.listAppraisal;
import com.application.bris.ikurma_nos_konsumer.api.model.request.agunan.rejectAppraisal;
import com.application.bris.ikurma_nos_konsumer.api.model.request.ceknasabah.cekNasabah;
import com.application.bris.ikurma_nos_konsumer.api.model.request.flpp.ReqDataSikasep;
import com.application.bris.ikurma_nos_konsumer.api.model.request.flpp.ReqFotoFlpp;
import com.application.bris.ikurma_nos_konsumer.api.model.request.flpp.ReqLoginSikasep;
import com.application.bris.ikurma_nos_konsumer.api.model.request.flpp.ReqPihakKetiga;
import com.application.bris.ikurma_nos_konsumer.api.model.request.flpp.ReqRumahFlpp;
import com.application.bris.ikurma_nos_konsumer.api.model.request.flpp.ReqSimpanFollowUpFlpp;
import com.application.bris.ikurma_nos_konsumer.api.model.request.flpp.ReqSimpanKonfirmasiFlpp;
import com.application.bris.ikurma_nos_konsumer.api.model.request.flpp.ReqSimpanUpdateIdLokasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.general.Activation;
import com.application.bris.ikurma_nos_konsumer.api.model.request.general.Checkupdate;
import com.application.bris.ikurma_nos_konsumer.api.model.request.general.ListDeviasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.general.ReqFirebase;
import com.application.bris.ikurma_nos_konsumer.api.model.request.general.ReqRegisterBrisnotif;
import com.application.bris.ikurma_nos_konsumer.api.model.request.general.SimpanFeedback;
import com.application.bris.ikurma_nos_konsumer.api.model.request.general.home;
import com.application.bris.ikurma_nos_konsumer.api.model.request.general.login;
import com.application.bris.ikurma_nos_konsumer.api.model.request.general.searchAddress;
import com.application.bris.ikurma_nos_konsumer.api.model.request.general.searchListSektorEkonomi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.general.searchSektorEkonomi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.InputSektorEkonomiKpr;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.KonsumerKMGInputKelengkapanDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.KonsumerKPRKaryawanPnsInputKelengkapanDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.KonsumerKPRWiraswastaInputKelengkapanDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.Prescreening;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.ReqScoringKmg;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.SimpanDataFinansial;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.SimpanDataFinansialKpr;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.ValidasiDataFinansialKmg;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.cekRekomendasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inputAgunanDeposito;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inputAgunanKios;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inputHotprospek;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inputKelengkapanDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inputRemaksSlik;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inputScoring;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inputSektorEkonomi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryDataLengkap;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryHistory;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryHotprospek;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryIjk;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryKelengkapanDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryLkn;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryNikPasangan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.ReqIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquiryScoring;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.inquirySektorEkonomi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.kirimPutusanMikro;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.kirimPutusanMikroDeviasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.listHotprospek;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.rejectHotprospek;
import com.application.bris.ikurma_nos_konsumer.api.model.request.monitoring.ReqMonitoringNasabah;
import com.application.bris.ikurma_nos_konsumer.api.model.request.monitoring.ReqRankingAo;
import com.application.bris.ikurma_nos_konsumer.api.model.request.monitoring.ReqRiwayatMutasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.monitoring.ReqSaldoNasabah;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.KonsumerKMGInputPipeline;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.KonsumerKMGInquiryPipeline;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.KonsumerKPRInputPipeline;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.inputPipeline;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.inquiryInstansi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.inquiryListKateg;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.inquiryPipeline;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.inquiryProject;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.inquiryTujuan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.listPipeline;
import com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline.processRejectPipeline;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqAcctNumber;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqAkadAsesoir;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqBatalAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDataPejabat;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDedupe;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDocumentUmum;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqDownloadFile;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqHasilRekomendasiAkad;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqHitungKalkulator;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqInquery;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqKodeAo;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqLanjutHotprospek;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUidIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.DataPembiayaan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqUploadDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqValidasiDukcapil;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.ReqValidasiLngp;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.SearchSekEkonomi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.SimpanIdebOjk;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateDataInstansiDapen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateDataNasabah;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateDataOjkBi;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateDataPendapatan;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateIdebOjk;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateJaminandanDokumen;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UpdateMemo;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MParseArray;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.ParseResponseFile;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.ParseResponseReturn;
import com.application.bris.ikurma_nos_konsumer.database.pojo.AgunanKendaraanPojo;
import com.application.bris.ikurma_nos_konsumer.database.pojo.AgunanTanahBangunanPojo;
import com.application.bris.ikurma_nos_konsumer.database.pojo.DataLengkapPojo;
import com.application.bris.ikurma_nos_konsumer.database.pojo.LknPojo;
import com.application.bris.ikurma_nos_konsumer.model.hotprospek.KonsumerKprFlppInputKelengkapanDokumen;
import com.application.bris.ikurma_nos_konsumer.model.prapen.HapusDataHutang;
import com.application.bris.ikurma_nos_konsumer.model.prapen.ReqUpdateDataMarketing;
import com.application.bris.ikurma_nos_konsumer.model.prapen.UpdateDataHutang;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by PID on 4/5/2019.
 */

public interface ApiInterface {
    @POST(UriApi.general.login)
    Call<ParseResponse> login(@Body login login);

    @POST(UriApi.general.login2)
    Call<ParseResponse> login2(@Body login login);

    @POST(UriApi.general.home)
    Call<ParseResponse> home(@Body home home);

    @POST(UriApi.general.getProduct)
    Call<ParseResponse> getProduct(@Body EmptyRequest emptyRequest);

    @POST(UriApi.general.getProduct)
    Call<ParseResponse> getProductAmanah(@Body ReqUidLong ReqUidLong);

    @POST(UriApi.general.getKategSektorEkonomii)
    Call<ParseResponse> getKategSektorEkonomii(@Body EmptyRequest emptyRequest);

    @POST(UriApi.general.getKategSektorEkonomii)
    Call<ParseResponse> getKategSektorEkonomiiByGroup(@Body searchListSektorEkonomi searchListSektorEkonomi);

    @POST(UriApi.general.searcSektorEkonomi)
    Call<ParseResponse> searcSektorEkonomi(@Body searchSektorEkonomi searchSektorEkonomi);

    @POST(UriApi.general.listDeviasi)
    Call<ParseResponse> listDeviasi(@Body ListDeviasi listDeviasi);

    @POST(UriApi.general.activation)
    Call<ParseResponse> activation(@Body Activation activation);

    @POST(UriApi.general.checkUpdate)
    Call<ParseResponse> checkUpdate(@Body Checkupdate checkupdate);

    @GET(UriApi.general.geocoding + "pretty=1&limit=1")
    Call<ParseResponseGmapsV3> geocoding(@Query("address") String address, @Query("key") String key);

    @POST(UriApi.general.updateFirebase)
    Call<ParseResponse> updateFirebase(@Body ReqFirebase ReqFirebase);

    @POST(UriApi.general.brisnotifRegister)
    Call<ParseResponse> brisnotifRegister(@Body ReqRegisterBrisnotif ReqRegisterBrisnotif);

    /* ************** PIPELINE *************** */
    /* ************** PIPELINE *************** */
    @POST(UriApi.pipeline.listPipeline)
    Call<ParseResponseArr> listPipeline(@Body listPipeline listPipeline);

    @POST(UriApi.pipeline.inquiryPipeline)
    Call<ParseResponse> inquiryPipeline(@Body inquiryPipeline inquiryPipeline);

    @POST(UriApi.ceknasabah.cekNasabah)
    Call<ParseResponse> cekNasabah(@Body cekNasabah cekNasabah);

    @Multipart
    @POST(UriApi.pipeline.uploadFoto)
    Call<ParseResponse> uploadFoto(@Part MultipartBody.Part file);

    @POST(UriApi.pipeline.sendDataPipeline)
    Call<ParseResponse> sendDataPipeline(@Body inputPipeline inputPipeline);

    @POST(UriApi.pipeline.pipelineToHotprospek)
    Call<ParseResponse> pipelineToHotprospek(@Body processRejectPipeline processRejectPipeline);

    @POST(UriApi.pipeline.rejectPipeline)
    Call<ParseResponse> rejectPipeline(@Body processRejectPipeline processRejectPipeline);

    @POST(UriApi.pipeline.savePipelineHotprospek)
    Call<ParseResponse> savePipelineHotprospek(@Body inputPipeline inputPipeline);

    @POST(UriApi.general.searchAddress)
    Call<ParseResponseArr> searchAddress(@Body searchAddress searchAddress);


    @POST(UriApi.pipeline.getListInstansi)
    Call<ParseResponseArr> getListInstansi(@Body inquiryInstansi inquiryInstansi);

    @POST(UriApi.pipeline.listPipelineKonsumer)
    Call<ParseResponseArr> listPipelineKonsumer(@Body listPipeline listPipeline);

    @POST(UriApi.pipeline.inquiryPipelineKonsumer)
    Call<ParseResponse> inquiryPipelineKonsumer(@Body KonsumerKMGInquiryPipeline inquiryPipeline);

    @POST(UriApi.pipeline.sendDataPipelineKonsumer)
    Call<ParseResponse> sendDataPipelineKonsumer(@Body KonsumerKMGInputPipeline inputPipeline);

    @POST(UriApi.pipeline.pipelineToHotprospekKonsumer)
    Call<ParseResponse> pipelineToHotprospekKonsumer(@Body processRejectPipeline processRejectPipeline);

    @POST(UriApi.pipeline.rejectPipelineKonsumer)
    Call<ParseResponse> rejectPipelineKonsumer(@Body processRejectPipeline processRejectPipeline);

    @POST(UriApi.pipeline.savePipelineHotprospekKonsumer)
    Call<ParseResponse> savePipelineHotprospekKonsumer(@Body KonsumerKMGInputPipeline inputPipeline);


    /* **************** HOTPROSPEK ********************* */
    @POST(UriApi.hotprospek.listHotprospek)
    Call<ParseResponseArr> listHotprospek(@Body listHotprospek listHotprospek);

    @POST(UriApi.hotprospek.inquiryHotprospek)
    Call<ParseResponse> inquiryHotprospek(@Body inquiryHotprospek inquiryHotprospek);

    @POST(UriApi.hotprospek.sendDataHotprospek)
    Call<ParseResponse> sendDataHotprospek(@Body inputHotprospek inputHotprospek);

    @POST(UriApi.hotprospek.rejectHotprospek)
    Call<ParseResponse> rejectHotprospek(@Body rejectHotprospek rejectHotprospek);

    @POST(UriApi.hotprospek.rejectHotprospekKpr)
    Call<ParseResponse> rejectHotprospekKpr(@Body rejectHotprospek rejectHotprospek);

//    @POST(UriApi.hotprospek.inquiryDataLengkap)
//    Call<ParseResponse> inquiryDataLengkap (@Body inquiryDataLengkap inquiryDataLengkap);

    @POST(UriApi.hotprospek.sendDataLengkap)
    Call<ParseResponse> sendDataLengkap(@Body DataLengkapPojo dataLengkapPojo);

    @POST(UriApi.flpp.sendDataLengkapFlpp)
    Call<ParseResponse> sendDataLengkapFlpp(@Body DataLengkapPojo dataLengkapPojo);

    @POST(UriApi.hotprospek.inquiryHistory)
    Call<ParseResponse> inquiryHistory(@Body inquiryHistory inquiryHistory);

    @POST(UriApi.hotprospek.inquirySektorEkonomi)
    Call<ParseResponse> inquirySektorEkonomi(@Body inquirySektorEkonomi inquirySektorEkonomi);

    @POST(UriApi.hotprospek.sendDataSektorEkonomi)
    Call<ParseResponse> sendDataSektorEkonomi(@Body inputSektorEkonomi inputSektorEkonomi);

    @POST(UriApi.hotprospek.hitungRPC)
    Call<ParseResponse> hitungRPC(@Body ReqIdAplikasi ReqIdAplikasi);

    @POST(UriApi.hotprospek.inquiryRPC)
    Call<ParseResponse> inquiryRPC(@Body ReqIdAplikasi ReqIdAplikasi);

    @POST(UriApi.hotprospek.inquiryKelengkapanDokumen)
    Call<ParseResponse> inquiryKelengkapanDokumen(@Body inquiryKelengkapanDokumen inquiryKelengkapanDokumen);

    @POST(UriApi.hotprospek.sendKelengkapanDokumen)
    Call<ParseResponse> sendKelengkapanDokumen(@Body inputKelengkapanDokumen inputKelengkapanDokumen);

    @POST(UriApi.hotprospek.cekRekomendasi)
    Call<ParseResponse> cekRekomendasi(@Body cekRekomendasi cekRekomendasi);

    @POST(UriApi.hotprospek.inquiryLKN)
    Call<ParseResponse> inquiryLKN(@Body inquiryLkn inquiryLkn);

    @POST(UriApi.hotprospek.sendLkn)
    Call<ParseResponse> sendLkn(@Body LknPojo lknPojo);

    @POST(UriApi.hotprospek.cekSikp)
    Call<ParseResponse> cekSikp(@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.cekDHNKonsumer)
    Call<ParseResponse> cekDHNKonsumer(@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.cekDukcapilKonsumer)
    Call<ParseResponse> cekDukcapilKonsumer(@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.cekSlikKonsumer)
    Call<ParseResponse> cekSlikKonsumer(@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.inquiryPrescreeningKonsumer)
    Call<ParseResponse> inquiryPrescreeningKonsumer(@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.sendPrescreeningKonsumer)
    Call<ParseResponse> sendPrescreeningKonsumer(@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.downloadSlikKonsumer)
    Call<ParseResponse> downloadSlikKonsumer(@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.downloadSlikPasanganKonsumer)
    Call<ParseResponse> downloadSlikPasanganKonsumer(@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.inquiryRemaksSlikKonsumer)
    Call<ParseResponse> inquiryRemaksSlikKonsumer(@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.sendRemaksSlikKonsumer)
    Call<ParseResponse> sendRemaksSlikKonsumer(@Body inputRemaksSlik inputRemaksSlik);


    @POST(UriApi.hotprospek.inquiryScoring)
    Call<ParseResponse> inquiryScoring(@Body inquiryScoring inquiryScoring);

    @POST(UriApi.hotprospek.sendScoring)
    Call<ParseResponse> sendScoring(@Body inputScoring inputScoring);

    @POST(UriApi.hotprospek.sendPutusanMikro)
    Call<ParseResponse> sendPutusanMikro(@Body kirimPutusanMikro kirimPutusanMikro);

    @POST(UriApi.hotprospek.sendPutusanMikroDeviasi)
    Call<ParseResponse> sendPutusanMikroDeviasi(@Body kirimPutusanMikroDeviasi kirimPutusanMikroDeviasi);

    //AGUNAN


    @POST(UriApi.hotprospek.listAgunan)
    Call<ParseResponseArrAgunan> listAgunan(@Body ReqAgunan ReqAgunan);

    @POST(UriApi.hotprospek.listAgunanAll)
    Call<ParseResponseArrAgunanByCif> listAgunanAll(@Body ReqAgunanByCif ReqAgunanByCif);

    @POST(UriApi.hotprospek.inquiryAgunanTanahBangunan)
    Call<ParseResponse> inquiryAgunanTanahBangunan(@Body ReqAgunanData reqAgunanData);

    @POST(UriApi.hotprospek.sendAgunanTanahBangunan)
    Call<ParseResponse> sendAgunanTanahBangunan(@Body AgunanTanahBangunanPojo agunanTanahBangunanPojo);

    @POST(UriApi.hotprospek.inquiryInfoAgunan)
    Call<ParseResponse> inquiryInfoAgunan(@Body ReqInfoAgunan ReqInfoAgunan);

    @POST(UriApi.hotprospek.deleteAgunan)
    Call<ParseResponse> deleteAgunan(@Body ReqDeleteAgunan ReqDeleteAgunan);

    @POST(UriApi.hotprospek.setPengikatan)
    Call<ParseResponse> setPengikatan(@Body ReqSetPengikatan ReqSetPengikatan);

    @POST(UriApi.hotprospek.ikatAgunan)
    Call<ParseResponse> ikatAgunan(@Body ReqIkatAgunan ReqIkatAgunan);

    @POST(UriApi.hotprospek.jenisKlasifikasi)
    Call<ParseResponseArr> jenisKlasifikasi(@Body EmptyRequest EmptyRequest);

    @POST(UriApi.hotprospek.saveAgunanTerikat)
    Call<ParseResponse> saveAgunanTerikat(@Body ReqSaveAgunan ReqSaveAgunan);


    @POST(UriApi.hotprospek.inquiryKendaraan)
    Call<ParseResponseAgunan> inquiryKendaraan(@Body ReqAgunanData ReqAgunanData);

    @POST(UriApi.hotprospek.saveKendaraan)
    Call<ParseResponseAgunan> saveKendaraan(@Body ReqSaveAgunanKendaraan ReqSaveAgunanKendaraan);


    @POST(UriApi.hotprospek.inquiryAgunanKios)
    Call<ParseResponseArr> inquiryAgunanKios(@Body ReqAgunanData reqAgunanData);

    @POST(UriApi.hotprospek.sendAgunanKios)
    Call<ParseResponse> sendAgunanKios(@Body inputAgunanKios inputAgunanKios);


    @POST(UriApi.hotprospek.inquiryAgunanTanahKosong)
    Call<ParseResponseArr> inquiryAgunanTanahKosong(@Body ReqAgunanData reqAgunanData);

    @POST(UriApi.hotprospek.saveTanahKosong)
    Call<ParseResponse> saveTanahKosong(@Body ReqSaveAgunanTanahKosong ReqSaveAgunanTanahKosong);

    @POST(UriApi.hotprospek.inquiryAgunanDeposito)
    Call<ParseResponse> inquiryAgunanDeposito(@Body ReqAgunanData reqAgunanData);

    @POST(UriApi.hotprospek.sendAgunanDeposito)
    Call<ParseResponse> sendAgunanDeposito(@Body inputAgunanDeposito inputAgunanDeposito);

    @POST(UriApi.hotprospek.inquiryAgunanKendaraan)
    Call<ParseResponse> inquiryAgunanKendaraan(@Body ReqAgunanData reqAgunanData);

    @POST(UriApi.hotprospek.sendAgunanKendaraan)
    Call<ParseResponse> sendAgunanKendaraan(@Body AgunanKendaraanPojo agunanKendaraanPojo);

    @POST(UriApi.hotprospek.requestAppraisal)
    Call<ParseResponse> requestAppraisal(@Body ReqAppraisal ReqAppraisal);


    /* **************** APPROVED ********************* */
    @POST(UriApi.approved.listApproved)
    Call<ParseResponseArr> listApproved(@Body listHotprospek listHotprospek);

    /* **************** REJECTED ********************* */
    @POST(UriApi.rejected.listRejected)
    Call<ParseResponseArr> listRejected(@Body listHotprospek listHotprospek);


    //KONSUMER KPR

    @POST(UriApi.hotprospek.inquiryHotprospekKpr)
    Call<ParseResponse> inquiryHotprospekKpr(@Body inquiryHotprospek inquiryHotprospek);

    @POST(UriApi.hotprospek.inquiryDataLengkap)
    Call<ParseResponse> inquiryDataLengkap(@Body inquiryDataLengkap inquiryDataLengkap);

    @POST(UriApi.hotprospek.sendDataLengkapKpr)
    Call<ParseResponse> sendDataLengkapKpr(@Body DataLengkapPojo dataLengkapPojo);

    @POST(UriApi.hotprospek.listHotprospekKpr)
    Call<ParseResponseArr> listHotprospekKpr(@Body listHotprospek listHotprospek);

    @POST(UriApi.hotprospek.inquiryPrescreeningKpr)
    Call<ParseResponse> inquiryPrescreeningKpr(@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.cekDHNKpr)
    Call<ParseResponse> cekDHNKpr(@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.cekDukcapilKpr)
    Call<ParseResponse> cekDukcapilKpr(@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.cekSlikKpr)
    Call<ParseResponse> cekSlikKpr(@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.sendPrescreeningKpr)
    Call<ParseResponse> sendPrescreeningKpr(@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.downloadSlikKpr)
    Call<ParseResponse> downloadSlikKpr(@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.downloadSlikPasanganKpr)
    Call<ParseResponse> downloadSlikPasanganKpr(@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.inquiryRemaksSlikKpr)
    Call<ParseResponse> inquiryRemaksSlikKpr(@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.sendRemaksSlikKpr)
    Call<ParseResponse> sendRemaksSlikKpr(@Body inputRemaksSlik inputRemaksSlik);

    @POST(UriApi.pipeline.listPipelineKpr)
    Call<ParseResponseArr> listPipelineKpr(@Body listPipeline listPipeline);

    @POST(UriApi.pipeline.inquiryPipelineKpr)
    Call<ParseResponse> inquiryPipelineKpr(@Body KonsumerKMGInquiryPipeline inquiryPipeline);

    @POST(UriApi.pipeline.listPihakKetiga)
    Call<ParseResponseArr> listPihakKetiga(@Body inquiryProject inquiryProject);

    @POST(UriApi.pipeline.listProject)
    Call<ParseResponseArr> listProject(@Body inquiryProject inquiryProject);

    @POST(UriApi.pipeline.listProgramKpr)
    Call<ParseResponseArr> listProgramKpr(@Body EmptyRequest EmptyRequest);


    @POST(UriApi.pipeline.listTujuanPenggunaanKpr)
    Call<ParseResponseArr> listTujuanPenggunaanKpr(@Body inquiryInstansi inquiryInstansi);

    @POST(UriApi.pipeline.listBidangPekerjaan)
    Call<ParseResponseArr> listBidangPekerjaan(@Body EmptyRequest emptyRequest);

    @POST(UriApi.pipeline.listJenisKPR)
    Call<ParseResponseArr> listJenisKPR(@Body EmptyRequest emptyRequest);

    @POST(UriApi.pipeline.sendDataPipelineKpr)
    Call<ParseResponse> sendDataPipelineKpr(@Body KonsumerKPRInputPipeline inputPipeline);

    @POST(UriApi.pipeline.savePipelineHotprospekKpr)
    Call<ParseResponse> savePipelineHotprospekKpr(@Body KonsumerKPRInputPipeline inputPipeline);

    @POST(UriApi.flpp.savePipelineHotprospekFlpp)
    Call<ParseResponse> savePipelineHotprospekFlpp(@Body KonsumerKPRInputPipeline inputPipeline);

    @POST(UriApi.hotprospek.listJenisPekerjaan)
    Call<ParseResponse> listJenisPekerjaan(@Body EmptyRequest emptyRequest);

    @POST(UriApi.flpp.listJenisPekerjaanFlpp)
    Call<ParseResponse> listJenisPekerjaanFlpp(@Body EmptyRequest emptyRequest);

    @POST(UriApi.hotprospek.inquiryDataFinansialKpr)
    Call<ParseResponse> inquiryDataFinansialKpr(@Body ReqIdAplikasi ReqIdAplikasi);

    @POST(UriApi.flpp.inquiryDataFinansialKprFlpp)
    Call<ParseResponse> inquiryDataFinansialKprFlpp(@Body ReqIdAplikasi ReqIdAplikasi);

    @POST(UriApi.hotprospek.simpanDataFinansialKpr)
    Call<ParseResponse> simpanDataFinansialKpr(@Body SimpanDataFinansialKpr SimpanDataFinansialKpr);

    @POST(UriApi.hotprospek.validasiDataFinansialKpr)
    Call<ParseResponse> validasiDataFinansialKpr(@Body ValidasiDataFinansialKmg ValidasiDataFinansialKmg);

    @POST(UriApi.flpp.simpanDataFinansialFlpp)
    Call<ParseResponse> simpanDataFinansialKprFlpp(@Body SimpanDataFinansialKpr SimpanDataFinansialKpr);

    @POST(UriApi.flpp.validasiDataFinansialFlpp)
    Call<ParseResponse> validasiDataFinansialKprFlpp(@Body ValidasiDataFinansialKmg ValidasiDataFinansialKmg);

    @POST(UriApi.flpp.listRumahFlpp)
    Call<ParseResponseArr> listRumahFlpp(@Body ReqRumahFlpp ReqRumahFlpp);

    @POST(UriApi.flpp.getBlokByIdLokasi)
    Call<ParseResponseArr> getBlokByIdLokasi(@Body ReqRumahFlpp ReqRumahFlpp);

    @POST(UriApi.hotprospek.updateScoringKpr)
    Call<ParseResponse> updateScoringKpr(@Body ReqScoringKmg ReqScoringKmg);

    @POST(UriApi.flpp.updateScoringFlpp)
    Call<ParseResponse> updateScoringFlpp(@Body ReqScoringKmg ReqScoringKmg);

    @POST(UriApi.hotprospek.inquiryKelengkapanDokumenKpr)
    Call<ParseResponse> inquiryKelengkapanDokumenKpr(@Body inquiryKelengkapanDokumen inquiryKelengkapanDokumen);

    @POST(UriApi.flpp.inquiryKelengkapanDokumenFlpp)
    Call<ParseResponse> inquiryKelengkapanDokumenFlpp(@Body inquiryKelengkapanDokumen inquiryKelengkapanDokumen);

    @POST(UriApi.hotprospek.sendKelengkapanDokumenKprKaryawanPns)
    Call<ParseResponse> sendKelengkapanDokumenKprKaryawanPns(@Body KonsumerKPRKaryawanPnsInputKelengkapanDokumen KonsumerKPRKaryawanPnsInputKelengkapanDokumen);

    @POST(UriApi.flpp.sendKelengkapanDokumenKprFlpp)
    Call<ParseResponse> sendKelengkapanDokumenKprFlpp(@Body KonsumerKprFlppInputKelengkapanDokumen KonsumerKprFlppInputKelengkapanDokumen);

    @POST(UriApi.hotprospek.sendKelengkapanDokumenKprKaryawanPns)
    Call<ParseResponse> sendKelengkapanDokumenKprWiraswasta(@Body KonsumerKPRWiraswastaInputKelengkapanDokumen KonsumerKPRWiraswastaInputKelengkapanDokumen);

    @POST(UriApi.hotprospek.inquirySektorEkonomiKpr)
    Call<ParseResponse> inquirySektorEkonomiKpr(@Body inquirySektorEkonomi inquirySektorEkonomi);

    @POST(UriApi.flpp.inquirySektorEkonomiFlpp)
    Call<ParseResponse> inquirySektorEkonomiFlpp(@Body inquirySektorEkonomi inquirySektorEkonomi);

    @POST(UriApi.hotprospek.sendDataSektorEkonomiKpr)
    Call<ParseResponse> sendDataSektorEkonomiKpr(@Body InputSektorEkonomiKpr InputSektorEkonomiKpr);

    @POST(UriApi.hotprospek.getKategSektorEkonomiKpr)
    Call<ParseResponse> getKategSektorEkonomiiByGroupKpr(@Body searchListSektorEkonomi searchListSektorEkonomi);

    @POST(UriApi.hotprospek.searcSektorEkonomiKpr)
    Call<ParseResponse> searcSektorEkonomiKpr(@Body searchSektorEkonomi searchSektorEkonomi);

    @POST(UriApi.pipeline.pipelineToHotprospekKpr)
    Call<ParseResponse> pipelineToHotprospekKpr(@Body processRejectPipeline processRejectPipeline);

    @POST(UriApi.pipeline.rejectPipelineKpr)
    Call<ParseResponse> rejectPipelineKpr(@Body processRejectPipeline processRejectPipeline);

    @POST(UriApi.hotprospek.searchPasar)
    Call<ParseResponseArr> searchPasar(@Body searchAddress searchAddress);

    @POST(UriApi.hotprospek.listJenisBangunan)
    Call<ParseResponseArr> listJenisBangunan(@Body EmptyRequest emptyRequest);

    //AO SILANG
    @POST(UriApi.hotprospek.listPenilaianAgunan)
    Call<ParseResponse> listPenilaianAgunan(@Body listAppraisal listAppraisal);

    @POST(UriApi.hotprospek.rejectAppraisal)
    Call<ParseResponse> rejectAppraisal(@Body rejectAppraisal rejectAppraisal);

    @POST(UriApi.hotprospek.selesaiAppraisal)
    Call<ParseResponse> selesaiAppraisal(@Body SelesaiAppraisal SelesaiAppraisal);

    @POST(UriApi.hotprospek.sendPutusanKpr)
    Call<ParseResponse> sendPutusanKpr(@Body kirimPutusanMikro kirimPutusanMikro);

    @POST(UriApi.hotprospek.kirimApraisal)
    Call<ParseResponse> kirimApraisal(@Body ReqKirimAppraisal reqKirimAppraisal);

    //MONITORING
    @POST(UriApi.hotprospek.listMonitoringNasabah)
    Call<ParseResponse> listMonitoringNasabah(@Body ReqMonitoringNasabah ReqMonitoringNasabah);

    @POST(UriApi.hotprospek.listMonitoringNasabahNpf)
    Call<ParseResponse> listMonitoringNasabahNpf(@Body ReqMonitoringNasabah ReqMonitoringNasabah);


    @POST(UriApi.hotprospek.getSaldoNasabah)
    Call<ParseResponseSaldo> getSaldoNasabah(@Body ReqSaldoNasabah ReqSaldoNasabah);

    @POST(UriApi.hotprospek.inquiryRekeningAmanah)
    Call<ParseResponse> inquiryRekeningAmanah(@Body ReqSaldoNasabah ReqSaldoNasabah);

    @POST(UriApi.hotprospek.rankingAoTop)
    Call<ParseResponseArr> getRankingAoTop(@Body ReqRankingAo ReqRankingAo);

    @POST(UriApi.hotprospek.rankingAoBottom)
    Call<ParseResponseArr> getRankingAoBottom(@Body ReqRankingAo ReqRankingAo);

    @POST(UriApi.hotprospek.getRiwayatMutasi)
    Call<ParseResponse> getRiwayatMutasi(@Body ReqRiwayatMutasi ReqRiwayatMutasi);

    @POST(UriApi.hotprospek.getRataRata)
    Call<ParseResponse> getRataRata(@Body EmptyRequest EmptyRequest);


    //END OF KONSUMER KPR


    //pakai inquiry rpc karena dia sama sama request id Aplikasi
    @POST(UriApi.hotprospek.inquiryDataFinansial)
    Call<ParseResponseDataInstansi> inquiryDataFinansial(@Body ReqIdAplikasi ReqIdAplikasi);

    @POST(UriApi.hotprospek.simpanDataFinansial)
    Call<ParseResponse> simpanDataFinansial(@Body SimpanDataFinansial SimpanDataFinansial);

    @POST(UriApi.hotprospek.validasiDataFinansial)
    Call<ParseResponse> validasiDataFinansial(@Body ValidasiDataFinansialKmg ValidasiDataFinansialKmg);


    @POST(UriApi.hotprospek.inquiryScoringKmg)
    Call<ParseResponse> inquiryScoringKmg(@Body ReqScoringKmg ReqScoringKmg);

    @POST(UriApi.hotprospek.updateScoringKmg)
    Call<ParseResponse> updateScoringKmg(@Body ReqScoringKmg ReqScoringKmg);

    @POST(UriApi.general.simpanFeedback)
    Call<ParseResponse> simpanFeedback(@Body SimpanFeedback SimpanFeedback);

    @POST(UriApi.hotprospek.inquiryKelengkapanDokumenKonsumer)
    Call<ParseResponse> inquiryKelengkapanDokumenKonsumer(@Body inquiryKelengkapanDokumen inquiryKelengkapanDokumen);

    @POST(UriApi.hotprospek.sendKelengkapanDokumenKonsumer)
    Call<ParseResponse> sendKelengkapanDokumenKonsumer(@Body KonsumerKMGInputKelengkapanDokumen inputKelengkapanDokumen);

    @POST(UriApi.hotprospek.sendPutusanKmg)
    Call<ParseResponse> sendPutusanKmg(@Body kirimPutusanMikro kirimPutusanMikro);

    @POST(UriApi.hotprospek.cekNikPasangan)
    Call<ParseResponseDataDukcapil> cekNikPasangan(@Body inquiryNikPasangan inquiryNikPasangan);

    @POST(UriApi.hotprospek.listAsuransi)
    Call<ParseResponseArr> listAsuransi(@Body EmptyRequest emptyRequest);

    @POST(UriApi.hotprospek.inquiryIjk)
    Call<ParseResponse> inquiryIjk(@Body inquiryIjk inquiryIjk);

    @POST(UriApi.pipeline.getTujuanPenggunaan)
    Call<ParseResponseArr> getTujuanPenggunaan(@Body inquiryTujuan inquiryTujuan);

    @Multipart
    @POST(UriApi.hotprospek.uploadFile)
    Call<ParseResponse> uploadFile(@Part MultipartBody.Part file);


    //KONSUMER PURNA
    @POST(UriApi.hotprospek.inquiryDataLengkapKpr)
    Call<ParseResponse> inquiryDataLengkapKpr(@Body inquiryDataLengkap inquiryDataLengkap);


    //Purna
    @POST(UriApi.pipeline.listProgram)
    Call<ParseResponseArr> getListProgram(@Body EmptyRequest emptyRequest);

    @POST(UriApi.pipeline.listInstitusi)
    Call<ParseResponseArr> listInstitusi(@Body inquiryTujuan inquiryTujuan);

    @POST(UriApi.pipeline.listRekananDM)
    Call<ParseResponseArr> getListRekananDM(@Body EmptyRequest emptyRequest);

    @POST(UriApi.pipeline.listKategNasabah)
    Call<ParseResponseArr> getListKategNasabah(@Body inquiryListKateg inquiryListKateg);

    //FLPP
    @POST(UriApi.flpp.listFollowupFlpp)
    Call<ParseResponseArr> listFollowupFlpp(@Body ReqFollowUpFlpp ReqFollowUpFlpp);

    @POST(UriApi.flpp.listKonfirmasiFlpp)
    Call<ParseResponseArr> listKonfirmasiFlpp(@Body ReqUid ReqUid);

    @POST(UriApi.flpp.listTelahKonfirmasiFlpp)
    Call<ParseResponseArr> listTelahKonfirmasiFlpp(@Body ReqUid ReqUid);


    @POST(UriApi.flpp.listKotaKabupatenFlpp)
    Call<ParseResponseArr> listKotaKabupatenFlpp(@Body ReqKodeProvinsi ReqKodeProvinsi);

    @POST(UriApi.flpp.listKelurahanFlpp)
    Call<ParseResponseArr> listKelurahanFlpp(@Body ReqKodeWilayah ReqKodeWilayah);

    @POST(UriApi.flpp.getPihakketiga)
    Call<ParseResponse> getPihakketiga(@Body ReqPihakKetiga ReqPihakKetiga);

    @POST(UriApi.flpp.getDataSikasep)
    Call<ParseResponse> getDataSikasep(@Body ReqDataSikasep ReqDataSikasep);

    @POST(UriApi.flpp.sendAgunanTanahBangunanFlpp)
    Call<ParseResponse> sendAgunanTanahBangunanFlpp(@Body AgunanTanahBangunanPojo agunanTanahBangunanPojo);


    @POST(UriApi.flpp.inquiryKodeWilayah)
    Call<ParseResponse> inquiryKodeWilayah(@Body ReqIdAplikasi ReqIdAplikasi);

    @POST(UriApi.flpp.getFotoFlpp)
    Call<ParseResponse> getFotoFlpp(@Body ReqFotoFlpp ReqFotoFlpp);

    @POST(UriApi.flpp.cekSikasepByKtp)
    Call<ParseResponse> cekSikasepByKtp(@Body ReqFotoFlpp ReqFotoFlpp);

    @POST(UriApi.flpp.getDataUpdateIdLokasi)
    Call<ParseResponse> getDataUpdateIdLokasi(@Body ReqIdAplikasi ReqIdAplikasi);

    @POST(UriApi.flpp.updateIdLokasiFlpp)
    Call<ParseResponse> updateIdLokasiFlpp(@Body ReqSimpanUpdateIdLokasi ReqSimpanUpdateIdLokasi);

    @POST(UriApi.flpp.loginSikasep)
    Call<ParseResponse> loginSikasep(@Body ReqLoginSikasep ReqLoginSikasep);

    @POST(UriApi.flpp.simpanFollowUpFlpp)
    Call<ParseResponse> simpanFollowUpFlpp(@Body ReqSimpanFollowUpFlpp ReqSimpanFollowUpFlpp);

    @POST(UriApi.flpp.simpanKonfirmasiFlpp)
    Call<ParseResponse> simpanKonfirmasiFlpp(@Body ReqSimpanKonfirmasiFlpp ReqSimpanKonfirmasiFlpp);

    @POST(UriApi.flpp.updatePipelineFlpp)
    Call<ParseResponse> updatePipelineFlpp(@Body ReqSimpanKonfirmasiFlpp ReqSimpanKonfirmasiFlpp);

    @POST(UriApi.flpp.sendDataPipelineKprFlpp)
    Call<ParseResponse> sendDataPipelineKprFlpp(@Body KonsumerKPRInputPipeline inputPipeline);


    //GADAI
    @POST(UriApi.prapen.dropdownTipeProduk)
    Call<ParseResponseArr> dropdownTipeProduk(@Body EmptyRequest EmptyRequest);

    @POST(UriApi.prapen.dropdownSegmen)
    Call<ParseResponseArr> dropdownSegmen(@Body EmptyRequest EmptyRequest);

    @POST(UriApi.prapen.dropdownJenisPembiayaan)
    Call<ParseResponseArr> dropdownJenisPembiayaan(@Body EmptyRequest EmptyRequest);

    @POST(UriApi.prapen.dropdownTujuanPembiayaan)
    Call<ParseResponseArr> dropdownTujuanPembiayaan(@Body EmptyRequest EmptyRequest);

    @POST(UriApi.prapen.dropdownProgram)
    Call<ParseResponseArr> dropdownProgram(@Body EmptyRequest EmptyRequest);

    @POST(UriApi.prapen.cekHasilRekomendasiAkad)
    Call<ParseResponseArr> cekHasilRekomendasiAkad(@Body ReqHasilRekomendasiAkad ReqHasilRekomendasiAkad);

    @POST(UriApi.prapen.updateDataPembiayaan)
    Call<ParseResponse> updateDataPembiayaan(@Body DataPembiayaan DataPembiayaan);

    @POST(UriApi.prapen.inquiryDataPembiayaan)
    Call<ParseResponseArr> inquiryDataPembiayaan(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.listAplikasiMarketing)
    Call<ParseResponseArr> listAplikasiMarketing(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.inquiryDedupe)
    Call<ParseResponseArr> inquiryDedupe(@Body ReqDedupe ReqDedupe);

    @POST(UriApi.prapen.updateDedupe)
    Call<ParseResponse> updateDedupe(@Body ReqDedupe ReqDedupe);

    @POST(UriApi.prapen.updateDataNasabah)
    Call<ParseResponse> updateDataNasabah(@Body UpdateDataNasabah UpdateDataNasabah);

    @POST(UriApi.prapen.inquiryDataNasabah)
    Call<ParseResponse> inquiryDataNasabah(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.inquiryDataInstansiDapen)
    Call<ParseResponseArr> inquiryDataInstansiDapen(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.updateDataInstansiDapen)
    Call<ParseResponse> updateDataInstansiDapen(@Body UpdateDataInstansiDapen UpdateDataInstansiDapen);

    @POST(UriApi.prapen.dropdownLembagaPengelolaPensiun)
    Call<ParseResponseArr> dropdownLembagaPengelolaPensiun(@Body EmptyRequest EmptyRequest);

    @POST(UriApi.prapen.dropdownTreatmentRekening)
    Call<ParseResponseArr> dropdownTreatmentRekening(@Body EmptyRequest EmptyRequest);

    @POST(UriApi.prapen.dropdownMitraFronting)
    Call<ParseResponseArr> dropdownMitraFronting(@Body EmptyRequest EmptyRequest);

    @POST(UriApi.prapen.dropdownPejabat)
    Call<ParseResponseArr> dropdownPejabat(@Body EmptyRequest EmptyRequest);

    @POST(UriApi.prapen.validasiPayroll)
    Call<ParseResponse> validasiPayroll(@Body ReqAcctNumber ReqAcctNumber);

    @POST(UriApi.prapen.validasiLngp)
    Call<ParseResponse> validasiLngp(@Body ReqValidasiLngp ReqValidasiLngp);

    @POST(UriApi.prapen.inquiryDetailAplikasi)
    Call<ParseResponse> inquiryDetailAplikasi(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.validasiDukcapil)
    Call<ParseResponse> validasiDukcapil(@Body ReqValidasiDukcapil ReqValidasiDukcapil);

    @POST(UriApi.prapen.inquiryNamaKodeAo)
    Call<ParseResponse> inquiryNamaKodeAo(@Body ReqKodeAo ReqKodeAo);

    @POST(UriApi.prapen.updateDataMarketing)
    Call<ParseResponse> updateDataMarketing(@Body ReqUpdateDataMarketing ReqUpdateDataMarketing);

    @POST(UriApi.prapen.inquiryDataMarketing)
    Call<ParseResponseArr> inquiryDataMarketing(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.lanjutHotprospek)
    Call<ParseResponse> lanjutHotprospek(@Body ReqLanjutHotprospek ReqLanjutHotprospek);

    @POST(UriApi.prapen.inquiryIdebOjk)
    Call<ParseResponse> inquiryIdebOjk(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.updateIdebOjk)
    Call<ParseResponse> updateIdebOjk(@Body UpdateIdebOjk UpdateIdebOjk);

    @POST(UriApi.prapen.downloadIdeb)
    Call<ParseResponse> downloadIdeb(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.simpanIdebOjk)
    Call<ParseResponse> simpanIdebOjk(@Body SimpanIdebOjk SimpanIdebOjk);

    @POST(UriApi.prapen.inquiryDataHutang)
    Call<ParseResponseArr> inquiryDataHutang(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.updateDataHutang)
    Call<ParseResponse> updateDataHutang(@Body UpdateDataHutang UpdateDataHutang);

    @POST(UriApi.prapen.hapusDataHutang)
    Call<ParseResponse> hapusDataHutang(@Body HapusDataHutang HapusDataHutang);

    @POST(UriApi.prapen.inquiryRac)
    Call<ParseResponse> inquiryRac(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.inquiryFitur)
    Call<ParseResponse> inquiryFitur(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.inquiryMemo)
    Call<ParseResponseArr> inquiryMemo(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.updateMemo)
    Call<ParseResponse> updateMemo(@Body UpdateMemo UpdateMemo);

    @POST(UriApi.prapen.lanjutPembiayaanKeVerifikator)
    Call<ParseResponse> lanjutPembiayaanKeVerifikator(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.batalPembiayaan)
    Call<ParseResponse> batalPembiayaan(@Body ReqBatalAplikasi ReqBatalAplikasi);

    @POST(UriApi.prapen.kembalikanPembiayaan)
    Call<ParseResponse> kembalikanPembiayaan(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.inquiryVerifikasiIdeb)
    Call<ParseResponse> inquiryVerifikasiIdeb(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.inquiryVerifikasiHutang)
    Call<ParseResponseArr> inquiryVerifikasiHutang(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.inquiryJangkaWaktu)
    Call<ParseResponse> inquiryJangkaWaktu(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.inquiryKesimpulanVerifikasi)
    Call<ParseResponse> inquiryKesimpulanVerifikasi(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.inquiryTempatKerja)
    Call<ParseResponse> inquiryTempatKerja(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.lanjutPembiayaanKeOtorVerifikator)
    Call<ParseResponse> lanjutPembiayaanKeOtorVerifikator(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.batalPembiayaanVerifikator)
    Call<ParseResponse> batalPembiayaanVerifikator(@Body ReqBatalAplikasi ReqBatalAplikasi);

    @POST(UriApi.prapen.kembalikanPembiayaanVerifikator)
    Call<ParseResponse> kembalikanPembiayaanVerifikator(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.lanjutPembiayaanKeD5)
    Call<ParseResponse> lanjutPembiayaanKeD5(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.batalPembiayaanOtorVerifikator)
    Call<ParseResponse> batalPembiayaanOtorVerifikator(@Body ReqBatalAplikasi ReqBatalAplikasi);

    @POST(UriApi.prapen.kembalikanPembiayaanOtorVerifikator)
    Call<ParseResponse> kembalikanPembiayaanOtorVerifikator(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.lanjutPembiayaanKePemutus)
    Call<ParseResponse> lanjutPembiayaanKePemutus(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.batalPembiayaanD5)
    Call<ParseResponse> batalPembiayaanD5(@Body ReqBatalAplikasi ReqBatalAplikasi);

    @POST(UriApi.prapen.kembalikanPembiayaanOtorMarketing)
    Call<ParseResponse> kembalikanPembiayaanOtorMarketing(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.lanjutPembiayaanKeAkad)
    Call<ParseResponse> lanjutPembiayaanKeAkad(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.batalPembiayaanD6)
    Call<ParseResponse> batalPembiayaanD6(@Body ReqBatalAplikasi ReqBatalAplikasi);

    @POST(UriApi.prapen.kembalikanD6)
    Call<ParseResponse> kembalikanD6(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.inqueryDataPendapatan)
    Call<ParseResponseAgunan> inqueryDataPendapatan(@Body ReqInquery ReqInquery);

    @POST(UriApi.prapen.UpdateDataPendapatan)
    Call<ParseResponseReturn> UpdateDataPendapatan(@Body UpdateDataPendapatan UpdateDataPendapatan);

    @POST(UriApi.prapen.InquiryDataPendapatanD4)
    Call<ParseResponseAgunan> InquiryDataPendapatanD4(@Body ReqInquery ReqInquery);

    @POST(UriApi.prapen.InquiryTotalKualitasPemb)
    Call<ParseResponseAgunan> sendInquiryTotalKualitasPemb(@Body ReqInquery ReqInquery);

    @POST(UriApi.prapen.UpdateTotalKualitasPemb)
    Call<ParseResponseAgunan> sendUpdateTotalKualitasPemb(@Body ReqInquery ReqInquery);

    @POST(UriApi.prapen.InquiryHasilCanvasing)
    Call<ParseResponseAgunan> sendDetailAplikasiGadai(@Body ReqInquery ReqInquery);

    @POST(UriApi.prapen.UpdateJaminandanDokumen)
    Call<ParseResponseReturn> UpdateJaminandanDokumen(@Body UpdateJaminandanDokumen UpdateJaminandanDokumen);

    @POST(UriApi.prapen.InquiryJaminandanDokumen)
    Call<ParseResponseAgunan> InqueryJaminandanDokumen(@Body ReqInquery ReqInquery);

    @POST(UriApi.prapen.inquiryAkadMmq)
    Call<ParseResponse> inquiryAkadMmq(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.inquiryAkadMurabahahIjarah)
    Call<ParseResponse> inquiryAkadMurabahahIjarah(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.updateAkadMmq)
    Call<ParseResponse> updateAkadMmq(@Body ReqAkadAsesoir ReqAkadAsesoir);

    @POST(UriApi.prapen.updateAkadMurabahahIjarah)
    Call<ParseResponse> updateAkadMurabahahIjarah(@Body ReqAkadAsesoir ReqAkadAsesoir);

    @POST(UriApi.prapen.inquiryDataPejabat)
    Call<ParseResponse> inquiryDataPejabat(@Body ReqUidIdAplikasi ReqUidIdAplikasi);

    @POST(UriApi.prapen.updateDataPejabat)
    Call<ParseResponse> updateDataPejabat(@Body ReqDataPejabat ReqDataPejabat);

    @POST(UriApi.prapen.InqKalkulatorVerfikator)
    Call<ParseResponseAgunan> InqKalkulatorVerfikator(@Body ReqInquery ReqInquery);

    @POST(UriApi.prapen.HitungBiayadanAngsuran)
    Call<ParseResponseAgunan> SendHitungBiayadanAngsuran(@Body ReqHitungKalkulator ReqHitungKalkulator);

    @POST(UriApi.prapen.UpdateKalkulatorVerifikator)
    Call<ParseResponseAgunan> UpdateKalkulatorVerifikator(@Body ReqHitungKalkulator ReqHitungKalkulator);

    @POST(UriApi.prapen.inqListAsusransi)
    Call<MParseArray> inqListAsusransi(@Body EmptyRequest EmptyRequest);


    //    G1 Download File
    @POST(UriApi.prapen.DownloadSUP)
    Call<ParseResponseFile> DownloadSUP(@Body ReqDownloadFile ReqDownloadFile);

    @POST(UriApi.prapen.DownloadAkadIjarah)
    Call<ParseResponseFile> DownloadAkadIjarah(@Body ReqDownloadFile ReqDownloadFile);

    @POST(UriApi.prapen.DownloadAkadMMQ)
    Call<ParseResponseFile> DownloadAkadMMQ(@Body ReqDownloadFile ReqDownloadFile);

    @POST(UriApi.prapen.DownloadAkadMurabahah)
    Call<ParseResponseFile> DownloadAkadMurabahah(@Body ReqDownloadFile ReqDownloadFile);

    @POST(UriApi.prapen.DownloadAkadRahn)
    Call<ParseResponseFile> DownloadAkadRahn(@Body ReqDownloadFile ReqDownloadFile);

    @POST(UriApi.prapen.DownloadFormMutasi)
    Call<ParseResponseFile> DownloadFormMutasi(@Body ReqDownloadFile ReqDownloadFile);

    @POST(UriApi.prapen.DownloadLampiranSuratKuasa)
    Call<ParseResponseFile> DownloadLampiranSuratKuasa(@Body ReqDownloadFile ReqDownloadFile);

    @POST(UriApi.prapen.DownloadLampiranSuratPernyataan)
    Call<ParseResponseFile> DownloadLampiranSuratPernyataan(@Body ReqDownloadFile ReqDownloadFile);

    @POST(UriApi.prapen.DownloadTandaTerimaSK)
    Call<ParseResponseFile> DownloadTandaTerimaSK(@Body ReqDownloadFile ReqDownloadFile);

    @POST(UriApi.prapen.DownloadWakalah)
    Call<ParseResponseFile> DownloadWakalah(@Body ReqDownloadFile ReqDownloadFile);

    @POST(UriApi.prapen.DownloadPurchaseMurabahah)
    Call<ParseResponseFile> DownloadPurchaseMurabahah(@Body ReqDownloadFile ReqDownloadFile);

    @POST(UriApi.prapen.DownloadPurchaseIjarah)
    Call<ParseResponseFile> DownloadPurchaseIjarah(@Body ReqDownloadFile ReqDownloadFile);

    @POST(UriApi.prapen.DownloadLampiranAngsMur)
    Call<ParseResponseFile> DownloadLampiranAngsMur(@Body ReqDownloadFile ReqDownloadFile);

    @POST(UriApi.prapen.DownloadJdwlAngsRahn)
    Call<ParseResponseFile> DownloadJdwlAngsRahn(@Body ReqDownloadFile ReqDownloadFile);

    @POST(UriApi.prapen.DownloadJadwalPengambilAlihan)
    Call<ParseResponseFile> DownloadJadwalPengambilAlihan(@Body ReqDownloadFile ReqDownloadFile);

    @POST(UriApi.prapen.DownloadLaporanPenilaianAset)
    Call<ParseResponseFile> DownloadLaporanPenilaianAset(@Body ReqDownloadFile ReqDownloadFile);

    @POST(UriApi.prapen.DownloadJadwalAngsuranUjrah)
    Call<ParseResponseFile> DownloadJadwalAngsuranUjrah(@Body ReqDownloadFile ReqDownloadFile);

    @POST(UriApi.prapen.DownloadSuratTandaTerima)
    Call<ParseResponseFile> DownloadSuratTandaTerima(@Body ReqDownloadFile ReqDownloadFile);


    @POST(UriApi.prapen.inquiryOjkBi)
    Call<ParseResponse> inquiryOjkBi(@Body ReqInquery ReqInquery);

    @POST(UriApi.prapen.updateDataOjkBi)
    Call<ParseResponse> updateDataOjkBi(@Body UpdateDataOjkBi UpdateDataOjkBi);

    @POST(UriApi.prapen.dropdownSektorEkonomi)
    Call<ParseResponseArr> dropdownSektorekonomi(@Body SearchSekEkonomi searchSektorEkonomi);

    @POST(UriApi.prapen.dropdownKodePekerjaan)
    Call<ParseResponseArr> dropdownKodePekerjaan(@Body EmptyRequest EmptyRequest);

    @POST(UriApi.prapen.dropdownBidangUsahaTempatKerja)
    Call<ParseResponse> dropdownBidangUsahaTempatKerja(@Body EmptyRequest EmptyRequest);

    @POST(UriApi.prapen.UploadDokumenUmum)
    Call<ParseResponse> UploadDokumenUmum(@Body ReqUploadDokumen ReqUploadDokumen);

    @POST(UriApi.prapen.InqDokumenUpload)
    Call<ParseResponse> InqDokumenUpload(@Body ReqInquery ReqInquery);


}

