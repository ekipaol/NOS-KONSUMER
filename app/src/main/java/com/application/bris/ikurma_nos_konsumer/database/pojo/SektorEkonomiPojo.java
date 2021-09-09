package com.application.bris.ikurma_nos_konsumer.database.pojo;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by idong on 27/05/2019.
 */

public class SektorEkonomiPojo extends RealmObject {
    @Required
    private String kateg_sekom;


    public SektorEkonomiPojo(){
        super();
    }

    public String getKateg_sekom() {
        return kateg_sekom;
    }

    public void setKateg_sekom(String kateg_sekom) {
        this.kateg_sekom = kateg_sekom;
    }
}
