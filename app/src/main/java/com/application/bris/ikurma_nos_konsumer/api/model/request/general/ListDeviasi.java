package com.application.bris.ikurma_nos_konsumer.api.model.request.general;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 05/07/2019.
 */

public class ListDeviasi {
    @SerializedName("uid")
    public Integer uid;
    @SerializedName("kode_kanwil")
    public String kodeKanwil;
    @SerializedName("kode_uker")
    public String kodeUker;
    @SerializedName("kode_rsc")
    public String kodeRsc;

    public ListDeviasi(Integer uid, String kodeKanwil, String kodeUker, String kodeRsc) {
        this.uid = uid;
        this.kodeKanwil = kodeKanwil;
        this.kodeUker = kodeUker;
        this.kodeRsc = kodeRsc;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setKodeKanwil(String kodeKanwil) {
        this.kodeKanwil = kodeKanwil;
    }

    public void setKodeUker(String kodeUker) {
        this.kodeUker = kodeUker;
    }

    public void setKodeRsc(String kodeRsc) {
        this.kodeRsc = kodeRsc;
    }
}
