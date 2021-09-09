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
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.history.model.ModelHistoryStatus;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by idong on 20/06/2019.
 */

public class FragmentHistoryStatus extends Fragment{

    @BindView(R.id.rv_historystatus)
    RecyclerView rv_historystatus;

    private String dataStatus;
    private List<ModelHistoryStatus> data;

    public FragmentHistoryStatus(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_historystatus, container, false);
        ButterKnife.bind(this, view);

        dataStatus = getArguments().getString("dataStatus");

        //DATA HISTORY STATUS
        Gson gson = new Gson();
        Type type = new TypeToken<List<ModelHistoryStatus>>() {}.getType();
        data = gson.fromJson(dataStatus, type);
        HistoryStatusAdapater adp = new HistoryStatusAdapater(getContext(), data);
        rv_historystatus.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_historystatus.setItemAnimator(new DefaultItemAnimator());
        rv_historystatus.setAdapter(adp);

        return view;


    }
}
