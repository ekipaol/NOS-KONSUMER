package com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline;
import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 17/05/2019.
 */

public class KonsumerKMGInquiryPipeline {
    @SerializedName("idPipeline")
    private int idPipeline;

    public KonsumerKMGInquiryPipeline(int idPipeline) {
        this.idPipeline = idPipeline;
    }

    public void setIdPipeline(int idPipeline) {
        this.idPipeline = idPipeline;
    }
}