package com.application.bris.ikurma_nos_konsumer.api.model.request.monitoring;

import com.google.gson.annotations.SerializedName;

public class ReqRiwayatMutasi {

    @SerializedName("noRekening")
    private String noRekening;


    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }


}
