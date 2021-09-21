package com.application.bris.ikurma_nos_konsumer.page_aom.dialog;

/**
 * Created by idong on 06/05/2019.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.config.globaldata.GlobalData;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListenerByEditText;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;

import java.util.ArrayList;
import java.util.List;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class DialogKeyValue extends DialogFragment{
    private ImageView btn_close;
    private TextView tv_title;
    private DialogKeyValue.ProdukAdapater produkAdapater;
    private RecyclerView rv_produk;
    private List<keyvalue> dataKeyvalue;
    private static KeyValueListener keyValueListener;
    private static KeyValueListenerByEditText keyValueListenerByEditText;
    public static final String TAG = "example_dialog";

    private static String title;
    private static String jenisPicklist="";
    private static String type = "";
    private static int viewId=0;
    public static DialogKeyValue display(FragmentManager fragmentManager, String titleId, KeyValueListener keyValueListenerId) {
        title = titleId;
        keyValueListener = keyValueListenerId;
        DialogKeyValue dialogAddress = new DialogKeyValue();
        dialogAddress.show(fragmentManager, TAG);
        return dialogAddress;
    }

    public static DialogKeyValue displayByViewId(FragmentManager fragmentManager,String titleId, int idView, KeyValueListener keyValueListenerId) {
        title=titleId;
        viewId = idView;
        keyValueListener = keyValueListenerId;
        DialogKeyValue dialogAddress = new DialogKeyValue();
        dialogAddress.show(fragmentManager, TAG);
        return dialogAddress;
    }

    public static DialogKeyValue displayYaTidak(FragmentManager fragmentManager,String titleId, KeyValueListener keyValueListenerId) {
        title = titleId;
        jenisPicklist="ya tidak";
        keyValueListener = keyValueListenerId;
        DialogKeyValue dialogAddress = new DialogKeyValue();
        dialogAddress.show(fragmentManager, TAG);
        return dialogAddress;
    }

    public static DialogKeyValue displayWithType(FragmentManager fragmentManager, String titleId, KeyValueListener keyValueListenerId, String typeId) {
        title = titleId;
        type = typeId;
        jenisPicklist="";
        keyValueListener = keyValueListenerId;
        DialogKeyValue dialogAddress = new DialogKeyValue();
        dialogAddress.show(fragmentManager, TAG);
        return dialogAddress;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide_Produk);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_produk, container, false);
        btn_close = (ImageView) view.findViewById(R.id.btn_close);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        rv_produk = (RecyclerView) view.findViewById(R.id.rv_produk);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customToolbar();
        initializeProduct();
    }

    public void customToolbar(){
        backgroundStatusBar();
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_title.setText("Pilih "+title);
    }

    private void backgroundStatusBar(){
        Window window = getDialog().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void initializeProduct(){

        //prapen nos
        if(jenisPicklist.equalsIgnoreCase("ya tidak")) {
            dataKeyvalue = getYaTidak();
        }
        else if(viewId==R.id.tf_sumber_aplikasi) {
            dataKeyvalue = getSumberAplikasi();
        }
        else if (title.equalsIgnoreCase("status nikah")){
            dataKeyvalue = getDataStatusNikah();
        }

        else if (title.equalsIgnoreCase("status nikah saat ini")){
            dataKeyvalue = getDataStatusNikah();
        }
        else if (title.equalsIgnoreCase("agama")){
            dataKeyvalue = getDataAgama();
        }


        //KONSUMER ISIAN DATA FINANSIAL KPR
//        if(title.equalsIgnoreCase("jenis tiering")) {
//            dataKeyvalue = getDataJenisTiering();
//
//        }
//
//        //KONSUMER ISIAN DATA CIF KPR
//        else if(title.equalsIgnoreCase("referal dari")){
//            dataKeyvalue = getDataReferalDari();
//        }
//        else if(title.equalsIgnoreCase("paket")){
//            dataKeyvalue = getDataPaket();
//        }
//        else if(title.equalsIgnoreCase("rekomendasi penilai")){
//            dataKeyvalue = getRekomendasiAppraisal();
//        }
//        else if(title.equalsIgnoreCase("program")){
//            dataKeyvalue = getDataProgram();
//        }
//        else if(title.equalsIgnoreCase("pihak ketiga")){
//            dataKeyvalue = getDataPihakKetiga();
//        }
//        else if(title.equalsIgnoreCase("Proyek Perumahan")){
//
//            //ini diganti dengan http request ambil dari service datanya lalu masukkan ke datakeyvalue
////            dataKeyvalue = getDataPaket();
//        }
//        else if(title.equalsIgnoreCase("tujuan penggunaan")){
//            dataKeyvalue = getTujuanPenggunaanKpr();
//        }
//        else if(title.equalsIgnoreCase("jenis pekerjaan")){
//            dataKeyvalue = getJenisPekerjaan();
//        }
//        else if(title.equalsIgnoreCase("status kepegawaian")){
//            dataKeyvalue = getStatusKepegawaian();
//        }
//
//        //KONSUMER DATA NASABAH
//        else if (title.equalsIgnoreCase("tingkat pendidikan")){
//            dataKeyvalue = getDataPendidikanTerakhir();
//        }
//        else if (title.equalsIgnoreCase("No Telp/Fixed Line")){
//            dataKeyvalue = getNoTelpFixedLine();
//        }
//        else if (title.equalsIgnoreCase("Kepemilikan Tempat Tinggal")){
//            dataKeyvalue = getKepemilikanTempatTinggal();
//        }
//
//        //KONSUMER FORM PRESCORING
//
//        //ini sangat rawan error, ganti ke id viewnya next time
//        else if (title.equalsIgnoreCase("jenis prescoring")){
//            dataKeyvalue = getJenisPrescoring();
//        }
//        else if (title.equalsIgnoreCase("Status Pegawai")){
//            dataKeyvalue = getStatusPegawai();
//        }
//        else if (title.equalsIgnoreCase("kepemilikan kartu kredit")){
//            dataKeyvalue = getKepemilikanKartuKredit();
//        }
//        else if (title.equalsIgnoreCase("id/name tag pegawai")){
//            dataKeyvalue = getIdNamePegawai();
//        }
//        else if (title.equalsIgnoreCase("slip gaji")){
//            dataKeyvalue = getSlipGaji();
//        }
//        else if (title.equalsIgnoreCase("rekening gaji")){
//            dataKeyvalue = getRekeningGaji();
//        }
//        else if (title.equalsIgnoreCase("Kebenaran informasi tempat bekerja")){
//            dataKeyvalue = getKebenaranInformasiTempatBekerja();
//        }
//        else if (title.equalsIgnoreCase("operasional kantor")){
//            dataKeyvalue = getOperasionalKantor();
//        }
//        else if (title.equalsIgnoreCase("prospek usaha selama minimal 5 tahun")){
//            dataKeyvalue = getProspekUsahaKonsumer();
//        }
//        else if (title.equalsIgnoreCase("lama beroperasi kantor/perusahaan")){
//            dataKeyvalue = getLamaBeroperasiKantor();
//        }
//        else if (title.equalsIgnoreCase("akses lokasi kantor")){
//        dataKeyvalue = getAksesLokasiKantor();
//    }
//        else if (title.equalsIgnoreCase("jenis kantor")){
//            dataKeyvalue = getJenisKantor();
//        }
//        else if (title.equalsIgnoreCase("papan nama kantor/perusahaan")){
//            dataKeyvalue = getPapanNamaKantor();
//        }
//        else if (title.equalsIgnoreCase("jumlah karyawan")){
//            dataKeyvalue = getJumlahKaryawan();
//        }
//        else if (title.equalsIgnoreCase("kepemilikan badan usaha")){
//            dataKeyvalue = getKepemilikanBadanUsaha();
//        }
//        else if (title.equalsIgnoreCase("keterangan domisili yayasan")){
//            dataKeyvalue = getKeteranganDomisiliYayasan();
//        }
//        else if (title.equalsIgnoreCase("lama berdiri dan beroperasi yayasan")){
//            dataKeyvalue = getLamaBerdiriYayasan();
//        }
//        else if (title.equalsIgnoreCase("total penyertaan yayasan pada seluruh badan usaha")){
//            dataKeyvalue = getTotalPenyertaanYayasan();
//        }
//        else if (title.equalsIgnoreCase("validitas tempat tinggal")){
//            dataKeyvalue = getValiditasTempatTinggal();
//        }
//        else if (title.equalsIgnoreCase("jumlah penyertaan yayasan pada masing-masing badan usaha")){
//            dataKeyvalue = getJumlahPenyertaanYayasan();
//        }
//        else if (title.equalsIgnoreCase("Kesesuaian pendirian badan usaha dengan maksud dan tujuan yayasan")){
//            dataKeyvalue = getKesesuaianPendirianBadanUsaha();
//        }
//        else if (title.equalsIgnoreCase("kesesuaian badan usaha dengan ketentuan bris")){
//            dataKeyvalue = getKesesuaianDenganBris();
//        }
//        else if (title.equalsIgnoreCase("struktur manajemen badan usaha yayasan")){
//            dataKeyvalue = getStrukturManajemen();
//        }
//        else if (title.equalsIgnoreCase("lama berdiri dan beroperasi badan usaha yayasan")){
//            dataKeyvalue = getLamaBerdiriBadanUsaha();
//        }
//
//
//        //KONSUMER KMG
//
//        else if (title.equalsIgnoreCase("tujuan penggunaan kmg")){
//            dataKeyvalue = getTujuanPenggunaanKmg();
//        }
//        else if (title.equalsIgnoreCase("embp")){
//            dataKeyvalue = getEmbp();
//        }
//
//        //konsumer kmg bidang pekerjaan ,sama dengan bidang usaha mikro
//        else if(title.equalsIgnoreCase("bidang pekerjaan")){
//            dataKeyvalue = getDataBidangUsaha();
//        }
//
//        else if(title.equalsIgnoreCase("posisi jabatan")){
//            dataKeyvalue = getPosisiJabatan();
//        }
//
//        else if(title.equalsIgnoreCase("pembayaran gaji melalui")){
//            dataKeyvalue = getPembayaranGajiMelalui();
//        }
//
//        else if(title.equalsIgnoreCase("referensi")){
//            dataKeyvalue = getReferensi();
//        }
//
//
//
//        //MIKRO
//        else if(title.equalsIgnoreCase("bidang usaha")){
//            dataKeyvalue = getDataBidangUsaha();
//        }
//        else if(title.equalsIgnoreCase("pekerjaan")){
//            dataKeyvalue = getDataBidangPekerjaan();
//        }
//        else if (title.equalsIgnoreCase("tujuan penggunaan")){
//            dataKeyvalue = getDataTujuanPenggunaan();
//        }
//        else if (title.equalsIgnoreCase("jenis kelamin")){
//            dataKeyvalue = getDataJenisKelamin();
//        }
//
//
//
//
//
//        else if (title.equalsIgnoreCase("status nikah ktp")){
//            dataKeyvalue = getDataStatusNikah();
//        }
//
//        else if (title.equalsIgnoreCase("tipe pendapatan")){
//            dataKeyvalue = getDataTipePendapatan();
//        }
//
//        else if (title.equalsIgnoreCase("pendidikan terakhir")){
//            dataKeyvalue = getDataPendidikanTerakhir();
//        }
//
//        else if (title.equalsIgnoreCase("status tempat domisili")){
//            dataKeyvalue = getDataStatusTempatDomisili();
//        }
//
//        else if (title.equalsIgnoreCase("status permohonan")){
//            dataKeyvalue = getDataStatusPemohon();
//        }
//
//        else if (title.equalsIgnoreCase("hubungan")){
//            dataKeyvalue = getDataHubungan();
//        }
//        else if (title.equalsIgnoreCase("lokasi usaha")){
//            dataKeyvalue = getDataLokasiUsaha();
//        }
//        else if (title.equalsIgnoreCase("status tempat usaha")){
//            dataKeyvalue = getDataStatusTempatUsaha();
//        }
//        else if (title.equalsIgnoreCase("jenis tempat usaha")){
//            dataKeyvalue = getDataJenisTempatUsaha();
//        }
//        else if (title.equalsIgnoreCase("aspek pemasaran")){
//            dataKeyvalue = getDataAspekPemasaran();
//        }
//        else if (title.equalsIgnoreCase("jenis usaha")){
//            dataKeyvalue = getDataJenisUsaha();
//        }
//
//
//        else if (title.equalsIgnoreCase("reputasi/integritas usaha")){
//            dataKeyvalue = getDataReputasiUsaha();
//        }
//        else if (title.equalsIgnoreCase("riwayat hubungan bank")){
//            dataKeyvalue = getDataRiwayatHubunganBank();
//        }
//        else if (title.equalsIgnoreCase("prospek usaha")){
//            dataKeyvalue = getDataProspekUsaha();
//        }
//        else if (title.equalsIgnoreCase("ketergantungan thd supplier")){
//            dataKeyvalue = getDataKetergantunganthdSupplier();
//        }
//        else if (title.equalsIgnoreCase("ketergantungan thd pelanggan")){
//            dataKeyvalue = getDataKetergantunganthdPelanggan();
//        }
//        else if (title.equalsIgnoreCase("wilayah pemasaran")){
//            dataKeyvalue = getDataWilayahPemasaran();
//        }
//
//        else if (title.equalsIgnoreCase("jenis produk")){
//            dataKeyvalue = getDataJenisProduk();
//        }
//        else if (title.equalsIgnoreCase("jenis pembiayaan")){
//            dataKeyvalue = getDataJenisPembiayaan();
//        }
//
//
//        //tambahan eki
//        else if (title.equalsIgnoreCase("bentuk bidang tanah")){
//            dataKeyvalue = getBidangTanah();
//        }
//        else if (title.equalsIgnoreCase("permukaan tanah")){
//            dataKeyvalue = getPermukaanTanah();
//        }
//        else if (title.equalsIgnoreCase("jenis surat tanah")){
//            dataKeyvalue = getJenisSurat();
//        }
//        else if (title.equalsIgnoreCase("hub dengan pemegang hak")){
//            dataKeyvalue = getHubNasabah();
//        }
//        else if (title.equalsIgnoreCase("Jenis Bangunan")){
//            dataKeyvalue = getJenisBangunan();
//        }
//        else if (title.equalsIgnoreCase("Lokasi Bangunan")){
//            dataKeyvalue = getLokasiBangun();
//        }
//        else if (title.equalsIgnoreCase("Jenis Agunan XBRL")){
//            dataKeyvalue = getJenisAgunanXBRL();
//        }
//        else if (title.equalsIgnoreCase("hub penghuni dengan pemegang hak")){
//            dataKeyvalue = getHubPenghuniDenganPemegangHak();
//        }
//        else if (title.equalsIgnoreCase("Kondisi Bangunan")){
//            dataKeyvalue = getKondisiBangunan();
//        }
//        else if (title.equalsIgnoreCase("Spesifikasi Jenis Bangunan")){
//            dataKeyvalue = getJenisBangunanSpek();
//        }
//        else if (title.equalsIgnoreCase("Pondasi")){
//            dataKeyvalue = getPondasi();
//        }
//        else if (title.equalsIgnoreCase("Dinding")){
//            dataKeyvalue = getDinding();
//        }
//        else if (title.equalsIgnoreCase("Atap")){
//            dataKeyvalue = getAtap();
//        }
//        else if (title.equalsIgnoreCase("Peruntukan Lokasi")){
//            dataKeyvalue = getPeruntukan();
//        }
//        else if (title.equalsIgnoreCase("Perkembangan Lingkungan")){
//            dataKeyvalue = getPerkembangan();
//        }
//        else if (title.equalsIgnoreCase("Kepadatan")){
//            dataKeyvalue = getKepadatan();
//        }
//        else if (title.equalsIgnoreCase("Akses Mencapai Objek")){
//            dataKeyvalue = getAksesCapai();
//        }
//        else if (title.equalsIgnoreCase("Akses Jalan Objek")){
//            dataKeyvalue = getAksesJalan();
//        }
//
//        else if (title.equalsIgnoreCase("Fasilitas Sosial")){
//            dataKeyvalue = getFasilitasSosial();
//        }
//
//        else if (title.equalsIgnoreCase("Jenis Agunan")){
//            dataKeyvalue = getJenisAgunan();
//        }
//        else if (title.equalsIgnoreCase("Hub. Pemegang Hak dg Nasabah") || title.equalsIgnoreCase("Hubungan Dengan Nasabah")){
//            dataKeyvalue = getHubunganPemegangHak();
//        }
//        else if (title.equalsIgnoreCase("Lokasi Agunan")){
//            dataKeyvalue = getLokasiAgunan();
//        }
//        else if (title.equalsIgnoreCase("Listrik") || title.equalsIgnoreCase("Telpon")){
//            dataKeyvalue = getAdaTidak();
//        }
//
//        else if (title.equalsIgnoreCase("Hub Pemegang Hak dg Nasabah")){
//            dataKeyvalue = getHubPenghuniDenganPemegangHak();
//        }
//        else if (title.equalsIgnoreCase("status penggarap")){
//            dataKeyvalue = getStatusPenggarap();
//        }
//        else if (title.equalsIgnoreCase("jenis dokumen")){
//            dataKeyvalue = getJenisDokumen();
//        }
//        else if (title.equalsIgnoreCase("check bpn")){
//            dataKeyvalue = getCheckBpn();
//        }
//        else if (title.equalsIgnoreCase("Hasil")){
//            dataKeyvalue = getHasilBpn();
//        }
//        else if (title.equalsIgnoreCase("Hub Penggarap dg Pemegang Hak")){
//            dataKeyvalue = getHubPenggarap();
//        }
//
//        else if (title.equalsIgnoreCase("Current Ratio")){
//            dataKeyvalue = getCurrentRatio();
//        }
//        else if (title.equalsIgnoreCase("Profitability")){
//            dataKeyvalue = getProfitability();
//        }
//        else if (title.equalsIgnoreCase("Jenis Deposito")){
//            dataKeyvalue = getJenisDeposito();
//        }
//        else if (title.equalsIgnoreCase("Automatic Roll Over (ARO)")){
//            dataKeyvalue = getAro();
//        }
//        else if (title.equalsIgnoreCase("Dokumen Bukti Hak Kios")){
//            dataKeyvalue = getDokumenBuktiHakKios();
//        }
//
//
//        //Agunan Kendaraaan
//        else if (title.equalsIgnoreCase("Jenis Kendaraan")){
//            dataKeyvalue = getJenisKendaraan();
//        }
//        else if (title.equalsIgnoreCase("Kategori Kendaraan")){
//            dataKeyvalue = getKategoriKendaraan();
//        }
//        else if (title.equalsIgnoreCase("Penggunaan Jaminan")){
//            dataKeyvalue = getPenggunaanJaminan();
//        }
//        else if (title.equalsIgnoreCase("Daerah Operasional Jaminan")){
//            dataKeyvalue = getDaerahOperasional();
//        }
//        else if (title.equalsIgnoreCase("Kondisi Jaminan")){
//            dataKeyvalue = getKondisiJaminan();
//        }
//        else if (title.equalsIgnoreCase("Hubungan Pemilik Dengan Nasabah")){
//            dataKeyvalue = getHubunganPemilikDenganNasabah();
//        }
//        else if (title.equalsIgnoreCase("Bukti Gesek Mesin")){
//            dataKeyvalue = getBuktiGesekMesin();
//        }
//        else if (title.equalsIgnoreCase("Bukti Gesek Rangka")){
//            dataKeyvalue = getBuktiGesekRangka();
//        }
//        else if (title.equalsIgnoreCase("Kendaraan Jepang")){
//            dataKeyvalue = getKendaraanJepang();
//        }
//        else if (title.equalsIgnoreCase("Plat Kuning")){
//            dataKeyvalue = getPlatKuning();
//        }
//        else if (title.equalsIgnoreCase("Check Samsat")){
//            dataKeyvalue = getCheckSamsat();
//        }
//        else if (title.equalsIgnoreCase("Hasil")){
//            dataKeyvalue = getHasil();
//        }
//        //End Agunan Kendaraan
//
//        //Purna
//        else if (title.equalsIgnoreCase("Instansi/Koperasi")){
//            dataKeyvalue = getInstansiNonEmBP();
//        }


        produkAdapater = new DialogKeyValue.ProdukAdapater(getContext(), dataKeyvalue, title, keyValueListener);
        rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_produk.setItemAnimator(new DefaultItemAnimator());
        rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rv_produk.setAdapter(produkAdapater);
    }

    //PRAPEN NOS
    private List<keyvalue> getYaTidak(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.yaTidak(getContext(), data);
        return data;
    }
    private List<keyvalue> getSumberAplikasi(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.sumberAplikasi(getContext(), data);
        return data;
    }


    //KONSUMER KMG

    private List<keyvalue> getReferensi(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.referensi(getContext(), data);
        return data;
    }

    private List<keyvalue> getPembayaranGajiMelalui(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.pembayaranGajiMelalui(getContext(), data);
        return data;
    }

    private List<keyvalue> getPosisiJabatan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.posisiJabatan(getContext(), data);
        return data;
    }
    private List<keyvalue> getDataJenisTiering(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisTiering(getContext(), data);
        return data;
    }
    private List<keyvalue> getDataReferalDari(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.referalDariKpr(getContext(), data);
        return data;
    }
    private List<keyvalue> getRekomendasiAppraisal(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.rekomendasiAppraisal(getContext(), data);
        return data;
    }
    private List<keyvalue> getDataPaket(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.paketKpr(getContext(), data);
        return data;
    }
    private List<keyvalue> getDataProgram(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.programKpr(getContext(), data);
        return data;
    }
    private List<keyvalue> getDataPihakKetiga(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.pihakKetiga(getContext(), data);
        return data;
    }
    private List<keyvalue> getTujuanPenggunaanKpr(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.tujuanPenggunaanKpr(getContext(), data);
        return data;
    }
    private List<keyvalue> getJenisPekerjaan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisPekerjaan(getContext(), data);
        return data;
    }

    private List<keyvalue> getStatusKepegawaian(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.statusKepegawaian(getContext(), data);
        return data;
    }

    //KONSUMER DATA NASABAH
    private List<keyvalue> getKepemilikanTempatTinggal(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.kepemilikanTempatTinggal(getContext(), data);
        return  data;
    }
    private List<keyvalue> getNoTelpFixedLine(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.noTelpFixedLine(getContext(), data);
        return  data;
    }

    //KONSUMER PRESCORING

    private List<keyvalue> getJenisPrescoring(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisPreScoring(getContext(), data);
        return  data;
    }

    private List<keyvalue> getStatusPegawai(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.statusPegawai(getContext(), data);
        return  data;
    }

    private List<keyvalue> getKepemilikanKartuKredit(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.kepemilikanKartuKredit(getContext(), data);
        return  data;
    }

    private List<keyvalue> getIdNamePegawai(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.idNamePegawai(getContext(), data);
        return  data;
    }

    private List<keyvalue> getSlipGaji(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.slipGaji(getContext(), data);
        return  data;
    }

    private List<keyvalue> getRekeningGaji(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.rekeningGaji(getContext(), data);
        return  data;
    }

    private List<keyvalue> getKebenaranInformasiTempatBekerja(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.kebenaranInformasiTempatBekerja(getContext(), data);
        return  data;
    }

    private List<keyvalue> getOperasionalKantor(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.operasionalKantor(getContext(), data);
        return  data;
    }

    private List<keyvalue> getProspekUsahaKonsumer(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.prospekUsahaKonsumer(getContext(), data);
        return  data;
    }

    private List<keyvalue> getLamaBeroperasiKantor(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.lamaBeroperasiKantor(getContext(), data);
        return  data;
    }

    private List<keyvalue> getAksesLokasiKantor(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.aksesLokasiKantor(getContext(), data);
        return  data;
    }

    private List<keyvalue> getJenisKantor(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisKantor(getContext(), data);
        return  data;
    }

    private List<keyvalue> getPapanNamaKantor(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.papanNamaKantor(getContext(), data);
        return  data;
    }

    private List<keyvalue> getJumlahKaryawan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jumlahKaryawan(getContext(), data);
        return  data;
    }

    //KONSUMER PRESCORING YAYASAN
    private List<keyvalue> getKepemilikanBadanUsaha(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.kepemilikanBadanUsaha(getContext(), data);
        return  data;
    }

    private List<keyvalue> getKeteranganDomisiliYayasan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.keteranganDomisiliYayasan(getContext(), data);
        return  data;
    }

    private List<keyvalue> getLamaBerdiriYayasan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.lamaBerdiriYayasan(getContext(), data);
        return  data;
    }

    private List<keyvalue> getTotalPenyertaanYayasan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.totalPenyertaanYayasan(getContext(), data);
        return  data;
    }

    private List<keyvalue> getValiditasTempatTinggal(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.validitasTempatTinggal(getContext(), data);
        return  data;
    }

    private List<keyvalue> getJumlahPenyertaanYayasan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jumlahPenyertaanYayasan(getContext(), data);
        return  data;
    }

    private List<keyvalue> getKesesuaianPendirianBadanUsaha(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.kesesuaianPendirianBadanUsaha(getContext(), data);
        return  data;
    }

    private List<keyvalue> getKesesuaianDenganBris(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.kesesuaianBadanUsahaDenganBris(getContext(), data);
        return  data;
    }

    private List<keyvalue> getStrukturManajemen(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.strukturManajemen(getContext(), data);
        return  data;
    }

    private List<keyvalue> getLamaBerdiriBadanUsaha(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.lamaBerdiriBadanUsaha(getContext(), data);
        return  data;
    }

    //KONSUMER KMG
    private List<keyvalue> getTujuanPenggunaanKmg(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.tujuanPenggunaanKmg(getContext(), data);
        return  data;
    }

    private List<keyvalue> getEmbp(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.embp(getContext(), data);
        return  data;
    }



    //MIKRO
    private List<keyvalue> getDataBidangUsaha(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.bidangUsaha(getContext(), data);
        return data;
    }

    private List<keyvalue> getDataBidangPekerjaan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.bidangKerjaan(getContext(), data);
        return data;
    }

    private List<keyvalue> getDataTujuanPenggunaan(){
        List<keyvalue> data = new ArrayList<>();
        if(type.equalsIgnoreCase("127") || type.equalsIgnoreCase("128")){
            data.add(new keyvalue("Barang Modal Kerja", "40"));
            data.add(new keyvalue("Investasi", "41"));
        }
        else{
            GlobalData.tujuanPenggunaan(getContext(), data);
        }
        return  data;
    }

    private List<keyvalue> getDataJenisKelamin(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisKelamin(getContext(), data);
        return  data;
    }

    private List<keyvalue> getDataAgama(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.agama(getContext(), data);
        return  data;
    }

    private List<keyvalue> getDataStatusNikah(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.statusMenikah(getContext(), data);
        return  data;
    }

    private List<keyvalue> getDataTipePendapatan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.tipePendapatan(getContext(), data);
        return  data;
    }

    private List<keyvalue> getDataPendidikanTerakhir(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.pendidikanTerakhir(getContext(), data);
        return  data;
    }

    private List<keyvalue> getDataStatusTempatDomisili(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.statusTempatDomisili(getContext(), data);
        return  data;
    }

    private List<keyvalue> getDataStatusPemohon(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.statusPermohonan(getContext(), data);
        return  data;
    }

    private List<keyvalue> getDataHubungan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.hubunganKeluarga(getContext(), data);
        return  data;
    }

    private List<keyvalue> getDataLokasiUsaha(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.lokasiUsaha(getContext(), data);
        return  data;
    }

    private List<keyvalue> getDataStatusTempatUsaha(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.statusTempatUsaha(getContext(), data);
        return  data;
    }
    private List<keyvalue> getDataJenisTempatUsaha(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisTempatUsaha(getContext(), data);
        return  data;
    }
    private List<keyvalue> getDataAspekPemasaran(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.aspekPemasaran(getContext(), data);
        return  data;
    }

    private List<keyvalue> getDataJenisUsaha(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisUsaha(getContext(), data);
        return  data;
    }

    private List<keyvalue> getDataReputasiUsaha() {
        List<keyvalue> data = new ArrayList<>();
        GlobalData.reputasiUsaha(getContext(), data);
        return data;
    }

    private List<keyvalue> getDataRiwayatHubunganBank() {
        List<keyvalue> data = new ArrayList<>();
        GlobalData.riwayatHubdgnBank(getContext(), data);
        return data;
    }

    private List<keyvalue> getDataProspekUsaha() {
        List<keyvalue> data = new ArrayList<>();
        GlobalData.prospekUsaha(getContext(), data);
        return data;
    }

    private List<keyvalue> getDataKetergantunganthdSupplier() {
        List<keyvalue> data = new ArrayList<>();
        GlobalData.ketergantunganSupplierdanPelanggan(getContext(), data);
        return data;
    }

    private List<keyvalue> getDataKetergantunganthdPelanggan() {
        List<keyvalue> data = new ArrayList<>();
        GlobalData.ketergantunganSupplierdanPelanggan(getContext(), data);
        return data;
    }

    private List<keyvalue> getDataWilayahPemasaran() {
        List<keyvalue> data = new ArrayList<>();
        GlobalData.wilayahPemasaran(getContext(), data);
        return data;
    }

    private List<keyvalue> getDataJenisProduk() {
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisProduk(getContext(), data);
        return data;
    }

    private List<keyvalue> getDataJenisPembiayaan() {
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisPembiayaan(getContext(), data);
        return data;
    }

    //tambahan eki
    private List<keyvalue> getBidangTanah(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.bentukBidangTanah(getContext(), data);
        return  data;
    }

    private List<keyvalue> getPermukaanTanah(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.permukaanTanah(getContext(), data);
        return  data;
    }
    private List<keyvalue> getJenisSurat(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisSuratTanah(getContext(), data);
        return  data;
    }
    private List<keyvalue> getHubNasabah(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.hubDenganPemegangHak(getContext(), data);
        return  data;
    }
    private List<keyvalue> getJenisBangunan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisBangunan(getContext(), data);
        return  data;
    }
    private List<keyvalue> getLokasiBangun(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.lokasiBangunan(getContext(), data);
        return  data;
    }
    private List<keyvalue> getJenisAgunanXBRL(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisAgunanXBRL(getContext(), data);
        return  data;
    }
    private List<keyvalue> getHubPenghuniDenganPemegangHak(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.hubPenghuniDenganPemegangHak(getContext(), data);
        return  data;
    }
    private List<keyvalue> getKondisiBangunan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.kondisiBangunan(getContext(), data);
        return  data;
    }
    private List<keyvalue> getPondasi(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.pondasi(getContext(), data);
        return  data;
    }
    private List<keyvalue> getJenisBangunanSpek(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisBangunanSpek(getContext(), data);
        return  data;
    }
    private List<keyvalue> getDinding(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.dinding(getContext(), data);
        return  data;
    }
    private List<keyvalue> getAtap(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.atap(getContext(), data);
        return  data;
    }
    private List<keyvalue> getPeruntukan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.peruntukan(getContext(), data);
        return  data;
    }
    private List<keyvalue> getPerkembangan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.perkembangan(getContext(), data);
        return  data;
    }
    private List<keyvalue> getKepadatan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.kepadatan(getContext(), data);
        return  data;
    }
    private List<keyvalue> getAksesCapai(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.aksesCapai(getContext(), data);
        return  data;
    }
    private List<keyvalue> getAksesJalan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.aksesJalan(getContext(), data);
        return  data;
    }

    private List<keyvalue> getFasilitasSosial(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.fasilitasSosial(getContext(), data);
        return data;
    }

    private List<keyvalue> getJenisAgunan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisAgunan(getContext(), data);
        return  data;
    }
    private List<keyvalue> getHubunganPemegangHak(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.hubunganPemegangHak(getContext(), data);
        return  data;
    }

    private List<keyvalue> getLokasiAgunan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.lokasiAgunan(getContext(), data);
        return  data;
    }

    private List<keyvalue> getAdaTidak(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.adaTidak(getContext(), data);
        return  data;
    }

    private List<keyvalue> getStatusPenggarap(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.statusPenggarap(getContext(), data);
        return  data;
    }

    private List<keyvalue> getHubPenggarap(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.hubPenggarapDgPemegang(getContext(), data);
        return  data;
    }

    private List<keyvalue> getJenisDokumen(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisDokumen(getContext(), data);
        return  data;
    }

    private List<keyvalue> getCheckBpn(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.checkBpn(getContext(), data);
        return  data;
    }

    private List<keyvalue> getHasilBpn(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.hasilBpn(getContext(), data);
        return  data;
    }


    private List<keyvalue> getCurrentRatio(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.currentRatio(getContext(), data);
        return  data;
    }
    private List<keyvalue> getProfitability(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.profitability(getContext(), data);
        return  data;
    }
    private List<keyvalue> getJenisDeposito(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisDeposito(getContext(), data);
        return  data;
    }
    private List<keyvalue> getAro(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.aro(getContext(), data);
        return  data;
    }
    private List<keyvalue> getDokumenBuktiHakKios(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.dokumenBuktiHakKios(getContext(), data);
        return  data;
    }


    //Agunan Kendaraan
    private List<keyvalue> getJenisKendaraan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.jenisKendaraan(getContext(), data);
        return data;
    }
    private List<keyvalue> getKategoriKendaraan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.kategoriKendaraan(getContext(), data);
        return data;
    }
    private List<keyvalue> getPenggunaanJaminan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.penggunaanJaminan(getContext(), data);
        return data;
    }
    private List<keyvalue> getDaerahOperasional(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.daerahOperasional(getContext(), data);
        return data;
    }
    private List<keyvalue> getKondisiJaminan(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.kondisiJaminan(getContext(), data);
        return data;
    }
    private List<keyvalue> getHubunganPemilikDenganNasabah(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.hubPemilikDenganNasabah(getContext(), data);
        return data;
    }
    private List<keyvalue> getBuktiGesekMesin(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.buktiGesekMesin(getContext(), data);
        return data;
    }
    private List<keyvalue> getBuktiGesekRangka(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.buktiGesekRangka(getContext(), data);
        return data;
    }
    private List<keyvalue> getKendaraanJepang(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.kendaraanJepang(getContext(), data);
        return data;
    }
    private List<keyvalue> getPlatKuning(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.platKuning(getContext(), data);
        return data;
    }
    private List<keyvalue> getCheckSamsat(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.checkSamsat(getContext(), data);
        return data;
    }
    private List<keyvalue> getHasil(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.hasil(getContext(), data);
        return data;
    }
    //End Agunan Kendaraan

    //Purna

    private List<keyvalue> getInstansiNonEmBP(){
        List<keyvalue> data = new ArrayList<>();
        GlobalData.instansiNonEmBp(getContext(), data);
        return data;
    }


    //CLASS ADAPTER PRODUCT
    public class ProdukAdapater extends RecyclerView.Adapter<ProdukAdapater.ProductViewHolder> {

        private Context context;
        private List<keyvalue> data;
        private String title;
        private KeyValueListener keyValueListener;

        public ProdukAdapater(Context context, List<keyvalue> data, String title, KeyValueListener keyValueListener) {
            this.context = context;
            this.data = data;
            this.title = title;
            this.keyValueListener = keyValueListener;
        }

        @NonNull
        @Override
        public ProdukAdapater.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_product, parent, false);
            return new ProdukAdapater.ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
            holder.tv_title.setText(title);
            holder.tv_product.setText(data.get(position).getName());
            holder.rl_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    keyValueListener.onKeyValueSelect(title, data.get(position));
                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            if (data == null){
                return 0;
            }
            else {
                return data.size();
            }
        }

        public class ProductViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_title, tv_product;
            public RelativeLayout rl_product;
            public ProductViewHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                tv_product = (TextView) itemView.findViewById(R.id.tv_product);
                rl_product = (RelativeLayout) itemView.findViewById(R.id.rl_product);
            }
        }
    }
}
