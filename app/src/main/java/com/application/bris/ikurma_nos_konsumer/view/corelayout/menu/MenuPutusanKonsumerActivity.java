package com.application.bris.ikurma_nos_konsumer.view.corelayout.menu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;

import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.HotprospekActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.HotprospekKprActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.pipeline.KonsumerKMGPipelineActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.pipeline.kpr.KonsumerKprPipelineActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.CoreLayoutActivity;


public class MenuPutusanKonsumerActivity extends AppCompatActivity {
    ImageView bt_multifaedah, bt_griya_faedah;
    TextView tvNotifikasiMultiFaedah,tvNotifikasiGriyaFaedah;
    CardView cvGriyaFaedah,cvMultiFaedah;
    AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_putusan);
        bt_multifaedah=findViewById(R.id.bt_multi_faedah);
        bt_griya_faedah =findViewById(R.id.bt_griya_faedah);

        tvNotifikasiMultiFaedah=findViewById(R.id.tv_notifikasi_multi_faedah);
        tvNotifikasiGriyaFaedah=findViewById(R.id.tv_notifikasi_griya_faedah);

        cvGriyaFaedah=findViewById(R.id.cv_griya_faedah);
        cvMultiFaedah=findViewById(R.id.cv_multi_faedah);

        tvNotifikasiMultiFaedah.setVisibility(View.GONE);
        tvNotifikasiGriyaFaedah.setVisibility(View.GONE);

        appPreferences=new AppPreferences(this);




        AppUtil.toolbarRegular(this, "Pilih Jenis Pembiayaan");

        otherViewChanges();

        //toolbar back configuration, hard to explain, just ask to mr eki. In short, this is needed so the activity flows as eki wants
        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(MenuPutusanKonsumerActivity.this, CoreLayoutActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                startActivity(intent);


            }
        });


        bt_multifaedah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getIntent().getStringExtra("jenisMenu").equalsIgnoreCase("hotprospek")){
                    Intent intent=new Intent(MenuPutusanKonsumerActivity.this, HotprospekActivity.class);
                    startActivity(intent);
                }
                else if(getIntent().getStringExtra("jenisMenu").equalsIgnoreCase("pipeline")){
                    Intent intent=new Intent(MenuPutusanKonsumerActivity.this, KonsumerKMGPipelineActivity.class);
                    startActivity(intent);
                }




            }
        });

        bt_griya_faedah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                                     Toast.makeText(MenuPutusanKonsumerActivity.this, "Masih Dalam Pengembangan", Toast.LENGTH_SHORT).show();


                 if(getIntent().getStringExtra("jenisMenu").equalsIgnoreCase("hotprospek")){
                    Intent intent=new Intent(MenuPutusanKonsumerActivity.this, HotprospekKprActivity.class);
                    startActivity(intent);
                }
                else if(getIntent().getStringExtra("jenisMenu").equalsIgnoreCase("pipeline")){
                    Intent intent=new Intent(MenuPutusanKonsumerActivity.this, KonsumerKprPipelineActivity.class);
                    startActivity(intent);
//                     Toast.makeText(MenuPutusanKonsumerActivity.this, "Belum ada menu pipeline multifaedah", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent=new Intent(MenuPutusanKonsumerActivity.this, CoreLayoutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
        startActivity(intent);
    }

    private void otherViewChanges(){
        if(appPreferences.getCbAmanah().equalsIgnoreCase("true")){
            cvMultiFaedah.setVisibility(View.GONE);
        }
        else{
            cvMultiFaedah.setVisibility(View.VISIBLE);
        }
    }


}
