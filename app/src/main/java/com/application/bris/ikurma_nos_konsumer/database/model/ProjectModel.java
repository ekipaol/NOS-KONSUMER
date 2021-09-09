package com.application.bris.ikurma_nos_konsumer.database.model;

import android.content.Context;

import com.application.bris.ikurma_nos_konsumer.page_aom.model.MListProject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by idong on 27/05/2019.
 */

public class ProjectModel {
    private Realm realm;
    private Context context;

    public ProjectModel(Context context) {
        this.context = context;
        this.realm = Realm.getDefaultInstance();
    }

    public void add(String nama, String fid_pihak_ketiga, String tipe, int id_project){
        realm.beginTransaction();
        MListProject data = realm.createObject(MListProject.class);
        data.setNAMA(nama);
        data.setFID_PIHAK_KETIGA(fid_pihak_ketiga);
        data.setTipe(tipe);
        data.setID_PROJECT(id_project);
        realm.commitTransaction();
    }

    public void clear() {
        RealmResults<MListProject> data = realm.where(MListProject.class).findAll();
        if(data.size() > 0){
            realm.beginTransaction();
            data.deleteAllFromRealm();
            realm.commitTransaction();
        }
    }
}
