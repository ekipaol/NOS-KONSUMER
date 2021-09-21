package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.databinding.PrapenAoActivityMemoBinding;
import com.application.bris.ikurma_nos_konsumer.model.menu.ListViewSubmenuHotprospek;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataHutang;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataMemo;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d1_data_entry.data_nasabah.DataNasabahPrapenActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.data_hutang.DataHutangAdapter;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class MemoActivity extends AppCompatActivity implements GenericListenerOnSelect {

    private List<ListViewSubmenuHotprospek> dataMenu;
    private MemoAdapter memoAdapter;

    public static int idAplikasi=0;
    private List<DataMemo> data=new ArrayList<>();
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private PrapenAoActivityMemoBinding binding;
    private BottomSheetBehavior bottomSheetBehavior;
    List<MGenericModel> dataDropdownFlow=new ArrayList<>();

    int hideButtonClickIndicator=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=PrapenAoActivityMemoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        bottomSheetBehavior=BottomSheetBehavior.from(binding.bottomSheet.bottomSheet);

        //pantekan default toolbar
        customToolbar();
        backgroundStatusBar();
        otherViewChanges();

        //initialize status
        setData();
        initialize();
        onClicks();


    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void initialize(){
        binding.rvMemo.setVisibility(View.VISIBLE);
        binding.rvMemo.setHasFixedSize(true);
        memoAdapter = new MemoAdapter(MemoActivity.this, data);
        binding.rvMemo.setLayoutManager(new LinearLayoutManager(MemoActivity.this));
        binding.rvMemo.setItemAnimator(new DefaultItemAnimator());
        binding.rvMemo.setAdapter(memoAdapter);

    }


    public void customToolbar(){
        binding.toolbarNosearch.tvPageTitle.setText("Memo");

        binding.toolbarNosearch.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog.DialogBackpress(MemoActivity.this);
            }
        });
    }

    private void otherViewChanges(){
        //default bottom shit position
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        //biar keyboard gak nongol di awal activity kalau ada edittext
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void setData(){
        DataMemo data1=new DataMemo();
        data1.setNama("Aleksander Dashkevitct");
        data1.setJabatan("Marketing Mikro");
        data1.setJenisPutusan("Dilanjutkan");
        data1.setTahapAplikasi("Confirm Validasi Engine");
        data1.setTanggal(AppUtil.parseTanggalGeneral("18032021","ddmmyyyy","dd-mm-yyyy"));
        data1.setMemo("Menurut saya ini sangatlah bagus sekali, pembiayaan ini ingin saya lanjutkan dan langsung saya sikat aja rasanya pak");

        DataMemo data2=new DataMemo();
        data2.setNama("Pelawak Uzbek");
        data2.setJabatan("FF");
        data2.setJenisPutusan("Dilanjutkan");
        data2.setTahapAplikasi("Verifikasi");
        data2.setTanggal(AppUtil.parseTanggalGeneral("19032021","ddmmyyyy","dd-mm-yyyy"));
        data2.setMemo("Dilanjutkan aja pak biar cepat selesai, saya mau pulang soalnya");

        DataMemo data3=new DataMemo();
        data3.setNama("Hasan Kahraman");
        data3.setJabatan("Supervisor FF");
        data3.setJenisPutusan("Dilanjutkan");
        data3.setTahapAplikasi("Otor Verifikasi");
        data3.setTanggal(AppUtil.parseTanggalGeneral("19032021","ddmmyyyy","dd-mm-yyyy"));
        data3.setMemo("Ya boleh silahkan aja kamu pulang");


        data.add(data1);
        data.add(data2);
        data.add(data3);


    }



    @Override
    public void onSelect(String title, MGenericModel data) {

    }


    private void onClicks(){
        binding.btSembunyiInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("buttonindicator",Integer.toString(hideButtonClickIndicator));
                if(hideButtonClickIndicator==0){

                    binding.llContentInfo.animate().translationY(0).setDuration(500);
                    binding.llContentInfo.setVisibility(View.GONE);

                    binding.btSembunyiInfo.setVisibility(View.GONE);
                    binding.btTampilInfo.setVisibility(View.VISIBLE);
                    hideButtonClickIndicator++;

                }

            }
        });

        binding.btTampilInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(hideButtonClickIndicator==1){
                    binding.cvDataPembiayaanCatatan.setVisibility(View.VISIBLE);
                    binding.llContentInfo.setVisibility(View.VISIBLE);
                    binding.btTampilInfo.setVisibility(View.GONE);
                    binding.btSembunyiInfo.setVisibility(View.VISIBLE);
                    hideButtonClickIndicator--;
                }
            }
        });

        binding.buttonLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.bottomSheet.extendedCatatan.setEnabled(true);
                binding.bottomSheet.extendedCatatan.setFocusable(true);

                binding.bottomSheet.extendedCatatan.requestFocus();
                binding.bottomSheet.extendedCatatan.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        binding.bottomSheet.ivCapsuleClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            //close bottom sheet on click outside
            if (bottomSheetBehavior.getState()==BottomSheetBehavior.STATE_EXPANDED) {

                Rect outRect = new Rect();
                binding.bottomSheet.bottomSheet.getGlobalVisibleRect(outRect);

                if(!outRect.contains((int)ev.getRawX(), (int)ev.getRawY()))
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        CustomDialog.DialogBackpress(MemoActivity.this);
    }
}