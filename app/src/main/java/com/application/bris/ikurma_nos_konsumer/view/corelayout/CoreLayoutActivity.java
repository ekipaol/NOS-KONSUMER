package com.application.bris.ikurma_nos_konsumer.view.corelayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;

import com.application.bris.ikurma_nos_konsumer.api.model.request.ReqUidLong;
import com.application.bris.ikurma_nos_konsumer.database.AppPreferences;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.ranking.FragmentRanking;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.Error;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponse;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseError;
import com.application.bris.ikurma_nos_konsumer.api.model.request.EmptyRequest;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.database.model.ProductModel;
import com.application.bris.ikurma_nos_konsumer.database.model.SektorEkonomiModel;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.BackStackFragment;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.home.FragmentHome;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.user.FragmentUser;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoreLayoutActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private Fragment fragment;
    private boolean doubleBackToExitPressedOnce = false;

    @BindView(R.id.bn_main)
    BottomNavigationView bottomNavigationView;

    private ApiClientAdapter apiClientAdapter;
    private ProductModel productModel;
    private SektorEkonomiModel sektorEkonomiModel;
    private String kode_segmen, nama_segmen, kode_product, nama_product, nama_gimmick;
    private int kode_gimmick;
    AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corelayout);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        productModel = new ProductModel(this);
        sektorEkonomiModel = new SektorEkonomiModel(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        appPreferences=new AppPreferences(this);
        loadFragment(new FragmentHome());
        loadDataProduct();
        loadKategSektorEkonomi();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment frg : getSupportFragmentManager().getFragments()) {
            frg.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        if (!BackStackFragment.handleBackPressed(getSupportFragmentManager())) {{

            if(doubleBackToExitPressedOnce){
                finishAffinity();

                return;
            }
            doubleBackToExitPressedOnce = true;
            AppUtil.showToastShort(this, "Tekan sekali lagi untuk keluar aplikasi");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }}
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragment  = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new FragmentHome();
                break;
            case R.id.navigation_dashboard:
//                Toast.makeText(this, "Segera Tersedia", Toast.LENGTH_SHORT).show();
                fragment = new FragmentRanking();
                break;
            case R.id.navigation_notifications:
                fragment = new FragmentUser();
                break;
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }
        return false;
    }

    private void loadDataProduct(){
        ReqUidLong req=new ReqUidLong();
        req.setUid(appPreferences.getUid());
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().getProductAmanah(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            productModel.clear();
                            JsonArray arrSegmen = response.body().getData().get("listSegmen").getAsJsonArray();
                            for (int i = 0; i < arrSegmen.size(); i++){
                                JsonObject objSegmen = arrSegmen.get(i).getAsJsonObject();
                                kode_segmen = objSegmen.get("key").getAsString();
                                nama_segmen = objSegmen.get("value").getAsString();
                                JsonArray arrProduct = objSegmen.get("listProduk").getAsJsonArray();
                                for (int j = 0; j < arrProduct.size(); j++){
                                    JsonObject objProduct = arrProduct.get(j).getAsJsonObject();
                                    kode_product = objProduct.get("key").getAsString();
                                    nama_product = objProduct.get("value").getAsString();
                                    kode_gimmick = 0;
                                    nama_gimmick = "";
                                    if (objProduct.has("listGimmick")){
                                        JsonArray arrGimmick = objProduct.get("listGimmick").getAsJsonArray();
                                        for (int k = 0; k < arrGimmick.size(); k++){
                                            JsonObject objGimmick = arrGimmick.get(k).getAsJsonObject();
                                            kode_gimmick = objGimmick.get("gimmick_id").getAsInt();
                                            nama_gimmick = objGimmick.get("program_name").getAsString();
                                            productModel.add(kode_segmen, nama_segmen, kode_product, nama_product, kode_gimmick, nama_gimmick);
                                        }
                                    }
                                    else{
                                        productModel.add(kode_segmen, nama_segmen, kode_product, nama_product, kode_gimmick, nama_gimmick);
                                    }
                                }

                            }
                        }
                        else {
                            AppUtil.showToastShort(CoreLayoutActivity.this, response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.showToastShort(CoreLayoutActivity.this, error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.showToastShort(CoreLayoutActivity.this, getString(R.string.txt_connection_failure));
            }
        });
    }

    private void loadKategSektorEkonomi(){
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().getKategSektorEkonomii(EmptyRequest.INSTANCE);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            sektorEkonomiModel.clear();
                            JsonArray arrKategSekom = response.body().getData().get("dtKategoriSektorEko").getAsJsonArray();
                            for (int i = 0; i < arrKategSekom.size(); i++){
                                JsonObject objKategSekom = arrKategSekom.get(i).getAsJsonObject();
                                sektorEkonomiModel.add(objKategSekom.get("DESC3").getAsString());
                            }
                        }
                        else {
                            AppUtil.showToastShort(CoreLayoutActivity.this, response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.showToastShort(CoreLayoutActivity.this, error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.showToastShort(CoreLayoutActivity.this, getString(R.string.txt_connection_failure));
            }
        });
    }
}
