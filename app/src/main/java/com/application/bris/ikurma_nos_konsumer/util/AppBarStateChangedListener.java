package com.application.bris.ikurma_nos_konsumer.util;

import com.google.android.material.appbar.AppBarLayout;

/**
 * Created by PID on 4/26/2019.
 */

public abstract class AppBarStateChangedListener implements AppBarLayout.OnOffsetChangedListener {

    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private State mCurrentState = State.IDLE;

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0) {
            setCurrentStateAndNotify(appBarLayout, State.EXPANDED);
        }
        else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
            setCurrentStateAndNotify(appBarLayout, State.COLLAPSED);
        }
        else {
            setCurrentStateAndNotify(appBarLayout, State.IDLE);
        }
    }

    private void setCurrentStateAndNotify(AppBarLayout appBarLayout, State state){
        if (mCurrentState != state) {
            onStateChanged(appBarLayout, state);
        }
        mCurrentState = state;
    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
}
