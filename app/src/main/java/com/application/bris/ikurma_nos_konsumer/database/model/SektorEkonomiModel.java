package com.application.bris.ikurma_nos_konsumer.database.model;

import android.content.Context;
import com.application.bris.ikurma_nos_konsumer.database.pojo.SektorEkonomiPojo;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by idong on 27/05/2019.
 */

public class SektorEkonomiModel {
    private Realm realm;
    private Context context;

    public SektorEkonomiModel(Context context) {
        this.context = context;
        this.realm = Realm.getDefaultInstance();
    }

    public void add(String kategsekom){
        realm.beginTransaction();
        SektorEkonomiPojo data = realm.createObject(SektorEkonomiPojo.class);
        data.setKateg_sekom(kategsekom);
        realm.commitTransaction();
    }

    public void clear() {
        RealmResults<SektorEkonomiPojo> data = realm.where(SektorEkonomiPojo.class).findAll();
        if(data.size() > 0){
            realm.beginTransaction();
            data.deleteAllFromRealm();
            realm.commitTransaction();
        }
    }
}
