package com.application.bris.ikurma_nos_konsumer.page_aom.listener;

import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.prescreening.model.ModelRemaksSlik;

import java.util.List;

/**
 * Created by idong on 17/09/2019.
 */

public interface SlikListener {
    void onSelectSlik(List<ModelRemaksSlik> listData, ModelRemaksSlik data, int position);
}
