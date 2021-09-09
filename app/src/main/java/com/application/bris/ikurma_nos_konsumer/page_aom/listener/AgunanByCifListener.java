package com.application.bris.ikurma_nos_konsumer.page_aom.listener;

/**
 * Created by PID on 05/05/19.
 */

public interface AgunanByCifListener {
    void onAgunanByCifSelect(String idAplikasi, String idCif, String idAgunan, String jenisAgunan);

    void onAgunanByCifSelect(String idAplikasi, String idCif, String idAgunan, String jenisAgunan,String uuid);
}
