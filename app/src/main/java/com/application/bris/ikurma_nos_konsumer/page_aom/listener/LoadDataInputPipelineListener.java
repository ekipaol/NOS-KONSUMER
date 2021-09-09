package com.application.bris.ikurma_nos_konsumer.page_aom.listener;


import com.application.bris.ikurma_nos_konsumer.page_aom.model.MKategoriNasabahPensiun;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MProgramKmg;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MRekananDM;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MTujuanPenggunaan;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MlistInstansi;

/**
 * Created by PID on 05/05/19.
 */

public interface LoadDataInputPipelineListener {

    void onSelectProgram(String title, MProgramKmg data);

    void onSelectInstitusi(String title, MKategoriNasabahPensiun data);

    void onSelectRekananDM(String title, MRekananDM data);

    void onSelectTujuan(String title, MTujuanPenggunaan data);

    void onSelectInstansi(String title, MlistInstansi data);

    void onSelectKategNasabah(String title, MKategoriNasabahPensiun data);
}

