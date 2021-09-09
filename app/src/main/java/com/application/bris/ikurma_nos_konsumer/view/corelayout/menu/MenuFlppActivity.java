package com.application.bris.ikurma_nos_konsumer.view.corelayout.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.flpp.FollowUpFlppActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.flpp.InputUlangFlppActivity;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.flpp.KonfirmasiFlppActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.CoreLayoutActivity;

public class MenuFlppActivity extends AppCompatActivity {

    ImageView bt_followup, bt_konfirmasi,bt_inputulang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_flpp);
        bt_followup =findViewById(R.id.bt_followup);
        bt_konfirmasi =findViewById(R.id.bt_konfirmasi_flpp);
        bt_inputulang =findViewById(R.id.bt_input_ulang_flpp);

        AppUtil.toolbarRegular(this, "Pilih Tindak Lanjut ");

        //toolbar back configuration, hard to explain, just ask to mr eki. In short, this is needed so the activity flows as eki wants
        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(MenuFlppActivity.this, CoreLayoutActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                startActivity(intent);


            }
        });


        bt_followup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent=new Intent(MenuFlppActivity.this, FollowUpFlppActivity.class);
                    startActivity(intent);

            }
        });

        bt_konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                                     Toast.makeText(MenuFlppActivity.this, "Masih Dalam Pengembangan", Toast.LENGTH_SHORT).show();


                    Intent intent=new Intent(MenuFlppActivity.this, KonfirmasiFlppActivity.class);
                    startActivity(intent);
//                     Toast.makeText(MenuFlppActivity.this, "Belum ada menu pipeline multifaedah", Toast.LENGTH_SHORT).show();

            }
        });

        bt_inputulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                                     Toast.makeText(MenuFlppActivity.this, "Masih Dalam Pengembangan", Toast.LENGTH_SHORT).show();


                Intent intent=new Intent(MenuFlppActivity.this, InputUlangFlppActivity.class);
                startActivity(intent);
//                     Toast.makeText(MenuFlppActivity.this, "Belum ada menu pipeline multifaedah", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent=new Intent(MenuFlppActivity.this, CoreLayoutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
        startActivity(intent);
    }
}