package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.scoring_konsumer;

import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek.ReqScoringKmg;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.model.hotprospek.ScoringKmg;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScoringKonsumerActivity extends AppCompatActivity{

    @BindView(R.id.ll_content)
    LinearLayout ll_content;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    @BindView(R.id.tv_skor_penilaian_risiko)
    TextView tv_skor_penilaian_risiko;
    @BindView(R.id.tv_grade_skoring)
    TextView tv_grade_skoring;
    @BindView(R.id.tv_definisi)
    TextView tv_definisi;
    @BindView(R.id.tv_kesimpulan)
    TextView tv_kesimpulan;
    @BindView(R.id.sv_rpc)
    ScrollView sv_rpc;
    @BindView(R.id.btn_selesai)
    Button btn_selesai;

    private int idAplikasi;
    private int cif;

    private ApiClientAdapter apiClientAdapter;
    private String dataString,approved;
    private ScoringKmg data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoring_konsumer_kmg);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        cif = getIntent().getIntExtra("cif",0);
        approved = getIntent().getStringExtra("approved");
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Scoring");

        //diasble edittects if approved page
        if (approved.equalsIgnoreCase("yes")){
            btn_selesai.setVisibility(View.GONE);
        }
        btn_selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }


    private void loadData() {
        loading.setVisibility(View.VISIBLE);
        ReqScoringKmg req = new ReqScoringKmg(); //101928


        //pantekan
//        req.setIdAplikasi(101928);
//        req.setCif(81862);

         req.setIdAplikasi(idAplikasi);
        req.setCif(cif);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updateScoringKmg(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                //pantekan
//                Toast.makeText(ScoringKonsumerActivity.this, "id aplikasi masih hardcoded", Toast.LENGTH_SHORT).show();

                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            dataString = response.body().getData().toString();
                            data = gson.fromJson(dataString, ScoringKmg.class);

                            tv_skor_penilaian_risiko.setText(data.getScore());
                            tv_definisi.setText(data.getDesc());
                            tv_grade_skoring.setText(data.getGrade());
                            tv_kesimpulan.setText(data.getKesimpulan());

//                            sv_rpc.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    sv_rpc.fullScroll(ScrollView.FOCUS_DOWN);
//                                }
//                            });
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ScoringKonsumerActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(ScoringKonsumerActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }
}