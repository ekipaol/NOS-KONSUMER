package com.application.bris.ikurma_nos_konsumer.page_aom.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/26/2019.
 */

public class MTujuanPenggunaan {

    @SerializedName("ID_TUJUAN")
    private int ID_TUJUAN;
    @SerializedName("DESC1")
    private String DESC1;

    public MTujuanPenggunaan(int ID_TUJUAN, String DESC1) {
        this.ID_TUJUAN = ID_TUJUAN;
        this.DESC1 = DESC1;
    }

    public int getID_TUJUAN() {
        return ID_TUJUAN;
    }

    public String getDESC1() {
        return DESC1;
    }
}
