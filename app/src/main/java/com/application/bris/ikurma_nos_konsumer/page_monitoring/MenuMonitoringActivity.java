package com.application.bris.ikurma_nos_konsumer.page_monitoring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_monitoring.monitoring_pencairan.MonitoringPencairanActivity;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.CoreLayoutActivity;


public class MenuMonitoringActivity extends AppCompatActivity {
    ImageView bt_monitoring_pencairan, bt_monitoring_npf;
    TextView tvNotifikasiMonitoringPencairan,tvNotifikasiMonitoringNpf;
    CardView cvPutusanKoreksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_monitoring);
        bt_monitoring_pencairan=findViewById(R.id.bt_monitoring_pencairan);
        bt_monitoring_npf =findViewById(R.id.bt_monitoring_npf);

        tvNotifikasiMonitoringPencairan=findViewById(R.id.tv_notifikasi_monitoring_pencairan);
        tvNotifikasiMonitoringNpf=findViewById(R.id.tv_notifikasi_monitoring_npf);

        tvNotifikasiMonitoringPencairan.setVisibility(View.GONE);
        tvNotifikasiMonitoringNpf.setVisibility(View.GONE);




        AppUtil.toolbarRegular(this, "Pilih Jenis Monitoring");

        //toolbar back configuration, hard to explain, just ask to mr eki. In short, this is needed so the activity flows as eki wants
        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(MenuMonitoringActivity.this, CoreLayoutActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                startActivity(intent);


            }
        });


        bt_monitoring_pencairan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent=new Intent(MenuMonitoringActivity.this, MonitoringPencairanActivity.class);
                    startActivity(intent);

            }
        });

        bt_monitoring_npf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                                     Toast.makeText(MenuMonitoringActivity.this, "Masih Dalam Pengembangan", Toast.LENGTH_SHORT).show();


//                    Intent intent=new Intent(MenuMonitoringActivity.this, KonsumerKprPipelineActivity.class);
//                    startActivity(intent);
                     Toast.makeText(MenuMonitoringActivity.this, "belum jadi", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent=new Intent(MenuMonitoringActivity.this, CoreLayoutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
        startActivity(intent);
    }


}