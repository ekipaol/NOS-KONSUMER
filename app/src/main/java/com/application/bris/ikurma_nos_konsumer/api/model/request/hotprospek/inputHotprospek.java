package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 13/06/2019.
 */

public class inputHotprospek {
    @SerializedName("idAplikasi")
    private int idAplikasi;
    @SerializedName("uid")
    private String uid;
    @SerializedName("rencanaPlafond")
    private int rencanaPlafond;
    @SerializedName("tenor")
    private int tenor;
    @SerializedName("jenisProduk")
    private String jenisProduk;
    @SerializedName("fidTujuanPenggunaan")
    private int fidTujuanPenggunaan;
    @SerializedName("fidPhoto")
    private int fidPhoto;

    public inputHotprospek(int idAplikasi, String uid, int rencanaPlafond, int tenor, String jenisProduk, int fidTujuanPenggunaan, int fidPhoto) {
        this.idAplikasi = idAplikasi;
        this.uid = uid;
        this.rencanaPlafond = rencanaPlafond;
        this.tenor = tenor;
        this.jenisProduk = jenisProduk;
        this.fidTujuanPenggunaan = fidTujuanPenggunaan;
        this.fidPhoto = fidPhoto;
    }

    public inputHotprospek(int idAplikasi, String uid, int rencanaPlafond, String jenisProduk, int fidTujuanPenggunaan, int fidPhoto) {
        this.idAplikasi = idAplikasi;
        this.uid = uid;
        this.rencanaPlafond = rencanaPlafond;
        this.jenisProduk = jenisProduk;
        this.fidTujuanPenggunaan = fidTujuanPenggunaan;
        this.fidPhoto = fidPhoto;
    }

    public void setIdAplikasi(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setRencanaPlafond(int rencanaPlafond) {
        this.rencanaPlafond = rencanaPlafond;
    }

    public void setTenor(int tenor) {
        this.tenor = tenor;
    }

    public void setJenisProduk(String jenisProduk) {
        this.jenisProduk = jenisProduk;
    }

    public void setFidTujuanPenggunaan(int fidTujuanPenggunaan) {
        this.fidTujuanPenggunaan = fidTujuanPenggunaan;
    }

    public void setFidPhoto(int fidPhoto) {
        this.fidPhoto = fidPhoto;
    }
}
