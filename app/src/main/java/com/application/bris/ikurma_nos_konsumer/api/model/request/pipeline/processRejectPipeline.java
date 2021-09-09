package com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 21/05/2019.
 */

public class processRejectPipeline {
    @SerializedName("fidPipeline")
    private int fidPipeline;
    @SerializedName("uid")
    private int uid;

    public processRejectPipeline(int fidPipeline, int uid) {
        this.fidPipeline = fidPipeline;
        this.uid = uid;
    }

    public void setFidPipeline(int fidPipeline) {
        this.fidPipeline = fidPipeline;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
