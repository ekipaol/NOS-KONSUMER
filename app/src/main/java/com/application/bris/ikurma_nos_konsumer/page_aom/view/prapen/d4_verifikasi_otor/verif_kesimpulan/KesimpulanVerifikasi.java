package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d4_verifikasi_otor.verif_kesimpulan;


import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.databinding.ActivityKesimpulanVerifikasiBinding;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class KesimpulanVerifikasi extends AppCompatActivity implements View.OnClickListener {

    private
    BigDecimal umur = new BigDecimal(125);
    @NonNull
    ActivityKesimpulanVerifikasiBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKesimpulanVerifikasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        BigDecimal sisabulan = umur.remainder(new BigDecimal(12));
        BigDecimal totumur = umur.divide(new BigDecimal(12), 0, RoundingMode.HALF_EVEN);
        String text = totumur.toString() + " Tahun " + sisabulan.toString() + " Bulan";
        binding.etMaksimalJangkaWaktuPembiayaanNasabah.setText(text);
        setContentView(view);
        disableEditText();
        NumberText();
        backgroundStatusBar();
        allOnClicks();
        AppUtil.toolbarRegular(this, "Kesimpulan Verifikasi");

    }


    private void disableEditText() {
        binding.etPensesuaianRac.setFocusable(false);
        binding.tfPensesuaianRac.setFocusable(false);
        binding.etPensesuaianFiturPembiayaan.setFocusable(false);
        binding.tfPensesuaianFiturPembiayaan.setFocusable(false);
        binding.etMaksimalJangkaWaktuPembiayaanNasabah.setFocusable(false);
        binding.etMaksimalJangkaWaktuPembiayaanNasabah.setFocusable(false);
        binding.etMaksimalAngsuranBulanan.setFocusable(false);
        binding.tfMaksimalAngsuranBulanan.setFocusable(false);
    }

    private void NumberText(){
        binding.etMaksimalAngsuranBulanan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(binding.etMaksimalAngsuranBulanan));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                finish();
                break;
        }
    }

    private void allOnClicks(){
        binding.btnClose.setOnClickListener(this);
    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

//        public void sendMessage(View view) {
        // Do something in response to button
//        Intent intent = new Intent(this, KesimpulanVerifikasi.class);
//        EditText editText = (EditText) findViewById(R.id.et_pensesuaian_rac);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        String message2 = CheckAnswer(message);
//          intent.putExtra(CORRECT_MESSAGE, message2);
//        startActivity(intent);
//    }

//    public static String CheckAnswer(String string) {
//        String sesuai[] = {"Sesuai","Tidak Sesuai"};
//          String answer = null;
//        for (int i = 0; i <= sesuai.length - 1; i++) {
//            if (string == sesuai[i]) {
//                answer = "Sesuai";
//                break;
//            } else answer = "Tidak .";
//       }
//        return answer;
//    }
}