package com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 17/05/2019.
 */

public class inquiryPipeline {
    @SerializedName("idPipeline")
    private int idPipeline;
    @SerializedName("idRole")
    private int idRole;

    public inquiryPipeline(int idPipeline, int idRole) {
        this.idPipeline = idPipeline;
        this.idRole = idRole;
    }

    public void setIdPipeline(int idPipeline) {
        this.idPipeline = idPipeline;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}
