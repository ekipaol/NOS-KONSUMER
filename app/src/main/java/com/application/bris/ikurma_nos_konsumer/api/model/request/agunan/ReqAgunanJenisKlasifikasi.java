package com.application.bris.ikurma_nos_konsumer.api.model.request.agunan;

import com.google.gson.annotations.SerializedName;

public class ReqAgunanJenisKlasifikasi {
    @SerializedName("VALUE_PENGIKATAN")
    private String valuePengikatan;
    @SerializedName("JENIS_PENGIKATAN")
    private String jenisPengikatan;

    public String getValuePengikatan() {
        return valuePengikatan;
    }

    public void setValuePengikatan(String valuePengikatan) {
        this.valuePengikatan = valuePengikatan;
    }

    public String getJenisPengikatan() {
        return jenisPengikatan;
    }

    public void setJenisPengikatan(String jenisPengikatan) {
        this.jenisPengikatan = jenisPengikatan;
    }
}
