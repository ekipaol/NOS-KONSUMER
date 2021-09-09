package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.prescreening.remaksslik;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.SlikListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.prescreening.model.ModelRemaksSlik;
import com.application.bris.ikurma_nos_konsumer.util.Stringinfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by idong on 16/09/2019.
 */

public class FragmentRemaksSlikPasangan extends Fragment implements SlikListener{

    @BindView(R.id.rv_remakssliknasabah)
    RecyclerView rv_remakssliknasabah;
    @BindView(R.id.tv_info)
    TextView tv_info;

    private String dataSlikPasangan,approved;
    private List<ModelRemaksSlik> data;
    private int REQUEST_CODE = 1;
    private RemaksSlikAdapater adp = null;

    public FragmentRemaksSlikPasangan(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_remakssliknasabah, container, false);
        ButterKnife.bind(this, view);

        dataSlikPasangan = getArguments().getString("dataSlikPasangan");
        approved=getArguments().getString("approved");

        //DATA HISTORY COVENANCE
        Gson gson = new Gson();
        Type type = new TypeToken<List<ModelRemaksSlik>>() {}.getType();
        data = gson.fromJson(dataSlikPasangan, type);
        adp = new RemaksSlikAdapater(getContext(), data, this);
        rv_remakssliknasabah.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_remakssliknasabah.setItemAnimator(new DefaultItemAnimator());
        rv_remakssliknasabah.setAdapter(adp);

        tv_info.setText(Html.fromHtml(Stringinfo.info_pilihremarks));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        data.clear();
        dataSlikPasangan = getArguments().getString("dataSlikPasangan");

        //DATA HISTORY COVENANCE
        Gson gson = new Gson();
        Type type = new TypeToken<List<ModelRemaksSlik>>() {}.getType();
        data = gson.fromJson(dataSlikPasangan, type);
        adp = new RemaksSlikAdapater(getContext(), data, this);
        rv_remakssliknasabah.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_remakssliknasabah.setItemAnimator(new DefaultItemAnimator());
        rv_remakssliknasabah.setAdapter(adp);
    }

    @Override
    public void onSelectSlik(List<ModelRemaksSlik> listData, ModelRemaksSlik data, int position) {

        if(approved.equalsIgnoreCase("no")){
            Gson gson = new Gson();
            Bundle bundle = new Bundle();
            bundle.putString("dataListString", gson.toJson(listData));
            bundle.putString("dataString", gson.toJson(data));
            bundle.putString("idType", "pasangan");
            bundle.putInt("position", position);
            Intent intent = new Intent(getContext(), RemaksSlikEditActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
}
