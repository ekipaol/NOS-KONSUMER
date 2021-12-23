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

import com.application.bris.ikurma_nos_konsumer.api.model.response_prapen.MparseResponseFitur;
import com.application.bris.ikurma_nos_konsumer.databinding.FragmentHasilCanvasingBinding;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.List;

public class FragmentResumeFitur extends Fragment implements Step, SwipeRefreshLayout.OnRefreshListener {
    FragmentHasilCanvasingBinding binding;
    private List<MparseResponseFitur> lrac;
    private String approved;
    ResumeFiturAdapter ResumeFiturAdapter;

    public FragmentResumeFitur(List<MparseResponseFitur> lFitur, String aFitur) {
        lrac = lFitur;
        approved = aFitur;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHasilCanvasingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        MparseResponseFitur rac = new MparseResponseFitur();
        rac.setParameter("Resume Fitur");
        rac.setValue(approved);
        lrac.add(0, rac);
        initialize();
        return view;
    }

    private void initialize() {
        binding.rvListRac.setVisibility(View.VISIBLE);
        binding.rvListRac.setHasFixedSize(true);
        ResumeFiturAdapter = new ResumeFiturAdapter(getContext(), lrac);
        binding.rvListRac.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvListRac.setItemAnimator(new DefaultItemAnimator());
        binding.rvListRac.setAdapter(ResumeFiturAdapter);
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
