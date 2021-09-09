package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.history;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.history.model.ModelHistoryFasilitas;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by idong on 20/06/2019.
 */

public class FragmentHistoryFasilitas extends Fragment{

    @BindView(R.id.rv_historyfasilitas)
    RecyclerView rv_historyfasilitas;

    private String dataFasilitas;
    private List<ModelHistoryFasilitas> data;

    public FragmentHistoryFasilitas(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ao_fragment_historyfasilitas, container, false);
        ButterKnife.bind(this, view);
        dataFasilitas = getArguments().getString("dataFasilitas");

        //DATA HISTORY FASILITAS
        Gson gson = new Gson();
        Type type = new TypeToken<List<ModelHistoryFasilitas>>() {}.getType();
        data = gson.fromJson(dataFasilitas, type);
        HistoryFasilitasAdapater adp = new HistoryFasilitasAdapater(getContext(), data);
        rv_historyfasilitas.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_historyfasilitas.setItemAnimator(new DefaultItemAnimator());
        rv_historyfasilitas.setAdapter(adp);

        return view;
    }
}
