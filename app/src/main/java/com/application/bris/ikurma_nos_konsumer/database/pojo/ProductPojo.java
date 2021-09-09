package com.application.bris.ikurma_nos_konsumer.database.pojo;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by idong on 27/05/2019.
 */

public class ProductPojo extends RealmObject {
    @Required
    private String kode_segmen;
    @Required
    private String nama_segmen;
    @Required
    private String kode_produk;
    @Required
    private String nama_produk;
    private int kode_gimmick;
    private String nama_gimmick;

    public ProductPojo(){
        super();
    }

    public String getKode_segmen() {
        return kode_segmen;
    }

    public void setKode_segmen(String kode_segmen) {
        this.kode_segmen = kode_segmen;
    }

    public String getNama_segmen() {
        return nama_segmen;
    }

    public void setNama_segmen(String nama_segmen) {
        this.nama_segmen = nama_segmen;
    }

    public String getKode_produk() {
        return kode_produk;
    }

    public void setKode_produk(String kode_produk) {
        this.kode_produk = kode_produk;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public int getKode_gimmick() {
        return kode_gimmick;
    }

    public void setKode_gimmick(int kode_gimmick) {
        this.kode_gimmick = kode_gimmick;
    }

    public String getNama_gimmick() {
        return nama_gimmick;
    }

    public void setNama_gimmick(String nama_gimmick) {
        this.nama_gimmick = nama_gimmick;
    }
}
