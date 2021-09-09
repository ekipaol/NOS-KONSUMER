package com.application.bris.ikurma_nos_konsumer.view.corelayout.activation;

import android.content.Context;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class WelcomeStepAdapter extends AbstractFragmentStepAdapter{
    private String title;

    public WelcomeStepAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        switch (position){
            case 0:
                title = "Simple dan Mudah";
                break;
            case 1:
                title = "Cepat dan Digital";
                break;
            case 2:
                title = "Alhamdulillah...";
                break;
            default:
                title = "Default Tab";
        }
        return new StepViewModel.Builder(context)
                .setTitle(title)
                .create();
    }

    @Override
    public Step createStep(int position) {
        switch (position) {
            case 0:
                FragmentWelcome fragmentWelcome = new FragmentWelcome();
                return fragmentWelcome;
            case 1:
                FragmentWelcome2 fragmentWelcome2 = new FragmentWelcome2();
                return fragmentWelcome2;
            case 2:
                FragmentWelcome3 fragmentWelcome3 = new FragmentWelcome3();
                return fragmentWelcome3;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}