package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.data_finansial;


import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseDataInstansi;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.SimpanDataFinansial;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.ValidasiDataFinansialKmg;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.ReqIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.database.pojo.ProductPojo;
import com.application.bris.ikurma_nos_konsumer.model.hotprospek.DataFinansial;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogAddress;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.AddressListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ProductListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.address;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class DataFinansialKmgActivity extends AppCompatActivity implements
        View.OnClickListener, View.OnFocusChangeListener, ProductListener, AddressListener, KeyValueListener, CameraListener, ConfirmListener, TextWatcher {

    @BindView(R.id.tb_regular)
    Toolbar toolbar;
    @BindView(R.id.tv_page_title)
    TextView tv_page_title;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    //Data Finansial
    @BindView(R.id.tf_gaji_pokok)
    TextFieldBoxes tf_gaji_pokok;
    @BindView(R.id.et_gaji_pokok)
    EditText et_gaji_pokok;

    @BindView(R.id.tf_nilai_permohonan_pembiayaan)
    TextFieldBoxes tf_nilai_permohonan_pembiayaan;
    @BindView(R.id.et_nilai_permohonan_pembiayaan)
    EditText et_nilai_permohonan_pembiayaan;

    @BindView(R.id.tf_penghasilan_bersih)
    TextFieldBoxes tf_penghasilan_bersih;
    @BindView(R.id.et_penghasilan_bersih)
    EditText et_penghasilan_bersih;

    @BindView(R.id.tf_harga_beli)
    TextFieldBoxes tf_harga_beli;
    @BindView(R.id.et_harga_beli)
    EditText et_harga_beli;


    @BindView(R.id.tf_tunjangan_tetap_lainnya)
    TextFieldBoxes tf_tunjangan_tetap_lainnya;
    @BindView(R.id.et_tunjangan_tetap_lainnya)
    EditText et_tunjangan_tetap_lainnya;

    @BindView(R.id.tf_angsuran_pinjaman_eksisting_1)
    TextFieldBoxes tf_angsuran_pinjaman_eksisting_1;
    @BindView(R.id.et_angsuran_pinjaman_eksisting_1)
    EditText et_angsuran_pinjaman_eksisting_1;

    @BindView(R.id.tf_margin_pertahun)
    TextFieldBoxes tf_margin_pertahun;
    @BindView(R.id.et_margin_pertahun)
    EditText et_margin_pertahun;


    @BindView(R.id.tf_jangka_waktu)
    TextFieldBoxes tf_jangka_waktu;
    @BindView(R.id.et_jangka_waktu)
    EditText et_jangka_waktu;

    @BindView(R.id.tf_jangka_waktu_qardh)
    TextFieldBoxes tf_jangka_waktu_qardh;
    @BindView(R.id.et_jangka_waktu_qardh)
    EditText et_jangka_waktu_qardh;

    @BindView(R.id.tf_jumlah_plafon_pembiayaan_diusulkan)
    TextFieldBoxes tf_jumlah_plafon_pembiayaan_diusulkan;
    @BindView(R.id.et_jumlah_plafon_pembiayaan_diusulkan)
    EditText et_jumlah_plafon_pembiayaan_diusulkan;

    @BindView(R.id.tf_rpc)
    TextFieldBoxes tf_rpc;
    @BindView(R.id.et_rpc)
    EditText et_rpc;

    @BindView(R.id.tf_angsuran_)
    TextFieldBoxes tf_angsuran_;
    @BindView(R.id.et_angsuran)
    EditText et_angsuran;

    @BindView(R.id.tf_ftv_ratio)
    TextFieldBoxes tf_ftv_ratio;
    @BindView(R.id.et_ftv_ratio)
    EditText et_ftv_ratio;

    @BindView(R.id.tf_tujuan_penggunaan)
    TextFieldBoxes tf_tujuan_penggunaan;
    @BindView(R.id.et_tujuan_penggunaan)
    EditText et_tujuan_penggunaan;

    @BindView(R.id.tf_kewajiban_lain)
    TextFieldBoxes tf_kewajiban_lain;
    @BindView(R.id.et_kewajiban_lain)
    EditText et_kewajiban_lain;



    @BindView(R.id.tf_uang_muka)
    TextFieldBoxes tf_uang_muka;
    @BindView(R.id.et_uang_muka)
    EditText et_uang_muka;

    @BindView(R.id.tf_jangka_waktu_takeover)
    TextFieldBoxes tf_jangka_waktu_takeover;
    @BindView(R.id.et_jangka_waktu_takeover)
    EditText et_jangka_waktu_takeover;

    @BindView(R.id.tf_jumlah_plafon_pembiayaan_konsumtif)
    TextFieldBoxes tf_jumlah_plafon_pembiayaan_konsumtif;
    @BindView(R.id.et_jumlah_plafon_pembiayaan_konsumtif)
    EditText et_jumlah_plafon_pembiayaan_konsumtif;

    @BindView(R.id.tf_margin_pertahun_konsumtif)
    TextFieldBoxes tf_margin_pertahun_konsumtif;
    @BindView(R.id.et_margin_pertahun_konsumtif)
    EditText et_margin_pertahun_konsumtif;

    @BindView(R.id.tf_angsuran_konsumtif)
    TextFieldBoxes tf_angsuran_konsumtif;
    @BindView(R.id.et_angsuran_konsumtif)
    EditText et_angsuran_konsumtif;

    @BindView(R.id.tf_rpc_konsumtif)
    TextFieldBoxes tf_rpc_konsumtif;
    @BindView(R.id.et_rpc_konsummtif)
    EditText et_rpc_konsummtif;

    @BindView(R.id.ll_datafinansial)
    LinearLayout ll_datafinansial;

    @BindView(R.id.tv_label_topup)
    TextView tv_label_topup;

    @BindView(R.id.tv_label_takeover)
    TextView tv_label_takeover;
    int maxJw = 0;
    Double maxMargin = 0d;

    BigDecimal rateAnuitas = new BigDecimal(0);


    //variabel buat menghitung berapa field yang udah lolos validasi
    private int jumlahValidasi = 0;
    int idAplikasi = 0;
    String cif, approved;


    //VALUE
    private static String val_jenis_tiering = "";

    Long nilaiAngsuranAppel=0l;
    Long nilaiAngsuranAppelKonsumtif=0l;


    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    DataFinansial dataFinansial;
    DataFinansial dataInstansi;
    DataFinansial dataQanun;
    DataFinansial dataAngsuran;
    String tujuanPenggunaanDepan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konsumer_data_finansial_kmg);

//        //push up when keyboard shown
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ButterKnife.bind(this);

        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
//        setGps();
        backgroundStatusBar();
//        checkCollapse();
        tv_page_title.setText("Form Data Finansial");
        btn_back.setOnClickListener(this);
        btn_send.setOnClickListener(this);

        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        cif = getIntent().getStringExtra("cif");
        approved = getIntent().getStringExtra("approved");
        tujuanPenggunaanDepan=getIntent().getStringExtra("tujuanPembiayaan");
//        Toast.makeText(this, "bug 10 mar", Toast.LENGTH_SHORT).show();


        //pantekan intent
//
//        idAplikasi =344703;
//        cif = "0";
//        approved = "no";
//        tujuanPenggunaanDepan="beli rumah";
//        Toast.makeText(this, "bug 10 mar", Toast.LENGTH_SHORT).show();



        //disable edittexts based on approved or not
        if (approved.equalsIgnoreCase("no")) {
            setDynamicIcon();
        }

        if (approved.equalsIgnoreCase("yes")) {
            AppUtil.disableEditTexts(ll_datafinansial);
            btn_send.setVisibility(View.GONE);
            btn_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            btn_back.setOnClickListener(this);
        }

        onclickSelectDialog();

        loadData();


        //rupiah formatting
        et_angsuran_pinjaman_eksisting_1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_angsuran_pinjaman_eksisting_1));
        et_nilai_permohonan_pembiayaan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilai_permohonan_pembiayaan));
        et_gaji_pokok.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_gaji_pokok));
        et_tunjangan_tetap_lainnya.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_tunjangan_tetap_lainnya));
        et_penghasilan_bersih.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_penghasilan_bersih));
        et_harga_beli.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_harga_beli));
        et_jumlah_plafon_pembiayaan_diusulkan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_jumlah_plafon_pembiayaan_diusulkan));
        et_angsuran.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_angsuran));
        et_kewajiban_lain.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_kewajiban_lain));
        et_uang_muka.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_uang_muka));
        et_jumlah_plafon_pembiayaan_konsumtif.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_jumlah_plafon_pembiayaan_konsumtif));
        et_angsuran_konsumtif.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_angsuran_konsumtif));


        //disable edittexts
        et_angsuran_pinjaman_eksisting_1.setFocusable(false);
        et_penghasilan_bersih.setFocusable(false);
//        et_margin_pertahun.setFocusable(false);
        et_rpc.setFocusable(false);
        et_angsuran.setFocusable(false);
        et_tujuan_penggunaan.setFocusable(false);
        et_nilai_permohonan_pembiayaan.setFocusable(false);
        et_uang_muka.setFocusable(false);
        et_jangka_waktu_qardh.setFocusable(false);

        et_angsuran_konsumtif.setFocusable(false);
        et_rpc_konsummtif.setFocusable(false);
//        et_margin_pertahun_konsumtif.setFocusable(false);
//        et_jumlah_plafon_pembiayaan_konsumtif.setFocusable(false);




    }

    private void setData() {


        //pakai textchanged khusus jika pembiayaan takeover topup, karena total plafonnya beda
        if (dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")) {
            Log.d("marsupilamitujuan",dataFinansial.getIDTUJUAN());
            Log.d("marsupilami","masuk takeover");
            textChangedForDataFinansialTakeOver();
        }
        else{
            Log.d("marsupilamitujuan",dataFinansial.getIDTUJUAN());
            Log.d("marsupilami","masuk non-takeover");
            textChangedForDataFinansial();
        }




        //isi kolom hari qardh kalau takeover
        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("91")){
            tf_jangka_waktu_qardh.setVisibility(View.VISIBLE);
            et_jangka_waktu_qardh.setText("14");
            et_harga_beli.setText("0");
        }

        //kalau takeover topup, harga beli jangan diilangin
        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")){
            tf_jangka_waktu_qardh.setVisibility(View.VISIBLE);
            et_jangka_waktu_qardh.setText("14");
        }

//ini ambil dari keyvalue pantekan, dan dia tidak dinamis, jadi di c0mment aje
//        et_tujuan_penggunaan.setText(KeyValue.getKeyTujuanPenggunaanKmg(dataFinansial.getIDTUJUAN()));

        //ini ngambil dari halaman hotprospek detail, jadi udah sesuai dari respon DB
        et_tujuan_penggunaan.setText(tujuanPenggunaanDepan);
        et_gaji_pokok.setText(dataFinansial.getOMZETPERBULAN());





//        et_nilai_permohonan_pembiayaan.setText(AppUtil.parseRupiahLongNoSymbol(dataFinansial.getPLAFONDINDUK())); //ambil dari pipeline sesuai pengajuan
//
//        String parsedTunjanganTetapLainnya=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getPENGHASILANTETAPLAIN());
//        String parsedAngsuranPinjamanEksisting=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getANGSURAN());


        et_tunjangan_tetap_lainnya.setText(String.valueOf(dataFinansial.getPENGHASILANTETAPLAIN()));
        if (et_tunjangan_tetap_lainnya.getText().toString().equalsIgnoreCase("000")) {
            et_tunjangan_tetap_lainnya.setText("0");
        }

        //for some reason calling datafinansial.getangsuran returns 0 eventhough it has already been set in load data with a value.
        //this is a workaround for now
//        Log.d("masbaydatafinansial",String.valueOf(dataFinansial.getANGSURAN()));
        et_angsuran_pinjaman_eksisting_1.setText(String.valueOf(dataFinansial.getANGSURAN()));
        if (et_angsuran_pinjaman_eksisting_1.getText().toString().equalsIgnoreCase("000")) {
            et_angsuran_pinjaman_eksisting_1.setText("0");
        }



//        et_margin_pertahun.setText(dataFinansial.); //ini ambil dari data instansi


        //referensi buat bigdecimal
//        valBd_grossProfitMargin = (valBd_pendapatanUsaha.subtract(valBd_hargapokokPenjualan)).divide(valBd_pendapatanUsaha, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(1, BigDecimal.ROUND_HALF_UP);


        //set margin dari service
        //margin biasa
        if(dataFinansial.getSUKU_MARGIN()!=null&& !dataFinansial.getSUKU_MARGIN().equalsIgnoreCase("0")){
            et_margin_pertahun.setText(dataFinansial.getSUKU_MARGIN());

        }

        //margin konsumtif (khusus TO dan topup
        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")){
            if(dataFinansial.getSUKU_MARGIN_KONSUMTIF()!=null&& !dataFinansial.getSUKU_MARGIN_KONSUMTIF().equalsIgnoreCase("0")){
                et_margin_pertahun_konsumtif.setText(dataFinansial.getSUKU_MARGIN_KONSUMTIF());

            }
        }

        final BigDecimal penghasilanBersih = (new BigDecimal(dataFinansial.getOMZETPERBULAN()).add(new BigDecimal(dataFinansial.getPENGHASILANTETAPLAIN()))).subtract(new BigDecimal(dataFinansial.getANGSURAN()));
        et_penghasilan_bersih.setText(penghasilanBersih.toString()); //ini kalkulasi gaji pokok + penghasilan lain - angsuran eksisting


        et_jangka_waktu.setText(dataFinansial.getJANGKAWAKTU());
        et_jangka_waktu_takeover.setText(dataFinansial.getJANGKA_WAKTU_TO());

//        et_jumlah_plafon_pembiayaan_diusulkan.setText(dataFinansial.getPLAFONDYANGDIUSULKAN());
//        et_harga_beli.setText(dataFinansial.getPERMOHONANKREDIT());
//        et_rpc.setText(); //ini dari data instansi

//        et_jumlah_plafon_pembiayaan_diusulkan.setText(AppUtil.parseRupiah(dataFinansial.getPLAFONDYANGDIUSULKAN()));
//        et_harga_beli.setText(AppUtil.parseRupiah(dataFinansial.getPERMOHONANKREDIT()));

//        String parsedJumlahPlafon=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getPLAFONDYANGDIUSULKAN());
//        String parsedHargaBeli=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getPERMOHONANKREDIT());
//        String parsedPlafondInduk=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getPLAFONDINDUK());
        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")){
            et_jumlah_plafon_pembiayaan_diusulkan.setText(String.valueOf(dataFinansial.getPLAFONDTAKEOVER()));
            if (et_jumlah_plafon_pembiayaan_diusulkan.getText().toString().equalsIgnoreCase("000")) {
                et_jumlah_plafon_pembiayaan_diusulkan.setText("0");
            }
        }
        else{
            et_jumlah_plafon_pembiayaan_diusulkan.setText(String.valueOf(dataFinansial.getPLAFONDYANGDIUSULKAN()));
            if (et_jumlah_plafon_pembiayaan_diusulkan.getText().toString().equalsIgnoreCase("000")) {
                et_jumlah_plafon_pembiayaan_diusulkan.setText("0");
            }
        }


        //kalau takeover, get data konsumtif
        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")){
            et_jumlah_plafon_pembiayaan_konsumtif.setText(String.valueOf(dataFinansial.getPLAFOND_KONSUMTIF()));
            if (et_jumlah_plafon_pembiayaan_konsumtif.getText().toString().equalsIgnoreCase("000")) {
                et_jumlah_plafon_pembiayaan_konsumtif.setText("0");
            }
        }

        et_harga_beli.setText(String.valueOf(dataFinansial.getHARGAPROPERTY()));
        if (et_harga_beli.getText().toString().equalsIgnoreCase("000")) {
            et_harga_beli.setText("0");
        }

        et_nilai_permohonan_pembiayaan.setText(String.valueOf(dataFinansial.getPLAFONDINDUK()));
        et_kewajiban_lain.setText(String.valueOf(dataFinansial.getBIAYALAINLAIN()));

        //


        //set margin rate - DI COMMENT KARENA SEKARANG DAPET VARIABEL SUKU MARGIN DARI SERVICE
//        if (Integer.parseInt(dataFinansial.getJANGKAWAKTU()) > maxJw) {
//            Toast.makeText(DataFinansialKmgActivity.this, "Jangka waktu tidak boleh melebihi " + maxJw + " bulan", Toast.LENGTH_SHORT).show();
//
////            if(Integer.toString(maxMargin).length()<=2){
////                et_margin_pertahun.setText(Integer.toString(maxMargin).substring(0,2)+"."+Integer.toString(maxMargin).substring(2));
////            }
//            et_margin_pertahun.setText(Double.toString(maxMargin));
//            et_jangka_waktu_takeover.setText(Integer.toString(maxJw));
//            et_jangka_waktu_takeover.setSelection(et_jangka_waktu_takeover.getText().length());
//
//            et_margin_pertahun_konsumtif.setText(Double.toString(maxMargin));
//            et_jangka_waktu.setText(Integer.toString(maxJw));
//            et_jangka_waktu.setSelection(et_jangka_waktu.getText().length());
//        }
//
//        if (Integer.parseInt(dataFinansial.getJANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW1()) && !dataFinansial.getMAXJW1().equalsIgnoreCase("0")) {
//            et_margin_pertahun.setText(dataFinansial.getMARGINRATE1());
//        } else if (Integer.parseInt(dataFinansial.getJANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW2()) && !dataFinansial.getMAXJW2().equalsIgnoreCase("0")) {
//            et_margin_pertahun.setText(dataFinansial.getMARGINRATE2());
//        } else if (Integer.parseInt(dataFinansial.getJANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW3()) && !dataFinansial.getMAXJW3().equalsIgnoreCase("0")) {
//            et_margin_pertahun.setText(dataFinansial.getMARGINRATE3());
//        } else if (Integer.parseInt(dataFinansial.getJANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW4()) && !dataFinansial.getMAXJW4().equalsIgnoreCase("0")) {
//            et_margin_pertahun.setText(dataFinansial.getMARGINRATE4());
//        }
//
//        //khusus takeover topup
//        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")){
//            if (Integer.parseInt(dataFinansial.getJANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW1()) && !dataFinansial.getMAXJW1().equalsIgnoreCase("0")) {
//                et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE1());
//            } else if (Integer.parseInt(dataFinansial.getJANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW2()) && !dataFinansial.getMAXJW2().equalsIgnoreCase("0")) {
//                et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE2());
//            } else if (Integer.parseInt(dataFinansial.getJANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW3()) && !dataFinansial.getMAXJW3().equalsIgnoreCase("0")) {
//                et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE3());
//            } else if (Integer.parseInt(dataFinansial.getJANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW4()) && !dataFinansial.getMAXJW4().equalsIgnoreCase("0")) {
//                et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE4());
//            }
//        }







//         rateAnuitas=(new BigDecimal(dataFinansial.getPLAFOND_INDUK()).multiply((new BigDecimal(et_margin_pertahun.getText().toString()).divide(new BigDecimal("12")))).multiply((new BigDecimal(et_margin_pertahun.getText().toString()).divide(new BigDecimal("12"),2, RoundingMode.HALF_UP))).divide(new BigDecimal("1").subtract((new BigDecimal(1).add((new BigDecimal(et_margin_pertahun.getText().toString()).divide(new BigDecimal("12")))).pow(Integer.parseInt(et_jangka_waktu.getText().toString())))), 2, RoundingMode.HALF_UP)).setScale(1, BigDecimal.ROUND_HALF_UP);


//62839225.84
        //set rate anuitas
//        Double ratePerTahun = Double.parseDouble(et_margin_pertahun.getText().toString()) / 100 / 12;
//
//        //ini rumus aslinya, karena double gakbisa menampung hasil perpangkatan yang besar, jadi dipecah ke big decimal dibawah
////        Double pengaliPlafon=ratePerTahun/(1-(1/(Math.pow(1+ratePerTahun,Integer.parseInt(et_jangka_waktu.getText().toString())))));
//
//        BigDecimal pangkatPembagi = (new BigDecimal(1).add(new BigDecimal(ratePerTahun))).pow(Integer.parseInt(et_jangka_waktu
//                .getText().toString()));
//
//        BigDecimal pengaliPlafon = new BigDecimal(ratePerTahun).divide(new BigDecimal(1).subtract((new BigDecimal(1).divide(pangkatPembagi, 15, RoundingMode.HALF_UP))), 15, RoundingMode.HALF_UP).setScale(15, RoundingMode.HALF_UP);
//        ;
//
////        BigDecimal Angsuran=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(new BigDecimal(pengaliPlafon)).setScale(2,RoundingMode.HALF_UP);
//
//        BigDecimal Angsuran = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(pengaliPlafon).setScale(2, RoundingMode.HALF_UP);
//
//        et_angsuran.setText(String.valueOf(Angsuran));
//
//
////        Toast.makeText(this, String.valueOf(Angsuran), Toast.LENGTH_LONG).show();
////        Log.d("anuitas",String.valueOf(rateAnuitas));
//
//        //set rpc
//        BigDecimal rpcValue = Angsuran.multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())), 2, RoundingMode.HALF_UP);
//
//        et_rpc.setText(String.valueOf(rpcValue));

        //ini ngitung angsuran dari rumus appel, jadi tipe datanya long bro, buset dah



        //khusus takeover topup, memang ngeset angsuran takeover dan topup
        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")){
            nilaiAngsuranAppelKonsumtif=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun_konsumtif.getText().toString()),Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString())),Integer.parseInt(et_jangka_waktu.getText().toString()));

            //ini ngitung RPC dengan nilai dari appel
            BigDecimal rpcValueKonsumtif=new BigDecimal(nilaiAngsuranAppelKonsumtif).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())),2,RoundingMode.HALF_UP);

            et_rpc_konsummtif.setText(String.valueOf(rpcValueKonsumtif));

            et_angsuran_konsumtif.setText(String.valueOf(nilaiAngsuranAppelKonsumtif));



            nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())),Integer.parseInt(et_jangka_waktu_takeover.getText().toString()));

            //ini ngitung RPC dengan nilai dari appel
            BigDecimal rpcValue=new BigDecimal(nilaiAngsuranAppel).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())),2,RoundingMode.HALF_UP);


            et_rpc.setText(String.valueOf(rpcValue));

            et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));


        }
        else{
            nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())),Integer.parseInt(et_jangka_waktu
                    .getText().toString()));

            et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));

            //ini ngitung RPC dengan nilai dari appel
            BigDecimal rpcValue=new BigDecimal(nilaiAngsuranAppel).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())),2,RoundingMode.HALF_UP);


            et_rpc.setText(String.valueOf(rpcValue));
        }





//        Toast.makeText(this, String.valueOf(Angsuran), Toast.LENGTH_LONG).show();
//        Log.d("anuitas",String.valueOf(rateAnuitas));

        //set rpc
//        BigDecimal rpcValue=Angsuran.multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())),2,RoundingMode.HALF_UP);








    }

    private void textChangedForDataFinansial() {

        Log.d("baris 383", "mengakses kelas ini");

        et_gaji_pokok.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    if (s.length() > 0) {

                        //perubahan penghasilan bersih
                        BigDecimal penghasilanBersih = (new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString()))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString())));

                        et_penghasilan_bersih.setText(penghasilanBersih.toString());


                    } else {
                        BigDecimal penghasilanBersihZero = new BigDecimal("0").subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString()))));

                        et_penghasilan_bersih.setText(penghasilanBersihZero.toString());

                    }
                } catch (Exception e) {
                    Log.d("exceptional 412", e.getMessage());
                }


            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_tunjangan_tetap_lainnya.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    if (s.length() > 0 && et_kewajiban_lain.getText().length() > 0) {
                        BigDecimal penghasilanBersih = (new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString()))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString())));

                        et_penghasilan_bersih.setText(penghasilanBersih.toString());
                    } else {
                        BigDecimal penghasilanBersihZero = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString())).add(new BigDecimal("0")));

                        et_penghasilan_bersih.setText(penghasilanBersihZero.toString());

                    }

                } catch (Exception e) {
                    Log.d("exceptional 454", e.getMessage());
                }


            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_kewajiban_lain.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {


                    if (s.length() > 0 && et_penghasilan_bersih.getText().length() > 0) {
                        BigDecimal penghasilanBersih = (new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString()))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString())));

                        et_penghasilan_bersih.setText(penghasilanBersih.toString());
                    } else {
                        BigDecimal penghasilanBersihZero = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString())).add(new BigDecimal("0")));

                        et_penghasilan_bersih.setText(penghasilanBersihZero.toString());

                    }

                } catch (Exception e) {
                    Log.d("exceptional 496", e.getMessage());
                }


            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_kewajiban_lain.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false && et_kewajiban_lain.getText().toString().equalsIgnoreCase("")) {
                    et_kewajiban_lain.setText("0");
                }
            }
        });

        et_tunjangan_tetap_lainnya.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false && et_tunjangan_tetap_lainnya.getText().toString().equalsIgnoreCase("")) {
                    et_tunjangan_tetap_lainnya.setText("0");
                }
            }
        });


        et_margin_pertahun.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {


                    //set rate anuitas
//                    Double ratePerTahun = Double.parseDouble(et_margin_pertahun.getText().toString()) / 100 / 12;
//                    BigDecimal pangkatPembagi = (new BigDecimal(1).add(new BigDecimal(ratePerTahun))).pow(Integer.parseInt(et_jangka_waktu
//                            .getText().toString()));
//
//                    BigDecimal pengaliPlafon = new BigDecimal(ratePerTahun).divide(new BigDecimal(1).subtract((new BigDecimal(1).divide(pangkatPembagi, 15, RoundingMode.HALF_UP))), 15, RoundingMode.HALF_UP).setScale(15, RoundingMode.HALF_UP);
//                    ;
//
////        BigDecimal Angsuran=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(new BigDecimal(pengaliPlafon)).setScale(2,RoundingMode.HALF_UP);
//
//                    BigDecimal Angsuran = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(pengaliPlafon).setScale(2, RoundingMode.HALF_UP);
//
//                    et_angsuran.setText(String.valueOf(Angsuran));

                    //ini ngitung angsuran dari rumus appel, jadi tipe datanya long bro, buset dah

                    if(Float.parseFloat(et_margin_pertahun.getText().toString())!=0f&&!dataFinansial.getJANGKAWAKTU().equalsIgnoreCase("0")){
                        nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())),Integer.parseInt(et_jangka_waktu
                                .getText().toString()));

                        et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));
                    }

                } catch (Exception e) {
                    Log.d("exceptional 560", e.getMessage());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_jumlah_plafon_pembiayaan_diusulkan
                .addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        try {


                            //INI NGITUNG ANGSURAN DENGAN BIG DECIMAL

                            //perubahan angsuran
                            //set rate anuitas
//                            Double ratePerTahun = Double.parseDouble(et_margin_pertahun.getText().toString()) / 100 / 12;
//                            BigDecimal pangkatPembagi = (new BigDecimal(1).add(new BigDecimal(ratePerTahun))).pow(Integer.parseInt(et_jangka_waktu
//                                    .getText().toString()));
//
//                            BigDecimal pengaliPlafon = new BigDecimal(ratePerTahun).divide(new BigDecimal(1).subtract((new BigDecimal(1).divide(pangkatPembagi, 15, RoundingMode.HALF_UP))), 15, RoundingMode.HALF_UP).setScale(15, RoundingMode.HALF_UP);
//                            ;
//
////        BigDecimal Angsuran=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(new BigDecimal(pengaliPlafon)).setScale(2,RoundingMode.HALF_UP);
//
//                            BigDecimal Angsuran = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(pengaliPlafon).setScale(2, RoundingMode.HALF_UP);
//
//                            et_angsuran.setText(String.valueOf(Angsuran));


                            //ini ngitung angsuran dari rumus appel, jadi tipe datanya long bro, buset dah

                            nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())),Integer.parseInt(et_jangka_waktu
                                    .getText().toString()));

                            et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));


                            //jika harga beli dan plafon tidak kosong
                            if (!et_harga_beli.getText().toString().isEmpty() && !et_jumlah_plafon_pembiayaan_diusulkan.getText().toString().isEmpty()) {
                                //perubahan uang muka
                                BigDecimal uangMukaBD = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_harga_beli.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())));
                                et_uang_muka.setText(uangMukaBD.toString());
                            }


                        } catch (Exception e) {
                            Log.d("exceptional 599", e.getMessage());
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

        et_harga_beli
                .addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        try {

                            //jika harga beli dan plafon tidak kosong
                            if (!et_harga_beli.getText().toString().isEmpty() && !et_jumlah_plafon_pembiayaan_diusulkan.getText().toString().isEmpty()) {


                                //perubahan uang muka
                                BigDecimal uangMukaBD = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_harga_beli.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())));
                                et_uang_muka.setText(uangMukaBD.toString());
                            }


                        } catch (Exception e) {
                            Log.d("exceptional 599", e.getMessage());
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

        et_penghasilan_bersih.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {


                    if (s != null && s.length() > 0 && !new BigDecimal(s.toString()).equals(0)) {

                        BigDecimal rpcValue = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran.getText().toString())).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())), 2, RoundingMode.HALF_UP);

                        et_rpc.setText(String.valueOf(rpcValue));
                    }
                } catch (Exception e) {
                    Log.d("exceptional 630", e.getMessage());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_angsuran.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                BigDecimal rpcValue = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran.getText().toString())).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())), 2, RoundingMode.HALF_UP);

                et_rpc.setText(String.valueOf(rpcValue));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //memastikan bahwa plafon tidak bisa diedit sebelum jangka waktu diisi, karna dalam rumus angsuran, ada pembagian dengan jangka waktu, jadi kalo diisi 0 nanti dia error division by zero
        et_jumlah_plafon_pembiayaan_diusulkan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && et_jangka_waktu.getText().toString().isEmpty()) {
                    tf_jangka_waktu.setError("Harap isi jangka waktu", true);
                    AppUtil.notiferror(DataFinansialKmgActivity.this, findViewById(android.R.id.content), "Harap isi jangka waktu sebelum mengubah plafon diusulkan");
                    Log.d("ekiduper"," masuk kedalam non takeover");
                }

                if (hasFocus && et_margin_pertahun.getText().toString().isEmpty()) {
                    tf_margin_pertahun.setError("Harap isi margin terlebih dahulu", true);
                    AppUtil.notiferror(DataFinansialKmgActivity.this, findViewById(android.R.id.content), "Harap isi margin sebelum mengubah plafon diusulkan");
                    Log.d("ekiduper"," masuk kedalam non takeover");
                }


            }
        });

        et_jangka_waktu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Log.d("max jw", Integer.toString(maxJw));
                if (s != null && s.length() > 0 && Integer.parseInt(s.toString()) != 0) {
                    if (Integer.parseInt(et_jangka_waktu.getText().toString()) > maxJw) {
                        Toast.makeText(DataFinansialKmgActivity.this, "Jangka waktu tidak boleh melebihi " + maxJw + " bulan", Toast.LENGTH_SHORT).show();
                        et_jangka_waktu.setText(Integer.toString(maxJw));
                        et_jangka_waktu.setSelection(et_jangka_waktu.getText().length());
                    }

                    //kalau dari qanun, tidak ambil margin dari margin rate, tapi sakarepe dewe AOnya, di validasi di service

                    if(dataFinansial.getQanun()!=null&&dataFinansial.getQanun().equalsIgnoreCase("true")){
                        if(dataFinansial.getSUKU_MARGIN()!=null&&!dataFinansial.getSUKU_MARGIN().equalsIgnoreCase("0")){
                            Long totalTakeover=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString()));

                            nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),totalTakeover,Integer.parseInt(et_jangka_waktu.getText().toString()));

                            et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));
                        }

                    }

                    //selain qanun, masih ambil margin berdasarkan marginrate per instansi

                    else{
                        if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW1()) && !dataFinansial.getMAXJW1().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE1().equalsIgnoreCase("0")) {
                            et_margin_pertahun.setText(dataFinansial.getMARGINRATE1());
                        } else if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW2()) && !dataFinansial.getMAXJW2().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE2().equalsIgnoreCase("0")) {
                            et_margin_pertahun.setText(dataFinansial.getMARGINRATE2());
                        } else if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW3()) && !dataFinansial.getMAXJW3().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE3().equalsIgnoreCase("0")) {
                            et_margin_pertahun.setText(dataFinansial.getMARGINRATE3());
                        } else if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW4()) && !dataFinansial.getMAXJW4().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE4().equalsIgnoreCase("0")) {
                            et_margin_pertahun.setText(dataFinansial.getMARGINRATE4());
                        }

                        Long totalTakeover=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString()));

                        nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),totalTakeover,Integer.parseInt(et_jangka_waktu.getText().toString()));

                        et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));

                    }



                } else {
                    if (s.length() > 0) {
                        Toast.makeText(DataFinansialKmgActivity.this, "Jangka waktu tidak boleh 0 ", Toast.LENGTH_SHORT).show();
                        et_jangka_waktu.setText("1");

                        et_jangka_waktu.setSelection(et_jangka_waktu.getText().length());
                    }

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void textChangedForDataFinansialTakeOver() {

        et_gaji_pokok.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    if (s.length() > 0) {

                        //perubahan penghasilan bersih
                        BigDecimal penghasilanBersih = (new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString()))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString())));

                        et_penghasilan_bersih.setText(penghasilanBersih.toString());


                    } else {
                        BigDecimal penghasilanBersihZero = new BigDecimal("0").subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString()))));

                        et_penghasilan_bersih.setText(penghasilanBersihZero.toString());

                    }
                } catch (Exception e) {
                    Log.d("exceptional 412", e.getMessage());
                }


            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_tunjangan_tetap_lainnya.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    if (s.length() > 0 && et_kewajiban_lain.getText().length() > 0) {
                        BigDecimal penghasilanBersih = (new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString()))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString())));

                        et_penghasilan_bersih.setText(penghasilanBersih.toString());
                    } else {
                        BigDecimal penghasilanBersihZero = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString())).add(new BigDecimal("0")));

                        et_penghasilan_bersih.setText(penghasilanBersihZero.toString());

                    }

                } catch (Exception e) {
                    Log.d("exceptional 454", e.getMessage());
                }


            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_kewajiban_lain.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {


                    if (s.length() > 0 && et_penghasilan_bersih.getText().length() > 0) {
                        BigDecimal penghasilanBersih = (new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString()))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString())));

                        et_penghasilan_bersih.setText(penghasilanBersih.toString());
                    } else {
                        BigDecimal penghasilanBersihZero = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString())).add(new BigDecimal("0")));

                        et_penghasilan_bersih.setText(penghasilanBersihZero.toString());

                    }

                } catch (Exception e) {
                    Log.d("exceptional 496", e.getMessage());
                }


            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_kewajiban_lain.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false && et_kewajiban_lain.getText().toString().equalsIgnoreCase("")) {
                    et_kewajiban_lain.setText("0");
                }
            }
        });

        et_tunjangan_tetap_lainnya.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false && et_tunjangan_tetap_lainnya.getText().toString().equalsIgnoreCase("")) {
                    et_tunjangan_tetap_lainnya.setText("0");
                }
            }
        });


        et_margin_pertahun.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {


                    //set rate anuitas
//                    Double ratePerTahun = Double.parseDouble(et_margin_pertahun.getText().toString()) / 100 / 12;
//                    BigDecimal pangkatPembagi = (new BigDecimal(1).add(new BigDecimal(ratePerTahun))).pow(Integer.parseInt(et_jangka_waktu
//                            .getText().toString()));
//
//                    BigDecimal pengaliPlafon = new BigDecimal(ratePerTahun).divide(new BigDecimal(1).subtract((new BigDecimal(1).divide(pangkatPembagi, 15, RoundingMode.HALF_UP))), 15, RoundingMode.HALF_UP).setScale(15, RoundingMode.HALF_UP);
//                    ;
//
////        BigDecimal Angsuran=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(new BigDecimal(pengaliPlafon)).setScale(2,RoundingMode.HALF_UP);
//
//                    BigDecimal Angsuran = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(pengaliPlafon).setScale(2, RoundingMode.HALF_UP);
//
//                    et_angsuran.setText(String.valueOf(Angsuran));

                    //ini ngitung angsuran dari rumus appel, jadi tipe datanya long bro, buset dah

                    if(Float.parseFloat(et_margin_pertahun.getText().toString())!=0f&&!dataFinansial.getJANGKA_WAKTU_TO().equalsIgnoreCase("0")){
                        nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())),Integer.parseInt(et_jangka_waktu_takeover.getText().toString()));

                        et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));
                    }


                } catch (Exception e) {
                    Log.d("exceptional 560", e.getMessage());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_margin_pertahun_konsumtif.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {


                    //set rate anuitas
//                    Double ratePerTahun = Double.parseDouble(et_margin_pertahun.getText().toString()) / 100 / 12;
//                    BigDecimal pangkatPembagi = (new BigDecimal(1).add(new BigDecimal(ratePerTahun))).pow(Integer.parseInt(et_jangka_waktu
//                            .getText().toString()));
//
//                    BigDecimal pengaliPlafon = new BigDecimal(ratePerTahun).divide(new BigDecimal(1).subtract((new BigDecimal(1).divide(pangkatPembagi, 15, RoundingMode.HALF_UP))), 15, RoundingMode.HALF_UP).setScale(15, RoundingMode.HALF_UP);
//                    ;
//
////        BigDecimal Angsuran=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(new BigDecimal(pengaliPlafon)).setScale(2,RoundingMode.HALF_UP);
//
//                    BigDecimal Angsuran = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(pengaliPlafon).setScale(2, RoundingMode.HALF_UP);
//
//                    et_angsuran.setText(String.valueOf(Angsuran));

                    //ini ngitung angsuran dari rumus appel, jadi tipe datanya long bro, buset dah

                    if(Float.parseFloat(et_margin_pertahun_konsumtif.getText().toString())!=0f&&!dataFinansial.getJANGKAWAKTU().equalsIgnoreCase("0")){
                        Long totalTakeoverKonsumtif=(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString())));

                        nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun_konsumtif.getText().toString()),totalTakeoverKonsumtif,Integer.parseInt(et_jangka_waktu.getText().toString()));

                        et_angsuran_konsumtif.setText(String.valueOf(nilaiAngsuranAppel));
                    }


                } catch (Exception e) {
                    Log.d("exceptional 560", e.getMessage());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_jumlah_plafon_pembiayaan_konsumtif
                .addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        try {


                            //INI NGITUNG ANGSURAN DENGAN BIG DECIMAL

                            //perubahan angsuran
                            //set rate anuitas
//                            Double ratePerTahun = Double.parseDouble(et_margin_pertahun.getText().toString()) / 100 / 12;
//                            BigDecimal pangkatPembagi = (new BigDecimal(1).add(new BigDecimal(ratePerTahun))).pow(Integer.parseInt(et_jangka_waktu
//                                    .getText().toString()));
//
//                            BigDecimal pengaliPlafon = new BigDecimal(ratePerTahun).divide(new BigDecimal(1).subtract((new BigDecimal(1).divide(pangkatPembagi, 15, RoundingMode.HALF_UP))), 15, RoundingMode.HALF_UP).setScale(15, RoundingMode.HALF_UP);
//                            ;
//
////        BigDecimal Angsuran=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(new BigDecimal(pengaliPlafon)).setScale(2,RoundingMode.HALF_UP);
//
//                            BigDecimal Angsuran = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(pengaliPlafon).setScale(2, RoundingMode.HALF_UP);
//
//                            et_angsuran.setText(String.valueOf(Angsuran));


                            //ini ngitung angsuran dari rumus appel, jadi tipe datanya long bro, buset dah

                            Long totalTakeoverKonsumtif=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString()));

                            nilaiAngsuranAppelKonsumtif=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun_konsumtif.getText().toString()),totalTakeoverKonsumtif,Integer.parseInt(et_jangka_waktu.getText().toString()));

                            et_angsuran_konsumtif.setText(String.valueOf(nilaiAngsuranAppelKonsumtif));


                            //jika harga beli dan plafon tidak kosong
                            if (!et_harga_beli.getText().toString().isEmpty() && !et_jumlah_plafon_pembiayaan_konsumtif.getText().toString().isEmpty()) {
                                //perubahan uang muka
                                BigDecimal uangMukaBD = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_harga_beli.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString())));
                                et_uang_muka.setText(uangMukaBD.toString());
                            }


                        } catch (Exception e) {
                            Log.d("exceptional 599", e.getMessage());
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

        et_jumlah_plafon_pembiayaan_diusulkan
                .addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        try {


                            //INI NGITUNG ANGSURAN DENGAN BIG DECIMAL

                            //perubahan angsuran
                            //set rate anuitas
//                            Double ratePerTahun = Double.parseDouble(et_margin_pertahun.getText().toString()) / 100 / 12;
//                            BigDecimal pangkatPembagi = (new BigDecimal(1).add(new BigDecimal(ratePerTahun))).pow(Integer.parseInt(et_jangka_waktu
//                                    .getText().toString()));
//
//                            BigDecimal pengaliPlafon = new BigDecimal(ratePerTahun).divide(new BigDecimal(1).subtract((new BigDecimal(1).divide(pangkatPembagi, 15, RoundingMode.HALF_UP))), 15, RoundingMode.HALF_UP).setScale(15, RoundingMode.HALF_UP);
//                            ;
//
////        BigDecimal Angsuran=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(new BigDecimal(pengaliPlafon)).setScale(2,RoundingMode.HALF_UP);
//
//                            BigDecimal Angsuran = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(pengaliPlafon).setScale(2, RoundingMode.HALF_UP);
//
//                            et_angsuran.setText(String.valueOf(Angsuran));


                            //ini ngitung angsuran dari rumus appel, jadi tipe datanya long bro, buset dah

                            Long totalTakeoverKonsumtif=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString()));

                            nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),totalTakeoverKonsumtif,Integer.parseInt(et_jangka_waktu_takeover.getText().toString()));

                            et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));



                        } catch (Exception e) {
                            Log.d("exceptional 599", e.getMessage());
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

        et_harga_beli
                .addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        try {

                            //jika harga beli dan plafon tidak kosong
                            if (!et_harga_beli.getText().toString().isEmpty() && !et_jumlah_plafon_pembiayaan_konsumtif.getText().toString().isEmpty()) {


                                //perubahan uang muka
                                BigDecimal uangMukaBD = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_harga_beli.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString())));
                                et_uang_muka.setText(uangMukaBD.toString());
                            }


                        } catch (Exception e) {
                            Log.d("exceptional 599", e.getMessage());
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

        et_penghasilan_bersih.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {


                    if (s != null && s.length() > 0 && !new BigDecimal(s.toString()).equals(0)) {

                        if(!et_angsuran.getText().toString().isEmpty()&&!et_angsuran_konsumtif.getText().toString().isEmpty()){
                            BigDecimal rpcValue = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran.getText().toString()));
                            BigDecimal rpcKonsumtifValue = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_konsumtif.getText().toString()));

                            BigDecimal totalRpc=(rpcValue.add(rpcKonsumtifValue)).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())), 2, RoundingMode.HALF_UP);

                            et_rpc.setText(String.valueOf(totalRpc));
                        }

//                        et_rpc_konsummtif.setText(String.valueOf(totalRpc));
                    }
                } catch (Exception e) {
                    Log.d("exceptional 630", e.getMessage());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_angsuran.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!et_angsuran.getText().toString().isEmpty()&&!et_angsuran_konsumtif.getText().toString().isEmpty()){
                    BigDecimal rpcValue = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran.getText().toString()));
                    BigDecimal rpcKonsumtifValue = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_konsumtif.getText().toString()));

                    BigDecimal totalRpc=(rpcValue.add(rpcKonsumtifValue)).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())), 2, RoundingMode.HALF_UP);

                    et_rpc.setText(String.valueOf(totalRpc));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_angsuran_konsumtif.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!et_angsuran.getText().toString().isEmpty()&&!et_angsuran_konsumtif.getText().toString().isEmpty()){
                    BigDecimal rpcValue = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran.getText().toString()));
                    BigDecimal rpcKonsumtifValue = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_konsumtif.getText().toString()));

                    BigDecimal totalRpc=(rpcValue.add(rpcKonsumtifValue)).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())), 2, RoundingMode.HALF_UP);

                    et_rpc.setText(String.valueOf(totalRpc));
                }

//                et_rpc_konsummtif.setText(String.valueOf(rpcValue));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //memastikan bahwa plafon tidak bisa diedit sebelum jangka waktu diisi, karna dalam rumus angsuran, ada pembagian dengan jangka waktu, jadi kalo diisi 0 nanti dia error division by zero
        et_jumlah_plafon_pembiayaan_diusulkan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && et_jangka_waktu_takeover.getText().toString().isEmpty()) {
                    tf_jangka_waktu_takeover.setError("Harap isi jangka waktu", true);
                    AppUtil.notiferror(DataFinansialKmgActivity.this, findViewById(android.R.id.content), "Harap isi jangka waktu sebelum mengubah plafon diusulkan");
                    Log.d("ekiduper"," masuk kedalam super takeover ");
                }

                if (hasFocus && (et_gaji_pokok.getText().toString().isEmpty()||et_gaji_pokok.getText().toString().equalsIgnoreCase("0"))) {
                    tf_gaji_pokok.setError("Harap isi gaji pokok", true);
                    AppUtil.notiferror(DataFinansialKmgActivity.this, findViewById(android.R.id.content), "Harap isi gaji pokok sebelum mengubah plafon diusulkan");
                    Log.d("ekiduper"," masuk kedalam jangka wakto non takeover, tapi di textchanged takeover");
                }

                if (hasFocus && (et_margin_pertahun.getText().toString().isEmpty()||et_margin_pertahun.getText().toString().equalsIgnoreCase("0"))) {
                    tf_margin_pertahun.setError("Harap isi margin", true);
                    AppUtil.notiferror(DataFinansialKmgActivity.this, findViewById(android.R.id.content), "Harap isi margin sebelum mengubah plafon diusulkan");
                    Log.d("ekiduper"," masuk kedalam jangka wakto non takeover, tapi di textchanged takeover");
                }
            }
        });


        et_jumlah_plafon_pembiayaan_konsumtif.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && et_jangka_waktu.getText().toString().isEmpty()) {
                    tf_jangka_waktu.setError("Harap isi jangka waktu", true);
                    AppUtil.notiferror(DataFinansialKmgActivity.this, findViewById(android.R.id.content), "Harap isi jangka waktu sebelum mengubah plafon konsumtif");
                }

                if (hasFocus && (et_gaji_pokok.getText().toString().isEmpty()||et_gaji_pokok.getText().toString().equalsIgnoreCase("0"))) {
                    tf_gaji_pokok.setError("Harap isi gaji pokok", true);
                    AppUtil.notiferror(DataFinansialKmgActivity.this, findViewById(android.R.id.content), "Harap isi gaji pokok sebelum mengubah plafon konsumtif");
                }

                if (hasFocus && (et_margin_pertahun_konsumtif.getText().toString().isEmpty()||et_margin_pertahun_konsumtif.getText().toString().equalsIgnoreCase("0"))) {
                    tf_margin_pertahun_konsumtif.setError("Harap isi margin", true);
                    AppUtil.notiferror(DataFinansialKmgActivity.this, findViewById(android.R.id.content), "Harap isi margin sebelum mengubah plafon Top Up");
                    Log.d("ekiduper"," masuk kedalam jangka wakto non takeover, tapi di textchanged takeover");
                }
            }
        });

        et_jangka_waktu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Log.d("max jw", Integer.toString(maxJw));
                if (s != null && s.length() > 0 && Integer.parseInt(s.toString()) != 0) {
                    if (Integer.parseInt(et_jangka_waktu.getText().toString()) > maxJw) {
                        Toast.makeText(DataFinansialKmgActivity.this, "Jangka waktu tidak boleh melebihi " + maxJw + " bulan", Toast.LENGTH_SHORT).show();
                        et_jangka_waktu.setText(Integer.toString(maxJw));
                        et_jangka_waktu.setSelection(et_jangka_waktu.getText().length());
                    }

                    //jika qanun, tidak ambil data margin rate
                    if(dataFinansial.getQanun()!=null&&dataFinansial.getQanun().equalsIgnoreCase("true")){
                        if(et_margin_pertahun_konsumtif.getText()!=null&&!et_margin_pertahun_konsumtif.getText().toString().equalsIgnoreCase("0")&&!et_margin_pertahun_konsumtif.getText().toString().isEmpty()){

                            Long totalTakeover=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString()));

                            nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun_konsumtif.getText().toString()),totalTakeover,Integer.parseInt(et_jangka_waktu.getText().toString()));

                            et_angsuran_konsumtif.setText(String.valueOf(nilaiAngsuranAppel));
                        }



                    }
                    else {
                        if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW1()) && !dataFinansial.getMAXJW1().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE1().equalsIgnoreCase("0")) {
                            et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE1());
                        } else if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW2()) && !dataFinansial.getMAXJW2().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE2().equalsIgnoreCase("0")) {
                            et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE2());
                        } else if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW3()) && !dataFinansial.getMAXJW3().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE3().equalsIgnoreCase("0")) {
                            et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE3());
                        } else if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW4()) && !dataFinansial.getMAXJW4().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE4().equalsIgnoreCase("0")) {
                            et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE4());
                        }

                        Long totalTakeover=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString()));

                        nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun_konsumtif.getText().toString()),totalTakeover,Integer.parseInt(et_jangka_waktu.getText().toString()));

                        et_angsuran_konsumtif.setText(String.valueOf(nilaiAngsuranAppel));

                    }





                } else {
                    if (s.length() > 0) {
                        Toast.makeText(DataFinansialKmgActivity.this, "Jangka waktu tidak boleh 0 ", Toast.LENGTH_SHORT).show();
                        et_jangka_waktu.setText("1");

                        et_jangka_waktu.setSelection(et_jangka_waktu.getText().length());
                    }

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_jangka_waktu_takeover.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Log.d("max jw", Integer.toString(maxJw));
                if (s != null && s.length() > 0 && Integer.parseInt(s.toString()) != 0) {
                    if (Integer.parseInt(et_jangka_waktu_takeover.getText().toString()) > maxJw) {
                        Toast.makeText(DataFinansialKmgActivity.this, "Jangka waktu tidak boleh melebihi " + maxJw + " bulan", Toast.LENGTH_SHORT).show();
                        et_jangka_waktu_takeover.setText(Integer.toString(maxJw));
                        et_jangka_waktu_takeover.setSelection(et_jangka_waktu_takeover.getText().length());
                    }

                    //jika qanun, tidak ambil data margin rate
                    if(dataFinansial.getQanun()!=null&&dataFinansial.getQanun().equalsIgnoreCase("true")){
                        if(et_margin_pertahun.getText()!=null&&!et_margin_pertahun.getText().toString().equalsIgnoreCase("0")&&!et_margin_pertahun.getText().toString().isEmpty()){

                            Long totalTakeoverKonsumtif=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString()));

                            nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),totalTakeoverKonsumtif,Integer.parseInt(et_jangka_waktu_takeover.getText().toString()));

                            et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));
                        }



                    }
                    else{


                        if (Integer.parseInt(et_jangka_waktu_takeover.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW1()) && !dataFinansial.getMAXJW1().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE1().equalsIgnoreCase("0")) {
                            et_margin_pertahun.setText(dataFinansial.getMARGINRATE1());
                        } else if (Integer.parseInt(et_jangka_waktu_takeover.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW2()) && !dataFinansial.getMAXJW2().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE2().equalsIgnoreCase("0")) {
                            et_margin_pertahun.setText(dataFinansial.getMARGINRATE2());
                        } else if (Integer.parseInt(et_jangka_waktu_takeover.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW3()) && !dataFinansial.getMAXJW3().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE3().equalsIgnoreCase("0")) {
                            et_margin_pertahun.setText(dataFinansial.getMARGINRATE3());
                        } else if (Integer.parseInt(et_jangka_waktu_takeover.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW4()) && !dataFinansial.getMAXJW4().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE4().equalsIgnoreCase("0")) {
                            et_margin_pertahun.setText(dataFinansial.getMARGINRATE4());
                        }

                        Long totalTakeoverKonsumtif=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString()));

                        nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),totalTakeoverKonsumtif,Integer.parseInt(et_jangka_waktu_takeover.getText().toString()));

                        et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));
                    }


                } else {
                    if (s.length() > 0) {
                        Toast.makeText(DataFinansialKmgActivity.this, "Jangka waktu tidak boleh 0 ", Toast.LENGTH_SHORT).show();
                        et_jangka_waktu_takeover.setText("1");

                        et_jangka_waktu_takeover.setSelection(et_jangka_waktu_takeover.getText().length());
                    }

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    private void backgroundStatusBar() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorBackgroundTransparent));
        }
    }

    private void checkCollapse() {
//        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangedListener() {
//            @Override
//            public void onStateChanged(AppBarLayout appBarLayout, State state) {
//                if (state.name().equalsIgnoreCase("COLLAPSED")) {
//                    tv_page_title.setVisibility(View.VISIBLE);
//                    btn_takepicture.setVisibility(View.VISIBLE);
//                    tv_page_title.setText("Input Data Nasabah");
//                } else {
//                    tv_page_title.setVisibility(View.GONE);
//                    btn_takepicture.setVisibility(View.GONE);
//                    tv_page_title.setText("");
//                }
//            }
//        });
    }

    private void openProdukDialog(String title, String segmen, String product) {
        DialogSelect.display(getSupportFragmentManager(), title, segmen, product, this);
    }

    private void openKeyValueDialog(String title) {
        title = title.substring(0, title.length() - 2);
        DialogKeyValue.display(getSupportFragmentManager(), title, this);
    }

    private void openAddressDialog() {
        DialogAddress.display(getSupportFragmentManager(), this);
    }

    private void openCameraMenu() {
        BSBottomCamera.display(getSupportFragmentManager(), this);
    }


    //method ketika klik sesuatu
    private void onclickSelectDialog() {
        tf_uang_muka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataFinansial.getIDTUJUAN().equalsIgnoreCase("89")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")) {
                    AppUtil.notifinfo(DataFinansialKmgActivity.this, findViewById(R.id.content), "Uang muka minimal 30% dari harga beli");
                }

            }
        });

    }

    //method untuk mempersingkat method onclickselectdialog^ jadi semua semua onclick ditaruh disini, gak menggunakan implement onclick
    private void subSelectDialog(EditText editText, final TextFieldBoxes textFieldBoxes) {
        editText.setFocusable(false);
        editText.setOnFocusChangeListener(this);
        textFieldBoxes.setOnClickListener(this);
        textFieldBoxes.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(textFieldBoxes.getLabelText().toString().trim());
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    openKeyValueDialog(textFieldBoxes.getLabelText().toString().trim());
                }

            }
        });

        textFieldBoxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(textFieldBoxes.getLabelText().toString().trim());
            }
        });
    }

    //   TIDAK DIGUNAKAN----method ketika on click di edittext yang berupa pilihan/dropdown
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
//            switch (v.getId()) {
//
//                //KONSUMER
//                case R.id.et_tingkat_pendidikan:
//                    openKeyValueDialog(tf_tingkat_pendidikan.getLabelText().toString().trim());
//                    break;
//
//                case R.id.et_jenis_kelamin:
//                    openKeyValueDialog(tf_jenis_kelamin.getLabelText().toString().trim());
//                    break;
//
//                case R.id.et_tanggal_mulai_bekerja:
//                    datePickerTanggalLahir(et_tanggal_mulai_bekerja,tf_tanggal_mulai_bekerja);
//                    break;
//
//                case R.id.et_expired_ktp:
//                    datePickerTanggalLahir(et_expired_ktp,tf_expired_ktp);
//                    break;
//
//                case R.id.et_tanggal_lahir:
//                    datePickerTanggalLahir(et_tanggal_lahir,tf_tanggal_lahir);
//                    break;
//
//                case R.id.et_no_telp:
//                    openKeyValueDialog(tf_no_telp.getLabelText().toString().trim());
//                    break;
//
//
//
//
//                case R.id.et_kepemilikan_tempat_tinggal:
//                    openKeyValueDialog(tf_kepemilikian_tempat_tinggal.getLabelText().toString().trim());
//                    break;
//


            //PROGRAM
//                case R.id.et_program:
//                    if (!et_segmen.getText().toString().trim().isEmpty() && !et_produk.getText().toString().trim().isEmpty()){
//                        openProdukDialog(tf_program.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), et_produk.getText().toString().trim());
//                    }
//                    else{
//                        AppUtil.notifwarning(IsianCifKprActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect)+" "+tf_segmen.getLabelText()+" atau "+tf_produk.getLabelText()+" terlebih dahulu");
//                    }
//                    break;


        }
    }


    //method ketika onclick pada gambar atau textview
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                CustomDialog.DialogBackpress(this);
                break;
            case R.id.btn_pickaddress:
                openAddressDialog();
                break;

            case R.id.btn_send:

                if (validateForm()) {
                    validasiData();
//                    Toast.makeText(DataFinansialKmgActivity.this, "Menyimpan data", Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }

    //method ketika memilih salah satu opsi di dialog fragment, tidak bisa di simplifikasi , eh belum bisa, belum cukup ilmunya :')
    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
//        if (title.equalsIgnoreCase("jenis tiering")) {
//            et_jenis_tiering.setText(data.getName());
//            AppUtil.dynamicIconLogoChangeDropdown(DataFinansialKmgActivity.this, tf_jenis_tiering);
//            val_jenis_tiering = data.getValue(); //set value berdasarkan pilihan di dialog fragment
//            AppUtil.dynamicIconLogoChangeDropdown(DataFinansialKmgActivity.this,tf_jenis_tiering);
//        }

    }


    @Override
    public void onProductSelect(String title, ProductPojo data) {
//        if (title.equalsIgnoreCase("segmen")){
//            et_segmen.setText(data.getNama_segmen());
//            val_segmen = data.getKode_segmen(); //set value segmen
//            et_produk.getText().clear();
//            et_program.getText().clear();
//            et_jenisusaha.getText().clear();
//            if(data.getNama_segmen().equalsIgnoreCase("konsumer")){
//                tf_program.setVisibility(View.VISIBLE);
//                tf_jenisusaha.setLabelText("Pekerjaan");
//                tf_pendapatan.setLabelText("Pendapatan");
//            }
//            else if (data.getNama_segmen().equalsIgnoreCase("mikro")){
//                tf_program.setVisibility(View.GONE);
//                tf_jenisusaha.setLabelText("Bidang Usaha");
//                tf_pendapatan.setLabelText("Omset Per Bulan");
//            }
//        }
//        else if(title.equalsIgnoreCase("produk")){
//            et_produk.setText(data.getNama_produk());
//            val_produk = data.getKode_produk(); //set value produk
//            et_program.getText().clear();
//        }
//
//        else if(title.equalsIgnoreCase("program")){
//            et_program.setText(data.getNama_gimmick());
//            val_program = String.valueOf(data.getKode_gimmick()); //set value program
//        }
    }


    private void onChangeText() {
//        et_plafond.addTextChangedListener(new NumberTextWatcherForThousand(et_plafond));
//        et_pendapatan.addTextChangedListener(new NumberTextWatcherForThousand(et_pendapatan));
//        et_tindaklanjut.addTextChangedListener(this);
    }


    public void loadData() {
        loading.setVisibility(View.VISIBLE);


        ReqIdAplikasi req = new ReqIdAplikasi(idAplikasi);
        //pantekan
//        inquiryRPC req=new inquiryRPC(344703);
        Call<ParseResponseDataInstansi> call = apiClientAdapter.getApiInterface().inquiryDataFinansial(req);
        call.enqueue(new Callback<ParseResponseDataInstansi>() {
            @Override
            public void onResponse(Call<ParseResponseDataInstansi> call, Response<ParseResponseDataInstansi> response) {
                try {
                    if (response.isSuccessful()) {
//                            Toast.makeText(DataFinansialKmgActivity.this, "Id aplikasi masih hardcode", Toast.LENGTH_SHORT).show();
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            loading.setVisibility(View.GONE);
                            String dataAngsuranString = "";
                            String dataFinansialString = response.body().getData().toString();
                            String dataInstansiString = response.body().getDataInstansi().toString();
                            String dataQanunString = response.body().getData_qanun();
//                            Log.d("pirmen",response.body().getData_qanun().toString());
                            if (response.body().getData().get("data_angsuran") != null) {
                                dataAngsuranString = response.body().getData().get("data_angsuran").toString();
                            } else {
                                dataAngsuranString = response.body().getDataAngsuran().toString();
                            }

                            Gson gson = new Gson();

                            dataFinansial = gson.fromJson(dataFinansialString, DataFinansial.class);
                            dataInstansi = gson.fromJson(dataInstansiString, DataFinansial.class);
                            dataAngsuran = gson.fromJson(dataAngsuranString, DataFinansial.class);


                            dataFinansial.setANGSURAN(dataAngsuran.getANGSURAN());
                            dataFinansial.setQanun(dataQanunString);
//                                Log.d("masbayangsuran",dataAngsuran.getANGSURAN().toString());
                            dataFinansial.setKATEGORI(dataInstansi.getKATEGORI());
                            dataFinansial.setMINPLAFONBERAGUNAN(dataInstansi.getMINPLAFONBERAGUNAN());
                            dataFinansial.setMAXRPC(dataInstansi.getMAXRPC());
                            dataFinansial.setFIDINSTANSI(dataInstansi.getFIDINSTANSI());
                            dataFinansial.setSISAPLAFONDINSTANSI(dataInstansi.getSISAPLAFONDINSTANSI());

                            dataFinansial.setMARGINRATE1(dataInstansi.getMARGINRATE1());
                            dataFinansial.setMARGINRATE2(dataInstansi.getMARGINRATE2());
                            dataFinansial.setMARGINRATE3(dataInstansi.getMARGINRATE3());
                            dataFinansial.setMARGINRATE4(dataInstansi.getMARGINRATE4());

                            dataFinansial.setMAXJW1(dataInstansi.getMAXJW1());
                            dataFinansial.setMAXJW2(dataInstansi.getMAXJW2());
                            dataFinansial.setMAXJW3(dataInstansi.getMAXJW3());
                            dataFinansial.setMAXJW4(dataInstansi.getMAXJW4());

                            //pantekan id tujua
//                            dataFinansial.setIDTUJUAN("91");


                            //CEK KALAU DIA CABANG QANUN, MAKA MARGIN BISA DIEDIT
                            if(dataFinansial.getQanun()!=null&&dataFinansial.getQanun().equalsIgnoreCase("true")){
                                et_margin_pertahun.setFocusable(true);
                                tf_margin_pertahun.setPanelBackgroundColor(getResources().getColor(R.color.colorBgEdittext));
                                et_margin_pertahun_konsumtif.setFocusable(true);
                                tf_margin_pertahun_konsumtif.setPanelBackgroundColor(getResources().getColor(R.color.colorBgEdittext));
                            }
                            else{
                                et_margin_pertahun.setFocusable(false);
                                et_margin_pertahun_konsumtif.setFocusable(false);
                            }

                            //parameter yang berubah kalau pembiayaan takeover murni
                            if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("91")){
                                tf_jumlah_plafon_pembiayaan_diusulkan.setLabelText("Plafond Takeover");
//                                tf_jangka_waktu.setLabelText("Jangka Waktu Konsumtif *");
                                tv_label_takeover.setVisibility(View.VISIBLE);
                                tf_jangka_waktu_qardh.setVisibility(View.VISIBLE);
                                tf_harga_beli.setVisibility(View.GONE);
                                tf_uang_muka.setVisibility(View.GONE);


                            }


                            //parameter yang berubah kalau pembiayaan takeover + konsumtif
                            else if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")){
                                tf_jumlah_plafon_pembiayaan_diusulkan.setLabelText("Plafond Takeover *");
                                tf_jangka_waktu.setLabelText("Jangka Waktu Top Up *");
                                tf_margin_pertahun.setLabelText("Margin Takeover *");
                                tf_angsuran_.setLabelText("Angsuran Takeover *");
                                tf_rpc.setLabelText("RPC Total *");
                                tv_label_takeover.setVisibility(View.VISIBLE);
                                tv_label_topup.setVisibility(View.VISIBLE);

                                tf_jumlah_plafon_pembiayaan_konsumtif.setVisibility(View.VISIBLE);
                                tf_jangka_waktu_qardh.setVisibility(View.VISIBLE);
                                tf_jangka_waktu_takeover.setVisibility(View.VISIBLE);
                                tf_angsuran_konsumtif.setVisibility(View.VISIBLE);
//                                tf_rpc_konsumtif.setVisibility(View.VISIBLE);
                                tf_margin_pertahun_konsumtif.setVisibility(View.VISIBLE);

                            }



                            //ngambil max jangka waktu
                            int data_jw1 = Integer.parseInt(dataInstansi.getMAXJW1());
                            int data_jw2 = Integer.parseInt(dataInstansi.getMAXJW2());
                            int data_jw3 = Integer.parseInt(dataInstansi.getMAXJW3());
                            int data_jw4 = Integer.parseInt(dataInstansi.getMAXJW4());

                            Double data_margin1 = Double.parseDouble(dataInstansi.getMARGINRATE1());
                            Double data_margin2 = Double.parseDouble(dataInstansi.getMARGINRATE2());
                            Double data_margin3 = Double.parseDouble(dataInstansi.getMARGINRATE3());
                            Double data_margin4 = Double.parseDouble(dataInstansi.getMARGINRATE4());

                            List<Integer> listJw = new ArrayList<>();
                            listJw.add(data_jw1);
                            listJw.add(data_jw2);
                            listJw.add(data_jw3);
                            listJw.add(data_jw4);

                            List<Double> listMargin = new ArrayList<>();
                            listMargin.add(data_margin1);
                            listMargin.add(data_margin2);
                            listMargin.add(data_margin3);
                            listMargin.add(data_margin4);

                            List<String> listMarginString = new ArrayList<>();
                            listMarginString.add(dataInstansi.getMARGINRATE1());
                            listMarginString.add(dataInstansi.getMARGINRATE2());
                            listMarginString.add(dataInstansi.getMARGINRATE3());
                            listMarginString.add(dataInstansi.getMARGINRATE4());

                            maxJw = Collections.max(listJw);
                            maxMargin = Collections.max(listMargin);
//                                maxMargin=0;
//
//                                for (int i = 0; i <listMargin.size() ; i++) {
//                                    if(maxMargin<=Integer.parseInt(listMarginString.get(i).replace(".","")))
//                                    {
//                                        for (int j = 0; j <listMargin.size() ; j++) {
//                                            if
//                                            (Integer.parseInt(listMarginString.get(i).replace(".",""))>=Integer.parseInt(listMarginString.get(j).replace(".",""))){
//                                                maxMargin=i;
//
//                                            }
//                                            else{
//                                                maxMargin=j;
//                                            }
//                                        }
//                                    }
//                                    else{
//                                        continue;
//                                    }
//
//                                }


//                                 textChangedForDataFinansial();

                            //kalau omzet perbulan 0, berarti belum pernah ngisi data finansial sebelumnya, maka edittext tidak diisi dulu, hanya beberapa field yang bisa diisi
                            if (dataFinansial.getOMZETPERBULAN().equalsIgnoreCase("0")) {
                                //pakai textchanged khusus jika pembiayaan takeover, karena total plafonnya beda
                                if (dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")) {
//                                    Log.d("marsupilamitujuan",dataFinansial.getIDTUJUAN());
//                                    Log.d("marsupilami","masuk takeover");
                                    textChangedForDataFinansialTakeOver();
                                }
                                else{
//                                    Log.d("marsupilamitujuan",dataFinansial.getIDTUJUAN());
//                                    Log.d("marsupilami","masuk non-takeover");
                                    textChangedForDataFinansial();
                                }
                                //berarti kosong kabeh ,jadi set data yang dari awal udah keisi aje

                                //ini ambil dari keyvalue pantekan, dan dia tidak dinamis, jadi di comment aje
//        et_tujuan_penggunaan.setText(KeyValue.getKeyTujuanPenggunaanKmg(dataFinansial.getIDTUJUAN()));

                                //ini ngambil dari halaman hotprospek detail, jadi udah sesuai dari respon DB
                                et_tujuan_penggunaan.setText(tujuanPenggunaanDepan);
                                et_nilai_permohonan_pembiayaan.setText(dataFinansial.getPLAFONDINDUK().toString());
                                et_harga_beli.setText(dataFinansial.getHARGAPROPERTY().toString());
                                et_angsuran_pinjaman_eksisting_1.setText(dataFinansial.getANGSURAN().toString());
                                et_jangka_waktu_qardh.setText("14");
//                                    et_jumlah_plafon_pembiayaan_diusulkan.setText(dataFinansial.getPLAFONDINDUK().toString());

                            } else {
                                setData();
                            }


//                                Log.d("firmansah",dataFinansialString);
//                                Log.d("firmansah isdebest",dataFinansial.getOMZETPERBULAN());


                        } else {
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(DataFinansialKmgActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataFinansialKmgActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ParseResponseDataInstansi> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(DataFinansialKmgActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }

    public void simpanData(final SweetAlertDialog dialog) {


        dialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        dialog.setCanceledOnTouchOutside(false);
        SimpanDataFinansial req = new SimpanDataFinansial();

        req.setIdAplikasi(Integer.toString(idAplikasi));
        req.setIdCif(cif);
        req.setIdTujuan(dataFinansial.getIDTUJUAN());

        req.setIndexNpw("0");
        req.setIdPrescoring(Integer.parseInt(dataFinansial.getIDPRESCORING()));
        req.setPenghasilanLain(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())));
        req.setAngsuran(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran.getText().toString()).replace(".", "")));
        req.setSukuMargin(Double.parseDouble(et_margin_pertahun.getText().toString()));
        req.setjANGKAWAKTU(Integer.parseInt(et_jangka_waktu.getText().toString()));
        req.setmAKSIMUMPLAFOND(Long.parseLong(dataFinansial.getMAKSIMUMPLAFOND().toString()));
        req.setiNPUTPERMOHONAN(Long.parseLong(dataFinansial.getPERMOHONANKREDIT().toString()));
        req.setPlafondUsul(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())));
        req.setoMZETPERBULAN((NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())));
        req.setOmzetSetelahPotongan("0");//gak ada di field
        req.setOmzetBersih((NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())));
        req.setHargaBeli(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_harga_beli.getText().toString())));
        req.setBesarUangMuka(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_uang_muka.getText().toString()));
        req.setIdScoring(Integer.parseInt(dataFinansial.getIDSCORING()));
        req.setNetIncome(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString()));
        req.setReqLoanAmount("0");//apa ini maksudnya?
        req.setReqPeriode("0");//apa ini
        req.setSukuMarginBulanan(0d);//ga ada di field
        req.setAngsuranScoring("0");//gak ada di field
        req.setDeviasiDir("0");//gak ada di field
        req.setLtv("0");//apa ini
        req.setNamaAOS(appPreferences.getNama());
        req.setUidAOS(Integer.toString(appPreferences.getUid()));
        req.setKewajibanLain(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString())));

        //cek lagi nih, kalo takeover topup, rpcnya tetep 1 aja yang dikirim?
        req.setRPCfinal(et_rpc.getText().toString());

        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")){
            req.setPlafondKonsumtif(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString())));
            req.setSukuMarginKonsumtif(Double.parseDouble(et_margin_pertahun_konsumtif.getText().toString()));
            req.setAngsuranKonsumtif(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_konsumtif.getText().toString()).replace(".", "")));
        }



        //khusus untuk takeover maka ngirim jangka waktu takeover
        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")){
            req.setJANGKA_WAKTU_TO(Integer.parseInt(et_jangka_waktu_takeover.getText().toString()));
        }

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().simpanDataFinansial(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {
//                            Toast.makeText(DataFinansialKmgActivity.this, "Id aplikasi masih hardcode", Toast.LENGTH_SHORT).show();
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            loading.setVisibility(View.GONE);

//                            String dataFinansialString=response.body().getData().toString();
//
//                            Gson gson = new Gson();


                            dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            dialog.setTitle("Sukses");
                            dialog.setContentText("Berhasil Menyimpan Data Finansial");
                            dialog.setConfirmText("Ok");
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    dialog.dismissWithAnimation();
                                    finish();
//            startActivity(new Intent(this, PipelineActivity.class));
//                                    startActivity(new Intent(DataFinansialKmgActivity.this, HotprospekDetailActivity.class));
                                }
                            });
                            dialog.showCancelButton(false);


                        } else {
                            loading.setVisibility(View.GONE);
                            dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            dialog.setTitle("Gagal");
                            dialog.setContentText(response.body().getMessage());
                            dialog.setConfirmText("Coba Lagi");
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    simpanData(dialog);
//            startActivity(new Intent(this, PipelineActivity.class));
//                                    startActivity(new Intent(DataFinansialKmgActivity.this, HotprospekDetailActivity.class));
                                }
                            });
                            dialog.setCancelText("Batal");
                            dialog.showCancelButton(true);
                            AppUtil.notiferror(DataFinansialKmgActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitle("Gagal");
                        dialog.setContentText(error.getMessage());
                        dialog.setConfirmText("Coba Lagi");
                        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                simpanData(dialog);
//            startActivity(new Intent(this, PipelineActivity.class));
//                                    startActivity(new Intent(DataFinansialKmgActivity.this, HotprospekDetailActivity.class));
                            }
                        });
                        dialog.setCancelText("Batal");
                        dialog.showCancelButton(true);

                        AppUtil.notiferror(DataFinansialKmgActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                dialog.setTitle("Gagal");
                dialog.setContentText("Terjadi kesalahan ketika menyimpan data");
                dialog.setConfirmText("Coba Lagi");
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        simpanData(dialog);
//            startActivity(new Intent(this, PipelineActivity.class));
//                                    startActivity(new Intent(DataFinansialKmgActivity.this, HotprospekDetailActivity.class));
                    }
                });
                dialog.setCancelText("Batal");
                dialog.showCancelButton(true);
                AppUtil.notiferror(DataFinansialKmgActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }


    public void validasiData() {
//        loading.setVisibility(View.VISIBLE);
        final SweetAlertDialog dialog = new SweetAlertDialog(DataFinansialKmgActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("Memvalidasi");
        dialog.showCancelButton(true);
        dialog.setCancelText("Batal");
        dialog.show();

        //pantekan
//        inquiryRPC req=new inquiryRPC(101419);


        ValidasiDataFinansialKmg req = new ValidasiDataFinansialKmg();
        req.setIdAplikasi(Integer.toString(idAplikasi));
        req.setPlafondUsul(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString()));
        req.setRPCfinal(et_rpc.getText().toString());
        req.setMAKSIMUM_PLAFOND(Long.toString(dataFinansial.getMAKSIMUMPLAFOND()));
        req.setCookie_jw_max(Integer.toString(maxJw));
        req.setJANGKA_WAKTU(et_jangka_waktu.getText().toString());
        req.setMAKS_TENOR_MPP(dataFinansial.getMAKSTENORMPP());
        req.setOMZET_PERBULAN(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString()));
        req.setINPUT_PERMOHONAN(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_nilai_permohonan_pembiayaan.getText().toString()));
        req.setMARGIN(et_margin_pertahun.getText().toString());
        req.setOmzetBersih((NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())));


        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")){
            req.setPlafondKonsumtif(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString()));
            req.setMARGIN_KONSUMTIF(et_margin_pertahun_konsumtif.getText().toString());
//            req.setRPCfinalKonsumtif(et_rpc_konsummtif.getText().toString());
        }



        Call<ParseResponse> call = apiClientAdapter.getApiInterface().validasiDataFinansial(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            loading.setVisibility(View.GONE);
                            dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            dialog.setTitle("Hasil Validasi");
                            dialog.setContentText(response.body().getMessage() + "\n");
                            dialog.setCancelText("Kembali");
                            dialog.setConfirmText("Simpan");
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    //simpan hasil
                                    simpanData(dialog);
                                }
                            });
                            dialog.show();
//                            Toast.makeText(DataFinansialKmgActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                        } else {
                            loading.setVisibility(View.GONE);
                            dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            dialog.setTitle("Hasil Validasi");
                            dialog.setContentText(response.body().getMessage() + "\n");
                            dialog.showCancelButton(false);
                            dialog.setConfirmText("OK");
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    dialog.dismissWithAnimation();

                                }
                            });
                            dialog.show();
//                            AppUtil.notiferror(DataFinansialKmgActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitle("Terjadi Kesalahan");
                        dialog.setContentText(error.getMessage() + "\n"+"Pastikan koneksi internet normal, silahkan login ulang jika masih belum berhasil");
                        dialog.showCancelButton(false);
                        dialog.setConfirmText("OK");
                        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                dialog.dismissWithAnimation();

                            }
                        });
                        dialog.show();
//                        AppUtil.notiferror(DataFinansialKmgActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(DataFinansialKmgActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                dialog.setTitle("Terjadi Kesalahan");
                dialog.setContentText("Pastikan koneksi internet normal, silahkan login ulang jika masih belum berhasil"+"\n");
                dialog.showCancelButton(false);
                dialog.setConfirmText("OK");
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.dismissWithAnimation();

                    }
                });
                dialog.show();
            }
        });

    }

    private boolean validateForm() {

        //reset nilai jumlah validasi
        jumlahValidasi = 0;

        //else dan if buat validasi seluruh field dengan menggunakan 1 method

        subValidate(et_nilai_permohonan_pembiayaan, tf_nilai_permohonan_pembiayaan);
        subValidate(et_angsuran, tf_angsuran_);
        subValidate(et_gaji_pokok, tf_gaji_pokok);
        subValidate(et_tunjangan_tetap_lainnya, tf_tunjangan_tetap_lainnya);
        subValidate(et_penghasilan_bersih, tf_penghasilan_bersih);
        subValidate(et_margin_pertahun, tf_margin_pertahun);
        subValidate(et_harga_beli, tf_harga_beli);
        subValidate(et_jangka_waktu, tf_jangka_waktu);
        subValidate(et_jumlah_plafon_pembiayaan_diusulkan, tf_jumlah_plafon_pembiayaan_diusulkan);
        subValidate(et_rpc, tf_rpc);
        subValidate(et_jangka_waktu, tf_jangka_waktu);

        //khusus untuk takeover murni maka validasi jangka waktu takeover
//        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("91")){
//            subValidate(et_jangka_waktu_takeover,tf_jangka_waktu_takeover);
//        }

        //khusus untuk takeover konsumtif maka validasi jangka waktu takeover dan plafon konsumtif
        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")){
            subValidate(et_jangka_waktu_takeover,tf_jangka_waktu_takeover);
            subValidate(et_jumlah_plafon_pembiayaan_konsumtif, tf_jumlah_plafon_pembiayaan_konsumtif);
            subValidate(et_margin_pertahun_konsumtif, tf_margin_pertahun_konsumtif);
//            subValidate(et_rpc_konsummtif, tf_jumlah_plafon_pembiayaan_konsumtif);
            subValidate(et_angsuran_konsumtif, tf_angsuran_konsumtif);
        }

        //validasi uang muka harus 30% dari harga beli, jika tujuan penggunaan, kendaraan roda 2 , termasuk yang takeover
        BigDecimal nilaiUangMuka = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_uang_muka.getText().toString()));

        if (dataFinansial.getIDTUJUAN().equalsIgnoreCase("89")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")) {
//            BigDecimal nilaiUangMuka=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_uang_muka.getText().toString()));
            BigDecimal nilaiHargaBeli = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_harga_beli.getText().toString()));

            BigDecimal tigaPuluhPersenUangMuka = nilaiHargaBeli.multiply(new BigDecimal(30)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);

            //jika uang muka lebih kecil dari 30 persen harga beli
            if (nilaiUangMuka.compareTo(tigaPuluhPersenUangMuka) < 0) {
                tf_uang_muka.setError("Minimum Uang Muka " + AppUtil.parseRupiah(String.valueOf(tigaPuluhPersenUangMuka)), false);

            } else {
                    jumlahValidasi = jumlahValidasi + 1;
            }

        }

        //cek jangan sampe plafon lebih besar dari harga beli, alias uang mukanya minus (khusus non takeover murni)
        if(!dataFinansial.getIDTUJUAN().equalsIgnoreCase("91")){
            if (nilaiUangMuka.compareTo(new BigDecimal(0)) < 0) {
                tf_harga_beli.setError("Harga beli tidak boleh lebih kecil dari plafon usulan/konsumtif", false);
            } else {
                jumlahValidasi = jumlahValidasi + 1;
            }
        }



        //sesuaikan dengan jumlah subvalidate dan pengecekan lainnya
        //konsumtif
        if (dataFinansial.getIDTUJUAN().equalsIgnoreCase("89")) {
            if (jumlahValidasi == 13) {
                return true;
            } else {
                Log.d("statusvalidasi", Integer.toString(jumlahValidasi));
                return false;
            }
        }

        else if (dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")) {
            if (jumlahValidasi == 17) {
                return true;
            } else {
                Log.d("statusvalidasi", Integer.toString(jumlahValidasi));
                return false;
            }
        }

        //takeover
        else if (dataFinansial.getIDTUJUAN().equalsIgnoreCase("91")) {
            if (jumlahValidasi == 11) {
                return true;
            } else {
                Log.d("statusvalidasi", Integer.toString(jumlahValidasi));
                return false;
            }
        }
        else if (dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")) {
            if (jumlahValidasi == 16) {
                return true;
            } else {
                Log.d("statusvalidasi", Integer.toString(jumlahValidasi));
                return false;
            }
        }
        else if (dataFinansial.getIDTUJUAN().equalsIgnoreCase("90")) {
            if (jumlahValidasi == 12) {
                return true;
            } else {
                Log.d("statusvalidasi", "id tujuan :"+dataFinansial.getIDTUJUAN()+" jumlah validasi :"+Integer.toString(jumlahValidasi));
                return false;
            }
        }
        else {
            if (jumlahValidasi == 12) {
                return true;
            } else {
                Log.d("statusvalidasi", Integer.toString(jumlahValidasi));
                return false;
            }

        }


    }

    private void subValidate(EditText editext, TextFieldBoxes textFieldBoxes) {
        if (editext.getText().toString().trim().equalsIgnoreCase("pilih") || editext.getText().toString().trim().equalsIgnoreCase("pilih jenis tiering") || editext.getText().toString().trim().isEmpty()) {
            //agar user tau field yang error dari textfield
            textFieldBoxes.setError("Harap Isi " + textFieldBoxes.getLabelText().substring(0, textFieldBoxes.getLabelText().length() - 2), true);

            //agar user tau nama field yang error dari snackbar
            AppUtil.notiferror(DataFinansialKmgActivity.this, findViewById(android.R.id.content), "Harap pilih " + textFieldBoxes.getLabelText().substring(0, textFieldBoxes.getLabelText().length() - 2));


        } else {
            jumlahValidasi = jumlahValidasi + 1;
        }
    }


    @Override
    public void onSelectMenuCamera(String idMenu) {
        switch (idMenu) {
            case "Take Photo":
//                openCamera();
                break;
            case "Pick Photo":
//                openGalery();
                break;
        }
    }

    @Override
    public void success(boolean val) {
//        if(val){
//            finish();
//            startActivity(new Intent(this, PipelineActivity.class));
//        }

    }

    @Override
    public void confirm(boolean val) {

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        if (et_tindaklanjut.getText().toString().trim().isEmpty() || et_tindaklanjut.getText().toString().trim().equalsIgnoreCase("")){
//            rg_typetindaklanjut.clearCheck();
//            val_jenistindaklanjut = "";
//        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onAddressSelect(address data) {
//        et_provinsi.setText(data.getProvinsi());
//        et_kota.setText(data.getKota());
//        et_kecamatan.setText(data.getKecamatan());
//        et_kelurahan.setText(data.getKelurahan());
//        et_kodepos.setText(data.getKodepos());
    }


    @Override
    public void onBackPressed() {

        if(approved.equalsIgnoreCase("no")){
            CustomDialog.DialogBackpress(DataFinansialKmgActivity.this);
        }
        else{
            super.onBackPressed();
        }
    }


    private void setDynamicIcon() {
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_tujuan_penggunaan, tf_tujuan_penggunaan);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_nilai_permohonan_pembiayaan, tf_nilai_permohonan_pembiayaan);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_gaji_pokok, tf_gaji_pokok);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_tunjangan_tetap_lainnya, tf_tunjangan_tetap_lainnya);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_kewajiban_lain, tf_kewajiban_lain);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_angsuran_pinjaman_eksisting_1, tf_angsuran_pinjaman_eksisting_1);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_penghasilan_bersih, tf_penghasilan_bersih);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_margin_pertahun, tf_margin_pertahun);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_jangka_waktu, tf_jangka_waktu);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_harga_beli, tf_harga_beli);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_jumlah_plafon_pembiayaan_diusulkan, tf_jumlah_plafon_pembiayaan_diusulkan);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_ftv_ratio, tf_ftv_ratio);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_angsuran, tf_angsuran_);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_rpc, tf_rpc);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_uang_muka, tf_uang_muka);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_jangka_waktu_qardh, tf_jangka_waktu_qardh);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_jangka_waktu_takeover, tf_jangka_waktu_takeover);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_jumlah_plafon_pembiayaan_konsumtif, tf_jumlah_plafon_pembiayaan_konsumtif);

        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_angsuran_konsumtif, tf_angsuran_konsumtif);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_rpc_konsummtif, tf_rpc_konsumtif);
        AppUtil.dynamicIconLogoChange(DataFinansialKmgActivity.this, et_margin_pertahun_konsumtif,tf_margin_pertahun_konsumtif);
    }

    public long hitungAngsuranAppel(float _rate, long _plafond, int jk_waktu){
        return (long)((long)(((_rate / 12) / 100) * _plafond) / (1 - (1 / Math.pow((1.00 + ((_rate / 100) / 12.00)), jk_waktu))));
    }
}