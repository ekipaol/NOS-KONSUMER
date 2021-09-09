package com.application.bris.ikurma_nos_konsumer.page_aom.listener;


import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListBidangPekerjaan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListJenisKPR;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListPihakKetiga;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MTujuanPenggunaan;

/**
 * Created by PID on 05/05/19.
 */

public interface LoadDataInputPipelineKPRListener {

    void onSelectPihakKetiga(String title, MListPihakKetiga data);

    void onSelectTujuan(String title, MTujuanPenggunaan data);

    void onSelectBidangPekerjaan(String title, MListBidangPekerjaan data);

    void onSelectJenisKPR(String title, MListJenisKPR data);
}

