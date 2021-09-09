package com.application.bris.ikurma_nos_konsumer.view.corelayout.activation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.baseapp.RouteApp;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.view.corelayout.login.LoginActivity2;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PID on 6/15/2019.
 */

public class FragmentWelcome2 extends Fragment implements Step, ConfirmListener, View.OnClickListener{

    @BindView(R.id.iv_welcome)
    ImageView iv_welcome;
    @BindView(R.id.tv_welcome_title)
    TextView tv_welcome_title;
    @BindView(R.id.tv_welcome_desc)
    TextView tv_welcome_desc;
    @BindView(R.id.btn_bd)
    View btn_bd;

    public int counter = 0;

    public FragmentWelcome2() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        ButterKnife.bind(this, view);
        Glide.with(getContext())
                .load(R.drawable.welcome_page2)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(iv_welcome);

        tv_welcome_title.setText("Cepat dan Digital");
        tv_welcome_desc.setText("Proses pembiayaanmu dan segera kirim ke pemutus");


      //otomasi status backdoor jika prod atau dev
        if (!BuildConfig.IS_PRODUCTION) {
            btn_bd.setOnClickListener(this);
        }
        else{
            if(BuildConfig.IS_BD){
                btn_bd.setOnClickListener(this);
            }
        }



//        iv_welcome.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Intent intent=new Intent(getActivity(), DataFinansialPurnaActivity.class); //data lengkap
////                Intent intent=new Intent(getActivity(), DataFinansialKmgActivity.class);
//                startActivity(intent);
//                return true;
//            }
//        });
        return view;
    }



    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }

    @Override
    public void success(boolean val) {

    }

    @Override
    public void confirm(boolean val) {
        if (val){
            Bundle mbundle = new Bundle();
            mbundle.putString("type", "bdwelcome");
            RouteApp router = new RouteApp(getContext());
            router.openActivityWithDataAndClearAllPrevious(LoginActivity2.class, mbundle);
            counter = 0;
        }
        else {
            AppUtil.showToastShort(getContext(), "Wrong Developer Key");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_bd :
                ++counter;
                if (counter > 6){
                    CustomDialog.DialogInput(getContext(), "Input Developer Key", FragmentWelcome2.this);
                    counter = 0;


                }
                break;
        }
    }


}
