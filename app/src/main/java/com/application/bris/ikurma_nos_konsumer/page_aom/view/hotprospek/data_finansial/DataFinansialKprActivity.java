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
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.SimpanDataFinansialKpr;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.ValidasiDataFinansialKmg;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.ReqIdAplikasi;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.model.hotprospek.DataFinansialKpr;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.AddressListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.CameraListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.address;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.keyvalue;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.RoundingMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class DataFinansialKprActivity extends AppCompatActivity implements
        View.OnClickListener,  AddressListener, KeyValueListener, CameraListener, ConfirmListener, TextWatcher {

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

    @BindView(R.id.tf_rumah_keberapa)
    TextFieldBoxes tf_rumah_keberapa;
    @BindView(R.id.et_rumah_keberapa)
    EditText et_rumah_keberapa;


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

    @BindView(R.id.tf_nominal_uang_muka)
    TextFieldBoxes tf_nominal_uang_muka;
    @BindView(R.id.et_nominal_uang_muka)
    EditText et_nominal_uang_muka;

    @BindView(R.id.tf_angsuran_konsumtif)
    TextFieldBoxes tf_angsuran_konsumtif;
    @BindView(R.id.et_angsuran_konsumtif)
    EditText et_angsuran_konsumtif;

    @BindView(R.id.tf_rpc_konsumtif)
    TextFieldBoxes tf_rpc_konsumtif;
    @BindView(R.id.et_rpc_konsummtif)
    EditText et_rpc_konsummtif;

    @BindView(R.id.tf_tujuan_qardh)
    TextFieldBoxes tf_tujuan_qardh;
    @BindView(R.id.et_tujuan_qardh)
    EditText et_tujuan_qardh;

    @BindView(R.id.tf_joint_income)
    TextFieldBoxes tf_joint_income;
    @BindView(R.id.et_joint_income)
    EditText et_joint_income;

    @BindView(R.id.ll_datafinansial)
    LinearLayout ll_datafinansial;

    @BindView(R.id.tv_label_topup)
    TextView tv_label_topup;

    @BindView(R.id.tv_label_takeover)
    TextView tv_label_takeover;


    BigDecimal rateAnuitas = new BigDecimal(0);


    //variabel buat menghitung berapa field yang udah lolos validasi
    private int jumlahValidasi = 0;
    int idAplikasi = 0;
    private int plafond=0;
    String cif, approved,idTujuanPembiayaan;


    //VALUE
    private static String val_jenis_tiering = "";

    Long nilaiAngsuranAppel=0l;
    Long nilaiAngsuranAppelKonsumtif=0l;
    String idProgram="";


    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    DataFinansialKpr dataFinansial;
    String tujuanPenggunaanDepan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konsumer_data_finansial_kpr_final);

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
        plafond = getIntent().getIntExtra("plafond",0);

        if(getIntent().hasExtra("idProgram")){
            idProgram=getIntent().getStringExtra("idProgram");
        }

        idTujuanPembiayaan=Integer.toString(getIntent().getIntExtra("idTujuanPembiayaan",0));
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


        loadData();
        otherViewChanges();


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
        et_nominal_uang_muka.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nominal_uang_muka));
        et_joint_income.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_joint_income));


        //disable edittexts
        et_angsuran_pinjaman_eksisting_1.setFocusable(false);
        et_penghasilan_bersih.setFocusable(false);
        et_rpc.setFocusable(false);
        et_angsuran.setFocusable(false);
        et_tujuan_penggunaan.setFocusable(false);
        et_nilai_permohonan_pembiayaan.setFocusable(false);
        et_uang_muka.setFocusable(false);
        et_angsuran_konsumtif.setFocusable(false);
        et_rpc_konsummtif.setFocusable(false);

        //kalo flpp marginnya di konci
        if(idProgram.equalsIgnoreCase("222")){
            et_margin_pertahun.setFocusable(false);
            tf_margin_pertahun.setPanelBackgroundColor(getResources().getColor(R.color.colorDisableEdit));
        }

//        et_jumlah_plafon_pembiayaan_konsumtif.setFocusable(false);




    }

    private void setData() {


      if(idProgram.equalsIgnoreCase("222")){
          textChangedForDataFinansialFlpp();
      }
      else{
          textChangedForDataFinansial();
      }

        
            

    

//ini ambil dari keyvalue pantekan, dan dia tidak dinamis, jadi di c0mment aje
//        et_tujuan_penggunaan.setText(KeyValue.getKeyTujuanPenggunaanKmg(dataFinansial.getIDTUJUAN()));

        //ini ngambil dari halaman hotprospek detail, jadi udah sesuai dari respon DB
        et_tujuan_penggunaan.setText(tujuanPenggunaanDepan);
        et_gaji_pokok.setText(dataFinansial.getoMZETPERBULAN());
        et_rumah_keberapa.setText(dataFinansial.getrUMAHKEBERAPA());
        et_nominal_uang_muka.setText(dataFinansial.getbESARUANGMUKA());

        if(dataFinansial.getJOINT_INCOME()!=null&&!dataFinansial.getJOINT_INCOME().equalsIgnoreCase("0")){
            et_joint_income.setText(dataFinansial.getJOINT_INCOME().substring(0,dataFinansial.getJOINT_INCOME().length()-2));
        }






//        et_nilai_permohonan_pembiayaan.setText(AppUtil.parseRupiahLongNoSymbol(dataFinansial.getPLAFONDINDUK())); //ambil dari pipeline sesuai pengajuan
//
//        String parsedTunjanganTetapLainnya=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getpENGHASILANTETAPLAIN());
//        String parsedAngsuranPinjamanEksisting=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getaNGSURAN());


        et_tunjangan_tetap_lainnya.setText(String.valueOf(dataFinansial.getpENGHASILANTETAPLAIN()));
        if (et_tunjangan_tetap_lainnya.getText().toString().equalsIgnoreCase("000")) {
            et_tunjangan_tetap_lainnya.setText("0");
        }

        //for some reason calling datafinansial.getaNGSURAN returns 0 eventhough it has already been set in load data with a value.
        //this is a workaround for now
//        Log.d("masbaydatafinansial",String.valueOf(dataFinansial.getaNGSURAN()));
        et_angsuran_pinjaman_eksisting_1.setText(String.valueOf(dataFinansial.getaNGSURAN()));
        if (et_angsuran_pinjaman_eksisting_1.getText().toString().equalsIgnoreCase("000")) {
            et_angsuran_pinjaman_eksisting_1.setText("0");
        }

        //khusus takeover KPR

        if(dataFinansial.getID_TUJUAN().equalsIgnoreCase("37")){
            tf_jangka_waktu_qardh.setVisibility(View.VISIBLE);
            et_jangka_waktu_qardh.setText(dataFinansial.getJW_QARDH());

            tf_tujuan_qardh.setVisibility(View.VISIBLE);
            et_tujuan_qardh.setText(dataFinansial.getTUJUAN_QARDH());

        }
        else{
            tf_jangka_waktu_qardh.setVisibility(View.GONE);
            tf_tujuan_qardh.setVisibility(View.GONE);
        }

        //khusus flpp
        if(idProgram.equalsIgnoreCase("222")){
            et_uang_muka.setVisibility(View.VISIBLE);
        }
        else{
            et_uang_muka.setVisibility(View.GONE);
        }

        //referensi buat bigdecimal
//        valBd_grossProfitMargin = (valBd_pendapatanUsaha.subtract(valBd_hargapokokPenjualan)).divide(valBd_pendapatanUsaha, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(1, BigDecimal.ROUND_HALF_UP);


        //set margin dari service
        //margin biasa
        if(dataFinansial.getSUKU_MARGIN()!=null&& !dataFinansial.getSUKU_MARGIN().equalsIgnoreCase("0")){
            et_margin_pertahun.setText(dataFinansial.getSUKU_MARGIN());

        }


        final BigDecimal penghasilanBersih = (new BigDecimal(dataFinansial.getoMZETPERBULAN()).add(new BigDecimal(dataFinansial.getpENGHASILANTETAPLAIN()))).subtract(new BigDecimal(dataFinansial.getaNGSURAN()));
        et_penghasilan_bersih.setText(penghasilanBersih.toString()); //ini kalkulasi gaji pokok + penghasilan lain - angsuran eksisting


        et_jangka_waktu.setText(dataFinansial.getjANGKAWAKTU());
      

//        et_jumlah_plafon_pembiayaan_diusulkan.setText(dataFinansial.getpLAFONDYANGDIUSULKAN());
//        et_harga_beli.setText(dataFinansial.getPERMOHONANKREDIT());
//        et_rpc.setText(); //ini dari data instansi

//        et_jumlah_plafon_pembiayaan_diusulkan.setText(AppUtil.parseRupiah(dataFinansial.getpLAFONDYANGDIUSULKAN()));
//        et_harga_beli.setText(AppUtil.parseRupiah(dataFinansial.getPERMOHONANKREDIT()));

//        String parsedJumlahPlafon=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getpLAFONDYANGDIUSULKAN());
//        String parsedHargaBeli=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getPERMOHONANKREDIT());
//        String parsedPlafondInduk=AppUtil.parseRupiahLongNoSymbol(dataFinansial.getPLAFONDINDUK());
       
          et_jumlah_plafon_pembiayaan_diusulkan.setText(String.valueOf(dataFinansial.getpLAFONDYANGDIUSULKAN()));
            if (et_jumlah_plafon_pembiayaan_diusulkan.getText().toString().equalsIgnoreCase("000")) {
                et_jumlah_plafon_pembiayaan_diusulkan.setText("0");
            }
            

        et_harga_beli.setText(String.valueOf(dataFinansial.gethARGAPROPERTY()));
        if (et_harga_beli.getText().toString().equalsIgnoreCase("000")) {
            et_harga_beli.setText("0");
        }



        et_nilai_permohonan_pembiayaan.setText(String.valueOf(dataFinansial.getpERMOHONANKREDIT()));
        et_kewajiban_lain.setText(String.valueOf(dataFinansial.getbIAYALAINLAIN()));

        //


        //set margin rate - DI COMMENT KARENA SEKARANG DAPET VARIABEL SUKU MARGIN DARI SERVICE
//        if (Integer.parseInt(dataFinansial.getjANGKAWAKTU()) > maxJw) {
//            Toast.makeText(DataFinansialKprActivity.this, "Jangka waktu tidak boleh melebihi " + maxJw + " bulan", Toast.LENGTH_SHORT).show();
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
//        if (Integer.parseInt(dataFinansial.getjANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW1()) && !dataFinansial.getMAXJW1().equalsIgnoreCase("0")) {
//            et_margin_pertahun.setText(dataFinansial.getMARGINRATE1());
//        } else if (Integer.parseInt(dataFinansial.getjANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW2()) && !dataFinansial.getMAXJW2().equalsIgnoreCase("0")) {
//            et_margin_pertahun.setText(dataFinansial.getMARGINRATE2());
//        } else if (Integer.parseInt(dataFinansial.getjANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW3()) && !dataFinansial.getMAXJW3().equalsIgnoreCase("0")) {
//            et_margin_pertahun.setText(dataFinansial.getMARGINRATE3());
//        } else if (Integer.parseInt(dataFinansial.getjANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW4()) && !dataFinansial.getMAXJW4().equalsIgnoreCase("0")) {
//            et_margin_pertahun.setText(dataFinansial.getMARGINRATE4());
//        }
//
//        //khusus takeover topup
//        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("110")||dataFinansial.getIDTUJUAN().equalsIgnoreCase("111")){
//            if (Integer.parseInt(dataFinansial.getjANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW1()) && !dataFinansial.getMAXJW1().equalsIgnoreCase("0")) {
//                et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE1());
//            } else if (Integer.parseInt(dataFinansial.getjANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW2()) && !dataFinansial.getMAXJW2().equalsIgnoreCase("0")) {
//                et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE2());
//            } else if (Integer.parseInt(dataFinansial.getjANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW3()) && !dataFinansial.getMAXJW3().equalsIgnoreCase("0")) {
//                et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE3());
//            } else if (Integer.parseInt(dataFinansial.getjANGKAWAKTU()) <= Integer.parseInt(dataFinansial.getMAXJW4()) && !dataFinansial.getMAXJW4().equalsIgnoreCase("0")) {
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

            nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())),Integer.parseInt(et_jangka_waktu
                    .getText().toString()));

            et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));

            //ini ngitung RPC dengan nilai dari appel
            BigDecimal rpcValue=new BigDecimal(nilaiAngsuranAppel).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())),2,RoundingMode.HALF_UP);


            et_rpc.setText(String.valueOf(rpcValue));
        





//        Toast.makeText(this, String.valueOf(Angsuran), Toast.LENGTH_LONG).show();
//        Log.d("anuitas",String.valueOf(rateAnuitas));

        //set rpc
//        BigDecimal rpcValue=Angsuran.multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())),2,RoundingMode.HALF_UP);








    }

    private void textChangedForDataFinansial() {



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

                    if(Float.parseFloat(et_margin_pertahun.getText().toString())!=0f&&!dataFinansial.getjANGKAWAKTU().equalsIgnoreCase("0")){
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
                    AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(R.id.content), "Harap isi jangka waktu sebelum mengubah plafon diusulkan");
                    Log.d("ekiduper"," masuk kedalam non takeover");
                }

                if (hasFocus && et_margin_pertahun.getText().toString().isEmpty()) {
                    tf_margin_pertahun.setError("Harap isi margin terlebih dahulu", true);
                    AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(R.id.content), "Harap isi margin sebelum mengubah plafon diusulkan");
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

                if (s != null && s.length() > 0 && Integer.parseInt(s.toString()) != 0) {

                    //kalau dari qanun, tidak ambil margin dari margin rate, tapi sakarepe dewe AOnya, di validasi di service



                            Long totalTakeover=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString()));

                            if(!et_margin_pertahun.getText().toString().isEmpty()){
                                nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),totalTakeover,Integer.parseInt(et_jangka_waktu.getText().toString()));

                                et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));
                            }




                } else {
                    if (s.length() > 0) {
                        Toast.makeText(DataFinansialKprActivity.this, "Jangka waktu tidak boleh 0 ", Toast.LENGTH_SHORT).show();
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

    private void textChangedForDataFinansialFlpp() {

        Log.d("datafinansial", "menggunakan data finansial flpp");

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
                    if (s.length() > 0 && et_kewajiban_lain.getText().length() > 0 && et_joint_income.getText().length() > 0) {
                        BigDecimal penghasilanBersih = (new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString()))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString()))).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_joint_income.getText().toString())));

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

        et_joint_income.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    if (s.length() > 0 && et_kewajiban_lain.getText().length() > 0 && et_tunjangan_tetap_lainnya.getText().length() > 0) {
                        BigDecimal penghasilanBersih = (new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString()))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString()))).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_joint_income.getText().toString())));

                        et_penghasilan_bersih.setText(penghasilanBersih.toString());
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


                    if (s.length() > 0 && et_tunjangan_tetap_lainnya.getText().length() > 0 &&et_joint_income.getText().length() > 0 ) {
                        BigDecimal penghasilanBersih = (new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString()))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString()))).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_joint_income.getText().toString())));

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

                    if(Float.parseFloat(et_margin_pertahun.getText().toString())!=0f&&!dataFinansial.getjANGKAWAKTU().equalsIgnoreCase("0")){
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
                    AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(R.id.content), "Harap isi jangka waktu sebelum mengubah plafon diusulkan");
                    Log.d("ekiduper"," masuk kedalam non takeover");
                }

                if (hasFocus && et_margin_pertahun.getText().toString().isEmpty()) {
                    tf_margin_pertahun.setError("Harap isi margin terlebih dahulu", true);
                    AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(R.id.content), "Harap isi margin sebelum mengubah plafon diusulkan");
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

                if (s != null && s.length() > 0 && Integer.parseInt(s.toString()) != 0) {

                    //kalau dari qanun, tidak ambil margin dari margin rate, tapi sakarepe dewe AOnya, di validasi di service



                    Long totalTakeover=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString()));

                    if(!et_margin_pertahun.getText().toString().isEmpty()){
                        nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),totalTakeover,Integer.parseInt(et_jangka_waktu.getText().toString()));

                        et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));
                    }




                } else {
                    if (s.length() > 0) {
                        Toast.makeText(DataFinansialKprActivity.this, "Jangka waktu tidak boleh 0 ", Toast.LENGTH_SHORT).show();
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

//    private void textChangedForDataFinansialTakeOver() {
//
//        et_gaji_pokok.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                try {
//                    if (s.length() > 0) {
//
//                        //perubahan penghasilan bersih
//                        BigDecimal penghasilanBersih = (new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString()))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString())));
//
//                        et_penghasilan_bersih.setText(penghasilanBersih.toString());
//
//
//                    } else {
//                        BigDecimal penghasilanBersihZero = new BigDecimal("0").subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString()))));
//
//                        et_penghasilan_bersih.setText(penghasilanBersihZero.toString());
//
//                    }
//                } catch (Exception e) {
//                    Log.d("exceptional 412", e.getMessage());
//                }
//
//
//            }
//
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        et_tunjangan_tetap_lainnya.addTextChangedListener(new TextWatcher() {
//
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                try {
//                    if (s.length() > 0 && et_kewajiban_lain.getText().length() > 0) {
//                        BigDecimal penghasilanBersih = (new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString()))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString())));
//
//                        et_penghasilan_bersih.setText(penghasilanBersih.toString());
//                    } else {
//                        BigDecimal penghasilanBersihZero = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString())).add(new BigDecimal("0")));
//
//                        et_penghasilan_bersih.setText(penghasilanBersihZero.toString());
//
//                    }
//
//                } catch (Exception e) {
//                    Log.d("exceptional 454", e.getMessage());
//                }
//
//
//            }
//
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        et_kewajiban_lain.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                try {
//
//
//                    if (s.length() > 0 && et_penghasilan_bersih.getText().length() > 0) {
//                        BigDecimal penghasilanBersih = (new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString()))).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString())));
//
//                        et_penghasilan_bersih.setText(penghasilanBersih.toString());
//                    } else {
//                        BigDecimal penghasilanBersihZero = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_pinjaman_eksisting_1.getText().toString())).add(new BigDecimal("0")));
//
//                        et_penghasilan_bersih.setText(penghasilanBersihZero.toString());
//
//                    }
//
//                } catch (Exception e) {
//                    Log.d("exceptional 496", e.getMessage());
//                }
//
//
//            }
//
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        et_kewajiban_lain.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus == false && et_kewajiban_lain.getText().toString().equalsIgnoreCase("")) {
//                    et_kewajiban_lain.setText("0");
//                }
//            }
//        });
//
//        et_tunjangan_tetap_lainnya.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus == false && et_tunjangan_tetap_lainnya.getText().toString().equalsIgnoreCase("")) {
//                    et_tunjangan_tetap_lainnya.setText("0");
//                }
//            }
//        });
//
//
//        et_margin_pertahun.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                try {
//
//
//                    //set rate anuitas
////                    Double ratePerTahun = Double.parseDouble(et_margin_pertahun.getText().toString()) / 100 / 12;
////                    BigDecimal pangkatPembagi = (new BigDecimal(1).add(new BigDecimal(ratePerTahun))).pow(Integer.parseInt(et_jangka_waktu
////                            .getText().toString()));
////
////                    BigDecimal pengaliPlafon = new BigDecimal(ratePerTahun).divide(new BigDecimal(1).subtract((new BigDecimal(1).divide(pangkatPembagi, 15, RoundingMode.HALF_UP))), 15, RoundingMode.HALF_UP).setScale(15, RoundingMode.HALF_UP);
////                    ;
////
//////        BigDecimal Angsuran=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(new BigDecimal(pengaliPlafon)).setScale(2,RoundingMode.HALF_UP);
////
////                    BigDecimal Angsuran = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(pengaliPlafon).setScale(2, RoundingMode.HALF_UP);
////
////                    et_angsuran.setText(String.valueOf(Angsuran));
//
//                    //ini ngitung angsuran dari rumus appel, jadi tipe datanya long bro, buset dah
//
//                    if(Float.parseFloat(et_margin_pertahun.getText().toString())!=0f&&!dataFinansial.getJANGKA_WAKTU_TO().equalsIgnoreCase("0")){
//                        nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())),Integer.parseInt(et_jangka_waktu_takeover.getText().toString()));
//
//                        et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));
//                    }
//
//
//                } catch (Exception e) {
//                    Log.d("exceptional 560", e.getMessage());
//                }
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        et_margin_pertahun_konsumtif.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                try {
//
//
//                    //set rate anuitas
////                    Double ratePerTahun = Double.parseDouble(et_margin_pertahun.getText().toString()) / 100 / 12;
////                    BigDecimal pangkatPembagi = (new BigDecimal(1).add(new BigDecimal(ratePerTahun))).pow(Integer.parseInt(et_jangka_waktu
////                            .getText().toString()));
////
////                    BigDecimal pengaliPlafon = new BigDecimal(ratePerTahun).divide(new BigDecimal(1).subtract((new BigDecimal(1).divide(pangkatPembagi, 15, RoundingMode.HALF_UP))), 15, RoundingMode.HALF_UP).setScale(15, RoundingMode.HALF_UP);
////                    ;
////
//////        BigDecimal Angsuran=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(new BigDecimal(pengaliPlafon)).setScale(2,RoundingMode.HALF_UP);
////
////                    BigDecimal Angsuran = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(pengaliPlafon).setScale(2, RoundingMode.HALF_UP);
////
////                    et_angsuran.setText(String.valueOf(Angsuran));
//
//                    //ini ngitung angsuran dari rumus appel, jadi tipe datanya long bro, buset dah
//
//                    if(Float.parseFloat(et_margin_pertahun_konsumtif.getText().toString())!=0f&&!dataFinansial.getjANGKAWAKTU().equalsIgnoreCase("0")){
//                        Long totalTakeoverKonsumtif=(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString())));
//
//                        nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun_konsumtif.getText().toString()),totalTakeoverKonsumtif,Integer.parseInt(et_jangka_waktu.getText().toString()));
//
//                        et_angsuran_konsumtif.setText(String.valueOf(nilaiAngsuranAppel));
//                    }
//
//
//                } catch (Exception e) {
//                    Log.d("exceptional 560", e.getMessage());
//                }
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        et_jumlah_plafon_pembiayaan_konsumtif
//                .addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//                        try {
//
//
//                            //INI NGITUNG ANGSURAN DENGAN BIG DECIMAL
//
//                            //perubahan angsuran
//                            //set rate anuitas
////                            Double ratePerTahun = Double.parseDouble(et_margin_pertahun.getText().toString()) / 100 / 12;
////                            BigDecimal pangkatPembagi = (new BigDecimal(1).add(new BigDecimal(ratePerTahun))).pow(Integer.parseInt(et_jangka_waktu
////                                    .getText().toString()));
////
////                            BigDecimal pengaliPlafon = new BigDecimal(ratePerTahun).divide(new BigDecimal(1).subtract((new BigDecimal(1).divide(pangkatPembagi, 15, RoundingMode.HALF_UP))), 15, RoundingMode.HALF_UP).setScale(15, RoundingMode.HALF_UP);
////                            ;
////
//////        BigDecimal Angsuran=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(new BigDecimal(pengaliPlafon)).setScale(2,RoundingMode.HALF_UP);
////
////                            BigDecimal Angsuran = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(pengaliPlafon).setScale(2, RoundingMode.HALF_UP);
////
////                            et_angsuran.setText(String.valueOf(Angsuran));
//
//
//                            //ini ngitung angsuran dari rumus appel, jadi tipe datanya long bro, buset dah
//
//                            Long totalTakeoverKonsumtif=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString()));
//
//                            nilaiAngsuranAppelKonsumtif=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun_konsumtif.getText().toString()),totalTakeoverKonsumtif,Integer.parseInt(et_jangka_waktu.getText().toString()));
//
//                            et_angsuran_konsumtif.setText(String.valueOf(nilaiAngsuranAppelKonsumtif));
//
//
//                            //jika harga beli dan plafon tidak kosong
//                            if (!et_harga_beli.getText().toString().isEmpty() && !et_jumlah_plafon_pembiayaan_konsumtif.getText().toString().isEmpty()) {
//                                //perubahan uang muka
//                                BigDecimal uangMukaBD = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_harga_beli.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString())));
//                                et_uang_muka.setText(uangMukaBD.toString());
//                            }
//
//
//                        } catch (Exception e) {
//                            Log.d("exceptional 599", e.getMessage());
//                        }
//
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable s) {
//
//                    }
//                });
//
//        et_jumlah_plafon_pembiayaan_diusulkan
//                .addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//                        try {
//
//
//                            //INI NGITUNG ANGSURAN DENGAN BIG DECIMAL
//
//                            //perubahan angsuran
//                            //set rate anuitas
////                            Double ratePerTahun = Double.parseDouble(et_margin_pertahun.getText().toString()) / 100 / 12;
////                            BigDecimal pangkatPembagi = (new BigDecimal(1).add(new BigDecimal(ratePerTahun))).pow(Integer.parseInt(et_jangka_waktu
////                                    .getText().toString()));
////
////                            BigDecimal pengaliPlafon = new BigDecimal(ratePerTahun).divide(new BigDecimal(1).subtract((new BigDecimal(1).divide(pangkatPembagi, 15, RoundingMode.HALF_UP))), 15, RoundingMode.HALF_UP).setScale(15, RoundingMode.HALF_UP);
////                            ;
////
//////        BigDecimal Angsuran=new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(new BigDecimal(pengaliPlafon)).setScale(2,RoundingMode.HALF_UP);
////
////                            BigDecimal Angsuran = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())).multiply(pengaliPlafon).setScale(2, RoundingMode.HALF_UP);
////
////                            et_angsuran.setText(String.valueOf(Angsuran));
//
//
//                            //ini ngitung angsuran dari rumus appel, jadi tipe datanya long bro, buset dah
//
//                            Long totalTakeoverKonsumtif=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString()));
//
//                            nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),totalTakeoverKonsumtif,Integer.parseInt(et_jangka_waktu_takeover.getText().toString()));
//
//                            et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));
//
//
//
//                        } catch (Exception e) {
//                            Log.d("exceptional 599", e.getMessage());
//                        }
//
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable s) {
//
//                    }
//                });
//
//        et_harga_beli
//                .addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//                        try {
//
//                            //jika harga beli dan plafon tidak kosong
//                            if (!et_harga_beli.getText().toString().isEmpty() && !et_jumlah_plafon_pembiayaan_konsumtif.getText().toString().isEmpty()) {
//
//
//                                //perubahan uang muka
//                                BigDecimal uangMukaBD = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_harga_beli.getText().toString())).subtract(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString())));
//                                et_uang_muka.setText(uangMukaBD.toString());
//                            }
//
//
//                        } catch (Exception e) {
//                            Log.d("exceptional 599", e.getMessage());
//                        }
//
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable s) {
//
//                    }
//                });
//
//        et_penghasilan_bersih.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                try {
//
//
//                    if (s != null && s.length() > 0 && !new BigDecimal(s.toString()).equals(0)) {
//
//                        if(!et_angsuran.getText().toString().isEmpty()&&!et_angsuran_konsumtif.getText().toString().isEmpty()){
//                            BigDecimal rpcValue = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran.getText().toString()));
//                            BigDecimal rpcKonsumtifValue = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_konsumtif.getText().toString()));
//
//                            BigDecimal totalRpc=(rpcValue.add(rpcKonsumtifValue)).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())), 2, RoundingMode.HALF_UP);
//
//                            et_rpc.setText(String.valueOf(totalRpc));
//                        }
//
////                        et_rpc_konsummtif.setText(String.valueOf(totalRpc));
//                    }
//                } catch (Exception e) {
//                    Log.d("exceptional 630", e.getMessage());
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        et_angsuran.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(!et_angsuran.getText().toString().isEmpty()&&!et_angsuran_konsumtif.getText().toString().isEmpty()){
//                    BigDecimal rpcValue = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran.getText().toString()));
//                    BigDecimal rpcKonsumtifValue = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_konsumtif.getText().toString()));
//
//                    BigDecimal totalRpc=(rpcValue.add(rpcKonsumtifValue)).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())), 2, RoundingMode.HALF_UP);
//
//                    et_rpc.setText(String.valueOf(totalRpc));
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        et_angsuran_konsumtif.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                if(!et_angsuran.getText().toString().isEmpty()&&!et_angsuran_konsumtif.getText().toString().isEmpty()){
//                    BigDecimal rpcValue = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran.getText().toString()));
//                    BigDecimal rpcKonsumtifValue = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran_konsumtif.getText().toString()));
//
//                    BigDecimal totalRpc=(rpcValue.add(rpcKonsumtifValue)).multiply(new BigDecimal(100)).divide(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())), 2, RoundingMode.HALF_UP);
//
//                    et_rpc.setText(String.valueOf(totalRpc));
//                }
//
////                et_rpc_konsummtif.setText(String.valueOf(rpcValue));
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        //memastikan bahwa plafon tidak bisa diedit sebelum jangka waktu diisi, karna dalam rumus angsuran, ada pembagian dengan jangka waktu, jadi kalo diisi 0 nanti dia error division by zero
//        et_jumlah_plafon_pembiayaan_diusulkan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus && et_jangka_waktu_takeover.getText().toString().isEmpty()) {
//                    tf_jangka_waktu_takeover.setError("Harap isi jangka waktu", true);
//                    AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(R.id.content), "Harap isi jangka waktu sebelum mengubah plafon diusulkan");
//                    Log.d("ekiduper"," masuk kedalam super takeover ");
//                }
//
//                if (hasFocus && (et_gaji_pokok.getText().toString().isEmpty()||et_gaji_pokok.getText().toString().equalsIgnoreCase("0"))) {
//                    tf_gaji_pokok.setError("Harap isi gaji pokok", true);
//                    AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(R.id.content), "Harap isi gaji pokok sebelum mengubah plafon diusulkan");
//                    Log.d("ekiduper"," masuk kedalam jangka wakto non takeover, tapi di textchanged takeover");
//                }
//
//                if (hasFocus && (et_margin_pertahun.getText().toString().isEmpty()||et_margin_pertahun.getText().toString().equalsIgnoreCase("0"))) {
//                    tf_margin_pertahun.setError("Harap isi margin", true);
//                    AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(R.id.content), "Harap isi margin sebelum mengubah plafon diusulkan");
//                    Log.d("ekiduper"," masuk kedalam jangka wakto non takeover, tapi di textchanged takeover");
//                }
//            }
//        });
//
//
//        et_jumlah_plafon_pembiayaan_konsumtif.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus && et_jangka_waktu.getText().toString().isEmpty()) {
//                    tf_jangka_waktu.setError("Harap isi jangka waktu", true);
//                    AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(R.id.content), "Harap isi jangka waktu sebelum mengubah plafon konsumtif");
//                }
//
//                if (hasFocus && (et_gaji_pokok.getText().toString().isEmpty()||et_gaji_pokok.getText().toString().equalsIgnoreCase("0"))) {
//                    tf_gaji_pokok.setError("Harap isi gaji pokok", true);
//                    AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(R.id.content), "Harap isi gaji pokok sebelum mengubah plafon konsumtif");
//                }
//
//                if (hasFocus && (et_margin_pertahun_konsumtif.getText().toString().isEmpty()||et_margin_pertahun_konsumtif.getText().toString().equalsIgnoreCase("0"))) {
//                    tf_margin_pertahun_konsumtif.setError("Harap isi margin", true);
//                    AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(R.id.content), "Harap isi margin sebelum mengubah plafon Top Up");
//                    Log.d("ekiduper"," masuk kedalam jangka wakto non takeover, tapi di textchanged takeover");
//                }
//            }
//        });
//
//        et_jangka_waktu.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                Log.d("max jw", Integer.toString(maxJw));
//                if (s != null && s.length() > 0 && Integer.parseInt(s.toString()) != 0) {
//                    if (Integer.parseInt(et_jangka_waktu.getText().toString()) > maxJw) {
//                        Toast.makeText(DataFinansialKprActivity.this, "Jangka waktu tidak boleh melebihi " + maxJw + " bulan", Toast.LENGTH_SHORT).show();
//                        et_jangka_waktu.setText(Integer.toString(maxJw));
//                        et_jangka_waktu.setSelection(et_jangka_waktu.getText().length());
//                    }
//
//                    //jika qanun, tidak ambil data margin rate
//                    if(dataFinansial.getQanun()!=null&&dataFinansial.getQanun().equalsIgnoreCase("true")){
//                        if(et_margin_pertahun_konsumtif.getText()!=null&&!et_margin_pertahun_konsumtif.getText().toString().equalsIgnoreCase("0")&&!et_margin_pertahun_konsumtif.getText().toString().isEmpty()){
//
//                            Long totalTakeover=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString()));
//
//                            nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun_konsumtif.getText().toString()),totalTakeover,Integer.parseInt(et_jangka_waktu.getText().toString()));
//
//                            et_angsuran_konsumtif.setText(String.valueOf(nilaiAngsuranAppel));
//                        }
//
//
//
//                    }
//                    else {
//                        if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW1()) && !dataFinansial.getMAXJW1().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE1().equalsIgnoreCase("0")) {
//                            et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE1());
//                        } else if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW2()) && !dataFinansial.getMAXJW2().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE2().equalsIgnoreCase("0")) {
//                            et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE2());
//                        } else if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW3()) && !dataFinansial.getMAXJW3().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE3().equalsIgnoreCase("0")) {
//                            et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE3());
//                        } else if (Integer.parseInt(et_jangka_waktu.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW4()) && !dataFinansial.getMAXJW4().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE4().equalsIgnoreCase("0")) {
//                            et_margin_pertahun_konsumtif.setText(dataFinansial.getMARGINRATE4());
//                        }
//
//                        Long totalTakeover=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_konsumtif.getText().toString()));
//
//                        nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun_konsumtif.getText().toString()),totalTakeover,Integer.parseInt(et_jangka_waktu.getText().toString()));
//
//                        et_angsuran_konsumtif.setText(String.valueOf(nilaiAngsuranAppel));
//
//                    }
//
//
//
//
//
//                } else {
//                    if (s.length() > 0) {
//                        Toast.makeText(DataFinansialKprActivity.this, "Jangka waktu tidak boleh 0 ", Toast.LENGTH_SHORT).show();
//                        et_jangka_waktu.setText("1");
//
//                        et_jangka_waktu.setSelection(et_jangka_waktu.getText().length());
//                    }
//
//                }
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        et_jangka_waktu_takeover.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                Log.d("max jw", Integer.toString(maxJw));
//                if (s != null && s.length() > 0 && Integer.parseInt(s.toString()) != 0) {
//                    if (Integer.parseInt(et_jangka_waktu_takeover.getText().toString()) > maxJw) {
//                        Toast.makeText(DataFinansialKprActivity.this, "Jangka waktu tidak boleh melebihi " + maxJw + " bulan", Toast.LENGTH_SHORT).show();
//                        et_jangka_waktu_takeover.setText(Integer.toString(maxJw));
//                        et_jangka_waktu_takeover.setSelection(et_jangka_waktu_takeover.getText().length());
//                    }
//
//                    //jika qanun, tidak ambil data margin rate
//                    if(dataFinansial.getQanun()!=null&&dataFinansial.getQanun().equalsIgnoreCase("true")){
//                        if(et_margin_pertahun.getText()!=null&&!et_margin_pertahun.getText().toString().equalsIgnoreCase("0")&&!et_margin_pertahun.getText().toString().isEmpty()){
//
//                            Long totalTakeoverKonsumtif=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString()));
//
//                            nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),totalTakeoverKonsumtif,Integer.parseInt(et_jangka_waktu_takeover.getText().toString()));
//
//                            et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));
//                        }
//
//
//
//                    }
//                    else{
//
//
//                        if (Integer.parseInt(et_jangka_waktu_takeover.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW1()) && !dataFinansial.getMAXJW1().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE1().equalsIgnoreCase("0")) {
//                            et_margin_pertahun.setText(dataFinansial.getMARGINRATE1());
//                        } else if (Integer.parseInt(et_jangka_waktu_takeover.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW2()) && !dataFinansial.getMAXJW2().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE2().equalsIgnoreCase("0")) {
//                            et_margin_pertahun.setText(dataFinansial.getMARGINRATE2());
//                        } else if (Integer.parseInt(et_jangka_waktu_takeover.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW3()) && !dataFinansial.getMAXJW3().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE3().equalsIgnoreCase("0")) {
//                            et_margin_pertahun.setText(dataFinansial.getMARGINRATE3());
//                        } else if (Integer.parseInt(et_jangka_waktu_takeover.getText().toString()) <= Integer.parseInt(dataFinansial.getMAXJW4()) && !dataFinansial.getMAXJW4().equalsIgnoreCase("0") && !dataFinansial.getMARGINRATE4().equalsIgnoreCase("0")) {
//                            et_margin_pertahun.setText(dataFinansial.getMARGINRATE4());
//                        }
//
//                        Long totalTakeoverKonsumtif=Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString()));
//
//                        nilaiAngsuranAppel=hitungAngsuranAppel(Float.parseFloat(et_margin_pertahun.getText().toString()),totalTakeoverKonsumtif,Integer.parseInt(et_jangka_waktu_takeover.getText().toString()));
//
//                        et_angsuran.setText(String.valueOf(nilaiAngsuranAppel));
//                    }
//
//
//                } else {
//                    if (s.length() > 0) {
//                        Toast.makeText(DataFinansialKprActivity.this, "Jangka waktu tidak boleh 0 ", Toast.LENGTH_SHORT).show();
//                        et_jangka_waktu_takeover.setText("1");
//
//                        et_jangka_waktu_takeover.setSelection(et_jangka_waktu_takeover.getText().length());
//                    }
//
//                }
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//
//    }


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


    //method ketika onclick pada gambar atau textview
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                CustomDialog.DialogBackpress(this);
                break;

            case R.id.btn_send:

                if(idProgram.equalsIgnoreCase("222")){
                    if (validateFormFlpp()) {
                        validasiData();
                    }
                }
                else{
                    if (validateForm()) {
                        validasiData();
                    }
                }



                break;

        }
    }

    //method ketika memilih salah satu opsi di dialog fragment, tidak bisa di simplifikasi , eh belum bisa, belum cukup ilmunya :')
    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
//        if (title.equalsIgnoreCase("jenis tiering")) {
//            et_jenis_tiering.setText(data.getName());
//            AppUtil.dynamicIconLogoChangeDropdown(DataFinansialKprActivity.this, tf_jenis_tiering);
//            val_jenis_tiering = data.getValue(); //set value berdasarkan pilihan di dialog fragment
//            AppUtil.dynamicIconLogoChangeDropdown(DataFinansialKprActivity.this,tf_jenis_tiering);
//        }

    }





    public void loadData() {
        loading.setVisibility(View.VISIBLE);


        ReqIdAplikasi req = new ReqIdAplikasi(idAplikasi);
        //pantekan
//        inquiryRPC req=new inquiryRPC(344703);
        Call<ParseResponse> call;

        //khusus flpp nembak ke service inquiry sendiri
        if(idProgram.equalsIgnoreCase("222")){
            call = apiClientAdapter.getApiInterface().inquiryDataFinansialKprFlpp(req);
        }
        else{
            call = apiClientAdapter.getApiInterface().inquiryDataFinansialKpr(req);
        }

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {
//                            Toast.makeText(DataFinansialKprActivity.this, "Id aplikasi masih hardcode", Toast.LENGTH_SHORT).show();
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            loading.setVisibility(View.GONE);
                            String dataAngsuranString = "";
                            String dataFinansialString = response.body().getData().get("prescoring").toString();
//                            Log.d("pirmen",response.body().getData_qanun().toString());
                            Gson gson = new Gson();

                            dataFinansial = gson.fromJson(dataFinansialString, DataFinansialKpr.class);



                            //kalau permohonan kredit 0, berarti belum pernah ngisi data finansial sebelumnya, maka edittext tidak diisi dulu, hanya beberapa field yang bisa diisi
                            if (dataFinansial.getpERMOHONANKREDIT()==null||dataFinansial.getpERMOHONANKREDIT()==0) {

                                if(idProgram.equalsIgnoreCase("222")){
                                    textChangedForDataFinansialFlpp();
                                }
                                else{
                                    textChangedForDataFinansial();
                                }


                                //ini ngambil dari halaman hotprospek detail, jadi udah sesuai dari respon DB
                                et_tujuan_penggunaan.setText(tujuanPenggunaanDepan);
                                et_nilai_permohonan_pembiayaan.setText(String.valueOf(plafond));
                                et_angsuran_pinjaman_eksisting_1.setText(dataFinansial.getaNGSURAN().toString());
//                                    et_jumlah_plafon_pembiayaan_diusulkan.setText(dataFinansial.getPLAFONDINDUK().toString());

                                if(dataFinansial.getID_TUJUAN().equalsIgnoreCase("37")){
                                    tf_jangka_waktu_qardh.setVisibility(View.VISIBLE);
                                    tf_tujuan_qardh.setVisibility(View.VISIBLE);
                                }
                                else {
                                    tf_jangka_waktu_qardh.setVisibility(View.GONE);
                                    tf_tujuan_qardh.setVisibility(View.GONE);
                                }

                                if(dataFinansial.getSUKU_MARGIN()!=null&& !dataFinansial.getSUKU_MARGIN().equalsIgnoreCase("0")){
                                    et_margin_pertahun.setText(dataFinansial.getSUKU_MARGIN());


                                }


                            } else {
                                setData();
                            }


//                                Log.d("firmansah",dataFinansialString);
//                                Log.d("firmansah isdebest",dataFinansial.getoMZETPERBULAN());


                        } else {
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }

    public void simpanData(final SweetAlertDialog dialog) {

        //reroute ke simpan data flpp, kalau pembiayaan flpp
        if(idProgram.equalsIgnoreCase("222")){
            simpanDataFlpp(dialog);
        }
        else{
            dialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
            dialog.setCanceledOnTouchOutside(false);
            SimpanDataFinansialKpr req = new SimpanDataFinansialKpr();

            req.setAngsuran(Long.parseLong(dataFinansial.getaNGSURAN()));

            req.setAngsuranScoring(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran.getText().toString()).replace(".", ""));

            req.setBesarUangMuka(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_uang_muka.getText().toString()));

            req.setDeviasiDir("0");//gak ada di field

            req.setHargaBeli(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_harga_beli.getText().toString())));

            req.setiNPUTPERMOHONAN(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString()))); //disamain pake plafon usulan, gaktau ini isinya apaan

            req.setIdAplikasi(Integer.toString(idAplikasi));
            req.setIdCif(cif);

            req.setIdPrescoring(Integer.parseInt(dataFinansial.getiDPRESCORING()));
            req.setIdScoring(Integer.parseInt(dataFinansial.getiDSCORING()));

            req.setIdTujuan(idTujuanPembiayaan);

            req.setIndexNpw("0"); //pantekan

            req.setjANGKAWAKTU(Integer.parseInt(et_jangka_waktu.getText().toString()));

            req.setKewajibanLain(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString())));

            req.setLtv("0");//apa ini gak ngerti

            req.setmAKSIMUMPLAFOND(Long.parseLong(dataFinansial.getmAKSIMUMPLAFOND().toString()));

            req.setNamaAOS(appPreferences.getNama());

            req.setNetIncome(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString()));

            req.setoMZETPERBULAN((NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())));


            req.setOmzetBersih((NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString()))); //sama dengan net income

            req.setOmzetSetelahPotongan((NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())));//sama dengan net income

            req.setPenghasilanLain(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())));


            req.setPlafondUsul(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())));

            req.setSukuMargin(Double.parseDouble(et_margin_pertahun.getText().toString()));

            req.setSukuMarginBulanan(Double.parseDouble(et_margin_pertahun.getText().toString())/12);//suku margin dibagi 12

            req.setUidAOS(Integer.toString(appPreferences.getUid()));

            req.setRumahKeBerapa(et_rumah_keberapa.getText().toString());

            //KHUSUS TAKEOVER
            if(dataFinansial.getID_TUJUAN().equalsIgnoreCase("37")){
                req.setJwQardh(Integer.parseInt(et_jangka_waktu_qardh.getText().toString()));
                req.setTujuanQardh(et_tujuan_qardh.getText().toString());
            }

            //selain takeover langsung pantek aja 0 dan string kosong untuk parameter dibawah
            else{
                req.setJwQardh(0);
                req.setTujuanQardh("");
            }




            Call<ParseResponse> call;

            //validasi flpp, maka tembak ke service flpp
            if(idProgram.equalsIgnoreCase("222")){
                call = apiClientAdapter.getApiInterface().simpanDataFinansialKprFlpp(req);
            }
            else{
                call = apiClientAdapter.getApiInterface().simpanDataFinansialKpr(req);
            }
            call.enqueue(new Callback<ParseResponse>() {
                @Override
                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                    try {
                        if (response.isSuccessful()) {
//                            Toast.makeText(DataFinansialKprActivity.this, "Id aplikasi masih hardcode", Toast.LENGTH_SHORT).show();
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
//                                    startActivity(new Intent(DataFinansialKprActivity.this, HotprospekDetailActivity.class));
                                    }
                                });
                                dialog.showCancelButton(false);


                            } else {
                                loading.setVisibility(View.GONE);
                                dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                dialog.setTitle("Gagal");
                                dialog.setContentText(response.body().getMessage()+"\n");
                                dialog.setConfirmText("Coba Lagi");
                                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        simpanData(dialog);
//            startActivity(new Intent(this, PipelineActivity.class));
//                                    startActivity(new Intent(DataFinansialKprActivity.this, HotprospekDetailActivity.class));
                                    }
                                });
                                dialog.setCancelText("Batal");
                                dialog.showCancelButton(true);
                                AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
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
//                                    startActivity(new Intent(DataFinansialKprActivity.this, HotprospekDetailActivity.class));
                                }
                            });
                            dialog.setCancelText("Batal");
                            dialog.showCancelButton(true);

                            AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), error.getMessage());
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
//                                    startActivity(new Intent(DataFinansialKprActivity.this, HotprospekDetailActivity.class));
                        }
                    });
                    dialog.setCancelText("Batal");
                    dialog.showCancelButton(true);
                    AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                }
            });
        }




    }

    public void simpanDataFlpp(final SweetAlertDialog dialog) {


        dialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        dialog.setCanceledOnTouchOutside(false);
        SimpanDataFinansialKpr req = new SimpanDataFinansialKpr();

        req.setAngsuran(Long.parseLong(dataFinansial.getaNGSURAN()));

        req.setAngsuranScoring(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_angsuran.getText().toString()).replace(".", ""));

        req.setBesarUangMuka(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_nominal_uang_muka.getText().toString()));

        req.setDeviasiDir("0");//gak ada di field

        req.setHargaBeli(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_harga_beli.getText().toString())));

        req.setiNPUTPERMOHONAN(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString()))); //disamain pake plafon usulan, gaktau ini isinya apaan

        req.setIdAplikasi(Integer.toString(idAplikasi));
        req.setIdCif(cif);

        req.setIdPrescoring(Integer.parseInt(dataFinansial.getiDPRESCORING()));
        req.setIdScoring(Integer.parseInt(dataFinansial.getiDSCORING()));

        req.setIdTujuan(idTujuanPembiayaan);

        req.setIndexNpw("0"); //pantekan

        req.setjANGKAWAKTU(Integer.parseInt(et_jangka_waktu.getText().toString()));

        req.setKewajibanLain(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_kewajiban_lain.getText().toString())));

        req.setLtv("0");//apa ini gak ngerti

        req.setmAKSIMUMPLAFOND(Long.parseLong(dataFinansial.getmAKSIMUMPLAFOND().toString()));

        req.setNamaAOS(appPreferences.getNama());

        req.setNetIncome(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString()));

        req.setoMZETPERBULAN((NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString())));


        req.setOmzetBersih((NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString()))); //sama dengan net income

        req.setOmzetSetelahPotongan((NumberTextWatcherCanNolForThousand.trimCommaOfString(et_penghasilan_bersih.getText().toString())));//sama dengan net income

        req.setPenghasilanLain(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_tunjangan_tetap_lainnya.getText().toString())));


        req.setPlafondUsul(Long.parseLong(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString())));

        req.setSukuMargin(Double.parseDouble(et_margin_pertahun.getText().toString()));

        req.setSukuMarginBulanan(Double.parseDouble(et_margin_pertahun.getText().toString())/12);//suku margin dibagi 12

        req.setUidAOS(Integer.toString(appPreferences.getUid()));

        req.setRumahKeBerapa(et_rumah_keberapa.getText().toString());

        req.setJointIncome(Double.parseDouble(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_joint_income.getText().toString())));

        //KHUSUS TAKEOVER
        if(dataFinansial.getID_TUJUAN().equalsIgnoreCase("37")){
            req.setJwQardh(Integer.parseInt(et_jangka_waktu_qardh.getText().toString()));
            req.setTujuanQardh(et_tujuan_qardh.getText().toString());
        }

        //selain takeover langsung pantek aja 0 dan string kosong untuk parameter dibawah
        else{
            req.setJwQardh(0);
            req.setTujuanQardh("");
        }




        Call<ParseResponse> call;

        //validasi flpp, maka tembak ke service flpp
        if(idProgram.equalsIgnoreCase("222")){
            call = apiClientAdapter.getApiInterface().simpanDataFinansialKprFlpp(req);
        }
        else{
            call = apiClientAdapter.getApiInterface().simpanDataFinansialKpr(req);
        }
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {
//                            Toast.makeText(DataFinansialKprActivity.this, "Id aplikasi masih hardcode", Toast.LENGTH_SHORT).show();
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
//                                    startActivity(new Intent(DataFinansialKprActivity.this, HotprospekDetailActivity.class));
                                }
                            });
                            dialog.showCancelButton(false);


                        } else {
                            loading.setVisibility(View.GONE);
                            dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            dialog.setTitle("Gagal");
                            dialog.setContentText(response.body().getMessage()+"\n");
                            dialog.setConfirmText("Coba Lagi");
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    simpanData(dialog);
//            startActivity(new Intent(this, PipelineActivity.class));
//                                    startActivity(new Intent(DataFinansialKprActivity.this, HotprospekDetailActivity.class));
                                }
                            });
                            dialog.setCancelText("Batal");
                            dialog.showCancelButton(true);
                            AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
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
//                                    startActivity(new Intent(DataFinansialKprActivity.this, HotprospekDetailActivity.class));
                            }
                        });
                        dialog.setCancelText("Batal");
                        dialog.showCancelButton(true);

                        AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), error.getMessage());
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
//                                    startActivity(new Intent(DataFinansialKprActivity.this, HotprospekDetailActivity.class));
                    }
                });
                dialog.setCancelText("Batal");
                dialog.showCancelButton(true);
                AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }


    public void validasiData() {
//        loading.setVisibility(View.VISIBLE);
        final SweetAlertDialog dialog = new SweetAlertDialog(DataFinansialKprActivity.this, SweetAlertDialog.PROGRESS_TYPE);
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
        req.setMAKSIMUM_PLAFOND(Long.toString(dataFinansial.getmAKSIMUMPLAFOND()));
        req.setINPUT_PERMOHONAN(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_jumlah_plafon_pembiayaan_diusulkan.getText().toString()));
        req.setJANGKA_WAKTU(et_jangka_waktu.getText().toString());
        req.setMARGIN(et_margin_pertahun.getText().toString());
        req.setOMZET_PERBULAN(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_gaji_pokok.getText().toString()));

        Call<ParseResponse> call;

        //validasi flpp, maka tembak ke service flpp
        if(idProgram.equalsIgnoreCase("222")){
             call = apiClientAdapter.getApiInterface().validasiDataFinansialKprFlpp(req);
        }
        else{
            call = apiClientAdapter.getApiInterface().validasiDataFinansialKpr(req);
        }

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
//                            Toast.makeText(DataFinansialKprActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();


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
//                            AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), response.body().getMessage());
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
//                        AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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
        subValidate(et_rumah_keberapa, tf_rumah_keberapa);


        if(dataFinansial.getID_TUJUAN().equalsIgnoreCase("37")){
            subValidate(et_tujuan_qardh, tf_tujuan_qardh);
            subValidate(et_jangka_waktu_qardh, tf_jangka_waktu_qardh);
        }


        //khusus untuk takeover murni maka validasi jangka waktu takeover
//        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("91")){
//            subValidate(et_jangka_waktu_takeover,tf_jangka_waktu_takeover);
//        }

        //khusus untuk takeover konsumtif maka validasi jangka waktu takeover dan plafon konsumtif


        //validasi uang muka harus 30% dari harga beli, jika tujuan penggunaan, kendaraan roda 2 , termasuk yang takeover
        BigDecimal nilaiUangMuka = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_uang_muka.getText().toString()));


        //cek jangan sampe plafon lebih besar dari harga beli, alias uang mukanya minus (khusus non takeover murni)

            if (nilaiUangMuka.compareTo(new BigDecimal(0)) < 0) {
                tf_harga_beli.setError("Harga beli tidak boleh lebih kecil dari plafon usulan/konsumtif", false);
            } else {
                jumlahValidasi = jumlahValidasi + 1;
            }


        //sesuaikan dengan jumlah subvalidate dan pengecekan lainnya
        //konsumtif

        if(dataFinansial.getID_TUJUAN().equalsIgnoreCase("37")){
            if(jumlahValidasi==15){
                return true;
            }
            else{
                return false;
            }
        }
            else if (jumlahValidasi == 13) {
                return true;
            } else {
                Log.d("statusvalidasi", Integer.toString(jumlahValidasi));
                return false;
            }




    }

    private boolean validateFormFlpp() {

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
        subValidate(et_rumah_keberapa, tf_rumah_keberapa);
        subValidate(et_nominal_uang_muka, tf_nominal_uang_muka);
        subValidate(et_joint_income, tf_joint_income);


        if(dataFinansial.getID_TUJUAN().equalsIgnoreCase("37")){
            subValidate(et_tujuan_qardh, tf_tujuan_qardh);
            subValidate(et_jangka_waktu_qardh, tf_jangka_waktu_qardh);
        }



        //khusus untuk takeover murni maka validasi jangka waktu takeover
//        if(dataFinansial.getIDTUJUAN().equalsIgnoreCase("91")){
//            subValidate(et_jangka_waktu_takeover,tf_jangka_waktu_takeover);
//        }

        //khusus untuk takeover konsumtif maka validasi jangka waktu takeover dan plafon konsumtif


        //validasi uang muka harus 30% dari harga beli, jika tujuan penggunaan, kendaraan roda 2 , termasuk yang takeover
        BigDecimal nilaiUangMuka = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(et_uang_muka.getText().toString()));


        //cek jangan sampe plafon lebih besar dari harga beli, alias uang mukanya minus (khusus non takeover murni)

        if (nilaiUangMuka.compareTo(new BigDecimal(0)) < 0) {
            tf_harga_beli.setError("Harga beli tidak boleh lebih kecil dari plafon usulan/konsumtif", false);
        } else {
            jumlahValidasi = jumlahValidasi + 1;
        }


        //sesuaikan dengan jumlah subvalidate dan pengecekan lainnya
        //konsumtif

        if(dataFinansial.getID_TUJUAN().equalsIgnoreCase("37")){
            if(jumlahValidasi==15){
                return true;
            }
            else{
                return false;
            }
        }
        else if (jumlahValidasi == 15) {
            return true;
        } else {
            Log.d("statusvalidasi", Integer.toString(jumlahValidasi));
            return false;
        }




    }

    private void subValidate(EditText editext, TextFieldBoxes textFieldBoxes) {
        if (editext.getText().toString().trim().equalsIgnoreCase("pilih") || editext.getText().toString().trim().equalsIgnoreCase("pilih jenis tiering") || editext.getText().toString().trim().isEmpty()) {
            //agar user tau field yang error dari textfield
            textFieldBoxes.setError("Harap Isi " + textFieldBoxes.getLabelText().substring(0, textFieldBoxes.getLabelText().length() - 2), true);

            //agar user tau nama field yang error dari snackbar
            AppUtil.notiferror(DataFinansialKprActivity.this, findViewById(android.R.id.content), "Harap isi " + textFieldBoxes.getLabelText().substring(0, textFieldBoxes.getLabelText().length() - 2));


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
            CustomDialog.DialogBackpress(DataFinansialKprActivity.this);
        }
        else{
            super.onBackPressed();
        }
    }


    private void setDynamicIcon() {
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_tujuan_penggunaan, tf_tujuan_penggunaan);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_nilai_permohonan_pembiayaan, tf_nilai_permohonan_pembiayaan);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_gaji_pokok, tf_gaji_pokok);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_tunjangan_tetap_lainnya, tf_tunjangan_tetap_lainnya);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_kewajiban_lain, tf_kewajiban_lain);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_angsuran_pinjaman_eksisting_1, tf_angsuran_pinjaman_eksisting_1);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_penghasilan_bersih, tf_penghasilan_bersih);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_margin_pertahun, tf_margin_pertahun);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_jangka_waktu, tf_jangka_waktu);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_harga_beli, tf_harga_beli);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_jumlah_plafon_pembiayaan_diusulkan, tf_jumlah_plafon_pembiayaan_diusulkan);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_ftv_ratio, tf_ftv_ratio);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_angsuran, tf_angsuran_);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_rpc, tf_rpc);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_uang_muka, tf_uang_muka);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_jangka_waktu_qardh, tf_jangka_waktu_qardh);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_jangka_waktu_takeover, tf_jangka_waktu_takeover);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_jumlah_plafon_pembiayaan_konsumtif, tf_jumlah_plafon_pembiayaan_konsumtif);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_rumah_keberapa,tf_rumah_keberapa);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_nominal_uang_muka,tf_nominal_uang_muka);
        AppUtil.dynamicIconLogoChange(DataFinansialKprActivity.this, et_joint_income,tf_joint_income);
    }

    public long hitungAngsuranAppel(float _rate, long _plafond, int jk_waktu){
        return (long)((long)(((_rate / 12) / 100) * _plafond) / (1 - (1 / Math.pow((1.00 + ((_rate / 100) / 12.00)), jk_waktu))));
    }

    private void otherViewChanges(){
        if(idProgram.equalsIgnoreCase("222")){
            tf_nominal_uang_muka.setVisibility(View.VISIBLE);
            tf_joint_income.setVisibility(View.VISIBLE);
        }
        else{
            tf_nominal_uang_muka.setVisibility(View.GONE);
            tf_joint_income.setVisibility(View.GONE);
        }
    }


}
