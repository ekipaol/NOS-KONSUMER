package com.application.bris.ikurma_nos_konsumer.database.model;

import android.content.Context;

import com.application.bris.ikurma_nos_konsumer.database.pojo.ProductPojo;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by idong on 27/05/2019.
 */

public class ProductModel {
    private Realm realm;
    private Context context;

    public ProductModel(Context context) {
        this.context = context;
        this.realm = Realm.getDefaultInstance();
    }

    public void add(String kode_segmen, String nama_segmen, String kode_produk, String nama_produk, int kode_gimmick, String nama_gimmick){
        realm.beginTransaction();
        ProductPojo data = realm.createObject(ProductPojo.class);
        data.setKode_segmen(kode_segmen);
        data.setNama_segmen(nama_segmen);
        data.setKode_produk(kode_produk);
        data.setNama_produk(nama_produk);
        data.setKode_gimmick(kode_gimmick);
        data.setNama_gimmick(nama_gimmick);
        realm.commitTransaction();
    }

    public void clear() {
        RealmResults<ProductPojo> data = realm.where(ProductPojo.class).findAll();
        if(data.size() > 0){
            realm.beginTransaction();
            data.deleteAllFromRealm();
            realm.commitTransaction();
        }
    }
}
