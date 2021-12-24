package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.canvasing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseHasilRAC;
import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseRAC;
import com.application.bris.ikurma_nos_konsumer.databinding.FragmentHasilCanvasingBinding;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;


import java.util.List;

public class FragmentResumeRAC extends Fragment implements Step, SwipeRefreshLayout.OnRefreshListener {
    FragmentHasilCanvasingBinding binding;
    private List<MparseResponseRAC> lrac;
    private String approved;
    MparseResponseHasilRAC objRAC;
    ResumeRACAdapter resumeRACAdapter;

    public FragmentResumeRAC(List<MparseResponseRAC> lRAC, String maprroved, MparseResponseHasilRAC objHasilRAC) {
        lrac = lRAC;
        approved = maprroved;
        objRAC = objHasilRAC;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHasilCanvasingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        MparseResponseRAC rac = new MparseResponseRAC();
        if (lrac.get(0).getParameter().equalsIgnoreCase("Resume Rac")) {
            initialize();
        }else{
            rac.setParameter("Resume Rac");
            rac.setValue(approved);
            lrac.add(0, rac);
            initialize();
        }
        return view;
    }

    private void initialize() {
        binding.rvListRac.setVisibility(View.VISIBLE);
        binding.rvListRac.setHasFixedSize(true);
        resumeRACAdapter = new ResumeRACAdapter(getContext(), lrac);
        binding.rvListRac.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvListRac.setItemAnimator(new DefaultItemAnimator());
        binding.rvListRac.setAdapter(resumeRACAdapter);
//        binding.refresh.setOnRefreshListener(null);
        binding.refresh.setEnabled(false);
        binding.refresh.setDistanceToTriggerSync(220);
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

    }

    @Override
    public void onRefresh() {
    }
}
